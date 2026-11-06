@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.tv.live.aisubs

import android.content.Context
import android.os.SystemClock
import androidx.media3.common.Format
import com.arflix.tv.R
import com.arflix.tv.ui.screens.player.audiosync.AudioSampleSink
import com.arflix.tv.ui.screens.player.audiosync.AudioSyncDecoder
import com.arflix.tv.ui.screens.player.audiosync.PlaybackPcmListener
import com.arflix.tv.ui.screens.player.audiosync.SileroVad
import com.arflix.tv.ui.screens.player.audiosync.SileroVadWeights
import com.arflix.tv.ui.screens.player.audiosync.SpeechAnalyzer
import com.arflix.tv.ui.screens.player.audiosync.SpeechTimeline
import com.arflix.tv.ui.screens.player.audiosync.asr.SpeechSegmenter

/**
 * Phase 0 proof of concept for Live TV AI subtitles: copies the channel's audio at the extractor
 * (ahead of playback), decodes it on a side thread, cuts it into speech segments with Silero and
 * logs everything needed to judge the design, under tag `LiveAiSubs`:
 *
 * - tapped sample times next to the player's period position, and how far ahead the tap runs;
 * - each segment's start/end in media time, and how long before its playback it was ready;
 * - with [LiveAiSubsDebug.verify], the same VAD run on the audio as it is played (its times come
 *   from the audio renderer); matching segments from both sides must start at the same media
 *   time if the tap and playback share one time base.
 *
 * Nothing here talks to an AI service or draws anything. One probe lives as long as the Live TV
 * player; [newSink] is called for every prepared channel so stale loads of the previous channel
 * can never feed the new one.
 */
internal class LiveSpeechProbe(context: Context) {
    private val appContext = context.applicationContext

    @Volatile
    private var weights: SileroVadWeights? = null

    @Volatile
    private var generation = 0

    @Volatile
    private var decoder: AudioSyncDecoder? = null

    /** Whether the player is fullscreen; the probe only runs there. Set on the UI thread. */
    @Volatile
    var fullscreen: Boolean = false

    // Last known player position, written on the UI thread, read by the other threads to estimate
    // where playback is right now.
    @Volatile private var positionUs = UNKNOWN
    @Volatile private var positionAtMs = 0L
    @Volatile private var positionPlaying = false

    // Tap statistics.
    @Volatile private var lastTapUs = UNKNOWN
    @Volatile private var lastPlayedUs = UNKNOWN
    @Volatile private var tapSamples = 0L
    @Volatile private var droppedSamples = 0L
    @Volatile private var tapSegments = 0
    @Volatile private var playedSegments = 0
    private var lastStatsMs = 0L

    /** Recent look-ahead segment starts, for matching the played side's segments. */
    private val recentTapStarts = ArrayDeque<Long>()
    private val deltasMs = ArrayList<Long>()

    init {
        LiveAiSubsDebug.start()
        Thread({
            weights = runCatching {
                appContext.resources.openRawResource(R.raw.silero_vad_v5_16k).use { SileroVadWeights.read(it) }
            }.onFailure { LiveAiSubsLog.w("could not load VAD weights: ${it.message}") }.getOrNull()
        }, "LiveAiSubsInit").apply {
            isDaemon = true
            start()
        }
    }

    private val active: Boolean
        get() = LiveAiSubsDebug.enabled && fullscreen && weights != null

    /** A sink for one prepared channel; it goes quiet as soon as another channel is prepared. */
    fun newSink(label: String): AudioSampleSink {
        val sinkGeneration = ++generation
        synchronized(recentTapStarts) {
            recentTapStarts.clear()
            deltasMs.clear()
        }
        lastTapUs = UNKNOWN
        lastPlayedUs = UNKNOWN
        tapSamples = 0L
        droppedSamples = 0L
        tapSegments = 0
        playedSegments = 0
        decoder?.discontinuity()
        if (LiveAiSubsDebug.enabled) LiveAiSubsLog.i("new channel source gen=$sinkGeneration ($label)")
        return ChannelSink(sinkGeneration)
    }

