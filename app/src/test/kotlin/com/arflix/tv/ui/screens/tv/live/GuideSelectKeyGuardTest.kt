package com.arflix.tv.ui.screens.tv.live

import org.junit.Assert.*
import org.junit.Test

class GuideSelectKeyGuardTest {
    private fun openedByHold(): GuideSelectKeyGuard = GuideSelectKeyGuard().apply {
        assertFalse(consume(100, 100, true, 0))
        assertFalse(consume(100, 500, true, 1))
        blockCurrentPress(includeSyntheticBurst = true)
    }

    @Test fun firstDeliberateMenuClickAfterReleaseIsAccepted() {
        val guard = openedByHold()
        assertTrue(guard.consume(100, 550, false, 0))
        // The old 250ms event-gap guard discarded this complete click.
        assertFalse(guard.consume(700, 700, true, 0))
        guard.blockCurrentPress(includeSyntheticBurst = true)
        assertTrue(guard.consume(700, 720, false, 0))
        assertFalse(guard.consume(850, 850, true, 0))
    }

    @Test fun sameHeldGestureNeverTriggersActionEvenAfterLongEventGap() {
        val guard = openedByHold()
        assertTrue(guard.consume(100, 1_200, true, 2))
        assertTrue(guard.consume(100, 1_800, false, 0))
    }

    @Test fun syntheticPressPairsDuringHoldDoNotSelectMenuOrTuneChannel() {
        val guard = openedByHold()
        assertTrue(guard.consume(100, 550, false, 0))
        repeat(20) { index ->
            val down = 580L + index * 33L
            assertTrue(guard.consume(down, down, true, 0))
            assertTrue(guard.consume(down, down + 10, false, 0))
        }
        assertFalse(guard.consume(1_400, 1_400, true, 0))
    }

    @Test fun missingReleaseDoesNotEatNextIndependentClick() {
        val guard = openedByHold()
        assertFalse(guard.consume(700, 700, true, 0))
    }

    @Test fun menuActionReleaseCannotReachUnderlyingChannel() {
        val guard = GuideSelectKeyGuard()
        assertFalse(guard.consume(100, 100, true, 0))
        guard.blockCurrentPress(includeSyntheticBurst = false)
        assertTrue(guard.consume(100, 300, true, 1))
        assertTrue(guard.consume(100, 320, false, 0))
        assertFalse(guard.consume(400, 400, true, 0))
    }

    @Test fun holdingMenuActionCannotSendSyntheticClicksToTheChannel() {
        val guard = GuideSelectKeyGuard()
        assertFalse(guard.consume(100, 100, true, 0))
        guard.blockCurrentPress(includeSyntheticBurst = true)
        assertTrue(guard.consume(100, 600, false, 0))
        repeat(10) { index ->
            val down = 633L + index * 33L
            assertTrue(guard.consume(down, down, true, 0))
            assertTrue(guard.consume(down, down + 10, false, 0))
        }
        assertFalse(guard.consume(1_100, 1_100, true, 0))
    }
}
