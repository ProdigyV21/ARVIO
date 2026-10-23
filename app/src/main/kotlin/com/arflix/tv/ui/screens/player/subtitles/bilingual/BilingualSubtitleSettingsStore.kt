package com.arflix.tv.ui.screens.player.subtitles.bilingual

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.settingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Singleton

/**
 * App-wide source of truth for the bilingual subtitle settings.
 *
 * Persisted as one JSON value in ARVIO's existing `settings_prefs` DataStore under a
 * profile-scoped key (`profile_<id>_bilingual_subtitle_settings_v1`), i.e. the same mechanism
 * every other per-profile subtitle setting uses. It is deliberately NOT added to CloudSync's key
 * list, so the cloud sync payload/protocol is unchanged.
 *
 * [settings] is updated synchronously on [update] (the overlay re-lays out on the very next
 * frame while a slider is dragged); the DataStore write is debounced.
 */
class BilingualSubtitleSettingsStore(
    private val dataStore: DataStore<Preferences>,
    private val profileId: StateFlow<String>,
    private val scope: CoroutineScope,
    private val persistDebounceMs: Long = 300L,
) {
    private val _settings = MutableStateFlow(BilingualSubtitleSettings.DEFAULT)
    val settings: StateFlow<BilingualSubtitleSettings> = _settings.asStateFlow()

    /** True when the user has a Gemini/Groq key saved (ARVIO's existing global key). */
    val aiKeyConfigured: Flow<Boolean> = dataStore.data
        .map { !it[AI_API_KEY].isNullOrBlank() }
        .distinctUntilChanged()

    @Volatile private var pendingWrite: Job? = null
    @Volatile private var loadedProfile: String? = null

    init {
        scope.launch {
            combine(profileId, dataStore.data) { id, prefs -> id to prefs[keyFor(id)] }
                .collect { (id, raw) ->
                    // Never let a stale disk value clobber an edit that is still being written.
                    if (pendingWrite?.isActive == true && loadedProfile == id) return@collect
                    loadedProfile = id
                    _settings.value = BilingualSettingsCodec.decode(raw)
                }
        }
    }

    fun update(transform: (BilingualSubtitleSettings) -> BilingualSubtitleSettings) {
        val next = transform(_settings.value).sanitized()
        _settings.value = next
        val id = profileId.value
        pendingWrite?.cancel()
        pendingWrite = scope.launch {
            delay(persistDebounceMs)
            write(id, next)
        }
    }

    /** Resets the STYLE only; mode, smart mode and the chosen languages are kept. */
    fun resetToDefaults() = update {
        BilingualSubtitleSettings.DEFAULT.copy(
            mode = it.mode,
            smartEnabled = it.smartEnabled,
            generationMode = it.generationMode,
            primaryLanguage = it.primaryLanguage,
            secondaryLanguage = it.secondaryLanguage,
        )
    }

    /** Writes immediately (used by tests and when leaving the player). */
    suspend fun flush() {
        pendingWrite?.cancel()
        write(profileId.value, _settings.value)
    }

    suspend fun readPersisted(): BilingualSubtitleSettings =
        BilingualSettingsCodec.decode(dataStore.data.first()[keyFor(profileId.value)])

    private suspend fun write(id: String, value: BilingualSubtitleSettings) {
        dataStore.edit { it[keyFor(id)] = BilingualSettingsCodec.encode(value) }
    }

    companion object {
        /** Same naming as ProfileManager.profileStringKeyFor(profileId, name). */
        fun keyFor(profileId: String): Preferences.Key<String> =
            stringPreferencesKey("profile_${profileId}_${BilingualSettingsCodec.PREF_NAME}")

        /** ARVIO's existing global AI key (see PlayerViewModel.aiApiKeyKey). Read-only here. */
        private val AI_API_KEY = stringPreferencesKey("subtitle_ai_api_key")

        fun from(context: Context): BilingualSubtitleSettingsStore =
            EntryPointAccessors.fromApplication(
                context.applicationContext,
                BilingualSubtitleEntryPoint::class.java
            ).bilingualSubtitleSettingsStore()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface BilingualSubtitleEntryPoint {
    fun bilingualSubtitleSettingsStore(): BilingualSubtitleSettingsStore
}

@Module
@InstallIn(SingletonComponent::class)
object BilingualSubtitleModule {
    @Provides
    @Singleton
    fun provideBilingualSubtitleSettingsStore(
        @ApplicationContext context: Context,
        profileManager: ProfileManager,
    ): BilingualSubtitleSettingsStore = BilingualSubtitleSettingsStore(
        dataStore = context.settingsDataStore,
        profileId = profileManager.currentProfileId,
        scope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    )
}
