package com.arflix.tv.ui.screens.player.subtitles

import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.audio.BaseAudioProcessor
import androidx.media3.common.audio.AudioProcessor.AudioFormat
import androidx.media3.common.audio.AudioProcessor.UnhandledAudioFormatException
import androidx.media3.common.util.UnstableApi
import java.nio.ByteBuffer
import java.nio.ByteOrder

private const val TARGET_RATE = 16000
private const val CHUNK_SAMPLES = TARGET_RATE / 10  // 100 ms per chunk

@OptIn(UnstableApi::class)
class AudioCaptureProcessor : BaseAudioProcessor() {

    /** Set to receive (pcm16kHz, captureTimeMs) pairs; null = capture inactive. */
    @Volatile var onChunk: ((ByteArray, Long) -> Unit)? = null

    // Resampler state
    private var srcStep = 1.0          // input samples per output sample
    private var srcFrac = 0.0          // fractional position within current input block
    private val accum = ArrayList<Short>(CHUNK_SAMPLES * 2)
    private var prevMono: Short = 0    // last sample of previous block for interpolation

    @Throws(UnhandledAudioFormatException::class)
    override fun onConfigure(inputAudioFormat: AudioFormat): AudioFormat {
        Log.d("AudioCapture", "onConfigure encoding=${inputAudioFormat.encoding} " +
            "rate=${inputAudioFormat.sampleRate} ch=${inputAudioFormat.channelCount} " +
            "onChunk=${onChunk != null}")
        if (inputAudioFormat.encoding != C.ENCODING_PCM_16BIT &&
            inputAudioFormat.encoding != C.ENCODING_PCM_FLOAT) {
            // Still passthrough — return same format, just won't capture.
        }
        srcStep = if (inputAudioFormat.sampleRate > 0)
            inputAudioFormat.sampleRate.toDouble() / TARGET_RATE else 1.0
        srcFrac = 0.0
        accum.clear()
        prevMono = 0
        return inputAudioFormat
    }

    override fun onFlush() {
        srcFrac = 0.0
        accum.clear()
        prevMono = 0
    }

    override fun onReset() {
        srcFrac = 0.0
        accum.clear()
        prevMono = 0
    }

    private var queueInputCallCount = 0
    override fun queueInput(inputBuffer: ByteBuffer) {
        queueInputCallCount++
        if (queueInputCallCount <= 3 || queueInputCallCount % 500 == 0) {
            Log.d("AudioCapture", "queueInput #$queueInputCallCount remaining=${inputBuffer.remaining()} " +
                "onChunk=${onChunk != null} encoding=${inputAudioFormat.encoding}")
        }
        val size = inputBuffer.remaining()
        val bytes = ByteArray(size)
        inputBuffer.get(bytes)  // advances position to limit

        // Write passthrough output
        replaceOutputBuffer(size).put(bytes).flip()

        val callback = onChunk ?: return
        val fmt = inputAudioFormat
        if (fmt == AudioFormat.NOT_SET) return

        when (fmt.encoding) {
            C.ENCODING_PCM_16BIT -> processPcm16(bytes, fmt.channelCount, callback)
            C.ENCODING_PCM_FLOAT -> processPcmFloat(bytes, fmt.channelCount, callback)
            // Other encodings: skip capture, passthrough only
        }
    }

    private fun processPcm16(bytes: ByteArray, channels: Int, callback: (ByteArray, Long) -> Unit) {
        val shorts = ShortArray(bytes.size / 2)
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts)
        resampleAndEmit(mixToMono(shorts, channels), callback)
    }

    private fun processPcmFloat(bytes: ByteArray, channels: Int, callback: (ByteArray, Long) -> Unit) {
        val floatBuf = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer()
        val count = floatBuf.remaining()
        val shorts = ShortArray(count)
        for (i in 0 until count) {
            val sample = floatBuf.get().coerceIn(-1f, 1f)
            shorts[i] = (sample * Short.MAX_VALUE).toInt().toShort()
        }
        resampleAndEmit(mixToMono(shorts, channels), callback)
    }

    private fun mixToMono(samples: ShortArray, channels: Int): ShortArray {
        if (channels == 1) return samples
        if (channels == 6) {
            // 5.1 channel order: FL(0), FR(1), C(2), LFE(3), BL(4), BR(5)
            // ITU-R BS.775: mono = C*1.0 + (FL+FR)*0.707 + (BL+BR)*0.707 + LFE*0.0
            // Integer arithmetic: multiply by 1000, sum of weights ≈ 3828, divide by 3828
            return ShortArray(samples.size / 6) { i ->
                val fl = samples[i * 6 + 0].toLong()
                val fr = samples[i * 6 + 1].toLong()
                val c  = samples[i * 6 + 2].toLong()
                val bl = samples[i * 6 + 4].toLong()
                val br = samples[i * 6 + 5].toLong()
                ((c * 1000 + (fl + fr + bl + br) * 707) / 3828).toInt().toShort()
            }
        }
        return ShortArray(samples.size / channels) { i ->
            var sum = 0L
            for (c in 0 until channels) sum += samples[i * channels + c]
            (sum / channels).toShort()
        }
    }

    private fun resampleAndEmit(mono: ShortArray, callback: (ByteArray, Long) -> Unit) {
        if (mono.isEmpty()) return
        var pos = srcFrac
        while (pos < mono.size) {
            val idx = pos.toInt().coerceIn(0, mono.size - 1)
            val next = if (idx + 1 < mono.size) mono[idx + 1] else if (mono.size > 0) prevMono else mono[0]
            val frac = pos - idx
            val sample = ((mono[idx] * (1.0 - frac)) + (next * frac)).toInt().toShort()
            accum.add(sample)
            if (accum.size >= CHUNK_SAMPLES) {
                callback(shortsToBytes(accum), System.currentTimeMillis())
                accum.clear()
            }
            pos += srcStep
        }
        srcFrac = pos - mono.size
        if (mono.isNotEmpty()) prevMono = mono.last()
    }

    private fun shortsToBytes(shorts: List<Short>): ByteArray {
        val buf = ByteBuffer.allocate(shorts.size * 2).order(ByteOrder.LITTLE_ENDIAN)
        for (s in shorts) buf.putShort(s)
        return buf.array()
    }
}
