package com.arflix.tv.ui.screens.details

import android.content.Context
import android.util.Log
import com.arflix.tv.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.PersonDetails
import com.arflix.tv.data.model.Review
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TraktComment
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.HomeServerRepository
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.util.Constants
import com.arflix.tv.util.settingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale
import com.arflix.tv.core.plugin.PluginManager
import com.arflix.tv.domain.model.LocalScraperResult
import javax.inject.Inject

data class DetailsUiState(
    val isLoading: Boolean = true,
    val item: MediaItem? = null,
    val imdbId: String? = null,  // Real IMDB ID for stream resolution
    val tvdbId: Int? = null,     // TVDB ID for Kitsu anime mapping
    val logoUrl: String? = null,
    val trailerKey: String? = null,
    val episodes: List<Episode> = emptyList(),
    val totalSeasons: Int = 1,
    val currentSeason: Int = 1,
    val cast: List<CastMember> = emptyList(),
    val similar: List<MediaItem> = emptyList(),
    val similarLogoUrls: Map<String, String> = emptyMap(),
    val reviews: List<Review> = emptyList(),
    val error: String? = null,
    // Person modal
    val showPersonModal: Boolean = false,
    val selectedPerson: PersonDetails? = null,
    val isLoadingPerson: Boolean = false,
    // Streams
    val streams: List<StreamSource> = emptyList(),
    val subtitles: List<Subtitle> = emptyList(),
    val isLoadingStreams: Boolean = false,
    val streamSearchStartTime: Long = 0L,
    val pluginScrapersLoading: Boolean = false,
    val loadingPluginNames: Set<String> = emptySet(),
    val completedAddons: Int = 0,
    val totalAddons: Int = 0,
    val hasStreamingAddons: Boolean = true,
    val addonOrderedIds: List<String> = emptyList(),
    val isInWatchlist: Boolean = false,
    // Toast
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.INFO,
    // Genre names
    val genres: List<String> = emptyList(),
    val language: String? = null,
    // Budget (movies only)
    val budget: String? = null,
    // Show status
    val showStatus: String? = null,
    // Streaming services from TMDB watch providers
    val streamingServices: List<StreamingServiceUi> = emptyList(),
    val providerRegion: String? = null,
    // Initial positions for Continue Watching navigation
    val initialEpisodeIndex: Int = 0,
    val initialSeasonIndex: Int = 0,
    // Season progress: Map<seasonNumber, Pair<watchedCount, totalCount>>
    val seasonProgress: Map<Int, Pair<Int, Int>> = emptyMap(),
    val playSeason: Int? = null,
    val playEpisode: Int? = null,
    val playLabel: String? = null,
    val playPositionMs: Long? = null,
    val autoPlaySingleSource: Boolean = true,
    val autoPlayMinQuality: String = "Any",
    // TMDB collection (franchise) info — populated for movies that belong to a collection
    val collectionId: Int? = null,
    val collectionName: String? = null,
    val collectionItems: List<MediaItem> = emptyList(),
    val collectionPosterPath: String? = null
)

data class StreamingServiceUi(
    val name: String,
    val logoUrl: String? = null
)

private data class PlayTarget(
    val season: Int? = null,
    val episode: Int? = null,
    val label: String,
    val positionMs: Long? = null
)

private data class SeasonProgressResult(
    val progress: Map<Int, Pair<Int, Int>>,
    val hasWatched: Boolean,
    val nextUnwatched: Pair<Int, Int>?
)

private data class ResumeInfo(
    val season: Int? = null,
    val episode: Int? = null,
    val label: String,
    val positionMs: Long
)

// TMDB Genre mappings
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

private val languages = mapOf(
    "en" to "English", "es" to "Spanish", "fr" to "French", "de" to "German",
    "it" to "Italian", "pt" to "Portuguese", "ja" to "Japanese", "ko" to "Korean",
    "zh" to "Chinese", "hi" to "Hindi", "ru" to "Russian", "ar" to "Arabic",
    "nl" to "Dutch", "sv" to "Swedish", "pl" to "Polish", "tr" to "Turkish",
    "th" to "Thai", "vi" to "Vietnamese", "id" to "Indonesian", "tl" to "Tagalog"
)

/**
 * Format budget number to human-readable string
 */
private fun formatBudget(budget: Long): String {
    return when {
        budget >= 1_000_000_000 -> "$${budget / 1_000_000_000.0}B"
        budget >= 1_000_000 -> "$${budget / 1_000_000}M"
        budget >= 1_000 -> "$${budget / 1_000}K"
        else -> "$$budget"
    }
}

enum class ToastType {
    SUCCESS, ERROR, INFO
}

private fun isSupplementalStream(stream: StreamSource): Boolean =
    stream.addonId == "iptv_xtream_vod" || stream.addonId == HomeServerRepository.ADDON_ID

