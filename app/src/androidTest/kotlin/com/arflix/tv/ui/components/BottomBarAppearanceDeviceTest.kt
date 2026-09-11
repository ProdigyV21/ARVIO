package com.arflix.tv.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
            compose.onNodeWithText(compose.activity.getString(it.labelRes)).fetchSemanticsNode().boundsInRoot
        }
        bottomBarItems.forEach { item ->
            val tab = compose.onNodeWithText(compose.activity.getString(item.labelRes))
            tab.performClick().assertIsSelected().assertHeightIsAtLeast(48.dp)
            compose.runOnIdle { assertEquals(item.route, route.value) }
            assertEquals(originalBounds, bottomBarItems.map {
                compose.onNodeWithText(compose.activity.getString(it.labelRes)).fetchSemanticsNode().boundsInRoot
            })
        }
        for (name in listOf("White", "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet")) {
            val expected = accentColorFromName(name)
            compose.runOnIdle { accent.value = expected }
            compose.waitForIdle()
            val pixels = compose.onNodeWithTag("bar").captureToImage().toPixelMap()
            var matching = 0
            for (y in 0 until pixels.height) for (x in 0 until pixels.width) {
                val pixel = pixels[x, y]
                if (abs(pixel.red - expected.red) < .03f && abs(pixel.green - expected.green) < .03f &&
                    abs(pixel.blue - expected.blue) < .03f) matching++
            }
            assertTrue("Active icon must render $name from Settings", matching > 20)
            // The top of the bar must reveal the page, rather than paint an opaque strip.
            val top = pixels[pixels.width / 2, 0]
            assertTrue("Content is visible through the fade", top.green > .35f)
        }
    }
}
