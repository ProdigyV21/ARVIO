package com.arflix.tv.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import android.graphics.Bitmap
import java.io.File
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class AboutCreditsDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun creditsAreReadableAndDismissible() {
        val visible = mutableStateOf(true)
        compose.setContent {
            Box(Modifier.fillMaxSize().background(Color.Black)) {
                if (visible.value) AboutCreditsDialog { visible.value = false }
            }
        }
        compose.onNodeWithText("About & Credits").assertIsDisplayed()
        compose.onNodeWithText("This product uses the TMDB API but is not endorsed or certified by TMDB.").assertIsDisplayed()
        compose.onNodeWithContentDescription("TMDB").assertIsDisplayed()
        compose.waitForIdle()
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val output = File(instrumentation.targetContext.getExternalFilesDir(null), "credits-screenshot.png")
        instrumentation.uiAutomation.takeScreenshot().useBitmap { bitmap ->
            output.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
        compose.onNodeWithText("Close").performClick()
        compose.runOnIdle { assertFalse(visible.value) }
    }
}

private inline fun Bitmap.useBitmap(block: (Bitmap) -> Unit) {
    try { block(this) } finally { recycle() }
}
