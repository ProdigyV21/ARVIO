@file:OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.player.audiosync

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.media3.common.Format
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.audio.AudioSink
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.extractor.ExtractorsFactory
import com.arflix.tv.R
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.screens.player.audiosync.asr.AsrModel
import com.arflix.tv.ui.screens.player.subtitles.SubtitleSyncMatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Sync by hearing: the audio subtitle sync (the controller in this package), driven by the
 * "find best match" scan.
 *
 * The order is: the scan, then the audio, then AI. While the scan runs, the audio is already being
 * listened to ([arm]). When the scan verifies nothing, the audio takes the best unverified subtitle
 * over ([takeOver]) and every mapping it finds is handed to [onModel] to apply; when it cannot
 * confirm one, the scan's own fallback (AI translation) runs. A verified result, a user pick or a
 * new stream stops it ([stop]).
 */
internal class ArvioAudioSync(
    context: Context,
    private val scope: CoroutineScope,
    /** Apply [model] to the subtitle [key] took over (null: back to its own timing). Main thread. */
    private val onModel: (key: String, model: SubtitleSyncModel?) -> Unit,
    /** The audio fits another subtitle of the same language better: show [url] instead. */
    private val onSwitch: (url: String) -> Unit,
    /** True while the audio is syncing a subtitle it has not confirmed yet (the on-screen indicator). */
    private val onListening: (Boolean) -> Unit = {},
) {
    private val appContext = context.applicationContext
    private val controller: AudioSubtitleSyncController

    /** Playhead and duration of the player showing the subtitle (set by the player screen). */
    @Volatile var positionMs: () -> Long = { 0L }
    @Volatile var durationMs: () -> Long = { 0L }

    private var sourceUrl: String? = null
    private var sessionKey: String? = null
    private var appliedModel: SubtitleSyncModel? = null
    private var ticker: Job? = null
    private var contentKey: String? = null
    @Volatile private var callbackGeneration = 0L

    /** The next fallback (AI translation, when it can run), until the session stops. */
    private var pendingFailure: (() -> Unit)? = null
    private var betterFallback: () -> Boolean = { false }
    /** A confirmed lock (not an early estimate): the audio sync keeps the subtitle from here. */
    @Volatile private var confirmed = false
    private var heardMs = 0L
    private var lastTickPositionMs = -1L

    /** The scan's measurement at the playhead, and the constant mapping it stands for. */
    private var anchor: LocalAnchor? = null
    private var anchorModel: SubtitleSyncModel? = null
    private var anchorHolding = false

    /** Something else holds the screen until the audio confirms (see takeOver). */
    private var hold = false

    /** True while the audio works behind AI translation and has not taken the screen yet. */
    val holdingForConfirmation: Boolean get() = hold

    /** What reaches the screen, and when (see SteadyMapping). */
    private val steady = SteadyMapping()
    private var listeningShown = false
    private var lastSeenPositionMs = -1L

    init {
        initialize(appContext)
        controller = AudioSubtitleSyncController(
            context = appContext,
            // ARVIO applies the mapping to the subtitle itself, so the user's own delay adds on top.
            manualDelayMs = { 0 },
            onStatus = ::toast,
            requestSwitch = { url ->
                val generation = callbackGeneration
                mainHandler.post {
                    if (generation == callbackGeneration && sessionKey != null) onSwitch(url)
                }
            },
        )
        controller.listensBeforeSession = false
        controller.enabled = AudioSyncSettings.fallbackEnabled.value
        AudioSyncTaps.attach(controller)
    }

    val active: Boolean get() = sessionKey != null

    /** A new stream is about to play: stop, and point the controller's look-ahead sampler at it. */
    fun onStream(url: String, headers: Map<String, String>, allowSpotSampling: Boolean = true) {
        stop()
        if (url == sourceUrl) return
        sourceUrl = url
        contentKey = null
        controller.onSourceChanged(url)
        val dataSourceFactory = OkHttpDataSource.Factory(OkHttpProvider.playbackClient)
            .setDefaultRequestProperties(headers)
        controller.setSpotSource(
            forSourceKey = url,
            url = url,
            dataSourceFactory = dataSourceFactory,
            extractorsFactory = DefaultExtractorsFactory(),
            localEngine = isLoopback(url),
            allowExtraConnections = allowSpotSampling,
        )
    }

    fun onAudioTrackSelected(format: Format?) = controller.onAudioTrackSelected(format)

    /**
     * A scan starts: listen meanwhile, so a takeover starts with audio already heard. [references]
     * are the addon subtitles (English ones become recognition references); [contentType] /
     * [videoId] ("movie" / "tt123", "series" / "tt123:1:3") look up English references when the
     * addons have none.
     */
    fun arm(
        references: List<AudioSubtitleSyncController.ReferenceCandidate>,
        contentType: String?,
        videoId: String?,
        secondaryLanguage: String? = null,
    ) {
        stop()
        if (!AudioSyncSettings.fallbackEnabled.value) return
        controller.enabled = true
        controller.listensBeforeSession = true
        controller.secondaryPreferredLanguage = secondaryLanguage
        val key = "$contentType|$videoId"
        if (key != contentKey && !contentType.isNullOrBlank() && !videoId.isNullOrBlank()) {
            contentKey = key
            controller.setContent(contentType, videoId)
        }
        controller.setReferenceSubtitles(references)
        SyncLog.i("armed: ${references.size} addon subtitles, secondary=$secondaryLanguage, content=$contentType/$videoId")
    }

    /** Whether a takeover can happen at all. */
    val canTakeOver: Boolean get() = AudioSyncSettings.fallbackEnabled.value

    /**
     * The scan left [key]'s own timing unverified: sync its [cues] to the audio. When the audio
     * cannot confirm a mapping — the session can't run, or no confirmed lock within
     * [HEARING_DEADLINE_MS] of playback — the subtitle goes back to its own timing and [onFailed]
     * runs (the next fallback). A stop before that (a user pick, a new stream, a new scan) never
     * runs it. Null [onFailed] keeps the current one (the audio switching to a better subtitle).
     */
    fun takeOver(
        key: String,
        cues: List<SubtitleSyncMatcher.TimedCue>,
        onFailed: (() -> Unit)? = null,
        localAnchor: LocalAnchor? = null,
        /** Whether [onFailed] leads anywhere better than this subtitle's own timing (AI can run). */
        hasBetterFallback: (() -> Boolean)? = null,
        /**
         * Something else (AI translation) holds the screen: nothing is shown until the audio has
         * confirmed a timing, and a failure leaves the screen as it is. Null keeps the current
         * setting (the audio switching to a better subtitle).
         */
        holdUntilConfirmed: Boolean? = null,
    ): Boolean {
        val keepHold = holdUntilConfirmed ?: hold
        val next = onFailed ?: pendingFailure
        val better = hasBetterFallback ?: betterFallback
        // A switch to another subtitle keeps nothing: the anchor measured the one before.
        val keptAnchor = localAnchor
        if (!canTakeOver || cues.isEmpty()) {
            stop()
            return false
        }
        stop()
        sessionKey = key
        appliedModel = null
        confirmed = false
        heardMs = 0L
        lastTickPositionMs = -1L
        pendingFailure = next
        betterFallback = better
        hold = keepHold
        anchor = keptAnchor
        anchorModel = keptAnchor?.let { SubtitleSyncModel(listOf(SubtitleSyncSegment(0L, 1.0, it.shiftMs.toDouble()))) }
        anchorHolding = false
        steady.reset()
        lastSeenPositionMs = -1L
        keptAnchor?.let { SyncLog.i("anchor: ${it.shiftMs}ms measured at ${it.atMediaMs / 1_000}s — shown until the audio has measured there") }
        controller.enabled = true
        controller.startSession(key, AudioSyncBridge.toCuesWithTiming(cues))
        SyncLog.i("taking over \"$key\" (${cues.size} cues)")
        startTicker()
        return true
    }

    /** A verified match, a user pick or a new stream: the subtitle keeps (or gets back) its own timing. */
    fun stop() {
        callbackGeneration++
        showListening(false)
        hold = false
        pendingFailure = null
        anchor = null
        anchorModel = null
        ticker?.cancel()
        ticker = null
        val key = sessionKey
        sessionKey = null
        if (key != null && appliedModel != null) onModel(key, null)
        appliedModel = null
        controller.listensBeforeSession = false
        controller.stopSession()
    }

    private fun showListening(listening: Boolean) {
        if (listening == listeningShown) return
        listeningShown = listening
        onListening(listening)
    }

    /** The audio could not confirm the subtitle: back to its own timing, then the next fallback. */
    private fun fail(reason: String) {
        val next = pendingFailure
        SyncLog.i("hearing gave up: $reason")
        stop()
        next?.invoke()
    }

    fun release() {
        stop()
        AudioSyncTaps.detach(controller)
        controller.release()
    }

    /** Feeds the playhead and applies each new mapping, on the main thread. */
    private fun startTicker() {
        if (ticker?.isActive == true) return
        ticker = scope.launch {
            while (isActive) {
                val key = sessionKey ?: return@launch
                showListening(!confirmed)
                val position = positionMs()
                controller.onPlaybackPosition(position, durationMs().coerceAtLeast(0L))
                if (lastSeenPositionMs >= 0 && kotlin.math.abs(position - lastSeenPositionMs) > SEEK_JUMP_MS) steady.onSeek()
                lastSeenPositionMs = position
                if (hold && confirmed) {
                    // Behind AI, a confirmed timing takes the screen only when the model's own
                    // measurement confirms it: AI's timing comes from the file's own track, so a
                    // timing it does not confirm is not worth replacing it with (The Shards S01E04,
                    // Sept 2026: the audio said +8.3s then +13.2s where the model paired 6 lines at
                    // +10.2s). Contradicted, unmeasured, or away from where the model measured (no
                    // way to check), AI stays and the audio stops.
                    val at = anchor
                    val audio = controller.currentModel()
                    val near = at != null && position in (at.atMediaMs - ANCHOR_BEFORE_MS)..(at.atMediaMs + ANCHOR_AFTER_MS)
                    val audioMs = audio?.let { SteadyMapping.delayMs(it, position) }
                    val reason = when {
                        at == null || audioMs == null -> "nothing measured to confirm it against — AI stays"
                        !near -> "${position / 1_000}s is away from where the AI measured (${at.atMediaMs / 1_000}s) — AI stays"
                        kotlin.math.abs(audioMs - at.shiftMs) > ANCHOR_AGREE_MS ->
                            "its ${audioMs}ms contradicts the AI's ${at.shiftMs}ms at ${position / 1_000}s — AI stays"
                        else -> null
                    }
                    if (reason != null) {
                        fail(reason)
                        return@launch
                    }
                    SyncLog.i("the AI confirms the audio's ${audioMs}ms at ${position / 1_000}s — the subtitle replaces AI")
                    hold = false
                }
                val latest = shown(controller.currentModel(), position)
                // Behind AI: nothing reaches the screen before the audio has taken it (above).
                val decision = if (hold) SteadyMapping.Decision.Keep else steady.decide(latest, position, confirmed)
                when (decision) {
                    SteadyMapping.Decision.Keep -> Unit
                    SteadyMapping.Decision.Froze -> {
                        // The audio cannot be trusted here: on to the next fallback (AI translation)
                        // when there is one; without one, the last steady timing beats the
                        // subtitle's own, so it stays.
                        if (pendingFailure != null && betterFallback()) {
                            fail("the timing keeps reversing")
                            return@launch
                        }
                        SyncLog.i("steady: the timing keeps reversing, no better fallback — keeping the current one")
                        mainHandler.post {
                            Toast.makeText(appContext, appContext.getString(R.string.player_audio_sync_unsteady), Toast.LENGTH_LONG).show()
                        }
                    }
                    is SteadyMapping.Decision.Show -> {
                        val model = decision.model
                        if (model !== appliedModel) {
                            appliedModel = model
                            onModel(key, model)
                        }
                        decision.changeMs?.let { change ->
                            val nowMs = model?.let { SteadyMapping.delayMs(it, position) } ?: 0L
                            SyncLog.i("steady: adjusted by ${change}ms to ${nowMs}ms at ${position / 1_000}s")
                            mainHandler.post {
                                Toast.makeText(
                                    appContext,
                                    appContext.getString(R.string.player_audio_sync_adjusted, formatOffset(nowMs)),
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }
                    }
                }
                if (!confirmed && pendingFailure != null) {
                    // Only time the video actually plays counts: a pause is not the audio failing,
                    // and a seek is not listening (a 50s skip once ended the wait 38s in).
                    val advancedMs = position - lastTickPositionMs
                    if (lastTickPositionMs >= 0 && advancedMs in 1..MAX_TICK_ADVANCE_MS) heardMs += advancedMs
                    lastTickPositionMs = position
                    when {
                        controller.sessionCannotRun() -> { fail("the session cannot run"); return@launch }
                        heardMs >= HEARING_DEADLINE_MS -> {
                            fail("no confirmed sync after ${heardMs / 1_000}s of playback")
                            return@launch
                        }
                    }
                }
                delay(TICK_MS)
            }
        }
    }

    /**
     * What to show at [positionMs]: the audio's [model], unless the scan's [anchor] contradicts it
     * where the anchor was measured. The audio's first mappings often come from sampled spots far
     * from the playhead and are applied to the whole film until it hears the playhead itself — The
     * Shards S01E08 (Sept 2026): +6.5s from words at 6:40 shown at 1:30, where the model had paired
     * 7 of 8 lines at +4.6s. Near the anchor, a mapping within [ANCHOR_AGREE_MS] of it is the audio
     * agreeing (and taking over from then on); anything else there is the audio not having heard it.
     */
    private fun shown(model: SubtitleSyncModel?, positionMs: Long): SubtitleSyncModel? {
        val at = anchor ?: return model
        val fixed = anchorModel ?: return model
        if (model == null) return fixed
        val near = positionMs in (at.atMediaMs - ANCHOR_BEFORE_MS)..(at.atMediaMs + ANCHOR_AFTER_MS)
        val audioMs = model.delayUsAt(positionMs * 1_000L) / 1_000L
        val agrees = kotlin.math.abs(audioMs - at.shiftMs) <= ANCHOR_AGREE_MS
        if (agrees) {
            // A confirmed lock that measured the same thing owns the timing from here on,
            // everywhere. An early estimate that agrees is shown but can still be withdrawn, and
            // the anchor must still be there when it is.
            if (confirmed) {
                SyncLog.i("anchor: the audio confirms it (${audioMs}ms vs ${at.shiftMs}ms) — the audio's mapping from now on")
                anchor = null
                anchorModel = null
            }
            return model
        }
        val holding = near
        if (holding != anchorHolding) {
            anchorHolding = holding
            SyncLog.i(
                if (holding) "anchor: keeping ${at.shiftMs}ms at ${positionMs / 1_000}s over the audio's ${audioMs}ms"
                else "anchor: ${positionMs / 1_000}s is away from where it was measured — the audio's ${audioMs}ms"
            )
        }
        return if (holding) fixed else model
    }

    private fun toast(status: AudioSyncStatus) {
        if (status is AudioSyncStatus.Synced || status is AudioSyncStatus.Adjusted) confirmed = true
        // Announced by the ticker when it actually reaches the screen (SteadyMapping may hold it back).
        if (status is AudioSyncStatus.Adjusted) return
        val message = when (status) {
            AudioSyncStatus.Listening -> appContext.getString(R.string.player_audio_sync_listening)
            AudioSyncStatus.Withdrawn -> appContext.getString(R.string.player_audio_sync_withdrawn)
            is AudioSyncStatus.ModelDownloading ->
                appContext.getString(R.string.player_audio_sync_model_downloading, status.megabytes.toString())
            is AudioSyncStatus.LiveOnly -> appContext.getString(
                R.string.player_audio_sync_live_only,
                status.mimeType.substringAfter('/').uppercase(),
            )
            is AudioSyncStatus.Estimated ->
                appContext.getString(R.string.player_audio_sync_estimated, formatOffset(status.offsetMs))
            is AudioSyncStatus.Synced -> appContext.getString(
                if (status.rateCorrected) R.string.player_audio_sync_synced_rate else R.string.player_audio_sync_synced,
                formatOffset(status.offsetMs),
            )
            is AudioSyncStatus.Adjusted ->
                appContext.getString(R.string.player_audio_sync_adjusted, formatOffset(status.offsetMs))
        }
        val kind = when (status) {
            AudioSyncStatus.Listening, is AudioSyncStatus.ModelDownloading ->
                com.arflix.tv.ui.screens.player.audiosync.bubble.AutoSyncBubbleKind.Working
            AudioSyncStatus.Withdrawn ->
                com.arflix.tv.ui.screens.player.audiosync.bubble.AutoSyncBubbleKind.Failure
            else ->
                com.arflix.tv.ui.screens.player.audiosync.bubble.AutoSyncBubbleKind.Success
        }
        com.arflix.tv.ui.screens.player.audiosync.bubble.showAutoSyncMessage(appContext, kind, message)
    }

    /**
     * The scan's own measurement of the subtitle's timing at one point of the film: [shiftMs]
     * (media = subtitle + shift) where the model paired its lines with the built-in track, around
     * [atMediaMs]. Shown from the takeover on, and kept there until the audio agrees (see [shown]).
     */
    data class LocalAnchor(val atMediaMs: Long, val shiftMs: Long)

    companion object {
        private const val TICK_MS = 250L
        /** A playhead move this large between two ticks is a seek. */
        private const val SEEK_JUMP_MS = 30_000L
        /** The most one tick of normal playback moves the playhead (250ms, with room for a slow tick). */
        private const val MAX_TICK_ADVANCE_MS = 2_000L
        /** Where the scan's anchor holds: from a minute before it to four after (what the audio hears next). */
        private const val ANCHOR_BEFORE_MS = 60_000L
        private const val ANCHOR_AFTER_MS = 240_000L
        /** The audio and the anchor within this of each other measured the same timing. */
        private const val ANCHOR_AGREE_MS = 700L

        /** Playback time the audio gets to confirm a subtitle before the next fallback (AI) takes over. */
        private const val HEARING_DEADLINE_MS = 90_000L
        private val mainHandler = Handler(Looper.getMainLooper())
        private val initialized = AtomicBoolean(false)

        /** Settings persistence and the speech model. Safe to call more than once. */
        fun initialize(context: Context) {
            if (!initialized.compareAndSet(false, true)) return
            val appContext = context.applicationContext
            val preferences = appContext.getSharedPreferences("arvio_audio_sync_settings", Context.MODE_PRIVATE)
            AudioSyncSettings.installPersistence(
                load = { key -> if (preferences.contains(key)) preferences.getBoolean(key, false) else null },
                save = { key, value -> preferences.edit().putBoolean(key, value).apply() },
            )
            AsrModel.initialize(appContext)
        }

        private fun isLoopback(url: String): Boolean = runCatching {
            val host = Uri.parse(url).host.orEmpty().lowercase()
            host == "localhost" || host == "127.0.0.1" || host == "::1" || host == "[::1]"
        }.getOrDefault(false)

        private fun formatOffset(offsetMs: Long): String {
            val sign = if (offsetMs < 0) "-" else "+"
            val tenths = (kotlin.math.abs(offsetMs) + 50) / 100
            return "$sign${tenths / 10}.${tenths % 10}s"
        }

        /**
         * [raw] (SRT/VTT text) with every line placed on the media timeline by [model] (subtitle
         * time -> media time): a line of a scene the release cuts has no place and is dropped (a
         * subtitle delay would never show it either), and durations take the scale of the segment
         * the line lands in.
         */
        fun retime(raw: String, model: SubtitleSyncModel): String =
            SubtitleSyncMatcher.retimeOrDropCues(raw) { startMs, endMs ->
                val start = model.mediaTimeUs(startMs * 1_000L)?.div(1_000L) ?: return@retimeOrDropCues null
                val duration = ((endMs - startMs) * model.segmentAt(start).scale).toLong().coerceAtLeast(1L)
                start to start + duration
            }
    }
}

/**
 * Routes audio the player demuxes or plays to the active [ArvioAudioSync]. The player's extractors
 * and audio output are created before it, so they look it up here; the playing stream is read when
 * an extractor is created.
 */
internal object AudioSyncTaps {
    @Volatile
    private var active: AudioSubtitleSyncController? = null

    fun attach(controller: AudioSubtitleSyncController) {
        active = controller
    }

    fun detach(controller: AudioSubtitleSyncController) {
        if (active === controller) active = null
    }

    /** Copies the audio of the playing stream ([currentSourceKey]) while it is demuxed (look-ahead). */
    fun wrapExtractors(factory: ExtractorsFactory, currentSourceKey: () -> String?): ExtractorsFactory =
        if (!com.arflix.tv.BuildConfig.AUDIO_SYNC_AVAILABLE) factory else AudioSyncExtractorsFactory(factory, object : AudioSampleSink {
            private fun current() = active?.takeIf { it.currentSourceKey == currentSourceKey() }

            override fun wantsSamples(format: Format): Boolean = current()?.wantsSamples(format) == true

            override fun onSample(format: Format, timeUs: Long, data: ByteArray, offset: Int, size: Int) {
                current()?.onSample(format, timeUs, data, offset, size)
            }

            override fun onDiscontinuity() {
                current()?.onDiscontinuity()
            }
        })

    /** The audio format the player has selected (for the player screen, outside this package). */
    fun androidx.media3.common.Tracks.selectedAudio(): Format? = selectedAudioFormat()

    /** Hears the player's own decoded audio where the look-ahead copy can't be decoded. */
    fun wrapAudioSink(sink: AudioSink): AudioSink = if (!com.arflix.tv.BuildConfig.AUDIO_SYNC_AVAILABLE) sink else PlaybackAudioTap(sink, object : PlaybackPcmListener {
        override fun wantsPlaybackPcm(mediaTimeUs: Long, durationUs: Long): Boolean =
            active?.wantsPlaybackPcm(mediaTimeUs, durationUs) == true

        override fun onPlaybackPcm(mono: FloatArray, frames: Int, sampleRate: Int, mediaTimeUs: Long) {
            active?.onPlaybackPcm(mono, frames, sampleRate, mediaTimeUs)
        }
    })
}
