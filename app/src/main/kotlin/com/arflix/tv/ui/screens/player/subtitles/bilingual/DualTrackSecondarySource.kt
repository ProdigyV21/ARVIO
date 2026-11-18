package com.arflix.tv.ui.screens.player.subtitles.bilingual

import kotlin.math.abs

/**
 * Path A: supplies the secondary-language line for the primary cue on screen, read from a second
 * embedded track of the same file ([EmbeddedSubtitleStore]) and time-aligned with
 * [BilingualAligner]. Makes no network or AI request of any kind.
 *
 * [primaryTrackId] is the primary track inside the store when the primary is itself embedded
 * (normal case: its exact cue spans are known ahead of time). When the primary comes from
 * elsewhere it is null and the primary spans observed on screen are used instead.
 */
class DualTrackSecondarySource(
    private val store: EmbeddedSubtitleStore,
    val primaryTrackId: Int?,
    val secondaryTrackId: Int,
    private val clockMs: () -> Long = { System.currentTimeMillis() },
) {
    enum class Status { WARMING_UP, ALIGNED, UNRELIABLE, NO_DATA }

    data class Lookup(val text: String?, val status: Status, val alignment: BilingualAligner.Alignment?)

    @Volatile private var alignment: BilingualAligner.Alignment? = null
    @Volatile private var alignedAtVersion = -1L
    @Volatile private var alignedAtMs = 0L
    @Volatile private var alignedAroundMs = Long.MIN_VALUE

    /** Primary cues seen on screen (only used when the primary is not in the store). */
    private val observed = ArrayDeque<TimedText>()
    private var openObserved: TimedText? = null
    private val lock = Any()

    /** Current alignment verdict (null before the first estimate). */
    fun currentAlignment(): BilingualAligner.Alignment? = alignment

    /** Records a primary cue appearing at [presentationMs] (and closes the previous one). */
    fun onPrimaryShown(text: String, presentationMs: Long): Unit = synchronized(lock) {
        openObserved?.let { if (presentationMs > it.startMs) observed.addLast(it.copy(endMs = presentationMs)) }
        openObserved = TimedText(presentationMs, presentationMs + 6_000L, text)
        while (observed.size > 400) observed.removeFirst()
    }

    fun onPrimaryCleared(presentationMs: Long): Unit = synchronized(lock) {
        openObserved?.let { if (presentationMs > it.startMs) observed.addLast(it.copy(endMs = presentationMs)) }
        openObserved = null
    }

    /**
     * The secondary text for the primary cue [primaryText] that became visible at
     * [presentationMs] (media time). Null text = nothing belongs with it (a pause in the other
     * language, data not demuxed yet, or alignment not trustworthy — see [Lookup.status]).
     */
    fun lookup(primaryText: String, presentationMs: Long): Lookup {
        if (store.cueCount(secondaryTrackId) == 0) return Lookup(null, Status.NO_DATA, alignment)
        val a = alignmentAround(presentationMs)
        val span = primarySpan(primaryText, presentationMs)
        val secondary = store.cues(secondaryTrackId, span.first - BilingualAligner.MAX_OFFSET_MS - 1_000, span.second + BilingualAligner.MAX_OFFSET_MS + 1_000)
        return when {
            a.reliable -> Lookup(BilingualAligner.partnerText(span.first, span.second, secondary, a.offsetMs), Status.ALIGNED, a)
            a.reason == BilingualAligner.UnreliableReason.TOO_FEW_CUES ->
                // Not enough history yet to estimate a shift: only pair what lines up as authored.
                Lookup(BilingualAligner.partnerText(span.first, span.second, secondary, 0L), Status.WARMING_UP, a)
            else -> Lookup(null, Status.UNRELIABLE, a)
        }
    }

    private fun alignmentAround(positionMs: Long): BilingualAligner.Alignment {
        val cached = alignment
        val version = store.version
        val now = clockMs()
        val stale = cached == null ||
            (version != alignedAtVersion && now - alignedAtMs >= REALIGN_INTERVAL_MS) ||
            abs(positionMs - alignedAroundMs) > REALIGN_DISTANCE_MS
        if (!stale) return cached!!
        val from = positionMs - WINDOW_BEFORE_MS
        val to = positionMs + WINDOW_AFTER_MS
        val primaryCues = if (primaryTrackId != null) store.cues(primaryTrackId, from, to) else synchronized(lock) { observed.toList() }
        val secondaryCues = store.cues(secondaryTrackId, from - BilingualAligner.MAX_OFFSET_MS, to + BilingualAligner.MAX_OFFSET_MS)
        val result = BilingualAligner.align(primaryCues, secondaryCues)
        // Once a reliable alignment exists, one weak re-estimate (e.g. a song with no secondary
        // lines) must not throw it away; only a reliable new estimate replaces it.
        val keep = cached != null && cached.reliable && !result.reliable &&
            result.reason != BilingualAligner.UnreliableReason.LOW_OVERLAP
        val chosen = if (keep) cached!! else result
        alignment = chosen
        alignedAtVersion = version
        alignedAtMs = now
        alignedAroundMs = positionMs
        return chosen
    }

    /** Exact span of the primary cue when known; otherwise an estimate starting at [presentationMs]. */
    private fun primarySpan(primaryText: String, presentationMs: Long): Pair<Long, Long> {
        if (primaryTrackId != null) {
            val key = normaliseForMatch(primaryText)
            val candidates = store.cues(primaryTrackId, presentationMs - 2_000, presentationMs + 2_000)
            val best = candidates
                .filter { it.startMs <= presentationMs + 500 && it.endMs >= presentationMs - 500 }
                .minByOrNull { c ->
                    val textPenalty = if (key.isNotEmpty() && textsMatch(normaliseForMatch(c.text), key)) 0 else 10_000
                    abs(c.startMs - presentationMs) + textPenalty
                }
            if (best != null) return best.startMs to best.endMs
        }
        return presentationMs to presentationMs + ASSUMED_PRIMARY_DURATION_MS
    }

    private fun textsMatch(a: String, b: String): Boolean = a == b || a.contains(b) || b.contains(a)

    companion object {
        private const val REALIGN_INTERVAL_MS = 4_000L
        private const val REALIGN_DISTANCE_MS = 90_000L
        private const val WINDOW_BEFORE_MS = 5 * 60_000L
        private const val WINDOW_AFTER_MS = 2 * 60_000L
        private const val ASSUMED_PRIMARY_DURATION_MS = 2_000L

        internal fun normaliseForMatch(text: String): String =
            text.lowercase().filter { it.isLetterOrDigit() }
    }
}
