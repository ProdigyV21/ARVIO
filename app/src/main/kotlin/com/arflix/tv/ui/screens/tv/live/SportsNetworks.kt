package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsFollow
import com.arflix.tv.data.model.sportsFollowKey
import java.time.Instant
import java.time.ZoneId

/*
 * League networks, remembered channels and channel failover for the Sports page.
 * Pure functions, shared by the Sports page, Live TV and their tests.
 */

/** A channel brand that carries one league or sport, found by name in the user's own playlists. */
internal data class SportsNetwork(val id: String, val label: String, val sport: GuideSport?, val prefixes: List<String>)

internal val SPORTS_NETWORKS = listOf(
    SportsNetwork("nba-tv", "NBA TV", GuideSport.BASKETBALL, listOf("nba tv")),
    SportsNetwork("nba-league-pass", "NBA League Pass", GuideSport.BASKETBALL, listOf("nba league pass")),
    SportsNetwork("nfl-network", "NFL Network", GuideSport.AMERICAN_FOOTBALL, listOf("nfl network")),
    SportsNetwork("nfl-redzone", "NFL RedZone", GuideSport.AMERICAN_FOOTBALL, listOf("nfl redzone", "redzone")),
    SportsNetwork("nhl-network", "NHL Network", GuideSport.HOCKEY, listOf("nhl network")),
    SportsNetwork("mlb-network", "MLB Network", GuideSport.BASEBALL, listOf("mlb network")),
    SportsNetwork("premier-league", "Sky Sports Premier League", GuideSport.FOOTBALL, listOf("sky sports premier league", "premier league tv")),
    SportsNetwork("sky-football", "Sky Sports Football", GuideSport.FOOTBALL, listOf("sky sports football")),
    SportsNetwork("laliga", "LaLiga TV", GuideSport.FOOTBALL, listOf("laliga tv", "la liga tv", "movistar laliga", "m laliga", "dazn laliga")),
    SportsNetwork("bundesliga", "Sky Sport Bundesliga", GuideSport.FOOTBALL, listOf("sky sport bundesliga")),
    SportsNetwork("ziggo-sport", "Ziggo Sport", null, listOf("ziggo sport")),
    SportsNetwork("sky-f1", "Sky Sports F1", GuideSport.F1, listOf("sky sports f1", "f1 tv")),
    SportsNetwork("tnt-sports", "TNT Sports", null, listOf("tnt sports")),
    SportsNetwork("espn", "ESPN", null, listOf("espn")),
    SportsNetwork("dazn", "DAZN", null, listOf("dazn")),
    SportsNetwork("bein", "beIN Sports", null, listOf("bein sports")),
    SportsNetwork("eurosport", "Eurosport", null, listOf("eurosport")),
    SportsNetwork("viaplay", "Viaplay Sports", null, listOf("viaplay sport", "v sport")),
    SportsNetwork("canal-sport", "Canal+ Sport", null, listOf("canal plus sport", "canal sport")),
    SportsNetwork("fox-sports", "Fox Sports", null, listOf("fox sports", "fs1", "fs2")),
    SportsNetwork("tsn", "TSN", null, listOf("tsn")),
    SportsNetwork("sportsnet", "Sportsnet", null, listOf("sportsnet")),
    SportsNetwork("willow", "Willow", GuideSport.CRICKET, listOf("willow")),
    SportsNetwork("golf", "Golf Channel", GuideSport.GOLF, listOf("golf channel", "sky sports golf")),
    SportsNetwork("tennis", "Tennis Channel", GuideSport.TENNIS, listOf("tennis channel")),
    SportsNetwork("ufc", "UFC Fight Pass", GuideSport.MMA, listOf("ufc fight pass", "ufc tv")),
)

/** At most this many channels per network: HD, FHD and backup variants of one brand. */
internal const val SPORTS_NETWORK_CHANNELS = 6

private val networkCountryPrefix = Regex("^(?:uk|gb|us|usa|ca|au|nz|ie|nl|be|de|at|ch|fr|es|it|pt|br|ar|mx|in|za|tr|pl|se|no|dk|fi|ro|ar|ae|sa|en|int) ")

/** The network a normalized channel name ([sportsChannelKey]) belongs to, or null. */
internal fun sportsNetworkFor(channelKey: String): SportsNetwork? {
    val name = channelKey.replace(networkCountryPrefix, "")
    return SPORTS_NETWORKS.firstOrNull { network ->
        network.prefixes.any { prefix -> name == prefix || name.startsWith("$prefix ") }
    }
}

private val leadingLabel = Regex("^[^:|\u00b7]{1,24}[:|\u00b7]\\s*")

/**
 * The network of a raw channel name. Playlists put labels in front, such as
 * "Strong8K · CA EN: NBA TV HD" or "US| ESPN", so each leading label is dropped
 * in turn until the rest names a network.
 */
