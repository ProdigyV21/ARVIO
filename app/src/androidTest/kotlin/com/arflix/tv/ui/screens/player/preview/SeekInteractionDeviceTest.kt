package com.arflix.tv.ui.screens.player.preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SeekInteractionDeviceTest {
    @Test
    fun controllerRetainsResumeIntentDuringLongBrowseAndDirectionChanges() {
        for (playing in listOf(false, true)) {
            for (surface in SeekSurface.values()) {
                val first = SeekInteraction().step(surface, 20_000L, START_MS, DURATION_MS, playing, 100L)
                val reversed = first.step(surface, -10_000L, 1L, DURATION_MS, false, 20_000L)

                assertTrue(reversed.browsing)
                assertEquals(START_MS, reversed.originMs)
                assertEquals(START_MS + 10_000L, reversed.targetMs)
                assertEquals(playing, reversed.resumeAfterBrowse)
            }
        }
    }

    @Test
    fun controllerRetainsNonzeroDragTargetThroughExitAndRestartsFromPlayback() {
        for (playing in listOf(false, true)) {
            val drag = SeekInteraction().dragTo(600_000L, START_MS, DURATION_MS, playing)
                .dragTo(650_000L, START_MS, DURATION_MS, false)
            val exiting = drag.finish()
            val idle = exiting.afterExit()

            assertEquals(SeekPhase.Exiting, exiting.phase)
            assertFalse(exiting.browsing)
            assertEquals(650_000L, exiting.targetMs)
            assertEquals(650_000L, idle.targetMs)
            assertEquals(playing, idle.resumeAfterBrowse)
            assertEquals(SeekPhase.Idle, idle.phase)
            val next = idle.step(SeekSurface.Quick, -10_000L, 800_000L, DURATION_MS, false, 30_000L)
            assertEquals(790_000L, next.targetMs)
            assertFalse(next.resumeAfterBrowse)
        }
    }
}

