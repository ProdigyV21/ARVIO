package com.arflix.tv.ui.screens.settings

import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.view.inspector.WindowInspector
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertTrue

@OptIn(ExperimentalTestApi::class)
class SettingsRemoteInputDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val state = MutableStateFlow(SettingsUiState(autoPlayNext = true, autoPlaySingleSource = true))
    private val viewModel = mockk<SettingsViewModel>(relaxed = true)

    private fun show(section: String = "playback") {
        every { viewModel.uiState } returns state
        every { viewModel.setAutoPlayNext(any()) } answers {
            state.value = state.value.copy(autoPlayNext = firstArg())
        }
        every { viewModel.setAutoPlaySingleSource(any()) } answers {
            state.value = state.value.copy(autoPlaySingleSource = firstArg())
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                SettingsScreen(viewModel = viewModel, initialSection = section)
            }
        }
        compose.waitForIdle()
    }

    @Test fun firstSelectActivatesHighlightedSettingOnce() {
        show()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        compose.waitForIdle()
        verify(exactly = 1) { viewModel.setAutoPlayNext(false) }
    }

    @Test fun numpadEnterActivatesTheHighlightedSettingOnce() {
        show()
        compose.onRoot().performKeyInput { pressKey(Key.NumPadEnter) }
        verify(exactly = 1) { viewModel.setAutoPlayNext(false) }
    }

    @Test fun switchingFromDeepPlaybackToLanguageStartsAtItsFirstRow() {
        show()
        repeat(12) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
        compose.onRoot().performKeyInput {
            pressKey(Key.DirectionLeft)
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionRight)
        }
        assertRowVisible(0)
        compose.onRoot().performKeyInput {
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionCenter)
        }
        compose.onNode(isDialog()).assertExists()
    }

    @Test fun closingQualityDialogRestoresNativeRemoteInput() {
        show()
        repeat(tvGeneralRowsForSection("playback").indexOf(16)) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        compose.onNode(isDialog()).assertExists()
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.waitForIdleSync()
        instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK)
        compose.onNode(isDialog()).assertDoesNotExist()
        instrumentation.waitForIdleSync()
        instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.onNode(isDialog()).assertExists()
        verify(exactly = 0) { viewModel.setAutoPlayNext(any()) }
    }

    @Test fun nativeDownAfterDialogDismissalMovesExactlyOneRow() {
        show()
        repeat(tvGeneralRowsForSection("playback").indexOf(16)) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.waitForIdleSync()
        instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK)
        compose.onNode(isDialog()).assertDoesNotExist()
        instrumentation.waitForIdleSync()
        instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN)
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        verify(exactly = 1) { viewModel.cycleFrameRateMatchingMode() }
    }

    @Test fun downThenSelectWithoutWaitingTargetsNextRow() {
        show()
        compose.onRoot().performKeyInput {
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionCenter)
        }
        compose.waitForIdle()
        verify(exactly = 1) { viewModel.setAutoPlaySingleSource(false) }
        verify(exactly = 0) { viewModel.setAutoPlayNext(any()) }
    }

    @Test fun rapidReversalKeepsTheLatestRowVisibleAndSelectable() {
        show()
        compose.onRoot().performKeyInput {
            repeat(8) { pressKey(Key.DirectionDown) }
            repeat(7) { pressKey(Key.DirectionUp) }
        }
        assertRowVisible(1)
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        verify(exactly = 1) { viewModel.setAutoPlaySingleSource(false) }
        verify(exactly = 0) { viewModel.setAutoPlayNext(any()) }
    }

    @Test fun heldSelectDoesNotToggleBackAndSeparatePressStillWorks() {
        show()
        val start = SystemClock.uptimeMillis()
        for (repeat in 0..3) {
            compose.runOnUiThread {
                compose.activity.dispatchKeyEvent(KeyEvent(start, start + repeat * 120L,
                    KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER, repeat))
            }
            compose.waitForIdle()
        }
        compose.runOnUiThread {
            compose.activity.dispatchKeyEvent(KeyEvent(start, start + 400,
                KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER, 0))
        }
        compose.waitForIdle()
        verify(exactly = 1) { viewModel.setAutoPlayNext(false) }
        verify(exactly = 0) { viewModel.setAutoPlayNext(true) }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        compose.waitForIdle()
        verify(exactly = 1) { viewModel.setAutoPlayNext(true) }
    }

    @Test fun singlePressesRevealEveryPlaybackRowDownAndUp() {
        show()
        val lastRow = tvGeneralRowsForSection("playback").lastIndex
        for (index in 1..lastRow) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
            assertRowVisible(index)
        }
        for (index in lastRow - 1 downTo 0) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) }
            assertRowVisible(index)
        }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        verify(exactly = 1) { viewModel.setAutoPlayNext(false) }
    }

    @Test fun largeCatalogListNavigation() {
        state.value = state.value.copy(catalogs = List(300) { index ->
            CatalogConfig(id = "catalog-$index", title = "Catalog $index",
                sourceType = CatalogSourceType.ADDON, addonName = "Test addon")
        })
        val opening = SystemClock.elapsedRealtime()
        show("catalogs")
        Log.i("SettingsLatency", "catalog-open-ms=${SystemClock.elapsedRealtime() - opening}")
        compose.onNodeWithText("Catalog 299").assertDoesNotExist()
        val samples = mutableListOf<Long>()
        for (index in 1..25) {
            val start = SystemClock.elapsedRealtime()
            compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
            assertRowVisible(index)
            samples += SystemClock.elapsedRealtime() - start
        }
        Log.i("SettingsLatency", "catalog-down-ms=$samples")
        compose.onRoot().performKeyInput {
            pressKey(Key.DirectionRight)
            pressKey(Key.DirectionCenter)
        }
        verify(exactly = 1) { viewModel.moveCatalogUp("catalog-22") }
        for (index in 24 downTo 0) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) }
            assertRowVisible(index)
        }
        repeat(5) { compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) } }
        compose.runOnUiThread { state.value = state.value.copy(catalogs = emptyList()) }
        assertRowVisible(2)
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        verify(exactly = 1) { viewModel.toggleBuiltInCollections() }
    }

    @Test fun sidebarAndPlaybackStayResponsiveWithLargeCatalogState() {
        state.value = state.value.copy(catalogs = List(300) { index ->
            CatalogConfig("catalog-$index", "Catalog $index", CatalogSourceType.ADDON)
        })
        show("playback")
        compose.onRoot().performKeyInput { pressKey(Key.DirectionLeft) }
        val samples = mutableListOf<Long>()
        repeat(2) {
            for (key in listOf(Key.DirectionDown, Key.DirectionUp)) {
                repeat(7) {
                    val start = SystemClock.elapsedRealtime()
                    compose.onRoot().performKeyInput { pressKey(key) }
                    compose.waitForIdle()
                    samples += SystemClock.elapsedRealtime() - start
                }
            }
        }
        Log.i("SettingsLatency", "sidebar-step-ms=$samples")
        compose.onRoot().performKeyInput {
            pressKey(Key.DirectionRight)
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionCenter)
        }
        verify(exactly = 1) { viewModel.setAutoPlaySingleSource(false) }
    }

    @Test fun largeCategoryListKeepsFocusedRowVisibleAndClickable() {
        state.value = state.value.copy(
            iptvPlaylists = listOf(IptvPlaylistEntry("test", "Test playlist", "https://example.invalid/list.m3u")),
            iptvSelectedPlaylistId = "test",
            iptvAvailableGroups = List(1000) { "Category $it" }
        )
        show("iptv")
        compose.onRoot().performKeyInput {
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionCenter)
        }
        for (index in 1..40) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
            assertRowVisible(index)
        }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        verify(exactly = 1) { viewModel.toggleIptvHiddenGroup("test", "Category 38") }
        for (index in 39 downTo 2) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionUp) }
            assertRowVisible(index)
        }
    }

    @Test fun firstPressAfterClosingPickerStillWorks() {
        state.value = state.value.copy(subtitleOptions = listOf("Off", "English", "Dutch"))
        show("language")
        compose.onRoot().performKeyInput {
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionCenter)
        }
        compose.onNode(isDialog()).assertExists().performKeyInput {
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionCenter)
        }
        compose.onNode(isDialog()).assertDoesNotExist()
        verify(exactly = 1) { viewModel.setDefaultSubtitle("English") }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        compose.onNode(isDialog()).assertExists()
    }

    @Test @SdkSuppress(minSdkVersion = 29)
    fun heldSelectCannotImmediatelyAcceptNewPicker() {
        state.value = state.value.copy(subtitleOptions = listOf("Off", "English"))
        show("language")
        compose.onRoot().performKeyInput {
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionCenter)
        }
        compose.onNode(isDialog()).assertExists()
        // Compose semantics can settle before Android grants focus to the dialog window.
        compose.waitUntil(5_000) {
            var focused = false
            compose.runOnUiThread {
                focused = WindowInspector.getGlobalWindowViews().any {
                    it !== compose.activity.window.decorView && it.hasWindowFocus()
                }
            }
            focused
        }
        val start = SystemClock.uptimeMillis()
        for (repeat in 1..3) {
            compose.runOnUiThread {
                val dialog = WindowInspector.getGlobalWindowViews().first { it.hasWindowFocus() }
                dialog.dispatchKeyEvent(KeyEvent(start, start + repeat * 100L,
                    KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER, repeat))
            }
            compose.onNode(isDialog()).assertExists()
        }
        verify(exactly = 0) { viewModel.setDefaultSubtitle(any()) }
        compose.onNode(isDialog()).performKeyInput { pressKey(Key.DirectionCenter) }
        verify(exactly = 1) { viewModel.setDefaultSubtitle("Off") }
    }

    private fun assertRowVisible(index: Int) {
        compose.waitForIdle()
        val viewport = compose.onNodeWithTag("settings-content").getUnclippedBoundsInRoot()
        val row = compose.onNode(
            hasTestTag("settings-focus-$index") and hasAnyAncestor(hasTestTag("settings-content")),
            useUnmergedTree = true
        ).getUnclippedBoundsInRoot()
        assertTrue("Row $index is clipped: $row in $viewport", row.bottom > row.top &&
            row.top.value >= viewport.top.value - 1 && row.bottom.value <= viewport.bottom.value + 1)
    }
}
