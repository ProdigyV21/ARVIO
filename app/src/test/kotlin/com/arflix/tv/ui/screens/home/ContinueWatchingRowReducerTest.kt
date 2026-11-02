package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.NextEpisode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ContinueWatchingRowReducerTest {
    @Test
    fun `progress update replaces card immediately and preserves enriched artwork`() {
        val existing = MediaItem(
            id = 10,
            title = "Movie",
            overview = "Overview",
            imdbRating = "8.1",
            mediaType = MediaType.MOVIE,
            image = "poster",
            progress = 12
        )
        val fresh = MediaItem(
            id = 10,
            title = "Movie",
            subtitle = "Continue from 22:00",
            mediaType = MediaType.MOVIE,
            progress = 42,
            timeRemainingMinutes = 48
        )

        val result = ContinueWatchingRowReducer.upsert(
            listOf(Category("continue_watching", "Continue Watching", listOf(existing))),
            fresh
        ).first().items.single()

        assertEquals(42, result.progress)
        assertEquals("Continue from 22:00", result.subtitle)
        assertEquals(48, result.timeRemainingMinutes)
        assertEquals("poster", result.image)
        assertEquals("8.1", result.imdbRating)
    }

    @Test
    fun `next episode update replaces the previous episode for the same show`() {
        val existing = tvItem(20, season = 1, episode = 4).copy(episodeStill = "episode-4")
        val next = tvItem(20, season = 1, episode = 5)

        val categories = ContinueWatchingRowReducer.upsert(
            listOf(Category("continue_watching", "Continue Watching", listOf(existing))),
            next
        )

        assertEquals(1, categories.first().items.size)
        assertEquals(5, categories.first().items.single().nextEpisode?.episodeNumber)
        assertNull(categories.first().items.single().episodeStill)
    }

    @Test
    fun `progress update for the same episode preserves its still`() {
        val existing = tvItem(20, season = 1, episode = 4).copy(episodeStill = "episode-4")
        val progressUpdate = tvItem(20, season = 1, episode = 4).copy(progress = 45)

        val categories = ContinueWatchingRowReducer.upsert(
            listOf(Category("continue_watching", "Continue Watching", listOf(existing))),
            progressUpdate
        )

        assertEquals("episode-4", categories.first().items.single().episodeStill)
    }

    @Test
    fun `mark watched removes the final continue watching row`() {
        val categories = ContinueWatchingRowReducer.remove(
            categories = listOf(Category("continue_watching", "Continue Watching", listOf(tvItem(20, 1, 4)))),
            mediaType = MediaType.TV,
            tmdbId = 20,
            season = 1,
            episode = 4
        )

        assertNull(categories.firstOrNull { it.id == "continue_watching" })
    }

    private fun tvItem(id: Int, season: Int, episode: Int) = MediaItem(
        id = id,
        title = "Show",
        mediaType = MediaType.TV,
        progress = 3,
        nextEpisode = NextEpisode(0, season, episode, "Episode $episode")
    )
}
