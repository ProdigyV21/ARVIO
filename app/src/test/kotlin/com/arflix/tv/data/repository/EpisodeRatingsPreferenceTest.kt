package com.arflix.tv.data.repository

import com.arflix.tv.data.repository.CloudSyncRepository.CloudProfileSettings
import com.arflix.tv.ui.screens.details.DetailsUiState
import com.arflix.tv.ui.screens.settings.SettingsUiState
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Test

class EpisodeRatingsPreferenceTest {
    private val gson = Gson()

    @Test
    fun episodeRatingsAreDisabledInInitialUiStates() {
        assertThat(SettingsUiState().showEpisodeRatings).isFalse()
        assertThat(DetailsUiState().showEpisodeRatings).isFalse()
    }

    @Test
    fun newCloudProfileDefaultsToDisabled() {
        assertThat(CloudProfileSettings().showEpisodeRatings).isFalse()
    }

    @Test
    fun cloudProfileWithoutPreferenceDefaultsToDisabled() {
        val restored = gson.fromJson("{}", CloudProfileSettings::class.java)

        assertThat(restored.showEpisodeRatings).isFalse()
    }

    @Test
    fun explicitOptInSurvivesCloudRoundTrip() {
        val original = CloudProfileSettings(showEpisodeRatings = true)
        val restored = gson.fromJson(gson.toJson(original), CloudProfileSettings::class.java)

        assertThat(restored.showEpisodeRatings).isTrue()
        assertThat(SettingsUiState(showEpisodeRatings = true).showEpisodeRatings).isTrue()
        assertThat(DetailsUiState(showEpisodeRatings = true).showEpisodeRatings).isTrue()
    }

    @Test
    fun explicitOptOutSurvivesCloudRoundTrip() {
        val original = CloudProfileSettings(showEpisodeRatings = false)
        val restored = gson.fromJson(gson.toJson(original), CloudProfileSettings::class.java)

        assertThat(restored.showEpisodeRatings).isFalse()
    }
}
