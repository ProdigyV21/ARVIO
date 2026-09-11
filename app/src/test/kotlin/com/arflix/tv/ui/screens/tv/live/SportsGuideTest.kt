package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import java.time.Instant
import java.time.ZoneId
import org.junit.Assert.*
import org.junit.Test

class SportsGuideTest {
    @Test fun sportsChannelDoesNotTurnDowntimeOrDramaIntoEvents() {
        val resolver = SportsProgrammeResolver()
        for (title in listOf("Sendepause", "Die Aquarium-Profis", "Murder Under the Friday Night Lights", "Familien Green i storby'n", "Best of NBA Action")) {
            assertNull(title, resolver.resolve(IptvProgram(title, startUtcMillis = 1, endUtcMillis = 2,
                description = "A family talks about football and cricket"), GuideSport.FOOTBALL))
        }
    }
    @Test fun upcomingFilterUsesCalendarDaysAcrossDstWithoutEmptyRows() {
        val zone = ZoneId.of("Europe/Amsterdam")
        val clock = Instant.parse("2026-10-24T22:30:00Z").toEpochMilli()
        val lateToday = Instant.parse("2026-10-25T22:30:00Z").toEpochMilli()
        val tomorrow = Instant.parse("2026-10-25T23:30:00Z").toEpochMilli()
        assertTrue(SportsDay.TODAY.includes(lateToday, clock, zone))
        assertFalse(SportsDay.TODAY.includes(tomorrow, clock, zone))
        assertTrue(SportsDay.TOMORROW.includes(tomorrow, clock, zone))
        val event = SportsGuideEvent("future", "Football", GuideSport.FOOTBALL,
            IptvProgram("Football", startUtcMillis = tomorrow, endUtcMillis = tomorrow + 60_000), emptyList())
        val rows = sportsGuideRows(listOf(event), clock, SportsDay.TODAY, zone)
        assertTrue(rows.isEmpty())
    }
    private val now = Instant.parse("2026-09-09T18:00:00Z").toEpochMilli()
    private val a = IptvChannel("one:1", "Football 1", streamUrl = "https://example.invalid/a", group = "Football")
    private val b = a.copy(id = "two:1", streamUrl = "https://example.invalid/b")
    private fun programme(title: String = "Football: North vs South", start: Long = now - 60_000, end: Long = now + 60_000) =
        IptvProgram(title, startUtcMillis = start, endUtcMillis = end)
    private fun slice(p: IptvProgram) = IptvNowNext(now = p, next = p, upcoming = listOf(p))

