package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbTvDetails
import com.arflix.tv.data.api.TmdbTvSeason
import com.arflix.tv.data.api.TmdbVideo
import com.arflix.tv.data.api.TmdbVideosResponse
import com.arflix.tv.data.api.WatchmodeApi
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.RuntimeApiKeys
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.nio.file.Files

class MediaRepositoryTrailerTest {

    @Before
    fun setUp() {
        RuntimeApiKeys.setTmdbApiKey("test-key")
    }

    @After
    fun tearDown() {
        RuntimeApiKeys.clearTmdbApiKey()
    }

    @Test
    fun `getTrailerKey uses season trailer when tv show level only has fallback clip`() = runTest {
        val tmdbApi = mockk<TmdbApi>()
        coEvery { tmdbApi.getVideos("tv", 123, any(), null) } returns TmdbVideosResponse(
            results = listOf(video(key = "show-clip", type = "Clip"))
        )
        coEvery { tmdbApi.getTvDetails(123, any(), null) } returns TmdbTvDetails(
            id = 123,
            name = "Series",
            seasons = listOf(
                TmdbTvSeason(seasonNumber = 1, episodeCount = 8),
                TmdbTvSeason(seasonNumber = 2, episodeCount = 10)
            )
        )
        coEvery { tmdbApi.getTvSeasonVideos(123, 2, any(), null) } returns TmdbVideosResponse(
            results = listOf(video(key = "season-two-trailer", type = "Trailer", official = true, name = "Official Trailer"))
        )

        val repository = mediaRepository(tmdbApi)

        assertEquals("season-two-trailer", repository.getTrailerKey(MediaType.TV, 123))
    }

    @Test
    fun `getTrailerKey does not fetch tv seasons when show level trailer exists`() = runTest {
        val tmdbApi = mockk<TmdbApi>()
        coEvery { tmdbApi.getVideos("tv", 789, any(), null) } returns TmdbVideosResponse(
            results = listOf(video(key = "show-trailer", type = "Trailer", official = true))
        )

        val repository = mediaRepository(tmdbApi)

        assertEquals("show-trailer", repository.getTrailerKey(MediaType.TV, 789))
        coVerify(exactly = 0) { tmdbApi.getTvDetails(789, any(), any()) }
    }

    @Test
    fun `getTrailerKey scans tv seasons once when fallback clip is selected`() = runTest {
        val tmdbApi = mockk<TmdbApi>()
        coEvery { tmdbApi.getVideos("tv", 456, any(), null) } returns TmdbVideosResponse(
            results = emptyList()
        )
        coEvery { tmdbApi.getTvDetails(456, any(), null) } returns TmdbTvDetails(
            id = 456,
            name = "Series",
            seasons = listOf(
                TmdbTvSeason(seasonNumber = 1, episodeCount = 8),
                TmdbTvSeason(seasonNumber = 2, episodeCount = 10)
            )
        )
        coEvery { tmdbApi.getTvSeasonVideos(456, 2, any(), null) } returns TmdbVideosResponse(
            results = listOf(video(key = "season-two-clip", type = "Clip"))
        )
        coEvery { tmdbApi.getTvSeasonVideos(456, 1, any(), null) } returns TmdbVideosResponse(
            results = emptyList()
        )

        val repository = mediaRepository(tmdbApi)

        assertEquals("season-two-clip", repository.getTrailerKey(MediaType.TV, 456))
        coVerify(exactly = 1) { tmdbApi.getTvDetails(456, any(), null) }
        coVerify(exactly = 1) { tmdbApi.getTvSeasonVideos(456, 2, any(), null) }
        coVerify(exactly = 1) { tmdbApi.getTvSeasonVideos(456, 1, any(), null) }
    }

    private fun mediaRepository(tmdbApi: TmdbApi): MediaRepository {
        return MediaRepository(
            tmdbApi = tmdbApi,
            watchmodeApi = mockk<WatchmodeApi>(relaxed = true),
            traktRepository = mockk(relaxed = true),
            traktApi = mockk(relaxed = true),
            okHttpClient = OkHttpClient(),
            streamRepository = mockk(relaxed = true),
            homeServerRepository = mockk(relaxed = true),
            watchmodeCatalogCache = WatchmodeCatalogCache(
                Files.createTempDirectory("watchmode-cache").toFile()
            )
        ).apply {
            contentLanguage = null
        }
    }

    private fun video(
        key: String,
        type: String,
        official: Boolean = false,
        name: String = type
    ): TmdbVideo {
        return TmdbVideo(
            id = key,
            key = key,
            name = name,
            site = "YouTube",
            type = type,
            official = official,
            iso6391 = "en"
        )
    }
}
