@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.player

import android.content.Context
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import com.arflix.tv.BuildConfig
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.components.WaveLoadingDots
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.PurpleDark
import com.arflix.tv.ui.theme.PurpleLight
import com.arflix.tv.ui.theme.PurplePrimary
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import androidx.compose.runtime.rememberCoroutineScope
import java.util.concurrent.TimeUnit

/**
 * Netflix-style Player UI for Android TV
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PlayerScreen(
    mediaType: MediaType,
    mediaId: Int,
    seasonNumber: Int? = null,
    episodeNumber: Int? = null,
    imdbId: String? = null,
    streamUrl: String? = null,
    preferredAddonId: String? = null,
    preferredSourceName: String? = null,
    startPositionMs: Long? = null,
    viewModel: PlayerViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
    onPlayNext: (Int, Int, String?, String?) -> Unit = { _, _, _, _ -> }
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    var isPlaying by remember { mutableStateOf(false) }
    var isBuffering by remember { mutableStateOf(true) }
    var hasPlaybackStarted by remember { mutableStateOf(false) }  // Track if playback has actually started
    var showControls by remember { mutableStateOf(true) }
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    var progress by remember { mutableFloatStateOf(0f) }

    // Skip overlay state - shows +10/-10 without showing full controls
    var skipAmount by remember { mutableIntStateOf(0) }
    var showSkipOverlay by remember { mutableStateOf(false) }
    var lastSkipTime by remember { mutableLongStateOf(0L) }
    var skipStartPosition by remember { mutableLongStateOf(0L) }  // Position when skipping started
    var isControlScrubbing by remember { mutableStateOf(false) }
    var scrubPreviewPosition by remember { mutableLongStateOf(0L) }
    var controlsSeekJob by remember { mutableStateOf<Job?>(null) }

    // Volume state
    val audioManager = remember { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    var currentVolume by remember { mutableIntStateOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)) }
    val maxVolume = remember { audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) }
    var showVolumeIndicator by remember { mutableStateOf(false) }
    var isMuted by remember { mutableStateOf(false) }
    var volumeBeforeMute by remember { mutableIntStateOf(currentVolume) }

    // Focus requesters for TV navigation
    val playButtonFocusRequester = remember { FocusRequester() }
    val trackbarFocusRequester = remember { FocusRequester() }
    val subtitleButtonFocusRequester = remember { FocusRequester() }
    val sourceButtonFocusRequester = remember { FocusRequester() }
    val nextEpisodeButtonFocusRequester = remember { FocusRequester() }
    val containerFocusRequester = remember { FocusRequester() }
    val skipIntroFocusRequester = remember { FocusRequester() }

    // Focus state - 0=Play, 1=Subtitles
    var focusedButton by remember { mutableIntStateOf(0) }
    var showSubtitleMenu by remember { mutableStateOf(false) }
    var showSourceMenu by remember { mutableStateOf(false) }
    var subtitleMenuIndex by remember { mutableIntStateOf(0) }
    var subtitleMenuTab by remember { mutableIntStateOf(0) } // 0 = Subtitles, 1 = Audio

    // Audio tracks from ExoPlayer
    var audioTracks by remember { mutableStateOf<List<AudioTrackInfo>>(emptyList()) }
    var selectedAudioIndex by remember { mutableIntStateOf(0) }

    // Error modal focus
    var errorModalFocusIndex by remember { mutableIntStateOf(0) }

    // Buffering watchdog - detect stuck buffering
    var bufferingStartTime by remember { mutableStateOf<Long?>(null) }
    val bufferingTimeoutMs = 12_000L // Mid-playback timeout for stuck buffering
    var userSelectedSourceManually by remember { mutableStateOf(false) }
    val allowAutomaticSourceFallback = false
    val initialBufferingTimeoutMs = remember(uiState.selectedStream, userSelectedSourceManually) {
        estimateInitialStartupTimeoutMs(
            stream = uiState.selectedStream,
            isManualSelection = userSelectedSourceManually
        )
    }

    // Track stream selection time (for future diagnostics)
    var streamSelectedTime by remember { mutableStateOf<Long?>(null) }
    var playbackIssueReported by remember { mutableStateOf(false) }
    var startupRecoverAttempted by remember { mutableStateOf(false) }
    var startupHardFailureReported by remember { mutableStateOf(false) }
    var startupSameSourceRetryCount by remember { mutableIntStateOf(0) }
    var startupSameSourceRefreshAttempted by remember { mutableStateOf(false) }
    var startupUrlLock by remember { mutableStateOf<String?>(null) }
    val heavyStartupMaxRetries = 6
    var rebufferRecoverAttempted by remember { mutableStateOf(false) }
    var autoAdvanceAttempts by remember { mutableIntStateOf(0) }
    var triedStreamIndexes by remember { mutableStateOf<Set<Int>>(emptySet()) }
    var isAutoAdvancing by remember { mutableStateOf(false) }
    var lastProgressReportSecond by remember { mutableLongStateOf(-1L) }

    // Load media
    LaunchedEffect(mediaType, mediaId, seasonNumber, episodeNumber, imdbId, preferredAddonId, preferredSourceName, startPositionMs) {
        playbackIssueReported = false
        startupRecoverAttempted = false
        startupHardFailureReported = false
        startupSameSourceRetryCount = 0
        startupSameSourceRefreshAttempted = false
        startupUrlLock = null
        rebufferRecoverAttempted = false
        autoAdvanceAttempts = 0
        triedStreamIndexes = emptySet()
        isAutoAdvancing = false
        userSelectedSourceManually = false
        viewModel.loadMedia(
            mediaType = mediaType,
            mediaId = mediaId,
            seasonNumber = seasonNumber,
            episodeNumber = episodeNumber,
            providedImdbId = imdbId,
            providedStreamUrl = streamUrl,
            preferredAddonId = preferredAddonId,
            preferredSourceName = preferredSourceName,
            startPositionMs = startPositionMs
        )
    }

    // Track current stream index for auto-advancement on error
    var currentStreamIndex by remember { mutableIntStateOf(0) }
    val tryAdvanceToNextStream: () -> Boolean = {
        val streams = uiState.streams
        if (streams.size <= 1) {
            false
        } else {
            val nextIndex = (1 until streams.size)
                .map { offset -> (currentStreamIndex + offset) % streams.size }
                .firstOrNull { idx ->
                    streams[idx].url?.isNotBlank() == true &&
                        idx !in triedStreamIndexes
                } ?: -1

            if (nextIndex < 0) {
                false
            } else {
                autoAdvanceAttempts += 1
                currentStreamIndex = nextIndex
                triedStreamIndexes = triedStreamIndexes + nextIndex
                userSelectedSourceManually = false
                playbackIssueReported = false
                startupRecoverAttempted = false
                startupHardFailureReported = false
                startupSameSourceRetryCount = 0
                startupSameSourceRefreshAttempted = false
                startupUrlLock = null
                rebufferRecoverAttempted = false
                isAutoAdvancing = true
                viewModel.selectStream(streams[nextIndex])
                true
            }
        }
    }

    val baseRequestHeaders = remember {
        mapOf(
            "Accept" to "*/*",
            "Accept-Encoding" to "identity",
            "Connection" to "keep-alive"
        )
    }
    val httpDataSourceFactory = remember {
        DefaultHttpDataSource.Factory()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
            .setAllowCrossProtocolRedirects(true)
            .setConnectTimeoutMs(20_000)
            .setReadTimeoutMs(120_000)
            .setDefaultRequestProperties(baseRequestHeaders)
    }

    // ExoPlayer - configured for maximum codec compatibility and smooth streaming
    val exoPlayer = remember {
        val mediaSourceFactory = DefaultMediaSourceFactory(context)
            .setDataSourceFactory(httpDataSourceFactory)

        // FAST START buffering - minimal buffer before starting, build up while playing
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                10_000,
                300_000,
                4_000,
                4_000
            )
            .setTargetBufferBytes(100 * 1024 * 1024)
            .setPrioritizeTimeOverSizeThresholds(true)
            .setBackBuffer(15_000, true) // Keep 15 seconds of back buffer
            .build()

        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setRenderersFactory(
                DefaultRenderersFactory(context)
                    // Prefer hardware decoders first, use extension decoders as fallback.
                    // This is more stable for heavy 4K remux streams on TV devices.
                    .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
                    // Enable fallback decoders for any format issues
                    .setEnableDecoderFallback(true)
            )
            .setLoadControl(loadControl)
            // Configure track selection for maximum compatibility
            .setTrackSelector(
                androidx.media3.exoplayer.trackselection.DefaultTrackSelector(context).apply {
                    parameters = buildUponParameters()
                        // Prefer original audio language when available
                        .setPreferredAudioLanguage(uiState.preferredAudioLanguage)
                        // Allow decoder fallback for unsupported codecs
                        .setAllowVideoMixedMimeTypeAdaptiveness(true)
                        .setAllowVideoNonSeamlessAdaptiveness(true)
                        // Allow any audio/video codec combination
                        .setAllowAudioMixedMimeTypeAdaptiveness(true)
                        // Disable HDR requirement - play HDR as SDR if needed
                        .setForceLowestBitrate(false)
                        // Stay within device capabilities to avoid endless startup buffering
                        // on unsupported 4K remux tracks (codec/profile/audio combination).
                        .setExceedVideoConstraintsIfNecessary(false)
                        .setExceedAudioConstraintsIfNecessary(false)
                        .setExceedRendererCapabilitiesIfNecessary(false)
                        .build()
                }
            )
            .setAudioAttributes(
                // Configure audio attributes for movie/TV playback
                androidx.media3.common.AudioAttributes.Builder()
                    .setUsage(androidx.media3.common.C.USAGE_MEDIA)
                    .setContentType(androidx.media3.common.C.AUDIO_CONTENT_TYPE_MOVIE)
                    .build(),
                /* handleAudioFocus = */ true
            )
            .build().apply {
                // Ensure volume is at maximum
                volume = 1.0f

                // Add error listener to try next stream on codec errors
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        val stateStr = when (playbackState) {
                            Player.STATE_IDLE -> "IDLE"
                            Player.STATE_BUFFERING -> "BUFFERING"
                            Player.STATE_READY -> "READY"
                            Player.STATE_ENDED -> "ENDED"
                            else -> "UNKNOWN($playbackState)"
                        }
                        if (BuildConfig.DEBUG) {
                        }
                    }

                    override fun onIsPlayingChanged(playing: Boolean) {
                        if (BuildConfig.DEBUG) {
                        }
                    }

                    override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                        // Source/decoder/network errors on startup should fail over to another source.
                        // Error codes: https://developer.android.com/reference/androidx/media3/common/PlaybackException
                        val isSourceError = error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODER_INIT_FAILED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FAILED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_UNSPECIFIED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT

                        if (isSourceError) {
                            val heavy = isLikelyHeavyStream(uiState.selectedStream)
                            val timeoutMessage = buildString {
                                append(error.message.orEmpty())
                                append(' ')
                                append(error.cause?.message.orEmpty())
                            }.lowercase()
                            val isTimeoutError =
                                error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT ||
                                    error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT ||
                                    "timeout" in timeoutMessage ||
                                    "timed out" in timeoutMessage ||
                                    "sockettimeout" in timeoutMessage ||
                                    "etimedout" in timeoutMessage

                            // For heavy sources, retry same source first instead of failing immediately.
                            if (!hasPlaybackStarted && heavy && isTimeoutError && startupSameSourceRetryCount < heavyStartupMaxRetries) {
                                startupSameSourceRetryCount += 1
                                val wasPlaying = playWhenReady
                                stop()
                                prepare()
                                playWhenReady = wasPlaying
                                return
                            }
                            if (!hasPlaybackStarted && heavy && isTimeoutError) {
                                // One-time full re-resolve of same source to refresh debrid URL/headers.
                                if (!startupSameSourceRefreshAttempted) {
                                    startupSameSourceRefreshAttempted = true
                                    uiState.selectedStream?.let { viewModel.selectStream(it) }
                                    return
                                }
                            }

                            if (!hasPlaybackStarted &&
                                allowAutomaticSourceFallback &&
                                !userSelectedSourceManually &&
                                tryAdvanceToNextStream()
                            ) {
                                return
                            }
                            if (!playbackIssueReported) {
                                playbackIssueReported = true
                                viewModel.reportPlaybackError("This source failed to play. Please choose another source.")
                            }
                        }
                    }

                    override fun onTracksChanged(tracks: androidx.media3.common.Tracks) {
                        // Extract audio tracks from ExoPlayer
                        val extractedAudioTracks = mutableListOf<AudioTrackInfo>()
                        var trackIndex = 0
                        tracks.groups.forEachIndexed { groupIndex, group ->
                            if (group.type == C.TRACK_TYPE_AUDIO) {
                                for (i in 0 until group.length) {
                                    val format = group.getTrackFormat(i)
                                    val track = AudioTrackInfo(
                                        index = trackIndex,
                                        groupIndex = groupIndex,
                                        trackIndex = i,
                                        language = format.language,
                                        label = format.label,
                                        channelCount = format.channelCount,
                                        sampleRate = format.sampleRate,
                                        codec = format.sampleMimeType
                                    )
                                    extractedAudioTracks.add(track)
                                    trackIndex++
                                }
                            }
                        }
                        audioTracks = extractedAudioTracks

                        // Find currently selected audio track
                        val currentAudioGroup = tracks.groups.find { it.type == C.TRACK_TYPE_AUDIO && it.isSelected }
                        if (currentAudioGroup != null) {
                            val currentGroupIndex = tracks.groups.indexOf(currentAudioGroup)
                            val selectedTrackIndex = (0 until currentAudioGroup.length)
                                .firstOrNull { currentAudioGroup.isTrackSelected(it) }
                            val matchingTrack = extractedAudioTracks.firstOrNull { track ->
                                track.groupIndex == currentGroupIndex &&
                                    (selectedTrackIndex == null || track.trackIndex == selectedTrackIndex)
                            }
                            if (matchingTrack != null) {
                                selectedAudioIndex = extractedAudioTracks.indexOf(matchingTrack)
                            }
                        }
                        
                        // Extract embedded subtitles
                        val embeddedSubs = mutableListOf<Subtitle>()
                        tracks.groups.forEachIndexed { groupIndex, group ->
                            if (group.type == C.TRACK_TYPE_TEXT) {
                                for (i in 0 until group.length) {
                                    val format = group.getTrackFormat(i)
                                    val lang = format.language ?: "und"
                                    val label = format.label ?: getFullLanguageName(lang)
                                    
                                    embeddedSubs.add(Subtitle(
                                        id = "embedded_${groupIndex}_$i",
                                        url = "", 
                                        lang = lang,
                                        label = label,
                                        isEmbedded = true,
                                        groupIndex = groupIndex,
                                        trackIndex = i
                                    ))
                                }
                            }
                        }
                        if (embeddedSubs.isNotEmpty()) {
                            viewModel.updateEmbeddedSubtitles(embeddedSubs)
                        }
                    }
                })
            }
    }

    val queueControlsSeek: (Long) -> Unit = { deltaMs ->
        val basePosition = if (isControlScrubbing) {
            scrubPreviewPosition
        } else {
            exoPlayer.currentPosition.coerceAtLeast(0L)
        }
        val unclamped = (basePosition + deltaMs).coerceAtLeast(0L)
        val targetPosition = if (duration > 0L) unclamped.coerceAtMost(duration) else unclamped
        scrubPreviewPosition = targetPosition
        isControlScrubbing = true
        controlsSeekJob?.cancel()
        controlsSeekJob = coroutineScope.launch {
            delay(260)
            exoPlayer.seekTo(scrubPreviewPosition)
            isControlScrubbing = false
        }
    }

    val commitControlsSeekNow: () -> Unit = {
        if (isControlScrubbing) {
            controlsSeekJob?.cancel()
            exoPlayer.seekTo(scrubPreviewPosition)
            isControlScrubbing = false
        }
    }

    LaunchedEffect(uiState.preferredAudioLanguage) {
        val trackSelector = exoPlayer.trackSelector as? androidx.media3.exoplayer.trackselection.DefaultTrackSelector
        if (trackSelector != null) {
            val params = trackSelector.buildUponParameters()
                .setPreferredAudioLanguage(uiState.preferredAudioLanguage)
                .build()
            trackSelector.parameters = params
        }
    }

    LaunchedEffect(uiState.frameRateMatchingMode) {
        val configuredStrategy = resolveFrameRateStrategyForMode(uiState.frameRateMatchingMode)
        val effectiveStrategy = if (isFrameRateMatchingSupported(context)) {
            configuredStrategy
        } else {
            resolveFrameRateOffStrategy()
        }
        runCatching {
            exoPlayer.javaClass
                .getMethod("setVideoChangeFrameRateStrategy", Int::class.javaPrimitiveType)
                .invoke(exoPlayer, effectiveStrategy)
        }
    }

    LaunchedEffect(uiState.selectedStreamUrl, uiState.streams) {
        val currentUrl = uiState.selectedStreamUrl ?: return@LaunchedEffect
        val idx = uiState.streams.indexOfFirst { it.url == currentUrl }
        if (idx >= 0) {
            currentStreamIndex = idx
            if (isAutoAdvancing) {
                triedStreamIndexes = triedStreamIndexes + idx
                isAutoAdvancing = false
            } else {
                triedStreamIndexes = setOf(idx)
                autoAdvanceAttempts = 0
            }
        }
    }

    // Keep track of the last external subtitle URL applied to the current media item.
    var lastAppliedExternalSubtitleUrl by remember { mutableStateOf<String?>(null) }
    var lastAppliedExternalSubtitleNonce by remember { mutableIntStateOf(-1) }

    // Update player when stream URL changes - also add subtitle if selected
    LaunchedEffect(uiState.selectedStreamUrl) {
        val url = uiState.selectedStreamUrl
        if (BuildConfig.DEBUG) {
        }
        if (url != null) {
            val isNewStartupSource = startupUrlLock != url
            if (isNewStartupSource) {
                startupUrlLock = url
                startupRecoverAttempted = false
                startupHardFailureReported = false
                startupSameSourceRetryCount = 0
                startupSameSourceRefreshAttempted = false
            }
            val streamHeaders = uiState.selectedStream
                ?.behaviorHints
                ?.proxyHeaders
                ?.request
                .orEmpty()
                .filterKeys { it.isNotBlank() }
            httpDataSourceFactory.setDefaultRequestProperties(baseRequestHeaders + streamHeaders)

            // Track when stream was selected
            streamSelectedTime = System.currentTimeMillis()
            bufferingStartTime = null
            hasPlaybackStarted = false  // Reset for new stream
            playbackIssueReported = false
            rebufferRecoverAttempted = false

            // New stream means any previously-applied external subtitle config is stale.
            lastAppliedExternalSubtitleUrl = null
            lastAppliedExternalSubtitleNonce = -1

            // Always start playback with video-only media item first.
            // External subtitles are applied after startup to avoid blocking stream start.
            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(url))
                .build()
            val resumePosition = uiState.savedPosition
            if (resumePosition > 0L) {
                exoPlayer.setMediaItem(mediaItem, resumePosition)
            } else {
                exoPlayer.setMediaItem(mediaItem)
            }
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true

            // Enable text track display
            val subtitle = uiState.selectedSubtitle
            if (subtitle != null) {
                exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                    .buildUpon()
                    .setPreferredTextLanguage(subtitle.lang)
                    .setSelectUndeterminedTextLanguage(true)
                    .setIgnoredTextSelectionFlags(0)
                    .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                    .build()
            }

        }
    }
    // Apply subtitle when user selects a different one.
    LaunchedEffect(uiState.selectedSubtitle, uiState.subtitleSelectionNonce, hasPlaybackStarted) {
        val streamUrl = uiState.selectedStreamUrl ?: return@LaunchedEffect
        val subtitle = uiState.selectedSubtitle

        if (subtitle == null) {
            // Disable text tracks. If we previously injected an external subtitle config into the media item,
            // rebuild without it so ExoPlayer doesn't keep trying to load it.
            exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                .buildUpon()
                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true)
                .build()

            if (lastAppliedExternalSubtitleUrl != null && hasPlaybackStarted) {
                val currentPosition = exoPlayer.currentPosition
                val wasPlaying = exoPlayer.isPlaying
                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(streamUrl))
                    .build()
                exoPlayer.setMediaItem(mediaItem, currentPosition)
                lastAppliedExternalSubtitleUrl = null
                lastAppliedExternalSubtitleNonce = -1
                exoPlayer.prepare()
                exoPlayer.playWhenReady = wasPlaying
            }
            return@LaunchedEffect
        }

        if (subtitle.isEmbedded) {
            // Embedded subtitle selection via track override.
            lastAppliedExternalSubtitleUrl = null
            val groupIndex = subtitle.groupIndex
            val trackIndex = subtitle.trackIndex
            if (groupIndex != null && trackIndex != null) {
                val tracks = exoPlayer.currentTracks
                if (groupIndex < tracks.groups.size) {
                    val trackGroup = tracks.groups[groupIndex].mediaTrackGroup
                    exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                        .buildUpon()
                        .setOverrideForType(
                            androidx.media3.common.TrackSelectionOverride(trackGroup, trackIndex)
                        )
                        .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                        .build()
                }
            } else {
                // Fallback: enable text tracks; ExoPlayer will pick based on parameters/flags.
                exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                    .buildUpon()
                    .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                    .build()
            }
            return@LaunchedEffect
        }

        // Avoid first-second rebuffer: auto-selected external subtitles (nonce == 0)
        // should not rebuild the media item during startup.
        // External subtitles will still work when user explicitly selects one.
        if (uiState.subtitleSelectionNonce == 0) {
            return@LaunchedEffect
        }

        // External subtitle: rebuild media item.
        if (!hasPlaybackStarted) {
            // Do not rebuild media item before initial playback starts.
            // This prevents startup loops on slow/debrid streams.
            return@LaunchedEffect
        }
        val subtitleUrl = subtitle.url.trim().let { if (it.startsWith("//")) "https:$it" else it }
        if (subtitleUrl.isBlank()) return@LaunchedEffect

        // Fast path only when this exact subtitle has already been applied for the same explicit selection action.
        if (subtitleUrl == lastAppliedExternalSubtitleUrl &&
            uiState.subtitleSelectionNonce == lastAppliedExternalSubtitleNonce
        ) {
            exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                .buildUpon()
                .setPreferredTextLanguage(subtitle.lang)
                .setSelectUndeterminedTextLanguage(true)
                .setIgnoredTextSelectionFlags(0)
                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                .build()
            return@LaunchedEffect
        }

        val cleanUrl = subtitleUrl.substringBefore('?')
        val mimeType = when {
            cleanUrl.endsWith(".vtt", ignoreCase = true) -> MimeTypes.TEXT_VTT
            cleanUrl.endsWith(".srt", ignoreCase = true) -> MimeTypes.APPLICATION_SUBRIP
            cleanUrl.endsWith(".srt.gz", ignoreCase = true) -> MimeTypes.APPLICATION_SUBRIP
            cleanUrl.endsWith(".ass", ignoreCase = true) -> MimeTypes.TEXT_SSA
            cleanUrl.endsWith(".ssa", ignoreCase = true) -> MimeTypes.TEXT_SSA
            cleanUrl.endsWith(".ttml", ignoreCase = true) -> MimeTypes.APPLICATION_TTML
            cleanUrl.endsWith(".dfxp", ignoreCase = true) -> MimeTypes.APPLICATION_TTML
            else -> MimeTypes.APPLICATION_SUBRIP
        }

        val subtitleConfig = MediaItem.SubtitleConfiguration.Builder(Uri.parse(subtitleUrl))
            .setMimeType(mimeType)
            .setLanguage(subtitle.lang)
            .setLabel(subtitle.label)
            .setSelectionFlags(C.SELECTION_FLAG_DEFAULT)
            .setRoleFlags(C.ROLE_FLAG_SUBTITLE)
            .build()

        val currentPosition = exoPlayer.currentPosition
        val wasPlaying = exoPlayer.isPlaying

        val mediaItem = MediaItem.Builder()
            .setUri(Uri.parse(streamUrl))
            .setSubtitleConfigurations(listOf(subtitleConfig))
            .build()

        exoPlayer.setMediaItem(mediaItem, currentPosition)
        lastAppliedExternalSubtitleUrl = subtitleUrl
        lastAppliedExternalSubtitleNonce = uiState.subtitleSelectionNonce
        exoPlayer.prepare()
        exoPlayer.addListener(object : Player.Listener {
            private var consumed = false
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (!consumed && playbackState == Player.STATE_READY) {
                    consumed = true
                    exoPlayer.seekTo(currentPosition)
                    exoPlayer.playWhenReady = wasPlaying
                    exoPlayer.removeListener(this)
                }
            }
        })

        exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
            .buildUpon()
            .setPreferredTextLanguage(subtitle.lang)
            .setSelectUndeterminedTextLanguage(true)
            .setIgnoredTextSelectionFlags(0)
            .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
            .build()
    }

    // Auto-hide controls and return focus to container
    LaunchedEffect(showControls, isPlaying) {
        if (showControls && isPlaying && !showSubtitleMenu && !showSourceMenu) {
            delay(5000)
            showControls = false
            // Return focus to container so it can receive key events
            delay(100)
            try {
                containerFocusRequester.requestFocus()
            } catch (_: Exception) {}
        }
    }

    // Request focus on play button when controls are shown
    LaunchedEffect(showControls) {
        if (showControls && !showSubtitleMenu && !showSourceMenu && uiState.error == null) {
            delay(100) // Small delay to ensure UI is composed
            try {
                playButtonFocusRequester.requestFocus()
            } catch (e: Exception) {
                // Focus request may fail if component not ready
            }
        }
    }

    // Auto-hide skip overlay and reset - use lastSkipTime as key to restart on each skip
    LaunchedEffect(lastSkipTime) {
        if (showSkipOverlay && lastSkipTime > 0) {
            delay(1500)
            showSkipOverlay = false
            skipAmount = 0
            skipStartPosition = 0L
        }
    }

    // Auto-hide volume indicator
    LaunchedEffect(showVolumeIndicator) {
        if (showVolumeIndicator) {
            delay(2000)
            showVolumeIndicator = false
        }
    }

    // Volume helpers
    fun adjustVolume(direction: Int) {
        val newVolume = (currentVolume + direction).coerceIn(0, maxVolume)
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
        currentVolume = newVolume
        isMuted = newVolume == 0
        showVolumeIndicator = true
    }

    fun toggleMute() {
        if (isMuted) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeBeforeMute, 0)
            currentVolume = volumeBeforeMute
            isMuted = false
        } else {
            volumeBeforeMute = currentVolume
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
            currentVolume = 0
            isMuted = true
        }
        showVolumeIndicator = true
    }

    // Guard against accessing a released ExoPlayer from long-running coroutines (can crash on some devices).
    var playerReleased by remember { mutableStateOf(false) }

    // Update progress periodically
    LaunchedEffect(exoPlayer) {
        while (!playerReleased) {
            currentPosition = exoPlayer.currentPosition
            viewModel.onPlaybackPosition(currentPosition)
            val rawDuration = exoPlayer.duration
            duration = if (rawDuration > 0L && rawDuration != C.TIME_UNSET) rawDuration else 0L
            progress = if (duration > 0L) {
                (currentPosition.toFloat() / duration.toFloat()).coerceIn(0f, 1f)
            } else {
                0f
            }
            isPlaying = exoPlayer.isPlaying
            isBuffering = exoPlayer.playbackState == Player.STATE_BUFFERING

            // Buffering watchdog - detect long buffering but do not force a source error popup.
            if (isBuffering && hasPlaybackStarted) {
                if (bufferingStartTime == null) {
                    bufferingStartTime = System.currentTimeMillis()
                } else {
                    val bufferingDuration = System.currentTimeMillis() - (bufferingStartTime ?: 0L)
                    if (bufferingDuration > bufferingTimeoutMs) {
                        bufferingStartTime = null
                        if (!rebufferRecoverAttempted) {
                            rebufferRecoverAttempted = true
                            val resumeAt = exoPlayer.currentPosition.coerceAtLeast(0L)
                            val wasPlaying = exoPlayer.playWhenReady
                            exoPlayer.stop()
                            exoPlayer.prepare()
                            if (resumeAt > 0L) exoPlayer.seekTo(resumeAt)
                            exoPlayer.playWhenReady = wasPlaying
                        }
                    }
                }
            } else {
                bufferingStartTime = null
            }

            // Initial startup watchdog: while first frame has not really started, enforce bounded startup.
            val startupPending = uiState.selectedStreamUrl != null && !hasPlaybackStarted
            val startupStalled =
                (
                    exoPlayer.playbackState == Player.STATE_BUFFERING ||
                        (exoPlayer.playbackState == Player.STATE_READY && !exoPlayer.isPlaying) ||
                        exoPlayer.playbackState == Player.STATE_IDLE
                )
            if (startupPending) {
                val selectedAt = streamSelectedTime ?: System.currentTimeMillis()
                val startupBufferDuration = System.currentTimeMillis() - selectedAt
                val isHeavyStartupSource = isLikelyHeavyStream(uiState.selectedStream)
                if (startupStalled && startupBufferDuration > initialBufferingTimeoutMs) {
                    if (!startupRecoverAttempted) {
                        startupRecoverAttempted = true
                        if (allowAutomaticSourceFallback &&
                            !userSelectedSourceManually &&
                            tryAdvanceToNextStream()
                        ) {
                            // auto advanced to a fallback stream
                        } else if (!isHeavyStartupSource) {
                            exoPlayer.stop()
                            exoPlayer.prepare()
                            exoPlayer.playWhenReady = true
                        }
                    }
                }
                val hardTimeoutMs = (initialBufferingTimeoutMs + if (isHeavyStartupSource) 45_000L else 20_000L)
                    .coerceAtMost(180_000L)
                if (!startupHardFailureReported && startupBufferDuration > hardTimeoutMs) {
                    if (!startupSameSourceRefreshAttempted) {
                        startupSameSourceRefreshAttempted = true
                        uiState.selectedStream?.let { viewModel.selectStream(it) }
                    } else {
                        startupHardFailureReported = true
                        playbackIssueReported = true
                        viewModel.reportPlaybackError(
                            "Source did not start in time. Try another source."
                        )
                    }
                }
            }

            // Mark playback as started only after media time moves beyond 00:00.
            if (!hasPlaybackStarted &&
                exoPlayer.playbackState == Player.STATE_READY &&
                exoPlayer.isPlaying &&
                exoPlayer.currentPosition > 800L
            ) {
                hasPlaybackStarted = true
            }

            if (currentPosition > 0 && duration > 0) {
                val currentSecond = (currentPosition / 1000L).coerceAtLeast(0L)
                val shouldReport =
                    (!exoPlayer.isPlaying && currentSecond != lastProgressReportSecond) ||
                        (exoPlayer.isPlaying && (lastProgressReportSecond < 0L || currentSecond - lastProgressReportSecond >= 3L))
                if (shouldReport) {
                    lastProgressReportSecond = currentSecond
                    val progressPercent = (currentPosition.toFloat() / duration.toFloat() * 100).toInt()
                    viewModel.saveProgress(
                        currentPosition,
                        duration,
                        progressPercent,
                        isPlaying = exoPlayer.isPlaying,
                        playbackState = exoPlayer.playbackState
                    )
                }

            }

            // Auto-play next episode when current one ends
            if (exoPlayer.playbackState == Player.STATE_ENDED && mediaType == MediaType.TV) {
                if (seasonNumber != null && episodeNumber != null) {
                    val selected = uiState.selectedStream
                    onPlayNext(
                        seasonNumber,
                        episodeNumber + 1,
                        selected?.addonId?.takeIf { it.isNotBlank() },
                        selected?.source?.takeIf { it.isNotBlank() }
                    )
                }
            }

            delay(1000)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            controlsSeekJob?.cancel()
            playerReleased = true
            val safeDuration = exoPlayer.duration.takeIf { it > 0L && it != C.TIME_UNSET } ?: 0L
            val safeProgressPercent = if (safeDuration > 0L) {
                ((exoPlayer.currentPosition.toDouble() / safeDuration.toDouble()) * 100.0)
                    .toInt()
                    .coerceIn(0, 100)
            } else {
                0
            }
            viewModel.saveProgress(
                exoPlayer.currentPosition,
                safeDuration,
                safeProgressPercent,
                isPlaying = exoPlayer.isPlaying,
                playbackState = exoPlayer.playbackState
            )
            exoPlayer.release()
        }
    }

    // Request focus on the container when not showing controls
    LaunchedEffect(showControls, showSubtitleMenu, showSourceMenu, uiState.error) {
        if (!showControls && !showSubtitleMenu && !showSourceMenu && uiState.error == null) {
            delay(100)
            try {
                containerFocusRequester.requestFocus()
            } catch (_: Exception) {}
        }
    }

    BackHandler(enabled = showSubtitleMenu) {
        showSubtitleMenu = false
        showControls = true
        coroutineScope.launch {
            delay(120)
            runCatching { subtitleButtonFocusRequester.requestFocus() }
        }
    }

    BackHandler(enabled = showSourceMenu) {
        showSourceMenu = false
        showControls = true
        coroutineScope.launch {
            delay(120)
            runCatching { sourceButtonFocusRequester.requestFocus() }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .focusRequester(containerFocusRequester)
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    // Handle error modal
                    if (uiState.error != null) {
                        return@onKeyEvent when (event.key) {
                            Key.DirectionLeft -> {
                                if (errorModalFocusIndex > 0) errorModalFocusIndex--
                                true
                            }
                            Key.DirectionRight -> {
                                if (errorModalFocusIndex < 1) errorModalFocusIndex++
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                if (errorModalFocusIndex == 0) viewModel.retry() else onBack()
                                true
                            }
                            Key.Back, Key.Escape -> {
                                onBack()
                                true
                            }
                            else -> false
                        }
                    }

                    // Handle subtitle/audio menu
                    if (showSubtitleMenu) {
                        val maxIndex = if (subtitleMenuTab == 0) {
                            uiState.subtitles.size + 1 // +1 for "Off"
                        } else {
                            audioTracks.size.coerceAtLeast(1)
                        }

                        return@onKeyEvent when (event.key) {
                        Key.MediaPlayPause, Key.MediaPlay, Key.MediaPause -> {
                            if (event.key == Key.MediaPause) {
                                exoPlayer.pause()
                            } else if (event.key == Key.MediaPlay) {
                                exoPlayer.play()
                            } else {
                                if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                            }
                            showControls = true
                            true
                        }
                        Key.Back, Key.Escape -> {
                                showSubtitleMenu = false
                                showControls = true
                                // Restore focus to subtitle button
                                coroutineScope.launch {
                                    delay(150)
                                    try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                                }
                                true
                            }
                            Key.DirectionUp -> {
                                if (subtitleMenuIndex > 0) subtitleMenuIndex--
                                true
                            }
                            Key.DirectionDown -> {
                                if (subtitleMenuIndex < maxIndex - 1) subtitleMenuIndex++
                                true
                            }
                            Key.DirectionLeft -> {
                                // Switch to Subtitles tab
                                if (subtitleMenuTab != 0) {
                                    subtitleMenuTab = 0
                                    subtitleMenuIndex = 0
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                // Switch to Audio tab
                                if (subtitleMenuTab != 1) {
                                    subtitleMenuTab = 1
                                    subtitleMenuIndex = 0
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                if (subtitleMenuTab == 0) {
                                    // Subtitle selection
                                    if (subtitleMenuIndex == 0) {
                                        viewModel.disableSubtitles()
                                    } else {
                                        uiState.subtitles.getOrNull(subtitleMenuIndex - 1)?.let { viewModel.selectSubtitle(it) }
                                    }
                                } else {
                                    // Audio selection
                                    audioTracks.getOrNull(subtitleMenuIndex)?.let { track ->
                                        // Switch audio track via ExoPlayer
                                        val params = exoPlayer.trackSelectionParameters.buildUpon()
                                        params.setPreferredAudioLanguage(track.language)
                                        val trackGroups = exoPlayer.currentTracks.groups
                                        if (track.groupIndex < trackGroups.size &&
                                            trackGroups[track.groupIndex].type == C.TRACK_TYPE_AUDIO
                                        ) {
                                            params.setOverrideForType(
                                                androidx.media3.common.TrackSelectionOverride(
                                                    trackGroups[track.groupIndex].mediaTrackGroup,
                                                    track.trackIndex
                                                )
                                            )
                                        }
                                        exoPlayer.trackSelectionParameters = params.build()
                                        selectedAudioIndex = audioTracks.indexOfFirst {
                                            it.groupIndex == track.groupIndex && it.trackIndex == track.trackIndex
                                        }.takeIf { it >= 0 } ?: track.index
                                    }
                                }
                                showSubtitleMenu = false
                                showControls = true
                                // Restore focus to subtitle button
                                coroutineScope.launch {
                                    delay(150)
                                    try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                                }
                                true
                            }
                            else -> false
                        }
                    }

                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            onBack()
                            true
                        }
                        Key.DirectionLeft -> {
                            if (!showControls) {
                                // Accumulate skip amount - track from start position
                                val now = System.currentTimeMillis()
                                if (now - lastSkipTime < 1200 && showSkipOverlay) {
                                    // Continue accumulating from current skip session
                                    skipAmount = (skipAmount - 10).coerceIn(-10000, 10000)
                                } else {
                                    // Start new skip session
                                    skipStartPosition = exoPlayer.currentPosition
                                    skipAmount = -10
                                }
                                lastSkipTime = now
                                val unclamped = (skipStartPosition + (skipAmount * 1000L)).coerceAtLeast(0L)
                                val targetPosition = if (duration > 0L) unclamped.coerceAtMost(duration) else unclamped
                                exoPlayer.seekTo(targetPosition)
                                showSkipOverlay = true
                                true
                            } else {
                                false
                            }
                        }
                        Key.DirectionRight -> {
                            if (!showControls) {
                                // Accumulate skip amount - track from start position
                                val now = System.currentTimeMillis()
                                if (now - lastSkipTime < 1200 && showSkipOverlay) {
                                    // Continue accumulating from current skip session
                                    skipAmount = (skipAmount + 10).coerceIn(-10000, 10000)
                                } else {
                                    // Start new skip session
                                    skipStartPosition = exoPlayer.currentPosition
                                    skipAmount = 10
                                }
                                lastSkipTime = now
                                val unclamped = (skipStartPosition + (skipAmount * 1000L)).coerceAtLeast(0L)
                                val targetPosition = if (duration > 0L) unclamped.coerceAtMost(duration) else unclamped
                                exoPlayer.seekTo(targetPosition)
                                showSkipOverlay = true
                                true
                            } else {
                                false
                            }
                        }
                        Key.VolumeUp -> {
                            adjustVolume(1)
                            true
                        }
                        Key.VolumeDown -> {
                            adjustVolume(-1)
                            true
                        }
                        Key.DirectionUp, Key.DirectionDown -> {
                            val skipVisible = uiState.activeSkipInterval != null && !uiState.skipIntervalDismissed
                            // When hidden, prefer focusing the skip button (if present) instead of showing controls.
                            if (!showControls) {
                                if (skipVisible && event.key == Key.DirectionUp) {
                                    coroutineScope.launch {
                                        delay(40)
                                        runCatching { skipIntroFocusRequester.requestFocus() }
                                    }
                                } else {
                                    showControls = true
                                }
                                true
                            } else {
                                // Let focused buttons handle navigation
                                false
                            }
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            if (!showControls) {
                                // Show controls and toggle play/pause
                                if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                                showControls = true
                                true
                            } else {
                                // Let the focused button handle Enter key
                                false
                            }
                        }
                        Key.Spacebar -> {
                            if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                            showControls = true
                            true
                        }
                        // Any other key shows controls
                        else -> {
                            if (!showControls) {
                                showControls = true
                                true
                            } else {
                                false
                            }
                        }
                    }
                } else false
            }
    ) {
        // Keep PlayerView mounted as soon as we have a stream URL.
        // A real video surface must exist during startup, otherwise some streams never transition out of buffering.
        if (uiState.selectedStreamUrl != null) {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = false
                        setKeepContentOnPlayerReset(true)

                        // Enable subtitle view with Netflix-style: bold white text with black outline
                        subtitleView?.apply {
                            // Use CaptionStyleCompat from ui package
                            setStyle(
                                androidx.media3.ui.CaptionStyleCompat(
                                    android.graphics.Color.WHITE,                    // Foreground color
                                    android.graphics.Color.TRANSPARENT,              // Background color (transparent = no box)
                                    android.graphics.Color.TRANSPARENT,              // Window color
                                    androidx.media3.ui.CaptionStyleCompat.EDGE_TYPE_OUTLINE,  // Text outline
                                    android.graphics.Color.BLACK,                    // Edge color (black outline)
                                    android.graphics.Typeface.DEFAULT_BOLD           // Bold typeface
                                )
                            )
                            // Normalize embedded subtitle styling to keep size consistent
                            setApplyEmbeddedStyles(false)
                            setApplyEmbeddedFontSizes(false)
                            // Set subtitle text size - not too big, not too small (like Netflix)
                            setFixedTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 24f)
                            // Position subtitles at bottom with some margin
                            setBottomPaddingFraction(0.08f)
                        }
                    }
                },
                update = { playerView ->
                    playerView.player = exoPlayer
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // Loading screen overlay - keep visible until player is fully started.
        if (uiState.isLoading || uiState.selectedStreamUrl == null || !hasPlaybackStarted) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.backdropUrl != null) {
                    AsyncImage(
                        model = uiState.backdropUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.7f))
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    WaveLoadingDots(
                        dotCount = 4,
                        dotSize = 14.dp,
                        dotSpacing = 14.dp,
                        color = PurplePrimary,
                        secondaryColor = PurpleLight
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = when {
                            uiState.isLoadingSubtitles -> "Fetching subtitles..."
                            uiState.isLoadingStreams -> "Loading streams..."
                            uiState.selectedStreamUrl != null && !hasPlaybackStarted -> "Starting playback..."
                            else -> "Loading..."
                        },
                        style = ArflixTypography.body,
                        color = TextSecondary
                    )
                }
            }
        }

        // Buffering indicator - only show after playback has started (mid-stream buffering)
        // Initial buffering is handled by the main loading screen above
        if (isBuffering && hasPlaybackStarted && uiState.selectedStreamUrl != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Use smaller wave dots for mid-stream buffering
                WaveLoadingDots(
                    dotCount = 4,
                    dotSize = 12.dp,
                    dotSpacing = 12.dp,
                    color = PurplePrimary,
                    secondaryColor = PurpleLight
                )
            }
        }

        // Skip intro/recap overlay (independent of controls)
        val activeSkip = uiState.activeSkipInterval
        SkipIntroButton(
            interval = activeSkip,
            dismissed = uiState.skipIntervalDismissed,
            controlsVisible = showControls,
            onSkip = {
                val end = activeSkip?.endMs ?: return@SkipIntroButton
                exoPlayer.seekTo((end + 500L).coerceAtLeast(0L))
                viewModel.dismissSkipInterval()
            },
            focusRequester = skipIntroFocusRequester,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(5f) // Ensure it's above the controls overlay scrim.
                .padding(start = 32.dp, bottom = if (showControls) 120.dp else 32.dp)
        )

        // Netflix-style Controls Overlay
        AnimatedVisibility(
            visible = showControls && !showSubtitleMenu && !showSourceMenu,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Top info
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Left side - clearlogo/title and episode info
                    Column(modifier = Modifier.weight(1f)) {
                        if (!uiState.logoUrl.isNullOrBlank()) {
                            AsyncImage(
                                model = uiState.logoUrl,
                                contentDescription = uiState.title,
                                alignment = Alignment.CenterStart,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .height(32.dp)
                                    .width(240.dp)
                            )
                        } else {
                            Text(
                                text = uiState.title,
                                style = ArflixTypography.sectionTitle.copy(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = TextPrimary
                            )
                        }
                        if (seasonNumber != null && episodeNumber != null) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ,
                                modifier = Modifier.padding(top = 6.dp)
                            ) {
                                Text(
                                    text = "S$seasonNumber E$episodeNumber",
                                    style = ArflixTypography.body.copy(fontSize = 16.sp),
                                    color = TextSecondary
                                )
                                // Episode title would be shown here if available
                            }
                        }
                        // Source info
                        uiState.selectedStream?.let { stream ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(top = 4.dp)
                            ) {
                                Text(
                                    text = stream.quality,
                                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                    color = Pink
                                )
                                stream.sizeBytes?.let { size ->
                                    Text(
                                        text = "•",
                                        style = ArflixTypography.caption,
                                        color = TextSecondary.copy(alpha = 0.5f)
                                    )
                                    Text(
                                        text = formatFileSize(size),
                                        style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                        color = TextSecondary
                                    )
                                }
                            }
                        }
                    }

                    // Right side - clock
                    val currentTime = remember { mutableStateOf("") }
                    LaunchedEffect(Unit) {
                        while (true) {
                            currentTime.value = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                                .format(java.util.Date())
                            kotlinx.coroutines.delay(30000)
                        }
                    }
                    Text(
                        text = currentTime.value,
                        style = ArflixTypography.body.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = TextSecondary
                    )
                }

                // Bottom controls - positioned at very bottom
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 48.dp, vertical = 24.dp)
                ) {
                    // Progress bar row with play button - FIRST
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Focusable play/pause button - icon only with glow on focus
                        var playButtonFocused by remember { mutableStateOf(false) }
                        val playButtonInteraction = remember { MutableInteractionSource() }
                        val playButtonScale by animateFloatAsState(
                            if (playButtonFocused) 1.3f else 1f,
                            label = "playScale"
                        )
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .focusRequester(playButtonFocusRequester)
                                .onFocusChanged { state ->
                                    playButtonFocused = state.isFocused
                                    if (state.isFocused) focusedButton = 0
                                }
                                .focusable()
                                .graphicsLayer {
                                    scaleX = playButtonScale
                                    scaleY = playButtonScale
                                }
                                .clickable(
                                    interactionSource = playButtonInteraction,
                                    indication = null
                                ) {
                                    if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                                }
                                .onKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown) {
                                        when (event.key) {
                                            Key.Enter, Key.DirectionCenter -> {
                                                if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                                                true
                                            }
                                            Key.DirectionUp -> {
                                                val skipVisible = uiState.activeSkipInterval != null && !uiState.skipIntervalDismissed
                                                if (skipVisible) {
                                                    skipIntroFocusRequester.requestFocus()
                                                    true
                                                } else {
                                                    false
                                                }
                                            }
                                            Key.DirectionRight -> {
                                                // Move focus to trackbar
                                                trackbarFocusRequester.requestFocus()
                                                true
                                            }
                                            Key.DirectionLeft -> {
                                                // Seek backward when at leftmost button
                                                queueControlsSeek(-10_000L)
                                                true
                                            }
                                            Key.DirectionDown -> {
                                                // Move to subtitle button
                                                subtitleButtonFocusRequester.requestFocus()
                                                true
                                            }
                                            else -> false
                                        }
                                    } else false
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Pause" else "Play",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Current time
                        Text(
                            text = formatTime(if (isControlScrubbing) scrubPreviewPosition else currentPosition),
                            style = ArflixTypography.label.copy(fontSize = 13.sp),
                            color = TextPrimary,
                            modifier = Modifier.width(55.dp)
                        )

                        // Focusable Progress bar with scrubber
                        var trackbarFocused by remember { mutableStateOf(false) }
                        val trackbarHeight by animateFloatAsState(
                            if (trackbarFocused) 10f else 5f,
                            label = "trackbarHeight"
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(trackbarHeight.dp)
                                .focusRequester(trackbarFocusRequester)
                                .onFocusChanged { state ->
                                    trackbarFocused = state.isFocused
                                    if (!state.isFocused && isControlScrubbing) {
                                        commitControlsSeekNow()
                                    }
                                }
                                .focusable()
                                .onKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown && trackbarFocused) {
                                        when (event.key) {
                                            Key.DirectionLeft -> {
                                                // Preview scrub backward and debounce real seek
                                                queueControlsSeek(-10_000L)
                                                true
                                            }
                                            Key.DirectionRight -> {
                                                // Preview scrub forward and debounce real seek
                                                queueControlsSeek(10_000L)
                                                true
                                            }
                                            Key.Enter, Key.DirectionCenter -> {
                                                // Commit pending scrub immediately
                                                commitControlsSeekNow()
                                                true
                                            }
                                            Key.DirectionDown -> {
                                                // Move to subtitle button
                                                subtitleButtonFocusRequester.requestFocus()
                                                true
                                            }
                                            Key.DirectionUp -> {
                                                // No controls above trackbar
                                                true
                                            }
                                            else -> false
                                        }
                                    } else false
                                }
                                .background(
                                    if (trackbarFocused) Color.White.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.3f),
                                    RoundedCornerShape(5.dp)
                                ),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            // Progress fill
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(
                                        if (duration > 0) {
                                            ((if (isControlScrubbing) scrubPreviewPosition else currentPosition).toFloat() / duration.toFloat()).coerceIn(0f, 1f)
                                        } else {
                                            progress
                                        }
                                    )
                                    .fillMaxHeight()
                                    .background(Pink, RoundedCornerShape(5.dp))
                            )
                            // Scrubber circle - only visible when focused
                            if (trackbarFocused) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(
                                            if (duration > 0) {
                                                ((if (isControlScrubbing) scrubPreviewPosition else currentPosition).toFloat() / duration.toFloat()).coerceIn(0f, 1f)
                                            } else {
                                                progress
                                            }
                                        )
                                        .wrapContentWidth(Alignment.End)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(18.dp)
                                            .offset(x = 9.dp) // Center on progress edge
                                            .background(Color.White, CircleShape)
                                            .border(2.dp, Pink, CircleShape)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Duration
                        Text(
                            text = formatTime(duration),
                            style = ArflixTypography.label.copy(fontSize = 13.sp),
                            color = TextSecondary,
                            modifier = Modifier.width(55.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Text buttons row - closer to the track bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Subtitle/Audio button with proper TV focus
                        var subtitleButtonFocused by remember { mutableStateOf(false) }
                        PlayerTextButtonFocusable(
                            text = "Subtitles & Audio",
                            isFocused = subtitleButtonFocused,
                            focusRequester = subtitleButtonFocusRequester,
                            onFocusChanged = { focused ->
                                subtitleButtonFocused = focused
                                if (focused) focusedButton = 1
                            },
                            onClick = {
                                showSubtitleMenu = true
                                subtitleMenuIndex = 0
                            },
                            onLeftKey = {
                                playButtonFocusRequester.requestFocus()
                            },
                            onRightKey = {
                                sourceButtonFocusRequester.requestFocus()
                            },
                            onUpKey = {
                                trackbarFocusRequester.requestFocus()
                            }
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        // Sources button
                        var sourceButtonFocused by remember { mutableStateOf(false) }
                        PlayerTextButtonFocusable(
                            text = "Sources",
                            isFocused = sourceButtonFocused,
                            focusRequester = sourceButtonFocusRequester,
                            onFocusChanged = { sourceButtonFocused = it },
                            onClick = {
                                showSourceMenu = true
                                showControls = true
                            },
                            onLeftKey = {
                                subtitleButtonFocusRequester.requestFocus()
                            },
                            onRightKey = {
                                if (mediaType == MediaType.TV) {
                                    nextEpisodeButtonFocusRequester.requestFocus()
                                } else {
                                    queueControlsSeek(10_000L)
                                }
                            },
                            onUpKey = {
                                trackbarFocusRequester.requestFocus()
                            }
                        )

                        if (mediaType == MediaType.TV) {
                            Spacer(modifier = Modifier.width(12.dp))

                            // Next episode button
                            var nextEpisodeButtonFocused by remember { mutableStateOf(false) }
                            PlayerTextButtonFocusable(
                                text = "Next Episode",
                                isFocused = nextEpisodeButtonFocused,
                                focusRequester = nextEpisodeButtonFocusRequester,
                                onFocusChanged = { nextEpisodeButtonFocused = it },
                                onClick = {
                                    val season = seasonNumber ?: return@PlayerTextButtonFocusable
                                    val episode = episodeNumber ?: return@PlayerTextButtonFocusable
                                    val selected = uiState.selectedStream
                                    onPlayNext(
                                        season,
                                        episode + 1,
                                        selected?.addonId?.takeIf { it.isNotBlank() },
                                        selected?.source?.takeIf { it.isNotBlank() }
                                    )
                                },
                                onLeftKey = {
                                    sourceButtonFocusRequester.requestFocus()
                                },
                                onRightKey = {
                                    queueControlsSeek(10_000L)
                                },
                                onUpKey = {
                                    trackbarFocusRequester.requestFocus()
                                }
                            )
                        }
                    }
                }
            }
        }

        // Subtitle/Audio menu
        AnimatedVisibility(
            visible = showSubtitleMenu,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            SubtitleMenu(
                subtitles = uiState.subtitles,
                selectedSubtitle = uiState.selectedSubtitle,
                audioTracks = audioTracks,
                selectedAudioIndex = selectedAudioIndex,
                activeTab = subtitleMenuTab,
                focusedIndex = subtitleMenuIndex,
                onTabChanged = { tab ->
                    subtitleMenuTab = tab
                    subtitleMenuIndex = 0
                },
                onSelectSubtitle = { index ->
                    if (index == 0) {
                        viewModel.disableSubtitles()
                    } else {
                        uiState.subtitles.getOrNull(index - 1)?.let { viewModel.selectSubtitle(it) }
                    }
                    showSubtitleMenu = false
                    showControls = true
                    // Restore focus to subtitle button after closing menu
                    coroutineScope.launch {
                        delay(150)
                        try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                    }
                },
                onSelectAudio = { track ->
                    // Switch audio track via ExoPlayer
                    val params = exoPlayer.trackSelectionParameters.buildUpon()
                    params.setPreferredAudioLanguage(track.language)
                    val trackGroups = exoPlayer.currentTracks.groups
                    if (track.groupIndex < trackGroups.size &&
                        trackGroups[track.groupIndex].type == C.TRACK_TYPE_AUDIO
                    ) {
                        params.setOverrideForType(
                            androidx.media3.common.TrackSelectionOverride(
                                trackGroups[track.groupIndex].mediaTrackGroup,
                                track.trackIndex
                            )
                        )
                    }
                    exoPlayer.trackSelectionParameters = params.build()
                    selectedAudioIndex = audioTracks.indexOfFirst {
                        it.groupIndex == track.groupIndex && it.trackIndex == track.trackIndex
                    }.takeIf { it >= 0 } ?: track.index
                    showSubtitleMenu = false
                    showControls = true
                    // Restore focus to subtitle button after closing menu
                    coroutineScope.launch {
                        delay(150)
                        try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                    }
                },
                onClose = {
                    showSubtitleMenu = false
                    showControls = true
                    // Restore focus to subtitle button after closing menu
                    coroutineScope.launch {
                        delay(150)
                        try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                    }
                }
            )
        }

        StreamSelector(
            isVisible = showSourceMenu,
            streams = uiState.streams,
            selectedStream = uiState.selectedStream,
            isLoading = uiState.isLoadingStreams,
            title = uiState.title,
            subtitle = if (seasonNumber != null && episodeNumber != null) {
                "S$seasonNumber E$episodeNumber"
            } else {
                ""
            },
            onSelect = { stream: StreamSource ->
                userSelectedSourceManually = true
                playbackIssueReported = false
                startupRecoverAttempted = false
                startupHardFailureReported = false
                startupSameSourceRetryCount = 0
                startupSameSourceRefreshAttempted = false
                startupUrlLock = null
                rebufferRecoverAttempted = false
                viewModel.selectStream(stream)
                showSourceMenu = false
                showControls = true
                coroutineScope.launch {
                    delay(150)
                    runCatching { sourceButtonFocusRequester.requestFocus() }
                }
            },
            onClose = {
                showSourceMenu = false
                showControls = true
                coroutineScope.launch {
                    delay(150)
                    runCatching { sourceButtonFocusRequester.requestFocus() }
                }
            }
        )

        // Volume indicator
        AnimatedVisibility(
            visible = showVolumeIndicator,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 48.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = when {
                        isMuted || currentVolume == 0 -> Icons.Default.VolumeMute
                        currentVolume < maxVolume / 2 -> Icons.Default.VolumeDown
                        else -> Icons.Default.VolumeUp
                    },
                    contentDescription = "Volume",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(100.dp)
                        .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize((currentVolume.toFloat() / maxVolume).coerceIn(0f, 1f))
                            .background(Pink, RoundedCornerShape(4.dp))
                            .align(Alignment.BottomCenter)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isMuted) "Muted" else "${currentVolume * 100 / maxVolume}%",
                    style = ArflixTypography.caption,
                    color = Color.White
                )
            }
        }

        // Skip overlay - shows +10/-10 when seeking without controls
        // Positioned near bottom (above trackbar area), no background, just text with shadow
        AnimatedVisibility(
            visible = showSkipOverlay,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 120.dp)
        ) {
            Text(
                text = if (skipAmount >= 0) "+${skipAmount}s" else "${skipAmount}s",
                style = ArflixTypography.sectionTitle.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 8f
                    )
                ),
                color = Color.White
            )
        }

        // Error modal
        AnimatedVisibility(
            visible = uiState.error != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .width(450.dp)
                        .background(Color(0xFF1A1A1A), RoundedCornerShape(16.dp))
                        .border(1.dp, Color(0xFFEF4444).copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .background(Color(0xFFEF4444).copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ErrorOutline,
                            contentDescription = "Error",
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Playback Error",
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = uiState.error ?: "An unknown error occurred",
                        style = ArflixTypography.body,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        ErrorButton(
                            text = "TRY AGAIN",
                            icon = Icons.Default.Refresh,
                            isFocused = errorModalFocusIndex == 0,
                            isPrimary = true,
                            onClick = { viewModel.retry() }
                        )
                        ErrorButton(
                            text = "GO BACK",
                            isFocused = errorModalFocusIndex == 1,
                            isPrimary = false,
                            onClick = onBack
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PlayerTextButtonFocusable(
    text: String,
    isFocused: Boolean,
    focusRequester: FocusRequester,
    onFocusChanged: (Boolean) -> Unit,
    onClick: () -> Unit,
    onLeftKey: () -> Unit = {},
    onRightKey: () -> Unit = {},
    onUpKey: () -> Unit = {}
) {
    val scale by animateFloatAsState(if (isFocused) 1.08f else 1f, label = "scale")

    Box(
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged { state ->
                onFocusChanged(state.isFocused)
            }
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Enter, Key.DirectionCenter -> {
                            onClick()
                            true
                        }
                        Key.DirectionLeft -> {
                            onLeftKey()
                            true
                        }
                        Key.DirectionRight -> {
                            onRightKey()
                            true
                        }
                        Key.DirectionUp -> {
                            onUpKey()
                            true
                        }
                        else -> false
                    }
                } else false
            }
            .clickable { onClick() }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                if (isFocused) Color.White else Color.White.copy(alpha = 0.1f),
                RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 20.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = ArflixTypography.body.copy(
                fontSize = 14.sp,
                fontWeight = if (isFocused) FontWeight.SemiBold else FontWeight.Normal
            ),
            color = if (isFocused) Color.Black else Color.White
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ErrorButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    isFocused: Boolean,
    isPrimary: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(if (isFocused) 1.05f else 1f, label = "scale")

    Box(
        modifier = Modifier
            .focusable()
            .clickable { onClick() }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                when {
                    isFocused -> Color.White
                    isPrimary -> Color.White.copy(alpha = 0.1f)
                    else -> Color.Transparent
                },
                RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = when {
                    isFocused -> Color.White
                    isPrimary -> Pink.copy(alpha = 0.5f)
                    else -> Color.White.copy(alpha = 0.3f)
                },
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = if (isFocused) Color.Black else if (isPrimary) Pink else TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = text,
                style = ArflixTypography.button,
                color = if (isFocused) Color.Black else if (isPrimary) Pink else TextSecondary
            )
        }
    }
}

