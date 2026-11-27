package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsEventArtwork
import com.arflix.tv.data.model.SportsFollow
import com.arflix.tv.data.model.SportsReminder
import com.arflix.tv.data.model.sportsFollowKey
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/*
 * "My sports", day tabs, filters and reminders. Pure functions over the sports
 * catalogue, shared by the Sports page, the reminder banner and their tests.
 */

private val matchupSplit = Regex("\\s+(?:vs?\\.?|versus|at|[-–—])\\s+", RegexOption.IGNORE_CASE)

/** The two sides of a game: from the fixture when known, else from an "A vs B" title. */
internal fun SportsGuideEvent.teamNames(): List<String> {
    val home = teamArtwork?.homeTeam
    val away = teamArtwork?.awayTeam
    if (!home.isNullOrBlank() && !away.isNullOrBlank()) return listOf(home, away)
    val plain = title.substringAfterLast(": ").trim()
    val parts = plain.split(matchupSplit).map { it.trim() }.filter { it.isNotEmpty() }
    return if (parts.size == 2) parts else emptyList()
}

internal fun SportsGuideEvent.followsTeam(follows: Collection<SportsFollow>): Boolean {
    val teams = follows.filter { it.kind == SportsFollow.KIND_TEAM }.map { sportsFollowKey(it.name) }.toSet()
    if (teams.isEmpty()) return false
    return teamNames().any { sportsFollowKey(it) in teams }
}

internal fun SportsGuideEvent.followsLeague(follows: Collection<SportsFollow>): Boolean {
    val competition = competition ?: fixture?.league ?: return false
    val key = sportsFollowKey(competition, league = true)
    return follows.any { it.kind == SportsFollow.KIND_LEAGUE && sportsFollowKey(it.name, league = true) == key }
}

internal fun SportsGuideEvent.isFollowed(follows: Collection<SportsFollow>): Boolean =
    follows.isNotEmpty() && (followsTeam(follows) || followsLeague(follows))

/** What the event's detail sheet offers to follow: both teams, then the competition. */
internal fun SportsGuideEvent.followOptions(): List<SportsFollow> = buildList {
    teamNames().forEach { add(SportsFollow(SportsFollow.KIND_TEAM, it, sport.name)) }
    (competition ?: fixture?.league)?.takeIf { it.isNotBlank() }?.let { add(SportsFollow(SportsFollow.KIND_LEAGUE, it, sport.name)) }
}.distinctBy { it.key }

internal fun SportsGuideEvent.isFinished(): Boolean = fixture?.status == "finished"

/** Live first, then the soonest kick-off; finished games last. */
internal fun mySportsEvents(events: List<SportsGuideEvent>, follows: Collection<SportsFollow>, now: Long): List<SportsGuideEvent> {
    if (follows.isEmpty()) return emptyList()
    return events.filter { it.isFollowed(follows) && !it.isFinished() && it.hasChannels(now) &&
        (it.isOnAir(now) || it.isScheduledNow(now) || it.programme.startUtcMillis > now) }
        .sortedWith(compareByDescending<SportsGuideEvent> { it.isOnAir(now) }.thenBy { it.programme.startUtcMillis }.thenBy { it.id })
}

// ── Filters ────────────────────────────────────────────────────────────────

internal const val SPORTS_FILTER_ALL = "all"
internal const val SPORTS_FILTER_LIVE = "live"

/** "all", "live", or a [GuideSport] name. */
internal fun SportsGuideEvent.matchesSportsFilter(filter: String, now: Long): Boolean = when (filter) {
    SPORTS_FILTER_ALL -> true
    SPORTS_FILTER_LIVE -> isOnAir(now)
    else -> sport.name == filter
}

/** The sport chips worth showing: sports that have at least one game, in the usual order. */
internal fun sportsFilterOptions(events: List<SportsGuideEvent>): List<GuideSport> {
    val present = events.mapTo(hashSetOf()) { it.sport }
    return GuideSport.entries.filter { it in present }
}

