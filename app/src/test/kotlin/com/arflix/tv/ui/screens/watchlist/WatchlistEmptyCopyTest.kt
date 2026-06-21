package com.arflix.tv.ui.screens.watchlist

import org.junit.Assert.assertEquals
import org.junit.Test

class WatchlistEmptyCopyTest {

    @Test
    fun `empty watchlist body uses translatable app copy key`() {
        assertEquals("Your watchlist is empty", WATCHLIST_EMPTY_TITLE_COPY)
        assertEquals("Add movies and shows to watch later", WATCHLIST_EMPTY_BODY_COPY)
    }
}
