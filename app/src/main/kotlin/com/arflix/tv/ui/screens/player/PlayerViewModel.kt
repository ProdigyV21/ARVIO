package com.arflix.tv.ui.screens.player

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.arflix.tv.BuildConfig
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.HomeServerRepository
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
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.weightedSubtitleScore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey as globalStringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

private fun isSupplementalStream(stream: StreamSource): Boolean =
    stream.addonId == "iptv_xtream_vod" || stream.addonId == HomeServerRepository.ADDON_ID

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
    val preferredSubtitleLang: String = "",
    val secondarySubtitleLang: String = "",
    val frameRateMatchingMode: String = "Off",
    val subtitleSize: String = "Medium",
    val subtitleColor: String = "White",
    val subtitleStyle: String = "Bold",
    val error: String? = null,
    val isSetupError: Boolean = false, // true when error is due to missing addons (shows friendly guide instead of red error)
    // Auto-play next episode at end of current one. Mirrors the profile-scoped
    // "auto_play_next" DataStore setting so the player can respect the toggle
    // and so the post-episode overlay can show a Continue/Cancel prompt.
    val autoPlayNext: Boolean = true,
    // Volume boost in decibels. 0 = disabled, up to 15 dB. The player observes this
    // and attaches a LoudnessEnhancer to the ExoPlayer audio session. Issue #88.
    val volumeBoostDb: Int = 0,
    // Show loading progress during stream resolution
    val showLoadingStats: Boolean = true,
    // Skip intro/recap
    val activeSkipInterval: SkipInterval? = null,
    val skipIntervalDismissed: Boolean = false,
    // Source-loading progress surfaced to the loading UI. When streams are
    // being resolved progressively, this fills from 0f→1f as addons complete.
    // Null when progress is not meaningful (e.g. trailer loads, cached hits).
    val streamProgress: Float? = null,
    // Human-readable phase label for the loading UI (e.g. "Searching 3/8
    // sources"). Null when progress isn't meaningful.
    val streamLoadPhase: String? = null,
    // True while AI subtitle translation is active for this playback session
    val isAiTranslating: Boolean = false,
    // True when AI translation is available (settings enabled + source track exists), even if not currently active
    val isAiAvailable: Boolean = false,
    // Language name being translated into (e.g. "Hebrew") when AI is available
    val aiTargetLanguageName: String = "",
    // Non-null while an AI translation API error toast should be visible
    val aiErrorToast: String? = null
)


