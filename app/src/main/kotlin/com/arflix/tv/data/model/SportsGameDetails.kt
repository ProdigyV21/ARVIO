package com.arflix.tv.data.model

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser

/** One period of a game: a half, quarter, period or inning. */
data class SportsPeriodScore(val label: String, val home: Int, val away: Int)

data class SportsStandingRow(
    val rank: Int,
    val team: String,
    val played: Int?,
    val won: Int?,
    val drawn: Int?,
    val lost: Int?,
    val difference: Int?,
    val points: Int?,
    val highlight: Boolean,
)

data class SportsPastMatch(val date: String, val homeTeam: String, val awayTeam: String, val homeScore: Int, val awayScore: Int)

/**
 * The game page beyond the schedule: score per period, the competition table,
 * both teams' last results and earlier meetings. Every part may be empty.
 */
data class SportsGameDetails(
    val status: String,
    val periods: List<SportsPeriodScore>,
    val standingsTitle: String?,
    val standings: List<SportsStandingRow>,
    val homeForm: List<String>,
    val awayForm: List<String>,
    val headToHead: List<SportsPastMatch>,
) {
    val isEmpty: Boolean get() = periods.isEmpty() && standings.isEmpty() && homeForm.isEmpty() && awayForm.isEmpty() && headToHead.isEmpty()
}

private fun JsonObject.text(key: String): String? =
    get(key)?.takeIf { it.isJsonPrimitive }?.asString?.trim()?.takeIf { it.isNotEmpty() }?.take(120)

private fun JsonObject.int(key: String): Int? =
    get(key)?.takeIf { it.isJsonPrimitive }?.let { runCatching { it.asDouble.toInt() }.getOrNull() }

private fun JsonElement.objects(limit: Int): List<JsonObject> =
    if (isJsonArray) asJsonArray.take(limit).mapNotNull { it.takeIf { e -> e.isJsonObject }?.asJsonObject } else emptyList()

private val formResult = setOf("W", "D", "L")
private val isoDay = Regex("^\\d{4}-\\d{2}-\\d{2}$")

fun parseSportsGameDetails(body: String): SportsGameDetails? = runCatching {
    val root = JsonParser.parseString(body).asJsonObject
    if (root.int("version") != 1) return null
    val form = root.getAsJsonObject("form")
    fun results(side: String) = form?.get(side)?.takeIf { it.isJsonArray }?.asJsonArray?.take(5)
        ?.mapNotNull { e -> e.takeIf { it.isJsonPrimitive }?.asString?.takeIf { it in formResult } }.orEmpty()
    SportsGameDetails(
        status = root.text("status") ?: "scheduled",
        periods = root.get("periods")?.objects(20).orEmpty().mapNotNull { p ->
            val label = p.text("label")?.take(6) ?: return@mapNotNull null
            SportsPeriodScore(label, p.int("home") ?: return@mapNotNull null, p.int("away") ?: return@mapNotNull null)
        },
        standingsTitle = root.text("standingsTitle"),
        standings = root.get("standings")?.objects(40).orEmpty().mapIndexedNotNull { index, row ->
            SportsStandingRow(row.int("rank") ?: (index + 1), row.text("team") ?: return@mapIndexedNotNull null,
                row.int("played"), row.int("won"), row.int("drawn"), row.int("lost"), row.int("difference"), row.int("points"),
                row.get("highlight")?.takeIf { it.isJsonPrimitive }?.asBoolean == true)
        },
        homeForm = results("home"),
        awayForm = results("away"),
        headToHead = root.get("headToHead")?.objects(5).orEmpty().mapNotNull { m ->
            SportsPastMatch(m.text("date")?.takeIf { it.matches(isoDay) } ?: return@mapNotNull null,
                m.text("homeTeam") ?: return@mapNotNull null, m.text("awayTeam") ?: return@mapNotNull null,
                m.int("homeScore") ?: return@mapNotNull null, m.int("awayScore") ?: return@mapNotNull null)
        },
    )
}.getOrNull()

/**
 * The standings rows worth showing on a small sheet: the whole table when it is
 * short, otherwise the rows around both teams.
 */
fun List<SportsStandingRow>.aroundHighlighted(limit: Int = 8): List<SportsStandingRow> {
    if (size <= limit) return this
    val marked = withIndex().filter { it.value.highlight }.map { it.index }
    if (marked.isEmpty()) return take(limit)
    val keep = sortedSetOf<Int>()
    marked.forEach { i -> (i - 1..i + 1).filter { it in indices }.forEach(keep::add) }
    var cursor = 0
    while (keep.size < limit && cursor < size) keep.add(cursor++)
    return keep.take(limit).sorted().map { this[it] }
}
