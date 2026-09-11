package com.arflix.tv.ui.screens.tv.live

import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.*
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.net.URL

/** Opt-in real metadata/artwork check. Fixture channel, no provider stream requests. */
class SportsMetadataDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun liveMetadataRendersInActualSportsPane() {
        val endpoint = InstrumentationRegistry.getArguments().getString("sportsMetadataUrl")
        assumeTrue("Requires a deployed metadata endpoint", endpoint?.startsWith("https://") == true)
        val connection = URL(endpoint).openConnection().apply { connectTimeout = 10_000; readTimeout = 20_000 }
        val metadata = parseSportsMetadata(connection.getInputStream().bufferedReader().use { it.readText() })
        val now = System.currentTimeMillis()
        val relevant = metadata.filter { it.startsAt!! > now - 7_200_000 && it.background.isNotBlank() && GuideSport.fromText(it.genres.joinToString(" ")) != null }.take(24)
        assertTrue("Real feed must have relevant event artwork", relevant.size >= 4)
        val events = relevant.mapIndexed { index, item -> SportsGuideEvent("metadata:$index", item.title,
            GuideSport.fromText(item.genres.joinToString(" "))!!,
            IptvProgram(item.title, startUtcMillis = item.startsAt!!, endUtcMillis = item.startsAt + 7_200_000),
            listOf(IptvChannel("fixture:$index", "Artwork test channel", "https://example.invalid/not-played", "Sports"))) }
        val decorated = buildSportsCatalogue(events, relevant, emptyList(), now)
        compose.setContent { SportsGuidePane(decorated, now, false, 0, {}, {}, {}, Modifier.fillMaxSize()) }
        compose.waitUntil(45_000) { compose.onAllNodesWithTag("sports-artwork-loaded", useUnmergedTree = true).fetchSemanticsNodes().size >= 3 }
        compose.onNodeWithText("TheSportsDB").assertDoesNotExist()
        compose.onNodeWithText("Available on my channels").assertDoesNotExist()
        compose.waitForIdle()
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.waitForIdleSync()
        android.os.SystemClock.sleep(500)
        val bitmap = instrumentation.uiAutomation.takeScreenshot()
        val folder = File(instrumentation.targetContext.getExternalFilesDir(null), "tv-overhaul").apply { mkdirs() }
        File(folder, "sportsdb-live-artwork-fixture.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
    }

    @Test fun fixtureCatalogueDoesNotDisplayUnmatchedEvents() {
        val endpoint = InstrumentationRegistry.getArguments().getString("sportsMetadataUrl")
        assumeTrue(endpoint?.startsWith("https://") == true)
        val connection = URL(endpoint).openConnection().apply { connectTimeout = 10_000; readTimeout = 20_000 }
        val metadata = parseSportsMetadata(connection.getInputStream().bufferedReader().use { it.readText() })
        val now = System.currentTimeMillis()
        val events = buildSportsCatalogue(emptyList(), metadata, emptyList(), now)
        assertTrue(events.any { it.fixture != null })
        assertTrue(sportsGuideRows(events, now).isNotEmpty())
        compose.setContent { SportsGuidePane(events, now, false, 0, {}, {}, {}, Modifier.fillMaxSize()) }
        compose.onNodeWithText("No sports events matched to your channels").assertIsDisplayed()
        compose.onAllNodesWithTag("sports-event-card").assertCountEquals(0)
        compose.onNodeWithText("Available on my channels").assertDoesNotExist()
        capture("sports-catalogue-unmatched-hidden.png")
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        android.os.SystemClock.sleep(500)
        val bitmap = instrumentation.uiAutomation.takeScreenshot()
        val folder = File(instrumentation.targetContext.getExternalFilesDir(null), "tv-overhaul").apply { mkdirs() }
        File(folder, name).outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
    }
}
