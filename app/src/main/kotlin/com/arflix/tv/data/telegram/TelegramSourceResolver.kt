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
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

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
        private const val SEARCH_TIMEOUT_MS = 20_000L
        private const val MAX_RESULTS = 100
        private const val CACHE_TTL_SHORT_MS = 2  * 60 * 60 * 1_000L
        private const val CACHE_TTL_LONG_MS  = 24 * 60 * 60 * 1_000L
    }

    private data class CacheEntry(val results: List<StreamSource>, val expiresAt: Long)
    private val cache = ConcurrentHashMap<String, CacheEntry>()

    private fun cacheKey(imdbId: String, title: String, season: Int?, episode: Int?) =
        if (imdbId.isNotBlank()) "$imdbId:${season ?: ""}:${episode ?: ""}"
        else "$title:${season ?: ""}:${episode ?: ""}"

    fun isEnabled(): Boolean = repository.isAuthenticated()

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
    ): List<StreamSource> {
        if (!repository.isAuthenticated()) return emptyList()

        val key = cacheKey(imdbId, title, season, episode)
        cache[key]?.let { entry ->
            if (System.currentTimeMillis() < entry.expiresAt) return entry.results
        }

        return try {
            val results = withTimeoutOrNull(SEARCH_TIMEOUT_MS) {
                resolveInternal(title, year, season, episode, imdbId, isMovie)
            } ?: emptyList<StreamSource>().also {
                Log.w(TAG, "Telegram search timed out for '$title'")
                showToast(context.getString(R.string.telegram_search_timed_out))
            }

            cache[key] = CacheEntry(results, System.currentTimeMillis() + cacheTtl(year, isMovie))
            results
        } catch (e: TelegramApiException) {
            Log.w(TAG, "Telegram API error for '$title': ${e.message}")
            showToast(friendlyError(e.message))
            emptyList()
        }
    }

    private suspend fun resolveInternal(
        title: String,
        year: Int?,
        season: Int?,
        episode: Int?,
        imdbId: String,
        isMovie: Boolean
    ): List<StreamSource> {
        val excludedIds = repository.getExcludedChatIds().first()
        // Read content language from SharedPreferences (same store SettingsViewModel writes to).
        // Avoids a DI cycle: StreamRepository → TelegramSourceResolver → MediaRepository → StreamRepository.
        val rawLang = context.getSharedPreferences("app_locale", android.content.Context.MODE_PRIVATE)
            .getString("locale_tag", "en-US") ?: "en-US"
        val langCode = rawLang.replace("iw", "he").substringBefore("-")
        val (englishTitle, localizedTitle) = fetchTitles(imdbId, isMovie, langCode)

        val queries = if (season != null && episode != null)
            matcher.buildSeriesQueries(title, season, episode, localizedTitle, englishTitle, langCode)
        else
            matcher.buildMovieQueries(title, year, localizedTitle, englishTitle)

        val seen = mutableSetOf<Pair<String, Long>>()
        val allMessages = mutableListOf<TelegramVideoMessage>()

        coroutineScope {
            queries.map { query ->
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
            }.awaitAll().flatten().forEach { msg ->
                if (seen.add(msg.fileName to msg.fileSize)) allMessages.add(msg)
            }
        }

        return allMessages
            .mapNotNull { msg ->
                val score = matcher.score(
                    fileName = msg.fileName,
                    caption = msg.caption,
                    title = title,
                    localizedTitle = localizedTitle,
                    englishTitle = englishTitle,
                    year = year,
                    season = season,
                    episode = episode
                )
                if (score < SCORE_THRESHOLD) null else msg
            }
            .map { msg ->
                val streamUrl = repository.getStreamUrl(msg.fileId)
                val displayName = if (msg.fileName == "Default_Name.mkv" || msg.fileName == "Default_Name.mp4")
                    msg.caption.takeIf { it.isNotBlank() } ?: msg.fileName
                else msg.fileName
                val quality = parseQuality("${msg.fileName} ${msg.caption}")
                StreamSource(
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
            .sortedWith(
                compareByDescending<StreamSource> { langCode == "he" && matcher.isHebrew(it.source) }
                    .thenByDescending { qualityTier(it.quality) }
                    .thenByDescending { it.sizeBytes ?: 0L }
            )
    }

    private fun friendlyError(raw: String?): String {
        if (raw == null) return context.getString(R.string.telegram_search_failed)
        val waitSeconds = raw.removePrefix("FLOOD_WAIT_").toIntOrNull()
        return if (waitSeconds != null)
            context.getString(R.string.telegram_too_many_searches, waitSeconds)
        else
            context.getString(R.string.telegram_error_raw, raw)
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

    private suspend fun fetchTitles(imdbId: String, isMovie: Boolean, langCode: String): Pair<String?, String?> {
        if (imdbId.isBlank()) return null to null
        return try {
            val findResult = tmdbApi.findByExternalId(imdbId, Constants.TMDB_API_KEY)
            val findItem = if (isMovie) findResult.movieResults.firstOrNull()
                           else findResult.tvResults.firstOrNull()
            val tmdbId = findItem?.id ?: return null to null
            // Always fetch English explicitly — the HTTP interceptor may inject the user's
            // content language into all TMDB calls, so findItem.title isn't always English.
            val englishTitle = if (isMovie)
                tmdbApi.getMovieDetails(tmdbId, Constants.TMDB_API_KEY, language = "en").title
                    .takeIf { it.isNotBlank() }
            else
                tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY, language = "en").name
                    .takeIf { it.isNotBlank() }
            // Fetch localized title only when the user's language is not English
            val localizedTitle = if (langCode != "en") {
                if (isMovie)
                    tmdbApi.getMovieDetails(tmdbId, Constants.TMDB_API_KEY, language = langCode).title
                        .takeIf { it.isNotBlank() }
                else
                    tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY, language = langCode).name
                        .takeIf { it.isNotBlank() }
            } else null
            englishTitle to localizedTitle
        } catch (e: Exception) {
            Log.w(TAG, "Failed to fetch titles for $imdbId: ${e.message}")
            null to null
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
