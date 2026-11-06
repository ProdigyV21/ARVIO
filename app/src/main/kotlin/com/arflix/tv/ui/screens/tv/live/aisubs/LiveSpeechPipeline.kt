@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.tv.live.aisubs

import android.content.Context
import android.os.Process
import android.os.SystemClock
import androidx.media3.common.Format
import androidx.media3.common.MimeTypes
import com.arflix.tv.R
import com.arflix.tv.ui.screens.player.audiosync.AudioSampleSink
import com.arflix.tv.ui.screens.player.audiosync.AudioSyncDecoder
import com.arflix.tv.ui.screens.player.audiosync.SileroVad
import com.arflix.tv.ui.screens.player.audiosync.SileroVadWeights
import com.arflix.tv.ui.screens.player.audiosync.SpeechAnalyzer
import com.arflix.tv.ui.screens.player.audiosync.SpeechTimeline
import com.arflix.tv.ui.screens.player.audiosync.asr.SpeechSegmenter
import com.arflix.tv.ui.screens.tv.live.isBitmapSubtitle

/**
 * Live TV speech capture: copies the channel's compressed audio at the extractor, as it is
 * downloaded and before it is played; decodes it on a side thread; and cuts it into speech
 * segments with Silero. Each [SpeechSegment] carries its own media time, which the player's
 * period position shares (measured in Phase 0: the same 32 ms frame), so subtitles can be timed
 * exactly however late the AI answers.
 *
 * One pipeline lives as long as the Live TV player. [newSink] is called for every prepared channel
 * source; a sink from an earlier channel goes quiet at once, so a load still running for the
 * previous channel can never feed the new one. Nothing is captured unless [active].
 *
 * Threads: sinks run on the player's loading threads, segments are delivered on the decoder
 * thread, and [onPosition] comes from the UI thread.
 */
