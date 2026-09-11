package com.arflix.tv.ui.screens.search

import androidx.lifecycle.ViewModelStore
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.PersonDetails
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.MediaSearchResults
import com.arflix.tv.data.repository.PersonMediaSearchResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {
    private val repository = mockk<MediaRepository>(relaxed = true)
    private val store = ViewModelStore()
    private lateinit var model: SearchViewModel
    private val loki = MediaItem(id = 2, title = "Loki", mediaType = MediaType.TV)

    @Before fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        coEvery { repository.getLogoUrl(any<MediaType>(), any()) } returns null
        model = SearchViewModel(repository)
        store.put("search", model)
    }

    @After fun tearDown() {
        store.clear()
        Dispatchers.resetMain()
    }

    @Test fun resultsPublishBeforeSlowLogosAndDoNotWaitForPeopleRequests() = runBlocking {
        val logoRequested = CompletableDeferred<Unit>()
        val releaseLogo = CompletableDeferred<Unit>()
        coEvery { repository.searchWithPeople("Loki", any()) } returns MediaSearchResults(
            listOf(loki), listOf(PersonMediaSearchResult(99, "Actor", listOf(loki))))
        coEvery { repository.getLogoUrl(any<MediaType>(), any()) } coAnswers {
            logoRequested.complete(Unit)
            releaseLogo.await()
            "https://example.test/logo.png"
        }
        model.updateQuery("Loki")
        model.search()
        val result = awaitResults("Loki")
        withTimeout(5_000) { logoRequested.await() }
        assertEquals(listOf(loki), result.results)
        assertEquals("Actor", result.personResults.single().title)
        assertTrue(result.cardLogoUrls.isEmpty())
        assertFalse(result.isLoading)
        model.search()
        coVerify(exactly = 1) { repository.searchWithPeople("Loki", any()) }
        releaseLogo.complete(Unit)
        withTimeout(5_000) { model.uiState.first { it.cardLogoUrls.isNotEmpty() } }
        assertEquals(listOf(loki), model.uiState.value.results)
    }

    @Test fun cancelledOldQueryCannotReplaceNewResults() = runBlocking {
        val oldStarted = CompletableDeferred<Unit>()
        val finishOld = CompletableDeferred<Unit>()
        coEvery { repository.searchWithPeople("old", any()) } coAnswers {
            withContext(NonCancellable) {
                oldStarted.complete(Unit)
                finishOld.await()
                MediaSearchResults(listOf(loki.copy(title = "Old")), emptyList())
            }
        }
        coEvery { repository.searchWithPeople("new", any()) } returns MediaSearchResults(listOf(loki), emptyList())
        model.updateQuery("old"); model.search()
        withTimeout(5_000) { oldStarted.await() }
        model.updateQuery("new"); model.search()
        assertEquals(listOf(loki), awaitResults("new").results)
        finishOld.complete(Unit)
        delay(100)
        assertEquals("new", model.uiState.value.query)
        assertEquals(listOf(loki), model.uiState.value.results)
    }

    @Test fun typingDebouncesAndSupportsSingleCharacterTitles() = runBlocking {
        coEvery { repository.searchWithPeople("X", any()) } returns MediaSearchResults(listOf(loki.copy(title = "X")), emptyList())
        model.updateQuery("lo")
        model.updateQuery("lok")
        model.updateQuery("X")
        assertTrue(model.uiState.value.isLoading)
        assertEquals("X", awaitResults("X").results.single().title)
        coVerify(exactly = 0) { repository.searchWithPeople("lo", any()) }
        coVerify(exactly = 0) { repository.searchWithPeople("lok", any()) }
        coVerify(exactly = 1) { repository.searchWithPeople("X", any()) }
    }

    @Test fun failedSearchCanRetryAndIsNotReportedAsSuccessfulEmptyResults() = runBlocking {
        coEvery { repository.searchWithPeople("Loki", any()) } throws IllegalStateException("Offline")
        model.updateQuery("Loki"); model.search()
        val failed = awaitResults("Loki")
        assertEquals("Offline", failed.error)
        coEvery { repository.searchWithPeople("Loki", any()) } returns MediaSearchResults(listOf(loki), emptyList())
        model.search()
        val result = withTimeout(5_000) { model.uiState.first { it.results.isNotEmpty() } }
        assertNull(result.error)
    }

    @Test fun titleContainingDiscoveryKeywordsUsesTitleSearch() = runBlocking {
        coEvery { repository.searchWithPeople("Best in Show", any()) } returns MediaSearchResults(listOf(loki), emptyList())
        model.updateQuery("Best in Show"); model.search()
        assertFalse(awaitResults("Best in Show").isAiSearch)
        coVerify(exactly = 1) { repository.searchWithPeople("Best in Show", any()) }
    }

    @Test fun missingPersonCreditsLoadSeparatelyWithoutHoldingUpTitleMatches() = runBlocking {
        val creditsStarted = CompletableDeferred<Unit>()
        val releaseCredits = CompletableDeferred<Unit>()
        coEvery { repository.searchWithPeople("Loki", any()) } returns MediaSearchResults(
            listOf(loki), listOf(PersonMediaSearchResult(99, "Actor", emptyList())))
        coEvery { repository.getPersonDetails(99) } coAnswers {
            creditsStarted.complete(Unit)
            releaseCredits.await()
            PersonDetails(99, "Actor", knownFor = listOf(loki))
        }
        model.updateQuery("Loki"); model.search()
        assertEquals(listOf(loki), awaitResults("Loki").results)
        withTimeout(5_000) { creditsStarted.await() }
        assertFalse(model.uiState.value.isLoading)
        releaseCredits.complete(Unit)
        val result = withTimeout(5_000) { model.uiState.first { it.personResults.isNotEmpty() } }
        assertEquals(listOf(loki), result.results)
        assertEquals("Actor", result.personResults.single().title)
    }

    @Test fun peopleOnlySearchWaitsForMissingCreditsWithoutFalseEmptyMessage() = runBlocking {
        val releaseCredits = CompletableDeferred<Unit>()
        coEvery { repository.searchWithPeople("Actor", any()) } returns MediaSearchResults(
            emptyList(), listOf(PersonMediaSearchResult(99, "Actor", emptyList())))
        coEvery { repository.getPersonDetails(99) } coAnswers {
            releaseCredits.await()
            PersonDetails(99, "Actor", knownFor = listOf(loki))
        }
        model.updateQuery("Actor"); model.search()
        assertTrue(model.uiState.value.isLoading)
        releaseCredits.complete(Unit)
        assertEquals(listOf(loki), awaitResults("Actor").personResults.single().items)
    }

    private suspend fun awaitResults(query: String) = withTimeout(5_000) {
        model.uiState.first { it.query == query && !it.isLoading }
    }
}
