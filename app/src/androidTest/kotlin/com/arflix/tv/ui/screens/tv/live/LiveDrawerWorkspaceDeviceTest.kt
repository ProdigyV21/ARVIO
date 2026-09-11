package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class LiveDrawerWorkspaceDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun slideKeepsContentStableWithoutFrameByFrameMeasurement() = verifySlide(LayoutDirection.Ltr)
    @Test fun slideAndRapidReversalAlsoWorkInRtl() = verifySlide(LayoutDirection.Rtl)

    private fun verifySlide(direction: LayoutDirection) {
        val expanded = mutableStateOf(true)
        var measures = 0
        var visible = true
        compose.setContent {
            CompositionLocalProvider(LocalLayoutDirection provides direction) {
                Box(Modifier.size(600.dp, 360.dp)) {
                    LiveDrawerWorkspace(expanded.value, sidebarWidth = 180.dp, sidebar = {
                        visible = LocalLiveDrawerVisible.current
                        Box(Modifier.fillMaxSize().background(Color.DarkGray))
                    }, content = {
                        Box(Modifier.fillMaxSize().layout { measurable, constraints ->
                            measures++
                            val child = measurable.measure(constraints)
                            layout(child.width, child.height) { child.placeRelative(0, 0) }
                        }) {
                            Box(Modifier.size(80.dp, 40.dp).background(Color.White).testTag("channel"))
                        }
                    })
                }
            }
        }
        fun marker() = compose.onNodeWithTag("channel").getUnclippedBoundsInRoot()
        val open = marker()
        compose.mainClock.autoAdvance = false
        compose.runOnIdle { expanded.value = false }
        compose.mainClock.advanceTimeBy(48)
        val early = marker()
        val duringMeasures = measures
        compose.mainClock.advanceTimeBy(96)
        val later = marker()
        assertEquals("The guide is not remeasured for each animation frame", duringMeasures, measures)
        assertEquals("Channel geometry stays unchanged", (open.right - open.left).value,
            (later.right - later.left).value, 0.01f)
        assertTrue("Keep category content present until it has slid away", visible)
        val movement = if (direction == LayoutDirection.Ltr) open.left - later.left else later.left - open.left
        assertTrue("The drawer actually moves", movement > 0.dp && movement < 180.dp)
        assertTrue("Movement is continuous", early.left != later.left)

        // Reverse before settling: no overshoot, missing content or stale target.
        compose.runOnIdle { expanded.value = true }
        compose.mainClock.advanceTimeBy(32)
        val reversed = marker()
        assertTrue(reversed.left >= minOf(open.left, later.left) - 1.dp)
        assertTrue(reversed.left <= maxOf(open.left, later.left) + 1.dp)
        compose.mainClock.advanceTimeBy(320)
        assertEquals(open, marker())
        assertTrue(visible)
        compose.runOnIdle { expanded.value = false }
        compose.mainClock.advanceTimeBy(320)
        val closed = marker()
        val total = if (direction == LayoutDirection.Ltr) open.left - closed.left else closed.left - open.left
        assertEquals("Travel matches the sidebar width, within pixel rounding", 180f, total.value, 1f)
        assertTrue("Closed drawer no longer exposes category contents", !visible)
    }
}
