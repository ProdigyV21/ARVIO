package com.arflix.tv.ui.screens.player.preview

internal const val PREVIEW_APPROXIMATION_MS = 20_000L
internal const val PREVIEW_SPECULATIVE_BYTES = 128L * 1024 * 1024

/** One bounded job at a time: local detail, five-minute coverage, then sparse title coverage. */
internal class SeekPreviewCoverage {
    private var turn = 0
    private var attempts = 0
    private val retryAfter = mutableMapOf<Long, Long>()
    private var lastSeek: Long? = null
    private var seekDirection = 1
    private var intentUntilMs = 0L
    private var gestureOriginMs: Long? = null
    private var lastSeekTimeMs = 0L
    private var repeatedJumpMs: Long? = null
    private var predictionUntilMs = 0L
    private var coverageAnchorMs: Long? = null

    @Synchronized fun noteSeek(positionMs: Long, nowMs: Long, originMs: Long? = null) {
        if (gestureOriginMs == null) gestureOriginMs = originMs ?: lastSeek ?: positionMs
        lastSeekTimeMs = nowMs
        (lastSeek ?: originMs)?.let { previous ->
            if (positionMs != previous) seekDirection = if (positionMs < previous) -1 else 1
        }
        if (lastSeek != positionMs) turn = 0
        lastSeek = positionMs
        intentUntilMs = nowMs + 60_000L
    }

    @Synchronized fun endGesture() {
        val origin = gestureOriginMs
        val target = lastSeek
        if (origin != null && target != null) {
            repeatedJumpMs = (target - origin).takeIf { kotlin.math.abs(it) in 60_000L..1_800_000L }
            predictionUntilMs = lastSeekTimeMs + 120_000L
        }
        lastSeek = null
        gestureOriginMs = null
    }

    @Synchronized fun direction(nowMs: Long): Int = if (nowMs < intentUntilMs) seekDirection else 1

    @Synchronized fun next(positionMs: Long, durationMs: Long, sizeBytes: Long?, nowMs: Long,
        networkBytes: Long, preferWindow: Boolean = false, cached: (Long) -> Boolean): Long? {
        if (durationMs <= 0) return null
        val wideBudgetAvailable = networkBytes < PREVIEW_SPECULATIVE_BYTES && attempts < 512
        val anchor = positionMs.coerceIn(0, durationMs - 1) / 10_000 * 10_000
        // Keep distant points fixed while the playhead advances. Moving every point
        // on each turn repeatedly decodes almost the same scene and never fills gaps.
        val previousAnchor = coverageAnchorMs
        if (previousAnchor == null || positionMs < previousAnchor - 60_000 || positionMs > previousAnchor + 300_000) {
            coverageAnchorMs = anchor / 30_000L * 30_000L
        }
        val coverageAnchor = coverageAnchorMs ?: anchor
        val local = seekPreviewWarmTargets(positionMs, durationMs)
        val ahead = sequence {
            // Cover jumps first, then fill the half-minute gaps.
            for (step in 1..5) yield(anchor + step * 60_000L)
            for (step in 0..9) yield(anchor + 30_000L + step * 30_000L)
        }
        val whole = sequence {
            // Unknown sizes and large remuxes keep speculative reads near playback.
            if (sizeBytes != null && sizeBytes in 1 until 20L * 1024 * 1024 * 1024) {
                // First pass every two minutes, then one minute, then thirty seconds.
                for (stride in listOf(120_000L, 60_000L, 30_000L)) {
                    var target = stride
                    while (target < durationMs) { yield(target); target += stride }
                }
            } else {
                for (step in 6..10) yield(anchor + step * 60_000L)
            }
        }
        // Local work cannot starve distant coverage as the playback clock advances.
        val cycle = turn++
        val phase = cycle % 4
        val broad = if (!wideBudgetAvailable) local.asSequence() else when (phase) {
            1 -> ahead + local.asSequence() + whole
            3 -> whole + ahead + local.asSequence()
            else -> local.asSequence() + ahead + whole
        }
        // Establish the current strip once, then alternate local detail and stable
        // distant coverage. A moving playhead must not starve multi-minute jumps.
        val window = seekPreviewNeighborTargets(anchor, durationMs, direction(nowMs)) + anchor + local
        // Distant turns can prepare a likely repeat of the user's last long jump.
        // The same byte budget applies; never scan a large file.
        val predicted = repeatedJumpMs?.takeIf { nowMs < predictionUntilMs && wideBudgetAvailable }
            ?.let { jump -> (anchor + jump).takeIf { it in 0 until durationMs } }
        val prediction = predicted?.let { target ->
            sequenceOf(target) + seekPreviewNeighborTargets(target, durationMs, direction(nowMs)).asSequence()
        } ?: emptySequence()
        val distant = sequence {
            for (minutes in listOf(5, 10, 20, 2, 15, 1)) yield(coverageAnchor + minutes * 60_000L)
            for (minutes in listOf(5, 10, 20)) {
                yieldAll(seekPreviewNeighborTargets(coverageAnchor + minutes * 60_000L, durationMs))
            }
            // A 30-second first pass provides a nearby, honestly timestamped image;
            // a later ten-second pass refines it. Both use the same byte budget.
            for (step in 1..40) yield(coverageAnchor + step * 30_000L)
            for (step in 1..120) yield(coverageAnchor + step * 10_000L)
        }
        val ordered = when {
            !preferWindow -> broad
            !wideBudgetAvailable -> local.asSequence()
            cycle < 5 || cycle % 2 == 0 -> window.asSequence() + prediction + distant + broad
            else -> prediction + distant + broad
        }
        val target = ordered.filter { it in 0 until durationMs }
            .firstOrNull { (retryAfter[it] ?: 0) <= nowMs && !cached(it) } ?: return null
        if (kotlin.math.abs(target - positionMs) > 40_000L) attempts++
        // A failed sparse point must not trap every subsequent pass.
        retryAfter[target] = nowMs + 60_000L
        return target
    }
}
