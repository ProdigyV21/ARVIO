package com.arflix.tv.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DragHandleReorderDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val keys = mutableStateOf(listOf("a", "b", "c"))
    private val drops = mutableListOf<Triple<String, Int, Int>>()

    @Test fun draggingStoresOneMoveOnlyOnRelease() {
        fixture()
        holdAndMoveFirstRow()
        assertTrue(drops.isEmpty())
        compose.onRoot().performTouchInput { up() }
        compose.waitForIdle()
        assertEquals(1, drops.size)
        assertEquals("a", drops.single().first)
        assertEquals(0, drops.single().second)
        assertTrue(drops.single().third > 0)
    }

    @Test fun changingSourceOrderDuringDragDoesNotWriteAStaleMove() {
        fixture()
        holdAndMoveFirstRow()
        compose.runOnUiThread { keys.value = listOf("c", "a", "b") }
        compose.mainClock.advanceTimeBy(32)
        compose.waitForIdle()
        compose.onNodeWithText("a:false").assertIsDisplayed()
        compose.onRoot().performTouchInput { up() }
        compose.waitForIdle()
        assertTrue(drops.isEmpty())
    }

    @Test fun canceledPointerGestureDoesNotStoreAMove() {
        fixture()
        holdAndMoveFirstRow()
        compose.onRoot().performTouchInput { cancel() }
        compose.waitForIdle()
        assertTrue(drops.isEmpty())
    }

    private fun holdAndMoveFirstRow() {
        compose.onNodeWithTag("handle-a").performTouchInput { down(center) }
        compose.mainClock.advanceTimeBy(700)
        compose.waitForIdle()
        compose.onNodeWithText("a:true").assertIsDisplayed()
        compose.onRoot().performTouchInput { moveBy(Offset(0f, 100f)) }
        compose.mainClock.advanceTimeBy(32)
        compose.waitForIdle()
    }

    private fun fixture() {
        compose.mainClock.autoAdvance = false
        compose.setContent {
            DragHandleReorderColumn(
                items = keys.value,
                key = { it },
                onDrop = { item, from, to -> drops += Triple(item, from, to) },
                heldBackground = Color.Black,
                heldAccent = Color.Green,
                modifier = Modifier.width(240.dp)
            ) { item, _, _, handle, held ->
                Box(Modifier.width(240.dp).height(60.dp)) {
                    Text("$item:$held")
                    Box(Modifier.width(48.dp).height(60.dp).testTag("handle-$item").then(handle))
                }
            }
        }
    }
}
