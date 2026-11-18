package com.arflix.tv.ui.screens.player

import android.content.Context
import android.media.AudioDeviceInfo
import android.media.AudioFormat
import android.media.AudioManager
import android.media.MediaCodecList
import android.os.Build
import androidx.media3.common.Format
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.DecoderReuseEvaluation
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.audio.AudioSink
import java.util.Locale

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal object PlaybackCompatibility {
    @Volatile private var last: String? = null

    fun session(displayHz: () -> Float?) = Session(displayHz)

    fun report(context: Context): String {
        val codecs = runCatching { MediaCodecList(MediaCodecList.REGULAR_CODECS).codecInfos.toList() }
            .getOrDefault(emptyList()).filterNot { it.isEncoder }
        val outputs = context.getSystemService(AudioManager::class.java)
            ?.getDevices(AudioManager.GET_DEVICES_OUTPUTS).orEmpty()
        return buildString {
            appendLine("ARVIO playback compatibility")
            appendLine("Android API: ${Build.VERSION.SDK_INT}")
            appendLine("Reported decoders (not a playback test):")
            listOf("video/avc", "video/hevc", "video/av01", "video/dolby-vision", "audio/ac3", "audio/eac3", "audio/true-hd")
                .forEach { mime ->
                    appendLine("  $mime: ${if (codecs.any { mime in it.supportedTypes }) "yes" else "not reported"}")
                }
            appendLine("Available audio outputs: " + outputs.map { output ->
                val type = when (output.type) {
                    AudioDeviceInfo.TYPE_BUILTIN_SPEAKER -> "Built-in speaker"
                    AudioDeviceInfo.TYPE_HDMI -> "HDMI"
                    AudioDeviceInfo.TYPE_HDMI_ARC -> "HDMI ARC"
                    AudioDeviceInfo.TYPE_HDMI_EARC -> "HDMI eARC"
                    AudioDeviceInfo.TYPE_BLUETOOTH_A2DP -> "Bluetooth"
                    AudioDeviceInfo.TYPE_WIRED_HEADPHONES, AudioDeviceInfo.TYPE_WIRED_HEADSET -> "Wired audio"
                    else -> "Other"
                }
                val channels = output.channelCounts.filter { it in 1..32 }.distinct().sorted().joinToString("/")
                if (channels.isEmpty()) type else "$type ($channels ch)"
            }.distinct().joinToString().ifBlank { "not reported" })
            val snapshot = last
            appendLine()
            appendLine(snapshot ?: "No playback session captured.")
        }.trimEnd()
    }

    internal class Session(private val displayHz: () -> Float?) : AnalyticsListener, Player.Listener {
        private var video = "unknown"
        private var audio = "unknown"
        private var fps = 0f
        private var requestedFps = 0f
        private var dropped = 0L
        private var error = ""
        private var recovery = ""
        private var output = "unknown"

        fun reset() {
            video = "unknown"
            audio = "unknown"
            fps = 0f
            requestedFps = 0f
            dropped = 0
            error = ""
            recovery = ""
            output = "unknown"
        }

        override fun onVideoInputFormatChanged(eventTime: AnalyticsListener.EventTime, format: Format, reuse: DecoderReuseEvaluation?) {
            video = safeMime(format.sampleMimeType) + " ${format.width.coerceAtLeast(0)}x${format.height.coerceAtLeast(0)}"
            fps = format.frameRate
            publish()
        }

        override fun onAudioInputFormatChanged(eventTime: AnalyticsListener.EventTime, format: Format, reuse: DecoderReuseEvaluation?) {
            audio = safeMime(format.sampleMimeType) + " (${format.channelCount.coerceAtLeast(0)} ch input)"
            publish()
        }

        override fun onDroppedVideoFrames(eventTime: AnalyticsListener.EventTime, droppedFrames: Int, elapsedMs: Long) {
            dropped += droppedFrames
            publish()
        }

        override fun onPlayerError(failure: PlaybackException) {
            error = failure.errorCode.toString()
            publish()
        }

        fun onRecovery(step: Recovery) {
            recovery = step.label
            publish()
        }

        fun onFrameRateRequest(rate: Float) {
            requestedFps = rate
            publish()
        }

        override fun onAudioTrackInitialized(eventTime: AnalyticsListener.EventTime, audioTrackConfig: AudioSink.AudioTrackConfig) {
            output = configuredAudioOutput(audioTrackConfig)
            publish()
        }

        private fun publish() {
            last = buildString {
                appendLine("Last playback session:")
                appendLine("  Video: $video; source FPS: ${rateLabel(fps)}")
                appendLine("  AFR request: ${rateLabel(requestedFps)}; display Hz: ${rateLabel(displayHz() ?: 0f)}")
                appendLine("  Audio: $audio; output: $output")
                appendLine("  Dropped video frames: $dropped")
                if (error.isNotBlank()) appendLine("  Last player error code: $error")
                if (recovery.isNotBlank()) appendLine("  Audio recovery: $recovery")
            }.trimEnd()
        }
    }

    enum class Recovery(val label: String) {
        SOFTWARE("software audio decoder"), SURROUND_TRACK("alternate Dolby Digital 5.1 track"),
        PCM("decoded PCM"), CONSTRAINED("device-constrained audio"), DISABLED("audio disabled")
    }

    private fun safeMime(value: String?): String =
        value?.takeIf { mimePattern.matches(it) } ?: "unknown"

    private val mimePattern = Regex("(audio|video)/[a-zA-Z0-9.+_-]{1,48}")
    private fun rateLabel(rate: Float): String =
        if (rate > 0f && rate <= 500f) String.format(Locale.ROOT, "%.3f", rate) else "none"
}

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal fun configuredAudioOutput(config: AudioSink.AudioTrackConfig): String {
    val encoding = when (config.encoding) {
        AudioFormat.ENCODING_PCM_16BIT, AudioFormat.ENCODING_PCM_FLOAT -> "PCM"
        AudioFormat.ENCODING_AC3 -> "Dolby Digital"
        AudioFormat.ENCODING_E_AC3, AudioFormat.ENCODING_E_AC3_JOC -> "Dolby Digital Plus"
        AudioFormat.ENCODING_DOLBY_TRUEHD -> "Dolby TrueHD"
        AudioFormat.ENCODING_DTS, AudioFormat.ENCODING_DTS_HD -> "DTS"
        else -> "encoding ${config.encoding}"
    }
    return "$encoding (${Integer.bitCount(config.channelConfig)} ch configured)"
}
