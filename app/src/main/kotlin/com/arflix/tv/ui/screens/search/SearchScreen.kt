package com.arflix.tv.ui.screens.search

import android.os.SystemClock
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R

import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.isPortrait
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.appBackgroundDark
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
    onNavigateToCatalog: (String) -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToDiscover: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val aiUsePosterCards = rememberCatalogueRowLayoutMode("search:ai") == CardLayoutMode.POSTER
    val configuration = LocalConfiguration.current
    val isCompactHeight = configuration.screenHeightDp <= 780
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val searchBarWidth = if (isTouchDevice) configuration.screenWidthDp.dp - 24.dp
        else (configuration.screenWidthDp.dp * 0.48f).coerceIn(460.dp, 680.dp)

    val hasCatalogResults = uiState.catalogResults.isNotEmpty()
    val hasSearchResults = hasCatalogResults || uiState.movieResults.isNotEmpty() || uiState.tvResults.isNotEmpty() || uiState.personResults.isNotEmpty()
    val hasAiResults = uiState.isAiSearch && uiState.aiResults.isNotEmpty()
    val searchTopResults = remember(uiState.movieResults, uiState.tvResults) {
        interleaveSearchResults(uiState.movieResults, uiState.tvResults).take(24)
    }

    // Determine which categories to show in rows (filter out empty ones)
    val activeCategories: List<Category> = when {
        hasSearchResults -> {
            val list = mutableListOf<Category>()
            list.addAll(uiState.personResults)
            if (searchTopResults.isNotEmpty()) list.add(Category("s_all", "${stringResource(R.string.search)} (${searchTopResults.size})", searchTopResults))
            if (uiState.movieResults.isNotEmpty()) list.add(Category("s_m", "${stringResource(R.string.movies)} (${uiState.movieResults.size})", uiState.movieResults))
            if (uiState.tvResults.isNotEmpty()) list.add(Category("s_t", "${stringResource(R.string.tv_shows)} (${uiState.tvResults.size})", uiState.tvResults))
            list
        }
        uiState.query.isEmpty() -> uiState.discoverCategories.filter { it.items.isNotEmpty() }
        else -> emptyList()
    }
    val activeLogoUrls: Map<String, String> = when {
        hasSearchResults -> uiState.cardLogoUrls
        else -> uiState.discoverLogoUrls
    }
    val catalogRowPresent = uiState.query.isNotEmpty() && !uiState.isAiSearch && hasCatalogResults
    val catalogRowOffset = if (catalogRowPresent) 1 else 0
    val resultRowCount = catalogRowOffset + activeCategories.size

    var focusZone by remember { mutableStateOf(FocusZone.SEARCH_INPUT) }
    val hasProfile = false
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 1 else 0) }
    var isSearchInputFocused by remember { mutableStateOf(false) }
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }
    val fastScrollThresholdMs = 220L

    // Manual row/item focus tracking (like HomeScreen)
    var currentRowIndex by remember { mutableIntStateOf(0) }
    var currentItemIndex by remember { mutableIntStateOf(0) }
    var focusedFilterIndex by remember { mutableIntStateOf(0) }
    var resultsLastNavEventTime by remember { mutableLongStateOf(0L) }
    var isSearchEditing by remember { mutableStateOf(false) }
    var searchEditRequestNonce by remember { mutableIntStateOf(0) }

    val searchFocusRequester = remember { FocusRequester() }
    val textInputFocusRequester = remember { FocusRequester() }
    val filtersFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val actionGenre = remember { ALL_GENRES.firstOrNull { it.id == 28 } }
    val comedyGenre = remember { ALL_GENRES.firstOrNull { it.id == 35 } }
    val horrorGenre = remember { ALL_GENRES.firstOrNull { it.id == 27 } }
    val sciFiGenre = remember { ALL_GENRES.firstOrNull { it.id == 878 } }
    val japaneseCountry = remember { COUNTRIES.firstOrNull { it.code == "ja" } }
    val koreanCountry = remember { COUNTRIES.firstOrNull { it.code == "ko" } }
    val hindiCountry = remember { COUNTRIES.firstOrNull { it.code == "hi" } }
    val quickFilters = listOfNotNull(
        DiscoverQuickFilter(
            key = "all",
            label = "All",
            isSelected = uiState.selectedType == DiscoverType.ALL && uiState.selectedGenre == null && uiState.selectedCountry == null,
            onSelect = { viewModel.setDiscoverFilters(DiscoverType.ALL, null, null) }
        ),
        DiscoverQuickFilter(
            key = "movies",
            label = stringResource(R.string.movies),
            isSelected = uiState.selectedType == DiscoverType.MOVIES && uiState.selectedGenre == null && uiState.selectedCountry == null,
            onSelect = { viewModel.setDiscoverFilters(DiscoverType.MOVIES, null, null) }
        ),
        DiscoverQuickFilter(
            key = "shows",
            label = stringResource(R.string.tv_shows),
            isSelected = uiState.selectedType == DiscoverType.TV_SHOWS && uiState.selectedGenre == null && uiState.selectedCountry == null,
            onSelect = { viewModel.setDiscoverFilters(DiscoverType.TV_SHOWS, null, null) }
        ),
        DiscoverQuickFilter(
            key = "anime",
            label = "Anime",
            isSelected = uiState.selectedType == DiscoverType.ANIME && uiState.selectedGenre == null && uiState.selectedCountry == null,
            onSelect = { viewModel.setDiscoverFilters(DiscoverType.ANIME, null, null) }
        ),
        actionGenre?.let { genre ->
            DiscoverQuickFilter(
                key = "genre_${genre.id}",
                label = genre.name,
                isSelected = uiState.selectedGenre?.id == genre.id,
                onSelect = { viewModel.setDiscoverFilters(uiState.selectedType, genre, uiState.selectedCountry) }
            )
        },
        comedyGenre?.let { genre ->
            DiscoverQuickFilter(
                key = "genre_${genre.id}",
                label = genre.name,
                isSelected = uiState.selectedGenre?.id == genre.id,
                onSelect = { viewModel.setDiscoverFilters(uiState.selectedType, genre, uiState.selectedCountry) }
            )
        },
        horrorGenre?.let { genre ->
            DiscoverQuickFilter(
                key = "genre_${genre.id}",
                label = genre.name,
                isSelected = uiState.selectedGenre?.id == genre.id,
                onSelect = { viewModel.setDiscoverFilters(uiState.selectedType, genre, uiState.selectedCountry) }
            )
        },
        sciFiGenre?.let { genre ->
            DiscoverQuickFilter(
                key = "genre_${genre.id}",
                label = genre.name,
                isSelected = uiState.selectedGenre?.id == genre.id,
                onSelect = { viewModel.setDiscoverFilters(uiState.selectedType, genre, uiState.selectedCountry) }
            )
        },
        japaneseCountry?.let { country ->
            DiscoverQuickFilter(
                key = "country_${country.code}",
                label = country.name,
                isSelected = uiState.selectedCountry?.code == country.code,
                onSelect = { viewModel.setDiscoverFilters(uiState.selectedType, uiState.selectedGenre, country) }
            )
        },
        koreanCountry?.let { country ->
            DiscoverQuickFilter(
                key = "country_${country.code}",
                label = country.name,
                isSelected = uiState.selectedCountry?.code == country.code,
                onSelect = { viewModel.setDiscoverFilters(uiState.selectedType, uiState.selectedGenre, country) }
            )
        },
        hindiCountry?.let { country ->
            DiscoverQuickFilter(
                key = "country_${country.code}",
                label = country.name,
                isSelected = uiState.selectedCountry?.code == country.code,
                onSelect = { viewModel.setDiscoverFilters(uiState.selectedType, uiState.selectedGenre, country) }
            )
        }
    )
    val searchPreview = remember(
        activeCategories,
        currentRowIndex,
        currentItemIndex,
        uiState.aiResults,
        hasAiResults,
        catalogRowOffset
    ) {
        resolveSearchPreview(
            categories = activeCategories,
            currentRowIndex = currentRowIndex - catalogRowOffset,
            currentItemIndex = currentItemIndex,
            aiResults = uiState.aiResults,
            hasAiResults = hasAiResults
        )
    }
    val focusedMediaResult = remember(
        uiState.query,
        uiState.isAiSearch,
        focusZone,
        currentRowIndex,
        currentItemIndex,
        catalogRowPresent,
        catalogRowOffset,
        activeCategories
    ) {
        if (uiState.query.isBlank() || uiState.isAiSearch || focusZone != FocusZone.RESULTS || hasAiResults) {
            null
        } else if (catalogRowPresent && currentRowIndex == 0) {
            null
        } else {
            activeCategories
                .getOrNull(currentRowIndex - catalogRowOffset)
                ?.items
                ?.getOrNull(currentItemIndex)
        }
    }

    LaunchedEffect(focusedMediaResult?.mediaType, focusedMediaResult?.id) {
        focusedMediaResult?.let(viewModel::focusStreamSummary)
    }

    LaunchedEffect(quickFilters.size) {
        focusedFilterIndex = focusedFilterIndex.coerceIn(0, (quickFilters.size - 1).coerceAtLeast(0))
    }
    LaunchedEffect(uiState.query, activeCategories.size, uiState.catalogResults.size, hasAiResults, catalogRowPresent) {
        currentRowIndex = currentRowIndex.coerceIn(0, (resultRowCount - 1).coerceAtLeast(0))
        val maxItem = if (catalogRowPresent && currentRowIndex == 0) {
            uiState.catalogResults.size - 1
        } else {
            (activeCategories.getOrNull(currentRowIndex - catalogRowOffset)?.items?.size ?: 1) - 1
        }
        currentItemIndex = currentItemIndex.coerceIn(0, maxItem.coerceAtLeast(0))
    }
    LaunchedEffect(uiState.selectedType, uiState.selectedGenre?.id, uiState.selectedCountry?.code) {
        currentRowIndex = 0
        currentItemIndex = 0
    }

    // LaunchedEffect to restore RESULTS focus when results become available
    LaunchedEffect(activeCategories, hasAiResults) {
        if ((resultRowCount > 0 || hasAiResults) && focusZone == FocusZone.SEARCH_INPUT && isSearchInputFocused.not()) {
            // If we have results and just returned from details, stay in search input but prepare for results
            // This prevents the "back to keyboard" issue when returning from details
        }
    }

    LaunchedEffect(isTouchDevice) {
        // FocusRequester can throw IllegalStateException if the target composable
        // hasn't been placed yet (e.g. zero-sized keyboard on cold start, or when
        // the screen is composed then immediately navigated away). Swallow that
        // specific case so it doesn't surface to the user as a crash — TalkBack
        // focus will re-claim on next frame.
        if (!isTouchDevice) runCatching { searchFocusRequester.requestFocus() }
        suppressSelectUntilMs = SystemClock.elapsedRealtime() + 150L
    }
    LaunchedEffect(isSearchEditing, searchEditRequestNonce) {
        if (isSearchEditing) {
            runCatching { textInputFocusRequester.requestFocus() }
            keyboardController?.show()
        }
    }

    val showFilters = uiState.query.isEmpty()

    // D-pad handler: manages zone transitions. FILTERS zone lets native focus handle Left/Right.
    val dpadModifier = if (!isTouchDevice) {
        Modifier.onPreviewKeyEvent { event ->
            if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
            val effectiveKey = when (event.key) {
                Key.DirectionLeft  -> if (isRtl) Key.DirectionRight else Key.DirectionLeft
                Key.DirectionRight -> if (isRtl) Key.DirectionLeft  else Key.DirectionRight
                else -> event.key
            }
            when (effectiveKey) {
                Key.Back, Key.Escape -> {
                    when (
                        resolveSearchBackAction(
                            focusZone = focusZone,
                            isEditing = isSearchEditing,
                            hasQuery = uiState.query.isNotBlank(),
                            showFilters = showFilters,
                            hasFilters = quickFilters.isNotEmpty()
                        )
                    ) {
                        SearchBackAction.HIDE_KEYBOARD -> {
                            isSearchEditing = false
                            keyboardController?.hide()
                            runCatching { searchFocusRequester.requestFocus() }
                        }
                        SearchBackAction.CLEAR_QUERY -> {
                            isSearchEditing = false
                            keyboardController?.hide()
                            viewModel.clearSearch()
                            focusZone = FocusZone.SEARCH_INPUT
                            runCatching { searchFocusRequester.requestFocus() }
                        }
                        SearchBackAction.MOVE_TO_FILTERS -> {
                            focusZone = FocusZone.FILTERS
                            focusedFilterIndex = focusedFilterIndex.coerceIn(0, (quickFilters.size - 1).coerceAtLeast(0))
                            try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                        }
                        SearchBackAction.MOVE_TO_SEARCH_INPUT -> {
                            isSearchEditing = false
                            keyboardController?.hide()
                            focusZone = FocusZone.SEARCH_INPUT
                            runCatching { searchFocusRequester.requestFocus() }
                        }
                        SearchBackAction.MOVE_TO_SIDEBAR -> {
                            isSearchEditing = false
                            keyboardController?.hide()
                            focusZone = FocusZone.SIDEBAR
                        }
                        SearchBackAction.EXIT -> {
                            isSearchEditing = false
                            keyboardController?.hide()
                            if (uiState.query.isNotBlank()) viewModel.clearSearch()
                            onBack()
                        }
                    }
                    true
                }
                Key.DirectionUp -> when (focusZone) {
                    FocusZone.SIDEBAR -> true
                    FocusZone.SEARCH_INPUT -> {
                        isSearchEditing = false
                        keyboardController?.hide()
                        focusZone = FocusZone.SIDEBAR
                        true
                    }
                    FocusZone.FILTERS -> { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false // AI grid: let native focus handle navigation
                        else if (currentRowIndex > 0) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            currentRowIndex--
                            currentItemIndex = 0
                            true
                        }
                        else if (showFilters && quickFilters.isNotEmpty()) {
                            focusZone = FocusZone.FILTERS
                            focusedFilterIndex = focusedFilterIndex.coerceIn(0, (quickFilters.size - 1).coerceAtLeast(0))
                            try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                            true
                        }
                        else { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    }
                }
                Key.DirectionDown -> when (focusZone) {
                    FocusZone.SIDEBAR -> { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    FocusZone.SEARCH_INPUT -> {
                        isSearchEditing = false
                        keyboardController?.hide()
                        if (showFilters && quickFilters.isNotEmpty()) {
                            focusZone = FocusZone.FILTERS
                            focusedFilterIndex = focusedFilterIndex.coerceIn(0, (quickFilters.size - 1).coerceAtLeast(0))
                            try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                        }
                        else if (resultRowCount > 0 || hasAiResults) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            focusZone = FocusZone.RESULTS
                            currentRowIndex = 0
                            currentItemIndex = 0
                        }
                        true
                    }
                    FocusZone.FILTERS -> {
                        if (resultRowCount > 0 || hasAiResults) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            focusZone = FocusZone.RESULTS
                            currentRowIndex = 0
                            currentItemIndex = 0
                        }
                        true
                    }
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false // AI grid: let native focus handle navigation
                        else if (currentRowIndex < resultRowCount - 1) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            currentRowIndex++
                            currentItemIndex = 0
                            true
                        }
                        else true
                    }
                }
                Key.DirectionLeft -> when (focusZone) {
                    FocusZone.SIDEBAR -> { if (sidebarFocusIndex > 0) sidebarFocusIndex--; true }
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false else {
                            if (currentItemIndex > 0) {
                                resultsLastNavEventTime = SystemClock.elapsedRealtime()
                                currentItemIndex--
                            }
                            true
                        }
                    }
                    FocusZone.FILTERS -> {
                        if (focusedFilterIndex > 0) {
                            focusedFilterIndex--
                        } else {
                            focusZone = FocusZone.SEARCH_INPUT
                            runCatching { searchFocusRequester.requestFocus() }
                        }
                        true
                    }
                    else -> false
                }
                Key.DirectionRight -> when (focusZone) {
                    FocusZone.SIDEBAR -> { if (sidebarFocusIndex < maxSidebarIndex) sidebarFocusIndex++; true }
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false // AI grid: let native focus handle navigation
                        else {
                            val maxItem = if (catalogRowPresent && currentRowIndex == 0) {
                                uiState.catalogResults.size - 1
                            } else {
                                val cats = activeCategories.filter { it.items.isNotEmpty() }
                                (cats.getOrNull(currentRowIndex - catalogRowOffset)?.items?.size ?: 1) - 1
                            }
                            if (currentItemIndex < maxItem) {
                                resultsLastNavEventTime = SystemClock.elapsedRealtime()
                                currentItemIndex++
                            }
                            true
                        }
                    }
                    FocusZone.FILTERS -> {
                        if (focusedFilterIndex < quickFilters.size - 1) {
                            focusedFilterIndex++
                        }
                        true
                    }
                    else -> false
                }
                Key.Enter, Key.DirectionCenter -> {
                    when (focusZone) {
                        FocusZone.SIDEBAR -> {
                            when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) {
                                SidebarItem.SEARCH -> Unit
                                SidebarItem.HOME -> onNavigateToHome()
                                SidebarItem.DISCOVER -> onNavigateToDiscover()
                                SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                SidebarItem.SETTINGS -> onNavigateToSettings()
                                null -> Unit
                            }
                            true
                        }
                        FocusZone.SEARCH_INPUT -> {
                            focusZone = FocusZone.SEARCH_INPUT
                            isSearchEditing = true
                            searchEditRequestNonce++
                            true
                        }
                        FocusZone.FILTERS -> {
                            quickFilters.getOrNull(focusedFilterIndex)?.onSelect?.invoke()
                            true
                        }
                        FocusZone.RESULTS -> {
                            if (hasAiResults) false
                            else {
                                // Use stable category lookup to avoid race condition with dynamic list updates
                                if (catalogRowPresent && currentRowIndex == 0) {
                                    uiState.catalogResults.getOrNull(currentItemIndex)?.let { onNavigateToCatalog(it.catalogId) }
                                } else {
                                    val cats = activeCategories.filter { it.items.isNotEmpty() }
                                    val item = cats.getOrNull(currentRowIndex - catalogRowOffset)?.items?.getOrNull(currentItemIndex)
                                    if (item != null) onNavigateToDetails(item.mediaType, item.id)
                                }
                                true
                            }
                        }
                    }
                }
                else -> false
            }
        }
    } else Modifier

    Box(modifier = Modifier.fillMaxSize().background(appBackgroundDark()).then(dpadModifier)) {
        SearchAmbientBackground(preview = searchPreview)
        if (!isTouchDevice) AppTopBar(selectedItem = SidebarItem.SEARCH, isFocused = focusZone == FocusZone.SIDEBAR, focusedIndex = sidebarFocusIndex)

        Column(modifier = Modifier.fillMaxSize().padding(top = if (isTouchDevice) 16.dp else AppTopBarContentTopInset).padding(horizontal = if (isTouchDevice) 12.dp else if (isCompactHeight) 20.dp else 28.dp)) {
            // ── Search Bar ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = if (isTouchDevice) 0.dp else 22.dp,
                        end = if (isTouchDevice) 0.dp else 22.dp,
                        bottom = if (isCompactHeight) 8.dp else 12.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchInputBar(
                    query = uiState.query,
                    searchBarWidth = searchBarWidth,
                    isTouchDevice = isTouchDevice,
                    isFocused = focusZone == FocusZone.SEARCH_INPUT || isSearchEditing,
                    isEditing = isSearchEditing,
                    searchFocusRequester = searchFocusRequester,
                    textInputFocusRequester = textInputFocusRequester,
                    onQueryChange = { viewModel.updateQuery(it) },
                    onSearch = {
                        viewModel.search()
                        keyboardController?.hide()
                        isSearchEditing = false
                    },
                    onFocused = {
                        focusZone = FocusZone.SEARCH_INPUT
                        isSearchInputFocused = true
                    },
                    onFocusLost = { isSearchInputFocused = false },
                    onStartEditing = {
                        focusZone = FocusZone.SEARCH_INPUT
                        isSearchEditing = true
                        searchEditRequestNonce++
                    },
                    onMoveUp = {
                        isSearchEditing = false
                        keyboardController?.hide()
                        focusZone = FocusZone.SIDEBAR
                    },
                    onMoveDown = {
                        isSearchEditing = false
                        keyboardController?.hide()
                        if (showFilters && quickFilters.isNotEmpty()) {
                            focusZone = FocusZone.FILTERS
                            focusedFilterIndex = 0
                            runCatching { filtersFocusRequester.requestFocus() }
                        } else if (resultRowCount > 0 || hasAiResults) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            focusZone = FocusZone.RESULTS
                            currentRowIndex = 0
                            currentItemIndex = 0
                        }
                    }
                )
                if (!isTouchDevice) {
                    Spacer(Modifier.width(if (isCompactHeight) 18.dp else 24.dp))
                    SearchPreviewHeader(
                        preview = searchPreview,
                        modifier = Modifier
                            .weight(1f)
                            .height(if (isCompactHeight) 104.dp else 124.dp)
                    )
                }
            }

            // ── Filter Chips (discover mode) - focusable with D-pad ──
            if (showFilters) {
                DiscoverFilterStrip(
                    filters = quickFilters,
                    focusZone = focusZone,
                    focusedFilterIndex = focusedFilterIndex,
                    filtersFocusRequester = filtersFocusRequester,
                    isTouchDevice = isTouchDevice,
                    isRtl = isRtl,
                    onFocused = { index ->
                        focusZone = FocusZone.FILTERS
                        focusedFilterIndex = index
                    },
                    onMoveUp = {
                        focusZone = FocusZone.SEARCH_INPUT
                        runCatching { searchFocusRequester.requestFocus() }
                    },
                    onMoveDown = {
                        if (resultRowCount > 0 || hasAiResults) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            focusZone = FocusZone.RESULTS
                            currentRowIndex = 0
                            currentItemIndex = 0
                        }
                    },
                    onMoveLeft = {
                        if (focusedFilterIndex > 0) {
                            focusedFilterIndex--
                        } else {
                            focusZone = FocusZone.SEARCH_INPUT
                            runCatching { searchFocusRequester.requestFocus() }
                        }
                    },
                    onMoveRight = {
                        if (focusedFilterIndex < quickFilters.size - 1) {
                            focusedFilterIndex++
                        }
                    }
                )
            }

            // ── Content ──
            when {
                uiState.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 48.dp) }

                hasAiResults -> {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)) {
                        Icon(Icons.Default.AutoAwesome, null, tint = AccentGreen, modifier = Modifier.size(16.dp)); Spacer(Modifier.width(6.dp))
                        Text(uiState.aiInterpretation ?: "", style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = FontWeight.Medium), color = Color.White.copy(alpha = 0.85f))
                    }
                    ContentGrid(items = uiState.aiResults, usePosterCards = aiUsePosterCards, isLoading = false, isTouchDevice = isTouchDevice, onItemClick = { onNavigateToDetails(it.mediaType, it.id) }, onLoadMore = {})
                }

                uiState.query.isNotEmpty() && !uiState.isAiSearch && !hasSearchResults -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("${stringResource(R.string.no_results_for)} \"${uiState.query}\"", style = ArflixTypography.body, color = TextSecondary) }
                }

                uiState.isDiscoverLoading && activeCategories.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 48.dp) }

                resultRowCount > 0 -> {
                    // Row-based content (discover rows or search results) - HomeScreen pattern
                    Column(Modifier.fillMaxSize()) {
                        if (catalogRowPresent) {
                            CatalogResultsRow(
                                catalogs = uiState.catalogResults,
                                currentItemIndex = currentItemIndex,
                                isFocused = focusZone == FocusZone.RESULTS && currentRowIndex == 0,
                                isTouchDevice = isTouchDevice,
                                onCatalogClick = { onNavigateToCatalog(it.catalogId) }
                            )
                        }
                        if (activeCategories.isNotEmpty()) {
                            RowsLayer(
                                categories = activeCategories,
                                cardLogoUrls = activeLogoUrls,
                                streamSummaryStates = if (uiState.query.isNotBlank()) uiState.streamSummaryStates else emptyMap(),
                                currentRowIndex = (currentRowIndex - catalogRowOffset).coerceAtLeast(0),
                                currentItemIndex = currentItemIndex,
                                lastNavEventTime = resultsLastNavEventTime,
                                fastScrollThresholdMs = fastScrollThresholdMs,
                                isFocused = focusZone == FocusZone.RESULTS && !(catalogRowPresent && currentRowIndex == 0),
                                isTouchDevice = isTouchDevice,
                                onItemClick = { onNavigateToDetails(it.mediaType, it.id) }
                            )
                        }
                    }
                }
            }
        }

    }
}

