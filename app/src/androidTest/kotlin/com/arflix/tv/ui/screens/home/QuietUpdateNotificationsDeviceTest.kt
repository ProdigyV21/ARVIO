package com.arflix.tv.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.arflix.tv.R
import com.arflix.tv.ui.screens.settings.SettingsSidebarHeader
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import java.io.File
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class QuietUpdateNotificationsDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val quiet = mutableStateOf<String?>("Update v2.4 is available. Install it from Settings - Accounts - App Update.")
    private val ordinary = mutableStateOf<String?>(null)
    private val updateMessage = quiet.value!!
    private var updateDismissals = 0
    private var ordinaryDismissals = 0

    @Test fun errorTakesPriorityAndTheUpdateThenGetsItsFullDuration() {
        ordinary.value = "Request failed"
        fixture()
        advance(500)
        compose.onNodeWithText("Request failed").assertIsDisplayed()
        compose.onNodeWithText(updateMessage).assertDoesNotExist()
        advance(3500)
        compose.onNodeWithText("Request failed").assertDoesNotExist()
        compose.onNodeWithText(updateMessage).assertIsDisplayed()
        assertEquals(1, ordinaryDismissals)
        assertEquals(0, updateDismissals)
        advance(6500)
        compose.onNodeWithText(updateMessage).assertIsDisplayed()
        capture("quiet-tv")
        advance(2000)
        compose.onNodeWithText(updateMessage).assertDoesNotExist()
        assertEquals(1, updateDismissals)
    }

    @Test fun anInterruptedUpdateResumesWithoutOverlappingOrDismissingTheWrongMessage() {
        fixture()
        advance(2500)
        compose.onNodeWithText(updateMessage).assertIsDisplayed()
        compose.runOnUiThread { ordinary.value = "Saved to watchlist" }
        advance(500)
        compose.onNodeWithText("Saved to watchlist").assertIsDisplayed()
        compose.onNodeWithText(updateMessage).assertDoesNotExist()
        advance(3500)
        compose.onNodeWithText("Saved to watchlist").assertDoesNotExist()
        compose.onNodeWithText(updateMessage).assertIsDisplayed()
        assertEquals(0, updateDismissals)
        advance(8500)
        compose.onNodeWithText(updateMessage).assertDoesNotExist()
        assertEquals(1, updateDismissals)
        assertEquals(1, ordinaryDismissals)
    }

    @Test fun quietNoticeFitsANarrowMobileSurface() {
        fixture(mobile = true)
        advance(500)
        val surface = compose.onNodeWithTag("mobile_surface").fetchSemanticsNode().boundsInRoot
        val notice = compose.onNodeWithText(updateMessage).assertIsDisplayed().fetchSemanticsNode().boundsInRoot
        assertTrue(notice.left >= surface.left && notice.right <= surface.right)
        assertTrue(notice.top >= surface.top && notice.bottom <= surface.bottom)
        capture("quiet-mobile")
    }

    @Test fun settingsHeaderRetainsBothUpdateAndOptimizationNotices() {
        compose.setContent {
            Column(Modifier.width(204.dp).background(Color.Black).padding(10.dp)) {
                SettingsSidebarHeader("v2.4", awaitingCompilation = true)
            }
        }
        val update = compose.onNodeWithText(
            compose.activity.getString(R.string.settings_sidebar_update_available, "v2.4")
        ).assertIsDisplayed().fetchSemanticsNode().boundsInRoot
        val optimization = compose.onNodeWithText(
            compose.activity.getString(R.string.settings_optimizing_notice)
        ).assertIsDisplayed().fetchSemanticsNode().boundsInRoot
        assertTrue("Both header notices occupy separate lines", update.bottom <= optimization.top)
        capture("settings-both-notices")
    }

    private fun fixture(mobile: Boolean = false) {
        compose.mainClock.autoAdvance = false
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides if (mobile) DeviceType.PHONE else DeviceType.TV) {
                Box(Modifier.fillMaxSize().background(Color.Black)) {
                    Box(if (mobile) Modifier.width(360.dp).height(480.dp).testTag("mobile_surface") else Modifier.fillMaxSize()) {
                        HomeToastNotifications(
                            toastMessage = ordinary.value,
                            toastType = ToastType.ERROR,
                            quietUpdateNotice = quiet.value,
                            onToastDismiss = { ordinaryDismissals++; ordinary.value = null },
                            onQuietUpdateDismiss = { updateDismissals++; quiet.value = null }
                        )
                    }
                }
            }
        }
    }

    private fun advance(milliseconds: Long) {
        compose.mainClock.advanceTimeBy(milliseconds)
        compose.waitForIdle()
    }

    private fun capture(name: String) {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(
            File(compose.activity.getExternalFilesDir(null), "pr840-$name.png")
        )
    }
}
