package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.VideoSize

data class LivePlaybackQuality(val channelId: String, val quality: Quality)

internal fun qualityFromVideoSize(width: Int, height: Int): Quality = when {
    width <= 0 || height <= 0 -> Quality.UNKNOWN
    width >= 3840 || height >= 2160 -> Quality.K4
    width >= 1920 || height >= 1080 -> Quality.FHD
    width >= 1280 || height >= 720 -> Quality.HD
    else -> Quality.SD
}

internal fun EnrichedChannel.displayQuality(playback: LivePlaybackQuality?): Quality =
    playback?.takeIf { it.channelId == id && it.quality != Quality.UNKNOWN }?.quality ?: quality

/** Observes only the stream already playing; never probes other provider streams. */
internal class LivePlaybackQualityListener(
    private val player: Player,
    private val onQuality: (LivePlaybackQuality?) -> Unit,
) : Player.Listener {
    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        onQuality(null)
    }

    override fun onVideoSizeChanged(videoSize: VideoSize) {
        update(videoSize)
    }

    override fun onRenderedFirstFrame() {
        // A new channel can have the same dimensions as the previous one.
        update(player.videoSize)
    }

    private fun update(size: VideoSize) {
        val channelId = player.currentMediaItem?.mediaId?.takeIf { it.isNotBlank() }
        val quality = qualityFromVideoSize(size.width, size.height)
        onQuality(
            if (channelId != null && quality != Quality.UNKNOWN) LivePlaybackQuality(channelId, quality)
            else null
        )
    }
}
