package com.arflix.tv.ui.screens.tv.live.aisubs

/** One subtitle line on the media timeline (the player's period time, in microseconds). */
internal data class LiveCue(
    val startUs: Long,
    val endUs: Long,
    val text: String,
    val rtl: Boolean = false,
)

/**
 * Keeps AI subtitle cues in media time and answers "what is on screen at this position". Cues are
 * shown by where playback is, never by when the AI answered, so pausing, rebuffering and fast
 * dialogue cannot push lines out of step with the picture.
 *
 * Display rules: each cue stays up at least [MIN_DISPLAY_US] and at most [MAX_DISPLAY_US], and is
 * cut short by the next cue. Text longer than [SPLIT_CHARS] is split into two cues, timed by
 * length. A cue that arrives after its start still shows while it is inside its display window;
 * one that arrives after the window has closed is dropped and counted in [lateDropped].
 *
 * Not thread-safe; the session calls it from one thread (the main thread).
 */
internal class LiveCueScheduler {
    private val cues = ArrayList<LiveCue>()

    var lateDropped: Int = 0
        private set

    var shown: Int = 0
        private set

    private var lastShown: LiveCue? = null

    /** Adds cues from one segment. [positionUs] is where playback is now, for the late-cue rule. */
    fun add(newCues: List<LiveCue>, positionUs: Long) {
        for (cue in newCues.flatMap(::split)) {
            if (cue.text.isBlank()) continue
            val provisionalEnd = displayEnd(cue, next = null)
            if (positionUs >= provisionalEnd) {
                lateDropped++
                continue
            }
            val index = cues.indexOfFirst { it.startUs > cue.startUs }.let { if (it < 0) cues.size else it }
            cues.add(index, cue)
        }
    }

    /** The cue on screen at [positionUs], or null. Forgets cues that are over. */
    fun cueAt(positionUs: Long): LiveCue? {
        var result: LiveCue? = null
        for (i in cues.indices) {
            val cue = cues[i]
            if (cue.startUs > positionUs) break
            val end = displayEnd(cue, cues.getOrNull(i + 1))
            if (positionUs < end) result = cue
        }
        // Drop cues whose window closed well before the position (bounded memory).
        cues.removeAll { displayEnd(it, null) < positionUs - FORGET_AFTER_US }
        if (result != null && result !== lastShown) shown++
        lastShown = result
        return result
    }

    fun clear() {
        cues.clear()
        lastShown = null
    }

    val pendingCount: Int get() = cues.size

    private fun displayEnd(cue: LiveCue, next: LiveCue?): Long {
        val natural = cue.endUs.coerceAtLeast(cue.startUs + MIN_DISPLAY_US)
        val capped = natural.coerceAtMost(cue.startUs + MAX_DISPLAY_US)
        return if (next != null && next.startUs > cue.startUs) minOf(capped, next.startUs) else capped
    }

    companion object {
        const val MIN_DISPLAY_US = 1_200_000L
        const val MAX_DISPLAY_US = 6_000_000L
        const val SPLIT_CHARS = 84
        private const val FORGET_AFTER_US = 30_000_000L

        /**
         * Splits a long line into two cues at the word boundary nearest its middle, each getting a
         * share of the time proportional to its length.
         */
        fun split(cue: LiveCue): List<LiveCue> {
            val text = cue.text.trim()
            if (text.length <= SPLIT_CHARS) return listOf(cue.copy(text = text))
            val middle = text.length / 2
            val spaces = text.indices.filter { text[it] == ' ' }
            val cut = spaces.minByOrNull { kotlin.math.abs(it - middle) } ?: return listOf(cue.copy(text = text))
            val first = text.substring(0, cut).trim()
            val second = text.substring(cut + 1).trim()
            if (first.isEmpty() || second.isEmpty()) return listOf(cue.copy(text = text))
            val duration = (cue.endUs - cue.startUs).coerceAtLeast(0L)
            val splitAt = cue.startUs + duration * first.length / (first.length + second.length)
            return listOf(
                cue.copy(endUs = splitAt, text = first),
                cue.copy(startUs = splitAt, text = second),
            )
        }
    }
}

/**
 * The hold ("Auto" delay) that lets a whole segment be heard, sent and answered before its first
 * word plays: the longest segment, plus the AI's p95 latency, plus a margin. Before any latency has
 * been measured, it assumes [DEFAULT_LATENCY_P95_MS]. Clamped to 6–15 s.
 */
internal fun autoLiveAiSubsDelayMs(latencyP95Ms: Long?): Long {
    val latency = latencyP95Ms ?: DEFAULT_LATENCY_P95_MS
    return (LIVE_MAX_SEGMENT_MS + latency + DELAY_MARGIN_MS).coerceIn(6_000L, 15_000L)
}


/** The 95th percentile of [samples], or null with fewer than [minSamples]. */
internal fun percentile95(samples: List<Long>, minSamples: Int = 3): Long? {
    if (samples.size < minSamples) return null
    val sorted = samples.sorted()
    val index = ((sorted.size - 1) * 0.95).let { kotlin.math.ceil(it).toInt() }.coerceIn(0, sorted.size - 1)
    return sorted[index]
}

internal const val LIVE_MAX_SEGMENT_MS = 5_000L
/** Measured on a Mi Box 4 with gemini-3.5-flash-lite: p50 about 5 s, p95 about 8.5 s. */
private const val DEFAULT_LATENCY_P95_MS = 5_000L
private const val DELAY_MARGIN_MS = 1_000L
