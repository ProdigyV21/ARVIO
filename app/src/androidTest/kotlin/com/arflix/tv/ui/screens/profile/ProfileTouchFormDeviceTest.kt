package com.arflix.tv.ui.screens.profile

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.detectDeviceType
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ProfileTouchFormDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun nativeKeyboardDoesNotCoverProfileConfirmation() {
        val name = mutableStateOf("Keyboard QA")
        val shown = mutableStateOf(true)
        var confirmed = false
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides detectDeviceType(compose.activity)) {
                if (shown.value) AddProfileDialog(
                    name = name.value, onNameChange = { name.value = it },
                    selectedColorIndex = 0, onColorSelected = {},
                    onConfirm = { confirmed = true; shown.value = false },
                    onDismiss = { shown.value = false },
                )
            }
        }
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val field = device.wait(Until.findObject(By.clazz("android.widget.EditText")), 8000)
        assertNotNull(field)
        checkNotNull(field).click()
        assertTrue("Native keyboard did not open", device.wait(
            Until.hasObject(By.pkg("com.google.android.inputmethod.latin")), 8000))
        compose.waitForIdle()
        compose.waitUntil(5000) {
            val inputBounds = device.findObject(By.clazz("android.widget.EditText"))?.visibleBounds
            val confirmBounds = device.findObject(By.text("Confirm"))?.visibleBounds
            inputBounds != null && confirmBounds != null && inputBounds.height() > 50 &&
                inputBounds.bottom <= confirmBounds.top
        }
        compose.onNodeWithText("Confirm").assertIsDisplayed()
        val confirm = checkNotNull(device.wait(Until.findObject(By.text("Confirm")), 5000))
        val input = checkNotNull(device.findObject(By.clazz("android.widget.EditText")))
        assertTrue("The focused name must be visible above the fixed actions",
            input.visibleBounds.bottom <= confirm.visibleBounds.top)
        // A real screen tap catches an IME overlay that a semantics click would bypass.
        confirm.click()
        compose.waitUntil(5000) { confirmed }
    }
}
