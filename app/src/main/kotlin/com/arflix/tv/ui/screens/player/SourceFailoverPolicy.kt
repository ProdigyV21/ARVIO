package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.StreamSource
import java.util.Locale

internal data class PlaybackRequestIdentity(val url: String, val headers: Map<String, String>) {
    // Identities are for in-memory comparison, never diagnostics: headers may contain credentials.
    override fun toString(): String = "PlaybackRequestIdentity(redacted)"
}

private fun isSafePlaybackHeader(name: String, value: String): Boolean =
    name.isNotBlank() && value.isNotBlank() &&
        name.all { ch -> ch.code in 33..126 &&
            ch !in setOf('(', ')', '<', '>', '@', ',', ';', ':', '\\', '"', '/', '[', ']', '?', '=', '{', '}') } &&
        value.all { ch -> ch == '\t' || ch.code in 32..126 }

internal fun Map<String, String>.safePlaybackHeaders(): Map<String, String> =
    filter { (name, value) -> isSafePlaybackHeader(name.trim(), value.trim()) }
        .mapKeys { (name, _) -> name.trim() }
        .mapValues { (_, value) -> value.trim() }

internal fun playbackRequestIdentity(
    stream: StreamSource?,
    playbackUrl: String? = stream?.url
): PlaybackRequestIdentity? {
    val url = playbackUrl?.trim()?.takeIf { it.isNotBlank() } ?: return null
    val headers = stream?.behaviorHints?.proxyHeaders?.request.orEmpty().safePlaybackHeaders()
        .mapKeys { (name, _) -> name.lowercase(Locale.ROOT) }
    return PlaybackRequestIdentity(url, headers)
}

internal fun canTryFailoverSource(
    candidate: StreamSource,
    playingStream: StreamSource?,
    playingUrl: String?,
    placeholders: Set<PlaybackRequestIdentity>
): Boolean {
    val request = playbackRequestIdentity(candidate) ?: return false
    return request != playbackRequestIdentity(playingStream, playingUrl) && request !in placeholders
}

internal fun playingRequestIndex(streams: List<StreamSource>, playing: StreamSource?, url: String?): Int {
    val request = playbackRequestIdentity(playing, url) ?: return -1
    return streams.indexOfFirst { playbackRequestIdentity(it) == request }
}
