package com.arflix.tv.ui.screens.player

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.media3.common.C
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.common.TrackSelectionOverride
import androidx.media3.common.Tracks
import androidx.media3.common.text.CueGroup
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.audio.AudioRendererEventListener
import androidx.media3.exoplayer.audio.AudioSink
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.exoplayer.video.VideoRendererEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.util.Collections
import kotlin.math.abs

/**
 * AI auto-sync anchors 2–4: best-effort background calibration of a *linear* subtitle drift
 * (frame-rate-mismatch style) on top of anchor 1's flat delay.
 *
 * An invisible, text-only secondary ExoPlayer instant-seeks to up to 3 distant positions and
 * repeats anchor 1's measurement there (built-in cues → LLM match against the addon file →
 * pooled offsets → robust mean). After each gathered anchor a line
 * `delay(position) = intercept + rate·position` is refit across all anchors
 * ([SubtitleAutoSync.fitDriftLineRobust]) and, when accepted, handed to the caller to apply
 * (ARVIO bakes it into the served subtitle file).
 *
 * Entirely fail-safe: any failure just leaves whatever delay/fit is already in place. The caller
 * owns cancellation — every await point here is a suspension, so cancelling the job invalidates
 * the run.
 */
class SubtitleDriftCalibrator(private val context: Context) {

    companion object {
        private const val TAG = "SubSyncDrift"

        // Never target within this margin of either edge of the content.
        private const val TRAILING_SAFETY_MARGIN_MS = 150_000L

        // Below this separation between anchors, a rate can't be measured above matching noise.
        const val MIN_SEPARATION_MS = 90_000L

        // A target landing in an extended quiet/music stretch (gather timeout with no lines) is
        // retried at a nudged nearby position, bounded so a bad stream still fails safely.
        private const val MAX_TARGET_RETRY_ATTEMPTS = 3
        private const val TARGET_NUDGE_MS = 180_000L

        // Total calibration points including anchor 1.
        private const val MAX_TOTAL_ANCHORS = 4

        // Fractions of the safety-trimmed usable room (from anchor 1 toward whichever edge has
        // more room) at which anchors 2..4 are targeted — spread wide for a stronger slope.
        private val ANCHOR_ROOM_FRACTIONS = doubleArrayOf(0.5, 0.667, 0.833)

        // Per-anchor pooling (smaller than anchor 1's budget — the multi-anchor fit itself now
        // adds redundancy across anchors).
        private const val POOL_MAX_ATTEMPTS = 2
        private const val POOL_TARGET_OFFSETS = 2

        // Pre-fit window sizing: worst plausible frame-rate drift (PAL 25↔23.976 ≈ 0.043) plus
        // margin, and a typical dialogue cue cadence for converting a time span to a line count.
        private const val MAX_PLAUSIBLE_DRIFT_RATE = 0.05
        private const val TYPICAL_CUE_GAP_MS = 2_500.0

        // Non-linear rescue (periodic resync): the loop approximates the true delay curve with
        // short line segments re-anchored just ahead of the viewer. Cadence and rule are derived
        // from measured data — see runPeriodicResync. Position-based, not wall-clock.
        private const val RESYNC_MAX_INTERVAL_MS = 10 * 60_000L
        private const val RESYNC_MIN_INTERVAL_MS = 2 * 60_000L
        // Max subtitle error allowed to accumulate between samplings; next interval = TOL/|v|.
        private const val RESYNC_TOLERANCE_MS = 1_000L
        private const val RESYNC_BACKOFF_FACTOR = 1.5
        private const val RESYNC_POLL_MS = 5_000L
        private const val RESYNC_LOOKAHEAD_MS = 45_000L
        // Measured deltas at or below this are indistinguishable from measurement error.
        private const val RESYNC_NOISE_MS = 450L

        // A between-poll position jump beyond this is a user seek: the viewer may have landed in
        // a differently-timed segment, so the rule and cadence are RE-SEEDED from the measurement
        // set (free — no new sampling) rather than waiting out the current interval.
        private const val RESYNC_SEEK_DETECT_MS = 30_000L

        // Below this local velocity a slope is measurement noise — apply the offset flat.
        private const val RESYNC_MIN_RATE = 0.001

        // Hard sampling budget: at most this many sampling attempts per rolling hour of
        // wall-clock time (each attempt = one extra stream connection + 1–2 LLM calls). On
        // typical content the backoff keeps usage far below it; on pathological content the
        // loop degrades gracefully to the max interval instead of hammering the device/API.
        private const val RESYNC_BUDGET_PER_HOUR = 15
        private const val RESYNC_BUDGET_WINDOW_MS = 60 * 60_000L

        // Bounded gather timeout per target attempt. Background-only cost; dialogue can
        // genuinely be minutes away in quiet stretches.
        private const val GATHER_TIMEOUT_MS = 240_000L

        // No video/audio renderers → the secondary player runs on its standalone clock, so a
        // faster-than-realtime speed shortens gathering; network throughput (the whole muxed
        // stream must still be read to reach subtitle samples) is the practical ceiling, and a
        // stalled buffer just slows this back down gracefully.
        private const val GATHER_PLAYBACK_SPEED = 4f

        // Give the primary player's buffer a moment to be healthy before opening a second
        // concurrent connection. Never blocks for long. Re-checked before EVERY gather (anchors,
        // resync samplings, background scans) — a single check before the first anchor let the
        // later back-to-back gathers starve the primary buffer and stutter playback.
        private const val BUFFER_HEALTH_CHECK_ATTEMPTS = 20
        private const val BUFFER_HEALTH_CHECK_INTERVAL_MS = 1_000L
        private const val BUFFER_HEALTH_MIN_MARGIN_MS = 5_000L

        // Small settle before a subsequent secondary-player connection. The real pacing is the
        // dynamic buffer-health gate immediately afterwards: healthy streams continue quickly,
        // while low-buffer streams can wait up to BUFFER_HEALTH_CHECK_ATTEMPTS seconds.
        private const val INTER_GATHER_COOLDOWN_MS = 3_000L
    }

