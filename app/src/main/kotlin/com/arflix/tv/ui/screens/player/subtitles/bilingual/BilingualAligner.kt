package com.arflix.tv.ui.screens.player.subtitles.bilingual

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/** One subtitle event on the media timeline, in milliseconds. */
data class TimedText(val startMs: Long, val endMs: Long, val text: String) {
    val durationMs: Long get() = max(0L, endMs - startMs)
}

/**
 * Time-based alignment of two independently authored subtitle tracks of the same video
 * (e.g. an English and a Chinese embedded track).
 *
 * Never pairs by index: the tracks are cut differently (one long English line may be two Chinese
 * lines and vice versa) and may be shifted against each other. The aligner
 *  1. estimates one global offset of the secondary track (the shift that maximises how much of the
 *     primary track's on-screen time is covered by secondary cues), then
 *  2. pairs every primary cue with the secondary cues that substantially overlap it after that
 *     shift (one-to-many and many-to-one both fall out of the overlap rule).
 *
 * Confidence is the coverage at the chosen offset plus a cue-density sanity check; below the
 * thresholds the result is [Alignment.reliable] = false and callers must not merge the tracks.
 */
object BilingualAligner {

    const val MAX_OFFSET_MS = 8_000L
    private const val COARSE_STEP_MS = 100L
    private const val MIN_CUES_FOR_CONFIDENCE = 6
    const val MIN_COVERAGE = 0.55
    private const val MIN_DENSITY_RATIO = 0.3
    private const val MAX_DENSITY_RATIO = 3.5
    /** Cap on cues considered per estimate — keeps an estimate well under a millisecond-scale budget. */
    private const val MAX_SAMPLE = 240

    data class Alignment(
        /** Shift to ADD to secondary cue times to line them up with the primary track. */
        val offsetMs: Long,
        /** Fraction (0..1) of primary on-screen time covered by shifted secondary cues. */
        val coverage: Double,
        val primaryCount: Int,
        val secondaryCount: Int,
        val reliable: Boolean,
        /** Why [reliable] is false (null when reliable). */
        val reason: UnreliableReason? = null,
        /** Fraction of primary cue starts with a secondary cue starting within ±[START_TOLERANCE_MS]. */
        val startAgreement: Double = 0.0,
        /** Coverage at the chosen offset minus the average coverage at clearly wrong offsets. */
        val peakMargin: Double = 0.0,
    )

    enum class UnreliableReason { TOO_FEW_CUES, LOW_OVERLAP, DENSITY_MISMATCH, NO_TIMING_CORRELATION }

    const val START_TOLERANCE_MS = 500L
    private const val MIN_START_AGREEMENT = 0.3
    private const val MIN_PEAK_MARGIN = 0.08

    /**
     * Estimates the alignment of [secondary] against [primary]. Both lists may be unsorted and
     * may contain duplicates; only their overlapping time window is used.
     */
    fun align(primary: List<TimedText>, secondary: List<TimedText>): Alignment {
        val p = normalise(primary)
        val s = normalise(secondary)
        if (p.size < MIN_CUES_FOR_CONFIDENCE || s.size < MIN_CUES_FOR_CONFIDENCE) {
            val cov = if (p.isEmpty() || s.isEmpty()) 0.0 else coverage(p, s, 0L)
            return Alignment(0L, cov, p.size, s.size, reliable = false, reason = UnreliableReason.TOO_FEW_CUES)
        }
        // Restrict both to the common window (± max offset) so a track that simply has not been
        // read that far yet does not look like a mismatch.
        val windowStart = max(p.first().startMs, s.first().startMs - MAX_OFFSET_MS)
        val windowEnd = min(p.last().endMs, s.last().endMs + MAX_OFFSET_MS)
        val pw = sample(p.filter { it.endMs >= windowStart && it.startMs <= windowEnd })
        val sw = s.filter { it.endMs >= windowStart - MAX_OFFSET_MS && it.startMs <= windowEnd + MAX_OFFSET_MS }
        if (pw.size < MIN_CUES_FOR_CONFIDENCE || sw.size < MIN_CUES_FOR_CONFIDENCE) {
            return Alignment(0L, coverage(p, s, 0L), pw.size, sw.size, reliable = false, reason = UnreliableReason.TOO_FEW_CUES)
        }

        var bestOffset = 0L
        var bestScore = Double.NEGATIVE_INFINITY
        var bestCoverage = 0.0
        val coverageByOffset = HashMap<Long, Double>()
        var d = -MAX_OFFSET_MS
        while (d <= MAX_OFFSET_MS) {
            val cov = coverage(pw, sw, d)
            coverageByOffset[d] = cov
            // Tiny preference for small shifts: equal coverage → the less surprising offset.
            val score = cov - abs(d) * 1e-7
            if (score > bestScore) { bestScore = score; bestOffset = d; bestCoverage = cov }
            d += COARSE_STEP_MS
        }
        // Refine ±100 ms in 20 ms steps.
        var fine = bestOffset - COARSE_STEP_MS
        while (fine <= bestOffset + COARSE_STEP_MS) {
            val cov = coverage(pw, sw, fine)
            val score = cov - abs(fine) * 1e-7
            if (score > bestScore) { bestScore = score; bestOffset = fine; bestCoverage = cov }
            fine += 20L
        }

        val primarySpan = (pw.last().endMs - pw.first().startMs).coerceAtLeast(1L)
        val secondaryInSpan = sw.count { it.startMs + bestOffset <= pw.last().endMs && it.endMs + bestOffset >= pw.first().startMs }
        val density = secondaryInSpan.toDouble() / pw.size
        // Related tracks are cut on the same speech onsets, so their starts agree after the shift;
        // two unrelated tracks of dense dialogue can still "cover" each other by chance, but their
        // starts do not line up and their coverage does not peak at one offset.
        val shiftedStarts = sw.map { it.startMs + bestOffset }.sorted()
        val agreeing = pw.count { p -> nearestDistance(shiftedStarts, p.startMs) <= START_TOLERANCE_MS }
        val startAgreement = agreeing.toDouble() / pw.size
        val far = coverageByOffset.filterKeys { abs(it - bestOffset) >= 2_500L }.values
        val baseline = if (far.isEmpty()) 0.0 else far.average()
        val margin = bestCoverage - baseline
        val reason = when {
            bestCoverage < MIN_COVERAGE -> UnreliableReason.LOW_OVERLAP
            density < MIN_DENSITY_RATIO || density > MAX_DENSITY_RATIO -> UnreliableReason.DENSITY_MISMATCH
            startAgreement < MIN_START_AGREEMENT || margin < MIN_PEAK_MARGIN -> UnreliableReason.NO_TIMING_CORRELATION
            else -> null
        }
        return Alignment(
            bestOffset, bestCoverage, pw.size, sw.size,
            reliable = reason == null && primarySpan > 0, reason = reason,
            startAgreement = startAgreement, peakMargin = margin,
        )
    }

