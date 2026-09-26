package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.arflix.tv.data.api.*
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.traktDataStore
import com.google.gson.Gson
import io.mockk.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import javax.inject.Provider

/**
 * A profile without Trakt or Cloud keeps its watched history only in the local snapshot. A mark
 * made before anything loaded the watched cache (right after launch or a profile switch) must add
 * to that history, not replace it.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class TraktLocalWatchedSnapshotTest {
    private class MemoryStore(initial: Preferences) : DataStore<Preferences> {
        override val data = MutableStateFlow(initial)
        private val mutex = Mutex()
        override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences = mutex.withLock {
            transform(data.value).also { data.value = it }
        }
    }

    private val gson = Gson()
    private val moviesKey = stringPreferencesKey("local_watched_movies_v1")
    private val episodesKey = stringPreferencesKey("local_watched_episodes_v1")
    private lateinit var store: MemoryStore
    private lateinit var syncService: TraktSyncService
    private lateinit var repository: TraktRepository

    @Before fun setUp() {
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        store = MemoryStore(
            preferencesOf(
                moviesKey to gson.toJson(listOf(1, 2, 3)),
                episodesKey to gson.toJson(listOf("show_tmdb:10:1:1"))
            )
        )
        every { any<Context>().traktDataStore } returns store
        every { any<Context>().settingsDataStore } returns MemoryStore(emptyPreferences())
        val profiles = mockk<ProfileManager>(relaxed = true) {
            every { getProfileIdSync() } returns "p"
            every { activeProfileId } returns MutableStateFlow("p")
            every { profileStringKey(any()) } answers { stringPreferencesKey(firstArg()) }
            every { profileLongKey(any()) } answers { longPreferencesKey(firstArg()) }
            every { profileBooleanKey(any()) } answers { booleanPreferencesKey(firstArg()) }
        }
        val providers = mockk<SyncProviderStore>(relaxed = true) {
            coEvery { readProviders(any()) } returns emptySet()
            coEvery { writeProviders() } returns emptySet()
        }
        syncService = mockk<TraktSyncService>(relaxed = true) {
            coEvery { getWatchedMovies() } returns emptySet()
            coEvery { getWatchedEpisodes() } returns emptySet()
        }
        repository = TraktRepository(RuntimeEnvironment.getApplication(), mockk(relaxed = true), mockk(),
            OkHttpClient(), Provider { syncService }, profiles, mockk(relaxed = true), providers,
            mockk(relaxed = true), ContinueWatchingUpdates())
    }

    @After fun tearDown() {
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
    }

    private fun storedMovies(): Set<Int> =
        store.data.value[moviesKey]?.let { gson.fromJson(it, Array<Int>::class.java).toSet() }.orEmpty()

    private fun storedEpisodes(): Set<String> =
        store.data.value[episodesKey]?.let { gson.fromJson(it, Array<String>::class.java).toSet() }.orEmpty()

    @Test fun filmMarkedBeforeTheCacheLoadedKeepsTheStoredHistory() = runBlocking {
        repository.markMovieWatched(4)
        assertEquals(setOf(1, 2, 3, 4), storedMovies())
        assertEquals(setOf("show_tmdb:10:1:1"), storedEpisodes())
    }

    @Test fun filmUnmarkedBeforeTheCacheLoadedRemovesOnlyThatFilm() = runBlocking {
        repository.markMovieUnwatched(2)
        assertEquals(setOf(1, 3), storedMovies())
        assertEquals(setOf("show_tmdb:10:1:1"), storedEpisodes())
    }

    @Test fun episodeMarkedBeforeTheCacheLoadedKeepsTheStoredHistory() = runBlocking {
        repository.markEpisodeWatched(10, 1, 2)
        assertEquals(setOf(1, 2, 3), storedMovies())
        assertEquals(setOf("show_tmdb:10:1:1", "show_tmdb:10:1:2"), storedEpisodes())
    }

    @Test fun seasonMarkedBeforeTheCacheLoadedKeepsTheStoredHistory() = runBlocking {
        repository.markSeasonWatched(10, 1, listOf(2, 3))
        assertEquals(setOf(1, 2, 3), storedMovies())
        assertEquals(setOf("show_tmdb:10:1:1", "show_tmdb:10:1:2", "show_tmdb:10:1:3"), storedEpisodes())
    }

    @Test fun writeWaitingOnALoadThatIsCancelledStillKeepsTheStoredHistory() = runBlocking {
        // Home cancels its tick pass - and the cache load inside it - whenever new rows land.
        val entered = CompletableDeferred<Unit>()
        var calls = 0
        coEvery { syncService.getWatchedMovies() } coAnswers {
            if (calls++ == 0) {
                entered.complete(Unit)
                awaitCancellation()
            }
            emptySet()
        }
        val otherLoad = launch { repository.initializeWatchedCache() }
        entered.await()
        val mark = launch { repository.markMovieWatched(4) }
        delay(200)
        otherLoad.cancelAndJoin()
        mark.join()

        assertEquals(setOf(1, 2, 3, 4), storedMovies())
    }

    @Test fun historyRestoredWhileTheWriteLoadsIsNotOverwritten() = runBlocking {
        val entered = CompletableDeferred<Unit>()
        val release = CompletableDeferred<Unit>()
        var calls = 0
        coEvery { syncService.getWatchedMovies() } coAnswers {
            if (calls++ == 0) {
                entered.complete(Unit)
                release.await()
            }
            emptySet()
        }
        val mark = launch { repository.markMovieWatched(4) }
        entered.await()
        // A cloud restore writes the snapshot and empties the caches while the write still loads.
        store.updateData { it.toMutablePreferences().apply { this[moviesKey] = gson.toJson(listOf(7, 8)) } }
        repository.clearAllProfileCaches()
        release.complete(Unit)
        mark.join()

        assertEquals(setOf(4, 7, 8), storedMovies())
    }
}
