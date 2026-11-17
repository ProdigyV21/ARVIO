package com.arflix.tv.data.repository.wetrakr

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import com.google.gson.JsonParser
import io.mockk.*
import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WeTrakrRecommendationsRepositoryTest {
    private val api = mockk<WeTrakrRepository>(relaxed = true)
    private val lists = WeTrakrLists(api)
    private val repository = spyk(MediaRepository(mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true)))

    @Before fun setup() {
        MediaRepository::class.java.getDeclaredField("weTrakrLists\$delegate").apply { isAccessible = true }
            .set(repository, lazyOf(lists))
        every { api.isConnected(any()) } returns true
        coEvery { repository.getMovieDetails(any()) } answers { MediaItem(firstArg(), "Film ${firstArg<Int>()}") }
        coEvery { repository.getTvDetails(any()) } answers { MediaItem(firstArg(), "Show ${firstArg<Int>()}", mediaType = MediaType.TV) }
    }

    @Test fun `disconnected account returns an empty terminal page without a request`() = runBlocking {
        every { api.isConnected(any()) } returns false
        val result = repository.loadHomeCategoryPage(WeTrakrLists.RECS_MOVIES_ID, 1)
        assertTrue(result.items.isEmpty())
        assertFalse(result.hasMore)
        coVerify(exactly = 0) { api.request(any(), any(), any(), any()) }
    }

    @Test fun `movie recommendations are resolved and hydrated through the shared Home loader`() = runBlocking {
        coEvery { api.request(any(), any(), any(), any()) } returns (
            JsonParser.parseString("""[{"ids":{"tmdb":10}},{"ids":{"tmdb":20}},{"ids":{"tmdb":10}}]""") to emptyMap())
        val result = repository.loadHomeCategoryPage(WeTrakrLists.RECS_MOVIES_ID, 1)
        assertEquals(listOf(10, 20), result.items.map { it.id })
        assertFalse(result.hasMore)
        coVerify { api.request("/discover/recommended?filter_type=movie&limit=40", any(), any(), any()) }
    }

    @Test fun `show recommendations use the show endpoint and TV metadata`() = runBlocking {
        coEvery { api.request(any(), any(), any(), any()) } returns (
            JsonParser.parseString("""{"shows":[{"ids":{"tmdb":30}}]}""") to emptyMap())
        val result = repository.loadHomeCategoryPage(WeTrakrLists.RECS_SHOWS_ID, 1)
        assertEquals(30, result.items.single().id)
        assertEquals(MediaType.TV, result.items.single().mediaType)
        coVerify { api.request("/discover/recommended?filter_type=show&limit=40", any(), any(), any()) }
    }

    @Test fun `a terminal recommendation page never refetches the provider`() = runBlocking {
        assertTrue(repository.loadHomeCategoryPage(WeTrakrLists.RECS_SHOWS_ID, 2).items.isEmpty())
        coVerify(exactly = 0) { api.request(any(), any(), any(), any()) }
    }

    @Test fun `provider failure returns no recommendation row`() = runBlocking {
        coEvery { api.request(any(), any(), any(), any()) } throws IOException("Fixture unavailable")
        assertTrue(repository.loadHomeCategoryPage(WeTrakrLists.RECS_SHOWS_ID, 1).items.isEmpty())
    }

    @Test fun `one failed metadata item does not discard valid recommendations`() = runBlocking {
        coEvery { api.request(any(), any(), any(), any()) } returns (
            JsonParser.parseString("""[{"ids":{"tmdb":10}},{"ids":{"tmdb":20}}]""") to emptyMap())
        coEvery { repository.getMovieDetails(10) } throws IOException("Fixture missing metadata")
        assertEquals(listOf(20), repository.loadHomeCategoryPage(WeTrakrLists.RECS_MOVIES_ID, 1).items.map { it.id })
    }

    @Test fun `profile cancellation is not treated as an empty provider result`() = runBlocking {
        coEvery { api.request(any(), any(), any(), any()) } throws CancellationException("Profile changed")
        try {
            repository.loadHomeCategoryPage(WeTrakrLists.RECS_SHOWS_ID, 1)
            fail("Cancellation must propagate")
        } catch (_: CancellationException) { }
    }
}
