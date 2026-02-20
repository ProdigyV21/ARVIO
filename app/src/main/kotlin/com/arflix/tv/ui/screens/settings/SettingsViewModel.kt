package com.arflix.tv.ui.screens.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.api.TraktDeviceCode
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.TvDeviceAuthRepository
import com.arflix.tv.data.repository.TvDeviceAuthStatusType
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.TraktSyncService
import com.arflix.tv.data.repository.SyncProgress
import com.arflix.tv.data.repository.SyncStatus
import com.arflix.tv.data.repository.SyncResult
import com.arflix.tv.ui.components.CARD_LAYOUT_MODE_LANDSCAPE
import com.arflix.tv.ui.components.normalizeCardLayoutMode
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
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
    // Catalogs
    val catalogs: List<CatalogConfig> = emptyList(),
    // Addons
    val addons: List<Addon> = emptyList(),
    val torrServerBaseUrl: String = "",
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
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val tvDeviceAuthRepository: TvDeviceAuthRepository,
    private val traktSyncService: TraktSyncService
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private fun defaultSubtitleKey() = profileManager.profileStringKey("default_subtitle")
    private fun defaultAudioLanguageKey() = profileManager.profileStringKey("default_audio_language")
    private fun subtitleUsageKey() = profileManager.profileStringKey("subtitle_usage_v1")
    private fun cardLayoutModeKey() = stringPreferencesKey("card_layout_mode")
    private fun frameRateMatchingModeKey() = profileManager.profileStringKey("frame_rate_matching_mode")
    private fun autoPlayNextKey() = profileManager.profileBooleanKey("auto_play_next")
    private fun includeSpecialsKey() = profileManager.profileBooleanKey("include_specials")
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
    }

    private fun loadSettings() {
        viewModelScope.launch {
            // Load local preferences first
            val prefs = context.settingsDataStore.data.first()
            var defaultSub = prefs[defaultSubtitleKey()] ?: "Off"
            val defaultAudio = prefs[defaultAudioLanguageKey()] ?: "Auto (Original)"
            val cardLayoutMode = normalizeCardLayoutMode(prefs[cardLayoutModeKey()])
            val frameRateMode = normalizeFrameRateMode(prefs[frameRateMatchingModeKey()])
            var autoPlay = prefs[autoPlayNextKey()] ?: true
            val includeSpecials = prefs[includeSpecialsKey()] ?: false

            // Try to load from cloud profile (takes priority if user is logged in)
            val cloudSubtitle = authRepository.getDefaultSubtitleFromProfile()
            val cloudAutoPlay = authRepository.getAutoPlayNextFromProfile()

            // Use cloud values if available, sync to local
            if (cloudSubtitle != null && cloudSubtitle != defaultSub) {
                defaultSub = cloudSubtitle
                context.settingsDataStore.edit { it[defaultSubtitleKey()] = defaultSub }
            }
            if (cloudAutoPlay != null && cloudAutoPlay != autoPlay) {
                autoPlay = cloudAutoPlay
                context.settingsDataStore.edit { it[autoPlayNextKey()] = autoPlay }
            }

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

            _uiState.value = SettingsUiState(
                defaultSubtitle = defaultSub,
                subtitleOptions = subtitleOptions,
                defaultAudioLanguage = defaultAudio,
                audioLanguageOptions = audioLanguageOptions,
                cardLayoutMode = cardLayoutMode,
                frameRateMatchingMode = frameRateMode,
                autoPlayNext = autoPlay,
                includeSpecials = includeSpecials,
                isLoggedIn = isLoggedIn,
                accountEmail = accountEmail,
                isTraktAuthenticated = isTrakt,
                traktExpiration = traktExpiration,
                iptvM3uUrl = iptvConfig.m3uUrl,
                iptvEpgUrl = iptvConfig.epgUrl,
                catalogs = existingCatalogs,
                addons = addons
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
            if (current.isNotBlank()) add(current)
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
            syncLocalStateToCloud(silent = true)
        }
    }
    
    fun addCustomAddon(url: String) {
        viewModelScope.launch {
            val result = streamRepository.addCustomAddon(url)
            result.onSuccess { addon ->
                val currentAddons = _uiState.value.addons.toMutableList()
                currentAddons.removeAll { it.id == addon.id }
                currentAddons.add(addon)
                _uiState.value = _uiState.value.copy(
                    addons = currentAddons,
                    toastMessage = "Added ${addon.name}",
                    toastType = ToastType.SUCCESS
                )
                syncLocalStateToCloud(silent = true)
            }.onFailure { _ ->
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to add addon",
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
            refreshIptv(showToast = true, configured = true, force = false)
            syncLocalStateToCloud(silent = true)
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
        if (_uiState.value.iptvM3uUrl.isBlank()) return
        if (iptvLoadJob?.isActive == true) return

        iptvLoadJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isIptvLoading = true, iptvError = null)
            runCatching {
                val snapshot = iptvRepository.loadSnapshot(
                    forcePlaylistReload = force,
                    forceEpgReload = force
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
            job.invokeOnCompletion { iptvLoadJob = null }
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
        // If we already have an active pairing session, keep it and just show the fallback modal.
        if (!cloudDeviceCode.isNullOrBlank() && !cloudUserCode.isNullOrBlank()) {
            _uiState.value = _uiState.value.copy(
                showCloudPairDialog = false,
                showCloudEmailPasswordDialog = true
            )
            return
        }
        startCloudAuth()
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

    private suspend fun buildCloudSnapshotJson(): String {
        val prefs = context.settingsDataStore.data.first()
        val root = JSONObject()
        root.put("version", 1)
        root.put("updatedAt", System.currentTimeMillis())
        root.put("defaultSubtitle", prefs[defaultSubtitleKey()] ?: _uiState.value.defaultSubtitle)
        root.put("defaultAudioLanguage", prefs[defaultAudioLanguageKey()] ?: _uiState.value.defaultAudioLanguage)
        root.put("cardLayoutMode", normalizeCardLayoutMode(prefs[cardLayoutModeKey()] ?: _uiState.value.cardLayoutMode))
        root.put("frameRateMatchingMode", prefs[frameRateMatchingModeKey()] ?: _uiState.value.frameRateMatchingMode)
        root.put("autoPlayNext", prefs[autoPlayNextKey()] ?: _uiState.value.autoPlayNext)
        root.put("includeSpecials", prefs[includeSpecialsKey()] ?: _uiState.value.includeSpecials)
        root.put("activeProfileId", profileRepository.getActiveProfileId() ?: JSONObject.NULL)
        root.put("profiles", JSONArray(gson.toJson(profileRepository.getProfiles())))
        // Trakt tokens are profile-scoped. Store a map keyed by profileId.
        val profiles = profileRepository.getProfiles()
        val traktTokens = traktRepository.exportTokensForProfiles(profiles.map { it.id })
        root.put("traktTokens", JSONObject(gson.toJson(traktTokens)))
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
        root.put("traktLinked", _uiState.value.isTraktAuthenticated)
        root.put("traktExpiration", _uiState.value.traktExpiration ?: JSONObject.NULL)
        return root.toString()
    }

    fun syncLocalStateToCloud(silent: Boolean = false, force: Boolean = false) {
        if (!force && !_uiState.value.isLoggedIn) return
        if (authRepository.getCurrentUserId().isNullOrBlank()) return
        viewModelScope.launch {
            val payload = runCatching { buildCloudSnapshotJson() }.getOrNull() ?: return@launch
            val result = authRepository.saveAccountSyncPayload(payload)
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
        val payloadResult = authRepository.loadAccountSyncPayload()
        if (payloadResult.isFailure) {
            if (!silent) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = payloadResult.exceptionOrNull()?.message ?: "Cloud restore failed",
                    toastType = ToastType.ERROR
                )
            }
            return CloudRestoreResult.FAILED
        }

        val payload = payloadResult.getOrNull().orEmpty()
        if (payload.isBlank()) {
            if (!silent) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "No cloud backup found",
                    toastType = ToastType.INFO
                )
            }
            return CloudRestoreResult.NO_BACKUP
        }

        return runCatching {
            val root = JSONObject(payload)
            val defaultSubtitle = root.optString("defaultSubtitle", _uiState.value.defaultSubtitle)
            val defaultAudioLanguage = root.optString("defaultAudioLanguage", _uiState.value.defaultAudioLanguage)
            val cardLayoutMode = normalizeCardLayoutMode(root.optString("cardLayoutMode", _uiState.value.cardLayoutMode))
            val frameRateMatchingMode = normalizeFrameRateMode(root.optString("frameRateMatchingMode", _uiState.value.frameRateMatchingMode))
            val autoPlayNext = root.optBoolean("autoPlayNext", _uiState.value.autoPlayNext)
            val includeSpecials = root.optBoolean("includeSpecials", _uiState.value.includeSpecials)
            context.settingsDataStore.edit { prefs ->
                prefs[defaultSubtitleKey()] = defaultSubtitle
                prefs[defaultAudioLanguageKey()] = defaultAudioLanguage
                prefs[cardLayoutModeKey()] = cardLayoutMode
                prefs[frameRateMatchingModeKey()] = frameRateMatchingMode
                prefs[autoPlayNextKey()] = autoPlayNext
                prefs[includeSpecialsKey()] = includeSpecials
            }
            authRepository.saveDefaultSubtitleToProfile(defaultSubtitle)
            authRepository.saveAutoPlayNextToProfile(autoPlayNext)

            root.optJSONArray("profiles")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = object : TypeToken<List<Profile>>() {}.type
                val profiles: List<Profile> = gson.fromJson(json, type) ?: emptyList()
                val activeProfileId = root.optString("activeProfileId").ifBlank { null }
                if (profiles.isNotEmpty()) {
                    profileRepository.replaceProfilesFromCloud(profiles, activeProfileId)
                }
            }
            // Import Trakt tokens AFTER profiles are restored.
            root.optJSONObject("traktTokens")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = object : TypeToken<Map<String, TraktRepository.CloudTraktToken>>() {}.type
                val tokens: Map<String, TraktRepository.CloudTraktToken> = gson.fromJson(json, type) ?: emptyMap()
                traktRepository.importTokensForProfiles(tokens)
            }
            root.optJSONArray("addons")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = object : TypeToken<List<Addon>>() {}.type
                val addons: List<Addon> = gson.fromJson(json, type) ?: emptyList()
                if (addons.isNotEmpty()) {
                    streamRepository.replaceAddonsFromCloud(addons)
                }
            }
            root.optJSONArray("catalogs")?.toString()?.takeIf { it.isNotBlank() }?.let { json ->
                val type = object : TypeToken<List<CatalogConfig>>() {}.type
                val catalogs: List<CatalogConfig> = gson.fromJson(json, type) ?: emptyList()
                if (catalogs.isNotEmpty()) {
                    catalogRepository.replaceCatalogsForActiveProfile(catalogs)
                }
            }
            root.optJSONArray("hiddenPreinstalledCatalogs")?.toString()?.let { json ->
                val hidden = if (json.isBlank()) {
                    emptyList()
                } else {
                    val type = object : TypeToken<List<String>>() {}.type
                    gson.fromJson<List<String>>(json, type) ?: emptyList()
                }
                catalogRepository.setHiddenPreinstalledCatalogIdsForActiveProfile(hidden)
            }
            val cloudHasIptvKeys = root.has("iptvM3uUrl") || root.has("iptvEpgUrl") ||
                root.has("iptvFavoriteGroups") || root.has("iptvFavoriteChannels")
            val m3u = root.optString("iptvM3uUrl")
            val epg = root.optString("iptvEpgUrl")
            val favorites = root.optJSONArray("iptvFavoriteGroups")?.toString().orEmpty().let { json ->
                if (json.isBlank()) emptyList() else {
                    val type = object : TypeToken<List<String>>() {}.type
                    gson.fromJson<List<String>>(json, type) ?: emptyList()
                }
            }
            val favoriteChannels = root.optJSONArray("iptvFavoriteChannels")?.toString().orEmpty().let { json ->
                if (json.isBlank()) emptyList() else {
                    val type = object : TypeToken<List<String>>() {}.type
                    gson.fromJson<List<String>>(json, type) ?: emptyList()
                }
            }
            val localIptv = iptvRepository.observeConfig().first()
            val cloudHasIptvData = m3u.isNotBlank() || epg.isNotBlank() || favorites.isNotEmpty() || favoriteChannels.isNotEmpty()
            val localHasIptvData = localIptv.m3uUrl.isNotBlank() || localIptv.epgUrl.isNotBlank()
            // Prevent accidental IPTV wipe when cloud payload has empty/default IPTV fields.
            if (cloudHasIptvKeys && (cloudHasIptvData || !localHasIptvData)) {
                iptvRepository.importCloudConfig(m3u, epg, favorites, favoriteChannels)
                if (m3u.isNotBlank()) {
                    refreshIptv(showToast = false, configured = false, force = false)
                }
            }
        }.onSuccess {
            loadSettings()
            if (!silent) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Cloud restore complete",
                    toastType = ToastType.SUCCESS
                )
            }
        }.onFailure { error ->
            if (!silent) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = error.message ?: "Cloud restore failed",
                    toastType = ToastType.ERROR
                )
            }
        }.fold(
            onSuccess = { CloudRestoreResult.RESTORED },
            onFailure = { CloudRestoreResult.FAILED }
        )
    }

    fun onCloudProfileSwitchHandled() {
        if (_uiState.value.shouldSwitchProfile) {
            _uiState.value = _uiState.value.copy(shouldSwitchProfile = false)
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
                    performFullSync(silent = true)
                    syncLocalStateToCloud(silent = true, force = true)
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




