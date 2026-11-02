package com.arflix.tv.data.repository

import java.util.Locale

/**
 * Failures of distinct sources on one host before its remaining sources are skipped for a while.
 * A resolver host (an AIOStreams instance, Torrentio, Comet …) hands out a different file for
 * every source, so one dead or not-yet-cached file there says nothing about the next one: it
 * takes more distinct failures before the whole host is ruled out.
 */
private const val BAD_HOST_FAILED_SOURCES = 2
private const val BAD_RESOLVER_HOST_FAILED_SOURCES = 4

internal fun isPlaybackHostBad(
    failedSources: Int,
    failures: Int,
    successes: Int,
    perFileResolver: Boolean
): Boolean {
    val threshold = if (perFileResolver) BAD_RESOLVER_HOST_FAILED_SOURCES else BAD_HOST_FAILED_SOURCES
    return failedSources >= threshold && failures > successes
}

private val RESOLVER_HOST_MARKERS = listOf(
    "aiostreams", "torrentio", "comet", "mediafusion", "stremthru", "jackettio", "debridio"
)

// Per-file playback endpoints: AIOStreams "/api/v1/debrid/playback/…", Torrentio "/resolve/…",
// Comet "/playback/…".
private val RESOLVER_PATH_MARKERS = listOf("/api/v1/debrid/", "/api/v1/usenet/", "/resolve/", "/playback/")

/** Whether [url] is resolved to its own file when played, rather than being the file itself. */
internal fun isPerFileResolverUrl(url: String?): Boolean {
    val parsed = try {
        java.net.URI(url?.trim().orEmpty())
    } catch (_: Exception) {
        return false
    }
    val host = parsed.host?.lowercase(Locale.US).orEmpty()
    if (host.isBlank()) return false
    if (RESOLVER_HOST_MARKERS.any { host.contains(it) }) return true
    val path = parsed.rawPath?.lowercase(Locale.US).orEmpty()
    return RESOLVER_PATH_MARKERS.any { path.contains(it) }
}

/** One source on a host, so retries of the same source count once. */
internal fun playbackSourceKey(url: String?): String =
    url?.trim().orEmpty().substringBefore('|').substringBefore('#')
