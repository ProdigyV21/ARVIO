@file:OptIn(androidx.tv.material3.ExperimentalTvMaterial3Api::class)

package com.arflix.tv.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.drawBehind
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.zIndex
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
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.components.MediaCard as ArvioMediaCard
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.ui.components.MediaContextMenu
import com.arflix.tv.ui.components.rememberCardLayoutMode
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.max
import kotlin.math.min
import kotlin.math.abs
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

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
private class HomeFocusState(
    initialRowIndex: Int = 0,
    initialItemIndex: Int = 0,
    initialSidebarIndex: Int = 1
) {
    var isSidebarFocused by mutableStateOf(false)
    var sidebarFocusIndex by mutableIntStateOf(initialSidebarIndex)
    var currentRowIndex by mutableIntStateOf(initialRowIndex)
    var currentItemIndex by mutableIntStateOf(initialItemIndex)
    var lastNavEventTime by mutableLongStateOf(0L)

    companion object {
        val Saver: androidx.compose.runtime.saveable.Saver<HomeFocusState, List<Int>> =
            androidx.compose.runtime.saveable.Saver(
                save = { listOf(it.currentRowIndex, it.currentItemIndex, it.sidebarFocusIndex) },
                restore = { HomeFocusState(it[0], it[1], it[2]) }
            )
    }
}

private fun getFocusedItem(categories: List<Category>, rowIndex: Int, itemIndex: Int): MediaItem? {
    val row = categories.getOrNull(rowIndex)
    return row?.items?.getOrNull(itemIndex)
        ?: row?.items?.firstOrNull()
        ?: categories.firstOrNull()?.items?.firstOrNull()
}

