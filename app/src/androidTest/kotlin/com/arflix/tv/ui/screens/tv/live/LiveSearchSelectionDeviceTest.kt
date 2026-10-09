package com.arflix.tv.ui.screens.tv.live

import android.graphics.Bitmap
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.InterceptPlatformTextInput
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import java.io.File
import kotlinx.coroutines.awaitCancellation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalComposeUiApi::class)
class LiveSearchSelectionDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val channels = (1..40).map { index ->
        IptvChannel(
            id = "search:$index",
            name = "Example ${index.toString().padStart(2, '0')}",
            group = "News",
            streamUrl = "https://example.invalid/live/$index",
        ).enrichForFastStartup(index)
    }
    private val picked = mutableListOf<String>()
    private var dismissed = 0

    @Test fun remoteSelectOpensChannelResultExactlyOnce() {
        show()
        query("Example 05")
        input().performImeAction()
        result(channels[4]).assertIsFocused()
        remote(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle {
            assertEquals(listOf("search:5"), picked)
            assertEquals(0, dismissed)
        }
    }

    @Test fun remoteSelectOpensProgrammeResult() {
        show(guide = mapOf(channels[2].id to IptvNowNext(
            now = IptvProgram("Match of the day", startUtcMillis = 0, endUtcMillis = Long.MAX_VALUE),
        )))
        query("Match of the day")
        remote(KeyEvent.KEYCODE_DPAD_DOWN)
        result(channels[2]).assertIsFocused()
        capture("live-search-programme")
        remote(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(listOf("search:3"), picked) }
    }

    @Test fun keyboardEnterAndNumpadEnterSelectExactlyOncePerPress() {
        show()
        query("Example 09")
        remote(KeyEvent.KEYCODE_DPAD_DOWN)
        remote(KeyEvent.KEYCODE_ENTER)
        remote(KeyEvent.KEYCODE_NUMPAD_ENTER)
        compose.runOnIdle { assertEquals(listOf("search:9", "search:9"), picked) }
    }

    @Test fun imeSearchHandsFocusToSelectableProviderResult() {
        val remoteChannel = IptvChannel(
            id = "search:55000", name = "Remote sports", group = "Sports",
            streamUrl = "https://example.invalid/live/55000",
        ).enrichForFastStartup(55000)
        show(provider = { listOf(remoteChannel) })
        query("Remote sports")
        input().performImeAction()
        result(remoteChannel).assertIsFocused()
        remote(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(listOf("search:55000"), picked) }
    }

    @Test fun resultsScrollAndReturnToInputWithoutLosingSelection() {
        show()
        input().assertIsFocused()
        remote(KeyEvent.KEYCODE_DPAD_DOWN)
        result(channels[0]).assertIsFocused()
        repeat(12) { index ->
            remote(KeyEvent.KEYCODE_DPAD_DOWN)
            result(channels[index + 1]).assertIsFocused()
        }
        result(channels[12]).assertIsFocused().assertIsDisplayed()
        remote(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(listOf("search:13"), picked) }
        repeat(12) { remote(KeyEvent.KEYCODE_DPAD_UP) }
        result(channels[0]).assertIsFocused()
        remote(KeyEvent.KEYCODE_DPAD_UP)
        input().assertIsFocused()
    }

    @Test fun touchTapSelectsWithoutDismissingOverlay() {
        show()
        query("Example 07")
        result(channels[6]).performTouchInput { click() }
        compose.runOnIdle {
            assertEquals(listOf("search:7"), picked)
            assertEquals(0, dismissed)
        }
    }

    @Test fun accessibilityClickSelectsChannelResult() {
        show()
        query("Example 12")
        result(channels[11]).assertHasClickAction().performClick()
        compose.runOnIdle {
            assertEquals(listOf("search:12"), picked)
            assertEquals(0, dismissed)
        }
    }

    @Test fun selectingResultDoesNotLeakSelectReleaseIntoGuide() {
        val open = mutableStateOf(true)
        val guideFocus = FocusRequester()
        var guideReleases = 0
        compose.setContent {
            InterceptPlatformTextInput(interceptor = { _, _ -> awaitCancellation() }) {
                Box(Modifier.onPreviewKeyEvent { event ->
                    if (!open.value && event.key == Key.DirectionCenter && event.type == KeyEventType.KeyUp) {
                        guideReleases++
                    }
                    false
                }) {
                    Box(Modifier.size(20.dp).focusRequester(guideFocus).focusable())
                    if (open.value) SearchOverlay(channels, onDismiss = {}, onPick = { channel ->
                        picked.add(channel.id)
                        open.value = false
                        guideFocus.requestFocus()
                    })
                }
            }
        }
        awaitWindowFocus()
        input().performImeAction()
        result(channels[0]).assertIsFocused()
        remote(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle {
            assertFalse(open.value)
            assertEquals(listOf("search:1"), picked)
            assertEquals("A search selection must not activate the guide beneath it", 0, guideReleases)
        }
    }

    private fun show(
        guide: Map<String, IptvNowNext> = emptyMap(),
        provider: (suspend (String) -> List<EnrichedChannel>)? = null,
    ) {
        compose.setContent {
            // The system IME owns its own remote keys; these checks target the result list.
            InterceptPlatformTextInput(interceptor = { _, _ -> awaitCancellation() }) {
                SearchOverlay(channels, guide, provider,
                    onDismiss = { dismissed++ }, onPick = { picked.add(it.id) })
            }
        }
        awaitWindowFocus()
    }

    private fun awaitWindowFocus() {
        compose.waitUntil(5_000) { compose.activity.hasWindowFocus() }
        compose.waitForIdle()
    }

    private fun input() = compose.onNode(hasSetTextAction())

    private fun query(value: String) {
        input().performTextReplacement(value)
        compose.mainClock.advanceTimeBy(200)
        compose.waitForIdle()
    }

    private fun result(channel: EnrichedChannel) = compose.onNode(
        isFocusable() and !hasSetTextAction() and
            (hasText(channel.name) or hasAnyDescendant(hasText(channel.name))),
    )

    // Inject Android DOWN/UP events through the activity, just as a Fire TV remote does.
    private fun remote(code: Int) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.sendKeyDownUpSync(code)
        instrumentation.waitForIdleSync()
        compose.waitForIdle()
    }

    private fun capture(name: String) {
        val image = compose.onRoot().captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null), "$name.png").outputStream().use {
            image.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }
}
