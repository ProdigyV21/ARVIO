package com.arflix.tv.playback

import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class PlaybackHealth {
    EXCELLENT, GOOD, FAIR, POOR, CRITICAL
}

data class PlaybackMetrics(
    val throughputBps: Long = 0,
    val bufferDepletionRateAvg: Float = 0f, 
    val startupLatencyMs: Long = 0,
    val droppedFrames: Int = 0,
    val health: PlaybackHealth = PlaybackHealth.EXCELLENT
)

class PlaybackMetricsAnalyzer(
    private val player: ExoPlayer,
    private val trackSelector: DefaultTrackSelector?,
    private val loadControl: NetworkAdaptiveLoadControl?,
    coroutineScope: CoroutineScope
) : AnalyticsListener {

    private val _metrics = MutableStateFlow(PlaybackMetrics())
    val metrics: StateFlow<PlaybackMetrics> = _metrics.asStateFlow()

    private var startupStartTime = 0L
    private var isStartup = true
    private var lastBufferedPosition = 0L
    private var lastCurrentPosition = 0L
    private var monitorJob: Job? = null

    init {
        player.addAnalyticsListener(this)
        monitorJob = coroutineScope.launch {
            while (true) {
                delay(1000)
                updatePeriodicMetrics()
            }
        }
    }

    override fun onBandwidthEstimate(
        eventTime: AnalyticsListener.EventTime,
        totalLoadTimeMs: Int,
        totalBytesLoaded: Long,
        bitrateEstimate: Long
    ) {
        _metrics.value = _metrics.value.copy(throughputBps = bitrateEstimate)
        evaluateHealthAndAdapt()
    }

    override fun onDroppedVideoFrames(
        eventTime: AnalyticsListener.EventTime,
        droppedFrames: Int,
        elapsedMs: Long
    ) {
        _metrics.value = _metrics.value.copy(
            droppedFrames = _metrics.value.droppedFrames + droppedFrames
        )
        evaluateHealthAndAdapt()
    }

    override fun onPlaybackStateChanged(
        eventTime: AnalyticsListener.EventTime,
        state: Int
    ) {
        if (state == Player.STATE_BUFFERING && isStartup && startupStartTime == 0L) {
            startupStartTime = System.currentTimeMillis()
        } else if (state == Player.STATE_READY && isStartup) {
            val latency = System.currentTimeMillis() - startupStartTime
            _metrics.value = _metrics.value.copy(startupLatencyMs = latency)
            isStartup = false
        }
        evaluateHealthAndAdapt()
    }

    private fun updatePeriodicMetrics() {
        if (!player.isPlaying) return

        val currentPos = player.currentPosition
        val bufferedPos = player.bufferedPosition

        val playedDelta = currentPos - lastCurrentPosition
        val bufferedDelta = bufferedPos - lastBufferedPosition

        // Calculate depletion as a ratio: how much playhead advanced vs how much buffer advanced
        val depletion = if (playedDelta > 0) {
            (playedDelta - bufferedDelta).toFloat() / playedDelta.toFloat()
        } else 0f

        val oldAvg = _metrics.value.bufferDepletionRateAvg
        val newAvg = oldAvg * 0.7f + depletion * 0.3f

        _metrics.value = _metrics.value.copy(bufferDepletionRateAvg = newAvg)

        lastCurrentPosition = currentPos
        lastBufferedPosition = bufferedPos

        evaluateHealthAndAdapt()
    }

    private fun evaluateHealthAndAdapt() {
        val curr = _metrics.value
        var newHealth = PlaybackHealth.EXCELLENT

        if (curr.droppedFrames > 30 || curr.bufferDepletionRateAvg > 0.8f) {
            newHealth = PlaybackHealth.CRITICAL
        } else if (curr.droppedFrames > 10 || curr.bufferDepletionRateAvg > 0.5f) {
            newHealth = PlaybackHealth.POOR
        } else if (curr.bufferDepletionRateAvg > 0.2f) {
            newHealth = PlaybackHealth.FAIR
        } else if (curr.bufferDepletionRateAvg > 0f) {
            newHealth = PlaybackHealth.GOOD
        }

        if (curr.health != newHealth) {
            _metrics.value = curr.copy(health = newHealth)
            adaptPlayback(newHealth)
        }
    }

    private fun adaptPlayback(health: PlaybackHealth) {
        if (trackSelector != null) {
            val parametersBuilder = trackSelector.parameters.buildUpon()
            when (health) {
                PlaybackHealth.CRITICAL, PlaybackHealth.POOR -> {
                    parametersBuilder.setMaxVideoBitrate(1_000_000) // Lower quality
                }
                PlaybackHealth.FAIR -> {
                    parametersBuilder.setMaxVideoBitrate(3_000_000)
                }
                PlaybackHealth.GOOD, PlaybackHealth.EXCELLENT -> {
                    parametersBuilder.setMaxVideoBitrate(Int.MAX_VALUE)
                }
            }
            trackSelector.parameters = parametersBuilder.build()
        }
        
        if (loadControl != null) {
            when (health) {
                PlaybackHealth.CRITICAL, PlaybackHealth.POOR -> {
                    loadControl.increaseBufferDynamically()
                }
                PlaybackHealth.GOOD, PlaybackHealth.EXCELLENT -> {
                    loadControl.resetBuffer()
                }
                else -> {}
            }
        }
    }

    fun release() {
        player.removeAnalyticsListener(this)
        monitorJob?.cancel()
    }
}