private fun isActionableHomeItem(item: MediaItem?): Boolean {
    return item != null && item.id > 0 && !item.isPlaceholder
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
    onNavigateToTv: (channelId: String?, streamUrl: String?) -> Unit = { _, _ -> },
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onExitApp: () -> Unit = {}
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()

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
    // Performance: Directly collect StateFlow instead of syncing to mutableStateMapOf
    // This avoids O(n) iteration on every logo cache update
    val cardLogoUrls by viewModel.cardLogoUrls.collectAsState()
    val usePosterCards = rememberCardLayoutMode() == CardLayoutMode.POSTER
    val lifecycleOwner = LocalLifecycleOwner.current
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        // Prevent stale select key events from previous screen from reopening details.
        suppressSelectUntilMs = SystemClock.elapsedRealtime() + 900L
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refreshContinueWatchingOnly(force = true)
                suppressSelectUntilMs = SystemClock.elapsedRealtime() + 900L
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
    val displayHeroOverview = uiState.heroOverviewOverride
    val latestDisplayCategories by rememberUpdatedState(displayCategories)

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
                0.0f to Color.Black.copy(alpha = 0.95f),
                0.12f to Color.Black.copy(alpha = 0.88f),
                0.22f to Color.Black.copy(alpha = 0.72f),
                0.32f to Color.Black.copy(alpha = 0.50f),
                0.42f to Color.Black.copy(alpha = 0.30f),
                0.55f to Color.Black.copy(alpha = 0.10f),
                0.70f to Color.Transparent,
                1.0f to Color.Transparent
            )
        )
    }
    val heroTopScrim = remember {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Black.copy(alpha = 0.7f),
                0.06f to Color.Black.copy(alpha = 0.45f),
                0.15f to Color.Black.copy(alpha = 0.15f),
                0.25f to Color.Transparent,
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
    val contentStartPadding = if (isMobile) 16.dp else 36.dp

    // Use rememberSaveable to persist focus position across navigation (back from details page)
    val focusState = rememberSaveable(saver = HomeFocusState.Saver) { HomeFocusState() }
    val fastScrollThresholdMs = 650L

    // Context menu state (Menu button only, no long-press)
    var showContextMenu by remember { mutableStateOf(false) }
    var contextMenuItem by remember { mutableStateOf<MediaItem?>(null) }
    var contextMenuIsContinueWatching by remember { mutableStateOf(false) }
    var contextMenuIsInWatchlist by remember { mutableStateOf(false) }

    BackHandler(enabled = showContextMenu) {
        showContextMenu = false
        contextMenuItem = null
        contextMenuIsContinueWatching = false
    }

    // Preload logos for current and next rows when row changes
    LaunchedEffect(Unit) {
        snapshotFlow { focusState.currentRowIndex }
            .distinctUntilChanged()
            .collectLatest { rowIndex ->
                viewModel.preloadLogosForCategory(rowIndex, prioritizeVisible = true)
                viewModel.preloadLogosForCategory(rowIndex + 1, prioritizeVisible = false)
            }
    }

    // Update hero based on focused item with adaptive idle delay to avoid heavy churn while scrolling
    LaunchedEffect(Unit) {
        snapshotFlow {
            Triple(
                focusState.currentRowIndex,
                focusState.currentItemIndex,
                // Include first item ID so the flow re-emits when categories load or CW changes,
                // preventing distinctUntilChanged from swallowing the initial (0,0) emission.
                latestDisplayCategories.firstOrNull()?.items?.firstOrNull()?.id ?: -1
            )
        }
            .distinctUntilChanged()
            .collectLatest { (rowIndex, itemIndex, _) ->
                val categoriesSnapshot = latestDisplayCategories
                if (categoriesSnapshot.isEmpty() || focusState.isSidebarFocused) return@collectLatest
                val now = SystemClock.elapsedRealtime()
                val isFastScrolling = now - focusState.lastNavEventTime < fastScrollThresholdMs
                viewModel.onFocusChanged(rowIndex, itemIndex, shouldPrefetch = true)
                delay(if (isFastScrolling) 700L else 220L)

                val idleFor = SystemClock.elapsedRealtime() - focusState.lastNavEventTime
                if (idleFor < fastScrollThresholdMs) return@collectLatest

                val row = categoriesSnapshot.getOrNull(rowIndex)
                val newHeroItem = row?.items?.getOrNull(itemIndex)
                    ?: row?.items?.firstOrNull()
                    ?: categoriesSnapshot.firstOrNull()?.items?.firstOrNull()

                if (newHeroItem != null) {
                    viewModel.updateHeroItem(newHeroItem)
                }
            }
    }

    // Infinite row pagination: keep initial Home fast, then append as user reaches row end.
    LaunchedEffect(Unit) {
        snapshotFlow {
            Triple(
                focusState.currentRowIndex,
                focusState.currentItemIndex,
                focusState.isSidebarFocused
            )
        }
            .distinctUntilChanged()
            .collectLatest { (rowIndex, itemIndex, sidebarFocused) ->
                if (sidebarFocused) return@collectLatest
                val category = latestDisplayCategories.getOrNull(rowIndex) ?: return@collectLatest
                viewModel.maybeLoadNextPageForCategory(category.id, itemIndex)
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

    // ── IPTV hero live-player state ──
    val isHeroIptv = displayHeroItem != null && viewModel.isIptvItem(displayHeroItem)
    val heroVideoUrl = displayHeroItem?.let { if (isHeroIptv) viewModel.getIptvStreamUrl(it.id) else null }

    val heroOkHttp = remember {
        OkHttpClient.Builder()
            .connectionPool(ConnectionPool(2, 2, TimeUnit.MINUTES))
            .followRedirects(true).followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .dns(OkHttpProvider.dns)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }
    val heroDataSourceFactory = remember(heroOkHttp) {
        OkHttpDataSource.Factory(heroOkHttp).setUserAgent("ARVIO/1.7.0 (Android TV)")
    }
    val heroHlsFactory = remember(heroDataSourceFactory) {
        HlsMediaSource.Factory(heroDataSourceFactory).setAllowChunklessPreparation(true)
    }
    val heroDefaultFactory = remember(heroDataSourceFactory) {
        DefaultMediaSourceFactory(context).setDataSourceFactory(heroDataSourceFactory)
    }
    val heroExoPlayer = remember {
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(8_000, 30_000, 1_500, 3_000)
            .setPrioritizeTimeOverSizeThresholds(true)
            .build()
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(heroDefaultFactory)
            .setLoadControl(loadControl)
            .build().apply {
                playWhenReady = false
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
                volume = 1f
            }
    }
    DisposableEffect(Unit) { onDispose { heroExoPlayer.release() } }

    LaunchedEffect(heroVideoUrl) {
        if (heroVideoUrl != null) {
            heroExoPlayer.stop()
            heroExoPlayer.clearMediaItems()
            val mi = androidx.media3.common.MediaItem.Builder()
                .setUri(heroVideoUrl)
                .setLiveConfiguration(
                    androidx.media3.common.MediaItem.LiveConfiguration.Builder()
                        .setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f)
                        .setTargetOffsetMs(4_000).build()
                ).build()
            val lower = heroVideoUrl.lowercase()
            if (lower.contains(".m3u8") || lower.contains("/hls") || lower.contains("format=hls")) {
                heroExoPlayer.setMediaSource(heroHlsFactory.createMediaSource(mi))
            } else {
                heroExoPlayer.setMediaItem(mi)
            }
            heroExoPlayer.volume = 1f
            heroExoPlayer.prepare()
            heroExoPlayer.playWhenReady = true
        } else {
            heroExoPlayer.stop()
            heroExoPlayer.clearMediaItems()
            heroExoPlayer.playWhenReady = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
      ) {
        val currentBackdrop = displayHeroItem?.backdrop ?: displayHeroItem?.image
        // On mobile, the hero backdrop is rendered inline inside MobileHomeRowsLayer — skip the fixed backdrop.
        // On TV, fill the entire screen with the backdrop.
        if (!isMobile) {
        val backdropModifier = Modifier.fillMaxSize()
        Box(modifier = backdropModifier) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = backdropGradient
                    )
            )

            Crossfade(
                targetState = currentBackdrop,
                animationSpec = tween(durationMillis = 320),
                label = "hero_backdrop_crossfade"
            ) { backdropUrl ->
                if (backdropUrl != null) {
                    val (backdropWidthPx, backdropHeightPx) = backdropSize
                    val request = remember(backdropUrl, backdropWidthPx, backdropHeightPx) {
                        ImageRequest.Builder(context)
                            .data(backdropUrl)
                            .size(backdropWidthPx, backdropHeightPx)
                            .precision(Precision.INEXACT)
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

            if (heroVideoUrl != null) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            player = heroExoPlayer
                            useController = false
                            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                            setShutterBackgroundColor(android.graphics.Color.TRANSPARENT)
                            setKeepContentOnPlayerReset(true)
                        }
                    },
                    update = { pv -> pv.player = heroExoPlayer },
                    modifier = Modifier.fillMaxSize()
                )
            }

            // === SCRIM SYSTEM ===
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawBehind {
                            drawRect(brush = heroLeftScrim)
                            drawRect(brush = heroTopScrim)
                            drawRect(brush = heroBottomScrim)
                        }
                )
        }
        } // end if (!isMobile) backdrop
        
        HomeInputLayer(
            categories = displayCategories,
            cardLogoUrls = cardLogoUrls,
            focusState = focusState,
            suppressSelectUntilMs = suppressSelectUntilMs,
            contentStartPadding = contentStartPadding,
            fastScrollThresholdMs = fastScrollThresholdMs,
            usePosterCards = usePosterCards,
            isContextMenuOpen = showContextMenu,
            isMobile = isMobile,
            heroItem = displayHeroItem,
            heroOverviewOverride = displayHeroOverview,
            onPlay = {
                displayHeroItem?.let { item ->
                    if (viewModel.isIptvItem(item)) {
                        onNavigateToTv(viewModel.getIptvChannelId(item), viewModel.getIptvStreamUrl(item.id))
                    } else {
                        onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                    }
                }
            },
            onDetails = {
                displayHeroItem?.let { item ->
                    if (viewModel.isIptvItem(item)) {
                        onNavigateToTv(viewModel.getIptvChannelId(item), viewModel.getIptvStreamUrl(item.id))
                    } else {
                        onNavigateToDetails(item.mediaType, item.id, null, null)
                    }
                }
            },
            currentProfile = currentProfile,
            onNavigateToDetails = onNavigateToDetails,
            onNavigateToSearch = onNavigateToSearch,
            onNavigateToWatchlist = onNavigateToWatchlist,
            onNavigateToTv = onNavigateToTv,
            getIptvStreamUrl = { itemId -> viewModel.getIptvStreamUrl(itemId) },
            onNavigateToSettings = onNavigateToSettings,
            onSwitchProfile = onSwitchProfile,
            onExitApp = onExitApp,
            onOpenContextMenu = { item, isContinue ->
                contextMenuItem = item
                contextMenuIsContinueWatching = isContinue
                showContextMenu = true
            }
        )

        HomeHeroLayer(
            heroItem = displayHeroItem,
            heroLogoUrl = displayHeroLogo,
            heroOverviewOverride = displayHeroOverview,
            contentStartPadding = contentStartPadding,
            isMobile = isMobile,
            onNavigateToDetails = onNavigateToDetails,
            onNavigateToTv = { channelId, streamUrl -> onNavigateToTv(channelId, streamUrl) },
            isIptvItem = { item -> viewModel.isIptvItem(item) },
            getIptvChannelId = { item -> viewModel.getIptvChannelId(item) },
            getIptvStreamUrl = { itemId -> viewModel.getIptvStreamUrl(itemId) }
        )

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(120f)
            ) {
                MediaContextMenu(
                    isVisible = showContextMenu,
                    title = item.title,
                    isInWatchlist = contextMenuIsInWatchlist,
                    isWatched = item.isWatched,
                    isContinueWatching = contextMenuIsContinueWatching,
                    onPlay = {
                        if (viewModel.isIptvItem(item)) {
                            onNavigateToTv(viewModel.getIptvChannelId(item), viewModel.getIptvStreamUrl(item.id))
                        } else {
                            onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                        }
                    },
                    onViewDetails = {
                        if (viewModel.isIptvItem(item)) {
                            onNavigateToTv(viewModel.getIptvChannelId(item), viewModel.getIptvStreamUrl(item.id))
                        } else {
                            onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                        }
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
    overviewOverride: String? = null,
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

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        // Performance: Instant logo transition, no animation overhead
        key(logoUrl, item.id) {
            val currentLogoUrl = logoUrl
            val currentItem = item
            val configuration = LocalConfiguration.current
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

        // Performance: Use key instead of AnimatedContent for faster transitions
        key(item.id) {
            val currentItem = item
            val isIptvHero = currentItem.status?.startsWith("iptv:") == true
            Column {
                if (isIptvHero) {
                    // IPTV hero: LIVE badge + channel group
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .background(AccentRed, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "LIVE",
                                style = ArflixTypography.caption.copy(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black
                                ),
                                color = Color.White
                            )
                        }
                        if (currentItem.subtitle.isNotBlank()) {
                            Text(
                                text = currentItem.subtitle,
                                style = ArflixTypography.caption.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    shadow = textShadow
                                ),
                                color = Color.White
                            )
                        }
                    }
                } else {
                // Get actual genre names from genre IDs (memoized to avoid list allocations per recomposition)
                val genreText = remember(currentItem.id, currentItem.genreIds) {
                    val genreMap = if (currentItem.mediaType == MediaType.TV) tvGenres else movieGenres
                    currentItem.genreIds.mapNotNull { genreMap[it] }.take(2).joinToString(" / ")
                }
                val displayDate = currentItem.releaseDate?.takeIf { it.isNotEmpty() } ?: currentItem.year
                val hasDuration = currentItem.duration.isNotEmpty() && currentItem.duration != "0m"
                val hasGenre = genreText.isNotEmpty()
                val budgetText = remember(currentItem.mediaType, currentItem.budget) {
                    val budgetValue = currentItem.budget
                    if (currentItem.mediaType == MediaType.MOVIE && budgetValue != null && budgetValue > 0L) {
                        formatBudgetCompact(budgetValue)
                    } else {
                        null
                    }
                }

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

                    if (!budgetText.isNullOrBlank()) {
                        if (displayDate.isNotEmpty() || hasGenre || hasDuration || ratingValue > 0f) {
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
                            text = "Budget $budgetText",
                            style = ArflixTypography.caption.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                shadow = textShadow
                            ),
                            color = Color.White
                        )
                    }
                }
                } // end else (non-IPTV metadata)

                Spacer(modifier = Modifier.height(14.dp))

                // Overview text (EPG data for IPTV, synopsis for movies/shows)
                val displayOverview = (overviewOverride ?: currentItem.overview)
                    .replace(Regex("<[^>]*>"), " ")
                    .replace(Regex("[\\u00A0\\u2007\\u202F]"), " ")
                    .replace(Regex("\\p{Z}+"), " ")
                    .replace(Regex("\\s+"), " ")
                    .trim()
                    .ifBlank { "No description available." }

                val overviewMaxHeight = 66.dp
                Box(
                    modifier = Modifier
                        .width(560.dp)
                        .height(overviewMaxHeight)
                ) {
                    Text(
                        text = displayOverview,
                        style = ArflixTypography.body.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp,
                            shadow = textShadow
                        ),
                        color = Color.White,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

private fun formatBudgetCompact(budget: Long): String {
    return when {
        budget >= 1_000_000_000 -> "$${budget / 1_000_000_000.0}B"
        budget >= 1_000_000 -> "$${budget / 1_000_000}M"
        budget >= 1_000 -> "$${budget / 1_000}K"
        else -> "$$budget"
    }
}

@Composable
private fun HomeHeroLayer(
    heroItem: MediaItem?,
    heroLogoUrl: String?,
    heroOverviewOverride: String?,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    isMobile: Boolean = false,
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit = { _, _, _, _ -> },
    onNavigateToTv: (channelId: String?, streamUrl: String?) -> Unit = { _, _ -> },
    isIptvItem: (MediaItem) -> Boolean = { false },
    getIptvChannelId: (MediaItem) -> String? = { null },
    getIptvStreamUrl: (Int) -> String? = { null }
) {
    if (isMobile) {
        // Mobile hero is rendered inline inside MobileHomeRowsLayer's LazyColumn — no fixed overlay needed.
    } else {
        // TV hero: full-screen overlay with clearlogo
        val configuration = LocalConfiguration.current
        val contentRowHeight = (configuration.screenHeightDp * 0.34f).dp.coerceIn(240.dp, 320.dp)
        val contentRowBottomPadding = 12.dp
        val contentRowTopPadding = contentRowHeight + contentRowBottomPadding
        val buttonsBottomPadding = contentRowTopPadding - 10.dp
        val heroBottomPadding = buttonsBottomPadding + if (configuration.screenHeightDp < 720) 32.dp else 40.dp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = AppTopBarContentTopInset)
                .zIndex(3f)
        ) {
            heroItem?.let { item ->
                HeroSection(
                    item = item,
                    logoUrl = heroLogoUrl,
                    overviewOverride = heroOverviewOverride,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = contentStartPadding, end = 400.dp)
                        .offset(y = -heroBottomPadding)
                    )
            }
        }
    }
}

