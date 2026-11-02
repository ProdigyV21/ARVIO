package com.arflix.tv.ui.screens.home

import android.os.SystemClock
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListPrefetchScope
import androidx.compose.foundation.lazy.LazyListPrefetchStrategy
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Precision
import kotlinx.coroutines.delay
import com.arflix.tv.R
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.KidsStarterCatalogs
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.isPortrait
import com.arflix.tv.ui.components.AppTopBarHeight
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.CardClearLogo
import com.arflix.tv.ui.components.TrailerPlayer
import com.arflix.tv.ui.components.WatchedBadge
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode
import com.arflix.tv.ui.components.timeRemainingBadgeLabel
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.util.Constants
import com.arflix.tv.util.TmdbImageSizing
import com.arflix.tv.util.parseRatingValue
import com.arflix.tv.ui.components.ImdbSvgRatingBadge
import coil.imageLoader
import kotlin.math.abs
import kotlin.math.roundToInt
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.LayoutCoordinates

private const val NETFLIX_RAIL_MORPH_MS = 220
/** Margin past the vertical scroll before a row that left through the top collapses. */
private const val NETFLIX_COLLAPSE_SETTLE_MS = 80
/** Resting row titles draw at 17sp of the focused row's 20sp. */
private const val NETFLIX_RESTING_HEADING_SCALE = 17f / 20f

/** What the Netflix-style TV Home draws beyond the classic rows. Null selects the classic Home. */
@Immutable
internal data class NetflixHomeContent(
    val billboardItem: MediaItem?,
    val billboardLogoUrl: String?,
    val billboardTrailerKey: String?,
    /** The ViewModel hero: the focused card with its loaded details (overview, runtime...). */
    val featuredItem: MediaItem?,
    val cardTrailerKey: String?,
    val cardTrailerDelayMs: Long,
    val trailerVolume: Float,
    /** The ViewModel hero's overview; for a channel, the programme on air. */
    val featuredOverview: String? = null,
    /**
     * The hero preview player while a channel is the hero ([channelPreviewItem]). Channel
     * cards share one surface on it: many IPTV streams rarely send keyframes, so moving the
     * decoder to a new surface would leave the video black under playing audio.
     */
    val channelPreviewPlayer: androidx.media3.exoplayer.ExoPlayer? = null,
    val channelPreviewItem: MediaItem? = null,
    /** Home finished loading with no billboard candidate: collapse the slot instead of a black box. */
    val billboardHidden: Boolean = false
)

private val NETFLIX_BILLBOARD_TOP10_IDS = setOf("top10_movies_today", "top10_shows_today")
private val NETFLIX_BILLBOARD_TRENDING_IDS = setOf("trending_movies", "trending_tv")
private val NETFLIX_BILLBOARD_KIDS_IDS = setOf(
    KidsStarterCatalogs.ROW_MOVIES, KidsStarterCatalogs.ROW_ANIMATED,
    KidsStarterCatalogs.ROW_SHOWS, KidsStarterCatalogs.ROW_FAMILY_SHOWS
)

/**
 * Billboard candidates: today's Top 10 movies and series. When those rows are hidden,
 * any other Top 10 row, then the trending rows, then the kids starter rows. Those exist
 * on a kids profile, where the age filter can empty every Top 10 and trending row and an
 * empty candidate list left a black billboard. Last, any other row except Continue
 * Watching, for profiles whose Home has none of those rows (only collections and addon
 * catalogs); special items (channels, collections, sports) are filtered out per item.
 */
internal fun netflixBillboardCandidates(
    categories: List<Category>,
    isSpecialItem: (MediaItem) -> Boolean
): List<MediaItem> {
    fun itemsOf(rows: List<Category>) = rows.asSequence()
        .flatMap { it.items.asSequence() }
        .filter { isActionableHomeItem(it) && !isSpecialItem(it) && !it.backdrop.isNullOrBlank() }
        .distinctBy { homeRowItemKey(it) }
        .toList()

    val isOtherTop10 = { category: Category ->
        category.id.contains("top10", ignoreCase = true) ||
            category.title.contains("Top 10", ignoreCase = true)
    }
    return sequenceOf<(Category) -> Boolean>(
        { it.id in NETFLIX_BILLBOARD_TOP10_IDS },
        isOtherTop10,
        { it.id in NETFLIX_BILLBOARD_TRENDING_IDS },
        { it.id in NETFLIX_BILLBOARD_KIDS_IDS },
        { it.id != "continue_watching" }
    )
        .map { matches -> itemsOf(categories.filter(matches)) }
        .firstOrNull { it.isNotEmpty() }
        .orEmpty()
}

internal fun stableNetflixBillboard(previous: MediaItem?, candidates: List<MediaItem>, pick: (List<MediaItem>) -> MediaItem): MediaItem? =
    previous?.let { selected -> candidates.firstOrNull { homeRowItemKey(it) == homeRowItemKey(selected) } ?: selected }
        ?: candidates.takeIf { it.isNotEmpty() }?.let(pick)

/**
 * D-pad handling the Netflix layout adds on top of the classic Home: the billboard is a
 * focus stop between the top bar and the first row. Returns null for keys it leaves to
 * the classic handler.
 */