private fun Addon.isVodStreamingAddon(): Boolean =
    isEnabled &&
        type != AddonType.SUBTITLE &&
        !SportsAddonCapabilities.isSportsLiveTvAddon(this)

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mediaRepository: MediaRepository,
    private val pluginManager: PluginManager,
    private val profileManager: ProfileManager,
    private val traktRepository: TraktRepository,
    private val streamRepository: StreamRepository,
    private val tmdbApi: TmdbApi,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val watchlistRepository: WatchlistRepository,
    private val cloudSyncRepository: CloudSyncRepository,
    private val launcherContinueWatchingRepository: LauncherContinueWatchingRepository
) : ViewModel() {

    companion object {
        private const val TAG = "DetailsViewModel"
        private const val MIN_COMMUNITY_REVIEW_CHARS = 40
        private const val MAX_COMMUNITY_REVIEW_CHARS = 1400
        private const val MIN_COMMUNITY_REVIEW_WORDS = 8
        private const val MIN_COMMUNITY_REVIEW_COUNT = 1
    }

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    private var currentMediaType: MediaType = MediaType.MOVIE
    private var currentMediaId: Int = 0
    private var vodAppendJob: kotlinx.coroutines.Job? = null
    private var homeServerAppendJob: kotlinx.coroutines.Job? = null
    private var loadStreamsJob: kotlinx.coroutines.Job? = null
    private var loadStreamsRequestId: Long = 0L
    private var focusedStreamPrewarmJob: kotlinx.coroutines.Job? = null
    private var streamListPrewarmJob: kotlinx.coroutines.Job? = null
    private var lastStreamListPrewarmKey: String = ""
    /** Set to true after loadDetails() child coroutines finish populating episodes/seasons. */
    @Volatile private var initialLoadComplete = false
    private fun autoPlaySingleSourceKey() = profileManager.profileBooleanKey("auto_play_single_source")
    private fun autoPlayMinQualityKey() = profileManager.profileStringKey("auto_play_min_quality")
    private fun showBudgetKey() = profileManager.profileBooleanKey("show_budget_on_home")

    private fun isBlankRating(value: String): Boolean {
        return value.isBlank() || value == "0.0" || value == "0"
    }

    private fun normalizeAutoPlayMinQuality(raw: String?): String {
        return when (raw?.trim()?.lowercase()) {
            "any" -> "Any"
            "720p", "hd" -> "720p"
            "1080p", "fullhd", "fhd" -> "1080p"
            "4k", "2160p", "uhd" -> "4K"
            else -> "Any"
        }
    }

    private fun mergeItem(primary: MediaItem, fallback: MediaItem?): MediaItem {
        if (fallback == null) return primary
        return primary.copy(
            title = primary.title.ifBlank { fallback.title },
            subtitle = primary.subtitle.ifBlank { fallback.subtitle },
            overview = primary.overview.ifBlank { fallback.overview },
            year = primary.year.ifBlank { fallback.year },
            releaseDate = primary.releaseDate ?: fallback.releaseDate,
            rating = primary.rating.ifBlank { fallback.rating },
            duration = primary.duration.ifBlank { fallback.duration },
            imdbRating = if (isBlankRating(primary.imdbRating)) fallback.imdbRating else primary.imdbRating,
            tmdbRating = if (isBlankRating(primary.tmdbRating)) fallback.tmdbRating else primary.tmdbRating,
            image = primary.image.ifBlank { fallback.image },
            backdrop = primary.backdrop ?: fallback.backdrop,
            primaryNetworkLogo = primary.primaryNetworkLogo ?: fallback.primaryNetworkLogo,
            genreIds = if (primary.genreIds.isEmpty()) fallback.genreIds else primary.genreIds,
            originalLanguage = primary.originalLanguage ?: fallback.originalLanguage,
            isOngoing = primary.isOngoing || fallback.isOngoing,
            totalEpisodes = primary.totalEpisodes ?: fallback.totalEpisodes,
            watchedEpisodes = primary.watchedEpisodes ?: fallback.watchedEpisodes,
            budget = primary.budget ?: fallback.budget,
            revenue = primary.revenue ?: fallback.revenue,
            status = primary.status ?: fallback.status
        )
    }

    fun loadDetails(mediaType: MediaType, mediaId: Int, initialSeason: Int? = null, initialEpisode: Int? = null) {
        currentMediaType = mediaType
        currentMediaId = mediaId
        initialLoadComplete = false
        vodAppendJob?.cancel()
        homeServerAppendJob?.cancel()
        streamListPrewarmJob?.cancel()
        focusedStreamPrewarmJob?.cancel()
        lastStreamListPrewarmKey = ""

        viewModelScope.launch {
            try {
                val prefs = context.settingsDataStore.data.first()
                val autoPlaySingleSource = prefs[autoPlaySingleSourceKey()] ?: true
                val autoPlayMinQuality = normalizeAutoPlayMinQuality(prefs[autoPlayMinQualityKey()])
                val showBudget = prefs[showBudgetKey()] ?: true

                val previousState = _uiState.value
                val previousMatches = previousState.item?.id == mediaId &&
                    previousState.item?.mediaType == mediaType
                val seasonToLoad = initialSeason ?: 1
                val hasExplicitEpisodeTarget = mediaType == MediaType.TV && initialSeason != null && initialEpisode != null
                val previousItem = _uiState.value.item?.takeIf {
                    it.id == mediaId && it.mediaType == mediaType
                }
                val cachedFullItem = mediaRepository.getCachedFullItem(mediaType, mediaId)
                val cachedItem = cachedFullItem ?: mediaRepository.getCachedItem(mediaType, mediaId)
                val initialItem = cachedItem ?: previousItem
                val cachedLogoUrl = mediaRepository.peekCachedLogoUrl(mediaType, mediaId)
                    ?: previousState.logoUrl?.takeIf { previousMatches }
                val cachedEpisodes = if (mediaType == MediaType.TV) {
                    mediaRepository.peekCachedSeasonEpisodes(mediaId, seasonToLoad)
                        ?: previousState.episodes.takeIf { previousMatches && previousState.currentSeason == seasonToLoad }
                } else null
                val cachedTotalSeasons = if (mediaType == MediaType.TV) {
                    cachedFullItem?.totalEpisodes?.coerceAtLeast(1)
                        ?: previousState.totalSeasons.takeIf { previousMatches && !previousState.isLoading }
                        ?: 1
                } else {
                    1
                }

                _uiState.value = DetailsUiState(
                    isLoading = initialItem == null,
                    item = initialItem,
                    logoUrl = cachedLogoUrl,
                    episodes = cachedEpisodes ?: emptyList(),
                    currentSeason = seasonToLoad,
                    totalSeasons = cachedTotalSeasons,
                    playSeason = initialSeason,
                    playEpisode = initialEpisode,
                    playLabel = if (mediaType == MediaType.TV && initialSeason != null && initialEpisode != null) {
                        context.getString(R.string.continue_season_episode, initialSeason, initialEpisode)
                    } else {
                        null
                    },
                    autoPlaySingleSource = autoPlaySingleSource,
                    autoPlayMinQuality = autoPlayMinQuality
                )

                fun logDetailsLoadFailure(label: String, throwable: Throwable) {
                    if (throwable is CancellationException) throw throwable
                    Log.w(TAG, "Failed to load details $label", throwable)
                }

                suspend fun <T> loadDetailsPart(label: String, block: suspend () -> T): T? {
                    return runCatching { block() }
                        .onFailure { logDetailsLoadFailure(label, it) }
                        .getOrNull()
                }

                val itemDeferred = async {
                    loadDetailsPart("item") {
                        if (mediaType == MediaType.TV) {
                            mediaRepository.getTvDetails(mediaId)
                        } else {
                            mediaRepository.getMovieDetails(mediaId)
                        }
                    }
                }
                val watchlistDeferred = async {
                    loadDetailsPart("watchlist") {
                        watchlistRepository.isInWatchlist(mediaType, mediaId)
                    } ?: false
                }
                // Fetch real IMDB ID and TVDB ID from TMDB external_ids endpoint
                val externalIdsDeferred = async { resolveExternalIds(mediaType, mediaId) }
                val resumeDeferred = async { fetchResumeInfo(mediaId, mediaType, initialSeason, initialEpisode) }
                // Fetch logo URL concurrently with details to avoid ~1s delay
                val logoDeferred = async { mediaRepository.getLogoUrl(mediaType, mediaId) }

                // For TV shows, also load episodes
                val episodesDeferred = if (mediaType == MediaType.TV) {
                    async {
                        loadDetailsPart("season $seasonToLoad episodes") {
                            mediaRepository.getSeasonEpisodes(mediaId, seasonToLoad)
                        } ?: emptyList<Episode>()
                    }
                } else null

                // For TV shows, fetch season progress (watched/total per season).
                // IMPORTANT: Initialize watched cache FIRST so fetchSeasonProgress()
                // can read from the in-memory cache rather than falling back to a
                // backend query that may return stale or empty data. The async below
                // starts immediately, but initializeWatchedCache() runs synchronously
                // before it, ensuring the cache is populated before fetchSeasonProgress
                // checks getWatchedEpisodesFromCache().
                if (mediaType == MediaType.TV) {
                    runCatching { traktRepository.initializeWatchedCache() }
                }
                val seasonProgressDeferred = if (mediaType == MediaType.TV) {
                    async { fetchSeasonProgress(mediaId) }
                } else null

                val requestMediaId = mediaId
                val requestMediaType = mediaType
                fun isCurrentRequest(): Boolean {
                    return currentMediaId == requestMediaId && currentMediaType == requestMediaType
                }
                fun updateState(block: (DetailsUiState) -> DetailsUiState) {
                    if (!isCurrentRequest()) return
                    _uiState.value = block(_uiState.value)
                }

                if (mediaType == MediaType.TV) {
                    launch {
                        val firstEpisodes = runCatching { episodesDeferred?.await() }.getOrNull()
                        if (!firstEpisodes.isNullOrEmpty()) {
                            updateState { state ->
                                if (state.currentSeason == seasonToLoad && state.episodes == firstEpisodes) {
                                    state
                                } else {
                                    state.copy(
                                        episodes = firstEpisodes,
                                        currentSeason = seasonToLoad
                                    )
                                }
                            }
                        }
                    }
                }

                val loadedItem = runCatching { itemDeferred.await() }.getOrNull()
                val item = loadedItem ?: initialItem
                if (item == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = context.getString(R.string.details_error_load_failed)
                    )
                    return@launch
                }
                val mergedItem = mergeItem(item, initialItem)
                val hasTrustedTvDetails = mediaType != MediaType.TV || loadedItem != null || cachedFullItem != null

                // Get total seasons for TV shows (stored in totalEpisodes field)
                val totalSeasons = if (mediaType == MediaType.TV) {
                    if (hasTrustedTvDetails) {
                        mergedItem.totalEpisodes?.coerceAtLeast(1) ?: 1
                    } else {
                        1
                    }
                } else 1

                // Map genre IDs to names
                val genreMap = if (mediaType == MediaType.TV) tvGenres else movieGenres
                val genreNames = mergedItem.genreIds.mapNotNull { genreMap[it] }.take(4)

                // Get language name
                val languageName = mergedItem.originalLanguage?.let { languages[it] ?: it.uppercase() }

                // Format budget for movies
                val budgetDisplay = if (mediaType == MediaType.MOVIE && mergedItem.budget != null && mergedItem.budget > 0) {
                    formatBudget(mergedItem.budget)
                } else null
                val visibleBudget = if (showBudget) budgetDisplay else null

                // Get show status
                val showStatus = if (mediaType == MediaType.TV) mergedItem.status else null

                // Initialize watched cache lazily to avoid wiping optimistic local state.
                if (runCatching { traktRepository.getWatchedEpisodesFromCache().isEmpty() }.getOrDefault(true)) {
                    runCatching { traktRepository.initializeWatchedCache() }
                }

                // Check if item is watched (for movies, check Trakt; for TV, check if started)
                val isWatched = if (mediaType == MediaType.MOVIE) {
                    traktRepository.isMovieWatched(mediaId)
                } else {
                    // For TV shows, check if any episode is watched
                    traktRepository.hasWatchedEpisodes(mediaId)
                }
                val itemWithWatchedStatus = mergedItem.copy(isWatched = isWatched)

                val baseState = _uiState.value.copy(
                    isLoading = false,
                    item = itemWithWatchedStatus,
                    totalSeasons = totalSeasons,
                    currentSeason = seasonToLoad,
                    genres = genreNames,
                    language = languageName,
                    budget = visibleBudget,
                    showStatus = showStatus
                )
                _uiState.value = baseState

                // Calculate initial season index (0-based)
                val initialSeasonIndex = (seasonToLoad - 1).coerceAtLeast(0)
                updateState { it.copy(initialSeasonIndex = initialSeasonIndex) }

                launch {
                    val externalIds = runCatching { externalIdsDeferred.await() }.getOrNull()
                    val imdbId = externalIds?.imdbId
                    val tvdbId = externalIds?.tvdbId
                    if (!imdbId.isNullOrBlank()) {
                        mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
                        updateState { state -> state.copy(imdbId = imdbId, tvdbId = tvdbId) }
                        // Prefetch streams in background as soon as IMDB ID is available.
                        // When the user presses Play or opens Sources, results are already
                        // cached in StreamRepository, so loading appears near-instant.
                        val prefetchSeason = if (mediaType == MediaType.TV) (initialSeason ?: 1) else null
                        val prefetchEpisode = if (mediaType == MediaType.TV) (initialEpisode ?: 1) else null
                        prefetchStreamsInBackground(imdbId, prefetchSeason, prefetchEpisode)

                        launch {
                            val imdbRating = runCatching {
                                mediaRepository.getImdbRating(mediaType, mediaId, imdbId)
                            }.getOrNull()
                            if (!imdbRating.isNullOrBlank()) {
                                updateState { state ->
                                    state.copy(item = state.item?.copy(imdbRating = imdbRating))
                                }
                            }
                        }

                    } else if (tvdbId != null) {
                        updateState { state -> state.copy(tvdbId = tvdbId) }
                    }
                }

                // Logo URL was fetched concurrently with details via logoDeferred
                launch {
                    val logoUrl = runCatching { logoDeferred.await() }.getOrNull()
                    if (logoUrl != null && logoUrl != _uiState.value.logoUrl) {
                        updateState { state -> state.copy(logoUrl = logoUrl) }
                    }
                }

                launch {
                    delay(180L)
                    val trailerKey = runCatching { mediaRepository.getTrailerKey(mediaType, mediaId) }.getOrNull()
                    if (trailerKey != null) {
                        updateState { state -> state.copy(trailerKey = trailerKey) }
                    }
                }

                launch {
                    delay(220L)
                    val cast = runCatching { mediaRepository.getCast(mediaType, mediaId) }.getOrNull()
                    if (!cast.isNullOrEmpty()) {
                        updateState { state -> state.copy(cast = cast) }
                    }
                }

                launch {
                    delay(320L)
                    val similar = runCatching { mediaRepository.getSimilar(mediaType, mediaId) }.getOrNull()
                    if (!similar.isNullOrEmpty()) {
                        val logos = similar.take(8).map { item ->
                            async {
                                val key = "${item.mediaType}_${item.id}"
                                val logo = runCatching {
                                    mediaRepository.getLogoUrl(item.mediaType, item.id)
                                }.getOrNull()
                                if (logo.isNullOrBlank()) null else key to logo
                            }
                        }.mapNotNull { runCatching { it.await() }.getOrNull() }.toMap()
                        updateState { state ->
                            state.copy(
                                similar = similar,
                                similarLogoUrls = logos
                            )
                        }
                    }
                }

                launch {
                    delay(420L)
                    val externalIds = runCatching { externalIdsDeferred.await() }.getOrNull()
                    val reviews = runCatching {
                        loadCommunityReviews(mediaType, mediaId, externalIds?.imdbId)
                    }.getOrNull()
                    if (!reviews.isNullOrEmpty()) {
                        updateState { state -> state.copy(reviews = reviews) }
                    }
                }

                // TMDB collection (franchise) — only for movies
                launch {
                    if (mediaType != MediaType.MOVIE) return@launch
                    val collectionRef = runCatching {
                        mediaRepository.getMovieCollectionRef(mediaId)
                    }.getOrNull()
                    if (collectionRef != null) {
                        updateState { state ->
                            state.copy(
                                collectionId = collectionRef.id,
                                collectionName = collectionRef.name,
                                collectionPosterPath = collectionRef.posterPath
                            )
                        }
                        // Fetch collection items in background
                        launch {
                            val items = runCatching {
                                mediaRepository.getTmdbCollectionItems(collectionRef.id)
                            }.getOrNull() ?: emptyList()
                            updateState { state ->
                                if (state.collectionId == collectionRef.id) {
                                    state.copy(collectionItems = items)
                                } else state
                            }
                        }
                    }
                }

                launch {
                    delay(260L)
                    val servicesResult = runCatching {
                        mediaRepository.getStreamingServices(
                            mediaType = mediaType,
                            mediaId = mediaId,
                            preferredRegion = Locale.getDefault().country
                        )
                    }.getOrNull()
                    if (servicesResult != null) {
                        updateState { state ->
                            state.copy(
                                streamingServices = servicesResult.services.map {
                                    StreamingServiceUi(
                                        name = it.name,
                                        logoUrl = it.logoUrl
                                    )
                                },
                                providerRegion = servicesResult.region
                            )
                        }
                    }
                }

                launch {
                    val episodes = runCatching { episodesDeferred?.await() }.getOrNull()
                    if (!episodes.isNullOrEmpty()) {
                        val resumeTarget = runCatching { resumeDeferred.await() }.getOrNull()
                        val fallbackTargetSeason = initialSeason ?: resumeTarget?.season
                        val fallbackTargetEpisode = initialEpisode ?: resumeTarget?.episode

                        // Decorate episodes with watched status from cache
                        val watchedKeys = runCatching {
                            traktRepository.getWatchedEpisodesForShow(mediaId)
                        }.getOrDefault(emptySet())
                        val decoratedEpisodes = if (watchedKeys.isNotEmpty()) {
                            episodes.map { ep ->
                                val key = "show_tmdb:$mediaId:${ep.seasonNumber}:${ep.episodeNumber}"
                                if (watchedKeys.contains(key)) ep.copy(isWatched = true) else ep
                            }
                        } else if (fallbackTargetSeason != null) {
                            episodes.map { ep ->
                                when {
                                    ep.seasonNumber < fallbackTargetSeason -> ep.copy(isWatched = true)
                                    ep.seasonNumber == fallbackTargetSeason -> ep.copy(
                                        isWatched = ep.episodeNumber < (fallbackTargetEpisode ?: 1)
                                    )
                                    else -> ep
                                }
                            }
                        } else {
                            val seasonNum = episodes.firstOrNull()?.seasonNumber
                            val progress = seasonNum?.let { _uiState.value.seasonProgress[it] }
                            val isFullyWatchedSeason = progress != null && progress.second > 0 && progress.first >= progress.second
                            if (isFullyWatchedSeason) {
                                episodes.map { it.copy(isWatched = true) }
                            } else {
                                episodes
                            }
                        }

                        val targetEpisodeForRow = if (initialSeason == seasonToLoad) initialEpisode else null
                        val nextUnwatchedEpisode = decoratedEpisodes.firstOrNull { !it.isWatched }
                        // Focus the explicit target episode if the caller passed one (deeplink /
                        // Continue Watching tile), otherwise focus the first unwatched episode so
                        // users don't have to scroll past every episode they've already seen.
                        // When every episode in the current season is watched we fall back to
                        // the first episode (index 0) — from there the user can jump to the next
                        // season via the season selector. See issue #117.
                        val initialEpisodeIndex = when {
                            targetEpisodeForRow != null ->
                                decoratedEpisodes.indexOfFirst { it.episodeNumber == targetEpisodeForRow }.coerceAtLeast(0)
                            nextUnwatchedEpisode != null ->
                                decoratedEpisodes.indexOf(nextUnwatchedEpisode).coerceAtLeast(0)
                            else -> 0
                        }
                        val hasWatchedEpisodes = decoratedEpisodes.any { it.isWatched }
                        updateState { state ->
                            val shouldUseEpisodeTarget = !hasExplicitEpisodeTarget &&
                                (state.playLabel.isNullOrBlank() || state.playLabel == context.getString(R.string.play_start_s1e1))
                            state.copy(
                                episodes = decoratedEpisodes,
                                initialEpisodeIndex = initialEpisodeIndex,
                                playSeason = if (shouldUseEpisodeTarget) {
                                    nextUnwatchedEpisode?.seasonNumber ?: if (hasWatchedEpisodes) 1 else state.playSeason
                                } else state.playSeason,
                                playEpisode = if (shouldUseEpisodeTarget) {
                                    nextUnwatchedEpisode?.episodeNumber ?: if (hasWatchedEpisodes) 1 else state.playEpisode
                                } else state.playEpisode,
                                playLabel = if (shouldUseEpisodeTarget) {
                                    if (nextUnwatchedEpisode != null) {
                                        context.getString(R.string.continue_season_episode, nextUnwatchedEpisode.seasonNumber, nextUnwatchedEpisode.episodeNumber)
                                    } else if (hasWatchedEpisodes) {
                                        context.getString(R.string.play_start_s1e1)
                                    } else {
                                        state.playLabel
                                    }
                                } else state.playLabel
                            )
                        }
                    }
                    initialLoadComplete = true
                }

                launch {
                    val isInWatchlist = runCatching { watchlistDeferred.await() }.getOrDefault(false)
                    updateState { state -> state.copy(isInWatchlist = isInWatchlist) }
                }

                launch {
                    val seasonProgressResult = runCatching { seasonProgressDeferred?.await() }.getOrNull()
                    val baseSeasonProgress = seasonProgressResult?.progress ?: emptyMap()
                    val resumeTarget = runCatching { resumeDeferred.await() }.getOrNull()
                    val fallbackTargetSeason = initialSeason ?: resumeTarget?.season
                    val fallbackTargetEpisode = initialEpisode ?: resumeTarget?.episode
                    val seasonProgress = if (
                        fallbackTargetSeason != null &&
                        baseSeasonProgress.values.none { it.first > 0 }
                    ) {
                        val derived = baseSeasonProgress.toMutableMap()
                        val targetSeason = fallbackTargetSeason
                        val targetEpisode = fallbackTargetEpisode ?: 1
                        for ((seasonNum, counts) in baseSeasonProgress) {
                            val totalCount = counts.second
                            val watchedCount = when {
                                seasonNum < targetSeason -> totalCount
                                seasonNum == targetSeason -> (targetEpisode - 1).coerceIn(0, totalCount)
                                else -> 0
                            }
                            derived[seasonNum] = Pair(watchedCount, totalCount)
                        }
                        derived
                    } else {
                        baseSeasonProgress
                    }
                    val resolvedTotalSeasons = if (mediaType == MediaType.TV) {
                        maxOf(baseState.totalSeasons, seasonProgress.keys.maxOrNull() ?: 0, 1)
                    } else {
                        baseState.totalSeasons
                    }
                    updateState { state ->
                        state.copy(
                            seasonProgress = seasonProgress,
                            totalSeasons = resolvedTotalSeasons
                        )
                    }
                }

                launch {
                    val resumeInfo = runCatching { resumeDeferred.await() }.getOrNull()
                    if (initialSeason != null && initialEpisode != null && mediaType == MediaType.TV) {
                        val matchedResume = resumeInfo?.takeIf {
                            it.season == initialSeason && it.episode == initialEpisode
                        }
                        updateState { state ->
                            state.copy(
                                playSeason = initialSeason,
                                playEpisode = initialEpisode,
                                playLabel = matchedResume?.label ?: context.getString(R.string.continue_season_episode, initialSeason, initialEpisode),
                                playPositionMs = matchedResume?.positionMs
                            )
                        }
                        return@launch
                    }
                    if (resumeInfo != null) {
                        // Fast path: show Continue immediately from local history.
                        val playTarget = buildPlayTarget(mediaType, null, resumeInfo)
                        updateState { state ->
                            state.copy(
                                playSeason = playTarget?.season,
                                playEpisode = playTarget?.episode,
                                playLabel = playTarget?.label,
                                playPositionMs = playTarget?.positionMs
                            )
                        }
                    } else {
                        val seasonProgressResult = runCatching { seasonProgressDeferred?.await() }.getOrNull()
                        val playTarget = buildPlayTarget(mediaType, seasonProgressResult, null)
                        updateState { state ->
                            state.copy(
                                playSeason = playTarget?.season,
                                playEpisode = playTarget?.episode,
                                playLabel = playTarget?.label,
                                playPositionMs = playTarget?.positionMs
                            )
                        }
                    }
                }

                if (mediaType == MediaType.TV) {
                    launch {
                        val titleForPrefetch = baseState.item?.title.orEmpty().ifBlank { mergedItem.title }
                        if (titleForPrefetch.isBlank()) {
                            return@launch
                        }
                        // Start immediately with TMDB/title so resolver can warm caches ASAP.
                        runCatching {
                            streamRepository.prefetchSeriesVodInfo(
                                imdbId = null,
                                title = titleForPrefetch,
                                tmdbId = mediaId
                            )
                        }.onFailure { logDetailsLoadFailure("series VOD prefetch", it) }
                        val externalIds = runCatching { externalIdsDeferred.await() }.getOrNull()
                        runCatching {
                            streamRepository.prefetchSeriesVodInfo(
                                imdbId = externalIds?.imdbId,
                                title = titleForPrefetch,
                                tmdbId = mediaId
                            )
                        }.onFailure { logDetailsLoadFailure("series VOD prefetch with IMDB ID", it) }
                        val resumeInfo = runCatching { resumeDeferred.await() }.getOrNull()
                        val loadedEpisodes = runCatching { episodesDeferred?.await() }.getOrNull().orEmpty()
                        val targetSeason = initialSeason
                            ?: resumeInfo?.season
                            ?: loadedEpisodes.firstOrNull()?.seasonNumber
                            ?: seasonToLoad
                        val targetEpisode = initialEpisode
                            ?: resumeInfo?.episode
                            ?: loadedEpisodes.firstOrNull()?.episodeNumber
                            ?: 1
                        runCatching {
                            streamRepository.prefetchEpisodeVod(
                                imdbId = externalIds?.imdbId,
                                season = targetSeason,
                                episode = targetEpisode,
                                title = titleForPrefetch,
                                tmdbId = mediaId
                            )
                        }.onFailure { logDetailsLoadFailure("episode VOD prefetch", it) }
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun loadSeason(seasonNumber: Int) {
        if (currentMediaType != MediaType.TV) return
        // Don't reload if already on this season
        if (_uiState.value.currentSeason == seasonNumber && _uiState.value.episodes.isNotEmpty()) return

        viewModelScope.launch {
            // Keep current episodes visible while loading new ones
            val currentEpisodes = _uiState.value.episodes

            try {
                val episodes = mediaRepository.getSeasonEpisodes(currentMediaId, seasonNumber)
                if (episodes.isNotEmpty()) {
                    // Decorate episodes with watched status from cache
                    val watchedKeys = runCatching {
                        traktRepository.getWatchedEpisodesForShow(currentMediaId)
                    }.getOrDefault(emptySet())
                    val decoratedEpisodes = if (watchedKeys.isNotEmpty()) {
                        episodes.map { ep ->
                            val key = "show_tmdb:$currentMediaId:${ep.seasonNumber}:${ep.episodeNumber}"
                            if (watchedKeys.contains(key)) ep.copy(isWatched = true) else ep
                        }
                    } else {
                        val progress = _uiState.value.seasonProgress[seasonNumber]
                        val isFullyWatchedSeason = progress != null && progress.second > 0 && progress.first >= progress.second
                        if (isFullyWatchedSeason) {
                            episodes.map { it.copy(isWatched = true) }
                        } else {
                            episodes
                        }
                    }

                    _uiState.value = _uiState.value.copy(
                        episodes = decoratedEpisodes,
                        currentSeason = seasonNumber
                    )
                } else {
                    // If no episodes returned, keep current and show error
                    _uiState.value = _uiState.value.copy(
                        toastMessage = context.getString(R.string.details_no_episodes_season, seasonNumber),
                        toastType = ToastType.ERROR
                    )
                }
            } catch (e: Exception) {
                // On error, keep showing current episodes
                _uiState.value = _uiState.value.copy(
                    toastMessage = context.getString(R.string.details_failed_load_season, seasonNumber),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun toggleWatched(episodeIndex: Int? = null) {
        val currentItem = _uiState.value.item ?: return

        viewModelScope.launch {
            try {
                if (currentMediaType == MediaType.MOVIE) {
                    val newWatched = !currentItem.isWatched
                    if (newWatched) {
                        traktRepository.markMovieWatched(currentMediaId)
                    } else {
                        traktRepository.markMovieUnwatched(currentMediaId)
                    }
                    _uiState.value = _uiState.value.copy(
                        item = currentItem.copy(isWatched = newWatched),
                        toastMessage = if (newWatched) context.getString(R.string.details_marked_watched) else context.getString(R.string.details_marked_unwatched),
                        toastType = ToastType.SUCCESS
                    )
                    runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                } else {
                    val targetEpisode = _uiState.value.episodes.getOrNull(episodeIndex ?: 0)
                    if (targetEpisode == null) {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = context.getString(R.string.details_no_episode_selected),
                            toastType = ToastType.ERROR
                        )
                        return@launch
                    }

                    val episodeWatched = !targetEpisode.isWatched
                    if (episodeWatched) {
                        traktRepository.markEpisodeWatched(
                            currentMediaId,
                            targetEpisode.seasonNumber,
                            targetEpisode.episodeNumber
                        )
                        watchHistoryRepository.removeFromHistory(
                            currentMediaId,
                            targetEpisode.seasonNumber,
                            targetEpisode.episodeNumber
                        )

                        // Save the NEXT episode to CW (local + cloud) so it appears on all devices
                        try {
                            val nextEp = targetEpisode.episodeNumber + 1
                            traktRepository.saveLocalContinueWatching(
                                mediaType = MediaType.TV,
                                tmdbId = currentMediaId,
                                title = currentItem.title,
                                posterPath = currentItem.image,
                                backdropPath = currentItem.backdrop,
                                season = targetEpisode.seasonNumber,
                                episode = nextEp,
                                episodeTitle = null,
                                progress = 3,
                                positionSeconds = 0L,
                                durationSeconds = 1L,
                                year = currentItem.year
                            )
                            watchHistoryRepository.saveProgress(
                                mediaType = MediaType.TV,
                                tmdbId = currentMediaId,
                                title = currentItem.title,
                                poster = currentItem.image,
                                backdrop = currentItem.backdrop,
                                season = targetEpisode.seasonNumber,
                                episode = nextEp,
                                episodeTitle = null,
                                progress = 0.01f,
                                duration = 0L,
                                position = 0L
                            )
                        } catch (_: Exception) {}
                    } else {
                        traktRepository.markEpisodeUnwatched(
                            currentMediaId,
                            targetEpisode.seasonNumber,
                            targetEpisode.episodeNumber
                        )
                    }

                    val updatedEpisodes = _uiState.value.episodes.map { ep ->
                        if (ep.seasonNumber == targetEpisode.seasonNumber &&
                            ep.episodeNumber == targetEpisode.episodeNumber
                        ) {
                            ep.copy(isWatched = episodeWatched)
                        } else {
                            ep
                        }
                    }

                    val anyWatched = updatedEpisodes.any { it.isWatched }
                    _uiState.value = _uiState.value.copy(
                        item = currentItem.copy(isWatched = anyWatched),
                        episodes = updatedEpisodes,
                        toastMessage = if (episodeWatched) {
                            context.getString(R.string.details_episode_marked_watched, targetEpisode.seasonNumber, targetEpisode.episodeNumber)
                        } else {
                            context.getString(R.string.details_episode_marked_unwatched, targetEpisode.seasonNumber, targetEpisode.episodeNumber)
                        },
                        toastType = ToastType.SUCCESS
                    )
                    runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                }
                runCatching { cloudSyncRepository.pushToCloud() }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = context.getString(R.string.details_failed_update_watched),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun toggleWatchlist() {
        val currentItem = _uiState.value.item ?: return
        val newInWatchlist = !_uiState.value.isInWatchlist

        viewModelScope.launch {
            try {
                val traktConnected = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
                if (newInWatchlist) {
                    if (traktConnected && !traktRepository.addToWatchlist(currentMediaType, currentMediaId)) {
                        throw IllegalStateException(context.getString(R.string.details_failed_trakt_watchlist_add))
                    }
                    // Pass the full MediaItem so it appears instantly in watchlist
                    watchlistRepository.addToWatchlist(currentMediaType, currentMediaId, currentItem)
                } else {
                    if (traktConnected && !traktRepository.removeFromWatchlist(currentMediaType, currentMediaId)) {
                        throw IllegalStateException(context.getString(R.string.details_failed_trakt_watchlist_remove))
                    }
                    watchlistRepository.removeFromWatchlist(currentMediaType, currentMediaId)
                }
                runCatching { cloudSyncRepository.pushToCloud() }

                _uiState.value = _uiState.value.copy(
                    isInWatchlist = newInWatchlist,
                    toastMessage = if (newInWatchlist) context.getString(R.string.added_to_watchlist) else context.getString(R.string.watchlist_toast_removed),
                    toastType = ToastType.SUCCESS
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = context.getString(R.string.details_failed_update_watchlist),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }

    fun showToast(message: String, type: ToastType = ToastType.INFO) {
        _uiState.value = _uiState.value.copy(
            toastMessage = message,
            toastType = type
        )
    }

    private fun isPendingDebridStream(stream: StreamSource): Boolean {
        val text = listOfNotNull(stream.source, stream.addonName, stream.quality, stream.url)
            .joinToString(" ")
            .lowercase()
        return listOf(
            "torrent being downloaded",
            "being downloaded",
            "still downloading",
            "queued",
            "not cached",
            "uncached",
            "cache pending",
            "caching",
            "processing torrent",
            "download in progress"
        ).any { text.contains(it) }
    }

    private fun sortPlayableStreamsFirst(streams: List<StreamSource>): List<StreamSource> {
        return streams.sortedBy { if (isPendingDebridStream(it)) 1 else 0 }
    }

    /**
     * Refresh watched badges and continue target when returning from Player.
     * Uses local caches/history first for near-instant UI updates.
     */
    fun refreshAfterPlayerReturn() {
        val tmdbId = currentMediaId
        if (tmdbId == 0) return
        // Don't run during initial load — would overwrite episodes/seasonProgress with empty data.
        // The isLoading guard alone is insufficient: cached items set isLoading=false immediately,
        // but episodes haven't been populated yet. Wait for the episodes coroutine to finish.
        if (_uiState.value.isLoading || !initialLoadComplete) return
        val mediaType = currentMediaType

        viewModelScope.launch {
            // Force-refresh watched episodes from backend (not just in-memory cache)
            // to pick up episodes marked watched during playback.
            val watchedKeys = if (mediaType == MediaType.TV) {
                runCatching { traktRepository.getWatchedEpisodesForShow(tmdbId) }
                    .getOrDefault(traktRepository.getWatchedEpisodesFromCache())
            } else {
                emptySet()
            }

            // Build all updates in one shot to avoid partial state writes
            val currentState = _uiState.value

            // 1. Update main item watched status
            val updatedItem = currentState.item?.let { currentItem ->
                val watched = if (mediaType == MediaType.MOVIE) {
                    traktRepository.isMovieWatched(tmdbId)
                } else {
                    traktRepository.hasWatchedEpisodes(tmdbId) ||
                        watchedKeys.any { it.startsWith("show_tmdb:$tmdbId:") }
                }
                currentItem.copy(isWatched = watched)
            }

            // 2. Update episode watched badges and season progress
            // Read the LATEST state for episodes — the snapshot captured above may be stale
            // if loadDetails() child coroutines populated episodes after we started.
            val latestForEpisodes = _uiState.value
            var updatedEpisodes = latestForEpisodes.episodes
            var updatedProgress = latestForEpisodes.seasonProgress
            if (mediaType == MediaType.TV && latestForEpisodes.episodes.isNotEmpty()) {
                val prefix = "show_tmdb:$tmdbId:"
                if (watchedKeys.any { it.startsWith(prefix) }) {
                    updatedEpisodes = latestForEpisodes.episodes.map { ep ->
                        val key = "show_tmdb:$tmdbId:${ep.seasonNumber}:${ep.episodeNumber}"
                        ep.copy(isWatched = ep.isWatched || watchedKeys.contains(key))
                    }
                    val season = latestForEpisodes.currentSeason
                    val progress = latestForEpisodes.seasonProgress.toMutableMap()
                    progress[season] = Pair(updatedEpisodes.count { it.isWatched }, updatedEpisodes.size)
                    updatedProgress = progress
                }
            }

            // 3. Re-derive play target: try resume info first, then next unwatched episode
            val quickResume = fetchResumeInfoFromHistoryOnly(tmdbId, mediaType)
            val playTarget = if (quickResume != null) {
                PlayTarget(
                    season = quickResume.season,
                    episode = quickResume.episode,
                    label = quickResume.label,
                    positionMs = quickResume.positionMs
                )
            } else if (mediaType == MediaType.TV) {
                // No resume point (episode was finished) — find the next unwatched episode
                deriveNextUnwatchedPlayTarget(tmdbId, watchedKeys)
            } else {
                null
            }

            // Read latest state to avoid overwriting concurrent updates (e.g. seasonProgress)
            val latestState = _uiState.value
            _uiState.value = latestState.copy(
                item = updatedItem ?: latestState.item,
                // Only overwrite episodes if we actually computed watched badges;
                // otherwise keep the latest (avoids blanking if episodes were populated concurrently)
                episodes = if (updatedEpisodes.isNotEmpty()) updatedEpisodes else latestState.episodes,
                // Only update seasonProgress if we actually computed new data; preserve existing otherwise
                seasonProgress = if (updatedProgress !== latestForEpisodes.seasonProgress) updatedProgress else latestState.seasonProgress,
                playSeason = playTarget?.season ?: latestState.playSeason,
                playEpisode = playTarget?.episode ?: latestState.playEpisode,
                playLabel = playTarget?.label ?: latestState.playLabel,
                playPositionMs = playTarget?.positionMs ?: 0L
            )
        }
    }

    /**
     * Find the next unwatched episode across all seasons to set the play button target.
     */
    private suspend fun deriveNextUnwatchedPlayTarget(tmdbId: Int, watchedKeys: Set<String>): PlayTarget? {
        return try {
            val tvDetails = tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY)
            for (seasonNum in 1..tvDetails.numberOfSeasons) {
                val seasonDetails = runCatching {
                    tmdbApi.getTvSeason(tmdbId, seasonNum, Constants.TMDB_API_KEY)
                }.getOrNull() ?: continue
                val firstUnwatched = seasonDetails.episodes.firstOrNull { episode ->
                    val key = "show_tmdb:$tmdbId:$seasonNum:${episode.episodeNumber}"
                    !watchedKeys.contains(key)
                }
                if (firstUnwatched != null) {
                    return PlayTarget(
                        season = seasonNum,
                        episode = firstUnwatched.episodeNumber,
                        label = context.getString(R.string.continue_season_episode, seasonNum, firstUnwatched.episodeNumber)
                    )
                }
            }
            // All episodes watched — offer restart
            PlayTarget(season = 1, episode = 1, label = context.getString(R.string.play_start_s1e1))
        } catch (_: Exception) {
            null
        }
    }

    // ========== Person Modal ==========

    fun loadPerson(personId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                showPersonModal = true,
                isLoadingPerson = true,
                selectedPerson = null
            )

            try {
                val person = mediaRepository.getPersonDetails(personId)
                _uiState.value = _uiState.value.copy(
                    isLoadingPerson = false,
                    selectedPerson = person
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoadingPerson = false
                )
            }
        }
    }

    fun closePersonModal() {
        _uiState.value = _uiState.value.copy(
            showPersonModal = false,
            selectedPerson = null
        )
    }

    // ========== Stream Resolution ==========

    /**
     * Silently prefetch streams in the background so they are cached by the time
     * the user presses Play or opens Sources. Does not update UI loading state —
     * it only populates StreamRepository's internal cache.
     */
    private var prefetchJob: kotlinx.coroutines.Job? = null
    private fun prefetchStreamsInBackground(imdbId: String, season: Int?, episode: Int?) {
        prefetchJob?.cancel()
        val requestMediaType = currentMediaType
        val requestMediaId = currentMediaId
        prefetchJob = viewModelScope.launch {
            runCatching {
                if (requestMediaType == MediaType.MOVIE) {
                    launch {
                        streamRepository.resolveMovieHomeServerSources(
                            imdbId = imdbId,
                            title = _uiState.value.item?.title.orEmpty(),
                            year = _uiState.value.item?.year?.toIntOrNull(),
                            tmdbId = requestMediaId,
                            timeoutMs = 5_000L
                        )
                    }
                    streamRepository.resolveMovieStreamsProgressive(
                        imdbId = imdbId,
                        title = _uiState.value.item?.title.orEmpty(),
                        year = _uiState.value.item?.year?.toIntOrNull()
                    ).collect { progressive ->
                        prewarmVisibleStreams(
                            sortPlayableStreamsFirst(
                                progressive.streams
                                    .filter { !it.url.isNullOrBlank() && !it.url.orEmpty().startsWith("magnet:", ignoreCase = true) }
                                    .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                            )
                        )
                    }
                } else if (season != null && episode != null) {
                    launch {
                        streamRepository.resolveEpisodeHomeServerSources(
                            imdbId = imdbId,
                            season = season,
                            episode = episode,
                            title = _uiState.value.item?.title.orEmpty(),
                            tmdbId = requestMediaId,
                            tvdbId = _uiState.value.tvdbId,
                            timeoutMs = 5_000L
                        )
                    }
                    val prefetchAirDate = _uiState.value.episodes
                        .firstOrNull { it.seasonNumber == season && it.episodeNumber == episode }
                        ?.airDate?.takeIf { it.isNotBlank() }
                    streamRepository.resolveEpisodeStreamsProgressive(
                        imdbId = imdbId,
                        season = season,
                        episode = episode,
                        tmdbId = currentMediaId,
                        tvdbId = _uiState.value.tvdbId,
                        genreIds = _uiState.value.item?.genreIds ?: emptyList(),
                        originalLanguage = _uiState.value.item?.originalLanguage,
                        title = _uiState.value.item?.title ?: "",
                        airDate = prefetchAirDate
                    ).collect { progressive ->
                        prewarmVisibleStreams(
                            sortPlayableStreamsFirst(
                                progressive.streams
                                    .filter { !it.url.isNullOrBlank() && !it.url.orEmpty().startsWith("magnet:", ignoreCase = true) }
                                    .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                            )
                        )
                    }
                } else {
                    null
                }
            }
        }
    }

    fun prewarmStream(stream: StreamSource) {
        focusedStreamPrewarmJob?.cancel()
        focusedStreamPrewarmJob = viewModelScope.launch {
            runCatching {
                streamRepository.prewarmStreamForPlayback(stream, allowNetworkWarmup = true)
            }
        }
    }

    fun prewarmStreamsAround(stream: StreamSource, streams: List<StreamSource>) {
        if (streams.isEmpty()) return
        focusedStreamPrewarmJob?.cancel()
        focusedStreamPrewarmJob = viewModelScope.launch {
            val index = streams.indexOf(stream).takeIf { it >= 0 } ?: 0
            val candidates = listOf(index, index + 1, index + 2)
                .mapNotNull { streams.getOrNull(it) }
                .distinctBy { "${it.addonId}:${it.source}:${it.url?.substringBefore('|')?.substringBefore('#')}" }
            runCatching {
                streamRepository.prewarmStreamsForPlayback(
                    streams = candidates,
                    limit = candidates.size,
                    allowNetworkWarmup = true
                )
            }
        }
    }

    private fun prewarmVisibleStreams(streams: List<StreamSource>) {
        if (streams.isEmpty()) return
        val topStreams = streams.take(3)
        val prewarmKey = topStreams.joinToString("|") { stream ->
            "${stream.addonId}:${stream.source}:${stream.url?.substringBefore('|')?.substringBefore('#')}"
        }
        if (prewarmKey == lastStreamListPrewarmKey) return
        lastStreamListPrewarmKey = prewarmKey
        streamListPrewarmJob?.cancel()
        streamListPrewarmJob = viewModelScope.launch {
            runCatching {
                streamRepository.prewarmStreamsForPlayback(
                    streams = topStreams,
                    limit = topStreams.size,
                    allowNetworkWarmup = true
                )
            }
        }
    }

    fun loadStreams(imdbId: String?, season: Int? = null, episode: Int? = null) {
        loadStreamsJob?.cancel()
        focusedStreamPrewarmJob?.cancel()
        streamListPrewarmJob?.cancel()
        homeServerAppendJob?.cancel()
        // Reset synchronously so the modal opens in loading state immediately,
        // before the coroutine below gets a chance to run.
        _uiState.value = _uiState.value.copy(
            isLoadingStreams = true,
            completedAddons = 0,
            totalAddons = 0,
            streams = emptyList(),
            subtitles = emptyList(),
            streamSearchStartTime = System.currentTimeMillis(),
            pluginScrapersLoading = false
        )
        val requestId = ++loadStreamsRequestId
        val requestMediaType = currentMediaType
        val requestMediaId = currentMediaId

        loadStreamsJob = viewModelScope.launch {
            fun isCurrentRequest(): Boolean {
                return requestId == loadStreamsRequestId &&
                    currentMediaType == requestMediaType &&
                    currentMediaId == requestMediaId
            }
            if (!isCurrentRequest()) return@launch

            // If the user clicked Play/Sources very fast (e.g. from Search), the background
            // resolveExternalIds might still be running. Try to recover it from cache
            // or wait for the UI state to be updated by the background fetch.
            var currentImdbId = imdbId ?: _uiState.value.imdbId
            if (currentImdbId.isNullOrBlank()) {
                currentImdbId = mediaRepository.getCachedImdbId(requestMediaType, requestMediaId)
            }
            if (currentImdbId.isNullOrBlank()) {
                withTimeoutOrNull(3500) {
                    while (currentImdbId.isNullOrBlank() && isCurrentRequest()) {
                        delay(200)
                        currentImdbId = _uiState.value.imdbId
                    }
                }
            }
            val resolvedImdbId = currentImdbId
            val effectiveStreamId: String? = when {
                !resolvedImdbId.isNullOrBlank() -> resolvedImdbId
                requestMediaId > 0 -> "tmdb:$requestMediaId"
                else -> null
            }

            val orderedAddonIds = streamRepository.installedAddons.first()
                .filter { it.isVodStreamingAddon() }
                .map { it.id }
            _uiState.value = _uiState.value.copy(
                isLoadingStreams = true,
                completedAddons = 0,
                totalAddons = 0,
                streams = emptyList(),
                subtitles = emptyList(),
                addonOrderedIds = orderedAddonIds,
                streamSearchStartTime = System.currentTimeMillis(),
                pluginScrapersLoading = false
            )

            if (requestMediaType == MediaType.MOVIE) {
                val title = _uiState.value.item?.title.orEmpty()
                Log.d(
                    TAG,
                    "[MovieSources] loadStreams start requestId=$requestId mediaId=$requestMediaId imdbId=${resolvedImdbId ?: "null"} title=$title"
                )
            }

            try {
                // Get current item's genre IDs and language for anime detection
                val item = _uiState.value.item
                val genreIds = item?.genreIds ?: emptyList()
                val originalLanguage = item?.originalLanguage
                val hasHomeServerConnections = streamRepository.hasHomeServerConnections()
                // Start VOD append in background - runs parallel to addon stream fetch
                homeServerAppendJob = viewModelScope.launch {
                    appendHomeServerSourcesInBackground(
                        imdbId = resolvedImdbId,
                        season = season,
                        episode = episode,
                        timeoutMs = 5_000L,
                        requestId = requestId,
                        requestMediaType = requestMediaType,
                        requestMediaId = requestMediaId
                    )
                }
                vodAppendJob?.cancel()
                vodAppendJob = viewModelScope.launch {
                    // VOD lookups use disk-cached catalogs (near-instant on warm starts).
                    // On rare true cold starts, catalog download can take 15-30s for large providers.
                    val vodTimeout = if (currentMediaType == MediaType.MOVIE) 30_000L else 45_000L
                    appendVodSourceInBackground(
                        imdbId = resolvedImdbId,
                        season = season,
                        episode = episode,
                        timeoutMs = vodTimeout,
                        requestId = requestId,
                        requestMediaType = requestMediaType,
                        requestMediaId = requestMediaId
                    )
                }

                var pluginScraperJob: kotlinx.coroutines.Job? = null
                pluginScraperJob = viewModelScope.launch {
                    try {
                        _uiState.value = _uiState.value.copy(pluginScrapersLoading = true)
                        val tmdbIdStr = requestMediaId.toString()
                        val pluginMediaType = if (requestMediaType == MediaType.MOVIE) "movie" else "tv"
                        pluginManager.executeScrapersStreaming(
                            tmdbId = tmdbIdStr,
                            mediaType = pluginMediaType,
                            season = if (requestMediaType != MediaType.MOVIE) (season ?: 1) else null,
                            episode = if (requestMediaType != MediaType.MOVIE) (episode ?: 1) else null
                        ).collect { pair ->
                            val scraperInfo = pair.first
                            val results: List<LocalScraperResult>? = pair.second
                            if (!isCurrentRequest()) return@collect

                            val current = _uiState.value
                            if (results == null) {
                                // Plugin started loading
                                _uiState.value = current.copy(
                                    loadingPluginNames = current.loadingPluginNames + scraperInfo.name
                                )
                            } else {
                                // Plugin finished loading (with or without results)
                                val updatedNames = current.loadingPluginNames - scraperInfo.name
                                if (results.isNotEmpty()) {
                                    val pluginStreams = results.map { it.toStreamSource() }
                                    val merged = sortPlayableStreamsFirst(
                                        (current.streams + pluginStreams)
                                            .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                                    )
                                    _uiState.value = current.copy(
                                        streams = merged,
                                        isLoadingStreams = false,
                                        loadingPluginNames = updatedNames
                                    )
                                } else {
                                    _uiState.value = current.copy(
                                        loadingPluginNames = updatedNames
                                    )
                                }
                            }
                        }
                    } catch (e: Exception) {
                        Log.w(TAG, "[PluginScrapers] streaming execution failed: ${e.message}")
                    } finally {
                        val current = _uiState.value
                        val stillLoading = loadStreamsJob?.isActive == true ||
                                           vodAppendJob?.isActive == true ||
                                           homeServerAppendJob?.isActive == true
                        val newLoading = current.isLoadingStreams && current.streams.isEmpty() && stillLoading

                        _uiState.value = current.copy(
                            pluginScrapersLoading = false,
                            loadingPluginNames = emptySet(),
                            isLoadingStreams = newLoading
                        )
                    }
                }

                val result = if (currentMediaType == MediaType.MOVIE) {
                    val enabledAddons = streamRepository.installedAddons.first()
                        .filter { it.isVodStreamingAddon() }
                    val enabledStreamingAddons = enabledAddons.size
                    val stremioCount = enabledAddons.count { it.runtimeKind == com.arflix.tv.data.model.RuntimeKind.STREMIO }
                    val enabledAddonNames = enabledAddons.take(4).joinToString(",") { it.name }
                    Log.d(
                        TAG,
                        "[MovieSources] enabledStreamingAddons=$enabledStreamingAddons requestId=$requestId mediaId=$requestMediaId"
                    )
                    Log.d(
                        TAG,
                        "[MovieSources] addonBreakdown stremio=$stremioCount names=$enabledAddonNames"
                    )
                    if (enabledStreamingAddons == 0) {
                        Log.w(
                            TAG,
                            "[MovieSources] no streaming addons enabled. Install/enable sources in Settings > Addons."
                        )
                    }

                    if (effectiveStreamId.isNullOrBlank()) {
                        Log.w(
                            TAG,
                            "[MovieSources] loadStreams skipped (missing imdbId) requestId=$requestId mediaId=$requestMediaId"
                        )
                        _uiState.value = _uiState.value.copy(
                            isLoadingStreams = false,
                            streams = emptyList(),
                            subtitles = emptyList(),
                            hasStreamingAddons = streamRepository.installedAddons.first()
                                .count { it.isVodStreamingAddon() } > 0 ||
                                hasHomeServerConnections
                        )
                        return@launch
                    }
                    streamRepository.resolveMovieStreamsProgressive(
                        imdbId = effectiveStreamId,
                        title = item?.title.orEmpty(),
                        year = item?.year?.toIntOrNull()
                    ).collect { progressive ->
                        if (!isCurrentRequest()) return@collect
                        val existingVod = _uiState.value.streams.filter(::isSupplementalStream)
                        val mergedStreams = sortPlayableStreamsFirst(
                            (progressive.streams + existingVod)
                                .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                        )
                        Log.d(
                            TAG,
                            "[MovieSources] progressive requestId=$requestId final=${progressive.isFinal} " +
                                "incoming=${progressive.streams.size} merged=${mergedStreams.size} subtitles=${progressive.subtitles.size}"
                        )
                        val addonCount = streamRepository.installedAddons.first()
                            .count { it.isVodStreamingAddon() }
                        val supplementalSourcesStillLoading =
                            homeServerAppendJob?.isActive == true || vodAppendJob?.isActive == true
                        _uiState.value = _uiState.value.copy(
                            isLoadingStreams = mergedStreams.isEmpty() &&
                                (!progressive.isFinal || hasHomeServerConnections || supplementalSourcesStillLoading || pluginScraperJob?.isActive == true),
                            completedAddons = progressive.completedAddons,
                            totalAddons = progressive.totalAddons,
                            streams = mergedStreams,
                            subtitles = progressive.subtitles,
                            hasStreamingAddons = addonCount > 0 || hasHomeServerConnections
                        )
                        prewarmVisibleStreams(mergedStreams)
                        if (progressive.isFinal) {
                            Log.d(
                                TAG,
                                "[MovieSources] loadStreams completed requestId=$requestId mediaId=$requestMediaId totalStreams=${mergedStreams.size}"
                            )
                        }
                    }
                    return@launch
                } else {
                    if (effectiveStreamId.isNullOrBlank()) {
                        _uiState.value = _uiState.value.copy(
                            isLoadingStreams = false,
                            streams = emptyList(),
                            subtitles = emptyList(),
                            hasStreamingAddons = streamRepository.installedAddons.first()
                                .count { it.isVodStreamingAddon() } > 0 ||
                                hasHomeServerConnections
                        )
                        return@launch
                    }
                    // Look up air date for daily show stream resolution fallback
                    val episodeAirDate = _uiState.value.episodes
                        .firstOrNull { it.seasonNumber == (season ?: 1) && it.episodeNumber == (episode ?: 1) }
                        ?.airDate?.takeIf { it.isNotBlank() }

                    streamRepository.resolveEpisodeStreamsProgressive(
                        imdbId = effectiveStreamId,
                        season = season ?: 1,
                        episode = episode ?: 1,
                        tmdbId = currentMediaId,
                        tvdbId = _uiState.value.tvdbId,
                        genreIds = genreIds,
                        originalLanguage = originalLanguage,
                        title = item?.title ?: "",
                        airDate = episodeAirDate
                    ).collect { progressive ->
                        if (!isCurrentRequest()) return@collect
                        val existingVod = _uiState.value.streams.filter(::isSupplementalStream)
                        val mergedStreams = sortPlayableStreamsFirst(
                            (progressive.streams + existingVod)
                                .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                        )
                        val addonCount = streamRepository.installedAddons.first()
                            .count { it.isVodStreamingAddon() }
                        val supplementalSourcesStillLoading =
                            homeServerAppendJob?.isActive == true || vodAppendJob?.isActive == true
                        _uiState.value = _uiState.value.copy(
                            isLoadingStreams = mergedStreams.isEmpty() &&
                                (!progressive.isFinal || hasHomeServerConnections || supplementalSourcesStillLoading || pluginScraperJob?.isActive == true),
                            completedAddons = progressive.completedAddons,
                            totalAddons = progressive.totalAddons,
                            streams = mergedStreams,
                            subtitles = progressive.subtitles,
                            hasStreamingAddons = addonCount > 0 || hasHomeServerConnections
                        )
                        prewarmVisibleStreams(mergedStreams)
                    }
                    return@launch
                }
            } catch (e: Exception) {
                if (!isCurrentRequest()) return@launch
                if (requestMediaType == MediaType.MOVIE) {
                    Log.e(
                        TAG,
                        "[MovieSources] loadStreams failed requestId=$requestId mediaId=$requestMediaId message=${e.message}",
                        e
                    )
                }
                _uiState.value = _uiState.value.copy(isLoadingStreams = false)
            }
        }
    }

    fun markEpisodeWatched(season: Int, episode: Int, watched: Boolean) {
        viewModelScope.launch {
            try {
                if (watched) {
                    traktRepository.markEpisodeWatched(currentMediaId, season, episode)
                    // Also remove from Supabase watch_history (removes from Continue Watching)
                    watchHistoryRepository.removeFromHistory(currentMediaId, season, episode)

                    // Save the NEXT episode to CW (local + cloud) so it appears on all devices
                    val item = _uiState.value.item
                    if (item != null) {
                        try {
                            val nextEp = episode + 1
                            traktRepository.saveLocalContinueWatching(
                                mediaType = MediaType.TV,
                                tmdbId = currentMediaId,
                                title = item.title,
                                posterPath = item.image,
                                backdropPath = item.backdrop,
                                season = season,
                                episode = nextEp,
                                episodeTitle = null,
                                progress = 3,
                                positionSeconds = 0L,
                                durationSeconds = 1L,
                                year = item.year
                            )
                            watchHistoryRepository.saveProgress(
                                mediaType = MediaType.TV,
                                tmdbId = currentMediaId,
                                title = item.title,
                                poster = item.image,
                                backdrop = item.backdrop,
                                season = season,
                                episode = nextEp,
                                episodeTitle = null,
                                progress = 0.01f,
                                duration = 0L,
                                position = 0L
                            )
                        } catch (_: Exception) {}
                    }
                } else {
                    traktRepository.markEpisodeUnwatched(currentMediaId, season, episode)
                }

                // Update local state
                val updatedEpisodes = _uiState.value.episodes.map { ep ->
                    if (ep.seasonNumber == season && ep.episodeNumber == episode) {
                        ep.copy(isWatched = watched)
                    } else ep
                }
                _uiState.value = _uiState.value.copy(episodes = updatedEpisodes)
                runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                // Push cloud snapshot so other devices see the episode watched-status
                // change and the updated Continue Watching entry.
                runCatching { cloudSyncRepository.pushToCloud() }
            } catch (e: Exception) {
                // Failed silently
            }
        }
    }

    fun markSeasonWatched(season: Int) {
        if (currentMediaType != MediaType.TV) return
        val currentItem = _uiState.value.item ?: return

        viewModelScope.launch {
            try {
                val seasonEpisodes = if (_uiState.value.currentSeason == season && _uiState.value.episodes.isNotEmpty()) {
                    _uiState.value.episodes
                } else {
                    mediaRepository.getSeasonEpisodes(currentMediaId, season)
                }

                if (seasonEpisodes.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = context.getString(R.string.details_no_episodes_season, season),
                        toastType = ToastType.ERROR
                    )
                    return@launch
                }

                // OPTIMISTIC UPDATE: Update local state immediately so the UI responds instantly
                val updatedEpisodes = if (_uiState.value.currentSeason == season) {
                    _uiState.value.episodes.map { ep ->
                        if (ep.seasonNumber == season) ep.copy(isWatched = true) else ep
                    }
                } else {
                    _uiState.value.episodes
                }
                val optimisticProgress = _uiState.value.seasonProgress.toMutableMap().apply {
                    this[season] = Pair(seasonEpisodes.size, seasonEpisodes.size)
                }
                _uiState.value = _uiState.value.copy(
                    episodes = updatedEpisodes,
                    seasonProgress = optimisticProgress
                )

                // BATCH: Use single Trakt API call for all episodes, then concurrent Supabase writes.
                // Previously this looped sequentially calling markEpisodeWatched() per episode,
                // each making its own Supabase + Trakt network call — taking ~5-12s for a full season.
                val episodeNumbers = seasonEpisodes.map { it.episodeNumber }

                // 1. Single batch Trakt API call (all episodes in one request)
                runCatching {
                    traktRepository.markSeasonWatched(currentMediaId, season, episodeNumbers)
                }

                // 2. Remove from watch history FIRST (synchronous), before Supabase writes.
                //    This avoids a race condition where removeFromHistory deletes the
                //    just-written watched records, causing watched status to be lost on re-entry.
                runCatching {
                    watchHistoryRepository.removeFromHistory(currentMediaId, season, null)
                }

                // 3. Concurrent Supabase writes for each episode (faster than sequential).
                //    These run AFTER removeFromHistory to ensure the watched records are the final state.
                episodeNumbers.map { epNum ->
                    async {
                        runCatching {
                            traktRepository.markEpisodeWatchedWithoutTraktSync(currentMediaId, season, epNum)
                        }
                    }
                }.forEach { it.await() }

                val refreshedProgress = runCatching { fetchSeasonProgress(currentMediaId) }.getOrNull()
                val nextUnwatched = refreshedProgress?.nextUnwatched

                if (nextUnwatched != null) {
                    val (nextSeason, nextEpisode) = nextUnwatched
                    runCatching {
                        traktRepository.saveLocalContinueWatching(
                            mediaType = MediaType.TV,
                            tmdbId = currentMediaId,
                            title = currentItem.title,
                            posterPath = currentItem.image,
                            backdropPath = currentItem.backdrop,
                            season = nextSeason,
                            episode = nextEpisode,
                            episodeTitle = null,
                            progress = 3,
                            positionSeconds = 0L, // next episode: no resume position yet
                            durationSeconds = 0L, // next episode: unknown duration
                            year = currentItem.year
                        )
                        watchHistoryRepository.saveProgress(
                            mediaType = MediaType.TV,
                            tmdbId = currentMediaId,
                            title = currentItem.title,
                            poster = currentItem.image,
                            backdrop = currentItem.backdrop,
                            season = nextSeason,
                            episode = nextEpisode,
                            episodeTitle = null,
                            progress = 0.01f,
                            duration = 0L,
                            position = 0L
                        )
                    }
                } else {
                    runCatching {
                        traktRepository.removeFromContinueWatchingCache(currentMediaId, null, null)
                        watchHistoryRepository.removeFromHistory(currentMediaId, null, null)
                    }
                }

                val playTarget = buildPlayTarget(currentMediaType, refreshedProgress, null)

                _uiState.value = _uiState.value.copy(
                    item = currentItem.copy(isWatched = nextUnwatched == null),
                    episodes = updatedEpisodes,
                    seasonProgress = refreshedProgress?.progress ?: optimisticProgress,
                    playSeason = playTarget?.season ?: _uiState.value.playSeason,
                    playEpisode = playTarget?.episode ?: _uiState.value.playEpisode,
                    playLabel = playTarget?.label ?: _uiState.value.playLabel,
                    playPositionMs = playTarget?.positionMs ?: _uiState.value.playPositionMs,
                    toastMessage = context.getString(R.string.details_season_marked_watched, season),
                    toastType = ToastType.SUCCESS
                )
                runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                // Push cloud snapshot so other devices see the entire season marked watched
                // and the updated Continue Watching entry pointing to the next unwatched episode.
                runCatching { cloudSyncRepository.pushToCloud() }
            } catch (_: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = context.getString(R.string.details_failed_mark_season_watched),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun markSeasonUnwatched(season: Int) {
        if (currentMediaType != MediaType.TV) return
        val currentItem = _uiState.value.item ?: return

        viewModelScope.launch {
            try {
                val seasonEpisodes = if (_uiState.value.currentSeason == season && _uiState.value.episodes.isNotEmpty()) {
                    _uiState.value.episodes
                } else {
                    mediaRepository.getSeasonEpisodes(currentMediaId, season)
                }

                if (seasonEpisodes.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        toastMessage = context.getString(R.string.details_no_episodes_season, season),
                        toastType = ToastType.ERROR
                    )
                    return@launch
                }

                // OPTIMISTIC UPDATE: Update local state immediately
                val updatedEpisodes = if (_uiState.value.currentSeason == season) {
                    _uiState.value.episodes.map { ep ->
                        if (ep.seasonNumber == season) ep.copy(isWatched = false) else ep
                    }
                } else {
                    _uiState.value.episodes
                }
                val optimisticProgress = _uiState.value.seasonProgress.toMutableMap().apply {
                    this[season] = Pair(0, seasonEpisodes.size)
                }
                _uiState.value = _uiState.value.copy(
                    episodes = updatedEpisodes,
                    seasonProgress = optimisticProgress
                )

                // BATCH: Single Trakt API call to remove all episodes, then concurrent Supabase writes
                val episodeNumbers = seasonEpisodes.map { it.episodeNumber }

                // 1. Single batch Trakt API call to remove from history
                val batchTraktRemoved = runCatching {
                    traktRepository.removeSeasonFromHistory(currentMediaId, season, episodeNumbers)
                }.getOrDefault(false)

                // 2. Concurrent local/Supabase unwatch writes for each episode.
                // If the batch Trakt removal failed, fall back to per-episode Trakt sync.
                val episodeUnwatchResults = episodeNumbers.map { epNum ->
                    async {
                        runCatching {
                            traktRepository.markEpisodeUnwatched(
                                currentMediaId,
                                season,
                                epNum,
                                syncTrakt = !batchTraktRemoved
                            )
                        }
                    }
                }.map { it.await() }

                if (!batchTraktRemoved && episodeUnwatchResults.any { it.isFailure }) {
                    _uiState.value = _uiState.value.copy(
                        episodes = updatedEpisodes,
                        seasonProgress = optimisticProgress,
                        toastMessage = context.getString(R.string.details_failed_sync_season_unwatched, season),
                        toastType = ToastType.ERROR
                    )
                    return@launch
                }

                val refreshedProgress = runCatching { fetchSeasonProgress(currentMediaId) }.getOrNull()

                _uiState.value = _uiState.value.copy(
                    item = currentItem.copy(isWatched = false),
                    episodes = updatedEpisodes,
                    seasonProgress = refreshedProgress?.progress ?: optimisticProgress,
                    toastMessage = context.getString(R.string.details_season_marked_unwatched, season),
                    toastType = ToastType.SUCCESS
                )
                runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                runCatching { cloudSyncRepository.pushToCloud() }
            } catch (_: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = context.getString(R.string.details_failed_mark_season_unwatched),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    /**
     * Resolve real IMDB ID from TMDB using external_ids endpoint
     * This is required for addon stream resolution
     */
    /**
     * Fetch season progress for a TV show
     * Returns Map<seasonNumber, Pair<watchedCount, totalCount>>
     * Uses Trakt's show progress API which has accurate per-season data
     */
    private suspend fun fetchSeasonProgress(tmdbId: Int): SeasonProgressResult {
        return try {
            val cachedEpisodes = runCatching { traktRepository.getWatchedEpisodesFromCache() }.getOrDefault(emptySet())
            val cachedCountsBySeason = mutableMapOf<Int, Int>()
            val cachedKeysForShow = cachedEpisodes.filter { it.startsWith("show_tmdb:$tmdbId:") }.toSet()
            for (key in cachedKeysForShow) {
                val parts = key.split(":")
                val seasonNum = parts.getOrNull(2)?.toIntOrNull() ?: continue
                cachedCountsBySeason[seasonNum] = (cachedCountsBySeason[seasonNum] ?: 0) + 1
            }

            val watchedKeys = if (cachedKeysForShow.isNotEmpty()) {
                cachedKeysForShow
            } else {
                runCatching { traktRepository.getWatchedEpisodesForShow(tmdbId) }.getOrDefault(emptySet())
            }

            val tvDetails = tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY)
            val seasonNumbers = tvDetails.seasons
                .asSequence()
                .filter { it.seasonNumber > 0 && it.episodeCount > 0 }
                .map { it.seasonNumber }
                .distinct()
                .sorted()
                .toList()
                .ifEmpty { (1..tvDetails.numberOfSeasons.coerceAtLeast(1)).toList() }

            val progressMap = mutableMapOf<Int, Pair<Int, Int>>()
            var nextUnwatched: Pair<Int, Int>? = null

            for (seasonNum in seasonNumbers) {
                try {
                    val seasonDetails = tmdbApi.getTvSeason(tmdbId, seasonNum, Constants.TMDB_API_KEY)
                    val totalEpisodes = seasonDetails.episodes.size

                    val watchedCount = if (cachedCountsBySeason.isNotEmpty()) {
                        cachedCountsBySeason[seasonNum] ?: 0
                    } else {
                        watchedKeys.count { key ->
                            key.startsWith("show_tmdb:$tmdbId:$seasonNum:")
                        }
                    }
                    progressMap[seasonNum] = Pair(watchedCount, totalEpisodes)

                    if (nextUnwatched == null) {
                        val firstUnwatched = seasonDetails.episodes.firstOrNull { episode ->
                            val key = "show_tmdb:$tmdbId:$seasonNum:${episode.episodeNumber}"
                            !watchedKeys.contains(key)
                        }
                        if (firstUnwatched != null) {
                            nextUnwatched = Pair(seasonNum, firstUnwatched.episodeNumber)
                        }
                    }
                } catch (e: Exception) {
                    // Skip seasons we can't load
                }
            }

            SeasonProgressResult(
                progress = progressMap,
                hasWatched = watchedKeys.isNotEmpty() || progressMap.values.any { it.first > 0 },
                nextUnwatched = nextUnwatched
            )
        } catch (e: Exception) {
            SeasonProgressResult(emptyMap(), false, null)
        }
    }

    private suspend fun fetchResumeInfo(
        tmdbId: Int,
        mediaType: MediaType,
        preferredSeason: Int? = null,
        preferredEpisode: Int? = null
    ): ResumeInfo? {
        return try {
            val exactEntry = if (mediaType == MediaType.TV && preferredSeason != null && preferredEpisode != null) {
                watchHistoryRepository.getProgress(mediaType, tmdbId, preferredSeason, preferredEpisode)
            } else {
                null
            }
            val watchedEpisodeKeys = if (mediaType == MediaType.TV) {
                val prefix = "show_tmdb:$tmdbId:"
                val cached = runCatching {
                    traktRepository.getWatchedEpisodesFromCache()
                        .filter { it.startsWith(prefix) }
                        .toSet()
                }.getOrDefault(emptySet())
                if (cached.isNotEmpty()) {
                    cached
                } else {
                    runCatching { traktRepository.getWatchedEpisodesForShow(tmdbId) }.getOrDefault(emptySet())
                }
            } else {
                emptySet()
            }
            fun ResumeInfo?.dropIfWatchedEpisode(): ResumeInfo? {
                val info = this ?: return null
                if (mediaType != MediaType.TV) return info
                val s = info.season ?: return null
                val e = info.episode ?: return null
                val key = "show_tmdb:$tmdbId:$s:$e"
                return if (watchedEpisodeKeys.contains(key)) null else info
            }

            val entry = exactEntry ?: watchHistoryRepository.getLatestProgress(mediaType, tmdbId)
            val cloudResume = if (entry != null) {
                buildResumeFromProgress(
                    mediaType = mediaType,
                    tmdbId = tmdbId,
                    season = entry.season,
                    episode = entry.episode,
                    progress = entry.progress,
                    positionSeconds = entry.position_seconds,
                    durationSeconds = entry.duration_seconds
                ).dropIfWatchedEpisode()
            } else null

            val hasTrakt = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
            val localItem = runCatching {
                traktRepository.getLocalContinueWatchingEntry(
                    mediaType = mediaType,
                    tmdbId = tmdbId,
                    season = preferredSeason ?: entry?.season,
                    episode = preferredEpisode ?: entry?.episode
                )
            }.getOrNull()
            val localFallbackItem = if (localItem == null && preferredSeason == null && preferredEpisode == null) {
                runCatching {
                    traktRepository.getBestLocalContinueWatchingEntry(
                        mediaType = mediaType,
                        tmdbId = tmdbId
                    )
                }.getOrNull()
            } else {
                null
            }

            val cachedTraktItem = if (hasTrakt) {
                runCatching {
                    traktRepository.getCachedContinueWatching()
                        .firstOrNull {
                            it.id == tmdbId &&
                                it.mediaType == mediaType &&
                                it.progress > 0 &&
                                (preferredSeason == null || preferredEpisode == null || mediaType != MediaType.TV ||
                                    (it.season == preferredSeason && it.episode == preferredEpisode))
                        }
                }.getOrNull()
            } else {
                null
            }
            val fetchedTraktItem = if (hasTrakt && cachedTraktItem == null) {
                withTimeoutOrNull(4_000L) {
                    runCatching {
                        traktRepository.getContinueWatching()
                            .firstOrNull {
                                it.id == tmdbId &&
                                    it.mediaType == mediaType &&
                                    it.progress > 0 &&
                                    (preferredSeason == null || preferredEpisode == null || mediaType != MediaType.TV ||
                                        (it.season == preferredSeason && it.episode == preferredEpisode))
                            }
                    }.getOrNull()
                }
            } else {
                null
            }

            val resumeCandidate = fetchedTraktItem ?: cachedTraktItem ?: localItem ?: localFallbackItem
            val localResume = if (resumeCandidate != null) {
                buildResumeFromProgress(
                    mediaType = mediaType,
                    tmdbId = tmdbId,
                    season = resumeCandidate.season,
                    episode = resumeCandidate.episode,
                    progress = resumeCandidate.progress / 100f,
                    positionSeconds = resumeCandidate.resumePositionSeconds,
                    durationSeconds = resumeCandidate.durationSeconds,
                    allowProgressDerivedResume = !resumeCandidate.isUpNext
                ).dropIfWatchedEpisode()
            } else null

            when {
                preferredSeason != null && preferredEpisode != null -> {
                    // Only use resume data that matches the requested episode.
                    // Never let a stale entry from a different episode bleed through.
                    val matchLocal = localResume?.takeIf { it.season == preferredSeason && it.episode == preferredEpisode }
                    val matchCloud = cloudResume?.takeIf { it.season == preferredSeason && it.episode == preferredEpisode }
                    matchLocal ?: matchCloud
                }
                cloudResume == null -> localResume
                localResume == null -> cloudResume
                // When both exist, prefer the one for the later episode; if same episode, prefer higher position
                localResume.season != null && cloudResume.season != null && localResume.episode != null && cloudResume.episode != null -> {
                    val localEp = localResume.season!! * 10000 + localResume.episode!!
                    val cloudEp = cloudResume.season!! * 10000 + cloudResume.episode!!
                    if (localEp > cloudEp) localResume
                    else if (cloudEp > localEp) cloudResume
                    else if (localResume.positionMs > cloudResume.positionMs) localResume
                    else cloudResume
                }
                localResume.positionMs > cloudResume.positionMs -> localResume
                else -> cloudResume
            }
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchResumeInfoFromHistoryOnly(tmdbId: Int, mediaType: MediaType): ResumeInfo? {
        return try {
            val entry = watchHistoryRepository.getLatestProgress(mediaType, tmdbId) ?: return null
            if (mediaType == MediaType.TV && entry.season != null && entry.episode != null) {
                val watchedKey = "show_tmdb:$tmdbId:${entry.season}:${entry.episode}"
                val isWatched = runCatching {
                    traktRepository.getWatchedEpisodesFromCache().contains(watchedKey) ||
                        traktRepository.getWatchedEpisodesForShow(tmdbId).contains(watchedKey)
                }.getOrDefault(false)
                if (isWatched) return null
            }
            buildResumeFromProgress(
                mediaType = mediaType,
                tmdbId = tmdbId,
                season = entry.season,
                episode = entry.episode,
                progress = entry.progress,
                positionSeconds = entry.position_seconds,
                durationSeconds = entry.duration_seconds
            )
        } catch (_: Exception) {
            null
        }
    }

    private suspend fun buildResumeFromProgress(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?,
        progress: Float,
        positionSeconds: Long,
        durationSeconds: Long,
        allowProgressDerivedResume: Boolean = true
    ): ResumeInfo? {
        val normalizedDuration = if (durationSeconds > 86_400L) durationSeconds / 1000L else durationSeconds
        val normalizedPosition = if (positionSeconds > 86_400L) positionSeconds / 1000L else positionSeconds
        val normalizedProgress = progress.coerceIn(0f, 1f)
        val minPlaybackProgress = Constants.MIN_PROGRESS_THRESHOLD / 100f

        // If both position and duration are 0, this entry has no real playback data.
        // The progress field may be a show-level synthetic value (% of episodes watched),
        // NOT an episode playback percentage. Don't derive a fake resume position from it.
        if (normalizedPosition <= 0L && normalizedDuration <= 0L) {
            return null
        }

        // TV "up next" placeholders intentionally point at the next episode with
        // little/no progress. Older builds could accidentally attach the previous
        // episode's position to that placeholder, causing labels like
        // "Continue S4E4 at 23:16" for a never-started episode.
        if (
            mediaType == MediaType.TV &&
            season != null &&
            episode != null &&
            normalizedPosition > 0L &&
            (
                (normalizedProgress <= minPlaybackProgress && normalizedDuration < 60L) ||
                    (normalizedDuration in 1L..59L && normalizedPosition >= 60L)
            )
        ) {
            return null
        }

        var seconds = when {
            normalizedPosition > 0 -> normalizedPosition
            allowProgressDerivedResume && normalizedDuration > 0 && normalizedProgress > 0f ->
                (normalizedDuration * normalizedProgress).toLong()
            else -> 0L
        }
        if (normalizedDuration > 0L && seconds > 0L) {
            seconds = seconds.coerceIn(1L, normalizedDuration.coerceAtLeast(1L))
        }
        if (seconds <= 0L) return null
        val timeLabel = formatResumeTime(seconds)
        if (timeLabel.isBlank()) return null

        return if (mediaType == MediaType.MOVIE) {
            ResumeInfo(
                label = context.getString(R.string.continue_at, timeLabel),
                positionMs = seconds * 1000L
            )
        } else {
            val s = season ?: return null
            val e = episode ?: return null
            ResumeInfo(
                season = s,
                episode = e,
                label = context.getString(R.string.continue_season_episode_at, s, e, timeLabel),
                positionMs = seconds * 1000L
            )
        }
    }

    private suspend fun resolveRuntimeSeconds(
        tmdbId: Int,
        mediaType: MediaType,
        season: Int?,
        episode: Int?
    ): Long {
        return try {
            if (mediaType == MediaType.MOVIE) {
                val details = tmdbApi.getMovieDetails(tmdbId, Constants.TMDB_API_KEY)
                (details.runtime ?: 0) * 60L
            } else {
                val details = tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY)
                val avgRuntime = details.episodeRunTime.firstOrNull() ?: 0
                if (avgRuntime > 0) {
                    avgRuntime * 60L
                } else {
                    val s = season ?: return 0L
                    val e = episode ?: return 0L
                    val seasonDetails = tmdbApi.getTvSeason(tmdbId, s, Constants.TMDB_API_KEY)
                    val episodeRuntime = seasonDetails.episodes.firstOrNull { it.episodeNumber == e }?.runtime
                        ?: seasonDetails.episodes.firstOrNull { it.runtime != null }?.runtime
                        ?: 0
                    episodeRuntime * 60L
                }
            }
        } catch (_: Exception) {
            0L
        }
    }

    private fun formatResumeTime(seconds: Long): String {
        val total = seconds.coerceAtLeast(0)
        val hours = total / 3600
        val minutes = (total % 3600) / 60
        val secs = total % 60
        return if (hours > 0) {
            "%d:%02d:%02d".format(hours, minutes, secs)
        } else {
            "%d:%02d".format(minutes, secs)
        }
    }

    private fun buildPlayTarget(
        mediaType: MediaType,
        result: SeasonProgressResult?,
        resumeInfo: ResumeInfo?
    ): PlayTarget? {
        if (resumeInfo != null) {
            return PlayTarget(
                season = resumeInfo.season,
                episode = resumeInfo.episode,
                label = resumeInfo.label,
                positionMs = resumeInfo.positionMs
            )
        }
        if (mediaType == MediaType.MOVIE) return null
        if (result == null) return null
        return if (!result.hasWatched) {
            PlayTarget(
                season = 1,
                episode = 1,
                label = context.getString(R.string.play_start_s1e1)
            )
        } else {
            val next = result.nextUnwatched
            if (next != null) {
                PlayTarget(
                    season = next.first,
                    episode = next.second,
                    label = context.getString(R.string.continue_season_episode, next.first, next.second)
                )
            } else {
                PlayTarget(
                    season = 1,
                    episode = 1,
                    label = context.getString(R.string.play_start_s1e1)
                )
            }
        }
    }

    private data class ExternalIds(val imdbId: String?, val tvdbId: Int?)

    private suspend fun resolveExternalIds(mediaType: MediaType, mediaId: Int): ExternalIds {
        return try {
            val ids = when (mediaType) {
                MediaType.MOVIE -> tmdbApi.getMovieExternalIds(mediaId, Constants.TMDB_API_KEY)
                MediaType.TV -> tmdbApi.getTvExternalIds(mediaId, Constants.TMDB_API_KEY)
            }
            ExternalIds(imdbId = ids.imdbId, tvdbId = ids.tvdbId)
        } catch (_: Exception) {
            ExternalIds(null, null)
        }
    }

    private suspend fun loadCommunityReviews(
        mediaType: MediaType,
        mediaId: Int,
        imdbId: String?
    ): List<Review> {
        val traktId = imdbId?.takeIf { it.startsWith("tt", ignoreCase = true) } ?: mediaId.toString()
        val comments = loadCommunityComments(mediaType, traktId)

        val formalReviews = comments.toFilteredCommunityReviews(requireReview = true)
        val reviews = if (formalReviews.isNotEmpty()) {
            formalReviews
        } else {
            comments.toFilteredCommunityReviews(requireReview = false)
        }

        if (reviews.size >= MIN_COMMUNITY_REVIEW_COUNT) return reviews

        return loadFilteredTmdbReviews(mediaType, mediaId)
    }

    private suspend fun loadCommunityComments(mediaType: MediaType, traktId: String): List<TraktComment> {
        val liked = if (mediaType == MediaType.TV) {
            traktRepository.getShowComments(traktId, page = 1, limit = 30, sort = "likes")
        } else {
            traktRepository.getMovieComments(traktId, page = 1, limit = 30, sort = "likes")
        }
        val newest = if (liked.size < 8) {
            if (mediaType == MediaType.TV) {
                traktRepository.getShowComments(traktId, page = 1, limit = 30, sort = "newest")
            } else {
                traktRepository.getMovieComments(traktId, page = 1, limit = 30, sort = "newest")
            }
        } else {
            emptyList()
        }
        return (liked + newest).distinctBy { it.id }
    }

    private fun List<TraktComment>.toFilteredCommunityReviews(requireReview: Boolean): List<Review> {
        return asSequence()
            .filter { it.parentId == null && !it.spoiler }
            .filter { !requireReview || it.review }
            .filterNot { isSpammyReviewText(it.comment) }
            .sortedWith(
                compareByDescending<TraktComment> { it.review }
                    .thenByDescending { it.likes }
                    .thenByDescending { it.userStats?.rating ?: 0 }
                    .thenByDescending { it.createdAt }
            )
            .mapNotNull { it.toCommunityReview() }
            .distinctBy { review ->
                "${review.authorUsername}:${review.content.lowercase(Locale.US).take(140)}"
            }
            .take(8)
            .toList()
    }

    private suspend fun loadFilteredTmdbReviews(mediaType: MediaType, mediaId: Int): List<Review> {
        return mediaRepository.getReviews(mediaType, mediaId)
            .asSequence()
            .filterNot { isSpammyReviewText(it.content) }
            .mapNotNull { review ->
                val cleanedContent = cleanCommunityReviewText(review.content)
                if (cleanedContent.length !in MIN_COMMUNITY_REVIEW_CHARS..MAX_COMMUNITY_REVIEW_CHARS) {
                    return@mapNotNull null
                }
                val wordCount = cleanedContent.split(DetailsVMRegexes.reviewWhitespaceRegex).count { it.length > 1 }
                if (wordCount < MIN_COMMUNITY_REVIEW_WORDS) return@mapNotNull null
                review.copy(content = cleanedContent)
            }
            .distinctBy { review ->
                "${review.authorUsername.ifBlank { review.author }}:${review.content.lowercase(Locale.US).take(140)}"
            }
            .sortedWith(
                compareByDescending<Review> { it.rating ?: 0f }
                    .thenByDescending { it.createdAt }
            )
            .take(8)
            .toList()
    }

    private fun TraktComment.toCommunityReview(): Review? {
        val cleanedContent = cleanCommunityReviewText(comment)
        if (cleanedContent.length !in MIN_COMMUNITY_REVIEW_CHARS..MAX_COMMUNITY_REVIEW_CHARS) {
            return null
        }
        val wordCount = cleanedContent.split(DetailsVMRegexes.reviewWhitespaceRegex).count { it.length > 1 }
        if (wordCount < MIN_COMMUNITY_REVIEW_WORDS) return null

        val username = user?.username?.trim().orEmpty()
        val displayName = user?.name
            ?.trim()
            ?.takeIf { it.isNotBlank() }
            ?: username.takeIf { it.isNotBlank() }
            ?: "Trakt user"

        return Review(
            id = "trakt_$id",
            author = displayName,
            authorUsername = username,
            authorAvatar = null,
            content = cleanedContent,
            rating = userStats?.rating?.takeIf { it > 0 }?.toFloat(),
            createdAt = createdAt
        )
    }

    private fun cleanCommunityReviewText(raw: String): String {
        return raw
            .replace(DetailsVMRegexes.reviewMarkdownLinkRegex, "\$1")
            .replace(DetailsVMRegexes.reviewHtmlTagRegex, " ")
            .replace(DetailsVMRegexes.reviewMarkdownNoiseRegex, " ")
            .replace(DetailsVMRegexes.reviewWhitespaceRegex, " ")
            .trim()
    }

    private fun isSpammyReviewText(raw: String): Boolean {
        val text = raw.trim()
        if (text.isBlank()) return true
        if (DetailsVMRegexes.reviewSpamRegex.containsMatchIn(text) || DetailsVMRegexes.reviewDomainRegex.containsMatchIn(text)) return true
        if (text.count { it == '$' } > 2 || text.count { it == '!' } > 6) return true

        val visibleChars = text.count { !it.isWhitespace() }.coerceAtLeast(1)
        val letters = text.count { it.isLetter() }
        return letters.toFloat() / visibleChars.toFloat() < 0.45f
    }

    private suspend fun appendHomeServerSourcesInBackground(
        imdbId: String?,
        season: Int?,
        episode: Int?,
        timeoutMs: Long,
        requestId: Long,
        requestMediaType: MediaType,
        requestMediaId: Int
    ) {
        if (requestId != loadStreamsRequestId ||
            currentMediaType != requestMediaType ||
            currentMediaId != requestMediaId
        ) {
            return
        }
        val itemTitle = _uiState.value.item?.title.orEmpty()

        val sources = if (currentMediaType == MediaType.MOVIE) {
            streamRepository.resolveMovieHomeServerSources(
                imdbId = imdbId,
                title = itemTitle,
                year = _uiState.value.item?.year?.toIntOrNull(),
                tmdbId = currentMediaId,
                timeoutMs = timeoutMs
            )
        } else {
            streamRepository.resolveEpisodeHomeServerSources(
                imdbId = imdbId,
                season = season ?: 1,
                episode = episode ?: 1,
                title = itemTitle,
                tmdbId = currentMediaId,
                tvdbId = _uiState.value.tvdbId,
                timeoutMs = timeoutMs
            )
        }
        val validSources = sources.filter { !it.url.isNullOrBlank() }
        if (validSources.isEmpty()) {
            if (_uiState.value.streams.isEmpty() && vodAppendJob?.isActive != true) {
                _uiState.value = _uiState.value.copy(isLoadingStreams = false)
            }
            return
        }
        val latest = _uiState.value.streams
        if (requestId != loadStreamsRequestId ||
            currentMediaType != requestMediaType ||
            currentMediaId != requestMediaId
        ) {
            return
        }
        val mergedStreams = sortPlayableStreamsFirst(
            (latest + validSources)
                .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
        )
        _uiState.value = _uiState.value.copy(
            streams = mergedStreams,
            isLoadingStreams = false
        )
        prewarmVisibleStreams(mergedStreams)
    }

    private suspend fun appendVodSourceInBackground(
        imdbId: String?,
        season: Int?,
        episode: Int?,
        timeoutMs: Long,
        requestId: Long,
        requestMediaType: MediaType,
        requestMediaId: Int
    ) {
        if (requestId != loadStreamsRequestId ||
            currentMediaType != requestMediaType ||
            currentMediaId != requestMediaId
        ) {
            return
        }
        val itemTitle = _uiState.value.item?.title.orEmpty()

        val vodSources = if (requestMediaType == MediaType.MOVIE) {
            streamRepository.resolveMovieVodSources(
                imdbId = imdbId,
                title = itemTitle,
                year = _uiState.value.item?.year?.toIntOrNull(),
                tmdbId = currentMediaId,
                timeoutMs = timeoutMs
            )
        } else {
            streamRepository.resolveEpisodeVodSources(
                imdbId = imdbId,
                season = season ?: 1,
                episode = episode ?: 1,
                title = itemTitle,
                tmdbId = currentMediaId,
                tvdbId = _uiState.value.tvdbId,
                timeoutMs = timeoutMs
            )
        }
        val validVodSources = vodSources.filter { !it.url.isNullOrBlank() }
        if (validVodSources.isEmpty()) {
            return
        }
        val latest = _uiState.value.streams
        if (requestId != loadStreamsRequestId ||
            currentMediaType != requestMediaType ||
            currentMediaId != requestMediaId
        ) {
            return
        }
        val mergedStreams = sortPlayableStreamsFirst(
            (latest + validVodSources)
                .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
        )
        _uiState.value = _uiState.value.copy(
            streams = mergedStreams,
            isLoadingStreams = false
        )
        prewarmVisibleStreams(mergedStreams)
    }
}

private object DetailsVMRegexes {
    val reviewWhitespaceRegex = Regex("\\s+")
    val reviewMarkdownLinkRegex = Regex("\\[([^\\]]+)]\\([^)]*\\)")
    val reviewHtmlTagRegex = Regex("<[^>]*>")
    val reviewMarkdownNoiseRegex = Regex("[*_`>#]+")
    val reviewSpamRegex = Regex(
        pattern = "\\b(?:https?://|www\\.|discord\\.gg|t\\.me/|telegram|whatsapp|onlyfans|casino|betting|viagra|loan|crypto|airdrop|promo\\s+code|coupon|download\\s+now|watch\\s+(?:free|online)|free\\s+stream|\\.xyz\\b|\\.top\\b|\\.click\\b|\\.link\\b|\\.site\\b)\\b",
        option = RegexOption.IGNORE_CASE
    )
    val reviewDomainRegex = Regex(
        pattern = "\\b[a-z0-9-]+\\.(?:com|net|org|xyz|top|click|link|site|online|shop|info)\\b",
        option = RegexOption.IGNORE_CASE
    )

}

private fun LocalScraperResult.toStreamSource(): StreamSource = StreamSource(
    source = title,
    addonName = provider ?: name ?: "Plugin",
    addonId = "plugin_${provider?.lowercase()?.replace(" ", "_") ?: "unknown"}",
    quality = quality ?: "Unknown",
    size = size ?: "",
    sizeBytes = null,
    url = url,
    infoHash = infoHash,
    fileIdx = null,
    behaviorHints = headers?.let { hdrs ->
        com.arflix.tv.data.model.StreamBehaviorHints(
            notWebReady = false,
            proxyHeaders = com.arflix.tv.data.model.ProxyHeaders(request = hdrs)
        )
    },
    subtitles = emptyList(),
    sources = emptyList(),
    description = null
)
