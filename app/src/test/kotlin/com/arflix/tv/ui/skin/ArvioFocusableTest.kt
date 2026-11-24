package com.arflix.tv.ui.skin

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import org.robolectric.annotation.GraphicsMode

/**
 * The focus modifier only builds its scale animation once a card has been focused.
 * These tests make sure that did not change what the person sees: a focused card still
 * grows (animated), and a card that loses focus is back at its normal size.
 */
@OptIn(androidx.compose.ui.ExperimentalComposeUiApi::class)
@RunWith(RobolectricTestRunner::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], application = Application::class, qualifiers = "w400dp-h200dp-mdpi")
class ArvioFocusableTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val first = FocusRequester()
    private val second = FocusRequester()

    private val coords = mutableMapOf<Color, androidx.compose.ui.layout.LayoutCoordinates>()
    private val focused = mutableMapOf<Color, Boolean>()

    private fun setCards(showRestBorder: Boolean = false) {
        compose.setContent {
            // Clickable items only take focus in keyboard (D-pad) mode, like on a TV.
            val inputMode = androidx.compose.ui.platform.LocalInputModeManager.current
            androidx.compose.runtime.SideEffect {
                inputMode.requestInputMode(androidx.compose.ui.input.InputMode.Keyboard)
            }
            Box(Modifier.fillMaxSize().background(Color.Black)) {
                Row(Modifier.padding(start = 60.dp, top = 50.dp)) {
                    card(first, Color.Red, showRestBorder)
                    Box(Modifier.size(80.dp))
                    card(second, Color.Green, showRestBorder)
                }
            }
        }
    }

    @androidx.compose.runtime.Composable
    private fun card(requester: FocusRequester, color: Color, showRestBorder: Boolean) {
        ArvioFocusableSurface(
            modifier = Modifier.size(80.dp).focusRequester(requester),
            shape = RoundedCornerShape(0.dp),
            backgroundColor = color,
            focusedScale = 1.25f,
            outlineColor = color,
            outlineWidth = 0.dp,
            glowWidth = 0.dp,
            showRestBorder = showRestBorder,
            onClick = {},
            onFocusChanged = { focused[color] = it },
        ) {
            Box(Modifier.fillMaxSize().onGloballyPositioned { coords[color] = it })
        }
    }

    /** On-screen width in px (mdpi: 1px = 1dp) of the card, including any focus scale. */
    private fun widthOf(color: Color): Int {
        var width = 0
        compose.runOnUiThread {
            // Layer properties (the focus scale) are applied when the layer is drawn, and a
            // hand-driven clock does not draw by itself.
            val view = compose.activity.window.decorView
            view.draw(android.graphics.Canvas(android.graphics.Bitmap.createBitmap(view.width, view.height, android.graphics.Bitmap.Config.ARGB_8888)))
            width = Math.round(coords.getValue(color).boundsInRoot().width)
        }
        return width
    }

    private fun assertWidth(expected: Int, actual: Int) =
        assertTrue("expected about $expected px but was $actual", kotlin.math.abs(expected - actual) <= 2)

    @Test fun unfocusedCardsAreDrawnAtNormalSize() {
        setCards()
        compose.waitForIdle()
        assertWidth(80, widthOf(Color.Red))
        assertWidth(80, widthOf(Color.Green))
    }

    @Test fun focusedCardGrowsAndTheOneThatLostFocusShrinksBack() {
        setCards()
        compose.runOnIdle { first.requestFocus() }
        compose.waitForIdle()
        assertTrue("first card should report focus", focused[Color.Red] == true)
        // 80dp * 1.25 = 100dp
        assertWidth(100, widthOf(Color.Red))
        assertWidth(80, widthOf(Color.Green))

        compose.runOnIdle { second.requestFocus() }
        compose.waitForIdle()
        assertWidth(80, widthOf(Color.Red))
        assertWidth(100, widthOf(Color.Green))

        // Coming back to a card that was focused before animates up again.
        compose.runOnIdle { first.requestFocus() }
        compose.waitForIdle()
        assertWidth(100, widthOf(Color.Red))
        assertWidth(80, widthOf(Color.Green))
    }

    @Test fun focusScaleIsAnimatedNotJumped() {
        compose.mainClock.autoAdvance = false
        setCards()
        compose.mainClock.advanceTimeBy(64)
        compose.runOnUiThread { first.requestFocus() }
        val widths = mutableListOf<Int>()
        repeat(20) {
            compose.mainClock.advanceTimeBy(16)
            widths += widthOf(Color.Red)
        }
        assertTrue("focus should have been reported: $widths", focused[Color.Red] == true)
        assertTrue("never reached the focused size: $widths", widths.last() in 98..100)
        assertTrue("scale jumped instead of animating: $widths", widths.any { it in 82..97 })
        assertTrue("scale should only grow while gaining focus: $widths", widths.zipWithNext().all { (a, b) -> b >= a })
    }

    @Test fun restBorderCardsStillRenderAndFocus() {
        setCards(showRestBorder = true)
        compose.waitForIdle()
        compose.runOnIdle { first.requestFocus() }
        compose.waitForIdle()
        assertWidth(100, widthOf(Color.Red))
    }
}
