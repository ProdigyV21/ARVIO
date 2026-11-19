package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.Player

/** Pausing alone leaves the stream loader alive when Android keeps the screen in memory. */
internal class LiveTvPlaybackSession(
    private val player: Player,
    private val cancelRequests: () -> Unit,
) {
    private var suspended = false
    private var resumePlayback = false

    /** A retune starts playing, so the caller reapplies a pause from this. */
    val resumesPlaying: Boolean get() = resumePlayback

    fun suspend() {
        if (suspended) return
        suspended = true
        resumePlayback = player.playWhenReady
        player.pause()
        player.stop()
        cancelRequests()
    }

    /** Returns true when the retained source was dropped, so the caller must tune again. */
    fun resume(isLive: Boolean, allowed: Boolean, retainedSourceUsable: Boolean = true): Boolean {
        if (!suspended || !allowed) return false
        suspended = false
        if (player.mediaItemCount == 0) return false
        if (!retainedSourceUsable) {
            player.clearMediaItems()
            return true
        }
        if (isLive) player.seekToDefaultPosition()
        player.prepare()
        player.playWhenReady = resumePlayback
        return false
    }
}
