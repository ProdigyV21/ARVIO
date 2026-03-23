@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.player

import android.content.Context
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import com.arflix.tv.BuildConfig
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
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
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.components.WaveLoadingDots
import androidx.compose.ui.text.style.TextOverflow
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
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
    val uiState by viewModel.uiState.collectAsState()
    val latestUiState by rememberUpdatedState(uiState)
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    var isPlaying by remember { mutableStateOf(false) }
    var isBuffering by remember { mutableStateOf(true) }
    var hasPlaybackStarted by remember { mutableStateOf(false) }  
    var showControls by remember { mutableStateOf(true) }
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    var progress by remember { mutableFloatStateOf(0f) }

    // Skip overlay state
    var skipAmount by remember { mutableIntStateOf(0) }
    var showSkipOverlay by remember { mutableStateOf(false) }
    var lastSkipTime by remember { mutableLongStateOf(0L) }
    var skipStartPosition by remember { mutableLongStateOf(0L) }
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

    // Focus requesters
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

    var focusedButton by remember { mutableIntStateOf(0) }
    var showSubtitleMenu by remember { mutableStateOf(false) }
    var showSourceMenu by remember { mutableStateOf(false) }
    var playerResizeMode by remember { mutableIntStateOf(AspectRatioFrameLayout.RESIZE_MODE_FIT) }
    var subtitleMenuIndex by remember { mutableIntStateOf(0) }
    var subtitleMenuTab by remember { mutableIntStateOf(0) }

    var audioTracks by remember { mutableStateOf<List<AudioTrackInfo>>(emptyList()) }
    var selectedAudioIndex by remember { mutableIntStateOf(0) }
    var errorModalFocusIndex by remember { mutableIntStateOf(0) }

    var bufferingStartTime by remember { mutableStateOf<Long?>(null) }
    val bufferingTimeoutMs = 25_000L
    var userSelectedSourceManually by remember { mutableStateOf(false) }
    val initialBufferingTimeoutMs = remember(uiState.selectedStream, userSelectedSourceManually) {
        estimateInitialStartupTimeoutMs(uiState.selectedStream, userSelectedSourceManually)
    }

    var streamSelectedTime by remember { mutableStateOf<Long?>(null) }
    var playbackIssueReported by remember { mutableStateOf(false) }
    var startupRecoverAttempted by remember { mutableStateOf(false) }
    var startupHardFailureReported by remember { mutableStateOf(false) }
    var startupSameSourceRetryCount by remember { mutableIntStateOf(0) }
    var startupSameSourceRefreshAttempted by remember { mutableStateOf(false) }
    var startupUrlLock by remember { mutableStateOf<String?>(null) }
    var dvStartupFallbackStage by remember { mutableIntStateOf(0) }
    var midPlaybackRecoveryAttempts by remember { mutableIntStateOf(0) }
    var blackVideoRecoveryStage by remember { mutableIntStateOf(0) }
    var blackVideoReadySinceMs by remember { mutableStateOf<Long?>(null) }
    var rebufferRecoverAttempted by remember { mutableStateOf(false) }
    var longRebufferCount by remember { mutableIntStateOf(0) }
    var autoAdvanceAttempts by remember { mutableIntStateOf(0) }
    var triedStreamIndexes by remember { mutableStateOf<Set<Int>>(emptySet()) }
    var isAutoAdvancing by remember { mutableStateOf(false) }
    var lastProgressReportSecond by remember { mutableLongStateOf(-1L) }
    val playerReleasedAtomic = remember { java.util.concurrent.atomic.AtomicBoolean(false) }
    var playerReleased by remember { mutableStateOf(false) }

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
    val directProgressiveFactory = remember(httpDataSourceFactory) {
        ProgressiveMediaSource.Factory(httpDataSourceFactory)
    }

    val hlsFactory = remember(httpDataSourceFactory) {
        HlsMediaSource.Factory(cacheDataSourceFactory).setAllowChunklessPreparation(true)
    }
    val dashFactory = remember(httpDataSourceFactory) {
        DashMediaSource.Factory(cacheDataSourceFactory)
    }
    val progressiveFactory = remember(httpDataSourceFactory) {
        ProgressiveMediaSource.Factory(cacheDataSourceFactory)
    }
    val mediaSourceFactory = remember(httpDataSourceFactory) {
        DefaultMediaSourceFactory(context).setDataSourceFactory(cacheDataSourceFactory)
    }

    val exoPlayer = remember {
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(15_000, 50_000, 500, 2_500)
            .setTargetBufferBytes(80 * 1024 * 1024)
            .setPrioritizeTimeOverSizeThresholds(false)
            .setBackBuffer(3_000, false)
            .build()

        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setRenderersFactory(
                DefaultRenderersFactory(context)
                    .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
                    .setEnableDecoderFallback(true)
            )
            .setLoadControl(loadControl)
            .setTrackSelector(
                androidx.media3.exoplayer.trackselection.DefaultTrackSelector(context).apply {
                    parameters = buildUponParameters()
                        .setPreferredAudioLanguage(uiState.preferredAudioLanguage)
                        .setAllowVideoMixedMimeTypeAdaptiveness(true)
                        .setAllowVideoNonSeamlessAdaptiveness(true)
                        .setAllowAudioMixedMimeTypeAdaptiveness(true)
                        .setExceedVideoConstraintsIfNecessary(true)
                        .setExceedAudioConstraintsIfNecessary(true)
                        .setExceedRendererCapabilitiesIfNecessary(true)
                        .build()
                }
            )
            .setAudioAttributes(
                androidx.media3.common.AudioAttributes.Builder()
                    .setUsage(androidx.media3.common.C.USAGE_MEDIA)
                    .setContentType(androidx.media3.common.C.AUDIO_CONTENT_TYPE_MOVIE)
                    .build(),
                true
            )
            .build().apply {
                volume = 1.0f
                addListener(object : Player.Listener {
                    override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                        if (playerReleasedAtomic.get()) return
                        if (hasPlaybackStarted) {
                            val isTransientError = error.errorCode in listOf(
                                androidx.media3.common.PlaybackException.ERROR_CODE_IO_UNSPECIFIED,
                                androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
                                androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT,
                                androidx.media3.common.PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS,
                                androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT,
                                androidx.media3.common.PlaybackException.ERROR_CODE_BEHIND_LIVE_WINDOW
                            )
                            if (isTransientError && midPlaybackRecoveryAttempts < 3) {
                                midPlaybackRecoveryAttempts++
                                val pos = currentPosition.coerceAtLeast(0L)
                                val wasPlaying = playWhenReady
                                if (midPlaybackRecoveryAttempts <= 1) seekTo(pos) else { stop(); prepare(); seekTo(pos) }
                                playWhenReady = wasPlaying
                                return
                            }
                        }
                        val isSourceError = error.errorCode in listOf(
                            androidx.media3.common.PlaybackException.ERROR_CODE_DECODER_INIT_FAILED,
                            androidx.media3.common.PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED,
                            androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FAILED,
                            androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES,
                            androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED,
                            androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED,
                            androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED,
                            androidx.media3.common.PlaybackException.ERROR_CODE_IO_UNSPECIFIED,
                            androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
                            androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT,
                            androidx.media3.common.PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS,
                            androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT
                        )
                        if (isSourceError) {
                            if (!hasPlaybackStarted && isLikelyDolbyVisionStream(latestUiState.selectedStream) && dvStartupFallbackStage < 2) {
                                val selector = this@apply.trackSelector as? androidx.media3.exoplayer.trackselection.DefaultTrackSelector
                                val preferredMime = if (dvStartupFallbackStage == 0) MimeTypes.VIDEO_H265 else MimeTypes.VIDEO_H264
                                selector?.let { it.parameters = it.buildUponParameters().setPreferredVideoMimeType(preferredMime).build() }
                                dvStartupFallbackStage += 1
                                val keepPlaying = this@apply.playWhenReady
                                this@apply.stop(); this@apply.prepare(); this@apply.playWhenReady = keepPlaying
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
                        val extractedAudioTracks = mutableListOf<AudioTrackInfo>()
                        var trackIndex = 0
                        tracks.groups.forEachIndexed { groupIndex, group ->
                            if (group.type == C.TRACK_TYPE_AUDIO) {
                                for (i in 0 until group.length) {
                                    val format = group.getTrackFormat(i)
                                    extractedAudioTracks.add(AudioTrackInfo(trackIndex, groupIndex, i, format.language, format.label, format.channelCount, format.sampleRate, format.sampleMimeType))
                                    trackIndex++
                                }
                            }
                        }
                        audioTracks = extractedAudioTracks
                        val currentAudioGroup = tracks.groups.find { it.type == C.TRACK_TYPE_AUDIO && it.isSelected }
                        currentAudioGroup?.let { group ->
                            val currentGroupIndex = tracks.groups.indexOf(group)
                            val selectedTrackIndex = (0 until group.length).firstOrNull { group.isTrackSelected(it) }
                            extractedAudioTracks.firstOrNull { it.groupIndex == currentGroupIndex && (selectedTrackIndex == null || it.trackIndex == selectedTrackIndex) }?.let {
                                selectedAudioIndex = extractedAudioTracks.indexOf(it)
                            }
                        }
                        val textTracks = mutableListOf<Subtitle>()
                        val subtitleByTrackId = latestUiState.subtitles.associateBy { subtitleTrackId(it) }
                        tracks.groups.forEachIndexed { groupIndex, group ->
                            if (group.type == C.TRACK_TYPE_TEXT) {
                                for (i in 0 until group.length) {
                                    val format = group.getTrackFormat(i)
                                    val formatTrackId = format.id?.trim().orEmpty()
                                    val matched = if (formatTrackId.isNotBlank()) subtitleByTrackId[formatTrackId] else latestUiState.subtitles.firstOrNull { !it.isEmbedded && it.label.equals(format.label, true) && it.lang.equals(format.language ?: it.lang, true) }
                                    val lang = format.language ?: matched?.lang ?: "und"
                                    val label = format.label ?: matched?.label ?: getFullLanguageName(lang)
                                    textTracks.add(Subtitle(matched?.id ?: formatTrackId.ifBlank { "embedded_${groupIndex}_$i" }, matched?.url.orEmpty(), lang, label, matched?.url.isNullOrBlank(), groupIndex, i))
                                }
                            }
                        }
                        viewModel.updatePlayerTextTracks(textTracks)
                    }
                })
            }
    }

    val queueControlsSeek: (Long) -> Unit = { deltaMs ->
        if (playerReleased) return@queueControlsSeek
        val basePosition = if (isControlScrubbing) scrubPreviewPosition else exoPlayer.currentPosition.coerceAtLeast(0L)
        val targetPosition = (basePosition + deltaMs).coerceIn(0L, if (duration > 0L) duration else Long.MAX_VALUE)
        scrubPreviewPosition = targetPosition
        isControlScrubbing = true
        controlsSeekJob?.cancel()
        controlsSeekJob = coroutineScope.launch {
            delay(260)
            if (!playerReleased) exoPlayer.seekTo(scrubPreviewPosition)
            isControlScrubbing = false
        }
    }

    val commitControlsSeekNow: () -> Unit = {
        if (playerReleased) return@commitControlsSeekNow
        if (isControlScrubbing) {
            controlsSeekJob?.cancel()
            exoPlayer.seekTo(scrubPreviewPosition)
            isControlScrubbing = false
        }
    }

    LaunchedEffect(uiState.selectedStreamUrl, uiState.streamSelectionNonce) {
        if (playerReleased) return@LaunchedEffect
        val url = uiState.selectedStreamUrl
        if (url != null) {
            if (startupUrlLock != url) {
                startupUrlLock = url
                startupRecoverAttempted = false; startupHardFailureReported = false; startupSameSourceRetryCount = 0; startupSameSourceRefreshAttempted = false; dvStartupFallbackStage = 0; blackVideoRecoveryStage = 0; blackVideoReadySinceMs = null
            }
            val streamHeaders = uiState.selectedStream?.behaviorHints?.proxyHeaders?.request.orEmpty().filterKeys { it.isNotBlank() }
            httpDataSourceFactory.setDefaultRequestProperties(baseRequestHeaders + streamHeaders)
            streamSelectedTime = System.currentTimeMillis()
            bufferingStartTime = null; hasPlaybackStarted = false; playbackIssueReported = false; rebufferRecoverAttempted = false; longRebufferCount = 0
            val subtitleConfigs = buildExternalSubtitleConfigurations(uiState.subtitles)
            val mediaItem = MediaItem.Builder().setUri(Uri.parse(url)).apply { if (subtitleConfigs.isNotEmpty()) setSubtitleConfigurations(subtitleConfigs) }.build()
            val mediaSource = when {
                url.lowercase().run { contains(".m3u8") || contains("/hls") || contains("format=hls") } -> hlsFactory.createMediaSource(mediaItem)
                url.lowercase().run { contains(".mpd") || contains("/dash") || contains("format=dash") } -> dashFactory.createMediaSource(mediaItem)
                isLikelyHeavyStream(latestUiState.selectedStream) -> directProgressiveFactory.createMediaSource(mediaItem)
                else -> mediaSourceFactory.createMediaSource(mediaItem)
            }
            runCatching { exoPlayer.playWhenReady = false; exoPlayer.stop(); exoPlayer.clearMediaItems() }
            if (uiState.savedPosition > 0L) exoPlayer.setMediaSource(mediaSource, uiState.savedPosition) else exoPlayer.setMediaSource(mediaSource)
            exoPlayer.playWhenReady = true; exoPlayer.prepare()
            val subtitle = uiState.selectedSubtitle
            exoPlayer.trackSelectionParameters = if (subtitle != null) {
                exoPlayer.trackSelectionParameters.buildUpon().clearOverridesOfType(C.TRACK_TYPE_TEXT).setPreferredTextLanguage(subtitle.lang).setSelectUndeterminedTextLanguage(true).setIgnoredTextSelectionFlags(0).setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false).build()
            } else {
                exoPlayer.trackSelectionParameters.buildUpon().clearOverridesOfType(C.TRACK_TYPE_TEXT).setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true).build()
            }
        }
    }

    LaunchedEffect(uiState.selectedSubtitle, uiState.subtitleSelectionNonce, uiState.subtitles) {
        if (playerReleased) return@LaunchedEffect
        val subtitle = uiState.selectedSubtitle
        val params = exoPlayer.trackSelectionParameters.buildUpon().clearOverridesOfType(C.TRACK_TYPE_TEXT)
        if (subtitle == null) { exoPlayer.trackSelectionParameters = params.setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true).build(); return@LaunchedEffect }
        val res = uiState.subtitles.firstOrNull { it.id == subtitle.id && it.groupIndex != null } ?: uiState.subtitles.firstOrNull { subtitle.url.isNotBlank() && it.url == subtitle.url && it.groupIndex != null } ?: subtitle
        val g = res.groupIndex; val t = res.trackIndex; val groups = exoPlayer.currentTracks.groups
        if (g != null && t != null && g in groups.indices && groups[g].type == C.TRACK_TYPE_TEXT) params.setOverrideForType(androidx.media3.common.TrackSelectionOverride(groups[g].mediaTrackGroup, t))
        exoPlayer.trackSelectionParameters = params.setPreferredTextLanguage(subtitle.lang).setSelectUndeterminedTextLanguage(true).setIgnoredTextSelectionFlags(0).setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false).build()
    }

    LaunchedEffect(exoPlayer) {
        while (!playerReleasedAtomic.get()) {
            currentPosition = runCatching { exoPlayer.currentPosition }.getOrDefault(currentPosition)
            viewModel.onPlaybackPosition(currentPosition)
            val rawDur = exoPlayer.duration; duration = if (rawDur > 0L && rawDur != C.TIME_UNSET) rawDur else 0L
            progress = if (duration > 0L) (currentPosition.toFloat() / duration.toFloat()).coerceIn(0f, 1f) else 0f
            isPlaying = exoPlayer.isPlaying; isBuffering = exoPlayer.playbackState == Player.STATE_BUFFERING
            if (isBuffering && hasPlaybackStarted) {
                if (bufferingStartTime == null) bufferingStartTime = System.currentTimeMillis() else {
                    if (System.currentTimeMillis() - (bufferingStartTime ?: 0L) > bufferingTimeoutMs) {
                        bufferingStartTime = null; longRebufferCount++; viewModel.onLongRebufferDetected()
                        if (!rebufferRecoverAttempted) { rebufferRecoverAttempted = true; exoPlayer.playWhenReady = true }
                    }
                }
            } else { bufferingStartTime = null; if (exoPlayer.isPlaying && exoPlayer.playbackState == Player.STATE_READY) longRebufferCount = 0 }
            if (uiState.selectedStreamUrl != null && !hasPlaybackStarted) {
                val stall = exoPlayer.playbackState == Player.STATE_BUFFERING || (exoPlayer.playbackState == Player.STATE_READY && !exoPlayer.isPlaying) || exoPlayer.playbackState == Player.STATE_IDLE
                if (stall && System.currentTimeMillis() - (streamSelectedTime ?: System.currentTimeMillis()) > initialBufferingTimeoutMs && !startupRecoverAttempted) {
                    startupRecoverAttempted = true; if (!tryAdvanceToNextStream()) exoPlayer.playWhenReady = true
                }
            }
            if (!hasPlaybackStarted && exoPlayer.playbackState == Player.STATE_READY && exoPlayer.isPlaying) {
                hasPlaybackStarted = true; midPlaybackRecoveryAttempts = 0
                val startMs = streamSelectedTime?.let { (System.currentTimeMillis() - it).coerceAtLeast(0L) } ?: 0L
                viewModel.onPlaybackStarted(startMs, startupSameSourceRetryCount, autoAdvanceAttempts)
            }
            if (currentPosition > 0 && duration > 0) {
                val curSec = currentPosition / 1000L
                if ((!exoPlayer.isPlaying && curSec != lastProgressReportSecond) || (exoPlayer.isPlaying && (lastProgressReportSecond < 0L || curSec - lastProgressReportSecond >= 3L))) {
                    lastProgressReportSecond = curSec; viewModel.saveProgress(currentPosition, duration, (currentPosition.toFloat() / duration.toFloat() * 100).toInt(), exoPlayer.isPlaying, exoPlayer.playbackState)
                }
            }
            if (exoPlayer.playbackState == Player.STATE_ENDED && mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null) {
                val sel = uiState.selectedStream; onPlayNext(seasonNumber, episodeNumber + 1, sel?.addonId, sel?.source, sel?.behaviorHints?.bingeGroup)
            }
            delay(if (!hasPlaybackStarted) 150L else 500L)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            controlsSeekJob?.cancel(); playerReleasedAtomic.set(true); playerReleased = true
            runCatching { val dur = exoPlayer.duration.takeIf { it > 0L && it != C.TIME_UNSET } ?: 0L; viewModel.saveProgress(exoPlayer.currentPosition, dur, if (dur > 0L) ((exoPlayer.currentPosition.toDouble() / dur.toDouble()) * 100.0).toInt().coerceIn(0, 100) else 0, exoPlayer.isPlaying, exoPlayer.playbackState) }
            runCatching { exoPlayer.release() }
        }
    }

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val aspectModeLabel = when (playerResizeMode) { AspectRatioFrameLayout.RESIZE_MODE_ZOOM -> "Zoom"; AspectRatioFrameLayout.RESIZE_MODE_FILL -> "Fill"; else -> "Fit" }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black).focusRequester(containerFocusRequester).focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    if (uiState.error != null) {
                        val maxB = if (uiState.isSetupError) 0 else 1
                        return@onKeyEvent when (event.key) {
                            Key.DirectionLeft -> { if (errorModalFocusIndex > 0) errorModalFocusIndex--; true }
                            Key.DirectionRight -> { if (errorModalFocusIndex < maxB) errorModalFocusIndex++; true }
                            Key.Enter, Key.DirectionCenter -> { if (uiState.isSetupError) onBack() else if (errorModalFocusIndex == 0) viewModel.retry() else onBack(); true }
                            Key.Back, Key.Escape -> { onBack(); true }
                            else -> false
                        }
                    }
                    if (showSubtitleMenu) return@onKeyEvent false
                    when (event.key) {
                        Key.Back, Key.Escape -> { onBack(); true }
                        Key.DirectionLeft -> { if (!showControls) { val now = System.currentTimeMillis(); if (now - lastSkipTime < 1200 && showSkipOverlay) skipAmount = (skipAmount - 10).coerceIn(-10000, 10000) else { skipStartPosition = exoPlayer.currentPosition; skipAmount = -10 }; lastSkipTime = now; val target = (skipStartPosition + (skipAmount * 1000L)).coerceIn(0L, if (duration > 0) duration else Long.MAX_VALUE); exoPlayer.seekTo(target); showSkipOverlay = true; true } else false }
                        Key.DirectionRight -> { if (!showControls) { val now = System.currentTimeMillis(); if (now - lastSkipTime < 1200 && showSkipOverlay) skipAmount = (skipAmount + 10).coerceIn(-10000, 10000) else { skipStartPosition = exoPlayer.currentPosition; skipAmount = 10 }; lastSkipTime = now; val target = (skipStartPosition + (skipAmount * 1000L)).coerceIn(0L, if (duration > 0) duration else Long.MAX_VALUE); exoPlayer.seekTo(target); showSkipOverlay = true; true } else false }
                        Key.VolumeUp -> { adjustVolume(1); true }
                        Key.VolumeDown -> { adjustVolume(-1); true }
                        Key.DirectionUp, Key.DirectionDown -> { if (!showControls) { if (uiState.activeSkipInterval != null && !uiState.skipIntervalDismissed && event.key == Key.DirectionUp) coroutineScope.launch { delay(40); runCatching { skipIntroFocusRequester.requestFocus() } } else showControls = true; true } else false }
                        Key.Enter, Key.DirectionCenter -> { if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play(); if (!showControls) showControls = true; true }
                        Key.Spacebar -> { if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play(); showControls = true; true }
                        else -> { if (!showControls) { showControls = true; true } else false }
                    }
                } else false
            }
    ) {
        if (uiState.selectedStreamUrl != null) {
            AndroidView(factory = { ctx -> PlayerView(ctx).apply { player = exoPlayer; useController = false; setKeepContentOnPlayerReset(true); resizeMode = playerResizeMode; subtitleView?.apply { setStyle(androidx.media3.ui.CaptionStyleCompat(android.graphics.Color.WHITE, android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT, androidx.media3.ui.CaptionStyleCompat.EDGE_TYPE_OUTLINE, android.graphics.Color.BLACK, android.graphics.Typeface.DEFAULT_BOLD)); setApplyEmbeddedStyles(false); setApplyEmbeddedFontSizes(false); setFixedTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 24f); setBottomPaddingFraction(0.08f) } } }, update = { it.player = exoPlayer; it.resizeMode = playerResizeMode }, modifier = Modifier.fillMaxSize())
        }

        // --- LOADING SCREEN OVERLAY (PULSING LOGO) ---
        if (uiState.isLoading || uiState.selectedStreamUrl == null || !hasPlaybackStarted) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (uiState.backdropUrl != null) {
                    AsyncImage(model = uiState.backdropUrl, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.75f)))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    PulsingLogo(logoUrl = uiState.logoUrl, title = uiState.title)
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = when { uiState.isLoadingSubtitles -> "Fetching subtitles..."; uiState.isLoadingStreams -> "Finding the best streams..."; uiState.selectedStreamUrl != null && !hasPlaybackStarted -> "Preparing playback..."; else -> "Loading..." }, style = ArflixTypography.body.copy(fontSize = 14.sp), color = TextSecondary.copy(alpha = 0.6f))
                }
            }
        }

        // --- BUFFERING INDICATOR (PULSING LOGO OVER VIDEO) ---
        if (isBuffering && hasPlaybackStarted && uiState.selectedStreamUrl != null) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)), contentAlignment = Alignment.Center) {
                PulsingLogo(logoUrl = uiState.logoUrl, title = uiState.title)
            }
        }

        SkipIntroButton(interval = uiState.activeSkipInterval, dismissed = uiState.skipIntervalDismissed, controlsVisible = showControls, onSkip = { exoPlayer.seekTo((uiState.activeSkipInterval!!.endMs + 500L).coerceAtLeast(0L)); viewModel.dismissSkipInterval() }, focusRequester = skipIntroFocusRequester, modifier = Modifier.align(Alignment.BottomEnd).zIndex(5f).padding(end = if (isTouchDevice) 24.dp else 48.dp, bottom = if (showControls) 90.dp else 32.dp))

        // --- CONTROLS OVERLAY ---
        AnimatedVisibility(visible = showControls && !showSubtitleMenu && !showSourceMenu, enter = fadeIn(), exit = fadeOut()) {
            Box(modifier = Modifier.fillMaxSize()) {
                // --- TOP INFO ROW (Clock + Ends At) ---
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        if (!uiState.logoUrl.isNullOrBlank()) {
                            AsyncImage(model = uiState.logoUrl, contentDescription = uiState.title, alignment = Alignment.CenterStart, contentScale = ContentScale.Fit, modifier = Modifier.height(32.dp).width(240.dp))
                        } else {
                            Text(text = uiState.title, style = ArflixTypography.sectionTitle.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold), color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                        if (seasonNumber != null && episodeNumber != null) {
                            Text(text = "S$seasonNumber E$episodeNumber", style = ArflixTypography.body.copy(fontSize = 16.sp), color = TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 6.dp))
                        }
                        uiState.selectedStream?.let { stream ->
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                                Text(text = stream.quality, style = ArflixTypography.caption.copy(fontSize = 12.sp), color = Pink, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                stream.sizeBytes?.let { Text(text = "•", style = ArflixTypography.caption, color = TextSecondary.copy(alpha = 0.5f)); Text(text = formatFileSize(it), style = ArflixTypography.caption.copy(fontSize = 12.sp), color = TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis) }
                            }
                        }
                    }

                    // --- UPDATED CLOCK + ENDS AT ---
                    val clockTime = remember { mutableStateOf("") }
                    val endsAtTimeValue = remember { mutableStateOf("") }
                    LaunchedEffect(Unit) {
                        while (true) {
                            val now = System.currentTimeMillis()
                            clockTime.value = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault()).format(java.util.Date(now))
                            val remainingMs = (duration - currentPosition).coerceAtLeast(0)
                            if (remainingMs > 0 && duration > 0) {
                                endsAtTimeValue.value = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault()).format(java.util.Date(now + remainingMs))
                            } else {
                                endsAtTimeValue.value = ""
                            }
                            delay(1000)
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = clockTime.value,
                            style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                            color = TextSecondary,
                            maxLines = 1
                        )
                        if (endsAtTimeValue.value.isNotBlank()) {
                            Text(
                                text = "Ends at ${endsAtTimeValue.value}",
                                style = ArflixTypography.caption.copy(fontSize = 13.sp),
                                color = TextSecondary.copy(alpha = 0.6f),
                                maxLines = 1
                            )
                        }
                    }
                }

                // Bottom controls area...
                Column(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).background(Brush.verticalGradient(colorStops = arrayOf(0.0f to Color.Transparent, 0.3f to Color.Black.copy(alpha = 0.2f), 1.0f to Color.Black.copy(alpha = 0.7f)))).padding(horizontal = if (isTouchDevice) 24.dp else 48.dp, vertical = if (isTouchDevice) 16.dp else 24.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                        val small = if (isTouchDevice) 24.dp else 28.dp; val si = if (isTouchDevice) 17.dp else 19.dp; val mid = if (isTouchDevice) 28.dp else 30.dp; val mi = if (isTouchDevice) 20.dp else 22.dp; val big = if (isTouchDevice) 34.dp else 38.dp; val bi = if (isTouchDevice) 26.dp else 28.dp; val gap = if (isTouchDevice) 10.dp else 14.dp
                        PlayerIconButton(Icons.Default.ClosedCaption, "Subs", subtitleButtonFocusRequester, small, si, { if (it) focusedButton = 1 }, { showSubtitleMenu = true }, { if (mediaType == MediaType.TV) nextEpisodeButtonFocusRequester.requestFocus() else aspectButtonFocusRequester.requestFocus() }, { sourceButtonFocusRequester.requestFocus() }, onDownKey = { trackbarFocusRequester.requestFocus() })
                        Spacer(Modifier.width(gap))
                        PlayerIconButton(Icons.Default.Folder, "Source", sourceButtonFocusRequester, small, si, {}, { showSourceMenu = true }, { subtitleButtonFocusRequester.requestFocus() }, { rewindButtonFocusRequester.requestFocus() }, onDownKey = { trackbarFocusRequester.requestFocus() })
                        Spacer(Modifier.width(if (isTouchDevice) 14.dp else 18.dp))
                        PlayerIconButton(Icons.Default.Replay10, "-10", rewindButtonFocusRequester, mid, mi, {}, { queueControlsSeek(-10_000L) }, { sourceButtonFocusRequester.requestFocus() }, { playButtonFocusRequester.requestFocus() }, onDownKey = { trackbarFocusRequester.requestFocus() })
                        Spacer(Modifier.width(gap))
                        PlayerIconButton(if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow, if (isPlaying) "Pause" else "Play", playButtonFocusRequester, big, bi, { if (it) focusedButton = 0 }, { if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play() }, { rewindButtonFocusRequester.requestFocus() }, { forwardButtonFocusRequester.requestFocus() }, onDownKey = { trackbarFocusRequester.requestFocus() }, onUpKey = { if (uiState.activeSkipInterval != null && !uiState.skipIntervalDismissed) skipIntroFocusRequester.requestFocus() })
                        Spacer(Modifier.width(gap))
                        PlayerIconButton(Icons.Default.Forward10, "+10", forwardButtonFocusRequester, mid, mi, {}, { queueControlsSeek(10_000L) }, { playButtonFocusRequester.requestFocus() }, { aspectButtonFocusRequester.requestFocus() }, onDownKey = { trackbarFocusRequester.requestFocus() })
                        Spacer(Modifier.width(if (isTouchDevice) 14.dp else 18.dp))
                        PlayerIconButton(Icons.Default.AspectRatio, "Aspect", aspectButtonFocusRequester, small, si, {}, cycleAspectRatio, { forwardButtonFocusRequester.requestFocus() }, { if (mediaType == MediaType.TV) nextEpisodeButtonFocusRequester.requestFocus() else subtitleButtonFocusRequester.requestFocus() }, onDownKey = { trackbarFocusRequester.requestFocus() })
                        if (mediaType == MediaType.TV) {
                            Spacer(Modifier.width(gap))
                            PlayerIconButton(Icons.Default.SkipNext, "Next", nextEpisodeButtonFocusRequester, small, si, {}, { val s = seasonNumber ?: return@PlayerIconButton; val e = episodeNumber ?: return@PlayerIconButton; val sel = uiState.selectedStream; onPlayNext(s, e + 1, sel?.addonId, sel?.source, sel?.behaviorHints?.bingeGroup) }, { aspectButtonFocusRequester.requestFocus() }, { subtitleButtonFocusRequester.requestFocus() }, onDownKey = { trackbarFocusRequester.requestFocus() })
                        }
                    }
                    Spacer(Modifier.height(if (isTouchDevice) 4.dp else 6.dp))
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = formatTime(if (isControlScrubbing) scrubPreviewPosition else currentPosition), style = ArflixTypography.label.copy(fontSize = if (isTouchDevice) 12.sp else 13.sp), color = Color.White.copy(alpha = 0.9f), modifier = Modifier.width(if (isTouchDevice) 48.dp else 55.dp))
                        var trackbarFocused by remember { mutableStateOf(false) }
                        val tbh by animateFloatAsState(if (trackbarFocused) 8f else 4f, label = "tbh"); var tbwPx by remember { mutableIntStateOf(0) }
                        Box(Modifier.weight(1f).height(tbh.dp).onSizeChanged { tbwPx = it.width }.focusRequester(trackbarFocusRequester).onFocusChanged { trackbarFocused = it.isFocused; if (!it.isFocused && isControlScrubbing) commitControlsSeekNow() }.focusable().pointerInput(duration) { detectHorizontalDragGestures(onDragStart = { if (duration > 0L && tbwPx > 0) { scrubPreviewPosition = ((it.x / tbwPx).coerceIn(0f, 1f) * duration).toLong(); isControlScrubbing = true } }, onDragEnd = { if (isControlScrubbing && !playerReleased) { exoPlayer.seekTo(scrubPreviewPosition); isControlScrubbing = false } }, onDragCancel = { if (isControlScrubbing && !playerReleased) { exoPlayer.seekTo(scrubPreviewPosition); isControlScrubbing = false } }, onHorizontalDrag = { _, d -> if (duration > 0L && tbwPx > 0) { scrubPreviewPosition = (scrubPreviewPosition + (d / tbwPx * duration).toLong()).coerceIn(0L, duration); isControlScrubbing = true } }) }.pointerInput(duration) { detectTapGestures { if (duration > 0L && tbwPx > 0 && !playerReleased) exoPlayer.seekTo(((it.x / tbwPx).coerceIn(0f, 1f) * duration).toLong()) } }.onKeyEvent { if (it.type == KeyEventType.KeyDown && trackbarFocused) { when (it.key) { Key.DirectionLeft -> { queueControlsSeek(-10_000L); true }; Key.DirectionRight -> { queueControlsSeek(10_000L); true }; Key.Enter, Key.DirectionCenter -> { commitControlsSeekNow(); true }; Key.DirectionUp -> { playButtonFocusRequester.requestFocus(); true }; else -> false } } else false }.background(Color.White.copy(alpha = if (trackbarFocused) 0.25f else 0.15f), RoundedCornerShape(3.dp)), contentAlignment = Alignment.CenterStart) {
                            val fr = if (duration > 0) ((if (isControlScrubbing) scrubPreviewPosition else currentPosition).toFloat() / duration.toFloat()).coerceIn(0f, 1f) else progress
                            Box(Modifier.fillMaxWidth(fr).fillMaxHeight().background(if (trackbarFocused) Pink else Pink.copy(alpha = 0.8f), RoundedCornerShape(3.dp)))
                        }
                        Spacer(Modifier.width(8.dp)); Text(text = formatTime(duration), style = ArflixTypography.label.copy(fontSize = if (isTouchDevice) 12.sp else 13.sp), color = Color.White.copy(alpha = 0.5f), modifier = Modifier.width(if (isTouchDevice) 48.dp else 55.dp))
                    }
                }
            }
        }

        // Subtitle/Audio/Source menus and other overlays...
        AnimatedVisibility(visible = showSubtitleMenu, enter = fadeIn(), exit = fadeOut()) {
            SubtitleMenu(subtitles = uiState.subtitles, selectedSubtitle = uiState.selectedSubtitle, audioTracks = audioTracks, selectedAudioIndex = selectedAudioIndex, activeTab = subtitleMenuTab, focusedIndex = subtitleMenuIndex, onTabChanged = { subtitleMenuTab = it; subtitleMenuIndex = 0 }, onSelectSubtitle = { if (it == 0) viewModel.disableSubtitles() else uiState.subtitles.getOrNull(it - 1)?.let { s -> viewModel.selectSubtitle(s) }; showSubtitleMenu = false; showControls = true; coroutineScope.launch { delay(150); runCatching { subtitleButtonFocusRequester.requestFocus() } } }, onSelectAudio = { t -> val p = exoPlayer.trackSelectionParameters.buildUpon().setPreferredAudioLanguage(t.language); val tg = exoPlayer.currentTracks.groups; if (t.groupIndex < tg.size && tg[t.groupIndex].type == C.TRACK_TYPE_AUDIO) p.setOverrideForType(androidx.media3.common.TrackSelectionOverride(tg[t.groupIndex].mediaTrackGroup, t.trackIndex)); exoPlayer.trackSelectionParameters = p.build(); selectedAudioIndex = audioTracks.indexOfFirst { it.groupIndex == t.groupIndex && it.trackIndex == t.trackIndex }.takeIf { it >= 0 } ?: t.index; showSubtitleMenu = false; showControls = true; coroutineScope.launch { delay(150); runCatching { subtitleButtonFocusRequester.requestFocus() } } }, onClose = { showSubtitleMenu = false; showControls = true; coroutineScope.launch { delay(150); runCatching { subtitleButtonFocusRequester.requestFocus() } } })
        }

        StreamSelector(isVisible = showSourceMenu, streams = uiState.streams, selectedStream = uiState.selectedStream, isLoading = uiState.isLoadingStreams, hasStreamingAddons = !uiState.isSetupError, title = uiState.title, subtitle = if (seasonNumber != null) "S$seasonNumber E$episodeNumber" else "", onSelect = { userSelectedSourceManually = true; viewModel.selectStream(it); showSourceMenu = false; showControls = true; coroutineScope.launch { delay(150); runCatching { sourceButtonFocusRequester.requestFocus() } } }, onClose = { showSourceMenu = false; showControls = true; coroutineScope.launch { delay(150); runCatching { sourceButtonFocusRequester.requestFocus() } } })

        // Aspect, Volume, Skip, Error overlays...
        AnimatedVisibility(visible = showVolumeIndicator, enter = fadeIn(), exit = fadeOut(), modifier = Modifier.align(Alignment.CenterEnd).padding(end = 48.dp)) { Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(12.dp)).padding(16.dp)) { Icon(if (isMuted || currentVolume == 0) Icons.Default.VolumeMute else if (currentVolume < maxVolume/2) Icons.Default.VolumeDown else Icons.Default.VolumeUp, "Vol", tint = Color.White, modifier = Modifier.size(32.dp)); Spacer(Modifier.height(8.dp)); Box(Modifier.width(8.dp).height(100.dp).background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(4.dp))) { Box(Modifier.fillMaxWidth().fillMaxSize((currentVolume.toFloat()/maxVolume).coerceIn(0f, 1f)).background(Pink, RoundedCornerShape(4.dp)).align(Alignment.BottomCenter)) }; Spacer(Modifier.height(8.dp)); Text(if (isMuted) "Muted" else "${currentVolume*100/maxVolume}%", style = ArflixTypography.caption, color = Color.White) } }
        AnimatedVisibility(visible = showAspectIndicator, enter = fadeIn(), exit = fadeOut(), modifier = Modifier.align(Alignment.Center)) { Box(Modifier.background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(10.dp)).padding(horizontal = 24.dp, vertical = 14.dp)) { Text(aspectModeLabel, style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium), color = Color.White) } }
        AnimatedVisibility(visible = showSkipOverlay, enter = fadeIn(), exit = fadeOut(), modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 120.dp)) { Text(text = if (skipAmount >= 0) "+${skipAmount}s" else "${skipAmount}s", style = ArflixTypography.sectionTitle.copy(fontSize = 28.sp, fontWeight = FontWeight.Bold, shadow = Shadow(Color.Black, Offset(2f, 2f), 8f)), color = Color.White) }

        AnimatedVisibility(visible = uiState.error != null, enter = fadeIn(), exit = fadeOut()) { val isS = uiState.isSetupError; val acc = if (isS) Color(0xFF3B82F6) else Color(0xFFEF4444); Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.9f)), Alignment.Center) { Column(Modifier.width(480.dp).background(Color(0xFF1A1A1A), RoundedCornerShape(16.dp)).border(1.dp, acc.copy(alpha = 0.3f), RoundedCornerShape(16.dp)).padding(32.dp), Alignment.CenterHorizontally) { Box(Modifier.size(72.dp).background(acc.copy(alpha = 0.15f), CircleShape), Alignment.Center) { Icon(if (isS) Icons.Default.Settings else Icons.Default.ErrorOutline, "Err", tint = acc, modifier = Modifier.size(40.dp)) }; Spacer(Modifier.height(24.dp)); Text(if (isS) "Addon Setup Required" else "Playback Error", style = ArflixTypography.sectionTitle, color = TextPrimary); Spacer(Modifier.height(12.dp)); Text(uiState.error ?: "Unknown error", style = ArflixTypography.body, color = TextSecondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.fillMaxWidth()); if (isS) { Spacer(Modifier.height(16.dp)); Text("ARVIO uses community streaming addons to find video sources.", style = ArflixTypography.caption, color = TextSecondary.copy(alpha = 0.7f), textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.fillMaxWidth()) }; Spacer(Modifier.height(32.dp)); Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) { if (!isS) ErrorButton("TRY AGAIN", Icons.Default.Refresh, errorModalFocusIndex == 0, true, { viewModel.retry() }); ErrorButton("GO BACK", null, if (isS) errorModalFocusIndex == 0 else errorModalFocusIndex == 1, isS, onBack) } } } }
    }
}

