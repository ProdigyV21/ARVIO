package com.arflix.tv.ui.screens.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvSnapshot
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.IptvConfig
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.IptvTvSessionState
import com.arflix.tv.util.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


private object TvViewModelRegexes {
    val NON_ALPHANUMERIC_REGEX = Regex("""[^a-z0-9]+""")
}

internal const val FAVORITES_GROUP_NAME = "My Favorites"
private const val EpgLoadingStateLimit = 800
private const val EpgAttemptedStateLimit = 2_400
private const val LargeIptvListChannelCount = 10_000
private const val StandardPriorityEpgLimit = 3_200
private const val LargeListPriorityCacheLimit = 360
private const val RichCatchupRecentTarget = 6
private const val RichCatchupRefreshThrottleMs = 45_000L
private const val CurrentChannelEpgRefreshThrottleMs = 12_000L
private const val LargeListCompleteGuideCoverageTarget = 0.75f

data class TvUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val loadingMessage: String? = null,
    val loadingPercent: Int = 0,
    val config: IptvConfig = IptvConfig(),
    val snapshot: IptvSnapshot = IptvSnapshot(),
    val channelLookup: Map<String, IptvChannel> = emptyMap(),
    val groups: List<String> = emptyList(),
    val channelsByGroup: Map<String, List<IptvChannel>> = emptyMap(),
    val tvSession: IptvTvSessionState = IptvTvSessionState(),
    val iptvPreferencesLoaded: Boolean = false,
    val tvSessionLoaded: Boolean = false,
    val favoritesOnly: Boolean = false,
    val query: String = "",
    val epgLoadingChannelIds: Set<String> = emptySet(),
    val epgAttemptedChannelIds: Set<String> = emptySet(),
    val epgBackfillInProgress: Boolean = false,
) {
    val isConfigured: Boolean get() =
        config.m3uUrl.isNotBlank() ||
            config.stalkerPortalUrl.isNotBlank() ||
            config.playlists.any { it.enabled && it.m3uUrl.isNotBlank() }

    val hasPotentialGuideSource: Boolean get() = config.hasConfiguredEpgSource()
}