    /**
     * Secondary cues that belong with the primary cue [primaryStartMs]..[primaryEndMs] once
     * shifted by [offsetMs]. A secondary cue belongs when the overlap is at least half of the
     * shorter of the two cues, so a short neighbour that merely touches the edge is excluded.
     */
    fun partnersOf(
        primaryStartMs: Long,
        primaryEndMs: Long,
        secondary: List<TimedText>,
        offsetMs: Long,
    ): List<TimedText> {
        val pDur = max(1L, primaryEndMs - primaryStartMs)
        return secondary.filter { cue ->
            val ss = cue.startMs + offsetMs
            val se = cue.endMs + offsetMs
            val overlap = min(primaryEndMs, se) - max(primaryStartMs, ss)
            if (overlap <= 0) return@filter false
            val sDur = max(1L, se - ss)
            overlap * 2 >= min(pDur, sDur)
        }.sortedBy { it.startMs }
    }

    /** Joined partner text, or null when nothing belongs with this primary cue. */
    fun partnerText(primaryStartMs: Long, primaryEndMs: Long, secondary: List<TimedText>, offsetMs: Long): String? =
        partnersOf(primaryStartMs, primaryEndMs, secondary, offsetMs)
            .map { it.text.trim() }
            .filter { it.isNotEmpty() }
            .distinct()
            .joinToString("\n")
            .takeIf { it.isNotEmpty() }

    /** Pairs every primary cue (for tests and diagnostics). */
    fun pairAll(primary: List<TimedText>, secondary: List<TimedText>, offsetMs: Long): List<Pair<TimedText, String?>> {
        val s = normalise(secondary)
        return normalise(primary).map { it to partnerText(it.startMs, it.endMs, s, offsetMs) }
    }

    /** Fraction of the primary cues' on-screen time covered by the union of shifted secondary cues. */
    internal fun coverage(primary: List<TimedText>, secondary: List<TimedText>, offsetMs: Long): Double {
        var total = 0L
        var covered = 0L
        var j = 0
        for (p in primary) {
            val pd = p.durationMs
            if (pd <= 0) continue
            total += pd
            while (j < secondary.size && secondary[j].endMs + offsetMs <= p.startMs) j++
            var k = j
            var cursor = p.startMs
            var c = 0L
            while (k < secondary.size && secondary[k].startMs + offsetMs < p.endMs) {
                val ss = max(cursor, secondary[k].startMs + offsetMs)
                val se = min(p.endMs, secondary[k].endMs + offsetMs)
                if (se > ss) { c += se - ss; cursor = se }
                k++
            }
            covered += c
        }
        return if (total == 0L) 0.0 else covered.toDouble() / total
    }

    private fun nearestDistance(sorted: List<Long>, value: Long): Long {
        var lo = 0
        var hi = sorted.size - 1
        while (lo < hi) {
            val mid = (lo + hi) ushr 1
            if (sorted[mid] < value) lo = mid + 1 else hi = mid
        }
        var best = Long.MAX_VALUE
        for (i in (lo - 1)..(lo + 1)) if (i in sorted.indices) best = min(best, abs(sorted[i] - value))
        return best
    }

    private fun normalise(list: List<TimedText>): List<TimedText> =
        list.asSequence()
            .filter { it.endMs > it.startMs && it.text.isNotBlank() }
            .distinctBy { Triple(it.startMs, it.endMs, it.text) }
            .sortedWith(compareBy({ it.startMs }, { it.endMs }))
            .toList()

    private fun sample(list: List<TimedText>): List<TimedText> {
        if (list.size <= MAX_SAMPLE) return list
        // Keep contiguous runs (coverage needs neighbours), centred on the middle of the window.
        val from = (list.size - MAX_SAMPLE) / 2
        return list.subList(from, from + MAX_SAMPLE)
    }
}
