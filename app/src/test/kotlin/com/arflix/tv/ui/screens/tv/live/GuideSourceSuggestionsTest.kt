package com.arflix.tv.ui.screens.tv.live

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GuideSourceSuggestionsTest {
    @Test
    fun sameChannelUnderOtherTagsAndQualityScoresHighest() {
        assertEquals(100, GuideSourceSuggestions.score("NL: NPO 1 HD", "NPO 1"))
        assertEquals(100, GuideSourceSuggestions.score("|NL| RTL 4 FHD", "RTL 4 (backup)"))
    }

    @Test
    fun differentNumberIsNotTheSameChannel() {
        assertEquals(0, GuideSourceSuggestions.score("NPO 1", "NPO 2"))
    }

    @Test
    fun suggestionsRankCloseNamesAndPreferChannelsWithAGuide() {
        val names = listOf("NPO 2", "NPO 1 4K", "Discovery", "NPO 1", "NPO 1 Extra")
        val withGuide = setOf("NPO 1")
        val ranked = GuideSourceSuggestions.rank("NL | NPO 1 HD", "", names, { it }, { it in withGuide })
        assertEquals(listOf("NPO 1", "NPO 1 4K"), ranked.take(2))
        assertTrue("NPO 2" !in ranked)
        assertTrue("Discovery" !in ranked)
    }

    @Test
    fun searchFindsEveryNameWithAllWords() {
        val names = listOf("Sky Sport 1", "Sky Cinema", "Ziggo Sport", "BBC One")
        assertEquals(listOf("Sky Sport 1"), GuideSourceSuggestions.rank("Unknown", "sky sport", names, { it }, { false }))
    }

    @Test
    fun offsetsReadAsSignedHoursAndMinutes() {
        assertEquals("+1:30", formatGuideOffset(90))
        assertEquals("−0:30", formatGuideOffset(-30))
        assertEquals("0:00", formatGuideOffset(0))
    }
}
