package com.arflix.tv.ui.screens.player

import kotlin.math.abs

/**
 * Pure math/support for AI subtitle auto-sync (LLM semantic line matching between built-in
 * subtitle cues and an addon subtitle file). Two stages share this:
 *
 * - **Anchor 1** (foreground): built-in reference lines near the playback position are matched
 *   against the addon file; each confident pair yields an offset `builtInTime − addonTime`,
 *   pooled across attempts and reduced with [robustMeanOfLongs] into a flat delay.
 * - **Anchors 2–4** (background drift calibration): the same measurement is repeated at distant
 *   positions by an invisible secondary player; [fitDriftLineRobust] fits
 *   `delay(position) = intercept + rate·position` across all anchors to correct a gradual
 *   frame-rate-style drift (e.g. 23.976 vs 25 fps).
 *
 * Everything here is deliberately free of player/Android dependencies so it can be unit-tested.
 */
object SubtitleAutoSync {

    /** Consecutive built-in lines sent to the LLM as the source window (and the minimum gathered). */
    const val SOURCE_LINE_COUNT = 5

    /** Addon lines included on each side of the estimated corresponding position. */
    const val TARGET_WINDOW_RADIUS = 12

    /** Anchor 1: total LLM attempts and the pooled per-pair offset target across them. */
    const val MAX_ATTEMPTS = 3
    const val TARGET_POOLED_OFFSETS = 3

    /**
     * A presentation-time jump between consecutive gathered cues larger than this means a seek —
     * older buffered reference lines belong to another region of the timeline and must be dropped.
     */
    const val CUE_JUMP_RESET_MS = 90_000L

    // ── Robust offset pooling ───────────────────────────────────────────────────

    // Pairs deviating from the median by more than this are gross mismatches (bad LLM matches),
    // not genuine data points — excluded before averaging. Comfortably above reaction-time/
    // frame-boundary noise, well below a plausible real offset difference.
    const val OUTLIER_THRESHOLD_MS = 450L

    fun medianOfLongs(values: List<Long>): Long {
        val sorted = values.sorted()
        val n = sorted.size
        return if (n % 2 == 1) {
            sorted[n / 2]
        } else {
            Math.round((sorted[n / 2 - 1] + sorted[n / 2]) / 2.0)
        }
    }

    /**
     * Median alone is robust but wastes information (ignores magnitude among the "good" points).
     * A plain mean is tighter on clean data but has zero tolerance for one gross mismatch (a known
     * failure mode — occasional bad LLM pairs). Combined: the median is used purely as a reference
     * to reject gross outliers, then whatever remains is averaged.
     */
    fun robustMeanOfLongs(values: List<Long>): Long {
        if (values.isEmpty()) return 0L
        if (values.size <= 2) return medianOfLongs(values)
        val median = medianOfLongs(values)
        val inliers = values.filter { abs(it - median) <= OUTLIER_THRESHOLD_MS }
        val effective = inliers.ifEmpty { values }
        return Math.round(effective.sum().toDouble() / effective.size)
    }

    // ── Non-dialogue filtering ──────────────────────────────────────────────────

    private val MUSIC_NOTE_CHARS = charArrayOf('\u266A', '\u266B', '\u266C', '\u2669')
    private val NON_DIALOGUE_BRACKET_KEYWORDS =
        listOf("music", "song", "theme", "instrumental", "singing", "humming")

    /**
     * Music/SFX annotations ("♪ … ♪", "[Music]", "(singing)") are common on built-in SDH tracks
     * but rarely present in addon subtitles, so they'd never find a match — skip them.
     */
    fun isNonDialogueMusicCue(text: String): Boolean {
        if (text.any { it in MUSIC_NOTE_CHARS }) return true
        val bracketed = (text.startsWith("[") && text.endsWith("]")) ||
            (text.startsWith("(") && text.endsWith(")"))
        if (!bracketed) return false
        val inner = text.substring(1, text.length - 1).lowercase()
        return NON_DIALOGUE_BRACKET_KEYWORDS.any { inner.contains(it) }
    }

    // ── Target window selection ─────────────────────────────────────────────────

    /**
     * The window of addon cues (as an index range into [addonStartsMs], sorted by start) around
     * the addon line nearest to [approxTrueTimeMs] — the estimated true video time of the last
     * reference cue. [radius] can be widened when an attempt finds no matches (a large real
     * offset can put the correct lines outside the default window).
     */
    fun targetWindow(
        addonStartsMs: List<Long>,
        approxTrueTimeMs: Long,
        radius: Int = TARGET_WINDOW_RADIUS
    ): IntRange {
        if (addonStartsMs.isEmpty()) return IntRange.EMPTY
        val nearest = addonStartsMs.indices.minByOrNull { abs(addonStartsMs[it] - approxTrueTimeMs) } ?: 0
        val start = (nearest - radius).coerceAtLeast(0)
        val end = (nearest + radius).coerceAtMost(addonStartsMs.lastIndex)
        return start..end
    }

