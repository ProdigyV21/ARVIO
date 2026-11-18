package com.arflix.tv.ui.screens.player.engine.exoplayer

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Build
import androidx.media3.common.C
import androidx.media3.common.Format
import androidx.media3.common.MimeTypes
import androidx.media3.common.TrackSelectionOverride
import androidx.media3.common.Tracks

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal fun compatibleSurroundTrack(
    failed: Format?,
    tracks: Tracks,
    routeSupportsAc3: (Int) -> Boolean = ::supportsDirectAc3
): TrackSelectionOverride? {
    if (failed == null || failed.channelCount < 6 || failed.sampleMimeType == MimeTypes.AUDIO_AC3 ||
        failed.cryptoType != C.CRYPTO_TYPE_NONE
    ) return null
    for (group in tracks.groups) {
        if (group.type != C.TRACK_TYPE_AUDIO) continue
        for (index in 0 until group.length) {
            val format = group.getTrackFormat(index)
            if (!group.isTrackSupported(index) || format.sampleMimeType != MimeTypes.AUDIO_AC3 ||
                format.channelCount != 6 || format.sampleRate <= 0 || format.cryptoType != C.CRYPTO_TYPE_NONE ||
                format.roleFlags != failed.roleFlags || !sameLanguage(format.language, failed.language)
            ) continue
            if (routeSupportsAc3(format.sampleRate)) return TrackSelectionOverride(group.mediaTrackGroup, index)
        }
    }
    return null
}

private fun sameLanguage(first: String?, second: String?): Boolean =
    first?.takeIf { it.isNotBlank() && it != "und" }?.equals(second, ignoreCase = true) == true

private fun supportsDirectAc3(sampleRate: Int): Boolean {
    if (Build.VERSION.SDK_INT < 29) return false
    return runCatching {
        val format = AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_AC3)
            .setSampleRate(sampleRate)
            .setChannelMask(AudioFormat.CHANNEL_OUT_5POINT1)
            .build()
        val media = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
            .build()
        if (Build.VERSION.SDK_INT >= 33) {
            android.media.AudioManager.getDirectPlaybackSupport(format, media) and
                android.media.AudioManager.DIRECT_PLAYBACK_BITSTREAM_SUPPORTED != 0
        } else {
            AudioTrack.isDirectPlaybackSupported(format, media)
        }
    }.getOrDefault(false)
}
