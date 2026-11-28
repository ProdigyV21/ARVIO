package com.arflix.tv.ui.screens.tv.live

import androidx.compose.runtime.Immutable
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
    val sourceVersion: Long, val excludedGroups: Set<String>, val epgBackfill: Boolean, val window: Long,
    val guideCoverage: Int = 0)
internal data class SportsScheduleSnapshot(val key: SportsScheduleKey, val events: List<SportsGuideEvent>)
/** Least time between two automatic sports scans once a schedule is showing. */
internal const val SportsRescanGapMs = 5 * 60_000L

internal data class SportsScanRequest(
    val key: SportsScheduleKey,
    val guide: Map<String, IptvNowNext>,
    val refresh: Int,
    val retryOnOpen: Int,
) {
    // The guide is the whole in-memory EPG, and a big playlist hands over a new copy
    // every time a page of it arrives. Rescanning for each copy kept a TV busy scanning
    // about half of the time. The key already changes when the guide grows a lot
    // (guideCoverage), when the backfill ends, and every ten minutes (window), so a new
    // copy alone is not a new request; the next request carries the newest guide.
    override fun equals(other: Any?): Boolean = this === other || other is SportsScanRequest &&
        key == other.key && refresh == other.refresh && retryOnOpen == other.retryOnOpen

    override fun hashCode(): Int =
        (key.hashCode() * 31 + refresh) * 31 + retryOnOpen
}
internal data class SportsScanResult(val request: SportsScanRequest, val events: List<SportsGuideEvent>)

internal fun SportsScheduleKey.sameCatalogueScope(other: SportsScheduleKey): Boolean =
    profileId == other.profileId && providerId == other.providerId &&
        sourceVersion == other.sourceVersion && excludedGroups == other.excludedGroups

internal fun SportsGuideEvent.isVisibleSportsResult(now: Long): Boolean =
    hasChannels(now) && (isOnAir(now) || isScheduledNow(now) || programme.startUtcMillis > now)

internal fun displaySportsCatalogue(
    previous: List<SportsGuideEvent>,
    incoming: List<SportsGuideEvent>,
    complete: Boolean,
    now: Long,
): List<SportsGuideEvent> =
    if (!complete && previous.any { it.isVisibleSportsResult(now) }) previous else incoming

internal fun List<SportsGuideEvent>.filterSportsInstallations(allowed: Set<String>): List<SportsGuideEvent> {
    if (none { event -> event.addonSources.any { it.installation !in allowed } }) return this
    return map { event -> event.copy(addonSources = event.addonSources.filter { it.installation in allowed }) }
}

/**
 * A large playlist is indexed in stages. An empty in-memory guide therefore does
 * not mean that the provider has no sports; it can simply mean that the EPG index
 * has not reached the sports channels yet.
 */
internal fun shouldWaitForSportsGuide(
    indexedGuideChannelCount: Int,
    inMemoryGuideChannelCount: Int,
    largePlaylist: Boolean,
): Boolean = largePlaylist && indexedGuideChannelCount == 0 && inMemoryGuideChannelCount == 0

internal fun shouldShowSportsLoading(
    hasVisibleEvents: Boolean,
    workLoading: Boolean,
    hasCompletedScan: Boolean,
    scanFailed: Boolean,
): Boolean = !hasVisibleEvents && (workLoading || (!hasCompletedScan && !scanFailed))

