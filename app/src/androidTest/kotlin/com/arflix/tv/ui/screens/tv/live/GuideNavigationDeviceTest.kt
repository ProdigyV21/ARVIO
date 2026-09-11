package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.ui.Modifier
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.semantics.SemanticsActions
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
import kotlinx.coroutines.delay

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class, ExperimentalComposeUiApi::class)
class GuideNavigationDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val rows by lazy { (0 until 55_000).map { index ->
        IptvChannel(id = "test:$index", name = "Channel $index", group = "News",
            streamUrl = "https://example.test/live").enrichForFastStartup(index + 1)
    } }

    @Test fun programmeNavigationRevealsOffscreenRowsWithoutLosingFocus() {
        val mode = mutableStateOf(EpgGridFocusMode.ChannelList)
        var focused = ""
        val now = 1_783_000_000_000L
        val guide = rows.take(144).associate { channel ->
            channel.id to IptvNowNext(now = IptvProgram("Current ${channel.name}",
                startUtcMillis = now - 60_000L, endUtcMillis = now + 3_600_000L))
        }
        compose.setContent {
            Box(Modifier.width(900.dp).height(400.dp)) {
                EpgGrid(channels = rows.take(144), clockTickMillis = 1_783_000_000_000L,
                    nowNext = guide, selectedChannelId = "test:0", focusSelectedChannelSignal = 1,
                    scrollResetKey = "all", onChannelSelect = {}, favorites = emptySet(),
                    focusMode = mode.value, onEnterEpg = { mode.value = EpgGridFocusMode.Epg },
                    onChannelFocused = { focused = it.id })
            }
        }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        repeat(30) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
        compose.runOnIdle { assertEquals("test:30", focused) }
        repeat(20) { compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) } }
        compose.runOnIdle { assertEquals("test:10", focused) }
    }

    @Test fun backClosesMenuInsteadOfMovingTheGuideBehindIt() {
        val menuOpen = mutableStateOf(true)
        var guideBacks = 0
        lateinit var dispatcher: OnBackPressedDispatcher
        compose.setContent {
            dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
            BackHandler(enabled = menuOpen.value) { menuOpen.value = false }
            EpgGrid(channels = rows.take(8), clockTickMillis = 1_783_000_000_000L,
                nowNext = emptyMap(), selectedChannelId = "test:0", focusSelectedChannelSignal = 1,
                scrollResetKey = "all", onChannelSelect = {}, favorites = emptySet(),
                gridFocused = true, backHandlingEnabled = !menuOpen.value,
                onMoveLeftFromChannels = { guideBacks++ })
        }
        compose.runOnIdle { dispatcher.onBackPressed() }
        compose.runOnIdle { assertFalse(menuOpen.value); assertEquals(0, guideBacks) }
        compose.runOnIdle { dispatcher.onBackPressed() }
        compose.runOnIdle { assertEquals(1, guideBacks) }
    }

    @Test fun remoteScrollCrossesPageBoundariesAndCanReverseWithoutReset() {
        val channels = mutableStateOf(rows.take(144))
        val requested = mutableStateOf(144)
        var focused = ""
        compose.setContent {
            LaunchedEffect(requested.value) {
                delay(100L)
                channels.value = rows.take(requested.value)
            }
            Box(Modifier.width(900.dp).height(400.dp)) {
                EpgGrid(channels = channels.value, totalChannelCount = rows.size,
                    clockTickMillis = 1_783_000_000_000L, nowNext = emptyMap(),
                    selectedChannelId = "test:0", focusSelectedChannelSignal = 1,
                    scrollResetKey = "all", onChannelSelect = {}, favorites = emptySet(),
                    onChannelFocused = { focused = it.id },
                    onRequestNextChannels = {
                        requested.value = nextGuidePageLimit(channels.value.size, requested.value, rows.size)
                    })
            }
        }
        compose.waitForIdle()
        repeat(170) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        }
        compose.runOnIdle { assertEquals("test:170", focused) }
        repeat(20) { compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) } }
        compose.runOnIdle { assertEquals("test:150", focused) }
        compose.onNodeWithTag("iptv-channel:test:150").assertIsDisplayed()
    }

    @Test fun touchScrollSurvivesAppendAndMetadataRefresh() {
        val channels = mutableStateOf(rows.take(144))
        var firstVisible = 0
        compose.setContent {
            Box(Modifier.width(380.dp).height(620.dp)) {
                EpgGrid(channels = channels.value, totalChannelCount = rows.size,
                    clockTickMillis = 1_783_000_000_000L, nowNext = emptyMap(), compact = true,
                    selectedChannelId = null, focusSelectedChannelSignal = 0,
                    scrollResetKey = "all", onChannelSelect = {}, favorites = emptySet(),
                    onVisibleChannelRange = { first, _ -> firstVisible = first })
            }
        }
        compose.onNodeWithTag("iptv-guide").performScrollToIndex(90)
        compose.waitForIdle()
        var before = 0
        compose.runOnIdle { before = firstVisible; channels.value = rows.take(336) }
        compose.waitForIdle()
        compose.runOnIdle { assertEquals(before, firstVisible) }
        compose.onNodeWithTag("iptv-guide").performTouchInput { swipeUp() }
        compose.waitForIdle()
        compose.runOnIdle { assertTrue(firstVisible > before) }
    }

    @Test fun favoriteReorderKeepsStableFocusedChannel() {
        val channels = mutableStateOf(rows.take(8))
        compose.setContent {
            EpgGrid(channels = channels.value, clockTickMillis = 1_783_000_000_000L,
                nowNext = emptyMap(), selectedChannelId = "test:3", focusSelectedChannelSignal = 1,
                scrollResetKey = "fav", onChannelSelect = {}, favorites = setOf("test:3"))
        }
        compose.waitForIdle()
        compose.runOnIdle {
            channels.value = channels.value.toMutableList().apply { add(2, removeAt(3)) }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("iptv-channel:test:3").assertIsFocused()
    }

    @Test fun removingFocusedFavoriteFocusesTheNextChannel() {
        val channels = mutableStateOf(rows.take(8))
        compose.setContent {
            EpgGrid(channels = channels.value, clockTickMillis = 1_783_000_000_000L,
                nowNext = emptyMap(), selectedChannelId = "test:3", focusSelectedChannelSignal = 1,
                scrollResetKey = "fav", onChannelSelect = {}, favorites = setOf("test:3"),
                gridFocused = true)
        }
        compose.waitForIdle()
        compose.runOnIdle { channels.value = channels.value.filterNot { it.id == "test:3" } }
        compose.waitForIdle()
        compose.onNodeWithTag("iptv-channel:test:4").assertIsFocused()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        compose.onNodeWithTag("iptv-channel:test:5").assertIsFocused()
    }

    @Test fun touchCategoryMenuHidesGroupWithoutLeavingHiddenShortcut() {
        val hidden = mutableStateOf(emptySet<String>())
        compose.setContent {
            val state = buildPagedStartupChannelState(
                channels = rows.take(1).map { it.source }, totalChannelCount = 55_000,
                playlistGroupCounts = listOf(Triple("test", "News", 54_990), Triple("test", "Movies", 10)),
                favorites = emptySet(), recents = emptySet(), hiddenGroups = hidden.value)
            CategorySidebar(tree = state.tree, selectedId = "all", expanded = true,
                listState = rememberLazyListState(), onSelect = {}, onOpenSearch = {},
                isTouchDevice = true,
                onHideCategory = { playlist, group -> hidden.value = setOf("$playlist|$group") })
        }
        compose.onNodeWithText("News").performTouchInput { longClick() }
        compose.onNodeWithText("Hide category").performClick()
        compose.waitForIdle()
        compose.runOnIdle { assertEquals(setOf("test|News"), hidden.value) }
        compose.onNodeWithText("News").assertDoesNotExist()
        compose.onNodeWithText("Movies").assertIsDisplayed()
    }

    @Test fun restoringFocusToVisibleChannelDoesNotPinItToTop() {
        val selected = mutableStateOf("test:0")
        val signal = mutableStateOf(1)
        var firstVisible = 0
        compose.setContent {
            Box(Modifier.width(900.dp).height(400.dp)) {
                EpgGrid(channels = rows.take(336), totalChannelCount = rows.size,
                    clockTickMillis = 1_783_000_000_000L, nowNext = emptyMap(),
                    selectedChannelId = selected.value, focusSelectedChannelSignal = signal.value,
                    scrollResetKey = "all", onChannelSelect = {}, favorites = emptySet(),
                    onVisibleChannelRange = { first, _ -> firstVisible = first })
            }
        }
        compose.onNodeWithTag("iptv-guide").performScrollToIndex(90)
        compose.runOnIdle { selected.value = "test:92"; signal.value = 2 }
        compose.waitForIdle()
        compose.onNodeWithTag("iptv-channel:test:92").assertIsFocused()
        compose.runOnIdle { assertEquals(90, firstVisible) }
    }

    @Test fun touchCategorySwitchRestoresIndependentScrollPositions() {
        val category = mutableStateOf("provider-a|all")
        var firstVisible = 0
        compose.setContent {
            Box(Modifier.width(380.dp).height(620.dp)) {
                EpgGrid(channels = rows.take(336), totalChannelCount = rows.size,
                    clockTickMillis = 1_783_000_000_000L, nowNext = emptyMap(), compact = true,
                    selectedChannelId = null, focusSelectedChannelSignal = 0,
                    scrollResetKey = category.value, onChannelSelect = {}, favorites = emptySet(),
                    onVisibleChannelRange = { first, _ -> firstVisible = first })
            }
        }
        compose.onNodeWithTag("iptv-guide").performScrollToIndex(180)
        compose.runOnIdle { category.value = "provider-b|all" }
        compose.waitForIdle()
        compose.runOnIdle { assertEquals(0, firstVisible) }
        compose.onNodeWithTag("iptv-guide").performScrollToIndex(40)
        compose.runOnIdle { category.value = "provider-a|all" }
        compose.waitForIdle()
        compose.runOnIdle { assertEquals(180, firstVisible) }
    }

    @Test fun pendingCategoryRowsDoNotEraseSavedScrollPosition() {
        val category = mutableStateOf("all")
        val channels = mutableStateOf(rows.take(336))
        var firstVisible = 0
        compose.setContent {
            Box(Modifier.width(900.dp).height(400.dp)) {
                EpgGrid(channels = channels.value, totalChannelCount = channels.value.size,
                    clockTickMillis = 1_783_000_000_000L, nowNext = emptyMap(),
                    selectedChannelId = null, focusSelectedChannelSignal = 0,
                    scrollResetKey = category.value, onChannelSelect = {}, favorites = emptySet(),
                    onVisibleChannelRange = { first, _ -> firstVisible = first })
            }
        }
        compose.onNodeWithTag("iptv-guide").performScrollToIndex(90)
        compose.runOnIdle { channels.value = emptyList() }
        compose.waitForIdle()
        compose.runOnIdle { category.value = "fav" }
        compose.waitForIdle()
        compose.runOnIdle { channels.value = rows.take(1) }
        compose.waitForIdle()
        compose.runOnIdle { channels.value = emptyList() }
        compose.waitForIdle()
        compose.runOnIdle { category.value = "all" }
        compose.waitForIdle()
        compose.runOnIdle { channels.value = rows.take(336) }
        compose.waitForIdle()
        compose.runOnIdle { assertEquals(90, firstVisible) }
        compose.onNodeWithTag("iptv-guide").performTouchInput { swipeUp() }
        compose.waitForIdle()
        compose.runOnIdle { assertTrue(firstVisible > 90) }
    }

    @Test fun hidingFocusedCategoryKeepsRemoteFocusInTheDrawer() {
        val hidden = mutableStateOf(emptySet<String>())
        compose.setContent {
            val inputModeManager = LocalInputModeManager.current
            LaunchedEffect(inputModeManager) {
                check(inputModeManager.requestInputMode(InputMode.Keyboard))
            }
            val state = buildPagedStartupChannelState(
                channels = rows.take(1).map { it.source }, totalChannelCount = 55_000,
                playlistGroupCounts = listOf(Triple("test", "News", 54_990), Triple("test", "Movies", 10)),
                favorites = emptySet(), recents = emptySet(), hiddenGroups = hidden.value)
            CategorySidebar(tree = state.tree, selectedId = "all", expanded = true,
                listState = rememberLazyListState(), onSelect = {}, onOpenSearch = {},
                isTouchDevice = false,
                onHideCategory = { playlist, group -> hidden.value = setOf("$playlist|$group") })
        }
        compose.onNode(isFocusable() and hasAnyDescendant(hasText("News")))
            .performSemanticsAction(SemanticsActions.RequestFocus) { it() }
            .assertIsFocused()
        compose.onRoot().performKeyInput { pressKey(Key.Menu) }
        compose.onNodeWithText("Hide category").performClick()
        compose.waitForIdle()
        compose.onNodeWithText("News").assertDoesNotExist()
        compose.onNode(isFocusable() and hasAnyDescendant(hasText("All Channels"))).assertIsFocused()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        compose.onNode(isFocusable() and hasAnyDescendant(hasText("Movies"))).assertIsFocused()
    }
}
