package com.arflix.tv.data.model

import com.arflix.tv.util.replaceLiteral

import com.arflix.tv.data.api.StremioMetaPreview
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.security.MessageDigest
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

/** An installed add-on's event reference, not a cached/expiring stream URL. */
data class SportsAddonEvent(
    val installation: String,
    val addonId: String,
    val addonName: String,
    val type: String,
    val eventId: String,
    val title: String,
    val genres: List<String>,
    val startsAt: Long?,
    val live: Boolean,
    val observedAt: Long,
    val artwork: String?,
) {
    val key: String get() = "$installation|$type|$eventId"
    // The guide clock ticks periodically and can precede a just-completed request.
    fun isLive(now: Long) = live && now - observedAt in -60_000L..299_999L &&
        (startsAt == null || startsAt <= now)
}

/**
 * A copy restored from disk at app start. Its live marker is usually older than the
 * five minutes [SportsAddonEvent.isLive] accepts, so a game that is still on would
 * first show as not live and then jump back once the network load arrives. A game that
 * started at most [RESTORED_LIVE_MS] ago keeps its marker until that load replaces it;
 * anything older is no longer called live.
 */
fun SportsAddonEvent.restoredFromDisk(now: Long): SportsAddonEvent {
    if (!live) return this
    val stillOn = startsAt?.let { now - it in 0..RESTORED_LIVE_MS } ?: (now - observedAt in 0..RESTORED_LIVE_MS)
    return if (stillOn) copy(observedAt = now) else copy(live = false)
}

const val RESTORED_LIVE_MS = 3 * 60 * 60_000L

/** Overlapping catalogs may omit fields; keep the original live observation time. */
fun mergeSportsAddonEvent(previous: SportsAddonEvent, incoming: SportsAddonEvent): SportsAddonEvent {
    if (previous.key != incoming.key || sportsEventIdentity(previous.title) != sportsEventIdentity(incoming.title) ||
        (previous.startsAt != null && incoming.startsAt != null && kotlin.math.abs(previous.startsAt - incoming.startsAt) > 7_200_000)) return incoming
    val live = listOf(previous, incoming).filter { it.live }.maxByOrNull { it.observedAt }
    return incoming.copy(startsAt = incoming.startsAt ?: previous.startsAt,
        genres = incoming.genres.ifEmpty { previous.genres }, artwork = incoming.artwork ?: previous.artwork,
        live = live != null, observedAt = live?.observedAt ?: incoming.observedAt)
}

fun sportsAddonInstallation(addon: Addon): String = MessageDigest.getInstance("SHA-256")
    .digest("${addon.id}|${addon.url ?: addon.transportUrl}".toByteArray(Charsets.UTF_8))
    .joinToString("") { "%02x".format(it) }

fun sportsAddonUrl(addon: Addon, resource: String, type: String, id: String, skip: Int? = null): String? {
    val url = (addon.url ?: addon.transportUrl)?.replace("stremio://", "https://")?.toHttpUrlOrNull() ?: return null
    val builder = url.newBuilder()
    if (url.pathSegments.lastOrNull() == "manifest.json" || url.pathSegments.lastOrNull() == "")
        builder.removePathSegment(url.pathSize - 1)
    builder.addPathSegment(resource).addPathSegment(type)
    if (skip == null) builder.addPathSegment("$id.json")
    else builder.addPathSegment(id).addPathSegment("skip=$skip.json")
    return builder.build().toString()
}

private val excludedSportsCatalog = Regex("replay|network|24/7|24_7|highlights", RegexOption.IGNORE_CASE)
fun sportsEventCatalogs(addon: Addon): List<AddonCatalog> = if (!addon.isInstalled || !addon.isEnabled ||
    !SportsAddonCapabilities.isSportsLiveTvAddon(addon)) emptyList() else addon.manifest?.catalogs.orEmpty()
    .filter { SportsAddonCapabilities.isLiveSportsCatalog(it) && !excludedSportsCatalog.containsMatchIn("${it.id} ${it.name}") &&
        it.extra.orEmpty().none { extra -> extra.isRequired && extra.name != "skip" } }
    .sortedBy { if ("live" in it.id.lowercase()) 0 else if ("today" in it.id.lowercase() || "upcoming" in it.id.lowercase()) 1 else 2 }

private val utcSchedule = Regex("\\b(\\d{1,2} [A-Za-z]{3} \\d{4})\\s*[·,]?\\s*(\\d{2}:\\d{2})\\s*UTC\\b")
private val terminal = Regex("\\b(replay|finished|ended|cancelled|canceled|postponed|highlights)\\b", RegexOption.IGNORE_CASE)
private val liveStatus = Regex("^(?:LIVE|LIVE NOW)$", RegexOption.IGNORE_CASE)
private val statusSymbols = Regex("^[^\\p{L}\\p{N}]+")
private val liveTitle = Regex("^LIVE\\s*:?\\s+", RegexOption.IGNORE_CASE)
private val livePrefix = Regex("^LIVE\\s*:\\s*", RegexOption.IGNORE_CASE)
private val terminalLine = Regex("^(finished|ended|cancelled|canceled|postponed|replay)\\b", RegexOption.IGNORE_CASE)
private val utcScheduleFormat = DateTimeFormatter.ofPattern("d MMM uuuu HH:mm", Locale.ENGLISH)
private fun cleanSportsStatus(value: String) = value.replaceLiteral(statusSymbols, "").trim()

fun StremioMetaPreview.toSportsAddonEvent(addon: Addon, catalog: AddonCatalog, now: Long,
    installation: String = sportsAddonInstallation(addon)): SportsAddonEvent? {
    val id = id?.takeIf { it.isNotBlank() } ?: return null
    val title = name?.replaceLiteral(statusSymbols, "")
        ?.replaceLiteral(liveTitle, "")?.trim()?.takeIf { it.isNotEmpty() } ?: return null
    if (id.startsWith("leaf:") || id.startsWith("recap:") || releaseInfo == "24/7" ||
        terminal.containsMatchIn("$title ${releaseInfo.orEmpty()}")) return null
    if (description.orEmpty().lineSequence().any { terminalLine.containsMatchIn(cleanSportsStatus(it)) }) return null
    val date = released?.let { runCatching { Instant.parse(it).toEpochMilli() }.getOrNull() }
        ?: utcSchedule.find("${releaseInfo.orEmpty()}\n${description.orEmpty()}")?.let { match ->
            runCatching { LocalDateTime.parse("${match.groupValues[1]} ${match.groupValues[2]}",
                utcScheduleFormat).toInstant(ZoneOffset.UTC).toEpochMilli() }.getOrNull()
        }
    val live = liveStatus.matches(cleanSportsStatus(releaseInfo.orEmpty())) ||
        livePrefix.containsMatchIn(cleanSportsStatus(name.orEmpty())) ||
        description.orEmpty().lineSequence().any { liveStatus.matches(cleanSportsStatus(it)) }
    if (date == null && !live) return null
    if (date != null && (date < now - 86_400_000L || date > now + 172_800_000L)) return null
    return SportsAddonEvent(installation, addon.id, addon.name, type?.takeIf { it.isNotBlank() } ?: catalog.type,
        id, title, genres.orEmpty(), date, live, now, safeSportsImage(background))
}