// ── Glow Chip ───────────────────────────────────────────────────────────────

private data class DiscoverQuickFilter(
    val key: String,
    val label: String,
    val isSelected: Boolean,
    val onSelect: () -> Unit
)

@Composable
private fun SearchAmbientBackground(preview: SearchPreviewState?) {
    val imageUrl = preview?.backgroundImageUrl

    Box(Modifier.fillMaxSize().background(appBackgroundDark())) {
        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = 0.42f }
            )
        }
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            appBackgroundDark().copy(alpha = 0.96f),
                            appBackgroundDark().copy(alpha = 0.74f),
                            appBackgroundDark().copy(alpha = 0.36f)
                        )
                    )
                )
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            appBackgroundDark().copy(alpha = 0.68f),
                            appBackgroundDark().copy(alpha = 0.20f),
                            appBackgroundDark().copy(alpha = 0.94f)
                        )
                    )
                )
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SearchPreviewHeader(
    preview: SearchPreviewState?,
    modifier: Modifier = Modifier
) {
    if (preview == null) {
        Spacer(modifier)
        return
    }

    val item = preview.focusedItem
    val posterUrl = item.image.takeIf { it.isNotBlank() } ?: preview.backgroundImageUrl
    val mediaLabel = when (item.mediaType) {
        MediaType.TV -> stringResource(R.string.series)
        MediaType.MOVIE -> stringResource(R.string.movie)
    }
    val rating = item.imdbRating.takeIf { it.isNotBlank() }
        ?: item.rating.takeIf { it.isNotBlank() }
        ?: item.tmdbRating.takeIf { it.isNotBlank() }

    val metaParts = mutableListOf<String>()
    if (preview.isPersonResult) {
        metaParts += "Person"
    } else {
        metaParts += mediaLabel
    }
    item.year.takeIf { it.isNotBlank() }?.let { metaParts += it }
    if (!preview.isPersonResult) {
        item.duration.takeIf { it.isNotBlank() }?.let { metaParts += it }
    }
    val metadata = metaParts.joinToString(" / ")
    val supportingText = if (preview.isPersonResult) {
        buildString {
            append("Known for ")
            append(item.title)
            if (item.character.isNotBlank()) {
                append(" as ")
                append(item.character)
            }
        }
    } else {
        item.overview.takeIf { it.isNotBlank() } ?: item.subtitle.takeIf { it.isNotBlank() }
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.11f),
                        Color.White.copy(alpha = 0.055f),
                        Color.White.copy(alpha = 0.015f)
                    )
                )
            )
            .border(1.dp, Color.White.copy(alpha = 0.13f), RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(96.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(7.dp))
                .background(Color.White.copy(alpha = 0.08f))
        ) {
            if (!posterUrl.isNullOrBlank()) {
                AsyncImage(
                    model = posterUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp, end = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                preview.displayTitle,
                style = ArflixTypography.sectionTitle.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(5.dp))
            Text(
                metadata,
                style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.Medium),
                color = Color.White.copy(alpha = 0.72f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (!supportingText.isNullOrBlank()) {
                Spacer(Modifier.height(7.dp))
                Text(
                    supportingText,
                    style = ArflixTypography.body.copy(fontSize = 13.sp, lineHeight = 17.sp),
                    color = Color.White.copy(alpha = 0.72f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        if (!rating.isNullOrBlank()) {
            Box(
                modifier = Modifier
                    .align(Alignment.Top)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.Black.copy(alpha = 0.36f))
                    .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 9.dp, vertical = 5.dp)
            ) {
                Text(
                    "IMDb $rating",
                    style = ArflixTypography.caption.copy(fontSize = 11.sp, fontWeight = FontWeight.SemiBold),
                    color = Color.White.copy(alpha = 0.88f),
                    maxLines = 1
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SearchInputBar(
    query: String,
    searchBarWidth: Dp,
    isTouchDevice: Boolean,
    isFocused: Boolean,
    isEditing: Boolean,
    searchFocusRequester: FocusRequester,
    textInputFocusRequester: FocusRequester,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onFocused: () -> Unit,
    onFocusLost: () -> Unit,
    onStartEditing: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit
) {
    if (isTouchDevice) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text(stringResource(R.string.search), style = ArflixTypography.body, color = TextSecondary) },
            leadingIcon = { Icon(Icons.Default.Search, null, tint = if (isFocused) Pink else TextSecondary, modifier = Modifier.size(22.dp)) },
            textStyle = ArflixTypography.body.copy(color = TextPrimary),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            colors = TextFieldDefaults.colors(
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                cursorColor = Color.White,
                focusedContainerColor = BackgroundCard.copy(alpha = 0.82f),
                unfocusedContainerColor = BackgroundCard.copy(alpha = 0.66f),
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.18f)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(searchFocusRequester)
                .onFocusChanged {
                    if (it.isFocused) onFocused() else onFocusLost()
                }
        )
        return
    }

    val shape = rememberArvioCardShape(10.dp)
    ArvioFocusableSurface(
        modifier = Modifier
            .width(searchBarWidth)
            .height(50.dp)
            .onPreviewKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                when (event.key) {
                    Key.DirectionUp -> { onMoveUp(); true }
                    Key.DirectionDown -> { onMoveDown(); true }
                    Key.Enter, Key.DirectionCenter -> { onStartEditing(); true }
                    else -> false
                }
            }
            .focusRequester(searchFocusRequester),
        shape = shape,
        backgroundColor = Color.White.copy(alpha = if (isFocused) 0.105f else 0.052f),
        outlineColor = Color.White,
        outlineWidth = if (isFocused) 2.dp else 1.dp,
        glowWidth = if (isFocused) 1.dp else 0.dp,
        glowAlpha = 0.18f,
        focusedScale = 1f,
        pressedScale = 0.985f,
        useSystemFocusForVisuals = false,
        isFocusedOverride = isFocused,
        onClick = onStartEditing,
        onFocusChanged = { if (it) onFocused() else onFocusLost() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, null, tint = if (isFocused) Color.White else TextSecondary, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(12.dp))
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                readOnly = !isEditing,
                textStyle = ArflixTypography.body.copy(color = TextPrimary, fontSize = 16.sp),
                cursorBrush = SolidColor(Color.White),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSearch() }),
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(textInputFocusRequester),
                decorationBox = { inner ->
                    if (query.isEmpty()) {
                        Text(
                            stringResource(R.string.search),
                            style = ArflixTypography.body.copy(fontSize = 16.sp),
                            color = Color.White.copy(alpha = 0.32f)
                        )
                    }
                    inner()
                }
            )
        }
    }
}

