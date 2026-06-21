package com.arflix.tv.ui.screens.details

import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.stream.analyzeStreamSource
import com.arflix.tv.data.stream.bestStreamAvailabilityAnalysisForStreams
import com.arflix.tv.data.stream.parseSizeString

// Autoplay starts the best quality/size source it can find within ~2s. It keeps
// collecting progressive addon results until every addon has reported OR this
// ceiling is reached, then plays the best candidate found so far.
internal const val AUTOPLAY_MAX_WAIT_MS = 2000L
// Once a top-tier (4K) source is found we only briefly settle to let a larger 4K
// rip arrive, instead of waiting on slow addons. 4K quality cannot be beaten.
internal const val AUTOPLAY_TOP_TIER_SETTLE_MS = 450L
internal const val AUTOPLAY_SOURCE_RECHECK_MS = 120L
private const val TOP_TIER_QUALITY_SCORE = 4

/** Score quality from the shared stream analysis because addons do not fill quality consistently. */
internal fun qualityScoreForAutoPlay(stream: StreamSource): Int {
    return analyzeStreamSource(stream).resolutionScore
}

internal fun bestAutoPlayStream(
    streams: List<StreamSource>,
    minQualityScore: Int
): StreamSource? {
    val candidates = streams.filter { stream -> qualityScoreForAutoPlay(stream) >= minQualityScore }
    val best = bestStreamAvailabilityAnalysisForStreams(candidates)?.stream ?: return null

    // Shared availability ranking decides quality/release/size/cached. Prefer a simpler
    // web-ready URL only when it is effectively tied with that best candidate.
    return candidates
        .filter { candidate -> isSameAvailabilityTier(candidate, best) }
        .maxWithOrNull(
            compareBy<StreamSource> { if (it.behaviorHints?.notWebReady == true) 0 else 1 }
                .thenBy { if (it.behaviorHints?.cached == true) 1 else 0 }
                .thenByDescending { autoPlaySizeBytes(it) }
                .thenBy { it.addonName.lowercase() }
                .thenBy { it.source.lowercase() }
        ) ?: best
}

/**
 * The source sheet sorts from the visible size string because addon-provided
 * byte hints are inconsistent. Autoplay must do the same.
 */
internal fun autoPlaySizeBytes(stream: StreamSource): Long {
    return parseSizeString(stream.size)
}

internal fun minQualityThreshold(value: String): Int {
    return when (value.trim().lowercase()) {
        "720p", "hd" -> 2
        "1080p", "fullhd", "fhd" -> 3
        "4k", "2160p", "uhd" -> 4
        else -> 0
    }
}

internal fun isAutoPlayableStream(stream: StreamSource): Boolean {
    val url = stream.url?.trim().orEmpty()
    if (!url.startsWith("http", ignoreCase = true)) return false
    return !isPendingDebridStream(stream)
}

internal fun isPendingDebridStream(stream: StreamSource): Boolean {
    val text = listOfNotNull(stream.source, stream.addonName, stream.quality, stream.url, stream.description)
        .joinToString(" ")
        .lowercase()
    return listOf(
        "torrent being downloaded",
        "being downloaded",
        "still downloading",
        "queued",
        "not cached",
        "uncached",
        "cache pending",
        "caching",
        "processing torrent",
        "download in progress"
    ).any { text.contains(it) }
}

/**
 * Decides whether autoplay should keep waiting for more/better sources, or start now.
 *
 * Goal: play the best quality/size found across all sources, within ~2 seconds.
 * - Hard ceiling at [AUTOPLAY_MAX_WAIT_MS]: whatever is best by then plays.
 * - No candidate yet: wait while addons are still loading, until the ceiling.
 * - Top-tier (4K) candidate: settle briefly so a bigger 4K can arrive, then play.
 * - Sub-4K candidate: keep collecting until every addon has reported, capped by the ceiling.
 */
internal fun shouldWaitForAutoPlaySources(
    isLoadingStreams: Boolean,
    selectedStream: StreamSource?,
    elapsedMs: Long
): Boolean {
    if (elapsedMs >= AUTOPLAY_MAX_WAIT_MS) return false
    if (selectedStream == null) return isLoadingStreams
    if (qualityScoreForAutoPlay(selectedStream) >= TOP_TIER_QUALITY_SCORE) {
        return elapsedMs < AUTOPLAY_TOP_TIER_SETTLE_MS
    }
    return isLoadingStreams
}

private fun isSameAvailabilityTier(a: StreamSource, b: StreamSource): Boolean {
    val aa = analyzeStreamSource(a)
    val bb = analyzeStreamSource(b)
    return aa.resolutionScore == bb.resolutionScore &&
        aa.releaseScore == bb.releaseScore &&
        aa.sizeBytes == bb.sizeBytes &&
        aa.isCachedOrDebridReady == bb.isCachedOrDebridReady
}
