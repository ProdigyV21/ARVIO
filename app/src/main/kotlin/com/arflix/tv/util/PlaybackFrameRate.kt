package com.arflix.tv.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.abs
import kotlin.math.roundToInt

/** Uses the existing decoder output, never a second network connection. */
class PlaybackFrameRate {
    private val mutableRate = MutableStateFlow(0f)
    val rate = mutableRate.asStateFlow()
    private var previousUs: Long? = null
    private val intervals = ArrayList<Long>(48)
    private var candidateRate = 0f
    private var candidateFrames = 0
    private var measuredRate = 0f
    private var measuredCandidate = 0f
    private var measuredCandidateWindows = 0

    @Synchronized
    fun reset() {
        previousUs = null
        intervals.clear()
        candidateRate = 0f
        candidateFrames = 0
        measuredRate = 0f
        measuredCandidate = 0f
        measuredCandidateWindows = 0
        mutableRate.value = 0f
    }

    /** Seed AFR even on renderers that do not dispatch per-frame metadata callbacks. */
    @Synchronized
    fun onInputFormat(frameRate: Float) {
        if (mutableRate.value == 0f && frameRate.isFinite() && frameRate in 10f..120f) {
            mutableRate.value = stablePlaybackRate(frameRate)
        }
    }

    @Synchronized
    fun onFrame(timeUs: Long, declaredRate: Float) {
        if (measuredRate == 0f && declaredRate.isFinite() && declaredRate in 10f..120f) {
            val rate = stablePlaybackRate(declaredRate)
            if (mutableRate.value == 0f || rate == mutableRate.value) {
                mutableRate.value = rate
                candidateFrames = 0
            } else {
                // Adaptive formats may briefly disagree. Do not renegotiate HDMI per frame.
                candidateFrames = if (candidateRate == rate) candidateFrames + 1 else 1
                candidateRate = rate
                if (candidateFrames >= 48) {
                    mutableRate.value = rate
                    candidateFrames = 0
                }
            }
        } else {
            candidateFrames = 0
        }
        val delta = previousUs?.let { timeUs - it }
        previousUs = timeUs
        if (delta == null) return
        if (delta !in 8_000L..100_000L) {
            intervals.clear()
            measuredCandidateWindows = 0
            return
        }
        intervals.add(delta)
        if (intervals.size < 48) return
        val sorted = intervals.sorted()
        val median = sorted[sorted.size / 2]
        // Matroska commonly rounds PTS to milliseconds: 24fps alternates 41/42ms.
        // Fit their cumulative timestamps to smooth rounding without losing the
        // distinction between 23.976/24 and 59.94/60. No wall-clock timing is used.
        val tolerance = maxOf(1_000.0, median * 0.02)
        val stable = intervals.filter { abs(it - median) <= tolerance }
        if (stable.size >= 44) {
            val midpoint = stable.size / 2.0
            var timestamp = 0L
            var covariance = 0.0
            stable.forEachIndexed { index, interval ->
                timestamp += interval
                covariance += (index + 1 - midpoint) * timestamp
            }
            val count = stable.size.toDouble()
            val slope = covariance / (count * (count + 1) * (count + 2) / 12)
            val estimate = stablePlaybackRate((1_000_000.0 / slope).toFloat())
            if (estimate in 10f..120f) {
                measuredCandidateWindows = if (abs(measuredCandidate - estimate) <= 0.01f) {
                    measuredCandidateWindows + 1
                } else 1
                measuredCandidate = estimate
                // Missing metadata can start immediately; correcting a declared rate
                // needs two stable windows so an intro or dropped frame cannot flip HDMI.
                if (mutableRate.value == 0f || measuredCandidateWindows >= 2) {
                    measuredRate = estimate
                    mutableRate.value = estimate
                }
            }
        } else {
            measuredCandidateWindows = 0
        }
        intervals.clear()
    }
}

private val STANDARD_PLAYBACK_RATES = floatArrayOf(24000f / 1001f, 24f, 25f,
    30000f / 1001f, 30f, 48f, 50f, 60000f / 1001f, 60f, 100f, 120000f / 1001f, 120f)

internal fun stablePlaybackRate(fps: Float): Float {
    return STANDARD_PLAYBACK_RATES.minByOrNull { abs(it - fps) }
        ?.takeIf { abs(it - fps) <= 0.01f } ?: fps
}

internal fun matchingRefreshRateIndex(
    rates: List<Float>,
    fps: Float,
    activeIndex: Int? = null,
    allowFractionalFallback: Boolean = false,
): Int? {
    if (!fps.isFinite() || fps !in 10f..120f) return null
    val matches = rates.indices.filter { index ->
        val rate = rates[index]
        if (!rate.isFinite() || rate <= 0f) false else {
            val multiple = (rate / fps).roundToInt()
            multiple >= 1 && abs(rate / multiple - fps) <= 0.012f
        }
    }
    // Keep a compatible active mode (e.g. 120Hz for 24fps) instead of blanking HDMI.
    if (matches.isNotEmpty()) {
        return activeIndex?.takeIf { it in matches } ?: matches.minByOrNull { rates[it] }
    }
    if (!allowFractionalFallback) return null
    // Some HDMI mode lists expose only the integer counterpart (24 rather than 23.976).
    // Prefer that cadence to 60Hz pulldown, but never override an available exact match.
    val nearMatches = rates.indices.filter { index ->
        val rate = rates[index]
        val multiple = if (rate.isFinite() && rate > 0f) (rate / fps).roundToInt() else 0
        multiple >= 1 && abs(rate / multiple - fps) / fps <= 0.0011f
    }
    return activeIndex?.takeIf { it in nearMatches } ?: nearMatches.minByOrNull { rates[it] }
}
