@file:OptIn(androidx.tv.material3.ExperimentalTvMaterial3Api::class)

package com.arflix.tv.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import android.os.SystemClock
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.foundation.focusable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.components.MediaCard as ArvioMediaCard
import com.arflix.tv.ui.components.MediaContextMenu
import com.arflix.tv.ui.components.Sidebar
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.TopBarClock
import com.arflix.tv.ui.theme.AnimationConstants
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.AccentRed
import com.arflix.tv.ui.theme.PrimeBlue
import com.arflix.tv.ui.theme.PrimeGreen
import com.arflix.tv.ui.theme.RankNumberColor
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.theme.BackgroundGradientCenter
import com.arflix.tv.ui.theme.BackgroundGradientEnd
import com.arflix.tv.ui.theme.BackgroundGradientStart
import com.arflix.tv.util.isInCinema
import com.arflix.tv.util.parseRatingValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.max
import kotlin.math.min
import kotlin.math.abs

// Genre ID to name mapping (TMDB standard)
private val movieGenres = mapOf(
    28 to "Action", 12 to "Adventure", 16 to "Animation", 35 to "Comedy",
    80 to "Crime", 99 to "Documentary", 18 to "Drama", 10751 to "Family",
    14 to "Fantasy", 36 to "History", 27 to "Horror", 10402 to "Music",
    9648 to "Mystery", 10749 to "Romance", 878 to "Sci-Fi", 10770 to "TV Movie",
    53 to "Thriller", 10752 to "War", 37 to "Western"
)

private val tvGenres = mapOf(
    10759 to "Action & Adventure", 16 to "Animation", 35 to "Comedy",
    80 to "Crime", 99 to "Documentary", 18 to "Drama", 10751 to "Family",
    10762 to "Kids", 9648 to "Mystery", 10763 to "News", 10764 to "Reality",
    10765 to "Sci-Fi & Fantasy", 10766 to "Soap", 10767 to "Talk",
    10768 to "War & Politics", 37 to "Western"
)

@Stable
private class HomeFocusState {
    var isSidebarFocused by mutableStateOf(false)
    var sidebarFocusIndex by mutableIntStateOf(1)
    var currentRowIndex by mutableIntStateOf(0)
    var currentItemIndex by mutableIntStateOf(0)
    var lastNavEventTime by mutableLongStateOf(0L)
}

private fun getFocusedItem(categories: List<Category>, rowIndex: Int, itemIndex: Int): MediaItem? {
    val row = categories.getOrNull(rowIndex)
    return row?.items?.getOrNull(itemIndex)
        ?: row?.items?.firstOrNull()
        ?: categories.firstOrNull()?.items?.firstOrNull()
}

