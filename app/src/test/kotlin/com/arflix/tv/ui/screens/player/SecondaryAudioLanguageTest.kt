package com.arflix.tv.ui.screens.player

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SecondaryAudioLanguageTest {
    private fun track(index: Int, language: String?, label: String? = null) =
        AudioTrackInfo(index, index, 0, language, label, 2, 48_000, "aac")

    private val englishGerman = listOf(track(0, "en"), track(1, "de"))

    @Test fun preferredLanguageWinsWhenPresent() {
        val tracks = listOf(track(0, "en"), track(1, "de"), track(2, "da"))
        assertEquals(2, chooseAudioTrackIndex(tracks, "da", "de"))
    }

    @Test fun secondaryLanguageIsUsedWhenPreferredIsMissing() {
        assertEquals(1, chooseAudioTrackIndex(englishGerman, "da", "de"))
    }

    @Test fun secondaryLanguageMatchesTrackLabelsLikeThePreferredOne() {
        val tracks = listOf(track(0, "en"), track(1, null, "Deutsch 5.1"))
        assertEquals(1, chooseAudioTrackIndex(tracks, "da", "de"))
    }

    @Test fun neitherLanguagePresentLeavesTheDefaultTrack() {
        assertNull(chooseAudioTrackIndex(englishGerman, "da", "pl"))
    }

    @Test fun secondaryOffKeepsPreviousBehaviour() {
        assertNull(chooseAudioTrackIndex(englishGerman, "da", ""))
        assertEquals(0, chooseAudioTrackIndex(englishGerman, "en", ""))
    }

    @Test fun noneDisablesBothLanguages() {
        assertNull(chooseAudioTrackIndex(englishGerman, "none", "de"))
        assertArrayEquals(emptyArray<String>(), preferredAudioLanguages("none", "de"))
    }

    @Test fun languageListIsOrderedAndDeduplicated() {
        assertArrayEquals(arrayOf("da", "de"), preferredAudioLanguages(" da ", "de"))
        assertArrayEquals(arrayOf("da"), preferredAudioLanguages("da", "da"))
        assertArrayEquals(arrayOf("da"), preferredAudioLanguages("da", "  "))
        assertArrayEquals(arrayOf("de"), preferredAudioLanguages("", "de"))
    }
}
