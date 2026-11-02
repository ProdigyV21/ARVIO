package com.arflix.tv.ui.screens.player.engine.exoplayer

import androidx.media3.common.C
import androidx.media3.common.Format
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.RendererCapabilities
import androidx.media3.exoplayer.audio.AudioSink
import androidx.media3.exoplayer.audio.ForwardingAudioSink
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal class AudioDecoderFallback {
    @Volatile
    var softwareCapabilities: List<RendererCapabilities> = emptyList()

    private val blockedDecoders = Collections.newSetFromMap(ConcurrentHashMap<String, Boolean>())
    private val blockedPassthrough = Collections.newSetFromMap(ConcurrentHashMap<String, Boolean>())

    fun blockPassthroughIfSupported(format: Format?): Boolean {
        if (format == null || format.sampleMimeType == MimeTypes.AUDIO_RAW ||
            !MimeTypes.isAudio(format.sampleMimeType) || format.cryptoType != C.CRYPTO_TYPE_NONE
        ) return false
        return softwareCapabilities.any {
            runCatching { RendererCapabilities.getFormatSupport(it.supportsFormat(format)) == C.FORMAT_HANDLED }
                .getOrDefault(false)
        } && blockedPassthrough.add(format.sampleMimeType!!)
    }

    fun wrapSink(delegate: AudioSink): AudioSink = object : ForwardingAudioSink(delegate) {
        override fun supportsFormat(format: Format): Boolean =
            format.sampleMimeType?.let { it in blockedPassthrough } != true && super.supportsFormat(format)

        override fun getFormatSupport(format: Format): Int =
            if (format.sampleMimeType?.let { it in blockedPassthrough } == true) AudioSink.SINK_FORMAT_UNSUPPORTED
            else super.getFormatSupport(format)
    }

    fun blockIfSupported(decoderName: String, format: Format?): Boolean {
        if (decoderName.isBlank() || format == null ||
            !MimeTypes.isAudio(format.sampleMimeType) || format.cryptoType != C.CRYPTO_TYPE_NONE
        ) return false

        // An installed extension can still lack this codec, native library, or PCM output support.
        val supported = softwareCapabilities.any { capabilities ->
            runCatching {
                RendererCapabilities.getFormatSupport(capabilities.supportsFormat(format)) == C.FORMAT_HANDLED
            }.getOrDefault(false)
        }
        return supported && blockedDecoders.add(decoderName)
    }

    fun wrapSelector(delegate: MediaCodecSelector): MediaCodecSelector =
        MediaCodecSelector { mimeType, requiresSecureDecoder, requiresTunnelingDecoder ->
            val infos = delegate.getDecoderInfos(mimeType, requiresSecureDecoder, requiresTunnelingDecoder)
            if (blockedDecoders.isEmpty()) infos else infos.filterNot { it.name in blockedDecoders }
        }
}
