package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsEventArtwork
import com.arflix.tv.data.model.SportsFixture
import com.arflix.tv.data.model.sportsEventIdentity
import com.arflix.tv.data.model.safeSportsImage
import com.arflix.tv.data.model.sportsQualifierKey
import java.time.Instant
import java.time.ZoneId
import java.util.Locale

internal data class SportsScheduleKey(val profileId: String?, val providerId: String,
    val sourceVersion: Long, val excludedGroups: Set<String>, val epgBackfill: Boolean, val window: Long)
internal data class SportsScheduleSnapshot(val key: SportsScheduleKey, val events: List<SportsGuideEvent>)

/** Schedule facts, not stream probes. Channel identities remain provider-specific. */
internal data class SportsGuideEvent(
    val id: String,
    val title: String,
    val sport: GuideSport,
    val programme: IptvProgram,
    val channels: List<IptvChannel>,
    val artwork: String? = null,
    val schedules: Map<String, IptvProgram> = channels.associate { it.id to programme },
    val competition: String? = null,
    val teamArtwork: SportsEventArtwork? = null,
    val artworkSource: String? = null,
    val fixture: SportsFixture? = null,
    val possibleChannels: List<IptvChannel> = emptyList(),
    val prominence: Int = 0,
) {
    val identity: String = sportsEventIdentity(title)
    val hasEventArtwork: Boolean get() = !artwork.isNullOrBlank() ||
        (teamArtwork?.homeBadge != null && teamArtwork.awayBadge != null)
    fun isConfirmedLive(now: Long) = fixture?.status == "live" && now >= fixture.observedAt && now - fixture.observedAt < 300_000
    fun isOnAir(now: Long) = fixture?.status !in setOf("finished", "postponed") &&
        (isConfirmedLive(now) || if (schedules.isEmpty()) programme.isLive(now) else schedules.values.any { it.isLive(now) })
    fun availableChannels(now: Long) = channels.filter { (schedules[it.id] ?: programme).isLive(now) }
    fun hasChannels(now: Long) = (if (isOnAir(now)) availableChannels(now) else channels).isNotEmpty() || possibleChannels.isNotEmpty()
}

internal fun attachSportsArtwork(events: List<SportsGuideEvent>, artwork: List<SportsEventArtwork>): List<SportsGuideEvent> {
    val byTitle = artwork.groupBy { sportsEventIdentity(it.title) }
    return events.map { event ->
        val candidates = byTitle[event.identity].orEmpty().filter {
            val sport = GuideSport.fromText(it.genres.joinToString(" "))
            (sport == event.sport || (sport == null && it.source != "TheSportsDB")) &&
                sportsQualifierKey("${event.title} ${event.competition.orEmpty()}") == sportsQualifierKey("${it.title} ${it.fixture?.league.orEmpty()}") &&
                (it.startsAt == null || kotlin.math.abs(it.startsAt - event.programme.startUtcMillis) <=
                    (if (it.source == "TheSportsDB") 2 else 6) * 60 * 60_000L)
        }
        val match = candidates.firstOrNull { it.homeBadge != null && it.awayBadge != null }
        val banner = candidates.firstOrNull { safeSportsImage(it.background) != null }
        event.copy(artwork = safeSportsImage(event.programme.artworkUrl) ?: candidates.firstNotNullOfOrNull { safeSportsImage(it.background) },
            teamArtwork = match, artworkSource = banner?.source ?: match?.source)
    }
}

