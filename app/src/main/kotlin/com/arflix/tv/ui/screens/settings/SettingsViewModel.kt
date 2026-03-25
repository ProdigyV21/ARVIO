package com.arflix.tv.ui.screens.settings

import android.content.Context
import coil.Coil
import com.arflix.tv.BuildConfig
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.api.TraktDeviceCode
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.TvDeviceAuthRepository
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
import java.io.File
import javax.inject.Inject

enum class ToastType {
    SUCCESS, ERROR, INFO
}

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
    val autoPlayMaxQuality: String = "4K",
    val dnsProvider: String = "System DNS",
    val dnsProviderOptions: List<String> = listOf("System DNS", "Cloudflare", "Google", "AdGuard"),
    val subtitleSize: String = "Medium",
    val subtitleColor: String = "White",
    val includeSpecials: Boolean = false,
    val isLoggedIn: Boolean = false,
    val accountEmail: String? = null,
    val showCloudPairDialog: Boolean = false,
    val cloudUserCode: String? = null,
    val cloudVerificationUrl: String? = null,
    val showCloudEmailPasswordDialog: Boolean = false,
    val isCloudAuthWorking: Boolean = false,
    val shouldSwitchProfile: Boolean = false,
    // Trakt
    val isTraktAuthenticated: Boolean = false,
    val traktCode: TraktDeviceCode? = null,
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
    val iptvChannelCount: Int = 0,
    val isIptvLoading: Boolean = false,
    val iptvError: String? = null,
    val iptvStatusMessage: String? = null,
    val iptvStatusType: ToastType = ToastType.INFO,
    val iptvProgressText: String? = null,
    val iptvProgressPercent: Int = 0,
    // App updates
    val isSelfUpdateSupported: Boolean = true,
    val isCheckingForUpdate: Boolean = false,
    val availableAppUpdate: AppUpdate? = null,
    val isAppUpdateAvailable: Boolean = false,
    val isDownloadingAppUpdate: Boolean = false,
    val appUpdateDownloadProgress: Float? = null,
    val downloadedApkPath: String? = null,
    val showAppUpdateDialog: Boolean = false,
    val showUnknownSourcesDialog: Boolean = false,
    val appUpdateError: String? = null,
    // Catalogs
    val catalogs: List<CatalogConfig> = emptyList(),
    // Addons
    val addons: List<Addon> = emptyList(),
    val torrServerBaseUrl: String = "",
    // Content language (TMDB metadata)
    val contentLanguage: String = "en-US",
    // Device mode override
    val deviceModeOverride: String = "auto",
    // Toast
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.INFO
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val traktRepository: TraktRepository,
    private val streamRepository: StreamRepository,
    private val mediaRepository: MediaRepository,
    private val catalogRepository: CatalogRepository,
    private val iptvRepository: IptvRepository,
    private val watchlistRepository: WatchlistRepository,
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val tvDeviceAuthRepository: TvDeviceAuthRepository,
    private val traktSyncService: TraktSyncService,
    private val cloudSyncRepository: CloudSyncRepository,
    private val launcherContinueWatchingRepository: LauncherContinueWatchingRepository,
    private val appUpdateRepository: AppUpdateRepository,
    private val updatePreferences: UpdatePreferences,
    private val apkDownloader: ApkDownloader
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private fun contentLanguageKey() = profileManager.profileStringKey("content_language")

    private fun defaultSubtitleKey() = profileManager.profileStringKey("default_subtitle")
    private fun defaultSubtitleKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "default_subtitle")
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
    private fun autoPlayMaxQualityKey() = profileManager.profileStringKey("auto_play_max_quality")
    private fun autoPlayMaxQualityKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "auto_play_max_quality")
    private fun subtitleSizeKey() = profileManager.profileStringKey("subtitle_size")
    private fun subtitleColorKey() = profileManager.profileStringKey("subtitle_color")
    private fun dnsProviderKey() = profileManager.profileStringKey("dns_provider")
    private fun includeSpecialsKey() = profileManager.profileBooleanKey("include_specials")
    private fun includeSpecialsKeyFor(profileId: String) = profileManager.profileBooleanKeyFor(profileId, "include_specials")
    private val gson = Gson()
    private var lastObservedIptvM3u: String = ""

    private var traktPollingJob: Job? = null
    private var iptvLoadJob: Job? = null
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

    private enum class CloudRestoreResult {
        RESTORED,
        NO_BACKUP,
        FAILED
    }

    init {
        loadSettings()
        observeProfileChanges()
        observeAddons()
        observeTorrServer()
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
            val autoPlayMaxQuality = normalizeAutoPlayMaxQuality(prefs[autoPlayMaxQualityKey()])
            val subtitleSize = prefs[subtitleSizeKey()] ?: "Medium"
            val subtitleColor = prefs[subtitleColorKey()] ?: "White"
            val dnsProviderValue = normalizeDnsProviderValue(prefs[dnsProviderKey()])
            val includeSpecials = prefs[includeSpecialsKey()] ?: false

            // Check auth statuses
            val authState = authRepository.authState.first()
            val isLoggedIn = authState is AuthState.Authenticated
            val accountEmail = (authState as? AuthState.Authenticated)?.email
            val isTrakt = traktRepository.isAuthenticated.first()

            // Get Trakt expiration if authenticated
            var traktExpiration: String? = null
            if (isTrakt) {
                traktExpiration = traktRepository.getTokenExpirationDate()
            }

            // Load addons immediately to avoid showing 0
            val addons = streamRepository.installedAddons.first()
            val subtitleOptions = loadSubtitleOptions(defaultSub)
            val audioLanguageOptions = loadAudioLanguageOptions(defaultAudio)
            val iptvConfig = iptvRepository.observeConfig().first()
            val existingCatalogs = _uiState.value.catalogs.ifEmpty {
                mediaRepository.getDefaultCatalogConfigs()
            }

            val currentState = _uiState.value
            _uiState.value = SettingsUiState(
                defaultSubtitle = defaultSub,
                subtitleOptions = subtitleOptions,
                defaultAudioLanguage = defaultAudio,
                audioLanguageOptions = audioLanguageOptions,
                cardLayoutMode = cardLayoutMode,
                frameRateMatchingMode = frameRateMode,
                autoPlayNext = autoPlay,
                autoPlaySingleSource = autoPlaySingleSource,
                autoPlayMinQuality = autoPlayMinQuality,
                autoPlayMaxQuality = autoPlayMaxQuality,
                subtitleSize = subtitleSize,
                subtitleColor = subtitleColor,
                dnsProvider = dnsProviderLabel(dnsProviderValue),
                includeSpecials = includeSpecials,
                isLoggedIn = isLoggedIn,
                accountEmail = accountEmail,
                isTraktAuthenticated = isTrakt,
                traktExpiration = traktExpiration,
                iptvM3uUrl = iptvConfig.m3uUrl,
                iptvEpgUrl = iptvConfig.epgUrl,
                isSelfUpdateSupported = currentState.isSelfUpdateSupported,
                isCheckingForUpdate = currentState.isCheckingForUpdate,
                availableAppUpdate = currentState.availableAppUpdate,
                isAppUpdateAvailable = currentState.isAppUpdateAvailable,
                isDownloadingAppUpdate = currentState.isDownloadingAppUpdate,
                appUpdateDownloadProgress = currentState.appUpdateDownloadProgress,
                downloadedApkPath = currentState.downloadedApkPath,
                showAppUpdateDialog = currentState.showAppUpdateDialog,
                showUnknownSourcesDialog = currentState.showUnknownSourcesDialog,
                appUpdateError = currentState.appUpdateError,
                catalogs = existingCatalogs,
                addons = addons,
                contentLanguage = contentLang,
                deviceModeOverride = deviceModeOverride
            )
        }
    }

    private fun observeProfileChanges() {
        viewModelScope.launch {
            profileManager.activeProfileId.collect { profileId ->
                if (observedProfileId == profileId) return@collect
                observedProfileId = profileId
                hasObservedIptvConfig = false
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
            context.settingsDataStore.edit { prefs ->
                prefs[defaultSubtitleKey()] = language
            }
            _uiState.value = _uiState.value.copy(
                defaultSubtitle = language,
                subtitleOptions = loadSubtitleOptions(language)
            )

            // Sync to cloud
            authRepository.saveDefaultSubtitleToProfile(language)
            syncLocalStateToCloud(silent = true)
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

    fun cycleAutoPlayMaxQuality() {
        val current = normalizeAutoPlayMaxQuality(_uiState.value.autoPlayMaxQuality)
        val next = when (current) {
            "Any" -> "720p"
            "720p" -> "1080p"
            "1080p" -> "4K"
            else -> "Any"
        }
        setAutoPlayMaxQuality(next)
    }

    private fun setAutoPlayMaxQuality(value: String) {
        val normalized = normalizeAutoPlayMaxQuality(value)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[autoPlayMaxQualityKey()] = normalized
            }
            _uiState.value = _uiState.value.copy(autoPlayMaxQuality = normalized)
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
            }
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
            _uiState.value = _uiState.value.copy(deviceModeOverride = mode)
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

    private fun normalizeAutoPlayMaxQuality(raw: String?): String {
        return when (raw?.trim()?.lowercase()) {
            "any" -> "Any"
            "720p", "hd" -> "720p"
            "1080p", "fullhd", "fhd" -> "1080p"
            "4k", "2160p", "uhd" -> "4K"
            else -> "4K"
        }
    }

    fun cycleSubtitleSize() {
        val next = when (_uiState.value.subtitleSize) { "Small" -> "Medium"; "Medium" -> "Large"; "Large" -> "Extra Large"; else -> "Small" }
        viewModelScope.launch { context.settingsDataStore.edit { it[subtitleSizeKey()] = next }; _uiState.value = _uiState.value.copy(subtitleSize = next) }
    }

    fun cycleSubtitleColor() {
        val next = when (_uiState.value.subtitleColor) { "White" -> "Yellow"; "Yellow" -> "Green"; "Green" -> "Cyan"; else -> "White" }
        viewModelScope.launch { context.settingsDataStore.edit { it[subtitleColorKey()] = next }; _uiState.value = _uiState.value.copy(subtitleColor = next) }
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
            }
            context.settingsDataStore.edit { prefs ->
                prefs[dnsProviderKey()] = value
            }
            _uiState.value = _uiState.value.copy(
                dnsProvider = dnsProviderLabel(value)
            )

            // Keep image requests consistent if restart is delayed by the system.
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
                if (current.iptvM3uUrl != config.m3uUrl || current.iptvEpgUrl != config.epgUrl) {
                    _uiState.value = current.copy(
                        iptvM3uUrl = config.m3uUrl,
                        iptvEpgUrl = config.epgUrl
                    )
                }
                if (!hasObservedIptvConfig) {
                    hasObservedIptvConfig = true
                    lastObservedIptvM3u = config.m3uUrl
                    if (config.m3uUrl.isBlank()) {
                        _uiState.value = _uiState.value.copy(
                            iptvChannelCount = 0,
                            iptvError = null,
                            iptvProgressText = null,
                            iptvProgressPercent = 0
                        )
                    } else if (iptvLoadJob?.isActive != true && _uiState.value.iptvChannelCount == 0) {
                        // Auto-refresh IPTV on startup/profile switch when configured but not loaded yet.
                        refreshIptv(showToast = false, force = false)
                    }
                    return@collect
                }

                if (config.m3uUrl.isNotBlank() && config.m3uUrl != lastObservedIptvM3u) {
                    lastObservedIptvM3u = config.m3uUrl
                    if (iptvLoadJob?.isActive != true) {
                        refreshIptv(showToast = false, force = false)
                    }
                } else if (config.m3uUrl.isBlank()) {
                    lastObservedIptvM3u = ""
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
                if (_uiState.value.catalogs != effectiveCatalogs) {
                    _uiState.value = _uiState.value.copy(catalogs = effectiveCatalogs)
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
                _uiState.value = _uiState.value.copy(
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

    fun refreshIptv(showToast: Boolean = true, configured: Boolean = false, force: Boolean = true) {
        viewModelScope.launch {
            val currentConfig = iptvRepository.observeConfig().first()
            if (currentConfig.m3uUrl.isBlank()) return@launch

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
                    forceEpgReload = false
                ) { progress ->
                    _uiState.value = _uiState.value.copy(
                        isIptvLoading = true,
                        iptvProgressText = progress.message,
                        iptvProgressPercent = progress.percent ?: _uiState.value.iptvProgressPercent
                    )
                }
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
            tvDeviceAuthRepository.startSession()
                .onSuccess { session ->
                    cloudDeviceCode = session.deviceCode
                    cloudUserCode = session.userCode
                    cloudVerificationUrl = session.verificationUrl
                    cloudPollIntervalMs = (session.intervalSeconds.coerceIn(1, 10) * 1000L)
                    cloudExpiresAtMs = System.currentTimeMillis() + (session.expiresInSeconds.coerceAtLeast(30) * 1000L)
                    _uiState.value = _uiState.value.copy(
                        isCloudAuthWorking = false,
                        showCloudPairDialog = true,
                        cloudUserCode = session.userCode,
                        cloudVerificationUrl = session.verificationUrl
                    )
                    startCloudPolling()
                }
                .onFailure { error ->
                    cloudDeviceCode = null
                    cloudUserCode = null
                    cloudVerificationUrl = null
                    cloudPollIntervalMs = 800L
                    cloudExpiresAtMs = 0L
                    _uiState.value = _uiState.value.copy(
                        isCloudAuthWorking = false,
                        toastMessage = error.message ?: "Failed to start cloud login",
                        toastType = ToastType.ERROR
                    )
                }
        }
    }

    fun cancelCloudAuth() {
        cloudDeviceCode = null
        cloudUserCode = null
        cloudVerificationUrl = null
        cloudPollingJob?.cancel()
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
        // Show email/password dialog directly (used on mobile where QR scanning isn't practical).
        // If we already have an active pairing session, keep it running in the background.
        _uiState.value = _uiState.value.copy(
            showCloudPairDialog = false,
            showCloudEmailPasswordDialog = true
        )
        // Start the device auth session in the background if not already active,
        // so polling still works if the user later switches to QR on a different device.
        if (cloudDeviceCode.isNullOrBlank() || cloudUserCode.isNullOrBlank()) {
            viewModelScope.launch {
                runCatching {
                    tvDeviceAuthRepository.startSession().onSuccess { session ->
                        cloudDeviceCode = session.deviceCode
                        cloudUserCode = session.userCode
                        cloudVerificationUrl = session.verificationUrl
                        cloudPollIntervalMs = (session.intervalSeconds.coerceIn(1, 10) * 1000L)
                        cloudExpiresAtMs = System.currentTimeMillis() + (session.expiresInSeconds.coerceAtLeast(30) * 1000L)
                        startCloudPolling()
                    }
                }
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
        val deviceCode = cloudDeviceCode
        val userCode = cloudUserCode
        val trimmedEmail = email.trim()

        if (deviceCode.isNullOrBlank() || userCode.isNullOrBlank()) {
            _uiState.value = _uiState.value.copy(
                toastMessage = "Cloud login expired. Open ARVIO Cloud again.",
                toastType = ToastType.ERROR,
                isCloudAuthWorking = false,
                showCloudEmailPasswordDialog = false
            )
            cloudDeviceCode = null
            cloudUserCode = null
            cloudExpiresAtMs = 0L
            return
        }
        if (trimmedEmail.isBlank()) {
            _uiState.value = _uiState.value.copy(
                toastMessage = "Email is required",
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
                            cloudDeviceCode = null
                            cloudUserCode = null
                            cloudVerificationUrl = null
                            cloudExpiresAtMs = 0L
                            pendingProfileSwitchAfterCloudLogin = true
                            _uiState.value = _uiState.value.copy(
                                isCloudAuthWorking = false,
                                showCloudPairDialog = false,
                                showCloudEmailPasswordDialog = false,
                                cloudUserCode = null,
                                cloudVerificationUrl = null,
                                toastMessage = "Signed in successfully",
                                toastType = ToastType.SUCCESS
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
                        cloudDeviceCode = null
                        cloudUserCode = null
                        cloudVerificationUrl = null
                        cloudExpiresAtMs = 0L
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
        }
    }

    fun syncLocalStateToCloud(silent: Boolean = false, force: Boolean = false) {
        if (!force && !_uiState.value.isLoggedIn) return
        if (authRepository.getCurrentUserId().isNullOrBlank()) return
        viewModelScope.launch {
            // Small delay to ensure DataStore writes from the caller have flushed
            // before we snapshot all profiles for cloud upload.
            delay(350)
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
            _uiState.value = _uiState.value.copy(
                isSelfUpdateSupported = false,
                showAppUpdateDialog = force,
                appUpdateError = if (force) "This install is managed by the Play Store." else null
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isCheckingForUpdate = true,
                appUpdateError = null,
                showAppUpdateDialog = false
            )

            val ignoredTag = updatePreferences.ignoredTag.first()
            val result = appUpdateRepository.getLatestUpdate()
            updatePreferences.setLastCheckAtMs(System.currentTimeMillis())

            result
                .onSuccess { update ->
                    // Use the actually installed version from PackageManager, not BuildConfig,
                    // because on Android TV the old process can survive an APK install.
                    val installedVersion = appUpdateRepository.getInstalledVersionName()
                    val remoteNewer = VersionUtils.isRemoteNewer(update.tag, installedVersion)
                    val shouldShow = remoteNewer && (ignoredTag == null || ignoredTag != update.tag)

                    _uiState.value = _uiState.value.copy(
                        isCheckingForUpdate = false,
                        availableAppUpdate = update,
                        isAppUpdateAvailable = remoteNewer,
                        isDownloadingAppUpdate = false,
                        appUpdateDownloadProgress = null,
                        downloadedApkPath = if (remoteNewer) _uiState.value.downloadedApkPath else null,
                        showAppUpdateDialog = shouldShow || force,
                        appUpdateError = null,
                        toastMessage = if (showNoUpdateFeedback && !remoteNewer) "You already have the latest version" else _uiState.value.toastMessage,
                        toastType = if (showNoUpdateFeedback && !remoteNewer) ToastType.INFO else _uiState.value.toastType
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isCheckingForUpdate = false,
                        availableAppUpdate = null,
                        isAppUpdateAvailable = false,
                        showAppUpdateDialog = force,
                        appUpdateError = error.message ?: "Update check failed"
                    )
                }
        }
    }

    fun dismissAppUpdateDialog() {
        _uiState.value = _uiState.value.copy(showAppUpdateDialog = false, showUnknownSourcesDialog = false, appUpdateError = null)
    }

    fun ignoreAppUpdate() {
        viewModelScope.launch {
            updatePreferences.setIgnoredTag(_uiState.value.availableAppUpdate?.tag)
            _uiState.value = _uiState.value.copy(showAppUpdateDialog = false)
        }
    }

    fun downloadAppUpdate() {
        val update = _uiState.value.availableAppUpdate ?: return
        if (!appUpdateRepository.supportsSelfUpdate()) {
            _uiState.value = _uiState.value.copy(
                toastMessage = "This install is managed by the Play Store.",
                toastType = ToastType.INFO
            )
            return
        }
        if (!_uiState.value.isAppUpdateAvailable) {
            _uiState.value = _uiState.value.copy(
                toastMessage = "You already have the latest version",
                toastType = ToastType.INFO,
                showAppUpdateDialog = true,
                downloadedApkPath = null
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isDownloadingAppUpdate = true,
                appUpdateDownloadProgress = 0f,
                appUpdateError = null,
                showAppUpdateDialog = true
            )

            val safeName = update.assetName.replace(Regex("[^a-zA-Z0-9._-]"), "_")
            val dest = File(File(context.cacheDir, "updates"), safeName)
            val result = withContext(Dispatchers.IO) {
                apkDownloader.download(update.assetUrl, dest) { downloaded, total ->
                    val progress = if (total != null && total > 0L) {
                        (downloaded.toFloat() / total.toFloat()).coerceIn(0f, 1f)
                    } else {
                        null
                    }
                    _uiState.value = _uiState.value.copy(appUpdateDownloadProgress = progress)
                }
            }

            result
                .onSuccess { file ->
                    _uiState.value = _uiState.value.copy(
                        isDownloadingAppUpdate = false,
                        appUpdateDownloadProgress = 1f,
                        downloadedApkPath = file.absolutePath,
                        appUpdateError = null,
                        showAppUpdateDialog = true
                    )
                    installAppUpdateOrRequestPermission()
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isDownloadingAppUpdate = false,
                        appUpdateDownloadProgress = null,
                        downloadedApkPath = null,
                        appUpdateError = error.message ?: "Download failed",
                        showAppUpdateDialog = true
                    )
                }
        }
    }

    fun installAppUpdateOrRequestPermission() {
        val apkPath = _uiState.value.downloadedApkPath ?: return
        val apkFile = File(apkPath)
        if (!apkFile.exists()) {
            _uiState.value = _uiState.value.copy(appUpdateError = "Downloaded file is missing", showAppUpdateDialog = true)
            return
        }

        if (!ApkInstaller.canRequestPackageInstalls(context)) {
            _uiState.value = _uiState.value.copy(showUnknownSourcesDialog = true, showAppUpdateDialog = false)
            return
        }

        // Check for signature conflict before installing
        val conflictMsg = ApkInstaller.checkSignatureConflict(context, apkFile)
        if (conflictMsg != null) {
            _uiState.value = _uiState.value.copy(appUpdateError = conflictMsg, showAppUpdateDialog = true)
            return
        }

        ApkInstaller.launchInstall(context, apkFile)
        // Mark this release as "installed" so we don't re-show the update after the
        // system installer returns the user to the old still-running process.
        viewModelScope.launch {
            _uiState.value.availableAppUpdate?.tag?.let { tag ->
                updatePreferences.setIgnoredTag(tag)
            }
        }
        _uiState.value = _uiState.value.copy(
            downloadedApkPath = null,
            showAppUpdateDialog = false,
            isAppUpdateAvailable = false,
            toastMessage = "Installing update...",
            toastType = ToastType.INFO
        )
    }

    fun openUnknownSourcesSettings() {
        ApkInstaller.buildUnknownSourcesSettingsIntent(context)?.let { intent ->
            context.startActivity(intent)
        }
    }
    
    // ========== Trakt Authentication ==========
    
    fun startTraktAuth() {
        viewModelScope.launch {
            try {
                val deviceCode = traktRepository.getDeviceCode()
                _uiState.value = _uiState.value.copy(
                    traktCode = deviceCode,
                    isTraktPolling = true
                )
                
                // Start polling for token
                startTraktPolling(deviceCode)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    traktCode = null,
                    isTraktPolling = false
                )
            }
        }
    }
    
    private fun startTraktPolling(deviceCode: TraktDeviceCode) {
        traktPollingJob?.cancel()
        traktPollingJob = viewModelScope.launch {
            val expiresAt = System.currentTimeMillis() + (deviceCode.expiresIn * 1000)
            
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
                        // Stop on actual error (401, 500, etc.)
                        break
                    }
                    // 400 = pending, continue polling
                }
            }
            
            // Expired or failed
            _uiState.value = _uiState.value.copy(
                traktCode = null,
                isTraktPolling = false
            )
        }
    }
    
    fun cancelTraktAuth() {
        traktPollingJob?.cancel()
        _uiState.value = _uiState.value.copy(
            traktCode = null,
            isTraktPolling = false
        )
    }
    
    fun disconnectTrakt() {
        viewModelScope.launch {
            traktRepository.logout()
            _uiState.value = _uiState.value.copy(
                isTraktAuthenticated = false,
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
    }
}