package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.parseSportsMetadata
import org.junit.Assert.*
import org.junit.Test

class SportsMetadataTest {
    @Test fun premiumBroadcasterCoverageIsNotTruncatedAtOneHundred() {
        val broadcasters = (1..350).joinToString(",") { """{"name":"Sports $it","country":"Netherlands","startsAt":1789048800000}""" }
        val json = """{"version":1,"catalogueEnabled":true,"events":[{"id":"42","title":"North vs South","sport":"Soccer","startsAt":1789048800000,"broadcasters":[$broadcasters]}]}"""
        assertEquals(350, parseSportsMetadata(json).single().fixture!!.broadcasters.size)
    }
    private val start = 1_789_048_800_000L
    private val payload = """{"version":1,"events":[{"title":"Barcelona vs Feyenoord","sport":"Soccer","startsAt":$start,
        "homeBadge":"https://example.com/home.png","awayBadge":"https://example.com/away.png",
        "homeTeam":"Barcelona","awayTeam":"Feyenoord","background":null}]}"""

    @Test fun pairedCrestsMatchActualProgrammeWithoutChangingTimingOrChannels() {
        val art = parseSportsMetadata(payload)
        val event = SportsGuideEvent("event", "Football: Feyenoord - Barcelona", GuideSport.FOOTBALL,
            IptvProgram("Football", startUtcMillis = start, endUtcMillis = start + 7_200_000), emptyList())
        val result = attachSportsArtwork(listOf(event), art).single()
        assertNull(result.artwork)
        assertEquals("https://example.com/home.png", result.teamArtwork?.homeBadge)
        assertEquals(event.programme, result.programme)
        for (changed in listOf(event.copy(title = "Champions League"), event.copy(sport = GuideSport.BASKETBALL),
            event.copy(title = "Barcelona Women vs Feyenoord Women"),
            event.copy(programme = event.programme.copy(startUtcMillis = start + 86_400_000)))) {
            assertNull(attachSportsArtwork(listOf(changed), art).single().teamArtwork)
        }
    }
    @Test fun unsafeOrIncompletePairsCannotBecomeCards() {
        assertTrue(parseSportsMetadata(payload.replace("https://example.com/away.png", "file:///secret")).isEmpty())
        assertTrue(parseSportsMetadata(payload.replace("\"startsAt\":$start", "\"startsAt\":null")).isEmpty())
        assertTrue(parseSportsMetadata("not json").isEmpty())
    }
}
