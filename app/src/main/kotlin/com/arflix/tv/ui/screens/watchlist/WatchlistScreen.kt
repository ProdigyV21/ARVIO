package com.arflix.tv.ui.screens.watchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.itemsIndexed
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * Watchlist screen - matches webapp design with grid layout
 */
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
    val uiState by viewModel.uiState.collectAsState()
    val usePosterCards = false
    val configuration = LocalConfiguration.current
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val gridColumns = if (isMobile) 2 else when {
        configuration.screenWidthDp >= 2200 -> 5
        configuration.screenWidthDp >= 1600 -> 4
        else -> 3
    }
    val cardWidth = if (isMobile) 160.dp else when (gridColumns) {
        5 -> 240.dp
        4 -> 250.dp
        else -> 230.dp
    }
    
    var isSidebarFocused by remember { mutableStateOf(false) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 3 else 2) } // WATCHLIST
    val rootFocusRequester = remember { FocusRequester() }
    val gridFocusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()
    val gridState = rememberTvLazyGridState()
    var focusedGridIndex by remember { mutableIntStateOf(0) }

    // Keep the focused card in view with smooth animated scrolling.
    LaunchedEffect(focusedGridIndex, uiState.items.size) {
        if (uiState.items.isEmpty()) return@LaunchedEffect
        val safe = focusedGridIndex.coerceIn(0, uiState.items.lastIndex)
        val firstVisible = gridState.firstVisibleItemIndex
        val lastVisible = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: firstVisible
        val outsideViewport = safe < firstVisible || safe > lastVisible
        val distance = abs(firstVisible - safe)
        if (safe == 0 || outsideViewport || distance > gridColumns) {
            gridState.scrollToItem(safe)
        } else {
            gridState.animateScrollToItem(safe)
        }
    }

    LaunchedEffect(Unit) {
        rootFocusRequester.requestFocus()
    }

    LaunchedEffect(uiState.isLoading, uiState.items.isEmpty()) {
        if (!uiState.isLoading && uiState.items.isEmpty()) {
            // Empty screen must always have a deterministic focus target.
            isSidebarFocused = true
            sidebarFocusIndex = if (hasProfile) 3 else SidebarItem.WATCHLIST.ordinal
        } else if (!uiState.isLoading && uiState.items.isNotEmpty() && !isSidebarFocused) {
            // Ensure first card can receive focus when content becomes available.
            delay(80)
            runCatching { gridFocusRequester.requestFocus() }
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .focusRequester(rootFocusRequester)
            .onFocusChanged {
                if (it.hasFocus && uiState.items.isEmpty()) {
                    isSidebarFocused = true
                }
            }
            .focusable()
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            if (isSidebarFocused) {
                                onBack()
                            } else {
                                isSidebarFocused = true
                            }
                            true
                        }
                        Key.DirectionLeft -> {
                            if (!isSidebarFocused) {
                                // If on the leftmost column, move to Sidebar/TopBar
                                if (focusedGridIndex % gridColumns == 0) {
                                    isSidebarFocused = true
                                    true
                                } else false // Let the grid handle internal left movement
                            } else {
                                if (sidebarFocusIndex > 0) {
                                    sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                }
                                true
                            }
                        }
                        Key.DirectionRight -> {
                            if (isSidebarFocused) {
                                if (sidebarFocusIndex < maxSidebarIndex) {
                                    sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                                }
                                true
                            } else false
                        }
                        Key.DirectionUp -> {
                            if (!isSidebarFocused) {
                                // If on the top row, move focus back to the Top Bar
                                if (focusedGridIndex < gridColumns) {
                                    isSidebarFocused = true
                                    true
                                } else false // Let grid scroll up
                            } else true
                        }
                        Key.DirectionDown -> {
                            if (isSidebarFocused) {
                                if (uiState.items.isNotEmpty()) {
                                    isSidebarFocused = false
                                    scope.launch {
                                        delay(40)
                                        runCatching { gridFocusRequester.requestFocus() }
                                    }
                                }
                                true
                            } else false
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            if (isSidebarFocused) {
                                if (hasProfile && sidebarFocusIndex == 0) {
                                    onSwitchProfile()
                                } else {
                                    when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) {
                                        SidebarItem.SEARCH -> onNavigateToSearch()
                                        SidebarItem.HOME -> onNavigateToHome()
                                        SidebarItem.WATCHLIST -> { }
                                        SidebarItem.TV -> onNavigateToTv()
                                        SidebarItem.SETTINGS -> onNavigateToSettings()
                                        null -> Unit
                                    }
                                }
                                true
                            } else false
                        }
                        else -> false
                    }
                } else false
            }
    ) {
        if (!LocalDeviceType.current.isTouchDevice()) {
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
                .padding(top = AppTopBarContentTopInset)
                .padding(start = 24.dp, top = 24.dp, end = 48.dp)
        ) {
                // Header with pink bookmark icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Bookmark,
                        contentDescription = null,
                        tint = Pink,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "MY WATCHLIST",
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary
                    )
                }
                
                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingIndicator(color = Pink, size = 64.dp)
                        }
                    }
                    uiState.items.isEmpty() -> {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Bookmark,
                                    contentDescription = null,
                                    tint = Color.White.copy(alpha = 0.2f),
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Your watchlist is empty",
                                    style = ArflixTypography.body,
                                    color = Color.White.copy(alpha = 0.5f)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Add movies and shows to watch later",
                                    style = ArflixTypography.caption,
                                    color = Color.White.copy(alpha = 0.3f)
                                )
                            }
                        }
                    }
                    else -> {
                        // Grid of items - 4 columns like screenshot
                        TvLazyVerticalGrid(
                            columns = TvGridCells.Fixed(gridColumns),
                            state = gridState,
                            contentPadding = PaddingValues(top = 8.dp, bottom = 48.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(gridFocusRequester)
                                .onFocusChanged { 
                                    if (it.hasFocus) {
                                        isSidebarFocused = false
                                    }
                                }
                        ) {
                            itemsIndexed(uiState.items) { index, item ->
                                MediaCard(
                                    item = item,
                                    width = cardWidth,
                                    isLandscape = !usePosterCards,
                                    onFocused = { focusedGridIndex = index },
                                    onClick = { onNavigateToDetails(item.mediaType, item.id) }
                                )
                            }
                        }
                    }
                }
            }

        // Toast notification
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