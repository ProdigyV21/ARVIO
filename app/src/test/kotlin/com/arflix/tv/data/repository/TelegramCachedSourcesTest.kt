package com.arflix.tv.data.repository

import com.arflix.tv.data.model.StreamSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

/**
 * Reopening Sources after a click-search: the saved source list holds only addon results, the
 * Telegram files live in the Telegram lookup's cache, and every saved-list return path merges them
 * back (resolveMovieStreamsProgressive / resolveEpisodeStreamsProgressive).
 */
class TelegramCachedSourcesTest {

    private fun addon(name: String) = StreamSource(
        source = name, addonName = "Torrentio", addonId = "torrentio", quality = "1080p", size = "2 GB",
        url = "https://example.com/$name"
    )

    private fun telegram(name: String) = StreamSource(
        source = name, addonName = "Telegram", addonId = "telegram_native", quality = "1080p", size = "2 GB",
        url = "http://localhost:1234/file/${name.hashCode()}"
    )

    @Test
    fun `a saved addon-only list gets the click-search results back`() {
        val saved = listOf(addon("a.mkv"), addon("b.mkv"))
        val found = listOf(telegram("Show.S01E04.mkv"))
        assertEquals(saved + found, withCachedTelegramSources(saved, found))
    }

    @Test
    fun `with Telegram as the only provider the list is the click-search results`() {
        val found = listOf(telegram("Show.S01E04.mkv"), telegram("Show.S01E04.720p.mkv"))
        assertEquals(found, withCachedTelegramSources(emptyList(), found))
    }

    @Test
    fun `a list that already has the Telegram files does not list them twice`() {
        val found = listOf(telegram("Show.S01E04.mkv"))
        val saved = listOf(addon("a.mkv")) + found
        assertEquals(saved, withCachedTelegramSources(saved, found))
    }

    @Test
    fun `nothing cached leaves the saved list untouched`() {
        val saved = listOf(addon("a.mkv"))
        assertSame(saved, withCachedTelegramSources(saved, emptyList()))
    }
}
