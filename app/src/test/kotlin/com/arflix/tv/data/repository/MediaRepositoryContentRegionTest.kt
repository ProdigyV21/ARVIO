package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbListResponse
import com.arflix.tv.data.api.TmdbMediaItem
import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.api.TmdbWatchProvider
import com.arflix.tv.data.api.TmdbWatchProviderRegion
import com.arflix.tv.data.api.TmdbWatchProvidersResponse
import com.arflix.tv.data.api.WatchmodeApi
import com.arflix.tv.data.api.WatchmodeListTitlesResponse
import com.arflix.tv.data.api.WatchmodeTitle
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.RuntimeApiKeys
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import java.nio.file.Files
import org.junit.Test

class MediaRepositoryContentRegionTest {

    @After
    fun tearDown() {
        RuntimeApiKeys.clearTmdbApiKey()
        RuntimeApiKeys.clearWatchmodeApiKey()
    }

    @Test
    fun `collection watch-provider sources default to Sweden`() {
        val services = MediaRepository.buildPreinstalledDefaults()
            .filter { it.collectionGroup == CollectionGroupKind.SERVICE }

        assertTrue("Expected service collections", services.isNotEmpty())
        services
            .flatMap { it.collectionSources }
            .filter { it.kind == CollectionSourceKind.TMDB_WATCH_PROVIDER }
            .forEach { source ->
                assertEquals("SE", source.watchRegion)
            }
    }

    @Test
    fun `Swedish services include Watchmode catalog sources`() {
        val services = MediaRepository.buildPreinstalledDefaults()
            .filter { it.collectionGroup == CollectionGroupKind.SERVICE }

        assertTrue("Expected service collections", services.isNotEmpty())
        listOf("Netflix", "Prime Video", "Disney+", "Max", "Apple TV+", "SkyShowtime", "Viaplay")
            .forEach { serviceTitle ->
                val service = services.firstOrNull { it.title == serviceTitle }
                assertTrue("Missing service $serviceTitle", service != null)
                assertTrue(
                    "$serviceTitle must have a Watchmode source",
                    service!!.collectionSources.any { it.kind == CollectionSourceKind.WATCHMODE_SOURCE }
                )
            }
    }

    @Test
    fun `Watchmode collection source maps Swedish titles to TMDB refs`() = runTest {
        RuntimeApiKeys.setTmdbApiKey("test-key")
        RuntimeApiKeys.setWatchmodeApiKey("watchmode-key")
        val tmdbApi = mockk<TmdbApi>()
        coEvery { tmdbApi.getMovieDetails(123, any(), any()) } returns com.arflix.tv.data.api.TmdbMovieDetails(
            id = 123,
            title = "Swedish Netflix Movie"
        )
        val watchmodeApi = mockk<WatchmodeApi>()
        coEvery {
            watchmodeApi.listTitles(
                apiKey = any(),
                types = "movie",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 1,
                limit = any()
            )
        } returns WatchmodeListTitlesResponse(
            titles = listOf(
                WatchmodeTitle(
                    id = 1,
                    title = "Swedish Netflix Movie",
                    tmdbId = 123,
                    tmdbType = "movie",
                    type = "movie"
                )
            ),
            page = 1,
            totalPages = 1,
            totalResults = 1
        )

        val repository = mediaRepository(tmdbApi, watchmodeApi)
        val netflix = MediaRepository.buildPreinstalledDefaults().first { it.title == "Netflix" }

        val page = repository.loadCollectionCatalogPage(netflix, offset = 0, limit = 1)

        assertEquals(1, page.items.size)
        assertEquals(123, page.items.first().id)
        coVerify {
            watchmodeApi.listTitles(
                apiKey = "watchmode-key",
                types = "movie",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 1,
                limit = any()
            )
        }
    }

