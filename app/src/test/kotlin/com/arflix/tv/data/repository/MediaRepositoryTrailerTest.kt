package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbTvDetails
import com.arflix.tv.data.api.TmdbTvSeason
import com.arflix.tv.data.api.TmdbVideo
import com.arflix.tv.data.api.TmdbVideosResponse
import com.arflix.tv.data.model.MediaType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Test

class MediaRepositoryTrailerTest {

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

    private fun mediaRepository(tmdbApi: TmdbApi): MediaRepository {
        return MediaRepository(
            tmdbApi = tmdbApi,
            traktRepository = mockk(relaxed = true),
            traktApi = mockk(relaxed = true),
            okHttpClient = OkHttpClient(),
            streamRepository = mockk(relaxed = true),
            homeServerRepository = mockk(relaxed = true)
        )
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
