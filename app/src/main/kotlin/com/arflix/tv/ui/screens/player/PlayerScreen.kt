@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.player

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import com.arflix.tv.BuildConfig
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween as animTween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FitScreen
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material.icons.filled.ClosedCaption
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.compose.ui.layout.onSizeChanged
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
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.components.WaveLoadingDots
import androidx.compose.ui.text.style.TextOverflow
import com.arflix.tv.util.LocalDeviceType
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
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.dash.DashMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource

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
    preferredBingeGroup: String? = null,
    startPositionMs: Long? = null,
    viewModel: PlayerViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
    onPlayNext: (Int, Int, String?, String?, String?) -> Unit = { _, _, _, _, _ -> }
) {
    val context = LocalContext.current
    val activity = remember(context) { context.findActivity() }
    val uiState by viewModel.uiState.collectAsState()
    val latestUiState by rememberUpdatedState(uiState)
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val deviceType = LocalDeviceType.current

    // Keep playback in landscape while the player is visible, then restore the app's prior orientation.
    DisposableEffect(activity) {
        val previousOrientation = activity?.requestedOrientation
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        onDispose {
            if (previousOrientation != null) {
                activity.requestedOrientation = previousOrientation
            }
        }
    }

    // On mobile, enable immersive fullscreen for the player and restore system bars on exit.
    // TV is always in fullscreen so no change is needed there.
    DisposableEffect(Unit) {
        val window = activity?.window
        if (window != null && deviceType != com.arflix.tv.util.DeviceType.TV) {
            val controller = androidx.core.view.WindowInsetsControllerCompat(window, window.decorView)
            controller.systemBarsBehavior =
                androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller.hide(androidx.core.view.WindowInsetsCompat.Type.systemBars())
        }
        onDispose {
            if (window != null && deviceType != com.arflix.tv.util.DeviceType.TV) {
                val controller = androidx.core.view.WindowInsetsControllerCompat(window, window.decorView)
                controller.show(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            }
        }
    }

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
    val audioManager = remember { context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager ?: android.media.AudioManager::class.java.getDeclaredConstructor().newInstance() }
    var currentVolume by remember { mutableIntStateOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)) }
    val maxVolume = remember { audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) }
    var showVolumeIndicator by remember { mutableStateOf(false) }
    var showAspectIndicator by remember { mutableStateOf(false) }
    var aspectIndicatorTrigger by remember { mutableIntStateOf(0) }
    var isMuted by remember { mutableStateOf(false) }
    var volumeBeforeMute by remember { mutableIntStateOf(currentVolume) }

    // Focus requesters for TV navigation
    val playButtonFocusRequester = remember { FocusRequester() }
    val trackbarFocusRequester = remember { FocusRequester() }
    val subtitleButtonFocusRequester = remember { FocusRequester() }
    val sourceButtonFocusRequester = remember { FocusRequester() }
    val rewindButtonFocusRequester = remember { FocusRequester() }
    val forwardButtonFocusRequester = remember { FocusRequester() }
    val aspectButtonFocusRequester = remember { FocusRequester() }
    val nextEpisodeButtonFocusRequester = remember { FocusRequester() }
    val containerFocusRequester = remember { FocusRequester() }
    val skipIntroFocusRequester = remember { FocusRequester() }

    // Focus state - 0=Play, 1=Subtitles
    var focusedButton by remember { mutableIntStateOf(0) }
    var showSubtitleMenu by remember { mutableStateOf(false) }
    var showSourceMenu by remember { mutableStateOf(false) }
    var playerResizeMode by remember { mutableIntStateOf(AspectRatioFrameLayout.RESIZE_MODE_FIT) }
    var subtitleMenuIndex by remember { mutableIntStateOf(0) }
    var subtitleMenuTab by remember { mutableIntStateOf(0) } // 0 = Subtitles, 1 = Audio

    // Audio tracks from ExoPlayer
    var audioTracks by remember { mutableStateOf<List<AudioTrackInfo>>(emptyList()) }
    var selectedAudioIndex by remember { mutableIntStateOf(0) }

    // Error modal focus
    var errorModalFocusIndex by remember { mutableIntStateOf(0) }

    // Buffering watchdog - detect stuck buffering
    var bufferingStartTime by remember { mutableStateOf<Long?>(null) }
    val bufferingTimeoutMs = 25_000L // Mid-playback timeout for stuck buffering
    var userSelectedSourceManually by remember { mutableStateOf(false) }
    val allowStartupSourceFallback = true
    val allowMidPlaybackSourceFallback = false
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
    var dvStartupFallbackStage by remember { mutableIntStateOf(0) } // 0=none, 1=HEVC forced, 2=AVC forced
    var midPlaybackRecoveryAttempts by remember { mutableIntStateOf(0) }
    var blackVideoRecoveryStage by remember { mutableIntStateOf(0) } // 0=none, 1=HEVC forced, 2=AVC forced
    var blackVideoReadySinceMs by remember { mutableStateOf<Long?>(null) }
    val heavyStartupMaxRetries = 6
    var rebufferRecoverAttempted by remember { mutableStateOf(false) }
    var longRebufferCount by remember { mutableIntStateOf(0) }
    var autoAdvanceAttempts by remember { mutableIntStateOf(0) }
    var triedStreamIndexes by remember { mutableStateOf<Set<Int>>(emptySet()) }
    var isAutoAdvancing by remember { mutableStateOf(false) }
    var lastProgressReportSecond by remember { mutableLongStateOf(-1L) }
    // Guard against accessing a released ExoPlayer from long-running coroutines (can crash on some devices).
    // AtomicBoolean gives cross-thread visibility; Compose state drives recomposition.
    val playerReleasedAtomic = remember { java.util.concurrent.atomic.AtomicBoolean(false) }
    var playerReleased by remember { mutableStateOf(false) }

    // Load media
    LaunchedEffect(mediaType, mediaId, seasonNumber, episodeNumber, imdbId, preferredAddonId, preferredSourceName, preferredBingeGroup, startPositionMs) {
        playbackIssueReported = false
        startupRecoverAttempted = false
        startupHardFailureReported = false
        startupSameSourceRetryCount = 0
        startupSameSourceRefreshAttempted = false
        startupUrlLock = null
        dvStartupFallbackStage = 0
        rebufferRecoverAttempted = false
        longRebufferCount = 0
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
            preferredBingeGroup = preferredBingeGroup,
            startPositionMs = startPositionMs
        )
    }

    // Track current stream index for auto-advancement on error
    var currentStreamIndex by remember { mutableIntStateOf(0) }
    val tryAdvanceToNextStream: () -> Boolean = {
        val streams = uiState.streams
        if (streams.size <= 1) {
            viewModel.onFailoverAttempt(success = false)
            false
        } else {
            val nextIndex = (1 until streams.size)
                .map { offset -> (currentStreamIndex + offset) % streams.size }
                .firstOrNull { idx ->
                    streams[idx].url?.isNotBlank() == true &&
                        idx !in triedStreamIndexes
                } ?: -1

            if (nextIndex < 0) {
                viewModel.onFailoverAttempt(success = false)
                false
            } else {
                viewModel.onFailoverAttempt(success = true)
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
                dvStartupFallbackStage = 0
                rebufferRecoverAttempted = false
                longRebufferCount = 0
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
    val playbackCookieJar = remember { PlaybackCookieJar() }
    val playbackHttpClient = remember(playbackCookieJar) {
        OkHttpClient.Builder()
            .cookieJar(playbackCookieJar)
            .connectionPool(ConnectionPool(4, 5, TimeUnit.MINUTES))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .dns(OkHttpProvider.dns)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    val httpDataSourceFactory = remember(playbackHttpClient) {
        OkHttpDataSource.Factory(playbackHttpClient)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
            .setDefaultRequestProperties(baseRequestHeaders)
    }
    val mediaCache = remember(context) { PlaybackCacheSingleton.getInstance(context) }
    val cacheDataSourceFactory = remember(httpDataSourceFactory, mediaCache) {
        CacheDataSource.Factory()
            .setCache(mediaCache)
            .setUpstreamDataSourceFactory(httpDataSourceFactory)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }
    // Non-cached factory for heavy/debrid progressive streams to avoid disk I/O bottleneck
    val directProgressiveFactory = remember(httpDataSourceFactory) {
        ProgressiveMediaSource.Factory(httpDataSourceFactory)
    }

    // Protocol-specific media source factories for faster startup
    val hlsFactory = remember(httpDataSourceFactory) {
        HlsMediaSource.Factory(cacheDataSourceFactory)
            .setAllowChunklessPreparation(true)
    }
    val dashFactory = remember(httpDataSourceFactory) {
        DashMediaSource.Factory(cacheDataSourceFactory)
    }
    val progressiveFactory = remember(httpDataSourceFactory) {
        ProgressiveMediaSource.Factory(cacheDataSourceFactory)
    }
    val mediaSourceFactory = remember(httpDataSourceFactory) {
        DefaultMediaSourceFactory(context)
            .setDataSourceFactory(cacheDataSourceFactory)
    }

    // ExoPlayer - tuned for both small and very large (70GB+) files.
    // Byte cap is authoritative (prioritize size over time) so high-bitrate streams
    // cannot exhaust memory on TV devices with limited heap (384-512 MB).
    val exoPlayer = remember {
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                15_000,    // minBufferMs — 15s safety net
                50_000,    // maxBufferMs — 50s max lookahead
                500,       // bufferForPlaybackMs — stable start
                2_500      // bufferForPlaybackAfterRebufferMs — stable resume
            )
            .setTargetBufferBytes(80 * 1024 * 1024)   // 80 MB hard cap
            .setPrioritizeTimeOverSizeThresholds(false) // byte cap is authoritative
            .setBackBuffer(3_000, false)                // minimal back buffer
            .build()

        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setRenderersFactory(
                DefaultRenderersFactory(context)
                    // Use hardware decoders first; extension decoders only as fallback.
                    // MODE_PREFER forces software decoding which is slow/jumpy on TV.
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
                        // DV-first compatibility path:
                        // allow selector to exceed strict reported caps when needed,
                        // because many Android TV devices under-report DV profile support.
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
                        if (playerReleasedAtomic.get()) return

                        // If playback was already running (has started), transient IO/timeout errors
                        // during seek or normal playback should attempt recovery by re-preparing
                        // at the current position instead of failing over to another source.
                        if (hasPlaybackStarted) {
                            val isTransientError =
                                error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_UNSPECIFIED ||
                                error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED ||
                                error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT ||
                                error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS ||
                                error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT ||
                                error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_BEHIND_LIVE_WINDOW
                            if (isTransientError && midPlaybackRecoveryAttempts < 3) {
                                midPlaybackRecoveryAttempts++
                                val pos = currentPosition.coerceAtLeast(0L)
                                val wasPlaying = playWhenReady
                                if (midPlaybackRecoveryAttempts <= 1) {
                                    // Light recovery: re-seek without re-reading container headers
                                    seekTo(pos)
                                } else {
                                    // Heavy recovery: full re-prepare (needed if light recovery didn't work)
                                    stop()
                                    prepare()
                                    seekTo(pos)
                                }
                                playWhenReady = wasPlaying
                                return
                            }
                        }

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
                            val sourceLikelyDv = isLikelyDolbyVisionStream(latestUiState.selectedStream)
                            if (!hasPlaybackStarted && sourceLikelyDv && dvStartupFallbackStage < 2) {
                                val selector = this@apply.trackSelector as? androidx.media3.exoplayer.trackselection.DefaultTrackSelector
                                val preferredMime = if (dvStartupFallbackStage == 0) {
                                    MimeTypes.VIDEO_H265
                                } else {
                                    MimeTypes.VIDEO_H264
                                }
                                selector?.let {
                                    it.parameters = it.buildUponParameters()
                                        .setPreferredVideoMimeType(preferredMime)
                                        .setExceedRendererCapabilitiesIfNecessary(true)
                                        .setExceedVideoConstraintsIfNecessary(true)
                                        .build()
                                }
                                dvStartupFallbackStage += 1
                                val keepPlaying = this@apply.playWhenReady
                                this@apply.stop()
                                this@apply.prepare()
                                this@apply.playWhenReady = keepPlaying
                                return
                            }
                            val heavy = isLikelyHeavyStream(latestUiState.selectedStream)
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
                                    latestUiState.selectedStream?.let { viewModel.selectStream(it) }
                                    return
                                }
                            }

                            if (!hasPlaybackStarted &&
                                allowStartupSourceFallback &&
                                !userSelectedSourceManually &&
                                tryAdvanceToNextStream()
                            ) {
                                return
                            }
                            if (!playbackIssueReported) {
                                playbackIssueReported = true
                                viewModel.onSelectedStreamPlaybackFailure()
                                viewModel.reportPlaybackError(playbackErrorMessageFor(error, hasPlaybackStarted))
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
                        val textTracks = mutableListOf<Subtitle>()
                        val subtitleByTrackId = latestUiState.subtitles.associateBy { subtitleTrackId(it) }
                        tracks.groups.forEachIndexed { groupIndex, group ->
                            if (group.type == C.TRACK_TYPE_TEXT) {
                                for (i in 0 until group.length) {
                                    val format = group.getTrackFormat(i)
                                    val formatTrackId = format.id?.trim().orEmpty()
                                    val matched = if (formatTrackId.isNotBlank()) {
                                        subtitleByTrackId[formatTrackId]
                                    } else {
                                        latestUiState.subtitles.firstOrNull { candidate ->
                                            !candidate.isEmbedded &&
                                                candidate.label.equals(format.label, ignoreCase = true) &&
                                                candidate.lang.equals(format.language ?: candidate.lang, ignoreCase = true)
                                        }
                                    }
                                    val lang = format.language ?: matched?.lang ?: "und"
                                    val label = format.label ?: matched?.label ?: getFullLanguageName(lang)
                                    val isExternal = matched?.url?.isNotBlank() == true
                                    textTracks.add(Subtitle(
                                        id = matched?.id ?: formatTrackId.ifBlank { "embedded_${groupIndex}_$i" },
                                        url = matched?.url.orEmpty(),
                                        lang = lang,
                                        label = label,
                                        isEmbedded = !isExternal,
                                        groupIndex = groupIndex,
                                        trackIndex = i
                                    ))
                                }
                            }
                        }
                        viewModel.updatePlayerTextTracks(textTracks)
                    }
                })
            }
    }

    val queueControlsSeek: (Long) -> Unit = queueSeek@{ deltaMs ->
        if (playerReleased) return@queueSeek
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
            if (!playerReleased) {
                exoPlayer.seekTo(scrubPreviewPosition)
            }
            isControlScrubbing = false
        }
    }

    val commitControlsSeekNow: () -> Unit = commitSeek@{
        if (playerReleased) return@commitSeek
        if (isControlScrubbing) {
            controlsSeekJob?.cancel()
            exoPlayer.seekTo(scrubPreviewPosition)
            isControlScrubbing = false
        }
    }

    LaunchedEffect(uiState.preferredAudioLanguage) {
        if (playerReleased) return@LaunchedEffect
        val trackSelector = exoPlayer.trackSelector as? androidx.media3.exoplayer.trackselection.DefaultTrackSelector
        if (trackSelector != null) {
            val params = trackSelector.buildUponParameters()
                .setPreferredAudioLanguage(uiState.preferredAudioLanguage)
                .build()
            trackSelector.parameters = params
        }
    }

    // Frame rate matching: set ExoPlayer strategy + actual display mode switching
    val frameRateActivity = context as? android.app.Activity
    LaunchedEffect(uiState.frameRateMatchingMode) {
        if (playerReleased) return@LaunchedEffect
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

    // Actual display mode switching when stream URL changes and frame rate matching is enabled
    LaunchedEffect(uiState.selectedStreamUrl, uiState.frameRateMatchingMode) {
        val url = uiState.selectedStreamUrl ?: return@LaunchedEffect
        val mode = uiState.frameRateMatchingMode
        val activity = frameRateActivity ?: return@LaunchedEffect
        if (mode == "Off" || mode.isBlank()) {
            com.arflix.tv.util.FrameRateUtils.restoreOriginalMode(activity)
            return@LaunchedEffect
        }
        // Detect and switch in background
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            val detection = com.arflix.tv.util.FrameRateUtils.detectFrameRate(url)
            if (detection != null) {
                com.arflix.tv.util.FrameRateUtils.matchFrameRateAndWait(activity, detection.snapped)
            }
        }
    }

    // Restore original display mode when leaving the player
    DisposableEffect(frameRateActivity) {
        onDispose {
            frameRateActivity?.let { com.arflix.tv.util.FrameRateUtils.restoreOriginalMode(it) }
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

    // Update player when stream URL changes. Attach currently-known external subtitle tracks once,
    // then switch subtitle tracks via track overrides (no media source rebuild needed).
    LaunchedEffect(uiState.selectedStreamUrl, uiState.streamSelectionNonce) {
        if (playerReleased) return@LaunchedEffect
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
                dvStartupFallbackStage = 0
                blackVideoRecoveryStage = 0
                blackVideoReadySinceMs = null
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
            longRebufferCount = 0

            // Only add the selected subtitle to ExoPlayer (not all 30+).
            // Loading all external subs slows down preparation and causes non-UTF8 subs to fail.
            val selectedSub = uiState.selectedSubtitle
            val subtitleConfigs = if (selectedSub != null && !selectedSub.isEmbedded) {
                buildExternalSubtitleConfigurations(listOf(selectedSub))
            } else {
                emptyList()
            }
            val mediaItemBuilder = MediaItem.Builder().setUri(Uri.parse(url))
            if (subtitleConfigs.isNotEmpty()) {
                mediaItemBuilder.setSubtitleConfigurations(subtitleConfigs)
            }
            val mediaItem = mediaItemBuilder.build()

            // Use protocol-specific media source for faster startup:
            // - HLS: chunkless preparation enabled (saves 1-3s)
            // - DASH/Progressive: dedicated factories for optimal handling
            val urlLower = url.lowercase()
            val isHeavy = isLikelyHeavyStream(latestUiState.selectedStream)
            val mediaSource: MediaSource = when {
                urlLower.contains(".m3u8") || urlLower.contains("/hls") || urlLower.contains("format=hls") ->
                    hlsFactory.createMediaSource(mediaItem)
                urlLower.contains(".mpd") || urlLower.contains("/dash") || urlLower.contains("format=dash") ->
                    dashFactory.createMediaSource(mediaItem)
                isHeavy ->
                    // Bypass disk cache for large/debrid progressive streams to avoid I/O bottleneck
                    directProgressiveFactory.createMediaSource(mediaItem)
                else -> mediaSourceFactory.createMediaSource(mediaItem)
            }

            // Source-switch hardening: stop+clear before loading next source.
            runCatching {
                exoPlayer.playWhenReady = false
                exoPlayer.stop()
                exoPlayer.clearMediaItems()
            }

            val resumePosition = uiState.savedPosition
            if (resumePosition > 0L) {
                exoPlayer.setMediaSource(mediaSource, resumePosition)
            } else {
                exoPlayer.setMediaSource(mediaSource)
            }
            // Let ExoPlayer's LoadControl handle buffering (bufferForPlaybackMs = 500ms).
            // No manual startup gate — trust the CDN/debrid to deliver fast enough.
            exoPlayer.playWhenReady = true
            exoPlayer.prepare()

            // Prefer currently selected subtitle language (if any), otherwise keep text disabled.
            val subtitle = uiState.selectedSubtitle
            if (subtitle != null) {
                exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                    .buildUpon()
                    .clearOverridesOfType(C.TRACK_TYPE_TEXT)
                    .setPreferredTextLanguage(subtitle.lang)
                    .setSelectUndeterminedTextLanguage(true)
                    .setIgnoredTextSelectionFlags(0)
                    .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                    .build()
            } else {
                exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                    .buildUpon()
                    .clearOverridesOfType(C.TRACK_TYPE_TEXT)
                    .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true)
                    .build()
            }

        }
    }

    // When new external subtitles arrive after initial load, rebuild the MediaItem once.
    // Subtitle rebuild removed: we now load only the selected subtitle on-demand.
    // When user switches subtitles, the LaunchedEffect below rebuilds the MediaItem with the new sub.
    var subtitleRebuildDone by remember { mutableStateOf(false) }
    var initialSubtitleCount by remember { mutableIntStateOf(-1) }
    LaunchedEffect(uiState.subtitles.size) {
        if (playerReleased) return@LaunchedEffect
        val newCount = uiState.subtitles.size
        if (initialSubtitleCount < 0) { initialSubtitleCount = newCount; return@LaunchedEffect }
        // No longer rebuild with all subs - they're loaded individually on selection
        initialSubtitleCount = newCount
    }
    // Reset rebuild flag when stream changes
    LaunchedEffect(uiState.selectedStreamUrl) { subtitleRebuildDone = false; initialSubtitleCount = -1 }

    // When subtitle selection changes, rebuild MediaItem with just the selected subtitle.
    // This avoids loading all 30+ subtitle files and fixes non-English encoding issues.
    LaunchedEffect(uiState.selectedSubtitle, uiState.subtitleSelectionNonce) {
        if (playerReleased) return@LaunchedEffect
        val subtitle = uiState.selectedSubtitle
        val url = uiState.selectedStreamUrl ?: return@LaunchedEffect

        if (subtitle == null) {
            // Disable all text tracks
            exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                .buildUpon()
                .clearOverridesOfType(C.TRACK_TYPE_TEXT)
                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true)
                .build()
            return@LaunchedEffect
        }

        if (subtitle.isEmbedded && subtitle.groupIndex != null && subtitle.trackIndex != null) {
            // For embedded subs, just select the track directly
            val groups = exoPlayer.currentTracks.groups
            val params = exoPlayer.trackSelectionParameters.buildUpon()
                .clearOverridesOfType(C.TRACK_TYPE_TEXT)
                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
            if (subtitle.groupIndex in groups.indices &&
                groups[subtitle.groupIndex].type == C.TRACK_TYPE_TEXT) {
                params.setOverrideForType(
                    androidx.media3.common.TrackSelectionOverride(
                        groups[subtitle.groupIndex].mediaTrackGroup,
                        subtitle.trackIndex
                    )
                )
            }
            exoPlayer.trackSelectionParameters = params.build()
            return@LaunchedEffect
        }

        // External subtitle: rebuild MediaItem with just this one subtitle
        if (subtitle.url.isNotBlank() && exoPlayer.playbackState != Player.STATE_IDLE) {
            val currentPosition = exoPlayer.currentPosition
            val wasPlaying = exoPlayer.isPlaying
            val subtitleConfigs = buildExternalSubtitleConfigurations(listOf(subtitle))
            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(url))
                .setSubtitleConfigurations(subtitleConfigs)
                .build()
            exoPlayer.setMediaItem(mediaItem, currentPosition)
            exoPlayer.prepare()
            if (wasPlaying) exoPlayer.play()

            // Enable the subtitle track after rebuild
            exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                .buildUpon()
                .clearOverridesOfType(C.TRACK_TYPE_TEXT)
                .setPreferredTextLanguage(subtitle.lang)
                .setSelectUndeterminedTextLanguage(true)
                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                .build()
        }
    }

    // Re-apply embedded subtitle selection when track list updates (e.g., after onTracksChanged)
    LaunchedEffect(uiState.subtitles) {
        if (playerReleased) return@LaunchedEffect
        val subtitle = uiState.selectedSubtitle ?: return@LaunchedEffect
        if (!subtitle.isEmbedded) return@LaunchedEffect

        // Find the resolved version with groupIndex/trackIndex from ExoPlayer
        val resolved = uiState.subtitles.firstOrNull {
            it.id == subtitle.id && it.groupIndex != null && it.trackIndex != null
        } ?: return@LaunchedEffect

        val groups = exoPlayer.currentTracks.groups
        if (resolved.groupIndex != null && resolved.trackIndex != null &&
            resolved.groupIndex in groups.indices &&
            groups[resolved.groupIndex].type == C.TRACK_TYPE_TEXT
        ) {
            exoPlayer.trackSelectionParameters = exoPlayer.trackSelectionParameters
                .buildUpon()
                .clearOverridesOfType(C.TRACK_TYPE_TEXT)
                .setOverrideForType(
                    androidx.media3.common.TrackSelectionOverride(
                        groups[resolved.groupIndex].mediaTrackGroup,
                        resolved.trackIndex
                    )
                )
                .setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                .build()
        }
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
    LaunchedEffect(aspectIndicatorTrigger) {
        if (aspectIndicatorTrigger > 0) {
            showAspectIndicator = true
            kotlinx.coroutines.delay(1200)
            showAspectIndicator = false
        }
    }
    LaunchedEffect(showVolumeIndicator) {
        if (showVolumeIndicator) {
            kotlinx.coroutines.delay(1500)
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
        while (!playerReleasedAtomic.get()) {
            if (playerReleasedAtomic.get()) break
            currentPosition = runCatching { exoPlayer.currentPosition }.getOrDefault(currentPosition)
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
                        longRebufferCount += 1
                        viewModel.onLongRebufferDetected()
                        if (allowMidPlaybackSourceFallback &&
                            !userSelectedSourceManually &&
                            longRebufferCount >= 1 &&
                            tryAdvanceToNextStream()
                        ) {
                            continue
                        }
                        if (!rebufferRecoverAttempted) {
                            rebufferRecoverAttempted = true
                            // Avoid hard re-prepare loops that can worsen long-form buffering.
                            // Nudge playback state only; let load control continue buffering.
                            exoPlayer.playWhenReady = true
                        }
                    }
                }
            } else {
                bufferingStartTime = null
                if (exoPlayer.isPlaying && exoPlayer.playbackState == Player.STATE_READY) {
                    longRebufferCount = 0
                }
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
                        if (allowStartupSourceFallback &&
                            !userSelectedSourceManually &&
                            tryAdvanceToNextStream()
                        ) {
                            // auto advanced to a fallback stream
                        } else if (!isHeavyStartupSource) {
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
                        viewModel.onSelectedStreamPlaybackFailure()
                        viewModel.reportPlaybackError(
                            if (autoAdvanceAttempts > 0 || startupSameSourceRetryCount > 0) {
                                "Source did not start after retries/fallback. Try another source."
                            } else {
                                "Source did not start in time. Try another source."
                            }
                        )
                    }
                }
            }

            // Dolby Vision black-screen recovery:
            // Some TVs select an incompatible DV path (audio plays, no video). Detect sustained
            // READY+playing with selected audio but zero video size, then force non-DV codecs.
            val hasSelectedAudioTrack = exoPlayer.currentTracks.groups.any { group ->
                group.type == C.TRACK_TYPE_AUDIO && group.isSelected && group.length > 0
            }
            val hasVideoOutput = exoPlayer.videoSize.width > 0 && exoPlayer.videoSize.height > 0
            val blackVideoState =
                uiState.selectedStreamUrl != null &&
                    exoPlayer.playbackState == Player.STATE_READY &&
                    exoPlayer.playWhenReady &&
                    hasSelectedAudioTrack &&
                    !hasVideoOutput
            if (blackVideoState) {
                if (blackVideoReadySinceMs == null) {
                    blackVideoReadySinceMs = System.currentTimeMillis()
                } else {
                    val stuckMs = System.currentTimeMillis() - (blackVideoReadySinceMs ?: 0L)
                    val thresholdMs = if (blackVideoRecoveryStage == 0) 6_500L else 9_000L
                    if (stuckMs >= thresholdMs && blackVideoRecoveryStage < 2) {
                        val selector = exoPlayer.trackSelector as? androidx.media3.exoplayer.trackselection.DefaultTrackSelector
                        val preferredMime = if (blackVideoRecoveryStage == 0) {
                            MimeTypes.VIDEO_H265
                        } else {
                            MimeTypes.VIDEO_H264
                        }
                        selector?.let {
                            it.parameters = it.buildUponParameters()
                                .setPreferredVideoMimeType(preferredMime)
                                .setExceedRendererCapabilitiesIfNecessary(true)
                                .setExceedVideoConstraintsIfNecessary(true)
                                .build()
                        }
                        val resumeAt = exoPlayer.currentPosition.coerceAtLeast(0L)
                        val keepPlaying = exoPlayer.playWhenReady
                        exoPlayer.seekTo(resumeAt)
                        exoPlayer.prepare()
                        exoPlayer.playWhenReady = keepPlaying
                        blackVideoRecoveryStage += 1
                        blackVideoReadySinceMs = System.currentTimeMillis()
                    }
                }
            } else {
                blackVideoReadySinceMs = null
            }

            // Mark playback as started as soon as the player is actually playing.
            if (!hasPlaybackStarted &&
                exoPlayer.playbackState == Player.STATE_READY &&
                exoPlayer.isPlaying
            ) {
                hasPlaybackStarted = true
                midPlaybackRecoveryAttempts = 0
                val startupMs = streamSelectedTime?.let { startedAt ->
                    (System.currentTimeMillis() - startedAt).coerceAtLeast(0L)
                } ?: 0L
                viewModel.onPlaybackStarted(
                    startupMs = startupMs,
                    startupRetries = startupSameSourceRetryCount + if (startupSameSourceRefreshAttempted) 1 else 0,
                    autoFailovers = autoAdvanceAttempts
                )
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
                        selected?.source?.takeIf { it.isNotBlank() },
                        selected?.behaviorHints?.bingeGroup?.takeIf { it.isNotBlank() }
                    )
                }
            }

            val tickDelayMs = when {
                !hasPlaybackStarted -> 150L
                uiState.activeSkipInterval != null && !uiState.skipIntervalDismissed -> 200L
                else -> 500L
            }
            delay(tickDelayMs)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            controlsSeekJob?.cancel()
            playerReleasedAtomic.set(true)
            playerReleased = true
            runCatching {
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
            }
            runCatching { exoPlayer.release() }
        }
    }

    // Close menus when an error occurs so the error overlay can receive input
    LaunchedEffect(uiState.error) {
        if (uiState.error != null) {
            showSourceMenu = false
            showSubtitleMenu = false
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
        if (uiState.error != null) {
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

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    // Read subtitle appearance prefs
    val subtitleSizePref = uiState.subtitleSize
    val subtitleColorPref = uiState.subtitleColor
    val aspectModeLabel = when (playerResizeMode) {
        AspectRatioFrameLayout.RESIZE_MODE_ZOOM -> "Zoom"
        AspectRatioFrameLayout.RESIZE_MODE_FILL -> "Fill"
        else -> "Fit"
    }
    val cycleAspectRatio: () -> Unit = {
        playerResizeMode = when (playerResizeMode) {
            AspectRatioFrameLayout.RESIZE_MODE_FIT -> AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            AspectRatioFrameLayout.RESIZE_MODE_ZOOM -> AspectRatioFrameLayout.RESIZE_MODE_FILL
            else -> AspectRatioFrameLayout.RESIZE_MODE_FIT
        }
        aspectIndicatorTrigger++
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .focusRequester(containerFocusRequester)
            .focusable()
            .then(
                if (isTouchDevice) {
                    Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                if (uiState.error == null && !showSubtitleMenu && !showSourceMenu) {
                                    showControls = !showControls
                                }
                            },
                            onDoubleTap = { offset ->
                                if (uiState.error == null && !showSubtitleMenu && !showSourceMenu) {
                                    val halfWidth = size.width / 2
                                    if (offset.x < halfWidth) {
                                        // Double-tap left side: rewind 10 seconds
                                        queueControlsSeek(-10_000L)
                                    } else {
                                        // Double-tap right side: forward 10 seconds
                                        queueControlsSeek(10_000L)
                                    }
                                }
                            }
                        )
                    }
                } else {
                    Modifier
                }
            )
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    // Handle error modal
                    if (uiState.error != null) {
                        val maxButtons = if (uiState.isSetupError) 0 else 1 // setup=1 button, error=2 buttons
                        return@onKeyEvent when (event.key) {
                            Key.DirectionLeft -> {
                                if (errorModalFocusIndex > 0) errorModalFocusIndex--
                                true
                            }
                            Key.DirectionRight -> {
                                if (errorModalFocusIndex < maxButtons) errorModalFocusIndex++
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                if (uiState.isSetupError) {
                                    onBack()
                                } else {
                                    if (errorModalFocusIndex == 0) viewModel.retry() else onBack()
                                }
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
                            // Always toggle play/pause on Enter/Select.
                            // Controls overlay buttons have their own onKeyEvent handlers
                            // that will intercept Enter before this point if they have focus.
                            if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                            if (!showControls) showControls = true
                            true
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
                        resizeMode = playerResizeMode

                        // Enable subtitle view with Netflix-style: bold white text with black outline
                        subtitleView?.apply {
                            // Read subtitle appearance from user settings
                            val subSizeSp = when (subtitleSizePref) {
                                "Small" -> 18f; "Large" -> 30f; "Extra Large" -> 36f; else -> 24f
                            }
                            val subFgColor = when (subtitleColorPref) {
                                "Yellow" -> android.graphics.Color.YELLOW
                                "Green" -> android.graphics.Color.GREEN
                                "Cyan" -> android.graphics.Color.CYAN
                                else -> android.graphics.Color.WHITE
                            }
                            setStyle(
                                androidx.media3.ui.CaptionStyleCompat(
                                    subFgColor,
                                    android.graphics.Color.TRANSPARENT,
                                    android.graphics.Color.TRANSPARENT,
                                    androidx.media3.ui.CaptionStyleCompat.EDGE_TYPE_OUTLINE,
                                    android.graphics.Color.BLACK,
                                    android.graphics.Typeface.DEFAULT_BOLD
                                )
                            )
                            setApplyEmbeddedStyles(false)
                            setApplyEmbeddedFontSizes(false)
                            setFixedTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, subSizeSp)
                            setBottomPaddingFraction(0.08f)
                        }
                    }
                },
                update = { playerView ->
                    playerView.player = exoPlayer
                    playerView.resizeMode = playerResizeMode
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
                    PulsingLogo(logoUrl = uiState.logoUrl, title = uiState.title)

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
                PulsingLogo(logoUrl = uiState.logoUrl, title = uiState.title)
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
                .align(Alignment.BottomEnd)
                .zIndex(5f) // Ensure it's above the controls overlay scrim.
                .padding(end = if (isTouchDevice) 24.dp else 48.dp, bottom = if (showControls) 90.dp else 32.dp)
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
                                color = TextPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
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
                                    color = TextSecondary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
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
                                    color = Pink,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
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
                                        color = TextSecondary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }

                    // Right side - Ends At + Clock
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(end = 8.dp)) {
                        val currentTime = remember { mutableStateOf("") }
                        val endsAtTime = remember { mutableStateOf("") }
                        LaunchedEffect(duration, currentPosition) {
                            while (true) {
                                val now = System.currentTimeMillis()
                                val sdf = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                                currentTime.value = sdf.format(java.util.Date(now))
                                if (duration > 0 && currentPosition >= 0) {
                                    val remainingMs = (duration - currentPosition).coerceAtLeast(0L)
                                    endsAtTime.value = sdf.format(java.util.Date(now + remainingMs))
                                } else { endsAtTime.value = "" }
                                kotlinx.coroutines.delay(1000)
                            }
                        }
                        Text(currentTime.value, style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium), color = TextSecondary, maxLines = 1)
                        if (endsAtTime.value.isNotBlank()) {
                            Text("Ends at ${endsAtTime.value}", style = ArflixTypography.caption.copy(fontSize = 12.sp), color = TextSecondary.copy(alpha = 0.7f), maxLines = 1, modifier = Modifier.padding(top = 2.dp))
                        }
                    }
                }

                // Bottom controls - positioned at very bottom
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    0.0f to Color.Transparent,
                                    0.3f to Color.Black.copy(alpha = 0.2f),
                                    1.0f to Color.Black.copy(alpha = 0.7f)
                                )
                            )
                        )
                        .padding(horizontal = if (isTouchDevice) 24.dp else 48.dp)
                        .padding(top = if (isTouchDevice) 16.dp else 24.dp, bottom = if (isTouchDevice) 32.dp else 24.dp)
                ) {
                    // Icon buttons row - left-aligned, tight above trackbar (avoids subtitle overlap)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val smallBtn = if (isTouchDevice) 24.dp else 28.dp
                        val smallIcon = if (isTouchDevice) 17.dp else 19.dp
                        val midBtn = if (isTouchDevice) 28.dp else 30.dp
                        val midIcon = if (isTouchDevice) 20.dp else 22.dp
                        val bigBtn = if (isTouchDevice) 34.dp else 38.dp
                        val bigIcon = if (isTouchDevice) 26.dp else 28.dp
                        val gap = if (isTouchDevice) 10.dp else 14.dp
                        val wideGap = if (isTouchDevice) 14.dp else 18.dp

                        // Subtitles
                        PlayerIconButton(icon = Icons.Default.ClosedCaption, contentDescription = "Subtitles & Audio",
                            focusRequester = subtitleButtonFocusRequester, size = smallBtn, iconSize = smallIcon,
                            onFocusChanged = { if (it) focusedButton = 1 },
                            onClick = { showSubtitleMenu = true; subtitleMenuIndex = 0 },
                            onLeftKey = { if (mediaType == MediaType.TV) nextEpisodeButtonFocusRequester.requestFocus() else aspectButtonFocusRequester.requestFocus() },
                            onRightKey = { sourceButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        Spacer(modifier = Modifier.width(gap))

                        // Sources
                        PlayerIconButton(icon = Icons.Default.Folder, contentDescription = "Sources",
                            focusRequester = sourceButtonFocusRequester, size = smallBtn, iconSize = smallIcon,
                            onFocusChanged = {},
                            onClick = { showSourceMenu = true; showControls = true },
                            onLeftKey = { subtitleButtonFocusRequester.requestFocus() },
                            onRightKey = { rewindButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        Spacer(modifier = Modifier.width(wideGap))

                        // Rewind 10s
                        PlayerIconButton(icon = Icons.Default.Replay10, contentDescription = "Rewind 10s",
                            focusRequester = rewindButtonFocusRequester, size = midBtn, iconSize = midIcon,
                            onFocusChanged = {},
                            onClick = { queueControlsSeek(-10_000L) },
                            onLeftKey = { sourceButtonFocusRequester.requestFocus() },
                            onRightKey = { playButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        Spacer(modifier = Modifier.width(gap))

                        // Play/Pause - center, largest
                        PlayerIconButton(icon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            focusRequester = playButtonFocusRequester, size = bigBtn, iconSize = bigIcon,
                            onFocusChanged = { if (it) focusedButton = 0 },
                            onClick = { if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play() },
                            onLeftKey = { rewindButtonFocusRequester.requestFocus() },
                            onRightKey = { forwardButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() },
                            onUpKey = { val sv = uiState.activeSkipInterval != null && !uiState.skipIntervalDismissed; if (sv) skipIntroFocusRequester.requestFocus() })

                        Spacer(modifier = Modifier.width(gap))

                        // Forward 10s - own focus requester
                        PlayerIconButton(icon = Icons.Default.Forward10, contentDescription = "Forward 10s",
                            focusRequester = forwardButtonFocusRequester, size = midBtn, iconSize = midIcon,
                            onFocusChanged = {},
                            onClick = { queueControlsSeek(10_000L) },
                            onLeftKey = { playButtonFocusRequester.requestFocus() },
                            onRightKey = { aspectButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        Spacer(modifier = Modifier.width(wideGap))

                        // Aspect Ratio
                        PlayerIconButton(icon = Icons.Default.AspectRatio, contentDescription = "Aspect: $aspectModeLabel",
                            focusRequester = aspectButtonFocusRequester, size = smallBtn, iconSize = smallIcon,
                            onFocusChanged = {},
                            onClick = cycleAspectRatio,
                            onLeftKey = { forwardButtonFocusRequester.requestFocus() },
                            onRightKey = { if (mediaType == MediaType.TV) nextEpisodeButtonFocusRequester.requestFocus() else subtitleButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        if (mediaType == MediaType.TV) {
                            Spacer(modifier = Modifier.width(gap))
                            PlayerIconButton(icon = Icons.Default.SkipNext, contentDescription = "Next Episode",
                                focusRequester = nextEpisodeButtonFocusRequester, size = smallBtn, iconSize = smallIcon,
                                onFocusChanged = {},
                                onClick = {
                                    val season = seasonNumber ?: return@PlayerIconButton
                                    val episode = episodeNumber ?: return@PlayerIconButton
                                    val selected = uiState.selectedStream
                                    onPlayNext(season, episode + 1, selected?.addonId?.takeIf { it.isNotBlank() }, selected?.source?.takeIf { it.isNotBlank() }, selected?.behaviorHints?.bingeGroup?.takeIf { it.isNotBlank() })
                                },
                                onLeftKey = { aspectButtonFocusRequester.requestFocus() },
                                onRightKey = { subtitleButtonFocusRequester.requestFocus() },
                                onDownKey = { trackbarFocusRequester.requestFocus() })
                        }
                    }

                    Spacer(modifier = Modifier.height(if (isTouchDevice) 4.dp else 6.dp))

                    // Trackbar at the very bottom with time labels
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = formatTime(if (isControlScrubbing) scrubPreviewPosition else currentPosition),
                            style = ArflixTypography.label.copy(fontSize = if (isTouchDevice) 12.sp else 13.sp),
                            color = Color.White.copy(alpha = 0.9f),
                            maxLines = 1,
                            modifier = Modifier.width(if (isTouchDevice) 48.dp else 55.dp)
                        )

                        // Trackbar
                        var trackbarFocused by remember { mutableStateOf(false) }
                        val trackbarHeight by animateFloatAsState(if (trackbarFocused) 8f else if (isTouchDevice) 6f else 4f, label = "trackbarHeight")
                        var trackbarWidthPx by remember { mutableIntStateOf(0) }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(if (isTouchDevice) 28.dp else 20.dp)
                                .onSizeChanged { trackbarWidthPx = it.width }
                                .focusRequester(trackbarFocusRequester)
                                .onFocusChanged { state ->
                                    trackbarFocused = state.isFocused
                                    if (!state.isFocused && isControlScrubbing) commitControlsSeekNow()
                                }
                                .focusable()
                                .pointerInput(duration) {
                                    detectHorizontalDragGestures(
                                        onDragStart = { offset -> if (duration > 0L && trackbarWidthPx > 0) { scrubPreviewPosition = ((offset.x / trackbarWidthPx).coerceIn(0f, 1f) * duration).toLong(); isControlScrubbing = true } },
                                        onDragEnd = { if (isControlScrubbing && !playerReleased) { exoPlayer.seekTo(scrubPreviewPosition); isControlScrubbing = false } },
                                        onDragCancel = { if (isControlScrubbing && !playerReleased) { exoPlayer.seekTo(scrubPreviewPosition); isControlScrubbing = false } },
                                        onHorizontalDrag = { _, dragAmount -> if (duration > 0L && trackbarWidthPx > 0) { val delta = (dragAmount / trackbarWidthPx * duration).toLong(); scrubPreviewPosition = (scrubPreviewPosition + delta).coerceIn(0L, duration); isControlScrubbing = true } }
                                    )
                                }
                                .pointerInput(duration) {
                                    detectTapGestures { offset -> if (duration > 0L && trackbarWidthPx > 0 && !playerReleased) { exoPlayer.seekTo(((offset.x / trackbarWidthPx).coerceIn(0f, 1f) * duration).toLong()) } }
                                }
                                .onKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown && trackbarFocused) {
                                        when (event.key) {
                                            Key.DirectionLeft -> { queueControlsSeek(-10_000L); true }
                                            Key.DirectionRight -> { queueControlsSeek(10_000L); true }
                                            Key.Enter, Key.DirectionCenter -> { commitControlsSeekNow(); true }
                                            Key.DirectionUp -> { playButtonFocusRequester.requestFocus(); true }
                                            Key.DirectionDown -> true
                                            else -> false
                                        }
                                    } else false
                                }
                                .background(Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            // Visible thin bar centered in the larger touch target
                            val barHeight = if (trackbarFocused) 8.dp else if (isTouchDevice) 6.dp else 4.dp
                            Box(modifier = Modifier.fillMaxWidth().height(barHeight).background(Color.White.copy(alpha = if (trackbarFocused) 0.25f else 0.15f), RoundedCornerShape(3.dp)))
                            val frac = if (duration > 0) ((if (isControlScrubbing) scrubPreviewPosition else currentPosition).toFloat() / duration.toFloat()).coerceIn(0f, 1f) else progress
                            Box(modifier = Modifier.fillMaxWidth().height(barHeight).align(Alignment.Center), contentAlignment = Alignment.CenterStart) {
                            Box(modifier = Modifier.fillMaxWidth(frac).fillMaxHeight().background(
                                if (trackbarFocused) Pink else Pink.copy(alpha = 0.8f), RoundedCornerShape(3.dp)
                            ))
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = formatTime(duration),
                            style = ArflixTypography.label.copy(fontSize = if (isTouchDevice) 12.sp else 13.sp),
                            color = Color.White.copy(alpha = 0.5f),
                            maxLines = 1,
                            modifier = Modifier.width(if (isTouchDevice) 48.dp else 55.dp)
                        )
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
            hasStreamingAddons = !uiState.isSetupError,
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
                longRebufferCount = 0
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

        // Aspect ratio indicator - brief center popup
        AnimatedVisibility(
            visible = showAspectIndicator,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(10.dp))
                    .padding(horizontal = 24.dp, vertical = 14.dp)
            ) {
                Text(
                    text = aspectModeLabel,
                    style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium),
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

        // Error modal — friendly setup guide for no-addons, red error for actual playback failures
        AnimatedVisibility(
            visible = uiState.error != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            val isSetup = uiState.isSetupError
            val accentColor = if (isSetup) Color(0xFF3B82F6) else Color(0xFFEF4444) // blue vs red
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .width(480.dp)
                        .background(Color(0xFF1A1A1A), RoundedCornerShape(16.dp))
                        .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .background(accentColor.copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isSetup) Icons.Default.Settings else Icons.Default.ErrorOutline,
                            contentDescription = if (isSetup) "Setup" else "Error",
                            tint = accentColor,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = if (isSetup) "Addon Setup Required" else "Playback Error",
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = uiState.error ?: "An unknown error occurred",
                        style = ArflixTypography.body,
                        color = TextSecondary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (isSetup) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "ARVIO uses community streaming addons to find video sources. Without at least one streaming addon, content cannot be played.",
                            style = ArflixTypography.caption,
                            color = TextSecondary.copy(alpha = 0.7f),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        if (!isSetup) {
                            ErrorButton(
                                text = "TRY AGAIN",
                                icon = Icons.Default.Refresh,
                                isFocused = errorModalFocusIndex == 0,
                                isPrimary = true,
                                onClick = { viewModel.retry() }
                            )
                        }
                        ErrorButton(
                            text = "GO BACK",
                            isFocused = if (isSetup) errorModalFocusIndex == 0 else errorModalFocusIndex == 1,
                            isPrimary = isSetup,
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
private fun PlayerIconButton(
    icon: ImageVector,
    contentDescription: String,
    focusRequester: FocusRequester,
    size: Dp = 32.dp,
    iconSize: Dp = 22.dp,
    onFocusChanged: (Boolean) -> Unit,
    onClick: () -> Unit,
    onLeftKey: () -> Unit = {},
    onRightKey: () -> Unit = {},
    onUpKey: () -> Unit = {},
    onDownKey: () -> Unit = {}
) {
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (focused) 1.15f else 1f, label = "iconScale")

    Box(
        modifier = Modifier
            .size(size)
            .focusRequester(focusRequester)
            .onFocusChanged { state -> focused = state.isFocused; onFocusChanged(state.isFocused) }
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Enter, Key.DirectionCenter -> { onClick(); true }
                        Key.DirectionLeft -> { onLeftKey(); true }
                        Key.DirectionRight -> { onRightKey(); true }
                        Key.DirectionUp -> { onUpKey(); true }
                        Key.DirectionDown -> { onDownKey(); true }
                        else -> false
                    }
                } else false
            }
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { onClick() }
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .background(
                color = if (focused) Color.White else Color.Transparent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (focused) Color.Black else Color.White.copy(alpha = 0.6f),
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
private fun PulsingLogo(logoUrl: String?, title: String, modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.92f, targetValue = 1.08f,
        animationSpec = infiniteRepeatable(animation = animTween(1200, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Reverse),
        label = "pulseScale"
    )
    Box(modifier = modifier.graphicsLayer { scaleX = scale; scaleY = scale }, contentAlignment = Alignment.Center) {
        if (!logoUrl.isNullOrBlank()) {
            AsyncImage(model = logoUrl, contentDescription = title, contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth(0.5f).height(100.dp))
        } else {
            Text(title, style = ArflixTypography.sectionTitle.copy(fontSize = 36.sp, fontWeight = FontWeight.Bold),
                color = Color.White, modifier = Modifier.padding(horizontal = 24.dp))
        }
    }
}

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
        normalizedCode == "pt-br" || normalizedCode == "pob" -> "Portuguese (Brazil)"
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
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val subtitleListState = rememberLazyListState()
    val audioListState = rememberLazyListState()

    if (!isMobile) {
        // ── TV layout (D-pad side panel) ──────────────────────────────────
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
    } else {
        // ── Mobile layout (bottom sheet style) ────────────────────────────
        var mobileTab by remember { mutableIntStateOf(activeTab) }
        val mobileListState = rememberLazyListState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClose() }
        ) {
            // Bottom sheet panel – occupies ~70% of screen height
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.70f)
                    .align(Alignment.BottomCenter)
                    .background(
                        Color(0xFF1A1A1A),
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* consume clicks so they don't dismiss */ }
            ) {
                // ── Header: title + close button ──────────────────────────
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 8.dp, top = 12.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (mobileTab == 0) "Subtitles" else "Audio",
                        style = ArflixTypography.body.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Color.White
                    )
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { onClose() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                // ── Tab row ───────────────────────────────────────────────
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Subtitles" to 0, "Audio" to 1).forEach { (label, tabIndex) ->
                        val selected = mobileTab == tabIndex
                        Box(
                            modifier = Modifier
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    mobileTab = tabIndex
                                    onTabChanged(tabIndex)
                                }
                                .background(
                                    if (selected) Color.White.copy(alpha = 0.15f) else Color.Transparent,
                                    RoundedCornerShape(20.dp)
                                )
                                .then(
                                    if (selected) Modifier.border(1.dp, Color.White.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                                    else Modifier
                                )
                                .padding(horizontal = 20.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = label,
                                style = ArflixTypography.body.copy(
                                    fontSize = 14.sp,
                                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                                ),
                                color = if (selected) Color.White else Color.White.copy(alpha = 0.6f)
                            )
                        }
                    }
                }

                // ── Thin divider ──────────────────────────────────────────
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.1f))
                )

                // ── Track list ────────────────────────────────────────────
                LazyColumn(
                    state = mobileListState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    if (mobileTab == 0) {
                        // "Off" option
                        item {
                            MobileTrackItem(
                                name = "Off",
                                description = null,
                                isSelected = selectedSubtitle == null,
                                onClick = { onSelectSubtitle(0) }
                            )
                            // Divider
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                                    .height(1.dp)
                                    .background(Color.White.copy(alpha = 0.06f))
                            )
                        }

                        itemsIndexed(subtitles) { index, sub ->
                            val trackLabel = sub.label.ifBlank { sub.lang }
                            val languageInfo = getFullLanguageName(sub.lang)
                            val description = if (trackLabel.lowercase() != languageInfo.lowercase() &&
                                !trackLabel.lowercase().contains(languageInfo.lowercase())
                            ) languageInfo else null

                            MobileTrackItem(
                                name = trackLabel,
                                description = description,
                                isSelected = selectedSubtitle?.id == sub.id,
                                onClick = { onSelectSubtitle(index + 1) }
                            )
                            // Divider between items
                            if (index < subtitles.lastIndex) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp)
                                        .height(1.dp)
                                        .background(Color.White.copy(alpha = 0.06f))
                                )
                            }
                        }
                    } else {
                        // Audio tab
                        if (audioTracks.isEmpty()) {
                            item {
                                Text(
                                    text = "No audio tracks available",
                                    style = ArflixTypography.body.copy(fontSize = 14.sp),
                                    color = TextSecondary,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        } else {
                            itemsIndexed(audioTracks) { index, track ->
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
                                val description = listOfNotNull(codecInfo, channelInfo).joinToString(" • ").ifEmpty { null }

                                MobileTrackItem(
                                    name = trackLabel,
                                    description = description,
                                    isSelected = index == selectedAudioIndex,
                                    onClick = { onSelectAudio(track) }
                                )
                                // Divider between items
                                if (index < audioTracks.lastIndex) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp)
                                            .height(1.dp)
                                            .background(Color.White.copy(alpha = 0.06f))
                                    )
                                }
                            }
                        }
                    }
                }
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

