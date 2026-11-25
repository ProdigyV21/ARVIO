package com.arflix.tv.ui.focus

import android.app.Application
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
@Config(sdk = [33], application = Application::class, qualifiers = "w1280dp-h720dp-xhdpi")
class TvPivotScrollTest {
    @get:Rule val compose = createComposeRule()

    @Test fun pivotMathMatchesTvLazyRowDefaults() {
        // Item at 900px in a 1000px viewport: its leading edge moves to 30% (300px).
        assertEquals(600f, tvPivotScrollDistance(offset = 900f, size = 100f, containerSize = 1000f), 0.001f)
        // Already at the pivot: no scroll.
        assertEquals(0f, tvPivotScrollDistance(offset = 300f, size = 100f, containerSize = 1000f), 0.001f)
        // A wide item that would not fit after the pivot ends at the trailing edge instead
        // (leading edge at 1000 - 800 = 200px).
        assertEquals(700f, tvPivotScrollDistance(offset = 900f, size = 800f, containerSize = 1000f), 0.001f)
        // parentFraction 0 pins the item to the leading edge (CollectionDetails grid).
        assertEquals(900f, tvPivotScrollDistance(900f, 100f, 1000f, parentFraction = 0f), 0.001f)
    }

    @Test fun tinyNudgesForFullyVisibleItemsAreSkipped() {
        // 5px away from the pivot and fully on screen: no twitch.
        assertEquals(0f, tvPivotScrollDistance(offset = 305f, size = 100f, containerSize = 1000f), 0.001f)
        assertEquals(0f, tvPivotScrollDistance(offset = 295f, size = 100f, containerSize = 1000f), 0.001f)
        // Anything from 12px up still scrolls.
        assertEquals(12f, tvPivotScrollDistance(offset = 312f, size = 100f, containerSize = 1000f), 0.001f)
        assertEquals(30f, tvPivotScrollDistance(offset = 330f, size = 100f, containerSize = 1000f), 0.001f)
        // An item that is partly off the leading edge must still be brought fully into view.
        assertEquals(-5f, tvPivotScrollDistance(offset = -5f, size = 100f, containerSize = 1000f, parentFraction = 0f), 0.001f)
        // Same for one hanging over the trailing edge.
        assertEquals(8f, tvPivotScrollDistance(offset = 908f, size = 100f, containerSize = 1000f, parentFraction = 0.9f), 0.001f)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test fun dpadRightScrollsTheRowToThePivotWithoutCrashing() {
        lateinit var state: LazyListState
        val first = FocusRequester()
        compose.setContent {
            state = rememberLazyListState()
            TvPivotScroll {
                LazyRow(Modifier.width(1000.dp).testTag("row").arvioDpadFocusGroup(), state = state) {
                    items(40, key = { it }, contentType = { "card" }) { index ->
                        Box(Modifier.size(200.dp)
                            .then(if (index == 0) Modifier.focusRequester(first) else Modifier)
                            .testTag("card$index").focusable())
                    }
                }
            }
        }
        compose.runOnIdle { first.requestFocus() }
        repeat(12) { compose.onNodeWithTag("row").performKeyInput { pressKey(Key.DirectionRight) } }
        compose.waitForIdle()
        // After 12 steps focus is on card 12; with pivot scrolling the row has moved so
        // card 12 sits near 30% of the viewport, i.e. the row scrolled well past card 9.
        assertTrue("first visible ${state.firstVisibleItemIndex}", state.firstVisibleItemIndex in 9..12)
    }
}
