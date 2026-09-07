package com.arflix.tv.ui.screens.player.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SeekPreviewOverlayDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun previewDoesNotMoveBottomAnchoredControls() {
        val browsing = mutableStateOf(false)
        val ready = mutableStateOf(false)
        compose.setContent {
            Box(Modifier.size(500.dp, 400.dp)) {
                Column(Modifier.align(Alignment.BottomCenter).testTag("controls")) {
                    Box(Modifier.size(300.dp, 48.dp).testTag("buttons"))
                    if (browsing.value) {
                        Box(Modifier.seekPreviewOverlay().height(128.dp)) {
                            if (ready.value) Box(Modifier.size(224.dp, 126.dp))
                        }
                    }
                    Box(Modifier.size(300.dp, 20.dp).testTag("seekbar"))
                }
            }
        }
        val tags = listOf("controls", "buttons", "seekbar")
        fun bounds() = tags.map { compose.onNodeWithTag(it).fetchSemanticsNode().boundsInRoot }
        val initial = bounds()
        compose.runOnIdle { browsing.value = true }
        assertEquals(initial, bounds())
        compose.runOnIdle { ready.value = true }
        assertEquals(initial, bounds())
        compose.runOnIdle { ready.value = false }
        assertEquals(initial, bounds())
        compose.runOnIdle { browsing.value = false }
        assertEquals(initial, bounds())
    }
}