/** Compact mobile hero overlay with gradient, title, metadata, description, and action buttons. */
@Composable
private fun MobileHeroOverlay(
    item: MediaItem,
    overviewOverride: String?,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    onPlay: () -> Unit,
    onDetails: () -> Unit
) {
    val mobileHeroGradient = remember {
        Brush.verticalGradient(
            listOf(
                Color.Transparent,
                Color.Transparent,
                Color.Black.copy(alpha = 0.7f),
                Color.Black.copy(alpha = 0.95f)
            )
        )
    }

    val textShadow = Shadow(
        color = Color.Black.copy(alpha = 0.9f),
        offset = Offset(0f, 2f),
        blurRadius = 8f
    )

    val genreText = remember(item.id, item.genreIds) {
        val genreMap = if (item.mediaType == MediaType.TV) tvGenres else movieGenres
        item.genreIds.mapNotNull { genreMap[it] }.take(2).joinToString(" | ")
    }
    val year = item.releaseDate?.take(4)?.takeIf { it.isNotEmpty() } ?: item.year
    val rating = item.imdbRating.ifEmpty { item.tmdbRating }
    val metaParts = listOfNotNull(
        genreText.takeIf { it.isNotEmpty() },
        year.takeIf { it.isNotEmpty() },
        rating.takeIf { it.isNotEmpty() }?.let { "IMDb $it" }
    )
    val metaLine = metaParts.joinToString(" | ")

    val displayOverview = (overviewOverride ?: item.overview)
        .replace(Regex("<[^>]*>"), " ")
        .replace(Regex("[\\u00A0\\u2007\\u202F]"), " ")
        .replace(Regex("\\p{Z}+"), " ")
        .replace(Regex("\\s+"), " ")
        .trim()
        .ifBlank { "No description available." }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.42f)
            .zIndex(3f)
    ) {
        // Bottom gradient over the backdrop
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .align(Alignment.BottomCenter)
                .background(mobileHeroGradient)
        )

        // Content at the bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = contentStartPadding, end = contentStartPadding, bottom = 12.dp)
        ) {
            // Title
            Text(
                text = item.title,
                style = ArflixTypography.heroTitle.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = textShadow
                ),
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (metaLine.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = metaLine,
                    style = ArflixTypography.caption.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        shadow = textShadow
                    ),
                    color = Color.White.copy(alpha = 0.8f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = displayOverview,
                style = ArflixTypography.body.copy(
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    shadow = textShadow
                ),
                color = Color.White.copy(alpha = 0.75f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Action buttons
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                // Play button
                Box(
                    modifier = Modifier
                        .background(AccentRed, RoundedCornerShape(8.dp))
                        .clickable(onClick = onPlay)
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Play",
                            style = ArflixTypography.caption.copy(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                    }
                }

                // Details button
                Box(
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                        .clickable(onClick = onDetails)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Details",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Details",
                            style = ArflixTypography.caption.copy(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

/** Swipeable hero carousel for mobile: HorizontalPager with backdrop, gradient, title/meta/description, page dots, and auto-scroll. */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MobileHeroCarousel(
    categories: List<Category>,
    cardLogoUrls: Map<String, String>,
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    // Hero area height: 40% of screen
    val heroHeight = (configuration.screenHeightDp * 0.40f).dp

    // Build hero items: Continue Watching first, then first catalog, up to 5 items
    val heroItems = remember(categories) {
        val cwItems = categories
            .firstOrNull { it.id == "continue_watching" }
            ?.items
            ?.filter { it.id > 0 && !it.isPlaceholder }
            .orEmpty()
        val catalogItems = categories
            .firstOrNull { it.id != "continue_watching" }
            ?.items
            ?.filter { it.id > 0 && !it.isPlaceholder }
            .orEmpty()
        (cwItems + catalogItems).distinctBy { "${it.mediaType}_${it.id}" }.take(5)
    }

    if (heroItems.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { heroItems.size })

    // Auto-scroll every 5 seconds
    LaunchedEffect(pagerState, heroItems.size) {
        if (heroItems.size <= 1) return@LaunchedEffect
        while (true) {
            delay(5000L)
            val nextPage = (pagerState.currentPage + 1) % heroItems.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    // Strong gradient covering bottom 70% for smooth fade to black
    val mobileHeroGradient = remember {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Transparent,
                0.30f to Color.Transparent,
                0.50f to Color.Black.copy(alpha = 0.4f),
                0.75f to Color.Black.copy(alpha = 0.85f),
                1.0f to Color.Black
            )
        )
    }

    // Extra thin gradient at the very bottom edge for seamless transition to card rows
    val bottomEdgeFade = remember {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Transparent,
                0.7f to Color.Transparent,
                1.0f to Color.Black
            )
        )
    }

    val textShadow = remember {
        Shadow(
            color = Color.Black.copy(alpha = 0.9f),
            offset = Offset(0f, 2f),
            blurRadius = 8f
        )
    }

    Column {
        // Pager
        Box {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heroHeight)
            ) { page ->
                val item = heroItems[page]
                val backdropUrl = item.backdrop ?: item.image

                val backdropSizePx = remember(configuration, density, heroHeight) {
                    val widthPx = with(density) { configuration.screenWidthDp.dp.roundToPx() }
                    val heightPx = with(density) { heroHeight.roundToPx() }
                    widthPx.coerceAtMost(3840).coerceAtLeast(1) to heightPx.coerceAtMost(2160).coerceAtLeast(1)
                }

                val heroLogoUrl = cardLogoUrls["${item.mediaType}_${item.id}"]

                val genreText = remember(item.id, item.genreIds) {
                    val genreMap = if (item.mediaType == MediaType.TV) tvGenres else movieGenres
                    item.genreIds.mapNotNull { genreMap[it] }.take(2).joinToString(" | ")
                }
                val year = item.releaseDate?.take(4)?.takeIf { it.isNotEmpty() } ?: item.year
                val rating = item.imdbRating.ifEmpty { item.tmdbRating }
                val ratingValue = parseRatingValue(rating)

                val displayOverview = remember(item.id, item.overview) {
                    item.overview
                        .replace(Regex("<[^>]*>"), " ")
                        .replace(Regex("[\\u00A0\\u2007\\u202F]"), " ")
                        .replace(Regex("\\p{Z}+"), " ")
                        .replace(Regex("\\s+"), " ")
                        .trim()
                        .ifBlank { "No description available." }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onNavigateToDetails(item.mediaType, item.id, null, null)
                        }
                ) {
                    // Backdrop image - Crop to fill without letterboxing
                    if (backdropUrl != null) {
                        val (bw, bh) = backdropSizePx
                        val request = remember(backdropUrl, bw, bh) {
                            ImageRequest.Builder(context)
                                .data(backdropUrl)
                                .size(bw, bh)
                                .precision(Precision.INEXACT)
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

                    // Strong bottom gradient covering bottom 70%
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.70f)
                            .align(Alignment.BottomCenter)
                            .background(mobileHeroGradient)
                    )

                    // Text content at bottom-left
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        // Clearlogo image or fallback title text
                        if (heroLogoUrl != null) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(heroLogoUrl)
                                    .precision(Precision.INEXACT)
                                    .allowHardware(true)
                                    .crossfade(false)
                                    .build(),
                                contentDescription = item.title,
                                contentScale = ContentScale.Fit,
                                alignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .height(44.dp)
                                    .width(200.dp)
                            )
                        } else {
                            Text(
                                text = item.title,
                                style = ArflixTypography.heroTitle.copy(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    shadow = textShadow
                                ),
                                color = Color.White,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Metadata row with IMDb badge
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (genreText.isNotEmpty()) {
                                Text(
                                    text = genreText,
                                    style = ArflixTypography.caption.copy(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        shadow = textShadow
                                    ),
                                    color = Color.White.copy(alpha = 0.8f),
                                    maxLines = 1
                                )
                            }
                            if (year.isNotEmpty()) {
                                if (genreText.isNotEmpty()) {
                                    Text(
                                        text = "|",
                                        style = ArflixTypography.caption.copy(fontSize = 11.sp, shadow = textShadow),
                                        color = Color.White.copy(alpha = 0.5f)
                                    )
                                }
                                Text(
                                    text = year,
                                    style = ArflixTypography.caption.copy(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        shadow = textShadow
                                    ),
                                    color = Color.White.copy(alpha = 0.8f),
                                    maxLines = 1
                                )
                            }
                            // IMDb rating badge (yellow rounded box)
                            if (ratingValue > 0f) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                                    modifier = Modifier
                                        .background(Color(0xFFF5C518), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "IMDb",
                                        style = ArflixTypography.caption.copy(
                                            fontSize = 8.sp,
                                            fontWeight = FontWeight.Black
                                        ),
                                        color = Color.Black
                                    )
                                    Text(
                                        text = rating,
                                        style = ArflixTypography.caption.copy(
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = Color.Black
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        // Description with lighter alpha and text shadow
                        Text(
                            text = displayOverview,
                            style = ArflixTypography.body.copy(
                                fontSize = 11.sp,
                                lineHeight = 15.sp,
                                shadow = textShadow
                            ),
                            color = Color.White.copy(alpha = 0.7f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        // Bottom padding above page indicators
                        Spacer(modifier = Modifier.height(16.dp))

                        // Page indicator dots inside the carousel page area
                        if (heroItems.size > 1) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                heroItems.forEachIndexed { index, _ ->
                                    val isSelected = pagerState.currentPage == index
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 3.dp)
                                            .size(if (isSelected) 8.dp else 6.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (isSelected) Color.White
                                                else Color.White.copy(alpha = 0.30f)
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Smooth fade-to-black at the very bottom edge (overlays the pager)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .align(Alignment.BottomCenter)
                    .background(bottomEdgeFade)
            )
        }
    }
}

@Composable
private fun HomeInputLayer(
    categories: List<Category>,
    cardLogoUrls: Map<String, String>,
    focusState: HomeFocusState,
    suppressSelectUntilMs: Long,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    fastScrollThresholdMs: Long,
    usePosterCards: Boolean,
    isContextMenuOpen: Boolean,
    isMobile: Boolean = false,
    heroItem: MediaItem? = null,
    heroOverviewOverride: String? = null,
    onPlay: () -> Unit = {},
    onDetails: () -> Unit = {},
    currentProfile: com.arflix.tv.data.model.Profile?,
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToWatchlist: () -> Unit,
    onNavigateToTv: (channelId: String?, streamUrl: String?) -> Unit,
    getIptvStreamUrl: (itemId: Int) -> String?,
    onNavigateToSettings: () -> Unit,
    onSwitchProfile: () -> Unit,
    onExitApp: () -> Unit,
    onOpenContextMenu: (MediaItem, Boolean) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var selectPressedInHome by remember { mutableStateOf(false) }
    var selectDownAtMs by remember { mutableLongStateOf(0L) }
    var rootHasFocus by remember { mutableStateOf(false) }
    var preferredCategoryId by rememberSaveable { mutableStateOf<String?>(null) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    LaunchedEffect(hasProfile) {
        if (hasProfile) focusState.sidebarFocusIndex = 2
    }

    LaunchedEffect(focusState.currentRowIndex, categories) {
        preferredCategoryId = categories.getOrNull(focusState.currentRowIndex)?.id
    }

    LaunchedEffect(categories) {
        if (categories.isEmpty()) {
            focusState.currentRowIndex = 0
            focusState.currentItemIndex = 0
            return@LaunchedEffect
        }

        val restoredRow = preferredCategoryId
            ?.let { id -> categories.indexOfFirst { it.id == id } }
            ?.takeIf { it >= 0 }
        val boundedRow = (restoredRow ?: focusState.currentRowIndex)
            .coerceIn(0, (categories.size - 1).coerceAtLeast(0))
        focusState.currentRowIndex = boundedRow

        val currentRowItems = categories.getOrNull(boundedRow)?.items.orEmpty()
        if (currentRowItems.isEmpty()) {
            val fallbackRow = categories.indexOfFirst { it.items.isNotEmpty() }.takeIf { it >= 0 } ?: 0
            focusState.currentRowIndex = fallbackRow
            focusState.currentItemIndex = 0
            preferredCategoryId = categories.getOrNull(fallbackRow)?.id
        } else if (focusState.currentItemIndex > currentRowItems.lastIndex) {
            focusState.currentItemIndex = currentRowItems.lastIndex
        } else if (focusState.currentItemIndex < 0) {
            focusState.currentItemIndex = 0
        }

        if (!focusState.isSidebarFocused && !rootHasFocus) {
            runCatching { focusRequester.requestFocus() }
        }
    }

    val keyEventModifier = if (isMobile) {
        Modifier // No D-pad key handling on mobile
    } else {
        Modifier.onPreviewKeyEvent { event ->
            if (isContextMenuOpen) {
                return@onPreviewKeyEvent false
            }
            when (event.type) {
                KeyEventType.KeyDown -> when (event.key) {
                    Key.Enter, Key.DirectionCenter -> {
                        if (SystemClock.elapsedRealtime() < suppressSelectUntilMs) {
                            return@onPreviewKeyEvent true
                        }
                        selectPressedInHome = true
                        if (selectDownAtMs == 0L) {
                            selectDownAtMs = SystemClock.elapsedRealtime()
                        }
                        true
                    }
                    Key.DirectionLeft -> {
                        if (!focusState.isSidebarFocused) {
                            if (focusState.currentItemIndex == 0) {
                                true
                            } else {
                                focusState.currentItemIndex--
                                focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                                true
                            }
                        } else {
                            if (focusState.sidebarFocusIndex > 0) {
                                focusState.sidebarFocusIndex--
                                focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                            }
                            true
                        }
                    }
                    Key.DirectionRight -> {
                        if (focusState.isSidebarFocused) {
                            if (focusState.sidebarFocusIndex < maxSidebarIndex) {
                                focusState.sidebarFocusIndex++
                                focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                            }
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
                        if (focusState.isSidebarFocused) {
                            true
                        } else if (focusState.currentRowIndex > 0) {
                            focusState.currentRowIndex--
                            focusState.currentItemIndex = 0
                            focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                            true
                        } else {
                            focusState.isSidebarFocused = true
                            true
                        }
                    }
                    Key.DirectionDown -> {
                        if (focusState.isSidebarFocused) {
                            focusState.isSidebarFocused = false
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
                                currentItem?.takeIf { isActionableHomeItem(it) }?.let { item ->
                                    val currentCategory = categories.getOrNull(focusState.currentRowIndex)
                                    val isContinue = currentCategory?.id == "continue_watching"
                                    onOpenContextMenu(item, isContinue)
                                }
                            }
                            true
                        }
                        else -> false
                    }
                    KeyEventType.KeyUp -> when (event.key) {
                        Key.Enter, Key.DirectionCenter -> {
                            if (SystemClock.elapsedRealtime() < suppressSelectUntilMs) {
                                selectPressedInHome = false
                                selectDownAtMs = 0L
                                return@onPreviewKeyEvent true
                            }
                            if (!selectPressedInHome) {
                                // Ignore stale KeyUp events that can arrive after screen navigation.
                                selectDownAtMs = 0L
                                return@onPreviewKeyEvent true
                            }
                            val heldMs = (SystemClock.elapsedRealtime() - selectDownAtMs).coerceAtLeast(0L)
                            selectPressedInHome = false
                            selectDownAtMs = 0L

                            if (heldMs >= 900L && !focusState.isSidebarFocused) {
                                val currentItem = getFocusedItem(
                                    categories,
                                    focusState.currentRowIndex,
                                    focusState.currentItemIndex
                                )
                                currentItem?.takeIf { isActionableHomeItem(it) }?.let { item ->
                                    val currentCategory = categories.getOrNull(focusState.currentRowIndex)
                                    val isContinue = currentCategory?.id == "continue_watching"
                                    onOpenContextMenu(item, isContinue)
                                }
                                return@onPreviewKeyEvent true
                            }

                            if (focusState.isSidebarFocused) {
                                if (hasProfile && focusState.sidebarFocusIndex == 0) {
                                    onSwitchProfile()
                                } else {
                                    when (topBarFocusedItem(focusState.sidebarFocusIndex, hasProfile)) {
                                        SidebarItem.SEARCH -> onNavigateToSearch()
                                        SidebarItem.HOME -> { }
                                        SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                        SidebarItem.TV -> onNavigateToTv(null, null)
                                        SidebarItem.SETTINGS -> onNavigateToSettings()
                                        null -> Unit
                                    }
                                }
                            } else {
                                val currentItem = getFocusedItem(
                                    categories,
                                    focusState.currentRowIndex,
                                    focusState.currentItemIndex
                                )
                                currentItem?.takeIf { isActionableHomeItem(it) }?.let { item ->
                                    val iptvId = item.status?.removePrefix("iptv:")?.takeIf { item.status?.startsWith("iptv:") == true && it.isNotBlank() }
                                    if (iptvId != null) {
                                        onNavigateToTv(iptvId, getIptvStreamUrl(item.id))
                                    } else {
                                        onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                                    }
                                }
                            }
                            true
                        }
                        else -> false
                    }
                    else -> false
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .focusRequester(focusRequester)
            .onFocusChanged { rootHasFocus = it.hasFocus }
            .focusable()
            .then(keyEventModifier)
    ) {
        if (!isMobile) {
            AppTopBar(
                selectedItem = SidebarItem.HOME,
                isFocused = focusState.isSidebarFocused,
                focusedIndex = focusState.sidebarFocusIndex,
                profile = currentProfile
            )
        }

        HomeRowsLayer(
            categories = categories,
            cardLogoUrls = cardLogoUrls,
            focusState = focusState,
            contentStartPadding = contentStartPadding,
            fastScrollThresholdMs = fastScrollThresholdMs,
            usePosterCards = usePosterCards,
            isMobile = isMobile,
            heroItem = heroItem,
            heroOverviewOverride = heroOverviewOverride,
            onPlay = onPlay,
            onDetails = onDetails,
            onNavigateToDetails = onNavigateToDetails,
            onItemClick = { item ->
                if (!isActionableHomeItem(item)) {
                    return@HomeRowsLayer
                }
                val iptvId = item.status?.removePrefix("iptv:")?.takeIf { item.status?.startsWith("iptv:") == true && it.isNotBlank() }
                if (iptvId != null) {
                    onNavigateToTv(iptvId, getIptvStreamUrl(item.id))
                } else {
                    onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                }
            },
            onItemLongClick = if (isMobile) { item, isContinue -> onOpenContextMenu(item, isContinue) } else null
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
    usePosterCards: Boolean,
    isMobile: Boolean = false,
    heroItem: MediaItem? = null,
    heroOverviewOverride: String? = null,
    onPlay: () -> Unit = {},
    onDetails: () -> Unit = {},
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit = { _, _, _, _ -> },
    onItemClick: (MediaItem) -> Unit,
    onItemLongClick: ((MediaItem, Boolean) -> Unit)? = null
) {
    if (isMobile) {
        MobileHomeRowsLayer(
            categories = categories,
            cardLogoUrls = cardLogoUrls,
            contentStartPadding = contentStartPadding,
            usePosterCards = usePosterCards,
            onNavigateToDetails = onNavigateToDetails,
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick
        )
    } else {
        TvHomeRowsLayer(
            categories = categories,
            cardLogoUrls = cardLogoUrls,
            focusState = focusState,
            contentStartPadding = contentStartPadding,
            fastScrollThresholdMs = fastScrollThresholdMs,
            usePosterCards = usePosterCards,
            onItemClick = onItemClick
        )
    }
}

/** Mobile-optimized rows: free-scrolling LazyColumn with smaller cards, no viewport constraint. */
@Composable
private fun MobileHomeRowsLayer(
    categories: List<Category>,
    cardLogoUrls: Map<String, String>,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    usePosterCards: Boolean,
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit = { _, _, _, _ -> },
    onItemClick: (MediaItem) -> Unit,
    onItemLongClick: ((MediaItem, Boolean) -> Unit)? = null
) {
    val mobileItemWidth = if (usePosterCards) 120.dp else 200.dp
    val mobileItemSpacing = 10.dp

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Swipeable hero carousel as the first scrollable item
        item(key = "mobile_hero", contentType = "mobile_hero") {
            MobileHeroCarousel(
                categories = categories,
                cardLogoUrls = cardLogoUrls,
                onNavigateToDetails = onNavigateToDetails
            )
        }

        itemsIndexed(
            items = categories,
            key = { _, category -> category.id },
            contentType = { _, _ -> "mobile_home_category_row" }
        ) { _, category ->
            val isContinueWatching = category.id == "continue_watching"
            val isRanked = category.title.contains("Top 10", ignoreCase = true)

            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                // Section title
                Text(
                    text = category.title,
                    style = ArflixTypography.sectionTitle.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = contentStartPadding,
                        bottom = 8.dp
                    )
                )

                // Horizontal card row with touch scrolling
                LazyRow(
                    contentPadding = PaddingValues(
                        start = contentStartPadding,
                        end = 16.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(mobileItemSpacing)
                ) {
                    itemsIndexed(
                        category.items,
                        key = { _, item ->
                            val episodeSuffix = if (item.nextEpisode != null) "_S${item.nextEpisode.seasonNumber}E${item.nextEpisode.episodeNumber}" else ""
                            "${item.mediaType.name}-${item.id}${episodeSuffix}"
                        },
                        contentType = { _, item -> "${item.mediaType.name}_mobile_card" }
                    ) { index, item ->
                        if (isRanked) {
                            Box(
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(110.dp),
                                contentAlignment = Alignment.BottomStart
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    style = TextStyle(
                                        fontSize = 72.sp,
                                        fontWeight = FontWeight.Black,
                                        color = RankNumberColor,
                                        letterSpacing = (-4).sp
                                    ),
                                    modifier = Modifier
                                        .offset(x = (-6).dp, y = 14.dp)
                                        .graphicsLayer { alpha = 0.9f }
                                )
                                Box(modifier = Modifier.padding(start = 44.dp)) {
                                    val cardLogoUrl = cardLogoUrls["${item.mediaType}_${item.id}"]
                                    ArvioMediaCard(
                                        item = item,
                                        width = 110.dp,
                                        isLandscape = !usePosterCards,
                                        logoImageUrl = cardLogoUrl,
                                        showProgress = false,
                                        isFocusedOverride = false,
                                        enableSystemFocus = false,
                                        onFocused = {},
                                        onClick = { onItemClick(item) },
                                        onLongClick = onItemLongClick?.let { callback -> { callback(item, isContinueWatching) } },
                                    )
                                }
                            }
                        } else {
                            val cardLogoUrl = cardLogoUrls["${item.mediaType}_${item.id}"]
                            ArvioMediaCard(
                                item = item,
                                width = mobileItemWidth,
                                isLandscape = !usePosterCards,
                                logoImageUrl = cardLogoUrl,
                                showProgress = isContinueWatching,
                                isFocusedOverride = false,
                                enableSystemFocus = false,
                                onFocused = {},
                                onClick = { onItemClick(item) },
                                onLongClick = onItemLongClick?.let { callback -> { callback(item, isContinueWatching) } },
                            )
                        }
                    }
                }
            }
        }
    }
}

/** TV-optimized rows: D-pad controlled, viewport-constrained to bottom half of screen. */
@Composable
private fun TvHomeRowsLayer(
    categories: List<Category>,
    cardLogoUrls: Map<String, String>,
    focusState: HomeFocusState,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    fastScrollThresholdMs: Long,
    usePosterCards: Boolean,
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
            .padding(top = 24.dp)
    ) {
        val rowsViewportHeight = (maxHeight * 0.31f).coerceIn(260.dp, 340.dp)
        val listState = rememberLazyListState()
        val targetIndex = currentRowIndex.coerceIn(0, (categories.size - 1).coerceAtLeast(0))
        LaunchedEffect(targetIndex) {
            val currentIndex = listState.firstVisibleItemIndex
            if (currentIndex == targetIndex) return@LaunchedEffect

            val jumpDistance = kotlin.math.abs(targetIndex - currentIndex)
            if (jumpDistance <= 1) {
                listState.animateScrollToItem(index = targetIndex, scrollOffset = 0)
            } else {
                listState.scrollToItem(index = targetIndex, scrollOffset = 0)
            }
        }
        // Keep rows in the lower portion of the screen so hero metadata has dedicated space,
        // matching the separation used on Details.
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(rowsViewportHeight)
                .clipToBounds()
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = rowsViewportHeight),
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds()
                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen },
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
            itemsIndexed(
                items = categories,
                key = { _, category -> category.id },
                contentType = { _, _ -> "home_category_row" }
            ) { index, category ->
                    val targetAlpha = if (index <= currentRowIndex) 1f else 0.25f
                    val rowAlpha = androidx.compose.animation.core.animateFloatAsState(
                        targetValue = targetAlpha,
                        animationSpec = tween(durationMillis = 300),
                        label = "homeRowAlpha"
                    ).value
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clipToBounds()
                            .graphicsLayer { alpha = rowAlpha }
                    ) {
                        ContentRow(
                            category = category,
                            cardLogoUrls = cardLogoUrls,
                            isCurrentRow = !focusState.isSidebarFocused && index == focusState.currentRowIndex,
                            isRanked = category.title.contains("Top 10", ignoreCase = true),
                            usePosterCards = usePosterCards,
                            startPadding = contentStartPadding,
                            focusedItemIndex = if (!focusState.isSidebarFocused && index == focusState.currentRowIndex) focusState.currentItemIndex else -1,
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
    usePosterCards: Boolean = false,
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
    val itemWidth = if (usePosterCards) 105.dp else 210.dp
    val itemSpacing = 14.dp
    val availableWidthDp = configuration.screenWidthDp.dp - 56.dp - 12.dp
    val fallbackItemsPerPage = remember(configuration, density, itemWidth, itemSpacing) {
        val availablePx = with(density) { availableWidthDp.coerceAtLeast(1.dp).roundToPx() }
        val itemSpanPx = with(density) { (itemWidth + itemSpacing).roundToPx() }.coerceAtLeast(1)
        max(1, availablePx / itemSpanPx)
    }
    var baseVisibleCount by remember { mutableIntStateOf(0) }
    // Performance: Use derivedStateOf to avoid recomposition on every scroll frame
    val visibleCount by remember {
        derivedStateOf { rowState.layoutInfo.visibleItemsInfo.size }
    }
    LaunchedEffect(visibleCount) {
        if (visibleCount > 0 && baseVisibleCount == 0) {
            baseVisibleCount = visibleCount
        }
    }
    // Performance: Calculate itemsPerPage once based on fallback, avoid recalculating on scroll
    val itemsPerPage = remember(fallbackItemsPerPage, baseVisibleCount) {
        if (baseVisibleCount > 0) min(baseVisibleCount, fallbackItemsPerPage) else fallbackItemsPerPage
    }
    val rowFade = remember { Animatable(1f) }
    var lastPageIndex by remember { mutableIntStateOf(0) }
    val totalItems = category.items.size
    // Performance: Use derivedStateOf to avoid recalculation on every frame
    val effectiveVisibleCount by remember(totalItems, itemsPerPage) {
        derivedStateOf {
            val currentVisible = rowState.layoutInfo.visibleItemsInfo.size
            if (currentVisible > 0) min(currentVisible, totalItems.coerceAtLeast(1)) else itemsPerPage
        }
    }
    // Performance: Derive maxFirstIndex since effectiveVisibleCount is derived
    val maxFirstIndex by remember(totalItems) {
        derivedStateOf { (totalItems - effectiveVisibleCount).coerceAtLeast(0) }
    }
    val isScrollable = totalItems > itemsPerPage
    // Use rememberUpdatedState to ensure items recompose when focus changes
    val currentFocusedIndex by rememberUpdatedState(focusedItemIndex)
    val currentIsCurrentRow by rememberUpdatedState(isCurrentRow)
    // Performance: Remove maxFirstIndex from remember keys since it's derived
    val scrollTargetIndex by remember(focusedItemIndex, isCurrentRow, totalItems) {
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
    var lastScrollOffset by remember { mutableIntStateOf(-1) }
    LaunchedEffect(isCurrentRow) {
        if (!isCurrentRow) {
            lastScrollIndex = -1
            lastScrollOffset = -1
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
            rowState.scrollToItem(index = 0, scrollOffset = 0)
            lastScrollIndex = 0
            lastScrollOffset = 0
            return@LaunchedEffect
        }

        if (lastScrollIndex == scrollTargetIndex && lastScrollOffset == extraOffset) return@LaunchedEffect
        if (lastScrollIndex == -1) {
            // First time we jump directly to the correct position (no animation)
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
            lastScrollIndex = scrollTargetIndex
            lastScrollOffset = extraOffset
            return@LaunchedEffect
        }

        val currentFirstIndex = rowState.firstVisibleItemIndex
        val currentLastIndex = rowState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: currentFirstIndex
        val targetOutsideViewport = focusedItemIndex < currentFirstIndex || focusedItemIndex > currentLastIndex
        val jumpDistance = kotlin.math.abs(scrollTargetIndex - currentFirstIndex)
        if (isFastScrolling || extraOffset > 0 || jumpDistance > 1) {
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (scrollTargetIndex != currentFirstIndex || targetOutsideViewport) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else {
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        }
        lastScrollIndex = scrollTargetIndex
        lastScrollOffset = extraOffset
    }

    // Performance: Remove rowState from remember keys - derivedStateOf handles state tracking
    val pageIndex by remember(itemsPerPage) {
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
            rowFade.animateTo(1f, animationSpec = tween(durationMillis = 180))
        }
    }

    val rowAlpha = if (isCurrentRow) 1f else 0.95f
    val rowOffsetY = if (isCurrentRow) 0.dp else 2.dp

    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .offset(y = rowOffsetY)
            .graphicsLayer { alpha = rowAlpha }
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
                contentPadding = PaddingValues(
                    start = startPadding,
                    end = itemWidth + 30.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(itemSpacing)
            ) {
            itemsIndexed(
                category.items,
                key = { _, item ->
                    // Stable identity prevents unnecessary card disposal/recreation on progress/title updates.
                    // Include season/episode for CW items so two episodes of the same show don't collide.
                    val episodeSuffix = if (item.nextEpisode != null) "_S${item.nextEpisode.seasonNumber}E${item.nextEpisode.episodeNumber}" else ""
                    "${item.mediaType.name}-${item.id}${episodeSuffix}"
                },
                contentType = { _, item -> "${item.mediaType.name}_card" }
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
                                isLandscape = !usePosterCards,
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
                        isLandscape = !usePosterCards,
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
