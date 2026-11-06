package com.arflix.tv.ui.screens.watchlist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.gestures.BringIntoViewSpec
import androidx.compose.foundation.gestures.LocalBringIntoViewSpec
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.unit.LayoutDirection
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.arflix.tv.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.arflix.tv.data.model.*
import com.arflix.tv.data.repository.*
import com.arflix.tv.ui.components.*
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.tr
import com.arflix.tv.ui.screens.watchlist.calendar.*
import com.arflix.tv.ui.screens.collections.CollectionBackdrop
import com.arflix.tv.ui.screens.collections.CollectionSpotlight
import com.arflix.tv.ui.screens.collections.collectionSpotlightHeight
import kotlinx.coroutines.delay
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.BackgroundElevated
import java.time.LocalDate

internal enum class LibrarySection(val label: String) { WATCHLISTS("Watchlists"), LISTS("My lists"), SERVERS("Homeserver"), CALENDAR("Calendar") }
internal val LibraryTabsTopInset = AppTopBarHeight - 8.dp
@OptIn(ExperimentalFoundationApi::class)
private object LibraryTvBringIntoViewSpec : BringIntoViewSpec {
    override fun calculateScrollDistance(offset: Float, size: Float, containerSize: Float): Float = 0f
}
internal fun libraryColumns(width: Int, poster: Boolean, collections: Boolean = false): Int =
    if (collections) (width / 270).coerceIn(1, 3) else if (poster) (width / 115).coerceIn(2, 8) else (width / 180).coerceIn(2, 4)
internal fun WatchlistSourceItem.isPersonalCollection(): Boolean = this is WatchlistSourceItem.Catalog ||
    (this is WatchlistSourceItem.TrackerList && (provider == TrackerLibraryProvider.MDBLIST ||
        (provider == TrackerLibraryProvider.TRAKT && listKey !in setOf("__watchlist__", "watchlist", "collection", "watched"))))
internal fun librarySources(sources: List<WatchlistSourceItem>, section: LibrarySection): List<WatchlistSourceItem> =
    sources.filter { when (section) {
        LibrarySection.WATCHLISTS -> it is WatchlistSourceItem.MyWatchlist || (it is WatchlistSourceItem.TrackerList && !it.isPersonalCollection())
        LibrarySection.LISTS -> it.isPersonalCollection()
        LibrarySection.SERVERS -> it is WatchlistSourceItem.HomeServer
        LibrarySection.CALENDAR -> false
    } }

