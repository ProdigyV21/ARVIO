package com.arflix.tv.data.model

import java.net.URLDecoder
import java.net.URLEncoder

/**
 * Placeholder URL for a Stalker VOD source.
 *
 * A Stalker portal never hands out a playable URL, only a `cmd` token that has
 * to be exchanged for one via `create_link`. Doing that while building a source
 * list would fire one portal request per candidate, so a matched movie carries
 * this marker instead and `StreamRepository.resolveStreamInternal` exchanges it
 * exactly once, when the user actually starts playback.
 *
 * Shape: `stalker_vod://<portalId>/<urlencoded cmd>`. The portal id travels
 * inside the marker so a multi-portal setup always resolves against the portal
 * the entry came from.
 */
internal object StalkerVodLink {

    const val SCHEME = "stalker_vod://"

    fun isMarker(url: String): Boolean = url.trim().startsWith(SCHEME, ignoreCase = true)

    fun buildMarker(portalId: String, cmd: String): String? {
        val id = portalId.trim()
        val command = cmd.trim()
        if (id.isBlank() || command.isBlank()) return null
        return SCHEME + URLEncoder.encode(id, "UTF-8") + "/" + URLEncoder.encode(command, "UTF-8")
    }

    /** Returns `portalId to cmd`, or null when [url] is not a well-formed marker. */
    fun parseMarker(url: String): Pair<String, String>? {
        val trimmed = url.trim()
        if (!isMarker(trimmed)) return null
        val body = trimmed.substring(SCHEME.length)
        // The cmd is url-encoded, so its own slashes cannot be confused with
        // the single separator between portal id and command.
        val separator = body.indexOf('/')
        if (separator <= 0 || separator == body.length - 1) return null
        val portalId = runCatching { URLDecoder.decode(body.substring(0, separator), "UTF-8") }
            .getOrNull()?.trim().orEmpty()
        val command = runCatching { URLDecoder.decode(body.substring(separator + 1), "UTF-8") }
            .getOrNull()?.trim().orEmpty()
        if (portalId.isBlank() || command.isBlank()) return null
        return portalId to command
    }
}

/**
 * True when [url] plays directly, or is a Stalker VOD placeholder that becomes
 * a direct URL the moment playback resolves it.
 *
 * Everything that filters or ranks "direct http source" has to count the
 * placeholder too. Autoplay does: without this, a matched Stalker movie is
 * dropped from the autoplay candidates and the player reports "no source
 * matches this filter" even though the source list shows it and it plays fine
 * when picked by hand.
 */
internal fun isDirectStreamUrl(url: String?): Boolean {
    val trimmed = url?.trim().orEmpty()
    if (trimmed.isBlank()) return false
    return trimmed.startsWith("http", ignoreCase = true) || StalkerVodLink.isMarker(trimmed)
}
