package com.arflix.tv.ui.screens.player.subtitles.bilingual

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
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
    private val _loadedProfileId = MutableStateFlow<String?>(null)
    val loadedProfileId: StateFlow<String?> = _loadedProfileId.asStateFlow()
    /** Default AUTO must not authorize API requests before the real profile has loaded. */
    val isLoadedForCurrentProfile: Boolean get() = _loadedProfileId.value == profileId.value

    /** True when the user has a Gemini/Groq key saved (ARVIO's existing global key). */
    val aiKeyConfigured: Flow<Boolean> = dataStore.data
        .map { !it[AI_API_KEY].isNullOrBlank() }
        .distinctUntilChanged()

    private val lock = Any()
    private val pendingWrites = mutableMapOf<String, Job>()
    private val pendingSettings = mutableMapOf<String, BilingualSubtitleSettings>()
    private val preLoadEdits = mutableMapOf<String, MutableList<(BilingualSubtitleSettings) -> BilingualSubtitleSettings>>()
    private var loadedProfile: String? = null
    private var anchorSettings = BilingualSubtitleSettings.DEFAULT
    private var legacyAppearance = LegacySubtitleAppearance()

    init {
        scope.launch {
            combine(profileId, dataStore.data) { id, prefs -> id to prefs }
                .collect { (id, prefs) ->
                    val raw = prefs[keyFor(id)]
                    val legacy = legacyAppearanceFor(prefs, id)
                    val decoded = BilingualSettingsCodec.decodeOrNull(raw)
                    val migration = synchronized(lock) {
                        // Pending edits belong to their captured profile, not whichever profile
                        // happens to be active when the debounce expires.
                        val pending = pendingSettings[id]
                        val base = pending ?: legacy.initialize(
                            decoded ?: BilingualSubtitleSettings.DEFAULT,
                            hasBilingualSettings = decoded != null,
                        )
                        // An early click is an edit, not a whole default-valued configuration.
                        // Replay it only after the actual profile and legacy baseline are known.
                        val edits = preLoadEdits.remove(id)
                        val anchor = if (edits.isNullOrEmpty()) base else
                            edits.fold(legacy.resolve(base)) { value, edit -> edit(value).sanitized() }
                                .copy(legacyAppearance = legacy)
                        if (!edits.isNullOrEmpty()) scheduleWriteLocked(id, anchor)
                        loadedProfile = id
                        legacyAppearance = legacy
                        anchorSettings = anchor
                        _settings.value = legacy.resolve(anchor)
                        _loadedProfileId.value = id
                        // Persist even a pristine profile's empty baseline. Otherwise its first
                        // TV adjustment would be mistaken for a historical legacy migration.
                        anchor.takeIf { pending == null && decoded?.legacyAppearance == null }
                    }
                    if (migration != null) {
                        // Capture the baseline once, even for an existing bilingual file. Do
                        // not overwrite a newer bilingual edit made while DataStore was loading.
                        dataStore.edit { current ->
                            synchronized(lock) {
                                if (current[keyFor(id)] == raw && pendingSettings[id] == null) {
                                    current[keyFor(id)] = BilingualSettingsCodec.encode(migration)
                                }
                            }
                        }
                    }
                }
        }
    }

    fun update(transform: (BilingualSubtitleSettings) -> BilingualSubtitleSettings) {
        synchronized(lock) {
            val id = profileId.value
            if (_loadedProfileId.value != id) {
                val edits = preLoadEdits.getOrPut(id) { mutableListOf() }.also { it.add(transform) }
                val preview = pendingSettings[id] ?: BilingualSubtitleSettings.DEFAULT
                _settings.value = edits.fold(preview) { value, edit -> edit(value).sanitized() }
                return
            }
            val legacy = if (loadedProfile == id) legacyAppearance else LegacySubtitleAppearance()
            val current = if (loadedProfile == id) _settings.value else
                legacy.resolve(pendingSettings[id] ?: BilingualSubtitleSettings.DEFAULT)
            // The panel edits the already-resolved values. Re-anchor here, not on each frame
            // or on each legacy change: this preserves independent styles and avoids drift.
            val next = transform(current).copy(legacyAppearance = legacy).sanitized()
            loadedProfile = id
            legacyAppearance = legacy
            anchorSettings = next
            _settings.value = next
            scheduleWriteLocked(id, next)
        }
    }

    private fun scheduleWriteLocked(id: String, next: BilingualSubtitleSettings) {
        pendingSettings[id] = next
        pendingWrites.remove(id)?.cancel()
        pendingWrites[id] = scope.launch {
            delay(persistDebounceMs)
            write(id, next)
            synchronized(lock) {
                if (pendingSettings[id] == next) {
                    pendingSettings.remove(id)
                    pendingWrites.remove(id)
                }
            }
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
        // Flush may be called immediately after a click, before the collector's first emission,
        // or after several rapid profile switches. Materialize every queued edit from real data.
        val prefs = dataStore.data.first()
        val writes = synchronized(lock) {
            preLoadEdits.toMap().forEach { (id, edits) ->
                val legacy = legacyAppearanceFor(prefs, id)
                val decoded = BilingualSettingsCodec.decodeOrNull(prefs[keyFor(id)])
                val base = pendingSettings[id] ?: legacy.initialize(
                    decoded ?: BilingualSubtitleSettings.DEFAULT, hasBilingualSettings = decoded != null,
                )
                val next = edits.fold(legacy.resolve(base)) { value, edit -> edit(value).sanitized() }
                    .copy(legacyAppearance = legacy)
                pendingSettings[id] = next
                if (id == profileId.value) {
                    loadedProfile = id
                    legacyAppearance = legacy
                    anchorSettings = next
                    _settings.value = next
                    _loadedProfileId.value = id
                }
            }
            preLoadEdits.clear()
            pendingWrites.values.forEach { it.cancel() }
            pendingWrites.clear()
            pendingSettings.toMutableMap().also { values ->
                loadedProfile?.let { values[it] = anchorSettings }
            }
        }
        // Persist anchors, not resolved display values. Otherwise flush would bake a legacy
        // scale into the styles a second time on the next launch.
        writes.forEach { (id, value) -> write(id, value) }
        synchronized(lock) {
            writes.forEach { (id, value) ->
                if (pendingSettings[id] == value) pendingSettings.remove(id)
            }
        }
    }

    /** Raw persisted anchor; [settings] exposes the appearance resolved against current legacy prefs. */
    suspend fun readPersisted(): BilingualSubtitleSettings =
        BilingualSettingsCodec.decode(dataStore.data.first()[keyFor(profileId.value)])

    private suspend fun write(id: String, value: BilingualSubtitleSettings) {
        dataStore.edit { it[keyFor(id)] = BilingualSettingsCodec.encode(value) }
    }

    companion object {
        /** Same naming as ProfileManager.profileStringKeyFor(profileId, name). */
        fun keyFor(profileId: String): Preferences.Key<String> =
            stringPreferencesKey("profile_${profileId}_${BilingualSettingsCodec.PREF_NAME}")

        /** Read all legacy appearance keys with exactly the same profile boundary as the JSON. */
        fun legacyAppearanceFor(prefs: Preferences, profileId: String): LegacySubtitleAppearance {
            val prefix = "profile_${profileId}_"
            fun string(name: String) = prefs[stringPreferencesKey(prefix + name)]
            fun int(name: String) = prefs[intPreferencesKey(prefix + name)]
            return LegacySubtitleAppearance(
                size = string("subtitle_size"),
                sizePercent = int("subtitle_size_pct"),
                color = string("subtitle_color"),
                position = string("subtitle_offset"),
                verticalPercent = int("subtitle_vertical_pct"),
                style = string("subtitle_style"),
                font = string("subtitle_font"),
                stylized = prefs[booleanPreferencesKey(prefix + "subtitle_stylized")],
            )
        }

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