/** Schedule facts, not stream probes. Channel identities remain provider-specific. */
@Immutable
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
    // A provider EPG can say that a sports channel is on air without naming a
    // specific fixture. Keep that source playable, but never present it as a
    // verified match.
    val channelOnly: Boolean = false,
    val addonSources: List<com.arflix.tv.data.model.SportsAddonEvent> = emptyList(),
) {
    /** Score feeds can be delayed briefly; do not drop a live event during a normal refresh gap. */
    private companion object {
        const val LIVE_STALE_MS = 15 * 60_000L
    }
    val identity: String = sportsEventIdentity(title)
    val hasEventArtwork: Boolean get() = !artwork.isNullOrBlank() ||
        (teamArtwork?.homeBadge != null && teamArtwork.awayBadge != null)
    fun isConfirmedLive(now: Long): Boolean {
        val liveFixture = fixture ?: return false
        return liveFixture.status == "live" && now >= liveFixture.observedAt &&
            now - liveFixture.observedAt < LIVE_STALE_MS
    }
    fun isOnAir(now: Long) = fixture?.status !in setOf("finished", "postponed") &&
        (isConfirmedLive(now) || addonSources.any { it.isLive(now) } || if (schedules.isEmpty()) programme.isLive(now) else schedules.values.any { it.isLive(now) })
    fun availableChannels(now: Long) = channels.filter { (schedules[it.id] ?: programme).isLive(now) }
    // A delayed status feed must not make an event disappear at its start time.
    // This is deliberately separate from LIVE; the broadcaster is not a stream probe.
    fun isScheduledNow(now: Long) = fixture?.status == "scheduled" && !isOnAir(now) &&
        now - programme.startUtcMillis in 0 until 4 * 60 * 60_000L
    fun hasChannels(now: Long) = (if (isOnAir(now)) availableChannels(now) else channels).isNotEmpty() || possibleChannels.isNotEmpty() || addonSources.isNotEmpty()
}

internal fun attachSportsArtwork(events: List<SportsGuideEvent>, artwork: List<SportsEventArtwork>,
    preferMetadata: Boolean = false): List<SportsGuideEvent> {
    val byTitle = artwork.groupBy { sportsEventIdentity(it.title) }
    return events.map { event ->
        val candidates = byTitle[event.identity].orEmpty().filter {
            val sport = GuideSport.fromText(it.genres.joinToString(" "))
            (sport == event.sport || (sport == null && !it.isScheduleMetadata)) &&
                sportsQualifierKey("${event.title} ${event.competition.orEmpty()}") == sportsQualifierKey("${it.title} ${it.fixture?.league.orEmpty()}") &&
                (it.startsAt == null || kotlin.math.abs(it.startsAt - event.programme.startUtcMillis) <=
                    (if (it.isScheduleMetadata) 2 else 6) * 60 * 60_000L)
        }
        val match = candidates.firstOrNull { it.homeBadge != null && it.awayBadge != null }
        val banner = candidates.firstOrNull { safeSportsImage(it.background) != null }
        if (preferMetadata) {
            val paid = candidates.filter { it.source == "TheSportsDB" }
            val preferredPair = paid.firstOrNull { it.homeBadge != null && it.awayBadge != null }
            val preferredBanner = paid.firstOrNull { safeSportsImage(it.background) != null }
            val pair = preferredPair ?: match
            val image = preferredBanner ?: banner.takeIf { preferredPair == null }
            if (image == null && pair == null) return@map event
            return@map event.copy(artwork = safeSportsImage(image?.background), teamArtwork = pair,
                artworkSource = image?.source ?: pair?.source)
        }
        event.copy(artwork = safeSportsImage(event.programme.artworkUrl) ?: candidates.firstNotNullOfOrNull { safeSportsImage(it.background) },
            teamArtwork = match, artworkSource = banner?.source ?: match?.source)
    }
}

internal enum class GuideSport(val title: String, val asset: String, val terms: Regex) {
    FOOTBALL("Football", "football", Regex("\\b(football|soccer|premier league|champions league|la liga|eredivisie|bundesliga)\\b")),
    BASKETBALL("Basketball", "basketball", Regex("\\b(basketball|nba|wnba|euroleague|ncaab|ncaam|ncaaw|college basketball|march madness)\\b")),
    F1("Formula 1", "motor_sports", Regex("\\b(f1|formula 1|formula one)\\b")),
    TENNIS("Tennis", "tennis", Regex("\\b(tennis|atp|wta|wimbledon)\\b")),
    MMA("MMA", "fight", Regex("\\b(mma|ufc|bellator|pfl|kick ?boxing|muay thai|k-1)\\b")),
    BOXING("Boxing", "fight", Regex("(?<!kick )\\b(boxing|boxen)\\b")),
    AMERICAN_FOOTBALL("American football", "american_football", Regex("\\b(american football|nfl|ncaa football|ncaaf|college football|cfb)\\b")),
    CRICKET("Cricket", "cricket", Regex("\\b(cricket|t20|t20i|odi|ipl|big bash|bbl|rcb|csk|kkr|srh|pbks|lsg)\\b")),
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
    HANDBALL("Handball", "other", Regex("\\b(handball)\\b")),
    OTHER("Other sports", "other", Regex("$^"));

