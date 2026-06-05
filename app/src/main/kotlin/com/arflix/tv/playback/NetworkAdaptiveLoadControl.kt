package com.arflix.tv.playback

import androidx.media3.common.C
import androidx.media3.common.Timeline
import androidx.media3.exoplayer.LoadControl
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.source.MediaPeriodId
import androidx.media3.exoplayer.source.TrackGroupArray
import androidx.media3.exoplayer.trackselection.ExoTrackSelection
import androidx.media3.exoplayer.upstream.Allocator
import androidx.media3.exoplayer.upstream.DefaultAllocator

class NetworkAdaptiveLoadControl(
    private val minBufferMs: Int = 15_000,
    private var maxBufferMs: Int = 50_000,
    private val bufferForPlaybackMs: Int = 1_500,
    private val bufferForPlaybackAfterRebufferMs: Int = 3_000,
    private val targetBufferBytes: Int = C.LENGTH_UNSET
) : LoadControl {

    private val allocator = DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE)
    private var targetBufferSize = 0
    private var isBuffering = false

    override fun onPrepared() {
        targetBufferSize = 0
        isBuffering = false
    }

    override fun onTracksSelected(
        timeline: Timeline,
        mediaPeriodId: MediaPeriodId,
        renderers: Array<Renderer>,
        trackGroups: TrackGroupArray,
        trackSelections: Array<ExoTrackSelection>
    ) {
        // Calculate target buffer size
        targetBufferSize = if (targetBufferBytes == C.LENGTH_UNSET) {
            calculateTargetBufferSize(renderers, trackSelections)
        } else {
            targetBufferBytes
        }
        allocator.setTargetBufferSize(targetBufferSize)
    }

    override fun onStopped() {
        targetBufferSize = 0
        isBuffering = false
    }

    override fun onReleased() {
        targetBufferSize = 0
        isBuffering = false
    }

    override fun getAllocator(): Allocator = allocator

    override fun getBackBufferDurationUs(): Long = 0

    override fun retainBackBufferFromKeyframe(): Boolean = false

    override fun shouldContinueLoading(
        playbackPositionUs: Long,
        bufferedDurationUs: Long,
        playbackSpeed: Float
    ): Boolean {
        val targetBufferSizeReached = allocator.totalBytesAllocated >= targetBufferSize
        val bufferedMs = bufferedDurationUs / 1000

        if (bufferedMs < minBufferMs) {
            isBuffering = true
        } else if (bufferedMs >= maxBufferMs || targetBufferSizeReached) {
            isBuffering = false
        }

        return isBuffering
    }

    override fun shouldStartPlayback(
        timeline: Timeline,
        mediaPeriodId: MediaPeriodId,
        bufferedDurationUs: Long,
        playbackSpeed: Float,
        rebufferring: Boolean,
        targetLiveOffsetUs: Long
    ): Boolean {
        val bufferedMs = bufferedDurationUs / 1000
        val minBuffer = if (rebufferring) bufferForPlaybackAfterRebufferMs else bufferForPlaybackMs
        return bufferedMs >= (minBuffer * playbackSpeed).toLong()
    }

    private fun calculateTargetBufferSize(
        renderers: Array<Renderer>,
        trackSelections: Array<ExoTrackSelection>
    ): Int {
        var targetBufferSize = 0
        for (i in renderers.indices) {
            if (trackSelections[i] != null) {
                targetBufferSize += C.DEFAULT_VIDEO_BUFFER_SIZE // simplify
            }
        }
        return Math.max(C.DEFAULT_BUFFER_SEGMENT_SIZE, targetBufferSize)
    }

    fun increaseBufferDynamically() {
        // Increase max buffer dynamically when network degrades
        maxBufferMs = (maxBufferMs * 1.5).toInt().coerceAtMost(120_000)
    }
    
    fun resetBuffer() {
        maxBufferMs = 50_000
    }
}
