package com.arflix.tv.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PlaybackHostRulesTest {
    @Test
    fun `resolver urls are recognised by host and by playback path`() {
        assertTrue(isPerFileResolverUrl("https://aiostreams.elfhosted.com/api/v1/debrid/playback/eyJ/file.mkv"))
        assertTrue(isPerFileResolverUrl("https://aio.my-server.net/api/v1/debrid/playback/eyJ/file.mkv"))
        assertTrue(isPerFileResolverUrl("https://torrentio.strem.fun/resolve/realdebrid/key/hash/null/0/file.mkv"))
        assertTrue(isPerFileResolverUrl("https://comet.example.org/playback/abc"))
        assertFalse(isPerFileResolverUrl("https://cdn.example.com/movies/film.mkv"))
        assertFalse(isPerFileResolverUrl("not a url"))
        assertFalse(isPerFileResolverUrl(null))
    }

    @Test
    fun `a plain host is ruled out after two different sources failed`() {
        assertFalse(isPlaybackHostBad(failedSources = 1, failures = 3, successes = 0, perFileResolver = false))
        assertTrue(isPlaybackHostBad(failedSources = 2, failures = 2, successes = 0, perFileResolver = false))
        assertFalse(isPlaybackHostBad(failedSources = 2, failures = 2, successes = 2, perFileResolver = false))
    }

    @Test
    fun `a resolver host takes four different failed sources`() {
        assertFalse(isPlaybackHostBad(failedSources = 3, failures = 5, successes = 0, perFileResolver = true))
        assertTrue(isPlaybackHostBad(failedSources = 4, failures = 4, successes = 0, perFileResolver = true))
    }

    @Test
    fun `retries of one source count as one source`() {
        assertEquals(
            playbackSourceKey("https://host/file.mkv"),
            playbackSourceKey("https://host/file.mkv|User-Agent=x#t=10")
        )
    }
}
