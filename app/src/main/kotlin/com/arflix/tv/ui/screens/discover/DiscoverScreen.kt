package com.arflix.tv.ui.screens.discover

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.StreamAvailabilityRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryEntry
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.data.stream.StreamAvailabilitySummary
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.util.LocalDeviceType
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

@Stable
data class DiscoverUiState(
    val mode: DiscoverMediaMode = DiscoverMediaMode.MOVIES,
    val sections: List<DiscoverUiSection> = emptyList(),
    val streamSummaryStates: Map<String, DiscoverStreamSummaryState> = emptyMap(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

data class DiscoverStreamSummaryState(
    val summary: StreamAvailabilitySummary? = null,
    val isLoading: Boolean = false,
    val hasChecked: Boolean = false
)

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val mediaRepository: MediaRepository,
    private val streamAvailabilityRepository: StreamAvailabilityRepository,
    private val traktRepository: TraktRepository,
    private val watchHistoryRepository: WatchHistoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DiscoverUiState())
    val uiState: StateFlow<DiscoverUiState> = _uiState.asStateFlow()
    private var loadGeneration = 0
    private var streamSummaryJob: Job? = null
    private var pendingStreamSummaryKey: String? = null

    init {
        load(DiscoverMediaMode.MOVIES)
    }

    fun setMode(mode: DiscoverMediaMode) {
        if (_uiState.value.mode == mode) return
        load(mode)
    }

    private fun load(mode: DiscoverMediaMode) {
        val generation = ++loadGeneration
        _uiState.value = _uiState.value.copy(mode = mode, isLoading = true, errorMessage = null)
        viewModelScope.launch {
            runCatching {
                val catalogs = catalogRepository.getCatalogs()
                val titleSections = loadTitleDrivenSections(mode, catalogs)
                val catalogSections = buildDiscoverUiSections(
                    specs = defaultDiscoverSections(mode = mode, date = LocalDate.now()),
                    catalogs = catalogs,
                    mode = mode
                )
                val continueSection = buildContinueWatchingDiscoverSection(
                    mode = mode,
                    items = loadContinueWatchingMediaItems(mode)
                )
                listOfNotNull(continueSection) + titleSections + catalogSections
            }.onSuccess { sections ->
                if (generation != loadGeneration) return@onSuccess
                _uiState.value = DiscoverUiState(
                    mode = mode,
                    sections = sections,
                    streamSummaryStates = _uiState.value.streamSummaryStates,
                    isLoading = false,
                    errorMessage = null
                )

                val similarSection = loadBecauseYouWatchedSection(mode)
                if (generation == loadGeneration && similarSection != null) {
                    _uiState.value = _uiState.value.copy(
                        sections = mergePersonalSection(_uiState.value.sections, similarSection)
                    )
                }
            }.onFailure { error ->
                if (generation != loadGeneration) return@onFailure
                _uiState.value = DiscoverUiState(
                    mode = mode,
                    sections = emptyList(),
                    isLoading = false,
                    errorMessage = error.message ?: "Kunde inte ladda Upptäck."
                )
            }
        }
    }

    private suspend fun loadBecauseYouWatchedSection(mode: DiscoverMediaMode): DiscoverUiSection? {
        val seed = latestHistorySeed(mode) ?: loadContinueWatchingMediaItems(mode).firstOrNull() ?: return null
        val similar = runCatching { mediaRepository.getSimilar(seed.mediaType, seed.id) }.getOrDefault(emptyList())
        return buildBecauseYouWatchedDiscoverSection(
            mode = mode,
            seedTitle = seed.title,
            items = similar
        )
    }

    private suspend fun loadTitleDrivenSections(
        mode: DiscoverMediaMode,
        catalogs: List<CatalogConfig>
    ): List<DiscoverUiSection> = coroutineScope {
        val catalogsById = catalogs.associateBy { it.id }
        discoverTitleRows(mode)
            .map { spec ->
                async(Dispatchers.IO) {
                    val catalog = catalogsById[spec.catalogId] ?: return@async null
                    val category = withTimeoutOrNull(DISCOVER_CATALOG_TIMEOUT_MS) {
                        runCatching { mediaRepository.loadCustomCatalog(catalog, maxItems = 14) }.getOrNull()
                    } ?: return@async null
                    buildCatalogMediaDiscoverSection(
                        mode = mode,
                        catalogId = catalog.id,
                        title = spec.title,
                        subtitle = spec.subtitle,
                        accent = spec.accent,
                        sourceLabel = spec.sourceLabel,
                        items = category.items,
                        maxItems = 14
                    )
                }
            }
            .awaitAll()
            .filterNotNull()
    }

    private data class DiscoverTitleRowSpec(
        val catalogId: String,
        val title: String,
        val subtitle: String,
        val accent: Long,
        val sourceLabel: String = "MAJO"
    )

    private fun discoverTitleRows(mode: DiscoverMediaMode): List<DiscoverTitleRowSpec> {
        return when (mode) {
            DiscoverMediaMode.MOVIES -> listOf(
                DiscoverTitleRowSpec(
                    catalogId = "trending_movies",
                    title = "IMDb Popular",
                    subtitle = "Titlar som folk väljer just nu.",
                    accent = 0xFFFFB000
                ),
                DiscoverTitleRowSpec(
                    catalogId = "top10_movies_today",
                    title = "Top 10 idag",
                    subtitle = "Snabba val när du vill hitta något direkt.",
                    accent = 0xFFE83D5C
                ),
                DiscoverTitleRowSpec(
                    catalogId = "just_added",
                    title = "Nytt att streama",
                    subtitle = "Nya digitala släpp och färskt innehåll.",
                    accent = 0xFF7DD3FC
                )
            )
            DiscoverMediaMode.SERIES -> listOf(
                DiscoverTitleRowSpec(
                    catalogId = "trending_tv",
                    title = "Populära serier",
                    subtitle = "Serier som trendar just nu.",
                    accent = 0xFFFFB000
                ),
                DiscoverTitleRowSpec(
                    catalogId = "top10_shows_today",
                    title = "Top 10 idag",
                    subtitle = "Mest intressanta serierna just nu.",
                    accent = 0xFFE83D5C
                ),
                DiscoverTitleRowSpec(
                    catalogId = "trending_anime",
                    title = "Anime som trendar",
                    subtitle = "Aktuella anime-serier och favoriter.",
                    accent = 0xFFA78BFA
                ),
                DiscoverTitleRowSpec(
                    catalogId = "new_kdramas",
                    title = "Nya K-dramas",
                    subtitle = "Färska koreanska serier att börja på.",
                    accent = 0xFF59D38C
                )
            )
        }
    }

    fun focusStreamSummary(card: DiscoverUiCard?) {
        if (card == null || !card.isMediaTitle) return
        val key = discoverStreamSummaryKey(card)
        val existing = _uiState.value.streamSummaryStates[key]
        if (existing?.summary != null || existing?.isLoading == true) return

        pendingStreamSummaryKey = key
        streamSummaryJob?.cancel()
        streamSummaryJob = viewModelScope.launch {
            delay(STREAM_SUMMARY_DEBOUNCE_MS)
            if (pendingStreamSummaryKey != key) return@launch

            _uiState.value = _uiState.value.copy(
                streamSummaryStates = _uiState.value.streamSummaryStates +
                    (key to DiscoverStreamSummaryState(isLoading = true))
            )

            val summary = withContext(Dispatchers.IO) {
                runCatching {
                    val mediaType = card.mediaType ?: return@runCatching null
                    val mediaId = card.mediaId ?: return@runCatching null
                    val imdbId = mediaRepository.getCachedImdbId(mediaType, mediaId)
                        ?: withTimeoutOrNull(IMDB_RESOLVE_TIMEOUT_MS) {
                            mediaRepository.getOrResolveImdbId(mediaType, mediaId)
                        }
                    val year = card.year.take(4).toIntOrNull()
                    when (mediaType) {
                        MediaType.MOVIE -> streamAvailabilityRepository.movieSummary(
                            imdbId = imdbId,
                            title = card.title,
                            year = year,
                            timeoutMs = STREAM_SUMMARY_TIMEOUT_MS
                        )
                        MediaType.TV -> streamAvailabilityRepository.episodeSummary(
                            imdbId = imdbId,
                            title = card.title,
                            year = year,
                            season = card.initialSeason ?: 1,
                            episode = card.initialEpisode ?: 1,
                            tmdbId = mediaId,
                            genreIds = card.genreIds,
                            originalLanguage = card.originalLanguage,
                            timeoutMs = STREAM_SUMMARY_TIMEOUT_MS
                        )
                    }
                }.getOrNull()
            }

            if (pendingStreamSummaryKey != key) return@launch
            _uiState.value = _uiState.value.copy(
                streamSummaryStates = _uiState.value.streamSummaryStates +
                    (key to DiscoverStreamSummaryState(
                        summary = summary,
                        isLoading = false,
                        hasChecked = true
                    ))
            )
        }
    }

    private suspend fun latestHistorySeed(mode: DiscoverMediaMode): com.arflix.tv.data.model.MediaItem? {
        val targetType = mode.toMediaType()
        return runCatching {
            watchHistoryRepository.getWatchHistory()
                .firstOrNull { entry -> entry.mediaType() == targetType && entry.show_tmdb_id > 0 }
                ?.toContinueWatchingItem()
                ?.toMediaItem()
        }.getOrNull()
    }

    private suspend fun loadContinueWatchingMediaItems(mode: DiscoverMediaMode): List<com.arflix.tv.data.model.MediaItem> {
        val targetType = mode.toMediaType()
        val cached = runCatching { traktRepository.getCachedContinueWatching() }.getOrDefault(emptyList())
        val local = runCatching { traktRepository.getLocalContinueWatching() }.getOrDefault(emptyList())
        val history = runCatching {
            watchHistoryRepository.getContinueWatching().map { it.toContinueWatchingItem() }
        }.getOrDefault(emptyList())

        return (cached + local + history)
            .distinctBy { item -> "${item.mediaType}:${item.id}:${item.season ?: -1}:${item.episode ?: -1}" }
            .filter { it.mediaType == targetType }
            .map { it.toMediaItem() }
            .take(12)
    }

    private fun mergePersonalSection(
        sections: List<DiscoverUiSection>,
        personalSection: DiscoverUiSection
    ): List<DiscoverUiSection> {
        val withoutExisting = sections.filterNot { it.id == personalSection.id }
        val insertAt = withoutExisting.indexOfFirst { it.id.startsWith("movie_") || it.id.startsWith("series_") }
            .takeIf { it >= 0 }
            ?: withoutExisting.size
        return withoutExisting.toMutableList().apply { add(insertAt, personalSection) }
    }

    private fun DiscoverMediaMode.toMediaType(): MediaType = when (this) {
        DiscoverMediaMode.MOVIES -> MediaType.MOVIE
        DiscoverMediaMode.SERIES -> MediaType.TV
    }

    private fun WatchHistoryEntry.mediaType(): MediaType {
        return if (media_type == "tv") MediaType.TV else MediaType.MOVIE
    }

    private fun WatchHistoryEntry.toContinueWatchingItem(): ContinueWatchingItem {
        val normalizedProgress = progress.coerceIn(0f, 1f)
        val derivedPct = when {
            normalizedProgress > 0f -> (normalizedProgress * 100f).toInt()
            duration_seconds > 0L && position_seconds > 0L ->
                ((position_seconds.toFloat() / duration_seconds.toFloat()) * 100f).toInt()
            position_seconds > 0L -> 1
            else -> 0
        }
        val resolvedTitle = title
            ?.trim()
            ?.takeIf { it.isNotBlank() }
            ?: episode_title
                ?.trim()
                ?.takeIf { it.isNotBlank() }
            ?: "Untitled"
        return ContinueWatchingItem(
            id = show_tmdb_id,
            title = resolvedTitle,
            mediaType = mediaType(),
            progress = derivedPct.coerceIn(0, 100),
            resumePositionSeconds = position_seconds.coerceAtLeast(0L),
            durationSeconds = duration_seconds.coerceAtLeast(0L),
            season = season,
            episode = episode,
            episodeTitle = episode_title,
            backdropPath = backdrop_path,
            posterPath = poster_path,
            streamKey = stream_key,
            streamAddonId = stream_addon_id,
            streamTitle = stream_title
        )
    }

    companion object {
        const val DISCOVER_CATALOG_TIMEOUT_MS = 5_500L
        const val STREAM_SUMMARY_DEBOUNCE_MS = 360L
        const val STREAM_SUMMARY_TIMEOUT_MS = 3_500L
        const val IMDB_RESOLVE_TIMEOUT_MS = 1_500L
    }
}

