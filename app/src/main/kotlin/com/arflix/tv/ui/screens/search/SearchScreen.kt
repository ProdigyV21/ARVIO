package com.arflix.tv.ui.screens.search

import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.ui.components.LocalBottomBarHeight
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlinx.coroutines.CancellationException
import androidx.activity.compose.BackHandler
import android.content.res.Configuration
import android.os.SystemClock
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
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
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.ui.theme.AccentGreen
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType

/**
 * Display-only localization of the five discover row titles built in
 * `SearchViewModel.buildRow`. The English title stays in [Category.title] because
 * it is part of the category id used as the row's focus key — only the rendered
 * label is translated. Same approach as `liveCategoryLabel` in `LiveCategory.kt`.
 */
@Composable
private fun localizedDiscoverRowTitle(category: Category): String = when (category.title) {
    "Trending" -> stringResource(R.string.search_row_trending)
    "Popular This Year" -> stringResource(R.string.search_row_popular_this_year)
    "Top Rated" -> stringResource(R.string.search_row_top_rated)
    "New Releases" -> stringResource(R.string.search_row_new_releases)
    "Hidden Gems" -> stringResource(R.string.search_row_hidden_gems)
    else -> category.title
}

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalLayoutApi::class)
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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val aiUsePosterCards = rememberCatalogueRowLayoutMode("search:ai") == CardLayoutMode.POSTER
    val configuration = LocalConfiguration.current
    val isCompactHeight = configuration.screenHeightDp <= 780
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val searchBarWidth = if (isTouchDevice) configuration.screenWidthDp.dp - 32.dp
        else (configuration.screenWidthDp.dp * 0.48f).coerceIn(460.dp, 680.dp)

    val hasSearchResults = uiState.movieResults.isNotEmpty() || uiState.tvResults.isNotEmpty() || uiState.personResults.isNotEmpty()
    val hasAiResults = uiState.isAiSearch && uiState.aiResults.isNotEmpty()
    val searchTopResults = uiState.results

    // Determine which categories to show in rows (filter out empty ones)
    val activeCategories: List<Category> = when {
        hasSearchResults -> {
            val list = mutableListOf<Category>()
            if (searchTopResults.isNotEmpty()) list.add(Category("s_all", "${stringResource(R.string.search)} (${searchTopResults.size})", searchTopResults))
            if (uiState.movieResults.isNotEmpty()) list.add(Category("s_m", "${stringResource(R.string.movies)} (${uiState.movieResults.size})", uiState.movieResults))
            if (uiState.tvResults.isNotEmpty()) list.add(Category("s_t", "${stringResource(R.string.tv_shows)} (${uiState.tvResults.size})", uiState.tvResults))
            list.addAll(uiState.personResults)
            list
        }
        uiState.query.isEmpty() -> uiState.discoverCategories.filter { it.items.isNotEmpty() }
        else -> emptyList()
    }
    val gridItems = uiState.discoverGridItems
    val activeLogoUrls: Map<String, String> = when {
        hasSearchResults -> uiState.cardLogoUrls
        else -> uiState.discoverLogoUrls
    }

    var focusZone by rememberSaveable { mutableStateOf(FocusZone.SEARCH_INPUT) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 1 else 0) }
    var isSearchInputFocused by remember { mutableStateOf(false) }
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }
    val fastScrollThresholdMs = 220L

    // Manual row/item focus tracking (like HomeScreen)
    var currentRowIndex by rememberSaveable(uiState.query) { mutableIntStateOf(0) }
    var currentItemIndex by rememberSaveable(uiState.query) { mutableIntStateOf(0) }
    val rowPositions = rememberSaveable(uiState.query) { mutableMapOf<String, Int>() }
    var enterResultsOnLoad by remember { mutableStateOf(false) }
    var consumedDpadKey by remember { mutableStateOf<Key?>(null) }
    var focusedFilterIndex by remember { mutableIntStateOf(0) }
    // The filtered grid is driven the same way the rows are: one remembered index, one white
    // focus ring, no second native focus target (see RowsLayer).
    var gridFocusIndex by rememberSaveable { mutableIntStateOf(0) }
    val discoverGridState = rememberLazyGridState()
    var resultsLastNavEventTime by remember { mutableLongStateOf(0L) }
    var isSearchEditing by remember { mutableStateOf(false) }
    var searchEditRequestNonce by remember { mutableIntStateOf(0) }

    val searchFocusRequester = remember { FocusRequester() }
    val filtersFocusRequester = remember { FocusRequester() }
    val resultsFocusRequester = remember { FocusRequester() }
    val textInputFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val contentLanguage = viewModel.contentLanguage
    val certifications = remember(contentLanguage) { certificationsForLanguage(contentLanguage) }
    // The panel that is open under a chip, and where the focus sits inside it. Both live here
    // and not in the view model: nothing about an open panel survives leaving the screen.
    var openPanel by remember { mutableStateOf<DiscoverFilterId?>(null) }
    var panelFocus by remember { mutableStateOf(PanelFocus.START) }
    // An open drop-down list is a second level inside the panel, so it needs its own position
    // and its own BACK step — see the three-rung BACK ladder further down.
    var openDropdown by remember { mutableStateOf<String?>(null) }
    var dropdownFocusIndex by remember { mutableIntStateOf(0) }
    val filterActions = remember(viewModel) {
        DiscoverFilterActions(
            onSelectType = viewModel::selectType,
            onToggleGenre = viewModel::toggleGenre,
            onMatchAllGenres = viewModel::setMatchAllGenres,
            onSelectSort = viewModel::selectSort,
            onSetRating = viewModel::setRating,
            onSelectDecade = viewModel::selectDecade,
            onSelectYear = viewModel::selectYear,
            onSelectCertification = viewModel::selectCertification,
            onSelectLanguage = viewModel::selectLanguage,
            onToggleHideWatched = { viewModel.setHideWatched(!viewModel.uiState.value.hideWatched) },
            onClearFilters = viewModel::clearDiscoverFilters,
            onOpenPanel = { id ->
                openDropdown = null
                if (openPanel == id) {
                    openPanel = null
                    focusZone = FocusZone.FILTERS
                } else {
                    openPanel = id
                    panelFocus = PanelFocus.START
                    focusZone = FocusZone.PANEL
                }
            }
        )
    }
    val quickFilters = discoverChips(state = uiState, certifications = certifications, actions = filterActions)
    val openPanelSpec = openPanel?.let { filterPanelSpec(it, uiState, certifications, filterActions) }
    // The exact-year section appears with the decade and vanishes with it, so the remembered
    // position has to be checked against the panel as it is now.
    val panelShapes = openPanelSpec?.shapes
    LaunchedEffect(panelShapes) {
        panelShapes?.let { panelFocus = clampPanelFocus(panelFocus, it) ?: PanelFocus.START }
    }
    LaunchedEffect(quickFilters.size) {
        focusedFilterIndex = focusedFilterIndex.coerceIn(0, (quickFilters.size - 1).coerceAtLeast(0))
    }
    LaunchedEffect(uiState.query, activeCategories.size, hasAiResults) {
        currentRowIndex = currentRowIndex.coerceIn(0, (activeCategories.size - 1).coerceAtLeast(0))
        val maxItem = (activeCategories.getOrNull(currentRowIndex)?.items?.size ?: 1) - 1
        currentItemIndex = currentItemIndex.coerceIn(0, maxItem.coerceAtLeast(0))
    }
    // A changed filter set means a different list, so the remembered position in the old one is
    // meaningless. Every filter belongs in this key — a missing one leaves the focus sitting on
    // the row and card index of a list that is no longer there.
    val filterSelection = listOf(
        uiState.selectedType,
        uiState.selectedGenres.joinToString(",") { it.id.toString() },
        uiState.matchAllGenres,
        uiState.sortOption,
        uiState.rating.min, uiState.rating.max, uiState.rating.minVotes,
        uiState.decade,
        uiState.year,
        uiState.certification,
        uiState.hideWatched
    ).joinToString(":")
    var previousFilterSelection by rememberSaveable { mutableStateOf(filterSelection) }
    LaunchedEffect(filterSelection) {
        if (previousFilterSelection != filterSelection) {
            previousFilterSelection = filterSelection
            rowPositions.clear()
            currentRowIndex = 0
            currentItemIndex = 0
            gridFocusIndex = 0
            try {
                discoverGridState.scrollToItem(0)
            } catch (e: Exception) {
                if (e is CancellationException) throw e
            }
        }
    }

    LaunchedEffect(uiState.isLoading, enterResultsOnLoad) {
        if (enterResultsOnLoad && !uiState.isLoading) {
            enterResultsOnLoad = false
            if (activeCategories.isNotEmpty()) {
                focusZone = FocusZone.RESULTS
                resultsLastNavEventTime = SystemClock.elapsedRealtime()
            }
        }
    }
    LaunchedEffect(focusZone, activeCategories.isNotEmpty(), gridItems.isNotEmpty(), (uiState.gridLoadFailed || uiState.gridScanPaused), isSearchEditing) {
        if (!isTouchDevice && focusZone == FocusZone.RESULTS && (activeCategories.isNotEmpty() || gridItems.isNotEmpty() || (uiState.gridLoadFailed || uiState.gridScanPaused)) && !isSearchEditing) {
            resultsFocusRequester.requestFocus()
        }
    }

    fun moveResultRow(offset: Int) {
        activeCategories.getOrNull(currentRowIndex)?.let { rowPositions[it.id] = currentItemIndex }
        currentRowIndex = (currentRowIndex + offset).coerceIn(0, (activeCategories.size - 1).coerceAtLeast(0))
        val row = activeCategories.getOrNull(currentRowIndex)
        currentItemIndex = (rowPositions[row?.id] ?: currentItemIndex)
            .coerceIn(0, (row?.items?.lastIndex ?: 0).coerceAtLeast(0))
        resultsLastNavEventTime = SystemClock.elapsedRealtime()
    }

    LaunchedEffect(isTouchDevice) {
        // FocusRequester can throw IllegalStateException if the target composable
        // hasn't been placed yet (e.g. zero-sized keyboard on cold start, or when
        // the screen is composed then immediately navigated away). Swallow that
        // specific case so it doesn't surface to the user as a crash — TalkBack
        // focus will re-claim on next frame.
        if (!isTouchDevice && focusZone != FocusZone.RESULTS) try { searchFocusRequester.requestFocus() } catch (_: Exception) {}
        suppressSelectUntilMs = SystemClock.elapsedRealtime() + SEARCH_SELECT_SUPPRESS_MS
    }
    LaunchedEffect(isSearchEditing, searchEditRequestNonce) {
        if (isSearchEditing) {
            try { textInputFocusRequester.requestFocus() } catch (_: Exception) {}
            keyboardController?.show()
        }
    }
    // A keyboard that closes itself takes its BACK press with it, so the screen is never told
    // it is gone and typing mode outlives it — every direction key then goes to an input that
    // is no longer there. Follow what the keyboard actually does instead (SearchEditingEntry).
    val imeVisible = WindowInsets.isImeVisible
    var keyboardWasSeen by remember { mutableStateOf(false) }
    LaunchedEffect(isSearchEditing, imeVisible) {
        if (!isSearchEditing) {
            keyboardWasSeen = false
            return@LaunchedEffect
        }
        if (imeVisible) keyboardWasSeen = true
        if (!searchEditingSurvivesKeyboard(imeVisible, keyboardWasSeen)) {
            isSearchEditing = false
            try { searchFocusRequester.requestFocus() } catch (_: Exception) {}
        }
    }
    // Coming back from the background composes nothing anew, so the entry guard above would not
    // run again: re-arm it here and make sure the screen is never resumed in typing mode.
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Back from a title's page: whatever was watched there gets its tick here too.
                viewModel.refreshWatchedMarks()
                isSearchEditing = false
                keyboardController?.hide()
                suppressSelectUntilMs = SystemClock.elapsedRealtime() + SEARCH_SELECT_SUPPRESS_MS
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    // Every door into typing mode goes through here: the D-pad handler below, select on the
    // search bar itself, and its click. A press that still belongs to the one that OPENED this
    // screen is dropped — see SearchEditingEntry.kt for what it does to the screen otherwise.
    fun startSearchEditing(repeatCount: Int = 0) {
        if (!startsSearchEditing(SystemClock.elapsedRealtime(), suppressSelectUntilMs, repeatCount)) return
        focusZone = FocusZone.SEARCH_INPUT
        isSearchEditing = true
        searchEditRequestNonce++
    }

    val showFilters = uiState.query.isEmpty()
    // Rows while nothing is filtered, one endlessly paging grid from the first filter on (H9).
    val showGrid = showFilters && uiState.hasDiscoverFilters
    val gridSlotCount = gridItems.size + if ((uiState.gridLoadFailed || uiState.gridScanPaused)) 1 else 0
    val hasGridResults = showGrid && gridSlotCount > 0
    val canEnterResults = activeCategories.isNotEmpty() || hasAiResults || hasGridResults
    // The discover grid always shows poster cards, no matter what the catalogue row layout
    // setting says: the approved design shows it that way, and about twice as many titles fit
    // on a TV screen, which is the whole point of a grid. Rows and the AI grid keep following
    // the setting.
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    // Phone keeps the size it was tested with; the TV and desktop grid follows the approved
    // design instead of CollectionDetailsScreen — see DiscoverGridLayout for why and by how much.
    val screenWidthDp = configuration.screenWidthDp.dp
    val gridCardWidth = if (isTouchDevice) 138.dp else discoverGridCardWidth(screenWidthDp)
    val gridColumns = if (isTouchDevice) {
        if (isLandscape) 4 else 3
    } else {
        discoverGridColumns(screenWidthDp, gridCardWidth)
    }
    LaunchedEffect(gridSlotCount) {
        gridFocusIndex = gridFocusIndex.coerceIn(0, (gridSlotCount - 1).coerceAtLeast(0))
    }

    fun moveGridFocus(offset: Int) {
        val last = gridSlotCount - 1
        if (last < 0) return
        gridFocusIndex = (gridFocusIndex + offset).coerceIn(0, last)
        // Asking early keeps the next page ready before the user reaches the bottom edge.
        if (gridFocusIndex >= gridItems.size - gridColumns * 2) viewModel.loadMoreDiscoverGrid()
    }

    BackHandler {
        if (isSearchEditing) {
            isSearchEditing = false
            keyboardController?.hide()
            try { searchFocusRequester.requestFocus() } catch (_: Exception) {}
        } else if (openDropdown != null) {
            // The teuerste Falle of this round: BACK closes the LIST first. Closing the whole
            // panel here would throw away the half-made entry the list was opened for.
            openDropdown = null
        } else if (openPanel != null) {
            openPanel = null
            focusZone = FocusZone.FILTERS
        } else {
            when (focusZone) {
                // PANEL: the open panel is handled before this runs.
                FocusZone.PANEL -> Unit
                FocusZone.RESULTS -> {
                    if (showFilters && quickFilters.isNotEmpty()) {
                        focusZone = FocusZone.FILTERS
                        val selectedIdx = quickFilters.indexOfFirst { it.isSet }.coerceAtLeast(0)
                        focusedFilterIndex = if (focusedFilterIndex in quickFilters.indices) focusedFilterIndex else selectedIdx
                        try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                    } else {
                        focusZone = FocusZone.SEARCH_INPUT
                        try { searchFocusRequester.requestFocus() } catch (_: Exception) {}
                    }
                }
                FocusZone.FILTERS -> {
                    focusZone = FocusZone.SEARCH_INPUT
                    try { searchFocusRequester.requestFocus() } catch (_: Exception) {}
                }
                FocusZone.SEARCH_INPUT -> {
                    focusZone = FocusZone.SIDEBAR
                }
                FocusZone.SIDEBAR -> {
                    onBack()
                }
            }
        }
    }

    // One D-pad handler owns zone transitions and filter selection.
    val dpadModifier = if (!isTouchDevice) {
        Modifier.onPreviewKeyEvent { event ->
            if (event.type == KeyEventType.KeyUp && consumedDpadKey == event.key) {
                consumedDpadKey = null
                return@onPreviewKeyEvent true
            }
            if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
            if (isSearchEditing && (event.key == Key.Back || event.key == Key.Escape)) {
                isSearchEditing = false
                keyboardController?.hide()
                try { searchFocusRequester.requestFocus() } catch (_: Exception) {}
                consumedDpadKey = event.key
                return@onPreviewKeyEvent true
            }
            if (isSearchEditing) return@onPreviewKeyEvent false
            // An open panel owns every key until it is closed. Without this the chip row would
            // move at the same time and the panel would end up describing a different chip.
            if (focusZone == FocusZone.PANEL) {
                val focusedDropdown = openPanelSpec?.dropdownAt(panelFocus)
                val openList = focusedDropdown?.takeIf { it.key == openDropdown }?.entries
                val panelHandled = if (openList != null) {
                    // An open list owns every key: BACK closes the list and nothing else, and
                    // left/right are swallowed so the tiles behind it cannot move underneath.
                    when (event.key) {
                        Key.Back, Key.Escape -> { openDropdown = null; true }
                        Key.Enter, Key.DirectionCenter -> {
                            openList.getOrNull(dropdownFocusIndex)?.onToggle?.invoke()
                            openDropdown = null
                            true
                        }
                        Key.DirectionUp, Key.DirectionDown -> {
                            val step = if (event.key == Key.DirectionUp) -1 else 1
                            dropdownFocusIndex =
                                moveDropdownFocus(dropdownFocusIndex, openList.size, step)
                            true
                        }
                        Key.DirectionLeft, Key.DirectionRight -> true
                        else -> false
                    }
                } else when (event.key) {
                    Key.Back, Key.Escape -> {
                        openPanel = null
                        focusZone = FocusZone.FILTERS
                        true
                    }
                    Key.Enter, Key.DirectionCenter -> {
                        if (focusedDropdown != null) {
                            openDropdown = focusedDropdown.key
                            dropdownFocusIndex =
                                focusedDropdown.entries.indexOfFirst { it.isSelected }.coerceAtLeast(0)
                        } else {
                            openPanelSpec?.optionAt(panelFocus)?.onToggle?.invoke()
                        }
                        true
                    }
                    Key.DirectionUp, Key.DirectionDown, Key.DirectionLeft, Key.DirectionRight -> {
                        val dx = when (event.key) {
                            Key.DirectionLeft -> if (isRtl) 1 else -1
                            Key.DirectionRight -> if (isRtl) -1 else 1
                            else -> 0
                        }
                        val dy = when (event.key) {
                            Key.DirectionUp -> -1
                            Key.DirectionDown -> 1
                            else -> 0
                        }
                        val next = movePanelFocus(panelFocus, panelShapes.orEmpty(), dx, dy)
                        if (next.hasLeft) {
                            openPanel = null
                            focusZone = FocusZone.FILTERS
                        } else {
                            panelFocus = next
                        }
                        true
                    }
                    else -> false
                }
                if (panelHandled) consumedDpadKey = event.key
                return@onPreviewKeyEvent panelHandled
            }
            val effectiveKey = when (event.key) {
                Key.DirectionLeft  -> if (isRtl) Key.DirectionRight else Key.DirectionLeft
                Key.DirectionRight -> if (isRtl) Key.DirectionLeft  else Key.DirectionRight
                else -> event.key
            }
            val handled = when (effectiveKey) {
                Key.Back, Key.Escape -> when (focusZone) {
                    // PANEL: the open panel is handled before this runs.
                    FocusZone.PANEL -> false
                    FocusZone.RESULTS -> {
                        if (showFilters && quickFilters.isNotEmpty()) {
                            focusZone = FocusZone.FILTERS
                            val selectedIdx = quickFilters.indexOfFirst { it.isSet }.coerceAtLeast(0)
                            focusedFilterIndex = if (focusedFilterIndex in quickFilters.indices) focusedFilterIndex else selectedIdx
                            try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                        }
                        else { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus() }
                        true
                    }
                    FocusZone.FILTERS -> { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    FocusZone.SEARCH_INPUT -> {
                        // Always progress toward sidebar so repeated Back presses can exit Search.
                        isSearchEditing = false
                        keyboardController?.hide()
                        focusZone = FocusZone.SIDEBAR
                        true
                    }
                    FocusZone.SIDEBAR -> { onBack(); true }
                }
                Key.DirectionUp -> when (focusZone) {
                    // PANEL: the open panel is handled before this runs.
                    FocusZone.PANEL -> false
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
                        else if (hasGridResults && gridFocusIndex >= gridColumns) {
                            moveGridFocus(-gridColumns)
                            true
                        }
                        else if (!hasGridResults && currentRowIndex > 0) {
                            moveResultRow(-1)
                            true
                        }
                        else if (showFilters && quickFilters.isNotEmpty()) {
                            focusZone = FocusZone.FILTERS
                            val selectedIdx = quickFilters.indexOfFirst { it.isSet }.coerceAtLeast(0)
                            focusedFilterIndex = if (focusedFilterIndex in quickFilters.indices) focusedFilterIndex else selectedIdx
                            try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                            true
                        }
                        else { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    }
                }
                Key.DirectionDown -> when (focusZone) {
                    // PANEL: the open panel is handled before this runs.
                    FocusZone.PANEL -> false
                    FocusZone.SIDEBAR -> { focusZone = FocusZone.SEARCH_INPUT; searchFocusRequester.requestFocus(); true }
                    FocusZone.SEARCH_INPUT -> {
                        isSearchEditing = false
                        keyboardController?.hide()
                        if (showFilters && quickFilters.isNotEmpty()) {
                            focusZone = FocusZone.FILTERS
                            val selectedIdx = quickFilters.indexOfFirst { it.isSet }.coerceAtLeast(0)
                            focusedFilterIndex = if (selectedIdx in quickFilters.indices) selectedIdx else 0
                            try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                        }
                        else if (canEnterResults) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            focusZone = FocusZone.RESULTS
                        } else if (uiState.isLoading) {
                            enterResultsOnLoad = true
                        }
                        true
                    }
                    FocusZone.FILTERS -> {
                        if (canEnterResults) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            focusZone = FocusZone.RESULTS
                        }
                        true
                    }
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false // AI grid: let native focus handle navigation
                        else if (hasGridResults) {
                            moveGridFocus(gridColumns)
                            true
                        }
                        else if (currentRowIndex < activeCategories.size - 1) {
                            moveResultRow(1)
                            true
                        }
                        else true
                    }
                }
                Key.DirectionLeft -> when (focusZone) {
                    // PANEL: the open panel is handled before this runs.
                    FocusZone.PANEL -> false
                    FocusZone.SIDEBAR -> { if (sidebarFocusIndex > 0) sidebarFocusIndex--; true }
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false
                        else if (hasGridResults) {
                            // Stays inside the row, like the D-pad map says: left/right never
                            // wraps into another line and never leaves the zone.
                            if (gridFocusIndex % gridColumns > 0) moveGridFocus(-1)
                            true
                        }
                        else {
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
                        }
                        true
                    }
                    else -> false
                }
                Key.DirectionRight -> when (focusZone) {
                    // PANEL: the open panel is handled before this runs.
                    FocusZone.PANEL -> false
                    FocusZone.SIDEBAR -> { if (sidebarFocusIndex < maxSidebarIndex) sidebarFocusIndex++; true }
                    FocusZone.RESULTS -> {
                        if (hasAiResults) false // AI grid: let native focus handle navigation
                        else if (hasGridResults) {
                            if (gridFocusIndex % gridColumns < gridColumns - 1) moveGridFocus(1)
                            true
                        }
                        else {
                            val cats = activeCategories.filter { it.items.isNotEmpty() }
                            val maxItem = (cats.getOrNull(currentRowIndex)?.items?.size ?: 1) - 1
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
                        // PANEL: the open panel is handled before this runs.
                        FocusZone.PANEL -> false
                        FocusZone.SIDEBAR -> {
                            if (hasProfile && sidebarFocusIndex == 0) onSwitchProfile()
                            else when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) { SidebarItem.SEARCH -> Unit; SidebarItem.HOME -> onNavigateToHome(); SidebarItem.WATCHLIST -> onNavigateToWatchlist(); SidebarItem.TV -> onNavigateToTv(); SidebarItem.SETTINGS -> onNavigateToSettings(); null -> Unit }
                            true
                        }
                        FocusZone.SEARCH_INPUT -> {
                            startSearchEditing(event.nativeKeyEvent.repeatCount)
                            true
                        }
                        FocusZone.FILTERS -> {
                            val chip = quickFilters.getOrNull(focusedFilterIndex)
                            // The reset chip is the one control that removes itself, so the frame
                            // is moved off it in the same press rather than afterwards. The
                            // LaunchedEffect on `quickFilters.size` further up already clamps an
                            // index that is out of range, but it runs a recomposition later —
                            // long enough for one frame with no focus ring at all, and a ring
                            // that blinks out after a press is the kind of thing this row has
                            // been reported for before.
                            if (chip?.id == DiscoverFilterId.CLEAR) {
                                focusedFilterIndex = focusAfterClearChip(focusedFilterIndex)
                            }
                            chip?.onActivate?.invoke()
                            try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                            true
                        }
                        FocusZone.RESULTS -> {
                            if (hasAiResults) false
                            else if (hasGridResults) {
                                if ((uiState.gridLoadFailed || uiState.gridScanPaused) && gridFocusIndex == gridItems.size) {
                                    viewModel.retryDiscoverGrid()
                                } else {
                                    gridItems.getOrNull(gridFocusIndex)?.let { onNavigateToDetails(it.mediaType, it.id) }
                                }
                                true
                            }
                            else {
                                // Use stable category lookup to avoid race condition with dynamic list updates
                                val cats = activeCategories.filter { it.items.isNotEmpty() }
                                val item = cats.getOrNull(currentRowIndex)?.items?.getOrNull(currentItemIndex)
                                if (item != null) onNavigateToDetails(item.mediaType, item.id)
                                true
                            }
                        }
                    }
                }
                else -> false
            }
            if (handled) consumedDpadKey = event.key
            handled
        }
    } else Modifier

    Box(modifier = Modifier.fillMaxSize().background(appBackgroundDark()).then(dpadModifier).testTag("search-screen")) {
        if (!isTouchDevice) AppTopBar(selectedItem = SidebarItem.SEARCH, isFocused = focusZone == FocusZone.SIDEBAR, focusedIndex = sidebarFocusIndex, profile = currentProfile)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (isTouchDevice) Modifier.statusBarsPadding().padding(top = 12.dp)
                    else Modifier.padding(top = AppTopBarContentTopInset)
                )
                .padding(horizontal = if (isTouchDevice) 0.dp else if (isCompactHeight) 20.dp else 28.dp)
        ) {
            // ── Search Bar ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = if (isTouchDevice) 16.dp else 22.dp,
                        end = if (isTouchDevice) 16.dp else 22.dp,
                        bottom = if (isCompactHeight) 6.dp else 8.dp
                    ),
                horizontalArrangement = Arrangement.Center,
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
                    onQueryChange = { enterResultsOnLoad = false; viewModel.updateQuery(it) },
                    onSearch = {
                        viewModel.search()
                        keyboardController?.hide()
                        isSearchEditing = false
                        enterResultsOnLoad = !isTouchDevice
                    },
                    onFocused = {
                        if (focusZone == FocusZone.SEARCH_INPUT) {
                            isSearchInputFocused = true
                        }
                    },
                    onFocusLost = { isSearchInputFocused = false },
                    onStartEditing = { repeatCount -> startSearchEditing(repeatCount) },
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
                            val selectedIdx = quickFilters.indexOfFirst { it.isSet }.coerceAtLeast(0)
                            focusedFilterIndex = if (selectedIdx in quickFilters.indices) selectedIdx else 0
                            try { filtersFocusRequester.requestFocus() } catch (_: Exception) {}
                        } else if (canEnterResults) {
                            resultsLastNavEventTime = SystemClock.elapsedRealtime()
                            focusZone = FocusZone.RESULTS
                        }
                    }
                )
            }

            // ── Filter row (discover mode) — one painted focus, no native focus target ──
            if (showFilters) {
                DiscoverFilterRow(
                    chips = quickFilters,
                    focusedIndex = focusedFilterIndex,
                    isRowFocused = focusZone == FocusZone.FILTERS || focusZone == FocusZone.PANEL,
                    isTouchDevice = isTouchDevice,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = if (isTouchDevice) 4.dp else 0.dp),
                    focusRequester = if (isTouchDevice) null else filtersFocusRequester
                )
                // On a TV the panel hangs under the chip that opened it, as in the draft. The
                // phone gets the same content from the bottom edge instead — further down, at
                // the screen's outer box, because that is the only place a sheet can sit OVER
                // the grid instead of pushing it down.
                if (!isTouchDevice) {
                    openPanelSpec?.let { spec ->
                        DiscoverFilterPanel(
                            spec = spec,
                            focus = panelFocus,
                            openDropdownKey = openDropdown,
                            dropdownFocusIndex = dropdownFocusIndex,
                            isTouchDevice = false,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(0.62f)
                                .padding(vertical = 4.dp)
                        )
                    }
                }
            }

            // ── Content ──
            when {
                uiState.isLoading && !hasSearchResults -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 48.dp) }

                hasAiResults -> {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)) {
                        Icon(Icons.Default.AutoAwesome, null, tint = AccentGreen, modifier = Modifier.size(16.dp)); Spacer(Modifier.width(6.dp))
                        Text(uiState.aiInterpretation ?: "", style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = FontWeight.Medium), color = Color.White.copy(alpha = 0.85f))
                    }
                    ContentGrid(items = uiState.aiResults, usePosterCards = aiUsePosterCards, isLoading = false, isTouchDevice = isTouchDevice, onItemClick = { onNavigateToDetails(it.mediaType, it.id) }, onLoadMore = {})
                }

                uiState.query.isNotEmpty() && !uiState.isAiSearch && !hasSearchResults -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(if (uiState.error != null) stringResource(R.string.error_loading)
                            else "${stringResource(R.string.no_results_for)} \"${uiState.query}\"",
                            style = ArflixTypography.body, color = TextSecondary)
                    }
                }

                showGrid && uiState.isGridLoading && gridItems.isEmpty() ->
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 48.dp) }

                showGrid && gridItems.isEmpty() && !(uiState.gridLoadFailed || uiState.gridScanPaused) -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(stringResource(R.string.no_results), style = ArflixTypography.body, color = TextSecondary)
                    }
                }

                showGrid -> ContentGrid(
                    items = gridItems,
                    usePosterCards = true,
                    isLoading = uiState.isGridLoadingMore,
                    isTouchDevice = isTouchDevice,
                    onItemClick = { onNavigateToDetails(it.mediaType, it.id) },
                    onLoadMore = { viewModel.loadMoreDiscoverGrid() },
                    loadFailed = uiState.gridLoadFailed,
                    scanPaused = uiState.gridScanPaused,
                    onRetry = { viewModel.retryDiscoverGrid() },
                    modifier = if (isTouchDevice) Modifier else Modifier.focusRequester(resultsFocusRequester).focusable(),
                    columns = gridColumns,
                    cardWidth = gridCardWidth,
                    // One line with an ellipsis, as in the approved design. A second line would
                    // make cards with long titles taller than their neighbours, and at 105 dp
                    // that is most of them — the rows of the grid would stop lining up.
                    titleMaxLines = 1,
                    manualFocusIndex = if (isTouchDevice) null else gridFocusIndex,
                    isZoneFocused = focusZone == FocusZone.RESULTS,
                    gridState = discoverGridState
                )

                uiState.isDiscoverLoading && activeCategories.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 48.dp) }

                activeCategories.isNotEmpty() -> {
                    // Row-based content (discover rows or search results) - HomeScreen pattern
                    key(uiState.query) { RowsLayer(
                        categories = activeCategories,
                        cardLogoUrls = activeLogoUrls,
                        currentRowIndex = currentRowIndex,
                        currentItemIndex = currentItemIndex,
                        lastNavEventTime = resultsLastNavEventTime,
                        fastScrollThresholdMs = fastScrollThresholdMs,
                        isFocused = focusZone == FocusZone.RESULTS,
                        isTouchDevice = isTouchDevice,
                        modifier = if (isTouchDevice) Modifier else Modifier.focusRequester(resultsFocusRequester).focusable(),
                        onItemClick = { onNavigateToDetails(it.mediaType, it.id) }
                    ) }
                }
            }
        }

        // The phone sheet. One surface, not two (E8): the same panel, only anchored to the
        // bottom edge where a thumb reaches it, instead of to the chip it belongs to.
        if (isTouchDevice) {
            openPanelSpec?.let { spec ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.45f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            openDropdown = null
                            openPanel = null
                        }
                )
                DiscoverFilterPanel(
                    spec = spec,
                    focus = null,
                    openDropdownKey = openDropdown,
                    dropdownFocusIndex = dropdownFocusIndex,
                    isTouchDevice = true,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp + LocalBottomBarHeight.current)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {},
                    onOpenDropdown = { field ->
                        openDropdown = if (openDropdown == field.key) null else field.key
                    },
                    onPickFromDropdown = { option ->
                        option.onToggle()
                        openDropdown = null
                    }
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
    onStartEditing: (Int) -> Unit,
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
                focusedContainerColor = BackgroundCard,
                unfocusedContainerColor = BackgroundCard,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.18f)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("search-input")
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
            .height(54.dp)
            .onPreviewKeyEvent { event ->
                if (!isFocused || isEditing) return@onPreviewKeyEvent false
                if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                when (event.key) {
                    Key.DirectionUp -> { onMoveUp(); true }
                    Key.DirectionDown -> { onMoveDown(); true }
                    Key.Enter, Key.DirectionCenter -> { onStartEditing(event.nativeKeyEvent.repeatCount); true }
                    else -> false
                }
            }
            .focusRequester(searchFocusRequester),
        shape = shape,
        backgroundColor = Color.White.copy(alpha = if (isFocused) 0.075f else 0.045f),
        outlineColor = Color.White,
        outlineWidth = if (isFocused) 3.dp else 2.dp,
        glowWidth = if (isFocused) 2.dp else 0.dp,
        glowAlpha = 0.22f,
        focusedScale = 1f,
        pressedScale = 0.985f,
        useSystemFocusForVisuals = false,
        isFocusedOverride = isFocused,
        onClick = { onStartEditing(0) },
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
                textStyle = ArflixTypography.body.copy(color = TextPrimary, fontSize = 17.sp),
                cursorBrush = SolidColor(Color.White),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSearch() }),
                modifier = Modifier
                    .weight(1f)
                    .testTag("search-input")
                    .focusRequester(textInputFocusRequester),
                decorationBox = { inner ->
                    if (query.isEmpty()) {
                        Text(
                            stringResource(R.string.search),
                            style = ArflixTypography.body.copy(fontSize = 17.sp),
                            color = Color.White.copy(alpha = 0.32f)
                        )
                    }
                    inner()
                }
            )
        }
    }
}

