package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.MediaType

internal fun isMatchedIptvVodPlayback(
    mediaType: MediaType,
    mediaId: Int,
    season: Int?,
    episode: Int?,
    addonId: String?,
    explicitlyLive: Boolean = false
): Boolean = !explicitlyLive && mediaId > 0 && IptvVodSourceIds.isIptvVodAddonId(addonId) &&
    (mediaType == MediaType.MOVIE ||
        (mediaType == MediaType.TV && season != null && season >= 0 && episode != null && episode > 0))

internal data class IptvVodTrackingProgress(val durationMs: Long, val percent: Float)

internal fun playbackTrackingDurationMs(durationMs: Long, liveTimeline: Boolean, matchedIptvVod: Boolean): Long =
    if (durationMs > 0L && !(liveTimeline && matchedIptvVod)) durationMs else 0L

/** A metadata runtime is an estimate, not evidence that the video has finished. */
internal fun unknownDurationVodProgress(
    positionMs: Long,
    expectedDurationMs: Long,
    ended: Boolean
): IptvVodTrackingProgress {
    val duration = expectedDurationMs.coerceAtLeast(0L)
    val percent = when {
        ended -> 100f
        duration > 0L -> (positionMs.coerceAtLeast(0L).toDouble() / duration * 100.0)
            .toFloat().coerceIn(0f, 79f)
        else -> 0f
    }
    // Trakt can mark a stop as watched above 80%; never cross that using an estimate alone.
    return IptvVodTrackingProgress(duration, percent)
}

internal fun canReportPlaybackProgress(positionMs: Long, durationMs: Long, matchedIptvVod: Boolean): Boolean =
    positionMs > 0L && (durationMs > 0L || matchedIptvVod)

internal fun shouldReportPlaybackProgress(previousSecond: Long, currentSecond: Long,
    previousPlaying: Boolean?, playing: Boolean): Boolean =
    previousSecond < 0 || previousPlaying != playing || currentSecond < previousSecond ||
        (if (playing) currentSecond - previousSecond >= 3L else currentSecond != previousSecond)
