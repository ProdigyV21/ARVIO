package com.arflix.tv.data.model

import com.google.gson.JsonParser

data class SportsBroadcaster(val name: String, val country: String, val startsAt: Long)
data class SportsFixture(
    val id: String, val league: String?, val qualifier: String?, val venue: String?, val round: String?,
    val status: String, val observedAt: Long, val homeScore: Int?, val awayScore: Int?,
    val broadcasters: List<SportsBroadcaster>,
)

/** Public metadata DTO; the provider API key exists only in the backend. */
fun parseSportsMetadata(body: String): List<SportsEventArtwork> {
    val root = runCatching { JsonParser.parseString(body).asJsonObject }.getOrNull() ?: return emptyList()
    if (runCatching { root.get("version")?.asInt }.getOrNull() != 1) return emptyList()
    val events = runCatching { root.getAsJsonArray("events") }.getOrNull() ?: return emptyList()
    return events.take(6000).mapNotNull { value ->
        runCatching {
            val item = value.asJsonObject
            fun text(key: String) = item.get(key)?.takeUnless { it.isJsonNull }?.asString
            val title = text("title")?.takeIf { it.isNotBlank() } ?: return@runCatching null
            val sport = text("sport")?.takeIf { it.isNotBlank() } ?: return@runCatching null
            val start = item.get("startsAt")?.asLong?.takeIf { it > 0 } ?: return@runCatching null
            val background = safeSportsImage(text("background"))
            val home = safeSportsImage(text("homeBadge"))
            val away = safeSportsImage(text("awayBadge"))
            val catalogueEnabled = root.get("catalogueEnabled")?.asBoolean == true
            val fixture = if (catalogueEnabled && text("id")?.matches(Regex("\\d+")) == true) SportsFixture(
                id = text("id")!!, league = text("league"), qualifier = text("qualifier"), venue = text("venue"), round = text("round"),
                status = text("status") ?: "scheduled", observedAt = item.get("observedAt")?.asLong ?: 0L,
                homeScore = text("homeScore")?.toIntOrNull(), awayScore = text("awayScore")?.toIntOrNull(),
                broadcasters = item.getAsJsonArray("broadcasters")?.take(1500)?.mapNotNull { raw -> runCatching {
                    val b = raw.asJsonObject
                    SportsBroadcaster(b.get("name").asString, b.get("country").asString, b.get("startsAt").asLong)
                }.getOrNull() }.orEmpty(),
            ) else null
            if (fixture == null && background == null && (home == null || away == null)) return@runCatching null
            SportsEventArtwork(title, background.orEmpty(), listOf(sport), start,
                homeBadge = if (away != null) home else null, awayBadge = if (home != null) away else null,
                homeTeam = text("homeTeam"), awayTeam = text("awayTeam"), source = "TheSportsDB", fixture = fixture)
        }.getOrNull()
    }
}
