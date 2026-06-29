package com.arflix.tv.ui.screens.details

import com.arflix.tv.data.model.StreamSource
import java.util.Locale

// Autoplay starts the best quality/size source it can find within ~2s. It keeps
// collecting progressive addon results until every addon has reported OR this
// ceiling is reached, then plays the best candidate found so far.
internal const val AUTOPLAY_MAX_WAIT_MS = 2000L
// Once a top-tier (4K) source is found we only briefly settle to let a larger 4K
// rip arrive, instead of waiting on slow addons — 4K quality can't be beaten.
internal const val AUTOPLAY_TOP_TIER_SETTLE_MS = 450L
internal const val AUTOPLAY_SOURCE_RECHECK_MS = 120L
private const val TOP_TIER_QUALITY_SCORE = 4

private val fourKRegex = Regex("""\b4[kK]\b""")
private val sizeRegex = Regex("""(?i)(\d+(?:[\.,]\d+)?)\s*(TB|GB|MB|KB|B|GiB|MiB|KiB)?""")

/** Score quality from all stream text because addons do not fill the quality field consistently. */
internal fun qualityScoreForAutoPlay(stream: StreamSource): Int {
    val combined = buildString {
        append(stream.quality)
        append(' ')
        append(stream.source)
        append(' ')
        append(stream.addonName)
        stream.behaviorHints?.filename?.let {
            append(' ')
            append(it)
        }
        stream.description?.let {
            append(' ')
            append(it)
        }
    }
    return when {
        combined.contains("2160p", ignoreCase = true) || fourKRegex.containsMatchIn(combined) -> 4
        combined.contains("1080p", ignoreCase = true) -> 3
        combined.contains("720p", ignoreCase = true) -> 2
        combined.contains("480p", ignoreCase = true) -> 1
        else -> 0
    }
}

internal fun bestAutoPlayStream(
    streams: List<StreamSource>,
    minQualityScore: Int
): StreamSource? {
    return streams
        .asSequence()
        .filter { stream -> qualityScoreForAutoPlay(stream) >= minQualityScore }
        .sortedWith(
            // Best quality, then biggest size — that is the user's "best" definition.
            // `notWebReady` HTTP sources (e.g. direct MKV rips) are fully playable on the
            // native ExoPlayer, so they are eligible; webReady only breaks ties at equal
            // quality+size so a known-simple URL wins a coin-flip.
            compareByDescending<StreamSource> { qualityScoreForAutoPlay(it) }
                .thenByDescending { autoPlaySizeBytes(it) }
                .thenByDescending { if (it.behaviorHints?.notWebReady == true) 0 else 1 }
                .thenByDescending { if (it.behaviorHints?.cached == true) 1 else 0 }
                .thenBy { it.addonName.lowercase() }
                .thenBy { it.source.lowercase() }
        )
        .firstOrNull()
}

/**
 * The source sheet sorts from the visible size string because addon-provided
 * byte hints are inconsistent. Autoplay must do the same or it can choose a
 * tiny 720p source over a visibly larger 4K one.
 */
internal fun autoPlaySizeBytes(stream: StreamSource): Long {
    val raw = stream.size.trim()
    if (raw.isBlank()) return 0L
    val match = sizeRegex.find(raw) ?: return 0L
    val value = match.groupValues[1].replace(',', '.').toDoubleOrNull() ?: return 0L
    val unit = match.groupValues.getOrNull(2)?.uppercase(Locale.US).orEmpty()
    val multiplier = when (unit) {
        "TB" -> 1024.0 * 1024.0 * 1024.0 * 1024.0
        "GB", "GIB" -> 1024.0 * 1024.0 * 1024.0
        "MB", "MIB" -> 1024.0 * 1024.0
        "KB", "KIB" -> 1024.0
        else -> 1.0
    }
    return (value * multiplier).toLong()
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
 * - No candidate yet → wait while addons are still loading (until the ceiling).
 * - Top-tier (4K) candidate → only a brief settle ([AUTOPLAY_TOP_TIER_SETTLE_MS]) to let a
 *   larger 4K rip arrive; don't stall on slow addons since 4K can't be out-qualitied.
 * - Sub-4K candidate → keep collecting until every addon has reported (so a better
 *   source isn't missed), capped by the ceiling.
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