    /** Everything the calibration needs from the player screen / view model. */
    class Params(
        /** Fresh media source for the currently playing stream (no subtitle side-loads needed). */
        val createMediaSource: () -> MediaSource?,
        /** True when an embedded text track's language matches anchor 1's reference track. */
        val referenceLangMatches: (String?) -> Boolean,
        /** Parsed cues of the ORIGINAL (untransformed) addon subtitle file, sorted by start. */
        val addonCues: List<SubtitleSyncMatcher.TimedCue>,
        /** LLM semantic matcher (source lines, target lines) → index pairs. */
        val matchLines: suspend (List<String>, List<String>) -> SubtitleTranslationService.LineMatchResult,
        val anchor1PositionMs: Long,
        val anchor1DelayMs: Long,
        val durationMs: Long,
        /** Primary-player buffered margin (bufferedPosition − currentPosition), for contention. */
        val primaryBufferMarginMs: () -> Long,
        /** Apply an accepted fit: `delay(position) = interceptMs + rate·position`. */
        val onFitAccepted: suspend (interceptMs: Long, rate: Double) -> Unit,
        /** User-visible progress for long-running background refinement/rematch work. */
        val onStatus: (String) -> Unit = {}
    )

    /** How the anchors-2..4 pass ended — drives the non-linear periodic rescue. */
    enum class Outcome {
        /** A linear fit was accepted and applied — extrapolation covers the rest of playback. */
        FIT_APPLIED,

        /** Anchors measured fine but NO line fits them — evidence of non-linear timing (cuts). */
        NON_LINEAR,

        /** Nothing measured/not enough room — no evidence either way. */
        INCONCLUSIVE
    }

    /**
     * Every anchor successfully measured during [run] — INCLUDING anchors the line fit rejected
     * (they are still trusted measurements of the true delay curve; rejection only means no
     * single line passes through all of them). This is the initial measurement set the
     * non-linear rescue seeds its rule and cadence from.
     */
    private val measuredAnchors = mutableListOf<SubtitleAutoSync.DriftAnchorPoint>()

    /**
     * Reference cue intervals accumulated from EVERY secondary-player session this playback
     * (anchor gathers, resync samplings). The embedded track's timing is ground truth, so these
     * are free reference material for timing-based candidate scoring — the background rematch
     * scores against them instead of opening yet another stream connection.
     */
    private val collectedIntervals = mutableListOf<Pair<Long, Long>>()

    /** Snapshot of all reference intervals gathered so far, sorted by start. */
    fun collectedReferenceIntervals(): List<Pair<Long, Long>> =
        synchronized(collectedIntervals) { collectedIntervals.sortedBy { it.first } }

    /**
     * Reference cue BATCHES (text + true video time) retained from every secondary session.
     * Embedded cues are a property of the VIDEO, not of any subtitle file — so a batch gathered
     * while syncing subtitle A can be LLM-matched against subtitle B's file for free (no new
     * player session, only the LLM call). Consumed by [measureFlatOffsetFromRetained] and by
     * [run] as free anchors before any player spins up.
     */
    private val collectedCueBatches = mutableListOf<List<SubtitleSyncMatcher.TimedCue>>()

    /**
     * Clears all retained VIDEO-derived material (intervals + cue batches). Per NEW STREAM only —
     * a different stream may be a different cut. Deliberately NOT cleared between runs on
     * different subtitles of the same stream: that cross-subtitle reuse is the whole point.
     */
    fun resetForNewStream() {
        synchronized(collectedIntervals) { collectedIntervals.clear() }
        synchronized(collectedCueBatches) { collectedCueBatches.clear() }
    }

    private fun recordSession(session: GatherSession) {
        val gatheredIntervals = synchronized(session.intervals) { session.intervals.toList() }
        val gatheredCues = synchronized(session.cues) { session.cues.toList() }
        recordExternalReference(gatheredCues, gatheredIntervals)
    }

    /**
     * Records reference material gathered OUTSIDE the calibrator into the same retained pools —
     * anchor 1 runs on the PRIMARY player and produces both true-timed cue batches and (while
     * the reference track is selected) real buffered intervals. With stage 1 + stage 2 material
     * retained, the background rematch normally needs no new gathers at all.
     */
    fun recordExternalReference(
        batch: List<SubtitleSyncMatcher.TimedCue>,
        intervals: List<Pair<Long, Long>>
    ) {
        if (intervals.isNotEmpty()) {
            synchronized(collectedIntervals) {
                collectedIntervals += intervals
                // Bounded: oldest-first trim; hundreds of intervals are plenty for scoring.
                while (collectedIntervals.size > 500) collectedIntervals.removeAt(0)
            }
        }
        if (batch.size >= 2) {
            synchronized(collectedCueBatches) {
                collectedCueBatches += batch.sortedBy { it.startMs }
                while (collectedCueBatches.size > 30) collectedCueBatches.removeAt(0)
            }
        }
    }

