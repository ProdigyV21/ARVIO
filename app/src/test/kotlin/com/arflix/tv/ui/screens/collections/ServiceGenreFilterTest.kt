package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.ui.components.movieGenreNameRes
import com.arflix.tv.ui.components.tvGenreNameRes
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class ServiceGenreFilterTest {
    private fun provider(type: String) = CollectionSourceConfig(
        kind = CollectionSourceKind.TMDB_WATCH_PROVIDER, mediaType = type,
        tmdbWatchProviderId = 8, watchRegion = "US", sortBy = "popularity.desc"
    )

    private fun catalog(vararg sources: CollectionSourceConfig) = CatalogConfig(
        id = "collection_service_test", title = "Test", sourceType = CatalogSourceType.PREINSTALLED,
        isPreinstalled = true, kind = CatalogKind.COLLECTION, collectionSources = sources.toList()
    )

    @Test
    fun `only pages built purely from watch-provider queries are filterable`() {
        assertTrue(ServiceGenreFilter.supports(catalog(provider("movie"), provider("series"))))
        assertFalse(ServiceGenreFilter.supports(catalog()))
        assertFalse(ServiceGenreFilter.supports(catalog(provider("movie"),
            CollectionSourceConfig(kind = CollectionSourceKind.TMDB_COLLECTION, mediaType = "movie", tmdbCollectionId = 10))))
    }

    @Test
    fun `a genre narrows provider sources and no genre leaves them untouched`() {
        val sources = listOf(provider("series"))
        assertSame(sources, ServiceGenreFilter.apply(sources, null))
        val filtered = ServiceGenreFilter.apply(sources, 10764)
        assertEquals(10764, filtered.single().tmdbGenreId)
        assertEquals(8, filtered.single().tmdbWatchProviderId)
        assertNull(sources.single().tmdbGenreId)
    }

    @Test
    fun `each tab offers its own TMDB genre list and every id has a label`() {
        assertTrue(10764 in ServiceGenreFilter.genreIds(CollectionTab.SERIES))
        assertFalse(10764 in ServiceGenreFilter.genreIds(CollectionTab.MOVIES))
        assertTrue(80 in ServiceGenreFilter.genreIds(CollectionTab.MOVIES))
        ServiceGenreFilter.movieGenreIds.forEach { assertTrue("movie $it", movieGenreNameRes(it) != null) }
        ServiceGenreFilter.seriesGenreIds.forEach { assertTrue("series $it", tvGenreNameRes(it) != null) }
    }

    @Test
    fun `a service page lists newest first per tab and leaves other sources alone`() {
        val curated = CollectionSourceConfig(kind = CollectionSourceKind.CURATED_IDS, curatedRefs = listOf("movie:1"))
        val sources = ServiceGenreFilter.newestFirst(listOf(provider("movie"), provider("series"), curated))
        assertEquals("primary_release_date.desc", sources[0].sortBy)
        assertEquals("first_air_date.desc", sources[1].sortBy)
        assertSame(curated, sources[2])
    }

    @Test
    fun `newest first keeps the chosen genre and the template stays on popularity`() {
        val template = listOf(provider("series"))
        val page = ServiceGenreFilter.newestFirst(ServiceGenreFilter.apply(template, 10764)).single()
        assertEquals(10764, page.tmdbGenreId)
        assertEquals("first_air_date.desc", page.sortBy)
        // The Home row reads the stored template, which keeps its popularity order.
        assertEquals("popularity.desc", template.single().sortBy)
    }

    @Test
    fun `a dual provider gets one sorted query per tab with its genre and date cutoff`() {
        val movie = provider("movie").copy(tmdbWatchProviderId = 2303, tmdbGenreId = 28)
        val series = provider("series").copy(tmdbWatchProviderId = 2303, tmdbGenreId = 10759)
        val sources = listOf(movie, series, movie.copy(tmdbWatchProviderId = 2616), series.copy(tmdbWatchProviderId = 2616))
        val (movies, shows) = ServiceGenreFilter.newestFirst(sources, LocalDate.of(2026, 11, 18)).also {
            assertEquals(2, it.size)
        }
        assertEquals(CollectionSourceKind.TMDB_DISCOVER, movies.kind)
        assertEquals("primary_release_date.desc", movies.sortBy)
        assertEquals(mapOf(
            "with_watch_providers" to "2303|2616", "watch_region" to "US", "with_genres" to "28",
            "primary_release_date.lte" to "2026-11-18", "vote_count.gte" to "10"
        ), movies.discoverParams)
        assertEquals("first_air_date.desc", shows.sortBy)
        assertEquals(mapOf(
            "with_watch_providers" to "2303|2616", "watch_region" to "US", "with_genres" to "10759",
            "first_air_date.lte" to "2026-11-18", "vote_count.gte" to "10"
        ), shows.discoverParams)
        assertTrue(sources.all { it.sortBy == "popularity.desc" })
    }
}
