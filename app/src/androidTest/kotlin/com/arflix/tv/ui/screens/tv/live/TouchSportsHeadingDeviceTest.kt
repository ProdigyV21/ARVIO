package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.detectDeviceType
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class TouchSportsHeadingDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun enteringSportsDoesNotFocusOrScrollTheFirstTouchCardUnderTheToolbar() {
        androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().setInTouchMode(true)
        val now = System.currentTimeMillis()
        val channel = IptvChannel("touch:1", "QA Sports", "https://example.invalid/not-played", "Sports")
        val programme = IptvProgram("North vs South", startUtcMillis = now - 60000, endUtcMillis = now + 3600000)
        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        val event = SportsGuideEvent("touch-heading", programme.title, GuideSport.FOOTBALL, programme, listOf(channel),
            artwork = "android.resource://${context.packageName}/${com.arflix.tv.R.mipmap.ic_launcher_foreground}")
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides detectDeviceType(LocalContext.current)) {
                Column(Modifier.fillMaxSize()) {
                    Box(Modifier.fillMaxWidth().height(56.dp).testTag("toolbar"))
                    SportsGuidePane(listOf(event), now, false, 1, {}, {}, {}, Modifier.weight(1f), showHeader = false)
                }
            }
        }
        compose.waitUntil(5000) { compose.onAllNodesWithTag("sports-event-card").fetchSemanticsNodes().isNotEmpty() }
        compose.waitForIdle()
        val toolbar = compose.onNodeWithTag("toolbar").getUnclippedBoundsInRoot()
        val heading = compose.onNodeWithText("Featured live").getUnclippedBoundsInRoot()
        val scroll = compose.onNodeWithTag("sports-guide-list").fetchSemanticsNode().config[
            androidx.compose.ui.semantics.SemanticsProperties.VerticalScrollAxisRange].value()
        assertTrue("Entering touch Sports scrolled the catalogue by $scroll", scroll == 0f)
        assertTrue("Heading $heading was scrolled under toolbar $toolbar", heading.top >= toolbar.bottom)
        assertTrue("Heading must retain its full text height", heading.bottom.value - heading.top.value >= 18f)
        assertTrue("Touch mode should not steal focus", compose.onAllNodes(isFocused()).fetchSemanticsNodes().isEmpty())
    }
}
