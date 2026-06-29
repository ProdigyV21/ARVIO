package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.components.CARD_LAYOUT_MODE_LANDSCAPE
import com.arflix.tv.ui.components.catalogueRowLayoutKeyFromPreferenceName
import com.arflix.tv.ui.components.catalogueRowLayoutPreferencePrefixFor
import com.arflix.tv.ui.components.normalizeCardLayoutMode
import com.arflix.tv.ui.components.profileCatalogueRowLayoutModeKey
import com.arflix.tv.util.LAST_APP_LANGUAGE_KEY
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.ACCENT_COLOR_KEY
import com.arflix.tv.util.OLED_BLACK_BACKGROUND_KEY
import com.arflix.tv.util.SKIP_PROFILE_SELECTION_KEY
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.max
import javax.inject.Inject
import javax.inject.Singleton

internal fun mergeCloudAddonsPreservingLocalDirectAddons(
    cloudAddons: List<Addon>,
    localAddons: List<Addon>
): Pair<List<Addon>, Boolean> {
    val merged = LinkedHashMap<String, Addon>()
    cloudAddons.forEach { addon ->
        val id = addon.id.trim()
        if (id.isNotBlank()) {
            merged[id] = addon
        }
    }

    var preservedLocalAddon = false
    localAddons.forEach { addon ->
        val id = addon.id.trim()
        val shouldPreserve = id.isNotBlank() &&
            id !in merged &&
            id != "opensubtitles" &&
            addon.isInstalled &&
            addon.type == AddonType.CUSTOM &&
            !addon.url.isNullOrBlank()

        if (shouldPreserve) {
            merged[id] = addon
            preservedLocalAddon = true
        }
    }

    return merged.values.toList() to preservedLocalAddon
}

/**
 * Shared cloud sync logic used by both SettingsViewModel (full push/pull on
 * Settings screen) and ProfileViewModel (pull on profile selection).
 *
 * This repository handles the data layer: build the cloud JSON snapshot,
 * push it to the server, and restore cloud state to local repositories.
 * UI-specific concerns (toasts, loading indicators) are left to the ViewModels.
 */
