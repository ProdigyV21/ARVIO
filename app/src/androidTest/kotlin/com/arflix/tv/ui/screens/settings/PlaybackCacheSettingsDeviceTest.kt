package com.arflix.tv.ui.screens.settings

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class PlaybackCacheSettingsDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val model = mockk<SettingsViewModel>(relaxed = true)

    @Test fun remoteCacheActionsAreReachableAndDistinct() {
        every { model.uiState } returns MutableStateFlow(SettingsUiState())
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                SettingsScreen(viewModel = model, initialSection = "playback")
            }
        }
        repeat(tvGeneralRowsForSection("playback").size - 2) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        }
        compose.onNodeWithText("Playback Cache Size").assertIsDisplayed()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        verify(exactly = 1) { model.cyclePlaybackCacheSize() }
        compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        compose.onNodeWithText("Clear Playback Cache").assertIsDisplayed()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionCenter) }
        verify(exactly = 1) { model.clearPlaybackCache() }
    }

    @Test fun mobileCacheActionsAreReachable() {
        every { model.uiState } returns MutableStateFlow(SettingsUiState())
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                SettingsScreen(viewModel = model)
            }
        }
        compose.onNodeWithText("Playback & Controls").performScrollTo().performClick()
        compose.onNodeWithText("Playback Cache Size").performScrollTo().performClick()
        compose.onNodeWithText("Clear Playback Cache").performScrollTo().performClick()
        verify(exactly = 1) { model.cyclePlaybackCacheSize() }
        verify(exactly = 1) { model.clearPlaybackCache() }
    }

    @Test fun liveAiRowsRemainReachableInTheirOwnSection() {
        every { model.uiState } returns MutableStateFlow(SettingsUiState())
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                SettingsScreen(viewModel = model, initialSection = "ai_subtitles")
            }
        }
        repeat(tvGeneralRowsForSection("ai_subtitles").size - 2) {
            compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        }
        compose.onNodeWithText("Live TV AI subtitles").assertIsDisplayed()
        compose.onRoot().performKeyInput { pressKey(Key.DirectionDown) }
        compose.onNodeWithText("Auto-translate when a channel starts").assertIsDisplayed()
        verify(exactly = 0) { model.cyclePlaybackCacheSize() }
        verify(exactly = 0) { model.clearPlaybackCache() }
    }
}
