package com.arflix.tv.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.PersonDetails
import com.arflix.tv.data.model.Review
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailsUiState(
    val isLoading: Boolean = true,
    val item: MediaItem? = null,
    val imdbId: String? = null,  // Real IMDB ID for stream resolution
    val logoUrl: String? = null,
    val trailerKey: String? = null,
    val episodes: List<Episode> = emptyList(),
    val totalSeasons: Int = 1,
    val currentSeason: Int = 1,
    val cast: List<CastMember> = emptyList(),
    val similar: List<MediaItem> = emptyList(),
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
    // Initial positions for Continue Watching navigation
    val initialEpisodeIndex: Int = 0,
    val initialSeasonIndex: Int = 0,
    // Season progress: Map<seasonNumber, Pair<watchedCount, totalCount>>
    val seasonProgress: Map<Int, Pair<Int, Int>> = emptyMap(),
    val playSeason: Int? = null,
    val playEpisode: Int? = null,
    val playLabel: String? = null
)

private data class PlayTarget(
    val season: Int? = null,
    val episode: Int? = null,
    val label: String
)

private data class SeasonProgressResult(
    val progress: Map<Int, Pair<Int, Int>>,
    val hasWatched: Boolean,
    val nextUnwatched: Pair<Int, Int>?
)

