package com.arflix.tv.ui.components

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import java.io.File
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ChannelLogoCardDeviceTest(private val device: DeviceType) {
    @get:Rule val compose = createComposeRule()

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun devices() = listOf(DeviceType.TV, DeviceType.PHONE, DeviceType.TABLET)
    }

    private val channel = MediaItem(
        id = -12,
        title = "USA | Local Test Channel",
        mediaType = MediaType.TV,
        status = "iptv:test-channel"
    )

    @Test fun transparentLogoIsCenteredUncroppedAndHasNoTextBehindIt() {
        val logo = logoFixture()
        showCard(channel.copy(image = logo, backdrop = logo), overlay = logo)
        waitForLogo()
        compose.onNodeWithText(channel.title, useUnmergedTree = true).assertDoesNotExist()

        val pixels = compose.onNodeWithTag("card").captureToImage().toPixelMap()
        val red = bounds(pixels.width, pixels.height) { x, y -> isRed(pixels[x, y]) }
        val green = bounds(pixels.width, pixels.height) { x, y ->
            val color = pixels[x, y]
            color.green > 0.8f && color.red < 0.2f && color.blue < 0.2f
        }
        val blue = bounds(pixels.width, pixels.height) { x, y ->
            val color = pixels[x, y]
            color.blue > 0.8f && color.red < 0.2f && color.green < 0.2f
        }
        val yellow = bounds(pixels.width, pixels.height) { x, y ->
            val color = pixels[x, y]
            color.red > 0.8f && color.green > 0.8f && color.blue < 0.2f
        }
        // All four edge markers survive. A cropped image loses the left/right markers.
        assertNotNull(red)
        assertNotNull(green)
        assertNotNull(blue)
        assertNotNull(yellow)
        val left = red!![0]
        val top = red[1]
        val right = green!![2]
        val bottom = yellow!![3]
        assertEquals(pixels.width / 2f, (left + right) / 2f, 2f)
        assertEquals(pixels.height / 2f, (top + bottom) / 2f, 2f)
        assertEquals(3f, (right - left + 1f) / (bottom - top + 1f), 0.1f)
        assertTrue(left >= pixels.width * 0.09f)
        assertTrue(top >= pixels.height * 0.12f)
        // The optional movie-logo overlay must not draw this channel logo a second time.
        compose.onAllNodes(hasContentDescription(channel.title, substring = true),
            useUnmergedTree = true).assertCountEquals(1)
    }

    @Test fun missingLogoShowsChannelName() {
        showCard(channel)
        compose.onNodeWithText(channel.title, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test fun failedLogoShowsChannelName() {
        showCard(channel.copy(image = "file:///missing-arvio-channel-logo.png"))
        compose.waitUntil(5_000) {
            compose.onAllNodesWithText(channel.title, useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNodeWithText(channel.title, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test fun replacingFailedLogoRemovesFallbackAndKeepsCardClickable() {
        val item = mutableStateOf(channel.copy(image = "file:///missing-arvio-channel-logo.png"))
        var clicks = 0
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides device) {
                MediaCard(item = item.value, width = 280.dp, showTitle = false,
                    showSubtitle = false, onClick = { clicks++ }, modifier = Modifier.testTag("card"))
            }
        }
        compose.waitUntil(5_000) {
            compose.onAllNodesWithText(channel.title, useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        val logo = logoFixture()
        compose.runOnIdle { item.value = channel.copy(image = logo) }
        waitForLogo()
        compose.onNodeWithText(channel.title, useUnmergedTree = true).assertDoesNotExist()
        compose.onNode(hasClickAction()).performClick()
        compose.runOnIdle { assertEquals(1, clicks) }
    }

    @Test fun posterLayoutAlsoFitsEntireChannelLogo() {
        showCard(channel.copy(image = logoFixture()), landscape = false)
        waitForLogo()
        compose.onNodeWithText(channel.title, useUnmergedTree = true).assertDoesNotExist()
        val pixels = compose.onNodeWithTag("card").captureToImage().toPixelMap()
        val red = bounds(pixels.width, pixels.height) { x, y -> isRed(pixels[x, y]) }!!
        assertTrue(red[0] >= pixels.width * 0.09f)
        assertTrue(red[1] > pixels.height * 0.3f)
    }

    private fun showCard(item: MediaItem, overlay: String? = null, landscape: Boolean = true) {
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides device) {
                MediaCard(item = item, width = 280.dp, isLandscape = landscape,
                    logoImageUrl = overlay, showTitle = false, showSubtitle = false,
                    modifier = Modifier.testTag("card"))
            }
        }
    }

    private fun waitForLogo() {
        compose.waitUntil(5_000) {
            val pixels = compose.onNodeWithTag("card").captureToImage().toPixelMap()
            bounds(pixels.width, pixels.height) { x, y -> isRed(pixels[x, y]) } != null
        }
    }

    private fun isRed(color: Color) = color.red > 0.8f && color.green < 0.2f && color.blue < 0.2f

    private fun bounds(width: Int, height: Int, matches: (Int, Int) -> Boolean): IntArray? {
        var left = width
        var right = -1
        var top = height
        var bottom = -1
        for (y in 0 until height) for (x in 0 until width) {
            if (matches(x, y)) {
                left = minOf(left, x)
                right = maxOf(right, x)
                top = minOf(top, y)
                bottom = maxOf(bottom, y)
            }
        }
        return if (right < 0) null else intArrayOf(left, top, right, bottom)
    }

    private fun logoFixture(): String {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val file = File.createTempFile("channel-logo-", ".png", context.cacheDir)
        val bitmap = Bitmap.createBitmap(600, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = android.graphics.Color.RED
        canvas.drawRect(0f, 0f, 50f, 50f, paint)
        paint.color = android.graphics.Color.GREEN
        canvas.drawRect(550f, 0f, 600f, 50f, paint)
        paint.color = android.graphics.Color.BLUE
        canvas.drawRect(0f, 150f, 50f, 200f, paint)
        paint.color = android.graphics.Color.YELLOW
        canvas.drawRect(550f, 150f, 600f, 200f, paint)
        file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
        return file.toURI().toString()
    }
}
