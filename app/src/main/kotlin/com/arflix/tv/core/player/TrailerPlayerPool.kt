package com.arflix.tv.core.player

import android.content.Context
import android.util.Log
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Application-scoped singleton that holds a single ExoPlayer instance dedicated to
 * trailer preview playback on the home and details screens.
 *
 * Creating and tearing down ExoPlayer for every poster focus is extremely expensive
 * on Android TV hardware (codec initialization, hardware decoder allocation). This pool keeps
 * one instance alive and reuses it across focus changes. The player is stopped and cleared
 * between uses but never released until the process terminates or [release] is explicitly called.
 *
 * Strict focus synchronization is maintained via owner tokens so that focus changes immediately
 * invalidate and cancel any previous or pending playback.
 *
 * When the full-screen player needs hardware decoders, call [yield] to free
 * codec resources without destroying the instance. Call [reclaim] when returning to
 * the home screen to lazily rebuild if needed.
 */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Singleton
class TrailerPlayerPool @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val TAG = "TrailerPlayerPool"
    }

    private var _player: ExoPlayer? = null
    private val yielded = AtomicBoolean(false)
    private val released = AtomicBoolean(false)

    @Volatile
    private var activeOwnerToken: String? = null
    private val lock = Any()
    private val mainHandler = Handler(Looper.getMainLooper())

    private fun runOnMain(block: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            block()
        } else {
            mainHandler.post(block)
        }
    }

    /**
     * Returns the shared trailer ExoPlayer, creating it lazily if needed.
     * If [ownerToken] is specified, any playback from a different owner is stopped immediately.
     */
    fun acquire(ownerToken: String? = null): ExoPlayer? {
        synchronized(lock) {
            if (released.get()) return null
            if (yielded.get()) {
                // Reclaim was not called yet but someone wants the player — rebuild lazily.
                reclaim()
            }
            if (ownerToken != null && activeOwnerToken != null && activeOwnerToken != ownerToken) {
                stopInternal()
            }
            if (ownerToken != null) {
                activeOwnerToken = ownerToken
            }
            return _player ?: createPlayer().also { _player = it }
        }
    }

    /**
     * Checks whether [ownerToken] is still the active owner of the player.
     */
    fun isCurrentOwner(ownerToken: String?): Boolean {
        if (ownerToken == null) return false
        return activeOwnerToken == ownerToken && !released.get() && !yielded.get()
    }

    /**
     * Stops playback and clears media items immediately.
     * If [ownerToken] is specified, only stops if [ownerToken] matches the current active owner.
     */
    fun stop(ownerToken: String? = null) {
        synchronized(lock) {
            if (ownerToken != null && activeOwnerToken != null && activeOwnerToken != ownerToken) {
                return
            }
            if (ownerToken == null || activeOwnerToken == ownerToken) {
                activeOwnerToken = null
            }
            stopInternal()
        }
    }

    private fun stopInternal() {
        runOnMain {
            _player?.let { player ->
                runCatching {
                    player.playWhenReady = false
                    player.stop()
                    player.clearMediaItems()
                }
            }
        }
    }

    /**
     * Releases codec resources so the main video player can claim hardware decoders.
     * The ExoPlayer instance is released here; [reclaim] will allow creating a fresh one.
     */
    fun yield() {
        if (yielded.compareAndSet(false, true)) {
            Log.d(TAG, "Yielding trailer player for main video playback")
            synchronized(lock) {
                activeOwnerToken = null
                runOnMain {
                    _player?.let { player ->
                        runCatching { player.stop() }
                        runCatching { player.clearMediaItems() }
                        runCatching { player.release() }
                    }
                    _player = null
                }
            }
        }
    }

    /**
     * Re-enables player creation after a [yield]. Safe to call multiple times.
     */
    fun reclaim() {
        if (released.get()) return
        if (yielded.compareAndSet(true, false)) {
            Log.d(TAG, "Reclaiming trailer player")
            // Player will be lazily created on next acquire()
        }
    }

    /**
     * Permanently releases the player. Called on process termination / onDestroy.
     */
    fun release() {
        if (released.compareAndSet(false, true)) {
            synchronized(lock) {
                activeOwnerToken = null
                runOnMain {
                    _player?.let { player ->
                        runCatching { player.stop() }
                        runCatching { player.clearMediaItems() }
                        runCatching { player.release() }
                    }
                    _player = null
                }
            }
        }
    }

    private fun createPlayer(): ExoPlayer {
        Log.d(TAG, "Creating shared trailer ExoPlayer instance")
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                /* minBufferMs = */ 15_000,
                /* maxBufferMs = */ 60_000,
                /* bufferForPlaybackMs = */ 2_500,
                /* bufferForPlaybackAfterRebufferMs = */ 5_000
            )
            .build()

        val trackSelector = DefaultTrackSelector(context).apply {
            setParameters(
                buildUponParameters()
                    .setMaxVideoSizeSd()
                    .clearVideoSizeConstraints()
                    .setForceHighestSupportedBitrate(true)
            )
        }

        return ExoPlayer.Builder(context)
            .setLoadControl(loadControl)
            .setTrackSelector(trackSelector)
            .setBandwidthMeter(
                DefaultBandwidthMeter.Builder(context)
                    .setInitialBitrateEstimate(25_000_000L)
                    .build()
            )
            .setVideoChangeFrameRateStrategy(C.VIDEO_CHANGE_FRAME_RATE_STRATEGY_ONLY_IF_SEAMLESS)
            .build()
            .apply {
                repeatMode = Player.REPEAT_MODE_OFF
            }
    }
}
