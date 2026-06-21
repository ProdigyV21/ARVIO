package com.arflix.tv.ui.screens.collections

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed as foundationItemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.graphics.graphicsLayer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.CollectionTileShape
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.StreamAvailabilityRepository
import com.arflix.tv.data.stream.StreamAvailabilitySummary
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode
import com.arflix.tv.ui.focus.arvioManualBringIntoViewBoundary
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.performance.rememberTvMenuBackdropRequest
import com.arflix.tv.ui.performance.tvMenuBackdropSwapDelayMs
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.theme.ImdbYellow
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.util.Constants
import com.arflix.tv.util.LocalDeviceType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class CollectionTab { MOVIES, SERIES }

enum class CollectionToastType { SUCCESS, ERROR, INFO }

data class CollectionPreviewState(
    val item: MediaItem? = null,
    val imdbRating: String = "",
    val streamSummary: StreamAvailabilitySummary? = null,
    val isLoadingStreamSummary: Boolean = false,
    val isLoading: Boolean = false
)

data class CollectionDetailsUiState(
    val catalog: CatalogConfig? = null,
    val movieItems: List<MediaItem> = emptyList(),
    val seriesItems: List<MediaItem> = emptyList(),
    val preview: CollectionPreviewState = CollectionPreviewState(),
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
    val toastMessage: String? = null,
    val toastType: CollectionToastType = CollectionToastType.INFO,
    val error: String? = null
) {
    val hasMovies: Boolean get() = movieItems.isNotEmpty()
    val hasSeries: Boolean get() = seriesItems.isNotEmpty()
}

