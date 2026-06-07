package com.arflix.tv.data.model

import java.time.Instant

/**
 * DRM configuration parsed from `#KODIPROP` directives in an M3U playlist.
 *
 * @property scheme Canonical DRM scheme name: `"clearkey"`, `"widevine"`, `"playready"`,
 *   or the raw value if unrecognised.
 * @property licenseUrl For Widevine: the license server URL.
 *   For ClearKey: the `kid:key` hex pair (e.g. `9eb4…:166…`).
 * @property licenseData Optional PSSH override (base64). Most MPD manifests declare
 *   PSSH inline or in the init segment; ExoPlayer handles both automatically.
 */
data class DrmInfo(
    val scheme: String,
    val licenseUrl: String? = null,
    val licenseData: String? = null,
)

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
    val drmInfo: DrmInfo? = null,
)

/**
 * Compact now/next program slice for a channel.
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
