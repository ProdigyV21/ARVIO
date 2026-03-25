package com.arflix.tv.ui.screens.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvSnapshot
import com.arflix.tv.data.repository.CloudSyncRepository
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
    val isConfigured: Boolean get() = config.m3uUrl.isNotBlank() || config.stalkerPortalUrl.isNotBlank()

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
        val hiddenSet = snapshot.hiddenGroups.toHashSet()
        val visibleGroups = dynamicGroups.filterNot { hiddenSet.contains(it) }
        val favorites = snapshot.favoriteGroups.filter { visibleGroups.contains(it) }
        val others = visibleGroups.filterNot { snapshot.favoriteGroups.contains(it) }
        val baseOrdered = if (snapshot.groupOrder.isNotEmpty()) {
            val orderMap = snapshot.groupOrder.withIndex().associate { (i, g) -> g to i }
            (favorites + others).sortedBy { orderMap[it] ?: Int.MAX_VALUE }
        } else { favorites + others }
        val hasFavoriteChannelsInSnapshot = snapshot.favoriteChannels
            .toHashSet()
            .let { ids -> snapshot.channels.any { ids.contains(it.id) } }
        return if (hasFavoriteChannelsInSnapshot) {
            listOf(FAVORITES_GROUP_NAME) + baseOrdered
        } else {
            baseOrdered
        }
    }
}

