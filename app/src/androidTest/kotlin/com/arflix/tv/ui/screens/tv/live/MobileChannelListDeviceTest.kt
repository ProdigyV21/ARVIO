package com.arflix.tv.ui.screens.tv.live

import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MobileChannelListDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun listRetainsTuneRepeatPressContextMenuAndVisibleRange() {
        val channels = (0 until 50).map { IptvChannel("review-$it", "Demo channel $it", "https://example.invalid/$it", "Demo").enrich(it) }
        val guide = channels.associate { it.id to IptvNowNext(IptvProgram("Current programme", startUtcMillis = 0, endUtcMillis = 1000),
            IptvProgram("Next programme", startUtcMillis = 1000, endUtcMillis = 2000)) }
        var tuned: String? = null
        var tunes = 0
        var menu: String? = null
        var lastVisible = 0
        compose.setContent {
            MobileChannelList(channels, guide, null, emptySet(), onSelect = { tuned = it.id; tunes++ },
                onToggleFavorite = {}, onLongPress = { menu = it.id }, onVisibleRange = { _, last -> lastVisible = last })
        }
        compose.onNodeWithText("Demo channel 0").performClick().performClick()
        compose.runOnIdle { assertEquals("review-0", tuned); assertEquals(2, tunes) }
        compose.onNodeWithTag("mobile-channel-list").performScrollToIndex(25)
        compose.onNodeWithText("Demo channel 25").assertIsDisplayed().performTouchInput { longClick() }
        compose.runOnIdle { assertEquals("review-25", menu); assertTrue(lastVisible >= 25) }
        compose.onAllNodesWithText("Next  Next programme").onFirst().assertIsDisplayed()
    }
}
