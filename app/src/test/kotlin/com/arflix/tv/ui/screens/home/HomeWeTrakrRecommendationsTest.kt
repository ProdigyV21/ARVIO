package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.MediaRepository.CategoryPageResult
import com.arflix.tv.data.repository.wetrakr.WeTrakrLists
import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class HomeWeTrakrRecommendationsTest {
    private val shows = CatalogConfig(WeTrakrLists.RECS_SHOWS_ID, "My shows", CatalogSourceType.PREINSTALLED, isPreinstalled = true)
    private val movies = CatalogConfig(WeTrakrLists.RECS_MOVIES_ID, "My films", CatalogSourceType.PREINSTALLED, isPreinstalled = true)
    private val trending = CatalogConfig("trending_movies", "Trending", CatalogSourceType.MDBLIST, isPreinstalled = true)
    private val item = MediaItem(10, "Example")
    private val full = CategoryPageResult(listOf(item), hasMore = false)
    private val empty = CategoryPageResult(emptyList(), hasMore = false)

    @Test fun `optional account rows never create startup skeletons`() {
        val skeletons = listOf(trending, shows, movies).filter(::homeCatalogHasSkeleton)
        assertEquals(listOf(trending), skeletons)
        assertFalse(isWeTrakrRecommendationRow("custom_wetrakr_list"))
    }

    @Test fun `TV loads both recommendation pages and preserves saved row names and order`() = runBlocking {
        val requested = mutableListOf<Pair<String, Int>>()
        val result = loadWeTrakrRecommendationRows(listOf(movies, trending, shows)) { id, page ->
            requested += id to page
            full
        }
        assertEquals(setOf(movies.id to 1, shows.id to 1), requested.toSet())
        assertEquals(2, requested.size)
        assertEquals(listOf("My films", "My shows"), result.map { it.title })
        assertEquals(listOf(item), result.first().items)
    }

    @Test fun `disconnected or empty recommendations create no visible rows`() = runBlocking {
        assertTrue(loadWeTrakrRecommendationRows(listOf(shows, movies)) { _, _ -> empty }.isEmpty())
    }

    @Test fun `a failed recommendation lookup does not discard the other row`() = runBlocking {
        val result = loadWeTrakrRecommendationRows(listOf(shows, movies)) { id, _ ->
            if (id == shows.id) throw IOException("Fixture provider unavailable")
            full
        }
        assertEquals(listOf(movies.id), result.map { it.id })
    }

    @Test fun `hidden recommendation catalogs make no provider requests`() = runBlocking {
        val result = loadWeTrakrRecommendationRows(listOf(trending)) { _, _ -> error("Must not request hidden rows") }
        assertTrue(result.isEmpty())
    }

    @Test fun `a cancelled Home load does not publish account recommendations`() = runBlocking {
        try {
            loadWeTrakrRecommendationRows(listOf(shows)) { _, _ -> throw CancellationException("Profile changed") }
            fail("Cancellation must propagate")
        } catch (_: CancellationException) { }
    }

    @Test fun `empty mobile result removes a stale row without changing the other rows`() {
        val other = Category(trending.id, trending.title, listOf(item))
        val cached = listOf(other, Category(shows.id, shows.title, listOf(item)), Category(movies.id, movies.title, listOf(item)))
        val result = removeEmptyWeTrakrRecommendationRow(cached, shows.id)
        assertEquals(listOf(trending.id, movies.id), result.map { it.id })
        assertSame(other, result.first())
        assertEquals(3, cached.size)
    }

    @Test fun `empty mobile result removes an old loading placeholder too`() {
        val placeholder = Category(shows.id, shows.title, listOf(item.copy(isPlaceholder = true)))
        assertTrue(removeEmptyWeTrakrRecommendationRow(listOf(placeholder), shows.id).isEmpty())
    }

    @Test fun `recommendation cleanup never removes an unrelated catalog`() {
        val rows = listOf(Category(trending.id, trending.title, listOf(item)))
        assertSame(rows, removeEmptyWeTrakrRecommendationRow(rows, trending.id))
    }

    @Test fun `a completed TV reload cannot resurrect stale recommendations`() = runBlocking {
        val oldRows = listOf(Category(shows.id, shows.title, listOf(item)))
        val fresh = loadWeTrakrRecommendationRows(listOf(shows, movies)) { _, _ -> empty }
        assertTrue(preserveExtendedCatalogRows(fresh, oldRows).isEmpty())
    }

    @Test fun `shorter fresh recommendations replace the old single page instead of preserving stale titles`() {
        val fresh = Category(shows.id, shows.title, listOf(item))
        val cached = fresh.copy(items = listOf(item, item.copy(id = 20, title = "Already watched")))
        assertSame(fresh, preserveExtendedCatalogRows(listOf(fresh), listOf(cached)).single())
    }
}
