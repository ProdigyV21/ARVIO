package com.arflix.tv.ui.screens.player

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import com.arflix.tv.R
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.arflix.tv.BuildConfig
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.EpisodeIdentity
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.HomeServerRepository
import com.arflix.tv.data.repository.PlaybackTelemetryRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.SkipInterval
import com.arflix.tv.data.repository.SkipIntroRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.ui.screens.details.minQualityThreshold
import com.arflix.tv.ui.screens.details.qualityScoreForAutoPlay
import com.arflix.tv.data.repository.isHubCloudPageUrl
import com.arflix.tv.data.repository.providerScopedStreamIdentity
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryEntry
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.util.AnimeMapper
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import com.arflix.tv.util.fallbackAdjacentEpisodeIdentity
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.weightedSubtitleScore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey as globalStringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.time.Clock
import com.arflix.tv.ui.screens.player.subtitles.SubtitleAiModel
import com.arflix.tv.ui.screens.player.subtitles.SubtitleSyncMatcher
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import com.arflix.tv.ui.screens.player.subtitles.TRANSLATION_ERROR_CONTENT_BLOCKED
import com.arflix.tv.ui.screens.player.subtitles.GeminiLiveTranslationService
import com.arflix.tv.ui.screens.player.subtitles.GeminiLiveState
import com.arflix.tv.ui.screens.player.subtitles.AudioCaptureProcessor
import com.arflix.tv.ui.screens.player.common.NextEpisodePromptGate
import com.arflix.tv.ui.screens.player.common.PlaybackEpisodeKey
import javax.inject.Inject

private fun isSupplementalStream(stream: StreamSource): Boolean =
    stream.addonId == "iptv_xtream_vod" || stream.addonId == HomeServerRepository.ADDON_ID

private fun Addon.isVodStreamingAddon(): Boolean =
    isEnabled &&
        type != AddonType.SUBTITLE &&
        !SportsAddonCapabilities.isSportsOnlyLiveTvAddon(this)

private const val PLAYBACK_DIAGNOSTICS = true

// "Preload Subtitles" mode: cap on how many preferred-language subs are downloaded and
// side-loaded at startup (each becomes a MediaItem SubtitleConfiguration read at prepare).
private const val MAX_PRELOAD_SUBS = 15

/**
 * How long a manual subtitle pick waits for its local (correctly decoded) copy before falling back
 * to the remote URL. Generous enough for a normal ~100 KB fetch, short enough that a stalled addon
 * doesn't make the menu feel broken.
 *
 * This bound is only real because `SubtitleSyncMatcher.loadRaw` enqueues its call and cancels it on
 * coroutine cancellation. A blocking `execute()` would ignore the timeout entirely and run on to
 * OkHttp's own connect/read timeouts.
 */
private const val SUBTITLE_LOCALIZE_TIMEOUT_MS = 6_000L

private fun playbackDiag(message: String) {
    if (PLAYBACK_DIAGNOSTICS) {
        System.err.println("[PlaybackDiag] $message")
    }
}

/**
 * A user-facing player message, kept as a resource reference until a composable renders it.
 * A ViewModel only has the application context, whose resources follow the SYSTEM language
 * instead of the language selected in the app, so resolving here would show the wrong
 * language whenever the two differ.
 */
sealed interface PlayerMessage {
    /** A localizable message. [formatArgs] may itself contain [PlayerMessage] entries. */
    data class Res(
        @param:StringRes val resourceId: Int,
        val formatArgs: List<Any> = emptyList()
    ) : PlayerMessage

    /** Text without a resource (e.g. a platform exception message); shown as it is. */
    data class Raw(val text: String) : PlayerMessage
}

