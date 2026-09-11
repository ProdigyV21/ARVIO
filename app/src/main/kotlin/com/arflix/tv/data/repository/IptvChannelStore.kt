package com.arflix.tv.data.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.arflix.tv.data.model.DrmInfo
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.PlaylistGroupKey
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

    init {
        // Refreshes replace a complete provider snapshot. WAL lets the TV page
        // continue reading the previous snapshot while the new one is staged.
        setWriteAheadLoggingEnabled(true)
    }

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
                stalker_direct_stream INTEGER NOT NULL DEFAULT 0,
                PRIMARY KEY(source_key, ord)
            )
            """.trimIndent()
        )
        db.execSQL("CREATE INDEX idx_channels_group ON channels(source_key, group_title, ord)")
        db.execSQL("CREATE INDEX idx_channels_id ON channels(source_key, id)")
        db.execSQL(
            """
            CREATE TABLE channel_sources (
                source_key TEXT PRIMARY KEY NOT NULL,
                updated_ms INTEGER NOT NULL,
                channel_count INTEGER NOT NULL,
                group_summary_json TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion in 3..6) {
            if (oldVersion < 4 && newVersion >= 4) {
                db.execSQL("ALTER TABLE channel_sources ADD COLUMN group_summary_json TEXT")
            }
            if (oldVersion < 5 && newVersion >= 5) {
                db.execSQL("CREATE INDEX IF NOT EXISTS idx_channels_id ON channels(source_key, id)")
            }
            if (oldVersion < 6 && newVersion >= 6) {
                db.execSQL("DROP INDEX IF EXISTS idx_channels_group")
                db.execSQL("CREATE INDEX idx_channels_group ON channels(source_key, group_title, ord)")
            }
            if (oldVersion < 7 && newVersion >= 7) {
                db.execSQL("ALTER TABLE channels ADD COLUMN stalker_direct_stream INTEGER NOT NULL DEFAULT 0")
            }
            return
        }
        if (oldVersion !in 3..6) {
            db.execSQL("DROP TABLE IF EXISTS channels")
            db.execSQL("DROP TABLE IF EXISTS channel_sources")
            onCreate(db)
        }
    }

    /** Replace the whole channel set for [sourceKey] in a single transaction. */
    fun replaceAll(sourceKey: String, channels: List<IptvChannel>, updatedAtMs: Long) {
        if (sourceKey.isBlank()) return
        val groupSummaryJson = encodeGroupSummary(channels)
        val db = writableDatabase
        db.beginTransaction()
        try {
            if (channels.isNotEmpty()) {
                val statements = HashMap<Int, android.database.sqlite.SQLiteStatement>(2)
                try {
                    var offset = 0
                    while (offset < channels.size) {
                        val rowCount = minOf(MAX_CHANNEL_INSERT_ROWS, channels.size - offset)
                        val statement = statements.getOrPut(rowCount) {
                            val values = List(rowCount) {
                                "(" + List(CHANNEL_BINDINGS_PER_ROW) { "?" }.joinToString(",") + ")"
                            }.joinToString(",")
                            db.compileStatement(
                                """
                                INSERT OR REPLACE INTO channels
                                (source_key, ord, id, name, stream_url, group_title, logo, epg_id, raw_title,
                                 xtream_stream_id, catchup_days, catchup_type, catchup_source, tvg_name,
                                 provider_channel_number, request_headers_json, language, country, quality_label,
                                 variant_key, drm_json, stalker_direct_stream)
                                VALUES $values
                                """.trimIndent()
                            )
                        }
                        statement.clearBindings()
                        var bindIndex = 1
                        repeat(rowCount) { rowOffset ->
                            bindIndex = bindChannel(
                                statement = statement,
                                startIndex = bindIndex,
                                sourceKey = sourceKey,
                                order = offset + rowOffset,
                                channel = channels[offset + rowOffset]
                            )
                        }
                        statement.execute()
                        offset += rowCount
                    }
                } finally {
                    statements.values.forEach { it.close() }
                }

                // Each provider position is overwritten above. Delete only rows
                // beyond the new end, rather than first deleting all 50k rows.
                // Apart from being much faster, this leaves the previous snapshot
                // readable through WAL for the complete duration of a refresh.
                db.delete(
                    "channels",
                    "source_key = ? AND ord >= ?",
                    arrayOf(sourceKey, channels.size.toString())
                )
            } else {
                db.delete("channels", "source_key = ?", arrayOf(sourceKey))
            }
            db.compileStatement(
                """
                INSERT OR REPLACE INTO channel_sources
                (source_key, updated_ms, channel_count, group_summary_json)
                VALUES (?,?,?,?)
                """.trimIndent()
            ).use { meta ->
                meta.bindString(1, sourceKey)
                meta.bindLong(2, updatedAtMs)
                meta.bindLong(3, channels.size.toLong())
                meta.bindString(4, groupSummaryJson)
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

    fun loadStartupChannels(sourceKey: String, fullLoadThreshold: Int, previewLimit: Int): List<IptvChannel> =
        if (count(sourceKey) > fullLoadThreshold) window(sourceKey, 0, previewLimit) else loadAll(sourceKey)

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

    /** Sequential metadata scan without decoding stream headers, DRM or channel objects. */
    fun visitLabels(sourceKey: String, playlistId: String?, visitor: (String, String, String) -> Unit) {
        if (sourceKey.isBlank()) return
        val scoped = !playlistId.isNullOrBlank()
        val sql = "SELECT id,name,group_title FROM channels WHERE source_key = ?" +
            (if (scoped) " AND (id LIKE ? OR id LIKE ?)" else "") + " ORDER BY ord"
        val args = if (scoped) arrayOf(sourceKey, "$playlistId:%", "stalker:$playlistId:%") else arrayOf(sourceKey)
        readableDatabase.rawQuery(sql, args).use { cursor ->
            while (cursor.moveToNext()) visitor(cursor.getString(0), cursor.getString(1), cursor.getString(2))
        }
    }

    fun windowForPlaylistGroup(
        sourceKey: String,
        playlistId: String?,
        groupTitle: String?,
        offset: Int,
        limit: Int,
        excludedGroups: Set<String> = emptySet(),
    ): List<IptvChannel> {
        if (sourceKey.isBlank()) return emptyList()
        fun query(normalizedGroup: Boolean): List<IptvChannel> {
            val byGroup = !groupTitle.isNullOrEmpty()
            val byPlaylist = !playlistId.isNullOrBlank()
            val sql = buildString {
                append("SELECT * FROM channels")
                if (byGroup && !normalizedGroup) append(" INDEXED BY idx_channels_group")
                append(" WHERE source_key = ?")
                if (byPlaylist) append(" AND (id LIKE ? OR id LIKE ?)")
                if (byGroup) {
                    if (normalizedGroup) append(" AND trim(group_title) = ?") else append(" AND group_title = ?")
                }
                excludedGroups.forEach { _ ->
                    append(" AND NOT (trim(group_title) = ? AND (id LIKE ? OR id LIKE ?))")
                }
                append(" ORDER BY ord")
                if (limit >= 0) append(" LIMIT ").append(limit).append(" OFFSET ").append(offset.coerceAtLeast(0))
            }
            val args = buildList {
                add(sourceKey)
                if (byPlaylist) {
                    add("${playlistId}:%")
                    add("stalker:${playlistId}:%")
                }
                if (byGroup) add(if (normalizedGroup) groupTitle!!.trim() else groupTitle!!)
                excludedGroups.forEach { rawKey ->
                    val key = PlaylistGroupKey(rawKey)
                    add(key.groupName.trim())
                    add("${key.playlistId}:%")
                    add("stalker:${key.playlistId}:%")
                }
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
            if (byPlaylist) append(" AND (id LIKE ? OR id LIKE ?)")
            if (byGroup) append(" AND group_title = ?")
        }
        val args = buildList {
            add(sourceKey)
            if (byPlaylist) {
                add("${playlistId}:%")
                add("stalker:${playlistId}:%")
            }
            if (byGroup) add(groupTitle!!)
        }.toTypedArray()
        return readableDatabase.rawQuery(sql, args)
            .use { cursor -> if (cursor.moveToFirst()) cursor.getInt(0) else 0 }
    }

    /** 0-based position of [channelId] within [groupTitle] (or all), or -1. Used to anchor the window. */
    fun indexOfId(sourceKey: String, groupTitle: String?, channelId: String): Int {
        return indexOfId(sourceKey, playlistId = null, groupTitle = groupTitle, channelId = channelId)
    }

    /** 0-based provider position within one playlist/group, or -1 when the channel is unavailable. */
    fun indexOfId(sourceKey: String, playlistId: String?, groupTitle: String?, channelId: String): Int {
        if (sourceKey.isBlank() || channelId.isBlank()) return -1
        val byGroup = !groupTitle.isNullOrEmpty()
        val normalizedPlaylistId = playlistId?.trim().orEmpty()
        val byPlaylist = normalizedPlaylistId.isNotEmpty()
        val targetSql = buildString {
            append("SELECT ord FROM channels WHERE source_key = ? AND id = ?")
            if (byPlaylist) append(" AND (id LIKE ? OR id LIKE ?)")
            if (byGroup) append(" AND group_title = ?")
            append(" LIMIT 1")
        }
        val targetArgs = buildList {
            add(sourceKey)
            add(channelId)
            if (byPlaylist) {
                add("$normalizedPlaylistId:%")
                add("stalker:$normalizedPlaylistId:%")
            }
            if (byGroup) add(groupTitle!!)
        }.toTypedArray()
        val target = readableDatabase.rawQuery(
            targetSql,
            targetArgs
        ).use { c -> if (c.moveToFirst()) c.getLong(0) else return -1 }
        val sql = buildString {
            append("SELECT COUNT(*) FROM channels WHERE source_key = ?")
            if (byPlaylist) append(" AND (id LIKE ? OR id LIKE ?)")
            if (byGroup) append(" AND group_title = ?")
            append(" AND ord < ?")
        }
        val args = buildList {
            add(sourceKey)
            if (byPlaylist) {
                add("$normalizedPlaylistId:%")
                add("stalker:$normalizedPlaylistId:%")
            }
            if (byGroup) add(groupTitle!!)
            add(target.toString())
        }.toTypedArray()
        return readableDatabase.rawQuery(sql, args).use { c -> if (c.moveToFirst()) c.getInt(0) else -1 }
    }

    /** Fetch specific channels by id (favorites / recents / focus lookups), in store order. */
    fun getByIds(sourceKey: String, ids: Collection<String>): List<IptvChannel> {
        if (sourceKey.isBlank() || ids.isEmpty()) return emptyList()
        val indexed = ArrayList<Pair<Int, IptvChannel>>(ids.size)
        ids.asSequence().filter { it.isNotBlank() }.chunked(MAX_SQL_ARGS - 1).forEach { chunk ->
            val placeholders = chunk.joinToString(",") { "?" }
            readableDatabase.rawQuery(
                // ORDER BY ord made SQLite prefer the (source_key, ord) primary-key
                // index and scan a complete 50k playlist even for one favorite.
                // Force the exact-id index and restore provider order over the tiny
                // result set in memory instead.
                "SELECT * FROM channels INDEXED BY idx_channels_id " +
                    "WHERE source_key = ? AND id IN ($placeholders)",
                (listOf(sourceKey) + chunk).toTypedArray()
            ).use { cursor ->
                val cols = ColumnIndices(cursor)
                val orderColumn = cursor.getColumnIndexOrThrow("ord")
                while (cursor.moveToNext()) {
                    indexed.add(cursor.getInt(orderColumn) to readChannel(cursor, cols))
                }
            }
        }
        indexed.sortBy { it.first }
        return indexed.map { it.second }
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
            "SELECT group_title, COUNT(*), MIN(ord) AS first_ord FROM channels WHERE source_key = ? GROUP BY group_title ORDER BY first_ord",
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
        val stored = readableDatabase.rawQuery(
            "SELECT group_summary_json FROM channel_sources WHERE source_key = ? LIMIT 1",
            arrayOf(sourceKey)
        ).use { cursor ->
            if (cursor.moveToFirst() && !cursor.isNull(0)) cursor.getString(0).orEmpty() else ""
        }
        if (stored.isNotBlank()) {
            val decoded = runCatching {
                gson.fromJson(stored, Array<StoredGroupSummary>::class.java)
                    .orEmpty()
                    .asSequence()
                    .filter { it.groupTitle.isNotBlank() && it.count > 0 }
                    .map { Triple(it.playlistId, it.groupTitle, it.count) }
                    .toList()
            }.getOrDefault(emptyList())
            if (decoded.isNotEmpty()) return decoded
        }

        val computed = readableDatabase.rawQuery(
            """
            SELECT
                CASE
                    WHEN id LIKE 'stalker:%:%' THEN
                        substr(
                            substr(id, instr(id, ':') + 1),
                            1,
                            instr(substr(id, instr(id, ':') + 1), ':') - 1
                        )
                    WHEN instr(id, ':') > 0 THEN substr(id, 1, instr(id, ':') - 1)
                    ELSE ''
                END AS playlist_id,
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
        if (computed.isNotEmpty()) {
            val json = gson.toJson(
                computed.map { (playlistId, groupTitle, count) ->
                    StoredGroupSummary(playlistId, groupTitle, count)
                }
            )
            runCatching {
                writableDatabase.compileStatement(
                    "UPDATE channel_sources SET group_summary_json = ? WHERE source_key = ?"
                ).use { statement ->
                    statement.bindString(1, json)
                    statement.bindString(2, sourceKey)
                    statement.executeUpdateDelete()
                }
            }
        }
        return computed
    }

    fun deleteSource(sourceKey: String) {
        if (sourceKey.isBlank()) return
        try {
            writableDatabase.beginTransaction()
            try {
                writableDatabase.delete("channels", "source_key = ?", arrayOf(sourceKey))
                writableDatabase.delete("channel_sources", "source_key = ?", arrayOf(sourceKey))
                writableDatabase.setTransactionSuccessful()
            } finally {
                writableDatabase.endTransaction()
            }
        } catch (e: Exception) {
            // Ignore DB errors on delete
        }
    }

    private fun readChannel(cursor: android.database.Cursor, c: ColumnIndices): IptvChannel {
        val headersJson = if (cursor.isNull(c.requestHeaders)) null else cursor.getString(c.requestHeaders)
        val drmJson = if (cursor.isNull(c.drm)) null else cursor.getString(c.drm)
        val headers = headersJson?.let {
            try {
                val jsonElement = com.google.gson.JsonParser.parseString(it)
                if (jsonElement.isJsonObject) {
                    jsonElement.asJsonObject.entrySet().mapNotNull { entry ->
                        val value = entry.value
                        if (value != null && value.isJsonPrimitive && value.asJsonPrimitive.isString) {
                            entry.key to value.asString
                        } else null
                    }.toMap()
                } else null
            } catch (e: Exception) { null }
        }.orEmpty()
        val drm = drmJson?.let { try { gson.fromJson(it, DrmInfo::class.java) } catch (e: Exception) { null } }
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
            stalkerDirectStream = cursor.getInt(c.stalkerDirectStream) != 0,
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
        val stalkerDirectStream = cursor.getColumnIndexOrThrow("stalker_direct_stream")
    }

    private data class StoredGroupSummary(
        val playlistId: String = "",
        val groupTitle: String = "",
        val count: Int = 0
    )

    private fun encodeGroupSummary(channels: List<IptvChannel>): String {
        if (channels.isEmpty()) return "[]"
        val summaries = LinkedHashMap<String, StoredGroupSummary>()
        channels.forEach { channel ->
            val playlistId = playlistIdFromChannelId(channel.id)
            val groupTitle = channel.group.trim().ifBlank { "Uncategorized" }
            val key = "$playlistId\u0000$groupTitle"
            val current = summaries[key]
            summaries[key] = if (current == null) {
                StoredGroupSummary(playlistId, groupTitle, 1)
            } else {
                current.copy(count = current.count + 1)
            }
        }
        return gson.toJson(summaries.values)
    }

    private fun playlistIdFromChannelId(channelId: String): String {
        val normalized = channelId.trim()
        if (normalized.startsWith("stalker:")) {
            return normalized.removePrefix("stalker:").substringBefore(':')
        }
        return normalized.substringBefore(':', missingDelimiterValue = "")
    }

    private fun bindChannel(
        statement: android.database.sqlite.SQLiteStatement,
        startIndex: Int,
        sourceKey: String,
        order: Int,
        channel: IptvChannel
    ): Int {
        var index = startIndex
        statement.bindString(index++, sourceKey)
        statement.bindLong(index++, order.toLong())
        statement.bindString(index++, channel.id)
        statement.bindString(index++, channel.name)
        statement.bindString(index++, channel.streamUrl)
        statement.bindString(index++, channel.group)
        bindNullableString(statement, index++, channel.logo)
        bindNullableString(statement, index++, channel.epgId)
        bindNullableString(statement, index++, channel.rawTitle)
        if (channel.xtreamStreamId != null) {
            statement.bindLong(index++, channel.xtreamStreamId.toLong())
        } else {
            statement.bindNull(index++)
        }
        statement.bindLong(index++, channel.catchupDays.toLong())
        bindNullableString(statement, index++, channel.catchupType)
        bindNullableString(statement, index++, channel.catchupSource)
        bindNullableString(statement, index++, channel.tvgName)
        bindNullableString(statement, index++, channel.providerChannelNumber)
        bindNullableString(
            statement,
            index++,
            channel.requestHeaders.takeIf { it.isNotEmpty() }?.let { gson.toJson(it) }
        )
        bindNullableString(statement, index++, channel.language)
        bindNullableString(statement, index++, channel.country)
        bindNullableString(statement, index++, channel.qualityLabel)
        bindNullableString(statement, index++, channel.variantKey)
        bindNullableString(statement, index++, channel.drmInfo?.let { gson.toJson(it) })
        statement.bindLong(index++, if (channel.stalkerDirectStream) 1L else 0L)
        return index
    }

    private fun bindNullableString(statement: android.database.sqlite.SQLiteStatement, index: Int, value: String?) {
        if (value == null) statement.bindNull(index) else statement.bindString(index, value)
    }

    private companion object {
        const val DATABASE_NAME = "arvio_iptv_channels.db"
        // v3 rebuilds snapshots created before provider-order imports became canonical.
        // v4 stores the provider/category summary next to the snapshot.
        // v5 indexes channel ids so focus/EPG actions never scan a 50k-row table.
        // v6 keeps provider order in the group index for instant deep-category reads.
        // v7 preserves the portal's direct-live decision; old snapshots still resolve links.
        const val DATABASE_VERSION = 7
        const val MAX_SQL_ARGS = 900
        const val CHANNEL_BINDINGS_PER_ROW = 22
        const val MAX_CHANNEL_INSERT_ROWS = MAX_SQL_ARGS / CHANNEL_BINDINGS_PER_ROW
    }
}
