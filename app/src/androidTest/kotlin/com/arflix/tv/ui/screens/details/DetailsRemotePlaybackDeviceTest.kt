package com.arflix.tv.ui.screens.details

import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.EpisodeIdentity
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
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

    private fun show(type: MediaType, autoplay: Boolean = true) {
        val state = MutableStateFlow(DetailsUiState(
            isLoading = false, item = MediaItem(42, "Remote playback test", mediaType = type),
            imdbId = "tt-test", episodes = if (type == MediaType.TV) episodes else emptyList(),
            totalSeasons = 2, autoPlaySingleSource = autoplay,
            streams = listOf(StreamSource("Test source", "Test addon", "fixture", "1080p", "", url = "https://example.invalid/test.mp4")),
        ))
        every { model.uiState } returns state
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ArflixTvTheme {
                    DetailsScreen(type, 42, viewModel = model,
                        onNavigateToPlayer = { _, _, episode, _, _, _, _, _ -> plays++; playedEpisode = episode },
                        onNavigateToDetails = { _, _ -> }, onBack = {})
                }
            }
        }
        compose.waitForIdle()
    }

    private fun key(code: Int) {
        InstrumentationRegistry.getInstrumentation().sendKeyDownUpSync(code)
        compose.waitForIdle()
    }
}
