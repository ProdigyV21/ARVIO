package com.arflix.tv.ui.screens.details

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DetailsSeasonSelectionTest {
    @Test fun `linked specials are not automatically replaced by season one`() {
        assertEquals(0, detailSeasonForAutomaticSelection(0, 0, 0))
        assertTrue(detailInitialEpisodeSeasonMatches(0, 0, 0))
    }

    @Test fun `normal and manually selected seasons retain one based behavior`() {
        assertEquals(1, detailSeasonForAutomaticSelection(0, 1, 0))
        assertEquals(2, detailSeasonForAutomaticSelection(1, 0, 0))
        assertEquals(1, detailSeasonForAutomaticSelection(0, 2, null))
        assertTrue(detailInitialEpisodeSeasonMatches(2, 1, 2))
        assertFalse(detailInitialEpisodeSeasonMatches(3, 1, 2))
        assertFalse(detailInitialEpisodeSeasonMatches(0, 0, null))
    }
}