    /**
     * LLM-matches a retained reference batch against [Params.addonCues] and reduces it to one
     * anchor — the playerless core of [gatherAnchorAtTarget], with the same reliability gates
     * (≥2 agreeing offsets, spread bound). Null = unusable batch, costs only the LLM call(s).
     */
    private suspend fun anchorFromBatch(
        params: Params,
        batch: List<SubtitleSyncMatcher.TimedCue>,
        expectedDelayAt: (Long) -> Long,
        initialWindowRadius: Int
    ): SubtitleAutoSync.DriftAnchorPoint? {
        val lines = batch.takeLast(SubtitleAutoSync.SOURCE_LINE_COUNT)
        if (lines.size < 2) return null
        val addonStarts = params.addonCues.map { it.startMs }
        var windowRadius = initialWindowRadius
        repeat(POOL_MAX_ATTEMPTS) {
            val expectedDelayMs = expectedDelayAt(lines.last().startMs)
            val window = SubtitleAutoSync.targetWindow(
                addonStarts, lines.last().startMs - expectedDelayMs, windowRadius
            )
            if (window.isEmpty()) return null
            val windowCues = params.addonCues.subList(window.first, window.last + 1)
            val result = params.matchLines(lines.map { it.text }, windowCues.map { it.text })
            if (result.success && result.pairs.isNotEmpty()) {
                val offsets = result.pairs.map { (s, t) -> lines[s].startMs - windowCues[t].startMs }
                if (offsets.size < 2) return null
                if (offsets.size == 2 && offsets.max() - offsets.min() > SubtitleAutoSync.OUTLIER_THRESHOLD_MS) {
                    return null
                }
                return SubtitleAutoSync.DriftAnchorPoint(
                    positionMs = lines.last().startMs,
                    delayMs = SubtitleAutoSync.robustMeanOfLongs(offsets)
                )
            }
            // No matches may mean the true offset falls outside the window — widen and retry.
            windowRadius *= 2
        }
        return null
    }

    /**
     * Playerless flat-offset measurement of an arbitrary subtitle file from the retained batch
     * NEAREST [nearPositionMs] — no stream connection at all, only LLM calls. Null when no
     * usable batch exists (caller falls back to [measureFlatOffset]).
     */
    suspend fun measureFlatOffsetFromRetained(
        params: Params,
        nearPositionMs: Long,
        expectedDelayMs: Long
    ): SubtitleAutoSync.DriftAnchorPoint? {
        val batches = synchronized(collectedCueBatches) { collectedCueBatches.toList() }
            .sortedBy { abs(it.last().startMs - nearPositionMs) }
        for (batch in batches.take(2)) {
            val anchor = anchorFromBatch(
                params, batch, { expectedDelayMs }, SubtitleAutoSync.TARGET_WINDOW_RADIUS
            )
            if (anchor != null) return anchor
        }
        return null
    }

