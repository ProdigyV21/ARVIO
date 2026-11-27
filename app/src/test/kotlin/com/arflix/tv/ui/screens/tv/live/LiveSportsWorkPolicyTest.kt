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

    @Test fun offScreenRefreshWaitsForBrowsingToPause() {
        assertEquals(0L, LiveSportsWorkPolicy.rescanSettleMs(sportsOnScreen = true, hasSchedule = true))
        assertEquals(LiveSportsWorkPolicy.BACKGROUND_SETTLE_MS,
            LiveSportsWorkPolicy.rescanSettleMs(sportsOnScreen = false, hasSchedule = false))
        assertTrue(LiveSportsWorkPolicy.rescanSettleMs(sportsOnScreen = false, hasSchedule = true) >= 10_000L)
    }
}