    // ── Multi-anchor drift fit ──────────────────────────────────────────────────

    // Realistic frame-rate mismatches (23.976/24/25 fps combinations) are all well under this; a
    // larger computed rate almost certainly means a bad LLM match, not a real drift — reject it.
    const val DRIFT_RATE_MAX_ABS = 0.08

    // With ≥3 anchors, how far an anchor's delay may sit from the fitted line before it's treated
    // as one bad measurement (bad LLM match, not genuine non-constant drift) rather than noise.
    private const val DRIFT_RESIDUAL_OUTLIER_MS = 400.0

    /** One measured calibration point: the delay observed at a (true) playback position. */
    data class DriftAnchorPoint(val positionMs: Long, val delayMs: Long)

    /** Unweighted least-squares line: `delayMs = interceptMs + rate * positionMs`. */
    data class DriftLinearFit(val rate: Double, val interceptMs: Double) {
        fun predictMs(positionMs: Long): Double = interceptMs + rate * positionMs
    }

    private fun fitDriftLine(anchors: List<DriftAnchorPoint>): DriftLinearFit {
        val meanX = anchors.sumOf { it.positionMs.toDouble() } / anchors.size
        val meanY = anchors.sumOf { it.delayMs.toDouble() } / anchors.size
        var numerator = 0.0
        var denominator = 0.0
        for (anchor in anchors) {
            val dx = anchor.positionMs - meanX
            val dy = anchor.delayMs - meanY
            numerator += dx * dy
            denominator += dx * dx
        }
        val rate = if (denominator == 0.0) 0.0 else numerator / denominator
        return DriftLinearFit(rate = rate, interceptMs = meanY - rate * meanX)
    }

    /**
     * Fits a line through all anchors, guarding against one bad anchor corrupting the rate: with
     * ≥3 anchors, if the worst residual exceeds the outlier bound, that single anchor is dropped
     * and the rest refit. If residuals are still too large, returns null — a single global rate
     * genuinely doesn't fit (more likely a real edit/cut difference than measurement noise), so
     * the caller must not force a linear correction.
     *
     * The FIRST anchor is never the one dropped: it's anchor 1 — measured in the foreground with
     * the largest pooling budget and implicitly user-verified (the flat delay it produced is on
     * screen). Dropping it would let two noisy background anchors redefine the line and visibly
     * destroy an already-correct sync near the viewing position.
     *
     * Noise floor: if the fitted rate's total effect across the measured anchor span is within
     * the per-anchor noise bound, the slope is not a measurement — it's noise wearing a slope.
     * Snap to a flat fit (rate 0, robust-mean delay) so unmeasurable "drift" is never applied.
     */
    fun fitDriftLineRobust(anchors: List<DriftAnchorPoint>): DriftLinearFit? {
        if (anchors.size < 2) return null
        val fit = fitDriftLine(anchors)
        if (anchors.size < 3) return snapNoiseRateToFlat(fit, anchors)

        val residuals = anchors.map { abs(it.delayMs - fit.predictMs(it.positionMs)) }
        if ((residuals.maxOrNull() ?: 0.0) <= DRIFT_RESIDUAL_OUTLIER_MS) {
            return snapNoiseRateToFlat(fit, anchors)
        }

        val worstIndex = (1 until anchors.size).maxByOrNull { residuals[it] } ?: return fit
        val withoutWorst = anchors.filterIndexed { index, _ -> index != worstIndex }
        val refit = fitDriftLine(withoutWorst)
        val refitResiduals = withoutWorst.map { abs(it.delayMs - refit.predictMs(it.positionMs)) }
        return if ((refitResiduals.maxOrNull() ?: 0.0) <= DRIFT_RESIDUAL_OUTLIER_MS) {
            snapNoiseRateToFlat(refit, withoutWorst)
        } else {
            null
        }
    }

    private fun snapNoiseRateToFlat(fit: DriftLinearFit, anchors: List<DriftAnchorPoint>): DriftLinearFit {
        val spanMs = (anchors.maxOf { it.positionMs } - anchors.minOf { it.positionMs })
        return if (abs(fit.rate) * spanMs <= DRIFT_RESIDUAL_OUTLIER_MS) {
            DriftLinearFit(rate = 0.0, interceptMs = robustMeanOfLongs(anchors.map { it.delayMs }).toDouble())
        } else {
            fit
        }
    }
}
