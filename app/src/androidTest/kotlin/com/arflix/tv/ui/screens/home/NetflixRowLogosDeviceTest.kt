package com.arflix.tv.ui.screens.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import coil.EventListener
import coil.ImageLoader
import coil.decode.DataSource
import coil.fetch.DrawableResult
import coil.fetch.Fetcher
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.Options
import com.arflix.tv.data.model.MediaItem
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean

class NetflixRowLogosDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun unrelatedLogoUpdatesReuseTheRowMapAndRelevantUpdatesStillAppear() {
        val item = MediaItem(10, "First title")
        val key = "${item.mediaType}_${item.id}"
        val logos = mutableStateMapOf(key to "first-logo", "other-row" to "other-logo")
        var observed = emptyMap<String, String>()
        val items = listOf(item)
        compose.setContent {
            val scoped = rememberNetflixRowLogos(items, logos)
            SideEffect { observed = scoped }
        }
        val original = compose.runOnIdle { observed }
        compose.runOnUiThread { logos["other-row"] = "updated-other-logo" }
        compose.runOnIdle {
            assertSame("Unchanged row logos must not invalidate the rail", original, observed)
            assertEquals(mapOf(key to "first-logo"), observed)
        }
        compose.runOnUiThread { logos[key] = "updated-first-logo" }
        compose.runOnIdle {
            assertNotSame(original, observed)
            assertEquals(mapOf(key to "updated-first-logo"), observed)
        }
        compose.runOnUiThread { logos.remove(key) }
        compose.runOnIdle { assertTrue("Removing a logo must restore the existing fallback", observed.isEmpty()) }
    }

    @Test fun portraitTitleStaysVisibleUntilTheLogoLoadsAndReturnsOnError() {
        val item = MediaItem(11, "Slice title")
        val url = mutableStateOf<String?>(null)
        val pending = AtomicBoolean(false)
        val failed = AtomicBoolean(false)
        val release = CompletableDeferred<Unit>()
        val loader = ImageLoader.Builder(compose.activity)
            .components {
                add(object : Fetcher.Factory<Uri> {
                    override fun create(data: Uri, options: Options, imageLoader: ImageLoader): Fetcher? {
                        if (data.scheme != "fixture") return null
                        return Fetcher {
                            check(data.path == "/valid") { "Broken logo" }
                            pending.set(true)
                            release.await()
                            val bitmap = Bitmap.createBitmap(120, 30, Bitmap.Config.ARGB_8888)
                            bitmap.eraseColor(android.graphics.Color.WHITE)
                            DrawableResult(BitmapDrawable(compose.activity.resources, bitmap), false, DataSource.NETWORK)
                        }
                    }
                })
            }
            .eventListener(object : EventListener {
                override fun onError(request: ImageRequest, result: ErrorResult) { failed.set(true) }
            })
            .build()

        try {
            compose.setContent { NetflixSliceTitle(item, url.value, Modifier.size(120.dp, 180.dp), loader) }
            compose.onNodeWithText(item.title).assertIsDisplayed()

            compose.runOnIdle { url.value = "fixture://logo/valid" }
            compose.waitUntil(5_000) { pending.get() }
            compose.onNodeWithText(item.title).assertIsDisplayed()

            release.complete(Unit)
            compose.waitUntil(5_000) { compose.onAllNodesWithText(item.title).fetchSemanticsNodes().isEmpty() }

            compose.runOnIdle { url.value = "fixture://logo/broken" }
            compose.waitUntil(5_000) { failed.get() }
            compose.onNodeWithText(item.title).assertIsDisplayed()
        } finally {
            loader.shutdown()
        }
    }
}
