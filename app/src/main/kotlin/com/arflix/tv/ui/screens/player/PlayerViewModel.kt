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
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.util.Constants
import com.arflix.tv.util.settingsDataStore
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlayerUiState(
    val isLoading: Boolean = true,
    val isLoadingStreams: Boolean = false,
    val isLoadingSubtitles: Boolean = false,  // Show when fetching subtitles
    val isResolvingStream: Boolean = false,  // New: show when resolving debrid URL
    val title: String = "",
    val backdropUrl: String? = null,
    val logoUrl: String? = null,
    val streams: List<StreamSource> = emptyList(),
    val subtitles: List<Subtitle> = emptyList(),
    val selectedStream: StreamSource? = null,
    val selectedStreamUrl: String? = null,
    val selectedSubtitle: Subtitle? = null,
    val savedPosition: Long = 0,
    val preferredAudioLanguage: String = "en",
    val error: String? = null
)

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mediaRepository: MediaRepository,
    private val streamRepository: StreamRepository,
    private val traktRepository: TraktRepository,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val tmdbApi: TmdbApi
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
    private var lastScrobbleTime: Long = 0
    private var lastWatchHistorySaveTime: Long = 0
    private var lastIsPlaying: Boolean = false
    private var hasMarkedWatched: Boolean = false

    private val SCROBBLE_UPDATE_INTERVAL_MS = 20_000L
    private val WATCH_HISTORY_UPDATE_INTERVAL_MS = 15_000L

    private val DEFAULT_SUBTITLE_KEY = stringPreferencesKey("default_subtitle")
    private val SUBTITLE_USAGE_KEY = stringPreferencesKey("subtitle_usage_v1")
    private val gson = Gson()
    private val knownLanguageCodes = setOf(
        "en", "es", "fr", "de", "it", "pt", "nl", "ru", "zh", "ja", "ko",
        "ar", "hi", "tr", "pl", "sv", "no", "da", "fi", "el", "cs", "hu",
        "ro", "th", "vi", "id", "he"
    )
    
    fun loadMedia(
        mediaType: MediaType,
        mediaId: Int,
        seasonNumber: Int?,
        episodeNumber: Int?,
        providedStreamUrl: String?
    ) {
        currentMediaType = mediaType
        currentMediaId = mediaId
        currentSeason = seasonNumber
        currentEpisode = episodeNumber
        hasMarkedWatched = false
        lastIsPlaying = false
        lastScrobbleTime = 0
        lastWatchHistorySaveTime = 0
        currentOriginalLanguage = mediaRepository.getCachedItem(mediaType, mediaId)?.originalLanguage

        viewModelScope.launch {
            val preferredAudioLanguage = resolvePreferredAudioLanguage()
            _uiState.value = PlayerUiState(
                isLoading = true,
                isLoadingStreams = true,
                preferredAudioLanguage = preferredAudioLanguage
            )

            // If stream URL provided, use it directly - INSTANT
            if (providedStreamUrl != null) {
                Log.d("PlayerViewModel", "Using provided stream URL: $providedStreamUrl")
                // Fetch saved position for resume playback
                val savedPosition = watchHistoryRepository.getProgress(mediaType, mediaId, seasonNumber, episodeNumber)
                    ?.position_seconds?.times(1000) ?: 0L
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    selectedStreamUrl = providedStreamUrl,
                    savedPosition = savedPosition
                )
                // Fetch metadata in background
                launch { fetchMediaMetadata(mediaType, mediaId) }
                return@launch
            }

            try {
                // INSTANT MODE: Fetch streams in parallel with metadata
                // Start metadata fetch in background (non-blocking)
                launch { fetchMediaMetadata(mediaType, mediaId) }

                // Fetch saved position from watch history (for resume playback)
                val savedPositionDeferred = async {
                    watchHistoryRepository.getProgress(mediaType, mediaId, seasonNumber, episodeNumber)
                        ?.position_seconds?.times(1000) ?: 0L
                }

                // Get IMDB ID as fast as possible
                val cachedImdbId = mediaRepository.getCachedImdbId(mediaType, mediaId)
                val imdbId = cachedImdbId ?: resolveImdbId(mediaType, mediaId)
                if (imdbId.isNullOrBlank()) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoadingStreams = false,
                        error = "Unable to resolve IMDB ID. Try again."
                    )
                    return@launch
                }
                mediaRepository.cacheImdbId(mediaType, mediaId, imdbId)
                Log.d("PlayerViewModel", "Got IMDB ID: $imdbId - fetching streams NOW")

                // Fetch streams IMMEDIATELY
                val result = if (mediaType == MediaType.MOVIE) {
                    streamRepository.resolveMovieStreams(imdbId)
                } else {
                    streamRepository.resolveEpisodeStreams(
                        imdbId,
                        seasonNumber ?: 1,
                        episodeNumber ?: 1
                    )
                }

                Log.d("PlayerViewModel", "Stream result: ${result.streams.size} streams, ${result.subtitles.size} subtitles")

                // FAST MODE: Show ALL streams immediately without filtering
                val allStreams = result.streams
                    .filter { stream -> stream.url != null }
                    .sortedWith(
                        compareByDescending<StreamSource> { it.isCached }
                            .thenByDescending { it.sizeBytes ?: 0L }
                            .thenByDescending { qualityScore(it.quality, it.source) }
                    )

                Log.d("PlayerViewModel", "INSTANT: Loaded ${allStreams.size} streams")

                // Get saved position for resume playback
                val savedPosition = savedPositionDeferred.await()
                Log.d("PlayerViewModel", "Saved position: ${savedPosition}ms")

                // Update UI with streams immediately
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingStreams = false,
                    streams = allStreams,
                    subtitles = result.subtitles,
                    savedPosition = savedPosition,
                    error = if (allStreams.isEmpty()) "No streams found. Try adding more addons in Settings." else null
                )

                // Auto-select first stream IMMEDIATELY
                val preferredStream = pickPreferredStream(allStreams, preferredAudioLanguage)
                if (preferredStream != null) {
                    Log.d("PlayerViewModel", "Auto-selecting stream: ${preferredStream.source.take(50)}...")
                    selectStream(preferredStream)
                }

                // Apply subtitle preference in background (non-blocking)
                launch {
                    val preferredSub = getDefaultSubtitle()
                    applyPreferredSubtitle(preferredSub, result.subtitles, currentOriginalLanguage)
                    _uiState.value = _uiState.value.copy(isLoadingSubtitles = false)
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
                backdropUrl = tvDetails.backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
                posterUrl = tvDetails.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                currentOriginalLanguage = tvDetails.originalLanguage ?: currentOriginalLanguage
            } else {
                val movieDetails = details as com.arflix.tv.data.api.TmdbMovieDetails
                title = movieDetails.title
                backdropUrl = movieDetails.backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
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
            Log.e("PlayerViewModel", "Failed to fetch metadata: ${e.message}")
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

    private suspend fun getDefaultSubtitle(): String {
        return try {
            val prefs = context.settingsDataStore.data.first()
            // Default to English instead of Off to ensure subtitles are shown
            prefs[DEFAULT_SUBTITLE_KEY] ?: "English"
        } catch (_: Exception) {
            "English"
        }
    }

    private fun applyPreferredSubtitle(preference: String, subtitles: List<Subtitle>, fallbackLanguage: String?) {
        Log.d("PlayerViewModel", "Applying preferred subtitle: '$preference', available: ${subtitles.size}")
        if (preference.equals("Off", ignoreCase = true)) {
            Log.d("PlayerViewModel", "Subtitle preference is Off, not selecting any")
            return
        }

        // Normalize language codes for matching
        val normalizedPref = normalizeLanguage(preference)
        val normalizedFallback = fallbackLanguage
            ?.let { normalizeLanguage(it) }
            ?.takeIf { it.isNotBlank() && it != normalizedPref }
        Log.d("PlayerViewModel", "Normalized preference: '$normalizedPref'")

        fun findMatch(target: String): Subtitle? {
            return subtitles.firstOrNull { sub ->
                val normalizedSubLang = normalizeLanguage(sub.lang)
                normalizedSubLang == target
            } ?: subtitles.firstOrNull { sub ->
                val normalizedSubLabel = normalizeLanguage(sub.label)
                normalizedSubLabel == target
            }
        }

        // Prioritize: 1. Exact match, 2. Contains match, 3. English fallback if preference is English
        val match = findMatch(normalizedPref)
            ?: if (normalizedPref == "en") {
                // Fallback for English: Try "eng", "en", "English"
                subtitles.firstOrNull { sub ->
                    sub.lang.equals("en", ignoreCase = true) ||
                        sub.lang.equals("eng", ignoreCase = true) ||
                        sub.label.contains("English", ignoreCase = true)
                }
            } else null
            ?: normalizedFallback?.let { findMatch(it) }

        if (match != null) {
            Log.d("PlayerViewModel", "Auto-selected subtitle: ${match.label} (${match.lang})")
            _uiState.value = _uiState.value.copy(selectedSubtitle = match)
        } else {
            Log.d("PlayerViewModel", "No matching subtitle found for preference '$preference'")
        }
    }

    private fun qualityScore(quality: String, source: String): Int {
        val q = quality.uppercase()
        val s = source.uppercase()
        val base = when {
            q.contains("4K") || q.contains("2160") -> 4000
            q.contains("1080") -> 3000
            q.contains("720") -> 2000
            q.contains("480") -> 1000
            else -> 0
        }
        val dvBonus = if (q.contains("DOLBY VISION") || s.contains("DOLBY VISION") || q.contains("DV") || s.contains("DV")) 200 else 0
        val hdrBonus = if (q.contains("HDR") || s.contains("HDR")) 100 else 0
        return base + dvBonus + hdrBonus
    }

    private fun resolvePreferredAudioLanguage(): String {
        val normalized = currentOriginalLanguage
            ?.let { normalizeLanguage(it) }
            ?.takeIf { it.isNotBlank() }
            ?: "en"
        return if (normalized in knownLanguageCodes) normalized else "en"
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

    private fun pickPreferredStream(
        streams: List<StreamSource>,
        preferredLanguage: String
    ): StreamSource? {
        if (streams.isEmpty()) return null
        val scored = streams.map { it to streamLanguageScore(it, preferredLanguage) }
        val first = scored.firstOrNull() ?: return null
        if (first.second > 0) return first.first

        val bestScore = scored.maxOfOrNull { it.second } ?: return first.first
        if (bestScore <= 0) return first.first
        return scored.firstOrNull { it.second == bestScore }?.first ?: first.first
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
            lowerLang == "spanish" || lowerLang.startsWith("spanish") || lowerLang == "español" -> "es"
            lowerLang == "french" || lowerLang.startsWith("french") || lowerLang == "français" -> "fr"
            lowerLang == "german" || lowerLang.startsWith("german") || lowerLang == "deutsch" -> "de"
            lowerLang == "italian" || lowerLang.startsWith("italian") -> "it"
            lowerLang == "portuguese" || lowerLang.startsWith("portuguese") -> "pt"
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
            // ISO 639-1 codes (2 letter)
            lowerLang.length == 2 -> lowerLang
            // ISO 639-2 codes (3 letter)
            lowerLang == "eng" -> "en"
            lowerLang == "spa" -> "es"
            lowerLang == "fra" || lowerLang == "fre" -> "fr"
            lowerLang == "deu" || lowerLang == "ger" -> "de"
            lowerLang == "ita" -> "it"
            lowerLang == "por" -> "pt"
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
            else -> lowerLang
        }
    }
    
    // Track current stream index for auto-retry
    private var currentStreamIndex = 0

    /**
     * Select a stream for playback - resolves debrid URLs on-demand
     * If resolution fails, automatically tries the next stream
     */
    fun selectStream(stream: StreamSource, autoRetryOnFail: Boolean = true) {
        viewModelScope.launch {
            val url = stream.url ?: return@launch

            // Find the index of this stream
            val streams = _uiState.value.streams
            val streamIndex = streams.indexOf(stream)
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
                    Log.d("PlayerViewModel", "Adding ${newSubs.size} subtitles from stream source")
                    _uiState.value = _uiState.value.copy(subtitles = existingSubs + newSubs)
                }
            }

            // Check if this needs debrid resolution
            // Must match ALL debrid service URL patterns from Torrentio
            val needsResolution = url.contains("/torbox/") ||
                                  url.contains("/realdebrid/") ||
                                  url.contains("/rd/") ||
                                  url.contains("/debridlink/") ||
                                  url.contains("/premiumize/") ||
                                  url.contains("/alldebrid/") ||
                                  url.startsWith("magnet:")

            if (needsResolution) {
                // Show resolving state
                _uiState.value = _uiState.value.copy(
                    selectedStream = stream,
                    isResolvingStream = true,
                    error = null
                )

                Log.d("PlayerViewModel", "Resolving stream on-demand: ${stream.source.take(50)}...")

                try {
                    val resolvedStream = streamRepository.resolveStreamForPlayback(stream)
                    if (resolvedStream != null) {
                        Log.d("PlayerViewModel", "Stream resolved successfully!")
                        _uiState.value = _uiState.value.copy(
                            selectedStream = resolvedStream,
                            selectedStreamUrl = resolvedStream.url,
                            isResolvingStream = false
                        )
                    } else {
                        Log.e("PlayerViewModel", "Failed to resolve stream")
                        // Auto-try next stream if enabled
                        if (autoRetryOnFail) {
                            tryNextStream(streams, "Resolution returned null")
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isResolvingStream = false,
                                error = "Failed to resolve stream. Try another source."
                            )
                        }
                    }
                } catch (e: Exception) {
                    Log.e("PlayerViewModel", "Error resolving stream: ${e.message}")
                    // Auto-try next stream if enabled
                    if (autoRetryOnFail) {
                        tryNextStream(streams, e.message ?: "Unknown error")
                    } else {
                        _uiState.value = _uiState.value.copy(
                            isResolvingStream = false,
                            error = "Error: ${e.message}"
                        )
                    }
                }
            } else {
                // Direct URL - use immediately
                Log.d("PlayerViewModel", "Direct stream URL - no resolution needed")
                _uiState.value = _uiState.value.copy(
                    selectedStream = stream,
                    selectedStreamUrl = stream.url
                )
            }
        }
    }

    /**
     * Try the next available stream when current one fails
     */
    private fun tryNextStream(streams: List<StreamSource>, reason: String) {
        val nextIndex = currentStreamIndex + 1
        Log.d("PlayerViewModel", "Stream failed ($reason), trying next: $nextIndex / ${streams.size}")

        if (nextIndex < streams.size && nextIndex < 10) { // Limit to 10 retries
            currentStreamIndex = nextIndex
            val nextStream = streams[nextIndex]
            Log.d("PlayerViewModel", "Auto-selecting stream #$nextIndex: ${nextStream.source.take(50)}...")
            selectStream(nextStream, autoRetryOnFail = true)
        } else {
            Log.e("PlayerViewModel", "No more streams to try or retry limit reached")
            _uiState.value = _uiState.value.copy(
                isResolvingStream = false,
                error = "Failed to resolve stream. Try another source."
            )
        }
    }

    fun updateEmbeddedSubtitles(embedded: List<Subtitle>) {
        val current = _uiState.value.subtitles.filter { !it.isEmbedded }
        // Avoid duplicates based on ID
        val newEmbedded = embedded.filter { new -> 
            current.none { it.id == new.id } 
        }
        
        if (newEmbedded.isEmpty()) return
        
        val merged = current + newEmbedded
        Log.d("PlayerViewModel", "Merged ${newEmbedded.size} embedded subtitles. Total: ${merged.size}")
        
        _uiState.value = _uiState.value.copy(subtitles = merged)
        
        // Re-apply preference if currently selected is null or we are in initial state
        // This ensures embedded English subs get picked up if external ones failed
        if (_uiState.value.selectedSubtitle == null) {
             viewModelScope.launch {
                 val preferred = getDefaultSubtitle()
                 applyPreferredSubtitle(preferred, merged, currentOriginalLanguage)
             }
        }
    }

    fun selectSubtitle(subtitle: Subtitle) {
        _uiState.value = _uiState.value.copy(selectedSubtitle = subtitle)
        recordSubtitleUsage(subtitle)
    }
    
    fun disableSubtitles() {
        _uiState.value = _uiState.value.copy(selectedSubtitle = null)
    }

    private fun recordSubtitleUsage(subtitle: Subtitle) {
        viewModelScope.launch {
            val raw = subtitle.lang.ifBlank { subtitle.label }
            if (raw.isBlank()) return@launch
            val key = normalizeLanguage(raw)
            if (key.isBlank()) return@launch

            val prefs = context.settingsDataStore.data.first()
            val json = prefs[SUBTITLE_USAGE_KEY]
            val type = TypeToken.getParameterized(MutableMap::class.java, String::class.java, Int::class.javaObjectType).type
            val map: MutableMap<String, Int> = if (!json.isNullOrBlank()) {
                gson.fromJson(json, type)
            } else {
                mutableMapOf()
            }

            map[key] = (map[key] ?: 0) + 1
            context.settingsDataStore.edit { it[SUBTITLE_USAGE_KEY] = gson.toJson(map) }
        }
    }
    
    fun dismissError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun retry() {
        loadMedia(currentMediaType, currentMediaId, currentSeason, currentEpisode, null)
    }

    fun saveProgress(position: Long, duration: Long, progressPercent: Int, isPlaying: Boolean, playbackState: Int) {
        if (duration <= 0) return

        viewModelScope.launch {
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
                    Log.w("PlayerViewModel", "Scrobble start failed", e)
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
                    Log.w("PlayerViewModel", "Scrobble pause immediate failed", e)
                }
                lastScrobbleTime = currentTime
            } else if (isPlaying && currentTime - lastScrobbleTime >= SCROBBLE_UPDATE_INTERVAL_MS) {
                try {
                    traktRepository.scrobblePause(
                        mediaType = currentMediaType,
                        tmdbId = currentMediaId,
                        progress = progressPercent.toFloat(),
                        season = currentSeason,
                        episode = currentEpisode
                    )
                } catch (e: Exception) {
                    Log.w("PlayerViewModel", "Scrobble pause failed", e)
                }
                lastScrobbleTime = currentTime
            }

            // Save to Supabase watch history (debounced + on pause/stop)
            if (!isPlaying || currentTime - lastWatchHistorySaveTime >= WATCH_HISTORY_UPDATE_INTERVAL_MS || progressPercent >= Constants.WATCHED_THRESHOLD) {
                lastWatchHistorySaveTime = currentTime
                val durationSeconds = (duration / 1000L).coerceAtLeast(1L)
                val positionSeconds = (position / 1000L).coerceAtLeast(0L)
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
                    position = positionSeconds
                )
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
                    Log.w("PlayerViewModel", "Scrobble stop failed", e)
                }
                try {
                    if (currentMediaType == MediaType.TV && currentSeason != null && currentEpisode != null) {
                        traktRepository.deletePlaybackForEpisode(currentMediaId, currentSeason!!, currentEpisode!!)
                    } else if (currentMediaType == MediaType.MOVIE) {
                        traktRepository.deletePlaybackForContent(currentMediaId, currentMediaType)
                    }
                } catch (e: Exception) {
                    Log.w("PlayerViewModel", "Delete playback failed", e)
                }
            }

            lastIsPlaying = isPlaying
        }
    }
}