    companion object {
        private val priority = listOf(AMERICAN_FOOTBALL, AUSTRALIAN_FOOTBALL, BASKETBALL, F1, MOTORSPORT, TENNIS, MMA, BOXING,
            CRICKET, BASEBALL, HOCKEY, RUGBY, GOLF, SNOOKER, DARTS, CYCLING, ATHLETICS, VOLLEYBALL, HANDBALL, FOOTBALL)
        private val anySport by lazy { Regex(priority.joinToString("|") { it.terms.pattern }) }
        fun fromText(text: String): GuideSport? {
            val value = text.lowercase(Locale.ROOT)
            // Most provider labels are not sports. One negative scan replaces twenty.
            if (!anySport.containsMatchIn(value)) return null
            // Specific football codes must win over the generic word football.
            return priority.firstOrNull { it.terms.containsMatchIn(value) }
        }
    }
}

/**
 * Playlist guides fill event channels with placeholders between games: "ENDED | ...",
 * "Next | ...", "- NO EVENT STREAMING -", "24/7 ...". Those are not games.
 */
internal val guidePlaceholder = Regex(
    "^\\W*(end(ed)?|finished|full ?time|next|up ?next|coming up|upcoming|starts? (at|soon)|no event|offline|closed)\\b|\\bno (live )?events?\\b|\\b24/7\\b|\\bevent (starts|begins)\\b|@\\s*(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)[a-z]*\\.?\\s+\\d{1,2}\\b|\\|\\s*all\\s*\\||\\b8k exclusive\\b|\\(\\d{4}-\\d{2}-\\d{2}[ t]\\d{1,2}:\\d{2}",
    RegexOption.IGNORE_CASE)

/** Event channels that are parked between games ("NO EVENT STREAMING", "No event"). */
internal val sportsPlaceholderChannel = Regex("\\bno (live )?events?\\b|\\bno stream", RegexOption.IGNORE_CASE)

private val channelPrefix = Regex("^\\s*[a-z]{2,3}\\s*[:|]\\s*", RegexOption.IGNORE_CASE)
private val channelQuality = Regex("\\b(hd|fhd|uhd|sd|4k|8k|hevc|raw|backup)\\b", RegexOption.IGNORE_CASE)
private val nonWord = Regex("[^\\p{L}\\p{N}]+")
private val leadingZeros = Regex("\\b0+(\\d)")
private fun channelText(value: String) = value.replace(channelPrefix, "").replace(channelQuality, " ")
    .lowercase(Locale.ROOT).replace(nonWord, " ").replace(leadingZeros, "$1").trim()

/**
 * A guide that only repeats the channel's own name ("IT: CHAMPIONS LEAGUE INFINITY 1 HD",
 * "BE: DAZN LIVE EVENT 11" on "DAZN LIVE EVENT 11", "Eleven Sport Cricket Live + Replica"
 * on "IT: Eleven Sport Cricket") is not a game.
 */
internal fun sportsTitleIsChannelName(title: String, channelName: String): Boolean {
    val a = channelText(title)
    val b = channelText(channelName)
    if (a.isEmpty() || b.isEmpty()) return false
    return a == b || (b.length >= 8 && a.startsWith("$b ")) || (a.length >= 8 && b.startsWith("$a "))
}

private val channelMatchup = Regex("\\s(?:vs?\\.?|versus|@)\\s", RegexOption.IGNORE_CASE)
private val stopWords = setOf("the", "and", "live", "vs", "fc", "sc", "university", "state", "college")
private fun teamWords(value: String) = channelText(value).split(' ').filter { it.length >= 3 && it !in stopWords }.toSet()
private val channelNumber = Regex("^(?:\\d{1,3}\\s*[-.:]?\\s+)(?=\\p{L})")
private val channelTail = Regex("\\s*(?:[(\\[|]|\\s@\\s|\\s-\\s|\\s\\d{1,2}[:.h]\\d{2}\\b).*$", RegexOption.IGNORE_CASE)
private val channelTailQuality = Regex("(?:\\s+(?:hd|fhd|uhd|sd|4k|8k|hevc|raw|backup|ᴿᴬᵂ|ᴴᴰ|ᶠᴴᴰ|ᵁᴴᴰ))+\\s*$", RegexOption.IGNORE_CASE)

