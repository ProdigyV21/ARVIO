package com.arflix.tv.ui.screens.player

import org.junit.Assert.assertEquals
import org.junit.Test

class SubtitlePlacementPrefsTest {
    @Test
    fun playerPercentWinsWhileItMatchesTheSettingsValue() {
        assertEquals(110, SubtitlePlacementPrefs.resolveSizePct("Medium", 110))
        assertEquals(10, SubtitlePlacementPrefs.resolveVerticalPct("Low", 10))
    }

    @Test
    fun settingsChangeOverridesAnOlderPlayerPercent() {
        // Player set 140 % (Extra Large); Settings then switched to Small.
        assertEquals(80, SubtitlePlacementPrefs.resolveSizePct("Small", 140))
        // Player set 30 % (High); Settings/cloud then chose Bottom.
        assertEquals(2, SubtitlePlacementPrefs.resolveVerticalPct("Bottom", 30))
    }

    @Test
    fun missingPercentUsesTheNamedDefault() {
        assertEquals(120, SubtitlePlacementPrefs.resolveSizePct("Large", null))
        assertEquals(15, SubtitlePlacementPrefs.resolveVerticalPct("Medium", null))
        assertEquals(100, SubtitlePlacementPrefs.resolveSizePct("weird", null))
    }
}
