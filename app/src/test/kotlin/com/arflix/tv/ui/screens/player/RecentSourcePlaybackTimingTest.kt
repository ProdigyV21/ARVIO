package com.arflix.tv.ui.screens.player

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.RecentPlayedSource
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.RecentPlayedSourceRepository
import com.arflix.tv.data.repository.StreamRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

/** Playback-timing regressions for the recently played source (PR review on #790). */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = android.app.Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class RecentSourcePlaybackTimingTest {
    private lateinit var model: PlayerViewModel
    private val store = ViewModelStore()
    private val streams = mockk<StreamRepository>(relaxed = true)
    private val recentRepository = mockk<RecentPlayedSourceRepository>(relaxed = true)
    private var clockMs = 0L
    private var positionMs = 0L

    private val sourceA = stream("a")
    private val sourceB = stream("b")
    private val iptv = stream("iptv", addonId = "iptv_vod")

    @Before fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        coEvery { streams.resolveStreamForPlayback(any()) } coAnswers { firstArg() }
        model = PlayerViewModel(
            context = mockk(relaxed = true),
            profileManager = mockk(relaxed = true),
            mediaRepository = mockk(relaxed = true),
            streamRepository = streams,
            traktRepository = mockk(relaxed = true),
            remoteSyncManager = mockk(relaxed = true),
            watchHistoryRepository = mockk(relaxed = true),
            cloudSyncRepository = mockk(relaxed = true),
            launcherContinueWatchingRepository = mockk(relaxed = true),
            animeMapper = mockk(relaxed = true),
            tmdbApi = mockk(relaxed = true),
            skipIntroRepository = mockk(relaxed = true),
            playbackTelemetryRepository = mockk(relaxed = true),
            pluginManager = mockk(relaxed = true),
            streamIntegrationRepository = mockk(relaxed = true),
            recentPlayedSourceRepository = recentRepository
        )
        store.put("player", model)
        field("currentMediaId").setInt(model, 42)
        val clock: () -> Long = { clockMs }
        field("recentSourceClock").set(model, clock)
        state().value = state().value.copy(streams = listOf(sourceA, sourceB, iptv))
    }

    @After fun tearDown() {
        val job = model.viewModelScope.coroutineContext[Job]
        try {
            store.clear()
            runBlocking { withTimeout(5_000) { job?.join() } }
        } finally {
            Dispatchers.resetMain()
        }
    }

    // Review case 1: selectedStream names the replacement before its URL is applied, while the
    // old video keeps playing and reporting progress.

    @Test fun aReplacementStillResolvingIsNotSavedFromTheOldVideosTime() {
        model.selectStream(sourceA)
        play(seconds = 6) // baseline + 4 s counted

        val bResolved = CompletableDeferred<StreamSource?>()
        coEvery { streams.resolveStreamForPlayback(sourceB) } coAnswers { bResolved.await() }
        model.selectStream(sourceB)
        assertSame(sourceB, model.uiState.value.selectedStream)
        play(seconds = 10)

        verify(exactly = 0) { recentRepository.save(any(), any()) }

        bResolved.complete(sourceB)
        play(seconds = 12) // the first report after the switch only sets the baseline

        val saved = slot<RecentPlayedSource>()
        verify(exactly = 1) { recentRepository.save(any(), capture(saved)) }
        assertEquals(sourceB.url, saved.captured.url)
    }

    @Test fun aFailedSwitchKeepsCreditingTheSourceStillPlaying() {
        model.selectStream(sourceA)
        play(seconds = 6) // baseline + 4 s counted

        // B can't produce a URL: the switch fails and A never stops playing.
        val unplayable = sourceB.copy(url = null)
        model.selectStream(unplayable)
        assertSame(unplayable, model.uiState.value.selectedStream)
        play(seconds = 6)

        val saved = slot<RecentPlayedSource>()
        verify(exactly = 1) { recentRepository.save(any(), capture(saved)) }
        assertEquals(sourceA.url, saved.captured.url)
    }

    // Review case 2: Home Server / IPTV results reach autoplay through autoplaySelectBest,
    // possibly before any addon result. Once armed, the gate holds there too.

    @Test fun anEarlyBackgroundResultCannotAutoplayWhileTheRecentSourceIsAwaited() {
        armGate(recentOf(sourceB))

        autoplaySelectBest(listOf(iptv))
        assertNull(model.uiState.value.selectedStream)

        autoplaySelectBest(listOf(iptv, sourceB))
        assertSame(sourceB, model.uiState.value.selectedStream)
    }

    @Test fun anEarlyBackgroundResultAutoplaysOnceTheHoldRunsOut() {
        armGate(recentOf(sourceB))
        clockMs += RecentSourceAutoplayGate.RECENT_SOURCE_AUTOPLAY_WAIT_MS

        autoplaySelectBest(listOf(iptv))

        assertSame(iptv, model.uiState.value.selectedStream)
    }

    @Test fun withoutARecentSourceAutoplayIsNotHeld() {
        armGate(null)

        autoplaySelectBest(listOf(iptv))

        assertSame(iptv, model.uiState.value.selectedStream)
    }

    /** The playing video reports progress every 2 s for [seconds], as PlayerScreen's loop does. */
    private fun play(seconds: Int) {
        repeat(seconds / 2) {
            clockMs += 2_000L
            positionMs += 2_000L
            noteRecentSourcePlayback(positionMs, isPlaying = true)
        }
    }

    private fun noteRecentSourcePlayback(positionMs: Long, isPlaying: Boolean) {
        PlayerViewModel::class.java.getDeclaredMethod(
            "noteRecentSourcePlayback", Long::class.javaPrimitiveType, Boolean::class.javaPrimitiveType
        ).apply { isAccessible = true }.invoke(model, positionMs, isPlaying)
    }

    private fun autoplaySelectBest(candidates: List<StreamSource>) {
        PlayerViewModel::class.java.getDeclaredMethod(
            "autoplaySelectBest", List::class.java, String::class.java
        ).apply { isAccessible = true }.invoke(model, candidates, "en")
    }

    private fun armGate(recent: RecentPlayedSource?) {
        (field("recentSourceGate").get(model) as RecentSourceAutoplayGate).arm(recent, clockMs)
    }

    private fun recentOf(source: StreamSource) =
        RecentPlayedSource.of(MediaType.MOVIE, 42, null, null, source, playedAtMs = 1L)

    private fun stream(name: String, addonId: String = "torrentio") = StreamSource(
        source = "Movie 1080p $name",
        addonName = addonId,
        addonId = addonId,
        quality = "1080p",
        size = "4 GB",
        url = "https://$addonId.example/play/$name.mkv",
    )

    private fun field(name: String) = PlayerViewModel::class.java.getDeclaredField(name).apply { isAccessible = true }

    @Suppress("UNCHECKED_CAST")
    private fun state() = field("_uiState").get(model) as MutableStateFlow<PlayerUiState>
}