internal fun sportsNetworkForChannel(name: String): SportsNetwork? {
    var rest = name.trim()
    repeat(4) {
        sportsNetworkFor(sportsChannelKey(rest))?.let { return it }
        val next = rest.replaceFirst(leadingLabel, "")
        if (next == rest || next.isBlank()) return null
        rest = next
    }
    return sportsNetworkFor(sportsChannelKey(rest))
}

/**
 * One tile per network that the playlist has, as a channel-only Sports event so
 * the Sports page can show it in a row and open its channel list. Networks of a
 * followed sport come first.
 */
internal fun sportsNetworkEvents(
    channelsByNetwork: Map<String, List<IptvChannel>>,
    follows: Collection<SportsFollow>,
    now: Long,
    zone: ZoneId = ZoneId.systemDefault(),
): List<SportsGuideEvent> {
    val start = Instant.ofEpochMilli(now).atZone(zone).toLocalDate().atStartOfDay(zone).toInstant().toEpochMilli()
    val followedSports = follows.mapNotNull { follow -> follow.sport?.let { s ->
        // Android stores the enum name ("AMERICAN_FOOTBALL"), the web app its id ("american-football").
        GuideSport.entries.firstOrNull { it.name.equals(s.replace('-', '_'), ignoreCase = true) || it.title == s }
    } }.toSet()
    return SPORTS_NETWORKS.mapNotNull { network ->
        val channels = channelsByNetwork[network.id].orEmpty().distinctBy { it.id }.take(SPORTS_NETWORK_CHANNELS)
        if (channels.isEmpty()) return@mapNotNull null
        val programme = IptvProgram(title = network.label, startUtcMillis = start, endUtcMillis = start + 2 * 86_400_000L)
        SportsGuideEvent(id = "$SPORTS_NETWORK_PREFIX${network.id}", title = network.label, sport = network.sport ?: GuideSport.OTHER,
            // Playlists often give network channels the wrong logo, so the tile shows the name.
            programme = programme, channels = channels, artwork = null,
            channelOnly = true)
    }.sortedByDescending { it.sport in followedSports }
}

internal const val SPORTS_NETWORK_PREFIX = "network:"
internal const val SPORTS_ROW_NETWORKS = "league-networks"
internal val SportsGuideEvent.isNetwork: Boolean get() = id.startsWith(SPORTS_NETWORK_PREFIX)

// ── Remembered channels ───────────────────────────────────────────────────

/** What a channel choice is remembered under: the competition, or the network tile. */
internal fun SportsGuideEvent.channelMemoryKeys(): List<String> = buildList {
    if (isNetwork) add(id)
    (competition ?: fixture?.league)?.takeIf { it.isNotBlank() }?.let { add("league:${sportsFollowKey(it, league = true)}") }
}

/** The channel last chosen for this game's competition (or network) first, then the rest in their usual order. */
internal fun List<IptvChannel>.withRememberedFirst(memory: Map<String, String>, keys: List<String>): List<IptvChannel> {
    if (memory.isEmpty() || keys.isEmpty() || size < 2) return this
    val preferred = keys.mapNotNull { memory[it] }.distinct()
    if (preferred.isEmpty()) return this
    val rank = preferred.withIndex().associate { it.value to it.index }
    return sortedBy { rank[it.id] ?: Int.MAX_VALUE }
}

// ── Failover ──────────────────────────────────────────────────────────────

/**
 * A game started from Sports, with the other channels that show it. When the
 * playing channel fails for good, Live TV moves on to the next one not tried.
 */
internal data class SportsFailover(
    val eventId: String,
    val title: String,
    val channels: List<IptvChannel>,
    val tried: Set<String> = emptySet(),
) {
    fun covers(channelId: String?): Boolean = channelId != null && channels.any { it.id == channelId }

    /** The next channel after [failedId], or null when every channel was tried. */
    fun next(failedId: String): Pair<IptvChannel, SportsFailover>? {
        val tried = tried + failedId
        val start = channels.indexOfFirst { it.id == failedId }.coerceAtLeast(0)
        val ordered = channels.drop(start + 1) + channels.take(start)
        val channel = ordered.firstOrNull { it.id !in tried } ?: return null
        return channel to copy(tried = tried)
    }
}

/**
 * The failover for any channel: its other copies (same guide id or name) from
 * every playlist, the failed one first. Null when there is no other copy.
 */
internal fun channelCopyFailover(failed: IptvChannel, copies: List<IptvChannel>): SportsFailover? {
    val channels = (listOf(failed) + copies).distinctBy { it.id }.take(8)
    return if (channels.size > 1) SportsFailover("channel:${failed.id}", failed.name, channels) else null
}

/** The failover for a channel chosen on a game's sheet: the chosen one first, then the others. */
internal fun sportsFailoverFor(event: SportsGuideEvent, chosen: IptvChannel, candidates: List<IptvChannel>): SportsFailover? {
    val channels = (listOf(chosen) + candidates).distinctBy { it.id }.take(8)
    return SportsFailover(event.id, event.title, channels).takeIf { channels.size > 1 || !event.isNetwork }
}
