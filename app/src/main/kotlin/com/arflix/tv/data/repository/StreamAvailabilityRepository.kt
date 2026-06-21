package com.arflix.tv.data.repository

import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.stream.StreamAvailabilitySummary
import com.arflix.tv.data.stream.summarizeStreamAvailability
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.withTimeoutOrNull

interface StreamSummaryResolver {
    suspend fun movieStreams(imdbId: String, title: String, year: Int?): List<StreamSource>

    suspend fun episodeStreams(
        imdbId: String,
        title: String,
        year: Int?,
        season: Int,
        episode: Int,
        tmdbId: Int?,
        genreIds: List<Int>,
        originalLanguage: String?
    ): List<StreamSource>
}

@Singleton
class StreamRepositorySummaryResolver @Inject constructor(
    private val streamRepository: StreamRepository
) : StreamSummaryResolver {
    override suspend fun movieStreams(imdbId: String, title: String, year: Int?): List<StreamSource> {
        return streamRepository.resolveMovieStreams(
            imdbId = imdbId,
            title = title,
            year = year
        ).streams
    }

    override suspend fun episodeStreams(
        imdbId: String,
        title: String,
        year: Int?,
        season: Int,
        episode: Int,
        tmdbId: Int?,
        genreIds: List<Int>,
        originalLanguage: String?
    ): List<StreamSource> {
        return streamRepository.resolveEpisodeStreams(
            imdbId = imdbId,
            season = season,
            episode = episode,
            tmdbId = tmdbId,
            genreIds = genreIds,
            originalLanguage = originalLanguage,
            title = title
        ).streams
    }
}

@Singleton
class StreamAvailabilityRepository @Inject constructor(
    private val resolver: StreamSummaryResolver
) {
    internal constructor(
        resolver: StreamSummaryResolver,
        clock: () -> Long
    ) : this(resolver) {
        this.clock = clock
    }

    private val lock = Any()
    private val cache = LinkedHashMap<String, StreamAvailabilitySummary>(MAX_CACHE_ITEMS, 0.75f, true)
    private var clock: () -> Long = System::currentTimeMillis

    suspend fun movieSummary(
        imdbId: String?,
        title: String,
        year: Int?,
        timeoutMs: Long = 7_000L
    ): StreamAvailabilitySummary? {
        val id = imdbId?.trim().orEmpty()
        if (id.isBlank()) return null

        val key = "movie:$id"
        cached(key)?.let { return it }

        return summarizeWithinDeadline(timeoutMs) {
            resolver.movieStreams(
                imdbId = id,
                title = title,
                year = year
            )
        }?.also { summary -> cache(key, summary) }
    }

    suspend fun episodeSummary(
        imdbId: String?,
        title: String,
        year: Int?,
        season: Int,
        episode: Int,
        tmdbId: Int?,
        genreIds: List<Int>,
        originalLanguage: String?,
        timeoutMs: Long = 7_000L
    ): StreamAvailabilitySummary? {
        val id = imdbId?.trim().orEmpty()
        if (id.isBlank()) return null

        val safeSeason = season.coerceAtLeast(1)
        val safeEpisode = episode.coerceAtLeast(1)
        val key = episodeCacheKey(
            imdbId = id,
            title = title,
            year = year,
            season = safeSeason,
            episode = safeEpisode,
            tmdbId = tmdbId,
            genreIds = genreIds,
            originalLanguage = originalLanguage
        )
        cached(key)?.let { return it }

        return summarizeWithinDeadline(timeoutMs) {
            resolver.episodeStreams(
                imdbId = id,
                title = title,
                year = year,
                season = safeSeason,
                episode = safeEpisode,
                tmdbId = tmdbId,
                genreIds = genreIds,
                originalLanguage = originalLanguage
            )
        }?.also { summary -> cache(key, summary) }
    }

    private suspend fun summarizeWithinDeadline(
        timeoutMs: Long,
        loadStreams: suspend () -> List<StreamSource>
    ): StreamAvailabilitySummary? {
        val startedAt = clock()
        val deadline = startedAt + timeoutMs

        return withTimeoutOrNull(timeoutMs) {
            val streams = loadStreams()
            val completedAt = clock()

            if (completedAt <= deadline) {
                summarizeStreamAvailability(streams = streams, loadedAtMs = completedAt)
            } else {
                null
            }
        }
    }

    private fun episodeCacheKey(
        imdbId: String,
        title: String,
        year: Int?,
        season: Int,
        episode: Int,
        tmdbId: Int?,
        genreIds: List<Int>,
        originalLanguage: String?
    ): String {
        val language = originalLanguage?.trim()?.lowercase(Locale.US).orEmpty()
        val genres = genreIds.sorted().joinToString(",")
        val titleYear = "${normalizeTitle(title)}:${year ?: ""}"
        return "series:$imdbId:$season:$episode:${tmdbId ?: ""}:$language:$genres:$titleYear"
    }

    private fun normalizeTitle(title: String): String {
        return title
            .trim()
            .lowercase(Locale.US)
            .replace(WHITESPACE, " ")
    }

    private fun cached(key: String): StreamAvailabilitySummary? = synchronized(lock) {
        cache[key]
    }

    private fun cache(key: String, summary: StreamAvailabilitySummary) = synchronized(lock) {
        cache[key] = summary
        while (cache.size > MAX_CACHE_ITEMS) {
            val oldestKey = cache.keys.firstOrNull() ?: return@synchronized
            cache.remove(oldestKey)
        }
    }

    private companion object {
        const val MAX_CACHE_ITEMS = 80
        val WHITESPACE = Regex("""\s+""")
    }
}
