package com.arflix.tv.ui.screens.player.preview

/** Small, ordered windows; never scan the movie or invent a timestamp for a cached image. */
internal fun seekPreviewWindow(positionMs: Long, durationMs: Long): List<Long> {
    if (durationMs <= 0) return emptyList()
    val center = positionMs.coerceIn(0, durationMs - 1)
    return (-2..2).map { (center + it * 10_000L).coerceIn(0, durationMs - 1) }.distinct()
}

internal fun seekPreviewWarmTargets(positionMs: Long, durationMs: Long): List<Long> {
    if (durationMs <= 0) return emptyList()
    val anchor = positionMs.coerceIn(0, durationMs - 1) / 10_000L * 10_000L
    return listOf(10_000L, 20_000L, 30_000L, 40_000L, -10_000L)
        .map { (anchor + it).coerceIn(0, durationMs - 1) }.distinct()
}

/** Fill the two forward slots first; rewind often already has rendered frames. */
internal fun seekPreviewNeighborTargets(positionMs: Long, durationMs: Long, direction: Int = 1): List<Long> =
    if (durationMs <= 0) emptyList() else listOf(10_000L, 20_000L, -10_000L, -20_000L)
        .map { (positionMs + it * if (direction < 0) -1 else 1).coerceIn(0, durationMs - 1) }.distinct().filter { it != positionMs }

/** Re-evaluated while a job is running, not just before starting it. */
internal class SeekPreviewPlaybackGuard {
    private var healthySinceMs: Long? = null
    private var strainedUntilMs = 0L

    fun onPlaybackInterrupted() { healthySinceMs = null }

    fun onPlaybackStrain(nowMs: Long) {
        healthySinceMs = null
        strainedUntilMs = nowMs + 30_000L
    }

    fun mayWarm(
        nowMs: Long,
        playing: Boolean,
        ready: Boolean,
        bufferedMs: Long,
        browsing: Boolean,
        foreground: Boolean,
        resourceSafe: Boolean,
        minimumBufferedMs: Long = 8_000L,
    ): Boolean {
        if (!playing || !ready || bufferedMs < minimumBufferedMs.coerceAtLeast(4_000L) || browsing || !foreground || !resourceSafe || nowMs < strainedUntilMs) {
            healthySinceMs = null
            return false
        }
        val since = healthySinceMs ?: nowMs.also { healthySinceMs = it }
        return nowMs - since >= 8_000L
    }
}
