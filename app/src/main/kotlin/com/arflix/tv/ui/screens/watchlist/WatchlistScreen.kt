package com.arflix.tv.ui.screens.watchlist

import android.os.SystemClock
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.MutatePriority
import kotlin.math.abs
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed as gridItemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarHeight
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import com.arflix.tv.ui.components.rememberCardLayoutMode
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.tr

private enum class WatchlistFocusZone {
    TOP_BAR,
    LIST_SELECTOR,
    CONTENT,
    EMPTY_STATE
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onNavigateToDetails: (MediaType, Int) -> Unit = { _, _ -> },
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: (String?) -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val logoUrls by viewModel.logoUrls.collectAsStateWithLifecycle()
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    // Follow system card layout setting (Poster vs Landscape)
    val usePosterCards = rememberCardLayoutMode() == CardLayoutMode.POSTER
    val contentStartPadding = if (isMobile) 16.dp else 36.dp
    val cardWidth = if (isMobile) {
        if (usePosterCards) {
            ((screenWidth - 62.dp) / 2).coerceIn(112.dp, 150.dp)
        } else {
            ((screenWidth - 62.dp) / 2).coerceIn(138.dp, 200.dp)
        }
    } else {
        if (usePosterCards) 105.dp else 210.dp
    }
    val gridColumns = if (isMobile) {
        2
    } else {
        if (usePosterCards) {
            ((screenWidth - (contentStartPadding * 2)) / (cardWidth + 14.dp)).toInt().coerceIn(5, 8)
        } else {
            ((screenWidth - (contentStartPadding * 2)) / (cardWidth + 14.dp)).toInt().coerceIn(3, 5)
        }
    }

