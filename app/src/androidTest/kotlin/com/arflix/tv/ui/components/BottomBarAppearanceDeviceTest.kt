package com.arflix.tv.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.arflix.tv.ui.skin.LocalAccentColorOverride
import com.arflix.tv.ui.skin.accentColorFromName
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.math.abs

class BottomBarAppearanceDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun landscapeSettingsStaysPinnedWhenUpperItemsScrollAndWindowResizes() {
        val height = mutableStateOf(180.dp)
        compose.setContent {
            val landscape = Configuration(LocalConfiguration.current).apply {
                screenWidthDp = 800
                screenHeightDp = 360
                orientation = Configuration.ORIENTATION_LANDSCAPE
            }
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE,
                LocalConfiguration provides landscape) {
                Box(Modifier.fillMaxSize()) {
                    AppBottomBar("home", {}, Modifier.height(height.value).testTag("rail"))
                }
            }
        }
        val settings = compose.onNodeWithContentDescription(compose.activity.getString(com.arflix.tv.R.string.settings))
        settings.assertIsDisplayed().assertHeightIsAtLeast(48.dp)
        val original = settings.fetchSemanticsNode().boundsInRoot
        compose.onNodeWithContentDescription(compose.activity.getString(com.arflix.tv.R.string.topbar_tv)).performScrollTo()
        assertEquals(original, settings.fetchSemanticsNode().boundsInRoot)
        compose.runOnIdle { height.value = 320.dp }
        compose.waitForIdle()
        val resized = settings.fetchSemanticsNode().boundsInRoot
        val rail = compose.onNodeWithTag("rail").fetchSemanticsNode().boundsInRoot
        assertTrue(resized.top > original.top)
        assertTrue(resized.bottom <= rail.bottom)
        assertTrue(resized.top > rail.center.y)
    }

    @Test fun tabsKeepBoundsAndUseEverySettingsAccent() {
        val route = mutableStateOf("home")
        val accent = mutableStateOf(Color.White)
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE,
                LocalAccentColorOverride provides accent.value) {
                Box(Modifier.fillMaxSize().background(Color(0xFF34745C))) {
                    AppBottomBar(route.value, { route.value = it },
                        Modifier.align(Alignment.BottomCenter).testTag("bar"))
                }
            }
        }
        val originalBounds = bottomBarItems.map {
            compose.onNodeWithContentDescription(compose.activity.getString(it.labelRes)).fetchSemanticsNode().boundsInRoot
        }
        bottomBarItems.forEach { item ->
            val tab = compose.onNodeWithContentDescription(compose.activity.getString(item.labelRes))
            tab.performClick().assertIsSelected().assertHeightIsAtLeast(48.dp)
            compose.runOnIdle { assertEquals(item.route, route.value) }
            assertEquals(originalBounds, bottomBarItems.map {
                compose.onNodeWithContentDescription(compose.activity.getString(it.labelRes)).fetchSemanticsNode().boundsInRoot
            })
        }
        for (name in listOf("White", "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet")) {
            val expected = accentColorFromName(name)
            compose.runOnIdle { accent.value = expected }
            // PixelCopy can see the previous frame while the icon colour/ripple finishes drawing.
            compose.waitUntil(timeoutMillis = 5000) {
                val pixels = compose.onNodeWithTag("bar").captureToImage().toPixelMap()
                var matching = 0
                for (y in 0 until pixels.height) for (x in 0 until pixels.width) {
                    val pixel = pixels[x, y]
                    if (abs(pixel.red - expected.red) < .03f && abs(pixel.green - expected.green) < .03f &&
                        abs(pixel.blue - expected.blue) < .03f) matching++
                }
                matching > 20
            }
            val pixels = compose.onNodeWithTag("bar").captureToImage().toPixelMap()
            // The top of the bar must reveal the page, rather than paint an opaque strip.
            val top = pixels[pixels.width / 2, 0]
            assertTrue("Content is visible through the dark tint", top.green > .01f && top.green < .35f)
        }
    }
}
