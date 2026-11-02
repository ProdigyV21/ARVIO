package com.arflix.tv.ui.screens.tv.live

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class PlaylistDropdownDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val device get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private fun dialog() = compose.onNode(isDialog())
    private fun row(label: String) = compose.onNode(hasText(label) and hasAnyAncestor(isDialog()))
    private fun settleWindow() { device.waitForIdle(); compose.waitForIdle() }

    @Test fun remoteCanSelectEveryPlaylistAndReopenTheTenthWithFocus() {
        val selected = mutableStateOf("all")
        val providers = listOf(TvProviderFilter("all", "All providers", 10)) +
            (1..10).map { TvProviderFilter("list_$it", "Playlist $it", 1) }
        compose.setContent {
            Box(Modifier.width(320.dp)) {
                PlaylistDropdown(providers, selected.value, { selected.value = it }, remember { FocusRequester() }, {}, {})
            }
        }
        for (n in 1..10) {
            compose.onNodeWithText(if (n == 1) "All providers" else "Playlist ${n - 1}").performClick()
            settleWindow()
            device.pressDPadDown()
            settleWindow()
            row("Playlist $n").assertIsFocused()
            device.pressDPadCenter()
            settleWindow()
            dialog().assertDoesNotExist()
            compose.runOnIdle { assertEquals("list_$n", selected.value) }
        }
        compose.onNodeWithText("Playlist 10").performClick()
        settleWindow()
        val instrumentation = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation()
        java.io.File(instrumentation.targetContext.getExternalFilesDir(null), "iptv-ten-dialog.png").outputStream().use {
            instrumentation.uiAutomation.takeScreenshot().compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
        row("Playlist 10").assertIsFocused().assertIsDisplayed()
        repeat(10) { device.pressDPadUp(); settleWindow() }
        row("All providers").assertIsFocused()
        device.pressDPadCenter()
        settleWindow()
        compose.runOnIdle { assertEquals("all", selected.value) }
    }
}
