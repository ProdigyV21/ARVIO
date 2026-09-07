package com.arflix.tv.ui.screens.settings

import android.content.Context
import android.graphics.Bitmap
import coil.Coil
import androidx.annotation.StringRes
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.R
import com.arflix.tv.server.AiKeyConfigServer
import com.arflix.tv.ui.screens.player.SubtitleFontOption
import com.arflix.tv.ui.screens.player.subtitles.SubtitleAiModel
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.DeviceIpAddress
import com.arflix.tv.util.DiagnosticsManager
import com.arflix.tv.util.QrCodeGenerator
import com.arflix.tv.data.api.TraktDeviceCode
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogDiscoveryResult
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogPackManifest
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
import com.arflix.tv.data.repository.MAX_STALKER_PORTALS
import com.arflix.tv.data.repository.normalizeIptvSortOrder
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.arflix.tv.data.repository.StalkerPortalEntry
import com.arflix.tv.data.repository.StalkerPortalSupport
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
import com.arflix.tv.util.resolveAppLanguage
import com.arflix.tv.util.IPTV_EPG_VOD_ACTIONS_ENABLED_KEY
import com.arflix.tv.util.IPTV_VOD_SEARCH_ENABLED_KEY
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

internal data class SettingsIptvRefreshPolicy(
    val forcePlaylistReload: Boolean,
    val forceEpgReload: Boolean,
    val allowNetworkEpgFetch: Boolean,
)

internal fun settingsIptvRefreshPolicy(force: Boolean): SettingsIptvRefreshPolicy =
    SettingsIptvRefreshPolicy(
        forcePlaylistReload = force,
        forceEpgReload = force,
        allowNetworkEpgFetch = force,
    )

/**
 * A user-facing settings message, kept as a resource reference until a composable renders it.
 * A ViewModel only has the application context, whose resources follow the SYSTEM language
 * instead of the language selected in the app, so resolving here would show the wrong
 * language whenever the two differ. Mirrors [com.arflix.tv.ui.screens.player.PlayerMessage].
 */
sealed interface SettingsMessage {
    /** A localizable message. [formatArgs] may itself contain [SettingsMessage] entries. */
    data class Res(
        @param:StringRes val resourceId: Int,
        val formatArgs: List<Any> = emptyList()
    ) : SettingsMessage

    /** Text without a resource (e.g. a platform exception message); shown as it is. */
    data class Raw(val text: String) : SettingsMessage
}

