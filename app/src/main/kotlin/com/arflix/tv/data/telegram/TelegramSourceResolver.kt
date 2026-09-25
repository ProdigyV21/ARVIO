package com.arflix.tv.data.telegram

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.arflix.tv.R
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A Telegram lookup's outcome. [complete] is false when the search did not run to the end — it
 * timed out, Telegram refused it, or playback cancelled it — so [streams] says nothing about what
 * the groups hold, and callers must not cache it as the answer.
 */
data class TelegramResolution(val streams: List<StreamSource>, val complete: Boolean)

@Singleton
class TelegramSourceResolver @Inject constructor(
    private val repository: TelegramRepository,
    private val matcher: TelegramSearchMatcher,
    private val tmdbApi: TmdbApi,
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val TAG = "TelegramResolver"
        private const val SCORE_THRESHOLD = 55
        // The whole lookup. Results already found are kept (and shown) when it runs out; only a
        // lookup that found nothing reports a timeout.
        private const val SEARCH_TIMEOUT_MS = 30_000L
        private const val MAX_RESULTS = 100
        // Phrasings searched at once. Global search is what Telegram rate-limits, and TDLib then
        // holds requests back rather than failing them: one episode lookup sent all ~13 phrasings
        // (26 requests) together and most went unanswered until the lookup gave up (Special Ops
        // S1E1, Sept 2026: 8 of 9 requests unanswered after 10s; limits of 4 and 8 in flight did
        // not help — the volume did).
        private const val MAX_PARALLEL_QUERIES = 2
        private const val CACHE_TTL_SHORT_MS = 2  * 60 * 60 * 1_000L
        private const val CACHE_TTL_LONG_MS  = 24 * 60 * 60 * 1_000L
    }

    private data class CacheEntry(val results: List<StreamSource>, val expiresAt: Long)
    private val cache = ConcurrentHashMap<String, CacheEntry>()

    /**
     * A running lookup: [found] holds every matching source so far (whole list, replaced as it
     * grows) so callers can show results before the lookup ends; [result] is the final answer.
     */
    private class Search(
        val found: MutableStateFlow<List<StreamSource>>,
        val result: Deferred<TelegramResolution>
    )

    /**
     * One search per title/episode at a time: a second caller (the details screen and the player
     * both ask) joins the running one instead of sending another burst. Runs in its own scope so
     * [cancelActiveSearches] can stop it regardless of who started it.
     */
    private val inFlight = ConcurrentHashMap<String, Search>()
    private val searchScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /** Players currently open. While any is, searches stay silent — no toast over playback. */
    private val activePlayers = AtomicInteger(0)

    private fun cacheKey(imdbId: String, title: String, season: Int?, episode: Int?) =
        if (imdbId.isNotBlank()) "$imdbId:${season ?: ""}:${episode ?: ""}"
        else "$title:${season ?: ""}:${episode ?: ""}"

    fun isEnabled(): Boolean = repository.isAuthenticated()

    /** See [TelegramRepository.searchOnClickOnly]. */
    suspend fun searchOnClickOnly(): Boolean = repository.searchOnClickOnly.first()

    /** What an earlier completed lookup found for this title/episode, without searching. */
    fun cachedResults(title: String, season: Int? = null, episode: Int? = null, imdbId: String = ""): List<StreamSource> =
        cache[cacheKey(imdbId, title, season, episode)]
            ?.takeIf { System.currentTimeMillis() < it.expiresAt }
            ?.results
            .orEmpty()

    /**
     * Playback is starting: stop every running search (a source list the user has already chosen
     * from no longer needs it) and keep later ones quiet until [onPlaybackEnded]. A stopped search
     * caches nothing, so the next source list searches again.
     */
    fun onPlaybackStarted() {
        activePlayers.incrementAndGet()
        cancelActiveSearches("playback started")
    }

    fun onPlaybackEnded() {
        activePlayers.updateAndGet { (it - 1).coerceAtLeast(0) }
    }

    private fun cancelActiveSearches(reason: String) {
        val running = inFlight.values.filter { it.result.isActive }
        if (running.isEmpty()) return
        Log.i(TAG, "Cancelling ${running.size} Telegram search(es): $reason")
        running.forEach { it.result.cancel(CancellationException(reason)) }
    }

    // Old movies (released 2+ years ago) are stable — cache longer.
    // Series always use the short TTL since new episodes may appear at any time.
    private fun cacheTtl(year: Int?, isMovie: Boolean): Long {
        if (!isMovie) return CACHE_TTL_SHORT_MS
        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        return if (year != null && year < currentYear - 1) CACHE_TTL_LONG_MS else CACHE_TTL_SHORT_MS
    }

    suspend fun resolve(
        title: String,
        year: Int?,
        season: Int? = null,
        episode: Int? = null,
        imdbId: String = "",
        isMovie: Boolean = true
    ): List<StreamSource> = resolveDetailed(title, year, season, episode, imdbId, isMovie).streams

    /**
     * [onFound] receives every matching source found so far, each time the list grows, while the
     * lookup is still running — the complete list each time, to replace the previous one.
     */
    suspend fun resolveDetailed(
        title: String,
        year: Int?,
        season: Int? = null,
        episode: Int? = null,
        imdbId: String = "",
        isMovie: Boolean = true,
        onFound: (suspend (List<StreamSource>) -> Unit)? = null
    ): TelegramResolution {
        if (!repository.isAuthenticated()) return TelegramResolution(emptyList(), complete = true)

        val key = cacheKey(imdbId, title, season, episode)
        cache[key]?.let { entry ->
            if (System.currentTimeMillis() < entry.expiresAt) return TelegramResolution(entry.results, complete = true)
        }

        val search = inFlight.compute(key) { _, running ->
            running?.takeIf { it.result.isActive } ?: run {
                val found = MutableStateFlow<List<StreamSource>>(emptyList())
                Search(found, searchScope.async {
                    runSearch(key, title, year, season, episode, imdbId, isMovie, found)
                }).also { started -> started.result.invokeOnCompletion { inFlight.remove(key, started) } }
            }
        }!!
        return coroutineScope {
            val relay = onFound?.let { callback ->
                launch { search.found.collect { if (it.isNotEmpty()) callback(it) } }
            }
            try {
                search.result.await()
            } catch (e: CancellationException) {
                // Either this caller was cancelled (rethrow) or the shared search was — by playback
                // starting. The latter is not this caller's failure: keep what was found, cache nothing.
                currentCoroutineContext().ensureActive()
                TelegramResolution(search.found.value, complete = false)
            } finally {
                relay?.cancel()
            }
        }
    }

    private suspend fun runSearch(
        key: String,
        title: String,
        year: Int?,
        season: Int?,
        episode: Int?,
        imdbId: String,
        isMovie: Boolean,
        found: MutableStateFlow<List<StreamSource>>
    ): TelegramResolution = try {
        val startedAt = System.currentTimeMillis()
        val finished = withTimeoutOrNull(SEARCH_TIMEOUT_MS) {
            resolveInternal(title, year, season, episode, imdbId, isMovie, found)
        } != null
        val results = found.value
        Log.i(
            TAG,
            "Telegram lookup '$title'${season?.let { " S${it}E$episode" }.orEmpty()}: " +
                "${results.size} source(s)" + (if (finished) "" else ", timed out") +
                " after ${System.currentTimeMillis() - startedAt}ms"
        )
        if (!finished) {
            // Not cached: a timeout says Telegram was slow, not that the groups have nothing.
            // Caching it hid the sources for hours (2h for a series). What was found is kept.
            if (results.isEmpty()) notifyUser(context.getString(R.string.telegram_search_timed_out))
            TelegramResolution(results, complete = false)
        } else {
            cache[key] = CacheEntry(results, System.currentTimeMillis() + cacheTtl(year, isMovie))
            TelegramResolution(results, complete = true)
        }
    } catch (e: TelegramApiException) {
        Log.w(TAG, "Telegram API error for '$title': ${e.message}")
        if (found.value.isEmpty()) notifyUser(friendlyError(e.message))
        TelegramResolution(found.value, complete = false)
    }

    /**
     * Which phrasings run first. Measured on Special Ops S1E1 (Sept 2026), all 13 sent: ten files
     * matched, and every one was found by `ע1פ1`, `ע1 פ1`, `פרק 1` or `<English title> s01e01`;
     * the other nine phrasings (`פ1`, `עונה 1 פרק 1`, the Hebrew title with Latin S/E patterns,
     * the English title with `s1e1`/`s1 e1`/`s01 e01`) found nothing new and took 30 of the 43s.
     * The core set always runs; the rest only when it found nothing. Movies build 2–6 phrasings
     * and all of them are core.
     */
    private fun splitCoreQueries(queries: List<String>, season: Int?, episode: Int?): Pair<List<String>, List<String>> {
        if (season == null || episode == null) return queries to emptyList()
        val s = season.toString()
        val e = episode.toString()
        val sxe = "s${s.padStart(2, '0')}e${e.padStart(2, '0')}"
        fun isCore(query: String): Boolean =
            query.endsWith(" ע${s}פ$e") ||
                query.endsWith(" ע$s פ$e") ||
                (season == 1 && query.endsWith(" פרק $e") && !query.contains("עונה")) ||
                (season != 1 && query.endsWith(" עונה $s פרק $e")) ||
                (query.endsWith(" $sxe") && !matcher.isHebrew(query))
        val core = queries.filter(::isCore)
        return if (core.isEmpty()) queries to emptyList() else core to queries.filterNot(::isCore)
    }

    private suspend fun resolveInternal(
        title: String,
        year: Int?,
        season: Int?,
        episode: Int?,
        imdbId: String,
        isMovie: Boolean,
        found: MutableStateFlow<List<StreamSource>>
    ) {
        val excludedIds = repository.getExcludedChatIds().first()
        // Read content language from SharedPreferences (same store SettingsViewModel writes to).
        // Avoids a DI cycle: StreamRepository → TelegramSourceResolver → MediaRepository → StreamRepository.
        val rawLang = context.getSharedPreferences("app_locale", android.content.Context.MODE_PRIVATE)
            .getString("locale_tag", "en-US") ?: "en-US"
        val langCode = rawLang.replace("iw", "he").substringBefore("-")
        val titles = fetchTitles(imdbId, isMovie, langCode)
        val englishTitle = titles.english
        val localizedTitle = titles.localized
        val originalTitle = titles.original

        val queries = if (season != null && episode != null)
            matcher.buildSeriesQueries(
                title, season, episode, localizedTitle, englishTitle, langCode, originalTitle
            )
        else
            matcher.buildMovieQueries(title, year, localizedTitle, englishTitle, originalTitle)
        val (core, fallback) = splitCoreQueries(queries, season, episode)

        val seen = mutableSetOf<Pair<String, Long>>()
        // Matching sources by file, built once: the stream URL registers the file with the proxy.
        val matched = LinkedHashMap<Pair<String, Long>, StreamSource>()
        val searchStartedAt = System.currentTimeMillis()

        fun matchesRequest(msg: TelegramVideoMessage): Boolean = matcher.score(
            fileName = msg.fileName,
            caption = msg.caption,
            title = title,
            localizedTitle = localizedTitle,
            englishTitle = englishTitle,
            originalTitle = originalTitle,
            year = year,
            season = season,
            episode = episode
        ) >= SCORE_THRESHOLD

        suspend fun searchBatch(batch: List<String>) {
            val messages = coroutineScope {
                batch.map { query ->
                    async {
                        try {
                            repository.searchVideoMessages(query, MAX_RESULTS)
                                .filter { it.chatId !in excludedIds }
                        } catch (e: TelegramApiException) {
                            throw e
                        } catch (e: kotlinx.coroutines.CancellationException) {
                            throw e
                        } catch (e: Exception) {
                            Log.e(TAG, "Search failed for '$query'", e)
                            emptyList()
                        }
                    }
                }.awaitAll().flatten()
            }
            var grew = false
            for (msg in messages) {
                val fileKey = msg.fileName to msg.fileSize
                if (!seen.add(fileKey) || !matchesRequest(msg)) continue
                matched[fileKey] = toStreamSource(msg)
                grew = true
            }
            // Shown as soon as they are found; the lookup keeps going.
            if (grew) found.value = sortSources(matched.values, langCode)
        }

        var sent = 0
        for (batch in core.chunked(MAX_PARALLEL_QUERIES)) {
            searchBatch(batch)
            sent += batch.size
        }
        if (matched.isEmpty()) {
            for (batch in fallback.chunked(MAX_PARALLEL_QUERIES)) {
                searchBatch(batch)
                sent += batch.size
                if (matched.isNotEmpty()) break
            }
        }
        Log.i(
            TAG,
            "Telegram search: $sent of ${queries.size} phrasings sent (${core.size} core, " +
                "$MAX_PARALLEL_QUERIES at a time) in ${System.currentTimeMillis() - searchStartedAt}ms, " +
                "${seen.size} distinct files, ${matched.size} matching"
        )
    }

    private fun toStreamSource(msg: TelegramVideoMessage): StreamSource {
        val streamUrl = repository.getStreamUrl(msg.fileId)
        val displayName = if (msg.fileName == "Default_Name.mkv" || msg.fileName == "Default_Name.mp4")
            msg.caption.takeIf { it.isNotBlank() } ?: msg.fileName
        else msg.fileName
        val quality = parseQuality("${msg.fileName} ${msg.caption}")
        return StreamSource(
            source = displayName,
            addonName = "Telegram",
            addonId = "telegram_native",
            quality = quality,
            size = formatBytes(msg.fileSize),
            sizeBytes = msg.fileSize,
            url = streamUrl,
            infoHash = null,
            fileIdx = null,
            behaviorHints = com.arflix.tv.data.model.StreamBehaviorHints(
                notWebReady = false,
                filename = msg.fileName,
                videoSize = msg.fileSize
            ),
            subtitles = emptyList(),
            sources = emptyList(),
            description = msg.caption.takeIf { it.isNotBlank() }
        )
    }

    private fun sortSources(sources: Collection<StreamSource>, langCode: String): List<StreamSource> =
        sources.sortedWith(
            compareByDescending<StreamSource> { langCode == "he" && matcher.isHebrew(it.source) }
                .thenByDescending { qualityTier(it.quality) }
                .thenByDescending { it.sizeBytes ?: 0L }
        )

    private fun friendlyError(raw: String?): String {
        if (raw == null) return context.getString(R.string.telegram_search_failed)
        val waitSeconds = raw.removePrefix("FLOOD_WAIT_").toIntOrNull()
        return if (waitSeconds != null)
            context.getString(R.string.telegram_too_many_searches, waitSeconds)
        else
            context.getString(R.string.telegram_error_raw, raw)
    }

    /** A toast — unless a player is open: nothing about a background search belongs over playback. */
    private fun notifyUser(message: String) {
        if (activePlayers.get() > 0) {
            Log.i(TAG, "Not shown during playback: $message")
            return
        }
        showToast(message)
    }

    private fun showToast(message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun qualityTier(quality: String): Int = when (quality) {
        "4K" -> 6
        "1080p" -> 5
        "720p" -> 4
        "480p" -> 3
        "360p" -> 2
        "CAM" -> 1
        "SCR" -> 1
        else -> 0
    }

    /** The names a title is known by. [original] is the native name from TMDB. */
    private data class ResolvedTitles(
        val english: String? = null,
        val localized: String? = null,
        val original: String? = null
    )

    private suspend fun fetchTitles(imdbId: String, isMovie: Boolean, langCode: String): ResolvedTitles {
        if (imdbId.isBlank()) return ResolvedTitles()
        return try {
            val findResult = tmdbApi.findByExternalId(imdbId, Constants.TMDB_API_KEY)
            val findItem = if (isMovie) findResult.movieResults.firstOrNull()
                           else findResult.tvResults.firstOrNull()
            val tmdbId = findItem?.id ?: return ResolvedTitles()
            // Always fetch English explicitly — the HTTP interceptor may inject the user's
            // content language into all TMDB calls, so findItem.title isn't always English.
            val englishMovie = if (isMovie)
                tmdbApi.getMovieDetails(tmdbId, Constants.TMDB_API_KEY, language = "en") else null
            val englishTv = if (isMovie) null
                else tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY, language = "en")
            val englishTitle = (englishMovie?.title ?: englishTv?.name.orEmpty())
                .takeIf { it.isNotBlank() }
            // Native name (e.g. Hebrew for an Israeli show). TMDB returns it regardless of the
            // requested language, so it is the only reliable source when no translation exists —
            // a localized request then just returns the English name again.
            val originalTitle = (englishMovie?.originalTitle ?: englishTv?.originalName)
                ?.takeIf { it.isNotBlank() }
            // Fetch localized title only when the user's language is not English
            val localizedTitle = if (langCode != "en") {
                if (isMovie)
                    tmdbApi.getMovieDetails(tmdbId, Constants.TMDB_API_KEY, language = langCode).title
                        .takeIf { it.isNotBlank() }
                else
                    tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY, language = langCode).name
                        .takeIf { it.isNotBlank() }
            } else null
            ResolvedTitles(english = englishTitle, localized = localizedTitle, original = originalTitle)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            Log.w(TAG, "Failed to fetch titles for $imdbId: ${e.message}")
            ResolvedTitles()
        }
    }

    private fun parseQuality(raw: String): String {
        val t = raw.lowercase().replace(' ', '.')
        fun has(vararg xs: String) = xs.any { it in t }
        return when {
            has("dvdscr", "screener", ".scr.")                          -> "SCR"
            has(".cam.", "camrip", "hdcam", "hdts", "telesync")         -> "CAM"
            has("360", "36o")                                           -> "360p"
            has("480", "48o")                                           -> "480p"
            has("720", "72o")                                           -> "720p"
            has("1080", "1o8o", "108o", "1o80", ".fhd.")               -> "1080p"
            has("2160", "216o", ".4k.", ".uhd.", "ultrahd")             -> "4K"
            else -> "Unknown"
        }
    }

    private fun formatBytes(bytes: Long): String = when {
        bytes <= 0 -> ""
        bytes >= 1_000_000_000 -> "%.2f GB".format(bytes / 1_000_000_000.0)
        bytes >= 1_000_000 -> "%.1f MB".format(bytes / 1_000_000.0)
        else -> "%.0f KB".format(bytes / 1_000.0)
    }
}
