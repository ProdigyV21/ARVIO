package com.arflix.tv.data.model

import java.util.Locale

/**
 * Add-on ids ARVIO puts on its own IPTV video-on-demand sources.
 *
 * Xtream and Stalker feed the very same movie/episode source lists, so every
 * place that special-cases IPTV VOD has to know about both ids. Checking only
 * the Xtream id silently drops the Stalker sources when a source list is
 * re-merged, or classifies them as live TV and filters them out.
 */
object IptvVodSourceIds {

    const val XTREAM = "iptv_xtream_vod"
    const val STALKER = "iptv_stalker_vod"

    val ALL: Set<String> = setOf(XTREAM, STALKER)

    fun isIptvVodAddonId(addonId: String?): Boolean {
        val id = addonId?.trim()?.lowercase(Locale.US) ?: return false
        return id in ALL
    }
}