    /**
     * Runs the full anchors-2..4 pass. Suspends until done; throws only [kotlinx.coroutines.CancellationException].
     */
    suspend fun run(params: Params): Outcome {
        val durationMs = params.durationMs
        measuredAnchors.clear()
        if (durationMs <= 0L || params.addonCues.isEmpty()) return Outcome.INCONCLUSIVE

        // Pick whichever side of anchor 1 has more room, so this works wherever sync happened.
        val forwardRoomMs = durationMs - params.anchor1PositionMs
        val backwardRoomMs = params.anchor1PositionMs
        val useForward = forwardRoomMs >= backwardRoomMs
        val availableRoomMs = if (useForward) forwardRoomMs else backwardRoomMs
        if (availableRoomMs < MIN_SEPARATION_MS + TRAILING_SAFETY_MARGIN_MS) {
            Log.i(TAG, "skip: not enough room (available=${availableRoomMs}ms duration=$durationMs)")
            return Outcome.INCONCLUSIVE
        }
        val usableRoomMs = (availableRoomMs - TRAILING_SAFETY_MARGIN_MS).coerceAtLeast(0L)
        val plannedTargets = ANCHOR_ROOM_FRACTIONS.map { fraction ->
            (if (useForward) {
                params.anchor1PositionMs + (usableRoomMs * fraction).toLong()
            } else {
                params.anchor1PositionMs - (usableRoomMs * fraction).toLong()
            }).coerceIn(0L, durationMs)
        }
        Log.i(
            TAG,
            "scheduling: anchor1=(${params.anchor1PositionMs},${params.anchor1DelayMs}) " +
                "targets=$plannedTargets forward=$useForward duration=$durationMs"
        )

        // Bandwidth-contention guard before opening a second connection.
        awaitPrimaryBufferHealthy(params)

        val anchors = mutableListOf(
            SubtitleAutoSync.DriftAnchorPoint(params.anchor1PositionMs, params.anchor1DelayMs)
        )
        measuredAnchors += anchors.first()
        var appliedAnyFit = false
        // Anchors that measured consistently but were rejected by the LINE fit — the signature
        // of non-linear timing (edit/cut differences), as opposed to nothing being measurable.
        var rejectedByLineFit = 0
        // Window centering must track the best CURRENT delay estimate: with a real frame-rate
        // drift (PAL 25↔23.976 ≈ +2.6 s/min) the true delay at a distant anchor sits tens of
        // seconds away from anchor 1's flat delay — centered there, the ±12-line window misses
        // the correct addon lines entirely (anchors fail) or matches the wrong ones (bad fits).
        var currentFit: SubtitleAutoSync.DriftLinearFit? = null
        fun expectedDelayAt(positionMs: Long): Long =
            currentFit?.let { Math.round(it.predictMs(positionMs)) } ?: params.anchor1DelayMs

        // Shared accept/reject bookkeeping for an anchor from any source (retained batch or
        // fresh gather): separation check → refit → apply or count the line-fit rejection.
        suspend fun integrateAnchor(anchorResult: SubtitleAutoSync.DriftAnchorPoint) {
            measuredAnchors += anchorResult
            if (anchors.any { abs(anchorResult.positionMs - it.positionMs) < MIN_SEPARATION_MS }) {
                Log.i(TAG, "anchor at ${anchorResult.positionMs}ms: too close to an existing anchor — skipping")
                return
            }
            anchors += anchorResult
            val fit = SubtitleAutoSync.fitDriftLineRobust(anchors)
            if (fit == null || abs(fit.rate) > SubtitleAutoSync.DRIFT_RATE_MAX_ABS) {
                anchors.removeAt(anchors.lastIndex)
                rejectedByLineFit++
                Log.i(
                    TAG,
                    "discarding anchor at ${anchorResult.positionMs}ms (" +
                        (if (fit == null) "residuals too large" else "rate ${fit.rate} out of range") +
                        ") — keeping ${anchors.size} anchor(s)"
                )
                return
            }
            Log.i(
                TAG,
                "fit with ${anchors.size} anchors: rate=${fit.rate} interceptMs=${fit.interceptMs} " +
                    "anchors=${anchors.joinToString { "(${it.positionMs},${it.delayMs})" }}"
            )
            currentFit = fit
            params.onFitAccepted(Math.round(fit.interceptMs), fit.rate)
            appliedAnyFit = true
        }

        // NOTE: retained cue batches are deliberately NOT consumed here. The anchor pass owes its
        // non-linearity detection to WIDE anchor geometry (anchor 1 + the spread planned targets
        // below); retained Stage-1 batches cluster near the viewing position and would eat the
        // anchor budget with redundant near measurements. Retained material serves the BACKGROUND
        // stages instead: rematch scoring (collectedReferenceIntervals) and the winner's flat
        // anchor-1 (measureFlatOffsetFromRetained).
        for ((targetIndex, plannedTarget) in plannedTargets.withIndex()) {
            if (anchors.size >= MAX_TOTAL_ANCHORS) break
            // Pace consecutive gathers: let the primary buffer refill between anchors, and never
            // open the second connection while the primary is running low.
            if (targetIndex > 0) {
                params.onStatus(
                    "Auto-sync refinement ${targetIndex + 1}/${plannedTargets.size} starts in " +
                        "${INTER_GATHER_COOLDOWN_MS / 1000}s…"
                )
                delay(INTER_GATHER_COOLDOWN_MS)
            }
            params.onStatus(
                "Auto-sync refinement ${targetIndex + 1}/${plannedTargets.size} — measuring…"
            )
            awaitPrimaryBufferHealthy(params)

            var currentTarget = plannedTarget
            var anchorResult: SubtitleAutoSync.DriftAnchorPoint? = null
            for (targetAttempt in 1..MAX_TARGET_RETRY_ATTEMPTS) {
                Log.i(TAG, "anchor ${anchors.size + 1}: attempt $targetAttempt at ${currentTarget}ms")
                anchorResult = gatherAnchorAtTarget(params, currentTarget, ::expectedDelayAt)
                if (anchorResult != null) break
                // Likely an extended quiet/music stretch — nudge and retry nearby.
                currentTarget = (if (useForward) {
                    currentTarget + TARGET_NUDGE_MS
                } else {
                    currentTarget - TARGET_NUDGE_MS
                }).coerceIn(0L, durationMs)
            }
            if (anchorResult == null) {
                params.onStatus(
                    "Auto-sync refinement ${targetIndex + 1}/${plannedTargets.size} — no dialogue found"
                )
                Log.i(TAG, "anchor ${anchors.size + 1}: all target attempts failed — trying next planned target")
                continue
            }
            integrateAnchor(anchorResult)
        }
        if (!appliedAnyFit) Log.i(TAG, "no anchor beyond anchor1 produced a usable fit — keeping flat delay")
        // End-of-pass validation: the applied line must explain EVERY trusted measurement —
        // including anchors the robust fit dropped as "outliers" and near-duplicates skipped for
        // separation. With ≤3 accepted anchors the robust fit can never fail (dropping the worst
        // leaves an exact 2-point line), so without this check a disagreeing measurement is
        // silently swallowed and the NON_LINEAR handoff (rematch + resync) never starts.
        // 2× margin: a noise-snapped flat fit can legitimately sit up to twice the per-anchor
        // bound from one noisy anchor; real edit/cut differences are seconds off, far beyond it.
        val finalFit = currentFit
        val defiantAnchor = finalFit?.let { fit ->
            measuredAnchors.firstOrNull {
                abs(it.delayMs - fit.predictMs(it.positionMs)) >
                    2 * SubtitleAutoSync.DRIFT_RESIDUAL_OUTLIER_MS
            }
        }
        if (defiantAnchor != null) {
            Log.i(
                TAG,
                "final fit misses trusted anchor (${defiantAnchor.positionMs},${defiantAnchor.delayMs}) — non-linear"
            )
        }
        return when {
            // Any trusted anchor that refuses the final line is evidence of non-linear timing,
            // even when a subset of anchors produced an accepted fit. This must win over
            // appliedAnyFit or the rematch/resync handoff is silently skipped.
            rejectedByLineFit > 0 || defiantAnchor != null -> Outcome.NON_LINEAR
            appliedAnyFit -> Outcome.FIT_APPLIED
            else -> Outcome.INCONCLUSIVE
        }
    }

