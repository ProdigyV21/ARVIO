package com.arflix.tv.data.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvGuideHistory

/**
 * Local guide index used by the Live TV page.
 *
 * The JSON IPTV snapshot is intentionally capped so it cannot grow without
 * bound on 50k-channel lists. This SQLite index stores parsed program rows
 * separately, allowing the UI to query only the visible channels instantly.
 */
internal class IptvEpgIndex(context: Context, databaseName: String = DATABASE_NAME) : SQLiteOpenHelper(
    context,
    databaseName,
    null,
    DATABASE_VERSION
) {
    init {
        // Keep the last complete guide readable while a refreshed guide is staged.
        // Without WAL, a 50k-channel import monopolises SQLite's only connection
        // and every visible guide query waits behind the writer.
        setWriteAheadLoggingEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE epg_programs (
                source_key TEXT NOT NULL,
                channel_id TEXT NOT NULL,
                start_ms INTEGER NOT NULL,
                end_ms INTEGER NOT NULL,
                title TEXT NOT NULL,
                description TEXT,
                catchup_available INTEGER,
                artwork_url TEXT,
                category TEXT,
                PRIMARY KEY(source_key, channel_id, start_ms, end_ms, title)
            )
            """.trimIndent()
        )
        db.execSQL(
            """
            CREATE TABLE epg_sources (
                source_key TEXT PRIMARY KEY NOT NULL,
                updated_ms INTEGER NOT NULL,
                full_updated_ms INTEGER NOT NULL DEFAULT 0,
                channel_count INTEGER NOT NULL DEFAULT -1,
                program_count INTEGER NOT NULL DEFAULT -1
            )
            """.trimIndent()
        )
        createAliasTable(db)
    }

    private fun createAliasTable(db: SQLiteDatabase) {
        db.execSQL("""CREATE TABLE IF NOT EXISTS epg_channel_aliases (
            source_key TEXT NOT NULL, channel_id TEXT NOT NULL, guide_id TEXT NOT NULL,
            updated_ms INTEGER NOT NULL, PRIMARY KEY(source_key, channel_id, guide_id))""")
    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        // The composite guide key is several columns wide. A modest page cache
        // prevents a 50k-channel refresh from repeatedly paging the B-tree from
        // disk, without consuming enough RAM to compete with the TV UI.
        db.execSQL("PRAGMA cache_size=-16384")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion in 2..7 && newVersion >= 8) {
            db.execSQL("ALTER TABLE epg_programs ADD COLUMN artwork_url TEXT")
            db.execSQL("ALTER TABLE epg_programs ADD COLUMN category TEXT")
            if (oldVersion == 7) return
        }
        if (oldVersion in 2..6 && newVersion >= 7) {
            // Nullable preserves "unknown" for XMLTV and already indexed programmes.
            db.execSQL("ALTER TABLE epg_programs ADD COLUMN catchup_available INTEGER")
            if (oldVersion == 6) return
        }
        if (oldVersion in 2..5 && newVersion >= 6) {
            db.execSQL("ALTER TABLE epg_sources ADD COLUMN full_updated_ms INTEGER NOT NULL DEFAULT 0")
            if (oldVersion == 5) return
        }
        if (oldVersion in 2..4 && newVersion >= 5) {
            createAliasTable(db)
            if (oldVersion >= 3) return
        }
        if (oldVersion == 2 && newVersion >= 3) {
            // Preserve the user's already parsed guide. Counts are intentionally
            // unknown until the next normal refresh; startup must never scan the
            // complete programme table merely to decide whether to refresh it.
            db.execSQL("ALTER TABLE epg_sources ADD COLUMN channel_count INTEGER NOT NULL DEFAULT -1")
            db.execSQL("ALTER TABLE epg_sources ADD COLUMN program_count INTEGER NOT NULL DEFAULT -1")
        }
        if (oldVersion in 2..3 && newVersion >= 4) {
            // Existing guides keep their redundant index until the next background
            // refresh. Dropping a large index here would block the first TV-page
            // query after an app update for several seconds.
            return
        }
        if (oldVersion == 2 && newVersion == 3) return
        db.execSQL("DROP TABLE IF EXISTS epg_programs")
        db.execSQL("DROP TABLE IF EXISTS epg_sources")
        onCreate(db)
    }

    fun replaceAll(
        sourceKey: String,
        nowNext: Map<String, IptvNowNext>,
        updatedAtMs: Long,
        shouldAbort: () -> Boolean = { false }
    ) {
        if (sourceKey.isBlank() || nowNext.isEmpty()) return
        abortIfRequested(shouldAbort)

        val db = writableDatabase
        // The primary-key auto-index already has the same source/channel/start
        // prefix used by guide-window queries. Remove the legacy duplicate on this
        // background refresh instead of delaying the first visible guide query.
        db.execSQL("DROP INDEX IF EXISTS idx_epg_programs_window")
        db.runInTransaction {
            abortIfRequested(shouldAbort)
            // A full feed can contain a shorter rolling window than a channel's archive.
            // Keep previously indexed programmes unless expired or explicitly corrected.
            deleteMatchingProgramStarts(sourceKey, nowNext, shouldAbort)
            insertNowNextRows(sourceKey, nowNext, shouldAbort)
            delete("epg_programs", "source_key = ? AND (end_ms <= ? OR start_ms >= ?)", arrayOf(
                sourceKey,
                (updatedAtMs - IptvGuideHistory.MAX_WINDOW_MS).toString(),
                (updatedAtMs + IptvGuideHistory.MAX_WINDOW_MS).toString(),
            ))
            abortIfRequested(shouldAbort)
            // Recompute during the background write, never on the startup read path.
            updateSourceCounts(sourceKey, updatedAtMs)
            abortIfRequested(shouldAbort)
        }
        if (!shouldAbort()) {
            db.checkpointWalAfterBulkWrite()
        }
    }

    fun replaceChannels(
        sourceKey: String, nowNext: Map<String, IptvNowNext>, updatedAtMs: Long,
        aliases: Map<String, List<String>> = emptyMap(),
    ) {
        if (sourceKey.isBlank() || nowNext.isEmpty()) return

        writableDatabase.runInTransaction {
            if (aliases.isNotEmpty()) {
                compileStatement("INSERT OR REPLACE INTO epg_channel_aliases (source_key,channel_id,guide_id,updated_ms) VALUES (?,?,?,?)").use { stmt ->
                    aliases.forEach { (guideId, channelIds) -> channelIds.forEach { channelId ->
                        stmt.bindString(1, sourceKey)
                        stmt.bindString(2, channelId)
                        stmt.bindString(3, guideId)
                        stmt.bindLong(4, updatedAtMs)
                        stmt.execute()
                    } }
                }
            }
            // A short guide response is a patch, not a complete channel schedule.
            // Replace matching start times for corrections, retaining the rest of the archive.
            deleteMatchingProgramStarts(sourceKey, nowNext)
            insertNowNextRows(sourceKey, nowNext)
            nowNext.keys
                .asSequence()
                .filter { it.isNotBlank() }
                .chunked(MAX_SQL_ARGS - 3)
                .forEach { channelIds ->
                    val placeholders = channelIds.joinToString(",") { "?" }
                    val args = arrayOf(sourceKey) + channelIds.map { it.trim() }.toTypedArray() + arrayOf(
                        (updatedAtMs - IptvGuideHistory.MAX_WINDOW_MS).toString(),
                        (updatedAtMs + IptvGuideHistory.MAX_WINDOW_MS).toString())
                    delete("epg_programs", "source_key = ? AND channel_id IN ($placeholders) AND (end_ms <= ? OR start_ms >= ?)", args)
                }
            touchSource(sourceKey, updatedAtMs)
        }
    }

    fun finishStreamingRefresh(sourceKey: String, updatedAtMs: Long) {
        writableDatabase.runInTransaction {
            delete("epg_programs", "source_key = ? AND (end_ms <= ? OR start_ms >= ?)", arrayOf(
                sourceKey, (updatedAtMs - IptvGuideHistory.MAX_WINDOW_MS).toString(),
                (updatedAtMs + IptvGuideHistory.MAX_WINDOW_MS).toString(),
            ))
            delete("epg_channel_aliases", "source_key = ? AND NOT EXISTS (SELECT 1 FROM epg_programs p WHERE p.source_key = epg_channel_aliases.source_key AND p.channel_id = epg_channel_aliases.guide_id)",
                arrayOf(sourceKey))
            updateSourceCounts(sourceKey, updatedAtMs)
        }
    }

    private fun SQLiteDatabase.updateSourceCounts(sourceKey: String, updatedAtMs: Long) {
        val channelCount = rawQuery("""SELECT COUNT(*) FROM (
            SELECT channel_id FROM epg_programs WHERE source_key = ? AND channel_id NOT LIKE '@xml:%'
            UNION SELECT a.channel_id FROM epg_channel_aliases a WHERE a.source_key = ?
                AND EXISTS(SELECT 1 FROM epg_programs p WHERE p.source_key = a.source_key AND p.channel_id = a.guide_id)
        )""", arrayOf(sourceKey, sourceKey)).use { it.moveToFirst(); it.getInt(0) }
        val programCount = rawQuery("SELECT COUNT(*) FROM epg_programs WHERE source_key = ?", arrayOf(sourceKey))
            .use { it.moveToFirst(); it.getInt(0) }
        upsertSource(sourceKey, updatedAtMs, channelCount, programCount)
    }

    /** Channel identities with cached programmes, including XMLTV alias targets. No network. */
    fun channelIdsInWindow(sourceKey: String, startMs: Long, endMs: Long): Set<String> {
        if (sourceKey.isBlank() || startMs >= endMs) return emptySet()
        // Resolve the eligible guide identities once. Correlated EXISTS caused an
        // expensive repeated scan for every alias on large provider indexes.
        return readableDatabase.rawQuery("""
            WITH eligible AS (
                SELECT DISTINCT channel_id FROM epg_programs
                WHERE source_key = ? AND end_ms > ? AND start_ms < ?
            )
            SELECT channel_id FROM eligible WHERE channel_id NOT LIKE '@xml:%'
            UNION SELECT a.channel_id FROM epg_channel_aliases a
                JOIN eligible p ON p.channel_id = a.guide_id WHERE a.source_key = ?
        """, arrayOf(sourceKey, startMs.toString(), endMs.toString(), sourceKey)).use { cursor ->
            buildSet { while (cursor.moveToNext()) add(cursor.getString(0)) }
        }
    }

    fun loadNowNext(
        sourceKey: String,
        channelIds: Set<String>,
        nowMs: Long = System.currentTimeMillis(),
        pastWindowMs: Long = DEFAULT_PAST_WINDOW_MS,
        futureWindowMs: Long = DEFAULT_FUTURE_WINDOW_MS,
        recentProgramLimit: Int = MAX_RECENT_PROGRAMS,
    ): Map<String, IptvNowNext> {
        if (sourceKey.isBlank() || channelIds.isEmpty()) return emptyMap()
        val startBound = nowMs - pastWindowMs
        val endBound = nowMs + futureWindowMs
        val grouped = LinkedHashMap<String, MutableList<IptvProgram>>()

        readableDatabase.useQueryChunks(
            sourceKey = sourceKey,
            channelIds = channelIds,
            startBound = startBound,
            endBound = endBound
        ) { channelId, program ->
            grouped.getOrPut(channelId) { mutableListOf() }.add(program)
        }

        if (grouped.isEmpty()) return emptyMap()
        return buildMap {
            grouped.forEach { (channelId, programs) ->
                buildNowNext(programs, nowMs, recentProgramLimit)?.let { put(channelId, it) }
            }
        }
    }

    private fun SQLiteDatabase.deleteMatchingProgramStarts(
        sourceKey: String,
        nowNext: Map<String, IptvNowNext>,
        shouldAbort: () -> Boolean = { false },
    ) {
        nowNext.entries.sortedBy { it.key }.forEach { (channelId, guide) ->
            abortIfRequested(shouldAbort)
            (guide.recent.asSequence() + listOfNotNull(guide.now, guide.next, guide.later).asSequence() + guide.upcoming.asSequence())
                .filter { it.title.isNotBlank() && it.endUtcMillis > it.startUtcMillis }
                .map { it.startUtcMillis }.distinct().chunked(MAX_SQL_ARGS - 2)
                .forEach { starts ->
                    abortIfRequested(shouldAbort)
                    val placeholders = starts.joinToString(",") { "?" }
                    delete("epg_programs", "source_key = ? AND channel_id = ? AND start_ms IN ($placeholders)",
                        (listOf(sourceKey, channelId.trim()) + starts.map { it.toString() }).toTypedArray())
                }
        }
    }

    /**
     * Load the full list of programmes that overlap [startMs, endMs) for the given
     * channels, sorted by start time and de-duplicated.
     *
     * Unlike [loadNowNext] this does NOT compact the result into a now/next slice —
     * the Live TV guide grid needs every programme in the visible window (e.g. the
     * full past-48h / future-48h span) so it can lay out cells across the timeline.
     */
    fun loadWindow(
        sourceKey: String,
        channelIds: Set<String>,
        startMs: Long,
        endMs: Long
    ): Map<String, List<IptvProgram>> {
        if (sourceKey.isBlank() || channelIds.isEmpty() || endMs <= startMs) return emptyMap()
        val grouped = LinkedHashMap<String, MutableList<IptvProgram>>()

        readableDatabase.useQueryChunks(
            sourceKey = sourceKey,
            channelIds = channelIds,
            startBound = startMs,
            endBound = endMs
        ) { channelId, program ->
            grouped.getOrPut(channelId) { mutableListOf() }.add(program)
        }

        if (grouped.isEmpty()) return emptyMap()
        return buildMap {
            grouped.forEach { (channelId, programs) ->
                val sorted = programs
                    .asSequence()
                    .filter { it.endUtcMillis > it.startUtcMillis }
                    .distinctBy { "${it.startUtcMillis}|${it.endUtcMillis}|${it.title}" }
                    .sortedBy { it.startUtcMillis }
                    .toList()
                if (sorted.isNotEmpty()) put(channelId, sorted)
            }
        }
    }

    fun countChannelsWithPrograms(sourceKey: String): Int {
        if (sourceKey.isBlank()) return 0
        return sourceStat(sourceKey, "channel_count")
    }

    fun countPrograms(sourceKey: String): Int {
        if (sourceKey.isBlank()) return 0
        return sourceStat(sourceKey, "program_count")
    }

    fun markFullRefreshComplete(sourceKey: String, updatedAtMs: Long) {
        writableDatabase.compileStatement("UPDATE epg_sources SET full_updated_ms = ? WHERE source_key = ?").use {
            it.bindLong(1, updatedAtMs)
            it.bindString(2, sourceKey)
            it.executeUpdateDelete()
        }
    }

    /** Streaming consumers can discard non-sports programmes without materializing a full guide. */
    fun visitWindow(sourceKey: String, channelIds: Set<String>, startMs: Long, endMs: Long,
        visitor: (String, IptvProgram) -> Unit) {
        if (sourceKey.isBlank() || channelIds.isEmpty() || startMs >= endMs) return
        readableDatabase.useQueryChunks(sourceKey, channelIds, startMs, endMs, visitor)
    }

    fun fullRefreshAtMs(sourceKey: String): Long = readableDatabase.rawQuery(
        "SELECT full_updated_ms FROM epg_sources WHERE source_key = ?", arrayOf(sourceKey),
    ).use { if (it.moveToFirst()) it.getLong(0) else 0L }

    private fun sourceStat(sourceKey: String, column: String): Int {
        val value = readableDatabase.rawQuery(
            "SELECT $column FROM epg_sources WHERE source_key = ? LIMIT 1",
            arrayOf(sourceKey)
        ).use { cursor ->
            if (cursor.moveToFirst()) cursor.getInt(0) else -1
        }
        // A migrated v2 index uses -1 until its next refresh. Returning quickly
        // is more important than a synchronous full-table COUNT on TV startup.
        return value.coerceAtLeast(0)
    }

    fun deleteSource(sourceKey: String) {
        if (sourceKey.isBlank()) return
        writableDatabase.runInTransaction {
            delete("epg_programs", "source_key = ?", arrayOf(sourceKey))
            delete("epg_channel_aliases", "source_key = ?", arrayOf(sourceKey))
            delete("epg_sources", "source_key = ?", arrayOf(sourceKey))
        }
    }

    private fun SQLiteDatabase.useQueryChunks(
        sourceKey: String,
        channelIds: Set<String>,
        startBound: Long,
        endBound: Long,
        onProgram: (String, IptvProgram) -> Unit
    ) {
        val aliases = LinkedHashMap<String, MutableList<String>>()
        channelIds.toList().chunked(MAX_SQL_ARGS - 1).forEach { ids ->
            if (ids.isEmpty()) return@forEach
            val placeholders = ids.joinToString(",") { "?" }
            rawQuery("SELECT channel_id,guide_id FROM epg_channel_aliases WHERE source_key = ? AND channel_id IN ($placeholders)",
                (listOf(sourceKey) + ids).toTypedArray()).use { cursor ->
                while (cursor.moveToNext()) aliases.getOrPut(cursor.getString(1)) { ArrayList() }.add(cursor.getString(0))
            }
        }
        if (aliases.isEmpty()) {
            useRawQueryChunks(sourceKey, channelIds, startBound, endBound, onProgram)
            return
        }
        val seenStarts = HashMap<String, MutableSet<Long>>()
        useRawQueryChunks(sourceKey, channelIds, startBound, endBound) { id, program ->
            seenStarts.getOrPut(id) { HashSet() }.add(program.startUtcMillis)
            onProgram(id, program)
        }
        useRawQueryChunks(sourceKey, aliases.keys, startBound, endBound) { id, program ->
            aliases[id].orEmpty().forEach { target ->
                // A provider's channel-specific API correction wins over the shared feed.
                if (seenStarts.getOrPut(target) { HashSet() }.add(program.startUtcMillis)) onProgram(target, program)
            }
        }
    }

    private fun SQLiteDatabase.useRawQueryChunks(
        sourceKey: String,
        channelIds: Set<String>,
        startBound: Long,
        endBound: Long,
        onProgram: (String, IptvProgram) -> Unit
    ) {
        channelIds
            .asSequence()
            .filter { it.isNotBlank() }
            .chunked(MAX_SQL_ARGS - 3)
            .forEach { chunk ->
                val placeholders = chunk.joinToString(",") { "?" }
                val sql = """
                    SELECT channel_id, start_ms, end_ms, title, description, catchup_available, artwork_url, category
                    FROM epg_programs
                    WHERE source_key = ?
                      AND channel_id IN ($placeholders)
                      AND end_ms > ?
                      AND start_ms < ?
                    ORDER BY channel_id, start_ms
                """.trimIndent()
                val args = buildList {
                    add(sourceKey)
                    addAll(chunk)
                    add(startBound.toString())
                    add(endBound.toString())
                }.toTypedArray()

                rawQuery(sql, args).use { cursor ->
                    val channelCol = cursor.getColumnIndexOrThrow("channel_id")
                    val startCol = cursor.getColumnIndexOrThrow("start_ms")
                    val endCol = cursor.getColumnIndexOrThrow("end_ms")
                    val titleCol = cursor.getColumnIndexOrThrow("title")
                    val descCol = cursor.getColumnIndexOrThrow("description")
                    val catchupCol = cursor.getColumnIndexOrThrow("catchup_available")
                    val artworkCol = cursor.getColumnIndexOrThrow("artwork_url")
                    val categoryCol = cursor.getColumnIndexOrThrow("category")
                    while (cursor.moveToNext()) {
                        val channelId = cursor.getString(channelCol).orEmpty()
                        val startMs = cursor.getLong(startCol)
                        val endMs = cursor.getLong(endCol)
                        val title = cursor.getString(titleCol).orEmpty()
                        if (channelId.isBlank() || title.isBlank() || endMs <= startMs) continue
                        val description = if (cursor.isNull(descCol)) null else cursor.getString(descCol)
                        onProgram(
                            channelId,
                            IptvProgram(
                                title = title,
                                description = description?.takeIf { it.isNotBlank() },
                                startUtcMillis = startMs,
                                endUtcMillis = endMs,
                                catchupAvailable = if (cursor.isNull(catchupCol)) null else cursor.getInt(catchupCol) != 0,
                                artworkUrl = cursor.getString(artworkCol),
                                category = cursor.getString(categoryCol),
                            )
                        )
                    }
                }
            }
    }

    private class ProgramDedupKey(val start: Long, val end: Long, val title: String) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ProgramDedupKey) return false
            return start == other.start && end == other.end && title == other.title
        }
        override fun hashCode(): Int {
            var result = start.hashCode()
            result = 31 * result + end.hashCode()
            result = 31 * result + title.hashCode()
            return result
        }
    }

    private data class PendingProgramRow(
        val channelId: String,
        val startMs: Long,
        val endMs: Long,
        val title: String,
        val description: String?,
        val catchupAvailable: Boolean?,
        val artworkUrl: String?,
        val category: String?,
    )

    private fun SQLiteDatabase.insertNowNextRows(
        sourceKey: String,
        nowNext: Map<String, IptvNowNext>,
        shouldAbort: () -> Boolean = { false }
    ) {
        val pending = ArrayList<PendingProgramRow>(MAX_INSERT_ROWS)
        val statements = HashMap<Int, android.database.sqlite.SQLiteStatement>(2)

        fun flushPending() {
            if (pending.isEmpty()) return
            abortIfRequested(shouldAbort)
            val rowCount = pending.size
            val statement = statements.getOrPut(rowCount) {
                val values = List(rowCount) { "(?, ?, ?, ?, ?, ?, ?, ?, ?)" }.joinToString(",")
                compileStatement(
                    """
                    INSERT OR IGNORE INTO epg_programs
                    (source_key, channel_id, start_ms, end_ms, title, description, catchup_available, artwork_url, category)
                    VALUES $values
                    """.trimIndent()
                )
            }
            statement.clearBindings()
            var bindIndex = 1
            pending.forEach { row ->
                statement.bindString(bindIndex++, sourceKey)
                statement.bindString(bindIndex++, row.channelId)
                statement.bindLong(bindIndex++, row.startMs)
                statement.bindLong(bindIndex++, row.endMs)
                statement.bindString(bindIndex++, row.title)
                if (row.description.isNullOrBlank()) {
                    statement.bindNull(bindIndex++)
                } else {
                    statement.bindString(bindIndex++, row.description)
                }
                when (row.catchupAvailable) {
                    true -> statement.bindLong(bindIndex++, 1L)
                    false -> statement.bindLong(bindIndex++, 0L)
                    null -> statement.bindNull(bindIndex++)
                }
                row.artworkUrl?.let { statement.bindString(bindIndex++, it) } ?: statement.bindNull(bindIndex++)
                row.category?.let { statement.bindString(bindIndex++, it) } ?: statement.bindNull(bindIndex++)
            }
            // execute() avoids retrieving a row id for a result that is never used.
            statement.execute()
            pending.clear()
        }

        try {
            val seenPrograms = HashSet<ProgramDedupKey>(128)
            // The primary key starts with source_key/channel_id. ConcurrentHashMap
            // iteration is effectively random and turned a 150k-row refresh into
            // thousands of random B-tree page reads on low-memory TVs. Inserting in
            // key order keeps the write sequential and is dramatically cheaper.
            nowNext.entries.sortedBy { it.key }.forEach { (channelId, item) ->
                val normalizedId = channelId.trim()
                if (normalizedId.isBlank()) return@forEach
                seenPrograms.clear()

                fun insertProgram(program: IptvProgram) {
                    if (program.title.isBlank() || program.endUtcMillis <= program.startUtcMillis) return
                    val titleTrimmed = program.title.trim()
                    val key = ProgramDedupKey(program.startUtcMillis, program.endUtcMillis, titleTrimmed)
                    if (!seenPrograms.add(key)) return

                    val description = program.description?.trim()?.take(MAX_DESCRIPTION_CHARS)
                    pending += PendingProgramRow(
                        channelId = normalizedId,
                        startMs = program.startUtcMillis,
                        endMs = program.endUtcMillis,
                        title = titleTrimmed,
                        description = description,
                        catchupAvailable = program.catchupAvailable,
                        artworkUrl = program.artworkUrl?.take(2048),
                        category = program.category?.take(200),
                    )
                    if (pending.size == MAX_INSERT_ROWS) {
                        flushPending()
                    }
                }

                item.now?.let(::insertProgram)
                item.next?.let(::insertProgram)
                item.later?.let(::insertProgram)
                item.upcoming.forEach(::insertProgram)
                item.recent.forEach(::insertProgram)
            }
            flushPending()
        } finally {
            statements.values.forEach { it.close() }
        }
    }

    private fun abortIfRequested(shouldAbort: () -> Boolean) {
        if (shouldAbort()) {
            throw kotlinx.coroutines.CancellationException(
                "EPG index update deferred while Live TV is interactive"
            )
        }
    }

    private fun SQLiteDatabase.upsertSource(
        sourceKey: String,
        updatedAtMs: Long,
        channelCount: Int,
        programCount: Int
    ) {
        compileStatement(
            """
            INSERT OR REPLACE INTO epg_sources
            (source_key, updated_ms, channel_count, program_count, full_updated_ms)
            VALUES (?, ?, ?, ?, COALESCE((SELECT full_updated_ms FROM epg_sources WHERE source_key = ?), 0))
            """.trimIndent()
        ).use { statement ->
            statement.bindString(1, sourceKey)
            statement.bindLong(2, updatedAtMs)
            statement.bindLong(3, channelCount.toLong())
            statement.bindLong(4, programCount.toLong())
            statement.bindString(5, sourceKey)
            statement.executeInsert()
        }
    }

    private fun SQLiteDatabase.touchSource(sourceKey: String, updatedAtMs: Long) {
        val updated = compileStatement(
            "UPDATE epg_sources SET updated_ms = ? WHERE source_key = ?"
        ).use { statement ->
            statement.bindLong(1, updatedAtMs)
            statement.bindString(2, sourceKey)
            statement.executeUpdateDelete()
        }
        if (updated == 0) {
            upsertSource(sourceKey, updatedAtMs, channelCount = -1, programCount = -1)
        }
    }

    private fun buildNowNext(programs: List<IptvProgram>, nowMs: Long, recentProgramLimit: Int): IptvNowNext? {
        if (programs.isEmpty()) return null
        val sorted = programs
            .asSequence()
            .filter { it.endUtcMillis > it.startUtcMillis }
            .distinctBy { "${it.startUtcMillis}|${it.endUtcMillis}|${it.title}" }
            .sortedBy { it.startUtcMillis }
            .toList()
        if (sorted.isEmpty()) return null

        val now = sorted.lastOrNull { it.isLive(nowMs) }
        val future = sorted
            .asSequence()
            .filter { it.startUtcMillis > nowMs }
            .take(MAX_UPCOMING_PROGRAMS)
            .toList()
        val recent = sorted
            .filter { it.endUtcMillis <= nowMs }
            .takeLast(recentProgramLimit.coerceIn(0, IptvGuideHistory.MAX_PROGRAMS))

        val result = IptvNowNext(
            now = now,
            next = future.getOrNull(0),
            later = future.getOrNull(1),
            upcoming = future,
            recent = recent
        )
        return if (
            result.now != null ||
            result.next != null ||
            result.later != null ||
            result.upcoming.isNotEmpty() ||
            result.recent.isNotEmpty()
        ) {
            result
        } else {
            null
        }
    }

    private inline fun SQLiteDatabase.runInTransaction(block: SQLiteDatabase.() -> Unit) {
        beginTransaction()
        try {
            block()
            setTransactionSuccessful()
        } finally {
            endTransaction()
        }
    }

    private fun SQLiteDatabase.checkpointWalAfterBulkWrite() {
        runCatching {
            rawQuery("PRAGMA wal_checkpoint(TRUNCATE)", null).use { cursor ->
                if (cursor.moveToFirst()) {
                    System.err.println(
                        "[IPTV-Timing] EPG WAL checkpoint busy=${cursor.getInt(0)} " +
                            "log=${cursor.getInt(1)} checkpointed=${cursor.getInt(2)}"
                    )
                }
            }
        }.onFailure { error ->
            System.err.println("[IPTV-Timing] EPG WAL checkpoint deferred: ${error.message}")
        }
    }


    private companion object {
        const val DATABASE_NAME = "arvio_iptv_epg_index.db"
        // v2 dropped the guide table on upgrade. A previous build's full-guide backfill
        // bloated it with up to 336 programmes/channel; loading 360 such channels into
        // memory churned the heap and crashed the Live TV page. Recreating it clears
        // that, and the reduced caps below keep per-channel memory bounded.
        // v3 persists coverage statistics so startup never scans the whole table.
        // v4 removes the duplicate programme-window index to speed up bulk imports.
        // v5 shares XMLTV schedules across variants; v6 distinguishes full and partial refreshes.
        // v7 retains provider archive availability without deleting existing guide data.
        const val DATABASE_VERSION = 8
        const val MAX_SQL_ARGS = 900
        const val INSERT_BINDINGS_PER_ROW = 9
        const val MAX_INSERT_ROWS = MAX_SQL_ARGS / INSERT_BINDINGS_PER_ROW
        const val MAX_DESCRIPTION_CHARS = 200
        // ±48h of guide needs only ~24-48 programmes each way. Keeping 96+240 held far
        // more in memory than the grid ever shows.
        const val MAX_UPCOMING_PROGRAMS = 48
        const val MAX_RECENT_PROGRAMS = 48
        const val DEFAULT_PAST_WINDOW_MS = 48L * 60L * 60_000L
        const val DEFAULT_FUTURE_WINDOW_MS = 48L * 60L * 60_000L
    }
}