@OptIn(androidx.tv.material3.ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel = hiltViewModel(),
    currentProfile: Profile? = null,
    onNavigateToDetails: (MediaType, Int) -> Unit = { _, _ -> },
    onNavigateToHome: () -> Unit = {}, onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {}, onNavigateToSettings: (String?) -> Unit = {},
    onSwitchProfile: () -> Unit = {}, onBack: () -> Unit = {},
    calendarStateOverride: ReleaseCalendarUiState? = null,
    onCalendarSelectDate: (LocalDate) -> Unit = {},
    onCalendarChangeMonth: (Long) -> Unit = {},
    onCalendarSelectSource: (String) -> Unit = {},
    onCalendarRefresh: () -> Unit = {},
    calendarViewModel: ReleaseCalendarViewModel? = null,
    onCalendarOpenRelease: ((com.arflix.tv.data.model.CalendarRelease) -> Unit)? = null
) {
    val rawState by viewModel.uiState.collectAsStateWithLifecycle()
    val rawServers by viewModel.libraryState.collectAsStateWithLifecycle()
    val kidsFilterState by viewModel.kidsFilterState.collectAsStateWithLifecycle()
    // A kids profile sees its lists age-filtered; any other profile gets them unchanged.
    val state = remember(rawState, kidsFilterState) { viewModel.applyKidsFilter(rawState, kidsFilterState) }
    val servers = remember(rawServers, kidsFilterState) { viewModel.applyKidsFilter(rawServers, kidsFilterState) }
    val logos by viewModel.logoUrls.collectAsStateWithLifecycle()
    val touch = LocalDeviceType.current.isTouchDevice()
    val haptic = LocalHapticFeedback.current
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val poster = rememberCardLayoutMode() == CardLayoutMode.POSTER
    val scrollScope = rememberCoroutineScope()
    var section by rememberSaveable { mutableStateOf(LibrarySection.WATCHLISTS) }
    val calendarMode = section == LibrarySection.CALENDAR
    val calendarTab = remember { FocusRequester() }
    var openedList by rememberSaveable { mutableStateOf<String?>(null) }
    var query by rememberSaveable { mutableStateOf("") }
    var sort by rememberSaveable { mutableStateOf(HomeServerLibrarySort.RECENTLY_ADDED) }
    var mediaFilter by rememberSaveable { mutableStateOf<MediaType?>(null) }
    var filters by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf(false) }
    var sourcesOpen by remember { mutableStateOf(false) }
    var topFocused by remember { mutableStateOf(false) }
    var topIndex by remember { mutableIntStateOf(if (currentProfile != null) 3 else 2) }
    val firstTab = remember { FocusRequester() }
    var initialFocusPlaced by remember { mutableStateOf(false) }
    val filterButton = remember { FocusRequester() }
    val topFocus = remember { FocusRequester() }
    val scopeSources = remember(state.sources, servers.libraries, section) { if(section == LibrarySection.SERVERS) servers.libraries.map { WatchlistSourceItem.HomeServer(it) } else librarySources(state.sources, section) }
    val collections = section == LibrarySection.LISTS && openedList == null
    val serverMode = section == LibrarySection.SERVERS
    val selectedId = if (serverMode) "server_${servers.selectedSourceRef}" else state.selectedSourceId
    val savedItems = remember(state.movies, state.series, state.selectedSourceId) {
        val combined = state.movies + state.series
        if(state.selectedSourceId == WatchlistSourceItem.MyWatchlist.id) combined.sortedByDescending { it.addedAt } else combined
    }
    val rawItems = if (serverMode) servers.items else savedItems
    val items = remember(rawItems, query, sort, mediaFilter, serverMode) {
        val filtered = rawItems.filter { (mediaFilter == null || it.mediaType == mediaFilter) && (serverMode || it.title.contains(query, true)) }
        if (serverMode) filtered else sortLibraryItems(filtered, sort)
    }
    val loading = if (serverMode) servers.isLoading else state.isLoading
    val error = if (serverMode) servers.error else state.error
    val sourceKey = if (collections) "collections" else "$section:$selectedId:$query:$sort:$mediaFilter"
    val viewports = rememberSaveable(saver = mapSaver(
        save = { states: MutableMap<String, LazyGridState> -> states.mapValues { (_, grid) -> arrayListOf(grid.firstVisibleItemIndex, grid.firstVisibleItemScrollOffset) } },
        restore = { saved -> saved.mapValues { (_, value) -> val position = value as List<*>; LazyGridState(position[0] as Int, position[1] as Int) }.toMutableMap() }
    )) { mutableMapOf<String, LazyGridState>() }
    val grid = remember(sourceKey) { viewports.getOrPut(sourceKey) { LazyGridState() } }
    var revealJob by remember(grid) { mutableStateOf<Job?>(null) }
    var focusedGridRow by remember(grid) { mutableStateOf<Pair<Int, Int>?>(null) }
    DisposableEffect(grid) {
        onDispose { revealJob?.cancel() }
    }
    fun revealCard(index: Int, columns: Int) {
        if (touch) return
        val row = columns to index / columns
        // Sideways focus must not cancel or restart the current row's reveal.
        if (focusedGridRow == row) return
        focusedGridRow = row
        revealJob?.cancel()
        revealJob = scrollScope.launch {
            withFrameNanos { }
            val layout = grid.layoutInfo
            val card = layout.visibleItemsInfo.firstOrNull { it.index == index }
            if (card == null) grid.animateScrollToItem(index / columns * columns) else {
                val delta = libraryRowAnchorScrollDelta(card.offset.y, card.size.height,
                    layout.viewportStartOffset + layout.beforeContentPadding,
                    layout.viewportEndOffset - layout.afterContentPadding)
                if (delta != 0) grid.animateScrollBy(delta.toFloat(), tween(160))
            }
        }
    }
    var focusedPreviewKey by rememberSaveable(currentProfile?.id, sourceKey) { mutableStateOf<String?>(null) }
    val touchPreviewIndex by remember(grid) { derivedStateOf { grid.firstVisibleItemIndex } }
    val previewCandidate = if (touch) items.getOrNull(touchPreviewIndex.coerceAtMost(items.lastIndex.coerceAtLeast(0)))
        else items.firstOrNull { libraryPreviewKey(it) == focusedPreviewKey } ?: items.firstOrNull()
    var readyPreview by remember(currentProfile?.id, sourceKey) { mutableStateOf<LibraryPreview?>(null) }
    val activePreview = readyPreview ?: previewCandidate?.let { LibraryPreview(it) }
    val previewItem = activePreview?.item
    LaunchedEffect(currentProfile?.id, sourceKey, previewCandidate?.let(::libraryPreviewKey), calendarMode, collections) {
        if (!calendarMode && !collections) {
            val item = previewCandidate
            if (item == null) {
                readyPreview = null
                return@LaunchedEffect
            }
            delay(100)
            viewModel.ensureLogo(item)
            // Swap complete previews together; never clear the description between cards.
            val preview = viewModel.loadLibraryPreview(item)
            readyPreview = preview
            if (item.isHomeServer) {
                viewModel.loadLibraryImdbRating(item)?.let { rating ->
                    readyPreview = preview.copy(item = preview.item.copy(imdbRating = rating))
                }
            }
        }
    }
    fun selectSource(source: WatchlistSourceItem) {
        if (source is WatchlistSourceItem.HomeServer) {
            viewModel.selectLibraryProvider(source.candidate.serverKind)
            viewModel.selectLibrary(source.candidate.sourceRef)
        } else viewModel.selectSource(source.id)
        sourcesOpen = false
    }
    fun selectSection(next: LibrarySection) {
        section = next; openedList = null; query = ""; mediaFilter = null
        search = false; sourcesOpen = false; filters = false
        if (next == LibrarySection.WATCHLISTS) viewModel.selectSource(WatchlistSourceItem.MyWatchlist.id)
        if (next == LibrarySection.SERVERS && servers.selectedSourceRef == null) {
            servers.libraries.firstOrNull()?.let { viewModel.selectLibraryProvider(it.serverKind); viewModel.selectLibrary(it.sourceRef) }
        }
    }
    BackHandler(!search && !filters && !sourcesOpen) {
        when { openedList != null -> openedList = null; !touch && !topFocused -> topFocus.requestFocus(); else -> onBack() }
    }
    BackHandler(search && touch) {
        search = false
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle, viewModel) {
        val observer = LifecycleEventObserver { _, event -> if(event == Lifecycle.Event.ON_RESUME) viewModel.refreshAfterResume() }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    LaunchedEffect(serverMode, query) { if (serverMode) viewModel.setLibrarySearch(query) }
    LaunchedEffect(serverMode, servers.libraries, servers.selectedSourceRef) {
        if (serverMode && servers.selectedSourceRef == null) {
            servers.libraries.firstOrNull()?.let { selectSource(WatchlistSourceItem.HomeServer(it)) }
        }
    }
    LaunchedEffect(serverMode, sort) { if (serverMode) viewModel.setLibrarySort(sort) }
    val loadingMore = if(serverMode) servers.isLoadingMore else state.isLoadingMore
    val hasMore = if(serverMode) servers.hasMore else state.hasMore
    LaunchedEffect(grid, sourceKey, items.size, rawItems.size, error, loading, loadingMore, hasMore) {
        snapshotFlow { grid.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1 }.collect { last ->
            if (!calendarMode && !collections && error == null && !loading && !loadingMore && hasMore && (items.isEmpty() || last >= items.size - 16)) {
                if (serverMode) viewModel.loadMoreLibrary() else viewModel.loadMoreActiveSource()
            }
        }
    }
    LaunchedEffect(grid, sourceKey, items, poster) {
        if (!calendarMode && !poster && !collections) snapshotFlow { grid.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }.collect { last ->
            val first = grid.firstVisibleItemIndex
            viewModel.prefetchLogos(items.subList(first.coerceAtMost(items.size), (last + 17).coerceAtMost(items.size)))
        }
    }
    BoxWithConstraints(Modifier.fillMaxSize().background(Color.Black).testTag("oled-library")) {
        val compact = touch && (maxWidth < 700.dp || maxHeight < 500.dp)
        val sideWidth = if (touch) 160.dp else 126.dp
            if (!touch) {
                Box(Modifier.fillMaxWidth().height(AppTopBarContentTopInset).focusRequester(topFocus).onFocusChanged { topFocused = it.isFocused }
                    .onKeyEvent { event ->
                        // The top bar's index runs left-to-right, but Compose mirrors
                        // the row in an RTL locale, so the physical key has to be
                        // mirrored back before it reaches index arithmetic written
                        // against an LTR layout — otherwise the menu walks the wrong way.
                        if (event.type != KeyEventType.KeyDown) false else when(event.key.mirrorHorizontalForRtl(isRtl)) {
                            Key.DirectionLeft -> { topIndex = (topIndex - 1).coerceAtLeast(0); true }
                            Key.DirectionRight -> { topIndex = (topIndex + 1).coerceAtMost(topBarMaxIndex(currentProfile != null)); true }
                            Key.DirectionDown -> { firstTab.requestFocus(); true }
                            Key.Enter, Key.DirectionCenter, Key.NumPadEnter -> {
                                when(topBarFocusedItem(topIndex, currentProfile != null)) {
                                    SidebarItem.HOME -> onNavigateToHome(); SidebarItem.SEARCH -> onNavigateToSearch()
                                    SidebarItem.TV -> onNavigateToTv(); SidebarItem.SETTINGS -> onNavigateToSettings(null)
                                    SidebarItem.WATCHLIST -> firstTab.requestFocus(); null -> onSwitchProfile()
                                }; true
                            }
                            else -> false
                        }
                    }.focusable()) {
                    AppTopBar(SidebarItem.WATCHLIST, topFocused, topIndex, profile = currentProfile)
                }
            }
        Column(Modifier.fillMaxSize().padding(top = if (touch) 0.dp else LibraryTabsTopInset)
            .padding(horizontal = if (compact) 16.dp else 26.dp)) {
            if (touch) Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth().testTag("library-section-tabs").then(if (compact) Modifier.horizontalScroll(rememberScrollState()) else Modifier)
                .heightIn(min = 48.dp)
                .padding(bottom = if(compact) 10.dp else 6.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(if (compact) 4.dp else 8.dp)) {
                LibrarySection.entries.forEachIndexed { index, entry ->
                    OledControl(tr(entry.label), selected = section == entry,
                        modifier = (if(index == 0) Modifier.focusRequester(firstTab).onGloballyPositioned {
                            if(!touch && !initialFocusPlaced) { initialFocusPlaced = true; firstTab.requestFocus() }
                        } else if (entry == LibrarySection.CALENDAR) Modifier.focusRequester(calendarTab) else Modifier), compact = compact,
                        onClick = { selectSection(entry) })
                }
                if (!compact && !calendarMode) {
                    Spacer(Modifier.weight(1f))
                    val serverTotal = servers.totalCount.takeIf { serverMode && mediaFilter == null }
                    Text(if(collections) "${scopeSources.size} ${tr("lists")}" else "${serverTotal ?: items.size}${if(serverTotal == null && hasMore) "+" else ""} ${tr("titles")}", color = Color.LightGray, fontSize = 13.sp)
                    OledControl("⌕", onClick = { sourcesOpen = false; filters = false; search = true })
                    if(collections) OledControl("+ " + tr("New list"), onClick = { onNavigateToSettings("catalogs") })
                    OledControl(tr("Filters"), modifier = Modifier.focusRequester(filterButton), onClick = { sourcesOpen = false; filters = true })
                }
            }
            if (calendarMode) {
                if (calendarStateOverride != null) LibraryCalendarPane(
                    state = calendarStateOverride, onSelectDate = onCalendarSelectDate,
                    onChangeMonth = onCalendarChangeMonth, onSelectSource = onCalendarSelectSource,
                    onRefresh = onCalendarRefresh, onOpenDetails = onNavigateToDetails, onOpenRelease = onCalendarOpenRelease,
                    modifier = Modifier.weight(1f), onExitUp = { calendarTab.requestFocus() })
                else LibraryCalendarRoute(onOpenDetails = onNavigateToDetails, onOpenRelease = onCalendarOpenRelease,
                    modifier = Modifier.weight(1f), onExitUp = { calendarTab.requestFocus() },
                    viewModel = calendarViewModel ?: hiltViewModel())
            } else {
            if (compact) {
                if (search) {
                    val searchFocus = remember { FocusRequester() }
                    val keyboard = LocalSoftwareKeyboardController.current
                    LaunchedEffect(searchFocus) {
                        searchFocus.requestFocus()
                        // Let Android attach the new field's input connection before showing the IME.
                        withFrameNanos { }
                        keyboard?.show()
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .height(42.dp)
                            .clip(CircleShape)
                            .background(BackgroundElevated)
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = tr("Search library"),
                            tint = resolveAccentColor(Color.White),
                            modifier = Modifier.size(18.dp)
                        )
                        BasicTextField(
                            value = query,
                            onValueChange = { query = it },
                            modifier = Modifier.weight(1f).focusRequester(searchFocus).testTag("library-search-field"),
                            singleLine = true,
                            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 14.sp),
                            cursorBrush = SolidColor(resolveAccentColor(Color.White)),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = { search = false }),
                            decorationBox = { innerTextField ->
                                if (query.isEmpty()) {
                                    Text(tr("Search library…"), color = Color.Gray, fontSize = 14.sp)
                                }
                                innerTextField()
                            }
                        )
                        if (query.isNotEmpty()) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.15f))
                                    .clickable { query = "" },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = tr("Clear search"),
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable { search = false }
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                        ) {
                            Text(
                                tr("Done"),
                                color = resolveAccentColor(Color.White),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                } else {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!collections) OledControl(
                            label = scopeSources.firstOrNull { it.id == selectedId }?.title ?: tr("Choose library"),
                            modifier = Modifier.weight(1f).testTag("library-source-picker"),
                            compact = true,
                            trailingIcon = Icons.Outlined.KeyboardArrowDown,
                            onClick = { sourcesOpen = true }
                        ) else OledControl(
                            "+ " + tr("New list"),
                            modifier = Modifier.weight(1f),
                            compact = true,
                            onClick = { onNavigateToSettings("catalogs") }
                        )
                        if (query.isNotEmpty()) {
                            OledControl(
                                label = "\"$query\"",
                                compact = true,
                                selected = true,
                                trailingIcon = Icons.Default.Close,
                                onClick = { query = "" }
                            )
                        } else {
                            OledControl(
                                "⌕",
                                compact = true,
                                onClick = { sourcesOpen = false; filters = false; search = true }
                            )
                        }
                        val hasActiveFilters = mediaFilter != null || sort != HomeServerLibrarySort.RECENTLY_ADDED
                        OledControl(
                            label = tr("Filters"),
                            compact = true,
                            selected = hasActiveFilters,
                            hasActiveBadge = hasActiveFilters,
                            modifier = Modifier.focusRequester(filterButton),
                            onClick = { sourcesOpen = false; filters = true }
                        )
                    }
                }
            }
            if (openedList != null) Row(Modifier.padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                OledControl("‹ ${tr("My lists")}", onClick = { openedList = null })
                Text(state.selectedSource.title, color = Color.White, fontSize = 15.sp, modifier = Modifier.padding(start = 12.dp))
            }
            Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                if (!compact && !collections && openedList == null) {
                    OledSources(scopeSources, selectedId, Modifier.width(sideWidth), ::selectSource, onNavigateToSettings)
                }
                BoxWithConstraints(Modifier.weight(1f).fillMaxHeight()) {
                    val columns = libraryColumns(maxWidth.value.toInt(), poster, collections)
                    val width = (maxWidth - 12.dp * (columns - 1)) / columns
                    if (serverMode) LibraryArtworkPrefetch(grid, items, width - 12.dp, columns, poster)
                    val previewCatalog = remember(sourceKey, state.selectedSource.title, scopeSources, selectedId) {
                        CatalogConfig(sourceKey, scopeSources.firstOrNull { it.id == selectedId }?.title
                            ?: state.selectedSource.title, CatalogSourceType.PREINSTALLED)
                    }
                    val spotlightItem = previewItem
                    val compactSpotlight = compact || maxHeight < 440.dp
                    val showSpotlight = !collections && items.isNotEmpty()
                    if (showSpotlight) CollectionBackdrop(previewCatalog, spotlightItem, touch,
                        collectionSpotlightHeight(touch, compactSpotlight) + 150.dp,
                        background = Color.Black)
                    Column(Modifier.fillMaxSize()) {
                    if (showSpotlight) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .then(if (touch && spotlightItem != null) Modifier.clickable {
                                    onNavigateToDetails(spotlightItem.mediaType, spotlightItem.id)
                                } else Modifier)
                        ) {
                            CollectionSpotlight(previewCatalog, spotlightItem, touch, compactSpotlight, {},
                                providerLogoUrl = activePreview?.providerLogoUrl ?: spotlightItem?.primaryNetworkLogo,
                                clearLogoUrl = previewItem?.let { logos[watchlistLogoKey(it)] },
                                horizontalInset = 6.dp, showBackButton = false, topInset = 16.dp)
                        }
                    }
                    // Row anchoring keeps the previous row entirely outside this viewport.
                    BoxWithConstraints(Modifier.weight(1f).fillMaxWidth().clipToBounds()) {
                    val rowHeight by remember(grid) { derivedStateOf {
                        grid.layoutInfo.visibleItemsInfo.maxOfOrNull { it.size.height } ?: 0
                    } }
                    val minimumBottomPadding = 16.dp + LocalBottomBarInset.current
                    val bottomPadding = if (touch || collections) minimumBottomPadding else with(LocalDensity.current) {
                        libraryGridBottomPadding(maxHeight.roundToPx(), rowHeight,
                            8.dp.roundToPx(), minimumBottomPadding.roundToPx()).toDp()
                    }
                    // Touch keeps native scrolling; TV moves once per row, never per column.
                    CompositionLocalProvider(LocalBringIntoViewSpec provides
                        if (touch || collections) LocalBringIntoViewSpec.current else LibraryTvBringIntoViewSpec) {
                    if (collections) {
                        val lists = scopeSources.filter { it.title.contains(query, true) }
                        if (lists.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 24.dp, vertical = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(68.dp)
                                            .clip(CircleShape)
                                            .background(Color.White.copy(alpha = 0.08f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = if (query.isNotEmpty()) Icons.Outlined.Search else Icons.Default.BookmarkBorder,
                                            contentDescription = null,
                                            tint = resolveAccentColor(Color.White),
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(
                                            text = if (query.isNotEmpty()) tr("No matching lists") else tr("No lists yet"),
                                            color = Color.White,
                                            fontSize = 19.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (query.isNotEmpty()) tr("No lists found matching \"%s\"").format(query)
                                                else tr("Your custom catalogs and personal lists appear here."),
                                            color = Color.Gray,
                                            fontSize = 13.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.widthIn(max = 280.dp)
                                        )
                                    }
                                    if (query.isNotEmpty()) {
                                        OledControl(
                                            label = tr("Clear search"),
                                            compact = touch,
                                            onClick = { query = "" }
                                        )
                                    } else {
                                        OledControl(
                                            label = "+ " + tr("New list"),
                                            compact = touch,
                                            selected = true,
                                            onClick = { onNavigateToSettings("catalogs") }
                                        )
                                    }
                                }
                            }
                        } else {
                            LazyVerticalGrid(GridCells.Fixed(columns), state = grid, modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(18.dp),
                                contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp + LocalBottomBarInset.current)) {
                                items(lists, key = { it.id }) { source ->
                                    var cover by remember(source) { mutableStateOf<String?>((source as? WatchlistSourceItem.Catalog)?.config?.collectionCoverImageUrl) }
                                    LaunchedEffect(source) { cover = viewModel.collectionCover(source) }
                                    OledCollection(source, cover, width) { openedList = source.id; viewModel.selectSource(source.id) }
                                }
                            }
                        }
                    } else if (loading && items.isEmpty()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center), color = resolveAccentColor(Color.White))
                    } else if (items.isEmpty()) {
                        Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                            OledMessage(if(error != null) tr("Library unavailable") else tr("No titles found"), error ?: tr("Choose a source or add titles to your watchlist."))
                            if(error != null) OledControl(tr("Retry"), onClick = { if(serverMode) viewModel.refreshLibrary() else viewModel.refresh() })
                        }
                    } else LazyVerticalGrid(GridCells.Fixed(columns), state = grid,
                        modifier = Modifier.fillMaxSize().arvioDpadFocusGroup(enableFocusRestorer = false)
                            .clipToBounds().testTag("library-grid"),
                        horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(18.dp),
                        contentPadding = PaddingValues(top = 8.dp, bottom = bottomPadding)) {
                        itemsIndexed(items, key = { index, item -> watchlistItemKey(item, index) }) { index, item ->
                            LaunchedEffect(watchlistLogoKey(item), poster) { if(!poster) viewModel.ensureLogo(item) }
                            Box(Modifier.padding(6.dp).testTag("library-card-frame-$index")) {
                            MediaCard(item, width = width - 12.dp, isLandscape = !poster, logoImageUrl = logos[watchlistLogoKey(item)],
                                focusedScale = 1.025f, titleMaxLines = 1, showTitle = true,
                                modifier = Modifier.testTag("library-card-$index"),
                                onFocused = { focusedPreviewKey = libraryPreviewKey(item); viewModel.saveFocusState(0, index); revealCard(index, columns) },
                                onClick = { onNavigateToDetails(item.mediaType, item.id) },
                                onLongClick = if(state.selectedSourceId == WatchlistSourceItem.MyWatchlist.id && !serverMode) ({
                                    if (touch) haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    viewModel.removeFromWatchlist(item)
                                }) else null)
                            }
                        }
                        if (if(serverMode) servers.isLoadingMore else state.isLoadingMore) item("loading-more", span = { GridItemSpan(maxLineSpan) }) {
                            Box(Modifier.fillMaxWidth().padding(12.dp), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(Modifier.size(24.dp), color = resolveAccentColor(Color.White))
                            }
                        }
                        if(error != null) item("retry-page", span = { GridItemSpan(maxLineSpan) }) {
                            OledControl(tr("Could not update this source. Retry"), onClick = { if(serverMode) viewModel.refreshLibrary() else viewModel.refresh() })
                        }
                    }
                    }
                    }
                    }
                }
            }
            }
        }
        state.toastMessage?.let { message ->
            Toast(message = message, isVisible = true, onDismiss = viewModel::dismissToast)
        }
        TextInputModal(search && !compact, tr("Search library"), initialValue = query,
            onConfirm = { query = it.trim(); search = false }, onCancel = { search = false })
    }
    if(sourcesOpen) OledDrawer(tr("Sources"), { sourcesOpen = false }) {
        OledSources(scopeSources, selectedId, Modifier.fillMaxWidth(), ::selectSource, onNavigateToSettings, inDrawer = true)
    }
    if(filters) OledDrawer(tr("Filters"), { filters = false; if(!touch) filterButton.requestFocus() }) {
        Text(tr("Sort"), color = Color.LightGray, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp), fontSize = 13.sp, fontWeight = FontWeight.Medium)
        listOf("Recently added" to HomeServerLibrarySort.RECENTLY_ADDED, "Title A-Z" to HomeServerLibrarySort.TITLE,
            "Highest rated" to HomeServerLibrarySort.RATING, "Newest release" to HomeServerLibrarySort.RELEASE_DATE_NEWEST,
            "Oldest release" to HomeServerLibrarySort.RELEASE_DATE_OLDEST).forEach { (label, value) ->
            OledControl(
                tr(label),
                selected = sort == value,
                trailingIcon = if (sort == value) Icons.Outlined.Check else null,
                compact = touch,
                modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                onClick = {
                    sort = value
                    if (touch) filters = false
                }
            )
        }
        if(!collections) {
            Text(tr("Type"), color = Color.LightGray, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp), fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("All" to null, "Movies" to MediaType.MOVIE, "Series" to MediaType.TV).forEach { (label, value) ->
                    OledControl(
                        tr(label),
                        selected = mediaFilter == value,
                        compact = true,
                        icon = when(value) {
                            MediaType.MOVIE -> Icons.Outlined.Movie
                            MediaType.TV -> Icons.Outlined.Tv
                            else -> null
                        },
                        modifier = Modifier.weight(1f),
                        onClick = { mediaFilter = value }
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (mediaFilter != null || sort != HomeServerLibrarySort.RECENTLY_ADDED) {
                    OledControl(
                        tr("Reset"),
                        compact = touch,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            sort = HomeServerLibrarySort.RECENTLY_ADDED
                            mediaFilter = null
                        }
                    )
                }
                OledControl(
                    tr("Refresh"),
                    compact = touch,
                    modifier = if (mediaFilter != null || sort != HomeServerLibrarySort.RECENTLY_ADDED) Modifier.weight(1f) else Modifier.fillMaxWidth(),
                    onClick = { if(serverMode) viewModel.refreshLibrary() else viewModel.refresh() }
                )
            }
        }
    }
}