@HiltViewModel
class TvViewModel @Inject constructor(
    val iptvRepository: IptvRepository,
    private val cloudSyncRepository: CloudSyncRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TvUiState())
    val uiState: StateFlow<TvUiState> = _uiState.asStateFlow()
    private var refreshJob: Job? = null
    private var epgRefreshJob: Job? = null
    private var warmVodJob: Job? = null
    private var pendingForcedReload: Boolean = false
    private var periodicEpgJob: Job? = null
    private var iptvCloudSyncJob: Job? = null
    private var lastObservedConfigSignature: String? = null
    private var lastAutomaticEpgReloadAt: Long = 0L
    private var visibleEpgRefreshJob: Job? = null
    private var lastVisibleEpgRefreshKey: String? = null
    private var lastVisibleEpgRefreshAt: Long = 0L
    private val visibleEpgQueueLock = Any()
    private val pendingVisibleEpgChannelIds = LinkedHashSet<String>()
    private var pendingVisibleEpgSelectedChannelId: String? = null
    private var tvSessionSaveJob: Job? = null
    private var startupGuideWarmupKey: String? = null
    private var fullEpgWarmupJob: Job? = null
    private var lastFullEpgWarmupKey: String? = null
    private var completeEpgBackfillJob: Job? = null
    private var lastCompleteEpgBackfillKey: String? = null
    private var lastVisibleForcedCompleteEpgAt: Long = 0L
    private var lastCompleteEpgBackfillCompletedAt: Long = 0L
    private var preparedContentJob: Job? = null
    private var preparedContentRevision: Long = 0L
    private val resolvedStalkerStreamCache = LinkedHashMap<String, String>()
    private val catchupHistoryRefreshAt = LinkedHashMap<String, Long>()
    private val currentChannelEpgRefreshAt = LinkedHashMap<String, Long>()

    private data class VisibleEpgDrain(
        val ids: List<String>,
        val selectedId: String?
    )

    /**
     * In-memory cache of the live-TV enriched channel list + category tree.
     * Persists across screen visits for the lifetime of the ViewModel (which
     * Hilt scopes to the nav backstack entry). Keeps the sub-second first
     * paint when returning to the TV screen — the costly enrichment of 52k
     * channels only runs once per session.
     */
    @Volatile var cachedEnrichedChannels: Any? = null
    @Volatile var cachedChannelsSignature: String? = null

    private fun countBucket(count: Int): String = when {
        count < 100 -> "lt_100"
        count < 1_000 -> "lt_1k"
        count < 10_000 -> "lt_10k"
        count < 50_000 -> "lt_50k"
        else -> "gte_50k"
    }

    init {
        observeConfigAndFavorites()
        observeTvSession()
        viewModelScope.launch {
            runCatching { iptvRepository.warmupFromCacheOnly() }
            // Try fast non-blocking in-memory read first; fall back to mutex-guarded disk read
            val cached = iptvRepository.getMemoryCachedSnapshot()
                ?: iptvRepository.getCachedSnapshotOrNull()
            if (cached != null) {
                val config = iptvRepository.observeConfig().first()
                // The observeConfigAndFavorites() coroutine may have already read fresh
                // favorites from DataStore before this cached snapshot was loaded from disk.
                // Prefer those in-memory favorites over whatever was baked into the cache,
                // which can be stale if the user added/removed favorites since the last save.
                val liveSnapshot = _uiState.value.snapshot
                val snapshotToUse = if (liveSnapshot.favoriteChannels.isNotEmpty() || liveSnapshot.favoriteGroups.isNotEmpty()) {
                    cached.copy(
                        favoriteChannels = liveSnapshot.favoriteChannels,
                        favoriteGroups = liveSnapshot.favoriteGroups,
                        hiddenGroups = liveSnapshot.hiddenGroups,
                        groupOrder = liveSnapshot.groupOrder
                    )
                } else {
                    cached
                }
                setUiState(
                    _uiState.value.copy(
                        isLoading = false,
                        error = null,
                        snapshot = snapshotToUse,
                        loadingMessage = null,
                        loadingPercent = 0
                    )
                )
                maybeWarmStartupGuide()
                startFullEpgWarmup()
                startCompleteEpgBackfill()
                warmXtreamVodCache()
                val needsChannelReload = config.m3uUrl.isNotBlank() && cached.channels.isEmpty()
                val epgAgeMs = iptvRepository.cachedEpgAgeMs()
                if (needsChannelReload) {
                    // Only situation that still requires a blocking refresh:
                    // there are literally no channels to show.
                    refresh(force = true, showLoading = false, forceEpg = false)
                } else {
                    // In every other case render the warm cache instantly —
                    // never block the TV page on EPG. The active category
                    // will request guide data on demand once the user lands
                    // there, instead of broad startup sweeps.
                    if (iptvRepository.isSnapshotStale(cached)) {
                        refresh(force = false, showLoading = false, forceEpg = false)
                    } else {
                        System.err.println("[EPG] Startup: using warm cached EPG (age=${epgAgeMs / 1000}s)")
                    }
                }
            } else {
                refresh(force = false, showLoading = false, forceEpg = false)
            }
            startPeriodicEpgRefresh()
        }
    }

    private fun observeTvSession() {
        viewModelScope.launch {
            iptvRepository.observeTvSessionState()
                .distinctUntilChanged()
                .collect { session ->
                    _uiState.value = _uiState.value.copy(
                        tvSession = session,
                        tvSessionLoaded = true,
                    )
                    maybeWarmStartupGuide()
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
            }
                .distinctUntilChanged()
                .collect { (triple, hiddenGroups, groupOrder) ->
                val (config, favoriteGroups, favoriteChannels) = triple
                val newConfigSignature = config.syncSignature()
                val configChanged = lastObservedConfigSignature != null &&
                    lastObservedConfigSignature != newConfigSignature
                lastObservedConfigSignature = newConfigSignature
                val snapshot = _uiState.value.snapshot.copy(
                    favoriteGroups = favoriteGroups,
                    favoriteChannels = favoriteChannels,
                    hiddenGroups = hiddenGroups,
                    groupOrder = groupOrder
                )
                setUiState(
                    _uiState.value.copy(
                        config = config,
                        snapshot = snapshot,
                        iptvPreferencesLoaded = true,
                    )
                )
                maybeWarmStartupGuide()
                startFullEpgWarmup()

                val hasAnyIptvConfig = config.m3uUrl.isNotBlank() ||
                    config.stalkerPortalUrl.isNotBlank() ||
                    config.playlists.any { it.enabled && it.m3uUrl.isNotBlank() }

                // Auto-heal cases where the app has IPTV config but an empty in-memory snapshot.
                if (hasAnyIptvConfig && snapshot.channels.isEmpty() && refreshJob?.isActive != true) {
                    refresh(force = false, showLoading = false)
                } else if (configChanged && refreshJob?.isActive != true) {
                    cachedEnrichedChannels = null
                    cachedChannelsSignature = null
                    refresh(force = true, showLoading = false, forceEpg = false)
                }
            }
        }
    }

    fun refresh(force: Boolean, showLoading: Boolean = true, forceEpg: Boolean = false) {
        if (refreshJob?.isActive == true) return
        if (force) {
            epgRefreshJob?.cancel()
        }

        refreshJob = viewModelScope.launch {
            val hasExistingChannels = _uiState.value.snapshot.channels.isNotEmpty()
            if (showLoading && !hasExistingChannels) {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    error = null,
                    loadingMessage = "Starting IPTV load...",
                    loadingPercent = 2
                )
            }
            runCatching {
                kotlinx.coroutines.withTimeoutOrNull(180_000L) {
                    iptvRepository.loadSnapshot(
                        forcePlaylistReload = force,
                        forceEpgReload = forceEpg,
                        allowNetworkEpgFetch = false,
                        onProgress = { progress ->
                            if (showLoading && !hasExistingChannels) {
                                _uiState.value = _uiState.value.copy(
                                    isLoading = true,
                                    loadingMessage = progress.message,
                                    loadingPercent = progress.percent ?: _uiState.value.loadingPercent
                                )
                            }
                        },
                        onChannelsReady = { channels ->
                            // Publish channels to UI immediately — don't wait for EPG.
                            // This makes the TV page responsive even on cold start with no cache.
                            val cachedNowNext = withContext(Dispatchers.Default) {
                                iptvRepository.reDeriveCachedNowNext(
                                    channels.asSequence().map { it.id }.toSet()
                                ).orEmpty()
                            }
                            val currentSnapshot = _uiState.value.snapshot
                            // Rebuild grouped from the new channels so that all playlist
                            // groups (Norway, Sweden, Denmark …) appear immediately rather
                            // than inheriting the stale groups from the previous snapshot.
                            val freshGrouped = channels.groupBy { it.group.ifBlank { "Uncategorized" } }
                            setUiState(
                                _uiState.value.copy(
                                    isLoading = false,
                                    error = null,
                                    snapshot = currentSnapshot.copy(
                                        channels = channels,
                                        grouped = freshGrouped,
                                        nowNext = if (cachedNowNext.isNotEmpty()) {
                                            currentSnapshot.nowNext.toMutableMap().apply { putAll(cachedNowNext) }
                                        } else {
                                            currentSnapshot.nowNext
                                        }
                                    ),
                                    loadingMessage = null,
                                    loadingPercent = 0
                                )
                            )
                            startFullEpgWarmup()
                            startCompleteEpgBackfill()
                        }
                    )
                } ?: throw IllegalStateException("IPTV load timed out")
            }.onSuccess { snapshot ->
                cachedEnrichedChannels = null
                cachedChannelsSignature = null
                setUiState(
                    _uiState.value.copy(
                        isLoading = false,
                        error = null,
                        snapshot = snapshot,
                        loadingMessage = null,
                        loadingPercent = 0
                    )
                )
                maybeWarmStartupGuide()
                startFullEpgWarmup()
                startCompleteEpgBackfill()
                warmXtreamVodCache()
                if (!force && _uiState.value.isConfigured && snapshot.channels.isEmpty()) {
                    // Soft refresh returned empty even though IPTV is configured:
                    // schedule one forced reload to bypass stale in-memory paths.
                    pendingForcedReload = true
                }
            }.onFailure { error ->
                AppLogger.recordException(
                    throwable = error,
                    context = mapOf(
                        "error_area" to "IPTV",
                        "iptv_phase" to "load_snapshot",
                        "force_playlist_reload" to force.toString(),
                        "force_epg_reload" to forceEpg.toString(),
                        "had_existing_channels" to hasExistingChannels.toString()
                    )
                )
                val fallback = runCatching {
                    iptvRepository.getMemoryCachedSnapshot() ?: iptvRepository.getCachedSnapshotOrNull()
                }.getOrNull()
                if (fallback != null && fallback.channels.isNotEmpty()) {
                    setUiState(
                        _uiState.value.copy(
                            isLoading = false,
                            error = null,
                            snapshot = fallback,
                            loadingMessage = null,
                            loadingPercent = 0
                        )
                    )
                    maybeWarmStartupGuide()
                    startFullEpgWarmup()
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to load IPTV",
                        loadingMessage = null,
                        loadingPercent = 0
                    )
                }
            }
        }.also { job ->
            job.invokeOnCompletion {
                refreshJob = null
                if (pendingForcedReload) {
                    pendingForcedReload = false
                    refresh(force = true, showLoading = false, forceEpg = false)
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

    private fun hasAnyEpgData(snapshot: IptvSnapshot): Boolean {
        if (snapshot.nowNext.isEmpty()) return false
        return snapshot.nowNext.values.any { item ->
            item.now != null ||
                item.next != null ||
                item.later != null ||
                item.upcoming.isNotEmpty() ||
                item.recent.isNotEmpty()
        }
    }

    private fun hasProgramData(item: com.arflix.tv.data.model.IptvNowNext?): Boolean {
        return item != null && (
            item.now != null ||
                item.next != null ||
                item.later != null ||
                item.upcoming.isNotEmpty() ||
                item.recent.isNotEmpty()
            )
    }

    private fun hasUsefulVisibleGuideData(item: com.arflix.tv.data.model.IptvNowNext?): Boolean {
        if (!hasProgramData(item)) return false
        if (item == null) return false
        if (item.next != null || item.later != null || item.upcoming.isNotEmpty()) return true
        val live = item.now ?: return item.recent.isNotEmpty()
        return live.endUtcMillis - System.currentTimeMillis() > 45L * 60_000L
    }

    private fun hasRichSelectedGuideData(item: com.arflix.tv.data.model.IptvNowNext?): Boolean {
        if (!hasProgramData(item) || item == null) return false
        val now = System.currentTimeMillis()
        val futureCount = buildList {
            item.next?.let(::add)
            item.later?.let(::add)
            addAll(item.upcoming)
        }.distinctBy { "${it.startUtcMillis}|${it.endUtcMillis}|${it.title}" }
            .count { it.startUtcMillis > now }
        val hasLongEnoughCurrent = item.now?.let { it.endUtcMillis - now > 20L * 60_000L } == true
        return futureCount >= 6 || (hasLongEnoughCurrent && futureCount >= 3)
    }

    private suspend fun refreshGuideFromCache() {
        val state = _uiState.value
        val channelIds = buildPriorityEpgChannelIds(
            state = state,
            maxChannels = if (isLargeIptvList(state.snapshot.channels.size)) {
                LargeListPriorityCacheLimit
            } else {
                StandardPriorityEpgLimit
            }
        )
        if (channelIds.isEmpty()) return
        val updated = withContext(Dispatchers.Default) {
            iptvRepository.reDeriveCachedNowNext(channelIds)
        } ?: return
        mergeNowNext(updated)
    }

    private suspend fun refreshGuideFromCache(channelIds: Set<String>) {
        if (channelIds.isEmpty()) return
        val updated = withContext(Dispatchers.Default) {
            iptvRepository.reDeriveCachedNowNext(channelIds)
        } ?: return
        mergeNowNext(updated)
    }

    private fun mergeNowNext(updated: Map<String, com.arflix.tv.data.model.IptvNowNext>) {
        if (updated.isEmpty()) return
        val current = _uiState.value
        val updatedIds = updated.keys
        setUiState(
            current.copy(
                snapshot = current.snapshot.copy(
                    nowNext = current.snapshot.nowNext.toMutableMap().apply { putAll(updated) }
                ),
                epgLoadingChannelIds = current.epgLoadingChannelIds - updatedIds,
                epgAttemptedChannelIds = capChannelStateSet(current.epgAttemptedChannelIds + updatedIds, EpgAttemptedStateLimit),
            )
        )
    }

    private fun markEpgLoading(channelIds: Collection<String>) {
        val ids = channelIds.asSequence().filter { it.isNotBlank() }.take(EpgLoadingStateLimit).toSet()
        if (ids.isEmpty()) return
        val current = _uiState.value
        setUiState(
            current.copy(
                epgLoadingChannelIds = capChannelStateSet(current.epgLoadingChannelIds + ids, EpgLoadingStateLimit),
                epgAttemptedChannelIds = current.epgAttemptedChannelIds - ids,
            )
        )
    }

    private fun clearEpgLoading(channelIds: Collection<String>) {
        val ids = channelIds.asSequence().filter { it.isNotBlank() }.toSet()
        if (ids.isEmpty()) return
        val current = _uiState.value
        setUiState(current.copy(epgLoadingChannelIds = current.epgLoadingChannelIds - ids))
    }

    private fun finishEpgAttempt(channelIds: Collection<String>) {
        val ids = channelIds.asSequence().filter { it.isNotBlank() }.toSet()
        if (ids.isEmpty()) return
        val current = _uiState.value
        setUiState(
            current.copy(
                epgLoadingChannelIds = current.epgLoadingChannelIds - ids,
                epgAttemptedChannelIds = capChannelStateSet(current.epgAttemptedChannelIds + ids, EpgAttemptedStateLimit),
            )
        )
    }

    private fun capChannelStateSet(ids: Set<String>, limit: Int): Set<String> {
        if (ids.size <= limit) return ids
        return ids.toList().takeLast(limit).toSet()
    }

    private fun setEpgBackfillInProgress(inProgress: Boolean) {
        val current = _uiState.value
        if (current.epgBackfillInProgress == inProgress) return
        setUiState(current.copy(epgBackfillInProgress = inProgress))
    }

    private fun epgCoverageRatio(snapshot: IptvSnapshot): Float {
        if (snapshot.channels.isEmpty()) return 0f
        val covered = snapshot.channels.count { ch ->
            val item = snapshot.nowNext[ch.id]
            item != null && (item.now != null || item.next != null || item.later != null || item.upcoming.isNotEmpty())
        }
        return covered.toFloat() / snapshot.channels.size.toFloat()
    }

    private fun startPeriodicEpgRefresh() {
        if (periodicEpgJob?.isActive == true) return
        periodicEpgJob = viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(60_000L)
                val state = _uiState.value
                if (state.isConfigured && state.snapshot.channels.isNotEmpty()) {
                    refreshGuideFromCache()
                }
            }
        }
    }

    private fun startFullEpgWarmup() {
        val state = _uiState.value
        val channels = state.snapshot.channels
        if (channels.isEmpty()) return
        if (isLargeIptvList(channels.size)) {
            val cacheWarmIds = buildPriorityEpgChannelIds(
                state = state,
                maxChannels = LargeListPriorityCacheLimit
            )
            if (cacheWarmIds.isEmpty()) return
            val warmupKey = buildString {
                append("large-cache-only|")
                append(state.config.syncSignature())
                append('|')
                append(channels.size)
                append('|')
                append(cacheWarmIds.firstOrNull().orEmpty())
                append('|')
                append(cacheWarmIds.lastOrNull().orEmpty())
            }
            if (warmupKey == lastFullEpgWarmupKey) return
            lastFullEpgWarmupKey = warmupKey

            fullEpgWarmupJob?.cancel()
            fullEpgWarmupJob = viewModelScope.launch(Dispatchers.IO) {
                delay(800L)
                refreshGuideFromCache(cacheWarmIds)
            }.also { job ->
                job.invokeOnCompletion {
                    if (fullEpgWarmupJob === job) {
                        fullEpgWarmupJob = null
                    }
                }
            }
            return
        }
        val warmChannelIds = buildPriorityEpgChannelIds(
            state = state,
            maxChannels = StandardPriorityEpgLimit
        )
        if (warmChannelIds.isEmpty()) return
        val missingCount = warmChannelIds.count { id -> !hasProgramData(state.snapshot.nowNext[id]) }
        if (missingCount == 0) return

        val warmupKey = buildString {
            append(state.config.syncSignature())
            append('|')
            append(channels.size)
            append('|')
            append(warmChannelIds.size)
            append('|')
            append(warmChannelIds.firstOrNull().orEmpty())
            append('|')
            append(warmChannelIds.lastOrNull().orEmpty())
        }
        if (warmupKey == lastFullEpgWarmupKey) return
        lastFullEpgWarmupKey = warmupKey

        fullEpgWarmupJob?.cancel()
        fullEpgWarmupJob = viewModelScope.launch(Dispatchers.IO) {
            delay(1_500L)
            if (visibleEpgRefreshJob?.isActive == true) {
                visibleEpgRefreshJob?.join()
            }
            refreshGuideFromCache()

            val afterCache = _uiState.value
            val missingWarmIds = afterCache.snapshot.channels
                .asSequence()
                .map { it.id }
                .filter { id -> !hasProgramData(afterCache.snapshot.nowNext[id]) }
                .take(StandardPriorityEpgLimit)
                .toCollection(LinkedHashSet())
            if (missingWarmIds.isEmpty()) return@launch

            System.err.println("[EPG-Warm] warming priority guide for ${missingWarmIds.size} channels, missing=$missingCount")
            val refreshed = runCatching {
                iptvRepository.refreshEpgForChannels(missingWarmIds, maxChannels = missingWarmIds.size)
            }.getOrNull()

            if (!refreshed.isNullOrEmpty()) {
                mergeNowNext(refreshed)
            }
        }.also { job ->
            job.invokeOnCompletion {
                if (fullEpgWarmupJob === job) {
                    fullEpgWarmupJob = null
                }
            }
        }
    }

    private fun startCompleteEpgBackfill(
        force: Boolean = false,
        priorityChannelIds: Collection<String> = emptyList()
    ) {
        val state = _uiState.value
        val channels = state.snapshot.channels
        val largeList = isLargeIptvList(channels.size)
        if (!state.isConfigured || channels.isEmpty()) return
        if (!hasNetworkEpgSource(state.config)) return
        val indexedGuideChannels = if (largeList) {
            runCatching { iptvRepository.indexedGuideChannelCount() }.getOrDefault(0)
        } else {
            0
        }
        val indexedGuidePrograms = if (largeList) {
            runCatching { iptvRepository.indexedGuideProgramCount() }.getOrDefault(0)
        } else {
            0
        }
        val guideCapableChannels = if (largeList) {
            guideCapableChannelCount(channels)
        } else {
            channels.size
        }.coerceAtLeast(1)
        val indexedCoverage = if (largeList && channels.isNotEmpty()) {
            indexedGuideChannels.toFloat() / guideCapableChannels.toFloat()
        } else {
            0f
        }
        val hasGuideData = hasAnyEpgData(state.snapshot)
        val ageMs = iptvRepository.cachedEpgAgeMs()
        if (
            !force &&
            hasGuideData &&
            ageMs < 24 * 60 * 60_000L &&
            (!largeList || indexedCoverage >= LargeListCompleteGuideCoverageTarget)
        ) {
            setEpgBackfillInProgress(false)
            System.err.println(
                "[EPG-Complete] Keeping cached guide; age=${ageMs / 1000}s " +
                    "index=$indexedGuideChannels/$indexedGuidePrograms"
            )
            return
        }
        if (!force && largeList && hasGuideData && indexedCoverage >= LargeListCompleteGuideCoverageTarget) {
            completeEpgBackfillJob?.cancel()
            completeEpgBackfillJob = null
            setEpgBackfillInProgress(false)
            return
        }

        val coverage = epgCoverageRatio(state.snapshot)
        val cacheLooksComplete = if (largeList) {
            indexedCoverage >= LargeListCompleteGuideCoverageTarget && ageMs < 24 * 60 * 60_000L
        } else {
            coverage >= 0.98f && ageMs < 6 * 60 * 60_000L
        }
        if (!force && cacheLooksComplete) return
        if (completeEpgBackfillJob?.isActive == true) return

        val backfillKey = buildString {
            append(state.config.syncSignature())
            append('|')
            append(channels.size)
            append('|')
            append((coverage * 1_000).toInt())
            append('|')
            append((indexedCoverage * 1_000).toInt())
            append('|')
            append(ageMs / (30 * 60_000L))
        }
        if (!force && backfillKey == lastCompleteEpgBackfillKey) return
        lastCompleteEpgBackfillKey = backfillKey

        if (largeList) {
            System.err.println(
                "[EPG-Complete] Starting large-list full guide index: " +
                    "channels=${channels.size} guideCapable=$guideCapableChannels " +
                    "indexed=$indexedGuideChannels programs=$indexedGuidePrograms " +
                    "coverage=${(indexedCoverage * 100).toInt()}% age=${ageMs / 1000}s"
            )
        }
        setEpgBackfillInProgress(true)
        completeEpgBackfillJob = viewModelScope.launch(Dispatchers.IO) {
            delay(if (largeList) 6_000L else if (hasGuideData) 2_000L else 250L)
            val backfillResult = runCatching {
                kotlinx.coroutines.withTimeoutOrNull(900_000L) {
                    iptvRepository.loadSnapshot(
                        forcePlaylistReload = false,
                        forceEpgReload = true,
                        allowNetworkEpgFetch = true,
                        allowBroadShortEpg = true,
                        onProgress = { progress ->
                            System.err.println("[EPG-Complete] ${progress.message} ${progress.percent ?: ""}".trim())
                        }
                    )
                }
            }
            backfillResult.onFailure { error ->
                AppLogger.recordException(
                    throwable = error,
                    context = mapOf(
                        "error_area" to "IPTV",
                        "iptv_phase" to "complete_epg_backfill",
                        "channel_count" to countBucket(channels.size),
                        "start_coverage_pct" to ((coverage * 100).toInt()).toString()
                    )
                )
            }
            val snapshot = backfillResult.getOrNull()
            if (snapshot == null) {
                lastCompleteEpgBackfillKey = null
                AppLogger.recordException(
                    throwable = IllegalStateException("Complete EPG backfill timed out"),
                    context = mapOf(
                        "error_area" to "IPTV",
                        "iptv_phase" to "complete_epg_backfill_timeout",
                        "channel_count" to countBucket(channels.size),
                        "start_coverage_pct" to ((coverage * 100).toInt()).toString()
                    )
                )
                return@launch
            }

            if (snapshot.channels.isEmpty() || snapshot.nowNext.isEmpty()) {
                lastCompleteEpgBackfillKey = null
                AppLogger.recordException(
                    throwable = IllegalStateException("Complete EPG backfill returned empty guide"),
                    context = mapOf(
                        "error_area" to "IPTV",
                        "iptv_phase" to "complete_epg_backfill_empty",
                        "channel_count" to countBucket(channels.size),
                        "snapshot_channels" to snapshot.channels.size.toString(),
                        "snapshot_now_next" to snapshot.nowNext.size.toString()
                    )
                )
                return@launch
            }
            withContext(Dispatchers.Main.immediate) {
                val current = _uiState.value
                if (current.config.syncSignature() != state.config.syncSignature()) return@withContext
                if (largeList) {
                    val priorityIds = LinkedHashSet<String>(StandardPriorityEpgLimit)
                    priorityChannelIds
                        .asSequence()
                        .filter { it.isNotBlank() }
                        .take(StandardPriorityEpgLimit)
                        .forEach(priorityIds::add)
                    current.epgLoadingChannelIds
                        .asSequence()
                        .filter { it.isNotBlank() }
                        .take(StandardPriorityEpgLimit - priorityIds.size)
                        .forEach(priorityIds::add)
                    buildPriorityEpgChannelIds(current, maxChannels = LargeListPriorityCacheLimit)
                        .asSequence()
                        .take(StandardPriorityEpgLimit - priorityIds.size)
                        .forEach(priorityIds::add)

                    val visibleGuide = withContext(Dispatchers.Default) {
                        val indexed = iptvRepository.reDeriveCachedNowNext(priorityIds).orEmpty()
                        val direct = snapshot.nowNext.filterKeys { it in priorityIds }
                        if (direct.isEmpty()) indexed else direct + indexed
                    }
                    mergeNowNext(visibleGuide)
                    lastCompleteEpgBackfillCompletedAt = System.currentTimeMillis()
                    val covered = visibleGuide.count { (_, item) -> hasProgramData(item) }
                    val indexedAfter = runCatching { iptvRepository.indexedGuideChannelCount() }.getOrDefault(0)
                    val programsAfter = runCatching { iptvRepository.indexedGuideProgramCount() }.getOrDefault(0)
                    System.err.println(
                        "[EPG-Complete] indexed full guide; index=$indexedAfter channels/" +
                            "$programsAfter programs; merged visible guide $covered/${priorityIds.size} channels"
                    )
                    return@withContext
                }
                val mergedSnapshot = snapshot.copy(
                    favoriteGroups = current.snapshot.favoriteGroups,
                    favoriteChannels = current.snapshot.favoriteChannels,
                    hiddenGroups = current.snapshot.hiddenGroups,
                    groupOrder = current.snapshot.groupOrder,
                )
                setUiState(
                    current.copy(
                        snapshot = mergedSnapshot,
                        epgLoadingChannelIds = emptySet(),
                    )
                )
                val finalCoveragePct = (epgCoverageRatio(mergedSnapshot) * 100).toInt()
                lastCompleteEpgBackfillCompletedAt = System.currentTimeMillis()
                System.err.println("[EPG-Complete] merged full guide coverage=$finalCoveragePct%")
                if (finalCoveragePct < 80) {
                    AppLogger.breadcrumb(
                        tag = "IPTV",
                        message = "complete_epg_low_coverage channel_count=${countBucket(channels.size)} coverage=$finalCoveragePct",
                        severity = "warning"
                    )
                }
            }
        }.also { job ->
            job.invokeOnCompletion {
                if (completeEpgBackfillJob === job) {
                    completeEpgBackfillJob = null
                    viewModelScope.launch {
                        setEpgBackfillInProgress(false)
                    }
                }
            }
        }
    }

    private fun requestVisibleCompleteEpgBackfill(priorityChannelIds: Collection<String> = emptyList()) {
        val isLargeList = isLargeIptvList(_uiState.value.snapshot.channels.size)
        if (isLargeList) {
            System.err.println("[EPG-Complete] Visible guide unresolved; large list uses scoped XMLTV fallback instead")
            return
        }
        val now = System.currentTimeMillis()
        if (now - lastVisibleForcedCompleteEpgAt < 60_000L) return
        lastVisibleForcedCompleteEpgAt = now
        startCompleteEpgBackfill(force = true, priorityChannelIds = priorityChannelIds)
    }

    fun setQuery(query: String) {
        setUiState(_uiState.value.copy(query = query))
    }

    fun toggleFavoriteGroup(groupName: String) {
        viewModelScope.launch {
            iptvRepository.toggleFavoriteGroup(groupName)
            scheduleIptvCloudSync()
        }
    }

    fun toggleFavoriteChannel(channelId: String) {
        viewModelScope.launch {
            iptvRepository.toggleFavoriteChannel(channelId)
            scheduleIptvCloudSync()
        }
    }

    fun toggleHiddenGroup(groupName: String) {
        toggleHiddenGroup(null, groupName)
    }

    fun toggleHiddenGroup(playlistId: String?, groupName: String) {
        viewModelScope.launch {
            val targetPlaylistId = playlistId?.trim().orEmpty()
            if (targetPlaylistId.isNotBlank()) {
                iptvRepository.toggleHiddenGroup(targetPlaylistId, groupName)
            } else {
                val config = iptvRepository.observeConfig().first()
                val activePlaylists = config.playlists.filter { it.enabled }.map { it.id }
                activePlaylists.forEach { activePlaylistId ->
                    iptvRepository.toggleHiddenGroup(activePlaylistId, groupName)
                }
            }
            scheduleIptvCloudSync()
        }
    }

    fun prefetchVisibleCategoryEpg(
        channelIds: List<String>,
        selectedChannelId: String?,
        eagerLimit: Int = 96,
        backgroundLimit: Int = 640
    ) {
        if (channelIds.isEmpty()) return
        val firstPaintLimit = 40
        val selectedId = selectedChannelId
            ?.takeIf { it in channelIds }
            ?: channelIds.firstOrNull()
        val orderedIds = buildList {
            selectedId?.let { add(it) }
            channelIds.forEach { id ->
                if (id != selectedId) add(id)
            }
        }
        if (orderedIds.isEmpty()) return

        val currentState = _uiState.value
        val currentNowNext = currentState.snapshot.nowNext
        val missingCount = orderedIds.count { id ->
            !hasUsefulVisibleGuideData(currentNowNext[id])
        }
        if (missingCount == 0) return

        val refreshKey = buildString {
            append(_uiState.value.config.syncSignature())
            append('|')
            append(selectedId.orEmpty())
            append('|')
            append(orderedIds.size)
            append('|')
            append(eagerLimit)
            append('|')
            append(orderedIds.firstOrNull().orEmpty())
            append('|')
            append(orderedIds.lastOrNull().orEmpty())
        }
        val now = System.currentTimeMillis()
        if (refreshKey == lastVisibleEpgRefreshKey && now - lastVisibleEpgRefreshAt < 20_000L) return

        lastVisibleEpgRefreshKey = refreshKey
        lastVisibleEpgRefreshAt = now
        val requestLimit = maxOf(firstPaintLimit, eagerLimit, backgroundLimit).coerceAtMost(orderedIds.size)
        val missingIds = orderedIds
            .filterNot { id ->
                hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id])
            }
            .take(requestLimit)
        if (missingIds.isEmpty()) return

        markEpgLoading(missingIds)
        enqueueVisibleEpgRefresh(missingIds, selectedId)
    }

    fun refreshCurrentChannelEpg(channelId: String?) {
        val id = channelId?.trim().orEmpty()
        if (id.isBlank()) return
        val now = System.currentTimeMillis()
        val lastRefreshAt = currentChannelEpgRefreshAt[id] ?: 0L
        if (now - lastRefreshAt < CurrentChannelEpgRefreshThrottleMs) return
        currentChannelEpgRefreshAt[id] = now
        while (currentChannelEpgRefreshAt.size > 160) {
            val firstKey = currentChannelEpgRefreshAt.keys.firstOrNull() ?: break
            currentChannelEpgRefreshAt.remove(firstKey)
        }

        markEpgLoading(setOf(id))
        viewModelScope.launch {
            refreshGuideFromCache(setOf(id))
            val cachedGuide = _uiState.value.snapshot.nowNext[id]
            if (hasRichSelectedGuideData(cachedGuide)) {
                clearEpgLoading(setOf(id))
                return@launch
            }
            System.err.println("[EPG-Current] refreshing channel=$id")
            val refreshed = withContext(Dispatchers.IO) {
                runCatching {
                    iptvRepository.refreshEpgForChannels(
                        channelIds = setOf(id),
                        maxChannels = 1,
                        preferFullCatchupHistory = true
                    )
                }.getOrNull()
            }
            if (!refreshed.isNullOrEmpty()) {
                mergeNowNext(refreshed)
            } else {
                finishEpgAttempt(setOf(id))
            }
        }
    }

    fun refreshCatchupHistoryForChannel(channelId: String?) {
        val id = channelId?.trim().orEmpty()
        if (id.isBlank()) return
        val now = System.currentTimeMillis()
        val current = _uiState.value
        val recentCount = current.snapshot.nowNext[id]?.recent
            .orEmpty()
            .count { it.endUtcMillis <= now && it.endUtcMillis >= now - 48L * 60L * 60_000L }
        if (recentCount >= RichCatchupRecentTarget) return
        val lastRefreshAt = catchupHistoryRefreshAt[id] ?: 0L
        if (now - lastRefreshAt < RichCatchupRefreshThrottleMs) return

        catchupHistoryRefreshAt[id] = now
        while (catchupHistoryRefreshAt.size > 120) {
            val firstKey = catchupHistoryRefreshAt.keys.firstOrNull() ?: break
            catchupHistoryRefreshAt.remove(firstKey)
        }

        markEpgLoading(setOf(id))
        viewModelScope.launch {
            val refreshed = withContext(Dispatchers.IO) {
                runCatching {
                    iptvRepository.refreshEpgForChannels(
                        channelIds = setOf(id),
                        maxChannels = 1,
                        preferFullCatchupHistory = true
                    )
                }.getOrNull()
            }
            if (!refreshed.isNullOrEmpty()) {
                mergeNowNext(refreshed)
            } else {
                finishEpgAttempt(setOf(id))
            }
        }
    }

    private fun enqueueVisibleEpgRefresh(channelIds: List<String>, selectedChannelId: String?) {
        val shouldRestart = synchronized(visibleEpgQueueLock) {
            val selectedId = selectedChannelId?.takeIf { it in channelIds }
            val selectedChanged = selectedId != null && selectedId != pendingVisibleEpgSelectedChannelId
            if (selectedChanged) {
                pendingVisibleEpgChannelIds.clear()
            }
            selectedId?.let { pendingVisibleEpgSelectedChannelId = it }
            channelIds.forEach { id ->
                if (id.isNotBlank()) {
                    pendingVisibleEpgChannelIds.add(id)
                }
            }
            selectedChanged
        }
        if (shouldRestart && visibleEpgRefreshJob?.isActive == true) {
            visibleEpgRefreshJob?.cancel()
            visibleEpgRefreshJob = null
        }
        startVisibleEpgDrain()
    }

    private fun startVisibleEpgDrain() {
        if (visibleEpgRefreshJob?.isActive == true) return
        visibleEpgRefreshJob = viewModelScope.launch {
            delay(120L)
            var pass = 0
            while (true) {
                val drain = drainVisibleEpgBatch(
                    maxChannels = when (pass) {
                        0 -> 48
                        1 -> 96
                        else -> 160
                    }
                )
                val batch = drain.ids
                if (batch.isEmpty()) break

                val batchSet = batch.toCollection(LinkedHashSet())
                markEpgLoading(batchSet)
                runCatching {
                    refreshGuideFromCache(batchSet)

                    val selectedMissingId = drain.selectedId
                        ?.takeIf { id -> id in batchSet && !hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) }
                    if (selectedMissingId != null) {
                        System.err.println("[EPG-Category] selected-first channel=$selectedMissingId pass=$pass")
                        val selectedRefresh = withContext(Dispatchers.IO) {
                            runCatching {
                                iptvRepository.refreshEpgForChannels(
                                    setOf(selectedMissingId),
                                    maxChannels = 1,
                                    preferFullCatchupHistory = true
                                )
                            }.getOrNull()
                        }
                        if (!selectedRefresh.isNullOrEmpty()) {
                            mergeNowNext(selectedRefresh)
                        }
                    }

                    val missingIds = batch
                        .filterNot { id -> hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) }
                    if (missingIds.isNotEmpty()) {
                        System.err.println("[EPG-Category] queued=${missingIds.size} pass=$pass selected=${drain.selectedId.orEmpty()}")
                        val refreshed = withContext(Dispatchers.IO) {
                            runCatching {
                                iptvRepository.refreshEpgForChannels(
                                    missingIds.toSet(),
                                    maxChannels = missingIds.size,
                                    preferFullCatchupHistory = false
                                )
                            }.getOrNull()
                        }
                        if (!refreshed.isNullOrEmpty()) {
                            mergeNowNext(refreshed)
                        }
                    }
                }.onFailure { error ->
                    AppLogger.recordException(
                        throwable = error,
                        context = mapOf(
                            "error_area" to "IPTV",
                            "iptv_phase" to "visible_epg_prefetch",
                            "channel_count" to batch.size.toString()
                        )
                    )
                }
                val unresolvedIds = batchSet.filterNot { id ->
                    hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id])
                }
                if (unresolvedIds.isNotEmpty()) {
                    val current = _uiState.value
                    val hasGuideSource = current.hasPotentialGuideSource && hasNetworkEpgSource(current.config)
                    val fullGuideRecentlyCompleted = lastCompleteEpgBackfillCompletedAt > 0L &&
                        System.currentTimeMillis() - lastCompleteEpgBackfillCompletedAt < 5 * 60_000L
                    val waitingForFullGuide = current.epgBackfillInProgress ||
                        (hasGuideSource && !fullGuideRecentlyCompleted)
                    if (waitingForFullGuide) {
                        if (isLargeIptvList(current.snapshot.channels.size)) {
                            finishEpgAttempt(unresolvedIds)
                        } else {
                            clearEpgLoading(unresolvedIds)
                            if (!current.epgBackfillInProgress) {
                                requestVisibleCompleteEpgBackfill()
                            }
                        }
                    } else {
                        finishEpgAttempt(unresolvedIds)
                    }
                }
                pass += 1
                delay(60L)
            }
        }.also { job ->
            job.invokeOnCompletion {
                if (visibleEpgRefreshJob === job) {
                    visibleEpgRefreshJob = null
                }
            }
        }
    }

    private fun drainVisibleEpgBatch(maxChannels: Int): VisibleEpgDrain {
        if (maxChannels <= 0) return VisibleEpgDrain(emptyList(), null)
        val currentNowNext = _uiState.value.snapshot.nowNext
        return synchronized(visibleEpgQueueLock) {
            if (pendingVisibleEpgChannelIds.isEmpty()) return@synchronized VisibleEpgDrain(emptyList(), null)
            val batch = ArrayList<String>(maxChannels)
            val selectedAtStart = pendingVisibleEpgSelectedChannelId

            fun takePending(id: String?) {
                if (id.isNullOrBlank() || batch.size >= maxChannels) return
                if (pendingVisibleEpgChannelIds.remove(id) && !hasUsefulVisibleGuideData(currentNowNext[id])) {
                    batch += id
                }
            }

            takePending(pendingVisibleEpgSelectedChannelId)
            val iterator = pendingVisibleEpgChannelIds.iterator()
            while (iterator.hasNext() && batch.size < maxChannels) {
                val id = iterator.next()
                iterator.remove()
                if (!hasUsefulVisibleGuideData(currentNowNext[id])) {
                    batch += id
                }
            }
            val selectedId = pendingVisibleEpgSelectedChannelId
            if (selectedId == null || selectedId !in pendingVisibleEpgChannelIds) {
                pendingVisibleEpgSelectedChannelId = null
            }
            VisibleEpgDrain(batch, selectedAtStart?.takeIf { it in batch })
        }
    }

    fun moveGroupUp(groupName: String) {
        viewModelScope.launch {
            val current = currentVisiblePlaylistGroups()
            val config = iptvRepository.observeConfig().first()
            val activePlaylists = config.playlists.filter { it.enabled }.map { it.id }
            activePlaylists.forEach { playlistId ->
                iptvRepository.moveGroupUp(playlistId, groupName, current)
            }
            scheduleIptvCloudSync()
        }
    }

    fun moveGroupToTop(groupName: String) {
        viewModelScope.launch {
            val current = currentVisiblePlaylistGroups()
            val config = iptvRepository.observeConfig().first()
            val activePlaylists = config.playlists.filter { it.enabled }.map { it.id }
            activePlaylists.forEach { playlistId ->
                iptvRepository.moveGroupToTop(playlistId, groupName, current)
            }
            scheduleIptvCloudSync()
        }
    }

    fun moveGroupDown(groupName: String) {
        viewModelScope.launch {
            val current = currentVisiblePlaylistGroups()
            val config = iptvRepository.observeConfig().first()
            val activePlaylists = config.playlists.filter { it.enabled }.map { it.id }
            activePlaylists.forEach { playlistId ->
                iptvRepository.moveGroupDown(playlistId, groupName, current)
            }
            scheduleIptvCloudSync()
        }
    }

    private fun currentVisiblePlaylistGroups(): List<String> {
        val snapshot = _uiState.value.snapshot
        val hidden = snapshot.hiddenGroups.mapTo(HashSet()) { it.trim() }
        return snapshot.grouped.keys
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() && it !in hidden }
            .distinct()
            .toList()
    }

    fun rememberTvSession(
        lastChannelId: String?,
        lastGroupName: String?,
        lastFocusedZone: String,
        markOpened: Boolean = false
    ) {
        val current = _uiState.value.tvSession
        val normalizedChannelId = lastChannelId.orEmpty().trim().ifBlank { current.lastChannelId }
        val normalizedGroupName = lastGroupName.orEmpty().trim().ifBlank { current.lastGroupName }
        val normalizedFocusZone = lastFocusedZone.trim().ifBlank { current.lastFocusedZone.ifBlank { "GUIDE" } }
        val channelChanged = normalizedChannelId.isNotBlank() && normalizedChannelId != current.lastChannelId
        val recentChannelIds = if (normalizedChannelId.isNotBlank() && (markOpened || channelChanged)) {
            current.recentChannelIds
                .filterNot { it == normalizedChannelId }
                .plus(normalizedChannelId)
                .takeLast(40)
        } else {
            current.recentChannelIds
        }
        val next = current.copy(
            lastChannelId = normalizedChannelId,
            lastGroupName = normalizedGroupName,
            lastFocusedZone = normalizedFocusZone,
            lastOpenedAt = if (markOpened || channelChanged) System.currentTimeMillis() else current.lastOpenedAt,
            recentChannelIds = recentChannelIds
        )
        if (next == current) return

        _uiState.value = _uiState.value.copy(tvSession = next)
        maybeWarmStartupGuide()
        tvSessionSaveJob?.cancel()
        tvSessionSaveJob = viewModelScope.launch(Dispatchers.IO) {
            kotlinx.coroutines.delay(if (markOpened || channelChanged) 0L else 220L)
            iptvRepository.saveTvSessionState(next)
            if (markOpened || channelChanged) {
                scheduleIptvCloudSync()
            }
        }
    }

    suspend fun resolvePlayableStreamUrl(
        channel: IptvChannel,
        program: IptvProgram? = null,
        forceRefresh: Boolean = false,
        catchupAttempt: Int = 0
    ): String {
        val rawUrl = if (program != null) {
            iptvRepository.resolvePlayableCatchupUrl(channel, program, catchupAttempt)
        } else {
            channel.streamUrl
        }
        return resolveStalkerStreamIfNeeded(rawUrl, forceRefresh)
    }

    private suspend fun resolveStalkerStreamIfNeeded(rawUrl: String, forceRefresh: Boolean): String {
        val trimmed = rawUrl.trim()
        if (!looksLikeStalkerStreamCommand(trimmed)) return trimmed

        if (!forceRefresh) {
            synchronized(resolvedStalkerStreamCache) {
                resolvedStalkerStreamCache[trimmed]?.let { return it }
            }
        }

        val resolved = withContext(Dispatchers.IO) {
            iptvRepository.cachedStalkerApi?.resolveStreamUrl(trimmed)
        }?.trim().orEmpty()
        val playable = resolved.ifBlank { trimmed.removePrefix("ffmpeg").trim() }
        if (playable.isNotBlank()) {
            synchronized(resolvedStalkerStreamCache) {
                resolvedStalkerStreamCache[trimmed] = playable
                while (resolvedStalkerStreamCache.size > 200) {
                    val firstKey = resolvedStalkerStreamCache.keys.firstOrNull() ?: break
                    resolvedStalkerStreamCache.remove(firstKey)
                }
            }
        }
        return playable.ifBlank { trimmed }
    }

    private fun setUiState(nextState: TvUiState) {
        val previous = _uiState.value
        if (canReusePreparedContent(previous, nextState)) {
            _uiState.value = nextState.copy(
                channelLookup = previous.channelLookup,
                groups = previous.groups,
                channelsByGroup = previous.channelsByGroup
            )
        } else {
            val revision = ++preparedContentRevision
            preparedContentJob?.cancel()
            _uiState.value = nextState.copy(
                channelLookup = previous.channelLookup,
                groups = previous.groups,
                channelsByGroup = previous.channelsByGroup
            )
            preparedContentJob = viewModelScope.launch(Dispatchers.Default) {
                val prepared = setPreparedContent(nextState)
                withContext(Dispatchers.Main.immediate) {
                    if (revision == preparedContentRevision) {
                        val latest = _uiState.value
                        _uiState.value = latest.copy(
                            channelLookup = prepared.channelLookup,
                            groups = prepared.groups,
                            channelsByGroup = prepared.channelsByGroup
                        )
                    }
                }
            }
        }
    }

    private fun canReusePreparedContent(previous: TvUiState, next: TvUiState): Boolean {
        val previousSnapshot = previous.snapshot
        val nextSnapshot = next.snapshot
        return previous.query == next.query &&
            previousSnapshot.channels === nextSnapshot.channels &&
            previousSnapshot.grouped === nextSnapshot.grouped &&
            previousSnapshot.favoriteChannels == nextSnapshot.favoriteChannels &&
            previousSnapshot.favoriteGroups == nextSnapshot.favoriteGroups &&
            previousSnapshot.hiddenGroups == nextSnapshot.hiddenGroups &&
            previousSnapshot.groupOrder == nextSnapshot.groupOrder
    }

    private fun maybeWarmStartupGuide() {
        val state = _uiState.value
        if (state.channelsByGroup.isEmpty()) return
        val largeList = isLargeIptvList(state.snapshot.channels.size)

        val warmGroups = buildStartupWarmGroups(state)
        if (warmGroups.isEmpty()) return
        val warmChannels = buildList {
            warmGroups.forEachIndexed { index, groupName ->
                val limit = when {
                    largeList && (groupName == FAVORITES_GROUP_NAME || index == 0) -> 48
                    largeList -> 16
                    groupName == FAVORITES_GROUP_NAME || index == 0 -> 96
                    else -> 56
                }
                addAll(state.channelsByGroup[groupName].orEmpty().take(limit))
            }
        }
            .distinctBy { it.id }
        if (warmChannels.isEmpty()) return

        val coverage = warmChannels.count { hasProgramData(state.snapshot.nowNext[it.id]) }
        if (coverage >= minOf(warmChannels.size, if (largeList) 12 else 24)) return

        val preferredSelectedId = state.tvSession.lastChannelId
            .takeIf { id -> id.isNotBlank() && warmChannels.any { channel -> channel.id == id } }
            ?: warmChannels.firstOrNull()?.id
        val warmupKey = buildString {
            append(warmGroups.joinToString(","))
            append('|')
            append(warmChannels.firstOrNull()?.id.orEmpty())
            append('|')
            append(warmChannels.size)
            append('|')
            append(preferredSelectedId.orEmpty())
        }
        if (warmupKey == startupGuideWarmupKey) return
        startupGuideWarmupKey = warmupKey

        prefetchVisibleCategoryEpg(
            channelIds = warmChannels.map { it.id },
            selectedChannelId = preferredSelectedId,
            eagerLimit = minOf(warmChannels.size, if (largeList) 32 else 96),
            backgroundLimit = minOf(warmChannels.size, if (largeList) 96 else 520)
        )
    }

    private fun scheduleIptvCloudSync() {
        iptvCloudSyncJob?.cancel()
        iptvCloudSyncJob = viewModelScope.launch(Dispatchers.IO) {
            kotlinx.coroutines.delay(350L)
            val firstAttempt = runCatching { cloudSyncRepository.pushToCloud() }.getOrNull()
            if (firstAttempt?.isFailure != false) {
                kotlinx.coroutines.delay(1_200L)
                runCatching { cloudSyncRepository.pushToCloud() }
            }
        }
    }
}

