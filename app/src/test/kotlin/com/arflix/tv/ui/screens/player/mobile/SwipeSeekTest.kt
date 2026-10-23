package com.arflix.tv.ui.screens.player.mobile

import org.junit.Assert.assertEquals
import org.junit.Test

class SwipeSeekTest {
    @Test fun fullWidthIsTwoMinutesForShortVideos() {
        assertEquals(120_000L, swipeSeekDeltaMs(1000f, 1000f, 30 * 60_000L))
        assertEquals(-60_000L, swipeSeekDeltaMs(-500f, 1000f, 30 * 60_000L))
    }

    @Test fun fullWidthIsThreeMinutesForLongVideos() {
        assertEquals(180_000L, swipeSeekDeltaMs(1000f, 1000f, 2 * 60 * 60_000L))
    }

    @Test fun zeroWidthDoesNotDivideByZero() {
        assertEquals(120_000L, swipeSeekDeltaMs(1f, 0f, 30 * 60_000L))
    }

    @Test fun formatsTimes() {
        assertEquals("0:00", formatSwipeSeekTime(-5_000L))
        assertEquals("1:05", formatSwipeSeekTime(65_000L))
        assertEquals("1:01:05", formatSwipeSeekTime(3_665_000L))
    }
}
