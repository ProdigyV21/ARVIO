package com.arflix.tv.ui.screens.collections

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.itemsIndexed
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import coil.compose.AsyncImage
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.CollectionTileShape
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.SportsRepository
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class CollectionTab { MOVIES, SERIES }

/**
 * Sentinel stored in [CollectionDetailsUiState.error] when a collection page fails to load.
 * The ViewModel has no Context, so the human-readable message is resolved with
 * [stringResource] at the @Composable display point (see CollectionDetailsScreen).
 */
private const val COLLECTION_LOAD_FAILED_ERROR = "__collection_load_failed__"

data class CollectionDetailsUiState(
    val catalog: CatalogConfig? = null,
    val movieItems: List<MediaItem> = emptyList(),
    val seriesItems: List<MediaItem> = emptyList(),
    val supportsMovies: Boolean = false,
    val supportsSeries: Boolean = false,
    val isLoadingMovies: Boolean = true,
    val isLoadingSeries: Boolean = true,
    val isLoadingMoreMovies: Boolean = false,
    val isLoadingMoreSeries: Boolean = false,
    val hasMoreMovies: Boolean = false,
    val hasMoreSeries: Boolean = false,
    val loadedMovieOffset: Int = 0,
    val loadedSeriesOffset: Int = 0,
    val error: String? = null
) {
    val hasMovies: Boolean get() = movieItems.isNotEmpty()
    val hasSeries: Boolean get() = seriesItems.isNotEmpty()
}

