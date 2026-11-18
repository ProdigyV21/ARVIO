package com.arflix.tv.ui.screens.player.subtitles.bilingual

import kotlin.math.abs
import kotlin.math.roundToLong

/**
 * Lines an external subtitle up with the timing of a subtitle track that is
 * muxed into the video (which is authored against this exact cut). Works across languages: only
 * cue start times are compared, never text.
 *
 * Every pair (reference start − target start) within ±[maxShiftMs] votes into 100 ms bins; the
 * densest ±[toleranceMs] window wins, its median difference is the shift. Common frame-rate
 * mismatches are tried as scales (media = subtitle × scale + shift).
 */
object EmbeddedTimingAligner {
    data class Interval(val startMs: Long, val endMs: Long)

    data class Result(
        val scale: Double,
        val shiftMs: Long,
        /** Target cues (inside the reference's time span) whose start landed on a reference start. */
        val matched: Int,
        val considered: Int,
        /** Votes of the best window divided by the best window more than 2 s away. */
        val prominence: Double,
    ) {
        val matchedFraction: Double get() = if (considered == 0) 0.0 else matched.toDouble() / considered
        val confident: Boolean
            get() = matched >= MIN_MATCHED && matchedFraction >= MIN_FRACTION && prominence >= MIN_PROMINENCE
        val isPlainShift: Boolean get() = abs(scale - 1.0) < 1e-6
    }

    val SCALES = doubleArrayOf(1.0, 24_000.0 / 23_976.0, 23_976.0 / 24_000.0, 25.0 / 23.976, 23.976 / 25.0, 25.0 / 24.0, 24.0 / 25.0)
    const val MIN_MATCHED = 10
    const val MIN_FRACTION = 0.35
    const val MIN_PROMINENCE = 1.8
    private const val BIN_MS = 100L

    fun align(
        reference: List<Interval>,
        target: List<Interval>,
        maxShiftMs: Long = 120_000L,
        toleranceMs: Long = 300L,
    ): Result? {
        if (reference.size < MIN_MATCHED || target.size < MIN_MATCHED) return null
        val refStarts = reference.map { it.startMs }.sorted().toLongArray()
        val refFrom = refStarts.first()
        val refTo = refStarts.last()
        var best: Result? = null
        for (scale in SCALES) {
            val tgt = target.map { (it.startMs * scale).roundToLong() }
                .filter { it in (refFrom - maxShiftMs)..(refTo + maxShiftMs) }
                .sorted()
            if (tgt.size < MIN_MATCHED) continue
            val r = alignStarts(refStarts, tgt.toLongArray(), maxShiftMs, toleranceMs, scale) ?: continue
            val b = best
            // A frame-rate scale must beat the plain shift clearly to be believed.
            val better = when {
                b == null -> true
                b.isPlainShift && !r.isPlainShift -> r.matched > b.matched * 1.25 + 2
                else -> r.matched > b.matched
            }
            if (better) best = r
        }
        return best
    }

    private fun alignStarts(ref: LongArray, tgt: LongArray, maxShiftMs: Long, toleranceMs: Long, scale: Double): Result? {
        val bins = HashMap<Long, Int>()
        var lo = 0
        for (t in tgt) {
            while (lo < ref.size && ref[lo] < t - maxShiftMs) lo++
            var i = lo
            while (i < ref.size && ref[i] <= t + maxShiftMs) {
                val bin = Math.floorDiv(ref[i] - t, BIN_MS)
                bins[bin] = (bins[bin] ?: 0) + 1
                i++
            }
        }
        if (bins.isEmpty()) return null
        val radius = (toleranceMs / BIN_MS).coerceAtLeast(1L)
        fun windowVotes(center: Long): Int {
            var s = 0
            for (k in -radius..radius) s += bins[center + k] ?: 0
            return s
        }
        var bestBin = 0L
        var bestVotes = -1
        for (bin in bins.keys) {
            val v = windowVotes(bin)
            if (v > bestVotes || (v == bestVotes && abs(bin) < abs(bestBin))) { bestVotes = v; bestBin = bin }
        }
        val farBins = (2_000L / BIN_MS)
        var second = 0
        for (bin in bins.keys) {
            if (abs(bin - bestBin) <= farBins) continue
            val v = windowVotes(bin)
            if (v > second) second = v
        }
        // Median of the differences inside the winning window, one per target cue (nearest start).
        val centerMs = bestBin * BIN_MS + BIN_MS / 2
        val diffs = ArrayList<Long>()
        val shifted = LongArray(tgt.size)
        for ((k, t) in tgt.withIndex()) {
            val want = t + centerMs
            val near = nearest(ref, want)
            shifted[k] = near - t
            if (abs(near - want) <= toleranceMs + BIN_MS) diffs.add(near - t)
        }
        if (diffs.isEmpty()) return null
        diffs.sort()
        val shift = diffs[diffs.size / 2]
        // Only target cues inside the reference span count (the reference is often a buffered slice).
        val considered = tgt.count { it + shift in (ref.first() - 1_000)..(ref.last() + 1_000) }
        val matched = tgt.count { t -> abs(nearest(ref, t + shift) - (t + shift)) <= toleranceMs }
        return Result(
            scale = scale,
            shiftMs = shift,
            matched = matched,
            considered = considered.coerceAtLeast(matched),
            prominence = if (second == 0) bestVotes.toDouble() else bestVotes.toDouble() / second,
        )
    }

    private fun nearest(sorted: LongArray, x: Long): Long {
        var lo = 0
        var hi = sorted.size - 1
        while (lo < hi) {
            val mid = (lo + hi) ushr 1
            if (sorted[mid] < x) lo = mid + 1 else hi = mid
        }
        val a = sorted[lo]
        val b = if (lo > 0) sorted[lo - 1] else a
        return if (abs(a - x) <= abs(b - x)) a else b
    }
}
