package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsEventArtwork
import com.arflix.tv.data.model.SportsFixture
import com.arflix.tv.data.model.SportsFollow
import com.arflix.tv.data.model.SportsReminder
import com.arflix.tv.data.model.sanitizedFollows
import com.arflix.tv.data.model.sportsFollowKey
import com.arflix.tv.data.model.toggled
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

class SportsMySportsTest {
    private val now = 1_790_000_000_000L
    private val hour = 3_600_000L
    private val channel = IptvChannel("c1", "ESPN 1", "http://x", "Sports")

    private fun event(id: String, title: String, start: Long, competition: String? = null, home: String? = null, away: String? = null,
        status: String = "scheduled", channels: List<IptvChannel> = listOf(channel)): SportsGuideEvent {
        val programme = IptvProgram(title = title, startUtcMillis = start, endUtcMillis = start + 2 * hour)
        return SportsGuideEvent(id = id, title = title, sport = GuideSport.FOOTBALL, programme = programme, channels = channels,
            competition = competition,
            teamArtwork = if (home != null && away != null) SportsEventArtwork(title, "", listOf("Soccer"), start, "h", "a", home, away) else null,
            fixture = SportsFixture(id, competition, null, null, null, status, now, null, null, emptyList()))
    }

    private val ajax = SportsFollow(SportsFollow.KIND_TEAM, "AFC Ajax")
    private val epl = SportsFollow(SportsFollow.KIND_LEAGUE, "English Premier League")

    @Test fun teamsMatchFromFixturesOrTitlesWithoutClubAffixes() {
        assertEquals(sportsFollowKey("Ajax"), sportsFollowKey("AFC Ajax"))
        assertEquals(sportsFollowKey("Premier League", league = true), sportsFollowKey("English Premier League", league = true))
        assertTrue(event("1", "Ajax vs PSV", now + hour).isFollowed(listOf(ajax)))
        assertTrue(event("2", "Football: PSV - Ajax", now + hour).isFollowed(listOf(ajax)))
        assertTrue(event("3", "Home vs Away", now + hour, home = "Ajax Amsterdam", away = "AFC Ajax").isFollowed(listOf(ajax)))
        // A longer name that merely contains the team is another team.
        assertFalse(event("4", "Jong Ajax vs Den Bosch", now + hour).isFollowed(listOf(ajax)))
        assertTrue(event("5", "Arsenal vs Chelsea", now + hour, competition = "Premier League").isFollowed(listOf(epl)))
        assertFalse(event("6", "Arsenal vs Chelsea", now + hour, competition = "Premier League").isFollowed(listOf(ajax)))
    }

    @Test fun myRowListsLiveFirstAndLeavesFinishedOrChannellessGamesOut() {
        val live = event("live", "PSV vs Ajax", now - hour)
        val later = event("later", "Ajax vs Feyenoord", now + 5 * hour)
        val finished = event("done", "Ajax vs AZ", now - hour, status = "finished")
        val noChannel = event("none", "Ajax vs Twente", now + hour, channels = emptyList())
        val other = event("other", "Utrecht vs Twente", now + hour)
        assertEquals(listOf("live", "later"), mySportsEvents(listOf(later, finished, noChannel, other, live), listOf(ajax), now).map { it.id })
        assertTrue(mySportsEvents(listOf(live), emptyList(), now).isEmpty())
    }

    @Test fun followOptionsAreBothTeamsThenTheCompetition() {
        val options = event("1", "Ajax vs PSV", now, competition = "Eredivisie").followOptions()
        assertEquals(listOf("team:ajax", "team:psv", "league:eredivisie"), options.map { it.key })
        val follows = emptyList<SportsFollow>().toggled(options[0]).toggled(options[2])
        assertEquals(2, follows.size)
        assertTrue(follows.toggled(options[0]).none { it.key == "team:ajax" })
        assertEquals(1, (follows + follows).sanitizedFollows().count { it.key == "team:ajax" })
    }

    @Test fun filtersCoverLiveAndSingleSports() {
        val live = event("live", "PSV vs Ajax", now - hour)
        val tennis = event("t", "Player A vs Player B", now + hour).copy(sport = GuideSport.TENNIS)
        assertTrue(live.matchesSportsFilter(SPORTS_FILTER_LIVE, now))
        assertFalse(tennis.matchesSportsFilter(SPORTS_FILTER_LIVE, now))
        assertTrue(tennis.matchesSportsFilter("TENNIS", now))
        assertFalse(live.matchesSportsFilter("TENNIS", now))
        assertEquals(listOf(GuideSport.TENNIS, GuideSport.FOOTBALL).sortedBy { it.ordinal }, sportsFilterOptions(listOf(live, tennis, live)))
    }

