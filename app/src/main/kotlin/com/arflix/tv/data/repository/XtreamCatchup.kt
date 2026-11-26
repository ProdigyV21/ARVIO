package com.arflix.tv.data.repository

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.time.DateTimeException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/** Answers that mean "slow down", the only ones that end a catch-up scan early. */
internal val CATCHUP_RATE_LIMIT_STATUSES = setOf(429, 503, 513)

private val XTREAM_MINUTE: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm")
private val XTREAM_SECOND: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm-ss")
private val XTREAM_SPACE_SECOND: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
private val XTREAM_SPACE_MINUTE: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

/**
 * The wall-clock start times a panel may expect for a timeshift request, most
 * likely first. Xtream panels read the start in their own time zone:
 * 1. the zone the panel reports in `server_info.timezone`,
 * 2. the offset seen between the guide's text and epoch times,
 * 3. the device's zone (panels sold for a local market),
 * 4. UTC.
 */
internal fun xtreamCatchupStartTimes(
    startUtcMillis: Long,
    serverZone: ZoneId?,
    epgOffsetMs: Long,
    deviceZone: ZoneId,
): List<LocalDateTime> {
    val instant = Instant.ofEpochMilli(startUtcMillis)
    return buildList {
        serverZone?.let { add(LocalDateTime.ofInstant(instant, it)) }
        if (epgOffsetMs != 0L) add(LocalDateTime.ofInstant(Instant.ofEpochMilli(startUtcMillis + epgOffsetMs), ZoneOffset.UTC))
        add(LocalDateTime.ofInstant(instant, deviceZone))
        add(LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
    }.distinct()
}

/**
 * Timeshift URLs to try, in order. The common shape (`/timeshift/.../start/id.ts`)
 * comes first for every candidate start time, so the first bounded probe batch
 * tests the time zones rather than five spellings of one wrong time.
 */
internal fun xtreamCatchupUrlCandidates(
    starts: List<LocalDateTime>,
    pathUrl: (start: String, extension: String?) -> String,
    queryUrl: (start: String, streamingPrefix: Boolean, streamParameter: String) -> String,
): List<String> {
    val minute = starts.map { it.format(XTREAM_MINUTE) }
    val hasSeconds = starts.any { it.second != 0 }
    val precise = if (hasSeconds) starts.map { it.format(XTREAM_SECOND) } else emptyList()
    return buildList {
        (precise + minute).forEach { add(pathUrl(it, "ts")) }
        minute.forEach { add(pathUrl(it, "m3u8")) }
        minute.forEach {
            add(queryUrl(it, true, "stream"))
            add(queryUrl(it, false, "stream"))
        }
        minute.forEach { add(pathUrl(it, null)) }
        val spaced = starts.flatMap { listOf(it.format(XTREAM_SPACE_SECOND), it.format(XTREAM_SPACE_MINUTE)) }
        val seconds = if (hasSeconds) precise else starts.map { it.format(XTREAM_SECOND) }
        (seconds + spaced).forEach { start ->
            add(pathUrl(start, "ts"))
            add(queryUrl(start, true, "stream"))
            add(queryUrl(start, false, "stream"))
        }
        (minute + seconds + spaced).forEach { start ->
            add(queryUrl(start, true, "stream_id"))
            add(queryUrl(start, false, "stream_id"))
        }
    }.distinct()
}

/**
 * The panel's time zone from the account answer (`player_api.php` without an
 * action). Prefers the named zone; otherwise derives a fixed offset from
 * `time_now` (panel wall clock) against `timestamp_now` (epoch seconds),
 * rounded to 15 minutes. Null when neither is readable.
 */
internal fun parseXtreamServerZone(body: String?): ZoneId? {
    val root = runCatching { JsonParser.parseString(body.orEmpty()) }.getOrNull()
        ?.takeIf { it.isJsonObject }?.asJsonObject ?: return null
    val info = root.get("server_info")?.takeIf { it.isJsonObject }?.asJsonObject ?: return null
    info.text("timezone")?.let { name ->
        try {
            return ZoneId.of(name.trim())
        } catch (_: DateTimeException) {
            // Fall through to the clock comparison.
        }
    }
    val epochSeconds = info.text("timestamp_now")?.toLongOrNull() ?: return null
    val wallClock = info.text("time_now")?.let {
        runCatching { LocalDateTime.parse(it.trim(), XTREAM_SPACE_SECOND) }.getOrNull()
    } ?: return null
    val offsetSeconds = wallClock.toEpochSecond(ZoneOffset.UTC) - epochSeconds
    val quarterHours = Math.round(offsetSeconds / 900.0)
    if (kotlin.math.abs(quarterHours) > 18 * 4) return null
    return ZoneOffset.ofTotalSeconds((quarterHours * 900).toInt())
}

private fun JsonObject.text(name: String): String? =
    get(name)?.takeIf { it.isJsonPrimitive }?.asString?.takeIf { it.isNotBlank() }