    @Test
    fun `Watchmode collection source reads multiple pages for fresh Swedish refs`() = runTest {
        RuntimeApiKeys.setTmdbApiKey("test-key")
        RuntimeApiKeys.setWatchmodeApiKey("watchmode-key")
        val tmdbApi = mockk<TmdbApi>()
        coEvery { tmdbApi.getMovieDetails(123, any(), any()) } returns TmdbMovieDetails(
            id = 123,
            title = "First Swedish Netflix Movie"
        )
        coEvery { tmdbApi.getMovieDetails(456, any(), any()) } returns TmdbMovieDetails(
            id = 456,
            title = "Second Swedish Netflix Movie"
        )
        val watchmodeApi = mockk<WatchmodeApi>()
        coEvery {
            watchmodeApi.listTitles(
                apiKey = any(),
                types = "movie",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 1,
                limit = any()
            )
        } returns WatchmodeListTitlesResponse(
            titles = listOf(
                WatchmodeTitle(id = 1, title = "First", tmdbId = 123, tmdbType = "movie", type = "movie")
            ),
            page = 1,
            totalPages = 2,
            totalResults = 2
        )
        coEvery {
            watchmodeApi.listTitles(
                apiKey = any(),
                types = "movie",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 2,
                limit = any()
            )
        } returns WatchmodeListTitlesResponse(
            titles = listOf(
                WatchmodeTitle(id = 2, title = "Second", tmdbId = 456, tmdbType = "movie", type = "movie")
            ),
            page = 2,
            totalPages = 2,
            totalResults = 2
        )

        val repository = mediaRepository(tmdbApi, watchmodeApi)
        val netflix = MediaRepository.buildPreinstalledDefaults().first { it.title == "Netflix" }

        val firstPage = repository.loadCollectionCatalogPage(netflix, offset = 0, limit = 1)
        val expandedPage = repository.loadCollectionCatalogPage(netflix, offset = 0, limit = 2)

        assertEquals(listOf(123), firstPage.items.map { it.id })
        assertEquals(listOf(123, 456), expandedPage.items.map { it.id })
        coVerify {
            watchmodeApi.listTitles(
                apiKey = "watchmode-key",
                types = "movie",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 2,
                limit = any()
            )
        }
    }

    @Test
    fun `Watchmode disk cache persists Swedish refs across repository instances`() = runTest {
        RuntimeApiKeys.setTmdbApiKey("test-key")
        RuntimeApiKeys.setWatchmodeApiKey("watchmode-key")
        val cacheDir = Files.createTempDirectory("watchmode-cache-test").toFile()
        val cache = WatchmodeCatalogCache(cacheDir)
        val cacheKey = WatchmodeCatalogCache.keyFor(
            sourceId = 203,
            mediaType = "movie",
            region = "SE",
            sourceTypes = null,
            sortBy = "popularity_desc"
        )
        cache.write(
            cacheKey,
            WatchmodeCatalogCache.Snapshot(
                refs = listOf(MediaType.MOVIE to 123),
                fetchedAtMs = System.currentTimeMillis(),
                complete = true
            )
        )
        val tmdbApi = mockk<TmdbApi>()
        coEvery { tmdbApi.getMovieDetails(123, any(), any()) } returns TmdbMovieDetails(
            id = 123,
            title = "Cached Swedish Netflix Movie"
        )
        val watchmodeApi = mockk<WatchmodeApi>(relaxed = true)
        val repository = mediaRepository(tmdbApi, watchmodeApi, cache)
        val netflix = MediaRepository.buildPreinstalledDefaults()
            .first { it.title == "Netflix" }
            .copy(
                collectionSources = MediaRepository.buildPreinstalledDefaults()
                    .first { it.title == "Netflix" }
                    .collectionSources
                    .filter {
                        it.kind == CollectionSourceKind.WATCHMODE_SOURCE &&
                            it.mediaType == "movie"
                    }
            )

        val page = repository.loadCollectionCatalogPage(netflix, offset = 0, limit = 1)

        assertEquals(listOf(123), page.items.map { it.id })
        coVerify(exactly = 0) {
            watchmodeApi.listTitles(
                apiKey = any(),
                types = any(),
                regions = any(),
                sourceIds = any(),
                sourceTypes = any(),
                sortBy = any(),
                page = any(),
                limit = any()
            )
        }
    }

