package com.arflix.tv.ui.screens.watchlist

import com.arflix.tv.ui.components.LocalBottomBarInset
import android.os.SystemClock
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed as gridItemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.focused
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.selected
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
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.HomeServerCatalogCandidate
import com.arflix.tv.data.repository.HomeServerKind
import com.arflix.tv.data.repository.HomeServerLibrarySort
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarHeight
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.TextInputModal
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

private enum class WatchlistFocusZone {
    TOP_BAR,
    PROVIDERS,
    LIBRARIES,
    FILTERS,
    CONTENT
}

private data class LibraryFilter(
    val label: String,
    val isSort: Boolean = false,
    val isSearch: Boolean = false,
    val isRefresh: Boolean = false,
    val iconOnly: Boolean = false
)

private data class LibraryProviderOption(
    val id: String,
    val label: String,
    val homeServerKind: HomeServerKind? = null,
    val trackerSources: List<WatchlistSourceItem> = emptyList()
) {
    val isWatchlist: Boolean get() = id == WATCHLIST_PROVIDER_ID
    val isHomeServer: Boolean get() = homeServerKind != null
    val isTracker: Boolean get() = trackerSources.isNotEmpty()
}

private fun WatchlistSourceItem.trackerProviderLabel(): String? {
    return when (this) {
        is WatchlistSourceItem.TrackerList -> provider.displayName
        is WatchlistSourceItem.Catalog -> {
            val url = config.sourceUrl.orEmpty()
            val identity = listOf(config.id, config.title, config.addonName.orEmpty(), url).joinToString(" ")
            when {
                config.sourceType == CatalogSourceType.TRAKT || identity.contains("trakt", ignoreCase = true) -> "Trakt"
                identity.contains("simkl", ignoreCase = true) -> "Simkl"
                else -> null
            }
        }
        else -> null
    }
}

private fun WatchlistSourceItem.asSidebarLibrary(
    providerLabel: String,
    localizedTitle: String = title
): HomeServerCatalogCandidate {
    return HomeServerCatalogCandidate(
        title = localizedTitle,
        sourceRef = id,
        serverName = providerLabel,
        collectionName = localizedTitle,
        collectionType = "mixed",
        serverKind = HomeServerKind.UNKNOWN,
        connectionId = "tracker:${providerLabel.lowercase()}"
    )
}