/**
 * The game an event channel is named after: "US: VTSU_Castleton vs Eastern Nazarene" ->
 * "VTSU Castleton vs Eastern Nazarene". Null for channels that do not name one game, also
 * for a lone "V" in a brand ("SWE| V SPORT EXTRA"): both sides must name a team.
 */
internal fun sportsChannelMatchup(channelName: String): String? {
    val plain = channelName.replace('_', ' ').trim()
    val split = channelMatchup.find(plain) ?: return null
    val left = plain.substring(0, split.range.first)
        .substringAfterLast('|').substringAfterLast('·').substringAfterLast(':').trim()
        .replace(channelNumber, "").trim()
    val right = plain.substring(split.range.last + 1)
        .replace(channelTail, "").replace(channelTailQuality, "").trim()
    if (teamWords(left).isEmpty() || teamWords(right).isEmpty()) return null
    return "$left vs $right"
}

/**
 * An event channel named after one game ("VTSU_Castleton vs Eastern") whose guide shows
 * another game ("Sharjah vs Dubai") carries the game in its name; its guide is a shared
 * listing. That guide title must not be offered on this channel.
 */
internal fun sportsChannelNamesOtherEvent(title: String, channelName: String): Boolean {
    val named = sportsChannelMatchup(channelName) ?: return false
    val channelWords = teamWords(named)
    return channelWords.none { it in teamWords(title) }
}

private val listingMarker = Regex("\\|\\s*all\\s*\\||\\b8k exclusive\\b", RegexOption.IGNORE_CASE)
private val listingMatchup = Regex("\\s(?:vs?\\.?|versus|[-–—])\\s", RegexOption.IGNORE_CASE)
private val listingSportCode = Regex("^[a-z]{2,3}\\s*:\\s*([a-z]{2,4})$", RegexOption.IGNORE_CASE)
private val listingSportCodes = mapOf("soc" to "soccer", "fut" to "soccer", "bkb" to "basketball", "bsk" to "basketball",
    "hoc" to "hockey", "hky" to "hockey", "ten" to "tennis", "cri" to "cricket", "crk" to "cricket", "rug" to "rugby",
    "nfl" to "nfl", "nba" to "nba", "nhl" to "nhl", "mlb" to "mlb", "mma" to "mma", "box" to "boxing")

/**
 * Some providers list the game on their event channels between labels:
 * "Live | China - Great Britain | all | 8K EXCLUSIVE | ES: SOC". The game is the
 * matchup part. A listing without a matchup stays a placeholder (see [guidePlaceholder]).
 */
internal fun sportsListingProgramme(programme: IptvProgram): IptvProgram {
    val title = programme.title
    // Runs for every guide row of a big playlist: skip the regex for ordinary titles.
    if ((title.indexOf('|') < 0 && !title.contains("8k", ignoreCase = true)) || !listingMarker.containsMatchIn(title)) return programme
    val parts = programme.title.split('|').map { it.trim() }.filter { it.isNotEmpty() }
    val game = parts.firstOrNull { listingMatchup.containsMatchIn(it) } ?: return programme
    val sport = parts.firstNotNullOfOrNull { part ->
        listingSportCode.find(part)?.groupValues?.get(1)?.lowercase(Locale.ROOT)?.let { listingSportCodes[it] }
    }
    return programme.copy(title = game, category = listOfNotNull(programme.category, sport).joinToString(" ").ifBlank { null })
}