    val rootFocusRequester = remember { FocusRequester() }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 3 else 2) }

    var focusZone by remember { mutableStateOf(WatchlistFocusZone.CONTENT) }
    var listSelectorFocusIndex by remember { mutableIntStateOf(0) }
    var focusedSectionIndex by remember { mutableIntStateOf(uiState.lastFocusedSectionIndex) }
    var focusedItemIndex by remember { mutableIntStateOf(uiState.lastFocusedItemIndex) }
    var enterKeyDownTimeMs by remember { mutableLongStateOf(-1L) }
    val longPressThresholdMs = 500L

    val sectionItemIndices = remember { mutableMapOf<String, Int>() }

    val watchlistColumnState = rememberLazyListState()
    val gridState = rememberLazyGridState()
    val listSelectorRowState = rememberLazyListState()

    val sources = uiState.sources
    val selectedSourceIndex = remember(sources, uiState.selectedSourceId) {
        sources.indexOfFirst { it.id == uiState.selectedSourceId }.coerceAtLeast(0)
    }
    val isMyWatchlist = uiState.selectedSourceId == WatchlistSourceItem.MyWatchlist.id
    val isSingleType = !isMyWatchlist && (
        (uiState.movies.isNotEmpty() && uiState.series.isEmpty()) ||
        (uiState.series.isNotEmpty() && uiState.movies.isEmpty())
    )
    val singleTypeItems = if (uiState.movies.isNotEmpty()) uiState.movies else uiState.series
    val singleTypeTitle = if (uiState.movies.isNotEmpty()) tr("Movies") else tr("Series")

    val multiSections = remember(uiState.movies, uiState.series) {
        listOfNotNull(
            if (uiState.movies.isNotEmpty()) ("movies" to uiState.movies) else null,
            if (uiState.series.isNotEmpty()) ("series" to uiState.series) else null
        )
    }

    fun openDetails(item: MediaItem) {
        viewModel.saveFocusState(focusedSectionIndex, focusedItemIndex)
        onNavigateToDetails(item.mediaType, item.id)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        var initialResumeHandled = false
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (initialResumeHandled) viewModel.refreshAfterResume() else initialResumeHandled = true
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(Unit) { rootFocusRequester.requestFocus() }

    LaunchedEffect(selectedSourceIndex) {
        listSelectorFocusIndex = selectedSourceIndex
        if (selectedSourceIndex >= 0) {
            listSelectorRowState.animateScrollToItem(selectedSourceIndex)
        }
    }

    LaunchedEffect(listSelectorFocusIndex) {
        if (listSelectorFocusIndex in sources.indices) {
            listSelectorRowState.animateScrollToItem(listSelectorFocusIndex)
        }
    }

    LaunchedEffect(uiState.selectedSourceId) {
        focusedSectionIndex = 0
        focusedItemIndex = 0
        sectionItemIndices.clear()
        gridState.scrollToItem(0)
        watchlistColumnState.scrollToItem(0)
    }

    LaunchedEffect(uiState.selectedSourceId, uiState.isEmpty) {
        if (focusZone == WatchlistFocusZone.CONTENT || focusZone == WatchlistFocusZone.EMPTY_STATE) {
            focusZone = if (uiState.isEmpty) WatchlistFocusZone.LIST_SELECTOR else WatchlistFocusZone.CONTENT
        }
    }

    LaunchedEffect(uiState.movies, uiState.series, isSingleType) {
        if (uiState.isEmpty) {
            focusedSectionIndex = 0
            focusedItemIndex = 0
            if (focusZone == WatchlistFocusZone.CONTENT) {
                focusZone = WatchlistFocusZone.EMPTY_STATE
            }
        } else if (isSingleType) {
            focusedSectionIndex = 0
            focusedItemIndex = focusedItemIndex.coerceIn(0, (singleTypeItems.size - 1).coerceAtLeast(0))
        } else {
            focusedSectionIndex = focusedSectionIndex.coerceIn(0, (multiSections.size - 1).coerceAtLeast(0))
            val currentSectionItems = multiSections.getOrNull(focusedSectionIndex)?.second.orEmpty()
            focusedItemIndex = focusedItemIndex.coerceIn(0, (currentSectionItems.size - 1).coerceAtLeast(0))
        }
    }

    LaunchedEffect(focusedItemIndex, isSingleType, singleTypeItems.size, focusZone) {
        if (isSingleType && focusZone == WatchlistFocusZone.CONTENT && singleTypeItems.isNotEmpty()) {
            val safe = focusedItemIndex.coerceIn(0, singleTypeItems.lastIndex)
            val rowFirstIndex = (safe / gridColumns) * gridColumns
            gridState.animateSmoothGridScroll(rowFirstIndex)
        }
    }

    LaunchedEffect(focusedSectionIndex, multiSections.size, focusZone) {
        if (!isSingleType && focusZone == WatchlistFocusZone.CONTENT && multiSections.isNotEmpty()) {
            val target = focusedSectionIndex.coerceIn(0, multiSections.lastIndex)
            watchlistColumnState.animateSmoothListScroll(target)
        }
    }

    BackHandler(enabled = true) {
        when (focusZone) {
            WatchlistFocusZone.TOP_BAR -> onBack()
            WatchlistFocusZone.LIST_SELECTOR -> focusZone = WatchlistFocusZone.TOP_BAR
            WatchlistFocusZone.CONTENT, WatchlistFocusZone.EMPTY_STATE -> focusZone = WatchlistFocusZone.TOP_BAR
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            .focusRequester(rootFocusRequester)
            .focusable()
            .onKeyEvent { event ->
                val effectiveKey = when (event.key) {
                    Key.DirectionLeft -> if (isRtl) Key.DirectionRight else Key.DirectionLeft
                    Key.DirectionRight -> if (isRtl) Key.DirectionLeft else Key.DirectionRight
                    else -> event.key
                }

                if (event.type == KeyEventType.KeyDown) {
                    when (effectiveKey) {
                        Key.Back, Key.Escape -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> onBack()
                                WatchlistFocusZone.LIST_SELECTOR -> focusZone = WatchlistFocusZone.TOP_BAR
                                WatchlistFocusZone.CONTENT, WatchlistFocusZone.EMPTY_STATE -> focusZone = WatchlistFocusZone.TOP_BAR
                            }
                            true
                        }
                        Key.DirectionLeft -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> {
                                    sidebarFocusIndex = (sidebarFocusIndex - 1).coerceAtLeast(0)
                                }
                                WatchlistFocusZone.LIST_SELECTOR -> {
                                    listSelectorFocusIndex = (listSelectorFocusIndex - 1).coerceAtLeast(0)
                                }
                                WatchlistFocusZone.CONTENT -> {
                                    if (isSingleType) {
                                        if (focusedItemIndex % gridColumns > 0) {
                                            focusedItemIndex--
                                        }
                                    } else {
                                        if (focusedItemIndex > 0) {
                                            focusedItemIndex--
                                            val currentKey = multiSections.getOrNull(focusedSectionIndex)?.first.orEmpty()
                                            sectionItemIndices[currentKey] = focusedItemIndex
                                        }
                                    }
                                }
                                WatchlistFocusZone.EMPTY_STATE -> Unit
                            }
                            true
                        }
                        Key.DirectionRight -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> {
                                    sidebarFocusIndex = (sidebarFocusIndex + 1).coerceAtMost(maxSidebarIndex)
                                }
                                WatchlistFocusZone.LIST_SELECTOR -> {
                                    listSelectorFocusIndex = (listSelectorFocusIndex + 1).coerceAtMost(sources.lastIndex)
                                }
                                WatchlistFocusZone.CONTENT -> {
                                    if (isSingleType) {
                                        if (focusedItemIndex % gridColumns < gridColumns - 1 && focusedItemIndex < singleTypeItems.lastIndex) {
                                            focusedItemIndex++
                                        }
                                    } else {
                                        val max = multiSections.getOrNull(focusedSectionIndex)?.second?.lastIndex ?: -1
                                        if (focusedItemIndex < max) {
                                            focusedItemIndex++
                                            val currentKey = multiSections.getOrNull(focusedSectionIndex)?.first.orEmpty()
                                            sectionItemIndices[currentKey] = focusedItemIndex
                                        }
                                    }
                                }
                                WatchlistFocusZone.EMPTY_STATE -> Unit
                            }
                            true
                        }
                        Key.DirectionUp -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> Unit
                                WatchlistFocusZone.LIST_SELECTOR -> focusZone = WatchlistFocusZone.TOP_BAR
                                WatchlistFocusZone.CONTENT -> {
                                    if (isSingleType) {
                                        if (focusedItemIndex >= gridColumns) {
                                            focusedItemIndex -= gridColumns
                                        } else {
                                            focusZone = WatchlistFocusZone.LIST_SELECTOR
                                        }
                                    } else {
                                        if (focusedSectionIndex > 0) {
                                            val currentKey = multiSections.getOrNull(focusedSectionIndex)?.first.orEmpty()
                                            sectionItemIndices[currentKey] = focusedItemIndex
                                            focusedSectionIndex--
                                            val targetKey = multiSections.getOrNull(focusedSectionIndex)?.first.orEmpty()
                                            val maxIdx = (multiSections.getOrNull(focusedSectionIndex)?.second?.size ?: 1) - 1
                                            val targetSaved = sectionItemIndices[targetKey] ?: 0
                                            focusedItemIndex = if (maxIdx > 0) targetSaved.coerceIn(0, maxIdx) else 0
                                        } else {
                                            val currentKey = multiSections.getOrNull(focusedSectionIndex)?.first.orEmpty()
                                            sectionItemIndices[currentKey] = focusedItemIndex
                                            focusZone = WatchlistFocusZone.LIST_SELECTOR
                                        }
                                    }
                                }
                                WatchlistFocusZone.EMPTY_STATE -> focusZone = WatchlistFocusZone.LIST_SELECTOR
                            }
                            true
                        }
                        Key.DirectionDown -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> focusZone = WatchlistFocusZone.LIST_SELECTOR
                                WatchlistFocusZone.LIST_SELECTOR -> {
                                    if (!uiState.isEmpty) {
                                        focusZone = WatchlistFocusZone.CONTENT
                                    }
                                }
                                WatchlistFocusZone.CONTENT -> {
                                    if (isSingleType) {
                                        val next = focusedItemIndex + gridColumns
                                        if (next <= singleTypeItems.lastIndex) {
                                            focusedItemIndex = next
                                        } else if (focusedItemIndex / gridColumns < singleTypeItems.lastIndex / gridColumns) {
                                            focusedItemIndex = singleTypeItems.lastIndex
                                        }
                                    } else {
                                        if (focusedSectionIndex < multiSections.lastIndex) {
                                            val currentKey = multiSections.getOrNull(focusedSectionIndex)?.first.orEmpty()
                                            sectionItemIndices[currentKey] = focusedItemIndex
                                            focusedSectionIndex++
                                            val targetKey = multiSections.getOrNull(focusedSectionIndex)?.first.orEmpty()
                                            val maxIdx = (multiSections.getOrNull(focusedSectionIndex)?.second?.size ?: 1) - 1
                                            val targetSaved = sectionItemIndices[targetKey] ?: 0
                                            focusedItemIndex = if (maxIdx > 0) targetSaved.coerceIn(0, maxIdx) else 0
                                        }
                                    }
                                }
                                WatchlistFocusZone.EMPTY_STATE -> Unit
                            }
                            true
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> {
                                    if (hasProfile && sidebarFocusIndex == 0) {
                                        onSwitchProfile()
                                    } else {
                                        when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) {
                                            SidebarItem.SEARCH -> onNavigateToSearch()
                                            SidebarItem.HOME -> onNavigateToHome()
                                            SidebarItem.WATCHLIST -> Unit
                                            SidebarItem.TV -> onNavigateToTv()
                                            SidebarItem.SETTINGS -> onNavigateToSettings(null)
                                            null -> Unit
                                        }
                                    }
                                }
                                WatchlistFocusZone.LIST_SELECTOR -> {
                                    sources.getOrNull(listSelectorFocusIndex)?.let { selected ->
                                        viewModel.selectSource(selected.id)
                                    }
                                }
                                WatchlistFocusZone.CONTENT -> {
                                    enterKeyDownTimeMs = SystemClock.elapsedRealtime()
                                }
                                WatchlistFocusZone.EMPTY_STATE -> {
                                    onNavigateToSettings("catalogs")
                                }
                            }
                            true
                        }
                        else -> false
                    }
                } else if (event.type == KeyEventType.KeyUp && effectiveKey in listOf(Key.Enter, Key.DirectionCenter)) {
                    if (focusZone == WatchlistFocusZone.CONTENT && enterKeyDownTimeMs >= 0L) {
                        val holdMs = SystemClock.elapsedRealtime() - enterKeyDownTimeMs
                        val item = if (isSingleType) {
                            singleTypeItems.getOrNull(focusedItemIndex)
                        } else {
                            multiSections.getOrNull(focusedSectionIndex)?.second?.getOrNull(focusedItemIndex)
                        }
                        if (item != null) {
                            if (uiState.selectedSourceId == WatchlistSourceItem.MyWatchlist.id && holdMs >= longPressThresholdMs) {
                                viewModel.removeFromWatchlist(item)
                            } else {
                                openDetails(item)
                            }
                        }
                        enterKeyDownTimeMs = -1L
                    }
                    true
                } else {
                    false
                }
            }
    ) {
        if (!isMobile) {
            AppTopBar(
                selectedItem = SidebarItem.WATCHLIST,
                isFocused = focusZone == WatchlistFocusZone.TOP_BAR,
                focusedIndex = sidebarFocusIndex,
                profile = currentProfile
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = if (isMobile) 0.dp else AppTopBarHeight + 20.dp)
        ) {
            if (isMobile) {
                Text(
                    text = stringResource(R.string.library_default),
                    style = ArflixTypography.heroTitle.copy(fontSize = 28.sp),
                    color = TextPrimary,
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            // 1. List Selector Layer
            ListSelectorRow(
                sources = sources,
                selectedSourceId = uiState.selectedSourceId,
                focusedIndex = if (focusZone == WatchlistFocusZone.LIST_SELECTOR) listSelectorFocusIndex else -1,
                listState = listSelectorRowState,
                isMobile = isMobile,
                contentStartPadding = contentStartPadding,
                onSelectSource = { source ->
                    viewModel.selectSource(source.id)
                }
            )

            // Content Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when {
                    uiState.isLoading -> {
                        CenteredLoading()
                    }
                    uiState.error != null && uiState.isEmpty -> {
                        EmptyStateView(
                            title = tr("Unable to load list"),
                            subtitle = uiState.error.orEmpty()
                        )
                    }
                    uiState.isEmpty -> {
                        EmptyStateView(
                            title = tr("This list is empty"),
                            subtitle = tr("Add movies and series for later")
                        )
                    }
                    isMyWatchlist -> {
                        // Dedicated My Watchlist module: Always maintains Movies / Series horizontal rows
                        MyWatchlistView(
                            sections = multiSections,
                            logoUrls = logoUrls,
                            usePosterCards = usePosterCards,
                            cardWidth = cardWidth,
                            contentStartPadding = contentStartPadding,
                            isMobile = isMobile,
                            focusedSectionIndex = if (focusZone == WatchlistFocusZone.CONTENT) focusedSectionIndex else -1,
                            focusedItemIndex = if (focusZone == WatchlistFocusZone.CONTENT) focusedItemIndex else -1,
                            columnState = watchlistColumnState,
                            onItemFocused = { secIdx, itemIdx ->
                                focusedSectionIndex = secIdx
                                focusedItemIndex = itemIdx
                            },
                            onItemClick = { item -> openDetails(item) },
                            onItemLongPress = { item -> viewModel.removeFromWatchlist(item) }
                        )
                    }
                    isSingleType -> {
                        // Library Single-type Catalog/Server: Multi-row Grid
                        SingleTypeGridView(
                            items = singleTypeItems,
                            logoUrls = logoUrls,
                            usePosterCards = usePosterCards,
                            columns = gridColumns,
                            cardWidth = cardWidth,
                            contentStartPadding = contentStartPadding,
                            isMobile = isMobile,
                            focusedIndex = if (focusZone == WatchlistFocusZone.CONTENT) focusedItemIndex else -1,
                            gridState = gridState,
                            onItemFocused = { index -> focusedItemIndex = index },
                            onItemClick = { item -> openDetails(item) },
                            onLoadMore = viewModel::loadMoreActiveSource,
                            onItemLongPress = { }
                        )
                    }
                    else -> {
                        // Library Multi-section scrolling rows (Movies + Series)
                        MultiSectionRowsView(
                            sections = multiSections,
                            logoUrls = logoUrls,
                            usePosterCards = usePosterCards,
                            cardWidth = cardWidth,
                            contentStartPadding = contentStartPadding,
                            isMobile = isMobile,
                            focusedSectionIndex = if (focusZone == WatchlistFocusZone.CONTENT) focusedSectionIndex else -1,
                            focusedItemIndex = if (focusZone == WatchlistFocusZone.CONTENT) focusedItemIndex else -1,
                            columnState = watchlistColumnState,
                            onItemFocused = { secIdx, itemIdx ->
                                focusedSectionIndex = secIdx
                                focusedItemIndex = itemIdx
                            },
                            onItemClick = { item -> openDetails(item) },
                            onLoadMore = viewModel::loadMoreActiveSource,
                            onItemLongPress = { }
                        )
                    }
                }
                if (uiState.isLoadingMore) {
                    LoadingIndicator(
                        color = Pink,
                        size = 26.dp,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 12.dp)
                    )
                }
            }
        }

        uiState.toastMessage?.let { message ->
            Toast(
                message = message,
                type = when (uiState.toastType) {
                    ToastType.SUCCESS -> ComponentToastType.SUCCESS
                    ToastType.ERROR -> ComponentToastType.ERROR
                    ToastType.INFO -> ComponentToastType.INFO
                },
                isVisible = true,
                onDismiss = viewModel::dismissToast
            )
        }
    }
}