@Composable
private fun DiscoverFilterStrip(
    filters: List<DiscoverQuickFilter>,
    focusZone: FocusZone,
    focusedFilterIndex: Int,
    filtersFocusRequester: FocusRequester,
    isTouchDevice: Boolean,
    isRtl: Boolean = false,
    onFocused: (Int) -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onMoveLeft: () -> Unit,
    onMoveRight: () -> Unit
) {
    if (filters.isEmpty()) return
    val rowState = rememberLazyListState()

    LaunchedEffect(focusedFilterIndex, filters.size) {
        val target = focusedFilterIndex.coerceIn(0, (filters.size - 1).coerceAtLeast(0))
        val first = rowState.firstVisibleItemIndex
        val last = rowState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: first
        if (target < first || target > last - 1) {
            rowState.animateScrollToItem(target)
        }
    }

    LazyRow(
        state = rowState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = if (isTouchDevice) 10.dp else 8.dp)
            .onPreviewKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                when (event.key) {
                    Key.DirectionUp -> { onMoveUp(); true }
                    Key.DirectionDown -> { onMoveDown(); true }
                    Key.DirectionLeft -> { if (isRtl) onMoveRight() else onMoveLeft(); true }
                    Key.DirectionRight -> { if (isRtl) onMoveLeft() else onMoveRight(); true }
                    Key.Enter, Key.DirectionCenter -> false
                    else -> false
                }
            }
            .arvioDpadFocusGroup(),
        horizontalArrangement = Arrangement.spacedBy(if (isTouchDevice) 7.dp else 8.dp),
        contentPadding = PaddingValues(
            start = if (isTouchDevice) 0.dp else 22.dp,
            end = if (isTouchDevice) 6.dp else 40.dp,
            top = 2.dp,
            bottom = 5.dp
        )
    ) {
        itemsIndexed(filters, key = { _, filter -> filter.key }) { index, filter ->
            GlowChip(
                label = filter.label,
                isSelected = filter.isSelected,
                isVisuallyFocused = !isTouchDevice && focusZone == FocusZone.FILTERS && focusedFilterIndex == index,
                modifier = if (index == 0) Modifier.focusRequester(filtersFocusRequester) else Modifier,
                onFocused = { onFocused(index) },
                useSystemFocusForVisuals = isTouchDevice,
                onSelect = filter.onSelect
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GlowChip(
    label: String,
    isSelected: Boolean,
    isVisuallyFocused: Boolean = false,
    modifier: Modifier = Modifier,
    onFocused: () -> Unit = {},
    useSystemFocusForVisuals: Boolean = true,
    onSelect: () -> Unit
) {
    var systemFocused by remember { mutableStateOf(false) }
    val focused = isVisuallyFocused || (useSystemFocusForVisuals && systemFocused)
    val active = focused || isSelected
    val chipShape = RoundedCornerShape(6.dp)
    val accentColor = resolveAccentColor(fallback = Color.White)
    val backgroundColor = when {
        focused -> Color.White.copy(alpha = 0.11f)
        isSelected -> Color.White.copy(alpha = 0.88f)
        else -> Color.White.copy(alpha = 0.062f)
    }
    val borderColor = when {
        focused -> accentColor
        isSelected -> Color.White.copy(alpha = 0.88f)
        else -> Color.White.copy(alpha = 0.18f)
    }
    Box(
        modifier = modifier
            .padding(vertical = 2.dp)
            .background(
                color = backgroundColor,
                shape = chipShape
            )
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = borderColor,
                shape = chipShape
            )
            .clickable { onSelect() }
            .onFocusChanged {
                systemFocused = it.isFocused
                if (it.isFocused) onFocused()
            }
            .focusable()
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            label,
            style = ArflixTypography.caption.copy(
                fontSize = 11.sp,
                fontWeight = if (active) FontWeight.SemiBold else FontWeight.Medium
            ),
            color = when {
                focused -> Color.White                // White text on dark bg
                isSelected -> Color.Black             // Black text on bright bg
                else -> Color.White.copy(alpha = 0.84f)
            },
            maxLines = 1
        )
    }
}

// ── Rows Layer (HomeScreen pattern - manual focus, smooth scroll) ────────────

@Composable
private fun CatalogResultsRow(
    catalogs: List<SearchCatalogResult>,
    currentItemIndex: Int,
    isFocused: Boolean,
    isTouchDevice: Boolean,
    onCatalogClick: (SearchCatalogResult) -> Unit
) {
    val itemWidth = if (isTouchDevice) 188.dp else 238.dp
    val rowState = rememberLazyListState()
    val focusBleedPadding = if (isTouchDevice) 14.dp else 22.dp

    LaunchedEffect(isFocused, currentItemIndex) {
        if (!isFocused) return@LaunchedEffect
        val safeIndex = currentItemIndex.coerceIn(0, (catalogs.size - 1).coerceAtLeast(0))
        val first = rowState.firstVisibleItemIndex
        val visibleItems = rowState.layoutInfo.visibleItemsInfo
        val last = visibleItems.lastOrNull()?.index ?: first
        if (safeIndex < first || safeIndex > last) {
            rowState.animateScrollToItem(safeIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isTouchDevice) 150.dp else 168.dp)
    ) {
        Text(
            text = "Kataloger och tjänster",
            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp),
            color = Color.White.copy(alpha = if (isFocused) 0.9f else 0.62f),
            modifier = Modifier.padding(start = focusBleedPadding, top = 4.dp, bottom = 8.dp)
        )
        LazyRow(
            state = rowState,
            contentPadding = PaddingValues(start = focusBleedPadding, end = itemWidth),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .arvioDpadFocusGroup()
        ) {
            itemsIndexed(catalogs, key = { _, catalog -> catalog.catalogId }) { index, catalog ->
                CatalogSearchCard(
                    catalog = catalog,
                    width = itemWidth,
                    isFocused = isFocused && index == currentItemIndex,
                    isTouchDevice = isTouchDevice,
                    onClick = { onCatalogClick(catalog) }
                )
            }
        }
    }
}

