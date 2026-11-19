package com.arflix.tv.ui.screens.collections

import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.tv.material3.Icon
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.components.AppTopBarHeight
import com.arflix.tv.ui.components.movieGenreNameRes
import com.arflix.tv.ui.components.tvGenreNameRes
import com.arflix.tv.ui.screens.home.HomeFocusState
import com.arflix.tv.ui.screens.home.NetflixHomeContent
import com.arflix.tv.ui.screens.home.NetflixTvHomeRowsLayer
import com.arflix.tv.ui.screens.home.navigableHomeItems
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.appBackgroundDark
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A streaming-service page drawn like the Netflix-style TV Home (see [ServiceHome]): the big
 * card, then rows that all come from the service. TV only; touch devices keep the grid.
 */
@Composable
internal fun ServiceHomeScreen(
    service: CatalogConfig,
    onOpen: (item: MediaItem, play: Boolean) -> Unit,
    onViewAll: (ServiceHome.ViewAll) -> Unit,
    onBack: () -> Unit,
    viewModel: ServiceHomeViewModel = hiltViewModel()
) {
    LaunchedEffect(service.id) { viewModel.load(service) }
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val kids by viewModel.kidsFilterState.collectAsStateWithLifecycle()
    val cardLogoUrls by viewModel.cardLogoUrls.collectAsStateWithLifecycle()
    val trailerSettings by viewModel.trailerSettings.collectAsStateWithLifecycle()
    val page = state.page
    val visibleRows = remember(page.rows, kids) { viewModel.visibleRows(page, kids) }

    val billboard = remember(page.billboard, visibleRows, kids) {
        page.billboard?.takeIf { viewModel.allows(it, kids) }
            ?: visibleRows.flatMap { it.items }.firstOrNull { !it.backdrop.isNullOrBlank() }
    }

    ServiceHomeContent(
        service = service, state = state, visibleRows = visibleRows, billboard = billboard,
        cardLogoUrls = cardLogoUrls, trailerSettings = trailerSettings,
        onFocus = viewModel::focus, onPreloadLogos = viewModel::preloadLogos,
        onFilter = viewModel::selectFilter, onRetry = viewModel::retry,
        onOpen = onOpen, onViewAll = onViewAll, onBack = onBack
    )
}

/** Cards a row shows at once, and the rows on screen below the focused one, for logo loading. */
private const val VISIBLE_CARDS = 8
private const val LOGO_ROWS = 3

private data class ServiceHomeRails(
    val categories: List<Category> = emptyList(),
    val hasMore: Map<String, Boolean> = emptyMap(),
    val viewAllByRow: Map<String, ServiceHome.ViewAll?> = emptyMap()
)

