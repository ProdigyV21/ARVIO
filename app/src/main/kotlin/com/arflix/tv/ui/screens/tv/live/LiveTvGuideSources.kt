package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.repository.IptvConfig
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Which guide sources a playlist can actually use.
 *
 * These rules decide whether the full-guide backfill runs, and they behave very
 * differently per source type, so they live here rather than inline in
 * TvViewModel where they could only be checked on a device.
 */
object LiveTvGuideSources {

    /** Channel count above which a playlist takes the paged/low-work code paths. */
    const val LARGE_LIST_CHANNEL_COUNT: Int = 10_000

    /**
     * Explicit guides and standard Xtream logins both provide an XMLTV endpoint.
     * Large-list backfill uses this single feed, not a per-channel API sweep.
     */
    fun hasXmltvSource(config: IptvConfig): Boolean {
        if (config.epgUrl.isNotBlank()) return true
        if (config.playlists.isEmpty() && hasXtreamLogin(config.m3uUrl)) return true
        return config.playlists.any { playlist ->
            playlist.enabled &&
                (playlist.epgUrl.isNotBlank() || playlist.epgUrls.any { it.isNotBlank() } || hasXtreamLogin(playlist.m3uUrl))
        }
    }

    private fun hasXtreamLogin(value: String): Boolean {
        val url = value.toHttpUrlOrNull() ?: return false
        return url.pathSegments.lastOrNull() in setOf("get.php", "player_api.php", "xmltv.php") &&
            !url.queryParameter("username").isNullOrBlank() && !url.queryParameter("password").isNullOrBlank()
    }

    /**
     * Whether the on-device full-guide backfill may run.
     *
     * Large playlists skip it, because the Xtream path fans out into thousands
     * of requests. That guard used to apply to XMLTV too — and since the
     * "loads on demand" fallback (refreshEpgForChannels) skips every channel
     * without Xtream credentials, a large plain-M3U playlist ended up with no
     * guide at all, no matter how long it was left running. XMLTV is bounded,
     * so it is allowed through.
     */
    fun allowsFullGuideBackfill(config: IptvConfig, channelCount: Int): Boolean {
        if (channelCount < LARGE_LIST_CHANNEL_COUNT) return true
        return hasXmltvSource(config)
    }
}
