package com.arflix.tv.ui.screens.details

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModelStore
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.StreamIntegrationRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.data.repository.sync.RemoteSyncManager
import com.arflix.tv.network.TmdbPriorityDispatcher
import com.arflix.tv.util.settingsDataStore
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsWatchlistToggleTest {
    private val movie = MediaItem(id = 603, title = "The Matrix", mediaType = MediaType.MOVIE)
    private val store = ViewModelStore()
    private lateinit var media: MediaRepository
    private lateinit var watchlist: WatchlistRepository
    private lateinit var remote: RemoteSyncManager
    private lateinit var cloud: CloudSyncRepository
    private lateinit var watchlistRead: CompletableDeferred<Boolean>

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.w(any<String>(), any<String>()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        val settings = object : DataStore<Preferences> {
            override val data = MutableStateFlow(emptyPreferences())
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences) = data.value
        }
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().settingsDataStore } returns settings

        media = mockk(relaxed = true)
        every { media.episodeRatingsUpdated } returns MutableSharedFlow<Pair<Int, Int>>()
        every { media.getCachedFullItem(MediaType.MOVIE, movie.id) } returns movie
        coEvery { media.getMovieDetails(movie.id) } returns movie
        watchlistRead = CompletableDeferred()
        watchlist = mockk(relaxed = true)
        coEvery { watchlist.isInWatchlist(MediaType.MOVIE, movie.id) } coAnswers { watchlistRead.await() }
        remote = mockk(relaxed = true)
        coEvery { remote.isRemoteConnected(any()) } returns true
        cloud = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        store.clear()
        Dispatchers.resetMain()
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
        unmockkStatic(Log::class)
    }

    private fun TestScope.openDetails(inWatchlist: Boolean? = false): DetailsViewModel {
        val streams = mockk<StreamRepository>(relaxed = true)
        every { streams.installedAddons } returns flowOf(emptyList())
        val integrations = mockk<StreamIntegrationRepository>(relaxed = true)
        every { integrations.getUnifiedSourceOrderedIds() } returns flowOf(emptyList())
        val model = DetailsViewModel(
            context = mockk(relaxed = true),
            mediaRepository = media,
            mdbListRepository = mockk(relaxed = true),
            pluginManager = mockk(relaxed = true),
            profileManager = mockk(relaxed = true),
            traktRepository = mockk(relaxed = true),
            remoteSyncManager = remote,
            streamRepository = streams,
            networkMonitor = mockk(relaxed = true),
            tmdbPriorityDispatcher = TmdbPriorityDispatcher(),
            animeMapper = mockk(relaxed = true),
            tmdbApi = mockk(relaxed = true),
            watchHistoryRepository = mockk(relaxed = true),
            watchlistRepository = watchlist,
            cloudSyncRepository = cloud,
            launcherContinueWatchingRepository = mockk(relaxed = true),
            streamIntegrationRepository = integrations,
            recentPlayedSourceRepository = mockk(relaxed = true)
        )
        store.put("details", model)
        model.loadDetails(MediaType.MOVIE, movie.id)
        runCurrent()
        assertEquals(movie.id, model.uiState.value.item?.id)
        if (inWatchlist != null) {
            watchlistRead.complete(inWatchlist)
            runCurrent()
            assertEquals(inWatchlist, model.uiState.value.isInWatchlist)
        }
        return model
    }

    @Test
    fun `bookmark shows at once while trakt and cloud are still saving`() = runTest {
        val traktAdd = CompletableDeferred<Boolean>()
        coEvery { remote.addToWatchlist(any(), any(), any()) } coAnswers { traktAdd.await() }
        coEvery { cloud.pushToCloud(any()) } coAnswers { awaitCancellation() }
        val model = openDetails()

        model.toggleWatchlist()
        assertTrue(model.uiState.value.isInWatchlist)
        assertEquals(ToastType.SUCCESS, model.uiState.value.toastType)

        runCurrent()
        assertTrue("Trakt still saving", model.uiState.value.isInWatchlist)

        traktAdd.complete(true)
        runCurrent()
        assertTrue("Cloud push hangs", model.uiState.value.isInWatchlist)
        coVerify(exactly = 1) { watchlist.addToWatchlist(MediaType.MOVIE, movie.id, movie) }
        coVerify(exactly = 1) { cloud.pushToCloud(any()) }
    }

    @Test
    fun `trakt failure puts the bookmark back and shows an error`() = runTest {
        coEvery { remote.addToWatchlist(any(), any(), any()) } returns false
        val model = openDetails()

        model.toggleWatchlist()
        assertTrue(model.uiState.value.isInWatchlist)
        runCurrent()

        assertFalse(model.uiState.value.isInWatchlist)
        assertEquals(ToastType.ERROR, model.uiState.value.toastType)
        coVerify(exactly = 0) { watchlist.addToWatchlist(any(), any(), any()) }
        coVerify(exactly = 0) { cloud.pushToCloud(any()) }
    }

    @Test
    fun `second tap while the first is saving ends out of the watchlist`() = runTest {
        val traktAdd = CompletableDeferred<Boolean>()
        coEvery { remote.addToWatchlist(any(), any(), any()) } coAnswers { traktAdd.await() }
        coEvery { remote.removeFromWatchlist(any(), any(), any()) } returns true
        val model = openDetails()

        model.toggleWatchlist()
        runCurrent()
        model.toggleWatchlist()
        runCurrent()
        assertFalse(model.uiState.value.isInWatchlist)

        traktAdd.complete(true)
        runCurrent()

        assertFalse(model.uiState.value.isInWatchlist)
        assertEquals(ToastType.SUCCESS, model.uiState.value.toastType)
        coVerifyOrder {
            remote.addToWatchlist(MediaType.MOVIE, movie.id, any())
            watchlist.addToWatchlist(MediaType.MOVIE, movie.id, movie)
            remote.removeFromWatchlist(MediaType.MOVIE, movie.id, any())
            watchlist.removeFromWatchlist(MediaType.MOVIE, movie.id)
        }
    }

    @Test
    fun `second tap after a failed first save leaves nothing to undo`() = runTest {
        val traktAdd = CompletableDeferred<Boolean>()
        coEvery { remote.addToWatchlist(any(), any(), any()) } coAnswers { traktAdd.await() }
        val model = openDetails()

        model.toggleWatchlist()
        runCurrent()
        model.toggleWatchlist()
        traktAdd.complete(false)
        runCurrent()

        assertFalse(model.uiState.value.isInWatchlist)
        coVerify(exactly = 0) { watchlist.addToWatchlist(any(), any(), any()) }
        coVerify(exactly = 0) { remote.removeFromWatchlist(any(), any(), any()) }
        coVerify(exactly = 0) { watchlist.removeFromWatchlist(any(), any()) }
    }

    @Test
    fun `late watchlist read from the page load does not undo a tap`() = runTest {
        coEvery { remote.addToWatchlist(any(), any(), any()) } returns true
        val model = openDetails(inWatchlist = null)

        model.toggleWatchlist()
        runCurrent()
        assertTrue(model.uiState.value.isInWatchlist)

        watchlistRead.complete(false)
        runCurrent()

        assertTrue(model.uiState.value.isInWatchlist)
        coVerify(exactly = 1) { watchlist.addToWatchlist(MediaType.MOVIE, movie.id, movie) }
    }

    @Test
    fun `double tap before initial read still saves the final intent`() = runTest {
        coEvery { remote.removeFromWatchlist(any(), any(), any()) } returns true
        val model = openDetails(inWatchlist = null)

        model.toggleWatchlist()
        model.toggleWatchlist()
        runCurrent()
        watchlistRead.complete(true)
        runCurrent()

        assertFalse(model.uiState.value.isInWatchlist)
        coVerify(exactly = 1) { remote.removeFromWatchlist(MediaType.MOVIE, movie.id, any()) }
        coVerify(exactly = 1) { watchlist.removeFromWatchlist(MediaType.MOVIE, movie.id) }
        coVerify(exactly = 0) { remote.addToWatchlist(any(), any(), any()) }
    }

    @Test
    fun `old title save cannot make the next title skip its write`() = runTest {
        val otherMovie = movie.copy(id = 604, title = "Another movie")
        every { media.getCachedFullItem(MediaType.MOVIE, otherMovie.id) } returns otherMovie
        coEvery { media.getMovieDetails(otherMovie.id) } returns otherMovie
        coEvery { watchlist.isInWatchlist(MediaType.MOVIE, otherMovie.id) } returns false
        val firstSave = CompletableDeferred<Boolean>()
        coEvery { remote.addToWatchlist(MediaType.MOVIE, movie.id, any()) } coAnswers { firstSave.await() }
        coEvery { remote.addToWatchlist(MediaType.MOVIE, otherMovie.id, any()) } returns true
        val model = openDetails()

        model.toggleWatchlist()
        runCurrent()
        model.loadDetails(MediaType.MOVIE, otherMovie.id)
        runCurrent()
        assertEquals(otherMovie.id, model.uiState.value.item?.id)
        assertFalse(model.uiState.value.isInWatchlist)
        firstSave.complete(true)
        runCurrent()
        model.toggleWatchlist()
        runCurrent()

        assertTrue(model.uiState.value.isInWatchlist)
        coVerify(exactly = 1) { watchlist.addToWatchlist(MediaType.MOVIE, otherMovie.id, otherMovie) }
    }

    @Test
    fun `reentering details during a save preserves the pending bookmark`() = runTest {
        val firstSave = CompletableDeferred<Boolean>()
        coEvery { remote.addToWatchlist(any(), any(), any()) } coAnswers { firstSave.await() }
        val model = openDetails()
        model.toggleWatchlist()
        runCurrent()

        model.loadDetails(MediaType.MOVIE, movie.id)
        runCurrent()
        assertTrue(model.uiState.value.isInWatchlist)
        firstSave.complete(true)
        runCurrent()

        assertTrue(model.uiState.value.isInWatchlist)
        coVerify(exactly = 1) { watchlist.addToWatchlist(MediaType.MOVIE, movie.id, movie) }
    }

    @Test
    fun `failed removal restores a previously saved bookmark`() = runTest {
        coEvery { remote.removeFromWatchlist(any(), any(), any()) } returns false
        val model = openDetails(inWatchlist = true)
        model.toggleWatchlist()
        assertFalse(model.uiState.value.isInWatchlist)
        runCurrent()

        assertTrue(model.uiState.value.isInWatchlist)
        assertEquals(ToastType.ERROR, model.uiState.value.toastType)
        coVerify(exactly = 0) { watchlist.removeFromWatchlist(any(), any()) }
    }

    @Test
    fun `two immediate taps with known state need no writes`() = runTest {
        val model = openDetails()
        model.toggleWatchlist()
        model.toggleWatchlist()
        runCurrent()

        assertFalse(model.uiState.value.isInWatchlist)
        coVerify(exactly = 0) { remote.addToWatchlist(any(), any(), any()) }
        coVerify(exactly = 0) { remote.removeFromWatchlist(any(), any(), any()) }
        coVerify(exactly = 0) { cloud.pushToCloud(any()) }
    }

    @Test
    fun `local save failure rolls back and does not push cloud`() = runTest {
        coEvery { remote.isRemoteConnected(any()) } returns false
        coEvery { watchlist.addToWatchlist(any(), any(), any()) } throws IllegalStateException("Disk write failed")
        val model = openDetails()
        model.toggleWatchlist()
        runCurrent()

        assertFalse(model.uiState.value.isInWatchlist)
        assertEquals(ToastType.ERROR, model.uiState.value.toastType)
        coVerify(exactly = 0) { cloud.pushToCloud(any()) }
    }

    @Test
    fun `cloud failure does not roll back a successful local save`() = runTest {
        coEvery { remote.isRemoteConnected(any()) } returns false
        coEvery { cloud.pushToCloud(any()) } throws IllegalStateException("Offline")
        val model = openDetails()
        model.toggleWatchlist()
        runCurrent()

        assertTrue(model.uiState.value.isInWatchlist)
        assertEquals(ToastType.SUCCESS, model.uiState.value.toastType)
        coVerify(exactly = 1) { watchlist.addToWatchlist(MediaType.MOVIE, movie.id, movie) }
    }
}