    /**
     * Non-linear rescue: when the timing difference is stepwise (ad-break cuts / different edit)
     * no line fits — but every anchor measured so far is still a TRUE point of the delay curve.
     * This loop approximates that arbitrary curve with short line segments re-anchored just
     * ahead of the viewer, growing a measurement set `M` (anchor-pass anchors + every resync
     * sample) as it goes:
     *
     *  SEED (at start and after every user seek — free, pure arithmetic on `M`):
     *    take the two measurements bracketing the viewer (widened outward until they are at
     *    least [MIN_SEPARATION_MS] apart), slope v = ΔD/ΔP between them, then
     *      |v| < [RESYNC_MIN_RATE]            → flat rule at the interpolated delay, max interval
     *      plausible v (≤ [MAX_PLAUSIBLE_DRIFT_RATE]) → interpolation line as rule,
     *                                           interval = tolerance/|v| (clamped)
     *      |v| > max plausible                → a step hides between them: flat rule at the
     *                                           NEAREST measurement, min interval
     *
     *  LOOP: wait until the viewer has watched `interval` past where the previous sampling
     *  FINISHED (position-based: pauses cost nothing; a >[RESYNC_SEEK_DETECT_MS] jump re-seeds
     *  immediately), then sample one anchor ~[RESYNC_LOOKAHEAD_MS] ahead and decide:
     *    (A) |δ| ≤ noise      → rule confirmed: keep it, interval ×[RESYNC_BACKOFF_FACTOR]
     *                           (δ = measured delay − applied rule's prediction there; a correct
     *                           slope keeps δ≈0 even while the delay itself keeps drifting)
     *    (B) plausible v      → new rule through the sample with slope v (v taken against the
     *                           nearest other measurement in `M` ≥ [MIN_SEPARATION_MS] away),
     *                           interval = tolerance/|v| (clamped)
     *    (C) otherwise (step / v uncomputable / δ big with v≈0) → flat rule at the sample,
     *                           min interval
     *
     *  BUDGET: at most [RESYNC_BUDGET_PER_HOUR] sampling attempts per rolling hour — beyond it
     *  the loop degrades gracefully to the max interval instead of hammering the device/API.
     *
     * Runs until cancelled or playback ends; every failure just waits for the next trigger.
     */
    suspend fun runPeriodicResync(
        params: Params,
        playbackPositionMs: () -> Long,
        currentDelayAtMs: (positionMs: Long) -> Long,
        onOffsetMeasured: suspend (delayMs: Long, measuredAtMs: Long, rate: Double) -> Unit
    ) {
        // M: all trusted measurements of the true delay curve, sorted by position.
        val measurements = measuredAnchors.sortedBy { it.positionMs }.toMutableList()
        Log.i(TAG, "periodic resync engaged (non-linear timing, ${measurements.size} known anchors)")
        val sampleTimesMs = ArrayDeque<Long>()
        var intervalMs = RESYNC_MAX_INTERVAL_MS

        suspend fun seed(positionMs: Long, reason: String) {
            val pair = bracketingPair(measurements, positionMs) ?: run {
                intervalMs = RESYNC_MAX_INTERVAL_MS
                Log.i(TAG, "seed ($reason): no usable measurement pair — max interval")
                return
            }
            val (lo, hi) = pair
            val v = (hi.delayMs - lo.delayMs).toDouble() / (hi.positionMs - lo.positionMs)
            when {
                abs(v) < RESYNC_MIN_RATE -> {
                    // Delays agree around the viewer — the non-linearity lives elsewhere.
                    val interpolated = lo.delayMs +
                        Math.round(v * (positionMs - lo.positionMs))
                    intervalMs = RESYNC_MAX_INTERVAL_MS
                    Log.i(TAG, "seed ($reason): flat ${interpolated}ms (v=$v) interval=${intervalMs}ms")
                    onOffsetMeasured(interpolated, positionMs, 0.0)
                }
                abs(v) <= MAX_PLAUSIBLE_DRIFT_RATE -> {
                    // Delay in measured motion here — ride the interpolation between the anchors.
                    intervalMs = (RESYNC_TOLERANCE_MS / abs(v)).toLong()
                        .coerceIn(RESYNC_MIN_INTERVAL_MS, RESYNC_MAX_INTERVAL_MS)
                    Log.i(TAG, "seed ($reason): slope v=$v through (${lo.positionMs},${lo.delayMs}) interval=${intervalMs}ms")
                    onOffsetMeasured(lo.delayMs, lo.positionMs, v)
                }
                else -> {
                    // A step (cut) hides between the pair; we don't know on which side of the
                    // viewer. Trust the nearest measurement, come back soon.
                    val nearest = if (abs(positionMs - lo.positionMs) <= abs(hi.positionMs - positionMs)) lo else hi
                    intervalMs = RESYNC_MIN_INTERVAL_MS
                    Log.i(TAG, "seed ($reason): step between pair (v=$v) — flat ${nearest.delayMs}ms interval=${intervalMs}ms")
                    onOffsetMeasured(nearest.delayMs, positionMs, 0.0)
                }
            }
        }

        var lastMeasuredPosMs = playbackPositionMs()
        var lastPolledPosMs = lastMeasuredPosMs
        seed(lastMeasuredPosMs, "start")
        while (true) {
            delay(RESYNC_POLL_MS)
            val positionMs = playbackPositionMs()
            if (positionMs <= 0L ||
                positionMs >= params.durationMs - TRAILING_SAFETY_MARGIN_MS
            ) {
                lastPolledPosMs = positionMs
                continue
            }
            if (abs(positionMs - lastPolledPosMs) > RESYNC_SEEK_DETECT_MS) {
                // User seek: possibly a differently-timed segment — re-derive rule and cadence
                // from the measurement set (free) and restart the interval from the landing.
                seed(positionMs, "seek")
                lastMeasuredPosMs = positionMs
            }
            lastPolledPosMs = positionMs
            if (abs(positionMs - lastMeasuredPosMs) < intervalMs) continue
            // Budget gate: degrade to the max interval instead of sampling when exhausted.
            val nowMs = System.currentTimeMillis()
            while (sampleTimesMs.isNotEmpty() && nowMs - sampleTimesMs.first() > RESYNC_BUDGET_WINDOW_MS) {
                sampleTimesMs.removeFirst()
            }
            if (sampleTimesMs.size >= RESYNC_BUDGET_PER_HOUR) {
                intervalMs = RESYNC_MAX_INTERVAL_MS
                lastMeasuredPosMs = positionMs
                Log.i(TAG, "resync budget exhausted (${sampleTimesMs.size}/h) — degrading to max interval")
                continue
            }
            sampleTimesMs.addLast(nowMs)
            // Never open the second connection while the primary buffer is running low.
            awaitPrimaryBufferHealthy(params)
            // Measure slightly ahead so the corrected stretch is what plays next; the expected
            // delay is whatever the current rule predicts there.
            val targetMs = (positionMs + RESYNC_LOOKAHEAD_MS)
                .coerceAtMost(params.durationMs - TRAILING_SAFETY_MARGIN_MS)
            val anchor = gatherAnchorAtTarget(params, targetMs, currentDelayAtMs)
            // Attempt (success or fail) resets the trigger — a quiet stretch shouldn't hammer.
            // The gather itself can outlast the interval (quiet stretches run up to the gather
            // timeout), so the interval counts from where playback stands when the measurement
            // FINISHES — otherwise a slow gather re-triggers immediately, chaining stream
            // connections and LLM calls back-to-back. Refreshing the poll baseline too keeps
            // playback progress during the gather from being mistaken for a user seek.
            lastMeasuredPosMs = playbackPositionMs().takeIf { it > 0L } ?: positionMs
            lastPolledPosMs = lastMeasuredPosMs
            if (anchor == null) continue
            val insertAt = measurements.indexOfFirst { it.positionMs > anchor.positionMs }
            if (insertAt < 0) measurements.add(anchor) else measurements.add(insertAt, anchor)
            // δ: the error the viewer actually accumulated — measured truth vs the applied rule.
            val deltaMs = anchor.delayMs - currentDelayAtMs(anchor.positionMs)
            if (abs(deltaMs) <= RESYNC_NOISE_MS) {
                // (A) Rule confirmed (a correct slope keeps δ≈0 even under active drift) — trust
                // it a bit longer each time.
                intervalMs = (intervalMs * RESYNC_BACKOFF_FACTOR).toLong()
                    .coerceAtMost(RESYNC_MAX_INTERVAL_MS)
                Log.i(TAG, "resync at ${anchor.positionMs}ms: rule confirmed (Δ=${deltaMs}ms nextInterval=${intervalMs}ms)")
                continue
            }
            // Local velocity vs the nearest other measurement far enough away not to drown the
            // slope in per-anchor noise. Every element of M is ground truth regardless of seeks:
            // a velocity spanning a hidden cut simply comes out implausible and lands in (C).
            val reference = nearestMeasurementBeyond(measurements, anchor)
            val velocity = reference?.let {
                (anchor.delayMs - it.delayMs).toDouble() / (anchor.positionMs - it.positionMs)
            }
            if (velocity != null && abs(velocity) in RESYNC_MIN_RATE..MAX_PLAUSIBLE_DRIFT_RATE) {
                // (B) Measurable motion — new rule through this sample with the local slope; the
                // next check comes when the error COULD reach tolerance if the slope were wrong.
                intervalMs = (RESYNC_TOLERANCE_MS / abs(velocity)).toLong()
                    .coerceIn(RESYNC_MIN_INTERVAL_MS, RESYNC_MAX_INTERVAL_MS)
                Log.i(TAG, "resync at ${anchor.positionMs}ms: delay=${anchor.delayMs}ms (Δ=${deltaMs}ms v=$velocity nextInterval=${intervalMs}ms)")
                onOffsetMeasured(anchor.delayMs, anchor.positionMs, velocity)
            } else {
                // (C) Step / unattributable change — flat correction, stay alert.
                intervalMs = RESYNC_MIN_INTERVAL_MS
                Log.i(TAG, "resync at ${anchor.positionMs}ms: delay=${anchor.delayMs}ms (Δ=${deltaMs}ms step/v=$velocity nextInterval=${intervalMs}ms)")
                onOffsetMeasured(anchor.delayMs, anchor.positionMs, 0.0)
            }
        }
    }

