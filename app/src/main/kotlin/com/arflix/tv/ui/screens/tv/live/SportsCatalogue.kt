package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsEventArtwork
import com.arflix.tv.data.model.sportsEventIdentity
import com.arflix.tv.data.model.sportsArtworkKey
import com.arflix.tv.data.model.sportsQualifierKey
import java.time.Instant
import java.time.ZoneId

/** Editorial priority plus broadcast reach, never a claim of measured viewers. */
internal fun sportsProminence(league: String?, countries: Int = 0): Int {
    val name = league.orEmpty().lowercase(java.util.Locale.ROOT)
    val major = setOf("uefa champions league", "english premier league", "premier league", "spanish la liga", "la liga", "nba", "nfl", "formula 1", "ufc", "fifa world cup")
    val featured = setOf("italian serie a", "serie a", "german bundesliga", "bundesliga", "french ligue 1", "ligue 1", "nhl", "mlb", "wimbledon", "us open", "atp us open", "wta us open", "indian premier league")
    return (if (name in major) 200 else if (name in featured) 100 else 0) + countries.coerceIn(0, 50) * 2
}

private val channelQuality = Regex("\\b(uhd|fhd|hd|sd|4k|8k|hevc|h[.]?265|h[.]?264|1080p|720p|2160p|(?:25|30|50|60)fps|raw|backup)\\b", RegexOption.IGNORE_CASE)
private val channelPackage = Regex("^([a-z]{2,3})\\s+nowtv\\s+")
private val tntStation = Regex("\\btnt sport\\b")
private val beinStation = Regex("\\bbein\\s*sports?\\s*(\\d*)")
private val stationNumber = Regex("\\b(sports|espn)(\\d+)\\b")
private val channelSpaces = Regex("\\s+")
internal fun sportsChannelKey(name: String) = sportsArtworkKey(name.replace(channelQuality, ""))
    .replace(channelPackage, "$1 ")
    .replace(tntStation, "tnt sports").replace(beinStation, "bein sports $1")
    .replace(stationNumber, "$1 $2").replace(channelSpaces, " ").trim()
private val broadcasterRegions = mapOf("united kingdom" to listOf("uk", "gb"), "united states" to listOf("us", "usa"), "netherlands" to listOf("nl", "nld"),
        "germany" to listOf("de", "ger"), "france" to listOf("fr"), "spain" to listOf("es"), "italy" to listOf("it"), "portugal" to listOf("pt"),
        "brazil" to listOf("br"), "australia" to listOf("au"), "canada" to listOf("ca"), "belgium" to listOf("be"), "switzerland" to listOf("ch"),
        "austria" to listOf("at"), "ireland" to listOf("ie"), "denmark" to listOf("dk", "dnk"), "sweden" to listOf("se"), "norway" to listOf("no"),
        "finland" to listOf("fi"), "poland" to listOf("pl"), "romania" to listOf("ro"), "turkey" to listOf("tr"), "india" to listOf("in"),
        "argentina" to listOf("ar"), "mexico" to listOf("mx"), "south africa" to listOf("za"), "new zealand" to listOf("nz"),
        "saudi arabia" to listOf("sa"), "united arab emirates" to listOf("ae", "uae"))
internal fun sportsBroadcasterKeys(name: String, country: String): List<String> {
    val codes = broadcasterRegions[country.trim().lowercase(java.util.Locale.ROOT)].orEmpty()
    val key = sportsChannelKey(name)
    // TV listings often include the country in the name, e.g. ESPN 3 Netherlands.
    // Strip only the explicitly supplied country, never another region or channel number.
    val countrySuffix = " ${sportsArtworkKey(country)}"
    val localName = if (country.isNotBlank() && key.endsWith(countrySuffix)) key.removeSuffix(countrySuffix) else key
    return (listOf(key, localName) + codes.flatMap { listOf("$it $localName", "$localName $it") }).distinct()
}
private fun leagueKey(name: String): String {
    val key = sportsArtworkKey(name)
    return if (key in setOf("english premier league", "spanish la liga", "italian serie a", "german bundesliga", "french ligue 1")) key.substringAfter(' ') else key
}

private val footballAliases = listOf(
    listOf("manchester united", "man utd"), listOf("manchester city", "man city"),
    listOf("paris saint germain", "paris sg", "psg"), listOf("bayern munich", "bayern munchen"),
    listOf("internazionale", "inter milan"), listOf("atletico madrid", "atl madrid"),
)
private fun participantKeys(name: String?, sport: GuideSport): List<String> {
    val key = sportsArtworkKey(name.orEmpty())
    if (key.length < 3) return emptyList()
    return if (sport == GuideSport.FOOTBALL) footballAliases.firstOrNull { key in it } ?: listOf(key) else listOf(key)
}