private fun buildStartupWarmGroups(state: TvUiState): List<String> {
    if (state.channelsByGroup.isEmpty()) return emptyList()
    val favoritesFirst = state.channelsByGroup[FAVORITES_GROUP_NAME]
        .orEmpty()
        .takeIf { it.isNotEmpty() }
        ?.let { listOf(FAVORITES_GROUP_NAME) }
        .orEmpty()
    val sessionGroup = state.tvSession.lastGroupName
        .takeIf { it.isNotBlank() && state.channelsByGroup[it].orEmpty().isNotEmpty() }
        ?.let(::listOf)
        .orEmpty()
    val favoriteGroups = state.snapshot.favoriteGroups
        .filter { state.channelsByGroup[it].orEmpty().isNotEmpty() }
    val netherlandsGroups = state.groups.filter { groupName ->
        state.channelsByGroup[groupName].orEmpty().isNotEmpty() && groupName.isPriorityStartupGroup()
    }
    val fallbackGroups = state.groups.filter { state.channelsByGroup[it].orEmpty().isNotEmpty() }
    return (favoritesFirst + sessionGroup + favoriteGroups + netherlandsGroups + fallbackGroups)
        .distinct()
        .take(16)
}

private fun buildPriorityEpgChannelIds(
    state: TvUiState,
    maxChannels: Int
): LinkedHashSet<String> {
    if (maxChannels <= 0 || state.channelsByGroup.isEmpty()) return LinkedHashSet()
    val selectedGroups = buildStartupWarmGroups(state)
    val result = LinkedHashSet<String>(maxChannels)
    selectedGroups.forEachIndexed { index, groupName ->
        if (result.size >= maxChannels) return@forEachIndexed
        val perGroupLimit = when {
            groupName == FAVORITES_GROUP_NAME -> 520
            index == 0 -> 420
            groupName.isPriorityStartupGroup() -> 280
            else -> 120
        }
        state.channelsByGroup[groupName].orEmpty()
            .asSequence()
            .take(perGroupLimit)
            .forEach { channel ->
                if (result.size < maxChannels) {
                    result.add(channel.id)
                }
            }
    }
    if (result.isEmpty()) {
        state.snapshot.channels.asSequence()
            .take(maxChannels)
            .forEach { result.add(it.id) }
    }
    return result
}

