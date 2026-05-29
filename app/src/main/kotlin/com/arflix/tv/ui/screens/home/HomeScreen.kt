@file:OptIn(androidx.tv.material3.ExperimentalTvMaterial3Api::class)

package com.arflix.tv.ui.screens.home

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import com.arflix.tv.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.drawWithCache
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
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
import androidx.compose.ui.unit.Dp
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
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CollectionTileShape
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.components.MediaCard as ArvioMediaCard
import com.arflix.tv.ui.components.TrailerPlayer
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.ui.components.MediaContextMenu
import com.arflix.tv.ui.components.rememberCardLayoutMode
import com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.focus.arvioManualBringIntoViewBoundary
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.focus.isArvioDpadNavigationKey
import com.arflix.tv.ui.focus.rememberArvioDpadRepeatGate
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape
import com.arflix.tv.ui.theme.AnimationConstants
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.ui.theme.AccentRed
import com.arflix.tv.ui.theme.PrimeBlue
import com.arflix.tv.ui.theme.PrimeGreen
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
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.res.stringResource



private object HomeRegexes {
    val HTML_TAG = Regex("<[^>]*>")
    val NON_BREAKING_SPACE = Regex("[\u00A0\u2007\u202F]")
    val UNICODE_SPACE = Regex("\\p{Z}+")
    val WHITESPACE = Regex("\\s+")
}

private fun cleanOverviewText(value: String): String {
    return value
        .replace(HomeRegexes.HTML_TAG, " ")
        .replace(HomeRegexes.NON_BREAKING_SPACE, " ")
        .replace(HomeRegexes.UNICODE_SPACE, " ")
        .replace(HomeRegexes.WHITESPACE, " ")
        .trim()
        .ifBlank { "No description available." }
}

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
    var userHasNavigated by mutableStateOf(false)
    // Per-row item indices — when pressing D-pad Down, we save the current item
    // index for the current row so pressing Up later returns to the same position.
    // Netflix preserves horizontal scroll position across rows; without this,
    // every Down press resets to item 0 which is jarring.
    val rowItemIndices = mutableMapOf<Int, Int>()

    companion object {
        // `userHasNavigated` is saved as the 4th element (0/1). Without it,
        // returning from a Collection/Details screen caused the "preferred
        // start row" reset to fire (since the field defaulted back to false),
        // which snapped the scroll position to Trending Movies — losing the
        // user's place in Franchises or wherever they were.
        val Saver: androidx.compose.runtime.saveable.Saver<HomeFocusState, List<Int>> =
            androidx.compose.runtime.saveable.Saver(
                save = {
                    listOf(
                        it.currentRowIndex,
                        it.currentItemIndex,
                        it.sidebarFocusIndex,
                        if (it.userHasNavigated) 1 else 0
                    )
                },
                restore = {
                    HomeFocusState(it[0], it[1], it[2]).apply {
                        userHasNavigated = (it.getOrNull(3) ?: 0) == 1
                    }
                }
            )
    }
}

@Composable
private fun localizedCategoryTitle(category: Category): String = when (category.id) {
    "continue_watching"        -> stringResource(R.string.continue_watching)
    "trending_movies"          -> stringResource(R.string.trending_movies)
    "trending_series"          -> stringResource(R.string.trending_series)
    "trending_tv"              -> stringResource(R.string.trending_in_shows)
    "trending_anime"           -> stringResource(R.string.trending_anime)
    "collection_row_service"   -> stringResource(R.string.services)
    "collection_row_genre"     -> stringResource(R.string.genres)
    "collection_row_decade"    -> stringResource(R.string.decades)
    "collection_row_franchise" -> stringResource(R.string.franchises)
    "collection_row_network"   -> stringResource(R.string.networks)
    "collection_row_featured"  -> stringResource(R.string.featured)
    else                       -> category.title
}

private fun deduplicateHomeCategories(categories: List<Category>): List<Category> {
    if (categories.size < 2) return categories
    val byId = LinkedHashMap<String, Category>(categories.size)
    categories.forEach { category ->
        val existing = byId[category.id]
        byId[category.id] = when {
            existing == null -> category
            category.id == "continue_watching" -> chooseContinueWatchingCategory(existing, category)
            existing.items.isEmpty() && category.items.isNotEmpty() -> category
            else -> existing
        }
    }
    return byId.values.toList()
}

private fun chooseContinueWatchingCategory(first: Category, second: Category): Category {
    val firstHasRealItems = first.items.any { !it.isPlaceholder }
    val secondHasRealItems = second.items.any { !it.isPlaceholder }
    return when {
        secondHasRealItems && !firstHasRealItems -> second
        firstHasRealItems && !secondHasRealItems -> first
        second.items.size > first.items.size -> second
        else -> first
    }
}

private fun getFocusedItem(categories: List<Category>, rowIndex: Int, itemIndex: Int): MediaItem? {
    val row = categories.getOrNull(rowIndex)
    return row?.items?.getOrNull(itemIndex)
        ?: row?.items?.firstOrNull()
        ?: categories.firstOrNull()?.items?.firstOrNull()
}

private fun homeRowItemKey(item: MediaItem): String {
    val episodeSuffix = item.nextEpisode?.let { "_S${it.seasonNumber}E${it.episodeNumber}" }.orEmpty()
    return "${item.mediaType.name}-${item.id}$episodeSuffix"
}

private data class HomeFocusedHeroSnapshot(
    val rowIndex: Int,
    val itemIndex: Int,
    val focusedItemKey: String,
    val heroItemKey: String
)

private fun preferredHomeStartRowIndex(categories: List<Category>): Int {
    val realContentIndex = categories.indexOfFirst { category ->
        !category.id.startsWith("collection_row_") && category.items.any { !it.isPlaceholder }
    }
    if (realContentIndex >= 0) return realContentIndex

    val nonCollectionIndex = categories.indexOfFirst { !it.id.startsWith("collection_row_") }
    if (nonCollectionIndex >= 0) return nonCollectionIndex

    return 0
}

private fun isActionableHomeItem(item: MediaItem?): Boolean {
    return item != null && item.id > 0 && !item.isPlaceholder
}

private data class HomeHeroPlaybackHandles(
    val player: ExoPlayer,
    val hlsFactory: HlsMediaSource.Factory
)

private fun createHomeHeroPlaybackHandles(context: Context): HomeHeroPlaybackHandles {
    val heroOkHttp = OkHttpClient.Builder()
        .connectionPool(ConnectionPool(2, 2, TimeUnit.MINUTES))
        .followRedirects(true)
        .followSslRedirects(true)
        .retryOnConnectionFailure(true)
        .dns(OkHttpProvider.dns)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()
    val heroDataSourceFactory =
        OkHttpDataSource.Factory(heroOkHttp).setUserAgent("ARVIO/1.7.0 (Android TV)")
    val heroHlsFactory = HlsMediaSource.Factory(heroDataSourceFactory)
        .setAllowChunklessPreparation(true)
    val heroDefaultFactory = DefaultMediaSourceFactory(context)
        .setDataSourceFactory(heroDataSourceFactory)
    val loadControl = DefaultLoadControl.Builder()
        .setBufferDurationsMs(2_000, 8_000, 750, 1_500)
        .setTargetBufferBytes(12 * 1024 * 1024)
        .setPrioritizeTimeOverSizeThresholds(true)
        .setBackBuffer(0, false)
        .build()
    val player = ExoPlayer.Builder(context)
        .setMediaSourceFactory(heroDefaultFactory)
        .setLoadControl(loadControl)
        .build()
        .apply {
            playWhenReady = false
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            volume = 1f
        }
    return HomeHeroPlaybackHandles(
        player = player,
        hlsFactory = heroHlsFactory
    )
}

private suspend fun androidx.compose.foundation.lazy.LazyListState.animateHomeScrollDelta(
    deltaPx: Float,
    durationMillis: Int
) {
    if (abs(deltaPx) <= 1f) return
    scroll(scrollPriority = MutatePriority.PreventUserInput) {
        var previousValue = 0f
        animate(
            initialValue = 0f,
            targetValue = deltaPx,
            animationSpec = spring(
                dampingRatio = 0.85f,
                stiffness = 200f
            )
        ) { value, _ ->
            val step = value - previousValue
            if (abs(step) > 0.01f) {
                scrollBy(step)
            }
            previousValue = value
        }
    }
}

@Composable
private fun rememberMetadataLogoImageLoader(context: Context): ImageLoader {
    return remember(context) {
        ImageLoader.Builder(context)
            .okHttpClient(OkHttpProvider.coilClient)
            .components {
                add(SvgDecoder.Factory())
            }
            .allowRgb565(false)
            .crossfade(false)
            .placeholder(android.R.color.transparent)
            .error(android.R.color.transparent)
            .build()
    }
}

