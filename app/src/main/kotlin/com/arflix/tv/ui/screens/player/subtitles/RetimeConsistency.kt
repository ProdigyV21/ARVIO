package com.arflix.tv.ui.screens.player.subtitles

/**
 * Whether one retimer result fits the WHOLE file, judged by how far each part of the episode sits
 * from the result's own global map.
 *
 * The retimer's `confident` flag checks that lines found partners, not how far off they are, so a
 * release with a cut (or uneven drift) can pass as confident while part of the episode is seconds
 * out — a measured weakness of the Nuvio retimer (3 of 8 planted 5-second cuts accepted, per the
 * NG Feature-16 study) and exactly The Shards S01E04 (Sept 2026): ×1.001 + 6.9s, right at the start
 * and ~4–5s late from minute 30. The NG "A–I" contract refuses on local residual steps of 1s; this
 * is the same test on the retimer's own matched pairs.
 *
 * Calibrated on real files: correct results spread 147–205ms across the windows (Peaky Blinders
 * −47.8s, The Shards S01E03), the bad S01E04 result 1,349–1,602ms.
 */
internal object RetimeConsistency {
    const val WINDOWS = 6
    const val MIN_PAIRS_PER_WINDOW = 5
    const val MIN_PAIRS = 30
    const val MAX_WINDOW_SPREAD_MS = 1_000L

    data class Verdict(val fits: Boolean, val windowMedians: List<Long>, val spreadMs: Long, val pairs: Int)

    /**
     * [pairs] are (subtitle line start as authored, residual in ms): the reference start the
     * retimer paired the line with, minus where its global map puts that line. Returns fits=true
     * when there is too little evidence to judge — the check may only refuse on proof.
     */
    fun check(pairs: List<Pair<Long, Long>>): Verdict {
        if (pairs.size < MIN_PAIRS) return Verdict(true, emptyList(), 0L, pairs.size)
        val sorted = pairs.sortedBy { it.first }
        val first = sorted.first().first
        val span = (sorted.last().first - first).coerceAtLeast(1L)
        val medians = (0 until WINDOWS).mapNotNull { w ->
            val lo = first + span * w / WINDOWS
            val hi = first + span * (w + 1) / WINDOWS
            val residuals = sorted
                .filter { (t, _) -> t >= lo && (t < hi || w == WINDOWS - 1) }
                .map { it.second }
                .sorted()
            if (residuals.size < MIN_PAIRS_PER_WINDOW) null else residuals[residuals.size / 2]
        }
        if (medians.size < 3) return Verdict(true, medians, 0L, pairs.size)
        val spread = medians.max() - medians.min()
        return Verdict(spread <= MAX_WINDOW_SPREAD_MS, medians, spread, pairs.size)
    }

    /** The residual pairs of a retimer [result] computed against [reference] for [target]. */
    fun pairs(
        result: AutoSyncTimelineRetimeResult,
        reference: List<SubtitleSyncMatcher.TimedCue>,
        target: List<SubtitleSyncMatcher.TimedCue>,
    ): List<Pair<Long, Long>> = result.groups.mapNotNull { group ->
        val authored = target.getOrNull(group.targetStartIndex)?.startMs ?: return@mapNotNull null
        val referenceStart = reference.getOrNull(group.referenceStartIndex)?.startMs ?: return@mapNotNull null
        authored to (referenceStart - (authored * result.alignmentScale + result.alignmentInterceptMs)).toLong()
    }
}