/**
 * Audio track info from ExoPlayer
 */
data class AudioTrackInfo(
    val index: Int,
    val groupIndex: Int,
    val trackIndex: Int,
    val language: String?,
    val label: String?,
    val channelCount: Int,
    val sampleRate: Int,
    val codec: String?
)

/**
 * Language code to full name mapping
 */
private fun getFullLanguageName(code: String?): String {
    if (code == null) return "Unknown"
    val normalizedCode = code.lowercase().trim()
    return when {
        normalizedCode == "en" || normalizedCode == "eng" || normalizedCode == "english" -> "English"
        normalizedCode == "es" || normalizedCode == "spa" || normalizedCode == "spanish" -> "Spanish"
        normalizedCode == "nl" || normalizedCode == "nld" || normalizedCode == "dut" || normalizedCode == "dutch" -> "Dutch"
        normalizedCode == "de" || normalizedCode == "ger" || normalizedCode == "deu" || normalizedCode == "german" -> "German"
        normalizedCode == "fr" || normalizedCode == "fra" || normalizedCode == "fre" || normalizedCode == "french" -> "French"
        normalizedCode == "it" || normalizedCode == "ita" || normalizedCode == "italian" -> "Italian"
        normalizedCode == "pt" || normalizedCode == "por" || normalizedCode == "portuguese" -> "Portuguese"
        normalizedCode == "pob" -> "Portuguese (Brazil)"
        normalizedCode == "ru" || normalizedCode == "rus" || normalizedCode == "russian" -> "Russian"
        normalizedCode == "ja" || normalizedCode == "jpn" || normalizedCode == "japanese" -> "Japanese"
        normalizedCode == "ko" || normalizedCode == "kor" || normalizedCode == "korean" -> "Korean"
        normalizedCode == "zh" || normalizedCode == "chi" || normalizedCode == "zho" || normalizedCode == "chinese" -> "Chinese"
        normalizedCode == "ar" || normalizedCode == "ara" || normalizedCode == "arabic" -> "Arabic"
        normalizedCode == "hi" || normalizedCode == "hin" || normalizedCode == "hindi" -> "Hindi"
        normalizedCode == "tr" || normalizedCode == "tur" || normalizedCode == "turkish" -> "Turkish"
        normalizedCode == "pl" || normalizedCode == "pol" || normalizedCode == "polish" -> "Polish"
        normalizedCode == "sv" || normalizedCode == "swe" || normalizedCode == "swedish" -> "Swedish"
        normalizedCode == "no" || normalizedCode == "nor" || normalizedCode == "norwegian" -> "Norwegian"
        normalizedCode == "da" || normalizedCode == "dan" || normalizedCode == "danish" -> "Danish"
        normalizedCode == "fi" || normalizedCode == "fin" || normalizedCode == "finnish" -> "Finnish"
        normalizedCode == "cs" || normalizedCode == "cze" || normalizedCode == "ces" || normalizedCode == "czech" -> "Czech"
        normalizedCode == "hu" || normalizedCode == "hun" || normalizedCode == "hungarian" -> "Hungarian"
        normalizedCode == "ro" || normalizedCode == "ron" || normalizedCode == "rum" || normalizedCode == "romanian" -> "Romanian"
        normalizedCode == "el" || normalizedCode == "gre" || normalizedCode == "ell" || normalizedCode == "greek" -> "Greek"
        normalizedCode == "he" || normalizedCode == "heb" || normalizedCode == "hebrew" -> "Hebrew"
        normalizedCode == "th" || normalizedCode == "tha" || normalizedCode == "thai" -> "Thai"
        normalizedCode == "vi" || normalizedCode == "vie" || normalizedCode == "vietnamese" -> "Vietnamese"
        normalizedCode == "id" || normalizedCode == "ind" || normalizedCode == "indonesian" -> "Indonesian"
        normalizedCode == "ms" || normalizedCode == "msa" || normalizedCode == "may" || normalizedCode == "malay" -> "Malay"
        normalizedCode == "uk" || normalizedCode == "ukr" || normalizedCode == "ukrainian" -> "Ukrainian"
        normalizedCode == "bg" || normalizedCode == "bul" || normalizedCode == "bulgarian" -> "Bulgarian"
        normalizedCode == "hr" || normalizedCode == "hrv" || normalizedCode == "croatian" -> "Croatian"
        normalizedCode == "sr" || normalizedCode == "srp" || normalizedCode == "serbian" -> "Serbian"
        normalizedCode == "sk" || normalizedCode == "slo" || normalizedCode == "slk" || normalizedCode == "slovak" -> "Slovak"
        normalizedCode == "sl" || normalizedCode == "slv" || normalizedCode == "slovenian" -> "Slovenian"
        normalizedCode == "et" || normalizedCode == "est" || normalizedCode == "estonian" -> "Estonian"
        normalizedCode == "lv" || normalizedCode == "lav" || normalizedCode == "latvian" -> "Latvian"
        normalizedCode == "lt" || normalizedCode == "lit" || normalizedCode == "lithuanian" -> "Lithuanian"
        normalizedCode == "fa" || normalizedCode == "per" || normalizedCode == "fas" || normalizedCode == "persian" -> "Persian"
        normalizedCode == "kur" || normalizedCode == "ku" || normalizedCode == "kurdish" -> "Kurdish"
        normalizedCode == "mon" || normalizedCode == "mn" || normalizedCode == "mongolian" -> "Mongolian"
        normalizedCode == "und" || normalizedCode == "unknown" -> "Unknown"
        else -> code.uppercase()
    }
}

