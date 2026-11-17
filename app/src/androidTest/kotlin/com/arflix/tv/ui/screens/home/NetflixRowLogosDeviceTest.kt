package com.arflix.tv.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.arflix.tv.data.model.MediaItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

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
}