@HiltViewModel
class CollectionDetailsViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val mediaRepository: MediaRepository,
    private val sportsRepository: SportsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionDetailsUiState())
    val uiState: StateFlow<CollectionDetailsUiState> = _uiState.asStateFlow()
    private val _cardLogoUrls = MutableStateFlow<Map<String, String>>(emptyMap())
    val cardLogoUrls: StateFlow<Map<String, String>> = _cardLogoUrls.asStateFlow()

    private companion object {
        const val FIRST_PAGE = 8
        const val PAGE_STEP = 12
        const val BACKGROUND_PREFETCH_DELAY_MS = 350L
        const val TMDB_COLLECTION_PREFIX = "tmdb_collection:"
    }

    fun load(catalogId: String) {
        viewModelScope.launch {
            val normalizedCatalogId = normalizeCatalogId(catalogId)
            // Skip reload if this catalog is already loaded — the composable is re-entered
            // after back navigation (Navigation Compose tears down composables on forward nav)
            // and we want to preserve all paginated data so saved scroll positions stay valid.
            val current = _uiState.value
            if (current.catalog?.id == normalizedCatalogId && !current.isLoadingMovies && !current.isLoadingSeries) return@launch

            _uiState.value = CollectionDetailsUiState(isLoadingMovies = true, isLoadingSeries = true)
            val catalog = sportsRepository.sportsCollectionCatalog(normalizedCatalogId)
                ?: catalogRepository.getCatalogs().firstOrNull { it.id == normalizedCatalogId || it.id == catalogId }
                ?: syntheticTmdbCollectionCatalog(normalizedCatalogId)
            if (catalog == null) {
                _uiState.value = CollectionDetailsUiState(
                    isLoadingMovies = false,
                    isLoadingSeries = false,
                    error = "Collection not found"
                )
                return@launch
            }
            _uiState.value = CollectionDetailsUiState(
                catalog = catalog,
                supportsMovies = supportsTab(catalog, CollectionTab.MOVIES),
                supportsSeries = supportsTab(catalog, CollectionTab.SERIES),
                isLoadingMovies = true,
                isLoadingSeries = true
            )

            val primaryTab = when {
                _uiState.value.supportsMovies -> CollectionTab.MOVIES
                _uiState.value.supportsSeries -> CollectionTab.SERIES
                else -> CollectionTab.MOVIES
            }
            loadInitialTab(catalog, primaryTab)
            launch {
                delay(1200L)
                val secondaryTab = if (primaryTab == CollectionTab.MOVIES) CollectionTab.SERIES else CollectionTab.MOVIES
                if (supportsTab(catalog, secondaryTab)) {
                    loadInitialTab(catalog, secondaryTab)
                } else {
                    _uiState.value = when (secondaryTab) {
                        CollectionTab.MOVIES -> _uiState.value.copy(isLoadingMovies = false)
                        CollectionTab.SERIES -> _uiState.value.copy(isLoadingSeries = false)
                    }
                }
            }
        }
    }

    private fun normalizeCatalogId(catalogId: String): String {
        val trimmed = catalogId.trim()
        return runCatching {
            java.net.URLDecoder.decode(trimmed, "UTF-8")
        }.getOrDefault(trimmed)
    }

    private fun syntheticTmdbCollectionCatalog(catalogId: String): CatalogConfig? {
        if (!catalogId.startsWith(TMDB_COLLECTION_PREFIX)) return null

        val payload = catalogId.removePrefix(TMDB_COLLECTION_PREFIX)
        val collectionId = payload.substringBefore(":").toIntOrNull() ?: return null
        val collectionName = payload.substringAfter(":", missingDelimiterValue = "")
            .trim()
            .takeIf { it.isNotBlank() }
            ?: "Collection"

        return CatalogConfig(
            id = catalogId,
            title = collectionName,
            sourceType = CatalogSourceType.PREINSTALLED,
            isPreinstalled = true,
            kind = CatalogKind.COLLECTION,
            collectionGroup = CollectionGroupKind.FRANCHISE,
            collectionTileShape = CollectionTileShape.POSTER,
            collectionSources = listOf(
                CollectionSourceConfig(
                    kind = CollectionSourceKind.TMDB_COLLECTION,
                    mediaType = "movie",
                    tmdbCollectionId = collectionId
                )
            )
        )
    }

    private suspend fun loadInitialTab(catalog: CatalogConfig, tab: CollectionTab) {
        val page = runCatching { loadCollectionPage(catalog, tab, offset = 0, limit = FIRST_PAGE) }.getOrNull()
        val pageItems = when (tab) {
            CollectionTab.MOVIES -> page?.items.orEmpty().filter { it.mediaType == MediaType.MOVIE }
            CollectionTab.SERIES -> page?.items.orEmpty().filter { it.mediaType == MediaType.TV }
        }
        val decoratedCatalog = decorateSportsCatalogWithArtwork(catalog, pageItems)
        _uiState.value = when (tab) {
            CollectionTab.MOVIES -> _uiState.value.copy(
                catalog = decoratedCatalog,
                movieItems = pageItems,
                isLoadingMovies = false,
                hasMoreMovies = page?.hasMore == true,
                loadedMovieOffset = pageItems.size,
                error = _uiState.value.error ?: if (page == null) COLLECTION_LOAD_FAILED_ERROR else null
            )
            CollectionTab.SERIES -> _uiState.value.copy(
                catalog = decoratedCatalog,
                seriesItems = pageItems,
                isLoadingSeries = false,
                hasMoreSeries = page?.hasMore == true,
                loadedSeriesOffset = pageItems.size,
                error = _uiState.value.error ?: if (page == null) COLLECTION_LOAD_FAILED_ERROR else null
            )
        }
        preloadLogos(pageItems.take(2))
        val hasMore = when (tab) {
            CollectionTab.MOVIES -> _uiState.value.hasMoreMovies
            CollectionTab.SERIES -> _uiState.value.hasMoreSeries
        }
        if (hasMore) {
            viewModelScope.launch {
                delay(BACKGROUND_PREFETCH_DELAY_MS)
                loadMoreIfNeeded(tab)
            }
        }
    }

    fun loadMoreIfNeeded(tab: CollectionTab) {
        val state = _uiState.value
        val catalog = state.catalog ?: return
        val isBusy = when (tab) {
            CollectionTab.MOVIES -> state.isLoadingMovies || state.isLoadingMoreMovies || !state.hasMoreMovies
            CollectionTab.SERIES -> state.isLoadingSeries || state.isLoadingMoreSeries || !state.hasMoreSeries
        }
        if (isBusy) return
        _uiState.value = when (tab) {
            CollectionTab.MOVIES -> state.copy(isLoadingMoreMovies = true)
            CollectionTab.SERIES -> state.copy(isLoadingMoreSeries = true)
        }
        viewModelScope.launch {
            val pageCatalog = catalogForTab(catalog, tab)
            val nextOffset = when (tab) {
                CollectionTab.MOVIES -> state.loadedMovieOffset
                CollectionTab.SERIES -> state.loadedSeriesOffset
            }
            val next = runCatching { loadCollectionPage(pageCatalog, tab, offset = nextOffset, limit = PAGE_STEP) }.getOrNull()
            val freshItems = when (tab) {
                CollectionTab.MOVIES -> next?.items.orEmpty().filter { it.mediaType == MediaType.MOVIE }
                CollectionTab.SERIES -> next?.items.orEmpty().filter { it.mediaType == MediaType.TV }
            }
            val existingIds = when (tab) {
                CollectionTab.MOVIES -> state.movieItems.mapTo(HashSet()) { it.id to it.mediaType }
                CollectionTab.SERIES -> state.seriesItems.mapTo(HashSet()) { it.id to it.mediaType }
            }
            val uniqueNew = freshItems.filter { (it.id to it.mediaType) !in existingIds }
            _uiState.value = when (tab) {
                CollectionTab.MOVIES -> _uiState.value.copy(
                    movieItems = state.movieItems + uniqueNew,
                    isLoadingMoreMovies = false,
                    hasMoreMovies = next?.hasMore == true,
                    loadedMovieOffset = state.loadedMovieOffset + freshItems.size
                )
                CollectionTab.SERIES -> _uiState.value.copy(
                    seriesItems = state.seriesItems + uniqueNew,
                    isLoadingMoreSeries = false,
                    hasMoreSeries = next?.hasMore == true,
                    loadedSeriesOffset = state.loadedSeriesOffset + freshItems.size
                )
            }
            preloadLogos(uniqueNew)
        }
    }

    fun preloadLogos(items: List<MediaItem>) {
        if (items.isEmpty()) return
        viewModelScope.launch {
            val current = _cardLogoUrls.value.toMutableMap()
            val missing = items
                .filterNot { item -> SportsAddonCapabilities.isSportsEventStatus(item.status) }
                .filter { item ->
                    val key = "${item.mediaType}_${item.id}"
                    key !in current
                }
                .take(2)
            if (missing.isEmpty()) return@launch

            missing.forEach { item ->
                mediaRepository.peekCachedLogoUrl(item.mediaType, item.id)?.let { cached ->
                    current["${item.mediaType}_${item.id}"] = cached
                }
            }
            _cardLogoUrls.value = current.toMap()

            val remoteMissing = missing.filter { item ->
                val key = "${item.mediaType}_${item.id}"
                key !in current
            }
            if (remoteMissing.isEmpty()) return@launch

            val fetched = remoteMissing.map { item ->
                async {
                    val key = "${item.mediaType}_${item.id}"
                    val logo = runCatching {
                        mediaRepository.getLogoUrl(item.mediaType, item.id)
                    }.getOrNull()
                    if (logo.isNullOrBlank()) null else key to logo
                }
            }.awaitAll().filterNotNull()

            if (fetched.isNotEmpty()) {
                _cardLogoUrls.value = (_cardLogoUrls.value + fetched).toMap()
            }
        }
    }

    fun openSportsCollectionItem(
        item: MediaItem,
        onUnavailable: () -> Unit,
        onNavigateToPlayer: (MediaType, Int, String, String?, String?) -> Unit
    ) {
        val status = item.status.orEmpty()
        if (!SportsAddonCapabilities.isSportsEventStatus(status)) return
        if (!item.badge.equals("LIVE", ignoreCase = true)) {
            onUnavailable()
            return
        }
        viewModelScope.launch {
            val playback = sportsRepository.resolvePlayback(status, item.title)
            if (playback == null) {
                onUnavailable()
                return@launch
            }
            onNavigateToPlayer(
                MediaType.TV,
                playback.mediaId,
                playback.streamUrl,
                playback.addonId,
                playback.sourceName
            )
        }
    }

    private suspend fun loadCollectionPage(
        catalog: CatalogConfig,
        tab: CollectionTab,
        offset: Int,
        limit: Int
    ): MediaRepository.CategoryPageResult {
        return if (SportsAddonCapabilities.isSportsCollectionCatalogId(catalog.id)) {
            sportsRepository.loadSportsCollectionPage(catalog.id, offset = offset, limit = limit)
        } else {
            mediaRepository.loadCollectionCatalogPage(
                catalogForTab(catalog, tab),
                offset = offset,
                limit = limit
            )
        }
    }

    private fun decorateSportsCatalogWithArtwork(
        catalog: CatalogConfig,
        items: List<MediaItem>
    ): CatalogConfig {
        if (!SportsAddonCapabilities.isSportsCollectionCatalogId(catalog.id)) return catalog
        val hero = items.firstOrNull { !it.backdrop.isNullOrBlank() }?.backdrop
            ?: items.firstOrNull { it.image.isNotBlank() }?.image
        return if (hero.isNullOrBlank()) {
            catalog
        } else {
            catalog.copy(
                collectionCoverImageUrl = catalog.collectionCoverImageUrl ?: hero,
                collectionHeroImageUrl = catalog.collectionHeroImageUrl ?: hero,
                collectionFocusGifUrl = catalog.collectionFocusGifUrl ?: hero
            )
        }
    }

    private fun catalogForTab(catalog: CatalogConfig, tab: CollectionTab): CatalogConfig {
        val filteredSources = catalog.collectionSources.filter { sourceMatchesTab(it, tab) }
        return catalog.copy(collectionSources = filteredSources)
    }

    private fun supportsTab(catalog: CatalogConfig, tab: CollectionTab): Boolean {
        return catalog.collectionSources.any { sourceMatchesTab(it, tab) }
    }

    private fun sourceMatchesTab(source: com.arflix.tv.data.model.CollectionSourceConfig, tab: CollectionTab): Boolean {
        val mediaType = source.mediaType?.trim()?.lowercase()
        if (mediaType != null) {
            if (mediaType == "all" || mediaType == "any" || mediaType == "both" || mediaType == "mixed") {
                return true
            }
            return when (tab) {
                CollectionTab.MOVIES -> mediaType == "movie" || mediaType == "film"
                CollectionTab.SERIES -> mediaType == "series" || mediaType == "tv" || mediaType == "show" || mediaType == "anime"
            }
        }

        return when (source.kind) {
            com.arflix.tv.data.model.CollectionSourceKind.TMDB_COLLECTION -> tab == CollectionTab.MOVIES
            else -> true
        }
    }
}

