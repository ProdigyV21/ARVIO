package com.arflix.tv.ui.screens.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.BuildConfig
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.screens.player.SubtitleAiModel
import com.arflix.tv.updater.UpdateStatus
import com.arflix.tv.updater.UpdateStatusManager
import com.arflix.tv.util.ACCENT_COLOR_KEY
import com.arflix.tv.util.LAST_APP_LANGUAGE_KEY
import com.arflix.tv.util.OLED_BLACK_BACKGROUND_KEY
import com.arflix.tv.util.settingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val autoPlayNext: Boolean = true,
    val autoPlaySingleSource: Boolean = true,
    val frameRateMatchingMode: String = "Off",
    val volumeBoostDb: Int = 0,
    val defaultSubtitle: String = "Off",
    val secondarySubtitle: String = "Off",
    val subtitleAiEnabled: Boolean = false,
    val subtitleAiModel: SubtitleAiModel = SubtitleAiModel.GROQ_LLAMA_70B,
    val addons: List<Addon> = emptyList(),
    val catalogs: List<CatalogConfig> = emptyList(),
    val accentColor: String = "White",
    val oledBlackBackground: Boolean = false,
    val clockFormat: String = "24h",
    val contentLanguage: String = "sv",
    val dnsProvider: String = "System DNS",
    val customUserAgent: String = "",
    val isSelfUpdateSupported: Boolean = BuildConfig.SELF_UPDATE_ENABLED,
    val updateStatus: UpdateStatus = UpdateStatus.Idle
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val streamRepository: StreamRepository,
    private val catalogRepository: CatalogRepository,
    private val updateStatusManager: UpdateStatusManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        observeLocalSettings()
        observeAddons()
        observeCatalogs()
        observeUpdates()
    }

    private fun observeLocalSettings() {
        viewModelScope.launch {
            val prefs = context.settingsDataStore.data.first()
            val profileId = profileManager.getProfileIdSync().ifBlank { "default" }
            fun profileString(name: String) = stringPreferencesKey("profile_${profileId}_$name")
            fun profileBoolean(name: String) = booleanPreferencesKey("profile_${profileId}_$name")

            val dnsProvider = OkHttpProvider.parseDnsProvider(
                prefs[stringPreferencesKey(OkHttpProvider.DNS_PROVIDER_PREF_KEY)]
            )
            val subtitleAiModel = runCatching {
                SubtitleAiModel.valueOf(
                    prefs[stringPreferencesKey("subtitle_ai_model")] ?: SubtitleAiModel.GROQ_LLAMA_70B.name
                )
            }.getOrDefault(SubtitleAiModel.GROQ_LLAMA_70B)

            _uiState.value = _uiState.value.copy(
                autoPlayNext = prefs[profileBoolean("auto_play_next")] ?: true,
                autoPlaySingleSource = prefs[profileBoolean("auto_play_single_source")] ?: true,
                frameRateMatchingMode = prefs[profileString("frame_rate_matching_mode")] ?: "Off",
                volumeBoostDb = prefs[profileString("volume_boost_db")]?.toIntOrNull()?.coerceIn(0, 15) ?: 0,
                defaultSubtitle = prefs[profileString("default_subtitle")] ?: "Off",
                secondarySubtitle = prefs[profileString("secondary_subtitle")] ?: "Off",
                subtitleAiEnabled = prefs[booleanPreferencesKey("subtitle_ai_enabled")] ?: false,
                subtitleAiModel = subtitleAiModel,
                accentColor = prefs[ACCENT_COLOR_KEY] ?: "White",
                oledBlackBackground = prefs[OLED_BLACK_BACKGROUND_KEY] ?: false,
                clockFormat = prefs[profileString("clock_format")] ?: "24h",
                contentLanguage = prefs[LAST_APP_LANGUAGE_KEY] ?: "sv",
                dnsProvider = dnsProviderLabel(dnsProvider),
                customUserAgent = prefs[stringPreferencesKey(OkHttpProvider.USER_AGENT_PREF_KEY)].orEmpty()
            )
        }
    }

    private fun observeAddons() {
        viewModelScope.launch {
            streamRepository.installedAddons.collect { addons ->
                _uiState.value = _uiState.value.copy(addons = addons)
            }
        }
    }

    private fun observeCatalogs() {
        viewModelScope.launch {
            catalogRepository.observeCatalogs().collect { catalogs ->
                _uiState.value = _uiState.value.copy(catalogs = catalogs)
            }
        }
    }

    private fun observeUpdates() {
        viewModelScope.launch {
            updateStatusManager.status.collect { status ->
                _uiState.value = _uiState.value.copy(updateStatus = status)
            }
        }
    }

    private fun dnsProviderLabel(value: OkHttpProvider.AppDnsProvider): String = when (value) {
        OkHttpProvider.AppDnsProvider.CLOUDFLARE -> "Cloudflare"
        OkHttpProvider.AppDnsProvider.GOOGLE -> "Google"
        OkHttpProvider.AppDnsProvider.ADGUARD -> "AdGuard"
        else -> "System DNS"
    }
}