@Composable
internal fun OledControl(
    label: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    compact: Boolean = false,
    icon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    hasActiveBadge: Boolean = false,
    maxLines: Int = 1,
    onClick: () -> Unit
) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(Color.White)
    // Phone/tablet (compact): selected tab is a white pill with black text.
    // TV keeps the elevated dark treatment; focus uses the accent color.
    val isPhoneSelected = selected && compact
    val foreground = if(focused) { if(accent.luminance() > .4f) Color.Black else Color.White } else if(isPhoneSelected) Color.Black else Color.White
    val haptic = LocalHapticFeedback.current
    val background by animateColorAsState(if(focused) accent else if(isPhoneSelected) Color.White else if(selected) BackgroundElevated else Color.Transparent, tween(120), label = "library-control")
    Box(
        modifier
            .onFocusChanged { focused = it.isFocused }
            .clip(if(compact) CircleShape else RoundedCornerShape(8.dp))
            .background(background)
            .clickable {
                if (compact && !selected) {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                }
                onClick()
            }
            .padding(horizontal = if(compact) 10.dp else 12.dp, vertical = if(compact) 8.dp else 11.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if(label == "⌕") {
            Icon(Icons.Outlined.Search, contentDescription = tr("Search library"), tint = foreground, modifier = Modifier.size(20.dp))
        } else {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if(icon != null) {
                    Icon(icon, contentDescription = null, tint = foreground, modifier = Modifier.size(18.dp))
                }
                Text(
                    label,
                    color = foreground,
                    fontSize = if(compact) 13.sp else 14.sp,
                    fontWeight = if(selected || focused) FontWeight.SemiBold else FontWeight.Normal,
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis
                )
                if (hasActiveBadge) {
                    Box(
                        Modifier
                            .size(6.dp)
                            .background(if (isPhoneSelected) Color.Black else accent, CircleShape)
                    )
                }
                if (trailingIcon != null) {
                    Icon(trailingIcon, contentDescription = null, tint = foreground, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
private fun OledSources(
    sources: List<WatchlistSourceItem>,
    selectedId: String,
    modifier: Modifier,
    select: (WatchlistSourceItem) -> Unit,
    settings: (String) -> Unit,
    inDrawer: Boolean = false
) {
    val touch = LocalDeviceType.current.isTouchDevice()
    val groups = sources.groupBy { when(it) {
        is WatchlistSourceItem.MyWatchlist -> tr("Saved")
        is WatchlistSourceItem.HomeServer -> it.candidate.serverName
        else -> it.subtitle ?: tr("Lists")
    } }
    val header: @Composable (String, WatchlistSourceItem?) -> Unit = { group, first ->
        Column(Modifier.padding(top = if (touch) 10.dp else 24.dp, bottom = 6.dp, start = 8.dp)) {
            val provider = (first as? WatchlistSourceItem.TrackerList)?.provider
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                when (provider) {
                    TrackerLibraryProvider.TRAKT -> Icon(painterResource(R.drawable.ic_trakt), contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
                    TrackerLibraryProvider.SIMKL -> Icon(painterResource(R.drawable.ic_simkl), contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
                    TrackerLibraryProvider.MDBLIST -> Icon(painterResource(R.drawable.ic_mdblist), contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
                    else -> Unit
                }
                Text(
                    group.uppercase(),
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = if (provider != null) 9.sp else 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp,
                    maxLines = 1
                )
            }
            (first as? WatchlistSourceItem.HomeServer)?.let { Text(it.candidate.serverKind.name.lowercase().replaceFirstChar(Char::titlecase), color = Color.Gray, fontSize = 11.sp) }
        }
    }
    val row: @Composable (WatchlistSourceItem) -> Unit = { source ->
        OledControl(
            label = tr(source.title),
            selected = source.id == selectedId,
            compact = touch,
            trailingIcon = if (source.id == selectedId) Icons.Outlined.Check else null,
            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp).testTag("library-source-${source.id}"),
            icon = (source as? WatchlistSourceItem.HomeServer)?.let { if(it.candidate.collectionType.contains("movie", true)) Icons.Outlined.Movie else Icons.Outlined.Tv },
            onClick = { select(source) }
        )
    }
    val showConnect = sources.isEmpty() || sources.any { it is WatchlistSourceItem.HomeServer }
    val connect: @Composable () -> Unit = {
        OledControl(
            label = "+ ${tr("Connect server")}",
            compact = touch,
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            maxLines = 2,
            onClick = { settings("home_server") }
        )
    }
    if (inDrawer) {
        // The drawer scrolls the header and the sources together.
        Column(modifier.testTag("library-sources"), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            groups.forEach { (group, entries) ->
                header(group, entries.firstOrNull())
                entries.forEach { source -> key(source.id) { row(source) } }
            }
            if (showConnect) connect()
        }
    } else {
        LazyColumn(modifier.testTag("library-sources"), verticalArrangement = Arrangement.spacedBy(4.dp), contentPadding = PaddingValues(bottom = 24.dp)) {
            groups.forEach { (group, entries) ->
                item("group:$group") { header(group, entries.firstOrNull()) }
                items(entries, key = { it.id }) { row(it) }
            }
            if (showConnect) item("connect") { connect() }
        }
    }
}

@Composable
private fun OledCollection(source: WatchlistSourceItem, cover: String?, width: Dp, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(Color.White)
    Column(Modifier.width(width).onFocusChanged { focused = it.isFocused }.clip(RoundedCornerShape(8.dp)).clickable(onClick = onClick)) {
        Box(Modifier.fillMaxWidth().padding(top = 5.dp)) {
            Box(Modifier.padding(horizontal = 10.dp).offset(y = (-5).dp).fillMaxWidth().height(8.dp).background(BackgroundElevated, RoundedCornerShape(8.dp)))
            AsyncImage(cover, source.title, modifier = Modifier.fillMaxWidth().aspectRatio(2.2f).clip(RoundedCornerShape(8.dp)).background(BackgroundCard), contentScale = ContentScale.Crop)
        }
        Column(Modifier.fillMaxWidth().background(if(focused) accent else Color.Black).padding(10.dp)) {
            val ink = if(focused && accent.luminance() > .4f) Color.Black else Color.White
            Text(source.title, color = ink, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(source.subtitle ?: tr("Personal list"), color = ink.copy(alpha = .7f), fontSize = 13.sp, maxLines = 1)
        }
    }
}

@Composable private fun OledMessage(title: String, detail: String) {
    Column(Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, color = Color.White, fontSize = 19.sp)
        Text(detail, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
private fun OledDrawer(title: String, close: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    val initial = remember { FocusRequester() }
    val touch = LocalDeviceType.current.isTouchDevice()
    Dialog(
        onDismissRequest = close,
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .testTag("library-drawer-root")
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.65f))
                    .pointerInput(close) {
                        detectTapGestures { close() }
                    }
            )
            Column(
                Modifier
                    .then(
                        if (touch) {
                            Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .heightIn(max = 560.dp)
                                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                                .background(BackgroundDark)
                                .pointerInput(Unit) {
                                    detectTapGestures { }
                                }
                                .padding(horizontal = 20.dp, vertical = 14.dp)
                                .navigationBarsPadding()
                        } else {
                            Modifier
                                .align(Alignment.CenterEnd)
                                .fillMaxHeight()
                                .widthIn(max = 340.dp)
                                .fillMaxWidth()
                                .background(BackgroundDark)
                                .pointerInput(Unit) {
                                    detectTapGestures { }
                                }
                                .padding(24.dp)
                        }
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                if (touch) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier
                                .width(36.dp)
                                .height(4.dp)
                                .background(Color.White.copy(alpha = 0.35f), CircleShape)
                        )
                    }
                }
                Row(
                    Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        title,
                        color = Color.White,
                        fontSize = if (touch) 20.sp else 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = if (touch) 4.dp else 12.dp)
                    )
                    if (touch) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.12f))
                                .testTag("library-drawer-close")
                                .clickable(onClick = close),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = tr("Close"),
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    } else {
                        OledControl(
                            tr("Close"),
                            icon = Icons.Default.Close,
                            modifier = Modifier.testTag("library-drawer-close").focusRequester(initial),
                            onClick = close
                        )
                    }
                }
                content()
            }
        }
        LaunchedEffect(Unit) { if (!touch) initial.requestFocus() }
    }
}
