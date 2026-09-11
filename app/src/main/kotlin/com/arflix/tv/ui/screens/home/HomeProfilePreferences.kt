package com.arflix.tv.ui.screens.home

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.util.IPTV_FAVORITES_ON_HOME
import com.arflix.tv.util.resolveAppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

internal data class HomeProfilePreferences(
    val trailerAutoPlay: Boolean,
    val trailerSoundEnabled: Boolean,
    val trailerDelaySeconds: Int,
    val trailerInCards: Boolean,
    val showBudget: Boolean,
    val iptvFavoritesOnHome: Boolean,
    val clockFormat: String,
    val smoothScrolling: Boolean,
    val contentLanguage: String
)

internal fun readHomeProfilePreferences(
    preferences: Preferences,
    profileId: String
): HomeProfilePreferences {
    val prefix = "profile_${profileId}_"
    val contentLang = resolveAppLanguage(preferences, profileId)
    return HomeProfilePreferences(
        trailerAutoPlay = preferences[booleanPreferencesKey("${prefix}trailer_auto_play")] ?: false,
        trailerSoundEnabled = preferences[booleanPreferencesKey("${prefix}trailer_sound_enabled")] ?: false,
        trailerDelaySeconds = preferences[stringPreferencesKey("${prefix}trailer_delay_seconds")]
            ?.toIntOrNull() ?: 2,
        trailerInCards = preferences[booleanPreferencesKey("${prefix}trailer_in_cards")] ?: true,
        showBudget = preferences[booleanPreferencesKey("${prefix}show_budget_on_home")] ?: true,
        iptvFavoritesOnHome = preferences[
            booleanPreferencesKey("$prefix$IPTV_FAVORITES_ON_HOME")
        ] ?: true,
        clockFormat = preferences[stringPreferencesKey("${prefix}clock_format")] ?: "24h",
        smoothScrolling = preferences[booleanPreferencesKey("${prefix}smooth_scrolling")] ?: false,
        contentLanguage = contentLang
    )
}

internal fun observeHomeProfilePreferences(
    activeProfileId: Flow<String>,
    preferences: Flow<Preferences>
): Flow<HomeProfilePreferences> = activeProfileId
    .combine(preferences) { profileId, profilePreferences ->
        readHomeProfilePreferences(profilePreferences, profileId)
    }
    .distinctUntilChanged()