/**
 * 1. Horizontal D-pad-scrollable row of list pills right under the top bar,
 * with a 'Manage lists' ghost button at the far right.
 */
@Composable
private fun ListSelectorRow(
    sources: List<WatchlistSourceItem>,
    selectedSourceId: String,
    focusedIndex: Int,
    listState: androidx.compose.foundation.lazy.LazyListState,
    isMobile: Boolean,
    contentStartPadding: Dp,
    onSelectSource: (WatchlistSourceItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        state = listState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = contentStartPadding, end = contentStartPadding, top = 0.dp, bottom = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(sources, key = { _, source -> "source-${source.id}" }) { index, source ->
            val isSelected = source.id == selectedSourceId
            val isFocused = index == focusedIndex
            val displayLabel = if (source is WatchlistSourceItem.MyWatchlist) {
                tr("My watchlist")
            } else {
                source.displayLabel
            }
            ListPill(
                label = displayLabel,
                selected = isSelected,
                focused = isFocused,
                modifier = Modifier.clickable(enabled = isMobile) { onSelectSource(source) }
            )
        }
        // TODO: "Manage lists" feature has not been implemented yet.
        // Uncomment once list creation, reordering, and deletion management is ready.
        /*
        item(key = "manage-lists-ghost-button") {
            val isFocused = focusedIndex == sources.size
            ManageListsGhostButton(
                focused = isFocused,
                modifier = Modifier.clickable(enabled = isMobile) { onManageLists?.invoke() }
            )
        }
        */
    }
}