@Composable
internal fun ServiceHomeContent(
    service: CatalogConfig,
    state: ServiceHomeUiState,
    visibleRows: List<ServiceHomeRowState>,
    billboard: MediaItem?,
    cardLogoUrls: Map<String, String>,
    trailerSettings: ServiceHomeTrailerSettings,
    onFocus: (MediaItem?) -> Unit,
    onPreloadLogos: (List<MediaItem>) -> Unit,
    onFilter: (ServiceHome.Filter) -> Unit,
    onRetry: () -> Unit,
    onOpen: (MediaItem, Boolean) -> Unit,
    onViewAll: (ServiceHome.ViewAll) -> Unit,
    onBack: () -> Unit
) {
    // Saved like Home's, so returning from a title lands on the card it was opened from.
    val focusState = rememberSaveable(service.id, saver = HomeFocusState.Saver) {
        HomeFocusState().apply {
            isHeroFocused = true
            userHasNavigated = true
            sidebarFocusIndex = 0
        }
    }
    val titles = visibleRows.associate { it.row.id to serviceRowTitle(it.row.id, service.title) }
    val incomingRails = remember(visibleRows, titles) {
        ServiceHomeRails(
            categories = visibleRows.map { Category(id = it.row.id, title = titles.getValue(it.row.id), items = it.items) },
            hasMore = visibleRows.associate { it.row.id to (it.row.viewAll != null) },
            viewAllByRow = visibleRows.associate { it.row.id to it.row.viewAll }
        )
    }
    var rails by remember(service.id) { mutableStateOf(ServiceHomeRails()) }
    SideEffect {
        // The rendered list and the key handler adopt the new rows only after focus is re-anchored.
        if (rails != incomingRails) {
            focusState.reconcile(incomingRails.categories, incomingRails.hasMore)
            rails = incomingRails
        }
    }
    val (categories, hasMore, viewAllByRow) = rails
    val latestViewAllByRow by rememberUpdatedState(viewAllByRow)
    val latestOnViewAll by rememberUpdatedState(onViewAll)
    val page = state.page
    val showEmpty = !page.isLoading && visibleRows.isEmpty()
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val focusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    val latestOnBack by rememberUpdatedState(onBack)

    fun requestFocus() {
        scope.launch {
            // Clears the screen's enter animation before touching the focus tree.
            delay(300)
            runCatching { focusRequester.requestFocus() }
        }
    }
    LaunchedEffect(Unit) { requestFocus() }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event -> if (event == Lifecycle.Event.ON_RESUME) requestFocus() }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    // The focused card's details and the clear logos around it, as Home loads them.
    val focusedItem = if (focusState.isHeroFocused || focusState.isSidebarFocused) null else {
        categories.getOrNull(focusState.currentRowIndex)
            ?.let { navigableHomeItems(it.items) }
            ?.let { items -> items.getOrNull(focusState.currentItemIndex)?.let { it to items } }
    }
    val billboardFocused = focusState.isHeroFocused && !focusState.isSidebarFocused
    LaunchedEffect(billboardFocused, billboard?.id, billboard?.mediaType) {
        if (billboardFocused) onFocus(billboard)
    }
    LaunchedEffect(focusedItem?.first?.id, focusedItem?.first?.mediaType) {
        val (item, rowItems) = focusedItem ?: return@LaunchedEffect
        onFocus(item)
        val at = rowItems.indexOf(item)
        onPreloadLogos(rowItems.subList((at - 1).coerceAtLeast(0), (at + VISIBLE_CARDS).coerceAtMost(rowItems.size)))
    }
    // Every card carries its title's clear logo, so the rows on screen get theirs up front.
    val logoRow = if (focusedItem == null) 0 else focusState.currentRowIndex
    val logoRowIds = categories.drop(logoRow).take(LOGO_ROWS).map { it.id }
    LaunchedEffect(logoRowIds) {
        categories.drop(logoRow).take(LOGO_ROWS).forEach { row ->
            onPreloadLogos(navigableHomeItems(row.items).take(VISIBLE_CARDS))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            .testTag("service_home")
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                val key = serviceHomeKey(event) ?: return@onPreviewKeyEvent false
                if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent true
                // A held Select or Back must not open or leave twice.
                if ((key == ServiceHomeKey.SELECT || key == ServiceHomeKey.BACK) && event.nativeKeyEvent.repeatCount > 0) {
                    return@onPreviewKeyEvent true
                }
                when (val action = handleServiceHomeKey(
                    key, focusState, categories, hasMore, billboard, isRtl, SystemClock.elapsedRealtime(), state.filter,
                    canRetry = showEmpty
                )) {
                    ServiceHomeAction.Moved -> Unit
                    is ServiceHomeAction.Filter -> if (action.filter != state.filter) {
                        focusState.startFilteredPage()
                        onFilter(action.filter)
                    }
                    ServiceHomeAction.Retry -> onRetry()
                    is ServiceHomeAction.Open -> onOpen(action.item, action.play)
                    is ServiceHomeAction.ViewAll -> viewAllByRow[action.rowId]?.let(onViewAll)
                    ServiceHomeAction.OpenGrid -> onViewAll(ServiceHome.grid(state.filter))
                    ServiceHomeAction.Exit -> latestOnBack()
                }
                true
            }
    ) {
        ServiceHomeHeader(
            serviceName = service.title,
            selected = state.filter,
            focusedIndex = focusState.sidebarFocusIndex.takeIf { focusState.isSidebarFocused },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 40.dp, end = 40.dp, top = 18.dp)
                .height(AppTopBarHeight - 18.dp)
        )
        if (showEmpty) {
            Column(
                modifier = Modifier.align(Alignment.Center).padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(if (page.sourceLoadFailed) R.string.collection_failed_load else R.string.collection_empty),
                    style = ArflixTypography.sectionTitle,
                    color = Color.White
                )
                ServiceHomeFilterButton(
                    label = stringResource(R.string.retry),
                    isSelected = false,
                    isFocused = !focusState.isSidebarFocused,
                    modifier = Modifier.focusProperties { canFocus = false }.clickable(role = Role.Button, onClick = onRetry)
                )
            }
        } else NetflixTvHomeRowsLayer(
            categories = categories,
            cardLogoUrls = cardLogoUrls,
            focusState = focusState,
            contentStartPadding = 36.dp,
            categoryHasMoreMap = hasMore,
            smoothScrolling = true,
            onLoadMoreCategory = {},
            onViewAllCategory = { id -> latestViewAllByRow[id]?.let(latestOnViewAll) },
            netflix = NetflixHomeContent(
                billboardItem = billboard,
                billboardLogoUrl = billboard?.let { cardLogoUrls["${it.mediaType}_${it.id}"] },
                // Trailers follow the profile's settings, as on Home: the big card's while it has
                // focus, a card's (after the profile's delay) while focus rests on it.
                billboardTrailerKey = state.trailer?.takeIf {
                    trailerSettings.autoPlay && billboardFocused && it.isFor(billboard)
                }?.youtubeKey,
                featuredItem = state.featured,
                cardTrailerKey = state.trailer?.takeIf {
                    trailerSettings.autoPlay && trailerSettings.inCards && it.isFor(focusedItem?.first)
                }?.youtubeKey,
                cardTrailerDelayMs = trailerSettings.delaySeconds * 1000L,
                trailerVolume = if (trailerSettings.soundEnabled) 1f else 0f,
                billboardHidden = !page.isLoading && billboard == null
            ),
            isSpecialItem = { false },
            onItemClick = { onOpen(it, false) }
        )
    }
}