internal val nonEvent = Regex("\\b(highlights?|hoogtepunten|samenvatting|resumen|replay|replica|herhaling|wiederholung|repeticion|repetición|rediffusion|re-?run|classic|news|magazine|review|preview|cancelled|canceled|postponed|abandoned|sendepause|off air|no signal|best of|teleshopping|infomercial|documentary)\\b", RegexOption.IGNORE_CASE)
private val collegeRanking = Regex("(^|[\\s|:])#\\d{1,2}\\s+\\p{L}")
private val eventMatchup = Regex("\\s+(?:vs?\\.?|versus|at|[-–—])\\s+", RegexOption.IGNORE_CASE)
private val liveEventCue = Regex("\\b(live|on air|match|game|race|grand prix|qualifying|tournament|coverage|championship|event)\\b", RegexOption.IGNORE_CASE)
private val genericSportsChannel = Regex("\\b(sports?|espn|eurosport|dazn|bein sports?|sky sports?|bt sports?|tnt sports?|fox sports?|supersports?|sportsnet|star sports?|eleven sports?|willow)\\b", RegexOption.IGNORE_CASE)
private val space = Regex("\\s+")

/** Generic sports channels need an explicit live/event cue before their EPG is shown as an event. */
internal fun sportsChannelSport(text: String): GuideSport? =
    GuideSport.fromText(text) ?: GuideSport.OTHER.takeIf { genericSportsChannel.containsMatchIn(text) }

internal fun allowsChannelOnlyFallback(programme: IptvProgram, fallback: GuideSport?): Boolean =
    fallback == GuideSport.OTHER || liveEventCue.containsMatchIn(programme.title)

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
        var channelOnly = first.channelOnly
        fun add(event: SportsGuideEvent) {
            event.channels.forEach { channels.putIfAbsent(it.id, it) }
            schedules.putAll(event.schedules)
            artwork = artwork ?: event.programme.artworkUrl
            competition = competition ?: event.competition
            channelOnly = channelOnly && event.channelOnly
        }
        fun snapshot() = first.copy(channels = channels.values.toList(), schedules = schedules.toMap(),
            programme = first.programme.copy(artworkUrl = artwork), artwork = artwork,
            competition = competition, channelOnly = channelOnly)
    }
    private val groups = linkedMapOf<String, MutableList<Entry>>()
    fun add(event: SportsGuideEvent) {
        add(event.sport, event.identity, event.programme, event.channels, event.schedules, event.competition, event.channelOnly, event.artwork)
    }
    fun add(sport: GuideSport, identity: String, programme: IptvProgram, channel: IptvChannel, competition: String?) {
        if (sportsPlaceholderChannel.containsMatchIn(channel.name)) return
        val named = sportsChannelMatchup(channel.name)
        if (named != null) {
            // An event channel named after a game shows that game. Its guide often lists a
            // different one (a shared listing for all event channels); dropping those channels
            // took most live games off the page, so the name wins over the guide title.
            if (sportsChannelNamesOtherEvent(programme.title, channel.name)) addNamedEvent(named, programme, channel)
            else add(sport, identity, programme, listOf(channel), null, competition)
            return
        }
        if (sportsTitleIsChannelName(programme.title, channel.name)) return
        add(sport, identity, programme, listOf(channel), null, competition)
    }
    fun addChannelOnly(sport: GuideSport, programme: IptvProgram, channel: IptvChannel) {
        if (sportsPlaceholderChannel.containsMatchIn(channel.name)) return
        val named = sportsChannelMatchup(channel.name)
        if (named != null) {
            addNamedEvent(named, programme, channel)
            return
        }
        add(sport, sportsEventIdentity(programme.title), programme, listOf(channel), null, null,
            channelOnly = true, artwork = channel.logo)
    }
    // One game per named channel: the slot on air now, else the next one. Its guide repeats
    // the shared listing all day, and each later slot would add the same game again.
    private class Named(val title: String, val sport: GuideSport, val programme: IptvProgram, val channel: IptvChannel)
    private val named = linkedMapOf<String, Named>()
    private fun addNamedEvent(title: String, programme: IptvProgram, channel: IptvChannel) {
        val old = named[channel.id]
        if (old != null && old.programme.startUtcMillis <= programme.startUtcMillis) return
        // The shared listing's sport says nothing about this channel's game.
        val sport = GuideSport.fromText("${channel.group} ${channel.name}") ?: GuideSport.OTHER
        named[channel.id] = Named(title, sport, programme.copy(title = title, description = null, artworkUrl = null), channel)
    }
    private fun flushNamed() {
        if (named.isEmpty()) return
        named.values.forEach { add(it.sport, sportsEventIdentity(it.title), it.programme, listOf(it.channel), null, null) }
        named.clear()
    }
    private fun add(sport: GuideSport, identity: String, programme: IptvProgram,
        channels: List<IptvChannel>, schedules: Map<String, IptvProgram>?, competition: String?,
        channelOnly: Boolean = false, artwork: String? = null) {
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
            sport, programme, emptyList(), artwork = artwork, competition = competition, channelOnly = channelOnly)).also(group::add)
        channels.forEach { entry.channels.putIfAbsent(it.id, it) }
        if (schedules != null) entry.schedules.putAll(schedules)
        else channels.forEach { entry.schedules[it.id] = programme }
        entry.artwork = entry.artwork ?: programme.artworkUrl ?: artwork
        entry.competition = entry.competition ?: competition
    }
    fun events(): List<SportsGuideEvent> {
        flushNamed()
        return groups.values.flatMap { group -> group.map { it.snapshot() } }
    }
}