internal enum class GuideSport(val title: String, val asset: String, val terms: Regex) {
    FOOTBALL("Football", "football", Regex("\\b(football|soccer|premier league|champions league|la liga|eredivisie|bundesliga)\\b")),
    BASKETBALL("Basketball", "basketball", Regex("\\b(basketball|nba|wnba|euroleague)\\b")),
    F1("Formula 1", "motor_sports", Regex("\\b(f1|formula 1|formula one)\\b")),
    TENNIS("Tennis", "tennis", Regex("\\b(tennis|atp|wta|wimbledon)\\b")),
    MMA("MMA", "fight", Regex("\\b(mma|ufc|bellator|pfl)\\b")),
    BOXING("Boxing", "fight", Regex("\\b(boxing|boxen)\\b")),
    AMERICAN_FOOTBALL("American football", "american_football", Regex("\\b(american football|nfl|ncaa football)\\b")),
    CRICKET("Cricket", "cricket", Regex("\\b(cricket|t20|ipl)\\b")),
    BASEBALL("Baseball", "baseball", Regex("\\b(baseball|mlb)\\b")),
    HOCKEY("Ice hockey", "hockey", Regex("\\b(ice hockey|hockey|nhl)\\b")),
    MOTORSPORT("Motorsport", "motor_sports", Regex("\\b(motorsport|motor sports|motogp|nascar|indycar|superbike|formula e|rally)\\b")),
    RUGBY("Rugby", "rugby", Regex("\\b(rugby|six nations)\\b")),
    GOLF("Golf", "golf", Regex("\\b(golf|pga|lpga|ryder cup|solheim cup)\\b")),
    SNOOKER("Snooker", "billiards", Regex("\\b(snooker|billiards)\\b")),
    DARTS("Darts", "darts", Regex("\\b(darts|pdc)\\b")),
    AUSTRALIAN_FOOTBALL("Australian football", "afl", Regex("\\b(australian football|aussie rules|afl)\\b")),
    CYCLING("Cycling", "other", Regex("\\b(cycling|tour de france|vuelta|giro d italia)\\b")),
    ATHLETICS("Athletics", "other", Regex("\\b(athletics|track and field|diamond league)\\b")),
    VOLLEYBALL("Volleyball", "other", Regex("\\b(volleyball)\\b")),
    HANDBALL("Handball", "other", Regex("\\b(handball)\\b"));

    companion object {
        private val priority = listOf(AMERICAN_FOOTBALL, AUSTRALIAN_FOOTBALL, BASKETBALL, F1, MOTORSPORT, TENNIS, MMA, BOXING,
            CRICKET, BASEBALL, HOCKEY, RUGBY, GOLF, SNOOKER, DARTS, CYCLING, ATHLETICS, VOLLEYBALL, HANDBALL, FOOTBALL)
        fun fromText(text: String): GuideSport? {
            val value = text.lowercase(Locale.ROOT)
            // Specific football codes must win over the generic word football.
            return priority.firstOrNull { it.terms.containsMatchIn(value) }
        }
    }
}

private val nonEvent = Regex("\\b(highlights?|hoogtepunten|samenvatting|resumen|replay|re-?run|classic|news|magazine|review|preview|cancelled|canceled|postponed|abandoned|sendepause|off air|no signal|best of|teleshopping|infomercial|documentary)\\b", RegexOption.IGNORE_CASE)
private val eventMatchup = Regex("\\s+(?:vs?\\.?|versus|at|[-–—])\\s+", RegexOption.IGNORE_CASE)
private val space = Regex("\\s+")

private val competitions = listOf("UEFA Champions League", "Premier League", "La Liga", "Eredivisie", "Bundesliga",
    "Serie A", "Ligue 1", "WNBA", "NBA", "Euroleague", "NFL", "MLB", "NHL", "Wimbledon", "UFC", "Formula 1")
    .map { it to Regex("\\b${Regex.escape(it)}\\b", RegexOption.IGNORE_CASE) }
private fun competition(text: String) = competitions.firstOrNull { it.second.containsMatchIn(text) }?.first