// ── Rows Layer (HomeScreen pattern - manual focus, smooth scroll) ────────────

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun RowsLayer(
    categories: List<Category>, cardLogoUrls: Map<String, String>,
    currentRowIndex: Int, currentItemIndex: Int,
    lastNavEventTime: Long,
    fastScrollThresholdMs: Long,
    isFocused: Boolean,
    isTouchDevice: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: (MediaItem) -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenHeight = configuration.screenHeightDp

    val focusBleedPadding = if (isTouchDevice) 16.dp else 22.dp

    val listState = rememberLazyListState()
    var lastAppliedTargetIndex by remember { mutableIntStateOf(-1) }
    val targetIndex = currentRowIndex.coerceIn(0, (categories.size - 1).coerceAtLeast(0))

    // Only move the results viewport in response to actual D-pad navigation on TV.
    if (!isTouchDevice) {
        LaunchedEffect(targetIndex, isFocused) {
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
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(
                top = if (isTouchDevice) 4.dp else focusBleedPadding / 2,
                bottom = if (isTouchDevice) 16.dp + LocalBottomBarInset.current else maxHeight * 0.6f
            ),
            modifier = Modifier.fillMaxSize().arvioDpadFocusGroup(),
            verticalArrangement = Arrangement.spacedBy(if (isTouchDevice) 20.dp else 0.dp)
        ) {
            items(categories.size, key = { categories[it].id }) { index ->
                val category = categories[index]
                val isCurrentRow = !isTouchDevice && isFocused && index == currentRowIndex
                val rowKey = remember(category.id) { "search:${category.id}" }
                val rowUsePosterCards = rememberCatalogueRowLayoutMode(rowKey) == CardLayoutMode.POSTER
                val isPortrait = category.isPortrait(rowUsePosterCards)
                val itemWidth = if (isTouchDevice) {
                    if (isPortrait) 120.dp else 200.dp
                } else {
                    if (isPortrait) 105.dp else 210.dp
                }
                val baseRowHeight = if (isPortrait) {
                    // Poster cards (2:3) need extra vertical room for title + date below the image
                    if (screenHeight <= 640) 271.dp else 309.dp
                } else {
                    // Landscape cards still render title + subtitle below artwork.
                    if (screenHeight <= 640) 210.dp else 274.dp
                }
                val rowHeight = baseRowHeight + focusBleedPadding
                // Fade non-current rows on TV only
                val rowAlpha by animateFloatAsState(
                    targetValue = if (isTouchDevice || !isFocused || index <= currentRowIndex) 1f else 0.3f,
                    animationSpec = tween(250), label = "rowAlpha"
                )

                val rowContent = @Composable {
                    Column(modifier = Modifier.fillMaxWidth().testTag("search-row-${category.id}")) {
                        Row(
                            modifier = Modifier.padding(
                                start = if (isTouchDevice) 16.dp else focusBleedPadding,
                                bottom = if (isTouchDevice) 4.dp else 4.dp,
                                top = if (isTouchDevice) 0.dp else 4.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                localizedDiscoverRowTitle(category),
                                style = if (isTouchDevice) {
                                    ArflixTypography.sectionTitle.copy(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        shadow = Shadow(
                                            color = Color.Black.copy(alpha = 0.8f),
                                            offset = Offset(1f, 1f),
                                            blurRadius = 4f
                                        )
                                    )
                                } else {
                                    ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp)
                                },
                                color = if (isTouchDevice) Color.White else Color.White.copy(alpha = if (isCurrentRow) 0.9f else 0.5f)
                            )
                        }

                        // The keyed lazy item saves this state across disposal and navigation.
                        val rowState = rememberLazyListState()
                        // Keep visible cards still; only scroll enough to reveal a clipped selection.
                        if (!isTouchDevice) {
                            LaunchedEffect(isCurrentRow, currentItemIndex) {
                                if (!isCurrentRow) return@LaunchedEffect
                                val safeIndex = currentItemIndex.coerceIn(0, (category.items.size - 1).coerceAtLeast(0))
                                val first = rowState.firstVisibleItemIndex
                                val visibleItems = rowState.layoutInfo.visibleItemsInfo
                                val targetInfo = visibleItems.firstOrNull { it.index == safeIndex }
                                if (targetInfo == null) {
                                    if (kotlin.math.abs(safeIndex - first) > 6) rowState.scrollToItem(safeIndex)
                                    else rowState.animateScrollToItem(safeIndex)
                                } else {
                                    val margin = with(density) { focusBleedPadding.roundToPx() }
                                    val end = rowState.layoutInfo.viewportEndOffset - margin
                                    val delta = when {
                                        targetInfo.offset < 0 -> targetInfo.offset
                                        targetInfo.offset + targetInfo.size > end -> targetInfo.offset + targetInfo.size - end
                                        else -> 0
                                    }
                                    if (delta != 0) rowState.animateScrollBy(delta.toFloat())
                                }
                            }
                        }

                        LazyRow(
                            state = rowState,
                            modifier = Modifier.arvioDpadFocusGroup(),
                            contentPadding = if (isTouchDevice) {
                                PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
                            } else {
                                PaddingValues(
                                    start = focusBleedPadding,
                                    end = itemWidth + 56.dp,
                                    top = 8.dp,
                                    bottom = focusBleedPadding + 12.dp
                                )
                            },
                            horizontalArrangement = Arrangement.spacedBy(if (isTouchDevice) 14.dp else 18.dp)
                        ) {
                            itemsIndexed(category.items, key = { _, item -> "${item.mediaType}_${item.id}" }) { itemIdx, item ->
                                val itemIsFocused = !isTouchDevice && isCurrentRow && itemIdx == currentItemIndex
                                MediaCard(
                                    item = item.copy(
                                        title = buildCardTitle(item),
                                        subtitle = buildCardSubtitle(item),
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
                                    modifier = Modifier.testTag("search-card-${category.id}-${item.mediaType}-${item.id}")
                                        .semantics { selected = itemIsFocused }
                                        .then(if (isTouchDevice) Modifier.clickable { onItemClick(item) } else Modifier)
                                )
                            }
                        }
                    }
                }

                if (isTouchDevice) {
                    rowContent()
                } else {
                    Box(modifier = Modifier.fillMaxWidth().height(rowHeight).graphicsLayer { alpha = rowAlpha }) {
                        rowContent()
                    }
                }
            }
        }
    }
}

