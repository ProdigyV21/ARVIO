package com.arflix.tv.ui.screens.home

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28], qualifiers = "en-rUS")
class HomeProfilePreferencesTest {

    @Test
    fun `delayed cloud restore enables trailer autoplay without recreating Home`() = runTest {
        val profileId = MutableStateFlow("primary")
        val preferences = MutableStateFlow<Preferences>(mutablePreferencesOf())

        observeHomeProfilePreferences(profileId, preferences).test {
            assertThat(awaitItem().trailerAutoPlay).isFalse()

            preferences.value = mutablePreferencesOf(
                booleanPreferencesKey("profile_primary_trailer_auto_play") to true
            )

            assertThat(awaitItem().trailerAutoPlay).isTrue()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `IPTV favorites Home preference updates without recreating Home`() = runTest {
        val profileId = MutableStateFlow("primary")
        val preferences = MutableStateFlow<Preferences>(mutablePreferencesOf())

        observeHomeProfilePreferences(profileId, preferences).test {
            assertThat(awaitItem().iptvFavoritesOnHome).isTrue()

            preferences.value = mutablePreferencesOf(
                booleanPreferencesKey("profile_primary_iptv_favorites_on_home") to false
            )

            assertThat(awaitItem().iptvFavoritesOnHome).isFalse()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `profile switch reads only the selected profile settings`() = runTest {
        val profileId = MutableStateFlow("primary")
        val preferences = MutableStateFlow<Preferences>(
            mutablePreferencesOf(
                booleanPreferencesKey("profile_primary_trailer_auto_play") to true,
                booleanPreferencesKey("profile_secondary_trailer_auto_play") to false,
                stringPreferencesKey("profile_primary_trailer_delay_seconds") to "5",
                stringPreferencesKey("profile_secondary_trailer_delay_seconds") to "1"
            )
        )

        observeHomeProfilePreferences(profileId, preferences).test {
            val primary = awaitItem()
            assertThat(primary.trailerAutoPlay).isTrue()
            assertThat(primary.trailerDelaySeconds).isEqualTo(5)

            profileId.value = "secondary"

            val secondary = awaitItem()
            assertThat(secondary.trailerAutoPlay).isFalse()
            assertThat(secondary.trailerDelaySeconds).isEqualTo(1)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `missing settings preserve Home defaults`() {
        val settings = readHomeProfilePreferences(mutablePreferencesOf(), "primary")

        assertThat(settings.trailerAutoPlay).isFalse()
        assertThat(settings.trailerSoundEnabled).isFalse()
        assertThat(settings.trailerDelaySeconds).isEqualTo(2)
        assertThat(settings.trailerInCards).isTrue()
        assertThat(settings.showBudget).isTrue()
        assertThat(settings.iptvFavoritesOnHome).isTrue()
        assertThat(settings.clockFormat).isEqualTo("24h")
        assertThat(settings.smoothScrolling).isFalse()
        assertThat(settings.contentLanguage).isEqualTo("en-US")
    }

    @Test
    fun `missing profile language uses last app language`() {
        val settings = readHomeProfilePreferences(
            mutablePreferencesOf(
                stringPreferencesKey("last_app_language") to "ar-SA"
            ),
            "primary"
        )

        assertThat(settings.contentLanguage).isEqualTo("ar-SA")
    }

    @Test
    fun `profile language overrides last app language`() {
        val settings = readHomeProfilePreferences(
            mutablePreferencesOf(
                stringPreferencesKey("last_app_language") to "ar-SA",
                stringPreferencesKey("profile_primary_content_language") to "iw-IL"
            ),
            "primary"
        )

        assertThat(settings.contentLanguage).isEqualTo("iw-IL")
    }
}