@HiltViewModel
class CollectionDetailsViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val mediaRepository: MediaRepository,
    private val streamAvailabilityRepository: StreamAvailabilityRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionDetailsUiState())
    val uiState: StateFlow<CollectionDetailsUiState> = _uiState.asStateFlow()
    private val _cardLogoUrls = MutableStateFlow<Map<String, String>>(emptyMap())
    val cardLogoUrls: StateFlow<Map<String, String>> = _cardLogoUrls.asStateFlow()
    private val previewCache = mutableMapOf<String, CollectionPreviewState>()
    private var previewJob: Job? = null

    companion object {
        const val FIRST_PAGE = 8
        const val PAGE_STEP = 12
        const val BACKGROUND_PREFETCH_DELAY_MS = 350L
        const val PREVIEW_ENRICH_DEBOUNCE_MS = 260L
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
            val catalog = catalogRepository.getCatalogs().firstOrNull { it.id == normalizedCatalogId || it.id == catalogId }
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
            val missingServiceKeyMessage = missingServiceCatalogKeysMessage(catalog)
            if (missingServiceKeyMessage != null) {
                _uiState.value = _uiState.value.copy(
                    isLoadingMovies = false,
                    isLoadingSeries = false,
                    error = missingServiceKeyMessage
                )
                return@launch
            }

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
        val page = runCatching {
            mediaRepository.loadCollectionCatalogPage(
                catalogForTab(catalog, tab),
                offset = 0,
                limit = FIRST_PAGE
            )
        }.getOrNull()
        val pageItems = when (tab) {
            CollectionTab.MOVIES -> page?.items.orEmpty().filter { it.mediaType == MediaType.MOVIE }
            CollectionTab.SERIES -> page?.items.orEmpty().filter { it.mediaType == MediaType.TV }
        }
        _uiState.value = when (tab) {
            CollectionTab.MOVIES -> _uiState.value.copy(
                movieItems = pageItems,
                isLoadingMovies = false,
                hasMoreMovies = page?.hasMore == true,
                loadedMovieOffset = pageItems.size,
                error = _uiState.value.error ?: if (page == null) "Failed to load collection" else null
            )
            CollectionTab.SERIES -> _uiState.value.copy(
                seriesItems = pageItems,
                isLoadingSeries = false,
                hasMoreSeries = page?.hasMore == true,
                loadedSeriesOffset = pageItems.size,
                error = _uiState.value.error ?: if (page == null) "Failed to load collection" else null
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
            val next = runCatching {
                mediaRepository.loadCollectionCatalogPage(
                    pageCatalog,
                    offset = nextOffset,
                    limit = PAGE_STEP
                )
            }.getOrNull()
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

    fun focusPreview(item: MediaItem) {
        val key = previewKey(item) ?: return
        val currentPreview = _uiState.value.preview
        val currentPreviewKey = previewKey(currentPreview.item)
        val cached = previewCache[key]

        if (cached != null && !cached.isLoading && !cached.isLoadingStreamSummary) {
            _uiState.value = _uiState.value.copy(preview = cached)
            previewJob?.cancel()
            previewJob = null
            return
        }

        if (currentPreviewKey == key) {
            if (!currentPreview.isLoading && !currentPreview.isLoadingStreamSummary) {
                previewCache[key] = currentPreview
                previewJob?.cancel()
                previewJob = null
            }
            return
        }

        _uiState.value = _uiState.value.copy(
            preview = cached ?: CollectionPreviewState(
                item = item,
                imdbRating = item.imdbRating.ifBlank { item.tmdbRating },
                isLoadingStreamSummary = true,
                isLoading = true
            )
        )

        previewJob?.cancel()
        previewJob = viewModelScope.launch {
            delay(PREVIEW_ENRICH_DEBOUNCE_MS)

            val detailedItem = runCatching {
                when (item.mediaType) {
                    MediaType.MOVIE -> mediaRepository.getMovieDetails(item.id)
                    MediaType.TV -> mediaRepository.getTvDetails(item.id)
                }
            }.getOrNull()
            val mergedItem = detailedItem ?: item
            val fallbackRating = item.imdbRating.ifBlank { item.tmdbRating }
            val imdbRating = mergedItem.imdbRating
                .ifBlank { runCatching { mediaRepository.getImdbRating(item.mediaType, item.id) }.getOrNull().orEmpty() }
                .ifBlank { fallbackRating }
            val enrichedItem = if (imdbRating.isNotBlank() && mergedItem.imdbRating.isBlank()) {
                mergedItem.copy(imdbRating = imdbRating)
            } else {
                mergedItem
            }
            val imdbId = runCatching {
                mediaRepository.getOrResolveImdbId(enrichedItem.mediaType, enrichedItem.id)
            }.getOrNull()
            val streamSummary = runCatching {
                val year = enrichedItem.year.toPreviewYear()
                when (enrichedItem.mediaType) {
                    MediaType.MOVIE -> streamAvailabilityRepository.movieSummary(
                        imdbId = imdbId,
                        title = enrichedItem.title,
                        year = year
                    )
                    MediaType.TV -> streamAvailabilityRepository.episodeSummary(
                        imdbId = imdbId,
                        title = enrichedItem.title,
                        year = year,
                        season = enrichedItem.nextEpisode?.seasonNumber ?: 1,
                        episode = enrichedItem.nextEpisode?.episodeNumber ?: 1,
                        tmdbId = enrichedItem.id,
                        genreIds = enrichedItem.genreIds,
                        originalLanguage = enrichedItem.originalLanguage
                    )
                }
            }.getOrNull()
            val preview = CollectionPreviewState(
                item = enrichedItem,
                imdbRating = imdbRating,
                streamSummary = streamSummary,
                isLoadingStreamSummary = false,
                isLoading = false
            )

            if (previewKey(_uiState.value.preview.item) != key) return@launch
            previewCache[key] = preview
            _uiState.value = _uiState.value.copy(preview = preview)
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }

    private fun previewKey(item: MediaItem?): String? {
        item ?: return null
        return "${item.mediaType}:${item.id}"
    }

    private fun String.toPreviewYear(): Int? = take(4).toIntOrNull()

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

    private fun missingServiceCatalogKeysMessage(catalog: CatalogConfig): String? {
        if (catalog.collectionGroup != CollectionGroupKind.SERVICE) return null

        val hasWatchmodeSource = catalog.collectionSources.any { it.kind == CollectionSourceKind.WATCHMODE_SOURCE }
        val hasTmdbProviderSource = catalog.collectionSources.any { it.kind == CollectionSourceKind.TMDB_WATCH_PROVIDER }
        val hasUsableTmdbKey = Constants.TMDB_API_KEY.isNotBlank()
        val hasRegionedServiceSource = hasWatchmodeSource || hasTmdbProviderSource

        return if (hasRegionedServiceSource && !hasUsableTmdbKey) {
            "Add a TMDB API key in Settings > API keys to load Swedish service catalogs. Watchmode improves freshness, but TMDB is required for artwork and details."
        } else {
            null
        }
    }
}

@Composable
fun CollectionDetailsScreen(
    catalogId: String,
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    viewModel: CollectionDetailsViewModel = hiltViewModel(),
    onNavigateToDetails: (MediaType, Int) -> Unit,
    onNavigateToPlayer: (MediaType, Int, Int?, Int?, String?) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToWatchlist: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val cardLogoUrls by viewModel.cardLogoUrls.collectAsStateWithLifecycle()
    LaunchedEffect(catalogId) { viewModel.load(catalogId) }
    BackHandler(onBack = onBack)

    val rowKey = remember(catalogId) { "collection:$catalogId" }
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
        if (isMobile) {
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
                onTabSelected = { selectedTab = it },
                onItemClick = { onNavigateToDetails(it.mediaType, it.id) },
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
                emptyMessage = uiState.error ?: "Nothing to show here yet.",
                topContentPadding = 18.dp
            )
        } else {
            CollectionMasterDetailList(
                catalog = uiState.catalog,
                items = items,
                preview = uiState.preview,
                hasMovies = uiState.supportsMovies,
                hasSeries = uiState.supportsSeries,
                selectedTab = selectedTab,
                moviesTabFocusRequester = moviesTabFocusRequester,
                seriesTabFocusRequester = seriesTabFocusRequester,
                onTabSelected = { selectedTab = it },
                onItemClick = { onNavigateToDetails(it.mediaType, it.id) },
                onItemFocused = { item, index ->
                    viewModel.focusPreview(item)
                    when (activeTab) {
                        CollectionTab.MOVIES -> lastFocusedMovieIndex = index
                        CollectionTab.SERIES -> lastFocusedSeriesIndex = index
                    }
                    if (items.size > 10 && index >= items.size - 3) viewModel.loadMoreIfNeeded(activeTab)
                },
                onNearEnd = { viewModel.loadMoreIfNeeded(activeTab) },
                isLoading = isTabLoading,
                isLoadingMore = isTabLoadingMore,
                emptyMessage = uiState.error ?: "Nothing to show here yet."
            )
        }

        uiState.toastMessage?.let { message ->
            Toast(
                message = message,
                type = when (uiState.toastType) {
                    CollectionToastType.SUCCESS -> ComponentToastType.SUCCESS
                    CollectionToastType.ERROR -> ComponentToastType.ERROR
                    CollectionToastType.INFO -> ComponentToastType.INFO
                },
                isVisible = true,
                durationMs = if (uiState.toastType == CollectionToastType.ERROR) 8000 else 3500,
                onDismiss = { viewModel.dismissToast() }
            )
        }

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
        if (showMovies) {
            CollectionTabChip(
                label = "Movies",
                isSelected = selectedTab == CollectionTab.MOVIES || onlyOne,
                focusRequester = moviesTabFocusRequester,
                onClick = { onTabSelected(CollectionTab.MOVIES) }
            )
        }
        if (showSeries) {
            CollectionTabChip(
                label = "Series",
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
private fun CollectionMasterDetailList(
    catalog: CatalogConfig?,
    items: List<MediaItem>,
    preview: CollectionPreviewState,
    hasMovies: Boolean,
    hasSeries: Boolean,
    selectedTab: CollectionTab,
    moviesTabFocusRequester: FocusRequester,
    seriesTabFocusRequester: FocusRequester,
    onTabSelected: (CollectionTab) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onItemFocused: (MediaItem, Int) -> Unit,
    onNearEnd: () -> Unit,
    isLoading: Boolean,
    isLoadingMore: Boolean,
    emptyMessage: String
) {
    val listState = rememberLazyListState()
    var selectedIndex by rememberSaveable(selectedTab) { mutableStateOf(0) }
    val safeSelectedIndex = selectedIndex.coerceIn(0, (items.size - 1).coerceAtLeast(0))
    val listFocusRequesters = remember(items.size, selectedTab) { List(items.size) { FocusRequester() } }
    val firstItemKey = items.firstOrNull()?.let { "${it.mediaType}-${it.id}" }
    val selectedItem = items.getOrNull(safeSelectedIndex)
    val displayPreview = when {
        preview.item != null && selectedItem != null &&
            preview.item.id == selectedItem.id && preview.item.mediaType == selectedItem.mediaType -> preview
        selectedItem != null -> CollectionPreviewState(
            item = selectedItem,
            imdbRating = selectedItem.imdbRating.ifBlank { selectedItem.tmdbRating },
            isLoading = true
        )
        else -> preview
    }

    LaunchedEffect(selectedTab, items.size) {
        if (items.isNotEmpty() && selectedIndex > items.lastIndex) {
            selectedIndex = items.lastIndex
            runCatching { listState.scrollToItem(items.lastIndex) }
        }
    }

    LaunchedEffect(selectedTab, firstItemKey) {
        if (items.isEmpty()) return@LaunchedEffect
        val nextIndex = safeSelectedIndex.coerceAtMost(items.lastIndex)
        selectedIndex = nextIndex
        onItemFocused(items[nextIndex], nextIndex)
        runCatching { listState.scrollToItem(nextIndex) }
        delay(260)
        runCatching { listFocusRequesters.getOrNull(nextIndex)?.requestFocus() }
    }

    LaunchedEffect(listState, items.size) {
        snapshotFlow {
            val layout = listState.layoutInfo
            layout.visibleItemsInfo.lastOrNull()?.index ?: 0
        }.distinctUntilChanged().collect { last ->
            if (items.size > 10 && last >= items.size - 3) onNearEnd()
        }
    }

    val backgroundImage = displayPreview.item?.backdrop?.takeIf { it.isNotBlank() }
        ?: displayPreview.item?.image?.takeIf { it.isNotBlank() }
        ?: catalog?.collectionHeroImageUrl?.takeIf { it.isNotBlank() }
        ?: catalog?.collectionCoverImageUrl?.takeIf { it.isNotBlank() }
    val isTvDevice = !LocalDeviceType.current.isTouchDevice()
    var settledBackgroundImage by remember { mutableStateOf<String?>(backgroundImage) }
    LaunchedEffect(backgroundImage, isTvDevice) {
        val next = backgroundImage
        if (next == settledBackgroundImage) return@LaunchedEffect
        val delayMs = tvMenuBackdropSwapDelayMs(
            isTvDevice = isTvDevice,
            hasDisplayedBackdrop = !settledBackgroundImage.isNullOrBlank()
        )
        if (delayMs > 0L) delay(delayMs)
        settledBackgroundImage = next
    }
    val backgroundRequest = rememberTvMenuBackdropRequest(settledBackgroundImage)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
    ) {
        if (backgroundRequest != null) {
            AsyncImage(
                model = backgroundRequest,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                alpha = 0.54f
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            appBackgroundDark().copy(alpha = 0.96f),
                            appBackgroundDark().copy(alpha = 0.78f),
                            Color.Black.copy(alpha = 0.28f),
                            Color.Black.copy(alpha = 0.64f)
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
                            appBackgroundDark().copy(alpha = 0.72f),
                            Color.Transparent,
                            appBackgroundDark().copy(alpha = 0.92f)
                        )
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 28.dp, top = 24.dp, end = 30.dp, bottom = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(330.dp)
                    .fillMaxHeight()
            ) {
                androidx.tv.material3.Text(
                    text = catalog?.title?.takeIf { it.isNotBlank() } ?: "Collection",
                    color = TextPrimary,
                    style = ArflixTypography.heroTitle.copy(
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
                )
                CollectionTabBar(
                    hasMovies = hasMovies,
                    hasSeries = hasSeries,
                    selectedTab = selectedTab,
                    moviesTabFocusRequester = moviesTabFocusRequester,
                    seriesTabFocusRequester = seriesTabFocusRequester,
                    onTabSelected = onTabSelected
                )
                Spacer(modifier = Modifier.height(12.dp))

                when {
                    isLoading -> CollectionListSkeleton()
                    items.isEmpty() && !isLoadingMore -> CollectionEmptyState(message = emptyMessage)
                    else -> {
                        LazyColumn(
                            state = listState,
                            modifier = Modifier
                                .fillMaxSize()
                                .arvioManualBringIntoViewBoundary()
                                .arvioDpadFocusGroup(),
                            contentPadding = PaddingValues(bottom = 24.dp)
                        ) {
                            foundationItemsIndexed(
                                items,
                                key = { _, item -> "${item.mediaType}-${item.id}" },
                                contentType = { _, _ -> "collection_text_row" }
                            ) { index, item ->
                                CollectionTextRow(
                                    item = item,
                                    isSelected = index == safeSelectedIndex,
                                    preview = displayPreview.takeIf { previewState ->
                                        val previewItem = previewState.item
                                        previewItem?.id == item.id && previewItem.mediaType == item.mediaType
                                    },
                                    focusRequester = listFocusRequesters[index],
                                    onFocused = {
                                        selectedIndex = index
                                        onItemFocused(item, index)
                                    },
                                    onClick = { onItemClick(item) },
                                    onUpFromFirst = {
                                        when (selectedTab) {
                                            CollectionTab.MOVIES -> moviesTabFocusRequester.requestFocus()
                                            CollectionTab.SERIES -> seriesTabFocusRequester.requestFocus()
                                        }
                                    },
                                    isFirst = index == 0
                                )
                            }
                            if (isLoadingMore) {
                                item(contentType = "collection_loading_more") {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        androidx.compose.material3.CircularProgressIndicator(
                                            color = Color.White.copy(alpha = 0.82f),
                                            strokeWidth = 2.dp,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            CollectionPreviewPanel(
                preview = displayPreview,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
private fun CollectionTextRow(
    item: MediaItem,
    isSelected: Boolean,
    preview: CollectionPreviewState?,
    focusRequester: FocusRequester,
    onFocused: () -> Unit,
    onClick: () -> Unit,
    onUpFromFirst: () -> Unit,
    isFirst: Boolean
) {
    var isFocused by remember { mutableStateOf(false) }
    val rowShape = RoundedCornerShape(7.dp)
    val bgBrush = when {
        isFocused -> Brush.horizontalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.26f),
                Color.White.copy(alpha = 0.13f),
                Color.Transparent
            )
        )
        isSelected -> Brush.horizontalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.12f),
                Color.White.copy(alpha = 0.05f),
                Color.Transparent
            )
        )
        else -> Brush.horizontalGradient(listOf(Color.Transparent, Color.Transparent))
    }
    val borderColor = if (isFocused) Color.White.copy(alpha = 0.9f) else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .graphicsLayer {
                scaleX = if (isFocused) 1.014f else 1f
                scaleY = if (isFocused) 1.014f else 1f
            }
            .clip(rowShape)
            .background(bgBrush)
            .border(1.dp, borderColor, rowShape)
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
                if (it.isFocused) onFocused()
            }
            .onPreviewKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                when (event.key) {
                    Key.DirectionRight -> false
                    Key.DirectionUp -> {
                        if (isFirst) {
                            onUpFromFirst()
                            true
                        } else {
                            false
                        }
                    }
                    else -> false
                }
            }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.tv.material3.Text(
            text = item.title,
            color = if (isFocused) Color.White else TextPrimary.copy(alpha = 0.92f),
            style = ArflixTypography.body.copy(
                fontSize = 13.5.sp,
                fontWeight = if (isFocused) FontWeight.SemiBold else FontWeight.Medium
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        androidx.tv.material3.Text(
            text = rowMeta(item, preview?.streamSummary),
            color = TextSecondary,
            style = ArflixTypography.caption.copy(fontSize = 10.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(112.dp)
        )
        androidx.tv.material3.Text(
            text = "IMDb ${item.imdbRating.ifBlank { item.tmdbRating }.ifBlank { "-" }}",
            color = ImdbYellow,
            style = ArflixTypography.caption.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            modifier = Modifier.width(48.dp)
        )
    }
}

@Composable
private fun CollectionPreviewPanel(
    preview: CollectionPreviewState,
    modifier: Modifier = Modifier
) {
    val item = preview.item

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.18f),
                            Color.Black.copy(alpha = 0.58f)
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
                            Color.Transparent,
                            appBackgroundDark().copy(alpha = 0.30f),
                            appBackgroundDark().copy(alpha = 0.86f)
                        )
                    )
                )
        )

        if (item == null) {
            CollectionEmptyState(message = "Nothing selected.")
            return@Box
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(0.62f)
                .padding(start = 4.dp, end = 20.dp, bottom = 30.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            androidx.tv.material3.Text(
                text = item.title,
                color = Color.White,
                style = ArflixTypography.heroTitle.copy(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(9.dp))
            androidx.tv.material3.Text(
                text = previewMeta(item, preview),
                color = TextSecondary,
                style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            StreamSummaryFlags(preview)
            Spacer(modifier = Modifier.height(12.dp))
            androidx.tv.material3.Text(
                text = item.overview.ifBlank { "No overview available." },
                color = TextPrimary.copy(alpha = 0.9f),
                style = ArflixTypography.body.copy(fontSize = 15.sp),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }

        item.image.takeIf { it.isNotBlank() }?.let { poster ->
            AsyncImage(
                model = poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 32.dp)
                    .width(204.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.06f))
                    .border(1.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
private fun StreamSummaryFlags(preview: CollectionPreviewState) {
    val summary = preview.streamSummary
    val labels = when {
        summary != null -> buildList {
            summary.bestSourceLabel?.takeIf { it.isNotBlank() }?.let(::add)
            summary.bestResolution?.takeIf { it.isNotBlank() }?.let(::add)
            summary.bestVisualTag?.takeIf { it.isNotBlank() }?.let(::add)
            summary.bestAudioTag?.takeIf { it.isNotBlank() }?.let(::add)
            if (summary.hasSwedishSubtitles) add("SE subs")
            if (summary.isCachedOrDebridReady) add("Cached")
            add("${summary.sourceCount} ${if (summary.sourceCount == 1) "source" else "sources"}")
        }
        preview.isLoadingStreamSummary -> listOf("Checking sources")
        else -> emptyList()
    }

    if (labels.isEmpty()) return

    Spacer(modifier = Modifier.height(7.dp))
    androidx.tv.material3.Text(
        text = labels.joinToString("  •  "),
        color = TextPrimary.copy(alpha = 0.82f),
        style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun CollectionListSkeleton() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(10) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color.White.copy(alpha = 0.06f))
            )
        }
    }
}

internal fun rowMeta(item: MediaItem, streamSummary: StreamAvailabilitySummary? = null): String {
    val parts = mutableListOf<String>()
    item.year.takeIf { it.isNotBlank() }?.let(parts::add)
    val runtime = when {
        item.mediaType == MediaType.TV && item.totalEpisodes != null -> "${item.totalEpisodes} eps"
        item.mediaType == MediaType.TV && item.status?.isNotBlank() == true -> item.status
        item.duration.isNotBlank() -> item.duration.toCompactRowRuntime()
        else -> null
    }
    runtime?.let(parts::add)
    collectionStreamSummaryCompactLabel(streamSummary)?.let(parts::add)
    return parts.joinToString(" • ").ifBlank {
        if (item.mediaType == MediaType.TV) "Series" else "Movie"
    }
}

private fun String.toCompactRowRuntime(): String = replace(" ", "")

internal fun collectionStreamSummaryCompactLabel(summary: StreamAvailabilitySummary?): String? {
    summary ?: return null
    return buildList {
        summary.bestResolution?.takeIf { it.isNotBlank() }?.let(::add)
        summary.bestVisualTag?.takeIf { it.isNotBlank() }?.let(::add)
        summary.bestAudioTag?.takeIf { it.isNotBlank() }?.let(::add)
        if (summary.hasSwedishSubtitles) add("SE")
        summary.bestSourceLabel?.takeIf { it.isNotBlank() }?.let { add(it.take(18)) }
        add("${summary.sourceCount} src")
    }.joinToString("/").takeIf { it.isNotBlank() }
}

private fun previewMeta(item: MediaItem, preview: CollectionPreviewState): String {
    val parts = mutableListOf<String>()
    item.year.takeIf { it.isNotBlank() }?.let(parts::add)
    parts += if (item.mediaType == MediaType.TV) "Series" else "Movie"
    item.duration.takeIf { it.isNotBlank() }?.let(parts::add)
    item.status.takeIf { !it.isNullOrBlank() }?.let(parts::add)
    val rating = preview.imdbRating.ifBlank { item.imdbRating }.ifBlank { item.tmdbRating }
    parts += "IMDb ${rating.ifBlank { "-" }}"
    return parts.joinToString("  •  ")
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