// ── Content Grid (AI results and the filtered discover grid) ────────────────

private fun defaultGridCardWidth(usePosterCards: Boolean, isTouchDevice: Boolean): Dp = when {
    usePosterCards && isTouchDevice -> 120.dp
    usePosterCards -> 105.dp
    isTouchDevice -> 200.dp
    else -> 210.dp
}

/** Asks for the next page while the last visible cards are still a screenful away. */
@Composable
private fun LoadMoreWhenGridNearsEnd(gridState: LazyGridState, itemCount: Int, onLoadMore: () -> Unit) {
    LaunchedEffect(gridState.firstVisibleItemIndex, itemCount) {
        val lastVisible = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        if (itemCount > 0 && lastVisible >= itemCount - 8) onLoadMore()
    }
}

/**
 * Manual focus paints no system focus, so the viewport has to follow the index itself — the same
 * job `RowsLayer` does for a row, and it needs the same second case for a card that is only
 * clipped at an edge. The decision lives in [gridFollowFor]; this is the part that touches the
 * grid. Both content paddings are taken off first — the grid has one at the top too — and grid
 * infos carry IntOffset/IntSize, hence `.offset.y` and `.size.height`.
 */
@Composable
private fun FollowFocusedGridItem(gridState: LazyGridState, focusedIndex: Int?, itemCount: Int) {
    if (focusedIndex == null) return
    LaunchedEffect(focusedIndex, itemCount) {
        if (itemCount == 0) return@LaunchedEffect
        val target = focusedIndex.coerceIn(0, itemCount - 1)
        val layout = gridState.layoutInfo
        val targetInfo = layout.visibleItemsInfo.firstOrNull { it.index == target }
        val follow = gridFollowFor(
            targetIndex = target,
            firstVisibleIndex = layout.visibleItemsInfo.firstOrNull()?.index ?: 0,
            targetOffsetY = targetInfo?.offset?.y,
            targetHeight = targetInfo?.size?.height ?: 0,
            viewportStart = layout.viewportStartOffset + layout.beforeContentPadding,
            viewportEnd = layout.viewportEndOffset - layout.afterContentPadding
        )
        when (follow) {
            is GridFollow.Stay -> Unit
            is GridFollow.ScrollBy -> gridState.animateScrollBy(follow.delta.toFloat())
            is GridFollow.ScrollTo ->
                if (follow.animate) gridState.animateScrollToItem(follow.index)
                else gridState.scrollToItem(follow.index)
        }
    }
}

