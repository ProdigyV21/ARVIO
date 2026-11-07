package com.arflix.tv.ui.screens.player

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PlaybackBufferProfileTest {
    @Test
    fun `byte limited devices reserve allocator capacity for forward playback`() {
        for (memoryMb in listOf(128, 256, 384, 512, 768, 1024)) {
            for (lowRam in listOf(true, false)) {
                val profile = buildPlaybackBufferProfile(memoryMb, lowRam, isTvDevice = true)
                if (!profile.prioritizeTimeOverSizeThresholds) {
                    val previousLimit = when {
                        lowRam || memoryMb <= 256 -> 2_000
                        memoryMb <= 384 -> 3_000
                        else -> 5_000
                    }
                    assertEquals(previousLimit, profile.backBufferMs)
                    assertTrue(profile.targetBufferBytes > 0)
                }
            }
        }
    }

    @Test
    fun `time prioritized devices keep a twenty second rewind in memory`() {
        val profile = buildPlaybackBufferProfile(1024, isLowRamDevice = false, isTvDevice = true)
        assertTrue(profile.prioritizeTimeOverSizeThresholds)
        assertEquals(20_000, profile.backBufferMs)
    }
}
