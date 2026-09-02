package com.arflix.tv.ui.screens.player

import android.content.Context
import android.os.Handler
import androidx.media3.common.text.Cue
import androidx.media3.common.text.CueGroup
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.audio.AudioRendererEventListener
import androidx.media3.exoplayer.audio.AudioSink
import androidx.media3.exoplayer.audio.DefaultAudioSink
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector
import androidx.media3.exoplayer.text.TextOutput
import androidx.media3.exoplayer.video.VideoRendererEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


private object AiSubtitleRegexes {
    val BRACKET_REGEX = Regex("""\[.*?\]""")
    val MUSIC_REGEX = Regex("[♪♫]+")
}

/** Sentinel: the auto-sync drift anchor hasn't been captured on the render thread yet. */
private const val AUTO_SYNC_ANCHOR_PENDING_US = Long.MIN_VALUE

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class AiSubtitleRenderersFactory(
    context: Context,
    private val translationManager: SubtitleTranslationManager,
    private val scope: CoroutineScope
) : DefaultRenderersFactory(context) {

    val syncOffsetUs = java.util.concurrent.atomic.AtomicLong(0L)

    // AI auto-sync correction, applied renderer-side (no MediaItem rebuild, no hiccup) as
    // delay(position) = baseUs + rate·(position − anchor), Nuvio's exact mechanism. The anchor
    // self-captures at the first render call after a (re-)apply: render positions run on
    // ExoPlayer's internal offset timebase (media position + a large private offset,
    // MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US ≈ 1e12 µs) — mixing an app-side media
    // position into any slope≠0 math would scale that offset by the rate (rate·1e6 seconds!) and
    // blank the subtitles entirely. Delta-only math is immune. The rate is a Double stored as
    // raw bits for lock-free atomicity.
    private val autoSyncDelayBaseUs = java.util.concurrent.atomic.AtomicLong(0L)
    private val autoSyncRateBits = java.util.concurrent.atomic.AtomicLong(0.0.toRawBits())
    private val autoSyncRateAnchorUs = java.util.concurrent.atomic.AtomicLong(AUTO_SYNC_ANCHOR_PENDING_US)

    /**
     * Sets (or clears, with `0, 0.0`) the auto-sync correction. [delayBaseMs] must be the fitted
     * delay AT THE CURRENT PLAYBACK POSITION (`intercept + rate·now`) — the rate then evolves it
     * forward from the self-captured anchor, so `delay(T) = intercept + rate·T` holds exactly.
     */
    fun setAutoSyncTransform(delayBaseMs: Long, rate: Double) {
        autoSyncDelayBaseUs.set(delayBaseMs * 1000L)
        autoSyncRateBits.set(rate.toRawBits())
        autoSyncRateAnchorUs.set(AUTO_SYNC_ANCHOR_PENDING_US)
    }

    var audioCaptureProcessor: AudioCaptureProcessor? = null
        private set

    private val offsetRenderers = mutableListOf<SubtitleOffsetRenderer>()

    /**
     * Cue-visible intervals (startMs to endMs) of the currently selected text track, read from
     * ExoPlayer's buffered cue list via reflection — i.e. upcoming cues that haven't rendered yet.
     * Lets "Find best match" build its built-in reference from prefetched cues instead of waiting
     * for playback to reach them. Returns empty if nothing is buffered yet.
     */
    fun extractBufferedReferenceIntervals(maxCount: Int): List<Pair<Long, Long>> {
        for (renderer in offsetRenderers) {
            val intervals = renderer.extractBufferedIntervals(maxCount)
            if (intervals.isNotEmpty()) return intervals
        }
        return emptyList()
    }

    /**
     * Text of the currently-buffered cues of the selected text track. Lets "Find best match"
     * verify WHICH track the buffer belongs to (by comparing against the displayed candidate's
     * own lines) — timing-based self-detection false-positived on subs cut from the same master.
     */
    fun extractBufferedCueTexts(maxCount: Int): List<String> {
        for (renderer in offsetRenderers) {
            val texts = renderer.extractAllCueTexts()
            if (texts.isNotEmpty()) return texts.take(maxCount)
        }
        return emptyList()
    }

    /**
     * Buffered cues of the currently selected text track as (startMs, endMs, text) — the paired
     * form AI auto-sync needs: timestamps to compute offsets against the addon file, text to send
     * to the LLM for semantic matching. Same reflection walk as the interval extractor.
     */
    fun extractBufferedTimedCues(maxCount: Int): List<SubtitleSyncMatcher.TimedCue> {
        for (renderer in offsetRenderers) {
            val cues = renderer.extractBufferedTimedCues(maxCount)
            if (cues.isNotEmpty()) return cues
        }
        return emptyList()
    }

    override fun buildAudioRenderers(
        context: Context,
        extensionRendererMode: Int,
        mediaCodecSelector: MediaCodecSelector,
        enableDecoderFallback: Boolean,
        audioSink: AudioSink,
        eventHandler: Handler,
        eventListener: AudioRendererEventListener,
        out: ArrayList<Renderer>
    ) {
        // EXTENSION_RENDERER_MODE_PREFER exists only for the Amlogic/SEI *video* C2 decoder hang
        // (PlayerScreen preferExtensionDecoder). For AUDIO, FFmpeg-first software-decodes every
        // E-AC3/TrueHD/DTS stream to PCM — killing AVR bitstream passthrough — and jellyfin-ffmpeg's
        // TrueHD path fails outright. Always build audio with mode ON: the platform
        // MediaCodecAudioRenderer (whose DefaultAudioSink auto-detects AVR capabilities and
        // bitstreams via bypass) goes first; FFmpeg stays strictly a fallback for codecs the
        // device can neither passthrough nor decode.
        super.buildAudioRenderers(
            context, EXTENSION_RENDERER_MODE_ON, mediaCodecSelector,
            enableDecoderFallback, audioSink, eventHandler, eventListener, out
        )
    }

    override fun buildVideoRenderers(
        context: Context,
        extensionRendererMode: Int,
        mediaCodecSelector: MediaCodecSelector,
        enableDecoderFallback: Boolean,
        eventHandler: Handler,
        eventListener: VideoRendererEventListener,
        allowedVideoJoiningTimeMs: Long,
        out: ArrayList<Renderer>
    ) {
        // VIDEO is ALWAYS hardware-first, exactly like NuvioTV (which ships no software video
        // renderer at all and never guesses by device name). The bundled jellyfin FFmpeg *video*
        // decoder must never be PREFERRED — software 4K HEVC/DV decode can't keep up on TV boxes
        // (Homatics/Mi Box), producing audio-but-no-video → source skip, while the hardware
        // decoder plays fine. Forcing MODE_ON keeps the platform MediaCodecVideoRenderer first;
        // enableDecoderFallback + forceDisableMediaCodecAsynchronousQueueing (set on the factory)
        // handle genuine hardware failures/hangs reactively — no device-class heuristic.
        super.buildVideoRenderers(
            context, EXTENSION_RENDERER_MODE_ON, mediaCodecSelector,
            enableDecoderFallback, eventHandler, eventListener, allowedVideoJoiningTimeMs, out
        )
    }

    override fun buildAudioSink(
        context: Context,
        enableFloatOutput: Boolean,
        enableAudioTrackPlaybackParams: Boolean
    ): AudioSink {
        val capture = AudioCaptureProcessor()
        audioCaptureProcessor = capture
        return DefaultAudioSink.Builder(context)
            .setEnableFloatOutput(enableFloatOutput)
            .setEnableAudioTrackPlaybackParams(enableAudioTrackPlaybackParams)
            .setAudioProcessors(arrayOf(capture))
            .build()
    }

    override fun buildTextRenderers(
        context: Context,
        output: TextOutput,
        outputLooper: android.os.Looper,
        extensionRendererMode: Int,
        out: ArrayList<Renderer>
    ) {
        val translatingOutput = TranslatingTextOutput(
            delegate = output,
            manager = translationManager,
            outputLooper = outputLooper,
            scope = scope
        )
        val startIndex = out.size
        super.buildTextRenderers(context, translatingOutput, outputLooper, extensionRendererMode, out)
        offsetRenderers.clear()
        for (index in startIndex until out.size) {
            val offsetRenderer = SubtitleOffsetRenderer(
                baseRenderer = out[index],
                translationManager = translationManager,
                translationScope = scope,
                syncOffsetUs = syncOffsetUs,
                autoSyncDelayBaseUs = autoSyncDelayBaseUs,
                autoSyncRateBits = autoSyncRateBits,
                autoSyncRateAnchorUs = autoSyncRateAnchorUs
            )
            offsetRenderers.add(offsetRenderer)
            out[index] = offsetRenderer
        }
        // Wire first-cue callback: when the first subtitle arrives on the playback thread
        // (while TextRenderer.render() has the cue buffer populated), trigger pre-translation.
        translatingOutput.onFirstCueOnPlaybackThread = {
            offsetRenderers.forEach { it.triggerPreTranslation() }
        }
    }
}

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
private class TranslatingTextOutput(
    private val delegate: TextOutput,
    private val manager: SubtitleTranslationManager,
    outputLooper: android.os.Looper,
    private val scope: CoroutineScope
) : TextOutput {

    private val handler = Handler(outputLooper)
    @Volatile private var lastCueGroup: CueGroup? = null
    var onFirstCueOnPlaybackThread: (() -> Unit)? = null
    private var hasFiredFirstCue = false
    private var cueSerial = 0

    override fun onCues(cueGroup: CueGroup) {
        val cues = cueGroup.cues

        // Fire once when the first non-empty cue arrives while TextRenderer.render() is on the
        // call stack — the cue buffer is populated at that moment, enabling lookahead.
        if (!hasFiredFirstCue && cues.isNotEmpty() && manager.isEnabled) {
            hasFiredFirstCue = true
            onFirstCueOnPlaybackThread?.invoke()
            onFirstCueOnPlaybackThread = null
        }

        if (!manager.isEnabled) {
            lastCueGroup = cueGroup
            delegate.onCues(cueGroup)
            return
        }
        if (cues.isEmpty()) {
            // Genuine end-of-subtitle marker — update so pending translations know display cleared.
            lastCueGroup = cueGroup
            delegate.onCues(cueGroup)
            return
        }

        val rawText = extractRawText(cues)
        if (rawText.isBlank()) {
            // Cues carry no extractable text (e.g. bitmap/PGS image subtitles). They can't be
            // translated — pass the originals through so image subtitles still appear on screen
            // instead of being hidden behind a blank.
            // Translation is ON yet this source yields no text: tell the ViewModel so it can move
            // to a real text track (or turn AI off) instead of silently showing the source
            // language and looking like "AI returned English".
            manager.onUntranslatableSource?.invoke()
            lastCueGroup = cueGroup
            delegate.onCues(cueGroup)
            return
        }
        val text = if (manager.removeHearingImpaired) stripHearingImpaired(rawText) else rawText
        if (text.isBlank()) {
            // SDH sound-effect-only cue (e.g. "[CROWD CHEERING]") — hide it but do NOT update
            // lastCueGroup. Updating here would invalidate any in-flight dialogue translation
            // that arrived just before this sound-effect cue, causing the translated text to be
            // silently discarded and the user to see English instead of Hebrew on every line.
            delegate.onCues(CueGroup(emptyList(), cueGroup.presentationTimeUs))
            return
        }

        // Real translatable dialogue cue — update lastCueGroup so any stale in-flight
        // translation from the previous cue is correctly discarded.
        lastCueGroup = cueGroup
        val serial = ++cueSerial
        val cached = manager.getCached(text)
        if (cached != null) {
            delegate.onCues(buildTranslated(cueGroup, cues, cached))
            return
        }

        // Hide cues while translating, then post translated result back on the output looper.
        // The lastCueGroup guard prevents a stale translation from overwriting a newer cue.
        delegate.onCues(CueGroup(emptyList(), cueGroup.presentationTimeUs))
        val captured = cueGroup
        scope.launch {
            val translated = manager.translate(text)
            handler.post {
                if (lastCueGroup === captured) {
                    delegate.onCues(buildTranslated(captured, captured.cues, translated))
                }
            }
        }
    }

    @Deprecated("Uses the deprecated Media3 callback.")
    override fun onCues(cues: List<Cue>) {
        if (!manager.isEnabled || cues.isEmpty()) {
            delegate.onCues(cues)
            return
        }
        val rawText = extractRawText(cues)
        if (rawText.isBlank()) {
            // No translatable text (e.g. bitmap subtitles) — show originals rather than hiding.
            delegate.onCues(cues)
            return
        }
        val text = if (manager.removeHearingImpaired) stripHearingImpaired(rawText) else rawText
        if (text.isBlank()) {
            delegate.onCues(emptyList())
            return
        }
        // Cache-only path — full async translation is driven by onCues(CueGroup).
        // If translation is in-flight, hide cues so this call doesn't race and show English
        // while onCues(CueGroup) is waiting for the async result.
        val cached = manager.getCached(text)
        when {
            cached != null -> delegate.onCues(applyTranslatedLinesToCues(cues, cached))
            manager.isInFlight(text) -> delegate.onCues(emptyList())
            else -> delegate.onCues(cues)
        }
    }

    // Raw joined cue text before any hearing-impaired stripping. Blank when cues carry no
    // text at all (e.g. bitmap/PGS image subtitles, whose Cue.text is null).
    private fun extractRawText(cues: List<Cue>): String =
        cues.mapNotNull { it.text?.toString()?.trim() }
            .filter { it.isNotBlank() }
            .joinToString("\n")

    private fun stripHearingImpaired(text: String): String =
        text.replace(AiSubtitleRegexes.BRACKET_REGEX, "")
            .replace(AiSubtitleRegexes.MUSIC_REGEX, "")
            .trim()

    private fun buildTranslated(group: CueGroup, originalCues: List<Cue>, translatedText: String): CueGroup =
        CueGroup(applyTranslatedLinesToCues(originalCues, translatedText), group.presentationTimeUs)

    /**
     * Maps translated lines back to cues, respecting how many lines each original cue had.
     * A multi-line cue (text with \n) receives the corresponding translated lines so the full
     * translated text is preserved rather than only the first line being applied per cue.
     * RTL text is wrapped with RTL marks so Android's bidi algorithm places trailing
     * punctuation on the correct side.
     */
    private fun applyTranslatedLinesToCues(originalCues: List<Cue>, translatedText: String): List<Cue> {
        val translatedLines = translatedText.split("\n")
        var lineIndex = 0
        return originalCues.map { cue ->
            val originalLineCount = (cue.text?.toString() ?: "").split("\n").size
            val end = (lineIndex + originalLineCount).coerceAtMost(translatedLines.size)
            val cueText = if (lineIndex < translatedLines.size) {
                translatedLines.subList(lineIndex, end).joinToString("\n")
            } else {
                cue.text?.toString() ?: ""
            }
            lineIndex += originalLineCount
            val rtlAware = if (cueText.any { ch ->
                val dir = Character.getDirectionality(ch)
                dir == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                    dir == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
            }) "‏$cueText‏" else cueText
            cue.buildUpon().setText(android.text.SpannableString(rtlAware)).build()
        }
    }
}

