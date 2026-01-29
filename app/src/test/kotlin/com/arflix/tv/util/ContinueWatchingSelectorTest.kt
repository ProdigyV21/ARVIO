package com.arflix.tv.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ContinueWatchingSelectorTest {

    @Test
    fun selectNextEpisodeAfterLastWatched_returnsNextUnwatched() {
        val episodes = listOf(
            EpisodeProgressSnapshot(1, 1, completed = true),
            EpisodeProgressSnapshot(1, 2, completed = true),
            EpisodeProgressSnapshot(1, 3, completed = false)
        )
        val watched = setOf(EpisodePointer(1, 1), EpisodePointer(1, 2))
        val lastWatched = WatchedEpisodeSnapshot(1, 2, "2025-01-01T00:00:00Z")

        val next = ContinueWatchingSelector.selectNextEpisodeAfterLastWatched(
            episodes = episodes,
            watched = watched,
            lastWatched = lastWatched,
            includeSpecials = true
        )

        assertEquals(EpisodePointer(1, 3), next)
    }

    @Test
    fun selectNextEpisodeAfterLastWatched_skipsSpecialsByDefault() {
        val episodes = listOf(
            EpisodeProgressSnapshot(0, 1, completed = false),
            EpisodeProgressSnapshot(1, 1, completed = false)
        )

        val next = ContinueWatchingSelector.selectNextEpisodeAfterLastWatched(
            episodes = episodes,
            watched = emptySet(),
            lastWatched = null,
            includeSpecials = false
        )

        assertEquals(EpisodePointer(1, 1), next)
    }

    @Test
    fun selectNextEpisodeAfterLastWatched_fallsBackWhenLastNotFound() {
        val episodes = listOf(
            EpisodeProgressSnapshot(1, 1, completed = false),
            EpisodeProgressSnapshot(1, 2, completed = false)
        )
        val lastWatched = WatchedEpisodeSnapshot(2, 1, "2025-01-01T00:00:00Z")

        val next = ContinueWatchingSelector.selectNextEpisodeAfterLastWatched(
            episodes = episodes,
            watched = emptySet(),
            lastWatched = lastWatched,
            includeSpecials = true
        )

        assertEquals(EpisodePointer(1, 1), next)
    }

    @Test
    fun selectInProgressEpisode_prefersMostRecent() {
        val inProgress = listOf(
            InProgressSnapshot(1, 1, progress = 0.4f, updatedAt = "2025-01-01T00:00:00Z"),
            InProgressSnapshot(1, 2, progress = 0.5f, updatedAt = "2025-01-02T00:00:00Z")
        )

        val next = ContinueWatchingSelector.selectInProgressEpisode(
            inProgress = inProgress,
            watched = emptySet(),
            completionThreshold = 0.9f
        )

        assertEquals(EpisodePointer(1, 2), next)
    }

    @Test
    fun selectInProgressEpisode_ignoresCompleted() {
        val inProgress = listOf(
            InProgressSnapshot(1, 1, progress = 0.95f, updatedAt = "2025-01-01T00:00:00Z")
        )

        val next = ContinueWatchingSelector.selectInProgressEpisode(
            inProgress = inProgress,
            watched = emptySet(),
            completionThreshold = 0.9f
        )

        assertNull(next)
    }
}
