package com.arflix.tv.util

import android.app.ActivityManager
import android.content.Context

/** Shared heap headroom for artwork, previews and the mutually exclusive players. */
data class PlaybackMemoryBudget(
    val artworkBytes: Int,
    val seekPreviewBytes: Int,
    val trailerBytes: Int,
    val maxPlaybackBytes: Int,
    val lowPhysicalRam: Boolean,
)

internal fun playbackMemoryBudget(heapMb: Int, physicalRamMb: Long?, lowRam: Boolean): PlaybackMemoryBudget {
    val heap = heapMb.coerceAtLeast(64)
    val constrained = lowRam || physicalRamMb?.let { it in 1..2304 } == true
    val artwork = if (heap < 192) 12 else if (constrained) 24 else 64
    val previews = if (heap < 192) 4 else if (constrained || heap <= 256) 6 else 16
    val trailer = if (constrained || heap <= 256) 8 else 16
    val reserve = maxOf(32, heap / 4)
    val room = (heap - reserve - artwork - previews).coerceAtLeast(16)
    val playback = minOf(room, if (constrained) 96 else 384)
    return PlaybackMemoryBudget(artwork.mb(), previews.mb(), trailer.mb(), playback.mb(), constrained)
}

fun playbackMemoryBudget(context: Context): PlaybackMemoryBudget {
    val manager = kotlin.runCatching { context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager }.getOrNull()
    val physical = kotlin.runCatching {
        ActivityManager.MemoryInfo().also { manager?.getMemoryInfo(it) }.totalMem / (1024L * 1024L)
    }.getOrNull()?.takeIf { it > 0 }
    val runtimeHeap = (Runtime.getRuntime().maxMemory() / (1024L * 1024L)).toInt()
    val heap = minOf(manager?.memoryClass ?: runtimeHeap, runtimeHeap)
    return playbackMemoryBudget(heap, physical, manager?.isLowRamDevice == true)
}

private fun Int.mb(): Int = this * 1024 * 1024