@Singleton
class CloudSyncRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val profileManager: ProfileManager,
    private val catalogRepository: CatalogRepository,
    private val iptvRepository: IptvRepository,
    private val streamRepository: StreamRepository,
    private val homeServerRepository: HomeServerRepository,
    private val traktRepository: TraktRepository,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val watchlistRepository: WatchlistRepository,
    private val profileAvatarImageManager: ProfileAvatarImageManager,
    private val invalidationBus: CloudSyncInvalidationBus,
    private val pluginDataStore: com.arflix.tv.data.local.PluginDataStore
) {
    private val TAG = "CloudSync"
    private val gson = Gson()
    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val cloudSyncLocalDirtyAtKey = longPreferencesKey("cloud_sync_local_dirty_at")
    private val cloudSyncLastPushAtKey = longPreferencesKey("cloud_sync_last_push_at")
    private val cloudSyncLastAppliedAtKey = longPreferencesKey("cloud_sync_last_applied_at")
    private val globalDnsProviderKey = stringPreferencesKey(OkHttpProvider.DNS_PROVIDER_PREF_KEY)
    private val customUserAgentKey = stringPreferencesKey(OkHttpProvider.USER_AGENT_PREF_KEY)
    @Volatile
    private var latestLocalDirtyAt: Long = 0L

    private fun payloadSizeBucket(payload: String): String = when {
        payload.length < 10_000 -> "lt_10kb"
        payload.length < 100_000 -> "lt_100kb"
        payload.length < 1_000_000 -> "lt_1mb"
        else -> "gte_1mb"
    }

    private fun cloudPayloadProfileCount(payload: String): Int? {
        if (payload.isBlank()) return null
        return runCatching {
            val root = JSONObject(payload)
            if (!root.has("profiles")) null else root.optJSONArray("profiles")?.length() ?: 0
        }.getOrNull()
    }

    private fun hasMeaningfulLocalProfiles(profiles: List<Profile>): Boolean {
        if (profiles.isEmpty()) return false
        if (profiles.size > 1) return true

        val profile = profiles.first()
        return !profile.name.equals("Profile 1", ignoreCase = true) ||
            profile.avatarId != 0 ||
            profile.avatarImageVersion > 0L ||
            profile.isKidsProfile ||
            profile.isLocked ||
            !profile.pin.isNullOrBlank()
    }

    suspend fun hasMeaningfulLocalProfiles(): Boolean {
        return hasMeaningfulLocalProfiles(profileRepository.getProfiles())
    }

    private fun shouldRestoreRemoteBeforePush(localPayload: String, remotePayload: String): Boolean {
        val localRank = accountSyncPayloadRestoreRank(localPayload)
        val remoteRank = accountSyncPayloadRestoreRank(remotePayload)
        val localProfileCount = cloudPayloadProfileCount(localPayload)
        val remoteProfileCount = cloudPayloadProfileCount(remotePayload)

        if (
            localProfileCount != null &&
            remoteProfileCount != null &&
            remoteProfileCount > localProfileCount &&
            remoteRank >= localRank
        ) {
            return true
        }

        return remoteRank >= 70 && localRank < 70
    }

    private fun mergeAddonsForSharedRestore(addonLists: Iterable<List<Addon>>): List<Addon> {
        val merged = LinkedHashMap<String, Addon>()
        addonLists.flatten().forEach { addon ->
            val id = addon.id.trim()
            if (id.isNotBlank()) {
                merged.putIfAbsent(id, addon)
            }
        }
        return merged.values.toList()
    }

    /**
     * Serializes push/pull so they can never overlap. Without this, a manual
     * Save in Settings that coincides with a realtime pull (or a startup pull
     * that coincides with an auto-push) can interleave DataStore writes and
     * leave the local cache diverged from the cloud — which showed up as
     * "sync silently stops, needs logout/login to recover".
     */
    private val cloudSyncMutex = Mutex()

    /** Callback invoked after a successful push so realtime listeners can skip the echo. */
    var onPushCompleted: (() -> Unit)? = null

    /**
     * Dirty flag: set to true when a push fails, cleared on next successful push.
     * Callers (HomeViewModel.pullCloudStateOnResume, RealtimeSyncManager periodic sync)
     * can check this and retry the push so failed pushes aren't permanently lost.
     */
    @Volatile
    var isPushDirty: Boolean = false
        private set

    fun markLocalStateDirty() {
        val dirtyAt = System.currentTimeMillis()
        latestLocalDirtyAt = max(latestLocalDirtyAt, dirtyAt)
        isPushDirty = true
        repositoryScope.launch {
            persistLocalDirty(dirtyAt)
        }
    }

    suspend fun markLocalStateDirtyNow() {
        val dirtyAt = System.currentTimeMillis()
        latestLocalDirtyAt = max(latestLocalDirtyAt, dirtyAt)
        isPushDirty = true
        persistLocalDirty(dirtyAt)
    }

    private suspend fun persistLocalDirty(dirtyAt: Long) {
        if (dirtyAt <= 0L || latestLocalDirtyAt <= 0L) return
        context.settingsDataStore.edit { prefs ->
            if (latestLocalDirtyAt <= 0L) return@edit
            val existing = prefs[cloudSyncLocalDirtyAtKey] ?: 0L
            prefs[cloudSyncLocalDirtyAtKey] = max(existing, dirtyAt)
        }
    }

    private suspend fun markPushFailedDirty() {
        val dirtyAt = latestLocalDirtyAt.takeIf { it > 0L } ?: System.currentTimeMillis()
        latestLocalDirtyAt = max(latestLocalDirtyAt, dirtyAt)
        isPushDirty = true
        persistLocalDirty(dirtyAt)
    }

    private suspend fun clearLocalDirtyAfterSuccessfulPush() {
        latestLocalDirtyAt = 0L
        isPushDirty = false
        context.settingsDataStore.edit { prefs ->
            prefs.remove(cloudSyncLocalDirtyAtKey)
            prefs[cloudSyncLastPushAtKey] = System.currentTimeMillis()
        }
    }

    private suspend fun clearStaleLocalDirtyBeforeRemoteRestore() {
        latestLocalDirtyAt = 0L
        isPushDirty = false
        context.settingsDataStore.edit { prefs ->
            prefs.remove(cloudSyncLocalDirtyAtKey)
        }
    }

    suspend fun hasPendingLocalChanges(): Boolean {
        val storedDirtyAt = context.settingsDataStore.data.first()[cloudSyncLocalDirtyAtKey] ?: 0L
        if (storedDirtyAt > 0L) {
            latestLocalDirtyAt = max(latestLocalDirtyAt, storedDirtyAt)
        }
        return isPushDirty || latestLocalDirtyAt > 0L || storedDirtyAt > 0L
    }

    private suspend fun markCloudPayloadApplied(payload: String, payloadHash: Int) {
        val cloudUpdatedAt = runCatching { JSONObject(payload).optLong("updatedAt", 0L) }.getOrDefault(0L)
        context.settingsDataStore.edit { prefs ->
            prefs[cloudSyncLastAppliedAtKey] = cloudUpdatedAt.takeIf { it > 0L } ?: System.currentTimeMillis()
            prefs[androidx.datastore.preferences.core.intPreferencesKey("cloud_sync_last_applied_hash")] = payloadHash
        }
    }

    enum class RestoreResult { RESTORED, NO_BACKUP, FAILED }

    // ── Data class for per-profile settings stored in cloud ──

    data class CloudProfileSettings(
        val defaultSubtitle: String = "Off",
        val defaultAudioLanguage: String = "Auto (Original)",
        val contentLanguage: String = "en-US",
        val subtitleSize: String = "Medium",
        val subtitleColor: String = "White",
        val subtitleStyle: String = "Bold",
        val subtitleOffset: String = "Bottom",
        val subtitleStylized: Boolean = true,
        val cardLayoutMode: String = CARD_LAYOUT_MODE_LANDSCAPE,
        val frameRateMatchingMode: String = "Off",
        val autoPlayNext: Boolean = true,
        val autoPlaySingleSource: Boolean = true,
        val autoPlayMinQuality: String = "Any",
        val trailerAutoPlay: Boolean = false,
        val trailerSoundEnabled: Boolean = false,
        val trailerDelaySeconds: Int = 2,
        val trailerInCards: Boolean = true,
        val clockFormat: String = "24h",
        val showBudget: Boolean = true,
        val showLoadingStats: Boolean? = null,
        val spoilerBlurEnabled: Boolean = false,
        val volumeBoostDb: Int = 0,
        val includeSpecials: Boolean = false,
        val dnsProvider: String = "system",
        val subtitleUsageJson: String = "",
        val subtitleSettingsUpdatedAt: Long = 0L,
        val secondarySubtitle: String = "Off",
        val filterSubtitlesByLanguage: Boolean = true,
        val homeServerConnectionJson: String? = null,
        val torrServerBaseUrl: String? = null,
        val catalogueRowLayoutModes: Map<String, String> = emptyMap()
    )

    // ── DataStore key helpers ──

    private fun contentLanguageKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "content_language")
    private fun trailerAutoPlayKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "trailer_auto_play")
    private fun trailerSoundEnabledKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "trailer_sound_enabled")
    private fun trailerInCardsKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "trailer_in_cards")
    private fun trailerDelayKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "trailer_delay_seconds")
    private fun clockFormatKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "clock_format")
    private fun showBudgetKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "show_budget_on_home")
    private fun showLoadingStatsKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "show_loading_stats")
    private fun spoilerBlurKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "spoiler_blur")
    private fun volumeBoostDbKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "volume_boost_db")
    private fun dnsProviderKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "dns_provider")
    private fun subtitleUsageKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "subtitle_usage_v1")
    private fun subtitleSettingsUpdatedAtKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "subtitle_settings_updated_at")

    private fun subtitleSizeKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "subtitle_size")
    private fun subtitleColorKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "subtitle_color")
    private fun subtitleOffsetKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "subtitle_offset")
    private fun subtitleStyleKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "subtitle_style")
    private fun subtitleStylizedKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "subtitle_stylized")
    private fun iptvHiddenGroupsKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "iptv_hidden_groups")
    private fun iptvGroupOrderKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "iptv_group_order")
    private fun secondarySubtitleKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "secondary_subtitle")
    private fun filterSubtitlesByLanguageKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "filter_subtitles_by_lang")
    private fun defaultSubtitleKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "default_subtitle")
    private fun defaultAudioLanguageKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "default_audio_language")
    private fun cardLayoutModeKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "card_layout_mode")
    private fun frameRateMatchingModeKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "frame_rate_matching_mode")
    private fun autoPlayNextKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "auto_play_next")
    private fun autoPlaySingleSourceKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "auto_play_single_source")
    private fun autoPlayMinQualityKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "auto_play_min_quality")
    private fun includeSpecialsKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "include_specials")
    private fun dnsProviderKey() = profileManager.profileStringKey("dns_provider")
    private fun subtitleUsageKey() = profileManager.profileStringKey("subtitle_usage_v1")
    private fun subtitleSettingsUpdatedAtKey() = profileManager.profileStringKey("subtitle_settings_updated_at")

    // Global AI subtitle keys (non-profile-scoped, device-wide)
    private val subtitleAiEnabledKey = androidx.datastore.preferences.core.booleanPreferencesKey("subtitle_ai_enabled")
    private val subtitleAiAutoSelectKey = androidx.datastore.preferences.core.booleanPreferencesKey("subtitle_ai_auto_select")
    private val subtitleAiApiKeyKey = androidx.datastore.preferences.core.stringPreferencesKey("subtitle_ai_api_key")
    private val subtitleAiModelKey = androidx.datastore.preferences.core.stringPreferencesKey("subtitle_ai_model")
    private val subtitleRemoveHearingImpairedKey = androidx.datastore.preferences.core.booleanPreferencesKey("subtitle_remove_hearing_impaired")

    // Active-profile key shortcuts (used for legacy flat fields in snapshot)
    private fun defaultSubtitleKey() = profileManager.profileStringKey("default_subtitle")
    private fun defaultAudioLanguageKey() = profileManager.profileStringKey("default_audio_language")
    private fun cardLayoutModeKey() = profileManager.profileStringKey("card_layout_mode")
    private fun frameRateMatchingModeKey() = profileManager.profileStringKey("frame_rate_matching_mode")
    private fun autoPlayNextKey() = profileManager.profileBooleanKey("auto_play_next")
    private fun autoPlaySingleSourceKey() = profileManager.profileBooleanKey("auto_play_single_source")
    private fun autoPlayMinQualityKey() = profileManager.profileStringKey("auto_play_min_quality")
    private fun includeSpecialsKey() = profileManager.profileBooleanKey("include_specials")

    private fun catalogueRowLayoutModesForProfile(
        prefs: androidx.datastore.preferences.core.Preferences,
        profileId: String
    ): Map<String, String> {
        val prefix = catalogueRowLayoutPreferencePrefixFor(profileId)
        return prefs.asMap()
            .mapNotNull { (key, value) ->
                val rowKey = catalogueRowLayoutKeyFromPreferenceName(profileId, key.name)
                    ?: return@mapNotNull null
                val normalized = normalizeCardLayoutMode(value as? String)
                if (key.name.startsWith(prefix)) rowKey to normalized else null
            }
            .toMap()
    }

    // ── Normalize helpers ──

    private fun normalizeFrameRateMode(raw: String?): String {
        return when (raw?.trim()?.lowercase()) {
            "off" -> "Off"
            "seamless", "seamless only", "only if seamless", "only_if_seamless" -> "Seamless only"
            "always" -> "Always"
            else -> "Off"
        }
    }

    private fun normalizeAutoPlayMinQuality(raw: String?): String {
        return when (raw?.trim()?.lowercase()) {
            "any" -> "Any"
            "720p", "hd" -> "720p"
            "1080p", "fullhd", "fhd" -> "1080p"
            "4k", "2160p", "uhd" -> "4K"
            else -> "Any"
        }
    }

    // ══════════════════════════════════════════════════════════
    //  BUILD CLOUD SNAPSHOT JSON
    // ══════════════════════════════════════════════════════════

    /**
     * Builds the full cloud snapshot JSON for all profiles.
     * This replaces the SettingsViewModel version and does NOT reference any UI state.
     */
    suspend fun buildCloudSnapshotJson(): String {
        val prefs = context.settingsDataStore.data.first()
        val root = JSONObject()
        val profiles = profileRepository.getProfiles()
        val globalDnsProvider = prefs[globalDnsProviderKey] ?: prefs[dnsProviderKey()] ?: "system"

        // Per-profile settings
        val profileSettingsById = buildMap<String, CloudProfileSettings> {
            profiles.forEach { profile ->
                put(
                    profile.id,
                    CloudProfileSettings(
                        defaultSubtitle = prefs[defaultSubtitleKeyFor(profile.id)] ?: "Off",
                        defaultAudioLanguage = prefs[defaultAudioLanguageKeyFor(profile.id)] ?: "Auto (Original)",
                        contentLanguage = prefs[contentLanguageKeyFor(profile.id)] ?: "en-US",

                        trailerAutoPlay = prefs[trailerAutoPlayKeyFor(profile.id)] ?: false,
                        trailerSoundEnabled = prefs[trailerSoundEnabledKeyFor(profile.id)] ?: false,
                        trailerDelaySeconds = prefs[trailerDelayKeyFor(profile.id)]?.toIntOrNull() ?: 2,
                        trailerInCards = prefs[trailerInCardsKeyFor(profile.id)] ?: true,
                        clockFormat = prefs[clockFormatKeyFor(profile.id)] ?: "24h",
                        showBudget = prefs[showBudgetKeyFor(profile.id)] ?: true,
                        showLoadingStats = prefs[showLoadingStatsKeyFor(profile.id)] ?: true,
                        spoilerBlurEnabled = prefs[spoilerBlurKeyFor(profile.id)] ?: false,
                        volumeBoostDb = prefs[volumeBoostDbKeyFor(profile.id)]?.toIntOrNull()?.coerceIn(0, 15) ?: 0,
                        dnsProvider = prefs[dnsProviderKeyFor(profile.id)] ?: globalDnsProvider,
                        subtitleUsageJson = prefs[subtitleUsageKeyFor(profile.id)] ?: "",
                        subtitleSettingsUpdatedAt = prefs[subtitleSettingsUpdatedAtKeyFor(profile.id)]?.toLongOrNull() ?: 0L,
                        subtitleSize = prefs[subtitleSizeKeyFor(profile.id)] ?: "Medium",
                        subtitleColor = prefs[subtitleColorKeyFor(profile.id)] ?: "White",
                        subtitleOffset = prefs[subtitleOffsetKeyFor(profile.id)] ?: "Bottom",
                        subtitleStyle = prefs[subtitleStyleKeyFor(profile.id)] ?: "Bold",
                        subtitleStylized = prefs[subtitleStylizedKeyFor(profile.id)] ?: true,
                        secondarySubtitle = prefs[secondarySubtitleKeyFor(profile.id)] ?: "Off",
                        filterSubtitlesByLanguage = prefs[filterSubtitlesByLanguageKeyFor(profile.id)] ?: true,
                        homeServerConnectionJson = homeServerRepository.exportCloudConnectionsJsonForProfile(profile.id),
                        torrServerBaseUrl = streamRepository.exportTorrServerBaseUrlForProfile(profile.id),
                        catalogueRowLayoutModes = catalogueRowLayoutModesForProfile(prefs, profile.id),
                        cardLayoutMode = normalizeCardLayoutMode(
                            prefs[cardLayoutModeKeyFor(profile.id)] ?: CARD_LAYOUT_MODE_LANDSCAPE
                        ),
                        frameRateMatchingMode = normalizeFrameRateMode(
                            prefs[frameRateMatchingModeKeyFor(profile.id)] ?: "Off"
                        ),
                        autoPlayNext = prefs[autoPlayNextKeyFor(profile.id)] ?: true,
                        autoPlaySingleSource = prefs[autoPlaySingleSourceKeyFor(profile.id)] ?: true,
                        autoPlayMinQuality = normalizeAutoPlayMinQuality(
                            prefs[autoPlayMinQualityKeyFor(profile.id)] ?: "Any"
                        ),
                        includeSpecials = prefs[includeSpecialsKeyFor(profile.id)] ?: false
                    )
                )
            }
        }

        root.put("version", 1)
        root.put("updatedAt", System.currentTimeMillis())
        // Legacy flat fields (for backward compat with older clients)
        root.put("defaultSubtitle", prefs[defaultSubtitleKey()] ?: "Off")
        root.put("defaultAudioLanguage", prefs[defaultAudioLanguageKey()] ?: "Auto (Original)")
        root.put("cardLayoutMode", normalizeCardLayoutMode(prefs[cardLayoutModeKey()] ?: CARD_LAYOUT_MODE_LANDSCAPE))
        root.put("frameRateMatchingMode", prefs[frameRateMatchingModeKey()] ?: "Off")
        root.put("autoPlayNext", prefs[autoPlayNextKey()] ?: true)
        root.put("autoPlaySingleSource", prefs[autoPlaySingleSourceKey()] ?: true)
        root.put("autoPlayMinQuality", normalizeAutoPlayMinQuality(prefs[autoPlayMinQualityKey()] ?: "Any"))
        root.put("includeSpecials", prefs[includeSpecialsKey()] ?: false)
        root.put("dnsProvider", globalDnsProvider)
        root.put("customUserAgent", prefs[customUserAgentKey] ?: "")
        root.put("oledBlackBackground", prefs[OLED_BLACK_BACKGROUND_KEY] ?: false)
        val accentColor = prefs[ACCENT_COLOR_KEY] ?: "White"
        root.put("accentColor", accentColor)
        root.put("focusBorderColor", accentColor)
        root.put("subtitleUsageJson", prefs[subtitleUsageKey()] ?: "")
        root.put("subtitleSettingsUpdatedAt", prefs[subtitleSettingsUpdatedAtKey()]?.toLongOrNull() ?: 0L)
        root.put("skipProfileSelection", prefs[SKIP_PROFILE_SELECTION_KEY] ?: false)

        // Global AI subtitle settings (non-profile-scoped)
        root.put("subtitleAiEnabled", prefs[subtitleAiEnabledKey] ?: false)
        root.put("subtitleAiAutoSelect", prefs[subtitleAiAutoSelectKey] ?: false)
        root.put("subtitleAiApiKey", prefs[subtitleAiApiKeyKey] ?: "")
        root.put("subtitleAiModel", prefs[subtitleAiModelKey] ?: "GROQ_LLAMA_70B")
        root.put("subtitleRemoveHearingImpaired", prefs[subtitleRemoveHearingImpairedKey] ?: true)

        root.put("activeProfileId", profileRepository.getActiveProfileId() ?: JSONObject.NULL)
        root.put("profiles", JSONArray(gson.toJson(profiles)))
        val existingAvatarImagesById = authRepository.loadAccountSyncPayload()
            .getOrNull()
            ?.takeIf { it.isNotBlank() }
            ?.let { payload ->
                runCatching {
                    JSONObject(payload).optJSONObject("profileAvatarImagesById")
                }.getOrNull()
            }
        root.put(
            "profileAvatarImagesById",
            profileAvatarImageManager.buildInlineAvatarImagesJson(profiles, existingAvatarImagesById)
        )
        root.put("profileSettingsById", JSONObject(gson.toJson(profileSettingsById)))

        // Validate active Trakt auth before exporting so revoked tokens do not
        // get written back to cloud and restored on the next launch.
        runCatching { traktRepository.hasTrakt() }

        // Trakt tokens per profile
        val traktTokens = traktRepository.exportTokensForProfiles(profiles.map { it.id })
        root.put("traktTokens", JSONObject(gson.toJson(traktTokens)))

        // Dismissed Continue Watching keys per profile (persist hide/remove state)
        val dismissedContinueWatchingByProfile =
            traktRepository.exportDismissedContinueWatchingForProfiles(profiles.map { it.id })
        root.put(
            "dismissedContinueWatchingByProfile",
            JSONObject(gson.toJson(dismissedContinueWatchingByProfile))
        )

        val localContinueWatchingByProfile =
            traktRepository.exportLocalContinueWatchingForProfiles(profiles.map { it.id })
        root.put(
            "localContinueWatchingByProfile",
            JSONObject(gson.toJson(localContinueWatchingByProfile))
        )

        val localWatchedMoviesByProfile =
            traktRepository.exportLocalWatchedMoviesForProfiles(profiles.map { it.id })
        root.put(
            "localWatchedMoviesByProfile",
            JSONObject(gson.toJson(localWatchedMoviesByProfile))
        )

        val localWatchedEpisodesByProfile =
            traktRepository.exportLocalWatchedEpisodesForProfiles(profiles.map { it.id })
        root.put(
            "localWatchedEpisodesByProfile",
            JSONObject(gson.toJson(localWatchedEpisodesByProfile))
        )

        // Addons are shared account state. Keep the per-profile payload shape
        // for older clients, but each profile receives the same shared list.
        val sharedAddons = streamRepository.installedAddons.first()
        val addonsByProfile = buildMap<String, List<Addon>> {
            profiles.forEach { profile ->
                put(profile.id, sharedAddons)
            }
        }
        root.put("addonsByProfile", JSONObject(gson.toJson(addonsByProfile)))

        // Catalogs per profile
        val catalogsByProfile = buildMap<String, List<CatalogConfig>> {
            profiles.forEach { profile ->
                put(profile.id, catalogRepository.getCatalogsForProfile(profile.id))
            }
        }
        root.put("catalogsByProfile", JSONObject(gson.toJson(catalogsByProfile)))

        // Hidden preinstalled catalogs per profile
        val hiddenPreinstalledByProfile = buildMap<String, List<String>> {
            profiles.forEach { profile ->
                put(profile.id, catalogRepository.getHiddenPreinstalledCatalogIdsForProfile(profile.id))
            }
        }
        root.put("hiddenPreinstalledByProfile", JSONObject(gson.toJson(hiddenPreinstalledByProfile)))

        // Hidden addon catalogs per profile — without this, a deletion on one
        // device is undone by another device's next addon sync.
        val hiddenAddonByProfile = buildMap<String, List<String>> {
            profiles.forEach { profile ->
                put(profile.id, catalogRepository.getHiddenAddonCatalogIdsForProfile(profile.id))
            }
        }
        root.put("hiddenAddonByProfile", JSONObject(gson.toJson(hiddenAddonByProfile)))

        val hiddenHomeServerByProfile = buildMap<String, List<String>> {
            profiles.forEach { profile ->
                put(profile.id, catalogRepository.getHiddenHomeServerCatalogIdsForProfile(profile.id))
            }
        }
        root.put("hiddenHomeServerByProfile", JSONObject(gson.toJson(hiddenHomeServerByProfile)))

        // IPTV config per profile (including favorites)
        val iptvByProfile = buildMap<String, IptvCloudProfileState> {
            profiles.forEach { profile ->
                put(profile.id, iptvRepository.exportCloudConfigForProfile(profile.id))
            }
        }
        root.put("iptvByProfile", JSONObject(gson.toJson(iptvByProfile)))

        // Watchlist per profile
        val watchlistByProfile = buildMap<String, List<LocalWatchlistItem>> {
            profiles.forEach { profile ->
                put(profile.id, watchlistRepository.exportWatchlistForProfile(profile.id))
            }
        }
        root.put("watchlistByProfile", JSONObject(gson.toJson(watchlistByProfile)))

        // Backward compatibility fields (legacy single-profile clients)
        root.put("addons", JSONArray(gson.toJson(sharedAddons)))
        root.put("catalogs", JSONArray(gson.toJson(catalogRepository.getCatalogs())))
        root.put(
            "hiddenPreinstalledCatalogs",
            JSONArray(gson.toJson(catalogRepository.getHiddenPreinstalledCatalogIdsForActiveProfile()))
        )
        val iptvConfig = iptvRepository.observeConfig().first()
        root.put("iptvM3uUrl", iptvConfig.m3uUrl)
        root.put("iptvEpgUrl", iptvConfig.epgUrl)
        root.put("iptvFavoriteGroups", JSONArray(gson.toJson(iptvRepository.observeFavoriteGroups().first())))
        root.put("iptvFavoriteChannels", JSONArray(gson.toJson(iptvRepository.observeFavoriteChannels().first())))

        // Plugin repositories and scrapers (sideload flavor)
        runCatching {
            val pluginRepos = pluginDataStore.repositories.first()
            val pluginScrapers = pluginDataStore.scrapers.first()
            val pluginsEnabled = pluginDataStore.pluginsEnabled.first()
            root.put("pluginRepositories", JSONArray(gson.toJson(pluginRepos)))
            root.put("pluginScrapers", JSONArray(gson.toJson(pluginScrapers)))
            root.put("pluginsEnabled", pluginsEnabled)
        }

        // Informational
        val isTraktLinked = traktRepository.hasTrakt()
        root.put("traktLinked", isTraktLinked)
        root.put("traktExpiration", JSONObject.NULL)

        return root.toString()
    }

    @Volatile
    private var lastPushedPayloadHash: Int? = null

    @Volatile
    var pushFailureCount: Int = 0
        private set

    @Volatile
    private var lastPushAttemptAt: Long = 0L

    // ══════════════════════════════════════════════════════════
    //  PUSH LOCAL STATE TO CLOUD
    // ══════════════════════════════════════════════════════════

    suspend fun pushToCloud(force: Boolean = false): Result<Unit> = cloudSyncMutex.withLock {
        pushToCloudLocked(force = force)
    }

    suspend fun pushLocalSnapshotToCloud(): Result<Unit> = cloudSyncMutex.withLock {
        pushToCloudLocked(force = true, allowRemoteRestoreBeforePush = false)
    }

    private suspend fun pushToCloudLocked(
        force: Boolean = false,
        allowRemoteRestoreBeforePush: Boolean = true
    ): Result<Unit> {
        val now = System.currentTimeMillis()
        if (!force && pushFailureCount > 0) {
            val requiredBackoffMs = (2_000L * (1 shl (pushFailureCount - 1).coerceAtMost(6))).coerceAtMost(300_000L)
            if (now - lastPushAttemptAt < requiredBackoffMs) {
                AppLogger.breadcrumb(
                    tag = "CloudSync",
                    message = "push_skipped_backoff delay=${requiredBackoffMs}",
                    severity = "info"
                )
                return Result.failure(IllegalStateException("Exponential backoff active: wait ${requiredBackoffMs}ms"))
            }
        }
        lastPushAttemptAt = now

        val userId = authRepository.getCurrentUserIdForSync()
        if (userId.isNullOrBlank()) {
            Log.w(TAG, "Push skipped: no valid cloud user session dirty=$isPushDirty force=$force")
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "push_skipped_not_logged_in dirty=$isPushDirty",
                severity = "warning"
            )
            return Result.failure(IllegalStateException("Not logged in"))
        }
        val payload = runCatching { buildCloudSnapshotJson() }.getOrElse {
            markPushFailedDirty()
            pushFailureCount++
            AppLogger.recordException(
                throwable = it,
                context = mapOf(
                    "error_area" to "CloudSync",
                    "cloud_flow" to "push_build_payload",
                    "dirty" to isPushDirty.toString()
                )
            )
            return Result.failure(it)
        }

        val existingRemotePayload = authRepository.loadAccountSyncPayload()
            .getOrNull()
            ?.takeIf { it.isNotBlank() }
        if (
            allowRemoteRestoreBeforePush &&
            existingRemotePayload != null &&
            shouldRestoreRemoteBeforePush(payload, existingRemotePayload)
        ) {
            val localProfileCount = cloudPayloadProfileCount(payload)
            val remoteProfileCount = cloudPayloadProfileCount(existingRemotePayload)
            Log.w(
                TAG,
                "Push blocked because remote snapshot is richer; local_profiles=$localProfileCount remote_profiles=$remoteProfileCount"
            )
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "push_blocked_remote_richer local_profiles=$localProfileCount remote_profiles=$remoteProfileCount",
                severity = "warning"
            )
            return runCatching {
                invalidationBus.suppressDuringRemoteApply {
                    clearStaleLocalDirtyBeforeRemoteRestore()
                    applyCloudPayload(existingRemotePayload)
                }
                markCloudPayloadApplied(existingRemotePayload, existingRemotePayload.hashCode())
                clearLocalDirtyAfterSuccessfulPush()
            }.fold(
                onSuccess = {
                    Log.i(TAG, "Restored richer remote snapshot before push")
                    Result.success(Unit)
                },
                onFailure = { error ->
                    markPushFailedDirty()
                    pushFailureCount++
                    Log.w(TAG, "Failed to restore richer remote snapshot before push: ${error.message}", error)
                    Result.failure(error)
                }
            )
        }

        val effectivePayload = if (existingRemotePayload != null && !iptvRepository.isGroupOrderLocallyDirty()) {
            mergeRemoteGroupOrder(payload, existingRemotePayload)
        } else {
            payload
        }

        val payloadHash = runCatching {
            JSONObject(effectivePayload).apply { remove("updatedAt") }.toString().hashCode()
        }.getOrNull()

        if (!force && payloadHash != null && payloadHash == lastPushedPayloadHash && !isPushDirty && pushFailureCount == 0) {
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "push_skipped_duplicate_hash",
                severity = "info"
            )
            return Result.success(Unit)
        }

        val result = authRepository.saveAccountSyncPayload(effectivePayload)
        if (result.isSuccess) {
            clearLocalDirtyAfterSuccessfulPush()
            lastPushedPayloadHash = payloadHash
            pushFailureCount = 0
            Log.i(TAG, "Push succeeded size=${payloadSizeBucket(effectivePayload)}")
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "push_success size=${payloadSizeBucket(effectivePayload)} user=${userId.take(8)}",
                severity = "info"
            )
            onPushCompleted?.invoke()
        } else {
            // Mark dirty so the next ON_RESUME or periodic sync retries the push.
            // Without this, a single network hiccup would permanently diverge the
            // cloud state until the user explicitly changes another setting.
            markPushFailedDirty()
            pushFailureCount++
            Log.w(
                TAG,
                "Push failed size=${payloadSizeBucket(effectivePayload)} failures=$pushFailureCount error=${result.exceptionOrNull()?.message}"
            )
            AppLogger.recordException(
                throwable = result.exceptionOrNull() ?: IllegalStateException("Cloud push failed"),
                context = mapOf(
                    "error_area" to "CloudSync",
                    "cloud_flow" to "push_save_payload",
                    "dirty" to isPushDirty.toString(),
                    "payload_size" to payloadSizeBucket(effectivePayload),
                    "failure_count" to pushFailureCount.toString()
                )
            )
        }
        return result
    }

    private fun mergeRemoteGroupOrder(localPayload: String, remotePayload: String): String {
        return runCatching {
            val local = JSONObject(localPayload)
            val remote = JSONObject(remotePayload)
            val localByProfile = local.optJSONObject("iptvByProfile") ?: return@runCatching localPayload
            val remoteByProfile = remote.optJSONObject("iptvByProfile") ?: return@runCatching localPayload
            val remoteKeys = remoteByProfile.keys()
            while (remoteKeys.hasNext()) {
                val profileId = remoteKeys.next()
                val remoteProfile = remoteByProfile.optJSONObject(profileId) ?: continue
                val localProfile = localByProfile.optJSONObject(profileId) ?: continue
                val remoteGroupOrder = remoteProfile.optJSONArray("groupOrder") ?: continue
                if (remoteGroupOrder.length() > 0) {
                    localProfile.put("groupOrder", remoteGroupOrder)
                }
            }
            local.toString()
        }.getOrDefault(localPayload)
    }

    // ══════════════════════════════════════════════════════════
    //  PULL CLOUD STATE TO LOCAL
    // ══════════════════════════════════════════════════════════

    /**
     * Restores the full cloud state to local repositories.
     * Returns [RestoreResult] indicating what happened.
     */
    suspend fun pullFromCloud(pushPendingLocalFirst: Boolean = true): RestoreResult = cloudSyncMutex.withLock {
        val hasPendingLocalChanges = hasPendingLocalChanges()
        if (pushPendingLocalFirst && hasPendingLocalChanges) {
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "pull_pushes_pending_local_first",
                severity = "info"
            )
            val pushResult = pushToCloudLocked()
            if (pushResult.isFailure) {
                AppLogger.recordException(
                    throwable = pushResult.exceptionOrNull() ?: IllegalStateException("Pending local cloud push failed"),
                    context = mapOf(
                        "error_area" to "CloudSync",
                        "cloud_flow" to "pull_pre_push_pending_local"
                    )
                )
                return@withLock RestoreResult.FAILED
            }
        } else if (!pushPendingLocalFirst && hasPendingLocalChanges) {
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "pull_remote_first_skips_pending_local_push",
                severity = "info"
            )
        }

        val payloadResult = authRepository.loadAccountSyncPayload()
        if (payloadResult.isFailure) {
            AppLogger.recordException(
                throwable = payloadResult.exceptionOrNull() ?: IllegalStateException("Cloud pull failed"),
                context = mapOf(
                    "error_area" to "CloudSync",
                    "cloud_flow" to "pull_load_payload"
                )
            )
            return@withLock RestoreResult.FAILED
        }

        val payload = payloadResult.getOrNull().orEmpty()
        if (payload.isBlank()) {
            Log.i(TAG, "Pull found no cloud backup")
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "pull_no_backup",
                severity = "info"
            )
            return@withLock RestoreResult.NO_BACKUP
        }

        val remoteRestoreRank = accountSyncPayloadRestoreRank(payload)
        val localRestoreRank = runCatching {
            accountSyncPayloadRestoreRank(buildCloudSnapshotJson())
        }.getOrDefault(0)
        if (localRestoreRank > remoteRestoreRank && remoteRestoreRank <= 10) {
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "pull_remote_placeholder_seed_local local_rank=$localRestoreRank remote_rank=$remoteRestoreRank",
                severity = "warning"
            )
            val pushResult = pushToCloudLocked()
            return@withLock if (pushResult.isSuccess) RestoreResult.RESTORED else RestoreResult.FAILED
        }

        val cloudProfileCount = cloudPayloadProfileCount(payload)
        if (!pushPendingLocalFirst && cloudProfileCount != null && cloudProfileCount <= 0) {
            val localProfiles = profileRepository.getProfiles()
            if (hasMeaningfulLocalProfiles(localProfiles)) {
                AppLogger.breadcrumb(
                    tag = "CloudSync",
                    message = "pull_remote_profiles_empty_seed_local local_profiles=${localProfiles.size}",
                    severity = "warning"
                )
                val pushResult = pushToCloudLocked()
                return@withLock if (pushResult.isSuccess) RestoreResult.RESTORED else RestoreResult.FAILED
            }

            Log.i(TAG, "Pull found cloud backup without usable profiles")
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "pull_remote_profiles_empty_no_restore local_profiles=${localProfiles.size}",
                severity = "warning"
            )
            return@withLock RestoreResult.NO_BACKUP
        }

        if (!pushPendingLocalFirst && cloudProfileCount == null) {
            val localProfiles = profileRepository.getProfiles()
            if (hasMeaningfulLocalProfiles(localProfiles)) {
                AppLogger.breadcrumb(
                    tag = "CloudSync",
                    message = "pull_remote_profiles_missing_seed_local local_profiles=${localProfiles.size}",
                    severity = "warning"
                )
                val pushResult = pushToCloudLocked()
                return@withLock if (pushResult.isSuccess) RestoreResult.RESTORED else RestoreResult.FAILED
            }

            Log.i(TAG, "Pull found legacy cloud backup without profile list")
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "pull_remote_profiles_missing_no_restore local_profiles=${localProfiles.size}",
                severity = "warning"
            )
            return@withLock RestoreResult.NO_BACKUP
        }

        val prefs = context.settingsDataStore.data.first()
        val lastAppliedHash = prefs[androidx.datastore.preferences.core.intPreferencesKey("cloud_sync_last_applied_hash")]
        val payloadHash = payload.hashCode()

        if (lastAppliedHash == payloadHash) {
            Log.i(TAG, "Pull skipped identical payload")
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "pull_skipped_identical_payload",
                severity = "info"
            )
            return@withLock RestoreResult.RESTORED
        }

        runCatching {
            invalidationBus.suppressDuringRemoteApply {
                if (!pushPendingLocalFirst) {
                    clearStaleLocalDirtyBeforeRemoteRestore()
                }
                applyCloudPayload(payload)
            }
            markCloudPayloadApplied(payload, payloadHash)
        }.fold(
            onSuccess = {
                Log.i(TAG, "Pull restored size=${payloadSizeBucket(payload)}")
                AppLogger.breadcrumb(
                    tag = "CloudSync",
                    message = "pull_restored size=${payloadSizeBucket(payload)}",
                    severity = "info"
                )
                RestoreResult.RESTORED
            },
            onFailure = { e ->
                Log.w(TAG, "Pull failed size=${payloadSizeBucket(payload)} error=${e.message}")
                System.err.println("[CLOUD-SYNC] pullFromCloud failed: ${e.message}")
                AppLogger.recordException(
                    throwable = e,
                    context = mapOf(
                        "error_area" to "CloudSync",
                        "cloud_flow" to "pull_apply_payload",
                        "payload_size" to payloadSizeBucket(payload)
                    )
                )
                RestoreResult.FAILED
            }
        )
    }

    /**
     * Applies a cloud JSON payload to all local repositories.
     */
    private suspend fun applyCloudPayload(payload: String) {
        val root = JSONObject(payload)

        val fallbackDefaultSubtitle = root.optString("defaultSubtitle", "Off")
        val fallbackDefaultAudioLanguage = root.optString("defaultAudioLanguage", "Auto (Original)")
        val fallbackCardLayoutMode = normalizeCardLayoutMode(root.optString("cardLayoutMode", CARD_LAYOUT_MODE_LANDSCAPE))
        val fallbackFrameRateMatchingMode = normalizeFrameRateMode(root.optString("frameRateMatchingMode", "Off"))
        val fallbackAutoPlayNext = root.optBoolean("autoPlayNext", true)
        val fallbackAutoPlaySingleSource = root.optBoolean("autoPlaySingleSource", true)
        val fallbackAutoPlayMinQuality = normalizeAutoPlayMinQuality(root.optString("autoPlayMinQuality", "Any"))
        val fallbackIncludeSpecials = root.optBoolean("includeSpecials", false)
        val fallbackSubtitleSettingsUpdatedAt = root.optLong("subtitleSettingsUpdatedAt", 0L)
        var preservedNewerLocalSubtitle = false

        // ── Profiles ──
        val avatarImagesById = root.optJSONObject("profileAvatarImagesById")
        root.optJSONArray("profiles")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = TypeToken.getParameterized(List::class.java, Profile::class.java).type
            val profiles: List<Profile> = gson.fromJson(json, type) ?: emptyList()
            val activeProfileId = root.optString("activeProfileId").ifBlank { null }
            if (profiles.isNotEmpty()) {
                val localProfilesById = profileRepository.getProfiles().associateBy { it.id }
                val fallbackAvatarVersion = root.optLong("updatedAt", System.currentTimeMillis())
                    .takeIf { it > 0L }
                    ?: System.currentTimeMillis()
                val restoredProfiles = profiles.map { profile ->
                    val inlineAvatar = avatarImagesById
                        ?.optString(profile.id)
                        ?.takeIf { it.isNotBlank() }
                    val localProfile = localProfilesById[profile.id]
                    val profileWithAvatarState = when {
                        profile.avatarImageVersion > 0L -> profile
                        localProfile != null && localProfile.avatarImageVersion > 0L -> {
                            profile.copy(
                                avatarId = 0,
                                avatarImageVersion = localProfile.avatarImageVersion,
                                avatarImageStoragePath = localProfile.avatarImageStoragePath
                            )
                        }
                        !inlineAvatar.isNullOrBlank() -> {
                            profile.copy(
                                avatarId = 0,
                                avatarImageVersion = fallbackAvatarVersion,
                                avatarImageStoragePath = null
                            )
                        }
                        else -> profile
                    }
                    profileAvatarImageManager.restoreAvatarIfNeeded(profileWithAvatarState, inlineAvatar)
                    profileWithAvatarState
                }
                // Preserve local active profile if it exists in cloud set
                val localActiveId = profileRepository.getActiveProfileId()
                val effectiveActiveId = if (localActiveId != null &&
                    restoredProfiles.any { it.id == localActiveId }
                ) localActiveId else activeProfileId
                profileRepository.replaceProfilesFromCloud(restoredProfiles, effectiveActiveId)
                val effectiveProfile = restoredProfiles.firstOrNull { it.id == effectiveActiveId }
                if (effectiveProfile != null) {
                    profileManager.setCurrentProfileId(effectiveProfile.id)
                    profileManager.setCurrentProfileName(effectiveProfile.name)
                }
            }
        }

        val allProfiles = profileRepository.getProfiles()
        val activeProfileId = profileRepository.getActiveProfileId()
            ?: allProfiles.firstOrNull()?.id ?: "default"
        val homeServerConnectionsToImport = linkedMapOf<String, String?>()
        val torrServerUrlsToImport = linkedMapOf<String, String?>()

        // ── Per-profile settings ──
        root.optJSONObject("profileSettingsById")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = TypeToken.getParameterized(Map::class.java, String::class.java, CloudProfileSettings::class.java).type
            val settingsByProfile: Map<String, CloudProfileSettings> = gson.fromJson(json, type) ?: emptyMap()
            if (settingsByProfile.isNotEmpty()) {
                context.settingsDataStore.edit { prefs ->
                    settingsByProfile.forEach { (profileId, state) ->
                        val defaultSubtitleKey = defaultSubtitleKeyFor(profileId)
                        val subtitleUpdatedAtKey = subtitleSettingsUpdatedAtKeyFor(profileId)
                        val localSubtitle = prefs[defaultSubtitleKey]?.trim().orEmpty()
                        val localSubtitleUpdatedAt = prefs[subtitleUpdatedAtKey]?.toLongOrNull() ?: 0L
                        val cloudSubtitle = state.defaultSubtitle.trim().ifBlank { "Off" }
                        val keepLocalSubtitle = localSubtitle.isNotBlank() &&
                            !localSubtitle.equals(cloudSubtitle, ignoreCase = true) &&
                            (
                                localSubtitleUpdatedAt > state.subtitleSettingsUpdatedAt ||
                                    (
                                        state.subtitleSettingsUpdatedAt <= 0L &&
                                            cloudSubtitle.equals("Off", ignoreCase = true) &&
                                            !localSubtitle.equals("Off", ignoreCase = true)
                                        )
                                )

                        if (keepLocalSubtitle) {
                            preservedNewerLocalSubtitle = true
                        } else {
                            prefs[defaultSubtitleKey] = cloudSubtitle
                            if (state.subtitleSettingsUpdatedAt > 0L) {
                                prefs[subtitleUpdatedAtKey] = state.subtitleSettingsUpdatedAt.toString()
                            }
                        }
                        prefs[defaultAudioLanguageKeyFor(profileId)] = state.defaultAudioLanguage
                        prefs[contentLanguageKeyFor(profileId)] = state.contentLanguage
                        if (profileId == activeProfileId) {
                            prefs[LAST_APP_LANGUAGE_KEY] = state.contentLanguage
                            context.getSharedPreferences("app_locale", Context.MODE_PRIVATE)
                                .edit().putString("locale_tag", state.contentLanguage).apply()
                        }

                        prefs[trailerAutoPlayKeyFor(profileId)] = state.trailerAutoPlay
                        prefs[trailerSoundEnabledKeyFor(profileId)] = state.trailerSoundEnabled
                        prefs[trailerDelayKeyFor(profileId)] = state.trailerDelaySeconds.toString()
                        prefs[trailerInCardsKeyFor(profileId)] = state.trailerInCards
                        prefs[clockFormatKeyFor(profileId)] = state.clockFormat
                        prefs[showBudgetKeyFor(profileId)] = state.showBudget
                        state.showLoadingStats?.let { prefs[showLoadingStatsKeyFor(profileId)] = it }
                        prefs[spoilerBlurKeyFor(profileId)] = state.spoilerBlurEnabled
                        prefs[volumeBoostDbKeyFor(profileId)] = state.volumeBoostDb.coerceIn(0, 15).toString()
                        prefs[dnsProviderKeyFor(profileId)] = state.dnsProvider.ifBlank { "system" }
                        if (state.subtitleUsageJson.isBlank()) {
                            prefs.remove(subtitleUsageKeyFor(profileId))
                        } else {
                            prefs[subtitleUsageKeyFor(profileId)] = state.subtitleUsageJson
                        }
                        prefs[subtitleSizeKeyFor(profileId)] = state.subtitleSize
                        prefs[subtitleColorKeyFor(profileId)] = state.subtitleColor
                        prefs[subtitleOffsetKeyFor(profileId)] = state.subtitleOffset
                        prefs[subtitleStyleKeyFor(profileId)] = state.subtitleStyle
                        prefs[subtitleStylizedKeyFor(profileId)] = state.subtitleStylized
                        prefs[secondarySubtitleKeyFor(profileId)] = state.secondarySubtitle.ifBlank { "Off" }
                        prefs[filterSubtitlesByLanguageKeyFor(profileId)] = state.filterSubtitlesByLanguage
                        state.homeServerConnectionJson?.let { homeServerConnectionJson ->
                            homeServerConnectionsToImport[profileId] = homeServerConnectionJson
                        }
                        state.torrServerBaseUrl?.let { torrServerBaseUrl ->
                            torrServerUrlsToImport[profileId] = torrServerBaseUrl
                        }
                        val normalizedProfileLayout = normalizeCardLayoutMode(state.cardLayoutMode)
                        prefs[cardLayoutModeKeyFor(profileId)] = normalizedProfileLayout
                        state.catalogueRowLayoutModes.forEach { (rowKey, mode) ->
                            prefs[profileCatalogueRowLayoutModeKey(profileId, rowKey)] = normalizeCardLayoutMode(mode)
                        }
                        prefs[frameRateMatchingModeKeyFor(profileId)] = normalizeFrameRateMode(state.frameRateMatchingMode)
                        prefs[autoPlayNextKeyFor(profileId)] = state.autoPlayNext
                        prefs[autoPlaySingleSourceKeyFor(profileId)] = state.autoPlaySingleSource
                        prefs[autoPlayMinQualityKeyFor(profileId)] = normalizeAutoPlayMinQuality(state.autoPlayMinQuality)
                        prefs[includeSpecialsKeyFor(profileId)] = state.includeSpecials
                    }
                }
            }
        } ?: run {
            // Legacy fallback: single-profile settings
            context.settingsDataStore.edit { prefs ->
                val defaultSubtitleKey = defaultSubtitleKeyFor(activeProfileId)
                val subtitleUpdatedAtKey = subtitleSettingsUpdatedAtKeyFor(activeProfileId)
                val localSubtitle = prefs[defaultSubtitleKey]?.trim().orEmpty()
                val localSubtitleUpdatedAt = prefs[subtitleUpdatedAtKey]?.toLongOrNull() ?: 0L
                val cloudSubtitle = fallbackDefaultSubtitle.trim().ifBlank { "Off" }
                val keepLocalSubtitle = localSubtitle.isNotBlank() &&
                    !localSubtitle.equals(cloudSubtitle, ignoreCase = true) &&
                    (
                        localSubtitleUpdatedAt > fallbackSubtitleSettingsUpdatedAt ||
                            (
                                fallbackSubtitleSettingsUpdatedAt <= 0L &&
                                    cloudSubtitle.equals("Off", ignoreCase = true) &&
                                    !localSubtitle.equals("Off", ignoreCase = true)
                                )
                            )
                if (keepLocalSubtitle) {
                    preservedNewerLocalSubtitle = true
                } else {
                    prefs[defaultSubtitleKey] = cloudSubtitle
                    if (fallbackSubtitleSettingsUpdatedAt > 0L) {
                        prefs[subtitleUpdatedAtKey] = fallbackSubtitleSettingsUpdatedAt.toString()
                    }
                }
                prefs[defaultAudioLanguageKeyFor(activeProfileId)] = fallbackDefaultAudioLanguage
                prefs[cardLayoutModeKeyFor(activeProfileId)] = fallbackCardLayoutMode
                prefs[frameRateMatchingModeKeyFor(activeProfileId)] = fallbackFrameRateMatchingMode
                prefs[autoPlayNextKeyFor(activeProfileId)] = fallbackAutoPlayNext
                prefs[autoPlaySingleSourceKeyFor(activeProfileId)] = fallbackAutoPlaySingleSource
                prefs[autoPlayMinQualityKeyFor(activeProfileId)] = fallbackAutoPlayMinQuality
                prefs[includeSpecialsKeyFor(activeProfileId)] = fallbackIncludeSpecials
                prefs[dnsProviderKeyFor(activeProfileId)] = root.optString("dnsProvider", "system").ifBlank { "system" }
                root.optString("subtitleUsageJson", "").let { usage ->
                    if (usage.isBlank()) prefs.remove(subtitleUsageKeyFor(activeProfileId)) else prefs[subtitleUsageKeyFor(activeProfileId)] = usage
                }
            }
        }
        homeServerConnectionsToImport.forEach { (profileId, json) ->
            homeServerRepository.importCloudConnectionsJsonForProfile(profileId, json)
        }
        torrServerUrlsToImport.forEach { (profileId, url) ->
            streamRepository.importTorrServerBaseUrlForProfile(profileId, url)
        }

        var restoredDnsProvider: String? = null
        var restoredCustomUserAgent: String? = null
        if (
            root.has("dnsProvider") ||
            root.has("customUserAgent") ||
            root.has("oledBlackBackground") ||
            root.has("accentColor") ||
            root.has("focusBorderColor")
        ) {
            context.settingsDataStore.edit { prefs ->
                if (root.has("dnsProvider")) {
                    val dnsProvider = root.optString("dnsProvider", "system").ifBlank { "system" }
                    prefs[globalDnsProviderKey] = dnsProvider
                    prefs[dnsProviderKeyFor(activeProfileId)] = dnsProvider
                    restoredDnsProvider = dnsProvider
                }
                if (root.has("customUserAgent")) {
                    val userAgent = root.optString("customUserAgent", "").trim()
                    if (userAgent.isBlank()) {
                        prefs.remove(customUserAgentKey)
                    } else {
                        prefs[customUserAgentKey] = userAgent
                    }
                    restoredCustomUserAgent = userAgent
                }
                if (root.has("oledBlackBackground")) {
                    prefs[OLED_BLACK_BACKGROUND_KEY] = root.optBoolean("oledBlackBackground", false)
                }
                if (root.has("accentColor") || root.has("focusBorderColor")) {
                    prefs[ACCENT_COLOR_KEY] = root.optString(
                        "accentColor",
                        root.optString("focusBorderColor", "White")
                    ).ifBlank { "White" }
                }
            }
            restoredDnsProvider?.let { OkHttpProvider.setDnsProvider(OkHttpProvider.parseDnsProvider(it)) }
            restoredCustomUserAgent?.let { OkHttpProvider.setCustomUserAgent(it) }
        }
        if (root.has("skipProfileSelection")) {
            context.settingsDataStore.edit { prefs ->
                prefs[SKIP_PROFILE_SELECTION_KEY] = root.optBoolean("skipProfileSelection", false)
            }
        }

        // Global AI subtitle settings
        val hasAiSettings = root.has("subtitleAiEnabled") || root.has("subtitleAiApiKey")
        if (hasAiSettings) {
            context.settingsDataStore.edit { prefs ->
                prefs[subtitleAiEnabledKey] = root.optBoolean("subtitleAiEnabled", false)
                prefs[subtitleAiAutoSelectKey] = root.optBoolean("subtitleAiAutoSelect", false)
                val apiKey = root.optString("subtitleAiApiKey", "")
                if (apiKey.isNotBlank()) prefs[subtitleAiApiKeyKey] = apiKey
                val model = root.optString("subtitleAiModel", "GROQ_LLAMA_70B")
                if (model.isNotBlank()) prefs[subtitleAiModelKey] = model
                prefs[subtitleRemoveHearingImpairedKey] = root.optBoolean("subtitleRemoveHearingImpaired", true)
            }
        }
        if (preservedNewerLocalSubtitle) {
            markLocalStateDirty()
        } else {
            authRepository.saveDefaultSubtitleToProfile(fallbackDefaultSubtitle)
        }
        authRepository.saveAutoPlayNextToProfile(fallbackAutoPlayNext)

        // ── Trakt tokens ──
        runCatching {
            root.optJSONObject("traktTokens")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TraktRepository.CloudTraktToken::class.java).type
                val tokens: Map<String, TraktRepository.CloudTraktToken> = gson.fromJson(json, type) ?: emptyMap()
                traktRepository.importTokensForProfiles(tokens)
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_trakt_tokens")) }

        // ── Addons ──
        runCatching {
            var preservedLocalAddon = false
            root.optJSONObject("addonsByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, Addon::class.java).type).type
                val map: Map<String, List<Addon>> = gson.fromJson(json, type) ?: emptyMap()
                val sharedAddons = mergeAddonsForSharedRestore(map.values)
                val localAddons = streamRepository.installedAddons.first()
                val (resolvedAddons, preserved) = mergeCloudAddonsPreservingLocalDirectAddons(sharedAddons, localAddons)
                preservedLocalAddon = preservedLocalAddon || preserved
                if (resolvedAddons.isNotEmpty()) {
                    streamRepository.replaceSharedAddonsFromCloud(resolvedAddons)
                }
            }
            root.optJSONArray("addons")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                if (!root.has("addonsByProfile")) {
                    val type = TypeToken.getParameterized(List::class.java, Addon::class.java).type
                    val addons: List<Addon> = gson.fromJson(json, type) ?: emptyList()
                    val localAddons = streamRepository.installedAddons.first()
                    val (resolvedAddons, preserved) = mergeCloudAddonsPreservingLocalDirectAddons(addons, localAddons)
                    preservedLocalAddon = preservedLocalAddon || preserved
                    if (resolvedAddons.isNotEmpty()) {
                        streamRepository.replaceSharedAddonsFromCloud(resolvedAddons)
                    }
                }
            }
            if (preservedLocalAddon) {
                markLocalStateDirty()
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_addons")) }

        // ── Catalogs ──
        runCatching {
            root.optJSONObject("catalogsByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, CatalogConfig::class.java).type).type
                val map: Map<String, List<CatalogConfig>> = gson.fromJson(json, type) ?: emptyMap()
                map.forEach { (profileId, catalogs) ->
                    catalogRepository.replaceCatalogsForProfile(profileId, catalogs)
                }
            }
            root.optJSONArray("catalogs")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                if (!root.has("catalogsByProfile")) {
                    val type = TypeToken.getParameterized(List::class.java, CatalogConfig::class.java).type
                    val catalogs: List<CatalogConfig> = gson.fromJson(json, type) ?: emptyList()
                    if (catalogs.isNotEmpty()) {
                        catalogRepository.replaceCatalogsForProfile(activeProfileId, catalogs)
                    }
                }
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_catalogs")) }

        // ── Hidden preinstalled catalogs ──
        runCatching {
            root.optJSONObject("hiddenPreinstalledByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, String::class.java).type).type
                val map: Map<String, List<String>> = gson.fromJson(json, type) ?: emptyMap()
                map.forEach { (profileId, hidden) ->
                    catalogRepository.setHiddenPreinstalledCatalogIdsForProfile(profileId, hidden)
                }
            }
            root.optJSONArray("hiddenPreinstalledCatalogs")?.toString()?.let { json ->
                if (!root.has("hiddenPreinstalledByProfile")) {
                    val hidden = if (json.isBlank()) {
                        emptyList()
                    } else {
                        val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                        gson.fromJson<List<String>>(json, type) ?: emptyList()
                    }
                    catalogRepository.setHiddenPreinstalledCatalogIdsForProfile(activeProfileId, hidden)
                }
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_hidden_preinstalled")) }

        // ── Hidden addon catalogs ──
        runCatching {
            root.optJSONObject("hiddenAddonByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, String::class.java).type).type
                val map: Map<String, List<String>> = gson.fromJson(json, type) ?: emptyMap()
                map.forEach { (profileId, hidden) ->
                    catalogRepository.setHiddenAddonCatalogIdsForProfile(profileId, hidden)
                }
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_hidden_addons")) }

        // ── Hidden Home Server catalogs ──
        runCatching {
            root.optJSONObject("hiddenHomeServerByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, String::class.java).type).type
                val map: Map<String, List<String>> = gson.fromJson(json, type) ?: emptyMap()
                map.forEach { (profileId, hidden) ->
                    catalogRepository.setHiddenHomeServerCatalogIdsForProfile(profileId, hidden)
                }
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_hidden_home_server")) }

        // ── IPTV config + favorites ──
        runCatching {
            var importedActiveProfileIptv = false
            root.optJSONObject("iptvByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, IptvCloudProfileState::class.java).type
                val map: Map<String, IptvCloudProfileState> = gson.fromJson(json, type) ?: emptyMap()
                map.forEach { (profileId, state) ->
                    iptvRepository.importCloudConfigForProfile(profileId, state)
                    if (profileId == activeProfileId) {
                        importedActiveProfileIptv = true
                    }
                }
            }

            // Legacy IPTV flat fields (only used if iptvByProfile is absent)
            val cloudHasIptvKeys = root.has("iptvM3uUrl") || root.has("iptvEpgUrl") ||
                root.has("iptvFavoriteGroups") || root.has("iptvFavoriteChannels")
            val m3u = root.optString("iptvM3uUrl")
            val epg = root.optString("iptvEpgUrl")
            val favorites = root.optJSONArray("iptvFavoriteGroups")?.toString().orEmpty().let { j ->
                if (j.isBlank()) emptyList() else {
                    val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                    gson.fromJson<List<String>>(j, type) ?: emptyList()
                }
            }
            val favoriteChannels = root.optJSONArray("iptvFavoriteChannels")?.toString().orEmpty().let { j ->
                if (j.isBlank()) emptyList() else {
                    val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                    gson.fromJson<List<String>>(j, type) ?: emptyList()
                }
            }
            val localIptv = iptvRepository.observeConfig().first()
            val cloudHasIptvData = m3u.isNotBlank() || epg.isNotBlank() || favorites.isNotEmpty() || favoriteChannels.isNotEmpty()
            val localHasIptvData = localIptv.m3uUrl.isNotBlank() || localIptv.epgUrl.isNotBlank()
            var importedLegacyIptv = false
            if (!root.has("iptvByProfile") && cloudHasIptvKeys && (cloudHasIptvData || !localHasIptvData)) {
                iptvRepository.importCloudConfig(m3u, epg, favorites, favoriteChannels)
                importedLegacyIptv = true
            }

            if (importedActiveProfileIptv || importedLegacyIptv) {
                runCatching {
                    iptvRepository.invalidateCache()
                }
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_iptv")) }

        // ── Watchlist ──
        runCatching {
            root.optJSONObject("watchlistByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, LocalWatchlistItem::class.java).type).type
                val map: Map<String, List<LocalWatchlistItem>> = gson.fromJson(json, type) ?: emptyMap()
                map.forEach { (profileId, items) ->
                    // Restore the cloud mirror for every profile, including Trakt profiles.
                    // Trakt remains the source of truth after a successful live sync, but
                    // skipping this cache made fresh installs show an empty watchlist while
                    // auth/network refresh was still settling or failed.
                    watchlistRepository.importWatchlistForProfile(profileId, items)
                }
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_watchlist")) }

        // ── Dismissed Continue Watching ──
        runCatching {
            root.optJSONObject("dismissedContinueWatchingByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, String::class.java).type
                val map: Map<String, String> = gson.fromJson(json, type) ?: emptyMap()
                traktRepository.importDismissedContinueWatchingForProfiles(map)
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_dismissed_cw")) }

        // ── Local Continue Watching ──
        runCatching {
            // Only import local CW for profiles that DON'T have Trakt connected.
            // For Trakt profiles, CW is sourced exclusively from Trakt's progress API.
            root.optJSONObject("localContinueWatchingByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, ContinueWatchingItem::class.java).type).type
                val map: Map<String, List<ContinueWatchingItem>> = gson.fromJson(json, type) ?: emptyMap()
                val traktProfiles = mutableSetOf<String>()

                val traktTokenType = TypeToken.getParameterized(Map::class.java, String::class.java, TraktRepository.CloudTraktToken::class.java).type
                val traktTokens = root.optJSONObject("traktTokens")
                    ?.toString()
                    ?.takeIf { it.isNotBlank() }
                    ?.let { tokenJson ->
                        runCatching {
                            gson.fromJson<Map<String, TraktRepository.CloudTraktToken>>(tokenJson, traktTokenType)
                        }.getOrNull()
                    }
                    .orEmpty()

                traktTokens.forEach { (profileId, token) ->
                    if (profileId.isNotBlank() && token.accessToken.isNotBlank()) {
                        traktProfiles.add(profileId)
                    }
                }

                val isActiveProfileTrakt = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
                val activeProfileIdLocal = profileManager.getProfileIdSync().ifBlank { null }
                if (isActiveProfileTrakt && activeProfileIdLocal != null) {
                    traktProfiles.add(activeProfileIdLocal)
                }

                val nonTraktOnly = map.filterKeys { it !in traktProfiles }
                if (nonTraktOnly.isNotEmpty()) {
                    traktRepository.importLocalContinueWatchingForProfiles(nonTraktOnly)
                }
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_local_cw")) }

        runCatching {
            root.optJSONObject("localWatchedMoviesByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, Int::class.javaObjectType).type).type
                val map: Map<String, List<Int>> = gson.fromJson(json, type) ?: emptyMap()
                traktRepository.importLocalWatchedMoviesForProfiles(map)
            }

            root.optJSONObject("localWatchedEpisodesByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, TypeToken.getParameterized(List::class.java, String::class.java).type).type
                val map: Map<String, List<String>> = gson.fromJson(json, type) ?: emptyMap()
                traktRepository.importLocalWatchedEpisodesForProfiles(map)
            }
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_local_watched")) }

        traktRepository.clearAllProfileCaches()
        watchHistoryRepository.clearProfileCaches()

        // Restore plugin repositories and scrapers
        runCatching {
            root.optJSONArray("pluginRepositories")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = com.google.gson.reflect.TypeToken.getParameterized(List::class.java, com.arflix.tv.domain.model.PluginRepository::class.java).type
                val repos: List<com.arflix.tv.domain.model.PluginRepository> = gson.fromJson(json, type) ?: emptyList()
                if (repos.isNotEmpty()) pluginDataStore.saveRepositories(repos)
            }
            root.optJSONArray("pluginScrapers")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = com.google.gson.reflect.TypeToken.getParameterized(List::class.java, com.arflix.tv.domain.model.ScraperInfo::class.java).type
                val scrapers: List<com.arflix.tv.domain.model.ScraperInfo> = gson.fromJson(json, type) ?: emptyList()
                if (scrapers.isNotEmpty()) pluginDataStore.saveScrapers(scrapers)
            }
            if (root.has("pluginsEnabled")) pluginDataStore.setPluginsEnabled(root.optBoolean("pluginsEnabled", false))
        }.onFailure { AppLogger.recordException(it, mapOf("error_area" to "CloudSync", "cloud_flow" to "apply_plugins")) }

        System.err.println("[CLOUD-SYNC] Full cloud restore applied successfully")
    }
}
