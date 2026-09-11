package com.arflix.tv.data.repository

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvGuideHistory
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.*
import org.junit.Test

class IptvGuideHistoryTest {
    private val channel = IptvChannel("one:1", "News", "https://example.invalid/live/one.ts", "News", catchupDays = 3)
    private val now = 1_800_000_000_000L

    @Test
    fun historyUsesAdvertisedProviderWindowWithBoundedFallback() {
        assertEquals(3, IptvGuideHistory.days(channel))
        assertEquals(1, IptvGuideHistory.days(channel.copy(catchupDays = 1)))
        assertEquals(7, IptvGuideHistory.days(channel.copy(catchupDays = 30)))
        assertEquals(3, IptvGuideHistory.days(channel.copy(catchupDays = 0)))
    }

    @Test
    fun manyShortProgrammesDoNotMasqueradeAsFullHistory() {
        val short = IptvNowNext(recent = List(24) { i -> IptvProgram("Show $i",
            startUtcMillis = now - (24 - i) * 5 * 60_000L, endUtcMillis = now - (23 - i) * 5 * 60_000L) })
        assertFalse(IptvGuideHistory.hasCoverage(short, 3 * IptvGuideHistory.DAY_MS, now))
        assertFalse(IptvGuideHistory.hasCoverage(short.copy(recent = short.recent.takeLast(6)), 3 * IptvGuideHistory.DAY_MS, now))
    }

    @Test fun providerUnavailableOverridesChannelArchiveSupport() {
        val aired = IptvProgram("Aired", startUtcMillis = now - 60_000, endUtcMillis = now - 1, catchupAvailable = false)
        assertFalse(IptvGuideHistory.canReplay(channel, aired, now))
    }

    @Test fun unknownAvailabilityUsesFullThreeDayWindowRatherThanTwoDays() {
        val aired = IptvProgram("Aired", startUtcMillis = now - 60 * 3_600_000L, endUtcMillis = now - 59 * 3_600_000L)
        assertTrue(IptvGuideHistory.canReplay(channel, aired, now))
        assertFalse(IptvGuideHistory.canReplay(channel, aired.copy(startUtcMillis = now - 4 * IptvGuideHistory.DAY_MS), now))
    }

    @Test fun explicitAvailabilityAllowsReplayButNotFutureProgrammes() {
        val aired = IptvProgram("Aired", startUtcMillis = now - 60_000, endUtcMillis = now - 1, catchupAvailable = true)
        assertTrue(IptvGuideHistory.canReplay(null, aired, now))
        assertFalse(IptvGuideHistory.canReplay(channel, aired.copy(startUtcMillis = now + 1, endUtcMillis = now + 60_000), now))
    }

    @Test fun shortRefreshRetainsThreeDaysAndAcceptsCorrectedArchiveAvailability() {
        val programmes = List(72) { i -> IptvProgram("Show $i",
            startUtcMillis = now - (72 - i) * 3_600_000L, endUtcMillis = now - (71 - i) * 3_600_000L) }
        val refreshed = programmes.takeLast(6).map { it.copy(catchupAvailable = false) }
        val merged = IptvGuideHistory.mergeSchedules(IptvNowNext(recent = programmes), IptvNowNext(recent = refreshed))
        assertEquals(72, merged.recent.size)
        assertTrue(IptvGuideHistory.hasCoverage(merged, 3 * IptvGuideHistory.DAY_MS, now))
        assertTrue(merged.recent.takeLast(6).all { it.catchupAvailable == false })
        val xmlRefresh = IptvGuideHistory.mergeSchedules(merged, IptvNowNext(recent = programmes.takeLast(6)))
        assertTrue(xmlRefresh.recent.takeLast(6).all { it.catchupAvailable == false })
    }

    @Test fun mergedHistoryRemainsMemoryBounded() {
        val programmes = List(1_500) { i -> IptvProgram("Show $i", startUtcMillis = i.toLong(), endUtcMillis = i + 1L) }
        val merged = IptvGuideHistory.mergeSchedules(IptvNowNext(recent = programmes.take(900)), IptvNowNext(recent = programmes.drop(900)))
        assertEquals(IptvGuideHistory.MAX_PROGRAMS, merged.recent.size)
        assertEquals(programmes.last(), merged.recent.last())
    }

    @Test
    fun threeDaysOfHistorySatisfiesThreeDayProviderButNotSevenDayProvider() {
        val guide = IptvNowNext(recent = listOf(IptvProgram("Older show",
            startUtcMillis = now - 3 * IptvGuideHistory.DAY_MS, endUtcMillis = now - 3 * IptvGuideHistory.DAY_MS + 60_000L)))
        assertTrue(IptvGuideHistory.hasCoverage(guide, 3 * IptvGuideHistory.DAY_MS, now))
        assertFalse(IptvGuideHistory.hasCoverage(guide, 7 * IptvGuideHistory.DAY_MS, now))
    }
}
