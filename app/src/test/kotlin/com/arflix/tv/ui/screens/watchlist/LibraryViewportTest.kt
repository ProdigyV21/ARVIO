package com.arflix.tv.ui.screens.watchlist

import org.junit.Assert.assertEquals
import org.junit.Test

class LibraryViewportTest {
    @Test fun visibleRowsDoNotMove() {
        assertEquals(0, libraryRevealScrollDelta(60, 44, 0, 300))
        assertEquals(0, libraryRevealScrollDelta(256, 44, 0, 300))
    }
    @Test fun clippedBottomMovesOnlyEnoughToRevealTheRow() {
        assertEquals(24, libraryRevealScrollDelta(280, 44, 0, 300))
    }
    @Test fun clippedTopMovesBackWithoutAligningEverythingToTheTop() {
        assertEquals(-20, libraryRevealScrollDelta(-20, 44, 0, 300))
    }
    @Test fun contentPaddingIsRespected() {
        assertEquals(-2, libraryRevealScrollDelta(0, 44, 2, 298))
        assertEquals(2, libraryRevealScrollDelta(256, 44, 2, 298))
    }
    @Test fun oversizedItemUsesItsLeadingEdgeAndEmptyViewportDoesNotScroll() {
        assertEquals(40, libraryRevealScrollDelta(40, 320, 0, 300))
        assertEquals(0, libraryRevealScrollDelta(40, 44, 0, 0))
    }
}
