package com.arflix.tv.ui.screens.player.preview

import org.junit.Assert.*
import org.junit.Test

class SeekPreviewWindowTest {
    @Test fun nativePreparationKeepsFourSecondReserveAndCooldown() {
        val guard = SeekPreviewPlaybackGuard()
        fun sample(time: Long, buffered: Long = 5_000, minimum: Long = 4_000) =
            guard.mayWarm(time, true, true, buffered, false, true, true, minimum)
        assertFalse(sample(0))
        assertTrue(sample(8_000))
        assertFalse(sample(8_250, 3_999))
        assertFalse(sample(9_000, 3_999, 0))
        assertFalse(sample(10_000))
        assertTrue(sample(18_000))
        guard.onPlaybackStrain(18_000)
        guard.onPlaybackInterrupted()
        assertFalse(sample(47_999))
        assertFalse(sample(48_000))
        assertTrue(sample(56_000))
        guard.onPlaybackInterrupted()
        assertFalse(sample(56_001))
        assertTrue(sample(64_001))
    }
    @Test fun neighborsPrioritizeBothForwardSlotsAndRespectEdges() {
        assertEquals(listOf(70_000L, 80_000L, 50_000L, 40_000L), seekPreviewNeighborTargets(60_000, 120_000))
        assertEquals(listOf(10_000L, 20_000L), seekPreviewNeighborTargets(0, 120_000))
        assertEquals(listOf(50_000L, 40_000L, 70_000L, 80_000L), seekPreviewNeighborTargets(60_000, 120_000, -1))
        assertTrue(seekPreviewNeighborTargets(0, 0).isEmpty())
    }

    @Test fun windowsStayBoundedAtBothEndsAndNeverScanTheMovie() {
        assertEquals(listOf(0L, 10_000L, 20_000L), seekPreviewWindow(0, 7_200_000))
        assertEquals(listOf(79_999L, 89_999L, 99_999L), seekPreviewWindow(100_000, 100_000))
        assertEquals(listOf(10_000L, 20_000L, 30_000L, 40_000L, 0L), seekPreviewWarmTargets(7_000, 7_200_000))
        assertEquals(listOf(0L), seekPreviewWindow(0, 1))
        assertTrue(seekPreviewWindow(0, 0).isEmpty())
    }

    @Test fun warmingRequiresEightSecondsOfHealthyPlaybackAndResetsWhenBufferFalls() {
        val guard = SeekPreviewPlaybackGuard()
        fun sample(time: Long, buffered: Long = 9_000, browsing: Boolean = false,
            foreground: Boolean = true, safe: Boolean = true, ready: Boolean = true, playing: Boolean = true) =
            guard.mayWarm(time, playing, ready, buffered, browsing, foreground, safe)
        assertFalse(sample(0))
        assertFalse(sample(7_999))
        assertTrue(sample(8_000))
        assertFalse(sample(8_250, buffered = 7_999))
        assertFalse(sample(9_000))
        assertTrue(sample(17_000))
        assertFalse(sample(17_250, browsing = true))
        assertFalse(sample(26_000, foreground = false))
        assertFalse(sample(35_000, safe = false))
        assertFalse(sample(44_000, ready = false))
        assertFalse(sample(53_000, playing = false))
        assertFalse(sample(62_000))
        assertTrue(sample(70_000))
        guard.onPlaybackStrain(70_000)
        assertFalse(sample(99_999))
        assertFalse(sample(100_000))
        assertTrue(sample(108_000))
    }
}
