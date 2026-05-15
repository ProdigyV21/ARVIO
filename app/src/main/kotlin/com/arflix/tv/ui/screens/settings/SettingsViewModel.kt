package com.arflix.tv.ui.screens.settings

import android.content.Context
import android.graphics.Bitmap
import coil.Coil
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.server.AiKeyConfigServer
import com.arflix.tv.ui.screens.player.SubtitleAiModel
import com.arflix.tv.util.DeviceIpAddress
import com.arflix.tv.util.QrCodeGenerator
import com.arflix.tv.data.api.TraktDeviceCode
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogDiscoveryResult
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.QualityFilterConfig
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.data.repository.CatalogDiscoveryRepository
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.CollectionTemplateManifest
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.HomeServerConnection
import com.arflix.tv.data.repository.HomeServerRepository
import com.arflix.tv.data.repository.PlexPinAuthSession
import com.arflix.tv.data.repository.IptvConfig
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.TvDeviceAuthRepository
import com.arflix.tv.data.repository.TvDeviceAuthSession
import com.arflix.tv.data.repository.TvDeviceAuthStatusType
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.TraktSyncService
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.data.repository.SyncProgress
import com.arflix.tv.data.repository.SyncStatus
import com.arflix.tv.data.repository.SyncResult
import com.arflix.tv.ui.components.CARD_LAYOUT_MODE_LANDSCAPE
import com.arflix.tv.ui.components.normalizeCardLayoutMode
import com.arflix.tv.updater.ApkDownloader
import com.arflix.tv.updater.ApkInstaller
import com.arflix.tv.updater.AppUpdate
import com.arflix.tv.updater.AppUpdateRepository
import com.arflix.tv.updater.UpdatePreferences
import com.arflix.tv.updater.VersionUtils
import com.arflix.tv.util.AuthEmailValidator
import com.arflix.tv.util.LAST_APP_LANGUAGE_KEY
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.io.File
import javax.inject.Inject

enum class ToastType {
    SUCCESS, ERROR, INFO
}

data class AiKeyServerState(
    val isActive: Boolean = false,
    val serverUrl: String? = null,
    val qrBitmap: Bitmap? = null,
    val keyReceived: Boolean = false
)

