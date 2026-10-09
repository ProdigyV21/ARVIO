package com.arflix.tv.ui.screens.search

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.MediaSearchResults
import com.arflix.tv.data.repository.TraktRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchWatchedLookupTest {
    private val main = Executors.newSingleThreadExecutor { Thread(it, "search-watched-test-main") }
        .asCoroutineDispatcher()
    private val repository = mockk<MediaRepository>(relaxed = true)
    private val trakt = mockk<TraktRepository>(relaxed = true)
    private val store = ViewModelStore()
    private val history = AtomicReference<Set<String>>(emptySet())
    private val readThreads = ConcurrentHashMap.newKeySet<String>()
    private lateinit var model: SearchViewModel

    @Before fun setUp() = runBlocking {
        Dispatchers.setMain(main)
        every { trakt.getWatchedMoviesFromCache() } answers {
            readThreads.add(Thread.currentThread().name)
            setOf(7)
        }
        every { trakt.getWatchedEpisodesFromCache() } answers {
            readThreads.add(Thread.currentThread().name)
            history.get()
        }
        withContext(main) {
            model = SearchViewModel(repository, trakt, mockk(relaxed = true))
            store.put("search", model)
        }
        withTimeout(5_000) { model.uiState.first { !it.isDiscoverLoading } }
        Unit
    }

    @After fun tearDown() = runBlocking {
        try {
            val job = model.viewModelScope.coroutineContext[Job]
            withContext(main) { store.clear() }
            withTimeout(5_000) { job?.join() }
        } finally {
            Dispatchers.resetMain()
            main.close()
        }
        Unit
    }

    private suspend fun search(query: String, items: List<MediaItem>) {
        coEvery { repository.searchWithPeople(query, any()) } returns MediaSearchResults(items, emptyList())
        withContext(main) {
            model.updateQuery(query)
            model.search()
        }
        withTimeout(5_000) { model.uiState.first { it.query == query && !it.isLoading } }
    }

    @Test fun largeHistoryIsIndexedOffMainWithoutPerShowScans() = runBlocking {
        history.set((1..20_000).mapTo(HashSet()) { "show_tmdb:${it / 10 + 1}:1:$it" } +
            setOf("show_trakt:5000:1:1", "show_tmdb:5000", "show_tmdb:bad:1:1"))
        val items = listOf(
            MediaItem(id = 7, title = "Film", mediaType = MediaType.MOVIE),
            MediaItem(id = 2, title = "Started", mediaType = MediaType.TV),
            MediaItem(id = 5000, title = "New", mediaType = MediaType.TV)
        )
        search("history", items)
        assertEquals(listOf(true, true, false), model.uiState.value.results.map { it.isWatched })
        assertTrue(readThreads.isNotEmpty())
        assertFalse(readThreads.contains("search-watched-test-main"))
        verify(exactly = 0) { trakt.hasWatchedEpisodes(any()) }

        history.set(emptySet())
        withContext(main) { model.refreshWatchedMarks() }
        withTimeout(5_000) { model.uiState.first { !it.tvResults.first().isWatched } }
        assertEquals(listOf(true, false, false), model.uiState.value.results.map { it.isWatched })
        assertFalse(readThreads.contains("search-watched-test-main"))
    }

    @Test fun slowRefreshDoesNotRestoreAnOldSearch() = runBlocking {
        val old = MediaItem(id = 2, title = "Old", mediaType = MediaType.TV)
        val new = MediaItem(id = 3, title = "New", mediaType = MediaType.TV)
        search("old", listOf(old))
        val started = CountDownLatch(1)
        val release = CountDownLatch(1)
        every { trakt.getWatchedEpisodesFromCache() } answers {
            if (started.count > 0) {
                started.countDown()
                check(release.await(5, TimeUnit.SECONDS))
                setOf("show_tmdb:3:1:1")
            } else emptySet()
        }
        try {
            withContext(main) { model.refreshWatchedMarks() }
            assertTrue(withContext(Dispatchers.IO) { started.await(5, TimeUnit.SECONDS) })
            search("new", listOf(new))
            release.countDown()
            withTimeout(5_000) { model.uiState.first { it.results.singleOrNull()?.isWatched == true } }
            assertEquals("new", model.uiState.value.query)
            assertEquals(listOf(3), model.uiState.value.results.map { it.id })
        } finally {
            release.countDown()
        }
    }
}
