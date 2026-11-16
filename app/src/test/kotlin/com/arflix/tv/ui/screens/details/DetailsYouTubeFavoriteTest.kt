package com.arflix.tv.ui.screens.details

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModelStore
import com.arflix.tv.data.model.KidsAgeLevel
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.KidsContentFilter
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.StreamIntegrationRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.data.repository.YouTubeCollection
import com.arflix.tv.data.repository.effectiveSettingsDataStore
import com.arflix.tv.data.repository.sync.RemoteSyncManager
import com.arflix.tv.network.TmdbPriorityDispatcher
import com.arflix.tv.util.settingsDataStore
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

/** A YouTube video's bookmark is the profile's YouTube row, never the watchlist or Trakt. */
@OptIn(ExperimentalCoroutinesApi::class)
class DetailsYouTubeFavoriteTest {
    private val videoId = "AAAAAAAAAA1"
    private val video = MediaItem(
        id = -42,
        title = "Example video",
        mediaType = MediaType.MOVIE,
        addonNativeId = YouTubeCollection.metaId(videoId),
        addonNativeAddonId = YouTubeCollection.ADDON_ID
    )
    private val store = ViewModelStore()
    private lateinit var media: MediaRepository
    private lateinit var watchlist: WatchlistRepository
    private lateinit var remote: RemoteSyncManager
    private lateinit var catalogs: CatalogRepository
    private lateinit var kids: KidsContentFilter

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
        mockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
        every { any<Context>().effectiveSettingsDataStore } returns settings

        media = mockk(relaxed = true)
        every { media.episodeRatingsUpdated } returns MutableSharedFlow<Pair<Int, Int>>()
        every { media.getCachedFullItem(MediaType.MOVIE, video.id) } returns video
        every { media.addonNativeCard(MediaType.MOVIE, video.id) } returns video
        coEvery { media.getMovieDetails(video.id) } returns video
        watchlist = mockk(relaxed = true)
        remote = mockk(relaxed = true)
        coEvery { remote.isRemoteConnected(any()) } returns true
        catalogs = mockk(relaxed = true)
        coEvery { catalogs.isYouTubeVideoSaved(videoId) } returns false
        coEvery { catalogs.addYouTubeVideo(any(), any(), any(), any()) } returns Result.success(mockk<com.arflix.tv.data.model.CatalogConfig>() to video.title)
        coEvery { catalogs.removeSavedYouTubeVideo(videoId) } returns Result.success(Unit)
        kids = mockk(relaxed = true)
        every { kids.state } returns MutableStateFlow(KidsContentFilter.State(null, "US", emptyMap()))
    }

    @After
    fun tearDown() {
        store.clear()
        Dispatchers.resetMain()
        unmockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
        unmockkStatic(Log::class)
    }

    private fun TestScope.openDetails(): DetailsViewModel {
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
            cloudSyncRepository = mockk<CloudSyncRepository>(relaxed = true),
            launcherContinueWatchingRepository = mockk(relaxed = true),
            streamIntegrationRepository = integrations,
            recentPlayedSourceRepository = mockk(relaxed = true),
            personalListsRepository = mockk(relaxed = true),
            kidsContentFilter = kids,
            idleReturnCoordinator = com.arflix.tv.data.repository.IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true)),
            catalogRepository = dagger.Lazy { catalogs }
        )
        store.put("details", model)
        model.loadDetails(MediaType.MOVIE, video.id)
        runCurrent()
        assertEquals(video.id, model.uiState.value.item?.id)
        return model
    }

    @Test
    fun `bookmarking a YouTube video saves it to the YouTube row, not the watchlist`() = runTest {
        val model = openDetails()
        assertTrue(model.uiState.value.isYouTubeVideo)

        model.toggleWatchlist()
        runCurrent()

        assertTrue(model.uiState.value.isInWatchlist)
        coVerify { catalogs.addYouTubeVideo(YouTubeCollection.watchUrl(videoId), null, any(), video.title) }
        coVerify(exactly = 0) { remote.addToWatchlist(any(), any(), any()) }
        coVerify(exactly = 0) { watchlist.addToWatchlist(any(), any(), any()) }
    }

    @Test
    fun `a saved video shows as saved and un-bookmarking removes it from the row`() = runTest {
        coEvery { catalogs.isYouTubeVideoSaved(videoId) } returns true
        val model = openDetails()
        assertTrue(model.uiState.value.isInWatchlist)

        model.toggleWatchlist()
        runCurrent()

        assertFalse(model.uiState.value.isInWatchlist)
        coVerify { catalogs.removeSavedYouTubeVideo(videoId) }
        coVerify(exactly = 0) { remote.removeFromWatchlist(any(), any(), any()) }
    }

    @Test
    fun `a kids profile is asked for the parent PIN before a video is saved`() = runTest {
        every { kids.state } returns MutableStateFlow(KidsContentFilter.State(KidsAgeLevel.LITTLE_KIDS, "US", emptyMap()))
        val model = openDetails()

        model.toggleWatchlist()
        runCurrent()

        assertTrue(model.uiState.value.youTubeParentPinRequested)
        assertFalse(model.uiState.value.isInWatchlist)
        coVerify(exactly = 0) { catalogs.addYouTubeVideo(any(), any(), any(), any()) }

        model.submitYouTubeParentPin("1234")
        runCurrent()

        assertFalse(model.uiState.value.youTubeParentPinRequested)
        assertTrue(model.uiState.value.isInWatchlist)
        coVerify { catalogs.addYouTubeVideo(any(), "1234", any(), video.title) }
    }

    @Test
    fun `a wrong parent PIN rolls the heart back`() = runTest {
        every { kids.state } returns MutableStateFlow(KidsContentFilter.State(KidsAgeLevel.LITTLE_KIDS, "US", emptyMap()))
        coEvery { catalogs.addYouTubeVideo(any(), any(), any(), any()) } returns
            Result.failure(com.arflix.tv.data.repository.CatalogException(com.arflix.tv.R.string.youtube_parent_approval_required))
        val model = openDetails()

        model.toggleWatchlist()
        runCurrent()
        model.submitYouTubeParentPin("0000")
        runCurrent()

        assertFalse(model.uiState.value.isInWatchlist)
    }
}