@Composable
private fun ListPill(
    label: String,
    selected: Boolean,
    focused: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(999.dp)
    val scale by animateFloatAsState(
        targetValue = if (focused) 1.04f else 1f,
        animationSpec = tween(100),
        label = "pill_scale"
    )

    val background = when {
        focused -> Color.White.copy(alpha = 0.28f)
        selected -> Color.White.copy(alpha = 0.16f)
        else -> Color.White.copy(alpha = 0.06f)
    }
    val borderColor = when {
        focused -> Color.White
        selected -> Color.White.copy(alpha = 0.5f)
        else -> Color.White.copy(alpha = 0.12f)
    }
    val borderWidth = if (focused) 1.5.dp else if (selected) 1.dp else 0.5.dp

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(background, shape)
            .border(borderWidth, borderColor, shape)
            .padding(horizontal = 14.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = when {
                focused -> Color.White
                selected -> Color.White
                else -> Color.White.copy(alpha = 0.7f)
            },
            fontSize = 13.sp,
            fontWeight = if (selected || focused) FontWeight.SemiBold else FontWeight.Medium,
            maxLines = 1
        )
    }
}

/*
// TODO: "Manage lists" feature has not been implemented yet.
// Uncomment once list management UI / workflows are implemented.
@Composable
private fun ManageListsGhostButton(
    focused: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(999.dp)
    val scale by animateFloatAsState(
        targetValue = if (focused) 1.04f else 1f,
        animationSpec = tween(100),
        label = "ghost_button_scale"
    )

    Row(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                color = if (focused) Color.White.copy(alpha = 0.22f) else Color.Transparent,
                shape = shape
            )
            .border(
                width = if (focused) 1.5.dp else 0.8.dp,
                color = if (focused) Color.White else Color.White.copy(alpha = 0.25f),
                shape = shape
            )
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Tune,
            contentDescription = tr("Manage lists"),
            tint = if (focused) Color.White else Color.White.copy(alpha = 0.65f),
            modifier = Modifier.size(15.dp)
        )
        Text(
            text = tr("Manage lists"),
            color = if (focused) Color.White else Color.White.copy(alpha = 0.68f),
            fontSize = 13.sp,
            fontWeight = if (focused) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1
        )
    }
}
*/