@Composable
fun CollectionDetailsScreen(
    catalogId: String,
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    viewModel: CollectionDetailsViewModel = hiltViewModel(),
    onNavigateToDetails: (MediaType, Int) -> Unit,
    onNavigateToPlayer: (MediaType, Int, String, String?, String?) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToWatchlist: () -> Unit,
    onNavigateToTv: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val cardLogoUrls by viewModel.cardLogoUrls.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(catalogId) { viewModel.load(catalogId) }
    BackHandler(onBack = onBack)

    val rowKey = remember(catalogId) { "collection:$catalogId" }
    val isSportsCollection = SportsAddonCapabilities.isSportsCollectionCatalogId(uiState.catalog?.id ?: catalogId)
    val usePosterCards = uiState.catalog?.collectionGroup != CollectionGroupKind.GENRE &&
        rememberCatalogueRowLayoutMode(rowKey) == CardLayoutMode.POSTER
    val configuration = LocalConfiguration.current
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val cardWidth = if (usePosterCards) {
        if (isMobile) 138.dp else when {
            configuration.screenWidthDp >= 2200 -> 196.dp
            configuration.screenWidthDp >= 1600 -> 184.dp
            else -> 172.dp
        }
    } else if (isMobile) 220.dp else 260.dp
    val gridColumns = if (isMobile) {
        if (isLandscape) {
            if (usePosterCards) 4 else 3
        } else if (usePosterCards) {
            3
        } else {
            2
        }
    } else if (usePosterCards) {
        when {
            configuration.screenWidthDp >= 2200 -> 8
            configuration.screenWidthDp >= 1600 -> 7
            else -> 5
        }
    } else {
        when {
            configuration.screenWidthDp >= 2200 -> 6
            configuration.screenWidthDp >= 1600 -> 5
            else -> 4
        }
    }

    val initialTab = when {
        uiState.supportsMovies -> CollectionTab.MOVIES
        uiState.supportsSeries -> CollectionTab.SERIES
        else -> CollectionTab.MOVIES
    }
    var selectedTab by rememberSaveable(uiState.catalog?.id) { mutableStateOf(initialTab) }
    val moviesGridState = rememberTvLazyGridState()
    val seriesGridState = rememberTvLazyGridState()
    val moviesTabFocusRequester = remember { FocusRequester() }
    val seriesTabFocusRequester = remember { FocusRequester() }
    // True after the first focus has been delivered; subsequent ON_RESUME uses saved index.
    var hasReceivedInitialFocus by rememberSaveable { mutableStateOf(false) }
    // Index (within the items list) of the last card the user focused per tab.
    var lastFocusedMovieIndex by rememberSaveable { mutableStateOf(-1) }
    var lastFocusedSeriesIndex by rememberSaveable { mutableStateOf(-1) }
    // Set on back-navigation to trigger focus on the specific card after scrolling to it.
    var pendingFocusIndex by remember { mutableStateOf(-1) }

    LaunchedEffect(uiState.catalog?.id, uiState.supportsMovies, uiState.supportsSeries) {
        val resolvedTab = when {
            selectedTab == CollectionTab.MOVIES && uiState.supportsMovies -> CollectionTab.MOVIES
            selectedTab == CollectionTab.SERIES && uiState.supportsSeries -> CollectionTab.SERIES
            uiState.supportsMovies -> CollectionTab.MOVIES
            uiState.supportsSeries -> CollectionTab.SERIES
            else -> CollectionTab.MOVIES
        }
        if (resolvedTab != selectedTab) {
            selectedTab = resolvedTab
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val currentTab by rememberUpdatedState(selectedTab)
    val currentSupportsMovies by rememberUpdatedState(uiState.supportsMovies)
    val currentSupportsSeries by rememberUpdatedState(uiState.supportsSeries)

    fun requestTabFocus() {
        coroutineScope.launch {
            // 300ms clears the 250ms pop-enter animation before touching the focus tree
            kotlinx.coroutines.delay(300)
            if (!hasReceivedInitialFocus) {
                // First entry: focus the tab chip so D-pad works from the start
                runCatching {
                    when (currentTab) {
                        CollectionTab.MOVIES -> if (currentSupportsMovies) moviesTabFocusRequester.requestFocus()
                        CollectionTab.SERIES -> if (currentSupportsSeries) seriesTabFocusRequester.requestFocus()
                    }
                }
                hasReceivedInitialFocus = true
            } else {
                // Returning from back navigation: scroll back to the saved card index and
                // set pendingFocusIndex so the card requests focus once it's in composition.
                // focusRestorer() can't be used here because lazy grid recycles off-screen
                // items, making saved focus nodes stale by the time we return.
                val savedIndex = when (currentTab) {
                    CollectionTab.MOVIES -> lastFocusedMovieIndex
                    CollectionTab.SERIES -> lastFocusedSeriesIndex
                }
                if (savedIndex >= 0) {
                    val currentGridState = when (currentTab) {
                        CollectionTab.MOVIES -> moviesGridState
                        CollectionTab.SERIES -> seriesGridState
                    }
                    // Grid has 2 header items (tab bar + spacer) before the media cards
                    runCatching { currentGridState.scrollToItem(savedIndex + 2) }
                    pendingFocusIndex = savedIndex
                } else {
                    runCatching {
                        when (currentTab) {
                            CollectionTab.MOVIES -> if (currentSupportsMovies) moviesTabFocusRequester.requestFocus()
                            CollectionTab.SERIES -> if (currentSupportsSeries) seriesTabFocusRequester.requestFocus()
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(selectedTab) { pendingFocusIndex = -1 }

    // Fires on fresh composition (first entry or recreation after back navigation)
    LaunchedEffect(Unit) { requestTabFocus() }

    // Fires when the screen resumes from STARTED (back navigation without recreation)
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) requestTabFocus()
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown &&
                    (event.key == Key.Back || event.key == Key.Escape)
                ) {
                    onBack()
                    true
                } else {
                    false
                }
            }
    ) {
        CollectionBackdrop(catalog = uiState.catalog)
        val activeTab = selectedTab
        val items = if (activeTab == CollectionTab.MOVIES) {
            uiState.movieItems
        } else {
            uiState.seriesItems
        }
        val isTabLoading = if (activeTab == CollectionTab.MOVIES) {
            uiState.isLoadingMovies
        } else {
            uiState.isLoadingSeries
        }
        val isTabLoadingMore = if (activeTab == CollectionTab.MOVIES) {
            uiState.isLoadingMoreMovies
        } else {
            uiState.isLoadingMoreSeries
        }
        val gridState = if (activeTab == CollectionTab.MOVIES) {
            moviesGridState
        } else {
            seriesGridState
        }
        CollectionItemsGrid(
            items = items,
            gridColumns = gridColumns,
            cardWidth = cardWidth,
            usePosterCards = usePosterCards,
            gridState = gridState,
            pendingFocusIndex = pendingFocusIndex,
            onClearPendingFocus = { pendingFocusIndex = -1 },
            hasMovies = uiState.supportsMovies,
            hasSeries = uiState.supportsSeries,
            cardLogoUrls = cardLogoUrls,
            selectedTab = selectedTab,
            moviesTabFocusRequester = moviesTabFocusRequester,
            seriesTabFocusRequester = seriesTabFocusRequester,
            isSportsCollection = isSportsCollection,
            onTabSelected = { selectedTab = it },
            onItemClick = { item ->
                if (SportsAddonCapabilities.isSportsEventStatus(item.status)) {
                    viewModel.openSportsCollectionItem(
                        item = item,
                        onUnavailable = {
                            android.widget.Toast.makeText(
                                context,
                                if (item.badge.equals("LIVE", ignoreCase = true)) {
                                    context.getString(R.string.home_sports_playback_failed)
                                } else {
                                    context.getString(R.string.home_sports_event_not_live)
                                },
                                android.widget.Toast.LENGTH_SHORT
                            ).show()
                        },
                        onNavigateToPlayer = onNavigateToPlayer
                    )
                } else {
                    onNavigateToDetails(item.mediaType, item.id)
                }
            },
            onItemFocused = { item, index ->
                viewModel.preloadLogos(listOf(item))
                when (activeTab) {
                    CollectionTab.MOVIES -> lastFocusedMovieIndex = index
                    CollectionTab.SERIES -> lastFocusedSeriesIndex = index
                }
            },
            onVisibleItemsChanged = { visibleItems -> viewModel.preloadLogos(visibleItems) },
            onNearEnd = { viewModel.loadMoreIfNeeded(activeTab) },
            isLoading = isTabLoading,
            isLoadingMore = isTabLoadingMore,
            emptyMessage = when (uiState.error) {
                null -> stringResource(R.string.collection_empty)
                COLLECTION_LOAD_FAILED_ERROR -> stringResource(R.string.collection_failed_load)
                else -> uiState.error!!
            },
            topContentPadding = if (isMobile) 18.dp else if (usePosterCards) 22.dp else 10.dp
        )
    }
}

@Composable
private fun CollectionBackdrop(catalog: CatalogConfig?) {
    val accent = collectionAccentColor(catalog?.collectionGroup)
    val backdrop = catalog?.collectionHeroImageUrl
        ?.takeIf { it.isNotBlank() }
        ?: catalog?.collectionCoverImageUrl?.takeIf { it.isNotBlank() }

    Box(modifier = Modifier.fillMaxSize()) {
        if (backdrop != null) {
            AsyncImage(
                model = backdrop,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                alpha = 0.2f
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            accent.copy(alpha = 0.32f),
                            accent.copy(alpha = 0.1f),
                            Color.Transparent
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            appBackgroundDark().copy(alpha = 0.62f),
                            accent.copy(alpha = 0.12f),
                            appBackgroundDark().copy(alpha = 0.88f),
                            appBackgroundDark()
                        )
                    )
                )
        )
    }
}

private fun collectionAccentColor(group: CollectionGroupKind?): Color = when (group) {
    CollectionGroupKind.FEATURED -> Color(0xFFE6A23C)
    CollectionGroupKind.SERVICE -> Color(0xFF1AA7EC)
    CollectionGroupKind.GENRE -> Color(0xFFC65D3B)
    CollectionGroupKind.DECADE -> Color(0xFFB98B32)
    CollectionGroupKind.FRANCHISE -> Color(0xFF2F9C95)
    CollectionGroupKind.NETWORK -> Color(0xFF4F9D69)
    null -> Color.White
}

@Composable
private fun CollectionTabBar(
    hasMovies: Boolean,
    hasSeries: Boolean,
    selectedTab: CollectionTab,
    moviesTabFocusRequester: FocusRequester,
    seriesTabFocusRequester: FocusRequester,
    isSportsCollection: Boolean,
    onTabSelected: (CollectionTab) -> Unit
) {
    val showMovies = hasMovies || !hasSeries
    val showSeries = hasSeries || !hasMovies
    val onlyOne = showMovies xor showSeries
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .arvioDpadFocusGroup()
            .padding(start = 42.dp, end = 42.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isSportsCollection) {
            CollectionTabChip(
                label = "Live & Upcoming",
                isSelected = true,
                focusRequester = seriesTabFocusRequester,
                onClick = { onTabSelected(CollectionTab.SERIES) }
            )
            return@Row
        }
        if (showMovies) {
            CollectionTabChip(
                label = stringResource(R.string.movies),
                isSelected = selectedTab == CollectionTab.MOVIES || onlyOne,
                focusRequester = moviesTabFocusRequester,
                onClick = { onTabSelected(CollectionTab.MOVIES) }
            )
        }
        if (showSeries) {
            CollectionTabChip(
                label = stringResource(R.string.series),
                isSelected = selectedTab == CollectionTab.SERIES || onlyOne,
                focusRequester = seriesTabFocusRequester,
                onClick = { onTabSelected(CollectionTab.SERIES) }
            )
        }
    }
}

@Composable
private fun CollectionTabChip(
    label: String,
    isSelected: Boolean,
    focusRequester: FocusRequester,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(50)
    val bg = when {
        isSelected -> Color.White
        isFocused -> Color.Transparent
        else -> Color.White.copy(alpha = 0.08f)
    }
    val fg = when {
        isSelected -> appBackgroundDark()
        isFocused -> Color.White
        else -> TextPrimary.copy(alpha = 0.75f)
    }
    val borderColor = when {
        isSelected && isFocused -> Color(0xFF4F7FB0)
        isFocused -> Color.White
        else -> Color.Transparent
    }
    val borderWidth = when {
        isSelected && isFocused -> 1.dp
        isFocused -> 2.dp
        else -> 0.dp
    }
    Box(
        modifier = Modifier
            .clip(shape)
            .background(bg)
            .border(width = borderWidth, color = borderColor, shape = shape)
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused = it.isFocused }
            .onPreviewKeyEvent { event ->
                event.type == KeyEventType.KeyDown && event.key == Key.DirectionUp
            }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 10.dp)
    ) {
        androidx.tv.material3.Text(
            text = label,
            style = ArflixTypography.sectionTitle.copy(
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                letterSpacing = 0.4.sp
            ),
            color = fg
        )
    }
}

