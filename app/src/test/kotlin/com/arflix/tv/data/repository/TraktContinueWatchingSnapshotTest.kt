package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TraktIds
import com.arflix.tv.data.api.TraktShowInfo
import com.arflix.tv.data.api.TraktWatchedEpisode
import com.arflix.tv.data.api.TraktWatchedSeason
import com.arflix.tv.data.api.TraktWatchedShow
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class TraktContinueWatchingSnapshotTest {
    private fun show(id: Int?, seasons: List<TraktWatchedSeason>?) = TraktWatchedShow(
        plays = 1, lastWatchedAt = null, lastUpdatedAt = null,
        show = TraktShowInfo("Show", 2026, TraktIds(tmdb = id)), seasons = seasons
    )

    @Test
    fun `fresh history rejects watched pause but leaves next episode available`() {
        val keys = traktWatchedEpisodeKeys(listOf(show(42, listOf(
            TraktWatchedSeason(3, listOf(TraktWatchedEpisode(10, 1, null)))
        ))))
        assertTrue("show_tmdb:42:3:10" in keys)
        assertFalse("show_tmdb:42:3:11" in keys)
        assertFalse("show_tmdb:43:3:10" in keys)
    }

    @Test
    fun `unwatched and missing metadata do not invent watched episodes`() {
        val keys = traktWatchedEpisodeKeys(listOf(
            show(42, listOf(TraktWatchedSeason(1, listOf(TraktWatchedEpisode(1, 0, null))))),
            show(43, null),
            show(null, listOf(TraktWatchedSeason(1, listOf(TraktWatchedEpisode(1, 1, null)))))
        ))
        assertTrue(keys.isEmpty())
    }

    @Test
    fun `fresh empty history replaces previously watched state`() {
        assertTrue(traktWatchedEpisodeKeys(emptyList()).isEmpty())
    }

    @Test
    fun `request failure does not cancel sibling snapshot requests`() = runBlocking {
        val failed = async { traktSnapshotRead<List<String>> { error("network") } }
        val successful = async { traktSnapshotRead { listOf("next episode") } }
        assertTrue(failed.await().isFailure)
        assertEquals(listOf("next episode"), successful.await().getOrThrow())
    }

    @Test
    fun `cancellation is never treated as a recoverable failure`() = runBlocking {
        try {
            traktSnapshotRead<Unit> { throw CancellationException("profile changed") }
            fail("Cancellation must propagate")
        } catch (expected: CancellationException) {
            assertEquals("profile changed", expected.message)
        }
    }
}