@Composable
private fun CatalogSearchCard(
    catalog: SearchCatalogResult,
    width: Dp,
    isFocused: Boolean,
    isTouchDevice: Boolean,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(8.dp)
    val borderColor = if (isFocused) Color.White.copy(alpha = 0.95f) else Color.White.copy(alpha = 0.10f)
    val bgColor = if (isFocused) Color.White.copy(alpha = 0.12f) else BackgroundCard.copy(alpha = 0.72f)
    Box(
        modifier = Modifier
            .width(width)
            .height(if (isTouchDevice) 104.dp else 116.dp)
            .clip(shape)
            .background(bgColor)
            .border(if (isFocused) 2.dp else 1.dp, borderColor, shape)
            .then(if (isTouchDevice) Modifier.clickable { onClick() } else Modifier)
    ) {
        if (!catalog.imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = catalog.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { alpha = if (isFocused) 0.42f else 0.24f }
            )
        }
        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            appBackgroundDark().copy(alpha = 0.88f),
                            appBackgroundDark().copy(alpha = 0.54f)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = catalog.title,
                    style = ArflixTypography.body.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = catalog.subtitle,
                    style = ArflixTypography.caption.copy(fontSize = 11.sp),
                    color = Color.White.copy(alpha = 0.72f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = catalog.sourceLabel,
                style = ArflixTypography.caption.copy(fontSize = 10.sp, fontWeight = FontWeight.SemiBold),
                color = AccentGreen.copy(alpha = if (isFocused) 1f else 0.82f),
                maxLines = 1
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun RowsLayer(
    categories: List<Category>, cardLogoUrls: Map<String, String>,
    streamSummaryStates: Map<String, SearchStreamSummaryState>,
    currentRowIndex: Int, currentItemIndex: Int,
    lastNavEventTime: Long,
    fastScrollThresholdMs: Long,
    isFocused: Boolean,
    isTouchDevice: Boolean,
    onItemClick: (MediaItem) -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenHeight = configuration.screenHeightDp

    val focusBleedPadding = if (isTouchDevice) 14.dp else 22.dp

    val listState = rememberLazyListState()
    var lastAppliedTargetIndex by remember { mutableIntStateOf(-1) }
    val targetIndex = currentRowIndex.coerceIn(0, (categories.size - 1).coerceAtLeast(0))

    // Only move the results viewport in response to actual D-pad navigation.
    // Search result rows update frequently while typing/loading, and snapping the
    // LazyColumn on every target change makes the screen feel unstable.
    LaunchedEffect(targetIndex, lastNavEventTime) {
        val currentFirst = listState.firstVisibleItemIndex
        val initialPlacement = lastAppliedTargetIndex < 0
        if (currentFirst == targetIndex) {
            lastAppliedTargetIndex = targetIndex
            return@LaunchedEffect
        }

        val recentUserNav = lastNavEventTime > 0L &&
            (SystemClock.elapsedRealtime() - lastNavEventTime) <= fastScrollThresholdMs
        if (!initialPlacement && !recentUserNav) return@LaunchedEffect

        val jump = kotlin.math.abs(targetIndex - currentFirst)
        if (!initialPlacement && jump <= 5) {
            listState.animateScrollToItem(targetIndex)
        } else {
            listState.scrollToItem(targetIndex)
        }
        lastAppliedTargetIndex = targetIndex
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(top = focusBleedPadding / 2, bottom = maxHeight * 0.6f),
            modifier = Modifier.fillMaxSize().arvioDpadFocusGroup(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(categories.size, key = { categories[it].id }) { index ->
                val category = categories[index]
                val isCurrentRow = isFocused && index == currentRowIndex
                val rowKey = remember(category.id) { "search:${category.id}" }
                val rowUsePosterCards = rememberCatalogueRowLayoutMode(rowKey) == CardLayoutMode.POSTER
                val isPortrait = category.isPortrait(rowUsePosterCards)
                val itemWidth = if (isTouchDevice) {
                    if (isPortrait) 110.dp else 170.dp
                } else {
                    if (isPortrait) 105.dp else 210.dp
                }
                val baseRowHeight = if (isTouchDevice) {
                    if (isPortrait) 260.dp else 190.dp
                } else if (isPortrait) {
                    // Poster cards (2:3) need extra vertical room for title + date below the image
                    if (screenHeight <= 640) 271.dp else 309.dp
                } else {
                    // Landscape cards still render title + subtitle below artwork.
                    if (screenHeight <= 640) 210.dp else 274.dp
                }
                val rowHeight = baseRowHeight + focusBleedPadding
                // Fade non-current rows
                val rowAlpha by animateFloatAsState(
                    targetValue = if (!isFocused || index <= currentRowIndex) 1f else 0.3f,
                    animationSpec = tween(250), label = "rowAlpha"
                )

                Box(modifier = Modifier.fillMaxWidth().height(rowHeight).graphicsLayer { alpha = rowAlpha }) {
                    Column {
                        Row(
                            modifier = Modifier.padding(start = focusBleedPadding, bottom = 4.dp, top = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                category.title,
                                style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp),
                                color = Color.White.copy(alpha = if (isCurrentRow) 0.9f else 0.5f)
                            )
                        }

                        val rowState = rememberLazyListState()
                        var lastScrollIndex by remember(category.id) { mutableIntStateOf(-1) }
                        var lastScrollOffset by remember(category.id) { mutableIntStateOf(Int.MIN_VALUE) }
                        // Scroll to focused item in current row
                        LaunchedEffect(isCurrentRow, currentItemIndex, lastNavEventTime) {
                            if (!isCurrentRow) return@LaunchedEffect
                            val safeIndex = currentItemIndex.coerceIn(0, (category.items.size - 1).coerceAtLeast(0))
                            val first = rowState.firstVisibleItemIndex
                            val visibleItems = rowState.layoutInfo.visibleItemsInfo
                            val last = visibleItems.lastOrNull()?.index ?: first
                            val targetInfo = visibleItems.firstOrNull { it.index == safeIndex }
                            val targetOutsideViewport = safeIndex < first || safeIndex > last
                            val viewportEnd = rowState.layoutInfo.viewportEndOffset
                            val trailingPaddingPx = rowState.layoutInfo.afterContentPadding
                            val targetNearViewportEnd = targetInfo != null &&
                                targetInfo.offset + targetInfo.size > viewportEnd - trailingPaddingPx
                            val scrollTargetIndex = safeIndex
                            val extraOffset = if (targetNearViewportEnd) {
                                (with(density) { itemWidth.roundToPx() } * 0.35f).toInt()
                            } else 0

                            if (lastScrollIndex == scrollTargetIndex && lastScrollOffset == extraOffset) {
                                return@LaunchedEffect
                            }
                            if (lastScrollIndex == -1) {
                                rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
                                lastScrollIndex = scrollTargetIndex
                                lastScrollOffset = extraOffset
                                return@LaunchedEffect
                            }

                            val recentUserNav = lastNavEventTime > 0L &&
                                (SystemClock.elapsedRealtime() - lastNavEventTime) <= fastScrollThresholdMs
                            if (!recentUserNav) return@LaunchedEffect

                            val jumpDistance = kotlin.math.abs(scrollTargetIndex - first)
                            if (jumpDistance > 6) {
                                rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
                            } else if (scrollTargetIndex != first || targetOutsideViewport) {
                                rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
                            } else {
                                rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
                            }
                            lastScrollIndex = scrollTargetIndex
                            lastScrollOffset = extraOffset
                        }

                        LazyRow(
                            state = rowState,
                            modifier = Modifier.arvioDpadFocusGroup(),
                            contentPadding = PaddingValues(
                                start = focusBleedPadding,
                                end = itemWidth + 56.dp,
                                top = 8.dp,
                                bottom = focusBleedPadding + 12.dp
                            ),
                            horizontalArrangement = Arrangement.spacedBy(18.dp)
                        ) {
                            itemsIndexed(category.items, key = { _, item -> "${item.mediaType}_${item.id}" }) { itemIdx, item ->
                                val itemIsFocused = isCurrentRow && itemIdx == currentItemIndex
                                MediaCard(
                                    item = item.copy(
                                        title = buildCardTitle(item),
                                        subtitle = buildCardSubtitle(item, streamSummaryStates[searchStreamSummaryKey(item)]),
                                        releaseDate = null,
                                        year = ""
                                    ),
                                    width = itemWidth,
                                    isLandscape = !isPortrait,
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
    val itemWidth = if (usePosterCards) 105.dp else 210.dp
    val gridState = rememberLazyGridState()
    LaunchedEffect(gridState.firstVisibleItemIndex, items.size) { val lv = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0; if (items.isNotEmpty() && lv >= items.size - 8) onLoadMore() }

    val focusBleedPadding = if (isTouchDevice) 14.dp else 24.dp
    LazyVerticalGrid(state = gridState, columns = GridCells.Adaptive(minSize = itemWidth + focusBleedPadding), contentPadding = PaddingValues(horizontal = focusBleedPadding, vertical = focusBleedPadding),
        horizontalArrangement = Arrangement.spacedBy(18.dp), verticalArrangement = Arrangement.spacedBy(26.dp), modifier = Modifier.fillMaxSize().arvioDpadFocusGroup()) {
        items(items.size, key = { "${items[it].mediaType}_${items[it].id}" }) { idx ->
            val item = items[idx]
            MediaCard(item = item.copy(
                title = buildCardTitle(item),
                subtitle = buildCardSubtitle(item),
                releaseDate = null,
                year = ""
            ),
                width = itemWidth, isLandscape = !usePosterCards, showProgress = false, titleMaxLines = 2, subtitleMaxLines = 1,
                isFocusedOverride = false, enableSystemFocus = true, onFocused = {}, onClick = { onItemClick(item) },
                modifier = if (isTouchDevice) Modifier.clickable { onItemClick(item) } else Modifier)
        }
        if (isLoading) { item { Box(Modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 32.dp) } } }
    }
}

private fun buildCardTitle(item: MediaItem): String {
    // Return the clean title — year is shown separately in the subtitle
    return item.title
}

@Composable
private fun buildCardSubtitle(item: MediaItem, streamSummaryState: SearchStreamSummaryState? = null): String {
    val mediaLabel = when (item.mediaType) {
        MediaType.TV -> stringResource(R.string.series)
        MediaType.MOVIE -> stringResource(R.string.movie)
    }
    val year = item.year.takeIf { it.isNotBlank() }
    val base = if (year != null) "$mediaLabel · $year" else mediaLabel
    val streamLabel = searchStreamSummaryLabel(streamSummaryState)
    return if (streamLabel.isNullOrBlank()) base else "$base · $streamLabel"
}

private fun interleaveSearchResults(movies: List<MediaItem>, shows: List<MediaItem>): List<MediaItem> {
    val combined = ArrayList<MediaItem>(movies.size + shows.size)
    val maxSize = maxOf(movies.size, shows.size)
    for (index in 0 until maxSize) {
        if (index < movies.size) combined.add(movies[index])
        if (index < shows.size) combined.add(shows[index])
    }
    return combined.distinctBy { "${it.mediaType}_${it.id}" }
}

internal enum class FocusZone { SIDEBAR, SEARCH_INPUT, FILTERS, RESULTS }

internal enum class SearchBackAction {
    HIDE_KEYBOARD,
    CLEAR_QUERY,
    MOVE_TO_FILTERS,
    MOVE_TO_SEARCH_INPUT,
    MOVE_TO_SIDEBAR,
    EXIT
}

internal fun resolveSearchBackAction(
    focusZone: FocusZone,
    isEditing: Boolean,
    hasQuery: Boolean,
    showFilters: Boolean,
    hasFilters: Boolean
): SearchBackAction {
    if (isEditing) return SearchBackAction.HIDE_KEYBOARD

    return when (focusZone) {
        FocusZone.RESULTS -> {
            if (showFilters && hasFilters) SearchBackAction.MOVE_TO_FILTERS
            else SearchBackAction.MOVE_TO_SEARCH_INPUT
        }
        FocusZone.FILTERS -> SearchBackAction.MOVE_TO_SEARCH_INPUT
        FocusZone.SEARCH_INPUT -> {
            if (hasQuery) SearchBackAction.CLEAR_QUERY else SearchBackAction.MOVE_TO_SIDEBAR
        }
        FocusZone.SIDEBAR -> SearchBackAction.EXIT
    }
}
