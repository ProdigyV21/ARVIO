package com.arflix.tv.data.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.arflix.tv.data.model.DrmInfo
import com.arflix.tv.data.model.IptvChannel
import com.google.gson.Gson

/**
 * On-disk channel store for the Live TV page.
 *
 * Why this exists: huge IPTV playlists (50k+ channels) were persisted/loaded as one
 * giant gzipped-JSON blob via Gson. Serializing/parsing 50k channels allocates a
 * multi-MB JSON string (Large Object Space) plus millions of intermediate objects,
 * spiking the (384MB-capped) heap into a blocking-GC spiral that froze the UI.
 *
 * Storing channels in SQLite instead:
 *  - replaces the giant JSON string + Gson intermediates with streamed cursor I/O
 *    (bounded memory regardless of channel count), and
 *  - enables windowed/paged reads + SQL COUNT/GROUP BY so the UI never has to hold
 *    the whole list in memory (Stage 2).
 *
 * Mirrors the raw-SQLiteOpenHelper approach already used by [IptvEpgIndex] rather
 * than pulling in Room, to stay consistent and avoid new build dependencies.
 */
internal class IptvChannelStore(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    private val gson = Gson()

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE channels (
                source_key TEXT NOT NULL,
                ord INTEGER NOT NULL,
                id TEXT NOT NULL,
                name TEXT NOT NULL,
                stream_url TEXT NOT NULL,
                group_title TEXT NOT NULL,
                logo TEXT,
                epg_id TEXT,
                raw_title TEXT,
                xtream_stream_id INTEGER,
                catchup_days INTEGER NOT NULL DEFAULT 0,
                catchup_type TEXT,
                catchup_source TEXT,
                tvg_name TEXT,
                provider_channel_number TEXT,
                request_headers_json TEXT,
                language TEXT,
                country TEXT,
                quality_label TEXT,
                variant_key TEXT,
                drm_json TEXT,
                PRIMARY KEY(source_key, ord)
            )
            """.trimIndent()
        )
        db.execSQL("CREATE INDEX idx_channels_group ON channels(source_key, group_title)")
        db.execSQL(
            """
            CREATE TABLE channel_sources (
                source_key TEXT PRIMARY KEY NOT NULL,
                updated_ms INTEGER NOT NULL,
                channel_count INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS channels")
        db.execSQL("DROP TABLE IF EXISTS channel_sources")
        onCreate(db)
    }

    /** Replace the whole channel set for [sourceKey] in a single transaction. */
    fun replaceAll(sourceKey: String, channels: List<IptvChannel>, updatedAtMs: Long) {
        if (sourceKey.isBlank()) return
        val db = writableDatabase
        db.beginTransaction()
        try {
            db.delete("channels", "source_key = ?", arrayOf(sourceKey))
            if (channels.isNotEmpty()) {
                val statement = db.compileStatement(
                    """
                    INSERT OR REPLACE INTO channels
                    (source_key, ord, id, name, stream_url, group_title, logo, epg_id, raw_title,
                     xtream_stream_id, catchup_days, catchup_type, catchup_source, tvg_name,
                     provider_channel_number, request_headers_json, language, country, quality_label,
                     variant_key, drm_json)
                    VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                    """.trimIndent()
                )
                try {
                    channels.forEachIndexed { index, channel ->
                        statement.clearBindings()
                        statement.bindString(1, sourceKey)
                        statement.bindLong(2, index.toLong())
                        statement.bindString(3, channel.id)
                        statement.bindString(4, channel.name)
                        statement.bindString(5, channel.streamUrl)
                        statement.bindString(6, channel.group)
                        bindNullableString(statement, 7, channel.logo)
                        bindNullableString(statement, 8, channel.epgId)
                        bindNullableString(statement, 9, channel.rawTitle)
                        if (channel.xtreamStreamId != null) {
                            statement.bindLong(10, channel.xtreamStreamId.toLong())
                        } else {
                            statement.bindNull(10)
                        }
                        statement.bindLong(11, channel.catchupDays.toLong())
                        bindNullableString(statement, 12, channel.catchupType)
                        bindNullableString(statement, 13, channel.catchupSource)
                        bindNullableString(statement, 14, channel.tvgName)
                        bindNullableString(statement, 15, channel.providerChannelNumber)
                        bindNullableString(
                            statement, 16,
                            channel.requestHeaders.takeIf { it.isNotEmpty() }?.let { gson.toJson(it) }
                        )
                        bindNullableString(statement, 17, channel.language)
                        bindNullableString(statement, 18, channel.country)
                        bindNullableString(statement, 19, channel.qualityLabel)
                        bindNullableString(statement, 20, channel.variantKey)
                        bindNullableString(statement, 21, channel.drmInfo?.let { gson.toJson(it) })
                        statement.executeInsert()
                    }
                } finally {
                    statement.close()
                }
            }
            db.compileStatement(
                "INSERT OR REPLACE INTO channel_sources(source_key, updated_ms, channel_count) VALUES (?,?,?)"
            ).use { meta ->
                meta.bindString(1, sourceKey)
                meta.bindLong(2, updatedAtMs)
                meta.bindLong(3, channels.size.toLong())
                meta.executeInsert()
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun count(sourceKey: String): Int {
        if (sourceKey.isBlank()) return 0
        return readableDatabase.rawQuery(
            "SELECT channel_count FROM channel_sources WHERE source_key = ?",
            arrayOf(sourceKey)
        ).use { cursor -> if (cursor.moveToFirst()) cursor.getInt(0) else 0 }
    }

    fun updatedAtMs(sourceKey: String): Long {
        if (sourceKey.isBlank()) return 0L
        return readableDatabase.rawQuery(
            "SELECT updated_ms FROM channel_sources WHERE source_key = ?",
            arrayOf(sourceKey)
        ).use { cursor -> if (cursor.moveToFirst()) cursor.getLong(0) else 0L }
    }

    /** Read every channel for [sourceKey] in original order, streamed from the cursor. */
    fun loadAll(sourceKey: String): List<IptvChannel> = window(sourceKey, offset = 0, limit = -1)

    /**
     * Windowed read — `ORDER BY ord LIMIT/OFFSET`. Pass [limit] < 0 for "all".
     * Used by the paged channel list so only the visible slice is materialised.
     */
    fun window(sourceKey: String, offset: Int, limit: Int): List<IptvChannel> {
        if (sourceKey.isBlank()) return emptyList()
        val sql = buildString {
            append("SELECT * FROM channels WHERE source_key = ? ORDER BY ord")
            if (limit >= 0) append(" LIMIT ").append(limit).append(" OFFSET ").append(offset.coerceAtLeast(0))
        }
        return readableDatabase.rawQuery(sql, arrayOf(sourceKey)).use { cursor ->
            val out = ArrayList<IptvChannel>(if (limit in 1..100_000) limit else cursor.count)
            val cols = ColumnIndices(cursor)
            while (cursor.moveToNext()) {
                out.add(readChannel(cursor, cols))
            }
            out
        }
    }

    /**
     * Windowed read filtered to a single [groupTitle] (the category buckets in the UI).
     * Pass a blank/null [groupTitle] for "all channels".
     */
    fun windowForGroup(sourceKey: String, groupTitle: String?, offset: Int, limit: Int): List<IptvChannel> {
        return windowForPlaylistGroup(sourceKey, playlistId = null, groupTitle = groupTitle, offset = offset, limit = limit)
    }

    fun windowForPlaylistGroup(
        sourceKey: String,
        playlistId: String?,
        groupTitle: String?,
        offset: Int,
        limit: Int
    ): List<IptvChannel> {
        if (sourceKey.isBlank()) return emptyList()
        fun query(normalizedGroup: Boolean): List<IptvChannel> {
            val byGroup = !groupTitle.isNullOrEmpty()
            val byPlaylist = !playlistId.isNullOrBlank()
            val sql = buildString {
                append("SELECT * FROM channels WHERE source_key = ?")
                if (byPlaylist) append(" AND id LIKE ?")
                if (byGroup) {
                    if (normalizedGroup) append(" AND trim(group_title) = ?") else append(" AND group_title = ?")
                }
                append(" ORDER BY ord")
                if (limit >= 0) append(" LIMIT ").append(limit).append(" OFFSET ").append(offset.coerceAtLeast(0))
            }
            val args = buildList {
                add(sourceKey)
                if (byPlaylist) add("${playlistId}:%")
                if (byGroup) add(if (normalizedGroup) groupTitle!!.trim() else groupTitle!!)
            }.toTypedArray()
            return readableDatabase.rawQuery(sql, args).use { cursor ->
                val out = ArrayList<IptvChannel>(if (limit in 1..100_000) limit else cursor.count)
                val cols = ColumnIndices(cursor)
                while (cursor.moveToNext()) out.add(readChannel(cursor, cols))
                out
            }
        }
        val exact = query(normalizedGroup = false)
        return if (exact.isNotEmpty() || groupTitle.isNullOrBlank()) exact else query(normalizedGroup = true)
    }

    fun countForGroup(sourceKey: String, groupTitle: String?): Int {
        return countForPlaylistGroup(sourceKey, playlistId = null, groupTitle = groupTitle)
    }

    fun countForPlaylistGroup(sourceKey: String, playlistId: String?, groupTitle: String?): Int {
        if (sourceKey.isBlank()) return 0
        val byGroup = !groupTitle.isNullOrEmpty()
        val byPlaylist = !playlistId.isNullOrBlank()
        if (!byGroup && !byPlaylist) return count(sourceKey)
        val sql = buildString {
            append("SELECT COUNT(*) FROM channels WHERE source_key = ?")
            if (byPlaylist) append(" AND id LIKE ?")
            if (byGroup) append(" AND group_title = ?")
        }
        val args = buildList {
            add(sourceKey)
            if (byPlaylist) add("${playlistId}:%")
            if (byGroup) add(groupTitle!!)
        }.toTypedArray()
        return readableDatabase.rawQuery(sql, args)
            .use { cursor -> if (cursor.moveToFirst()) cursor.getInt(0) else 0 }
    }

    /** 0-based position of [channelId] within [groupTitle] (or all), or -1. Used to anchor the window. */
    fun indexOfId(sourceKey: String, groupTitle: String?, channelId: String): Int {
        if (sourceKey.isBlank() || channelId.isBlank()) return -1
        val byGroup = !groupTitle.isNullOrEmpty()
        val target = readableDatabase.rawQuery(
            "SELECT ord FROM channels WHERE source_key = ? AND id = ? LIMIT 1",
            arrayOf(sourceKey, channelId)
        ).use { c -> if (c.moveToFirst()) c.getLong(0) else return -1 }
        val sql = buildString {
            append("SELECT COUNT(*) FROM channels WHERE source_key = ?")
            if (byGroup) append(" AND group_title = ?")
            append(" AND ord < ?")
        }
        val args = if (byGroup) arrayOf(sourceKey, groupTitle!!, target.toString())
        else arrayOf(sourceKey, target.toString())
        return readableDatabase.rawQuery(sql, args).use { c -> if (c.moveToFirst()) c.getInt(0) else -1 }
    }

    /** Fetch specific channels by id (favorites / recents / focus lookups), in store order. */
    fun getByIds(sourceKey: String, ids: Collection<String>): List<IptvChannel> {
        if (sourceKey.isBlank() || ids.isEmpty()) return emptyList()
        val out = ArrayList<IptvChannel>(ids.size)
        ids.asSequence().filter { it.isNotBlank() }.chunked(MAX_SQL_ARGS - 1).forEach { chunk ->
            val placeholders = chunk.joinToString(",") { "?" }
            readableDatabase.rawQuery(
                "SELECT * FROM channels WHERE source_key = ? AND id IN ($placeholders) ORDER BY ord",
                (listOf(sourceKey) + chunk).toTypedArray()
            ).use { cursor ->
                val cols = ColumnIndices(cursor)
                while (cursor.moveToNext()) out.add(readChannel(cursor, cols))
            }
        }
        return out
    }

    fun search(sourceKey: String, query: String, limit: Int): List<IptvChannel> {
        if (sourceKey.isBlank() || query.isBlank()) return emptyList()
        val like = "%" + query.trim().replace("%", "").replace("_", "") + "%"
        return readableDatabase.rawQuery(
            "SELECT * FROM channels WHERE source_key = ? AND name LIKE ? ORDER BY ord LIMIT ?",
            arrayOf(sourceKey, like, limit.coerceAtLeast(1).toString())
        ).use { cursor ->
            val out = ArrayList<IptvChannel>()
            val cols = ColumnIndices(cursor)
            while (cursor.moveToNext()) out.add(readChannel(cursor, cols))
            out
        }
    }

    /** (group_title, count) for the category sidebar — computed in SQL, no object materialisation. */
    fun groupCounts(sourceKey: String): List<Pair<String, Int>> {
        if (sourceKey.isBlank()) return emptyList()
        return readableDatabase.rawQuery(
            "SELECT group_title, COUNT(*) FROM channels WHERE source_key = ? GROUP BY group_title ORDER BY ord",
            arrayOf(sourceKey)
        ).use { cursor ->
            val out = ArrayList<Pair<String, Int>>(cursor.count)
            while (cursor.moveToNext()) {
                out.add(cursor.getString(0).orEmpty() to cursor.getInt(1))
            }
            out
        }
    }

    fun playlistGroupCounts(sourceKey: String): List<Triple<String, String, Int>> {
        if (sourceKey.isBlank()) return emptyList()
        return readableDatabase.rawQuery(
            """
            SELECT
                CASE WHEN instr(id, ':') > 0 THEN substr(id, 1, instr(id, ':') - 1) ELSE '' END AS playlist_id,
                group_title,
                COUNT(*),
                MIN(ord) AS first_ord
            FROM channels
            WHERE source_key = ?
            GROUP BY playlist_id, group_title
            ORDER BY first_ord
            """.trimIndent(),
            arrayOf(sourceKey)
        ).use { cursor ->
            val out = ArrayList<Triple<String, String, Int>>(cursor.count)
            while (cursor.moveToNext()) {
                out.add(Triple(cursor.getString(0).orEmpty(), cursor.getString(1).orEmpty(), cursor.getInt(2)))
            }
            out
        }
    }

    fun deleteSource(sourceKey: String) {
        if (sourceKey.isBlank()) return
        writableDatabase.runCatching {
            beginTransaction()
            try {
                delete("channels", "source_key = ?", arrayOf(sourceKey))
                delete("channel_sources", "source_key = ?", arrayOf(sourceKey))
                setTransactionSuccessful()
            } finally {
                endTransaction()
            }
        }
    }

    private fun readChannel(cursor: android.database.Cursor, c: ColumnIndices): IptvChannel {
        val headersJson = if (cursor.isNull(c.requestHeaders)) null else cursor.getString(c.requestHeaders)
        val drmJson = if (cursor.isNull(c.drm)) null else cursor.getString(c.drm)
        @Suppress("UNCHECKED_CAST")
        val headers = headersJson?.let {
            runCatching { gson.fromJson(it, Map::class.java) as? Map<String, String> }.getOrNull()
        }.orEmpty()
        val drm = drmJson?.let { runCatching { gson.fromJson(it, DrmInfo::class.java) }.getOrNull() }
        val name = cursor.getString(c.name).orEmpty()
        return IptvChannel(
            id = cursor.getString(c.id).orEmpty(),
            name = name,
            streamUrl = normalizeIptvStreamUrl(cursor.getString(c.streamUrl).orEmpty()),
            group = cursor.getString(c.group).orEmpty(),
            logo = normalizeIptvLogoUrlOrNull(if (cursor.isNull(c.logo)) null else cursor.getString(c.logo)),
            epgId = if (cursor.isNull(c.epgId)) null else cursor.getString(c.epgId),
            rawTitle = if (cursor.isNull(c.rawTitle)) name else cursor.getString(c.rawTitle),
            xtreamStreamId = if (cursor.isNull(c.xtreamStreamId)) null else cursor.getInt(c.xtreamStreamId),
            catchupDays = cursor.getInt(c.catchupDays),
            catchupType = if (cursor.isNull(c.catchupType)) null else cursor.getString(c.catchupType),
            catchupSource = if (cursor.isNull(c.catchupSource)) null else cursor.getString(c.catchupSource),
            tvgName = if (cursor.isNull(c.tvgName)) null else cursor.getString(c.tvgName),
            providerChannelNumber = if (cursor.isNull(c.providerNumber)) null else cursor.getString(c.providerNumber),
            requestHeaders = headers,
            language = if (cursor.isNull(c.language)) null else cursor.getString(c.language),
            country = if (cursor.isNull(c.country)) null else cursor.getString(c.country),
            qualityLabel = if (cursor.isNull(c.quality)) null else cursor.getString(c.quality),
            variantKey = if (cursor.isNull(c.variantKey)) null else cursor.getString(c.variantKey),
            drmInfo = drm,
        )
    }

    private class ColumnIndices(cursor: android.database.Cursor) {
        val id = cursor.getColumnIndexOrThrow("id")
        val name = cursor.getColumnIndexOrThrow("name")
        val streamUrl = cursor.getColumnIndexOrThrow("stream_url")
        val group = cursor.getColumnIndexOrThrow("group_title")
        val logo = cursor.getColumnIndexOrThrow("logo")
        val epgId = cursor.getColumnIndexOrThrow("epg_id")
        val rawTitle = cursor.getColumnIndexOrThrow("raw_title")
        val xtreamStreamId = cursor.getColumnIndexOrThrow("xtream_stream_id")
        val catchupDays = cursor.getColumnIndexOrThrow("catchup_days")
        val catchupType = cursor.getColumnIndexOrThrow("catchup_type")
        val catchupSource = cursor.getColumnIndexOrThrow("catchup_source")
        val tvgName = cursor.getColumnIndexOrThrow("tvg_name")
        val providerNumber = cursor.getColumnIndexOrThrow("provider_channel_number")
        val requestHeaders = cursor.getColumnIndexOrThrow("request_headers_json")
        val language = cursor.getColumnIndexOrThrow("language")
        val country = cursor.getColumnIndexOrThrow("country")
        val quality = cursor.getColumnIndexOrThrow("quality_label")
        val variantKey = cursor.getColumnIndexOrThrow("variant_key")
        val drm = cursor.getColumnIndexOrThrow("drm_json")
    }

    private fun bindNullableString(statement: android.database.sqlite.SQLiteStatement, index: Int, value: String?) {
        if (value == null) statement.bindNull(index) else statement.bindString(index, value)
    }

    private companion object {
        const val DATABASE_NAME = "arvio_iptv_channels.db"
        const val DATABASE_VERSION = 1
        const val MAX_SQL_ARGS = 900
    }
}
