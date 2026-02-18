package com.arflix.tv.ui.screens.details

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.components.PersonModal
import com.arflix.tv.ui.components.PosterCard
import com.arflix.tv.ui.components.Sidebar
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.SkeletonDetailsPage
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.TopBarClock
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape
import com.arflix.tv.ui.theme.AccentRed
import com.arflix.tv.ui.theme.AnimationConstants
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.PrimeBlue
import com.arflix.tv.ui.theme.PrimeBlueDark
import com.arflix.tv.ui.theme.Purple
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.isInCinema
import com.arflix.tv.util.parseRatingValue
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
    onNavigateToPlayer: (MediaType, Int, Int?, Int?, String?, String?, Long?) -> Unit,
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
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Start on buttons for both TV and movies (buttons are now shown for both)
    var focusedSection by remember { mutableStateOf(FocusSection.BUTTONS) }
    var buttonIndex by remember { mutableIntStateOf(0) }
    var episodeIndex by remember { mutableIntStateOf(0) }
    var seasonIndex by remember { mutableIntStateOf(0) }
    var castIndex by remember { mutableIntStateOf(0) }
    var reviewIndex by remember { mutableIntStateOf(0) }
    var similarIndex by remember { mutableIntStateOf(0) }
    
    // Sidebar state
    var isSidebarFocused by remember { mutableStateOf(false) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = if (hasProfile) SidebarItem.entries.size else SidebarItem.entries.size - 1
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 2 else 1) }
    
    // Stream Selector state
    var showStreamSelector by remember { mutableStateOf(false) }
    
    // Episode Context Menu state
    var showEpisodeContextMenu by remember { mutableStateOf(false) }
    var contextMenuEpisode by remember { mutableStateOf<Episode?>(null) }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(mediaType, mediaId, initialSeason, initialEpisode) {
        viewModel.loadDetails(mediaType, mediaId, initialSeason, initialEpisode)
    }

    // Keep watched badges and continue target fresh when returning from player.
    DisposableEffect(lifecycleOwner, mediaType, mediaId) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refreshAfterPlayerReturn()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    // Check if any modal is showing
                    if (showStreamSelector || showEpisodeContextMenu || uiState.showPersonModal) {
                        return@onPreviewKeyEvent false // Let the modal handle it
                    }
                    
                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            onBack()  // Go directly home on back press
                            true
                        }
                        Key.DirectionLeft -> {
                            if (isSidebarFocused) {
                                false
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
                                    isSidebarFocused = true
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
                                isSidebarFocused = false
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
                            if (isSidebarFocused && sidebarFocusIndex > 0) {
                                sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                true
                            } else {
                                // Navigation: BUTTONS -> EPISODES -> SEASONS -> CAST -> REVIEWS -> SIMILAR
                                val isTV = mediaType == MediaType.TV
                                val hasCast = uiState.cast.isNotEmpty()
                                val hasReviews = uiState.reviews.isNotEmpty()
                                focusedSection = when (focusedSection) {
                                    FocusSection.BUTTONS -> FocusSection.BUTTONS  // Stay on buttons (top)
                                    FocusSection.EPISODES -> FocusSection.BUTTONS  // Go up to buttons
                                    FocusSection.SEASONS -> FocusSection.EPISODES  // Go up to episodes
                                    FocusSection.CAST -> {
                                        if (isTV) {
                                            if (uiState.totalSeasons > 1) FocusSection.SEASONS else FocusSection.EPISODES
                                        } else FocusSection.BUTTONS
                                    }
                                    FocusSection.REVIEWS -> if (hasCast) FocusSection.CAST else FocusSection.BUTTONS
                                    FocusSection.SIMILAR -> if (hasReviews) FocusSection.REVIEWS else if (hasCast) FocusSection.CAST else FocusSection.BUTTONS
                                }
                                true
                            }
                        }
                        Key.DirectionDown -> {
                            if (isSidebarFocused && sidebarFocusIndex < maxSidebarIndex) {
                                sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                                true
                            } else {
                                // Navigation: BUTTONS -> EPISODES -> SEASONS -> CAST -> REVIEWS -> SIMILAR
                                val isTV = mediaType == MediaType.TV
                                val hasEpisodes = uiState.episodes.isNotEmpty()
                                val hasCast = uiState.cast.isNotEmpty()
                                val hasReviews = uiState.reviews.isNotEmpty()
                                val hasSimilar = uiState.similar.isNotEmpty()
                                focusedSection = when (focusedSection) {
                                    FocusSection.BUTTONS -> {
                                        if (isTV && hasEpisodes) FocusSection.EPISODES
                                        else if (hasCast) FocusSection.CAST
                                        else if (hasReviews) FocusSection.REVIEWS
                                        else if (hasSimilar) FocusSection.SIMILAR
                                        else FocusSection.BUTTONS
                                    }
                                    FocusSection.EPISODES -> {
                                        if (uiState.totalSeasons > 1) FocusSection.SEASONS
                                        else if (hasCast) FocusSection.CAST
                                        else if (hasReviews) FocusSection.REVIEWS
                                        else if (hasSimilar) FocusSection.SIMILAR
                                        else FocusSection.EPISODES
                                    }
                                    FocusSection.SEASONS -> {
                                        if (hasCast) FocusSection.CAST
                                        else if (hasReviews) FocusSection.REVIEWS
                                        else if (hasSimilar) FocusSection.SIMILAR
                                        else FocusSection.SEASONS
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
                            if (isSidebarFocused) {
                                if (hasProfile && sidebarFocusIndex == 0) {
                                    onSwitchProfile()
                                } else {
                                    val itemIndex = if (hasProfile) sidebarFocusIndex - 1 else sidebarFocusIndex
                                    when (SidebarItem.entries[itemIndex]) {
                                        SidebarItem.SEARCH -> onNavigateToSearch()
                                        SidebarItem.HOME -> onNavigateToHome()
                                        SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                        SidebarItem.TV -> onNavigateToTv()
                                        SidebarItem.SETTINGS -> onNavigateToSettings()
                                    }
                                }
                                return@onPreviewKeyEvent true
                            }
                            when (focusedSection) {
                                FocusSection.BUTTONS -> {
                                    when (buttonIndex) {
                                        0 -> { // Play - Auto-play highest quality source
                                            if (mediaType == MediaType.TV) {
                                                val season = uiState.playSeason
                                                    ?: uiState.episodes.getOrNull(episodeIndex)?.seasonNumber
                                                    ?: 1
                                                val episode = uiState.playEpisode
                                                    ?: uiState.episodes.getOrNull(episodeIndex)?.episodeNumber
                                                    ?: 1
                                                val startPositionMs = if (
                                                    season == uiState.playSeason &&
                                                    episode == uiState.playEpisode
                                                ) uiState.playPositionMs else null
                                                onNavigateToPlayer(
                                                    mediaType, mediaId,
                                                    season, episode, uiState.imdbId, null, startPositionMs
                                                )
                                            } else {
                                                onNavigateToPlayer(
                                                    mediaType, mediaId, null, null, uiState.imdbId, null, uiState.playPositionMs
                                                )
                                            }
                                        }
                                        1 -> { // Sources - Show StreamSelector for manual selection
                                            showStreamSelector = true
                                            uiState.imdbId?.let { imdbId ->
                                                // Pass the currently focused episode for TV shows
                                                val ep = uiState.episodes.getOrNull(episodeIndex)
                                                viewModel.loadStreams(imdbId, ep?.seasonNumber, ep?.episodeNumber)
                                            }
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
                                            ep.seasonNumber, ep.episodeNumber, uiState.imdbId, null, null
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
                } else false
            }
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
                    seasonProgress = uiState.seasonProgress,
                    playLabel = uiState.playLabel
                )
            }
        }

        // Sidebar overlays on top (same as HomeScreen)
        Sidebar(
            selectedItem = SidebarItem.HOME,
            isSidebarFocused = isSidebarFocused,
            focusedIndex = sidebarFocusIndex,
            profile = currentProfile,
            onProfileClick = onSwitchProfile,
            onItemSelected = { item ->
                when (item) {
                    SidebarItem.SEARCH -> onNavigateToSearch()
                    SidebarItem.HOME -> onNavigateToHome()
                    SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                    SidebarItem.TV -> onNavigateToTv()
                    SidebarItem.SETTINGS -> onNavigateToSettings()
                }
            }
        )
        
        // Clock (profile moved to sidebar)
        TopBarClock(modifier = Modifier.align(Alignment.TopEnd))
        
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
            onSelect = { stream ->
                showStreamSelector = false
                val ep = uiState.episodes.getOrNull(episodeIndex)
                onNavigateToPlayer(
                    mediaType, mediaId,
                    ep?.seasonNumber, ep?.episodeNumber,
                    uiState.imdbId,
                    stream.url,
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
                        episode.seasonNumber, episode.episodeNumber, uiState.imdbId, null, null
                    )
                },
                onSelectSource = {
                    showEpisodeContextMenu = false
                    showStreamSelector = true
                    uiState.imdbId?.let { imdbId ->
                        viewModel.loadStreams(imdbId, episode.seasonNumber, episode.episodeNumber)
                    }
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
    seasonProgress: Map<Int, Pair<Int, Int>> = emptyMap(),
    playLabel: String? = null
) {
    // === PREMIUM LAYERED TEXT SHADOWS ===
    val textShadow = Shadow(
        color = Color.Black.copy(alpha = 0.9f),
        offset = Offset(0f, 2f),
        blurRadius = 8f  // Soft spread shadow for better readability
    )

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
        val heroBottomPadding = 306.dp
        val heroStartPadding = 68.dp  // 56dp sidebar + 12dp gap
        val heroEndPadding = 400.dp
        val configuration = LocalConfiguration.current
        val contentRowHeight = (configuration.screenHeightDp * 0.34f).dp.coerceIn(240.dp, 320.dp)
        val contentRowBottomPadding = 12.dp
        val contentRowTopPadding = contentRowHeight + contentRowBottomPadding
        val buttonsBottomPadding = contentRowTopPadding - 10.dp

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
                // Logo or Title (same size as HomePage)
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

                Spacer(modifier = Modifier.height(10.dp))

                val genreText = genres.take(2).joinToString(" / ").ifEmpty {
                    if (item.mediaType == MediaType.TV) "TV Series" else "Movie"
                }
                val displayDate = item.releaseDate?.takeIf { it.isNotEmpty() } ?: item.year
                val hasDuration = item.duration.isNotEmpty() && item.duration != "0m"
                val rating = item.imdbRating.ifEmpty { item.tmdbRating }
                val ratingValue = parseRatingValue(rating)
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
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Overview text - show episode description when episode is focused
                val focusedEpisode = if (focusedSection == FocusSection.EPISODES && episodes.isNotEmpty()) {
                    episodes.getOrNull(episodeIndex)
                } else null

                val displayOverview = if (focusedEpisode != null && focusedEpisode.overview.isNotEmpty()) {
                    focusedEpisode.overview
                } else {
                    item.overview
                }

                Text(
                    text = displayOverview,
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
                val playButtonLabel = if (
                    focusedSection == FocusSection.BUTTONS &&
                    buttonIndex == 0 &&
                    !playLabel.isNullOrBlank()
                ) {
                    playLabel
                } else {
                    "Play"
                }
                PremiumActionButton(
                    icon = Icons.Default.PlayArrow,
                    text = playButtonLabel,
                    isPrimary = true,
                    isFocused = focusedSection == FocusSection.BUTTONS && buttonIndex == 0
                )
                PremiumActionButton(
                    icon = Icons.Default.List,
                    text = "Sources",
                    isFocused = focusedSection == FocusSection.BUTTONS && buttonIndex == 1,
                    isIconOnly = true
                )
                PremiumActionButton(
                    icon = Icons.Default.Movie,
                    text = "Trailer",
                    isFocused = focusedSection == FocusSection.BUTTONS && buttonIndex == 2,
                    isIconOnly = true
                )
                PremiumActionButton(
                    icon = if (buttonWatched) Icons.Default.Check else Icons.Default.Visibility,
                    text = if (buttonWatched) "Watched" else "Mark Watched",
                    isFocused = focusedSection == FocusSection.BUTTONS && buttonIndex == 3,
                    isActive = buttonWatched,
                    isIconOnly = true
                )
                PremiumActionButton(
                    icon = if (isInWatchlist) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    text = "Watchlist",
                    isFocused = focusedSection == FocusSection.BUTTONS && buttonIndex == 4,
                    isIconOnly = true,
                    isActive = isInWatchlist
                )
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
        val episodesIdx = if (hasEpisodes) idx.also { idx++ } else -1
        // Seasons follows episodes directly (no spacer)
        val seasonsIdx = if (hasSeasons) idx.also { idx++ } else -1
        // Cast has a spacer before it
        if (hasCast) idx++  // spacer
        val castIdx = if (hasCast) idx.also { idx++ } else -1
        // Reviews has a spacer before it
        if (hasReviews) idx++  // spacer
        val reviewsIdx = if (hasReviews) idx.also { idx++ } else -1
        // Similar has a spacer before it
        if (hasSimilar) idx++  // spacer
        val similarIdx = if (hasSimilar) idx.also { idx++ } else -1
        // Smart vertical section scroll:
        // keep top cluster (buttons/episodes/seasons) stable and only scroll when moving to lower sections.
        LaunchedEffect(focusedSection) {
            val targetIndex = when (focusedSection) {
                FocusSection.BUTTONS, FocusSection.EPISODES, FocusSection.SEASONS -> 0
                FocusSection.CAST -> castIdx
                FocusSection.REVIEWS -> reviewsIdx
                FocusSection.SIMILAR -> similarIdx
            }

            if (targetIndex < 0) return@LaunchedEffect

            val firstVisible = contentScrollState.firstVisibleItemIndex
            val topClusterMaxIndex = maxOf(episodesIdx, seasonsIdx, 0)

            // Avoid jitter while moving inside the top area.
            if (focusedSection == FocusSection.BUTTONS ||
                focusedSection == FocusSection.EPISODES ||
                focusedSection == FocusSection.SEASONS
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

        // Content padding for consistent alignment (12dp to match play button at 68dp total)
        val contentStartPadding = 12.dp

        TvLazyColumn(
            state = contentScrollState,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(contentRowHeight)
                .padding(start = 56.dp, bottom = contentRowBottomPadding)
                .clipToBounds(),  // Clip content to prevent overlay on hero
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 12.dp)
        ) {
            // Episodes row (for TV shows)
            if (item.mediaType == MediaType.TV && episodes.isNotEmpty()) {
                item {
                    val episodeRowState = rememberTvLazyListState()
                    HomeStyleRowAutoScroll(
                        rowState = episodeRowState,
                        isCurrentRow = focusedSection == FocusSection.EPISODES,
                        focusedItemIndex = episodeIndex,
                        totalItems = episodes.size,
                        itemWidth = 210.dp,
                        itemSpacing = 14.dp
                    )

                    // Use rememberUpdatedState to ensure items recompose when focus changes
                    val currentFocusedSection by rememberUpdatedState(focusedSection)
                    val currentEpisodeIndex by rememberUpdatedState(episodeIndex)

                    TvLazyRow(
                        state = episodeRowState,
                        contentPadding = PaddingValues(start = contentStartPadding, end = 400.dp),  // Extra padding to ensure last items visible
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        itemsIndexed(episodes, key = { _, ep -> ep.episodeNumber }) { index, episode ->
                            // Read state inside item to ensure recomposition on focus change
                            val isFocused = currentFocusedSection == FocusSection.EPISODES && index == currentEpisodeIndex
                            EpisodeCard(
                                episode = episode,
                                isFocused = isFocused
                            )
                        }
                    }
                }

                // Season buttons row
                if (totalSeasons > 1) {
                    item {
                        val seasonRowState = rememberTvLazyListState()
                        val seasonItems = remember(totalSeasons) { (1..totalSeasons).toList() }
                        HomeStyleRowAutoScroll(
                            rowState = seasonRowState,
                            isCurrentRow = focusedSection == FocusSection.SEASONS,
                            focusedItemIndex = seasonIndex,
                            totalItems = totalSeasons,
                            itemWidth = 120.dp,
                            itemSpacing = 8.dp
                        )

                        val seasonFocusIndex by remember(focusedSection, seasonIndex) {
                            derivedStateOf {
                                if (focusedSection == FocusSection.SEASONS) seasonIndex else -1
                            }
                        }

                        TvLazyRow(
                            state = seasonRowState,
                            contentPadding = PaddingValues(start = contentStartPadding, end = 150.dp),  // 120dp button + 30dp margin
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(seasonItems, key = { _, s -> s }) { index, season ->
                                val progress = seasonProgress[season]
                                SeasonButton(
                                    season = season,
                                    isSelected = season == currentSeason,
                                    isFocused = focusedSection == FocusSection.SEASONS && index == seasonFocusIndex,
                                    watchedCount = progress?.first ?: 0,
                                    totalCount = progress?.second ?: 0
                                )
                            }
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
                        isCurrentRow = focusedSection == FocusSection.CAST,
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
                            contentPadding = PaddingValues(start = contentStartPadding, end = 120.dp),  // 90dp card + 30dp margin
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            itemsIndexed(cast, key = { _, c -> c.id }) { index, castMember ->
                                CircularCastCard(
                                    castMember = castMember,
                                    isFocused = focusedSection == FocusSection.CAST && index == castIndex,
                                    onClick = { /* Handled by key navigation */ }
                                )
                            }
                        }
                    }
                }
            }

            // Reviews section - larger gap from cast
            if (reviews.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
                item {
                    val reviewRowState = rememberTvLazyListState()
                    HomeStyleRowAutoScroll(
                        rowState = reviewRowState,
                        isCurrentRow = focusedSection == FocusSection.REVIEWS,
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
                            contentPadding = PaddingValues(start = contentStartPadding, end = 350.dp),  // 320dp card + 30dp margin
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            itemsIndexed(reviews, key = { _, r -> r.id }) { index, review ->
                                ReviewCard(
                                    review = review,
                                    isFocused = focusedSection == FocusSection.REVIEWS && index == reviewIndex
                                )
                            }
                        }
                    }
                }
            }

            // More Like This section - large gap to hide reviews when scrolled
            if (similar.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
                item {
                    val similarRowState = rememberTvLazyListState()
                    HomeStyleRowAutoScroll(
                        rowState = similarRowState,
                        isCurrentRow = focusedSection == FocusSection.SIMILAR,
                        focusedItemIndex = similarIndex,
                        totalItems = similar.size,
                        itemWidth = 180.dp,
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
                            contentPadding = PaddingValues(start = contentStartPadding, end = 210.dp),  // 180dp card + 30dp margin
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            itemsIndexed(similar, key = { _, m -> m.id }) { index, mediaItem ->
                                SimilarMediaCard(
                                    item = mediaItem,
                                    logoImageUrl = similarLogoUrls["${mediaItem.mediaType}_${mediaItem.id}"],
                                    isFocused = focusedSection == FocusSection.SIMILAR && index == similarIndex
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
    LaunchedEffect(isCurrentRow) {
        if (!isCurrentRow) {
            lastScrollIndex = -1
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
            rowState.animateScrollToItem(index = 0, scrollOffset = 0)
            lastScrollIndex = 0
            return@LaunchedEffect
        }

        if (lastScrollIndex == scrollTargetIndex && extraOffset == 0) return@LaunchedEffect
        if (lastScrollIndex == -1) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
            lastScrollIndex = scrollTargetIndex
            return@LaunchedEffect
        }
        val delta = scrollTargetIndex - lastScrollIndex
        if (delta == 0 && extraOffset > 0) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (abs(delta) > 1) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        } else if (delta != 0) {
            rowState.animateScrollToItem(index = scrollTargetIndex, scrollOffset = extraOffset)
        }
        lastScrollIndex = scrollTargetIndex
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
        targetValue = if (isFocused) 1.04f else 1f,
        animationSpec = tween(
            durationMillis = AnimationConstants.DURATION_FAST,
            easing = AnimationConstants.EaseOut
        ),
        label = "button_scale"
    )

    // Animated background color - buttons only glow when focused
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isFocused && isPrimary -> Color.White
            isFocused -> Color.White.copy(alpha = 0.95f)
            else -> Color.Transparent
        },
        animationSpec = tween(150),
        label = "button_bg"
    )

    // Animated text/icon color - black when focused (on white bg), white otherwise
    val contentColor by animateColorAsState(
        targetValue = if (isFocused) Color.Black else Color.White.copy(alpha = 0.9f),
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
    isFocused: Boolean
) {
    // Same styling as MediaCard on home screen
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

    // Same width as home screen cards (210dp)
    val cardWidth = 210.dp
    val context = LocalContext.current
    val density = LocalDensity.current
    val aspectRatio = 16f / 9f
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

    Column(modifier = Modifier.width(cardWidth)) {
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
                .fillMaxWidth()
                .aspectRatio(aspectRatio)
                .then(scaleModifier),
            shape = shape,
            backgroundColor = ArvioSkin.colors.surface,
            outlineColor = ArvioSkin.colors.focusOutline,  // Same focus border as MediaCard
            outlineWidth = borderWidth,
            focusedScale = 1f,
            pressedScale = 1f,
            enableSystemFocus = false,
            isFocusedOverride = isFocused,
            onClick = null,
        ) { _ ->
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = imageRequest,
                    contentDescription = episode.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Gradient overlay (same as MediaCard)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Transparent,
                                    ArvioSkin.colors.background.copy(alpha = 0.55f),
                                    ArvioSkin.colors.background.copy(alpha = 0.85f),
                                )
                            )
                        )
                )

                // Subtle green watched badge
                if (episode.isWatched) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 6.dp, end = 6.dp)
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

        // Episode title (same style as MediaCard)
        Text(
            text = episode.name,
            style = ArvioSkin.typography.cardTitle,
            color = if (isFocused) ArvioSkin.colors.textPrimary else ArvioSkin.colors.textMuted,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Subtitle: episode number and air date (same style as MediaCard subtitle)
        Text(
            text = "${episode.seasonNumber}x${String.format("%02d", episode.episodeNumber)}" +
                    if (episode.airDate.isNotEmpty()) " | ${episode.airDate}" else "",
            style = ArvioSkin.typography.caption,
            color = ArvioSkin.colors.textMuted.copy(alpha = 0.85f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SeasonButton(
    season: Int,
    isSelected: Boolean,
    isFocused: Boolean,
    watchedCount: Int = 0,
    totalCount: Int = 0
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
        modifier = Modifier.width(90.dp)
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
            text = "BUDGET: $budget",
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
    isFocused: Boolean
) {
    val mediaTypeLabel = if (item.mediaType == MediaType.TV) "TV Series" else "Movie"
    val yearSuffix = item.year.takeIf { it.isNotBlank() }?.let { " | $it" }.orEmpty()
    MediaCard(
        item = item.copy(subtitle = "$mediaTypeLabel$yearSuffix"),
        width = 210.dp,
        isLandscape = true,
        logoImageUrl = logoImageUrl,
        showProgress = false,
        isFocusedOverride = isFocused,
        enableSystemFocus = false,
        onFocused = { },
        onClick = { }
    )
}