data class PlayerUiState(
    val isLoading: Boolean = true,
    val isLoadingStreams: Boolean = false,
    val isLoadingSubtitles: Boolean = false,
    val title: String = "",
    val backdropUrl: String? = null,
    val logoUrl: String? = null,
    val posterUrl: String? = null,
    val streams: List<StreamSource> = emptyList(),
    val addonOrderedIds: List<String> = emptyList(),
    val subtitles: List<Subtitle> = emptyList(),
    val selectedStream: StreamSource? = null,
    val selectedStreamUrl: String? = null,
    val streamSelectionNonce: Int = 0,
    val selectedSubtitle: Subtitle? = null,
    val subtitleSelectionNonce: Int = 0,
    // "Preload Subtitles" mode: preferred-language addon subs downloaded to local files before
    // playback so they can be side-loaded into the initial MediaItem — switching then needs only
    // a track override, not a MediaItem rebuild (no visible video reload). Default ON.
    val subtitlePreloadEnabled: Boolean = true,
    // Dolby Vision compatibility: strip DV P7 metadata so remuxes play as HDR10 on devices
    // without a DV decoder (see com.arflix.tv.player.dv). Default ON; the device policy
    // additionally gates activation to devices that actually need it.
    val dolbyVisionCompatEnabled: Boolean = true,
    // Localized (file://) copies ready to attach. Separate from [subtitles]: menu entries keep
    // their remote URLs so scan/download flows are unaffected.
    val preloadedSubtitles: List<Subtitle> = emptyList(),
    // True once preloading for this video finished (even with zero results) — the prepare gate
    // in PlayerScreen waits on this (with a timeout) before building the MediaItem.
    val subtitlePreloadComplete: Boolean = false,
    // Addon names still being queried for subtitles (preload mode) — shown on the loading
    // screen so a chronically slow addon identifies itself to the user.
    val pendingSubtitleAddons: List<String> = emptyList(),
    val savedPosition: Long = 0,
    val preferredAudioLanguage: String = "en",
    val preferredSubtitleLang: String = "",
    val secondarySubtitleLang: String = "",
    val frameRateMatchingMode: String = "Off",
    val subtitleSize: String = "Medium",
    val subtitleSizePct: Int = 100,
    val subtitleColor: String = "White",
    val subtitleStyle: String = "Bold",
    val subtitleFont: String = SubtitleFontOption.DefaultPreference,
    val subtitleStylized: Boolean = true,
    val subtitleOffset: String = "Bottom",
    val subtitleVerticalPct: Int = 2,
    val filterSubtitlesByLanguage: Boolean = false,
    val subtitleRemoveHearingImpaired: Boolean = false,
    val error: PlayerMessage? = null,
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
    val skipIntervals: List<SkipInterval> = emptyList(),
    val skipIntervalDismissed: Boolean = false,
    // Source-loading progress surfaced to the loading UI. When streams are
    // being resolved progressively, this fills from 0f→1f as addons complete.
    // Null when progress is not meaningful (e.g. trailer loads, cached hits).
    val streamProgress: Float? = null,
    // Human-readable phase label for the loading UI (e.g. "Searching 3/8
    // sources"). Null when progress isn't meaningful.
    val streamLoadPhase: PlayerMessage? = null,
    // True while source discovery can still add playback alternatives. This
    // remains true after autoplay selects the first stream.
    val sourceSearchActive: Boolean = false,
    // True while AI subtitle translation is active for this playback session
    val isAiTranslating: Boolean = false,
    // True when AI translation is available (settings enabled + source track exists), even if not currently active
    val isAiAvailable: Boolean = false,
    // Language name being translated into (e.g. "Hebrew") when AI is available
    val aiTargetLanguageName: String = "",
    // Non-null while an AI translation API error toast should be visible
    val aiErrorToast: PlayerMessage? = null,
    // True while Gemini Live audio translation is active for this session
    val isLiveAudioTranslating: Boolean = false,
    // True while "Find best match" is scanning subtitles; message shown as a transient toast.
    val isFindingBestMatch: Boolean = false,
    val matchToast: PlayerMessage? = null,
    // Persistent status shown on screen for the whole duration of a "Find best match" scan.
    // Null while no scan is running.
    val matchStatus: PlayerMessage? = null,
    // Full name of the preferred subtitle language (e.g. "Hebrew") — drives the "Find Best Match"
    // menu entry. Independent of AI availability: the timing scan needs no AI/API key.
    val matchLanguageName: String = "",
    // Active media metadata fields
    val mediaType: MediaType = MediaType.MOVIE,
    val seasonNumber: Int? = null,
    val episodeNumber: Int? = null,
    // Episode title for TV shows (e.g. "The Devil's Verdict"), populated from TMDB season details
    val episodeTitle: String? = null,
    // Plot synopsis from TMDB, used in the pause overlay metadata block
    val overview: String? = null,
    // Release year extracted from TMDB releaseDate/firstAirDate (e.g. "2023")
    val releaseYear: String? = null,
    // All episodes for the active season (used in Mobile Episodes drawer)
    val seasonEpisodes: List<com.arflix.tv.data.model.Episode> = emptyList(),
    // Auto-skip intro preference
    val autoSkipIntro: Boolean = false,
    // Auto-skip outro preference
    val autoSkipOutro: Boolean = false,
    // Audio delay offset in milliseconds
    val audioDelayMs: Long = 0L,
    // Audio volume normalization preference
    val audioNormalization: Boolean = false
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
    private val remoteSyncManager: com.arflix.tv.data.repository.sync.RemoteSyncManager,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val cloudSyncRepository: CloudSyncRepository,
    private val launcherContinueWatchingRepository: LauncherContinueWatchingRepository,
    private val animeMapper: AnimeMapper,
    private val tmdbApi: TmdbApi,
    private val skipIntroRepository: SkipIntroRepository,
    private val playbackTelemetryRepository: PlaybackTelemetryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    private val nextEpisodeAirDateResolver = NextEpisodeAirDateResolver(
        loadSeason = { tmdbId, seasonNumber ->
            tmdbApi.getTvSeason(tmdbId, seasonNumber, Constants.TMDB_API_KEY)
        },
        clock = Clock.systemDefaultZone(),
    )

    private var currentMediaType: MediaType = MediaType.MOVIE
    private var currentMediaId: Int = 0
    private var currentSeason: Int? = null
    private var currentEpisode: Int? = null
    private var currentDisplaySeason: Int? = null
    private var currentDisplayEpisode: Int? = null
    private var currentAnimeQueryOverride: String? = null
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
    private var currentAddonOrderedIds: List<String> = emptyList()
    private var currentInstalledAddons: List<Addon> = emptyList()
    private var currentIsLiveStreamPlayback: Boolean = false
    private var autoPlayMinimumQuality: Int = 0
    private var lastScrobbleTime: Long = 0
    private var lastWatchHistorySaveTime: Long = 0
    private var lastWatchHistorySavedPositionSeconds: Long = -1L
    private var lastIsPlaying: Boolean = false
    private var hasMarkedWatched: Boolean = false
    private var hasManualSubtitleSelection: Boolean = false
    // True only when the user explicitly picked a subtitle track from the menu (not AI/auto-match).
    // A late-arriving embedded preferred-language track overrides auto selections but never this.
    private var userPickedSubtitle: Boolean = false
    private var playbackSessionStartTime: Long = 0L

    private fun isCurrentAnime(): Boolean =
        currentMediaType == MediaType.TV &&
            currentOriginalLanguage.equals("ja", ignoreCase = true) &&
            currentGenreIds.contains(16)

    suspend fun adjacentEpisodeIdentity(
        tmdbId: Int,
        current: EpisodeIdentity,
        forward: Boolean
    ): EpisodeIdentity? {
        val structure = runCatching { animeMapper.resolveAnimeSeasonStructure(tmdbId) }.getOrNull()
        if (structure != null) {
            return if (forward) {
                structure.nextAfterDisplay(current.displaySeason, current.displayEpisode)
            } else {
                structure.previousBeforeDisplay(current.displaySeason, current.displayEpisode)
            }
        }
        return fallbackAdjacentEpisodeIdentity(current, forward)
    }

    internal suspend fun resolveNextEpisodeAirDate(
        tmdbId: Int,
        target: EpisodeIdentity,
    ): NextEpisodeAirDateResolution = nextEpisodeAirDateResolver.resolve(tmdbId, target)

    // AI subtitle settings (read once per video load)
    private var aiSubtitleEnabled = false
    private var aiSubtitleAutoSelect = false
    // When true (default), activating AI first tries to auto-pick a synced addon subtitle in the
    // user's preferred language (Find Best Match); only if nothing matches does it translate the
    // built-in subtitle.
    private var aiFindBestMatchFirst = false
    // Normalized code of the user's preferred subtitle language (e.g. "he") — the target of both
    // "find best match" and AI translation. Blank when unset/disabled.
    @Volatile private var targetSubtitleLangCode = ""
    // One auto-run of "find best match" per playback/stream — applyPreferredSubtitle re-runs on
    // every subtitle-list update and must not restart a completed scan.
    private var autoMatchAttempted = false
    // "Preload Subtitles" mode (read once per video load, like the AI flags).
    private var subtitlePreloadEnabled = false
    private var subtitlePreloadJob: Job? = null
    private var aiApiKey = ""
    private var aiModel = SubtitleAiModel.GROQ_LLAMA_70B
    private var aiRemoveHearingImpaired = true

    // Global AI subtitle DataStore keys (device-wide, not profile-scoped)
    private val aiEnabledKey = booleanPreferencesKey("subtitle_ai_enabled")
    private val aiAutoSelectKey = booleanPreferencesKey("subtitle_ai_auto_select")
    private val aiFindBestMatchKey = booleanPreferencesKey("subtitle_ai_find_best_match")
    private val subtitlePreloadKey = booleanPreferencesKey("subtitle_preload_enabled")
    private val dolbyVisionCompatPrefKey = booleanPreferencesKey("dolby_vision_compat")
    private val aiApiKeyKey = globalStringPreferencesKey("subtitle_ai_api_key")
    private val aiModelKey = globalStringPreferencesKey("subtitle_ai_model")
    private val aiRemoveHearingImpairedKey = booleanPreferencesKey("subtitle_remove_hearing_impaired")
    // Remembered "find best match" results, keyed by stream identity (sync is a property of the
    // exact video file, not the title — a different rip needs a fresh scan).
    private val subtitleMatchCacheKey = globalStringPreferencesKey("subtitle_match_cache_v1")

    private val _isTranslatingLive = MutableStateFlow(false)
    val isTranslatingLive: kotlinx.coroutines.flow.StateFlow<Boolean> = _isTranslatingLive.asStateFlow()

    // True after the first API error toast has been shown for the current AI session.
    // Reset each time AI translation is (re-)activated so the user sees one toast per session.
    private var aiErrorToastShown = false
    // The source subtitle used for AI translation — retained so the user can re-activate AI after switching away.
    private var aiSourceSubtitle: Subtitle? = null
    // AI sources proven at runtime to carry no text (image tracks whose MIME/label didn't say so).
    // Per-file state: cleared on every new media/stream so it can't leak across sources.
    private val untranslatableSourceIds = mutableSetOf<String>()

    val translationManager: SubtitleTranslationManager = SubtitleTranslationManager(
        service = SubtitleTranslationService(
            apiKeyProvider = { aiApiKey },
            modelProvider = { aiModel }
        ),
        targetLanguage = "",
        scope = viewModelScope
    ).also { mgr ->
        mgr.onTranslatingChanged = { isTranslating -> _isTranslatingLive.value = isTranslating }
        mgr.onUntranslatableSource = { viewModelScope.launch { onAiSourceUntranslatable() } }
        mgr.onBatchResult = { success, errorMessage ->
            // Content-policy blocks are not actionable (Gemini's non-configurable output filter
            // on raw movie dialogue) and don't stop translation of other windows — the service
            // already bisected the batch to salvage what it could. Don't consume the one toast
            // per session on them; keep it for real errors (bad key, rate limit).
            if (!success && errorMessage == TRANSLATION_ERROR_CONTENT_BLOCKED) {
                android.util.Log.w("SubtitleTranslation", "batch content-blocked — toast suppressed")
            } else if (!success && !aiErrorToastShown) {
                aiErrorToastShown = true
                val msg = when {
                    errorMessage == "API key missing" -> PlayerMessage.Res(R.string.player_ai_no_key)
                    errorMessage == "RATE_LIMITED"    -> PlayerMessage.Res(R.string.player_ai_rate_limited)
                    errorMessage?.startsWith("HTTP 401") == true -> PlayerMessage.Res(R.string.player_ai_invalid_key)
                    else -> PlayerMessage.Res(
                        R.string.player_ai_translation_error,
                        listOf(errorMessage.orEmpty())
                    )
                }
                _uiState.value = _uiState.value.copy(aiErrorToast = msg)
            }
        }
    }

    val geminiLiveService: GeminiLiveTranslationService = GeminiLiveTranslationService(
        apiKeyProvider = { aiApiKey },
        scope = viewModelScope
    )
    val liveAudioText = geminiLiveService.translatedText
    val liveAudioState = geminiLiveService.state

    // Latest playback media position, used to time-align AI-hearing samples with subtitle cues.
    @Volatile private var lastKnownPositionMs = 0L

    private var findMatchJob: Job? = null

    // Built-in reference collection: while active, rendered cues of the selected (built-in English)
    // track are turned into cue-visible intervals used as the sync reference.
    @Volatile private var collectingReference = false
    @Volatile private var refIntervalStart = -1L
    private val referenceIntervals = mutableListOf<Pair<Long, Long>>()

    /** Set by the player screen: reads the selected text track's already-buffered cue intervals. */
    @Volatile var bufferedReferenceIntervalsProvider: ((Int) -> List<Pair<Long, Long>>)? = null

    /** Set by the player screen: reads the selected text track's already-buffered cue texts. */
    @Volatile var bufferedCueTextsProvider: ((Int) -> List<String>)? = null

    // Normalized lines of the candidate displayed when the scan started — set for the duration of
    // reference collection so BOTH the buffered and the realtime paths can reject self-cues (the
    // reference track switch landing late/never would otherwise score a candidate against itself).
    @Volatile private var matchPreviousTexts: Set<String>? = null
    @Volatile private var refIntervalSelfText = false

    /** Rendered-cue callback from the player; records reference intervals during a built-in scan. */
    fun onPlayerCues(hasText: Boolean, timeMs: Long, textSample: String? = null) {
        if (!collectingReference || timeMs < 0) return
        if (hasText) {
            if (refIntervalStart < 0) {
                refIntervalStart = timeMs
                refIntervalSelfText = false
            }
            val prev = matchPreviousTexts
            if (prev != null && textSample != null &&
                normalizeCueTextForCompare(textSample) in prev
            ) {
                refIntervalSelfText = true
            }
        } else if (refIntervalStart in 0 until timeMs) {
            // An interval spanning a forward seek would record a bogus minutes-long "cue";
            // real cues never stay on screen this long — drop it.
            val validLength = timeMs - refIntervalStart <= MATCH_MAX_REF_INTERVAL_MS
            if (validLength && !refIntervalSelfText) {
                synchronized(referenceIntervals) { referenceIntervals.add(refIntervalStart to timeMs) }
            } else if (refIntervalSelfText) {
                android.util.Log.w(
                    "SubMatch",
                    "realtime self-cue interval dropped ${refIntervalStart}..$timeMs — reference track switch not landed?"
                )
            }
            refIntervalStart = -1L
        } else if (refIntervalStart >= 0) {
            // Position jumped backwards past the interval start (seek) — discard the open interval.
            refIntervalStart = -1L
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
    private val WATCH_HISTORY_UPDATE_INTERVAL_MS = 60_000L
    private val CLOUD_PUSH_INTERVAL_MS = 5 * 60_000L // Push CW to cloud occasionally during active playback

    private var lastCloudPushTime = 0L

    private fun defaultSubtitleKey() = profileManager.profileStringKey("default_subtitle")
    private fun defaultAudioLanguageKey() = profileManager.profileStringKey("default_audio_language")
    private fun subtitleUsageKey() = profileManager.profileStringKey("subtitle_usage_v1")
    private fun filterSubtitlesByLanguageKey() = profileManager.profileBooleanKey("filter_subtitles_by_lang")
    private fun secondarySubtitleKey() = profileManager.profileStringKey("secondary_subtitle")
    private val subtitleMenuCandidates = linkedMapOf<String, Subtitle>()
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
        displaySeasonNumber: Int? = seasonNumber,
        displayEpisodeNumber: Int? = episodeNumber,
        animeQueryOverride: String? = null,
        providedImdbId: String?,
        providedStreamUrl: String?,
        preferredAddonId: String?,
        preferredSourceName: String?,
        preferredBingeGroup: String?,
        startPositionMs: Long?,
        isLiveStreamPlayback: Boolean = false,
        forceRefresh: Boolean = false,
        airDate: String? = null
    ) {
        currentAirDate = airDate
        currentMediaType = mediaType
        currentMediaId = mediaId
        currentSeason = seasonNumber
        currentEpisode = episodeNumber
        currentDisplaySeason = displaySeasonNumber
        currentDisplayEpisode = displayEpisodeNumber
        currentAnimeQueryOverride = animeQueryOverride
        currentStartPositionMs = startPositionMs
        currentPreferredAddonId = preferredAddonId?.trim()?.takeIf { it.isNotBlank() }
        currentPreferredSourceName = preferredSourceName?.trim()?.takeIf { it.isNotBlank() }
        currentPreferredBingeGroup = preferredBingeGroup?.trim()?.takeIf { it.isNotBlank() }
        currentIsLiveStreamPlayback = isLiveStreamPlayback
        autoPlayMinimumQuality = 0
        playbackSessionStartTime = System.currentTimeMillis()
        playbackDiag(
            "loadMedia type=$mediaType id=$mediaId season=$seasonNumber episode=$episodeNumber " +
                "providedUrl=${!providedStreamUrl.isNullOrBlank()} preferredAddon=${currentPreferredAddonId.orEmpty()} " +
                "preferredSource=${currentPreferredSourceName.orEmpty().take(120)}"
        )
        currentEpisodeTitle = null
        hasMarkedWatched = false
        lastIsPlaying = false
        lastScrobbleTime = 0
        lastWatchHistorySaveTime = 0
        lastWatchHistorySavedPositionSeconds = -1L
        subtitleRefreshJob?.cancel()
        subtitleSelectionJob?.cancel()
        cancelSubtitleLocalization()
        subtitlePreloadJob?.cancel()
        // Cancel any in-flight "Find best match" scan from the previous title — otherwise it can
        // finish on this new session and select/restore a stale subtitle or poison the match cache
        // (its stream cache key resolves to the new stream). Same-VM path only, e.g. play-next.
        cancelFindBestMatch()
        vodAppendJob?.cancel()
        homeServerAppendJob?.cancel()
        streamPrewarmJob?.cancel()
        focusedStreamPrewarmJob?.cancel()
        streamSelectionJob?.cancel()
        playbackErrorReportJob?.cancel()
        primaryStreamResolutionFinal = false
        hasManualSubtitleSelection = false
        userPickedSubtitle = false
        autoMatchAttempted = false
        aiSourceSubtitle = null
        val cachedItem = mediaRepository.getCachedItem(mediaType, mediaId)
        val cachedLogoUrl = mediaRepository.peekCachedLogoUrl(mediaType, mediaId)
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            isLoadingStreams = false,
            selectedSubtitle = null,
            // A new video means a new subtitle universe — stale externals from the previous
            // title poisoned both the picker and the match scan (references from the new video
            // scored against the old video's candidates).
            subtitles = emptyList(),
            isAiTranslating = false,
            isAiAvailable = false,
            aiTargetLanguageName = "",
            logoUrl = cachedLogoUrl,
            mediaType = mediaType,
            seasonNumber = seasonNumber,
            episodeNumber = episodeNumber,
            streamProgress = null,
            streamLoadPhase = if (providedStreamUrl.isNullOrBlank()) null else PlayerMessage.Res(R.string.player_phase_preparing_stream),
            sourceSearchActive = false,
            error = null,
            isSetupError = false
        )
        lastTopPrewarmKey = ""
        skipIntervalsJob?.cancel()
        currentImdbId = providedImdbId
        skipIntervals = emptyList()
        subtitleMenuCandidates.clear()
        lastActiveSkipType = null
        activeSkipRequestKey = null
        _uiState.value = _uiState.value.copy(activeSkipInterval = null, skipIntervals = emptyList(), skipIntervalDismissed = false)
        currentOriginalLanguage = cachedItem?.originalLanguage
        currentGenreIds = cachedItem?.genreIds ?: emptyList()
        currentItemTitle = cachedItem?.title ?: ""

        mediaLoadJob?.cancel()
        mediaLoadJob = viewModelScope.launch {
            // Autoplay should always use the current highest-ranked source list.
            // Explicit source navigation still passes preferred fields or a URL below.
            val preferredAudioLanguage = resolvePreferredAudioLanguage()
            val frameRateMatchingMode = resolveFrameRateMatchingMode()
            val prefs = context.settingsDataStore.data.first()
            // A manually selected URL is explicit consent to that source's quality.
            autoPlayMinimumQuality = if (providedStreamUrl.isNullOrBlank()) {
                minQualityThreshold(prefs[profileManager.profileStringKey("auto_play_min_quality")] ?: "Any")
            } else 0
            val subSize = prefs[profileManager.profileStringKey("subtitle_size")] ?: "Medium"
            val subSizePct = prefs[profileManager.profileIntKey("subtitle_size_pct")] ?: when (subSize.lowercase()) {
                "small" -> 80
                "large" -> 120
                "extra large" -> 140
                else -> 100
            }
            val subColor = prefs[profileManager.profileStringKey("subtitle_color")] ?: "White"
            val subStyle = prefs[profileManager.profileStringKey("subtitle_style")] ?: "Bold"
            val subFont = SubtitleFontOption.fromPreference(
                prefs[profileManager.profileStringKey("subtitle_font")]
            ).preferenceValue
            val subStylized = prefs[profileManager.profileBooleanKey("subtitle_stylized")] ?: true
            val subOffset = prefs[profileManager.profileStringKey("subtitle_offset")] ?: "Bottom"
            val subVertPct = prefs[profileManager.profileIntKey("subtitle_vertical_pct")] ?: when (subOffset) {
                "Bottom" -> 2; "Low" -> 8; "Medium" -> 15; "High" -> 25; else -> 2
            }
            val filterSubLang = prefs[filterSubtitlesByLanguageKey()] ?: true
            val removeHi = prefs[profileManager.profileBooleanKey("subtitle_remove_hearing_impaired")] ?: false
            translationManager.removeSubtitleHearingImpaired = removeHi
            val autoPlayNext = prefs[autoPlayNextKey()] ?: true
            val showLoadingStats = prefs[showLoadingStatsKey()] ?: true
            val volumeBoostDb = prefs[profileManager.profileStringKey("volume_boost_db")]
                ?.toIntOrNull()?.coerceIn(0, 15) ?: 0
            val installedAddons = streamRepository.installedAddons.first()
            currentInstalledAddons = installedAddons
            val orderedAddonIds = installedAddons
                .filter { it.isVodStreamingAddon() }
                .map { it.id }
            currentAddonOrderedIds = orderedAddonIds
            val preferredSub = prefs[defaultSubtitleKey()]?.trim().orEmpty()
                .let { if (isSubtitleDisabledPreference(it)) "" else it }
            val secondarySub = prefs[secondarySubtitleKey()]?.trim().orEmpty()
                .let { if (isSubtitleDisabledPreference(it)) "" else it }
            setTargetSubtitleLang(normalizeLanguage(preferredSub))

            // Load AI subtitle settings
            aiSubtitleEnabled = prefs[aiEnabledKey] ?: false
            aiSubtitleAutoSelect = prefs[aiAutoSelectKey] ?: false
            aiFindBestMatchFirst = prefs[aiFindBestMatchKey] ?: false
            subtitlePreloadEnabled = prefs[subtitlePreloadKey] ?: true
            aiApiKey = prefs[aiApiKeyKey] ?: ""
            aiModel = runCatching {
                SubtitleAiModel.valueOf(prefs[aiModelKey] ?: SubtitleAiModel.GROQ_LLAMA_70B.name)
            }.getOrDefault(SubtitleAiModel.GROQ_LLAMA_70B)
            aiRemoveHearingImpaired = prefs[aiRemoveHearingImpairedKey] ?: true
            translationManager.updateService(apiKey = aiApiKey, model = aiModel)
            translationManager.removeHearingImpaired = aiRemoveHearingImpaired
            translationManager.isEnabled = false
            untranslatableSourceIds.clear()
            translationManager.reset()

            val autoSkipIntro = prefs[profileManager.profileBooleanKey("auto_skip_intro")] ?: false
            val autoSkipOutro = prefs[profileManager.profileBooleanKey("auto_skip_outro")] ?: false
            val audioDelayMs = prefs[profileManager.profileLongKey("audio_delay_ms")] ?: 0L
            val audioNormalization = prefs[profileManager.profileBooleanKey("audio_normalization")] ?: false

            _uiState.value = PlayerUiState(
                mediaType = mediaType,
                seasonNumber = seasonNumber,
                episodeNumber = episodeNumber,
                isLoading = true,
                isLoadingStreams = true,
                sourceSearchActive = true,
                streamLoadPhase = if (providedStreamUrl.isNullOrBlank()) null else PlayerMessage.Res(R.string.player_phase_preparing_stream),
                title = cachedItem?.title ?: currentItemTitle,
                backdropUrl = cachedItem?.backdrop?.takeIf { it.isNotBlank() }
                    ?: cachedItem?.image?.takeIf { it.isNotBlank() },
                logoUrl = cachedLogoUrl,
                addonOrderedIds = orderedAddonIds,
                preferredAudioLanguage = preferredAudioLanguage,
                preferredSubtitleLang = preferredSub,
                secondarySubtitleLang = secondarySub,
                frameRateMatchingMode = frameRateMatchingMode,
                subtitleSize = subSize,
                subtitleSizePct = subSizePct,
                subtitleColor = subColor,
                subtitleStyle = subStyle,
                subtitleFont = subFont,
                subtitleStylized = subStylized,
                subtitleOffset = subOffset,
                subtitleVerticalPct = subVertPct,
                filterSubtitlesByLanguage = filterSubLang,
                subtitleRemoveHearingImpaired = removeHi,
                autoPlayNext = autoPlayNext,
                autoSkipIntro = autoSkipIntro,
                autoSkipOutro = autoSkipOutro,
                audioDelayMs = audioDelayMs,
                audioNormalization = audioNormalization,
                showLoadingStats = showLoadingStats,
                volumeBoostDb = volumeBoostDb,
                subtitlePreloadEnabled = subtitlePreloadEnabled,
                dolbyVisionCompatEnabled = prefs[dolbyVisionCompatPrefKey] ?: true,
                // Nothing to preload without a preferred language — don't make the gate wait.
                subtitlePreloadComplete = normalizeLanguage(preferredSub).isBlank()
            )

            val providedIsMagnet = providedStreamUrl?.startsWith("magnet:", ignoreCase = true) == true
            val providedStreamCandidate = providedStreamUrl
                ?.takeUnless { providedIsMagnet }
                ?.let { url ->
                    StreamSource(
                        source = currentPreferredSourceName ?: "Selected source",
                        addonName = currentPreferredAddonId ?: "",
                        addonId = currentPreferredAddonId.orEmpty(),
                        quality = "",
                        size = "",
                        url = url
                    )
                }
            val providedIsHubPage = providedStreamCandidate?.url
                ?.let(::isHubCloudPageUrl) == true
            val preResolvedHubStream = if (providedIsHubPage) {
                _uiState.value = _uiState.value.copy(streamLoadPhase = PlayerMessage.Res(R.string.player_phase_preparing_stream))
                providedStreamCandidate?.let { stream ->
                    runCatching { streamRepository.resolveStreamForPlayback(stream) }.getOrNull()
                }
            } else {
                null
            }
            if (providedIsHubPage && preResolvedHubStream == null) {
                AppLogger.breadcrumb(
                    tag = "Sources",
                    message = "provided_hubcloud_unresolved_falling_back_to_source_search",
                    severity = "warning"
                )
            }
            val effectiveProvidedStreamUrl = when {
                preResolvedHubStream != null -> preResolvedHubStream.url
                providedIsHubPage -> null
                else -> providedStreamUrl
            }

            // If a playable stream URL was provided, use it directly. An unresolved HubCloud
            // page deliberately falls through to normal source discovery instead of playing HTML.
            if (effectiveProvidedStreamUrl != null) {
                val resumeData = resolveResumeData(
                    mediaType = mediaType,
                    mediaId = mediaId,
                    seasonNumber = seasonNumber,
                    episodeNumber = episodeNumber,
                    navigationStartPositionMs = startPositionMs
                )
                val isMagnet = providedIsMagnet
                val providedStream = preResolvedHubStream ?: providedStreamCandidate
                // Show a status while the debrid/source link resolves. Without this the initial-play
                // path sat 5-10s with no overlay text (selectedStreamUrl not set yet, so startupPhase
                // is gated off), unlike the manual selectStream() path which already labels this step.
                if (providedStream != null) {
                    _uiState.value = _uiState.value.copy(streamLoadPhase = PlayerMessage.Res(R.string.player_phase_preparing_stream))
                }
                val resolvedProvidedStream = preResolvedHubStream ?: providedStream?.let { stream ->
                    runCatching { streamRepository.resolveStreamForPlayback(stream) }.getOrNull() ?: stream
                }
                val resolvedProvidedUrl = resolvedProvidedStream?.url
                    ?: if (isMagnet) null else effectiveProvidedStreamUrl
                playbackDiag(
                    "providedStream resolved=${streamDiag(resolvedProvidedStream)} " +
                        "host=${hostFromUrl(resolvedProvidedUrl)}"
                )

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
                        sourceSearchActive = false,
                        error = if (isMagnet) {
                            PlayerMessage.Res(R.string.player_error_magnet_unsupported)
                        } else {
                            PlayerMessage.Res(R.string.player_error_open_source_failed)
                        }
                    )
                    return@launch
                }

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    sourceSearchActive = true,
                    streams = listOfNotNull(resolvedProvidedStream),
                    selectedStream = resolvedProvidedStream,
                    selectedStreamUrl = resolvedProvidedUrl,
                    savedPosition = resumeData.positionMs
                )
                // NOTE: these background children share the load job — an uncaught exception in
                // any of them cancels ALL siblings (that silently killed the subtitle flow).
                // Every body is therefore failure-isolated and logged.
                fun childFailed(name: String): (Throwable) -> Unit = { e ->
                    if (e !is kotlinx.coroutines.CancellationException) {
                        android.util.Log.w("PlayerViewModel", "load child '$name' failed: ${e.message}")
                    }
                }
                launch {
                    kotlinx.coroutines.delay(1_500L)
                    runCatching {
                        populateStreamsForProvidedUrl(
                            mediaType = mediaType,
                            mediaId = mediaId,
                            seasonNumber = seasonNumber,
                            episodeNumber = episodeNumber,
                            providedImdbId = providedImdbId,
                            playbackUrl = resolvedProvidedUrl
                        )
                    }.onFailure(childFailed("populateStreams"))
                }
                // Load IPTV and home-server sources from cache in parallel so the
                // source picker in the player shows alternatives immediately.
                homeServerAppendJob?.cancel()
                homeServerAppendJob = launch {
                    runCatching {
                        appendHomeServerSourcesInBackground(
                            mediaType = mediaType,
                            imdbId = currentImdbId,
                            seasonNumber = seasonNumber,
                            episodeNumber = episodeNumber,
                            timeoutMs = 5_000L
                        )
                    }.onFailure(childFailed("homeServerAppend"))
                }
                vodAppendJob?.cancel()
                vodAppendJob = launch {
                    runCatching {
                        appendVodSourceInBackground(
                            mediaType = mediaType,
                            imdbId = currentImdbId,
                            seasonNumber = seasonNumber,
                            episodeNumber = episodeNumber,
                            timeoutMs = 15_000L
                        )
                    }.onFailure(childFailed("vodAppend"))
                }
                // Fetch metadata in background
                launch {
                    runCatching { fetchMediaMetadata(mediaType, mediaId) }
                        .onFailure(childFailed("metadata"))
                }
                // Fetch skip intervals in background (needs IMDB id)
                launch {
                    runCatching {
                        if (mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null) {
                            val cachedImdbId = currentImdbId ?: mediaRepository.getCachedImdbId(mediaType, mediaId)
                            val imdbId = cachedImdbId ?: resolveExternalIds(mediaType, mediaId).imdbId
                            if (!imdbId.isNullOrBlank()) {
                                currentImdbId = imdbId
                                if (cachedImdbId == null) mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
                                fetchSkipIntervals(imdbId, seasonNumber, episodeNumber)
                            }
                        }
                    }.onFailure(childFailed("skipIntervals"))
                }
                // Direct-URL playback must still fetch subtitle addons (e.g. OpenSubtitles).
                // On viewModelScope (NOT a child of this load job): sibling children above
                // (VOD/home-server/stream appends) occasionally throw, which cancels the whole
                // load job and — because this block is registered last — killed the subtitle
                // flow before its first line ran: no fetch, no selection, playback with
                // subtitles silently Off. loadMedia cancels subtitleRefreshJob, so lifecycle
                // stays correct across videos.
                subtitleRefreshJob?.cancel()
                subtitleRefreshJob = viewModelScope.launch {
                    _uiState.value = _uiState.value.copy(isLoadingSubtitles = true)
                    val cachedImdbId = currentImdbId ?: mediaRepository.getCachedImdbId(mediaType, mediaId)
                    val imdbId = cachedImdbId
                        ?: runCatching { resolveExternalIds(mediaType, mediaId).imdbId }.getOrNull()

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
                                stream = null,
                                softDeadlineMs = subtitleFetchSoftDeadline(),
                                onPendingAddons = pendingSubtitleAddonsReporter()
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
                        preloadSubtitles(mergedSubs)

                        scheduleSubtitleSelection(currentOriginalLanguage)
                    } else {
                        // No IMDb id → addon subs can't be fetched, but the selection flow must
                        // still run: embedded tracks and the AI option exist regardless. Silently
                        // stopping here left playback with NOTHING selected ("Off") and no scan.
                        android.util.Log.w(
                            "SubMatch",
                            "subtitle fetch skipped: no imdbId for $mediaType/$mediaId — scheduling selection with embedded-only"
                        )
                        _uiState.value = _uiState.value.copy(isLoadingSubtitles = false)
                        // No addon subs will arrive — unblock the preload prepare gate.
                        preloadSubtitles(emptyList())
                        scheduleSubtitleSelection(currentOriginalLanguage)
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
                val effectiveStreamId: String = when {
                    !imdbId.isNullOrBlank() -> imdbId
                    mediaId > 0 -> "tmdb:$mediaId"
                    else -> {
                        AppLogger.recordException(
                            throwable = IllegalStateException("Playback IMDB ID missing"),
                            context = playbackDiagnosticContext("missing_imdb_id")
                        )
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            isLoadingStreams = false,
                            sourceSearchActive = false,
                            error = PlayerMessage.Res(R.string.player_error_imdb_resolve)
                        )
                        return@launch
                    }
                }
                if (!imdbId.isNullOrBlank() && cachedImdbId.isNullOrBlank()) {
                    mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
                }
                currentImdbId = imdbId
                // Never block source loading on title hydration from TMDB.
                // Fetch skip intervals in background. This should never block playback.
                launch {
                    if (mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null && !imdbId.isNullOrBlank()) {
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
                    .count { it.isVodStreamingAddon() }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = true,
                    sourceSearchActive = true,
                    savedPosition = resumeData.positionMs,
                    error = null,
                    isSetupError = false,
                    streamProgress = 0f,
                    streamLoadPhase = if (streamingAddonCount > 0) {
                        PlayerMessage.Res(
                            R.string.player_phase_searching_sources,
                            listOf(0, streamingAddonCount)
                        )
                    } else {
                        PlayerMessage.Res(R.string.player_phase_preparing_sources)
                    }
                )

                val preferredLanguage = _uiState.value.preferredAudioLanguage.ifBlank { resolvePreferredAudioLanguage() }
                val progressiveFlow = if (mediaType == MediaType.MOVIE) {
                    streamRepository.resolveMovieStreamsProgressive(
                        imdbId = effectiveStreamId,
                        title = currentItemTitle,
                        year = null,
                        forceRefresh = forceRefresh
                    )
                } else {
                    streamRepository.resolveEpisodeStreamsProgressive(
                        imdbId = effectiveStreamId,
                        season = seasonNumber ?: 1,
                        episode = episodeNumber ?: 1,
                        tmdbId = mediaId,
                        tvdbId = currentTvdbId,
                        genreIds = currentGenreIds,
                        originalLanguage = currentOriginalLanguage,
                        title = currentItemTitle,
                        forceRefresh = forceRefresh,
                        animeQueryOverride = animeQueryOverride,
                        airDate = currentAirDate
                    )
                }

                // Collect progressive emissions. Give cached/debrid-ready results
                // a short grace period to avoid false low-quality picks, then
                // force-select once quality/size looks stable.
                val hasHomeServerConnections = streamRepository.hasHomeServerConnections()
                val HOME_SERVER_AUTOPLAY_WAIT_MS = 850L
                val AUTOPLAY_MAX_WINDOW_MS = 1_750L
                val AUTOPLAY_QUALITY_WINDOW_MS = 180L
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
                    val activeStreamList = listOfNotNull(_uiState.value.selectedStream)
                    val mergedStreams = sortStreamsByQualityAndSize(
                        (allStreams + existingVod + activeStreamList)
                            .distinctBy(::providerScopedStreamIdentity),
                        preferredLanguage
                    )
                    lastMergedStreams = mergedStreams
                    val autoplayStreams = eligiblePlayerAutoplayStreams(mergedStreams, autoPlayMinimumQuality)

                    val supplementalSourcesStillLoading =
                        homeServerAppendJob?.isActive == true || vodAppendJob?.isActive == true
                    val availability = playerAutoplayAvailability(
                        streams = mergedStreams,
                        minimumQuality = autoPlayMinimumQuality,
                        searchActive = !progressive.isFinal || supplementalSourcesStillLoading,
                        hasSelection = !canStartAutoplay()
                    )
                    val errorMessage = when (availability) {
                        PlayerAutoplayAvailability.SELECTED -> _uiState.value.error
                        PlayerAutoplayAvailability.NO_MATCH -> PlayerMessage.Res(R.string.stream_no_sources_match)
                        PlayerAutoplayAvailability.NO_SOURCES -> when {
                            streamingAddonCount == 0 && !hasHomeServerConnections ->
                                PlayerMessage.Res(R.string.player_error_no_streaming_addons)
                            hasHomeServerConnections -> PlayerMessage.Res(R.string.player_error_no_streams_media_servers)
                            else -> PlayerMessage.Res(R.string.player_error_no_streams_from_addons)
                        }
                        else -> null
                    }
                    if (errorMessage != null && !sourceEmptyReported && mergedStreams.isEmpty()) {
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
                        mergedStreams.isNotEmpty() -> PlayerMessage.Res(
                            R.string.player_phase_found_sources,
                            listOf(mergedStreams.size, completed, total)
                        )
                        else -> PlayerMessage.Res(
                            R.string.player_phase_searching_sources,
                            listOf(completed, total)
                        )
                    }
                    val filteredSubtitles = filterSubsByPreferredLanguage(progressive.subtitles)

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoadingStreams = availability == PlayerAutoplayAvailability.SEARCHING,
                        sourceSearchActive = !progressive.isFinal || supplementalSourcesStillLoading,
                        streams = mergedStreams,
                        subtitles = filteredSubtitles,
                        error = errorMessage,
                        isSetupError = availability == PlayerAutoplayAvailability.NO_SOURCES &&
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
                    if (!autoplaySelected && autoplayStreams.isNotEmpty() && autoplayDeferredJob == null && canStartAutoplay()) {
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
                                            .distinctBy(::providerScopedStreamIdentity),
                                        preferredLanguage
                                    )
                                }
                                if (eligiblePlayerAutoplayStreams(snapshot, autoPlayMinimumQuality).isNotEmpty()) {
                                    autoplaySelected = true
                                    Log.i(
                                        TAG,
                                        "Autoplay selecting after quality window streams=${snapshot.size} elapsedMs=${System.currentTimeMillis() - collectionStartMs}"
                                    )
                                    playbackDiag(
                                        "autoplayDeferred streams=${snapshot.size} " +
                                            "top=${snapshot.take(5).joinToString(" | ") { streamDiag(it) }}"
                                    )
                                    autoplaySelectBest(snapshot, preferredLanguage)
                                }
                            }
                        }
                    }
                    val autoplayTopStream = pickAutoplayTopStream(autoplayStreams, preferredLanguage)
                    val hasRequestedPreferredStream = hasRequestedPreferredStream(autoplayStreams)
                    val shouldSelectNow = !autoplaySelected && autoplayStreams.isNotEmpty() && canStartAutoplay() && homeServerReadyForAutoplay && (
                        cacheHit ||
                            progressive.isFinal ||
                            hasCachedReadyStream ||
                            hasRequestedPreferredStream ||
                            isExcellentAutoplayCandidate(autoplayTopStream) ||
                            elapsedMs >= AUTOPLAY_MAX_WINDOW_MS
                        )

                    if (shouldSelectNow) {
                        autoplaySelected = true
                        autoplayDeferredJob?.cancel()
                        autoplayDeferredJob = null
                        Log.i(
                            TAG,
                            "Autoplay selecting streams=${mergedStreams.size} completed=$completed/$total final=${progressive.isFinal} cached=$hasCachedReadyStream preferred=$hasRequestedPreferredStream elapsedMs=$elapsedMs top=${autoplayTopStream?.quality}/${autoplayTopStream?.size}"
                        )
                        playbackDiag(
                            "autoplayNow streams=${mergedStreams.size} completed=$completed/$total final=${progressive.isFinal} " +
                                "cached=$hasCachedReadyStream preferred=$hasRequestedPreferredStream top=${autoplayTopStream?.let { streamDiag(it) } ?: "none"}"
                        )
                        autoplaySelectBest(autoplayStreams, preferredLanguage)
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
                                .distinctBy(::providerScopedStreamIdentity),
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
                            imdbId = effectiveStreamId,
                            season = seasonNumber,
                            episode = episodeNumber,
                            stream = null,
                            softDeadlineMs = subtitleFetchSoftDeadline(),
                            onPendingAddons = pendingSubtitleAddonsReporter()
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
                    preloadSubtitles(mergedSubs)
                    scheduleSubtitleSelection(currentOriginalLanguage)
                }

            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e

                AppLogger.recordException(
                    throwable = e,
                    context = playbackDiagnosticContext("load_media_exception")
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    sourceSearchActive = false,
                    streamProgress = null,
                    streamLoadPhase = null,
                    error = e.message?.let { PlayerMessage.Raw(it) }
                )
            }
        }
    }

    /**
     * Fetch media metadata in background (non-blocking)
     */
    private suspend fun loadPlayerSeasonEpisodes(mediaId: Int, displaySeason: Int): List<com.arflix.tv.data.model.Episode> {
        val structure = if (isCurrentAnime()) animeMapper.resolveAnimeSeasonStructure(mediaId) else null
        val identities = structure?.seasons?.get(displaySeason)
            ?: return mediaRepository.getSeasonEpisodes(mediaId, displaySeason)
        val bySeason = identities.map { it.tmdbSeason }.distinct().associateWith { season ->
            mediaRepository.getSeasonEpisodes(mediaId, season).associateBy { it.episodeNumber }
        }
        return identities.mapNotNull { identity ->
            bySeason[identity.tmdbSeason]?.get(identity.tmdbEpisode)?.copy(
                seasonNumber = identity.displaySeason,
                episodeNumber = identity.displayEpisode,
                identity = identity,
            )
        }
    }

    private suspend fun fetchMediaMetadata(mediaType: MediaType, mediaId: Int) {
        try {
            val details = if (mediaType == MediaType.TV) {
                tmdbApi.getTvDetails(mediaId, Constants.TMDB_API_KEY)
            } else {
                tmdbApi.getMovieDetails(mediaId, Constants.TMDB_API_KEY)
            }

            val logoUrl = try {
                mediaRepository.getLogoUrl(mediaType, mediaId)
            } catch (e: Exception) { if (e is kotlinx.coroutines.CancellationException) throw e
 null }

            val title: String
            val backdropUrl: String?
            val posterUrl: String?
            var overview: String? = null
            var releaseYear: String? = null
            var fetchedSeasonEpisodes = emptyList<com.arflix.tv.data.model.Episode>()

            if (mediaType == MediaType.TV) {
                val tvDetails = details as com.arflix.tv.data.api.TmdbTvDetails
                title = tvDetails.name
                backdropUrl = tvDetails.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                posterUrl = tvDetails.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                currentOriginalLanguage = tvDetails.originalLanguage ?: currentOriginalLanguage
                overview = tvDetails.overview
                releaseYear = tvDetails.firstAirDate?.take(4)

                // Keep episode title aligned with saved progress rows for TV playback sessions.
                val season = currentSeason
                val episode = currentEpisode
                if (season != null && episode != null) {
                    val episodeDetails = runCatching {
                        val seasonDetails = tmdbApi.getTvSeason(mediaId, season, Constants.TMDB_API_KEY)
                        seasonDetails.episodes.firstOrNull { it.episodeNumber == episode }
                    }.getOrNull()
                    currentEpisodeTitle = episodeDetails?.name?.takeIf { it.isNotBlank() }
                    overview = episodeDetails?.overview?.takeIf { it.isNotBlank() } ?: overview
                    fetchedSeasonEpisodes = try {
                        loadPlayerSeasonEpisodes(mediaId, season)
                    } catch (e: kotlinx.coroutines.CancellationException) {
                        throw e
                    } catch (_: Exception) {
                        emptyList()
                    }
                }
            } else {
                val movieDetails = details as com.arflix.tv.data.api.TmdbMovieDetails
                title = movieDetails.title
                backdropUrl = movieDetails.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                posterUrl = movieDetails.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                currentOriginalLanguage = movieDetails.originalLanguage ?: currentOriginalLanguage
                overview = movieDetails.overview
                releaseYear = movieDetails.releaseDate?.take(4)
            }

            // Store info for watch history
            currentTitle = title
            currentPoster = posterUrl
            currentBackdrop = backdropUrl

            // Update UI with metadata
            _uiState.value = _uiState.value.copy(
                title = title,
                backdropUrl = backdropUrl,
                logoUrl = logoUrl ?: _uiState.value.logoUrl,
                posterUrl = posterUrl,
                mediaType = mediaType,
                seasonNumber = currentSeason,
                episodeNumber = currentEpisode,
                episodeTitle = currentEpisodeTitle,
                overview = overview,
                releaseYear = releaseYear,
                seasonEpisodes = if (mediaType == MediaType.TV && fetchedSeasonEpisodes.isNotEmpty()) fetchedSeasonEpisodes else _uiState.value.seasonEpisodes,
                preferredAudioLanguage = resolvePreferredAudioLanguage()
            )
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

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
            _uiState.value = _uiState.value.copy(activeSkipInterval = null, skipIntervals = intervals, skipIntervalDismissed = false)
        }
    }

    fun onPlaybackPosition(positionMs: Long) {
        lastKnownPositionMs = positionMs
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
        subs.forEach { subtitle ->
            subtitleMenuCandidates[subtitle.id.ifBlank { subtitle.url }] = subtitle
        }
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

    /** Keeps [targetSubtitleLangCode] and the menu-facing [PlayerUiState.matchLanguageName] in sync. */
    private fun setTargetSubtitleLang(code: String) {
        targetSubtitleLangCode = code
        val name = if (code.isBlank()) "" else languageCodeToName(code)
        if (_uiState.value.matchLanguageName != name) {
            _uiState.value = _uiState.value.copy(matchLanguageName = name)
        }
    }

    private fun applyPreferredSubtitle(preference: String, subtitles: List<Subtitle>, fallbackLanguage: String?) {
        val normalizedPref = normalizeLanguage(preference)
        if (normalizedPref.isNotBlank() && !isSubtitleDisabledPreference(preference)) {
            setTargetSubtitleLang(normalizedPref)
        }

        // Highest priority: an embedded (muxed) track in the preferred language is inherently in
        // sync, so it beats any auto logic — it overrides an auto-selected/auto-matched subtitle and
        // cancels a running "Find best match" scan. It never overrides a real user pick. This also
        // handles embedded tracks that resolve *after* the auto-match already started.
        if (!userPickedSubtitle && normalizedPref.isNotBlank() && !isSubtitleDisabledPreference(preference)) {
            val embeddedPref = subtitles.firstOrNull {
                it.isEmbedded && !it.isBitmap && !it.isForced &&
                    !it.label.contains("forced", ignoreCase = true) &&
                    (normalizeLanguage(it.lang) == normalizedPref || normalizeLanguage(it.label) == normalizedPref)
            }
            if (embeddedPref != null && _uiState.value.selectedSubtitle?.id != embeddedPref.id) {
                cancelFindBestMatch()
                hasManualSubtitleSelection = true
                translationManager.isEnabled = false
                aiSourceSubtitle = null
                _uiState.value = _uiState.value.copy(
                    selectedSubtitle = embeddedPref,
                    isAiTranslating = false,
                    isAiAvailable = false,
                    aiTargetLanguageName = "",
                    subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
                )
                return
            }
        }

        if (hasManualSubtitleSelection) return
        if (isSubtitleDisabledPreference(preference)) {
            _uiState.value = _uiState.value.copy(selectedSubtitle = null)
            return
        }

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
                    // Prefer plain subs over SDH (accessibility) over forced-only tracks
                    .thenBy { when {
                        it.isForced -> 2
                        it.label.contains("SDH", ignoreCase = true) || it.label.contains("CC", ignoreCase = true) -> 1
                        else -> 0
                    }}
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
            val targetLangName = languageCodeToName(normalizedPref)
            if (source != null) {
                aiSourceSubtitle = source
                translationManager.targetLanguage = targetLangName
            }
            if (aiFindBestMatchFirst) {
                // Auto-run "Find best match" first: pick a synced Hebrew addon sub if one exists,
                // otherwise fall back to AI-translating the built-in source (handled inside).
                _uiState.value = _uiState.value.copy(
                    isAiAvailable = source != null,
                    aiTargetLanguageName = if (source != null) targetLangName else _uiState.value.aiTargetLanguageName
                )
                activateAiSubtitle()
            } else if (source != null) {
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
                    if (!embeddedBlocksExternal(current, externalMatch, normalizedPref)) {
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
                if (!embeddedBlocksExternal(current, externalMatch, normalizedPref)) {
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
            // "Find best match first" auto-runs the scan even without AI auto-select: it verifies
            // sync on top of the classic release-name pick. AI translation is deliberately NOT
            // auto-activated here — that's what the auto-select toggle controls — and the raw
            // reference cues stay hidden while scanning, so on no-match the classic pick above
            // simply returns to screen.
            if (aiFindBestMatchFirst && !autoMatchAttempted && !hasManualSubtitleSelection) {
                autoMatchAttempted = true
                findBestSubtitleMatch()
            }
        }
    }

    /**
     * Whether a currently-selected embedded track should block auto-selecting [externalMatch].
     * An embedded track normally wins over externals (guaranteed sync) — but a fallback-language
     * embedded (e.g. English selected while the preferred-language addon subs were still
     * loading) must not block the preferred-language external that arrives later.
     */
    private fun embeddedBlocksExternal(
        current: Subtitle?,
        externalMatch: Subtitle,
        normalizedPref: String
    ): Boolean {
        if (current == null || !current.isEmbedded || externalMatch.isEmbedded) return false
        val externalIsPref = normalizeLanguage(externalMatch.lang) == normalizedPref
        val currentIsPref = normalizeLanguage(current.lang) == normalizedPref
        return !(externalIsPref && !currentIsPref)
    }

    private fun findAiSourceSubtitle(subtitles: List<Subtitle>): Subtitle? {
        // Some files label forced tracks "SUBFORCED" without setting SELECTION_FLAG_FORCED
        fun Subtitle.isEffectivelyForced() = isForced || label.contains("forced", ignoreCase = true)
        // Bitmap subtitles (PGS/VOBSUB) are images with no text — they can't be translated,
        // so they must never be chosen as the AI source (would render a blank screen).
        fun Subtitle.isUsableSource() = !isEffectivelyForced() && !isBitmap && id !in untranslatableSourceIds
        fun List<Subtitle>.bestEmbedded(): Subtitle? {
            val embedded = filter { it.isEmbedded }
            // Prefer plain > SDH/CC; never use forced-only or image-based tracks as AI source
            return embedded.firstOrNull { it.isUsableSource() && !it.label.contains("SDH", ignoreCase = true) && !it.label.contains("CC", ignoreCase = true) }
                ?: embedded.firstOrNull { it.isUsableSource() }
        }

        // AI translation only ever translates a BUILT-IN (embedded) track — English first, else any
        // other embedded foreign-language track. It must NEVER use an EXTERNAL addon sub as source
        // (unverified timing, and when the user has target-language addon subs the app should
        // pick/verify one of those or use the hearing scan — not AI-translate an English addon).
        // So "no embedded source ⇒ no AI translation": the scan then falls to hearing (if a Gemini
        // key + AI feature are on), then to the top release-name-scored addon sub.
        return subtitles.filter { normalizeLanguage(it.lang) == "en" }.bestEmbedded()
            ?: subtitles.filter { it.lang.isNotBlank() }.bestEmbedded()
            ?: subtitles.bestEmbedded()
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

    private fun streamSearchText(stream: StreamSource): String {
        return buildString {
            append(stream.quality)
            append(' ')
            append(stream.source)
            append(' ')
            append(stream.addonName)
            stream.behaviorHints?.filename?.let {
                append(' ')
                append(it)
            }
        }.lowercase()
    }

    private fun hostFromUrl(url: String?): String {
        return runCatching { java.net.URI(url?.trim().orEmpty()).host.orEmpty() }.getOrDefault("")
    }

    private fun streamDiag(stream: StreamSource?): String {
        stream ?: return "none"
        return "addon=${stream.addonId.ifBlank { "-" }} q=${stream.quality.ifBlank { "-" }} " +
            "size=${stream.size.ifBlank { "-" }} cached=${stream.behaviorHints?.cached == true} " +
            "score=${playbackPriorityScore(stream)} src=${stream.source.take(90)}"
    }

    private fun playbackPriorityScore(stream: StreamSource): Int {
        val text = streamSearchText(stream)

        // Autoplay intentionally prefers the highest-quality source first.
        var score = qualityScore(stream.quality) * 1_000

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

    private fun addonOrderIndex(stream: StreamSource): Int {
        if (currentAddonOrderedIds.isEmpty()) return Int.MAX_VALUE
        val tabId = if (stream.addonId == HomeServerRepository.ADDON_ID) {
            val baseName = stream.addonName.split(" - ").firstOrNull()?.trim().orEmpty()
            if (baseName.isNotBlank()) "${stream.addonId}:$baseName" else stream.addonId
        } else {
            stream.addonId.ifBlank { stream.addonName }
        }
        val directIndex = currentAddonOrderedIds.indexOfFirst { orderedId ->
            orderedId == stream.addonId || orderedId == tabId
        }
        if (directIndex >= 0) return directIndex
        val fuzzyIndex = currentAddonOrderedIds.indexOfFirst { orderedId ->
            tabId.contains(orderedId) || orderedId.contains(tabId)
        }
        return if (fuzzyIndex >= 0) fuzzyIndex else Int.MAX_VALUE
    }

    fun onPlaybackStarted(startupMs: Long, startupRetries: Int, autoFailovers: Int) {
        playbackErrorReportJob?.cancel()
        val currentState = _uiState.value
        if (
            currentState.isLoading ||
            currentState.isLoadingStreams ||
            currentState.streamProgress != null ||
            currentState.streamLoadPhase != null ||
            currentState.error != null
        ) {
            _uiState.value = currentState.copy(
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

        // "None" disables language preference entirely: no forced audio language,
        // while autoplay still uses the same quality/size ordering as the source picker.
        if (setting.equals("None", ignoreCase = true)) return "none"

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
        val codes = extractLanguageCodes(combined).toMutableSet()
        codes.addAll(detectAudioLanguageMarkers(combined.lowercase()))
        val hasMulti = hasMultiLanguageHint(combined)
        return when {
            codes.contains(preferred) -> 2
            codes.isEmpty() || hasMulti -> 1
            else -> 0
        }
    }

    /**
     * Detect audio-language markers commonly used in release names that the plain
     * ISO-code tokenizer in [extractLanguageCodes] misses. Focused on Polish audio
     * ("Lektor", "Dubbing PL", "PLDUB", ...) which is otherwise invisible to language
     * scoring and would lose to a larger foreign-language rip.
     */
    private fun detectAudioLanguageMarkers(lowerText: String): Set<String> {
        val out = mutableSetOf<String>()
        val polishAudio = listOf(
            "lektor", "dubbing pl", "dub pl", "pl dub", "pldub", "pl-dub", "dubpl", "polski"
        )
        if (polishAudio.any { lowerText.contains(it) }) out.add("pl")
        return out
    }

    private fun autoplaySelectBest(streams: List<StreamSource>, preferredLanguage: String) {
        if (!canStartAutoplay()) return
        val healthyStreams = sortStreamsByQualityAndSize(
            eligiblePlayerAutoplayStreams(streams, autoPlayMinimumQuality), preferredLanguage
        )
        if (healthyStreams.isEmpty()) return
        val hasExplicitPreferred =
            !currentPreferredBingeGroup.isNullOrBlank() ||
                !currentPreferredAddonId.isNullOrBlank() ||
                !currentPreferredSourceName.isNullOrBlank()
        val preferredFromBingeGroup = currentPreferredBingeGroup?.let { preferredGroup ->
            healthyStreams.firstOrNull { stream ->
                stream.behaviorHints?.bingeGroup == preferredGroup &&
                    (currentPreferredAddonId?.let { stream.addonId == it } ?: true)
            } ?: healthyStreams.firstOrNull { stream ->
                stream.behaviorHints?.bingeGroup == preferredGroup
            }
        }

        val preferredNavigationCandidate = healthyStreams.firstOrNull { s ->
            val addonMatch = currentPreferredAddonId?.let { s.addonId == it } ?: true
            val sourceMatch = currentPreferredSourceName?.let { s.source == it } ?: true
            addonMatch && sourceMatch
        } ?: healthyStreams.firstOrNull { s ->
            currentPreferredAddonId?.let { s.addonId == it } ?: false
        }

        val stabilitySelected = pickAutoplayTopStream(healthyStreams, preferredLanguage)
        val selected = if (hasExplicitPreferred) {
            preferredFromBingeGroup ?: preferredNavigationCandidate ?: stabilitySelected ?: healthyStreams.first()
        } else {
            stabilitySelected ?: healthyStreams.first()
        }
        playbackDiag(
            "autoplaySelected selected=${streamDiag(selected)} " +
                "preferredNavigation=${streamDiag(preferredNavigationCandidate)} " +
                "usedPreferred=${hasExplicitPreferred && (selected === preferredFromBingeGroup || selected === preferredNavigationCandidate)}"
        )
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
            groupMatch &&
                addonMatch &&
                sourceMatch &&
                !stream.url.isNullOrBlank()
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

    private fun pickAutoplayTopStream(
        streams: List<StreamSource>,
        preferredLanguage: String
    ): StreamSource? {
        if (streams.isEmpty()) return null
        return sortStreamsForAutoplay(streams, preferredLanguage).firstOrNull()
    }

    private fun sortStreamsForAutoplay(
        streams: List<StreamSource>,
        preferredLanguage: String
    ): List<StreamSource> {
        return streams.sortedWith(
            compareBy<StreamSource> { streamRepository.getPlaybackHostHealthPenalty(it) }
                .thenByDescending { qualityScoreForAutoPlay(it) }
                .thenByDescending { parseSize(it.size) }
                .thenBy { if (it.behaviorHints?.notWebReady == true) 1 else 0 }
                .thenByDescending { playbackPriorityScore(it) }
                .thenByDescending { if (it.behaviorHints?.cached == true) 1 else 0 }
                .thenByDescending { streamLanguageScore(it, preferredLanguage) }
                .thenByDescending { streamRepository.getAddonHealthBias(it.addonId) }
        )
    }

    /** Aggregator addons (AIOStreams) sort results server-side per the user's own web config —
     *  their streams keep the order the addon returned them in instead of being re-sorted. */
    private fun keepsOwnStreamOrder(stream: StreamSource): Boolean =
        stream.addonName.contains("aiostream", ignoreCase = true) ||
            stream.addonId.contains("aiostream", ignoreCase = true)

    private fun sortStreamsByQualityAndSize(
        streams: List<StreamSource>,
        preferredLanguage: String
    ): List<StreamSource> {
        val qualityOrder = compareByDescending<IndexedValue<StreamSource>> { parseSize(it.value.size) }
            .thenByDescending { qualityScore(it.value.quality) }
            .thenByDescending { playbackPriorityScore(it.value) }
            .thenBy { streamRepository.getPlaybackHostHealthPenalty(it.value) }
            .thenBy { if (it.value.behaviorHints?.notWebReady == true) 1 else 0 }
            .thenByDescending { streamLanguageScore(it.value, preferredLanguage) }
            .thenBy { it.value.source.lowercase() }
        return streams.withIndex()
            .sortedWith(
                // Streams stay grouped by addon order; within a group, self-ordered addons keep
                // their original (arrival) order while everything else gets the quality sort.
                compareBy<IndexedValue<StreamSource>> { addonOrderIndex(it.value) }
                    .then { a, b ->
                        if (keepsOwnStreamOrder(a.value) && keepsOwnStreamOrder(b.value)) {
                            a.index.compareTo(b.index)
                        } else {
                            qualityOrder.compare(a, b)
                        }
                    }
            )
            .map { it.value }
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
        val topStreams = sortStreamsForAutoplay(streams, preferredLanguage).take(3)
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
        subtitleMenuCandidates.entries.removeAll { it.value.isEmbedded }
        streamSelectionJob?.cancel()
        playbackErrorReportJob?.cancel()
        streamSelectionJob = viewModelScope.launch {
            val selectionStartMs = System.currentTimeMillis()
            val requestedResumePosition = resumePositionMs?.coerceAtLeast(0L)
            var selectedOriginal = stream
            playbackDiag("selectStream request=${streamDiag(stream)}")
            _uiState.value = _uiState.value.copy(
                selectedStream = stream,
                isLoading = true,
                isLoadingStreams = false,
                streamProgress = null,
                streamLoadPhase = PlayerMessage.Res(R.string.player_phase_preparing_stream),
                error = null,
                isSetupError = false
            )
            val resolvedResult = runCatching {
                streamRepository.resolveStreamForPlayback(stream)
            }
            resolvedResult.onFailure { error ->
                AppLogger.recordException(
                    throwable = error,
                    context = playbackDiagnosticContext("manual_stream_resolve_exception", stream)
                )
            }
            val directResolvedStream = resolvedResult.getOrNull()
            val selection = when {
                directResolvedStream != null -> ReachableStreamSelection(stream, directResolvedStream)
                isHubCloudPageUrl(stream.url.orEmpty()) -> findFirstResolvableAlternative(stream)
                else -> ReachableStreamSelection(stream, stream)
            }
            if (selection == null) {
                AppLogger.recordException(
                    throwable = IllegalStateException("HubCloud source could not be resolved"),
                    context = playbackDiagnosticContext("selected_hubcloud_unresolved", stream)
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    sourceSearchActive = false,
                    streamProgress = null,
                    streamLoadPhase = null,
                    error = PlayerMessage.Res(R.string.player_error_resolve_stream_failed)
                )
                return@launch
            }
            selectedOriginal = selection.original
            val resolvedStream = selection.resolved
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
                    sourceSearchActive = false,
                    streamProgress = null,
                    streamLoadPhase = null,
                    error = if (isP2p) {
                        PlayerMessage.Res(R.string.player_error_p2p_needs_torrserver)
                    } else {
                        PlayerMessage.Res(R.string.player_error_resolve_stream_failed)
                    }
                )
                return@launch
            }
            Log.i(
                TAG,
                "Selected stream resolvedMs=$resolveMs addon=${resolvedStream.addonId} quality=${resolvedStream.quality} size=${resolvedStream.size} cached=${resolvedStream.behaviorHints?.cached == true} host=${runCatching { java.net.URI(url).host }.getOrNull().orEmpty()}"
            )
            playbackDiag(
                "selectStream resolvedMs=$resolveMs resolved=${streamDiag(resolvedStream)} " +
                    "host=${hostFromUrl(url)}"
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
            val streamSubs = selectedOriginal.subtitles
            if (streamSubs.isNotEmpty()) {
                val existingSubs = _uiState.value.subtitles
                val newSubs = streamSubs.filter { newSub ->
                    existingSubs.none { it.id == newSub.id || (it.url == newSub.url && newSub.url.isNotBlank()) }
                }
                if (newSubs.isNotEmpty()) {
                    _uiState.value = _uiState.value.copy(subtitles = existingSubs + newSubs)
                }
            }

            // A different source is a different file: the previous stream's track selection, AI
            // translation state, any running match scan, and — crucially — its embedded subtitle
            // entries are all meaningless for it. Drop them so the auto-selection flow (find best
            // match / AI) runs fresh: a stale embedded entry would otherwise be re-selected against
            // the stale list and map onto an arbitrary track of the new file, while the fresh
            // embedded tracks arriving later would be ignored because the selection "already
            // matches" (no nonce bump → the override is never applied to the new media item).
            cancelFindBestMatch()
            cancelSubtitleLocalization()
            hasManualSubtitleSelection = false
            userPickedSubtitle = false
            autoMatchAttempted = false
            aiSourceSubtitle = null
            untranslatableSourceIds.clear()
            translationManager.isEnabled = false

            // Direct URL - use immediately (ExoPlayer handles redirects)
            _uiState.value = _uiState.value.copy(
                selectedStream = resolvedStream,
                selectedStreamUrl = url,
                savedPosition = requestedResumePosition ?: _uiState.value.savedPosition,
                streamSelectionNonce = _uiState.value.streamSelectionNonce + 1,
                subtitles = _uiState.value.subtitles.filterNot { it.isEmbedded },
                selectedSubtitle = null,
                isAiTranslating = false,
                subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1,
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
                    preloadSubtitles(merged)

                    scheduleSubtitleSelection(currentOriginalLanguage)
                }
            }
        }
    }

    private suspend fun findFirstResolvableAlternative(
        selected: StreamSource,
        maxAttempts: Int = 4
    ): ReachableStreamSelection? {
        val streams = _uiState.value.streams
        if (streams.isEmpty()) return null

        val selectedIndex = streams.indexOf(selected)
        val candidateIndexes = if (selectedIndex >= 0) {
            (1 until streams.size).map { offset -> (selectedIndex + offset) % streams.size }
        } else {
            streams.indices.toList()
        }

        val candidates = candidateIndexes
            .map { idx -> streams[idx] }
            .filter { candidate ->
                !candidate.url.isNullOrBlank()
            }
            .take(maxAttempts)

        for (candidate in candidates) {
            val resolved = runCatching {
                streamRepository.resolveStreamForPlayback(candidate)
            }.getOrNull()
            if (resolved != null) return ReachableStreamSelection(candidate, resolved)

            // Preserve the existing raw-URL fallback for ordinary HTTP streams, but never
            // hand another unresolved HubCloud HTML page to ExoPlayer.
            if (!isHubCloudPageUrl(candidate.url.orEmpty())) {
                return ReachableStreamSelection(candidate, candidate)
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

    /**
     * Stable English identifier for telemetry. The displayed text is localized per user, so the
     * message itself would make error reports unsortable across languages; the resource entry
     * name ("player_fail_render_failed") never changes with the locale.
     */
    private fun telemetryKeyOf(message: PlayerMessage): String = when (message) {
        is PlayerMessage.Raw -> message.text
        is PlayerMessage.Res -> runCatching {
            context.resources.getResourceEntryName(message.resourceId)
        }.getOrDefault(message.resourceId.toString())
    }

    fun reportPlaybackError(message: PlayerMessage) {
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
                sourceSearchActive = false,
                streamProgress = null,
                streamLoadPhase = null,
                error = message
            )
            AppLogger.recordException(
                throwable = IllegalStateException("Playback error displayed"),
                context = playbackDiagnosticContext(
                    phase = "playback_error_displayed",
                    extra = mapOf("playback_error_message" to telemetryKeyOf(message))
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
                if (!selected.isEmbedded && selected.url.startsWith("file:")) {
                    // A matched subtitle localized to a cache file — keep it as-is. Remapping to
                    // the addon-list entry (same id) would swap the URL back to the remote server
                    // and re-trigger a MediaItem rebuild with the slow remote source.
                    selected
                } else {
                    finalList.firstOrNull { it.id == selected.id }
                        ?: finalList.firstOrNull { selected.url.isNotBlank() && it.url == selected.url }
                        // After MediaItem rebuild groupIndex can change, making generated IDs stale.
                        // Fall back to lang+label match for embedded subs so the nonce-based
                        // LaunchedEffect fires with the freshly-indexed version.
                        ?: if (selected.isEmbedded) finalList.firstOrNull {
                            it.isEmbedded && it.lang == selected.lang && it.label == selected.label
                        } else null
                        ?: selected
                }
            } else {
                null
            }

            _uiState.value = _uiState.value.copy(
                subtitles = finalList,
                selectedSubtitle = resolvedSelected
            )

            // Late AI-interim activation for a running scan. The preload gate defers prepare
            // until the addon-subtitle fetch completes, so the scan now reliably STARTS before
            // the player exposes any embedded track — runFindBestMatch then found no AI source
            // and went silent, and autoMatchAttempted blocks any re-run (the pre-preload flow
            // only worked because prepare raced ahead of the fetch). Join the interim as soon
            // as an embedded source appears while the scan is still running.
            if (_uiState.value.isFindingBestMatch && !_uiState.value.isAiTranslating &&
                aiSubtitleEnabled && aiSubtitleAutoSelect &&
                findAiSourceSubtitle(finalList) != null
            ) {
                activateAiTranslation()
            }

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
                cancelSubtitleLocalization()
                applyPreferredSubtitle(preferred, finalList, currentOriginalLanguage)
            }
        }
    }

    fun selectSubtitle(subtitle: Subtitle, isUserAction: Boolean = true) {
        hasManualSubtitleSelection = true
        userPickedSubtitle = isUserAction
        if (isUserAction) {
            cancelFindBestMatch()
            updateMatchCacheForManualPick(subtitle)
        }
        subtitleSelectionJob?.cancel()
        cancelSubtitleLocalization()
        translationManager.isEnabled = false

        // Handing ExoPlayer the addon URL makes media3 decode the file as UTF-8, which turns a
        // legacy code page (windows-1255 Hebrew is still common) into rows of U+FFFD. Only a
        // locally decoded copy is safe — the same reason the matched/remembered paths localize.
        // Preload covers the first MAX_PRELOAD_SUBS tracks; anything past that (or picked before
        // preload finished) must be fetched here, or it renders as symbols.
        // Recorded at pick time, not after the download: the user's choice of language counts
        // even if the localization is later cancelled or falls back to the remote URL.
        recordSubtitleUsage(subtitle)

        val preloaded = preloadedCopyFor(subtitle)
        if (preloaded != null || !needsLocalDecode(subtitle)) {
            applySelectedSubtitle(preloaded ?: subtitle)
            return
        }
        // Generation token: cancelling the job is not enough on its own, because the apply below is
        // a plain state write with no suspension point — a job cancelled after its last suspension
        // would still land, re-enabling subtitles that were turned off or applying the previous
        // episode's pick. Every path that abandons the selection bumps this.
        val generation = ++subtitleLocalizeGeneration
        subtitleLocalizeJob = viewModelScope.launch {
            // Bounded: a slow addon must not hold the pick hostage — falling back to the remote
            // URL is exactly today's behaviour, so the worst case is unchanged. The bound is real
            // because SubtitleSyncMatcher.loadRaw is cancellation-aware (enqueue + Call.cancel).
            val localized = withTimeoutOrNull(SUBTITLE_LOCALIZE_TIMEOUT_MS) {
                SubtitleSyncMatcher.loadRaw(subtitle.url, subtitle.lang)
                    ?.takeIf { it.isNotBlank() }
                    ?.let { raw -> localizeSubtitle(subtitle, raw) }
                    ?.takeIf { it.url.startsWith("file:") }
            }
            if (generation != subtitleLocalizeGeneration) {
                Log.i("SubMatch", "stale subtitle localization dropped: ${subtitle.label}")
                return@launch
            }
            if (localized == null) {
                Log.w("SubMatch", "manual pick not localized (serving remote): ${subtitle.label}")
            }
            applySelectedSubtitle(localized ?: subtitle)
        }
    }

    /**
     * Abandons any in-flight manual-pick localization: cancels the download *and* invalidates its
     * result, so a request already past its last suspension point cannot apply a subtitle the user
     * has since replaced, turned off, or left behind with the previous media.
     */
    private fun cancelSubtitleLocalization() {
        subtitleLocalizeGeneration++
        subtitleLocalizeJob?.cancel()
        subtitleLocalizeJob = null
    }

    /** True for external subtitles still pointing at a remote URL of unknown character encoding. */
    private fun needsLocalDecode(subtitle: Subtitle): Boolean =
        !subtitle.isEmbedded &&
            subtitle.url.isNotBlank() &&
            !subtitle.url.startsWith("file:")

    /** An already-downloaded local copy of [subtitle] from the preload pass, if there is one. */
    private fun preloadedCopyFor(subtitle: Subtitle): Subtitle? {
        if (subtitle.isEmbedded) return null
        val key = "${subtitle.provider}|${subtitle.id}"
        return _uiState.value.preloadedSubtitles.firstOrNull {
            it.url.startsWith("file:") && "${it.provider}|${it.id}" == key
        }
    }

    /** Commits [served] as the playing subtitle (a local copy of the user's pick, or the pick). */
    private fun applySelectedSubtitle(served: Subtitle) {
        // Keep isAiAvailable/aiTargetLanguageName so the AI entry stays in the menu for re-selection
        _uiState.value = _uiState.value.copy(
            selectedSubtitle = served,
            isAiTranslating = false,
            subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
        )
    }

    /** Cancel a running/queued "Find best match" scan and clear its transient state. */
    private fun cancelFindBestMatch() {
        findMatchJob?.cancel()
        collectingReference = false
        matchPreviousTexts = null
        if (_uiState.value.isFindingBestMatch || _uiState.value.isLiveAudioTranslating) {
            geminiLiveService.disconnect()
            _uiState.value = _uiState.value.copy(
                isFindingBestMatch = false,
                isLiveAudioTranslating = false,
                matchStatus = null
            )
        }
    }

    fun setAutoPlayNext(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(autoPlayNext = enabled)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[autoPlayNextKey()] = enabled
            }
        }
    }

    fun setAutoSkipIntro(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(autoSkipIntro = enabled)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileBooleanKey("auto_skip_intro")] = enabled
            }
        }
    }

    fun setAutoSkipOutro(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(autoSkipOutro = enabled)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileBooleanKey("auto_skip_outro")] = enabled
            }
        }
    }

    fun setAudioDelayMs(delayMs: Long) {
        _uiState.value = _uiState.value.copy(audioDelayMs = delayMs)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileLongKey("audio_delay_ms")] = delayMs
            }
        }
    }

    fun setAudioNormalization(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(audioNormalization = enabled)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileBooleanKey("audio_normalization")] = enabled
            }
        }
    }

    fun setSubtitleSizePref(size: String) {
        val mappedPct = when (size.lowercase()) {
            "small" -> 80
            "large" -> 120
            "extra large" -> 140
            else -> 100
        }
        _uiState.value = _uiState.value.copy(subtitleSize = size, subtitleSizePct = mappedPct)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileStringKey("subtitle_size")] = size
                prefs[profileManager.profileIntKey("subtitle_size_pct")] = mappedPct
            }
        }
    }

    fun setSubtitleSizePct(pct: Int) {
        val clamped = pct.coerceIn(50, 250)
        val name = when {
            clamped <= 80 -> "Small"
            clamped >= 130 -> "Extra Large"
            clamped >= 115 -> "Large"
            else -> "Medium"
        }
        _uiState.value = _uiState.value.copy(subtitleSizePct = clamped, subtitleSize = name)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileIntKey("subtitle_size_pct")] = clamped
                prefs[profileManager.profileStringKey("subtitle_size")] = name
            }
        }
    }

    fun setSubtitleVerticalPct(pct: Int) {
        val clamped = pct.coerceIn(1, 88)
        val name = when {
            clamped >= 60 -> "Top"
            clamped >= 25 -> "High"
            clamped >= 12 -> "Medium"
            clamped >= 5 -> "Low"
            else -> "Bottom"
        }
        _uiState.value = _uiState.value.copy(subtitleVerticalPct = clamped, subtitleOffset = name)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileIntKey("subtitle_vertical_pct")] = clamped
                prefs[profileManager.profileStringKey("subtitle_offset")] = name
            }
        }
    }

    fun setSubtitlePreloadEnabled(enabled: Boolean) {
        subtitlePreloadEnabled = enabled
        _uiState.value = _uiState.value.copy(subtitlePreloadEnabled = enabled)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[subtitlePreloadKey] = enabled
            }
        }
    }

    fun setFilterSubtitlesByLanguage(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(filterSubtitlesByLanguage = enabled)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[filterSubtitlesByLanguageKey()] = enabled
            }
            val filtered = filterSubsByPreferredLanguage(subtitleMenuCandidates.values.toList())
            val selected = _uiState.value.selectedSubtitle
            _uiState.value = _uiState.value.copy(
                subtitles = (filtered + listOfNotNull(selected)).distinctBy { it.id }
            )
        }
    }

    fun setSubtitleRemoveHearingImpaired(enabled: Boolean) {
        translationManager.removeSubtitleHearingImpaired = enabled
        _uiState.value = _uiState.value.copy(subtitleRemoveHearingImpaired = enabled)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileBooleanKey("subtitle_remove_hearing_impaired")] = enabled
            }
        }
    }

    fun setSubtitleColorPref(color: String) {
        _uiState.value = _uiState.value.copy(subtitleColor = color)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileStringKey("subtitle_color")] = color
            }
        }
    }

    fun setSubtitleOffsetPref(offset: String) {
        _uiState.value = _uiState.value.copy(subtitleOffset = offset)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileStringKey("subtitle_offset")] = offset
            }
        }
    }

    fun setVolumeBoostDb(boostDb: Int) {
        val clamped = boostDb.coerceIn(0, 15)
        _uiState.value = _uiState.value.copy(volumeBoostDb = clamped)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileStringKey("volume_boost_db")] = clamped.toString()
            }
        }
    }

    fun setSubtitleStylePref(style: String) {
        _uiState.value = _uiState.value.copy(subtitleStyle = style)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileStringKey("subtitle_style")] = style
            }
        }
    }

    fun setSubtitleFontPref(font: String) {
        _uiState.value = _uiState.value.copy(subtitleFont = font)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileStringKey("subtitle_font")] = font
            }
        }
    }

    fun setSubtitleStylizedPref(stylized: Boolean) {
        _uiState.value = _uiState.value.copy(subtitleStylized = stylized)
        viewModelScope.launch {
            context.settingsDataStore.edit { prefs ->
                prefs[profileManager.profileBooleanKey("subtitle_stylized")] = stylized
            }
        }
    }

    /**
     * The AI subtitle entry point (menu option + auto-select). When "find best match first" is on,
     * it first tries to auto-pick a synced addon subtitle in the user's preferred language; only
     * if nothing matches does it fall back to AI-translating the built-in subtitle.
     */
    fun activateAiSubtitle() {
        if (aiFindBestMatchFirst) {
            runFindBestMatch(isUserAction = false)
        } else {
            activateAiTranslation()
        }
    }

    /**
     * The "Find Best Match (AI)" menu button: always run the match logic; if nothing synced is
     * found, fall back to the AI translation option when one is available.
     */
    fun runFindBestMatch(isUserAction: Boolean = true) {
        // The timing-based scan is AI-independent — no aiSubtitleEnabled gate. Only the optional
        // AI interim below and the hearing fallback (gated at the scoring dispatch) need AI.
        // A user-triggered rescan means the current (possibly remembered) pick is unwanted —
        // forget the cached entry and scan fresh; the auto-run keeps using the cache.
        if (isUserAction) writeCachedMatch(null) else autoMatchAttempted = true
        // With AI auto-select on, show AI translation immediately (if a source exists) so the
        // user sees subtitles right away — and because the AI source (English) track is then
        // selected under the hood, the match can read its cue timing without ever putting English
        // on screen. Then run the match in the background and upgrade to a synced addon sub if
        // one is found; otherwise stay on AI.
        // With AI auto-select OFF (or the AI feature disabled), the user opted out of automatic
        // AI translation entirely: the scan runs "silent" (reference cues hidden, zero AI/API
        // usage) and falls back to whatever was selected before.
        val source = if (aiSubtitleEnabled && aiSubtitleAutoSelect) {
            findAiSourceSubtitle(_uiState.value.subtitles) ?: aiSourceSubtitle
        } else {
            null
        }
        if (source != null) {
            activateAiTranslation()
            findBestSubtitleMatch(
                onNoMatch = { score ->
                    // Staying on the AI fallback — re-activate to upgrade the translation source:
                    // an embedded track may have resolved while the scan ran, and the early
                    // activation could still be translating an external fallback source. Say so —
                    // otherwise the searching indicator just vanishes with no visible outcome.
                    if (_uiState.value.isAiTranslating) {
                        activateAiTranslation()
                        showMatchToast(
                            if (score == null) {
                                PlayerMessage.Res(R.string.player_match_none_keeping_ai)
                            } else {
                                PlayerMessage.Res(
                                    R.string.player_match_none_score_keeping_ai,
                                    listOf((score * 100).toInt())
                                )
                            }
                        )
                    }
                },
                useCache = !isUserAction
            )
        } else {
            // default onNoMatch: "no well-synced subtitle" toast
            findBestSubtitleMatch(useCache = !isUserAction)
        }
    }

    /**
     * Scan-failure fallback: activate AI translation when the feature can run (master toggle on,
     * key present, source available). Deliberately NOT gated on auto-select — that setting only
     * governs unprompted activation at playback start; a failed scan explicitly asked for the
     * best available subtitle, and AI timing (from the built-in track) beats an unverified addon
     * pick. Returns true when AI took over.
     */
    private fun tryAiFallbackAfterScan(): Boolean {
        if (!aiSubtitleEnabled || aiApiKey.isBlank()) return false
        if (findAiSourceSubtitle(_uiState.value.subtitles) == null) return false
        activateAiTranslation()
        showMatchToast(PlayerMessage.Res(R.string.player_match_none_using_ai))
        return true
    }

    /**
     * The active AI source turned out to carry no text (image/PGS track). Move to another embedded
     * text source if one exists; otherwise stop AI rather than leave the untranslated source
     * language on screen pretending to be a translation.
     */
    private fun onAiSourceUntranslatable() {
        if (!_uiState.value.isAiTranslating) return
        val current = aiSourceSubtitle ?: return
        // add() is false when already flagged — keeps this to one pass per source.
        if (!untranslatableSourceIds.add(current.id)) return
        val next = findAiSourceSubtitle(_uiState.value.subtitles)
        if (next != null && next.id != current.id) {
            android.util.Log.i("SubMatch", "AI source '${current.label}' has no text (image track) - switching to '${next.label}'")
            activateAiTranslation()
        } else {
            android.util.Log.i("SubMatch", "AI source '${current.label}' has no text (image track) - no text source available, AI off")
            translationManager.isEnabled = false
            aiSourceSubtitle = null
            _uiState.value = _uiState.value.copy(
                isAiTranslating = false,
                isAiAvailable = false,
                aiErrorToast = PlayerMessage.Res(R.string.player_ai_no_text_source)
            )
        }
    }

    /** Existing behavior: translate the built-in/source subtitle to the target language. */
    fun activateAiTranslation() {
        // Defense-in-depth: every caller is already gated on the AI master toggle (menu entry via
        // isAiAvailable, interim via auto-select, fallback via tryAiFallbackAfterScan), but AI
        // must never activate — and never spend API requests — when the feature is off.
        if (!aiSubtitleEnabled) return
        // Re-resolve the source on every activation: an early activation (before embedded tracks
        // resolve) may have cached an external fallback source, and translation inherits the
        // source's timing — keep the cached one only when nothing better is available now.
        val source = findAiSourceSubtitle(_uiState.value.subtitles) ?: aiSourceSubtitle ?: return
        aiSourceSubtitle = source
        hasManualSubtitleSelection = true
        // AI activation is not a specific user track pick — a late embedded preferred-language
        // track may still displace it.
        userPickedSubtitle = false
        // The silent-scan path never populates aiTargetLanguageName (it's set only when a source
        // existed at scan start) — resolve from the preferred-language code so a late activation
        // doesn't hand the manager a BLANK target (which silently translates to nothing).
        val targetLangName = _uiState.value.aiTargetLanguageName
            .ifBlank { if (targetSubtitleLangCode.isNotBlank()) languageCodeToName(targetSubtitleLangCode) else "" }
        if (targetLangName.isNotBlank()) translationManager.targetLanguage = targetLangName
        translationManager.isEnabled = true
        translationManager.reset()
        aiErrorToastShown = false
        android.util.Log.i(
            "SubMatch",
            "AI translation activated: source=${source.lang}/${source.label} target=$targetLangName"
        )
        _uiState.value = _uiState.value.copy(
            selectedSubtitle = source,
            isAiTranslating = true,
            isAiAvailable = true,
            aiTargetLanguageName = targetLangName,
            aiErrorToast = null
        )
    }

    private var subtitleBeforeLiveAudio: Subtitle? = null

    fun toggleLiveAudioTranslation() {
        if (_uiState.value.isLiveAudioTranslating) {
            geminiLiveService.disconnect()
            // Restore whatever subtitle was active before live AI took over
            _uiState.value = _uiState.value.copy(
                isLiveAudioTranslating = false,
                selectedSubtitle = subtitleBeforeLiveAudio,
                subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
            )
            subtitleBeforeLiveAudio = null
        } else {
            // Clear current subtitle so ExoPlayer stops rendering it while live AI is active.
            // Must mark this as a manual selection — otherwise a subtitle-fetch job that
            // resolves after this point (scheduleSubtitleSelection) would silently re-select
            // a subtitle on top of the live AI overlay, since it only checks this flag.
            hasManualSubtitleSelection = true
            subtitleSelectionJob?.cancel()
            cancelSubtitleLocalization()
            translationManager.isEnabled = false
            subtitleBeforeLiveAudio = _uiState.value.selectedSubtitle
            targetSubtitleLangCode.takeIf { it.isNotBlank() }?.let { geminiLiveService.targetLanguageCode = it }
            geminiLiveService.connect()
            _uiState.value = _uiState.value.copy(
                isLiveAudioTranslating = true,
                selectedSubtitle = null,
                isAiTranslating = false,
                subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
            )
        }
    }

    /**
     * "Find best match": scan the addon subtitle tracks in the user's preferred language and
     * auto-select the one that is best synced with the audio. Loads every candidate's cues,
     * listens to the AI hearing for a short window, then scores each candidate by how often its
     * on-screen cue matches what was spoken at that moment (see [SubtitleSyncMatcher]).
     */
    fun findBestSubtitleMatch(onNoMatch: ((Double?) -> Unit)? = null, useCache: Boolean = true) {
        if (_uiState.value.isFindingBestMatch) return
        val previousSubtitle = _uiState.value.selectedSubtitle
        findMatchJob?.cancel()
        findMatchJob = viewModelScope.launch {
            val targetLang = targetSubtitleLangCode.ifBlank { normalizeLanguage(getDefaultSubtitle()) }
            val targetLangName = languageCodeToName(targetLang)
            // Include the best (rejected) score so a fast verdict is visibly a real scan result.
            val noMatch = onNoMatch ?: { score ->
                showMatchToast(
                    if (score == null) {
                        PlayerMessage.Res(
                            R.string.player_match_none_language,
                            listOf(targetLangName)
                        )
                    } else {
                        PlayerMessage.Res(
                            R.string.player_match_none_language_score,
                            listOf(targetLangName, (score * 100).toInt())
                        )
                    }
                )
            }
            if (targetLang.isBlank() || isSubtitleDisabledPreference(targetLang)) {
                noMatch(null)
                return@launch
            }
            beginMatch(PlayerMessage.Res(R.string.player_match_finding))

            // Subtitle sources resolve asynchronously after playback starts: embedded tracks a beat
            // later, addon subtitles when their fetch completes. Wait for both a usable muxed
            // reference and target-language addon candidates — otherwise the scan can fire before
            // the addon list exists and silently find nothing. (AI translation is already showing
            // meanwhile, so waiting here costs the user nothing.)
            val waitStart = System.currentTimeMillis()
            var playingSinceMs = -1L
            while (true) {
                val nowMs = System.currentTimeMillis()
                val elapsed = nowMs - waitStart
                // Grace periods count *continuous playback* time, not wall time: embedded tracks
                // cannot resolve before the stream actually plays, so a scan that gives up while
                // the player is still buffering (e.g. right after a source switch) is meaningless.
                if (lastIsPlaying) {
                    if (playingSinceMs < 0) playingSinceMs = nowMs
                } else {
                    playingSinceMs = -1L
                }
                val playingFor = if (playingSinceMs < 0) -1L else nowMs - playingSinceMs
                val current = _uiState.value.subtitles
                val hasEmbedded = current.any { it.isEmbedded && !it.isBitmap && !it.isForced }
                val hasCandidates = current.any {
                    !it.isEmbedded && !it.isBitmap && it.url.isNotBlank() &&
                        normalizeLanguage(it.lang) == targetLang
                }
                if (hasEmbedded && hasCandidates) break
                // The only reason to wait for an embedded track is to give the TIMING SCAN a
                // reference. An exact release-name match skips the scan entirely, so once one is
                // on the table there is nothing left to wait for — stop immediately instead of
                // burning MATCH_SOURCES_WAIT_MS. Matters most on PGS-only files, where no usable
                // embedded reference will EVER appear and the loop would always run to timeout.
                if (hasCandidates) {
                    val srcNow = _uiState.value.selectedStream?.source.orEmpty()
                    if (srcNow.isNotBlank() && current.any {
                            !it.isEmbedded && !it.isBitmap && it.url.isNotBlank() &&
                                normalizeLanguage(it.lang) == targetLang &&
                                weightedSubtitleScore(srcNow, it.id) >= 100
                        }
                    ) break
                }
                if (playingFor >= MATCH_SOURCES_WAIT_MS) break
                // Past the embedded grace period and the addon fetch is done — whatever is missing
                // now isn't coming; proceed with what we have.
                if (playingFor >= MATCH_EMBEDDED_WAIT_MS && !_uiState.value.isLoadingSubtitles) break
                // Stream never started (stall/error) — stop waiting eventually.
                if (elapsed >= MATCH_PLAYBACK_WAIT_CAP_MS) break
                delay(200)
            }
            val subs = _uiState.value.subtitles

            // An embedded target-language track is muxed into the media → guaranteed in sync.
            val embedded = subs.firstOrNull {
                it.isEmbedded && !it.isBitmap && !it.isForced &&
                    !it.label.contains("forced", ignoreCase = true) &&
                    normalizeLanguage(it.lang) == targetLang
            }
            if (embedded != null) {
                endMatch()
                selectSubtitle(embedded, isUserAction = false)
                showMatchToast(
                    PlayerMessage.Res(
                        R.string.player_match_embedded,
                        listOf(targetLangName)
                    )
                )
                return@launch
            }

            // Cap the number of candidates we download/score — addons can return dozens. Rank by
            // the release-name heuristic first so the subs most likely cut for this rip survive.
            val streamSrc = _uiState.value.selectedStream?.source.orEmpty()
            val candidates = subs.filter {
                !it.isEmbedded && !it.isBitmap && it.url.isNotBlank() &&
                    normalizeLanguage(it.lang) == targetLang
            }
                .sortedByDescending { weightedSubtitleScore(streamSrc, it.id) }
                .take(MATCH_MAX_CANDIDATES)
            if (candidates.isEmpty()) {
                endMatch()
                // No candidates at all in the target language — AI translation is the only
                // possible target-language subtitle; fall back to it when available.
                if (!_uiState.value.isAiTranslating && !tryAiFallbackAfterScan()) noMatch(null)
                return@launch
            }

            // A previously matched subtitle for this exact stream (same file → same sync) that is
            // still offered by the addons wins immediately — no need to rescan. Skipped for
            // user-triggered rescans, which mean the remembered pick is unwanted.
            if (useCache) {
                val cached = readCachedMatch()
                val remembered = cached?.let { c ->
                    candidates.firstOrNull { it.id == c.id && it.provider == c.provider }
                }
                if (remembered != null) {
                    // Serve from a local cache file so the MediaItem rebuild doesn't stall on a
                    // slow addon server (download bounded by the matcher's client timeouts).
                    // Re-bake the remembered rescue offset, if any.
                    val offsetMs = cached.offsetMs
                    val raw = SubtitleSyncMatcher.loadRaw(remembered.url, remembered.lang)
                    // A remembered OFFSET can only be honoured if we can download the text to bake
                    // the shift in. If that download fails, DON'T serve the un-shifted remote copy
                    // under an "auto-offset" label (the sub would be mistimed while the UI claims it
                    // was corrected) — fall through to a fresh scan instead.
                    if (raw == null && offsetMs != 0L) {
                        Log.w("SubMatch", "remembered offset download failed — rescanning instead of serving unshifted")
                    } else {
                        val local = raw?.let { localizeSubtitle(remembered, it, offsetMs) } ?: remembered
                        endMatch()
                        selectSubtitle(local, isUserAction = false)
                        showMatchToast(
                            if (offsetMs != 0L) {
                                PlayerMessage.Res(
                                    R.string.player_match_remembered_offset,
                                    listOf(remembered.label, formatMatchOffset(offsetMs))
                                )
                            } else {
                                PlayerMessage.Res(
                                    R.string.player_match_remembered,
                                    listOf(remembered.label)
                                )
                            }
                        )
                        return@launch
                    }
                }
            }

            // An EXACT release-name match (score 100 = every weighted token AND the release group
            // agree) means the subtitle was cut for this precise rip, so the timing scan can only
            // confirm what the name already establishes — at the cost of ~20s of dialogue
            // collection before playback settles. Take it directly.
            // Deliberately 100 only: at 95 a single token differs, and that token is often the
            // source (BluRay vs WEB-DL) or the release group — i.e. a different master whose
            // timings genuinely drift. Those still earn a full scan.
            val exactNameMatch = candidates.firstOrNull { weightedSubtitleScore(streamSrc, it.id) >= 100 }
            if (exactNameMatch != null) {
                // Serve from a local copy for the same reason the remembered path does: the
                // MediaItem rebuild would otherwise stall on a slow addon server.
                val exactRaw = SubtitleSyncMatcher.loadRaw(exactNameMatch.url, exactNameMatch.lang)
                val exactLocal = exactRaw?.let { localizeSubtitle(exactNameMatch, it) } ?: exactNameMatch
                endMatch()
                selectSubtitle(exactLocal, isUserAction = false)
                writeCachedMatch(exactNameMatch)
                Log.i("SubMatch", "exact release-name match — scan skipped: ${exactNameMatch.label}")
                showMatchToast(
                    PlayerMessage.Res(
                        R.string.player_match_exact_release_name,
                        listOf(exactNameMatch.label)
                    )
                )
                return@launch
            }

            // Prefer any embedded (muxed) track as the sync reference (English first, else any).
            // SDH/CC is fine for *timing*; only forced/bitmap are unsuitable.
            val embeddedRefs = subs.filter {
                it.isEmbedded && !it.isBitmap && !it.isForced &&
                    !it.label.contains("forced", ignoreCase = true)
            }
            val builtInReference = embeddedRefs.firstOrNull { normalizeLanguage(it.lang) == "en" }
                ?: embeddedRefs.firstOrNull()
            val sourceLabel = if (builtInReference != null) "Built-in" else "Hearing"
            val sourceLabelRes = if (builtInReference != null) {
                R.string.player_match_source_builtin
            } else {
                R.string.player_match_source_hearing
            }
            android.util.Log.i(
                "SubMatch",
                "reference source=$sourceLabel embeddedRefs=${embeddedRefs.size} " +
                    "ref=\"${builtInReference?.label ?: "-"}\" (lang=${builtInReference?.lang}) " +
                    "allEmbedded=${subs.count { it.isEmbedded }}"
            )
            updateMatchStatus(
                PlayerMessage.Res(
                    R.string.player_match_finding_source,
                    listOf(PlayerMessage.Res(sourceLabelRes))
                )
            )

            // Keep the raw text alongside the parsed cues: the winning subtitle is later served to
            // ExoPlayer from a local cache file (already downloaded here) instead of re-fetching
            // a possibly slow addon server during the MediaItem rebuild.
            val loadedRaw = candidates.map { sub ->
                async { sub to SubtitleSyncMatcher.loadRaw(sub.url, sub.lang) }
            }.awaitAll()
            val rawBySubKey = loadedRaw.mapNotNull { (sub, raw) ->
                raw?.let { "${sub.provider}|${sub.id}" to it }
            }.toMap()
            val loaded = loadedRaw.mapNotNull { (sub, raw) ->
                val cues = raw?.let { SubtitleSyncMatcher.parseCues(it) }.orEmpty()
                if (cues.isEmpty()) null else sub to cues
            }

            /**
             * Select [sub], serving it from a local file when its text was downloaded. A non-zero
             * [offsetMs] is baked into that local copy (constant-offset rescue).
             */
            fun selectServedLocally(sub: Subtitle, offsetMs: Long = 0L) {
                val localized = rawBySubKey["${sub.provider}|${sub.id}"]
                    ?.let { localizeSubtitle(sub, it, offsetMs) } ?: sub
                selectSubtitle(localized, isUserAction = false)
            }

            // Last resort when the scan fails outright and no AI fallback is active: behave like
            // the classic non-AI flow and pick the best release-name-scored candidate. A current
            // selection survives only if it's already in the target language — a fallback-language
            // stand-in (e.g. embedded English picked while the addon list was still loading) must
            // not outlive a failed scan. Sync is unverified, so it is deliberately not remembered
            // in the match cache.
            fun selectLastResort() {
                if (_uiState.value.isAiTranslating) return
                // AI translation first (when the feature can run) — verified timing beats any
                // unverified addon pick, independent of the auto-select setting.
                if (tryAiFallbackAfterScan()) return
                val current = _uiState.value.selectedSubtitle
                if (current != null && normalizeLanguage(current.lang) == targetLang) return
                candidates.firstOrNull()?.let {
                    selectServedLocally(it)
                    showMatchToast(
                        PlayerMessage.Res(
                            R.string.player_match_sync_unverified,
                            listOf(it.label)
                        )
                    )
                }
            }

            if (loaded.isEmpty()) {
                endMatch()
                restoreSubtitle(previousSubtitle)
                noMatch(null)
                selectLastResort()
                return@launch
            }

            val scored = when {
                builtInReference != null ->
                    scoreAgainstBuiltIn(loaded, builtInReference, sourceLabelRes, previousSubtitle)
                // AI translation is already on screen as the fallback — the hearing path is too
                // unreliable to risk replacing it, so just stay on AI.
                _uiState.value.isAiTranslating -> null
                // The hearing reference streams audio to the Gemini Live API — it needs the AI
                // feature on, a key, and the Gemini model (a Groq key can't open that connection).
                !aiSubtitleEnabled || aiApiKey.isBlank() ||
                    aiModel != SubtitleAiModel.GEMINI_FLASH_25 -> null
                else -> scoreAgainstHearing(loaded, sourceLabelRes)
            }
            endMatch()

            val successThreshold = if (builtInReference != null) {
                MATCH_SUCCESS_THRESHOLD_TIMING
            } else {
                MATCH_SUCCESS_THRESHOLD_HEARING
            }
            val best = scored?.maxByOrNull { it.score }
            // A corroborated constant-offset rescue (offsetMs != 0, set only when ≥2 candidates
            // agreed) accepts at its own lower bar — segmentation caps its timing score below the
            // normal 0.70. The lone-strong offset path already required ≥0.80, so it clears this too.
            if (best != null && (
                    best.score >= successThreshold ||
                        (best.offsetMs != 0L && best.score >= MATCH_OFFSET_CORROBORATED_ACCEPT)
                    )
            ) {
                selectServedLocally(best.sub, best.offsetMs)
                // Cache the original (addon) identity + any rescue offset — the local file is
                // per-session transient, but the offset must be re-applied on the next playback.
                writeCachedMatch(best.sub, best.offsetMs)
                showMatchToast(
                    if (best.offsetMs != 0L) {
                        PlayerMessage.Res(
                            R.string.player_match_scored_offset,
                            listOf(
                                best.sub.label,
                                (best.score * 100).toInt(),
                                PlayerMessage.Res(sourceLabelRes),
                                formatMatchOffset(best.offsetMs)
                            )
                        )
                    } else {
                        PlayerMessage.Res(
                            R.string.player_match_scored,
                            listOf(
                                best.sub.label,
                                (best.score * 100).toInt(),
                                PlayerMessage.Res(sourceLabelRes)
                            )
                        )
                    }
                )
            } else {
                // Nothing synced found (or too little dialogue) → let the caller fall back (AI translate).
                restoreSubtitle(previousSubtitle)
                noMatch(best?.score)
                selectLastResort()
            }
        }
    }

    /** Whitespace/tag-insensitive form for comparing renderer cue text against parsed file text. */
    private fun normalizeCueTextForCompare(text: String): String =
        text.replace(PlayerViewModelRegexes.HTML_TAG_REGEX, " ").replace(PlayerViewModelRegexes.MULTI_SPACE_REGEX, " ").trim()

    /**
     * A scored candidate. [offsetMs] is 0 for a normal (as-authored) match, or the uniform delay
     * that had to be baked in for a constant-offset rescue — in which case [score] is the
     * *corrected* (post-shift) timing score.
     */
    private data class ScoredCandidate(val sub: Subtitle, val score: Double, val offsetMs: Long)

    /** "+2.0s" / "-1.5s" for toasts and menu rows. */
    private fun formatMatchOffset(ms: Long): String =
        (if (ms >= 0) "+" else "-") + String.format(java.util.Locale.US, "%.1f", kotlin.math.abs(ms) / 1000.0) + "s"

    /**
     * Score every candidate against the reference [refs], with constant-offset rescue. Each
     * candidate gets: (a) its as-authored score; (b) a lone strong offset (search corrected ≥
     * [MATCH_OFFSET_ACCEPT]); and (c) a *corroborated* offset — when ≥[MATCH_OFFSET_CORROBORATE_MIN]
     * candidates independently agree on an offset (within [MATCH_OFFSET_CORROBORATE_MS]), that
     * shared shift is trusted and applied to every candidate at the lower [MATCH_OFFSET_CORROBORATED_ACCEPT]
     * bar. The candidates are the same episode from different addons, so agreement is strong evidence
     * the offset is real (and it discards search-noise outliers that no one else voted for).
     */
    private fun scoreCandidatesWithOffsets(
        loaded: List<Pair<Subtitle, List<SubtitleSyncMatcher.TimedCue>>>,
        rawRefs: List<Pair<Long, Long>>,
        debug: Boolean
    ): List<ScoredCandidate> {
        val tol = MATCH_OVERLAP_TOLERANCE_MS
        // Orphan-drop: discard reference windows that NO candidate covers at all — almost always
        // SDH/non-dialogue reference cues (sound effects, on-screen text) the dialogue subs lack.
        // Left in, each scores a hard 0 for everyone and craters an otherwise-perfect sub. But only
        // drop when the uncovered windows are a small MINORITY: if a large fraction is uncovered
        // that's bad sync across the board (not SDH), and dropping it would shrink the denominator
        // enough to inflate a sub that matches only a couple of windows into a false ~100%.
        val covered = rawRefs.filter { (rs, re) ->
            loaded.any { (_, cues) -> cues.any { it.startMs < re && rs < it.endMs } }
        }
        val keepFloor = Math.ceil(rawRefs.size * MATCH_ORPHAN_KEEP_MIN_FRACTION).toInt()
        val refs = if (covered.size >= MATCH_MIN_REF_INTERVALS && covered.size >= keepFloor) {
            covered
        } else {
            rawRefs
        }
        data class Est(
            val sub: Subtitle,
            val cues: List<SubtitleSyncMatcher.TimedCue>,
            val normal: Double,
            val offset: SubtitleSyncMatcher.OffsetMatch?
        )
        val ests = loaded.map { (sub, cues) ->
            Est(
                sub, cues,
                SubtitleSyncMatcher.scoreByTiming(cues, refs, tol),
                // Offset search stays strict (no tolerance) so its peaks stay sharp for corroboration.
                SubtitleSyncMatcher.estimateOffsetMatch(cues, refs, MATCH_OFFSET_MIN_MS, MATCH_OFFSET_MAX_MS)
            )
        }
        // Each candidate votes its best offset if it meaningfully improves that candidate. Compare
        // against the strict base (both from the offset search) — not the tolerant accept score.
        val votes = ests.mapNotNull { e ->
            e.offset?.takeIf { it.correctedScore >= MATCH_OFFSET_CORROBORATE_FLOOR && it.correctedScore > it.baseScore }
                ?.offsetMs
        }.sorted()
        // Largest cluster of votes within the agreement window; median of it is the trusted offset.
        var corroboratedOffset: Long? = null
        var corroboratedSupport = 0
        for (anchor in votes) {
            val members = votes.filter { kotlin.math.abs(it - anchor) <= MATCH_OFFSET_CORROBORATE_MS }
            if (members.size > corroboratedSupport) {
                corroboratedSupport = members.size
                corroboratedOffset = members[members.size / 2]
            }
        }
        val corrob = corroboratedOffset?.takeIf { corroboratedSupport >= MATCH_OFFSET_CORROBORATE_MIN }
        return ests.map { e ->
            var score = e.normal // tolerant, offset 0
            var off = 0L
            // (b) lone strong offset — qualify via the strict search (≥ 0.80), then take the
            // tolerant score at that shift so the accept bar is measured consistently.
            e.offset?.let {
                if (it.correctedScore >= MATCH_OFFSET_ACCEPT) {
                    val sc = SubtitleSyncMatcher.scoreByTiming(SubtitleSyncMatcher.shiftCues(e.cues, it.offsetMs), refs, tol)
                    if (sc > score) { score = sc; off = it.offsetMs }
                }
            }
            // (c) corroborated offset — tolerant re-score at the shared shift (a candidate's own
            // argmax may have landed elsewhere on search noise; the corroborated value is trusted).
            if (corrob != null) {
                val sc = SubtitleSyncMatcher.scoreByTiming(SubtitleSyncMatcher.shiftCues(e.cues, corrob), refs, tol)
                if (sc >= MATCH_OFFSET_CORROBORATED_ACCEPT && sc > score) {
                    score = sc; off = corrob
                }
            }
            if (debug) {
                android.util.Log.i(
                    "SubMatch",
                    "[builtin] candidate label=\"${e.sub.label}\" provider=${e.sub.provider} cues=${e.cues.size} " +
                        "refIntervals=${refs.size} score=${"%.2f".format(score)}" +
                        (if (off != 0L) " offset=${off}ms" else "")
                )
            }
            ScoredCandidate(e.sub, score, off)
        }
    }

    /** Reference = a built-in track's cue timing (any language). Returns null if too little dialogue. */
    private suspend fun scoreAgainstBuiltIn(
        loaded: List<Pair<Subtitle, List<SubtitleSyncMatcher.TimedCue>>>,
        referenceSub: Subtitle,
        @StringRes sourceLabelRes: Int,
        previousSubtitle: Subtitle?
    ): List<ScoredCandidate>? {
        synchronized(referenceIntervals) { referenceIntervals.clear() }
        refIntervalStart = -1L
        // If AI translation is already showing (it has the source/English track selected under the
        // hood), read that track's cues without switching the displayed subtitle — keeps Hebrew on
        // screen. Otherwise select the reference track so ExoPlayer renders/buffers its cues.
        val aiAlreadyShowingSource = _uiState.value.isAiTranslating && aiSourceSubtitle?.id == referenceSub.id
        if (!aiAlreadyShowingSource) {
            hasManualSubtitleSelection = true
            // Re-resolve the reference's track indices from the CURRENT list: a MediaItem rebuild
            // (e.g. the previous scan's pick) reassigns embedded group indices, and a stale
            // override target silently keeps the wrong track rendering.
            val refResolved = _uiState.value.subtitles.firstOrNull {
                it.isEmbedded && it.id == referenceSub.id &&
                    it.groupIndex != null && it.trackIndex != null
            } ?: referenceSub
            if (_uiState.value.isAiTranslating) {
                // AI interim is running on a DIFFERENT source — with the preload gate this is
                // the common case: activation happens before embedded tracks resolve, so the
                // source is an external English sub. Killing the interim here (the historical
                // behavior of this branch) is what made AI "never start" during scans. Instead,
                // migrate the translation onto the reference: it's an embedded non-bitmap text
                // track — precisely the preferred AI source — with strictly better (muxed)
                // timing than the external sub.
                android.util.Log.i(
                    "SubMatch",
                    "reference switch: migrating AI interim source ${aiSourceSubtitle?.label} -> ${refResolved.label}"
                )
                aiSourceSubtitle = refResolved
                translationManager.reset()
                _uiState.value = _uiState.value.copy(
                    selectedSubtitle = refResolved,
                    subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    selectedSubtitle = refResolved,
                    isAiTranslating = false,
                    subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
                )
            }
        }

        // The reference-track selection propagates asynchronously (compose → track selector →
        // decoder flush), so reading the cue buffer too early returns the *previous* track's
        // cues — a previously-displayed candidate would then score a perfect match against
        // itself. Give the switch a moment to settle before trusting the buffer.
        delay(MATCH_TRACK_SWITCH_SETTLE_MS)

        // Unified reference collection: every tick, read the track's already-buffered (upcoming)
        // cues — they land in ExoPlayer's buffer many seconds before they're spoken, so the match
        // can complete before the dialogue even happens — and merge them with cues observed
        // rendering in real time. Stops as soon as enough evidence accumulates.
        val candMin = loaded.minOf { it.second.firstOrNull()?.startMs ?: Long.MAX_VALUE }
        val candMax = loaded.maxOf { it.second.lastOrNull()?.endMs ?: 0L }
        // Normalized cue texts of the candidate that was on screen when the scan started — used
        // to detect stale buffer reads that still mirror that track instead of the reference.
        val previousTexts: Set<String>? = previousSubtitle?.let { prev ->
            loaded.firstOrNull { it.first.id == prev.id }?.second
                ?.mapTo(HashSet()) { normalizeCueTextForCompare(it.text) }
        }
        matchPreviousTexts = previousTexts
        collectingReference = true
        var refs: List<Pair<Long, Long>> = emptyList()
        var bufferedCount = 0
        var lastRefCount = 0
        var lastProgressElapsed = 0L
        val startedAt = System.currentTimeMillis()
        while (true) {
            // Sanity-filter buffered timestamps: they must land within the candidate subtitles'
            // time range, otherwise the stream-offset correction failed — ignore those.
            val bufferedRaw = runCatching {
                bufferedReferenceIntervalsProvider?.invoke(MATCH_BUFFERED_REF_INTERVALS)
            }.getOrNull().orEmpty().filter { it.first in candMin..candMax }
            // Self-match guard: verify by TEXT which track the buffer belongs to. If the buffered
            // cue texts match the previously-displayed candidate's own lines, the reference track
            // switch hasn't landed and we'd be scoring the candidate against itself. Timing-based
            // detection is unusable here: subs cut from the same master share cue timings across
            // languages (a constant-delta check froze scans by discarding legit references).
            val buffered = run {
                if (previousTexts.isNullOrEmpty() || bufferedRaw.isEmpty()) return@run bufferedRaw
                val sampled = runCatching { bufferedCueTextsProvider?.invoke(8) }
                    .getOrNull().orEmpty()
                    .map { normalizeCueTextForCompare(it) }
                    .filter { it.isNotEmpty() }
                if (sampled.isEmpty()) return@run bufferedRaw
                val hits = sampled.count { it in previousTexts }
                if (hits * 2 >= sampled.size) emptyList() else bufferedRaw
            }
            val realtime = synchronized(referenceIntervals) { referenceIntervals.toList() }
            // Realtime intervals that overlap a buffered one describe the same cue — drop them.
            val merged = buffered.toMutableList()
            for (r in realtime) {
                if (buffered.none { b -> r.first < b.second && b.first < r.second }) merged.add(r)
            }
            refs = merged.sortedBy { it.first }
            bufferedCount = buffered.size

            val elapsed = System.currentTimeMillis() - startedAt
            if (refs.size > lastRefCount) {
                lastRefCount = refs.size
                lastProgressElapsed = elapsed
            }
            // While paused no realtime evidence can arrive — freeze the give-up clock so a scan
            // with partial evidence isn't concluded just because the user paused. (A scan with
            // full evidence still exits via the target/early-accept breaks above.)
            if (!lastIsPlaying) lastProgressElapsed = elapsed
            // Interval count alone is not evidence: cues clustered in one moment (a recap, a
            // titles sequence) can agree with an overall-drifting sub. Only accept once the refs
            // also cover a minimum stretch of the video.
            val span = if (refs.size >= 2) refs.last().second - refs.first().first else 0L
            val spanOk = span >= MATCH_MIN_REF_SPAN_MS
            // Target-reached needs BUFFERED quorum: realtime-dominated references carry render-lag
            // skew and merged pseudo-cues that suppress well-synced subs (a verified-perfect sub
            // scored 0.65 on buffered=2/9 refs from a sparse-dialogue recap, then 0.85 once
            // buffered coverage existed — From S03E03, July 2026). Keep collecting instead of
            // concluding from weak evidence; the accept breaks below stay reference-agnostic
            // (a HIGH score is trustworthy on any reference) and the give-up timers still bound
            // the scan.
            if (spanOk && refs.size >= MATCH_TARGET_REF_INTERVALS &&
                bufferedCount >= MATCH_MIN_BUFFERED_FOR_REJECT
            ) break
            // Early accept: scoring is free, so score incrementally — once some candidate is
            // already clearly in sync there's no need to keep collecting up to the target.
            // Scoring is CPU-heavy (esp. the offset sweep below, ×N candidates) — run it off the
            // Main dispatcher so it can't stutter playback/UI on slower TV boxes. The buffered-cue
            // reflection reads at the top of the loop stay on Main (withContext returns here after).
            val bestNow = if (refs.size >= MATCH_EARLY_ACCEPT_REFS) {
                withContext(Dispatchers.Default) {
                    loaded.maxOf { (_, cues) -> SubtitleSyncMatcher.scoreByTiming(cues, refs) }
                }
            } else {
                0.0
            }
            if (spanOk && refs.size >= MATCH_EARLY_ACCEPT_REFS && bestNow >= MATCH_EARLY_ACCEPT_SCORE) break
            // Fast accept: a decisively-synced candidate doesn't need the full span — the
            // cluster false-positive it protects against packs refs into a few seconds, which
            // half the span already rules out. Saves ~15s of realtime collection per scan.
            if (span >= MATCH_FAST_ACCEPT_SPAN_MS && refs.size >= MATCH_FAST_ACCEPT_REFS &&
                bestNow >= MATCH_FAST_ACCEPT_SCORE
            ) break
            // Constant-offset early accept: none of the candidates may be synced as-authored, yet a
            // single uniform delay can make one work. Once there's solid buffered evidence (the
            // offset search needs trustworthy authored timing), if a corroborated offset already
            // produces an acceptable winner, end collection now instead of waiting out the scan.
            if (spanOk && refs.size >= MATCH_OFFSET_EARLY_REFS &&
                bufferedCount >= MATCH_MIN_BUFFERED_FOR_REJECT
            ) {
                val early = withContext(Dispatchers.Default) {
                    scoreCandidatesWithOffsets(loaded, refs, debug = false)
                }
                    .filter { it.offsetMs != 0L }
                    .maxByOrNull { it.score }
                if (early != null && early.score >= MATCH_OFFSET_CORROBORATED_ACCEPT) break
            }
            // Give-up timers: a long one while waiting for speech to exist at all (openings can
            // run minutes without dialogue), and a progress-anchored one afterwards — a silent
            // scene mid-scan pauses the clock instead of draining it. Absolute ceiling on top.
            val deadline = if (lastRefCount == 0) {
                MATCH_NO_SPEECH_CAP_MS
            } else {
                minOf(lastProgressElapsed + MATCH_HARD_CAP_MS, MATCH_TOTAL_CAP_MS)
            }
            if (elapsed >= deadline) break
            updateMatchStatus(
                if (refs.isEmpty()) {
                    PlayerMessage.Res(
                        R.string.player_match_waiting_for_speech,
                        listOf(PlayerMessage.Res(sourceLabelRes))
                    )
                } else {
                    PlayerMessage.Res(
                        R.string.player_match_collecting_reference,
                        listOf(PlayerMessage.Res(sourceLabelRes), refs.size)
                    )
                }
            )
            delay(300)
        }
        collectingReference = false
        matchPreviousTexts = null
        // Inconclusive after the hard cap (too few refs, or all clustered in one moment) — a pick
        // would be a guess; return null so the caller stays on the AI-translation fallback.
        // Floor matches the fast-accept span so a fast-accepted result isn't discarded here.
        if (refs.size < MATCH_MIN_REF_INTERVALS ||
            refs.last().second - refs.first().first < MATCH_FAST_ACCEPT_SPAN_MS
        ) return null
        val srcLabel = when {
            bufferedCount == refs.size -> "buffer"
            bufferedCount == 0 -> "realtime"
            else -> "buffer+realtime"
        }

        loaded.firstOrNull()?.let { (_, cues) ->
            // Show the candidate cues NEAREST the first reference window — file-start cues say
            // nothing about alignment at the scan position.
            val firstRef = refs.firstOrNull()?.first ?: 0L
            val nearIdx = cues.indexOfFirst { it.endMs >= firstRef }.coerceAtLeast(0)
            val near = cues.subList(nearIdx, minOf(nearIdx + 3, cues.size))
            android.util.Log.i(
                "SubMatch",
                "align src=$srcLabel buffered=$bufferedCount total=${refs.size} " +
                    "refs=${refs.take(3)} candNear=${near.map { it.startMs to it.endMs }} " +
                    "candRange=${cues.firstOrNull()?.startMs}..${cues.lastOrNull()?.endMs}"
            )
        }

        val results = withContext(Dispatchers.Default) { scoreCandidatesWithOffsets(loaded, refs, debug = true) }
        // A LOW score from a realtime-dominated reference (give-up exit without buffered quorum)
        // is not evidence of a bad sub — report inconclusive instead of a confident
        // "no well-synced subtitle (best N%)" reject. Accepts pass through unchanged.
        val best = results.maxOfOrNull { it.score } ?: 0.0
        if (best < MATCH_SUCCESS_THRESHOLD_TIMING && bufferedCount < MATCH_MIN_BUFFERED_FOR_REJECT) {
            android.util.Log.i(
                "SubMatch",
                "verdict withheld: best=${"%.2f".format(best)} but buffered=$bufferedCount/${refs.size} — inconclusive"
            )
            return null
        }
        return results
    }

    /** Reference = AI hearing transcription (fallback when there's no built-in English track). */
    private suspend fun scoreAgainstHearing(
        loaded: List<Pair<Subtitle, List<SubtitleSyncMatcher.TimedCue>>>,
        @StringRes sourceLabelRes: Int
    ): List<ScoredCandidate>? {
        startMatchListening()
        val samples = mutableListOf<SubtitleSyncMatcher.SpokenSample>()
        val collector = viewModelScope.launch {
            geminiLiveService.translatedText
                .filterNotNull()
                .distinctUntilChanged()
                .collect { line ->
                    val t = line.trim()
                    if (t.length >= 2) samples.add(SubtitleSyncMatcher.SpokenSample(t, lastKnownPositionMs))
                }
        }
        val startedAt = System.currentTimeMillis()
        var lastSampleCount = 0
        var lastProgressElapsed = 0L
        while (samples.size < MATCH_MAX_SAMPLES) {
            val elapsed = System.currentTimeMillis() - startedAt
            if (samples.size > lastSampleCount) {
                lastSampleCount = samples.size
                lastProgressElapsed = elapsed
            }
            // Paused playback produces no audio — freeze the give-up clock (as in the built-in path).
            if (!lastIsPlaying) lastProgressElapsed = elapsed
            // Same timer scheme as the built-in path: wait long for speech to exist, then a
            // progress-anchored budget so mid-scan silence doesn't drain it. Absolute ceiling.
            val deadline = if (lastSampleCount == 0) {
                MATCH_NO_SPEECH_CAP_MS
            } else {
                minOf(lastProgressElapsed + MATCH_HARD_CAP_MS, MATCH_TOTAL_CAP_MS)
            }
            if (elapsed >= deadline) break
            // Connection failed (bad key, network) → no samples will ever arrive; bail out now
            // instead of spinning until the hard cap.
            if (geminiLiveService.state.value == GeminiLiveState.ERROR) {
                android.util.Log.w("SubMatch", "hearing aborted: Gemini Live error=${geminiLiveService.errorMessage.value}")
                break
            }
            updateMatchStatus(
                if (samples.isEmpty()) {
                    PlayerMessage.Res(
                        R.string.player_match_waiting_for_speech,
                        listOf(PlayerMessage.Res(sourceLabelRes))
                    )
                } else {
                    PlayerMessage.Res(
                        R.string.player_match_scanning_subtitles,
                        listOf(PlayerMessage.Res(sourceLabelRes), samples.size)
                    )
                }
            )
            delay(300)
        }
        collector.cancel()
        if (samples.size < MATCH_MIN_SAMPLES) return null

        return loaded.map { (sub, cues) ->
            val s = SubtitleSyncMatcher.score(
                cues, samples, MATCH_LATENCY_MS, MATCH_TOLERANCE_MS, MATCH_MIN_SIMILARITY
            )
            android.util.Log.i(
                "SubMatch",
                "[hearing] candidate label=\"${sub.label}\" provider=${sub.provider} cues=${cues.size} " +
                    "samples=${samples.size} score=${"%.2f".format(s)}"
            )
            ScoredCandidate(sub, s, 0L)
        }
    }

    private fun beginMatch(status: PlayerMessage) {
        _uiState.value = _uiState.value.copy(isFindingBestMatch = true, matchStatus = status)
    }

    private fun updateMatchStatus(status: PlayerMessage) {
        if (_uiState.value.matchStatus != status) {
            _uiState.value = _uiState.value.copy(matchStatus = status)
        }
    }

    private fun startMatchListening() {
        hasManualSubtitleSelection = true
        subtitleSelectionJob?.cancel()
        cancelSubtitleLocalization()
        translationManager.isEnabled = false
        targetSubtitleLangCode.takeIf { it.isNotBlank() }?.let { geminiLiveService.targetLanguageCode = it }
        geminiLiveService.connect()
        _uiState.value = _uiState.value.copy(
            isLiveAudioTranslating = true,
            selectedSubtitle = null,
            isAiTranslating = false,
            subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
        )
    }

    private fun endMatch() {
        geminiLiveService.disconnect()
        _uiState.value = _uiState.value.copy(
            isFindingBestMatch = false,
            isLiveAudioTranslating = false,
            matchStatus = null
        )
    }

    private fun restoreSubtitle(subtitle: Subtitle?) {
        _uiState.value = _uiState.value.copy(
            selectedSubtitle = subtitle,
            subtitleSelectionNonce = _uiState.value.subtitleSelectionNonce + 1
        )
    }

    // ── Per-stream match cache ──────────────────────────────────────────────────

    private data class CachedSubMatch(
        val key: String,
        val provider: String,
        val id: String,
        val offsetMs: Long = 0L // constant-offset rescue delay to re-apply (0 = as authored)
    )

    /**
     * Identity of the currently playing file, stable across sessions. Torrent infoHash+fileIdx is
     * best; videoHash/filename+size next; the raw URL last (debrid links change per resolve, so a
     * URL key may simply never hit again — harmless).
     */
    private fun currentStreamCacheKey(): String? {
        val stream = _uiState.value.selectedStream
        if (stream != null) {
            val infoHash = stream.infoHash
            if (!infoHash.isNullOrBlank()) return "ih:$infoHash:${stream.fileIdx ?: -1}"
            val hints = stream.behaviorHints
            val videoHash = hints?.videoHash
            if (!videoHash.isNullOrBlank()) return "vh:$videoHash"
            val filename = hints?.filename
            if (!filename.isNullOrBlank()) {
                return "fn:$filename:${hints.videoSize ?: stream.sizeBytes ?: -1}"
            }
        }
        val url = stream?.url?.takeIf { it.isNotBlank() } ?: _uiState.value.selectedStreamUrl
        return url?.takeIf { it.isNotBlank() }?.let { "url:${it.hashCode()}" }
    }

    private suspend fun loadMatchCache(): MutableList<CachedSubMatch> {
        val json = context.settingsDataStore.data.first()[subtitleMatchCacheKey]
        if (json.isNullOrBlank()) return mutableListOf()
        val type = TypeToken.getParameterized(MutableList::class.java, CachedSubMatch::class.java).type
        return runCatching { gson.fromJson<MutableList<CachedSubMatch>>(json, type) }
            .getOrNull() ?: mutableListOf()
    }

    private suspend fun readCachedMatch(): CachedSubMatch? {
        val key = currentStreamCacheKey() ?: return null
        return loadMatchCache().lastOrNull { it.key == key }
    }

    /** Remember [subtitle] as the match for the current stream (null = forget the entry). */
    private fun writeCachedMatch(subtitle: Subtitle?, offsetMs: Long = 0L) {
        val key = currentStreamCacheKey() ?: return
        viewModelScope.launch {
            runCatching {
                val cache = loadMatchCache()
                val removed = cache.removeAll { it.key == key }
                if (subtitle == null && !removed) return@runCatching
                if (subtitle != null) cache.add(CachedSubMatch(key, subtitle.provider, subtitle.id, offsetMs))
                while (cache.size > MATCH_CACHE_MAX_ENTRIES) cache.removeAt(0)
                context.settingsDataStore.edit { it[subtitleMatchCacheKey] = gson.toJson(cache) }
            }.onFailure { Log.w("SubMatch", "match cache write failed: ${it.message}") }
        }
    }

    /**
     * A manual track pick overrides what the matcher remembered for this stream: remember the
     * user's choice if it's an addon sub in the AI target language, otherwise drop the entry so
     * the stale match can't fight the user on the next playback.
     */
    private fun updateMatchCacheForManualPick(subtitle: Subtitle) {
        val target = targetSubtitleLangCode
        val cacheable = target.isNotBlank() && !subtitle.isEmbedded && !subtitle.isBitmap &&
            subtitle.url.isNotBlank() && normalizeLanguage(subtitle.lang) == target
        writeCachedMatch(if (cacheable) subtitle else null)
    }

    /**
     * Writes downloaded subtitle text to a small local cache file and returns a copy of [sub]
     * pointing at it (file:// URI). ExoPlayer then side-loads the matched subtitle instantly
     * during the MediaItem rebuild instead of re-downloading it from a possibly slow/flaky addon
     * server — which is what used to leave the player stuck buffering after a match. Also
     * normalizes the content (UTF-8, gunzipped) as a side effect.
     */
    private fun localizeSubtitle(sub: Subtitle, raw: String, offsetMs: Long = 0L): Subtitle {
        return runCatching {
            // Constant-offset rescue: bake the delay into the served text so the correction lives
            // in the file, independent of the player's delay knob (no state leaks across track/
            // source switches). The id gets an "…#ofs<ms>" marker so this shifted copy is a
            // DISTINCT track — otherwise preload mode would override to the un-shifted preloaded
            // copy that shares the base id.
            val text = if (offsetMs != 0L) SubtitleSyncMatcher.shiftTimestamps(raw, offsetMs) else raw
            val dir = java.io.File(context.cacheDir, "matched_subs").apply { mkdirs() }
            // Keep the directory bounded; files are ~100KB and keyed stably per subtitle.
            dir.listFiles()?.sortedBy { it.lastModified() }?.dropLast(39)?.forEach { it.delete() }
            val ext = if (text.trimStart().startsWith("WEBVTT")) "vtt" else "srt"
            val idBase = "${sub.provider}|${sub.id}"
            val fileKey = if (offsetMs != 0L) "$idBase$MATCH_OFFSET_ID_MARKER$offsetMs" else idBase
            val file = java.io.File(dir, "${fileKey.hashCode().toUInt()}.$ext")
            file.writeText(text)
            val newId = if (offsetMs != 0L) "${sub.id}$MATCH_OFFSET_ID_MARKER$offsetMs" else sub.id
            sub.copy(id = newId, url = file.toURI().toString())
        }.getOrDefault(sub)
    }

    /**
     * Preload mode only: cap the addon-list wait at 10s — completed addons are harvested, the
     * stragglers are cancelled and DROPPED for this playback (user decision: a slow addon should
     * cost its own subs, not everyone's startup time). Null (classic wait-for-all) when the mode
     * is off: with no prepare gate there is nothing to hold up, so dropping subs buys nothing.
     */
    private fun subtitleFetchSoftDeadline(): Long? =
        if (subtitlePreloadEnabled) SUBTITLE_FETCH_SOFT_DEADLINE_MS else null

    /** Streams the names of still-pending subtitle addons to the loading screen (preload mode). */
    private fun pendingSubtitleAddonsReporter(): ((List<String>) -> Unit)? =
        if (!subtitlePreloadEnabled) null else { pending ->
            _uiState.value = _uiState.value.copy(pendingSubtitleAddons = pending)
        }

    /**
     * "Preload Subtitles" mode: download the preferred-language addon subtitles to local cache
     * files (same UTF-8/gunzipped [localizeSubtitle] files the match scan serves) so PlayerScreen
     * can side-load them ALL into the initial MediaItem — switching between them is then a track
     * override with no MediaItem rebuild. The historical "never preload all subs" revert was about
     * ExoPlayer eagerly fetching 30+ REMOTE configs at prepare; local files make that eager read
     * free and encoding-safe. Incremental: re-invocations only download subs not yet localized.
     * Every path ends with [PlayerUiState.subtitlePreloadComplete] = true so the prepare gate in
     * PlayerScreen never waits on a dead job (it also has its own timeout).
     */
    private fun preloadSubtitles(subs: List<Subtitle>) {
        if (!subtitlePreloadEnabled) return
        val target = targetSubtitleLangCode
        val alreadyPreloaded = _uiState.value.preloadedSubtitles
            .map { "${it.provider}|${it.id}|${it.lang}" }
            .toSet()
        val candidates = if (target.isBlank()) emptyList() else subs
            .filter { sub ->
                !sub.isEmbedded && !sub.isBitmap && sub.url.isNotBlank() &&
                    !sub.url.startsWith("file:") &&
                    normalizeLanguage(sub.lang) == target &&
                    "${sub.provider}|${sub.id}|${sub.lang}" !in alreadyPreloaded
            }
            .take(MAX_PRELOAD_SUBS)
        if (candidates.isEmpty()) {
            if (!_uiState.value.subtitlePreloadComplete) {
                _uiState.value = _uiState.value.copy(subtitlePreloadComplete = true)
            }
            return
        }
        subtitlePreloadJob?.cancel()
        subtitlePreloadJob = viewModelScope.launch {
            val localized = candidates.map { sub ->
                async(Dispatchers.IO) {
                    SubtitleSyncMatcher.loadRaw(sub.url, sub.lang)
                        ?.takeIf { it.isNotBlank() }
                        ?.let { raw -> localizeSubtitle(sub, raw) }
                        ?.takeIf { it.url.startsWith("file:") }
                }
            }.awaitAll().filterNotNull()
            android.util.Log.d(
                "SubMatch",
                "preload done: ${localized.size}/${candidates.size} candidates localized (target=$target)"
            )
            _uiState.value = _uiState.value.copy(
                preloadedSubtitles = (_uiState.value.preloadedSubtitles + localized)
                    .distinctBy { "${it.provider}|${it.id}|${it.lang}" },
                subtitlePreloadComplete = true
            )
        }
    }

    private fun showMatchToast(message: PlayerMessage) {
        _uiState.value = _uiState.value.copy(matchToast = message)
    }

    fun dismissMatchToast() {
        _uiState.value = _uiState.value.copy(matchToast = null)
    }

    fun disableSubtitles() {
        hasManualSubtitleSelection = true
        // Turning subtitles Off is an explicit user action — stop any running "Find best match"/
        // hearing scan (it also disconnects the Gemini Live audio stream). Without this, the scan
        // kept running with no way to cancel it. Mirrors selectSubtitle()'s user-action path.
        userPickedSubtitle = true
        cancelFindBestMatch()
        subtitleSelectionJob?.cancel()
        cancelSubtitleLocalization()
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
        retryPlayback()
    }

    fun reloadStreams() {
        val resumeTarget = lastKnownPositionMs.takeIf { it > 0L } ?: currentStartPositionMs
        mediaLoadJob?.cancel()
        _uiState.value = _uiState.value.copy(
            error = null,
            isLoading = false,
            isLoadingStreams = true,
            sourceSearchActive = true,
            streams = emptyList(),
            selectedStream = null,
            selectedStreamUrl = null,
            streamProgress = 0f,
            streamLoadPhase = null
        )
        loadMedia(
            mediaType = currentMediaType,
            mediaId = currentMediaId,
            seasonNumber = currentSeason,
            episodeNumber = currentEpisode,
            displaySeasonNumber = currentDisplaySeason,
            displayEpisodeNumber = currentDisplayEpisode,
            animeQueryOverride = currentAnimeQueryOverride,
            providedImdbId = currentImdbId,
            providedStreamUrl = null,
            preferredAddonId = currentPreferredAddonId,
            preferredSourceName = currentPreferredSourceName,
            preferredBingeGroup = currentPreferredBingeGroup,
            startPositionMs = resumeTarget,
            isLiveStreamPlayback = currentIsLiveStreamPlayback,
            forceRefresh = true,
            airDate = currentAirDate
        )
    }

    fun retryPlayback() {
        val stream = _uiState.value.selectedStream
        if (stream != null) {
            _uiState.value = _uiState.value.copy(error = null)
            val resumeTarget = lastKnownPositionMs.takeIf { it > 0L } ?: currentStartPositionMs
            selectStream(stream, resumeTarget)
        } else {
            reloadStreams()
        }
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

    fun saveProgress(
        position: Long,
        duration: Long,
        progressPercent: Int,
        isPlaying: Boolean,
        playbackState: Int
    ): Job? {
        if (duration <= 0) return null

        // On pause/stop, replace an in-flight periodic save. During normal playback,
        // debounce by returning the save that is already running.
        if (!isPlaying || playbackState == Player.STATE_ENDED) {
            progressSaveJob?.cancel()
        } else if (progressSaveJob?.isActive == true) {
            return progressSaveJob
        }

        val job = viewModelScope.launch(Dispatchers.IO) {
            val currentTime = System.currentTimeMillis()
            val progressFraction = (progressPercent / 100f).coerceIn(0f, 1f)
            val selectedStream = _uiState.value.selectedStream
            val streamAddonIdForCheck = selectedStream?.addonId?.takeIf { it.isNotBlank() }
            val isLiveStreamOrSports = SportsAddonCapabilities.isLiveStreamOrSportsItem(
                mediaType = currentMediaType,
                id = currentMediaId,
                streamAddonId = streamAddonIdForCheck,
                title = currentTitle,
                isLiveStream = currentIsLiveStreamPlayback,
                addons = currentInstalledAddons
            )

            // Scrobble start/pause/updates with debounce
            if (!isLiveStreamOrSports && isPlaying && !lastIsPlaying) {
                try {
                    remoteSyncManager.scrobbleStart(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode,
                        isAnime = isCurrentAnime()
                    )
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e

                    // Scrobble start failed
                }
                lastScrobbleTime = currentTime
            } else if (!isLiveStreamOrSports && !isPlaying && lastIsPlaying) {
                try {
                    remoteSyncManager.scrobblePause(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode,
                        isAnime = isCurrentAnime()
                    )
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e

                    // Scrobble pause immediate failed
                }
                lastScrobbleTime = currentTime
            } else if (!isLiveStreamOrSports && isPlaying && currentTime - lastScrobbleTime >= SCROBBLE_UPDATE_INTERVAL_MS) {
                // Periodic heartbeat. SIMKL intentionally ignores this because it enforces a strict write lock.
                try {
                    remoteSyncManager.scrobbleProgress(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode,
                        isAnime = isCurrentAnime()
                    )
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e

                    // Scrobble update failed
                }
                lastScrobbleTime = currentTime
            }

            // Save to Supabase watch history (debounced + on pause/stop)
            // Skip saving progress at watched threshold — the mark-watched block below handles
            // the next-episode CW entry, and saving the finished episode's full position here
            // would contaminate the next episode's resume time via show-level history fallback.
            val isAtWatchedThreshold = progressPercent >= Constants.WATCHED_THRESHOLD
            val durationSeconds = (duration / 1000L).coerceAtLeast(1L)
            val positionSeconds = (position / 1000L).coerceAtLeast(0L)
            val hasSeekJump = lastWatchHistorySavedPositionSeconds >= 0L &&
                kotlin.math.abs(positionSeconds - lastWatchHistorySavedPositionSeconds) >= 20L
            val shouldPersistWatchHistory = !isPlaying ||
                currentTime - lastWatchHistorySaveTime >= WATCH_HISTORY_UPDATE_INTERVAL_MS ||
                hasSeekJump
            if (shouldPersistWatchHistory && !isAtWatchedThreshold && !isLiveStreamOrSports) {
                lastWatchHistorySaveTime = currentTime
                lastWatchHistorySavedPositionSeconds = positionSeconds
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
                    streamTitle = streamTitle,
                    sessionStartTime = playbackSessionStartTime
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
                        displaySeason = currentDisplaySeason,
                        displayEpisode = currentDisplayEpisode,
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
            if (!isLiveStreamOrSports && !hasMarkedWatched &&
                (playbackState == Player.STATE_ENDED || progressPercent >= Constants.WATCHED_THRESHOLD)
            ) {
                hasMarkedWatched = true
                try {
                    remoteSyncManager.scrobbleStop(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode,
                        isAnime = isCurrentAnime()
                    )
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e

                    // Scrobble stop failed
                }
                try {
                    val safeSeason = currentSeason
                    val safeEpisode = currentEpisode
                    remoteSyncManager.dismissContinueWatching(
                        currentMediaType,
                        currentMediaId,
                        safeSeason,
                        safeEpisode
                    )
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
                    if (e is kotlinx.coroutines.CancellationException) throw e

                    // Delete playback failed
                }

                // Remove the finished episode from CW cache so it doesn't resurface
                // before the next refresh cycle picks up the server-side changes.
                runCatching {
                    traktRepository.removeFromContinueWatchingCache(
                        currentMediaId,
                        currentSeason,
                        currentEpisode,
                        currentMediaType
                    )
                }

                // When a TV episode completes, immediately save the next episode to
                // local Continue Watching so CW isn't empty between episodes.
                val cwSeason = currentSeason
                val cwEpisode = currentEpisode
                if (currentMediaType == MediaType.TV && cwSeason != null && cwEpisode != null) {
                    try {
                        val nextIdentity = runCatching {
                            animeMapper.resolveAnimeSeasonStructure(currentMediaId)
                                ?.nextAfterDisplay(
                                    currentDisplaySeason ?: cwSeason,
                                    currentDisplayEpisode ?: cwEpisode
                                )
                        }.getOrNull()
                        val nextSeason = nextIdentity?.tmdbSeason ?: cwSeason
                        val nextEpisode = nextIdentity?.tmdbEpisode ?: (cwEpisode + 1)
                        traktRepository.saveLocalContinueWatching(
                            mediaType = currentMediaType,
                            tmdbId = currentMediaId,
                            title = currentItemTitle.ifEmpty { currentTitle },
                            posterPath = currentPoster,
                            backdropPath = currentBackdrop,
                            season = nextSeason,
                            episode = nextEpisode,
                            displaySeason = nextIdentity?.displaySeason ?: nextSeason,
                            displayEpisode = nextIdentity?.displayEpisode ?: nextEpisode,
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
        }

        progressSaveJob = job
        job.invokeOnCompletion {
            if (progressSaveJob === job) {
                progressSaveJob = null
            }
        }
        return job
    }

    suspend fun saveProgressAndWait(
        position: Long,
        duration: Long,
        progressPercent: Int,
        isPlaying: Boolean,
        playbackState: Int
    ) {
        // Let an in-flight save finish before starting the final transition save.
        // This avoids cancelling after hasMarkedWatched was set but before the
        // corresponding remote/history writes completed.
        progressSaveJob?.takeIf { it.isActive }?.join()

        val finalJob = saveProgress(
            position = position,
            duration = duration,
            progressPercent = progressPercent,
            isPlaying = isPlaying,
            playbackState = playbackState
        )
        finalJob?.join()
    }

    private var progressSaveJob: Job? = null
    private var mediaLoadJob: Job? = null
    private var subtitleRefreshJob: Job? = null
    private var vodAppendJob: Job? = null
    private var homeServerAppendJob: Job? = null
    private var subtitleSelectionJob: Job? = null
    /** Downloads+decodes a manually picked subtitle before it is served (see selectSubtitle). */
    private var subtitleLocalizeJob: Job? = null
    /** Bumped whenever an in-flight localization must not be applied any more. */
    private var subtitleLocalizeGeneration = 0L
    private var streamPrewarmJob: Job? = null
    private var focusedStreamPrewarmJob: Job? = null
    private var streamSelectionJob: Job? = null
    private var playbackErrorReportJob: Job? = null
    private var primaryStreamResolutionFinal: Boolean = false
    private var lastTopPrewarmKey: String = ""

    private fun canStartAutoplay(): Boolean = _uiState.value.selectedStream == null &&
        _uiState.value.selectedStreamUrl.isNullOrBlank() && streamSelectionJob?.isActive != true

    private fun sourceLookupStillActive(currentJob: Job? = null): Boolean {
        val supplementalStillLoading =
            (homeServerAppendJob != null && homeServerAppendJob !== currentJob && homeServerAppendJob?.isActive == true) ||
                (vodAppendJob != null && vodAppendJob !== currentJob && vodAppendJob?.isActive == true)
        return !primaryStreamResolutionFinal || supplementalStillLoading
    }

    private fun finishSupplementalSourceLookupIfReady(currentJob: Job?, errorMessage: PlayerMessage) {
        val state = _uiState.value
        val stillActive = sourceLookupStillActive(currentJob)
        if (state.streams.isNotEmpty() || !state.selectedStreamUrl.isNullOrBlank()) {
            _uiState.value = state.copy(
                isLoading = false,
                isLoadingStreams = false,
                sourceSearchActive = stillActive,
                streamProgress = null,
                streamLoadPhase = null,
                error = if (!stillActive && canStartAutoplay() &&
                    eligiblePlayerAutoplayStreams(state.streams, autoPlayMinimumQuality).isEmpty()
                ) PlayerMessage.Res(R.string.stream_no_sources_match) else state.error
            )
            return
        }

        if (!stillActive) {
            _uiState.value = state.copy(
                isLoading = false,
                isLoadingStreams = false,
                sourceSearchActive = false,
                streamProgress = null,
                streamLoadPhase = null,
                error = state.error ?: errorMessage,
                isSetupError = false
            )
        }
    }

    private suspend fun appendHomeServerSourcesInBackground(
        mediaType: MediaType,
        imdbId: String?,
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
                errorMessage = PlayerMessage.Res(R.string.player_error_no_streams_media_servers)
            )
            return
        }
        val latest = _uiState.value.streams

        val updated = (latest + validSources)
            .distinctBy(::providerScopedStreamIdentity)
        val preferredLanguage = _uiState.value.preferredAudioLanguage.ifBlank { "en" }
        val sortedStreams = sortStreamsByQualityAndSize(updated, preferredLanguage)
        val shouldAutoplayHomeServer = _uiState.value.selectedStreamUrl.isNullOrBlank()
        val currentJob = currentCoroutineContext()[Job]
        _uiState.value = _uiState.value.copy(
            streams = sortedStreams,
            isLoadingStreams = false,
            sourceSearchActive = sourceLookupStillActive(currentJob),
            error = _uiState.value.error.takeUnless { canStartAutoplay() },
            isSetupError = false,
            streamProgress = null,
            streamLoadPhase = null
        )
        prewarmTopStreams(sortedStreams, preferredLanguage)
        if (shouldAutoplayHomeServer) {
            autoplaySelectBest(sortedStreams, preferredLanguage)
            finishSupplementalSourceLookupIfReady(currentJob, PlayerMessage.Res(R.string.player_error_no_streams_media_servers))
        }
    }

    private suspend fun appendVodSourceInBackground(
        mediaType: MediaType,
        imdbId: String?,
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
                errorMessage = PlayerMessage.Res(R.string.player_error_no_streams_other_source)
            )
            return
        }
        val latest = _uiState.value.streams

        val updated = (latest + validVodSources)
            .distinctBy(::providerScopedStreamIdentity)
        val preferredLanguage = _uiState.value.preferredAudioLanguage.ifBlank { "en" }
        val sortedStreams = sortStreamsByQualityAndSize(updated, preferredLanguage)
        val shouldAutoplayVod = _uiState.value.selectedStreamUrl.isNullOrBlank()
        val currentJob = currentCoroutineContext()[Job]
        _uiState.value = _uiState.value.copy(
            streams = sortedStreams,
            isLoadingStreams = false,
            sourceSearchActive = sourceLookupStillActive(currentJob),
            error = _uiState.value.error.takeUnless { canStartAutoplay() },
            isSetupError = false,
            streamProgress = null,
            streamLoadPhase = null
        )
        prewarmTopStreams(sortedStreams, preferredLanguage)
        if (shouldAutoplayVod) {
            autoplaySelectBest(sortedStreams, preferredLanguage)
            finishSupplementalSourceLookupIfReady(currentJob, PlayerMessage.Res(R.string.player_error_no_streams_other_source))
        }
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
                _uiState.value = _uiState.value.copy(
                    isLoadingStreams = false,
                    sourceSearchActive = false
                )
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
                    title = currentItemTitle,
                    animeQueryOverride = currentAnimeQueryOverride
                )
            }

            val allStreams = result.streams
                .filter { stream ->
                    val u = stream.url?.trim().orEmpty()
                    u.isNotBlank() && !u.startsWith("magnet:", ignoreCase = true)
                }

            val existingVod = _uiState.value.streams.filter(::isSupplementalStream)
            val activeStreamList = listOfNotNull(
                _uiState.value.selectedStream,
                if (playbackUrl.isNotBlank()) StreamSource(source = "Direct", addonName = "Direct", quality = "Direct", size = "", url = playbackUrl) else null
            )
            val mergedStreams = (allStreams + existingVod + activeStreamList)
                .distinctBy(::providerScopedStreamIdentity)

            val preferredLanguage = _uiState.value.preferredAudioLanguage.ifBlank { "en" }
            val sortedStreams = sortStreamsByQualityAndSize(mergedStreams, preferredLanguage)
            val selectedMatch = findProvidedSelectedStreamMatch(sortedStreams, playbackUrl)

            val shouldAutoplay = _uiState.value.selectedStreamUrl.isNullOrBlank()
            val prevSource = _uiState.value.selectedStream?.source.orEmpty()
            val selectedForState = if (shouldAutoplay) {
                _uiState.value.selectedStream
            } else {
                selectedMatch ?: _uiState.value.selectedStream
            }
            val newSource = selectedForState?.source.orEmpty()
            _uiState.value = _uiState.value.copy(
                streams = sortedStreams,
                selectedStream = selectedForState,
                isLoadingStreams = false,
                sourceSearchActive = false,
                streamProgress = null,
                streamLoadPhase = null
            )
            prewarmTopStreams(sortedStreams, preferredLanguage)
            if (shouldAutoplay) {
                pickPreferredStream(sortedStreams, preferredLanguage)?.let { selectStream(it) }
            }
            // Re-run subtitle selection if stream source just became known
            if (prevSource.isBlank() && newSource.isNotBlank()) {
                scheduleSubtitleSelection(currentOriginalLanguage)
            }
        } catch (_: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoadingStreams = false,
                sourceSearchActive = false
            )
        }
    }

    private fun isSameStreamUrl(candidate: String?, target: String): Boolean {
        val candidateTrimmed = candidate?.trim().orEmpty()
        val targetTrimmed = target.trim()
        if (candidateTrimmed.isBlank() || targetTrimmed.isBlank()) return false
        if (candidateTrimmed.equals(targetTrimmed, ignoreCase = true)) return true
        return normalizeStreamUrlKey(candidateTrimmed) == normalizeStreamUrlKey(targetTrimmed)
    }

    private fun findProvidedSelectedStreamMatch(
        streams: List<StreamSource>,
        playbackUrl: String
    ): StreamSource? {
        streams.firstOrNull { stream ->
            isSameStreamUrl(stream.url, playbackUrl)
        }?.let { return it }

        val preferredGroup = currentPreferredBingeGroup
        val preferredAddon = currentPreferredAddonId
        val preferredSource = currentPreferredSourceName
        if (preferredGroup.isNullOrBlank() && preferredAddon.isNullOrBlank() && preferredSource.isNullOrBlank()) {
            return null
        }

        preferredGroup?.let { group ->
            streams.firstOrNull { stream ->
                stream.behaviorHints?.bingeGroup == group &&
                    (preferredAddon?.let { stream.addonId == it } ?: true)
            }?.let { return it }
        }

        preferredSource?.let { source ->
            streams.firstOrNull { stream ->
                val addonMatch = preferredAddon?.let { stream.addonId == it } ?: true
                addonMatch && sameSourceTitle(stream.source, source)
            }?.let { return it }
        }

        return preferredAddon?.let { addonId ->
            streams.firstOrNull { it.addonId == addonId }
        }
    }

    private fun sameSourceTitle(left: String, right: String): Boolean {
        val normalizedLeft = normalizeSourceTitle(left)
        val normalizedRight = normalizeSourceTitle(right)
        if (normalizedLeft.isBlank() || normalizedRight.isBlank()) return false
        return normalizedLeft == normalizedRight
    }

    private fun normalizeSourceTitle(value: String): String {
        return value
            .trim()
            .replace(PlayerRegexes.WHITESPACE, " ")
            .lowercase()
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

        // ── "Find best match" tuning ──────────────────────────────────────────
        private const val MATCH_HARD_CAP_MS = 90_000L    // give up this long after the FIRST cue/sample appeared
        private const val MATCH_NO_SPEECH_CAP_MS = 240_000L // how long to wait for speech to exist at all
        private const val MATCH_TOTAL_CAP_MS = 600_000L  // absolute ceiling on a single scan
        private const val MATCH_MAX_REF_INTERVAL_MS = 20_000L // longer "cues" are seek artifacts
        private const val MATCH_MAX_SAMPLES = 12         // stop early once we have this many lines
        private const val MATCH_MIN_REF_INTERVALS = 2    // built-in mode: min reference cues to decide (1 is too noisy)
        private const val MATCH_TARGET_REF_INTERVALS = 8 // built-in mode: stop collecting once we have this many
        private const val MATCH_EARLY_ACCEPT_REFS = 4    // min refs before the incremental early-accept check
        private const val MATCH_EARLY_ACCEPT_SCORE = 0.8 // a candidate this well-synced ends collection early
        private const val MATCH_FAST_ACCEPT_SPAN_MS = 15_000L // decisive candidates accept at half the span
        private const val MATCH_FAST_ACCEPT_REFS = 5    // …with at least this many refs
        private const val MATCH_FAST_ACCEPT_SCORE = 0.85 // …scoring at least this
        private const val MATCH_MIN_REF_SPAN_MS = 30_000L // refs must cover this much video before any accept
        // A REJECT verdict requires at least this many buffered (authored-timing) reference
        // intervals — realtime-only references systematically under-score synced subs (§2 skew).
        private const val MATCH_MIN_BUFFERED_FOR_REJECT = 4
        // Preload mode: max wait for the addon subtitle lists before slow addons are dropped.
        private const val SUBTITLE_FETCH_SOFT_DEADLINE_MS = 10_000L
        private const val MATCH_TRACK_SWITCH_SETTLE_MS = 1_500L // let the reference-track switch reach the renderer
        private const val MATCH_BUFFERED_REF_INTERVALS = 12  // max upcoming cues to read from the buffer per poll
        private const val MATCH_EMBEDDED_WAIT_MS = 8_000L    // grace period for async embedded tracks
        private const val MATCH_SOURCES_WAIT_MS = 15_000L    // max continuous-playback wait for embedded + addon subs
        private const val MATCH_PLAYBACK_WAIT_CAP_MS = 90_000L // give up if playback never starts at all
        private const val MATCH_MIN_SAMPLES = 4          // need at least this many to decide
        private const val MATCH_LATENCY_MS = 800L        // AI hearing lag: audio time ≈ arrival − this
        private const val MATCH_TOLERANCE_MS = 1_500L    // cue search window around the audio time
        private const val MATCH_MIN_SIMILARITY = 0.35    // word-overlap needed to count a cue as a hit
        // Separate accept thresholds per reference type: calibrated against user-verified subs —
        // a confirmed-synced sub scored 0.71, a confirmed-offset one 0.65 (union-coverage
        // scoring, 12 refs). Below the bar, staying on AI translation beats a visibly-off sub.
        // Hearing hit-ratios run much lower, so they keep the permissive bar.
        private const val MATCH_SUCCESS_THRESHOLD_TIMING = 0.70
        private const val MATCH_SUCCESS_THRESHOLD_HEARING = 0.30
        private const val MATCH_MAX_CANDIDATES = 10      // cap downloaded candidates (best release-name matches first)
        private const val MATCH_CACHE_MAX_ENTRIES = 50   // per-stream remembered matches (oldest evicted)

        // Constant-offset rescue: a candidate that fails at offset 0 but is a perfectly-cut sub
        // with a UNIFORM delay gets shifted into sync instead of rejected (common addon defect).
        // Accept/reject scoring widens each candidate cue by this before measuring overlap, so a
        // genuinely-synced sub isn't docked for sub-second caption pre-roll or different cue
        // boundaries (SDH reference vs merged dialogue sub). The offset SEARCH stays strict (0) to
        // keep its peaks sharp. Kept modest so a truly mis-synced sub (≥~1s off) still fails here
        // and is instead caught by the offset search.
        private const val MATCH_OVERLAP_TOLERANCE_MS = 400L
        // Orphan-drop only fires when at least this fraction of reference windows stay covered by
        // some candidate; below it, keep all windows (widespread non-coverage = bad sync, not SDH).
        private const val MATCH_ORPHAN_KEEP_MIN_FRACTION = 0.7
        private const val MATCH_OFFSET_MIN_MS = 300L      // below this the sub is "already aligned" — normal path owns it
        private const val MATCH_OFFSET_MAX_MS = 10_000L   // above this a "constant offset" is implausible
        private const val MATCH_OFFSET_ACCEPT = 0.80      // single candidate: strict bar (no corroboration available)
        private const val MATCH_OFFSET_EARLY_REFS = 6     // min refs before the mid-scan offset check runs
        // Cross-candidate corroboration: the candidates are the same episode from different addons, so
        // a REAL global offset shows up in several of them; a spurious one (search noise) does not.
        // When ≥N agree on an offset within a tolerance, we trust it and accept at a lower bar than a
        // lone candidate — interval-overlap scoring caps well below 1.0 when the reference (finely
        // split CC) and candidate (merged lines) are segmented differently, even at the true offset.
        private const val MATCH_OFFSET_CORROBORATE_MS = 400L   // agreement window between candidate offsets
        private const val MATCH_OFFSET_CORROBORATE_MIN = 2     // min candidates that must agree
        private const val MATCH_OFFSET_CORROBORATE_FLOOR = 0.62 // min corrected score to be an eligible voter
        // Accept bar once an offset is corroborated. Lower than the normal 0.70 timing bar on purpose:
        // the agreement of ≥2 independent addon files IS the evidence, and interval-overlap scoring is
        // structurally capped (~0.71 here) when the reference (finely-split CC) and candidate (merged
        // lines) are segmented differently even at the perfect offset. A corroborated match may accept
        // below 0.70 — see the offset clause in findBestSubtitleMatch's accept test.
        private const val MATCH_OFFSET_CORROBORATED_ACCEPT = 0.68
        // Marker appended to a served copy's id when an offset was baked in (e.g. "…#ofs2000").
        // Forces a distinct track id (so preload mode rebuilds onto the shifted file rather than
        // overriding to the un-shifted preloaded copy); the menu strips it to match rows by base id.
        const val MATCH_OFFSET_ID_MARKER = "#ofs"

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


private object PlayerViewModelRegexes {
    val HTML_TAG_REGEX = Regex("<[^>]*>")
    val MULTI_SPACE_REGEX = Regex("\\s+")
}
