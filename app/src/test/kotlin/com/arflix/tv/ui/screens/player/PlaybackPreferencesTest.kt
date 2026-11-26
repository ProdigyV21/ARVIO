package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.ui.screens.settings.SettingsUiState
import com.arflix.tv.ui.screens.settings.secondaryAudioLanguageOptions
import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Test

class PlaybackPreferencesTest {
    private val tracks = listOf("de", "en", "da")
    private val find: (List<String>, String) -> Int? = { list, code -> list.indexOf(code).takeIf { it >= 0 } }

    @Test fun `preferred language wins when the stream has it`() {
        assertEquals(2, pickAudioTrackByLanguage(tracks, "da", "en", find))
    }

    @Test fun `secondary language is used when the preferred one is missing`() {
        assertEquals(1, pickAudioTrackByLanguage(tracks, "fi", "en", find))
        assertEquals(1, pickAudioTrackByLanguage(tracks, "none", "en", find))
    }

    @Test fun `no match keeps the stream choice`() {
        assertNull(pickAudioTrackByLanguage(tracks, "fi", "", find))
        assertNull(pickAudioTrackByLanguage(tracks, "fi", "sv", find))
    }

    @Test fun `track selector gets both languages in order without none or duplicates`() {
        assertArrayEquals(arrayOf("da", "en"), preferredAudioLanguageList("da", "en"))
        assertArrayEquals(arrayOf("en"), preferredAudioLanguageList("none", "en"))
        assertArrayEquals(arrayOf("en"), preferredAudioLanguageList("en", "EN"))
        assertArrayEquals(emptyArray<String>(), preferredAudioLanguageList("none", ""))
    }

    @Test fun `secondary picker offers off and real languages only`() {
        assertEquals(listOf("Off", "English", "Danish"),
            secondaryAudioLanguageOptions(listOf("Auto (Original)", "None", "English", "Danish")))
    }

    @Test fun `defaults keep todays behaviour`() {
        assertTrue(SettingsUiState().autoSourceFailover)
        assertTrue(PlayerUiState().autoSourceFailover)
        assertEquals("Off", SettingsUiState().secondaryAudioLanguage)
        assertEquals("", PlayerUiState().secondaryAudioLanguage)
    }

    @Test fun `older cloud payloads keep the local choice and new values round trip`() {
        val gson = Gson()
        val empty = gson.fromJson("{}", CloudSyncRepository.CloudProfileSettings::class.java)
        assertNull(empty.autoSourceFailover)
        assertNull(empty.secondaryAudioLanguage)
        val settings = CloudSyncRepository.CloudProfileSettings(autoSourceFailover = false, secondaryAudioLanguage = "Danish")
        val restored = gson.fromJson(gson.toJson(settings), CloudSyncRepository.CloudProfileSettings::class.java)
        assertEquals(false, restored.autoSourceFailover)
        assertEquals("Danish", restored.secondaryAudioLanguage)
    }
}
