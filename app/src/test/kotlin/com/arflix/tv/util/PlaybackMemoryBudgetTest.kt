package com.arflix.tv.util

import org.junit.Assert.*
import org.junit.Test

class PlaybackMemoryBudgetTest {
    @Test fun combinedHeapBudgetLeavesUiHeadroom() {
        for (heap in listOf(64, 128, 192, 256, 384, 512, 768, 1024)) {
            for (physical in listOf(1024L, 2048L, 4096L, 8192L)) {
                val budget = playbackMemoryBudget(heap, physical, false)
                val reserve = maxOf(32, heap / 4) * 1024 * 1024
                assertTrue(budget.maxPlaybackBytes + budget.artworkBytes + budget.seekPreviewBytes + reserve <= heap * 1024 * 1024)
                assertTrue(budget.trailerBytes <= budget.maxPlaybackBytes)
            }
        }
    }
    @Test fun largeHeapDoesNotMisclassifyTwoGbBoxes() {
        val small = playbackMemoryBudget(512, 2048, false)
        assertTrue(small.lowPhysicalRam)
        assertEquals(96 * 1024 * 1024, small.maxPlaybackBytes)
        assertEquals(6 * 1024 * 1024, small.seekPreviewBytes)
        assertEquals(24 * 1024 * 1024, small.artworkBytes)
    }
    @Test fun roomyDevicesKeepFullArtworkCache() {
        val budget = playbackMemoryBudget(512, 8192, false)
        assertFalse(budget.lowPhysicalRam)
        assertEquals(64 * 1024 * 1024, budget.artworkBytes)
        assertEquals(16 * 1024 * 1024, budget.trailerBytes)
    }
    @Test fun lowRamFlagAndUnknownPhysicalRamAreHandled() {
        assertTrue(playbackMemoryBudget(512, null, true).lowPhysicalRam)
        assertFalse(playbackMemoryBudget(512, null, false).lowPhysicalRam)
    }
}
