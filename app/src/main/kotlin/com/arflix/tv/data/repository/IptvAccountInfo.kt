package com.arflix.tv.data.repository

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.arflix.tv.network.IptvAccountUsage
import java.security.MessageDigest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

/**
 * What a provider told us about the subscription behind one playlist or portal:
 * when it ends and how many streams may run at once.
 *
 * Stored per source id, locally only (never in the cloud snapshot): the values
 * describe one provider account at one moment, and are asked for only when a
 * source is added or edited, when the user taps "Refresh now", or once per process
 * when a user-started Xtream play finds none stored for these credentials.
 *
 * Every field is nullable on purpose - the map is stored as Gson JSON, and Gson
 * skips Kotlin default values (see [StalkerPortalEntry]).
 */
data class IptvAccountInfo(
    /** [IptvAccountInfoParser.fingerprint] of the credentials that were asked. */
    val sourceFingerprint: String? = null,
    val checkedAtMs: Long? = null,
    /** One of [STATUS_ACTIVE], [STATUS_EXPIRED], [STATUS_UNAVAILABLE]. */
    val status: String? = null,
    /** End of the subscription, or null when it has none or none was reported. */
    val expiresAtMs: Long? = null,
    /** True when the provider reported a subscription without an end date. */
    val unlimited: Boolean? = null,
    /** Allowed concurrent streams; only Xtream panels report it. */
    val maxConnections: Int? = null,
) {
    companion object {
        const val STATUS_ACTIVE = "active"
        const val STATUS_EXPIRED = "expired"
        const val STATUS_UNAVAILABLE = "unavailable"
    }
}

/** How a source row presents [IptvAccountInfo] at a given moment. */
sealed class IptvAccountBadge {
    data object Unlimited : IptvAccountBadge()
    /** More than [IptvAccountInfoParser.WARNING_DAYS] days left. */
    data class Ok(val daysLeft: Int, val expiresAtMs: Long) : IptvAccountBadge()
    /** [IptvAccountInfoParser.WARNING_DAYS] days or less left; 0 means "ends today". */
    data class Warning(val daysLeft: Int, val expiresAtMs: Long) : IptvAccountBadge()
    data class Expired(val expiredAtMs: Long?) : IptvAccountBadge()
    /** The provider exposes nothing (plain M3U file) or did not answer. */
    data object Unavailable : IptvAccountBadge()
}

/**
 * Pure parsing and classification for [IptvAccountInfo], kept free of Android
 * and network code so every provider shape is unit-testable.
 */
object IptvAccountInfoParser {
    const val WARNING_DAYS = 14
    private const val DAY_MS = 24L * 60 * 60 * 1000

    /**
     * Identifies the credentials an [IptvAccountInfo] belongs to. Source ids
     * are reused (a removed "stalker2" comes back as the next "stalker2"), so an
     * entry is only shown while its fingerprint still matches. Hashed so the
     * stored map carries no portal address, MAC or password.
     */
    fun fingerprint(vararg parts: String): String {
        val raw = parts.joinToString("\u0000") { it.trim().lowercase(Locale.ROOT) }
        val digest = MessageDigest.getInstance("SHA-256").digest(raw.toByteArray(Charsets.UTF_8))
        return digest.take(12).joinToString("") { "%02x".format(it) }
    }

    fun fingerprint(playlist: IptvPlaylistEntry): String =
        fingerprint(playlist.m3uUrl, playlist.epgUrls.joinToString("\n"), playlist.epgUrl)

    fun fingerprint(portal: StalkerPortalEntry): String =
        fingerprint(portal.portalUrl, portal.macAddress)

    /**
     * Reads the answer of `player_api.php?username=…&password=…` (no action).
     * Returns null when the body is not an Xtream account answer at all - an
     * HTML error page under HTTP 200, or a rejected login (`auth: 0`).
     */
    fun parseXtream(body: String?, fingerprint: String, nowMs: Long): IptvAccountInfo? {
        val root = parseObject(body) ?: return null
        val userInfo = root.get("user_info")?.takeIf { it.isJsonObject }?.asJsonObject ?: return null
        if (userInfo.text("auth") == "0") return null
        val status = userInfo.text("status")?.lowercase(Locale.ROOT)
        val expText = userInfo.text("exp_date")
        val expSeconds = expText?.toLongOrNull()?.takeIf { it > 0 }
        val expiresAtMs = expSeconds?.let { it * 1000 }
        val unlimited = expiresAtMs == null && (expText == null || expText == "0" || expText.equals("null", true))
        val maxConnections = userInfo.text("max_connections")?.toIntOrNull()?.takeIf { it > 0 }
        val expired = (status != null && status != "active") ||
            (expiresAtMs != null && expiresAtMs <= nowMs)
        return IptvAccountInfo(
            sourceFingerprint = fingerprint,
            checkedAtMs = nowMs,
            status = if (expired) IptvAccountInfo.STATUS_EXPIRED else IptvAccountInfo.STATUS_ACTIVE,
            expiresAtMs = expiresAtMs,
            unlimited = unlimited && !expired,
            maxConnections = maxConnections,
        )
    }

