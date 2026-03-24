package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.ui.components.CARD_LAYOUT_MODE_LANDSCAPE
import com.arflix.tv.ui.components.normalizeCardLayoutMode
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

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
    private val traktRepository: TraktRepository,
    private val watchlistRepository: WatchlistRepository
) {
    private val gson = Gson()

    /** Callback invoked after a successful push so realtime listeners can skip the echo. */
    var onPushCompleted: (() -> Unit)? = null

    enum class RestoreResult { RESTORED, NO_BACKUP, FAILED }

    // ── Data class for per-profile settings stored in cloud ──

    data class CloudProfileSettings(
        val defaultSubtitle: String = "Off",
        val defaultAudioLanguage: String = "Auto (Original)",
        val contentLanguage: String = "en-US",
        val cardLayoutMode: String = CARD_LAYOUT_MODE_LANDSCAPE,
        val frameRateMatchingMode: String = "Off",
        val autoPlayNext: Boolean = true,
        val autoPlaySingleSource: Boolean = true,
        val autoPlayMinQuality: String = "Any",
        val autoPlayMaxQuality: String = "4K",
        val includeSpecials: Boolean = false
    )

    // ── DataStore key helpers ──

    private fun contentLanguageKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "content_language")
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
    private fun autoPlayMaxQualityKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "auto_play_max_quality")
    private fun includeSpecialsKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "include_specials")

    // Active-profile key shortcuts (used for legacy flat fields in snapshot)
    private fun defaultSubtitleKey() = profileManager.profileStringKey("default_subtitle")
    private fun defaultAudioLanguageKey() = profileManager.profileStringKey("default_audio_language")
    private fun cardLayoutModeKey() = profileManager.profileStringKey("card_layout_mode")
    private fun frameRateMatchingModeKey() = profileManager.profileStringKey("frame_rate_matching_mode")
    private fun autoPlayNextKey() = profileManager.profileBooleanKey("auto_play_next")
    private fun autoPlaySingleSourceKey() = profileManager.profileBooleanKey("auto_play_single_source")
    private fun autoPlayMinQualityKey() = profileManager.profileStringKey("auto_play_min_quality")
    private fun autoPlayMaxQualityKey() = profileManager.profileStringKey("auto_play_max_quality")
    private fun includeSpecialsKey() = profileManager.profileBooleanKey("include_specials")

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

    private fun normalizeAutoPlayMaxQuality(raw: String?): String {
        return when (raw?.trim()?.lowercase()) {
            "any" -> "Any"
            "720p", "hd" -> "720p"
            "1080p", "fullhd", "fhd" -> "1080p"
            "4k", "2160p", "uhd" -> "4K"
            else -> "4K"
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

        // Per-profile settings
        val profileSettingsById = buildMap<String, CloudProfileSettings> {
            profiles.forEach { profile ->
                put(
                    profile.id,
                    CloudProfileSettings(
                        defaultSubtitle = prefs[defaultSubtitleKeyFor(profile.id)] ?: "Off",
                        defaultAudioLanguage = prefs[defaultAudioLanguageKeyFor(profile.id)] ?: "Auto (Original)",
                        contentLanguage = prefs[contentLanguageKeyFor(profile.id)] ?: "en-US",
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
                        autoPlayMaxQuality = normalizeAutoPlayMaxQuality(
                            prefs[autoPlayMaxQualityKeyFor(profile.id)] ?: "4K"
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
        root.put("autoPlayMaxQuality", normalizeAutoPlayMaxQuality(prefs[autoPlayMaxQualityKey()] ?: "4K"))
        root.put("includeSpecials", prefs[includeSpecialsKey()] ?: false)

        root.put("activeProfileId", profileRepository.getActiveProfileId() ?: JSONObject.NULL)
        root.put("profiles", JSONArray(gson.toJson(profiles)))
        root.put("profileSettingsById", JSONObject(gson.toJson(profileSettingsById)))

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

        // Addons per profile
        val addonsByProfile = buildMap<String, List<Addon>> {
            profiles.forEach { profile ->
                put(profile.id, streamRepository.getAddonsForProfile(profile.id))
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
        root.put("addons", JSONArray(gson.toJson(streamRepository.installedAddons.first())))
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

        // Informational
        val isTraktLinked = traktRepository.isAuthenticated.first()
        root.put("traktLinked", isTraktLinked)
        root.put("traktExpiration", JSONObject.NULL)

        return root.toString()
    }

    // ══════════════════════════════════════════════════════════
    //  PUSH LOCAL STATE TO CLOUD
    // ══════════════════════════════════════════════════════════

    suspend fun pushToCloud(): Result<Unit> {
        if (authRepository.getCurrentUserId().isNullOrBlank()) {
            return Result.failure(IllegalStateException("Not logged in"))
        }
        val payload = runCatching { buildCloudSnapshotJson() }.getOrElse {
            return Result.failure(it)
        }
        val result = authRepository.saveAccountSyncPayload(payload)
        if (result.isSuccess) onPushCompleted?.invoke()
        return result
    }

    // ══════════════════════════════════════════════════════════
    //  PULL CLOUD STATE TO LOCAL
    // ══════════════════════════════════════════════════════════

    /**
     * Restores the full cloud state to local repositories.
     * Returns [RestoreResult] indicating what happened.
     */
    suspend fun pullFromCloud(): RestoreResult {
        val payloadResult = authRepository.loadAccountSyncPayload()
        if (payloadResult.isFailure) return RestoreResult.FAILED

        val payload = payloadResult.getOrNull().orEmpty()
        if (payload.isBlank()) return RestoreResult.NO_BACKUP

        return runCatching {
            applyCloudPayload(payload)
        }.fold(
            onSuccess = { RestoreResult.RESTORED },
            onFailure = { e ->
                System.err.println("[CLOUD-SYNC] pullFromCloud failed: ${e.message}")
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
        val fallbackAutoPlayMaxQuality = normalizeAutoPlayMaxQuality(root.optString("autoPlayMaxQuality", "4K"))
        val fallbackIncludeSpecials = root.optBoolean("includeSpecials", false)

        // ── Profiles ──
        root.optJSONArray("profiles")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<List<Profile>>() {}.type
            val profiles: List<Profile> = gson.fromJson(json, type) ?: emptyList()
            val activeProfileId = root.optString("activeProfileId").ifBlank { null }
            if (profiles.isNotEmpty()) {
                // Preserve local active profile if it exists in cloud set
                val localActiveId = profileRepository.getActiveProfileId()
                val effectiveActiveId = if (localActiveId != null &&
                    profiles.any { it.id == localActiveId }
                ) localActiveId else activeProfileId
                profileRepository.replaceProfilesFromCloud(profiles, effectiveActiveId)
            }
        }

        val allProfiles = profileRepository.getProfiles()
        val activeProfileId = profileRepository.getActiveProfileId()
            ?: allProfiles.firstOrNull()?.id ?: "default"

        // ── Per-profile settings ──
        root.optJSONObject("profileSettingsById")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, CloudProfileSettings>>() {}.type
            val settingsByProfile: Map<String, CloudProfileSettings> = gson.fromJson(json, type) ?: emptyMap()
            if (settingsByProfile.isNotEmpty()) {
                context.settingsDataStore.edit { prefs ->
                    settingsByProfile.forEach { (profileId, state) ->
                        prefs[defaultSubtitleKeyFor(profileId)] = state.defaultSubtitle
                        prefs[defaultAudioLanguageKeyFor(profileId)] = state.defaultAudioLanguage
                        prefs[contentLanguageKeyFor(profileId)] = state.contentLanguage
                        prefs[cardLayoutModeKeyFor(profileId)] = normalizeCardLayoutMode(state.cardLayoutMode)
                        prefs[frameRateMatchingModeKeyFor(profileId)] = normalizeFrameRateMode(state.frameRateMatchingMode)
                        prefs[autoPlayNextKeyFor(profileId)] = state.autoPlayNext
                        prefs[autoPlaySingleSourceKeyFor(profileId)] = state.autoPlaySingleSource
                        prefs[autoPlayMinQualityKeyFor(profileId)] = normalizeAutoPlayMinQuality(state.autoPlayMinQuality)
                        prefs[autoPlayMaxQualityKeyFor(profileId)] = normalizeAutoPlayMaxQuality(state.autoPlayMaxQuality)
                        prefs[includeSpecialsKeyFor(profileId)] = state.includeSpecials
                    }
                }
            }
        } ?: run {
            // Legacy fallback: single-profile settings
            context.settingsDataStore.edit { prefs ->
                prefs[defaultSubtitleKeyFor(activeProfileId)] = fallbackDefaultSubtitle
                prefs[defaultAudioLanguageKeyFor(activeProfileId)] = fallbackDefaultAudioLanguage
                prefs[cardLayoutModeKeyFor(activeProfileId)] = fallbackCardLayoutMode
                prefs[frameRateMatchingModeKeyFor(activeProfileId)] = fallbackFrameRateMatchingMode
                prefs[autoPlayNextKeyFor(activeProfileId)] = fallbackAutoPlayNext
                prefs[autoPlaySingleSourceKeyFor(activeProfileId)] = fallbackAutoPlaySingleSource
                prefs[autoPlayMinQualityKeyFor(activeProfileId)] = fallbackAutoPlayMinQuality
                prefs[autoPlayMaxQualityKeyFor(activeProfileId)] = fallbackAutoPlayMaxQuality
                prefs[includeSpecialsKeyFor(activeProfileId)] = fallbackIncludeSpecials
            }
        }
        authRepository.saveDefaultSubtitleToProfile(fallbackDefaultSubtitle)
        authRepository.saveAutoPlayNextToProfile(fallbackAutoPlayNext)

        // ── Trakt tokens ──
        root.optJSONObject("traktTokens")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, TraktRepository.CloudTraktToken>>() {}.type
            val tokens: Map<String, TraktRepository.CloudTraktToken> = gson.fromJson(json, type) ?: emptyMap()
            traktRepository.importTokensForProfiles(tokens)
        }

        // ── Addons ──
        root.optJSONObject("addonsByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, List<Addon>>>() {}.type
            val map: Map<String, List<Addon>> = gson.fromJson(json, type) ?: emptyMap()
            map.forEach { (profileId, addons) ->
                streamRepository.replaceAddonsForProfile(profileId, addons)
            }
        }
        root.optJSONArray("addons")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            if (!root.has("addonsByProfile")) {
                val type = object : TypeToken<List<Addon>>() {}.type
                val addons: List<Addon> = gson.fromJson(json, type) ?: emptyList()
                if (addons.isNotEmpty()) {
                    streamRepository.replaceAddonsForProfile(activeProfileId, addons)
                }
            }
        }

        // ── Catalogs ──
        root.optJSONObject("catalogsByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, List<CatalogConfig>>>() {}.type
            val map: Map<String, List<CatalogConfig>> = gson.fromJson(json, type) ?: emptyMap()
            map.forEach { (profileId, catalogs) ->
                catalogRepository.replaceCatalogsForProfile(profileId, catalogs)
            }
        }
        root.optJSONArray("catalogs")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            if (!root.has("catalogsByProfile")) {
                val type = object : TypeToken<List<CatalogConfig>>() {}.type
                val catalogs: List<CatalogConfig> = gson.fromJson(json, type) ?: emptyList()
                if (catalogs.isNotEmpty()) {
                    catalogRepository.replaceCatalogsForProfile(activeProfileId, catalogs)
                }
            }
        }

        // ── Hidden preinstalled catalogs ──
        root.optJSONObject("hiddenPreinstalledByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, List<String>>>() {}.type
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
                    val type = object : TypeToken<List<String>>() {}.type
                    gson.fromJson<List<String>>(json, type) ?: emptyList()
                }
                catalogRepository.setHiddenPreinstalledCatalogIdsForProfile(activeProfileId, hidden)
            }
        }

        // ── IPTV config + favorites ──
        var importedActiveProfileIptv = false
        var importedActiveProfileM3u = ""
        root.optJSONObject("iptvByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, IptvCloudProfileState>>() {}.type
            val map: Map<String, IptvCloudProfileState> = gson.fromJson(json, type) ?: emptyMap()
            map.forEach { (profileId, state) ->
                iptvRepository.importCloudConfigForProfile(profileId, state)
                if (profileId == activeProfileId) {
                    importedActiveProfileIptv = true
                    importedActiveProfileM3u = state.m3uUrl
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
                val type = object : TypeToken<List<String>>() {}.type
                gson.fromJson<List<String>>(j, type) ?: emptyList()
            }
        }
        val favoriteChannels = root.optJSONArray("iptvFavoriteChannels")?.toString().orEmpty().let { j ->
            if (j.isBlank()) emptyList() else {
                val type = object : TypeToken<List<String>>() {}.type
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
            importedActiveProfileM3u = m3u
        }

        if (importedActiveProfileIptv || importedLegacyIptv) {
            runCatching {
                iptvRepository.invalidateCache()
                if (importedActiveProfileM3u.isNotBlank()) {
                    iptvRepository.loadSnapshot(
                        forcePlaylistReload = false,
                        forceEpgReload = false
                    )
                }
            }
        }

        // ── Watchlist ──
        root.optJSONObject("watchlistByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, List<LocalWatchlistItem>>>() {}.type
            val map: Map<String, List<LocalWatchlistItem>> = gson.fromJson(json, type) ?: emptyMap()
            map.forEach { (profileId, items) ->
                watchlistRepository.importWatchlistForProfile(profileId, items)
            }
        }

        // ── Dismissed Continue Watching ──
        root.optJSONObject("dismissedContinueWatchingByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, String>>() {}.type
            val map: Map<String, String> = gson.fromJson(json, type) ?: emptyMap()
            traktRepository.importDismissedContinueWatchingForProfiles(map)
        }

        root.optJSONObject("localContinueWatchingByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, List<ContinueWatchingItem>>>() {}.type
            val map: Map<String, List<ContinueWatchingItem>> = gson.fromJson(json, type) ?: emptyMap()
            traktRepository.importLocalContinueWatchingForProfiles(map)
        }

        root.optJSONObject("localWatchedMoviesByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, List<Int>>>() {}.type
            val map: Map<String, List<Int>> = gson.fromJson(json, type) ?: emptyMap()
            traktRepository.importLocalWatchedMoviesForProfiles(map)
        }

        root.optJSONObject("localWatchedEpisodesByProfile")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
            val type = object : TypeToken<Map<String, List<String>>>() {}.type
            val map: Map<String, List<String>> = gson.fromJson(json, type) ?: emptyMap()
            traktRepository.importLocalWatchedEpisodesForProfiles(map)
        }

        traktRepository.clearAllProfileCaches()

        System.err.println("[CLOUD-SYNC] Full cloud restore applied successfully")
    }
}