/**
 * Forwarding renderer that wraps ExoPlayer's TextRenderer to provide:
 * - Immediate lookahead on first cue (via [triggerPreTranslation])
 * - Periodic 2-minute lookahead every 15 seconds
 * - Seek-aware window reset so translated cues are always fresh after scrubbing
 *
 * Cue texts are extracted from ExoPlayer's internal cue buffer via reflection,
 * supporting both the modern CuesResolver path and the legacy subtitle/nextSubtitle fields.
 */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
private class SubtitleOffsetRenderer(
    private val baseRenderer: Renderer,
    private val translationManager: SubtitleTranslationManager,
    private val translationScope: CoroutineScope,
    private val syncOffsetUs: java.util.concurrent.atomic.AtomicLong = java.util.concurrent.atomic.AtomicLong(0L),
    private val autoSyncDelayBaseUs: java.util.concurrent.atomic.AtomicLong = java.util.concurrent.atomic.AtomicLong(0L),
    private val autoSyncRateBits: java.util.concurrent.atomic.AtomicLong = java.util.concurrent.atomic.AtomicLong(0.0.toRawBits()),
    private val autoSyncRateAnchorUs: java.util.concurrent.atomic.AtomicLong = java.util.concurrent.atomic.AtomicLong(AUTO_SYNC_ANCHOR_PENDING_US)
) : Renderer by baseRenderer {

    companion object {
        private const val WINDOW_US = 2 * 60 * 1_000_000L       // 2-minute pre-translation window
        private const val PREFETCH_TRIGGER_US = 30 * 1_000_000L  // re-fetch 30 s before window end
        private const val WINDOW_CUES = 80                        // max cues per pre-translation batch
    }

    @Volatile private var preTranslatedUpToUs = Long.MIN_VALUE
    private var currentPositionUs = 0L
    @Volatile private var lastLookaheadMs = 0L
    @Volatile private var pendingSeek = false
    private var lastSeekRetryMs = 0L
    private var lastRenderPositionUs = Long.MIN_VALUE
    private var lookaheadJob: Job? = null

    override fun render(positionUs: Long, elapsedRealtimeUs: Long) {
        currentPositionUs = positionUs
        val prevPositionUs = lastRenderPositionUs
        val offset = syncOffsetUs.get()
        var adjustedUs = if (offset != 0L) (positionUs - offset).coerceAtLeast(0L) else positionUs
        // AI auto-sync: delay(position) = base + rate·(position − anchor). The anchor is captured
        // HERE, from the renderer's own position, on the first render after a (re-)apply — see
        // the timebase warning at the field declarations. Delta-only: never multiply positionUs.
        val autoSyncBaseUs = autoSyncDelayBaseUs.get()
        val autoSyncRate = Double.fromBits(autoSyncRateBits.get())
        if (autoSyncBaseUs != 0L || autoSyncRate != 0.0) {
            val driftUs = if (autoSyncRate == 0.0) {
                0L
            } else {
                autoSyncRateAnchorUs.compareAndSet(AUTO_SYNC_ANCHOR_PENDING_US, positionUs)
                val anchorUs = autoSyncRateAnchorUs.get()
                if (anchorUs == AUTO_SYNC_ANCHOR_PENDING_US) 0L
                else Math.round(autoSyncRate * (positionUs - anchorUs))
            }
            adjustedUs = (adjustedUs - autoSyncBaseUs - driftUs).coerceAtLeast(0L)
        }
        baseRenderer.render(adjustedUs, elapsedRealtimeUs)

        // Detect seeks (> 5 s jump) and reset the translation window to the new position
        if (prevPositionUs != Long.MIN_VALUE &&
            Math.abs(positionUs - prevPositionUs) > 5_000_000L) {
            preTranslatedUpToUs = positionUs
            lastLookaheadMs = 0L
            lastSeekRetryMs = 0L
            pendingSeek = true
            lookaheadJob?.cancel()
            lookaheadJob = null
        }
        lastRenderPositionUs = positionUs
        tryPeriodicLookahead()
    }

    private fun tryPeriodicLookahead() {
        if (!translationManager.isEnabled) return
        val now = System.currentTimeMillis()
        val isFreshSeek = pendingSeek
        // Periodic check every 5 s; seeks always proceed immediately
        if (!isFreshSeek && now - lastLookaheadMs < 5_000L) return
        // Throttle to avoid 60 fps spam when the cue buffer is not yet populated
        if (now - lastSeekRetryMs < 300L) return
        lastSeekRetryMs = now
        val allTexts = extractAllCueTexts()
        if (allTexts.isEmpty()) return
        lastLookaheadMs = now
        if (isFreshSeek) pendingSeek = false
        val toTranslate = allTexts.filter { translationManager.getCached(it) == null }.take(WINDOW_CUES)
        if (toTranslate.isEmpty()) return
        launchPreTranslation(toTranslate)
    }

    /**
     * Called from the playback thread via [TranslatingTextOutput.onFirstCueOnPlaybackThread]
     * while TextRenderer.render() has the subtitle buffer populated.
     */
    fun triggerPreTranslation() {
        // No AI translation active → no lookahead. tryPeriodicLookahead already checks this;
        // this first-cue trigger fires for any newly selected track (e.g. the reference track
        // "find best match" selects with AI off) and must not spend API requests either.
        if (!translationManager.isEnabled) return
        if (preTranslatedUpToUs != Long.MIN_VALUE &&
            preTranslatedUpToUs > currentPositionUs + PREFETCH_TRIGGER_US) {
            return
        }
        val allTexts = extractAllCueTexts()
        if (allTexts.isEmpty()) return
        val toTranslate = allTexts.filter { translationManager.getCached(it) == null }.take(WINDOW_CUES)
        if (toTranslate.isEmpty()) return
        preTranslatedUpToUs = currentPositionUs + WINDOW_US
        lastLookaheadMs = System.currentTimeMillis()
        launchPreTranslation(toTranslate)
    }

    private fun launchPreTranslation(texts: List<String>) {
        lookaheadJob = translationScope.launch {
            translationManager.preTranslateWindow(texts)
            // Schedule a follow-up lookahead 3s from now to catch any newly buffered cues.
            // (setting to now-2000 means 3s remain of the 5s window)
            lastLookaheadMs = System.currentTimeMillis() - 2_000L
        }
    }

    // ── Reflection-based cue extraction ──────────────────────────────────────

    fun extractAllCueTexts(): List<String> {
        val removeHI = translationManager.removeHearingImpaired
        val texts = mutableSetOf<String>()

        // Modern Media3: TextRenderer holds a MergingCuesResolver (field: cuesResolver)
        try {
            val resolverField = findField(baseRenderer.javaClass, "cuesResolver")
            val resolver = resolverField?.get(baseRenderer)
            if (resolver != null) {
                var extracted = false
                for (candidate in listOf("cuesWithTimingList", "cueGroupsByStartTime", "cueGroups", "cueGroupList", "groups")) {
                    val f = findField(resolver.javaClass, candidate) ?: continue
                    val v = f.get(resolver) ?: continue
                    val count = extractFromCollectionOrMap(v, texts, removeHI)
                    if (count > 0) {
                        extracted = true
                        break
                    }
                }
                if (!extracted) {
                    // Fall back to scanning all fields on the resolver
                    var cls: Class<*>? = resolver.javaClass
                    while (cls != null && cls != Any::class.java) {
                        for (f in cls.declaredFields) {
                            try {
                                f.isAccessible = true
                                val v = f.get(resolver) ?: continue
                                extractFromCollectionOrMap(v, texts, removeHI)
                            } catch (_: Exception) {}
                        }
                        cls = cls.superclass
                    }
                }
            }
        } catch (_: Exception) {
        }

        if (texts.isNotEmpty()) return texts.toList()

        // Legacy Media3: subtitle + nextSubtitle fields (SubtitleOutputBuffer)
        fun extractFromSubtitleField(fieldName: String) {
            try {
                val field = findField(baseRenderer.javaClass, fieldName) ?: return
                val subtitle = field.get(baseRenderer) ?: return
                val getEventTimeCount = subtitle.javaClass.getMethod("getEventTimeCount")
                val getEventTime = subtitle.javaClass.getMethod("getEventTime", Int::class.java)
                val getCues = subtitle.javaClass.getMethod("getCues", Long::class.java)
                val count = getEventTimeCount.invoke(subtitle) as Int
                for (i in 0 until count) {
                    val timeUs = getEventTime.invoke(subtitle, i) as Long
                    @Suppress("UNCHECKED_CAST")
                    val cues = getCues.invoke(subtitle, timeUs) as? List<Cue> ?: continue
                    val joined = joinCues(cues, removeHI)
                    if (joined.isNotBlank()) texts.add(joined)
                }
            } catch (_: Exception) {
            }
        }
        extractFromSubtitleField("subtitle")
        extractFromSubtitleField("nextSubtitle")
        return texts.toList()
    }

    /** Buffered cue intervals (startMs, endMs) for text-carrying cues, from the modern resolver. */
    fun extractBufferedIntervals(maxCount: Int): List<Pair<Long, Long>> =
        extractBufferedTimedCues(maxCount).map { it.startMs to it.endMs }

    /** Buffered (startMs, endMs, text) cues for text-carrying cues, from the modern resolver. */
    fun extractBufferedTimedCues(maxCount: Int): List<SubtitleSyncMatcher.TimedCue> {
        val timedCues = ArrayList<SubtitleSyncMatcher.TimedCue>()
        // Cue buffer timestamps are on ExoPlayer's internal stream timeline, which adds a large
        // base offset; subtract the renderer's stream offset to get 0-based media time.
        val offsetMs = readStreamOffsetUs() / 1000L
        try {
            val resolverField = findField(baseRenderer.javaClass, "cuesResolver")
            val resolver = resolverField?.get(baseRenderer)
            if (resolver != null) {
                // Media3 has multiple resolver impls chosen per track cue-replacement behavior
                // (MergingCuesResolver, ReplacingCuesResolver) — probe the known field names.
                for (candidate in listOf(
                    "cuesWithTimingList", "cuesWithTimings",
                    "cueGroupsByStartTime", "cueGroups", "cueGroupList", "groups"
                )) {
                    val f = findField(resolver.javaClass, candidate) ?: continue
                    val v = f.get(resolver) ?: continue
                    val items: Collection<*> = when (v) {
                        is Map<*, *> -> v.values
                        is Collection<*> -> v
                        else -> continue
                    }
                    for (item in items) {
                        val cue = item?.let(::timedCueFromWrapper) ?: continue
                        timedCues.add(
                            SubtitleSyncMatcher.TimedCue(cue.startMs - offsetMs, cue.endMs - offsetMs, cue.text)
                        )
                    }
                    if (timedCues.isNotEmpty()) break
                }
                if (timedCues.isEmpty()) {
                    // Unknown impl/field name (new media3 version?) — scan every field for a
                    // cue-carrying collection, like the text extractor already does.
                    var cls: Class<*>? = resolver.javaClass
                    outer@ while (cls != null && cls != Any::class.java) {
                        for (f in cls.declaredFields) {
                            try {
                                f.isAccessible = true
                                val v = f.get(resolver) ?: continue
                                val items: Collection<*> = when (v) {
                                    is Map<*, *> -> v.values
                                    is Collection<*> -> v
                                    else -> continue
                                }
                                for (item in items) {
                                    val cue = item?.let(::timedCueFromWrapper) ?: continue
                                    timedCues.add(
                                        SubtitleSyncMatcher.TimedCue(cue.startMs - offsetMs, cue.endMs - offsetMs, cue.text)
                                    )
                                }
                                if (timedCues.isNotEmpty()) break@outer
                            } catch (_: Exception) {
                            }
                        }
                        cls = cls.superclass
                    }
                }
            }
        } catch (_: Exception) {
        }
        return timedCues.filter { it.startMs >= 0 }.distinct().sortedBy { it.startMs }.take(maxCount)
    }

    /** The renderer's stream offset (µs) — the base added to buffer sample timestamps. */
    private fun readStreamOffsetUs(): Long {
        for (name in listOf("streamOffsetUs", "outputStreamOffsetUs")) {
            val v = runCatching { findField(baseRenderer.javaClass, name)?.getLong(baseRenderer) }.getOrNull()
            if (v != null && v > 0L) return v
        }
        return 0L
    }

    private fun timedCueFromWrapper(obj: Any): SubtitleSyncMatcher.TimedCue? {
        val cues: List<Cue>? = when (obj) {
            is CueGroup -> obj.cues
            is List<*> -> obj.filterIsInstance<Cue>()
            else -> (findField(obj.javaClass, "cues")?.get(obj) as? List<*>)?.filterIsInstance<Cue>()
        }
        if (cues.isNullOrEmpty()) return null
        val text = cues.mapNotNull { it.text?.toString()?.trim() }
            .filter { it.isNotBlank() }
            .joinToString("\n")
        if (text.isBlank()) return null

        val startUs: Long
        val endUs: Long
        if (obj is CueGroup) {
            startUs = obj.presentationTimeUs
            endUs = startUs + 2_000_000L // CueGroup carries no duration — assume 2s on screen
        } else {
            val start = runCatching { findField(obj.javaClass, "startTimeUs")?.getLong(obj) }.getOrNull() ?: return null
            // REPLACE-behavior tracks (ReplacingCuesResolver, e.g. MKV SubRip) carry
            // durationUs = C.TIME_UNSET (large negative) — each cue lasts until replaced. The
            // negative value made endUs < startUs and every item got rejected, silently killing
            // the buffered fast path for that whole content category. Fall back to a nominal
            // duration: the start timestamp carries the alignment signal.
            val dur = runCatching { findField(obj.javaClass, "durationUs")?.getLong(obj) }.getOrNull()
                ?.takeIf { it > 0L } ?: 2_000_000L
            startUs = start
            endUs = start + dur
        }
        if (endUs <= startUs) return null
        return SubtitleSyncMatcher.TimedCue(startUs / 1000L, endUs / 1000L, text)
    }

    private fun extractFromCollectionOrMap(v: Any, texts: MutableSet<String>, removeHI: Boolean): Int {
        var count = 0
        when (v) {
            is Map<*, *> -> v.values.forEach { if (extractCueGroupTexts(it, texts, removeHI)) count++ }
            is Collection<*> -> v.forEach { if (extractCueGroupTexts(it, texts, removeHI)) count++ }
        }
        return count
    }

    private fun extractCueGroupTexts(obj: Any?, texts: MutableSet<String>, removeHI: Boolean): Boolean {
        if (obj == null) return false
        if (obj is CueGroup) {
            val joined = joinCues(obj.cues, removeHI)
            if (joined.isNotBlank()) texts.add(joined)
            return true
        }
        if (obj is List<*>) {
            val cues = obj.filterIsInstance<Cue>()
            val joined = joinCues(cues, removeHI)
            if (joined.isNotBlank()) texts.add(joined)
            return obj.isNotEmpty()
        }
        // CuesWithTiming or similar wrapper — look for a 'cues' field
        try {
            val cuesField = findField(obj.javaClass, "cues")
            val cues = cuesField?.get(obj)
            if (cues is List<*>) {
                val joined = joinCues(cues.filterIsInstance<Cue>(), removeHI)
                if (joined.isNotBlank()) texts.add(joined)
                return cues.isNotEmpty()
            }
        } catch (_: Exception) {}
        return false
    }

    private fun joinCues(cues: List<Cue>, removeHI: Boolean): String =
        cues.mapNotNull { it.text?.toString()?.trim() }
            .filter { it.isNotBlank() }
            .joinToString("\n")
            .let { if (removeHI) stripHI(it) else it }

    private fun stripHI(text: String): String =
        text.replace(AiSubtitleRegexes.BRACKET_REGEX, "")
            .replace(AiSubtitleRegexes.MUSIC_REGEX, "")
            .trim()

    private fun findField(startClass: Class<*>, name: String): java.lang.reflect.Field? {
        var cls: Class<*>? = startClass
        while (cls != null && cls != Any::class.java) {
            try {
                val f = cls.getDeclaredField(name)
                f.isAccessible = true
                return f
            } catch (_: NoSuchFieldException) {}
            cls = cls.superclass
        }
        return null
    }
}
