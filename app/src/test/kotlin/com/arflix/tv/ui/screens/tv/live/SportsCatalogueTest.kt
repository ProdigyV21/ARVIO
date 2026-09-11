package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.*
import org.junit.Assert.*
import org.junit.Test
import java.time.Instant

class SportsCatalogueTest {
    @Test fun largeBroadcastFeedKeepsEveryFixtureAndProviderVariant() {
        val variants = (1..40).map { channel.copy(id = "provider:$it") }
        val repeated = List(200) { fixture.broadcasters.single() }
        val feed = (1..500).map { art.copy(title = "Fixture $it", fixture = fixture.copy(id = "$it", broadcasters = repeated)) }
        val started = System.nanoTime()
        val catalogue = buildSportsCatalogue(emptyList(), feed, variants, now)
        println("Sports catalogue: 500 fixtures, 100000 broadcast listings, 40 channel variants: ${(System.nanoTime() - started) / 1_000_000}ms")
        assertEquals(500, catalogue.size)
        assertTrue(catalogue.all { it.possibleChannels.size == 40 })
        assertEquals(500, sportsGuideRows(catalogue, now).single { it.id == "FOOTBALL" }.events.size)
    }
    @Test fun expandedRegionsAndQualityVariantsPreserveStationIdentity() {
        val keys = sportsBroadcasterKeys("beIN Sports 2", "Turkey")
        for (name in listOf("TR| beINSPORTS2 FHD", "TR| beIN Sport 2 1080p 50FPS BACKUP")) assertTrue(name, sportsChannelKey(name) in keys)
        for (name in listOf("FR| beIN Sports 2", "TR| beIN Sports 3", "TR| beIN Sports 2 +1")) assertFalse(name, sportsChannelKey(name) in keys)
    }
    @Test fun shortAndKnownTeamAliasesMatchWithoutGuessingOtherTeams() {
        val item = art.copy(title = "Manchester United vs PSV", homeTeam = "Manchester United", awayTeam = "PSV", startsAt = now)
        val result = buildSportsCatalogue(listOf(epg.copy(title = "Football: Man Utd - PSV, Premier League")), listOf(item), emptyList(), now)
        assertEquals(listOf(channel), result.single().channels)
        assertTrue(buildSportsCatalogue(listOf(epg.copy(title = "Man City - PSV")), listOf(item), emptyList(), now).single { it.fixture != null }.channels.isEmpty())
    }
    @Test fun previouslyExcludedSportsAreClassified() {
        for ((name, sport) in mapOf("Rugby" to GuideSport.RUGBY, "Golf" to GuideSport.GOLF, "Motorsport" to GuideSport.MOTORSPORT,
            "Formula 1" to GuideSport.F1, "Australian Football" to GuideSport.AUSTRALIAN_FOOTBALL, "Snooker" to GuideSport.SNOOKER))
            assertEquals(sport, GuideSport.fromText(name))
    }
    @Test fun missingArtworkDoesNotRemoveMatchedEvents() {
        val event = buildSportsCatalogue(emptyList(), listOf(art), listOf(channel), now).single()
        assertEquals("FOOTBALL-schedule", sportsPresentationRows(listOf(event), now, emptySet()).single().id)
        assertEquals("FOOTBALL-schedule", sportsPresentationRows(listOf(event.copy(artwork = "https://example.com/event.jpg")), now, setOf(event.id)).single().id)
        assertTrue(sportsPresentationRows(listOf(event.copy(possibleChannels = emptyList())), now, emptySet()).isEmpty())
    }
    @Test fun broadcasterDecorationsPreserveCountryAndChannelNumber() {
        assertEquals(sportsChannelKey("UK TNT Sports 2"), sportsChannelKey("UK-NOWTV| TNT SPORT 2 FHD"))
        assertNotEquals(sportsChannelKey("DE TNT Sports 2"), sportsChannelKey("UK-NOWTV| TNT SPORT 2 FHD"))
        assertNotEquals(sportsChannelKey("UK TNT Sports 1"), sportsChannelKey("UK-NOWTV| TNT SPORT 2 FHD"))
    }
    @Test fun broadcasterCountrySuffixMatchesOnlyThatRegionAndChannel() {
        val keys = sportsBroadcasterKeys("ESPN 3 Netherlands", "Netherlands")
        assertTrue(sportsChannelKey("NL | ESPN 3 UHD 8K") in keys)
        assertFalse(sportsChannelKey("US | ESPN 3 HD") in keys)
        assertFalse(sportsChannelKey("NL | ESPN 2 HD") in keys)
        assertFalse(sportsChannelKey("NL | ESPN 3 HD") in sportsBroadcasterKeys("ESPN 3 France", "Netherlands"))
    }
    @Test fun decoratedMatchTitlesUseRealCrestsWithoutStockArtwork() {
        val item = art.copy(homeTeam = "North", awayTeam = "South", homeBadge = "https://example.com/north.png", awayBadge = "https://example.com/south.png", startsAt = now)
        val decorated = epg.copy(title = "Football: North - South, Premier League 2026/2027")
        val result = buildSportsCatalogue(listOf(decorated), listOf(item), emptyList(), now).single()
        assertEquals(listOf(channel), result.channels)
        assertTrue(result.hasEventArtwork)
        assertFalse(epg.hasEventArtwork)
        val women = decorated.copy(title = "Football: North - South, Women")
        assertTrue(buildSportsCatalogue(listOf(women), listOf(item), emptyList(), now).single { it.fixture != null }.channels.isEmpty())
    }
    private val now = Instant.parse("2026-09-10T12:00:00Z").toEpochMilli()
    private val channel = IptvChannel("p:1", "UK | Sky Sports Main Event FHD", streamUrl = "https://example.invalid/live", group = "Sports")
    private val fixture = SportsFixture("42", "English Premier League", null, null, null, "scheduled", now, null, null,
        listOf(SportsBroadcaster("Sky Sports Main Event HD", "United Kingdom", now + 3600000)))
    private val art = SportsEventArtwork("North vs South", "", listOf("Soccer"), now + 3600000, source = "TheSportsDB", fixture = fixture)
    private val p = IptvProgram("North vs South", startUtcMillis = now - 60000, endUtcMillis = now + 3600000)
    private val epg = SportsGuideEvent("guide", p.title, GuideSport.FOOTBALL, p, listOf(channel), competition = "Premier League")

