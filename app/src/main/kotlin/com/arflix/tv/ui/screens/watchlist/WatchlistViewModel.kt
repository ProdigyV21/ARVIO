package com.arflix.tv.ui.screens.watchlist

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType.MOVIE
import com.arflix.tv.data.model.MediaType.TV
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.HomeServerCatalogCandidate
import com.arflix.tv.data.repository.HomeServerKind
import com.arflix.tv.data.repository.HomeServerLibrarySort
import com.arflix.tv.data.repository.HomeServerRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryEntry
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.data.repository.simkl.SimklAuthManager
import com.arflix.tv.data.repository.simkl.SimklSyncService
import com.arflix.tv.util.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject
import java.util.Locale

enum class ToastType {
    SUCCESS, ERROR, INFO
}

enum class TrackerLibraryProvider(val displayName: String) {
    TRAKT("Trakt"),
    SIMKL("Simkl")
}

sealed interface WatchlistSourceItem {
    val id: String
    val title: String
    val subtitle: String?
    val displayLabel: String

    data object MyWatchlist : WatchlistSourceItem {
        override val id: String = "my_watchlist"
        override val title: String = "My watchlist"
        override val subtitle: String? = null
        override val displayLabel: String = "My watchlist"
    }

    data class Catalog(
        val config: CatalogConfig
    ) : WatchlistSourceItem {
        override val id: String = "catalog_${config.id}"
        override val title: String = config.title
        override val subtitle: String? = when {
            config.sourceType == CatalogSourceType.TRAKT -> "Trakt"
            config.sourceType == CatalogSourceType.MDBLIST -> "MDBList"
            config.sourceType == CatalogSourceType.ADDON -> config.addonName ?: "Addon"
            config.sourceUrl?.contains("simkl", ignoreCase = true) == true -> "Simkl"
            else -> null
        }
        override val displayLabel: String = if (subtitle != null && !title.startsWith(subtitle, ignoreCase = true)) {
            "$subtitle / $title"
        } else {
            title
        }
    }

    data class HomeServer(
        val candidate: HomeServerCatalogCandidate
    ) : WatchlistSourceItem {
        override val id: String = "server_${candidate.sourceRef}"
        override val title: String = candidate.collectionName.ifBlank { candidate.title }
        override val subtitle: String = candidate.serverName
        override val displayLabel: String = if (subtitle.isNotBlank() && !title.startsWith(subtitle, ignoreCase = true)) {
            "$subtitle / $title"
        } else {
            title
        }
    }

    data class TrackerList(
        val provider: TrackerLibraryProvider,
        val listKey: String,
        override val title: String,
        @param:StringRes val titleRes: Int? = null
    ) : WatchlistSourceItem {
        override val id: String = "tracker_${provider.name.lowercase()}_$listKey"
        override val subtitle: String = provider.displayName
        override val displayLabel: String = title
    }
}

data class WatchlistUiState(
    val sources: List<WatchlistSourceItem> = listOf(WatchlistSourceItem.MyWatchlist),
    val selectedSourceId: String = WatchlistSourceItem.MyWatchlist.id,
    val isLoading: Boolean = true,
    val movies: List<MediaItem> = emptyList(),
    val series: List<MediaItem> = emptyList(),
    val error: String? = null,
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.INFO,
    val lastFocusedSectionIndex: Int = 0,
    val lastFocusedItemIndex: Int = 0,
    val hasMore: Boolean = false,
    val isLoadingMore: Boolean = false
) {
    val isEmpty: Boolean get() = movies.isEmpty() && series.isEmpty()
    val allItems: List<MediaItem> get() = movies + series
    val selectedSource: WatchlistSourceItem get() = sources.firstOrNull { it.id == selectedSourceId } ?: WatchlistSourceItem.MyWatchlist
}

data class HomeLibraryUiState(
    val providers: List<HomeServerKind> = emptyList(),
    val libraries: List<HomeServerCatalogCandidate> = emptyList(),
    val selectedProvider: HomeServerKind? = null,
    val selectedSourceRef: String? = null,
    val items: List<MediaItem> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMore: Boolean = false,
    val sort: HomeServerLibrarySort = HomeServerLibrarySort.RECENTLY_ADDED,
    val searchQuery: String = "",
    val error: String? = null
)

internal fun watchlistLogoKey(item: MediaItem): String {
    return if (item.id > 0) {
        "tmdb:${item.mediaType.name}:${item.id}"
    } else {
        "home:${item.homeServerSourceRef.orEmpty()}:${item.homeServerItemId.orEmpty()}:${item.mediaType.name}"
    }
}

internal fun watchlistItemKey(item: MediaItem, index: Int): String {
    val nativeIdentity = if (item.isHomeServer || !item.homeServerItemId.isNullOrBlank()) {
        "home:${item.homeServerSourceRef.orEmpty()}:${item.homeServerItemId.orEmpty()}"
    } else {
        "media:${item.mediaType.name}:${item.id}"
    }
    return "$nativeIdentity:$index"
}

private data class SourcePageState(
    val hasMore: Boolean = false,
    val isLoadingMore: Boolean = false,
    val nextOffset: Int = 0
)

