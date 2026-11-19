package com.arflix.tv.data.repository

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ClientCatalogTest {
    @Test fun `opaque and hierarchical delegates have identical semantics`() {
        for (scheme in listOf("trakt", "simkl", "hybrid", "homeserver")) {
            val action = if (scheme == "homeserver") "library" else "watchlist"
            assertEquals(ClientCatalogRequest.parse("$scheme:$action?type=series", null),
                ClientCatalogRequest.parse("$scheme://$action?type=series", null))
        }
        assertEquals(MediaType.MOVIE, ClientCatalogRequest.parse("TRAKT:WATCHLIST?type=movies", "series").mediaType)
        assertNull(ClientCatalogRequest.parse("trakt:watched?type=all", "movie").mediaType)
        assertEquals("simkl", ClientCatalogRequest.parse("hybrid:plan?tracker=simkl", null).provider)
    }

    @Test fun `malformed delegates and unsupported filters fail explicitly`() {
        for (uri in listOf("https://example.org", "trakt:unknown", "trakt:watchlist?type=anime", "trakt:watchlist?type=tv&type=movie", "trakt:watchlist#fragment")) {
            assertThrows(IllegalArgumentException::class.java) { ClientCatalogRequest.parse(uri, null) }
        }
        for (filter in listOf("[]", "null", "{\"unknown\":1}", "{\"minYear\":\"2000\"}", "{\"minRating\":11}", "{\"minYear\":2025,\"maxYear\":2020}", "{\"genreIds\":[-1]}")) {
            assertThrows(IllegalArgumentException::class.java) { ClientCatalogFilter.parse(filter) }
        }
    }

    @Test fun `filters combine typed predicates and reject missing metadata`() {
        val filter = ClientCatalogFilter.parse("""{"mediaType":"movie","genreIds":[28],"minYear":2000,"maxYear":2025,"minRating":6}""")
        val item = MediaItem(1, "Action", year = "2020", rating = "7.5", genreIds = listOf(28))
        assertTrue(filter.matches(item))
        assertFalse(filter.matches(item.copy(year = "")))
        assertFalse(filter.matches(item.copy(mediaType = MediaType.TV)))
        assertFalse(filter.matches(item.copy(genreIds = emptyList())))
    }

    @Test fun `filtered paging advances over nonmatches without losing later matches`() = runBlocking {
        val items = (1..8).map { MediaItem(it, "Title $it", year = if (it % 2 == 0) "2024" else "1999") }
        val fetch: suspend (Int, Int) -> MediaRepository.CategoryPageResult = { offset, size ->
            MediaRepository.CategoryPageResult(items.drop(offset).take(size), offset + size < items.size)
        }
        val filter = ClientCatalogFilter(minYear = 2020)
        val first = clientCatalogPage(0, 2, null, filter, fetch = fetch)
        assertEquals(listOf(2, 4), first.items.map { it.id })
        assertEquals(4, first.nextOffset)
        assertTrue(first.hasMore)
        val next = clientCatalogPage(first.nextOffset!!, 2, null, filter, fetch = fetch)
        assertEquals(listOf(6, 8), next.items.map { it.id })
        assertFalse(next.hasMore)
    }

    @Test fun `vod delegates parse recent categories and category filter safely`() {
        val recent = ClientCatalogRequest.parse("vod:recent?type=movie", fallbackType = null)
        assertEquals("vod", recent.provider)
        assertEquals("recent", recent.action)
        assertEquals(MediaType.MOVIE, recent.mediaType)
        assertNull(recent.category)

        val categorized = ClientCatalogRequest.parse("vod:categories?type=series&category=Action%20%26%20Sci-Fi", fallbackType = null)
        assertEquals("vod", categorized.provider)
        assertEquals("categories", categorized.action)
        assertEquals(MediaType.TV, categorized.mediaType)
        assertEquals("Action & Sci-Fi", categorized.category)
    }

    @Test fun `client filter parses availableOn vod and homeserver while rejecting unknown values`() {
        val vod = ClientCatalogFilter.parse("""{"availableOn":"vod","minYear":2021}""")
        assertEquals("vod", vod.availableOn)
        assertEquals(2021, vod.minYear)

        val hs = ClientCatalogFilter.parse("""{"availableOn":"homeserver"}""")
        assertEquals("homeserver", hs.availableOn)

        try {
            ClientCatalogFilter.parse("""{"availableOn":"unknown"}""")
            fail("Expected exception for invalid availableOn")
        } catch (_: IllegalArgumentException) {
        }
    }
}
