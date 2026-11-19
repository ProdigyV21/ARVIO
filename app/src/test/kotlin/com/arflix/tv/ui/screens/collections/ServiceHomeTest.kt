package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.ui.screens.home.isRankedHomeRow
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ServiceHomeTest {
    private val today = LocalDate.of(2026, 11, 17)

    private fun service(vararg providers: Int) = CatalogConfig(
        id = "collection_service_test", title = "Test+", sourceType = CatalogSourceType.PREINSTALLED,
        isPreinstalled = true, kind = CatalogKind.COLLECTION, collectionGroup = CollectionGroupKind.SERVICE,
        collectionSources = providers.flatMap { id ->
            listOf("movie", "series").map { type ->
                CollectionSourceConfig(kind = CollectionSourceKind.TMDB_WATCH_PROVIDER, mediaType = type,
                    tmdbWatchProviderId = id, watchRegion = "US", sortBy = "popularity.desc")
            }
        }
    )

    private fun ServiceHome.Row.only(): CollectionSourceConfig = catalog.collectionSources.single()

    @Test fun `a service page has Top 10s, new, originals, top rated, then genre rows`() {
        val ids = ServiceHome.rows(service(8), today).map { it.id }
        assertEquals(
            listOf(ServiceHome.TOP10_SERIES_ROW_ID, ServiceHome.TOP10_MOVIES_ROW_ID, ServiceHome.NEW_ROW_ID,
                ServiceHome.ORIGINALS_ROW_ID, ServiceHome.TOP_RATED_SERIES_ROW_ID, ServiceHome.TOP_RATED_MOVIES_ROW_ID) +
                ServiceHome.genres.map { ServiceHome.GENRE_ROW_PREFIX + it.key },
            ids
        )
    }

    @Test fun `the Top 10 series are this service's popular recent shows, ten and ranked`() {
        val row = ServiceHome.rows(service(8), today).first { it.id == ServiceHome.TOP10_SERIES_ROW_ID }
        val source = row.only()
        assertEquals("series", source.mediaType)
        assertEquals("popularity.desc", source.sortBy)
        assertEquals(
            mapOf("with_watch_providers" to "8", "watch_region" to "US", "first_air_date.gte" to "2023-11-17",
                "first_air_date.lte" to "2026-11-17", "vote_count.gte" to "20"),
            source.discoverParams
        )
        assertEquals(10, row.limit)
        assertNull(row.viewAll)
        assertTrue(isRankedHomeRow(Category(row.id, title = "10 translated", items = emptyList())))
    }

    @Test fun `new arrivals mix movies and series, newest first and released`() {
        val row = ServiceHome.rows(service(8), today).first { it.id == ServiceHome.NEW_ROW_ID }
        assertEquals(CollectionGroupKind.GENRE, row.catalog.collectionGroup)
        val (movies, series) = row.catalog.collectionSources
        assertEquals("primary_release_date.desc", movies.sortBy)
        assertEquals("2026-11-17", movies.discoverParams?.get("primary_release_date.lte"))
        assertEquals("first_air_date.desc", series.sortBy)
        assertEquals("2026-11-17", series.discoverParams?.get("first_air_date.lte"))
        assertFalse(isRankedHomeRow(Category(row.id, title = "New", items = emptyList())))
    }

    @Test fun `originals come from the service's network and top rated asks for many votes`() {
        val rows = ServiceHome.rows(service(8), today)
        val originals = rows.first { it.id == ServiceHome.ORIGINALS_ROW_ID }.only()
        assertEquals("213", originals.discoverParams?.get("with_networks"))
        assertNull(originals.discoverParams?.get("with_watch_providers"))
        val topRated = rows.first { it.id == ServiceHome.TOP_RATED_MOVIES_ROW_ID }.only()
        assertEquals("vote_average.desc", topRated.sortBy)
        assertEquals("500", topRated.discoverParams?.get("vote_count.gte"))
    }

    @Test fun `a service with two provider ids queries both`() {
        val rows = ServiceHome.rows(service(2303, 2616), today)
        assertEquals("2303|2616", rows.first().only().discoverParams?.get("with_watch_providers"))
        assertEquals("4330", rows.first { it.id == ServiceHome.ORIGINALS_ROW_ID }.only().discoverParams?.get("with_networks"))
    }

    @Test fun `a service without a known network has no originals row`() {
        assertTrue(ServiceHome.rows(service(99), today).none { it.id == ServiceHome.ORIGINALS_ROW_ID })
    }

    @Test fun `genre rows use each type's genre and open the grid on it`() {
        val rows = ServiceHome.rows(service(8), today).associateBy { it.id }
        val action = rows.getValue(ServiceHome.GENRE_ROW_PREFIX + "action")
        assertEquals(listOf("28", "10759"), action.catalog.collectionSources.map { it.discoverParams?.get("with_genres") })
        assertEquals(ServiceHome.ViewAll(action.id, CollectionTab.MOVIES), action.viewAll)
        val horror = rows.getValue(ServiceHome.GENRE_ROW_PREFIX + "horror")
        assertEquals(listOf("movie"), horror.catalog.collectionSources.map { it.mediaType })
        val reality = rows.getValue(ServiceHome.GENRE_ROW_PREFIX + "reality")
        assertEquals(ServiceHome.ViewAll(reality.id, CollectionTab.SERIES), reality.viewAll)
    }

    @Test fun `every genre a row opens is one the grid's filter offers`() {
        ServiceHome.genres.forEach { genre ->
            genre.movieGenreId?.let { assertTrue("movie $it", it in ServiceGenreFilter.movieGenreIds) }
            genre.seriesGenreId?.let { assertTrue("series $it", it in ServiceGenreFilter.seriesGenreIds) }
        }
    }

    @Test fun `only pages built purely from providers get the Home layout`() {
        assertTrue(ServiceHome.supports(service(8)))
        assertFalse(ServiceHome.supports(service(8).copy(collectionSources = listOf(
            CollectionSourceConfig(kind = CollectionSourceKind.TMDB_COLLECTION, mediaType = "movie", tmdbCollectionId = 1)
        ))))
    }

    @Test fun `the series filter keeps series rows and each mixed row's series query`() {
        val rows = ServiceHome.rows(service(8), today, ServiceHome.Filter.SERIES)
        assertEquals(
            listOf(ServiceHome.TOP10_SERIES_ROW_ID, ServiceHome.NEW_ROW_ID, ServiceHome.ORIGINALS_ROW_ID,
                ServiceHome.TOP_RATED_SERIES_ROW_ID) +
                ServiceHome.genres.filter { it.seriesGenreId != null }.map { ServiceHome.GENRE_ROW_PREFIX + it.key },
            rows.map { it.id }
        )
        assertTrue(rows.all { row -> row.catalog.collectionSources.all { it.mediaType == "series" } })
        assertTrue(rows.all { it.catalog.collectionGroup == CollectionGroupKind.SERVICE })
        val comedy = rows.first { it.id == ServiceHome.GENRE_ROW_PREFIX + "comedy" }
        assertEquals(ServiceHome.ViewAll(comedy.id, CollectionTab.SERIES, ServiceHome.Filter.SERIES), comedy.viewAll)
    }

    @Test fun `the Filter button opens the grid on the applied filter's tab, with no genre`() {
        assertEquals(ServiceHome.ViewAll(null, CollectionTab.MOVIES), ServiceHome.grid(ServiceHome.Filter.ALL))
        assertEquals(ServiceHome.ViewAll(null, CollectionTab.SERIES, ServiceHome.Filter.SERIES), ServiceHome.grid(ServiceHome.Filter.SERIES))
        assertEquals(ServiceHome.ViewAll(null, CollectionTab.MOVIES, ServiceHome.Filter.MOVIES), ServiceHome.grid(ServiceHome.Filter.MOVIES))
    }

    @Test fun `the movies filter keeps movie rows only, with their own cache identity`() {
        val all = ServiceHome.rows(service(8), today).associateBy { it.id }
        val rows = ServiceHome.rows(service(8), today, ServiceHome.Filter.MOVIES)
        assertTrue(rows.none { it.id == ServiceHome.ORIGINALS_ROW_ID || it.id == ServiceHome.TOP10_SERIES_ROW_ID })
        assertTrue(rows.none { it.id == ServiceHome.GENRE_ROW_PREFIX + "reality" })
        assertTrue(rows.all { row -> row.catalog.collectionSources.all { it.mediaType == "movie" } })
        val newMovies = rows.first { it.id == ServiceHome.NEW_ROW_ID }
        assertTrue(newMovies.catalog.id != all.getValue(ServiceHome.NEW_ROW_ID).catalog.id)
        assertEquals(CollectionTab.MOVIES, newMovies.viewAll?.tab)
    }

    @Test fun `view all resolves the exact row under every filter`() {
        val service = service(2303, 2616)
        ServiceHome.Filter.entries.forEach { filter ->
            val rows = ServiceHome.rows(service, today, filter)
            rows.forEach { row ->
                val resolved = ServiceHome.gridRow(service, today, row.id, filter)
                assertEquals(row.takeIf { it.viewAll != null }, resolved)
            }
        }
        assertNull(ServiceHome.gridRow(service, today, "unknown", ServiceHome.Filter.ALL))
        assertNull(ServiceHome.gridRow(service, today, ServiceHome.ORIGINALS_ROW_ID, ServiceHome.Filter.MOVIES))
    }
}
