package com.arflix.tv.data.repository

import com.arflix.tv.data.model.MediaType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ContinueWatchingMergeTest {
    private val remote = ContinueWatchingItem(
        id = 10, title = "Tracker show", mediaType = MediaType.TV, progress = 0,
        season = 2, episode = 3, isUpNext = true, updatedAtMs = 100
    )
    private val vod = ContinueWatchingItem(
        id = 20, title = "IPTV movie", mediaType = MediaType.MOVIE, progress = 15,
        resumePositionSeconds = 900, durationSeconds = 6_000,
        streamAddonId = "iptv_xtream_vod", streamKey = "vod-source",
        streamTitle = "IPTV VOD", posterPath = "https://example.test/poster.jpg",
        updatedAtMs = 200
    )

    @Test
    fun trackerRefreshDoesNotRemoveSavedIptvMovie() {
        val refreshed = ContinueWatchingMerge.merge(listOf(remote), listOf(vod))
        assertEquals(listOf(20, 10), refreshed.map { it.id })
        assertEquals(vod, refreshed.first())
        assertEquals(refreshed, ContinueWatchingMerge.merge(listOf(remote), listOf(vod)))
    }

    @Test
    fun emptyTrackerResponseKeepsPersistedIptvProgressAfterRestart() {
        assertEquals(listOf(vod), ContinueWatchingMerge.merge(emptyList(), listOf(vod)))
    }

    @Test
    fun partialHistoryDoesNotHideOtherPersistedIptvMovies() {
        assertEquals(listOf(20, 10), ContinueWatchingMerge.merge(listOf(remote), listOf(vod), listOf(remote)).map { it.id })
    }

    @Test
    fun matchingRemoteMovieIsNotDuplicatedAndKeepsItsSource() {
        val remoteMovie = vod.copy(progress = 10, resumePositionSeconds = 0, streamAddonId = null, streamKey = null)
        val merged = ContinueWatchingMerge.merge(listOf(remoteMovie), listOf(vod)).single()
        assertEquals(900L, merged.resumePositionSeconds)
        assertEquals("iptv_xtream_vod", merged.streamAddonId)
        assertEquals("vod-source", merged.streamKey)
    }

    @Test
    fun oldIptvEpisodeDoesNotOverrideTrackersNextEpisode() {
        val previous = vod.copy(id = remote.id, mediaType = MediaType.TV, season = 2, episode = 2)
        assertEquals(listOf(remote), ContinueWatchingMerge.merge(listOf(remote), listOf(previous)))
    }

    @Test
    fun unrelatedLocalHistoryIsNotImportedIntoSelectedTrackerList() {
        val addonMovie = vod.copy(streamAddonId = "torrentio")
        assertEquals(listOf(remote), ContinueWatchingMerge.merge(listOf(remote), listOf(addonMovie)))
    }

    @Test
    fun completedUnstartedAndLiveItemsAreNotAdded() {
        val items = listOf(
            vod.copy(progress = 90),
            vod.copy(id = 21, progress = 0, resumePositionSeconds = 0),
            vod.copy(id = 22, streamAddonId = "iptv_live"),
            vod.copy(id = -23),
            vod.copy(id = 24, progress = 1, resumePositionSeconds = 20),
            vod.copy(id = 25, progress = 0, resumePositionSeconds = 5_900)
        )
        assertEquals(listOf(remote), ContinueWatchingMerge.merge(listOf(remote), items))
    }

    @Test
    fun meaningfulProgressOnLongMovieStillAppearsBelowThreePercent() {
        val longMovie = vod.copy(progress = 1, resumePositionSeconds = 90, durationSeconds = 10_000)
        assertTrue(ContinueWatchingMerge.merge(listOf(remote), listOf(longMovie)).contains(longMovie))
    }

    @Test
    fun latestLocalEpisodeWinsWhenTrackerHasNoEntryForTheShow() {
        val first = vod.copy(mediaType = MediaType.TV, season = 1, episode = 1)
        val second = first.copy(episode = 2, updatedAtMs = 300)
        val merged = ContinueWatchingMerge.merge(listOf(remote), listOf(first), listOf(second))
        assertEquals(listOf(second, remote), merged)
    }

    @Test
    fun newerCompletionDoesNotResurrectOlderHistory() {
        val completed = vod.copy(progress = 95, updatedAtMs = 300)
        assertFalse(ContinueWatchingMerge.merge(listOf(remote), listOf(completed), listOf(vod)).any { it.id == vod.id })
    }

    @Test
    fun historyMappingKeepsSourceAndExactResumePosition() {
        val entry = WatchHistoryEntry(
            user_id = "account", profile_id = "profile-a", media_type = "movie", show_tmdb_id = vod.id,
            title = vod.title, progress = .15f, position_seconds = 900, duration_seconds = 6_000,
            stream_addon_id = vod.streamAddonId, stream_key = vod.streamKey, stream_title = vod.streamTitle,
            poster_path = vod.posterPath, updated_at = "invalid", paused_at = "2026-09-07T12:00:00Z"
        )
        val mapped = ContinueWatchingMerge.fromHistory(entry)
        assertEquals(900L, mapped.resumePositionSeconds)
        assertEquals(vod.streamAddonId, mapped.streamAddonId)
        assertEquals(vod.streamKey, mapped.streamKey)
        assertEquals(vod.streamTitle, mapped.streamTitle)
        assertEquals(15, mapped.progress)
        assertTrue(mapped.updatedAtMs > 0)
        assertEquals(mapped, ContinueWatchingMerge.merge(listOf(remote), emptyList(), listOf(mapped)).first())
    }

    @Test
    fun persistedSnapshotSurvivesCachedThenFreshTrackerRefresh() {
        val gson = Gson()
        val type = object : TypeToken<List<ContinueWatchingItem>>() {}.type
        val persisted: List<ContinueWatchingItem> = gson.fromJson(gson.toJson(listOf(vod)), type)
        val cached = ContinueWatchingMerge.merge(listOf(remote), persisted)
        val fresh = ContinueWatchingMerge.merge(listOf(remote.copy(title = "Refreshed show")), persisted)
        assertEquals(vod, cached.first())
        assertEquals(vod, fresh.first())
        assertEquals(2, fresh.size)
        // A refresh for another profile gets only that profile's supplied snapshot.
        assertEquals(listOf(remote), ContinueWatchingMerge.merge(listOf(remote), emptyList()))
    }

    @Test
    fun newerHistoryWithoutSourceMetadataRetainsSavedVodOrigin() {
        val history = vod.copy(streamAddonId = null, streamKey = null, streamTitle = null, updatedAtMs = 300)
        val merged = ContinueWatchingMerge.merge(listOf(remote), listOf(vod), listOf(history)).first()
        assertEquals(vod.streamAddonId, merged.streamAddonId)
        assertEquals(300L, merged.updatedAtMs)
    }
}
