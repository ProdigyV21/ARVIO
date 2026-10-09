package com.arflix.tv.ui.screens.search

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.PersonDetails
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.MediaSearchResults
import com.arflix.tv.data.repository.PersonMediaSearchResult
import com.arflix.tv.data.repository.TraktRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
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
    private val trakt = mockk<TraktRepository>(relaxed = true)
    private val store = ViewModelStore()
    private lateinit var model: SearchViewModel
    private val loki = MediaItem(id = 2, title = "Loki", mediaType = MediaType.TV)

    @Before fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        coEvery { repository.getLogoUrl(any<MediaType>(), any()) } returns null
        model = SearchViewModel(repository, trakt, mockk(relaxed = true))
        store.put("search", model)
    }

    @After fun tearDown() {
        val job = model.viewModelScope.coroutineContext[kotlinx.coroutines.Job]
        try {
            store.clear()
            // IO children must finish cancellation before Main is removed.
            runBlocking { withTimeout(5_000) { job?.join() } }
        } finally {
            Dispatchers.resetMain()
        }
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

    @Test fun ordinaryResultsPublishBeforeSlowAddonAndNativeResultsAppendLater() = runBlocking {
        val started = CompletableDeferred<Unit>()
        val release = CompletableDeferred<Unit>()
        val native = loki.copy(id = -12, title = "Loki special", addonNativeId = "provider:12",
            addonNativeAddonId = "provider")
        coEvery { repository.searchWithPeople("Loki", any()) } returns MediaSearchResults(listOf(loki), emptyList())
        coEvery { repository.searchNativeAddonItems("Loki") } coAnswers {
            started.complete(Unit)
            release.await()
            listOf(native)
        }
        model.updateQuery("Loki"); model.search()
        assertEquals(listOf(loki), awaitResults("Loki").results)
        withTimeout(5_000) { started.await() }
        assertFalse(model.uiState.value.isLoading)
        release.complete(Unit)
        val complete = withTimeout(5_000) { model.uiState.first { it.results.size == 2 } }
        assertEquals(setOf(loki, native), complete.results.toSet())
    }

    @Test fun cancelledAddonQueryCannotAppendToNewQuery() = runBlocking {
        val started = CompletableDeferred<Unit>()
        val release = CompletableDeferred<Unit>()
        coEvery { repository.searchWithPeople(any(), any()) } returns MediaSearchResults(listOf(loki), emptyList())
        coEvery { repository.searchNativeAddonItems("old") } coAnswers {
            withContext(NonCancellable) {
                started.complete(Unit)
                release.await()
                listOf(loki.copy(id = -12, title = "Old native", addonNativeId = "provider:12"))
            }
        }
        model.updateQuery("old"); model.search()
        withTimeout(5_000) { started.await() }
        model.updateQuery("new"); model.search()
        assertEquals(listOf(loki), awaitResults("new").results)
        release.complete(Unit)
        delay(100)
        assertEquals(listOf(loki), model.uiState.value.results)
    }

    @Test fun addonResultsStillWorkWhenTmdbFails() = runBlocking {
        val native = loki.copy(id = -12, addonNativeId = "provider:12", addonNativeAddonId = "provider")
        coEvery { repository.searchWithPeople("Loki", any()) } throws IllegalStateException("Offline")
        coEvery { repository.searchNativeAddonItems("Loki") } returns listOf(native)
        model.updateQuery("Loki"); model.search()
        val results = awaitResults("Loki")
        assertEquals(listOf(native), results.results)
        assertNull(results.error)
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

    @Test fun searchResultsAndPeopleRowsCarryTheWatchedTick() = runBlocking {
        val watchedFilm = MediaItem(id = 1, title = "Watched", mediaType = MediaType.MOVIE)
        val newFilm = MediaItem(id = 3, title = "New", mediaType = MediaType.MOVIE)
        every { trakt.getWatchedMoviesFromCache() } returns setOf(1)
        every { trakt.getWatchedEpisodesFromCache() } returns setOf("show_tmdb:2:1:1")
        coEvery { repository.searchWithPeople("Loki", any()) } returns MediaSearchResults(
            listOf(watchedFilm, loki, newFilm), listOf(PersonMediaSearchResult(99, "Actor", listOf(loki, newFilm))))
        model.updateQuery("Loki"); model.search()
        val result = awaitResults("Loki")
        // A film counts once it is in the watched list, a series once an episode of it is.
        assertEquals(mapOf(1 to true, 2 to true, 3 to false), result.results.associate { it.id to it.isWatched })
        assertEquals(listOf(true), result.movieResults.filter { it.id == 1 }.map { it.isWatched })
        assertEquals(listOf(true), result.tvResults.map { it.isWatched })
        assertEquals(listOf(true, false), result.personResults.single().items.map { it.isWatched })
    }

    @Test fun repeatedSearchReadsTheWatchedListAgainInsteadOfTheCachedAnswer() = runBlocking {
        coEvery { repository.searchWithPeople("Loki", any()) } returns MediaSearchResults(listOf(loki), emptyList())
        model.updateQuery("Loki"); model.search()
        assertFalse(awaitResults("Loki").results.single().isWatched)
        // Watched in the meantime: the cached answer is reused, the tick is not. Typing the
        // query again goes through updateQuery, which cancels the first search's leftover logo
        // work; a bare second search() would be ignored while that work is still running.
        every { trakt.getWatchedEpisodesFromCache() } returns setOf("show_tmdb:2:1:1")
        model.updateQuery("Lok")
        model.updateQuery("Loki")
        withTimeout(5_000) { model.uiState.first { it.results.singleOrNull()?.isWatched == true } }
        coVerify(exactly = 1) { repository.searchWithPeople("Loki", any()) }
    }

    private suspend fun awaitResults(query: String) = withTimeout(5_000) {
        model.uiState.first { it.query == query && !it.isLoading }
    }

    @Test fun selectTypeUpdatesSelectedTypeDirectly() {
        assertEquals(DiscoverType.MOVIES, model.uiState.value.selectedType)
        model.selectType(DiscoverType.ANIME)
        assertEquals(DiscoverType.ANIME, model.uiState.value.selectedType)
        model.selectType(DiscoverType.TV_SHOWS)
        assertEquals(DiscoverType.TV_SHOWS, model.uiState.value.selectedType)
    }
}
