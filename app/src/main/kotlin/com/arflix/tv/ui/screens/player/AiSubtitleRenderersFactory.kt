package com.arflix.tv.ui.screens.player

import android.content.Context
import android.os.Handler
import androidx.media3.common.text.Cue
import androidx.media3.common.text.CueGroup
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.text.TextOutput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


private object AiSubtitleRegexes {
    val BRACKET_REGEX = Regex("""\[.*?\]""")
    val MUSIC_REGEX = Regex("[♪♫]+")
}

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class AiSubtitleRenderersFactory(
    context: Context,
    private val translationManager: SubtitleTranslationManager,
    private val scope: CoroutineScope
) : DefaultRenderersFactory(context) {

    val syncOffsetUs = java.util.concurrent.atomic.AtomicLong(0L)

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
        val offsetRenderers = mutableListOf<SubtitleOffsetRenderer>()
        for (index in startIndex until out.size) {
            val offsetRenderer = SubtitleOffsetRenderer(
                baseRenderer = out[index],
                translationManager = translationManager,
                translationScope = scope,
                syncOffsetUs = syncOffsetUs
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
    private val syncOffsetUs: java.util.concurrent.atomic.AtomicLong = java.util.concurrent.atomic.AtomicLong(0L)
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
        val adjustedUs = if (offset != 0L) (positionUs - offset).coerceAtLeast(0L) else positionUs
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

    private fun extractAllCueTexts(): List<String> {
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