    @Test
    fun `service collections ignore non-regioned fallbacks when Swedish sources exist`() = runTest {
        RuntimeApiKeys.setTmdbApiKey("test-key")
        RuntimeApiKeys.setWatchmodeApiKey("watchmode-key")
        val tmdbApi = mockk<TmdbApi>()
        coEvery { tmdbApi.getMovieDetails(123, any(), any()) } returns TmdbMovieDetails(
            id = 123,
            title = "Swedish Service Movie"
        )
        coEvery { tmdbApi.getMovieDetails(999, any(), any()) } returns TmdbMovieDetails(
            id = 999,
            title = "Global Fallback Movie"
        )
        val watchmodeApi = mockk<WatchmodeApi>()
        coEvery {
            watchmodeApi.listTitles(
                apiKey = any(),
                types = "movie",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 1,
                limit = any()
            )
        } returns WatchmodeListTitlesResponse(
            titles = listOf(
                WatchmodeTitle(id = 1, title = "Swedish", tmdbId = 123, tmdbType = "movie", type = "movie")
            ),
            page = 1,
            totalPages = 1,
            totalResults = 1
        )
        val catalog = CatalogConfig(
            id = "service_test",
            title = "Service Test",
            sourceType = CatalogSourceType.PREINSTALLED,
            kind = CatalogKind.COLLECTION,
            collectionGroup = CollectionGroupKind.SERVICE,
            collectionSources = listOf(
                CollectionSourceConfig(
                    kind = CollectionSourceKind.WATCHMODE_SOURCE,
                    mediaType = "movie",
                    watchRegion = "SE",
                    watchmodeSourceId = 203,
                    sortBy = "popularity_desc"
                ),
                CollectionSourceConfig(
                    kind = CollectionSourceKind.CURATED_IDS,
                    curatedRefs = listOf("movie:999")
                )
            )
        )
        val repository = mediaRepository(tmdbApi, watchmodeApi)

        val page = repository.loadCollectionCatalogPage(catalog, offset = 0, limit = 5)

        assertEquals(listOf(123), page.items.map { it.id })
    }