private fun isLargeIptvList(channelCount: Int): Boolean {
    return channelCount > LargeIptvListChannelCount
}

private fun guideCapableChannelCount(channels: List<IptvChannel>): Int {
    return channels.count { channel ->
        !channel.epgId.isNullOrBlank() || !channel.tvgName.isNullOrBlank()
    }.takeIf { it > 0 } ?: channels.size
}

private fun setPreparedContent(state: TvUiState): TvUiState {
    val preparedGroups = buildPreparedGroups(state.snapshot)
    val preparedChannelsByGroup = buildPreparedChannelsByGroup(
        snapshot = state.snapshot,
        query = state.query,
        groups = preparedGroups
    )
    return state.copy(
        channelLookup = state.snapshot.channels.associateBy { it.id },
        groups = preparedGroups,
        channelsByGroup = preparedChannelsByGroup
    )
}

private fun buildPreparedGroups(snapshot: IptvSnapshot): List<String> {
    val dynamicGroups = snapshot.grouped.keys.toList()
    val hiddenSet = snapshot.hiddenGroups.toHashSet()
    val visibleGroups = dynamicGroups.filterNot { hiddenSet.contains(it) }
    val favorites = snapshot.favoriteGroups.filter { visibleGroups.contains(it) }
    val others = visibleGroups.filterNot { snapshot.favoriteGroups.contains(it) }
    val baseOrdered = if (snapshot.groupOrder.isNotEmpty()) {
        val orderMap = snapshot.groupOrder.withIndex().associate { (i, groupName) -> groupName to i }
        (favorites + others).sortedBy { orderMap[it] ?: Int.MAX_VALUE }
    } else {
        favorites + others
    }
    val hasFavoriteChannelsInSnapshot = snapshot.favoriteChannels
        .toHashSet()
        .let { ids -> snapshot.channels.any { ids.contains(it.id) } }
    return if (hasFavoriteChannelsInSnapshot) {
        listOf(FAVORITES_GROUP_NAME) + baseOrdered
    } else {
        baseOrdered
    }
}