    @Test fun fixtureWithoutArtworkOrChannelsIsBrowsableButNeverInventsLiveStatus() {
        val event = buildSportsCatalogue(emptyList(), listOf(art), emptyList(), now).single()
        assertEquals("sportsdb:42", event.id)
        assertTrue(event.channels.isEmpty())
        assertFalse(event.isOnAir(now + 7200000))
        assertEquals(listOf("upcoming", "FOOTBALL"), sportsGuideRows(listOf(event), now).map { it.id })
    }
    @Test fun possibleBroadcastsAreSeparateAndHiddenSourcesDoNotLeak() {
        val wrong = channel.copy(id = "wrong", name = "DE | Sky Sports Main Event HD")
        val hint = buildSportsCatalogue(emptyList(), listOf(art), listOf(channel, wrong), now).single()
        assertTrue(hint.channels.isEmpty())
        assertEquals(listOf(channel.id), hint.possibleChannels.map { it.id })
        assertTrue(buildSportsCatalogue(emptyList(), listOf(art), emptyList(), now).single().possibleChannels.isEmpty())
        val actual = buildSportsCatalogue(listOf(epg), listOf(art.copy(startsAt = now)), listOf(channel), now).single()
        assertEquals(listOf(channel), actual.availableChannels(now))
        assertEquals(p.endUtcMillis, actual.schedules[channel.id]!!.endUtcMillis)
    }
    @Test fun qualifiersOtherLeaguesAndOtherTimesRemainSeparate() {
        for (changed in listOf(art.copy(fixture = fixture.copy(qualifier = "women")), art.copy(genres = listOf("Basketball")),
            art.copy(startsAt = now + 10800000), art.copy(fixture = fixture.copy(league = "UEFA Champions League")))) {
            val result = buildSportsCatalogue(listOf(epg), listOf(changed), emptyList(), now)
            assertTrue(result.single { it.fixture != null }.channels.isEmpty())
            assertEquals(1, result.count { it.fixture == null })
        }
    }
    @Test fun liveExpiresAndFinishedEventsSuppressOldGuide() {
        val live = art.copy(startsAt = now - 60000, fixture = fixture.copy(status = "live"))
        val event = buildSportsCatalogue(emptyList(), listOf(live), emptyList(), now).single()
        assertTrue(event.isConfirmedLive(now))
        assertFalse(event.isOnAir(now + 300001))
        assertTrue(buildSportsCatalogue(listOf(epg), listOf(art.copy(startsAt = now, fixture = fixture.copy(status = "finished"))), emptyList(), now).isEmpty())
    }
    @Test fun featuredHighlightsRankByProminenceAndSportRowsKeepUpcomingChronological() {
        val event = buildSportsCatalogue(emptyList(), listOf(art.copy(startsAt = now, fixture = fixture.copy(status = "live"))), emptyList(), now).single()
        val minor = event.copy(id = "minor", prominence = 0)
        assertEquals(event.id, sportsGuideRows(listOf(minor, event), now).first().events.first().id)
        val later = event.copy(id = "later", fixture = fixture, programme = p.copy(startUtcMillis = now + 7200000), schedules = emptyMap())
        val earlier = later.copy(id = "earlier", programme = p.copy(startUtcMillis = now + 3600000), prominence = 0)
        val rows = sportsGuideRows(listOf(later, earlier), now)
        assertEquals("later", rows.first().events.first().id)
        assertEquals("earlier", rows.last().events.first().id)
        assertFalse(rows.any { it.id == "more" })
    }
}