/** Small broadcast padding differences may match; a later replay or a different opponent may not. */
internal class SportsEventIndex {
    private class Entry(val first: SportsGuideEvent) {
        val channels = linkedMapOf<String, IptvChannel>()
        val schedules = linkedMapOf<String, IptvProgram>()
        var artwork = first.programme.artworkUrl
        var competition = first.competition
        fun add(event: SportsGuideEvent) {
            event.channels.forEach { channels.putIfAbsent(it.id, it) }
            schedules.putAll(event.schedules)
            artwork = artwork ?: event.programme.artworkUrl
            competition = competition ?: event.competition
        }
        fun snapshot() = first.copy(channels = channels.values.toList(), schedules = schedules.toMap(),
            programme = first.programme.copy(artworkUrl = artwork), competition = competition)
    }
    private val groups = linkedMapOf<String, MutableList<Entry>>()
    fun add(event: SportsGuideEvent) {
        add(event.sport, event.identity, event.programme, event.channels, event.schedules, event.competition)
    }
    fun add(sport: GuideSport, identity: String, programme: IptvProgram, channel: IptvChannel, competition: String?) {
        add(sport, identity, programme, listOf(channel), null, competition)
    }
    private fun add(sport: GuideSport, identity: String, programme: IptvProgram,
        channels: List<IptvChannel>, schedules: Map<String, IptvProgram>?, competition: String?) {
        val key = "${sport.name}|$identity"
        val group = groups.getOrPut(key) { mutableListOf() }
        val old = group.firstOrNull { old ->
            val a = old.first.programme; val b = programme
            val overlap = minOf(a.endUtcMillis, b.endUtcMillis) - maxOf(a.startUtcMillis, b.startUtcMillis)
            kotlin.math.abs(a.startUtcMillis - b.startUtcMillis) <= 15 * 60_000L &&
                overlap > 0 && overlap >= minOf(a.endUtcMillis - a.startUtcMillis, b.endUtcMillis - b.startUtcMillis) / 2
        }
        // Materialize an event once, not once per HD/UHD/localized channel alias.
        val entry = old ?: Entry(SportsGuideEvent("$key|${programme.startUtcMillis}", programme.title,
            sport, programme, emptyList(), competition = competition)).also(group::add)
        channels.forEach { entry.channels.putIfAbsent(it.id, it) }
        if (schedules != null) entry.schedules.putAll(schedules)
        else channels.forEach { entry.schedules[it.id] = programme }
        entry.artwork = entry.artwork ?: programme.artworkUrl
        entry.competition = entry.competition ?: competition
    }
    fun events(): List<SportsGuideEvent> = groups.values.flatMap { group -> group.map { it.snapshot() } }
}

/** XMLTV variants share programme text. Classify it once per scan, with bounded memory. */
internal class SportsProgrammeResolver {
    data class Metadata(val sport: GuideSport, val identity: String, val competition: String?)
    private data class Key(val title: String, val category: String?, val fallback: GuideSport?)
    private val cache = object : LinkedHashMap<Key, Metadata?>(512, .75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Key, Metadata?>?) = size > 4096
    }
    fun resolve(programme: IptvProgram, fallback: GuideSport?): Metadata? {
        val key = Key(programme.title, programme.category, fallback)
        if (cache.containsKey(key)) return cache[key]
        val metadata = if (nonEvent.containsMatchIn(programme.title)) null else {
            val text = "${programme.category.orEmpty()} ${programme.title}"
            // Channel genre alone does not make advertising or downtime a sporting event.
            (GuideSport.fromText(text) ?: fallback?.takeIf { eventMatchup.containsMatchIn(programme.title) })
                ?.let { Metadata(it, sportsEventIdentity(programme.title), competition(text)) }
        }
        cache[key] = metadata
        return metadata
    }
}

internal fun retainSportsEventOrder(previous: List<SportsGuideEvent>, incoming: List<SportsGuideEvent>): List<SportsGuideEvent> {
    val rank = previous.withIndex().associate { it.value.id to it.index }
    return incoming.sortedBy { rank[it.id] ?: Int.MAX_VALUE }
}

internal fun sportsProgrammeKey(programme: IptvProgram): String =
    "${programme.title.trim().lowercase(Locale.ROOT).replace(space, " ")}|${programme.startUtcMillis}|${programme.endUtcMillis}"

internal fun buildSportsGuideEvents(
    channels: List<IptvChannel>,
    guide: Map<String, IptvNowNext>,
    now: Long,
    zone: ZoneId = ZoneId.systemDefault(),
    resolver: SportsProgrammeResolver = SportsProgrammeResolver(),
): List<SportsGuideEvent> {
    val events = SportsEventIndex()
    accumulateSportsGuideEvents(channels, guide, now, events, zone, resolver)
    return events.events().sortedWith(compareByDescending<SportsGuideEvent> { it.isOnAir(now) }
        .thenBy { it.programme.startUtcMillis }.thenBy { it.title })
}

