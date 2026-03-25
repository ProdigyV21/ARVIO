package com.arflix.tv.ui.screens.details

import android.content.Context
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
import com.arflix.tv.data.repository.CloudSyncRepository
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
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale
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
    val hasStreamingAddons: Boolean = true,
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
    val autoPlayMaxQuality: String = "4K"
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

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mediaRepository: MediaRepository,
    private val profileManager: ProfileManager,
    private val traktRepository: TraktRepository,
    private val streamRepository: StreamRepository,
    private val tmdbApi: TmdbApi,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val watchlistRepository: WatchlistRepository,
    private val cloudSyncRepository: CloudSyncRepository,
    private val launcherContinueWatchingRepository: LauncherContinueWatchingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    private var currentMediaType: MediaType = MediaType.MOVIE
    private var currentMediaId: Int = 0
    private var vodAppendJob: kotlinx.coroutines.Job? = null
    private var loadStreamsJob: kotlinx.coroutines.Job? = null
    private var loadStreamsRequestId: Long = 0L
    /** Set to true after loadDetails() child coroutines finish populating episodes/seasons. */
    @Volatile private var initialLoadComplete = false
    
    private fun autoPlaySingleSourceKey() = profileManager.profileBooleanKey("auto_play_single_source")
    private fun autoPlayMinQualityKey() = profileManager.profileStringKey("auto_play_min_quality")
    private fun autoPlayMaxQualityKey() = profileManager.profileStringKey("auto_play_max_quality")

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

    private fun normalizeAutoPlayMaxQuality(raw: String?): String {
        return when (raw?.trim()?.lowercase()) {
            "any" -> "Any"
            "720p", "hd" -> "720p"
            "1080p", "fullhd", "fhd" -> "1080p"
            "4k", "2160p", "uhd" -> "4K"
            else -> "4K"
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

        viewModelScope.launch {
            try {
                val prefs = context.settingsDataStore.data.first()
                val autoPlaySingleSource = prefs[autoPlaySingleSourceKey()] ?: true
                val autoPlayMinQuality = normalizeAutoPlayMinQuality(prefs[autoPlayMinQualityKey()])
                val autoPlayMaxQuality = normalizeAutoPlayMaxQuality(prefs[autoPlayMaxQualityKey()])

                val previousState = _uiState.value
                val previousMatches = previousState.item?.id == mediaId &&
                    previousState.item?.mediaType == mediaType
                val seasonToLoad = initialSeason ?: 1
                val hasExplicitEpisodeTarget = mediaType == MediaType.TV && initialSeason != null && initialEpisode != null
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
                    totalSeasons = cachedTotalSeasons,
                    playSeason = initialSeason,
                    playEpisode = initialEpisode,
                    playLabel = if (mediaType == MediaType.TV && initialSeason != null && initialEpisode != null) {
                        "Continue S${initialSeason}E${initialEpisode}"
                    } else {
                        null
                    },
                    autoPlaySingleSource = autoPlaySingleSource,
                    autoPlayMinQuality = autoPlayMinQuality,
                    autoPlayMaxQuality = autoPlayMaxQuality
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
                val streamingServicesDeferred = async {
                    mediaRepository.getStreamingServices(
                        mediaType = mediaType,
                        mediaId = mediaId,
                        preferredRegion = Locale.getDefault().country
                    )
                }

                // Fetch real IMDB ID and TVDB ID from TMDB external_ids endpoint
                val externalIdsDeferred = async { resolveExternalIds(mediaType, mediaId) }
                val resumeDeferred = async { fetchResumeInfo(mediaId, mediaType, initialSeason, initialEpisode) }

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
                    } else if (tvdbId != null) {
                        updateState { state -> state.copy(tvdbId = tvdbId) }
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
                        val logos = similar.take(20).map { item ->
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
                    val reviews = runCatching { reviewsDeferred.await() }.getOrNull()
                    if (!reviews.isNullOrEmpty()) {
                        updateState { state -> state.copy(reviews = reviews) }
                    }
                }

                launch {
                    val servicesResult = runCatching { streamingServicesDeferred.await() }.getOrNull()
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
                        val initialEpisodeIndex = if (targetEpisodeForRow != null) {
                            decoratedEpisodes.indexOfFirst { it.episodeNumber == targetEpisodeForRow }.coerceAtLeast(0)
                        } else 0
                        val nextUnwatchedEpisode = decoratedEpisodes.firstOrNull { !it.isWatched }
                        val hasWatchedEpisodes = decoratedEpisodes.any { it.isWatched }
                        updateState { state ->
                            val shouldUseEpisodeTarget = !hasExplicitEpisodeTarget &&
                                (state.playLabel.isNullOrBlank() || state.playLabel == "Start S1E1")
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
                                        "Continue S${nextUnwatchedEpisode.seasonNumber}E${nextUnwatchedEpisode.episodeNumber}"
                                    } else if (hasWatchedEpisodes) {
                                        "Start S1E1"
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
                                playLabel = matchedResume?.label ?: "Continue S${initialSeason}E${initialEpisode}",
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
                        streamRepository.prefetchSeriesVodInfo(
                            imdbId = null,
                            title = titleForPrefetch,
                            tmdbId = mediaId
                        )
                        val externalIds = runCatching { externalIdsDeferred.await() }.getOrNull()
                        streamRepository.prefetchSeriesVodInfo(
                            imdbId = externalIds?.imdbId,
                            title = titleForPrefetch,
                            tmdbId = mediaId
                        )
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
                        streamRepository.prefetchEpisodeVod(
                            imdbId = externalIds?.imdbId,
                            season = targetSeason,
                            episode = targetEpisode,
                            title = titleForPrefetch,
                            tmdbId = mediaId
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
                    runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
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
                            "S${targetEpisode.seasonNumber}E${targetEpisode.episodeNumber} marked as watched"
                        } else {
                            "S${targetEpisode.seasonNumber}E${targetEpisode.episodeNumber} marked as unwatched"
                        },
                        toastType = ToastType.SUCCESS
                    )
                    runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                }
            } catch (e: Exception) {
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
                    // Pass the full MediaItem so it appears instantly in watchlist
                    watchlistRepository.addToWatchlist(currentMediaType, currentMediaId, currentItem)
                } else {
                    watchlistRepository.removeFromWatchlist(currentMediaType, currentMediaId)
                }
                runCatching { cloudSyncRepository.pushToCloud() }

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
                        label = "Continue S${seasonNum}E${firstUnwatched.episodeNumber}"
                    )
                }
            }
            // All episodes watched — offer restart
            PlayTarget(season = 1, episode = 1, label = "Start S1E1")
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
        prefetchJob = viewModelScope.launch {
            runCatching {
                if (currentMediaType == MediaType.MOVIE) {
                    streamRepository.resolveMovieStreams(
                        imdbId = imdbId,
                        title = _uiState.value.item?.title.orEmpty(),
                        year = _uiState.value.item?.year?.toIntOrNull()
                    )
                } else if (season != null && episode != null) {
                    val prefetchAirDate = _uiState.value.episodes
                        .firstOrNull { it.seasonNumber == season && it.episodeNumber == episode }
                        ?.airDate?.takeIf { it.isNotBlank() }
                    streamRepository.resolveEpisodeStreams(
                        imdbId = imdbId,
                        season = season,
                        episode = episode,
                        tmdbId = currentMediaId,
                        tvdbId = _uiState.value.tvdbId,
                        genreIds = _uiState.value.item?.genreIds ?: emptyList(),
                        originalLanguage = _uiState.value.item?.originalLanguage,
                        title = _uiState.value.item?.title ?: "",
                        airDate = prefetchAirDate
                    )
                } else {
                    null
                }
            }
        }
    }

    fun loadStreams(imdbId: String?, season: Int? = null, episode: Int? = null) {
        loadStreamsJob?.cancel()
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
            _uiState.value = _uiState.value.copy(
                isLoadingStreams = true,
                streams = emptyList(),
                subtitles = emptyList()
            )

            try {
                // Get current item's genre IDs and language for anime detection
                val item = _uiState.value.item
                val genreIds = item?.genreIds ?: emptyList()
                val originalLanguage = item?.originalLanguage
                // Start VOD append in background - runs parallel to addon stream fetch
                vodAppendJob?.cancel()
                vodAppendJob = viewModelScope.launch {
                    // VOD lookups use disk-cached catalogs (near-instant on warm starts).
                    // On rare true cold starts, catalog download can take 15-30s for large providers.
                    val vodTimeout = if (currentMediaType == MediaType.MOVIE) 30_000L else 45_000L
                    appendVodSourceInBackground(
                        imdbId = imdbId,
                        season = season,
                        episode = episode,
                        timeoutMs = vodTimeout,
                        requestId = requestId,
                        requestMediaType = requestMediaType,
                        requestMediaId = requestMediaId
                    )
                }

                val result = if (currentMediaType == MediaType.MOVIE) {
                    if (imdbId.isNullOrBlank()) {
                        _uiState.value = _uiState.value.copy(
                            isLoadingStreams = false,
                            streams = emptyList(),
                            subtitles = emptyList(),
                            hasStreamingAddons = streamRepository.installedAddons.first()
                                .count { it.isEnabled && it.type != com.arflix.tv.data.model.AddonType.SUBTITLE } > 0
                        )
                        return@launch
                    }
                    streamRepository.resolveMovieStreamsProgressive(
                        imdbId = imdbId,
                        title = item?.title.orEmpty(),
                        year = item?.year?.toIntOrNull()
                    ).collect { progressive ->
                        if (!isCurrentRequest()) return@collect
                        val existingVod = _uiState.value.streams.filter { it.addonId == "iptv_xtream_vod" }
                        val mergedStreams = (progressive.streams + existingVod)
                            .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                        val addonCount = streamRepository.installedAddons.first()
                            .count { it.isEnabled && it.type != com.arflix.tv.data.model.AddonType.SUBTITLE }
                        _uiState.value = _uiState.value.copy(
                            isLoadingStreams = !progressive.isFinal && mergedStreams.isEmpty(),
                            streams = mergedStreams,
                            subtitles = progressive.subtitles,
                            hasStreamingAddons = addonCount > 0
                        )
                    }
                    return@launch
                } else {
                    if (imdbId.isNullOrBlank()) {
                        _uiState.value = _uiState.value.copy(
                            isLoadingStreams = false,
                            streams = emptyList(),
                            subtitles = emptyList(),
                            hasStreamingAddons = streamRepository.installedAddons.first()
                                .count { it.isEnabled && it.type != com.arflix.tv.data.model.AddonType.SUBTITLE } > 0
                        )
                        return@launch
                    }
                    // Look up air date for daily show stream resolution fallback
                    val episodeAirDate = _uiState.value.episodes
                        .firstOrNull { it.seasonNumber == (season ?: 1) && it.episodeNumber == (episode ?: 1) }
                        ?.airDate?.takeIf { it.isNotBlank() }

                    streamRepository.resolveEpisodeStreamsProgressive(
                        imdbId = imdbId,
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
                        val existingVod = _uiState.value.streams.filter { it.addonId == "iptv_xtream_vod" }
                        val mergedStreams = (progressive.streams + existingVod)
                            .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                        val addonCount = streamRepository.installedAddons.first()
                            .count { it.isEnabled && it.type != com.arflix.tv.data.model.AddonType.SUBTITLE }
                        _uiState.value = _uiState.value.copy(
                            isLoadingStreams = !progressive.isFinal && mergedStreams.isEmpty(),
                            streams = mergedStreams,
                            subtitles = progressive.subtitles,
                            hasStreamingAddons = addonCount > 0
                        )
                    }
                    return@launch
                }
            } catch (e: Exception) {
                if (!isCurrentRequest()) return@launch
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
                        toastMessage = "No episodes found for Season $season",
                        toastType = ToastType.ERROR
                    )
                    return@launch
                }

                seasonEpisodes.forEach { ep ->
                    runCatching {
                        traktRepository.markEpisodeWatched(currentMediaId, season, ep.episodeNumber)
                        watchHistoryRepository.removeFromHistory(currentMediaId, season, ep.episodeNumber)
                    }
                }

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
                    toastMessage = "Season $season marked as watched",
                    toastType = ToastType.SUCCESS
                )
                runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
            } catch (_: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to mark season as watched",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    /**
     * Resolve real IMDB ID from TMDB using external_ids endpoint
     * This is required for addon stream resolution
     */
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
            val entry = exactEntry ?: watchHistoryRepository.getLatestProgress(mediaType, tmdbId)
            val cloudResume = if (entry != null) {
                val resume = buildResumeFromProgress(
                    mediaType = mediaType,
                    tmdbId = tmdbId,
                    season = entry.season,
                    episode = entry.episode,
                    progress = entry.progress,
                    positionSeconds = entry.position_seconds,
                    durationSeconds = entry.duration_seconds
                )
                resume
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
                val resume = buildResumeFromProgress(
                    mediaType = mediaType,
                    tmdbId = tmdbId,
                    season = resumeCandidate.season,
                    episode = resumeCandidate.episode,
                    progress = resumeCandidate.progress / 100f,
                    positionSeconds = resumeCandidate.resumePositionSeconds,
                    durationSeconds = resumeCandidate.durationSeconds
                )
                resume
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
        durationSeconds: Long
    ): ResumeInfo? {
        val normalizedDuration = if (durationSeconds > 86_400L) durationSeconds / 1000L else durationSeconds
        val normalizedPosition = if (positionSeconds > 86_400L) positionSeconds / 1000L else positionSeconds

        // If both position and duration are 0, this entry has no real playback data.
        // The progress field may be a show-level synthetic value (% of episodes watched),
        // NOT an episode playback percentage. Don't derive a fake resume position from it.
        if (normalizedPosition <= 0L && normalizedDuration <= 0L) {
            return null
        }

        var seconds = when {
            normalizedPosition > 0 -> normalizedPosition
            normalizedDuration > 0 && progress > 0f -> (normalizedDuration * progress).toLong()
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
                label = "Continue at $timeLabel",
                positionMs = seconds * 1000L
            )
        } else {
            val s = season ?: return null
            val e = episode ?: return null
            ResumeInfo(
                season = s,
                episode = e,
                label = "Continue S${s}E${e} at $timeLabel",
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
                label = "Start S1E1"
            )
        } else {
            val next = result.nextUnwatched
            if (next != null) {
                PlayTarget(
                    season = next.first,
                    episode = next.second,
                    label = "Continue S${next.first}E${next.second}"
                )
            } else {
                PlayTarget(
                    season = 1,
                    episode = 1,
                    label = "Start S1E1"
                )
            }
        }
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
        val currentStreams = _uiState.value.streams
        if (currentStreams.any { it.addonId == "iptv_xtream_vod" }) {
            return
        }
        val itemTitle = _uiState.value.item?.title.orEmpty()

        val vod = if (currentMediaType == MediaType.MOVIE) {
            streamRepository.resolveMovieVodOnly(
                imdbId = imdbId,
                title = itemTitle,
                year = _uiState.value.item?.year?.toIntOrNull(),
                tmdbId = currentMediaId,
                timeoutMs = timeoutMs
            )
        } else {
            streamRepository.resolveEpisodeVodOnly(
                imdbId = imdbId,
                season = season ?: 1,
                episode = episode ?: 1,
                title = itemTitle,
                tmdbId = currentMediaId,
                timeoutMs = timeoutMs
            )
        }
        if (vod == null) {
            return
        }

        if (vod.url.isNullOrBlank()) {
            return
        }
        val latest = _uiState.value.streams
        if (latest.any { it.url == vod.url && it.source == vod.source }) {
            return
        }
        if (requestId != loadStreamsRequestId ||
            currentMediaType != requestMediaType ||
            currentMediaId != requestMediaId
        ) {
            return
        }
        _uiState.value = _uiState.value.copy(
            streams = latest + vod,
            isLoadingStreams = false
        )
    }
}