    /**
     * The two measurements bracketing [positionMs], widened outward (preferring the closer
     * extension) until they are at least [MIN_SEPARATION_MS] apart — a pair closer than that
     * can't carry a slope above per-anchor noise. Falls back to the nearest two when the
     * position is outside the measured range; null when no valid pair exists.
     */
    private fun bracketingPair(
        sorted: List<SubtitleAutoSync.DriftAnchorPoint>,
        positionMs: Long
    ): Pair<SubtitleAutoSync.DriftAnchorPoint, SubtitleAutoSync.DriftAnchorPoint>? {
        if (sorted.size < 2) return null
        var hi = sorted.indexOfFirst { it.positionMs >= positionMs }
        var lo: Int
        when {
            hi < 0 -> { hi = sorted.lastIndex; lo = hi - 1 }      // beyond the last measurement
            hi == 0 -> { lo = 0; hi = 1 }                          // before the first
            else -> lo = hi - 1
        }
        while (sorted[hi].positionMs - sorted[lo].positionMs < MIN_SEPARATION_MS) {
            val canLo = lo > 0
            val canHi = hi < sorted.lastIndex
            when {
                !canLo && !canHi -> return null
                canLo && (!canHi ||
                    positionMs - sorted[lo - 1].positionMs <= sorted[hi + 1].positionMs - positionMs
                    ) -> lo--
                else -> hi++
            }
        }
        return sorted[lo] to sorted[hi]
    }

    /** Nearest measurement to [anchor] (excluding it) at least [MIN_SEPARATION_MS] away. */
    private fun nearestMeasurementBeyond(
        sorted: List<SubtitleAutoSync.DriftAnchorPoint>,
        anchor: SubtitleAutoSync.DriftAnchorPoint
    ): SubtitleAutoSync.DriftAnchorPoint? =
        sorted.filter { it !== anchor && abs(it.positionMs - anchor.positionMs) >= MIN_SEPARATION_MS }
            .minByOrNull { abs(it.positionMs - anchor.positionMs) }