private fun handleSubtitleMenuKey(
    key: Key,
    currentIndex: Int,
    maxIndex: Int,
    setIndex: (Int) -> Unit,
    onClose: () -> Unit,
    onSelect: () -> Unit
): Boolean {
    return when (key) {
        Key.Back, Key.Escape -> {
            onClose()
            true
        }
        Key.DirectionUp -> {
            if (currentIndex > 0) setIndex(currentIndex - 1)
            true
        }
        Key.DirectionDown -> {
            if (currentIndex < maxIndex - 1) setIndex(currentIndex + 1)
            true
        }
        Key.Enter, Key.DirectionCenter -> {
            onSelect()
            true
        }
        else -> false
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SubtitleMenu(
    subtitles: List<Subtitle>,
    selectedSubtitle: Subtitle?,
    audioTracks: List<AudioTrackInfo>,
    selectedAudioIndex: Int,
    activeTab: Int,
    focusedIndex: Int,
    onTabChanged: (Int) -> Unit,
    onSelectSubtitle: (Int) -> Unit,
    onSelectAudio: (AudioTrackInfo) -> Unit,
    onClose: () -> Unit
) {
    val subtitleListState = rememberLazyListState()
    val audioListState = rememberLazyListState()

    // Scroll to focused item
    LaunchedEffect(focusedIndex, activeTab) {
        if (activeTab == 0) {
            if (focusedIndex >= 0) {
                subtitleListState.animateScrollToItem(focusedIndex)
            }
        } else {
            if (focusedIndex >= 0) {
                audioListState.animateScrollToItem(focusedIndex)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .clickable { onClose() },
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            modifier = Modifier
                .width(320.dp)
                .padding(end = 32.dp)
                .background(
                    Color.Black.copy(alpha = 0.85f),
                    RoundedCornerShape(16.dp)
                )
                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                .padding(16.dp)
                .clickable(enabled = false) {} // Prevent clicks from closing
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TabButton(
                    text = "Subtitles",
                    isSelected = activeTab == 0,
                    onClick = { onTabChanged(0) }
                )
                TabButton(
                    text = "Audio",
                    isSelected = activeTab == 1,
                    onClick = { onTabChanged(1) }
                )
            }

            // Content based on active tab
            Box(modifier = Modifier.height(300.dp)) {
                if (activeTab == 0) {
                    // Subtitles tab
                    LazyColumn(
                        state = subtitleListState,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        item {
                            TrackMenuItem(
                                label = "Off",
                                subtitle = null,
                                isSelected = selectedSubtitle == null,
                                isFocused = focusedIndex == 0,
                                onClick = { onSelectSubtitle(0) }
                            )
                        }

                        itemsIndexed(subtitles) { index, subtitle ->
                            // Use actual track label as main text, full language name as secondary
                            val trackLabel = subtitle.label.ifBlank { subtitle.lang }
                            val languageInfo = getFullLanguageName(subtitle.lang)
                            // Only show language info if different from label
                            val subtitleInfo = if (trackLabel.lowercase() != languageInfo.lowercase() &&
                                                   !trackLabel.lowercase().contains(languageInfo.lowercase())) {
                                languageInfo
                            } else null
                            TrackMenuItem(
                                label = trackLabel,
                                subtitle = subtitleInfo,
                                isSelected = selectedSubtitle?.id == subtitle.id,
                                isFocused = focusedIndex == index + 1,
                                onClick = { onSelectSubtitle(index + 1) }
                            )
                        }
                    }
                } else {
                    // Audio tab
                    LazyColumn(
                        state = audioListState,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (audioTracks.isEmpty()) {
                            item {
                                Text(
                                    text = "No audio tracks available",
                                    style = ArflixTypography.body,
                                    color = TextSecondary,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        } else {
                            itemsIndexed(audioTracks) { index, track ->
                                // Use track label if available, otherwise full language name
                                val languageName = getFullLanguageName(track.language)
                                val trackLabel = track.label?.takeIf { it.isNotBlank() } ?: languageName

                                val codecInfo = detectAudioCodecLabel(track.codec, trackLabel)
                                val channelInfo = when (track.channelCount) {
                                    1 -> "Mono"
                                    2 -> "Stereo"
                                    6 -> "5.1"
                                    8 -> "7.1"
                                    else -> if (track.channelCount > 0) "${track.channelCount}ch" else null
                                }
                                val subtitleText = listOfNotNull(codecInfo, channelInfo).joinToString(" • ")

                                TrackMenuItem(
                                    label = trackLabel,
                                    subtitle = subtitleText.ifEmpty { null },
                                    isSelected = index == selectedAudioIndex,
                                    isFocused = focusedIndex == index,
                                    onClick = { onSelectAudio(track) }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigation hint
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "← → Switch tabs • ↑↓ Navigate • BACK Close",
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Selected tab shows subtle highlight, not full white (to avoid confusion with list focus)
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(
                if (isSelected) Color.White.copy(alpha = 0.2f) else Color.Transparent,
                RoundedCornerShape(20.dp)
            )
            .then(
                if (isSelected) Modifier.border(1.dp, Color.White, RoundedCornerShape(20.dp))
                else Modifier
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.body.copy(
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                fontSize = 14.sp
            ),
            color = Color.White
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TrackMenuItem(
    label: String,
    subtitle: String?,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    // Only use isFocused from parent (programmatic focus via focusedIndex)
    // Don't track actual D-pad focus to avoid double-focus issues
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                if (isFocused) Color.White else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = ArflixTypography.body.copy(fontSize = 14.sp),
                color = if (isFocused) Color.Black else Color.White
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = ArflixTypography.caption.copy(fontSize = 11.sp),
                    color = if (isFocused) Color.Black.copy(alpha = 0.7f) else Color.White.copy(alpha = 0.6f)
                )
            }
        }

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = if (isFocused) Color.Black else Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// Legacy function for backwards compatibility
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SubtitleMenuItem(
    label: String,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    TrackMenuItem(
        label = getFullLanguageName(label),
        subtitle = null,
        isSelected = isSelected,
        isFocused = isFocused,
        onClick = onClick
    )
}

private fun formatTime(ms: Long): String {
    if (ms <= 0) return "00:00"
    val hours = TimeUnit.MILLISECONDS.toHours(ms)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(ms) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(ms) % 60

    return if (hours > 0) {
        String.format("%d:%02d:%02d", hours, minutes, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }
}

private fun formatFileSize(bytes: Long): String {
    return when {
        bytes >= 1_073_741_824 -> String.format("%.1f GB", bytes / 1_073_741_824.0)
        bytes >= 1_048_576 -> String.format("%.0f MB", bytes / 1_048_576.0)
        bytes >= 1024 -> String.format("%.0f KB", bytes / 1024.0)
        else -> "$bytes B"
    }
}

private fun detectAudioCodecLabel(codec: String?, trackLabel: String?): String? {
    val haystack = buildString {
        codec?.let {
            append(it)
            append(' ')
        }
        trackLabel?.let { append(it) }
    }.lowercase()

    return when {
        haystack.isBlank() -> null
        haystack.contains("dts:x") || haystack.contains("dtsx") || haystack.contains("dts x") -> "DTS:X"
        haystack.contains("dts-hd") || haystack.contains("dts hd") ||
            haystack.contains("dtshd") || haystack.contains("dca-ma") || haystack.contains("dca-hd") -> "DTS-HD"
        haystack.contains("truehd") && haystack.contains("atmos") -> "TrueHD Atmos"
        haystack.contains("truehd") -> "TrueHD"
        haystack.contains("eac3") || haystack.contains("e-ac3") || haystack.contains("dd+") -> "E-AC3"
        haystack.contains("ac3") || haystack.contains("dd ") || haystack.endsWith("dd") -> "AC3"
        haystack.contains("dts") -> "DTS"
        haystack.contains("aac") -> "AAC"
        haystack.contains("mp3") -> "MP3"
        haystack.contains("opus") -> "Opus"
        haystack.contains("flac") -> "FLAC"
        else -> null
    }
}

private fun estimateInitialStartupTimeoutMs(
    stream: StreamSource?,
    isManualSelection: Boolean
): Long {
    var timeoutMs = if (isManualSelection) 40_000L else 18_000L
    if (stream == null) return timeoutMs

    val haystack = buildString {
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

    val sizeBytes = parseSizeToBytes(stream.size)

    if (haystack.contains("4k") || haystack.contains("2160")) {
        timeoutMs = timeoutMs.coerceAtLeast(70_000L)
    }
    if (haystack.contains("remux") || haystack.contains("dolby vision") || haystack.contains(" dovi")) {
        timeoutMs = timeoutMs.coerceAtLeast(80_000L)
    }

    timeoutMs = when {
        sizeBytes >= 40L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(110_000L)
        sizeBytes >= 30L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(95_000L)
        sizeBytes >= 20L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(80_000L)
        sizeBytes >= 10L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(60_000L)
        else -> timeoutMs
    }

    return timeoutMs.coerceAtMost(120_000L)
}

private fun parseSizeToBytes(sizeStr: String): Long {
    if (sizeStr.isBlank()) return 0L

    val normalized = sizeStr.uppercase()
        .replace(",", ".")
        .replace(Regex("\\s+"), " ")
        .trim()

    val pattern = Regex("""(\d+(?:\.\d+)?)\s*(TB|GB|MB|KB)""")
    val match = pattern.find(normalized) ?: return 0L
    val number = match.groupValues[1].toDoubleOrNull() ?: return 0L

    val multiplier = when (match.groupValues[2]) {
        "TB" -> 1024.0 * 1024.0 * 1024.0 * 1024.0
        "GB" -> 1024.0 * 1024.0 * 1024.0
        "MB" -> 1024.0 * 1024.0
        "KB" -> 1024.0
        else -> 1.0
    }
    return (number * multiplier).toLong()
}

private fun isLikelyHeavyStream(stream: StreamSource?): Boolean {
    if (stream == null) return false
    val text = buildString {
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
    val sizeBytes = parseSizeToBytes(stream.size)
    return sizeBytes >= 20L * 1024 * 1024 * 1024 ||
        text.contains("4k") ||
        text.contains("2160") ||
        text.contains("remux") ||
        text.contains("dolby vision") ||
        text.contains(" dovi")
}

private fun isFrameRateMatchingSupported(context: Context): Boolean {
    if (Build.VERSION.SDK_INT < 30) return false
    val modesCount = runCatching { context.display?.supportedModes?.size ?: 0 }.getOrDefault(0)
    return modesCount > 1
}

private fun resolveFrameRateStrategyForMode(mode: String): Int {
    return when (mode.trim().lowercase()) {
        "always" -> readMedia3FrameRateConst(
            fieldName = "VIDEO_CHANGE_FRAME_RATE_STRATEGY_ALWAYS",
            fallback = resolveFrameRateSeamlessStrategy()
        )
        "seamless", "seamless only", "only if seamless", "only_if_seamless" -> resolveFrameRateSeamlessStrategy()
        else -> resolveFrameRateOffStrategy()
    }
}

private fun resolveFrameRateOffStrategy(): Int {
    return readMedia3FrameRateConst("VIDEO_CHANGE_FRAME_RATE_STRATEGY_OFF", fallback = 0)
}

private fun resolveFrameRateSeamlessStrategy(): Int {
    return readMedia3FrameRateConst("VIDEO_CHANGE_FRAME_RATE_STRATEGY_ONLY_IF_SEAMLESS", fallback = 1)
}

private fun readMedia3FrameRateConst(fieldName: String, fallback: Int): Int {
    return runCatching { C::class.java.getField(fieldName).getInt(null) }.getOrDefault(fallback)
}
