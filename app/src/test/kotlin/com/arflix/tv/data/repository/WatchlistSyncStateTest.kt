package com.arflix.tv.data.repository

import org.junit.Assert.*
import org.junit.Test

class WatchlistSyncStateTest {
    private val movie = LocalWatchlistItem(123, "movie", "Movie", addedAt = 100)
    private val episode = LocalWatchlistItem(123, "tv", "Show", addedAt = 101)
    private fun removed(time: Long = 200) = WatchlistSyncState(changes = mapOf("movie:123" to WatchlistChange(time, true)))

    @Test fun `offline removal survives repeated stale device uploads in both directions`() {
        val stale = WatchlistSyncState(listOf(movie, episode))
        var cloud = stale
        repeat(5) {
            cloud = mergeWatchlistStates(cloud, removed())
            cloud = mergeWatchlistStates(stale, cloud)
            assertEquals(listOf(episode), cloud.items)
            assertTrue(cloud.changes.getValue("movie:123").removed)
        }
    }

    @Test fun `legacy web refreshing addedAt does not manufacture a re-add`() {
        assertTrue(mergeWatchlistStates(removed(), WatchlistSyncState(listOf(movie.copy(addedAt = 999999)))).items.isEmpty())
    }

    @Test fun `explicit newer re-add works but stale add cannot defeat removal`() {
        val add = WatchlistSyncState(listOf(movie), mapOf("movie:123" to WatchlistChange(201, false)))
        assertEquals(listOf(movie), mergeWatchlistStates(removed(), add).items)
        assertEquals(listOf(movie), mergeWatchlistStates(add, removed()).items)
        assertTrue(mergeWatchlistStates(add, removed(202)).items.isEmpty())
    }

    @Test fun `remove wins equal timestamps regardless of arrival order`() {
        val add = WatchlistSyncState(listOf(movie), mapOf("movie:123" to WatchlistChange(200, false)))
        assertTrue(mergeWatchlistStates(add, removed()).items.isEmpty())
        assertTrue(mergeWatchlistStates(removed(), add).items.isEmpty())
    }

    @Test fun `empty old snapshot does not delete offline additions`() {
        assertEquals(listOf(movie), mergeWatchlistStates(WatchlistSyncState(listOf(movie)), WatchlistSyncState()).items)
    }

    @Test fun `last item removal and stale refresh leave a genuinely empty list`() {
        val cloud = mergeWatchlistStates(WatchlistSyncState(listOf(movie)), removed())
        assertTrue(cloud.items.isEmpty())
        assertTrue(mergeWatchlistStates(cloud, WatchlistSyncState(listOf(movie))).items.isEmpty())
    }

    @Test fun `intent after observed removal is newer even with a slow clock`() {
        assertEquals(WatchlistChange(201, false), nextWatchlistChange(WatchlistChange(200, true), false, 100))
    }
}