private fun serviceHomeKey(event: KeyEvent): ServiceHomeKey? = when (event.key) {
    Key.DirectionLeft -> ServiceHomeKey.LEFT
    Key.DirectionRight -> ServiceHomeKey.RIGHT
    Key.DirectionUp -> ServiceHomeKey.UP
    Key.DirectionDown -> ServiceHomeKey.DOWN
    Key.Enter, Key.DirectionCenter, Key.NumPadEnter -> ServiceHomeKey.SELECT
    Key.Back, Key.Escape -> ServiceHomeKey.BACK
    else -> null
}

@Composable
internal fun serviceRowTitle(rowId: String, service: String): String = when (rowId) {
    // Keep a trailing "+" (Disney+) on its side in right-to-left titles.
    ServiceHome.TOP10_SERIES_ROW_ID -> stringResource(R.string.service_home_top10_series, service + "\u200E")
    ServiceHome.TOP10_MOVIES_ROW_ID -> stringResource(R.string.service_home_top10_movies, service + "\u200E")
    ServiceHome.NEW_ROW_ID -> stringResource(R.string.service_home_new, service + "\u200E")
    ServiceHome.ORIGINALS_ROW_ID -> stringResource(R.string.service_home_originals, service + "\u200E")
    ServiceHome.TOP_RATED_SERIES_ROW_ID -> stringResource(R.string.service_home_top_rated_series)
    ServiceHome.TOP_RATED_MOVIES_ROW_ID -> stringResource(R.string.service_home_top_rated_movies)
    else -> {
        val genre = ServiceHome.genres.firstOrNull { ServiceHome.GENRE_ROW_PREFIX + it.key == rowId }
        val res = genre?.let {
            if (it.labelIsSeries) it.seriesGenreId?.let(::tvGenreNameRes) else it.movieGenreId?.let(::movieGenreNameRes)
        }
        if (res != null) stringResource(res) else genre?.key ?: service
    }
}

/** The service's name, its All / Series / Movies buttons and its Filter button, where Home has its top bar. */
@Composable
private fun ServiceHomeHeader(
    serviceName: String,
    selected: ServiceHome.Filter,
    focusedIndex: Int?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = serviceName,
            // The service name is Latin: its own direction keeps "Disney+" whole in right-to-left layouts.
            style = ArflixTypography.heroTitle.copy(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textDirection = TextDirection.Content
            ),
            color = Color.White,
            maxLines = 1
        )
        Spacer(Modifier.width(18.dp))
        ServiceHome.Filter.entries.forEach { filter ->
            ServiceHomeFilterButton(
                label = stringResource(
                    when (filter) {
                        ServiceHome.Filter.ALL -> R.string.service_home_filter_all
                        ServiceHome.Filter.SERIES -> R.string.tv_shows
                        ServiceHome.Filter.MOVIES -> R.string.movies
                    }
                ),
                isSelected = filter == selected,
                isFocused = focusedIndex == filter.ordinal
            )
        }
        Spacer(Modifier.width(10.dp))
        ServiceHomeFilterButton(
            label = stringResource(R.string.service_home_filter_grid),
            isSelected = false,
            isFocused = focusedIndex == SERVICE_HOME_GRID_BUTTON_INDEX,
            icon = Icons.Default.Tune
        )
    }
}

@Composable
private fun ServiceHomeFilterButton(
    label: String,
    isSelected: Boolean,
    isFocused: Boolean,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    val shape = RoundedCornerShape(50)
    Box(
        modifier = modifier
            .clip(shape)
            .background(
                when {
                    isFocused -> Color.White
                    isSelected -> Color.White.copy(alpha = 0.22f)
                    else -> Color.Transparent
                }
            )
            .border(1.dp, Color.White.copy(alpha = if (isSelected || isFocused) 0f else 0.35f), shape)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        val color = if (isFocused) Color.Black else Color.White
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text = label,
                style = ArflixTypography.label.copy(fontSize = 17.sp, fontWeight = FontWeight.SemiBold),
                color = color,
                maxLines = 1
            )
        }
    }
}
