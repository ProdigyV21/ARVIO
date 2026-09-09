package com.arflix.tv.data.api

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.network.withIptvProviderRequestGuard
import com.arflix.tv.data.repository.StalkerPortalSupport
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.FilterReader
import java.io.Reader
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Stalker/Ministra portal API client for MAC-based IPTV authentication.
 * Converts Stalker portal channels into the same IptvChannel format as Xtream/M3U.
 */
open class StalkerApi(
    private val portalUrl: String,
    private val macAddress: String
) {
    private var apiBase: String = portalUrl.trim().trimEnd('/')
    private var apiBaseResolved = false

    private val client = OkHttpClient.Builder()
        .withIptvProviderRequestGuard()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()
    private val gson = Gson()
    private var token: String = ""
    private var serialNumber: String = ""

    /** Stable identity for EPG caches; intentionally never written to logs or disk. */
    internal val epgCacheIdentity: String =
        portalUrl.trim().trimEnd('/').lowercase(Locale.ROOT) + "\u0000" +
            macAddress.trim().uppercase(Locale.ROOT)

    private val baseHeaders: Map<String, String>
        get() = mapOf(
            "User-Agent" to "Mozilla/5.0 (QtEmbedded; U; Linux; C) AppleWebKit/533.3 (KHTML, like Gecko) MAG200 stbapp ver: 2 rev: 250 Safari/533.3",
            "Cookie" to "mac=$macAddress; stb_lang=en; timezone=Europe/London",
            "X-User-Agent" to "Model: MAG250; Link: WiFi",
            "Authorization" to "Bearer $token"
        )

    /** Step 1: Handshake to get auth token */
    suspend fun handshake(): Boolean {
        return try {
            if (!apiBaseResolved) {
                resolveApiBase()
                // Probing the base path is itself a handshake and keeps the token it
                // received, so a second one here would only throw that token away.
                if (token.isNotBlank()) return true
            }
            val url = "$apiBase/server/load.php?type=stb&action=handshake&token=&JsHttpRequest=1-xml"
            val response = doGet(url)
            val parsed = gson.fromJson(response, StalkerHandshakeResponse::class.java)
            token = parsed?.js?.token ?: return false
            true
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            System.err.println("[Stalker] Handshake failed: ${e.message}")
            false
        }
    }

    /**
     * Try candidate portal base paths until one responds to the handshake with
     * a valid token. HTML 404 pages (e.g. served on /c/ portal URLs) or empty
     * bodies must not stop the probing early.
     * Order: / (root), /stalker_portal, /portal, /c
     */
    private suspend fun resolveApiBase() {
        val cleanPortal = portalUrl.trim().trimEnd('/')
        // Common portals serve the UI under /c/ while the API lives at the
        // root or a root subpath, so probe both the raw URL and its /c-stripped root.
        val root = cleanPortal.removeSuffix("/c").removeSuffix("/")
        val candidates = listOf(
            cleanPortal,
            root,
            "$root/stalker_portal",
            "$root/portal",
            "$root/ministra"
        ).distinct()
        for (base in candidates) {
            try {
                val url = "$base/server/load.php?type=stb&action=handshake"
                val response = doGet(url)
                val probeToken = try {
                    gson.fromJson(response, StalkerHandshakeResponse::class.java)?.js?.token
                } catch (_: Exception) { null }
                if (!probeToken.isNullOrBlank()) {
                    apiBase = base
                    token = probeToken
                    apiBaseResolved = true
                    return
                }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                // continue to next candidate
            }
        }
        // Fallback to root
        apiBase = cleanPortal
        apiBaseResolved = true
    }

    /** Step 2: Get profile (validates the connection) */
    suspend fun getProfile(): Boolean {
        return try {
            val url = "$apiBase/server/load.php?type=stb&action=get_profile&JsHttpRequest=1-xml"
            val response = doGet(url)
            response.contains("\"id\"")
        } catch (_: Exception) { false }
    }

    /** Step 3: Get all channels */
    suspend fun getChannels(): List<IptvChannel> {
        val channels = mutableListOf<IptvChannel>()
        val seenChannelIds = HashSet<String>()
        try {
            // Get genres first for group names
            val genreUrl = "$apiBase/server/load.php?type=itv&action=get_genres&JsHttpRequest=1-xml"
            val genreResponse = doGet(genreUrl)
            val genres = gson.fromJson(genreResponse, StalkerGenreResponse::class.java)
            val genreMap = genres?.js?.mapNotNull { g -> g.id?.let { it to (g.title ?: "Unknown") } }?.toMap() ?: emptyMap()

            // Get all channels page by page
            var page = 1
            var hasMore = true
            while (hasMore) {
                val url = "$apiBase/server/load.php?type=itv&action=get_all_channels&p=$page&JsHttpRequest=1-xml"
                val response = doGet(url)
                val parsed = gson.fromJson(response, StalkerChannelResponse::class.java)
                val data = parsed?.js?.data ?: break

                var newChannelIdCount = 0
                for (ch in data) {
                    val channelId = ch.id?.toString() ?: continue
                    if (!seenChannelIds.add(channelId)) continue
                    newChannelIdCount++
                    val streamCmd = ch.cmd ?: continue
                    val groupName = ch.tvGenreId?.let { genreMap[it] } ?: "Uncategorized"
                    // Portals that announce no temporary link publish the finished
                    // address here. Preserve that decision separately: even bare URLs
                    // can require create_link when the portal says so or omits the flags.
                    val directUrl = StalkerPortalSupport.directLiveStreamUrl(
                        cmd = streamCmd,
                        useHttpTmpLink = ch.useHttpTmpLink,
                        wowzaTmpLink = ch.wowzaTmpLink,
                        flussonicTmpLink = ch.flussonicTmpLink,
                    )
                    channels.add(
                        IptvChannel(
                            id = channelId,
                            name = ch.name ?: "Unknown",
                            logo = ch.logo,
                            group = groupName,
                            streamUrl = directUrl ?: streamCmd,
                            stalkerDirectStream = directUrl != null,
                        )
                    )
                }

                val totalItems = parsed.js?.totalItems ?: 0
                val maxPageItems = (parsed.js?.maxPageItems ?: 20).coerceAtLeast(1)
                // Some portals ignore pagination and return all channels in every response.
                // Stop when a page contains no new IDs as well as when one response
                // already contains the complete channel list.
                hasMore = newChannelIdCount > 0 &&
                    page * maxPageItems < totalItems &&
                    data.size < totalItems
                page++
            }

        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            System.err.println("[Stalker] Get channels failed: ${e.message}")
            // Never publish a partial page set as the complete provider catalog.
            throw e
        }
        return channels
    }

    /**
     * Fetch the portal's now/next EPG data for all channels.
     * [date] uses `YYYY-MM-DD`; blank means "today" on the server side.
     *
     * Tries `type=epg&action=get_simple_data_table` first (lightweight, one flat
     * list). Some Stalker/Ministra portal builds don't implement the `epg` type
     * handler at all (confirmed on-device: 0-byte response, while `itv`/`stb`
     * actions on the same portal work fine) - for those, falls back to
     * `type=itv&action=get_epg_info`, which some portals return as a flat list
     * like the first action and others as an object keyed by channel id.
     */
    suspend fun getEpg(
        date: String = "",
        notBeforeEpochSeconds: Long? = null,
        maxProgramsPerChannel: Int = Int.MAX_VALUE
    ): List<StalkerEpgProgram> {
        require(maxProgramsPerChannel > 0) { "maxProgramsPerChannel must be positive" }
        val dateParam = if (date.isBlank()) "" else "&date=${java.net.URLEncoder.encode(date, "UTF-8")}"
        val simpleTable = fetchSimpleDataTableEpg(
            dateParam = dateParam,
            notBeforeEpochSeconds = notBeforeEpochSeconds,
            maxProgramsPerChannel = maxProgramsPerChannel
        )
        if (simpleTable.sawProgramEntry) return simpleTable.programs
        return fetchEpgInfoFallback(
            dateParam = dateParam,
            notBeforeEpochSeconds = notBeforeEpochSeconds,
            maxProgramsPerChannel = maxProgramsPerChannel
        ).programs
    }

    private fun fetchSimpleDataTableEpg(
        dateParam: String,
        notBeforeEpochSeconds: Long?,
        maxProgramsPerChannel: Int
    ): StalkerEpgParseResult {
        return try {
            val url = "$apiBase/server/load.php?type=epg&action=get_simple_data_table&ch_id=all$dateParam&JsHttpRequest=1-xml"
            doGetReader(url).use { response ->
                parseEpgResponse(response, notBeforeEpochSeconds, maxProgramsPerChannel)
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            System.err.println("[Stalker] get_simple_data_table failed: ${e.message}")
            StalkerEpgParseResult()
        }
    }

    /**
     * `get_epg_info` response shape varies by portal build. Confirmed on-device
     * against two different portals: `{"js":{"data":[]}}` (nothing available) and
     * `{"js":{"data":{"<ch_id>":[{...program...}], ...}}}` (real data, one entry
     * per channel id, ~tens of MB for a multi-thousand-channel portal). Some
     * builds may skip the "data" wrapper and put the array/object directly under
     * "js" (matching the get_simple_data_table shape) - both are handled.
     */
    private fun fetchEpgInfoFallback(
        dateParam: String,
        notBeforeEpochSeconds: Long?,
        maxProgramsPerChannel: Int
    ): StalkerEpgParseResult {
        return try {
            val url = "$apiBase/server/load.php?type=itv&action=get_epg_info$dateParam&JsHttpRequest=1-xml"
            doGetReader(url).use { response ->
                parseEpgResponse(response, notBeforeEpochSeconds, maxProgramsPerChannel)
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            System.err.println("[Stalker] get_epg_info failed: ${e.message}")
            StalkerEpgParseResult()
        }
    }

    /**
     * Streams the portal response instead of materializing a large response String and
     * a second Gson tree. Production callers can also bound each channel to the nearest
     * current/future entries so multi-thousand-channel portals stay within TV memory limits.
     */
    private fun parseEpgResponse(
        response: Reader,
        notBeforeEpochSeconds: Long?,
        maxProgramsPerChannel: Int
    ): StalkerEpgParseResult {
        val collector = StalkerEpgCollector(notBeforeEpochSeconds, maxProgramsPerChannel)
        JsonReader(response).use { reader ->
            reader.isLenient = true
            when (reader.peek()) {
                JsonToken.BEGIN_OBJECT -> {
                    reader.beginObject()
                    while (reader.hasNext()) {
                        if (reader.nextName() == "js") {
                            readEpgContainer(reader, collector, inheritedChannelId = null)
                        } else {
                            reader.skipValue()
                        }
                    }
                    reader.endObject()
                }
                JsonToken.BEGIN_ARRAY -> readProgramArray(reader, collector, inheritedChannelId = null)
                else -> reader.skipValue()
            }
        }
        return collector.result()
    }

    private fun readEpgContainer(
        reader: JsonReader,
        collector: StalkerEpgCollector,
        inheritedChannelId: String?
    ) {
        when (reader.peek()) {
            JsonToken.BEGIN_ARRAY -> readProgramArray(reader, collector, inheritedChannelId)
            JsonToken.BEGIN_OBJECT -> {
                reader.beginObject()
                while (reader.hasNext()) {
                    val name = reader.nextName()
                    when {
                        name == "data" -> readEpgContainer(reader, collector, inheritedChannelId = null)
                        reader.peek() == JsonToken.BEGIN_ARRAY ->
                            readProgramArray(reader, collector, inheritedChannelId = name)
                        else -> reader.skipValue()
                    }
                }
                reader.endObject()
            }
            JsonToken.NULL -> reader.nextNull()
            else -> reader.skipValue()
        }
    }

    private fun readProgramArray(
        reader: JsonReader,
        collector: StalkerEpgCollector,
        inheritedChannelId: String?
    ) {
        reader.beginArray()
        while (reader.hasNext()) {
            if (reader.peek() == JsonToken.BEGIN_OBJECT) {
                collector.add(readProgram(reader, inheritedChannelId))
            } else {
                reader.skipValue()
            }
        }
        reader.endArray()
    }

    private fun readProgram(reader: JsonReader, inheritedChannelId: String?): StalkerEpgProgram {
        var chId: String? = inheritedChannelId
        var name: String? = null
        var descr: String? = null
        var startTimestamp: String? = null
        var stopTimestamp: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "ch_id", "channel_id" -> chId = reader.nextNullableString() ?: chId
                "name", "title" -> name = reader.nextNullableString()
                "descr", "description" -> descr = reader.nextNullableString()
                "start_timestamp", "start" -> startTimestamp = reader.nextNullableString()
                "stop_timestamp", "end_timestamp", "end" -> stopTimestamp = reader.nextNullableString()
                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return StalkerEpgProgram(chId, name, descr, startTimestamp, stopTimestamp)
    }

    private fun JsonReader.nextNullableString(): String? = when (peek()) {
        JsonToken.NULL -> {
            nextNull()
            null
        }
        JsonToken.STRING, JsonToken.NUMBER, JsonToken.BOOLEAN -> nextString()
        else -> {
            skipValue()
            null
        }
    }

    private data class StalkerEpgParseResult(
        val programs: List<StalkerEpgProgram> = emptyList(),
        val sawProgramEntry: Boolean = false
    )

    private class StalkerEpgCollector(
        private val notBeforeEpochSeconds: Long?,
        private val maxProgramsPerChannel: Int
    ) {
        private val bounded = notBeforeEpochSeconds != null || maxProgramsPerChannel != Int.MAX_VALUE
        private val programsByChannel = LinkedHashMap<String, MutableList<StalkerEpgProgram>>()
        private val ungroupedPrograms = mutableListOf<StalkerEpgProgram>()
        private var sawProgramEntry = false

        fun add(program: StalkerEpgProgram) {
            sawProgramEntry = true
            val channelId = program.chId?.takeIf { it.isNotBlank() }
            if (!bounded) {
                if (channelId == null) ungroupedPrograms += program
                else programsByChannel.getOrPut(channelId) { mutableListOf() } += program
                return
            }
            if (channelId == null) return
            val start = program.startTimestamp?.toLongOrNull() ?: return
            val stop = program.stopTimestamp?.toLongOrNull() ?: return
            if (stop <= start || (notBeforeEpochSeconds != null && stop <= notBeforeEpochSeconds)) return

            val channelPrograms = programsByChannel.getOrPut(channelId) { mutableListOf() }
            channelPrograms += program
            if (channelPrograms.size > maxProgramsPerChannel) {
                val latestIndex = channelPrograms.indices.maxBy { index ->
                    channelPrograms[index].startTimestamp?.toLongOrNull() ?: Long.MAX_VALUE
                }
                channelPrograms.removeAt(latestIndex)
            }
        }

        fun result(): StalkerEpgParseResult {
            val programs = ArrayList<StalkerEpgProgram>(
                ungroupedPrograms.size + programsByChannel.values.sumOf { it.size }
            )
            programs += ungroupedPrograms
            programsByChannel.values.forEach { channelPrograms ->
                programs += channelPrograms.sortedBy { it.startTimestamp?.toLongOrNull() ?: Long.MAX_VALUE }
            }
            return StalkerEpgParseResult(programs, sawProgramEntry)
        }
    }

    /**
     * Per-channel EPG fallback for portals whose `get_simple_data_table`/`get_epg_info`
     * both come back empty (confirmed live on-device: 8/8 channels succeed on such a
     * portal when neither bulk action returns anything). Response shape is the same flat
     * `{"js":[...]}` array as `get_simple_data_table`. [chId] is the portal's own numeric
     * channel id (the same value used as `ch_id` elsewhere, i.e. the last segment of our
     * `stalker:<portalId>:<origId>` channel id).
     */
    suspend fun getShortEpg(chId: String): List<StalkerEpgProgram> {
        return try {
            val encodedChId = java.net.URLEncoder.encode(chId, "UTF-8")
            val url = "$apiBase/server/load.php?type=itv&action=get_short_epg&ch_id=$encodedChId&size=10&JsHttpRequest=1-xml"
            val response = doGet(url)
            val parsed = gson.fromJson(response, StalkerEpgResponse::class.java)
            parsed?.js.orEmpty().filterNotNull().map { program ->
                if (program.chId.isNullOrBlank()) program.copy(chId = chId) else program
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            System.err.println("[Stalker] get_short_epg failed for ch_id=$chId: ${e.message}")
            emptyList()
        }
    }

    /**
     * Ask the portal itself for movies matching [query] instead of downloading
     * the whole catalog first.
     *
     * A Stalker portal only serves `get_ordered_list` in pages of (typically)
     * 14 entries, so mirroring the Xtream approach - fetch the complete catalog,
     * index it locally - would cost hundreds of requests per refresh on a large
     * portal. `search` narrows the same endpoint server-side, which keeps a
     * movie lookup at one request.
     *
     * Returns an empty list when the portal does not implement VOD listing at
     * all: such builds answer with an HTML page or a bare `{"js":""}` under a
     * plain HTTP 200, so success is measured on the parsed payload, never on the
     * status code.
     */
    suspend fun searchVod(
        query: String,
        maxPages: Int = DEFAULT_VOD_SEARCH_PAGES
    ): List<StalkerVodItem>? {
        require(maxPages > 0) { "maxPages must be positive" }
        val term = query.trim()
        if (term.isBlank()) return emptyList()

        val results = mutableListOf<StalkerVodItem>()
        val seenKeys = HashSet<String>()
        try {
            val encodedTerm = java.net.URLEncoder.encode(term, "UTF-8")
            var page = 1
            while (page <= maxPages) {
                // `category=0` means "every category" here. The category list
                // spells the same idea as `id: "*"`, but get_ordered_list does
                // not accept it: a portal that reads `*` as a literal category
                // name finds nothing, or drops `search` altogether and answers
                // with the head of its catalogue.
                // `sortby=name` keeps the matches for one term together. Sorted
                // by date added instead, a catalogue of six figures pushes them
                // past [maxPages] purely by age.
                val url = "$apiBase/server/load.php?type=vod&action=get_ordered_list" +
                    "&category=0&sortby=name&search=$encodedTerm&p=$page&JsHttpRequest=1-xml"
                val response = doGet(url)
                val parsed = gson.fromJson(response, StalkerVodResponse::class.java)
                val data = parsed?.js?.data ?: break
                if (data.isEmpty()) break

                var newEntries = 0
                for (item in data) {
                    val command = item.cmd?.trim().orEmpty()
                    if (command.isBlank()) continue
                    val key = item.id?.trim()?.ifBlank { null } ?: command
                    if (!seenKeys.add(key)) continue
                    newEntries++
                    results += item
                }

                val totalItems = parsed.js?.totalItems ?: 0
                val maxPageItems = (parsed.js?.maxPageItems ?: data.size).coerceAtLeast(1)
                // Some portals ignore `p` and answer every page with the same
                // result set - stop as soon as a page adds nothing new. A
                // portal that reports no total at all keeps paging until then
                // or until [maxPages].
                if (newEntries == 0) break
                if (totalItems > 0 && data.size >= totalItems) break
                if (totalItems > 0 && page * maxPageItems >= totalItems) break
                page++
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            System.err.println("[Stalker] VOD search failed: ${e.message}")
            // null, not the partial list: the caller caches what it gets back,
            // and a failed request must never be stored as "this portal has
            // nothing" - see the null contract on the return type.
            return null
        }
        return results
    }

    /**
     * Ask the portal for shows matching [query], the series counterpart of
     * [searchVod].
     *
     * Returns null when the request itself failed and an empty list when the
     * portal answered but knows no such show - callers cache the two very
     * differently.
     *
     * Same reasoning as there: `get_ordered_list` only ever answers in small
     * pages, so the portal's own `search` does the narrowing instead of a local
     * catalog copy. This is level one of Stalker's two-level series model - the
     * show, not its episodes.
     */
    suspend fun searchSeries(
        query: String,
        maxPages: Int = DEFAULT_VOD_SEARCH_PAGES
    ): List<StalkerSeriesItem>? {
        require(maxPages > 0) { "maxPages must be positive" }
        val term = query.trim()
        if (term.isBlank()) return emptyList()
        val encodedTerm = java.net.URLEncoder.encode(term, "UTF-8")
        return fetchSeriesPages(
            // Same two parameters as [searchVod], for the same reasons.
            baseUrl = "$apiBase/server/load.php?type=series&action=get_ordered_list" +
                "&category=0&sortby=name&search=$encodedTerm",
            maxPages = maxPages,
            failureLabel = "series search"
        )
    }

    /**
     * Level two: the seasons of one show, addressed by the show's portal id.
     *
     * Each entry carries its own `cmd` plus a `series` array of the episode
     * numbers available in that season - the episode itself has no id, it is a
     * parameter of [resolveVodStreamUrl]. Note that some portal builds answer
     * this call with episode entries rather than seasons; [StalkerSeriesItem]
     * covers both because the response shape is identical.
     */
    suspend fun getSeasons(
        seriesId: String,
        maxPages: Int = DEFAULT_SEASON_PAGES
    ): List<StalkerSeriesItem>? {
        require(maxPages > 0) { "maxPages must be positive" }
        val id = seriesId.trim()
        if (id.isBlank()) return emptyList()
        val encodedId = java.net.URLEncoder.encode(id, "UTF-8")
        return fetchSeriesPages(
            // No `sortby`: the seasons of one show arrive in the portal's own
            // order, and asking for another one only risks a build that reads
            // the parameter as a filter.
            baseUrl = "$apiBase/server/load.php?type=series&action=get_ordered_list" +
                "&movie_id=$encodedId",
            maxPages = maxPages,
            failureLabel = "get_seasons movie_id=$id"
        )
    }

    /**
     * Shared paging for both series levels. [baseUrl] carries everything except
     * `p` and the JsHttpRequest marker.
     *
     * A portal that ignores `p` answers every page with the same entries, so
     * paging stops as soon as a page adds nothing new; a portal that reports no
     * total at all stops there or at [maxPages]. Failure is measured on the
     * parsed payload - portals without series support answer an HTML page or a
     * bare `{"js":""}` under a plain HTTP 200.
     */
    private suspend fun fetchSeriesPages(
        baseUrl: String,
        maxPages: Int,
        failureLabel: String
    ): List<StalkerSeriesItem>? {
        val results = mutableListOf<StalkerSeriesItem>()
        val seenKeys = HashSet<String>()
        try {
            var page = 1
            while (page <= maxPages) {
                val url = "$baseUrl&p=$page&JsHttpRequest=1-xml"
                val response = doGet(url)
                val parsed = gson.fromJson(response, StalkerSeriesResponse::class.java)
                val data = parsed?.js?.data ?: break
                if (data.isEmpty()) break

                var newEntries = 0
                for (item in data) {
                    val key = item.id?.trim()?.ifBlank { null }
                        ?: item.cmd?.trim()?.ifBlank { null }
                        ?: continue
                    if (!seenKeys.add(key)) continue
                    newEntries++
                    results += item
                }

                val totalItems = parsed.js?.totalItems ?: 0
                val maxPageItems = (parsed.js?.maxPageItems ?: data.size).coerceAtLeast(1)
                if (newEntries == 0) break
                if (totalItems > 0 && data.size >= totalItems) break
                if (totalItems > 0 && page * maxPageItems >= totalItems) break
                page++
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            System.err.println("[Stalker] $failureLabel failed: ${e.message}")
            // See searchVod: a failure is null, never an empty result set.
            return null
        }
        return results
    }

    /**
     * Exchange a VOD `cmd` for a playable URL (`type=vod&action=create_link`).
     *
     * [series] is the episode number for a season `cmd`; omitting it resolves a
     * movie. Only ever called when playback actually starts - see
     * [StalkerVodItem].
     */
    suspend fun resolveVodStreamUrl(cmd: String, series: Int? = null): String? {
        val command = cmd.trim()
        if (command.isBlank()) return null
        return try {
            val encodedCmd = java.net.URLEncoder.encode(command, "UTF-8")
            val seriesParam = series?.takeIf { it > 0 }?.let { "&series=$it" }.orEmpty()
            val url = "$apiBase/server/load.php?type=vod&action=create_link&cmd=$encodedCmd" +
                seriesParam +
                "&forced_storage=undefined&disable_ad=0&JsHttpRequest=1-xml"
            val response = doGet(url)
            val parsed = gson.fromJson(response, StalkerLinkResponse::class.java)
            sanitizePlaybackCommand(parsed?.js?.cmd)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            System.err.println("[Stalker] Resolve VOD stream failed: ${e.message}")
            null
        }
    }

    /** Resolve a channel's cmd to a playable stream URL */
    suspend fun resolveStreamUrl(cmd: String): String? {
        return try {
            val encodedCmd = java.net.URLEncoder.encode(cmd, "UTF-8")
            val url = "$apiBase/server/load.php?type=itv&action=create_link&cmd=$encodedCmd&forced_storage=undefined&disable_ad=0&JsHttpRequest=1-xml"
            val response = doGet(url)
            val parsed = gson.fromJson(response, StalkerLinkResponse::class.java)
            StalkerPortalSupport.sanitizePlaybackCommand(parsed?.js?.cmd).takeIf { it.isNotBlank() }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            System.err.println("[Stalker] Resolve stream failed: ${e.message}")
            null
        }
    }

    private fun buildRequest(url: String): Request {
        val builder = Request.Builder().url(url)
        baseHeaders.forEach { (k, v) -> builder.addHeader(k, v) }
        return builder.build()
    }

    open fun doGet(url: String): String =
        client.newCall(buildRequest(url)).execute().use { response ->
            response.body?.string().orEmpty()
        }

    internal open fun doGetReader(url: String): Reader {
        val response = client.newCall(buildRequest(url)).execute()
        val body = response.body ?: run {
            response.close()
            return "".reader()
        }
        return object : FilterReader(body.charStream()) {
            override fun close() {
                try {
                    super.close()
                } finally {
                    response.close()
                }
            }
        }
    }

    // ── Response models ──

    data class StalkerHandshakeResponse(val js: StalkerToken?)
    data class StalkerToken(val token: String?)

    data class StalkerGenreResponse(val js: List<StalkerGenre>?)
    data class StalkerGenre(val id: String?, val title: String?)

    data class StalkerChannelResponse(val js: StalkerChannelData?)
    data class StalkerChannelData(
        val data: List<StalkerChannel>?,
        @SerializedName("total_items") val totalItems: Int?,
        @SerializedName("max_page_items") val maxPageItems: Int?
    )
    data class StalkerChannel(
        val id: Int?,
        val name: String?,
        val logo: String?,
        val cmd: String?,
        @SerializedName("tv_genre_id") val tvGenreId: String?,
        // Read as text on purpose: portals send these as 0/1, as "0"/"1", and
        // occasionally as an empty string, which a numeric field would reject —
        // taking the whole channel page down with it.
        @SerializedName("use_http_tmp_link") val useHttpTmpLink: String? = null,
        @SerializedName("wowza_tmp_link") val wowzaTmpLink: String? = null,
        @SerializedName("flussonic_tmp_link") val flussonicTmpLink: String? = null
    )

    data class StalkerLinkResponse(val js: StalkerLink?)
    data class StalkerLink(val cmd: String?)

    /**
     * One entry of the portal's VOD catalog.
     *
     * Every field is a String because portals disagree on whether ids, years
     * and ratings arrive as JSON numbers or strings; Gson accepts both for a
     * String field but throws on a mismatched primitive type, which would lose
     * the whole response. [cmd] is the token that has to go through
     * `create_link` before it can be played. [tmdbId] is only filled in by some
     * portal builds - matching falls back to title and year without it.
     */
    data class StalkerVodItem(
        val id: String? = null,
        val name: String? = null,
        val cmd: String? = null,
        val year: String? = null,
        /** Runtime in minutes on most builds; a few send "hh:mm:ss" instead. */
        val time: String? = null,
        /** 1 when the portal flags the entry as HD. Not an actual resolution. */
        val hd: String? = null,
        @SerializedName("screenshot_uri") val screenshotUri: String? = null,
        @SerializedName("rating_imdb") val ratingImdb: String? = null,
        @SerializedName(value = "tmdb_id", alternate = ["tmdb", "tmdbid"]) val tmdbId: String? = null,
        @SerializedName("category_id") val categoryId: String? = null
    )

    data class StalkerVodResponse(val js: StalkerVodData?)
    data class StalkerVodData(
        val data: List<StalkerVodItem>?,
        @SerializedName("total_items") val totalItems: Int?,
        @SerializedName("max_page_items") val maxPageItems: Int?
    )

    /**
     * One entry of the portal's series catalog - a show on level one, a season
     * of that show on level two. The response shape is identical for both,
     * which is why one class serves them.
     *
     * [series] is what tells them apart: a show has none, a season lists the
     * episode numbers it holds. It stays a raw [JsonElement] because portals
     * send it as an array of numbers, an array of strings, or an empty string
     * when there is nothing to list - a typed field would throw away the whole
     * response on the odd one out. Use [episodeNumbers] to read it.
     */
    data class StalkerSeriesItem(
        val id: String? = null,
        val name: String? = null,
        val cmd: String? = null,
        val year: String? = null,
        val time: String? = null,
        val hd: String? = null,
        val series: JsonElement? = null,
        @SerializedName("screenshot_uri") val screenshotUri: String? = null,
        @SerializedName("rating_imdb") val ratingImdb: String? = null,
        @SerializedName(value = "tmdb_id", alternate = ["tmdb", "tmdbid"]) val tmdbId: String? = null,
        @SerializedName("category_id") val categoryId: String? = null
    )

    data class StalkerSeriesResponse(val js: StalkerSeriesData?)
    data class StalkerSeriesData(
        val data: List<StalkerSeriesItem>?,
        @SerializedName("total_items") val totalItems: Int?,
        @SerializedName("max_page_items") val maxPageItems: Int?
    )

    companion object {
        /**
         * Search results are already narrow; a handful of pages is plenty and
         * keeps a single lookup from turning into a crawl.
         */
        const val DEFAULT_VOD_SEARCH_PAGES = 3

        /**
         * Seasons of a single show fit one page on any portal seen so far; the
         * second page exists for builds that answer this call with episodes
         * instead of seasons.
         */
        const val DEFAULT_SEASON_PAGES = 2

        /**
         * Episode numbers a season entry offers, read from its raw `series`
         * field.
         *
         * Accepts numbers and numeric strings and drops everything else, so a
         * portal that pads the array with nulls or labels still yields the
         * numbers it does report. Duplicates are collapsed and the result is
         * sorted, because "is episode N here" is the only question asked of it.
         */
        fun episodeNumbers(raw: JsonElement?): List<Int> {
            val array = raw as? JsonArray ?: return emptyList()
            val numbers = LinkedHashSet<Int>()
            for (element in array) {
                val primitive = element as? JsonPrimitive ?: continue
                val value = runCatching { primitive.asString }.getOrNull()?.trim()
                val number = value?.toIntOrNull() ?: continue
                if (number > 0) numbers += number
            }
            return numbers.sorted()
        }

        /**
         * Portals return the playable URL prefixed with the player they expect
         * ("ffmpeg http://...", "auto http://..."). Strip that hint, but leave a
         * value that is already a bare URL untouched.
         */
        fun sanitizePlaybackCommand(raw: String?): String? {
            val trimmed = raw?.trim().orEmpty()
            if (trimmed.isEmpty()) return null
            val separator = trimmed.indexOf(' ')
            if (separator <= 0) return trimmed
            if (trimmed.substring(0, separator).contains("://")) return trimmed
            return trimmed.substring(separator + 1).trim().ifBlank { null }
        }
    }

    data class StalkerEpgResponse(val js: List<StalkerEpgProgram?>?)

    /** Field names vary by portal software/version, hence the alternates. */
    data class StalkerEpgProgram(
        @SerializedName(value = "ch_id", alternate = ["channel_id"]) val chId: String?,
        @SerializedName(value = "name", alternate = ["title"]) val name: String?,
        @SerializedName(value = "descr", alternate = ["description"]) val descr: String?,
        @SerializedName(value = "start_timestamp", alternate = ["start"]) val startTimestamp: String?,
        @SerializedName(value = "stop_timestamp", alternate = ["end_timestamp", "end"]) val stopTimestamp: String?
    )
}
