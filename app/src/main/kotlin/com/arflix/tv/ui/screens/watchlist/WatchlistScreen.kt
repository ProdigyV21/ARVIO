package com.arflix.tv.ui.screens.watchlist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.arflix.tv.R
import androidx.compose.ui.text.font.FontWeight
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

internal enum class LibrarySection(val label: String) { WATCHLISTS("Watchlists"), LISTS("My lists"), SERVERS("Homeserver") }
internal fun libraryColumns(width: Int, poster: Boolean, collections: Boolean = false): Int =
    if (collections) (width / 270).coerceIn(1, 3) else if (poster) (width / 115).coerceIn(2, 8) else (width / 180).coerceIn(2, 4)
internal fun WatchlistSourceItem.isPersonalCollection(): Boolean = this is WatchlistSourceItem.Catalog ||
    (this is WatchlistSourceItem.TrackerList && provider == TrackerLibraryProvider.TRAKT && listKey !in setOf("__watchlist__", "watchlist", "collection", "watched"))
internal fun librarySources(sources: List<WatchlistSourceItem>, section: LibrarySection): List<WatchlistSourceItem> =
    sources.filter { when (section) {
        LibrarySection.WATCHLISTS -> it is WatchlistSourceItem.MyWatchlist || (it is WatchlistSourceItem.TrackerList && !it.isPersonalCollection())
        LibrarySection.LISTS -> it.isPersonalCollection()
        LibrarySection.SERVERS -> it is WatchlistSourceItem.HomeServer
    } }