// ── Day tabs ───────────────────────────────────────────────────────────────

/** A week back and a week ahead of today. */
internal val SPORTS_DAY_OFFSETS = -7..7

internal fun sportsLocalDate(millis: Long, zone: ZoneId): LocalDate = Instant.ofEpochMilli(millis).atZone(zone).toLocalDate()

/** The UTC calendar days the server is asked for to cover one local day. */
internal fun sportsUtcDaysFor(date: LocalDate, zone: ZoneId): List<String> {
    val start = date.atStartOfDay(zone).toInstant()
    val end = date.plusDays(1).atStartOfDay(zone).toInstant().minusMillis(1)
    return listOf(start, end).map { it.atZone(java.time.ZoneOffset.UTC).toLocalDate().toString() }.distinct()
}

/** A game known only from the schedule feed: shown on its day, playable only if the catalogue has it. */
internal fun fixtureOnlySportsEvent(item: SportsEventArtwork): SportsGuideEvent? {
    val fixture = item.fixture ?: return null
    val start = item.startsAt ?: return null
    val sport = GuideSport.fromText("${item.genres.joinToString(" ")} ${fixture.league.orEmpty()}") ?: GuideSport.OTHER
    return SportsGuideEvent(id = "sportsdb:${fixture.id}", title = item.title, sport = sport,
        programme = IptvProgram(title = item.title, startUtcMillis = start, endUtcMillis = start),
        channels = emptyList(), artwork = item.background.takeIf { it.isNotBlank() },
        teamArtwork = item.takeIf { it.homeBadge != null && it.awayBadge != null }, artworkSource = item.source,
        competition = fixture.league, fixture = fixture,
        prominence = sportsProminence(fixture.league, fixture.broadcasters.map { it.country }.filter { it.isNotBlank() }.distinct().size))
}

/**
 * Every game on one local day: the catalogue's games (which carry channels) plus
 * schedule-only fixtures for that day, including finished ones, so a past day
 * reads as results and a later day as the fixture list.
 */
internal fun sportsEventsForDay(
    catalogue: List<SportsGuideEvent>,
    fixtures: List<SportsEventArtwork>,
    date: LocalDate,
    zone: ZoneId,
): List<SportsGuideEvent> {
    val onDay = catalogue.filter { sportsLocalDate(it.programme.startUtcMillis, zone) == date }
    val known = onDay.mapTo(hashSetOf()) { it.id }
    val extra = fixtures.asSequence()
        .filter { it.startsAt != null && sportsLocalDate(it.startsAt, zone) == date }
        .mapNotNull(::fixtureOnlySportsEvent)
        .filter { known.add(it.id) }
        .toList()
    return onDay + extra
}

/** Day view rows: followed games first, then one row per sport in kick-off order. */
internal fun sportsDayRows(events: List<SportsGuideEvent>, follows: Collection<SportsFollow>, now: Long): List<SportsGuideRow> {
    val ordered = events.sortedWith(compareBy<SportsGuideEvent> { it.programme.startUtcMillis }
        .thenByDescending { it.prominence }.thenBy { it.id })
    return buildList {
        val mine = ordered.filter { it.isFollowed(follows) }
        if (mine.isNotEmpty()) add(SportsGuideRow(SPORTS_ROW_MINE, "My sports", mine))
        val live = ordered.filter { it.isOnAir(now) && !it.channelOnly }
        if (live.isNotEmpty()) add(SportsGuideRow("day-live", "Live now", live))
        GuideSport.entries.forEach { sport ->
            val items = ordered.filter { it.sport == sport && !it.channelOnly }
            if (items.isNotEmpty()) add(SportsGuideRow("day-${sport.name}", sport.title, items))
        }
    }
}

internal const val SPORTS_ROW_MINE = "my-sports"

// ── Reminders ──────────────────────────────────────────────────────────────

