package com.arflix.tv.ui.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.media3.common.PlaybackException
import com.arflix.tv.ui.screens.player.PlaybackCompatibility
import org.junit.Rule
import org.junit.Test

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class PlaybackCompatibilityDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun reportShowsTechnicalDetailsWithoutErrorPayloads() {
        val shown = mutableStateOf(true)
        val session = PlaybackCompatibility.session { 50f }
        session.onFrameRateRequest(25f)
        session.onRecovery(PlaybackCompatibility.Recovery.PCM)
        session.onPlayerError(PlaybackException("https://example.invalid/private", null, 4001))
        compose.setContent {
            if (shown.value) PlaybackCompatibilityDialog { shown.value = false }
        }
        compose.onNodeWithText("Playback compatibility").assertIsDisplayed()
        compose.waitUntil(10_000) {
            compose.onAllNodesWithText("AFR request: 25.000; display Hz: 50.000", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNode(hasText("player error code: 4001", substring = true)).assertExists()
        compose.onNode(hasText("example.invalid", substring = true)).assertDoesNotExist()
        compose.onNodeWithText("Copy report").assertIsEnabled()
        compose.onNodeWithText("Run format test").performClick()
        compose.waitUntil(18_000) {
            compose.onAllNodesWithText("H.264 + AAC stereo: played", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNode(hasText("H.264 + Dolby Digital 5.1:", substring = true)).assertExists()
        compose.onNodeWithText("Close").performClick()
        compose.onNodeWithText("Playback compatibility").assertDoesNotExist()
    }
}