internal fun handleNetflixHomeKey(
    event: KeyEvent,
    netflix: NetflixHomeContent,
    focusState: HomeFocusState,
    categories: List<Category>,
    isRtl: Boolean,
    onBillboardOpen: (MediaItem, Boolean) -> Unit,
    onOpenContextMenu: (MediaItem, Boolean) -> Unit
): Boolean? {
    val keyDown = event.type == KeyEventType.KeyDown
    val isSelect = event.key == Key.Enter || event.key == Key.DirectionCenter
    val isBack = event.key == Key.Back || event.key == Key.Escape
    val now = SystemClock.elapsedRealtime()
    val billboardFocused = focusState.isHeroFocused && !focusState.isSidebarFocused

    if (billboardFocused) {
        if (!keyDown) return if (isSelect) true else null
        return when (event.key) {
            Key.DirectionLeft, Key.DirectionRight -> {
                val forward = (event.key == Key.DirectionRight) != isRtl
                focusState.heroButtonIndex = if (forward) 1 else 0
                focusState.userHasNavigated = true
                focusState.lastNavEventTime = now
                true
            }
            Key.DirectionUp -> {
                focusState.userHasNavigated = true
                focusState.isSidebarFocused = true
                focusState.lastNavEventTime = now
                true
            }
            Key.DirectionDown -> {
                val firstRow = categories.firstOrNull() ?: return true
                focusState.userHasNavigated = true
                focusState.isHeroFocused = false
                focusState.currentRowIndex = 0
                // Catalog refreshes reconcile focus to the preferred row: make it this one.
                focusState.preferredCategoryId = firstRow.id
                focusState.currentItemIndex = focusState.restoredItemIndex(
                    firstRow,
                    focusState.rowItemIndicesByCategoryId[firstRow.id] ?: 0
                )
                focusState.lastNavEventTime = now
                true
            }
            Key.Enter, Key.DirectionCenter -> {
                if (event.nativeKeyEvent.repeatCount == 0) {
                    netflix.billboardItem?.takeIf { isActionableHomeItem(it) }
                        ?.let { onBillboardOpen(it, focusState.heroButtonIndex == 0) }
                }
                true
            }
            Key.Menu, Key.Info -> {
                netflix.billboardItem?.takeIf { isActionableHomeItem(it) }
                    ?.let { onOpenContextMenu(it, false) }
                true
            }
            Key.Back, Key.Escape -> {
                focusState.isSidebarFocused = true
                true
            }
            else -> null
        }
    }

    if (!keyDown) return null

    // The top bar above the billboard: Down returns to the billboard, not a row.
    if (focusState.isSidebarFocused && focusState.isHeroFocused && event.key == Key.DirectionDown) {
        focusState.isSidebarFocused = false
        focusState.lastNavEventTime = now
        return true
    }

    // A row: Up from the first row and Back both scroll back to the billboard.
    val rowFocused = !focusState.isSidebarFocused && !focusState.isHeroFocused
    val toBillboard = (event.key == Key.DirectionUp && focusState.currentRowIndex == 0) || isBack
    if (rowFocused && toBillboard && netflix.billboardItem != null) {
        categories.getOrNull(focusState.currentRowIndex)?.id?.let { categoryId ->
            focusState.rowItemIndicesByCategoryId[categoryId] = focusState.currentItemIndex
        }
        focusState.recordSelection(categories)
        focusState.userHasNavigated = true
        focusState.isHeroFocused = true
        focusState.heroButtonIndex = 0
        focusState.lastNavEventTime = now
        return true
    }
    return null
}

