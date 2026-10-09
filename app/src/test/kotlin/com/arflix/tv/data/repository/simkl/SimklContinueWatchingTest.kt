package com.arflix.tv.data.repository.simkl

import com.arflix.tv.data.api.*
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.google.gson.JsonParser
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SimklContinueWatchingTest {
    private val api = mockk<SimklApi>(relaxed = true)
    private val store = mockk<SyncProviderStore>(relaxed = true)
    private val tmdb = mockk<TmdbApi>(relaxed = true)
    private val sync = SimklSyncService(api, SimklAuthManager(api, store), tmdb, store)
    private val watchedAt = "2026-09-01T12:00:00Z"

    @Before fun prepare() {
        coEvery { store.getSimklAccessToken() } returns "regression-test-token"
        coEvery { api.getActivities(any(), any()) } returns SimklActivitiesResponse(all = watchedAt)
        coEvery { api.getAllItems(any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns JsonParser.parseString("{}")
        coEvery { api.getPlayback(any(), any()) } returns emptyList()
        coEvery { tmdb.getTvSeason(200, 1, any(), any()) } returns TmdbSeasonDetails(
            seasonNumber = 1, episodes = (1..4).map { TmdbEpisode(episodeNumber = it, seasonNumber = 1, name = "Episode $it", airDate = "2025-01-01") })
        coEvery { tmdb.getTvDetails(200, any(), any(), any()) } returns TmdbTvDetails(id = 200, name = "Show", seasons = listOf(TmdbTvSeason(seasonNumber = 1, episodeCount = 4)))
    }

    private fun library(status: String = "watching", next: String? = "S01E03", episodes: List<Int> = listOf(1, 2), info: String = "null") {
        val nextJson = next?.let { "\"$it\"" } ?: "null"
        coEvery { api.getAllItems(any(), any(), "shows", any(), any(), any(), any(), any(), any()) } returns JsonParser.parseString(
            """{"shows":[{"status":"$status","last_watched_at":"$watchedAt","show":{"title":"Show","ids":{"tmdb":200}},"next_to_watch":$nextJson,"next_to_watch_info":$info,"seasons":[{"number":1,"episodes":[${episodes.joinToString { """{"number":$it,"watched_at":"$watchedAt"}""" }}]}]}]}""")
    }

    private fun pause(episode: Int = 2, at: String = "2026-08-31T12:00:00Z") {
        coEvery { api.getPlayback(any(), any()) } returns listOf(SimklPlaybackItem(
            progress = 40f, pausedAt = at, show = SimklShowRef(ids = SimklIds(tmdb = 200)),
            episode = SimklPlaybackEpisode(season = 1, number = episode)))
    }

    @Test fun watchedPausedEpisodeMustNotOverwriteUpNext() = runBlocking {
        library(); pause()
        val item = sync.getContinueWatching(true).single()
        assertEquals(3, item.episode)
        assertTrue(item.isUpNext)
    }

    @Test fun bulkCompletedShowMustNotReturnOldPausedEpisode() = runBlocking {
        library(status = "completed", next = null, episodes = emptyList()); pause()
        assertTrue(sync.getContinueWatching(true).isEmpty())
    }

    @Test fun caughtUpPointerWinsOverLeftoverNextEpisodeInfo() = runBlocking {
        library(next = null, info = """{"season":1,"episode":3}""")
        assertTrue(sync.getContinueWatching(true).isEmpty())
    }

    @Test fun nonExistentNextEpisodeIsNeverOffered() = runBlocking {
        library(next = "S01E05", episodes = listOf(1, 2, 3, 4))
        assertTrue(sync.getContinueWatching(true).isEmpty())
    }

    @Test fun remoteWatchedPointerAdvancesUsingRealEpisodes() = runBlocking {
        library(next = "S01E02")
        assertEquals(3, sync.getContinueWatching(true).single().episode)
    }

    @Test fun localWatchedPointerAdvancesUsingRealEpisodes() = runBlocking {
        library()
        val item = sync.getContinueWatching(true, emptySet(), setOf("show_tmdb:200:1:3")).single()
        assertEquals(4, item.episode)
    }

    @Test fun watchedFinalEpisodeAdvancesToActualNextSeason() = runBlocking {
        library(next = "S01E04", episodes = listOf(1, 2, 3, 4))
        coEvery { tmdb.getTvDetails(200, any(), any(), any()) } returns TmdbTvDetails(id = 200, name = "Show",
            seasons = listOf(TmdbTvSeason(seasonNumber = 1, episodeCount = 4), TmdbTvSeason(seasonNumber = 2, episodeCount = 1)))
        coEvery { tmdb.getTvSeason(200, 2, any(), any()) } returns TmdbSeasonDetails(seasonNumber = 2,
            episodes = listOf(TmdbEpisode(seasonNumber = 2, episodeNumber = 1, airDate = "2025-01-01")))
        val item = sync.getContinueWatching(true).single()
        assertEquals(2, item.season)
        assertEquals(1, item.episode)
    }

    @Test fun watchedFinalEpisodeDoesNotInventAnAdditionalEpisode() = runBlocking {
        library(next = "S01E04", episodes = listOf(1, 2, 3, 4))
        assertTrue(sync.getContinueWatching(true).isEmpty())
    }

    @Test fun caughtUpShowDoesNotRestoreSkippedOlderEpisodes() = runBlocking {
        library(next = null, episodes = listOf(4))
        assertTrue(sync.getContinueWatching(true).isEmpty())
    }

    @Test fun confirmedLaterRewatchRemainsResumable() = runBlocking {
        library(); pause(at = "2026-09-02T12:00:00Z")
        val item = sync.getContinueWatching(true).single()
        assertEquals(2, item.episode)
        assertFalse(item.isUpNext)
    }

    @Test fun unknownWatchDateDoesNotTurnStalePauseIntoRewatch() = runBlocking {
        coEvery { api.getAllItems(any(), any(), "shows", any(), any(), any(), any(), any(), any()) } returns JsonParser.parseString(
            """{"shows":[{"status":"watching","show":{"ids":{"tmdb":200}},"next_to_watch":"S01E03","seasons":[{"number":1,"episodes":[{"number":2,"watched_at":"1970-01-01T00:00:01Z"}]}]}]}""")
        pause()
        assertEquals(3, sync.getContinueWatching(true).single().episode)
    }

    @Test fun staleNonExistentPausedEpisodeDoesNotHideNextEpisode() = runBlocking {
        library(); pause(episode = 99)
        assertEquals(3, sync.getContinueWatching(true).single().episode)
    }

    @Test fun localMarkWatchedSurvivesDelayedProviderAcknowledgement() = runBlocking {
        library(next = "S01E02", episodes = listOf(1)); pause()
        assertEquals(2, sync.getContinueWatching(true).single().episode)
        coEvery { api.addToHistory(any(), any(), any()) } returns retrofit2.Response.success(mockk<okhttp3.ResponseBody>())
        assertTrue(sync.markWatched(com.arflix.tv.data.model.MediaType.TV, 200, 1, 2))
        val item = sync.getContinueWatching(true).single()
        assertEquals(3, item.episode)
        assertTrue(item.isUpNext)
    }

    @Test fun futureNextEpisodeIsNotOffered() = runBlocking {
        library()
        coEvery { tmdb.getTvSeason(200, 1, any(), any()) } returns TmdbSeasonDetails(seasonNumber = 1,
            episodes = listOf(TmdbEpisode(seasonNumber = 1, episodeNumber = 3, airDate = "2099-01-01")))
        assertTrue(sync.getContinueWatching(true).isEmpty())
    }

    @Test fun localWholeShowCompletionDoesNotKeepStalePlayback(): Unit = runBlocking {
        library(); pause(episode = 3)
        sync.getContinueWatching(true)
        coEvery { api.addToHistory(any(), any(), any()) } returns retrofit2.Response.success(mockk<okhttp3.ResponseBody>())
        assertTrue(sync.markWatched(com.arflix.tv.data.model.MediaType.TV, 200))
        assertTrue(sync.getContinueWatching(true).isEmpty())
    }

    @Test fun seasonCompletionAdvancesEvenBeforeSimklAcknowledgesIt(): Unit = runBlocking {
        library(next = "S01E02", episodes = listOf(1)); pause()
        sync.getContinueWatching(true)
        coEvery { api.addToHistory(any(), any(), any()) } returns retrofit2.Response.success(mockk<okhttp3.ResponseBody>())
        assertTrue(sync.markSeasonWatched(200, 1, listOf(2, 3), true))
        assertEquals(4, sync.getContinueWatching(false).single().episode)
    }

    @Test fun metadataOutageIsReportedInsteadOfBecomingAnEmptySnapshot(): Unit = runBlocking {
        library()
        coEvery { tmdb.getTvSeason(200, 1, any(), any()) } throws java.io.IOException("offline")
        try { sync.getContinueWatching(true); fail("Expected failed lookup") }
        catch (_: java.io.IOException) { }
    }

    @Test fun animeNextPointerUsesMappedSeasonInsteadOfInventingSeasonOne() = runBlocking {
        coEvery { api.getAllItems(any(), any(), "anime", any(), any(), any(), any(), any(), any()) } returns JsonParser.parseString(
            """{"anime":[{"status":"watching","show":{"ids":{"tmdb":200}},"mapped_tvdb_seasons":[2],"next_to_watch":"E02","next_to_watch_info":{"episode":2},"seasons":[{"number":1,"episodes":[{"number":1,"tvdb":{"season":2,"episode":1}}]}]}]}""")
        coEvery { tmdb.getTvSeason(200, 2, any(), any()) } returns TmdbSeasonDetails(seasonNumber = 2,
            episodes = (1..2).map { TmdbEpisode(seasonNumber = 2, episodeNumber = it, airDate = "2025-01-01") })
        val item = sync.getContinueWatching(true).single()
        assertEquals(2, item.season)
        assertEquals(2, item.episode)
    }

    @Test fun incompleteEmptySnapshotStaysAFailureDuringRetryBackoff(): Unit = runBlocking {
        coEvery { api.getAllItems(any(), any(), "shows", any(), any(), any(), any(), any(), any()) } throws java.io.IOException("offline")
        for (force in listOf(true, false)) {
            try { sync.getContinueWatching(force); fail("An incomplete snapshot cannot clear saved progress") }
            catch (_: IllegalStateException) { }
        }
    }
}
