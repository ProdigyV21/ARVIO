package com.arflix.tv.ui.screens.details

import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.click
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.Episode
import com.arflix.tv.R
import com.arflix.tv.data.model.EpisodeIdentity
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.KidsContentFilter
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.every
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Sends real Android remote events to the production Details screen, not semantic clicks. */
@RunWith(AndroidJUnit4::class)
class DetailsRemotePlaybackDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val model = mockk<DetailsViewModel>(relaxed = true)
    private var plays = 0
    private var playedEpisode: EpisodeIdentity? = null
    private var playedPosition: Long? = null
    private val episodes = listOf(Episode(1, 1, 1, "First episode"), Episode(2, 2, 1, "Second episode"))

    @Test fun moviePlayRespondsToFirstOkWithoutDirectionalInput() {
        show(MediaType.MOVIE)
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(1, plays) }
    }

    @Test fun focusedEpisodePlaysOnFirstOk() {
        show(MediaType.TV)
        key(KeyEvent.KEYCODE_DPAD_DOWN) // seasons
        key(KeyEvent.KEYCODE_DPAD_DOWN) // episodes
        key(KeyEvent.KEYCODE_DPAD_RIGHT)
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle {
            assertEquals(1, plays)
            assertEquals(episodes[1].identity, playedEpisode)
        }
    }

    @Test fun episodeRailFocusKeepsWhiteOutlineWithoutBlackInnerBorder() {
        show(MediaType.TV)
        key(KeyEvent.KEYCODE_DPAD_DOWN) // seasons
        val density = InstrumentationRegistry.getInstrumentation().targetContext.resources.displayMetrics.density
        val beforeBounds = compose.onNodeWithTag("episode_1_1").fetchSemanticsNode().boundsInRoot
        val beforePixels = compose.onRoot().captureToImage().toPixelMap()
        val beforeInnerEdge = beforePixels[
            beforeBounds.center.x.toInt(), (beforeBounds.top + 4f * density).toInt()
        ].luminance()
        assertTrue("The unfocused card must have a visible surface", beforeInnerEdge > 0f)

        fun assertFocusOutline(episodeNumber: Int) {
            val bounds = compose.onNodeWithTag("episode_1_$episodeNumber").fetchSemanticsNode().boundsInRoot
            // Capture the whole screen so the fixed row overlay is included, not just the card.
            val pixels = compose.onRoot().captureToImage().toPixelMap()
            val x = bounds.center.x.toInt()
            assertTrue("The white episode focus ring must remain", pixels[x, bounds.top.toInt()].luminance() > 0.9f)
            assertTrue("The row overlay must not add a black inner border",
                pixels[x, (bounds.top + 4f * density).toInt()].luminance() >= beforeInnerEdge - 0.0001f)
        }

        key(KeyEvent.KEYCODE_DPAD_DOWN) // episodes
        assertFocusOutline(1)
        key(KeyEvent.KEYCODE_DPAD_RIGHT)
        assertFocusOutline(2)
        key(KeyEvent.KEYCODE_DPAD_LEFT)
        assertFocusOutline(1)
    }

    @Test fun heldOkDoesNotStartPlaybackTwice() {
        show(MediaType.MOVIE)
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val time = android.os.SystemClock.uptimeMillis()
        instrumentation.sendKeySync(KeyEvent(time, time, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER, 0))
        instrumentation.sendKeySync(KeyEvent(time, time + 50, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER, 1))
        instrumentation.sendKeySync(KeyEvent(time, time + 100, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER, 0))
        compose.runOnIdle { assertEquals(1, plays) }
    }

    @Test fun autoplayOffOpensEpisodeSourcesOnFirstOk() {
        show(MediaType.TV, autoplay = false)
        key(KeyEvent.KEYCODE_DPAD_DOWN)
        key(KeyEvent.KEYCODE_DPAD_DOWN)
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        verify(exactly = 1) { model.loadStreams("tt-test", episodes[0].identity) }
        compose.runOnIdle { assertEquals(0, plays) }
    }

    @Test fun movieSourcePickerRespondsToFirstOk() {
        show(MediaType.MOVIE, autoplay = false)
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        // Allow the modal's focus request and entrance animation to complete.
        compose.waitForIdle()
        android.os.SystemClock.sleep(350)
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(1, plays) }
    }

    @Test fun remotePlayWaitsForPostIdleRefreshAndUsesTheNewPosition() {
        val state = show(MediaType.MOVIE)
        val syncingLabel = compose.activity.getString(R.string.syncing_progress)
        compose.runOnIdle {
            state.value = state.value.copy(isSyncingProgress = true, playPositionMs = 90_000L)
        }
        compose.onNodeWithText(syncingLabel).assertIsDisplayed()
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(0, plays) }
        compose.runOnIdle {
            state.value = state.value.copy(isSyncingProgress = false, idleSyncRevision = 1L, playPositionMs = 224_000L)
        }
        compose.onNodeWithText(syncingLabel).assertDoesNotExist()
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(1, plays); assertEquals(224_000L, playedPosition) }
    }

    @Test fun restoredMobileSelectionDoesNotOverrideTheNewSyncedEpisode() {
        val visible = mutableStateOf(true)
        val state = show(MediaType.TV, phone = true, visible = visible)
        compose.onNodeWithTag("episode_1_1").performScrollTo().performTouchInput { click() }
        compose.runOnIdle { assertEquals(0, plays) }
        compose.runOnIdle { visible.value = false }
        val continueLabel = compose.activity.getString(R.string.continue_season_episode, 1, 2)
        compose.runOnIdle {
            state.value = state.value.copy(idleSyncRevision = 1L, isPlaybackTargetReady = true,
                playSeason = 1, playEpisode = 2, playTmdbSeason = 1, playTmdbEpisode = 2,
                playLabel = continueLabel, playPositionMs = 224_000L)
            visible.value = true
        }
        compose.onNodeWithText(continueLabel).performScrollTo().performTouchInput { click() }
        compose.runOnIdle {
            assertEquals(1, plays)
            assertEquals(episodes[1].identity, playedEpisode)
            assertEquals(224_000L, playedPosition)
        }
    }

    private fun show(type: MediaType, autoplay: Boolean = true, phone: Boolean = false,
        visible: MutableState<Boolean>? = null): MutableStateFlow<DetailsUiState> {
        val state = MutableStateFlow(DetailsUiState(
            isLoading = false, item = MediaItem(42, "Remote playback test", mediaType = type),
            imdbId = "tt-test", episodes = if (type == MediaType.TV) episodes else emptyList(),
            totalSeasons = 2, autoPlaySingleSource = autoplay,
            streams = listOf(StreamSource("Test source", "Test addon", "fixture", "1080p", "", url = "https://example.invalid/test.mp4")),
        ))
        every { model.uiState } returns state
        every { model.canStartPlayback(any()) } answers {
            !state.value.isSyncingProgress && firstArg<Long>() == state.value.idleSyncRevision
        }
        every { model.kidsFilterState } returns MutableStateFlow(KidsContentFilter.State(null, "US", emptyMap()))
        every { model.applyKidsFilter(any(), any()) } answers { firstArg() }
        every { model.listPicker } returns MutableStateFlow(null)
        coEvery { model.hasPersonalLists() } returns false
        compose.setContent {
            val saved = rememberSaveableStateHolder()
            if (visible?.value != false) saved.SaveableStateProvider("details") {
                CompositionLocalProvider(LocalDeviceType provides if (phone) DeviceType.PHONE else DeviceType.TV) {
                    ArflixTvTheme {
                        DetailsScreen(type, 42, viewModel = model,
                            onNavigateToPlayer = { _, _, episode, _, _, _, _, position, _ ->
                                plays++
                                playedEpisode = episode
                                playedPosition = position
                            },
                            onNavigateToDetails = { _, _ -> }, onBack = {})
                    }
                }
            }
        }
        compose.waitForIdle()
        return state
    }

    private fun key(code: Int) {
        InstrumentationRegistry.getInstrumentation().sendKeyDownUpSync(code)
        compose.waitForIdle()
    }
}