/** Wraps a non-null error text from a repository/exception into a [SettingsMessage]. */
private fun String?.orMessage(fallback: SettingsMessage): SettingsMessage =
    this?.takeIf { it.isNotBlank() }?.let { SettingsMessage.Raw(it) } ?: fallback

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
    val customUserAgent: String = "",
    val subtitleSize: String = "Medium",
    val subtitleColor: String = "White",
    val subtitleStyle: String = "Bold",
    val subtitleFont: String = SubtitleFontOption.DefaultPreference,
    val subtitleOffset: String = "Bottom",
    val subtitleStylized: Boolean = true,
    val filterSubtitlesByLanguage: Boolean = true,
    val secondarySubtitle: String = "Off",
    val trailerAutoPlay: Boolean = false,
    val trailerSoundEnabled: Boolean = false,
    val trailerDelaySeconds: Int = 2,
    val trailerInCards: Boolean = true,
    val showBudget: Boolean = true,
    val showEpisodeRatings: Boolean = true,
    /** Pin the IPTV "Favorite TV" row to the top of the home screen. */
    val iptvFavoritesOnHome: Boolean = true,
    // Volume boost in decibels (0 = off, up to 15 dB). Applied via system LoudnessEnhancer
    // attached to the ExoPlayer audio session. Issue #88.
    val volumeBoostDb: Int = 0,
    val showLoadingStats: Boolean = true,
    val diagnosticsSharingEnabled: Boolean = true,
    val includeSpecials: Boolean = false,
    val isLoggedIn: Boolean = false,
    val accountEmail: String? = null,
    val showCloudPairDialog: Boolean = false,
    val cloudUserCode: String? = null,
    val cloudVerificationUrl: String? = null,
    val showCloudEmailPasswordDialog: Boolean = false,
    val isCloudAuthWorking: Boolean = false,
    val isForceCloudSyncing: Boolean = false,
    val lastCloudSyncStatus: SettingsMessage? = null,
    val shouldSwitchProfile: Boolean = false,
    val watchlistCount: Int = 0,
    val historyCount: Int = 0,
    // Trakt
    val isTraktAuthenticated: Boolean = false,
    val traktCode: TraktDeviceCode? = null,
    val isTraktAuthStarting: Boolean = false,
    val isTraktPolling: Boolean = false,
    val traktExpiration: String? = null,
    val traktUsername: String? = null,
    // MDBList (alternative remote sync provider)
    val isMdbListConnected: Boolean = false,
    val mdbListConnecting: Boolean = false,
    val mdbListUsername: String? = null,
    // Simkl (alternative remote sync provider)
    val isSimklConnected: Boolean = false,
    val isSimklAuthStarting: Boolean = false,
    val isSimklPolling: Boolean = false,
    val simklUserCode: String? = null,
    val simklVerificationUrl: String? = null,
    val simklUsername: String? = null,
    val trackingWatchlistReadMode: com.arflix.tv.data.repository.sync.TrackingReadMode =
        com.arflix.tv.data.repository.sync.TrackingReadMode.AUTO,
    val trackingContinueReadMode: com.arflix.tv.data.repository.sync.TrackingReadMode =
        com.arflix.tv.data.repository.sync.TrackingReadMode.AUTO,
    val trackingWatchedReadMode: com.arflix.tv.data.repository.sync.TrackingReadMode =
        com.arflix.tv.data.repository.sync.TrackingReadMode.AUTO,
    val trackingWriteToTrakt: Boolean = false,
    val trackingWriteToSimkl: Boolean = false,
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
    val iptvStalkerPortals: List<StalkerPortalEntry> = emptyList(),
    val iptvSortOrder: String = "provider",
    val iptvChannelCount: Int = 0,
    val isIptvLoading: Boolean = false,
    val iptvError: SettingsMessage? = null,
    val iptvStatusMessage: SettingsMessage? = null,
    val iptvStatusType: ToastType = ToastType.INFO,
    val iptvProgressText: String? = null,
    val iptvProgressPercent: Int = 0,
    val iptvSelectedPlaylistId: String? = null,
    val iptvAvailableGroups: List<String> = emptyList(),
    val iptvHiddenGroups: List<String> = emptyList(),
    val iptvGroupOrder: List<String> = emptyList(),
    val vodSearchEnabled: Boolean = true,
    val epgVodActionsEnabled: Boolean = true,
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
    val catalogSearchError: SettingsMessage? = null,
    val pendingPackManifest: CatalogPackManifest? = null,
    val pendingPackUrl: String? = null,
    val isPackLoading: Boolean = false,
    val packError: SettingsMessage? = null,
    // Addons
    val addons: List<Addon> = emptyList(),
    val isRefreshingAddons: Boolean = false,
    val torrServerBaseUrl: String = "",
    val homeServerConnection: HomeServerConnection? = null,
    val homeServerConnections: List<HomeServerConnection> = emptyList(),
    val isHomeServerConnecting: Boolean = false,
    val homeServerError: SettingsMessage? = null,
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
    // Spoiler blur â€” blur unwatched episode card images and hide synopsis
    val spoilerBlurEnabled: Boolean = false,
    // Accent color — user-selectable theme colour for focus rings, buttons, and selected items
    val accentColor: String = "White",
    val qualityFilterPresetLabel: String = "OFF",
    // Toast
    val toastMessage: SettingsMessage? = null,
    val toastType: ToastType = ToastType.INFO,
    // AI Subtitles
    val subtitleAiEnabled: Boolean = false,
    val subtitleAiAutoSelect: Boolean = false,
    val subtitleAiFindBestMatch: Boolean = false,
    val subtitlePreloadEnabled: Boolean = true,
    val dolbyVisionCompatEnabled: Boolean = true,
    val subtitleAiApiKey: String = "",
    val subtitleAiModel: SubtitleAiModel = SubtitleAiModel.GROQ_LLAMA_70B,
    val subtitleRemoveHearingImpaired: Boolean = true,
    val aiKeyServerState: AiKeyServerState = AiKeyServerState(),
    val smoothScrolling: Boolean = true
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
    private val updateStatusManager: com.arflix.tv.updater.UpdateStatusManager,
    private val mdbListRepository: com.arflix.tv.data.repository.MdbListRepository,
    private val syncProviderStore: com.arflix.tv.data.repository.sync.SyncProviderStore,
    private val watchHistoryRepository: com.arflix.tv.data.repository.WatchHistoryRepository,
    private val simklAuthManager: com.arflix.tv.data.repository.simkl.SimklAuthManager,
    private val simklSyncService: com.arflix.tv.data.repository.simkl.SimklSyncService
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
    private fun trailerDelayKey() = profileManager.profileStringKey("trailer_delay_seconds")
    private fun trailerInCardsKey() = profileManager.profileBooleanKey("trailer_in_cards")
    private fun showBudgetKey() = profileManager.profileBooleanKey("show_budget_on_home")
    private fun showEpisodeRatingsKey() = profileManager.profileBooleanKey("show_episode_ratings")
    private fun iptvFavoritesOnHomeKey() =
        profileManager.profileBooleanKey(com.arflix.tv.util.IPTV_FAVORITES_ON_HOME)
    private fun clockFormatKey() = profileManager.profileStringKey("clock_format")
    private fun smoothScrollingKey() = profileManager.profileBooleanKey("smooth_scrolling")
    private fun spoilerBlurKey() = profileManager.profileBooleanKey("spoiler_blur")
    // Stored as a string because ProfileManager has no int helper and we only persist
    // a handful of discrete dB values. Parsed back to Int on read.
    private fun volumeBoostDbKey() = profileManager.profileStringKey("volume_boost_db")
    private fun showLoadingStatsKey() = profileManager.profileBooleanKey("show_loading_stats")

    private fun subtitleSizeKey() = profileManager.profileStringKey("subtitle_size")
    private fun subtitleColorKey() = profileManager.profileStringKey("subtitle_color")
    private fun subtitleOffsetKey() = profileManager.profileStringKey("subtitle_offset")
    private fun subtitleStyleKey() = profileManager.profileStringKey("subtitle_style")
    private fun subtitleFontKey() = profileManager.profileStringKey("subtitle_font")
    private fun subtitleStylizedKey() = profileManager.profileBooleanKey("subtitle_stylized")
    private fun filterSubtitlesByLanguageKey() = profileManager.profileBooleanKey("filter_subtitles_by_lang")
    private fun secondarySubtitleKey() = profileManager.profileStringKey("secondary_subtitle")
    private val dnsProviderKey = stringPreferencesKey(OkHttpProvider.DNS_PROVIDER_PREF_KEY)
    private val customUserAgentKey = stringPreferencesKey(OkHttpProvider.USER_AGENT_PREF_KEY)
    private fun includeSpecialsKey() = profileManager.profileBooleanKey("include_specials")
    private val qualityFiltersKey = stringPreferencesKey("quality_filters")

    // Global (non-profile-scoped) AI subtitle settings â€” device-wide, not per-profile
    private val subtitleAiEnabledKey = booleanPreferencesKey("subtitle_ai_enabled")
    private val subtitleAiAutoSelectKey = booleanPreferencesKey("subtitle_ai_auto_select")
    private val subtitleAiFindBestMatchKey = booleanPreferencesKey("subtitle_ai_find_best_match")
    private val subtitlePreloadEnabledKey = booleanPreferencesKey("subtitle_preload_enabled")
    private val dolbyVisionCompatKey = booleanPreferencesKey("dolby_vision_compat")
    private val subtitleAiApiKeyKey = stringPreferencesKey("subtitle_ai_api_key")
    private val subtitleAiModelKey = stringPreferencesKey("subtitle_ai_model")
    private val subtitleRemoveHearingImpairedKey = booleanPreferencesKey("subtitle_remove_hearing_impaired")
    private fun includeSpecialsKeyFor(profileId: String) = profileManager.profileBooleanKeyFor(profileId, "include_specials")
    private val gson = Gson()
    private var lastObservedIptvM3u: String = ""
    private var lastObservedStalkerUrl: String = ""

    private var traktPollingJob: Job? = null
    private var simklPollingJob: Job? = null
    private var traktStartupJob: Job? = null
    private var loadSettingsJob: Job? = null
    private var integrationMetadataJob: Job? = null
    private var syncSummaryJob: Job? = null
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
        _uiState.value = _uiState.value.copy(
            diagnosticsSharingEnabled = DiagnosticsManager.isReportingEnabled(context)
        )
        loadSettings()
        observeProfileChanges()
        observeAddons()
        observeTorrServer()
        observeHomeServer()
        observeSyncState()
        observeAuthState()
        observeIptvConfig()
        observeIptvGroupPrefs()
        initializeCatalogs()
        observeCatalogs()
        initializeUpdaterState()
        checkForAppUpdates(force = false, showNoUpdateFeedback = false)
    }

    private fun observeIptvGroupPrefs() {
        viewModelScope.launch {
            kotlinx.coroutines.flow.combine(
                iptvRepository.observeHiddenGroups(),
                iptvRepository.observeGroupOrder()
            ) { hidden, order -> Pair(hidden, order) }
            .collect { (hidden, order) ->
                _uiState.value = _uiState.value.copy(
                    iptvHiddenGroups = hidden,
                    iptvGroupOrder = order
                )
            }
        }
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

    fun setDiagnosticsSharingEnabled(enabled: Boolean) {
        DiagnosticsManager.setReportingEnabled(context, enabled)
        _uiState.value = _uiState.value.copy(diagnosticsSharingEnabled = enabled)
    }

    private fun loadSettings() {
        loadSettingsJob?.cancel()
        integrationMetadataJob?.cancel()
        syncSummaryJob?.cancel()
        loadSettingsJob = viewModelScope.launch {
            val loadProfileId = profileManager.getProfileIdSync()
            // Load local preferences first
            val prefs = context.settingsDataStore.data.first()
            var defaultSub = prefs[defaultSubtitleKey()] ?: "Off"
            val defaultAudio = prefs[defaultAudioLanguageKey()] ?: "Auto (Original)"
            val cardLayoutMode = normalizeCardLayoutMode(prefs[cardLayoutModeKey()])
            val frameRateMode = normalizeFrameRateMode(prefs[frameRateMatchingModeKey()])
            val deviceModeOverride = prefs[com.arflix.tv.util.DEVICE_MODE_OVERRIDE_KEY] ?: "auto"
            val skipProfileSelection = prefs[com.arflix.tv.util.SKIP_PROFILE_SELECTION_KEY] ?: false
            val oledBlackBackground = prefs[com.arflix.tv.util.OLED_BLACK_BACKGROUND_KEY] ?: false
            val contentLang = resolveAppLanguage(prefs, loadProfileId)
            // Apply content language to MediaRepository immediately
            mediaRepository.contentLanguage = contentLang
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
            val trailerDelaySeconds = prefs[trailerDelayKey()]?.toIntOrNull() ?: 2
            val trailerInCards = prefs[trailerInCardsKey()] ?: true
            val spoilerBlurEnabled = prefs[spoilerBlurKey()] ?: false
            val showBudget = prefs[showBudgetKey()] ?: true
            val showEpisodeRatings = prefs[showEpisodeRatingsKey()] ?: true
            val iptvFavoritesOnHome = prefs[iptvFavoritesOnHomeKey()] ?: true
            val clockFormat = prefs[clockFormatKey()] ?: "24h"
            // One-time migration: read old "focus_border_color" key if new "accent_color" is absent
            val OLD_FOCUS_BORDER_COLOR_KEY = stringPreferencesKey("focus_border_color")
            val legacyColor = prefs[OLD_FOCUS_BORDER_COLOR_KEY]
            val accentColor = prefs[com.arflix.tv.util.ACCENT_COLOR_KEY] ?: legacyColor ?: "White"
            // Schedule async migration to copy old key → new key and delete old
            if (legacyColor != null) {
                viewModelScope.launch {
                    context.settingsDataStore.edit {
                        val old = it[OLD_FOCUS_BORDER_COLOR_KEY] ?: return@edit
                        it[com.arflix.tv.util.ACCENT_COLOR_KEY] = old
                        it.remove(OLD_FOCUS_BORDER_COLOR_KEY)
                    }
                }
            }
            val volumeBoostDb = prefs[volumeBoostDbKey()]?.toIntOrNull()?.coerceIn(0, 15) ?: 0
            val showLoadingStats = prefs[showLoadingStatsKey()] ?: true
            val smoothScrolling = prefs[smoothScrollingKey()] ?: true
            val vodSearchEnabled = prefs[IPTV_VOD_SEARCH_ENABLED_KEY] ?: true
            val epgVodActionsEnabled = prefs[IPTV_EPG_VOD_ACTIONS_ENABLED_KEY] ?: true

            val subtitleSize = prefs[subtitleSizeKey()] ?: "Medium"
            val subtitleColor = prefs[subtitleColorKey()] ?: "White"
            val subtitleStyle = prefs[subtitleStyleKey()] ?: "Bold"
            val subtitleFont = SubtitleFontOption.fromPreference(prefs[subtitleFontKey()]).preferenceValue
            val subtitleOffset = prefs[subtitleOffsetKey()] ?: "Bottom"
            val subtitleStylized = prefs[subtitleStylizedKey()] ?: true
            val filterSubtitlesByLanguage = prefs[filterSubtitlesByLanguageKey()] ?: true
            val secondarySubtitle = prefs[secondarySubtitleKey()]?.trim()?.takeIf { it.isNotBlank() } ?: "Off"
            val dnsProviderValue = normalizeDnsProviderValue(prefs[dnsProviderKey])
            val customUserAgent = prefs[customUserAgentKey].orEmpty().trim()
            OkHttpProvider.setCustomUserAgent(customUserAgent)
            val includeSpecials = prefs[includeSpecialsKey()] ?: false
            val qualityFilters = runCatching {
                val json = prefs[qualityFiltersKey].orEmpty()
                if (json.isBlank()) {
                    emptyList()
                } else {
                    gson.fromJson<List<QualityFilterConfig>>(
                        json,
                        TypeToken.getParameterized(List::class.java, QualityFilterConfig::class.java).type
                    ).orEmpty()
                }
            }.getOrDefault(emptyList())

            val subtitleAiEnabled = prefs[subtitleAiEnabledKey] ?: false
            val subtitleAiAutoSelect = prefs[subtitleAiAutoSelectKey] ?: false
            val subtitleAiFindBestMatch = prefs[subtitleAiFindBestMatchKey] ?: false
            val subtitlePreloadEnabled = prefs[subtitlePreloadEnabledKey] ?: true
            val dolbyVisionCompatEnabled = prefs[dolbyVisionCompatKey] ?: true
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
            val isMdbList = mdbListRepository.isConnected()
            val isSimkl = simklAuthManager.isConnected()
            val trackingPreferences = syncProviderStore.getTrackingPreferences()

            if (profileManager.getProfileIdSync() != loadProfileId) return@launch

            // Get Trakt expiration if authenticated
            var traktExpiration: String? = null
            if (isTrakt) {
                traktExpiration = traktRepository.getTokenExpirationDate()
            }

            val subtitleOptions = loadSubtitleOptions(defaultSub)
            val audioLanguageOptions = loadAudioLanguageOptions(defaultAudio)
            val existingCatalogs = visibleCatalogs(
                catalogRepository.ensurePreinstalledDefaults(mediaRepository.getDefaultCatalogConfigs())
            )
            val watchlistCount = try {
                watchlistRepository.getLocalWatchlistItems().size
            } catch (e: kotlinx.coroutines.CancellationException) {
                throw e
            } catch (_: Exception) {
                0
            }
            val historyCount = try {
                watchHistoryRepository.getContinueWatching().size
            } catch (e: kotlinx.coroutines.CancellationException) {
                throw e
            } catch (_: Exception) {
                0
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
                trailerDelaySeconds = trailerDelaySeconds,
                trailerInCards = trailerInCards,
                showBudget = showBudget,
                showEpisodeRatings = showEpisodeRatings,
                iptvFavoritesOnHome = iptvFavoritesOnHome,
                volumeBoostDb = volumeBoostDb,
                showLoadingStats = showLoadingStats,

                subtitleSize = subtitleSize,
                subtitleColor = subtitleColor,
                subtitleStyle = subtitleStyle,
                subtitleFont = subtitleFont,
                subtitleOffset = subtitleOffset,
                subtitleStylized = subtitleStylized,
                filterSubtitlesByLanguage = filterSubtitlesByLanguage,
                secondarySubtitle = secondarySubtitle,
                dnsProvider = dnsProviderLabel(dnsProviderValue),
                customUserAgent = customUserAgent,
                includeSpecials = includeSpecials,
                spoilerBlurEnabled = spoilerBlurEnabled,
                isLoggedIn = isLoggedIn,
                accountEmail = accountEmail,
                isTraktAuthenticated = isTrakt,
                traktExpiration = traktExpiration,
                watchlistCount = watchlistCount,
                historyCount = historyCount,
                traktUsername = null,
                isMdbListConnected = isMdbList,
                mdbListUsername = null,
                isSimklConnected = isSimkl,
                simklUsername = null,
                trackingWatchlistReadMode = trackingPreferences.watchlistReadMode,
                trackingContinueReadMode = trackingPreferences.continueWatchingReadMode,
                trackingWatchedReadMode = trackingPreferences.watchedReadMode,
                trackingWriteToTrakt = trackingPreferences.writeToTrakt == true,
                trackingWriteToSimkl = trackingPreferences.writeToSimkl == true,
                lastSyncTime = null,
                syncedMovies = 0,
                syncedEpisodes = 0,
                catalogs = existingCatalogs,
                contentLanguage = contentLang,
                deviceModeOverride = deviceModeOverride,
                skipProfileSelection = skipProfileSelection,
                oledBlackBackground = oledBlackBackground,
                clockFormat = clockFormat,
                accentColor = accentColor,
                qualityFilters = qualityFilters,
                qualityFilterPresetLabel = detectQualityFilterPreset(qualityFilters).label,
                subtitleAiEnabled = subtitleAiEnabled,
                subtitleAiAutoSelect = subtitleAiAutoSelect,
                subtitleAiFindBestMatch = subtitleAiFindBestMatch,
                subtitlePreloadEnabled = subtitlePreloadEnabled,
                dolbyVisionCompatEnabled = dolbyVisionCompatEnabled,
                subtitleAiApiKey = subtitleAiApiKey,
                subtitleAiModel = subtitleAiModel,
                subtitleRemoveHearingImpaired = subtitleRemoveHearingImpaired,
                smoothScrolling = smoothScrolling,
                vodSearchEnabled = vodSearchEnabled,
                epgVodActionsEnabled = epgVodActionsEnabled,
            )

            refreshIntegrationUsernames(loadProfileId, isTrakt, isMdbList, isSimkl)
            if (isTrakt || isMdbList || isSimkl) refreshSyncSummary(loadProfileId)
        }
    }

    private fun refreshIntegrationUsernames(
        profileId: String,
        isTraktConnected: Boolean,
        isMdbListConnected: Boolean,
        isSimklConnected: Boolean = false
    ) {
        integrationMetadataJob?.cancel()
        integrationMetadataJob = viewModelScope.launch {
            if (isTraktConnected) {
                launch {
                    val username = try {
                        withTimeoutOrNull(5_000L) { traktRepository.fetchUsername() }
                    } catch (e: CancellationException) {
                        throw e
                    } catch (_: Exception) {
                        null
                    }
                    if (
                        profileManager.getProfileIdSync() == profileId &&
                        _uiState.value.isTraktAuthenticated
                    ) {
                        _uiState.value = _uiState.value.copy(traktUsername = username)
                    }
                }
            }

            if (isMdbListConnected) {
                launch {
                    val username = try {
                        withTimeoutOrNull(5_000L) { mdbListRepository.fetchUsername() }
                    } catch (e: CancellationException) {
                        throw e
                    } catch (_: Exception) {
                        null
                    }
                    if (
                        profileManager.getProfileIdSync() == profileId &&
                        _uiState.value.isMdbListConnected
                    ) {
                        _uiState.value = _uiState.value.copy(mdbListUsername = username)
                    }
                }
            }

            if (isSimklConnected) {
                launch {
                    val username = try {
                        withTimeoutOrNull(5_000L) { simklAuthManager.fetchUsername() }
                    } catch (e: CancellationException) {
                        throw e
                    } catch (_: Exception) {
                        null
                    }
                    if (
                        profileManager.getProfileIdSync() == profileId &&
                        _uiState.value.isSimklConnected
                    ) {
                        _uiState.value = _uiState.value.copy(simklUsername = username)
                    }
                }
            }
        }
    }

    private fun refreshSyncSummary(profileId: String) {
        syncSummaryJob?.cancel()
        syncSummaryJob = viewModelScope.launch(Dispatchers.IO) {
            val summary = traktSyncService.getLastSyncSummary()
            var movies = summary?.moviesSynced ?: 0
            var episodes = summary?.episodesSynced ?: 0
            var lastSyncAt = summary?.lastSyncAt

            val isTrakt = _uiState.value.isTraktAuthenticated
            val isMdbList = _uiState.value.isMdbListConnected
            val isSimkl = _uiState.value.isSimklConnected

            // If summary has 0/null but a provider is connected, query provider caches directly
            if (movies == 0 && episodes == 0 && (isTrakt || isMdbList || isSimkl)) {
                if (isTrakt) {
                    val traktMovies = runCatching { traktRepository.getWatchedMovies() }.getOrDefault(emptySet())
                    val traktEpisodes = runCatching { traktRepository.getWatchedEpisodes() }.getOrDefault(emptySet())
                    movies += traktMovies.size
                    episodes += traktEpisodes.size
                }
                if (isMdbList) {
                    val mdbMovies = runCatching { mdbListRepository.getWatchedMovies() }.getOrDefault(emptySet())
                    val mdbEpisodes = runCatching { mdbListRepository.getWatchedEpisodes() }.getOrDefault(emptySet())
                    movies += mdbMovies.size
                    episodes += mdbEpisodes.size
                }
                if (isSimkl) {
                    val simklMovies = runCatching { simklSyncService.getWatchedMovies() }.getOrDefault(emptySet())
                    val simklEpisodes = runCatching { simklSyncService.getWatchedEpisodes() }.getOrDefault(emptySet())
                    movies += simklMovies.size
                    episodes += simklEpisodes.size
                }
                if (lastSyncAt == null && (movies > 0 || episodes > 0)) {
                    lastSyncAt = java.time.Instant.now().toString()
                    traktSyncService.saveLocalSyncSummary(lastSyncAt, movies, episodes)
                }
            }

            if (profileManager.getProfileIdSync() != profileId) return@launch
            withContext(Dispatchers.Main) {
                _uiState.value = _uiState.value.copy(
                    lastSyncTime = formatSyncTime(lastSyncAt),
                    syncedMovies = movies,
                    syncedEpisodes = episodes
                )
            }
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
            if (e is kotlinx.coroutines.CancellationException) throw e

            null
        }
    }

    fun resetIptvGroupOrder(playlistId: String) {
        viewModelScope.launch {
            iptvRepository.resetGroupOrder(playlistId)
        }
    }

    fun setIptvSelectedPlaylistId(playlistId: String?) {
        val selectedPlaylistId = playlistId?.trim().orEmpty()
        if (selectedPlaylistId.isBlank()) {
            _uiState.value = _uiState.value.copy(
                iptvSelectedPlaylistId = null,
                iptvAvailableGroups = emptyList()
            )
            return
        }

        _uiState.value = _uiState.value.copy(
            iptvSelectedPlaylistId = selectedPlaylistId,
            iptvAvailableGroups = emptyList()
        )
        viewModelScope.launch {
            val groups = loadIptvGroupsForPlaylist(selectedPlaylistId)
            if (_uiState.value.iptvSelectedPlaylistId == selectedPlaylistId) {
                _uiState.value = _uiState.value.copy(iptvAvailableGroups = groups)
            }
        }
    }

    private suspend fun loadIptvGroupsForPlaylist(playlistId: String): List<String> {
        val stalkerPortalIds = _uiState.value.iptvStalkerPortals.map { it.id }.toSet()
        val isStalkerPortal = playlistId in stalkerPortalIds

        val pagedGroups = withContext(Dispatchers.IO) {
            iptvRepository.pagedPlaylistGroupCounts()
                .asSequence()
                .filter { (id, _, count) -> id == playlistId && count > 0 }
                .map { (_, group, _) -> group.trim().ifBlank { "Ungrouped" } }
                .distinct()
                .toList()
        }
        if (pagedGroups.isNotEmpty()) return pagedGroups

        val snapshot = iptvRepository.getMemoryCachedSnapshot()
            ?: iptvRepository.getCachedSnapshotOrNull()
        val prefix = if (isStalkerPortal) "stalker:$playlistId:" else "$playlistId:"
        return withContext(Dispatchers.Default) {
            snapshot?.channels
                ?.asSequence()
                ?.filter { it.id.startsWith(prefix) }
                ?.map { it.group.trim().ifBlank { "Ungrouped" } }
                ?.distinct()
                ?.toList()
                .orEmpty()
        }
    }

    fun toggleIptvHiddenGroup(playlistId: String, groupName: String) {
        viewModelScope.launch {
            iptvRepository.toggleHiddenGroup(playlistId, groupName)
        }
    }

    fun moveIptvGroupUp(playlistId: String, groupName: String) {
        viewModelScope.launch {
            iptvRepository.moveGroupUp(playlistId, groupName, _uiState.value.iptvAvailableGroups)
        }
    }

    fun moveIptvGroupDown(playlistId: String, groupName: String) {
        viewModelScope.launch {
            iptvRepository.moveGroupDown(playlistId, groupName, _uiState.value.iptvAvailableGroups)
        }
    }

    fun moveIptvGroupToTop(playlistId: String, groupName: String) {
        viewModelScope.launch {
            iptvRepository.moveGroupToTop(playlistId, groupName, _uiState.value.iptvAvailableGroups)
        }
    }

    /**
     * Bulk-show or bulk-hide all groups of a playlist at once. Drives the
     * "show all / hide all" button in the categories screen. The current
     * available groups are taken from the UI state so the operation only
     * touches groups that actually belong to the selected playlist.
     */
    fun setAllIptvGroupsVisible(playlistId: String, visible: Boolean) {
        viewModelScope.launch {
            val groups = _uiState.value.iptvAvailableGroups
            if (groups.isEmpty()) return@launch
            iptvRepository.setGroupsHidden(playlistId, groups, hidden = !visible)
        }
    }

    // ========== App Updates ==========

    fun syncAllTrackingProviders(silent: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_uiState.value.isSyncing) return@launch
            withContext(Dispatchers.Main) {
                _uiState.value = _uiState.value.copy(isSyncing = true)
            }
            try {
                var totalMovies = 0
                var totalEpisodes = 0
                var syncedAny = false
                val connectedProviders = mutableListOf<String>()
                val failures = mutableListOf<String>()

                if (_uiState.value.isTraktAuthenticated) {
                    connectedProviders += "Trakt"
                    when (val result = traktSyncService.performFullSync()) {
                        is SyncResult.Success -> {
                            totalMovies += result.moviesSynced
                            totalEpisodes += result.episodesSynced
                            syncedAny = true
                        }
                        is SyncResult.Error -> failures += "Trakt: ${result.message}"
                    }
                }
                if (_uiState.value.isMdbListConnected) {
                    connectedProviders += "MDBList"
                    mdbListRepository.getWatchedSnapshot()
                        .onSuccess { snapshot ->
                            totalMovies += snapshot.movies.size
                            totalEpisodes += snapshot.episodes.size
                            syncedAny = true
                        }
                        .onFailure { error ->
                            failures += "MDBList: ${error.message ?: "request failed"}"
                        }
                }
                if (_uiState.value.isSimklConnected) {
                    connectedProviders += "Simkl"
                    if (simklSyncService.syncIfNeeded(force = true)) {
                        val simklMovies = simklSyncService.getWatchedMovies()
                        val simklEpisodes = simklSyncService.getWatchedEpisodes()
                        totalMovies += simklMovies.size
                        totalEpisodes += simklEpisodes.size
                        syncedAny = true
                    } else {
                        failures += "Simkl: request failed"
                    }
                }

                val nowIso = java.time.Instant.now().toString()
                if (syncedAny) {
                    traktSyncService.saveLocalSyncSummary(nowIso, totalMovies, totalEpisodes)
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(
                            syncedMovies = totalMovies,
                            syncedEpisodes = totalEpisodes,
                            lastSyncTime = formatSyncTime(nowIso),
                            toastMessage = if (!silent) {
                                if (failures.isEmpty()) {
                                    SettingsMessage.Res(
                                        R.string.settings_sync_summary,
                                        listOf(totalMovies, totalEpisodes)
                                    )
                                } else {
                                    SettingsMessage.Res(
                                        R.string.settings_sync_summary_with_failures,
                                        listOf(totalMovies, totalEpisodes, failures.joinToString("; "))
                                    )
                                }
                            } else {
                                _uiState.value.toastMessage
                            },
                            toastType = if (!silent) {
                                if (failures.isEmpty()) ToastType.SUCCESS else ToastType.ERROR
                            } else {
                                _uiState.value.toastType
                            }
                        )
                    }
                    traktRepository.invalidateWatchedCache()
                    traktRepository.initializeWatchedCache()
                } else if (!silent && connectedProviders.isEmpty()) {
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = SettingsMessage.Res(R.string.settings_sync_no_provider),
                            toastType = ToastType.ERROR
                        )
                    }
                } else if (!silent) {
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = SettingsMessage.Res(
                                R.string.sync_failed,
                                listOf(failures.joinToString("; "))
                            ),
                            toastType = ToastType.ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                if (!silent) {
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = SettingsMessage.Res(
                                R.string.sync_failed,
                                listOf(e.message.orEmpty())
                            ),
                            toastType = ToastType.ERROR
                        )
                    }
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(isSyncing = false)
                }
            }
        }
    }

    fun performFullSync(silent: Boolean = false) {
        syncAllTrackingProviders(silent = silent)
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
            "Gujarati",
            "Hebrew",
            "Hindi",
            "Hungarian",
            "Indonesian",
            "Italian",
            "Japanese",
            "Kannada",
            "Korean",
            "Lithuanian",
            "Malayalam",
            "Marathi",
            "Norwegian",
            "Persian",
            "Polish",
            "Portuguese",
            "Portuguese (Brazil)",
            "Punjabi",
            "Romanian",
            "Russian",
            "Serbian",
            "Slovak",
            "Slovenian",
            "Spanish",
            "Swedish",
            "Tamil",
            "Telugu",
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
            "None",
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
            "Gujarati",
            "Hebrew",
            "Hindi",
            "Hungarian",
            "Indonesian",
            "Italian",
            "Japanese",
            "Kannada",
            "Korean",
            "Lithuanian",
            "Malayalam",
            "Marathi",
            "Norwegian",
            "Persian",
            "Polish",
            "Portuguese",
            "Portuguese (Brazil)",
            "Punjabi",
            "Romanian",
            "Russian",
            "Serbian",
            "Slovak",
            "Slovenian",
            "Spanish",
            "Swedish",
            "Tamil",
            "Telugu",
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
            mediaRepository.contentLanguage = lang
            _uiState.value = _uiState.value.copy(contentLanguage = lang)
            syncLocalStateToCloud(silent = true)

            // Refresh the Launcher "Keep watching" with the new language
            launcherContinueWatchingRepository.refreshForCurrentProfile()
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

    fun setTrailerInCards(enabled: Boolean) {
        viewModelScope.launch { context.settingsDataStore.edit { it[trailerInCardsKey()] = enabled }; _uiState.value = _uiState.value.copy(trailerInCards = enabled); syncLocalStateToCloud(silent = true) }
    }

    fun cycleTrailerDelay() {
        val next = when (_uiState.value.trailerDelaySeconds) {
            0 -> 1
            1 -> 2
            2 -> 3
            3 -> 5
            else -> 0
        }
        viewModelScope.launch {
            context.settingsDataStore.edit { it[trailerDelayKey()] = next.toString() }
            _uiState.value = _uiState.value.copy(trailerDelaySeconds = next)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setShowBudget(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[showBudgetKey()] = enabled }
            _uiState.value = _uiState.value.copy(showBudget = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setIptvFavoritesOnHome(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[iptvFavoritesOnHomeKey()] = enabled }
            _uiState.value = _uiState.value.copy(iptvFavoritesOnHome = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setShowEpisodeRatings(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[showEpisodeRatingsKey()] = enabled }
            _uiState.value = _uiState.value.copy(showEpisodeRatings = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setSmoothScrolling(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[smoothScrollingKey()] = enabled }
            _uiState.value = _uiState.value.copy(smoothScrolling = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setVodSearchEnabled(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[IPTV_VOD_SEARCH_ENABLED_KEY] = enabled }
            _uiState.value = _uiState.value.copy(vodSearchEnabled = enabled)
        }
    }

    fun setEpgVodActionsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[IPTV_EPG_VOD_ACTIONS_ENABLED_KEY] = enabled }
            _uiState.value = _uiState.value.copy(epgVodActionsEnabled = enabled)
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
     * Cycle the accent color through the full palette (primary + secondary).
     * Order: neutrals → warms → cools → back to White
     */
    fun cycleAccentColor() {
        val colors = listOf(
            "White", "Silver", "Slate", "Charcoal", "Cream",
            "Gold", "Bronze", "Amber", "Orange", "Red", "Maroon",
            "Rose Gold", "Magenta", "Lavender", "Violet", "Indigo", "Navy",
            "Blue", "Cyan", "Teal", "Green", "Olive", "Yellow"
        )
        val current = _uiState.value.accentColor
        val nextIndex = (colors.indexOf(current) + 1) % colors.size
        val next = colors[nextIndex]
        viewModelScope.launch {
            context.settingsDataStore.edit { it[com.arflix.tv.util.ACCENT_COLOR_KEY] = next }
            _uiState.value = _uiState.value.copy(accentColor = next)
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

    fun cycleSubtitleFont() {
        val next = SubtitleFontOption.nextPreference(_uiState.value.subtitleFont)
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitleFontKey()] = next }
            _uiState.value = _uiState.value.copy(subtitleFont = next)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun toggleSubtitleStylized() {
        val next = !_uiState.value.subtitleStylized
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitleStylizedKey()] = next }
            _uiState.value = _uiState.value.copy(subtitleStylized = next)
            syncLocalStateToCloud(silent = true)
        }
    }

    // -- AI Subtitles ---------------------------------------------------------

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

    fun setSubtitleAiFindBestMatch(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitleAiFindBestMatchKey] = enabled }
            _uiState.value = _uiState.value.copy(subtitleAiFindBestMatch = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setSubtitlePreloadEnabled(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[subtitlePreloadEnabledKey] = enabled }
            _uiState.value = _uiState.value.copy(subtitlePreloadEnabled = enabled)
            syncLocalStateToCloud(silent = true)
        }
    }

    fun setDolbyVisionCompatEnabled(enabled: Boolean) {
        viewModelScope.launch {
            context.settingsDataStore.edit { it[dolbyVisionCompatKey] = enabled }
            _uiState.value = _uiState.value.copy(dolbyVisionCompatEnabled = enabled)
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
            // Include the one-time pairing token as query param so the QR (scanned
            // by a phone) encodes the token and the server can validate it.
            val url = "http://$ip:${server.listeningPort}?t=${server.currentPairingToken}"
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
            syncLocalStateToCloud(silent = true)

            // Replace Coil image loader with one using the new DNS
            val imageLoader = withContext(Dispatchers.IO) {
                OkHttpProvider.createCoilImageLoader(context)
            }
            Coil.setImageLoader(imageLoader)
        }
    }

    fun setCustomUserAgent(value: String) {
        val trimmed = value.trim()
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                if (trimmed.isBlank()) {
                    prefs.remove(customUserAgentKey)
                } else {
                    prefs[customUserAgentKey] = trimmed
                }
            }
            OkHttpProvider.setCustomUserAgent(trimmed)
            _uiState.value = _uiState.value.copy(
                customUserAgent = trimmed
            )
            syncLocalStateToCloud(silent = true)
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

    fun addQualityFilter(deviceName: String, regexPattern: String): Boolean {
        val trimmedRegex = regexPattern.trim()
        if (trimmedRegex.isBlank()) return false
        try {
            Regex(trimmedRegex)
        } catch (_: java.util.regex.PatternSyntaxException) {
            return false
        } catch (_: IllegalArgumentException) {
            return false
        }

        viewModelScope.launch {
            val next = _uiState.value.qualityFilters + QualityFilterConfig(
                id = java.util.UUID.randomUUID().toString(),
                deviceName = deviceName.trim(),
                regexPattern = trimmedRegex,
                enabled = true
            )
            saveQualityFilters(next)
        }
        return true
    }

    fun updateQualityFilter(filterId: String, deviceName: String, regexPattern: String): Boolean {
        val trimmedRegex = regexPattern.trim()
        if (trimmedRegex.isBlank()) return false
        try {
            Regex(trimmedRegex)
        } catch (_: java.util.regex.PatternSyntaxException) {
            return false
        } catch (_: IllegalArgumentException) {
            return false
        }

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
        return true
    }

    fun cycleQualityFilterPreset() {
        viewModelScope.launch {
            val currentPreset = detectQualityFilterPreset(_uiState.value.qualityFilters)

            // Prevent losing custom filters by cycling into a preset
            if (currentPreset == QualityFilterPreset.CUSTOM) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = SettingsMessage.Res(R.string.settings_quality_filters_custom),
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

    fun moveAddonUp(addonId: String) {
        moveAddon(addonId, moveUp = true)
    }

    fun moveAddonDown(addonId: String) {
        moveAddon(addonId, moveUp = false)
    }

    private fun moveAddon(addonId: String, moveUp: Boolean) {
        viewModelScope.launch {
            val moved = if (moveUp) {
                streamRepository.moveAddonUp(addonId)
            } else {
                streamRepository.moveAddonDown(addonId)
            }
            if (!moved) return@launch
            val addonsAfterMove = streamRepository.installedAddons.first()
            runCatching {
                catalogRepository.syncAddonCatalogs(addonsAfterMove)
            }
            _uiState.value = _uiState.value.copy(addons = addonsAfterMove)
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
                        SettingsMessage.Res(
                            R.string.settings_addon_added_with_catalogs,
                            listOf(addon.name, importedCatalogs)
                        )
                    } else {
                        SettingsMessage.Res(
                            R.string.settings_addon_added_no_catalogs,
                            listOf(addon.name)
                        )
                    },
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.addon_failed_add)
                    ),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun refreshAddons() {
        if (_uiState.value.isRefreshingAddons) return
        _uiState.value = _uiState.value.copy(isRefreshingAddons = true)
        viewModelScope.launch {
            try {
                if (authRepository.hasValidCloudSyncSession()) {
                    val restoreResult = restoreCloudStateToLocalInternal(
                        silent = true,
                        pushPendingLocalFirst = false
                    )
                    if (restoreResult == CloudRestoreResult.FAILED) {
                        _uiState.value = _uiState.value.copy(
                            isRefreshingAddons = false,
                            toastMessage = SettingsMessage.Res(R.string.settings_addons_cloud_restore_failed),
                            toastType = ToastType.ERROR
                        )
                        return@launch
                    }
                }
                val report = streamRepository.refreshInstalledAddons()
                val updatedAddons = streamRepository.installedAddons.first()
                runCatching {
                    catalogRepository.syncAddonCatalogs(updatedAddons)
                }
                val toast = SettingsMessage.Res(
                    R.string.settings_addons_refresh_report,
                    listOf(report.refreshed, report.failed)
                )
                _uiState.value = _uiState.value.copy(
                    addons = updatedAddons,
                    isRefreshingAddons = false,
                    toastMessage = toast,
                    toastType = if (report.failed == 0) ToastType.SUCCESS else ToastType.INFO
                )
                syncLocalStateToCloud(silent = true)
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                _uiState.value = _uiState.value.copy(
                    isRefreshingAddons = false,
                    toastMessage = SettingsMessage.Res(R.string.settings_addons_refresh_failed),
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
                    val restoreResult = restoreCloudStateToLocalInternal(
                        silent = true,
                        pushPendingLocalFirst = false
                    )
                    // Only seed cloud when there is truly no backup yet.
                    if (
                        restoreResult == CloudRestoreResult.NO_BACKUP &&
                        cloudSyncRepository.hasMeaningfulLocalProfiles()
                    ) {
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
                val stalkerConfigured = config.stalkerPortals.any { it.portalUrl.isNotBlank() }
                if (current.iptvM3uUrl != config.m3uUrl || current.iptvEpgUrl != config.epgUrl || current.iptvStalkerPortals != config.stalkerPortals || current.iptvPlaylists != config.playlists || current.iptvSortOrder != config.sortOrder) {
                    _uiState.value = current.copy(
                        iptvM3uUrl = config.m3uUrl,
                        iptvEpgUrl = config.epgUrl,
                        iptvPlaylists = config.playlists,
                        iptvStalkerPortals = config.stalkerPortals,
                        iptvSortOrder = config.sortOrder
                    )
                }
                if (!hasObservedIptvConfig) {
                    hasObservedIptvConfig = true
                    lastObservedIptvM3u = config.m3uUrl
                    lastObservedStalkerUrl = if (stalkerConfigured) "stalker" else ""
                    lastObservedIptvConfigSignature = config.syncSignature()
                    val hasAnyIptvConfig = config.m3uUrl.isNotBlank() ||
                        stalkerConfigured ||
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
                    stalkerConfigured ||
                    config.playlists.any { it.enabled && it.m3uUrl.isNotBlank() }
                val configSignature = config.syncSignature()
                if (hasAnyConfig && configSignature != lastObservedIptvConfigSignature) {
                    lastObservedIptvM3u = config.m3uUrl
                    lastObservedStalkerUrl = if (stalkerConfigured) "stalker" else ""
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
            catalogRepository.observeCatalogs().collect {
                val effectiveCatalogs = catalogRepository.ensurePreinstalledDefaults(mediaRepository.getDefaultCatalogConfigs())
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

    fun loadPackManifest(url: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isPackLoading = true,
                packError = null,
                pendingPackManifest = null,
                pendingPackUrl = null
            )
            val result = catalogRepository.fetchCatalogPackManifest(url)
            result.onSuccess { manifest ->
                _uiState.value = _uiState.value.copy(
                    isPackLoading = false,
                    pendingPackManifest = manifest,
                    pendingPackUrl = url
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isPackLoading = false,
                    packError = error.message.orMessage(
                        SettingsMessage.Res(R.string.settings_pack_load_failed)
                    ),
                    pendingPackUrl = null
                )
            }
        }
    }

    fun clearPendingPack() {
        _uiState.value = _uiState.value.copy(
            pendingPackManifest = null,
            pendingPackUrl = null,
            isPackLoading = false,
            packError = null
        )
    }

    fun confirmInstallPack(url: String) {
        val manifest = _uiState.value.pendingPackManifest
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isPackLoading = true,
                packError = null
            )
            val result = catalogRepository.addCatalogPack(url, manifest)
            result.onSuccess { installedManifest ->
                _uiState.value = _uiState.value.copy(
                    isPackLoading = false,
                    pendingPackManifest = null,
                    pendingPackUrl = null,
                    toastMessage = SettingsMessage.Res(
                        R.string.settings_pack_installed,
                        listOf(installedManifest.name.orEmpty())
                    ),
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isPackLoading = false,
                    packError = error.message.orMessage(
                        SettingsMessage.Res(R.string.settings_pack_install_failed)
                    )
                )
            }
        }
    }

    fun removeCatalogPack(packId: String) {
        viewModelScope.launch {
            val result = catalogRepository.removeCatalogPack(packId)
            result.onSuccess {
                _uiState.value = _uiState.value.copy(
                    toastMessage = SettingsMessage.Res(R.string.settings_pack_removed),
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.settings_pack_remove_failed)
                    ),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun addCatalog(url: String) {
        viewModelScope.launch {
            val result = catalogRepository.addCustomCatalog(url)
            result.onSuccess { catalog ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = SettingsMessage.Res(
                        R.string.settings_catalog_added,
                        listOf(catalog.title)
                    ),
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.catalog_failed_add)
                    ),
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
                catalogSearchError = if (normalizedQuery.isBlank()) {
                    null
                } else {
                    SettingsMessage.Res(R.string.settings_catalog_search_min_chars)
                }
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
                    catalogSearchError = if (lists.isEmpty()) {
                        SettingsMessage.Res(R.string.settings_catalog_search_no_lists)
                    } else {
                        null
                    }
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    catalogSearchResults = emptyList(),
                    isCatalogSearching = false,
                    catalogSearchError = error.message.orMessage(
                        SettingsMessage.Res(R.string.catalog_failed_search)
                    )
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
                    toastMessage = SettingsMessage.Res(
                        R.string.settings_catalog_added,
                        listOf(catalog.title)
                    ),
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.catalog_failed_add)
                    ),
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
                    toastMessage = SettingsMessage.Res(
                        R.string.settings_catalog_updated,
                        listOf(catalog.title)
                    ),
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.catalog_failed_update)
                    ),
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
                    toastMessage = SettingsMessage.Res(R.string.settings_catalog_removed),
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.catalog_failed_remove)
                    ),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun unpackCatalog(catalogId: String) {
        viewModelScope.launch {
            val current = catalogRepository.getCatalogs()
            val index = current.indexOfFirst { it.id == catalogId }
            if (index != -1) {
                val target = current[index]
                if (target.packId != null) {
                    val updated = current.toMutableList()
                    updated[index] = target.copy(packId = null, packName = null)
                    catalogRepository.replaceCatalogsForActiveProfile(updated)

                    // Update state
                    val visible = visibleCatalogs(updated)
                    _uiState.value = _uiState.value.copy(
                        catalogs = visible,
                        toastMessage = SettingsMessage.Res(R.string.settings_catalog_unpacked),
                        toastType = ToastType.SUCCESS
                    )
                    syncLocalStateToCloud(silent = true)
                }
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
                    toastMessage = SettingsMessage.Res(R.string.settings_iptv_m3u_required),
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
            refreshIptv(showToast = true, configured = true, force = true)
        }
    }

    /**
     * Add a new Stalker portal at the end of the list (capped at
     * [MAX_STALKER_PORTALS]). Returns false (with a toast) when
     * the limit is reached or the URL/MAC are blank.
     */
    fun onAddStalkerPortal(portalUrl: String, macAddress: String, name: String? = null) {
        val trimmedUrl = portalUrl.trim().trimEnd('/')
        val trimmedMac = macAddress.trim().uppercase()
        if (trimmedUrl.isBlank() || trimmedMac.isBlank()) {
            _uiState.value = _uiState.value.copy(
                toastMessage = SettingsMessage.Res(R.string.settings_iptv_portal_mac_required),
                toastType = ToastType.ERROR
            )
            return
        }
        val current = _uiState.value.iptvStalkerPortals
        if (current.size >= MAX_STALKER_PORTALS) {
            _uiState.value = _uiState.value.copy(
                toastMessage = SettingsMessage.Res(R.string.settings_iptv_stalker_max_reached),
                toastType = ToastType.ERROR
            )
            return
        }
        val portalId = StalkerPortalSupport.nextAvailablePortalId(
            current.map { it.id },
            MAX_STALKER_PORTALS,
        ) ?: return
        val portalNumber = portalId.removePrefix("stalker").toIntOrNull() ?: (current.size + 1)
        val portal = StalkerPortalEntry(
            id = portalId,
            name = name?.trim()?.ifBlank { null } ?: "Portal $portalNumber",
            portalUrl = trimmedUrl,
            macAddress = trimmedMac
        )
        persistStalkerPortals(current + portal)
    }

    /**
     * Update an existing portal's URL/MAC (and optionally its name). The edit
     * dialog calls this with the portal's id.
     */
    fun onEditStalkerPortal(portalId: String, portalUrl: String, macAddress: String, name: String? = null) {
        val trimmedUrl = portalUrl.trim().trimEnd('/')
        val trimmedMac = macAddress.trim().uppercase()
        if (trimmedUrl.isBlank() || trimmedMac.isBlank()) {
            _uiState.value = _uiState.value.copy(
                toastMessage = SettingsMessage.Res(R.string.settings_iptv_portal_mac_required),
                toastType = ToastType.ERROR
            )
            return
        }
        val updated = _uiState.value.iptvStalkerPortals.map { portal ->
            if (portal.id == portalId) portal.copy(
                portalUrl = trimmedUrl,
                macAddress = trimmedMac,
                name = name?.trim()?.ifBlank { portal.name } ?: portal.name
            ) else portal
        }
        if (updated == _uiState.value.iptvStalkerPortals) return
        persistStalkerPortals(updated)
    }

    /**
     * Rename a portal without touching its URL/MAC.
     */
    fun onRenameStalkerPortal(portalId: String, name: String) {
        val trimmed = name.trim()
        if (trimmed.isBlank()) return
        val updated = _uiState.value.iptvStalkerPortals.map { portal ->
            if (portal.id == portalId) portal.copy(name = trimmed) else portal
        }
        if (updated == _uiState.value.iptvStalkerPortals) return
        viewModelScope.launch {
            iptvRepository.saveStalkerPortals(updated)
            _uiState.value = _uiState.value.copy(iptvStalkerPortals = updated)
            syncLocalStateToCloud(silent = true)
        }
    }

    /** Enable / disable a portal. */
    fun onToggleStalkerPortal(portalId: String) {
        val updated = _uiState.value.iptvStalkerPortals.map { portal ->
            if (portal.id == portalId) portal.copy(enabled = !portal.enabled) else portal
        }
        if (updated == _uiState.value.iptvStalkerPortals) return
        persistStalkerPortals(updated)
    }

    fun onMoveStalkerPortalUp(portalId: String) {
        val current = _uiState.value.iptvStalkerPortals.toMutableList()
        val idx = current.indexOfFirst { it.id == portalId }
        if (idx <= 0) return
        val item = current.removeAt(idx)
        current.add(idx - 1, item)
        persistStalkerPortals(current)
    }

    fun onMoveStalkerPortalDown(portalId: String) {
        val current = _uiState.value.iptvStalkerPortals.toMutableList()
        val idx = current.indexOfFirst { it.id == portalId }
        if (idx !in 0 until current.lastIndex) return
        val item = current.removeAt(idx)
        current.add(idx + 1, item)
        persistStalkerPortals(current)
    }

    fun onRemoveStalkerPortal(portalId: String) {
        val updated = _uiState.value.iptvStalkerPortals.filterNot { it.id == portalId }
        if (updated == _uiState.value.iptvStalkerPortals) return
        persistStalkerPortals(updated)
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                toastMessage = SettingsMessage.Res(R.string.settings_iptv_stalker_removed),
                toastType = ToastType.SUCCESS
            )
        }
    }

    /**
     * Open the categories dialog for a specific Stalker portal. Each portal has
     * its own independent group set (decision #3) — the portal id is used as
     * the playlist id for [PlaylistGroupKey] and hidden-group filtering.
     */
    fun onManageStalkerCategories(portalId: String) {
        setIptvSelectedPlaylistId(portalId)
    }

    private fun persistStalkerPortals(portals: List<StalkerPortalEntry>) {
        viewModelScope.launch {
            iptvRepository.saveStalkerPortals(portals)
            _uiState.value = _uiState.value.copy(iptvStalkerPortals = portals)
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
                toastMessage = SettingsMessage.Res(R.string.settings_iptv_xtream_credentials_required),
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
                iptvPlaylists = playlists.filter { it.m3uUrl.isNotBlank() }
            )
            syncLocalStateToCloud(silent = true)
            refreshIptv(showToast = true, configured = true, force = true)
        }
    }

    fun refreshIptv(showToast: Boolean = true, configured: Boolean = false, force: Boolean = true) {
        viewModelScope.launch {
            val currentConfig = iptvRepository.observeConfig().first()
            // Check legacy m3uUrl, multi-playlist entries, and Stalker portals
            val hasPlaylists = currentConfig.playlists.any { it.m3uUrl.isNotBlank() && it.enabled }
            val hasStalker = currentConfig.stalkerPortals.any { it.portalUrl.isNotBlank() }
            if (currentConfig.m3uUrl.isBlank() && !hasStalker && !hasPlaylists) {
                return@launch
            }

            val runningJob = iptvLoadJob
            if (runningJob?.isActive == true) {
                if (!force) {
                    return@launch
                }
                runningJob.cancelAndJoin()
            }

            iptvLoadJob = launch {
            _uiState.value = _uiState.value.copy(isIptvLoading = true, iptvError = null)
            // When the user explicitly forces a refresh (Settings → Refresh
            // IPTV), nuke every IPTV-side cache before reloading so the
            // snapshot + warm-up below go all the way back to the provider.
            // Auto-triggered refreshes (force=false) keep their soft TTL
            // behavior.
            if (force) {
                runCatching { iptvRepository.purgeAllIptvSourceCaches(preserveLiveSnapshot = true) }
            }
            runCatching {
                val refreshPolicy = settingsIptvRefreshPolicy(force)
                val snapshot = iptvRepository.loadSnapshot(
                    forcePlaylistReload = refreshPolicy.forcePlaylistReload,
                    forceEpgReload = refreshPolicy.forceEpgReload,
                    allowNetworkEpgFetch = refreshPolicy.allowNetworkEpgFetch,
                    onProgress = { progress ->
                        _uiState.value = _uiState.value.copy(
                            isIptvLoading = true,
                            iptvProgressText = progress.message,
                            iptvProgressPercent = progress.percent ?: _uiState.value.iptvProgressPercent
                        )
                    }
                )
                val epgCovered = snapshot.channels.count { channel ->
                    val item = snapshot.nowNext[channel.id]
                    item != null && (
                        item.now != null ||
                            item.next != null ||
                            item.later != null ||
                            item.upcoming.isNotEmpty() ||
                            item.recent.isNotEmpty()
                        )
                }
                val epgMissing = (snapshot.channels.size - epgCovered).coerceAtLeast(0)
                val epgStatus: SettingsMessage? = when {
                    snapshot.channels.isEmpty() -> null
                    epgCovered > 0 -> SettingsMessage.Res(
                        R.string.settings_iptv_epg_matched,
                        listOf(epgCovered, epgMissing)
                    )
                    else -> SettingsMessage.Res(R.string.settings_iptv_epg_none)
                }
                val channelCount = snapshot.channels.size
                val loadedMsg = when {
                    configured && epgStatus != null -> SettingsMessage.Res(
                        R.string.settings_iptv_connected_channels_epg,
                        listOf(channelCount, epgStatus)
                    )
                    configured -> SettingsMessage.Res(
                        R.string.settings_iptv_connected_channels,
                        listOf(channelCount)
                    )
                    epgStatus != null -> SettingsMessage.Res(
                        R.string.settings_iptv_refreshed_channels_epg,
                        listOf(channelCount, epgStatus)
                    )
                    else -> SettingsMessage.Res(
                        R.string.settings_iptv_refreshed_channels,
                        listOf(channelCount)
                    )
                }
                val doneMsg = snapshot.epgWarning?.let { SettingsMessage.Raw(it) } ?: loadedMsg
                _uiState.value = _uiState.value.copy(
                    isIptvLoading = false,
                    iptvChannelCount = snapshot.channels.size,
                    iptvError = null,
                    iptvStatusMessage = doneMsg,
                    iptvStatusType = if (snapshot.epgWarning != null) ToastType.INFO else ToastType.SUCCESS,
                    iptvProgressText = context.getString(R.string.done),
                    iptvProgressPercent = 100,
                    toastMessage = if (showToast) {
                        SettingsMessage.Res(
                            if (configured) {
                                R.string.settings_iptv_configured_toast
                            } else {
                                R.string.settings_iptv_refreshed_toast
                            },
                            listOf(channelCount)
                        )
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
                val failMessage = SettingsMessage.Res(
                    if (configured) {
                        R.string.settings_iptv_load_failed
                    } else {
                        R.string.settings_iptv_refresh_failed
                    }
                )
                val failDetail = error.message.orMessage(failMessage)
                _uiState.value = _uiState.value.copy(
                    isIptvLoading = false,
                    iptvError = failDetail,
                    iptvStatusMessage = failDetail,
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

    fun setIptvSortOrder(mode: String) {
        val normalized = normalizeIptvSortOrder(mode)
        _uiState.value = _uiState.value.copy(iptvSortOrder = normalized)
        viewModelScope.launch {
            iptvRepository.saveSortOrder(normalized)
            syncLocalStateToCloud(silent = true)
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
                iptvStatusMessage = SettingsMessage.Res(R.string.settings_iptv_config_removed),
                iptvStatusType = ToastType.SUCCESS,
                iptvProgressText = null,
                iptvProgressPercent = 0,
                toastMessage = SettingsMessage.Res(R.string.settings_iptv_config_removed),
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
            syncLocalStateToCloud(silent = true)
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
                        toastMessage = error.message.orMessage(
                            SettingsMessage.Res(R.string.cloud_login_failed_start)
                        ),
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
                        toastMessage = error.message.orMessage(
                            SettingsMessage.Res(R.string.cloud_signin_failed_start)
                        ),
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
        AuthEmailValidator.validate(trimmedEmail, rejectDisposable = createAccount)?.let { messageRes ->
            _uiState.value = _uiState.value.copy(
                toastMessage = SettingsMessage.Res(messageRes),
                toastType = ToastType.ERROR
            )
            return
        }
        if (password.isBlank()) {
            _uiState.value = _uiState.value.copy(
                toastMessage = SettingsMessage.Res(R.string.settings_cloud_password_required),
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
                    toastMessage = sessionReady.exceptionOrNull()?.message.orMessage(
                        SettingsMessage.Res(R.string.cloud_signin_could_not_start)
                    ),
                    toastType = ToastType.ERROR,
                    isCloudAuthWorking = false
                )
                return@launch
            }

            val userCode = cloudUserCode
            if (userCode.isNullOrBlank()) {
                clearCloudAuthSession()
                _uiState.value = _uiState.value.copy(
                    toastMessage = SettingsMessage.Res(R.string.settings_cloud_session_unavailable),
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
                    toastMessage = SettingsMessage.Res(R.string.settings_waiting_for_approval),
                    toastType = ToastType.INFO,
                    showCloudEmailPasswordDialog = false,
                    isCloudAuthWorking = true
                )
                startCloudPolling()
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.tv_link_failed)
                    ),
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
                                toastMessage = status.message.orMessage(
                                    SettingsMessage.Res(R.string.tv_link_approved_no_tokens)
                                ),
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
                                restoreCloudStateToLocalInternal(
                                    silent = true,
                                    pushPendingLocalFirst = false
                                )
                            } ?: CloudRestoreResult.FAILED
                            if (restoreResult == CloudRestoreResult.FAILED) {
                                delay(1200)
                                restoreResult = withTimeoutOrNull(15_000L) {
                                    restoreCloudStateToLocalInternal(
                                        silent = true,
                                        pushPendingLocalFirst = false
                                    )
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
                                toastMessage = SettingsMessage.Res(
                                    when (restoreResult) {
                                        CloudRestoreResult.RESTORED -> R.string.settings_cloud_signed_in_restored
                                        CloudRestoreResult.NO_BACKUP -> R.string.settings_cloud_signed_in
                                        CloudRestoreResult.FAILED -> R.string.settings_cloud_signed_in_restore_failed
                                    }
                                ),
                                toastType = when (restoreResult) {
                                    CloudRestoreResult.FAILED -> ToastType.ERROR
                                    else -> ToastType.SUCCESS
                                }
                            )
                            return@launch
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isCloudAuthWorking = false,
                                toastMessage = tokenImport.exceptionOrNull()?.message.orMessage(
                                    SettingsMessage.Res(R.string.cloud_failed_import_tokens)
                                ),
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
                            toastMessage = status.message.orMessage(
                                SettingsMessage.Res(R.string.cloud_signin_expired)
                            ),
                            toastType = ToastType.ERROR
                        )
                        clearCloudAuthSession(cancelPolling = false)
                        return@launch
                    }
                    TvDeviceAuthStatusType.ERROR -> {
                        _uiState.value = _uiState.value.copy(
                            isCloudAuthWorking = false,
                            toastMessage = status.message.orMessage(
                                SettingsMessage.Res(R.string.cloud_signin_failed)
                            ),
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
                toastMessage = SettingsMessage.Res(R.string.settings_cloud_signin_incomplete),
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
                toastMessage = SettingsMessage.Res(R.string.settings_homeserver_connecting),
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
                    toastMessage = SettingsMessage.Res(R.string.settings_homeserver_connected),
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    homeServerError = error.message.orMessage(
                        SettingsMessage.Res(R.string.homeserver_connection_failed)
                    ),
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.homeserver_connection_failed)
                    ),
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
                toastMessage = SettingsMessage.Res(R.string.settings_homeserver_code_starting),
                toastType = ToastType.INFO
            )
            val result = homeServerRepository.startHomeServerCodeAuth(trimmedUrl)
            result.onSuccess { session ->
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    plexHomeServerAuth = session,
                    isPlexHomeServerPolling = true,
                    homeServerError = null,
                    toastMessage = SettingsMessage.Res(R.string.settings_homeserver_enter_code),
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
                    homeServerError = error.message.orMessage(
                        SettingsMessage.Res(R.string.homeserver_code_signin_failed)
                    ),
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.homeserver_code_signin_failed)
                    ),
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
                val connectionResult = homeServerRepository.pollHomeServerCodeAuth(
                    session = session,
                    preferredServerUrl = serverUrl,
                    displayName = plexHomeServerDisplayName.orEmpty()
                )
                val connection = connectionResult.getOrElse { error ->
                    lastFailure = error.message
                    null
                }
                if (connection == null) {
                    continue
                }

                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = true,
                    toastMessage = SettingsMessage.Res(R.string.settings_homeserver_connecting_server),
                    toastType = ToastType.INFO
                )
                runCatching {
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
                        toastMessage = SettingsMessage.Res(R.string.settings_homeserver_server_connected),
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
                        homeServerError = error.message.orMessage(
                            SettingsMessage.Res(R.string.homeserver_server_connection_failed)
                        ),
                        toastMessage = error.message.orMessage(
                            SettingsMessage.Res(R.string.homeserver_server_connection_failed)
                        ),
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
                homeServerError = lastFailure.orMessage(
                    SettingsMessage.Res(R.string.settings_homeserver_code_expired)
                ),
                toastMessage = lastFailure.orMessage(
                    SettingsMessage.Res(R.string.settings_homeserver_code_expired)
                ),
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
                    toastMessage = SettingsMessage.Res(R.string.settings_homeserver_reachable),
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isHomeServerConnecting = false,
                    homeServerError = error.message.orMessage(
                        SettingsMessage.Res(R.string.homeserver_test_failed)
                    ),
                    toastMessage = error.message.orMessage(
                        SettingsMessage.Res(R.string.homeserver_test_failed)
                    ),
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
                toastMessage = SettingsMessage.Res(R.string.settings_homeserver_disconnected),
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
        viewModelScope.launch {
            if (!ensureCloudSyncSession()) return@launch
            if (force) {
                cloudSyncRepository.markLocalStateDirtyNow()
            } else {
                cloudSyncRepository.markLocalStateDirty()
            }
            if (!force) {
                delay(350)
            }
            var result = cloudSyncRepository.pushToCloud(force = force)
            if (result.isFailure) {
                delay(1200)
                result = cloudSyncRepository.pushToCloud(force = force)
            }

            if (!silent && result.isSuccess) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = SettingsMessage.Res(R.string.settings_cloud_sync_complete),
                    toastType = ToastType.SUCCESS
                )
            } else if (!silent && result.isFailure) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = result.exceptionOrNull()?.message.orMessage(
                        SettingsMessage.Res(R.string.cloud_sync_failed)
                    ),
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
                lastCloudSyncStatus = SettingsMessage.Res(R.string.settings_cloud_upload_starting_status),
                toastMessage = SettingsMessage.Res(R.string.settings_cloud_force_sync_toast),
                toastType = ToastType.INFO
            )

            if (!ensureCloudSyncSession()) {
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    lastCloudSyncStatus = SettingsMessage.Res(R.string.settings_cloud_session_expired_sync_status),
                    toastMessage = SettingsMessage.Res(R.string.settings_cloud_session_expired_sync_toast),
                    toastType = ToastType.INFO
                )
                return@launch
            }

            // Push local state first (30s timeout), then pull remote state so this device ends
            // with the server-authoritative snapshot after upload.
            cloudSyncRepository.markLocalStateDirtyNow()
            var pushResult = withTimeoutOrNull(30_000L) {
                cloudSyncRepository.pushLocalSnapshotToCloud()
            }
            if (pushResult == null) {
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    lastCloudSyncStatus = SettingsMessage.Res(R.string.settings_cloud_upload_timeout_status),
                    toastMessage = SettingsMessage.Res(R.string.settings_cloud_upload_timeout_toast),
                    toastType = ToastType.ERROR
                )
                return@launch
            }
            if (pushResult.isFailure) {
                delay(1200)
                pushResult = withTimeoutOrNull(30_000L) {
                    cloudSyncRepository.pushLocalSnapshotToCloud()
                }
            }
            if (pushResult == null || pushResult.isFailure) {
                val uploadErrorText = pushResult?.exceptionOrNull()?.message?.takeIf { it.isNotBlank() }
                val uploadError = uploadErrorText.orMessage(
                    SettingsMessage.Res(R.string.cloud_sync_failed_upload)
                )
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    lastCloudSyncStatus = SettingsMessage.Res(
                        R.string.settings_cloud_upload_failed_status,
                        listOf(
                            uploadErrorText?.take(120).orMessage(
                                SettingsMessage.Res(R.string.cloud_sync_failed_upload)
                            )
                        )
                    ),
                    toastMessage = uploadError,
                    toastType = ToastType.ERROR
                )
                return@launch
            }

            // Pull from cloud with timeout and single retry on failure
            var restoreResult = withTimeoutOrNull(30_000L) {
                restoreCloudStateToLocalInternal(
                    silent = true,
                    pushPendingLocalFirst = false
                )
            } ?: CloudRestoreResult.FAILED

            if (restoreResult == CloudRestoreResult.FAILED) {
                delay(1200)
                restoreResult = withTimeoutOrNull(30_000L) {
                    restoreCloudStateToLocalInternal(
                        silent = true,
                        pushPendingLocalFirst = false
                    )
                } ?: CloudRestoreResult.FAILED
            }

            _uiState.value = _uiState.value.copy(
                isForceCloudSyncing = false,
                lastCloudSyncStatus = SettingsMessage.Res(
                    when (restoreResult) {
                        CloudRestoreResult.RESTORED -> R.string.settings_cloud_sync_verified_status
                        CloudRestoreResult.NO_BACKUP -> R.string.settings_cloud_sync_no_restore_status
                        CloudRestoreResult.FAILED -> R.string.settings_cloud_sync_restore_failed_status
                    }
                ),
                toastMessage = SettingsMessage.Res(
                    when (restoreResult) {
                        CloudRestoreResult.RESTORED -> R.string.settings_cloud_sync_complete
                        CloudRestoreResult.NO_BACKUP -> R.string.settings_cloud_sync_complete_no_backup_toast
                        CloudRestoreResult.FAILED -> R.string.settings_cloud_sync_restore_failed_status
                    }
                ),
                toastType = if (restoreResult == CloudRestoreResult.FAILED) {
                    ToastType.ERROR
                } else {
                    ToastType.SUCCESS
                }
            )
        }
    }

    fun forceCloudPushOnly() {
        if (_uiState.value.isForceCloudSyncing) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isForceCloudSyncing = true,
                lastCloudSyncStatus = SettingsMessage.Res(R.string.settings_cloud_push_status_uploading),
                toastMessage = SettingsMessage.Res(R.string.settings_cloud_push_toast_uploading),
                toastType = ToastType.INFO
            )

            if (!ensureCloudSyncSession()) {
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    lastCloudSyncStatus = SettingsMessage.Res(R.string.settings_cloud_session_expired_status),
                    toastMessage = SettingsMessage.Res(R.string.settings_cloud_session_expired_push_toast),
                    toastType = ToastType.INFO
                )
                return@launch
            }

            cloudSyncRepository.markLocalStateDirtyNow()
            val pushResult = withTimeoutOrNull(30_000L) {
                cloudSyncRepository.pushLocalSnapshotToCloud()
            }

            if (pushResult == null || pushResult.isFailure) {
                val uploadErrorText = pushResult?.exceptionOrNull()?.message?.takeIf { it.isNotBlank() }
                val uploadError = uploadErrorText.orMessage(
                    SettingsMessage.Res(R.string.settings_cloud_pull_upload_error_default)
                )
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    lastCloudSyncStatus = SettingsMessage.Res(
                        R.string.settings_cloud_push_failed_status,
                        listOf(
                            uploadErrorText?.take(120).orMessage(
                                SettingsMessage.Res(R.string.settings_cloud_pull_upload_error_default)
                            )
                        )
                    ),
                    toastMessage = uploadError,
                    toastType = ToastType.ERROR
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    lastCloudSyncStatus = SettingsMessage.Res(R.string.settings_cloud_push_success_status),
                    toastMessage = SettingsMessage.Res(R.string.settings_cloud_push_success_toast),
                    toastType = ToastType.SUCCESS
                )
            }
        }
    }

    fun forceCloudPullOnly() {
        if (_uiState.value.isForceCloudSyncing) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isForceCloudSyncing = true,
                lastCloudSyncStatus = SettingsMessage.Res(R.string.settings_cloud_pull_status_pulling),
                toastMessage = SettingsMessage.Res(R.string.settings_cloud_pull_toast_pulling),
                toastType = ToastType.INFO
            )

            if (!ensureCloudSyncSession()) {
                _uiState.value = _uiState.value.copy(
                    isForceCloudSyncing = false,
                    lastCloudSyncStatus = SettingsMessage.Res(R.string.settings_cloud_session_expired_status),
                    toastMessage = SettingsMessage.Res(R.string.settings_cloud_session_expired_pull_toast),
                    toastType = ToastType.INFO
                )
                return@launch
            }

            val restoreResult = withTimeoutOrNull(30_000L) {
                restoreCloudStateToLocalInternal(
                    silent = true,
                    pushPendingLocalFirst = false
                )
            } ?: CloudRestoreResult.FAILED

            _uiState.value = _uiState.value.copy(
                isForceCloudSyncing = false,
                lastCloudSyncStatus = SettingsMessage.Res(
                    when (restoreResult) {
                        CloudRestoreResult.RESTORED -> R.string.settings_cloud_pull_restored_status
                        CloudRestoreResult.NO_BACKUP -> R.string.settings_cloud_pull_no_backup_status
                        CloudRestoreResult.FAILED -> R.string.settings_cloud_pull_failed_status
                    }
                ),
                toastMessage = SettingsMessage.Res(
                    when (restoreResult) {
                        CloudRestoreResult.RESTORED -> R.string.settings_cloud_pull_restored_toast
                        CloudRestoreResult.NO_BACKUP -> R.string.settings_cloud_pull_no_backup_toast
                        CloudRestoreResult.FAILED -> R.string.settings_cloud_pull_failed_status
                    }
                ),
                toastType = if (restoreResult == CloudRestoreResult.FAILED) ToastType.ERROR else ToastType.SUCCESS
            )
        }
    }

    private suspend fun ensureCloudSyncSession(): Boolean {
        if (authRepository.hasValidCloudSyncSession()) {
            return true
        }
        if (authRepository.getCurrentUserIdForSync().isNullOrBlank()) {
            authRepository.checkAuthState()
        }
        return authRepository.hasValidCloudSyncSession()
    }

    private suspend fun restoreCloudStateToLocalInternal(
        silent: Boolean,
        pushPendingLocalFirst: Boolean = true
    ): CloudRestoreResult {
        return when (cloudSyncRepository.pullFromCloud(pushPendingLocalFirst = pushPendingLocalFirst)) {
            CloudSyncRepository.RestoreResult.RESTORED -> {
                loadSettings()
                runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                if (!silent) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = SettingsMessage.Res(R.string.settings_cloud_restore_complete),
                        toastType = ToastType.SUCCESS
                    )
                }
                CloudRestoreResult.RESTORED
            }
            CloudSyncRepository.RestoreResult.NO_BACKUP -> {
                if (!silent) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = SettingsMessage.Res(R.string.settings_cloud_pull_no_backup_toast),
                        toastType = ToastType.INFO
                    )
                }
                CloudRestoreResult.NO_BACKUP
            }
            CloudSyncRepository.RestoreResult.FAILED -> {
                if (!silent) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = SettingsMessage.Res(R.string.settings_cloud_restore_failed),
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
                            toastMessage = SettingsMessage.Res(R.string.settings_update_already_latest),
                            toastType = ToastType.INFO
                        )
                    }
                    updateStatusManager.reset()
                }
            }.onFailure { error ->
                if (showNoUpdateFeedback) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = error.message.orMessage(
                            SettingsMessage.Res(R.string.update_check_failed)
                        ),
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
                    com.arflix.tv.updater.UpdateStatus.Failure(error.message ?: context.getString(R.string.update_download_failed), update)
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

        traktStartupJob?.cancel()
        traktPollingJob?.cancel()
        traktStartupJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                traktCode = null,
                isTraktAuthStarting = true,
                isTraktPolling = false,
                traktUsername = null,
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
                    traktUsername = null,
                    isTraktPolling = true
                )

                // Start polling for token
                startTraktPolling(deviceCode)
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e

                System.err.println("SettingsVM: failed to start Trakt auth: ${e.message}")
                val message: SettingsMessage = when (e) {
                    is retrofit2.HttpException -> SettingsMessage.Res(
                        R.string.settings_trakt_activation_failed_code,
                        listOf(e.code())
                    )
                    else -> e.message.orMessage(
                        SettingsMessage.Res(R.string.settings_trakt_activation_failed)
                    )
                }
                _uiState.value = _uiState.value.copy(
                    traktCode = null,
                    isTraktAuthStarting = false,
                    isTraktPolling = false,
                    traktUsername = null,
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
                traktUsername = null,
                traktExpiration = null
            )
            startTraktAuth()
        }
    }

    private fun startTraktPolling(deviceCode: TraktDeviceCode) {
        traktPollingJob?.cancel()
        traktPollingJob = viewModelScope.launch {
            val expiresAt = System.currentTimeMillis() + (deviceCode.expiresIn * 1000)
            var lastFailure: SettingsMessage? = null
            var pollDelayMs = deviceCode.interval.coerceAtLeast(1) * 1000L

            while (System.currentTimeMillis() < expiresAt) {
                delay(pollDelayMs)

                try {
                    traktRepository.pollForToken(deviceCode.deviceCode)

                    // Get the expiration date
                    val expirationDate = traktRepository.getTokenExpirationDate()

                    // Success. Tracking integrations are independent credentials.
                    simklPollingJob?.cancel()
                    simklPollingJob = null
                    syncProviderStore.onProviderConnected(com.arflix.tv.data.repository.sync.SyncProvider.TRAKT)
                    val simklStillConnected = simklAuthManager.isConnected()
                    val mdbListStillConnected = mdbListRepository.isConnected()
                    val trackingPreferences = syncProviderStore.getTrackingPreferences()
                    _uiState.value = _uiState.value.copy(
                        isTraktAuthenticated = true,
                        traktUsername = null,
                        isMdbListConnected = mdbListStillConnected,
                        isSimklConnected = simklStillConnected,
                        isSimklPolling = false,
                        simklUserCode = null,
                        simklVerificationUrl = null,
                        traktCode = null,
                        isTraktAuthStarting = false,
                        isTraktPolling = false,
                        traktExpiration = expirationDate,
                        trackingWatchlistReadMode = trackingPreferences.watchlistReadMode,
                        trackingContinueReadMode = trackingPreferences.continueWatchingReadMode,
                        trackingWatchedReadMode = trackingPreferences.watchedReadMode,
                        trackingWriteToTrakt = trackingPreferences.writeToTrakt == true,
                        trackingWriteToSimkl = trackingPreferences.writeToSimkl == true,
                        toastMessage = SettingsMessage.Res(R.string.settings_trakt_connected_toast),
                        toastType = ToastType.SUCCESS
                    )
                    refreshIntegrationUsernames(
                        profileManager.getProfileIdSync(),
                        isTraktConnected = true,
                        isMdbListConnected = mdbListStillConnected,
                        isSimklConnected = simklStillConnected
                    )
                    traktRepository.clearContinueWatchingCache()
                    runCatching { traktRepository.getContinueWatching() }
                    performFullSync(silent = true)
                    syncLocalStateToCloud(silent = true, force = true)
                    runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                    return@launch
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e

                    val httpError = e as? retrofit2.HttpException
                    val isPending = when {
                        httpError?.code() == 400 -> true
                        else -> e.message?.contains("400") == true ||
                            e.message?.contains("pending", ignoreCase = true) == true
                    }
                    if (isPending) continue

                    // Trakt uses 429 to ask device clients to slow down. Keep the
                    // activation alive and honor Retry-After instead of aborting it.
                    if (httpError?.code() == 429) {
                        val retryAfterMs = httpError.response()
                            ?.headers()
                            ?.get("Retry-After")
                            ?.toLongOrNull()
                            ?.times(1000L)
                        pollDelayMs = maxOf(
                            pollDelayMs + 1_000L,
                            retryAfterMs ?: 0L
                        ).coerceAtMost(30_000L)
                        continue
                    }

                    lastFailure = when (httpError?.code()) {
                        404 -> SettingsMessage.Res(R.string.settings_trakt_code_invalid)
                        409 -> SettingsMessage.Res(R.string.settings_trakt_code_used)
                        410 -> SettingsMessage.Res(R.string.settings_trakt_code_expired)
                        418 -> SettingsMessage.Res(R.string.settings_trakt_denied)
                        null -> e.message.orMessage(
                            SettingsMessage.Res(R.string.settings_trakt_auth_failed)
                        )
                        else -> SettingsMessage.Res(
                            R.string.settings_trakt_auth_failed_code,
                            listOf(httpError.code())
                        )
                    }
                    break
                }
            }

            // Expired or failed
            _uiState.value = _uiState.value.copy(
                traktCode = null,
                isTraktAuthStarting = false,
                isTraktPolling = false,
                traktUsername = null,
                toastMessage = lastFailure ?: SettingsMessage.Res(R.string.settings_trakt_code_expired),
                toastType = ToastType.ERROR
            )
        }
    }

    fun cancelTraktAuth() {
        traktPollingJob?.cancel()
        traktStartupJob?.cancel()
        _uiState.value = _uiState.value.copy(
            traktCode = null,
            isTraktAuthStarting = false,
            isTraktPolling = false,
            traktUsername = null
        )
    }

    fun disconnectTrakt() {
        viewModelScope.launch {
            cancelTraktAuth()
            traktRepository.logout()
            syncProviderStore.onProviderDisconnected(com.arflix.tv.data.repository.sync.SyncProvider.TRAKT)
            val preferences = syncProviderStore.getTrackingPreferences()
            _uiState.value = _uiState.value.copy(
                isTraktAuthenticated = false,
                traktUsername = null,
                traktExpiration = null,
                lastSyncTime = null,
                syncedMovies = 0,
                syncedEpisodes = 0,
                trackingWatchlistReadMode = preferences.watchlistReadMode,
                trackingContinueReadMode = preferences.continueWatchingReadMode,
                trackingWatchedReadMode = preferences.watchedReadMode,
                trackingWriteToTrakt = false,
                trackingWriteToSimkl = preferences.writeToSimkl == true,
                toastMessage = SettingsMessage.Res(R.string.settings_trakt_disconnected),
                toastType = ToastType.SUCCESS
            )
            syncLocalStateToCloud(silent = true, force = true)
        }
    }

    // ========== MDBList Authentication (API key) ==========

    /** Connect MDBList using a user API key from mdblist.com/preferences. */
    fun connectMdbList(apiKey: String) {
        val trimmed = apiKey.trim()
        if (trimmed.isEmpty() || _uiState.value.mdbListConnecting) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(mdbListConnecting = true)
            val valid = runCatching { mdbListRepository.validateKey(trimmed) }.getOrDefault(false)
            if (!valid) {
                _uiState.value = _uiState.value.copy(
                    mdbListConnecting = false,
                    toastMessage = SettingsMessage.Res(R.string.mdblist_invalid_key),
                    toastType = ToastType.ERROR
                )
                return@launch
            }
            syncProviderStore.setMdbListApiKey(trimmed)
            syncProviderStore.onProviderConnected(com.arflix.tv.data.repository.sync.SyncProvider.MDBLIST)
            val traktStillConnected = traktRepository.hasTrakt()
            val simklStillConnected = simklAuthManager.isConnected()
            val trackingPreferences = syncProviderStore.getTrackingPreferences()
            _uiState.value = _uiState.value.copy(
                mdbListConnecting = false,
                isMdbListConnected = true,
                mdbListUsername = null,
                isTraktAuthenticated = traktStillConnected,
                isSimklConnected = simklStillConnected,
                lastSyncTime = null,
                syncedMovies = 0,
                syncedEpisodes = 0,
                trackingWatchlistReadMode = trackingPreferences.watchlistReadMode,
                trackingContinueReadMode = trackingPreferences.continueWatchingReadMode,
                trackingWatchedReadMode = trackingPreferences.watchedReadMode,
                trackingWriteToTrakt = trackingPreferences.writeToTrakt == true,
                trackingWriteToSimkl = trackingPreferences.writeToSimkl == true,
                toastMessage = SettingsMessage.Res(R.string.mdblist_connected),
                toastType = ToastType.SUCCESS
            )
            refreshIntegrationUsernames(
                profileManager.getProfileIdSync(),
                isTraktConnected = traktStillConnected,
                isMdbListConnected = true,
                isSimklConnected = simklStillConnected
            )
            // The MDBList watchlist is pulled when the Watchlist screen next loads.
            syncLocalStateToCloud(silent = true, force = true)
            runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
        }
    }

    fun disconnectMdbList() {
        viewModelScope.launch {
            syncProviderStore.setMdbListApiKey(null)
            syncProviderStore.onProviderDisconnected(com.arflix.tv.data.repository.sync.SyncProvider.MDBLIST)
            val trackingPreferences = syncProviderStore.getTrackingPreferences()
            _uiState.value = _uiState.value.copy(
                isMdbListConnected = false,
                mdbListUsername = null,
                trackingWatchlistReadMode = trackingPreferences.watchlistReadMode,
                trackingContinueReadMode = trackingPreferences.continueWatchingReadMode,
                trackingWatchedReadMode = trackingPreferences.watchedReadMode,
                trackingWriteToTrakt = trackingPreferences.writeToTrakt == true,
                trackingWriteToSimkl = trackingPreferences.writeToSimkl == true,
                toastMessage = SettingsMessage.Res(R.string.mdblist_disconnected),
                toastType = ToastType.SUCCESS
            )
            syncLocalStateToCloud(silent = true, force = true)
        }
    }

    // ========== Simkl Authentication ==========

    fun startSimklAuth() {
        simklPollingJob?.cancel()
        simklPollingJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSimklAuthStarting = true)
            runCatching {
                val pinRes = simklAuthManager.startPinAuth()
                _uiState.value = _uiState.value.copy(
                    isSimklAuthStarting = false,
                    isSimklPolling = true,
                    simklUserCode = pinRes.userCode,
                    simklVerificationUrl = pinRes.verificationUrl
                )
                startSimklPolling(pinRes.userCode, pinRes.expiresIn, pinRes.interval)
            }.onFailure { e ->
                if (e is CancellationException) throw e
                _uiState.value = _uiState.value.copy(
                    isSimklAuthStarting = false,
                    isSimklPolling = false,
                    simklUserCode = null,
                    simklVerificationUrl = null,
                    toastMessage = SettingsMessage.Res(
                        R.string.settings_simkl_auth_error,
                        listOf(e.message.orEmpty())
                    ),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    private fun startSimklPolling(userCode: String, expiresInSec: Int, intervalSec: Int) {
        simklPollingJob?.cancel()
        simklPollingJob = viewModelScope.launch {
            val expiresAt = System.currentTimeMillis() + (expiresInSec * 1000L)
            val pollDelayMs = intervalSec.coerceAtLeast(3) * 1000L

            while (System.currentTimeMillis() < expiresAt) {
                delay(pollDelayMs)
                try {
                    val success = simklAuthManager.pollPinAuth(userCode)
                    if (success) {
                        syncProviderStore.onProviderConnected(com.arflix.tv.data.repository.sync.SyncProvider.SIMKL)
                        val traktStillConnected = traktRepository.hasTrakt()
                        val mdbListStillConnected = mdbListRepository.isConnected()
                        val trackingPreferences = syncProviderStore.getTrackingPreferences()
                        _uiState.value = _uiState.value.copy(
                            isSimklPolling = false,
                            isSimklConnected = true,
                            simklUserCode = null,
                            simklVerificationUrl = null,
                            isTraktAuthenticated = traktStillConnected,
                            isMdbListConnected = mdbListStillConnected,
                            trackingWatchlistReadMode = trackingPreferences.watchlistReadMode,
                            trackingContinueReadMode = trackingPreferences.continueWatchingReadMode,
                            trackingWatchedReadMode = trackingPreferences.watchedReadMode,
                            trackingWriteToTrakt = trackingPreferences.writeToTrakt == true,
                            trackingWriteToSimkl = trackingPreferences.writeToSimkl == true,
                            toastMessage = SettingsMessage.Res(R.string.settings_simkl_connected),
                            toastType = ToastType.SUCCESS
                        )
                        refreshIntegrationUsernames(
                            profileManager.getProfileIdSync(),
                            isTraktConnected = traktStillConnected,
                            isMdbListConnected = mdbListStillConnected,
                            isSimklConnected = true
                        )
                        syncLocalStateToCloud(silent = true, force = true)
                        runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                        return@launch
                    }
                } catch (e: Exception) {
                    if (e is CancellationException) throw e
                    AppLogger.e("SettingsViewModel", "Simkl polling error: ${e.message}")
                }
            }
            _uiState.value = _uiState.value.copy(
                isSimklPolling = false,
                simklUserCode = null,
                simklVerificationUrl = null,
                toastMessage = SettingsMessage.Res(R.string.settings_simkl_timed_out),
                toastType = ToastType.ERROR
            )
        }
    }

    fun pollSimklAuth() {
        val userCode = _uiState.value.simklUserCode ?: return
        simklPollingJob?.cancel()
        simklPollingJob = viewModelScope.launch {
            runCatching {
                val success = simklAuthManager.pollPinAuth(userCode)
                if (success) {
                    syncProviderStore.onProviderConnected(com.arflix.tv.data.repository.sync.SyncProvider.SIMKL)
                    val traktStillConnected = traktRepository.hasTrakt()
                    val mdbListStillConnected = mdbListRepository.isConnected()
                    val trackingPreferences = syncProviderStore.getTrackingPreferences()
                    _uiState.value = _uiState.value.copy(
                        isSimklPolling = false,
                        isSimklConnected = true,
                        simklUserCode = null,
                        simklVerificationUrl = null,
                        isTraktAuthenticated = traktStillConnected,
                        isMdbListConnected = mdbListStillConnected,
                        trackingWatchlistReadMode = trackingPreferences.watchlistReadMode,
                        trackingContinueReadMode = trackingPreferences.continueWatchingReadMode,
                        trackingWatchedReadMode = trackingPreferences.watchedReadMode,
                        trackingWriteToTrakt = trackingPreferences.writeToTrakt == true,
                        trackingWriteToSimkl = trackingPreferences.writeToSimkl == true,
                        toastMessage = SettingsMessage.Res(R.string.settings_simkl_connected),
                        toastType = ToastType.SUCCESS
                    )
                    refreshIntegrationUsernames(
                        profileManager.getProfileIdSync(),
                        isTraktConnected = traktStillConnected,
                        isMdbListConnected = mdbListStillConnected,
                        isSimklConnected = true
                    )
                    syncLocalStateToCloud(silent = true, force = true)
                    runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                }
            }
        }
    }

    fun disconnectSimkl() {
        simklPollingJob?.cancel()
        simklPollingJob = null
        viewModelScope.launch {
            simklAuthManager.disconnect()
            syncProviderStore.onProviderDisconnected(com.arflix.tv.data.repository.sync.SyncProvider.SIMKL)
            val preferences = syncProviderStore.getTrackingPreferences()
            _uiState.value = _uiState.value.copy(
                isSimklConnected = false,
                isSimklPolling = false,
                simklUserCode = null,
                simklVerificationUrl = null,
                simklUsername = null,
                trackingWatchlistReadMode = preferences.watchlistReadMode,
                trackingContinueReadMode = preferences.continueWatchingReadMode,
                trackingWatchedReadMode = preferences.watchedReadMode,
                trackingWriteToTrakt = preferences.writeToTrakt == true,
                trackingWriteToSimkl = false,
                toastMessage = SettingsMessage.Res(R.string.settings_simkl_disconnected),
                toastType = ToastType.SUCCESS
            )
            syncLocalStateToCloud(silent = true, force = true)
        }
    }

    fun setTrackingReadMode(
        feature: com.arflix.tv.data.repository.sync.TrackingFeature,
        mode: com.arflix.tv.data.repository.sync.TrackingReadMode
    ) {
        viewModelScope.launch {
            syncProviderStore.setReadMode(feature, mode)
            val preferences = syncProviderStore.getTrackingPreferences()
            _uiState.value = _uiState.value.copy(
                trackingWatchlistReadMode = preferences.watchlistReadMode,
                trackingContinueReadMode = preferences.continueWatchingReadMode,
                trackingWatchedReadMode = preferences.watchedReadMode
            )
            syncLocalStateToCloud(silent = true, force = true)
        }
    }

    fun setTrackingWriteTarget(provider: com.arflix.tv.data.repository.sync.SyncProvider, enabled: Boolean) {
        viewModelScope.launch {
            syncProviderStore.setWriteTarget(provider, enabled)
            val preferences = syncProviderStore.getTrackingPreferences()
            _uiState.value = _uiState.value.copy(
                trackingWriteToTrakt = preferences.writeToTrakt == true,
                trackingWriteToSimkl = preferences.writeToSimkl == true
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
            _uiState.value = _uiState.value.copy(
                toastMessage = SettingsMessage.Res(R.string.settings_signing_out),
                toastType = ToastType.INFO
            )
            authRepository.signOut()
            _uiState.value = _uiState.value.copy(
                toastMessage = SettingsMessage.Res(R.string.settings_signed_out),
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
        .joinToString("|") { playlist ->
            listOf(
                playlist.id,
                playlist.name,
                playlist.m3uUrl,
                playlist.epgUrl,
                playlist.epgUrls.orEmpty().joinToString(","),
                playlist.enabled.toString()
            ).joinToString("~")
        }
    val stalkerSignature = stalkerPortals
        .joinToString("|") { portal ->
            listOf(
                portal.id,
                portal.name,
                portal.portalUrl,
                portal.macAddress,
                portal.enabled.toString()
            ).joinToString("~")
        }
    return listOf(
        m3uUrl,
        epgUrl,
        stalkerSignature,
        playlistsSignature
    ).joinToString("||")
}