private const val WATCHLIST_PROVIDER_ID = "provider:watchlist"

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onNavigateToDetails: (MediaType, Int) -> Unit = { _, _ -> },
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: (String) -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val libraryState by viewModel.libraryState.collectAsStateWithLifecycle()
    val logoUrls by viewModel.logoUrls.collectAsStateWithLifecycle()
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val usePosterCards = rememberCardLayoutMode() == CardLayoutMode.POSTER
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth: Dp = if (usePosterCards) {
        if (isMobile) ((screenWidth - 62.dp) / 2).coerceIn(112.dp, 150.dp) else 110.dp
    } else {
        if (isMobile) ((screenWidth - 62.dp) / 2).coerceIn(138.dp, 210.dp) else 210.dp
    }
    val libraryCardWidth = if (!isMobile && !usePosterCards) 160.dp else cardWidth
    val libraryColumns = if (isMobile) 2 else if (usePosterCards) 6 else 4
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val rootFocusRequester = remember { FocusRequester() }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 3 else 2) }
    var focusZone by remember { mutableStateOf(WatchlistFocusZone.CONTENT) }
    var providerFocusIndex by remember { mutableIntStateOf(0) }
    var libraryFocusIndex by remember { mutableIntStateOf(0) }
    var filterFocusIndex by remember { mutableIntStateOf(0) }
    var focusedSectionIndex by remember { mutableIntStateOf(0) }
    var focusedItemIndex by remember { mutableIntStateOf(0) }
    var enterKeyDownTimeMs by remember { mutableLongStateOf(-1L) }
    var showSearchModal by remember { mutableStateOf(false) }
    var showSortMenu by remember { mutableStateOf(false) }
    var sortFocusIndex by remember { mutableIntStateOf(0) }
    var selectedProviderId by remember { mutableStateOf(WATCHLIST_PROVIDER_ID) }
    var listSort by remember { mutableStateOf(HomeServerLibrarySort.RECENTLY_ADDED) }
    var trackerSearchQuery by remember { mutableStateOf("") }
    val longPressThresholdMs = 500L
    val watchlistColumnState = rememberLazyListState()
    val libraryGridState = rememberLazyGridState()

    val trackerGroups = remember(uiState.sources) {
        uiState.sources
            .mapNotNull { source -> source.trackerProviderLabel()?.let { label -> label to source } }
            .groupBy(keySelector = { it.first }, valueTransform = { it.second })
    }
    val myWatchlistLabel = stringResource(R.string.watchlist_my_watchlist)
    val unknownServerLabel = stringResource(R.string.watchlist_provider_home_server)
    val providers = remember(libraryState.providers, trackerGroups, myWatchlistLabel, unknownServerLabel) {
        buildList {
            add(LibraryProviderOption(id = WATCHLIST_PROVIDER_ID, label = myWatchlistLabel))
            libraryState.providers.forEach { kind ->
                val label = when (kind) {
                    HomeServerKind.PLEX -> "Plex"
                    HomeServerKind.JELLYFIN -> "Jellyfin"
                    HomeServerKind.EMBY -> "Emby"
                    HomeServerKind.UNKNOWN -> unknownServerLabel
                }
                add(LibraryProviderOption(id = "provider:home:${kind.name}", label = label, homeServerKind = kind))
            }
            listOf("Trakt", "Simkl").forEach { label ->
                trackerGroups[label]?.takeIf { it.isNotEmpty() }?.let { sources ->
                    add(
                        LibraryProviderOption(
                            id = "provider:tracker:${label.lowercase()}",
                            label = label,
                            trackerSources = sources
                        )
                    )
                }
            }
        }
    }
    val activeProvider = providers.firstOrNull { it.id == selectedProviderId } ?: providers.first()
    val selectedProviderIndex = providers.indexOfFirst { it.id == activeProvider.id }.coerceAtLeast(0)
    val isHomeServerMode = activeProvider.isHomeServer
    val isTrackerMode = activeProvider.isTracker
    val localizedContext = LocalContext.current
    val providerLibraries = remember(
        libraryState.libraries,
        activeProvider.id,
        activeProvider.trackerSources,
        localizedContext
    ) {
        when {
            activeProvider.isHomeServer -> libraryState.libraries.filter {
                it.serverKind == activeProvider.homeServerKind
            }
            activeProvider.isTracker -> activeProvider.trackerSources.map { source ->
                val localizedTitle = (source as? WatchlistSourceItem.TrackerList)
                    ?.titleRes
                    ?.let(localizedContext::getString)
                    ?: source.title
                source.asSidebarLibrary(activeProvider.label, localizedTitle)
            }
            else -> emptyList()
        }
    }
    val trackerItems = remember(uiState.allItems, trackerSearchQuery, activeProvider.id, listSort) {
        val query = trackerSearchQuery.trim()
        val filtered = if (!isTrackerMode || query.isBlank()) uiState.allItems else uiState.allItems.filter { item ->
            item.title.contains(query, ignoreCase = true) ||
                item.overview.contains(query, ignoreCase = true)
        }
        sortLibraryItems(filtered, listSort)
    }
    val activeLibraryState = when {
        isHomeServerMode -> libraryState
        isTrackerMode -> HomeLibraryUiState(
            selectedSourceRef = uiState.selectedSourceId,
            items = trackerItems,
            isLoading = uiState.isLoading,
            isLoadingMore = uiState.isLoadingMore,
            hasMore = uiState.hasMore,
            sort = listSort,
            searchQuery = trackerSearchQuery,
            error = uiState.error
        )
        else -> HomeLibraryUiState(sort = listSort)
    }
    val selectedLibraryIndex = providerLibraries.indexOfFirst { it.sourceRef == activeLibraryState.selectedSourceRef }
        .coerceAtLeast(0)
    val filters = if (isHomeServerMode || isTrackerMode) {
        listOf(
            LibraryFilter(tr("Sort"), isSort = true),
            LibraryFilter(tr("Search"), isSearch = true, iconOnly = true),
            LibraryFilter(tr("Refresh"), isRefresh = true, iconOnly = true)
        )
    } else {
        listOf(
            LibraryFilter(tr("Sort"), isSort = true)
        )
    }
    val sortOptions = listOf(
        tr("Recently added") to HomeServerLibrarySort.RECENTLY_ADDED,
        stringResource(R.string.library_sort_release_newest) to HomeServerLibrarySort.RELEASE_DATE_NEWEST,
        stringResource(R.string.library_sort_release_oldest) to HomeServerLibrarySort.RELEASE_DATE_OLDEST,
        tr("Highest rated") to HomeServerLibrarySort.RATING,
        tr("Title A-Z") to HomeServerLibrarySort.TITLE
    )
    val watchlistSections = remember(uiState.movies, uiState.series, listSort) {
        listOf(
            "movies" to sortLibraryItems(uiState.movies, listSort),
            "series" to sortLibraryItems(uiState.series, listSort)
        ).filter { it.second.isNotEmpty() }
    }
    val watchlistTotal = uiState.movies.size + uiState.series.size
    val isLibraryMode = isHomeServerMode || isTrackerMode
    val visibleLibraryItems = activeLibraryState.items

    fun selectSort(sort: HomeServerLibrarySort) {
        if (isHomeServerMode) viewModel.setLibrarySort(sort) else listSort = sort
        focusedSectionIndex = 0
        focusedItemIndex = 0
    }

    fun moveToContent() {
        focusZone = WatchlistFocusZone.CONTENT
        val count = if (isLibraryMode) visibleLibraryItems.size else watchlistSections.firstOrNull()?.second?.size ?: 0
        focusedItemIndex = focusedItemIndex.coerceIn(
            0,
            (count - 1).coerceAtLeast(0)
        )
    }

    fun activateProvider(index: Int) {
        providerFocusIndex = index.coerceIn(0, (providers.size - 1).coerceAtLeast(0))
        val provider = providers[providerFocusIndex]
        selectedProviderId = provider.id
        trackerSearchQuery = ""
        when {
            provider.isWatchlist -> {
                viewModel.selectLibraryProvider(null)
                viewModel.selectSource(WatchlistSourceItem.MyWatchlist.id)
            }
            provider.isHomeServer -> viewModel.selectLibraryProvider(provider.homeServerKind)
            provider.isTracker -> {
                viewModel.selectLibraryProvider(null)
                provider.trackerSources.firstOrNull()?.let { viewModel.selectSource(it.id) }
            }
        }
        libraryFocusIndex = 0
        focusedSectionIndex = 0
        focusedItemIndex = 0
    }

    fun activateFilter(index: Int) {
        val filter = filters.getOrNull(index) ?: return
        when {
            filter.isSearch -> showSearchModal = true
            filter.isRefresh -> if (isHomeServerMode) viewModel.refreshLibrary() else viewModel.refresh()
            filter.isSort -> {
                sortFocusIndex = sortOptions.indexOfFirst { it.second == activeLibraryState.sort }.coerceAtLeast(0)
                showSortMenu = true
            }
        }
        focusedItemIndex = 0
    }

    BackHandler(enabled = showSortMenu) {
        showSortMenu = false
        focusZone = WatchlistFocusZone.FILTERS
    }
    BackHandler(enabled = !showSearchModal && !showSortMenu) {
        if (focusZone == WatchlistFocusZone.TOP_BAR) onBack() else focusZone = WatchlistFocusZone.TOP_BAR
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
    LaunchedEffect(providers.map { it.id }) {
        if (providers.none { it.id == selectedProviderId }) {
            selectedProviderId = WATCHLIST_PROVIDER_ID
            viewModel.selectLibraryProvider(null)
            viewModel.selectSource(WatchlistSourceItem.MyWatchlist.id)
        }
    }
    LaunchedEffect(selectedProviderIndex) { providerFocusIndex = selectedProviderIndex }
    LaunchedEffect(selectedLibraryIndex) { libraryFocusIndex = selectedLibraryIndex }
    LaunchedEffect(
        activeLibraryState.selectedSourceRef,
        activeLibraryState.sort,
        activeLibraryState.searchQuery
    ) {
        focusedItemIndex = 0
        if (isLibraryMode) libraryGridState.scrollToItem(0)
        else watchlistColumnState.scrollToItem(0)
    }
    LaunchedEffect(watchlistSections.size, uiState.movies.size, uiState.series.size) {
        if (watchlistSections.isNotEmpty() && focusedSectionIndex >= watchlistSections.size) {
            focusedSectionIndex = 0
            focusedItemIndex = 0
        }
    }
    LaunchedEffect(focusedSectionIndex, watchlistSections.size, focusZone) {
        if (!isLibraryMode && focusZone == WatchlistFocusZone.CONTENT && watchlistSections.isNotEmpty()) {
            watchlistColumnState.animateScrollToItem(focusedSectionIndex)
        }
    }
    LaunchedEffect(focusedItemIndex, isLibraryMode, focusZone, visibleLibraryItems.size, isMobile) {
        if (!isMobile && isLibraryMode && focusZone == WatchlistFocusZone.CONTENT && visibleLibraryItems.isNotEmpty()) {
            val safe = focusedItemIndex.coerceIn(0, visibleLibraryItems.lastIndex)
            val layout = snapshotFlow { libraryGridState.layoutInfo }.first { it.totalItemsCount > safe }
            val target = layout.visibleItemsInfo.firstOrNull { it.index == safe }
            if (target == null) {
                libraryGridState.animateScrollToItem(safe)
            } else {
                val delta = libraryRevealScrollDelta(target.offset.y, target.size.height,
                    layout.viewportStartOffset + layout.beforeContentPadding,
                    layout.viewportEndOffset - layout.afterContentPadding)
                if (delta != 0) libraryGridState.animateScrollBy(delta.toFloat(), tween(120))
            }
            if (safe >= visibleLibraryItems.size - libraryColumns * 2) {
                if (isHomeServerMode) viewModel.loadMoreLibrary() else viewModel.loadMoreActiveSource()
            }
        }
    }
    LaunchedEffect(
        libraryGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index,
        isMobile,
        activeLibraryState.hasMore
    ) {
        val last = libraryGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
        if (isMobile && last >= visibleLibraryItems.size - libraryColumns * 2) {
            if (isHomeServerMode) viewModel.loadMoreLibrary() else viewModel.loadMoreActiveSource()
        }
    }

    Box(
        modifier = Modifier
            .testTag("library-screen")
            .fillMaxSize()
            .background(appBackgroundDark())
            .focusRequester(rootFocusRequester)
            .focusable()
            .onKeyEvent { event ->
                if (showSearchModal) return@onKeyEvent false
                val effectiveKey = when (event.key) {
                    Key.DirectionLeft -> if (isRtl) Key.DirectionRight else Key.DirectionLeft
                    Key.DirectionRight -> if (isRtl) Key.DirectionLeft else Key.DirectionRight
                    else -> event.key
                }
                if (showSortMenu) {
                    if (event.type != KeyEventType.KeyDown) return@onKeyEvent true
                    return@onKeyEvent when (effectiveKey) {
                        Key.Back, Key.Escape -> {
                            showSortMenu = false
                            focusZone = WatchlistFocusZone.FILTERS
                            true
                        }
                        Key.DirectionUp, Key.DirectionLeft -> {
                            sortFocusIndex = (sortFocusIndex - 1).coerceAtLeast(0)
                            true
                        }
                        Key.DirectionDown, Key.DirectionRight -> {
                            sortFocusIndex = (sortFocusIndex + 1).coerceAtMost(sortOptions.lastIndex)
                            true
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            sortOptions.getOrNull(sortFocusIndex)?.second?.let(::selectSort)
                            showSortMenu = false
                            focusZone = WatchlistFocusZone.FILTERS
                            true
                        }
                        else -> true
                    }
                }
                if (event.type == KeyEventType.KeyDown) {
                    when (effectiveKey) {
                        Key.Back, Key.Escape -> {
                            if (focusZone == WatchlistFocusZone.TOP_BAR) onBack() else focusZone = WatchlistFocusZone.TOP_BAR
                            true
                        }
                        Key.DirectionLeft -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> sidebarFocusIndex = (sidebarFocusIndex - 1).coerceAtLeast(0)
                                WatchlistFocusZone.PROVIDERS -> providerFocusIndex = (providerFocusIndex - 1).coerceAtLeast(0)
                                WatchlistFocusZone.LIBRARIES -> Unit
                                WatchlistFocusZone.FILTERS -> {
                                    if (filterFocusIndex == 0) {
                                        providerFocusIndex = providers.lastIndex.coerceAtLeast(0)
                                        focusZone = WatchlistFocusZone.PROVIDERS
                                    } else {
                                        filterFocusIndex = (filterFocusIndex - 1).coerceAtLeast(0)
                                    }
                                }
                                WatchlistFocusZone.CONTENT -> {
                                    if (isLibraryMode) {
                                        if (focusedItemIndex % libraryColumns > 0) focusedItemIndex--
                                        else if (providerLibraries.isNotEmpty()) {
                                            libraryFocusIndex = selectedLibraryIndex.coerceAtLeast(0)
                                            focusZone = WatchlistFocusZone.LIBRARIES
                                        }
                                    } else if (focusedItemIndex > 0) focusedItemIndex--
                                }
                            }
                            true
                        }
                        Key.DirectionRight -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> sidebarFocusIndex = (sidebarFocusIndex + 1).coerceAtMost(maxSidebarIndex)
                                WatchlistFocusZone.PROVIDERS -> {
                                    if (providerFocusIndex >= providers.lastIndex) {
                                        filterFocusIndex = 0
                                        focusZone = WatchlistFocusZone.FILTERS
                                    } else {
                                        providerFocusIndex = (providerFocusIndex + 1).coerceAtMost(providers.lastIndex)
                                    }
                                }
                                WatchlistFocusZone.LIBRARIES -> {
                                    if (visibleLibraryItems.isNotEmpty()) moveToContent()
                                    else focusZone = WatchlistFocusZone.FILTERS
                                }
                                WatchlistFocusZone.FILTERS -> filterFocusIndex = (filterFocusIndex + 1).coerceAtMost(filters.lastIndex)
                                WatchlistFocusZone.CONTENT -> {
                                    val max = if (isLibraryMode) visibleLibraryItems.lastIndex else watchlistSections.getOrNull(focusedSectionIndex)?.second?.lastIndex ?: -1
                                    if (focusedItemIndex < max) focusedItemIndex++
                                }
                            }
                            true
                        }
                        Key.DirectionUp -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> Unit
                                WatchlistFocusZone.PROVIDERS -> focusZone = WatchlistFocusZone.TOP_BAR
                                WatchlistFocusZone.LIBRARIES -> {
                                    if (libraryFocusIndex > 0) libraryFocusIndex-- else focusZone = WatchlistFocusZone.PROVIDERS
                                }
                                WatchlistFocusZone.FILTERS -> focusZone = WatchlistFocusZone.TOP_BAR
                                WatchlistFocusZone.CONTENT -> {
                                    if (isLibraryMode) {
                                        if (focusedItemIndex >= libraryColumns) focusedItemIndex -= libraryColumns else focusZone = WatchlistFocusZone.FILTERS
                                    } else if (focusedSectionIndex > 0) {
                                        focusedSectionIndex--
                                        focusedItemIndex = 0
                                    } else {
                                        focusZone = WatchlistFocusZone.PROVIDERS
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionDown -> {
                            when (focusZone) {
                                WatchlistFocusZone.TOP_BAR -> focusZone = WatchlistFocusZone.PROVIDERS
                                WatchlistFocusZone.PROVIDERS -> {
                                    focusZone = if (isLibraryMode && providerLibraries.isNotEmpty()) WatchlistFocusZone.LIBRARIES else WatchlistFocusZone.CONTENT
                                }
                                WatchlistFocusZone.LIBRARIES -> {
                                    if (libraryFocusIndex < providerLibraries.lastIndex) libraryFocusIndex++
                                }
                                WatchlistFocusZone.FILTERS -> if (if (isLibraryMode) visibleLibraryItems.isNotEmpty() else watchlistSections.isNotEmpty()) moveToContent()
                                WatchlistFocusZone.CONTENT -> {
                                    if (isLibraryMode) {
                                        val next = focusedItemIndex + libraryColumns
                                        if (visibleLibraryItems.isNotEmpty()) {
                                            focusedItemIndex = next.coerceAtMost(visibleLibraryItems.lastIndex)
                                        }
                                    } else if (focusedSectionIndex < watchlistSections.lastIndex) {
                                        focusedSectionIndex++
                                        focusedItemIndex = 0
                                    }
                                }
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
                                            SidebarItem.SETTINGS -> onNavigateToSettings("")
                                            null -> Unit
                                        }
                                    }
                                }
                                WatchlistFocusZone.PROVIDERS -> activateProvider(providerFocusIndex)
                                WatchlistFocusZone.LIBRARIES -> providerLibraries.getOrNull(libraryFocusIndex)?.let { library ->
                                    if (isHomeServerMode) {
                                        viewModel.selectLibrary(library.sourceRef)
                                    } else {
                                        viewModel.selectSource(library.sourceRef)
                                    }
                                    focusedItemIndex = 0
                                }
                                WatchlistFocusZone.FILTERS -> activateFilter(filterFocusIndex)
                                WatchlistFocusZone.CONTENT -> enterKeyDownTimeMs = SystemClock.elapsedRealtime()
                            }
                            true
                        }
                        else -> false
                    }
                } else if (event.type == KeyEventType.KeyUp && effectiveKey in listOf(Key.Enter, Key.DirectionCenter)) {
                    if (focusZone == WatchlistFocusZone.CONTENT && enterKeyDownTimeMs >= 0L) {
                        val holdMs = SystemClock.elapsedRealtime() - enterKeyDownTimeMs
                        val item = if (isLibraryMode) {
                            visibleLibraryItems.getOrNull(focusedItemIndex)
                        } else {
                            watchlistSections.getOrNull(focusedSectionIndex)?.second?.getOrNull(focusedItemIndex)
                        }
                        if (item != null) {
                            if (!isLibraryMode && holdMs >= longPressThresholdMs) viewModel.removeFromWatchlist(item)
                            else onNavigateToDetails(item.mediaType, item.id)
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
                .padding(top = if (isMobile) 0.dp else AppTopBarHeight - 10.dp)
        ) {
            if (isMobile) {
                Text(
                    text = stringResource(R.string.nav_library),
                    style = ArflixTypography.heroTitle.copy(fontSize = 28.sp),
                    color = TextPrimary,
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 12.dp)
                )
            }

            ProviderTabs(
                providers = providers,
                selectedIndex = selectedProviderIndex,
                focusedIndex = if (focusZone == WatchlistFocusZone.PROVIDERS) providerFocusIndex else -1,
                libraryState = activeLibraryState,
                filters = filters,
                focusedFilterIndex = if (focusZone == WatchlistFocusZone.FILTERS) filterFocusIndex else -1,
                showLibraryControls = true,
                isMobile = isMobile,
                onSelect = ::activateProvider,
                onFilterSelect = { index ->
                    filterFocusIndex = index
                    activateFilter(index)
                }
            )

            if (isLibraryMode) {
                HomeLibraryContent(
                    state = activeLibraryState,
                    logoUrls = logoUrls,
                    libraries = providerLibraries,
                    selectedLibraryIndex = selectedLibraryIndex,
                    focusedLibraryIndex = if (focusZone == WatchlistFocusZone.LIBRARIES) libraryFocusIndex else -1,
                    focusedItemIndex = if (focusZone == WatchlistFocusZone.CONTENT) focusedItemIndex else -1,
                    gridState = libraryGridState,
                    columns = libraryColumns,
                    cardWidth = libraryCardWidth,
                    isLandscape = !usePosterCards,
                    isMobile = isMobile,
                    onLibrarySelect = { index, library ->
                        libraryFocusIndex = index
                        if (isHomeServerMode) {
                            viewModel.selectLibrary(library.sourceRef)
                        } else {
                            viewModel.selectSource(library.sourceRef)
                        }
                        focusedItemIndex = 0
                    },
                    onItemFocused = { focusedItemIndex = it },
                    onItemVisible = viewModel::ensureLogo,
                    onItemClick = { onNavigateToDetails(it.mediaType, it.id) },
                    onLoadMore = {
                        if (isHomeServerMode) viewModel.loadMoreLibrary() else viewModel.loadMoreActiveSource()
                    }
                )
            } else {
                WatchlistContent(
                    uiState = uiState,
                    sections = watchlistSections,
                    logoUrls = logoUrls,
                    cardWidth = cardWidth,
                    isLandscape = !usePosterCards,
                    isMobile = isMobile,
                    focusedSectionIndex = if (focusZone == WatchlistFocusZone.CONTENT) focusedSectionIndex else -1,
                    focusedItemIndex = if (focusZone == WatchlistFocusZone.CONTENT) focusedItemIndex else -1,
                    listState = watchlistColumnState,
                    onItemFocused = { index -> focusedItemIndex = index },
                    onItemClick = { onNavigateToDetails(it.mediaType, it.id) },
                    onItemLongPress = viewModel::removeFromWatchlist
                )
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

        TextInputModal(
            isVisible = showSearchModal,
            title = tr("Search library"),
            hint = tr("Movie or series title"),
            initialValue = activeLibraryState.searchQuery,
            onConfirm = {
                showSearchModal = false
                if (isHomeServerMode) {
                    viewModel.setLibrarySearch(it.trim())
                } else {
                    trackerSearchQuery = it.trim()
                }
                focusZone = WatchlistFocusZone.FILTERS
            },
            onCancel = {
                showSearchModal = false
                focusZone = WatchlistFocusZone.FILTERS
            }
        )

        if (showSortMenu) {
            SortSelectionOverlay(
                options = sortOptions,
                selectedSort = activeLibraryState.sort,
                focusedIndex = sortFocusIndex,
                isMobile = isMobile,
                onFocus = { sortFocusIndex = it },
                onSelect = { sort ->
                    selectSort(sort)
                    showSortMenu = false
                    focusZone = WatchlistFocusZone.FILTERS
                },
                onDismiss = {
                    showSortMenu = false
                    focusZone = WatchlistFocusZone.FILTERS
                }
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ProviderTabs(
    providers: List<LibraryProviderOption>,
    selectedIndex: Int,
    focusedIndex: Int,
    libraryState: HomeLibraryUiState,
    filters: List<LibraryFilter>,
    focusedFilterIndex: Int,
    showLibraryControls: Boolean,
    isMobile: Boolean,
    onSelect: (Int) -> Unit,
    onFilterSelect: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        itemsIndexed(providers, key = { _, provider -> provider.id }) { index, provider ->
            val accent = when {
                provider.isHomeServer -> providerAccent(provider.homeServerKind)
                provider.label == "Trakt" -> Color(0xFFED1C24)
                provider.label == "Simkl" -> Color(0xFF00A7B5)
                else -> Color.White
            }
            SelectablePill(
                label = provider.label,
                selected = index == selectedIndex,
                focused = index == focusedIndex,
                accent = accent,
                modifier = Modifier.clickable(enabled = isMobile) { onSelect(index) },
                leading = if (provider.isWatchlist) null else accent,
                compact = true
            )
        }
        if (showLibraryControls) {
            itemsIndexed(filters, key = { index, filter -> "library-control-$index-${filter.label}" }) { index, filter ->
                LibraryFilterControl(
                    state = libraryState,
                    filter = filter,
                    index = index,
                    focusedFilterIndex = focusedFilterIndex,
                    isMobile = isMobile,
                    onSelect = { onFilterSelect(index) }
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ColumnScope.HomeLibraryContent(
    state: HomeLibraryUiState,
    logoUrls: Map<String, String>,
    libraries: List<HomeServerCatalogCandidate>,
    selectedLibraryIndex: Int,
    focusedLibraryIndex: Int,
    focusedItemIndex: Int,
    gridState: androidx.compose.foundation.lazy.grid.LazyGridState,
    columns: Int,
    cardWidth: Dp,
    isLandscape: Boolean,
    isMobile: Boolean,
    onLibrarySelect: (Int, HomeServerCatalogCandidate) -> Unit,
    onItemFocused: (Int) -> Unit,
    onItemVisible: (MediaItem) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onLoadMore: () -> Unit
) {
    if (isMobile && libraries.isNotEmpty()) {
        MobileLibrarySelector(
            libraries = libraries,
            selectedIndex = selectedLibraryIndex,
            onSelect = onLibrarySelect
        )
    }

    if (isMobile) {
        LibraryResults(
            state = state,
            logoUrls = logoUrls,
            focusedItemIndex = focusedItemIndex,
            gridState = gridState,
            columns = columns,
            cardWidth = cardWidth,
            isLandscape = isLandscape,
            isMobile = true,
            onItemFocused = onItemFocused,
            onItemVisible = onItemVisible,
            onItemClick = onItemClick,
            onLoadMore = onLoadMore
        )
    } else {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LibrarySidebar(
                libraries = libraries,
                selectedIndex = selectedLibraryIndex,
                focusedIndex = focusedLibraryIndex,
                onSelect = onLibrarySelect
            )
            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                LibraryResults(
                    state = state,
                    logoUrls = logoUrls,
                    focusedItemIndex = focusedItemIndex,
                    gridState = gridState,
                    columns = columns,
                    cardWidth = cardWidth,
                    isLandscape = isLandscape,
                    isMobile = false,
                    onItemFocused = onItemFocused,
                    onItemVisible = onItemVisible,
                    onItemClick = onItemClick,
                    onLoadMore = onLoadMore
                )
            }
        }
    }
}

@Composable
private fun MobileLibrarySelector(
    libraries: List<HomeServerCatalogCandidate>,
    selectedIndex: Int,
    onSelect: (Int, HomeServerCatalogCandidate) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selected = libraries.getOrNull(selectedIndex) ?: libraries.first()
    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 1.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(6.dp))
                .border(1.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(6.dp))
                .clickable { expanded = true }
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(2.dp))
            )
            Column(modifier = Modifier.padding(start = 10.dp).weight(1f)) {
                Text(
                    text = selected.collectionName.ifBlank { selected.title },
                    style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = selected.serverName,
                    style = ArflixTypography.caption.copy(fontSize = 10.sp),
                    color = Color.White.copy(alpha = 0.45f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(Icons.Outlined.ArrowDropDown, contentDescription = tr("Choose library"), tint = Color.White.copy(alpha = 0.72f))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF151719)).width(280.dp)
        ) {
            libraries.forEachIndexed { index, library ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = library.collectionName.ifBlank { library.title },
                            color = if (index == selectedIndex) Color.White else Color.White.copy(alpha = 0.72f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    onClick = {
                        expanded = false
                        onSelect(index, library)
                    },
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(2.dp))
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun LibrarySidebar(
    libraries: List<HomeServerCatalogCandidate>,
    selectedIndex: Int,
    focusedIndex: Int,
    onSelect: (Int, HomeServerCatalogCandidate) -> Unit
) {
    val listState = rememberLazyListState()
    LaunchedEffect(focusedIndex, selectedIndex, libraries.firstOrNull()?.sourceRef, libraries.size) {
        if (libraries.isEmpty()) return@LaunchedEffect
        val targetIndex = (if (focusedIndex >= 0) focusedIndex else selectedIndex).coerceIn(libraries.indices)
        val layout = snapshotFlow { listState.layoutInfo }.first { it.totalItemsCount == libraries.size }
        val target = layout.visibleItemsInfo.firstOrNull { it.index == targetIndex }
        if (target == null) {
            listState.animateScrollToItem(targetIndex)
        } else {
            val delta = libraryRevealScrollDelta(target.offset, target.size,
                layout.viewportStartOffset + layout.beforeContentPadding,
                layout.viewportEndOffset - layout.afterContentPadding)
            if (delta != 0) listState.animateScrollBy(delta.toFloat(), tween(120))
        }
    }
    Column(
        modifier = Modifier
            .width(184.dp)
            .fillMaxHeight()
            .padding(start = 24.dp, bottom = 24.dp)
    ) {
        val activeServerName = libraries.getOrNull(selectedIndex)?.serverName
            ?: libraries.firstOrNull()?.serverName
            ?: tr("Home server")
        Text(
            text = activeServerName,
            style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
            color = Color.White.copy(alpha = 0.92f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 16.sp,
            modifier = Modifier.padding(start = 10.dp, top = 6.dp, end = 8.dp)
        )
        Text(
            text = tr("Libraries"),
            style = ArflixTypography.caption.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
            color = Color.White.copy(alpha = 0.4f),
            modifier = Modifier.padding(start = 10.dp, top = 2.dp, bottom = 7.dp)
        )
        LazyColumn(
            state = listState,
            modifier = Modifier.testTag("library-sidebar").weight(1f),
            contentPadding = PaddingValues(vertical = 2.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            itemsIndexed(libraries, key = { _, item -> item.sourceRef }) { index, library ->
                val selected = index == selectedIndex
                val focused = index == focusedIndex
                Row(
                    modifier = Modifier
                        .testTag("library-sidebar-${library.sourceRef}")
                        .semantics {
                            this.selected = selected
                            this.focused = focused
                        }
                        .fillMaxWidth()
                        .height(44.dp)
                        .background(
                            when {
                                selected -> Color.White.copy(alpha = if (focused) 0.14f else 0.1f)
                                focused -> Color.White.copy(alpha = 0.06f)
                                else -> Color.Transparent
                            },
                            RoundedCornerShape(6.dp)
                        )
                        .border(
                            if (focused) 2.dp else 1.dp,
                            when {
                                focused -> Color.White
                                selected -> Color.White.copy(alpha = 0.3f)
                                else -> Color.Transparent
                            },
                            RoundedCornerShape(6.dp)
                        )
                        .clickable(enabled = false) { onSelect(index, library) }
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (library.collectionType.lowercase().let { "movie" in it || "film" in it }) {
                            Icons.Outlined.Movie
                        } else {
                            Icons.Outlined.Tv
                        },
                        contentDescription = null,
                        tint = Color.White.copy(alpha = if (selected || focused) 0.92f else 0.62f),
                        modifier = Modifier.size(17.dp)
                    )
                    Text(
                        text = library.collectionName.ifBlank { library.title },
                        style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium),
                        color = Color.White.copy(alpha = if (selected || focused) 1f else 0.66f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun LibraryFilterControl(
    state: HomeLibraryUiState,
    filter: LibraryFilter,
    index: Int,
    focusedFilterIndex: Int,
    isMobile: Boolean,
    onSelect: () -> Unit
) {
    val selected = when {
        filter.isSort -> true
        filter.isSearch -> state.searchQuery.isNotBlank()
        else -> false
    }
    val label = when {
        filter.isSort -> when (state.sort) {
            HomeServerLibrarySort.RECENTLY_ADDED -> tr("Recently added")
            HomeServerLibrarySort.RATING -> tr("Highest rated")
            HomeServerLibrarySort.TITLE -> tr("Title A-Z")
            HomeServerLibrarySort.RELEASE_DATE_NEWEST -> stringResource(R.string.library_sort_release_newest)
            HomeServerLibrarySort.RELEASE_DATE_OLDEST -> stringResource(R.string.library_sort_release_oldest)
        }
        filter.isSearch && state.searchQuery.isNotBlank() -> state.searchQuery
        else -> filter.label
    }
    SelectablePill(
        label = label,
        selected = selected,
        focused = index == focusedFilterIndex,
        accent = Color.White,
        compact = true,
        iconOnly = filter.iconOnly,
        icon = when {
            filter.isSort -> Icons.AutoMirrored.Outlined.Sort
            filter.isSearch -> Icons.Outlined.Search
            filter.isRefresh -> Icons.Outlined.Refresh
            else -> null
        },
        modifier = Modifier.clickable(enabled = isMobile) { onSelect() }
    )
}

@Composable
private fun SortSelectionOverlay(
    options: List<Pair<String, HomeServerLibrarySort>>,
    selectedSort: HomeServerLibrarySort,
    focusedIndex: Int,
    isMobile: Boolean,
    onFocus: (Int) -> Unit,
    onSelect: (HomeServerLibrarySort) -> Unit,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.62f))
            .clickable(enabled = isMobile) { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(if (isMobile) 304.dp else 330.dp)
                .background(Color(0xFF151719), RoundedCornerShape(8.dp))
                .border(1.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(8.dp))
                .clickable(enabled = isMobile) { }
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = tr("Sort library"),
                style = ArflixTypography.sectionTitle.copy(fontSize = 18.sp),
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 7.dp)
            )
            options.forEachIndexed { index, (label, sort) ->
                val focused = index == focusedIndex
                val selected = sort == selectedSort
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .background(
                            when {
                                selected -> Color.White.copy(alpha = if (focused) 0.15f else 0.1f)
                                focused -> Color.White.copy(alpha = 0.06f)
                                else -> Color.Transparent
                            },
                            RoundedCornerShape(6.dp)
                        )
                        .border(
                            if (focused) 2.dp else 1.dp,
                            if (focused) Color.White else Color.Transparent,
                            RoundedCornerShape(6.dp)
                        )
                        .clickable(enabled = isMobile) {
                            onFocus(index)
                            onSelect(sort)
                        }
                        .padding(horizontal = 13.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = label,
                        style = ArflixTypography.body.copy(fontSize = 14.sp),
                        color = Color.White.copy(alpha = if (focused || selected) 1f else 0.7f),
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    if (selected) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = tr("Selected"),
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.LibraryResults(
    state: HomeLibraryUiState,
    logoUrls: Map<String, String>,
    focusedItemIndex: Int,
    gridState: androidx.compose.foundation.lazy.grid.LazyGridState,
    columns: Int,
    cardWidth: Dp,
    isLandscape: Boolean,
    isMobile: Boolean,
    onItemFocused: (Int) -> Unit,
    onItemVisible: (MediaItem) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onLoadMore: () -> Unit
) {
    when {
        state.isLoading && state.items.isEmpty() -> CenteredLoading()
        state.error != null && state.items.isEmpty() -> LibraryMessage(
            title = tr("Library unavailable"),
            subtitle = state.error
        )
        state.items.isEmpty() -> LibraryMessage(
            title = if (state.searchQuery.isBlank()) tr("This library is empty") else tr("No matching titles"),
            subtitle = if (state.searchQuery.isBlank()) tr("Choose another library") else tr("Try a different search")
        )
        else -> {
            val contentAlpha by animateFloatAsState(
                targetValue = if (state.isLoading) 0.56f else 1f,
                animationSpec = tween(durationMillis = 140),
                label = "library-content-alpha"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    state = gridState,
                    modifier = Modifier
                        .testTag("library-grid")
                        .fillMaxSize()
                        .graphicsLayer { alpha = contentAlpha }
                        .padding(horizontal = 24.dp),
                    contentPadding = PaddingValues(top = 6.dp, bottom = 24.dp + LocalBottomBarInset.current),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    userScrollEnabled = isMobile && !state.isLoading
                ) {
                    gridItemsIndexed(
                        items = state.items,
                        key = { index, item -> watchlistItemKey(item, index) },
                        contentType = { _, item -> "library-${item.mediaType}" }
                    ) { index, item ->
                        LaunchedEffect(watchlistLogoKey(item)) {
                            onItemVisible(item)
                        }
                        MediaCard(
                            modifier = Modifier.testTag("library-card-$index")
                                .semantics { selected = index == focusedItemIndex },
                            item = item,
                            width = cardWidth,
                            isLandscape = isLandscape,
                            logoImageUrl = logoUrls[watchlistLogoKey(item)],
                            showTitle = true,
                            titleMaxLines = 2,
                            isFocusedOverride = index == focusedItemIndex,
                            enableSystemFocus = false,
                            onFocused = { onItemFocused(index) },
                            onClick = { onItemClick(item) }
                        )
                    }
                    if (state.isLoadingMore) {
                        item(key = "library-loading-more", span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadingIndicator(color = Pink, size = 34.dp)
                            }
                        }
                    }
                }
                if (state.isLoading) {
                    Box(
                        modifier = Modifier.align(Alignment.TopEnd).padding(top = 12.dp, end = 34.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator(color = Color.White, size = 25.dp)
                    }
                }
            }
            LaunchedEffect(gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index) {
                val last = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
                if (last >= state.items.size - columns * 2) onLoadMore()
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ColumnScope.WatchlistContent(
    uiState: WatchlistUiState,
    sections: List<Pair<String, List<MediaItem>>>,
    logoUrls: Map<String, String>,
    cardWidth: Dp,
    isLandscape: Boolean,
    isMobile: Boolean,
    focusedSectionIndex: Int,
    focusedItemIndex: Int,
    listState: androidx.compose.foundation.lazy.LazyListState,
    onItemFocused: (Int) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onItemLongPress: (MediaItem) -> Unit
) {
    val totalItems = uiState.movies.size + uiState.series.size
    when {
        uiState.isLoading -> CenteredLoading()
        totalItems == 0 -> {
            Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Outlined.Bookmark,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = tr("Your watchlist is empty"),
                        style = ArflixTypography.body,
                        color = Color.White.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = tr("Add movies and shows for later"),
                        style = ArflixTypography.caption,
                        color = Color.White.copy(alpha = 0.3f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        else -> {
            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f).fillMaxWidth().padding(start = 24.dp, end = 48.dp),
                contentPadding = PaddingValues(top = 10.dp, bottom = 16.dp + LocalBottomBarInset.current),
                verticalArrangement = Arrangement.spacedBy(if (isMobile) 24.dp else 16.dp),
                userScrollEnabled = isMobile
            ) {
                itemsIndexed(sections, key = { _, item -> item.first }) { sectionIdx, (sectionType, items) ->
                    WatchlistItemsSection(
                        title = if (sectionType == "movies") tr("Movies") else tr("Series"),
                        items = items,
                        logoUrls = logoUrls,
                        cardWidth = cardWidth,
                        isLandscape = isLandscape,
                        isMobile = isMobile,
                        focusedItemIndex = if (focusedSectionIndex == sectionIdx) focusedItemIndex else -1,
                        onItemFocused = onItemFocused,
                        onItemClick = onItemClick,
                        onItemLongPress = onItemLongPress
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SelectablePill(
    label: String,
    selected: Boolean,
    focused: Boolean,
    accent: Color,
    modifier: Modifier = Modifier,
    leading: Color? = null,
    compact: Boolean = false,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    iconOnly: Boolean = false
) {
    val shape = RoundedCornerShape(6.dp)
    val scale by animateFloatAsState(
        targetValue = if (focused) 1.018f else 1f,
        animationSpec = tween(durationMillis = 110),
        label = "pill-focus-scale"
    )
    val background = when {
        selected -> Color.White.copy(alpha = if (focused) 0.16f else 0.11f)
        focused -> Color.White.copy(alpha = 0.065f)
        else -> Color.White.copy(alpha = 0.045f)
    }
    val foreground = if (selected || focused) Color.White else Color.White.copy(alpha = 0.66f)
    Row(
        modifier = modifier
            .height(if (compact) 34.dp else 40.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(background, shape)
            .border(
                width = if (focused) 2.dp else if (selected) 1.dp else 0.5.dp,
                color = when {
                    focused -> Color.White
                    selected -> accent.copy(alpha = 0.62f)
                    else -> Color.White.copy(alpha = 0.08f)
                },
                shape = shape
            )
            .padding(horizontal = if (iconOnly) 9.dp else if (compact) 12.dp else 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (leading != null) {
            Box(modifier = Modifier.size(7.dp).background(leading, RoundedCornerShape(2.dp)))
        }
        if (icon != null) {
            Icon(icon, contentDescription = if (iconOnly) label else null, tint = foreground, modifier = Modifier.size(16.dp))
        }
        if (!iconOnly) {
            Text(
                text = label,
                color = foreground,
                fontSize = if (compact) 13.sp else 14.sp,
                fontWeight = if (selected || focused) FontWeight.SemiBold else FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun providerAccent(provider: HomeServerKind?): Color = when (provider) {
    HomeServerKind.PLEX -> Color(0xFFE5A00D)
    HomeServerKind.JELLYFIN -> Color(0xFF9B5DE5)
    HomeServerKind.EMBY -> Color(0xFF52B54B)
    else -> Color.White
}

@Composable
private fun CenteredLoading() {
    Box(modifier = Modifier.fillMaxWidth().fillMaxSize(), contentAlignment = Alignment.Center) {
        LoadingIndicator(color = Pink, size = 56.dp)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun LibraryMessage(title: String, subtitle: String) {
    Box(modifier = Modifier.fillMaxWidth().fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, style = ArflixTypography.sectionTitle, color = TextPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = subtitle, style = ArflixTypography.caption, color = Color.White.copy(alpha = 0.45f))
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun WatchlistItemsSection(
    title: String,
    items: List<MediaItem>,
    logoUrls: Map<String, String>,
    cardWidth: Dp,
    isLandscape: Boolean,
    isMobile: Boolean = false,
    focusedItemIndex: Int = -1,
    onItemFocused: (Int) -> Unit = {},
    onItemClick: (MediaItem) -> Unit,
    onItemLongPress: (MediaItem) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        val lazyListState = rememberLazyListState()
        LaunchedEffect(focusedItemIndex) {
            if (focusedItemIndex < 0) return@LaunchedEffect
            val safe = focusedItemIndex.coerceIn(0, (items.size - 1).coerceAtLeast(0))
            val first = lazyListState.firstVisibleItemIndex
            val last = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: first
            if (safe < first || safe > last) lazyListState.scrollToItem(safe)
            else if (safe != first) lazyListState.animateScrollToItem(safe)
        }

        LazyRow(
            state = lazyListState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 8.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                items = items,
                key = { index, item -> watchlistItemKey(item, index) },
                contentType = { _, item -> "${item.mediaType.name}_card" }
            ) { index, item ->
                MediaCard(
                    item = item,
                    width = cardWidth,
                    isLandscape = isLandscape,
                    logoImageUrl = logoUrls[watchlistLogoKey(item)],
                    showTitle = true,
                    titleMaxLines = 2,
                    isFocusedOverride = index == focusedItemIndex && focusedItemIndex >= 0,
                    enableSystemFocus = false,
                    onFocused = { onItemFocused(index) },
                    onClick = { onItemClick(item) },
                    onLongClick = { onItemLongPress(item) }
                )
            }
        }
    }
}
