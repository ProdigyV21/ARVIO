package com.arflix.tv.ui.screens.details

import android.content.Context
import android.graphics.Bitmap
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed as standardItemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.blur
import com.arflix.tv.util.settingsDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.semantics.Role
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.ImageLoader
import coil.imageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.R
import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Review
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.components.EpisodeContextMenu
import com.arflix.tv.ui.components.KeepScreenOn
import com.arflix.tv.ui.components.SeasonContextMenu
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.PersonModal
import com.arflix.tv.ui.components.PosterCard
import com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.SkeletonDetailsPage
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.components.TrailerPlayer
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.zIndex
import androidx.compose.ui.input.key.onKeyEvent
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.focus.arvioManualBringIntoViewBoundary
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.focus.isArvioDpadNavigationKey
import com.arflix.tv.ui.focus.rememberArvioDpadRepeatGate
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.AnimationConstants
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.Purple
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.formatGenreName
import com.arflix.tv.util.isInCinema
import com.arflix.tv.util.parseRatingValue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import androidx.compose.ui.res.stringResource


private object DetailsScreenRegexes {
    val YEAR_REGEX = Regex("""\d{4}""")
}

/**
 * Details screen for movies and TV shows
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun DetailsScreen(
    mediaType: MediaType,
    mediaId: Int,
    initialSeason: Int? = null,
    initialEpisode: Int? = null,
    viewModel: DetailsViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onNavigateToPlayer: (MediaType, Int, Int?, Int?, String?, String?, String?, String?, Long?) -> Unit,
    onNavigateToDetails: (MediaType, Int) -> Unit,
    onNavigateToCollection: (String) -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit
) {
    val isRtlLayoutDirection = androidx.compose.ui.platform.LocalLayoutDirection.current == androidx.compose.ui.unit.LayoutDirection.Rtl
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val usePosterCards = rememberCatalogueRowLayoutMode("details:similar") == CardLayoutMode.POSTER
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val isMobile = LocalDeviceType.current.isTouchDevice()

    // Start on buttons for both TV and movies (buttons are now shown for both)
    var focusedSection by remember { mutableStateOf(FocusSection.BUTTONS) }
    var buttonIndex by remember { mutableIntStateOf(0) }
    var episodeIndex by rememberSaveable { mutableIntStateOf(0) }
    var seasonIndex by rememberSaveable { mutableIntStateOf(0) }
    var castIndex by remember { mutableIntStateOf(0) }
    var reviewIndex by remember { mutableIntStateOf(0) }
    var similarIndex by remember { mutableIntStateOf(0) }
    var collectionIndex by remember { mutableIntStateOf(0) }
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }

    // Sidebar state
    var isSidebarFocused by remember { mutableStateOf(false) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 2 else 1) }

    // Stream Selector state
    var showStreamSelector by remember { mutableStateOf(false) }
    var showTrailerPlayer by remember { mutableStateOf(false) }
    KeepScreenOn(active = showTrailerPlayer)
    var pendingAutoPlayRequest by remember { mutableStateOf<PendingAutoPlayRequest?>(null) }
    var autoPlayWaitTick by remember { mutableIntStateOf(0) }

    // Episode Context Menu state
    var showEpisodeContextMenu by remember { mutableStateOf(false) }
    var contextMenuEpisode by remember { mutableStateOf<Episode?>(null) }
    var showSeasonContextMenu by remember { mutableStateOf(false) }
    var contextMenuSeason by remember { mutableIntStateOf(1) }
    var seasonSelectDownAtMs by remember { mutableLongStateOf(0L) }
    var ignoreFirstResumeRefresh by remember(mediaType, mediaId, initialSeason, initialEpisode) { mutableStateOf(true) }

    fun requestFastAutoPlay(imdbId: String?, season: Int?, episode: Int?, startPositionMs: Long?) {
        showStreamSelector = false
        viewModel.loadStreams(imdbId, season, episode)
        autoPlayWaitTick = 0
        pendingAutoPlayRequest = PendingAutoPlayRequest(
            season = season,
            episode = episode,
            startPositionMs = startPositionMs,
            requestedAtMs = SystemClock.elapsedRealtime()
        )
    }

    // Spoiler blur setting
    var spoilerBlurEnabled by remember { mutableStateOf(false) }
    LaunchedEffect(context, currentProfile) {
        runCatching {
            val prefs = context.settingsDataStore.data.first()
            val profileId = currentProfile?.id
            if (profileId != null) {
                spoilerBlurEnabled = prefs[booleanPreferencesKey("profile_${profileId}_spoiler_blur")] ?: false
            }
        }
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(mediaType, mediaId, initialSeason, initialEpisode) {
        focusedSection = FocusSection.BUTTONS
        buttonIndex = 0
        episodeIndex = 0
        seasonIndex = 0
        castIndex = 0
        reviewIndex = 0
        similarIndex = 0
        isSidebarFocused = false
        viewModel.loadDetails(mediaType, mediaId, initialSeason, initialEpisode)
    }

    LaunchedEffect(uiState.episodes.size, uiState.totalSeasons, uiState.cast.size, uiState.reviews.size, uiState.similar.size) {
        if (episodeIndex >= uiState.episodes.size) {
            episodeIndex = (uiState.episodes.size - 1).coerceAtLeast(0)
        }
        if (seasonIndex >= uiState.totalSeasons) {
            seasonIndex = (uiState.totalSeasons - 1).coerceAtLeast(0)
        }
        if (castIndex >= uiState.cast.size) {
            castIndex = (uiState.cast.size - 1).coerceAtLeast(0)
        }
        if (reviewIndex >= uiState.reviews.size) {
            reviewIndex = (uiState.reviews.size - 1).coerceAtLeast(0)
        }
        if (similarIndex >= uiState.similar.size) {
            similarIndex = (uiState.similar.size - 1).coerceAtLeast(0)
        }
    }

    // Keep watched badges and continue target fresh when returning from player.
    DisposableEffect(lifecycleOwner, mediaType, mediaId) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (ignoreFirstResumeRefresh) {
                    ignoreFirstResumeRefresh = false
                } else {
                    viewModel.refreshAfterPlayerReturn()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        suppressSelectUntilMs = SystemClock.elapsedRealtime() + 150L
    }

    LaunchedEffect(
        pendingAutoPlayRequest,
        uiState.isLoadingStreams,
        uiState.streams,
        uiState.autoPlayMinQuality,
        autoPlayWaitTick
    ) {
        val request = pendingAutoPlayRequest ?: return@LaunchedEffect

        val validStreams = uiState.streams.filter(::isAutoPlayableStream)
        val minThreshold = minQualityThreshold(uiState.autoPlayMinQuality)
        val selectedStream = bestAutoPlayStream(validStreams, minThreshold)
        val shouldWaitForSources = shouldWaitForAutoPlaySources(
            isLoadingStreams = uiState.isLoadingStreams,
            selectedStream = selectedStream,
            elapsedMs = SystemClock.elapsedRealtime() - request.requestedAtMs
        )

        when {
            selectedStream != null && !shouldWaitForSources -> {
                onNavigateToPlayer(
                    mediaType,
                    mediaId,
                    request.season,
                    request.episode,
                    uiState.imdbId,
                    selectedStream.url?.takeIf { it.isNotBlank() },
                    selectedStream.addonId.takeIf { it.isNotBlank() },
                    selectedStream.source.takeIf { it.isNotBlank() },
                    request.startPositionMs
                )
                pendingAutoPlayRequest = null
            }
            shouldWaitForSources -> {
                delay(AUTOPLAY_SOURCE_RECHECK_MS)
                autoPlayWaitTick += 1
            }
            uiState.isLoadingStreams -> Unit
            validStreams.isNotEmpty() || uiState.streams.isNotEmpty() -> {
                showStreamSelector = true
                pendingAutoPlayRequest = null
            }
            else -> {
                // When no streams found, show the StreamSelector with its
                // friendly "no addons" / "no sources" empty state instead of
                // navigating to the player which would show a scary error.
                showStreamSelector = true
                pendingAutoPlayRequest = null
            }
        }
    }

    // Sync episodeIndex with initialEpisodeIndex from ViewModel
    LaunchedEffect(uiState.initialEpisodeIndex, uiState.episodes) {
        if (uiState.initialEpisodeIndex > 0 && uiState.episodes.isNotEmpty()) {
            episodeIndex = uiState.initialEpisodeIndex
        }
    }

    // Sync seasonIndex with initialSeasonIndex from ViewModel
    LaunchedEffect(uiState.initialSeasonIndex) {
        if (uiState.initialSeasonIndex > 0) {
            seasonIndex = uiState.initialSeasonIndex
        }
    }

    val currentUiState = rememberUpdatedState(uiState)
    val currentEpisodeIndex = rememberUpdatedState(episodeIndex)

    val onButtonClickRemembered = remember(isMobile, mediaType, mediaId) {
        { idx: Int ->
            val state = currentUiState.value
            val currentEpIdx = currentEpisodeIndex.value
            when (idx) {
                0 -> { // Play
                    val season = if (mediaType == MediaType.TV) {
                        state.playSeason
                            ?: state.episodes.getOrNull(currentEpIdx)?.seasonNumber
                            ?: 1
                    } else null
                    val episode = if (mediaType == MediaType.TV) {
                        state.playEpisode
                            ?: state.episodes.getOrNull(currentEpIdx)?.episodeNumber
                            ?: 1
                    } else null
                    val startPositionMs = if (
                        mediaType == MediaType.TV &&
                        season == state.playSeason &&
                        episode == state.playEpisode
                    ) {
                        state.playPositionMs
                    } else if (mediaType == MediaType.MOVIE) {
                        state.playPositionMs
                    } else null

                    if (!state.autoPlaySingleSource) {
                        // Autoplay OFF → open the source picker; never auto-play.
                        showStreamSelector = true
                        viewModel.loadStreams(state.imdbId, season, episode)
                    } else {
                        // Autoplay ON → go straight to the player; PlayerScreen auto-picks.
                        requestFastAutoPlay(state.imdbId, season, episode, startPositionMs)
                    }
                }
                1 -> { // Sources
                    showStreamSelector = true
                    val ep = state.episodes.getOrNull(currentEpIdx)
                    viewModel.loadStreams(state.imdbId, ep?.seasonNumber, ep?.episodeNumber)
                }
                2 -> { // Trailer
                    state.trailerKey?.let { showTrailerPlayer = true }
                }
                3 -> viewModel.toggleWatched(currentEpIdx)
                4 -> viewModel.toggleWatchlist()
                5 -> { // View Collection — scroll to and focus the collection row on this page
                    focusedSection = FocusSection.COLLECTION
                    collectionIndex = 0
                }
            }
        }
    }

    val onSeasonClickRemembered = remember {
        { idx: Int ->
            seasonIndex = idx
            episodeIndex = 0
            viewModel.loadSeason(idx + 1)
        }
    }

    val onSeasonLongClickRemembered = remember {
        { idx: Int ->
            contextMenuSeason = idx + 1
            showSeasonContextMenu = true
        }
    }

    val onEpisodeClickRemembered = remember(isMobile, mediaType, mediaId) {
        { idx: Int ->
            val state = currentUiState.value
            val ep = state.episodes.getOrNull(idx)
            if (ep != null) {
                episodeIndex = idx
                if (isMobile || !state.autoPlaySingleSource) {
                    showStreamSelector = true
                    viewModel.loadStreams(state.imdbId, ep.seasonNumber, ep.episodeNumber)
                } else {
                    requestFastAutoPlay(state.imdbId, ep.seasonNumber, ep.episodeNumber, null)
                }
            }
        }
    }

    val onCastClickRemembered = remember {
        { idx: Int ->
            val member = currentUiState.value.cast.getOrNull(idx)
            if (member != null) {
                viewModel.loadPerson(member.id)
            }
        }
    }

    val onSimilarClickRemembered = remember {
        { idx: Int ->
            val sim = currentUiState.value.similar.getOrNull(idx)
            if (sim != null) {
                onNavigateToDetails(sim.mediaType, sim.id)
            }
        }
    }

    val onCollectionClickRemembered = remember {
        { idx: Int ->
            val item = currentUiState.value.collectionItems.getOrNull(idx)
            if (item != null) {
                onNavigateToDetails(item.mediaType, item.id)
            }
        }
    }

    // D-pad key handler — only used on TV (skipped on mobile/touch devices)
    val dpadRepeatGate = rememberArvioDpadRepeatGate(
        horizontalMinRepeatIntervalMs = 80L,
        verticalMinRepeatIntervalMs = 112L
    )
    val keyModifier = if (isMobile) Modifier else Modifier.onPreviewKeyEvent { event ->
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
                if (
                    event.type == KeyEventType.KeyDown &&
                    (event.key == Key.DirectionUp || event.key == Key.DirectionDown) &&
                    event.nativeKeyEvent.repeatCount > 0
                ) {
                    return@onPreviewKeyEvent true
                }
                if (event.type == KeyEventType.KeyDown) {
                    // Check if any modal is showing
                    if (showStreamSelector || showEpisodeContextMenu || showSeasonContextMenu || uiState.showPersonModal) {
                        return@onPreviewKeyEvent false // Let the modal handle it
                    }

                    val isRtl = isRtlLayoutDirection
                    val actualKey = event.key
                    val logicalKey = if (isRtl) {
                        when (actualKey) {
                            Key.DirectionLeft -> Key.DirectionRight
                            Key.DirectionRight -> Key.DirectionLeft
                            else -> actualKey
                        }
                    } else actualKey

                    when (logicalKey) {
                        Key.Back, Key.Escape -> {
                            if (showTrailerPlayer) { showTrailerPlayer = false; true }
                            else { onBack(); true }
                        }
                        Key.DirectionLeft -> {
                            if (isSidebarFocused) {
                                if (sidebarFocusIndex > 0) {
                                    sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                }
                                true
                            } else {
                                // Check if at leftmost item in any section - go to sidebar
                                val atLeftmost = when (focusedSection) {
                                    FocusSection.BUTTONS -> buttonIndex == 0
                                    FocusSection.EPISODES -> episodeIndex == 0
                                    FocusSection.SEASONS -> seasonIndex == 0
                                    FocusSection.CAST -> castIndex == 0
                                    FocusSection.REVIEWS -> reviewIndex == 0
                                    FocusSection.SIMILAR -> similarIndex == 0
                                    FocusSection.COLLECTION -> collectionIndex == 0
                                }
                                if (atLeftmost) {
                                    true
                                } else {
                                    handleLeft(
                                        focusedSection, buttonIndex, episodeIndex, seasonIndex, castIndex, reviewIndex, similarIndex, collectionIndex,
                                        { buttonIndex = it }, { episodeIndex = it }, { seasonIndex = it },
                                        { castIndex = it }, { reviewIndex = it }, { similarIndex = it },
                                        { collectionIndex = it }
                                    )
                                }
                            }
                        }
                        Key.DirectionRight -> {
                            if (isSidebarFocused) {
                                if (sidebarFocusIndex < maxSidebarIndex) {
                                    sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                                }
                                true
                            } else {
                                handleRight(
                                    focusedSection, buttonIndex, episodeIndex, seasonIndex, castIndex, reviewIndex, similarIndex, collectionIndex,
                                    uiState, { buttonIndex = it }, { episodeIndex = it }, { seasonIndex = it },
                                    { castIndex = it }, { reviewIndex = it }, { similarIndex = it },
                                    { collectionIndex = it }
                                )
                            }
                        }
                        Key.DirectionUp -> {
                            if (isSidebarFocused) {
                                true
                            } else {
                                // Navigation: BUTTONS -> SEASONS -> EPISODES -> CAST -> REVIEWS -> SIMILAR -> COLLECTION
                                val isTV = mediaType == MediaType.TV
                                val hasEpisodes = uiState.episodes.isNotEmpty()
                                val hasCast = uiState.cast.isNotEmpty()
                                val hasReviews = uiState.reviews.isNotEmpty()
                                val hasSimilar = uiState.similar.isNotEmpty()
                                val hasCollection = uiState.collectionItems.isNotEmpty()
                                focusedSection = when (focusedSection) {
                                    FocusSection.BUTTONS -> {
                                        isSidebarFocused = true
                                        FocusSection.BUTTONS
                                    }
                                    FocusSection.SEASONS -> FocusSection.BUTTONS
                                    FocusSection.EPISODES -> {
                                        if (uiState.totalSeasons > 1) FocusSection.SEASONS else FocusSection.BUTTONS
                                    }
                                    FocusSection.CAST -> {
                                        if (isTV) {
                                            when {
                                                hasEpisodes -> FocusSection.EPISODES
                                                uiState.totalSeasons > 1 -> FocusSection.SEASONS
                                                else -> FocusSection.BUTTONS
                                            }
                                        } else FocusSection.BUTTONS
                                    }
                                    FocusSection.REVIEWS -> if (hasCast) FocusSection.CAST else FocusSection.BUTTONS
                                    FocusSection.SIMILAR -> {
                                        if (hasCollection) FocusSection.COLLECTION
                                        else if (hasReviews) FocusSection.REVIEWS
                                        else if (hasCast) FocusSection.CAST
                                        else FocusSection.BUTTONS
                                    }
                                    FocusSection.COLLECTION -> {
                                        if (hasReviews) FocusSection.REVIEWS
                                        else if (hasCast) FocusSection.CAST
                                        else FocusSection.BUTTONS
                                    }
                                }
                                true
                            }
                        }
                        Key.DirectionDown -> {
                            if (isSidebarFocused) {
                                isSidebarFocused = false
                                true
                            } else {
                                // Navigation: BUTTONS -> SEASONS -> EPISODES -> CAST -> REVIEWS -> SIMILAR -> COLLECTION
                                val isTV = mediaType == MediaType.TV
                                val hasEpisodes = uiState.episodes.isNotEmpty()
                                val hasSeasons = uiState.totalSeasons > 1
                                val hasCast = uiState.cast.isNotEmpty()
                                val hasReviews = uiState.reviews.isNotEmpty()
                                val hasSimilar = uiState.similar.isNotEmpty()
                                val hasCollection = uiState.collectionItems.isNotEmpty()
                                focusedSection = when (focusedSection) {
                                    FocusSection.BUTTONS -> {
                                        if (isTV && hasSeasons) FocusSection.SEASONS
                                        else if (isTV && hasEpisodes) FocusSection.EPISODES
                                        else if (hasCast) FocusSection.CAST
                                        else if (hasReviews) FocusSection.REVIEWS
                                        else if (hasCollection) FocusSection.COLLECTION
                                        else if (hasSimilar) FocusSection.SIMILAR
                                        else FocusSection.BUTTONS
                                    }
                                    FocusSection.SEASONS -> {
                                        if (hasEpisodes) FocusSection.EPISODES
                                        else if (hasCast) FocusSection.CAST
                                        else if (hasReviews) FocusSection.REVIEWS
                                        else if (hasCollection) FocusSection.COLLECTION
                                        else if (hasSimilar) FocusSection.SIMILAR
                                        else FocusSection.SEASONS
                                    }
                                    FocusSection.EPISODES -> {
                                        if (hasCast) FocusSection.CAST
                                        else if (hasReviews) FocusSection.REVIEWS
                                        else if (hasCollection) FocusSection.COLLECTION
                                        else if (hasSimilar) FocusSection.SIMILAR
                                        else FocusSection.EPISODES
                                    }
                                    FocusSection.CAST -> {
                                        if (hasReviews) FocusSection.REVIEWS
                                        else if (hasCollection) FocusSection.COLLECTION
                                        else if (hasSimilar) FocusSection.SIMILAR
                                        else FocusSection.CAST
                                    }
                                    FocusSection.REVIEWS -> {
                                        if (hasCollection) FocusSection.COLLECTION
                                        else if (hasSimilar) FocusSection.SIMILAR
                                        else FocusSection.REVIEWS
                                    }
                                    FocusSection.COLLECTION -> {
                                        if (hasSimilar) FocusSection.SIMILAR else FocusSection.COLLECTION
                                    }
                                    FocusSection.SIMILAR -> FocusSection.SIMILAR
                                }
                                true
                            }
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            if (!isSidebarFocused && focusedSection == FocusSection.SEASONS) {
                                if (seasonSelectDownAtMs == 0L) {
                                    seasonSelectDownAtMs = SystemClock.elapsedRealtime()
                                }
                                return@onPreviewKeyEvent true
                            }
                            if (isSidebarFocused) {
                                if (hasProfile && sidebarFocusIndex == 0) {
                                    onSwitchProfile()
                                } else {
                                    when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) {
                                        SidebarItem.SEARCH -> onNavigateToSearch()
                                        SidebarItem.HOME -> onNavigateToHome()
                                        SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                        SidebarItem.TV -> onNavigateToTv()
                                        SidebarItem.SETTINGS -> onNavigateToSettings()
                                        null -> Unit
                                    }
                                }
                                return@onPreviewKeyEvent true
                            }
                            when (focusedSection) {
                                FocusSection.BUTTONS -> {
                                    when (buttonIndex) {
                                        0 -> { // Play - Auto-play highest quality source
                                            val season = if (mediaType == MediaType.TV) {
                                                uiState.playSeason
                                                    ?: uiState.episodes.getOrNull(episodeIndex)?.seasonNumber
                                                    ?: 1
                                            } else null
                                            val episode = if (mediaType == MediaType.TV) {
                                                uiState.playEpisode
                                                    ?: uiState.episodes.getOrNull(episodeIndex)?.episodeNumber
                                                    ?: 1
                                            } else null
                                            val startPositionMs = if (
                                                mediaType == MediaType.TV &&
                                                season == uiState.playSeason &&
                                                episode == uiState.playEpisode
                                            ) {
                                                uiState.playPositionMs
                                            } else if (mediaType == MediaType.MOVIE) {
                                                uiState.playPositionMs
                                            } else null

                                            if (!uiState.autoPlaySingleSource) {
                                                // Autoplay OFF → open the source picker; never auto-play.
                                                showStreamSelector = true
                                                viewModel.loadStreams(uiState.imdbId, season, episode)
                                            } else {
                                                // Autoplay ON: pick a concrete stream first, then open PlayerScreen.
                                                requestFastAutoPlay(uiState.imdbId, season, episode, startPositionMs)
                                            }
                                        }
                                        1 -> { // Sources - Show StreamSelector for manual selection
                                            showStreamSelector = true
                                            // Pass the currently focused episode for TV shows
                                            val ep = uiState.episodes.getOrNull(episodeIndex)
                                            viewModel.loadStreams(uiState.imdbId, ep?.seasonNumber, ep?.episodeNumber)
                                        }
                                        2 -> { // Trailer
                                            uiState.trailerKey?.let {
                                                showTrailerPlayer = true
                                            }
                                        }
                                        3 -> viewModel.toggleWatched(episodeIndex)
                                        4 -> viewModel.toggleWatchlist()
                                        5 -> { // View Collection — scroll to and focus the collection row
                                            focusedSection = FocusSection.COLLECTION
                                            collectionIndex = 0
                                        }
                                    }
                                }
                                FocusSection.EPISODES -> {
                                    val ep = uiState.episodes.getOrNull(episodeIndex)
                                    if (ep != null) {
                                        if (!uiState.autoPlaySingleSource) {
                                            showStreamSelector = true
                                            viewModel.loadStreams(uiState.imdbId, ep.seasonNumber, ep.episodeNumber)
                                        } else {
                                            requestFastAutoPlay(uiState.imdbId, ep.seasonNumber, ep.episodeNumber, null)
                                        }
                                    }
                                }
                                FocusSection.SEASONS -> {
                                    episodeIndex = 0
                                    viewModel.loadSeason(seasonIndex + 1)
                                }
                                FocusSection.CAST -> {
                                    val member = uiState.cast.getOrNull(castIndex)
                                    if (member != null) {
                                        viewModel.loadPerson(member.id)
                                    }
                                }
                                FocusSection.REVIEWS -> {
                                    // Reviews don't have an action on Enter, just focus
                                }
                                FocusSection.SIMILAR -> {
                                    val similar = uiState.similar.getOrNull(similarIndex)
                                    if (similar != null) {
                                        onNavigateToDetails(similar.mediaType, similar.id)
                                    }
                                }
                                FocusSection.COLLECTION -> {
                                    val collectionItem = uiState.collectionItems.getOrNull(collectionIndex)
                                    if (collectionItem != null) {
                                        onNavigateToDetails(collectionItem.mediaType, collectionItem.id)
                                    }
                                }
                            }
                            true
                        }
                        // Long press or menu key for context menu
                        Key.Menu -> {
                            if (focusedSection == FocusSection.EPISODES) {
                                contextMenuEpisode = uiState.episodes.getOrNull(episodeIndex)
                                showEpisodeContextMenu = true
                            }
                            true
                        }
                        else -> false
                    }
                } else if (event.type == KeyEventType.KeyUp && (event.key == Key.Enter || event.key == Key.DirectionCenter)) {
                    if (!isSidebarFocused && focusedSection == FocusSection.SEASONS && seasonSelectDownAtMs > 0L) {
                        val heldMs = SystemClock.elapsedRealtime() - seasonSelectDownAtMs
                        seasonSelectDownAtMs = 0L
                        if (heldMs >= 900L) {
                            contextMenuSeason = seasonIndex + 1
                            showSeasonContextMenu = true
                        } else {
                            episodeIndex = 0
                            viewModel.loadSeason(seasonIndex + 1)
                        }
                        true
                    } else {
                        seasonSelectDownAtMs = 0L
                        false
                    }
                } else false
            }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            .focusRequester(focusRequester)
            .focusable()
            .then(keyModifier)
    ) {
        // Main content - full screen with sidebar overlay (same as HomeScreen)
        if (uiState.isLoading || uiState.item == null) {
            // Use skeleton loader for better UX
            SkeletonDetailsPage(
                isTV = mediaType == MediaType.TV,
                isMobile = isMobile,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            uiState.item?.let { item ->
                DetailsContent(
                    item = item,
                    logoUrl = uiState.logoUrl,
                    episodes = uiState.episodes,
                    totalSeasons = uiState.totalSeasons,
                    currentSeason = uiState.currentSeason,
                    cast = uiState.cast,
                    reviews = uiState.reviews,
                    similar = uiState.similar,
                    similarLogoUrls = uiState.similarLogoUrls,
                    collectionItems = uiState.collectionItems,
                    collectionName = uiState.collectionName,
                    hasCollectionAction = uiState.collectionId != null,
                    collectionIndex = collectionIndex,
                    focusedSection = focusedSection,
                    buttonIndex = buttonIndex,
                    episodeIndex = episodeIndex,
                    seasonIndex = seasonIndex,
                    castIndex = castIndex,
                    reviewIndex = reviewIndex,
                    similarIndex = similarIndex,
                    isInWatchlist = uiState.isInWatchlist,
                    genres = uiState.genres,
                    budget = uiState.budget,
                    seasonProgress = uiState.seasonProgress,
                    playLabel = uiState.playLabel,
                    hasTrailer = uiState.trailerKey != null,
                    contentHasFocus = !isSidebarFocused,
                    usePosterCards = usePosterCards,
                    isMobile = isMobile,
                    spoilerBlurEnabled = spoilerBlurEnabled,
                    onBack = onBack,
                    onButtonClick = onButtonClickRemembered,
                    onSeasonClick = onSeasonClickRemembered,
                    onSeasonLongClick = onSeasonLongClickRemembered,
                    onEpisodeClick = onEpisodeClickRemembered,
                    onCastClick = onCastClickRemembered,
                    onSimilarClick = onSimilarClickRemembered,
                    onCollectionClick = onCollectionClickRemembered
                )
            }
        }

        if (!LocalDeviceType.current.isTouchDevice()) {
            AppTopBar(
                selectedItem = SidebarItem.HOME,
                isFocused = isSidebarFocused,
                focusedIndex = sidebarFocusIndex,
                profile = currentProfile
            )
        }

        // Person Modal
        PersonModal(
            isVisible = uiState.showPersonModal,
            person = uiState.selectedPerson,
            isLoading = uiState.isLoadingPerson,
            onClose = { viewModel.closePersonModal() },
            onMediaClick = { type, id ->
                viewModel.closePersonModal()
                onNavigateToDetails(type, id)
            }
        )

        // In-app Trailer Player (fullscreen overlay)
        if (showTrailerPlayer && uiState.trailerKey != null) {
            BackHandler { showTrailerPlayer = false }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .zIndex(50f)
                    .clickable { showTrailerPlayer = false }
            ) {
                TrailerPlayer(
                    youtubeKey = uiState.trailerKey!!,
                    modifier = Modifier.fillMaxSize(),
                    delayMs = 0L,
                    volume = 1f
                )
                // Close button for touch devices
                if (isMobile) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 48.dp, end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.6f))
                            .clickable { showTrailerPlayer = false },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.close),
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        // Stream Selector Modal
        StreamSelector(
            isVisible = showStreamSelector,
            streams = uiState.streams,
            selectedStream = null,
            isLoading = uiState.isLoadingStreams,
            hasStreamingAddons = uiState.hasStreamingAddons,
            addonOrderedIds = uiState.addonOrderedIds,
            completedAddons = uiState.completedAddons,
            totalAddons = uiState.totalAddons,
            streamSearchStartTime = uiState.streamSearchStartTime,
            pluginScrapersLoading = uiState.pluginScrapersLoading,
            loadingPluginNames = uiState.loadingPluginNames,
            onFocusedStream = { stream ->
                viewModel.prewarmStreamsAround(stream, uiState.streams)
            },
            onSelect = { stream ->
                if (isPendingDebridStream(stream)) {
                    viewModel.showToast(
                        context.getString(R.string.details_toast_debrid_downloading),
                        ToastType.ERROR
                    )
                    return@StreamSelector
                }
                showStreamSelector = false
                val ep = uiState.episodes.getOrNull(episodeIndex)
                onNavigateToPlayer(
                    mediaType, mediaId,
                    ep?.seasonNumber, ep?.episodeNumber,
                    uiState.imdbId,
                    stream.url?.takeIf { it.isNotBlank() },
                    stream.addonId.takeIf { it.isNotBlank() },
                    stream.source.takeIf { it.isNotBlank() },
                    null
                )
            },
            onClose = { showStreamSelector = false }
        )

        // Episode Context Menu
        contextMenuEpisode?.let { episode ->
            EpisodeContextMenu(
                isVisible = showEpisodeContextMenu,
                episodeName = episode.name,
                seasonEpisode = stringResource(R.string.details_episode_code_short, episode.seasonNumber, episode.episodeNumber),
                isWatched = episode.isWatched,
                onPlay = {
                    showEpisodeContextMenu = false
                    requestFastAutoPlay(uiState.imdbId, episode.seasonNumber, episode.episodeNumber, null)
                },
                onSelectSource = {
                    showEpisodeContextMenu = false
                    showStreamSelector = true
                    viewModel.loadStreams(uiState.imdbId, episode.seasonNumber, episode.episodeNumber)
                },
                onToggleWatched = {
                    viewModel.markEpisodeWatched(
                        episode.seasonNumber,
                        episode.episodeNumber,
                        !episode.isWatched
                    )
                },
                onDismiss = {
                    showEpisodeContextMenu = false
                    contextMenuEpisode = null
                }
            )
        }

        SeasonContextMenu(
            isVisible = showSeasonContextMenu,
            seasonNumber = contextMenuSeason,
            onMarkSeasonWatched = {
                viewModel.markSeasonWatched(contextMenuSeason)
            },
            onMarkSeasonUnwatched = {
                viewModel.markSeasonUnwatched(contextMenuSeason)
            },
            onDismiss = {
                showSeasonContextMenu = false
            }
        )

        // Toast notifications
        uiState.toastMessage?.let { message ->
            Toast(
                message = message,
                type = when (uiState.toastType) {
                    com.arflix.tv.ui.screens.details.ToastType.SUCCESS -> com.arflix.tv.ui.components.ToastType.SUCCESS
                    com.arflix.tv.ui.screens.details.ToastType.ERROR -> com.arflix.tv.ui.components.ToastType.ERROR
                    com.arflix.tv.ui.screens.details.ToastType.INFO -> com.arflix.tv.ui.components.ToastType.INFO
                },
                isVisible = true,
                durationMs = if (uiState.toastType == com.arflix.tv.ui.screens.details.ToastType.ERROR) 8000 else 4000,
                onDismiss = { viewModel.dismissToast() }
            )
        }
    }
}

private enum class FocusSection {
    BUTTONS, EPISODES, SEASONS, CAST, REVIEWS, SIMILAR, COLLECTION
}

private data class PendingAutoPlayRequest(
    val season: Int?,
    val episode: Int?,
    val startPositionMs: Long?,
    val requestedAtMs: Long
)

private fun handleLeft(
    section: FocusSection,
    buttonIdx: Int, episodeIdx: Int, seasonIdx: Int, castIdx: Int, reviewIdx: Int, similarIdx: Int,
    collectionIdx: Int,
    setButton: (Int) -> Unit, setEpisode: (Int) -> Unit, setSeason: (Int) -> Unit,
    setCast: (Int) -> Unit, setReview: (Int) -> Unit, setSimilar: (Int) -> Unit,
    setCollection: (Int) -> Unit
): Boolean {
    when (section) {
        FocusSection.BUTTONS -> if (buttonIdx > 0) setButton(buttonIdx - 1)
        FocusSection.EPISODES -> if (episodeIdx > 0) setEpisode(episodeIdx - 1)
        FocusSection.SEASONS -> if (seasonIdx > 0) setSeason(seasonIdx - 1)
        FocusSection.CAST -> if (castIdx > 0) setCast(castIdx - 1)
        FocusSection.REVIEWS -> if (reviewIdx > 0) setReview(reviewIdx - 1)
        FocusSection.SIMILAR -> if (similarIdx > 0) setSimilar(similarIdx - 1)
        FocusSection.COLLECTION -> if (collectionIdx > 0) setCollection(collectionIdx - 1)
    }
    return true
}

private fun handleRight(
    section: FocusSection,
    buttonIdx: Int, episodeIdx: Int, seasonIdx: Int, castIdx: Int, reviewIdx: Int, similarIdx: Int,
    collectionIdx: Int,
    uiState: DetailsUiState,
    setButton: (Int) -> Unit, setEpisode: (Int) -> Unit, setSeason: (Int) -> Unit,
    setCast: (Int) -> Unit, setReview: (Int) -> Unit, setSimilar: (Int) -> Unit,
    setCollection: (Int) -> Unit
): Boolean {
    when (section) {
        FocusSection.BUTTONS -> {
            val maxButton = if (uiState.collectionId != null) 5 else 4
            if (buttonIdx < maxButton) setButton(buttonIdx + 1)
        }
        FocusSection.EPISODES -> if (episodeIdx < uiState.episodes.size - 1) setEpisode(episodeIdx + 1)
        FocusSection.SEASONS -> if (seasonIdx < uiState.totalSeasons - 1) setSeason(seasonIdx + 1)
        FocusSection.CAST -> if (castIdx < uiState.cast.size - 1) setCast(castIdx + 1)
        FocusSection.REVIEWS -> if (reviewIdx < uiState.reviews.size - 1) setReview(reviewIdx + 1)
        FocusSection.SIMILAR -> if (similarIdx < uiState.similar.size - 1) setSimilar(similarIdx + 1)
        FocusSection.COLLECTION -> if (collectionIdx < uiState.collectionItems.size - 1) setCollection(collectionIdx + 1)
    }
    return true
}



@Composable
private fun DetailsContent(
    item: MediaItem,
    logoUrl: String?,
    episodes: List<Episode>,
    totalSeasons: Int,
    currentSeason: Int,
    cast: List<CastMember>,
    reviews: List<Review>,
    similar: List<MediaItem>,
    similarLogoUrls: Map<String, String>,
    collectionItems: List<MediaItem> = emptyList(),
    collectionName: String? = null,
    hasCollectionAction: Boolean = collectionItems.isNotEmpty(),
    collectionIndex: Int = 0,
    focusedSection: FocusSection,
    buttonIndex: Int,
    episodeIndex: Int,
    seasonIndex: Int,
    castIndex: Int,
    reviewIndex: Int,
    similarIndex: Int,
    isInWatchlist: Boolean,
    genres: List<String> = emptyList(),
    budget: String? = null,
    seasonProgress: Map<Int, Pair<Int, Int>> = emptyMap(),
    playLabel: String? = null,
    hasTrailer: Boolean = false,
    contentHasFocus: Boolean = true,
    usePosterCards: Boolean = false,
    isMobile: Boolean = false,
    // Persistent back callback used by the phone-layout back button overlay
    // (issue #43). No-op by default so tablet/TV callers don't need to pass it.
    onBack: () -> Unit = {},
    onButtonClick: (Int) -> Unit = {},
    onSeasonClick: (Int) -> Unit = {},
    onSeasonLongClick: ((Int) -> Unit)? = null,
    onEpisodeClick: (Int) -> Unit = {},
    onCastClick: (Int) -> Unit = {},
    spoilerBlurEnabled: Boolean = false,
    onSimilarClick: (Int) -> Unit = {},
    onCollectionClick: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    val metadataLogoImageLoader = context.imageLoader
    val focusSectionForUi = if (contentHasFocus) focusedSection else null
    // === PREMIUM LAYERED TEXT SHADOWS ===
    val textShadow = Shadow(
        color = Color.Black.copy(alpha = 0.9f),
        offset = Offset(0f, 2f),
        blurRadius = 8f  // Soft spread shadow for better readability
    )

    // ===================== MOBILE LAYOUT =====================
    if (isMobile) {
        val configuration = LocalConfiguration.current
        val screenHeightDp = configuration.screenHeightDp.dp
        val backdropHeight = (screenHeightDp * 0.53f).coerceAtLeast(400.dp)
        val mobileScrollState = rememberScrollState()
        val density = LocalDensity.current
        var stickyThreshold by remember { mutableStateOf(-1f) }
        val topBarAlpha by remember {
            derivedStateOf {
                if (stickyThreshold >= 0f && mobileScrollState.value > stickyThreshold) {
                    val overscroll = mobileScrollState.value - stickyThreshold
                    val maxOverscroll = 150f
                    (overscroll / maxOverscroll).coerceIn(0f, 1f)
                } else {
                    0f
                }
            }
        }

        val tvSeriesLabel = stringResource(R.string.details_label_tv_series)
        val movieLabel = stringResource(R.string.movie)
        val genreText = genres.take(2).map(::formatGenreName).joinToString(" / ").ifBlank {
            if (item.mediaType == MediaType.TV) tvSeriesLabel else movieLabel
        }
        val displayDate = item.year.takeIf { it.isNotBlank() }
            ?: item.releaseDate?.trim()?.takeIf { it.isNotEmpty() }?.let { date ->
                DetailsScreenRegexes.YEAR_REGEX.find(date)?.value ?: date
            }
            ?: ""
        val hasDuration = item.duration.isNotEmpty() && item.duration != "0m"
        val rating = imdbRatingFor(item)
        val ratingValue = parseRatingValue(rating)
        val buttonWatched = if (item.mediaType == MediaType.TV) {
            episodes.getOrNull(episodeIndex)?.isWatched ?: item.isWatched
        } else {
            item.isWatched
        }

        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(mobileScrollState)
            ) {
                // --- Backdrop with gradient ---
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(backdropHeight)
                        .zIndex(10f)
                ) {
                    AsyncImage(
                        model = item.backdrop ?: item.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    // Strong bottom gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colorStops = arrayOf(
                                        0.0f to Color.Transparent,
                                        0.55f to Color.Transparent,
                                        0.8f to Color.Black.copy(alpha = 0.84f),
                                        1.0f to Color.Black
                                    )
                                )
                            )
                    )

                    if (topBarAlpha > 0f) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .fillMaxWidth()
                                .graphicsLayer {
                                    alpha = topBarAlpha
                                    translationY = mobileScrollState.value.toFloat()
                                }
                                .background(Color.Black.copy(alpha = 0.85f))
                        ) {
                            Spacer(modifier = Modifier.statusBarsPadding().height(64.dp))
                        }
                    }

                    // Title and metadata over backdrop bottom
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, bottom = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val statusBarsTop = WindowInsets.statusBars.getTop(density)
                        Box(
                            modifier = Modifier
                                .zIndex(11f)
                                .height(86.dp)
                                .onGloballyPositioned { coords ->
                                    val currentInitialY = coords.positionInWindow().y + mobileScrollState.value
                                    val pinnedY = statusBarsTop - with(density) { 12.dp.toPx() }
                                    val calculatedThreshold = currentInitialY - pinnedY

                                    // Update if uninitialized, or if the layout shifts significantly (e.g. metadata loaded)
                                    // The > 10f check prevents infinite recomposition loops and ignores 1-2px scroll jitter.
                                    if (stickyThreshold < 0f || kotlin.math.abs(calculatedThreshold - stickyThreshold) > 10f) {
                                        stickyThreshold = calculatedThreshold
                                    }
                                }
                                .graphicsLayer {
                                    if (stickyThreshold >= 0f && mobileScrollState.value > stickyThreshold) {
                                        val overscroll = mobileScrollState.value - stickyThreshold
                                        translationY = overscroll

                                        // Smooth scale down to feel like a header
                                        val maxOverscroll = 200f
                                        val progress = (overscroll / maxOverscroll).coerceIn(0f, 1f)
                                        val scale = 1f - (0.28f * progress)
                                        scaleX = scale
                                        scaleY = scale
                                    }
                                }
                        ) {
                            if (logoUrl != null) {
                                AsyncImage(
                                    model = logoUrl,
                                    contentDescription = item.title,
                                    contentScale = ContentScale.Fit,
                                    alignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxWidth(0.78f)
                                        .height(86.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            if (ratingValue > 0f) {
                                DetailsImdbSvgRatingBadge(
                                    rating = rating,
                                    imageLoader = metadataLogoImageLoader,
                                    ratingFontSize = 13,
                                    logoWidth = 34.dp,
                                    logoHeight = 14.dp,
                                    textShadow = textShadow
                                )
                            }
                            if (displayDate.isNotEmpty()) {
                                MobileMetadataSeparator()
                                Text(
                                    text = displayDate,
                                    style = ArflixTypography.caption.copy(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        shadow = textShadow
                                    ),
                                    color = Color.White.copy(alpha = 0.78f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            if (hasDuration) {
                                MobileMetadataSeparator()
                                Text(
                                    text = item.duration,
                                    style = ArflixTypography.caption.copy(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        shadow = textShadow
                                    ),
                                    color = Color.White.copy(alpha = 0.78f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        if (genreText.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = genreText,
                                style = ArflixTypography.caption.copy(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    shadow = textShadow
                                ),
                                color = Color.White.copy(alpha = 0.74f),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth(0.9f)
                            )
                        }
                    }

                }

                // --- Content on black background ---
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(12.dp))

                    // Primary mobile actions
                    val playButtonLabel = if (!playLabel.isNullOrBlank()) playLabel else stringResource(R.string.play)
                    MobileActionButton(
                        icon = Icons.Default.PlayArrow,
                        text = playButtonLabel,
                        isPrimary = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        onClick = { onButtonClick(0) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MobileIconActionButton(
                            icon = Icons.Default.List,
                            contentDescription = stringResource(R.string.sources),
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                            onClick = { onButtonClick(1) }
                        )
                        MobileIconActionButton(
                            icon = Icons.Default.Movie,
                            contentDescription = stringResource(R.string.trailer),
                            enabled = hasTrailer,
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                            onClick = { onButtonClick(2) }
                        )
                        MobileIconActionButton(
                            icon = if (buttonWatched) Icons.Default.Check else Icons.Default.Visibility,
                            contentDescription = if (buttonWatched) stringResource(R.string.watched) else stringResource(R.string.details_btn_mark_watched),
                            isActive = buttonWatched,
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                            onClick = { onButtonClick(3) }
                        )
                        MobileIconActionButton(
                            icon = if (isInWatchlist) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = if (isInWatchlist) stringResource(R.string.details_in_watchlist) else stringResource(R.string.add_to_watchlist),
                            isActive = isInWatchlist,
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                            onClick = { onButtonClick(4) }
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Description
                    Text(
                        text = item.overview,
                        style = ArflixTypography.body.copy(
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = Color.White.copy(alpha = 0.88f),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )

                    // --- TV Show: Season selector & Episodes ---
                    if (item.mediaType == MediaType.TV && episodes.isNotEmpty()) {
                        if (totalSeasons > 1) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = stringResource(R.string.seasons),
                                style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                                color = Color.White.copy(alpha = 0.9f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                (1..totalSeasons).forEachIndexed { index, season ->
                                    val progress = seasonProgress[season]
                                    val currentSeasonProgress = if (season == currentSeason && episodes.isNotEmpty()) {
                                        Pair(episodes.count { it.isWatched }, episodes.size)
                                    } else null
                                    val seasonClick = remember(index, onSeasonClick) { { onSeasonClick(index) } }
                                    val seasonLongClick = remember(index, onSeasonLongClick) {
                                        onSeasonLongClick?.let { callback -> { callback(index) } }
                                    }
                                    SeasonButton(
                                        season = season,
                                        isSelected = season == currentSeason,
                                        isFocused = false,
                                        watchedCount = currentSeasonProgress?.first ?: progress?.first ?: 0,
                                        totalCount = currentSeasonProgress?.second ?: progress?.second ?: 0,
                                        onClick = seasonClick,
                                        onLongClick = seasonLongClick
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(R.string.episodes),
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Episodes LazyRow (outside the inner Column to allow independent horizontal scroll)
                if (item.mediaType == MediaType.TV && episodes.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier.arvioDpadFocusGroup(),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        standardItemsIndexed(
                            episodes,
                            key = { index, ep -> "mob_ep_${ep.seasonNumber}_${ep.episodeNumber}_$index" },
                            contentType = { _, _ -> "episode" }
                        ) { index, episode ->
                            EpisodeCard(
                                episode = episode,
                                isFocused = false,
                                spoilerBlurEnabled = spoilerBlurEnabled,
                                onClick = { onEpisodeClick(index) }
                            )
                        }
                    }
                }

                // Cast section
                if (cast.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = stringResource(R.string.cast),
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    LazyRow(
                        modifier = Modifier.arvioDpadFocusGroup(),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        standardItemsIndexed(
                            cast,
                            key = { index, c -> "mob_cast_${c.id}_$index" },
                            contentType = { _, _ -> "cast" }
                        ) { index, castMember ->
                            CircularCastCard(
                                castMember = castMember,
                                isFocused = false,
                                onClick = { onCastClick(index) }
                            )
                        }
                    }
                }

                // Similar / More Like This section
                if (similar.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = stringResource(R.string.more_like_this),
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    LazyRow(
                        modifier = Modifier.arvioDpadFocusGroup(),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        standardItemsIndexed(
                            similar,
                            key = { index, m -> "mob_sim_${m.mediaType.name}_${m.id}_$index" },
                            contentType = { _, _ -> "similar" }
                        ) { index, mediaItem ->
                            SimilarMediaCard(
                                item = mediaItem,
                                logoImageUrl = similarLogoUrls["${mediaItem.mediaType}_${mediaItem.id}"],
                                usePosterCards = usePosterCards,
                                isFocused = false,
                                onClick = { onSimilarClick(index) }
                            )
                        }
                    }
                }

                // Collection items section — shown when this movie belongs to a TMDB collection
                if (collectionItems.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        val displayName = collectionName ?: stringResource(R.string.more_like_this)
                        Text(
                            text = displayName,
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    LazyRow(
                        modifier = Modifier.arvioDpadFocusGroup(),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        standardItemsIndexed(
                            collectionItems,
                            key = { index, m -> "mob_col_${m.mediaType.name}_${m.id}_$index" },
                            contentType = { _, _ -> "collection" }
                        ) { index, mediaItem ->
                            SimilarMediaCard(
                                item = mediaItem,
                                logoImageUrl = null,
                                usePosterCards = usePosterCards,
                                isFocused = false,
                                onClick = { onCollectionClick(index) }
                            )
                        }
                    }
                }

                // Reviews section
                if (reviews.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = stringResource(R.string.reviews),
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    LazyRow(
                        modifier = Modifier.arvioDpadFocusGroup(),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        standardItemsIndexed(
                            reviews,
                            key = { index, r -> "mob_review_${r.author}_$index" },
                            contentType = { _, _ -> "review" }
                        ) { index, review ->
                            ReviewCard(
                                review = review,
                                isFocused = false
                            )
                        }
                    }
                }

                // Bottom spacing
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Persistent back button for phone users (hidden on tablet/TV).
            // Sits on top of the scrolling Column so it's always reachable even
            // when the system nav bar auto-hides. Issue #43.
            com.arflix.tv.ui.components.MobileBackButton(
                onBack = onBack,
                modifier = Modifier.align(Alignment.TopStart).statusBarsPadding()
            )
        }
        return
    }
    // ===================== END MOBILE LAYOUT =====================

    Box(modifier = Modifier.fillMaxSize()) {
        // Full-screen hero background
        AsyncImage(
            model = item.backdrop ?: item.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // === PREMIUM MULTI-LAYER SCRIM SYSTEM ===

        // Layer 1: Strong left gradient for hero text area (Netflix-style)
        // Uses colorStops with percentages to work on any resolution
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
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
                )
        )

        // Layer 2: Top vignette for clock/status area
        // Uses colorStops with percentages to work on any resolution
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Black.copy(alpha = 0.5f),
                            0.05f to Color.Black.copy(alpha = 0.25f),
                            0.12f to Color.Transparent,
                            1.0f to Color.Transparent
                        )
                    )
                )
        )

        // Layer 3: Bottom floor-fade (starts low, darker at bottom)
        // Uses colorStops with percentages to work on any resolution
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.85f to Color.Transparent,
                            0.92f to Color.Black.copy(alpha = 0.5f),
                            1.0f to Color.Black.copy(alpha = 0.85f)
                        )
                    )
                )
        )

        // Layer 4 removed for performance - radial gradients are expensive on TV

        // Hero metadata positioned above the content rows
        val heroStartPadding = 36.dp
        val heroEndPadding = 400.dp
        val configuration = LocalConfiguration.current
        val hasPosterDetailRails = usePosterCards && (collectionItems.isNotEmpty() || similar.isNotEmpty())
        // Poster details rails need extra vertical room for two title lines,
        // subtitle text, and focus bleed; otherwise the title block clips.
        val minContentRowHeight = if (hasPosterDetailRails) 322.dp else 260.dp
        val maxContentRowHeight = if (hasPosterDetailRails) 350.dp else 330.dp
        val contentRowHeight = (configuration.screenHeightDp * 0.34f).dp.coerceIn(
            minimumValue = minContentRowHeight,
            maximumValue = maxContentRowHeight
        )
        val contentRowBottomPadding = 0.dp
        val contentRowTopPadding = contentRowHeight + contentRowBottomPadding
        val buttonsBottomPadding = contentRowTopPadding - 4.dp
        val heroBottomPadding = buttonsBottomPadding + if (configuration.screenHeightDp < 720) 46.dp else 58.dp

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    bottom = heroBottomPadding,
                    start = heroStartPadding,
                    end = heroEndPadding
                )
        ) {
            Column(verticalArrangement = Arrangement.Bottom) {
                val showInCinema = remember(item.releaseDate, item.mediaType) {
                    isInCinema(item)
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
                        if (logoUrl != null) {
                            AsyncImage(
                                model = logoUrl,
                                contentDescription = item.title,
                                contentScale = ContentScale.Fit,
                                alignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .height(72.dp)
                                    .width(320.dp)
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

                Spacer(modifier = Modifier.height(4.dp))

                val tvSeriesLabel = stringResource(R.string.details_label_tv_series)
                val movieLabel = stringResource(R.string.movie)
                val genreText = genres.take(2).map(::formatGenreName).joinToString(" / ").ifEmpty {
                    if (item.mediaType == MediaType.TV) tvSeriesLabel else movieLabel
                }
                val isCompactHeight = configuration.screenHeightDp < 720
                val displayDate = item.releaseDate?.takeIf { it.isNotEmpty() } ?: item.year
                val hasDuration = item.duration.isNotEmpty() && item.duration != "0m"
                val rating = imdbRatingFor(item)
                val ratingValue = parseRatingValue(rating)
                val hasRatingMetadata = ratingValue > 0f
                val primaryNetworkLogo = item.primaryNetworkLogo?.takeIf { it.isNotBlank() }
                val budgetText = budget?.trim()?.takeIf { it.isNotEmpty() && item.mediaType == MediaType.MOVIE }
                val hasBudgetMetadata = !budgetText.isNullOrBlank()
                val hasSecondaryMetadata = primaryNetworkLogo != null ||
                    hasRatingMetadata ||
                    hasBudgetMetadata
                val overviewMaxHeight = if (isCompactHeight) 68.dp else 72.dp

                val separatorStyle = ArflixTypography.caption.copy(
                    fontSize = 13.sp,
                    shadow = textShadow
                )

                Column(
                    modifier = Modifier.width(360.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
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

                        if (displayDate.isNotEmpty()) {
                            Text(text = "|", style = separatorStyle, color = Color.White.copy(alpha = 0.7f))
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
                        }

                        if (hasDuration) {
                            Text(text = "|", style = separatorStyle, color = Color.White.copy(alpha = 0.7f))
                            Text(
                                text = item.duration,
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
                                val networkLogoRequest = remember(primaryNetworkLogo, context) {
                                    ImageRequest.Builder(context)
                                        .data(primaryNetworkLogo)
                                        .bitmapConfig(Bitmap.Config.ARGB_8888)
                                        .allowRgb565(false)
                                        .build()
                                }
                                AsyncImage(
                                    model = networkLogoRequest,
                                    imageLoader = metadataLogoImageLoader,
                                    contentDescription = stringResource(R.string.details_cd_primary_provider),
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .height(16.dp)
                                        .width(52.dp)
                                )

                                if (hasRatingMetadata || hasBudgetMetadata) {
                                    Text(text = "|", style = separatorStyle, color = Color.White.copy(alpha = 0.58f))
                                }
                            }

                            if (hasRatingMetadata) {
                                DetailsImdbSvgRatingBadge(
                                    rating = rating,
                                    imageLoader = metadataLogoImageLoader,
                                    ratingFontSize = 13,
                                    logoWidth = 34.dp,
                                    logoHeight = 14.dp,
                                    textShadow = textShadow
                                )

                                if (hasBudgetMetadata) {
                                    Text(text = "|", style = separatorStyle, color = Color.White.copy(alpha = 0.58f))
                                }
                            }

                            if (hasBudgetMetadata) {
                                Text(
                                    text = "${stringResource(R.string.budget)} $budgetText",
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

                Spacer(modifier = Modifier.height(6.dp))

                val displayOverview = item.overview

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

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    bottom = buttonsBottomPadding,
                    start = heroStartPadding,
                    end = heroEndPadding
                )
        ) {
            val buttonWatched = if (item.mediaType == MediaType.TV) {
                episodes.getOrNull(episodeIndex)?.isWatched ?: item.isWatched
            } else {
                item.isWatched
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val playButtonLabel = if (!playLabel.isNullOrBlank()) {
                    playLabel
                } else {
                    stringResource(R.string.play)
                }
                Box(modifier = Modifier.clickable { onButtonClick(0) }) {
                    PremiumActionButton(
                        icon = Icons.Default.PlayArrow,
                        text = playButtonLabel,
                        isPrimary = true,
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 0
                    )
                }
                Box(modifier = Modifier.clickable { onButtonClick(1) }) {
                    PremiumActionButton(
                        icon = Icons.Default.List,
                        text = stringResource(R.string.sources),
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 1,
                        isIconOnly = true
                    )
                }
                Box(modifier = Modifier
                    .clickable(enabled = hasTrailer) { onButtonClick(2) }
                    .graphicsLayer { alpha = if (hasTrailer) 1f else 0.4f }
                ) {
                    PremiumActionButton(
                        icon = Icons.Default.Movie,
                        text = stringResource(R.string.trailer),
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 2,
                        isIconOnly = true
                    )
                }
                Box(modifier = Modifier.clickable { onButtonClick(3) }) {
                    PremiumActionButton(
                        icon = if (buttonWatched) Icons.Default.Check else Icons.Default.Visibility,
                        text = if (buttonWatched) stringResource(R.string.watched) else stringResource(R.string.details_btn_mark_watched),
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 3,
                        isActive = buttonWatched,
                        isIconOnly = true
                    )
                }
                Box(modifier = Modifier.clickable { onButtonClick(4) }) {
                    PremiumActionButton(
                        icon = if (isInWatchlist) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        text = stringResource(R.string.watchlist),
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 4,
                        isIconOnly = true,
                        isActive = isInWatchlist
                    )
                }

                // "View Collection" button — only shown when this movie belongs to a TMDB collection
                if (hasCollectionAction) {
                    Box(modifier = Modifier.clickable { onButtonClick(5) }) {
                        PremiumActionButton(
                            icon = Icons.Default.Star,
                            text = stringResource(R.string.view_collection),
                            isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 5,
                            isIconOnly = true
                        )
                    }
                }
            }
        }

        DetailsTvRows(
            modifier = Modifier.align(Alignment.BottomStart),
            item = item,
            episodes = episodes,
            totalSeasons = totalSeasons,
            currentSeason = currentSeason,
            cast = cast,
            reviews = reviews,
            similar = similar,
            similarLogoUrls = similarLogoUrls,
            collectionItems = collectionItems,
            collectionName = collectionName,
            collectionIndex = collectionIndex,
            focusedSection = focusedSection,
            focusSectionForUi = focusSectionForUi,
            episodeIndex = episodeIndex,
            seasonIndex = seasonIndex,
            castIndex = castIndex,
            reviewIndex = reviewIndex,
            similarIndex = similarIndex,
            seasonProgress = seasonProgress,
            usePosterCards = usePosterCards,
            spoilerBlurEnabled = spoilerBlurEnabled,
            contentRowHeight = contentRowHeight,
            contentRowBottomPadding = contentRowBottomPadding,
            configuration = configuration,
            contentHasFocus = contentHasFocus,
            onSeasonClick = onSeasonClick,
            onEpisodeClick = onEpisodeClick,
            onCastClick = onCastClick,
            onSimilarClick = onSimilarClick,
            onCollectionClick = onCollectionClick
        )
    }
}

@Composable
private fun DetailsTvRows(
    modifier: Modifier,
    item: MediaItem,
    episodes: List<Episode>,
    totalSeasons: Int,
    currentSeason: Int,
    cast: List<CastMember>,
    reviews: List<Review>,
    similar: List<MediaItem>,
    similarLogoUrls: Map<String, String>,
    collectionItems: List<MediaItem> = emptyList(),
    collectionName: String? = null,
    collectionIndex: Int = 0,
    focusedSection: FocusSection,
    focusSectionForUi: FocusSection?,
    episodeIndex: Int,
    seasonIndex: Int,
    castIndex: Int,
    reviewIndex: Int,
    similarIndex: Int,
    seasonProgress: Map<Int, Pair<Int, Int>>,
    usePosterCards: Boolean,
    spoilerBlurEnabled: Boolean,
    contentRowHeight: Dp,
    contentRowBottomPadding: Dp,
    configuration: android.content.res.Configuration,
    contentHasFocus: Boolean,
    onSeasonClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
    onCastClick: (Int) -> Unit,
    onSimilarClick: (Int) -> Unit,
    onCollectionClick: (Int) -> Unit = {}
) {
    val contentScrollState = rememberTvLazyListState()
    val detailsStackOffsetPx = remember { Animatable(0f) }
    val density = LocalDensity.current
    val isTV = item.mediaType == MediaType.TV
    val hasEpisodes = isTV && episodes.isNotEmpty()
    val hasSeasons = isTV && totalSeasons > 1
    val hasCast = cast.isNotEmpty()
    val hasReviews = reviews.isNotEmpty()
    val hasSimilar = similar.isNotEmpty()
    val hasCollection = collectionItems.isNotEmpty()

    var idx = 0
    val seasonsIdx = if (hasSeasons) idx.also { idx++ } else -1
    val episodesIdx = if (hasEpisodes) idx.also { idx++ } else -1
    if (hasCast) idx++
    val castIdx = if (hasCast) idx.also { idx++ } else -1
    if (hasReviews) idx++
    val reviewsIdx = if (hasReviews) idx.also { idx++ } else -1
    if (hasCollection) idx++
    val collectionIdx = if (hasCollection) idx.also { idx++ } else -1
    if (hasSimilar) idx++
    val similarIdx = if (hasSimilar) idx.also { idx++ } else -1

    LaunchedEffect(item.mediaType, item.id, currentSeason, hasEpisodes, hasSeasons) {
        contentScrollState.scrollToItem(0, 0)
    }

    LaunchedEffect(focusedSection, contentHasFocus, hasCollection, hasSimilar) {
        if (!contentHasFocus) return@LaunchedEffect

        val targetIndex = when (focusedSection) {
            FocusSection.BUTTONS, FocusSection.EPISODES, FocusSection.SEASONS -> 0
            FocusSection.CAST -> castIdx
            FocusSection.REVIEWS -> reviewsIdx
            FocusSection.COLLECTION -> collectionIdx
            FocusSection.SIMILAR -> similarIdx
        }
        if (targetIndex < 0) return@LaunchedEffect
        val firstFocusableRailIndex = listOf(
            seasonsIdx,
            episodesIdx,
            castIdx,
            reviewsIdx,
            collectionIdx,
            similarIdx
        ).firstOrNull { it >= 0 } ?: -1
        val targetScrollIndex = if (targetIndex == firstFocusableRailIndex && targetIndex > 0) {
            0
        } else {
            targetIndex
        }

        val firstVisible = contentScrollState.firstVisibleItemIndex
        val topClusterMaxIndex = maxOf(episodesIdx, seasonsIdx, 0)
        if (
            focusSectionForUi == FocusSection.BUTTONS ||
            focusSectionForUi == FocusSection.EPISODES ||
            focusSectionForUi == FocusSection.SEASONS
        ) {
            if (firstVisible > topClusterMaxIndex || contentScrollState.firstVisibleItemScrollOffset != 0) {
                val travelPx = with(density) { 96.dp.toPx() }
                detailsStackOffsetPx.stop()
                detailsStackOffsetPx.snapTo(-travelPx)
                contentScrollState.scrollToItem(0, 0)
                detailsStackOffsetPx.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
                )
            }
            return@LaunchedEffect
        }

        val targetAligned = firstVisible == targetScrollIndex &&
            contentScrollState.firstVisibleItemScrollOffset == 0
        if (targetAligned) {
            detailsStackOffsetPx.stop()
            detailsStackOffsetPx.snapTo(0f)
            return@LaunchedEffect
        }

        val direction = if (targetScrollIndex > firstVisible) 1f else -1f
        val travelPx = with(density) { 96.dp.toPx() }
        detailsStackOffsetPx.stop()
        detailsStackOffsetPx.snapTo(direction * travelPx)
        contentScrollState.scrollToItem(targetScrollIndex, 0)
        detailsStackOffsetPx.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
        )
    }

    val contentStartPadding = 12.dp
    val contentOuterStartPadding = 24.dp

    TvLazyColumn(
        state = contentScrollState,
        modifier = modifier
            .fillMaxWidth()
            .height(contentRowHeight)
            .padding(start = 24.dp, bottom = contentRowBottomPadding)
            .graphicsLayer { translationY = detailsStackOffsetPx.value }
            .arvioManualBringIntoViewBoundary()
            .arvioDpadFocusGroup(enableFocusRestorer = false)
            .clipToBounds(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(top = 6.dp)
    ) {
        if (item.mediaType == MediaType.TV && episodes.isNotEmpty()) {
            if (totalSeasons > 1) {
                item {
                    DetailsSeasonRail(
                        totalSeasons = totalSeasons,
                        currentSeason = currentSeason,
                        episodes = episodes,
                        seasonProgress = seasonProgress,
                        focusSectionForUi = focusSectionForUi,
                        seasonIndex = seasonIndex,
                        contentStartPadding = contentStartPadding,
                        contentOuterStartPadding = contentOuterStartPadding,
                        onSeasonClick = onSeasonClick
                    )
                }
            }

            item {
                DetailsEpisodeRail(
                    episodes = episodes,
                    episodeIndex = episodeIndex,
                    focusSectionForUi = focusSectionForUi,
                    configuration = configuration,
                    contentStartPadding = contentStartPadding,
                    contentOuterStartPadding = contentOuterStartPadding,
                    spoilerBlurEnabled = spoilerBlurEnabled,
                    onEpisodeClick = onEpisodeClick
                )
            }
        }

        if (cast.isNotEmpty()) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            item {
                DetailsCastRail(
                    cast = cast,
                    castIndex = castIndex,
                    focusSectionForUi = focusSectionForUi,
                    contentStartPadding = contentStartPadding,
                    contentOuterStartPadding = contentOuterStartPadding,
                    onCastClick = onCastClick
                )
            }
        }

        if (reviews.isNotEmpty()) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            item {
                DetailsReviewRail(
                    reviews = reviews,
                    reviewIndex = reviewIndex,
                    focusSectionForUi = focusSectionForUi,
                    contentStartPadding = contentStartPadding,
                    contentOuterStartPadding = contentOuterStartPadding
                )
            }
        }

        // Collection items row — shown when this movie belongs to a TMDB collection
        if (collectionItems.isNotEmpty()) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            item {
                DetailsCollectionRail(
                    collectionItems = collectionItems,
                    collectionName = collectionName,
                    collectionIndex = collectionIndex,
                    focusSectionForUi = focusSectionForUi,
                    usePosterCards = usePosterCards,
                    contentStartPadding = contentStartPadding,
                    contentOuterStartPadding = contentOuterStartPadding,
                    onCollectionClick = onCollectionClick
                )
            }
        }

        if (similar.isNotEmpty()) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            item {
                DetailsSimilarRail(
                    similar = similar,
                    similarLogoUrls = similarLogoUrls,
                    similarIndex = similarIndex,
                    focusSectionForUi = focusSectionForUi,
                    usePosterCards = usePosterCards,
                    contentStartPadding = contentStartPadding,
                    contentOuterStartPadding = contentOuterStartPadding,
                    onSimilarClick = onSimilarClick
                )
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
private fun DetailsSeasonRail(
    totalSeasons: Int,
    currentSeason: Int,
    episodes: List<Episode>,
    seasonProgress: Map<Int, Pair<Int, Int>>,
    focusSectionForUi: FocusSection?,
    seasonIndex: Int,
    contentStartPadding: Dp,
    contentOuterStartPadding: Dp,
    onSeasonClick: (Int) -> Unit
) {
    val seasonRowState = rememberTvLazyListState()
    val seasonItems = remember(totalSeasons) { (1..totalSeasons).toList() }
    HomeStyleRowAutoScroll(
        rowState = seasonRowState,
        isCurrentRow = focusSectionForUi == FocusSection.SEASONS,
        focusedItemIndex = seasonIndex,
        totalItems = totalSeasons,
        itemWidth = 128.dp,
        itemSpacing = 8.dp
    )

    val seasonFocusIndex by remember(focusSectionForUi, seasonIndex) {
        derivedStateOf {
            if (focusSectionForUi == FocusSection.SEASONS) seasonIndex else -1
        }
    }

    val currentOnSeasonClick = rememberUpdatedState(onSeasonClick)

    TvLazyRow(
        state = seasonRowState,
        modifier = Modifier.arvioDpadFocusGroup(enableFocusRestorer = false),
        contentPadding = PaddingValues(
            start = contentStartPadding,
            end = lockedDetailsRailEndPadding(
                itemWidth = 128.dp,
                startPadding = contentStartPadding,
                outerStartPadding = contentOuterStartPadding,
                minimum = 150.dp
            ),
            top = 6.dp,
            bottom = 6.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(seasonItems, key = { _, s -> s }) { index, season ->
            val progress = seasonProgress[season]
            val currentSeasonProgress = if (season == currentSeason && episodes.isNotEmpty()) {
                Pair(episodes.count { it.isWatched }, episodes.size)
            } else {
                null
            }
            val onClickForSeason = remember(index) {
                { currentOnSeasonClick.value(index) }
            }
            SeasonButton(
                season = season,
                isSelected = season == currentSeason,
                isFocused = focusSectionForUi == FocusSection.SEASONS && index == seasonFocusIndex,
                watchedCount = currentSeasonProgress?.first ?: progress?.first ?: 0,
                totalCount = currentSeasonProgress?.second ?: progress?.second ?: 0,
                onClick = onClickForSeason
            )
        }
    }
}

@Composable
private fun DetailsEpisodeRail(
    episodes: List<Episode>,
    episodeIndex: Int,
    focusSectionForUi: FocusSection?,
    configuration: android.content.res.Configuration,
    contentStartPadding: Dp,
    contentOuterStartPadding: Dp,
    spoilerBlurEnabled: Boolean,
    onEpisodeClick: (Int) -> Unit
) {
    val episodeCardWidth = if (configuration.screenWidthDp < 1400) 292.dp else 300.dp
    val episodeRowState = rememberTvLazyListState()
    val episodeFixedFocus = focusSectionForUi == FocusSection.EPISODES &&
        detailsRailUsesFixedFirstSlotFocus(
            totalItems = episodes.size,
            focusedItemIndex = episodeIndex
        )
    HomeStyleRowAutoScroll(
        rowState = episodeRowState,
        isCurrentRow = focusSectionForUi == FocusSection.EPISODES,
        focusedItemIndex = episodeIndex,
        totalItems = episodes.size,
        itemWidth = episodeCardWidth,
        itemSpacing = 16.dp
    )

    val currentFocusedSection by rememberUpdatedState(focusSectionForUi)
    val currentEpisodeIndex by rememberUpdatedState(episodeIndex)

    val currentOnEpisodeClick = rememberUpdatedState(onEpisodeClick)

    Box(modifier = Modifier.fillMaxWidth()) {
        TvLazyRow(
            state = episodeRowState,
            modifier = Modifier.arvioDpadFocusGroup(enableFocusRestorer = false),
            contentPadding = PaddingValues(
                start = contentStartPadding,
                end = lockedDetailsRailEndPadding(
                    itemWidth = episodeCardWidth,
                    startPadding = contentStartPadding,
                    outerStartPadding = contentOuterStartPadding,
                    minimum = 520.dp
                ),
                top = 6.dp,
                bottom = 6.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                episodes,
                key = { index, ep -> "${ep.seasonNumber}_${ep.episodeNumber}_$index" }
            ) { index, episode ->
                val isFocused = currentFocusedSection == FocusSection.EPISODES && index == currentEpisodeIndex
                val onClickForEpisode = remember(index) {
                    { currentOnEpisodeClick.value(index) }
                }
                EpisodeCard(
                    episode = episode,
                    cardWidth = episodeCardWidth,
                    isFocused = isFocused && !episodeFixedFocus,
                    spoilerBlurEnabled = spoilerBlurEnabled,
                    onClick = onClickForEpisode
                )
            }
        }
        if (episodeFixedFocus) {
            FixedDetailsRailFocusOverlay(
                startPadding = contentStartPadding,
                topPadding = 6.dp,
                width = episodeCardWidth,
                aspectRatio = 16f / 9f
            )
        }
    }
}

@Composable
private fun DetailsCastRail(
    cast: List<CastMember>,
    castIndex: Int,
    focusSectionForUi: FocusSection?,
    contentStartPadding: Dp,
    contentOuterStartPadding: Dp,
    onCastClick: (Int) -> Unit
) {
    val castRowState = rememberTvLazyListState()
    HomeStyleRowAutoScroll(
        rowState = castRowState,
        isCurrentRow = focusSectionForUi == FocusSection.CAST,
        focusedItemIndex = castIndex,
        totalItems = cast.size,
        itemWidth = 90.dp,
        itemSpacing = 16.dp
    )

    val currentOnCastClick = rememberUpdatedState(onCastClick)

    Column {
        Text(
            text = stringResource(R.string.cast),
            style = ArvioSkin.typography.sectionTitle.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(start = contentStartPadding, bottom = 10.dp)
        )

        TvLazyRow(
            state = castRowState,
            modifier = Modifier.arvioDpadFocusGroup(enableFocusRestorer = false),
            contentPadding = PaddingValues(
                start = contentStartPadding,
                end = lockedDetailsRailEndPadding(
                    itemWidth = 90.dp,
                    startPadding = contentStartPadding,
                    outerStartPadding = contentOuterStartPadding,
                    minimum = 120.dp
                ),
                top = 10.dp,
                bottom = 10.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                cast,
                key = { index, c -> "${c.id}_${c.character}_$index" }
            ) { index, castMember ->
                val onClickForCast = remember(index) {
                    { currentOnCastClick.value(index) }
                }
                CircularCastCard(
                    castMember = castMember,
                    isFocused = focusSectionForUi == FocusSection.CAST && index == castIndex,
                    onClick = onClickForCast
                )
            }
        }
    }
}

@Composable
private fun DetailsReviewRail(
    reviews: List<Review>,
    reviewIndex: Int,
    focusSectionForUi: FocusSection?,
    contentStartPadding: Dp,
    contentOuterStartPadding: Dp
) {
    val reviewRowState = rememberTvLazyListState()
    HomeStyleRowAutoScroll(
        rowState = reviewRowState,
        isCurrentRow = focusSectionForUi == FocusSection.REVIEWS,
        focusedItemIndex = reviewIndex,
        totalItems = reviews.size,
        itemWidth = 320.dp,
        itemSpacing = 16.dp
    )

    Column {
        Text(
            text = stringResource(R.string.reviews),
            style = ArvioSkin.typography.sectionTitle.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(start = contentStartPadding, bottom = 10.dp)
        )

        TvLazyRow(
            state = reviewRowState,
            modifier = Modifier.arvioDpadFocusGroup(enableFocusRestorer = false),
            contentPadding = PaddingValues(
                start = contentStartPadding,
                end = lockedDetailsRailEndPadding(
                    itemWidth = 320.dp,
                    startPadding = contentStartPadding,
                    outerStartPadding = contentOuterStartPadding,
                    minimum = 350.dp
                ),
                top = 14.dp,
                bottom = 14.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                reviews,
                key = { index, r -> "${r.id}_$index" }
            ) { index, review ->
                ReviewCard(
                    review = review,
                    isFocused = focusSectionForUi == FocusSection.REVIEWS && index == reviewIndex
                )
            }
        }
    }
}

@Composable
private fun DetailsSimilarRail(
    similar: List<MediaItem>,
    similarLogoUrls: Map<String, String>,
    similarIndex: Int,
    focusSectionForUi: FocusSection?,
    usePosterCards: Boolean,
    contentStartPadding: Dp,
    contentOuterStartPadding: Dp,
    onSimilarClick: (Int) -> Unit
) {
    val similarRowState = rememberTvLazyListState()
    val similarCardWidth = if (usePosterCards) 126.dp else 210.dp
    val similarFocusBleed = if (usePosterCards) 18.dp else 14.dp
    val similarFixedFocus = focusSectionForUi == FocusSection.SIMILAR &&
        detailsRailUsesFixedFirstSlotFocus(
            totalItems = similar.size,
            focusedItemIndex = similarIndex
        )
    HomeStyleRowAutoScroll(
        rowState = similarRowState,
        isCurrentRow = focusSectionForUi == FocusSection.SIMILAR,
        focusedItemIndex = similarIndex,
        totalItems = similar.size,
        itemWidth = similarCardWidth,
        itemSpacing = 14.dp
    )

    val currentOnSimilarClick = rememberUpdatedState(onSimilarClick)

    Column {
        Text(
            text = stringResource(R.string.more_like_this),
            style = ArvioSkin.typography.sectionTitle.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(start = contentStartPadding, bottom = 10.dp)
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            TvLazyRow(
                state = similarRowState,
                modifier = Modifier.arvioDpadFocusGroup(enableFocusRestorer = false),
                contentPadding = PaddingValues(
                    start = contentStartPadding,
                    end = lockedDetailsRailEndPadding(
                        itemWidth = similarCardWidth,
                        startPadding = contentStartPadding,
                        outerStartPadding = contentOuterStartPadding,
                        minimum = if (usePosterCards) 140.dp else 210.dp
                    ),
                    top = similarFocusBleed,
                    bottom = similarFocusBleed,
                ),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                itemsIndexed(
                    similar,
                    key = { index, m -> "${m.mediaType.name}_${m.id}_$index" }
                ) { index, mediaItem ->
                    val onClickForSimilar = remember(index) {
                        { currentOnSimilarClick.value(index) }
                    }
                    SimilarMediaCard(
                        item = mediaItem,
                        logoImageUrl = similarLogoUrls["${mediaItem.mediaType}_${mediaItem.id}"],
                        usePosterCards = usePosterCards,
                        isFocused = focusSectionForUi == FocusSection.SIMILAR && index == similarIndex && !similarFixedFocus,
                        onClick = onClickForSimilar
                    )
                }
            }
            if (similarFixedFocus) {
                FixedDetailsRailFocusOverlay(
                    startPadding = contentStartPadding,
                    topPadding = similarFocusBleed,
                    width = similarCardWidth,
                    aspectRatio = if (usePosterCards) 2f / 3f else 16f / 9f
                )
            }
        }
    }
}

@Composable
private fun DetailsCollectionRail(
    collectionItems: List<MediaItem>,
    collectionName: String?,
    collectionIndex: Int,
    focusSectionForUi: FocusSection?,
    usePosterCards: Boolean,
    contentStartPadding: Dp,
    contentOuterStartPadding: Dp,
    onCollectionClick: (Int) -> Unit
) {
    val collectionRowState = rememberTvLazyListState()
    val collectionCardWidth = if (usePosterCards) 126.dp else 210.dp
    val collectionFocusBleed = if (usePosterCards) 18.dp else 14.dp
    val collectionFixedFocus = focusSectionForUi == FocusSection.COLLECTION &&
        detailsRailUsesFixedFirstSlotFocus(
            totalItems = collectionItems.size,
            focusedItemIndex = collectionIndex
        )
    HomeStyleRowAutoScroll(
        rowState = collectionRowState,
        isCurrentRow = focusSectionForUi == FocusSection.COLLECTION,
        focusedItemIndex = collectionIndex,
        totalItems = collectionItems.size,
        itemWidth = collectionCardWidth,
        itemSpacing = 14.dp
    )

    val currentOnCollectionClick = rememberUpdatedState(onCollectionClick)

    Column {
        val displayName = collectionName ?: stringResource(R.string.more_like_this)
        Text(
            text = displayName,
            style = ArvioSkin.typography.sectionTitle.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(start = contentStartPadding, bottom = 10.dp)
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            TvLazyRow(
                state = collectionRowState,
                modifier = Modifier.arvioDpadFocusGroup(enableFocusRestorer = false),
                contentPadding = PaddingValues(
                    start = contentStartPadding,
                    end = lockedDetailsRailEndPadding(
                        itemWidth = collectionCardWidth,
                        startPadding = contentStartPadding,
                        outerStartPadding = contentOuterStartPadding,
                        minimum = if (usePosterCards) 140.dp else 210.dp
                    ),
                    top = collectionFocusBleed,
                    bottom = collectionFocusBleed,
                ),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                itemsIndexed(
                    collectionItems,
                    key = { index, m -> "col_${m.mediaType.name}_${m.id}_$index" }
                ) { index, mediaItem ->
                    val onClickForCollection = remember(index) {
                        { currentOnCollectionClick.value(index) }
                    }
                    SimilarMediaCard(
                        item = mediaItem,
                        logoImageUrl = null,
                        usePosterCards = usePosterCards,
                        isFocused = focusSectionForUi == FocusSection.COLLECTION && index == collectionIndex && !collectionFixedFocus,
                        onClick = onClickForCollection
                    )
                }
            }
            if (collectionFixedFocus) {
                FixedDetailsRailFocusOverlay(
                    startPadding = contentStartPadding,
                    topPadding = collectionFocusBleed,
                    width = collectionCardWidth,
                    aspectRatio = if (usePosterCards) 2f / 3f else 16f / 9f
                )
            }
        }
    }
}

@Composable
private fun detailsRailIsScrollable(
    totalItems: Int,
    itemWidth: Dp,
    itemSpacing: Dp
): Boolean {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val visibleCapacity = remember(configuration, density, itemWidth, itemSpacing) {
        val availablePx = with(density) {
            (configuration.screenWidthDp.dp - 56.dp - 12.dp).coerceAtLeast(1.dp).roundToPx()
        }
        val itemSpanPx = with(density) { (itemWidth + itemSpacing).roundToPx() }.coerceAtLeast(1)
        ((availablePx + itemSpanPx - 1) / itemSpanPx).coerceAtLeast(1)
    }
    return totalItems > visibleCapacity
}

private fun detailsRailUsesFixedFirstSlotFocus(
    totalItems: Int,
    focusedItemIndex: Int
): Boolean {
    if (focusedItemIndex < 0 || totalItems <= 0) return false
    return totalItems > 1 && focusedItemIndex <= totalItems - 1
}

@Composable
private fun lockedDetailsRailEndPadding(
    itemWidth: Dp,
    startPadding: Dp,
    outerStartPadding: Dp,
    minimum: Dp
): Dp {
    val configuration = LocalConfiguration.current
    return (configuration.screenWidthDp.dp - outerStartPadding - startPadding - itemWidth)
        .coerceAtLeast(minimum)
}

private suspend fun TvLazyListState.animateDetailsScrollDelta(
    deltaPx: Float,
    durationMillis: Int
) {
    if (abs(deltaPx) <= 1f) return
    scroll(scrollPriority = MutatePriority.PreventUserInput) {
        var previousValue = 0f
        animate(
            initialValue = 0f,
            targetValue = deltaPx,
            animationSpec = tween(durationMillis = durationMillis, easing = FastOutSlowInEasing)
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
private fun FixedDetailsRailFocusOverlay(
    startPadding: Dp,
    topPadding: Dp,
    width: Dp,
    aspectRatio: Float
) {
    ArvioFocusableSurface(
        modifier = Modifier
            .padding(start = startPadding, top = topPadding)
            .width(width)
            .aspectRatio(aspectRatio)
            .zIndex(4f),
        shape = rememberArvioCardShape(ArvioSkin.radius.md),
        backgroundColor = Color.Transparent,
        outlineColor = ArvioSkin.colors.focusOutline,
        outlineWidth = 2.5.dp,
        focusedScale = 1f,
        pressedScale = 0.97f,
        animateFocus = false,
        enableSystemFocus = false,
        isFocusedOverride = true
    ) {
        // Fixed focus lane: rows scroll under this ring.
    }
}

@Composable
private fun HomeStyleRowAutoScroll(
    rowState: TvLazyListState,
    isCurrentRow: Boolean,
    focusedItemIndex: Int,
    totalItems: Int,
    itemWidth: androidx.compose.ui.unit.Dp,
    itemSpacing: androidx.compose.ui.unit.Dp
) {
    val density = LocalDensity.current
    val maxFirstIndex = remember(totalItems) {
        (totalItems - 1).coerceAtLeast(0)
    }
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

        val extraOffset = 0

        if (focusedItemIndex == 0 && scrollTargetIndex == 0) {
            rowState.scrollToItem(index = 0, scrollOffset = 0)
            lastScrollIndex = 0
            lastScrollOffset = 0
            return@LaunchedEffect
        }

        if (lastScrollIndex == scrollTargetIndex && lastScrollOffset == extraOffset) return@LaunchedEffect
        if (lastScrollIndex == -1) {
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
            lastScrollIndex = scrollTargetIndex
            lastScrollOffset = extraOffset
            return@LaunchedEffect
        }
        val currentFirst = rowState.firstVisibleItemIndex
        val currentOffset = rowState.firstVisibleItemScrollOffset
        val currentLast = rowState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: currentFirst
        val targetOutsideViewport = focusedItemIndex < currentFirst || focusedItemIndex > currentLast
        val delta = scrollTargetIndex - currentFirst
        if (abs(delta) > 6) {
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (delta != 0 || targetOutsideViewport || lastScrollOffset != extraOffset) {
            val deltaPx = (delta * itemSpanPx) + (extraOffset - currentOffset)
            rowState.animateDetailsScrollDelta(
                deltaPx = deltaPx,
                durationMillis = if (abs(delta) >= 3) 180 else 150
            )
            if (
                rowState.firstVisibleItemIndex != scrollTargetIndex ||
                abs(rowState.firstVisibleItemScrollOffset - extraOffset) > 8
            ) {
                rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
            }
        }
        lastScrollIndex = scrollTargetIndex
        lastScrollOffset = extraOffset
    }
}

private fun imdbRatingFor(item: MediaItem): String {
    val imdbValue = parseRatingValue(item.imdbRating)
    return if (imdbValue > 0f) item.imdbRating else ""
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun DetailsImdbSvgRatingBadge(
    rating: String,
    imageLoader: ImageLoader,
    ratingFontSize: Int,
    logoWidth: Dp,
    logoHeight: Dp,
    textShadow: Shadow
) {
    val context = LocalContext.current
    val imdbLogoUri = remember { "android.resource://com.arvio.tv/${R.raw.logo_imdb_rectangle}" }
    val request = remember(imdbLogoUri, context) {
        ImageRequest.Builder(context)
            .data(imdbLogoUri)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .allowRgb565(false)
            .build()
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        AsyncImage(
            model = request,
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
private fun MobileScoreBadge(
    label: String,
    value: String,
    backgroundColor: Color,
    contentColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(3.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = label,
            style = ArflixTypography.caption.copy(fontSize = 8.sp, fontWeight = FontWeight.Black),
            color = contentColor,
            maxLines = 1
        )
        Text(
            text = value,
            style = ArflixTypography.caption.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold),
            color = contentColor,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MobileMetadataSeparator() {
    Text(
        text = "•",
        style = ArflixTypography.caption.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
        color = Color.White.copy(alpha = 0.42f),
        maxLines = 1
    )
}

/**
 * Mobile action button — labeled, tappable, Netflix-style
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MobileActionButton(
    icon: ImageVector,
    text: String,
    isPrimary: Boolean = false,
    isActive: Boolean = false,
    isOutlined: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(percent = 50)
    val bgColor = when {
        isPrimary -> Color.White
        isOutlined -> Color.Transparent
        isActive -> Color.White.copy(alpha = 0.15f)
        else -> Color.White.copy(alpha = 0.08f)
    }
    val contentColor = if (isPrimary) Color.Black else Color.White.copy(alpha = 0.92f)
    val borderColor = if (isOutlined) Color.White.copy(alpha = if (isActive) 0.55f else 0.22f) else Color.Transparent

    Row(
        modifier = modifier
            .clip(shape)
            .background(bgColor, shape)
            .border(1.dp, borderColor, shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(if (isPrimary) 24.dp else 22.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = ArvioSkin.typography.button.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f, fill = false)
        )
    }
}

@Composable
private fun MobileIconActionButton(
    icon: ImageVector,
    contentDescription: String,
    isActive: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(20.dp)
    val backgroundColor = when {
        !enabled -> Color.White.copy(alpha = 0.04f)
        isActive -> Color.White.copy(alpha = 0.18f)
        else -> Color.White.copy(alpha = 0.08f)
    }
    val contentColor = if (enabled) {
        Color.White.copy(alpha = if (isActive) 0.96f else 0.88f)
    } else {
        Color.White.copy(alpha = 0.3f)
    }
    val borderColor = if (isActive) {
        Color.White.copy(alpha = 0.28f)
    } else {
        Color.White.copy(alpha = 0.12f)
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor, shape)
            .border(1.dp, borderColor, shape)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
    }
}

/**
 * Premium ActionButton with smooth animations and glass morphism effect
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PremiumActionButton(
    icon: ImageVector,
    text: String,
    isFocused: Boolean,
    isPrimary: Boolean = false,
    isIconOnly: Boolean = false,
    isActive: Boolean = false
) {
    val shape = RoundedCornerShape(12.dp)
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val textStyle = ArvioSkin.typography.button.copy(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.3.sp
    )
    val iconSize = if (isIconOnly) 20.dp else 16.dp
    val expandedPadding = 12.dp
    val collapsedPadding = 0.dp
    val labelSpacing = 8.dp
    val labelExtraWidth = 12.dp
    val showLabel = isFocused && text.isNotBlank()

    val labelWidthPx = remember(text, density) {
        if (text.isBlank()) 0 else {
            textMeasurer.measure(AnnotatedString(text), style = textStyle).size.width
        }
    }
    val labelWidthDp = with(density) { labelWidthPx.toDp() }
    val targetPadding = if (showLabel || !isIconOnly) expandedPadding else collapsedPadding
    val horizontalPadding by animateDpAsState(
        targetValue = targetPadding,
        animationSpec = tween(140),
        label = "button_padding"
    )
    val baseWidth = iconSize + targetPadding * 2
    val expandedWidth = baseWidth + labelSpacing + labelWidthDp + labelExtraWidth
    val targetWidth = if (showLabel) expandedWidth else baseWidth
    val animatedWidth by animateDpAsState(
        targetValue = targetWidth,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "button_width"
    )
    val labelAlpha by animateFloatAsState(
        targetValue = if (showLabel) 1f else 0f,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "button_label_alpha"
    )

    // Animated scale for focus
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.05f else 1f,
        animationSpec = androidx.compose.animation.core.spring(dampingRatio = 0.8f, stiffness = 400f),
        label = "button_scale"
    )

    // Resolve accent color for focused button backgrounds
    val accent = resolveAccentColor(fallback = Color.White)

    // Animated background color - button fills with accent when focused
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isFocused && isPrimary -> accent
            isFocused -> accent
            else -> Color.Transparent
        },
        animationSpec = tween(150),
        label = "button_bg"
    )

    // Animated text/icon color - white on accent bg when focused, white otherwise
    // Use dark text for light accent colors (White, Yellow) to ensure contrast
    val contentColor by animateColorAsState(
        targetValue = if (isFocused) {
            val l = 0.299f * accent.red + 0.587f * accent.green + 0.114f * accent.blue
            if (l > 0.5f) Color.Black else Color.White
        } else Color.White.copy(alpha = 0.9f),
        animationSpec = tween(150),
        label = "button_content"
    )

    // Animated border - all non-focused buttons get a subtle border
    val borderAlpha by animateFloatAsState(
        targetValue = 0f,
        animationSpec = tween(150),
        label = "border_alpha"
    )

    val contentAlignment = if (!showLabel) Alignment.Center else Alignment.CenterStart

    Box(
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .width(animatedWidth)
            .background(
                if (isFocused) backgroundColor else Color.Transparent,
                shape
            )
            .then(
                if (borderAlpha > 0f) {
                    Modifier.border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = borderAlpha),
                        shape = shape
                    )
                } else Modifier
            )
            .clipToBounds()
            .padding(horizontal = horizontalPadding, vertical = 8.dp),
        contentAlignment = contentAlignment
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(labelSpacing)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(iconSize)
            )
            if (text.isNotEmpty()) {
                Text(
                    text = text,
                    style = textStyle,
                    color = contentColor,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.graphicsLayer {
                        alpha = labelAlpha
                        translationX = (1f - labelAlpha) * with(density) { 6.dp.toPx() }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun EpisodeCard(
    episode: Episode,
    cardWidth: androidx.compose.ui.unit.Dp = 300.dp,
    isFocused: Boolean,
    spoilerBlurEnabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    val aspectRatio = 16f / 9f
    val context = LocalContext.current
    val density = LocalDensity.current
    val metadataLogoImageLoader = context.imageLoader

    val shape = rememberArvioCardShape(ArvioSkin.radius.md)
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.03f else 1f,
        animationSpec = androidx.compose.animation.core.spring(dampingRatio = 0.8f, stiffness = 400f),
        label = "episode_scale"
    )
    val borderWidth = if (isFocused || scale != 1f) 3.dp else 0.dp

    val imageRequest = remember(episode.stillPath, cardWidth, context, density) {
        val widthPx = with(density) { cardWidth.roundToPx() }
        val heightPx = (widthPx / aspectRatio).toInt().coerceAtLeast(1)
        ImageRequest.Builder(context)
            .data(episode.stillPath)
            .size(widthPx, heightPx)
            .precision(Precision.INEXACT)
            .allowHardware(true)
            .crossfade(false)
            .build()
    }

    val episodeCode = "S${episode.seasonNumber} • E${String.format("%02d", episode.episodeNumber)}"
    val ratingLabel = episode.imdbRating.takeIf { parseRatingValue(it) > 0f }
    val isSpoilerBlurred = spoilerBlurEnabled && !episode.isWatched
    val noSynopsisText = stringResource(R.string.details_no_synopsis)
    val previewText = if (isSpoilerBlurred) {
        ""
    } else {
        episode.overview
            .trim()
            .ifEmpty { noSynopsisText }
    }
    val episodeAirDateLabel = remember(episode.airDate) { formatEpisodeAirDateLabel(episode.airDate) }
    val isEpisodeUnaired = remember(episode.airDate) { isFutureEpisodeAirDate(episode.airDate) }

    val scaleModifier = if (scale != 1f) {
        Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
            transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0.5f, 1f)
        }
    } else {
        Modifier
    }

    ArvioFocusableSurface(
        modifier = Modifier
            .width(cardWidth)
            .aspectRatio(aspectRatio)
            .then(scaleModifier),
        shape = shape,
        backgroundColor = ArvioSkin.colors.surface,
        outlineColor = ArvioSkin.colors.focusOutline,
        outlineWidth = borderWidth,
        focusedScale = 1f,
        pressedScale = 1f,
        enableSystemFocus = false,
        isFocusedOverride = isFocused,
        onClick = onClick,
    ) { _ ->
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = imageRequest,
                contentDescription = episode.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (isSpoilerBlurred && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) Modifier.blur(20.dp) else Modifier
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.18f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.28f),
                                Color.Black.copy(alpha = 0.86f)
                            )
                        )
                    )
            )

            if (isSpoilerBlurred && Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.85f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.details_spoiler),
                        style = ArvioSkin.typography.caption.copy(fontWeight = FontWeight.Bold),
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.55f), RoundedCornerShape(6.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = episodeCode,
                        style = ArvioSkin.typography.caption.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.3.sp
                        ),
                        color = Color.White.copy(alpha = 0.95f),
                        maxLines = 1
                    )
                }
            }

            if (episodeAirDateLabel != null) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 8.dp)
                        .background(Color.Black.copy(alpha = 0.58f), RoundedCornerShape(6.dp))
                        .border(
                            width = 1.dp,
                            color = if (isEpisodeUnaired) Color(0xFF8AD5FF) else Color.White.copy(alpha = 0.24f),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 7.dp, vertical = 3.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isEpisodeUnaired) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            tint = Color(0xFF8AD5FF),
                            modifier = Modifier.size(10.dp)
                        )
                    }
                    Text(
                        text = episodeAirDateLabel,
                        style = ArvioSkin.typography.caption.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = if (isEpisodeUnaired) Color(0xFFBDEBFF) else Color.White.copy(alpha = 0.92f),
                        maxLines = 1
                    )
                }
            }

            ratingLabel?.let { rating ->
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    DetailsImdbSvgRatingBadge(
                        rating = rating,
                        imageLoader = metadataLogoImageLoader,
                        ratingFontSize = 9,
                        logoWidth = 24.dp,
                        logoHeight = 10.dp,
                        textShadow = Shadow(
                            color = Color.Black.copy(alpha = 0.75f),
                            offset = Offset(0f, 1f),
                            blurRadius = 2f
                        )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Text(
                    text = episode.name,
                    style = ArvioSkin.typography.cardTitle.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = previewText,
                    style = ArvioSkin.typography.caption.copy(
                        fontSize = 10.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Color.White.copy(alpha = 0.92f),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (episode.isWatched) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 8.dp, end = 8.dp)
                        .size(16.dp)
                        .background(
                            color = ArvioSkin.colors.watchedGreen.copy(alpha = 0.22f),
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = ArvioSkin.colors.watchedGreen,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ArvioSkin.colors.watchedGreen,
                        modifier = Modifier.size(9.dp)
                    )
                }
            }
        }
    }
}

private fun formatEpisodeAirDateLabel(rawDate: String): String? {
    val value = rawDate.trim()
    if (value.isEmpty()) return null
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        parser.isLenient = false
        val date = parser.parse(value) ?: return value
        SimpleDateFormat("d MMM yyyy", Locale.US).format(date)
    } catch (_: Exception) {
        value
    }
}

private fun isFutureEpisodeAirDate(rawDate: String): Boolean {
    val value = rawDate.trim()
    if (value.isEmpty()) return false
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        parser.isLenient = false
        val date = parser.parse(value) ?: return false
        date.after(Date())
    } catch (_: Exception) {
        false
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SeasonButton(
    season: Int,
    isSelected: Boolean,
    isFocused: Boolean,
    watchedCount: Int = 0,
    totalCount: Int = 0,
    onClick: () -> Unit = {},
    onLongClick: (() -> Unit)? = null
) {
    val shape = RoundedCornerShape(8.dp)
    val backgroundColor = when {
        isFocused -> Color.White
        isSelected -> Color.White.copy(alpha = 0.2f)
        else -> Color.White.copy(alpha = 0.08f)
    }
    val textColor = when {
        isFocused -> Color.Black
        isSelected -> Color.White
        else -> Color.White.copy(alpha = 0.6f)
    }

    val isFullyWatched = totalCount > 0 && watchedCount >= totalCount

    val selectSeasonLabel = stringResource(R.string.details_cd_select_season, season)
    val seasonOptionsLabel = stringResource(R.string.details_cd_season_options)
    val clickModifier = if (onLongClick != null) {
        @OptIn(ExperimentalFoundationApi::class)
        Modifier.combinedClickable(
            onClick = onClick,
            onLongClick = onLongClick,
            role = Role.Button,
            onClickLabel = selectSeasonLabel,
            onLongClickLabel = seasonOptionsLabel
        )
    } else {
        Modifier.clickable(onClick = onClick)
    }

    Row(
        modifier = clickModifier
            .background(backgroundColor, shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "${stringResource(R.string.season_label)} $season",
            style = ArvioSkin.typography.button.copy(
                fontSize = 13.sp,
                fontWeight = if (isFocused || isSelected) FontWeight.Bold else FontWeight.Medium
            ),
            color = textColor
        )

        if (isFullyWatched) {
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(
                        color = ArvioSkin.colors.watchedGreen.copy(alpha = 0.2f),
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = ArvioSkin.colors.watchedGreen,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = ArvioSkin.colors.watchedGreen,
                    modifier = Modifier.size(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CastCard(
    member: CastMember,
    isFocused: Boolean
) {
    val shape = CircleShape
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.08f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "cast_scale"
    )
    val borderWidth = if (isFocused || scale != 1f) 3.dp else 0.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(120.dp)
    ) {
        val scaleModifier = if (scale != 1f) {
            Modifier.graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
        } else {
            Modifier
        }
        ArvioFocusableSurface(
            modifier = Modifier
                .size(100.dp)
                .then(scaleModifier),
            shape = shape,
            backgroundColor = ArvioSkin.colors.surfaceRaised.copy(alpha = 0.65f),
            enableSystemFocus = false,
            isFocusedOverride = isFocused,
            outlineWidth = borderWidth,
            focusedScale = 1f,
            pressedScale = 1f,
            onClick = null,
            glowWidth = 8.dp,
            glowAlpha = 0.18f,
        ) { _ ->
            if (member.profilePath != null) {
                AsyncImage(
                    model = member.profilePath,
                    contentDescription = member.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = member.name.firstOrNull()?.toString().orEmpty(),
                        style = ArvioSkin.typography.sectionTitle,
                        color = ArvioSkin.colors.textMuted
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = member.name,
            style = ArvioSkin.typography.cardTitle,
            color = if (isFocused) ArvioSkin.colors.textPrimary else ArvioSkin.colors.textMuted,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (member.character.isNotEmpty()) {
            Text(
                text = member.character,
                style = ArvioSkin.typography.caption,
                color = ArvioSkin.colors.textMuted.copy(alpha = 0.85f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Circular cast card with premium focus effect matching home screen style
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CircularCastCard(
    castMember: CastMember,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    // Animated scale for focus
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.08f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "cast_scale"
    )

    // Border stays consistent; scale handles the jump
    val borderWidth = if (isFocused || scale != 1f) 3.dp else 0.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(90.dp)
            .clickable { onClick() }
    ) {
        val scaleModifier = if (scale != 1f) {
            Modifier.graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
        } else {
            Modifier
        }
        Box(
            modifier = Modifier
                .size(72.dp)
                .then(scaleModifier)
                .then(
                    if (isFocused) {
                        Modifier.border(
                            width = borderWidth,
                            color = ArvioSkin.colors.focusOutline,
                            shape = CircleShape
                        )
                    } else Modifier
                )
                .clip(CircleShape)
                .background(ArvioSkin.colors.surfaceRaised.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            if (castMember.profilePath != null) {
                AsyncImage(
                    model = castMember.profilePath,
                    contentDescription = castMember.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Placeholder with initials
                Text(
                    text = castMember.name.take(1).uppercase(),
                    style = ArvioSkin.typography.sectionTitle.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Name
        Text(
            text = castMember.name,
            style = ArvioSkin.typography.caption.copy(
                fontSize = 11.sp,
                fontWeight = if (isFocused) FontWeight.Bold else FontWeight.Medium
            ),
            color = if (isFocused) Color.White else Color.White.copy(alpha = 0.8f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // Character name
        if (castMember.character.isNotEmpty()) {
            Text(
                text = castMember.character,
                style = ArvioSkin.typography.caption.copy(fontSize = 10.sp),
                color = Color.White.copy(alpha = 0.5f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Beautiful transparent review card
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ReviewCard(
    review: Review,
    isFocused: Boolean
) {
    val shape = RoundedCornerShape(16.dp)

    // Animated scale for focus
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.08f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "review_scale"
    )

    // Animated border
    val borderAlpha by animateFloatAsState(
        targetValue = if (isFocused) 1f else 0.2f,
        animationSpec = tween(150),
        label = "review_border"
    )

    val scaleModifier = if (scale != 1f) {
        Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
    } else {
        Modifier
    }
    Box(
        modifier = Modifier
            .width(320.dp)
            .height(160.dp)
            .then(scaleModifier)
            .background(
                color = Color.White.copy(alpha = if (isFocused) 0.12f else 0.06f),
                shape = shape
            )
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = if (isFocused) ArvioSkin.colors.focusOutline else Color.White.copy(alpha = borderAlpha),
                shape = shape
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Author row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Author avatar
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(ArvioSkin.colors.surfaceRaised.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    if (review.authorAvatar != null) {
                        AsyncImage(
                            model = review.authorAvatar,
                            contentDescription = review.author,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Text(
                            text = review.author.take(1).uppercase(),
                            style = ArvioSkin.typography.button.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }

                Column {
                    Text(
                        text = review.author,
                        style = ArvioSkin.typography.cardTitle.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    // Rating if available
                    if (review.rating != null && review.rating > 0f) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFF5C518),
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = String.format("%.1f", review.rating),
                                style = ArvioSkin.typography.caption.copy(fontSize = 11.sp),
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }

            // Review content
            Text(
                text = review.content,
                style = ArvioSkin.typography.body.copy(
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                ),
                color = Color.White.copy(alpha = 0.85f),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MetaPill(text: String) {
    Box(
        modifier = Modifier
            .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(6.dp))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text.uppercase(),
            style = ArflixTypography.label,
            color = TextSecondary
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ImdbBadge(rating: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFF5C518), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "IMDb",
                style = ArflixTypography.label,
                color = Color.Black
            )
            Text(
                text = rating,
                style = ArflixTypography.label,
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun OngoingBadge() {
    // Cyan/teal color like webapp
    val cyanColor = Color(0xFF22D3EE)

    Row(
        modifier = Modifier
            .background(cyanColor.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
            .border(1.dp, cyanColor.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Schedule,
            contentDescription = null,
            tint = cyanColor,
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = stringResource(R.string.ongoing).uppercase(),
            style = ArflixTypography.label,
            color = cyanColor
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GenreBadge(genre: String) {
    Box(
        modifier = Modifier
            .background(Pink.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
            .border(1.dp, Pink.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = formatGenreName(genre),
            style = ArflixTypography.label,
            color = Pink
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun LanguageBadge(language: String) {
    // Purple color for language
    val purpleColor = Purple

    Box(
        modifier = Modifier
            .background(purpleColor.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
            .border(1.dp, purpleColor.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = language.uppercase(),
            style = ArflixTypography.label,
            color = purpleColor
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun BudgetBadge(budget: String) {
    // Green color for budget
    val greenColor = Color(0xFF10B981) // Emerald green

    Box(
        modifier = Modifier
            .background(greenColor.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
            .border(1.dp, greenColor.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = "${stringResource(R.string.budget).uppercase()}: $budget",
            style = ArflixTypography.label,
            color = greenColor
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun StatusBadge(status: String) {
    // Different colors based on status
    val (bgColor, textColor) = when {
        status.contains("Return", ignoreCase = true) -> Pair(Color(0xFF22D3EE), Color(0xFF22D3EE)) // Cyan for ongoing
        status.contains("Ended", ignoreCase = true) -> Pair(Color(0xFF6B7280), Color(0xFF6B7280)) // Gray for ended
        status.contains("Cancel", ignoreCase = true) -> Pair(Color(0xFFEF4444), Color(0xFFEF4444)) // Red for canceled
        else -> Pair(Color(0xFF6B7280), Color(0xFF6B7280))
    }

    Box(
        modifier = Modifier
            .background(bgColor.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
            .border(1.dp, bgColor.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = status.uppercase(),
            style = ArflixTypography.label,
            color = textColor
        )
    }
}

/**
 * Similar media card for "More Like This" section - same style as home screen
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SimilarMediaCard(
    item: MediaItem,
    logoImageUrl: String?,
    usePosterCards: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit = {}
) {
    val mediaTypeLabel = if (item.mediaType == MediaType.TV) stringResource(R.string.details_label_tv_series) else stringResource(R.string.movie)
    val yearSuffix = item.year.takeIf { it.isNotBlank() }?.let { " | $it" }.orEmpty()
    MediaCard(
        item = item.copy(subtitle = "$mediaTypeLabel$yearSuffix"),
        width = if (usePosterCards) 126.dp else 210.dp,
        isLandscape = !usePosterCards,
        logoImageUrl = logoImageUrl,
        showProgress = false,
        titleMaxLines = if (usePosterCards) 2 else 1,
        subtitleMaxLines = 1,
        isFocusedOverride = isFocused,
        enableSystemFocus = false,
        onFocused = { },
        onClick = onClick
    )
}
