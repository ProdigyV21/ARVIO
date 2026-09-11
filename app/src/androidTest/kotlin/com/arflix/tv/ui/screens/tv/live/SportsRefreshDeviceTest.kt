package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import org.junit.Rule
import org.junit.Test
import org.junit.After
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File

class SportsRefreshDeviceTest {
    @get:Rule val compose = createComposeRule()

    @After fun captureUiState() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val bitmap = instrumentation.uiAutomation.takeScreenshot()
        val folder = File(instrumentation.targetContext.getExternalFilesDir(null), "tv-overhaul").apply { mkdirs() }
        File(folder, "sports-refresh-test.png").outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
        compose.onAllNodes(isRoot(), useUnmergedTree = true).printToLog("SportsRefreshTest")
    }

    @Test fun completeScheduleRefreshAndOpenPickerSurviveEventRemoval() {
        val now = System.currentTimeMillis()
        val channel = IptvChannel("fixture:1", "Test channel", "https://example.invalid/not-played", "Sports")
        val schedule = (1..500).map { i ->
            val programme = IptvProgram("North $i vs South $i", startUtcMillis = now - 60000, endUtcMillis = now + 3600000)
            SportsGuideEvent("event:$i", programme.title, GuideSport.FOOTBALL, programme, listOf(channel))
        }
        val events = mutableStateOf(schedule)
        compose.setContent { SportsGuidePane(events.value, now, false, 0, {}, {}, {}, Modifier.fillMaxSize()) }
        compose.waitUntil(5000) { compose.onAllNodesWithTag("sports-event-card").fetchSemanticsNodes().isNotEmpty() }
        compose.onAllNodesWithTag("sports-event-card")[0].performClick()
        compose.waitUntil(5000) { compose.onNodeWithContentDescription("Close").isDisplayed() }
        compose.onNodeWithText("Test channel").assertIsDisplayed()
        compose.runOnIdle { events.value = emptyList() }
        compose.waitForIdle()
        compose.onNodeWithText("This event is no longer in the available guide.").assertIsDisplayed()
        compose.onNodeWithContentDescription("Close").performClick()
        compose.runOnIdle { events.value = schedule.reversed() }
        compose.waitUntil(5000) { compose.onAllNodesWithTag("sports-event-card").fetchSemanticsNodes().isNotEmpty() }
    }
}