internal fun buildWatchlistSources(
    catalogs: List<CatalogConfig>,
    homeServerCandidates: List<HomeServerCatalogCandidate>,
    trackerLists: List<WatchlistSourceItem.TrackerList> = emptyList()
): List<WatchlistSourceItem> {
    val customCatalogs = catalogs.filter { config ->
        !config.isPreinstalled &&
            config.kind != CatalogKind.COLLECTION &&
            config.kind != CatalogKind.COLLECTION_RAIL &&
            config.sourceType != CatalogSourceType.PREINSTALLED &&
            config.sourceType != CatalogSourceType.HOME_SERVER
    }.map { WatchlistSourceItem.Catalog(it) }
    val homeServers = homeServerCandidates.map { WatchlistSourceItem.HomeServer(it) }
    return (listOf<WatchlistSourceItem>(WatchlistSourceItem.MyWatchlist) + homeServers + trackerLists + customCatalogs)
        .distinctBy(WatchlistSourceItem::id)
}

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val watchlistRepository: WatchlistRepository,
    private val cloudSyncRepository: CloudSyncRepository,
    private val traktRepository: TraktRepository,
    private val remoteSyncManager: com.arflix.tv.data.repository.sync.RemoteSyncManager,
    private val mediaRepository: MediaRepository,
    private val homeServerRepository: HomeServerRepository,
    private val catalogRepository: CatalogRepository,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val simklAuthManager: SimklAuthManager,
    private val simklSyncService: SimklSyncService,
    private val profileManager: ProfileManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(WatchlistUiState())
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()

    private val _logoUrls = MutableStateFlow<Map<String, String>>(emptyMap())
    val logoUrls: StateFlow<Map<String, String>> = _logoUrls.asStateFlow()

    private val _libraryState = MutableStateFlow(HomeLibraryUiState())
    val libraryState: StateFlow<HomeLibraryUiState> = _libraryState.asStateFlow()
    private val libraryCache = linkedMapOf<String, Pair<List<MediaItem>, Boolean>>()
    private var libraryLoadJob: Job? = null
    private var librarySearchJob: Job? = null
    private var libraryRequestId = 0

    private val sourceItemsCache = linkedMapOf<String, List<MediaItem>>()
    private val sourcePageStates = mutableMapOf<String, SourcePageState>()
    private var currentCatalogs: List<CatalogConfig> = emptyList()
    private var currentHomeServerCandidates: List<HomeServerCatalogCandidate> = emptyList()
    private var currentTrackerLists: List<WatchlistSourceItem.TrackerList> = emptyList()
    private var sourceLoadJob: Job? = null
    private var sourceLoadMoreJob: Job? = null
    private var traktSyncInFlight = false
    private var initialLoadComplete = false
    private var enrichmentInFlight = false
    private var enrichmentRequested = false
    private val logoRequestsInFlight = mutableSetOf<String>()

    private fun watchlistDiagnosticContext(
        phase: String,
        extra: Map<String, String> = emptyMap()
    ): Map<String, String> = mutableMapOf(
        "error_area" to "Watchlist",
        "watchlist_phase" to phase,
        "visible_count" to _uiState.value.allItems.size.toString()
    ).apply { putAll(extra) }

    private fun List<MediaItem>.watchlistDisplayOrder(): List<MediaItem> {
        return sortedWith(
            compareBy<MediaItem> { it.sourceOrder }
                .thenByDescending { it.addedAt }
        )
    }

    private fun List<MediaItem>.needsArtworkEnrichment(): Boolean {
        return any { item -> item.image.isBlank() && item.backdrop.isNullOrBlank() }
    }

    init {
        observeWatchlistChanges()
        observeCatalogsAndHomeServers()
        observeTrackerLibraries()
        loadWatchlistInstant()
    }

    private fun observeWatchlistChanges() {
        viewModelScope.launch {
            watchlistRepository.watchlistItems.collect { items ->
                if (traktSyncInFlight) return@collect
                if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                    val orderedItems = items.watchlistDisplayOrder().enrichWithPlaybackProgress()
                    sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = orderedItems
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = orderedItems.filter { it.mediaType == MOVIE },
                        series = orderedItems.filter { it.mediaType == TV },
                        error = null
                    )
                    fetchLogos(orderedItems)
                }
            }
        }
    }

    private fun observeCatalogsAndHomeServers() {
        viewModelScope.launch {
            catalogRepository.observeCatalogs().collect { catalogs ->
                updateAvailableSources(catalogs = catalogs)
            }
        }
        viewModelScope.launch {
            homeServerRepository.connections.collect { connections ->
                val usable = connections.filter { it.isUsable }.map { it.serverKind }.distinct()
                // Box-set discovery is for catalog settings, not the Library sidebar.
                // It can take multiple server timeouts and its results are not used here.
                val candidates = homeServerRepository.getSavedCatalogCandidates(connections)
                    .filter { it.collectionType.trim().lowercase(Locale.ROOT) in BROWSABLE_LIBRARY_TYPES }
                updateHomeLibraryState(usable, candidates)
                updateAvailableSources(homeServerCandidates = candidates)
            }
        }
    }

    private fun observeTrackerLibraries() {
        viewModelScope.launch {
            profileManager.activeProfileId
                .combine(traktRepository.isAuthenticated) { profileId, traktConnected ->
                    profileId to traktConnected
                }
                .distinctUntilChanged()
                .collectLatest { (_, traktConnected) ->
                    sourceLoadJob?.cancel()
                    sourceLoadMoreJob?.cancel()
                    sourceItemsCache.keys.removeAll { it.startsWith("tracker_") }
                    sourcePageStates.keys.removeAll { it.startsWith("tracker_") }
                    currentTrackerLists = emptyList()
                    updateAvailableSources()
                    refreshTrackerLibraries(traktConnected)
                }
        }
    }

    private suspend fun refreshTrackerLibraries(traktConnected: Boolean) {
        val simklConnected = runCatching { simklAuthManager.isConnected() }.getOrDefault(false)
        val trackerLists = buildList {
            if (traktConnected) {
                val personalLists = runCatching { traktRepository.getPersonalLists() }
                    .getOrDefault(emptyList())
                if (personalLists.isEmpty()) {
                    add(
                        WatchlistSourceItem.TrackerList(
                            provider = TrackerLibraryProvider.TRAKT,
                            listKey = TRAKT_WATCHLIST_KEY,
                            title = "Watchlist",
                            titleRes = R.string.watchlist_tracker_default_list
                        )
                    )
                } else {
                    personalLists.forEach { list ->
                        add(
                            WatchlistSourceItem.TrackerList(
                                provider = TrackerLibraryProvider.TRAKT,
                                listKey = list.id,
                                title = list.title
                            )
                        )
                    }
                }
            }
            if (simklConnected) {
                SIMKL_LIBRARY_LISTS.forEach { (status, title, titleRes) ->
                    add(
                        WatchlistSourceItem.TrackerList(
                            provider = TrackerLibraryProvider.SIMKL,
                            listKey = status,
                            title = title,
                            titleRes = titleRes
                        )
                    )
                }
            }
        }

        currentTrackerLists = trackerLists
        updateAvailableSources()
    }

    private fun updateHomeLibraryState(
        usableProviders: List<HomeServerKind>,
        candidates: List<HomeServerCatalogCandidate>
    ) {
        val providers = usableProviders.distinct().sortedBy { kind ->
            when (kind) {
                HomeServerKind.PLEX -> 0
                HomeServerKind.JELLYFIN -> 1
                HomeServerKind.EMBY -> 2
                HomeServerKind.UNKNOWN -> 3
            }
        }
        val current = _libraryState.value
        val selectedProvider = current.selectedProvider?.takeIf { it in providers }
        val selectedSource = current.selectedSourceRef?.takeIf { source ->
            candidates.any { it.sourceRef == source && it.serverKind == selectedProvider }
        } ?: candidates.firstOrNull { it.serverKind == selectedProvider }?.sourceRef
        val selectionChanged = current.selectedSourceRef != selectedSource || current.selectedProvider != selectedProvider
        libraryCache.keys.removeAll { key -> candidates.none { key.startsWith("${it.sourceRef}|") } }
        if (selectionChanged) {
            libraryRequestId++
            libraryLoadJob?.cancel()
            librarySearchJob?.cancel()
        }
        _libraryState.value = current.copy(
            providers = providers,
            libraries = candidates,
            selectedProvider = selectedProvider,
            selectedSourceRef = selectedSource,
            items = if (selectionChanged || selectedProvider == null) emptyList() else current.items,
            isLoading = if (selectionChanged) selectedSource != null else current.isLoading,
            isLoadingMore = if (selectionChanged) false else current.isLoadingMore,
            hasMore = if (selectionChanged) false else current.hasMore,
            error = if (selectionChanged) null else current.error
        )
        if (selectionChanged && selectedSource != null) loadLibraryFirstPage()
    }

    fun selectLibraryProvider(provider: HomeServerKind?) {
        val current = _libraryState.value
        if (current.selectedProvider == provider) return
        libraryRequestId++
        libraryLoadJob?.cancel()
        librarySearchJob?.cancel()
        if (provider == null) {
            _libraryState.value = current.copy(
                selectedProvider = null,
                selectedSourceRef = null,
                items = emptyList(),
                isLoading = false,
                isLoadingMore = false,
                error = null
            )
            return
        }
        val firstLibrary = current.libraries.firstOrNull { it.serverKind == provider }
        _libraryState.value = current.copy(
            selectedProvider = provider,
            selectedSourceRef = firstLibrary?.sourceRef,
            items = emptyList(),
            isLoading = firstLibrary != null,
            isLoadingMore = false,
            error = null
        )
        if (firstLibrary != null) loadLibraryFirstPage()
    }

    fun selectLibrary(sourceRef: String) {
        if (_libraryState.value.selectedSourceRef == sourceRef) return
        librarySearchJob?.cancel()
        _libraryState.value = _libraryState.value.copy(
            selectedSourceRef = sourceRef,
            items = emptyList(),
            isLoadingMore = false,
            error = null
        )
        loadLibraryFirstPage()
    }

    fun setLibrarySort(sort: HomeServerLibrarySort) {
        if (_libraryState.value.sort == sort) return
        _libraryState.value = _libraryState.value.copy(sort = sort, error = null)
        loadLibraryFirstPage()
    }

    fun setLibrarySearch(query: String) {
        if (_libraryState.value.searchQuery == query) return
        libraryRequestId++
        libraryLoadJob?.cancel()
        _libraryState.value = _libraryState.value.copy(searchQuery = query)
        librarySearchJob?.cancel()
        librarySearchJob = viewModelScope.launch {
            delay(300L)
            loadLibraryFirstPage()
        }
    }

    fun refreshLibrary() = loadLibraryFirstPage(force = true)

    private fun libraryCacheKey(state: HomeLibraryUiState): String = listOf(
        state.selectedSourceRef.orEmpty(),
        state.sort.name,
        state.searchQuery.trim().lowercase()
    ).joinToString("|")

    private fun loadLibraryFirstPage(force: Boolean = false) {
        val snapshot = _libraryState.value
        val sourceRef = snapshot.selectedSourceRef ?: return
        val cacheKey = libraryCacheKey(snapshot)
        val cached = libraryCache[cacheKey]
        if (cached != null && !force) {
            _libraryState.value = snapshot.copy(
                items = cached.first,
                hasMore = cached.second,
                isLoading = false,
                isLoadingMore = false,
                error = null
            )
        } else {
            _libraryState.value = snapshot.copy(
                isLoading = true,
                isLoadingMore = false,
                error = null
            )
        }
        val requestId = ++libraryRequestId
        libraryLoadJob?.cancel()
        libraryLoadJob = viewModelScope.launch {
            runCatching {
                mediaRepository.loadHomeServerLibraryPage(
                    sourceRef = sourceRef,
                    offset = 0,
                    limit = LIBRARY_PAGE_SIZE,
                    sort = snapshot.sort,
                    searchQuery = snapshot.searchQuery
                )
            }.onSuccess { page ->
                if (requestId != libraryRequestId) return@onSuccess
                val items = page.items.enrichWithPlaybackProgress()
                if (requestId != libraryRequestId) return@onSuccess
                libraryCache[cacheKey] = items to page.hasMore
                while (libraryCache.size > LIBRARY_CACHE_ENTRY_LIMIT) {
                    libraryCache.remove(libraryCache.keys.first())
                }
                _libraryState.value = _libraryState.value.copy(
                    items = items,
                    hasMore = page.hasMore,
                    isLoading = false,
                    isLoadingMore = false,
                    error = null
                )
                fetchLogos(items.take(LIBRARY_LOGO_INITIAL_PREFETCH))
            }.onFailure { error ->
                if (error is CancellationException) throw error
                if (requestId != libraryRequestId) return@onFailure
                _libraryState.value = _libraryState.value.copy(
                    items = if (cached == null) emptyList() else _libraryState.value.items,
                    isLoading = false,
                    isLoadingMore = false,
                    error = if (cached == null) {
                        error.message ?: context.getString(R.string.homeserver_connection_failed)
                    } else {
                        null
                    }
                )
            }
        }
    }

    fun loadMoreLibrary() {
        val snapshot = _libraryState.value
        val sourceRef = snapshot.selectedSourceRef ?: return
        if (!snapshot.hasMore || snapshot.isLoading || snapshot.isLoadingMore) return
        val requestId = libraryRequestId
        val cacheKey = libraryCacheKey(snapshot)
        _libraryState.value = snapshot.copy(isLoadingMore = true)
        viewModelScope.launch {
            runCatching {
                mediaRepository.loadHomeServerLibraryPage(
                    sourceRef = sourceRef,
                    offset = snapshot.items.size,
                    limit = LIBRARY_PAGE_SIZE,
                    sort = snapshot.sort,
                    searchQuery = snapshot.searchQuery
                )
            }.onSuccess { page ->
                if (requestId != libraryRequestId) return@onSuccess
                val current = _libraryState.value
                val existing = current.items.mapTo(HashSet()) { item ->
                    "${item.homeServerSourceRef}:${item.homeServerItemId}:${item.mediaType}:${item.id}"
                }
                val fresh = page.items.filter { item ->
                    "${item.homeServerSourceRef}:${item.homeServerItemId}:${item.mediaType}:${item.id}" !in existing
                }.enrichWithPlaybackProgress()
                if (requestId != libraryRequestId) return@onSuccess
                val merged = current.items + fresh
                _libraryState.value = current.copy(
                    items = merged,
                    hasMore = page.hasMore,
                    isLoadingMore = false,
                    error = null
                )
                libraryCache[cacheKey] = merged to page.hasMore
                fetchLogos(fresh.take(LIBRARY_LOGO_INITIAL_PREFETCH))
            }.onFailure {
                if (it is CancellationException) throw it
                if (requestId == libraryRequestId) {
                    _libraryState.value = _libraryState.value.copy(isLoadingMore = false)
                }
            }
        }
    }



    private fun updateAvailableSources(
        catalogs: List<CatalogConfig>? = null,
        homeServerCandidates: List<HomeServerCatalogCandidate>? = null
    ) {
        if (catalogs != null) currentCatalogs = catalogs
        if (homeServerCandidates != null) currentHomeServerCandidates = homeServerCandidates

        val allSources = buildWatchlistSources(
            catalogs = currentCatalogs,
            homeServerCandidates = currentHomeServerCandidates,
            trackerLists = currentTrackerLists
        )
        val previousSources = _uiState.value.sources.associateBy { it.id }
        val changedSourceIds = allSources.mapNotNull { source ->
            source.id.takeIf { previousSources[source.id] != source }
        }.toSet()
        changedSourceIds.forEach { sourceId ->
            sourceItemsCache.remove(sourceId)
            sourcePageStates.remove(sourceId)
        }
        val currentSelectedId = _uiState.value.selectedSourceId
        val wasValid = allSources.any { it.id == currentSelectedId }
        val validSelectedId = if (wasValid) currentSelectedId else WatchlistSourceItem.MyWatchlist.id

        _uiState.value = _uiState.value.copy(
            sources = allSources,
            selectedSourceId = validSelectedId
        )

        if ((!wasValid && currentSelectedId != WatchlistSourceItem.MyWatchlist.id) || validSelectedId in changedSourceIds) {
            loadActiveSourceItems()
        }
    }

    fun selectSource(sourceId: String) {
        if (_uiState.value.selectedSourceId == sourceId) return
        sourceLoadJob?.cancel()
        sourceLoadMoreJob?.cancel()
        val cached = sourceItemsCache[sourceId]
        val pageState = sourcePageStates[sourceId] ?: SourcePageState()
        _uiState.value = _uiState.value.copy(
            selectedSourceId = sourceId,
            isLoading = cached == null,
            movies = cached?.filter { it.mediaType == MOVIE } ?: emptyList(),
            series = cached?.filter { it.mediaType == TV } ?: emptyList(),
            error = null,
            lastFocusedSectionIndex = 0,
            lastFocusedItemIndex = 0,
            hasMore = pageState.hasMore,
            isLoadingMore = false
        )
        loadActiveSourceItems()
    }

    fun saveFocusState(sectionIndex: Int, itemIndex: Int) {
        _uiState.value = _uiState.value.copy(
            lastFocusedSectionIndex = sectionIndex,
            lastFocusedItemIndex = itemIndex
        )
    }

    private fun loadActiveSourceItems(forceRefresh: Boolean = false) {
        val activeSource = _uiState.value.selectedSource
        val cacheKey = activeSource.id
        val cached = sourceItemsCache[cacheKey]
        if (forceRefresh) {
            sourceLoadMoreJob?.cancel()
            sourcePageStates[cacheKey] = sourcePageStates[cacheKey]?.copy(isLoadingMore = false)
                ?: SourcePageState()
        }

        if (cached != null && !forceRefresh) {
            val pageState = sourcePageStates[cacheKey] ?: SourcePageState()
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                movies = cached.filter { it.mediaType == MOVIE },
                series = cached.filter { it.mediaType == TV },
                error = null,
                hasMore = pageState.hasMore,
                isLoadingMore = false
            )
            fetchLogos(cached)
            return
        }

        when (activeSource) {
            is WatchlistSourceItem.MyWatchlist -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = cached == null,
                    error = null,
                    hasMore = false,
                    isLoadingMore = false
                )
                sourceLoadJob = viewModelScope.launch {
                    val local = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                    sourceItemsCache[cacheKey] = local
                    if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            movies = local.filter { it.mediaType == MOVIE },
                            series = local.filter { it.mediaType == TV },
                            error = null
                        )
                        fetchLogos(local)
                        if (local.needsArtworkEnrichment()) enrichLocalWatchlistInBackground()
                    }
                }
            }
            is WatchlistSourceItem.Catalog -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = cached == null,
                    error = null,
                    hasMore = false,
                    isLoadingMore = false
                )
                sourceLoadJob = viewModelScope.launch {
                    runCatching {
                        mediaRepository.loadCustomCatalog(activeSource.config, maxItems = 120)
                    }.onSuccess { category ->
                        val items = (category?.items ?: emptyList()).enrichWithPlaybackProgress()
                        sourceItemsCache[cacheKey] = items
                        if (_uiState.value.selectedSourceId == activeSource.id) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                movies = items.filter { it.mediaType == MOVIE },
                                series = items.filter { it.mediaType == TV },
                                error = null
                            )
                            fetchLogos(items)
                        }
                    }.onFailure { error ->
                        if (_uiState.value.selectedSourceId == activeSource.id) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = error.message ?: context.getString(R.string.watchlist_error_refresh)
                            )
                        }
                    }
                }
            }
            is WatchlistSourceItem.TrackerList -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = cached == null,
                    error = null,
                    hasMore = false,
                    isLoadingMore = false
                )
                sourceLoadJob = viewModelScope.launch {
                    runCatching {
                        val baseItems = when (activeSource.provider) {
                            TrackerLibraryProvider.TRAKT -> {
                                if (activeSource.listKey == TRAKT_WATCHLIST_KEY) {
                                    traktRepository.getWatchlist()
                                } else {
                                    traktRepository.getPersonalListItems(activeSource.listKey)
                                }
                            }
                            TrackerLibraryProvider.SIMKL -> simklSyncService.getLibraryItems(
                                status = activeSource.listKey,
                                forceRefresh = forceRefresh
                            )
                        }
                        hydrateTrackerItems(baseItems)
                    }.onSuccess { items ->
                        val enriched = items.enrichWithPlaybackProgress()
                        sourceItemsCache[cacheKey] = enriched
                        if (_uiState.value.selectedSourceId == activeSource.id) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                movies = enriched.filter { it.mediaType == MOVIE },
                                series = enriched.filter { it.mediaType == TV },
                                error = null
                            )
                            fetchLogos(enriched)
                        }
                    }.onFailure { error ->
                        if (_uiState.value.selectedSourceId == activeSource.id) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = error.message ?: context.getString(R.string.watchlist_error_refresh)
                            )
                        }
                    }
                }
            }
            is WatchlistSourceItem.HomeServer -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = cached == null,
                    error = null,
                    isLoadingMore = false
                )
                sourceLoadJob = viewModelScope.launch {
                    runCatching {
                        mediaRepository.loadHomeServerLibraryPage(
                            sourceRef = activeSource.candidate.sourceRef,
                            offset = 0,
                            limit = LIBRARY_PAGE_SIZE,
                            sort = HomeServerLibrarySort.RECENTLY_ADDED
                        )
                    }.onSuccess { page ->
                        val items = page.items.enrichWithPlaybackProgress()
                        sourceItemsCache[cacheKey] = items
                        sourcePageStates[cacheKey] = SourcePageState(
                            hasMore = page.hasMore && page.items.isNotEmpty(),
                            nextOffset = page.items.size
                        )
                        if (_uiState.value.selectedSourceId == activeSource.id) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                movies = items.filter { it.mediaType == MOVIE },
                                series = items.filter { it.mediaType == TV },
                                error = null,
                                hasMore = page.hasMore && page.items.isNotEmpty(),
                                isLoadingMore = false
                            )
                            fetchLogos(items)
                        }
                    }.onFailure { error ->
                        if (_uiState.value.selectedSourceId == activeSource.id) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = error.message ?: context.getString(R.string.homeserver_connection_failed),
                                isLoadingMore = false
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun hydrateTrackerItems(items: List<MediaItem>): List<MediaItem> = coroutineScope {
        val limiter = Semaphore(6)
        items.take(TRACKER_LIST_ITEM_LIMIT).mapIndexed { index, item ->
            async {
                limiter.withPermit {
                    runCatching {
                        when (item.mediaType) {
                            MOVIE -> mediaRepository.getMovieDetails(item.id)
                            TV -> mediaRepository.getTvDetails(item.id)
                        }.copy(sourceOrder = index)
                    }.getOrElse {
                        item.copy(sourceOrder = index)
                    }
                }
            }
        }.awaitAll()
    }

    fun loadMoreActiveSource() {
        val activeSource = _uiState.value.selectedSource as? WatchlistSourceItem.HomeServer ?: return
        val cacheKey = activeSource.id
        val pageState = sourcePageStates[cacheKey] ?: return
        val currentItems = sourceItemsCache[cacheKey].orEmpty()
        if (!pageState.hasMore || pageState.isLoadingMore || currentItems.isEmpty()) return

        sourcePageStates[cacheKey] = pageState.copy(isLoadingMore = true)
        if (_uiState.value.selectedSourceId == cacheKey) {
            _uiState.value = _uiState.value.copy(isLoadingMore = true)
        }
        sourceLoadMoreJob?.cancel()
        sourceLoadMoreJob = viewModelScope.launch {
            runCatching {
                mediaRepository.loadHomeServerLibraryPage(
                    sourceRef = activeSource.candidate.sourceRef,
                    offset = pageState.nextOffset,
                    limit = LIBRARY_PAGE_SIZE,
                    sort = HomeServerLibrarySort.RECENTLY_ADDED
                )
            }.onSuccess { page ->
                val existingKeys = currentItems.mapTo(HashSet()) { item ->
                    "${item.homeServerSourceRef}:${item.homeServerItemId}:${item.mediaType}:${item.id}"
                }
                val freshItems = page.items.filter { item ->
                    "${item.homeServerSourceRef}:${item.homeServerItemId}:${item.mediaType}:${item.id}" !in existingKeys
                }.enrichWithPlaybackProgress()
                val mergedItems = currentItems + freshItems
                sourceItemsCache[cacheKey] = mergedItems
                sourcePageStates[cacheKey] = SourcePageState(
                    hasMore = page.hasMore && page.items.isNotEmpty(),
                    nextOffset = pageState.nextOffset + page.items.size
                )
                if (_uiState.value.selectedSourceId == cacheKey) {
                    _uiState.value = _uiState.value.copy(
                        movies = mergedItems.filter { it.mediaType == MOVIE },
                        series = mergedItems.filter { it.mediaType == TV },
                        hasMore = page.hasMore && page.items.isNotEmpty(),
                        isLoadingMore = false,
                        error = null
                    )
                    fetchLogos(freshItems.take(LIBRARY_LOGO_INITIAL_PREFETCH))
                }
            }.onFailure { error ->
                sourcePageStates[cacheKey] = pageState.copy(isLoadingMore = false)
                if (_uiState.value.selectedSourceId == cacheKey) {
                    _uiState.value = _uiState.value.copy(
                        isLoadingMore = false,
                        toastMessage = error.message ?: context.getString(R.string.homeserver_connection_failed),
                        toastType = ToastType.ERROR
                    )
                }
            }
        }
    }

    private suspend fun List<MediaItem>.enrichWithPlaybackProgress(): List<MediaItem> {
        return runCatching {
            val watchHistory = watchHistoryRepository.getWatchHistory()
            if (watchHistory.isEmpty()) return@runCatching this

            val historyByKey = mutableMapOf<String, WatchHistoryEntry>()
            for (entry in watchHistory) {
                val key = "${entry.media_type}:${entry.show_tmdb_id}"
                if (!historyByKey.containsKey(key)) {
                    historyByKey[key] = entry
                }
            }

            map { item ->
                val typeStr = if (item.mediaType == TV) "tv" else "movie"
                val history = historyByKey["$typeStr:${item.id}"]
                if (history != null) {
                    val progressPercent = (history.progress * 100).toInt().coerceIn(0, 100)
                    val isWatched = progressPercent >= 90 || history.progress >= 0.9f
                    val showProgress = progressPercent in 1..89 && !isWatched
                    item.copy(
                        progress = progressPercent,
                        isWatched = isWatched,
                        showPlaybackProgress = showProgress
                    )
                } else {
                    item
                }
            }
        }.getOrDefault(this)
    }

    private fun fetchLogos(items: List<MediaItem>) {
        viewModelScope.launch {
            val pending = items
                .distinctBy(::watchlistLogoKey)
                .filter { item ->
                    val key = watchlistLogoKey(item)
                    key !in _logoUrls.value && logoRequestsInFlight.add(key)
                }
            if (pending.isEmpty()) return@launch

            val limiter = Semaphore(5)
            val resolved = try {
                coroutineScope {
                    pending.map { item ->
                        async {
                            limiter.withPermit {
                                watchlistLogoKey(item) to runCatching {
                                    mediaRepository.getLogoUrl(item)
                                }.getOrNull()
                            }
                        }
                    }.awaitAll()
                }
            } finally {
                pending.forEach { logoRequestsInFlight.remove(watchlistLogoKey(it)) }
            }
            val found = resolved.mapNotNull { (key, url) -> url?.let { key to it } }.toMap()
            if (found.isNotEmpty()) {
                _logoUrls.value = _logoUrls.value + found
            }
        }
    }

    fun ensureLogo(item: MediaItem) = fetchLogos(listOf(item))

    private fun loadWatchlistInstant() {
        sourceLoadJob = viewModelScope.launch {
            val initialLocalItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
            if (initialLocalItems.isNotEmpty()) {
                sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = initialLocalItems
                if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = initialLocalItems.filter { it.mediaType == MOVIE },
                        series = initialLocalItems.filter { it.mediaType == TV }
                    )
                    fetchLogos(initialLocalItems)
                }
            }

            if (initialLocalItems.isEmpty()) {
                withTimeoutOrNull(3_500) {
                    try {
                        cloudSyncRepository.pullFromCloud()
                    } catch (e: Exception) {
                        if (e is kotlinx.coroutines.CancellationException) throw e
                        AppLogger.recordException(
                            throwable = e,
                            context = watchlistDiagnosticContext("startup_cloud_pull")
                        )
                    }
                }
                val cloudItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                if (cloudItems.isNotEmpty()) {
                    sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = cloudItems
                    if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            movies = cloudItems.filter { it.mediaType == MOVIE },
                            series = cloudItems.filter { it.mediaType == TV }
                        )
                        fetchLogos(cloudItems)
                    }
                }
            }

            val remoteConnected = try { remoteSyncManager.isRemoteConnected() } catch (e: Exception) { if (e is kotlinx.coroutines.CancellationException) throw e; false }
            if (!remoteConnected) {
                val items = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = items
                if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = items.filter { it.mediaType == MOVIE },
                        series = items.filter { it.mediaType == TV }
                    )
                    if (items.isNotEmpty()) fetchLogos(items)
                    if (items.isNotEmpty()) enrichLocalWatchlistInBackground()
                }
                initialLoadComplete = true
                return@launch
            }

            val visibleItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
            sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = visibleItems
            if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                _uiState.value = _uiState.value.copy(
                    isLoading = visibleItems.isEmpty(),
                    movies = visibleItems.filter { it.mediaType == MOVIE },
                    series = visibleItems.filter { it.mediaType == TV }
                )
                if (visibleItems.isNotEmpty()) fetchLogos(visibleItems)
                if (visibleItems.needsArtworkEnrichment()) enrichLocalWatchlistInBackground()
            }
            initialLoadComplete = true

            val syncedFromTrakt = withTimeoutOrNull(12_000) {
                syncTraktWatchlistSuspend()
            } ?: false

            if (!syncedFromTrakt) {
                val fallbackItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                if (fallbackItems.isNotEmpty()) {
                    sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = fallbackItems
                    if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            movies = fallbackItems.filter { it.mediaType == MOVIE },
                            series = fallbackItems.filter { it.mediaType == TV }
                        )
                        fetchLogos(fallbackItems)
                        enrichLocalWatchlistInBackground()
                    }
                } else if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = context.getString(R.string.watchlist_error_load_trakt)
                    )
                }
            } else {
                enrichLocalWatchlistInBackground()
            }
        }
    }

    private fun enrichLocalWatchlistInBackground() {
        if (enrichmentInFlight) {
            enrichmentRequested = true
            return
        }
        enrichmentInFlight = true
        viewModelScope.launch {
            try {
                do {
                    enrichmentRequested = false
                    val enrichedItems = watchlistRepository.refreshWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                    if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                        sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = enrichedItems
                        if (enrichedItems.isNotEmpty()) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                movies = enrichedItems.filter { it.mediaType == MOVIE },
                                series = enrichedItems.filter { it.mediaType == TV }
                            )
                            fetchLogos(enrichedItems)
                        } else if (_uiState.value.isLoading) {
                            _uiState.value = _uiState.value.copy(isLoading = false)
                        }
                    }
                } while (enrichmentRequested)
            } catch (error: Exception) {
                if (error is kotlinx.coroutines.CancellationException) throw error
                AppLogger.recordException(
                    throwable = error,
                    context = watchlistDiagnosticContext("background_enrich")
                )
                if (_uiState.value.isLoading && _uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                    val fallbackItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = fallbackItems.filter { it.mediaType == MOVIE },
                        series = fallbackItems.filter { it.mediaType == TV }
                    )
                }
            } finally {
                enrichmentInFlight = false
            }
        }
    }

    fun refresh() {
        if (_uiState.value.selectedSourceId != WatchlistSourceItem.MyWatchlist.id) {
            loadActiveSourceItems(forceRefresh = true)
            return
        }
        viewModelScope.launch {
            val hadItems = _uiState.value.allItems.isNotEmpty()
            _uiState.value = _uiState.value.copy(isLoading = !hadItems)
            try {
                val remoteConnected = try { remoteSyncManager.isRemoteConnected() } catch (e: Exception) { if (e is kotlinx.coroutines.CancellationException) throw e; false }
                val syncedFromTrakt = if (remoteConnected) {
                    withTimeoutOrNull(15_000) { syncTraktWatchlistSuspend() } ?: false
                } else {
                    false
                }
                if (!syncedFromTrakt && !remoteConnected) {
                    val items = watchlistRepository.refreshWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                    sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = items
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = items.filter { it.mediaType == MOVIE },
                        series = items.filter { it.mediaType == TV }
                    )
                } else if (!syncedFromTrakt) {
                    showLocalWatchlistOrError(context.getString(R.string.watchlist_error_load_trakt))
                } else {
                    enrichLocalWatchlistInBackground()
                }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                AppLogger.recordException(
                    throwable = e,
                    context = watchlistDiagnosticContext("refresh")
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    toastMessage = context.getString(R.string.watchlist_error_refresh),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun refreshAfterResume() {
        if (!initialLoadComplete) return
        if (_uiState.value.selectedSourceId != WatchlistSourceItem.MyWatchlist.id) {
            loadActiveSourceItems(forceRefresh = true)
        } else {
            viewModelScope.launch {
                val remoteConnected = try { remoteSyncManager.isRemoteConnected() } catch (e: Exception) { if (e is kotlinx.coroutines.CancellationException) throw e; false }
                if (!remoteConnected || traktSyncInFlight) return@launch
                val syncedFromTrakt = withTimeoutOrNull(10_000) { syncTraktWatchlistSuspend() } ?: false
                if (!syncedFromTrakt && _uiState.value.isLoading) {
                    val fallbackItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = fallbackItems.filter { it.mediaType == MOVIE },
                        series = fallbackItems.filter { it.mediaType == TV }
                    )
                }
            }
        }
    }

    private suspend fun showLocalWatchlistOrError(message: String) {
        val cachedItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
        if (cachedItems.isNotEmpty()) {
            sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = cachedItems
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                movies = cachedItems.filter { it.mediaType == MOVIE },
                series = cachedItems.filter { it.mediaType == TV }
            )
            fetchLogos(cachedItems)
        } else {
            _uiState.value = _uiState.value.copy(isLoading = false, error = message)
        }
    }

    fun removeFromWatchlist(item: MediaItem) {
        if (_uiState.value.selectedSourceId != WatchlistSourceItem.MyWatchlist.id) return
        viewModelScope.launch {
            try {
                val remoteConnected = try { remoteSyncManager.isRemoteConnected() } catch (e: Exception) { if (e is kotlinx.coroutines.CancellationException) throw e; false }
                val isAnime = item.mediaType == TV &&
                    item.originalLanguage.equals("ja", ignoreCase = true) &&
                    item.genreIds.contains(16)
                if (remoteConnected && !remoteSyncManager.removeFromWatchlist(item.mediaType, item.id, isAnime)) {
                    throw IllegalStateException(context.getString(R.string.watchlist_failed_remove_trakt))
                }

                watchlistRepository.removeFromWatchlist(item.mediaType, item.id)

                val current = _uiState.value
                val updatedMovies = current.movies.filter { it.id != item.id || it.mediaType != item.mediaType }
                val updatedSeries = current.series.filter { it.id != item.id || it.mediaType != item.mediaType }
                sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = updatedMovies + updatedSeries

                _uiState.value = current.copy(
                    movies = updatedMovies,
                    series = updatedSeries,
                    toastMessage = context.getString(R.string.watchlist_toast_removed),
                    toastType = ToastType.SUCCESS
                )
                try {
                    cloudSyncRepository.pushToCloud()
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e
                    AppLogger.recordException(
                        throwable = e,
                        context = watchlistDiagnosticContext("remove_cloud_push")
                    )
                }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e

                AppLogger.recordException(
                    throwable = e,
                    context = watchlistDiagnosticContext(
                        phase = "remove",
                        extra = mapOf("media_type" to item.mediaType.name.lowercase())
                    )
                )
                _uiState.value = _uiState.value.copy(
                    toastMessage = context.getString(R.string.watchlist_failed_remove),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    private suspend fun syncTraktWatchlistSuspend(): Boolean {
        if (traktSyncInFlight) return true
        traktSyncInFlight = true
        return try {
            val syncResult = remoteSyncManager.getWatchlist()
            if (syncResult == null || !syncResult.connected) {
                false
            } else {
                val traktItems = syncResult.items.orEmpty()
                val rawCount = syncResult.rawCount
                if (traktItems.isNotEmpty()) {
                    watchlistRepository.clearWatchlistCache()
                    val orderedTraktItems = traktItems.watchlistDisplayOrder()
                    watchlistRepository.syncFromTraktOrder(orderedTraktItems)
                    val mergedItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                    sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = mergedItems
                    if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            movies = mergedItems.filter { it.mediaType == MOVIE },
                            series = mergedItems.filter { it.mediaType == TV }
                        )
                        fetchLogos(mergedItems)
                    }
                    runCatching { cloudSyncRepository.pushToCloud() }
                } else if (rawCount == 0) {
                    val cachedItems = (watchlistRepository.getCachedItems().ifEmpty {
                        watchlistRepository.getWatchlistItems()
                    }).watchlistDisplayOrder().enrichWithPlaybackProgress()
                    sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = cachedItems
                    if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            movies = cachedItems.filter { it.mediaType == MOVIE },
                            series = cachedItems.filter { it.mediaType == TV }
                        )
                        if (cachedItems.isNotEmpty()) {
                            fetchLogos(cachedItems)
                        }
                    }
                } else {
                    val cachedItems = watchlistRepository.getWatchlistItems().watchlistDisplayOrder().enrichWithPlaybackProgress()
                    sourceItemsCache[WatchlistSourceItem.MyWatchlist.id] = cachedItems
                    if (_uiState.value.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            movies = cachedItems.filter { it.mediaType == MOVIE },
                            series = cachedItems.filter { it.mediaType == TV }
                        )
                        fetchLogos(cachedItems)
                    }
                }
                true
            }
        } catch (error: Exception) {
            if (error is kotlinx.coroutines.CancellationException) throw error
            AppLogger.recordException(
                throwable = error,
                context = watchlistDiagnosticContext("trakt_sync")
            )
            false
        } finally {
            traktSyncInFlight = false
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }

    companion object {
        private const val LIBRARY_PAGE_SIZE = 60
        private const val LIBRARY_LOGO_INITIAL_PREFETCH = 12
        private const val LIBRARY_CACHE_ENTRY_LIMIT = 12
        private const val TRACKER_LIST_ITEM_LIMIT = 240
        private const val TRAKT_WATCHLIST_KEY = "__watchlist__"
        // The status keys stay English so the Simkl API calls keep working;
        // only the rendered list title is localized.
        private val SIMKL_LIBRARY_LISTS = listOf(
            Triple("watching", "Watching", R.string.watchlist_simkl_watching),
            Triple("plantowatch", "Plan to watch", R.string.watchlist_simkl_plan_to_watch),
            Triple("completed", "Completed", R.string.watchlist_simkl_completed),
            Triple("hold", "On hold", R.string.watchlist_simkl_on_hold),
            Triple("dropped", "Dropped", R.string.watchlist_simkl_dropped)
        )
        private val BROWSABLE_LIBRARY_TYPES = setOf(
            "",
            "movie",
            "movies",
            "show",
            "shows",
            "series",
            "tvshows",
            "mixed"
        )
    }
}
