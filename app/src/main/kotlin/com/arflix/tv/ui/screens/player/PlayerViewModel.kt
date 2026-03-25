package com.arflix.tv.ui.screens.player

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.PlaybackTelemetryRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.SkipInterval
import com.arflix.tv.data.repository.SkipIntroRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryEntry
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.util.Constants
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlayerUiState(
    val isLoading: Boolean = true,
    val isLoadingStreams: Boolean = false,
    val isLoadingSubtitles: Boolean = false,
    val title: String = "",
    val backdropUrl: String? = null,
    val logoUrl: String? = null,
    val streams: List<StreamSource> = emptyList(),
    val subtitles: List<Subtitle> = emptyList(),
    val selectedStream: StreamSource? = null,
    val selectedStreamUrl: String? = null,
    val streamSelectionNonce: Int = 0,
    val selectedSubtitle: Subtitle? = null,
    val subtitleSelectionNonce: Int = 0,
    val savedPosition: Long = 0,
    val preferredAudioLanguage: String = "en",
    val frameRateMatchingMode: String = "Off",
    val error: String? = null,
    val isSetupError: Boolean = false, // true when error is due to missing addons (shows friendly guide instead of red error)
    // Skip intro/recap
    val activeSkipInterval: SkipInterval? = null,
    val skipIntervalDismissed: Boolean = false
)

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val mediaRepository: MediaRepository,
    private val streamRepository: StreamRepository,
    private val traktRepository: TraktRepository,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val cloudSyncRepository: CloudSyncRepository,
    private val launcherContinueWatchingRepository: LauncherContinueWatchingRepository,
    private val tmdbApi: TmdbApi,
    private val skipIntroRepository: SkipIntroRepository,
    private val playbackTelemetryRepository: PlaybackTelemetryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    private var currentMediaType: MediaType = MediaType.MOVIE
    private var currentMediaId: Int = 0
    private var currentSeason: Int? = null
    private var currentEpisode: Int? = null
    private var currentTitle: String = ""
    private var currentPoster: String? = null
    private var currentBackdrop: String? = null
    private var currentEpisodeTitle: String? = null
    private var currentOriginalLanguage: String? = null
    private var currentAirDate: String? = null
    private var currentGenreIds: List<Int> = emptyList()
    private var currentItemTitle: String = ""
    private var currentTvdbId: Int? = null  // For anime Kitsu mapping
    private var currentImdbId: String? = null
    private var currentStartPositionMs: Long? = null
    private var currentPreferredAddonId: String? = null
    private var currentPreferredSourceName: String? = null
    private var currentPreferredBingeGroup: String? = null
    private var lastScrobbleTime: Long = 0
    private var lastWatchHistorySaveTime: Long = 0
    private var lastIsPlaying: Boolean = false
    private var hasMarkedWatched: Boolean = false

    // Skip intro
    private var skipIntervals: List<SkipInterval> = emptyList()
    private var lastActiveSkipType: String? = null
    private var skipIntervalsJob: kotlinx.coroutines.Job? = null
    private var activeSkipRequestKey: String? = null

    private val SKIP_INTERVAL_SHOW_EARLY_MS = 1_200L
    private val SKIP_INTERVAL_END_GUARD_MS = 500L
    private val SKIP_INTERVAL_MIN_VISIBLE_MS = 250L

    private val SCROBBLE_UPDATE_INTERVAL_MS = 20_000L
    private val WATCH_HISTORY_UPDATE_INTERVAL_MS = 15_000L

    private fun defaultSubtitleKey() = profileManager.profileStringKey("default_subtitle")
    private fun defaultAudioLanguageKey() = profileManager.profileStringKey("default_audio_language")
    private fun subtitleUsageKey() = profileManager.profileStringKey("subtitle_usage_v1")
    private fun frameRateMatchingModeKey() = profileManager.profileStringKey("frame_rate_matching_mode")
    private val gson = Gson()
    private val knownLanguageCodes = setOf(
        "en", "es", "fr", "de", "it", "pt", "nl", "ru", "zh", "ja", "ko",
        "ar", "hi", "tr", "pl", "sv", "no", "da", "fi", "el", "cs", "hu",
        "ro", "th", "vi", "id", "he",
        "uk", "fa", "bn", "bg", "hr", "sr", "sk", "sl", "lt", "et",
        "pt-br", "pob"
    )

    fun loadMedia(
        mediaType: MediaType,
        mediaId: Int,
        seasonNumber: Int?,
        episodeNumber: Int?,
        providedImdbId: String?,
        providedStreamUrl: String?,
        preferredAddonId: String?,
        preferredSourceName: String?,
        preferredBingeGroup: String?,
        startPositionMs: Long?,
        airDate: String? = null
    ) {
        currentAirDate = airDate
        currentMediaType = mediaType
        currentMediaId = mediaId
        currentSeason = seasonNumber
        currentEpisode = episodeNumber
        currentStartPositionMs = startPositionMs
        currentPreferredAddonId = preferredAddonId?.trim()?.takeIf { it.isNotBlank() }
        currentPreferredSourceName = preferredSourceName?.trim()?.takeIf { it.isNotBlank() }
        currentPreferredBingeGroup = preferredBingeGroup?.trim()?.takeIf { it.isNotBlank() }
        currentEpisodeTitle = null
        hasMarkedWatched = false
        lastIsPlaying = false
        lastScrobbleTime = 0
        lastWatchHistorySaveTime = 0
        subtitleRefreshJob?.cancel()
        vodAppendJob?.cancel()
        skipIntervalsJob?.cancel()
        currentImdbId = providedImdbId
        skipIntervals = emptyList()
        lastActiveSkipType = null
        activeSkipRequestKey = null
        _uiState.value = _uiState.value.copy(activeSkipInterval = null, skipIntervalDismissed = false)
        val cachedItem = mediaRepository.getCachedItem(mediaType, mediaId)
        currentOriginalLanguage = cachedItem?.originalLanguage
        currentGenreIds = cachedItem?.genreIds ?: emptyList()
        currentItemTitle = cachedItem?.title ?: ""

        viewModelScope.launch {
            val preferredAudioLanguage = resolvePreferredAudioLanguage()
            val frameRateMatchingMode = resolveFrameRateMatchingMode()
            _uiState.value = PlayerUiState(
                isLoading = true,
                isLoadingStreams = true,
                preferredAudioLanguage = preferredAudioLanguage,
                frameRateMatchingMode = frameRateMatchingMode
            )

            // If stream URL provided, use it directly (except magnet links, which require resolution).
            if (providedStreamUrl != null) {
                val resumeData = resolveResumeData(
                    mediaType = mediaType,
                    mediaId = mediaId,
                    seasonNumber = seasonNumber,
                    episodeNumber = episodeNumber,
                    navigationStartPositionMs = startPositionMs
                )
                val isMagnet = providedStreamUrl.startsWith("magnet:", ignoreCase = true)
                val resolvedProvidedUrl = if (isMagnet) null else providedStreamUrl

                if (resolvedProvidedUrl.isNullOrBlank()) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoadingStreams = false,
                        error = if (isMagnet) {
                            "Selected source is P2P (magnet) and not supported. Choose an HTTP/debrid source."
                        } else {
                            "Failed to open selected source. Try another one."
                        }
                    )
                    return@launch
                }

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    selectedStreamUrl = resolvedProvidedUrl,
                    savedPosition = resumeData.positionMs
                )
                launch {
                    kotlinx.coroutines.delay(1_500L)
                    populateStreamsForProvidedUrl(
                        mediaType = mediaType,
                        mediaId = mediaId,
                        seasonNumber = seasonNumber,
                        episodeNumber = episodeNumber,
                        providedImdbId = providedImdbId,
                        playbackUrl = resolvedProvidedUrl
                    )
                }
                // Fetch metadata in background
                launch { fetchMediaMetadata(mediaType, mediaId) }
                // Fetch skip intervals in background (needs IMDB id)
                launch {
                    if (mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null) {
                        val cachedImdbId = currentImdbId ?: mediaRepository.getCachedImdbId(mediaType, mediaId)
                        val imdbId = cachedImdbId ?: resolveExternalIds(mediaType, mediaId).imdbId
                        if (!imdbId.isNullOrBlank()) {
                            currentImdbId = imdbId
                            if (cachedImdbId == null) mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
                            fetchSkipIntervals(imdbId, seasonNumber, episodeNumber)
                        }
                    }
                }
                // Direct-URL playback must still fetch subtitle addons (e.g. OpenSubtitles).
                launch {
                    _uiState.value = _uiState.value.copy(isLoadingSubtitles = true)
                    val cachedImdbId = currentImdbId ?: mediaRepository.getCachedImdbId(mediaType, mediaId)
                    val imdbId = cachedImdbId ?: resolveExternalIds(mediaType, mediaId).imdbId

                    if (!imdbId.isNullOrBlank()) {
                        currentImdbId = imdbId
                        if (cachedImdbId.isNullOrBlank()) {
                            mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
                        }
                        val fetchedSubs = runCatching {
                            streamRepository.fetchSubtitlesForSelectedStream(
                                mediaType = mediaType,
                                imdbId = imdbId,
                                season = seasonNumber,
                                episode = episodeNumber,
                                stream = null
                            )
                        }.getOrDefault(emptyList())

                        val mergedSubs = (_uiState.value.subtitles + fetchedSubs)
                            .filter { it.url.isNotBlank() }
                            .distinctBy { "${it.id}|${it.url}" }

                        _uiState.value = _uiState.value.copy(
                            subtitles = mergedSubs,
                            isLoadingSubtitles = false
                        )

                        if (_uiState.value.selectedSubtitle == null) {
                            val preferredSub = getDefaultSubtitle()
                            applyPreferredSubtitle(preferredSub, mergedSubs, currentOriginalLanguage)
                        }
                    } else {
                        _uiState.value = _uiState.value.copy(isLoadingSubtitles = false)
                    }
                }
                return@launch
            }

            try {
                // INSTANT MODE: Fetch streams in parallel with metadata
                // Start metadata fetch in background (non-blocking)
                launch { fetchMediaMetadata(mediaType, mediaId) }

                // Fetch saved position from watch history (for resume playback)
                val resumeDataDeferred = async {
                    resolveResumeData(
                        mediaType = mediaType,
                        mediaId = mediaId,
                        seasonNumber = seasonNumber,
                        episodeNumber = episodeNumber,
                        navigationStartPositionMs = startPositionMs
                    )
                }

                // Get IMDB ID and TVDB ID as fast as possible
                val cachedImdbId = mediaRepository.getCachedImdbId(mediaType, mediaId)
                val imdbId: String?
                if (!providedImdbId.isNullOrBlank()) {
                    imdbId = providedImdbId
                    launch {
                        if (cachedImdbId.isNullOrBlank()) {
                            mediaRepository.cacheImdbId(mediaType, mediaId, providedImdbId)
                        }
                        currentTvdbId = resolveExternalIds(mediaType, mediaId).tvdbId
                    }
                } else if (!cachedImdbId.isNullOrBlank()) {
                    imdbId = cachedImdbId
                    // Don't block playback on external IDs when IMDB is already known.
                    launch {
                        currentTvdbId = resolveExternalIds(mediaType, mediaId).tvdbId
                    }
                } else {
                    val externalIds = resolveExternalIds(mediaType, mediaId)
                    currentTvdbId = externalIds.tvdbId
                    imdbId = externalIds.imdbId
                }
                if (imdbId.isNullOrBlank()) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoadingStreams = false,
                        error = "Unable to resolve IMDB ID. Try again."
                    )
                    return@launch
                }
                if (cachedImdbId.isNullOrBlank()) {
                    mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
                }
                currentImdbId = imdbId
                // Never block source loading on title hydration from TMDB.
                // Fetch skip intervals in background. This should never block playback.
                launch {
                    if (mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null) {
                        fetchSkipIntervals(imdbId, seasonNumber, episodeNumber)
                    }
                }
                // Start VOD append in background - single fast attempt, no retries blocking UI
                vodAppendJob?.cancel()
                vodAppendJob = launch {
                    // VOD runs in parallel with addon streams — catalog is disk-cached
                    // so lookups are usually fast. Give enough time for series info calls.
                    appendVodSourceInBackground(
                        mediaType = mediaType,
                        imdbId = imdbId,
                        seasonNumber = seasonNumber,
                        episodeNumber = episodeNumber,
                        timeoutMs = 15_000L
                    )
                }

                // Get saved position for resume playback
                val resumeData = resumeDataDeferred.await()
                val streamingAddonCount = streamRepository.installedAddons.first()
                    .count { it.isEnabled && it.type != com.arflix.tv.data.model.AddonType.SUBTITLE }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = true,
                    savedPosition = resumeData.positionMs,
                    error = null,
                    isSetupError = false
                )

                val preferredLanguage = _uiState.value.preferredAudioLanguage.ifBlank { resolvePreferredAudioLanguage() }
                val progressiveFlow = if (mediaType == MediaType.MOVIE) {
                    streamRepository.resolveMovieStreamsProgressive(
                        imdbId = imdbId,
                        title = currentItemTitle,
                        year = null
                    )
                } else {
                    streamRepository.resolveEpisodeStreamsProgressive(
                        imdbId = imdbId,
                        season = seasonNumber ?: 1,
                        episode = episodeNumber ?: 1,
                        tmdbId = mediaId,
                        tvdbId = currentTvdbId,
                        genreIds = currentGenreIds,
                        originalLanguage = currentOriginalLanguage,
                        title = currentItemTitle,
                        airDate = currentAirDate
                    )
                }

                // Collect progressive emissions, updating the UI as sources arrive.
                // If the first emission is already a cache hit (isFinal on first emit),
                // pick immediately — no collection window needed.
                // Otherwise wait up to AUTOPLAY_COLLECTION_WINDOW_MS for more addons.
                val AUTOPLAY_COLLECTION_WINDOW_MS = 3_500L
                val collectionStartMs = System.currentTimeMillis()
                var autoplaySelected = false
                var lastMergedStreams: List<StreamSource> = emptyList()
                var isFirstEmission = true

                progressiveFlow.collect { progressive ->
                    val allStreams = progressive.streams
                        .filter { stream ->
                            val u = stream.url?.trim().orEmpty()
                            u.isNotBlank() && !u.startsWith("magnet:", ignoreCase = true)
                        }
                    val existingVod = _uiState.value.streams.filter { it.addonId == "iptv_xtream_vod" }
                    val mergedStreams = (allStreams + existingVod)
                        .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                    lastMergedStreams = mergedStreams

                    val errorMessage = if (progressive.isFinal && mergedStreams.isEmpty()) {
                        if (streamingAddonCount == 0) {
                            "No streaming addons configured.\n\nGo to Settings \u2192 Addons to add a streaming addon, then come back and try again."
                        } else {
                            "No streams found for this content. The addons may not have sources for this title."
                        }
                    } else null

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoadingStreams = !progressive.isFinal && mergedStreams.isEmpty(),
                        streams = mergedStreams,
                        subtitles = progressive.subtitles,
                        error = errorMessage,
                        isSetupError = progressive.isFinal && mergedStreams.isEmpty() && streamingAddonCount == 0
                    )

                    // Cache hit path: first emission is already final (prefetch completed)
                    // → pick immediately, no waiting.
                    val cacheHit = isFirstEmission && progressive.isFinal && mergedStreams.isNotEmpty()
                    isFirstEmission = false

                    val elapsedMs = System.currentTimeMillis() - collectionStartMs
                    val shouldSelectNow = !autoplaySelected && mergedStreams.isNotEmpty() &&
                        (cacheHit || progressive.isFinal || elapsedMs >= AUTOPLAY_COLLECTION_WINDOW_MS)

                    if (shouldSelectNow) {
                        autoplaySelected = true
                        autoplaySelectBest(mergedStreams, preferredLanguage)
                    }
                }

                if (!autoplaySelected && lastMergedStreams.isNotEmpty()) {
                    autoplaySelected = true
                    autoplaySelectBest(lastMergedStreams, preferredLanguage)
                }

                // Apply subtitle preference in background (non-blocking)
                subtitleRefreshJob?.cancel()
                subtitleRefreshJob = launch {
                    val fetchedSubs = runCatching {
                        streamRepository.fetchSubtitlesForSelectedStream(
                            mediaType = mediaType,
                            imdbId = imdbId,
                            season = seasonNumber,
                            episode = episodeNumber,
                            stream = null
                        )
                    }.getOrDefault(emptyList())

                    val mergedSubs = (_uiState.value.subtitles + fetchedSubs)
                        .filter { it.url.isNotBlank() }
                        .distinctBy { "${it.id}|${it.url}" }

                    val preferredSub = getDefaultSubtitle()
                    _uiState.value = _uiState.value.copy(
                        subtitles = mergedSubs,
                        isLoadingSubtitles = false
                    )
                    applyPreferredSubtitle(preferredSub, mergedSubs, currentOriginalLanguage)
                }

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    error = e.message
                )
            }
        }
    }

    /**
     * Fetch media metadata in background (non-blocking)
     */
    private suspend fun fetchMediaMetadata(mediaType: MediaType, mediaId: Int) {
        try {
            val details = if (mediaType == MediaType.TV) {
                tmdbApi.getTvDetails(mediaId, Constants.TMDB_API_KEY)
            } else {
                tmdbApi.getMovieDetails(mediaId, Constants.TMDB_API_KEY)
            }

            val logoUrl = try {
                mediaRepository.getLogoUrl(mediaType, mediaId)
            } catch (e: Exception) { null }

            val title: String
            val backdropUrl: String?
            val posterUrl: String?

            if (mediaType == MediaType.TV) {
                val tvDetails = details as com.arflix.tv.data.api.TmdbTvDetails
                title = tvDetails.name
                backdropUrl = tvDetails.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                posterUrl = tvDetails.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                currentOriginalLanguage = tvDetails.originalLanguage ?: currentOriginalLanguage

                // Keep episode title aligned with saved progress rows for TV playback sessions.
                val season = currentSeason
                val episode = currentEpisode
                if (season != null && episode != null) {
                    currentEpisodeTitle = runCatching {
                        val seasonDetails = tmdbApi.getTvSeason(mediaId, season, Constants.TMDB_API_KEY)
                        seasonDetails.episodes.firstOrNull { it.episodeNumber == episode }?.name
                    }.getOrNull()
                }
            } else {
                val movieDetails = details as com.arflix.tv.data.api.TmdbMovieDetails
                title = movieDetails.title
                backdropUrl = movieDetails.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                posterUrl = movieDetails.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                currentOriginalLanguage = movieDetails.originalLanguage ?: currentOriginalLanguage
            }

            // Store info for watch history
            currentTitle = title
            currentPoster = posterUrl
            currentBackdrop = backdropUrl

            // Update UI with metadata
            _uiState.value = _uiState.value.copy(
                title = title,
                backdropUrl = backdropUrl,
                logoUrl = logoUrl,
                preferredAudioLanguage = resolvePreferredAudioLanguage()
            )
        } catch (e: Exception) {
            // Failed to fetch metadata
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

    private fun fetchSkipIntervals(imdbId: String, season: Int, episode: Int) {
        val requestKey = "$imdbId:$season:$episode"
        activeSkipRequestKey = requestKey
        skipIntervalsJob?.cancel()
        skipIntervalsJob = viewModelScope.launch {
            val intervals = skipIntroRepository.getSkipIntervals(imdbId, season, episode)
            if (activeSkipRequestKey != requestKey) return@launch
            skipIntervals = intervals
            // Force a recompute on the next position tick.
            lastActiveSkipType = null
            _uiState.value = _uiState.value.copy(activeSkipInterval = null, skipIntervalDismissed = false)
        }
    }

    fun onPlaybackPosition(positionMs: Long) {
        updateActiveSkipInterval(positionMs)
    }

    fun dismissSkipInterval() {
        _uiState.value = _uiState.value.copy(skipIntervalDismissed = true)
    }

    private fun updateActiveSkipInterval(positionMs: Long) {
        if (skipIntervals.isEmpty()) {
            if (_uiState.value.activeSkipInterval != null) {
                _uiState.value = _uiState.value.copy(activeSkipInterval = null, skipIntervalDismissed = false)
            }
            return
        }

        val active = skipIntervals.firstOrNull { interval ->
            val effectiveEnd = (interval.endMs - SKIP_INTERVAL_END_GUARD_MS)
                .coerceAtLeast(interval.startMs + SKIP_INTERVAL_MIN_VISIBLE_MS)
            val startsSoonOrStarted = positionMs >= (interval.startMs - SKIP_INTERVAL_SHOW_EARLY_MS)
            startsSoonOrStarted && positionMs < effectiveEnd
        }
        val currentActive = _uiState.value.activeSkipInterval

        if (active != null) {
            val key = "${active.type}:${active.startMs}:${active.endMs}:${active.provider}"
            if (currentActive == null || key != lastActiveSkipType) {
                lastActiveSkipType = key
                _uiState.value = _uiState.value.copy(activeSkipInterval = active, skipIntervalDismissed = false)
            }
        } else if (currentActive != null) {
            lastActiveSkipType = null
            _uiState.value = _uiState.value.copy(activeSkipInterval = null, skipIntervalDismissed = false)
        }
    }

    private suspend fun getDefaultSubtitle(): String {
        return try {
            val prefs = context.settingsDataStore.data.first()
            val raw = prefs[defaultSubtitleKey()]?.trim().orEmpty()
            if (isSubtitleDisabledPreference(raw)) "Off" else raw
        } catch (_: Exception) {
            "Off"
        }
    }

    private suspend fun resolveFrameRateMatchingMode(): String {
        return try {
            val prefs = context.settingsDataStore.data.first()
            when (prefs[frameRateMatchingModeKey()]?.trim()?.lowercase()) {
                "off" -> "Off"
                "seamless", "seamless only", "only if seamless", "only_if_seamless" -> "Seamless only"
                "always" -> "Always"
                else -> "Off"
            }
        } catch (_: Exception) {
            "Off"
        }
    }

    private fun applyPreferredSubtitle(preference: String, subtitles: List<Subtitle>, fallbackLanguage: String?) {
        if (isSubtitleDisabledPreference(preference)) {
            _uiState.value = _uiState.value.copy(selectedSubtitle = null)
            return
        }

        // Normalize language codes for matching
        val normalizedPref = normalizeLanguage(preference)
        val normalizedFallback = fallbackLanguage
            ?.let { normalizeLanguage(it) }
            ?.takeIf { it.isNotBlank() && it != normalizedPref }

        fun subtitleTokens(sub: Subtitle): Set<String> {
            val rawTokens = Regex("[A-Za-z-]+").findAll("${sub.lang} ${sub.label}")
                .map { it.value }
                .toList()
            val normalized = rawTokens.map { normalizeLanguage(it) }.filter { it.isNotBlank() }
            return buildSet {
                add(normalizeLanguage(sub.lang))
                add(normalizeLanguage(sub.label))
                addAll(normalized)
            }.filter { it.isNotBlank() }.toSet()
        }

        fun findMatch(target: String): Subtitle? {
            // Prefer embedded subtitles over addon subtitles when both match
            val embeddedMatch = subtitles.firstOrNull { sub ->
                sub.isEmbedded && subtitleTokens(sub).contains(target)
            } ?: subtitles.firstOrNull { sub ->
                sub.isEmbedded && (sub.label.lowercase().contains(target) || sub.lang.lowercase().contains(target))
            }
            if (embeddedMatch != null) return embeddedMatch

            return subtitles.firstOrNull { sub ->
                subtitleTokens(sub).contains(target)
            } ?: subtitles.firstOrNull { sub ->
                sub.label.lowercase().contains(target) || sub.lang.lowercase().contains(target)
            }
        }

        // Prioritize: embedded match > exact normalized match > fallback language > English fallback.
        val match = findMatch(normalizedPref)
            ?: normalizedFallback?.let { findMatch(it) }
            ?: if (normalizedPref != "en") findMatch("en") else null

        if (match != null) {
            _uiState.value = _uiState.value.copy(selectedSubtitle = match)
        }
    }

    private fun isSubtitleDisabledPreference(value: String?): Boolean {
        val normalized = value?.trim()?.lowercase().orEmpty()
        return normalized.isBlank() ||
            normalized == "off" ||
            normalized == "none" ||
            normalized == "no subtitles" ||
            normalized == "disabled" ||
            normalized == "disable"
    }

    // qualityScore is no longer needed - use getQualityScore from StreamQualityUtils instead.
    // Kept for reference but can be deleted if unused elsewhere

    /**
     * Prioritize streams for real-world TV playback stability and startup speed.
     * This reduces auto-picking very heavy remux/DV streams that often fail or buffer.
     */
    private fun playbackPriorityScore(stream: StreamSource): Int {
        val text = buildString {
            append(stream.source)
            append(' ')
            append(stream.addonName)
            stream.behaviorHints?.filename?.let {
                append(' ')
                append(it)
            }
        }.lowercase()

        var score = getQualityScore(stream.extractQuality()) * 100

        val sizeBytes = parseSize(stream.size)
        score += when {
            sizeBytes <= 0L -> 30
            sizeBytes <= 8L * 1024 * 1024 * 1024 -> 80
            sizeBytes <= 15L * 1024 * 1024 * 1024 -> 55
            sizeBytes <= 25L * 1024 * 1024 * 1024 -> 20
            sizeBytes <= 40L * 1024 * 1024 * 1024 -> -10
            else -> -90
        }

        if (text.contains("remux")) score -= 120
        if (text.contains("dolby vision") || text.contains(" dovi") || text.contains(" dv ")) score -= 120
        if (text.contains("cam") || text.contains("hdcam") || text.contains("telesync")) score -= 300
        if (text.contains("web-dl") || text.contains("webrip")) score += 45
        if (text.contains("x264") || text.contains("h264")) score += 15
        if (stream.behaviorHints?.cached == true || text.contains(" rd+")) score += 220
        if (stream.behaviorHints?.notWebReady == true) score -= 260
        if (!stream.url.isNullOrBlank() && stream.url.startsWith("http", ignoreCase = true)) score += 80
        if (stream.url?.startsWith("magnet:", ignoreCase = true) == true) score -= 500
        score += streamRepository.getAddonHealthBias(stream.addonId)

        return score
    }

    fun onPlaybackStarted(startupMs: Long, startupRetries: Int, autoFailovers: Int) {
        val addonId = _uiState.value.selectedStream?.addonId?.trim().orEmpty()
        if (addonId.isNotBlank()) {
            streamRepository.noteAddonPlaybackStarted(addonId, startupMs)
        }
        viewModelScope.launch {
            playbackTelemetryRepository.recordStartup(
                startupMs = startupMs,
                retries = startupRetries,
                failoversBeforeStart = autoFailovers
            )
        }
    }

    fun onSelectedStreamPlaybackFailure() {
        val addonId = _uiState.value.selectedStream?.addonId?.trim().orEmpty()
        if (addonId.isNotBlank()) {
            streamRepository.noteAddonPlaybackFailure(addonId)
        }
        viewModelScope.launch {
            playbackTelemetryRepository.recordPlaybackFailure()
        }
    }

    fun onFailoverAttempt(success: Boolean) {
        viewModelScope.launch {
            playbackTelemetryRepository.recordFailoverAttempt(success)
        }
    }

    fun onLongRebufferDetected() {
        viewModelScope.launch {
            playbackTelemetryRepository.recordLongRebuffer()
        }
    }

    private suspend fun resolvePreferredAudioLanguage(): String {
        val setting = runCatching {
            context.settingsDataStore.data.first()[defaultAudioLanguageKey()]
        }.getOrNull().orEmpty().trim()

        if (setting.isNotBlank() && !setting.equals("Auto", ignoreCase = true) && !setting.equals("Auto (Original)", ignoreCase = true)) {
            val fromSetting = normalizeLanguage(setting)
            if (fromSetting in knownLanguageCodes) {
                return fromSetting
            }
        }

        val fromOriginal = currentOriginalLanguage
            ?.let { normalizeLanguage(it) }
            ?.takeIf { it.isNotBlank() }
            ?: "en"
        return if (fromOriginal in knownLanguageCodes) fromOriginal else "en"
    }

    private fun streamLanguageScore(stream: StreamSource, preferredLanguage: String): Int {
        val preferred = normalizeLanguage(preferredLanguage).ifBlank { "en" }
        val combined = buildString {
            append(stream.source)
            append(' ')
            append(stream.addonName)
            stream.behaviorHints?.filename?.let {
                append(' ')
                append(it)
            }
        }
        val codes = extractLanguageCodes(combined)
        val hasMulti = hasMultiLanguageHint(combined)
        return when {
            codes.contains(preferred) -> 2
            codes.isEmpty() || hasMulti -> 1
            else -> 0
        }
    }

    private fun autoplaySelectBest(streams: List<StreamSource>, preferredLanguage: String) {
        val preferredFromBingeGroup = currentPreferredBingeGroup?.let { preferredGroup ->
            streams.firstOrNull { stream ->
                stream.behaviorHints?.bingeGroup == preferredGroup &&
                    (currentPreferredAddonId?.let { stream.addonId == it } ?: true)
            } ?: streams.firstOrNull { stream ->
                stream.behaviorHints?.bingeGroup == preferredGroup
            }
        }

        val preferredFromNavigation = streams.firstOrNull { s ->
            val addonMatch = currentPreferredAddonId?.let { s.addonId == it } ?: true
            val sourceMatch = currentPreferredSourceName?.let { s.source == it } ?: true
            addonMatch && sourceMatch
        } ?: streams.firstOrNull { s ->
            currentPreferredAddonId?.let { s.addonId == it } ?: false
        }

        val stabilitySelected = pickPreferredStream(streams, preferredLanguage)
        val selected = preferredFromBingeGroup ?: preferredFromNavigation ?: stabilitySelected ?: streams.first()
        selectStream(selected)
    }

    private fun pickPreferredStream(
        streams: List<StreamSource>,
        preferredLanguage: String
    ): StreamSource? {
        if (streams.isEmpty()) return null

        val maxSizeBytes = 20L * 1024 * 1024 * 1024 // 20GB - anything larger is likely a season pack

        // Step 1: Filter out season packs (>20GB) when possible.
        val candidates = streams.filter {
            val size = parseSize(it.size)
            size == 0L || size < maxSizeBytes // 0 = unknown size, keep those
        }
        val pool = if (candidates.isNotEmpty()) candidates else streams

        // Step 2: Score by language affinity and playback stability.
        return pool.maxByOrNull { stream ->
            val langScore = streamLanguageScore(stream, preferredLanguage)
            val stabilityScore = playbackPriorityScore(stream)
            (langScore * 10_000) + stabilityScore
        }
    }

    // Robust size string parser - identical to StreamSelector's parseSizeString()
    // Handles comma decimals ("5,2 GB"), GiB notation, extra spaces, etc.
    private fun parseSize(sizeStr: String): Long {
        if (sizeStr.isBlank()) return 0L

        // Normalize: uppercase, replace comma with dot, remove extra spaces
        val normalized = sizeStr.uppercase()
            .replace(",", ".")
            .replace(Regex("\\s+"), " ")
            .trim()

        // Pattern 1: "15.2 GB", "6GB", "1.5 TB" etc.
        val pattern1 = Regex("""(\d+(?:\.\d+)?)\s*(TB|GB|MB|KB)""")
        pattern1.find(normalized)?.let { match ->
            val number = match.groupValues[1].toDoubleOrNull() ?: return@let
            val unit = match.groupValues[2]
            return calcBytes(number, unit)
        }

        // Pattern 2: Numbers with GiB/MiB notation
        val pattern2 = Regex("""(\d+(?:\.\d+)?)\s*(TIB|GIB|MIB|KIB)""")
        pattern2.find(normalized)?.let { match ->
            val number = match.groupValues[1].toDoubleOrNull() ?: return@let
            val unit = match.groupValues[2].replace("IB", "B")
            return calcBytes(number, unit)
        }

        // Pattern 3: Just a number (assume bytes)
        val pattern3 = Regex("""^(\d+(?:\.\d+)?)$""")
        pattern3.find(normalized)?.let { match ->
            return match.groupValues[1].toLongOrNull() ?: 0L
        }

        return 0L
    }

    private fun calcBytes(number: Double, unit: String): Long {
        return when (unit) {
            "TB" -> (number * 1024.0 * 1024.0 * 1024.0 * 1024.0).toLong()
            "GB" -> (number * 1024.0 * 1024.0 * 1024.0).toLong()
            "MB" -> (number * 1024.0 * 1024.0).toLong()
            "KB" -> (number * 1024.0).toLong()
            else -> number.toLong()
        }
    }

    private fun extractLanguageCodes(text: String): Set<String> {
        if (text.isBlank()) return emptySet()
        val tokens = Regex("[A-Za-z]+").findAll(text).map { it.value }.toList()
        if (tokens.isEmpty()) return emptySet()

        val codes = mutableSetOf<String>()
        for (token in tokens) {
            if (token.length < 2) continue
            val normalized = normalizeLanguage(token)
            if (normalized in knownLanguageCodes) {
                codes.add(normalized)
                continue
            }
            if (token.length >= 3) {
                val prefix3 = normalizeLanguage(token.take(3))
                if (prefix3 in knownLanguageCodes) {
                    codes.add(prefix3)
                    continue
                }
            }
            val prefix2 = normalizeLanguage(token.take(2))
            if (prefix2 in knownLanguageCodes) {
                codes.add(prefix2)
            }
        }
        return codes
    }

    private fun hasMultiLanguageHint(text: String): Boolean {
        val lower = text.lowercase()
        return lower.contains("multi") ||
            lower.contains("dual audio") ||
            lower.contains("dual-audio") ||
            lower.contains("multi audio") ||
            lower.contains("multi-audio")
    }

    /**
     * Normalize language codes to a standard format for matching
     * Maps: "English" -> "en", "eng" -> "en", "Spanish" -> "es", etc.
     */
    private fun normalizeLanguage(lang: String): String {
        val lowerLang = lang.lowercase().trim()
        return when {
            // Full names
            lowerLang == "english" || lowerLang.startsWith("english") -> "en"
            lowerLang == "spanish" || lowerLang.startsWith("spanish") || lowerLang == "espanol" -> "es"
            lowerLang == "french" || lowerLang.startsWith("french") || lowerLang == "francais" -> "fr"
            lowerLang == "german" || lowerLang.startsWith("german") || lowerLang == "deutsch" -> "de"
            lowerLang == "italian" || lowerLang.startsWith("italian") -> "it"
            lowerLang == "portuguese" -> "pt"
            lowerLang == "portuguese (brazil)" ||
                lowerLang == "portuguese-brazil" ||
                lowerLang == "brazilian portuguese" ||
                lowerLang == "brazil portuguese" ||
                lowerLang == "pt-br" ||
                lowerLang == "ptbr" -> "pt-br"
            lowerLang.startsWith("portuguese") -> "pt"
            lowerLang == "dutch" || lowerLang.startsWith("dutch") -> "nl"
            lowerLang == "russian" || lowerLang.startsWith("russian") -> "ru"
            lowerLang == "chinese" || lowerLang.startsWith("chinese") -> "zh"
            lowerLang == "japanese" || lowerLang.startsWith("japanese") || lowerLang == "jp" || lowerLang == "jap" -> "ja"
            lowerLang == "korean" || lowerLang.startsWith("korean") -> "ko"
            lowerLang == "arabic" || lowerLang.startsWith("arabic") -> "ar"
            lowerLang == "hindi" || lowerLang.startsWith("hindi") -> "hi"
            lowerLang == "turkish" || lowerLang.startsWith("turkish") -> "tr"
            lowerLang == "polish" || lowerLang.startsWith("polish") -> "pl"
            lowerLang == "swedish" || lowerLang.startsWith("swedish") -> "sv"
            lowerLang == "norwegian" || lowerLang.startsWith("norwegian") -> "no"
            lowerLang == "danish" || lowerLang.startsWith("danish") -> "da"
            lowerLang == "finnish" || lowerLang.startsWith("finnish") -> "fi"
            lowerLang == "greek" || lowerLang.startsWith("greek") -> "el"
            lowerLang == "czech" || lowerLang.startsWith("czech") -> "cs"
            lowerLang == "hungarian" || lowerLang.startsWith("hungarian") -> "hu"
            lowerLang == "romanian" || lowerLang.startsWith("romanian") -> "ro"
            lowerLang == "thai" || lowerLang.startsWith("thai") -> "th"
            lowerLang == "vietnamese" || lowerLang.startsWith("vietnamese") -> "vi"
            lowerLang == "indonesian" || lowerLang.startsWith("indonesian") -> "id"
            lowerLang == "hebrew" || lowerLang.startsWith("hebrew") -> "he"
            lowerLang == "persian" || lowerLang.startsWith("persian") || lowerLang == "farsi" -> "fa"
            lowerLang == "ukrainian" || lowerLang.startsWith("ukrainian") -> "uk"
            lowerLang == "bengali" || lowerLang.startsWith("bengali") -> "bn"
            lowerLang == "bulgarian" || lowerLang.startsWith("bulgarian") -> "bg"
            lowerLang == "croatian" || lowerLang.startsWith("croatian") -> "hr"
            lowerLang == "serbian" || lowerLang.startsWith("serbian") -> "sr"
            lowerLang == "slovak" || lowerLang.startsWith("slovak") -> "sk"
            lowerLang == "slovenian" || lowerLang.startsWith("slovenian") -> "sl"
            lowerLang == "lithuanian" || lowerLang.startsWith("lithuanian") -> "lt"
            lowerLang == "estonian" || lowerLang.startsWith("estonian") -> "et"
            // ISO 639-1 codes (2 letter)
            lowerLang.length == 2 -> lowerLang
            // ISO 639-2 codes (3 letter)
            lowerLang == "eng" -> "en"
            lowerLang == "spa" -> "es"
            lowerLang == "fra" || lowerLang == "fre" -> "fr"
            lowerLang == "deu" || lowerLang == "ger" -> "de"
            lowerLang == "ita" -> "it"
            lowerLang == "por" -> "pt"
            lowerLang == "pob" || lowerLang == "pobr" -> "pt-br"
            lowerLang == "nld" || lowerLang == "dut" -> "nl"
            lowerLang == "rus" -> "ru"
            lowerLang == "zho" || lowerLang == "chi" -> "zh"
            lowerLang == "jpn" -> "ja"
            lowerLang == "kor" -> "ko"
            lowerLang == "ara" -> "ar"
            lowerLang == "hin" -> "hi"
            lowerLang == "tur" -> "tr"
            lowerLang == "pol" -> "pl"
            lowerLang == "swe" -> "sv"
            lowerLang == "nor" -> "no"
            lowerLang == "dan" -> "da"
            lowerLang == "fin" -> "fi"
            lowerLang == "ell" || lowerLang == "gre" -> "el"
            lowerLang == "ces" || lowerLang == "cze" -> "cs"
            lowerLang == "hun" -> "hu"
            lowerLang == "ron" || lowerLang == "rum" -> "ro"
            lowerLang == "tha" -> "th"
            lowerLang == "vie" -> "vi"
            lowerLang == "ind" -> "id"
            lowerLang == "heb" -> "he"
            lowerLang == "fas" || lowerLang == "per" -> "fa"
            lowerLang == "ukr" -> "uk"
            lowerLang == "ben" -> "bn"
            lowerLang == "bul" -> "bg"
            lowerLang == "hrv" -> "hr"
            lowerLang == "srp" -> "sr"
            lowerLang == "slk" || lowerLang == "slo" -> "sk"
            lowerLang == "slv" -> "sl"
            lowerLang == "lit" -> "lt"
            lowerLang == "est" -> "et"
            else -> lowerLang
        }
    }

    // Track current stream index for auto-retry
    private var currentStreamIndex = 0
    private data class ReachableStreamSelection(
        val original: StreamSource,
        val resolved: StreamSource
    )

    /**
     * Select a stream for playback
     */
    fun selectStream(stream: StreamSource) {
        viewModelScope.launch {
            var selectedOriginal = stream
            var resolvedStream = runCatching {
                streamRepository.resolveStreamForPlayback(stream)
            }.getOrNull() ?: stream
            var url = resolvedStream.url
            if (url.isNullOrBlank()) {
                val isP2p = !stream.infoHash.isNullOrBlank() ||
                    (stream.url?.trim()?.startsWith("magnet:", ignoreCase = true) == true)
                _uiState.value = _uiState.value.copy(
                    error = if (isP2p) {
                        "P2P stream requires TorrServer. Install TorrServer and set its URL in Settings > Addons."
                    } else {
                        "Failed to resolve stream. Try another source."
                    }
                )
                return@launch
            }

            // Start playback immediately with the resolved URL.
            // Probing alternate HTTP candidates before first play adds large latency and
            // is now avoided; player failover handles truly bad sources after startup.

            // Find the index of this stream
            val streams = _uiState.value.streams
            val streamIndex = streams.indexOf(selectedOriginal)
            if (streamIndex >= 0) {
                currentStreamIndex = streamIndex
            }

            // Merge stream's embedded subtitles with existing subtitles
            val streamSubs = stream.subtitles
            if (streamSubs.isNotEmpty()) {
                val existingSubs = _uiState.value.subtitles
                val newSubs = streamSubs.filter { newSub ->
                    existingSubs.none { it.id == newSub.id || (it.url == newSub.url && newSub.url.isNotBlank()) }
                }
                if (newSubs.isNotEmpty()) {
                    _uiState.value = _uiState.value.copy(subtitles = existingSubs + newSubs)
                }
            }

            // Direct URL - use immediately (ExoPlayer handles redirects)
            _uiState.value = _uiState.value.copy(
                selectedStream = resolvedStream,
                selectedStreamUrl = url,
                streamSelectionNonce = _uiState.value.streamSelectionNonce + 1
            )

            // Refresh subtitles with stream-specific hints (videoHash/videoSize) for better matching,
            // especially for OpenSubtitles.
            val imdb = currentImdbId
            if (!imdb.isNullOrBlank()) {
                subtitleRefreshJob?.cancel()
                subtitleRefreshJob = viewModelScope.launch {
                    val hintSubs = runCatching {
                        streamRepository.fetchSubtitlesForSelectedStream(
                            mediaType = currentMediaType,
                            imdbId = imdb,
                            season = currentSeason,
                            episode = currentEpisode,
                            stream = resolvedStream
                        )
                    }.getOrDefault(emptyList())

                    val extraSubs = if (hintSubs.isNotEmpty()) {
                        hintSubs
                    } else {
                        // Fallback for providers that do better without stream hints.
                        runCatching {
                            streamRepository.fetchSubtitlesForSelectedStream(
                                mediaType = currentMediaType,
                                imdbId = imdb,
                                season = currentSeason,
                                episode = currentEpisode,
                                stream = null
                            )
                        }.getOrDefault(emptyList())
                    }

                    val existing = _uiState.value.subtitles
                    val merged = existing + extraSubs.filter { newSub ->
                        newSub.url.isNotBlank() && existing.none { it.id == newSub.id || it.url == newSub.url }
                    }
                    _uiState.value = _uiState.value.copy(subtitles = merged)

                    // If nothing is selected yet, try to apply the default preference against the merged list.
                    if (_uiState.value.selectedSubtitle == null) {
                        val preferred = getDefaultSubtitle()
                        applyPreferredSubtitle(preferred, merged, currentOriginalLanguage)
                    }
                }
            }
        }
    }

    private suspend fun findFirstReachableStreamInAddon(
        selected: StreamSource,
        maxAttempts: Int = 8
    ): ReachableStreamSelection? {
        val streams = _uiState.value.streams
        if (streams.isEmpty()) return null

        val selectedIndex = streams.indexOf(selected).takeIf { it >= 0 } ?: 0
        val candidateIndexes = (0 until streams.size)
            .map { offset -> (selectedIndex + offset) % streams.size }

        val candidates = candidateIndexes
            .map { idx -> streams[idx] }
            .filter { candidate ->
                candidate.addonId == selected.addonId &&
                    !candidate.url.isNullOrBlank()
            }
            .take(maxAttempts)

        for (candidate in candidates) {
            val resolved = runCatching {
                streamRepository.resolveStreamForPlayback(candidate)
            }.getOrNull() ?: candidate

            val candidateUrl = resolved.url?.trim().orEmpty()
            if (candidateUrl.isBlank()) continue
            if (!(candidateUrl.startsWith("http://", true) || candidateUrl.startsWith("https://", true))) {
                return ReachableStreamSelection(original = candidate, resolved = resolved)
            }

            val reachable = runCatching {
                streamRepository.isHttpStreamReachable(resolved)
            }.getOrDefault(false)
            if (reachable) {
                return ReachableStreamSelection(original = candidate, resolved = resolved)
            }
        }
        return null
    }

    /**
     * Returns true if the URL points to a known debrid CDN domain.
     * These URLs are freshly resolved and time-limited, so reachability checks
     * are wasteful and can consume single-use download tokens.
     */
    private fun isKnownDebridCdnUrl(url: String): Boolean {
        val host = runCatching { java.net.URI(url).host?.lowercase() }.getOrNull() ?: return false
        return DEBRID_CDN_DOMAINS.any { domain -> host == domain || host.endsWith(".$domain") }
    }

    fun reportPlaybackError(message: String) {
        _uiState.value = _uiState.value.copy(error = message)
    }

    fun updatePlayerTextTracks(playerTextTracks: List<Subtitle>) {
        val current = _uiState.value.subtitles
        val trackBackedIds = playerTextTracks.map { it.id }.toSet()

        // Keep external subtitle entries that haven't been mapped to concrete track indices yet.
        val unresolvedExternal = current.filter { subtitle ->
            !subtitle.isEmbedded && subtitle.url.isNotBlank() && subtitle.id !in trackBackedIds
        }

        // Embedded subtitles first, then external/addon subtitles
        val merged = (playerTextTracks + unresolvedExternal)
            .distinctBy { subtitle ->
                val normalizedId = subtitle.id.trim()
                if (normalizedId.isNotBlank()) normalizedId
                else "${subtitle.lang}|${subtitle.label}|${subtitle.url}"
            }

        val selected = _uiState.value.selectedSubtitle
        val resolvedSelected = if (selected != null) {
            merged.firstOrNull { it.id == selected.id }
                ?: merged.firstOrNull {
                    selected.url.isNotBlank() && it.url == selected.url
                }
                ?: selected
        } else {
            null
        }

        _uiState.value = _uiState.value.copy(
            subtitles = merged,
            selectedSubtitle = resolvedSelected
        )

        if (_uiState.value.selectedSubtitle == null) {
            viewModelScope.launch {
                val preferred = getDefaultSubtitle()
                applyPreferredSubtitle(preferred, merged, currentOriginalLanguage)
            }
        }
    }

    fun selectSubtitle(subtitle: Subtitle) {
        _uiState.value = _uiState.value.copy(
            selectedSubtitle = subtitle,
            subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
        )
        recordSubtitleUsage(subtitle)
    }

    fun disableSubtitles() {
        _uiState.value = _uiState.value.copy(
            selectedSubtitle = null,
            subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
        )
    }

    private fun recordSubtitleUsage(subtitle: Subtitle) {
        viewModelScope.launch {
            val raw = subtitle.lang.ifBlank { subtitle.label }
            if (raw.isBlank()) return@launch
            val key = normalizeLanguage(raw)
            if (key.isBlank()) return@launch

            val prefs = context.settingsDataStore.data.first()
            val json = prefs[subtitleUsageKey()]
            val type = TypeToken.getParameterized(MutableMap::class.java, String::class.java, Int::class.javaObjectType).type
            val map: MutableMap<String, Int> = if (!json.isNullOrBlank()) {
                gson.fromJson(json, type)
            } else {
                mutableMapOf()
            }

            map[key] = (map[key] ?: 0) + 1
            context.settingsDataStore.edit { it[subtitleUsageKey()] = gson.toJson(map) }
        }
    }

    fun dismissError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun retry() {
        loadMedia(
            currentMediaType,
            currentMediaId,
            currentSeason,
            currentEpisode,
            currentImdbId,
            null,
            currentPreferredAddonId,
            currentPreferredSourceName,
            currentPreferredBingeGroup,
            currentStartPositionMs
        )
    }

    private data class ResumeData(
        val positionMs: Long,
        val streamKey: String? = null,
        val streamAddonId: String? = null,
        val streamTitle: String? = null
    )

    private suspend fun resolveResumeData(
        mediaType: MediaType,
        mediaId: Int,
        seasonNumber: Int?,
        episodeNumber: Int?,
        navigationStartPositionMs: Long?
    ): ResumeData {
        val navStart = navigationStartPositionMs?.coerceAtLeast(0L) ?: 0L

        val cloudEntry = watchHistoryRepository.getProgress(mediaType, mediaId, seasonNumber, episodeNumber)
        val cloudPositionMs = cloudEntry?.let { entry ->
            computeResumePositionMs(entry, mediaType, mediaId, seasonNumber, episodeNumber)
        } ?: 0L

        val localEntry = runCatching {
            traktRepository.getLocalContinueWatchingEntry(mediaType, mediaId, seasonNumber, episodeNumber)
        }.getOrNull()

        val localPositionMs = localEntry?.let { item ->
            when {
                item.resumePositionSeconds > 0L -> item.resumePositionSeconds * 1000L
                item.durationSeconds > 0L && item.progress in 1..99 -> ((item.durationSeconds * item.progress) / 100L).coerceAtLeast(1L) * 1000L
                else -> 0L
            }
        } ?: 0L

        val finalPositionMs = when {
            navStart > 0L -> navStart
            cloudPositionMs > 0L || localPositionMs > 0L -> maxOf(cloudPositionMs, localPositionMs)
            else -> 0L
        }

        val useLocalStream = localPositionMs >= cloudPositionMs && localPositionMs > 0L
        val streamKey = if (useLocalStream) {
            localEntry?.streamKey ?: cloudEntry?.stream_key
        } else {
            cloudEntry?.stream_key ?: localEntry?.streamKey
        }
        val streamAddonId = if (useLocalStream) {
            localEntry?.streamAddonId ?: cloudEntry?.stream_addon_id
        } else {
            cloudEntry?.stream_addon_id ?: localEntry?.streamAddonId
        }
        val streamTitle = if (useLocalStream) {
            localEntry?.streamTitle ?: cloudEntry?.stream_title
        } else {
            cloudEntry?.stream_title ?: localEntry?.streamTitle
        }

        // Guard against replaying a bad source from failed startup attempts at 00:00.
        // Only trust persisted stream affinity when we have meaningful progress.
        val hasMeaningfulProgress = finalPositionMs >= 30_000L

        return ResumeData(
            positionMs = finalPositionMs.coerceAtLeast(0L),
            streamKey = if (hasMeaningfulProgress) streamKey else null,
            streamAddonId = if (hasMeaningfulProgress) streamAddonId else null,
            streamTitle = if (hasMeaningfulProgress) streamTitle else null
        )
    }

    private fun buildStreamKey(stream: StreamSource?): String? {
        if (stream == null) return null

        val infoHash = stream.infoHash?.trim()?.lowercase().orEmpty()
        if (infoHash.isNotBlank()) {
            val idx = stream.fileIdx
            return if (idx != null) "$infoHash:$idx" else infoHash
        }

        val videoHash = stream.behaviorHints?.videoHash?.trim().orEmpty()
        if (videoHash.isNotBlank()) return "vh:$videoHash"

        val filename = stream.behaviorHints?.filename?.trim().orEmpty()
        if (filename.isNotBlank()) return "fn:${filename.lowercase()}"

        val source = stream.source.trim()
        if (source.isNotBlank()) return "src:${source.lowercase()}"

        val url = stream.url?.trim().orEmpty()
        if (url.isNotBlank()) return "url:${url.substringBefore('?').lowercase()}"

        return null
    }

    private suspend fun computeResumePositionMs(
        entry: WatchHistoryEntry,
        mediaType: MediaType,
        mediaId: Int,
        seasonNumber: Int?,
        episodeNumber: Int?
    ): Long {
        val normalizedPositionSeconds = normalizeStoredSeconds(entry.position_seconds)
        if (normalizedPositionSeconds > 0L) {
            return normalizedPositionSeconds * 1000L
        }

        val normalizedDurationSeconds = normalizeStoredSeconds(entry.duration_seconds)
        if (normalizedDurationSeconds > 0L && entry.progress > 0f) {
            return (normalizedDurationSeconds * entry.progress).toLong().coerceAtLeast(0L) * 1000L
        }

        if (entry.progress <= 0f) return 0L

        val runtimeSeconds = resolveRuntimeSeconds(mediaType, mediaId, seasonNumber, episodeNumber)
        if (runtimeSeconds > 0L) {
            return (runtimeSeconds * entry.progress).toLong().coerceAtLeast(0L) * 1000L
        }
        return 0L
    }

    private fun normalizeStoredSeconds(value: Long): Long {
        // Defensive conversion for rows that may have been stored as milliseconds.
        return if (value > 86_400L) value / 1000L else value
    }

    private suspend fun resolveRuntimeSeconds(
        mediaType: MediaType,
        mediaId: Int,
        seasonNumber: Int?,
        episodeNumber: Int?
    ): Long {
        return try {
            if (mediaType == MediaType.MOVIE) {
                val details = tmdbApi.getMovieDetails(mediaId, Constants.TMDB_API_KEY)
                (details.runtime ?: 0) * 60L
            } else {
                val details = tmdbApi.getTvDetails(mediaId, Constants.TMDB_API_KEY)
                val avgRuntime = details.episodeRunTime.firstOrNull() ?: 0
                if (avgRuntime > 0) {
                    avgRuntime * 60L
                } else {
                    val season = seasonNumber ?: return 0L
                    val episode = episodeNumber ?: return 0L
                    val seasonDetails = tmdbApi.getTvSeason(mediaId, season, Constants.TMDB_API_KEY)
                    val runtime = seasonDetails.episodes.firstOrNull { it.episodeNumber == episode }?.runtime
                        ?: seasonDetails.episodes.firstOrNull { it.runtime != null }?.runtime
                        ?: 0
                    runtime * 60L
                }
            }
        } catch (_: Exception) {
            0L
        }
    }

    fun saveProgress(position: Long, duration: Long, progressPercent: Int, isPlaying: Boolean, playbackState: Int) {
        if (duration <= 0) return

        // On pause/stop, always save (cancel any in-flight periodic save).
        // During playback, skip if a previous save is still running (debounce).
        if (!isPlaying || playbackState == Player.STATE_ENDED) {
            progressSaveJob?.cancel()
        } else if (progressSaveJob?.isActive == true) {
            return
        }
        progressSaveJob = viewModelScope.launch(Dispatchers.IO) {
            val currentTime = System.currentTimeMillis()
            val progressFraction = (progressPercent / 100f).coerceIn(0f, 1f)

            // Scrobble start/pause/updates with debounce
            if (isPlaying && !lastIsPlaying) {
                try {
                    traktRepository.scrobbleStart(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode
                    )
                } catch (e: Exception) {
                    // Scrobble start failed
                }
                lastScrobbleTime = currentTime
            } else if (!isPlaying && lastIsPlaying) {
                try {
                    traktRepository.scrobblePauseImmediate(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode
                    )
                } catch (e: Exception) {
                    // Scrobble pause immediate failed
                }
                lastScrobbleTime = currentTime
            } else if (isPlaying && currentTime - lastScrobbleTime >= SCROBBLE_UPDATE_INTERVAL_MS) {
                // Periodic scrobble update while playing (use scrobbleStart, not pause)
                try {
                    traktRepository.scrobbleStart(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode
                    )
                } catch (e: Exception) {
                    // Scrobble update failed
                }
                lastScrobbleTime = currentTime
            }

            // Save to Supabase watch history (debounced + on pause/stop)
            // Skip saving progress at watched threshold — the mark-watched block below handles
            // the next-episode CW entry, and saving the finished episode's full position here
            // would contaminate the next episode's resume time via show-level history fallback.
            val isAtWatchedThreshold = progressPercent >= Constants.WATCHED_THRESHOLD
            if ((!isPlaying || currentTime - lastWatchHistorySaveTime >= WATCH_HISTORY_UPDATE_INTERVAL_MS) && !isAtWatchedThreshold) {
                lastWatchHistorySaveTime = currentTime
                val durationSeconds = (duration / 1000L).coerceAtLeast(1L)
                val positionSeconds = (position / 1000L).coerceAtLeast(0L)
                val selectedStream = _uiState.value.selectedStream
                val shouldPersistStreamAffinity = positionSeconds >= 30L
                val streamKey = if (shouldPersistStreamAffinity) buildStreamKey(selectedStream) else null
                val streamAddonId = if (shouldPersistStreamAffinity) selectedStream?.addonId?.takeIf { it.isNotBlank() } else null
                val streamTitle = if (shouldPersistStreamAffinity) selectedStream?.source?.take(200)?.takeIf { it.isNotBlank() } else null
                watchHistoryRepository.saveProgress(
                    mediaType = currentMediaType,
                    tmdbId = currentMediaId,
                    title = currentTitle,
                    poster = currentPoster,
                    backdrop = currentBackdrop,
                    season = currentSeason,
                    episode = currentEpisode,
                    episodeTitle = currentEpisodeTitle,
                    progress = progressFraction,
                    duration = durationSeconds,
                    position = positionSeconds,
                    streamKey = streamKey,
                    streamAddonId = streamAddonId,
                    streamTitle = streamTitle
                )

                // Also save to local Continue Watching (profile-scoped, for profiles without Trakt).
                // Skip when at watched threshold — the watched-mark block below will handle
                // creating the next-episode CW entry instead, avoiding a stale position/duration
                // from the finished episode leaking into the next-episode's resume label.
                if (progressPercent < Constants.WATCHED_THRESHOLD) {
                    traktRepository.saveLocalContinueWatching(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        title = currentItemTitle.ifEmpty { currentTitle },
                        posterPath = currentPoster,
                        backdropPath = currentBackdrop,
                        season = currentSeason,
                        episode = currentEpisode,
                        episodeTitle = currentEpisodeTitle,
                        progress = progressPercent,
                        positionSeconds = positionSeconds,
                        durationSeconds = durationSeconds,
                        streamKey = streamKey,
                        streamAddonId = streamAddonId,
                        streamTitle = streamTitle
                    )
                }

                if (!isPlaying || playbackState == Player.STATE_ENDED || progressPercent >= Constants.WATCHED_THRESHOLD) {
                    runCatching { cloudSyncRepository.pushToCloud() }
                    runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
                }
            }

            // Mark as watched when playback ends or crosses threshold
            if (!hasMarkedWatched && (playbackState == Player.STATE_ENDED || progressPercent >= Constants.WATCHED_THRESHOLD)) {
                hasMarkedWatched = true
                try {
                    traktRepository.scrobbleStop(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode
                    )
                } catch (e: Exception) {
                    // Scrobble stop failed
                }
                try {
                    val safeSeason = currentSeason
                    val safeEpisode = currentEpisode
                    if (currentMediaType == MediaType.TV && safeSeason != null && safeEpisode != null) {
                        traktRepository.deletePlaybackForEpisode(currentMediaId, safeSeason, safeEpisode)
                    } else if (currentMediaType == MediaType.MOVIE) {
                        traktRepository.deletePlaybackForContent(currentMediaId, currentMediaType)
                    }
                    // Clean up Supabase history for the finished episode so its stale
                    // position doesn't resurface as a Continue Watching candidate.
                    // Retry up to 2 times if the delete fails (network flakes).
                    val deleteType = currentMediaType
                    val deleteId = currentMediaId
                    val delS = safeSeason
                    val delE = safeEpisode
                    for (attempt in 1..2) {
                        try {
                            if (deleteType == MediaType.TV && delS != null && delE != null) {
                                watchHistoryRepository.removeFromHistory(deleteId, delS, delE)
                            } else if (deleteType == MediaType.MOVIE) {
                                watchHistoryRepository.removeFromHistory(deleteId, null, null)
                            }
                            break // Success
                        } catch (_: Exception) {
                            if (attempt < 2) kotlinx.coroutines.delay(500)
                        }
                    }
                } catch (e: Exception) {
                    // Delete playback failed
                }

                // Remove the finished episode from CW cache so it doesn't resurface
                // before the next refresh cycle picks up the server-side changes.
                runCatching {
                    traktRepository.removeFromContinueWatchingCache(
                        currentMediaId, currentSeason, currentEpisode
                    )
                }

                // When a TV episode completes, immediately save the next episode to
                // local Continue Watching so CW isn't empty between episodes.
                val cwSeason = currentSeason
                val cwEpisode = currentEpisode
                if (currentMediaType == MediaType.TV && cwSeason != null && cwEpisode != null) {
                    try {
                        val nextEpisode = cwEpisode + 1
                        traktRepository.saveLocalContinueWatching(
                            mediaType = currentMediaType,
                            tmdbId = currentMediaId,
                            title = currentItemTitle.ifEmpty { currentTitle },
                            posterPath = currentPoster,
                            backdropPath = currentBackdrop,
                            season = cwSeason,
                            episode = nextEpisode,
                            episodeTitle = null,
                            progress = 3, // meets MIN_PROGRESS_THRESHOLD to avoid filter
                            positionSeconds = 0L, // next episode: no resume position yet
                            durationSeconds = 0L  // next episode: unknown duration
                        )
                    } catch (_: Exception) {
                        // Best-effort: don't let CW save failure affect playback
                    }
                }

                runCatching { cloudSyncRepository.pushToCloud() }
                runCatching { launcherContinueWatchingRepository.refreshForCurrentProfile() }
            }

            lastIsPlaying = isPlaying
        }.also { job ->
            job.invokeOnCompletion { progressSaveJob = null }
        }
    }

    private var progressSaveJob: Job? = null
    private var subtitleRefreshJob: Job? = null
    private var vodAppendJob: Job? = null

    private suspend fun appendVodSourceInBackground(
        mediaType: MediaType,
        imdbId: String,
        seasonNumber: Int?,
        episodeNumber: Int?,
        timeoutMs: Long
    ) {
        val currentStreams = _uiState.value.streams
        if (currentStreams.any { it.addonId == "iptv_xtream_vod" }) return
        val lookupTitle = currentItemTitle
            .ifBlank { currentTitle }
            .ifBlank { mediaRepository.getCachedItem(mediaType, currentMediaId)?.title.orEmpty() }

        val vod = if (mediaType == MediaType.MOVIE) {
            streamRepository.resolveMovieVodOnly(
                imdbId = imdbId,
                title = lookupTitle,
                year = null,
                tmdbId = currentMediaId,
                timeoutMs = timeoutMs
            )
        } else {
            streamRepository.resolveEpisodeVodOnly(
                imdbId = imdbId,
                season = seasonNumber ?: 1,
                episode = episodeNumber ?: 1,
                title = lookupTitle,
                tmdbId = currentMediaId,
                timeoutMs = timeoutMs
            )
        } ?: return

        if (vod.url.isNullOrBlank()) return
        val latest = _uiState.value.streams
        if (latest.any { it.url == vod.url && it.source == vod.source }) return

        val updated = latest + vod
        _uiState.value = _uiState.value.copy(
            streams = updated,
            isLoadingStreams = false
        )
    }

    private suspend fun populateStreamsForProvidedUrl(
        mediaType: MediaType,
        mediaId: Int,
        seasonNumber: Int?,
        episodeNumber: Int?,
        providedImdbId: String?,
        playbackUrl: String
    ) {
        try {
            val cachedImdbId = currentImdbId ?: mediaRepository.getCachedImdbId(mediaType, mediaId)
            val imdbId = when {
                !providedImdbId.isNullOrBlank() -> providedImdbId
                !cachedImdbId.isNullOrBlank() -> cachedImdbId
                else -> resolveExternalIds(mediaType, mediaId).imdbId
            }

            if (imdbId.isNullOrBlank()) {
                _uiState.value = _uiState.value.copy(isLoadingStreams = false)
                return
            }

            if (cachedImdbId.isNullOrBlank()) {
                mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
            }
            currentImdbId = imdbId

            if (mediaType == MediaType.TV && currentTvdbId == null) {
                currentTvdbId = resolveExternalIds(mediaType, mediaId).tvdbId
            }

            val result = if (mediaType == MediaType.MOVIE) {
                streamRepository.resolveMovieStreams(
                    imdbId = imdbId,
                    title = currentItemTitle,
                    year = null
                )
            } else {
                streamRepository.resolveEpisodeStreams(
                    imdbId = imdbId,
                    season = seasonNumber ?: 1,
                    episode = episodeNumber ?: 1,
                    tmdbId = mediaId,
                    tvdbId = currentTvdbId,
                    genreIds = currentGenreIds,
                    originalLanguage = currentOriginalLanguage,
                    title = currentItemTitle
                )
            }

            val allStreams = result.streams
                .filter { stream ->
                    val u = stream.url?.trim().orEmpty()
                    u.isNotBlank() && !u.startsWith("magnet:", ignoreCase = true)
                }

            val existingVod = _uiState.value.streams.filter { it.addonId == "iptv_xtream_vod" }
            val mergedStreams = (allStreams + existingVod)
                .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }

            val selectedMatch = mergedStreams.firstOrNull { stream ->
                isSameStreamUrl(stream.url, playbackUrl)
            }

            _uiState.value = _uiState.value.copy(
                streams = mergedStreams,
                selectedStream = selectedMatch ?: _uiState.value.selectedStream,
                isLoadingStreams = false
            )
        } catch (_: Exception) {
            _uiState.value = _uiState.value.copy(isLoadingStreams = false)
        }
    }

    private fun isSameStreamUrl(candidate: String?, target: String): Boolean {
        val candidateTrimmed = candidate?.trim().orEmpty()
        val targetTrimmed = target.trim()
        if (candidateTrimmed.isBlank() || targetTrimmed.isBlank()) return false
        if (candidateTrimmed.equals(targetTrimmed, ignoreCase = true)) return true
        return normalizeStreamUrlKey(candidateTrimmed) == normalizeStreamUrlKey(targetTrimmed)
    }

    private fun normalizeStreamUrlKey(url: String): String {
        return url
            .substringBefore('|')
            .substringBefore('?')
            .trim()
            .lowercase()
    }

    companion object {
        /** Known debrid service CDN domains. Reachability checks are skipped for these. */
        private val DEBRID_CDN_DOMAINS = setOf(
            // Real-Debrid
            "real-debrid.com",
            "rdb.so",
            "rdeb.io",
            // AllDebrid
            "alldebrid.com",
            "alldebrid.fr",
            // Premiumize
            "premiumize.me",
            // Debrid-Link
            "debrid-link.com",
            "debrid-link.fr",
            "dl.debrid-link.com",
            // TorBox
            "torbox.app",
            // EasyDebrid
            "easydebrid.com",
            // Offcloud
            "offcloud.com",
        )
    }
}

