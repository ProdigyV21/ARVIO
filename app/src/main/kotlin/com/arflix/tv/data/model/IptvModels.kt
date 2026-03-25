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
    val xtreamStreamId: Int? = null
)

/**
 * Compact now/next program slice for a channel.
 */
data class IptvNowNext(
    val now: IptvProgram? = null,
    val next: IptvProgram? = null,
    val later: IptvProgram? = null,
    val upcoming: List<IptvProgram> = emptyList(),
    val recent: List<IptvProgram> = emptyList()  // Programs that ended within the past ~60-90 min
)

/**
 * EPG program row.
 */
data class IptvProgram(
    val title: String,
    val description: String? = null,
    val startUtcMillis: Long,
    val endUtcMillis: Long
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
