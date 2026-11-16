package com.arflix.tv.ui.components

import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.*
import org.junit.Assume.assumeFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class SourceScrollingDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val instrumentation get() = InstrumentationRegistry.getInstrumentation()

    private fun source(index: Int, extra: String = "") = StreamSource(
        source = "Movie.$index.2160p.WEB-DL.DV.HDR10.HEVC.DDP5.1$extra-Group",
        addonName = "AIOStreams", addonId = "aio-streams", quality = "4K", size = "8.4 GB",
        url = "https://example.invalid/$index.mp4"
    )

    private fun show(deviceType: DeviceType = DeviceType.TV, content: @androidx.compose.runtime.Composable () -> Unit) {
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides deviceType) {
                ArflixTvTheme { Box(Modifier.fillMaxSize().background(Color.Black)) { content() } }
            }
        }
        if (deviceType == DeviceType.TV) {
            compose.waitUntil(5_000) { compose.onAllNodes(isFocused()).fetchSemanticsNodes().size == 1 }
        } else compose.waitForIdle()
    }

    @Test fun touchSourcePickerRespectsSystemBarsAndTabletReadingWidth() {
        instrumentation.runOnMainSync {
            androidx.core.view.WindowCompat.setDecorFitsSystemWindows(compose.activity.window, false)
        }
        val deviceType = com.arflix.tv.util.detectDeviceType(compose.activity)
        show(deviceType) { StreamSelector(true, listOf(source(0)), null) }
        val title = compose.onNodeWithText(compose.activity.getString(com.arflix.tv.R.string.stream_title_select_source))
            .assertIsDisplayed().fetchSemanticsNode().boundsInRoot
        val bars = androidx.core.view.ViewCompat.getRootWindowInsets(compose.activity.window.decorView)
            ?.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())
        assertTrue("Source title overlaps status bar", title.top >= (bars?.top ?: 0))
        val row = compose.onNodeWithText(source(0).source).assertIsDisplayed().getUnclippedBoundsInRoot()
        assertTrue("Source text is stretched across the tablet", row.right.value - row.left.value < 840f)
        compose.onNodeWithContentDescription(compose.activity.getString(com.arflix.tv.R.string.close)).assertIsDisplayed()
    }

    @Test fun focusingLongReleaseNamesDoesNotResizeRows() {
        val streams = (0..24).map { source(it, ".Extended.Directors.Cut.Multi.Audio.TrueHD.7.1.Atmos.REMUX".repeat(it % 3 + 1)) }
        show { StreamSelector(true, streams, null) }
        val before = (0..2).associateWith { compose.onNodeWithText(streams[it].source).fetchSemanticsNode().size }
        key(KeyEvent.KEYCODE_DPAD_DOWN)
        for ((index, size) in before) {
            assertEquals("Focus changed row $index geometry", size,
                compose.onNodeWithText(streams[index].source).fetchSemanticsNode().size)
        }
    }

    @Test fun selectionUpdatesDoNotRewrapReleaseNames() {
        val streams = (0..10).map { source(it, ".Extended.Multi.Audio.TrueHD.7.1.Atmos".repeat(3)) }
        val selected = mutableStateOf<StreamSource?>(null)
        show { StreamSelector(true, streams, selected.value) }
        val before = compose.onNodeWithText(streams[1].source).fetchSemanticsNode().size
        compose.runOnIdle { selected.value = streams[1] }
        assertEquals(before, compose.onNodeWithText(streams[1].source).fetchSemanticsNode().size)
    }

    @Test fun addonFiltersResetAndReturnFocusWithoutSticking() {
        val streams = (0..35).map { source(it).copy(addonId = "aio-${it % 3}", addonName = "AIOStreams ${it % 3}") }
        var focused: StreamSource? = null
        var selected: StreamSource? = null
        show { StreamSelector(true, streams, null, onFocusedStream = { focused = it }, onSelect = { selected = it }) }
        repeat(8) { key(KeyEvent.KEYCODE_DPAD_DOWN) }
        key(KeyEvent.KEYCODE_DPAD_RIGHT)
        key(KeyEvent.KEYCODE_DPAD_DOWN)
        key(KeyEvent.KEYCODE_DPAD_LEFT)
        compose.runOnIdle { assertEquals("aio-0", focused?.addonId) }
        repeat(4) { key(KeyEvent.KEYCODE_DPAD_DOWN) }
        compose.onNodeWithText(checkNotNull(focused).source).assertIsDisplayed()
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(focused, selected) }
    }

    @Test fun repeatedNavigationKeepsEveryFocusedSourceVisibleAndSelectable() {
        val streams = (0..70).map { source(it, ".Extended.Multi.Audio.TrueHD.7.1.Atmos".repeat(it % 3)) }
        var focused: StreamSource? = null
        var selected: StreamSource? = null
        show { StreamSelector(true, streams, null, onFocusedStream = { focused = it }, onSelect = { selected = it }) }
        for (index in 1..35) {
            key(KeyEvent.KEYCODE_DPAD_DOWN)
            compose.onNodeWithText(streams[index].source).assertIsDisplayed()
            compose.runOnIdle { assertEquals(streams[index], focused) }
        }
        for (index in 34 downTo 0) {
            key(KeyEvent.KEYCODE_DPAD_UP)
            compose.onNodeWithText(streams[index].source).assertIsDisplayed()
            compose.runOnIdle { assertEquals(streams[index], focused) }
        }
        // A held remote key must not drop input or leave focus beyond the viewport.
        repeat(28) { instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN) }
        compose.waitForIdle()
        compose.runOnIdle { assertEquals("Rapid repeats dropped input", streams[28], focused) }
        compose.onNodeWithText(streams[28].source).assertIsDisplayed()
        key(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.runOnIdle { assertEquals(streams[28], selected) }
    }

    @Test fun edgeScrollingHasIntermediateFramesInsteadOfTeleporting() {
        val streams = (0..40).map { source(it) }
        show { StreamSelector(true, streams, null) }
        repeat(8) { key(KeyEvent.KEYCODE_DPAD_DOWN) }
        val row = compose.onNodeWithText(streams[8].source)
        val positions = mutableListOf(row.fetchSemanticsNode().boundsInRoot.top)
        compose.mainClock.autoAdvance = false
        try {
            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN)
            repeat(12) {
                compose.mainClock.advanceTimeByFrame()
                positions += row.fetchSemanticsNode().boundsInRoot.top
            }
            assertTrue("Scroll snapped instead of animating: $positions", positions.distinct().size >= 3)
            assertTrue("Scroll reversed during a down press: $positions", positions.zipWithNext().all { (a, b) -> b <= a + 1f })
            compose.mainClock.advanceTimeBy(1_000)
            compose.onNodeWithText(streams[9].source).assertIsDisplayed()
        } finally {
            compose.mainClock.autoAdvance = true
        }
        compose.waitForIdle()
        val directory = File(compose.activity.getExternalFilesDir(null), "source-scroll").apply { mkdirs() }
        File(directory, "frames.txt").writeText(positions.joinToString(","))
        val screenshot = checkNotNull(instrumentation.uiAutomation.takeScreenshot())
        File(directory, "tv-sources.png").outputStream().use {
            screenshot.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
        screenshot.recycle()
    }

    @Test fun focusStaysOnTheTopRowWhileResultsArrive() {
        // The picker often opens with only a trailing action row ("Search Telegram"); results then
        // arrive above it. Focus is a row position, so it stays at the top instead of riding the
        // action row down to the bottom of the list.
        val actionRow = source(999, extra = "action")
        val streams = mutableStateOf(listOf(actionRow))
        var focused: StreamSource? = null
        show { StreamSelector(true, streams.value, null, onFocusedStream = { focused = it }) }
        val arrived = (0..20).map { source(it) }
        compose.runOnIdle { streams.value = arrived + actionRow }
        compose.waitForIdle()
        compose.runOnIdle { assertEquals("Focus left the top row", arrived[0], focused) }
        compose.onNodeWithText(arrived[0].source).assertIsDisplayed()
        compose.runOnIdle { streams.value = listOf(source(100), source(101)) + arrived + actionRow }
        compose.waitForIdle()
        compose.runOnIdle { assertEquals("Focus left the top row", source(100), focused) }
    }

    @Test fun reopeningThePickerResetsFocusToTheFirstSource() {
        val streams = (0..20).map { source(it) }
        val visible = mutableStateOf(true)
        var focused: StreamSource? = null
        show { StreamSelector(visible.value, streams, null, onFocusedStream = { focused = it }) }
        repeat(6) { key(KeyEvent.KEYCODE_DPAD_DOWN) }
        compose.runOnIdle { visible.value = false }
        compose.mainClock.advanceTimeBy(500)
        compose.runOnIdle { visible.value = true }
        compose.waitForIdle()
        compose.runOnIdle { assertEquals(streams[0], focused) }
        compose.onNodeWithText(streams[0].source).assertIsDisplayed()
    }

    @Test fun mobileSourceScrollPositionSurvivesIncomingResults() {
        assumeFalse(instrumentation.targetContext.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_LEANBACK))
        val original = (0..70).map { source(it) }
        val streams = mutableStateOf(original)
        var selected: StreamSource? = null
        show(DeviceType.PHONE) { StreamSelector(true, streams.value, null, onSelect = { selected = it }) }
        captureMobileSources("sources-initial.png")
        compose.onNode(hasScrollToIndexAction()).performScrollToIndex(30)
        val row = compose.onNodeWithText(original[30].source)
        row.assertIsDisplayed()
        val before = row.fetchSemanticsNode().boundsInRoot.top
        compose.runOnIdle { streams.value = listOf(source(100), source(101)) + original }
        row.assertIsDisplayed()
        assertEquals("Incoming results moved the visible source", before, row.fetchSemanticsNode().boundsInRoot.top, 1f)
        row.performClick()
        compose.runOnIdle { assertEquals(original[30], selected) }
        captureMobileSources("sources-scrolled.png")
    }

    private fun captureMobileSources(name: String) {
        val directory = File(instrumentation.targetContext.filesDir, "mobile-ui-review").apply { mkdirs() }
        val screenshot = checkNotNull(instrumentation.uiAutomation.takeScreenshot())
        File(directory, name).outputStream().use {
            screenshot.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
        screenshot.recycle()
    }

    private fun key(code: Int) {
        instrumentation.sendKeyDownUpSync(code)
        compose.waitForIdle()
    }
}