/** XMLTV variants share programme text. Classify it once per scan, with bounded memory. */
internal class SportsProgrammeResolver {
    data class Metadata(val sport: GuideSport, val identity: String, val competition: String?)
    private data class Key(val title: String, val category: String?, val fallback: GuideSport?)
    private val cache = object : LinkedHashMap<Key, Metadata?>(512, .75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Key, Metadata?>?) = size > 32_768
    }
    fun resolve(programme: IptvProgram, fallback: GuideSport?): Metadata? {
        val key = Key(programme.title, programme.category, fallback)
        val metadata = if (cache.containsKey(key)) cache[key] else classify(programme, fallback).also { cache[key] = it }
        // A team game is over in a few hours. A guide block of half a day or more with a
        // matchup title is an event channel's placeholder, not a game on now.
        return metadata?.takeUnless {
            (it.sport in timedTeamSports || (it.sport == GuideSport.OTHER && eventMatchup.containsMatchIn(programme.title))) &&
                programme.endUtcMillis - programme.startUtcMillis >= 6 * 60 * 60_000L
        }
    }
    private fun classify(programme: IptvProgram, fallback: GuideSport?): Metadata? {
        val metadata = if (nonEvent.containsMatchIn(programme.title) || guidePlaceholder.containsMatchIn(programme.title)) null else {
            val text = "${programme.category.orEmpty()} ${programme.title}"
            // Channel genre alone does not make advertising or downtime a sporting event.
            (GuideSport.fromText(text) ?: fallback?.takeIf {
                eventMatchup.containsMatchIn(programme.title) ||
                    (it == GuideSport.OTHER && liveEventCue.containsMatchIn(programme.title))
            }?.let { sport ->
                // "#8 Tennessee vs #1 Ohio State": poll rankings are US college sport, not
                // soccer, even on a channel filed under Football.
                if (sport == GuideSport.FOOTBALL && collegeRanking.containsMatchIn(programme.title)) GuideSport.AMERICAN_FOOTBALL else sport
            })
                ?.let { Metadata(it, sportsEventIdentity(programme.title), competition(text)) }
        }
        return metadata
    }
    private companion object {
        val timedTeamSports = setOf(GuideSport.FOOTBALL, GuideSport.BASKETBALL, GuideSport.AMERICAN_FOOTBALL, GuideSport.BASEBALL,
            GuideSport.HOCKEY, GuideSport.RUGBY, GuideSport.AUSTRALIAN_FOOTBALL, GuideSport.VOLLEYBALL, GuideSport.HANDBALL)
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
    indexedStarts: Map<String, Set<Long>> = emptyMap(),
) {
    val end = Instant.ofEpochMilli(now).atZone(zone).toLocalDate().plusDays(2)
        .atStartOfDay(zone).toInstant().toEpochMilli()
    for (channel in channels) {
        val slice = guide[channel.id] ?: continue
        val fallback = sportsChannelSport("${channel.group} ${channel.name}")
        val starts = indexedStarts[channel.id]
        val programmes = sportsUpcomingProgrammes(slice).map(::sportsListingProgramme)
            .distinctBy { Triple(it.title, it.startUtcMillis, it.endUtcMillis) }
        for (programme in programmes) {
            if (starts?.contains(programme.startUtcMillis) == true ||
                programme.endUtcMillis <= now || programme.startUtcMillis >= end ||
                programme.endUtcMillis <= programme.startUtcMillis || programme.title.isBlank()) continue
            val meta = resolver.resolve(programme, fallback)
            if (meta != null) events.add(meta.sport, meta.identity, programme, channel, meta.competition)
            else if (allowsChannelOnlyFallback(programme, fallback) && programme.isLive(now) &&
                !nonEvent.containsMatchIn(programme.title) && !guidePlaceholder.containsMatchIn(programme.title)) {
                // Providers often classify a channel as Football, ESPN, or Sports
                // while the current EPG entry contains only a show name. Keep the
                // live source discoverable without inventing a fixture.
                fallback?.let { events.addChannelOnly(it, programme, channel) }
            }
        }
    }
}