/** TV rows for the Netflix layout: the billboard scrolls with the rows as their first item. */
@Composable
internal fun NetflixTvHomeRowsLayer(
    categories: List<Category>,
    cardLogoUrls: Map<String, String>,
    focusState: HomeFocusState,
    contentStartPadding: Dp,
    categoryHasMoreMap: Map<String, Boolean>,
    smoothScrolling: Boolean,
    onLoadMoreCategory: (String) -> Unit,
    onViewAllCategory: (String) -> Unit,
    netflix: NetflixHomeContent,
    isSpecialItem: (MediaItem) -> Boolean,
    onItemClick: (MediaItem) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val screenHeight = maxHeight
        val topInset = AppTopBarHeight + 6.dp
        // About 70% of the screen, leaving the first row's title and the top of
        // its cards peeking below it.
        val billboardHeight = (maxHeight - topInset - 70.dp).coerceAtLeast(200.dp)
        // Focused-row card height: room for the row title above, the description
        // below and a peek of the next row.
        val bigCardHeight = (maxHeight * 0.42f).coerceIn(170.dp, 330.dp)
        @OptIn(ExperimentalFoundationApi::class)
        val listState = rememberLazyListState(prefetchStrategy = remember { NetflixNextRowPrefetchStrategy() })
        val targetIndex = if (focusState.isHeroFocused) 0 else focusState.currentRowIndex + 1
        val focusedRowId = if (focusState.isHeroFocused) null else categories.getOrNull(focusState.currentRowIndex)?.id
        var appliedTarget by remember { mutableIntStateOf(-1) }
        var appliedScrollReset by rememberSaveable { mutableIntStateOf(0) }
        val verticalOffset = remember { Animatable(0f) }
        var verticalAnimating by remember { mutableStateOf(false) }
        LaunchedEffect(targetIndex, focusedRowId, focusState.scrollResetRequest) {
            val previousTarget = appliedTarget
            val reset = appliedScrollReset != focusState.scrollResetRequest
            appliedTarget = targetIndex
            appliedScrollReset = focusState.scrollResetRequest
            var target = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == targetIndex }
            if (!reset && previousTarget >= 0 && target == null &&
                targetIndex == listState.firstVisibleItemIndex - 1 && listState.firstVisibleItemScrollOffset <= 1
            ) {
                // Reveal the row just above the viewport before measuring it. This also
                // keeps returning from the first rail to the billboard animated.
                listState.scrollBy(-1f)
                target = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == targetIndex }
            }
            if (!reset && previousTarget >= 0 && target != null && abs(targetIndex - previousTarget) <= 4) {
                verticalAnimating = true
                try {
                    verticalOffset.snapTo(target.offset.toFloat())
                    listState.requestScrollToItem(targetIndex, -verticalOffset.value.roundToInt())
                    verticalOffset.animateTo(0f, tween(NETFLIX_RAIL_MORPH_MS, easing = FastOutSlowInEasing)) {
                        listState.requestScrollToItem(targetIndex, -value.roundToInt())
                    }
                } finally {
                    verticalAnimating = false
                }
            } else {
                listState.scrollToItem(targetIndex)
            }
        }
        // Rows inserted or removed above the focused one (late catalog / channel loads)
        // shift the list under a keyed LazyColumn: re-anchor to the focused row.
        val rowIds = remember(categories) { categories.map { it.id } }
        LaunchedEffect(rowIds) {
            if (appliedTarget >= 0 && !verticalAnimating && !listState.isScrollInProgress &&
                listState.firstVisibleItemIndex != targetIndex
            ) {
                listState.scrollToItem(targetIndex)
            }
        }

        // Where the focused channel card's media area is, relative to this layer.
        var layerCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }
        var channelMediaBounds by remember { mutableStateOf<Rect?>(null) }
        val onChannelCardPlaced: (LayoutCoordinates) -> Unit = { card ->
            layerCoordinates?.takeIf { it.isAttached && card.isAttached }?.let { layer ->
                channelMediaBounds = layer.localBoundingBoxOf(card, clipBounds = false)
            }
        }
        val focusedChannel = if (!focusState.isSidebarFocused && !focusState.isHeroFocused) {
            categories.getOrNull(focusState.currentRowIndex)
                ?.let { row -> navigableHomeItems(row.items).getOrNull(focusState.currentItemIndex) }
                ?.takeIf { it.isIptvChannel() }
        } else null

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topInset)
                .clipToBounds()
                .onGloballyPositioned { layerCoordinates = it }
        ) {
            LazyColumn(
                state = listState,
                userScrollEnabled = false,
                // Lets the last row scroll up to the top like every other row.
                contentPadding = PaddingValues(bottom = screenHeight),
                modifier = Modifier.fillMaxSize().testTag("netflix_vertical_rails")
            ) {
                // Stays item 0 even when hidden, so row N remains list index N + 1.
                item(key = "netflix_billboard", contentType = "netflix_billboard") {
                    if (netflix.billboardHidden) return@item
                    NetflixBillboard(
                        item = netflix.billboardItem,
                        logoUrl = netflix.billboardLogoUrl,
                        trailerKey = netflix.billboardTrailerKey,
                        trailerVolume = netflix.trailerVolume,
                        autoplayEnabled = focusState.isHeroFocused,
                        isFocused = focusState.isHeroFocused && !focusState.isSidebarFocused,
                        focusedButton = focusState.heroButtonIndex,
                        horizontalPadding = contentStartPadding,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(billboardHeight)
                    )
                    Spacer(Modifier.height(14.dp))
                }
                itemsIndexed(
                    items = categories,
                    key = { _, category -> stableHomeRowKey("netflix", category.id) },
                    contentType = { _, category ->
                        if (netflixRowIsSpecial(category, isSpecialItem)) "netflix_special_row" else "netflix_row"
                    }
                ) { rowIndex, category ->
                    val rowIsFocused = !focusState.isSidebarFocused && !focusState.isHeroFocused &&
                        rowIndex == focusState.currentRowIndex
                    val rowHasMore = categoryHasMoreMap[category.id] == true
                    val viewAllIndex = remember(category.items, rowHasMore) {
                        homeRowViewAllIndex(category, rowHasMore)
                    }
                    val focusedItemIndex = when {
                        !rowIsFocused -> -1
                        viewAllIndex >= 0 && focusState.currentItemIndex == viewAllIndex -> viewAllIndex
                        else -> clampHomeItemIndex(category.items, focusState.currentItemIndex)
                    }
                    val onRowLoadMore = remember(category.id) { { onLoadMoreCategory(category.id) } }
                    val onRowViewAll = remember(category.id) { { onViewAllCategory(category.id) } }
                    val rowUsePosterCards =
                        rememberCatalogueRowLayoutMode("home:${category.id}") == CardLayoutMode.POSTER
                    if (netflixRowIsSpecial(category, isSpecialItem)) {
                        // Channel, service and sports tiles have no backdrop/overview to
                        // expand into: they keep the classic rail.
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(if (category.isPortrait(rowUsePosterCards)) 245.dp else 202.dp)
                                .clipToBounds()
                        ) {
                            ContentRow(
                                category = category,
                                scrollResetRequest = focusState.scrollResetRequest,
                                cardLogoUrls = cardLogoUrls,
                                isCurrentRow = rowIsFocused,
                                usePosterCards = rowUsePosterCards,
                                startPadding = contentStartPadding,
                                categoryHasMore = rowHasMore,
                                smoothScrolling = smoothScrolling,
                                onLoadMore = onRowLoadMore,
                                showViewAll = viewAllIndex >= 0,
                                onViewAll = onRowViewAll,
                                focusedItemIndex = focusedItemIndex,
                                isFastScrolling = false,
                                onItemClick = onItemClick,
                                onItemFocused = { _, _ -> }
                            )
                        }
                    } else {
                        NetflixContentRow(
                            category = category,
                            scrollResetRequest = focusState.scrollResetRequest,
                            cardLogoUrls = cardLogoUrls,
                            isCurrentRow = rowIsFocused,
                            collapsesOffScreen = !focusState.isSidebarFocused && !focusState.isHeroFocused &&
                                rowIndex < focusState.currentRowIndex,
                            focusedItemIndex = focusedItemIndex,
                            restingItemIndex = focusState.rowItemIndicesByCategoryId[category.id] ?: 0,
                            usePosterCards = rowUsePosterCards,
                            bigCardHeight = bigCardHeight,
                            startPadding = contentStartPadding,
                            categoryHasMore = rowHasMore,
                            showViewAll = viewAllIndex >= 0,
                            featuredItem = netflix.featuredItem,
                            featuredOverview = netflix.featuredOverview,
                            onChannelCardPlaced = onChannelCardPlaced,
                            trailerKey = if (rowIsFocused) netflix.cardTrailerKey else null,
                            trailerDelayMs = netflix.cardTrailerDelayMs,
                            trailerVolume = netflix.trailerVolume,
                            onLoadMore = onRowLoadMore,
                            onViewAll = onRowViewAll,
                            onItemClick = onItemClick
                        )
                    }
                }
            }
            // One surface for every channel card (see NetflixHomeContent.channelPreviewPlayer):
            // it follows focus along the row instead of each card creating its own.
            val previewPlayer = netflix.channelPreviewPlayer
            val bounds = channelMediaBounds
            if (focusedChannel != null && previewPlayer != null && bounds != null) {
                val density = LocalDensity.current
                NetflixChannelPreview(
                    player = previewPlayer,
                    focusedItem = focusedChannel,
                    streamItem = netflix.channelPreviewItem,
                    modifier = Modifier
                        // Measured bounds are physical (from the left edge); anchor there in RTL too.
                        .align(AbsoluteAlignment.TopLeft)
                        .absoluteOffset { IntOffset(bounds.left.roundToInt(), bounds.top.roundToInt()) }
                        .size(with(density) { bounds.width.toDp() }, with(density) { bounds.height.toDp() })
                )
            }
        }
    }
}

private fun netflixRowIsSpecial(category: Category, isSpecialItem: (MediaItem) -> Boolean): Boolean =
    category.id.startsWith("collection_row_") ||
        category.items.firstOrNull { !it.isPlaceholder }?.let(isSpecialItem) == true