private object PlayerRegexes {
    val ALPHA_DASH = Regex("[A-Za-z-]+")
    val ALPHA = Regex("[A-Za-z]+")
    val WHITESPACE = Regex("""\s+""")
    val SIZE_PATTERN_1 = Regex("""(\d+(?:\.\d+)?)\s*(TB|GB|MB|KB)""")
    val SIZE_PATTERN_2 = Regex("""(\d+(?:\.\d+)?)\s*(TIB|GIB|MIB|KIB)""")
    val SIZE_PATTERN_3 = Regex("""^(\d+(?:\.\d+)?)$""")
}

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
    private var hasManualSubtitleSelection: Boolean = false

    // AI subtitle settings (read once per video load)
    private var aiSubtitleEnabled = false
    private var aiSubtitleAutoSelect = false
    private var aiApiKey = ""
    private var aiModel = SubtitleAiModel.GROQ_LLAMA_70B
    private var aiRemoveHearingImpaired = true

    // Global AI subtitle DataStore keys (device-wide, not profile-scoped)
    private val aiEnabledKey = booleanPreferencesKey("subtitle_ai_enabled")
    private val aiAutoSelectKey = booleanPreferencesKey("subtitle_ai_auto_select")
    private val aiApiKeyKey = globalStringPreferencesKey("subtitle_ai_api_key")
    private val aiModelKey = globalStringPreferencesKey("subtitle_ai_model")
    private val aiRemoveHearingImpairedKey = booleanPreferencesKey("subtitle_remove_hearing_impaired")

    private val _isTranslatingLive = MutableStateFlow(false)
    val isTranslatingLive: kotlinx.coroutines.flow.StateFlow<Boolean> = _isTranslatingLive.asStateFlow()

    // True after the first API error toast has been shown for the current AI session.
    // Reset each time AI translation is (re-)activated so the user sees one toast per session.
    private var aiErrorToastShown = false
    // The source subtitle used for AI translation — retained so the user can re-activate AI after switching away.
    private var aiSourceSubtitle: Subtitle? = null

    val translationManager: SubtitleTranslationManager = SubtitleTranslationManager(
        service = SubtitleTranslationService(
            apiKeyProvider = { aiApiKey },
            modelProvider = { aiModel }
        ),
        targetLanguage = "",
        scope = viewModelScope
    ).also { mgr ->
        mgr.onTranslatingChanged = { isTranslating -> _isTranslatingLive.value = isTranslating }
        mgr.onBatchResult = { success, errorMessage ->
            if (!success && !aiErrorToastShown) {
                aiErrorToastShown = true
                val msg = when {
                    errorMessage == "API key missing" -> "AI subtitles: no API key set. Add it in Settings → General."
                    errorMessage == "RATE_LIMITED"    -> "AI subtitles: rate limit hit — translation paused."
                    errorMessage?.startsWith("HTTP 401") == true -> "AI subtitles: invalid API key."
                    else -> "AI subtitles: translation error — ${errorMessage.orEmpty()}"
                }
                _uiState.value = _uiState.value.copy(aiErrorToast = msg)
            }
        }
    }

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
    private val CLOUD_PUSH_INTERVAL_MS = 60_000L // Push CW to cloud every 60s during active playback

    private var lastCloudPushTime = 0L

    private fun defaultSubtitleKey() = profileManager.profileStringKey("default_subtitle")
    private fun defaultAudioLanguageKey() = profileManager.profileStringKey("default_audio_language")
    private fun subtitleUsageKey() = profileManager.profileStringKey("subtitle_usage_v1")
    private fun filterSubtitlesByLanguageKey() = profileManager.profileBooleanKey("filter_subtitles_by_lang")
    private fun secondarySubtitleKey() = profileManager.profileStringKey("secondary_subtitle")
    private fun frameRateMatchingModeKey() = profileManager.profileStringKey("frame_rate_matching_mode")
    private fun autoPlayNextKey() = profileManager.profileBooleanKey("auto_play_next")
    private fun showLoadingStatsKey() = profileManager.profileBooleanKey("show_loading_stats")
    private val gson = Gson()
    private val knownLanguageCodes = setOf(
        "en", "es", "fr", "de", "it", "pt", "nl", "ru", "zh", "ja", "ko",
        "ar", "hi", "tr", "pl", "sv", "no", "da", "fi", "el", "cs", "hu",
        "ro", "th", "vi", "id", "he",
        "uk", "fa", "bn", "bg", "hr", "sr", "sk", "sl", "lt", "et",
        "pt-br", "pob"
    )

    private fun playbackMsBucket(ms: Long): String = when {
        ms < 500L -> "lt_500ms"
        ms < 1_500L -> "lt_1500ms"
        ms < 5_000L -> "lt_5s"
        ms < 15_000L -> "lt_15s"
        else -> "gte_15s"
    }

    private fun streamKind(stream: StreamSource?): String {
        val source = stream ?: return "none"
        val addonId = source.addonId
        val url = source.url?.trim().orEmpty()
        return when {
            addonId == HomeServerRepository.ADDON_ID -> "home_server"
            addonId == "iptv_xtream_vod" -> "iptv_vod"
            url.startsWith("magnet:", ignoreCase = true) || !source.infoHash.isNullOrBlank() -> "p2p"
            url.startsWith("http://", ignoreCase = true) || url.startsWith("https://", ignoreCase = true) -> "http"
            else -> "unknown"
        }
    }

    private fun playbackDiagnosticContext(
        phase: String,
        stream: StreamSource? = _uiState.value.selectedStream,
        extra: Map<String, String> = emptyMap()
    ): Map<String, String> = mutableMapOf(
        "error_area" to "PlayerViewModel",
        "playback_phase" to phase,
        "media_type" to currentMediaType.name.lowercase(),
        "has_season" to (currentSeason != null).toString(),
        "has_episode" to (currentEpisode != null).toString(),
        "source_kind" to streamKind(stream),
        "addon_id" to (stream?.addonId?.ifBlank { "unknown" } ?: "none"),
        "quality" to (stream?.quality?.ifBlank { "unknown" } ?: "none"),
        "stream_count" to _uiState.value.streams.size.toString(),
        "has_selected_url" to (!_uiState.value.selectedStreamUrl.isNullOrBlank()).toString()
    ).apply { putAll(extra) }

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
        subtitleSelectionJob?.cancel()
        vodAppendJob?.cancel()
        homeServerAppendJob?.cancel()
        streamPrewarmJob?.cancel()
        focusedStreamPrewarmJob?.cancel()
        streamSelectionJob?.cancel()
        playbackErrorReportJob?.cancel()
        primaryStreamResolutionFinal = false
        hasManualSubtitleSelection = false
        aiSourceSubtitle = null
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            isLoadingStreams = false,
            selectedSubtitle = null,
            isAiTranslating = false,
            isAiAvailable = false,
            aiTargetLanguageName = "",
            streamProgress = null,
            streamLoadPhase = null,
            error = null,
            isSetupError = false
        )
        lastTopPrewarmKey = ""
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
            if (currentPreferredAddonId == null && currentPreferredSourceName == null && currentPreferredBingeGroup == null) {
                streamRepository.getLastGoodPlaybackPreference(
                    mediaType = mediaType,
                    tmdbId = mediaId,
                    season = seasonNumber,
                    episode = episodeNumber
                )?.let { preference ->
                    currentPreferredAddonId = preference.addonId.takeIf { it.isNotBlank() }
                    currentPreferredSourceName = preference.source.takeIf { it.isNotBlank() }
                    currentPreferredBingeGroup = preference.bingeGroup?.takeIf { it.isNotBlank() }
                    Log.i(
                        TAG,
                        "Using last good source preference addon=${currentPreferredAddonId ?: "-"} source=${currentPreferredSourceName ?: "-"} group=${currentPreferredBingeGroup ?: "-"}"
                    )
                }
            }
            val preferredAudioLanguage = resolvePreferredAudioLanguage()
            val frameRateMatchingMode = resolveFrameRateMatchingMode()
            val prefs = context.settingsDataStore.data.first()
            val subSize = prefs[profileManager.profileStringKey("subtitle_size")] ?: "Medium"
            val subColor = prefs[profileManager.profileStringKey("subtitle_color")] ?: "White"
            val subStyle = prefs[profileManager.profileStringKey("subtitle_style")] ?: "Bold"
            val autoPlayNext = prefs[autoPlayNextKey()] ?: true
            val showLoadingStats = prefs[showLoadingStatsKey()] ?: true
            val volumeBoostDb = prefs[profileManager.profileStringKey("volume_boost_db")]
                ?.toIntOrNull()?.coerceIn(0, 15) ?: 0
            val preferredSub = prefs[defaultSubtitleKey()]?.trim().orEmpty()
                .let { if (isSubtitleDisabledPreference(it)) "" else it }
            val secondarySub = prefs[secondarySubtitleKey()]?.trim().orEmpty()
                .let { if (isSubtitleDisabledPreference(it)) "" else it }

            // Load AI subtitle settings
            aiSubtitleEnabled = prefs[aiEnabledKey] ?: false
            aiSubtitleAutoSelect = prefs[aiAutoSelectKey] ?: false
            aiApiKey = prefs[aiApiKeyKey] ?: ""
            aiModel = runCatching {
                SubtitleAiModel.valueOf(prefs[aiModelKey] ?: SubtitleAiModel.GROQ_LLAMA_70B.name)
            }.getOrDefault(SubtitleAiModel.GROQ_LLAMA_70B)
            aiRemoveHearingImpaired = prefs[aiRemoveHearingImpairedKey] ?: true
            translationManager.updateService(apiKey = aiApiKey, model = aiModel)
            translationManager.removeHearingImpaired = aiRemoveHearingImpaired
            translationManager.isEnabled = false
            translationManager.reset()

            _uiState.value = PlayerUiState(
                isLoading = true,
                isLoadingStreams = true,
                preferredAudioLanguage = preferredAudioLanguage,
                preferredSubtitleLang = preferredSub,
                secondarySubtitleLang = secondarySub,
                frameRateMatchingMode = frameRateMatchingMode,
                subtitleSize = subSize,
                subtitleColor = subColor,
                subtitleStyle = subStyle,
                autoPlayNext = autoPlayNext,
                showLoadingStats = showLoadingStats,
                volumeBoostDb = volumeBoostDb
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
                val providedStream = if (isMagnet) {
                    null
                } else {
                    StreamSource(
                        source = currentPreferredSourceName ?: "Selected source",
                        addonName = currentPreferredAddonId ?: "",
                        addonId = currentPreferredAddonId.orEmpty(),
                        quality = "",
                        size = "",
                        url = providedStreamUrl
                    )
                }
                val resolvedProvidedStream = providedStream?.let { stream ->
                    runCatching { streamRepository.resolveStreamForPlayback(stream) }.getOrNull() ?: stream
                }
                val resolvedProvidedUrl = resolvedProvidedStream?.url ?: if (isMagnet) null else providedStreamUrl

                if (resolvedProvidedUrl.isNullOrBlank()) {
                    AppLogger.recordException(
                        throwable = IllegalStateException("Provided playback URL could not be resolved"),
                        context = playbackDiagnosticContext(
                            phase = "provided_stream_unresolved",
                            stream = providedStream,
                            extra = mapOf("provided_was_magnet" to isMagnet.toString())
                        )
                    )
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
                    selectedStream = resolvedProvidedStream,
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

                        val mergedSubs = filterSubsByPreferredLanguage(
                            (_uiState.value.subtitles + fetchedSubs)
                                .filter { it.isEmbedded || it.url.isNotBlank() }
                                .distinctBy { if (it.isEmbedded) it.id else "${it.id}|${it.url}" }
                        )

                        _uiState.value = _uiState.value.copy(
                            subtitles = mergedSubs,
                            isLoadingSubtitles = false
                        )

                        scheduleSubtitleSelection(currentOriginalLanguage)
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
                    AppLogger.recordException(
                        throwable = IllegalStateException("Playback IMDB ID missing"),
                        context = playbackDiagnosticContext("missing_imdb_id")
                    )
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
                homeServerAppendJob?.cancel()
                homeServerAppendJob = launch {
                    appendHomeServerSourcesInBackground(
                        mediaType = mediaType,
                        imdbId = imdbId,
                        seasonNumber = seasonNumber,
                        episodeNumber = episodeNumber,
                        timeoutMs = 5_000L
                    )
                }
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
                    isSetupError = false,
                    streamProgress = 0f,
                    streamLoadPhase = if (streamingAddonCount > 0) "Searching 0/$streamingAddonCount sources" else "Preparing sources"
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

                // Collect progressive emissions. Wait a very short window so
                // cached/debrid-ready sources can arrive before autoplay picks.
                val hasHomeServerConnections = streamRepository.hasHomeServerConnections()
                val HOME_SERVER_AUTOPLAY_WAIT_MS = 1_100L
                val AUTOPLAY_MAX_WINDOW_MS = if (hasHomeServerConnections) HOME_SERVER_AUTOPLAY_WAIT_MS else 650L
                val AUTOPLAY_QUALITY_WINDOW_MS = if (hasHomeServerConnections) 550L else 350L
                val collectionStartMs = System.currentTimeMillis()
                var autoplaySelected = false
                var autoplayDeferredJob: Job? = null
                var lastMergedStreams: List<StreamSource> = emptyList()
                var isFirstEmission = true
                var sourceEmptyReported = false

                progressiveFlow.collect { progressive ->
                    if (progressive.isFinal) {
                        primaryStreamResolutionFinal = true
                    }
                    val allStreams = progressive.streams
                        .filter { stream ->
                            val u = stream.url?.trim().orEmpty()
                            u.isNotBlank() && !u.startsWith("magnet:", ignoreCase = true)
                        }
                    val existingVod = _uiState.value.streams.filter(::isSupplementalStream)
                    val mergedStreams = sortStreamsByQualityAndSize(
                        (allStreams + existingVod)
                            .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" },
                        preferredLanguage
                    )
                    lastMergedStreams = mergedStreams

                    val supplementalSourcesStillLoading =
                        homeServerAppendJob?.isActive == true || vodAppendJob?.isActive == true
                    val errorMessage = if (
                        progressive.isFinal &&
                        mergedStreams.isEmpty() &&
                        !hasHomeServerConnections &&
                        !supplementalSourcesStillLoading
                    ) {
                        if (streamingAddonCount == 0) {
                            "No streaming addons configured.\n\nGo to Settings \u2192 Addons to add a streaming addon, then come back and try again."
                        } else {
                            "No streams found for this content. The addons may not have sources for this title."
                        }
                    } else null
                    if (errorMessage != null && !sourceEmptyReported) {
                        sourceEmptyReported = true
                        AppLogger.recordException(
                            throwable = IllegalStateException("Playback source list empty"),
                            context = playbackDiagnosticContext(
                                phase = "source_list_empty",
                                stream = null,
                                extra = mapOf(
                                    "streaming_addon_count" to streamingAddonCount.toString(),
                                    "has_home_server_connections" to hasHomeServerConnections.toString(),
                                    "completed_addons" to progressive.completedAddons.toString(),
                                    "total_addons" to progressive.totalAddons.toString(),
                                    "supplemental_loading" to supplementalSourcesStillLoading.toString()
                                )
                            )
                        )
                    }

                    val total = progressive.totalAddons.coerceAtLeast(1)
                    val completed = progressive.completedAddons.coerceIn(0, total)
                    val progressFraction = if (progressive.isFinal) {
                        1f
                    } else {
                        (completed.toFloat() / total.toFloat()).coerceIn(0f, 0.99f)
                    }
                    val phaseLabel = when {
                        progressive.isFinal -> null
                        mergedStreams.isNotEmpty() -> "Found ${mergedStreams.size} sources ($completed/$total)"
                        else -> "Searching $completed/$total sources"
                    }
                    val filteredSubtitles = filterSubsByPreferredLanguage(progressive.subtitles)

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoadingStreams = mergedStreams.isEmpty() &&
                            (!progressive.isFinal || hasHomeServerConnections || supplementalSourcesStillLoading),
                        streams = mergedStreams,
                        subtitles = filteredSubtitles,
                        error = errorMessage,
                        isSetupError = progressive.isFinal &&
                            mergedStreams.isEmpty() &&
                            streamingAddonCount == 0 &&
                            !hasHomeServerConnections &&
                            !supplementalSourcesStillLoading,
                        streamProgress = if (progressive.isFinal) null else progressFraction,
                        streamLoadPhase = phaseLabel
                    )
                    prewarmTopStreams(mergedStreams, preferredLanguage)

                    val cacheHit = isFirstEmission && progressive.isFinal && mergedStreams.isNotEmpty()
                    isFirstEmission = false

                    val elapsedMs = System.currentTimeMillis() - collectionStartMs
                    val hasHomeServerStream = mergedStreams.any { it.addonId == HomeServerRepository.ADDON_ID }
                    val homeServerReadyForAutoplay = !hasHomeServerConnections ||
                        hasHomeServerStream ||
                        elapsedMs >= HOME_SERVER_AUTOPLAY_WAIT_MS
                    val hasCachedReadyStream = mergedStreams.any { stream ->
                        stream.behaviorHints?.cached == true &&
                            stream.behaviorHints.notWebReady != true &&
                            !stream.url.isNullOrBlank()
                    }
                    if (!autoplaySelected && mergedStreams.isNotEmpty() && autoplayDeferredJob == null) {
                        autoplayDeferredJob = launch {
                            delay(AUTOPLAY_QUALITY_WINDOW_MS)
                            if (!autoplaySelected) {
                                var snapshot = lastMergedStreams.ifEmpty { mergedStreams }
                                if (hasHomeServerConnections &&
                                    snapshot.none { it.addonId == HomeServerRepository.ADDON_ID }
                                ) {
                                    val remainingMs = HOME_SERVER_AUTOPLAY_WAIT_MS - (System.currentTimeMillis() - collectionStartMs)
                                    if (remainingMs > 0L) delay(remainingMs)
                                    snapshot = sortStreamsByQualityAndSize(
                                        (_uiState.value.streams + snapshot)
                                            .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" },
                                        preferredLanguage
                                    )
                                }
                                if (snapshot.isNotEmpty()) {
                                    autoplaySelected = true
                                    Log.i(
                                        TAG,
                                        "Autoplay selecting after quality window streams=${snapshot.size} elapsedMs=${System.currentTimeMillis() - collectionStartMs}"
                                    )
                                    autoplaySelectBest(snapshot, preferredLanguage)
                                }
                            }
                        }
                    }
                    val topStream = mergedStreams.firstOrNull()
                    val hasRequestedPreferredStream = hasRequestedPreferredStream(mergedStreams)
                    val shouldSelectNow = !autoplaySelected && mergedStreams.isNotEmpty() && homeServerReadyForAutoplay && (
                        cacheHit ||
                            progressive.isFinal ||
                            hasCachedReadyStream ||
                            hasRequestedPreferredStream ||
                            isExcellentAutoplayCandidate(topStream) ||
                            elapsedMs >= AUTOPLAY_MAX_WINDOW_MS
                        )

                    if (shouldSelectNow) {
                        autoplaySelected = true
                        autoplayDeferredJob?.cancel()
                        autoplayDeferredJob = null
                        Log.i(
                            TAG,
                            "Autoplay selecting streams=${mergedStreams.size} completed=$completed/$total final=${progressive.isFinal} cached=$hasCachedReadyStream preferred=$hasRequestedPreferredStream elapsedMs=$elapsedMs top=${topStream?.quality}/${topStream?.size}"
                        )
                        autoplaySelectBest(mergedStreams, preferredLanguage)
                    }
                }

                if (!autoplaySelected && lastMergedStreams.isNotEmpty()) {
                    if (hasHomeServerConnections &&
                        lastMergedStreams.none { it.addonId == HomeServerRepository.ADDON_ID }
                    ) {
                        val remainingMs = HOME_SERVER_AUTOPLAY_WAIT_MS - (System.currentTimeMillis() - collectionStartMs)
                        if (remainingMs > 0L) delay(remainingMs)
                        lastMergedStreams = sortStreamsByQualityAndSize(
                            (_uiState.value.streams + lastMergedStreams)
                                .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" },
                            preferredLanguage
                        )
                    }
                    autoplaySelected = true
                    autoplayDeferredJob?.cancel()
                    autoplayDeferredJob = null
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

                    val mergedSubs = filterSubsByPreferredLanguage(
                        (_uiState.value.subtitles + fetchedSubs)
                            .filter { it.isEmbedded || it.url.isNotBlank() }
                            .distinctBy { if (it.isEmbedded) it.id else "${it.id}|${it.url}" }
                    )

                    _uiState.value = _uiState.value.copy(
                        subtitles = mergedSubs,
                        isLoadingSubtitles = false
                    )
                    scheduleSubtitleSelection(currentOriginalLanguage)
                }

            } catch (e: Exception) {
                AppLogger.recordException(
                    throwable = e,
                    context = playbackDiagnosticContext("load_media_exception")
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    streamProgress = null,
                    streamLoadPhase = null,
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

    // Returns subs filtered to the preferred language(s) when the setting is enabled.
    // Tries primary language first; if nothing matches, tries secondary; falls back to full list.
    private suspend fun filterSubsByPreferredLanguage(subs: List<Subtitle>): List<Subtitle> {
        val prefs = runCatching { context.settingsDataStore.data.first() }.getOrNull() ?: return subs
        val enabled = prefs[filterSubtitlesByLanguageKey()] ?: true
        if (!enabled) return subs
        val preferred = prefs[defaultSubtitleKey()]?.trim().orEmpty()
        val secondary = prefs[secondarySubtitleKey()]?.trim().orEmpty()
        val targetLanguages = listOf(preferred, secondary)
            .filterNot { isSubtitleDisabledPreference(it) }
            .map { normalizeLanguage(it) }
            .filter { it.isNotBlank() }
            .distinct()
        if (targetLanguages.isEmpty()) return subs

        fun matchesLang(sub: Subtitle, lang: String): Boolean {
            val tokens = buildSet {
                add(normalizeLanguage(sub.lang))
                add(normalizeLanguage(sub.label))
                PlayerRegexes.ALPHA_DASH.findAll("${sub.lang} ${sub.label}")
                    .map { normalizeLanguage(it.value) }
                    .filter { it.isNotBlank() }
                    .forEach { add(it) }
            }
            if (tokens.contains(lang)) return true
            // Substring match only for long-form names — avoids "en" matching "Indonesian"
            if (lang.length > 2) {
                return sub.lang.lowercase().contains(lang) || sub.label.lowercase().contains(lang)
            }
            return false
        }

        // Always keep embedded subtitles — they are needed as AI translation sources
        // and represent tracks the device actually has, not fetched addons.
        val combined = subs.filter { sub ->
            sub.isEmbedded || targetLanguages.any { lang -> matchesLang(sub, lang) }
        }.distinctBy { it.id }
        return combined.ifEmpty { subs }
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

    private fun scheduleSubtitleSelection(fallbackLanguage: String?) {
        if (hasManualSubtitleSelection) return
        val currentSel = _uiState.value.selectedSubtitle
        subtitleSelectionJob?.cancel()
        subtitleSelectionJob = viewModelScope.launch {
            val preferred = getDefaultSubtitle()
            // Skip only if the already-selected embedded is in the preferred language — if it's
            // a fallback-language track (e.g. English selected while waiting for Hebrew), let
            // applyPreferredSubtitle decide whether to upgrade to the preferred language.
            if (currentSel?.isEmbedded == true &&
                normalizeLanguage(currentSel.lang) == normalizeLanguage(preferred)) {
                return@launch
            }
            val subs = _uiState.value.subtitles
            applyPreferredSubtitle(preferred, subs, fallbackLanguage)
        }
    }

    private fun applyPreferredSubtitle(preference: String, subtitles: List<Subtitle>, fallbackLanguage: String?) {
        if (hasManualSubtitleSelection) return
        if (isSubtitleDisabledPreference(preference)) {
            _uiState.value = _uiState.value.copy(selectedSubtitle = null)
            return
        }

        val normalizedPref = normalizeLanguage(preference)
        val normalizedFallback = fallbackLanguage
            ?.let { normalizeLanguage(it) }
            ?.takeIf { it.isNotBlank() && it != normalizedPref }

        val streamSrc = _uiState.value.selectedStream?.source.orEmpty()

        fun matchScore(sub: Subtitle): Int {
            if (sub.isEmbedded) return 100
            return weightedSubtitleScore(streamSrc, sub.id)
        }

        fun subtitleTokens(sub: Subtitle): Set<String> {
            val rawTokens = PlayerRegexes.ALPHA_DASH.findAll("${sub.lang} ${sub.label}")
                .map { it.value }
                .toList()
            val normalized = rawTokens.map { normalizeLanguage(it) }.filter { it.isNotBlank() }
            return buildSet {
                add(normalizeLanguage(sub.lang))
                add(normalizeLanguage(sub.label))
                addAll(normalized)
            }.filter { it.isNotBlank() }.toSet()
        }

        fun bestMatch(target: String, pool: List<Subtitle> = subtitles): Subtitle? {
            val byToken = pool.filter { sub -> subtitleTokens(sub).contains(target) }
            // Drop candidates whose label explicitly names a different language — catches scrambled
            // MKV metadata where the lang code is wrong (e.g. lang='he' label='Japanese').
            // A label is "known" when normalizeLanguage maps it to something different from its own
            // lowercased form (e.g. "Japanese" → "ja"); unknown labels like "SDH" are never excluded.
            val labelConsistent = byToken.filter { sub ->
                val labelLang = normalizeLanguage(sub.label)
                val labelMappedToKnown = sub.label.isNotBlank() && labelLang != sub.label.lowercase().trim()
                !labelMappedToKnown || labelLang == target
            }
            val candidates = when {
                labelConsistent.isNotEmpty() -> labelConsistent
                byToken.isEmpty() -> {
                    pool.filter { sub ->
                        sub.label.lowercase().contains(target) || sub.lang.lowercase().contains(target)
                    }
                }
                else -> emptyList()
            }
            if (candidates.isEmpty()) return null
            val sorted = candidates.sortedWith(
                compareByDescending<Subtitle> { if (it.isEmbedded) 1 else 0 }
                    .thenByDescending { matchScore(it) }
                    .thenBy { it.groupIndex ?: Int.MAX_VALUE }
                    .thenBy { it.trackIndex ?: Int.MAX_VALUE }
            )
            return sorted.first()
        }

        // When AI is active, only an embedded built-in subtitle suppresses AI.
        // External addon subtitles (WIZDOM, KTUVIT, etc.) do not count — AI takes priority over them.
        val aiModeActive = aiSubtitleEnabled && aiSubtitleAutoSelect && normalizedPref.isNotBlank()
        // AI enabled regardless of auto-select — used to expose the AI option in the picker
        val aiEnabledForLanguage = aiSubtitleEnabled && normalizedPref.isNotBlank()
        val embeddedOnly = subtitles.filter { it.isEmbedded }

        // In AI mode, only the user's preferred language embedded sub suppresses AI.
        // A fallback-language embedded sub (e.g. English) is the AI source, not a replacement.
        val embeddedPrefMatch = bestMatch(normalizedPref, embeddedOnly)
        val embeddedFallbackMatch = if (!aiEnabledForLanguage && embeddedPrefMatch == null && normalizedFallback != null)
            bestMatch(normalizedFallback, embeddedOnly) else null
        val embeddedMatch = embeddedPrefMatch ?: embeddedFallbackMatch

        if (embeddedMatch != null) {
            val current = _uiState.value.selectedSubtitle
            val isFallback = embeddedPrefMatch == null
            // Never auto-select a fallback-language embedded track — it would immediately block
            // the preferred language from being selected when it arrives later (scheduleSubtitleSelection
            // bails early if any embedded is already selected, and shouldReapply ignores lang changes).
            if (!isFallback) {
                translationManager.isEnabled = false
                aiSourceSubtitle = null
                _uiState.value = _uiState.value.copy(selectedSubtitle = embeddedMatch, isAiTranslating = false, isAiAvailable = false, aiTargetLanguageName = "")
            }
        } else if (aiModeActive) {
            // No embedded preferred-language subtitle → activate AI (addon subs are ignored)
            val source = findAiSourceSubtitle(subtitles)
            if (source != null) {
                val targetLangName = languageCodeToName(normalizedPref)
                aiSourceSubtitle = source
                translationManager.targetLanguage = targetLangName
                translationManager.isEnabled = true
                translationManager.reset()
                aiErrorToastShown = false
                _uiState.value = _uiState.value.copy(selectedSubtitle = source, isAiTranslating = true, isAiAvailable = true, aiTargetLanguageName = targetLangName, aiErrorToast = null)
            } else {
                // No embedded source to translate from — fall back to best external match
                val externalMatch = bestMatch(normalizedPref)
                    ?: normalizedFallback?.let { bestMatch(it) }
                if (externalMatch != null) {
                    val current = _uiState.value.selectedSubtitle
                    if (current == null || !(current.isEmbedded && !externalMatch.isEmbedded)) {
                        translationManager.isEnabled = false
                        aiSourceSubtitle = null
                        _uiState.value = _uiState.value.copy(selectedSubtitle = externalMatch, isAiTranslating = false, isAiAvailable = false, aiTargetLanguageName = "")
                    }
                }
            }
        } else {
            // AI enabled but auto-select off — expose AI option in picker without activating it
            if (aiEnabledForLanguage) {
                val source = findAiSourceSubtitle(subtitles)
                if (source != null) {
                    aiSourceSubtitle = source
                    val targetLangName = languageCodeToName(normalizedPref)
                    translationManager.targetLanguage = targetLangName
                    _uiState.value = _uiState.value.copy(isAiAvailable = true, aiTargetLanguageName = targetLangName)
                }
            }
            // Pick best external match in preferred language
            val externalMatch = bestMatch(normalizedPref)
                ?: normalizedFallback?.let { bestMatch(it) }
            if (externalMatch != null) {
                val current = _uiState.value.selectedSubtitle
                if (current == null || !(current.isEmbedded && !externalMatch.isEmbedded)) {
                    translationManager.isEnabled = false
                    if (aiEnabledForLanguage) {
                        // Keep AI available in picker — user can still activate it manually
                        _uiState.value = _uiState.value.copy(selectedSubtitle = externalMatch, isAiTranslating = false)
                    } else {
                        aiSourceSubtitle = null
                        _uiState.value = _uiState.value.copy(selectedSubtitle = externalMatch, isAiTranslating = false, isAiAvailable = false, aiTargetLanguageName = "")
                    }
                }
            }
        }
    }

    private fun findAiSourceSubtitle(subtitles: List<Subtitle>): Subtitle? {
        // Prefer English embedded > any embedded with lang > any embedded > any subtitle
        return subtitles.firstOrNull { it.isEmbedded && normalizeLanguage(it.lang) == "en" }
            ?: subtitles.firstOrNull { it.isEmbedded && it.lang.isNotBlank() }
            ?: subtitles.firstOrNull { it.isEmbedded }
            ?: subtitles.firstOrNull()
    }

    private fun languageCodeToName(code: String): String {
        return when (code.lowercase().trim()) {
            "he", "hebrew", "iw" -> "Hebrew"
            "ar", "arabic" -> "Arabic"
            "fa", "persian", "farsi" -> "Persian"
            "ur", "urdu" -> "Urdu"
            "yi", "yiddish" -> "Yiddish"
            "ru", "russian" -> "Russian"
            "zh", "chinese" -> "Chinese"
            "ja", "japanese" -> "Japanese"
            "ko", "korean" -> "Korean"
            "fr", "french" -> "French"
            "de", "german" -> "German"
            "es", "spanish" -> "Spanish"
            "it", "italian" -> "Italian"
            "pt", "portuguese" -> "Portuguese"
            "pt-br", "pob" -> "Brazilian Portuguese"
            "nl", "dutch" -> "Dutch"
            "pl", "polish" -> "Polish"
            "tr", "turkish" -> "Turkish"
            "sv", "swedish" -> "Swedish"
            "no", "norwegian" -> "Norwegian"
            "da", "danish" -> "Danish"
            "fi", "finnish" -> "Finnish"
            "el", "greek" -> "Greek"
            "cs", "czech" -> "Czech"
            "hu", "hungarian" -> "Hungarian"
            "ro", "romanian" -> "Romanian"
            "th", "thai" -> "Thai"
            "vi", "vietnamese" -> "Vietnamese"
            "id", "indonesian" -> "Indonesian"
            "hi", "hindi" -> "Hindi"
            "bn", "bengali" -> "Bengali"
            "bg", "bulgarian" -> "Bulgarian"
            "hr", "croatian" -> "Croatian"
            "sr", "serbian" -> "Serbian"
            "sk", "slovak" -> "Slovak"
            "sl", "slovenian" -> "Slovenian"
            "lt", "lithuanian" -> "Lithuanian"
            "et", "estonian" -> "Estonian"
            "uk", "ukrainian" -> "Ukrainian"
            "en", "english" -> "English"
            else -> code.replaceFirstChar { it.uppercase() }
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

    private fun qualityScore(quality: String): Int {
        return when {
            quality.contains("4K", ignoreCase = true) || quality.contains("2160p", ignoreCase = true) -> 4
            quality.contains("1080p", ignoreCase = true) -> 3
            quality.contains("720p", ignoreCase = true) -> 2
            quality.contains("480p", ignoreCase = true) -> 1
            else -> 0
        }
    }

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

        // Quality is the PRIMARY signal — 4K beats 1080p beats 720p regardless of size.
        var score = qualityScore(stream.quality) * 1_000

        // Within the same quality tier, prefer larger files because they usually
        // indicate better bitrate/encodes. Do not cap huge files: autoplay should
        // still pick the best source and let fast failover handle bad starts.
        val sizeBytes = parseSize(stream.size)
        score += when {
            sizeBytes <= 0L -> 0
            else -> (sizeBytes / (512L * 1024L * 1024L)).toInt().coerceAtMost(240)
        }

        if (text.contains("cam") || text.contains("hdcam") || text.contains("telesync")) score -= 600
        if (text.contains("web-dl") || text.contains("webrip")) score += 50
        if (text.contains("bluray") || text.contains("blu-ray")) score += 60
        if (text.contains("remux")) score += 80
        if (text.contains("dolby vision") || text.contains(" dovi") || text.contains(" dv ")) score += 30
        if (text.contains("x265") || text.contains("hevc") || text.contains("h265")) score += 30
        if (text.contains("x264") || text.contains("h264")) score += 20
        if (stream.behaviorHints?.cached == true || text.contains(" rd+")) score += 500
        if (stream.behaviorHints?.notWebReady == true) score -= 150
        if (!stream.url.isNullOrBlank() && stream.url.startsWith("http", ignoreCase = true)) score += 100
        if (stream.url?.startsWith("magnet:", ignoreCase = true) == true) score -= 800
        score += streamRepository.getAddonHealthBias(stream.addonId)

        return score
    }

    fun onPlaybackStarted(startupMs: Long, startupRetries: Int, autoFailovers: Int) {
        playbackErrorReportJob?.cancel()
        if (_uiState.value.error != null) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isLoadingStreams = false,
                streamProgress = null,
                streamLoadPhase = null,
                error = null
            )
        }
        val addonId = _uiState.value.selectedStream?.addonId?.trim()
            .takeUnless { it.isNullOrBlank() }
            ?: currentPreferredAddonId.orEmpty()
        if (addonId.isNotBlank()) {
            streamRepository.noteAddonPlaybackStarted(addonId, startupMs)
        }
        Log.i(
            TAG,
            "Playback started addonId=${addonId.ifBlank { "unknown" }} " +
                "source=${_uiState.value.selectedStream?.source ?: currentPreferredSourceName ?: "unknown"} " +
                "startupMs=$startupMs retries=$startupRetries failovers=$autoFailovers"
        )
        AppLogger.breadcrumb(
            tag = "Playback",
            message = "started kind=${streamKind(_uiState.value.selectedStream)} addon=${addonId.ifBlank { "unknown" }} startup=${playbackMsBucket(startupMs)} retries=$startupRetries failovers=$autoFailovers",
            severity = "info"
        )
        viewModelScope.launch {
            _uiState.value.selectedStream?.let { stream ->
                streamRepository.notePlaybackHostSuccess(stream)
                streamRepository.saveLastGoodPlaybackPreference(
                    mediaType = currentMediaType,
                    tmdbId = currentMediaId,
                    season = currentSeason,
                    episode = currentEpisode,
                    stream = stream
                )
            }
            playbackTelemetryRepository.recordStartup(
                startupMs = startupMs,
                retries = startupRetries,
                failoversBeforeStart = autoFailovers
            )
        }
    }

    fun onSelectedStreamPlaybackFailure() {
        val addonId = _uiState.value.selectedStream?.addonId?.trim()
            .takeUnless { it.isNullOrBlank() }
            ?: currentPreferredAddonId.orEmpty()
        if (addonId.isNotBlank()) {
            streamRepository.noteAddonPlaybackFailure(addonId)
        }
        streamRepository.notePlaybackHostFailure(_uiState.value.selectedStream, "exo_playback_failure")
        AppLogger.recordException(
            throwable = IllegalStateException("Selected stream playback failed"),
            context = playbackDiagnosticContext(
                phase = "selected_stream_playback_failure",
                extra = mapOf("addon_id" to addonId.ifBlank { "unknown" })
            )
        )
        viewModelScope.launch {
            playbackTelemetryRepository.recordPlaybackFailure()
        }
    }

    fun isPlaybackHostTemporarilyBad(stream: StreamSource): Boolean {
        return streamRepository.isPlaybackHostTemporarilyBad(stream)
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
        val healthyStreams = streams.filterNot { streamRepository.isPlaybackHostTemporarilyBad(it) }
            .ifEmpty { streams }
        val preferredFromBingeGroup = currentPreferredBingeGroup?.let { preferredGroup ->
            healthyStreams.firstOrNull { stream ->
                stream.behaviorHints?.bingeGroup == preferredGroup &&
                    (currentPreferredAddonId?.let { stream.addonId == it } ?: true)
            } ?: healthyStreams.firstOrNull { stream ->
                stream.behaviorHints?.bingeGroup == preferredGroup
            }
        }

        val preferredFromNavigation = healthyStreams.firstOrNull { s ->
            val addonMatch = currentPreferredAddonId?.let { s.addonId == it } ?: true
            val sourceMatch = currentPreferredSourceName?.let { s.source == it } ?: true
            addonMatch && sourceMatch
        } ?: healthyStreams.firstOrNull { s ->
            currentPreferredAddonId?.let { s.addonId == it } ?: false
        }

        val stabilitySelected = pickPreferredStream(healthyStreams, preferredLanguage)
        val selected = preferredFromBingeGroup ?: preferredFromNavigation ?: stabilitySelected ?: healthyStreams.first()
        selectStream(selected)
    }

    private fun hasRequestedPreferredStream(streams: List<StreamSource>): Boolean {
        if (streams.isEmpty()) return false
        val preferredGroup = currentPreferredBingeGroup
        val preferredAddon = currentPreferredAddonId
        val preferredSource = currentPreferredSourceName
        if (preferredGroup.isNullOrBlank() && preferredAddon.isNullOrBlank() && preferredSource.isNullOrBlank()) {
            return false
        }
        return streams.any { stream ->
            val groupMatch = preferredGroup?.let { stream.behaviorHints?.bingeGroup == it } ?: true
            val addonMatch = preferredAddon?.let { stream.addonId == it } ?: true
            val sourceMatch = preferredSource?.let { stream.source == it } ?: true
            groupMatch && addonMatch && sourceMatch && !stream.url.isNullOrBlank()
        }
    }

    private fun isExcellentAutoplayCandidate(stream: StreamSource?): Boolean {
        stream ?: return false
        val url = stream.url?.trim().orEmpty()
        if (url.isBlank() || url.startsWith("magnet:", ignoreCase = true)) return false
        if (stream.behaviorHints?.notWebReady == true) return false
        return stream.behaviorHints?.cached == true ||
            qualityScore(stream.quality) >= 4 ||
            (qualityScore(stream.quality) >= 3 && parseSize(stream.size) >= 8L * 1024L * 1024L * 1024L)
    }

    private fun pickPreferredStream(
        streams: List<StreamSource>,
        preferredLanguage: String
    ): StreamSource? {
        if (streams.isEmpty()) return null

        return sortStreamsByQualityAndSize(streams, preferredLanguage).firstOrNull()
    }

    private fun sortStreamsByQualityAndSize(
        streams: List<StreamSource>,
        preferredLanguage: String
    ): List<StreamSource> {
        return streams.sortedWith(
            compareByDescending<StreamSource> { qualityScore(it.quality) }
                .thenBy { streamRepository.getPlaybackHostHealthPenalty(it) }
                .thenByDescending { parseSize(it.size) }
                .thenByDescending { streamLanguageScore(it, preferredLanguage) }
                .thenByDescending { if (it.behaviorHints?.cached == true) 1 else 0 }
                .thenBy { if (it.behaviorHints?.notWebReady == true) 1 else 0 }
                .thenByDescending { streamRepository.getAddonHealthBias(it.addonId) }
        )
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

    private fun prewarmTopStreams(streams: List<StreamSource>, preferredLanguage: String) {
        if (streams.isEmpty()) return
        val topStreams = sortStreamsByQualityAndSize(streams, preferredLanguage).take(3)
        val prewarmKey = topStreams.joinToString("|") { stream ->
            "${stream.addonId}:${stream.source}:${stream.url?.substringBefore('|')?.substringBefore('#')}"
        }
        if (prewarmKey == lastTopPrewarmKey) return
        lastTopPrewarmKey = prewarmKey
        streamPrewarmJob?.cancel()
        streamPrewarmJob = viewModelScope.launch {
            runCatching {
                streamRepository.prewarmStreamsForPlayback(
                    streams = topStreams,
                    limit = topStreams.size,
                    allowNetworkWarmup = true
                )
            }
        }
    }

    // Robust size string parser - identical to StreamSelector's parseSizeString()
    // Handles comma decimals ("5,2 GB"), GiB notation, extra spaces, etc.
    private fun parseSize(sizeStr: String): Long {
        if (sizeStr.isBlank()) return 0L

        // Normalize: uppercase, replace comma with dot, remove extra spaces
        val normalized = sizeStr.uppercase()
            .replace(",", ".")
            .replace(PlayerRegexes.WHITESPACE, " ")
            .trim()

        // Pattern 1: "15.2 GB", "6GB", "1.5 TB" etc.
        val pattern1 = PlayerRegexes.SIZE_PATTERN_1
        pattern1.find(normalized)?.let { match ->
            val number = match.groupValues[1].toDoubleOrNull() ?: return@let
            val unit = match.groupValues[2]
            return calcBytes(number, unit)
        }

        // Pattern 2: Numbers with GiB/MiB notation
        val pattern2 = PlayerRegexes.SIZE_PATTERN_2
        pattern2.find(normalized)?.let { match ->
            val number = match.groupValues[1].toDoubleOrNull() ?: return@let
            val unit = match.groupValues[2].replace("IB", "B")
            return calcBytes(number, unit)
        }

        // Pattern 3: Just a number (assume bytes)
        val pattern3 = PlayerRegexes.SIZE_PATTERN_3
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
        val tokens = PlayerRegexes.ALPHA.findAll(text).map { it.value }.toList()
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
            lowerLang == "heb" || lowerLang == "iw" -> "he"
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
    fun selectStream(stream: StreamSource, resumePositionMs: Long? = null) {
        streamSelectionJob?.cancel()
        playbackErrorReportJob?.cancel()
        streamSelectionJob = viewModelScope.launch {
            val selectionStartMs = System.currentTimeMillis()
            val requestedResumePosition = resumePositionMs?.coerceAtLeast(0L)
            val selectedOriginal = stream
            val resolvedResult = runCatching {
                streamRepository.resolveStreamForPlayback(stream)
            }
            resolvedResult.onFailure { error ->
                AppLogger.recordException(
                    throwable = error,
                    context = playbackDiagnosticContext("manual_stream_resolve_exception", stream)
                )
            }
            val resolvedStream = resolvedResult.getOrNull() ?: stream
            val resolveMs = System.currentTimeMillis() - selectionStartMs
            val url = resolvedStream.url
            if (url.isNullOrBlank()) {
                val isP2p = !stream.infoHash.isNullOrBlank() ||
                    (stream.url?.trim()?.startsWith("magnet:", ignoreCase = true) == true)
                AppLogger.recordException(
                    throwable = IllegalStateException("Selected stream has no playable URL"),
                    context = playbackDiagnosticContext(
                        phase = "selected_stream_url_missing",
                        stream = stream,
                        extra = mapOf("is_p2p" to isP2p.toString())
                    )
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    streamProgress = null,
                    streamLoadPhase = null,
                    error = if (isP2p) {
                        "P2P stream requires TorrServer. Install TorrServer and set its URL in Settings > Addons."
                    } else {
                        "Failed to resolve stream. Try another source."
                    }
                )
                return@launch
            }
            Log.i(
                TAG,
                "Selected stream resolvedMs=$resolveMs addon=${resolvedStream.addonId} quality=${resolvedStream.quality} size=${resolvedStream.size} cached=${resolvedStream.behaviorHints?.cached == true} host=${runCatching { java.net.URI(url).host }.getOrNull().orEmpty()}"
            )
            AppLogger.breadcrumb(
                tag = "Playback",
                message = "stream_selected kind=${streamKind(resolvedStream)} addon=${resolvedStream.addonId.ifBlank { "unknown" }} quality=${resolvedStream.quality.ifBlank { "unknown" }} resolve=${playbackMsBucket(resolveMs)} cached=${resolvedStream.behaviorHints?.cached == true}",
                severity = "info"
            )

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
                savedPosition = requestedResumePosition ?: _uiState.value.savedPosition,
                streamSelectionNonce = _uiState.value.streamSelectionNonce + 1,
                isLoading = false,
                isLoadingStreams = false,
                streamProgress = null,
                streamLoadPhase = null,
                error = null,
                isSetupError = false
            )

            // Re-run subtitle selection now that streamSrc is known — scores are now meaningful
            scheduleSubtitleSelection(currentOriginalLanguage)

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
                    val merged = filterSubsByPreferredLanguage(
                        existing + extraSubs.filter { newSub ->
                            newSub.url.isNotBlank() && existing.none { it.id == newSub.id || it.url == newSub.url }
                        }
                    )
                    _uiState.value = _uiState.value.copy(subtitles = merged)

                    scheduleSubtitleSelection(currentOriginalLanguage)
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
        playbackErrorReportJob?.cancel()
        val errorSelectionNonce = _uiState.value.streamSelectionNonce
        val errorSelectedUrl = _uiState.value.selectedStreamUrl
        playbackErrorReportJob = viewModelScope.launch {
            delay(1_200L)
            val latest = _uiState.value
            if (
                latest.streamSelectionNonce != errorSelectionNonce ||
                latest.selectedStreamUrl != errorSelectedUrl
            ) {
                return@launch
            }
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isLoadingStreams = false,
                streamProgress = null,
                streamLoadPhase = null,
                error = message
            )
            AppLogger.recordException(
                throwable = IllegalStateException("Playback error displayed"),
                context = playbackDiagnosticContext(
                    phase = "playback_error_displayed",
                    extra = mapOf("playback_error_message" to message)
                )
            )
        }
    }

    fun updatePlayerTextTracks(playerTextTracks: List<Subtitle>) {
        viewModelScope.launch {
            val current = _uiState.value.subtitles
            val trackBackedIds = playerTextTracks.map { it.id }.toSet()

            // Keep external subtitle entries that haven't been mapped to concrete track indices yet.
            val unresolvedExternal = current.filter { subtitle ->
                !subtitle.isEmbedded && subtitle.url.isNotBlank() && subtitle.id !in trackBackedIds
            }

            // Preserve existing embedded subs when playerTextTracks has none — ExoPlayer fires
            // onTracksChanged with an empty list during MediaItem rebuilds (e.g. when switching
            // to an external subtitle), then fires again with the full track list. Without this,
            // embedded tracks disappear permanently if the second callback never fires or arrives late.
            val existingEmbedded = current.filter { it.isEmbedded }
            val newEmbedded = playerTextTracks.filter { it.isEmbedded }
            val effectiveEmbedded = newEmbedded.ifEmpty { existingEmbedded }

            // Embedded subtitles first, then external/addon subtitles
            val merged = (effectiveEmbedded + playerTextTracks.filter { !it.isEmbedded } + unresolvedExternal)
                .distinctBy { subtitle ->
                    val normalizedId = subtitle.id.trim()
                    if (normalizedId.isNotBlank()) normalizedId
                    else "${subtitle.lang}|${subtitle.label}|${subtitle.url}"
                }

            // Apply the same language filter used for fetched external subs so that
            // ExoPlayer text-track updates don't re-add all embedded languages to the menu.
            val filtered = filterSubsByPreferredLanguage(merged)

            // Always keep the currently selected subtitle visible even if it doesn't
            // match the preferred language filter so the active selection stays consistent.
            val selected = _uiState.value.selectedSubtitle
            val finalList = if (selected != null && filtered.none { it.id == selected.id }) {
                (filtered + selected).distinctBy { s ->
                    s.id.trim().ifBlank { "${s.lang}|${s.label}|${s.url}" }
                }
            } else {
                filtered
            }

            val resolvedSelected = if (selected != null) {
                finalList.firstOrNull { it.id == selected.id }
                    ?: finalList.firstOrNull { selected.url.isNotBlank() && it.url == selected.url }
                    // After MediaItem rebuild groupIndex can change, making generated IDs stale.
                    // Fall back to lang+label match for embedded subs so the nonce-based
                    // LaunchedEffect fires with the freshly-indexed version.
                    ?: if (selected.isEmbedded) finalList.firstOrNull {
                        it.isEmbedded && it.lang == selected.lang && it.label == selected.label
                    } else null
                    ?: selected
            } else {
                null
            }

            _uiState.value = _uiState.value.copy(
                subtitles = finalList,
                selectedSubtitle = resolvedSelected
            )

            val currentSel = _uiState.value.selectedSubtitle
            val preferred = getDefaultSubtitle()
            val normalizedPref = normalizeLanguage(preferred)
            val shouldReapply = when {
                currentSel == null -> true
                // AI is active (source track is embedded): re-check any time embedded tracks arrive
                // so a preferred-language built-in that arrives late can displace the AI source.
                _uiState.value.isAiTranslating && finalList.any { it.isEmbedded } -> true
                !currentSel.isEmbedded ->
                    // Non-embedded: re-apply if an embedded in the same lang just arrived
                    finalList.any { sub -> sub.isEmbedded && normalizeLanguage(sub.lang) == normalizeLanguage(currentSel.lang) }
                else ->
                    // Embedded: re-apply if preferred-lang embedded arrived and current is a different
                    // (fallback) language, or if earlier-indexed same-lang embedded arrived.
                    finalList.any { sub ->
                        sub.isEmbedded && (
                            (normalizedPref.isNotBlank() &&
                                normalizeLanguage(sub.lang) == normalizedPref &&
                                normalizeLanguage(currentSel.lang) != normalizedPref) ||
                            (normalizeLanguage(sub.lang) == normalizeLanguage(currentSel.lang) &&
                                (sub.groupIndex ?: Int.MAX_VALUE) < (currentSel.groupIndex ?: Int.MAX_VALUE))
                        )
                    }
            }
            if (shouldReapply) {
                subtitleSelectionJob?.cancel()
                applyPreferredSubtitle(preferred, finalList, currentOriginalLanguage)
            }
        }
    }

    fun selectSubtitle(subtitle: Subtitle) {
        hasManualSubtitleSelection = true
        subtitleSelectionJob?.cancel()
        translationManager.isEnabled = false
        // Keep isAiAvailable/aiTargetLanguageName so the AI entry stays in the menu for re-selection
        _uiState.value = _uiState.value.copy(
            selectedSubtitle = subtitle,
            isAiTranslating = false,
            subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
        )
        recordSubtitleUsage(subtitle)
    }

    fun activateAiSubtitle() {
        val source = aiSourceSubtitle ?: return
        hasManualSubtitleSelection = true
        val targetLangName = _uiState.value.aiTargetLanguageName
        if (targetLangName.isNotBlank()) translationManager.targetLanguage = targetLangName
        translationManager.isEnabled = true
        translationManager.reset()
        aiErrorToastShown = false
        _uiState.value = _uiState.value.copy(
            selectedSubtitle = source,
            isAiTranslating = true,
            isAiAvailable = true,
            aiErrorToast = null
        )
    }

    fun disableSubtitles() {
        hasManualSubtitleSelection = true
        subtitleSelectionJob?.cancel()
        translationManager.isEnabled = false
        aiSourceSubtitle = null
        _uiState.value = _uiState.value.copy(
            selectedSubtitle = null,
            isAiTranslating = false,
            isAiAvailable = false,
            aiTargetLanguageName = "",
            subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
        )
    }

    fun dismissAiErrorToast() {
        _uiState.value = _uiState.value.copy(aiErrorToast = null)
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

                    // Push local CW to cloud so other devices see mid-playback progress.
                    // Throttled to avoid excessive writes during active playback.
                    if (currentTime - lastCloudPushTime >= CLOUD_PUSH_INTERVAL_MS) {
                        lastCloudPushTime = currentTime
                        runCatching { cloudSyncRepository.pushToCloud() }
                    }
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
    private var homeServerAppendJob: Job? = null
    private var subtitleSelectionJob: Job? = null
    private var streamPrewarmJob: Job? = null
    private var focusedStreamPrewarmJob: Job? = null
    private var streamSelectionJob: Job? = null
    private var playbackErrorReportJob: Job? = null
    private var primaryStreamResolutionFinal: Boolean = false
    private var lastTopPrewarmKey: String = ""

    private fun finishSupplementalSourceLookupIfReady(currentJob: Job?, errorMessage: String) {
        val state = _uiState.value
        if (state.streams.isNotEmpty() || !state.selectedStreamUrl.isNullOrBlank()) {
            _uiState.value = state.copy(
                isLoading = false,
                isLoadingStreams = false,
                streamProgress = null,
                streamLoadPhase = null
            )
            return
        }

        val otherSupplementalStillLoading =
            (homeServerAppendJob != null && homeServerAppendJob !== currentJob && homeServerAppendJob?.isActive == true) ||
                (vodAppendJob != null && vodAppendJob !== currentJob && vodAppendJob?.isActive == true)
        if (primaryStreamResolutionFinal && !otherSupplementalStillLoading) {
            _uiState.value = state.copy(
                isLoading = false,
                isLoadingStreams = false,
                streamProgress = null,
                streamLoadPhase = null,
                error = state.error ?: errorMessage,
                isSetupError = false
            )
        }
    }

    private suspend fun appendHomeServerSourcesInBackground(
        mediaType: MediaType,
        imdbId: String,
        seasonNumber: Int?,
        episodeNumber: Int?,
        timeoutMs: Long
    ) {
        val lookupTitle = currentItemTitle
            .ifBlank { currentTitle }
            .ifBlank { mediaRepository.getCachedItem(mediaType, currentMediaId)?.title.orEmpty() }

        val sources = if (mediaType == MediaType.MOVIE) {
            streamRepository.resolveMovieHomeServerSources(
                imdbId = imdbId,
                title = lookupTitle,
                year = null,
                tmdbId = currentMediaId,
                timeoutMs = timeoutMs
            )
        } else {
            streamRepository.resolveEpisodeHomeServerSources(
                imdbId = imdbId,
                season = seasonNumber ?: 1,
                episode = episodeNumber ?: 1,
                title = lookupTitle,
                tmdbId = currentMediaId,
                tvdbId = currentTvdbId,
                timeoutMs = timeoutMs
            )
        }

        val validSources = sources.filter { !it.url.isNullOrBlank() }
        if (validSources.isEmpty()) {
            finishSupplementalSourceLookupIfReady(
                currentJob = currentCoroutineContext()[Job],
                errorMessage = "No streams found for this content. The configured media servers may not have this title."
            )
            return
        }
        val latest = _uiState.value.streams

        val updated = (latest + validSources)
            .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
        val preferredLanguage = _uiState.value.preferredAudioLanguage.ifBlank { "en" }
        val sortedStreams = sortStreamsByQualityAndSize(updated, preferredLanguage)
        val shouldAutoplayHomeServer = _uiState.value.selectedStreamUrl.isNullOrBlank()
        _uiState.value = _uiState.value.copy(
            streams = sortedStreams,
            isLoadingStreams = false,
            error = null,
            isSetupError = false,
            streamProgress = null,
            streamLoadPhase = null
        )
        prewarmTopStreams(sortedStreams, preferredLanguage)
        if (shouldAutoplayHomeServer) {
            pickPreferredStream(sortedStreams, preferredLanguage)?.let { selectStream(it) }
        }
    }

    private suspend fun appendVodSourceInBackground(
        mediaType: MediaType,
        imdbId: String,
        seasonNumber: Int?,
        episodeNumber: Int?,
        timeoutMs: Long
    ) {
        val lookupTitle = currentItemTitle
            .ifBlank { currentTitle }
            .ifBlank { mediaRepository.getCachedItem(mediaType, currentMediaId)?.title.orEmpty() }

        val vodSources = if (mediaType == MediaType.MOVIE) {
            streamRepository.resolveMovieVodSources(
                imdbId = imdbId,
                title = lookupTitle,
                year = null,
                tmdbId = currentMediaId,
                timeoutMs = timeoutMs
            )
        } else {
            streamRepository.resolveEpisodeVodSources(
                imdbId = imdbId,
                season = seasonNumber ?: 1,
                episode = episodeNumber ?: 1,
                title = lookupTitle,
                tmdbId = currentMediaId,
                tvdbId = currentTvdbId,
                timeoutMs = timeoutMs
            )
        }

        val validVodSources = vodSources.filter { !it.url.isNullOrBlank() }
        if (validVodSources.isEmpty()) {
            finishSupplementalSourceLookupIfReady(
                currentJob = currentCoroutineContext()[Job],
                errorMessage = "No streams found for this content. Try another source or check your configured sources."
            )
            return
        }
        val latest = _uiState.value.streams

        val updated = (latest + validVodSources)
            .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
        _uiState.value = _uiState.value.copy(
            streams = updated,
            isLoadingStreams = false,
            error = null,
            isSetupError = false,
            streamProgress = null,
            streamLoadPhase = null
        )
        prewarmTopStreams(updated, _uiState.value.preferredAudioLanguage.ifBlank { "en" })
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

            val existingVod = _uiState.value.streams.filter(::isSupplementalStream)
            val mergedStreams = (allStreams + existingVod)
                .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }

            val selectedMatch = mergedStreams.firstOrNull { stream ->
                isSameStreamUrl(stream.url, playbackUrl)
            }

            val prevSource = _uiState.value.selectedStream?.source.orEmpty()
            val newSource = (selectedMatch ?: _uiState.value.selectedStream)?.source.orEmpty()
            _uiState.value = _uiState.value.copy(
                streams = mergedStreams,
                selectedStream = selectedMatch ?: _uiState.value.selectedStream,
                isLoadingStreams = false
            )
            // Re-run subtitle selection if stream source just became known
            if (prevSource.isBlank() && newSource.isNotBlank()) {
                scheduleSubtitleSelection(currentOriginalLanguage)
            }
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
        private const val TAG = "PlayerViewModel"

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
