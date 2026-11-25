package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Test

class SmoothnessPolicyTest {
    @After fun reset() = SportsPresentationCache.clear()

    @Test fun clockTicksJustAfterEachMinuteBoundary() {
        val minute = 60_000L * 30_000_000L
        assertEquals(60_050L, liveClockDelayToNextMinute(minute))
        assertEquals(30_050L, liveClockDelayToNextMinute(minute + 30_000L))
        // Never a busy loop just before a boundary.
        assertEquals(1_000L, liveClockDelayToNextMinute(minute + 59_999L))
    }

    @Test fun sportsRowsAreReusedOnlyForTheSameEvents() {
        val now = 1_800_000_000_000L
        val channel = IptvChannel("provider:1", "Sport One", "https://example.com/live.m3u8", "Sport")
        fun event(id: String, start: Long = now) = SportsGuideEvent(id, "North vs South $id", GuideSport.FOOTBALL,
            IptvProgram("North vs South", startUtcMillis = start, endUtcMillis = start + 7_200_000L), listOf(channel))
        val events = listOf(event("a"), event("b"))
        val rows = listOf(SportsGuideRow("live", "Live", events))
        assertNull(SportsPresentationCache.rowsFor(events))
        SportsPresentationCache.store(events, rows)
        assertSame(rows, SportsPresentationCache.rowsFor(listOf(event("a"), event("b"))))
        assertNull(SportsPresentationCache.rowsFor(listOf(event("a"))))
        assertNull(SportsPresentationCache.rowsFor(listOf(event("a"), event("b", start = now + 60_000L))))
        assertNull(SportsPresentationCache.rowsFor(emptyList()))
    }
}