    @Test fun dayViewMergesScheduleOnlyFixturesWithoutDuplicates() {
        val zone = ZoneOffset.UTC
        val day = LocalDate.of(2026, 11, 20)
        val start = day.atTime(15, 0).toInstant(ZoneOffset.UTC).toEpochMilli()
        val inCatalogue = event("sportsdb:1", "Ajax vs PSV", start)
        val fixture = SportsFixture("1", "Eredivisie", null, null, null, "finished", now, 2, 1, emptyList())
        val sameGame = SportsEventArtwork("Ajax vs PSV", "", listOf("Soccer"), start, fixture = fixture)
        val otherGame = SportsEventArtwork("AZ vs Twente", "", listOf("Soccer"), start + hour, fixture = fixture.copy(id = "2"))
        val nextDay = SportsEventArtwork("Utrecht vs NEC", "", listOf("Soccer"), start + 24 * hour, fixture = fixture.copy(id = "3"))
        val events = sportsEventsForDay(listOf(inCatalogue), listOf(sameGame, otherGame, nextDay), day, zone)
        assertEquals(listOf("sportsdb:1", "sportsdb:2"), events.map { it.id })
        assertTrue(events[1].isFinished())
        val rows = sportsDayRows(events, listOf(ajax), now)
        assertEquals(SPORTS_ROW_MINE, rows.first().id)
        assertEquals(listOf("sportsdb:1"), rows.first().events.map { it.id })
        assertTrue(rows.any { it.id == "day-FOOTBALL" && it.events.size == 2 })
    }

    @Test fun aLocalDayAsksForEveryUtcDayItTouches() {
        val day = LocalDate.of(2026, 11, 20)
        assertEquals(listOf("2026-11-20"), sportsUtcDaysFor(day, ZoneOffset.UTC))
        assertEquals(listOf("2026-11-19", "2026-11-20"), sportsUtcDaysFor(day, ZoneId.of("Europe/Amsterdam")))
        assertEquals(listOf("2026-11-20", "2026-11-21"), sportsUtcDaysFor(day, ZoneId.of("America/New_York")))
    }

    @Test fun followedTeamsGetAutomaticRemindersThatCanBeTurnedOff() {
        val game = event("g", "Ajax vs PSV", now + 3 * hour)
        val league = event("l", "Arsenal vs Chelsea", now + 3 * hour, competition = "Premier League")
        val far = event("f", "Ajax vs AZ", now + 5 * 24 * hour)
        val reminders = emptyList<SportsReminder>().withAutoReminders(listOf(game, league, far), listOf(ajax, epl), now)
        assertEquals(listOf("g"), reminders.map { it.eventId })
        assertTrue(reminders.single().auto)
        assertEquals(listOf("c1"), reminders.single().channelIds)
        // Nothing changed: the same list comes back, so nothing is written.
        assertSame(reminders, reminders.withAutoReminders(listOf(game), listOf(ajax), now))
        val off = reminders.withReminder(game, on = false)
        assertFalse(off.isReminded("g"))
        assertFalse(off.withAutoReminders(listOf(game), listOf(ajax), now).isReminded("g"))
        assertTrue(off.withReminder(game, on = true).isReminded("g"))
        // A manual reminder on a game without a followed team is removed when turned off.
        val manual = emptyList<SportsReminder>().withReminder(league, on = true)
        assertTrue(manual.isReminded("l"))
        assertTrue(manual.withReminder(league, on = false).isEmpty())
        // Old reminders are dropped.
        assertTrue(manual.withAutoReminders(emptyList(), emptyList(), now + 10 * hour).isEmpty())
    }

    @Test fun reminderIsDueAMinuteBeforeKickOffOnce() {
        val reminder = SportsReminder("g", "Ajax vs PSV", now + 10 * 60_000L, listOf("c1"))
        val list = listOf(reminder)
        assertNull(list.dueReminder(now, emptySet()))
        assertEquals(now + 9 * 60_000L, list.nextReminderAt(now, emptySet()))
        assertEquals("g", list.dueReminder(now + 9 * 60_000L, emptySet())?.eventId)
        assertNull(list.dueReminder(now + 9 * 60_000L, setOf("g")))
        assertNull(list.nextReminderAt(now, setOf("g")))
        assertNull(listOf(reminder.copy(dismissed = true)).nextReminderAt(now, emptySet()))
        assertNull(listOf(reminder.copy(dismissed = true)).dueReminder(now + 9 * 60_000L, emptySet()))
        // Still fires shortly after kick-off when the app was closed, but not an hour later.
        assertEquals("g", list.dueReminder(now + 12 * 60_000L, emptySet())?.eventId)
        assertNull(list.dueReminder(now + 80 * 60_000L, emptySet()))
        assertNull(list.nextReminderAt(now + 80 * 60_000L, emptySet()))
    }
}
