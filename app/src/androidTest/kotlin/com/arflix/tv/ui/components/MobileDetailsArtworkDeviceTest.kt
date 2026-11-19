package com.arflix.tv.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import coil.ImageLoader
import coil.decode.DataSource
import coil.fetch.DrawableResult
import coil.fetch.Fetcher
import coil.request.Options
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MobileDetailsArtworkDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun portraitKeepsTopAndBottomOfPosterEvenInShortViewport() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val loader = ImageLoader.Builder(context).components {
            add(object : Fetcher.Factory<Uri> {
                override fun create(data: Uri, options: Options, imageLoader: ImageLoader): Fetcher? {
                    if (data.scheme != "fixture") return null
                    return Fetcher {
                        val bitmap = Bitmap.createBitmap(80, 120, Bitmap.Config.ARGB_8888)
                        bitmap.eraseColor(android.graphics.Color.BLUE)
                        for (y in 0 until 120) for (x in 0 until 80) {
                            if (y < 15) bitmap.setPixel(x, y, android.graphics.Color.RED)
                            if (y >= 105) bitmap.setPixel(x, y, android.graphics.Color.GREEN)
                        }
                        DrawableResult(BitmapDrawable(context.resources, bitmap), false, DataSource.NETWORK)
                    }
                }
            })
        }.build()
        val height = mutableStateOf(360.dp)
        compose.setContent {
            Box(Modifier.size(240.dp, height.value).background(Color.Black).testTag("art")) {
                MobileDetailsArtwork(MobileDetailsArtworkSource("fixture://poster", true),
                    Modifier.matchParentSize(), loader)
            }
        }
        fun checkEdges() {
            compose.waitUntil(8000) {
                val pixels = compose.onNodeWithTag("art").captureToImage().toPixelMap()
                pixels[pixels.width / 2, 4].red > 0.9f && pixels[pixels.width / 2, pixels.height - 5].green > 0.9f
            }
            val pixels = compose.onNodeWithTag("art").captureToImage().toPixelMap()
            assertTrue(pixels[pixels.width / 2, pixels.height / 2].blue > 0.9f)
        }
        checkEdges()
        compose.runOnIdle { height.value = 280.dp }
        checkEdges()
        loader.shutdown()
    }
}