/** The switch happens this long before kick-off, after a short countdown. */
internal const val SPORTS_REMINDER_LEAD_MS = 60_000L
internal const val SPORTS_REMINDER_COUNTDOWN_MS = 10_000L
/** A reminder missed while the app was closed still fires this long after kick-off. */
private const val SPORTS_REMINDER_LATE_MS = 15 * 60_000L
/** Automatic reminders look this far ahead. */
private const val SPORTS_AUTO_REMINDER_WINDOW_MS = 2 * 24 * 60 * 60_000L
private const val SPORTS_REMINDER_KEEP_MS = 6 * 60 * 60_000L

/** Channels to switch to, best first: guide matches, then likely broadcasters. */
internal fun SportsGuideEvent.reminderChannelIds(): List<String> =
    (channels + possibleChannels).map { it.id }.distinct().take(8)

internal fun SportsGuideEvent.toReminder(auto: Boolean = false) =
    SportsReminder(eventId = id, title = title, startsAt = programme.startUtcMillis, channelIds = reminderChannelIds(), auto = auto)

internal fun List<SportsReminder>.isReminded(eventId: String): Boolean = any { it.eventId == eventId && !it.dismissed }

/** Turns one game's reminder on or off. Turning off an automatic one remembers that. */
internal fun List<SportsReminder>.withReminder(event: SportsGuideEvent, on: Boolean): List<SportsReminder> {
    val existing = firstOrNull { it.eventId == event.id }
    val rest = filterNot { it.eventId == event.id }
    return when {
        on -> rest + event.toReminder(auto = existing?.auto == true)
        existing?.auto == true -> rest + existing.copy(dismissed = true)
        else -> rest
    }
}

/**
 * Adds reminders for games of followed teams (not whole leagues: a league can
 * have ten games at once), refreshes the channels of existing ones, and drops
 * old ones. Returns the same list when nothing changed.
 */
internal fun List<SportsReminder>.withAutoReminders(
    events: List<SportsGuideEvent>,
    follows: Collection<SportsFollow>,
    now: Long,
): List<SportsReminder> {
    val byId = events.associateBy { it.id }
    val kept = filter { it.startsAt > now - SPORTS_REMINDER_KEEP_MS }.map { reminder ->
        val event = byId[reminder.eventId] ?: return@map reminder
        val channels = event.reminderChannelIds()
        if (channels.isEmpty() || (channels == reminder.channelIds && event.programme.startUtcMillis == reminder.startsAt)) reminder
        else reminder.copy(channelIds = channels, startsAt = event.programme.startUtcMillis)
    }
    val known = kept.mapTo(hashSetOf()) { it.eventId }
    val added = events.filter { event ->
        event.id !in known && event.programme.startUtcMillis > now &&
            event.programme.startUtcMillis - now <= SPORTS_AUTO_REMINDER_WINDOW_MS &&
            !event.isFinished() && !event.channelOnly && event.followsTeam(follows) && event.reminderChannelIds().isNotEmpty()
    }.map { it.toReminder(auto = true) }
    val result = kept + added
    return if (result == this) this else result
}

/** The reminder whose countdown should show now, if any. */
internal fun List<SportsReminder>.dueReminder(now: Long, handled: Set<String>): SportsReminder? =
    filter { !it.dismissed && it.eventId !in handled &&
        now >= it.startsAt - SPORTS_REMINDER_LEAD_MS && now < it.startsAt + SPORTS_REMINDER_LATE_MS }
        .minByOrNull { it.startsAt }

/** When the next reminder needs attention, so the banner can sleep until then. */
internal fun List<SportsReminder>.nextReminderAt(now: Long, handled: Set<String>): Long? =
    filter { !it.dismissed && it.eventId !in handled && it.startsAt + SPORTS_REMINDER_LATE_MS > now }
        .minOfOrNull { (it.startsAt - SPORTS_REMINDER_LEAD_MS).coerceAtLeast(now) }
