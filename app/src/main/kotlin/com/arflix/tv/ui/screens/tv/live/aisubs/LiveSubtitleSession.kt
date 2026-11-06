package com.arflix.tv.ui.screens.tv.live.aisubs

import android.content.Context
import android.os.SystemClock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

/** What the viewer should be told about Live TV AI subtitles. */
internal sealed interface LiveAiSubsStatus {
    data object Off : LiveAiSubsStatus
    data object Running : LiveAiSubsStatus
    data object NoKey : LiveAiSubsStatus
    data object InvalidKey : LiveAiSubsStatus
    /** HTTP 429: sending pauses until [untilMs] (elapsed realtime). */
    data class QuotaPaused(val untilMs: Long) : LiveAiSubsStatus
    data object Offline : LiveAiSubsStatus
    data class Unsupported(val mimeType: String) : LiveAiSubsStatus
}

/**
 * One piece of work for the AI: a stretch of speech, or a sentence of the channel's captions.
 * [generation] is the channel source it was captured from (see [LiveSpeechPipeline.generation]).
 */
internal sealed interface LiveWork {
    val generation: Int
    class Audio(val segment: SpeechSegment, override val generation: Int) : LiveWork
    class Caption(val sentence: CaptionSentence, override val generation: Int) : LiveWork
}

/**
 * Live TV AI subtitles for the one Live TV player: [pipeline] cuts speech segments from the
 * channel's audio ahead of playback, [engine] turns each into timed lines, and [scheduler] shows
 * them when playback reaches them. The UI calls [tick] with the player position about every
 * 100 ms and observes [cue] and [status].
 *
 * Requests: at most [MAX_IN_FLIGHT] at once, results applied in segment order. A segment that
 * could no longer be shown in time is not sent (cost). Main-thread confined except for segment
 * hand-off from the decoder thread.
 */