    /** Bounded wait for the primary player's buffer to be healthy before a secondary gather. */
    private suspend fun awaitPrimaryBufferHealthy(params: Params) {
        var attempt = 0
        while (attempt < BUFFER_HEALTH_CHECK_ATTEMPTS &&
            params.primaryBufferMarginMs() < BUFFER_HEALTH_MIN_MARGIN_MS
        ) {
            attempt++
            delay(BUFFER_HEALTH_CHECK_INTERVAL_MS)
        }
    }

    /**
     * A running invisible text-only player session at a target position. [cues] are point cues
     * (start = end = appearance time) for LLM line-matching; [intervals] are (appear, disappear)
     * pairs of text cues for timing-based scoring (OCR cues land asynchronously, so they feed
     * [cues] only — their interval edges would be wrong).
     */
    private class GatherSession(
        val player: ExoPlayer,
        val cues: MutableList<SubtitleSyncMatcher.TimedCue>,
        val intervals: MutableList<Pair<Long, Long>>,
        val ocrScope: CoroutineScope
    ) {
        fun release() {
            ocrScope.cancel()
            runCatching { player.stop() }
            runCatching { player.clearMediaItems() }
            runCatching { player.release() }
        }
    }

    /** Builds, prepares and starts the invisible session; null when no media source is available. */
    private fun startGatherSession(params: Params, targetPositionMs: Long): GatherSession? {
        val cueBuffer = Collections.synchronizedList(mutableListOf<SubtitleSyncMatcher.TimedCue>())
        val intervalBuffer = Collections.synchronizedList(mutableListOf<Pair<Long, Long>>())
        var trackLocked = false
        val trackSelector = DefaultTrackSelector(context).apply {
            setParameters(
                buildUponParameters()
                    .setTrackTypeDisabled(C.TRACK_TYPE_VIDEO, true)
                    .setTrackTypeDisabled(C.TRACK_TYPE_AUDIO, true)
            )
        }
        // Small bounded buffer window so this only fetches data around the target point.
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(10_000, 15_000, 500, 1_000)
            .build()
        val player = ExoPlayer.Builder(context, TextOnlyRenderersFactory(context))
            .setTrackSelector(trackSelector)
            .setLoadControl(loadControl)
            .build()
        // OCR jobs for bitmap (PGS/DVB) reference cues — bounded to this gather.
        val ocrScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        player.volume = 0f
        player.addListener(object : Player.Listener {
            // Open text cue for interval tracking: closed when the cue set changes or empties.
            private var openStartMs = -1L
            private var openText: String? = null

            override fun onCues(cueGroup: CueGroup) {
                val presentationTimeMs = (cueGroup.presentationTimeUs / 1000L).coerceAtLeast(0L)
                val text = cueGroup.cues
                    .mapNotNull { it.text?.toString()?.trim()?.takeIf { t -> t.isNotEmpty() } }
                    .firstOrNull { !SubtitleAutoSync.isNonDialogueMusicCue(it) }
                // Any change of the visible text (including to nothing) closes the open interval.
                if (openStartMs >= 0 && text != openText) {
                    if (presentationTimeMs > openStartMs) intervalBuffer += openStartMs to presentationTimeMs
                    openStartMs = -1L
                    openText = null
                }
                if (text != null) {
                    if (openText == null) {
                        openStartMs = presentationTimeMs
                        openText = text
                    }
                    cueBuffer += SubtitleSyncMatcher.TimedCue(presentationTimeMs, presentationTimeMs, text)
                    return
                }
                if (cueGroup.cues.isEmpty()) return
                // Image-based (PGS/DVB) reference track: no cue text — OCR the bitmap, same
                // fallback as anchor 1's realtime path (mirrors NuvioTV's drift calibrator).
                val bitmap = cueGroup.cues.firstNotNullOfOrNull { it.bitmap } ?: return
                ocrScope.launch {
                    val recognized = runCatching { SubtitleCueOcr.recognizeSubtitleBitmapText(bitmap) }
                        .getOrNull()
                        ?.replace(Regex("\\s+"), " ")
                        ?.trim()
                        ?.takeIf { it.length >= 2 && !SubtitleAutoSync.isNonDialogueMusicCue(it) }
                        ?: return@launch
                    cueBuffer += SubtitleSyncMatcher.TimedCue(presentationTimeMs, presentationTimeMs, recognized)
                }
            }

            override fun onTracksChanged(tracks: Tracks) {
                // Track-identity lock: select the same-language embedded text track anchor 1
                // used, so both anchors measure against the same reference timing.
                if (trackLocked) return
                var fallbackGroup: Tracks.Group? = null
                var fallbackIndex = -1
                for (group in tracks.groups) {
                    if (group.type != C.TRACK_TYPE_TEXT) continue
                    for (i in 0 until group.length) {
                        val format = group.getTrackFormat(i)
                        if (fallbackGroup == null) {
                            fallbackGroup = group
                            fallbackIndex = i
                        }
                        if (params.referenceLangMatches(format.language)) {
                            player.trackSelectionParameters = player.trackSelectionParameters
                                .buildUpon()
                                .setOverrideForType(TrackSelectionOverride(group.mediaTrackGroup, i))
                                .build()
                            trackLocked = true
                            return
                        }
                    }
                }
                val group = fallbackGroup
                if (group != null && fallbackIndex >= 0) {
                    player.trackSelectionParameters = player.trackSelectionParameters
                        .buildUpon()
                        .setOverrideForType(TrackSelectionOverride(group.mediaTrackGroup, fallbackIndex))
                        .build()
                    trackLocked = true
                }
            }
        })
        val mediaSource = params.createMediaSource()
        if (mediaSource == null) {
            ocrScope.cancel()
            runCatching { player.release() }
            return null
        }
        player.setMediaSource(mediaSource)
        player.prepare()
        player.seekTo(targetPositionMs)
        player.playbackParameters = PlaybackParameters(GATHER_PLAYBACK_SPEED)
        player.playWhenReady = true
        return GatherSession(player, cueBuffer, intervalBuffer, ocrScope)
    }

