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
private val titlePrefixes = listOf("football", "soccer", "basketball", "baseball", "tennis", "ice hockey", "american football", "boxing", "mma", "cricket")
internal fun sportsChannelKey(name: String): String {
    val plain = name.replace(channelQuality, "").replace("+", " plus ")
    val lower = plain.lowercase(java.util.Locale.ROOT)
    // Provider labels are mostly ASCII. Avoid Unicode normalization and the
    // event-title regex pipeline for every channel in a 50k-channel playlist.
    val special = lower.startsWith("live") || lower.contains("vs") || lower.contains("versus") || lower.contains("v.") ||
        titlePrefixes.any(lower::startsWith)
    var key = if (special || plain.any { it.code > 127 }) sportsArtworkKey(plain) else buildString(plain.length) {
        var separator = false
        for (c in lower) {
            if (c in 'a'..'z' || c in '0'..'9') {
                if (separator && isNotEmpty()) append(' ')
                append(c)
                separator = false
            } else separator = true
        }
    }
    if (key.contains("nowtv")) key = key.replace(channelPackage, "$1 ")
    if (key.contains("tnt sport")) key = key.replace(tntStation, "tnt sports")
    if (key.contains("bein")) key = key.replace(beinStation, "bein sports $1")
    if (key.contains("sports") || key.contains("espn")) key = key.replace(stationNumber, "$1 $2")
    return if (key.contains("  ") || key.endsWith(' ')) key.replace(channelSpaces, " ").trim() else key
}
private val broadcasterRegions = java.util.Locale.getISOCountries().associate { code ->
    val locale = java.util.Locale("", code)
    locale.getDisplayCountry(java.util.Locale.ENGLISH).lowercase(java.util.Locale.ROOT) to
        listOf(code.lowercase(java.util.Locale.ROOT), locale.isO3Country.lowercase(java.util.Locale.ROOT))
} + mapOf("united kingdom" to listOf("uk", "gb"), "united states" to listOf("us", "usa"),
    "netherlands" to listOf("nl", "nld"), "the netherlands" to listOf("nl", "nld"),
    "germany" to listOf("de", "ger"), "france" to listOf("fr"), "spain" to listOf("es"), "italy" to listOf("it"), "portugal" to listOf("pt"),
        "brazil" to listOf("br"), "australia" to listOf("au"), "canada" to listOf("ca"), "belgium" to listOf("be"), "switzerland" to listOf("ch"),
        "austria" to listOf("at"), "ireland" to listOf("ie"), "denmark" to listOf("dk", "dnk"), "sweden" to listOf("se"), "norway" to listOf("no"),
        "finland" to listOf("fi"), "poland" to listOf("pl"), "romania" to listOf("ro"), "turkey" to listOf("tr"), "india" to listOf("in"),
        "argentina" to listOf("ar"), "mexico" to listOf("mx"), "south africa" to listOf("za"), "new zealand" to listOf("nz"),
        "saudi arabia" to listOf("sa"), "united arab emirates" to listOf("ae", "uae"))
internal fun sportsBroadcasterKeys(name: String, country: String): List<String> {
    val countryKey = country.trim().lowercase(java.util.Locale.ROOT)
    val codes = broadcasterRegions[countryKey].orEmpty()
    val key = sportsChannelKey(name)
    // TV listings are inconsistent about country labels: the API may say
    // "The Netherlands" while the channel name says "Netherlands". Strip any
    // known spelling of the supplied country, but never another region or a
    // channel number, so the generated local aliases remain precise.
    val countryNames = buildList {
        if (countryKey.isNotBlank()) add(countryKey)
        if (countryKey == "the netherlands") add("netherlands")
        if (countryKey == "united states") addAll(listOf("us", "usa"))
        if (countryKey == "united kingdom") addAll(listOf("uk", "gb"))
        addAll(codes)
    }.distinct().sortedByDescending { it.length }
    val localName = countryNames
        .asSequence()
        .map { " ${sportsArtworkKey(it)}" }
        .firstOrNull { key.endsWith(it) }
        ?.let { key.removeSuffix(it).trim() }
        ?.takeIf { it.isNotBlank() }
        ?: key
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
    if (sport != GuideSport.FOOTBALL) return listOf(key)
    // Keep disambiguators such as AC; only strip legal club affixes.
    val short = key.replace(Regex("^(?:(?:fc|cf|club|clube)\\s+)+"), "")
        .replace(Regex("(?:\\s+(?:fc|cf|afc))+$"), "")
    return (listOf(key, short) + footballAliases.firstOrNull { short in it }.orEmpty()).distinct().filter { it.length >= 3 }
}

