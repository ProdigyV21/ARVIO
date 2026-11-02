package com.arflix.tv.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.ImageLoader
import coil.decode.DataSource
import coil.imageLoader
import coil.intercept.Interceptor
import coil.request.SuccessResult
import coil.size.Scale
import coil.size.Size
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import java.io.File
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.awaitCancellation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class StackedMovieDeckDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private data class Request(val url: String, val size: Size, val scale: Scale, val prefetch: Boolean)

    @Test fun artworkFallbackSizesResizeAndPrefetchLifetimeMatchRenderedDeck() {
        val context = compose.activity
        val originalLoader = context.imageLoader
        val requests = ConcurrentLinkedQueue<Request>()
        val started = AtomicInteger()
        val cancelled = AtomicInteger()
        val bitmap = Bitmap.createBitmap(640, 360, Bitmap.Config.ARGB_8888).apply {
            eraseColor(android.graphics.Color.rgb(35, 170, 195))
        }
        val loader = ImageLoader.Builder(context).diskCache(null).components {
            add(Interceptor { chain ->
                val prefetch = chain.request.target == null
                requests.add(Request(chain.request.data.toString(), chain.size, chain.request.scale, prefetch))
                if (prefetch) {
                    started.incrementAndGet()
                    try {
                        awaitCancellation()
                    } finally {
                        cancelled.incrementAndGet()
                    }
                }
                SuccessResult(BitmapDrawable(context.resources, bitmap), chain.request, DataSource.MEMORY)
            })
        }.build()
        val layout = mutableStateOf(landscapeDeckLayout(700f, 300f))
        val visible = mutableStateOf(true)
        val item = MediaItem(1, "Fixture", mediaType = MediaType.MOVIE, image = "fixture-poster", backdrop = " ")
        var opened: MediaItem? = null
        Coil.setImageLoader(loader)
        try {
            compose.setContent {
                CompositionLocalProvider(LocalDensity provides Density(1f)) {
                    if (visible.value) {
                        Box(Modifier.size(1100.dp, 760.dp)) {
                            StackedMovieDeck(listOf(item), layout.value, 0, {}, { opened = it },
                                cardLogoUrls = mapOf("MOVIE_1" to "fixture-logo"),
                                modifier = Modifier.testTag("deck"), autoAdvanceDurationMs = 0)
                        }
                    }
                }
            }
            fun verifyArtworkAndCapture(name: String) {
                val expected = landscapeDeckArtworkSizes(layout.value, 1f)
                compose.waitUntil(10000) {
                    listOf(true, false).all { prefetch ->
                        requests.any { it.prefetch == prefetch && it.url == "fixture-poster" &&
                            it.size == Size(expected.backdrop.width, expected.backdrop.height) && it.scale == Scale.FILL } &&
                        requests.any { it.prefetch == prefetch && it.url == "fixture-logo" &&
                            it.size == Size(expected.logo.width, expected.logo.height) && it.scale == Scale.FIT }
                    }
                }
                // Confirm image pixels are drawn, not just that a request was submitted.
                compose.waitUntil(10000) {
                    val pixels = compose.onNodeWithTag("deck").captureToImage().toPixelMap()
                    var artworkPixels = 0
                    for (y in 0 until pixels.height step 8) for (x in 0 until pixels.width step 8) {
                        if (pixels[x, y].green > .2f && pixels[x, y].blue > .3f) artworkPixels++
                    }
                    artworkPixels > 100
                }
                File(context.getExternalFilesDir(null), "$name.png").outputStream().use {
                    compose.onNodeWithTag("deck").captureToImage().asAndroidBitmap()
                        .compress(Bitmap.CompressFormat.PNG, 100, it)
                }
            }
            verifyArtworkAndCapture("pr855-compact-phone")
            compose.onNodeWithTag("deck").performTouchInput { click(center) }
            compose.runOnIdle { assertEquals(item, opened) }
            compose.runOnIdle { layout.value = landscapeDeckLayout(1100f, 760f) }
            verifyArtworkAndCapture("pr855-tablet")
            compose.waitUntil(10000) { started.get() == 4 && cancelled.get() == 2 }
            assertTrue(requests.none { it.url.isBlank() || it.size == Size.ORIGINAL })
            compose.runOnIdle { visible.value = false }
            compose.waitUntil(10000) { cancelled.get() == started.get() }
        } finally {
            compose.runOnIdle { visible.value = false }
            loader.shutdown()
            Coil.setImageLoader(originalLoader)
        }
    }
}
