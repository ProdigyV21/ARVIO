package com.arflix.tv.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.sp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.math.abs

@OptIn(ExperimentalTestApi::class)
class NetflixRowRenderCacheDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun switchingBetweenLiveAndCachedRenderingDoesNotRecomposeTheRow() {
        val ready = mutableStateOf(false)
        var compositions = 0
        compose.setContent {
            Box(Modifier.netflixRowRenderCache(true, ready).size(100.dp)
                .background(Color.Red).testTag("static")) {
                SideEffect { compositions++ }
            }
        }
        compose.waitForIdle()
        val initial = compositions
        repeat(4) {
            compose.runOnUiThread { ready.value = !ready.value }
            val pixels = compose.onNodeWithTag("static").captureToImage().toPixelMap()
            assertEquals(Color.Red, pixels[pixels.width / 2, pixels.height / 2])
            compose.runOnIdle { assertEquals(initial, compositions) }
        }
    }

    @Test fun cachingPreservesLayoutAndPixelsIncludingOverflowAndTranslucency() = verifyCachePixels(null)

    @Test fun cachingPreservesOverflowAtLowDisplayDensity() = verifyCachePixels(Density(1f))

    private fun verifyCachePixels(densityOverride: Density?) {
        val cached = mutableStateOf(false)
        compose.setContent {
            CompositionLocalProvider(LocalDensity provides (densityOverride ?: LocalDensity.current)) {
                Box(Modifier.size(220.dp, 140.dp).background(Color(0xFF172132)).testTag("surface")) {
                    Box(Modifier.padding(24.dp).netflixRowRenderCache(cached.value)
                        .testTag("row").size(120.dp, 70.dp).drawBehind {
                            drawRect(Color.White.copy(alpha = 0.28f), Offset(-12f, -12f),
                                Size(size.width + 24f, size.height + 24f))
                            drawRoundRect(Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                                cornerRadius = CornerRadius(12f))
                            drawRoundRect(Color.White.copy(alpha = 0.4f), cornerRadius = CornerRadius(12f),
                                style = Stroke(1f))
                        }) {
                        BasicText("Static row", style = TextStyle(fontSize = 14.sp, color = Color.White,
                            shadow = Shadow(Color.Black, Offset(2f, 2f), 6f)))
                    }
                }
            }
        }
        val bounds = compose.onNodeWithTag("row").fetchSemanticsNode().boundsInRoot
        val before = compose.onNodeWithTag("surface").captureToImage().toPixelMap()
        compose.runOnUiThread { cached.value = true }
        assertEquals(bounds, compose.onNodeWithTag("row").fetchSemanticsNode().boundsInRoot)
        val after = compose.onNodeWithTag("surface").captureToImage().toPixelMap()
        assertEquals(before.width, after.width)
        assertEquals(before.height, after.height)
        for (y in 0 until before.height) for (x in 0 until before.width) {
            val a = before[x, y]
            val b = after[x, y]
            if (abs(a.red - b.red) > 0.02f || abs(a.green - b.green) > 0.02f ||
                abs(a.blue - b.blue) > 0.02f || abs(a.alpha - b.alpha) > 0.02f) {
                org.junit.Assert.fail("Cache changed pixel ($x,$y): $a -> $b")
            }
        }
        compose.runOnUiThread { cached.value = false }
        assertEquals(bounds, compose.onNodeWithTag("row").fetchSemanticsNode().boundsInRoot)
    }

    @Test fun cachedRowsUpdateWhenAChildProgressBarChangesSize() {
        val progress = mutableStateOf(0.25f)
        compose.setContent {
            Box(Modifier.netflixRowRenderCache(true).size(100.dp, 20.dp)
                .background(Color.Black).testTag("progress")) {
                Box(Modifier.fillMaxWidth(progress.value).fillMaxHeight().background(Color.Green))
            }
        }
        fun atThreeQuarters(): Color {
            val pixels = compose.onNodeWithTag("progress").captureToImage().toPixelMap()
            return pixels[pixels.width * 3 / 4, pixels.height / 2]
        }
        assertEquals(Color.Black, atThreeQuarters())
        compose.runOnUiThread { progress.value = 1f }
        assertEquals(Color.Green, atThreeQuarters())
    }

    @Test fun cachedRowsStillRenderEveryArtworkFadeFrame() {
        val blue = mutableStateOf(false)
        compose.setContent {
            val alpha = remember { Animatable(0f) }
            LaunchedEffect(blue.value) { alpha.animateTo(if (blue.value) 1f else 0f, tween(140)) }
            Box(Modifier.netflixRowRenderCache(true).size(100.dp).testTag("artwork").drawBehind {
                drawRect(Color.Red)
                drawRect(Color.Blue, alpha = alpha.value)
            })
        }
        fun center(): Color {
            val pixels = compose.onNodeWithTag("artwork").captureToImage().toPixelMap()
            return pixels[pixels.width / 2, pixels.height / 2]
        }
        assertEquals(Color.Red, center())
        compose.mainClock.autoAdvance = false
        try {
            compose.runOnUiThread { blue.value = true }
            compose.mainClock.advanceTimeByFrame()
            compose.mainClock.advanceTimeBy(64)
            val middle = center()
            assertTrue("The cache must not freeze a running fade: $middle", middle.red > 0.1f && middle.blue > 0.1f)
            compose.mainClock.advanceTimeBy(200)
            assertEquals(Color.Blue, center())
        } finally {
            compose.mainClock.autoAdvance = true
        }
    }
}
