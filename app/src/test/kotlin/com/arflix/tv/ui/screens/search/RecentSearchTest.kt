package com.arflix.tv.ui.screens.search

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.MediaSearchResults
import com.arflix.tv.data.repository.RecentSearchRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.withRecentSearch
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RecentSearchTest {
    private val repository = mockk<MediaRepository>(relaxed = true)
    private val recent = mockk<RecentSearchRepository>(relaxed = true)
    private val saved = MutableStateFlow(listOf("Older"))
    private val store = ViewModelStore()
    private lateinit var model: SearchViewModel
    private val show = MediaItem(id = 2, title = "Some Show", mediaType = MediaType.TV)

    @Before fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        every { recent.recentSearches } returns saved
        coEvery { repository.getLogoUrl(any<MediaType>(), any()) } returns null
        coEvery { repository.searchWithPeople("Some Show", any()) } returns MediaSearchResults(listOf(show), emptyList())
        coEvery { repository.searchWithPeople("zzzz", any()) } returns MediaSearchResults(emptyList(), emptyList())
        model = SearchViewModel(repository, mockk<TraktRepository>(relaxed = true), recent)
        store.put("search", model)
    }

    @After fun tearDown() {
        val job = model.viewModelScope.coroutineContext[Job]
        try {
            store.clear()
            runBlocking { withTimeout(5_000) { job?.join() } }
        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test fun newestFirstWithoutCaseDuplicatesAndCapped() {
        assertEquals(listOf("Dune", "Alien"), withRecentSearch(listOf("Alien", "dune"), " Dune "))
        assertEquals(listOf("Alien"), withRecentSearch(listOf("Alien"), "   "))
        val full = (1..8).map { "q$it" }
        assertEquals(listOf("new") + full.take(7), withRecentSearch(full, "new"))
    }

    @Test fun slotsAreTheQueriesThenClear() {
        val queries = listOf("a", "b")
        assertEquals(3, recentSearchSlotCount(queries))
        assertEquals(RecentSearchSlot.Query("a"), recentSearchSlot(queries, 0))
        assertEquals(RecentSearchSlot.Query("b"), recentSearchSlot(queries, 1))
        assertEquals(RecentSearchSlot.Clear, recentSearchSlot(queries, 2))
        assertNull(recentSearchSlot(queries, 3))
        assertNull(recentSearchSlot(queries, -1))
        assertEquals(0, recentSearchSlotCount(emptyList()))
    }

    @Test fun typingAloneDoesNotSave() = runBlocking {
        model.updateQuery("Some Show"); model.search()
        awaitResults("Some Show")
        coVerify(exactly = 0) { recent.add(any()) }
    }

    @Test fun submittedQueryWithResultsIsSaved() = runBlocking {
        model.updateQuery("Some Show"); model.search()
        awaitResults("Some Show")
        model.rememberSearch()
        coVerify(exactly = 1) { recent.add("Some Show") }
    }

    @Test fun submitBeforeResultsSavesOnceTheyArrive() = runBlocking {
        val release = CompletableDeferred<Unit>()
        coEvery { repository.searchWithPeople("Some Show", any()) } coAnswers {
            release.await(); MediaSearchResults(listOf(show), emptyList())
        }
        model.updateQuery("Some Show"); model.search(); model.rememberSearch()
        coVerify(exactly = 0) { recent.add(any()) }
        release.complete(Unit)
        awaitResults("Some Show")
        awaitSaved("Some Show")
    }

    @Test fun queryWithoutResultsIsNotSaved() = runBlocking {
        model.updateQuery("zzzz"); model.search(); model.rememberSearch()
        awaitResults("zzzz")
        coVerify(exactly = 0) { recent.add(any()) }
    }

    @Test fun recentQueryRunsAtOnceAndMovesToTheFront() = runBlocking {
        assertEquals(listOf("Older"), withTimeout(5_000) { model.recentSearches.first { it.isNotEmpty() } })
        model.searchRecent("Some Show")
        assertEquals(listOf(show), awaitResults("Some Show").results)
        awaitSaved("Some Show")
    }

    @Test fun clearReachesTheStore() {
        model.clearRecentSearches()
        coVerify { recent.clear() }
    }

    private suspend fun awaitResults(query: String) = withTimeout(5_000) {
        model.uiState.first { it.query == query && !it.isLoading }
    }

    /** The save is launched after the results land, so it is polled for rather than checked once. */
    private suspend fun awaitSaved(query: String) = withTimeout(5_000) {
        while (true) {
            try { coVerify(exactly = 1) { recent.add(query) }; return@withTimeout }
            catch (_: AssertionError) { delay(20) }
        }
    }
}
