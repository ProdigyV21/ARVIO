package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.api.TmdbTvDetails
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.MediaType
import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test

class MdbListCollectionPagingTest {
    @Test fun `large public list pages beyond 96 while hydrating only requested cards`() = runBlocking {
        val api = mockk<TmdbApi>()
        val client = mockk<OkHttpClient>()
        val call = mockk<Call>()
        val body = (1..19000).joinToString(",", "[", ",{\"id\":20001,\"mediatype\":\"show\"}]") {
            "{\"id\":$it,\"mediatype\":\"movie\"}"
        }
        every { client.newCall(any()) } returns call
        every { call.execute() } answers {
            Response.Builder().request(Request.Builder().url("https://mdblist.com/lists/test/large/json").build())
                .protocol(Protocol.HTTP_1_1).code(200).message("OK").body(body.toResponseBody()).build()
        }
        coEvery { api.getMovieDetails(any(), any(), any(), any()) } answers {
            TmdbMovieDetails(id = firstArg(), title = "Movie ${firstArg<Int>()}")
        }
        coEvery { api.getTvDetails(any(), any(), any(), any()) } answers {
            TmdbTvDetails(id = firstArg(), name = "Show")
        }
        val repo = MediaRepository(mockk(relaxed = true), api, mockk(), mockk(), client, mockk(), mockk())
        val catalog = CatalogConfig(id = "large", title = "Large list", sourceType = CatalogSourceType.PREINSTALLED,
            kind = CatalogKind.COLLECTION, collectionSources = listOf(CollectionSourceConfig(
                kind = CollectionSourceKind.MDBLIST_PUBLIC, mdblistSlug = "test/large"
            )))
        val first = repo.loadCollectionCatalogPage(catalog, 0, 8, MediaType.MOVIE)
        assertEquals((1..8).toList(), first.items.map { it.id })
        assertTrue(first.hasMore)
        coVerify(exactly = 8) { api.getMovieDetails(any(), any(), any(), any()) }

        val crossing = repo.loadCollectionCatalogPage(catalog, 92, 12, MediaType.MOVIE)
        assertEquals((93..104).toList(), crossing.items.map { it.id })
        assertTrue(crossing.hasMore)
        val last = repo.loadCollectionCatalogPage(catalog, 18992, 12, MediaType.MOVIE)
        assertEquals((18993..19000).toList(), last.items.map { it.id })
        assertFalse(last.hasMore)
        val shows = repo.loadCollectionCatalogPage(catalog, 0, 8, MediaType.TV)
        assertEquals(listOf(20001), shows.items.map { it.id })
        assertFalse(shows.hasMore)
        verify(exactly = 1) { call.execute() }
        coVerify(exactly = 28) { api.getMovieDetails(any(), any(), any(), any()) }
    }
}
