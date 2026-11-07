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

    /** True while playback is being held for the look-ahead (read by the UI). */
    @Volatile
    var holding: Boolean = false
        private set

    /** Buffered media when the hold was last checked, for "Preparing subtitles… N s". */
    @Volatile
    var lastBufferedUs: Long = 0L
        private set

    /**
     * The buffer's memory cap was reached before the look-ahead (a high-bitrate channel on a
     * low-memory box): playback started with less, so some subtitles may be late.
     */
    @Volatile
    var cappedByMemory: Boolean = false

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
        lastBufferedUs = parameters.bufferedDurationUs
        if (required <= 0L || parameters.bufferedDurationUs >= required) {
            holding = false
            return true
        }
        // Loading has stopped (memory target reached): waiting longer would never get there and
        // would end in the stuck-buffering error, so play with what there is.
        if (!delegate.shouldContinueLoading(parameters)) {
            if (!cappedByMemory) LiveAiSubsLog.w("look-ahead capped by the buffer memory target at ${parameters.bufferedDurationUs / 1_000}ms")
            cappedByMemory = true
            holding = false
            return true
        }
        holding = true
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