    /** Audio as it is played, from the Live TV player's audio sink (debug builds only). */
    val playbackListener: PlaybackPcmListener = object : PlaybackPcmListener {
        override fun wantsPlaybackPcm(mediaTimeUs: Long, durationUs: Long): Boolean =
            LiveAiSubsDebug.verify && active && decoder != null

        override fun onPlaybackPcm(mono: FloatArray, frames: Int, sampleRate: Int, mediaTimeUs: Long) {
            lastPlayedUs = mediaTimeUs
            decoder?.offerPlaybackPcm(mono, frames, sampleRate, mediaTimeUs)
        }
    }

    /**
     * Called on the UI thread about every half second. [periodPositionUs] is the playback position
     * on the period timeline (the window position plus the window's offset in the period).
     */
    fun onPosition(periodPositionUs: Long, bufferedAheadMs: Long, playing: Boolean) {
        positionUs = periodPositionUs
        positionAtMs = SystemClock.elapsedRealtime()
        positionPlaying = playing
        if (!active) return
        val now = positionAtMs
        if (now - lastStatsMs < STATS_INTERVAL_MS) return
        lastStatsMs = now
        val tap = lastTapUs
        val played = lastPlayedUs
        val median = synchronized(recentTapStarts) { deltasMs.sorted().let { if (it.isEmpty()) null else it[it.size / 2] } }
        LiveAiSubsLog.i(
            "stats pos=${periodPositionUs / 1_000}ms playing=$playing bufferedAhead=${bufferedAheadMs}ms " +
                "tapAhead=${if (tap == UNKNOWN) "-" else "${(tap - periodPositionUs) / 1_000}ms"} " +
                "playedAhead=${if (played == UNKNOWN) "-" else "${(played - periodPositionUs) / 1_000}ms"} " +
                "samples=$tapSamples dropped=$droppedSamples segments=$tapSegments played=$playedSegments " +
                "matchMedianDelta=${median ?: "-"}ms",
        )
    }

    fun release() {
        generation++
        decoder?.release()
        decoder = null
    }

    /** Where playback is right now, extrapolated from the last UI-thread report. */
    private fun estimatedPositionUs(): Long {
        val position = positionUs
        if (position == UNKNOWN) return UNKNOWN
        return if (positionPlaying) position + (SystemClock.elapsedRealtime() - positionAtMs) * 1_000L else position
    }

    private fun ensureDecoder(): AudioSyncDecoder? {
        decoder?.let { return it }
        val vadWeights = weights ?: return null
        synchronized(this) {
            decoder?.let { return it }
            val tapAnalyzer = SpeechAnalyzer(SileroVad(vadWeights), SpeechTimeline())
            val playedAnalyzer = SpeechAnalyzer(SileroVad(vadWeights), SpeechTimeline())
            tapAnalyzer.chunkListener = SpeechSegmenter(LIVE_MAX_SEGMENT_FRAMES) { startFrame, samples ->
                onTapSegment(startFrame, samples.size)
            }
            playedAnalyzer.chunkListener = SpeechSegmenter(LIVE_MAX_SEGMENT_FRAMES) { startFrame, samples ->
                onPlayedSegment(startFrame, samples.size)
            }
            return AudioSyncDecoder(tapAnalyzer, playedAnalyzer) { mime ->
                LiveAiSubsLog.w("no look-ahead decoder for $mime: AI subtitles not supported on this channel")
            }.also { decoder = it }
        }
    }

    private fun onTapSegment(startFrame: Int, sampleCount: Int) {
        val startUs = startFrame.toLong() * SpeechTimeline.FRAME_DURATION_US
        val endUs = startUs + sampleCount * 1_000_000L / SileroVad.SAMPLE_RATE
        val position = estimatedPositionUs()
        tapSegments++
        synchronized(recentTapStarts) {
            recentTapStarts.addLast(startUs)
            while (recentTapStarts.size > RECENT_SEGMENTS) recentTapStarts.removeFirst()
        }
        LiveAiSubsLog.i(
            "segment tap start=${startUs / 1_000}ms end=${endUs / 1_000}ms len=${(endUs - startUs) / 1_000}ms " +
                "readyBeforeStart=${if (position == UNKNOWN) "-" else "${(startUs - position) / 1_000}ms"} " +
                "readyBeforeEnd=${if (position == UNKNOWN) "-" else "${(endUs - position) / 1_000}ms"}",
        )
    }