/**
 * 2. Section Header matching HomeScreen style
 */
@Composable
private fun SectionHeader(
    title: String,
    count: Int,
    startPadding: Dp = 36.dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(start = startPadding, bottom = 12.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = title,
            style = ArflixTypography.sectionTitle.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.8f),
                    offset = androidx.compose.ui.geometry.Offset(1f, 1f),
                    blurRadius = 4f
                )
            ),
            color = Color.White
        )
        Text(
            text = "$count",
            style = ArflixTypography.sectionTitle.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Color.White.copy(alpha = 0.42f),
            modifier = Modifier.padding(bottom = 1.5.dp)
        )
    }
}

/**
 * 2. Single-Type Grid View (multi-row grid when only Movies or only Series exist)
 */
@Composable
private fun SingleTypeGridView(
    items: List<MediaItem>,
    logoUrls: Map<String, String>,
    usePosterCards: Boolean,
    columns: Int,
    cardWidth: Dp,
    contentStartPadding: Dp,
    isMobile: Boolean,
    focusedIndex: Int,
    gridState: androidx.compose.foundation.lazy.grid.LazyGridState,
    onItemFocused: (Int) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onLoadMore: () -> Unit,
    onItemLongPress: (MediaItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = gridState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = contentStartPadding),
        contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = isMobile
    ) {
            gridItemsIndexed(
                items = items,
                key = { index, item -> watchlistItemKey(item, index) },
                contentType = { _, item -> item.mediaType.name }
            ) { index, item ->
                if (index >= (items.size - columns * 2).coerceAtLeast(0)) {
                    LaunchedEffect(items.size, index) { onLoadMore() }
                }
                MediaCard(
                    item = item,
                    width = cardWidth,
                    isLandscape = !usePosterCards,
                    logoImageUrl = logoUrls[watchlistLogoKey(item)],
                    showLogoImage = true,
                    showTitle = !usePosterCards || item.title.isNotBlank(),
                    showSubtitle = false,
                    titleMaxLines = 1,
                    showProgress = false,
                    isFocusedOverride = index == focusedIndex,
                    focusedScale = 1.045f,
                    enableSystemFocus = false,
                    onFocused = { onItemFocused(index) },
                    onClick = { onItemClick(item) },
                    onLongClick = { onItemLongPress(item) }
                )
            }
        }
}