internal fun discoverStreamSummaryKey(card: DiscoverUiCard): String {
    return "${card.mediaType}:${card.mediaId}:${card.initialSeason ?: 0}:${card.initialEpisode ?: 0}"
}

internal fun discoverStreamSummaryLabel(state: DiscoverStreamSummaryState?): String? {
    state ?: return null
    val summary = state.summary
    if (summary != null) {
        return buildList {
            summary.bestResolution?.takeIf { it.isNotBlank() }?.let(::add)
            summary.bestVisualTag?.takeIf { it.isNotBlank() }?.let(::add)
            summary.bestAudioTag?.takeIf { it.isNotBlank() }?.let(::add)
            if (summary.hasSwedishSubtitles) add("SE")
            if (summary.isCachedOrDebridReady) add("Cached")
            summary.bestSourceLabel?.takeIf { it.isNotBlank() }?.let { add(it.take(18)) }
            add("${summary.sourceCount} src")
        }.joinToString(" / ")
    }
    return when {
        state.isLoading -> "Kollar källor"
        state.hasChecked -> "Källor -"
        else -> null
    }
}

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onOpenCatalog: (String) -> Unit = {},
    onOpenMediaDetails: (MediaType, Int, Int?, Int?) -> Unit = { _, _, _, _ -> },
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val rootFocusRequester = remember { FocusRequester() }
    val listState = rememberLazyListState()
    val hasProfile = false
    val maxTopBarIndex = topBarMaxIndex(hasProfile)
    var topBarFocused by remember { mutableStateOf(false) }
    var modeFocused by remember { mutableStateOf(false) }
    var topBarFocusIndex by remember { mutableIntStateOf(2) }
    var focusedSectionIndex by remember { mutableIntStateOf(0) }
    var focusedCardIndex by remember { mutableIntStateOf(0) }

    val focusedCard = remember(uiState.sections, focusedSectionIndex, focusedCardIndex) {
        uiState.sections.getOrNull(focusedSectionIndex)?.cards?.getOrNull(focusedCardIndex)
    }

    LaunchedEffect(Unit) {
        rootFocusRequester.requestFocus()
    }

    LaunchedEffect(uiState.mode, uiState.sections.size) {
        focusedSectionIndex = 0
        focusedCardIndex = 0
        topBarFocused = false
        modeFocused = false
        listState.scrollToItem(0)
    }

    LaunchedEffect(focusedSectionIndex, topBarFocused, modeFocused, uiState.sections.size) {
        if (!topBarFocused && !modeFocused && uiState.sections.isNotEmpty()) {
            listState.animateScrollToItem(focusedSectionIndex.coerceIn(0, uiState.sections.lastIndex))
        }
    }

    LaunchedEffect(focusedCard, topBarFocused, modeFocused) {
        if (!topBarFocused && !modeFocused) {
            viewModel.focusStreamSummary(focusedCard)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            .focusRequester(rootFocusRequester)
            .focusable()
            .onKeyEvent { event ->
                if (isTouchDevice) return@onKeyEvent false
                if (event.type != KeyEventType.KeyDown) return@onKeyEvent false
                val effectiveKey = when (event.key) {
                    Key.DirectionLeft -> if (isRtl) Key.DirectionRight else Key.DirectionLeft
                    Key.DirectionRight -> if (isRtl) Key.DirectionLeft else Key.DirectionRight
                    else -> event.key
                }
                when (effectiveKey) {
                    Key.Back, Key.Escape -> {
                        if (topBarFocused || modeFocused) {
                            topBarFocused = false
                            modeFocused = false
                        } else {
                            onBack()
                        }
                        true
                    }
                    Key.DirectionUp -> {
                        if (topBarFocused) {
                            true
                        } else if (modeFocused) {
                            modeFocused = false
                            topBarFocused = true
                            topBarFocusIndex = 2
                            true
                        } else if (focusedSectionIndex > 0) {
                            focusedSectionIndex--
                            focusedCardIndex = 0
                            true
                        } else {
                            modeFocused = true
                            true
                        }
                    }
                    Key.DirectionDown -> {
                        if (topBarFocused) {
                            topBarFocused = false
                            modeFocused = true
                        } else if (modeFocused) {
                            modeFocused = false
                        } else if (focusedSectionIndex < uiState.sections.lastIndex) {
                            focusedSectionIndex++
                            focusedCardIndex = 0
                        }
                        true
                    }
                    Key.DirectionLeft -> {
                        if (topBarFocused) {
                            topBarFocusIndex = (topBarFocusIndex - 1).coerceIn(0, maxTopBarIndex)
                        } else if (modeFocused) {
                            viewModel.setMode(DiscoverMediaMode.MOVIES)
                        } else if (focusedCardIndex > 0) {
                            focusedCardIndex--
                        }
                        true
                    }
                    Key.DirectionRight -> {
                        if (topBarFocused) {
                            topBarFocusIndex = (topBarFocusIndex + 1).coerceIn(0, maxTopBarIndex)
                        } else if (modeFocused) {
                            viewModel.setMode(DiscoverMediaMode.SERIES)
                        } else {
                            val cardCount = uiState.sections.getOrNull(focusedSectionIndex)?.cards?.size ?: 0
                            if (focusedCardIndex < cardCount - 1) focusedCardIndex++
                        }
                        true
                    }
                    Key.Tab -> {
                        viewModel.setMode(
                            if (uiState.mode == DiscoverMediaMode.MOVIES) {
                                DiscoverMediaMode.SERIES
                            } else {
                                DiscoverMediaMode.MOVIES
                            }
                        )
                        true
                    }
                    Key.Enter, Key.DirectionCenter -> {
                        if (topBarFocused) {
                            when (topBarFocusedItem(topBarFocusIndex, hasProfile)) {
                                SidebarItem.SEARCH -> onNavigateToSearch()
                                SidebarItem.HOME -> onNavigateToHome()
                                SidebarItem.DISCOVER -> Unit
                                SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                SidebarItem.SETTINGS -> onNavigateToSettings()
                                null -> Unit
                            }
                        } else if (modeFocused) {
                            modeFocused = false
                        } else {
                            focusedCard?.let { card ->
                                if (card.isMediaTitle) {
                                    onOpenMediaDetails(
                                        card.mediaType ?: MediaType.MOVIE,
                                        card.mediaId ?: return@let,
                                        card.initialSeason,
                                        card.initialEpisode
                                    )
                                } else {
                                    onOpenCatalog(card.catalogId)
                                }
                            }
                        }
                        true
                    }
                    else -> false
                }
            }
    ) {
        DiscoverBackdrop(imageUrl = focusedCard?.backdropUrl ?: focusedCard?.imageUrl)

        if (!isTouchDevice) {
            AppTopBar(
                selectedItem = SidebarItem.DISCOVER,
                isFocused = topBarFocused,
                focusedIndex = topBarFocusIndex
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = if (isTouchDevice) 24.dp else 64.dp)
                .padding(start = 36.dp, end = 38.dp, bottom = 24.dp)
        ) {
            DiscoverHeroHeader(
                focusedCard = focusedCard,
                mode = uiState.mode,
                modeFocused = modeFocused,
                onModeSelected = viewModel::setMode
            )

            Spacer(modifier = Modifier.height(6.dp))

            when {
                uiState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LoadingIndicator(size = 58.dp)
                    }
                }
                uiState.sections.isEmpty() -> {
                    DiscoverEmptyState(message = uiState.errorMessage ?: "Inga kataloger hittades.")
                }
                else -> {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(22.dp),
                        userScrollEnabled = isTouchDevice
                    ) {
                        itemsIndexed(
                            items = uiState.sections,
                            key = { _, section -> section.id },
                            contentType = { _, _ -> "discover_section" }
                        ) { sectionIndex, section ->
                            DiscoverSectionRow(
                                section = section,
                                isActiveSection = !topBarFocused && focusedSectionIndex == sectionIndex,
                                focusedCardIndex = if (!topBarFocused && !modeFocused && focusedSectionIndex == sectionIndex) {
                                    focusedCardIndex
                                } else {
                                    -1
                                },
                                onCardFocused = { cardIndex ->
                                    focusedSectionIndex = sectionIndex
                                    focusedCardIndex = cardIndex
                                    topBarFocused = false
                                },
                                streamSummaryStates = uiState.streamSummaryStates,
                                onOpenCatalog = onOpenCatalog,
                                onOpenMediaDetails = onOpenMediaDetails
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DiscoverBackdrop(imageUrl: String?) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = 0.34f }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.88f),
                            Color.Black.copy(alpha = 0.62f),
                            Color.Black.copy(alpha = 0.36f)
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.36f),
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.84f)
                        )
                    )
                )
        )
    }
}

