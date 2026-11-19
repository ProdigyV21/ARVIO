package com.arflix.tv.data.model

import com.arflix.tv.R
import java.util.Locale

/**
 * The 5 stream acquisition mechanisms in ARVIO.
 */
enum class StreamIntegrationType(
    val id: String,
    val titleRes: Int,
    val descRes: Int
) {
    HOME_SERVER(
        id = "home_server",
        titleRes = R.string.stream_integration_home_server,
        descRes = R.string.stream_integration_home_server_desc
    ),
    STREMIO_ADDONS(
        id = "stremio_addons",
        titleRes = R.string.stream_integration_stremio,
        descRes = R.string.stream_integration_stremio_desc
    ),
    PLUGINS(
        id = "plugins",
        titleRes = R.string.stream_integration_plugins,
        descRes = R.string.stream_integration_plugins_desc
    ),
    TELEGRAM(
        id = "telegram",
        titleRes = R.string.stream_integration_telegram,
        descRes = R.string.stream_integration_telegram_desc
    ),
    IPTV_VOD(
        id = "iptv_vod",
        titleRes = R.string.stream_integration_iptv_vod,
        descRes = R.string.stream_integration_iptv_vod_desc
    ),
    /** Sideload builds only (see BuildConfig.YOUTUBE_SEARCH_AVAILABLE). */
    YOUTUBE(
        id = "youtube",
        titleRes = R.string.stream_integration_youtube,
        descRes = R.string.stream_integration_youtube_desc
    );

    companion object {
        val DEFAULT_ORDER = listOf(
            HOME_SERVER,
            STREMIO_ADDONS,
            PLUGINS,
            TELEGRAM,
            IPTV_VOD,
            YOUTUBE
        )

        fun fromId(id: String?): StreamIntegrationType? {
            val raw = id?.trim()?.lowercase(Locale.US) ?: return null
            return entries.firstOrNull { it.id == raw }
        }

        fun inferFromStreamSource(stream: StreamSource): StreamIntegrationType {
            val addonId = stream.addonId.trim().lowercase(Locale.US)
            return when {
                addonId == "home_server" -> HOME_SERVER
                addonId.startsWith("plugin_") -> PLUGINS
                addonId == "telegram_native" || addonId == "telegram" -> TELEGRAM
                addonId == "arvio_youtube" -> YOUTUBE
                IptvVodSourceIds.isIptvVodAddonId(addonId) -> IPTV_VOD
                else -> STREMIO_ADDONS
            }
        }
    }
}

/**
 * User-configurable priority and enabled state for a stream integration.
 */
data class StreamIntegrationConfig(
    val type: StreamIntegrationType,
    val isEnabled: Boolean = true,
    val priority: Int = 0
)

/**
 * An individual, configurable stream provider from any of the 5 integrations:
 * - Stremio Addon (e.g. Torrentio, Comet)
 * - Plugin Repository (e.g. SoraStream Repo)
 * - Home Server Connection (e.g. Living Room Jellyfin, Basement Plex)
 * - IPTV Playlist / Portal VOD
 * - Telegram
 */
data class StreamProviderItem(
    val id: String,
    val rawProviderKeys: List<String>,
    val displayName: String,
    val subtitle: String? = null,
    val type: StreamIntegrationType,
    val isEnabled: Boolean = true,
    val priority: Int = 0,
    val targetConfigId: String? = null,
    val canMoveUp: Boolean = true,
    val canMoveDown: Boolean = true
)

/**
 * Strategy for discovering and resolving streams across integrations.
 */
enum class StreamSearchMode(val id: String) {
    PARALLEL("parallel"),
    SEQUENTIAL("sequential");

    companion object {
        fun fromId(id: String?): StreamSearchMode {
            val raw = id?.trim()?.lowercase(Locale.US) ?: return PARALLEL
            return entries.firstOrNull { it.id == raw } ?: PARALLEL
        }
    }
}
