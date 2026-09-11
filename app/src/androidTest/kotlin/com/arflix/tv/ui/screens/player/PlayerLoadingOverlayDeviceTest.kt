package com.arflix.tv.ui.screens.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayerLoadingOverlayDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val state = PlayerUiState(title = "Test movie", isLoading = true, streamLoadPhase = PlayerMessage.Raw("Finding sources"))

    @Test fun mobileLoadingShowsStatusAndWorkingCloseButton() {
        var closed = 0
        compose.setContent {
            PlayerLoadingOverlay(state, false, true, false, false, { closed++ })
        }
        compose.onNodeWithTag("playerLoadingOverlay").assertIsDisplayed()
        compose.onNodeWithText("Finding sources").assertIsDisplayed()
        compose.onNodeWithContentDescription("Close").performClick()
        compose.runOnIdle { assertEquals(1, closed) }
    }

    @Test fun startupErrorReplacesLoadingAndRetryRemainsClickable() {
        val current = mutableStateOf(state)
        var retried = 0
        compose.setContent {
            Box(Modifier.fillMaxSize()) {
                if (current.value.error != null) Button(onClick = { retried++ }) { Text("Retry") }
                PlayerLoadingOverlay(current.value, false, true, false, false, {})
            }
        }
        compose.runOnIdle { current.value = state.copy(isLoading = false, error = PlayerMessage.Raw("Source failed")) }
        compose.onNodeWithTag("playerLoadingOverlay").assertDoesNotExist()
        compose.onNodeWithText("Retry").performClick()
        compose.runOnIdle { assertEquals(1, retried) }
    }

    @Test fun sourcePickerAndPictureInPictureAreNotCovered() {
        val picker = mutableStateOf(true)
        val pip = mutableStateOf(false)
        compose.setContent { PlayerLoadingOverlay(state, false, true, pip.value, picker.value, {}) }
        compose.onNodeWithTag("playerLoadingOverlay").assertDoesNotExist()
        compose.runOnIdle { picker.value = false; pip.value = true }
        compose.onNodeWithTag("playerLoadingOverlay").assertDoesNotExist()
    }

    @Test fun tvLoadingDisappearsAfterPlaybackStarts() {
        val started = mutableStateOf(false)
        val current = state.copy(isLoading = false, selectedStreamUrl = "https://example.com/video.mp4")
        compose.setContent { PlayerLoadingOverlay(current, started.value, false, false, false, {}) }
        compose.onNodeWithTag("playerLoadingOverlay").assertIsDisplayed()
        compose.runOnIdle { started.value = true }
        compose.onNodeWithTag("playerLoadingOverlay").assertDoesNotExist()
    }
}
