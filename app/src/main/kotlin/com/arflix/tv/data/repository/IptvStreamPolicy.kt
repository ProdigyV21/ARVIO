package com.arflix.tv.data.repository

import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvProviderAccount

internal const val MAX_AUTO_CONNECTION_LIMIT = 2
internal const val MIN_AUTO_FREE_CONNECTIONS = 2

private const val STORED_LIMIT = "limit"
private const val STORED_FINGERPRINT = "fingerprint"

/** The "Connection limit on this device" choice, in the order the settings row steps through it. */
enum class IptvConnectionLimit(internal val stored: String, internal val explicit: Int?) {
    AUTO("auto", null),
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    NO_LIMIT("none", null);

    internal fun next(): IptvConnectionLimit = entries[(ordinal + 1) % entries.size]
}

/** A settings row's reading; [effectiveLimit] is null when nothing limits the source's streams. */
data class IptvConnectionLimitState(val setting: IptvConnectionLimit, val effectiveLimit: Int?)

/** How one account's IPTV streams behave. Holding a policy at all means warm-ups stay off. */
internal data class IptvStreamPolicy(
    val account: IptvProviderAccount,
    val effectiveLimit: Int,
    val homePreviewAllowed: Boolean,
    val matroskaIndexAllowed: Boolean,
    val panelCheckAllowed: Boolean,
)

/** A known channel owner, including an explicit No limit policy. */
internal data class ResolvedIptvChannelPolicy(val limit: IptvStreamPolicy?) {
    val allowsHomePreview: Boolean
        get() = limit?.let { it.homePreviewAllowed && it.panelCheckAllowed } ?: true
}

/**
 * Null means No limit or no resolvable account: every path stays as it is today. Auto follows
 * [latestUsage], the panel's last fresh answer, and never asks for one itself.
 */
internal fun iptvStreamPolicy(
    account: IptvProviderAccount?,
    setting: IptvConnectionLimit,
    reliableAllowance: Int?,
    latestUsage: IptvAccountUsage? = null,
): IptvStreamPolicy? {
    if (account == null || setting == IptvConnectionLimit.NO_LIMIT) return null
    val explicit = setting.explicit
    val limit = when {
        explicit != null && reliableAllowance != null -> minOf(explicit, reliableAllowance)
        explicit != null -> explicit
        // active counts this device's own streams too; they are never credited back, so a count that still holds one errs towards 1.
        reliableAllowance != null && latestUsage != null &&
            latestUsage.max - latestUsage.active >= MIN_AUTO_FREE_CONNECTIONS -> MAX_AUTO_CONNECTION_LIMIT
        else -> 1
    }
    return IptvStreamPolicy(
        account,
        effectiveLimit = limit,
        // Under Auto a preview still needs a confirmed spare slot.
        homePreviewAllowed = explicit == null || limit >= 2,
        matroskaIndexAllowed = limit >= 2,
        panelCheckAllowed = reliableAllowance != null
    )
}

/** Entries sharing an account resolve to one value: strictest explicit wins, explicit beats Auto. */
internal fun strictestIptvConnectionLimit(settings: Iterable<IptvConnectionLimit>): IptvConnectionLimit =
    settings.filter { it != IptvConnectionLimit.AUTO }.minByOrNull { it.explicit ?: Int.MAX_VALUE }
        ?: IptvConnectionLimit.AUTO

/**
 * The stored allowance, only while it still describes the account about to play. Its age is
 * not judged: it is asked for rarely, and the live panel check reads the real numbers anyway.
 */
internal fun reliableIptvAllowance(
    info: IptvAccountInfo?,
    expectedFingerprint: String,
    infoAccount: IptvProviderAccount?,
    streamAccount: IptvProviderAccount,
): Int? {
    if (info == null || streamAccount.kind != IptvProviderAccount.Kind.XTREAM) return null
    if (info.sourceFingerprint != expectedFingerprint || info.status != IptvAccountInfo.STATUS_ACTIVE) return null
    if (infoAccount != streamAccount) return null
    return info.maxConnections?.takeIf { it > 0 }
}

/** Editing an EPG field keeps the choice; changing the login behind a reused id does not. */
internal fun iptvConnectionLimitFingerprint(playlist: IptvPlaylistEntry): String =
    IptvAccountInfoParser.fingerprint(playlist.m3uUrl)

internal fun iptvConnectionLimitFingerprint(portal: StalkerPortalEntry): String =
    IptvAccountInfoParser.fingerprint(portal)

/** Absent, unknown or belonging to other credentials all read as Auto. */
internal fun storedIptvConnectionLimit(entry: Map<String, String>?, fingerprint: String): IptvConnectionLimit {
    if (entry == null || entry[STORED_FINGERPRINT] != fingerprint) return IptvConnectionLimit.AUTO
    return IptvConnectionLimit.entries.firstOrNull { it.stored == entry[STORED_LIMIT] } ?: IptvConnectionLimit.AUTO
}

internal fun storedIptvConnectionLimitEntry(limit: IptvConnectionLimit, fingerprint: String): Map<String, String> =
    mapOf(STORED_LIMIT to limit.stored, STORED_FINGERPRINT to fingerprint)
