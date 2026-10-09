package com.arflix.tv.ui.screens.player

/**
 * Decides when the selected source has played long enough to become the title's recently
 * played source. Opening a source is not enough: one that is abandoned or failed over inside
 * the threshold, because it was slow or broken, must never be recorded.
 *
 * Time counts only while playing, and each step counts the smaller of the media time and the
 * wall time that passed, so a seek (media time jumps) or a stalled tick (wall time jumps)
 * cannot inflate it. Resuming deep into a title counts from zero. A new selection starts over.
 */
internal class RecentSourcePlayTracker(
    private val thresholdMs: Long = RECENT_SOURCE_MIN_PLAY_MS,
) {
    private var selectionId: Long? = null
    private var lastPositionMs = -1L
    private var lastTickMs = -1L
    private var playedMs = 0L
    private var reached = false

    fun reset() {
        selectionId = null
        lastPositionMs = -1L
        lastTickMs = -1L
        playedMs = 0L
        reached = false
    }

    /** True once per selection: on the tick its played time reaches the threshold. */
    fun onProgress(selectionId: Long, positionMs: Long, isPlaying: Boolean, nowMs: Long): Boolean {
        if (this.selectionId != selectionId) {
            reset()
            this.selectionId = selectionId
        }
        val previousPositionMs = lastPositionMs
        val previousTickMs = lastTickMs
        lastPositionMs = positionMs
        lastTickMs = nowMs
        if (reached || !isPlaying || previousPositionMs < 0L) return false

        val step = minOf(positionMs - previousPositionMs, nowMs - previousTickMs)
        if (step <= 0L) return false
        playedMs += step
        if (playedMs < thresholdMs) return false
        reached = true
        return true
    }

    companion object {
        const val RECENT_SOURCE_MIN_PLAY_MS = 10_000L
    }
}
