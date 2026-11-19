package com.arflix.tv.ui.components

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.media3.common.Player
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter
import com.arflix.tv.util.playbackMemoryBudget
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** One preview owner at a time; stale disposals cannot stop a newer trailer. */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
object TrailerPlaybackPool {
    private val handler = Handler(Looper.getMainLooper())
    private var player: ExoPlayer? = null
    private var owner: Lease? = null
    private var fullPlayers = 0
    private var idleRelease: Runnable? = null
    private val mutableAvailability = MutableStateFlow(0L)
    val availability = mutableAvailability.asStateFlow()

    class Lease internal constructor(val player: ExoPlayer, internal val revoked: () -> Unit) {
        val isOwner: Boolean get() = owner === this
        @MainThread fun close() {
            if (!isOwner) return
            owner = null
            revoked()
            player.pause()
            player.stop()
            player.clearMediaItems()
            player.clearVideoSurface()
            scheduleIdleRelease()
        }
    }

    @MainThread
    fun acquire(context: Context, onRevoked: () -> Unit): Lease? {
        check(Looper.myLooper() == Looper.getMainLooper())
        if (fullPlayers > 0) return null
        cancelIdleRelease()
        val previous = owner
        owner = null
        previous?.revoked?.invoke()
        val shared = player ?: create(context.applicationContext).also { player = it }
        shared.pause()
        shared.stop()
        shared.clearMediaItems()
        shared.clearVideoSurface()
        return Lease(shared, onRevoked).also { owner = it }
    }

    @MainThread
    fun fullPlayback(): AutoCloseable {
        fullPlayers++
        releasePlayer()
        var closed = false
        return AutoCloseable {
            if (!closed) {
                closed = true
                fullPlayers--
                if (fullPlayers == 0) mutableAvailability.value++
            }
        }
    }

    @MainThread
    fun releaseIdle() {
        if (owner == null) releasePlayer()
    }

    @MainThread
    fun background() {
        releasePlayer()
        mutableAvailability.value++
    }

    private fun cancelIdleRelease() {
        idleRelease?.let(handler::removeCallbacks)
        idleRelease = null
    }

    private fun scheduleIdleRelease() {
        cancelIdleRelease()
        idleRelease = Runnable { if (owner == null) releasePlayer() }.also { handler.postDelayed(it, 30_000L) }
    }

    private fun releasePlayer() {
        cancelIdleRelease()
        val previous = owner
        owner = null
        previous?.revoked?.invoke()
        player?.release()
        player = null
    }

    private fun create(context: Context): ExoPlayer = ExoPlayer.Builder(context)
        .setLoadControl(DefaultLoadControl.Builder()
            .setBufferDurationsMs(10_000, 30_000, 1_000, 2_000)
            .setTargetBufferBytes(playbackMemoryBudget(context).trailerBytes)
            .setPrioritizeTimeOverSizeThresholds(false)
            .build())
        .setBandwidthMeter(DefaultBandwidthMeter.Builder(context).setInitialBitrateEstimate(8_000_000L).build())
        .build().apply {
            trackSelectionParameters = trackSelectionParameters.forFullHdTrailer()
            repeatMode = Player.REPEAT_MODE_OFF
        }
}