    @Test
    fun `watch provider collection loads Swedish TMDB region by default`() = runTest {
        RuntimeApiKeys.setTmdbApiKey("test-key")
        val tmdbApi = mockk<TmdbApi>()
        coEvery {
            tmdbApi.discoverMovies(
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
        } returns TmdbListResponse(results = listOf(TmdbMediaItem(id = 10, title = "Movie")))
        coEvery { tmdbApi.getMovieDetails(10, any(), any()) } returns TmdbMovieDetails(
            id = 10,
            title = "Movie"
        )

        val repository = mediaRepository(tmdbApi)
        val netflix = MediaRepository.buildPreinstalledDefaults().first { it.title == "Netflix" }

        val page = repository.loadCollectionCatalogPage(netflix, offset = 0, limit = 1)

        assertTrue(page.items.isNotEmpty())
        coVerify {
            tmdbApi.discoverMovies(
                apiKey = any(),
                genres = any(),
                crew = any(),
                sortBy = any(),
                minVoteCount = any(),
                keywords = any(),
                originalLanguage = any(),
                year = any(),
                releaseDateGte = any(),
                releaseDateLte = any(),
                watchProviders = 8,
                watchRegion = "SE",
                language = "sv-SE",
                page = any()
            )
        }
    }

    @Test
    fun `streaming services prefer Sweden before local device and US`() = runTest {
        RuntimeApiKeys.setTmdbApiKey("test-key")
        val tmdbApi = mockk<TmdbApi>()
        coEvery { tmdbApi.getMovieWatchProviders(42, any()) } returns TmdbWatchProvidersResponse(
            id = 42,
            results = mapOf(
                "US" to TmdbWatchProviderRegion(
                    flatrate = listOf(TmdbWatchProvider(providerId = 8, providerName = "Netflix", displayPriority = 1))
                ),
                "SE" to TmdbWatchProviderRegion(
                    flatrate = listOf(TmdbWatchProvider(providerId = 76, providerName = "Viaplay", displayPriority = 1))
                )
            )
        )
        val repository = mediaRepository(tmdbApi)

        val result = repository.getStreamingServices(MediaType.MOVIE, 42)

        assertEquals("SE", result?.region)
        assertEquals("Viaplay", result?.services?.firstOrNull()?.name)
    }

    @Test
    fun `streaming catalog refresh warms Watchmode refs for Swedish service sources`() = runTest {
        RuntimeApiKeys.setWatchmodeApiKey("watchmode-key")
        val watchmodeApi = mockk<WatchmodeApi>()
        coEvery {
            watchmodeApi.listTitles(
                apiKey = any(),
                types = "movie",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 1,
                limit = 5
            )
        } returns WatchmodeListTitlesResponse(
            titles = listOf(
                WatchmodeTitle(id = 1, title = "Movie", tmdbId = 100, tmdbType = "movie", type = "movie")
            ),
            page = 1,
            totalPages = 1,
            totalResults = 1
        )
        coEvery {
            watchmodeApi.listTitles(
                apiKey = any(),
                types = "tv_series",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 1,
                limit = 5
            )
        } returns WatchmodeListTitlesResponse(
            titles = listOf(
                WatchmodeTitle(id = 2, title = "Series", tmdbId = 200, tmdbType = "tv", type = "tv_series")
            ),
            page = 1,
            totalPages = 1,
            totalResults = 1
        )
        val repository = mediaRepository(
            tmdbApi = mockk(relaxed = true),
            watchmodeApi = watchmodeApi
        )
        val catalog = watchmodeServiceCatalog()

        val refreshedRefs = repository.refreshStreamingServiceCatalogRefs(
            catalogs = listOf(catalog),
            limitPerSource = 5
        )

        assertEquals(2, refreshedRefs)
        coVerify {
            watchmodeApi.listTitles(
                apiKey = "watchmode-key",
                types = "movie",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 1,
                limit = 5
            )
        }
        coVerify {
            watchmodeApi.listTitles(
                apiKey = "watchmode-key",
                types = "tv_series",
                regions = "SE",
                sourceIds = "203",
                sourceTypes = null,
                sortBy = "popularity_desc",
                page = 1,
                limit = 5
            )
        }
    }

    @Test
    fun `streaming catalog refresh skips Watchmode when API key is missing`() = runTest {
        RuntimeApiKeys.clearWatchmodeApiKey()
        val watchmodeApi = mockk<WatchmodeApi>(relaxed = true)
        val repository = mediaRepository(
            tmdbApi = mockk(relaxed = true),
            watchmodeApi = watchmodeApi
        )

        val refreshedRefs = repository.refreshStreamingServiceCatalogRefs(
            catalogs = listOf(watchmodeServiceCatalog()),
            limitPerSource = 5
        )

        assertEquals(0, refreshedRefs)
        coVerify(exactly = 0) {
            watchmodeApi.listTitles(
                apiKey = any(),
                types = any(),
                regions = any(),
                sourceIds = any(),
                sourceTypes = any(),
                sortBy = any(),
                page = any(),
                limit = any()
            )
        }
    }

    private fun watchmodeServiceCatalog(): CatalogConfig {
        return CatalogConfig(
            id = "service_refresh_test",
            title = "Netflix",
            sourceType = CatalogSourceType.PREINSTALLED,
            kind = CatalogKind.COLLECTION,
            collectionGroup = CollectionGroupKind.SERVICE,
            collectionSources = listOf(
                CollectionSourceConfig(
                    kind = CollectionSourceKind.WATCHMODE_SOURCE,
                    mediaType = "movie",
                    watchRegion = "SE",
                    watchmodeSourceId = 203,
                    sortBy = "popularity_desc"
                ),
                CollectionSourceConfig(
                    kind = CollectionSourceKind.WATCHMODE_SOURCE,
                    mediaType = "series",
                    watchRegion = "SE",
                    watchmodeSourceId = 203,
                    sortBy = "popularity_desc"
                ),
                CollectionSourceConfig(
                    kind = CollectionSourceKind.TMDB_WATCH_PROVIDER,
                    mediaType = "movie",
                    watchRegion = "SE",
                    tmdbWatchProviderId = 8
                )
            )
        )
    }

    private fun mediaRepository(
        tmdbApi: TmdbApi,
        watchmodeApi: WatchmodeApi = mockk(relaxed = true),
        watchmodeCatalogCache: WatchmodeCatalogCache = WatchmodeCatalogCache(
            Files.createTempDirectory("watchmode-cache").toFile()
        )
    ): MediaRepository {
        return MediaRepository(
            tmdbApi = tmdbApi,
            watchmodeApi = watchmodeApi,
            traktRepository = mockk(relaxed = true),
            traktApi = mockk(relaxed = true),
            okHttpClient = OkHttpClient(),
            streamRepository = mockk(relaxed = true),
            homeServerRepository = mockk(relaxed = true),
            watchmodeCatalogCache = watchmodeCatalogCache
        )
    }
}
