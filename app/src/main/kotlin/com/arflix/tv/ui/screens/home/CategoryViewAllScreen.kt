package com.arflix.tv.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode
import com.arflix.tv.ui.screens.collections.CollectionItemsGrid
import com.arflix.tv.ui.screens.collections.CollectionTab
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** Collection presentation with the original Home row's shared pagination and ordering. */
@Composable
fun CategoryViewAllScreen(
    categoryId: String,
    viewModel: HomeViewModel,
    onNavigateToDetails: (MediaType, Int) -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val category = uiState.categories.firstOrNull { it.id == categoryId }
    val items = remember(category?.items) { category?.items?.filterNot { it.isPlaceholder }.orEmpty() }
    val hasMore = uiState.categoryHasMoreMap[categoryId] != false
    val usePosterCards = rememberCatalogueRowLayoutMode("home:$categoryId") == CardLayoutMode.POSTER
    var focusedPreview by remember(categoryId) { mutableStateOf<MediaItem?>(null) }
    var enrichedPreview by remember(categoryId) { mutableStateOf<MediaItem?>(null) }
    LaunchedEffect(focusedPreview) {
        enrichedPreview = null
        focusedPreview?.let { item ->
            try {
                enrichedPreview = viewModel.loadViewAllPreview(item)
            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {
                enrichedPreview = item
            }
        }
    }
    CategoryViewAllContent(
        categoryId = categoryId,
        title = category?.let { localizedCategoryTitle(it) }.orEmpty(),
        items = items,
        usePosterCards = usePosterCards,
        cardLogoUrls = viewModel.cardLogoUrls,
        isLoading = category != null && hasMore && items.isEmpty(),
        hasMore = hasMore && items.isNotEmpty(),
        previewItem = enrichedPreview,
        onPreviewItemChanged = {
            focusedPreview = it
            viewModel.onViewAllVisiblePosition(categoryId, items.indexOf(it), 0)
        },
        onVisiblePositionChanged = { last, columns ->
            viewModel.onViewAllVisiblePosition(categoryId, last, columns * 3)
        },
        onNearEnd = { viewModel.loadNextPageForCategory(categoryId) },
        onItemClick = { item ->
            viewModel.cacheItem(item)
            viewModel.cardLogoUrls["${item.mediaType}_${item.id}"]
                ?.takeIf(String::isNotBlank)
                ?.let { viewModel.cacheLogoUrl(item.mediaType, item.id, it) }
            onNavigateToDetails(item.mediaType, item.id)
        },
        onBack = onBack
    )
}

@Composable
internal fun CategoryViewAllContent(
    categoryId: String,
    title: String,
    items: List<MediaItem>,
    usePosterCards: Boolean,
    cardLogoUrls: Map<String, String>,
    isLoading: Boolean,
    hasMore: Boolean,
    previewItem: MediaItem? = null,
    onPreviewItemChanged: (MediaItem) -> Unit,
    onVisiblePositionChanged: (Int, Int) -> Unit,
    onNearEnd: () -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)
    val catalog = remember(categoryId, title) { CatalogConfig(categoryId, title, CatalogSourceType.PREINSTALLED) }
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val gridState = rememberTvLazyGridState()
    var lastFocusedIndex by rememberSaveable(categoryId) { mutableIntStateOf(0) }
    var pendingFocusIndex by remember(categoryId) { mutableIntStateOf(-1) }
    val latestItemCount by rememberUpdatedState(items.size)
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()

    suspend fun restoreFocus() {
        delay(300)
        if (latestItemCount == 0) return
        val target = lastFocusedIndex.coerceIn(0, latestItemCount - 1)
        gridState.scrollToItem(target)
        pendingFocusIndex = target
    }
    // Data can arrive after ON_RESUME; focus once it is actually composed, not an empty grid.
    LaunchedEffect(categoryId, isMobile, items.isNotEmpty()) {
        if (!isMobile && items.isNotEmpty()) restoreFocus()
    }
    DisposableEffect(lifecycleOwner, categoryId, isMobile) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME && !isMobile) scope.launch { restoreFocus() }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    CollectionItemsGrid(
        catalog = catalog,
        onBack = onBack,
        items = items,
        usePosterCards = usePosterCards,
        gridState = gridState,
        pendingFocusIndex = pendingFocusIndex,
        onClearPendingFocus = { pendingFocusIndex = -1 },
        hasMovies = false,
        hasSeries = false,
        cardLogoUrls = cardLogoUrls,
        selectedTab = CollectionTab.MOVIES,
        moviesTabFocusRequester = remember { FocusRequester() },
        seriesTabFocusRequester = remember { FocusRequester() },
        isSportsCollection = false,
        onTabSelected = {},
        onItemClick = onItemClick,
        onItemFocused = { _, index -> lastFocusedIndex = index },
        onVisibleItemsChanged = {},
        onVisibleDetailsChanged = {},
        onNearEnd = onNearEnd,
        isLoading = isLoading,
        isLoadingMore = hasMore,
        emptyMessage = stringResource(R.string.collection_empty),
        topContentPadding = if (isMobile) 12.dp else 8.dp,
        onPreviewItemChanged = onPreviewItemChanged,
        showTabs = false,
        previewItemOverride = previewItem,
        onVisiblePositionChanged = onVisiblePositionChanged
    )
}
