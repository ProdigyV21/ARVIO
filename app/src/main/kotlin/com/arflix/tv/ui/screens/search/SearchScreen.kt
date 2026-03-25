package com.arflix.tv.ui.screens.search

import android.os.SystemClock
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Category
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.components.rememberCardLayoutMode
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.AccentGreen
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType

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
    val usePosterCards = rememberCardLayoutMode() == CardLayoutMode.POSTER
    val configuration = LocalConfiguration.current
    val isCompactHeight = configuration.screenHeightDp <= 780
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val searchBarWidth = if (isTouchDevice) (configuration.screenWidthDp.dp * 0.9f).coerceIn(280.dp, 760.dp)
        else (configuration.screenWidthDp.dp * 0.56f).coerceIn(500.dp, 760.dp)

    val hasSearchResults = uiState.movieResults.isNotEmpty() || uiState.tvResults.isNotEmpty()
    val hasAiResults = uiState.isAiSearch && uiState.aiResults.isNotEmpty()

    // Determine which categories to show in rows
    val activeCategories: List<Category> = when {
        hasSearchResults -> {
            val list = mutableListOf<Category>()
            if (uiState.movieResults.isNotEmpty()) list.add(Category("s_m", "Movies (${uiState.movieResults.size})", uiState.movieResults))
            if (uiState.tvResults.isNotEmpty()) list.add(Category("s_t", "TV Shows (${uiState.tvResults.size})", uiState.tvResults))
            list
        }
        uiState.query.isEmpty() -> uiState.discoverCategories
        else -> emptyList()
    }
    val activeLogoUrls: Map<String, String> = when {
        hasSearchResults -> uiState.cardLogoUrls
        else -> uiState.discoverLogoUrls
    }

    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 1 else 0) }
    var isSearchInputFocused by remember { mutableStateOf(false) }
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }

    // Manual row/item focus tracking (like HomeScreen)
    var currentRowIndex by remember { mutableIntStateOf(0) }
    var currentItemIndex by remember { mutableIntStateOf(0) }

    val searchFocusRequester = remember { FocusRequester() }
    val filtersFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) { searchFocusRequester.requestFocus(); suppressSelectUntilMs = SystemClock.elapsedRealtime() + 300L }

    val showFilters = uiState.query.isEmpty()

    // Persist focusZone across navigation back from Details - use rememberSaveable instead of remember
    var focusZone by rememberSaveable { mutableStateOf(FocusZone.SEARCH_INPUT) }

    // D-pad handler: manages zone transitions. FILTERS zone lets native focus handle Left/Right.
    val dpadModifier = if (!isTouchDevice) {
        Modifier.onPreviewKeyEvent { event ->
            if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
            when (event.key) {
                Key.Back, Key.Escape -> when (focusZone) {
                    FocusZone.RESULTS -> {
                        if (showFilters) focusZone = FocusZone.FILTERS
                        else { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus() }
                        true
                    }
                    FocusZone.FILTERS -> { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    FocusZone.SEARCH_INPUT -> { focusZone = FocusZone.SIDEBAR; true }
                    FocusZone.SIDEBAR -> { onBack(); true }
                }
                Key.DirectionUp -> when (focusZone) {
                    FocusZone.SIDEBAR -> true
                    FocusZone.SEARCH_INPUT -> { focusZone = FocusZone.SIDEBAR; true }
                    FocusZone.FILTERS -> false // Let native focus handle up/down between 3 filter chip rows
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false // AI grid: let native focus handle navigation
                        else if (currentRowIndex > 0) { currentRowIndex--; currentItemIndex = 0; true }
                        else if (showFilters) { focusZone = FocusZone.FILTERS; try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}; true }
                        else { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    }
                }
                Key.DirectionDown -> when (focusZone) {
                    FocusZone.SIDEBAR -> { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    FocusZone.SEARCH_INPUT -> {
                        if (showFilters) { focusZone = FocusZone.FILTERS; try { filtersFocusRequester.requestFocus() } catch (_: Exception) {} }
                        else if (activeCategories.isNotEmpty() || hasAiResults) { focusZone = FocusZone.RESULTS; currentRowIndex = 0; currentItemIndex = 0 }
                        true
                    }
                    FocusZone.FILTERS -> false // Let native focus handle up/down between 3 filter chip rows
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false // AI grid: let native focus handle navigation
                        else if (currentRowIndex < activeCategories.size - 1) { currentRowIndex++; currentItemIndex = 0; true }
                        else true
                    }
                }
                Key.DirectionLeft -> when (focusZone) {
                    FocusZone.SIDEBAR -> { if (sidebarFocusIndex > 0) sidebarFocusIndex--; true }
                    FocusZone.RESULTS -> { if (hasAiResults) false else { if (currentItemIndex > 0) currentItemIndex--; true } }
                    FocusZone.FILTERS -> false
                    else -> false
                }
                Key.DirectionRight -> when (focusZone) {
                    FocusZone.SIDEBAR -> { if (sidebarFocusIndex < maxSidebarIndex) sidebarFocusIndex++; true }
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false // AI grid: let native focus handle navigation
                        else { val maxItem = (activeCategories.getOrNull(currentRowIndex)?.items?.size ?: 1) - 1; if (currentItemIndex < maxItem) currentItemIndex++; true }
                    }
                    FocusZone.FILTERS -> false
                    else -> false
                }
                Key.Enter, Key.DirectionCenter -> {
                    if (SystemClock.elapsedRealtime() < suppressSelectUntilMs) return@onPreviewKeyEvent true
                    when (focusZone) {
                        FocusZone.SIDEBAR -> {
                            if (hasProfile && sidebarFocusIndex == 0) onSwitchProfile()
                            else when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) { SidebarItem.SEARCH -> Unit; SidebarItem.HOME -> onNavigateToHome(); SidebarItem.WATCHLIST -> onNavigateToWatchlist(); SidebarItem.TV -> onNavigateToTv(); SidebarItem.SETTINGS -> onNavigateToSettings(); null -> Unit }
                            true
                        }
                        FocusZone.SEARCH_INPUT -> false
                        FocusZone.FILTERS -> false
                        FocusZone.RESULTS -> {
                            if (hasAiResults) false // AI grid: let native focus handle Enter/OK on cards
                            else { val item = activeCategories.getOrNull(currentRowIndex)?.items?.getOrNull(currentItemIndex); if (item != null) onNavigateToDetails(item.mediaType, item.id); true }
                        }
                    }
                }
                else -> false
            }
        }
    } else Modifier

    Box(modifier = Modifier.fillMaxSize().background(BackgroundDark).then(dpadModifier)) {
        if (!isTouchDevice) AppTopBar(selectedItem = SidebarItem.SEARCH, isFocused = focusZone == FocusZone.SIDEBAR, focusedIndex = sidebarFocusIndex, profile = currentProfile)

        Column(modifier = Modifier.fillMaxSize().padding(top = AppTopBarContentTopInset).padding(horizontal = if (isCompactHeight) 20.dp else 28.dp)) {
            // ── Search Bar ──
            Box(modifier = Modifier.fillMaxWidth().padding(bottom = if (isCompactHeight) 3.dp else 5.dp), contentAlignment = Alignment.Center) {
                if (isTouchDevice) {
                    OutlinedTextField(value = uiState.query, onValueChange = { viewModel.updateQuery(it) },
                        placeholder = { Text("Search or discover... try \"top 10 horror movies\"", style = ArflixTypography.body, color = TextSecondary) },
                        leadingIcon = { Icon(Icons.Default.Search, null, tint = if (isSearchInputFocused) Pink else TextSecondary, modifier = Modifier.size(22.dp)) },
                        textStyle = ArflixTypography.body.copy(color = TextPrimary), singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), keyboardActions = KeyboardActions(onSearch = { viewModel.search(); keyboardController?.hide() }),
                        colors = TextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary, cursorColor = Color.White, focusedContainerColor = BackgroundCard, unfocusedContainerColor = BackgroundCard, focusedIndicatorColor = Color.White, unfocusedIndicatorColor = Color.White.copy(alpha = 0.15f)),
                        shape = RoundedCornerShape(10.dp), modifier = Modifier.width(searchBarWidth).focusRequester(searchFocusRequester).onFocusChanged { isSearchInputFocused = it.isFocused })
                } else {
                    Row(modifier = Modifier.width(searchBarWidth).background(Color.White.copy(alpha = 0.04f), RoundedCornerShape(10.dp)).border(if (isSearchInputFocused) 1.5.dp else 0.5.dp, if (isSearchInputFocused) Color.White.copy(alpha = 0.6f) else Color.White.copy(alpha = 0.1f), RoundedCornerShape(10.dp)).padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Search, null, tint = if (isSearchInputFocused) Color.White else TextSecondary, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(10.dp))
                        BasicTextField(value = uiState.query, onValueChange = { viewModel.updateQuery(it) },
                            textStyle = ArflixTypography.body.copy(color = TextPrimary, fontSize = 14.sp), cursorBrush = SolidColor(Color.White), singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), keyboardActions = KeyboardActions(onSearch = { viewModel.search(); keyboardController?.hide() }),
                            modifier = Modifier.weight(1f).focusRequester(searchFocusRequester).onFocusChanged { s -> isSearchInputFocused = s.isFocused; if (s.isFocused) focusZone = FocusZone.SEARCH_INPUT },
                            decorationBox = { inner -> if (uiState.query.isEmpty()) Text("Search or discover... try \"top 10 horror movies\"", style = ArflixTypography.body.copy(fontSize = 14.sp), color = Color.White.copy(alpha = 0.25f)); inner() })
                    }
                }
            }

            // ── Filter Chips (discover mode) - focusable with D-pad ──
            if (showFilters) {
                Column(modifier = Modifier
                    .focusRequester(filtersFocusRequester)
                    .onFocusChanged { state ->
                        if (state.hasFocus) focusZone = FocusZone.FILTERS
                        else if (focusZone == FocusZone.FILTERS && !state.hasFocus) {
                            // Focus left filters - if search bar got focus, zone will update via its onFocusChanged
                            // If nothing else caught it, transition to rows
                            if (!isSearchInputFocused && activeCategories.isNotEmpty()) {
                                focusZone = FocusZone.RESULTS
                                currentRowIndex = 0; currentItemIndex = 0
                            }
                        }
                    }
                ) {
                    LazyRow(modifier = Modifier.fillMaxWidth().padding(bottom = 3.dp), horizontalArrangement = Arrangement.spacedBy(6.dp), contentPadding = PaddingValues(horizontal = 2.dp)) {
                        items(DiscoverType.entries.size, key = { DiscoverType.entries[it].name }) { i -> val t = DiscoverType.entries[i]; GlowChip(t.label, uiState.selectedType == t) { viewModel.selectType(t) } }
                    }
                    LazyRow(modifier = Modifier.fillMaxWidth().padding(bottom = 3.dp), horizontalArrangement = Arrangement.spacedBy(6.dp), contentPadding = PaddingValues(horizontal = 2.dp)) {
                        val genres = viewModel.getGenresForType()
                        item(key = "all_g") { GlowChip("All Genres", uiState.selectedGenre == null) { viewModel.selectGenre(null) } }
                        items(genres.size, key = { "g_${genres[it].id}" }) { i -> GlowChip(genres[i].name, uiState.selectedGenre == genres[i]) { viewModel.selectGenre(genres[i]) } }
                    }
                    LazyRow(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp), horizontalArrangement = Arrangement.spacedBy(6.dp), contentPadding = PaddingValues(horizontal = 2.dp)) {
                        item(key = "any_l") { GlowChip("Any Language", uiState.selectedCountry == null) { viewModel.selectCountry(null) } }
                        items(COUNTRIES.size, key = { "c_${COUNTRIES[it].code}" }) { i -> GlowChip(COUNTRIES[i].name, uiState.selectedCountry == COUNTRIES[i]) { viewModel.selectCountry(COUNTRIES[i]) } }
                    }
                }
            }

            // ── Content ──
            when {
                uiState.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 48.dp) }

                hasAiResults -> {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)) {
                        Icon(Icons.Default.AutoAwesome, null, tint = AccentGreen, modifier = Modifier.size(16.dp)); Spacer(Modifier.width(6.dp))
                        Text(uiState.aiInterpretation ?: "", style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = FontWeight.Medium), color = Color.White.copy(alpha = 0.85f))
                    }
                    ContentGrid(items = uiState.aiResults, usePosterCards = usePosterCards, isLoading = false, isTouchDevice = isTouchDevice, onItemClick = { onNavigateToDetails(it.mediaType, it.id) }, onLoadMore = {})
                }

                uiState.query.isNotEmpty() && !uiState.isAiSearch && !hasSearchResults -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("No results found for \"${uiState.query}\"", style = ArflixTypography.body, color = TextSecondary) }
                }

                uiState.isDiscoverLoading && activeCategories.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 48.dp) }

                activeCategories.isNotEmpty() -> {
                    // Row-based content (discover rows or search results) - HomeScreen pattern
                    RowsLayer(
                        categories = activeCategories,
                        cardLogoUrls = activeLogoUrls,
                        currentRowIndex = currentRowIndex,
                        currentItemIndex = currentItemIndex,
                        isFocused = focusZone == FocusZone.RESULTS,
                        usePosterCards = usePosterCards,
                        isTouchDevice = isTouchDevice,
                        onItemClick = { onNavigateToDetails(it.mediaType, it.id) }
                    )
                }
            }
        }
    }
}

