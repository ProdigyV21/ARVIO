@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.tv.live.aisubs

import android.os.SystemClock
import androidx.media3.common.Timeline
import androidx.media3.exoplayer.LoadControl
import androidx.media3.exoplayer.analytics.PlayerId
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.TrackGroupArray
import androidx.media3.exoplayer.trackselection.ExoTrackSelection
import androidx.media3.exoplayer.upstream.Allocator

/**
 * Holds live playback back until [requiredLookaheadUs] of media is buffered, at channel start and
 * after every rebuffer, so audio reaches the subtitle pipeline that much before it is heard. A
 * line can only be translated once it has been spoken in full, so without this hold every
 * subtitle would be late. Everything else is [delegate]'s decision.
 *
 * The hold only applies while [requiredLookaheadUs] is above zero (set from the UI thread), so a
 * channel without AI subtitles starts as fast as before. Waiting always converges: while playback
 * waits, the live edge keeps moving, so the buffered duration keeps growing.
 */
internal class SubtitleLookaheadLoadControl(
    private val delegate: LoadControl,
) : LoadControl {
    @Volatile
    var requiredLookaheadUs: Long = 0L

    private var lastHoldLogMs = 0L

    override fun onPrepared(playerId: PlayerId) = delegate.onPrepared(playerId)

    override fun onTracksSelected(
        parameters: LoadControl.Parameters,
        trackGroups: TrackGroupArray,
        trackSelections: Array<out ExoTrackSelection?>,
    ) = delegate.onTracksSelected(parameters, trackGroups, trackSelections)

    override fun onStopped(playerId: PlayerId) = delegate.onStopped(playerId)

    override fun onReleased(playerId: PlayerId) = delegate.onReleased(playerId)

    override fun getAllocator(playerId: PlayerId): Allocator = delegate.getAllocator(playerId)

    override fun getBackBufferDurationUs(playerId: PlayerId): Long = delegate.getBackBufferDurationUs(playerId)

    override fun retainBackBufferFromKeyframe(playerId: PlayerId): Boolean =
        delegate.retainBackBufferFromKeyframe(playerId)

    override fun shouldContinueLoading(parameters: LoadControl.Parameters): Boolean =
        delegate.shouldContinueLoading(parameters)

    override fun shouldContinuePreloading(
        playerId: PlayerId,
        timeline: Timeline,
        mediaPeriodId: MediaSource.MediaPeriodId,
        bufferedDurationUs: Long,
    ): Boolean = delegate.shouldContinuePreloading(playerId, timeline, mediaPeriodId, bufferedDurationUs)

    override fun shouldStartPlayback(parameters: LoadControl.Parameters): Boolean {
        if (!delegate.shouldStartPlayback(parameters)) return false
        val required = requiredLookaheadUs
        if (required <= 0L || parameters.bufferedDurationUs >= required) return true
        val now = SystemClock.elapsedRealtime()
        if (now - lastHoldLogMs >= HOLD_LOG_INTERVAL_MS) {
            lastHoldLogMs = now
            LiveAiSubsLog.i(
                "holding playback: buffered=${parameters.bufferedDurationUs / 1_000}ms " +
                    "required=${required / 1_000}ms rebuffering=${parameters.rebuffering}",
            )
        }
        return false
    }

    private companion object {
        const val HOLD_LOG_INTERVAL_MS = 1_000L
    }
}