private fun buildPreparedChannelsByGroup(
    snapshot: IptvSnapshot,
    query: String,
    groups: List<String>
): Map<String, List<IptvChannel>> {
    if (groups.isEmpty()) return emptyMap()
    val trimmedQuery = query.trim().lowercase()
    val favoriteChannelIds = snapshot.favoriteChannels.toHashSet()
    return buildMap(groups.size) {
        groups.forEach { group ->
            val source = if (group == FAVORITES_GROUP_NAME) {
                if (favoriteChannelIds.isEmpty()) {
                    emptyList()
                } else {
                    val favoriteOrder = snapshot.favoriteChannels
                        .withIndex()
                        .associate { (index, id) -> id to index }
                    snapshot.channels
                        .filter { favoriteChannelIds.contains(it.id) }
                        .sortedBy { favoriteOrder[it.id] ?: Int.MAX_VALUE }
                }
            } else {
                snapshot.grouped[group].orEmpty()
            }
            put(group, filterTvChannels(source, trimmedQuery))
        }
    }
}

private fun String.isNetherlandsGroup(): Boolean {
    val tokens = lowercase()
        .split(TvVMRegexes.NON_ALPHA_NUM)
        .filter { it.isNotBlank() }
        .toSet()
    return "netherlands" in tokens || "nederland" in tokens || "nl" in tokens
}

