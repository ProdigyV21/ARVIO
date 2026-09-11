package com.arflix.tv.ui.screens.tv.live

import org.junit.Assert.*
import org.junit.Test

class GuideRenderWindowTest {
    @Test fun windowIncludesVisibleTimeAndOverscanNotEntireTwelveHours() {
        val window = guideRenderWindow(600, 600f, 5f)
        assertEquals(GuideRenderWindow(90, 270), window)
        assertTrue(window.intersects(110, 130))
        assertTrue(window.intersects(230, 260))
        assertFalse(window.intersects(300, 330))
        assertEquals(6, (0 until 720 step 30).count { window.intersects(it, it + 30) })
    }

    @Test fun smallPixelMovementDoesNotRebuildTheWholeCellSet() {
        assertEquals(guideRenderWindow(605, 500f, 5f), guideRenderWindow(620, 500f, 5f))
    }

    @Test fun densityDoesNotChangeVisibleProgrammes() {
        assertEquals(guideRenderWindow(600, 500f, 5f), guideRenderWindow(1_200, 1_000f, 10f))
    }

    @Test fun startAndUnmeasuredViewportAreBounded() {
        assertEquals(GuideRenderWindow(0, 30), guideRenderWindow(-10, 0f, 5f))
        assertTrue(guideRenderWindow(0, 500f, 0f).intersects(100, 130))
    }
}