/**
 * PULSING LOGO COMPONENT
 */
@Composable
private fun PulsingLogo(
    logoUrl: String?,
    title: String,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!logoUrl.isNullOrBlank()) {
            AsyncImage(
                model = logoUrl,
                contentDescription = title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(360.dp)
                    .height(180.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            )
        } else {
            Text(
                text = title,
                style = ArflixTypography.sectionTitle.copy(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(Color.Black, Offset(2f, 2f), 8f)
                ),
                color = Color.White,
                modifier = Modifier.graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
            )
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
    val scale by animateFloatAsState(if (focused) 1.35f else 1f, label = "iconScale")
    val iconAlpha by animateFloatAsState(if (focused) 1f else 0.6f, label = "iconAlpha")

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
            .graphicsLayer { scaleX = scale; scaleY = scale },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription, tint = Color.White.copy(alpha = iconAlpha), modifier = Modifier.size(iconSize))
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ErrorButton(text: String, icon: ImageVector? = null, isFocused: Boolean, isPrimary: Boolean, onClick: () -> Unit) {
    val scale by animateFloatAsState(if (isFocused) 1.05f else 1f)
    Box(modifier = Modifier.focusable().clickable { onClick() }.graphicsLayer { scaleX = scale; scaleY = scale }.background(when { isFocused -> Color.White; isPrimary -> Color.White.copy(alpha = 0.1f); else -> Color.Transparent }, RoundedCornerShape(8.dp)).border(width = 1.dp, color = when { isFocused -> Color.White; isPrimary -> Pink.copy(alpha = 0.5f); else -> Color.White.copy(alpha = 0.3f) }, shape = RoundedCornerShape(8.dp)).padding(horizontal = 24.dp, vertical = 12.dp)) { Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) { icon?.let { Icon(imageVector = it, contentDescription = null, tint = if (isFocused) Color.Black else if (isPrimary) Pink else TextSecondary, modifier = Modifier.size(18.dp)) }; Text(text = text, style = ArflixTypography.button, color = if (isFocused) Color.Black else if (isPrimary) Pink else TextSecondary) } }
}

