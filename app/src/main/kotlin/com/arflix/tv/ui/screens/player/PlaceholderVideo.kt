package com.arflix.tv.ui.screens.player

import java.net.URI
import java.util.Locale

private const val PLACEHOLDER_MAX_DURATION_MS = 5 * 60_000L
private val PLACEHOLDER_PATHS = setOf(
    "/static/200.mp4", "/static/401.mp4", "/static/403.mp4", "/static/429.mp4", "/static/500.mp4",
    "/static/downloading.mp4", "/static/download_failed.mp4", "/static/no_matching_file.mp4",
    "/static/content_proxy_limit_reached.mp4", "/static/payment_required.mp4",
    "/static/store_limit_exceeded.mp4", "/static/unavailable_for_legal_reasons.mp4"
)

internal enum class PlaybackVideoStatus { PENDING, REAL, PLACEHOLDER }

/** A large short file can be real content; require an error endpoint or a title-runtime mismatch. */
internal fun classifyPlaybackVideo(
    durationMs: Long,
    expectedDurationMs: Long,
    playbackUrl: String?,
    metadataPending: Boolean,
    isLive: Boolean
): PlaybackVideoStatus {
    if (isLive || durationMs > PLACEHOLDER_MAX_DURATION_MS) return PlaybackVideoStatus.REAL
    val path = runCatching { URI(playbackUrl.orEmpty()).path?.lowercase(Locale.US) }.getOrNull()
    if (path in PLACEHOLDER_PATHS) return PlaybackVideoStatus.PLACEHOLDER
    if (durationMs <= 0L) return PlaybackVideoStatus.PENDING
    // Wait for the actual episode's runtime, not the show's average (specials may be short).
    if (metadataPending) return PlaybackVideoStatus.PENDING
    return if (expectedDurationMs > PLACEHOLDER_MAX_DURATION_MS && durationMs <= expectedDurationMs / 3) {
        PlaybackVideoStatus.PLACEHOLDER
    } else {
        PlaybackVideoStatus.REAL
    }
}

internal fun canPersistPlaybackSource(
    selectedSource: String?,
    loadedSource: String?,
    checkedSource: String?,
    status: PlaybackVideoStatus
): Boolean = selectedSource != null && selectedSource == loadedSource &&
    selectedSource == checkedSource && status == PlaybackVideoStatus.REAL