private data class ResumeInfo(
    val season: Int? = null,
    val episode: Int? = null,
    val label: String
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

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val traktRepository: TraktRepository,
    private val streamRepository: StreamRepository,
    private val tmdbApi: TmdbApi,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val watchlistRepository: WatchlistRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()
    
    private var currentMediaType: MediaType = MediaType.MOVIE
    private var currentMediaId: Int = 0

    private fun isBlankRating(value: String): Boolean {
        return value.isBlank() || value == "0.0" || value == "0"
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

        viewModelScope.launch {
            try {
                val previousState = _uiState.value
                val previousMatches = previousState.item?.id == mediaId &&
                    previousState.item?.mediaType == mediaType
                val seasonToLoad = initialSeason ?: 1
                val previousItem = _uiState.value.item?.takeIf {
                    it.id == mediaId && it.mediaType == mediaType
                }
                val cachedItem = mediaRepository.getCachedItem(mediaType, mediaId)
                val initialItem = cachedItem ?: previousItem
                val cachedTotalSeasons = if (mediaType == MediaType.TV) {
                    initialItem?.totalEpisodes?.coerceAtLeast(1) ?: 1
                } else {
                    1
                }

                _uiState.value = DetailsUiState(
                    isLoading = initialItem == null,
                    item = initialItem,
                    currentSeason = seasonToLoad,
                    totalSeasons = cachedTotalSeasons
                )

                val itemDeferred = async {
                    if (mediaType == MediaType.TV) {
                        mediaRepository.getTvDetails(mediaId)
                    } else {
                        mediaRepository.getMovieDetails(mediaId)
                    }
                }
                // Load supporting data in parallel
                val logoDeferred = async { mediaRepository.getLogoUrl(mediaType, mediaId) }
                val trailerDeferred = async { mediaRepository.getTrailerKey(mediaType, mediaId) }
                val castDeferred = async { mediaRepository.getCast(mediaType, mediaId) }
                val similarDeferred = async { mediaRepository.getSimilar(mediaType, mediaId) }
                val watchlistDeferred = async { watchlistRepository.isInWatchlist(mediaType, mediaId) }
                val reviewsDeferred = async { mediaRepository.getReviews(mediaType, mediaId) }

                // Fetch real IMDB ID from TMDB external_ids endpoint
                val imdbDeferred = async { resolveImdbId(mediaType, mediaId) }
                val resumeDeferred = async { fetchResumeInfo(mediaId, mediaType) }

                // For TV shows, also load episodes
                val episodesDeferred = if (mediaType == MediaType.TV) {
                    async { mediaRepository.getSeasonEpisodes(mediaId, seasonToLoad) }
                } else null

                // For TV shows, fetch season progress (watched/total per season)
                val seasonProgressDeferred = if (mediaType == MediaType.TV) {
                    async { fetchSeasonProgress(mediaId) }
                } else null

                val item = runCatching { itemDeferred.await() }.getOrNull() ?: initialItem
                if (item == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Failed to load details"
                    )
                    return@launch
                }
                val mergedItem = mergeItem(item, initialItem)

                // Get total seasons for TV shows (stored in totalEpisodes field)
                val totalSeasons = if (mediaType == MediaType.TV) {
                    mergedItem.totalEpisodes?.coerceAtLeast(1) ?: 1
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

                // Get show status
                val showStatus = if (mediaType == MediaType.TV) mergedItem.status else null

                val traktAuthed = traktRepository.isAuthenticated.first()
                if (traktAuthed) {
                    traktRepository.initializeWatchedCache()
                }

                // Check if item is watched (for movies, check Trakt; for TV, check if started)
                val isWatched = if (mediaType == MediaType.MOVIE) {
                    traktRepository.isMovieWatched(mediaId)
                } else {
                    // For TV shows, check if any episode is watched
                    traktRepository.getWatchedEpisodesFromCache().any { it.startsWith("show_tmdb:$mediaId:") }
                }
                val itemWithWatchedStatus = mergedItem.copy(isWatched = isWatched)

                val baseState = _uiState.value.copy(
                    isLoading = false,
                    item = itemWithWatchedStatus,
                    totalSeasons = totalSeasons,
                    currentSeason = seasonToLoad,
                    genres = genreNames,
                    language = languageName,
                    budget = budgetDisplay,
                    showStatus = showStatus
                )
                _uiState.value = baseState

                val requestMediaId = mediaId
                val requestMediaType = mediaType
                fun isCurrentRequest(): Boolean {
                    return currentMediaId == requestMediaId && currentMediaType == requestMediaType
                }
                fun updateState(block: (DetailsUiState) -> DetailsUiState) {
                    if (!isCurrentRequest()) return
                    _uiState.value = block(_uiState.value)
                }

                // Calculate initial season index (0-based)
                val initialSeasonIndex = (seasonToLoad - 1).coerceAtLeast(0)
                updateState { it.copy(initialSeasonIndex = initialSeasonIndex) }

                launch {
                    val imdbId = runCatching { imdbDeferred.await() }.getOrNull()
                    if (!imdbId.isNullOrBlank()) {
                        mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
                        updateState { state -> state.copy(imdbId = imdbId) }
                    }
                }

                launch {
                    val logoUrl = runCatching { logoDeferred.await() }.getOrNull()
                    if (logoUrl != null) {
                        updateState { state -> state.copy(logoUrl = logoUrl) }
                    }
                }

                launch {
                    val trailerKey = runCatching { trailerDeferred.await() }.getOrNull()
                    if (trailerKey != null) {
                        updateState { state -> state.copy(trailerKey = trailerKey) }
                    }
                }

                launch {
                    val cast = runCatching { castDeferred.await() }.getOrNull()
                    if (!cast.isNullOrEmpty()) {
                        updateState { state -> state.copy(cast = cast) }
                    }
                }

                launch {
                    val similar = runCatching { similarDeferred.await() }.getOrNull()
                    if (!similar.isNullOrEmpty()) {
                        updateState { state -> state.copy(similar = similar) }
                    }
                }

                launch {
                    val reviews = runCatching { reviewsDeferred.await() }.getOrNull()
                    if (!reviews.isNullOrEmpty()) {
                        updateState { state -> state.copy(reviews = reviews) }
                    }
                }

                launch {
                    val episodes = runCatching { episodesDeferred?.await() }.getOrNull()
                    if (!episodes.isNullOrEmpty()) {
                        val initialEpisodeIndex = if (initialEpisode != null) {
                            episodes.indexOfFirst { it.episodeNumber == initialEpisode }.coerceAtLeast(0)
                        } else 0
                        updateState { state ->
                            state.copy(
                                episodes = episodes,
                                initialEpisodeIndex = initialEpisodeIndex
                            )
                        }
                    }
                }

                launch {
                    val isInWatchlist = runCatching { watchlistDeferred.await() }.getOrDefault(false)
                    updateState { state -> state.copy(isInWatchlist = isInWatchlist) }
                }

                launch {
                    val seasonProgressResult = runCatching { seasonProgressDeferred?.await() }.getOrNull()
                    val seasonProgress = seasonProgressResult?.progress ?: emptyMap()
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
                    val seasonProgressResult = runCatching { seasonProgressDeferred?.await() }.getOrNull()
                    val playTarget = buildPlayTarget(mediaType, seasonProgressResult, resumeInfo)
                    updateState { state ->
                        state.copy(
                            playSeason = playTarget?.season,
                            playEpisode = playTarget?.episode,
                            playLabel = playTarget?.label
                        )
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
                    _uiState.value = _uiState.value.copy(
                        episodes = episodes,
                        currentSeason = seasonNumber
                    )
                } else {
                    // If no episodes returned, keep current and show error
                    _uiState.value = _uiState.value.copy(
                        toastMessage = "No episodes found for Season $seasonNumber",
                        toastType = ToastType.ERROR
                    )
                }
            } catch (e: Exception) {
                // On error, keep showing current episodes
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to load Season $seasonNumber",
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
                        toastMessage = if (newWatched) "Marked as watched" else "Marked as unwatched",
                        toastType = ToastType.SUCCESS
                    )
                } else {
                    val targetEpisode = _uiState.value.episodes.getOrNull(episodeIndex ?: 0)
                    if (targetEpisode == null) {
                        _uiState.value = _uiState.value.copy(
                            toastMessage = "No episode selected",
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
                            "S${targetEpisode.seasonNumber}E${targetEpisode.episodeNumber} marked as watched"
                        } else {
                            "S${targetEpisode.seasonNumber}E${targetEpisode.episodeNumber} marked as unwatched"
                        },
                        toastType = ToastType.SUCCESS
                    )
                }
            } catch (e: Exception) {
                android.util.Log.e("DetailsViewModel", "toggleWatched failed", e)
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to update watched status",
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
                if (newInWatchlist) {
                    watchlistRepository.addToWatchlist(currentMediaType, currentMediaId)
                } else {
                    watchlistRepository.removeFromWatchlist(currentMediaType, currentMediaId)
                }

                _uiState.value = _uiState.value.copy(
                    isInWatchlist = newInWatchlist,
                    toastMessage = if (newInWatchlist) "Added to watchlist" else "Removed from watchlist",
                    toastType = ToastType.SUCCESS
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to update watchlist",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
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
    
    fun loadStreams(imdbId: String, season: Int? = null, episode: Int? = null) {
        android.util.Log.d("DetailsViewModel", "loadStreams called: imdbId=$imdbId, season=$season, episode=$episode")
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoadingStreams = true)

            try {
                val result = if (currentMediaType == MediaType.MOVIE) {
                    streamRepository.resolveMovieStreams(imdbId)
                } else {
                    streamRepository.resolveEpisodeStreams(imdbId, season ?: 1, episode ?: 1)
                }

                android.util.Log.d("DetailsViewModel", "loadStreams result: ${result.streams.size} streams, ${result.subtitles.size} subtitles")

                val filteredStreams = result.streams.filter { it.url != null }
                _uiState.value = _uiState.value.copy(
                    isLoadingStreams = false,
                    streams = filteredStreams,
                    subtitles = result.subtitles
                )
            } catch (e: Exception) {
                android.util.Log.e("DetailsViewModel", "loadStreams error: ${e.message}", e)
                _uiState.value = _uiState.value.copy(isLoadingStreams = false)
            }
        }
    }
    
    fun markEpisodeWatched(season: Int, episode: Int, watched: Boolean) {
        android.util.Log.d("DetailsViewModel", "markEpisodeWatched called: mediaId=$currentMediaId, S${season}E${episode}, watched=$watched")
        viewModelScope.launch {
            try {
                if (watched) {
                    android.util.Log.d("DetailsViewModel", "Calling traktRepository.markEpisodeWatched for $currentMediaId S${season}E${episode}")
                    traktRepository.markEpisodeWatched(currentMediaId, season, episode)
                    // Also remove from Supabase watch_history (removes from Continue Watching)
                    android.util.Log.d("DetailsViewModel", "Calling watchHistoryRepository.removeFromHistory for $currentMediaId S${season}E${episode}")
                    watchHistoryRepository.removeFromHistory(currentMediaId, season, episode)
                } else {
                    android.util.Log.d("DetailsViewModel", "Calling traktRepository.markEpisodeUnwatched for $currentMediaId S${season}E${episode}")
                    traktRepository.markEpisodeUnwatched(currentMediaId, season, episode)
                }

                // Update local state
                val updatedEpisodes = _uiState.value.episodes.map { ep ->
                    if (ep.seasonNumber == season && ep.episodeNumber == episode) {
                        ep.copy(isWatched = watched)
                    } else ep
                }
                _uiState.value = _uiState.value.copy(episodes = updatedEpisodes)
                android.util.Log.d("DetailsViewModel", "markEpisodeWatched completed successfully")
            } catch (e: Exception) {
                android.util.Log.e("DetailsViewModel", "markEpisodeWatched failed", e)
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
            traktRepository.initializeWatchedCache()
            val cachedEpisodes = traktRepository.getWatchedEpisodesFromCache()
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
                traktRepository.getWatchedEpisodesForShow(tmdbId)
            }

            val tvDetails = tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY)
            val numSeasons = tvDetails.numberOfSeasons

            val progressMap = mutableMapOf<Int, Pair<Int, Int>>()
            var nextUnwatched: Pair<Int, Int>? = null

            for (seasonNum in 1..numSeasons) {
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
                hasWatched = watchedKeys.isNotEmpty(),
                nextUnwatched = nextUnwatched
            )
        } catch (e: Exception) {
            SeasonProgressResult(emptyMap(), false, null)
        }
    }

    private suspend fun fetchResumeInfo(tmdbId: Int, mediaType: MediaType): ResumeInfo? {
        return try {
            val entry = watchHistoryRepository.getLatestProgress(mediaType, tmdbId)
            if (entry != null) {
                val resume = buildResumeFromProgress(
                    mediaType = mediaType,
                    tmdbId = tmdbId,
                    season = entry.season,
                    episode = entry.episode,
                    progress = entry.progress,
                    positionSeconds = entry.position_seconds,
                    durationSeconds = entry.duration_seconds
                )
                if (resume != null) return resume
            }

            val traktResume = try {
                traktRepository.getContinueWatching()
                    .firstOrNull { it.id == tmdbId && it.mediaType == mediaType && it.progress > 0 }
            } catch (_: Exception) {
                null
            }
            if (traktResume != null) {
                val resume = buildResumeFromProgress(
                    mediaType = mediaType,
                    tmdbId = tmdbId,
                    season = traktResume.season,
                    episode = traktResume.episode,
                    progress = traktResume.progress / 100f,
                    positionSeconds = 0L,
                    durationSeconds = 0L
                )
                if (resume != null) return resume
            }

            null
        } catch (e: Exception) {
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
        durationSeconds: Long
    ): ResumeInfo? {
        val normalizedDuration = if (durationSeconds > 86_400L) durationSeconds / 1000L else durationSeconds
        val normalizedPosition = if (positionSeconds > 86_400L) positionSeconds / 1000L else positionSeconds

        var seconds = when {
            normalizedPosition > 0 -> normalizedPosition
            normalizedDuration > 0 && progress > 0f -> (normalizedDuration * progress).toLong()
            else -> 0L
        }
        if (seconds <= 0L && progress > 0f) {
            val runtimeSeconds = resolveRuntimeSeconds(tmdbId, mediaType, season, episode)
            if (runtimeSeconds > 0L) {
                seconds = (runtimeSeconds * progress).toLong()
            }
        }
        if (seconds <= 0L) return null
        val timeLabel = formatResumeTime(seconds)
        if (timeLabel.isBlank()) return null

        return if (mediaType == MediaType.MOVIE) {
            ResumeInfo(label = "Resume $timeLabel")
        } else {
            val s = season ?: return null
            val e = episode ?: return null
            ResumeInfo(
                season = s,
                episode = e,
                label = "Resume $timeLabel E$e - S$s"
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
                label = resumeInfo.label
            )
        }
        if (mediaType == MediaType.MOVIE) return null
        if (result == null) return null
        return if (!result.hasWatched) {
            PlayTarget(
                season = 1,
                episode = 1,
                label = "Start E1-S1"
            )
        } else {
            val next = result.nextUnwatched
            if (next != null) {
                PlayTarget(
                    season = next.first,
                    episode = next.second,
                    label = "Continue S${next.first}-E${next.second}"
                )
            } else {
                PlayTarget(
                    season = 1,
                    episode = 1,
                    label = "Start E1-S1"
                )
            }
        }
    }

    private suspend fun resolveImdbId(mediaType: MediaType, mediaId: Int): String? {
        return try {
            when (mediaType) {
                MediaType.MOVIE -> tmdbApi.getMovieExternalIds(mediaId, Constants.TMDB_API_KEY).imdbId
                MediaType.TV -> tmdbApi.getTvExternalIds(mediaId, Constants.TMDB_API_KEY).imdbId
            }
        } catch (_: Exception) {
            null
        }
    }
}
