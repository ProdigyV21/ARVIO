package com.arflix.tv.ui.screens.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvSnapshot
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.IptvConfig
import com.arflix.tv.data.repository.IptvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

private const val FAVORITES_GROUP_NAME = "My Favorites"

data class TvUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val loadingMessage: String? = null,
    val loadingPercent: Int = 0,
    val config: IptvConfig = IptvConfig(),
    val snapshot: IptvSnapshot = IptvSnapshot(),
    val channelLookup: Map<String, IptvChannel> = emptyMap(),
    val favoritesOnly: Boolean = false,
    val query: String = ""
) {
    val isConfigured: Boolean get() = config.m3uUrl.isNotBlank()

    fun filteredChannels(group: String): List<IptvChannel> {
        val source = if (group == FAVORITES_GROUP_NAME) {
            val favorites = snapshot.favoriteChannels.toHashSet()
            if (favorites.isEmpty()) emptyList() else snapshot.channels.filter { favorites.contains(it.id) }
        } else {
            snapshot.grouped[group].orEmpty()
        }

        val trimmed = query.trim().lowercase()
        if (trimmed.isBlank()) return source

        return source.mapNotNull { channel ->
            val name = channel.name.lowercase()
            val groupName = channel.group.lowercase()
            val score = when {
                name.startsWith(trimmed) -> 100
                name.contains(trimmed) -> 80
                groupName.startsWith(trimmed) -> 60
                groupName.contains(trimmed) -> 45
                else -> 0
            }
            if (score > 0) channel to score else null
        }
            .sortedByDescending { it.second }
            .map { it.first }
    }

    fun groups(): List<String> {
        val dynamicGroups = snapshot.grouped.keys.toList()
        val favorites = snapshot.favoriteGroups.filter { dynamicGroups.contains(it) }
        val others = dynamicGroups.filterNot { snapshot.favoriteGroups.contains(it) }
        val ordered = favorites + others
        return if (snapshot.favoriteChannels.isNotEmpty()) {
            listOf(FAVORITES_GROUP_NAME) + ordered
        } else {
            ordered
        }
    }
}

@HiltViewModel
class TvViewModel @Inject constructor(
    private val iptvRepository: IptvRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TvUiState(isLoading = true))
    val uiState: StateFlow<TvUiState> = _uiState.asStateFlow()
    private var refreshJob: Job? = null
    private var warmVodJob: Job? = null

    init {
        observeConfigAndFavorites()
        viewModelScope.launch {
            val cached = iptvRepository.getCachedSnapshotOrNull()
            if (cached != null) {
                val config = iptvRepository.observeConfig().first()
                val lookup = withContext(Dispatchers.Default) {
                    cached.channels.associateBy { it.id }
                }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = null,
                    snapshot = cached,
                    channelLookup = lookup,
                    loadingMessage = null,
                    loadingPercent = 0
                )
                warmXtreamVodCache()
                val hasPotentialEpg = config.epgUrl.isNotBlank() || config.m3uUrl.contains("get.php", ignoreCase = true)
                val needsEpgRetry = hasPotentialEpg && cached.channels.isNotEmpty() && cached.nowNext.isEmpty()
                if (iptvRepository.isSnapshotStale(cached) || needsEpgRetry) {
                    refresh(force = false, showLoading = false)
                }
            } else {
                refresh(force = false, showLoading = true)
            }
        }
    }

    private fun observeConfigAndFavorites() {
        viewModelScope.launch {
            combine(
                iptvRepository.observeConfig(),
                iptvRepository.observeFavoriteGroups(),
                iptvRepository.observeFavoriteChannels()
            ) { config, favoriteGroups, favoriteChannels ->
                Triple(config, favoriteGroups, favoriteChannels)
            }.collect { (config, favoriteGroups, favoriteChannels) ->
                val snapshot = _uiState.value.snapshot.copy(
                    favoriteGroups = favoriteGroups,
                    favoriteChannels = favoriteChannels
                )
                _uiState.value = _uiState.value.copy(config = config, snapshot = snapshot)
            }
        }
    }

    fun refresh(force: Boolean, showLoading: Boolean = true) {
        if (refreshJob?.isActive == true) return

        refreshJob = viewModelScope.launch {
            if (showLoading) {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    error = null,
                    loadingMessage = "Starting IPTV load...",
                    loadingPercent = 2
                )
            }
            runCatching {
                iptvRepository.loadSnapshot(
                    forcePlaylistReload = force,
                    forceEpgReload = force
                ) { progress ->
                    if (showLoading) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            loadingMessage = progress.message,
                            loadingPercent = progress.percent ?: _uiState.value.loadingPercent
                        )
                    }
                }
            }.onSuccess { snapshot ->
                val lookup = withContext(Dispatchers.Default) {
                    snapshot.channels.associateBy { it.id }
                }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = null,
                    snapshot = snapshot,
                    channelLookup = lookup,
                    loadingMessage = "Done",
                    loadingPercent = 100
                )
                warmXtreamVodCache()
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = error.message ?: "Failed to load IPTV",
                    loadingMessage = null,
                    loadingPercent = 0
                )
            }
        }.also { job ->
            job.invokeOnCompletion { refreshJob = null }
        }
    }

    private fun warmXtreamVodCache() {
        if (warmVodJob?.isActive == true) return
        warmVodJob = viewModelScope.launch(Dispatchers.IO) {
            runCatching { iptvRepository.warmXtreamVodCachesIfPossible() }
        }.also { job ->
            job.invokeOnCompletion { warmVodJob = null }
        }
    }

    fun setQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun toggleFavoriteGroup(groupName: String) {
        viewModelScope.launch {
            iptvRepository.toggleFavoriteGroup(groupName)
            syncIptvFavoritesToCloud()
        }
    }

    fun toggleFavoriteChannel(channelId: String) {
        viewModelScope.launch {
            iptvRepository.toggleFavoriteChannel(channelId)
            syncIptvFavoritesToCloud()
        }
    }

    private suspend fun syncIptvFavoritesToCloud() {
        if (authRepository.getCurrentUserId().isNullOrBlank()) return
        runCatching {
            val config = iptvRepository.observeConfig().first()
            val favoriteGroups = iptvRepository.observeFavoriteGroups().first()
            val favoriteChannels = iptvRepository.observeFavoriteChannels().first()
            val existingPayload = authRepository.loadAccountSyncPayload().getOrNull().orEmpty()
            val root = if (existingPayload.isNotBlank()) JSONObject(existingPayload) else JSONObject().apply {
                put("version", 1)
            }
            root.put("updatedAt", System.currentTimeMillis())
            root.put("iptvM3uUrl", config.m3uUrl)
            root.put("iptvEpgUrl", config.epgUrl)
            root.put("iptvFavoriteGroups", JSONArray(favoriteGroups))
            root.put("iptvFavoriteChannels", JSONArray(favoriteChannels))
            authRepository.saveAccountSyncPayload(root.toString())
        }
    }
}