/** Tests the real reducer in a media-free UI adapter, not PlayerScreen or ExoPlayer wiring. */
@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class SeekInteractionComposeDeviceTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun singleForwardSkipSeeksOnceWithoutOpeningBrowserOrPausing() {
        val harness = start(playing = true)
        press(Key.DirectionRight)

        compose.onNodeWithTag(BROWSER).assertDoesNotExist()
        compose.onNodeWithTag(OVERLAY_TARGET).assertTextEquals("310000")
        compose.runOnIdle {
            assertEquals(SeekPhase.QuickSkip, harness.interaction.phase)
            assertEquals(listOf(310_000L), harness.player.seeks)
            assertEquals(310_000L, harness.player.positionMs)
            assertTrue(harness.player.playing)
            assertEquals(0, harness.player.pauseCalls)
        }
        press(Key.DirectionCenter)
        compose.runOnIdle { assertEquals(listOf(310_000L), harness.player.seeks) }
    }

    @Test
    fun singleBackwardSkipKeepsPausedPlaybackPaused() {
        val harness = start(playing = false)
        press(Key.DirectionLeft)

        compose.onNodeWithTag(BROWSER).assertDoesNotExist()
        compose.onNodeWithTag(OVERLAY_TARGET).assertTextEquals("290000")
        compose.runOnIdle {
            assertEquals(listOf(290_000L), harness.player.seeks)
            assertFalse(harness.player.playing)
            assertEquals(0, harness.player.pauseCalls)
        }
    }

    @Test
    fun repeatedBrowseSelectCommitsOnceDespiteDuplicateSelect() {
        val harness = start(playing = true)
        repeat(3) { press(Key.DirectionRight) }

        compose.onNodeWithTag(BROWSER).assertIsDisplayed()
        compose.onNodeWithTag(OVERLAY_TARGET).assertTextEquals("330000")
        compose.runOnIdle {
            assertEquals(listOf(310_000L), harness.player.seeks)
            assertEquals(310_000L, harness.player.positionMs)
            assertFalse(harness.player.playing)
            assertTrue(harness.interaction.resumeAfterBrowse)
            assertEquals(1, harness.player.pauseCalls)
        }

        compose.onNodeWithTag(INPUT).performKeyInput {
            pressKey(Key.DirectionCenter)
            pressKey(Key.DirectionCenter)
        }
        compose.onNodeWithTag(BROWSER).assertDoesNotExist()
        compose.runOnIdle {
            assertEquals(listOf(310_000L, 330_000L), harness.player.seeks)
            assertEquals(330_000L, harness.player.positionMs)
            assertTrue(harness.player.playing)
            assertEquals(listOf(true), harness.player.restoredPlayStates)
        }
    }

    @Test
    fun heldDirectionEntersBrowseAndCancelPreservesAlreadySkippedPlayback() {
        val harness = start(playing = true)
        compose.onNodeWithTag(INPUT).performKeyInput {
            keyDown(Key.DirectionRight)
            // Key injection generates repeated DOWN events after the initial hold delay.
            advanceEventTime(750L)
            keyUp(Key.DirectionRight)
        }

        compose.onNodeWithTag(BROWSER).assertIsDisplayed()
        compose.runOnIdle {
            assertTrue(harness.repeatedKeyDowns > 0)
            assertTrue(harness.interaction.targetMs > 310_000L)
            assertEquals(listOf(310_000L), harness.player.seeks)
            assertFalse(harness.player.playing)
            assertEquals(1, harness.player.pauseCalls)
        }
        press(Key.Back)
        compose.onNodeWithTag(BROWSER).assertDoesNotExist()
        compose.runOnIdle {
            assertEquals(listOf(310_000L), harness.player.seeks)
            assertEquals(310_000L, harness.player.positionMs)
            assertTrue(harness.player.playing)
            assertEquals(listOf(true), harness.player.restoredPlayStates)
        }
    }

    @Test
    fun controlsBrowseCancelDoesNotSeekOrResumeInitiallyPausedPlayback() {
        val harness = start(playing = false, surface = SeekSurface.Controls)
        press(Key.DirectionRight)
        press(Key.DirectionRight)
        compose.onNodeWithTag(BROWSER).assertIsDisplayed()
        press(Key.Back)

        compose.runOnIdle {
            assertTrue(harness.player.seeks.isEmpty())
            assertEquals(START_MS, harness.player.positionMs)
            assertFalse(harness.player.playing)
            assertEquals(listOf(false), harness.player.restoredPlayStates)
        }
    }

    @Test
    fun controlsBrowseCommitSeeksOnceAndKeepsInitiallyPausedPlaybackPaused() {
        val harness = start(playing = false, surface = SeekSurface.Controls)
        press(Key.DirectionRight)
        press(Key.DirectionRight)
        press(Key.DirectionCenter)
        press(Key.DirectionCenter)

        compose.runOnIdle {
            assertEquals(listOf(320_000L), harness.player.seeks)
            assertEquals(320_000L, harness.player.positionMs)
            assertFalse(harness.player.playing)
            assertEquals(listOf(false), harness.player.restoredPlayStates)
        }
    }

    @Test
    fun commitExitAnimationNeverRendersAnUnrelatedZeroTarget() {
        assertExitTargetRetained(commit = true)
    }

    @Test
    fun cancelExitAnimationNeverRendersAnUnrelatedZeroTarget() {
        assertExitTargetRetained(commit = false)
    }

    private fun assertExitTargetRetained(commit: Boolean) {
        compose.mainClock.autoAdvance = false
        val harness = start(playing = true)
        press(Key.DirectionRight)
        press(Key.DirectionRight)
        compose.onNodeWithTag(OVERLAY_TARGET).assertTextEquals("320000")
        press(if (commit) Key.DirectionCenter else Key.Back)

        compose.runOnIdle { assertEquals(SeekPhase.Exiting, harness.interaction.phase) }
        compose.onNodeWithTag(OVERLAY_TARGET).assertTextEquals("320000")
        compose.mainClock.advanceTimeBy(64L)
        compose.onNodeWithTag(OVERLAY_TARGET).assertTextEquals("320000")
        compose.mainClock.advanceTimeBy(200L)
        compose.onNodeWithTag(OVERLAY_TARGET).assertDoesNotExist()
        compose.runOnIdle {
            assertEquals(SeekPhase.Idle, harness.interaction.phase)
            assertEquals(320_000L, harness.interaction.targetMs)
            assertEquals(if (commit) listOf(310_000L, 320_000L) else listOf(310_000L), harness.player.seeks)
            assertTrue(harness.player.playing)
        }
    }

    private fun start(playing: Boolean, surface: SeekSurface = SeekSurface.Quick): SeekUiHarness {
        val harness = SeekUiHarness(FakePlayback(playing), surface)
        compose.setContent { SeekHarnessContent(harness) }
        compose.mainClock.advanceTimeByFrame()
        compose.onNodeWithTag(INPUT).assertIsFocused()
        return harness
    }

    private fun press(key: Key) {
        compose.onNodeWithTag(INPUT).performKeyInput { pressKey(key) }
        compose.mainClock.advanceTimeByFrame()
    }
}