/**
 * Dedicated My Watchlist module within the Library.
 * Displays Movies and Series in clean, dedicated horizontal scrolling sections.
 */
@Composable
private fun MyWatchlistView(
    sections: List<Pair<String, List<MediaItem>>>,
    logoUrls: Map<String, String>,
    usePosterCards: Boolean,
    cardWidth: Dp,
    contentStartPadding: Dp,
    isMobile: Boolean,
    focusedSectionIndex: Int,
    focusedItemIndex: Int,
    columnState: androidx.compose.foundation.lazy.LazyListState,
    onItemFocused: (Int, Int) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onItemLongPress: (MediaItem) -> Unit
) {
    MultiSectionRowsView(
        sections = sections,
        logoUrls = logoUrls,
        usePosterCards = usePosterCards,
        cardWidth = cardWidth,
        contentStartPadding = contentStartPadding,
        isMobile = isMobile,
        focusedSectionIndex = focusedSectionIndex,
        focusedItemIndex = focusedItemIndex,
        columnState = columnState,
        onItemFocused = onItemFocused,
        onItemClick = onItemClick,
        onLoadMore = {},
        onItemLongPress = onItemLongPress
    )
}

/**
 * 2. Multi-Section Rows View (when both Movies and Series exist or for My Watchlist module)
 */
