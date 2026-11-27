package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LivePlayerControlsTest {
    @Test
    fun `catch-up and replayable programmes are seekable`() {
        assertTrue(livePlayerSeekable(catchup = true, canReplayNow = false, playerSeekable = false, playerDurationMs = 0L))
        assertTrue(livePlayerSeekable(catchup = false, canReplayNow = true, playerSeekable = false, playerDurationMs = 0L))
    }

    @Test
    fun `plain live stream without a window is not seekable`() {
        assertFalse(livePlayerSeekable(false, false, playerSeekable = false, playerDurationMs = 3_600_000L))
        assertFalse(livePlayerSeekable(false, false, playerSeekable = true, playerDurationMs = androidx.media3.common.C.TIME_UNSET))
        assertFalse(livePlayerSeekable(false, false, playerSeekable = true, playerDurationMs = 12_000L))
    }

    @Test
    fun `stream with a long live window is seekable`() {
        assertTrue(livePlayerSeekable(false, false, playerSeekable = true, playerDurationMs = 120_000L))
    }

    @Test
    fun `minutes left rounds up and is null outside the programme`() {
        val p = IptvProgram(title = "News", startUtcMillis = 0L, endUtcMillis = 3_600_000L)
        assertEquals(60L, playerMinutesLeft(p, 0L))
        assertEquals(1L, playerMinutesLeft(p, 3_600_000L - 1_000L))
        assertNull(playerMinutesLeft(p, 3_600_001L))
    }
}