// ── Glow Chip ───────────────────────────────────────────────────────────────

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GlowChip(label: String, isSelected: Boolean, onSelect: () -> Unit) {
    val chipShape = rememberArvioCardShape(6.dp)
    ArvioFocusableSurface(
        modifier = Modifier.padding(vertical = 1.dp),
        shape = chipShape,
        backgroundColor = if (isSelected) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.03f),
        outlineColor = ArvioSkin.colors.focusOutline,
        outlineWidth = 2.5.dp,
        focusedScale = 1.05f,
        pressedScale = 0.97f,
        enableSystemFocus = true,
        onClick = onSelect,
    ) { isFocused ->
        Box(modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)) {
            Text(label, style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = if (isSelected || isFocused) FontWeight.SemiBold else FontWeight.Normal),
                color = if (isFocused) Color.White else if (isSelected) Color.White.copy(alpha = 0.9f) else Color.White.copy(alpha = 0.5f))
        }
    }
}

// ── Rows Layer (HomeScreen pattern - manual focus, smooth scroll) ────────────

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun RowsLayer(
    categories: List<Category>, cardLogoUrls: Map<String, String>,
    currentRowIndex: Int, currentItemIndex: Int, isFocused: Boolean,
    usePosterCards: Boolean, isTouchDevice: Boolean,
    onItemClick: (MediaItem) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    val itemWidth = if (usePosterCards) 130.dp else 260.dp
    val rowHeight = if (usePosterCards) 250.dp else 210.dp

    val listState = rememberLazyListState()
    val targetIndex = currentRowIndex.coerceIn(0, (categories.size - 1).coerceAtLeast(0))

    // Scroll to focused row (smooth like HomeScreen)
    LaunchedEffect(targetIndex) {
        val currentFirst = listState.firstVisibleItemIndex
        if (currentFirst == targetIndex) return@LaunchedEffect
        val jump = kotlin.math.abs(targetIndex - currentFirst)
        if (jump <= 1) listState.animateScrollToItem(targetIndex) else listState.scrollToItem(targetIndex)
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = maxHeight * 0.6f),
            modifier = Modifier.fillMaxSize().clipToBounds(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(categories.size, key = { categories[it].id }) { index ->
                val category = categories[index]
                val isCurrentRow = isFocused && index == currentRowIndex
                // Fade non-current rows
                val rowAlpha by animateFloatAsState(
                    targetValue = if (!isFocused || index <= currentRowIndex) 1f else 0.3f,
                    animationSpec = tween(250), label = "rowAlpha"
                )

                Box(modifier = Modifier.fillMaxWidth().height(rowHeight).clipToBounds().graphicsLayer { alpha = rowAlpha }) {
                    Column {
                        Text(
                            category.title,
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp),
                            color = Color.White.copy(alpha = if (isCurrentRow) 0.9f else 0.5f),
                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, top = 4.dp)
                        )

                        val rowState = rememberLazyListState()
                        // Scroll to focused item in current row
                        LaunchedEffect(isCurrentRow, currentItemIndex) {
                            if (!isCurrentRow) return@LaunchedEffect
                            val safeIndex = currentItemIndex.coerceIn(0, (category.items.size - 1).coerceAtLeast(0))
                            val first = rowState.firstVisibleItemIndex
                            val last = rowState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: first
                            if (safeIndex < first || safeIndex > last) rowState.scrollToItem(safeIndex)
                            else if (safeIndex != first) rowState.animateScrollToItem(safeIndex)
                        }

                        LazyRow(
                            state = rowState,
                            contentPadding = PaddingValues(start = 8.dp, end = itemWidth + 30.dp, top = 4.dp, bottom = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            itemsIndexed(category.items, key = { _, item -> "${item.mediaType}_${item.id}" }) { itemIdx, item ->
                                val itemIsFocused = isCurrentRow && itemIdx == currentItemIndex
                                MediaCard(
                                    item = item.copy(title = buildCardTitle(item), subtitle = buildCardSubtitle(item)),
                                    width = itemWidth,
                                    isLandscape = !usePosterCards,
                                    logoImageUrl = cardLogoUrls["${item.mediaType}_${item.id}"],
                                    showProgress = false,
                                    titleMaxLines = 2,
                                    subtitleMaxLines = 1,
                                    isFocusedOverride = itemIsFocused,
                                    enableSystemFocus = false,
                                    onFocused = {},
                                    onClick = { onItemClick(item) },
                                    modifier = if (isTouchDevice) Modifier.clickable { onItemClick(item) } else Modifier
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ── Content Grid (AI results) ───────────────────────────────────────────────

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ContentGrid(items: List<MediaItem>, usePosterCards: Boolean, isLoading: Boolean, isTouchDevice: Boolean, onItemClick: (MediaItem) -> Unit, onLoadMore: () -> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val itemWidth = if (usePosterCards) 130.dp else 260.dp
    val gridState = rememberLazyGridState()
    LaunchedEffect(gridState.firstVisibleItemIndex, items.size) { val lv = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0; if (items.isNotEmpty() && lv >= items.size - 8) onLoadMore() }

    LazyVerticalGrid(state = gridState, columns = GridCells.Adaptive(minSize = itemWidth + 16.dp), contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp), verticalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier.fillMaxSize()) {
        items(items.size, key = { "${items[it].mediaType}_${items[it].id}" }) { idx ->
            val item = items[idx]
            MediaCard(item = item.copy(title = buildCardTitle(item), subtitle = buildCardSubtitle(item)),
                width = itemWidth, isLandscape = !usePosterCards, showProgress = false, titleMaxLines = 2, subtitleMaxLines = 1,
                isFocusedOverride = false, enableSystemFocus = true, onFocused = {}, onClick = { onItemClick(item) },
                modifier = if (isTouchDevice) Modifier.clickable { onItemClick(item) } else Modifier)
        }
        if (isLoading) { item { Box(Modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 32.dp) } } }
    }
}

private fun buildCardTitle(item: MediaItem): String {
    val year = item.year.takeIf { it.isNotBlank() }
    return if (year != null) "${item.title} ($year)" else item.title
}

private fun buildCardSubtitle(item: MediaItem): String {
    return when (item.mediaType) { MediaType.TV -> "Series"; MediaType.MOVIE -> "Movie" }
}

private enum class FocusZone { SIDEBAR, SEARCH_INPUT, FILTERS, RESULTS }