    /**
     * Reads the live slot count from the same answer as [parseXtream]. Never
     * stored, because it ages within seconds. Null means unknown: not an account
     * answer, a rejected or inactive login, or counts a panel did not report.
     */
    internal fun parseXtreamAvailability(body: String?): IptvAccountUsage? {
        val root = parseObject(body) ?: return null
        val userInfo = root.get("user_info")?.takeIf { it.isJsonObject }?.asJsonObject ?: return null
        if (userInfo.text("auth") == "0") return null
        val status = userInfo.text("status")?.lowercase(Locale.ROOT)
        if (status != null && status != "active") return null
        val active = userInfo.text("active_cons")?.toIntOrNull()?.takeIf { it >= 0 } ?: return null
        val max = userInfo.text("max_connections")?.toIntOrNull()?.takeIf { it > 0 } ?: return null
        return IptvAccountUsage(active, max)
    }

    /**
     * Reads the answer of `type=account_info&action=get_main_info`.
     *
     * Portals disagree on where the end date lives: some send a machine
     * readable `end_date`, many reseller panels only write it as English text
     * into `phone` ("November 14, 2026, 6:01 pm", "Nov 15, 2022"). `end_date`
     * wins; `phone` is the fallback. An unreadable date never becomes a guessed
     * one - the account then counts as "no information".
     *
     * Returns null when the body is not a portal answer (HTML, empty).
     */
    fun parseStalker(
        body: String?,
        fingerprint: String,
        nowMs: Long,
        zone: ZoneId = ZoneId.systemDefault(),
    ): IptvAccountInfo? {
        val root = parseObject(body) ?: return null
        val js = root.get("js")?.takeIf { it.isJsonObject }?.asJsonObject ?: return null
        val candidates = listOfNotNull(js.text("end_date"), js.text("phone"))
        if (candidates.any { it.equals("unlimited", ignoreCase = true) }) {
            return IptvAccountInfo(fingerprint, nowMs, IptvAccountInfo.STATUS_ACTIVE, null, true, null)
        }
        val expiresAtMs = candidates.firstNotNullOfOrNull { parseStalkerDate(it, zone) }
            ?: return unavailable(fingerprint, nowMs)
        val expired = expiresAtMs <= nowMs
        return IptvAccountInfo(
            sourceFingerprint = fingerprint,
            checkedAtMs = nowMs,
            status = if (expired) IptvAccountInfo.STATUS_EXPIRED else IptvAccountInfo.STATUS_ACTIVE,
            expiresAtMs = expiresAtMs,
            unlimited = false,
            maxConnections = null,
        )
    }

    fun unavailable(fingerprint: String, nowMs: Long): IptvAccountInfo =
        IptvAccountInfo(fingerprint, nowMs, IptvAccountInfo.STATUS_UNAVAILABLE, null, false, null)

    private val dateTimeFormats: List<DateTimeFormatter> = listOf(
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "MMMM d, yyyy, h:mm a",
        "MMM d, yyyy, h:mm a",
        "MMMM d, yyyy h:mm a",
        "MMM d, yyyy h:mm a",
        "dd.MM.yyyy HH:mm",
    ).map(::formatter)

    private val dateFormats: List<DateTimeFormatter> = listOf(
        "yyyy-MM-dd",
        "MMMM d, yyyy",
        "MMM d, yyyy",
        "d MMMM yyyy",
        "d MMM yyyy",
        "dd.MM.yyyy",
    ).map(::formatter)

    private fun formatter(pattern: String): DateTimeFormatter =
        DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(pattern)
            .toFormatter(Locale.ENGLISH)

    internal fun parseStalkerDate(raw: String, zone: ZoneId): Long? {
        val text = raw.trim().replace(Regex("\\s+"), " ")
        if (text.isBlank() || text.startsWith("0000")) return null
        dateTimeFormats.forEach { format ->
            runCatching { LocalDateTime.parse(text, format) }.getOrNull()?.let {
                return it.atZone(zone).toInstant().toEpochMilli()
            }
        }
        dateFormats.forEach { format ->
            // A bare date means "valid through that day".
            runCatching { LocalDate.parse(text, format) }.getOrNull()?.let {
                return it.plusDays(1).atStartOfDay(zone).toInstant().toEpochMilli() - 1
            }
        }
        return null
    }

    /** Classifies [info] for display; the remaining days are counted at [nowMs], not at fetch time. */
    fun badge(info: IptvAccountInfo, nowMs: Long): IptvAccountBadge {
        val expiresAt = info.expiresAtMs
        return when {
            info.status == IptvAccountInfo.STATUS_EXPIRED -> IptvAccountBadge.Expired(expiresAt)
            info.status != IptvAccountInfo.STATUS_ACTIVE -> IptvAccountBadge.Unavailable
            info.unlimited == true -> IptvAccountBadge.Unlimited
            expiresAt == null -> IptvAccountBadge.Unavailable
            expiresAt <= nowMs -> IptvAccountBadge.Expired(expiresAt)
            else -> {
                val days = ((expiresAt - nowMs) / DAY_MS).toInt()
                if (days > WARNING_DAYS) IptvAccountBadge.Ok(days, expiresAt)
                else IptvAccountBadge.Warning(days, expiresAt)
            }
        }
    }

    private fun parseObject(body: String?): JsonObject? {
        if (body.isNullOrBlank()) return null
        return runCatching { JsonParser.parseString(body.trim()) }
            .getOrNull()
            ?.takeIf { it.isJsonObject }
            ?.asJsonObject
    }

    /** Reads a scalar as text: panels send the same field as number, string or null. */
    private fun JsonObject.text(key: String): String? {
        val value: JsonElement = get(key) ?: return null
        if (value.isJsonNull || !value.isJsonPrimitive) return null
        return value.asString.trim().ifBlank { null }
    }
}
