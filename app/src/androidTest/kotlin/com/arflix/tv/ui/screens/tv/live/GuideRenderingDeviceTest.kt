package com.arflix.tv.ui.screens.tv.live

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class, ExperimentalComposeUiApi::class)
class GuideRenderingDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val now = 1_783_000_000_000L / 1_800_000L * 1_800_000L
    private val rows by lazy {
        (0 until 55_000).map { index ->
            IptvChannel(id = "render:$index", name = "Channel $index", group = "News",
                streamUrl = "https://example.test/live", catchupDays = 2).enrichForFastStartup(index + 1)
        }
    }
    private val guide by lazy {
        rows.take(144).associate { row ->
            val programs = (0 until 24).map { slot ->
                val start = now - 120 * 60_000L + slot * 30 * 60_000L
                IptvProgram("Programme ${row.id}:$slot", "A programme description for rendering cost.",
                    start, start + 30 * 60_000L, catchupAvailable = true)
            }
            row.id to IptvNowNext(now = programs[4], next = programs[5],
                recent = programs.take(4), upcoming = programs.drop(5))
        }
    }
    private var focused = ""
    private var focusedTitle = ""
    private var longPressed = false

    private fun showGuide() {
        val mode = mutableStateOf(EpgGridFocusMode.ChannelList)
        compose.setContent {
            val inputModeManager = LocalInputModeManager.current
            LaunchedEffect(inputModeManager) {
                check(inputModeManager.requestInputMode(InputMode.Keyboard))
            }
            Box(Modifier.width(900.dp).height(400.dp)) {
                EpgGrid(channels = rows.take(144), totalChannelCount = 55_000,
                    clockTickMillis = now, nowNext = guide, selectedChannelId = "render:0",
                    focusSelectedChannelSignal = 1, scrollResetKey = "render-test",
                    favorites = emptySet(), onChannelSelect = {}, gridFocused = true,
                    onChannelFocused = { focused = it.id }, focusMode = mode.value,
                    onChannelLongPress = { _, _ -> longPressed = true },
                    onProgramFocused = { _, programme -> focusedTitle = programme.title },
                    onEnterEpg = { mode.value = EpgGridFocusMode.Epg },
                    onExitEpg = { mode.value = EpgGridFocusMode.ChannelList })
            }
        }
        compose.waitForIdle()
    }

    @Test fun channelModeDoesNotComposeTheEntireDayForEveryVisibleRow() {
        showGuide()
        val count = compose.onAllNodes(hasText("Programme ", substring = true),
            useUnmergedTree = false).fetchSemanticsNodes().size
        Log.i("GuideRenderCells", "composedProgrammeCells=$count")
        assertTrue("Visible guide has no programmes", count > 0)
        // Denser rows expose more channels; bound work by the viewport instead
        // of the old 42dp layout's absolute cell count.
        val programmeRows = compose.onAllNodes(hasText("Programme ", substring = true))
            .fetchSemanticsNodes().mapNotNull { node ->
                node.config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text
                    ?.takeIf { it.startsWith("Programme ") }?.substringBeforeLast(":")
            }.groupingBy { it }.eachCount()
        assertTrue("Too many composed channel rows: ${programmeRows.size}", programmeRows.size <= 12)
        assertTrue("Programme composition must stay horizontally bounded: $programmeRows", programmeRows.values.all { it <= 11 })
        compose.onNodeWithText("Programme render:0:4").assertIsDisplayed()
        // Channel-mode rendering exposes one entry without a child Text layout.
        compose.onAllNodes(hasText("Programme render:0:4"), useUnmergedTree = true)
            .assertCountEquals(1)
        compose.onNodeWithText("Programme render:0:23").assertDoesNotExist()
        val rulerCount = compose.onAllNodes(SemanticsMatcher("time ruler label") {
            it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("iptv-time-slot:") == true
        }).fetchSemanticsNodes().size
        assertTrue("Ruler labels should be viewport-bounded: $rulerCount", rulerCount in 1..15)
        compose.onNodeWithTag("iptv-time-slot:23").assertDoesNotExist()
    }

    @Test fun verticalChannelNavigationCannotEnterProgrammes() {
        showGuide()
        repeat(12) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
        compose.onNodeWithTag("iptv-channel:render:12").assertIsFocused()
        repeat(5) { compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) } }
        compose.onNodeWithTag("iptv-channel:render:7").assertIsFocused()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        compose.onNodeWithTag("iptv-channel:render:7").assertIsNotFocused()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        compose.runOnIdle { assertEquals("render:8", focused) }
    }

    @Test fun liveProgrammeHasOneAccessibleEntryWithAnAction() {
        showGuide()
        compose.onNodeWithText("Programme render:0:4")
            .assertHasClickAction()
            .assert(hasText("A programme description for rendering cost."))
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        compose.onNodeWithText("Programme render:0:4").assertIsFocused()
    }

    @Test fun channelKeepsFocusAndAccessibleLongPress() {
        showGuide()
        compose.onNodeWithTag("iptv-channel:render:0")
            .assertIsFocused().assertHasClickAction().assert(hasText("Channel 0"))
            .performSemanticsAction(SemanticsActions.OnLongClick)
        compose.runOnIdle { assertTrue(longPressed) }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        compose.onNodeWithTag("iptv-channel:render:1").assertIsFocused()
    }

    @Test fun epgNavigationStillReachesOffscreenProgrammesAndAdjacentChannel() {
        showGuide()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        repeat(10) { compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) } }
        compose.onNodeWithTag("iptv-time-slot:14").assertExists()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        compose.runOnIdle { assertEquals("render:1", focused) }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) }
        compose.runOnIdle { assertEquals("render:0", focused) }
        compose.runOnIdle { assertTrue(focusedTitle.startsWith("Programme render:0:")) }
    }

    @Test fun offscreenProgrammeCanBeRevealedAndNavigatedBackWithoutLosingFocus() {
        showGuide()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        repeat(16) { compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) } }
        compose.onNodeWithText("Programme render:0:20").assertIsFocused().assertIsDisplayed()
        repeat(14) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
        compose.onNodeWithText("Programme render:14:20").assertIsFocused().assertIsDisplayed()
        repeat(16) { compose.onRoot().performKeyInput { pressKey(Key.DirectionLeft) } }
        compose.onNodeWithText("Programme render:14:4").assertIsFocused().assertIsDisplayed()
    }

    @Test fun sustainedChannelScrollKeepsItsPosition() {
        showGuide()
        repeat(60) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
        compose.onNodeWithTag("iptv-channel:render:60").assertIsFocused()
        repeat(40) { compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) } }
        compose.onNodeWithTag("iptv-channel:render:20").assertIsFocused()
    }

    @Test fun rapidChannelKeysRetainTheRequestedIndex() {
        showGuide()
        compose.onRoot().performKeyInput { repeat(26) { pressKey(Key.DirectionDown) } }
        compose.waitUntil(5_000) {
            compose.onAllNodes(hasTestTag("iptv-channel:render:26") and isFocused())
                .fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNodeWithTag("iptv-channel:render:26").assertIsFocused()
        compose.onRoot().performKeyInput { repeat(10) { pressKey(Key.DirectionUp) } }
        compose.waitUntil(5_000) {
            compose.onAllNodes(hasTestTag("iptv-channel:render:16") and isFocused())
                .fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNodeWithTag("iptv-channel:render:16").assertIsFocused()
    }

}
