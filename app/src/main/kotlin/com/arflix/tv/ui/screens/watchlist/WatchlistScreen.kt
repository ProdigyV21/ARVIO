package com.arflix.tv.ui.screens.watchlist

import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.arflix.tv.R
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
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
import kotlinx.coroutines.delay

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onNavigateToDetails: (MediaType, Int) -> Unit = { _, _ -> },
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val logoUrls by viewModel.logoUrls.collectAsStateWithLifecycle()
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val usePosterCards = rememberCardLayoutMode() == CardLayoutMode.POSTER
    val cardWidth: Dp = if (usePosterCards) {
        if (isMobile) 120.dp else 110.dp
    } else {
        if (isMobile) 200.dp else 230.dp
    }
    var isSidebarFocused by remember { mutableStateOf(false) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 3 else 2) }
    val rootFocusRequester = remember { FocusRequester() }
    var focusedSectionIndex by remember { mutableIntStateOf(0) }
    var focusedItemIndex by remember { mutableIntStateOf(0) }
    var enterKeyDownTimeMs by remember { mutableLongStateOf(-1L) }
    val longPressThresholdMs = 500L
    val lazyColumnState = rememberLazyListState()

    val sections = listOf(
        Pair("movies", uiState.movies),
        Pair("series", uiState.series)
    ).filter { it.second.isNotEmpty() }

    val getFocusedItem: () -> MediaItem? = {
        sections.getOrNull(focusedSectionIndex)?.second?.getOrNull(focusedItemIndex)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) viewModel.refresh()
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(Unit) {
        rootFocusRequester.requestFocus()
    }

    LaunchedEffect(sections.size, uiState.movies.size, uiState.series.size) {
        if (sections.isNotEmpty() && !isSidebarFocused) {
            if (focusedSectionIndex >= sections.size) {
                focusedSectionIndex = 0
                focusedItemIndex = 0
            }
        }
    }

    LaunchedEffect(sections.size) {
        if (sections.isNotEmpty()) lazyColumnState.scrollToItem(0)
    }

    LaunchedEffect(focusedSectionIndex, sections.size) {
        if (!isSidebarFocused && sections.isNotEmpty() && focusedSectionIndex < sections.size) {
            lazyColumnState.animateScrollToItem(focusedSectionIndex)
        }
    }

    val totalItems = uiState.movies.size + uiState.series.size
    LaunchedEffect(uiState.isLoading, totalItems) {
        if (!uiState.isLoading && totalItems == 0) {
            isSidebarFocused = true
            sidebarFocusIndex = if (hasProfile) 3 else SidebarItem.WATCHLIST.ordinal
        } else if (!uiState.isLoading && totalItems > 0 && !isSidebarFocused) {
            delay(80)
            runCatching { rootFocusRequester.requestFocus() }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            .focusRequester(rootFocusRequester)
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            if (isSidebarFocused) onBack() else isSidebarFocused = true
                            true
                        }
                        Key.DirectionLeft -> {
                            if (!isSidebarFocused) {
                                if (focusedItemIndex > 0) {
                                    focusedItemIndex--
                                } else {
                                    isSidebarFocused = true
                                }
                            } else if (sidebarFocusIndex > 0) {
                                sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                            }
                            true
                        }
                        Key.DirectionRight -> {
                            if (!isSidebarFocused) {
                                val currentSection = sections.getOrNull(focusedSectionIndex)
                                if (currentSection != null && focusedItemIndex < currentSection.second.size - 1) {
                                    focusedItemIndex++
                                }
                            } else if (sidebarFocusIndex < maxSidebarIndex) {
                                sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                            }
                            true
                        }
                        Key.DirectionUp -> {
                            if (isSidebarFocused) {
                                // stay in sidebar
                            } else if (focusedSectionIndex > 0) {
                                focusedSectionIndex--
                                focusedItemIndex = 0
                            } else {
                                isSidebarFocused = true
                            }
                            true
                        }
                        Key.DirectionDown -> {
                            if (isSidebarFocused) {
                                if (totalItems > 0) {
                                    isSidebarFocused = false
                                    focusedSectionIndex = 0
                                    focusedItemIndex = 0
                                }
                            } else if (focusedSectionIndex < sections.size - 1) {
                                focusedSectionIndex++
                                focusedItemIndex = 0
                            }
                            true
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            if (isSidebarFocused) {
                                if (hasProfile && sidebarFocusIndex == 0) {
                                    onSwitchProfile()
                                } else {
                                    when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) {
                                        SidebarItem.SEARCH -> onNavigateToSearch()
                                        SidebarItem.HOME -> onNavigateToHome()
                                        SidebarItem.WATCHLIST -> {}
                                        SidebarItem.TV -> onNavigateToTv()
                                        SidebarItem.SETTINGS -> onNavigateToSettings()
                                        null -> Unit
                                    }
                                }
                            } else {
                                enterKeyDownTimeMs = SystemClock.elapsedRealtime()
                            }
                            true
                        }
                        else -> false
                    }
                } else if (event.type == KeyEventType.KeyUp) {
                    when (event.key) {
                        Key.Enter, Key.DirectionCenter -> {
                            if (!isSidebarFocused && enterKeyDownTimeMs >= 0L) {
                                val holdMs = SystemClock.elapsedRealtime() - enterKeyDownTimeMs
                                val focusedItem = getFocusedItem()
                                if (focusedItem != null) {
                                    if (holdMs >= longPressThresholdMs) {
                                        viewModel.removeFromWatchlist(focusedItem)
                                    } else {
                                        onNavigateToDetails(focusedItem.mediaType, focusedItem.id)
                                    }
                                }
                                enterKeyDownTimeMs = -1L
                            }
                            true
                        }
                        else -> false
                    }
                } else false
            }
    ) {
        if (!isMobile) {
            AppTopBar(
                selectedItem = SidebarItem.WATCHLIST,
                isFocused = isSidebarFocused,
                focusedIndex = sidebarFocusIndex,
                profile = currentProfile
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = if (isMobile) 0.dp else AppTopBarContentTopInset)
                .padding(top = 4.dp)
        ) {
            if (isMobile) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 48.dp, top = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.watchlist),
                        style = ArflixTypography.heroTitle.copy(fontSize = 28.sp),
                        color = TextPrimary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator(color = Pink, size = 64.dp)
                    }
                }
                totalItems == 0 -> {
                    Box(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
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
                        state = lazyColumnState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 48.dp)
                            .focusable(false),
                        contentPadding = PaddingValues(top = if (isMobile) 8.dp else 0.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(if (isMobile) 24.dp else 16.dp),
                        userScrollEnabled = isMobile
                    ) {
                        itemsIndexed(
                            items = sections,
                            key = { _, (type, _) -> type },
                            contentType = { _, _ -> "watchlist_section" }
                        ) { sectionIdx, (sectionType, items) ->
                            val title = when (sectionType) {
                                "movies" -> tr("Movies")
                                "series" -> tr("Series")
                                else -> sectionType.replaceFirstChar { it.uppercase() }
                            }
                            WatchlistItemsSection(
                                title = title,
                                items = items,
                                logoUrls = logoUrls,
                                cardWidth = cardWidth,
                                isLandscape = !usePosterCards,
                                isMobile = isMobile,
                                focusedItemIndex = if (!isMobile && focusedSectionIndex == sectionIdx && !isSidebarFocused) focusedItemIndex else -1,
                                onItemFocused = { index ->
                                    if (!isSidebarFocused && focusedSectionIndex == sectionIdx) {
                                        focusedItemIndex = index
                                    }
                                },
                                onItemClick = { item -> onNavigateToDetails(item.mediaType, item.id) },
                                onItemLongPress = { item -> viewModel.removeFromWatchlist(item) }
                            )
                        }
                    }
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
                onDismiss = { viewModel.dismissToast() }
            )
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
            if (safe < first || safe > last) {
                lazyListState.scrollToItem(safe)
            } else if (safe != first) {
                lazyListState.animateScrollToItem(safe)
            }
        }

        LazyRow(
            state = lazyListState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 8.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                items = items,
                key = { _, item -> "${item.mediaType.name}-${item.id}" },
                contentType = { _, item -> "${item.mediaType.name}_card" }
            ) { index, item ->
                val logoUrl = logoUrls["${item.mediaType}_${item.id}"]
                MediaCard(
                    item = item,
                    width = cardWidth,
                    isLandscape = isLandscape,
                    logoImageUrl = logoUrl,
                    showTitle = true,
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