@Composable
private fun CollectionItemsGrid(
    items: List<MediaItem>,
    gridColumns: Int,
    cardWidth: androidx.compose.ui.unit.Dp,
    usePosterCards: Boolean,
    gridState: androidx.tv.foundation.lazy.grid.TvLazyGridState,
    pendingFocusIndex: Int,
    onClearPendingFocus: () -> Unit,
    hasMovies: Boolean,
    hasSeries: Boolean,
    cardLogoUrls: Map<String, String>,
    selectedTab: CollectionTab,
    moviesTabFocusRequester: FocusRequester,
    seriesTabFocusRequester: FocusRequester,
    isSportsCollection: Boolean,
    onTabSelected: (CollectionTab) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onItemFocused: (MediaItem, Int) -> Unit,
    onVisibleItemsChanged: (List<MediaItem>) -> Unit,
    onNearEnd: () -> Unit,
    isLoading: Boolean,
    isLoadingMore: Boolean,
    emptyMessage: String,
    topContentPadding: androidx.compose.ui.unit.Dp
) {
    val cardContentType = if (usePosterCards) "poster_card" else "landscape_card"
    val focusBleedPadding = if (usePosterCards) 10.dp else 6.dp
    val latestItems by rememberUpdatedState(items)
    val latestGridColumns by rememberUpdatedState(gridColumns)
    val latestOnVisibleItemsChanged by rememberUpdatedState(onVisibleItemsChanged)
    val latestOnNearEnd by rememberUpdatedState(onNearEnd)
    // Collect scroll position without restarting on page-load-size changes —
    // items.size used to live in the key, which relaunched the snapshotFlow on
    // every page append and caused a stutter frame during scroll.
    LaunchedEffect(gridState) {
        snapshotFlow {
            val layout = gridState.layoutInfo
            val last = layout.visibleItemsInfo.lastOrNull()?.index ?: 0
            val mediaIndexes = layout.visibleItemsInfo
                .asSequence()
                .map { it.index - 2 }
                .filter { it >= 0 }
                .toList()
            Triple(last, layout.totalItemsCount, mediaIndexes)
        }.distinctUntilChanged().collect { (last, total, mediaIndexes) ->
            if (total > 12 && last >= total - 3) latestOnNearEnd()
            if (mediaIndexes.isNotEmpty()) {
                val start = (mediaIndexes.minOrNull() ?: 0).coerceAtLeast(0)
                val currentItems = latestItems
                val end = ((mediaIndexes.maxOrNull() ?: start) + latestGridColumns)
                    .coerceAtMost(currentItems.lastIndex)
                if (start <= end) {
                    latestOnVisibleItemsChanged(currentItems.subList(start, end + 1))
                }
            }
        }
    }

    TvLazyVerticalGrid(
        columns = TvGridCells.Fixed(gridColumns),
        state = gridState,
        modifier = Modifier.fillMaxSize().arvioDpadFocusGroup().clipToBounds(),
        contentPadding = PaddingValues(
            start = 42.dp,
            top = topContentPadding,
            end = 42.dp,
            bottom = 48.dp + focusBleedPadding
        ),
        verticalArrangement = Arrangement.spacedBy(if (usePosterCards) 18.dp else 14.dp),
        horizontalArrangement = Arrangement.spacedBy(if (usePosterCards) 18.dp else 14.dp)
    ) {
        item(
            span = { androidx.tv.foundation.lazy.grid.TvGridItemSpan(maxLineSpan) },
            contentType = "tabs"
        ) {
            CollectionTabBar(
                hasMovies = hasMovies,
                hasSeries = hasSeries,
                selectedTab = selectedTab,
                moviesTabFocusRequester = moviesTabFocusRequester,
                seriesTabFocusRequester = seriesTabFocusRequester,
                isSportsCollection = isSportsCollection,
                onTabSelected = onTabSelected
            )
        }
        item(
            span = { androidx.tv.foundation.lazy.grid.TvGridItemSpan(maxLineSpan) },
            contentType = "tabs_gap"
        ) {
            Box(modifier = Modifier.height(6.dp))
        }

        if (isLoading) {
            val cardHeight = if (usePosterCards) cardWidth * 1.5f else cardWidth * 9f / 16f
            itemsIndexed((1..gridColumns * 3).toList(), contentType = { _, _ -> "skeleton" }) { _, _ ->
                Box(
                    modifier = Modifier
                        .height(cardHeight)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.05f))
                )
            }
        } else if (items.isEmpty() && !isLoadingMore) {
            item(
                span = { androidx.tv.foundation.lazy.grid.TvGridItemSpan(maxLineSpan) },
                contentType = "empty"
            ) {
                CollectionEmptyState(message = emptyMessage)
            }
        } else {
            itemsIndexed(
                items,
                key = { _, item -> "${item.mediaType}-${item.id}" },
                contentType = { _, _ -> cardContentType }
            ) { index, item ->
                val cardLogoUrl = cardLogoUrls["${item.mediaType}_${item.id}"]
                val itemFocusRequester = remember { FocusRequester() }

                // Fires when scrollToItem brings this card into composition on back-navigation.
                // pendingFocusIndex is set by requestTabFocus() after scrolling to this item.
                LaunchedEffect(pendingFocusIndex) {
                    if (pendingFocusIndex == index) {
                        delay(50)
                        runCatching { itemFocusRequester.requestFocus() }
                        onClearPendingFocus()
                    }
                }

                MediaCard(
                    item = item,
                    width = cardWidth,
                    isLandscape = !usePosterCards,
                    logoImageUrl = cardLogoUrl,
                    showTitle = true,
                    titleMaxLines = if (usePosterCards) 2 else 1,
                    onFocused = {
                        onItemFocused(item, index)
                        if (items.size > 10 && index >= items.size - 2) onNearEnd()
                    },
                    onClick = { onItemClick(item) },
                    modifier = Modifier.focusRequester(itemFocusRequester)
                )
            }
        }

        if (isLoadingMore) {
            item(
                span = { androidx.tv.foundation.lazy.grid.TvGridItemSpan(maxLineSpan) },
                contentType = "loading_more"
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator(
                        color = Color(0xFF4F7FB0),
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CollectionEmptyState(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp),
        contentAlignment = Alignment.Center
    ) {
        androidx.tv.material3.Text(
            text = message,
            color = TextSecondary,
            style = ArflixTypography.body
        )
    }
}