@Composable
private fun DiscoverHeroHeader(
    focusedCard: DiscoverUiCard?,
    mode: DiscoverMediaMode,
    modeFocused: Boolean,
    onModeSelected: (DiscoverMediaMode) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 14.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = when (mode) {
                    DiscoverMediaMode.MOVIES -> "Filmer"
                    DiscoverMediaMode.SERIES -> "Serier"
                },
                style = ArflixTypography.caption.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White.copy(alpha = 0.64f),
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = focusedCard?.title ?: stringResource(R.string.discover),
                style = ArflixTypography.heroTitle.copy(
                    fontSize = 38.sp,
                    lineHeight = 42.sp,
                    fontWeight = FontWeight.Black
                ),
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = focusedCard?.subtitle
                    ?: "Valj film eller serie forst. Hitta nagot snabbt utan att blanda innehall.",
                style = ArflixTypography.body.copy(
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White.copy(alpha = 0.78f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.padding(top = 12.dp)
        ) {
            DiscoverModeChip(
                label = "Filmer",
                selected = mode == DiscoverMediaMode.MOVIES,
                focused = modeFocused && mode == DiscoverMediaMode.MOVIES,
                onClick = { onModeSelected(DiscoverMediaMode.MOVIES) }
            )
            DiscoverModeChip(
                label = "Serier",
                selected = mode == DiscoverMediaMode.SERIES,
                focused = modeFocused && mode == DiscoverMediaMode.SERIES,
                onClick = { onModeSelected(DiscoverMediaMode.SERIES) }
            )
        }
    }
}

