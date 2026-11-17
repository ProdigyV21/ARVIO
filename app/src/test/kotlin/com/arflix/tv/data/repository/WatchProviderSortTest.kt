package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbListResponse
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.MediaType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import java.time.LocalDate
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class WatchProviderSortTest {
    private val today = LocalDate.of(2026, 11, 16)

    @Test fun `movies sort by first release and series by first air date`() {
        assertEquals("primary_release_date.desc", WatchProviderSort.newestFor("movie"))
        assertEquals("first_air_date.desc", WatchProviderSort.newestFor("series"))
        assertEquals("first_air_date.desc", WatchProviderSort.newestFor("TV"))
        assertEquals("first_air_date.desc", WatchProviderSort.newestFor("show"))
    }

    @Test fun `a newest-first query stops at today and asks for votes`() {
        val expected = WatchProviderSort.Window(releasedOnOrBefore = "2026-11-16", minVoteCount = 10)
        assertEquals(expected, WatchProviderSort.window(WatchProviderSort.MOVIES_NEWEST, today))
        assertEquals(expected, WatchProviderSort.window(WatchProviderSort.SERIES_NEWEST, today))
    }

    @Test fun `a popularity query is left as it was`() {
        assertEquals(WatchProviderSort.Window(null, null), WatchProviderSort.window("popularity.desc", today))
    }

    @Test fun `a newest-first movie page asks TMDB for released, rated titles`() = runTest {
        val api = api()
        repository(api).loadCollectionCatalogPage(catalog(source("movie", WatchProviderSort.MOVIES_NEWEST)), 0, 8, MediaType.MOVIE)
        coVerify {
            api.discoverMovies(
                any(), genres = "28", sortBy = "primary_release_date.desc", minVoteCount = 10,
                primaryReleaseDateLte = LocalDate.now().toString(), watchProviders = 8, watchRegion = "US",
                crew = any(), minVoteAverage = any(), maxVoteAverage = any(), certificationCountry = any(),
                certificationLte = any(), keywords = any(), originalLanguage = any(), year = any(),
                releaseDateGte = any(), releaseDateLte = any(), primaryReleaseDateGte = any(),
                language = any(), page = any()
            )
        }
    }

    @Test fun `a newest-first series page asks TMDB for aired, rated shows`() = runTest {
        val api = api()
        repository(api).loadCollectionCatalogPage(catalog(source("series", WatchProviderSort.SERIES_NEWEST)), 0, 8, MediaType.TV)
        coVerify {
            api.discoverTv(
                any(), watchProviders = 8, watchRegion = "US", sortBy = "first_air_date.desc", genres = "28",
                minVoteCount = 10, firstAirDateLte = LocalDate.now().toString(), people = any(),
                originalLanguage = any(), year = any(), minVoteAverage = any(), maxVoteAverage = any(),
                keywords = any(), airDateGte = any(), airDateLte = any(), firstAirDateGte = any(),
                language = any(), page = any()
            )
        }
    }

    @Test fun `a popularity page sends no date or vote limit`() = runTest {
        val api = api()
        repository(api).loadCollectionCatalogPage(catalog(source("movie", "popularity.desc")), 0, 8, MediaType.MOVIE)
        coVerify {
            api.discoverMovies(
                any(), genres = "28", sortBy = "popularity.desc", minVoteCount = null, primaryReleaseDateLte = null,
                watchProviders = 8, watchRegion = "US", crew = any(), minVoteAverage = any(), maxVoteAverage = any(),
                certificationCountry = any(), certificationLte = any(), keywords = any(), originalLanguage = any(),
                year = any(), releaseDateGte = any(), releaseDateLte = any(), primaryReleaseDateGte = any(),
                language = any(), page = any()
            )
        }
    }

    private fun source(type: String, sort: String) = CollectionSourceConfig(
        kind = CollectionSourceKind.TMDB_WATCH_PROVIDER, mediaType = type, tmdbWatchProviderId = 8,
        watchRegion = "US", sortBy = sort, tmdbGenreId = 28
    )

    private fun catalog(source: CollectionSourceConfig) = CatalogConfig(
        id = "collection_service_test", title = "Test", sourceType = CatalogSourceType.PREINSTALLED,
        kind = CatalogKind.COLLECTION, collectionRailKey = "test", collectionSources = listOf(source)
    )

    /** Empty TMDB answers, so a page load ends after its discover call. */
    private fun api(): TmdbApi = mockk<TmdbApi>(relaxed = true).also { api ->
        coEvery {
            api.discoverMovies(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),
                any(), any(), any(), any(), any(), any(), any(), any(), any(), any())
        } returns TmdbListResponse()
        coEvery {
            api.discoverTv(any(), any(), any(), any(), any(), any(), any(), any(), any(),
                any(), any(), any(), any(), any(), any(), any(), any(), any())
        } returns TmdbListResponse()
    }

    private fun repository(api: TmdbApi) = MediaRepository(
        mockk(relaxed = true), api, mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true)
    )
}
