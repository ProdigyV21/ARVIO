package com.arflix.tv.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.Sidebar
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import kotlin.math.max
import kotlin.math.min
import kotlin.math.abs

/**
 * Search screen with centered search bar and separate Movies/TV Shows rows
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onNavigateToDetails: (MediaType, Int) -> Unit = { _, _ -> },
    onNavigateToHome: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val isCompactHeight = configuration.screenHeightDp <= 780
    val searchBarWidth = (configuration.screenWidthDp.dp * 0.56f).coerceIn(500.dp, 760.dp)

    var focusZone by remember { mutableStateOf(FocusZone.SEARCH_INPUT) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = if (hasProfile) SidebarItem.entries.size else SidebarItem.entries.size - 1
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 1 else 0) } // SEARCH
    var currentRowIndex by remember { mutableIntStateOf(0) } // 0 = Movies, 1 = TV Shows
    var movieItemIndex by remember { mutableIntStateOf(0) }
    var tvItemIndex by remember { mutableIntStateOf(0) }
    var isSearchInputFocused by remember { mutableStateOf(false) }

    val searchFocusRequester = remember { FocusRequester() }
    val movieRowState = rememberTvLazyListState()
    val tvRowState = rememberTvLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current

    // Auto-focus search input on launch
    LaunchedEffect(Unit) {
        searchFocusRequester.requestFocus()
    }

    LaunchedEffect(uiState.movieResults) {
        if (movieItemIndex >= uiState.movieResults.size) {
            movieItemIndex = 0
        }
        if (uiState.movieResults.isNotEmpty()) {
            movieRowState.scrollToItem(0)
        }
    }

    LaunchedEffect(uiState.tvResults) {
        if (tvItemIndex >= uiState.tvResults.size) {
            tvItemIndex = 0
        }
        if (uiState.tvResults.isNotEmpty()) {
            tvRowState.scrollToItem(0)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            when (focusZone) {
                                FocusZone.SIDEBAR -> onBack()
                                FocusZone.SEARCH_INPUT -> {
                                    focusZone = FocusZone.SIDEBAR
                                }
                                FocusZone.RESULTS -> {
                                    focusZone = FocusZone.SEARCH_INPUT
                                    searchFocusRequester.requestFocus()
                                }
                            }
                            true
                        }
                        Key.DirectionLeft -> {
                            when (focusZone) {
                                FocusZone.SEARCH_INPUT -> {
                                    focusZone = FocusZone.SIDEBAR
                                    true
                                }
                                FocusZone.RESULTS -> {
                                    val currentIndex = if (currentRowIndex == 0) movieItemIndex else tvItemIndex
                                    if (currentIndex == 0) {
                                        focusZone = FocusZone.SIDEBAR
                                    } else {
                                        if (currentRowIndex == 0) movieItemIndex-- else tvItemIndex--
                                    }
                                    true
                                }
                                else -> false
                            }
                        }
                        Key.DirectionRight -> {
                            when (focusZone) {
                                FocusZone.SIDEBAR -> {
                                    focusZone = FocusZone.SEARCH_INPUT
                                    searchFocusRequester.requestFocus()
                                    true
                                }
                                FocusZone.RESULTS -> {
                                    val maxIndex = if (currentRowIndex == 0) uiState.movieResults.size - 1 else uiState.tvResults.size - 1
                                    val currentIndex = if (currentRowIndex == 0) movieItemIndex else tvItemIndex
                                    if (currentIndex < maxIndex) {
                                        if (currentRowIndex == 0) movieItemIndex++ else tvItemIndex++
                                    }
                                    true
                                }
                                else -> false
                            }
                        }
                        Key.DirectionUp -> {
                            when (focusZone) {
                                FocusZone.SIDEBAR -> if (sidebarFocusIndex > 0) {
                                    sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                }
                                FocusZone.RESULTS -> {
                                    if (currentRowIndex == 1 && uiState.movieResults.isNotEmpty()) {
                                        currentRowIndex = 0
                                    } else if (currentRowIndex == 0) {
                                        focusZone = FocusZone.SEARCH_INPUT
                                        searchFocusRequester.requestFocus()
                                    }
                                }
                                else -> {}
                            }
                            true
                        }
                        Key.DirectionDown -> {
                            when (focusZone) {
                                FocusZone.SIDEBAR -> if (sidebarFocusIndex < maxSidebarIndex) {
                                    sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                                }
                                FocusZone.SEARCH_INPUT -> {
                                    if (uiState.movieResults.isNotEmpty() || uiState.tvResults.isNotEmpty()) {
                                        focusZone = FocusZone.RESULTS
                                        currentRowIndex = if (uiState.movieResults.isNotEmpty()) 0 else 1
                                    }
                                }
                                FocusZone.RESULTS -> {
                                    if (currentRowIndex == 0 && uiState.tvResults.isNotEmpty()) {
                                        currentRowIndex = 1
                                    }
                                }
                            }
                            true
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            when (focusZone) {
                                FocusZone.SIDEBAR -> {
                                    if (hasProfile && sidebarFocusIndex == 0) {
                                        onSwitchProfile()
                                    } else {
                                        val itemIndex = if (hasProfile) sidebarFocusIndex - 1 else sidebarFocusIndex
                                        when (SidebarItem.entries[itemIndex]) {
                                            SidebarItem.SEARCH -> { /* Already here */ }
                                            SidebarItem.HOME -> onNavigateToHome()
                                            SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                            SidebarItem.TV -> onNavigateToTv()
                                            SidebarItem.SETTINGS -> onNavigateToSettings()
                                        }
                                    }
                                    true
                                }
                                FocusZone.SEARCH_INPUT -> {
                                    viewModel.search()
                                    keyboardController?.hide()
                                    true
                                }
                                FocusZone.RESULTS -> {
                                    val item = if (currentRowIndex == 0) {
                                        uiState.movieResults.getOrNull(movieItemIndex)
                                    } else {
                                        uiState.tvResults.getOrNull(tvItemIndex)
                                    }
                                    if (item != null) {
                                        onNavigateToDetails(item.mediaType, item.id)
                                    }
                                    true
                                }
                            }
                        }
                        else -> false
                    }
                } else false
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Sidebar
            Sidebar(
                selectedItem = SidebarItem.SEARCH,
                isSidebarFocused = focusZone == FocusZone.SIDEBAR,
                focusedIndex = sidebarFocusIndex,
                profile = currentProfile,
                onProfileClick = onSwitchProfile
            )

            // Main content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(
                        horizontal = if (isCompactHeight) 40.dp else 48.dp,
                        vertical = if (isCompactHeight) 20.dp else 32.dp
                    )
            ) {
                // Centered Search Bar at Top
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = if (isCompactHeight) 20.dp else 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .width(searchBarWidth)
                            .background(BackgroundCard, RoundedCornerShape(12.dp))
                            .border(
                                width = if (isSearchInputFocused) 2.dp else 1.dp,
                                color = if (isSearchInputFocused) Color.White else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 20.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = if (isSearchInputFocused) Pink else TextSecondary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        BasicTextField(
                            value = uiState.query,
                            onValueChange = { viewModel.updateQuery(it) },
                            textStyle = ArflixTypography.body.copy(color = TextPrimary),
                            cursorBrush = SolidColor(Color.White),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = {
                                viewModel.search()
                                keyboardController?.hide()
                            }),
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(searchFocusRequester)
                                .onFocusChanged { state ->
                                    isSearchInputFocused = state.isFocused
                                    if (state.isFocused) {
                                        focusZone = FocusZone.SEARCH_INPUT
                                    }
                                },
                            decorationBox = { innerTextField ->
                                if (uiState.query.isEmpty()) {
                                    Text(
                                        text = "Search movies and TV shows...",
                                        style = ArflixTypography.body,
                                        color = TextSecondary
                                    )
                                }
                                innerTextField()
                            }
                        )
                    }
                }

                // Results Area
                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator(color = Pink, size = 64.dp)
                    }
                } else if (uiState.movieResults.isEmpty() && uiState.tvResults.isEmpty() && uiState.query.isNotEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No results found for \"${uiState.query}\"",
                            style = ArflixTypography.body,
                            color = TextSecondary
                        )
                    }
                } else {
                    // Movies Row
                    if (uiState.movieResults.isNotEmpty()) {
                        SearchResultRow(
                            title = "Movies",
                            items = uiState.movieResults,
                            cardLogoUrls = uiState.cardLogoUrls,
                            rowState = movieRowState,
                            isCurrentRow = focusZone == FocusZone.RESULTS && currentRowIndex == 0,
                            focusedItemIndex = movieItemIndex,
                            onItemClick = { item -> onNavigateToDetails(item.mediaType, item.id) }
                        )
                        Spacer(modifier = Modifier.height(if (isCompactHeight) 4.dp else 8.dp))
                    }

                    // TV Shows Row
                    if (uiState.tvResults.isNotEmpty()) {
                        SearchResultRow(
                            title = "TV Shows",
                            items = uiState.tvResults,
                            cardLogoUrls = uiState.cardLogoUrls,
                            rowState = tvRowState,
                            isCurrentRow = focusZone == FocusZone.RESULTS && currentRowIndex == 1,
                            focusedItemIndex = tvItemIndex,
                            onItemClick = { item -> onNavigateToDetails(item.mediaType, item.id) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SearchResultRow(
    title: String,
    items: List<MediaItem>,
    cardLogoUrls: Map<String, String>,
    rowState: androidx.tv.foundation.lazy.list.TvLazyListState,
    isCurrentRow: Boolean,
    focusedItemIndex: Int,
    onItemClick: (MediaItem) -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val isCompactHeight = configuration.screenHeightDp <= 780
    val itemWidth = 210.dp
    val itemSpacing = 16.dp
    val sidebarWidth = 56.dp
    val horizontalPadding = 96.dp
    val availableWidthDp = (configuration.screenWidthDp.dp - sidebarWidth - horizontalPadding)
        .coerceAtLeast(1.dp)
    val fallbackItemsPerPage = remember(configuration, density) {
        val availablePx = with(density) { availableWidthDp.roundToPx().coerceAtLeast(1) }
        val itemSpanPx = with(density) { (itemWidth + itemSpacing).roundToPx().coerceAtLeast(1) }
        max(1, availablePx / itemSpanPx)
    }
    var baseVisibleCount by remember { mutableIntStateOf(0) }
    val visibleCount = rowState.layoutInfo.visibleItemsInfo.size
    LaunchedEffect(visibleCount) {
        if (visibleCount > 0 && baseVisibleCount == 0) {
            baseVisibleCount = visibleCount
        }
    }
    val itemsPerPage = remember(fallbackItemsPerPage, baseVisibleCount) {
        if (baseVisibleCount > 0) min(baseVisibleCount, fallbackItemsPerPage) else fallbackItemsPerPage
    }
    val effectiveVisibleCount = remember(items.size, itemsPerPage, visibleCount) {
        if (visibleCount > 0) min(visibleCount, items.size.coerceAtLeast(1)) else itemsPerPage
    }
    val maxFirstIndex = (items.size - effectiveVisibleCount).coerceAtLeast(0)
    val scrollTargetIndex by remember(rowState, focusedItemIndex, isCurrentRow, items.size, maxFirstIndex) {
        derivedStateOf {
            if (!isCurrentRow || focusedItemIndex < 0) return@derivedStateOf -1
            if (items.isEmpty()) return@derivedStateOf -1
            focusedItemIndex.coerceAtMost(maxFirstIndex)
        }
    }
    val itemSpanPx = remember(density, itemWidth, itemSpacing) {
        with(density) { (itemWidth + itemSpacing).toPx().coerceAtLeast(1f) }
    }
    var lastScrollIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(isCurrentRow) {
        if (!isCurrentRow) {
            lastScrollIndex = -1
        }
    }
    LaunchedEffect(scrollTargetIndex, isCurrentRow, focusedItemIndex) {
        if (!isCurrentRow || scrollTargetIndex < 0) return@LaunchedEffect

        val extraOffset = if (focusedItemIndex > maxFirstIndex) {
            ((focusedItemIndex - maxFirstIndex) * itemSpanPx).toInt()
        } else 0

        if (focusedItemIndex == 0 && scrollTargetIndex == 0) {
            rowState.animateScrollToItem(index = 0, scrollOffset = 0)
            lastScrollIndex = 0
            return@LaunchedEffect
        }

        if (lastScrollIndex == scrollTargetIndex && extraOffset == 0) return@LaunchedEffect
        if (lastScrollIndex == -1) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
            lastScrollIndex = scrollTargetIndex
            return@LaunchedEffect
        }

        val delta = scrollTargetIndex - lastScrollIndex
        if (delta == 0 && extraOffset > 0) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (abs(delta) > 1) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (delta != 0) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        }
        lastScrollIndex = scrollTargetIndex
    }

    Column {
        // Row Title
        Text(
            text = "$title (${items.size})",
            style = ArvioSkin.typography.sectionTitle,
            color = ArvioSkin.colors.textPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Horizontal Card Row - extra padding to prevent focus scale clipping
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TvLazyRow(
                state = rowState,
                contentPadding = PaddingValues(
                    start = 0.dp,
                    end = 160.dp,
                    top = if (isCompactHeight) 10.dp else 16.dp,
                    bottom = if (isCompactHeight) 16.dp else 24.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(itemSpacing),
                pivotOffsets = androidx.tv.foundation.PivotOffsets(
                    parentFraction = 0.0f,
                    childFraction = 0.0f
                )
            ) {
                itemsIndexed(items, key = { _, it -> it.id }) { index, item ->
                    // Create item with year in subtitle (e.g., "Movie | 2023")
                    val mediaTypeLabel = when (item.mediaType) {
                        MediaType.TV -> "TV Show"
                        MediaType.MOVIE -> "Movie"
                    }
                    val yearValue = item.year.ifBlank { item.releaseDate?.takeLast(4).orEmpty() }
                    val yearDisplay = if (yearValue.isNotBlank()) " | $yearValue" else ""
                    val displayItem = item.copy(
                        subtitle = "$mediaTypeLabel$yearDisplay"
                    )
                    MediaCard(
                        item = displayItem,
                        width = itemWidth,
                        isLandscape = true,
                        logoImageUrl = cardLogoUrls["${item.mediaType}_${item.id}"],
                        showProgress = false,
                        isFocusedOverride = isCurrentRow && index == focusedItemIndex,
                        enableSystemFocus = false,
                        onFocused = { },
                        onClick = { onItemClick(item) }
                    )
                }
            }
        }
    }
}

private enum class FocusZone {
    SIDEBAR, SEARCH_INPUT, RESULTS
}