internal class LiveSubtitleSession(
    context: Context,
    private val engine: LiveSubtitleEngine,
    private val captionTranslator: LiveCaptionTranslator,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val segments = Channel<LiveWork>(capacity = 8, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    /**
     * Captions first: while the channel's own captions keep coming (up to [CAPTION_HOLDOVER_US]
     * after the last one), its audio is not sent. When they stop, the audio takes over again.
     */
    @Volatile private var captionsUntilUs = Long.MIN_VALUE
    private val inFlight = Semaphore(MAX_IN_FLIGHT)

    val pipeline = LiveSpeechPipeline(
        context = context,
        onSegment = { segment, generation -> segments.trySend(LiveWork.Audio(segment, generation)) },
        onCaption = { sentence, generation ->
            captionsUntilUs = sentence.endUs + CAPTION_HOLDOVER_US
            segments.trySend(LiveWork.Caption(sentence, generation))
        },
        onUnsupportedAudio = { mime -> scope.launch { setStatus(LiveAiSubsStatus.Unsupported(mime)) } },
    )
    private val scheduler = LiveCueScheduler()

    private val _cue = MutableStateFlow<LiveCue?>(null)
    val cue: StateFlow<LiveCue?> = _cue.asStateFlow()

    private val _status = MutableStateFlow<LiveAiSubsStatus>(LiveAiSubsStatus.Off)
    val status: StateFlow<LiveAiSubsStatus> = _status.asStateFlow()

    private var languageName = "English"
    private var rtl = false
    private var running = false
    private var epoch = 0
    private var positionUs = 0L
    private var quotaBackoffMs = QUOTA_BACKOFF_START_MS
    private var pausedUntilMs = 0L
    private val recentLines = ArrayDeque<String>()
    private val latencies = ArrayDeque<Long>()
    private var worker: Job? = null

    /** Measured p95 of the AI's answer time, for the Auto delay. */
    val latencyP95Ms: Long? get() = percentile95(latencies.toList())

    fun setTargetLanguage(name: String, isRtl: Boolean) {
        if (name != languageName) recentLines.clear()
        languageName = name
        rtl = isRtl
    }

    /**
     * Starts or stops subtitling. [hasKey] false reports [LiveAiSubsStatus.NoKey]. Capture only
     * runs while [capture] (fullscreen and playing), to keep cost down.
     */
    fun update(enabled: Boolean, hasKey: Boolean, capture: Boolean) {
        if (!enabled) {
            stop()
            return
        }
        if (!hasKey) {
            stop()
            setStatus(LiveAiSubsStatus.NoKey)
            return
        }
        if (!running) {
            running = true
            if (_status.value is LiveAiSubsStatus.Off || _status.value is LiveAiSubsStatus.NoKey) {
                setStatus(LiveAiSubsStatus.Running)
            }
            startWorker()
        }
        pipeline.active = capture && _status.value !is LiveAiSubsStatus.InvalidKey &&
            _status.value !is LiveAiSubsStatus.Unsupported
    }

    /** A new channel (or a re-prepared source): nothing from before may show. */
    fun onNewSource() {
        epoch++
        captionsUntilUs = Long.MIN_VALUE
        scheduler.clear()
        recentLines.clear()
        _cue.value = null
        // A channel that could not be decoded says nothing about the next one.
        if (_status.value is LiveAiSubsStatus.Unsupported) setStatus(if (running) LiveAiSubsStatus.Running else LiveAiSubsStatus.Off)
    }

    /** The key was changed in settings: try again. */
    fun onKeyChanged() {
        if (_status.value is LiveAiSubsStatus.InvalidKey || _status.value is LiveAiSubsStatus.NoKey) {
            setStatus(if (running) LiveAiSubsStatus.Running else LiveAiSubsStatus.Off)
        }
    }

    /** UI thread, about every 100 ms. */
    fun tick(periodPositionUs: Long, playing: Boolean) {
        positionUs = periodPositionUs
        pipeline.onPosition(periodPositionUs, playing)
        val now = SystemClock.elapsedRealtime()
        (_status.value as? LiveAiSubsStatus.QuotaPaused)?.let { if (now >= it.untilMs) setStatus(LiveAiSubsStatus.Running) }
        _cue.value = if (running) scheduler.cueAt(periodPositionUs) else null
    }

    fun release() {
        stop()
        pipeline.release()
        scope.cancel()
    }

    private fun stop() {
        running = false
        pipeline.active = false
        worker?.cancel()
        worker = null
        epoch++
        scheduler.clear()
        _cue.value = null
        if (_status.value !is LiveAiSubsStatus.Off) setStatus(LiveAiSubsStatus.Off)
    }

    private fun setStatus(status: LiveAiSubsStatus) {
        if (_status.value != status) LiveAiSubsLog.i("status: $status")
        _status.value = status
    }

    private fun startWorker() {
        worker?.cancel()
        // Drop anything queued while stopped.
        while (segments.tryReceive().isSuccess) Unit
        worker = scope.launch {
            val ordered = Channel<Pair<Int, Deferred<LiveEngineResult?>>>(capacity = MAX_IN_FLIGHT)
            launch {
                for ((resultEpoch, deferred) in ordered) {
                    val result = deferred.await() ?: continue
                    if (resultEpoch == epoch) apply(result)
                }
            }
            for (work in segments) {
                // Waits for a free request slot first: the channel can change while waiting.
                inFlight.acquire()
                // Captured from an earlier channel (or before a restart): never sent, never shown.
                if (work.generation != pipeline.generation || !shouldSend(work)) {
                    inFlight.release()
                    continue
                }
                val sentEpoch = epoch
                val context = recentLines.toList()
                val name = languageName
                val isRtl = rtl
                val deferred = async {
                    try {
                        val started = SystemClock.elapsedRealtime()
                        val result = when (work) {
                            is LiveWork.Audio -> engine.process(work.segment, context, name, isRtl)
                            is LiveWork.Caption -> captionTranslator.translate(work.sentence, name, isRtl)
                        }
                        if (result is LiveEngineResult.Cues) recordLatency(SystemClock.elapsedRealtime() - started)
                        result
                    } finally {
                        inFlight.release()
                    }
                }
                ordered.send(sentEpoch to deferred)
            }
        }
    }

    private fun shouldSend(work: LiveWork): Boolean {
        val (startUs, endUs) = when (work) {
            is LiveWork.Audio -> work.segment.startUs to work.segment.endUs
            is LiveWork.Caption -> work.sentence.startUs to work.sentence.endUs
        }
        // The channel's captions cover this stretch: no need to listen to its audio.
        if (work is LiveWork.Audio && startUs <= captionsUntilUs) return false
        val status = _status.value
        if (status is LiveAiSubsStatus.InvalidKey || status is LiveAiSubsStatus.Unsupported) return false
        val now = SystemClock.elapsedRealtime()
        if (status is LiveAiSubsStatus.QuotaPaused && now < status.untilMs) return false
        if (status is LiveAiSubsStatus.Offline && now < pausedUntilMs) return false
        // Its window would already be over when an answer could arrive.
        return endUs + LiveCueScheduler.MIN_DISPLAY_US > pipeline.estimatedPositionUs() + MIN_ANSWER_US
    }

    private fun apply(result: LiveEngineResult) {
        when (result) {
            is LiveEngineResult.Cues -> {
                quotaBackoffMs = QUOTA_BACKOFF_START_MS
                if (_status.value is LiveAiSubsStatus.Offline || _status.value is LiveAiSubsStatus.QuotaPaused) {
                    setStatus(LiveAiSubsStatus.Running)
                }
                scheduler.add(result.cues, positionUs)
                result.cues.forEach {
                    recentLines.addLast(it.text)
                    while (recentLines.size > CONTEXT_LINES) recentLines.removeFirst()
                }
            }
            LiveEngineResult.InvalidKey -> setStatus(LiveAiSubsStatus.InvalidKey)
            LiveEngineResult.QuotaExceeded -> {
                setStatus(LiveAiSubsStatus.QuotaPaused(SystemClock.elapsedRealtime() + quotaBackoffMs))
                quotaBackoffMs = (quotaBackoffMs * 2).coerceAtMost(QUOTA_BACKOFF_MAX_MS)
            }
            LiveEngineResult.Offline -> {
                pausedUntilMs = SystemClock.elapsedRealtime() + OFFLINE_RETRY_MS
                setStatus(LiveAiSubsStatus.Offline)
            }
            is LiveEngineResult.Failed -> LiveAiSubsLog.w("segment failed: ${result.reason}")
        }
    }

    private fun recordLatency(ms: Long) {
        latencies.addLast(ms)
        while (latencies.size > LATENCY_SAMPLES) latencies.removeFirst()
    }

    private companion object {
        const val MAX_IN_FLIGHT = 2
        const val CONTEXT_LINES = 3
        const val LATENCY_SAMPLES = 30
        /** The fastest a segment can realistically come back. */
        const val MIN_ANSWER_US = 800_000L
        const val QUOTA_BACKOFF_START_MS = 30_000L
        const val QUOTA_BACKOFF_MAX_MS = 240_000L
        const val OFFLINE_RETRY_MS = 10_000L
        /** How long after the last caption the audio stays unused (captions pause between lines). */
        const val CAPTION_HOLDOVER_US = 20_000_000L
    }
}