internal fun buildSportsCatalogue(guide: List<SportsGuideEvent>, artwork: List<SportsEventArtwork>, channels: List<IptvChannel>, now: Long,
    zone: ZoneId = ZoneId.systemDefault()): List<SportsGuideEvent> {
    val fixtures = artwork.filter { it.fixture != null && it.startsAt != null }
    if (fixtures.isEmpty()) return attachSportsArtwork(guide, artwork)
    val byIdentity = guide.groupBy { "${it.sport}|${it.identity}" }
    val guideTitles = guide.associate { it.id to " ${sportsArtworkKey(it.title)} " }
    val bySport = guide.groupBy { it.sport }
    val byChannel = channels.groupBy { sportsChannelKey(it.name) }
    val broadcasterMatches = hashMapOf<Pair<String, String>, List<IptvChannel>>()
    val used = hashSetOf<String>()
    val seen = hashSetOf<String>()
    val until = Instant.ofEpochMilli(now).atZone(zone).toLocalDate().plusDays(2).atStartOfDay(zone).toInstant().toEpochMilli()
    val output = fixtures.mapNotNull { item ->
        val fixture = item.fixture!!; val start = item.startsAt!!
        val sport = GuideSport.fromText(item.genres.joinToString(" ")) ?: return@mapNotNull null
        if (!seen.add(fixture.id) || start >= until || start < now - 86_400_000) return@mapNotNull null
        val home = participantKeys(item.homeTeam, sport)
        val away = participantKeys(item.awayTeam, sport)
        // Both complete participant names must be present; a league or one team is not enough.
        val candidates = if (home.isNotEmpty() && away.isNotEmpty() && home.none { it in away })
            bySport[sport].orEmpty().filter { event -> home.any { guideTitles.getValue(event.id).contains(" $it ") } && away.any { guideTitles.getValue(event.id).contains(" $it ") } }
            else emptyList()
        val matches = (byIdentity["$sport|${sportsEventIdentity(item.title)}"].orEmpty() + candidates).distinctBy { it.id }.filter { event ->
            event.id !in used && kotlin.math.abs(event.programme.startUtcMillis - start) <= 2 * 3600_000L &&
                sportsQualifierKey("${event.title} ${event.competition.orEmpty()}") == sportsQualifierKey("${item.title} ${fixture.league.orEmpty()}") &&
                (fixture.qualifier == null || "${event.title} ${event.competition.orEmpty()}".contains(fixture.qualifier, true)) &&
                (event.competition == null || fixture.league == null || leagueKey(event.competition) == leagueKey(fixture.league))
        }
        matches.forEach { used.add(it.id) }
        val mapped = matches.flatMap { it.channels }.distinctBy { it.id }
        val mappedIds = mapped.mapTo(hashSetOf()) { it.id }
        val possible = fixture.broadcasters.filter { kotlin.math.abs(it.startsAt - start) < 2 * 3600_000L }
            .distinctBy { it.name to it.country }
            .flatMap { b -> broadcasterMatches.getOrPut(b.name to b.country) {
                sportsBroadcasterKeys(b.name, b.country).flatMap { byChannel[it].orEmpty() }
            } }.filter { it.id !in mappedIds }.distinctBy { it.id }
        // Finished metadata suppresses a stale EPG entry; it must not reappear as a fallback.
        if (fixture.status in setOf("finished", "postponed")) return@mapNotNull null
        SportsGuideEvent(id = "sportsdb:${fixture.id}", title = item.title, sport = sport,
            programme = IptvProgram(title = item.title, startUtcMillis = start, endUtcMillis = start),
            channels = mapped, schedules = matches.flatMap { it.schedules.entries }.associate { it.toPair() },
            artwork = item.background.takeIf { it.isNotBlank() } ?: matches.firstNotNullOfOrNull { it.artwork },
            teamArtwork = item.takeIf { it.homeBadge != null && it.awayBadge != null }, artworkSource = "TheSportsDB",
            competition = fixture.league, fixture = fixture, possibleChannels = possible,
            prominence = sportsProminence(fixture.league, fixture.broadcasters.map { it.country }.filter { it.isNotBlank() }.distinct().size))
    }
    return output + attachSportsArtwork(guide.filter { it.id !in used }, artwork)
}
