package com.arflix.tv.cast

/**
 * Pure decision logic for a DLNA polling session.
 *
 * A renderer can disappear (TV switched off / off Wi-Fi), stop itself, or reject the first
 * `Play` while it is still `TRANSITIONING`. The poll loop must end the session in those cases
 * instead of leaving the player stuck in `Casting` forever. This class holds only the rules —
 * no Android, coroutine or network dependency — so every edge case can be tested on the JVM.
 */
internal class DlnaSessionTracker(
    private val nowMs: () -> Long = System::currentTimeMillis,
    private val unreachableWindowMs: Long = 15_000L,
    private val noPlaybackWindowMs: Long = 20_000L,
    private val playRetryIntervalMs: Long = 2_000L,
) {
    enum class EndReason { UNREACHABLE, REMOTE_STOPPED, NO_PLAYBACK }

    private var startedAtMs = 0L
    private var lastResponseAtMs = 0L
    private var everPlayed = false
    private var stopStreak = 0
    private var awaitingPlay = true
    private var playRetriesLeft = 0
    private var lastPlayRetryAtMs = 0L

    /** Called once, right after the load finishes. */
    fun start(playRejected: Boolean) {
        val now = nowMs()
        startedAtMs = now
        lastResponseAtMs = now
        everPlayed = false
        stopStreak = 0
        awaitingPlay = true
        playRetriesLeft = if (playRejected) MAX_PLAY_RETRIES else 0
        lastPlayRetryAtMs = now
    }

    /**
     * Feeds one poll iteration.
     *
     * @param positionOk whether GetPositionInfo answered at all
     * @param state CurrentTransportState, or null when GetTransportInfo failed
     * @return the reason to end the session, or null to keep polling
     */
    fun onPoll(positionOk: Boolean, state: String?): EndReason? {
        val now = nowMs()
        // A single poll can take up to ~18 s (two SOAP calls, 3 s connect + 6 s read each),
        // so "a few consecutive failed polls" is measured against a wall-clock window rather
        // than a raw iteration count.
        if (positionOk || state != null) {
            lastResponseAtMs = now
        } else if (now - lastResponseAtMs >= unreachableWindowMs) {
            return EndReason.UNREACHABLE
        }

        if (state == "PLAYING") {
            everPlayed = true
            awaitingPlay = false
            playRetriesLeft = 0
            stopStreak = 0
            return null
        }

        if (state == "STOPPED" || state == "NO_MEDIA_PRESENT") {
            if (everPlayed) {
                // A single STOPPED can also be a transient answer during a seek or a bitrate
                // switch; require two consecutive observations before ending the session.
                stopStreak++
                if (stopStreak >= 2) return EndReason.REMOTE_STOPPED
            } else if (awaitingPlay && now - startedAtMs >= noPlaybackWindowMs) {
                // SetAVTransportURI was accepted but playback never started. Do not report
                // this as a refused format — the renderer simply did not begin.
                return EndReason.NO_PLAYBACK
            }
        } else {
            stopStreak = 0
        }
        return null
    }

    /** Whether the poll loop should issue one more Play in this iteration. */
    fun shouldRetryPlay(state: String?, userPaused: Boolean): Boolean {
        if (!awaitingPlay || playRetriesLeft == 0 || userPaused) return false
        // TRANSITIONING is exactly the state in which renderers reject Play with UPnP 701.
        if (state == null || state == "TRANSITIONING" || state == "PLAYING") return false
        return nowMs() - lastPlayRetryAtMs >= playRetryIntervalMs
    }

    /** Records that a Play retry was issued (regardless of its result). */
    fun notePlayRetry() {
        playRetriesLeft--
        lastPlayRetryAtMs = nowMs()
    }

    companion object {
        const val MAX_PLAY_RETRIES = 2
    }
}