private fun String.isPriorityStartupGroup(): Boolean {
    return isNetherlandsGroup() || lowercase().contains("4k")
}

private fun filterTvChannels(
    source: List<IptvChannel>,
    trimmedQuery: String
): List<IptvChannel> {
    if (trimmedQuery.isBlank()) return source
    return source.mapNotNull { channel ->
        val name = channel.name.lowercase()
        val groupName = channel.group.lowercase()
        val score = when {
            name.startsWith(trimmedQuery) -> 100
            name.contains(trimmedQuery) -> 80
            groupName.startsWith(trimmedQuery) -> 60
            groupName.contains(trimmedQuery) -> 45
            else -> 0
        }
        if (score > 0) channel to score else null
    }
        .sortedByDescending { it.second }
        .map { it.first }
}

private fun hasNetworkEpgSource(config: IptvConfig): Boolean {
    return config.epgUrl.isNotBlank() ||
        config.stalkerPortalUrl.isNotBlank() ||
        config.m3uUrl.isNotBlank() ||
        looksLikeXtream(config.m3uUrl) ||
        config.playlists.any { playlist ->
            playlist.enabled && (
                playlist.epgUrl.isNotBlank() ||
                    playlist.epgUrls.any { it.isNotBlank() } ||
                    playlist.m3uUrl.isNotBlank() ||
                    looksLikeXtream(playlist.m3uUrl) ||
                    looksLikeXtream(playlist.epgUrl)
                )
        }
}

