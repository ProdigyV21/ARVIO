package com.arflix.tv.ui.screens.player

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecentSourcePlayTrackerTest {

    @Test
    fun reachesTheThresholdAfterTenSecondsOfPlaying() {
        val tracker = RecentSourcePlayTracker()

        // Progress is reported about every 2 s here; starting deep into the title counts from zero.
        val reached = playSteadily(tracker, startPositionMs = 2_400_000L, seconds = 10)

        assertThat(reached).containsExactly(10_000L)
    }

    @Test
    fun aSourceAbandonedBeforeTenSecondsIsNeverReported() {
        val tracker = RecentSourcePlayTracker()

        val reached = playSteadily(tracker, startPositionMs = 0L, seconds = 8)

        assertThat(reached).isEmpty()
    }

    @Test
    fun anotherSelectionStartsOver() {
        val tracker = RecentSourcePlayTracker()
        var now = 0L
        var position = 0L
        repeat(4) {
            tracker.onProgress(selectionId = 1, positionMs = position, isPlaying = true, nowMs = now)
            now += 2_000L
            position += 2_000L
        }

        // Failover or the user picks another source: its 10 s count from its own start.
        val reachedAt = mutableListOf<Int>()
        repeat(8) { tick ->
            if (tracker.onProgress(selectionId = 2, positionMs = position, isPlaying = true, nowMs = now)) {
                reachedAt += tick
            }
            now += 2_000L
            position += 2_000L
        }

        assertThat(reachedAt).containsExactly(5)
    }

    @Test
    fun pausedTimeDoesNotCount() {
        val tracker = RecentSourcePlayTracker()
        tracker.onProgress(1, positionMs = 0L, isPlaying = true, nowMs = 0L)
        tracker.onProgress(1, positionMs = 6_000L, isPlaying = true, nowMs = 6_000L)
        // Paused for an hour, then playing again.
        tracker.onProgress(1, positionMs = 6_000L, isPlaying = false, nowMs = 7_000L)
        tracker.onProgress(1, positionMs = 6_000L, isPlaying = false, nowMs = 3_600_000L)

        assertThat(tracker.onProgress(1, positionMs = 8_000L, isPlaying = true, nowMs = 3_602_000L)).isFalse()
        assertThat(tracker.onProgress(1, positionMs = 10_000L, isPlaying = true, nowMs = 3_604_000L)).isTrue()
    }

    @Test
    fun seekingForwardDoesNotCountAsPlaying() {
        val tracker = RecentSourcePlayTracker()
        tracker.onProgress(1, positionMs = 0L, isPlaying = true, nowMs = 0L)

        // A 20-minute skip two seconds later is two seconds played, not twenty minutes.
        assertThat(tracker.onProgress(1, positionMs = 1_200_000L, isPlaying = true, nowMs = 2_000L)).isFalse()
        assertThat(tracker.onProgress(1, positionMs = 1_206_000L, isPlaying = true, nowMs = 8_000L)).isFalse()
        assertThat(tracker.onProgress(1, positionMs = 1_208_000L, isPlaying = true, nowMs = 10_000L)).isTrue()
    }

    @Test
    fun seekingBackDoesNotCount() {
        val tracker = RecentSourcePlayTracker()
        tracker.onProgress(1, positionMs = 600_000L, isPlaying = true, nowMs = 0L)

        assertThat(tracker.onProgress(1, positionMs = 10_000L, isPlaying = true, nowMs = 40_000L)).isFalse()
    }

    @Test
    fun reportsOncePerSelection() {
        val tracker = RecentSourcePlayTracker()

        val reached = playSteadily(tracker, startPositionMs = 0L, seconds = 60)

        assertThat(reached).hasSize(1)
    }

    /** Plays [seconds] at 1x with a progress report every 2 s; returns the played times that reported. */
    private fun playSteadily(tracker: RecentSourcePlayTracker, startPositionMs: Long, seconds: Int): List<Long> {
        val reached = mutableListOf<Long>()
        var elapsed = 0L
        while (elapsed <= seconds * 1_000L) {
            if (tracker.onProgress(1, startPositionMs + elapsed, isPlaying = true, nowMs = elapsed)) {
                reached += elapsed
            }
            elapsed += 2_000L
        }
        return reached
    }
}