private const val FIXTURE_MATCH_HOUR_MS = 3_600_000L
private const val FIXTURE_MATCH_WINDOW_MS = 2 * FIXTURE_MATCH_HOUR_MS

/** Narrow participant matching by time without changing source order or match rules. */
internal class SportsGuideTimeIndex(guide: List<SportsGuideEvent>) {
    private val buckets = guide.withIndex().groupBy {
        it.value.sport to Math.floorDiv(it.value.programme.startUtcMillis, FIXTURE_MATCH_HOUR_MS)
    }

    fun matching(sport: GuideSport, startsAt: Long, includes: (SportsGuideEvent) -> Boolean): List<SportsGuideEvent> {
        val start = Math.floorDiv(startsAt - FIXTURE_MATCH_WINDOW_MS, FIXTURE_MATCH_HOUR_MS)
        val end = Math.floorDiv(startsAt + FIXTURE_MATCH_WINDOW_MS, FIXTURE_MATCH_HOUR_MS)
        val matches = ArrayList<IndexedValue<SportsGuideEvent>>()
        for (bucket in start..end) {
            for (entry in buckets[sport to bucket].orEmpty()) {
                if (kotlin.math.abs(entry.value.programme.startUtcMillis - startsAt) <= FIXTURE_MATCH_WINDOW_MS &&
                    includes(entry.value)) matches.add(entry)
            }
        }
        return matches.sortedBy { it.index }.map { it.value }
    }
}

internal fun buildSportsCatalogue(guide: List<SportsGuideEvent>, artwork: List<SportsEventArtwork>, channels: List<IptvChannel>, now: Long,
    zone: ZoneId = ZoneId.systemDefault()): List<SportsGuideEvent> {
    val fixtures = artwork.filter { it.fixture != null && it.startsAt != null }
    if (fixtures.isEmpty()) return attachSportsArtwork(guide, artwork)
    val byIdentity = guide.groupBy { "${it.sport}|${it.identity}" }
    val guideTitles = HashMap<String, String>()
    val guideQualifiers = HashMap<String, String>()
    val timeIndex = SportsGuideTimeIndex(guide)
    val byChannel = channels.groupBy { sportsChannelKey(it.name) }
    val broadcasterMatches = hashMapOf<Pair<String, String>, List<IptvChannel>>()
    val used = hashSetOf<String>()
    val seen = hashSetOf<String>()
    val until = Instant.ofEpochMilli(now).atZone(zone).toLocalDate().plusDays(2).atStartOfDay(zone).toInstant().toEpochMilli()
    val output = fixtures.mapNotNull { item ->
        val fixture = item.fixture!!; val start = item.startsAt!!
        if (!seen.add(fixture.id) || start >= until || start < now - 86_400_000) return@mapNotNull null
        val sport = GuideSport.fromText("${item.genres.joinToString(" ")} ${fixture.league.orEmpty()}") ?: GuideSport.OTHER
        val home = participantKeys(item.homeTeam, sport)
        val away = participantKeys(item.awayTeam, sport)
        // Both complete participant names must be present; a league or one team is not enough.
        val candidates = if (home.isNotEmpty() && away.isNotEmpty() && home.none { it in away }) {
            val homeTerms = home.map { " $it " }
            val awayTerms = away.map { " $it " }
            timeIndex.matching(sport, start) { event ->
                val title = guideTitles.getOrPut(event.id) { " ${sportsArtworkKey(event.title)} " }
                homeTerms.any { title.contains(it) } && awayTerms.any { title.contains(it) }
            }
        } else emptyList()
        val qualifier = sportsQualifierKey("${item.title} ${fixture.league.orEmpty()}")
        val matches = (byIdentity["$sport|${sportsEventIdentity(item.title)}"].orEmpty() + candidates).distinctBy { it.id }.filter { event ->
            event.id !in used && kotlin.math.abs(event.programme.startUtcMillis - start) <= FIXTURE_MATCH_WINDOW_MS &&
                guideQualifiers.getOrPut(event.id) { sportsQualifierKey("${event.title} ${event.competition.orEmpty()}") } == qualifier &&
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
