package com.arflix.tv.ui.screens.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.arflix.tv.ui.components.landscapeDeckLayout
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.arflix.tv.R
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.CollectionTileShape
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.arflix.tv.data.model.isPortrait
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.ui.components.MediaCard as ArvioMediaCard
import com.arflix.tv.ui.components.SkeletonMediaCard
import com.arflix.tv.ui.components.SkeletonPosterCard
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextSecondary
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Landscape Home Rows Layer:
 * - Top Item: [LandscapeHomeHero] containing the adaptive layered carousel.
 * - Catalog Items: Category rows with landscape-tailored padding and card dimensions.
 */
@Composable
fun LandscapeHomeRowsLayer(
    categories: List<Category>,
    cardLogoUrls: Map<String, String>,
    cardImdbRatings: Map<String, String> = emptyMap(),
    onPreloadHeroImdbRatings: (List<MediaItem>) -> Unit = {},
    contentStartPadding: Dp,
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit = { _, _, _, _ -> },
    onItemClick: (MediaItem) -> Unit,
    onItemLongClick: ((MediaItem, Boolean) -> Unit)? = null,
    onViewAllCategory: (String) -> Unit = {},
    onCategoryVisiblePosition: (String, Int) -> Unit = { _, _ -> },
    categoryHasMoreMap: Map<String, Boolean> = emptyMap(),
    isSlowLoading: Boolean = false,
    onRetry: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val landscapeItemSpacing = 16.dp

    val heroItems = remember(categories) {
        val eligibleRows = categories.filter {
            it.id != "continue_watching" &&
                !it.id.startsWith("collection_row_") &&
                it.id != SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID &&
                it.id != SportsAddonCapabilities.POPULAR_LIVE_TV_ROW_ID
        }
        val firstCat = eligibleRows.getOrNull(0)
            ?.items?.filter { !it.isPlaceholder && it.id > 0 && !SportsAddonCapabilities.isSportsHomeStatus(it.status) && !SportsAddonCapabilities.isSportsLockedStatus(it.status) }?.take(6)
            .orEmpty()
        val secondCat = eligibleRows.getOrNull(1)
            ?.items?.filter { !it.isPlaceholder && it.id > 0 && !SportsAddonCapabilities.isSportsHomeStatus(it.status) && !SportsAddonCapabilities.isSportsLockedStatus(it.status) }?.take(6)
            .orEmpty()
        // Interleave
        buildList {
            val maxLen = maxOf(firstCat.size, secondCat.size)
            for (i in 0 until maxLen) {
                if (i < firstCat.size) add(firstCat[i])
                if (i < secondCat.size) add(secondCat[i])
            }
        }.distinctBy { "${it.mediaType}_${it.id}" }
    }

    var activeHeroIndex by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(heroItems) {
        if (heroItems.isNotEmpty()) {
            onPreloadHeroImdbRatings(heroItems)
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val layout = landscapeDeckLayout(
            (maxWidth.value - 32f).coerceAtLeast(1f),
            (maxHeight - LocalBottomBarInset.current).value,
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp + LocalBottomBarInset.current),
            verticalArrangement = Arrangement.spacedBy(if (layout.compact) 8.dp else 20.dp)
        ) {
            // ── 1. Top Landscape Hero (Details + Stacked Movie Deck) ────────────
            item(key = "landscape_hero", contentType = "landscape_hero") {
                LandscapeHomeHero(
                    items = heroItems,
                    layout = layout,
                    activeIndex = activeHeroIndex.coerceIn(0, (heroItems.size - 1).coerceAtLeast(0)),
                    onActiveIndexChanged = { activeHeroIndex = it },
                    cardLogoUrls = cardLogoUrls,
                    cardImdbRatings = cardImdbRatings,
                    onDetails = { item ->
                        onNavigateToDetails(item.mediaType, item.id, null, null)
                    }
                )
            }

        // ── 2. Category Content Rows ─────────────────────────────────────────
        itemsIndexed(
            items = categories,
            key = { _, category -> stableHomeRowKey("landscape", category.id) },
            contentType = { _, _ -> "landscape_home_category_row" }
        ) { _, category ->
            val isContinueWatching = category.id == "continue_watching"
            val isRanked = category.title.contains("Top 10", ignoreCase = true)
            val isCollectionRow = category.id.startsWith("collection_row_")
            val rowKey = remember(category.id) { "home:${category.id}" }
            val rowUsePosterCards = rememberCatalogueRowLayoutMode(rowKey) == CardLayoutMode.POSTER
            val compactCatalogCards = layout.compact && !isContinueWatching && !isCollectionRow &&
                category.id != SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID &&
                category.id != SportsAddonCapabilities.POPULAR_LIVE_TV_ROW_ID
            val isPortrait = category.isPortrait(rowUsePosterCards)
            val rowCardWidth = if (isPortrait) {
                if (layout.compact) 104.dp else 130.dp
            } else if (layout.compact) 176.dp else 210.dp
            val rowState = rememberLazyListState()

            LaunchedEffect(rowState, category.id) {
                snapshotFlow {
                    rowState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
                }
                    .distinctUntilChanged()
                    .collectLatest { lastVisible ->
                        if (lastVisible >= 0) {
                            onCategoryVisiblePosition(category.id, lastVisible)
                        }
                    }
            }

            val rowHasMore = categoryHasMoreMap[category.id] == true
            val showViewAll = remember(category.items, rowHasMore) {
                homeRowSupportsViewAll(category, rowHasMore)
            }

            Column(modifier = Modifier.padding(bottom = 0.dp)) {
                // Section Title Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = contentStartPadding,
                            end = contentStartPadding,
                            bottom = 6.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = localizedCategoryTitle(category),
                        style = ArflixTypography.sectionTitle.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.8f),
                                offset = Offset(1f, 1f),
                                blurRadius = 4f
                            )
                        ),
                        color = Color.White
                    )
                    if (showViewAll) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = stringResource(R.string.home_view_all),
                            style = ArflixTypography.label,
                            color = TextSecondary,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .clickable { onViewAllCategory(category.id) }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                val isRowPortrait = if (isCollectionRow) {
                    category.items.firstOrNull()?.collectionTileShape == CollectionTileShape.POSTER
                } else {
                    rowUsePosterCards
                }
                val isRowSkeleton = category.items.isEmpty() || category.items.all { it.isPlaceholder }

                if (isRowSkeleton) {
                    LazyRow(
                        state = rowState,
                        modifier = Modifier.arvioDpadFocusGroup(),
                        contentPadding = PaddingValues(
                            start = contentStartPadding,
                            end = 24.dp,
                            top = 4.dp,
                            bottom = 4.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(landscapeItemSpacing)
                    ) {
                        items(8, key = { "skeleton_ls_${category.id}_$it" }) {
                            if (isRowPortrait) {
                                SkeletonPosterCard(width = rowCardWidth)
                            } else {
                                SkeletonMediaCard(width = rowCardWidth)
                            }
                        }
                    }
                } else {
                    val realItems = remember(category.items) {
                        category.items.filter { !it.isPlaceholder }
                    }
                    val itemKeys = remember(category.id, realItems) {
                        stableHomeRowItemKeys(category.id, realItems)
                    }

                    LazyRow(
                        state = rowState,
                        modifier = Modifier.arvioDpadFocusGroup(),
                        contentPadding = PaddingValues(
                            start = contentStartPadding,
                            end = 24.dp,
                            top = 4.dp,
                            bottom = 4.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(landscapeItemSpacing)
                    ) {
                        itemsIndexed(
                            realItems,
                            key = { index, _ -> itemKeys[index] },
                            contentType = { _, item -> "${item.mediaType.name}_ls_card" }
                        ) { index, item ->
                            val currentItem = rememberUpdatedState(item)
                            val onCardClick = remember {
                                { onItemClick(currentItem.value) }
                            }
                            val onCardLongClick = if (onItemLongClick != null) {
                                remember {
                                    { onItemLongClick(currentItem.value, isContinueWatching) }
                                }
                            } else null

                            if (isRanked && index < 10) {
                                Box(modifier = Modifier.width(rowCardWidth)) {
                                    val cardLogoUrl = if (isCollectionRow) null else (cardLogoUrls["${item.mediaType}_${item.id}"]
                                        ?: cardLogoUrls["${item.mediaType.name.lowercase()}_${item.id}"])
                                    ArvioMediaCard(
                                        item = item,
                                        width = rowCardWidth,
                                        isLandscape = !isRowPortrait,
                                        logoImageUrl = cardLogoUrl,
                                        showLogoImage = true,
                                        showProgress = false,
                                        showTitle = !item.collectionHideTitle,
                                        showSubtitle = true,
                                        titleMaxLines = if (compactCatalogCards) 2 else 1,
                                        titleMinLines = if (compactCatalogCards) 2 else 1,
                                        isFocusedOverride = false,
                                        enableSystemFocus = false,
                                        onFocused = {},
                                        onClick = onCardClick,
                                        onLongClick = onCardLongClick,
                                    )
                                    TopRankRibbon(
                                        rank = index + 1,
                                        isFocused = false,
                                        compact = true,
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .zIndex(2f)
                                            .padding(start = 6.dp)
                                    )
                                }
                            } else {
                                val cardLogoUrl = if (isCollectionRow) null else (cardLogoUrls["${item.mediaType}_${item.id}"]
                                    ?: cardLogoUrls["${item.mediaType.name.lowercase()}_${item.id}"])
                                ArvioMediaCard(
                                    item = item,
                                    width = rowCardWidth,
                                    isLandscape = !isRowPortrait,
                                    logoImageUrl = cardLogoUrl,
                                    showLogoImage = true,
                                    showProgress = isContinueWatching,
                                    showTitle = !item.collectionHideTitle,
                                    showSubtitle = true,
                                    titleMaxLines = if (compactCatalogCards) 2 else 1,
                                    titleMinLines = if (compactCatalogCards) 2 else 1,
                                    isFocusedOverride = false,
                                    enableSystemFocus = false,
                                    onFocused = {},
                                    onClick = onCardClick,
                                    onLongClick = onCardLongClick,
                                )
                            }
                        }

                        if (showViewAll) {
                            item(key = "${category.id}_ls_view_all", contentType = "view_all_card") {
                                HomeViewAllCard(
                                    width = rowCardWidth,
                                    isLandscape = !isRowPortrait,
                                    isFocused = false,
                                    enableSystemFocus = false,
                                    onClick = { onViewAllCategory(category.id) }
                                )
                            }
                        } else if (rowHasMore) {
                            item(key = "${category.id}_ls_loading_more", contentType = "loading_more_card") {
                                if (isRowPortrait) {
                                    SkeletonPosterCard(width = rowCardWidth)
                                } else {
                                    SkeletonMediaCard(width = rowCardWidth)
                                }
                            }
                        }
                    }
                }
            }
        }

        if (isSlowLoading) {
            item(key = "ls_slow_loading_indicator", contentType = "ls_slow_loading") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.home_still_loading_catalogue),
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                    TextButton(onClick = onRetry) {
                        Text(stringResource(R.string.retry), color = Color(0xFF00F0D0), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
    }
}
