package com.arflix.tv.ui.components

import org.junit.Assert.*
import org.junit.Test

class HomeTrailerPreviewTest {
    @Test fun fullyVisiblePlayerCanAutoplay() {
        assertTrue(canAutoplayHomeTrailer(480f, 270f, 480f * 270f))
    }
    @Test fun smallOrMostlyClippedPlayerCannotAutoplay() {
        assertFalse(canAutoplayHomeTrailer(355f, 199f, 355f * 199f))
        assertFalse(canAutoplayHomeTrailer(199f, 270f, 199f * 270f))
        assertFalse(canAutoplayHomeTrailer(480f, 270f, 480f * 135f))
        assertFalse(canAutoplayHomeTrailer(480f, 270f, 480f * 134f))
        assertFalse(canAutoplayHomeTrailer(480f, 270f, 0f))
    }
    @Test fun documentedMinimumSizeAndVisibilityAreAccepted() {
        assertTrue(canAutoplayHomeTrailer(200f, 200f, 200f * 101f))
    }
}
