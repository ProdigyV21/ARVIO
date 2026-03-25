package com.arflix.tv.ui.screens.details

import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed as standardItemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Review
import com.arflix.tv.ui.components.EpisodeContextMenu
import com.arflix.tv.ui.components.SeasonContextMenu
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.PersonModal
import com.arflix.tv.ui.components.PosterCard
import com.arflix.tv.ui.components.rememberCardLayoutMode
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.SkeletonDetailsPage
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape
import com.arflix.tv.ui.theme.AnimationConstants
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.Purple
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.isInCinema
import com.arflix.tv.util.parseRatingValue
import com.arflix.tv.util.extractQuality
import com.arflix.tv.util.getQualityScore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

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
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val usePosterCards = rememberCardLayoutMode() == CardLayoutMode.POSTER
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
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }

    // Sidebar state
    var isSidebarFocused by remember { mutableStateOf(false) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 2 else 1) }

    // Stream Selector state
    var showStreamSelector by remember { mutableStateOf(false) }
    var pendingAutoPlayRequest by remember { mutableStateOf<PendingAutoPlayRequest?>(null) }

    // Episode Context Menu state
    var showEpisodeContextMenu by remember { mutableStateOf(false) }
    var contextMenuEpisode by remember { mutableStateOf<Episode?>(null) }
    var showSeasonContextMenu by remember { mutableStateOf(false) }
    var contextMenuSeason by remember { mutableIntStateOf(1) }
    var seasonSelectDownAtMs by remember { mutableLongStateOf(0L) }
    var ignoreFirstResumeRefresh by remember(mediaType, mediaId, initialSeason, initialEpisode) { mutableStateOf(true) }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(mediaType, mediaId, initialSeason, initialEpisode) {
        viewModel.loadDetails(mediaType, mediaId, initialSeason, initialEpisode)
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
        suppressSelectUntilMs = SystemClock.elapsedRealtime() + 300L
    }

    // ---------------------------------------------------------------------------
    // CRITICAL AUTO-PLAY LOGIC — FIXED
    //
    // qualityScoreForAutoPlay now uses word-boundary Regex (same engine as
    // StreamSelector / SourceInfoOverlay) so "DS4K", "PSEUDO4K", etc. are NOT
    // mis-scored as 4K.  The filter then checks score in minThreshold..maxThreshold
    // so a 1080p cap correctly rejects genuine 4K streams and accepts 1080p.
    // ---------------------------------------------------------------------------
    LaunchedEffect(pendingAutoPlayRequest, uiState.isLoadingStreams, uiState.streams) {
        val request = pendingAutoPlayRequest ?: return@LaunchedEffect
        if (uiState.isLoadingStreams) return@LaunchedEffect

        val validStreams = uiState.streams.filter(::isAutoPlayableStream)
        val minThreshold = minQualityThreshold(uiState.autoPlayMinQuality)
        val maxThreshold = maxQualityThreshold(uiState.autoPlayMaxQuality)

        // Strict range filter — uses hybrid quality detection (filename first, then metadata)
        val filteredStreams = validStreams
            .filter { stream ->
                val score = getQualityScore(stream.extractQuality())
                score in minThreshold..maxThreshold
            }
            .sortedByDescending { getQualityScore(it.extractQuality()) }

        val bestStream = filteredStreams.firstOrNull()

        when {
            bestStream != null && uiState.autoPlaySingleSource -> {
                onNavigateToPlayer(
                    mediaType,
                    mediaId,
                    request.season,
                    request.episode,
                    uiState.imdbId,
                    bestStream.url?.takeIf { it.isNotBlank() },
                    bestStream.addonId.takeIf { it.isNotBlank() },
                    bestStream.source.takeIf { it.isNotBlank() },
                    request.startPositionMs
                )
            }
            // No stream matched the quality range — fall back to selector so the
            // user can choose manually rather than silently playing the wrong quality.
            else -> {
                showStreamSelector = true
            }
        }
        pendingAutoPlayRequest = null
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

    // D-pad key handler — only used on TV (skipped on mobile/touch devices)
    val keyModifier = if (isMobile) Modifier else Modifier.onPreviewKeyEvent { event ->
        if (event.type == KeyEventType.KeyDown) {
            // Check if any modal is showing
            if (showStreamSelector || showEpisodeContextMenu || showSeasonContextMenu || uiState.showPersonModal) {
                return@onPreviewKeyEvent false // Let the modal handle it
            }

            when (event.key) {
                Key.Back, Key.Escape -> {
                    onBack()
                    true
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
                        }
                        if (atLeftmost) {
                            true
                        } else {
                            handleLeft(
                                focusedSection, buttonIndex, episodeIndex, seasonIndex, castIndex, reviewIndex, similarIndex,
                                { buttonIndex = it }, { episodeIndex = it }, { seasonIndex = it },
                                { castIndex = it }, { reviewIndex = it }, { similarIndex = it }
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
                            focusedSection, buttonIndex, episodeIndex, seasonIndex, castIndex, reviewIndex, similarIndex,
                            uiState, { buttonIndex = it }, { episodeIndex = it }, { seasonIndex = it },
                            { castIndex = it }, { reviewIndex = it }, { similarIndex = it }
                        )
                    }
                }
                Key.DirectionUp -> {
                    if (isSidebarFocused) {
                        true
                    } else {
                        // Navigation: BUTTONS -> SEASONS -> EPISODES -> CAST -> REVIEWS -> SIMILAR
                        val isTV = mediaType == MediaType.TV
                        val hasEpisodes = uiState.episodes.isNotEmpty()
                        val hasCast = uiState.cast.isNotEmpty()
                        val hasReviews = uiState.reviews.isNotEmpty()
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
                            FocusSection.SIMILAR -> if (hasReviews) FocusSection.REVIEWS else if (hasCast) FocusSection.CAST else FocusSection.BUTTONS
                        }
                        true
                    }
                }
                Key.DirectionDown -> {
                    if (isSidebarFocused) {
                        isSidebarFocused = false
                        true
                    } else {
                        // Navigation: BUTTONS -> SEASONS -> EPISODES -> CAST -> REVIEWS -> SIMILAR
                        val isTV = mediaType == MediaType.TV
                        val hasEpisodes = uiState.episodes.isNotEmpty()
                        val hasSeasons = uiState.totalSeasons > 1
                        val hasCast = uiState.cast.isNotEmpty()
                        val hasReviews = uiState.reviews.isNotEmpty()
                        val hasSimilar = uiState.similar.isNotEmpty()
                        focusedSection = when (focusedSection) {
                            FocusSection.BUTTONS -> {
                                if (isTV && hasSeasons) FocusSection.SEASONS
                                else if (isTV && hasEpisodes) FocusSection.EPISODES
                                else if (hasCast) FocusSection.CAST
                                else if (hasReviews) FocusSection.REVIEWS
                                else if (hasSimilar) FocusSection.SIMILAR
                                else FocusSection.BUTTONS
                            }
                            FocusSection.SEASONS -> {
                                if (hasEpisodes) FocusSection.EPISODES
                                else if (hasCast) FocusSection.CAST
                                else if (hasReviews) FocusSection.REVIEWS
                                else if (hasSimilar) FocusSection.SIMILAR
                                else FocusSection.SEASONS
                            }
                            FocusSection.EPISODES -> {
                                if (hasCast) FocusSection.CAST
                                else if (hasReviews) FocusSection.REVIEWS
                                else if (hasSimilar) FocusSection.SIMILAR
                                else FocusSection.EPISODES
                            }
                            FocusSection.CAST -> {
                                if (hasReviews) FocusSection.REVIEWS
                                else if (hasSimilar) FocusSection.SIMILAR
                                else FocusSection.CAST
                            }
                            FocusSection.REVIEWS -> {
                                if (hasSimilar) FocusSection.SIMILAR else FocusSection.REVIEWS
                            }
                            FocusSection.SIMILAR -> FocusSection.SIMILAR  // Stay on similar (bottom)
                        }
                        true
                    }
                }
                Key.Enter, Key.DirectionCenter -> {
                    if (SystemClock.elapsedRealtime() < suppressSelectUntilMs) {
                        return@onPreviewKeyEvent true
                    }
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

                                    if (uiState.autoPlaySingleSource && !uiState.imdbId.isNullOrBlank()) {
                                        pendingAutoPlayRequest = PendingAutoPlayRequest(
                                            season = season,
                                            episode = episode,
                                            startPositionMs = startPositionMs
                                        )
                                        viewModel.loadStreams(uiState.imdbId, season, episode)
                                    } else {
                                        onNavigateToPlayer(
                                            mediaType,
                                            mediaId,
                                            season,
                                            episode,
                                            uiState.imdbId,
                                            null,
                                            null,
                                            null,
                                            startPositionMs
                                        )
                                    }
                                }
                                1 -> { // Sources - Show StreamSelector for manual selection
                                    showStreamSelector = true
                                    // Pass the currently focused episode for TV shows
                                    val ep = uiState.episodes.getOrNull(episodeIndex)
                                    viewModel.loadStreams(uiState.imdbId, ep?.seasonNumber, ep?.episodeNumber)
                                }
                                2 -> { // Trailer
                                    uiState.trailerKey?.let { key ->
                                        try {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$key"))
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            // Fallback: try vnd.youtube URI
                                            try {
                                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$key"))
                                                context.startActivity(intent)
                                            } catch (e2: Exception) {
                                                // Could not open trailer
                                            }
                                        }
                                    }
                                }
                                3 -> viewModel.toggleWatched(episodeIndex)
                                4 -> viewModel.toggleWatchlist()
                            }
                        }
                        FocusSection.EPISODES -> {
                            val ep = uiState.episodes.getOrNull(episodeIndex)
                            if (ep != null) {
                                onNavigateToPlayer(
                                    mediaType, mediaId,
                                    ep.seasonNumber, ep.episodeNumber, uiState.imdbId, null, null, null, null
                                )
                            }
                        }
                        FocusSection.SEASONS -> {
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
            .background(BackgroundDark)
            .focusRequester(focusRequester)
            .focusable()
            .then(keyModifier)
    ) {
        // Main content - full screen with sidebar overlay (same as HomeScreen)
        if (uiState.isLoading || uiState.item == null) {
            // Use skeleton loader for better UX
            SkeletonDetailsPage(
                isTV = mediaType == MediaType.TV,
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
                    contentHasFocus = !isSidebarFocused,
                    usePosterCards = usePosterCards,
                    isMobile = isMobile,
                    onButtonClick = { idx ->
                        when (idx) {
                            0 -> { // Play
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

                                if (uiState.autoPlaySingleSource && !uiState.imdbId.isNullOrBlank()) {
                                    pendingAutoPlayRequest = PendingAutoPlayRequest(
                                        season = season,
                                        episode = episode,
                                        startPositionMs = startPositionMs
                                    )
                                    viewModel.loadStreams(uiState.imdbId, season, episode)
                                } else {
                                    onNavigateToPlayer(
                                        mediaType, mediaId, season, episode,
                                        uiState.imdbId, null, null, null, startPositionMs
                                    )
                                }
                            }
                            1 -> { // Sources
                                showStreamSelector = true
                                val ep = uiState.episodes.getOrNull(episodeIndex)
                                viewModel.loadStreams(uiState.imdbId, ep?.seasonNumber, ep?.episodeNumber)
                            }
                            2 -> { // Trailer
                                uiState.trailerKey?.let { key ->
                                    try {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$key"))
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        try {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$key"))
                                            context.startActivity(intent)
                                        } catch (_: Exception) {}
                                    }
                                }
                            }
                            3 -> viewModel.toggleWatched(episodeIndex)
                            4 -> viewModel.toggleWatchlist()
                        }
                    },
                    onSeasonClick = { idx ->
                        viewModel.loadSeason(idx + 1)
                    },
                    onEpisodeClick = { idx ->
                        val ep = uiState.episodes.getOrNull(idx)
                        if (ep != null) {
                            episodeIndex = idx
                            if (isMobile) {
                                showStreamSelector = true
                                viewModel.loadStreams(uiState.imdbId, ep.seasonNumber, ep.episodeNumber)
                            } else {
                                onNavigateToPlayer(
                                    mediaType, mediaId,
                                    ep.seasonNumber, ep.episodeNumber, uiState.imdbId, null, null, null, null
                                )
                            }
                        }
                    },
                    onCastClick = { idx ->
                        val member = uiState.cast.getOrNull(idx)
                        if (member != null) {
                            viewModel.loadPerson(member.id)
                        }
                    },
                    onSimilarClick = { idx ->
                        val sim = uiState.similar.getOrNull(idx)
                        if (sim != null) {
                            onNavigateToDetails(sim.mediaType, sim.id)
                        }
                    }
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

        // Stream Selector Modal
        StreamSelector(
            isVisible = showStreamSelector,
            streams = uiState.streams,
            selectedStream = null,
            isLoading = uiState.isLoadingStreams,
            hasStreamingAddons = uiState.hasStreamingAddons,
            onSelect = { stream ->
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
                seasonEpisode = "S${episode.seasonNumber}:E${episode.episodeNumber}",
                isWatched = episode.isWatched,
                onPlay = {
                    showEpisodeContextMenu = false
                    onNavigateToPlayer(
                        mediaType, mediaId,
                        episode.seasonNumber, episode.episodeNumber, uiState.imdbId, null, null, null, null
                    )
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
    BUTTONS, EPISODES, SEASONS, CAST, REVIEWS, SIMILAR
}

private data class PendingAutoPlayRequest(
    val season: Int?,
    val episode: Int?,
    val startPositionMs: Long?
)

// Threshold helpers — now uses centralized quality detection from StreamQualityUtils
// "Any" / empty max maps to 4 (no upper cap).
// ---------------------------------------------------------------------------
private fun minQualityThreshold(value: String): Int = when (value.trim().lowercase()) {
    "720p", "hd"             -> 2
    "1080p", "fullhd", "fhd" -> 3
    "4k", "2160p", "uhd"     -> 4
    else                     -> 0  // "Any" / unset → no lower bound
}

private fun maxQualityThreshold(value: String): Int = when (value.trim().lowercase()) {
    "720p", "hd"             -> 2
    "1080p", "fullhd", "fhd" -> 3
    "4k", "2160p", "uhd"     -> 4
    else                     -> 4  // "Any" / unset → no upper cap
}

private fun isAutoPlayableStream(stream: com.arflix.tv.data.model.StreamSource): Boolean {
    val url = stream.url?.trim().orEmpty()
    return url.startsWith("http", ignoreCase = true)
}

private fun handleLeft(
    section: FocusSection,
    buttonIdx: Int, episodeIdx: Int, seasonIdx: Int, castIdx: Int, reviewIdx: Int, similarIdx: Int,
    setButton: (Int) -> Unit, setEpisode: (Int) -> Unit, setSeason: (Int) -> Unit,
    setCast: (Int) -> Unit, setReview: (Int) -> Unit, setSimilar: (Int) -> Unit
): Boolean {
    when (section) {
        FocusSection.BUTTONS -> if (buttonIdx > 0) setButton(buttonIdx - 1)
        FocusSection.EPISODES -> if (episodeIdx > 0) setEpisode(episodeIdx - 1)
        FocusSection.SEASONS -> if (seasonIdx > 0) setSeason(seasonIdx - 1)
        FocusSection.CAST -> if (castIdx > 0) setCast(castIdx - 1)
        FocusSection.REVIEWS -> if (reviewIdx > 0) setReview(reviewIdx - 1)
        FocusSection.SIMILAR -> if (similarIdx > 0) setSimilar(similarIdx - 1)
    }
    return true
}

private fun handleRight(
    section: FocusSection,
    buttonIdx: Int, episodeIdx: Int, seasonIdx: Int, castIdx: Int, reviewIdx: Int, similarIdx: Int,
    uiState: DetailsUiState,
    setButton: (Int) -> Unit, setEpisode: (Int) -> Unit, setSeason: (Int) -> Unit,
    setCast: (Int) -> Unit, setReview: (Int) -> Unit, setSimilar: (Int) -> Unit
): Boolean {
    when (section) {
        FocusSection.BUTTONS -> if (buttonIdx < 4) setButton(buttonIdx + 1)
        FocusSection.EPISODES -> if (episodeIdx < uiState.episodes.size - 1) setEpisode(episodeIdx + 1)
        FocusSection.SEASONS -> if (seasonIdx < uiState.totalSeasons - 1) setSeason(seasonIdx + 1)
        FocusSection.CAST -> if (castIdx < uiState.cast.size - 1) setCast(castIdx + 1)
        FocusSection.REVIEWS -> if (reviewIdx < uiState.reviews.size - 1) setReview(reviewIdx + 1)
        FocusSection.SIMILAR -> if (similarIdx < uiState.similar.size - 1) setSimilar(similarIdx + 1)
    }
    return true
}

@OptIn(ExperimentalTvMaterial3Api::class)
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
    contentHasFocus: Boolean = true,
    usePosterCards: Boolean = false,
    isMobile: Boolean = false,
    onButtonClick: (Int) -> Unit = {},
    onSeasonClick: (Int) -> Unit = {},
    onEpisodeClick: (Int) -> Unit = {},
    onCastClick: (Int) -> Unit = {},
    onSimilarClick: (Int) -> Unit = {}
) {
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
        val backdropHeight = screenHeightDp * 0.35f
        val mobileScrollState = rememberScrollState()

        val genreText = genres.take(2).joinToString(" / ").ifEmpty {
            if (item.mediaType == MediaType.TV) "TV Series" else "Movie"
        }
        val displayDate = item.releaseDate?.takeIf { it.isNotEmpty() } ?: item.year
        val hasDuration = item.duration.isNotEmpty() && item.duration != "0m"
        val rating = item.imdbRating.ifEmpty { item.tmdbRating }
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
                                        0.4f to Color.Transparent,
                                        0.7f to Color.Black.copy(alpha = 0.8f),
                                        1.0f to Color.Black
                                    )
                                )
                            )
                    )
                    // Title over backdrop bottom
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        if (logoUrl != null) {
                            AsyncImage(
                                model = logoUrl,
                                contentDescription = item.title,
                                contentScale = ContentScale.Fit,
                                alignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .height(48.dp)
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
                    }
                }

                // --- Metadata & content on black background ---
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    // Metadata row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = genreText,
                            style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold),
                            color = Color.White.copy(alpha = 0.7f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (displayDate.isNotEmpty()) {
                            Text(text = "|", style = ArflixTypography.caption.copy(fontSize = 12.sp), color = Color.White.copy(alpha = 0.4f), maxLines = 1)
                            Text(
                                text = displayDate,
                                style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold),
                                color = Color.White.copy(alpha = 0.7f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (hasDuration) {
                            Text(text = "|", style = ArflixTypography.caption.copy(fontSize = 12.sp), color = Color.White.copy(alpha = 0.4f), maxLines = 1)
                            Text(
                                text = item.duration,
                                style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold),
                                color = Color.White.copy(alpha = 0.7f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (ratingValue > 0f) {
                            Text(text = "|", style = ArflixTypography.caption.copy(fontSize = 12.sp), color = Color.White.copy(alpha = 0.4f))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                                modifier = Modifier
                                    .background(Color(0xFFF5C518), RoundedCornerShape(3.dp))
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "IMDb",
                                    style = ArflixTypography.caption.copy(fontSize = 8.sp, fontWeight = FontWeight.Black),
                                    color = Color.Black
                                )
                                Text(
                                    text = rating,
                                    style = ArflixTypography.caption.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                                    color = Color.Black
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Description
                    Text(
                        text = item.overview,
                        style = ArflixTypography.body.copy(
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = Color.White.copy(alpha = 0.85f),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action buttons row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val playButtonLabel = if (!playLabel.isNullOrBlank()) playLabel else "Play"
                        MobileActionButton(icon = Icons.Default.PlayArrow, text = playButtonLabel, isPrimary = true, onClick = { onButtonClick(0) })
                        MobileActionButton(icon = Icons.Default.List, text = "Sources", onClick = { onButtonClick(1) })
                        MobileActionButton(icon = Icons.Default.Movie, text = "Trailer", onClick = { onButtonClick(2) })
                        MobileActionButton(
                            icon = if (buttonWatched) Icons.Default.Check else Icons.Default.Visibility,
                            text = if (buttonWatched) "Watched" else "Watched",
                            isActive = buttonWatched,
                            onClick = { onButtonClick(3) }
                        )
                        MobileActionButton(
                            icon = if (isInWatchlist) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            text = "Watchlist",
                            isActive = isInWatchlist,
                            onClick = { onButtonClick(4) }
                        )
                    }

                    // --- TV Show: Season selector & Episodes ---
                    if (item.mediaType == MediaType.TV && episodes.isNotEmpty()) {
                        if (totalSeasons > 1) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Seasons",
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
                                    SeasonButton(
                                        season = season,
                                        isSelected = season == currentSeason,
                                        isFocused = false,
                                        watchedCount = currentSeasonProgress?.first ?: progress?.first ?: 0,
                                        totalCount = currentSeasonProgress?.second ?: progress?.second ?: 0,
                                        onClick = { onSeasonClick(index) }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Episodes",
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Episodes LazyRow (outside the inner Column to allow independent horizontal scroll)
                if (item.mediaType == MediaType.TV && episodes.isNotEmpty()) {
                    LazyRow(
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        standardItemsIndexed(
                            episodes,
                            key = { index, ep -> "mob_ep_${ep.seasonNumber}_${ep.episodeNumber}_$index" }
                        ) { index, episode ->
                            EpisodeCard(
                                episode = episode,
                                isFocused = false,
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
                            text = "Cast",
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    LazyRow(
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        standardItemsIndexed(
                            cast,
                            key = { index, c -> "mob_cast_${c.id}_$index" }
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
                            text = "More Like This",
                            style = ArvioSkin.typography.sectionTitle.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    LazyRow(
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        standardItemsIndexed(
                            similar,
                            key = { index, m -> "mob_sim_${m.mediaType.name}_${m.id}_$index" }
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

                // Bottom spacing
                Spacer(modifier = Modifier.height(32.dp))
            }
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

        // Layer 3: Bottom floor-fade
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

        // Hero metadata positioned above the content rows
        val heroStartPadding = 36.dp
        val heroEndPadding = 400.dp
        val configuration = LocalConfiguration.current
        val contentRowHeight = (configuration.screenHeightDp * 0.34f).dp.coerceIn(240.dp, 320.dp)
        val contentRowBottomPadding = 12.dp
        val contentRowTopPadding = contentRowHeight + contentRowBottomPadding
        val buttonsBottomPadding = contentRowTopPadding - 10.dp
        val heroBottomPadding = buttonsBottomPadding + if (configuration.screenHeightDp < 720) 56.dp else 64.dp

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
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier.height(70.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (logoUrl != null) {
                            AsyncImage(
                                model = logoUrl,
                                contentDescription = item.title,
                                contentScale = ContentScale.Fit,
                                alignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .height(70.dp)
                                    .width(300.dp)
                            )
                        } else {
                            Text(
                                text = item.title.uppercase(),
                                style = ArflixTypography.heroTitle.copy(
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 2.sp,
                                    shadow = textShadow
                                ),
                                color = TextPrimary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
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

                Spacer(modifier = Modifier.height(10.dp))

                val genreText = genres.take(2).joinToString(" / ").ifEmpty {
                    if (item.mediaType == MediaType.TV) "TV Series" else "Movie"
                }
                val isCompactHeight = configuration.screenHeightDp < 720
                val displayDate = item.releaseDate?.takeIf { it.isNotEmpty() } ?: item.year
                val hasDuration = item.duration.isNotEmpty() && item.duration != "0m"
                val rating = item.imdbRating.ifEmpty { item.tmdbRating }
                val ratingValue = parseRatingValue(rating)
                val budgetText = budget?.trim()?.takeIf { it.isNotEmpty() && item.mediaType == MediaType.MOVIE }
                val overviewMaxHeight = if (isCompactHeight) 62.dp else 66.dp

                val separatorStyle = ArflixTypography.caption.copy(
                    fontSize = 14.sp,
                    shadow = textShadow
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = genreText,
                        style = ArflixTypography.caption.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            shadow = textShadow
                        ),
                        color = Color.White
                    )

                    if (displayDate.isNotEmpty()) {
                        Text(text = "|", style = separatorStyle, color = Color.White.copy(alpha = 0.7f))
                        Text(
                            text = displayDate,
                            style = ArflixTypography.caption.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                shadow = textShadow
                            ),
                            color = Color.White
                        )
                    }

                    if (hasDuration) {
                        Text(text = "|", style = separatorStyle, color = Color.White.copy(alpha = 0.7f))
                        Text(
                            text = item.duration,
                            style = ArflixTypography.caption.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                shadow = textShadow
                            ),
                            color = Color.White
                        )
                    }

                    if (ratingValue > 0f) {
                        Text(text = "|", style = separatorStyle, color = Color.White.copy(alpha = 0.7f))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .background(Color(0xFFF5C518), RoundedCornerShape(3.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "IMDb",
                                style = ArflixTypography.caption.copy(fontSize = 9.sp, fontWeight = FontWeight.Black),
                                color = Color.Black
                            )
                            Text(
                                text = rating,
                                style = ArflixTypography.caption.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                                color = Color.Black
                            )
                        }
                    }

                    if (!budgetText.isNullOrBlank()) {
                        Text(text = "|", style = separatorStyle, color = Color.White.copy(alpha = 0.7f))
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

                Spacer(modifier = Modifier.height(14.dp))

                val displayOverview = item.overview

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
                    "Play"
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
                        text = "Sources",
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 1,
                        isIconOnly = true
                    )
                }
                Box(modifier = Modifier.clickable { onButtonClick(2) }) {
                    PremiumActionButton(
                        icon = Icons.Default.Movie,
                        text = "Trailer",
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 2,
                        isIconOnly = true
                    )
                }
                Box(modifier = Modifier.clickable { onButtonClick(3) }) {
                    PremiumActionButton(
                        icon = if (buttonWatched) Icons.Default.Check else Icons.Default.Visibility,
                        text = if (buttonWatched) "Watched" else "Mark Watched",
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 3,
                        isActive = buttonWatched,
                        isIconOnly = true
                    )
                }
                Box(modifier = Modifier.clickable { onButtonClick(4) }) {
                    PremiumActionButton(
                        icon = if (isInWatchlist) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        text = "Watchlist",
                        isFocused = focusSectionForUi == FocusSection.BUTTONS && buttonIndex == 4,
                        isIconOnly = true,
                        isActive = isInWatchlist
                    )
                }
            }
        }

        // Scrollable content area at the bottom
        val contentScrollState = rememberTvLazyListState()

        // Calculate section indices dynamically
        val isTV = item.mediaType == MediaType.TV
        val hasEpisodes = isTV && episodes.isNotEmpty()
        val hasSeasons = isTV && totalSeasons > 1
        val hasCast = cast.isNotEmpty()
        val hasReviews = reviews.isNotEmpty()
        val hasSimilar = similar.isNotEmpty()

        // Build index map for each section (accounting for spacer items)
        var idx = 0
        val seasonsIdx = if (hasSeasons) idx.also { idx++ } else -1
        val episodesIdx = if (hasEpisodes) idx.also { idx++ } else -1
        if (hasCast) idx++  // spacer
        val castIdx = if (hasCast) idx.also { idx++ } else -1
        if (hasReviews) idx++  // spacer
        val reviewsIdx = if (hasReviews) idx.also { idx++ } else -1
        if (hasSimilar) idx++  // spacer
        val similarIdx = if (hasSimilar) idx.also { idx++ } else -1

        LaunchedEffect(focusedSection, contentHasFocus) {
            if (!contentHasFocus) return@LaunchedEffect

            val targetIndex = when (focusedSection) {
                FocusSection.BUTTONS, FocusSection.EPISODES, FocusSection.SEASONS -> 0
                FocusSection.CAST -> castIdx
                FocusSection.REVIEWS -> reviewsIdx
                FocusSection.SIMILAR -> similarIdx
            }

            if (targetIndex < 0) return@LaunchedEffect

            val firstVisible = contentScrollState.firstVisibleItemIndex
            val topClusterMaxIndex = maxOf(episodesIdx, seasonsIdx, 0)

            if (focusSectionForUi == FocusSection.BUTTONS ||
                focusSectionForUi == FocusSection.EPISODES ||
                focusSectionForUi == FocusSection.SEASONS
            ) {
                if (firstVisible > topClusterMaxIndex) {
                    contentScrollState.animateScrollToItem(0)
                }
                return@LaunchedEffect
            }

            if (firstVisible != targetIndex) {
                contentScrollState.animateScrollToItem(targetIndex)
            }
        }

        val contentStartPadding = 12.dp

        TvLazyColumn(
            state = contentScrollState,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(contentRowHeight)
                .padding(start = 24.dp, bottom = contentRowBottomPadding)
                .clipToBounds(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 12.dp)
        ) {
            // TV rows: Seasons first, then Episodes
            if (item.mediaType == MediaType.TV && episodes.isNotEmpty()) {
                if (totalSeasons > 1) {
                    item {
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

                        TvLazyRow(
                            state = seasonRowState,
                            contentPadding = PaddingValues(start = contentStartPadding, end = 150.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(seasonItems, key = { _, s -> s }) { index, season ->
                                val progress = seasonProgress[season]
                                val currentSeasonProgress = if (season == currentSeason && episodes.isNotEmpty()) {
                                    Pair(episodes.count { it.isWatched }, episodes.size)
                                } else null
                                SeasonButton(
                                    season = season,
                                    isSelected = season == currentSeason,
                                    isFocused = focusSectionForUi == FocusSection.SEASONS && index == seasonFocusIndex,
                                    watchedCount = currentSeasonProgress?.first ?: progress?.first ?: 0,
                                    totalCount = currentSeasonProgress?.second ?: progress?.second ?: 0,
                                    onClick = { onSeasonClick(index) }
                                )
                            }
                        }
                    }
                }

                item {
                    val episodeCardWidth = if (configuration.screenWidthDp < 1400) 300.dp else 320.dp
                    val episodeRowState = rememberTvLazyListState()
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

                    TvLazyRow(
                        state = episodeRowState,
                        contentPadding = PaddingValues(start = contentStartPadding, end = 520.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        itemsIndexed(
                            episodes,
                            key = { index, ep -> "${ep.seasonNumber}_${ep.episodeNumber}_$index" }
                        ) { index, episode ->
                            val isFocused = currentFocusedSection == FocusSection.EPISODES && index == currentEpisodeIndex
                            EpisodeCard(
                                episode = episode,
                                isFocused = isFocused,
                                onClick = { onEpisodeClick(index) }
                            )
                        }
                    }
                }
            }

            // Cast section
            if (cast.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                }
                item {
                    val castRowState = rememberTvLazyListState()
                    HomeStyleRowAutoScroll(
                        rowState = castRowState,
                        isCurrentRow = focusSectionForUi == FocusSection.CAST,
                        focusedItemIndex = castIndex,
                        totalItems = cast.size,
                        itemWidth = 90.dp,
                        itemSpacing = 16.dp
                    )

                    Column {
                        Text(
                            text = "Cast",
                            style = ArvioSkin.typography.sectionTitle.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White.copy(alpha = 0.9f),
                            modifier = Modifier.padding(start = contentStartPadding, bottom = 10.dp)
                        )

                        TvLazyRow(
                            state = castRowState,
                            contentPadding = PaddingValues(start = contentStartPadding, end = 120.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            itemsIndexed(
                                cast,
                                key = { index, c -> "${c.id}_${c.character}_$index" }
                            ) { index, castMember ->
                                CircularCastCard(
                                    castMember = castMember,
                                    isFocused = focusSectionForUi == FocusSection.CAST && index == castIndex,
                                    onClick = { onCastClick(index) }
                                )
                            }
                        }
                    }
                }
            }

            // Reviews section
            if (reviews.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
                item {
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
                            text = "Reviews",
                            style = ArvioSkin.typography.sectionTitle.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White.copy(alpha = 0.9f),
                            modifier = Modifier.padding(start = contentStartPadding, bottom = 10.dp)
                        )

                        TvLazyRow(
                            state = reviewRowState,
                            contentPadding = PaddingValues(start = contentStartPadding, end = 350.dp),
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
            }

            // More Like This section
            if (similar.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
                item {
                    val similarRowState = rememberTvLazyListState()
                    HomeStyleRowAutoScroll(
                        rowState = similarRowState,
                        isCurrentRow = focusSectionForUi == FocusSection.SIMILAR,
                        focusedItemIndex = similarIndex,
                        totalItems = similar.size,
                        itemWidth = if (usePosterCards) 91.dp else 180.dp,
                        itemSpacing = 14.dp
                    )

                    Column {
                        Text(
                            text = "More Like This",
                            style = ArvioSkin.typography.sectionTitle.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White.copy(alpha = 0.9f),
                            modifier = Modifier.padding(start = contentStartPadding, bottom = 10.dp)
                        )

                        TvLazyRow(
                            state = similarRowState,
                            contentPadding = PaddingValues(
                                start = contentStartPadding,
                                end = if (usePosterCards) 112.dp else 210.dp
                            ),
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            itemsIndexed(
                                similar,
                                key = { index, m -> "${m.mediaType.name}_${m.id}_$index" }
                            ) { index, mediaItem ->
                                SimilarMediaCard(
                                    item = mediaItem,
                                    logoImageUrl = similarLogoUrls["${mediaItem.mediaType}_${mediaItem.id}"],
                                    usePosterCards = usePosterCards,
                                    isFocused = focusSectionForUi == FocusSection.SIMILAR && index == similarIndex,
                                    onClick = { onSimilarClick(index) }
                                )
                            }
                        }
                    }
                }
            }

            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
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
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val availableWidthDp = configuration.screenWidthDp.dp - 56.dp - 12.dp
    val fallbackItemsPerPage = remember(configuration, density, itemWidth, itemSpacing) {
        val availablePx = with(density) { availableWidthDp.coerceAtLeast(1.dp).roundToPx() }
        val itemSpanPx = with(density) { (itemWidth + itemSpacing).roundToPx() }.coerceAtLeast(1)
        (availablePx / itemSpanPx).coerceAtLeast(1)
    }
    var baseVisibleCount by remember { mutableIntStateOf(0) }
    val visibleCount = rowState.layoutInfo.visibleItemsInfo.size
    LaunchedEffect(visibleCount) {
        if (visibleCount > 0 && baseVisibleCount == 0) {
            baseVisibleCount = visibleCount
        }
    }
    val itemsPerPage = remember(fallbackItemsPerPage, baseVisibleCount) {
        if (baseVisibleCount > 0) minOf(baseVisibleCount, fallbackItemsPerPage) else fallbackItemsPerPage
    }
    val effectiveVisibleCount = remember(totalItems, itemsPerPage, visibleCount) {
        if (visibleCount > 0) minOf(visibleCount, totalItems.coerceAtLeast(1)) else itemsPerPage
    }
    val maxFirstIndex = remember(totalItems, effectiveVisibleCount) {
        (totalItems - effectiveVisibleCount).coerceAtLeast(0)
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

        val extraOffset = if (focusedItemIndex > maxFirstIndex) {
            ((focusedItemIndex - maxFirstIndex) * itemSpanPx).toInt()
        } else {
            0
        }

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
        val currentLast = rowState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: currentFirst
        val targetOutsideViewport = focusedItemIndex < currentFirst || focusedItemIndex > currentLast
        val delta = scrollTargetIndex - currentFirst
        if (extraOffset > 0 || targetOutsideViewport || abs(delta) > 1) {
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (delta != 0) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (lastScrollOffset != extraOffset) {
            rowState.scrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        }
        lastScrollIndex = scrollTargetIndex
        lastScrollOffset = extraOffset
    }
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
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(10.dp)
    val bgColor = when {
        isPrimary -> Color.White
        isActive -> Color.White.copy(alpha = 0.15f)
        else -> Color.White.copy(alpha = 0.08f)
    }
    val contentColor = if (isPrimary) Color.Black else Color.White.copy(alpha = 0.9f)

    Row(
        modifier = Modifier
            .clip(shape)
            .background(bgColor, shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            style = ArvioSkin.typography.button.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
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

    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.04f else 1f,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "button_scale"
    )

    val backgroundColor by animateColorAsState(
        targetValue = when {
            isFocused && isPrimary -> Color.White
            isFocused -> Color.White.copy(alpha = 0.95f)
            else -> Color.Transparent
        },
        animationSpec = tween(150),
        label = "button_bg"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isFocused) Color.Black else Color.White.copy(alpha = 0.9f),
        animationSpec = tween(150),
        label = "button_content"
    )

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
    isFocused: Boolean,
    onClick: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val cardWidth = if (configuration.screenWidthDp < 1400) 300.dp else 320.dp
    val aspectRatio = 16f / 9f
    val context = LocalContext.current
    val density = LocalDensity.current

    val shape = rememberArvioCardShape(ArvioSkin.radius.md)
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.02f else 1f,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
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
    val ratingLabel = if (episode.voteAverage > 0f) {
        "${String.format("%.1f", episode.voteAverage)}"
    } else {
        null
    }
    val previewText = episode.overview
        .trim()
        .ifEmpty { "No episode synopsis available." }
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
                modifier = Modifier.fillMaxSize()
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
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFF5C518),
                        modifier = Modifier.size(10.dp)
                    )
                    Text(
                        text = rating,
                        style = ArvioSkin.typography.caption.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
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
        SimpleDateFormat("MMM d, yyyy", Locale.US).format(date)
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
    onClick: () -> Unit = {}
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

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .background(backgroundColor, shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Season $season",
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
private fun CircularCastCard(
    castMember: CastMember,
    isFocused: Boolean,
    onClick: () -> Unit
) {
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ReviewCard(
    review: Review,
    isFocused: Boolean
) {
    val shape = RoundedCornerShape(16.dp)

    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.08f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "review_scale"
    )

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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
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
            text = "ONGOING",
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
            text = genre.uppercase(),
            style = ArflixTypography.label,
            color = Pink
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun LanguageBadge(language: String) {
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
    val greenColor = Color(0xFF10B981)

    Box(
        modifier = Modifier
            .background(greenColor.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
            .border(1.dp, greenColor.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = "BUDGET: $budget",
            style = ArflixTypography.label,
            color = greenColor
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun StatusBadge(status: String) {
    val (bgColor, textColor) = when {
        status.contains("Return", ignoreCase = true) -> Pair(Color(0xFF22D3EE), Color(0xFF22D3EE))
        status.contains("Ended", ignoreCase = true) -> Pair(Color(0xFF6B7280), Color(0xFF6B7280))
        status.contains("Cancel", ignoreCase = true) -> Pair(Color(0xFFEF4444), Color(0xFFEF4444))
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SimilarMediaCard(
    item: MediaItem,
    logoImageUrl: String?,
    usePosterCards: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit = {}
) {
    val mediaTypeLabel = if (item.mediaType == MediaType.TV) "TV Series" else "Movie"
    val yearSuffix = item.year.takeIf { it.isNotBlank() }?.let { " | $it" }.orEmpty()
    MediaCard(
        item = item.copy(subtitle = "$mediaTypeLabel$yearSuffix"),
        width = if (usePosterCards) 105.dp else 210.dp,
        isLandscape = !usePosterCards,
        logoImageUrl = logoImageUrl,
        showProgress = false,
        isFocusedOverride = isFocused,
        enableSystemFocus = false,
        onFocused = { },
        onClick = onClick
    )
}