private class FakePlayback(var playing: Boolean) {
    var positionMs = START_MS
        private set
    var pauseCalls = 0
        private set
    val seeks = mutableListOf<Long>()
    val restoredPlayStates = mutableListOf<Boolean>()

    fun seekTo(positionMs: Long) {
        seeks += positionMs
        this.positionMs = positionMs
    }

    fun pause() {
        pauseCalls++
        playing = false
    }

    fun restorePlaying(playing: Boolean) {
        restoredPlayStates += playing
        this.playing = playing
    }
}

private class SeekUiHarness(val player: FakePlayback, private val surface: SeekSurface) {
    var interaction by mutableStateOf(SeekInteraction())
    var repeatedKeyDowns = 0

    // This is a test-only callback adapter. The production reducer owns target/phase/resume intent.
    fun step(deltaMs: Long, eventTimeMs: Long) {
        val previous = interaction
        val next = previous.step(surface, deltaMs, player.positionMs, DURATION_MS, player.playing, eventTimeMs)
        if (next.browsing && !previous.browsing) player.pause()
        if (next.phase == SeekPhase.QuickSkip) player.seekTo(next.targetMs)
        interaction = next
    }

    fun finish(commit: Boolean) {
        val previous = interaction
        if (previous.browsing) {
            if (commit) player.seekTo(previous.targetMs)
            player.restorePlaying(previous.resumeAfterBrowse)
        }
        interaction = previous.finish()
    }
}

@Composable
private fun SeekHarnessContent(harness: SeekUiHarness) {
    val focus = remember { FocusRequester() }
    LaunchedEffect(Unit) { focus.requestFocus() }
    LaunchedEffect(harness.interaction.phase) {
        if (harness.interaction.phase == SeekPhase.Exiting) {
            delay(EXIT_MS.toLong())
            harness.interaction = harness.interaction.afterExit()
        }
    }

    Column(
        Modifier
            .testTag(INPUT)
            .onPreviewKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown) {
                    false
                } else {
                    when (event.key) {
                        Key.DirectionLeft, Key.DirectionRight -> {
                            if (event.nativeKeyEvent.repeatCount > 0) harness.repeatedKeyDowns++
                            harness.step(
                                if (event.key == Key.DirectionRight) 10_000L else -10_000L,
                                event.nativeKeyEvent.eventTime,
                            )
                            true
                        }
                        Key.DirectionCenter, Key.Enter -> {
                            harness.finish(commit = true)
                            true
                        }
                        Key.Back, Key.Escape -> {
                            harness.finish(commit = false)
                            true
                        }
                        else -> false
                    }
                }
            }
            .focusRequester(focus)
            .focusable(),
    ) {
        BasicText(harness.interaction.phase.name)
        if (harness.interaction.browsing) {
            BasicText("Browse", Modifier.testTag(BROWSER))
        }
        AnimatedVisibility(
            visible = harness.interaction.quickVisible,
            enter = EnterTransition.None,
            exit = fadeOut(tween(EXIT_MS)),
        ) {
            BasicText(harness.interaction.targetMs.toString(), Modifier.testTag(OVERLAY_TARGET))
        }
    }
}

private const val START_MS = 300_000L
private const val DURATION_MS = 3_600_000L
private const val EXIT_MS = 150
private const val INPUT = "seek-input"
private const val BROWSER = "seek-browser"
private const val OVERLAY_TARGET = "seek-overlay-target"
