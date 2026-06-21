package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.WatchmodeApi
import com.arflix.tv.util.RuntimeApiKeys
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Test
import java.nio.file.Files

class MediaRepositoryApiKeyGuardTest {

    @After
    fun tearDown() {
        RuntimeApiKeys.clearTmdbApiKey()
    }

    @Test
    fun `getHomeCategories does not call TMDB when API key is missing`() = runTest {
        RuntimeApiKeys.clearTmdbApiKey()
        val tmdbApi = mockk<TmdbApi>()
        val repository = mediaRepository(tmdbApi)

        val categories = repository.getHomeCategories()

        assertTrue(categories.isEmpty())
        coVerify(exactly = 0) { tmdbApi.getTrendingMovies(any(), any(), any()) }
        coVerify(exactly = 0) { tmdbApi.getTrendingTv(any(), any(), any()) }
        coVerify(exactly = 0) {
            tmdbApi.discoverTv(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
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
        )
    }
}
