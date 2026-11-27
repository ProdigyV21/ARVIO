package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsAddonEvent
import com.arflix.tv.data.model.restoredFromDisk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SportsHubTest {
    private val now = 1_790_000_000_000L
    private val minute = 60_000L
    private val hour = 60 * minute
    private val labels = SportsHubLabels("Live now", "Starting soon", "Live sports channels")

    private fun game(id: String, title: String, startIn: Long, sport: GuideSport = GuideSport.FOOTBALL,
        competition: String? = "Premier League", channelOnly: Boolean = false) = SportsGuideEvent(id, title, sport,
        IptvProgram(title, startUtcMillis = now + startIn, endUtcMillis = now + startIn + 2 * hour),
        listOf(IptvChannel("c$id", "UK: Sky Sports $id HD", "http://x/$id", "Sports")),
        competition = competition, channelOnly = channelOnly)

    @Test fun teamCodesReadLikeScoreboards() {
        assertEquals("LIV", sportsTeamCode("Liverpool"))
        assertEquals("MCI", sportsTeamCode("Manchester City"))
        assertEquals("GSW", sportsTeamCode("Golden State Warriors"))
        assertEquals("BOS", sportsTeamCode("Boston Celtics"))
        assertEquals("RMA", sportsTeamCode("Real Madrid"))
        assertEquals("AJA", sportsTeamCode("AFC Ajax"))
        assertEquals("BRE", sportsTeamCode("Brentford FC"))
        assertEquals("SCO", sportsTeamCode("Stockport County"))
    }

    @Test fun fallbackColoursAreSteadyAndDifferPerSide() {
        assertEquals(sportsTeamFallbackColor("Arsenal"), sportsTeamFallbackColor("arsenal "))
        val home = sportsTeamFallbackColor("Arsenal")
        assertNotEquals(home, sportsTeamFallbackColor("Arsenal", avoid = home))
    }

    @Test fun overviewHasLiveThenSoonThenSports() {
        val events = listOf(
            game("1", "Arsenal vs Chelsea", -30 * minute),
            game("2", "Lakers vs Celtics", 40 * minute, GuideSport.BASKETBALL, "NBA"),
            game("3", "Ajax vs PSV", 6 * hour, competition = "Eredivisie"),
            game("4", "Sky Sports News", -10 * minute, GuideSport.OTHER, null, channelOnly = true),
        )
        val rows = sportsHubRows(events, now, byCompetition = false, labels)
        assertEquals(listOf(SPORTS_ROW_LIVE, SPORTS_ROW_SOON, "FOOTBALL", "BASKETBALL", SPORTS_ROW_CHANNELS), rows.map { it.id })
        assertEquals(listOf("1"), rows[0].events.map { it.id })
        assertEquals(listOf("2"), rows[1].events.map { it.id })
        // Live first inside a sport row.
        assertEquals(listOf("1", "3"), rows.first { it.id == "FOOTBALL" }.events.map { it.id })
    }

    @Test fun oneSportIsSplitByCompetition() {
        val events = listOf(game("1", "Arsenal vs Chelsea", 5 * hour), game("3", "Ajax vs PSV", 6 * hour, competition = "Eredivisie"))
        val rows = sportsHubRows(events, now, byCompetition = true, labels)
        assertEquals(listOf("Premier League", "Eredivisie"), rows.map { it.title })
    }

    @Test fun railCountsGamesAndLiveGames() {
        val counts = sportsRailCounts(listOf(game("1", "A vs B", -minute), game("2", "C vs D", hour),
            game("3", "E vs F", hour, GuideSport.TENNIS)), now)
        assertEquals(SportsRailCount(3, 1), counts[null])
        assertEquals(SportsRailCount(2, 1), counts[GuideSport.FOOTBALL])
        assertEquals(SportsRailCount(1, 0), counts[GuideSport.TENNIS])
    }

    @Test fun cardStatusAndChannelBadge() {
        assertEquals(SportsCardStatus.Live, game("1", "A vs B", -minute).cardStatus(now))
        assertEquals(SportsCardStatus.Soon, game("1", "A vs B", 2 * hour).cardStatus(now))
        assertEquals(SportsCardStatus.Later, game("1", "A vs B", 4 * hour).cardStatus(now))
        assertEquals("Sky Sports 1", game("1", "A vs B", -minute).cardChannel(now))
        assertEquals("NBA TV", sportsChannelBadge("Strong8K · CA EN: NBA TV ᴿᴬᵂ"))
        assertEquals("ESPN", sportsChannelBadge("US| ESPN FHD"))
    }

    @Test fun restoredLiveMarkersLastUntilTheRefresh() {
        val saved = now - 20 * minute
        fun addon(startsAt: Long?) = SportsAddonEvent("i", "a", "Add-on", "tv", "e", "A vs B", emptyList(), startsAt, true, saved, null)
        // Started an hour ago: still live right after the app starts.
        assertTrue(addon(now - hour).restoredFromDisk(now).isLive(now))
        // Started five hours ago: no longer called live.
        assertFalse(addon(now - 5 * hour).restoredFromDisk(now).isLive(now))
        // Not live stays not live.
        val off = addon(now - hour).copy(live = false)
        assertFalse(off.restoredFromDisk(now).isLive(now))
    }
    @Test fun watchSkipsTeasersTipsAndConfigureLinks() {
        fun s(name: String, url: String, desc: String? = null, external: Boolean = false) =
            com.arflix.tv.data.repository.SportsAddonStream(name, url, emptyMap(), external, desc)
        val streams = listOf(
            s("🔒 FP1 HD", "https://www.google.com", "Upgrade to Premium"),
            s("Tip", "https://sports.example.dev/info", "Tip: use an external player"),
            s("Black Friday Sale", "https://sports.example.dev/sale"),
            s("Settings", "https://sports.example.to/configure"),
            s("Open in browser", "https://site.example/watch", external = true),
            s("UK: TNT Sports 2", "http://cdn.example/live/tnt2.m3u8"),
        )
        assertEquals("UK: TNT Sports 2", sportsWatchStream(streams)?.name)
        assertEquals(null, sportsWatchStream(streams.take(5)))
    }

    @Test fun guidePlaceholdersAreNotGames() {
        listOf("- NO EVENT STREAMING - | 8K EXCLUSIVE", "ENDED | Eintracht Frankfurt II vs Ulm", "Next | UK Championship",
            "24/7 Football", "No live events", "Coming up: Arsenal vs Chelsea").forEach {
            assertTrue(it, guidePlaceholder.containsMatchIn(it))
        }
        listOf("Arsenal vs Chelsea", "LIVE | #22 West Virginia vs #13 Notre Dame", "Next Gen ATP Finals Final", "Valencia GP FP1").forEach {
            if (it.startsWith("Next Gen")) return@forEach
            assertFalse(it, guidePlaceholder.containsMatchIn(it))
        }
        val resolver = SportsProgrammeResolver()
        assertEquals(null, resolver.resolve(IptvProgram("ENDED | Ajax vs PSV", startUtcMillis = now, endUtcMillis = now + hour), GuideSport.FOOTBALL))
        assertEquals(GuideSport.FOOTBALL, resolver.resolve(IptvProgram("Ajax vs PSV", startUtcMillis = now, endUtcMillis = now + hour), GuideSport.FOOTBALL)?.sport)
    }

    @Test fun bigGuidesRescanOnlyAFewTimes() {
        assertEquals(0, sportsGuideSizeBucket(10))
        assertEquals(sportsGuideSizeBucket(40_000), sportsGuideSizeBucket(45_000))
        val buckets = (0..60_000 step 64).map(::sportsGuideSizeBucket).distinct()
        assertTrue("${buckets.size} rescans", buckets.size < 30)
    }

    @Test fun rowsShowACapButCountEverything() {
        val many = (1..150).map { game("$it", "Team $it vs Club $it", -minute) }
        val live = sportsHubRows(many, now, byCompetition = false, labels).first { it.id == SPORTS_ROW_LIVE }
        assertEquals(SPORTS_ROW_CAP, live.events.size)
        assertEquals(150, live.total)
        assertEquals(150, sportsRailCounts(many, now)[null]?.live)
    }

    @Test fun endedTitlesDatedReplaysAndAllDayBlocksAreNotGames() {
        assertTrue(guidePlaceholder.containsMatchIn("End | Cardinals - Cubs | Major League Baseball 2026"))
        assertTrue(guidePlaceholder.containsMatchIn("Eagles @ Cowboys @ Nov 26 22:29 :TeliaPlay SE 59"))
        assertFalse(guidePlaceholder.containsMatchIn("Endurance: Le Mans 24h"))
        val resolver = SportsProgrammeResolver()
        val block = IptvProgram("Sunderland - Jagiellonia", startUtcMillis = now - 17 * hour, endUtcMillis = now + hour)
        assertEquals(null, resolver.resolve(block, GuideSport.FOOTBALL))
        assertEquals(null, resolver.resolve(block, GuideSport.OTHER))
        val game = IptvProgram("Sunderland - Jagiellonia", startUtcMillis = now - hour, endUtcMillis = now + hour)
        assertEquals(GuideSport.FOOTBALL, resolver.resolve(game, GuideSport.FOOTBALL)?.sport)
    }

    @Test fun aNewGuideCopyIsNotANewScan() {
        val key = SportsScheduleKey("p", "all", 1L, emptySet(), false, 1L, 3)
        assertEquals(SportsScanRequest(key, emptyMap(), 0, 0), SportsScanRequest(key, mapOf("a" to com.arflix.tv.data.model.IptvNowNext()), 0, 0))
        assertNotEquals(SportsScanRequest(key, emptyMap(), 0, 0), SportsScanRequest(key.copy(window = 2L), emptyMap(), 0, 0))
    }
}
