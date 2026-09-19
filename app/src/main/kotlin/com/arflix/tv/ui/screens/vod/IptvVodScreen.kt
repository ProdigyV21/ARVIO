package com.arflix.tv.ui.screens.vod

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.arflix.tv.R
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.repository.IptvRepository.IptvVodBrowseItem
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.TextInputModal
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.components.topBarSelectedIndex
import com.arflix.tv.ui.screens.watchlist.OledControl
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.util.LocalDeviceType

/**
 * Browses the IPTV provider's own VOD catalogue (movies / series in the provider's
 * categories). Reached from the "VOD" entry next to TV in the top bar.
 */
@Composable
fun IptvVodScreen(
    viewModel: IptvVodViewModel = hiltViewModel(),
    currentProfile: Profile? = null,
    onNavigateToDetails: (MediaType, Int) -> Unit = { _, _ -> },
    onPlayDirect: (mediaId: Int, streamUrl: String, title: String) -> Unit = { _, _, _ -> },
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: (String) -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val touch = LocalDeviceType.current.isTouchDevice()
    val hasProfile = currentProfile != null
    var topFocused by remember { mutableStateOf(false) }
    var topIndex by remember { mutableIntStateOf(topBarSelectedIndex(SidebarItem.VOD, hasProfile).coerceAtLeast(0)) }
    val topFocus = remember { FocusRequester() }
    val firstTab = remember { FocusRequester() }
    var initialFocusPlaced by remember { mutableStateOf(false) }
    var searchOpen by remember { mutableStateOf(false) }
    val grid = rememberLazyGridState()

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is IptvVodEvent.OpenDetails -> onNavigateToDetails(event.mediaType, event.tmdbId)
                is IptvVodEvent.PlayDirect -> onPlayDirect(event.mediaId, event.streamUrl, event.title)
            }
        }
    }
    LaunchedEffect(state.selectedCategoryId, state.isSeries, state.query) { grid.scrollToItem(0) }

    BackHandler(!searchOpen) {
        when {
            state.query.isNotEmpty() -> viewModel.setQuery("")
            !touch && !topFocused -> topFocus.requestFocus()
            else -> onBack()
        }
    }

    val allLabel = stringResource(R.string.vod_all)
    val recentLabel = stringResource(R.string.vod_recent)
    val categoryEntries = remember(state.categories, state.categoryCounts, allLabel, recentLabel) {
        buildList {
            add(Triple(VOD_CATEGORY_ALL, allLabel, state.categoryCounts.values.sum()))
            add(Triple(VOD_CATEGORY_RECENT, recentLabel, null))
            state.categories.forEach { add(Triple(it.id, it.name, state.categoryCounts[it.id])) }
        }
    }

    BoxWithConstraints(Modifier.fillMaxSize().background(Color.Black).testTag("iptv-vod")) {
        val compact = touch && maxWidth < 600.dp
        Column(Modifier.fillMaxSize().padding(horizontal = if (compact) 16.dp else 26.dp)) {
            if (!touch) {
                Box(
                    Modifier.fillMaxWidth().height(60.dp).focusRequester(topFocus)
                        .onFocusChanged { topFocused = it.isFocused }
                        .onKeyEvent { event ->
                            if (event.type != KeyEventType.KeyDown) false else when (event.key) {
                                Key.DirectionLeft -> { topIndex = (topIndex - 1).coerceAtLeast(0); true }
                                Key.DirectionRight -> { topIndex = (topIndex + 1).coerceAtMost(topBarMaxIndex(hasProfile)); true }
                                Key.DirectionDown -> { firstTab.requestFocus(); true }
                                Key.Enter, Key.DirectionCenter, Key.NumPadEnter -> {
                                    when (topBarFocusedItem(topIndex, hasProfile)) {
                                        SidebarItem.SEARCH -> onNavigateToSearch()
                                        SidebarItem.HOME -> onNavigateToHome()
                                        SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                        SidebarItem.TV -> onNavigateToTv()
                                        SidebarItem.VOD -> firstTab.requestFocus()
                                        SidebarItem.SETTINGS -> onNavigateToSettings("general")
                                        null -> onSwitchProfile()
                                    }
                                    true
                                }
                                else -> false
                            }
                        }
                        .focusable()
                ) {
                    AppTopBar(SidebarItem.VOD, topFocused, topIndex, profile = currentProfile, modifier = Modifier.offset(y = (-6).dp))
                }
            } else {
                Spacer(Modifier.height(12.dp))
            }

            // Tabs + actions
            Row(
                Modifier.fillMaxWidth().padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OledControl(
                    stringResource(R.string.vod_movies),
                    selected = !state.isSeries,
                    modifier = Modifier.focusRequester(firstTab).onGloballyPositioned {
                        if (!touch && !initialFocusPlaced) { initialFocusPlaced = true; firstTab.requestFocus() }
                    },
                    onClick = { viewModel.selectTab(series = false) }
                )
                OledControl(
                    stringResource(R.string.vod_series),
                    selected = state.isSeries,
                    onClick = { viewModel.selectTab(series = true) }
                )
                Spacer(Modifier.weight(1f))
                if (!compact && state.hasSource && !state.isLoading) {
                    Text(
                        stringResource(R.string.vod_titles_count, state.items.size),
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )
                }
                if (state.query.isNotEmpty()) {
                    OledControl("× ${state.query}", onClick = { viewModel.setQuery("") })
                }
                OledControl("⌕", onClick = { searchOpen = true })
                if (!compact) OledControl(stringResource(R.string.vod_refresh), onClick = { viewModel.refresh() })
            }

            when {
                !state.hasSource -> VodMessage(
                    title = stringResource(R.string.vod_no_source_title),
                    detail = stringResource(R.string.vod_no_source_desc),
                    action = stringResource(R.string.vod_open_settings),
                    onAction = { onNavigateToSettings("iptv") }
                )
                state.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(color = resolveAccentColor(Color.White))
                        Text(
                            stringResource(R.string.vod_loading),
                            color = Color.Gray,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }
                state.error != null -> VodMessage(
                    title = stringResource(R.string.vod_load_failed),
                    detail = state.error.orEmpty(),
                    action = stringResource(R.string.vod_retry),
                    onAction = { viewModel.refresh() }
                )
                else -> {
                    if (compact) {
                        LazyRow(
                            Modifier.fillMaxWidth().padding(bottom = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            items(categoryEntries, key = { it.first }) { (id, name, _) ->
                                OledControl(name, selected = state.selectedCategoryId == id, compact = true,
                                    onClick = { viewModel.selectCategory(id) })
                            }
                        }
                    }
                    Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                        if (!compact) {
                            LazyColumn(
                                Modifier.width(if (touch) 200.dp else 230.dp).fillMaxHeight().testTag("iptv-vod-categories"),
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                                contentPadding = PaddingValues(bottom = 24.dp + LocalBottomBarInset.current)
                            ) {
                                items(categoryEntries, key = { it.first }) { (id, name, count) ->
                                    OledControl(
                                        if (count != null) "$name  ·  $count" else name,
                                        selected = state.selectedCategoryId == id,
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = { viewModel.selectCategory(id) }
                                    )
                                }
                            }
                        }
                        BoxWithConstraints(Modifier.weight(1f).fillMaxHeight()) {
                            val columns = (maxWidth.value / if (touch) 110f else 150f).toInt().coerceIn(2, 9)
                            if (state.items.isEmpty()) {
                                Text(
                                    stringResource(R.string.vod_empty),
                                    color = Color.Gray,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            } else {
                                LazyVerticalGrid(
                                    GridCells.Fixed(columns),
                                    state = grid,
                                    modifier = Modifier.fillMaxSize().testTag("iptv-vod-grid"),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp),
                                    contentPadding = PaddingValues(top = 6.dp, bottom = 16.dp + LocalBottomBarInset.current)
                                ) {
                                    items(state.items, key = { it.key }) { item ->
                                        VodPosterCard(
                                            item = item,
                                            isResolving = state.resolvingKey == item.key,
                                            onClick = { viewModel.open(item) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        state.message?.let { name ->
            Toast(
                message = stringResource(R.string.vod_not_matched, name),
                type = ToastType.INFO,
                isVisible = true,
                onDismiss = viewModel::dismissMessage
            )
        }
    }

    TextInputModal(
        isVisible = searchOpen,
        title = stringResource(R.string.vod_search),
        initialValue = state.query,
        onConfirm = { viewModel.setQuery(it); searchOpen = false },
        onCancel = { searchOpen = false }
    )
}

@Composable
private fun VodPosterCard(item: IptvVodBrowseItem, isResolving: Boolean, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(Color.White)
    val borderColor by animateColorAsState(if (focused) accent else Color.Transparent, tween(120), label = "vod-card-border")
    Column(
        Modifier
            .fillMaxWidth()
            .onFocusChanged { focused = it.isFocused }
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .testTag("iptv-vod-card-${item.key}")
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, borderColor, RoundedCornerShape(8.dp))
                .background(Color(0xFF151518))
        ) {
            // Fallback title stays underneath, visible when the provider has no artwork.
            Text(
                item.name,
                color = Color.Gray,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.Center).padding(8.dp)
            )
            if (item.posterUrl != null) {
                AsyncImage(
                    model = item.posterUrl,
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            item.rating?.let { rating ->
                Text(
                    "★ $rating",
                    color = Color.White,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(5.dp)
                        .background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            if (isResolving) {
                Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.55f)), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(Modifier.size(28.dp), color = accent)
                }
            }
        }
        val ink = if (focused && accent.luminance() > .4f) Color.White else Color.White
        Text(
            item.name,
            color = ink,
            fontSize = 13.sp,
            fontWeight = if (focused) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 6.dp, start = 2.dp, end = 2.dp)
        )
        item.year?.let {
            Text(it.toString(), color = Color.Gray, fontSize = 11.sp, modifier = Modifier.padding(start = 2.dp))
        }
    }
}

@Composable
private fun VodMessage(title: String, detail: String, action: String, onAction: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.widthIn(max = 520.dp)) {
            Text(title, color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)
            Text(
                detail,
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
            OledControl(action, onClick = onAction)
        }
    }
}
