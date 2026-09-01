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

        // Non-linear rescue: playback moving this far from the last measurement (naturally or by
        // a user jump) triggers the next flat re-measure; poll cadence; how far ahead of the live
        // position to measure; and the minimum change worth re-applying (below it = noise).
        private const val RESYNC_INTERVAL_MS = 10 * 60_000L
        private const val RESYNC_POLL_MS = 5_000L
        private const val RESYNC_LOOKAHEAD_MS = 45_000L
        private const val RESYNC_MIN_CHANGE_MS = 450L

        // Bounded gather timeout per target attempt. Background-only cost; dialogue can
        // genuinely be minutes away in quiet stretches.
        private const val GATHER_TIMEOUT_MS = 240_000L

        // No video/audio renderers → the secondary player runs on its standalone clock, so a
        // faster-than-realtime speed shortens gathering; network throughput (the whole muxed
        // stream must still be read to reach subtitle samples) is the practical ceiling, and a
        // stalled buffer just slows this back down gracefully.
        private const val GATHER_PLAYBACK_SPEED = 4f

        // Give the primary player's buffer a moment to be healthy before opening a second
        // concurrent connection. Never blocks for long.
        private const val BUFFER_HEALTH_CHECK_ATTEMPTS = 5
        private const val BUFFER_HEALTH_CHECK_INTERVAL_MS = 1_000L
        private const val BUFFER_HEALTH_MIN_MARGIN_MS = 5_000L
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
        val onFitAccepted: suspend (interceptMs: Long, rate: Double) -> Unit
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
     * Runs the full anchors-2..4 pass. Suspends until done; throws only [kotlinx.coroutines.CancellationException].
     */
    suspend fun run(params: Params): Outcome {
        val durationMs = params.durationMs
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
        var healthAttempt = 0
        while (healthAttempt < BUFFER_HEALTH_CHECK_ATTEMPTS &&
            params.primaryBufferMarginMs() < BUFFER_HEALTH_MIN_MARGIN_MS
        ) {
            healthAttempt++
            delay(BUFFER_HEALTH_CHECK_INTERVAL_MS)
        }

        val anchors = mutableListOf(
            SubtitleAutoSync.DriftAnchorPoint(params.anchor1PositionMs, params.anchor1DelayMs)
        )
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

        for (plannedTarget in plannedTargets) {
            if (anchors.size >= MAX_TOTAL_ANCHORS) break

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
                Log.i(TAG, "anchor ${anchors.size + 1}: all target attempts failed — trying next planned target")
                continue
            }
            if (anchors.any { abs(anchorResult.positionMs - it.positionMs) < MIN_SEPARATION_MS }) {
                Log.i(TAG, "anchor ${anchors.size + 1}: too close to an existing anchor — skipping")
                continue
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
                continue
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
        if (!appliedAnyFit) Log.i(TAG, "no anchor beyond anchor1 produced a usable fit — keeping flat delay")
        return when {
            appliedAnyFit -> Outcome.FIT_APPLIED
            rejectedByLineFit > 0 -> Outcome.NON_LINEAR
            else -> Outcome.INCONCLUSIVE
        }
    }

    /**
     * Non-linear rescue: when the timing difference is stepwise (ad-break cuts / different edit)
     * no line fits — the flat anchor-1 offset goes stale as playback crosses each cut. This loop
     * re-measures a flat offset JUST AHEAD of the live playback position whenever playback has
     * moved [RESYNC_INTERVAL_MS] away from the last measurement — naturally (10 min of watching)
     * or instantly via a user jump — and hands it to [onOffsetMeasured] when it moved beyond
     * noise. Position-based rather than wall-clock: a jump lands in a possibly different timing
     * segment and must not wait out a timer, and paused playback burns no measurements.
     * Runs until cancelled or playback ends; every failure just waits for the next trigger.
     */
    suspend fun runPeriodicResync(
        params: Params,
        playbackPositionMs: () -> Long,
        currentDelayMs: () -> Long,
        onOffsetMeasured: suspend (offsetMs: Long, measuredAtMs: Long) -> Unit
    ) {
        Log.i(TAG, "periodic resync engaged (non-linear timing)")
        var lastMeasuredPosMs = playbackPositionMs()
        while (true) {
            delay(RESYNC_POLL_MS)
            val positionMs = playbackPositionMs()
            if (positionMs <= 0L ||
                positionMs >= params.durationMs - TRAILING_SAFETY_MARGIN_MS
            ) {
                continue
            }
            if (abs(positionMs - lastMeasuredPosMs) < RESYNC_INTERVAL_MS) continue
            // Attempt (success or fail) resets the trigger — a quiet stretch shouldn't hammer.
            lastMeasuredPosMs = positionMs
            // Measure slightly ahead so the corrected stretch is what plays next; the expected
            // delay is whatever is currently applied (steps are local, not slope-driven).
            val targetMs = (positionMs + RESYNC_LOOKAHEAD_MS)
                .coerceAtMost(params.durationMs - TRAILING_SAFETY_MARGIN_MS)
            val anchor = gatherAnchorAtTarget(params, targetMs) { currentDelayMs() } ?: continue
            val deltaMs = anchor.delayMs - currentDelayMs()
            if (abs(deltaMs) <= RESYNC_MIN_CHANGE_MS) {
                Log.i(TAG, "resync at ${anchor.positionMs}ms: delay unchanged (Δ=${deltaMs}ms)")
                continue
            }
            Log.i(TAG, "resync at ${anchor.positionMs}ms: delay=${anchor.delayMs}ms (Δ=${deltaMs}ms)")
            onOffsetMeasured(anchor.delayMs, anchor.positionMs)
        }
    }

    /** One target position: spin up the invisible player, gather lines, match, pool, reduce. */
    private suspend fun gatherAnchorAtTarget(
        params: Params,
        targetPositionMs: Long,
        expectedDelayAt: (Long) -> Long
    ): SubtitleAutoSync.DriftAnchorPoint? {
        val cueBuffer = Collections.synchronizedList(mutableListOf<SubtitleSyncMatcher.TimedCue>())
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

        try {
            player.volume = 0f
            player.addListener(object : Player.Listener {
                override fun onCues(cueGroup: CueGroup) {
                    if (cueGroup.cues.isEmpty()) return
                    val presentationTimeMs = (cueGroup.presentationTimeUs / 1000L).coerceAtLeast(0L)
                    val text = cueGroup.cues
                        .mapNotNull { it.text?.toString()?.trim()?.takeIf { t -> t.isNotEmpty() } }
                        .firstOrNull { !SubtitleAutoSync.isNonDialogueMusicCue(it) }
                    if (text != null) {
                        cueBuffer += SubtitleSyncMatcher.TimedCue(presentationTimeMs, presentationTimeMs, text)
                        return
                    }
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

            val mediaSource = params.createMediaSource() ?: return null
            player.setMediaSource(mediaSource)
            player.prepare()
            player.seekTo(targetPositionMs)
            player.playbackParameters = PlaybackParameters(GATHER_PLAYBACK_SPEED)
            player.playWhenReady = true

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
            ocrScope.cancel()
            runCatching { player.stop() }
            runCatching { player.clearMediaItems() }
            runCatching { player.release() }
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