data class AudioTrackInfo(val index: Int, val groupIndex: Int, val trackIndex: Int, val language: String?, val label: String?, val channelCount: Int, val sampleRate: Int, val codec: String?)

private fun getFullLanguageName(code: String?): String {
    if (code == null) return "Unknown"
    val n = code.lowercase().trim()
    return when {
        n == "en" || n == "eng" || n == "english" -> "English"
        n == "es" || n == "spa" || n == "spanish" -> "Spanish"
        n == "fr" || n == "fra" || n == "french" -> "French"
        n == "de" || n == "ger" || n == "german" -> "German"
        n == "it" || n == "ita" || n == "italian" -> "Italian"
        n == "pt" || n == "por" -> "Portuguese"
        n == "ru" || n == "rus" -> "Russian"
        n == "ja" || n == "jpn" -> "Japanese"
        n == "ko" || n == "kor" -> "Korean"
        n == "zh" || n == "chi" -> "Chinese"
        else -> n.uppercase()
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SubtitleMenu(subtitles: List<Subtitle>, selectedSubtitle: Subtitle?, audioTracks: List<AudioTrackInfo>, selectedAudioIndex: Int, activeTab: Int, focusedIndex: Int, onTabChanged: (Int) -> Unit, onSelectSubtitle: (Int) -> Unit, onSelectAudio: (AudioTrackInfo) -> Unit, onClose: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)).clickable { onClose() }, contentAlignment = Alignment.CenterEnd) {
        Column(modifier = Modifier.width(320.dp).padding(end = 32.dp).background(Color.Black.copy(alpha = 0.85f), RoundedCornerShape(16.dp)).border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp)).padding(16.dp).clickable(enabled = false) {}) {
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TabButton(text = "Subtitles", isSelected = activeTab == 0, onClick = { onTabChanged(0) })
                TabButton(text = "Audio", isSelected = activeTab == 1, onClick = { onTabChanged(1) })
            }
            Box(modifier = Modifier.height(300.dp)) {
                if (activeTab == 0) {
                    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        item { TrackMenuItem("Off", null, selectedSubtitle == null, focusedIndex == 0, { onSelectSubtitle(0) }) }
                        itemsIndexed(subtitles) { idx, s -> TrackMenuItem(s.label.ifBlank { s.lang }, getFullLanguageName(s.lang), selectedSubtitle?.id == s.id, focusedIndex == idx + 1, { onSelectSubtitle(idx + 1) }) }
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        itemsIndexed(audioTracks) { idx, t -> TrackMenuItem(t.label ?: getFullLanguageName(t.language), detectAudioCodecLabel(t.codec, t.label), idx == selectedAudioIndex, focusedIndex == idx, { onSelectAudio(t) }) }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TabButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(modifier = Modifier.clickable { onClick() }.background(if (isSelected) Color.White.copy(alpha = 0.2f) else Color.Transparent, RoundedCornerShape(20.dp)).then(if (isSelected) Modifier.border(1.dp, Color.White, RoundedCornerShape(20.dp)) else Modifier).padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = text, style = ArflixTypography.body.copy(fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal, fontSize = 14.sp), color = Color.White)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TrackMenuItem(label: String, subtitle: String?, isSelected: Boolean, isFocused: Boolean, onClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable { onClick() }.background(if (isFocused) Color.White else Color.Transparent, RoundedCornerShape(8.dp)).padding(horizontal = 12.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, style = ArflixTypography.body.copy(fontSize = 14.sp), color = if (isFocused) Color.Black else Color.White)
            if (subtitle != null) Text(text = subtitle, style = ArflixTypography.caption.copy(fontSize = 11.sp), color = if (isFocused) Color.Black.copy(alpha = 0.7f) else Color.White.copy(alpha = 0.6f))
        }
        if (isSelected) Icon(Icons.Default.Check, "Selected", tint = if (isFocused) Color.Black else Color.White, modifier = Modifier.size(18.dp))
    }
}

