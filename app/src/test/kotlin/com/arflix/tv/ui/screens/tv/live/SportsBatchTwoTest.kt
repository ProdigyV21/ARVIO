package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsEventArtwork
import com.arflix.tv.data.model.SportsFixture
import com.arflix.tv.data.model.SportsFollow
import com.arflix.tv.data.model.SportsStandingRow
import com.arflix.tv.data.model.aroundHighlighted
import com.arflix.tv.data.model.parseSportsGameDetails
import com.arflix.tv.data.repository.SPORTS_CHANNEL_MEMORY_LIMIT
import com.arflix.tv.data.repository.withRemembered
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.ZoneOffset

class SportsBatchTwoTest {
    private val now = 1_790_000_000_000L
    private fun channel(id: String, name: String = id) = IptvChannel(id, name, "http://x/$id", "Sports")

    @Test fun gameDetailsParseAndDropMalformedParts() {
        val details = parseSportsGameDetails("""
            {"version":1,"status":"live","periods":[{"label":"Q1","home":30,"away":28},{"label":"Q2","home":"x","away":1}],
             "standingsTitle":"East","standings":[{"rank":1,"team":"Knicks","played":16,"won":12,"highlight":true},{"rank":2}],
             "form":{"home":["W","L","X"],"away":[]},
             "headToHead":[{"date":"2026-11-01","homeTeam":"A","awayTeam":"B","homeScore":1,"awayScore":2},{"date":"bad","homeTeam":"A","awayTeam":"B","homeScore":1,"awayScore":2}]}
        """.trimIndent())!!
        assertEquals(1, details.periods.size)
        assertEquals(listOf("Knicks"), details.standings.map { it.team })
        assertEquals(listOf("W", "L"), details.homeForm)
        assertEquals(1, details.headToHead.size)
        assertNull(parseSportsGameDetails("{\"version\":2}"))
        assertNull(parseSportsGameDetails("not json"))
    }

    @Test fun longTablesShowTheRowsAroundBothTeams() {
        val rows = (1..20).map { SportsStandingRow(it, "T$it", null, null, null, null, null, null, highlight = it == 10 || it == 18) }
        val shown = rows.aroundHighlighted(8).map { it.rank }
        assertTrue(shown.containsAll(listOf(9, 10, 11, 17, 18, 19)))
        assertEquals(8, shown.size)
        assertEquals(shown.sorted(), shown)
    }

    @Test fun networksAreFoundByNameWithOrWithoutCountryPrefix() {
        assertEquals("nba-tv", sportsNetworkFor(sportsChannelKey("US: NBA TV HD"))?.id)
        assertEquals("premier-league", sportsNetworkFor(sportsChannelKey("UK | Sky Sports Premier League FHD"))?.id)
        assertEquals("espn", sportsNetworkFor(sportsChannelKey("ESPN 2"))?.id)
        assertNull(sportsNetworkFor(sportsChannelKey("ESPNews Weekly Magazine")))
        assertNull(sportsNetworkFor(sportsChannelKey("BBC One")))
    }

    @Test fun networkTilesAreChannelOnlyAndFollowedSportsComeFirst() {
        val tiles = sportsNetworkEvents(mapOf("espn" to listOf(channel("e1")), "nba-tv" to List(9) { channel("n$it") }),
            listOf(SportsFollow(SportsFollow.KIND_TEAM, "Boston Celtics", "basketball")), now, ZoneOffset.UTC)
        assertEquals(listOf("network:nba-tv", "network:espn"), tiles.map { it.id })
        assertEquals(SPORTS_NETWORK_CHANNELS, tiles[0].channels.size)
        assertTrue(tiles.all { it.channelOnly && it.isNetwork && it.isOnAir(now) })
        assertEquals(listOf("network:nba-tv"), tiles[0].channelMemoryKeys())
    }

    @Test fun rememberedChannelComesFirstPerCompetition() {
        val event = SportsGuideEvent("e", "A vs B", GuideSport.FOOTBALL, IptvProgram("A vs B", startUtcMillis = now, endUtcMillis = now), emptyList(),
            competition = "English Premier League")
        val keys = event.channelMemoryKeys()
        assertEquals(listOf("league:premier league"), keys)
        val channels = listOf(channel("a"), channel("b"), channel("c"))
        val memory = emptyMap<String, String>().withRemembered(keys, "c")
        assertEquals(listOf("c", "a", "b"), channels.withRememberedFirst(memory, keys).map { it.id })
        assertEquals(listOf("a", "b", "c"), channels.withRememberedFirst(emptyMap(), keys).map { it.id })
        val full = (1..100).fold(emptyMap<String, String>()) { m, i -> m.withRemembered(listOf("league:$i"), "x$i") }
        assertEquals(SPORTS_CHANNEL_MEMORY_LIMIT, full.size)
        assertEquals("x100", full["league:100"])
    }

    @Test fun failoverTriesEveryOtherChannelOnceThenStops() {
        var failover = SportsFailover("e", "A vs B", listOf(channel("a"), channel("b"), channel("c")))
        val (first, afterA) = failover.next("b")!!
        assertEquals("c", first.id)
        failover = afterA
        val (second, afterC) = failover.next("c")!!
        assertEquals("a", second.id)
        assertNull(afterC.next("a"))
        assertTrue(failover.covers("a"))
    }

    private fun scores(id: String, home: Int?, away: Int?, status: String, sport: String = "Soccer") =
        SportsEventArtwork("Ajax vs PSV", "", listOf(sport), now - 3_600_000L, homeTeam = "Ajax", awayTeam = "PSV",
            fixture = SportsFixture(id, "Eredivisie", null, null, null, status, now, home, away, emptyList()))

    @Test fun scoreAlertsForGoalsAndFullTimeOnlyAfterABaseline() {
        val follows = listOf(SportsFollow(SportsFollow.KIND_TEAM, "AFC Ajax"))
        val first = followedScores(listOf(scores("1", 0, 0, "live"), scores("2", null, null, "scheduled").copy(homeTeam = "Feyenoord", awayTeam = "AZ")), follows)
        assertEquals(setOf("1"), first.keys)
        assertTrue(detectScoreAlerts(null, first, null).isEmpty())
        val goal = followedScores(listOf(scores("1", 1, 0, "live")), follows)
        val alerts = detectScoreAlerts(first.mapValues { it.value.second }, goal, null)
        assertEquals(1, alerts.size)
        assertEquals(1, alerts[0].homeScore)
        assertTrue(detectScoreAlerts(first.mapValues { it.value.second }, goal, watchingEventId = "1").isEmpty())
        val end = followedScores(listOf(scores("1", 1, 0, "finished")), follows)
        assertTrue(detectScoreAlerts(goal.mapValues { it.value.second }, end, null).single().final)
        assertTrue(detectScoreAlerts(end.mapValues { it.value.second }, end, null).isEmpty())
    }

    @Test fun basketballOnlyAlertsAtTheEnd() {
        val follows = listOf(SportsFollow(SportsFollow.KIND_TEAM, "Ajax"))
        val before = followedScores(listOf(scores("1", 50, 48, "live", "Basketball")), follows)
        val after = followedScores(listOf(scores("1", 52, 48, "live", "Basketball")), follows)
        assertTrue(detectScoreAlerts(before.mapValues { it.value.second }, after, null).isEmpty())
        assertTrue(followedScores(listOf(scores("1", 1, 0, "live")), follows).anyActive(now))
    }
}