@HiltViewModel
class TvViewModel @Inject constructor(
    val iptvRepository: IptvRepository,
    private val cloudSyncRepository: CloudSyncRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TvUiState(isLoading = true))
    val uiState: StateFlow<TvUiState> = _uiState.asStateFlow()
    private var refreshJob: Job? = null
    private var epgRefreshJob: Job? = null
    private var warmVodJob: Job? = null
    private var pendingForcedReload: Boolean = false

    init {
        observeConfigAndFavorites()
        viewModelScope.launch {
            // Try fast non-blocking in-memory read first; fall back to mutex-guarded disk read
            val cached = iptvRepository.getMemoryCachedSnapshot()
                ?: iptvRepository.getCachedSnapshotOrNull()
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
                val hasPotentialEpg = config.epgUrl.isNotBlank() || config.m3uUrl.contains("get.php", ignoreCase = true) || config.m3uUrl.contains("player_api.php", ignoreCase = true)
                val needsChannelReload = config.m3uUrl.isNotBlank() && cached.channels.isEmpty()
                // Only force EPG refresh if cached EPG is older than 2 minutes.
                // When navigating from Home, EPG was likely just loaded — no need to re-fetch.
                val epgAgeMs = iptvRepository.cachedEpgAgeMs()
                val epgIsRecent = epgAgeMs < 120_000L
                if (hasPotentialEpg && cached.channels.isNotEmpty() && !epgIsRecent) {
                    System.err.println("[EPG] Startup: forcing EPG refresh (cached EPG age=${epgAgeMs / 1000}s, hasEpg=${hasAnyEpgData(cached)})")
                    maybeRefreshEpgInBackground(cached, forceRefresh = true)
                } else if (hasPotentialEpg && cached.channels.isNotEmpty()) {
                    System.err.println("[EPG] Startup: skipping EPG refresh (EPG is recent, age=${epgAgeMs / 1000}s)")
                }
                if (iptvRepository.isSnapshotStale(cached) || needsChannelReload) {
                    refresh(force = needsChannelReload, showLoading = needsChannelReload)
                }
            } else {
                refresh(force = false, showLoading = true)
            }
        }
    }

    private fun observeConfigAndFavorites() {
        viewModelScope.launch {
            combine(
                combine(iptvRepository.observeConfig(), iptvRepository.observeFavoriteGroups(), iptvRepository.observeFavoriteChannels()) { a, b, c -> Triple(a, b, c) },
                iptvRepository.observeHiddenGroups(),
                iptvRepository.observeGroupOrder()
            ) { triple, hiddenGroups, groupOrder ->
                Triple(triple, hiddenGroups, groupOrder)
            }.collect { (triple, hiddenGroups, groupOrder) ->
                val (config, favoriteGroups, favoriteChannels) = triple
                val snapshot = _uiState.value.snapshot.copy(
                    favoriteGroups = favoriteGroups,
                    favoriteChannels = favoriteChannels,
                    hiddenGroups = hiddenGroups,
                    groupOrder = groupOrder
                )
                _uiState.value = _uiState.value.copy(config = config, snapshot = snapshot)

                // Auto-heal cases where the app has IPTV config but an empty in-memory snapshot.
                if (config.m3uUrl.isNotBlank() && snapshot.channels.isEmpty() && refreshJob?.isActive != true) {
                    refresh(force = true, showLoading = true)
                }
            }
        }
    }

    fun refresh(force: Boolean, showLoading: Boolean = true) {
        if (refreshJob?.isActive == true) return
        if (force) {
            epgRefreshJob?.cancel()
        }

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
                    // Keep TV startup responsive: load channels first, fetch EPG separately.
                    forceEpgReload = false
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
                    loadingMessage = null,
                    loadingPercent = 0
                )
                warmXtreamVodCache()
                maybeRefreshEpgInBackground(snapshot)
                if (!force && _uiState.value.isConfigured && snapshot.channels.isEmpty()) {
                    // Soft refresh returned empty even though IPTV is configured:
                    // schedule one forced reload to bypass stale in-memory paths.
                    pendingForcedReload = true
                }
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = error.message ?: "Failed to load IPTV",
                    loadingMessage = null,
                    loadingPercent = 0
                )
            }
        }.also { job ->
            job.invokeOnCompletion {
                refreshJob = null
                if (pendingForcedReload) {
                    pendingForcedReload = false
                    refresh(force = true, showLoading = true)
                }
            }
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

    private fun maybeRefreshEpgInBackground(snapshot: IptvSnapshot, forceRefresh: Boolean = false) {
        val config = _uiState.value.config
        val hasPotentialEpg = config.epgUrl.isNotBlank() || config.m3uUrl.contains("get.php", ignoreCase = true) || config.m3uUrl.contains("player_api.php", ignoreCase = true)
        if (!hasPotentialEpg) return
        if (!forceRefresh && (snapshot.channels.isEmpty() || hasAnyEpgData(snapshot))) return
        if (epgRefreshJob?.isActive == true) return

        epgRefreshJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                loadingMessage = "Loading EPG in background...",
                loadingPercent = 90
            )
            runCatching {
                iptvRepository.loadSnapshot(
                    forcePlaylistReload = false,
                    forceEpgReload = true
                ) { progress ->
                    _uiState.value = _uiState.value.copy(
                        loadingMessage = progress.message,
                        loadingPercent = progress.percent ?: _uiState.value.loadingPercent
                    )
                }
            }.onSuccess { refreshed ->
                val lookup = withContext(Dispatchers.Default) {
                    refreshed.channels.associateBy { it.id }
                }
                _uiState.value = _uiState.value.copy(
                    error = null,
                    snapshot = refreshed,
                    channelLookup = lookup,
                    loadingMessage = null,
                    loadingPercent = 0
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    loadingMessage = null,
                    loadingPercent = 0
                )
            }
        }.also { job ->
            job.invokeOnCompletion {
                if (epgRefreshJob === job) {
                    epgRefreshJob = null
                }
            }
        }
    }

    private fun hasAnyEpgData(snapshot: IptvSnapshot): Boolean {
        if (snapshot.nowNext.isEmpty()) return false
        return snapshot.nowNext.values.any { item ->
            item.now != null || item.next != null || item.later != null || item.upcoming.isNotEmpty()
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

    fun toggleHiddenGroup(groupName: String) {
        viewModelScope.launch { iptvRepository.toggleHiddenGroup(groupName) }
    }

    fun moveGroupUp(groupName: String) {
        viewModelScope.launch {
            val current = _uiState.value.groups().filterNot { it == FAVORITES_GROUP_NAME }
            iptvRepository.moveGroupUp(groupName, current)
        }
    }

    fun moveGroupDown(groupName: String) {
        viewModelScope.launch {
            val current = _uiState.value.groups().filterNot { it == FAVORITES_GROUP_NAME }
            iptvRepository.moveGroupDown(groupName, current)
        }
    }

    private suspend fun syncIptvFavoritesToCloud() {
        runCatching { cloudSyncRepository.pushToCloud() }
    }
}