@Composable
private fun NetflixBillboard(
    item: MediaItem?,
    logoUrl: String?,
    trailerKey: String?,
    trailerVolume: Float,
    autoplayEnabled: Boolean,
    isFocused: Boolean,
    focusedButton: Int,
    horizontalPadding: Dp,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val shape = RoundedCornerShape(16.dp)
    val borderAlpha by animateFloatAsState(
        targetValue = if (isFocused) 0.85f else 0.12f,
        animationSpec = tween(160),
        label = "netflix-billboard-border"
    )
    Box(modifier = modifier.padding(horizontal = horizontalPadding).testTag("netflix_billboard")) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.White.copy(alpha = borderAlpha), shape)
                // The border stays outside the media, which must not be covered.
                .padding(3.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            // The trailer and artwork fill the width at 16:9; the billboard crops them.
            val mediaWidth = maxWidth
            val mediaHeight = maxOf(maxHeight, maxWidth * (9f / 16f))
            val backdropRequest = remember(item?.backdrop, item?.image, mediaWidth, mediaHeight) {
                val url = (item?.backdrop ?: item?.image)?.takeIf { it.isNotBlank() }
                url?.let {
                    val widthPx = with(density) { mediaWidth.roundToPx() }.coerceAtLeast(1)
                    val heightPx = with(density) { mediaHeight.roundToPx() }.coerceAtLeast(1)
                    ImageRequest.Builder(context)
                        .data(TmdbImageSizing.forSlot(it, widthPx, heightPx, TmdbImageSizing.Kind.BACKDROP))
                        .size(widthPx, heightPx)
                        .precision(Precision.INEXACT)
                        .crossfade(250)
                        .build()
                }
            }
            if (backdropRequest != null) {
                AsyncImage(
                    model = backdropRequest,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            if (autoplayEnabled && trailerKey != null) {
                TrailerPlayer(
                    youtubeKey = trailerKey,
                    delayMs = NETFLIX_RAIL_MORPH_MS.toLong(),
                    volume = trailerVolume,
                    modifier = Modifier.requiredSize(mediaWidth, mediaHeight)
                )
            }

            if (item != null) {
                val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithCache {
                            val start = if (isRtl) size.width else 0f
                            val end = if (isRtl) 0f else size.width
                            val sideScrim = Brush.horizontalGradient(
                                0f to Color.Black.copy(alpha = 0.82f),
                                0.35f to Color.Black.copy(alpha = 0.5f),
                                0.6f to Color.Transparent,
                                startX = start,
                                endX = end
                            )
                            val bottomScrim = Brush.verticalGradient(
                                0.55f to Color.Transparent,
                                1f to Color.Black.copy(alpha = 0.7f),
                                startY = 0f,
                                endY = size.height
                            )
                            onDrawBehind {
                                drawRect(sideScrim)
                                drawRect(bottomScrim)
                            }
                        }
                )
                NetflixBillboardInfo(
                    item = item,
                    logoUrl = logoUrl,
                    isFocused = isFocused,
                    focusedButton = focusedButton,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth(0.58f)
                        .padding(start = 36.dp, bottom = 30.dp)
                )
            }
        }
    }
}

private val NetflixTextShadow = Shadow(
    color = Color.Black.copy(alpha = 0.85f),
    offset = Offset(0f, 2f),
    blurRadius = 6f
)

@Composable
private fun NetflixBillboardInfo(
    item: MediaItem,
    logoUrl: String?,
    isFocused: Boolean,
    focusedButton: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        if (!logoUrl.isNullOrBlank()) {
            AsyncImage(
                model = logoUrl,
                contentDescription = item.title,
                contentScale = ContentScale.Fit,
                alignment = Alignment.BottomStart,
                modifier = Modifier
                    .height(88.dp)
                    .widthIn(max = 340.dp)
                    .fillMaxWidth()
            )
        } else {
            Text(
                text = item.title,
                style = ArflixTypography.heroTitle.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Black,
                    shadow = NetflixTextShadow
                ),
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        NetflixMetaLine(item = item, fontSize = 15)
        val overview = remember(item.overview, context) {
            netflixShortOverview(context.cleanOverviewText(item.overview))
        }
        Text(
            text = overview,
            style = ArflixTypography.body.copy(
                fontSize = 15.sp,
                lineHeight = 21.sp,
                shadow = NetflixTextShadow
            ),
            color = Color.White,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // With focus elsewhere (the top bar), Play keeps the resting highlight.
            NetflixBillboardButton(
                label = stringResource(R.string.play),
                icon = { tint ->
                    Icon(Icons.Filled.PlayArrow, contentDescription = null, tint = tint, modifier = Modifier.size(24.dp))
                },
                highlighted = if (isFocused) focusedButton == 0 else true
            )
            NetflixBillboardButton(
                label = stringResource(R.string.home_more_info),
                icon = { tint ->
                    Icon(Icons.Filled.Info, contentDescription = null, tint = tint, modifier = Modifier.size(22.dp))
                },
                highlighted = isFocused && focusedButton == 1
            )
        }
    }
}

@Composable
private fun NetflixBillboardButton(
    label: String,
    icon: @Composable (Color) -> Unit,
    highlighted: Boolean
) {
    val content = if (highlighted) Color.Black else Color.White
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (highlighted) Color.White else Color.White.copy(alpha = 0.22f))
            .padding(horizontal = 22.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        icon(content)
        Text(
            text = label,
            style = ArflixTypography.label.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            color = content,
            maxLines = 1
        )
    }
}

/** "Series • Drama • 2026 • 16+": the Netflix metadata line. */
@Composable
private fun NetflixMetaLine(item: MediaItem, fontSize: Int) {
    val context = LocalContext.current
    val parts = remember(item.id, item.mediaType, item.genreIds, item.year, item.duration, item.contentRating, context) {
        buildList {
            add(context.getString(if (item.mediaType == MediaType.TV) R.string.series else R.string.movie))
            context.genreNames(item.mediaType, item.genreIds).firstOrNull()?.let(::add)
            item.year.takeIf { it.isNotBlank() }?.let(::add)
            item.duration.takeIf { it.isNotBlank() && it != "0m" }?.let(::add)
            item.contentRating?.takeIf { it.isNotBlank() }?.let(::add)
        }
    }
    // The IMDb rating comes with the title's details, which can land after the card. It is
    // never waited for: it joins the end of the line when it arrives, so nothing else moves.
    // Kept per title, so it doesn't flicker away when a later copy of the item lacks it.
    var imdbRating by remember(item.id, item.mediaType) { mutableStateOf("") }
    LaunchedEffect(item.imdbRating) {
        if (parseRatingValue(item.imdbRating) > 0f) imdbRating = item.imdbRating
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = parts.joinToString("  •  "),
            style = ArflixTypography.caption.copy(
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                shadow = NetflixTextShadow
            ),
            color = Color.White.copy(alpha = 0.92f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )
        if (imdbRating.isNotEmpty()) {
            Text(
                text = "  •  ",
                style = ArflixTypography.caption.copy(fontSize = fontSize.sp, fontWeight = FontWeight.Bold),
                color = Color.White.copy(alpha = 0.92f)
            )
            ImdbSvgRatingBadge(
                rating = imdbRating,
                imageLoader = context.imageLoader,
                ratingFontSize = fontSize,
                logoWidth = (fontSize * 2.2f).dp,
                logoHeight = (fontSize * 1.1f).dp,
                textShadow = NetflixTextShadow
            )
        }
    }
}

/**
 * A Netflix row. In the focused row the focused title is a wide backdrop card with its
 * description below, and the other titles are posters of the same height. Other rows
 * keep the regular card size.
 */