internal class LiveSpeechPipeline(
    context: Context,
    /** A speech segment, with the [generation] of the channel source its audio came from. */
    private val onSegment: (SpeechSegment, generation: Int) -> Unit,
    /** A sentence of the channel's own closed captions, read ahead of playback. */
    private val onCaption: (CaptionSentence, generation: Int) -> Unit,
    /** The channel's audio format cannot be decoded ahead of playback on this device. */
    private val onUnsupportedAudio: (mimeType: String) -> Unit,
) {
    private val appContext = context.applicationContext

    @Volatile private var weights: SileroVadWeights? = null
    /** Bumped for every prepared channel source; work tagged with an older one is stale. */
    @Volatile var generation = 0
        private set
    /**
     * The source the decoder's audio belongs to. Decoder thread: it moves to [generation] when
     * the decoder resets, which drops everything queued from before ([newSinks] queues a reset).
     */
    @Volatile private var audioGeneration = 0
    @Volatile private var decoder: AudioSyncDecoder? = null

    /** Capture only while AI subtitles are on for the channel and it is playing fullscreen. */
    @Volatile
    var active: Boolean = false

    // Where playback is, from the UI thread; extrapolated on other threads.
    @Volatile private var positionUs = UNKNOWN
    @Volatile private var positionAtMs = 0L
    @Volatile private var positionPlaying = false

    /** Media time of the newest analysed audio, to detect when analysis falls behind playback. */
    @Volatile private var analysedUs = UNKNOWN
    @Volatile private var lastTapUs = UNKNOWN

    /** Decoder thread only. */
    private var skipping = false

    @Volatile var backlogSkips: Int = 0
        private set
    @Volatile var segmentsEmitted: Int = 0
        private set

    init {
        Thread({
            weights = runCatching {
                appContext.resources.openRawResource(R.raw.silero_vad_v5_16k).use { SileroVadWeights.read(it) }
            }.onFailure { LiveAiSubsLog.w("could not load VAD weights: ${it.message}") }.getOrNull()
        }, "LiveAiSubsInit").apply {
            isDaemon = true
            start()
        }
    }

    /** True once the VAD model is loaded. */
    val ready: Boolean get() = weights != null

    /** How far ahead of playback the newest copied audio is, or null when unknown. */
    fun tapAheadUs(): Long? {
        val tap = lastTapUs
        val position = estimatedPositionUs()
        return if (tap == UNKNOWN || position == UNKNOWN) null else tap - position
    }

    /** The audio and caption sinks for one prepared channel source. */
    fun newSinks(): LiveTapSinks {
        val sinkGeneration = ++generation
        reset()
        return LiveTapSinks(ChannelSink(sinkGeneration), CaptionSink(sinkGeneration))
    }

    /** Forget everything in flight (channel change, seek, resume). */
    fun reset() {
        analysedUs = UNKNOWN
        lastTapUs = UNKNOWN
        decoder?.discontinuity()
    }

    /** From the UI thread, about every 100–250 ms: the player's period position. */
    fun onPosition(periodPositionUs: Long, playing: Boolean) {
        positionUs = periodPositionUs
        positionAtMs = SystemClock.elapsedRealtime()
        positionPlaying = playing
    }

    fun estimatedPositionUs(): Long {
        val position = positionUs
        if (position == UNKNOWN) return UNKNOWN
        return if (positionPlaying) position + (SystemClock.elapsedRealtime() - positionAtMs) * 1_000L else position
    }

    fun release() {
        generation++
        active = false
        decoder?.release()
        decoder = null
    }

    private fun ensureDecoder(): AudioSyncDecoder? {
        decoder?.let { return it }
        val vadWeights = weights ?: return null
        synchronized(this) {
            decoder?.let { return it }
            audioGeneration = generation
            val analyzer = SpeechAnalyzer(SileroVad(vadWeights), SpeechTimeline())
            val segmenter = SpeechSegmenter(
                maxSegmentFrames = MAX_SEGMENT_FRAMES,
                softMaxSegmentFrames = SOFT_MAX_SEGMENT_FRAMES,
            ) { startFrame, samples ->
                val startUs = startFrame.toLong() * SpeechTimeline.FRAME_DURATION_US
                val endUs = startUs + samples.size * 1_000_000L / SileroVad.SAMPLE_RATE
                segmentsEmitted++
                onSegment(SpeechSegment(startUs, endUs, samples), audioGeneration)
            }
            analyzer.chunkListener = object : SpeechAnalyzer.ChunkListener {
                override fun onChunk(frame: Int, chunk: FloatArray, probability: Float) {
                    val chunkUs = frame.toLong() * SpeechTimeline.FRAME_DURATION_US
                    analysedUs = chunkUs
                    if (isBehindPlayback(chunkUs)) {
                        // Once per episode: the chunks still in the decoder are behind too.
                        if (!skipping) {
                            skipping = true
                            skipBacklog()
                        }
                        return
                    }
                    skipping = false
                    segmenter.onChunk(frame, chunk, probability)
                }

                override fun onReset() {
                    audioGeneration = generation
                    segmenter.onReset()
                }
            }
            return AudioSyncDecoder(
                analyzer = analyzer,
                liveAnalyzer = analyzer,
                onUnsupportedFormat = { mime ->
                    LiveAiSubsLog.w("no look-ahead decoder for $mime")
                    onUnsupportedAudio(mime)
                },
                threadPriority = Process.THREAD_PRIORITY_LESS_FAVORABLE,
            ).also { decoder = it }
        }
    }

    /**
     * Audio that will be heard within [MIN_USEFUL_AHEAD_US] cannot be subtitled in time. On a
     * busy box (measured: a 1080p channel on a Mi Box 4) analysis can fall 10–20 s behind; going
     * on would only make every later subtitle late too, so the backlog is dropped and analysis
     * restarts on the newest downloaded audio.
     */
    private fun isBehindPlayback(chunkUs: Long): Boolean {
        val position = estimatedPositionUs()
        return position != UNKNOWN && positionPlaying && chunkUs < position + MIN_USEFUL_AHEAD_US
    }

    private fun skipBacklog() {
        backlogSkips++
        analysedUs = UNKNOWN
        LiveAiSubsLog.i("analysis fell behind playback; skipping to the newest audio (skip #$backlogSkips)")
        decoder?.discontinuity()
    }

    private inner class ChannelSink(private val sinkGeneration: Int) : AudioSampleSink {
        /** A channel can carry several audio tracks; the first one seen is the one analysed. */
        @Volatile private var lockedTrackId: String? = null

        override fun wantsSamples(format: Format): Boolean {
            if (sinkGeneration != generation || !active) return false
            val locked = lockedTrackId
            if (locked != null && locked != (format.id ?: NO_ID)) return false
            val current = ensureDecoder() ?: return false
            return current.accepts(format)
        }

        override fun onSample(format: Format, timeUs: Long, data: ByteArray, offset: Int, size: Int) {
            if (sinkGeneration != generation) return
            if (lockedTrackId == null) {
                lockedTrackId = format.id ?: NO_ID
                LiveAiSubsLog.i(
                    "tapping ${format.sampleMimeType} ${format.channelCount}ch ${format.sampleRate}Hz lang=${format.language}",
                )
            }
            // Audio already about to play is useless; don't spend the decoder on it.
            val position = estimatedPositionUs()
            if (position != UNKNOWN && positionPlaying && timeUs < position + MIN_USEFUL_AHEAD_US) return
            lastTapUs = timeUs
            decoder?.offer(format, timeUs, data, offset, size)
        }

        override fun onDiscontinuity() {
            if (sinkGeneration != generation) return
            decoder?.discontinuity()
        }
    }

    /**
     * Reads the channel's own text subtitles into sentences: CEA-608 closed captions carried in
     * the video, and text renditions (WebVTT and the like) the player parsed during extraction.
     * Bitmap subtitles (DVB) carry no text; those channels are subtitled from their audio.
     */
    private inner class CaptionSink(private val sinkGeneration: Int) : AudioSampleSink {
        private val assembler = Cea608TextAssembler { sentence ->
            if (sinkGeneration == generation) onCaption(sentence, sinkGeneration)
        }
        private val cueReader = CaptionCueReader { sentence ->
            if (sinkGeneration == generation) onCaption(sentence, sinkGeneration)
        }
        private val loggedMimes = HashSet<String>()

        override fun wantsSamples(format: Format): Boolean {
            if (sinkGeneration != generation || !active) return false
            val mime = format.sampleMimeType ?: return false
            if (synchronized(loggedMimes) { loggedMimes.add("$mime ${format.codecs} ${format.language}") }) {
                LiveAiSubsLog.i("text track offered: $mime codecs=${format.codecs} lang=${format.language}")
            }
            if (isBitmapSubtitle(format)) return false
            return mime == MimeTypes.APPLICATION_CEA608 || mime == MimeTypes.APPLICATION_MEDIA3_CUES
        }

        override fun onSample(format: Format, timeUs: Long, data: ByteArray, offset: Int, size: Int) {
            if (sinkGeneration != generation) return
            synchronized(assembler) {
                if (format.sampleMimeType == MimeTypes.APPLICATION_MEDIA3_CUES) {
                    cueReader.feed(timeUs, data, offset, size)
                } else {
                    assembler.feed(timeUs, data, offset, size)
                }
            }
        }

        override fun onDiscontinuity() {
            synchronized(assembler) {
                assembler.reset()
                cueReader.reset()
            }
        }
    }

    companion object {
        private const val UNKNOWN = Long.MIN_VALUE
        private const val NO_ID = "(no id)"
        /** 5 s hard limit: a line can only be sent once its segment has ended. */
        const val MAX_SEGMENT_FRAMES = 156
        /** From 3.5 s on, a pause between words ends the segment. */
        const val SOFT_MAX_SEGMENT_FRAMES = 110
        /** Audio closer than this to the playhead cannot be subtitled before it is heard. */
        const val MIN_USEFUL_AHEAD_US = 2_500_000L
    }
}