    /** One target position: spin up the invisible player, gather lines, match, pool, reduce. */
    private suspend fun gatherAnchorAtTarget(
        params: Params,
        targetPositionMs: Long,
        expectedDelayAt: (Long) -> Long
    ): SubtitleAutoSync.DriftAnchorPoint? {
        val session = startGatherSession(params, targetPositionMs) ?: return null
        val cueBuffer = session.cues

        try {
            val pooledOffsetsMs = mutableListOf<Long>()
            var lastCuePositionMs: Long? = null
            val addonStarts = params.addonCues.map { it.startMs }
            // Widened when an attempt finds no matches — the true offset at this distance may
            // fall outside the default window (same fallback anchor 1 uses). Before any fit
            // exists, the initial radius also scales with the distance from anchor 1: at the
            // worst plausible drift rate the true delay moves ~3 s per minute of distance, which
            // at typical anchor spacing is far outside the default ±12 lines.
            val distanceMs = abs(targetPositionMs - params.anchor1PositionMs)
            val preFitSlackLines = if (expectedDelayAt(targetPositionMs) == params.anchor1DelayMs) {
                ((MAX_PLAUSIBLE_DRIFT_RATE * distanceMs) / TYPICAL_CUE_GAP_MS).toInt().coerceAtMost(48)
            } else 0
            var windowRadius = SubtitleAutoSync.TARGET_WINDOW_RADIUS + preFitSlackLines
            for (poolAttempt in 1..POOL_MAX_ATTEMPTS) {
                // Each attempt needs genuinely NEW lines (temperature 0 would repeat identical
                // input verbatim) — the player keeps advancing, so wait for the next batch.
                val neededCueCount = poolAttempt * SubtitleAutoSync.SOURCE_LINE_COUNT
                val gathered = withTimeoutOrNull(GATHER_TIMEOUT_MS) {
                    while (cueBuffer.size < neededCueCount) delay(250)
                    true
                } ?: false
                if (!gathered) break

                val batch = synchronized(cueBuffer) { cueBuffer.toList() }.takeLast(SubtitleAutoSync.SOURCE_LINE_COUNT)
                if (batch.isEmpty()) break
                // Cue timestamps here are true video time (this player never applies any subtitle
                // delay). The matching addon line sits near trueTime − expectedDelay in file time;
                // the expectation follows the current fit, not anchor 1's flat delay — a real
                // drift moves it by tens of seconds at this distance.
                val expectedDelayMs = expectedDelayAt(batch.last().startMs)
                val window = SubtitleAutoSync.targetWindow(
                    addonStarts, batch.last().startMs - expectedDelayMs, windowRadius
                )
                if (window.isEmpty()) break
                val windowCues = params.addonCues.subList(window.first, window.last + 1)
                val result = params.matchLines(batch.map { it.text }, windowCues.map { it.text })
                if (result.success && result.pairs.isNotEmpty()) {
                    pooledOffsetsMs += result.pairs.map { (s, t) ->
                        batch[s].startMs - windowCues[t].startMs
                    }
                    lastCuePositionMs = batch.last().startMs
                } else {
                    windowRadius *= 2
                }
                if (pooledOffsetsMs.size >= POOL_TARGET_OFFSETS) break
            }

            if (pooledOffsetsMs.isEmpty()) return null
            // Reliability gate: a background anchor rests on far fewer matches than anchor 1, and
            // ONE wrong-line match here silently tilts the whole fitted rate. If the pooled
            // offsets don't even agree with each other, the anchor is noise — discard it (the
            // caller retries at a nudged position).
            if (pooledOffsetsMs.size < 2) {
                Log.i(TAG, "anchor at ${targetPositionMs}ms discarded: only ${pooledOffsetsMs.size} offset(s)")
                return null
            }
            val spread = (pooledOffsetsMs.max() - pooledOffsetsMs.min())
            if (pooledOffsetsMs.size == 2 && spread > SubtitleAutoSync.OUTLIER_THRESHOLD_MS) {
                Log.i(TAG, "anchor at ${targetPositionMs}ms discarded: offsets disagree (spread=${spread}ms)")
                return null
            }
            return SubtitleAutoSync.DriftAnchorPoint(
                positionMs = lastCuePositionMs ?: targetPositionMs,
                delayMs = SubtitleAutoSync.robustMeanOfLongs(pooledOffsetsMs)
            )
        } finally {
            recordSession(session)
            session.release()
        }
    }

    /**
     * Background reference-interval collection (step 2 of the combined flow): reads the embedded
     * reference track's (appear, disappear) cue intervals near [targetPositionMs] through the
     * invisible secondary player, for timing-based candidate scoring — the same intervals the
     * on-screen match scan reads from the primary player, without touching the display.
     */
    suspend fun gatherReferenceIntervals(
        params: Params,
        targetPositionMs: Long,
        targetCount: Int,
        timeoutMs: Long
    ): List<Pair<Long, Long>> {
        awaitPrimaryBufferHealthy(params)
        val session = startGatherSession(params, targetPositionMs) ?: return emptyList()
        try {
            withTimeoutOrNull(timeoutMs) {
                while (session.intervals.size < targetCount) delay(250)
            }
            return synchronized(session.intervals) { session.intervals.toList() }
                .sortedBy { it.first }
        } finally {
            recordSession(session)
            session.release()
        }
    }
}

/**
 * A renderers factory with no video/audio renderers: the calibration player only ever needs
 * subtitle cue timestamps, so the dominant CPU/decoder cost of a normal player is avoided by
 * construction.
 */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
private class TextOnlyRenderersFactory(context: Context) : DefaultRenderersFactory(context) {
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
        // Intentionally empty.
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
        // Intentionally empty.
    }
}