@Composable
private fun DiscoverHeader(
    mode: DiscoverMediaMode,
    modeFocused: Boolean,
    onModeSelected: (DiscoverMediaMode) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.discover),
                style = ArflixTypography.heroTitle.copy(fontSize = 38.sp),
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Välj film eller serie först. Sedan öppnar varje kort en ren katalogvy.",
                style = ArflixTypography.body.copy(fontSize = 15.sp),
                color = Color.White.copy(alpha = 0.68f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DiscoverModeChip(
                label = "Filmer",
                selected = mode == DiscoverMediaMode.MOVIES,
                focused = modeFocused && mode == DiscoverMediaMode.MOVIES,
                onClick = { onModeSelected(DiscoverMediaMode.MOVIES) }
            )
            DiscoverModeChip(
                label = "Serier",
                selected = mode == DiscoverMediaMode.SERIES,
                focused = modeFocused && mode == DiscoverMediaMode.SERIES,
                onClick = { onModeSelected(DiscoverMediaMode.SERIES) }
            )
        }
    }
}

@Composable
private fun DiscoverModeChip(
    label: String,
    selected: Boolean,
    focused: Boolean,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(999.dp)
    val borderColor = when {
        focused -> Color.White.copy(alpha = 0.86f)
        selected -> Color.White.copy(alpha = 0.44f)
        else -> Color.White.copy(alpha = 0.12f)
    }
    Box(
        modifier = Modifier
            .clip(shape)
            .background(if (selected) Color.White.copy(alpha = 0.16f) else Color.White.copy(alpha = 0.06f), shape)
            .border(1.dp, borderColor, shape)
            .focusable()
            .padding(horizontal = 15.dp, vertical = 7.dp)
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && (event.key == Key.Enter || event.key == Key.DirectionCenter)) {
                    onClick()
                    true
                } else {
                    false
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (selected) Color.White else Color.White.copy(alpha = 0.62f),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun DiscoverSectionRow(
    section: DiscoverUiSection,
    isActiveSection: Boolean,
    focusedCardIndex: Int,
    onCardFocused: (Int) -> Unit,
    streamSummaryStates: Map<String, DiscoverStreamSummaryState>,
    onOpenCatalog: (String) -> Unit,
    onOpenMediaDetails: (MediaType, Int, Int?, Int?) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = section.title,
                    color = Color.White,
                    style = ArflixTypography.sectionTitle.copy(fontSize = 19.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = section.subtitle,
                    color = Color.White.copy(alpha = 0.58f),
                    style = ArflixTypography.caption.copy(fontSize = 11.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(modifier = Modifier.height(9.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(end = 56.dp),
            userScrollEnabled = true
        ) {
            itemsIndexed(
                items = section.cards,
                key = { _, card -> card.catalogId },
                contentType = { _, _ -> "discover_card" }
            ) { cardIndex, card ->
                DiscoverCatalogCard(
                    card = card,
                    focused = isActiveSection && focusedCardIndex == cardIndex,
                    streamSummaryLabel = discoverStreamSummaryLabel(streamSummaryStates[discoverStreamSummaryKey(card)]),
                    onFocused = { onCardFocused(cardIndex) },
                    onOpen = {
                        if (card.isMediaTitle) {
                            onOpenMediaDetails(
                                card.mediaType ?: MediaType.MOVIE,
                                card.mediaId ?: return@DiscoverCatalogCard,
                                card.initialSeason,
                                card.initialEpisode
                            )
                        } else {
                            onOpenCatalog(card.catalogId)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun DiscoverCatalogCard(
    card: DiscoverUiCard,
    focused: Boolean,
    streamSummaryLabel: String?,
    onFocused: () -> Unit,
    onOpen: () -> Unit
) {
    val shape = RoundedCornerShape(8.dp)
    val cardWidth = if (card.isMediaTitle) 128.dp else 224.dp
    val cardAspectRatio = if (card.isMediaTitle) 2f / 3f else 16f / 9f
    Box(
        modifier = Modifier
            .width(cardWidth)
            .aspectRatio(cardAspectRatio)
            .clip(shape)
            .background(Color.White.copy(alpha = 0.08f), shape)
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = if (focused) Color.White.copy(alpha = 0.92f) else Color.White.copy(alpha = 0.12f),
                shape = shape
            )
            .graphicsLayer {
                scaleX = if (focused) 1.035f else 1f
                scaleY = if (focused) 1.035f else 1f
            }
            .onFocusChanged { if (it.isFocused) onFocused() }
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && (event.key == Key.Enter || event.key == Key.DirectionCenter)) {
                    onOpen()
                    true
                } else {
                    false
                }
            }
            .padding(0.dp),
    ) {
        AsyncImage(
            model = card.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.22f),
                            Color.Black.copy(alpha = 0.76f)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(9.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(RoundedCornerShape(99.dp))
                        .background(Color(card.accent))
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = card.sourceLabel.uppercase(),
                    color = Color.White.copy(alpha = 0.68f),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.title,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = streamSummaryLabel ?: card.subtitle,
                color = Color.White.copy(alpha = 0.58f),
                fontSize = 9.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (card.progress in 1..99) {
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .clip(RoundedCornerShape(99.dp))
                        .background(Color.White.copy(alpha = 0.20f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(card.progress / 100f)
                            .height(3.dp)
                            .background(Color(card.accent))
                    )
                }
            }
        }
    }
}

@Composable
private fun DiscoverEmptyState(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Explore,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.24f),
            modifier = Modifier.size(74.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            color = Color.White.copy(alpha = 0.64f),
            style = ArflixTypography.body
        )
    }
}