private fun formatTime(ms: Long): String {
    if (ms <= 0) return "00:00"
    val h = TimeUnit.MILLISECONDS.toHours(ms); val m = TimeUnit.MILLISECONDS.toMinutes(ms) % 60; val s = TimeUnit.MILLISECONDS.toSeconds(ms) % 60
    return if (h > 0) String.format("%d:%02d:%02d", h, m, s) else String.format("%02d:%02d", m, s)
}

private fun formatFileSize(bytes: Long): String = when { bytes >= 1073741824 -> String.format("%.1f GB", bytes / 1073741824.0); bytes >= 1048576 -> String.format("%.0f MB", bytes / 1048576.0); else -> String.format("%.0f KB", bytes / 1024.0) }

private fun detectAudioCodecLabel(codec: String?, label: String?): String? {
    val h = "${codec} ${label}".lowercase()
    return when { h.contains("dts-hd") || h.contains("dtshd") -> "DTS-HD"; h.contains("truehd") && h.contains("atmos") -> "Atmos"; h.contains("truehd") -> "TrueHD"; h.contains("eac3") || h.contains("dd+") -> "E-AC3"; h.contains("ac3") || h.contains("dd ") -> "AC3"; h.contains("dts") -> "DTS"; h.contains("aac") -> "AAC"; else -> null }
}