/** Single track row for the mobile bottom-sheet subtitle/audio selector. */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MobileTrackItem(
    name: String,
    description: String?,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = ArflixTypography.body.copy(fontSize = 14.sp),
                color = if (isSelected) Color.White else Color.White.copy(alpha = 0.85f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (description != null) {
                Text(
                    text = description,
                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                    color = Color.White.copy(alpha = 0.5f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = Color(0xFF4CAF50), // Green checkmark
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(20.dp)
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

private fun subtitleTrackId(subtitle: Subtitle): String {
    val explicit = subtitle.id.trim()
    if (explicit.isNotBlank()) return explicit

    val normalizedUrl = subtitle.url.trim().ifBlank {
        "${subtitle.lang.trim().lowercase()}|${subtitle.label.trim().lowercase()}"
    }
    val stableHash = normalizedUrl.hashCode().toUInt().toString(16)
    return "ext_$stableHash"
}

private fun buildExternalSubtitleConfigurations(subtitles: List<Subtitle>): List<MediaItem.SubtitleConfiguration> {
    return subtitles
        .asSequence()
        .filter { !it.isEmbedded }
        .mapNotNull { subtitle ->
            val rawUrl = subtitle.url.trim()
            if (rawUrl.isBlank()) return@mapNotNull null
            val normalizedUrl = if (rawUrl.startsWith("//")) "https:$rawUrl" else rawUrl
            runCatching {
                MediaItem.SubtitleConfiguration.Builder(Uri.parse(normalizedUrl))
                    .setId(subtitleTrackId(subtitle))
                    .setMimeType(subtitleMimeTypeFromUrl(normalizedUrl))
                    .setLanguage(subtitle.lang)
                    .setLabel(subtitle.label)
                    .setSelectionFlags(0)
                    .setRoleFlags(C.ROLE_FLAG_SUBTITLE)
                    .build()
            }.getOrNull()
        }
        .distinctBy { it.id ?: "${it.uri}" }
        .toList()
}

private fun subtitleMimeTypeFromUrl(url: String): String {
    val cleanUrl = url.substringBefore('?').lowercase()
    return when {
        cleanUrl.endsWith(".vtt") -> MimeTypes.TEXT_VTT
        cleanUrl.endsWith(".srt") || cleanUrl.endsWith(".srt.gz") -> MimeTypes.APPLICATION_SUBRIP
        cleanUrl.endsWith(".ass") || cleanUrl.endsWith(".ssa") -> MimeTypes.TEXT_SSA
        cleanUrl.endsWith(".ttml") || cleanUrl.endsWith(".dfxp") -> MimeTypes.APPLICATION_TTML
        // OpenSubtitles serves SRT through extensionless URLs - use SRT as default
        // since it's the dominant format from subtitle addons (OpenSubtitles, Comet).
        // SRT and VTT are similar but SRT uses comma for milliseconds (00:01:23,456)
        // while VTT uses period and requires a WEBVTT header. Using SRT avoids silent
        // parse failures when the actual content is SRT.
        else -> MimeTypes.APPLICATION_SUBRIP
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
        sizeBytes >= 60L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(180_000L)
        sizeBytes >= 40L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(150_000L)
        sizeBytes >= 30L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(120_000L)
        sizeBytes >= 20L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(90_000L)
        sizeBytes >= 10L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(60_000L)
        else -> timeoutMs
    }

    return timeoutMs.coerceAtMost(200_000L)
}

private fun playbackErrorMessageFor(
    error: androidx.media3.common.PlaybackException,
    hasPlaybackStarted: Boolean
): String {
    val reason = when (error.errorCode) {
        androidx.media3.common.PlaybackException.ERROR_CODE_DECODER_INIT_FAILED,
        androidx.media3.common.PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED,
        androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED,
        androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES ->
            "Codec not supported by this device"

        androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT,
        androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
        androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT ->
            "Network timeout while loading source"

        androidx.media3.common.PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS ->
            "Source server rejected playback request"

        androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED,
        androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED ->
            "Source format is invalid or unsupported"

        else -> "Source failed to play"
    }

    return if (hasPlaybackStarted) {
        "$reason. Try another source."
    } else {
        "$reason during startup. Trying another source may work."
    }
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

private fun isLikelyDolbyVisionStream(stream: StreamSource?): Boolean {
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
    return text.contains("dolby vision") ||
        text.contains(" dovi") ||
        text.contains(" dv ") ||
        text.contains(" dvp") ||
        text.contains("hdr10+dv")
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

private tailrec fun Context.findActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}

private fun resolveFrameRateSeamlessStrategy(): Int {
    return readMedia3FrameRateConst("VIDEO_CHANGE_FRAME_RATE_STRATEGY_ONLY_IF_SEAMLESS", fallback = 1)
}

private fun readMedia3FrameRateConst(fieldName: String, fallback: Int): Int {
    return runCatching { C::class.java.getField(fieldName).getInt(null) }.getOrDefault(fallback)
}

private object PlaybackCacheSingleton {
    @Volatile
    private var instance: SimpleCache? = null

    fun getInstance(context: android.content.Context): SimpleCache {
        return instance ?: synchronized(this) {
            instance ?: run {
                val cacheDir = java.io.File(context.applicationContext.cacheDir, "media3_playback_cache").apply { mkdirs() }
                val evictor = LeastRecentlyUsedCacheEvictor(256L * 1024L * 1024L)
                SimpleCache(cacheDir, evictor, StandaloneDatabaseProvider(context.applicationContext)).also {
                    instance = it
                }
            }
        }
    }
}

private class PlaybackCookieJar : CookieJar {
    private val cookiesByHost = ConcurrentHashMap<String, MutableList<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        if (cookies.isEmpty()) return
        val host = url.host
        val current = cookiesByHost[host]?.toMutableList() ?: mutableListOf()
        val now = System.currentTimeMillis()

        cookies.forEach { cookie ->
            if (cookie.expiresAt <= now) return@forEach
            current.removeAll { existing ->
                existing.name == cookie.name &&
                    existing.domain == cookie.domain &&
                    existing.path == cookie.path
            }
            current.add(cookie)
        }

        if (current.isEmpty()) {
            cookiesByHost.remove(host)
        } else {
            cookiesByHost[host] = current
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val host = url.host
        val now = System.currentTimeMillis()
        val list = cookiesByHost[host]?.toMutableList() ?: return emptyList()
        val valid = list.filter { cookie -> cookie.expiresAt > now && cookie.matches(url) }
        if (valid.size != list.size) {
            if (valid.isEmpty()) {
                cookiesByHost.remove(host)
            } else {
                cookiesByHost[host] = valid.toMutableList()
            }
        }
        return valid
    }
}
