package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.ui.components.movieGenreNameRes
import com.arflix.tv.ui.components.tvGenreNameRes
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
}
