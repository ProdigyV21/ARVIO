package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.screens.home.MobileHeroCarousel
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MobileHeroFinishDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun immersiveFadeKeepsTopLightAndEndsInPageColourForBothThemes() {
        val background = mutableStateOf(BackgroundDark)
        compose.setContent {
            Box(Modifier.size(240.dp, 400.dp).background(Color.White).testTag("scrim")) {
                MobileHeroScrim(true, background.value, Modifier.fillMaxSize())
            }
        }
        for (colour in listOf(BackgroundDark, Color.Black)) {
            compose.runOnIdle { background.value = colour }
            val pixels = compose.onNodeWithTag("scrim").captureToImage().toPixelMap()
            val x = pixels.width / 2
            assertTrue("Top shade must remain subtle", pixels[x, 0].red > 0.85f)
            assertTrue("The centre of the artwork must remain clear", pixels[x, pixels.height * 4 / 10].red > 0.97f)
            var previous = 1f
            for (step in 0..24) {
                val y = ((0.4f + step * 0.6f / 24f) * (pixels.height - 1)).toInt()
                val red = pixels[x, y].red
                assertTrue("Bottom fade must be continuous", red <= previous + 0.01f)
                previous = red
            }
            val bottom = pixels[x, pixels.height - 1]
            assertEquals(colour.red, bottom.red, 0.01f)
            assertEquals(colour.green, bottom.green, 0.01f)
            assertEquals(colour.blue, bottom.blue, 0.01f)
        }
    }

    @Test fun bothHeroStylesKeepPaginationInsideArtworkWithoutCoveringMetadata() {
        val poster = mutableStateOf(false)
        val compact = mutableStateOf(false)
        val categories = listOf(Category("featured", "Featured", listOf(
            MediaItem(1, "First feature", year = "2026", imdbRating = "8.4"),
            MediaItem(2, "Second feature", year = "2025", imdbRating = "8.1"),
        )))
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Box(Modifier.width(360.dp).height(680.dp)) {
                    MobileHeroCarousel(categories,
                        heroLayout = if (compact.value) resolveMobileHeroLayout(360f, 240f)
                            else if (poster.value) resolveMobileHeroLayout(360f, 680f)
                            else resolveImmersiveMobileHeroLayout(360f, 680f),
                        posterStyle = poster.value,
                        onNavigateToDetails = { _, _, _, _ -> })
                }
            }
        }
        fun checkLayout() {
            compose.waitForIdle()
            val pager = compose.onNodeWithTag("mobile_hero_pager").fetchSemanticsNode().boundsInRoot
            val dots = compose.onNodeWithTag("mobile_hero_indicators").fetchSemanticsNode().boundsInRoot
            assertTrue("Dots belong inside the hero", dots.top > pager.top && dots.bottom < pager.bottom)
            val metadata = compose.onAllNodesWithTag("mobile_hero_metadata", useUnmergedTree = true)
                .fetchSemanticsNodes().map { it.boundsInRoot }.maxBy { it.width }
            assertTrue("Metadata must stay above pagination", metadata.bottom < dots.top)
        }
        checkLayout()
        val before = compose.onNodeWithTag("mobile_hero_dot_1").fetchSemanticsNode().boundsInRoot.width
        compose.onNodeWithTag("mobile_hero_pager").performTouchInput { swipeLeft() }
        compose.waitForIdle()
        val after = compose.onNodeWithTag("mobile_hero_dot_1").fetchSemanticsNode().boundsInRoot.width
        assertTrue("The selected dot follows a manual swipe", after > before + 10f)
        checkLayout()
        compose.runOnIdle { poster.value = true }
        checkLayout()
        compose.runOnIdle { compact.value = true }
        checkLayout()
    }
}
