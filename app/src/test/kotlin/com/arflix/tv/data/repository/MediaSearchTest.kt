package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbListResponse
import com.arflix.tv.data.api.TmdbMediaItem
import com.arflix.tv.data.model.MediaType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class MediaSearchTest {
    private val api = mockk<TmdbApi>()
    private val repository = MediaRepository(mockk(relaxed = true), api, mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true))

    @Test fun oneRequestReturnsTitlesAndPeopleWithoutFetchingCreditsOrImages() = runBlocking {
        val show = TmdbMediaItem(id = 1, name = "Loki", mediaType = "tv", posterPath = "/loki.jpg")
        val movie = TmdbMediaItem(id = 2, title = "Other", mediaType = "movie")
        coEvery { api.searchMulti(any(), "Loki", "en-US", 1) } returns TmdbListResponse(results = listOf(
            show, movie, show, TmdbMediaItem(id = 3, name = "Actor", mediaType = "person", knownFor = listOf(show)),
            TmdbMediaItem(id = 4, name = "No Credits Yet", mediaType = "person")
        ))
        val results = repository.searchWithPeople(" Loki ")
        assertEquals(listOf(1, 2), results.items.map { it.id })
        assertEquals(MediaType.TV, results.items.first().mediaType)
        assertEquals(listOf(3, 4), results.people.map { it.personId })
        assertEquals(1, results.people.first().items.single().id)
        assertTrue(results.people.last().items.isEmpty())
        coVerify(exactly = 1) { api.searchMulti(any(), any(), any(), any()) }
        coVerify(exactly = 0) { api.getPersonDetails(any(), any(), any(), any()) }
    }

    @Test fun blankQueryDoesNotMakeNetworkRequests() = runBlocking {
        assertTrue(repository.searchWithPeople("  ").items.isEmpty())
        coVerify(exactly = 0) { api.searchMulti(any(), any(), any(), any()) }
    }
}
