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

/** How long live playback is held back so subtitles arrive before their line is heard. */
internal enum class LiveAiSubsDelay(val prefValue: String, val fixedMs: Long?) {
    /** Uses the measured AI latency; see [autoLiveAiSubsDelayMs]. */
    AUTO("auto", null),
    SIX("6", 6_000L),
    TEN("10", 10_000L),
    FIFTEEN("15", 15_000L);

    fun next(): LiveAiSubsDelay = entries[(ordinal + 1) % entries.size]

    companion object {
        /**
         * Auto: Gemini's answer time for a few seconds of audio was measured at 5-9 s on a real
         * box, so no fixed value suits every connection; Auto follows the measurement.
         */
        val DEFAULT = AUTO
        fun fromPref(value: String?): LiveAiSubsDelay = entries.firstOrNull { it.prefValue == value } ?: DEFAULT
    }
}

/**
 * Per channel by default: with the feature on, every channel change waits for the delay, so only
 * channels the viewer picked "AI subtitles" on pay that cost.
 */
internal enum class LiveAiSubsActivation(val prefValue: String) {
    PER_CHANNEL("per_channel"),
    ALWAYS("always");

    fun next(): LiveAiSubsActivation = if (this == PER_CHANNEL) ALWAYS else PER_CHANNEL

    companion object {
        fun fromPref(value: String?): LiveAiSubsActivation = entries.firstOrNull { it.prefValue == value } ?: PER_CHANNEL
    }
}

internal data class LiveAiSubsSettings(
    val enabled: Boolean = false,
    val delay: LiveAiSubsDelay = LiveAiSubsDelay.DEFAULT,
    val activation: LiveAiSubsActivation = LiveAiSubsActivation.PER_CHANNEL,
    /** A language code, or blank for the profile's subtitle language. */
    val targetLanguage: String = "",
    /** The Gemini key in effect: the AI subtitles key when that is a Gemini key, else [ownApiKey]. */
    val apiKey: String = "",
    /** A Gemini key just for Live TV, used while the AI subtitles model is not Gemini. */
    val ownApiKey: String = "",
    /** True while the shared AI subtitles key is a Gemini key (and so is the one used here). */
    val usesSharedKey: Boolean = true,
) {
    val hasKey: Boolean get() = apiKey.isNotBlank()
}

/**
 * Global (not per-profile) settings for Live TV AI subtitles, next to the existing AI subtitle
 * key and model in the same DataStore, and synced to the cloud with them (CloudSyncRepository).
 */
internal object LiveAiSubsPrefs {
    val ENABLED = booleanPreferencesKey("live_ai_subs_enabled")
    val DELAY = stringPreferencesKey("live_ai_subs_delay")
    val ACTIVATION = stringPreferencesKey("live_ai_subs_activation")
    val TARGET_LANGUAGE = stringPreferencesKey("live_ai_subs_target_language")
    val OWN_API_KEY = stringPreferencesKey("live_ai_subs_api_key")
    private val SHARED_API_KEY = stringPreferencesKey("subtitle_ai_api_key")
    private val SHARED_MODEL = stringPreferencesKey("subtitle_ai_model")

    /** Target language choices offered in settings, cycled in this order ("" = profile default). */
    val TARGET_LANGUAGE_CHOICES = listOf("", "he", "en", "ar", "ru", "es", "fr", "de", "pt", "it")

    fun read(prefs: Preferences): LiveAiSubsSettings {
        val sharedIsGemini = prefs[SHARED_MODEL] == SubtitleAiModel.GEMINI_FLASH_25.name
        val shared = prefs[SHARED_API_KEY].orEmpty().trim()
        val own = prefs[OWN_API_KEY].orEmpty().trim()
        return LiveAiSubsSettings(
            enabled = prefs[ENABLED] ?: false,
            delay = LiveAiSubsDelay.fromPref(prefs[DELAY]),
            activation = LiveAiSubsActivation.fromPref(prefs[ACTIVATION]),
            targetLanguage = prefs[TARGET_LANGUAGE].orEmpty(),
            apiKey = if (sharedIsGemini && shared.isNotBlank()) shared else own,
            ownApiKey = own,
            usesSharedKey = sharedIsGemini && shared.isNotBlank(),
        )
    }

    fun flow(context: Context): Flow<LiveAiSubsSettings> = context.settingsDataStore.data.map(::read)

    suspend fun setEnabled(context: Context, enabled: Boolean) {
        context.settingsDataStore.edit { it[ENABLED] = enabled }
    }

    suspend fun setDelay(context: Context, delay: LiveAiSubsDelay) {
        context.settingsDataStore.edit { it[DELAY] = delay.prefValue }
    }

    suspend fun setActivation(context: Context, activation: LiveAiSubsActivation) {
        context.settingsDataStore.edit { it[ACTIVATION] = activation.prefValue }
    }

    suspend fun setTargetLanguage(context: Context, code: String) {
        context.settingsDataStore.edit { it[TARGET_LANGUAGE] = code }
    }

    suspend fun setOwnApiKey(context: Context, key: String) {
        context.settingsDataStore.edit { it[OWN_API_KEY] = key.trim() }
    }
}
