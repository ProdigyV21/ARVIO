package com.arflix.tv.ui.screens.tv.live

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LiveSportsWorkPolicyTest {
    @Test fun sportsOnScreenStartsImmediately() {
        assertEquals(0L, LiveSportsWorkPolicy.settleMs(sportsOnScreen = true))
    }

    @Test fun backgroundWorkWaitsUntilZappingSettles() {
        // Longer than a typical zap interval, so a run of zaps triggers one scan, not one per zap.
        assertTrue(LiveSportsWorkPolicy.settleMs(sportsOnScreen = false) >= 2_500L)
    }
}
