package com.arflix.tv.data.repository

import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StreamAvailabilityRepositoryTest {
    @Test
    fun `movie summary uses imdb id and summarizes streams`() = runTest {
        val resolver = FakeStreamSummaryResolver(
            movieStreams = listOf(stream("Movie 2160p Atmos 44 GB", "4K", "44 GB"))
        )
        val repository = StreamAvailabilityRepository(resolver)

        val summary = repository.movieSummary(" tt1234567 ", "Movie", 2026)

        assertEquals("tt1234567", resolver.lastMovieImdbId)
        assertEquals("Movie", resolver.lastMovieTitle)
        assertEquals(2026, resolver.lastMovieYear)
        assertEquals("4K", summary?.bestResolution)
        assertEquals("Atmos", summary?.bestAudioTag)
        assertEquals(1, summary?.sourceCount)
    }

    @Test
    fun `series summary uses supplied season episode and metadata`() = runTest {
        val resolver = FakeStreamSummaryResolver(
            episodeStreams = listOf(stream("Show S02E04 1080p 5.1 8 GB", "1080p", "8 GB"))
        )
        val repository = StreamAvailabilityRepository(resolver)

        val summary = repository.episodeSummary(
            imdbId = "tt7654321",
            title = "Show",
            year = 2025,
            season = 2,
            episode = 4,
            tmdbId = 99,
            genreIds = listOf(16, 10765),
            originalLanguage = "ja"
        )

        assertEquals("tt7654321", resolver.lastEpisodeImdbId)
        assertEquals("Show", resolver.lastEpisodeTitle)
        assertEquals(2, resolver.lastSeason)
        assertEquals(4, resolver.lastEpisode)
        assertEquals(99, resolver.lastTmdbId)
        assertEquals(listOf(16, 10765), resolver.lastGenreIds)
        assertEquals("ja", resolver.lastOriginalLanguage)
        assertEquals("1080p", summary?.bestResolution)
        assertEquals("5.1", summary?.bestAudioTag)
    }

    @Test
    fun `blank or null imdb returns null without resolver call`() = runTest {
        val resolver = FakeStreamSummaryResolver()
        val repository = StreamAvailabilityRepository(resolver)

        assertNull(repository.movieSummary(null, "Movie", 2026))
        assertNull(
            repository.episodeSummary(
                imdbId = " ",
                title = "Show",
                year = 2025,
                season = 1,
                episode = 1,
                tmdbId = 99,
                genreIds = emptyList(),
                originalLanguage = "en"
            )
        )

        assertEquals(0, resolver.movieCallCount)
        assertEquals(0, resolver.episodeCallCount)
    }

    @Test
    fun `season and episode are coerced to S1E1 fallback if non positive`() = runTest {
        val resolver = FakeStreamSummaryResolver(
            episodeStreams = listOf(stream("Show S01E01 720p 2 GB", "720p", "2 GB"))
        )
        val repository = StreamAvailabilityRepository(resolver)

        val summary = repository.episodeSummary(
            imdbId = "tt1111111",
            title = "Show",
            year = 2024,
            season = 0,
            episode = -2,
            tmdbId = null,
            genreIds = emptyList(),
            originalLanguage = null
        )

        assertEquals(1, resolver.lastSeason)
        assertEquals(1, resolver.lastEpisode)
        assertEquals("720p", summary?.bestResolution)
    }

    @Test
    fun `empty streams returns null`() = runTest {
        val resolver = FakeStreamSummaryResolver(movieStreams = emptyList())
        val repository = StreamAvailabilityRepository(resolver)

        assertNull(repository.movieSummary("tt2222222", "Movie", 2024))
        assertEquals(1, resolver.movieCallCount)
    }

    @Test
    fun `timeout returns null and does not cache a later result`() = runTest {
        val resolver = FakeStreamSummaryResolver(
            movieStreams = listOf(stream("Movie 2160p 20 GB", "4K", "20 GB")),
            delayMs = 100L
        )
        val repository = StreamAvailabilityRepository(resolver)

        assertNull(repository.movieSummary("tt3333333", "Movie", 2024, timeoutMs = 10L))

        resolver.delayMs = 0L
        val summary = repository.movieSummary("tt3333333", "Movie", 2024, timeoutMs = 10L)

        assertEquals(2, resolver.movieCallCount)
        assertEquals("4K", summary?.bestResolution)
    }

    @Test
    fun `resolver that catches cancellation after timeout returns null and does not cache`() = runTest {
        var nowMs = 0L
        val resolver = FakeStreamSummaryResolver(
            movieStreams = listOf(stream("Movie 2160p 20 GB", "4K", "20 GB")),
            delayMs = 100L,
            ignoreCancellation = true,
            onCancellation = { nowMs = 11L }
        )
        val repository = StreamAvailabilityRepository(resolver, clock = { nowMs })

        assertNull(repository.movieSummary("tt3333334", "Movie", 2024, timeoutMs = 10L))

        resolver.delayMs = 0L
        resolver.ignoreCancellation = false
        nowMs = 11L
        val summary = repository.movieSummary("tt3333334", "Movie", 2024, timeoutMs = 10L)

        assertEquals(2, resolver.movieCallCount)
        assertEquals("4K", summary?.bestResolution)
    }

    @Test
    fun `episode cache key includes metadata that can affect stream selection`() = runTest {
        val resolver = FakeStreamSummaryResolver(
            episodeStreams = listOf(stream("Show S01E01 720p 2 GB", "720p", "2 GB"))
        )
        val repository = StreamAvailabilityRepository(resolver)

        val first = repository.episodeSummary(
            imdbId = "tt5555555",
            title = "Original Show",
            year = 2023,
            season = 1,
            episode = 1,
            tmdbId = 10,
            genreIds = listOf(16, 10765),
            originalLanguage = "JA"
        )

        resolver.episodeStreams = listOf(stream("Different Show S01E01 2160p 18 GB", "4K", "18 GB"))
        val second = repository.episodeSummary(
            imdbId = "tt5555555",
            title = "Different Show",
            year = 2024,
            season = 1,
            episode = 1,
            tmdbId = 20,
            genreIds = listOf(10759, 18),
            originalLanguage = " en "
        )

        assertEquals(2, resolver.episodeCallCount)
        assertEquals("720p", first?.bestResolution)
        assertEquals("4K", second?.bestResolution)
    }

    @Test
    fun `repeated movie call with same id returns cached summary and resolver called once`() = runTest {
        val resolver = FakeStreamSummaryResolver(
            movieStreams = listOf(stream("Movie 1080p 8 GB", "1080p", "8 GB"))
        )
        val repository = StreamAvailabilityRepository(resolver)

        val first = repository.movieSummary("tt4444444", "Movie", 2024)
        resolver.movieStreams = listOf(stream("Movie 2160p 20 GB", "4K", "20 GB"))
        val second = repository.movieSummary("tt4444444", "Movie", 2024)

        assertEquals(1, resolver.movieCallCount)
        assertEquals("1080p", first?.bestResolution)
        assertEquals(first, second)
    }

    private class FakeStreamSummaryResolver(
        var movieStreams: List<StreamSource> = emptyList(),
        var episodeStreams: List<StreamSource> = emptyList(),
        var delayMs: Long = 0L,
        var ignoreCancellation: Boolean = false,
        var onCancellation: () -> Unit = {}
    ) : StreamSummaryResolver {
        var movieCallCount = 0
        var episodeCallCount = 0
        var lastMovieImdbId: String? = null
        var lastMovieTitle: String? = null
        var lastMovieYear: Int? = null
        var lastEpisodeImdbId: String? = null
        var lastEpisodeTitle: String? = null
        var lastEpisodeYear: Int? = null
        var lastSeason: Int? = null
        var lastEpisode: Int? = null
        var lastTmdbId: Int? = null
        var lastGenreIds: List<Int>? = null
        var lastOriginalLanguage: String? = null

        override suspend fun movieStreams(imdbId: String, title: String, year: Int?): List<StreamSource> {
            movieCallCount += 1
            lastMovieImdbId = imdbId
            lastMovieTitle = title
            lastMovieYear = year
            if (delayMs > 0L) cancellableDelay()
            return movieStreams
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
            episodeCallCount += 1
            lastEpisodeImdbId = imdbId
            lastEpisodeTitle = title
            lastEpisodeYear = year
            lastSeason = season
            lastEpisode = episode
            lastTmdbId = tmdbId
            lastGenreIds = genreIds
            lastOriginalLanguage = originalLanguage
            if (delayMs > 0L) cancellableDelay()
            return episodeStreams
        }

        private suspend fun cancellableDelay() {
            try {
                delay(delayMs)
            } catch (exception: CancellationException) {
                onCancellation()
                if (!ignoreCancellation) throw exception
            }
        }
    }

    private fun stream(source: String, quality: String, size: String) = StreamSource(
        source = source,
        addonName = "AIOStreams",
        addonId = "aiostreams",
        quality = quality,
        size = size,
        url = "https://example.com/${source.hashCode()}",
        behaviorHints = StreamBehaviorHints(cached = true, filename = source)
    )
}
