package com.arflix.tv.util

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RuntimeApiKeysTest {

    @After
    fun tearDown() {
        RuntimeApiKeys.clearTmdbApiKey()
        RuntimeApiKeys.clearWatchmodeApiKey()
    }

    @Test
    fun `manual TMDB key overrides build-time key`() {
        RuntimeApiKeys.setTmdbApiKey("manual-key")

        assertEquals("manual-key", RuntimeApiKeys.resolveTmdbApiKey("build-key"))
    }

    @Test
    fun `blank and placeholder TMDB values resolve to empty`() {
        RuntimeApiKeys.setTmdbApiKey("   ")

        assertEquals("", RuntimeApiKeys.resolveTmdbApiKey("your-tmdb-api-key"))
        assertFalse(RuntimeApiKeys.hasTmdbApiKey("your-tmdb-api-key"))
    }

    @Test
    fun `clearing manual TMDB key falls back to build-time key`() {
        RuntimeApiKeys.setTmdbApiKey("manual-key")
        RuntimeApiKeys.clearTmdbApiKey()

        assertEquals("build-key", RuntimeApiKeys.resolveTmdbApiKey("build-key"))
        assertTrue(RuntimeApiKeys.hasTmdbApiKey("build-key"))
    }

    @Test
    fun `manual Watchmode key overrides build-time key`() {
        RuntimeApiKeys.setWatchmodeApiKey("manual-watchmode-key")

        assertEquals("manual-watchmode-key", RuntimeApiKeys.resolveWatchmodeApiKey("build-key"))
    }

    @Test
    fun `blank and placeholder Watchmode values resolve to empty`() {
        RuntimeApiKeys.setWatchmodeApiKey("   ")

        assertEquals("", RuntimeApiKeys.resolveWatchmodeApiKey("your-watchmode-api-key"))
        assertFalse(RuntimeApiKeys.hasWatchmodeApiKey("your-watchmode-api-key"))
    }
}