/**
 * When a growing guide should trigger a new sports scan. Small guides rescan every 64
 * channels; large ones only each time they grow by about half, so a 50,000-channel
 * playlist that fills its guide in the background is scanned a handful of times
 * instead of hundreds.
 */
internal fun sportsGuideSizeBucket(size: Int): Int {
    if (size < 1024) return size / 64
    val top = Integer.highestOneBit(size)
    return 16 + 2 * Integer.numberOfTrailingZeros(top) + if (size and (top shr 1) != 0) 1 else 0
}

internal fun sportsUpcomingProgrammes(slice: IptvNowNext): List<IptvProgram> =
    listOfNotNull(slice.now, slice.next, slice.later) + slice.upcoming

@Immutable
internal data class SportsGuideRow(val id: String, val title: String, val events: List<SportsGuideEvent>,
    // How many games the row stands for; a row can show fewer cards than that (see SPORTS_ROW_CAP).
    val total: Int = events.size)

/**
 * Compose requires keys to be unique within each lazy container. Provider data
 * can contain duplicate IDs while aliases are being merged, so preserve the
 * first ID and disambiguate later occurrences without using a random value.
 */
internal fun <T> disambiguatedLazyKeys(items: List<T>, identity: (T) -> String): List<String> {
    val occurrences = HashMap<String, Int>()
    return items.map { item ->
        val id = identity(item).ifBlank { "item" }
        val occurrence = occurrences[id] ?: 0
        occurrences[id] = occurrence + 1
        "$id#$occurrence"
    }
}

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
    val verifiedLive = live.filterNot { it.channelOnly }
    val liveChannels = live.filter { it.channelOnly }
    val scheduledNow = events.filter { it.isScheduledNow(now) }.sortedWith(
        compareByDescending<SportsGuideEvent> { it.prominence }.thenBy { it.programme.startUtcMillis })
    return buildList {
        // EPG has no viewer metrics. Never call this popularity or confirmed live sport.
        if (verifiedLive.isNotEmpty()) add(SportsGuideRow("featured", "Featured live", verifiedLive.take(8)))
        if (liveChannels.isNotEmpty()) add(SportsGuideRow("live-channels", "Live sports channels", liveChannels))
        val upcoming = events.filter { it.programme.startUtcMillis > now && !it.isOnAir(now) &&
            day.includes(it.programme.startUtcMillis, now, zone) }
            .sortedWith(compareByDescending<SportsGuideEvent> { it.prominence }.thenBy { it.programme.startUtcMillis }.thenBy { it.id })
        if (upcoming.isNotEmpty()) add(SportsGuideRow("upcoming", "Upcoming highlights", upcoming.take(8)))
        if (scheduledNow.isNotEmpty()) add(SportsGuideRow("scheduled-now", "Scheduled now", scheduledNow))
        GuideSport.entries.forEach { sport ->
            val items = verifiedLive.filter { it.sport == sport } + scheduledNow.filter { it.sport == sport } + upcoming.filter { it.sport == sport }.sortedBy { it.programme.startUtcMillis }
            if (items.isNotEmpty()) add(SportsGuideRow(sport.name, sport.title, items))
        }
    }
}
