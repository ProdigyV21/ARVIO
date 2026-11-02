package com.arflix.tv.ui.screens.profile

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.R
import com.arflix.tv.data.model.Profile
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ProfileSelectionDesignDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val profiles = listOf(
        Profile(id = "one", name = "Arvind", avatarId = 1),
        Profile(id = "two", name = "Maya", avatarId = 7),
        Profile(id = "three", name = "Kids", avatarId = 12, isKidsProfile = true),
        Profile(id = "four", name = "A very long profile name", avatarId = 9),
        Profile(id = "five", name = "Guest", avatarId = 4)
    )
    private val state = MutableStateFlow(ProfileUiState(profiles = profiles, activeProfile = profiles[0], isLoading = false))
    private var selections = 0
    private var entered: String? = null
    private var manageClicks = 0

    private fun fixture(phone: Boolean, count: Int = 5) {
        state.value = state.value.copy(profiles = profiles.take(count))
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides if (phone) DeviceType.PHONE else DeviceType.TV) {
                Box(Modifier.testTag("profile-fixture").size(if (phone) 360.dp else 960.dp, if (phone) 520.dp else 540.dp)) {
                    ProfileSelectionContent(state.collectAsState().value, ProfileSelectionActions(toggleManageMode = { manageClicks++ }, selectProfile = {
                        selections++
                        state.value = state.value.copy(activeProfile = it, isSwitchingProfile = false)
                    }), { entered = it })
                }
            }
        }
        androidx.test.uiautomator.UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).waitForIdle()
        compose.mainClock.advanceTimeBy(500)
        compose.waitForIdle()
    }

    private fun screenshot(name: String) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        java.io.File(context.getExternalFilesDir(null), "$name.png").outputStream().use {
            compose.onNodeWithTag("profile-fixture").captureToImage().asAndroidBitmap().compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    @Test fun tvFitsFiveProfilesAndAllActions() {
        fixture(false)
        profiles.forEach { compose.onNodeWithContentDescription(it.name).assertIsDisplayed() }
        compose.onNodeWithText(compose.activity.getString(R.string.manage_profiles)).assertIsDisplayed()
        compose.onNodeWithText(compose.activity.getString(R.string.connect_to_cloud)).assertIsDisplayed()
        screenshot("profiles-tv-five")
    }

    @Test fun phoneGridScrollsToAllProfilesAndActions() {
        fixture(true)
        screenshot("profiles-mobile-five")
        profiles.forEach { compose.onNodeWithContentDescription(it.name).performScrollTo().assertIsDisplayed() }
        compose.onNodeWithText(compose.activity.getString(R.string.connect_to_cloud)).performScrollTo().assertIsDisplayed()
        compose.onNodeWithText(compose.activity.getString(R.string.manage_profiles)).performScrollTo().performTouchInput { click() }
        compose.runOnIdle { assertEquals(1, manageClicks) }
    }

    @Test fun phoneOpeningShowsTheAddAction() {
        fixture(true, 3)
        screenshot("profiles-mobile")
        compose.onNodeWithText(compose.activity.getString(R.string.add_profile)).performScrollTo().assertIsDisplayed()
    }

    @Test fun selectionTransitionsOnlyToTheChosenProfile() {
        fixture(false, 3)
        screenshot("profiles-tv")
        val device = androidx.test.uiautomator.UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressDPadRight()
        compose.waitForIdle()
        device.pressDPadCenter()
        compose.waitForIdle()
        assertEquals("Profile tap must be accepted", 1, selections)
        compose.mainClock.advanceTimeBy(1000)
        compose.waitForIdle()
        compose.waitUntil(5000) { entered != null }
        assertEquals("two", entered)
        assertEquals(1, selections)
    }
}
