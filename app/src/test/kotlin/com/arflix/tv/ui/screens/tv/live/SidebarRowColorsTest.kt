package com.arflix.tv.ui.screens.tv.live

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SidebarRowColorsTest {
    @Test
    fun activeTouchCategoryUsesDarkTextAndIconsOnWhite() {
        val colors = sidebarRowColors(focused = false, active = true, isTouch = true)
        assertEquals(Color.White, colors.background)
        assertEquals(Color.Black, colors.foreground)
        assertEquals(Color.Black.copy(alpha = .7f), colors.secondary)
        assertEquals(Color.Black, colors.accessory)
    }

    @Test
    fun activeTvCategoryKeepsItsDarkSelectedTreatment() {
        val colors = sidebarRowColors(focused = false, active = true, isTouch = false)
        assertEquals(LiveColors.FocusBg, colors.background)
        assertEquals(LiveColors.Fg, colors.foreground)
        assertEquals(LiveColors.FgDim, colors.secondary)
        assertEquals(LiveColors.FgMute, colors.accessory)
    }

    @Test
    fun focusKeepsAllContentDarkOnBothDeviceTypes() {
        for (touch in listOf(false, true)) {
            for (active in listOf(false, true)) {
                val colors = sidebarRowColors(focused = true, active = active, isTouch = touch)
                assertEquals(Color.White, colors.background)
                assertEquals(Color.Black, colors.foreground)
                assertEquals(Color.Black.copy(alpha = .7f), colors.secondary)
                assertEquals(Color.Black, colors.accessory)
            }
        }
    }

    @Test
    fun clearingSelectionRestoresTheUnselectedPalette() {
        for (touch in listOf(false, true)) {
            val colors = sidebarRowColors(focused = false, active = false, isTouch = touch)
            assertEquals(Color.Transparent, colors.background)
            assertEquals(LiveColors.FgDim, colors.foreground)
            assertEquals(LiveColors.FgDim, colors.secondary)
            assertEquals(LiveColors.FgMute, colors.accessory)
        }
    }

    @Test
    fun labelsCountsAndAccessoryIconsHaveReadableContrastOnLightSurfaces() {
        for (touch in listOf(false, true)) {
            for (focused in listOf(false, true)) {
                for (active in listOf(false, true)) {
                    val colors = sidebarRowColors(focused, active, touch)
                    if (colors.background != Color.White) continue
                    for (ink in listOf(colors.foreground, colors.secondary, colors.accessory)) {
                        val visibleInk = ink.compositeOver(colors.background)
                        val contrast = (colors.background.luminance() + .05f) / (visibleInk.luminance() + .05f)
                        assertTrue("Light category surfaces must keep readable content", contrast >= 4.5f)
                    }
                }
            }
        }
    }
}
