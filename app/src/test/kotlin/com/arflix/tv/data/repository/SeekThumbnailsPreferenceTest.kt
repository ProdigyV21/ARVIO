package com.arflix.tv.data.repository

import com.arflix.tv.ui.screens.player.PlayerUiState
import com.arflix.tv.ui.screens.settings.SettingsUiState
import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Test

class SeekThumbnailsPreferenceTest {
    private val gson = Gson()

    @Test fun `seek thumbnails are on by default`() {
        assertTrue(SettingsUiState().seekThumbnailsEnabled)
        assertTrue(PlayerUiState().seekThumbnailsEnabled)
    }

    @Test fun `older cloud payloads preserve the local choice`() {
        assertNull(CloudSyncRepository.CloudProfileSettings().seekThumbnailsEnabled)
        assertNull(gson.fromJson("{}", CloudSyncRepository.CloudProfileSettings::class.java).seekThumbnailsEnabled)
    }

    @Test fun `both choices survive cloud round trips`() {
        for (enabled in listOf(true, false)) {
            val settings = CloudSyncRepository.CloudProfileSettings(seekThumbnailsEnabled = enabled)
            val restored = gson.fromJson(gson.toJson(settings), CloudSyncRepository.CloudProfileSettings::class.java)
            assertEquals(enabled, restored.seekThumbnailsEnabled)
        }
    }
}
