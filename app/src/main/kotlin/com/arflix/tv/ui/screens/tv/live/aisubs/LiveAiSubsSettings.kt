package com.arflix.tv.ui.screens.tv.live.aisubs

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.repository.effectiveSettingsDataStore as settingsDataStore
import com.arflix.tv.ui.screens.player.subtitles.SubtitleAiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Live TV AI subtitles settings, kept deliberately small: the feature switch, and whether
 * channels start with AI subtitles on. The delay is always measured (Auto), the language is the
 * profile's subtitle language, and the key is the AI subtitles Gemini key.
 */
internal data class LiveAiSubsSettings(
    /** Offers "AI subtitles" in the live player's subtitle menu. */
    val enabled: Boolean = false,
    /** Every channel starts with AI subtitles on, without picking them in the menu. */
    val autoStart: Boolean = false,
    /** The AI subtitles key when that model is Gemini; blank otherwise. */
    val apiKey: String = "",
) {
    val hasKey: Boolean get() = apiKey.isNotBlank()
}

/** Global (not per-profile) settings, next to the AI subtitle key and model, synced to the cloud. */
internal object LiveAiSubsPrefs {
    val ENABLED = booleanPreferencesKey("live_ai_subs_enabled")
    val AUTO_START = booleanPreferencesKey("live_ai_subs_auto_start")
    private val SHARED_API_KEY = stringPreferencesKey("subtitle_ai_api_key")
    private val SHARED_MODEL = stringPreferencesKey("subtitle_ai_model")

    fun read(prefs: Preferences): LiveAiSubsSettings {
        val isGemini = prefs[SHARED_MODEL] == SubtitleAiModel.GEMINI_FLASH_25.name
        return LiveAiSubsSettings(
            enabled = prefs[ENABLED] ?: false,
            autoStart = prefs[AUTO_START] ?: false,
            apiKey = if (isGemini) prefs[SHARED_API_KEY].orEmpty().trim() else "",
        )
    }

    fun flow(context: Context): Flow<LiveAiSubsSettings> = context.settingsDataStore.data.map(::read)

    suspend fun setEnabled(context: Context, enabled: Boolean) {
        context.settingsDataStore.edit { it[ENABLED] = enabled }
    }

    suspend fun setAutoStart(context: Context, autoStart: Boolean) {
        context.settingsDataStore.edit { it[AUTO_START] = autoStart }
    }
}
