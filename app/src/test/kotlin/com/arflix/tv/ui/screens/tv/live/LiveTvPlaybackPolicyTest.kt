package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.C
import org.junit.Assert.*
import org.junit.Test

class LiveTvPlaybackPolicyTest {
    @Test fun providerControlsLiveOffsetInsteadOfAnEightSecondOverride() {
        val configuration = buildLiveTvConfiguration()
        assertEquals(C.TIME_UNSET, configuration.targetOffsetMs)
        assertEquals(1f, configuration.minPlaybackSpeed, 0f)
        assertEquals(1f, configuration.maxPlaybackSpeed, 0f)
    }

    @Test fun lowMemoryTvKeepsBoundedLiveBuffer() {
        val low = buildLiveTvBufferProfile(256, true)
        val normal = buildLiveTvBufferProfile(1024, false)
        assertTrue(low.targetBufferBytes <= 32 * 1024 * 1024)
        assertTrue(low.targetBufferBytes <= normal.targetBufferBytes)
        assertTrue(low.maxBufferMs <= 30_000)
        assertTrue(low.bufferForPlaybackAfterRebufferMs <= low.minBufferMs)
        assertTrue(low.minBufferMs <= low.maxBufferMs)
    }
}