data class SettingsUiState(
    val defaultSubtitle: String = "Off",
    val subtitleOptions: List<String> = emptyList(),
    val defaultAudioLanguage: String = "Auto (Original)",
    val audioLanguageOptions: List<String> = emptyList(),
    val cardLayoutMode: String = CARD_LAYOUT_MODE_LANDSCAPE,
    val frameRateMatchingMode: String = "Off",
    val autoPlayNext: Boolean = true,
    val autoPlaySingleSource: Boolean = true,
    val autoPlayMinQuality: String = "Any",
    val dnsProvider: String = "System DNS",
    val dnsProviderOptions: List<String> = listOf("System DNS", "Cloudflare", "Google", "AdGuard"),
    val subtitleSize: String = "Medium",
    val subtitleColor: String = "White",
    val subtitleStyle: String = "Bold",
    val subtitleOffset: String = "Low",
    val filterSubtitlesByLanguage: Boolean = true,
    val secondarySubtitle: String = "Off",
    val trailerAutoPlay: Boolean = false,
    val trailerSoundEnabled: Boolean = false,
    val showBudget: Boolean = true,
    // Volume boost in decibels (0 = off, up to 15 dB). Applied via system LoudnessEnhancer
    // attached to the ExoPlayer audio session. Issue #88.
    val volumeBoostDb: Int = 0,
    val showLoadingStats: Boolean = true,
    val includeSpecials: Boolean = false,
    val isLoggedIn: Boolean = false,
    val accountEmail: String? = null,
    val showCloudPairDialog: Boolean = false,
    val cloudUserCode: String? = null,
    val cloudVerificationUrl: String? = null,
    val showCloudEmailPasswordDialog: Boolean = false,
    val isCloudAuthWorking: Boolean = false,
    val isForceCloudSyncing: Boolean = false,
    val shouldSwitchProfile: Boolean = false,
    // Trakt
    val isTraktAuthenticated: Boolean = false,
    val traktCode: TraktDeviceCode? = null,
    val isTraktAuthStarting: Boolean = false,
    val isTraktPolling: Boolean = false,
    val traktExpiration: String? = null,
    // Trakt Sync
    val isSyncing: Boolean = false,
    val syncProgress: SyncProgress = SyncProgress(),
    val lastSyncTime: String? = null,
    val syncedMovies: Int = 0,
    val syncedEpisodes: Int = 0,
    // IPTV
    val iptvM3uUrl: String = "",
    val iptvEpgUrl: String = "",
    val iptvPlaylists: List<IptvPlaylistEntry> = emptyList(),
    val iptvStalkerUrl: String = "",
    val iptvStalkerMac: String = "",
    val iptvChannelCount: Int = 0,
    val isIptvLoading: Boolean = false,
    val iptvError: String? = null,
    val iptvStatusMessage: String? = null,
    val iptvStatusType: ToastType = ToastType.INFO,
    val iptvProgressText: String? = null,
    val iptvProgressPercent: Int = 0,
    // App updates
    val isSelfUpdateSupported: Boolean = true,
    val updateStatus: com.arflix.tv.updater.UpdateStatus = com.arflix.tv.updater.UpdateStatus.Idle,
    val showAppUpdateDialog: Boolean = false,
    val showUnknownSourcesDialog: Boolean = false,
    // Catalogs
    val catalogs: List<CatalogConfig> = emptyList(),
    val catalogSearchQuery: String = "",
    val catalogSearchResults: List<CatalogDiscoveryResult> = emptyList(),
    val isCatalogSearching: Boolean = false,
    val catalogSearchError: String? = null,
    // Addons
    val addons: List<Addon> = emptyList(),
    val torrServerBaseUrl: String = "",
    val homeServerConnection: HomeServerConnection? = null,
    val homeServerConnections: List<HomeServerConnection> = emptyList(),
    val isHomeServerConnecting: Boolean = false,
    val homeServerError: String? = null,
    val plexHomeServerAuth: PlexPinAuthSession? = null,
    val isPlexHomeServerPolling: Boolean = false,
    // Content language (TMDB metadata)
    val contentLanguage: String = "en-US",
    // Device mode override
    val deviceModeOverride: String = "auto",
    // Skip profile selection
    val skipProfileSelection: Boolean = false,
    val oledBlackBackground: Boolean = false,
    val clockFormat: String = "24h",
    val qualityFilters: List<QualityFilterConfig> = emptyList(),
    // Spoiler blur — blur unwatched episode card images and hide synopsis
    val spoilerBlurEnabled: Boolean = false,
    // Focus border color — user-selectable theme colour for the D-pad focus ring
    val focusBorderColor: String = "White",
    val qualityFilterPresetLabel: String = "OFF",
    // Toast
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.INFO,
    // AI Subtitles
    val subtitleAiEnabled: Boolean = false,
    val subtitleAiAutoSelect: Boolean = false,
    val subtitleAiApiKey: String = "",
    val subtitleAiModel: SubtitleAiModel = SubtitleAiModel.GROQ_LLAMA_70B,
    val subtitleRemoveHearingImpaired: Boolean = true,
    val aiKeyServerState: AiKeyServerState = AiKeyServerState()
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val traktRepository: TraktRepository,
    private val streamRepository: StreamRepository,
    private val mediaRepository: MediaRepository,
    private val catalogRepository: CatalogRepository,
    private val catalogDiscoveryRepository: CatalogDiscoveryRepository,
    private val iptvRepository: IptvRepository,
    private val homeServerRepository: HomeServerRepository,
    private val watchlistRepository: WatchlistRepository,
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val tvDeviceAuthRepository: TvDeviceAuthRepository,
    private val traktSyncService: TraktSyncService,
    private val cloudSyncRepository: CloudSyncRepository,
    private val launcherContinueWatchingRepository: LauncherContinueWatchingRepository,
    private val appUpdateRepository: AppUpdateRepository,
    private val updatePreferences: UpdatePreferences,
    private val apkDownloader: ApkDownloader,
    private val updateStatusManager: com.arflix.tv.updater.UpdateStatusManager
) : ViewModel() {
    private fun visibleCatalogs(catalogs: List<CatalogConfig>): List<CatalogConfig> {
        return catalogs.filter { config ->
            when (config.kind) {
                CatalogKind.COLLECTION -> false
                CatalogKind.COLLECTION_RAIL -> CollectionTemplateManifest.isValidCollectionConfig(config)
                else -> true
            }
        }
    }


    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private fun contentLanguageKey() = profileManager.profileStringKey("content_language")

    private fun defaultSubtitleKey() = profileManager.profileStringKey("default_subtitle")
    private fun defaultSubtitleKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "default_subtitle")
    private fun subtitleSettingsUpdatedAtKey() = profileManager.profileStringKey("subtitle_settings_updated_at")
    private fun defaultAudioLanguageKey() = profileManager.profileStringKey("default_audio_language")
    private fun defaultAudioLanguageKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "default_audio_language")
    private fun subtitleUsageKey() = profileManager.profileStringKey("subtitle_usage_v1")
    private fun cardLayoutModeKey() = profileManager.profileStringKey("card_layout_mode")
    private fun cardLayoutModeKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "card_layout_mode")
    private fun frameRateMatchingModeKey() = profileManager.profileStringKey("frame_rate_matching_mode")
    private fun frameRateMatchingModeKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "frame_rate_matching_mode")
    private fun autoPlayNextKey() = profileManager.profileBooleanKey("auto_play_next")
    private fun autoPlayNextKeyFor(profileId: String) = profileManager.profileBooleanKeyFor(profileId, "auto_play_next")
    private fun autoPlaySingleSourceKey() = profileManager.profileBooleanKey("auto_play_single_source")
    private fun autoPlaySingleSourceKeyFor(profileId: String) = profileManager.profileBooleanKeyFor(profileId, "auto_play_single_source")
    private fun autoPlayMinQualityKey() = profileManager.profileStringKey("auto_play_min_quality")
    private fun autoPlayMinQualityKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "auto_play_min_quality")
    private fun trailerAutoPlayKey() = profileManager.profileBooleanKey("trailer_auto_play")
    private fun trailerSoundEnabledKey() = profileManager.profileBooleanKey("trailer_sound_enabled")
    private fun showBudgetKey() = profileManager.profileBooleanKey("show_budget_on_home")
    private fun clockFormatKey() = profileManager.profileStringKey("clock_format")
    private fun spoilerBlurKey() = profileManager.profileBooleanKey("spoiler_blur")
    // Stored as a string because ProfileManager has no int helper and we only persist
    // a handful of discrete dB values. Parsed back to Int on read.
    private fun volumeBoostDbKey() = profileManager.profileStringKey("volume_boost_db")
    private fun showLoadingStatsKey() = profileManager.profileBooleanKey("show_loading_stats")

    private fun subtitleSizeKey() = profileManager.profileStringKey("subtitle_size")
    private fun subtitleColorKey() = profileManager.profileStringKey("subtitle_color")
    private fun subtitleOffsetKey() = profileManager.profileStringKey("subtitle_offset")
    private fun subtitleStyleKey() = profileManager.profileStringKey("subtitle_style")
    private fun filterSubtitlesByLanguageKey() = profileManager.profileBooleanKey("filter_subtitles_by_lang")
    private fun secondarySubtitleKey() = profileManager.profileStringKey("secondary_subtitle")
    private val dnsProviderKey = stringPreferencesKey(OkHttpProvider.DNS_PROVIDER_PREF_KEY)
    private fun includeSpecialsKey() = profileManager.profileBooleanKey("include_specials")
    private val qualityFiltersKey = stringPreferencesKey("quality_filters")

    // Global (non-profile-scoped) AI subtitle settings — device-wide, not per-profile
    private val subtitleAiEnabledKey = booleanPreferencesKey("subtitle_ai_enabled")
    private val subtitleAiAutoSelectKey = booleanPreferencesKey("subtitle_ai_auto_select")
    private val subtitleAiApiKeyKey = stringPreferencesKey("subtitle_ai_api_key")
    private val subtitleAiModelKey = stringPreferencesKey("subtitle_ai_model")
    private val subtitleRemoveHearingImpairedKey = booleanPreferencesKey("subtitle_remove_hearing_impaired")
    private fun includeSpecialsKeyFor(profileId: String) = profileManager.profileBooleanKeyFor(profileId, "include_specials")
    private val gson = Gson()
    private var lastObservedIptvM3u: String = ""
    private var lastObservedStalkerUrl: String = ""

    private var traktPollingJob: Job? = null
    private var plexHomeServerPollingJob: Job? = null
    private var plexHomeServerUrl: String? = null
    private var plexHomeServerDisplayName: String? = null
    private var iptvLoadJob: Job? = null
    private var catalogSearchJob: Job? = null
    private var aiKeyServer: AiKeyConfigServer? = null
    private var lastCloudSyncedUserId: String? = null
    private var cloudDeviceCode: String? = null
    private var cloudUserCode: String? = null
    private var cloudVerificationUrl: String? = null
    private var cloudPollIntervalMs: Long = 800L
    private var cloudExpiresAtMs: Long = 0L
    private var cloudPollingJob: Job? = null
    private var pendingProfileSwitchAfterCloudLogin: Boolean = false
    private var observedProfileId: String? = null
    private var hasObservedIptvConfig: Boolean = false
    private var lastObservedIptvConfigSignature: String? = null

    private enum class CloudRestoreResult {
        RESTORED,
        NO_BACKUP,
        FAILED
    }

    private enum class QualityFilterPreset(
        val label: String,
        val filterId: String?,
        val regexPattern: String?
    ) {
        OFF(label = "OFF", filterId = null, regexPattern = null),
        HD_1080_PLUS(
            label = "1080p+",
            filterId = "preset_quality_1080_plus",
            regexPattern = "(?:360|480|576|720)p|cam|hdcam|hdts|hdtc|telesync|telecine|ts|tc|screener|scr|sd"
        ),
        HD_1080_ONLY(
            label = "1080p only",
            filterId = "preset_quality_1080_only",
            regexPattern = "(?:2160|4k|uhd)|(?:360|480|576|720)p|cam|hdcam|hdts|hdtc|telesync|telecine|ts|tc|screener|scr|sd"
        ),
        HD_720_PLUS(
            label = "720p+",
            filterId = "preset_quality_720_plus",
            regexPattern = "(?:360|480|576)p|cam|hdcam|hdts|hdtc|telesync|telecine|ts|tc|screener|scr|sd"
        ),
        CUSTOM(label = "CUSTOM", filterId = null, regexPattern = null);

        fun toFilters(): List<QualityFilterConfig> {
            if (this == OFF || this == CUSTOM || filterId == null || regexPattern == null) return emptyList()
            return listOf(
                QualityFilterConfig(
                    id = filterId,
                    deviceName = "Preset: $label",
                    regexPattern = regexPattern,
                    enabled = true
                )
            )
        }
    }

    init {
        loadSettings()
        observeProfileChanges()
        observeAddons()
        observeTorrServer()
        observeHomeServer()
        observeSyncState()
        observeAuthState()
        observeIptvConfig()
        initializeCatalogs()
        observeCatalogs()
        initializeUpdaterState()
        checkForAppUpdates(force = false, showNoUpdateFeedback = false)
    }

    private fun initializeUpdaterState() {
        _uiState.value = _uiState.value.copy(
            isSelfUpdateSupported = appUpdateRepository.supportsSelfUpdate()
        )
        // If the app was updated to a new version, clear any previously ignored tag
        // so future updates are shown again.
        viewModelScope.launch {
            val ignoredTag = updatePreferences.ignoredTag.first()
            if (ignoredTag != null) {
                val installedVersion = appUpdateRepository.getInstalledVersionName()
                val ignoredNormalized = com.arflix.tv.updater.VersionUtils.normalize(ignoredTag)
                val installedNormalized = com.arflix.tv.updater.VersionUtils.normalize(installedVersion)
                if (ignoredNormalized == installedNormalized || !com.arflix.tv.updater.VersionUtils.isRemoteNewer(ignoredTag, installedVersion)) {
                    updatePreferences.setIgnoredTag(null)
                }
            }
        }

        viewModelScope.launch {
            updateStatusManager.status.collect { status ->
                _uiState.value = _uiState.value.copy(
                    updateStatus = status
                )
            }
        }
    }

    private fun loadSettings() {
        viewModelScope.launch {
            // Load local preferences first
            val prefs = context.settingsDataStore.data.first()
            var defaultSub = prefs[defaultSubtitleKey()] ?: "Off"
            val defaultAudio = prefs[defaultAudioLanguageKey()] ?: "Auto (Original)"
            val cardLayoutMode = normalizeCardLayoutMode(prefs[cardLayoutModeKey()])
            val frameRateMode = normalizeFrameRateMode(prefs[frameRateMatchingModeKey()])
            val deviceModeOverride = prefs[com.arflix.tv.util.DEVICE_MODE_OVERRIDE_KEY] ?: "auto"
            val skipProfileSelection = prefs[com.arflix.tv.util.SKIP_PROFILE_SELECTION_KEY] ?: false
            val oledBlackBackground = prefs[com.arflix.tv.util.OLED_BLACK_BACKGROUND_KEY] ?: false
            val contentLang = prefs[contentLanguageKey()] ?: "en-US"
            // Apply content language to MediaRepository immediately
            mediaRepository.contentLanguage = if (contentLang == "en-US") null else contentLang
            var autoPlay = prefs[autoPlayNextKey()] ?: true
            var autoPlaySingleSource = prefs[autoPlaySingleSourceKey()] ?: true
            // Ensure defaults are persisted on first launch so they're never ambiguous
            if (prefs[autoPlaySingleSourceKey()] == null) {
                autoPlaySingleSource = true
                context.settingsDataStore.edit { it[autoPlaySingleSourceKey()] = true }
            }
            if (prefs[autoPlayNextKey()] == null) {
                context.settingsDataStore.edit { it[autoPlayNextKey()] = true }
            }
            val autoPlayMinQuality = normalizeAutoPlayMinQuality(prefs[autoPlayMinQualityKey()])
            val trailerAutoPlay = prefs[trailerAutoPlayKey()] ?: false
            val trailerSoundEnabled = prefs[trailerSoundEnabledKey()] ?: false
            val spoilerBlurEnabled = prefs[spoilerBlurKey()] ?: false
            val showBudget = prefs[showBudgetKey()] ?: true
            val clockFormat = prefs[clockFormatKey()] ?: "24h"
            val focusBorderColor = prefs[com.arflix.tv.util.FOCUS_BORDER_COLOR_KEY] ?: "White"
            val volumeBoostDb = prefs[volumeBoostDbKey()]?.toIntOrNull()?.coerceIn(0, 15) ?: 0
            val showLoadingStats = prefs[showLoadingStatsKey()] ?: true

            val subtitleSize = prefs[subtitleSizeKey()] ?: "Medium"
            val subtitleColor = prefs[subtitleColorKey()] ?: "White"
            val subtitleStyle = prefs[subtitleStyleKey()] ?: "Bold"
            val subtitleOffset = prefs[subtitleOffsetKey()] ?: "Low"
            val filterSubtitlesByLanguage = prefs[filterSubtitlesByLanguageKey()] ?: true
            val secondarySubtitle = prefs[secondarySubtitleKey()]?.trim()?.takeIf { it.isNotBlank() } ?: "Off"
            val dnsProviderValue = normalizeDnsProviderValue(prefs[dnsProviderKey])
            val includeSpecials = prefs[includeSpecialsKey()] ?: false
            val qualityFilters = runCatching {
                val json = prefs[qualityFiltersKey].orEmpty()
                if (json.isBlank()) {
                    emptyList()
                } else {
                    gson.fromJson<List<QualityFilterConfig>>(
                        json,
                        object : TypeToken<List<QualityFilterConfig>>() {}.type
                    ).orEmpty()
                }
            }.getOrDefault(emptyList())

            val subtitleAiEnabled = prefs[subtitleAiEnabledKey] ?: false
            val subtitleAiAutoSelect = prefs[subtitleAiAutoSelectKey] ?: false
            val subtitleAiApiKey = prefs[subtitleAiApiKeyKey] ?: ""
            val subtitleAiModel = runCatching {
                SubtitleAiModel.valueOf(prefs[subtitleAiModelKey] ?: SubtitleAiModel.GROQ_LLAMA_70B.name)
            }.getOrDefault(SubtitleAiModel.GROQ_LLAMA_70B)
            val subtitleRemoveHearingImpaired = prefs[subtitleRemoveHearingImpairedKey] ?: true

            // Check auth statuses
            val authState = authRepository.authState.first()
            val isLoggedIn = authState is AuthState.Authenticated
            val accountEmail = (authState as? AuthState.Authenticated)?.email
            val isTrakt = traktRepository.hasTrakt()

            // Get Trakt expiration if authenticated
            var traktExpiration: String? = null
            if (isTrakt) {
                traktExpiration = traktRepository.getTokenExpirationDate()
            }

            val subtitleOptions = loadSubtitleOptions(defaultSub)
            val audioLanguageOptions = loadAudioLanguageOptions(defaultAudio)
            val existingCatalogs = _uiState.value.catalogs.ifEmpty {
                mediaRepository.getDefaultCatalogConfigs()
            }

            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                defaultSubtitle = defaultSub,
                subtitleOptions = subtitleOptions,
                defaultAudioLanguage = defaultAudio,
                audioLanguageOptions = audioLanguageOptions,
                cardLayoutMode = cardLayoutMode,
                frameRateMatchingMode = frameRateMode,
                autoPlayNext = autoPlay,
                autoPlaySingleSource = autoPlaySingleSource,
                autoPlayMinQuality = autoPlayMinQuality,
                trailerAutoPlay = trailerAutoPlay,
                trailerSoundEnabled = trailerSoundEnabled,
                showBudget = showBudget,
                volumeBoostDb = volumeBoostDb,
                showLoadingStats = showLoadingStats,

                subtitleSize = subtitleSize,
                subtitleColor = subtitleColor,
                subtitleStyle = subtitleStyle,
                subtitleOffset = subtitleOffset,
                filterSubtitlesByLanguage = filterSubtitlesByLanguage,
                secondarySubtitle = secondarySubtitle,
                dnsProvider = dnsProviderLabel(dnsProviderValue),
                includeSpecials = includeSpecials,
                spoilerBlurEnabled = spoilerBlurEnabled,
                isLoggedIn = isLoggedIn,
                accountEmail = accountEmail,
                isTraktAuthenticated = isTrakt,
                traktExpiration = traktExpiration,
                catalogs = existingCatalogs,
                contentLanguage = contentLang,
                deviceModeOverride = deviceModeOverride,
                skipProfileSelection = skipProfileSelection,
                oledBlackBackground = oledBlackBackground,
                clockFormat = clockFormat,
                focusBorderColor = focusBorderColor,
                qualityFilters = qualityFilters,
                qualityFilterPresetLabel = detectQualityFilterPreset(qualityFilters).label,
                subtitleAiEnabled = subtitleAiEnabled,
                subtitleAiAutoSelect = subtitleAiAutoSelect,
                subtitleAiApiKey = subtitleAiApiKey,
                subtitleAiModel = subtitleAiModel,
                subtitleRemoveHearingImpaired = subtitleRemoveHearingImpaired
            )
        }
    }

    private fun observeProfileChanges() {
        viewModelScope.launch {
            profileManager.activeProfileId.collect { profileId ->
                if (observedProfileId == profileId) return@collect
                observedProfileId = profileId
                hasObservedIptvConfig = false
                lastObservedIptvConfigSignature = null
                loadSettings()
            }
        }
    }

    fun refreshSubtitleOptions() {
        viewModelScope.launch {
            val options = loadSubtitleOptions(_uiState.value.defaultSubtitle)
            if (_uiState.value.subtitleOptions != options) {
                _uiState.value = _uiState.value.copy(subtitleOptions = options)
            }
        }
    }

    fun refreshAudioLanguageOptions() {
        viewModelScope.launch {
            val options = loadAudioLanguageOptions(_uiState.value.defaultAudioLanguage)
            if (_uiState.value.audioLanguageOptions != options) {
                _uiState.value = _uiState.value.copy(audioLanguageOptions = options)
            }
        }
    }

    private fun observeAddons() {
        viewModelScope.launch {
            streamRepository.installedAddons.collect { addons ->
                runCatching {
                    catalogRepository.syncAddonCatalogs(addons)
                }
                if (_uiState.value.addons != addons) {
                    _uiState.value = _uiState.value.copy(addons = addons)
                }
            }
        }
    }

    private fun observeTorrServer() {
        viewModelScope.launch {
            streamRepository.observeTorrServerBaseUrl().collect { url ->
                if (_uiState.value.torrServerBaseUrl != url) {
                    _uiState.value = _uiState.value.copy(torrServerBaseUrl = url)
                }
            }
        }
    }

    private fun observeHomeServer() {
        viewModelScope.launch {
            homeServerRepository.connections.collect { connections ->
                _uiState.value = _uiState.value.copy(
                    homeServerConnection = connections.firstOrNull(),
                    homeServerConnections = connections
                )
            }
        }
    }

    private fun observeSyncState() {
        // Observe sync progress
        viewModelScope.launch {
            traktSyncService.syncProgress.collect { progress ->
                if (_uiState.value.syncProgress != progress) {
                    _uiState.value = _uiState.value.copy(syncProgress = progress)
                }
            }
        }

        // Observe sync status
        viewModelScope.launch {
            traktSyncService.isSyncing.collect { isSyncing ->
                if (_uiState.value.isSyncing != isSyncing) {
                    _uiState.value = _uiState.value.copy(isSyncing = isSyncing)
                }
            }
        }

        // Load last sync time
        viewModelScope.launch {
            val lastSync = traktSyncService.getLastSyncTime()
            _uiState.value = _uiState.value.copy(lastSyncTime = formatSyncTime(lastSync))
        }
    }

    private fun formatSyncTime(isoTime: String?): String? {
        if (isoTime == null) return null
        return try {
            val instant = java.time.Instant.parse(isoTime)
            val formatter = java.time.format.DateTimeFormatter
                .ofPattern("MMM dd, yyyy 'at' h:mm a")
                .withZone(java.time.ZoneId.systemDefault())
            formatter.format(instant)
        } catch (e: Exception) {
            null
        }
    }

    // ========== Trakt Sync ==========

    fun performFullSync(silent: Boolean = false) {
        viewModelScope.launch {
            if (_uiState.value.isSyncing) return@launch
            val result = traktSyncService.performFullSync()
            when (result) {
                is SyncResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        syncedMovies = result.moviesSynced,
                        syncedEpisodes = result.episodesSynced,
                        lastSyncTime = formatSyncTime(java.time.Instant.now().toString()),
                        toastMessage = "Synced ${result.moviesSynced} movies and ${result.episodesSynced} episodes",
                        toastType = ToastType.SUCCESS
                    )
                    // Invalidate repository cache to pick up new data
                    traktRepository.invalidateWatchedCache()
                    traktRepository.initializeWatchedCache()
                }
                is SyncResult.Error -> {
                    if (!silent) {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "Sync failed: ${result.message}",
                            toastType = ToastType.ERROR
                        )
                    }
                }
            }
        }
    }

    fun performIncrementalSync() {
        viewModelScope.launch {
            val result = traktSyncService.performIncrementalSync()
            when (result) {
                is SyncResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        syncedMovies = _uiState.value.syncedMovies + result.moviesSynced,
                        syncedEpisodes = _uiState.value.syncedEpisodes + result.episodesSynced,
                        lastSyncTime = formatSyncTime(java.time.Instant.now().toString()),
                        toastMessage = if (result.moviesSynced == 0 && result.episodesSynced == 0)
                            "Already up to date"
                        else
                            "Synced ${result.moviesSynced} movies and ${result.episodesSynced} episodes",
                        toastType = ToastType.SUCCESS
                    )
                    // Invalidate repository cache to pick up new data
                    traktRepository.invalidateWatchedCache()
                    traktRepository.initializeWatchedCache()
                }
                is SyncResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = "Sync failed: ${result.message}",
                        toastType = ToastType.ERROR
                    )
                }
            }
        }
    }

    fun setDefaultSubtitle(language: String) {
        viewModelScope.launch {
            // Save locally
            val changedAt = System.currentTimeMillis()
            context.settingsDataStore.edit { prefs ->
                prefs[defaultSubtitleKey()] = language
                prefs[subtitleSettingsUpdatedAtKey()] = changedAt.toString()
            }
            _uiState.value = _uiState.value.copy(
                defaultSubtitle = language,
                subtitleOptions = loadSubtitleOptions(language)
            )

            // Sync to cloud
            authRepository.saveDefaultSubtitleToProfile(language)
            syncLocalStateToCloud(silent = true, force = true)
        }
    }

    fun setDefaultAudioLanguage(language: String) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[defaultAudioLanguageKey()] = language
            }
            _uiState.value = _uiState.value.copy(
                defaultAudioLanguage = language,
                audioLanguageOptions = loadAudioLanguageOptions(language)
            )
            syncLocalStateToCloud(silent = true)
        }
    }

    private suspend fun loadSubtitleOptions(current: String): List<String> {
        val prefs = context.settingsDataStore.data.first()
        val json = prefs[subtitleUsageKey()]
        val type = TypeToken.getParameterized(Map::class.java, String::class.java, Int::class.javaObjectType).type
        val usage: Map<String, Int> = if (!json.isNullOrBlank()) {
            gson.fromJson(json, type)
        } else {
            emptyMap()
        }

        val topUsed = usage.entries
            .sortedByDescending { it.value }
            .map { entry -> displayLanguage(entry.key) }
            .filter { it.isNotBlank() }
            .take(30)

        // Keep this list >= 25 items; this is the "always available" picker list.
        val defaults = listOf(
            "English",
            "Arabic",
            "Bengali",
            "Bulgarian",
            "Chinese",
            "Croatian",
            "Czech",
            "Danish",
            "Dutch",
            "Estonian",
            "Finnish",
            "French",
            "German",
            "Greek",
            "Hebrew",
            "Hindi",
            "Hungarian",
            "Indonesian",
            "Italian",
            "Japanese",
            "Korean",
            "Lithuanian",
            "Norwegian",
            "Persian",
            "Polish",
            "Portuguese",
            "Portuguese (Brazil)",
            "Romanian",
            "Russian",
            "Serbian",
            "Slovak",
            "Slovenian",
            "Spanish",
            "Swedish",
            "Thai",
            "Turkish",
            "Ukrainian",
            "Vietnamese"
        )
        val base = buildList {
            add("Off")
            add("Forced")
            if (current.isNotBlank() && current != "Off" && current != "Forced") add(current)
            addAll(topUsed)
            addAll(defaults)
        }

        return base.distinct().take(60)
    }

    private fun loadAudioLanguageOptions(current: String): List<String> {
        val defaults = listOf(
            "Auto (Original)",
            "English",
            "Arabic",
            "Bengali",
            "Bulgarian",
            "Chinese",
            "Croatian",
            "Czech",
            "Danish",
            "Dutch",
            "Estonian",
            "Finnish",
            "French",
            "German",
            "Greek",
            "Hebrew",
            "Hindi",
            "Hungarian",
            "Indonesian",
            "Italian",
            "Japanese",
            "Korean",
            "Lithuanian",
            "Norwegian",
            "Persian",
            "Polish",
            "Portuguese",
            "Portuguese (Brazil)",
            "Romanian",
            "Russian",
            "Serbian",
            "Slovak",
            "Slovenian",
            "Spanish",
            "Swedish",
            "Thai",
            "Turkish",
            "Ukrainian",
            "Vietnamese"
        )
        return buildList {
            if (current.isNotBlank()) add(current)
            addAll(defaults)
        }.distinct().take(60)
    }

    private fun displayLanguage(code: String): String {
        val normalized = code.trim()
        if (normalized.isBlank()) return ""
        val isCode = normalized.length <= 3 && normalized.all { it.isLetter() }
        if (!isCode) return normalized.replaceFirstChar { it.uppercase() }
        val locale = java.util.Locale(normalized)
        val name = locale.getDisplayLanguage(java.util.Locale.ENGLISH)
        return if (name.isNullOrBlank()) normalized else name
    }

    fun setAutoPlayNext(enabled: Boolean) {
        viewModelScope.launch {
            // Save locally
            context.settingsDataStore.edit { prefs ->
                prefs[autoPlayNextKey()] = enabled
            }
            _uiState.value = _uiState.value.copy(autoPlayNext = enabled)

            // Sync to cloud
            authRepository.saveAutoPlayNextToProfile(enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setAutoPlaySingleSource(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[autoPlaySingleSourceKey()] = enabled
            }
            _uiState.value = _uiState.value.copy(autoPlaySingleSource = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setSecondarySubtitle(language: String) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[secondarySubtitleKey()] = language
            }
            _uiState.value = _uiState.value.copy(secondarySubtitle = language)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setFilterSubtitlesByLanguage(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[filterSubtitlesByLanguageKey()] = enabled
            }
            _uiState.value = _uiState.value.copy(filterSubtitlesByLanguage = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun cycleAutoPlayMinQuality() {
        val current = normalizeAutoPlayMinQuality(_uiState.value.autoPlayMinQuality)
        val next = when (current) {
            "Any" -> "720p"
            "720p" -> "1080p"
            "1080p" -> "4K"
            else -> "Any"
        }
        setAutoPlayMinQuality(next)
    }

    private fun setAutoPlayMinQuality(value: String) {
        val normalized = normalizeAutoPlayMinQuality(value)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[autoPlayMinQualityKey()] = normalized
            }
            _uiState.value = _uiState.value.copy(autoPlayMinQuality = normalized)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun toggleCardLayoutMode() {
        val next = if (_uiState.value.cardLayoutMode.equals("Poster", ignoreCase = true)) {
            CARD_LAYOUT_MODE_LANDSCAPE
        } else {
            "Poster"
        }
        setCardLayoutMode(next)
    }

    fun setCardLayoutMode(mode: String) {
        val normalized = normalizeCardLayoutMode(mode)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[cardLayoutModeKey()] = normalized
            }
            _uiState.value = _uiState.value.copy(cardLayoutMode = normalized)
            syncLocalStateToCloud(silent = true)
        }
    }

    /** Set content/metadata language for TMDB (e.g. "en-US", "fr-FR", "nl-NL"). */
    fun setContentLanguage(lang: String) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[contentLanguageKey()] = lang
                prefs[LAST_APP_LANGUAGE_KEY] = lang
            }
            // Mirror to SharedPreferences so attachBaseContext can read it synchronously on next launch
            context.getSharedPreferences("app_locale", android.content.Context.MODE_PRIVATE)
                .edit().putString("locale_tag", lang).apply()
            mediaRepository.contentLanguage = if (lang == "en-US") null else lang
            _uiState.value = _uiState.value.copy(contentLanguage = lang)
            syncLocalStateToCloud(silent = true)
        }
    }

    /** Set UI mode override: "auto", "tv", "tablet", "phone". Requires app restart. */
    fun setDeviceModeOverride(mode: String) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[com.arflix.tv.util.DEVICE_MODE_OVERRIDE_KEY] = mode
            }
            // Mirror to SharedPreferences so the next cold start's
            // pre-onCreate detectDeviceType() read picks it up synchronously.
            com.arflix.tv.util.setDeviceModeOverrideCache(
                context,
                if (mode == "auto") null else mode,
            )
            _uiState.value = _uiState.value.copy(deviceModeOverride = mode)
        }
    }

    fun setSkipProfileSelection(skip: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[com.arflix.tv.util.SKIP_PROFILE_SELECTION_KEY] = skip
            }
            _uiState.value = _uiState.value.copy(skipProfileSelection = skip)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setOledBlackBackground(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[com.arflix.tv.util.OLED_BLACK_BACKGROUND_KEY] = enabled
            }
            _uiState.value = _uiState.value.copy(oledBlackBackground = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun cycleFrameRateMatchingMode() {
        val current = normalizeFrameRateMode(_uiState.value.frameRateMatchingMode)
        val next = when (current) {
            "Off" -> "Seamless only"
            "Seamless only" -> "Always"
            else -> "Off"
        }
        setFrameRateMatchingMode(next)
    }

    fun setFrameRateMatchingMode(mode: String) {
        val normalized = normalizeFrameRateMode(mode)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[frameRateMatchingModeKey()] = normalized
            }
            _uiState.value = _uiState.value.copy(frameRateMatchingMode = normalized)
            syncLocalStateToCloud(silent = true)
        }
    }

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

    fun setSpoilerBlurEnabled(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[spoilerBlurKey()] = enabled }
            _uiState.value = _uiState.value.copy(spoilerBlurEnabled = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setTrailerAutoPlay(enabled: Boolean) {
        viewModelScope.launch { context.settingsDataStore.edit { it[trailerAutoPlayKey()] = enabled }; _uiState.value = _uiState.value.copy(trailerAutoPlay = enabled); syncLocalStateToCloud(silent = true) }
    }

    fun setTrailerSoundEnabled(enabled: Boolean) {
        viewModelScope.launch { context.settingsDataStore.edit { it[trailerSoundEnabledKey()] = enabled }; _uiState.value = _uiState.value.copy(trailerSoundEnabled = enabled); syncLocalStateToCloud(silent = true) }
    }

    fun setShowBudget(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[showBudgetKey()] = enabled }
            _uiState.value = _uiState.value.copy(showBudget = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setShowLoadingStats(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[showLoadingStatsKey()] = enabled }
            _uiState.value = _uiState.value.copy(showLoadingStats = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun cycleClockFormat() {
        val next = if (_uiState.value.clockFormat == "24h") "12h" else "24h"
        viewModelScope.launch {
            context.settingsDataStore.edit { it[clockFormatKey()] = next }
            _uiState.value = _uiState.value.copy(clockFormat = next)
            syncLocalStateToCloud(silent = true)
        }
    }

    /**
     * Cycle the focus border color through the rainbow palette.
     * Order: White → Red → Orange → Yellow → Green → Blue → Indigo → Violet → White
     */
    fun cycleFocusBorderColor() {
        val colors = listOf("White", "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet")
        val current = _uiState.value.focusBorderColor
        val nextIndex = (colors.indexOf(current) + 1) % colors.size
        val next = colors[nextIndex]
        viewModelScope.launch {
            context.settingsDataStore.edit { it[com.arflix.tv.util.FOCUS_BORDER_COLOR_KEY] = next }
            _uiState.value = _uiState.value.copy(focusBorderColor = next)
            syncLocalStateToCloud(silent = true)
        }
    }

    /**
     * Cycle the volume boost through discrete dB steps: 0 -> 3 -> 6 -> 9 -> 12 -> 15 -> 0.
     * 0 dB = LoudnessEnhancer disabled (no overhead, no clipping). Above +12 dB is
     * cropped to +15 dB since higher values tend to introduce audible distortion on
     * streaming content with already-compressed audio. Issue #88.
     */
    fun cycleVolumeBoost() {
        val current = _uiState.value.volumeBoostDb
        val next = when {
            current < 3 -> 3
            current < 6 -> 6
            current < 9 -> 9
            current < 12 -> 12
            current < 15 -> 15
            else -> 0
        }
        viewModelScope.launch {
            context.settingsDataStore.edit { it[volumeBoostDbKey()] = next.toString() }
            _uiState.value = _uiState.value.copy(volumeBoostDb = next)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun cycleSubtitleSize() {
        val next = when (_uiState.value.subtitleSize) { "Small" -> "Medium"; "Medium" -> "Large"; "Large" -> "Extra Large"; else -> "Small" }
        viewModelScope.launch { context.settingsDataStore.edit { it[subtitleSizeKey()] = next }; _uiState.value = _uiState.value.copy(subtitleSize = next); syncLocalStateToCloud(silent = true) }
    }

    fun cycleSubtitleColor() {
        val next = when (_uiState.value.subtitleColor) { "White" -> "Yellow"; "Yellow" -> "Green"; "Green" -> "Cyan"; else -> "White" }
        viewModelScope.launch { context.settingsDataStore.edit { it[subtitleColorKey()] = next }; _uiState.value = _uiState.value.copy(subtitleColor = next); syncLocalStateToCloud(silent = true) }
    }

    fun cycleSubtitleOffset() {
        val next = when (_uiState.value.subtitleOffset) { "Bottom" -> "Low"; "Low" -> "Medium"; "Medium" -> "High"; else -> "Bottom" }
        viewModelScope.launch { context.settingsDataStore.edit { it[subtitleOffsetKey()] = next }; _uiState.value = _uiState.value.copy(subtitleOffset = next); syncLocalStateToCloud(silent = true) }
    }

    fun cycleSubtitleStyle() {
        val next = when (_uiState.value.subtitleStyle) { "Bold" -> "Normal"; "Normal" -> "Background"; else -> "Bold" }
        viewModelScope.launch { context.settingsDataStore.edit { it[subtitleStyleKey()] = next }; _uiState.value = _uiState.value.copy(subtitleStyle = next); syncLocalStateToCloud(silent = true) }
    }

    // ── AI Subtitles ──────────────────────────────────────────────────────────

    fun setSubtitleAiEnabled(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitleAiEnabledKey] = enabled }
            _uiState.value = _uiState.value.copy(subtitleAiEnabled = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setSubtitleAiAutoSelect(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitleAiAutoSelectKey] = enabled }
            _uiState.value = _uiState.value.copy(subtitleAiAutoSelect = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setSubtitleRemoveHearingImpaired(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitleRemoveHearingImpairedKey] = enabled }
            _uiState.value = _uiState.value.copy(subtitleRemoveHearingImpaired = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun saveSubtitleAiApiKey(key: String) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitleAiApiKeyKey] = key.trim() }
            _uiState.value = _uiState.value.copy(subtitleAiApiKey = key.trim())
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setSubtitleAiModel(model: SubtitleAiModel) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitleAiModelKey] = model.name }
            _uiState.value = _uiState.value.copy(subtitleAiModel = model)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun startAiKeyServer() {
        viewModelScope.launch {
            stopAiKeyServerInternal()
            val server = AiKeyConfigServer.startOnAvailablePort(
                onKeyReceived = { key ->
                    viewModelScope.launch {
                        saveSubtitleAiApiKey(key)
                        _uiState.value = _uiState.value.copy(
                            aiKeyServerState = _uiState.value.aiKeyServerState.copy(keyReceived = true)
                        )
                        kotlinx.coroutines.delay(2500)
                        stopAiKeyServerInternal()
                        _uiState.value = _uiState.value.copy(aiKeyServerState = AiKeyServerState())
                    }
                }
            ) ?: return@launch
            aiKeyServer = server
            val ip = DeviceIpAddress.get(context) ?: "device-ip"
            val url = "http://$ip:${server.listeningPort}"
            val qr = runCatching { QrCodeGenerator.generate(url, 512) }.getOrNull()
            _uiState.value = _uiState.value.copy(
                aiKeyServerState = AiKeyServerState(isActive = true, serverUrl = url, qrBitmap = qr)
            )
        }
    }

    fun stopAiKeyServer() {
        stopAiKeyServerInternal()
        _uiState.value = _uiState.value.copy(aiKeyServerState = AiKeyServerState())
    }

    private fun stopAiKeyServerInternal() {
        aiKeyServer?.stop()
        aiKeyServer = null
    }

    private fun normalizeDnsProviderValue(raw: String?): String {
        return when (raw?.trim()?.lowercase()) {
            "system", "system dns", "system_dns" -> "system"
            "cloudflare", "cloudflare dns", "cloudflare_dns" -> "cloudflare"
            "google" -> "google"
            "adguard", "ad guard" -> "adguard"
            else -> "system"
        }
    }

    private fun dnsProviderLabel(value: String): String {
        return when (normalizeDnsProviderValue(value)) {
            "system" -> "System DNS"
            "google" -> "Google"
            "adguard" -> "AdGuard"
            else -> "Cloudflare"
        }
    }

    private fun dnsProviderValueFromLabel(label: String): String {
        return when (label.trim().lowercase()) {
            "system dns" -> "system"
            "google" -> "google"
            "adguard" -> "adguard"
            else -> "cloudflare"
        }
    }

    fun setDnsProvider(label: String) {
        val value = dnsProviderValueFromLabel(label)
        viewModelScope.launch {
            val currentValue = dnsProviderValueFromLabel(_uiState.value.dnsProvider)
            if (value == currentValue) {
                return@launch
            }

            withContext(Dispatchers.IO) {
                OkHttpProvider.setDnsProvider(OkHttpProvider.parseDnsProvider(value))
                // Warm up the new DNS provider's lazy init off the main thread
                // so the first image request doesn't block
                runCatching { OkHttpProvider.dns.lookup("image.tmdb.org") }
            }
            context.settingsDataStore.edit { prefs ->
                prefs[dnsProviderKey] = value
            }
            _uiState.value = _uiState.value.copy(
                dnsProvider = dnsProviderLabel(value)
            )

            // Replace Coil image loader with one using the new DNS
            val imageLoader = withContext(Dispatchers.IO) {
                OkHttpProvider.createCoilImageLoader(context)
            }
            Coil.setImageLoader(imageLoader)
        }
    }

    fun setIncludeSpecials(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[includeSpecialsKey()] = enabled
            }
            _uiState.value = _uiState.value.copy(includeSpecials = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun addQualityFilter(deviceName: String, regexPattern: String) {
        val trimmedRegex = regexPattern.trim()
        if (trimmedRegex.isBlank()) return
        if (runCatching { Regex(trimmedRegex) }.isFailure) return

        viewModelScope.launch {
            val next = _uiState.value.qualityFilters + QualityFilterConfig(
                id = java.util.UUID.randomUUID().toString(),
                deviceName = deviceName.trim(),
                regexPattern = trimmedRegex,
                enabled = true
            )
            saveQualityFilters(next)
        }
    }

    fun updateQualityFilter(filterId: String, deviceName: String, regexPattern: String) {
        val trimmedRegex = regexPattern.trim()
        if (trimmedRegex.isBlank()) return
        if (runCatching { Regex(trimmedRegex) }.isFailure) return

        viewModelScope.launch {
            val next = _uiState.value.qualityFilters.map { filter ->
                if (filter.id == filterId) {
                    filter.copy(
                        deviceName = deviceName.trim(),
                        regexPattern = trimmedRegex
                    )
                } else {
                    filter
                }
            }
            saveQualityFilters(next)
        }
    }

    fun cycleQualityFilterPreset() {
        viewModelScope.launch {
            val currentPreset = detectQualityFilterPreset(_uiState.value.qualityFilters)

            // Prevent losing custom filters by cycling into a preset
            if (currentPreset == QualityFilterPreset.CUSTOM) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Custom filters detected — use manual editing to modify",
                    toastType = ToastType.INFO
                )
                return@launch
            }

            val nextPreset = when (currentPreset) {
                QualityFilterPreset.OFF -> QualityFilterPreset.HD_1080_PLUS
                QualityFilterPreset.HD_1080_PLUS -> QualityFilterPreset.HD_1080_ONLY
                QualityFilterPreset.HD_1080_ONLY -> QualityFilterPreset.HD_720_PLUS
                QualityFilterPreset.HD_720_PLUS -> QualityFilterPreset.OFF
                QualityFilterPreset.CUSTOM -> return@launch // Already handled above
            }
            saveQualityFilters(nextPreset.toFilters())
        }
    }

    fun toggleQualityFilter(filterId: String) {
        viewModelScope.launch {
            val next = _uiState.value.qualityFilters.map { filter ->
                if (filter.id == filterId) filter.copy(enabled = !filter.enabled) else filter
            }
            saveQualityFilters(next)
        }
    }

    fun deleteQualityFilter(filterId: String) {
        viewModelScope.launch {
            val next = _uiState.value.qualityFilters.filterNot { it.id == filterId }
            saveQualityFilters(next)
        }
    }

    private suspend fun saveQualityFilters(filters: List<QualityFilterConfig>) {
        context.settingsDataStore.edit { prefs ->
            prefs[qualityFiltersKey] = gson.toJson(filters)
        }
        // Device-scoped capability filter: intentionally local and not cloud-synced.
        _uiState.value = _uiState.value.copy(
            qualityFilters = filters,
            qualityFilterPresetLabel = detectQualityFilterPreset(filters).label
        )
        // Update in-memory cache in StreamRepository to avoid DataStore reads in hot path
        streamRepository.updateQualityFiltersCache(filters)
    }

    private fun detectQualityFilterPreset(filters: List<QualityFilterConfig>): QualityFilterPreset {
        val enabled = filters.filter { it.enabled && it.regexPattern.isNotBlank() }
        if (enabled.isEmpty()) return QualityFilterPreset.OFF
        if (enabled.size != 1) return QualityFilterPreset.CUSTOM

        val single = enabled.first()
        return QualityFilterPreset.entries.firstOrNull { preset ->
            preset != QualityFilterPreset.OFF &&
                preset != QualityFilterPreset.CUSTOM &&
                preset.filterId == single.id &&
                preset.regexPattern == single.regexPattern
        } ?: QualityFilterPreset.CUSTOM
    }

    // ========== Addon Management ==========

    fun toggleAddon(addonId: String) {
        viewModelScope.launch {
            streamRepository.toggleAddon(addonId)
            val addonsAfterToggle = streamRepository.installedAddons.first()
            runCatching {
                catalogRepository.syncAddonCatalogs(addonsAfterToggle)
            }
            syncLocalStateToCloud(silent = true)
        }
    }

    fun addCustomAddon(url: String) {
        viewModelScope.launch {
            val result = streamRepository.addCustomAddon(url)
            result.onSuccess { addon ->
                // Small delay to let DataStore flush the write before reading back
                delay(150)
                val currentAddons = streamRepository.installedAddons.first()
                val importedCatalogs = addon.manifest?.catalogs?.size ?: 0
                runCatching {
                    catalogRepository.syncAddonCatalogs(currentAddons)
                }
                _uiState.value = _uiState.value.copy(
                    addons = currentAddons,
                    toastMessage = if (importedCatalogs > 0) {
                        "Added ${addon.name} ($importedCatalogs catalogs imported)"
                    } else {
                        "Added ${addon.name} (no catalogs exposed)"
                    },
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message?.takeIf { it.isNotBlank() } ?: "Failed to add addon",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    private fun observeAuthState() {
        viewModelScope.launch {
            authRepository.authState.collect { state ->
                val isLoggedIn = state is AuthState.Authenticated
                val email = (state as? AuthState.Authenticated)?.email
                val userId = (state as? AuthState.Authenticated)?.userId
                _uiState.value = _uiState.value.copy(
                    isLoggedIn = isLoggedIn,
                    accountEmail = email
                )
                if (!userId.isNullOrBlank() && lastCloudSyncedUserId != userId) {
                    lastCloudSyncedUserId = userId
                    val restoreResult = restoreCloudStateToLocalInternal(silent = true)
                    // Only seed cloud when there is truly no backup yet.
                    if (restoreResult == CloudRestoreResult.NO_BACKUP) {
                        syncLocalStateToCloud(silent = true, force = true)
                    }
                    if (pendingProfileSwitchAfterCloudLogin) {
                        pendingProfileSwitchAfterCloudLogin = false
                        _uiState.value = _uiState.value.copy(shouldSwitchProfile = true)
                    }
                } else if (!isLoggedIn) {
                    lastCloudSyncedUserId = null
                }
            }
        }
    }

    private fun observeIptvConfig() {
        viewModelScope.launch {
            iptvRepository.observeConfig().collect { config ->
                val current = _uiState.value
                if (current.iptvM3uUrl != config.m3uUrl || current.iptvEpgUrl != config.epgUrl || current.iptvStalkerUrl != config.stalkerPortalUrl || current.iptvStalkerMac != config.stalkerMacAddress || current.iptvPlaylists != config.playlists) {
                    _uiState.value = current.copy(
                        iptvM3uUrl = config.m3uUrl,
                        iptvEpgUrl = config.epgUrl,
                        iptvPlaylists = config.playlists,
                        iptvStalkerUrl = config.stalkerPortalUrl,
                        iptvStalkerMac = config.stalkerMacAddress
                    )
                }
                if (!hasObservedIptvConfig) {
                    hasObservedIptvConfig = true
                    lastObservedIptvM3u = config.m3uUrl
                    lastObservedStalkerUrl = config.stalkerPortalUrl
                    lastObservedIptvConfigSignature = config.syncSignature()
                    val hasAnyIptvConfig = config.m3uUrl.isNotBlank() ||
                        config.stalkerPortalUrl.isNotBlank() ||
                        config.playlists.any { it.enabled && it.m3uUrl.isNotBlank() }
                    if (!hasAnyIptvConfig) {
                        _uiState.value = _uiState.value.copy(
                            iptvChannelCount = 0,
                            iptvError = null,
                            iptvProgressText = null,
                            iptvProgressPercent = 0
                        )
                    } else if (hasAnyIptvConfig && iptvLoadJob?.isActive != true && _uiState.value.iptvChannelCount == 0) {
                        // Auto-refresh IPTV on startup/profile switch when configured but not loaded yet.
                        refreshIptv(showToast = false, force = false)
                    }
                    return@collect
                }

                val hasAnyConfig = config.m3uUrl.isNotBlank() ||
                    config.stalkerPortalUrl.isNotBlank() ||
                    config.playlists.any { it.enabled && it.m3uUrl.isNotBlank() }
                val configSignature = config.syncSignature()
                if (hasAnyConfig && configSignature != lastObservedIptvConfigSignature) {
                    lastObservedIptvM3u = config.m3uUrl
                    lastObservedStalkerUrl = config.stalkerPortalUrl
                    lastObservedIptvConfigSignature = configSignature
                    if (iptvLoadJob?.isActive != true) {
                        refreshIptv(showToast = false, force = false)
                    }
                } else if (!hasAnyConfig) {
                    lastObservedIptvM3u = ""
                    lastObservedStalkerUrl = ""
                    lastObservedIptvConfigSignature = configSignature
                    _uiState.value = _uiState.value.copy(
                        iptvChannelCount = 0,
                        iptvError = null,
                        iptvProgressText = null,
                        iptvProgressPercent = 0
                    )
                }
            }
        }
    }

    private fun observeCatalogs() {
        viewModelScope.launch {
            catalogRepository.observeCatalogs().collect { catalogs ->
                val effectiveCatalogs = if (catalogs.isEmpty()) {
                    catalogRepository.ensurePreinstalledDefaults(mediaRepository.getDefaultCatalogConfigs())
                } else {
                    catalogs
                }
                val visible = visibleCatalogs(effectiveCatalogs)
                if (_uiState.value.catalogs != visible) {
                    _uiState.value = _uiState.value.copy(catalogs = visible)
                }
            }
        }
    }

    private fun initializeCatalogs() {
        viewModelScope.launch {
            runCatching {
                catalogRepository.ensurePreinstalledDefaults(mediaRepository.getDefaultCatalogConfigs())
            }
        }
    }

    fun addCatalog(url: String) {
        viewModelScope.launch {
            val result = catalogRepository.addCustomCatalog(url)
            result.onSuccess { catalog ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Added ${catalog.title}",
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message ?: "Failed to add catalog",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun setCatalogSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(
            catalogSearchQuery = query,
            catalogSearchError = null
        )
    }

    fun searchCatalogLists(query: String = _uiState.value.catalogSearchQuery) {
        val normalizedQuery = query.trim()
        catalogSearchJob?.cancel()
        if (normalizedQuery.length < 2) {
            _uiState.value = _uiState.value.copy(
                catalogSearchResults = emptyList(),
                isCatalogSearching = false,
                catalogSearchError = if (normalizedQuery.isBlank()) null else "Type at least 2 characters"
            )
            return
        }

        catalogSearchJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isCatalogSearching = true,
                catalogSearchError = null
            )
            val result = catalogDiscoveryRepository.searchCatalogLists(normalizedQuery)
            result.onSuccess { lists ->
                _uiState.value = _uiState.value.copy(
                    catalogSearchResults = lists,
                    isCatalogSearching = false,
                    catalogSearchError = if (lists.isEmpty()) "No public Trakt lists found" else null
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    catalogSearchResults = emptyList(),
                    isCatalogSearching = false,
                    catalogSearchError = error.message ?: "Failed to search catalogs"
                )
            }
        }
    }

    fun clearCatalogDiscovery() {
        catalogSearchJob?.cancel()
        catalogSearchJob = null
        _uiState.value = _uiState.value.copy(
            catalogSearchQuery = "",
            catalogSearchResults = emptyList(),
            isCatalogSearching = false,
            catalogSearchError = null
        )
    }

    fun addDiscoveredCatalog(result: CatalogDiscoveryResult) {
        viewModelScope.launch {
            val addResult = catalogRepository.addCustomCatalog(result.sourceUrl)
            addResult.onSuccess { catalog ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Added ${catalog.title}",
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message ?: "Failed to add catalog",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun updateCatalog(catalogId: String, url: String) {
        viewModelScope.launch {
            val result = catalogRepository.updateCustomCatalog(catalogId, url)
            result.onSuccess { catalog ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Updated ${catalog.title}",
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message ?: "Failed to update catalog",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun removeCatalog(catalogId: String) {
        viewModelScope.launch {
            val result = catalogRepository.removeCustomCatalog(catalogId)
            result.onSuccess {
                // Refresh the catalog list in UI state after removal
                val updatedCatalogs = visibleCatalogs(catalogRepository.getCatalogs())
                _uiState.value = _uiState.value.copy(
                    catalogs = updatedCatalogs,
                    toastMessage = "Catalog removed",
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message ?: "Failed to remove catalog",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun renameCatalog(catalogId: String, newTitle: String) {
        viewModelScope.launch {
            val success = catalogRepository.renameCatalog(catalogId, newTitle)
            if (success) {
                syncLocalStateToCloud(silent = true)
            }
        }
    }

    fun moveCatalogUp(catalogId: String) {
        viewModelScope.launch {
            catalogRepository.moveCatalogUp(catalogId)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun moveCatalogDown(catalogId: String) {
        viewModelScope.launch {
            catalogRepository.moveCatalogDown(catalogId)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun saveIptvConfig(m3uUrl: String, epgUrl: String) {
        viewModelScope.launch {
            val trimmedM3u = m3uUrl.trim()
            val trimmedEpg = epgUrl.trim()
            if (trimmedM3u.isBlank()) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "M3U URL is required",
                    toastType = ToastType.ERROR
                )
                return@launch
            }

            // Prevent duplicate auto-refresh from observer right after save.
            lastObservedIptvM3u = trimmedM3u
            iptvRepository.saveConfig(trimmedM3u, trimmedEpg)
            // Push to cloud AFTER the DataStore write is confirmed, so all profiles
            // (not just the active one) have their latest IPTV config captured.
            syncLocalStateToCloud(silent = true)
            refreshIptv(showToast = true, configured = true, force = false)
        }
    }

    fun saveStalkerConfig(portalUrl: String, macAddress: String) {
        viewModelScope.launch {
            if (portalUrl.isBlank() || macAddress.isBlank()) {
                _uiState.value = _uiState.value.copy(toastMessage = "Portal URL and MAC address are required", toastType = ToastType.ERROR)
                return@launch
            }
            iptvRepository.saveStalkerConfig(portalUrl, macAddress)
            syncLocalStateToCloud(silent = true)
            refreshIptv(showToast = true, configured = true, force = true)
        }
    }

    /**
     * Save IPTV config while supporting explicit Xtream credentials.
     * Host/base is taken from M3U field; credentials are entered separately.
     */
    fun saveIptvConfigWithXtream(
        sourceOrHost: String,
        epgUrl: String,
        xtreamUsername: String,
        xtreamPassword: String
    ) {
        val host = sourceOrHost.trim()
        val epg = epgUrl.trim()
        val user = xtreamUsername.trim()
        val pass = xtreamPassword.trim()

        val usingXtream = user.isNotBlank() || pass.isNotBlank()
        if (usingXtream && (user.isBlank() || pass.isBlank())) {
            _uiState.value = _uiState.value.copy(
                toastMessage = "Xtream requires both username and password",
                toastType = ToastType.ERROR
            )
            return
        }

        val m3uInput = if (usingXtream) "$host $user $pass" else host
        // If no manual EPG was provided, derive Xtream XMLTV from host/user/pass.
        val epgInput = when {
            epg.isNotBlank() -> epg
            usingXtream -> "$host $user $pass"
            else -> epg
        }

        saveIptvConfig(m3uInput, epgInput)
    }

    fun saveIptvPlaylists(playlists: List<IptvPlaylistEntry>) {
        viewModelScope.launch {
            iptvRepository.savePlaylists(playlists)
            _uiState.value = _uiState.value.copy(
                iptvPlaylists = playlists.filter { it.m3uUrl.isNotBlank() },
                toastMessage = "IPTV playlists updated",
                toastType = ToastType.SUCCESS
            )
            syncLocalStateToCloud(silent = true)
        }
    }

    fun refreshIptv(showToast: Boolean = true, configured: Boolean = false, force: Boolean = true) {
        viewModelScope.launch {
            val currentConfig = iptvRepository.observeConfig().first()
            // Check legacy m3uUrl, multi-playlist entries, and Stalker portal
            val hasPlaylists = currentConfig.playlists.any { it.m3uUrl.isNotBlank() && it.enabled }
            if (currentConfig.m3uUrl.isBlank() && currentConfig.stalkerPortalUrl.isBlank() && !hasPlaylists) return@launch

            val runningJob = iptvLoadJob
            if (runningJob?.isActive == true) {
                if (!force) return@launch
                runningJob.cancelAndJoin()
            }

            iptvLoadJob = launch {
            _uiState.value = _uiState.value.copy(isIptvLoading = true, iptvError = null)
            runCatching {
                val snapshot = iptvRepository.loadSnapshot(
                    forcePlaylistReload = force,
                    forceEpgReload = false,
                    onProgress = { progress ->
                        _uiState.value = _uiState.value.copy(
                            isIptvLoading = true,
                            iptvProgressText = progress.message,
                            iptvProgressPercent = progress.percent ?: _uiState.value.iptvProgressPercent
                        )
                    }
                )
                val doneMsg = if (configured) {
                    snapshot.epgWarning ?: "Connected. Loaded ${snapshot.channels.size} channels."
                } else {
                    snapshot.epgWarning ?: "Refreshed ${snapshot.channels.size} channels."
                }
                _uiState.value = _uiState.value.copy(
                    isIptvLoading = false,
                    iptvChannelCount = snapshot.channels.size,
                    iptvError = null,
                    iptvStatusMessage = doneMsg,
                    iptvStatusType = if (snapshot.epgWarning != null) ToastType.INFO else ToastType.SUCCESS,
                    iptvProgressText = "Done",
                    iptvProgressPercent = 100,
                    toastMessage = if (showToast) {
                        if (configured) "IPTV configured (${snapshot.channels.size} channels)" else "IPTV refreshed (${snapshot.channels.size} channels)"
                    } else _uiState.value.toastMessage,
                    toastType = if (showToast) ToastType.SUCCESS else _uiState.value.toastType
                )
                launch {
                    runCatching { iptvRepository.warmXtreamVodCachesIfPossible() }
                }
            }.onFailure { error ->
                if (error is CancellationException) {
                    return@onFailure
                }
                val failMessage = if (configured) "Failed to load IPTV playlist" else "Failed to refresh IPTV"
                _uiState.value = _uiState.value.copy(
                    isIptvLoading = false,
                    iptvError = error.message ?: failMessage,
                    iptvStatusMessage = error.message ?: failMessage,
                    iptvStatusType = ToastType.ERROR,
                    iptvProgressText = null,
                    iptvProgressPercent = 0,
                    toastMessage = if (showToast) failMessage else _uiState.value.toastMessage,
                    toastType = if (showToast) ToastType.ERROR else _uiState.value.toastType
                )
            }
            }.also { job ->
                job.invokeOnCompletion {
                    if (iptvLoadJob === job) {
                        iptvLoadJob = null
                    }
                }
            }
        }
    }

    fun clearIptvConfig() {
        viewModelScope.launch {
            iptvLoadJob?.cancel()
            iptvRepository.clearConfig()
            _uiState.value = _uiState.value.copy(
                isIptvLoading = false,
                iptvChannelCount = 0,
                iptvError = null,
                iptvStatusMessage = "IPTV playlist removed",
                iptvStatusType = ToastType.SUCCESS,
                iptvProgressText = null,
                iptvProgressPercent = 0,
                toastMessage = "IPTV playlist removed",
                toastType = ToastType.SUCCESS
            )
            syncLocalStateToCloud(silent = true)
        }
    }

    fun removeAddon(addonId: String) {
        viewModelScope.launch {
            streamRepository.removeAddon(addonId)
            val addonsAfterRemove = streamRepository.installedAddons.first()
            runCatching {
                catalogRepository.syncAddonCatalogs(addonsAfterRemove)
            }
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setTorrServerBaseUrl(url: String) {
        viewModelScope.launch {
            streamRepository.setTorrServerBaseUrl(url)
            // No cloud sync needed; this is a local playback setting.
        }
    }

    fun startCloudAuth() {
        if (_uiState.value.isLoggedIn || _uiState.value.isCloudAuthWorking) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isCloudAuthWorking = true)
            ensureCloudAuthSession(startPolling = true)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isCloudAuthWorking = false,
                        showCloudPairDialog = true,
                        cloudUserCode = cloudUserCode,
                        cloudVerificationUrl = cloudVerificationUrl
                    )
                }
                .onFailure { error ->
                    clearCloudAuthSession()
                    _uiState.value = _uiState.value.copy(
                        isCloudAuthWorking = false,
                        toastMessage = error.message ?: "Failed to start cloud login",
                        toastType = ToastType.ERROR
                    )
                }
        }
    }

    fun cancelCloudAuth() {
        clearCloudAuthSession()
        _uiState.value = _uiState.value.copy(
            showCloudPairDialog = false,
            cloudUserCode = null,
            cloudVerificationUrl = null,
            showCloudEmailPasswordDialog = false,
            isCloudAuthWorking = false
        )
    }

    fun openCloudEmailPasswordDialog() {
        if (_uiState.value.isLoggedIn) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                showCloudPairDialog = false,
                showCloudEmailPasswordDialog = false,
                isCloudAuthWorking = true
            )
            ensureCloudAuthSession(startPolling = false)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        showCloudPairDialog = false,
                        showCloudEmailPasswordDialog = true,
                        isCloudAuthWorking = false
                    )
                }
                .onFailure { error ->
                    clearCloudAuthSession()
                    _uiState.value = _uiState.value.copy(
                        showCloudEmailPasswordDialog = false,
                        isCloudAuthWorking = false,
                        toastMessage = error.message ?: "Failed to start cloud sign-in",
                        toastType = ToastType.ERROR
                    )
                }
        }
    }

    fun closeCloudEmailPasswordDialog() {
        _uiState.value = _uiState.value.copy(showCloudEmailPasswordDialog = false)
    }

    fun completeCloudAuthWithEmailPassword(
        email: String,
        password: String,
        createAccount: Boolean
    ) {
        val trimmedEmail = AuthEmailValidator.normalize(email)
        AuthEmailValidator.validate(trimmedEmail, rejectDisposable = createAccount)?.let { message ->
            _uiState.value = _uiState.value.copy(
                toastMessage = message,
                toastType = ToastType.ERROR
            )
            return
        }
        if (password.isBlank()) {
            _uiState.value = _uiState.value.copy(
                toastMessage = "Password is required",
                toastType = ToastType.ERROR
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isCloudAuthWorking = true)
            val sessionReady = ensureCloudAuthSession(startPolling = false)
            if (sessionReady.isFailure) {
                clearCloudAuthSession()
                _uiState.value = _uiState.value.copy(
                    toastMessage = sessionReady.exceptionOrNull()?.message ?: "Cloud sign-in could not start. Try again.",
                    toastType = ToastType.ERROR,
                    isCloudAuthWorking = false
                )
                return@launch
            }

            val userCode = cloudUserCode
            if (userCode.isNullOrBlank()) {
                clearCloudAuthSession()
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Cloud sign-in session was unavailable. Try again.",
                    toastType = ToastType.ERROR,
                    isCloudAuthWorking = false
                )
                return@launch
            }

            tvDeviceAuthRepository.completeWithEmailPassword(
                userCode = userCode,
                email = trimmedEmail,
                password = password,
                intent = if (createAccount) "signup" else "signin"
            ).onSuccess {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Waiting for approval...",
                    toastType = ToastType.INFO,
                    showCloudEmailPasswordDialog = false,
                    isCloudAuthWorking = true
                )
                startCloudPolling()
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message ?: "Failed to link TV",
                    toastType = ToastType.ERROR,
                    isCloudAuthWorking = false
                )
            }
        }
    }

    private fun startCloudPolling() {
        val deviceCode = cloudDeviceCode ?: return
        cloudPollingJob?.cancel()
        cloudPollingJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isCloudAuthWorking = true)

            val now = System.currentTimeMillis()
            val intervalMs = cloudPollIntervalMs.coerceIn(500L, 3_000L)
            val hardDeadline = now + 10 * 60_000L // never poll longer than 10 minutes
            val deadline = listOf(
                cloudExpiresAtMs.takeIf { it > 0L } ?: (now + 60_000L),
                hardDeadline
            ).minOrNull() ?: hardDeadline

            while (System.currentTimeMillis() < deadline) {
                val status = tvDeviceAuthRepository.pollStatus(deviceCode).getOrNull()
                when (status?.status) {
                    TvDeviceAuthStatusType.PENDING -> Unit
                    TvDeviceAuthStatusType.APPROVED -> {
                        val access = status.accessToken
                        val refresh = status.refreshToken
                        if (access.isNullOrBlank() || refresh.isNullOrBlank()) {
                            _uiState.value = _uiState.value.copy(
                                isCloudAuthWorking = false,
                                toastMessage = status.message ?: "Approved, but tokens were missing. Try again.",
                                toastType = ToastType.ERROR
                            )
                            return@launch
                        }

                        val tokenImport = authRepository.signInWithSessionTokens(access, refresh)
                        if (tokenImport.isSuccess) {
                            // TV auth previously stopped at token import, relying only on
                            // auth-state observation for restore. On slower networks/session
                            // propagation this could fail once and never retry, leaving a
                            // freshly signed-in device with empty addons/settings/CW.
                            // Now with timeout protection and retry.
                            var restoreResult = withTimeoutOrNull(15_000L) {
                                restoreCloudStateToLocalInternal(silent = true)
                            } ?: CloudRestoreResult.FAILED
                            if (restoreResult == CloudRestoreResult.FAILED) {
                                delay(1200)
                                restoreResult = withTimeoutOrNull(15_000L) {
                                    restoreCloudStateToLocalInternal(silent = true)
                                } ?: CloudRestoreResult.FAILED
                            }

                            clearCloudAuthSession(cancelPolling = false)
                            pendingProfileSwitchAfterCloudLogin = false
                            _uiState.value = _uiState.value.copy(
                                isCloudAuthWorking = false,
                                showCloudPairDialog = false,
                                showCloudEmailPasswordDialog = false,
                                cloudUserCode = null,
                                cloudVerificationUrl = null,
                                shouldSwitchProfile = true,
                                toastMessage = when (restoreResult) {
                                    CloudRestoreResult.RESTORED -> "Signed in and restored from cloud"
                                    CloudRestoreResult.NO_BACKUP -> "Signed in successfully"
                                    CloudRestoreResult.FAILED -> "Signed in, but cloud restore failed"
                                },
                                toastType = when (restoreResult) {
                                    CloudRestoreResult.FAILED -> ToastType.ERROR
                                    else -> ToastType.SUCCESS
                                }
                            )
                            return@launch
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isCloudAuthWorking = false,
                                toastMessage = tokenImport.exceptionOrNull()?.message ?: "Failed to import session tokens",
                                toastType = ToastType.ERROR
                            )
                            return@launch
                        }
                    }
                    TvDeviceAuthStatusType.EXPIRED -> {
                        _uiState.value = _uiState.value.copy(
                            isCloudAuthWorking = false,
                            showCloudPairDialog = false,
                            showCloudEmailPasswordDialog = false,
                            cloudUserCode = null,
                            cloudVerificationUrl = null,
                            toastMessage = status.message ?: "Cloud sign-in expired. Try again.",
                            toastType = ToastType.ERROR
                        )
                        clearCloudAuthSession(cancelPolling = false)
                        return@launch
                    }
                    TvDeviceAuthStatusType.ERROR -> {
                        _uiState.value = _uiState.value.copy(
                            isCloudAuthWorking = false,
                            toastMessage = status.message ?: "Cloud sign-in failed. Try again.",
                            toastType = ToastType.ERROR
                        )
                        return@launch
                    }
                    else -> Unit
                }
                delay(intervalMs)
            }

            _uiState.value = _uiState.value.copy(
                isCloudAuthWorking = false,
                toastMessage = "Sign-in did not complete. Try again.",
                toastType = ToastType.ERROR
            )
            clearCloudAuthSession(cancelPolling = false)
        }
    }

    private fun hasActiveCloudAuthSession(): Boolean {
        val hasCodes = !cloudDeviceCode.isNullOrBlank() && !cloudUserCode.isNullOrBlank()
        if (!hasCodes) return false
        return cloudExpiresAtMs <= 0L || System.currentTimeMillis() < cloudExpiresAtMs
    }

    private fun applyCloudAuthSession(session: TvDeviceAuthSession) {
        cloudDeviceCode = session.deviceCode
        cloudUserCode = session.userCode
        cloudVerificationUrl = session.verificationUrl
        cloudPollIntervalMs = (session.intervalSeconds.coerceIn(1, 10) * 1000L)
        cloudExpiresAtMs = System.currentTimeMillis() + (session.expiresInSeconds.coerceAtLeast(30) * 1000L)
    }

    private fun clearCloudAuthSession(cancelPolling: Boolean = true) {
        cloudDeviceCode = null
        cloudUserCode = null
        cloudVerificationUrl = null
        cloudPollIntervalMs = 800L
        cloudExpiresAtMs = 0L
        if (cancelPolling) {
            cloudPollingJob?.cancel()
        }
        cloudPollingJob = null
    }

    private suspend fun ensureCloudAuthSession(startPolling: Boolean): Result<Unit> {
        if (hasActiveCloudAuthSession()) {
            if (startPolling && cloudPollingJob?.isActive != true) {
                startCloudPolling()
            }
            return Result.success(Unit)
        }

        clearCloudAuthSession()
        return tvDeviceAuthRepository.startSession().map { session ->
            applyCloudAuthSession(session)
            if (startPolling) {
                startCloudPolling()
            }
        }
    }

    fun connectHomeServer(serverUrl: String, username: String, password: String, displayName: String = "") {
        if (_uiState.value.isHomeServerConnecting) return
        viewModelScope.launch {
            cancelPlexHomeServerAuth(updateState = false)
            _uiState.value = _uiState.value.copy(
                isHomeServerConnecting = true,
                homeServerError = null,
                toastMessage = "Connecting Home Server...",
                toastType = ToastType.INFO
            )
            val result = homeServerRepository.connect(serverUrl, username, password, displayName)
            result.onSuccess { connection ->
                syncHomeServerCatalogsFromConnections()
                val connections = homeServerRepository.currentConnections()
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    homeServerConnection = connection,
                    homeServerConnections = connections,
                    homeServerError = null,
                    toastMessage = "Home Server connected",
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    homeServerError = error.message ?: "Home Server connection failed",
                    toastMessage = error.message ?: "Home Server connection failed",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun startPlexHomeServerAuth(serverUrl: String, displayName: String = "") {
        if (_uiState.value.isHomeServerConnecting || _uiState.value.isPlexHomeServerPolling) return
        val trimmedUrl = serverUrl.trim()

        viewModelScope.launch {
            cancelPlexHomeServerAuth(updateState = false)
            plexHomeServerUrl = trimmedUrl
            plexHomeServerDisplayName = displayName.trim()
            _uiState.value = _uiState.value.copy(
                isHomeServerConnecting = true,
                homeServerError = null,
                plexHomeServerAuth = null,
                isPlexHomeServerPolling = false,
                toastMessage = "Starting code sign in...",
                toastType = ToastType.INFO
            )
            val result = homeServerRepository.startPlexPinAuth()
            result.onSuccess { session ->
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    plexHomeServerAuth = session,
                    isPlexHomeServerPolling = true,
                    homeServerError = null,
                    toastMessage = "Enter the code to connect",
                    toastType = ToastType.INFO
                )
                startPlexHomeServerPolling(trimmedUrl, session)
            }.onFailure { error ->
                plexHomeServerUrl = null
                plexHomeServerDisplayName = null
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    plexHomeServerAuth = null,
                    isPlexHomeServerPolling = false,
                    homeServerError = error.message ?: "Code sign in failed",
                    toastMessage = error.message ?: "Code sign in failed",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    private fun startPlexHomeServerPolling(serverUrl: String, session: PlexPinAuthSession) {
        plexHomeServerPollingJob?.cancel()
        plexHomeServerPollingJob = viewModelScope.launch {
            val deadline = System.currentTimeMillis() + (session.expiresIn.coerceIn(60, 900) * 1000L)
            var lastFailure: String? = null
            while (System.currentTimeMillis() < deadline) {
                delay(session.interval.coerceIn(2, 15) * 1000L)
                val tokenResult = homeServerRepository.pollPlexPinAuth(session.id)
                val accountToken = tokenResult.getOrElse { error ->
                    lastFailure = error.message
                    null
                }
                if (accountToken.isNullOrBlank()) {
                    continue
                }

                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = true,
                    toastMessage = "Connecting server...",
                    toastType = ToastType.INFO
                )
                val connectionResult = homeServerRepository.connectPlexAccount(
                    accountToken = accountToken,
                    preferredServerUrl = serverUrl,
                    displayName = plexHomeServerDisplayName.orEmpty()
                )
                connectionResult.onSuccess { connection ->
                    syncHomeServerCatalogsFromConnections()
                    val connections = homeServerRepository.currentConnections()
                    plexHomeServerUrl = null
                    plexHomeServerDisplayName = null
                    _uiState.value = _uiState.value.copy(
                        isHomeServerConnecting = false,
                        homeServerConnection = connection,
                        homeServerConnections = connections,
                        plexHomeServerAuth = null,
                        isPlexHomeServerPolling = false,
                        homeServerError = null,
                        toastMessage = "Server connected",
                        toastType = ToastType.SUCCESS
                    )
                    syncLocalStateToCloud(silent = true)
                    return@launch
                }.onFailure { error ->
                    plexHomeServerUrl = null
                    plexHomeServerDisplayName = null
                    _uiState.value = _uiState.value.copy(
                        isHomeServerConnecting = false,
                        plexHomeServerAuth = null,
                        isPlexHomeServerPolling = false,
                        homeServerError = error.message ?: "Server connection failed",
                        toastMessage = error.message ?: "Server connection failed",
                        toastType = ToastType.ERROR
                    )
                    return@launch
                }
            }

            plexHomeServerUrl = null
            plexHomeServerDisplayName = null
            _uiState.value = _uiState.value.copy(
                isHomeServerConnecting = false,
                plexHomeServerAuth = null,
                isPlexHomeServerPolling = false,
                homeServerError = lastFailure ?: "Activation code expired",
                toastMessage = lastFailure ?: "Activation code expired",
                toastType = ToastType.ERROR
            )
        }
    }

    fun cancelPlexHomeServerAuth(updateState: Boolean = true) {
        plexHomeServerPollingJob?.cancel()
        plexHomeServerPollingJob = null
        plexHomeServerUrl = null
        plexHomeServerDisplayName = null
        if (updateState) {
            _uiState.value = _uiState.value.copy(
                isHomeServerConnecting = false,
                plexHomeServerAuth = null,
                isPlexHomeServerPolling = false
            )
        }
    }

    fun testHomeServerConnection() {
        if (_uiState.value.isHomeServerConnecting) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isHomeServerConnecting = true,
                homeServerError = null
            )
            val result = homeServerRepository.testConnections()
            result.onSuccess { connections ->
                syncHomeServerCatalogsFromConnections()
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    homeServerConnection = connections.firstOrNull(),
                    homeServerConnections = connections,
                    homeServerError = null,
                    toastMessage = "Home Server is reachable",
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    homeServerError = error.message ?: "Home Server test failed",
                    toastMessage = error.message ?: "Home Server test failed",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun disconnectHomeServer() {
        viewModelScope.launch {
            cancelPlexHomeServerAuth(updateState = false)
            homeServerRepository.disconnect()
            catalogRepository.syncHomeServerCatalogs(emptyList())
            _uiState.value = _uiState.value.copy(
                homeServerConnection = null,
                homeServerConnections = emptyList(),
                plexHomeServerAuth = null,
                isPlexHomeServerPolling = false,
                homeServerError = null,
                toastMessage = "Home Server disconnected",
                toastType = ToastType.INFO
            )
            syncLocalStateToCloud(silent = true)
        }
    }

    private suspend fun syncHomeServerCatalogsFromConnections() {
        val candidates = homeServerRepository.getCatalogCandidates()
        catalogRepository.syncHomeServerCatalogs(candidates)
    }

    fun syncLocalStateToCloud(silent: Boolean = false, force: Boolean = false) {
        if (!force && !_uiState.value.isLoggedIn) return
        if (authRepository.getCurrentUserId().isNullOrBlank()) return
        cloudSyncRepository.markLocalStateDirty()
        viewModelScope.launch {
            if (!force) {
                delay(350)
            }
            var result = cloudSyncRepository.pushToCloud()
            if (result.isFailure) {
                delay(1200)
                result = cloudSyncRepository.pushToCloud()
            }

            if (!silent && result.isSuccess) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Cloud sync complete",
                    toastType = ToastType.SUCCESS
                )
            } else if (!silent && result.isFailure) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = result.exceptionOrNull()?.message ?: "Cloud sync failed",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun syncCloudStateToLocal(silent: Boolean = false) {
        if (!_uiState.value.isLoggedIn) return
        viewModelScope.launch {
            restoreCloudStateToLocalInternal(silent = silent)
        }
    }

    fun forceCloudSyncNow() {
        if (_uiState.value.isForceCloudSyncing) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isForceCloudSyncing = true,
                toastMessage = "Forcing cloud sync...",
                toastType = ToastType.INFO
            )

            if (!ensureCloudSyncSession()) {
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    toastMessage = "Sign in to ARVIO Cloud first",
                    toastType = ToastType.INFO
                )
                return@launch
            }

            // Push local state first (30s timeout), then pull remote state so this device ends
            // with the server-authoritative snapshot after upload.
            var pushResult = withTimeoutOrNull(30_000L) {
                cloudSyncRepository.pushToCloud()
            }
            if (pushResult == null) {
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    toastMessage = "Cloud sync upload timed out — try again",
                    toastType = ToastType.ERROR
                )
                return@launch
            }
            if (pushResult.isFailure) {
                delay(1200)
                pushResult = withTimeoutOrNull(30_000L) {
                    cloudSyncRepository.pushToCloud()
                }
            }
            if (pushResult == null || pushResult.isFailure) {
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    toastMessage = pushResult?.exceptionOrNull()?.message ?: "Cloud sync failed while uploading",
                    toastType = ToastType.ERROR
                )
                return@launch
            }

            // Pull from cloud with timeout and single retry on failure
            var restoreResult = withTimeoutOrNull(30_000L) {
                restoreCloudStateToLocalInternal(silent = true)
            } ?: CloudRestoreResult.FAILED

            if (restoreResult == CloudRestoreResult.FAILED) {
                delay(1200)
                restoreResult = withTimeoutOrNull(30_000L) {
                    restoreCloudStateToLocalInternal(silent = true)
                } ?: CloudRestoreResult.FAILED
            }

            _uiState.value = _uiState.value.copy(
                isForceCloudSyncing = false,
                toastMessage = when (restoreResult) {
                    CloudRestoreResult.RESTORED -> "Cloud sync complete"
                    CloudRestoreResult.NO_BACKUP -> "Cloud sync complete (no backup to restore)"
                    CloudRestoreResult.FAILED -> "Upload complete, but restore failed"
                },
                toastType = if (restoreResult == CloudRestoreResult.FAILED) {
                    ToastType.ERROR
                } else {
                    ToastType.SUCCESS
                }
            )
        }
    }

    private suspend fun ensureCloudSyncSession(): Boolean {
        if (authRepository.getCurrentUserId().isNullOrBlank()) {
            authRepository.checkAuthState()
        }
        if (authRepository.getCurrentUserId().isNullOrBlank()) {
            authRepository.getAccessToken()
            authRepository.checkAuthState()
        }
        return authRepository.getCurrentUserId().isNullOrBlank().not()
    }

    private suspend fun restoreCloudStateToLocalInternal(silent: Boolean): CloudRestoreResult {
        return when (cloudSyncRepository.pullFromCloud()) {
            CloudSyncRepository.RestoreResult.RESTORED -> {
                loadSettings()
                runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                if (!silent) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = "Cloud restore complete",
                        toastType = ToastType.SUCCESS
                    )
                }
                CloudRestoreResult.RESTORED
            }
            CloudSyncRepository.RestoreResult.NO_BACKUP -> {
                if (!silent) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = "No cloud backup found",
                        toastType = ToastType.INFO
                    )
                }
                CloudRestoreResult.NO_BACKUP
            }
            CloudSyncRepository.RestoreResult.FAILED -> {
                if (!silent) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = "Cloud restore failed",
                        toastType = ToastType.ERROR
                    )
                }
                CloudRestoreResult.FAILED
            }
        }
    }

    fun onCloudProfileSwitchHandled() {
        if (_uiState.value.shouldSwitchProfile) {
            _uiState.value = _uiState.value.copy(shouldSwitchProfile = false)
        }
    }

    fun checkForAppUpdates(force: Boolean, showNoUpdateFeedback: Boolean) {
        if (!appUpdateRepository.supportsSelfUpdate()) {
            _uiState.value = _uiState.value.copy(showAppUpdateDialog = force)
            return
        }

        viewModelScope.launch {
            updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.Checking)
            val result = appUpdateRepository.getLatestUpdate()
            updatePreferences.setLastCheckAtMs(System.currentTimeMillis())

            result.onSuccess { update ->
                val localVer = appUpdateRepository.getInstalledVersionName()
                val isNewer = com.arflix.tv.updater.VersionUtils.isRemoteNewer(update.tag, localVer)

                if (isNewer) {
                    updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.UpdateAvailable(update))
                    // If force is true, we want to show the dialog even if ignored
                    if (force) {
                        _uiState.value = _uiState.value.copy(showAppUpdateDialog = true)
                    }
                } else {
                    if (showNoUpdateFeedback) {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "You already have the latest version",
                            toastType = ToastType.INFO
                        )
                    }
                    updateStatusManager.reset()
                }
            }.onFailure { error ->
                if (showNoUpdateFeedback) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = error.message ?: "Failed to check for updates",
                        toastType = ToastType.ERROR
                    )
                }
                updateStatusManager.reset()
            }
        }
    }

    fun dismissAppUpdateDialog() {
        _uiState.value = _uiState.value.copy(showAppUpdateDialog = false, showUnknownSourcesDialog = false)
    }

    fun ignoreAppUpdate() {
        val currentStatus = updateStatusManager.status.value
        if (currentStatus is com.arflix.tv.updater.UpdateStatus.UpdateAvailable) {
            updateStatusManager.sessionIgnoredTag = currentStatus.update.tag
            viewModelScope.launch {
                updatePreferences.setIgnoredTag(currentStatus.update.tag)
            }
        }
        _uiState.value = _uiState.value.copy(showAppUpdateDialog = false)
        updateStatusManager.reset()
    }

    private var downloadJob: kotlinx.coroutines.Job? = null

    fun downloadAppUpdate() {
        val currentStatus = updateStatusManager.status.value
        val update = when (currentStatus) {
            is com.arflix.tv.updater.UpdateStatus.UpdateAvailable -> currentStatus.update
            is com.arflix.tv.updater.UpdateStatus.Failure -> currentStatus.update
            else -> return
        } ?: return

        if (!appUpdateRepository.supportsSelfUpdate()) return

        downloadJob = viewModelScope.launch {
            updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.Downloading(0f, update))

            val safeName = update.assetName.replace(Regex("[^a-zA-Z0-9._-]"), "_")
            val dest = File(File(context.cacheDir, "updates"), safeName)

            val result = withContext(Dispatchers.IO) {
                apkDownloader.download(update.assetUrl, dest) { downloaded, total ->
                    val progress = if (total != null && total > 0L) {
                        (downloaded.toFloat() / total.toFloat()).coerceIn(0f, 1f)
                    } else null

                    updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.Downloading(progress, update))
                }
            }

            result.onSuccess { file ->
                updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.ReadyToInstall(file.absolutePath, update))
                installAppUpdateOrRequestPermission()
            }.onFailure { error ->
                updateStatusManager.updateStatus(
                    com.arflix.tv.updater.UpdateStatus.Failure(error.message ?: "Download failed", update)
                )
            }
        }
    }

    fun cancelDownloadAppUpdate() {
        downloadJob?.cancel()
        downloadJob = null
        val currentStatus = updateStatusManager.status.value
        if (currentStatus is com.arflix.tv.updater.UpdateStatus.Downloading) {
            updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.UpdateAvailable(currentStatus.update))
        }
    }

    fun installAppUpdateOrRequestPermission() {
        val currentStatus = updateStatusManager.status.value
        if (currentStatus !is com.arflix.tv.updater.UpdateStatus.ReadyToInstall && currentStatus !is com.arflix.tv.updater.UpdateStatus.Failure) return

        val apkPath = if (currentStatus is com.arflix.tv.updater.UpdateStatus.ReadyToInstall) currentStatus.apkPath else return
        val update = currentStatus.update
        val apkFile = File(apkPath)

        if (!apkFile.exists()) {
            updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.Failure("Downloaded file is missing", update))
            return
        }

        if (!ApkInstaller.canRequestPackageInstalls(context)) {
            _uiState.value = _uiState.value.copy(showUnknownSourcesDialog = true, showAppUpdateDialog = false)
            return
        }

        val conflictMsg = ApkInstaller.checkSignatureConflict(context, apkFile)
        if (conflictMsg != null) {
            updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.Failure(conflictMsg, update))
            return
        }

        ApkInstaller.launchInstall(context, apkFile)
        updateStatusManager.updateStatus(com.arflix.tv.updater.UpdateStatus.Installing(update))

        viewModelScope.launch {
            updatePreferences.setIgnoredTag(update.tag)
        }
    }

    fun openUnknownSourcesSettings() {
        ApkInstaller.buildUnknownSourcesSettingsIntent(context)?.let { intent ->
            context.startActivity(intent)
        }
    }

    // ========== Trakt Authentication ==========

    fun startTraktAuth() {
        val current = _uiState.value
        if (current.isTraktAuthStarting || current.isTraktPolling) return

        viewModelScope.launch {
            traktPollingJob?.cancel()
            _uiState.value = _uiState.value.copy(
                traktCode = null,
                isTraktAuthStarting = true,
                isTraktPolling = false,
                toastMessage = null
            )

            try {
                traktRepository.logout()
                val deviceCode = withContext(Dispatchers.IO) {
                    traktRepository.getDeviceCode()
                }
                _uiState.value = _uiState.value.copy(
                    traktCode = deviceCode,
                    isTraktAuthStarting = false,
                    isTraktAuthenticated = false,
                    isTraktPolling = true
                )

                // Start polling for token
                startTraktPolling(deviceCode)
            } catch (e: Exception) {
                System.err.println("SettingsVM: failed to start Trakt auth: ${e.message}")
                val message = when (e) {
                    is retrofit2.HttpException -> "Trakt activation failed (${e.code()})"
                    else -> e.message?.takeIf { it.isNotBlank() } ?: "Trakt activation failed"
                }
                _uiState.value = _uiState.value.copy(
                    traktCode = null,
                    isTraktAuthStarting = false,
                    isTraktPolling = false,
                    toastMessage = message,
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun reconnectTrakt() {
        viewModelScope.launch {
            cancelTraktAuth()
            traktRepository.logout()
            _uiState.value = _uiState.value.copy(
                isTraktAuthenticated = false,
                traktExpiration = null
            )
            startTraktAuth()
        }
    }

    private fun startTraktPolling(deviceCode: TraktDeviceCode) {
        traktPollingJob?.cancel()
        traktPollingJob = viewModelScope.launch {
            val expiresAt = System.currentTimeMillis() + (deviceCode.expiresIn * 1000)
            var lastFailure: String? = null

            while (System.currentTimeMillis() < expiresAt) {
                delay(deviceCode.interval * 1000L)

                try {
                    traktRepository.pollForToken(deviceCode.deviceCode)

                    // Get the expiration date
                    val expirationDate = traktRepository.getTokenExpirationDate()

                    // Success!
                    _uiState.value = _uiState.value.copy(
                        isTraktAuthenticated = true,
                        traktCode = null,
                        isTraktAuthStarting = false,
                        isTraktPolling = false,
                        traktExpiration = expirationDate,
                        toastMessage = "Trakt connected successfully",
                        toastType = ToastType.SUCCESS
                    )
                    traktRepository.clearContinueWatchingCache()
                    runCatching { traktRepository.getContinueWatching() }
                    performFullSync(silent = true)
                    syncLocalStateToCloud(silent = true, force = true)
                    runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                    return@launch
                } catch (e: Exception) {
                    // Keep polling on 400 (pending) - user hasn't entered code yet
                    // Check both HttpException code and message for 400
                    val is400 = when (e) {
                        is retrofit2.HttpException -> e.code() == 400
                        else -> e.message?.contains("400") == true ||
                                e.message?.contains("pending") == true
                    }
                    if (!is400) {
                        lastFailure = when (e) {
                            is retrofit2.HttpException -> "Trakt authorization failed (${e.code()})"
                            else -> e.message?.takeIf { it.isNotBlank() } ?: "Trakt authorization failed"
                        }
                        break
                    }
                    // 400 = pending, continue polling
                }
            }

            // Expired or failed
            _uiState.value = _uiState.value.copy(
                traktCode = null,
                isTraktAuthStarting = false,
                isTraktPolling = false,
                toastMessage = lastFailure ?: "Trakt activation code expired",
                toastType = ToastType.ERROR
            )
        }
    }

    fun cancelTraktAuth() {
        traktPollingJob?.cancel()
        _uiState.value = _uiState.value.copy(
            traktCode = null,
            isTraktAuthStarting = false,
            isTraktPolling = false
        )
    }

    fun disconnectTrakt() {
        viewModelScope.launch {
            cancelTraktAuth()
            traktRepository.logout()
            _uiState.value = _uiState.value.copy(
                isTraktAuthenticated = false,
                traktExpiration = null,
                toastMessage = "Trakt disconnected",
                toastType = ToastType.SUCCESS
            )
            syncLocalStateToCloud(silent = true, force = true)
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }

    fun logout() {
        viewModelScope.launch {
            cancelCloudAuth()
            authRepository.signOut()
            _uiState.value = _uiState.value.copy(
                toastMessage = "Signed out",
                toastType = ToastType.SUCCESS
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        traktPollingJob?.cancel()
        stopAiKeyServerInternal()
        plexHomeServerPollingJob?.cancel()
    }
}

private fun IptvConfig.syncSignature(): String {
    val playlistsSignature = playlists
        .sortedBy { it.id }
        .joinToString("|") { playlist ->
            listOf(
                playlist.id,
                playlist.name,
                playlist.m3uUrl,
                playlist.epgUrl,
                playlist.enabled.toString()
            ).joinToString("~")
        }
    return listOf(
        m3uUrl,
        epgUrl,
        stalkerPortalUrl,
        stalkerMacAddress,
        playlistsSignature
    ).joinToString("||")
}