/**
 * One grid for both users of it. The AI results keep native focus ([manualFocusIndex] `null`,
 * adaptive columns); the discover grid passes a fixed column count and its own focus index, so
 * it behaves exactly like [RowsLayer] — one remembered position, one white ring, no second
 * native focus target that the screen's D-pad handler would have to fight.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ContentGrid(
    items: List<MediaItem>,
    usePosterCards: Boolean,
    isLoading: Boolean,
    isTouchDevice: Boolean,
    onItemClick: (MediaItem) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
    columns: Int? = null,
    cardWidth: Dp? = null,
    titleMaxLines: Int = 2,
    manualFocusIndex: Int? = null,
    isZoneFocused: Boolean = true,
    gridState: LazyGridState = rememberLazyGridState(),
    loadFailed: Boolean = false,
    scanPaused: Boolean = false,
    onRetry: () -> Unit = {}
) {
    val itemWidth = cardWidth ?: defaultGridCardWidth(usePosterCards, isTouchDevice)
    LoadMoreWhenGridNearsEnd(gridState, items.size, onLoadMore)
    FollowFocusedGridItem(gridState, manualFocusIndex, items.size + if (loadFailed || scanPaused) 1 else 0)

    val focusBleedPadding = if (isTouchDevice) 16.dp else 24.dp
    LazyVerticalGrid(
        state = gridState,
        columns = columns?.let { GridCells.Fixed(it) }
            ?: GridCells.Adaptive(minSize = itemWidth + (if (isTouchDevice) 8.dp else focusBleedPadding)),
        contentPadding = PaddingValues(start = focusBleedPadding, end = focusBleedPadding, top = focusBleedPadding, bottom = focusBleedPadding + LocalBottomBarInset.current),
        horizontalArrangement = Arrangement.spacedBy(if (isTouchDevice) 14.dp else 18.dp),
        verticalArrangement = Arrangement.spacedBy(if (isTouchDevice) 18.dp else 26.dp),
        modifier = modifier.fillMaxSize().arvioDpadFocusGroup()
    ) {
        items(items.size, key = { "${items[it].mediaType}_${items[it].id}" }) { idx ->
            val item = items[idx]
            val itemIsFocused = manualFocusIndex != null && isZoneFocused && idx == manualFocusIndex
            MediaCard(
                item = item.copy(
                    title = buildCardTitle(item),
                    subtitle = buildCardSubtitle(item),
                    releaseDate = null,
                    year = ""
                ),
                width = itemWidth,
                isLandscape = !usePosterCards,
                showProgress = false,
                titleMaxLines = titleMaxLines,
                subtitleMaxLines = 1,
                isFocusedOverride = itemIsFocused,
                enableSystemFocus = manualFocusIndex == null && !isTouchDevice,
                onFocused = {},
                onClick = { onItemClick(item) },
                modifier = Modifier
                    .semantics { selected = itemIsFocused }
                    .then(if (isTouchDevice) Modifier.clickable { onItemClick(item) } else Modifier)
            )
        }
        if (loadFailed || scanPaused) {
            item(key = "discover_retry", span = { GridItemSpan(maxLineSpan) }) {
                val retryFocused = isZoneFocused && manualFocusIndex == items.size
                Text(
                    text = if (loadFailed) stringResource(R.string.search_discover_load_failed) + " · " + stringResource(R.string.retry)
                    else stringResource(R.string.search_discover_load_more),
                    color = if (retryFocused) Color.White else TextSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (retryFocused) Pink else Color.Transparent)
                        .clickable(onClick = onRetry)
                        .padding(16.dp)
                )
            }
        }
        if (isLoading) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(Modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) { LoadingIndicator(color = Pink, size = 32.dp) }
            }
        }
    }
}

private fun buildCardTitle(item: MediaItem): String {
    // Return the clean title — year is shown separately in the subtitle
    return item.title
}

@Composable
private fun buildCardSubtitle(item: MediaItem): String {
    val mediaLabel = when (item.mediaType) {
        MediaType.TV -> stringResource(R.string.series)
        MediaType.MOVIE -> stringResource(R.string.movie)
    }
    val year = item.year.takeIf { it.isNotBlank() }
    return if (year != null) "$mediaLabel · $year" else mediaLabel
}

/**
 * PANEL is the open list under a filter chip. It is its own zone because while it is open the
 * direction keys belong to it and to nothing else — the chip row underneath must not move at
 * the same time, which is the usual way a panel ends up reordering things behind itself.
 */
private enum class FocusZone { SIDEBAR, SEARCH_INPUT, FILTERS, RESULTS, PANEL }
