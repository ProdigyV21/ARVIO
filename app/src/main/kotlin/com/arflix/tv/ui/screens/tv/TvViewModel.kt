package com.arflix.tv.ui.screens.tv

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvSnapshot
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.IptvConfig
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.IptvTvSessionState
import com.arflix.tv.util.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
private const val LargeListFocusedNetworkEpgLimit = 24
private const val LargeListFavoriteEpgPasses = 12
private const val LargeListFavoritePrefetchLookahead = 28
private const val RichCatchupRecentTarget = 6
private const val CatchupHistoryWindowMs = 48L * 60L * 60_000L
private const val RichCatchupRefreshThrottleMs = 45_000L
private const val CurrentChannelEpgRefreshThrottleMs = 12_000L
private const val LargeListCompleteGuideCoverageTarget = 0.75f
private const val PlaybackEpgBackfillResumeDelayMs = 90_000L
private const val LargeListCompleteEpgBackfillStartupDelayMs = 180_000L

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
    @ApplicationContext private val context: Context,
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
    private var liveTvPlaybackActive: Boolean = false
    private var deferredCompleteEpgBackfill: Boolean = false
    private val deferredCompleteEpgPriorityIds = LinkedHashSet<String>()
    private var deferredCompleteEpgBackfillJob: Job? = null
    private var preparedContentJob: Job? = null
    private var preparedContentRevision: Long = 0L
    private val resolvedStalkerStreamCache = LinkedHashMap<String, String>()
    private val catchupHistoryRefreshAt = LinkedHashMap<String, Long>()
    private val currentChannelEpgRefreshAt = LinkedHashMap<String, Long>()
    private val epgNetworkRefreshLock = Any()
    private val epgNetworkRefreshInFlight = LinkedHashSet<String>()

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
                val cappedSnapshot = capLargeListGuideSnapshot(
                    snapshot = snapshotToUse,
                    channelsByGroup = snapshotToUse.grouped,
                    tvSession = _uiState.value.tvSession
                )
                setUiState(
                    _uiState.value.copy(
                        isLoading = false,
                        error = null,
                        snapshot = cappedSnapshot,
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
                        System.err.println("[EPG] Startup: using stale warm cache without blocking TV page (age=${epgAgeMs / 1000}s)")
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
                            val currentSnapshot = _uiState.value.snapshot
                            // Rebuild grouped from the new channels so that all playlist
                            // groups (Norway, Sweden, Denmark …) appear immediately rather
                            // than inheriting the stale groups from the previous snapshot.
                            val freshGrouped = channels.groupBy { it.group.ifBlank { "Uncategorized" } }
                            val currentState = _uiState.value
                            val seedSnapshot = currentSnapshot.copy(
                                channels = channels,
                                grouped = freshGrouped
                            )
                            val cachedNowNext = withContext(Dispatchers.Default) {
                                val ids = if (isLargeIptvList(channels.size)) {
                                    buildPriorityEpgChannelIds(
                                        state = currentState.copy(
                                            snapshot = seedSnapshot,
                                            groups = freshGrouped.keys.toList(),
                                            channelsByGroup = freshGrouped
                                        ),
                                        maxChannels = LargeListPriorityCacheLimit
                                    )
                                } else {
                                    channels.asSequence().map { it.id }.toCollection(LinkedHashSet())
                                }
                                iptvRepository.reDeriveCachedNowNext(ids).orEmpty()
                            }
                            val nextSnapshot = seedSnapshot.copy(
                                nowNext = if (cachedNowNext.isNotEmpty()) {
                                    currentSnapshot.nowNext.toMutableMap().apply { putAll(cachedNowNext) }
                                } else {
                                    currentSnapshot.nowNext
                                }
                            )
                            val cappedSnapshot = capLargeListGuideSnapshot(
                                snapshot = nextSnapshot,
                                channelsByGroup = freshGrouped,
                                tvSession = currentState.tvSession,
                                keepChannelIds = cachedNowNext.keys
                            )
                            setUiState(
                                currentState.copy(
                                    isLoading = false,
                                    error = null,
                                    snapshot = cappedSnapshot,
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
                val currentState = _uiState.value
                if (currentState.isConfigured && snapshot.channels.isEmpty() && currentState.snapshot.channels.isNotEmpty()) {
                    System.err.println(
                        "[TV] Ignoring transient empty IPTV snapshot (configured=${
                            currentState.isConfigured
                        }) pending forced reload"
                    )
                    pendingForcedReload = true
                    // Keep the last known channels visible; avoid blanking TV page if a
                    // network or parse hiccup returns an empty result.
                    return@onSuccess
                }
                cachedEnrichedChannels = null
                cachedChannelsSignature = null
                val mergedSnapshot = mergeIncomingSnapshotWithCurrentGuide(snapshot, currentState)
                val cappedSnapshot = capLargeListGuideSnapshot(
                    snapshot = mergedSnapshot,
                    channelsByGroup = mergedSnapshot.grouped,
                    tvSession = currentState.tvSession,
                    keepChannelIds = currentState.snapshot.nowNext.keys
                )
                setUiState(
                    currentState.copy(
                        isLoading = false,
                        error = null,
                        snapshot = cappedSnapshot,
                        loadingMessage = null,
                        loadingPercent = 0
                    )
                )
                maybeWarmStartupGuide()
                startFullEpgWarmup()
                startCompleteEpgBackfill()
                warmXtreamVodCache()
            }.onFailure { error ->
                logIptvRefreshFailure(
                    error = error,
                    phase = "load_snapshot",
                    force = force,
                    forceEpg = forceEpg,
                    hasExistingChannels = hasExistingChannels
                )
                val fallback = runCatching {
                    iptvRepository.getMemoryCachedSnapshot() ?: iptvRepository.getCachedSnapshotOrNull()
                }.getOrNull()
                if (fallback != null && fallback.channels.isNotEmpty()) {
                    val currentState = _uiState.value
                    val mergedFallback = mergeIncomingSnapshotWithCurrentGuide(fallback, currentState)
                    val cappedFallback = capLargeListGuideSnapshot(
                        snapshot = mergedFallback,
                        channelsByGroup = mergedFallback.grouped,
                        tvSession = currentState.tvSession,
                        keepChannelIds = currentState.snapshot.nowNext.keys
                    )
                    setUiState(
                        currentState.copy(
                            isLoading = false,
                            error = null,
                            snapshot = cappedFallback,
                            loadingMessage = null,
                            loadingPercent = 0
                        )
                    )
                    maybeWarmStartupGuide()
                    startFullEpgWarmup()
                } else {
                    val currentState = _uiState.value
                    if (currentState.isConfigured && currentState.snapshot.channels.isNotEmpty()) {
                        // Preserve existing data on transient failures to avoid the TV page
                        // disappearing for long durations when a refresh request errors.
                        System.err.println(
                            "[TV] Keeping last known IPTV snapshot after refresh failure: ${error.message.orEmpty()}"
                        )
                        pendingForcedReload = true
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            error = error.message ?: context.getString(R.string.tv_failed_load_iptv),
                            loadingMessage = null,
                            loadingPercent = 0
                        )
                        return@onFailure
                    }
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: context.getString(R.string.tv_failed_load_iptv),
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
        val live = item.now ?: return false
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

    private fun supportsCatchup(channel: IptvChannel?): Boolean {
        if (channel == null) return false
        if (channel.catchupDays > 0) return true
        if (!channel.catchupType.isNullOrBlank() || !channel.catchupSource.isNullOrBlank()) return true
        return channel.streamUrl.contains("/timeshift/", ignoreCase = true) ||
            channel.xtreamStreamId != null ||
            channel.streamUrl.contains("/live/", ignoreCase = true)
    }

    private fun recentCatchupCount(
        item: com.arflix.tv.data.model.IptvNowNext?,
        now: Long = System.currentTimeMillis()
    ): Int {
        return item?.recent
            .orEmpty()
            .count { it.endUtcMillis <= now && it.endUtcMillis >= now - CatchupHistoryWindowMs }
    }

    private fun hasRecentCatchupHistory(
        channel: IptvChannel?,
        item: com.arflix.tv.data.model.IptvNowNext?,
        now: Long = System.currentTimeMillis()
    ): Boolean {
        if (!supportsCatchup(channel)) return true
        val targetWindowMs = catchupHistoryTargetWindowMs(channel)
        val recent = item?.recent
            .orEmpty()
            .asSequence()
            .filter { it.endUtcMillis <= now && it.endUtcMillis >= now - targetWindowMs }
            .toList()
        if (recent.size < RichCatchupRecentTarget) return false
        val oldestStart = recent.minOfOrNull { it.startUtcMillis } ?: return false
        val coveredMs = now - oldestStart
        return coveredMs >= (targetWindowMs * 3) / 4 || recent.size >= 24
    }

    private fun catchupHistoryTargetWindowMs(channel: IptvChannel?): Long {
        val days = channel?.catchupDays?.coerceIn(0, 7) ?: 0
        val hours = if (days > 0) {
            minOf(48L, days * 24L)
        } else {
            48L
        }
        return hours * 60L * 60_000L
    }

    private fun hasRecentAiredHistory(
        item: com.arflix.tv.data.model.IptvNowNext?,
        now: Long = System.currentTimeMillis()
    ): Boolean {
        val recent = item?.recent
            .orEmpty()
            .filter { it.endUtcMillis <= now && it.endUtcMillis >= now - CatchupHistoryWindowMs }
        if (recent.size < RichCatchupRecentTarget) return false
        val oldestStart = recent.minOfOrNull { it.startUtcMillis } ?: return false
        return now - oldestStart >= (CatchupHistoryWindowMs * 3) / 4 || recent.size >= 24
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
        val mergedNowNext = current.snapshot.nowNext.toMutableMap().apply {
            updated.forEach { (channelId, fresh) ->
                put(channelId, mergeGuideSlice(this[channelId], fresh))
            }
        }
        val nextSnapshot = current.snapshot.copy(nowNext = mergedNowNext)
        val cappedSnapshot = capLargeListGuideSnapshot(
            snapshot = nextSnapshot,
            channelsByGroup = current.channelsByGroup.ifEmpty { nextSnapshot.grouped },
            tvSession = current.tvSession,
            keepChannelIds = updatedIds
        )
        setUiState(
            current.copy(
                snapshot = cappedSnapshot,
                epgLoadingChannelIds = current.epgLoadingChannelIds - updatedIds,
                epgAttemptedChannelIds = capChannelStateSet(current.epgAttemptedChannelIds + updatedIds, EpgAttemptedStateLimit),
            )
        )
    }

    private fun mergeIncomingSnapshotWithCurrentGuide(
        incoming: IptvSnapshot,
        current: TvUiState
    ): IptvSnapshot {
        val retainedGuide = current.snapshot.nowNext
        if (retainedGuide.isEmpty()) return incoming
        val merged = incoming.nowNext.toMutableMap()
        retainedGuide.forEach { (channelId, retained) ->
            if (!hasProgramData(retained)) return@forEach
            val fresh = merged[channelId]
            merged[channelId] = if (fresh != null) {
                mergeGuideSlice(retained, fresh)
            } else {
                retained
            }
        }
        return incoming.copy(nowNext = merged)
    }

    private fun capLargeListGuideSnapshot(
        snapshot: IptvSnapshot,
        channelsByGroup: Map<String, List<IptvChannel>>,
        tvSession: IptvTvSessionState,
        keepChannelIds: Collection<String> = emptyList()
    ): IptvSnapshot {
        if (!isLargeIptvList(snapshot.channels.size) || snapshot.nowNext.size <= LargeListPriorityCacheLimit) {
            return snapshot
        }
        val priorityIds = buildPriorityEpgChannelIds(
            state = TvUiState(
                snapshot = snapshot,
                groups = channelsByGroup.keys.toList(),
                channelsByGroup = channelsByGroup,
                tvSession = tvSession
            ),
            maxChannels = LargeListPriorityCacheLimit
        )
        val keepIds = LinkedHashSet<String>(LargeListPriorityCacheLimit + 180)
        priorityIds.forEach(keepIds::add)
        snapshot.favoriteChannels
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .forEach { keepIds.add(it) }
        keepChannelIds
            .asSequence()
            .filter { it.isNotBlank() }
            .take(180)
            .forEach(keepIds::add)
        if (keepIds.isEmpty()) {
            return snapshot.copy(nowNext = emptyMap())
        }
        val cappedNowNext = snapshot.nowNext.filterKeys { it in keepIds }
        if (cappedNowNext.size == snapshot.nowNext.size) return snapshot
        System.err.println("[EPG-Memory] capped UI guide ${snapshot.nowNext.size} -> ${cappedNowNext.size} for ${snapshot.channels.size} channels")
        return snapshot.copy(nowNext = cappedNowNext)
    }

    private fun mergeGuideSlice(
        existing: com.arflix.tv.data.model.IptvNowNext?,
        fresh: com.arflix.tv.data.model.IptvNowNext
    ): com.arflix.tv.data.model.IptvNowNext {
        if (existing == null) return fresh
        return com.arflix.tv.data.model.IptvNowNext(
            now = fresh.now ?: existing.now,
            next = fresh.next ?: existing.next,
            later = fresh.later ?: existing.later,
            upcoming = if (fresh.upcoming.isNotEmpty()) fresh.upcoming else existing.upcoming,
            recent = if (fresh.recent.isNotEmpty()) fresh.recent else existing.recent,
        )
    }

    // Every TvUiState emission re-executes the (very large, interpreter-only)
    // LiveTvScreen composition — several hundred ms on a TV box. The per-channel
    // loading/attempted spinner bookkeeping used to emit a full state copy on each
    // transition, so one focus change produced 5-8 emissions = multi-second
    // main-thread stalls (and ANR kills under sustained d-pad navigation). For
    // large playlists we skip these purely-cosmetic emissions; guide cells simply
    // go from "pending" to populated when the data merge lands (1 emission).
    private fun shouldEmitEpgSpinnerState(): Boolean =
        !isActiveLargeIptvList()

    private fun isActiveLargeIptvList(): Boolean {
        val snapshotCount = _uiState.value.snapshot.channels.size
        if (isLargeIptvList(snapshotCount)) return true
        return runCatching { iptvRepository.pagedChannelStoreCount() }.getOrDefault(0) > 10_000
    }

    /**
     * Resolve a channel by id without scanning the full snapshot list. For large
     * playlists a `firstOrNull` over 50k+ channels ran per focus change on the main
     * thread; the SQLite channel store answers the same lookup with an indexed query.
     */
    private fun lookupChannelById(state: TvUiState, id: String): IptvChannel? {
        if (id.isBlank()) return null
        return if (isLargeIptvList(state.snapshot.channels.size) ||
            runCatching { iptvRepository.pagedChannelStoreCount() }.getOrDefault(0) > 10_000
        ) {
            iptvRepository.pagedChannelsByIds(listOf(id)).firstOrNull()
        } else {
            state.snapshot.channels.firstOrNull { it.id == id }
        }
    }

    private fun markEpgLoading(channelIds: Collection<String>) {
        if (!shouldEmitEpgSpinnerState()) return
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
        if (!shouldEmitEpgSpinnerState()) return
        val ids = channelIds.asSequence().filter { it.isNotBlank() }.toSet()
        if (ids.isEmpty()) return
        val current = _uiState.value
        setUiState(current.copy(epgLoadingChannelIds = current.epgLoadingChannelIds - ids))
    }

    private fun finishEpgAttempt(channelIds: Collection<String>) {
        if (!shouldEmitEpgSpinnerState()) return
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

    private fun claimEpgNetworkRefresh(
        channelIds: Collection<String>,
        allowLargeListFocusedRefresh: Boolean = false
    ): Set<String> {
        if (channelIds.isEmpty()) return emptySet()
        if (isActiveLargeIptvList() && !allowLargeListFocusedRefresh) {
            System.err.println("[EPG-Refresh] Skipping network prefetch for large paged TV list")
            return emptySet()
        }
        return synchronized(epgNetworkRefreshLock) {
            channelIds
                .asSequence()
                .filter { it.isNotBlank() }
                .filter { epgNetworkRefreshInFlight.add(it) }
                .toSet()
        }
    }

    private fun releaseEpgNetworkRefresh(channelIds: Collection<String>) {
        if (channelIds.isEmpty()) return
        synchronized(epgNetworkRefreshLock) {
            channelIds.forEach { epgNetworkRefreshInFlight.remove(it) }
        }
    }

    private fun setEpgBackfillInProgress(inProgress: Boolean) {
        val current = _uiState.value
        if (current.epgBackfillInProgress == inProgress) return
        setUiState(current.copy(epgBackfillInProgress = inProgress))
    }

    fun setLiveTvPlaybackActive(active: Boolean) {
        if (liveTvPlaybackActive == active) return
        liveTvPlaybackActive = active
        // The full-guide backfill is deferred while a channel preview is playing.
        // Measured reason: filling all ~10k guide-capable channels' EPG concurrently
        // with an interactive UI exceeds this device's 384MB heap cap and crashes
        // during navigation. Full all-channels coverage at this scale needs the
        // backend EPG service; on-device we keep visible/priority-channel EPG, which
        // stays within budget. (The backfill that DOES run when playback is idle now
        // streams in bounded batches — see fetchXtreamFullEpg — so it can't OOM.)
        if (active) {
            deferredCompleteEpgBackfillJob?.cancel()
            deferredCompleteEpgBackfillJob = null
            if (completeEpgBackfillJob?.isActive == true) {
                deferredCompleteEpgBackfill = true
                lastCompleteEpgBackfillKey = null
                System.err.println("[EPG-Complete] Pausing full guide backfill because live playback started")
                completeEpgBackfillJob?.cancel()
                setEpgBackfillInProgress(false)
            }
        } else {
            scheduleDeferredCompleteEpgBackfill()
        }
    }

    private fun deferCompleteEpgBackfill(priorityChannelIds: Collection<String>) {
        deferredCompleteEpgBackfill = true
        priorityChannelIds
            .asSequence()
            .filter { it.isNotBlank() }
            .take(StandardPriorityEpgLimit - deferredCompleteEpgPriorityIds.size)
            .forEach(deferredCompleteEpgPriorityIds::add)
        setEpgBackfillInProgress(false)
        System.err.println("[EPG-Complete] Deferred full guide backfill while live playback is active")
    }

    private fun scheduleDeferredCompleteEpgBackfill() {
        if (!deferredCompleteEpgBackfill) return
        if (completeEpgBackfillJob?.isActive == true) return
        deferredCompleteEpgBackfillJob?.cancel()
        deferredCompleteEpgBackfillJob = viewModelScope.launch {
            delay(PlaybackEpgBackfillResumeDelayMs)
            if (liveTvPlaybackActive) return@launch
            val priorityIds = deferredCompleteEpgPriorityIds.toList()
            deferredCompleteEpgPriorityIds.clear()
            deferredCompleteEpgBackfill = false
            System.err.println("[EPG-Complete] Resuming deferred full guide backfill after playback idle")
            startCompleteEpgBackfill(force = true, priorityChannelIds = priorityIds)
        }.also { job ->
            job.invokeOnCompletion {
                if (deferredCompleteEpgBackfillJob === job) {
                    deferredCompleteEpgBackfillJob = null
                }
            }
        }
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
        val largeList = isActiveLargeIptvList()
        if (!state.isConfigured || channels.isEmpty()) return
        if (!hasNetworkEpgSource(state.config)) return
        if (!force && channels.size <= 1) {
            AppLogger.breadcrumb(
                tag = "IPTV",
                message = "complete_epg_skipped_sparse_snapshot channels=${channels.size} now_next=${state.snapshot.nowNext.size}",
                severity = "info"
            )
            return
        }
        if (!force && channels.none { channel ->
                !channel.epgId.isNullOrBlank() || !channel.tvgName.isNullOrBlank()
            }
        ) {
            AppLogger.breadcrumb(
                tag = "IPTV",
                message = "complete_epg_skipped_no_channel_identity channels=${channels.size}",
                severity = "info"
            )
            return
        }
        if (liveTvPlaybackActive) {
            deferCompleteEpgBackfill(priorityChannelIds)
            return
        }
        if (largeList) {
            setEpgBackfillInProgress(false)
            System.err.println("[EPG-Complete] Skipping on-device full guide backfill for large playlist; visible guide loads on demand")
            return
        }
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
                "[EPG-Complete] Scheduling large-list full guide index: " +
                    "channels=${channels.size} guideCapable=$guideCapableChannels " +
                    "indexed=$indexedGuideChannels programs=$indexedGuidePrograms " +
                    "coverage=${(indexedCoverage * 100).toInt()}% age=${ageMs / 1000}s " +
                    "delay=${if (force) 0 else LargeListCompleteEpgBackfillStartupDelayMs / 1000}s"
            )
        }
        completeEpgBackfillJob = viewModelScope.launch(Dispatchers.IO) {
            val startupDelay = when {
                largeList && !force -> LargeListCompleteEpgBackfillStartupDelayMs
                largeList -> 0L
                hasGuideData -> 2_000L
                else -> 250L
            }
            delay(startupDelay)
            if (liveTvPlaybackActive) {
                deferCompleteEpgBackfill(priorityChannelIds)
                return@launch
            }
            if (largeList) {
                System.err.println("[EPG-Complete] Starting large-list full guide index after idle delay")
            }
            setEpgBackfillInProgress(true)
            val snapshot = try {
                kotlinx.coroutines.withTimeoutOrNull(900_000L) {
                    iptvRepository.loadSnapshot(
                        forcePlaylistReload = false,
                        forceEpgReload = true,
                        allowNetworkEpgFetch = true,
                        allowBroadShortEpg = !largeList,
                        onProgress = { progress ->
                            System.err.println("[EPG-Complete] ${progress.message} ${progress.percent ?: ""}".trim())
                        }
                    )
                }
            } catch (error: Throwable) {
                if (error is kotlinx.coroutines.CancellationException) {
                    System.err.println("[EPG-Complete] Full guide backfill cancelled")
                    throw error
                }
                AppLogger.recordException(
                    throwable = error,
                    context = mapOf(
                        "error_area" to "IPTV",
                        "iptv_phase" to "complete_epg_backfill",
                        "channel_count" to countBucket(channels.size),
                        "start_coverage_pct" to ((coverage * 100).toInt()).toString()
                    )
                )
                null
            }
            if (snapshot == null) {
                lastCompleteEpgBackfillKey = null
                AppLogger.breadcrumb(
                    tag = "IPTV",
                    message = "complete_epg_backfill_timeout channel_count=${countBucket(channels.size)} start_coverage_pct=${(coverage * 100).toInt()}",
                    severity = "warning"
                )
                return@launch
            }

            if (snapshot.channels.isEmpty() || snapshot.nowNext.isEmpty()) {
                lastCompleteEpgBackfillKey = null
                AppLogger.breadcrumb(
                    tag = "IPTV",
                    message = "complete_epg_backfill_empty channel_count=${countBucket(channels.size)} snapshot_channels=${snapshot.channels.size} snapshot_now_next=${snapshot.nowNext.size}",
                    severity = "warning"
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
        val isLargeList = isActiveLargeIptvList()
        val now = System.currentTimeMillis()
        if (now - lastVisibleForcedCompleteEpgAt < 60_000L) return
        lastVisibleForcedCompleteEpgAt = now
        if (isLargeList) {
            System.err.println("[EPG-Complete] Visible guide unresolved on large paged list; skip on-device XMLTV during TV page")
            return
        }
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
        backgroundLimit: Int = 640,
        allowFocusedNetworkRefresh: Boolean = false
    ) {
        if (channelIds.isEmpty()) return
        val largeList = isActiveLargeIptvList()
        val firstPaintLimit = if (largeList) 8 else 28
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
        val favoriteIds = currentState.snapshot.favoriteChannels
            .toHashSet()
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
        val requestLimit = maxOf(firstPaintLimit, eagerLimit, backgroundLimit)
            .coerceAtMost(orderedIds.size)
            .coerceAtMost(if (largeList) 36 else 240)
        val missingIds = orderedIds
            .filterNot { id ->
                hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id])
            }
            .take(requestLimit)
        if (missingIds.isEmpty()) return

        if (largeList) {
            viewModelScope.launch {
                val firstPaintIds = missingIds
                    .take(maxOf(firstPaintLimit, eagerLimit).coerceAtMost(orderedIds.size))
                    .toCollection(LinkedHashSet())
                val startedAt = System.currentTimeMillis()
                markEpgLoading(firstPaintIds)
                refreshGuideFromCache(firstPaintIds)
                val resolved = firstPaintIds.count { id ->
                    hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id])
                }
                System.err.println(
                    "[EPG-StartupCache] visible=$resolved/${firstPaintIds.size} " +
                        "selected=${selectedId.orEmpty()} in ${System.currentTimeMillis() - startedAt}ms"
                )
                val unresolved = firstPaintIds
                    .filterNot { id -> hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) }
                    .toCollection(LinkedHashSet())
                val favoriteUnresolved = unresolved
                    .asSequence()
                    .filter { favoriteIds.contains(it) }
                    .take(LargeListFavoriteEpgPasses)
                    .toCollection(LinkedHashSet())
                if (favoriteUnresolved.isNotEmpty()) {
                    System.err.println(
                        "[EPG-StartupCache] large favorite network refresh=${favoriteUnresolved.size}/${unresolved.size}"
                    )
                    val claimedIds = claimEpgNetworkRefresh(
                        favoriteUnresolved,
                        allowLargeListFocusedRefresh = true
                    )
                    val refreshed = if (claimedIds.isNotEmpty()) {
                        try {
                            withContext(Dispatchers.IO) {
                                runCatching {
                                    iptvRepository.refreshEpgForChannels(
                                        claimedIds,
                                        maxChannels = claimedIds.size,
                                        preferFullCatchupHistory = false
                                    )
                                }.getOrNull()
                            }
                        } finally {
                            releaseEpgNetworkRefresh(claimedIds)
                        }
                    } else {
                        null
                    }
                    if (!refreshed.isNullOrEmpty()) {
                        mergeNowNext(refreshed)
                    }
                }
                val remainingUnresolved = unresolved - favoriteUnresolved
                val focusedRefreshIds = if (allowFocusedNetworkRefresh) {
                    remainingUnresolved
                } else if (remainingUnresolved.isNotEmpty()) {
                    // A large IPTV list can have an empty local EPG index on the
                    // first run after migration. Fetch a tiny visible batch so the
                    // guide does not sit on "Guide pending" forever, but keep it
                    // capped to avoid hurting navigation/playback.
                    remainingUnresolved.take(LargeListFocusedNetworkEpgLimit)
                } else {
                    emptyList()
                }
                if (focusedRefreshIds.isNotEmpty()) {
                    System.err.println(
                        "[EPG-StartupCache] focused network refresh=${focusedRefreshIds.size}/${unresolved.size}"
                    )
                    val claimedIds = claimEpgNetworkRefresh(
                        focusedRefreshIds,
                        allowLargeListFocusedRefresh = true
                    )
                    val refreshed = if (claimedIds.isNotEmpty()) {
                        try {
                            withContext(Dispatchers.IO) {
                                runCatching {
                                    iptvRepository.refreshEpgForChannels(
                                        claimedIds,
                                        maxChannels = claimedIds.size,
                                        preferFullCatchupHistory = false
                                    )
                                }.getOrNull()
                            }
                        } finally {
                            releaseEpgNetworkRefresh(claimedIds)
                        }
                    } else {
                        null
                    }
                    if (!refreshed.isNullOrEmpty()) {
                        mergeNowNext(refreshed)
                    }
                }
                val delayedFavoritePass = orderedIds
                    .asSequence()
                    .filter { favoriteIds.contains(it) }
                    .filterNot { it in firstPaintIds }
                    .filterNot { hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[it]) }
                    .take(LargeListFavoritePrefetchLookahead)
                    .toList()
                if (delayedFavoritePass.isNotEmpty()) {
                    enqueueVisibleEpgRefresh(delayedFavoritePass, selectedId)
                }
                finishEpgAttempt(firstPaintIds.filterNot { id -> hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) })
                clearEpgLoading(firstPaintIds)
            }
            return
        }

        markEpgLoading(missingIds)
        enqueueVisibleEpgRefresh(missingIds, selectedId)
    }

    fun refreshCurrentChannelEpg(channelId: String?, forceNetworkForLargeList: Boolean = false) {
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
            val afterCacheState = _uiState.value
            val channel = afterCacheState.channelLookup[id]
                ?: lookupChannelById(afterCacheState, id)
            val cachedGuide = afterCacheState.snapshot.nowNext[id]
            val largeList = isActiveLargeIptvList()
            val currentFavoriteIds = afterCacheState.snapshot.favoriteChannels.toSet()
            val needsCatchupHistory = supportsCatchup(channel) && !hasRecentCatchupHistory(channel, cachedGuide)
            val needsAiredHistory = !largeList && !hasRecentAiredHistory(cachedGuide)
            val needsFullHistory = needsCatchupHistory || needsAiredHistory
            if (largeList) {
                val cacheGuide = afterCacheState.snapshot.nowNext[id]
                val shouldNetwork = forceNetworkForLargeList || currentFavoriteIds.contains(id)
                System.err.println(
                    "[EPG-Current] largeList channel=$id cached=${hasUsefulVisibleGuideData(cacheGuide)} " +
                        "force=$forceNetworkForLargeList fav=${currentFavoriteIds.contains(id)}"
                )
                if (!shouldNetwork || (hasRichSelectedGuideData(cacheGuide) && !needsFullHistory)) {
                    clearEpgLoading(setOf(id))
                    return@launch
                }
            }
            if (hasRichSelectedGuideData(cachedGuide) && !needsFullHistory) {
                clearEpgLoading(setOf(id))
                return@launch
            }
            System.err.println(
                "[EPG-Current] refreshing channel=$id fullHistory=$needsFullHistory " +
                    "catchup=$needsCatchupHistory aired=$needsAiredHistory recent=${recentCatchupCount(cachedGuide)}"
            )
            val claimedIds = claimEpgNetworkRefresh(
                setOf(id),
                allowLargeListFocusedRefresh = forceNetworkForLargeList || currentFavoriteIds.contains(id)
            )
            if (claimedIds.isEmpty()) {
                clearEpgLoading(setOf(id))
                return@launch
            }
            val refreshed = try {
                withContext(Dispatchers.IO) {
                    runCatching {
                        iptvRepository.refreshEpgForChannels(
                            channelIds = claimedIds,
                            maxChannels = 1,
                            preferFullCatchupHistory = needsFullHistory
                        )
                    }.getOrNull()
                }
            } finally {
                releaseEpgNetworkRefresh(claimedIds)
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
        val channel = current.channelLookup[id]
            ?: lookupChannelById(current, id)
        if (!supportsCatchup(channel)) return
        if (hasRecentCatchupHistory(channel, current.snapshot.nowNext[id], now)) return
        val lastRefreshAt = catchupHistoryRefreshAt[id] ?: 0L
        if (now - lastRefreshAt < RichCatchupRefreshThrottleMs) return

        catchupHistoryRefreshAt[id] = now
        while (catchupHistoryRefreshAt.size > 120) {
            val firstKey = catchupHistoryRefreshAt.keys.firstOrNull() ?: break
            catchupHistoryRefreshAt.remove(firstKey)
        }

        markEpgLoading(setOf(id))
        viewModelScope.launch {
            System.err.println(
                "[EPG-Catchup] refreshing history channel=$id " +
                    "recent=${recentCatchupCount(current.snapshot.nowNext[id], now)}"
            )
            refreshGuideFromCache(setOf(id))
            val afterCache = _uiState.value
            val afterCacheChannel = afterCache.channelLookup[id]
                ?: lookupChannelById(afterCache, id)
            if (hasRecentCatchupHistory(afterCacheChannel, afterCache.snapshot.nowNext[id])) {
                System.err.println(
                    "[EPG-Catchup] cache satisfied channel=$id " +
                        "recent=${recentCatchupCount(afterCache.snapshot.nowNext[id])}"
                )
                clearEpgLoading(setOf(id))
                return@launch
            }
            val claimedIds = claimEpgNetworkRefresh(
                setOf(id),
                allowLargeListFocusedRefresh = true
            )
            if (claimedIds.isEmpty()) {
                clearEpgLoading(setOf(id))
                return@launch
            }
            val refreshed = try {
                withContext(Dispatchers.IO) {
                    runCatching {
                        iptvRepository.refreshEpgForChannels(
                            channelIds = claimedIds,
                            maxChannels = 1,
                            preferFullCatchupHistory = true
                        )
                    }.getOrNull()
                }
            } finally {
                releaseEpgNetworkRefresh(claimedIds)
            }
            if (!refreshed.isNullOrEmpty()) {
                System.err.println(
                    "[EPG-Catchup] refreshed channel=$id keys=${refreshed.size} " +
                        "recent=${recentCatchupCount(refreshed[id])}"
                )
                mergeNowNext(refreshed)
            } else {
                System.err.println("[EPG-Catchup] no history returned channel=$id")
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
                        0 -> 18
                        1 -> 32
                        else -> 48
                    }
                )
                val batch = drain.ids
                if (batch.isEmpty()) break

                val batchSet = batch.toCollection(LinkedHashSet())
                markEpgLoading(batchSet)
                runCatching {
                    val activeLargeList = isActiveLargeIptvList()
                    val cacheLoaded = kotlinx.coroutines.withTimeoutOrNull(if (activeLargeList) 900L else 1_800L) {
                        refreshGuideFromCache(batchSet)
                        true
                    } == true
                    if (!cacheLoaded) {
                        System.err.println("[EPG-Category] cache lookup skipped after timeout for ${batchSet.size} visible channels")
                    }
                    if (activeLargeList) {
                        val resolved = batchSet.count { id -> hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) }
                        System.err.println("[EPG-Category] large cache-only resolved=$resolved/${batchSet.size}")
                        val unresolved = batchSet
                            .filterNot { id -> hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) }
                            .toCollection(LinkedHashSet())
                        if (unresolved.isNotEmpty() && batchSet.size <= LargeListFocusedNetworkEpgLimit) {
                            System.err.println("[EPG-Category] large focused network refresh=${unresolved.size}")
                            val claimedIds = claimEpgNetworkRefresh(
                                unresolved,
                                allowLargeListFocusedRefresh = true
                            )
                            val refreshed = if (claimedIds.isNotEmpty()) {
                                try {
                                    withContext(Dispatchers.IO) {
                                        runCatching {
                                            iptvRepository.refreshEpgForChannels(
                                                claimedIds,
                                                maxChannels = claimedIds.size,
                                                preferFullCatchupHistory = false
                                            )
                                        }.getOrNull()
                                    }
                                } finally {
                                    releaseEpgNetworkRefresh(claimedIds)
                                }
                            } else {
                                null
                            }
                            if (!refreshed.isNullOrEmpty()) {
                                mergeNowNext(refreshed)
                            }
                        }
                        finishEpgAttempt(batchSet.filterNot { id -> hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) })
                        return@runCatching
                    }

                    val selectedMissingId = drain.selectedId
                        ?.takeIf { id -> id in batchSet && !hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) }
                    if (selectedMissingId != null) {
                        System.err.println("[EPG-Category] selected-first channel=$selectedMissingId pass=$pass")
                        val claimedIds = claimEpgNetworkRefresh(setOf(selectedMissingId))
                        val selectedRefresh = if (claimedIds.isNotEmpty()) {
                            try {
                                withContext(Dispatchers.IO) {
                                    runCatching {
                                        iptvRepository.refreshEpgForChannels(
                                            claimedIds,
                                            maxChannels = 1,
                                            preferFullCatchupHistory = false
                                        )
                                    }.getOrNull()
                                }
                            } finally {
                                releaseEpgNetworkRefresh(claimedIds)
                            }
                        } else {
                            null
                        }
                        if (!selectedRefresh.isNullOrEmpty()) {
                            mergeNowNext(selectedRefresh)
                        }
                    }

                    val missingIds = batch
                        .filterNot { id -> hasUsefulVisibleGuideData(_uiState.value.snapshot.nowNext[id]) }
                    if (missingIds.isNotEmpty()) {
                        System.err.println("[EPG-Category] queued=${missingIds.size} pass=$pass selected=${drain.selectedId.orEmpty()}")
                        val claimedIds = claimEpgNetworkRefresh(missingIds.toSet())
                        val refreshed = if (claimedIds.isNotEmpty()) {
                            try {
                                withContext(Dispatchers.IO) {
                                    runCatching {
                                        iptvRepository.refreshEpgForChannels(
                                            claimedIds,
                                            maxChannels = claimedIds.size,
                                            preferFullCatchupHistory = false
                                        )
                                    }.getOrNull()
                                }
                            } finally {
                                releaseEpgNetworkRefresh(claimedIds)
                            }
                        } else {
                            null
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
                        if (isActiveLargeIptvList()) {
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
        moveGroupUp(null, groupName)
    }

    fun moveGroupUp(playlistId: String?, groupName: String) {
        viewModelScope.launch {
            val targetPlaylistId = playlistId?.trim().orEmpty()
            if (targetPlaylistId.isNotBlank()) {
                iptvRepository.moveGroupUp(targetPlaylistId, groupName, currentVisiblePlaylistGroups(targetPlaylistId))
            } else {
                val config = iptvRepository.observeConfig().first()
                val activePlaylists = config.playlists.filter { it.enabled }.map { it.id }
                activePlaylists.forEach { activePlaylistId ->
                    iptvRepository.moveGroupUp(activePlaylistId, groupName, currentVisiblePlaylistGroups(activePlaylistId))
                }
            }
            scheduleIptvCloudSync()
        }
    }

    fun moveGroupToTop(groupName: String) {
        moveGroupToTop(null, groupName)
    }

    fun moveGroupToTop(playlistId: String?, groupName: String) {
        viewModelScope.launch {
            val targetPlaylistId = playlistId?.trim().orEmpty()
            if (targetPlaylistId.isNotBlank()) {
                iptvRepository.moveGroupToTop(targetPlaylistId, groupName, currentVisiblePlaylistGroups(targetPlaylistId))
            } else {
                val config = iptvRepository.observeConfig().first()
                val activePlaylists = config.playlists.filter { it.enabled }.map { it.id }
                activePlaylists.forEach { activePlaylistId ->
                    iptvRepository.moveGroupToTop(activePlaylistId, groupName, currentVisiblePlaylistGroups(activePlaylistId))
                }
            }
            scheduleIptvCloudSync()
        }
    }

    fun moveGroupDown(groupName: String) {
        moveGroupDown(null, groupName)
    }

    fun moveGroupDown(playlistId: String?, groupName: String) {
        viewModelScope.launch {
            val targetPlaylistId = playlistId?.trim().orEmpty()
            if (targetPlaylistId.isNotBlank()) {
                iptvRepository.moveGroupDown(targetPlaylistId, groupName, currentVisiblePlaylistGroups(targetPlaylistId))
            } else {
                val config = iptvRepository.observeConfig().first()
                val activePlaylists = config.playlists.filter { it.enabled }.map { it.id }
                activePlaylists.forEach { activePlaylistId ->
                    iptvRepository.moveGroupDown(activePlaylistId, groupName, currentVisiblePlaylistGroups(activePlaylistId))
                }
            }
            scheduleIptvCloudSync()
        }
    }

    private fun currentVisiblePlaylistGroups(playlistId: String? = null): List<String> {
        val targetPlaylistId = playlistId?.trim().orEmpty()
        if (targetPlaylistId.isNotBlank() && iptvRepository.pagedChannelsReady()) {
            val hidden = _uiState.value.snapshot.hiddenGroups.mapTo(HashSet()) { it.trim() }
            return iptvRepository.pagedPlaylistGroupCounts()
                .asSequence()
                .filter { (id, _, _) -> id == targetPlaylistId }
                .map { (_, group, _) -> group.trim() }
                .filter { it.isNotBlank() && it !in hidden }
                .distinct()
                .toList()
        }
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

    private fun logIptvRefreshFailure(
        error: Throwable,
        phase: String,
        force: Boolean,
        forceEpg: Boolean,
        hasExistingChannels: Boolean
    ) {
        val message = error.message.orEmpty()
        val expected = message.contains("IPTV load timed out", ignoreCase = true) ||
            message.contains("Playlist loaded but contains no channels", ignoreCase = true) ||
            message.contains("Xtream provider timed out", ignoreCase = true)

        if (expected) {
            AppLogger.breadcrumb(
                tag = "IPTV",
                message = "$phase soft_fail force=$force force_epg=$forceEpg had_existing=$hasExistingChannels reason=${error::class.java.simpleName}",
                severity = "warning"
            )
            return
        }

        AppLogger.recordException(
            throwable = error,
            context = mapOf(
                "error_area" to "IPTV",
                "iptv_phase" to phase,
                "force_playlist_reload" to force.toString(),
                "force_epg_reload" to forceEpg.toString(),
                "had_existing_channels" to hasExistingChannels.toString()
            )
        )
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
        val largeList = isActiveLargeIptvList()

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
            val firstAttempt = runCatching { cloudSyncRepository.pushToCloud(force = true) }.getOrNull()
            if (firstAttempt?.isFailure != false) {
                kotlinx.coroutines.delay(1_200L)
                runCatching { cloudSyncRepository.pushToCloud(force = true) }
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
