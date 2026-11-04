package com.arflix.tv.ui.screens.details

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModelStore
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbExternalIds
import com.arflix.tv.data.api.TmdbSeasonDetails
import com.arflix.tv.data.api.TmdbTvDetails
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.StreamIntegrationRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryEntry
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.data.repository.effectiveSettingsDataStore
import com.arflix.tv.data.repository.sync.RemoteSyncManager
import com.arflix.tv.network.TmdbPriorityDispatcher
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class DetailsPlaybackTargetTest {
    private val store = ViewModelStore()
    private lateinit var metadata: CompletableDeferred<MediaItem>
    private lateinit var history: CompletableDeferred<WatchHistoryEntry?>
    private lateinit var progress: CompletableDeferred<Unit>
    private var watched = emptySet<String>()

    @Before fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        val settings = object : DataStore<Preferences> {
            override val data = MutableStateFlow(emptyPreferences())
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                transform(data.value).also { data.value = it }
        }
        mockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
        every { any<Context>().effectiveSettingsDataStore } returns settings
        metadata = CompletableDeferred()
        history = CompletableDeferred()
        progress = CompletableDeferred()
    }

    @After fun tearDown() {
        store.clear()
        unmockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
        Dispatchers.resetMain()
    }

    @Test fun cachedTvMetadataDoesNotBecomePlayableBeforeResumeTargetArrives() = runTest {
        val item = item(MediaType.TV)
        val model = open(item, metadataReady = false)
        runCurrent()
        assertEquals(item, model.uiState.value.item)
        assertFalse(model.uiState.value.isLoading)
        assertFalse(model.uiState.value.isPlaybackTargetReady)

        metadata.complete(item)
        runCurrent()
        assertFalse(model.uiState.value.isPlaybackTargetReady)

        history.complete(entry(MediaType.TV, season = 2, episode = 2))
        runCurrent()
        assertTarget(model, 2, 2, 90_000L)
    }

    @Test fun noResumeWaitsForWatchedProgressThenSelectsNextUnwatchedEpisode() = runTest {
        watched = setOf("show_tmdb:42:1:1", "show_tmdb:42:1:2", "show_tmdb:42:2:1")
        val model = open(item(MediaType.TV), progressReady = false)
        history.complete(null)
        runCurrent()
        assertFalse(model.uiState.value.isLoading)
        assertFalse(model.uiState.value.isPlaybackTargetReady)

        progress.complete(Unit)
        runCurrent()
        assertTarget(model, 2, 2, null)
    }

    @Test fun freshShowResolvesFirstEpisodeWithoutAResumePosition() = runTest {
        val model = open(item(MediaType.TV))
        history.complete(null)
        runCurrent()
        assertTarget(model, 1, 1, null)
    }

    @Test fun explicitEpisodeDoesNotInheritAnotherEpisodesResumePosition() = runTest {
        val model = open(item(MediaType.TV), initialSeason = 2, initialEpisode = 2, progressReady = false)
        runCurrent()
        assertFalse(model.uiState.value.isPlaybackTargetReady)
        history.complete(entry(MediaType.TV, season = 2, episode = 1))
        runCurrent()
        assertTarget(model, 2, 2, null)
    }

    @Test fun movieWaitsForExactResumePositionBeforeBecomingReady() = runTest {
        val model = open(item(MediaType.MOVIE))
        runCurrent()
        assertFalse(model.uiState.value.isPlaybackTargetReady)
        history.complete(entry(MediaType.MOVIE))
        runCurrent()
        assertTarget(model, null, null, 90_000L)
    }

    @Test fun reloadingCachedDetailsClearsReadinessBeforeReadingPreferences() = runTest {
        val model = open(item(MediaType.MOVIE))
        history.complete(null)
        runCurrent()
        assertTrue(model.uiState.value.isPlaybackTargetReady)

        history = CompletableDeferred()
        model.loadDetails(MediaType.MOVIE, 42)
        assertFalse(model.uiState.value.isPlaybackTargetReady)
        runCurrent()
        assertFalse(model.uiState.value.isPlaybackTargetReady)
        history.complete(entry(MediaType.MOVIE))
        runCurrent()
        assertTarget(model, null, null, 90_000L)
    }

    private fun item(type: MediaType) = MediaItem(42, "Playback target fixture", mediaType = type, totalEpisodes = 2)

    private fun entry(type: MediaType, season: Int? = null, episode: Int? = null) = WatchHistoryEntry(
        user_id = "fixture", media_type = type.name.lowercase(), show_tmdb_id = 42,
        season = season, episode = episode, progress = .25f, position_seconds = 90, duration_seconds = 360,
    )

    private fun assertTarget(model: DetailsViewModel, season: Int?, episode: Int?, position: Long?) {
        val state = model.uiState.value
        assertNull(state.error)
        assertTrue(state.isPlaybackTargetReady)
        assertEquals(season, state.playSeason)
        assertEquals(episode, state.playEpisode)
        assertEquals(position, state.playPositionMs)
    }

    private fun open(
        item: MediaItem,
        metadataReady: Boolean = true,
        progressReady: Boolean = true,
        initialSeason: Int? = null,
        initialEpisode: Int? = null,
    ): DetailsViewModel {
        if (metadataReady) metadata.complete(item)
        if (progressReady) progress.complete(Unit)
        fun episodes(season: Int) = (1..2).map { Episode(season * 10 + it, it, season, "Episode $it", airDate = "2020-01-01") }
        val media = mockk<MediaRepository>(relaxed = true)
        every { media.episodeRatingsUpdated } returns MutableSharedFlow()
        every { media.getCachedFullItem(item.mediaType, 42) } returns item
        every { media.peekCachedSeasonEpisodes(42, any()) } answers { episodes(secondArg()) }
        coEvery { media.getTvDetails(42) } coAnswers { metadata.await() }
        coEvery { media.getMovieDetails(42) } coAnswers { metadata.await() }
        coEvery { media.getSeasonEpisodes(42, any()) } answers { episodes(secondArg()) }
        val historyRepository = mockk<WatchHistoryRepository>(relaxed = true)
        coEvery { historyRepository.getLatestProgress(any(), 42) } coAnswers { history.await() }
        coEvery { historyRepository.getProgress(any(), 42, any(), any()) } returns null
        val trakt = mockk<TraktRepository>(relaxed = true)
        every { trakt.getWatchedEpisodesFromCache() } answers { watched }
        coEvery { trakt.getWatchedEpisodesForShow(42) } answers { watched }
        coEvery { trakt.getLocalContinueWatchingEntry(any(), any(), any(), any()) } returns null
        coEvery { trakt.getBestLocalContinueWatchingEntry(any(), any()) } returns null
        val remote = mockk<RemoteSyncManager>(relaxed = true)
        coEvery { remote.isRemoteConnected(any()) } returns false
        val api = mockk<TmdbApi>(relaxed = true)
        coEvery { api.getTvDetails(42, any()) } coAnswers {
            progress.await()
            TmdbTvDetails(id = 42, numberOfSeasons = 2)
        }
        coEvery { api.getTvSeason(42, any(), any()) } answers {
            val season = secondArg<Int>()
            TmdbSeasonDetails(seasonNumber = season, episodes = episodes(season).map {
                TmdbEpisode(id = it.id, seasonNumber = season, episodeNumber = it.episodeNumber, airDate = it.airDate)
            })
        }
        coEvery { api.getTvExternalIds(42, any()) } returns TmdbExternalIds()
        coEvery { api.getMovieExternalIds(42, any()) } returns TmdbExternalIds()
        val profiles = mockk<ProfileManager>(relaxed = true)
        every { profiles.profileBooleanKey(any()) } answers { booleanPreferencesKey(firstArg()) }
        every { profiles.profileStringKey(any()) } answers { stringPreferencesKey(firstArg()) }
        val streams = mockk<StreamRepository>(relaxed = true)
        every { streams.installedAddons } returns flowOf(emptyList())
        val integrations = mockk<StreamIntegrationRepository>(relaxed = true)
        every { integrations.getUnifiedSourceOrderedIds() } returns flowOf(emptyList())
        val model = DetailsViewModel(
            context = RuntimeEnvironment.getApplication(), mediaRepository = media,
            mdbListRepository = mockk(relaxed = true), pluginManager = mockk(relaxed = true),
            profileManager = profiles, traktRepository = trakt, remoteSyncManager = remote,
            streamRepository = streams, networkMonitor = mockk(relaxed = true),
            tmdbPriorityDispatcher = TmdbPriorityDispatcher(), animeMapper = mockk(relaxed = true),
            tmdbApi = api, watchHistoryRepository = historyRepository, watchlistRepository = mockk(relaxed = true),
            cloudSyncRepository = mockk(relaxed = true), launcherContinueWatchingRepository = mockk(relaxed = true),
            streamIntegrationRepository = integrations, recentPlayedSourceRepository = mockk(relaxed = true),
            personalListsRepository = mockk(relaxed = true), kidsContentFilter = mockk(relaxed = true), idleReturnCoordinator = com.arflix.tv.data.repository.IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true)),
        )
        store.put("details", model)
        model.loadDetails(item.mediaType, 42, initialSeason, initialEpisode)
        return model
    }
}
