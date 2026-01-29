package com.arflix.tv.ui.screens.player

import android.content.Context
import android.media.AudioManager
import android.net.Uri
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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
import androidx.hilt.navigation.compose.hiltViewModel
import android.util.Log
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
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.components.LoadingIndicator
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
    streamUrl: String? = null,
    viewModel: PlayerViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
    onPlayNext: (Int, Int) -> Unit = { _, _ -> }
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
    val nextEpisodeButtonFocusRequester = remember { FocusRequester() }
    val containerFocusRequester = remember { FocusRequester() }

    // Focus state - Netflix-style: 0=Play, 1=Subtitles, 2=Next Episode
    var focusedButton by remember { mutableIntStateOf(0) }
    var showSubtitleMenu by remember { mutableStateOf(false) }
    var subtitleMenuIndex by remember { mutableIntStateOf(0) }
    var subtitleMenuTab by remember { mutableIntStateOf(0) } // 0 = Subtitles, 1 = Audio

    // Audio tracks from ExoPlayer
    var audioTracks by remember { mutableStateOf<List<AudioTrackInfo>>(emptyList()) }
    var selectedAudioIndex by remember { mutableIntStateOf(0) }

    // Error modal focus
    var errorModalFocusIndex by remember { mutableIntStateOf(0) }

    // Auto-play next episode
    var showNextEpisodePrompt by remember { mutableStateOf(false) }
    var autoPlayCountdown by remember { mutableIntStateOf(10) }

    // Load media
    LaunchedEffect(mediaType, mediaId, seasonNumber, episodeNumber) {
        viewModel.loadMedia(mediaType, mediaId, seasonNumber, episodeNumber, streamUrl)
    }

    // Track current stream index for auto-advancement on error
    var currentStreamIndex by remember { mutableIntStateOf(0) }

    // ExoPlayer - configured for maximum codec compatibility and smooth streaming
    val exoPlayer = remember {
        // Create HTTP data source with custom headers for debrid CDNs
        val httpDataSourceFactory = DefaultHttpDataSource.Factory()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
            .setAllowCrossProtocolRedirects(true)
            .setConnectTimeoutMs(30000)
            .setReadTimeoutMs(60000) // Increased for large files
            .setDefaultRequestProperties(mapOf(
                "Accept" to "*/*",
                "Accept-Encoding" to "identity", // Disable compression for video streams
                "Connection" to "keep-alive"
            ))

        val mediaSourceFactory = DefaultMediaSourceFactory(context)
            .setDataSourceFactory(httpDataSourceFactory)

        // FAST START buffering - minimal buffer before starting, build up while playing
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                15_000,    // Min buffer: 15 seconds (reduced from 30)
                300_000,   // Max buffer: 5 minutes (enough for 4K remux)
                1_500,     // Buffer for playback: 1.5 seconds - FAST START
                5_000      // Buffer after rebuffer: 5 seconds (reduced from 10)
            )
            .setTargetBufferBytes(100 * 1024 * 1024) // 100MB target buffer (reduced)
            .setPrioritizeTimeOverSizeThresholds(true) // Prioritize time for faster start
            .setBackBuffer(15_000, true) // Keep 15 seconds of back buffer
            .build()

        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setRenderersFactory(
                DefaultRenderersFactory(context)
                    // FORCE FFmpeg decoders to be used FIRST - handles all codecs including 4K HDR/DV/HEVC
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
                        // Don't restrict by device capabilities
                        .setExceedVideoConstraintsIfNecessary(true)
                        .setExceedAudioConstraintsIfNecessary(true)
                        .setExceedRendererCapabilitiesIfNecessary(true)
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
                    override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                        Log.e("PlayerScreen", "ExoPlayer error: ${error.errorCode} - ${error.message}", error)
                        Log.e("PlayerScreen", "Error cause: ${error.cause?.javaClass?.simpleName} - ${error.cause?.message}")

                        val streams = uiState.streams
                        // If codec/decoder error or source error, try next stream
                        // Error codes: https://developer.android.com/reference/androidx/media3/common/PlaybackException
                        val shouldTryNextStream = error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODER_INIT_FAILED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FAILED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_UNSPECIFIED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS ||
                            error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT

                        if (shouldTryNextStream) {
                            val nextIndex = currentStreamIndex + 1
                            Log.d("PlayerScreen", "Trying next stream index: $nextIndex / ${streams.size}")
                            if (nextIndex < streams.size) {
                                currentStreamIndex = nextIndex
                                val nextStream = streams[nextIndex]
                                Log.d("PlayerScreen", "Switching to stream: ${nextStream.source} - ${nextStream.quality}")
                                // Reset player state before trying next stream
                                stop()
                                clearMediaItems()
                                // Select next stream via ViewModel (triggers state change -> LaunchedEffect)
                                viewModel.selectStream(nextStream)
                            } else {
                                Log.e("PlayerScreen", "No more streams to try!")
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
                                    Log.d("PlayerScreen", "Audio track $trackIndex: ${format.sampleMimeType}, ${format.channelCount}ch, ${format.sampleRate}Hz, lang=${format.language}")
                                    trackIndex++
                                }
                            }
                        }
                        audioTracks = extractedAudioTracks
                        Log.d("PlayerScreen", "Audio tracks available: ${extractedAudioTracks.size}")

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

    LaunchedEffect(uiState.preferredAudioLanguage) {
        val trackSelector = exoPlayer.trackSelector as? androidx.media3.exoplayer.trackselection.DefaultTrackSelector
        if (trackSelector != null) {
            val params = trackSelector.buildUponParameters()
                .setPreferredAudioLanguage(uiState.preferredAudioLanguage)
                .build()
            trackSelector.parameters = params
        }
    }

    // Update player when stream URL changes - also add subtitle if selected
    LaunchedEffect(uiState.selectedStreamUrl) {
        val url = uiState.selectedStreamUrl
        if (url != null) {
            Log.d("PlayerScreen", "Setting stream URL: $url")

            // Try to determine MIME type from URL or default to MKV (most common for torrents)
            val mimeType = when {
                url.contains(".mkv", ignoreCase = true) -> MimeTypes.APPLICATION_MATROSKA
                url.contains(".mp4", ignoreCase = true) -> MimeTypes.VIDEO_MP4
                url.contains(".webm", ignoreCase = true) -> MimeTypes.VIDEO_WEBM
                url.contains(".avi", ignoreCase = true) -> MimeTypes.VIDEO_H264 // fallback
                // For debrid CDN URLs without extension, assume MKV (most remuxes are MKV)
                url.contains("tb-cdn.st") || url.contains("real-debrid") || url.contains("debrid") -> {
                    Log.d("PlayerScreen", "Debrid CDN detected, using MKV mime type")
                    MimeTypes.APPLICATION_MATROSKA
                }
                else -> null // Let ExoPlayer figure it out
            }

            Log.d("PlayerScreen", "Detected MIME type: $mimeType")

            val mediaItemBuilder = MediaItem.Builder()
                .setUri(Uri.parse(url))

            if (mimeType != null) {
                mediaItemBuilder.setMimeType(mimeType)
            }

            // Add subtitle if already selected (for auto-select default subtitle)
            val subtitle = uiState.selectedSubtitle
            if (subtitle != null) {
                Log.d("PlayerScreen", "Adding subtitle to initial load: ${subtitle.label}")
                val subMimeType = when {
                    subtitle.url.endsWith(".vtt", ignoreCase = true) -> MimeTypes.TEXT_VTT
                    subtitle.url.endsWith(".srt", ignoreCase = true) -> MimeTypes.APPLICATION_SUBRIP
                    subtitle.url.endsWith(".ass", ignoreCase = true) -> MimeTypes.TEXT_SSA
                    subtitle.url.endsWith(".ssa", ignoreCase = true) -> MimeTypes.TEXT_SSA
                    subtitle.url.contains(".vtt", ignoreCase = true) -> MimeTypes.TEXT_VTT
                    else -> MimeTypes.APPLICATION_SUBRIP
                }
                val subtitleConfig = MediaItem.SubtitleConfiguration.Builder(Uri.parse(subtitle.url))
                    .setMimeType(subMimeType)
                    .setLanguage(subtitle.lang)
                    .setLabel(subtitle.label)
                    .setSelectionFlags(C.SELECTION_FLAG_DEFAULT or C.SELECTION_FLAG_FORCED)
                    .build()
                mediaItemBuilder.setSubtitleConfigurations(listOf(subtitleConfig))
            }

            val mediaItem = mediaItemBuilder.build()
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true

            // Enable text track display
            if (subtitle != null) {
                exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                    .buildUpon()
                    .setPreferredTextLanguage(subtitle.lang)
                    .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                    .build()
            }

            if (uiState.savedPosition > 0) {
                exoPlayer.seekTo(uiState.savedPosition)
            }
        }
    }

    // Track previous subtitle to detect changes (not initial load)
    var previousSubtitle by remember { mutableStateOf<Subtitle?>(null) }

    // Apply subtitle when user selects a different one (after initial load)
    LaunchedEffect(uiState.selectedSubtitle) {
        val subtitle = uiState.selectedSubtitle
        val streamUrl = uiState.selectedStreamUrl

        // Skip if this is the initial subtitle (handled in stream URL LaunchedEffect)
        // or if stream URL hasn't been set yet
        if (streamUrl == null) return@LaunchedEffect

        // Check if this is a real change (not initial load)
        val isInitialLoad = previousSubtitle == null && subtitle != null
        previousSubtitle = subtitle

        if (subtitle != null) {
            Log.d("PlayerScreen", "Applying subtitle change: ${subtitle.label} (embedded=${subtitle.isEmbedded})")

            if (subtitle.isEmbedded) {
                // Handle embedded subtitle selection
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
                        Log.d("PlayerScreen", "Selected embedded subtitle track: $groupIndex:$trackIndex")
                    }
                }
            } else {
                // Handle external subtitle (rebuild media item)
                // Determine subtitle MIME type from URL
                val mimeType = when {
                    subtitle.url.endsWith(".vtt", ignoreCase = true) -> MimeTypes.TEXT_VTT
                    subtitle.url.endsWith(".srt", ignoreCase = true) -> MimeTypes.APPLICATION_SUBRIP
                    subtitle.url.endsWith(".ass", ignoreCase = true) -> MimeTypes.TEXT_SSA
                    subtitle.url.endsWith(".ssa", ignoreCase = true) -> MimeTypes.TEXT_SSA
                    subtitle.url.contains(".vtt", ignoreCase = true) -> MimeTypes.TEXT_VTT
                    else -> MimeTypes.APPLICATION_SUBRIP  // Default to SRT
                }

                Log.d("PlayerScreen", "Subtitle MIME type: $mimeType")

                // Build subtitle configuration with forced selection
                val subtitleConfig = MediaItem.SubtitleConfiguration.Builder(Uri.parse(subtitle.url))
                    .setMimeType(mimeType)
                    .setLanguage(subtitle.lang)
                    .setLabel(subtitle.label)
                    .setSelectionFlags(C.SELECTION_FLAG_DEFAULT or C.SELECTION_FLAG_FORCED)
                    .build()

                // Get current position and playing state
                val currentPosition = exoPlayer.currentPosition
                val wasPlaying = exoPlayer.isPlaying

                // Determine video MIME type
                val videoMimeType = when {
                    streamUrl.contains(".mkv", ignoreCase = true) -> MimeTypes.APPLICATION_MATROSKA
                    streamUrl.contains(".mp4", ignoreCase = true) -> MimeTypes.VIDEO_MP4
                    streamUrl.contains("tb-cdn.st") || streamUrl.contains("debrid") -> MimeTypes.APPLICATION_MATROSKA
                    else -> null
                }

                // Rebuild media item with subtitle
                val mediaItemBuilder = MediaItem.Builder()
                    .setUri(Uri.parse(streamUrl))
                    .setSubtitleConfigurations(listOf(subtitleConfig))

                if (videoMimeType != null) {
                    mediaItemBuilder.setMimeType(videoMimeType)
                }

                val mediaItem = mediaItemBuilder.build()

                // Set new media item and restore position
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()

                // Wait for player to be ready before seeking
                delay(200)
                exoPlayer.seekTo(currentPosition)
                exoPlayer.playWhenReady = wasPlaying

                // Enable subtitle track
                exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                    .buildUpon()
                    .setPreferredTextLanguage(subtitle.lang)
                    .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                    .build()
            }
        } else {
            // Disable subtitles
            Log.d("PlayerScreen", "Disabling subtitles")
            exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                .buildUpon()
                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true)
                .build()
        }
    }

    // Auto-hide controls and return focus to container
    LaunchedEffect(showControls, isPlaying) {
        if (showControls && isPlaying && !showSubtitleMenu) {
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
        if (showControls && !showSubtitleMenu && uiState.error == null) {
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

    // Update progress periodically
    LaunchedEffect(exoPlayer) {
        while (true) {
            currentPosition = exoPlayer.currentPosition
            duration = exoPlayer.duration.coerceAtLeast(1)
            progress = (currentPosition.toFloat() / duration.toFloat()).coerceIn(0f, 1f)
            isPlaying = exoPlayer.isPlaying
            isBuffering = exoPlayer.playbackState == Player.STATE_BUFFERING

            // Mark playback as started once we've actually started playing (not just buffering)
            if (!hasPlaybackStarted && (isPlaying || exoPlayer.playbackState == Player.STATE_READY)) {
                hasPlaybackStarted = true
            }

            if (currentPosition > 0 && duration > 0) {
                val progressPercent = (currentPosition.toFloat() / duration.toFloat() * 100).toInt()
                viewModel.saveProgress(
                    currentPosition,
                    duration,
                    progressPercent,
                    isPlaying = exoPlayer.isPlaying,
                    playbackState = exoPlayer.playbackState
                )

                // Next episode prompt only for TV shows
                if (mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null) {
                    val remainingTime = duration - currentPosition
                    if (remainingTime in 1..30000 && !showNextEpisodePrompt) {
                        showNextEpisodePrompt = true
                        autoPlayCountdown = 10
                    }
                }
            }

            if (exoPlayer.playbackState == Player.STATE_ENDED && mediaType == MediaType.TV) {
                if (seasonNumber != null && episodeNumber != null) {
                    onPlayNext(seasonNumber, episodeNumber + 1)
                }
            }

            delay(500)
        }
    }

    // Auto-play countdown
    LaunchedEffect(showNextEpisodePrompt, autoPlayCountdown) {
        if (showNextEpisodePrompt && autoPlayCountdown > 0) {
            delay(1000)
            autoPlayCountdown--
            if (autoPlayCountdown == 0 && seasonNumber != null && episodeNumber != null) {
                onPlayNext(seasonNumber, episodeNumber + 1)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.saveProgress(
                exoPlayer.currentPosition,
                exoPlayer.duration,
                (exoPlayer.currentPosition.toFloat() / exoPlayer.duration.toFloat() * 100).toInt(),
                isPlaying = exoPlayer.isPlaying,
                playbackState = exoPlayer.playbackState
            )
            exoPlayer.release()
        }
    }

    // Request focus on the container when not showing controls
    LaunchedEffect(showControls, showSubtitleMenu, uiState.error) {
        if (!showControls && !showSubtitleMenu && uiState.error == null) {
            delay(100)
            try {
                containerFocusRequester.requestFocus()
            } catch (_: Exception) {}
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
                                        Log.d("PlayerScreen", "Switched to audio track: ${track.language}, ${track.codec}")
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
                                val targetPosition = (skipStartPosition + (skipAmount * 1000L)).coerceIn(0, duration)
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
                                val targetPosition = (skipStartPosition + (skipAmount * 1000L)).coerceIn(0, duration)
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
                            // Always show controls on up/down when hidden
                            if (!showControls) {
                                showControls = true
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
        // Loading screen - show when loading, resolving stream, no stream URL, or playback hasn't started yet
        // This keeps the nice loading screen visible until player is fully ready to play
        if (uiState.isLoading || uiState.isResolvingStream || uiState.selectedStreamUrl == null || !hasPlaybackStarted) {
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
                    if (uiState.logoUrl != null) {
                        AsyncImage(
                            model = uiState.logoUrl,
                            contentDescription = uiState.title,
                            modifier = Modifier
                                .width(300.dp)
                                .padding(bottom = 24.dp)
                        )
                    } else {
                        Text(
                            text = uiState.title,
                            style = ArflixTypography.sectionTitle,
                            color = TextPrimary,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                    }

                    // 4 dots loading animation - purple theme
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
                            uiState.isResolvingStream -> "Resolving stream..."
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
        } else {
            // Video player with Netflix-style subtitles
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = false

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

        // Netflix-style Controls Overlay
        AnimatedVisibility(
            visible = showControls && !showSubtitleMenu,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Gradient overlay at bottom
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.9f)
                                )
                            )
                        )
                )

                // Top info
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Left side - title and episode info
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = uiState.title,
                            style = ArflixTypography.sectionTitle.copy(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = TextPrimary
                        )
                        if (seasonNumber != null && episodeNumber != null) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                                .clickable {
                                    if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                                }
                                .onKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown) {
                                        when (event.key) {
                                            Key.Enter, Key.DirectionCenter -> {
                                                if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                                                true
                                            }
                                            Key.DirectionRight -> {
                                                // Move focus to trackbar
                                                trackbarFocusRequester.requestFocus()
                                                true
                                            }
                                            Key.DirectionLeft -> {
                                                // Seek backward when at leftmost button
                                                exoPlayer.seekTo((currentPosition - 10000).coerceAtLeast(0))
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
                            text = formatTime(currentPosition),
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
                                }
                                .focusable()
                                .onKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown && trackbarFocused) {
                                        when (event.key) {
                                            Key.DirectionLeft -> {
                                                // Seek backward 10 seconds
                                                exoPlayer.seekTo((currentPosition - 10000).coerceAtLeast(0))
                                                true
                                            }
                                            Key.DirectionRight -> {
                                                // Seek forward 10 seconds
                                                exoPlayer.seekTo((currentPosition + 10000).coerceAtMost(duration))
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
                                    .fillMaxWidth(progress)
                                    .fillMaxHeight()
                                    .background(Pink, RoundedCornerShape(5.dp))
                            )
                            // Scrubber circle - only visible when focused
                            if (trackbarFocused) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(progress)
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
                                if (mediaType == MediaType.TV && seasonNumber != null) {
                                    nextEpisodeButtonFocusRequester.requestFocus()
                                }
                            },
                            onUpKey = {
                                trackbarFocusRequester.requestFocus()
                            }
                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        // Next Episode button (only for TV shows)
                        if (mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null) {
                            var nextEpisodeButtonFocused by remember { mutableStateOf(false) }
                            PlayerTextButtonFocusable(
                                text = "Next Episode",
                                isFocused = nextEpisodeButtonFocused,
                                focusRequester = nextEpisodeButtonFocusRequester,
                                onFocusChanged = { focused ->
                                    nextEpisodeButtonFocused = focused
                                    if (focused) focusedButton = 2
                                },
                                onClick = {
                                    onPlayNext(seasonNumber, episodeNumber + 1)
                                },
                                onLeftKey = {
                                    subtitleButtonFocusRequester.requestFocus()
                                },
                                onRightKey = {
                                    // Seek forward when at rightmost button
                                    exoPlayer.seekTo((currentPosition + 10000).coerceAtMost(duration))
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
                    Log.d("PlayerScreen", "Switched to audio track: ${track.language}, ${track.codec}")
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

        // Next Episode prompt
        AnimatedVisibility(
            visible = showNextEpisodePrompt && mediaType == MediaType.TV,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.85f), RoundedCornerShape(8.dp))
                    .border(1.dp, Pink.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = "Up Next",
                        style = ArflixTypography.caption,
                        color = TextSecondary
                    )
                    Text(
                        text = "Episode ${(episodeNumber ?: 0) + 1}",
                        style = ArflixTypography.sectionTitle.copy(fontSize = 22.sp),
                        color = TextPrimary
                    )
                    Text(
                        text = "Starting in ${autoPlayCountdown}s",
                        style = ArflixTypography.caption,
                        color = Pink
                    )
                }
            }
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

                                val codecInfo = track.codec?.let { codec ->
                                    when {
                                        codec.contains("ac3", ignoreCase = true) -> "AC3"
                                        codec.contains("eac3", ignoreCase = true) || codec.contains("e-ac3", ignoreCase = true) -> "E-AC3"
                                        codec.contains("dts", ignoreCase = true) -> "DTS"
                                        codec.contains("truehd", ignoreCase = true) -> "TrueHD"
                                        codec.contains("aac", ignoreCase = true) -> "AAC"
                                        codec.contains("mp3", ignoreCase = true) -> "MP3"
                                        codec.contains("opus", ignoreCase = true) -> "Opus"
                                        codec.contains("flac", ignoreCase = true) -> "FLAC"
                                        else -> null
                                    }
                                }
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