@Composable
private fun MultiSectionRowsView(
    sections: List<Pair<String, List<MediaItem>>>,
    logoUrls: Map<String, String>,
    usePosterCards: Boolean,
    cardWidth: Dp,
    contentStartPadding: Dp,
    isMobile: Boolean,
    focusedSectionIndex: Int,
    focusedItemIndex: Int,
    columnState: androidx.compose.foundation.lazy.LazyListState,
    onItemFocused: (Int, Int) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onLoadMore: () -> Unit,
    onItemLongPress: (MediaItem) -> Unit
) {
    LazyColumn(
        state = columnState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        userScrollEnabled = isMobile
    ) {
        itemsIndexed(sections, key = { _, item -> item.first }) { secIdx, (type, items) ->
            val sectionTitle = if (type == "movies") tr("Movies") else tr("Series")
            val isSectionFocused = focusedSectionIndex == secIdx

            Column(modifier = Modifier.fillMaxWidth()) {
                SectionHeader(title = sectionTitle, count = items.size, startPadding = contentStartPadding)

                val rowState = rememberLazyListState()
                LaunchedEffect(focusedItemIndex, isSectionFocused) {
                    if (isSectionFocused && focusedItemIndex in items.indices) {
                        rowState.animateScrollToItem(focusedItemIndex)
                    }
                }

                LazyRow(
                    state = rowState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    contentPadding = PaddingValues(
                        start = contentStartPadding,
                        end = contentStartPadding,
                        top = 8.dp,
                        bottom = 12.dp
                    )
                ) {
                    itemsIndexed(
                        items = items,
                        key = { index, item -> watchlistItemKey(item, index) },
                        contentType = { _, item -> item.mediaType.name }
                    ) { itemIdx, item ->
                        if (itemIdx >= (items.size - 4).coerceAtLeast(0)) {
                            LaunchedEffect(items.size, itemIdx) { onLoadMore() }
                        }
                        MediaCard(
                            item = item,
                            width = cardWidth,
                            isLandscape = !usePosterCards,
                            logoImageUrl = logoUrls[watchlistLogoKey(item)],
                            showLogoImage = true,
                            showTitle = !usePosterCards || item.title.isNotBlank(),
                            showSubtitle = false,
                            titleMaxLines = 1,
                            showProgress = false,
                            isFocusedOverride = isSectionFocused && itemIdx == focusedItemIndex,
                            focusedScale = 1.045f,
                            enableSystemFocus = false,
                            onFocused = { onItemFocused(secIdx, itemIdx) },
                            onClick = { onItemClick(item) },
                            onLongClick = { onItemLongPress(item) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Empty State with optional CTA button
 */
@Composable
private fun EmptyStateView(
    title: String,
    subtitle: String,
    buttonText: String? = null,
    isButtonFocused: Boolean = false,
    isMobile: Boolean = false,
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Bookmark,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.22f),
                modifier = Modifier.size(68.dp)
            )
            Text(
                text = title,
                style = ArflixTypography.sectionTitle.copy(fontSize = 19.sp, fontWeight = FontWeight.SemiBold),
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitle,
                style = ArflixTypography.body.copy(fontSize = 13.5.sp),
                color = Color.White.copy(alpha = 0.42f),
                textAlign = TextAlign.Center
            )
            // TODO: "Manage lists" feature has not been implemented yet.
            // Uncomment button once list management functionality is ready.
            /*
            if (buttonText != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .background(
                            if (isButtonFocused) Color.White else Color.White.copy(alpha = 0.12f),
                            RoundedCornerShape(999.dp)
                        )
                        .border(
                            1.dp,
                            if (isButtonFocused) Color.White else Color.White.copy(alpha = 0.22f),
                            RoundedCornerShape(999.dp)
                        )
                        .clickable(enabled = isMobile) { onButtonClick() }
                        .padding(horizontal = 20.dp, vertical = 9.dp)
                ) {
                    Text(
                        text = buttonText,
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isButtonFocused) Color.Black else Color.White
                    )
                }
            }
            */
        }
    }
}

@Composable
private fun CenteredLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LoadingIndicator(color = Pink, size = 52.dp)
    }
}

/**
 * Smooth, symmetric vertical grid scrolling matching up and down speeds.
 */
private suspend fun androidx.compose.foundation.lazy.grid.LazyGridState.animateSmoothGridScroll(
    targetIndex: Int,
    durationMillis: Int = 200
) {
    val targetItem = layoutInfo.visibleItemsInfo.find { it.index == targetIndex }
    if (targetItem != null) {
        val delta = targetItem.offset.y.toFloat()
        if (abs(delta) > 1f) {
            scroll(scrollPriority = MutatePriority.PreventUserInput) {
                var prev = 0f
                animate(
                    initialValue = 0f,
                    targetValue = delta,
                    animationSpec = tween(durationMillis = durationMillis, easing = FastOutSlowInEasing)
                ) { value, _ ->
                    val step = value - prev
                    if (abs(step) > 0.01f) {
                        scrollBy(step)
                    }
                    prev = value
                }
            }
        }
    } else {
        animateScrollToItem(index = targetIndex, scrollOffset = 0)
    }
}

/**
 * Smooth, symmetric vertical multi-section list scrolling matching up and down speeds.
 */
private suspend fun androidx.compose.foundation.lazy.LazyListState.animateSmoothListScroll(
    targetIndex: Int,
    durationMillis: Int = 220
) {
    val targetItem = layoutInfo.visibleItemsInfo.find { it.index == targetIndex }
    if (targetItem != null) {
        val delta = targetItem.offset.toFloat()
        if (abs(delta) > 1f) {
            scroll(scrollPriority = MutatePriority.PreventUserInput) {
                var prev = 0f
                animate(
                    initialValue = 0f,
                    targetValue = delta,
                    animationSpec = tween(durationMillis = durationMillis, easing = FastOutSlowInEasing)
                ) { value, _ ->
                    val step = value - prev
                    if (abs(step) > 0.01f) {
                        scrollBy(step)
                    }
                    prev = value
                }
            }
        }
    } else {
        animateScrollToItem(index = targetIndex, scrollOffset = 0)
    }
}
