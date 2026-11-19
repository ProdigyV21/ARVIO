package com.arflix.tv.ui.screens.player.audiosync.asr

import com.arflix.tv.ui.screens.player.audiosync.SileroVad
import com.arflix.tv.ui.screens.player.audiosync.SpeechAnalyzer

/**
 * Cuts the analysed 16 kHz audio into speech segments using the Silero probabilities that are
 * already being computed, so the recogniser only ever sees dialogue: no silence, music-only
 * stretches or effects. Segments are at most [maxSegmentFrames] long so words come back quickly.
 */
internal class SpeechSegmenter(
    /**
     * Longest segment in 32 ms frames. Live subtitles keep this short (about 5 s): a line can only
     * be translated once its segment has ended, so it bounds how far ahead playback must be held.
     */
    private val maxSegmentFrames: Int = MAX_SEGMENT_FRAMES,
    /**
     * Past this length, a segment also ends at the first chunk whose speech probability dips below
     * [DIP_THRESHOLD], a pause between words, so long speech is not cut mid-word at the hard limit.
     */
    private val softMaxSegmentFrames: Int = maxSegmentFrames,
    private val onSegment: (startFrame: Int, samples: FloatArray) -> Unit,
) : SpeechAnalyzer.ChunkListener {
    private val chunk = SileroVad.CHUNK_SAMPLES
    private val preroll = ArrayDeque<FloatArray>()
    private var buffer = FloatArray(0)
    private var bufferFrames = 0
    private var inSpeech = false
    private var startFrame = 0
    private var lastSpeechFrame = 0
    private var expectedFrame = Int.MIN_VALUE

    @Synchronized
    override fun onChunk(frame: Int, chunk: FloatArray, probability: Float) {
        if (frame != expectedFrame) discard()
        expectedFrame = frame + 1
        val speech = probability >= SPEECH_THRESHOLD
        if (!inSpeech) {
            if (speech) {
                inSpeech = true
                startFrame = frame - preroll.size
                lastSpeechFrame = frame
                bufferFrames = 0
                preroll.forEach(::append)
                preroll.clear()
                append(chunk)
            } else {
                preroll.addLast(chunk.copyOf())
                if (preroll.size > PREROLL_FRAMES) preroll.removeFirst()
            }
            return
        }
        append(chunk)
        if (speech) lastSpeechFrame = frame
        val dip = bufferFrames >= softMaxSegmentFrames && probability < DIP_THRESHOLD
        if (frame - lastSpeechFrame > HANGOVER_FRAMES || bufferFrames >= maxSegmentFrames || dip) finish()
    }

    @Synchronized
    override fun onReset() = discard()

    private fun finish() {
        val keepFrames = (lastSpeechFrame - startFrame + 1 + TAIL_FRAMES).coerceAtMost(bufferFrames)
        val speechFrames = lastSpeechFrame - startFrame + 1 - PREROLL_FRAMES
        if (speechFrames >= MIN_SPEECH_FRAMES) {
            onSegment(startFrame, buffer.copyOf(keepFrames * chunk))
        }
        inSpeech = false
        bufferFrames = 0
        preroll.clear()
    }

    private fun discard() {
        inSpeech = false
        bufferFrames = 0
        preroll.clear()
        expectedFrame = Int.MIN_VALUE
    }

    private fun append(samples: FloatArray) {
        val required = (bufferFrames + 1) * chunk
        if (buffer.size < required) buffer = buffer.copyOf(maxOf(required, buffer.size * 2))
        samples.copyInto(buffer, bufferFrames * chunk, 0, chunk)
        bufferFrames++
    }

    companion object {
        private const val SPEECH_THRESHOLD = 0.5f
        private const val DIP_THRESHOLD = 0.7f
        private const val PREROLL_FRAMES = 3
        private const val TAIL_FRAMES = 4
        private const val HANGOVER_FRAMES = 8
        private const val MIN_SPEECH_FRAMES = 6
        /** 12 s. */
        const val MAX_SEGMENT_FRAMES = 375
    }
}