private fun subtitleTrackId(s: Subtitle): String = if (s.id.isNotBlank()) s.id else "ext_${(s.url + s.lang).hashCode().toUInt().toString(16)}"

private fun buildExternalSubtitleConfigurations(subs: List<Subtitle>): List<MediaItem.SubtitleConfiguration> = subs.filter { !it.isEmbedded }.mapNotNull { s -> runCatching { MediaItem.SubtitleConfiguration.Builder(Uri.parse(s.url)).setId(subtitleTrackId(s)).setMimeType(if (s.url.contains(".vtt")) MimeTypes.TEXT_VTT else MimeTypes.APPLICATION_SUBRIP).setLanguage(s.lang).setLabel(s.label).setRoleFlags(C.ROLE_FLAG_SUBTITLE).build() }.getOrNull() }.distinctBy { it.id }.toList()

private fun estimateInitialStartupTimeoutMs(s: StreamSource?, m: Boolean): Long {
    var t = if (m) 40_000L else 18_000L; if (s == null) return t
    val h = "${s.quality} ${s.source}".lowercase()
    if (h.contains("4k") || h.contains("2160") || h.contains("remux")) t = t.coerceAtLeast(70_000L)
    return t.coerceAtMost(120_000L)
}

private fun playbackErrorMessageFor(e: androidx.media3.common.PlaybackException, s: Boolean): String {
    val r = when (e.errorCode) { androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT, androidx.media3.common.PlaybackException.ERROR_CODE_TIMEOUT -> "Network timeout"; androidx.media3.common.PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED -> "Unsupported format"; else -> "Playback error" }
    return if (s) "$r. Try another source." else "$r during startup."
}

