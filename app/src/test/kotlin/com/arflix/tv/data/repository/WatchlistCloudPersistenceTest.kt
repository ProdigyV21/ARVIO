package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.traktDataStore
import io.mockk.every
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class WatchlistCloudPersistenceTest {
    private lateinit var repository: WatchlistRepository
    private lateinit var profiles: ProfileManager
    private lateinit var tmdb: TmdbApi
    private var profile = "main"
    private fun newRepository() = WatchlistRepository(RuntimeEnvironment.getApplication(), profiles, tmdb, CloudSyncInvalidationBus())

    @Before fun setup() = runBlocking {
        // Match DataStore's atomic transform contract without Android's rename-over-existing
        // implementation, which is unavailable on the Windows JVM test filesystem.
        val state = MutableStateFlow(emptyPreferences())
        val lock = Mutex()
        val store = object : DataStore<Preferences> {
            override val data = state
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                lock.withLock { transform(state.value).toPreferences().also { state.value = it } }
        }
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().traktDataStore } returns store
        profiles = mockk()
        tmdb = mockk()
        every { profiles.getProfileIdSync() } answers { profile }
        every { profiles.profileStringKey(any()) } answers { stringPreferencesKey("$profile:${firstArg<String>()}") }
        every { profiles.profileStringKeyFor(any(), any()) } answers { stringPreferencesKey("${firstArg<String>()}:${secondArg<String>()}") }
        repository = newRepository()
    }

    @After fun cleanup() { unmockkStatic("com.arflix.tv.util.DataStoresKt") }

    @Test fun `removal persists after repository recreation and old cloud restore`() = runBlocking {
        repository.addToWatchlist(MediaType.MOVIE, 123)
        val oldCloud = repository.exportWatchlistForProfile("main")
        repository.removeFromWatchlist(MediaType.MOVIE, 123)
        repository = newRepository()
        assertTrue(repository.importWatchlistForProfile("main", oldCloud))
        assertTrue(repository.exportWatchlistForProfile("main").isEmpty())
        assertFalse(repository.isInWatchlist(MediaType.MOVIE, 123))
        repository.addToWatchlist(MediaType.MOVIE, 123)
        assertEquals(1, repository.exportWatchlistForProfile("main").size)
    }

    @Test fun `concurrent stale restores cannot resurrect removals or lose other additions`() = runBlocking {
        repeat(12) { repository.addToWatchlist(MediaType.MOVIE, it + 1) }
        val oldCloud = repository.exportWatchlistForProfile("main")
        coroutineScope {
            repeat(12) { index ->
                launch { repository.removeFromWatchlist(MediaType.MOVIE, index + 1) }
                launch { repository.importWatchlistForProfile("main", oldCloud) }
                launch { repository.addToWatchlist(MediaType.TV, index + 1) }
            }
        }
        val state = repository.exportSyncStateForProfile("main")
        assertEquals(12, state.items.size)
        assertTrue(state.items.all { it.mediaType == "tv" })
        assertEquals(12, state.changes.values.count { it.removed })
    }

    @Test fun `cloud serialization propagates empty list and isolates profiles`() = runBlocking {
        repository.addToWatchlist(MediaType.MOVIE, 123)
        val oldCloud = repository.exportSyncStateForProfile("main")
        repository.removeFromWatchlist(MediaType.MOVIE, 123)
        val payload = JSONObject()
        WatchlistCloudFields.put(payload, mapOf("main" to repository.exportSyncStateForProfile("main")))
        val stale = JSONObject()
        WatchlistCloudFields.put(stale, mapOf("main" to oldCloud, "kids" to oldCloud))
        WatchlistCloudFields.merge(stale, JSONObject(payload.toString()))
        val states = WatchlistCloudFields.states(stale)
        assertTrue(states.getValue("main").items.isEmpty())
        assertEquals(1, states.getValue("kids").items.size)
        assertTrue(states.getValue("main").changes.getValue("movie:123").removed)
    }

    @Test fun `import for another profile does not replace current UI or deletion records`() = runBlocking {
        repository.addToWatchlist(MediaType.MOVIE, 123)
        val oldCloud = repository.exportWatchlistForProfile("main")
        repository.removeFromWatchlist(MediaType.MOVIE, 123)
        repository.importWatchlistForProfile("kids", oldCloud)
        assertFalse(repository.isInWatchlist(MediaType.MOVIE, 123))
        assertTrue(repository.exportWatchlistForProfile("main").isEmpty())
        assertEquals(1, repository.exportWatchlistForProfile("kids").size)
    }

    @Test fun `stale provider refresh neither undoes an add nor resurrects a removal`() = runBlocking {
        repository.addToWatchlist(MediaType.MOVIE, 123)
        val other = MediaItem(id = 456, title = "Other", mediaType = MediaType.MOVIE)
        repository.syncFromTraktOrder(listOf(other))
        assertEquals(setOf(123, 456), repository.exportWatchlistForProfile("main").map { it.tmdbId }.toSet())
        repository.removeFromWatchlist(MediaType.MOVIE, 123)
        repository.syncFromTraktOrder(listOf(other, MediaItem(id = 123, title = "Old", mediaType = MediaType.MOVIE)))
        assertEquals(listOf(456), repository.exportWatchlistForProfile("main").map { it.tmdbId })
        assertFalse(repository.exportSyncStateForProfile("main").changes.containsKey("movie:456"))
    }

    @Test fun `late enrichment keeps current membership and surviving metadata`() = runBlocking {
        val requested = CompletableDeferred<Unit>()
        val release = CompletableDeferred<Unit>()
        repository.importWatchlistForProfile("main", listOf(
            LocalWatchlistItem(123, "movie", "First"), LocalWatchlistItem(456, "movie", "Second")
        ))
        coEvery { tmdb.getMovieDetails(any(), any(), any(), any()) } coAnswers {
            requested.complete(Unit)
            release.await()
            TmdbMovieDetails(id = firstArg(), title = "Enriched ${firstArg<Int>()}", overview = "Description", posterPath = "/poster.jpg")
        }
        val refresh = async { repository.refreshWatchlistItems() }
        withTimeout(5000) { requested.await() }
        repository.removeFromWatchlist(MediaType.MOVIE, 123)
        repository.addToWatchlist(MediaType.TV, 789)
        release.complete(Unit)
        val refreshed = withTimeout(5000) { refresh.await() }
        assertEquals(setOf(456, 789), refreshed.map { it.id }.toSet())
        assertEquals("Description", refreshed.single { it.id == 456 }.overview)
        assertEquals("Enriched 456", repository.exportWatchlistForProfile("main").single { it.tmdbId == 456 }.title)
        assertFalse(repository.isInWatchlist(MediaType.MOVIE, 123))
        assertEquals(setOf(456, 789), repository.getCachedItems().map { it.id }.toSet())
    }

    @Test fun `in-flight enrichment stays with its original profile after switching`() = runBlocking {
        val requested = CompletableDeferred<Unit>()
        val release = CompletableDeferred<Unit>()
        repository.addToWatchlist(MediaType.MOVIE, 123)
        coEvery { tmdb.getMovieDetails(any(), any(), any(), any()) } coAnswers {
            requested.complete(Unit)
            release.await()
            TmdbMovieDetails(id = 123, title = "Main enrichment", posterPath = "/main.jpg")
        }
        val refresh = async { repository.refreshWatchlistItems() }
        withTimeout(5000) { requested.await() }
        profile = "kids"
        repository.clearWatchlistCache()
        repository.addToWatchlist(MediaType.MOVIE, 123, MediaItem(id = 123, title = "Kids title", mediaType = MediaType.MOVIE))
        release.complete(Unit)
        assertTrue(withTimeout(5000) { refresh.await() }.isEmpty())
        assertEquals("Main enrichment", repository.exportWatchlistForProfile("main").single().title)
        assertEquals("Kids title", repository.exportWatchlistForProfile("kids").single().title)
        assertEquals("Kids title", repository.getCachedItems().single().title)
        assertTrue(repository.isInWatchlist(MediaType.MOVIE, 123))
    }
}