/**
 * Home screen matching webapp design exactly:
 * - Large hero with logo image
 * - Single visible content row with large cards
 * - Slim sidebar on left
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    preloadedCategories: List<Category> = emptyList(),
    preloadedHeroItem: MediaItem? = null,
    preloadedHeroLogoUrl: String? = null,
    preloadedLogoCache: Map<String, String> = emptyMap(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit = { _, _, _, _ -> },
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onExitApp: () -> Unit = {}
) {
    // Use preloaded data from StartupViewModel if available
    LaunchedEffect(preloadedCategories, preloadedHeroItem, preloadedHeroLogoUrl, preloadedLogoCache) {
        if (preloadedCategories.isNotEmpty()) {
            viewModel.setPreloadedData(
                categories = preloadedCategories,
                heroItem = preloadedHeroItem,
                heroLogoUrl = preloadedHeroLogoUrl,
                logoCache = preloadedLogoCache
            )
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refreshContinueWatchingOnly()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val displayCategories = if (uiState.categories.isNotEmpty()) {
        uiState.categories
    } else {
        preloadedCategories
    }
    val displayHeroItem = uiState.heroItem ?: preloadedHeroItem
        ?: displayCategories.firstOrNull()?.items?.firstOrNull()
    val displayHeroLogo = uiState.heroLogoUrl ?: preloadedHeroLogoUrl

    val context = LocalContext.current
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val backdropSize = remember(configuration, density) {
        val widthPx = with(density) { configuration.screenWidthDp.dp.roundToPx() }
        val heightPx = with(density) { configuration.screenHeightDp.dp.roundToPx() }
        widthPx.coerceAtMost(3840).coerceAtLeast(1) to heightPx.coerceAtMost(2160).coerceAtLeast(1)
    }
    val backdropGradient = remember {
        Brush.linearGradient(
            colors = listOf(
                BackgroundGradientStart,
                BackgroundGradientCenter,
                BackgroundGradientEnd
            )
        )
    }
    val heroLeftScrim = remember {
        Brush.horizontalGradient(
            colorStops = arrayOf(
                0.0f to Color.Black.copy(alpha = 0.85f),
                0.15f to Color.Black.copy(alpha = 0.75f),
                0.25f to Color.Black.copy(alpha = 0.55f),
                0.35f to Color.Black.copy(alpha = 0.35f),
                0.45f to Color.Black.copy(alpha = 0.15f),
                0.55f to Color.Transparent,
                1.0f to Color.Transparent
            )
        )
    }
    val heroTopScrim = remember {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Black.copy(alpha = 0.5f),
                0.05f to Color.Black.copy(alpha = 0.25f),
                0.12f to Color.Transparent,
                1.0f to Color.Transparent
            )
        )
    }
    val heroBottomScrim = remember {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Transparent,
                0.85f to Color.Transparent,
                0.92f to Color.Black.copy(alpha = 0.5f),
                1.0f to Color.Black.copy(alpha = 0.85f)
            )
        )
    }
    val contentStartPadding = 12.dp

    val focusState = remember { HomeFocusState() }
    val fastScrollThresholdMs = 650L

    // Context menu state (Menu button only, no long-press)
    var showContextMenu by remember { mutableStateOf(false) }
    var contextMenuItem by remember { mutableStateOf<MediaItem?>(null) }
    var contextMenuIsContinueWatching by remember { mutableStateOf(false) }
    var contextMenuIsInWatchlist by remember { mutableStateOf(false) }

    // Preload logos for current and next rows when row changes
    LaunchedEffect(displayCategories) {
        snapshotFlow { focusState.currentRowIndex }
            .distinctUntilChanged()
            .collectLatest { rowIndex ->
                viewModel.preloadLogosForCategory(rowIndex)
                viewModel.preloadLogosForCategory(rowIndex + 1)
            }
    }

    // Update hero based on focused item with adaptive idle delay to avoid heavy churn while scrolling
    LaunchedEffect(displayCategories) {
        snapshotFlow { Pair(focusState.currentRowIndex, focusState.currentItemIndex) }
            .distinctUntilChanged()
            .collectLatest { (rowIndex, itemIndex) ->
                if (displayCategories.isEmpty() || focusState.isSidebarFocused) return@collectLatest
                val now = SystemClock.elapsedRealtime()
                val isFastScrolling = now - focusState.lastNavEventTime < fastScrollThresholdMs
                viewModel.onFocusChanged(rowIndex, itemIndex, shouldPrefetch = false)
                delay(if (isFastScrolling) 700L else 220L)

                val idleFor = SystemClock.elapsedRealtime() - focusState.lastNavEventTime
                if (idleFor < fastScrollThresholdMs) return@collectLatest

                val row = displayCategories.getOrNull(rowIndex)
                val newHeroItem = row?.items?.getOrNull(itemIndex)
                    ?: row?.items?.firstOrNull()
                    ?: displayCategories.firstOrNull()?.items?.firstOrNull()

                if (newHeroItem != null) {
                    viewModel.onFocusChanged(rowIndex, itemIndex, shouldPrefetch = true)
                    viewModel.updateHeroItem(newHeroItem)
                }
            }
    }

    LaunchedEffect(showContextMenu, contextMenuItem) {
        if (showContextMenu) {
            val item = contextMenuItem
            contextMenuIsInWatchlist = if (item != null) {
                viewModel.isInWatchlist(item)
            } else {
                false
            }
        } else {
            contextMenuIsInWatchlist = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
      ) {
        // Fast hero background transition
        val currentBackdrop = displayHeroItem?.backdrop ?: displayHeroItem?.image
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = backdropGradient
                    )
            )

            Crossfade(
                targetState = currentBackdrop,
                animationSpec = tween(durationMillis = 300),  // Smooth professional transition
                label = "hero_backdrop_crossfade"
            ) { backdropUrl ->
                if (backdropUrl != null) {
                    val (backdropWidthPx, backdropHeightPx) = backdropSize
                    val request = remember(backdropUrl, backdropWidthPx, backdropHeightPx) {
                        ImageRequest.Builder(context)
                            .data(backdropUrl)
                            .size(backdropWidthPx, backdropHeightPx)
                            .precision(Precision.EXACT)
                            .allowHardware(true)
                            .crossfade(false)
                            .build()
                    }
                    AsyncImage(
                        model = request,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // === PREMIUM MULTI-LAYER SCRIM SYSTEM ===

            // Layer 1: Strong left gradient for hero text area (Netflix-style)
            // Uses colorStops with percentages to work on any resolution
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = heroLeftScrim
                    )
            )

            // Layer 2: Top vignette for clock/status area
            // Uses colorStops with percentages to work on any resolution
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = heroTopScrim
                    )
            )

            // Layer 3: Bottom floor-fade (starts low, darker at bottom)
            // Uses colorStops with percentages to work on any resolution
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = heroBottomScrim
                    )
            )

            // Layer 4 removed for performance - radial gradients are expensive on TV
        }
        
        HomeHeroLayer(
            heroItem = displayHeroItem,
            heroLogoUrl = displayHeroLogo,
            contentStartPadding = contentStartPadding
        )

        HomeInputLayer(
            categories = displayCategories,
            cardLogoUrls = uiState.cardLogoUrls,
            focusState = focusState,
            contentStartPadding = contentStartPadding,
            fastScrollThresholdMs = fastScrollThresholdMs,
            isContextMenuOpen = showContextMenu,
            currentProfile = currentProfile,
            onNavigateToDetails = onNavigateToDetails,
            onNavigateToSearch = onNavigateToSearch,
            onNavigateToWatchlist = onNavigateToWatchlist,
            onNavigateToTv = onNavigateToTv,
            onNavigateToSettings = onNavigateToSettings,
            onSwitchProfile = onSwitchProfile,
            onExitApp = onExitApp,
            onOpenContextMenu = { item, isContinue ->
                contextMenuItem = item
                contextMenuIsContinueWatching = isContinue
                showContextMenu = true
            }
        )

        // Clock top-right (profile moved to sidebar)
        TopBarClock(modifier = Modifier.align(Alignment.TopEnd))
        
        // Error state - show message when loading failed and no content
        if (!uiState.isLoading && displayCategories.isEmpty() && uiState.error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundDark),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Unable to load content",
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = uiState.error ?: "Please check your connection",
                        style = ArflixTypography.body,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    androidx.tv.material3.Button(
                        onClick = { viewModel.refresh() }
                    ) {
                        Text("Retry")
                    }
                }
            }
        }

        // Context menu
        contextMenuItem?.let { item ->
            MediaContextMenu(
                isVisible = showContextMenu,
                title = item.title,
                isInWatchlist = contextMenuIsInWatchlist,
                isWatched = item.isWatched,
                isContinueWatching = contextMenuIsContinueWatching,
                onPlay = {
                    onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                },
                onViewDetails = {
                    onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                },
                onToggleWatchlist = {
                    viewModel.toggleWatchlist(item)
                },
                onToggleWatched = {
                    viewModel.toggleWatched(item)
                },
                onRemoveFromContinueWatching = if (contextMenuIsContinueWatching) {
                    { viewModel.removeFromContinueWatching(item) }
                } else null,
                onDismiss = {
                    showContextMenu = false
                    contextMenuItem = null
                    contextMenuIsContinueWatching = false
                }
            )
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun HeroSection(
    item: MediaItem,
    logoUrl: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val logoSize = remember(density) {
        val widthPx = with(density) { 300.dp.roundToPx() }
        val heightPx = with(density) { 70.dp.roundToPx() }
        widthPx.coerceAtLeast(1) to heightPx.coerceAtLeast(1)
    }

    // === PREMIUM LAYERED TEXT SHADOWS ===
    // Multiple shadows create depth and ensure readability on any background
    val textShadowPrimary = Shadow(
        color = Color.Black.copy(alpha = 0.9f),
        offset = Offset(0f, 2f),
        blurRadius = 8f  // Soft spread shadow
    )
    val textShadowSecondary = Shadow(
        color = Color.Black.copy(alpha = 0.7f),
        offset = Offset(1f, 3f),
        blurRadius = 4f  // Medium shadow
    )
    // Use primary shadow for text (Compose only supports one shadow per text)
    // But the frosted pill provides additional protection
    val textShadow = textShadowPrimary

    // Phase 2.2 & 2.4: Animate content changes with fade
    // Use item.id as key to trigger animations on content change
    val contentKey = remember(item.id, logoUrl) { "${item.id}_${logoUrl != null}" }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        // Fast logo transition
        Crossfade(
            targetState = logoUrl to item,
            animationSpec = tween(durationMillis = 30),  // Faster for TV performance
            label = "hero_logo_crossfade"
        ) { (currentLogoUrl, currentItem) ->
            val showInCinema = remember(currentItem.releaseDate, currentItem.mediaType) {
                isInCinema(currentItem)
            }
            val inCinemaColor = Color(0xFF8AD5FF)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier.height(70.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (currentLogoUrl != null) {
                        val (logoWidthPx, logoHeightPx) = logoSize
                        val request = remember(currentLogoUrl, logoWidthPx, logoHeightPx) {
                            ImageRequest.Builder(context)
                                .data(currentLogoUrl)
                                .size(logoWidthPx, logoHeightPx)
                                .precision(Precision.INEXACT)
                                .allowHardware(true)
                                .crossfade(false)
                                .build()
                        }
                        AsyncImage(
                            model = request,
                            contentDescription = currentItem.title,
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.CenterStart,
                            modifier = Modifier
                                .height(70.dp)
                                .width(300.dp)
                        )
                    } else {
                        // Fallback to title text
                        Text(
                            text = currentItem.title.uppercase(),
                            style = ArflixTypography.heroTitle.copy(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 2.sp,
                                shadow = textShadow
                            ),
                            color = TextPrimary,
                            maxLines = 2
                        )
                    }
                }

                if (showInCinema) {
                    Box(
                        modifier = Modifier
                            .background(inCinemaColor, RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "In Cinema",
                            style = ArflixTypography.caption.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fast metadata transition - simple fade only (optimized for TV)
        AnimatedContent(
            targetState = item,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)).togetherWith(fadeOut(animationSpec = tween(250)))
            },
            label = "hero_metadata_animation"
        ) { currentItem ->
            Column {
                // Get actual genre names from genre IDs
                val genreMap = if (currentItem.mediaType == MediaType.TV) tvGenres else movieGenres
                val genreNames = currentItem.genreIds.mapNotNull { genreMap[it] }.take(2)
                val genreText = genreNames.joinToString(" / ")
                val displayDate = currentItem.releaseDate?.takeIf { it.isNotEmpty() } ?: currentItem.year
                val hasDuration = currentItem.duration.isNotEmpty() && currentItem.duration != "0m"
                val hasGenre = genreText.isNotEmpty()

                // Metadata row: Date | Genre | Duration | IMDb rating
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (displayDate.isNotEmpty()) {
                        Text(
                            text = displayDate,
                            style = ArflixTypography.caption.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                shadow = textShadow
                            ),
                            color = Color.White
                        )

                        if (hasGenre) {
                            Text(
                                text = "|",
                                style = ArflixTypography.caption.copy(
                                    fontSize = 14.sp,
                                    shadow = textShadow
                                ),
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }

                    if (hasGenre) {
                        Text(
                            text = genreText,
                            style = ArflixTypography.caption.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                shadow = textShadow
                            ),
                            color = Color.White
                        )
                    }

                    if (hasDuration) {
                        if (displayDate.isNotEmpty() || hasGenre) {
                            Text(
                                text = "|",
                                style = ArflixTypography.caption.copy(
                                    fontSize = 14.sp,
                                    shadow = textShadow
                                ),
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                        Text(
                            text = currentItem.duration,
                            style = ArflixTypography.caption.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                shadow = textShadow
                            ),
                            color = Color.White
                        )
                    }

                    val rating = currentItem.imdbRating.ifEmpty { currentItem.tmdbRating }
                    val ratingValue = parseRatingValue(rating)
                    if (ratingValue > 0f) {
                        if (displayDate.isNotEmpty() || hasGenre || hasDuration) {
                            Text(
                                text = "|",
                                style = ArflixTypography.caption.copy(
                                    fontSize = 14.sp,
                                    shadow = textShadow
                                ),
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .background(Color(0xFFF5C518), RoundedCornerShape(3.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "IMDb",
                                style = ArflixTypography.caption.copy(
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Black
                                ),
                                color = Color.Black
                            )
                            Text(
                                text = rating,
                                style = ArflixTypography.caption.copy(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Overview text
                Text(
                    text = currentItem.overview,
                    style = ArflixTypography.body.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 22.sp,
                        shadow = textShadow
                    ),
                    color = Color.White,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(340.dp)
                )
            }
        }
    }
}

@Composable
private fun HomeHeroLayer(
    heroItem: MediaItem?,
    heroLogoUrl: String?,
    contentStartPadding: androidx.compose.ui.unit.Dp
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 56.dp)
    ) {
        heroItem?.let { item ->
            HeroSection(
                item = item,
                logoUrl = heroLogoUrl,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 300.dp, start = contentStartPadding, end = 400.dp)
            )
        }
    }
}

@Composable
private fun HomeInputLayer(
    categories: List<Category>,
    cardLogoUrls: Map<String, String>,
    focusState: HomeFocusState,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    fastScrollThresholdMs: Long,
    isContextMenuOpen: Boolean,
    currentProfile: com.arflix.tv.data.model.Profile?,
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToWatchlist: () -> Unit,
    onNavigateToTv: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onSwitchProfile: () -> Unit,
    onExitApp: () -> Unit,
    onOpenContextMenu: (MediaItem, Boolean) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var selectPressedInHome by remember { mutableStateOf(false) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = if (hasProfile) SidebarItem.entries.size else SidebarItem.entries.size - 1  // 5 or 4

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    LaunchedEffect(hasProfile) {
        if (hasProfile) focusState.sidebarFocusIndex = 2
    }

    LaunchedEffect(categories) {
        val boundedRow = focusState.currentRowIndex.coerceIn(0, (categories.size - 1).coerceAtLeast(0))
        focusState.currentRowIndex = boundedRow
        val maxItems = categories.getOrNull(boundedRow)?.items?.size ?: 0
        if (maxItems > 0 && focusState.currentItemIndex > maxItems - 1) {
            focusState.currentItemIndex = maxItems - 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                if (isContextMenuOpen) {
                    return@onPreviewKeyEvent false
                }
                when (event.type) {
                    KeyEventType.KeyDown -> when (event.key) {
                        Key.Enter, Key.DirectionCenter -> {
                            // Only accept KeyUp action when its KeyDown also happened on this screen.
                            selectPressedInHome = true
                            true
                        }
                        Key.DirectionLeft -> {
                            if (!focusState.isSidebarFocused) {
                                if (focusState.currentItemIndex == 0) {
                                    focusState.isSidebarFocused = true
                                    true
                                } else {
                                    focusState.currentItemIndex--
                                    focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                                    true
                                }
                            } else {
                                true
                            }
                        }
                        Key.DirectionRight -> {
                            if (focusState.isSidebarFocused) {
                                focusState.isSidebarFocused = false
                                true
                            } else {
                                val maxItems = categories.getOrNull(focusState.currentRowIndex)?.items?.size ?: 0
                                if (focusState.currentItemIndex < maxItems - 1) {
                                    focusState.currentItemIndex++
                                    focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                                }
                                true
                            }
                        }
                        Key.DirectionUp -> {
                            if (focusState.isSidebarFocused && focusState.sidebarFocusIndex > 0) {
                                focusState.sidebarFocusIndex = (focusState.sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                                true
                            } else if (!focusState.isSidebarFocused && focusState.currentRowIndex > 0) {
                                focusState.currentRowIndex--
                                focusState.currentItemIndex = 0
                                focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                                true
                            } else {
                                true
                            }
                        }
                        Key.DirectionDown -> {
                            if (focusState.isSidebarFocused && focusState.sidebarFocusIndex < maxSidebarIndex) {
                                focusState.sidebarFocusIndex = (focusState.sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                                focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                                true
                            } else if (!focusState.isSidebarFocused && focusState.currentRowIndex < categories.size - 1) {
                                focusState.currentRowIndex++
                                focusState.currentItemIndex = 0
                                focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                                true
                            } else {
                                true
                            }
                        }
                        Key.Back, Key.Escape -> {
                            if (focusState.isSidebarFocused) {
                                onExitApp()
                            } else {
                                focusState.isSidebarFocused = true
                            }
                            true
                        }
                        Key.Menu, Key.Info -> {
                            if (!focusState.isSidebarFocused) {
                                val currentItem = getFocusedItem(
                                    categories,
                                    focusState.currentRowIndex,
                                    focusState.currentItemIndex
                                )
                                if (currentItem != null) {
                                    val currentCategory = categories.getOrNull(focusState.currentRowIndex)
                                    val isContinue = currentCategory?.id == "continue_watching"
                                    onOpenContextMenu(currentItem, isContinue)
                                }
                            }
                            true
                        }
                        else -> false
                    }
                    KeyEventType.KeyUp -> when (event.key) {
                        Key.Enter, Key.DirectionCenter -> {
                            if (!selectPressedInHome) {
                                // Ignore stale KeyUp events that can arrive after screen navigation.
                                return@onPreviewKeyEvent true
                            }
                            selectPressedInHome = false
                            if (focusState.isSidebarFocused) {
                                if (hasProfile && focusState.sidebarFocusIndex == 0) {
                                    onSwitchProfile()
                                } else {
                                    val itemIndex = if (hasProfile) focusState.sidebarFocusIndex - 1 else focusState.sidebarFocusIndex
                                    when (SidebarItem.entries[itemIndex]) {
                                        SidebarItem.SEARCH -> onNavigateToSearch()
                                        SidebarItem.HOME -> { }
                                        SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                        SidebarItem.TV -> onNavigateToTv()
                                        SidebarItem.SETTINGS -> onNavigateToSettings()
                                    }
                                }
                            } else {
                                val currentItem = getFocusedItem(
                                    categories,
                                    focusState.currentRowIndex,
                                    focusState.currentItemIndex
                                )
                                currentItem?.let { item ->
                                    onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                                }
                            }
                            true
                        }
                        else -> false
                    }
                    else -> false
                }
            }
    ) {
        Sidebar(
            selectedItem = SidebarItem.HOME,
            isSidebarFocused = focusState.isSidebarFocused,
            focusedIndex = focusState.sidebarFocusIndex,
            profile = currentProfile,
            onProfileClick = onSwitchProfile,
            onItemSelected = { item ->
                when (item) {
                    SidebarItem.SEARCH -> onNavigateToSearch()
                    SidebarItem.HOME -> { }
                    SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                    SidebarItem.TV -> onNavigateToTv()
                    SidebarItem.SETTINGS -> onNavigateToSettings()
                }
            }
        )

        HomeRowsLayer(
            categories = categories,
            cardLogoUrls = cardLogoUrls,
            focusState = focusState,
            contentStartPadding = contentStartPadding,
            fastScrollThresholdMs = fastScrollThresholdMs,
            onItemClick = { item -> onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber) }
        )
    }
}

@Composable
private fun HomeRowsLayer(
    categories: List<Category>,
    cardLogoUrls: Map<String, String>,
    focusState: HomeFocusState,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    fastScrollThresholdMs: Long,
    onItemClick: (MediaItem) -> Unit
) {
    val currentRowIndex = focusState.currentRowIndex
    var isFastScrolling by remember { mutableStateOf(false) }
    LaunchedEffect(focusState.lastNavEventTime) {
        val anchor = focusState.lastNavEventTime
        isFastScrolling = true
        delay(fastScrollThresholdMs)
        if (focusState.lastNavEventTime == anchor) {
            isFastScrolling = false
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 56.dp)
    ) {
        val halfHeight = maxHeight / 2
        val listState = rememberLazyListState()
        val targetIndex = currentRowIndex.coerceIn(0, (categories.size - 1).coerceAtLeast(0))
        LaunchedEffect(targetIndex) {
            listState.animateScrollToItem(
                index = targetIndex,
                scrollOffset = 0
            )
        }
        // Viewport is only the bottom 50%: selected row stays at same height, rows above disappear
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(halfHeight)
                .clipToBounds()
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = halfHeight),
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds()
                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen },
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
            itemsIndexed(categories) { index, category ->
                key(category.id) {
                    val targetAlpha = if (index <= currentRowIndex) 1f else 0.25f
                    val alpha by animateFloatAsState(
                        targetValue = targetAlpha,
                        animationSpec = tween(durationMillis = 300),
                        label = "row_alpha"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clipToBounds()
                            .alpha(alpha)
                    ) {
                        ContentRow(
                            category = category,
                            cardLogoUrls = cardLogoUrls,
                            isCurrentRow = index == focusState.currentRowIndex,
                            isRanked = category.title.contains("Top 10", ignoreCase = true),
                            startPadding = contentStartPadding,
                            focusedItemIndex = if (index == focusState.currentRowIndex) focusState.currentItemIndex else 0,
                            isFastScrolling = isFastScrolling,
                            onItemClick = onItemClick,
                            onItemFocused = { _, itemIdx ->
                                focusState.currentRowIndex = index
                                focusState.currentItemIndex = itemIdx
                                focusState.isSidebarFocused = false
                                focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                            }
                        )
                    }
                }
            }
            }
        }
    }
}

@Composable
private fun ArcticFuseRatingBadge(
    label: String,
    rating: String,
    backgroundColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = label,
                style = ArflixTypography.caption.copy(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
        Text(
            text = rating,
            style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold),
            color = Color.White
        )
    }
}

@Composable
private fun PrimeLogo(modifier: Modifier = Modifier) {
    // Simple text-based logo for now, but blue "prime" with smile curve
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        // "prime" text
        Text(
            text = "prime",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = PrimeBlue,
                letterSpacing = (-0.5).sp
            )
        )
        // Smile curve path could be drawn here, but text is sufficient for now
    }
}

@Composable
private fun IncludedWithPrimeBadge() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            tint = PrimeBlue,
            modifier = Modifier
                .size(16.dp)
                .background(Color.Transparent) // No circle bg in screenshot, just check
        )
        Text(
            text = "Included with Prime",
            style = ArflixTypography.caption.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            ),
            color = TextPrimary
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MetaPill(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(2.dp)
            )
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.caption.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            ),
            color = TextPrimary
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ImdbBadge(rating: String) {
    // Kept for compatibility but not strictly in new hero design
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFFF5C518), // IMDb yellow
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "IMDb",
                style = ArflixTypography.caption.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = rating,
                style = ArflixTypography.caption.copy(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ContentRow(
    category: Category,
    cardLogoUrls: Map<String, String>,
    isCurrentRow: Boolean,
    isRanked: Boolean = false,
    startPadding: androidx.compose.ui.unit.Dp = 12.dp,
    focusedItemIndex: Int,
    isFastScrolling: Boolean,
    onItemClick: (MediaItem) -> Unit,
    onItemFocused: (MediaItem, Int) -> Unit
) {
    val rowState = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val isContinueWatching = category.id == "continue_watching"
    val itemWidth = 210.dp
    val itemSpacing = 14.dp
    val availableWidthDp = configuration.screenWidthDp.dp - 56.dp - 12.dp
    val fallbackItemsPerPage = remember(configuration, density, itemWidth, itemSpacing) {
        val availablePx = with(density) { availableWidthDp.coerceAtLeast(1.dp).roundToPx() }
        val itemSpanPx = with(density) { (itemWidth + itemSpacing).roundToPx() }.coerceAtLeast(1)
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
    val rowFade = remember { Animatable(1f) }
    var lastPageIndex by remember { mutableIntStateOf(0) }
    val totalItems = category.items.size
    val effectiveVisibleCount = remember(totalItems, itemsPerPage, visibleCount) {
        if (visibleCount > 0) min(visibleCount, totalItems.coerceAtLeast(1)) else itemsPerPage
    }
    val maxFirstIndex = remember(totalItems, effectiveVisibleCount) {
        (totalItems - effectiveVisibleCount).coerceAtLeast(0)
    }
    val isScrollable = totalItems > effectiveVisibleCount
    // Use rememberUpdatedState to ensure items recompose when focus changes
    val currentFocusedIndex by rememberUpdatedState(focusedItemIndex)
    val currentIsCurrentRow by rememberUpdatedState(isCurrentRow)
    val scrollTargetIndex by remember(rowState, focusedItemIndex, isCurrentRow, totalItems, maxFirstIndex) {
        derivedStateOf {
            if (!isCurrentRow || focusedItemIndex < 0) return@derivedStateOf -1
            if (totalItems == 0) return@derivedStateOf -1
            focusedItemIndex.coerceAtMost(maxFirstIndex)
        }
    }
    val itemSpanPx = remember(density, itemWidth, itemSpacing) {
        with(density) { (itemWidth + itemSpacing).toPx().coerceAtLeast(1f) }
    }

    // Keep focused card anchored by scrolling the row on every focus change.
    // Use smooth scroll (animated) for D-pad moves to avoid abrupt jumps.
    var lastScrollIndex by remember { mutableIntStateOf(-1) }
    LaunchedEffect(isCurrentRow) {
        if (!isCurrentRow) {
            lastScrollIndex = -1
        }
    }
    LaunchedEffect(scrollTargetIndex, isCurrentRow, focusedItemIndex) {
        if (!isCurrentRow || scrollTargetIndex < 0) return@LaunchedEffect

        // Calculate extra offset for items at the end of the list (past maxFirstIndex)
        // This ensures the last items remain fully visible when focused
        val extraOffset = if (focusedItemIndex > maxFirstIndex) {
            ((focusedItemIndex - maxFirstIndex) * itemSpanPx).toInt()
        } else {
            0
        }

        // FIX: When scrolling back to first item, ensure we reset to position 0 with no offset
        // This prevents focus from disappearing on the left side
        if (focusedItemIndex == 0 && scrollTargetIndex == 0) {
            rowState.animateScrollToItem(index = 0, scrollOffset = 0)
            lastScrollIndex = 0
            return@LaunchedEffect
        }

        if (lastScrollIndex == scrollTargetIndex && extraOffset == 0) return@LaunchedEffect
        if (lastScrollIndex == -1) {
            // First time we jump directly to the correct position (no animation)
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
            lastScrollIndex = scrollTargetIndex
            return@LaunchedEffect
        }

        // Always use a smooth animated scroll for D‑pad navigation between items
        rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        lastScrollIndex = scrollTargetIndex
    }

    val pageIndex by remember(itemsPerPage, rowState) {
        derivedStateOf { rowState.firstVisibleItemIndex / itemsPerPage }
    }

    // Fade the next page in when scrolling between page groups.
    LaunchedEffect(isCurrentRow) {
        if (isCurrentRow) {
            lastPageIndex = pageIndex
        }
    }

    LaunchedEffect(pageIndex, isCurrentRow, isFastScrolling) {
        if (!isCurrentRow) return@LaunchedEffect
        if (isFastScrolling) {
            if (rowFade.value < 0.999f) {
                rowFade.snapTo(1f)
            }
            lastPageIndex = pageIndex
            return@LaunchedEffect
        }
        if (pageIndex != lastPageIndex) {
            lastPageIndex = pageIndex
            rowFade.snapTo(0.8f)
            rowFade.animateTo(1f, animationSpec = tween(durationMillis = 300))
        }
    }

    Column(
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        // Section title - clean white text, aligned with cards
        Text(
            text = category.title,
            style = ArflixTypography.sectionTitle.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier.padding(start = startPadding, bottom = 12.dp)  // Align with cards start padding
        )

        // Cards row - clipped to hide previous items when scrolling
        val rowFadeModifier = if (rowFade.value < 0.999f) {
            Modifier.graphicsLayer { alpha = rowFade.value }
        } else {
            Modifier
        }
        val clipModifier = if (isContinueWatching) Modifier else Modifier.clipToBounds()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(clipModifier)
        ) {
            LazyRow(
                modifier = rowFadeModifier,
                state = rowState,
                contentPadding = PaddingValues(start = startPadding, end = 240.dp, top = 8.dp, bottom = 8.dp),  // 210dp card + 30dp margin to keep last item visible
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
            itemsIndexed(
                category.items,
                key = { _, item ->
                    val ep = item.nextEpisode
                    "${item.mediaType.name}-${item.id}-${ep?.seasonNumber ?: -1}-${ep?.episodeNumber ?: -1}"
                },
                contentType = { _, item -> item.mediaType }
            ) { index, item ->
                // Read state inside item to ensure recomposition on focus change
                val itemIsFocused = currentIsCurrentRow && index == currentFocusedIndex
                if (isRanked) {
                    // RANKED ITEM: Number + Card
                    Box(
                        modifier = Modifier
                            .width(210.dp)  // Smaller to fit 4.5 cards
                            .height(140.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        // Large Rank Number
                        Text(
                            text = "${index + 1}",
                            style = TextStyle(
                                fontSize = 100.sp,  // Smaller rank numbers
                                fontWeight = FontWeight.Black,
                                color = RankNumberColor,
                                letterSpacing = (-6).sp
                            ),
                            modifier = Modifier
                                .offset(x = (-8).dp, y = 20.dp)
                                .graphicsLayer { alpha = 0.9f }
                        )

                        // The Card (offset to right)
                        Box(modifier = Modifier.padding(start = 60.dp)) {
                            val cardLogoUrl = cardLogoUrls["${item.mediaType}_${item.id}"]
                            ArvioMediaCard(
                                item = item,
                                width = 140.dp,  // Smaller cards
                                isLandscape = true,
                                logoImageUrl = cardLogoUrl,
                                showProgress = false,
                                isFocusedOverride = itemIsFocused,
                                enableSystemFocus = false,
                                onFocused = { onItemFocused(item, index) },
                                onClick = { onItemClick(item) },
                            )
                        }
                    }
                } else {
                    // Standard Card - keep width aligned with scroll math
                    val cardLogoUrl = cardLogoUrls["${item.mediaType}_${item.id}"]
                    ArvioMediaCard(
                        item = item,
                        width = itemWidth,
                        isLandscape = true,
                        logoImageUrl = cardLogoUrl,
                        showProgress = isContinueWatching,
                        isFocusedOverride = itemIsFocused,
                        enableSystemFocus = false,
                        onFocused = { onItemFocused(item, index) },
                        onClick = { onItemClick(item) },
                    )
                }
            }
            }  // Close TvLazyRow
        }  // Close Box
    }  // Close Column
}
