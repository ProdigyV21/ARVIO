package com.arflix.tv.data.repository

import com.arflix.tv.data.model.MediaType
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class WatchHistoryRepositoryTest {

    @Test
    fun `watch history is isolated per active profile`() = runTest {
        var activeProfileId = "profile-a"
        val repository = watchHistoryRepository { activeProfileId }

        repository.saveProgress(
            mediaType = MediaType.MOVIE,
            tmdbId = 10,
            title = "Movie",
            poster = null,
            backdrop = null,
            season = null,
            episode = null,
            episodeTitle = null,
            progress = 50f,
            duration = 7_200L,
            position = 3_600L
        )
        activeProfileId = "profile-b"
        repository.saveProgress(
            mediaType = MediaType.TV,
            tmdbId = 10,
            title = "Show",
            poster = null,
            backdrop = null,
            season = 1,
            episode = 2,
            episodeTitle = "Episode",
            progress = 25f,
            duration = 2_400L,
            position = 600L
        )

        activeProfileId = "profile-a"
        val movieProgress = repository.getProgress(MediaType.MOVIE, 10, season = null, episode = null)
        assertNotNull(movieProgress)
        assertEquals(0.5f, movieProgress?.progress ?: 0f, 0.001f)
        assertNull(repository.getProgress(MediaType.TV, 10, season = 1, episode = 2))

        activeProfileId = "profile-b"
        assertNull(repository.getProgress(MediaType.MOVIE, 10, season = null, episode = null))
        assertNotNull(repository.getProgress(MediaType.TV, 10, season = 1, episode = 2))
    }

    @Test
    fun `continue watching keeps in-progress entries and drops watched entries`() = runTest {
        val repository = watchHistoryRepository { "profile-a" }
        repository.saveProgress(
            mediaType = MediaType.MOVIE,
            tmdbId = 20,
            title = "Almost Done",
            poster = null,
            backdrop = null,
            season = null,
            episode = null,
            episodeTitle = null,
            progress = 91f,
            duration = 7_200L,
            position = 6_552L
        )
        repository.saveProgress(
            mediaType = MediaType.MOVIE,
            tmdbId = 21,
            title = "In Progress",
            poster = null,
            backdrop = null,
            season = null,
            episode = null,
            episodeTitle = null,
            progress = 0f,
            duration = 7_200_000L,
            position = 1_800_000L
        )

        val continueWatching = repository.getContinueWatching()

        assertEquals(listOf(21), continueWatching.map { it.show_tmdb_id })
    }

    private fun watchHistoryRepository(activeProfileId: () -> String): WatchHistoryRepository {
        val profileManager = mockk<ProfileManager>()
        every { profileManager.getProfileIdSync() } answers { activeProfileId() }
        return WatchHistoryRepository(profileManager)
    }
}
