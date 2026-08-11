package com.arflix.tv.data.repository.sync

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MdbListRepository
import com.arflix.tv.util.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MdbListRemoteProviderTest {

    @Test
    fun scrobbleStop_belowWatchedThreshold_doesNotMarkWatched() = runBlocking {
        var scrobbleCalled = false
        var markedWatchedCalled = false

        val provider = MdbListRemoteProvider(object : MdbListRepository(createMockClient(), createMockApi(), createMockStore()) {
            override suspend fun scrobble(action: String, mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?) {
                scrobbleCalled = true
                assertEquals("stop", action)
            }

            override suspend fun markMovieWatched(tmdbId: Int): Boolean {
                markedWatchedCalled = true
                return true
            }
        })

        provider.scrobbleStop(MediaType.MOVIE, 100, 50f, null, null)

        assertTrue(scrobbleCalled)
        assertFalse(markedWatchedCalled)
    }

    @Test
    fun scrobbleStop_movieAtOrAboveWatchedThreshold_marksMovieWatchedAndClearsPlayback() = runBlocking {
        var scrobbleCalled = false
        var markedMovieId = 0
        var cleared = false

        val provider = MdbListRemoteProvider(object : MdbListRepository(createMockClient(), createMockApi(), createMockStore()) {
            override suspend fun scrobble(action: String, mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?) {
                scrobbleCalled = true
            }

            override suspend fun markMovieWatched(tmdbId: Int): Boolean {
                markedMovieId = tmdbId
                return true
            }

            override suspend fun clearPlayback(mediaType: MediaType, tmdbId: Int, season: Int?, episode: Int?) {
                cleared = true
            }
        })

        provider.scrobbleStop(MediaType.MOVIE, 500, Constants.WATCHED_THRESHOLD.toFloat(), null, null)

        assertTrue(scrobbleCalled)
        assertEquals(500, markedMovieId)
        assertTrue(cleared)
    }

    @Test
    fun scrobbleStop_episodeAtOrAboveWatchedThreshold_marksEpisodeWatchedAndClearsPlayback() = runBlocking {
        var markedEpisodeShowId = 0
        var markedSeason = 0
        var markedEpisodeNum = 0
        var cleared = false

        val provider = MdbListRemoteProvider(object : MdbListRepository(createMockClient(), createMockApi(), createMockStore()) {
            override suspend fun scrobble(action: String, mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?) {}

            override suspend fun markEpisodeWatched(showTmdbId: Int, season: Int, episode: Int): Boolean {
                markedEpisodeShowId = showTmdbId
                markedSeason = season
                markedEpisodeNum = episode
                return true
            }

            override suspend fun clearPlayback(mediaType: MediaType, tmdbId: Int, season: Int?, episode: Int?) {
                cleared = true
            }
        })

        provider.scrobbleStop(MediaType.TV, 700, 95f, 2, 5)

        assertEquals(700, markedEpisodeShowId)
        assertEquals(2, markedSeason)
        assertEquals(5, markedEpisodeNum)
        assertTrue(cleared)
    }

    private companion object {
        fun createMockClient(): okhttp3.OkHttpClient = org.mockito.Mockito.mock(okhttp3.OkHttpClient::class.java)
        fun createMockApi(): com.arflix.tv.data.api.MdbListApi = org.mockito.Mockito.mock(com.arflix.tv.data.api.MdbListApi::class.java)
        fun createMockStore(): com.arflix.tv.data.repository.sync.SyncProviderStore = org.mockito.Mockito.mock(com.arflix.tv.data.repository.sync.SyncProviderStore::class.java)
    }
}
