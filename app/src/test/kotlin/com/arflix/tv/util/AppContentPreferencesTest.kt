package com.arflix.tv.util

import org.junit.Assert.assertEquals
import org.junit.Test

class AppContentPreferencesTest {

    @Test
    fun `defaults target Swedish metadata and Swedish provider availability`() {
        assertEquals("sv-SE", AppContentPreferences.DEFAULT_LANGUAGE_TAG)
        assertEquals("SE", AppContentPreferences.DEFAULT_WATCH_REGION)
    }

    @Test
    fun `english metadata still keeps Swedish provider availability`() {
        assertEquals(null, AppContentPreferences.normalizeLanguageForTmdb("en-US"))
        assertEquals("SE", AppContentPreferences.normalizeWatchRegion(null))
    }

    @Test
    fun `normalizes language aliases and watch regions`() {
        assertEquals("sv-SE", AppContentPreferences.normalizeLanguageTag("sv_se"))
        assertEquals("he-IL", AppContentPreferences.normalizeLanguageTag("iw-IL"))
        assertEquals("SE", AppContentPreferences.normalizeWatchRegion(" se "))
    }
}
