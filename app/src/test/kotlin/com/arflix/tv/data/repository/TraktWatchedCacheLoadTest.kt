package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.arflix.tv.data.api.*
import com.arflix.tv.data.repository.sync.SyncProvider
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.arflix.tv.data.repository.sync.TrackingFeature
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.traktDataStore
import io.mockk.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.yield
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import javax.inject.Provider

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class TraktWatchedCacheLoadTest {
    private class MemoryStore(initial: Preferences) : DataStore<Preferences> {
        override val data = MutableStateFlow(initial)
        private val mutex = Mutex()
        override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences = mutex.withLock {
            transform(data.value).also { data.value = it }
        }
    }

    private val api = mockk<TraktApi>(relaxed = true)
    private val syncService = mockk<TraktSyncService>(relaxed = true)
    private val providers = mockk<SyncProviderStore>(relaxed = true)
    private lateinit var repository: TraktRepository

    private fun watchedMovie(tmdb: Int) =
        TraktWatchedMovie(1, null, null, TraktMovieInfo("Movie $tmdb", 2026, TraktIds(trakt = tmdb, tmdb = tmdb)))

    @Before fun setUp() {
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        val store = MemoryStore(preferencesOf(stringPreferencesKey("trakt_access_token") to "test-token"))
        every { any<Context>().traktDataStore } returns store
        every { any<Context>().settingsDataStore } returns MemoryStore(emptyPreferences())
        val profiles = mockk<ProfileManager>(relaxed = true) {
            every { getProfileIdSync() } returns "p"
            every { activeProfileId } returns MutableStateFlow("p")
            every { profileStringKey(any()) } answers { stringPreferencesKey(firstArg()) }
            every { profileLongKey(any()) } answers { longPreferencesKey(firstArg()) }
            every { profileBooleanKey(any()) } answers { booleanPreferencesKey(firstArg()) }
        }
        coEvery { providers.readProviders(TrackingFeature.WATCHED) } returns setOf(SyncProvider.TRAKT)
        coEvery { syncService.getWatchedEpisodes() } returns emptySet()
        repository = TraktRepository(RuntimeEnvironment.getApplication(), api, mockk(), OkHttpClient(),
            Provider { syncService }, profiles, mockk(relaxed = true), providers,
            mockk(relaxed = true), ContinueWatchingUpdates())
    }

    @After fun tearDown() {
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
    }

    @Test fun traktHistoryIsReadEvenWhenTheLastSyncLeftAPartialListInMemory() = runBlocking {
        // Without a cloud account the sync service returns the last full sync, which can be partial.
        coEvery { syncService.getWatchedMovies() } returns setOf(1)
        coEvery { api.getWatchedMovies(any(), any(), any(), any(), any(), any()) } answers {
            if (arg<Int?>(3) == 1) listOf(watchedMovie(1), watchedMovie(2), watchedMovie(3)) else emptyList()
        }

        repository.initializeWatchedCache()

        assertEquals(setOf(1, 2, 3), repository.getWatchedMoviesFromCache())
    }

    @Test fun aLoadOvertakenByAnInvalidationDoesNotKeepItsOldData() = runBlocking {
        val oldHistory = CompletableDeferred<Set<Int>>()
        var calls = 0
        coEvery { syncService.getWatchedMovies() } coAnswers { if (++calls == 1) oldHistory.await() else setOf(2) }
        coEvery { api.getWatchedMovies(any(), any(), any(), any(), any(), any()) } returns emptyList()

        val staleLoad = async(start = CoroutineStart.UNDISPATCHED) { repository.initializeWatchedCache() }
        // A sync finishes while the first load still waits for its data.
        repository.invalidateWatchedCache()
        val waiter = async(start = CoroutineStart.UNDISPATCHED) { repository.initializeWatchedCache() }
        oldHistory.complete(setOf(1))
        staleLoad.await()
        waiter.await()
        yield()

        repository.initializeWatchedCache()
        assertEquals(setOf(2), repository.getWatchedMoviesFromCache())
    }
}
