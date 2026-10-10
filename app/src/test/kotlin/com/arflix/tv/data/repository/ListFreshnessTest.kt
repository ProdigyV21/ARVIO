package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.api.TraktIds
import com.arflix.tv.data.api.TraktPublicListItem
import com.arflix.tv.data.api.TraktShowInfo
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.CopyOnWriteArrayList

class ListFreshnessTest {
    @Test fun `marker holds for one window and moves with the next`() {
        val start = 7 * LIST_REFRESH_MS
        assertEquals(listFreshnessMarker(start), listFreshnessMarker(start + LIST_REFRESH_MS - 1))
        assertEquals(listFreshnessMarker(start) + 1, listFreshnessMarker(start + LIST_REFRESH_MS))
    }

    @Test fun `marker is appended as its own query parameter`() {
        val now = 3 * LIST_REFRESH_MS
        assertEquals("https://example.test/list/json?_=3", withListFreshness("https://example.test/list/json", now))
        assertEquals("https://example.test/list/json?a=1&_=3", withListFreshness("https://example.test/list/json?a=1", now))
    }

    @Test fun `trakt list rows ask for the current window`() = runTest {
        val trakt = mockk<TraktApi>()
        coEvery { trakt.getUserListItems(any(), any(), any(), any(), "movies", any(), any(), any(), any()) } returns emptyList()
        coEvery { trakt.getUserListItems(any(), any(), any(), any(), "shows", any(), 1, any(), any()) } returns listOf(
            TraktPublicListItem(type = "show", show = TraktShowInfo("Some Show", 2020, TraktIds(tmdb = 501)))
        )
        coEvery { trakt.getUserListItems(any(), any(), any(), any(), "shows", any(), 2, any(), any()) } returns emptyList()
        val catalog = CatalogConfig(
            id = "custom_list", title = "List", sourceType = CatalogSourceType.TRAKT,
            sourceUrl = "https://trakt.tv/users/someone/lists/some-list",
            sourceRef = "trakt_user:someone:some-list"
        )

        val page = repository(trakt = trakt).loadCustomCatalogPage(catalog, 0, 20)

        assertEquals(listOf(501), page.items.map { it.id })
        coVerify { trakt.getUserListItems(any(), any(), "someone", "some-list", "shows", any(), 1, any(), match { it != null }) }
        coVerify(exactly = 0) { trakt.getUserListItems(any(), any(), any(), any(), any(), any(), any(), any(), null) }
    }

    @Test fun `mdblist rows ask for the current window`() = runTest {
        val requested = CopyOnWriteArrayList<String>()
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            requested += chain.request().url.toString()
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK")
                .body("""[{"id":501,"mediatype":"show"}]""".toResponseBody("application/json".toMediaType()))
                .build()
        }.build()
        val catalog = CatalogConfig(
            id = "custom_md", title = "List", sourceType = CatalogSourceType.MDBLIST,
            sourceUrl = "https://mdblist.com/lists/someone/some-list",
            sourceRef = "mdblist:https://mdblist.com/lists/someone/some-list"
        )

        val page = repository(client = client).loadCustomCatalogPage(catalog, 0, 20)

        assertEquals(listOf(501), page.items.map { it.id })
        assertTrue(requested.toString(), requested.any {
            it.matches(Regex("""https://mdblist\.com/lists/someone/some-list/json\?_=\d+"""))
        })
    }

    private fun repository(
        trakt: TraktApi = mockk(relaxed = true),
        client: OkHttpClient = mockk(relaxed = true)
    ) = spyk(MediaRepository(
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), trakt,
        client, mockk(relaxed = true), mockk(relaxed = true)
    )).also { repository ->
        coEvery { repository.getMovieDetails(any()) } answers { MediaItem(firstArg(), "Movie ${firstArg<Int>()}") }
        coEvery { repository.getTvDetails(any()) } answers { MediaItem(firstArg(), "Series ${firstArg<Int>()}", mediaType = MediaType.TV) }
    }
}