private fun IptvConfig.hasConfiguredEpgSource(): Boolean {
    return epgUrl.isNotBlank() ||
        stalkerPortalUrl.isNotBlank() ||
        m3uUrl.isNotBlank() ||
        looksLikeXtream(m3uUrl) ||
        playlists.any { playlist ->
            playlist.enabled && (
                playlist.epgUrl.isNotBlank() ||
                    playlist.epgUrls.any { it.isNotBlank() } ||
                    playlist.m3uUrl.isNotBlank() ||
                    looksLikeXtream(playlist.m3uUrl) ||
                    looksLikeXtream(playlist.epgUrl)
                )
        }
}

private fun looksLikeXtream(url: String): Boolean {
    return url.contains("player_api.php", ignoreCase = true) ||
        url.contains("get.php", ignoreCase = true) ||
        url.contains("xmltv.php", ignoreCase = true)
}

private fun looksLikeStalkerStreamCommand(url: String): Boolean {
    val trimmed = url.trim()
    if (trimmed.startsWith("ffmpeg", ignoreCase = true)) return true
    if (trimmed.startsWith("/") && !trimmed.startsWith("//")) return true
    return trimmed.startsWith("cmd=", ignoreCase = true) ||
        trimmed.contains("type=itv", ignoreCase = true) &&
        trimmed.contains("create_link", ignoreCase = true)
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
                playlist.epgUrls.joinToString(","),
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

private object TvVMRegexes {
    val NON_ALPHA_NUM = Regex("[^a-z0-9]+")
}