internal fun accumulateSportsGuideEvents(
    channels: List<IptvChannel>, guide: Map<String, IptvNowNext>, now: Long,
    events: SportsEventIndex, zone: ZoneId = ZoneId.systemDefault(),
    resolver: SportsProgrammeResolver = SportsProgrammeResolver(),
) {
    val end = Instant.ofEpochMilli(now).atZone(zone).toLocalDate().plusDays(2)
        .atStartOfDay(zone).toInstant().toEpochMilli()
    for (channel in channels) {
        val slice = guide[channel.id] ?: continue
        val fallback = GuideSport.fromText("${channel.group} ${channel.name}")
        val programmes = (listOfNotNull(slice.now, slice.next, slice.later) + slice.upcoming)
            .distinctBy { Triple(it.title, it.startUtcMillis, it.endUtcMillis) }
        for (programme in programmes) {
            if (programme.endUtcMillis <= now || programme.startUtcMillis >= end ||
                programme.endUtcMillis <= programme.startUtcMillis || programme.title.isBlank()) continue
            val meta = resolver.resolve(programme, fallback) ?: continue
            events.add(meta.sport, meta.identity, programme, channel, meta.competition)
        }
    }
}

internal data class SportsGuideRow(val id: String, val title: String, val events: List<SportsGuideEvent>)

internal fun sportsPresentationRows(events: List<SportsGuideEvent>, now: Long, failedArtwork: Set<String>): List<SportsGuideRow> {
    val (illustrated, schedule) = events.filter { it.hasChannels(now) }.partition { it.hasEventArtwork && it.id !in failedArtwork }
    return sportsGuideRows(illustrated, now) + sportsGuideRows(schedule, now)
        .filter { it.id !in setOf("featured", "upcoming", "more") }
        .map { it.copy(id = "${it.id}-schedule", title = "${it.title} schedule") }
}

internal enum class SportsDay(val label: String) {
    BOTH("Today & tomorrow"), TODAY("Today"), TOMORROW("Tomorrow");

    fun includes(start: Long, now: Long, zone: ZoneId): Boolean {
        val today = Instant.ofEpochMilli(now).atZone(zone).toLocalDate()
        val date = Instant.ofEpochMilli(start).atZone(zone).toLocalDate()
        return when (this) {
            BOTH -> date == today || date == today.plusDays(1)
            TODAY -> date == today
            TOMORROW -> date == today.plusDays(1)
        }
    }
}

internal fun sportsGuideRows(events: List<SportsGuideEvent>, now: Long,
    day: SportsDay = SportsDay.BOTH, zone: ZoneId = ZoneId.systemDefault()): List<SportsGuideRow> {
    val live = events.filter { it.isOnAir(now) }.sortedWith(compareByDescending<SportsGuideEvent> { it.prominence }
        .thenBy { it.programme.startUtcMillis }.thenBy { it.id })
    return buildList {
        // EPG has no viewer metrics. Never call this popularity or confirmed live sport.
        if (live.isNotEmpty()) add(SportsGuideRow("featured", "Featured live", live.take(8)))
        val upcoming = events.filter { it.programme.startUtcMillis > now && !it.isOnAir(now) &&
            day.includes(it.programme.startUtcMillis, now, zone) }
            .sortedWith(compareByDescending<SportsGuideEvent> { it.prominence }.thenBy { it.programme.startUtcMillis }.thenBy { it.id })
        if (upcoming.isNotEmpty()) add(SportsGuideRow("upcoming", "Upcoming highlights", upcoming.take(8)))
        GuideSport.entries.forEach { sport ->
            val items = live.filter { it.sport == sport } + upcoming.filter { it.sport == sport }.sortedBy { it.programme.startUtcMillis }
            if (items.isNotEmpty()) add(SportsGuideRow(sport.name, sport.title, items))
        }
    }
}