@Composable
private fun NetflixContentRow(
    category: Category,
    scrollResetRequest: Int,
    cardLogoUrls: Map<String, String>,
    isCurrentRow: Boolean,
    /** The row sits above the focused one, so the vertical scroll carries it out the top. */
    collapsesOffScreen: Boolean,
    focusedItemIndex: Int,
    restingItemIndex: Int,
    usePosterCards: Boolean,
    bigCardHeight: Dp,
    startPadding: Dp,
    categoryHasMore: Boolean,
    showViewAll: Boolean,
    featuredItem: MediaItem?,
    featuredOverview: String?,
    onChannelCardPlaced: (LayoutCoordinates) -> Unit,
    trailerKey: String?,
    trailerDelayMs: Long,
    trailerVolume: Float,
    onLoadMore: () -> Unit,
    onViewAll: () -> Unit,
    onItemClick: (MediaItem) -> Unit
) {
    val isContinueWatching = category.id == "continue_watching"
    val isRanked = category.title.contains("Top 10", ignoreCase = true)
    // Prefetching this row from the vertical list also builds its first cards.
    @OptIn(ExperimentalFoundationApi::class)
    val rowState = rememberLazyListState(
        prefetchStrategy = remember { LazyListPrefetchStrategy(nestedPrefetchItemCount = 3) }
    )
    val items = remember(category.items) {
        if (category.items.isEmpty()) {
            (1..8).map { MediaItem(id = -it, title = "", mediaType = MediaType.MOVIE, isPlaceholder = true) }
        } else {
            navigableHomeItems(category.items)
        }
    }
    val itemKeys = remember(category.id, items) { stableHomeRowItemKeys(category.id, items) }
    val hasViewAllCard = showViewAll && items.none { it.isPlaceholder }
    val bigCardWidth = bigCardHeight * (16f / 9f)
    val focusedPosterWidth = bigCardHeight * (2f / 3f)
    val restingPoster = category.isPortrait(usePosterCards)
    val restingWidth = if (restingPoster) HOME_TV_POSTER_CARD_WIDTH_DP.dp else HOME_TV_LANDSCAPE_CARD_WIDTH_DP.dp

    // A row left by moving down keeps its expanded layout while the vertical scroll
    // carries it out the top, then collapses in one frame once it is off screen.
    // Morphing it on screen would re-measure every one of its cards on each frame of
    // the scroll, on top of the row growing into focus.
    val hasComposed = remember { booleanArrayOf(false) }
    SideEffect { hasComposed[0] = true }
    val collapseOffScreen = remember(isCurrentRow) { !isCurrentRow && collapsesOffScreen && hasComposed[0] }
    var holdExpanded by remember(isCurrentRow) { mutableStateOf(collapseOffScreen) }
    LaunchedEffect(collapseOffScreen) {
        if (collapseOffScreen) {
            delay((NETFLIX_RAIL_MORPH_MS + NETFLIX_COLLAPSE_SETTLE_MS).toLong())
            holdExpanded = false
        }
    }
    val expanded = isCurrentRow || holdExpanded
    val expandedIndex = if (isCurrentRow) focusedItemIndex else restingItemIndex
    val focusedItem = items.getOrNull(expandedIndex)?.takeIf { expanded && !it.isPlaceholder }
    val sizeSpec: AnimationSpec<Dp> =
        if (collapseOffScreen) snap() else tween(NETFLIX_RAIL_MORPH_MS, easing = FastOutSlowInEasing)

    // The focused title always sits in the first slot; a resting row keeps its position.
    val anchorIndex = (if (isCurrentRow) focusedItemIndex else restingItemIndex)
        .coerceIn(0, (items.size - 1 + if (hasViewAllCard) 1 else 0).coerceAtLeast(0))
    var lastAnchor by remember { mutableIntStateOf(-1) }
    var appliedScrollReset by rememberSaveable { mutableIntStateOf(0) }
    val railOffset = remember { Animatable(0f) }
    LaunchedEffect(anchorIndex, isCurrentRow, scrollResetRequest) {
        val previousAnchor = lastAnchor
        val reset = appliedScrollReset != scrollResetRequest
        val visibleAnchor = rowState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == anchorIndex }
        lastAnchor = anchorIndex
        appliedScrollReset = scrollResetRequest
        if (!reset && isCurrentRow && previousAnchor >= 0 &&
            abs(anchorIndex - previousAnchor) <= 3 && visibleAnchor != null
        ) {
            // Anchor the incoming card, not the outgoing item's changing width. A stock
            // scroll animation chases a moving destination while the old card shrinks.
            railOffset.snapTo(visibleAnchor.offset.toFloat())
            rowState.requestScrollToItem(anchorIndex, -railOffset.value.roundToInt())
            railOffset.animateTo(0f, tween(NETFLIX_RAIL_MORPH_MS, easing = FastOutSlowInEasing)) {
                rowState.requestScrollToItem(anchorIndex, -value.roundToInt())
            }
        } else {
            rowState.scrollToItem(if (reset) 0 else anchorIndex)
        }
    }
    val latestOnItemClick by rememberUpdatedState(onItemClick)

    val bottomPadding by animateDpAsState(if (expanded) 20.dp else 10.dp,
        if (collapseOffScreen) snap<Dp>() else tween<Dp>(NETFLIX_RAIL_MORPH_MS), label = "netflixRowSpacing")
    // Scaled at draw time: animating the font size re-measures the text on every frame.
    val headingScale = animateFloatAsState(if (expanded) 1f else NETFLIX_RESTING_HEADING_SCALE,
        if (collapseOffScreen) snap<Float>() else tween<Float>(NETFLIX_RAIL_MORPH_MS), label = "netflixRowHeading")
    Column(modifier = Modifier.padding(bottom = bottomPadding).testTag("netflix_row_${category.id}")) {
        Text(
            text = localizedCategoryTitle(category),
            style = ArflixTypography.sectionTitle.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                shadow = NetflixTextShadow
            ),
            color = Color.White,
            modifier = Modifier
                .padding(start = startPadding, bottom = 8.dp)
                .graphicsLayer {
                    scaleX = headingScale.value
                    scaleY = headingScale.value
                    transformOrigin = TransformOrigin(0f, 1f)
                }
        )
        LazyRow(
            state = rowState,
            modifier = Modifier.testTag("home_rail_${category.id}"),
            userScrollEnabled = false,
            contentPadding = PaddingValues(start = startPadding, end = bigCardWidth),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            itemsIndexed(
                items = items,
                key = { index, _ -> itemKeys[index] },
                contentType = { _, item ->
                    if (item.isPlaceholder) "placeholder_card" else "netflix_media_card"
                }
            ) { index, item ->
                if (!item.isPlaceholder && categoryHasMore && index >= items.size - 5) {
                    LaunchedEffect(items.size) { onLoadMore() }
                }
                val onClick = remember(item) { { latestOnItemClick(item) } }
                val featured = expanded && index == expandedIndex && !item.isPlaceholder
                val landscape = featured || (!expanded && !restingPoster)
                val cardWidth = if (featured) bigCardWidth else if (expanded) focusedPosterWidth else restingWidth
                val cardHeight = if (expanded) bigCardHeight else cardWidth / (if (landscape) (16f / 9f) else (2f / 3f))
                val animatedWidth = animateDpAsState(cardWidth, sizeSpec, label = "netflixCardWidth")
                val animatedHeight = animateDpAsState(cardHeight, sizeSpec, label = "netflixCardHeight")
                // Decode once for the target geometry, never once per animation frame.
                val artwork = if (item.isPlaceholder) null else rememberNetflixArtwork(
                    item, landscape, cardWidth, cardHeight, isContinueWatching)
                Box(Modifier.testTag("netflix_morph_${item.mediaType}_${item.id}")) {
                    // The morph size is applied at layout time: the cards get their target
                    // size and are only re-measured each frame, not recomposed.
                    Box(Modifier.netflixMorphSize(animatedWidth, animatedHeight), propagateMinConstraints = true) {
                        when {
                            featured -> NetflixFeaturedCard(
                                item = item,
                                logoUrl = cardLogoUrls["${item.mediaType}_${item.id}"],
                                width = cardWidth,
                                height = cardHeight,
                                showProgress = isContinueWatching,
                                trailerKey = trailerKey,
                                trailerDelayMs = trailerDelayMs,
                                trailerVolume = trailerVolume,
                                // A row held expanded while it leaves must not move the channel preview.
                                onChannelCardPlaced = if (isCurrentRow) onChannelCardPlaced else { _ -> },
                                artworkPainter = artwork
                            )
                            // The artwork fills whatever height the morph gives the card.
                            expanded -> MediaCard(
                                item = item,
                                width = cardWidth,
                                isLandscape = false,
                                showLogoImage = false,
                                raiseOnFocus = false,
                                showProgress = isContinueWatching,
                                showTitle = false,
                                focusedScale = 1f,
                                animateFocus = false,
                                enableSystemFocus = false,
                                artworkPainter = artwork,
                                fillArtworkHeight = true,
                                onClick = onClick
                            )
                            else -> MediaCard(
                                item = item,
                                width = cardWidth,
                                isLandscape = !restingPoster,
                                logoImageUrl = cardLogoUrls["${item.mediaType}_${item.id}"],
                                showLogoImage = true,
                                raiseOnFocus = false,
                                showProgress = isContinueWatching,
                                showTitle = false,
                                focusedScale = 1f,
                                animateFocus = false,
                                enableSystemFocus = false,
                                artworkPainter = artwork,
                                fillArtworkHeight = true,
                                onClick = onClick
                            )
                        }
                    }
                    if (isRanked && index < 10 && !item.isPlaceholder) {
                        TopRankRibbon(
                            rank = index + 1,
                            isFocused = featured,
                            compact = !expanded && !restingPoster,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
            if (hasViewAllCard) {
                item(key = "${category.id}_view_all", contentType = "view_all_card") {
                    HomeViewAllCard(
                        width = if (expanded) focusedPosterWidth else restingWidth,
                        isLandscape = !expanded && !restingPoster,
                        isFocused = isCurrentRow && focusedItemIndex == items.size,
                        enableSystemFocus = false,
                        onClick = onViewAll
                    )
                }
            }
        }
        val descriptionItem = focusedItem ?: items.getOrNull(restingItemIndex)?.takeUnless { it.isPlaceholder }
        AnimatedVisibility(
            visible = focusedItem != null,
            enter = expandVertically(tween(NETFLIX_RAIL_MORPH_MS)) + fadeIn(tween(140)),
            exit = if (collapseOffScreen) ExitTransition.None
            else shrinkVertically(tween(NETFLIX_RAIL_MORPH_MS)) + fadeOut(tween(140))
        ) {
            if (descriptionItem == null) return@AnimatedVisibility
            // The ViewModel hero carries the loaded details once it is this title.
            val isFeatured = featuredItem.isSameHomeHero(descriptionItem)
            val details = featuredItem?.takeIf { isFeatured } ?: descriptionItem
            NetflixCardDescription(
                item = details,
                overviewOverride = featuredOverview?.takeIf { isFeatured },
                // Spans the big card and the next poster, as Netflix does, so the
                // overview is not cut at the card edge.
                modifier = Modifier
                    .padding(start = startPadding + 4.dp, end = startPadding, top = 10.dp)
                    .widthIn(max = bigCardWidth + focusedPosterWidth + 12.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun NetflixCardDescription(
    item: MediaItem,
    overviewOverride: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val overview = remember(overviewOverride, item.overview, context) {
        val text = context.cleanOverviewText(overviewOverride ?: item.overview)
        // A channel's text is the programme on air; it is kept as the guide wrote it.
        if (item.isIptvChannel()) text else netflixShortOverview(text)
    }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Box(Modifier.heightIn(min = 18.dp)) {
            if (item.isIptvChannel()) NetflixLiveLine(item) else NetflixMetaLine(item = item, fontSize = 13)
        }
        Text(
            text = overview,
            style = ArflixTypography.body.copy(fontSize = 14.sp, lineHeight = 19.sp),
            color = Color.White.copy(alpha = 0.9f),
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/** Keeps each source's crop independent while blending; a portrait must not suddenly use the backdrop's crop. */
private class NetflixArtworkBlendPainter(
    private val poster: Painter?, private val backdrop: Painter?, private val progress: State<Float>
) : Painter() {
    override val intrinsicSize: Size = Size.Unspecified

    override fun DrawScope.onDraw() {
        val fraction = progress.value
        if (backdrop == null || (poster != null && fraction < 1f)) poster?.let { drawCropped(it, 1f) }
        backdrop?.let { drawCropped(it, if (poster == null) 1f else fraction) }
    }

    private fun DrawScope.drawCropped(painter: Painter, alpha: Float) {
        if (alpha <= 0f || size.width <= 0f || size.height <= 0f) return
        val source = painter.intrinsicSize.let {
            if (it.width.isFinite() && it.height.isFinite() && it.width > 0f && it.height > 0f) it else size
        }
        val scale = ContentScale.Crop.computeScaleFactor(source, size)
        val drawn = Size(source.width * scale.scaleX, source.height * scale.scaleY)
        clipRect {
            translate((size.width - drawn.width) / 2f, (size.height - drawn.height) / 2f) {
                with(painter) { draw(drawn, alpha) }
            }
        }
    }
}

/** Sizes the card to the running morph values. Reading them here, not in composition, keeps the card from recomposing every frame. */
private fun Modifier.netflixMorphSize(width: State<Dp>, height: State<Dp>): Modifier =
    layout { measurable, _ ->
        val w = width.value.roundToPx().coerceAtLeast(0)
        val h = height.value.roundToPx().coerceAtLeast(0)
        val placeable = measurable.measure(Constraints.fixed(w, h))
        layout(w, h) { placeable.place(0, 0) }
    }

/** Keep the current image while loading, then blend the ready poster/backdrop without a blank frame. */
@Composable
internal fun rememberNetflixArtwork(
    item: MediaItem, landscape: Boolean, width: Dp, height: Dp, watching: Boolean,
    imageLoader: coil.ImageLoader = LocalContext.current.imageLoader
): Painter {
    val context = LocalContext.current
    val density = LocalDensity.current
    val raw = if (landscape) item.episodeStill?.takeIf { watching && it.isNotBlank() }
        ?: item.backdrop?.takeIf(String::isNotBlank) ?: item.image else item.image
    var poster by remember(homeRowItemKey(item)) { mutableStateOf<Painter?>(null) }
    var backdrop by remember(homeRowItemKey(item)) { mutableStateOf<Painter?>(null) }
    if (raw.isBlank()) {
        return poster ?: backdrop ?: remember { androidx.compose.ui.graphics.painter.ColorPainter(Color.Transparent) }
    }
    val request = remember(raw, width, height, density, context, landscape) {
        val widthPx = with(density) { width.roundToPx() }.coerceAtLeast(1)
        val heightPx = with(density) { height.roundToPx() }.coerceAtLeast(1)
        val url = TmdbImageSizing.forSlot(raw, widthPx, heightPx,
            TmdbImageSizing.cardArtworkKind(raw, item.backdrop, landscape))
        val cacheKey = "$url|${widthPx}x$heightPx"
        ImageRequest.Builder(context).data(url.takeIf(String::isNotBlank))
            .size(widthPx, heightPx).precision(Precision.INEXACT)
            .memoryCacheKey(cacheKey).placeholderMemoryCacheKey(cacheKey).crossfade(false).build()
    }
    val painter = rememberAsyncImagePainter(request, imageLoader = imageLoader, onState = { state ->
        if (state is AsyncImagePainter.State.Success && state.result.request.memoryCacheKey == request.memoryCacheKey) {
            if (landscape) backdrop = state.painter else poster = state.painter
        }
    })
    val fade = remember(homeRowItemKey(item)) { Animatable(if (landscape) 1f else 0f) }
    val target = when {
        landscape && backdrop != null -> 1f
        !landscape && poster != null -> 0f
        poster != null -> 0f
        backdrop != null -> 1f
        landscape -> 1f
        else -> 0f
    }
    // Keep the loaded source until the requested one is ready. Rapid reversals retarget
    // the same animation from its current value rather than flashing either endpoint.
    LaunchedEffect(target) { fade.animateTo(target, tween(140)) }
    if (poster == null && backdrop == null) return painter
    return remember(poster, backdrop, fade) { NetflixArtworkBlendPainter(poster, backdrop, fade.asState()) }
}

@Composable
private fun NetflixFeaturedCard(
    item: MediaItem,
    logoUrl: String?,
    width: Dp,
    height: Dp,
    showProgress: Boolean,
    trailerKey: String?,
    trailerDelayMs: Long,
    trailerVolume: Float,
    onChannelCardPlaced: (LayoutCoordinates) -> Unit = {},
    artworkPainter: Painter? = null
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    if (item.isIptvChannel()) {
        NetflixChannelCard(item, width, height, onChannelCardPlaced)
        return
    }
    val focusedAtMs = remember(item.id, item.mediaType) { SystemClock.elapsedRealtime() }
    val imageRequest = remember(item.backdrop, item.image, width, height, artworkPainter != null) {
        if (artworkPainter != null) return@remember null
        (item.backdrop ?: item.image).takeIf { it.isNotBlank() }?.let { url ->
            val widthPx = with(density) { width.roundToPx() }.coerceAtLeast(1)
            val heightPx = with(density) { height.roundToPx() }.coerceAtLeast(1)
            ImageRequest.Builder(context)
                .data(TmdbImageSizing.forSlot(url, widthPx, heightPx, TmdbImageSizing.Kind.BACKDROP))
                .size(widthPx, heightPx)
                .precision(Precision.INEXACT)
                .build()
        }
    }
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .border(2.5.dp, Color.White, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Black)
            .testTag("netflix_card_${item.mediaType}_${item.id}")
    ) {
        // Remaining focus delay, fixed when the card opens so recompositions don't restart it.
        val startDelayMs = remember(item.id, item.mediaType, trailerKey) {
            (focusedAtMs + trailerDelayMs.coerceAtLeast(500L) - SystemClock.elapsedRealtime()).coerceAtLeast(0L)
        }
        Box(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxSize()) {
                if (artworkPainter != null) {
                    Image(artworkPainter, item.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                } else if (imageRequest != null) {
                    AsyncImage(
                        model = imageRequest,
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                if (trailerKey != null) {
                    TrailerPlayer(
                        youtubeKey = trailerKey,
                        delayMs = startDelayMs,
                        volume = trailerVolume,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0.5f to Color.Transparent,
                                1f to Color.Black.copy(alpha = 0.65f)
                            )
                        )
                )
                if (!logoUrl.isNullOrBlank()) {
                    CardClearLogo(
                        url = logoUrl,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 16.dp, bottom = if (showProgress) 22.dp else 16.dp)
                            .testTag("netflix_logo_${item.mediaType}_${item.id}")
                    )
                } else {
                    Text(
                        text = item.title,
                        style = ArflixTypography.sectionTitle.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black,
                            shadow = NetflixTextShadow
                        ),
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 16.dp, bottom = 16.dp)
                            .fillMaxWidth(0.6f)
                    )
                }
                if (showProgress) {
                    netflixContinueWatchingLabel(item)?.let { label ->
                        Text(
                            text = label,
                            style = ArflixTypography.caption.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold),
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .background(Color.Black.copy(alpha = 0.72f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .testTag("netflix_continue_status_${item.mediaType}_${item.id}")
                        )
                    }
                }
                if (showProgress && item.showPlaybackProgress && !item.isWatched &&
                    item.progress in 1 until Constants.WATCHED_THRESHOLD
                ) {
                    Box(
                        Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .height(4.dp)
                            .background(Color.White.copy(alpha = 0.25f))
                            .testTag("netflix_continue_progress_${item.mediaType}_${item.id}")
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth(item.progress.coerceIn(0, 100) / 100f)
                                .fillMaxHeight()
                                .background(Color(0xFFE50914))
                        )
                    }
                }
                if (item.isWatched) {
                    WatchedBadge(
                        Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 12.dp, bottom = 12.dp)
                            .testTag("netflix_watched_${item.mediaType}_${item.id}"),
                        size = 18.dp
                    )
                }
            }
        }
    }
}

@Composable
private fun netflixContinueWatchingLabel(item: MediaItem): String? {
    if (item.isWatched) return null
    if (item.mediaType == MediaType.TV) {
        val episode = item.nextEpisode ?: return null
        return stringResource(
            if (item.showPlaybackProgress && item.progress > 0) R.string.continue_season_episode
            else R.string.component_next_season_episode,
            episode.displaySeason ?: episode.seasonNumber,
            episode.displayEpisode ?: episode.episodeNumber
        )
    }
    return item.timeRemainingMinutes?.takeIf { it > 0 }?.let {
        timeRemainingBadgeLabel(it, compact = false)
    }
}

private fun MediaItem.isIptvChannel(): Boolean = status?.startsWith("iptv:") == true

/** "LIVE  Channel • Group": the channel's metadata line. */
@Composable
private fun NetflixLiveLine(item: MediaItem) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.live).uppercase(),
            style = ArflixTypography.caption.copy(fontSize = 11.sp, fontWeight = FontWeight.Black),
            color = Color.White,
            modifier = Modifier
                .background(Color(0xFFE50914), RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 2.dp)
        )
        Text(
            text = listOf(item.title, item.subtitle).filter { it.isNotBlank() }.distinct().joinToString("  •  "),
            style = ArflixTypography.caption.copy(fontSize = 13.sp, fontWeight = FontWeight.Bold),
            color = Color.White.copy(alpha = 0.92f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/** The focused channel's card: border and logo. Its live preview is [NetflixChannelPreview]. */
@Composable
private fun NetflixChannelCard(
    item: MediaItem,
    width: Dp,
    height: Dp,
    onMediaPlaced: (LayoutCoordinates) -> Unit
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .border(2.5.dp, Color.White, RoundedCornerShape(10.dp))
            .padding(4.dp)
            .onGloballyPositioned(onMediaPlaced)
            .clip(RoundedCornerShape(7.dp))
            .testTag("netflix_channel_${item.id}")
    ) {
        NetflixChannelLogo(item)
    }
}

@Composable
private fun NetflixChannelLogo(item: MediaItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF15161A)),
        contentAlignment = Alignment.Center
    ) {
        if (item.image.isNotBlank()) {
            AsyncImage(
                model = item.image,
                contentDescription = item.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .fillMaxHeight(0.5f)
            )
        } else {
            Text(
                text = item.title,
                style = ArflixTypography.sectionTitle.copy(fontSize = 24.sp, fontWeight = FontWeight.Black),
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * The live preview over the focused channel card. The logo covers the surface until the
 * focused channel's own stream renders, so the previous channel's last frame never shows
 * on the new card.
 */
@Composable
private fun NetflixChannelPreview(
    player: androidx.media3.exoplayer.ExoPlayer,
    focusedItem: MediaItem,
    streamItem: MediaItem?,
    modifier: Modifier = Modifier
) {
    var frameShown by remember(focusedItem.id) { mutableStateOf(false) }
    val latestFocused by rememberUpdatedState(focusedItem)
    val latestStream by rememberUpdatedState(streamItem)
    DisposableEffect(player, focusedItem.id) {
        val listener = object : androidx.media3.common.Player.Listener {
            override fun onRenderedFirstFrame() {
                if (latestStream.isSameHomeHero(latestFocused)) frameShown = true
            }

            // Returning to the channel that is still prepared resumes without a new first frame.
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying && player.videoSize.width > 0 && latestStream.isSameHomeHero(latestFocused)) {
                    frameShown = true
                }
            }
        }
        player.addListener(listener)
        onDispose { player.removeListener(listener) }
    }
    Box(modifier = modifier.clip(RoundedCornerShape(7.dp))) {
        HomeHeroVideoSurface(player = player, modifier = Modifier.fillMaxSize())
        if (!frameShown) NetflixChannelLogo(focusedItem)
    }
}


private val SENTENCE_END = Regex("""[.!?…]+["'”’)]?(?=\s)""")
private val ABBREVIATION = Regex("""(?:^|\s)(?:[A-Z]|Mr|Mrs|Ms|Dr|St|Jr|Sr|Vs|vs|No|U\.S|U\.K)\.$""")
private const val SHORT_OVERVIEW_MIN_CHARS = 40

/**
 * Home shows a short synopsis, as Netflix does: the overview's first sentence. A very short
 * first sentence takes the next one too, and abbreviations ("Dr.", "U.S.") don't end one.
 */
internal fun netflixShortOverview(overview: String): String {
    val text = overview.trim()
    for (match in SENTENCE_END.findAll(text)) {
        val end = match.range.last + 1
        val candidate = text.substring(0, end)
        if (ABBREVIATION.containsMatchIn(candidate)) continue
        if (candidate.length < SHORT_OVERVIEW_MIN_CHARS) continue
        return candidate
    }
    return text
}

/**
 * The vertical rails move only through requestScrollToItem / scrollToItem, which never call the
 * default prefetcher (it reacts to scroll deltas). Without this, each newly revealed row is
 * composed and measured in one frame in the middle of the morph animation. After every layout,
 * schedule the row just below the last visible one so it is built ahead of time.
 */
@OptIn(ExperimentalFoundationApi::class)
private class NetflixNextRowPrefetchStrategy : LazyListPrefetchStrategy {
    private var scheduledIndex = -1
    private var handle: LazyLayoutPrefetchState.PrefetchHandle? = null

    override fun LazyListPrefetchScope.onScroll(delta: Float, layoutInfo: LazyListLayoutInfo) = Unit

    override fun LazyListPrefetchScope.onVisibleItemsUpdated(layoutInfo: LazyListLayoutInfo) {
        val next = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return) + 1
        if (next == scheduledIndex || next >= layoutInfo.totalItemsCount) return
        handle?.cancel()
        scheduledIndex = next
        handle = schedulePrefetch(next)
    }

    override fun NestedPrefetchScope.onNestedPrefetch(firstVisibleItemIndex: Int) = Unit
}
