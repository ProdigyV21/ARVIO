package com.arflix.tv.data.repository.simkl

import android.content.Context
import com.arflix.tv.data.api.SimklActivitiesResponse
import com.arflix.tv.data.api.SimklActivityGroup
import com.arflix.tv.data.api.SimklApi
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbListResponse
import com.arflix.tv.data.api.TmdbMediaItem
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.google.gson.JsonParser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.IOException

class SimklSnapshotRegressionTest {
    @get:Rule val temporaryFolder = TemporaryFolder()
    private val api = mockk<SimklApi>(relaxed = true)
    private val store = mockk<SyncProviderStore>(relaxed = true)
    private val tmdb = mockk<TmdbApi>(relaxed = true)
    private val first = "2026-09-08T10:00:00Z"
    private val second = "2026-09-08T11:00:00Z"
    private fun json(value: String) = JsonParser.parseString(value)
    private fun service(context: Context? = null) = SimklSyncService(api, SimklAuthManager(api, store), tmdb, store, context)
    private fun diskContext(): Context = mockk<Context>().also { every { it.filesDir } returns temporaryFolder.root }

    @Before fun prepare() {
        coEvery { store.getSimklAccessToken() } returns "review-token"
        coEvery { api.getActivities(any(), any()) } returns SimklActivitiesResponse(all = first)
        coEvery { api.getAllItems(any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns json("{}")
        coEvery { api.getAllItems(any(), any(), "movies", any(), any(), any(), any(), any(), any()) } returns json(
            """{"movies":[{"status":"plantowatch","movie":{"title":"Saved movie","ids":{"simkl":1,"tmdb":101}}}]}"""
        )
        coEvery { api.getAllItemsDelta(any(), any(), any(), any(), any(), any(), any()) } returns json("{}")
        coEvery { api.getPlayback(any(), any()) } returns emptyList()
    }

    @Test fun deletionReconcilesOnlyChangedCategory() = runBlocking {
        val sync = service()
        assertTrue(sync.syncIfNeeded(force = true))
        coEvery { api.getActivities(any(), any()) } returns SimklActivitiesResponse(
            all = second, movies = SimklActivityGroup(removedFromList = second)
        )
        coEvery { api.getAllItemIds(any(), any(), "movies", any()) } returns json("[]")
        assertTrue(sync.syncIfNeeded(force = true))
        assertTrue(sync.getWatchlistItems().isEmpty())
        coVerify(exactly = 1) { api.getAllItemIds(any(), any(), "movies", "ids_only") }
        coVerify(exactly = 0) { api.getAllItemIds(any(), any(), "shows", any()) }
        coVerify(exactly = 0) { api.getAllItemIds(any(), any(), "anime", any()) }
        assertTrue(sync.syncIfNeeded(force = true))
        coVerify(exactly = 1) { api.getAllItemIds(any(), any(), any(), any()) }
    }

    @Test fun failedDeletionReconciliationKeepsCacheAndRetriesSameWatermark() = runBlocking {
        val sync = service()
        assertTrue(sync.syncIfNeeded(force = true))
        coEvery { api.getActivities(any(), any()) } returns SimklActivitiesResponse(
            all = second, movies = SimklActivityGroup(removedFromList = second)
        )
        coEvery { api.getAllItemIds(any(), any(), "movies", any()) } throws IOException("offline")
        assertFalse(sync.syncIfNeeded(force = true))
        assertEquals(listOf(101), sync.getWatchlistItems().map { it.id })
        coVerify(exactly = 0) { store.setSimklWatermark(second) }
        coEvery { api.getAllItemIds(any(), any(), "movies", any()) } returns json("{\"movies\":[]}")
        assertTrue(sync.syncIfNeeded(force = true))
        assertTrue(sync.getWatchlistItems().isEmpty())
        coVerify(exactly = 2) { api.getAllItemsDelta(any(), any(), first, any(), any(), any(), any()) }
    }

    @Test fun failedPlaybackDoesNotAcknowledgeDeltaOrPreventRetry() = runBlocking {
        val sync = service()
        assertTrue(sync.syncIfNeeded(force = true))
        coEvery { api.getActivities(any(), any()) } returns SimklActivitiesResponse(all = second)
        coEvery { api.getPlayback(any(), any()) } throws IOException("temporary playback outage")
        assertFalse(sync.syncIfNeeded(force = true))
        coVerify(exactly = 0) { store.setSimklWatermark(second) }
        coEvery { api.getPlayback(any(), any()) } returns emptyList()
        assertTrue(sync.syncIfNeeded(force = true))
        coVerify(exactly = 2) { api.getAllItemsDelta(any(), any(), first, any(), any(), any(), any()) }
        coVerify(exactly = 1) { store.setSimklWatermark(second) }
    }

    @Test fun failedBootstrapPlaybackIsRetriedEvenWithUnchangedActivities() = runBlocking {
        val sync = service()
        coEvery { api.getPlayback(any(), any()) } throws IOException("offline")
        assertFalse(sync.syncIfNeeded(force = true))
        assertEquals(listOf(101), sync.getWatchlistItems().map { it.id })
        coEvery { api.getPlayback(any(), any()) } returns emptyList()
        assertTrue(sync.syncIfNeeded(force = true))
        coVerify(exactly = 2) { api.getPlayback(any(), any()) }
    }

    @Test fun restartRestoresAccountSnapshotAndItsOwnWatermark() = runBlocking {
        val context = diskContext()
        assertTrue(service(context).syncIfNeeded(force = true))
        // A separately saved timestamp must never move this snapshot past data it actually contains.
        coEvery { store.getSimklWatermark() } returns "2026-09-08T12:00:00Z"
        val restarted = service(context)
        assertTrue(restarted.syncIfNeeded(force = true))
        assertEquals(listOf(101), restarted.getWatchlistItems().map { it.id })
        coVerify(exactly = 3) { api.getAllItems(any(), any(), any(), any(), any(), any(), any(), any(), any()) }
        coVerify(exactly = 0) { api.getAllItemsDelta(any(), any(), any(), any(), any(), any(), any()) }
        val file = File(temporaryFolder.root, "simkl_snapshot_cache.json")
        assertFalse(file.readText().contains("review-token"))
    }

    @Test fun differentAccountCannotRestorePreviousAccountSnapshot() = runBlocking {
        val context = diskContext()
        assertTrue(service(context).syncIfNeeded(force = true))
        coEvery { store.getSimklAccessToken() } returns "other-account"
        coEvery { api.getActivities(any(), any()) } throws IOException("offline")
        val otherAccount = service(context)
        assertFalse(otherAccount.syncIfNeeded(force = true))
        assertTrue(otherAccount.getWatchlistItems().isEmpty())
    }

    @Test fun restartRetainsResolvedAnimeIdsWithoutRepeatingMetadataLookups() = runBlocking {
        val context = diskContext()
        coEvery { api.getAllItems(any(), any(), "anime", any(), any(), any(), any(), any(), any()) } returns json(
            """{"anime":[{"status":"plantowatch","show":{"title":"Test Anime","year":2025,"ids":{"simkl":900}}}]}"""
        )
        coEvery { tmdb.searchTv(any(), "Test Anime", any(), any(), 2025) } returns TmdbListResponse(
            results = listOf(TmdbMediaItem(id = 901, name = "Test Anime", firstAirDate = "2025-01-10", popularity = 10f))
        )
        assertTrue(service(context).syncIfNeeded(force = true))
        val restarted = service(context)
        assertTrue(restarted.syncIfNeeded(force = true))
        assertEquals(setOf(101, 901), restarted.getWatchlistItems().map { it.id }.toSet())
        coVerify(exactly = 1) { tmdb.searchTv(any(), "Test Anime", any(), any(), 2025) }
    }

    @Test fun failedRefreshNeverPersistsAnAdvancedWatermark() = runBlocking {
        val context = diskContext()
        assertTrue(service(context).syncIfNeeded(force = true))
        val sync = service(context)
        coEvery { api.getActivities(any(), any()) } returns SimklActivitiesResponse(all = second)
        coEvery { api.getPlayback(any(), any()) } throws IOException("offline")
        assertFalse(sync.syncIfNeeded(force = true))
        coEvery { api.getPlayback(any(), any()) } returns emptyList()
        assertTrue(service(context).syncIfNeeded(force = true))
        coVerify(exactly = 2) { api.getAllItemsDelta(any(), any(), first, any(), any(), any(), any()) }
    }

    @Test fun corruptCacheFallsBackToBootstrap() = runBlocking {
        val context = diskContext()
        File(temporaryFolder.root, "simkl_snapshot_cache.json").writeText("{broken")
        assertTrue(service(context).syncIfNeeded(force = true))
        coVerify(exactly = 3) { api.getAllItems(any(), any(), any(), any(), any(), any(), any(), any(), any()) }
    }
}
