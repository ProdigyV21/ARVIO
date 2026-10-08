package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.RecentPlayedSource
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.findRecentSourceMatch

/**
 * Gives a title's recently played source its chance to autoplay. Autoplay normally starts
 * within a couple of seconds, as soon as any good source appears, and Home Server / IPTV
 * lookups can answer before the addons do; the remembered source's addon may answer later.
 *
 * Armed before any lookup starts. While it holds, autoplay may start the recent source and
 * nothing else. It releases when the recent source arrives (even if the autoplay settings then
 * filter it out), when the search is over, or after [waitMs].
 */
internal class RecentSourceAutoplayGate(
    private val waitMs: Long = RECENT_SOURCE_AUTOPLAY_WAIT_MS,
) {
    var recent: RecentPlayedSource? = null
        private set
    private var deadlineMs = 0L
    private var released = true

    fun arm(recent: RecentPlayedSource?, nowMs: Long) {
        this.recent = recent
        deadlineMs = nowMs + waitMs
        released = recent == null
    }

    fun disarm() = arm(null, 0L)

    fun release() {
        released = true
    }

    /** Releases once [streams], everything found so far, include the recent source. */
    fun onStreams(streams: List<StreamSource>) {
        if (!released && findRecentSourceMatch(streams, recent) != null) released = true
    }

    fun isHolding(nowMs: Long): Boolean = !released && nowMs < deadlineMs

    fun remainingMs(nowMs: Long): Long = (deadlineMs - nowMs).coerceAtLeast(0L)

    /** The recent source among the streams autoplay may start, or null. */
    fun recentCandidate(candidates: List<StreamSource>): StreamSource? =
        findRecentSourceMatch(candidates, recent)

    companion object {
        const val RECENT_SOURCE_AUTOPLAY_WAIT_MS = 6_000L
    }
}
