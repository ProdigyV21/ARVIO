package com.arflix.tv.data.model

import java.time.Instant

/**
 * IPTV channel parsed from an M3U playlist.
 */
data class IptvChannel(
    val id: String,
    val name: String,
    val streamUrl: String,
    val group: String,
    val logo: String? = null,
    val epgId: String? = null,
    val rawTitle: String = name,
    val xtreamStreamId: Int? = null,
    val catchupDays: Int = 0,
    val catchupType: String? = null,
    val catchupSource: String? = null,
    val tvgName: String? = null,
    val providerChannelNumber: String? = null,
    val requestHeaders: Map<String, String> = emptyMap(),
    val language: String? = null,
    val country: String? = null,
    val qualityLabel: String? = null,
    val variantKey: String? = null,
    val health: IptvChannelHealth = IptvChannelHealth()
)

/**
 * Status of the last known health check for an IPTV stream.
 */
enum class IptvChannelHealthStatus {
    UNKNOWN,
    HEALTHY,
    DEGRADED,
    OFFLINE
}

data class IptvChannelHealth(
    val httpStatusCode: Int? = null,
    val latencyMs: Long? = null,
    val consecutiveFailureCount: Int = 0,
    val lastSuccessfulAtMs: Long? = null,
    val lastCheckedAtMs: Long = 0L
) {
    val status: IptvChannelHealthStatus
        get() = when {
            lastCheckedAtMs == 0L -> IptvChannelHealthStatus.UNKNOWN
            consecutiveFailureCount >= 3 -> IptvChannelHealthStatus.OFFLINE
            consecutiveFailureCount > 0 -> IptvChannelHealthStatus.DEGRADED
            httpStatusCode != null && httpStatusCode in 200..299 -> IptvChannelHealthStatus.HEALTHY
            else -> IptvChannelHealthStatus.UNKNOWN
        }

    val summaryText: String
        get() = when (status) {
            IptvChannelHealthStatus.HEALTHY -> "Healthy"
            IptvChannelHealthStatus.DEGRADED -> "Degraded"
            IptvChannelHealthStatus.OFFLINE -> "Offline"
            IptvChannelHealthStatus.UNKNOWN -> "Unknown"
        }
}

/**
 * Loaded IPTV snapshot used by UI.
 */
data class IptvNowNext(
    val now: IptvProgram? = null,
    val next: IptvProgram? = null,
    val later: IptvProgram? = null,
    val upcoming: List<IptvProgram> = emptyList(),
    val recent: List<IptvProgram> = emptyList()  // Past programs kept for replay/catchup when available
)

/**
 * EPG program row.
 */
data class IptvProgram(
    val title: String,
    val description: String? = null,
    val startUtcMillis: Long,
    val endUtcMillis: Long,
    val catchupAvailable: Boolean? = null
) {
    fun isLive(atUtcMillis: Long): Boolean = atUtcMillis in startUtcMillis until endUtcMillis
    fun startsInMinutes(atUtcMillis: Long): Long = ((startUtcMillis - atUtcMillis) / 60_000L).coerceAtLeast(0L)
}

/**
 * Loaded IPTV snapshot used by UI.
 */
data class IptvSnapshot(
    val channels: List<IptvChannel> = emptyList(),
    val grouped: Map<String, List<IptvChannel>> = emptyMap(),
    val nowNext: Map<String, IptvNowNext> = emptyMap(),
    val favoriteGroups: List<String> = emptyList(),
    val favoriteChannels: List<String> = emptyList(),
    val hiddenGroups: List<String> = emptyList(),
    val groupOrder: List<String> = emptyList(),
    val epgWarning: String? = null,
    val loadedAt: Instant = Instant.now()
)

data class IptvHealthSummary(
    val total: Int = 0,
    val healthy: Int = 0,
    val degraded: Int = 0,
    val offline: Int = 0,
    val unknown: Int = 0,
    val lastCheckedAtMs: Long = 0L
) {
    val summaryText: String
        get() = when {
            total == 0 -> "No health checks yet"
            else -> listOfNotNull(
                healthy.takeIf { it > 0 }?.let { "$it healthy" },
                degraded.takeIf { it > 0 }?.let { "$it degraded" },
                offline.takeIf { it > 0 }?.let { "$it offline" },
                unknown.takeIf { it > 0 }?.let { "$it unknown" }
            ).joinToString(" • ")
        }

    val lastCheckedText: String
        get() = if (lastCheckedAtMs == 0L) "" else "Last checked ${java.time.Duration.between(java.time.Instant.ofEpochMilli(lastCheckedAtMs), java.time.Instant.now()).abs().toMinutes()}m ago"
}

/**
 * Lightweight helper to handle playlistId|groupName composite keys without
 * unnecessary string allocations in UI loops.
 */
@JvmInline
value class PlaylistGroupKey(val key: String) {
    val playlistId: String get() = key.substringBefore('|')
    val groupName: String get() = key.substringAfter('|', missingDelimiterValue = key)

    companion object {
        fun build(playlistId: String, groupName: String): String {
            return "$playlistId|$groupName"
        }
    }
}
