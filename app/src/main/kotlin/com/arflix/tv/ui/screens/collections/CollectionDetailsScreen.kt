package com.arflix.tv.ui.screens.collections

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
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
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.itemsIndexed
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
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
import com.arflix.tv.ui.components.LocalBottomBarInset
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
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import javax.inject.Inject

enum class CollectionTab { MOVIES, SERIES }

/**
 * Sentinel stored in [CollectionDetailsUiState.error] when a collection page fails to load.
 * The ViewModel has no Context, so the human-readable message is resolved with
 * [stringResource] at the @Composable display point (see CollectionDetailsScreen).
 */
private const val COLLECTION_LOAD_FAILED_ERROR = "__collection_load_failed__"

/** Same sentinel mechanism as [COLLECTION_LOAD_FAILED_ERROR], for an unknown collection id. */
private const val COLLECTION_NOT_FOUND_ERROR = "__collection_not_found__"

/** Prefix for an empty collection whose add-on sources are missing; the add-on ids follow it. */
private const val COLLECTION_MISSING_ADDON_PREFIX = "__collection_missing_addon__:"

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
    private val _providerLogos = MutableStateFlow<Map<String, String?>>(emptyMap())
    val providerLogos: StateFlow<Map<String, String?>> = _providerLogos.asStateFlow()
    private var providerJob: Job? = null
    private var collectionJob = SupervisorJob(viewModelScope.coroutineContext[Job])
    private var collectionScope = CoroutineScope(viewModelScope.coroutineContext + collectionJob)
    private var loadJob: Job? = null
    private var loadedCatalogId: String? = null
    private var loadGeneration = 0L
    private val detailsSemaphore = Semaphore(2)
    private val detailJobs = mutableMapOf<Pair<MediaType, Int>, Job>()
    private val detailAttempts = mutableSetOf<Pair<MediaType, Int>>()
    private val enrichedDetails = mutableMapOf<Pair<MediaType, Int>, MediaItem>()
    private var visibleDetailKeys = emptySet<Pair<MediaType, Int>>()

    private companion object {
        const val FIRST_PAGE = 8
        const val PAGE_STEP = 12
        const val BACKGROUND_PREFETCH_DELAY_MS = 350L
        const val TMDB_COLLECTION_PREFIX = "tmdb_collection:"
    }

    fun load(catalogId: String) {
        val normalizedCatalogId = normalizeCatalogId(catalogId)
        // Preserve partial and paginated data when composition is recreated during a load.
        if (loadedCatalogId == normalizedCatalogId &&
            (loadJob?.isActive == true || (_uiState.value.catalog != null &&
                !_uiState.value.isLoadingMovies && !_uiState.value.isLoadingSeries))
        ) return
        loadGeneration += 1
        val generation = loadGeneration
        collectionJob.cancel()
        collectionJob = SupervisorJob(viewModelScope.coroutineContext[Job])
        collectionScope = CoroutineScope(viewModelScope.coroutineContext + collectionJob)
        loadedCatalogId = normalizedCatalogId
        detailJobs.clear()
        detailAttempts.clear()
        enrichedDetails.clear()
        visibleDetailKeys = emptySet()
        _cardLogoUrls.value = emptyMap()
        _providerLogos.value = emptyMap()
        _uiState.value = CollectionDetailsUiState()
        loadJob = collectionScope.launch {
            val catalog = sportsRepository.sportsCollectionCatalog(normalizedCatalogId)
                ?: catalogRepository.getCatalogs().firstOrNull { it.id == normalizedCatalogId || it.id == catalogId }
                ?: syntheticTmdbCollectionCatalog(normalizedCatalogId)
            ensureCurrentLoad(generation)
            if (catalog == null) {
                _uiState.value = CollectionDetailsUiState(
                    isLoadingMovies = false,
                    isLoadingSeries = false,
                    error = COLLECTION_NOT_FOUND_ERROR
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
            loadInitialTab(catalog, primaryTab, generation)
            launch {
                delay(1200L)
                ensureCurrentLoad(generation)
                val secondaryTab = if (primaryTab == CollectionTab.MOVIES) CollectionTab.SERIES else CollectionTab.MOVIES
                if (supportsTab(catalog, secondaryTab)) {
                    loadInitialTab(catalog, secondaryTab, generation)
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

    private suspend fun loadInitialTab(catalog: CatalogConfig, tab: CollectionTab, generation: Long) {
        val requestScope = collectionScope
        val page = collectionResultOrNull {
            loadCollectionPage(catalog, tab, offset = 0, limit = FIRST_PAGE, onItemsAvailable = { items ->
                // Repository callbacks may arrive off Main; serialize with enrichment and final publication.
                requestScope.launch {
                    ensureCurrentLoad(generation)
                    val state = _uiState.value
                    val loading = if (tab == CollectionTab.MOVIES) state.isLoadingMovies else state.isLoadingSeries
                    if (!loading) return@launch
                    val partial = mergeEnrichedItems(items.filter { it.mediaType == tab.mediaType() })
                    _uiState.value = when (tab) {
                        CollectionTab.MOVIES -> state.copy(movieItems = partial)
                        CollectionTab.SERIES -> state.copy(seriesItems = partial)
                    }
                }
            })
        }
        ensureCurrentLoad(generation)
        val pageItems = when (tab) {
            CollectionTab.MOVIES -> (page?.items ?: _uiState.value.movieItems).filter { it.mediaType == MediaType.MOVIE }
            CollectionTab.SERIES -> (page?.items ?: _uiState.value.seriesItems).filter { it.mediaType == MediaType.TV }
        }
        val missingAddons = if (page != null && pageItems.isEmpty() &&
            !SportsAddonCapabilities.isSportsCollectionCatalogId(catalog.id)
        ) {
            try {
                mediaRepository.missingCollectionAddons(catalogForTab(catalog, tab))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
        ensureCurrentLoad(generation)
        val pageError = when {
            page == null -> COLLECTION_LOAD_FAILED_ERROR
            missingAddons.isNotEmpty() -> COLLECTION_MISSING_ADDON_PREFIX + missingAddons.joinToString(", ")
            else -> null
        }
        val decoratedCatalog = decorateSportsCatalogWithArtwork(catalog, pageItems)
        _uiState.value = when (tab) {
            CollectionTab.MOVIES -> _uiState.value.copy(
                catalog = decoratedCatalog,
                movieItems = mergeEnrichedItems(pageItems),
                isLoadingMovies = false,
                hasMoreMovies = page?.hasMore == true,
                loadedMovieOffset = page?.nextOffset ?: pageItems.size,
                error = _uiState.value.error ?: pageError
            )
            CollectionTab.SERIES -> _uiState.value.copy(
                catalog = decoratedCatalog,
                seriesItems = mergeEnrichedItems(pageItems),
                isLoadingSeries = false,
                hasMoreSeries = page?.hasMore == true,
                loadedSeriesOffset = page?.nextOffset ?: pageItems.size,
                error = _uiState.value.error ?: pageError
            )
        }
        preloadLogos(pageItems.take(2))
        val hasMore = when (tab) {
            CollectionTab.MOVIES -> _uiState.value.hasMoreMovies
            CollectionTab.SERIES -> _uiState.value.hasMoreSeries
        }
        if (hasMore) {
            requestScope.launch {
                delay(BACKGROUND_PREFETCH_DELAY_MS)
                ensureCurrentLoad(generation)
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
        val generation = loadGeneration
        collectionScope.launch {
            val pageCatalog = catalogForTab(catalog, tab)
            val nextOffset = when (tab) {
                CollectionTab.MOVIES -> state.loadedMovieOffset
                CollectionTab.SERIES -> state.loadedSeriesOffset
            }
            val next = collectionResultOrNull { loadCollectionPage(pageCatalog, tab, offset = nextOffset, limit = PAGE_STEP) }
            ensureCurrentLoad(generation)
            val freshItems = when (tab) {
                CollectionTab.MOVIES -> next?.items.orEmpty().filter { it.mediaType == MediaType.MOVIE }
                CollectionTab.SERIES -> next?.items.orEmpty().filter { it.mediaType == MediaType.TV }
            }
            val current = _uiState.value
            val existingIds = when (tab) {
                CollectionTab.MOVIES -> current.movieItems.mapTo(HashSet()) { it.id to it.mediaType }
                CollectionTab.SERIES -> current.seriesItems.mapTo(HashSet()) { it.id to it.mediaType }
            }
            val uniqueNew = freshItems.filter { (it.id to it.mediaType) !in existingIds }
            _uiState.value = when (tab) {
                CollectionTab.MOVIES -> _uiState.value.copy(
                    movieItems = current.movieItems + mergeEnrichedItems(uniqueNew),
                    isLoadingMoreMovies = false,
                    hasMoreMovies = next?.hasMore == true,
                    loadedMovieOffset = next?.nextOffset ?: (state.loadedMovieOffset + freshItems.size)
                )
                CollectionTab.SERIES -> _uiState.value.copy(
                    seriesItems = current.seriesItems + mergeEnrichedItems(uniqueNew),
                    isLoadingMoreSeries = false,
                    hasMoreSeries = next?.hasMore == true,
                    loadedSeriesOffset = next?.nextOffset ?: (state.loadedSeriesOffset + freshItems.size)
                )
            }
            preloadLogos(uniqueNew)
        }
    }

    fun preloadLogos(items: List<MediaItem>) {
        if (items.isEmpty()) return
        val generation = loadGeneration
        collectionScope.launch {
            ensureCurrentLoad(generation)
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
                    val logo = collectionResultOrNull {
                        mediaRepository.getLogoUrl(item.mediaType, item.id)
                    }
                    if (logo.isNullOrBlank()) null else key to logo
                }
            }.awaitAll().filterNotNull()

            ensureCurrentLoad(generation)
            if (fetched.isNotEmpty()) {
                _cardLogoUrls.value = (_cardLogoUrls.value + fetched).toMap()
            }
        }
    }

    fun enrichVisibleDetails(items: List<MediaItem>) {
        val state = _uiState.value
        val loadedKeys = (state.movieItems + state.seriesItems).mapTo(HashSet()) { it.mediaType to it.id }
        val visible = items.filter {
            it.id > 0 && !it.isHomeServer && !SportsAddonCapabilities.isSportsEventStatus(it.status) &&
                (it.mediaType to it.id) in loadedKeys
        }
        visibleDetailKeys = visible.mapTo(HashSet()) { it.mediaType to it.id }
        val generation = loadGeneration
        visible.forEach { item ->
            val key = item.mediaType to item.id
            if (key in detailAttempts || key in detailJobs) return@forEach
            val job = collectionScope.launch(start = CoroutineStart.LAZY) {
                try {
                    detailsSemaphore.withPermit {
                        ensureCurrentLoad(generation)
                        if (key !in visibleDetailKeys || !detailAttempts.add(key)) return@withPermit
                        val details = collectionResultOrNull {
                            when (item.mediaType) {
                                MediaType.MOVIE -> mediaRepository.getMovieDetails(item.id)
                                MediaType.TV -> mediaRepository.getTvDetails(item.id)
                            }
                        }
                        ensureCurrentLoad(generation)
                        if (details == null || details.id != item.id || details.mediaType != item.mediaType) return@withPermit
                        enrichedDetails[key] = details
                        val current = _uiState.value
                        _uiState.value = current.copy(
                            movieItems = mergeEnrichedItems(current.movieItems),
                            seriesItems = mergeEnrichedItems(current.seriesItems)
                        )
                    }
                } finally {
                    if (generation == loadGeneration) detailJobs.remove(key)
                }
            }
            detailJobs[key] = job
            job.start()
        }
    }

    fun loadPreviewProvider(item: MediaItem) {
        providerJob?.cancel()
        val key = "${item.mediaType}_${item.id}"
        if (key in _providerLogos.value || item.id <= 0 ||
            item.status?.startsWith("iptv:") == true || SportsAddonCapabilities.isSportsEventStatus(item.status.orEmpty())) return
        val generation = loadGeneration
        providerJob = collectionScope.launch {
            delay(180)
            val providers = collectionResultOrNull {
                mediaRepository.getStreamingServices(item.mediaType, item.id,
                    preferredRegion = java.util.Locale.getDefault().country)
            }
            ensureCurrentLoad(generation)
            _providerLogos.value += key to providers?.services?.firstOrNull()?.logoUrl?.takeIf(String::isNotBlank)
        }
    }

    private fun mergeEnrichedItems(items: List<MediaItem>): List<MediaItem> = items.map { item ->
        val details = enrichedDetails[item.mediaType to item.id] ?: return@map item
        // Keep source identity, nonblank artwork, ordering and playback state on the original card.
        item.copy(
            image = item.image.ifBlank { details.image },
            backdrop = item.backdrop?.takeIf { it.isNotBlank() } ?: details.backdrop,
            overview = details.overview.ifBlank { item.overview },
            year = details.year.ifBlank { item.year },
            releaseDate = details.releaseDate ?: item.releaseDate,
            rating = details.rating.ifBlank { item.rating },
            contentRating = details.contentRating ?: item.contentRating,
            duration = details.duration.ifBlank { item.duration },
            imdbRating = details.imdbRating.ifBlank { item.imdbRating },
            tmdbRating = details.tmdbRating.ifBlank { item.tmdbRating },
            genreIds = details.genreIds.ifEmpty { item.genreIds },
            originalLanguage = details.originalLanguage ?: item.originalLanguage,
            originalTitle = details.originalTitle ?: item.originalTitle,
            primaryNetworkLogo = details.primaryNetworkLogo ?: item.primaryNetworkLogo,
            isOngoing = details.isOngoing,
            totalEpisodes = details.totalEpisodes ?: item.totalEpisodes,
            status = item.status ?: details.status,
            budget = details.budget ?: item.budget,
            revenue = details.revenue ?: item.revenue,
            popularity = details.popularity
        )
    }

    private fun CollectionTab.mediaType() = if (this == CollectionTab.MOVIES) MediaType.MOVIE else MediaType.TV

    private suspend fun ensureCurrentLoad(generation: Long) {
        currentCoroutineContext().ensureActive()
        if (generation != loadGeneration) throw CancellationException("Collection changed")
    }

    private suspend fun <T> collectionResultOrNull(block: suspend () -> T): T? = try {
        block()
    } catch (e: CancellationException) {
        throw e
    } catch (_: Exception) {
        null
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
        val generation = loadGeneration
        collectionScope.launch {
            val playback = sportsRepository.resolvePlayback(status, item.title)
            ensureCurrentLoad(generation)
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
        limit: Int,
        onItemsAvailable: ((List<MediaItem>) -> Unit)? = null
    ): MediaRepository.CategoryPageResult {
        return if (SportsAddonCapabilities.isSportsCollectionCatalogId(catalog.id)) {
            sportsRepository.loadSportsCollectionPage(catalog.id, offset = offset, limit = limit)
        } else {
            mediaRepository.loadCollectionCatalogPage(
                catalogForTab(catalog, tab),
                offset = offset,
                limit = limit,
                // Lists without a per-source type (MDBList, Trakt, TMDB lists) mix movies and
                // series; page through this tab's type only, or the tab stalls on pages of the other.
                mediaType = tab.mediaType(),
                onItemsAvailable = onItemsAvailable
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
            val isMovie = mediaType == "movie" || mediaType == "film"
            val isSeries = mediaType == "series" || mediaType == "tv" || mediaType == "show" || mediaType == "anime"
            // Addon-defined types ("Live Docs", "Podcasts") can hold either; the loader keeps
            // only the items whose own type matches the tab.
            if (!isMovie && !isSeries) return true
            return when (tab) {
                CollectionTab.MOVIES -> isMovie
                CollectionTab.SERIES -> isSeries
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
    val providerLogos by viewModel.providerLogos.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(catalogId) { viewModel.load(catalogId) }
    BackHandler(onBack = onBack)

    val rowKey = remember(catalogId) { "collection:$catalogId" }
    val isSportsCollection = SportsAddonCapabilities.isSportsCollectionCatalogId(uiState.catalog?.id ?: catalogId)
    val usePosterCards = uiState.catalog?.collectionGroup != CollectionGroupKind.GENRE &&
        rememberCatalogueRowLayoutMode(rowKey) == CardLayoutMode.POSTER
    val isMobile = LocalDeviceType.current.isTouchDevice()

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
        // Touch keeps its saved scroll position; TV restoration must not steal focus or jump it.
        if (isMobile) return
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
                    // Spotlight and tabs share the grid's only header item.
                    try {
                        currentGridState.scrollToItem(savedIndex + 1)
                    } catch (e: CancellationException) {
                        throw e
                    } catch (_: Exception) {
                        // The saved item may no longer exist after a collection update.
                    }
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

    BackHandler {
        onBack()
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
            catalog = uiState.catalog,
            onBack = onBack,
            items = items,
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
            onVisibleDetailsChanged = { visibleItems -> viewModel.enrichVisibleDetails(visibleItems) },
            onNearEnd = { viewModel.loadMoreIfNeeded(activeTab) },
            isLoading = isTabLoading,
            isLoadingMore = isTabLoadingMore,
            emptyMessage = when (val error = uiState.error) {
                null -> stringResource(R.string.collection_empty)
                COLLECTION_LOAD_FAILED_ERROR -> stringResource(R.string.collection_failed_load)
                COLLECTION_NOT_FOUND_ERROR -> stringResource(R.string.collection_not_found)
                else -> if (error != null && error.startsWith(COLLECTION_MISSING_ADDON_PREFIX)) {
                    stringResource(
                        R.string.collection_missing_addon,
                        error.removePrefix(COLLECTION_MISSING_ADDON_PREFIX)
                    )
                } else {
                    error.orEmpty()
                }
            },
            topContentPadding = if (isMobile) 12.dp else 8.dp,
            onPreviewItemChanged = viewModel::loadPreviewProvider,
            providerLogos = providerLogos
        )
    }
}

@Composable
private fun CollectionTabBar(
    hasMovies: Boolean,
    hasSeries: Boolean,
    selectedTab: CollectionTab,
    moviesTabFocusRequester: FocusRequester,
    seriesTabFocusRequester: FocusRequester,
    isSportsCollection: Boolean,
    onTabSelected: (CollectionTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val showMovies = hasMovies || !hasSeries
    val showSeries = hasSeries || !hasMovies
    val onlyOne = showMovies xor showSeries
    Row(
        modifier = modifier
            .fillMaxWidth()
            .arvioDpadFocusGroup(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isSportsCollection) {
            CollectionTabChip(
                label = stringResource(R.string.collection_tab_live_upcoming),
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
    val shape = RoundedCornerShape(6.dp)
    val bg = when {
        isSelected -> Color.White
        isFocused -> Color.Transparent
        else -> Color.Transparent
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
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 7.dp)
    ) {
        androidx.tv.material3.Text(
            text = label,
            style = ArflixTypography.sectionTitle.copy(
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                letterSpacing = 0.sp
            ),
            color = fg
        )
    }
}

@Composable
internal fun CollectionItemsGrid(
    catalog: CatalogConfig?,
    onBack: () -> Unit,
    items: List<MediaItem>,
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
    onVisibleDetailsChanged: (List<MediaItem>) -> Unit,
    onNearEnd: () -> Unit,
    isLoading: Boolean,
    isLoadingMore: Boolean,
    emptyMessage: String,
    topContentPadding: androidx.compose.ui.unit.Dp,
    onPreviewItemChanged: (MediaItem) -> Unit = {},
    providerLogos: Map<String, String?> = emptyMap()
) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val layout = collectionGridLayout(maxWidth.value.toInt(), isMobile, usePosterCards)
    val gridColumns = layout.columns
    val cardWidth = layout.cardWidthDp.dp
    val compact = LocalConfiguration.current.screenHeightDp < 480
    var focusedKey by rememberSaveable(catalog?.id, selectedTab) { mutableStateOf<String?>(null) }
    var previewKey by remember(catalog?.id, selectedTab) { mutableStateOf<String?>(null) }
    LaunchedEffect(focusedKey) {
        // Focus feedback stays immediate; only expensive artwork waits for rapid navigation to settle.
        delay(100)
        previewKey = focusedKey
    }
    val previewItem = remember(items, previewKey) {
        items.firstOrNull { "${it.mediaType}-${it.id}" == previewKey } ?: items.firstOrNull()
    }
    val latestPreviewChanged by rememberUpdatedState(onPreviewItemChanged)
    LaunchedEffect(previewItem?.mediaType, previewItem?.id) {
        previewItem?.let { latestPreviewChanged(it) }
    }
    val cardContentType = if (usePosterCards) "poster_card" else "landscape_card"
    val focusBleedPadding = if (usePosterCards) 10.dp else 6.dp
    val scrollScope = rememberCoroutineScope()
    var revealJob by remember(gridState) { mutableStateOf<Job?>(null) }
    var focusedRow by remember(gridState) { mutableStateOf(-1) }
    val focusBleedPx = with(androidx.compose.ui.platform.LocalDensity.current) { focusBleedPadding.toPx() }
    val latestItems by rememberUpdatedState(items)
    val latestGridColumns by rememberUpdatedState(gridColumns)
    val latestOnVisibleItemsChanged by rememberUpdatedState(onVisibleItemsChanged)
    val latestOnVisibleDetailsChanged by rememberUpdatedState(onVisibleDetailsChanged)
    val latestOnNearEnd by rememberUpdatedState(onNearEnd)
    LaunchedEffect(gridState) {
        try {
            snapshotFlow {
                gridState.layoutInfo.visibleItemsInfo.mapNotNull { latestItems.getOrNull(it.index - 1) }
            }.distinctUntilChanged().collect { latestOnVisibleDetailsChanged(it) }
        } finally {
            latestOnVisibleDetailsChanged(emptyList())
        }
    }
    // Collect scroll position without restarting on page-load-size changes —
    // items.size used to live in the key, which relaunched the snapshotFlow on
    // every page append and caused a stutter frame during scroll.
    LaunchedEffect(gridState) {
        snapshotFlow {
            val layout = gridState.layoutInfo
            val last = layout.visibleItemsInfo.lastOrNull()?.index ?: 0
            val mediaIndexes = layout.visibleItemsInfo
                .asSequence()
                .map { it.index - 1 }
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

    Box(Modifier.fillMaxSize().background(appBackgroundDark())) {
        CollectionBackdrop(catalog, previewItem, isMobile,
            collectionSpotlightHeight(isMobile, compact) + 150.dp)
        Column(Modifier.fillMaxSize().padding(top = topContentPadding)) {
        CollectionSpotlight(
            catalog, previewItem, isMobile, compact, onBack,
            providerLogoUrl = providerLogos["${previewItem?.mediaType}_${previewItem?.id}"]
                ?: previewItem?.primaryNetworkLogo,
            clearLogoUrl = cardLogoUrls["${previewItem?.mediaType}_${previewItem?.id}"]
        )
        TvLazyVerticalGrid(
        columns = TvGridCells.Fixed(gridColumns),
        state = gridState,
        // On TV, reveal whole rows ourselves; automatic pivot scrolling follows
        // the focus scale and fights the row animation. Touch retains native scrolling.
        userScrollEnabled = isMobile,
        pivotOffsets = PivotOffsets(parentFraction = 0f),
        modifier = Modifier.weight(1f).fillMaxWidth().arvioDpadFocusGroup().clipToBounds()
            .testTag("collection_grid"),
        contentPadding = PaddingValues(
            start = if (isMobile) 20.dp else 42.dp,
            top = focusBleedPadding,
            end = if (isMobile) 20.dp else 42.dp,
            bottom = 48.dp + focusBleedPadding + LocalBottomBarInset.current
        ),
        verticalArrangement = Arrangement.spacedBy(if (usePosterCards) 18.dp else 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(
            span = { androidx.tv.foundation.lazy.grid.TvGridItemSpan(maxLineSpan) },
            contentType = "header"
        ) {
            CollectionTabBar(
                hasMovies = hasMovies,
                hasSeries = hasSeries,
                selectedTab = selectedTab,
                moviesTabFocusRequester = moviesTabFocusRequester,
                seriesTabFocusRequester = seriesTabFocusRequester,
                isSportsCollection = isSportsCollection,
                onTabSelected = onTabSelected,
                modifier = Modifier.onFocusChanged { state ->
                    if (!isMobile && state.hasFocus) {
                        focusedRow = -1
                        revealJob?.cancel()
                        revealJob = scrollScope.launch { gridState.animateScrollToItem(0) }
                    }
                }
            )
        }
        if (isLoading && items.isEmpty()) {
            val cardHeight = if (usePosterCards) cardWidth * 1.5f else cardWidth * 9f / 16f
            itemsIndexed((1..gridColumns * 3).toList(), contentType = { _, _ -> "skeleton" }) { _, _ ->
                Box(Modifier.fillMaxWidth()) {
                Box(
                    modifier = (if (isMobile) Modifier.fillMaxWidth() else Modifier)
                        .width(cardWidth)
                        .height(cardHeight)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.05f))
                )
                }
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

                // Touch cards fill their measured cell, including after a window resize.
                // TV cards keep Home's fixed width inside the wider grid slot.
                Box(Modifier.fillMaxWidth()) {
                MediaCard(
                    item = item,
                    width = cardWidth,
                    isLandscape = !usePosterCards,
                    logoImageUrl = cardLogoUrl,
                    showTitle = true,
                    titleMaxLines = if (usePosterCards) 2 else 1,
                    titleMinLines = if (usePosterCards) 2 else 1,
                    onFocused = {
                        // Reveal unscaled rows only. TV's automatic pivot follows the animated
                        // card bounds and otherwise nudges the grid on every sideways move.
                        val row = index / gridColumns
                        if (!isMobile && focusedRow != row) {
                            focusedRow = row
                            revealJob?.cancel()
                            revealJob = scrollScope.launch {
                                val layout = gridState.layoutInfo
                                val cell = layout.visibleItemsInfo.firstOrNull { it.index == index + 1 }
                                if (cell != null) {
                                    val top = cell.offset.y.toFloat()
                                    val bottom = top + cell.size.height
                                    val minTop = layout.viewportStartOffset + focusBleedPx
                                    val maxBottom = layout.viewportEndOffset - focusBleedPx
                                    val delta = when {
                                        top < minTop -> top - minTop
                                        bottom > maxBottom -> bottom - maxBottom
                                        else -> 0f
                                    }
                                    if (delta != 0f) gridState.animateScrollBy(delta, tween(160))
                                } else {
                                    gridState.animateScrollToItem(row * gridColumns + 1, -focusBleedPx.toInt())
                                }
                            }
                        }
                        focusedKey = "${item.mediaType}-${item.id}"
                        onItemFocused(item, index)
                        if (items.size > 10 && index >= items.size - 2) onNearEnd()
                    },
                    onClick = { onItemClick(item) },
                    modifier = (if (isMobile) Modifier.fillMaxWidth() else Modifier)
                        .focusRequester(itemFocusRequester).testTag("collection_item_$index")
                )
                }
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
