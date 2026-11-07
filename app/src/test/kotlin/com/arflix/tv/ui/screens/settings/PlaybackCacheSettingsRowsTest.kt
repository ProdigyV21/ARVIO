package com.arflix.tv.ui.screens.settings

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PlaybackCacheSettingsRowsTest {
    @Test
    fun `cache actions do not reuse live AI subtitle actions`() {
        val playback = tvGeneralRowsForSection("playback")
        val ai = tvGeneralRowsForSection("ai_subtitles")
        assertEquals(listOf(53, 54), playback.takeLast(2))
        assertTrue(ai.containsAll(LIVE_AI_SUBS_ROWS))
        val all = listOf("language", "subtitles", "ai_subtitles", "playback", "appearance", "profiles", "network")
            .flatMap(::tvGeneralRowsForSection)
        assertEquals(all.size, all.distinct().size)
    }
}