    @Test fun oneEventRetainsEachProvidersChannelWithoutDuplicates() {
        val p = programme()
        val events = buildSportsGuideEvents(listOf(a, b), mapOf(a.id to slice(p), b.id to slice(p)), now)
        assertEquals(1, events.size)
        assertEquals(listOf(a.id, b.id), events.single().channels.map { it.id })
    }
    @Test fun paddedProviderSchedulesMatchButOnlyOnAirSourcesArePlayable() {
        val p = programme("Premier League: North versus South", now - 60_000, now + 7_200_000)
        val q = programme("LIVE: South v North [HD]", now + 60_000, now + 7_320_000)
        val event = buildSportsGuideEvents(listOf(a, b), mapOf(a.id to slice(p), b.id to slice(q)), now).single()
        assertEquals(2, event.channels.size)
        assertEquals(listOf(a.id), event.availableChannels(now).map { it.id })
        assertEquals(2, event.availableChannels(now + 120_000).size)
        assertEquals("Premier League", event.competition)
    }
    @Test fun qualifiersCancellationAndGeneralChannelMetadataArePreserved() {
        val p = programme("North vs South").copy(category = "Football", artworkUrl = "https://example.com/event.webp")
        val channel = a.copy(name = "National One", group = "General")
        val event = buildSportsGuideEvents(listOf(channel), mapOf(channel.id to slice(p)), now).single()
        assertEquals(p.artworkUrl, attachSportsArtwork(listOf(event), emptyList()).single().artwork)
        for (title in listOf("North vs South postponed", "North vs South cancelled")) {
            assertTrue(buildSportsGuideEvents(listOf(a), mapOf(a.id to slice(p.copy(title = title))), now).isEmpty())
        }
        assertEquals(2, buildSportsGuideEvents(listOf(a, b), mapOf(a.id to slice(p), b.id to slice(p.copy(title = "North Women vs South Women"))), now).size)
    }
    @Test fun refreshDoesNotReorderExistingCardsAndRevokedSourcesAreRemoved() {
        val p = programme()
        val one = buildSportsGuideEvents(listOf(a), mapOf(a.id to slice(p)), now).single()
        val two = one.copy(id = "other", title = "Another event")
        assertEquals(listOf(one.id, two.id), retainSportsEventOrder(listOf(one, two), listOf(two, one)).map { it.id })
        assertEquals(listOf(two.id), retainSportsEventOrder(listOf(one, two), listOf(two)).map { it.id })
    }
    @Test fun similarTeamsAtAnotherTimeDoNotMatch() {
        val events = buildSportsGuideEvents(listOf(a, b), mapOf(a.id to slice(programme()),
            b.id to slice(programme(start = now + 60_000, end = now + 120_000))), now)
        assertEquals(2, events.size)
    }
    @Test fun hiddenChannelNotInInputCannotLeakFromGuideMap() {
        val p = programme()
        assertEquals(listOf(a.id), buildSportsGuideEvents(listOf(a), mapOf(a.id to slice(p), b.id to slice(p)), now)
            .single().channels.map { it.id })
    }
    @Test fun expiredReplaysAndInvalidIntervalsAreExcluded() {
        for (p in listOf(programme(end = now), programme(start = now + 1, end = now),
            programme("Football highlights"), programme("Football replay"), programme("Football preview"))) {
            assertTrue(buildSportsGuideEvents(listOf(a), mapOf(a.id to slice(p)), now).isEmpty())
        }
    }
    @Test fun footballCodesAndCombatSportsStaySeparate() {
        assertEquals(GuideSport.AMERICAN_FOOTBALL, GuideSport.fromText("NFL American football"))
        assertEquals(GuideSport.FOOTBALL, GuideSport.fromText("UEFA Champions League"))
        assertEquals(GuideSport.BOXING, GuideSport.fromText("Boxing"))
        assertEquals(GuideSport.MMA, GuideSport.fromText("UFC 310"))
        assertNull(GuideSport.fromText("Generic event"))
    }
    @Test fun midnightWindowUsesDeviceZoneNotUtcAndDoesNotInventLiveStatus() {
        val start = Instant.parse("2026-09-11T01:00:00Z").toEpochMilli()
        val p = programme(start = start, end = start + 60_000)
        val guide = mapOf(a.id to slice(p))
        assertTrue(buildSportsGuideEvents(listOf(a), guide, now, ZoneId.of("Europe/Amsterdam")).isEmpty())
        val la = buildSportsGuideEvents(listOf(a), guide, now, ZoneId.of("America/Los_Angeles"))
        assertEquals(1, la.size)
        assertFalse(la.single().isOnAir(now))
    }
    @Test fun featuredIsCappedAndSportRowKeepsAllEvents() {
        val programmes = (0..11).map { programme("Football: Team $it vs Other") }
        val events = buildSportsGuideEvents(listOf(a), mapOf(a.id to IptvNowNext(upcoming = programmes)), now)
        val rows = sportsGuideRows(events, now)
        val featured = rows.single { it.id == "featured" }.events.map { it.id }.toSet()
        assertEquals(8, featured.size)
        assertEquals(12, rows.single { it.id == "FOOTBALL" }.events.size)
        assertFalse(rows.any { it.id == "more" })
        assertFalse(rows.any { it.id == "upcoming" })
    }
}