private fun isLikelyHeavyStream(s: StreamSource?): Boolean {
    if (s == null) return false
    val h = "${s.quality} ${s.source}".lowercase()
    return h.contains("4k") || h.contains("2160") || h.contains("remux")
}

private fun isLikelyDolbyVisionStream(s: StreamSource?): Boolean {
    if (s == null) return false
    val h = "${s.quality} ${s.source} ${s.behaviorHints?.filename}".lowercase()
    return h.contains("dolby vision") || h.contains(" dovi") || h.contains(" dv ")
}

private object PlaybackCacheSingleton {
    @Volatile private var i: SimpleCache? = null
    fun getInstance(c: Context): SimpleCache = i ?: synchronized(this) { i ?: SimpleCache(java.io.File(c.applicationContext.cacheDir, "media3_playback_cache"), LeastRecentlyUsedCacheEvictor(256L * 1024 * 1024), StandaloneDatabaseProvider(c.applicationContext)).also { i = it } }
}

private class PlaybackCookieJar : CookieJar {
    private val c = ConcurrentHashMap<String, MutableList<Cookie>>()
    override fun saveFromResponse(u: HttpUrl, cookies: List<Cookie>) { if (cookies.isNotEmpty()) c[u.host] = cookies.toMutableList() }
    override fun loadForRequest(u: HttpUrl): List<Cookie> = c[u.host] ?: emptyList()
}

/**
 * PULSING LOGO COMPONENT
 */
@Composable
private fun PulsingLogo(
    logoUrl: String?,
    title: String,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!logoUrl.isNullOrBlank()) {
            AsyncImage(
                model = logoUrl,
                contentDescription = title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(360.dp)
                    .height(180.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            )
        } else {
            Text(
                text = title,
                style = ArflixTypography.sectionTitle.copy(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(Color.Black, Offset(2f, 2f), 8f)
                ),
                color = Color.White,
                modifier = Modifier.graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
            )
        }
    }
}