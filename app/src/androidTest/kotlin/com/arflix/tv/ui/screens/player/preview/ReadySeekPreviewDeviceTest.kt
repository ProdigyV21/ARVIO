package com.arflix.tv.ui.screens.player.preview

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arflix.tv.ui.screens.player.mobile.MobilePlayerBottomSection
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReadySeekPreviewDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun missingStaleAndUnverifiedFramesHaveNoPreviewIncludingExitAnimation() {
        val blue = frame(android.graphics.Color.BLUE, 10_000L)
        val current = mutableStateOf<SeekPreviewFrame?>(null)
        val position = mutableStateOf(10_000L)
        compose.mainClock.autoAdvance = false
        compose.setContent {
            Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
                ReadySeekPreview(current.value, position.value, Modifier.size(200.dp, 112.5.dp), sourceGeneration = 7L)
            }
        }
        compose.onNodeWithTag(READY).assertDoesNotExist()
        compose.runOnIdle { current.value = blue }
        compose.mainClock.advanceTimeBy(128)
        compose.onNodeWithTag(READY).assertExists()
        assertPreviewColor(android.graphics.Color.BLUE)
        compose.runOnIdle { position.value = 60_000L }
        compose.mainClock.advanceTimeByFrame()
        compose.onNodeWithTag(READY).assertDoesNotExist()
        compose.runOnIdle {
            position.value = 10_000L
            current.value = blue.copy(sourceGeneration = 6L)
        }
        compose.mainClock.advanceTimeByFrame()
        compose.onNodeWithTag(READY).assertDoesNotExist()
        compose.runOnIdle { current.value = blue.copy(validity = SeekPreviewValidity.UNVERIFIED) }
        compose.mainClock.advanceTimeByFrame()
        compose.onNodeWithTag(READY).assertDoesNotExist()
        compose.runOnIdle { current.value = blue }
        compose.mainClock.advanceTimeBy(128)
        compose.onNodeWithTag(READY).assertExists()
        compose.runOnIdle { current.value = null }
        compose.mainClock.advanceTimeByFrame()
        compose.onNodeWithTag(READY).assertDoesNotExist()
    }

    @Test fun adjacentReadyFramesReplaceImmediatelyWithoutCrossfadingOldPosition() {
        val current = mutableStateOf(frame(android.graphics.Color.BLUE, 10_000L))
        compose.mainClock.autoAdvance = false
        compose.setContent {
            Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
                ReadySeekPreview(current.value, current.value.positionMs, Modifier.size(200.dp, 112.5.dp))
            }
        }
        compose.mainClock.advanceTimeBy(128)
        assertPreviewColor(android.graphics.Color.BLUE)
        compose.runOnIdle { current.value = frame(android.graphics.Color.GREEN, 60_000L) }
        compose.mainClock.advanceTimeByFrame()
        assertPreviewColor(android.graphics.Color.GREEN)
    }

    @Test fun mobileScrubberStaysInPlaceWhilePreviewLoadsChangesAndFails() {
        val current = mutableStateOf<SeekPreviewFrame?>(null)
        val position = mutableStateOf(30_000L)
        compose.setContent {
            Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.BottomCenter) {
                MobilePlayerBottomSection(
                    eyebrow = "", mainTitle = "Preview readiness test", currentPositionMs = 5_000L,
                    durationMs = 100_000L, bufferedPositionMs = 80_000L,
                    isScrubbing = true, scrubPreviewMs = position.value, seekPreviewFrame = current.value,
                    currentAudioTrack = "English", currentSubtitleTrack = "Off",
                    currentPlaybackSpeed = 1f, isEpisodeListAvailable = false, isPromptShowing = false,
                    onOpenSources = {}, onOpenEpisodes = {}, onOpenAudio = {}, onOpenSubtitles = {},
                    onOpenSpeed = {}, onSeekStart = {}, onSeekMove = {}, onSeekEnd = {}, onSeekCancel = {},
                )
            }
        }
        val bounds = compose.onNodeWithTag(SCRUBBER).fetchSemanticsNode().boundsInRoot
        compose.onNodeWithTag(READY).assertDoesNotExist()
        compose.runOnIdle { current.value = frame(android.graphics.Color.BLUE, 30_000L) }
        compose.onNodeWithTag(READY).assertExists()
        assertEquals(bounds, compose.onNodeWithTag(SCRUBBER).fetchSemanticsNode().boundsInRoot)
        val previewBounds = compose.onNodeWithTag(READY).fetchSemanticsNode().boundsInRoot
        // Four dp above the 18dp playhead, not above the larger invisible touch target.
        assertEquals(with(compose.density) { 13.dp.toPx() }, bounds.center.y - previewBounds.bottom, 1f)
        compose.runOnIdle { position.value = 70_000L }
        compose.onNodeWithTag(READY).assertDoesNotExist()
        assertEquals(bounds, compose.onNodeWithTag(SCRUBBER).fetchSemanticsNode().boundsInRoot)
        compose.runOnIdle { current.value = frame(android.graphics.Color.GREEN, 70_000L) }
        compose.onNodeWithTag(READY).assertExists()
        assertEquals(bounds, compose.onNodeWithTag(SCRUBBER).fetchSemanticsNode().boundsInRoot)
        compose.runOnIdle { current.value = null }
        compose.onNodeWithTag(READY).assertDoesNotExist()
        assertEquals(bounds, compose.onNodeWithTag(SCRUBBER).fetchSemanticsNode().boundsInRoot)
    }

    private fun frame(color: Int, positionMs: Long) = SeekPreviewFrame(
        bitmap = Bitmap.createBitmap(320, 180, Bitmap.Config.ARGB_8888).apply { eraseColor(color) },
        positionMs = positionMs, sourceGeneration = 7L,
    )

    private fun assertPreviewColor(expected: Int) {
        val bitmap = compose.onNodeWithTag(READY).captureToImage().asAndroidBitmap()
        assertEquals(expected, bitmap.getPixel(bitmap.width / 2, bitmap.height / 2))
    }

    companion object {
        private const val READY = "seek_preview_ready"
        private const val SCRUBBER = "mobile_player_scrubber"
    }
}
