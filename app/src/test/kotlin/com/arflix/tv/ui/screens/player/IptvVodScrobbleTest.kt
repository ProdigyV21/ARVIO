package com.arflix.tv.ui.screens.player

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.arflix.tv.data.model.*
import com.arflix.tv.data.repository.*
import com.arflix.tv.data.repository.sync.RemoteSyncManager
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvVodScrobbleTest {
    private lateinit var model: PlayerViewModel
    private val store = ViewModelStore()
    private val remote = mockk<RemoteSyncManager>(relaxed = true)
    private val history = mockk<WatchHistoryRepository>(relaxed = true)
    private val trakt = mockk<TraktRepository>(relaxed = true)
    private val profiles = mockk<ProfileManager>(relaxed = true)

    @Before fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        every { profiles.getProfileIdSync() } returns "test"
        every { profiles.activeProfileId } returns MutableStateFlow("test")
        model = PlayerViewModel(
            context = mockk(relaxed = true), profileManager = profiles,
            mediaRepository = mockk(relaxed = true), streamRepository = mockk(relaxed = true),
            traktRepository = trakt, remoteSyncManager = remote, watchHistoryRepository = history,
            cloudSyncRepository = mockk(relaxed = true), launcherContinueWatchingRepository = mockk(relaxed = true),
            animeMapper = mockk(relaxed = true), tmdbApi = mockk(relaxed = true),
            skipIntroRepository = mockk(relaxed = true), playbackTelemetryRepository = mockk(relaxed = true),
            pluginManager = mockk(relaxed = true), streamIntegrationRepository = mockk(relaxed = true),
            recentPlayedSourceRepository = mockk(relaxed = true), kidsContentFilter = mockk(relaxed = true),
            idleReturnCoordinator = IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true))
        )
        store.put("player", model)
        field("currentMediaType").set(model, MediaType.TV)
        field("currentMediaId").setInt(model, 1416)
        field("currentSeason").set(model, 22)
        field("currentEpisode").set(model, 15)
    }

    @After fun cleanup() {
        val job = model.viewModelScope.coroutineContext[Job]
        try { store.clear(); runBlocking { withTimeout(5_000) { job?.join() } } }
        finally { Dispatchers.resetMain() }
    }

    private fun field(name: String) = PlayerViewModel::class.java.getDeclaredField(name).apply { isAccessible = true }
    @Suppress("UNCHECKED_CAST")
    private fun state() = field("_uiState").get(model) as MutableStateFlow<PlayerUiState>
    private fun select(id: String, runtime: Long = 600_000) {
        state().value = state().value.copy(selectedStream = StreamSource("Episode", "IPTV VOD", id, "", "",
            url = "https://example.invalid/episode.ts"), expectedDurationMs = runtime)
    }
    private suspend fun save(position: Long, playing: Boolean, closing: Boolean = false, ended: Boolean = false, duration: Long = 0) {
        assertNotNull(model.saveProgress(position, duration, 0, playing,
            if (ended) Player.STATE_ENDED else Player.STATE_READY, closing)?.also { it.join() })
    }

    @Test fun unknownDurationSendsStartPauseResumeHeartbeatAndStop() = runBlocking {
        select(IptvVodSourceIds.XTREAM)
        save(60_000, true)
        save(120_000, false)
        save(150_000, true)
        field("lastScrobbleTime").setLong(model, 0)
        save(270_000, true)
        save(300_000, false, closing = true)
        coVerifyOrder {
            remote.scrobbleStart(MediaType.TV, 1416, 10f, 22, 15, any())
            remote.scrobblePause(MediaType.TV, 1416, 20f, 22, 15, any())
            remote.scrobbleStart(MediaType.TV, 1416, 25f, 22, 15, any())
            remote.scrobbleProgress(MediaType.TV, 1416, 45f, 22, 15, any())
            remote.scrobbleStop(MediaType.TV, 1416, 50f, 22, 15, any())
        }
        coVerify { history.saveProgress(any(), 1416, any(), any(), any(), 22, 15, any(), 0.5f,
            600L, 300L, any(), IptvVodSourceIds.XTREAM, any(), any()) }
    }

    @Test fun stalkerWithNoRuntimeStillTracksAndPersistsActualPosition() = runBlocking {
        select(IptvVodSourceIds.STALKER, runtime = 0)
        save(60_000, true)
        save(90_000, false, closing = true)
        coVerify { remote.scrobbleStart(MediaType.TV, 1416, 0f, 22, 15, any()) }
        coVerify { remote.scrobbleStop(MediaType.TV, 1416, 0f, 22, 15, any()) }
        coVerify { history.saveProgress(any(), 1416, any(), any(), any(), 22, 15, any(), 0f,
            0L, 90L, any(), IptvVodSourceIds.STALKER, any(), any()) }
        coVerify(exactly = 0) { remote.dismissContinueWatching(any(), any(), any(), any()) }
    }

    @Test fun estimatedRuntimeCannotMarkWatchedUntilTheActualEnd() = runBlocking {
        select(IptvVodSourceIds.XTREAM)
        save(1_200_000, true)
        save(1_200_000, false, closing = true)
        coVerify { remote.scrobbleStop(MediaType.TV, 1416, 79f, 22, 15, any()) }
        coVerify(exactly = 0) { remote.dismissContinueWatching(any(), any(), any(), any()) }
        save(1_250_000, false, ended = true)
        coVerify { remote.scrobbleStop(MediaType.TV, 1416, 100f, 22, 15, any()) }
        coVerify { remote.dismissContinueWatching(MediaType.TV, 1416, 22, 15) }
    }

    @Test fun liveTimelineHintCannotDeleteAMatchedVodSessionsHistory() = runBlocking {
        for (id in IptvVodSourceIds.ALL) {
            select(id)
            val manifest = AddonManifest(id = "live-parent", name = "IPTV", version = "1", types = listOf("tv"),
                resources = listOf(AddonResource(name = "stream")))
            field("currentInstalledAddons").set(model, listOf(Addon(id, "IPTV", "1", "", isInstalled = true,
                type = AddonType.COMMUNITY, manifest = manifest)))
            model.onLivePlaybackDetected()
            assertFalse(field("currentIsLiveStreamPlayback").getBoolean(model))
            save(60_000, true, duration = 600_000)
            field("lastIsPlaying").setBoolean(model, false)
        }
        coVerify(exactly = 2) { remote.scrobbleStart(MediaType.TV, 1416, 10f, 22, 15, any()) }
        coVerify(exactly = 0) { history.removeFromHistory(any(), any(), any()) }
        coVerify(exactly = 0) { trakt.removeFromContinueWatchingCache(any(), any(), any(), any()) }
    }

    @Test fun genuineLivePlaybackAndUnmatchedSourcesStayExcluded() = runBlocking {
        select(IptvVodSourceIds.XTREAM)
        field("currentIsLiveStreamPlayback").setBoolean(model, true)
        assertNull(model.saveProgress(60_000, 0, 0, true, Player.STATE_READY))
        save(60_000, true, duration = 600_000)
        field("currentIsLiveStreamPlayback").setBoolean(model, false)
        select("other-addon")
        assertNull(model.saveProgress(60_000, 0, 0, true, Player.STATE_READY))
        select(IptvVodSourceIds.XTREAM)
        field("currentMediaId").setInt(model, -1)
        assertNull(model.saveProgress(60_000, 0, 0, true, Player.STATE_READY))
        coVerify(exactly = 0) { remote.scrobbleStart(any(), any(), any(), any(), any(), any()) }
    }
}
