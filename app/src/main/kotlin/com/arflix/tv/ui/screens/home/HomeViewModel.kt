package com.arflix.tv.ui.screens.home

import android.content.Context
import android.util.Log
import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.TraktSyncService
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.SyncStatus
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.cancelAndJoin
import androidx.tracing.Trace
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val isInitialLoad: Boolean = true,
    val categories: List<Category> = emptyList(),
    val error: String? = null,
    // Current hero (may update during transitions)
    val heroItem: MediaItem? = null,
    val heroLogoUrl: String? = null,
    val cardLogoUrls: Map<String, String> = emptyMap(),
    // Previous hero for crossfade (Phase 2.1)
    val previousHeroItem: MediaItem? = null,
    val previousHeroLogoUrl: String? = null,
    // Transition state for animations
    val isHeroTransitioning: Boolean = false,
    val isAuthenticated: Boolean = false,
    // Toast
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.INFO
)

enum class ToastType {
    SUCCESS, ERROR, INFO
}

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val catalogRepository: CatalogRepository,
    private val traktRepository: TraktRepository,
    private val traktSyncService: TraktSyncService,
    private val iptvRepository: IptvRepository,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val watchlistRepository: WatchlistRepository,
    private val imageLoader: ImageLoader,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val networkDispatcher = Dispatchers.IO.limitedParallelism(4)
    private var lastContinueWatchingItems: List<MediaItem> = emptyList()
    private var lastContinueWatchingUpdateMs: Long = 0L
    private var lastResolvedBaseCategories: List<Category> = emptyList()
    private val CONTINUE_WATCHING_REFRESH_MS = 45_000L
    private val HOME_PLACEHOLDER_ITEM_COUNT = 8

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // Debounce job for hero updates (Phase 6.1)
    private var heroUpdateJob: Job? = null
    private var heroDetailsJob: Job? = null
    private var prefetchJob: Job? = null
    private var customCatalogsJob: Job? = null
    private var loadHomeJob: Job? = null
    private var refreshContinueWatchingJob: Job? = null
    private var loadHomeRequestId: Long = 0L
    private val HERO_DEBOUNCE_MS = 80L // Short debounce; focus idle is handled in HomeScreen

    // Phase 6.2-6.3: Fast scroll detection
    private var lastFocusChangeTime = 0L
    private var consecutiveFastChanges = 0
    private val FAST_SCROLL_THRESHOLD_MS = 650L  // Under 650ms = fast scrolling
    private val FAST_SCROLL_DEBOUNCE_MS = 700L   // Higher debounce during fast scroll

    private var lastPrefetchTime = 0L
    private val PREFETCH_DEBOUNCE_MS = 600L

    private val logoPreloadWidth = 300
    private val logoPreloadHeight = 70
    private val cardBackdropWidth = (240 * context.resources.displayMetrics.density)
        .toInt()
        .coerceAtLeast(1)
    private val cardBackdropHeight = (cardBackdropWidth / (16f / 9f))
        .toInt()
        .coerceAtLeast(1)
    private val backdropPreloadWidth = cardBackdropWidth
    private val backdropPreloadHeight = cardBackdropHeight
    private val initialLogoPrefetchRows = 1
    private val initialLogoPrefetchItemsPerRow = 6

    // Track current focus for ahead-of-focus preloading
    private var currentRowIndex = 0
    private var currentItemIndex = 0

    // Track if preloaded data was used to avoid duplicate loading
    private var usedPreloadedData = false

    private val logoCache = ConcurrentHashMap<String, String>()
    private val preloadedRequests = Collections.synchronizedSet(mutableSetOf<String>())

    private fun publishLogoCacheSnapshotIfChanged() {
        val current = _uiState.value.cardLogoUrls
        if (current.size == logoCache.size && logoCache.all { (k, v) -> current[k] == v }) {
            return
        }
        _uiState.value = _uiState.value.copy(cardLogoUrls = logoCache.toMap())
    }

    init {
        loadHomeData()
        viewModelScope.launch {
            // Ensure Continue Watching appears once Trakt tokens are loaded
            traktRepository.isAuthenticated.filter { it }.first()
            refreshContinueWatchingOnly()
        }
        viewModelScope.launch {
            traktSyncService.syncEvents.collect { status ->
                if (status == SyncStatus.COMPLETED) {
                    refreshContinueWatchingOnly()
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            // Warm IPTV/EPG in background shortly after app start so TV page opens with data.
            delay(1200L)
            runCatching {
                iptvRepository.warmupFromCacheOnly()
                iptvRepository.warmXtreamVodCachesIfPossible()
            }
        }
        viewModelScope.launch {
            catalogRepository.observeCatalogs()
                .map { catalogs ->
                    catalogs.joinToString("|") { "${it.id}:${it.title}:${it.sourceUrl.orEmpty()}" }
                }
                .distinctUntilChanged()
                .collect {
                    // Apply catalog reorder/add/remove immediately on Home.
                    loadHomeData()
                }
        }
    }

    /**
     * Set preloaded data from StartupViewModel for instant display.
     * This skips the initial network call since data is already loaded.
     *
     * Shows placeholder Continue Watching cards immediately while real data loads.
     */
    fun setPreloadedData(
        categories: List<Category>,
        heroItem: MediaItem?,
        heroLogoUrl: String?,
        logoCache: Map<String, String>
    ) {

        if (usedPreloadedData) {
            if (logoCache.isNotEmpty()) {
                this.logoCache.putAll(logoCache)
                publishLogoCacheSnapshotIfChanged()
            }
            val currentState = _uiState.value
            if (heroLogoUrl != null && currentState.heroLogoUrl == null) {
                _uiState.value = currentState.copy(heroLogoUrl = heroLogoUrl)
            }
            return
        }
        if (categories.isEmpty()) {
            return
        }

        usedPreloadedData = true

        this.logoCache.putAll(logoCache)

        // Filter out any existing continue_watching from preloaded data
        val filteredCategories = categories.filter { it.id != "continue_watching" }.toMutableList()

        // Show placeholder Continue Watching immediately while real data loads
        // This gives instant visual feedback that something is loading
        val placeholderItems = (1..5).map { index ->
            MediaItem(
                id = -index, // Negative IDs for placeholders
                title = "",
                mediaType = MediaType.MOVIE,
                isPlaceholder = true
            )
        }
        val placeholderContinueWatching = Category(
            id = "continue_watching",
            title = "Continue Watching",
            items = placeholderItems
        )
        filteredCategories.add(0, placeholderContinueWatching)

        // Adjust hero item if it was from continue watching
        val adjustedHeroItem = if (heroItem != null &&
            categories.firstOrNull()?.id == "continue_watching" &&
            categories.firstOrNull()?.items?.any { it.id == heroItem.id } == true) {
            // Hero was from continue watching, use first item from filtered categories
            filteredCategories.getOrNull(1)?.items?.firstOrNull() ?: heroItem
        } else {
            heroItem
        }

        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isInitialLoad = false,
            categories = filteredCategories,
            heroItem = adjustedHeroItem,
            cardLogoUrls = this.logoCache.toMap(),
            heroLogoUrl = if (adjustedHeroItem == heroItem) heroLogoUrl else null,
            error = null
        )
        refreshWatchedBadges()
    }

    private fun loadHomeData() {
        loadHomeJob?.cancel()
        val requestId = ++loadHomeRequestId
        loadHomeJob = viewModelScope.launch loadHome@{
            Trace.beginSection("HomeViewModel.loadHomeData")
            // Skip delay - preloading now happens on profile focus for instant display
            // Only add minimal delay if no preloaded data exists yet
            if (!usedPreloadedData) {
                delay(50) // Minimal delay for LaunchedEffect to potentially set preloaded data
            }
            if (requestId != loadHomeRequestId) return@loadHome

            try {
                val cachedContinueWatching = traktRepository.preloadContinueWatchingCache()
                val savedCatalogs = withContext(networkDispatcher) {
                    runCatching {
                        catalogRepository.ensurePreinstalledDefaults(
                            mediaRepository.getDefaultCatalogConfigs()
                        )
                    }.getOrElse { mediaRepository.getDefaultCatalogConfigs() }
                }

                // When Home is opened from profile selection, avoid an empty frame by showing
                // profile-ordered skeleton rows immediately while real catalogs load.
                if (_uiState.value.categories.isEmpty()) {
                    val skeletonCategories = buildProfileSkeletonCategories(
                        savedCatalogs = savedCatalogs,
                        cachedContinueWatching = cachedContinueWatching
                    )
                    if (requestId != loadHomeRequestId) return@loadHome
                    if (skeletonCategories.isNotEmpty()) {
                        val skeletonHero = skeletonCategories
                            .asSequence()
                            .flatMap { it.items.asSequence() }
                            .firstOrNull { !it.isPlaceholder }
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            isInitialLoad = false,
                            categories = skeletonCategories,
                            heroItem = skeletonHero,
                            heroLogoUrl = null,
                            error = null
                        )
                    }
                } else {
                    // Keep preloaded/previous UI visible and refresh in background.
                    _uiState.value = _uiState.value.copy(isLoading = false, error = null)
                }

                val currentBaseCategories = _uiState.value.categories.filter { it.id != "continue_watching" }
                val categories = withContext(networkDispatcher) {
                    val baseCategories = runCatching {
                        mediaRepository.getHomeCategories()
                    }.getOrElse { emptyList() }

                    val baseById = LinkedHashMap<String, Category>().apply {
                        currentBaseCategories.forEach { put(it.id, it) }
                        baseCategories.forEach { put(it.id, it) }
                    }

                    val preinstalled = savedCatalogs
                        .filter { it.isPreinstalled }
                        .mapNotNull { baseById[it.id] }

                    val resolved = mutableListOf<Category>()
                    if (preinstalled.isNotEmpty()) {
                        resolved.addAll(preinstalled)
                    } else if (baseCategories.isNotEmpty()) {
                        resolved.addAll(baseCategories)
                    } else if (currentBaseCategories.isNotEmpty()) {
                        resolved.addAll(currentBaseCategories)
                    } else if (lastResolvedBaseCategories.isNotEmpty()) {
                        resolved.addAll(lastResolvedBaseCategories)
                    }
                    resolved
                }
                if (categories.any { it.id != "continue_watching" }) {
                    lastResolvedBaseCategories = categories.filter { it.id != "continue_watching" }
                }
                if (requestId != loadHomeRequestId) return@loadHome

                // Only show continue watching from profile-specific cache
                // Don't use lastContinueWatchingItems fallback to prevent cross-profile data leakage
                if (cachedContinueWatching.isNotEmpty()) {
                    val continueWatchingCategory = Category(
                        id = "continue_watching",
                        title = "Continue Watching",
                        items = cachedContinueWatching.map { it.toMediaItem() }
                    )
                    continueWatchingCategory.items.forEach { mediaRepository.cacheItem(it) }
                    lastContinueWatchingItems = continueWatchingCategory.items
                    lastContinueWatchingUpdateMs = SystemClock.elapsedRealtime()
                    categories.add(0, continueWatchingCategory)
                }

                val heroItem = categories.firstOrNull()?.items?.firstOrNull()

                // Preload logos for the first visible rows so card overlays appear immediately.
                val itemsToPreload = categories
                    .take(initialLogoPrefetchRows)
                    .flatMap { it.items.take(initialLogoPrefetchItemsPerRow) }
                val logoJobs = itemsToPreload.map { item ->
                    async(networkDispatcher) {
                        val key = "${item.mediaType}_${item.id}"
                        try {
                            val logoUrl = mediaRepository.getLogoUrl(item.mediaType, item.id)
                            if (logoUrl != null) key to logoUrl else null
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
                val logoResults = logoJobs.awaitAll().filterNotNull().toMap()
                if (requestId != loadHomeRequestId) return@loadHome

                // Phase 1.2: Preload actual images with Coil
                preloadLogoImages(logoResults.values.toList())

                // Also preload backdrop images for first row
                val backdropUrls = categories.firstOrNull()?.items?.take(4)?.mapNotNull {
                    it.backdrop ?: it.image
                } ?: emptyList()
                preloadBackdropImages(backdropUrls)

                val heroLogoUrl = heroItem?.let { item ->
                    val key = "${item.mediaType}_${item.id}"
                    logoCache[key] ?: logoResults[key]
                }

                logoCache.putAll(logoResults)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isInitialLoad = false,
                    categories = categories,
                    heroItem = heroItem,
                    cardLogoUrls = logoCache.toMap(),
                    heroLogoUrl = heroLogoUrl,
                    isAuthenticated = traktRepository.isAuthenticated.first(),
                    error = null
                )
                refreshWatchedBadges()
                val allCatalogs = catalogRepository.getCatalogs()
                loadCustomCatalogsIncrementally(allCatalogs)

                viewModelScope.launch cw@{
                    if (requestId != loadHomeRequestId) return@cw
                    val continueWatchingDeferred = async {
                        try {
                            traktRepository.getContinueWatching()
                        } catch (_: Exception) {
                            emptyList()
                        }
                    }
                    // Also query history fallback so cloud-synced progress can appear
                    // even when Trakt isn't connected for this profile.
                    val historyDeferred: Deferred<List<ContinueWatchingItem>> = async {
                        loadContinueWatchingFromHistory()
                    }

                    val freshContinueWatching = withTimeoutOrNull(6_000L) {
                        continueWatchingDeferred.await()
                    } ?: emptyList()
                    val historyFallback = if (
                        freshContinueWatching.isEmpty() &&
                        cachedContinueWatching.isEmpty()
                    ) {
                        historyDeferred.await()
                    } else {
                        emptyList()
                    }

                    val resolvedContinueWatching = when {
                        freshContinueWatching.isNotEmpty() -> freshContinueWatching
                        historyFallback.isNotEmpty() -> historyFallback
                        else -> emptyList()
                    }
                    if (requestId != loadHomeRequestId) return@cw

                    if (resolvedContinueWatching.isNotEmpty()) {
                        val mergedContinueWatching = mergeContinueWatchingResumeData(resolvedContinueWatching)
                        val continueWatchingCategory = Category(
                            id = "continue_watching",
                            title = "Continue Watching",
                            items = mergedContinueWatching.map { it.toMediaItem() }
                        )
                        continueWatchingCategory.items.forEach { mediaRepository.cacheItem(it) }
                        lastContinueWatchingItems = continueWatchingCategory.items
                        lastContinueWatchingUpdateMs = SystemClock.elapsedRealtime()
                        val updated = _uiState.value.categories.toMutableList()
                        val index = updated.indexOfFirst { it.id == "continue_watching" }
                        if (index >= 0) {
                            updated[index] = continueWatchingCategory
                        } else {
                            updated.add(0, continueWatchingCategory)
                        }
                        _uiState.value = _uiState.value.copy(categories = updated)
                    }
                }
              } catch (e: Exception) {
                if (requestId != loadHomeRequestId) return@loadHome
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isInitialLoad = false,
                    error = if (_uiState.value.categories.isEmpty()) e.message ?: "Failed to load content" else null
                )
            } finally {
                Trace.endSection()
            }
        }
    }

    private fun loadCustomCatalogsIncrementally(savedCatalogs: List<com.arflix.tv.data.model.CatalogConfig>) {
        customCatalogsJob?.cancel()
        customCatalogsJob = viewModelScope.launch(networkDispatcher) {
            val customCatalogs = savedCatalogs.filter { cfg ->
                !cfg.isPreinstalled ||
                    cfg.id.startsWith("custom_") ||
                    !cfg.sourceUrl.isNullOrBlank() ||
                    !cfg.sourceRef.isNullOrBlank()
            }
            if (customCatalogs.isEmpty()) return@launch
            val customIds = customCatalogs.map { it.id }.toSet()
            val baseCategories = _uiState.value.categories.filterNot { customIds.contains(it.id) }
            val baseById = baseCategories.associateBy { it.id }

            val loadedById = LinkedHashMap<String, Category>()
            fun publishMerged(currentState: HomeUiState) {
                val continueWatching = currentState.categories.firstOrNull {
                    it.id == "continue_watching" && it.items.isNotEmpty()
                }
                val merged = mutableListOf<Category>()
                if (continueWatching != null) {
                    merged.add(continueWatching)
                }
                savedCatalogs.forEach { cfg ->
                    val category = if (customIds.contains(cfg.id)) {
                        loadedById[cfg.id]
                    } else {
                        baseById[cfg.id] ?: currentState.categories.firstOrNull { it.id == cfg.id }
                    }
                    if (category != null && category.items.isNotEmpty()) {
                        merged.add(category)
                    }
                }
                if (merged.isNotEmpty()) {
                    currentState.heroItem?.let { hero ->
                        if (merged.none { cat -> cat.items.any { it.id == hero.id && it.mediaType == hero.mediaType } }) {
                            val fallbackHero = merged.firstOrNull()?.items?.firstOrNull()
                            _uiState.value = currentState.copy(categories = merged, heroItem = fallbackHero)
                            return
                        }
                    }
                    _uiState.value = currentState.copy(categories = merged)
                }
            }

            customCatalogs.forEach { catalog ->
                var bestLoaded: Category? = null
                for (maxItems in listOf(20, 30, 40)) {
                    val candidate = runCatching {
                        mediaRepository.loadCustomCatalog(catalog, maxItems = maxItems)
                    }.getOrNull()

                    if (candidate != null && candidate.items.isNotEmpty()) {
                        val currentBestCount = bestLoaded?.items?.size ?: 0
                        if (candidate.items.size > currentBestCount) {
                            bestLoaded = candidate
                        }
                        if (candidate.items.size >= 40) break
                    }
                }
                val loaded = bestLoaded ?: return@forEach

                loadedById[catalog.id] = loaded
                val current = _uiState.value
                publishMerged(current)
            }
        }
    }

    private fun buildProfileSkeletonCategories(
        savedCatalogs: List<com.arflix.tv.data.model.CatalogConfig>,
        cachedContinueWatching: List<ContinueWatchingItem>
    ): List<Category> {
        val placeholderItems = (1..HOME_PLACEHOLDER_ITEM_COUNT).map { index ->
            MediaItem(
                id = -index,
                title = "",
                mediaType = MediaType.MOVIE,
                isPlaceholder = true
            )
        }

        val rows = mutableListOf<Category>()
        if (cachedContinueWatching.isNotEmpty()) {
            rows.add(
                Category(
                    id = "continue_watching",
                    title = "Continue Watching",
                    items = cachedContinueWatching.map { it.toMediaItem() }
                )
            )
        } else {
            rows.add(
                Category(
                    id = "continue_watching",
                    title = "Continue Watching",
                    items = placeholderItems
                )
            )
        }

        savedCatalogs.forEach { cfg ->
            rows.add(
                Category(
                    id = cfg.id,
                    title = cfg.title,
                    items = placeholderItems
                )
            )
        }

        return rows
    }

    /**
     * Phase 1.2: Preload images into Coil's memory/disk cache
     * Uses target display sizes to reduce decode overhead.
     */
    private fun preloadImagesWithCoil(urls: List<String>, width: Int, height: Int) {
        if (preloadedRequests.size > 4_000) {
            preloadedRequests.clear()
        }
        val uniqueUrls = urls.filter { url ->
            preloadedRequests.add("$url|${width}x${height}")
        }.take(8)
        if (uniqueUrls.isEmpty()) return

        uniqueUrls.forEach { url ->
            val request = ImageRequest.Builder(context)
                .data(url)
                .size(width.coerceAtLeast(1), height.coerceAtLeast(1))
                .precision(Precision.INEXACT)
                .allowHardware(true)
                .build()
            imageLoader.enqueue(request)
        }
    }

    private fun preloadLogoImages(urls: List<String>) {
        preloadImagesWithCoil(urls, logoPreloadWidth, logoPreloadHeight)
    }

    private fun preloadBackdropImages(urls: List<String>) {
        preloadImagesWithCoil(urls, backdropPreloadWidth, backdropPreloadHeight)
    }

    fun refresh() {
        loadHomeData()
    }

    fun refreshContinueWatchingOnly() {
        refreshContinueWatchingJob?.cancel()
        refreshContinueWatchingJob = viewModelScope.launch {
            try {
                val now = SystemClock.elapsedRealtime()
                val startCategories = _uiState.value.categories
                val continueWatchingIndexAtStart = startCategories.indexOfFirst { it.id == "continue_watching" }
                val existingContinueWatching = startCategories.getOrNull(continueWatchingIndexAtStart)
                val hasPlaceholders = existingContinueWatching?.items?.any { it.isPlaceholder } == true

                // Allow refresh if we have placeholders (need to replace them), otherwise throttle
                if (!hasPlaceholders && now - lastContinueWatchingUpdateMs < CONTINUE_WATCHING_REFRESH_MS) return@launch

                val continueWatching = try {
                    traktRepository.getContinueWatching()
                } catch (e: Exception) {
                    emptyList()
                }
                val cachedContinueWatching = traktRepository.getCachedContinueWatching()
                val historyFallback = if (continueWatching.isEmpty() && cachedContinueWatching.isEmpty()) {
                    loadContinueWatchingFromHistory()
                } else {
                    emptyList()
                }
                // Priority: Fresh Trakt data > Cached data > History fallback > Last known good data
                val resolvedContinueWatching = when {
                    continueWatching.isNotEmpty() -> continueWatching
                    cachedContinueWatching.isNotEmpty() -> cachedContinueWatching
                    historyFallback.isNotEmpty() -> historyFallback
                    else -> emptyList()
                }

                if (resolvedContinueWatching.isNotEmpty()) {
                    val mergedContinueWatching = mergeContinueWatchingResumeData(resolvedContinueWatching)
                    val continueWatchingCategory = Category(
                        id = "continue_watching",
                        title = "Continue Watching",
                        items = mergedContinueWatching.map { it.toMediaItem() }
                    )
                    continueWatchingCategory.items.forEach { mediaRepository.cacheItem(it) }
                    lastContinueWatchingItems = continueWatchingCategory.items
                    lastContinueWatchingUpdateMs = now
                    val latestCategories = _uiState.value.categories.toMutableList()
                    val continueWatchingIndex = latestCategories.indexOfFirst { it.id == "continue_watching" }
                    if (continueWatchingIndex >= 0) {
                        latestCategories[continueWatchingIndex] = continueWatchingCategory
                    } else {
                        latestCategories.add(0, continueWatchingCategory)
                    }
                    _uiState.value = _uiState.value.copy(categories = latestCategories)
                    refreshWatchedBadges()
                } else {
                    // No new data from any source
                    val latestCategories = _uiState.value.categories.toMutableList()
                    val continueWatchingIndex = latestCategories.indexOfFirst { it.id == "continue_watching" }
                    val latestHasPlaceholders = latestCategories
                        .getOrNull(continueWatchingIndex)
                        ?.items
                        ?.any { it.isPlaceholder } == true
                    if (hasPlaceholders) {
                        // We had placeholders but no data loaded - remove the placeholder category
                        if (continueWatchingIndex >= 0) {
                            latestCategories.removeAt(continueWatchingIndex)
                            _uiState.value = _uiState.value.copy(categories = latestCategories)
                        }
                    } else if (!latestHasPlaceholders && continueWatchingIndex >= 0) {
                        // Continue Watching exists with real data - preserve it exactly as is
                        return@launch
                    } else if (lastContinueWatchingItems.isNotEmpty()) {
                        // UI doesn't have Continue Watching but we have last known good items - restore them
                        val continueWatchingCategory = Category(
                            id = "continue_watching",
                            title = "Continue Watching",
                            items = lastContinueWatchingItems
                        )
                        latestCategories.add(0, continueWatchingCategory)
                        _uiState.value = _uiState.value.copy(categories = latestCategories)
                    }
                    // Else: No data anywhere - nothing to show, UI already doesn't have it
                }
            } catch (e: Exception) {
                // Silently fail - don't clear existing data on error
            }
        }
    }

    private suspend fun loadContinueWatchingFromHistory(): List<ContinueWatchingItem> {
        return try {
            val entries = watchHistoryRepository.getContinueWatching()
            if (entries.isEmpty()) return emptyList()
            entries.distinctBy { entry ->
                "${entry.media_type}:${entry.show_tmdb_id}:${entry.season ?: -1}:${entry.episode ?: -1}"
            }.mapNotNull { entry ->
                val mediaType = if (entry.media_type == "tv") MediaType.TV else MediaType.MOVIE
                ContinueWatchingItem(
                    id = entry.show_tmdb_id,
                    title = entry.title ?: return@mapNotNull null,
                    mediaType = mediaType,
                    progress = (entry.progress * 100f).toInt().coerceIn(0, 100),
                    resumePositionSeconds = entry.position_seconds.coerceAtLeast(0L),
                    durationSeconds = entry.duration_seconds.coerceAtLeast(0L),
                    season = entry.season,
                    episode = entry.episode,
                    episodeTitle = entry.episode_title,
                    backdropPath = entry.backdrop_path,
                    posterPath = entry.poster_path
                )
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    private suspend fun mergeContinueWatchingResumeData(
        items: List<ContinueWatchingItem>
    ): List<ContinueWatchingItem> {
        if (items.isEmpty()) return emptyList()
        return try {
            val historyEntries = watchHistoryRepository.getContinueWatching()
            if (historyEntries.isEmpty()) return items

            val sortedHistory = historyEntries.sortedByDescending { it.updated_at ?: it.paused_at.orEmpty() }
            val byExactKey = sortedHistory.associateBy { entry ->
                "${entry.media_type}:${entry.show_tmdb_id}:${entry.season ?: -1}:${entry.episode ?: -1}"
            }
            val byShowKey = sortedHistory.associateBy { entry ->
                "${entry.media_type}:${entry.show_tmdb_id}"
            }

            items.map { item ->
                val mediaTypeKey = if (item.mediaType == MediaType.TV) "tv" else "movie"
                val exactKey = "$mediaTypeKey:${item.id}:${item.season ?: -1}:${item.episode ?: -1}"
                val showKey = "$mediaTypeKey:${item.id}"
                val match = byExactKey[exactKey] ?: byShowKey[showKey]
                if (match == null) {
                    item
                } else {
                    item.copy(
                        progress = (match.progress * 100f).toInt().coerceIn(0, 100),
                        resumePositionSeconds = match.position_seconds.coerceAtLeast(0L),
                        durationSeconds = match.duration_seconds.coerceAtLeast(0L),
                        season = item.season ?: match.season,
                        episode = item.episode ?: match.episode,
                        episodeTitle = item.episodeTitle ?: match.episode_title
                    )
                }
            }
        } catch (_: Exception) {
            items
        }
    }

    private fun refreshWatchedBadges() {
        viewModelScope.launch(networkDispatcher) {
            val isAuth = traktRepository.isAuthenticated.first()
            if (!isAuth) return@launch

            traktRepository.initializeWatchedCache()
            val categories = _uiState.value.categories
            if (categories.isEmpty()) return@launch

            val watchedMovies = traktRepository.getWatchedMoviesFromCache()
            val showItems = categories
                .flatMap { it.items }
                .filter { it.mediaType == MediaType.TV }
                .distinctBy { it.id }

            val showWatched = mutableMapOf<Int, Boolean>()
            // Use hasWatchedEpisodes to show checkmark for any watched episodes (not just fully watched)
            showItems.forEach { item ->
                showWatched[item.id] = traktRepository.hasWatchedEpisodes(item.id)
            }

            val updatedCategories = categories.map { category ->
                // Skip Continue Watching - those items are in progress, not watched
                if (category.id == "continue_watching") {
                    category
                } else {
                    category.copy(
                        items = category.items.map { item ->
                            when (item.mediaType) {
                                MediaType.MOVIE -> item.copy(isWatched = watchedMovies.contains(item.id))
                                MediaType.TV -> item.copy(isWatched = showWatched[item.id] == true)
                            }
                        }
                    )
                }
            }

            val heroItem = _uiState.value.heroItem
            val updatedHero = heroItem?.let { hero ->
                updatedCategories.asSequence()
                    .flatMap { it.items.asSequence() }
                    .firstOrNull { it.id == hero.id && it.mediaType == hero.mediaType }
                    ?: hero
            }

            _uiState.value = _uiState.value.copy(
                categories = updatedCategories,
                heroItem = updatedHero
            )
        }
    }

    /**
     * Phase 1.4 & 6.1 & 6.2-6.3: Update hero with adaptive debouncing
     * Uses fast-scroll detection for smoother experience during rapid navigation
     */
    fun updateHeroItem(item: MediaItem) {
        Trace.beginSection("HomeViewModel.updateHeroItem")
        val cacheKey = "${item.mediaType}_${item.id}"
        val cachedLogo = logoCache[cacheKey]

        // Phase 6.2-6.3: Detect fast scrolling
        val currentTime = System.currentTimeMillis()
        val timeSinceLastChange = currentTime - lastFocusChangeTime
        lastFocusChangeTime = currentTime

        val isFastScrolling = timeSinceLastChange < FAST_SCROLL_THRESHOLD_MS
        if (isFastScrolling) {
            consecutiveFastChanges++
        } else {
            consecutiveFastChanges = 0
        }

        // Adaptive debounce: higher during fast scroll sequences
        val debounceMs = when {
            consecutiveFastChanges > 3 -> FAST_SCROLL_DEBOUNCE_MS  // Very fast scroll
            consecutiveFastChanges > 1 -> HERO_DEBOUNCE_MS + 50    // Moderate fast scroll
            cachedLogo != null -> 0L  // Cached = instant
            else -> HERO_DEBOUNCE_MS  // Normal debounce
        }

        // Phase 1.4: If logo is cached and not fast-scrolling, update immediately
        val fastScrolling = consecutiveFastChanges > 1
        if (cachedLogo != null && !fastScrolling) {
            heroUpdateJob?.cancel()
            performHeroUpdate(item, cachedLogo)
            scheduleHeroDetailsFetch(item, fastScrolling)
            Trace.endSection()
            return
        }

        // Phase 6.1 + 6.2-6.3: Adaptive debounce
        heroUpdateJob?.cancel()
        heroDetailsJob?.cancel()
        heroUpdateJob = viewModelScope.launch {
            if (debounceMs > 0) {
                delay(debounceMs)
            }

            // Check if still the current focus after debounce
            val currentCachedLogo = logoCache[cacheKey]
            performHeroUpdate(item, currentCachedLogo)
            scheduleHeroDetailsFetch(item, fastScrolling)

            // Fetch logo async if not cached
            if (currentCachedLogo == null) {
                try {
                    val logoUrl = withContext(networkDispatcher) {
                        mediaRepository.getLogoUrl(item.mediaType, item.id)
                    }
                    if (logoUrl != null && _uiState.value.heroItem?.id == item.id) {
                        logoCache[cacheKey] = logoUrl
                        _uiState.value = _uiState.value.copy(
                            cardLogoUrls = logoCache.toMap(),
                            heroLogoUrl = logoUrl,
                            isHeroTransitioning = false
                        )
                        // Preload the logo image
                        preloadLogoImages(listOf(logoUrl))
                    }
                } catch (e: Exception) {
                    // Logo fetch failed
                }
            }
        }
        Trace.endSection()
    }

    private fun performHeroUpdate(item: MediaItem, logoUrl: String?) {
        val currentState = _uiState.value

        // Save previous hero for crossfade animation
        _uiState.value = currentState.copy(
            previousHeroItem = currentState.heroItem,
            previousHeroLogoUrl = currentState.heroLogoUrl,
            heroItem = item,
            heroLogoUrl = logoUrl,
            isHeroTransitioning = true
        )
    }

    private fun scheduleHeroDetailsFetch(item: MediaItem, fastScrolling: Boolean) {
        heroDetailsJob?.cancel()
        heroDetailsJob = viewModelScope.launch(networkDispatcher) {
            delay(if (fastScrolling) 900L else 250L)
            val currentHero = _uiState.value.heroItem
            if (currentHero?.id != item.id) return@launch

            try {
                val details = if (item.mediaType == MediaType.MOVIE) {
                    mediaRepository.getMovieDetails(item.id)
                } else {
                    mediaRepository.getTvDetails(item.id)
                }

                val updatedItem = currentHero.copy(
                    duration = details.duration.ifEmpty { currentHero.duration },
                    releaseDate = details.releaseDate ?: currentHero.releaseDate,
                    imdbRating = details.imdbRating.ifEmpty { currentHero.imdbRating },
                    tmdbRating = details.tmdbRating.ifEmpty { currentHero.tmdbRating },
                    budget = details.budget ?: currentHero.budget
                )
                _uiState.value = _uiState.value.copy(
                    heroItem = updatedItem,
                    isHeroTransitioning = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isHeroTransitioning = false)
            }
        }
    }

    /**
     * Phase 1.3: Ahead-of-focus preloading
     * Call this when focus changes to preload nearby items
     */
    fun onFocusChanged(rowIndex: Int, itemIndex: Int, shouldPrefetch: Boolean = true) {
        currentRowIndex = rowIndex
        currentItemIndex = itemIndex
        if (!shouldPrefetch) {
            prefetchJob?.cancel()
            return
        }

        prefetchJob?.cancel()
        prefetchJob = viewModelScope.launch(networkDispatcher) {
            val now = System.currentTimeMillis()
            if (now - lastPrefetchTime < PREFETCH_DEBOUNCE_MS) return@launch
            lastPrefetchTime = now

            val categories = _uiState.value.categories
            if (rowIndex < 0 || rowIndex >= categories.size) return@launch

            val category = categories[rowIndex]
            val currentCache = logoCache

            if (category.items.isEmpty()) return@launch

            // Preload items ahead (N+1 to N+3)
            val itemsAhead = (itemIndex + 1..minOf(itemIndex + 3, category.items.size - 1))
                .mapNotNull { category.items.getOrNull(it) }
                .filter { item ->
                    val key = "${item.mediaType}_${item.id}"
                    !currentCache.containsKey(key)
                }

            if (itemsAhead.isEmpty()) return@launch

            // Fetch logos for items ahead
            val logoJobs = itemsAhead.map { item ->
                async(networkDispatcher) {
                    val key = "${item.mediaType}_${item.id}"
                    try {
                        val logoUrl = mediaRepository.getLogoUrl(item.mediaType, item.id)
                        if (logoUrl != null) key to logoUrl else null
                    } catch (e: Exception) {
                        null
                    }
                }
            }
            val newLogos = logoJobs.awaitAll().filterNotNull().toMap()

            if (newLogos.isNotEmpty()) {
                logoCache.putAll(newLogos)
                publishLogoCacheSnapshotIfChanged()
                // Preload actual images
                preloadLogoImages(newLogos.values.toList())
            }

            // Also preload backdrop images for items ahead
            val backdropUrls = itemsAhead.mapNotNull { it.backdrop ?: it.image }
            preloadBackdropImages(backdropUrls)
        }
    }

    /**
     * Phase 1.1: Preload logos for category + next 2 categories
     */
    fun preloadLogosForCategory(categoryIndex: Int) {
        val categories = _uiState.value.categories

        // Preload current + next category
        listOf(categoryIndex, categoryIndex + 1).forEach { idx ->
            if (idx < 0 || idx >= categories.size) return@forEach

            viewModelScope.launch(networkDispatcher) {
                val category = categories[idx]
                val currentCache = logoCache

                // Preload a fuller batch per row for smoother logo overlays.
                val itemsToLoad = category.items.take(6).filter { item ->
                    val key = "${item.mediaType}_${item.id}"
                    !currentCache.containsKey(key)
                }

                if (itemsToLoad.isEmpty()) return@launch

                val logoJobs = itemsToLoad.map { item ->
                    async(networkDispatcher) {
                        val key = "${item.mediaType}_${item.id}"
                        try {
                            val logoUrl = mediaRepository.getLogoUrl(item.mediaType, item.id)
                            if (logoUrl != null) key to logoUrl else null
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
                val newLogos = logoJobs.awaitAll().filterNotNull().toMap()

                if (newLogos.isNotEmpty()) {
                    logoCache.putAll(newLogos)
                    publishLogoCacheSnapshotIfChanged()
                    // Preload actual images
                    preloadLogoImages(newLogos.values.toList())
                }

                // Also preload backdrops
                val backdropUrls = category.items.take(4).mapNotNull { it.backdrop ?: it.image }
                preloadBackdropImages(backdropUrls)
            }
        }
    }

    /**
     * Clear hero transition state after animation completes
     */
    fun onHeroTransitionComplete() {
        _uiState.value = _uiState.value.copy(
            previousHeroItem = null,
            previousHeroLogoUrl = null,
            isHeroTransitioning = false
        )
    }

    fun toggleWatchlist(item: MediaItem) {
        viewModelScope.launch {
            try {
                val isInWatchlist = watchlistRepository.isInWatchlist(item.mediaType, item.id)
                if (isInWatchlist) {
                    watchlistRepository.removeFromWatchlist(item.mediaType, item.id)
                } else {
                    watchlistRepository.addToWatchlist(item.mediaType, item.id)
                }
                _uiState.value = _uiState.value.copy(
                    toastMessage = if (isInWatchlist) "Removed from watchlist" else "Added to watchlist",
                    toastType = ToastType.SUCCESS
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to update watchlist",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun toggleWatched(item: MediaItem) {
        viewModelScope.launch {
            try {
                if (item.mediaType == MediaType.MOVIE) {
                    if (item.isWatched) {
                        traktRepository.markMovieUnwatched(item.id)
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "Marked as unwatched",
                            toastType = ToastType.SUCCESS
                        )
                    } else {
                        traktRepository.markMovieWatched(item.id)
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "Marked as watched",
                            toastType = ToastType.SUCCESS
                        )
                    }
                } else {
                    val nextEp = item.nextEpisode
                    if (nextEp != null) {
                        traktRepository.markEpisodeWatched(item.id, nextEp.seasonNumber, nextEp.episodeNumber)
                        watchHistoryRepository.removeFromHistory(item.id, nextEp.seasonNumber, nextEp.episodeNumber)

                        val updatedCategories = _uiState.value.categories.map { category ->
                            if (category.id == "continue_watching") {
                                category.copy(items = category.items.filter { it.id != item.id })
                            } else {
                                category
                            }
                        }.filter { category ->
                            category.id != "continue_watching" || category.items.isNotEmpty()
                        }

                        _uiState.value = _uiState.value.copy(
                            categories = updatedCategories,
                            toastMessage = "S${nextEp.seasonNumber}E${nextEp.episodeNumber} marked as watched",
                            toastType = ToastType.SUCCESS
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "No episode info available",
                            toastType = ToastType.ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to update watched status",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun markWatched(item: MediaItem) {
        viewModelScope.launch {
            try {
                if (item.mediaType == MediaType.MOVIE) {
                    if (!item.isWatched) {
                        traktRepository.markMovieWatched(item.id)
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "Marked as watched",
                            toastType = ToastType.SUCCESS
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "Already watched",
                            toastType = ToastType.INFO
                        )
                    }
                } else {
                    val nextEp = item.nextEpisode
                    if (nextEp != null) {
                        traktRepository.markEpisodeWatched(item.id, nextEp.seasonNumber, nextEp.episodeNumber)
                        watchHistoryRepository.removeFromHistory(item.id, nextEp.seasonNumber, nextEp.episodeNumber)

                        val updatedCategories = _uiState.value.categories.map { category ->
                            if (category.id == "continue_watching") {
                                category.copy(items = category.items.filter { it.id != item.id })
                            } else {
                                category
                            }
                        }.filter { category ->
                            category.id != "continue_watching" || category.items.isNotEmpty()
                        }

                        _uiState.value = _uiState.value.copy(
                            categories = updatedCategories,
                            toastMessage = "S${nextEp.seasonNumber}E${nextEp.episodeNumber} marked as watched",
                            toastType = ToastType.SUCCESS
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "No episode info available",
                            toastType = ToastType.ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to update watched status",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }

    suspend fun isInWatchlist(item: MediaItem): Boolean {
        return watchlistRepository.isInWatchlist(item.mediaType, item.id)
    }

    fun removeFromContinueWatching(item: MediaItem) {
        viewModelScope.launch {
            try {
                val season = if (item.mediaType == MediaType.TV) item.nextEpisode?.seasonNumber else null
                val episode = if (item.mediaType == MediaType.TV) item.nextEpisode?.episodeNumber else null

                watchHistoryRepository.removeFromHistory(item.id, season, episode)
                traktRepository.deletePlaybackForContent(item.id, item.mediaType)
                traktRepository.dismissContinueWatching(item)

                val updatedCategories = _uiState.value.categories.map { category ->
                    if (category.id == "continue_watching") {
                        category.copy(items = category.items.filter { it.id != item.id })
                    } else {
                        category
                    }
                }.filter { category ->
                    category.id != "continue_watching" || category.items.isNotEmpty()
                }

                _uiState.value = _uiState.value.copy(
                    categories = updatedCategories,
                    toastMessage = "Removed from Continue Watching",
                    toastType = ToastType.SUCCESS
                )
                updatedCategories.firstOrNull { it.id == "continue_watching" }?.let { category ->
                    lastContinueWatchingItems = category.items
                    lastContinueWatchingUpdateMs = SystemClock.elapsedRealtime()
                } ?: run {
                    lastContinueWatchingItems = emptyList()
                    lastContinueWatchingUpdateMs = SystemClock.elapsedRealtime()
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to remove from Continue Watching",
                    toastType = ToastType.ERROR
                )
            }
        }
    }
}