    private fun onPlayedSegment(startFrame: Int, sampleCount: Int) {
        val startUs = startFrame.toLong() * SpeechTimeline.FRAME_DURATION_US
        val endUs = startUs + sampleCount * 1_000_000L / SileroVad.SAMPLE_RATE
        playedSegments++
        val match = synchronized(recentTapStarts) {
            recentTapStarts.minByOrNull { kotlin.math.abs(it - startUs) }
                ?.takeIf { kotlin.math.abs(it - startUs) <= MATCH_WINDOW_US }
                ?.also { deltasMs += (startUs - it) / 1_000; if (deltasMs.size > 200) deltasMs.removeAt(0) }
        }
        LiveAiSubsLog.i(
            "segment played start=${startUs / 1_000}ms end=${endUs / 1_000}ms " +
                "tapDelta=${if (match == null) "unmatched" else "${(startUs - match) / 1_000}ms"}",
        )
    }

    private inner class ChannelSink(private val sinkGeneration: Int) : AudioSampleSink {
        /** A channel can carry several audio tracks; the first one seen is the one analysed. */
        @Volatile private var lockedTrackId: String? = null
        private var ignoredLogged = false

        private var formatLogged: Format? = null

        override fun wantsSamples(format: Format): Boolean {
            if (sinkGeneration != generation || !active) return false
            if (formatLogged !== format) {
                formatLogged = format
                LiveAiSubsLog.i(
                    "audio format offered: id=${format.id} mime=${format.sampleMimeType} codecs=${format.codecs} " +
                        "accepted=${ensureDecoder()?.accepts(format)}",
                )
            }
            val locked = lockedTrackId
            if (locked != null && locked != (format.id ?: NO_ID)) {
                if (!ignoredLogged) {
                    ignoredLogged = true
                    LiveAiSubsLog.i("ignoring second audio track ${format.id} (${format.sampleMimeType}); analysing $locked")
                }
                return false
            }
            val current = ensureDecoder() ?: return false
            return current.accepts(format)
        }

        override fun onSample(format: Format, timeUs: Long, data: ByteArray, offset: Int, size: Int) {
            if (sinkGeneration != generation) return
            if (lockedTrackId == null) {
                lockedTrackId = format.id ?: NO_ID
                LiveAiSubsLog.i(
                    "tapping track ${format.id}: ${format.sampleMimeType} ${format.channelCount}ch " +
                        "${format.sampleRate}Hz lang=${format.language}",
                )
            }
            val current = decoder ?: return
            val count = ++tapSamples
            lastTapUs = timeUs
            if (count == 1L || count % SAMPLE_LOG_EVERY == 0L) {
                val position = estimatedPositionUs()
                LiveAiSubsLog.i(
                    "sample #$count t=${timeUs / 1_000}ms pos=${if (position == UNKNOWN) "-" else "${position / 1_000}ms"} " +
                        "ahead=${if (position == UNKNOWN) "-" else "${(timeUs - position) / 1_000}ms"}",
                )
            }
            if (!current.offer(format, timeUs, data, offset, size)) droppedSamples++
        }

        override fun onDiscontinuity() {
            if (sinkGeneration != generation) return
            LiveAiSubsLog.i("discontinuity (extractor seek/reset) gen=$sinkGeneration")
            decoder?.discontinuity()
        }
    }

    companion object {
        private const val UNKNOWN = Long.MIN_VALUE
        private const val NO_ID = "(no id)"
        /** About 5 s: bounds how long after a sentence starts its translation can begin. */
        const val LIVE_MAX_SEGMENT_FRAMES = 156
        private const val SAMPLE_LOG_EVERY = 500L
        private const val STATS_INTERVAL_MS = 2_000L
        private const val RECENT_SEGMENTS = 64
        private const val MATCH_WINDOW_US = 1_000_000L
    }
}
