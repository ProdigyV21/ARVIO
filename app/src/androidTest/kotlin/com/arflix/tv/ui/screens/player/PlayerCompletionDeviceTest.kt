package com.arflix.tv.ui.screens.player

import android.net.Uri
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.EpisodeIdentity
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import java.io.File
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Real decoder and STATE_ENDED through PlayerScreen, using only a local video fixture. */
@androidx.annotation.OptIn(UnstableApi::class)
@RunWith(AndroidJUnit4::class)
class PlayerCompletionDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val model = mockk<PlayerViewModel>(relaxed = true)
    private val visible = mutableStateOf(true)
    private lateinit var state: MutableStateFlow<PlayerUiState>
    private lateinit var player: Player
    private var exits = 0
    private var nextPlays = 0

    @Test fun movieReturnsToDetailsAtNaturalEndWithoutBack() {
        show(MediaType.MOVIE)
        endVideo()
        assertReturned()
    }

    @Test fun episodeWithAutoplayDisabledReturnsAtEnd() {
        show(MediaType.TV, autoplay = false)
        endVideo()
        assertReturned()
    }

    @Test fun lastEpisodeReturnsWhenThereIsNoNextEpisode() {
        show(MediaType.TV, next = null)
        endVideo()
        assertReturned()
    }

    @Test fun futureEpisodeNeverAutoplaysAndFinishedPlayerCloses() {
        show(MediaType.TV, resolution = NextEpisodeAirDateResolution.Blocked(NextEpisodeAirDateBlockReason.FutureAirDate))
        endVideo()
        assertReturned()
    }

    @Test fun stuckMetadataCannotLeaveABlackScreenIndefinitely() {
        coEvery { model.adjacentEpisodeIdentity(any(), any(), true) } coAnswers { awaitCancellation() }
        show(MediaType.TV, preserveMetadataStub = true)
        endVideo()
        assertReturned()
    }

    @Test fun latestAutoplaySettingOffersNextAndSlowTrackingCannotBlockContinue() {
        coEvery { model.saveProgressAndWait(any(), any(), any(), any(), any()) } coAnswers { delay(60_000) }
        show(MediaType.TV, autoplay = false)
        compose.runOnIdle { state.value = state.value.copy(autoPlayNext = true) }
        endVideo()
        compose.waitUntil(5_000) {
            compose.onAllNodes(androidx.compose.ui.test.hasText("NEXT")).fetchSemanticsNodes().isNotEmpty()
        }
        screenshot("tv-up-next")
        InstrumentationRegistry.getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.waitUntil(5_000) { nextPlays == 1 }
        compose.runOnIdle { assertEquals(0, exits); assertEquals(1, nextPlays) }
    }

    @Test fun liveStreamEndDoesNotNavigateAway() {
        show(MediaType.MOVIE, live = true)
        endVideo()
        compose.waitUntil(5_000) {
            var ended = false
            compose.runOnUiThread { ended = player.playbackState == Player.STATE_ENDED }
            ended
        }
        android.os.SystemClock.sleep(600)
        compose.runOnIdle { assertEquals(0, exits) }
    }

    @Test fun leavingDuringSlowNextTransitionCannotStartAnotherEpisode() {
        coEvery { model.saveProgressAndWait(any(), any(), any(), any(), any()) } coAnswers { delay(60_000) }
        show(MediaType.TV)
        endVideo()
        compose.waitUntil(5_000) {
            compose.onAllNodes(androidx.compose.ui.test.hasText("NEXT")).fetchSemanticsNodes().isNotEmpty()
        }
        InstrumentationRegistry.getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { visible.value = false }
        android.os.SystemClock.sleep(2_500)
        compose.runOnIdle { assertEquals(0, nextPlays) }
    }

    private fun show(
        type: MediaType,
        autoplay: Boolean = true,
        next: EpisodeIdentity? = EpisodeIdentity.canonical(1, 2),
        resolution: NextEpisodeAirDateResolution = NextEpisodeAirDateResolution.Allowed,
        preserveMetadataStub: Boolean = false,
        live: Boolean = false,
    ) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val file = File(instrumentation.targetContext.cacheDir, "completion-test.mp4")
        instrumentation.context.assets.open("seek_preview_device_test.mp4").use { input -> file.outputStream().use(input::copyTo) }
        val source = StreamSource("Local completion clip", "Fixture", "fixture", "1080p", "", url = Uri.fromFile(file).toString())
        state = MutableStateFlow(PlayerUiState(isLoading = false, title = "Completion test", selectedStream = source,
            selectedStreamUrl = source.url, streams = listOf(source), subtitlePreloadEnabled = false, autoPlayNext = autoplay))
        every { model.uiState } returns state
        every { model.isTranslatingLive } returns MutableStateFlow(false)
        if (!preserveMetadataStub) coEvery { model.adjacentEpisodeIdentity(any(), any(), true) } returns next
        coEvery { model.adjacentEpisodeIdentity(any(), any(), false) } returns null
        coEvery { model.resolveNextEpisodeAirDate(any(), any()) } returns resolution
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ArflixTvTheme {
                    if (visible.value) PlayerScreen(type, 42,
                        seasonNumber = if (type == MediaType.TV) 1 else null,
                        episodeNumber = if (type == MediaType.TV) 1 else null,
                        streamUrl = source.url, isLiveStream = live, viewModel = model,
                        onBack = { exits++; visible.value = false },
                        onPlayNext = { identity, _, _, _ -> assertEquals(next, identity); nextPlays++; visible.value = false })
                    else Text("Returned to details")
                }
            }
        }
        compose.waitUntil(20_000) {
            var ready = false
            compose.runOnUiThread {
                findPlayer(compose.activity.window.decorView)?.let {
                    player = it
                    ready = it.isPlaying && it.currentPosition > 700
                }
            }
            ready
        }
        // PlayerScreen records playback start after a first frame and a progress polling tick.
        compose.waitUntil(5_000) {
            compose.onAllNodes(androidx.compose.ui.test.hasTestTag("playerLoadingOverlay")).fetchSemanticsNodes().isEmpty()
        }
    }

    private fun endVideo() {
        compose.runOnUiThread { player.seekTo(player.duration - 150); player.play() }
    }

    private fun assertReturned() {
        compose.waitUntil(7_000) { exits == 1 }
        compose.runOnIdle { assertEquals(1, exits); assertEquals(0, nextPlays) }
        coVerify { model.saveProgressAndWait(any(), any(), 100, false, Player.STATE_ENDED) }
    }

    private fun findPlayer(view: View): Player? {
        if (view is PlayerView) return view.player
        if (view is ViewGroup) for (i in 0 until view.childCount) findPlayer(view.getChildAt(i))?.let { return it }
        return null
    }

    private fun screenshot(name: String) {
        val file = File(InstrumentationRegistry.getInstrumentation().targetContext.getExternalFilesDir(null), "$name.png")
        file.outputStream().use { compose.onRoot().captureToImage().asAndroidBitmap().compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
    }
}