@Composable
private fun HomeBackdropCrossfade(
    backdropUrl: String?,
    backdropSize: Pair<Int, Int>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var displayedBackdropUrl by remember { mutableStateOf<String?>(null) }
    var pendingBackdropUrl by remember { mutableStateOf<String?>(null) }
    var pendingBackdropReady by remember { mutableStateOf(false) }
    val pendingAlpha = remember { Animatable(0f) }
    val (backdropWidthPx, backdropHeightPx) = backdropSize

    LaunchedEffect(backdropUrl) {
        when {
            backdropUrl.isNullOrBlank() -> {
                displayedBackdropUrl = null
                pendingBackdropUrl = null
                pendingBackdropReady = false
                pendingAlpha.snapTo(0f)
            }

            displayedBackdropUrl == null -> {
                displayedBackdropUrl = backdropUrl
                pendingBackdropUrl = null
                pendingBackdropReady = false
                pendingAlpha.snapTo(0f)
            }

            displayedBackdropUrl == backdropUrl -> {
                pendingBackdropUrl = null
                pendingBackdropReady = false
                pendingAlpha.snapTo(0f)
            }

            else -> {
                pendingBackdropUrl = backdropUrl
                pendingBackdropReady = false
                pendingAlpha.snapTo(0f)
            }
        }
    }

    LaunchedEffect(pendingBackdropUrl, pendingBackdropReady) {
        val target = pendingBackdropUrl ?: return@LaunchedEffect
        if (!pendingBackdropReady) return@LaunchedEffect
        pendingAlpha.snapTo(0f)
        pendingAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 420)
        )
        displayedBackdropUrl = target
        pendingBackdropUrl = null
        pendingBackdropReady = false
        pendingAlpha.snapTo(0f)
    }

    fun buildBackdropRequest(url: String): ImageRequest =
        "$url|${backdropWidthPx}x$backdropHeightPx".let { cacheKey ->
        ImageRequest.Builder(context)
            .data(url)
            .size(backdropWidthPx, backdropHeightPx)
            .precision(Precision.INEXACT)
            .allowHardware(true)
            .memoryCacheKey(cacheKey)
            .placeholderMemoryCacheKey(cacheKey)
            .crossfade(false)
            .build()
        }

    Box(modifier = modifier) {
        displayedBackdropUrl?.let { stableBackdropUrl ->
            val request = remember(stableBackdropUrl, backdropWidthPx, backdropHeightPx) {
                buildBackdropRequest(stableBackdropUrl)
            }
            AsyncImage(
                model = request,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        pendingBackdropUrl?.let { nextBackdropUrl ->
            val request = remember(nextBackdropUrl, backdropWidthPx, backdropHeightPx) {
                buildBackdropRequest(nextBackdropUrl)
            }
            AsyncImage(
                model = request,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                onSuccess = { pendingBackdropReady = true },
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = pendingAlpha.value }
            )
        }
    }
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
    onNavigateToCollection: (String) -> Unit = {},
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
    // Lifecycle-aware: stops collecting when HomeScreen is off-screen so the
    // ViewModel's TMDB/Trakt refresh pushes don't drive recompositions behind
    // an invisible UI.
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    // Per-card logo reads now come from a stable snapshotStateMap so a single
    // logo arriving no longer recomposes the full home surface.
    val cardLogoUrls = viewModel.cardLogoUrls
    val profileCount = if (currentProfile != null) 1 else 0
    val usePosterCards = rememberCardLayoutMode() == CardLayoutMode.POSTER
    val lifecycleOwner = LocalLifecycleOwner.current
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        // Prevent stale select key events from previous screen from reopening details.
        suppressSelectUntilMs = SystemClock.elapsedRealtime() + 150L
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refreshContinueWatchingOnly(force = true)
                // Pull the full cloud state (addons, catalogs, settings) on resume.
                // This catches any changes pushed by another device while this one
                // was backgrounded — the WebSocket may have been killed by Android,
                // so we can't rely on realtime alone. Throttled internally to avoid
                // redundant pulls on rapid activity transitions.
                viewModel.pullCloudStateOnResume()
                suppressSelectUntilMs = SystemClock.elapsedRealtime() + 150L
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val rawDisplayCategories = if (uiState.categories.isNotEmpty()) {
        uiState.categories
    } else {
        preloadedCategories
    }
    val displayCategories = remember(rawDisplayCategories) {
        deduplicateHomeCategories(rawDisplayCategories)
    }
    val displayHeroItem = uiState.heroItem ?: preloadedHeroItem
        ?: if (uiState.categories.isEmpty()) {
            // Only fall through to first-row hero while the ViewModel is still
            // publishing its initial categories. Once categories are populated,
            // the hero-update LaunchedEffect drives heroItem from focused cards.
            displayCategories.firstOrNull()?.items?.firstOrNull()
        } else {
            null
        }
    val displayHeroLogo = uiState.heroLogoUrl ?: preloadedHeroLogoUrl
    val displayHeroOverview = uiState.heroOverviewOverride
    val latestDisplayCategories by rememberUpdatedState(displayCategories)
    val latestDisplayHeroItem by rememberUpdatedState(displayHeroItem)

    val context = LocalContext.current
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val backdropSize = remember(configuration, density) {
        val widthPx = with(density) { configuration.screenWidthDp.dp.roundToPx() }
        val heightPx = with(density) { configuration.screenHeightDp.dp.roundToPx() }
        widthPx.coerceAtLeast(1) to heightPx.coerceAtLeast(1)
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
    val contentStartPadding = if (isMobile) 16.dp else 36.dp

    // Use rememberSaveable to persist focus position across navigation (back from details page)
    val focusState = rememberSaveable(saver = HomeFocusState.Saver) { HomeFocusState() }
    val fastScrollThresholdMs = 650L
    val heroVideoIdleThresholdMs = 6_000L
    val startupEffectsDelayMs = if (isMobile) 0L else 900L
    var startupEffectsSettled by remember { mutableStateOf(isMobile) }
    var suppressHeroVideoPlayback by remember { mutableStateOf(false) }

    LaunchedEffect(isMobile) {
        if (isMobile) {
            startupEffectsSettled = true
            return@LaunchedEffect
        }
        startupEffectsSettled = false
        delay(startupEffectsDelayMs)
        startupEffectsSettled = true
    }
    val allowHomeBackgroundWork = startupEffectsSettled || focusState.userHasNavigated
    val showCinematicHomeLayer = isMobile || allowHomeBackgroundWork
    val limitRowsDuringStartup = !isMobile && !allowHomeBackgroundWork && !focusState.userHasNavigated

    LaunchedEffect(isMobile, focusState) {
        if (isMobile) {
            suppressHeroVideoPlayback = false
            return@LaunchedEffect
        }
        snapshotFlow { focusState.lastNavEventTime to focusState.isSidebarFocused }
            .distinctUntilChanged()
            .collectLatest { (anchor, sidebarFocused) ->
                if (sidebarFocused) {
                    suppressHeroVideoPlayback = true
                    return@collectLatest
                }
                if (anchor <= 0L) {
                    suppressHeroVideoPlayback = false
                    return@collectLatest
                }
                suppressHeroVideoPlayback = true
                delay(heroVideoIdleThresholdMs)
                if (focusState.lastNavEventTime == anchor && !focusState.isSidebarFocused) {
                    suppressHeroVideoPlayback = false
                }
            }
    }

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

    // Preload artwork for the focused row and the next rows soon after DPAD settles.
    LaunchedEffect(allowHomeBackgroundWork) {
        if (!allowHomeBackgroundWork) return@LaunchedEffect
        val rowPreloadIdleMs = 320L
        snapshotFlow {
            Triple(
                focusState.currentRowIndex,
                focusState.currentItemIndex,
                focusState.lastNavEventTime
            )
        }
            .distinctUntilChanged()
            .collectLatest { (rowIndex, itemIndex, navEventTime) ->
                val idleForMs = if (navEventTime > 0L) {
                    SystemClock.elapsedRealtime() - navEventTime
                } else {
                    rowPreloadIdleMs
                }
                if (idleForMs < rowPreloadIdleMs) {
                    delay(rowPreloadIdleMs - idleForMs)
                }
                if (focusState.currentRowIndex != rowIndex) return@collectLatest
                if (focusState.currentItemIndex != itemIndex) return@collectLatest
                viewModel.onFocusChanged(rowIndex, itemIndex, shouldPrefetch = true)
                viewModel.preloadLogosForCategory(rowIndex, prioritizeVisible = true)
                viewModel.preloadLogosForCategory(rowIndex + 1, prioritizeVisible = false)
                viewModel.preloadLogosForCategory(rowIndex + 2, prioritizeVisible = false)
            }
    }

    // Update hero based on focused item with adaptive idle delay to avoid heavy churn while scrolling
    LaunchedEffect(allowHomeBackgroundWork) {
        if (!allowHomeBackgroundWork) return@LaunchedEffect
        snapshotFlow {
            val focusedItem = latestDisplayCategories
                .getOrNull(focusState.currentRowIndex)
                ?.items
                ?.getOrNull(focusState.currentItemIndex)
            HomeFocusedHeroSnapshot(
                rowIndex = focusState.currentRowIndex,
                itemIndex = focusState.currentItemIndex,
                // Include the exact focused item key so async row updates, especially
                // Continue Watching reloads, cannot leave the hero bound to an old
                // first-row fallback while the visual focus is on another card.
                focusedItemKey = focusedItem?.let { homeRowItemKey(it) }.orEmpty(),
                // Also include the current hero key. Background home/CW refreshes can
                // republish the initial row-0 hero without changing focus indices; this
                // forces the watcher to restore the actually focused card.
                heroItemKey = latestDisplayHeroItem?.let { homeRowItemKey(it) }.orEmpty()
            )
        }
            .distinctUntilChanged()
            .collectLatest { focusSnapshot ->
                val categoriesSnapshot = latestDisplayCategories
                if (categoriesSnapshot.isEmpty() || focusState.isSidebarFocused) return@collectLatest
                if (focusSnapshot.focusedItemKey.isBlank()) return@collectLatest
                if (focusSnapshot.focusedItemKey == focusSnapshot.heroItemKey) return@collectLatest
                categoriesSnapshot.getOrNull(focusSnapshot.rowIndex)
                    ?.items
                    ?.getOrNull(focusSnapshot.itemIndex)
                    ?: return@collectLatest

                val now = SystemClock.elapsedRealtime()
                val isFastScrolling = now - focusState.lastNavEventTime < fastScrollThresholdMs
                if (isFastScrolling) {
                    delay(360L)
                    if (
                        focusState.currentRowIndex != focusSnapshot.rowIndex ||
                        focusState.currentItemIndex != focusSnapshot.itemIndex ||
                        focusState.isSidebarFocused
                    ) {
                        return@collectLatest
                    }
                }
                val latestFocusedItem = latestDisplayCategories
                    .getOrNull(focusSnapshot.rowIndex)
                    ?.items
                    ?.getOrNull(focusSnapshot.itemIndex)
                    ?: return@collectLatest
                if (homeRowItemKey(latestFocusedItem) != focusSnapshot.focusedItemKey) {
                    return@collectLatest
                }
                viewModel.onFocusChanged(focusSnapshot.rowIndex, focusSnapshot.itemIndex, shouldPrefetch = true)
                viewModel.updateHeroItem(latestFocusedItem)
            }
    }

    // Infinite row pagination: keep initial Home fast, then append as user reaches row end.
    LaunchedEffect(allowHomeBackgroundWork) {
        if (!allowHomeBackgroundWork) return@LaunchedEffect
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

    // ── IPTV + service-collection hero player state ──
    val isHeroIptv = displayHeroItem != null && viewModel.isIptvItem(displayHeroItem)
    val isHeroCollection = displayHeroItem != null && viewModel.isCollectionItem(displayHeroItem)
    // Track service-collection "played once" — after the video ends we stop
    // re-spawning the player until the user focuses a *different* service.
    // Keyed on the focused collection id so re-entering the card after
    // moving elsewhere replays it.
    var collectionVideoFinishedId by remember { mutableStateOf<Int?>(null) }
    val heroVideoAllowed = true
    val serviceHeroVideoUrl = displayHeroItem
        ?.takeIf { heroVideoAllowed && isHeroCollection && collectionVideoFinishedId != it.id }
        ?.let { viewModel.getCollectionHeroVideoUrl(it) }
    val heroVideoUrl = when {
        !isMobile && !focusState.userHasNavigated -> null
        !heroVideoAllowed -> null
        // Service collection MP4s should start as soon as the card becomes the hero.
        // Keep the idle gate for heavier IPTV/live playback, but do not delay MP4 previews.
        serviceHeroVideoUrl != null -> serviceHeroVideoUrl
        suppressHeroVideoPlayback -> null
        isHeroIptv -> displayHeroItem?.let { viewModel.getIptvStreamUrl(it.id) }
        else -> null
    }

    var isTrailerPlaying by remember { mutableStateOf(false) }
    var trailerSuppressed by remember { mutableStateOf(false) }
    LaunchedEffect(displayHeroItem?.id) { trailerSuppressed = false }
    val heroRowIsContinueWatching = latestDisplayCategories
        .getOrNull(focusState.currentRowIndex)?.id == "continue_watching"
    val trailerOverlayAlpha = remember { Animatable(1f) }
    LaunchedEffect(isTrailerPlaying) {
        if (isTrailerPlaying) {
            trailerOverlayAlpha.animateTo(0f, tween(1500, easing = FastOutSlowInEasing))
        } else {
            trailerOverlayAlpha.animateTo(1f, tween(500, easing = FastOutSlowInEasing))
        }
    }

    var heroPlaybackHandles by remember { mutableStateOf<HomeHeroPlaybackHandles?>(null) }
    var preparedHeroVideoUrl by remember { mutableStateOf<String?>(null) }
    val heroExoPlayer = heroPlaybackHandles?.player
    DisposableEffect(Unit) {
        onDispose {
            heroPlaybackHandles?.player?.release()
            heroPlaybackHandles = null
            preparedHeroVideoUrl = null
        }
    }

    // Service-collection video lifecycle: play once on focus, with sound,
    // then mark the card "played" so subsequent focus returns fall back to
    // the stock image. IPTV live streams bypass this (they loop naturally).
    val heroVideoFadeDurationMs = if (isMobile) 420 else 0
    val focusedCollectionId = displayHeroItem?.id?.takeIf { isHeroCollection }
    val latestFocusedCollectionId by rememberUpdatedState(focusedCollectionId)
    val heroVideoAlpha by animateFloatAsState(
        targetValue = if (heroVideoUrl != null) 1f else 0f,
        animationSpec = tween(durationMillis = heroVideoFadeDurationMs),
        label = "home-hero-video-alpha"
    )
    DisposableEffect(heroExoPlayer) {
        val player = heroExoPlayer ?: return@DisposableEffect onDispose { }
        val listener = object : androidx.media3.common.Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == androidx.media3.common.Player.STATE_ENDED) {
                    latestFocusedCollectionId?.let { collectionVideoFinishedId = it }
                }
            }
        }
        player.addListener(listener)
        onDispose { player.removeListener(listener) }
    }

    LaunchedEffect(heroVideoUrl) {
        if (heroVideoUrl != null && heroPlaybackHandles == null) {
            heroPlaybackHandles = createHomeHeroPlaybackHandles(context)
        }
        val player = heroPlaybackHandles?.player
        if (heroVideoUrl != null) {
            val handles = heroPlaybackHandles ?: return@LaunchedEffect
            if (preparedHeroVideoUrl != heroVideoUrl) {
                player?.stop()
                player?.clearMediaItems()
                val mi = androidx.media3.common.MediaItem.Builder()
                    .setUri(heroVideoUrl)
                    .setLiveConfiguration(
                        androidx.media3.common.MediaItem.LiveConfiguration.Builder()
                            .setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f)
                            .setTargetOffsetMs(4_000).build()
                    ).build()
                val lower = heroVideoUrl.lowercase()
                if (lower.contains(".m3u8") || lower.contains("/hls") || lower.contains("format=hls")) {
                    player?.setMediaSource(handles.hlsFactory.createMediaSource(mi))
                } else {
                    player?.setMediaItem(mi)
                }
                player?.prepare()
                preparedHeroVideoUrl = heroVideoUrl
            }
            // Service videos play once with sound and stop; IPTV live streams
            // naturally don't loop (they're live) so REPEAT_MODE_OFF is safe
            // for both paths.
            player?.repeatMode = androidx.media3.common.Player.REPEAT_MODE_OFF
            player?.volume = 1f
            player?.playWhenReady = true
        } else {
            player?.playWhenReady = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
      ) {
        val currentBackdrop = displayHeroItem?.let { item ->
            if (viewModel.isCollectionItem(item)) {
                viewModel.getCollectionHeroImageUrl(item) ?: item.image
            } else {
                item.backdrop ?: item.image
            }
        }
        var settledBackdrop by remember { mutableStateOf<String?>(null) }
        val latestCurrentBackdrop by rememberUpdatedState(currentBackdrop)
        LaunchedEffect(currentBackdrop, isMobile) {
            if (isMobile) {
                settledBackdrop = currentBackdrop
                return@LaunchedEffect
            }
            if (currentBackdrop.isNullOrBlank() || settledBackdrop == null) {
                settledBackdrop = currentBackdrop
                return@LaunchedEffect
            }
            if (currentBackdrop == settledBackdrop) return@LaunchedEffect

            val elapsedSinceNav = SystemClock.elapsedRealtime() - focusState.lastNavEventTime
            val settleDelayMs = (420L - elapsedSinceNav).coerceAtLeast(0L)
            if (settleDelayMs > 0L) {
                delay(settleDelayMs)
            }
            if (latestCurrentBackdrop == currentBackdrop) {
                settledBackdrop = currentBackdrop
            }
        }
        // On mobile, the hero backdrop is rendered inline inside MobileHomeRowsLayer — skip the fixed backdrop.
        // On TV, fill the entire screen with the backdrop.
        if (!isMobile) {
            val backdropModifier = Modifier.fillMaxSize()
            Box(modifier = backdropModifier) {
                if (!showCinematicHomeLayer || settledBackdrop == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = backdropGradient
                            )
                    )
                }

                if (showCinematicHomeLayer && settledBackdrop != null) {
                    HomeBackdropCrossfade(
                        backdropUrl = settledBackdrop,
                        backdropSize = backdropSize,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                if (heroExoPlayer != null && (heroVideoUrl != null || heroVideoAlpha > 0.01f)) {
                    AndroidView(
                        factory = { ctx ->
                            PlayerView(ctx).apply {
                                useController = false
                                setControllerAutoShow(false)
                                hideController()
                                isFocusable = false
                                isFocusableInTouchMode = false
                                descendantFocusability = android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS
                                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                                setShutterBackgroundColor(android.graphics.Color.TRANSPARENT)
                                setKeepContentOnPlayerReset(true)
                                player = heroExoPlayer
                            }
                        },
                        update = { pv ->
                            pv.useController = false
                            pv.setControllerAutoShow(false)
                            pv.hideController()
                            pv.player = heroExoPlayer
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer { alpha = heroVideoAlpha }
                    )
                }

                // YouTube trailer auto-play (sound controlled by trailerSoundEnabled setting)
                if (heroVideoUrl == null && uiState.trailerAutoPlay && uiState.heroTrailerKey != null && !trailerSuppressed && !heroRowIsContinueWatching) {
                    TrailerPlayer(
                        youtubeKey = uiState.heroTrailerKey!!,
                        delayMs = uiState.trailerDelaySeconds * 1000L,
                        volume = if (uiState.trailerSoundEnabled) 1f else 0f,
                        onPlayingChanged = { playing -> isTrailerPlaying = playing },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // === SCRIM SYSTEM ===
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithCache {
                            val width = size.width
                            val height = size.height
                            val leftScrim = Brush.horizontalGradient(
                                colorStops = arrayOf(
                                    0.0f to Color.Black.copy(alpha = 0.95f),
                                    0.12f to Color.Black.copy(alpha = 0.88f),
                                    0.22f to Color.Black.copy(alpha = 0.72f),
                                    0.32f to Color.Black.copy(alpha = 0.50f),
                                    0.42f to Color.Black.copy(alpha = 0.30f),
                                    0.55f to Color.Black.copy(alpha = 0.10f),
                                    0.65f to Color.Transparent,
                                    1.0f to Color.Transparent
                                ),
                                startX = 0f,
                                endX = width
                            )
                            val topScrim = Brush.verticalGradient(
                                colorStops = arrayOf(
                                    0.0f to Color.Black.copy(alpha = 0.7f),
                                    0.06f to Color.Black.copy(alpha = 0.45f),
                                    0.15f to Color.Black.copy(alpha = 0.15f),
                                    0.25f to Color.Transparent,
                                    1.0f to Color.Transparent
                                ),
                                startY = 0f,
                                endY = height
                            )
                            val bottomScrim = Brush.verticalGradient(
                                colorStops = arrayOf(
                                    0.0f to Color.Transparent,
                                    0.85f to Color.Transparent,
                                    0.92f to Color.Black.copy(alpha = 0.5f),
                                    1.0f to Color.Black.copy(alpha = 0.85f)
                                ),
                                startY = 0f,
                                endY = height
                            )
                            onDrawBehind {
                                drawRect(
                                    brush = leftScrim,
                                    size = Size(width * 0.66f, height)
                                )
                                drawRect(
                                    brush = topScrim,
                                    size = Size(width, height * 0.26f)
                                )
                                drawRect(
                                    brush = bottomScrim,
                                    topLeft = Offset(0f, height * 0.84f),
                                    size = Size(width, height * 0.16f)
                                )
                            }
                        }
                )
            }
        } // end if (!isMobile) backdrop
        
        Box(modifier = Modifier.fillMaxSize().graphicsLayer { alpha = trailerOverlayAlpha.value }) {
        HomeInputLayer(
            categories = displayCategories,
            cardLogoUrls = cardLogoUrls,
            focusState = focusState,
            limitRowsDuringStartup = limitRowsDuringStartup,
            suppressSelectUntilMs = suppressSelectUntilMs,
            contentStartPadding = contentStartPadding,
            fastScrollThresholdMs = fastScrollThresholdMs,
            usePosterCards = usePosterCards,
            isContextMenuOpen = showContextMenu,
            trailerIsPlaying = isTrailerPlaying,
            onTrailerStop = { trailerSuppressed = true },
            isMobile = isMobile,
            heroItem = displayHeroItem,
            heroOverviewOverride = displayHeroOverview,
            onPlay = {
                displayHeroItem?.let { item ->
                    if (viewModel.isIptvItem(item)) {
                        onNavigateToTv(viewModel.getIptvChannelId(item), viewModel.getIptvStreamUrl(item.id))
                    } else if (viewModel.isCollectionItem(item)) {
                        onNavigateToCollection(item.status?.removePrefix("collection:").orEmpty())
                    } else {
                        onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                    }
                }
            },
            onDetails = {
                displayHeroItem?.let { item ->
                    if (viewModel.isIptvItem(item)) {
                        onNavigateToTv(viewModel.getIptvChannelId(item), viewModel.getIptvStreamUrl(item.id))
                    } else if (viewModel.isCollectionItem(item)) {
                        onNavigateToCollection(item.status?.removePrefix("collection:").orEmpty())
                    } else {
                        onNavigateToDetails(item.mediaType, item.id, null, null)
                    }
                }
            },
            currentProfile = currentProfile,
            profileCount = profileCount,
            clockFormat = uiState.clockFormat,
            syncStatus = uiState.syncStatus,
            hasUpdateBadge = uiState.hasUpdateBadge,
            categoryHasMoreMap = uiState.categoryHasMoreMap,
            smoothScrolling = uiState.smoothScrolling,
            onLoadMoreCategory = { viewModel.loadNextPageForCategory(it) },
            onItemFocusedPrefetch = {},
            onMobileCategoryVisiblePosition = { categoryId, lastVisibleItemIndex ->
                viewModel.onMobileCategoryVisiblePosition(categoryId, lastVisibleItemIndex)
            },
            onNavigateToDetails = onNavigateToDetails,
            onNavigateToCollection = onNavigateToCollection,
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
        } // end trailer-dim wrapper

        if (showCinematicHomeLayer) {
            Box(modifier = Modifier.fillMaxSize().graphicsLayer { alpha = trailerOverlayAlpha.value }) {
            HomeHeroLayer(
                heroItem = displayHeroItem,
                heroLogoUrl = displayHeroLogo,
                heroOverviewOverride = displayHeroOverview,
                contentStartPadding = contentStartPadding,
                isMobile = isMobile,
                showBudget = uiState.showBudget,
                onNavigateToDetails = onNavigateToDetails,
                onNavigateToTv = { channelId, streamUrl -> onNavigateToTv(channelId, streamUrl) },
                isIptvItem = { item -> viewModel.isIptvItem(item) },
                getIptvChannelId = { item -> viewModel.getIptvChannelId(item) },
                getIptvStreamUrl = { itemId -> viewModel.getIptvStreamUrl(itemId) }
            )
            } // end trailer-dim wrapper
        }

        // Error state - show message when loading failed and no content
        if (!uiState.isLoading && displayCategories.isEmpty() && uiState.error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appBackgroundDark()),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_results),
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
                        Text(stringResource(R.string.retry))
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

        // App Update Modal
        if (uiState.showAppUpdateDialog) {
            com.arflix.tv.ui.components.AppUpdateModal(
                status = uiState.updateStatus,
                onDownload = { viewModel.downloadAppUpdate() },
                onCancelDownload = { viewModel.cancelDownloadAppUpdate() },
                onInstall = { viewModel.installAppUpdateOrRequestPermission() },
                onDismiss = { viewModel.dismissAppUpdateDialog() },
                onIgnore = { viewModel.ignoreAppUpdate() }
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
    // Hide the Budget line on the hero metadata row when false. Plumbed from
    // HomeUiState.showBudget, which is loaded from the per-profile
    // `show_budget_on_home` DataStore key and defaults to true so existing
    // users see no behavior change. Issue #72.
    showBudget: Boolean = true,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val metadataLogoImageLoader = rememberMetadataLogoImageLoader(context)
    val density = LocalDensity.current
    val logoSize = remember(density) {
        val widthPx = with(density) { 320.dp.roundToPx() }
        val heightPx = with(density) { 72.dp.roundToPx() }
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
    val heroTextWidth = 360.dp

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
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.height(72.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (currentLogoUrl != null) {
                        val (logoWidthPx, logoHeightPx) = logoSize
                        val request = remember(currentLogoUrl, logoWidthPx, logoHeightPx) {
                            val cacheKey = "$currentLogoUrl|${logoWidthPx}x$logoHeightPx"
                            ImageRequest.Builder(context)
                                .data(currentLogoUrl)
                                .size(logoWidthPx, logoHeightPx)
                                .precision(Precision.INEXACT)
                                .allowHardware(true)
                                .memoryCacheKey(cacheKey)
                                .placeholderMemoryCacheKey(cacheKey)
                                .crossfade(false)
                                .build()
                        }
                        AsyncImage(
                            model = request,
                            contentDescription = currentItem.title,
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.CenterStart,
                            modifier = Modifier
                                .height(72.dp)
                                .width(320.dp)
                        )
                    } else {
                        // Fallback to title text
                        Text(
                            text = currentItem.title.uppercase(),
                            style = ArflixTypography.heroTitle.copy(
                                fontSize = 40.sp,
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
                            text = stringResource(R.string.in_cinema),
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

                Spacer(modifier = Modifier.height(4.dp))

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
                                text = stringResource(R.string.live).uppercase(),
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
                    val primaryNetworkLogo = currentItem.primaryNetworkLogo?.takeIf { it.isNotBlank() }
                    val budgetText = remember(currentItem.mediaType, currentItem.budget) {
                        val budgetValue = currentItem.budget
                        if (currentItem.mediaType == MediaType.MOVIE && budgetValue != null && budgetValue > 0L) {
                            formatBudgetCompact(budgetValue)
                        } else {
                            null
                        }
                    }
                    val rating = imdbRatingFor(currentItem)
                    val ratingValue = parseRatingValue(rating)
                    val hasRatingMetadata = ratingValue > 0f
                    val hasBudgetMetadata = showBudget && !budgetText.isNullOrBlank()
                    val hasSecondaryMetadata = primaryNetworkLogo != null ||
                        hasRatingMetadata ||
                        hasBudgetMetadata

                    Column(
                        modifier = Modifier.width(heroTextWidth),
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (displayDate.isNotEmpty()) {
                                Text(
                                    text = displayDate,
                                    style = ArflixTypography.caption.copy(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        shadow = textShadow
                                    ),
                                    color = Color.White,
                                    maxLines = 1
                                )

                                if (hasGenre || hasDuration) {
                                    Text(
                                        text = "|",
                                        style = ArflixTypography.caption.copy(
                                            fontSize = 13.sp,
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
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        shadow = textShadow
                                    ),
                                    color = Color.White,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.weight(1f, fill = false)
                                )
                            }

                            if (hasDuration) {
                                if (hasGenre) {
                                    Text(
                                        text = "|",
                                        style = ArflixTypography.caption.copy(
                                            fontSize = 13.sp,
                                            shadow = textShadow
                                        ),
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                }
                                Text(
                                    text = currentItem.duration,
                                    style = ArflixTypography.caption.copy(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        shadow = textShadow
                                    ),
                                    color = Color.White,
                                    maxLines = 1
                                )
                            }
                        }

                        if (hasSecondaryMetadata) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (primaryNetworkLogo != null) {
                                    AsyncImage(
                                        model = primaryNetworkLogo,
                                        imageLoader = metadataLogoImageLoader,
                                        contentDescription = "Primary streaming provider",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .height(16.dp)
                                            .width(58.dp)
                                    )

                                    if (hasRatingMetadata || hasBudgetMetadata) {
                                        Text(
                                            text = "|",
                                            style = ArflixTypography.caption.copy(
                                                fontSize = 12.sp,
                                                shadow = textShadow
                                            ),
                                            color = Color.White.copy(alpha = 0.58f)
                                        )
                                    }
                                }

                                if (hasRatingMetadata) {
                                    ImdbSvgRatingBadge(
                                        rating = rating,
                                        imageLoader = metadataLogoImageLoader,
                                        ratingFontSize = 13,
                                        logoWidth = 36.dp,
                                        logoHeight = 15.dp,
                                        textShadow = textShadow
                                    )

                                    if (hasBudgetMetadata) {
                                        Text(
                                            text = "|",
                                            style = ArflixTypography.caption.copy(
                                                fontSize = 12.sp,
                                                shadow = textShadow
                                            ),
                                            color = Color.White.copy(alpha = 0.58f)
                                        )
                                    }
                                }

                                if (hasBudgetMetadata) {
                                    Text(
                                        text = "Budget $budgetText",
                                        style = ArflixTypography.caption.copy(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            shadow = textShadow
                                        ),
                                        color = Color.White.copy(alpha = 0.74f),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f, fill = false)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Overview text (EPG data for IPTV, synopsis for movies/shows)
                val displayOverview = remember(overviewOverride, currentItem.overview) {
                    cleanOverviewText(overviewOverride ?: currentItem.overview)
                }

                val overviewMaxHeight = 72.dp
                Box(
                    modifier = Modifier
                        .width(360.dp)
                        .height(overviewMaxHeight)
                ) {
                    Text(
                        text = displayOverview,
                        style = ArflixTypography.body.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 16.sp,
                            shadow = textShadow
                        ),
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 4,
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

private fun imdbRatingFor(item: MediaItem): String {
    val imdbValue = parseRatingValue(item.imdbRating)
    return if (imdbValue > 0f) item.imdbRating else ""
}

@Composable
private fun TopRankRibbon(
    rank: Int,
    isFocused: Boolean,
    compact: Boolean,
    modifier: Modifier = Modifier
) {
    val clamped = rank.coerceIn(1, 10)
    val resId = when (clamped) {
        1 -> R.drawable.rank_banner_01
        2 -> R.drawable.rank_banner_02
        3 -> R.drawable.rank_banner_03
        4 -> R.drawable.rank_banner_04
        5 -> R.drawable.rank_banner_05
        6 -> R.drawable.rank_banner_06
        7 -> R.drawable.rank_banner_07
        8 -> R.drawable.rank_banner_08
        9 -> R.drawable.rank_banner_09
        else -> R.drawable.rank_banner_10
    }
    val width = if (compact) 30.dp else 38.dp
    val context = LocalContext.current
    val density = LocalDensity.current
    // Decode only the pixels we'll actually draw — the source PNGs are 3334×3334 but
    // the ribbon is displayed at 30–38dp. Full-size decode was ~44 MB per card × 10 cards.
    val targetPx = remember(compact, density) {
        with(density) { (if (compact) 60.dp else 76.dp).roundToPx() }
    }

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(resId)
            .size(targetPx, targetPx)
            .allowHardware(true)
            .build(),
        contentDescription = "Rank #$clamped",
        contentScale = ContentScale.Fit,
        modifier = modifier
            .width(width)
            .alpha(if (isFocused) 1f else 0.97f)
    )
}

@Composable
private fun HomeHeroLayer(
    heroItem: MediaItem?,
    heroLogoUrl: String?,
    heroOverviewOverride: String?,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    isMobile: Boolean = false,
    showBudget: Boolean = true,
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
        val heroBottomPadding = buttonsBottomPadding + if (configuration.screenHeightDp < 720) 34.dp else 34.dp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = AppTopBarContentTopInset)
                .zIndex(3f)
        ) {
            heroItem?.let { item ->
                if (!item.status.orEmpty().startsWith("collection:")) {
                    HeroSection(
                        item = item,
                        logoUrl = heroLogoUrl,
                        overviewOverride = heroOverviewOverride,
                        showBudget = showBudget,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = contentStartPadding, end = 400.dp)
                            .offset(y = -heroBottomPadding)
                    )
                }
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
    val context = LocalContext.current
    val metadataLogoImageLoader = rememberMetadataLogoImageLoader(context)
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
    val rating = imdbRatingFor(item)
    val ratingValue = parseRatingValue(rating)
    val hasMetadata = genreText.isNotEmpty() || year.isNotEmpty() || ratingValue > 0f

    val displayOverview = remember(overviewOverride, item.overview) {
        cleanOverviewText(overviewOverride ?: item.overview)
    }

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

            if (hasMetadata) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (genreText.isNotEmpty()) {
                        Text(
                            text = genreText,
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
                    if (year.isNotEmpty()) {
                        if (genreText.isNotEmpty()) {
                            Text(
                                text = "|",
                                style = ArflixTypography.caption.copy(fontSize = 12.sp, shadow = textShadow),
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        }
                        Text(
                            text = year,
                            style = ArflixTypography.caption.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                shadow = textShadow
                            ),
                            color = Color.White.copy(alpha = 0.8f),
                            maxLines = 1
                        )
                    }
                    if (ratingValue > 0f) {
                        if (genreText.isNotEmpty() || year.isNotEmpty()) {
                            Text(
                                text = "|",
                                style = ArflixTypography.caption.copy(fontSize = 12.sp, shadow = textShadow),
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        }
                        ImdbSvgRatingBadge(
                            rating = rating,
                            imageLoader = metadataLogoImageLoader,
                            ratingFontSize = 12,
                            logoWidth = 32.dp,
                            logoHeight = 13.dp,
                            textShadow = textShadow
                        )
                    }
                }
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
                            contentDescription = stringResource(R.string.play),
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = stringResource(R.string.play),
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
                            contentDescription = stringResource(R.string.details),
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = stringResource(R.string.details),
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
    val metadataLogoImageLoader = rememberMetadataLogoImageLoader(context)
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
                val rating = imdbRatingFor(item)
                val ratingValue = parseRatingValue(rating)

                val displayOverview = remember(item.id, item.overview) {
                    cleanOverviewText(item.overview)
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onNavigateToDetails(item.mediaType, item.id, null, null)
                        }
                ) {
                    // Backdrop image - Crop to fill without letterboxing
                    backdropUrl?.let { mobileBackdropUrl ->
                        val (bw, bh) = backdropSizePx
                        val request = remember(mobileBackdropUrl, bw, bh) {
                            ImageRequest.Builder(context)
                                .data(mobileBackdropUrl)
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

                        // Metadata row with IMDb rating badge
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
                            if (ratingValue > 0f) {
                                ImdbSvgRatingBadge(
                                    rating = rating,
                                    imageLoader = metadataLogoImageLoader,
                                    ratingFontSize = 11,
                                    logoWidth = 30.dp,
                                    logoHeight = 12.dp,
                                    textShadow = textShadow
                                )
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
    limitRowsDuringStartup: Boolean,
    suppressSelectUntilMs: Long,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    fastScrollThresholdMs: Long,
    usePosterCards: Boolean,
    isContextMenuOpen: Boolean,
    trailerIsPlaying: Boolean = false,
    onTrailerStop: () -> Unit = {},
    isMobile: Boolean = false,
    heroItem: MediaItem? = null,
    heroOverviewOverride: String? = null,
    onPlay: () -> Unit = {},
    onDetails: () -> Unit = {},
    currentProfile: com.arflix.tv.data.model.Profile?,
    profileCount: Int = 1,
    clockFormat: String = "24h",
    syncStatus: com.arflix.tv.data.repository.CloudSyncStatus = com.arflix.tv.data.repository.CloudSyncStatus.NOT_SIGNED_IN,
    hasUpdateBadge: Boolean = false,
    categoryHasMoreMap: Map<String, Boolean> = emptyMap(),
    smoothScrolling: Boolean = true,
    onLoadMoreCategory: (String) -> Unit = {},
    onItemFocusedPrefetch: (MediaItem) -> Unit = {},
    onMobileCategoryVisiblePosition: (String, Int) -> Unit = { _, _ -> },
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit,
    onNavigateToCollection: (String) -> Unit,
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
    val focusRecoveryDelayMs = 180L
    var preferredCategoryId by rememberSaveable { mutableStateOf<String?>(null) }
    val dpadRepeatGate = rememberArvioDpadRepeatGate(
        horizontalMinRepeatIntervalMs = 80L,
        verticalMinRepeatIntervalMs = 112L
    )
    // Profile avatar is always shown when a profile exists (clickable, opens
    // profile switcher). Focus navigation includes it as the first focusable item.
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    LaunchedEffect(rootHasFocus, isContextMenuOpen, isMobile) {
        if (isMobile || isContextMenuOpen || rootHasFocus) return@LaunchedEffect
        delay(focusRecoveryDelayMs)
        if (!rootHasFocus && !isContextMenuOpen) {
            runCatching { focusRequester.requestFocus() }
        }
    }
    LaunchedEffect(hasProfile) {
        if (hasProfile) focusState.sidebarFocusIndex = 2
    }

    LaunchedEffect(focusState.currentRowIndex, categories) {
        preferredCategoryId = categories.getOrNull(focusState.currentRowIndex)?.id
    }

    // Clamp focus indices when the category list structurally changes (rows added
    // or removed). This uses only the category IDs as the key — NOT item counts —
    // so it only fires when rows themselves appear/disappear, not when items within
    // a row change (which happens 8-14 times during cold start as skeletons are
    // replaced by real data, logos load, badges update, etc.).
    //
    // The previous implementation used item counts in the key and also contained
    // a "fallback to first non-empty row" path that aggressively reset focus
    // indices, plus a requestFocus() call that fought with the existing focused
    // card. Both of those caused the visible "trip" on startup where focus
    // disappeared until the user pressed Up/Down.
    //
    // The new approach is purely defensive: only clamp out-of-bounds indices,
    // never jump to a different row, never re-request focus.
    val categoryIds = remember(categories) {
        categories.joinToString(",") { it.id }
    }
    LaunchedEffect(categoryIds) {
        if (categories.isEmpty()) return@LaunchedEffect

        if (!focusState.userHasNavigated && !focusState.isSidebarFocused) {
            val preferredStartRow = preferredHomeStartRowIndex(categories)
            if (focusState.currentRowIndex != preferredStartRow) {
                focusState.currentRowIndex = preferredStartRow
                focusState.currentItemIndex = 0
                preferredCategoryId = categories.getOrNull(preferredStartRow)?.id
            }
        }

        // If the preferred category still exists, restore the row index to it.
        // Otherwise keep the current index but clamp to valid range.
        val restoredRow = preferredCategoryId
            ?.let { id -> categories.indexOfFirst { it.id == id } }
            ?.takeIf { it >= 0 }
        if (restoredRow != null) {
            focusState.currentRowIndex = restoredRow
        } else {
            focusState.currentRowIndex = focusState.currentRowIndex
                .coerceIn(0, (categories.size - 1).coerceAtLeast(0))
        }

        // Clamp item index if it's beyond the current row's bounds.
        // Do NOT reset to 0 or jump rows — that's what caused the trip.
        val currentRowItems = categories.getOrNull(focusState.currentRowIndex)?.items.orEmpty()
        if (currentRowItems.isNotEmpty() && focusState.currentItemIndex > currentRowItems.lastIndex) {
            focusState.currentItemIndex = currentRowItems.lastIndex
        }
    }

    val focusedCategory = categories.getOrNull(focusState.currentRowIndex)
    val focusedRowItemCount = focusedCategory?.items?.size ?: 0
    LaunchedEffect(focusState.currentRowIndex, focusedRowItemCount) {
        if (focusedRowItemCount <= 0) {
            if (focusState.currentItemIndex != 0) focusState.currentItemIndex = 0
            return@LaunchedEffect
        }
        val maxItemIndex = focusedRowItemCount - 1
        if (focusState.currentItemIndex > maxItemIndex) {
            focusState.currentItemIndex = maxItemIndex
        }
    }

    val keyEventModifier = if (isMobile) {
        Modifier // No D-pad key handling on mobile
    } else {
        Modifier.onPreviewKeyEvent { event ->
            if (isContextMenuOpen) {
                return@onPreviewKeyEvent false
            }
            if (trailerIsPlaying && event.type == KeyEventType.KeyDown &&
                (isArvioDpadNavigationKey(event.key) || event.key == Key.Enter || event.key == Key.DirectionCenter || event.key == Key.Back)
            ) {
                onTrailerStop()
                return@onPreviewKeyEvent true
            }
            if (event.type == KeyEventType.KeyUp && isArvioDpadNavigationKey(event.key)) {
                dpadRepeatGate.reset()
            }
            if (
                event.type == KeyEventType.KeyDown &&
                isArvioDpadNavigationKey(event.key) &&
                dpadRepeatGate.shouldSkip(
                    keyCode = event.nativeKeyEvent.keyCode,
                    repeatCount = event.nativeKeyEvent.repeatCount,
                    nowMs = SystemClock.elapsedRealtime()
                )
            ) {
                return@onPreviewKeyEvent true
            }
            when (event.type) {
                KeyEventType.KeyDown -> when (event.key) {
                    Key.Enter, Key.DirectionCenter -> {
                        // Track KeyDown time for long-press detection.
                        // Sidebar actions fire immediately; content items wait for KeyUp
                        // to distinguish tap (navigate) from long-press (context menu).
                        if (focusState.isSidebarFocused) {
                            if (hasProfile && focusState.sidebarFocusIndex == 0) {
                                onSwitchProfile()
                            } else {
                                when (topBarFocusedItem(focusState.sidebarFocusIndex, hasProfile)) {
                                    SidebarItem.SEARCH -> onNavigateToSearch()
                                    SidebarItem.HOME -> Unit
                                    SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                    SidebarItem.TV -> onNavigateToTv(null, null)
                                    SidebarItem.SETTINGS -> onNavigateToSettings()
                                    null -> Unit
                                }
                            }
                        } else {
                            if (!selectPressedInHome) {
                                selectPressedInHome = true
                                selectDownAtMs = SystemClock.elapsedRealtime()
                            }
                        }
                        true
                    }
                    Key.DirectionLeft -> {
                        selectPressedInHome = false
                        selectDownAtMs = 0L
                        focusState.userHasNavigated = true
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
                        selectPressedInHome = false
                        selectDownAtMs = 0L
                        focusState.userHasNavigated = true
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
                        selectPressedInHome = false
                        selectDownAtMs = 0L
                        focusState.userHasNavigated = true
                        if (focusState.isSidebarFocused) {
                            true
                        } else if (focusState.currentRowIndex > 0) {
                            // Save current item position before leaving this row
                            focusState.rowItemIndices[focusState.currentRowIndex] = focusState.currentItemIndex
                            focusState.currentRowIndex--
                            // Restore saved position for the target row (or 0 if never visited)
                            focusState.currentItemIndex = focusState.rowItemIndices[focusState.currentRowIndex] ?: 0
                            focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                            true
                        } else {
                            focusState.isSidebarFocused = true
                            true
                        }
                    }
                    Key.DirectionDown -> {
                        selectPressedInHome = false
                        selectDownAtMs = 0L
                        focusState.userHasNavigated = true
                        if (focusState.isSidebarFocused) {
                            focusState.isSidebarFocused = false
                            focusState.currentItemIndex = 0
                            focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                            true
                        } else if (!focusState.isSidebarFocused && focusState.currentRowIndex < categories.size - 1) {
                            // Save current item position before leaving this row
                            focusState.rowItemIndices[focusState.currentRowIndex] = focusState.currentItemIndex
                            focusState.currentRowIndex++
                            // Restore saved position for the target row (or 0 if never visited)
                            focusState.currentItemIndex = focusState.rowItemIndices[focusState.currentRowIndex] ?: 0
                            focusState.lastNavEventTime = SystemClock.elapsedRealtime()
                            true
                        } else {
                            true
                        }
                    }
                        Key.Back, Key.Escape -> {
                            selectPressedInHome = false
                            selectDownAtMs = 0L
                            if (focusState.isSidebarFocused) {
                                onExitApp()
                            } else {
                                focusState.isSidebarFocused = true
                            }
                            true
                        }
                        Key.Menu, Key.Info -> {
                            selectPressedInHome = false
                            selectDownAtMs = 0L
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
                            if (selectPressedInHome && !focusState.isSidebarFocused) {
                                val holdMs = SystemClock.elapsedRealtime() - selectDownAtMs
                                val currentItem = getFocusedItem(
                                    categories,
                                    focusState.currentRowIndex,
                                    focusState.currentItemIndex
                                )
                                currentItem?.takeIf { isActionableHomeItem(it) }?.let { item ->
                                    if (holdMs >= 500L) {
                                        // Long-press: open context menu
                                        val currentCategory = categories.getOrNull(focusState.currentRowIndex)
                                        val isContinue = currentCategory?.id == "continue_watching"
                                        onOpenContextMenu(item, isContinue)
                                    } else {
                                        // Short press: navigate. Must check collection:
                                        // BEFORE falling through to Details — D-pad SELECT
                                        // on a service tile (Netflix, HBO, ...) was hitting
                                        // DetailsScreen with the synthetic hash id and
                                        // spamming TMDB 404s instead of opening the catalog.
                                        val iptvId = item.status?.removePrefix("iptv:")
                                            ?.takeIf { item.status?.startsWith("iptv:") == true && it.isNotBlank() }
                                        val collectionId = item.status?.removePrefix("collection:")
                                            ?.takeIf { item.status?.startsWith("collection:") == true && it.isNotBlank() }
                                        if (iptvId != null) {
                                            onNavigateToTv(iptvId, getIptvStreamUrl(item.id))
                                        } else if (collectionId != null) {
                                            onNavigateToCollection(collectionId)
                                        } else {
                                            onNavigateToDetails(item.mediaType, item.id, item.nextEpisode?.seasonNumber, item.nextEpisode?.episodeNumber)
                                        }
                                    }
                                }
                            }
                            selectPressedInHome = false
                            selectDownAtMs = 0L
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
            .onFocusChanged {
                rootHasFocus = it.hasFocus
                if (!it.hasFocus) {
                    selectPressedInHome = false
                    selectDownAtMs = 0L
                }
            }
            .focusable()
            .then(keyEventModifier)
    ) {
        if (!isMobile) {
            AppTopBar(
                selectedItem = SidebarItem.HOME,
                isFocused = focusState.isSidebarFocused,
                focusedIndex = focusState.sidebarFocusIndex,
                profile = currentProfile,
                profileCount = profileCount,
                clockFormat = clockFormat,
                hasUpdateBadge = hasUpdateBadge
            )
        }

        HomeRowsLayer(
            categories = categories,
            cardLogoUrls = cardLogoUrls,
            focusState = focusState,
            limitRowsDuringStartup = limitRowsDuringStartup,
            contentStartPadding = contentStartPadding,
            fastScrollThresholdMs = fastScrollThresholdMs,
            usePosterCards = usePosterCards,
            isMobile = isMobile,
            categoryHasMoreMap = categoryHasMoreMap,
            smoothScrolling = smoothScrolling,
            onLoadMoreCategory = onLoadMoreCategory,
            onItemFocusedPrefetch = onItemFocusedPrefetch,
            heroItem = heroItem,
            heroOverviewOverride = heroOverviewOverride,
            onPlay = onPlay,
            onDetails = onDetails,
            onNavigateToDetails = onNavigateToDetails,
            onMobileCategoryVisiblePosition = onMobileCategoryVisiblePosition,
            onItemClick = { item ->
                if (!isActionableHomeItem(item)) {
                    return@HomeRowsLayer
                }
                val iptvId = item.status?.removePrefix("iptv:")?.takeIf { item.status?.startsWith("iptv:") == true && it.isNotBlank() }
                val collectionId = item.status?.removePrefix("collection:")?.takeIf { item.status?.startsWith("collection:") == true && it.isNotBlank() }
                if (iptvId != null) {
                    onNavigateToTv(iptvId, getIptvStreamUrl(item.id))
                } else if (collectionId != null) {
                    onNavigateToCollection(collectionId)
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
    limitRowsDuringStartup: Boolean,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    fastScrollThresholdMs: Long,
    usePosterCards: Boolean,
    isMobile: Boolean = false,
    categoryHasMoreMap: Map<String, Boolean> = emptyMap(),
    smoothScrolling: Boolean = true,
    onLoadMoreCategory: (String) -> Unit = {},
    onItemFocusedPrefetch: (MediaItem) -> Unit = {},
    heroItem: MediaItem? = null,
    heroOverviewOverride: String? = null,
    onPlay: () -> Unit = {},
    onDetails: () -> Unit = {},
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit = { _, _, _, _ -> },
    onMobileCategoryVisiblePosition: (String, Int) -> Unit = { _, _ -> },
    onItemClick: (MediaItem) -> Unit,
    onItemLongClick: ((MediaItem, Boolean) -> Unit)? = null
) {
    if (isMobile) {
        MobileHomeRowsLayer(
            categories = categories,
            cardLogoUrls = cardLogoUrls,
            contentStartPadding = contentStartPadding,
            usePosterCards = usePosterCards,
            categoryHasMoreMap = categoryHasMoreMap,
            onLoadMoreCategory = onLoadMoreCategory,
            onNavigateToDetails = onNavigateToDetails,
            onItemClick = onItemClick,
            onItemLongClick = onItemLongClick,
            onCategoryVisiblePosition = { categoryId, lastVisibleItemIndex ->
                onMobileCategoryVisiblePosition(categoryId, lastVisibleItemIndex)
                val rowIndex = categories.indexOfFirst { it.id == categoryId }
                val visibleItem = categories
                    .getOrNull(rowIndex)
                    ?.items
                    ?.getOrNull(lastVisibleItemIndex)
                if (visibleItem != null) onItemFocusedPrefetch(visibleItem)
            }
        )
    } else {
        TvHomeRowsLayer(
            categories = categories,
            cardLogoUrls = cardLogoUrls,
            focusState = focusState,
            limitRowsDuringStartup = limitRowsDuringStartup,
            contentStartPadding = contentStartPadding,
            fastScrollThresholdMs = fastScrollThresholdMs,
            usePosterCards = usePosterCards,
            categoryHasMoreMap = categoryHasMoreMap,
            smoothScrolling = smoothScrolling,
            onLoadMoreCategory = onLoadMoreCategory,
            onItemFocusedPrefetch = onItemFocusedPrefetch,
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
    categoryHasMoreMap: Map<String, Boolean> = emptyMap(),
    onLoadMoreCategory: (String) -> Unit = {},
    onNavigateToDetails: (MediaType, Int, Int?, Int?) -> Unit = { _, _, _, _ -> },
    onItemClick: (MediaItem) -> Unit,
    onItemLongClick: ((MediaItem, Boolean) -> Unit)? = null,
    onCategoryVisiblePosition: (String, Int) -> Unit = { _, _ -> }
) {
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
            val isCollectionRow = category.id.startsWith("collection_row_")
            val rowKey = remember(category.id) { "home:${category.id}" }
            val rowUsePosterCards = rememberCatalogueRowLayoutMode(rowKey) == CardLayoutMode.POSTER
            val rowMobileItemWidth = if (rowUsePosterCards) 124.dp else 200.dp
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

            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                // Section title
                Row(
                    modifier = Modifier.padding(
                        start = contentStartPadding,
                        bottom = 8.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = localizedCategoryTitle(category),
                        style = ArflixTypography.sectionTitle.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }

                val rowHasMore = categoryHasMoreMap[category.id] == true
                val isPortrait = if (isCollectionRow) {
                    category.items.firstOrNull()?.collectionTileShape == CollectionTileShape.POSTER
                } else {
                    rowUsePosterCards
                }
                val skeletonCount = if (isPortrait) 12 else 7
                val itemsToRender = remember(category.items, rowHasMore, isPortrait) {
                    if (rowHasMore) {
                        category.items + List(skeletonCount) { idx ->
                            MediaItem(
                                id = -1000 - idx,
                                title = "",
                                isPlaceholder = true
                            )
                        }
                    } else {
                        category.items
                    }
                }

                // Horizontal card row with touch scrolling
                LazyRow(
                    state = rowState,
                    modifier = Modifier.arvioDpadFocusGroup(),
                    contentPadding = PaddingValues(
                        start = contentStartPadding,
                        end = 16.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(mobileItemSpacing)
                ) {
                    itemsIndexed(
                        itemsToRender,
                        key = { _, item ->
                            if (item.isPlaceholder) "placeholder_${category.id}_${item.id}"
                            else {
                                val episodeSuffix = if (item.nextEpisode != null) "_S${item.nextEpisode.seasonNumber}E${item.nextEpisode.episodeNumber}" else ""
                                "${item.mediaType.name}-${item.id}${episodeSuffix}"
                            }
                        },
                        contentType = { _, item -> if (item.isPlaceholder) "placeholder_card" else "${item.mediaType.name}_mobile_card" }
                    ) { index, item ->
                        if (item.isPlaceholder) {
                            LaunchedEffect(item.id) {
                                onLoadMoreCategory(category.id)
                            }
                        } else if (rowHasMore && index >= category.items.size - 5) {
                            LaunchedEffect(category.items.size) {
                                onLoadMoreCategory(category.id)
                            }
                        }
                        if (isRanked && index < 10) {
                            Box(
                                modifier = Modifier.width(rowMobileItemWidth)
                            ) {
                                val cardLogoUrl = if (isCollectionRow) null else cardLogoUrls["${item.mediaType}_${item.id}"]
                                val collectionLandscape = item.collectionTileShape != CollectionTileShape.POSTER
                                ArvioMediaCard(
                                    item = item,
                                    width = rowMobileItemWidth,
                                    isLandscape = if (isCollectionRow) collectionLandscape else !rowUsePosterCards,
                                    logoImageUrl = cardLogoUrl,
                                    showProgress = false,
                                    showTitle = !item.collectionHideTitle,
                                    isFocusedOverride = false,
                                    enableSystemFocus = false,
                                    onFocused = {},
                                    onClick = { onItemClick(item) },
                                    onLongClick = onItemLongClick?.let { callback -> { callback(item, isContinueWatching) } },
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
                            val cardLogoUrl = if (isCollectionRow) null else cardLogoUrls["${item.mediaType}_${item.id}"]
                            val collectionLandscape = item.collectionTileShape != CollectionTileShape.POSTER
                            ArvioMediaCard(
                                item = item,
                                width = rowMobileItemWidth,
                                isLandscape = if (isCollectionRow) collectionLandscape else !rowUsePosterCards,
                                logoImageUrl = cardLogoUrl,
                                showProgress = isContinueWatching,
                                showTitle = !item.collectionHideTitle,
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
    limitRowsDuringStartup: Boolean,
    contentStartPadding: androidx.compose.ui.unit.Dp,
    fastScrollThresholdMs: Long,
    usePosterCards: Boolean,
    categoryHasMoreMap: Map<String, Boolean> = emptyMap(),
    smoothScrolling: Boolean = true,
    onLoadMoreCategory: (String) -> Unit = {},
    onItemFocusedPrefetch: (MediaItem) -> Unit = {},
    onItemClick: (MediaItem) -> Unit
) {
    // ── Focus-row stabilizer ──
    // Track the focused row by its category ID (stable) rather than integer
    // index. When new catalogs are inserted above the focused row (e.g.,
    // "Favorite TV" or custom Trakt lists loading), the integer index of the
    // focused row shifts but its ID stays the same. Without this correction
    // the LazyColumn would scroll to the wrong row and the focus highlight
    // would visually "trip" to a different catalog until the user presses
    // Up/Down to re-establish focus. This was the root cause of the startup
    // focus/catalog glitch.
    var focusedCategoryId by remember { mutableStateOf<String?>(null) }
    // Sync: when the user moves focus (currentRowIndex changes from D-pad),
    // update the tracked category ID.
    LaunchedEffect(focusState.currentRowIndex) {
        val id = categories.getOrNull(focusState.currentRowIndex)?.id
        if (id != null) focusedCategoryId = id
    }
    LaunchedEffect(categories) {
        val tracked = focusedCategoryId ?: return@LaunchedEffect
        val newIndex = categories.indexOfFirst { it.id == tracked }
        if (newIndex >= 0 && newIndex != focusState.currentRowIndex) {
            focusState.currentRowIndex = newIndex
        }
    }

    val currentRowIndex = focusState.currentRowIndex
    val rowWindowStart = remember(categories, currentRowIndex, limitRowsDuringStartup) {
        if (!limitRowsDuringStartup || categories.size <= 3) {
            0
        } else {
            currentRowIndex
                .coerceIn(0, (categories.size - 1).coerceAtLeast(0))
        }
    }
    val renderedCategories = remember(categories, rowWindowStart, limitRowsDuringStartup) {
        if (!limitRowsDuringStartup || categories.size <= 3) {
            categories
        } else {
            categories.subList(
                rowWindowStart,
                min(categories.size, rowWindowStart + 3)
            )
        }
    }
    val localCurrentRowIndex = (currentRowIndex - rowWindowStart)
        .coerceIn(0, (renderedCategories.size - 1).coerceAtLeast(0))

    val density = LocalDensity.current
    val rowLayoutModes = renderedCategories.map { category ->
        rememberCatalogueRowLayoutMode("home:${category.id}") == CardLayoutMode.POSTER
    }
    val categoryHeightsPx = remember(renderedCategories, rowLayoutModes, density) {
        renderedCategories.mapIndexed { idx, category ->
            val usePoster = rowLayoutModes.getOrNull(idx) ?: false
            val heightDp = if (usePoster) 252.dp else 202.dp
            with(density) { heightDp.toPx() }
        }
    }

    var isFastScrolling by remember { mutableStateOf(false) }
    LaunchedEffect(focusState) {
        snapshotFlow { focusState.lastNavEventTime }
            .distinctUntilChanged()
            .collectLatest { anchor ->
                if (anchor <= 0L) {
                    isFastScrolling = false
                    return@collectLatest
                }
                isFastScrolling = true
                delay(fastScrollThresholdMs)
                if (focusState.lastNavEventTime == anchor) {
                    isFastScrolling = false
                }
            }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        val rowsViewportHeight = (maxHeight * 0.31f).coerceIn(260.dp, 340.dp)
        val listState = rememberLazyListState()
        var lastAppliedTargetIndex by remember { mutableIntStateOf(-1) }
        val targetIndex = localCurrentRowIndex.coerceIn(0, (renderedCategories.size - 1).coerceAtLeast(0))
        LaunchedEffect(targetIndex) {
            val currentIndex = listState.firstVisibleItemIndex
            val currentOffset = listState.firstVisibleItemScrollOffset
            val initialPlacement = lastAppliedTargetIndex < 0
            if (currentIndex == targetIndex && currentOffset <= 2) {
                lastAppliedTargetIndex = targetIndex
                return@LaunchedEffect
            }

            val recentUserNav = focusState.lastNavEventTime > 0L &&
                (SystemClock.elapsedRealtime() - focusState.lastNavEventTime) <= fastScrollThresholdMs
            if (!initialPlacement && !recentUserNav) return@LaunchedEffect

            val jumpDistance = abs(targetIndex - currentIndex)
            if (initialPlacement || jumpDistance > 7) {
                listState.scrollToItem(index = targetIndex, scrollOffset = 0)
            } else {
                if (smoothScrolling) {
                    val visibleTarget = listState.layoutInfo.visibleItemsInfo
                        .firstOrNull { it.index == targetIndex }
                    val deltaPx = if (visibleTarget != null) {
                        visibleTarget.offset.toFloat()
                    } else {
                        if (targetIndex < currentIndex) {
                            val intermediateSum = (targetIndex until currentIndex).sumOf { idx ->
                                categoryHeightsPx.getOrNull(idx)?.toDouble() ?: (202.0 * density.density)
                            }.toFloat()
                            -(intermediateSum + currentOffset)
                        } else {
                            val intermediateSum = (currentIndex until targetIndex).sumOf { idx ->
                                categoryHeightsPx.getOrNull(idx)?.toDouble() ?: (202.0 * density.density)
                            }.toFloat()
                            intermediateSum - currentOffset
                        }
                    }
                    listState.animateHomeScrollDelta(
                        deltaPx = deltaPx,
                        durationMillis = if (jumpDistance >= 3) 180 else 150
                    )
                    if (
                        listState.firstVisibleItemIndex != targetIndex ||
                        abs(listState.firstVisibleItemScrollOffset) > 6
                    ) {
                        listState.scrollToItem(index = targetIndex, scrollOffset = 0)
                    }
                } else {
                    listState.animateScrollToItem(index = targetIndex, scrollOffset = 0)
                }
            }
            lastAppliedTargetIndex = targetIndex
        }
        // Keep rows in the lower portion of the screen so hero metadata has dedicated space,
        // matching the separation used on Details.
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(rowsViewportHeight)
                .arvioManualBringIntoViewBoundary()
                .clipToBounds()
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = rowsViewportHeight),
                modifier = Modifier
                    .fillMaxSize()
                    .arvioDpadFocusGroup(enableFocusRestorer = false)
                    .clipToBounds(),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                itemsIndexed(
                    items = renderedCategories,
                    key = { _, category -> category.id },
                    contentType = { _, category ->
                        when {
                            category.id.startsWith("collection_row_") -> "home_collection_row"
                            category.title.contains("Top 10", ignoreCase = true) -> "home_ranked_row"
                            else -> "home_category_row"
                        }
                    }
                ) { index, category ->
                    val actualRowIndex = rowWindowStart + index
                    val rowIsFocused = !focusState.isSidebarFocused && actualRowIndex == focusState.currentRowIndex
                    val rowKey = remember(category.id) { "home:${category.id}" }
                    val rowUsePosterCards = rememberCatalogueRowLayoutMode(rowKey) == CardLayoutMode.POSTER
                    val rowHeight = if (rowUsePosterCards) 252.dp else 202.dp
                    Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(rowHeight)
                        .clipToBounds()
                    ) {
                        ContentRow(
                            category = category,
                            cardLogoUrls = cardLogoUrls,
                            isCurrentRow = rowIsFocused,
                            isRanked = category.title.contains("Top 10", ignoreCase = true),
                            usePosterCards = rowUsePosterCards,
                            startPadding = contentStartPadding,
                            categoryHasMore = categoryHasMoreMap[category.id] == true,
                            smoothScrolling = smoothScrolling,
                            onLoadMore = { onLoadMoreCategory(category.id) },
                            focusedItemIndex = if (rowIsFocused) focusState.currentItemIndex else -1,
                            isFastScrolling = rowIsFocused && isFastScrolling,
                            onItemClick = onItemClick,
                            onItemFocused = { item, itemIdx ->
                                focusState.currentRowIndex = actualRowIndex
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
private fun lockedHomeRailEndPadding(
    itemWidth: Dp,
    startPadding: Dp,
    minimum: Dp
): Dp {
    val configuration = LocalConfiguration.current
    return (configuration.screenWidthDp.dp - startPadding - itemWidth)
        .coerceAtLeast(minimum)
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
            text = stringResource(R.string.included_with_prime),
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
private fun ImdbSvgRatingBadge(
    rating: String,
    imageLoader: ImageLoader,
    ratingFontSize: Int,
    logoWidth: Dp,
    logoHeight: Dp,
    textShadow: Shadow
) {
    val imdbLogoUri = remember { "android.resource://com.arvio.tv/${R.raw.logo_imdb_rectangle}" }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        AsyncImage(
            model = imdbLogoUri,
            imageLoader = imageLoader,
            contentDescription = "IMDb",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(logoWidth)
                .height(logoHeight)
        )
        Text(
            text = rating,
            style = ArflixTypography.caption.copy(
                fontSize = ratingFontSize.sp,
                fontWeight = FontWeight.Bold,
                shadow = textShadow
            ),
            color = Color.White,
            maxLines = 1
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
    categoryHasMore: Boolean = false,
    smoothScrolling: Boolean = true,
    onLoadMore: () -> Unit = {},
    focusedItemIndex: Int,
    isFastScrolling: Boolean,
    onItemClick: (MediaItem) -> Unit,
    onItemFocused: (MediaItem, Int) -> Unit
) {
    val isCollectionRow = category.id.startsWith("collection_row_")
    val rowState = rememberLazyListState()
    val density = LocalDensity.current
    val isContinueWatching = category.id == "continue_watching"
    // Poster rows felt too tight vertically when focused. Instead of adding more
    // row spacing (which made the section layout feel loose), slightly reduce the
    // poster card width so the 1.05x focus zoom has more breathing room inside the
    // existing row spacing. ~5% smaller than before.
    val effectivePosterMode = if (isCollectionRow) {
        category.items.firstOrNull()?.collectionTileShape == CollectionTileShape.POSTER
    } else {
        usePosterCards
    }
    val cardAspectRatio = if (effectivePosterMode) 2f / 3f else 16f / 9f
    val itemWidth = if (effectivePosterMode) 119.dp else 210.dp
    val itemSpacing = 14.dp
    val totalItems = category.items.size
    val maxFirstIndex = remember(totalItems) {
        (totalItems - 1).coerceAtLeast(0)
    }
    val isScrollable = totalItems > 1
    val itemSpanPx = remember(density, itemWidth, itemSpacing) {
        with(density) { (itemWidth + itemSpacing).toPx().coerceAtLeast(1f) }
    }
    val railFocusOverlayActive = isCurrentRow && isScrollable && focusedItemIndex >= 0 && totalItems > 0 &&
        focusedItemIndex <= maxFirstIndex &&
        focusedItemIndex == rowState.firstVisibleItemIndex &&
        rowState.firstVisibleItemScrollOffset == 0
    val focusedCardIndex = if (railFocusOverlayActive) {
        -1
    } else {
        focusedItemIndex
    }
    val railFocusShape = rememberArvioCardShape(ArvioSkin.radius.md)
    val railEndPadding = lockedHomeRailEndPadding(
        itemWidth = itemWidth,
        startPadding = startPadding,
        minimum = itemWidth + 30.dp
    )
    val latestOnItemClick = rememberUpdatedState(onItemClick)
    val latestOnItemFocused = rememberUpdatedState(onItemFocused)
    // Keep focused card anchored by scrolling the row on every focus change.
    // Use smooth scroll (animated) for D-pad moves to avoid abrupt jumps.
    var lastScrollIndex by remember { mutableIntStateOf(-1) }
    var lastScrollOffset by remember { mutableIntStateOf(-1) }
    LaunchedEffect(isCurrentRow, totalItems) {
        lastScrollIndex = -1
        lastScrollOffset = -1
    }
    LaunchedEffect(isCurrentRow, focusedItemIndex, totalItems) {
        if (!isCurrentRow || focusedItemIndex < 0 || totalItems == 0) return@LaunchedEffect

        val currentFirstIndex = rowState.firstVisibleItemIndex.coerceAtMost(maxFirstIndex)
        val currentFirstOffset = rowState.firstVisibleItemScrollOffset
        // TV rows should behave like a stable focus rail: the focused tile stays
        // in the first visible slot while D-pad Right moves the row underneath it.
        // Allowing a leading comfort item made focus sit on the second tile.
        val scrollTargetIndex = when {
            !isScrollable || lastScrollIndex == -1 -> focusedItemIndex.coerceAtMost(maxFirstIndex)
            focusedItemIndex != currentFirstIndex -> focusedItemIndex.coerceAtLeast(0)
            else -> currentFirstIndex
        }.coerceAtMost(maxFirstIndex)

        val extraOffset = 0

        if (lastScrollIndex == scrollTargetIndex && lastScrollOffset == extraOffset) return@LaunchedEffect
        val isFirstScroll = lastScrollIndex == -1
        lastScrollIndex = scrollTargetIndex
        lastScrollOffset = extraOffset

        if (isFirstScroll) {
            // First time we jump directly to the correct position (no animation)
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
            return@LaunchedEffect
        }

        val currentLastIndex = rowState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: currentFirstIndex
        val targetOutsideViewport = focusedItemIndex < currentFirstIndex || focusedItemIndex > currentLastIndex
        val jumpDistance = abs(scrollTargetIndex - currentFirstIndex)
        val offsetDelta = abs(extraOffset - currentFirstOffset)
        if (jumpDistance > 7) {
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (
            scrollTargetIndex != currentFirstIndex ||
            targetOutsideViewport ||
            offsetDelta > 1
        ) {
            if (smoothScrolling) {
                val deltaPx = ((scrollTargetIndex - currentFirstIndex) * itemSpanPx) + (extraOffset - currentFirstOffset)
                rowState.animateHomeScrollDelta(
                    deltaPx = deltaPx,
                    durationMillis = when {
                        isFastScrolling -> 115
                        jumpDistance >= 3 -> 180
                        else -> 150
                    }
                )
                if (
                    !isFastScrolling && (
                        rowState.firstVisibleItemIndex != scrollTargetIndex ||
                            abs(rowState.firstVisibleItemScrollOffset - extraOffset) > 6
                        )
                ) {
                    rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
                }
            } else {
                rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
            }
        } else {
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        }
    }

    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
    ) {
        // Section title - clean white text, aligned with cards
        Row(
            modifier = Modifier.padding(start = startPadding, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = localizedCategoryTitle(category),
                style = ArflixTypography.sectionTitle.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }

        val skeletonCount = if (effectivePosterMode) 12 else 7
        val itemsToRender = remember(category.items, categoryHasMore, effectivePosterMode) {
            if (categoryHasMore) {
                category.items + List(skeletonCount) { idx ->
                    MediaItem(
                        id = -1000 - idx,
                        title = "",
                        isPlaceholder = true
                    )
                }
            } else {
                category.items
            }
        }

        // Cards row - clipped to hide previous items when scrolling
        val clipModifier = if (isContinueWatching) Modifier else Modifier.clipToBounds()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .arvioManualBringIntoViewBoundary()
                .then(clipModifier)
        ) {
            LazyRow(
                state = rowState,
                modifier = Modifier.arvioDpadFocusGroup(enableFocusRestorer = false),
                contentPadding = PaddingValues(
                    start = startPadding,
                    end = railEndPadding,
                    top = 14.dp,
                    bottom = 14.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(itemSpacing),
                userScrollEnabled = false
            ) {
                itemsIndexed(
                    itemsToRender,
                    key = { _, item ->
                        if (item.isPlaceholder) "placeholder_${category.id}_${item.id}"
                        else homeRowItemKey(item)
                    },
                    contentType = { index, item ->
                        when {
                            item.isPlaceholder -> "placeholder_card"
                            isCollectionRow -> "collection_tile"
                            isRanked && index < 10 -> "${item.mediaType.name}_ranked_card"
                            else -> "${item.mediaType.name}_card"
                        }
                    }
                ) { index, item ->
                if (item.isPlaceholder) {
                    LaunchedEffect(item.id) {
                        onLoadMore()
                    }
                } else if (categoryHasMore && index >= category.items.size - 5) {
                    LaunchedEffect(category.items.size) {
                        onLoadMore()
                    }
                }
                val itemIsFocused = isCurrentRow && index == focusedCardIndex
                val onCardFocused = remember(item, index) {
                    { latestOnItemFocused.value(item, index) }
                }
                val onCardClick = remember(item) {
                    { latestOnItemClick.value(item) }
                }
                if (isRanked && index < 10) {
                    // Top 10 rows should use the SAME card sizing as every other row.
                    // The previous layout used giant background numerals and a smaller
                    // embedded card, which made the row feel cramped and inconsistent.
                    // Use a normal card and place a premium gold rank badge in the
                    // top-right corner instead.
                    val cardLogoUrl = if (isCollectionRow) null else cardLogoUrls["${item.mediaType}_${item.id}"]
                    Box(
                        modifier = Modifier.width(itemWidth)
                    ) {
                        ArvioMediaCard(
                            item = item,
                            width = itemWidth,
                            isLandscape = !effectivePosterMode,
                            logoImageUrl = cardLogoUrl,
                            showLogoImage = true,
                            raiseOnFocus = !isFastScrolling,
                            showProgress = false,
                            showTitle = isCollectionRow && !item.collectionHideTitle,
                            isFocusedOverride = itemIsFocused && !railFocusOverlayActive,
                            focusedScale = 1f,
                            enableFocusedImageSwap = !isCollectionRow && !isFastScrolling,
                            animateFocus = false,
                            enableSystemFocus = false,
                            onFocused = onCardFocused,
                            onClick = onCardClick,
                        )

                        TopRankRibbon(
                            rank = index + 1,
                            isFocused = itemIsFocused,
                            compact = !effectivePosterMode,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .zIndex(2f)
                                .padding(start = 8.dp)
                        )
                    }
                } else {
                    // Standard Card - keep width aligned with scroll math
                    val cardLogoUrl = if (isCollectionRow) null else cardLogoUrls["${item.mediaType}_${item.id}"]
                    ArvioMediaCard(
                        item = item,
                        width = itemWidth,
                        isLandscape = !effectivePosterMode,
                        logoImageUrl = cardLogoUrl,
                        showLogoImage = true,
                        raiseOnFocus = !isFastScrolling,
                        showProgress = isContinueWatching,
                        showTitle = isCollectionRow && !item.collectionHideTitle,
                        isFocusedOverride = itemIsFocused && !railFocusOverlayActive,
                        focusedScale = 1f,
                        enableFocusedImageSwap = !isCollectionRow && !isFastScrolling,
                        animateFocus = false,
                        enableSystemFocus = false,
                        onFocused = onCardFocused,
                        onClick = onCardClick,
                    )
                }
                }
            }
            if (railFocusOverlayActive) {
                ArvioFocusableSurface(
                    modifier = Modifier
                        .padding(start = startPadding, top = 14.dp)
                        .width(itemWidth)
                        .aspectRatio(cardAspectRatio)
                        .zIndex(4f),
                    shape = railFocusShape,
                    backgroundColor = Color.Transparent,
                    outlineColor = ArvioSkin.colors.focusOutline,
                    outlineWidth = 2.5.dp,
                    focusedScale = 1f,
                    pressedScale = 0.97f,
                    animateFocus = false,
                    enableSystemFocus = false,
                    isFocusedOverride = true
                ) {
                    // Empty by design: this keeps the D-pad focus ring anchored to
                    // the first rail slot while the selected item scrolls under it.
                }
            }
        }  // Close Box
    }  // Close Column
}