@OptIn(androidx.tv.material3.ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel = hiltViewModel(),
    currentProfile: Profile? = null,
    onNavigateToDetails: (MediaType, Int) -> Unit = { _, _ -> },
    onNavigateToHome: () -> Unit = {}, onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {}, onNavigateToVod: () -> Unit = {}, onNavigateToSettings: (String) -> Unit = {},
    onSwitchProfile: () -> Unit = {}, onBack: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val servers by viewModel.libraryState.collectAsStateWithLifecycle()
    val logos by viewModel.logoUrls.collectAsStateWithLifecycle()
    val touch = LocalDeviceType.current.isTouchDevice()
    val poster = rememberCardLayoutMode() == CardLayoutMode.POSTER
    val scrollScope = rememberCoroutineScope()
    var section by rememberSaveable { mutableStateOf(LibrarySection.WATCHLISTS) }
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
    fun selectSource(source: WatchlistSourceItem) {
        if (source is WatchlistSourceItem.HomeServer) {
            viewModel.selectLibraryProvider(source.candidate.serverKind)
            viewModel.selectLibrary(source.candidate.sourceRef)
        } else viewModel.selectSource(source.id)
        sourcesOpen = false
    }
    fun selectSection(next: LibrarySection) {
        section = next; openedList = null; query = ""; mediaFilter = null
        if (next == LibrarySection.WATCHLISTS) viewModel.selectSource(WatchlistSourceItem.MyWatchlist.id)
        if (next == LibrarySection.SERVERS && servers.selectedSourceRef == null) {
            servers.libraries.firstOrNull()?.let { viewModel.selectLibraryProvider(it.serverKind); viewModel.selectLibrary(it.sourceRef) }
        }
    }
    BackHandler(!search && !filters && !sourcesOpen) {
        when { openedList != null -> openedList = null; !touch && !topFocused -> topFocus.requestFocus(); else -> onBack() }
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
            if (!collections && error == null && !loading && !loadingMore && hasMore && (items.isEmpty() || last >= items.size - 16)) {
                if (serverMode) viewModel.loadMoreLibrary() else viewModel.loadMoreActiveSource()
            }
        }
    }
    LaunchedEffect(grid, sourceKey, items, poster) {
        if (!poster && !collections) snapshotFlow { grid.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }.collect { last ->
            val first = grid.firstVisibleItemIndex
            viewModel.prefetchLogos(items.subList(first.coerceAtMost(items.size), (last + 17).coerceAtMost(items.size)))
        }
    }
    BoxWithConstraints(Modifier.fillMaxSize().background(Color.Black).testTag("oled-library")) {
        val compact = touch && maxWidth < 600.dp
        val sideWidth = if (touch) 160.dp else 126.dp
        Column(Modifier.fillMaxSize().padding(horizontal = if (compact) 16.dp else 26.dp)) {
            if (!touch) {
                Box(Modifier.fillMaxWidth().height(60.dp).focusRequester(topFocus).onFocusChanged { topFocused = it.isFocused }
                    .onKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) false else when(event.key) {
                            Key.DirectionLeft -> { topIndex = (topIndex - 1).coerceAtLeast(0); true }
                            Key.DirectionRight -> { topIndex = (topIndex + 1).coerceAtMost(topBarMaxIndex(currentProfile != null)); true }
                            Key.DirectionDown -> { firstTab.requestFocus(); true }
                            Key.Enter, Key.DirectionCenter, Key.NumPadEnter -> {
                                when(topBarFocusedItem(topIndex, currentProfile != null)) {
                                    SidebarItem.HOME -> onNavigateToHome(); SidebarItem.SEARCH -> onNavigateToSearch()
                                    SidebarItem.TV -> onNavigateToTv(); SidebarItem.VOD -> onNavigateToVod(); SidebarItem.SETTINGS -> onNavigateToSettings("general")
                                    SidebarItem.WATCHLIST -> firstTab.requestFocus(); null -> onSwitchProfile()
                                }; true
                            }
                            else -> false
                        }
                    }.focusable()) {
                    AppTopBar(SidebarItem.WATCHLIST, topFocused, topIndex, profile = currentProfile, modifier = Modifier.offset(y = (-6).dp))
                }
            } else Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth().padding(bottom = if(compact) 10.dp else 6.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(if (compact) 4.dp else 8.dp)) {
                LibrarySection.entries.forEachIndexed { index, entry ->
                    OledControl(tr(entry.label), selected = section == entry,
                        modifier = (if(index == 0) Modifier.focusRequester(firstTab).onGloballyPositioned {
                            if(!touch && !initialFocusPlaced) { initialFocusPlaced = true; firstTab.requestFocus() }
                        } else Modifier)
                            .then(if(compact) Modifier.weight(1f) else Modifier), compact = compact,
                        onClick = { selectSection(entry) })
                }
                if (!compact) {
                    Spacer(Modifier.weight(1f))
                    val serverTotal = servers.totalCount.takeIf { serverMode && mediaFilter == null }
                    Text(if(collections) "${scopeSources.size} ${tr("lists")}" else "${serverTotal ?: items.size}${if(serverTotal == null && hasMore) "+" else ""} ${tr("titles")}", color = Color.LightGray, fontSize = 13.sp)
                    OledControl("⌕", onClick = { sourcesOpen = false; filters = false; search = true })
                    if(collections) OledControl("+ " + tr("New list"), onClick = { onNavigateToSettings("catalogs") })
                    OledControl(tr("Filters"), modifier = Modifier.focusRequester(filterButton), onClick = { sourcesOpen = false; filters = true })
                }
            }
            if (compact) Row(Modifier.fillMaxWidth().padding(bottom = 10.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                if (!collections) OledControl(scopeSources.firstOrNull { it.id == selectedId }?.title ?: tr("Choose library"),
                    modifier = Modifier.weight(1f).testTag("library-source-picker"), onClick = { sourcesOpen = true })
                else OledControl("+ " + tr("New list"), modifier = Modifier.weight(1f), onClick = { onNavigateToSettings("catalogs") })
                OledControl("⌕", onClick = { sourcesOpen = false; filters = false; search = true })
                OledControl(tr("Filters"), modifier = Modifier.focusRequester(filterButton), onClick = { sourcesOpen = false; filters = true })
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
                    if (collections) {
                        val lists = scopeSources.filter { it.title.contains(query, true) }
                        if(lists.isEmpty()) OledMessage(tr("No lists yet"), tr("Your custom catalogs and personal lists appear here."))
                        LazyVerticalGrid(GridCells.Fixed(columns), state = grid, modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(18.dp),
                            contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp + LocalBottomBarInset.current)) {
                            items(lists, key = { it.id }) { source ->
                                var cover by remember(source) { mutableStateOf<String?>((source as? WatchlistSourceItem.Catalog)?.config?.collectionCoverImageUrl) }
                                LaunchedEffect(source) { cover = viewModel.collectionCover(source) }
                                OledCollection(source, cover, width) { openedList = source.id; viewModel.selectSource(source.id) }
                            }
                        }
                    } else if (loading && items.isEmpty()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center), color = resolveAccentColor(Color.White))
                    } else if (items.isEmpty()) {
                        Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                            OledMessage(if(error != null) tr("Library unavailable") else tr("No titles found"), error ?: tr("Choose a source or add titles to your watchlist."))
                            if(error != null) OledControl(tr("Retry"), onClick = { if(serverMode) viewModel.refreshLibrary() else viewModel.refresh() })
                        }
                    } else LazyVerticalGrid(GridCells.Fixed(columns), state = grid, modifier = Modifier.fillMaxSize().testTag("library-grid"),
                        horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(18.dp),
                        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp + LocalBottomBarInset.current)) {
                        itemsIndexed(items, key = { index, item -> watchlistItemKey(item, index) }) { index, item ->
                            val reveal = remember { BringIntoViewRequester() }
                            LaunchedEffect(watchlistLogoKey(item), poster) { if(!poster) viewModel.ensureLogo(item) }
                            Box(Modifier.bringIntoViewRequester(reveal).padding(6.dp).testTag("library-card-frame-$index")) {
                            MediaCard(item, width = width - 12.dp, isLandscape = !poster, logoImageUrl = logos[watchlistLogoKey(item)],
                                focusedScale = 1.025f, titleMaxLines = 1, showTitle = true,
                                modifier = Modifier.testTag("library-card-$index"),
                                onFocused = { viewModel.saveFocusState(0, index); if(!touch) scrollScope.launch { reveal.bringIntoView() } },
                                onClick = { onNavigateToDetails(item.mediaType, item.id) },
                                onLongClick = if(state.selectedSourceId == WatchlistSourceItem.MyWatchlist.id && !serverMode) ({ viewModel.removeFromWatchlist(item) }) else null)
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
        state.toastMessage?.let { message ->
            Toast(message = message, isVisible = true, onDismiss = viewModel::dismissToast)
        }
    }
    if(sourcesOpen) OledDrawer(tr("Sources"), { sourcesOpen = false }) {
        OledSources(scopeSources, selectedId, Modifier.fillMaxWidth().heightIn(max = 500.dp), ::selectSource, onNavigateToSettings)
    }
    if(filters) OledDrawer(tr("Filters"), { filters = false; if(!touch) filterButton.requestFocus() }) {
        Text(tr("Sort"), color = Color.LightGray, modifier = Modifier.padding(vertical = 12.dp))
        listOf("Recently added" to HomeServerLibrarySort.RECENTLY_ADDED, "Title A-Z" to HomeServerLibrarySort.TITLE,
            "Highest rated" to HomeServerLibrarySort.RATING, "Newest release" to HomeServerLibrarySort.RELEASE_DATE_NEWEST,
            "Oldest release" to HomeServerLibrarySort.RELEASE_DATE_OLDEST).forEach { (label, value) ->
            OledControl(tr(label), selected = sort == value, modifier = Modifier.fillMaxWidth(), onClick = { sort = value })
        }
        if(!collections) {
            Text(tr("Type"), color = Color.LightGray, modifier = Modifier.padding(vertical = 12.dp))
            listOf("All" to null, "Movies" to MediaType.MOVIE, "Series" to MediaType.TV).forEach { (label, value) ->
                OledControl(tr(label), selected = mediaFilter == value, modifier = Modifier.fillMaxWidth(), onClick = { mediaFilter = value })
            }
            OledControl(tr("Refresh"), onClick = { if(serverMode) viewModel.refreshLibrary() else viewModel.refresh() })
        }
    }
    TextInputModal(search, tr("Search library"), initialValue = query,
        onConfirm = { query = it.trim(); search = false }, onCancel = { search = false })
}

@Composable
internal fun OledControl(label: String, modifier: Modifier = Modifier, selected: Boolean = false, compact: Boolean = false, icon: ImageVector? = null, maxLines: Int = 1, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(Color.White)
    val foreground = if(focused) { if(accent.luminance() > .4f) Color.Black else Color.White } else Color.White
    val background by animateColorAsState(if(focused) accent else if(selected) Color(0xFF242426) else Color.Transparent, tween(120), label = "library-control")
    Box(modifier.onFocusChanged { focused = it.isFocused }.clip(RoundedCornerShape(7.dp))
        .background(background)
        .clickable(onClick = onClick).padding(horizontal = if(compact) 8.dp else 12.dp, vertical = 11.dp), contentAlignment = Alignment.CenterStart) {
        if(label == "⌕") Icon(Icons.Outlined.Search, contentDescription = tr("Search library"), tint = foreground, modifier = Modifier.size(20.dp))
        else Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
        if(icon != null) Icon(icon, contentDescription = null, tint = foreground, modifier = Modifier.size(20.dp))
        Text(label, color = foreground, fontSize = if(compact) 13.sp else 14.sp, fontWeight = if(selected || focused) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = maxLines, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun OledSources(sources: List<WatchlistSourceItem>, selectedId: String, modifier: Modifier,
    select: (WatchlistSourceItem) -> Unit, settings: (String) -> Unit) {
    val groups = sources.groupBy { when(it) {
        is WatchlistSourceItem.MyWatchlist -> tr("Saved")
        is WatchlistSourceItem.HomeServer -> it.candidate.serverName
        else -> it.subtitle ?: tr("Lists")
    } }
    LazyColumn(modifier.testTag("library-sources"), verticalArrangement = Arrangement.spacedBy(4.dp), contentPadding = PaddingValues(bottom = 24.dp)) {
        groups.forEach { (group, entries) ->
            item("group:$group") {
                Column(Modifier.padding(top = 24.dp, bottom = 10.dp, start = 10.dp)) {
                    val provider = (entries.firstOrNull() as? WatchlistSourceItem.TrackerList)?.provider
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        when (provider) {
                            TrackerLibraryProvider.TRAKT -> Icon(painterResource(R.drawable.ic_trakt), contentDescription = null, tint = Color.White, modifier = Modifier.size(11.dp))
                            TrackerLibraryProvider.SIMKL -> Icon(painterResource(R.drawable.ic_simkl), contentDescription = null, tint = Color.White, modifier = Modifier.size(11.dp))
                            else -> Unit
                        }
                        Text(group.uppercase(), color = Color.White, fontSize = if (provider != null) 8.sp else 13.sp, fontWeight = FontWeight.Bold, letterSpacing = if (provider != null) .3.sp else .6.sp, maxLines = 1)
                    }
                    (entries.firstOrNull() as? WatchlistSourceItem.HomeServer)?.let { Text(it.candidate.serverKind.name.lowercase().replaceFirstChar(Char::titlecase), color = Color.Gray, fontSize = 11.sp) }
                }
            }
            items(entries, key = { it.id }) { source -> OledControl(tr(source.title), selected = source.id == selectedId, modifier = Modifier.fillMaxWidth().testTag("library-source-${source.id}"), icon = (source as? WatchlistSourceItem.HomeServer)?.let { if(it.candidate.collectionType.contains("movie", true)) Icons.Outlined.Movie else Icons.Outlined.Tv }, onClick = { select(source) }) }
        }
        if(sources.isEmpty() || sources.any { it is WatchlistSourceItem.HomeServer }) item("connect") {
            OledControl("+ ${tr("Connect server")}", modifier = Modifier.padding(top = 20.dp), maxLines = 2, onClick = { settings("home_server") })
        }
    }
}

@Composable
private fun OledCollection(source: WatchlistSourceItem, cover: String?, width: Dp, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(Color.White)
    Column(Modifier.width(width).onFocusChanged { focused = it.isFocused }.clip(RoundedCornerShape(6.dp)).clickable(onClick = onClick)) {
        Box(Modifier.fillMaxWidth().padding(top = 5.dp)) {
            Box(Modifier.padding(horizontal = 10.dp).offset(y = (-5).dp).fillMaxWidth().height(8.dp).background(Color(0xFF252528), RoundedCornerShape(5.dp)))
            AsyncImage(cover, source.title, modifier = Modifier.fillMaxWidth().aspectRatio(2.2f).clip(RoundedCornerShape(6.dp)).background(Color(0xFF101012)), contentScale = ContentScale.Crop)
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
    Dialog(onDismissRequest = close, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Box(Modifier.fillMaxSize().testTag("library-drawer-root").background(Color.Black.copy(alpha = .55f))) {
            Column(Modifier.align(Alignment.CenterEnd).fillMaxHeight().widthIn(max = 340.dp).fillMaxWidth()
                .background(Color(0xFF0C0C0E)).padding(24.dp).verticalScroll(rememberScrollState())) {
                OledControl("${tr("Close")} ×", modifier = Modifier.focusRequester(initial), onClick = close)
                Text(title, color = Color.White, fontSize = 24.sp, modifier = Modifier.padding(vertical = 12.dp))
                content()
            }
        }
        LaunchedEffect(Unit) { initial.requestFocus() }
    }
}
