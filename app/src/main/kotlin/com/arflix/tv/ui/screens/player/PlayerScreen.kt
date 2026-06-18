@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.player

import android.app.Activity
import android.app.ActivityManager
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
import androidx.compose.animation.core.keyframes
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
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
import androidx.compose.material.icons.filled.Tune
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
import androidx.compose.ui.platform.LocalDensity
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
import androidx.media3.common.TrackSelectionOverride
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
import com.arflix.tv.ArflixApplication
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.components.KeepScreenOn
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType
import com.arflix.tv.ui.components.NextEpisodeOverlay
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.components.WaveLoadingDots
import androidx.compose.ui.text.style.TextOverflow
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.weightedSubtitleScore
import com.arflix.tv.ui.skin.LocalAccentColorOverride
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.PurpleDark
import com.arflix.tv.ui.theme.PurpleLight
import com.arflix.tv.ui.theme.PurplePrimary
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import androidx.compose.runtime.rememberCoroutineScope
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.dash.DashMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.cast.CastManager
import com.arflix.tv.cast.CastManagerEntryPoint
import dagger.hilt.android.EntryPointAccessors
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.CastConnected
import androidx.compose.material.icons.filled.PictureInPicture
import androidx.mediarouter.app.MediaRouteChooserDialog
import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon as DrawableIcon
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Canvas as ComposeCanvas
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.content.ContextCompat

private const val PIP_ACTION_REWIND = "com.arflix.tv.pip.REWIND"
private const val PIP_ACTION_PLAY_PAUSE = "com.arflix.tv.pip.PLAY_PAUSE"
private const val PIP_ACTION_FORWARD = "com.arflix.tv.pip.FORWARD"

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
    val playerAccent = LocalAccentColorOverride.current ?: Color.White
    val context = LocalContext.current
    val activity = remember(context) { context.findActivity() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val latestUiState by rememberUpdatedState(uiState)
    val clockFormat = rememberPlayerClockFormat()
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val deviceType = LocalDeviceType.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val castManager = remember(context) {
        EntryPointAccessors.fromApplication(context.applicationContext, CastManagerEntryPoint::class.java).castManager()
    }
    val castState by castManager.castState.collectAsStateWithLifecycle()
    val isCasting = castState is CastManager.CastState.Casting
    val castAvailable = castState !is CastManager.CastState.NotAvailable
    // Hide cast button for streams that require custom request headers (Authorization, Referer, etc.)
    // since the Chromecast default receiver fetches the URL directly without those headers.
    val streamNeedsHeaders = uiState.selectedStream
        ?.behaviorHints?.proxyHeaders?.request?.isNotEmpty() == true
    val playbackActivityManager = remember(context) {
        context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
    }
    val playbackMemoryClassMb = remember(playbackActivityManager) {
        playbackActivityManager?.memoryClass ?: 384
    }
    val isLowRamPlaybackDevice = remember(playbackActivityManager) {
        playbackActivityManager?.isLowRamDevice == true
    }
    val isConstrainedPlaybackDevice = remember(deviceType, isLowRamPlaybackDevice, playbackMemoryClassMb) {
        deviceType == com.arflix.tv.util.DeviceType.TV &&
            (isLowRamPlaybackDevice || playbackMemoryClassMb <= 384)
    }
    val playbackBufferProfile = remember(isLowRamPlaybackDevice, playbackMemoryClassMb, deviceType) {
        buildPlaybackBufferProfile(
            memoryClassMb = playbackMemoryClassMb,
            isLowRamDevice = isLowRamPlaybackDevice,
            isTvDevice = deviceType == com.arflix.tv.util.DeviceType.TV
        )
    }
    val preferExtensionDecoder = remember(deviceType) {
        deviceType == com.arflix.tv.util.DeviceType.TV &&
            (
                Build.HARDWARE.contains("amlogic", ignoreCase = true) ||
                    Build.MANUFACTURER.contains("sei", ignoreCase = true) ||
                    Build.MODEL.contains("Box R", ignoreCase = true)
                )
    }
    val allowVideoExceedCodecCapabilities = remember(deviceType, preferExtensionDecoder) {
        !preferExtensionDecoder && !deviceType.isTouchDevice()
    }
    val allowAudioExceedCodecCapabilities = remember(preferExtensionDecoder) {
        !preferExtensionDecoder
    }
    val allowRendererExceedCodecCapabilities = remember(deviceType, preferExtensionDecoder) {
        !preferExtensionDecoder && !deviceType.isTouchDevice()
    }

    // Keep playback in landscape while the player is visible, regardless of the
    // device's auto-rotate lock. Restore the app's prior orientation afterward.
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

    // Initialize Cast SDK once on mobile entry. No-op on TV (CastState.NotAvailable).
    DisposableEffect(deviceType) {
        castManager.initialize(isMobile = deviceType.isTouchDevice())
        onDispose { }
    }

    KeepScreenOn()
    var isPlaying by remember { mutableStateOf(false) }
    var isBuffering by remember { mutableStateOf(true) }
    var hasPlaybackStarted by remember { mutableStateOf(false) }  // Track if playback has actually started
    var firstVideoFrameRendered by remember { mutableStateOf(false) }
    var showControls by remember { mutableStateOf(true) }
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    var progress by remember { mutableFloatStateOf(0f) }

    // Skip overlay state - shows +10/-10 without showing full controls
    var skipAmount by remember { mutableIntStateOf(0) }
    var showSkipOverlay by remember { mutableStateOf(false) }
    var lastSkipTime by remember { mutableLongStateOf(0L) }
    var skipStartPosition by remember { mutableLongStateOf(0L) }  // Position when skipping started
    var skipPreviewPosition by remember { mutableLongStateOf(0L) }
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
    val subtitleSettingsBtnFocusRequester = remember { FocusRequester() }
    val pipButtonFocusRequester = remember { FocusRequester() }

    // Focus state - 0=Play, 1=Subtitles
    var focusedButton by remember { mutableIntStateOf(0) }
    var showSubtitleMenu by remember { mutableStateOf(false) }
    var showSourceMenu by remember { mutableStateOf(false) }
    // Post-episode "Up Next" prompt (issue #86). Shown on STATE_ENDED for TV shows:
    // a 10-second countdown lets the user Cancel or immediately Continue. On timeout we
    // advance to the next episode. Gated on the existing autoPlayNext profile setting —
    // when disabled we simply stay on the ended frame rather than advancing silently.
    var showNextEpisodePrompt by remember { mutableStateOf(false) }
    var pendingNextSeason by remember { mutableIntStateOf(0) }
    var pendingNextEpisode by remember { mutableIntStateOf(0) }
    var pendingNextAddonId by remember { mutableStateOf<String?>(null) }
    var pendingNextSourceName by remember { mutableStateOf<String?>(null) }
    var pendingNextBingeGroup by remember { mutableStateOf<String?>(null) }
    var nextEpisodePromptButton by remember { mutableIntStateOf(0) } // 0 = next, 1 = cancel
    var playerResizeMode by remember { mutableIntStateOf(AspectRatioFrameLayout.RESIZE_MODE_FIT) }
    var subtitleMenuIndex by remember { mutableIntStateOf(0) }
    var subtitleMenuTab by remember { mutableIntStateOf(0) } // 0 = Subtitles, 1 = Audio
    var subtitleLangIndex by remember { mutableIntStateOf(0) }
    var subtitleTrackIndex by remember { mutableIntStateOf(0) }
    var subtitlePanelFocus by remember { mutableIntStateOf(0) } // 0=lang panel, 1=track panel
    // In-player subtitle settings panel state
    var showSubtitleSettings by remember { mutableStateOf(false) }
    var subtitleSettingsRow by remember { mutableIntStateOf(0) }  // 0=Delay, 1=Size, 2=Vertical
    var subtitleSyncOffsetMs by remember { mutableLongStateOf(0L) }
    var subtitleSizePct by remember { mutableIntStateOf(100) }
    var subtitleVerticalPct by remember {
        mutableIntStateOf(when (uiState.subtitleOffset) {
            "Bottom" -> 2; "Low" -> 8; "Medium" -> 15; "High" -> 25; else -> 8
        })
    }
    val subtitleGroups = remember(uiState.subtitles, uiState.preferredSubtitleLang, uiState.secondarySubtitleLang, uiState.selectedStream, uiState.isAiAvailable, uiState.aiTargetLanguageName) {
        val streamSource = uiState.selectedStream?.source ?: ""
        val primaryName = getFullLanguageName(uiState.preferredSubtitleLang)
        val secondaryName = getFullLanguageName(uiState.secondarySubtitleLang)
        val groups = uiState.subtitles.mapIndexed { idx, sub -> Pair(idx, sub) }
            .groupBy { (_, sub) -> getFullLanguageName(sub.lang).ifBlank { sub.lang.ifBlank { "Unknown" } } }
            .entries
            .sortedWith(compareBy(
                { (langName, _) ->
                    when {
                        langName.equals(primaryName, ignoreCase = true) -> 0
                        langName.equals(secondaryName, ignoreCase = true) -> 1
                        else -> 2
                    }
                },
                { (langName, _) -> langName }
            ))
            .map { (langName, items) ->
                Pair(langName, items.sortedWith(
                    compareByDescending<Pair<Int, Subtitle>> { (_, sub) -> if (sub.isEmbedded) 1 else 0 }
                        .thenByDescending { (_, sub) -> subtitleMatchScore(streamSource, sub) }
                        .thenBy { (_, sub) -> sub.groupIndex ?: Int.MAX_VALUE }
                        .thenBy { (_, sub) -> sub.trackIndex ?: Int.MAX_VALUE }
                ))
            }
            .toMutableList()
        // When AI is available but no subtitles exist in the target language yet, inject a
        // synthetic empty group so the AI option is reachable in the picker.
        if (uiState.isAiAvailable && uiState.aiTargetLanguageName.isNotBlank() &&
            groups.none { (name, _) -> name.equals(uiState.aiTargetLanguageName, ignoreCase = true) }) {
            groups.add(0, Pair(uiState.aiTargetLanguageName, emptyList()))
        }
        groups.toList()
    }
    // Audio tracks from ExoPlayer
    var audioTracks by remember { mutableStateOf<List<AudioTrackInfo>>(emptyList()) }
    var selectedAudioIndex by remember { mutableIntStateOf(0) }
    // Once the user picks an audio track for the current stream, stop auto-applying
    // the preferred-language selection so we don't fight their choice.
    var userPickedAudioForStream by remember { mutableStateOf(false) }

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
    var pendingStartupFailover by remember { mutableStateOf(false) }
    var pendingStartupFailoverMessage by remember { mutableStateOf<String?>(null) }
    var pendingStartupFailureRecorded by remember { mutableStateOf(false) }
    var dvStartupFallbackStage by remember { mutableIntStateOf(0) } // 0=none, 1=HEVC forced, 2=AVC forced
    var midPlaybackRecoveryAttempts by remember { mutableIntStateOf(0) }
    var blackVideoRecoveryStage by remember { mutableIntStateOf(0) } // 0=none, 1=HEVC forced, 2=AVC forced
    var blackVideoReadySinceMs by remember { mutableStateOf<Long?>(null) }
    var readyPlayingSinceMs by remember { mutableStateOf<Long?>(null) }
    val heavyStartupMaxRetries = 1
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

    // Picture-in-Picture state
    var isInPipMode by remember { mutableStateOf(false) }

    // Render Material ImageVectors into bitmaps for PiP RemoteActions — same icon pack, no XML files.
    val pipDensity = LocalDensity.current
    val pipRewindPainter  = rememberVectorPainter(Icons.Default.Replay10)
    val pipPlayPainter    = rememberVectorPainter(Icons.Default.PlayArrow)
    val pipPausePainter   = rememberVectorPainter(Icons.Default.Pause)
    val pipForwardPainter = rememberVectorPainter(Icons.Default.Forward10)

    fun vectorToDrawableIcon(painter: androidx.compose.ui.graphics.painter.Painter): DrawableIcon {
        // Scale icon proportionally to the PiP window size.
        // PiP is typically ~35% of screen width at 16:9; action buttons fill ~30% of window height.
        val metrics = context.resources.displayMetrics
        val pipWindowHeightPx = (metrics.widthPixels * 0.35f * 9f / 16f).toInt()
        val proportionalSizePx = (pipWindowHeightPx * 0.30f).toInt()
        val minSizePx = with(pipDensity) { 48.dp.roundToPx() }
        val sizePx = proportionalSizePx.coerceAtLeast(minSizePx)

        val imageBitmap = ImageBitmap(sizePx, sizePx)
        val scope = CanvasDrawScope()
        val drawSize = androidx.compose.ui.geometry.Size(sizePx.toFloat(), sizePx.toFloat())
        scope.draw(pipDensity, LayoutDirection.Ltr, ComposeCanvas(imageBitmap), drawSize) {
            with(painter) { draw(drawSize, colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.White)) }
        }
        return DrawableIcon.createWithBitmap(imageBitmap.asAndroidBitmap())
    }

    // Helper to build PiP params with current playback state
    fun buildPipParams(): PictureInPictureParams? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return null
        val makeIntent = { action: String, code: Int ->
            PendingIntent.getBroadcast(
                context, code,
                Intent(action).apply { `package` = context.packageName },
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }
        val rewindLabel = context.getString(R.string.player_cd_rewind)
        val forwardLabel = context.getString(R.string.player_cd_forward)
        val playPauseLabel = if (isPlaying) context.getString(R.string.player_cd_pause) else context.getString(R.string.play)
        val actions = listOf(
            RemoteAction(
                vectorToDrawableIcon(pipRewindPainter),
                rewindLabel, rewindLabel, makeIntent(PIP_ACTION_REWIND, 10)
            ),
            RemoteAction(
                vectorToDrawableIcon(if (isPlaying) pipPausePainter else pipPlayPainter),
                playPauseLabel,
                playPauseLabel,
                makeIntent(PIP_ACTION_PLAY_PAUSE, 11)
            ),
            RemoteAction(
                vectorToDrawableIcon(pipForwardPainter),
                forwardLabel, forwardLabel, makeIntent(PIP_ACTION_FORWARD, 12)
            )
        )
        return PictureInPictureParams.Builder()
            .setAspectRatio(Rational(16, 9))
            .setActions(actions)
            .build()
    }

    // Enter PiP mode
    val enterPipMode: () -> Unit = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            buildPipParams()?.let { params -> activity?.enterPictureInPictureMode(params) }
        }
    }

    // Detect PiP mode changes via lifecycle — touch devices only (ON_PAUSE = entering PiP, ON_RESUME = exiting)
    DisposableEffect(lifecycleOwner, activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O || !deviceType.isTouchDevice()) {
            return@DisposableEffect onDispose {}
        }
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    if (activity?.isInPictureInPictureMode == true) {
                        isInPipMode = true
                        showControls = false
                    }
                }
                Lifecycle.Event.ON_RESUME -> isInPipMode = false
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    // Update PiP params when play/pause changes so the PiP overlay button stays in sync — touch only
    LaunchedEffect(isInPipMode, isPlaying) {
        if (isInPipMode && deviceType.isTouchDevice() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            buildPipParams()?.let { params -> activity?.setPictureInPictureParams(params) }
        }
    }

    // Load media
    LaunchedEffect(mediaType, mediaId, seasonNumber, episodeNumber, imdbId, preferredAddonId, preferredSourceName, preferredBingeGroup, startPositionMs) {
        playbackIssueReported = false
        startupRecoverAttempted = false
        startupHardFailureReported = false
        startupSameSourceRetryCount = 0
        startupSameSourceRefreshAttempted = false
        startupUrlLock = null
        pendingStartupFailover = false
        pendingStartupFailoverMessage = null
        pendingStartupFailureRecorded = false
        dvStartupFallbackStage = 0
        blackVideoRecoveryStage = 0
        blackVideoReadySinceMs = null
        firstVideoFrameRendered = false
        rebufferRecoverAttempted = false
        longRebufferCount = 0
        autoAdvanceAttempts = 0
        triedStreamIndexes = emptySet()
        isAutoAdvancing = false
        userSelectedSourceManually = false
        readyPlayingSinceMs = null
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
    fun tryAdvanceToNextStream(skipAddonId: String? = null, recordCurrentFailure: Boolean = true): Boolean {
        val streams = uiState.streams
        return if (streams.size <= 1) {
            viewModel.onFailoverAttempt(success = false)
            false
        } else {
            val nextIndex = (1 until streams.size)
                .map { offset -> (currentStreamIndex + offset) % streams.size }
                .firstOrNull { idx ->
                    val candidate = streams[idx]
                    candidate.url?.isNotBlank() == true &&
                        idx !in triedStreamIndexes &&
                        (skipAddonId.isNullOrBlank() || candidate.addonId != skipAddonId) &&
                        !viewModel.isPlaybackHostTemporarilyBad(candidate)
                } ?: -1

            if (nextIndex < 0) {
                viewModel.onFailoverAttempt(success = false)
                false
            } else {
                viewModel.onFailoverAttempt(success = true)
                autoAdvanceAttempts += 1
                playbackStartupDiag(
                    "advancing source from index=$currentStreamIndex to index=$nextIndex " +
                        "from=${uiState.selectedStream?.addonId}/${uiState.selectedStream?.quality}/${uiState.selectedStream?.size} " +
                        "to=${streams[nextIndex].addonId}/${streams[nextIndex].quality}/${streams[nextIndex].size}"
                )
                if (recordCurrentFailure) {
                    viewModel.onSelectedStreamPlaybackFailure()
                }
                currentStreamIndex = nextIndex
                triedStreamIndexes = triedStreamIndexes + nextIndex
                userSelectedSourceManually = false
                playbackIssueReported = false
                startupRecoverAttempted = false
                startupHardFailureReported = false
                startupSameSourceRetryCount = 0
                startupSameSourceRefreshAttempted = false
                startupUrlLock = null
                pendingStartupFailover = false
                pendingStartupFailoverMessage = null
                pendingStartupFailureRecorded = false
                dvStartupFallbackStage = 0
                rebufferRecoverAttempted = false
                longRebufferCount = 0
                isAutoAdvancing = true
                viewModel.selectStream(streams[nextIndex])
                true
            }
        }
    }

    LaunchedEffect(
        pendingStartupFailover,
        uiState.streams,
        uiState.sourceSearchActive,
        uiState.streamSelectionNonce
    ) {
        if (!pendingStartupFailover || hasPlaybackStarted || userSelectedSourceManually) {
            return@LaunchedEffect
        }

        if (tryAdvanceToNextStream(recordCurrentFailure = !pendingStartupFailureRecorded)) {
            return@LaunchedEffect
        }

        val sourceSearchStillActive = uiState.sourceSearchActive ||
            uiState.streamProgress != null ||
            !uiState.streamLoadPhase.isNullOrBlank()
        if (!sourceSearchStillActive && !playbackIssueReported) {
            playbackIssueReported = true
            pendingStartupFailover = false
            viewModel.reportPlaybackError(
                pendingStartupFailoverMessage ?: "Source failed during startup. Try another source."
            )
        }
    }

    fun markPlaybackStarted(reason: String) {
        if (hasPlaybackStarted) return
        hasPlaybackStarted = true
        pendingStartupFailover = false
        pendingStartupFailoverMessage = null
        pendingStartupFailureRecorded = false
        midPlaybackRecoveryAttempts = 0
        val startupMs = streamSelectedTime?.let { startedAt ->
            (System.currentTimeMillis() - startedAt).coerceAtLeast(0L)
        } ?: 0L
        playbackStartupDiag(
            "started reason=$reason startupMs=$startupMs retries=$startupSameSourceRetryCount refresh=$startupSameSourceRefreshAttempted failovers=$autoAdvanceAttempts"
        )
        viewModel.onPlaybackStarted(
            startupMs = startupMs,
            startupRetries = startupSameSourceRetryCount + if (startupSameSourceRefreshAttempted) 1 else 0,
            autoFailovers = autoAdvanceAttempts
        )
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
        OkHttpProvider.playbackClient.newBuilder()
            .cookieJar(playbackCookieJar)
            .build()
    }
    val httpDataSourceFactory = remember(playbackHttpClient) {
        OkHttpDataSource.Factory(playbackHttpClient)
            .setUserAgent(OkHttpProvider.userAgent)
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
        HlsMediaSource.Factory(httpDataSourceFactory)
            .setAllowChunklessPreparation(true)
    }
    val dashFactory = remember(httpDataSourceFactory) {
        DashMediaSource.Factory(httpDataSourceFactory)
    }
    val mediaSourceFactory = remember(httpDataSourceFactory) {
        DefaultMediaSourceFactory(context)
            .setDataSourceFactory(cacheDataSourceFactory)
    }

    // ExoPlayer - tuned for both small and very large files. The byte cap scales
    // with the device heap so 4K/debrid streams get breathing room without pushing
    // low-RAM TVs into GC pressure.
    val aiRenderersFactory = remember {
        AiSubtitleRenderersFactory(
            context = context,
            translationManager = viewModel.translationManager,
            scope = coroutineScope
        )
    }
    val exoPlayer = remember(isConstrainedPlaybackDevice, preferExtensionDecoder, playbackBufferProfile) {
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                playbackBufferProfile.minBufferMs,
                playbackBufferProfile.maxBufferMs,
                playbackBufferProfile.bufferForPlaybackMs,
                playbackBufferProfile.bufferForPlaybackAfterRebufferMs
            )
            .setTargetBufferBytes(playbackBufferProfile.targetBufferBytes)
            .setPrioritizeTimeOverSizeThresholds(false) // byte cap is authoritative
            .setBackBuffer(playbackBufferProfile.backBufferMs, false)
            .build()

        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setRenderersFactory(
                aiRenderersFactory
                    // Use hardware decoders first; extension decoders only as fallback.
                    // On this SEI/Amlogic TV the C2 hardware decoder hangs during allocation,
                    // so prefer the bundled FFmpeg decoder while keeping the selected source.
                    .setExtensionRendererMode(
                        if (preferExtensionDecoder) {
                            DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER
                        } else {
                            DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON
                        }
                    )
                    // Several Android TV firmware builds hang inside CCodec async allocation.
                    // Keep codec startup on the synchronous path for safer first-frame startup.
                    .forceDisableMediaCodecAsynchronousQueueing()
                    .experimentalSetEnableMediaCodecVideoRendererPrewarming(false)
                    // Enable fallback decoders for any format issues
                    .setEnableDecoderFallback(true)
            )
            .setLoadControl(loadControl)
            // Configure track selection for maximum compatibility
            .setTrackSelector(
                androidx.media3.exoplayer.trackselection.DefaultTrackSelector(context).apply {
                    parameters = buildUponParameters()
                        // Prefer original audio language when available
                        .setPreferredAudioLanguage(uiState.preferredAudioLanguage.takeUnless { it.isBlank() || it.equals("none", ignoreCase = true) })
                        // Allow decoder fallback for unsupported codecs
                        .setAllowVideoMixedMimeTypeAdaptiveness(true)
                        .setAllowVideoNonSeamlessAdaptiveness(true)
                        // Allow any audio/video codec combination
                        .setAllowAudioMixedMimeTypeAdaptiveness(true)
                        // Disable HDR requirement - play HDR as SDR if needed
                        .setForceLowestBitrate(false)
                        // Phones/tablets must not pick a video track above the hardware renderer's
                        // capability: that can produce audio/subtitles with a permanently black
                        // video surface on 4K remux/DV files. Keep audio permissive for DTS/TrueHD
                        // style tracks, but keep video renderer selection strict on touch devices.
                        .setExceedVideoConstraintsIfNecessary(allowVideoExceedCodecCapabilities)
                        .setExceedAudioConstraintsIfNecessary(allowAudioExceedCodecCapabilities)
                        .setExceedRendererCapabilitiesIfNecessary(allowRendererExceedCodecCapabilities)
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

                    override fun onRenderedFirstFrame() {
                        if (playerReleasedAtomic.get()) return
                        firstVideoFrameRendered = true
                        markPlaybackStarted("first_frame")
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
                                        .setExceedRendererCapabilitiesIfNecessary(allowRendererExceedCodecCapabilities)
                                        .setExceedVideoConstraintsIfNecessary(allowVideoExceedCodecCapabilities)
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
                            val isTransientStartupReadError =
                                error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED ||
                                    error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED ||
                                    error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_UNSPECIFIED ||
                                    error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED ||
                                    error.errorCode == androidx.media3.common.PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS ||
                                    isTimeoutError

                            // For heavy sources, retry same source first instead of failing immediately.
                            if (!hasPlaybackStarted && heavy && isTimeoutError && startupSameSourceRetryCount < heavyStartupMaxRetries) {
                                startupSameSourceRetryCount += 1
                                val wasPlaying = playWhenReady
                                stop()
                                prepare()
                                playWhenReady = wasPlaying
                                return
                            }
                            if (!hasPlaybackStarted && isTransientStartupReadError && startupSameSourceRetryCount < 1) {
                                startupSameSourceRetryCount += 1
                                val player = this@apply
                                val wasPlaying = player.playWhenReady
                                playbackStartupDiag(
                                    "same-source startup retry code=${error.errorCode} " +
                                        "streams=${latestUiState.streams.size} sourceSearch=${latestUiState.sourceSearchActive}"
                                )
                                coroutineScope.launch {
                                    delay(650)
                                    if (!playerReleasedAtomic.get() && !hasPlaybackStarted && latestUiState.selectedStreamUrl != null) {
                                        runCatching {
                                            player.stop()
                                            player.prepare()
                                            player.playWhenReady = wasPlaying
                                        }
                                    }
                                }
                                return
                            }
                            if (!hasPlaybackStarted && heavy && isTimeoutError) {
                                // One-time full re-resolve of same source to refresh debrid URL/headers.
                                if (!startupSameSourceRefreshAttempted) {
                                    startupSameSourceRefreshAttempted = true
                                    latestUiState.selectedStream?.let { viewModel.selectStream(it, this@apply.currentPosition) }
                                    return
                                }
                            }

                            // Auto-advance when the startup URL is clearly dead — HTTP 4xx/5xx
                            // or DNS/SSL/network failures. Even if the user manually picked this
                            // source, a dead URL isn't something they "selected" — it should
                            // skip to the next one rather than spin on a pulsing logo forever.
                            // Guarded below so only autoplay advances; manual selections stay pinned.
                            val isDnsFailure = "unknownhost" in timeoutMessage ||
                                "unable to resolve host" in timeoutMessage ||
                                "no address associated with hostname" in timeoutMessage
                            val deadAddonId = if (isDnsFailure) latestUiState.selectedStream?.addonId else null
                            if (!hasPlaybackStarted &&
                                allowStartupSourceFallback &&
                                !userSelectedSourceManually &&
                                tryAdvanceToNextStream(deadAddonId)
                            ) {
                                return
                            }
                            val sourceSearchStillActive = latestUiState.sourceSearchActive ||
                                latestUiState.streamProgress != null ||
                                !latestUiState.streamLoadPhase.isNullOrBlank()
                            if (!hasPlaybackStarted &&
                                allowStartupSourceFallback &&
                                !userSelectedSourceManually &&
                                sourceSearchStillActive
                            ) {
                                pendingStartupFailover = true
                                pendingStartupFailoverMessage = playbackErrorMessageFor(error, hasPlaybackStarted)
                                if (!pendingStartupFailureRecorded) {
                                    pendingStartupFailureRecorded = true
                                    viewModel.onSelectedStreamPlaybackFailure()
                                }
                                playbackStartupDiag(
                                    "waiting for more sources after startup error code=${error.errorCode} " +
                                        "streams=${latestUiState.streams.size}"
                                )
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
                                    // ExoPlayer prefixes external subtitle IDs with "{periodIndex}:"
                                    // (e.g. "1:189618" for an external sub whose id we set to "189618").
                                    // Fall back to matching by the bare id after the last colon.
                                    val matched = if (formatTrackId.isNotBlank()) {
                                        subtitleByTrackId[formatTrackId]
                                            ?: subtitleByTrackId[formatTrackId.substringAfterLast(':')]
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
                                    val isForced = format.selectionFlags and C.SELECTION_FLAG_FORCED != 0
                                    // Image-based subtitle tracks (PGS/VOBSUB/DVB) carry no text — they
                                    // can't be AI-translated, so flag them to exclude as a translation source.
                                    val isBitmap = isBitmapSubtitleMime(format.sampleMimeType)
                                    textTracks.add(Subtitle(
                                        id = matched?.id ?: formatTrackId.ifBlank { "embedded_${groupIndex}_$i" },
                                        url = matched?.url.orEmpty(),
                                        lang = lang,
                                        label = label,
                                        provider = matched?.provider.orEmpty(),
                                        isEmbedded = !isExternal,
                                        groupIndex = groupIndex,
                                        trackIndex = i,
                                        isForced = isForced,
                                        isBitmap = isBitmap,
                                    ))
                                }
                            }
                        }
                        viewModel.updatePlayerTextTracks(textTracks)
                    }
                })
            }
    }

    DisposableEffect(lifecycleOwner, exoPlayer) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    // Don't pause when entering PiP — video should keep playing in the window.
                    val inPip = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
                        activity?.isInPictureInPictureMode == true
                    if (!inPip && exoPlayer.isPlaying) {
                        exoPlayer.pause()
                    }
                }
                Lifecycle.Event.ON_STOP -> {
                    if (exoPlayer.isPlaying) exoPlayer.pause()
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // BroadcastReceiver for PiP control actions (rewind, play/pause, forward) — touch devices only
    DisposableEffect(exoPlayer) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O || !deviceType.isTouchDevice()) return@DisposableEffect onDispose {}
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(ctx: android.content.Context, intent: Intent) {
                if (playerReleased) return
                when (intent.action) {
                    PIP_ACTION_REWIND ->
                        exoPlayer.seekTo((exoPlayer.currentPosition - 10_000L).coerceAtLeast(0L))
                    PIP_ACTION_PLAY_PAUSE ->
                        if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                    PIP_ACTION_FORWARD -> {
                        val dur = exoPlayer.duration
                        exoPlayer.seekTo(
                            (exoPlayer.currentPosition + 10_000L)
                                .coerceAtMost(if (dur > 0L) dur else Long.MAX_VALUE)
                        )
                    }
                }
            }
        }
        val filter = IntentFilter().apply {
            addAction(PIP_ACTION_REWIND)
            addAction(PIP_ACTION_PLAY_PAUSE)
            addAction(PIP_ACTION_FORWARD)
        }
        ContextCompat.registerReceiver(context, receiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED)
        onDispose { context.unregisterReceiver(receiver) }
    }

    val queueControlsSeek: (Long) -> Unit = queueSeek@{ deltaMs ->
        if (isCasting) {
            if (deltaMs > 0) castManager.skipForward(deltaMs)
            else castManager.skipBack(-deltaMs)
            return@queueSeek
        }
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
        if (isCasting) {
            castManager.seekTo(scrubPreviewPosition)
            isControlScrubbing = false
            return@commitSeek
        }
        if (playerReleased) return@commitSeek
        if (isControlScrubbing) {
            controlsSeekJob?.cancel()
            exoPlayer.seekTo(scrubPreviewPosition)
            isControlScrubbing = false
        }
    }

    // Tracks the last confirmed position from the Chromecast so we can resume
    // ExoPlayer from it after disconnecting (remoteMediaClient is null by then).
    var lastCastPositionMs by remember { mutableStateOf(0L) }

    // When cast session starts: pause local ExoPlayer and hand the URL off to Chromecast.
    // When cast session ends: resume local ExoPlayer from the last reported cast position.
    LaunchedEffect(castState) {
        when (castState) {
            is CastManager.CastState.Casting -> {
                val url = uiState.selectedStreamUrl ?: return@LaunchedEffect
                val posMs = if (!playerReleased) exoPlayer.currentPosition else 0L
                if (!playerReleased) exoPlayer.pause()
                castManager.loadMedia(
                    url = url,
                    title = uiState.title,
                    imageUrl = uiState.backdropUrl,
                    mimeType = guessCastMimeType(url),
                    positionMs = posMs
                )
            }
            is CastManager.CastState.NotConnected -> {
                // remoteMediaClient is null here — use the position tracked by the poll loop
                val resumePos = lastCastPositionMs
                if (!playerReleased && resumePos > 0L && !exoPlayer.isPlaying) {
                    exoPlayer.seekTo(resumePos)
                    exoPlayer.play()
                }
                lastCastPositionMs = 0L
            }
            else -> Unit
        }
    }

    // Poll RemoteMediaClient state at 500 ms intervals while casting so the
    // progress bar and play/pause icon reflect what the Chromecast is doing.
    LaunchedEffect(isCasting) {
        if (!isCasting) return@LaunchedEffect
        while (true) {
            val pos = castManager.getApproximatePosition()
            if (pos > 0L) lastCastPositionMs = pos
            currentPosition = pos
            val remoteDuration = castManager.getApproximateDuration()
            if (remoteDuration > 0L) duration = remoteDuration
            progress = if (duration > 0L) {
                (currentPosition.toFloat() / duration.toFloat()).coerceIn(0f, 1f)
            } else 0f
            isPlaying = castManager.isRemotePlaying()
            delay(500)
        }
    }

    LaunchedEffect(uiState.preferredAudioLanguage) {
        if (playerReleased) return@LaunchedEffect
        val trackSelector = exoPlayer.trackSelector as? androidx.media3.exoplayer.trackselection.DefaultTrackSelector
        if (trackSelector != null) {
            val params = trackSelector.buildUponParameters()
                .setPreferredAudioLanguage(uiState.preferredAudioLanguage.takeUnless { it.isBlank() || it.equals("none", ignoreCase = true) })
                .build()
            trackSelector.parameters = params
        }
    }

    // Reset the manual-pick guard whenever the playing stream changes so the
    // preferred-language auto-selection runs fresh for the new file.
    LaunchedEffect(uiState.selectedStreamUrl) {
        userPickedAudioForStream = false
    }

    // Deterministically apply the preferred audio language once tracks are known.
    // ExoPlayer's setPreferredAudioLanguage only matches on the container's language
    // tag, so Polish "Lektor"/"Dubbing" tracks that ship with a missing or non-standard
    // tag get skipped and playback falls back to the default track (often Russian on
    // multi-audio releases). We additionally match on the track label here so the user's
    // chosen language wins regardless of how the track was tagged.
    LaunchedEffect(audioTracks, uiState.preferredAudioLanguage, userPickedAudioForStream) {
        if (playerReleased || userPickedAudioForStream) return@LaunchedEffect
        if (audioTracks.size < 2) return@LaunchedEffect
        val preferred = uiState.preferredAudioLanguage.trim()
        if (preferred.isBlank() || preferred.equals("none", ignoreCase = true)) return@LaunchedEffect
        val matchIndex = findPreferredAudioTrackIndex(audioTracks, preferred)
        if (matchIndex == null || matchIndex == selectedAudioIndex) return@LaunchedEffect
        audioTracks.getOrNull(matchIndex)?.let { track ->
            applyAudioTrackSelection(exoPlayer, track, audioTracks)?.let {
                selectedAudioIndex = it
            }
        }
    }

    // Frame rate matching: set ExoPlayer strategy + actual display mode switching
    val frameRateActivity = context as? android.app.Activity
    LaunchedEffect(uiState.frameRateMatchingMode) {
        if (playerReleased) return@LaunchedEffect
        // We apply display-mode matching explicitly before playback starts.
        // Keep Media3 runtime switching off to avoid late black-screen switches
        // once playback has already begun.
        val effectiveStrategy = resolveFrameRateOffStrategy()
        runCatching {
            exoPlayer.javaClass
                .getMethod("setVideoChangeFrameRateStrategy", Int::class.javaPrimitiveType)
                .invoke(exoPlayer, effectiveStrategy)
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
        val selected = uiState.selectedStream
        val idxByUrl = uiState.streams.indexOfFirst { it.url == currentUrl }
        val idx = if (idxByUrl >= 0) {
            idxByUrl
        } else {
            uiState.streams.indexOfFirst { candidate ->
                selected != null &&
                    candidate.addonId == selected.addonId &&
                    candidate.source == selected.source &&
                    candidate.behaviorHints?.bingeGroup == selected.behaviorHints?.bingeGroup
            }
        }
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
            // Track when stream was selected (before any blocking probes)
            streamSelectedTime = System.currentTimeMillis()
            val prepareStartMs = streamSelectedTime ?: System.currentTimeMillis()
            bufferingStartTime = null
            hasPlaybackStarted = false  // Reset for new stream
            firstVideoFrameRendered = false
            readyPlayingSinceMs = null
            playbackIssueReported = false
            rebufferRecoverAttempted = false
            longRebufferCount = 0
            ArflixApplication.trimImageMemory()

            val streamHeaders = uiState.selectedStream
                ?.behaviorHints
                ?.proxyHeaders
                ?.request
                .orEmpty()
                .filterKeys { it.isNotBlank() }

            // Never block first frame on MediaExtractor probing. Use a cached
            // frame-rate if available and prewarm the cache in the background.
            frameRateActivity?.let { activity ->
                val mode = uiState.frameRateMatchingMode
                if (mode == "Off" || mode.isBlank()) {
                    com.arflix.tv.util.FrameRateUtils.restoreOriginalMode(activity)
                } else {
                    val cachedDetection = com.arflix.tv.util.FrameRateUtils.getCachedFrameRate(url)
                    if (cachedDetection != null) {
                        com.arflix.tv.util.FrameRateUtils.applyFrameRateMode(activity, cachedDetection.snapped)
                    } else {
                        launch(kotlinx.coroutines.Dispatchers.IO) {
                            com.arflix.tv.util.FrameRateUtils.detectFrameRateCached(
                                sourceUrl = url,
                                headers = baseRequestHeaders + streamHeaders
                            )
                        }
                    }
                }
            }

            val isNewStartupSource = startupUrlLock != url
            if (isNewStartupSource) {
                startupUrlLock = url
                startupRecoverAttempted = false
                startupHardFailureReported = false
                startupSameSourceRetryCount = 0
                startupSameSourceRefreshAttempted = false
                pendingStartupFailover = false
                pendingStartupFailoverMessage = null
                pendingStartupFailureRecorded = false
                dvStartupFallbackStage = 0
                blackVideoRecoveryStage = 0
                blackVideoReadySinceMs = null
                firstVideoFrameRendered = false
                val selector = exoPlayer.trackSelector as? androidx.media3.exoplayer.trackselection.DefaultTrackSelector
                selector?.let {
                    it.parameters = it.buildUponParameters()
                        .setPreferredVideoMimeType(null)
                        .setExceedVideoConstraintsIfNecessary(allowVideoExceedCodecCapabilities)
                        .setExceedAudioConstraintsIfNecessary(allowAudioExceedCodecCapabilities)
                        .setExceedRendererCapabilitiesIfNecessary(allowRendererExceedCodecCapabilities)
                        .build()
                }
            }
            httpDataSourceFactory.setDefaultRequestProperties(baseRequestHeaders + streamHeaders)

            // Track when stream was selected
            // (Moved up before frame rate probe)

            // Only add the selected subtitle to ExoPlayer (not all 30+).
            // Loading all external subs slows down preparation and causes non-UTF8 subs to fail.
            val mediaItemBuilder = MediaItem.Builder().setUri(Uri.parse(url))
            val mediaItem = mediaItemBuilder.build()

            // Use protocol-specific media source for faster startup:
            // - HLS: chunkless preparation enabled (saves 1-3s)
            // - DASH/Progressive: dedicated factories for optimal handling
            val urlLower = url.lowercase()
            val isHeavy = isLikelyHeavyStream(latestUiState.selectedStream)
            val isRemoteHttp = urlLower.startsWith("http://") || urlLower.startsWith("https://")
            val mediaSource: MediaSource = when {
                urlLower.contains(".m3u8") || urlLower.contains("/hls") || urlLower.contains("format=hls") ->
                    hlsFactory.createMediaSource(mediaItem)
                urlLower.contains(".mpd") || urlLower.contains("/dash") || urlLower.contains("format=dash") ->
                    dashFactory.createMediaSource(mediaItem)
                isHeavy || isRemoteHttp ->
                    // Bypass disk cache for large/debrid progressive streams to avoid I/O bottleneck
                    directProgressiveFactory.createMediaSource(mediaItem)
                else -> mediaSourceFactory.createMediaSource(mediaItem)
            }

            // Keep the old frame visible while the next source prepares. Clearing
            // media items here created a black gap before autoplay/manual sources.
            runCatching {
                exoPlayer.playWhenReady = false
            }

            val resumePosition = uiState.savedPosition
            if (resumePosition > 0L) {
                exoPlayer.setMediaSource(mediaSource, resumePosition)
            } else {
                exoPlayer.setMediaSource(mediaSource)
            }
            // Let ExoPlayer's RAM-aware LoadControl handle startup buffering.
            // No manual startup gate - trust the CDN/debrid while keeping enough safety margin.
            exoPlayer.playWhenReady = true
            exoPlayer.prepare()
            playbackStartupDiag(
                "prepare issued setupMs=${System.currentTimeMillis() - prepareStartMs} source=${uiState.selectedStream?.addonId}/${uiState.selectedStream?.quality}/${uiState.selectedStream?.size} host=${runCatching { Uri.parse(url).host }.getOrNull().orEmpty()}"
            )

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
    LaunchedEffect(uiState.selectedSubtitle, uiState.subtitleSelectionNonce, hasPlaybackStarted) {
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

        if (!hasPlaybackStarted) {
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

        // Find the resolved version with groupIndex/trackIndex from ExoPlayer.
        // Fall back to lang+label match because generated IDs (embedded_N_i) change when
        // ExoPlayer reassigns group indices after a MediaItem rebuild.
        val resolved = uiState.subtitles.firstOrNull {
            it.id == subtitle.id && it.groupIndex != null && it.trackIndex != null
        } ?: uiState.subtitles.firstOrNull {
            it.isEmbedded && it.lang == subtitle.lang && it.label == subtitle.label &&
                it.groupIndex != null && it.trackIndex != null
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
    LaunchedEffect(showControls, isPlaying, isCasting) {
        if (showControls && isPlaying && !isCasting && !showSubtitleMenu && !showSourceMenu && !showSubtitleSettings) {
            delay(5000)
            showControls = false
            // Return focus to container so it can receive key events
            delay(100)
            try {
                containerFocusRequester.requestFocus()
            } catch (_: Exception) {}
        }
    }

    // Sync in-player subtitle delay with the renderer factory (microseconds = ms * 1000)
    LaunchedEffect(subtitleSyncOffsetMs) {
        aiRenderersFactory.syncOffsetUs.set(subtitleSyncOffsetMs * 1000L)
    }

    // When cast starts: keep controls permanently visible.
    LaunchedEffect(isCasting) {
        if (isCasting) showControls = true
    }

    // Request focus on play button when controls are shown.
    // hasPlaybackStarted is also a key because the controls are inside
    // AnimatedVisibility(visible = hasPlaybackStarted && showControls),
    // so the play button isn't in composition until playback begins.
    LaunchedEffect(showControls, hasPlaybackStarted) {
        if (showControls && hasPlaybackStarted && !showSubtitleMenu && !showSourceMenu && uiState.error == null) {
            delay(300)
            try {
                playButtonFocusRequester.requestFocus()
            } catch (e: Exception) {}
        }
    }

    // Auto-hide skip overlay and reset - use lastSkipTime as key to restart on each skip
    LaunchedEffect(lastSkipTime) {
        if (showSkipOverlay && lastSkipTime > 0) {
            delay(1500)
            showSkipOverlay = false
            skipAmount = 0
            skipStartPosition = 0L
            skipPreviewPosition = 0L
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
    LaunchedEffect(exoPlayer, isCasting) {
        while (!playerReleasedAtomic.get()) {
            if (playerReleasedAtomic.get()) break
            if (isCasting) { delay(500); continue }
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
            val loopNowMs = System.currentTimeMillis()
            val readyAndPlaying = exoPlayer.playbackState == Player.STATE_READY && exoPlayer.isPlaying
            if (readyAndPlaying) {
                if (readyPlayingSinceMs == null) {
                    readyPlayingSinceMs = loopNowMs
                }
            } else {
                readyPlayingSinceMs = null
            }

            // Buffering watchdog - detect long buffering but do not force a source error popup.
            if (isBuffering && hasPlaybackStarted) {
                if (bufferingStartTime == null) {
                    bufferingStartTime = loopNowMs
                } else {
                    val bufferingDuration = loopNowMs - (bufferingStartTime ?: 0L)
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
            if (startupPending) {
                val selectedAt = streamSelectedTime ?: System.currentTimeMillis()
                val startupBufferDuration = loopNowMs - selectedAt
                val isHeavyStartupSource = isLikelyHeavyStream(uiState.selectedStream)
                if (!startupRecoverAttempted && startupBufferDuration > initialBufferingTimeoutMs) {
                    startupRecoverAttempted = true
                    playbackStartupDiag(
                        "startup timeout elapsedMs=$startupBufferDuration state=${exoPlayer.playbackState} " +
                            "isPlaying=${exoPlayer.isPlaying} heavy=$isHeavyStartupSource manual=$userSelectedSourceManually"
                    )
                    if (!isHeavyStartupSource) {
                        exoPlayer.playWhenReady = true
                    }
                }
                val hardTimeoutMs = (initialBufferingTimeoutMs + if (isHeavyStartupSource) 12_000L else 8_000L)
                    .coerceAtMost(45_000L)
                if (!startupHardFailureReported && startupBufferDuration > hardTimeoutMs) {
                    playbackStartupDiag(
                        "hard startup timeout elapsedMs=$startupBufferDuration hardTimeoutMs=$hardTimeoutMs " +
                            "state=${exoPlayer.playbackState} failovers=$autoAdvanceAttempts"
                    )
                    if (allowStartupSourceFallback &&
                        !userSelectedSourceManually &&
                        tryAdvanceToNextStream()
                    ) {
                        continue
                    }
                    startupHardFailureReported = true
                    playbackIssueReported = true
                    viewModel.onSelectedStreamPlaybackFailure()
                    viewModel.reportPlaybackError(
                        if (autoAdvanceAttempts > 0 || startupSameSourceRetryCount > 0) {
                            "Source did not start after retries. Try another source."
                        } else {
                            "Source did not start in time. Try another source."
                        }
                    )
                }
            }

            // Black-screen recovery:
            // Some TV/device/container combinations can enter READY and advance the clock
            // before any video frame is actually rendered. Do not treat that as started.
            val hasVideoTrack = exoPlayer.currentTracks.groups.any { group ->
                group.type == C.TRACK_TYPE_VIDEO && group.length > 0
            }
            val blackVideoState =
                uiState.selectedStreamUrl != null &&
                    exoPlayer.playbackState == Player.STATE_READY &&
                    exoPlayer.playWhenReady &&
                    hasVideoTrack &&
                    !firstVideoFrameRendered
            if (blackVideoState) {
                if (blackVideoReadySinceMs == null) {
                    blackVideoReadySinceMs = loopNowMs
                } else {
                    val stuckMs = loopNowMs - (blackVideoReadySinceMs ?: 0L)
                    val thresholdMs = when (blackVideoRecoveryStage) {
                        0 -> 4_500L
                        1 -> 7_000L
                        else -> 9_000L
                    }
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
                                .setExceedRendererCapabilitiesIfNecessary(allowRendererExceedCodecCapabilities)
                                .setExceedVideoConstraintsIfNecessary(allowVideoExceedCodecCapabilities)
                                .build()
                        }
                        val resumeAt = exoPlayer.currentPosition.coerceAtLeast(0L)
                        val keepPlaying = exoPlayer.playWhenReady
                        playbackStartupDiag(
                            "black video recovery stage=$blackVideoRecoveryStage preferred=$preferredMime " +
                                "size=${exoPlayer.videoSize.width}x${exoPlayer.videoSize.height}"
                        )
                        exoPlayer.seekTo(resumeAt)
                        exoPlayer.prepare()
                        exoPlayer.playWhenReady = keepPlaying
                        blackVideoRecoveryStage += 1
                        blackVideoReadySinceMs = loopNowMs
                    } else if (stuckMs >= thresholdMs && blackVideoRecoveryStage >= 2 && !startupHardFailureReported) {
                        playbackStartupDiag(
                            "black video failure no_first_frame elapsedMs=$stuckMs " +
                                "state=${exoPlayer.playbackState} failovers=$autoAdvanceAttempts"
                        )
                        if (allowStartupSourceFallback &&
                            !userSelectedSourceManually &&
                            tryAdvanceToNextStream()
                        ) {
                            continue
                        }
                        startupHardFailureReported = true
                        playbackIssueReported = true
                        viewModel.onSelectedStreamPlaybackFailure()
                        viewModel.reportPlaybackError(
                            "Video could not render on this device. Try another source."
                        )
                    }
                }
            } else {
                blackVideoReadySinceMs = null
            }

            // Mark playback as started only after a real first frame for video sources.
            // Audio-only streams can still start from READY/isPlaying.
            if (!hasPlaybackStarted &&
                readyAndPlaying &&
                (!hasVideoTrack || firstVideoFrameRendered)
            ) {
                markPlaybackStarted(
                    if (hasVideoTrack) "ready_playing_after_first_frame" else "ready_playing_audio_only"
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

            // Post-episode prompt: when a TV episode ends, show the "Up Next" overlay with a
            // 10-second countdown that auto-advances (or lets the user cancel / continue
            // immediately). Gated on the profile's autoPlayNext setting — when disabled we
            // stay on the ended frame rather than silently advancing. Only trigger once per
            // session (showNextEpisodePrompt guard) to avoid re-triggering on tick loops.
            if (exoPlayer.playbackState == Player.STATE_ENDED &&
                mediaType == MediaType.TV &&
                !showNextEpisodePrompt &&
                !showSourceMenu &&
                !showSubtitleMenu &&
                uiState.error == null
            ) {
                if (seasonNumber != null && episodeNumber != null && uiState.autoPlayNext) {
                    val selected = uiState.selectedStream
                    pendingNextSeason = seasonNumber
                    pendingNextEpisode = episodeNumber + 1
                    pendingNextAddonId = selected?.addonId?.takeIf { it.isNotBlank() }
                    pendingNextSourceName = selected?.source?.takeIf { it.isNotBlank() }
                    pendingNextBingeGroup = selected?.behaviorHints?.bingeGroup?.takeIf { it.isNotBlank() }
                    nextEpisodePromptButton = 0
                    showNextEpisodePrompt = true
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
            // Restore the system stream volume if the player left it at zero.
            // setStreamVolume(STREAM_MUSIC, 0) silences HDMI ARC, optical, and
            // Bluetooth receivers globally — not just this app — so we must undo
            // it when leaving the player, regardless of whether the user muted
            // intentionally or accidentally scrolled the volume down.
            if (isMuted || currentVolume == 0) {
                val restoreLevel = volumeBeforeMute.coerceAtLeast(1)
                runCatching { audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, restoreLevel, 0) }
            }
        }
    }

    // Volume boost via system LoudnessEnhancer attached to the ExoPlayer audio session.
    // Re-attached whenever the audio session id changes (new stream / source switch) or
    // the user changes the boost in Settings (though in practice that requires reopening
    // the player since Settings changes don't propagate mid-session yet). 0 dB = no
    // effect created, no CPU cost. Issue #88.
    DisposableEffect(uiState.volumeBoostDb, exoPlayer.audioSessionId) {
        val sessionId = exoPlayer.audioSessionId
        val targetDb = uiState.volumeBoostDb
        val enhancer: android.media.audiofx.LoudnessEnhancer? =
            if (targetDb > 0 && sessionId != C.AUDIO_SESSION_ID_UNSET) {
                try {
                    android.media.audiofx.LoudnessEnhancer(sessionId).apply {
                        setTargetGain(targetDb * 100) // API takes millibels
                        enabled = true
                    }
                } catch (e: Throwable) {
                    // Some Android TV devices route audio through HDMI passthrough and
                    // reject audio-session effects (particularly when passthrough is
                    // enabled for DTS/AC3). Fail silently — user gets unboosted audio
                    // but playback still works.
                    android.util.Log.w("PlayerScreen", "LoudnessEnhancer unavailable on this device: ${e.message}")
                    null
                }
            } else {
                null
            }
        onDispose {
            runCatching {
                enhancer?.enabled = false
                enhancer?.release()
            }
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
    LaunchedEffect(showControls, showSubtitleMenu, showSourceMenu, showNextEpisodePrompt, uiState.error) {
        if (!showControls && !showSubtitleMenu && !showSourceMenu && !showNextEpisodePrompt && uiState.error == null) {
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

    BackHandler(enabled = showSubtitleSettings) {
        showSubtitleSettings = false
        showControls = true
        coroutineScope.launch {
            delay(120)
            runCatching { subtitleSettingsBtnFocusRequester.requestFocus() }
        }
    }

    BackHandler(
        enabled = !showSubtitleMenu && !showSourceMenu && !showNextEpisodePrompt && !showSubtitleSettings && uiState.error == null
    ) {
        if (showControls) {
            showControls = false
        } else {
            onBack()
        }
    }

    val playerDeviceType = LocalDeviceType.current
    val isTouchDevice = playerDeviceType.isTouchDevice()
    val isTablet = playerDeviceType == com.arflix.tv.util.DeviceType.TABLET
    val isPhone = playerDeviceType == com.arflix.tv.util.DeviceType.PHONE
    // Read subtitle appearance prefs
    val subtitleSizePref = uiState.subtitleSize
    val subtitleColorPref = uiState.subtitleColor
    val subtitleStylePref = uiState.subtitleStyle
    val subtitleStylizedPref = uiState.subtitleStylized
    val subtitleOffsetPref = uiState.subtitleOffset
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

    androidx.compose.runtime.CompositionLocalProvider(
        androidx.compose.ui.platform.LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Ltr
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .focusRequester(containerFocusRequester)
            .focusable()
            .then(
                if (isTouchDevice) {
                    // isCasting is a key so the handler restarts when casting changes,
                    // picking up the updated queueControlsSeek lambda.
                    Modifier.pointerInput(isCasting) {
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
                    // Fire TV / Bluetooth media remote keys. These must be handled at the
                    // top of the key handler so they work regardless of which overlay
                    // (error, menus, post-episode prompt) is currently visible. Previously
                    // only Key.MediaPlayPause was handled, and only when the subtitle menu
                    // was open \u2014 useless for the common case of watching with a Fire TV
                    // stick remote that has dedicated FF/RW/Play buttons. Issue #68 (part).
                    when (event.key) {
                        Key.MediaPlayPause -> {
                            if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                            showControls = true
                            return@onKeyEvent true
                        }
                        Key.MediaPlay -> {
                            exoPlayer.play()
                            showControls = true
                            return@onKeyEvent true
                        }
                        Key.MediaPause -> {
                            exoPlayer.pause()
                            showControls = true
                            return@onKeyEvent true
                        }
                        Key.MediaStop -> {
                            exoPlayer.pause()
                            onBack()
                            return@onKeyEvent true
                        }
                        Key.MediaRewind -> {
                            queueControlsSeek(-10_000L)
                            showControls = true
                            return@onKeyEvent true
                        }
                        Key.MediaFastForward -> {
                            queueControlsSeek(10_000L)
                            showControls = true
                            return@onKeyEvent true
                        }
                        Key.MediaNext -> {
                            // Jump to next episode if this is a TV series and we have a
                            // current episode. No-op for movies (there is no next).
                            if (mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null) {
                                val selected = uiState.selectedStream
                                onPlayNext(
                                    seasonNumber,
                                    episodeNumber + 1,
                                    selected?.addonId?.takeIf { it.isNotBlank() },
                                    selected?.source?.takeIf { it.isNotBlank() },
                                    selected?.behaviorHints?.bingeGroup?.takeIf { it.isNotBlank() }
                                )
                                return@onKeyEvent true
                            }
                        }
                        Key.MediaPrevious -> {
                            // Jump to previous episode for TV series. Movies: no-op.
                            if (mediaType == MediaType.TV && seasonNumber != null && episodeNumber != null && episodeNumber > 1) {
                                val selected = uiState.selectedStream
                                onPlayNext(
                                    seasonNumber,
                                    episodeNumber - 1,
                                    selected?.addonId?.takeIf { it.isNotBlank() },
                                    selected?.source?.takeIf { it.isNotBlank() },
                                    selected?.behaviorHints?.bingeGroup?.takeIf { it.isNotBlank() }
                                )
                                return@onKeyEvent true
                            }
                        }
                        else -> Unit // fall through to normal handling
                    }

                    if (showNextEpisodePrompt) {
                        return@onKeyEvent when (event.key) {
                            Key.DirectionLeft -> {
                                nextEpisodePromptButton = 0
                                true
                            }
                            Key.DirectionRight -> {
                                nextEpisodePromptButton = 1
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                showNextEpisodePrompt = false
                                if (nextEpisodePromptButton == 0) {
                                    onPlayNext(
                                        pendingNextSeason,
                                        pendingNextEpisode,
                                        pendingNextAddonId,
                                        pendingNextSourceName,
                                        pendingNextBingeGroup
                                    )
                                }
                                true
                            }
                            Key.Back, Key.Escape -> {
                                showNextEpisodePrompt = false
                                true
                            }
                            else -> true
                        }
                    }

                    if ((event.key == Key.Back || event.key == Key.Escape) &&
                        !showSubtitleMenu && !showSourceMenu && !showNextEpisodePrompt && !showSubtitleSettings && uiState.error == null
                    ) {
                        if (showControls) {
                            showControls = false
                        } else {
                            onBack()
                        }
                        return@onKeyEvent true
                    }

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

                    // Handle subtitle settings panel
                    if (showSubtitleSettings) {
                        return@onKeyEvent when (event.key) {
                            Key.DirectionUp -> {
                                subtitleSettingsRow = (subtitleSettingsRow - 1).coerceAtLeast(0)
                                true
                            }
                            Key.DirectionDown -> {
                                subtitleSettingsRow = (subtitleSettingsRow + 1).coerceAtMost(2)
                                true
                            }
                            Key.DirectionLeft -> {
                                when (subtitleSettingsRow) {
                                    0 -> subtitleSyncOffsetMs = (subtitleSyncOffsetMs - 100L).coerceAtLeast(-10000L)
                                    1 -> subtitleSizePct = (subtitleSizePct - 10).coerceAtLeast(50)
                                    2 -> subtitleVerticalPct = (subtitleVerticalPct - 1).coerceAtLeast(0)
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                when (subtitleSettingsRow) {
                                    0 -> subtitleSyncOffsetMs = (subtitleSyncOffsetMs + 100L).coerceAtMost(10000L)
                                    1 -> subtitleSizePct = (subtitleSizePct + 10).coerceAtMost(300)
                                    2 -> subtitleVerticalPct = (subtitleVerticalPct + 1).coerceAtMost(50)
                                }
                                true
                            }
                            Key.Back, Key.Escape -> {
                                showSubtitleSettings = false
                                showControls = true
                                coroutineScope.launch {
                                    delay(120)
                                    runCatching { subtitleSettingsBtnFocusRequester.requestFocus() }
                                }
                                true
                            }
                            else -> true
                        }
                    }

                    // Handle subtitle/audio menu — two-panel layout: lang panel | track panel | audio tab
                    if (showSubtitleMenu) {
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
                                coroutineScope.launch {
                                    delay(150)
                                    try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                                }
                                true
                            }
                            Key.DirectionUp -> {
                                when {
                                    subtitleMenuTab == 1 -> { if (subtitleMenuIndex > 0) subtitleMenuIndex-- }
                                    subtitlePanelFocus == 0 -> { if (subtitleLangIndex > 0) subtitleLangIndex-- }
                                    else -> { if (subtitleTrackIndex > 0) subtitleTrackIndex-- }
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                when {
                                    subtitleMenuTab == 1 -> {
                                        if (subtitleMenuIndex < audioTracks.size.coerceAtLeast(1) - 1) subtitleMenuIndex++
                                    }
                                    subtitlePanelFocus == 0 -> {
                                        if (subtitleLangIndex < subtitleGroups.size) subtitleLangIndex++
                                    }
                                    else -> {
                                        val group = subtitleGroups.getOrNull(subtitleLangIndex - 1)
                                        val aiGroup = latestUiState.isAiAvailable &&
                                            latestUiState.aiTargetLanguageName.isNotBlank() &&
                                            group?.first?.equals(latestUiState.aiTargetLanguageName, ignoreCase = true) == true
                                        val trackCount = (group?.second?.size ?: 0) + if (aiGroup) 1 else 0
                                        if (subtitleTrackIndex < trackCount - 1) subtitleTrackIndex++
                                    }
                                }
                                true
                            }
                            Key.DirectionLeft -> {
                                when {
                                    subtitleMenuTab == 1 -> {
                                        subtitleMenuTab = 0
                                        subtitlePanelFocus = 0
                                    }
                                    subtitlePanelFocus == 1 -> {
                                        subtitlePanelFocus = 0
                                    }
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                when {
                                    subtitleMenuTab == 0 && subtitlePanelFocus == 1 -> {
                                        subtitleMenuTab = 1
                                        subtitleMenuIndex = 0
                                    }
                                    subtitleMenuTab == 0 && subtitleLangIndex > 0 -> {
                                        subtitlePanelFocus = 1
                                        subtitleTrackIndex = 0
                                    }
                                    subtitleMenuTab == 0 && subtitleLangIndex == 0 -> {
                                        subtitleMenuTab = 1
                                        subtitleMenuIndex = 0
                                    }
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                if (subtitleMenuTab == 1) {
                                    audioTracks.getOrNull(subtitleMenuIndex)?.let { track ->
                                        userPickedAudioForStream = true
                                        applyAudioTrackSelection(exoPlayer, track, audioTracks)?.let {
                                            selectedAudioIndex = it
                                        }
                                    }
                                    showSubtitleMenu = false
                                    showControls = true
                                    coroutineScope.launch {
                                        delay(150)
                                        try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                                    }
                                } else if (subtitlePanelFocus == 0) {
                                    if (subtitleLangIndex == 0) {
                                        viewModel.disableSubtitles()
                                        showSubtitleMenu = false
                                        showControls = true
                                        coroutineScope.launch {
                                            delay(150)
                                            try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                                        }
                                    } else {
                                        // Enter track panel for the selected language
                                        subtitlePanelFocus = 1
                                        subtitleTrackIndex = 0
                                    }
                                } else {
                                    val group = subtitleGroups.getOrNull(subtitleLangIndex - 1)
                                    val aiGroup = latestUiState.isAiAvailable &&
                                        latestUiState.aiTargetLanguageName.isNotBlank() &&
                                        group?.first?.equals(latestUiState.aiTargetLanguageName, ignoreCase = true) == true
                                    val realIdx = if (aiGroup) subtitleTrackIndex - 1 else subtitleTrackIndex
                                    if (aiGroup && subtitleTrackIndex == 0) {
                                        if (!latestUiState.isAiTranslating) viewModel.activateAiSubtitle()
                                    } else {
                                        group?.second?.getOrNull(realIdx)?.second
                                            ?.let { viewModel.selectSubtitle(it) }
                                    }
                                    showSubtitleMenu = false
                                    showControls = true
                                    coroutineScope.launch {
                                        delay(150)
                                        try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                                    }
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
                                skipPreviewPosition = targetPosition
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
                                skipPreviewPosition = targetPosition
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
        if (isCasting) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black))
        }
        // Keep PlayerView mounted as soon as we have a stream URL.
        // A real video surface must exist during startup, otherwise some streams never transition out of buffering.
        if (uiState.selectedStreamUrl != null && !isCasting) {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        keepScreenOn = true
                        player = exoPlayer
                        useController = false
                        setKeepContentOnPlayerReset(true)
                        resizeMode = playerResizeMode

                        // Enable subtitle view with styling based on user preference
                        subtitleView?.apply {
                            val subSizeSp = when (subtitleSizePref) {
                                "Small" -> 18f; "Large" -> 30f; "Extra Large" -> 36f; else -> 24f
                            }
                            val subFgColor = when (subtitleColorPref) {
                                "Yellow" -> android.graphics.Color.YELLOW
                                "Green" -> android.graphics.Color.GREEN
                                "Cyan" -> android.graphics.Color.CYAN
                                else -> android.graphics.Color.WHITE
                            }
                            val subTypeface = when (subtitleStylePref) {
                                "Normal" -> android.graphics.Typeface.DEFAULT
                                "Background" -> android.graphics.Typeface.DEFAULT_BOLD
                                else -> android.graphics.Typeface.DEFAULT_BOLD
                            }
                            val subEdgeType = when (subtitleStylePref) {
                                "Normal" -> androidx.media3.ui.CaptionStyleCompat.EDGE_TYPE_NONE
                                "Background" -> androidx.media3.ui.CaptionStyleCompat.EDGE_TYPE_NONE
                                else -> androidx.media3.ui.CaptionStyleCompat.EDGE_TYPE_OUTLINE
                            }
                            val subBgColor = when (subtitleStylePref) {
                                "Background" -> android.graphics.Color.argb(180, 0, 0, 0)
                                else -> android.graphics.Color.TRANSPARENT
                            }
                            setStyle(
                                androidx.media3.ui.CaptionStyleCompat(
                                    subFgColor,
                                    android.graphics.Color.TRANSPARENT,
                                    subBgColor,
                                    subEdgeType,
                                    android.graphics.Color.BLACK,
                                    subTypeface
                                )
                            )
                            val pipSubScale = if (isInPipMode) 0.4f else 1f
                            setFixedTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, subSizeSp * pipSubScale)
                            val bottomPaddingFraction = when (subtitleOffsetPref) {
                                "Bottom" -> 0.02f
                                "Low" -> 0.08f
                                "Medium" -> 0.15f
                                "High" -> 0.25f
                                else -> 0.02f
                            }
                            setBottomPaddingFraction(bottomPaddingFraction)

                            if (subtitleStylizedPref) {
                                // Stylized mode: let Media3 render embedded ASS/SSA styles
                                // (colors, fonts, positioning, z-order). User prefs are
                                // only used as a fallback CaptionStyle for plain SRT/VTT.
                                setApplyEmbeddedStyles(true)
                                setApplyEmbeddedFontSizes(true)
                            } else {
                                // Uniform mode: override everything with user preferences
                                setApplyEmbeddedStyles(false)
                                setApplyEmbeddedFontSizes(false)
                            }
                        }
                    }
                },
                update = { playerView ->
                    playerView.keepScreenOn = true
                    playerView.player = exoPlayer
                    playerView.resizeMode = playerResizeMode
                    playerView.subtitleView?.apply {
                        val baseSizeSp = when (subtitleSizePref) {
                            "Small" -> 18f; "Large" -> 30f; "Extra Large" -> 36f; else -> 24f
                        }
                        val pipSubScale = if (isInPipMode) 0.4f else 1f
                        setFixedTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, baseSizeSp * (subtitleSizePct / 100f) * pipSubScale)
                        setBottomPaddingFraction((subtitleVerticalPct / 100f).coerceIn(0f, 0.5f))
                    }
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

                PulsingLogo(
                    logoUrl = uiState.logoUrl,
                    title = uiState.title,
                    progress = if (uiState.showLoadingStats) uiState.streamProgress else null,
                    phaseLabel = if (uiState.showLoadingStats) uiState.streamLoadPhase else null
                )
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

        // Skip intro/recap overlay — only after playback has started to avoid showing
        // on the loading screen (background art + pulsing logo).
        if (hasPlaybackStarted) {
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
        }

        // AI Translating badge — shown in top-right while subtitle translation is in progress
        val isTranslatingLive by viewModel.isTranslatingLive.collectAsStateWithLifecycle()
        AnimatedVisibility(
            visible = hasPlaybackStarted && uiState.isAiTranslating && isTranslatingLive,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 16.dp)
                .zIndex(6f)
        ) {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .background(
                        color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.65f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = androidx.compose.ui.graphics.Color(0xFF7EC8A0),
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    text = stringResource(R.string.player_ai_translating),
                    style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
                    color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.85f)
                )
            }
        }

        // AI translation API error toast
        uiState.aiErrorToast?.let { msg ->
            Toast(
                message = msg,
                type = ToastType.ERROR,
                isVisible = true,
                durationMs = 5000,
                onDismiss = { viewModel.dismissAiErrorToast() }
            )
        }

        // Netflix-style Controls Overlay
        AnimatedVisibility(
            visible = hasPlaybackStarted && showControls && !showSubtitleMenu && !showSourceMenu && !isInPipMode,
            enter = fadeIn(androidx.compose.animation.core.tween(150)),
            exit = fadeOut(androidx.compose.animation.core.tween(200))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Top info
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .padding(
                            start = if (isTouchDevice) 20.dp else 28.dp,
                            top = if (isTouchDevice) 18.dp else 30.dp,
                            end = if (isTouchDevice) 24.dp else 48.dp
                        )
                        .zIndex(4f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    val isPaused = hasPlaybackStarted && !isPlaying && !isBuffering

                    PlayerMetadataChrome(
                        uiState = uiState,
                        mediaType = mediaType,
                        seasonNumber = seasonNumber,
                        episodeNumber = episodeNumber,
                        isPaused = isPaused,
                        accentColor = playerAccent,
                        modifier = Modifier.weight(1f, fill = false)
                    )

                    // Right side - Cast button (mobile) + Ends At + Clock
                    Column(horizontalAlignment = Alignment.End) {
                        val currentTime = remember { mutableStateOf("") }
                        val endsAtTime = remember { mutableStateOf("") }
                        LaunchedEffect(duration, currentPosition, clockFormat) {
                            while (true) {
                                val now = System.currentTimeMillis()
                                currentTime.value = formatPlayerClockTime(now, clockFormat)
                                if (duration > 0 && currentPosition >= 0) {
                                    val remainingMs = (duration - currentPosition).coerceAtLeast(0L)
                                    endsAtTime.value = formatPlayerClockTime(now + remainingMs, clockFormat)
                                } else { endsAtTime.value = "" }
                                kotlinx.coroutines.delay(1000)
                            }
                        }

                        // Cast button — mobile/tablet only; hidden when stream requires custom headers
                        if (isTouchDevice && castAvailable && !streamNeedsHeaders) {
                            val castDeviceName = (castState as? CastManager.CastState.Casting)?.deviceName
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                modifier = Modifier.padding(bottom = if (endsAtTime.value.isNotBlank() || !isTouchDevice) 4.dp else 0.dp)
                            ) {
                                if (castDeviceName != null) {
                                    androidx.tv.material3.Text(
                                        text = castDeviceName,
                                        style = ArflixTypography.caption.copy(fontSize = 11.sp),
                                        color = Color.White.copy(alpha = 0.85f),
                                        maxLines = 1
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isCasting) Color.White.copy(alpha = 0.2f)
                                            else Color.Transparent
                                        )
                                        .clickable {
                                            if (isCasting) {
                                                castManager.disconnect()
                                            } else {
                                                val dialog = MediaRouteChooserDialog(context)
                                                dialog.routeSelector = castManager.getRouteSelector()
                                                dialog.show()
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (isCasting) Icons.Default.CastConnected else Icons.Default.Cast,
                                        contentDescription = if (isCasting) stringResource(R.string.player_cd_stop_casting) else stringResource(R.string.player_cd_cast_to_tv),
                                        tint = if (isCasting) playerAccent else Color.White.copy(alpha = 0.85f),
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            }
                        }

                        if (!isTouchDevice) {
                            Text(
                                currentTime.value,
                                style = ArflixTypography.sectionTitle.copy(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                color = TextPrimary.copy(alpha = 0.92f),
                                maxLines = 1
                            )
                        }
                        if (endsAtTime.value.isNotBlank()) {
                            Text(
                                "${stringResource(R.string.ends_at)} ${endsAtTime.value}",
                                style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                color = TextPrimary.copy(alpha = 0.72f),
                                maxLines = 1,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }

                // Bottom controls - positioned at very bottom.
                // Gradient made stronger on touch devices so the icon row stays readable
                // against bright content. Issue #97.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colorStops = if (isTouchDevice) arrayOf(
                                    0.0f to Color.Transparent,
                                    0.2f to Color.Black.copy(alpha = 0.5f),
                                    1.0f to Color.Black.copy(alpha = 0.85f)
                                ) else arrayOf(
                                    0.0f to Color.Transparent,
                                    0.3f to Color.Black.copy(alpha = 0.2f),
                                    1.0f to Color.Black.copy(alpha = 0.7f)
                                )
                            )
                        )
                        .padding(horizontal = if (isTouchDevice) 24.dp else 48.dp)
                        .padding(top = if (isTouchDevice) 16.dp else 24.dp, bottom = if (isTouchDevice) 32.dp else 24.dp)
                ) {
                    // Icon buttons row. On tablet we center the row and use slightly
                    // larger buttons than TV to match the shorter viewing distance and
                    // the Material minimum touch-target of 48dp. Phone keeps the compact
                    // left-aligned layout to fit vertical orientation. Issue #97.
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (isTablet) Arrangement.Center else Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Three-way sizing: phone (compact) < TV (medium) < tablet (largest).
                        // The old logic made touch devices SMALLER than TV which was
                        // backwards for tablet finger targets.
                        val smallBtn = when {
                            isTablet -> 36.dp
                            isPhone -> 24.dp
                            else -> 28.dp
                        }
                        val smallIcon = when {
                            isTablet -> 22.dp
                            isPhone -> 17.dp
                            else -> 19.dp
                        }
                        val midBtn = when {
                            isTablet -> 40.dp
                            isPhone -> 28.dp
                            else -> 30.dp
                        }
                        val midIcon = when {
                            isTablet -> 24.dp
                            isPhone -> 20.dp
                            else -> 22.dp
                        }
                        val bigBtn = when {
                            isTablet -> 48.dp
                            isPhone -> 34.dp
                            else -> 38.dp
                        }
                        val bigIcon = when {
                            isTablet -> 30.dp
                            isPhone -> 26.dp
                            else -> 28.dp
                        }
                        val gap = when {
                            isTablet -> 16.dp
                            isPhone -> 10.dp
                            else -> 14.dp
                        }
                        val wideGap = when {
                            isTablet -> 20.dp
                            isPhone -> 14.dp
                            else -> 18.dp
                        }

                        // Subtitles
                        PlayerIconButton(icon = Icons.Default.ClosedCaption, contentDescription = "${stringResource(R.string.subtitles)} / ${stringResource(R.string.audio)}",
                            focusRequester = subtitleButtonFocusRequester, size = smallBtn, iconSize = smallIcon,
                            onFocusChanged = { if (it) focusedButton = 1 },
                            onClick = {
                                subtitleMenuIndex = 0
                                subtitlePanelFocus = 0
                                val selected = latestUiState.selectedSubtitle
                                if (selected == null) {
                                    subtitleLangIndex = 0
                                    subtitleTrackIndex = 0
                                } else if (latestUiState.isAiAvailable && latestUiState.aiTargetLanguageName.isNotBlank() &&
                                    (latestUiState.isAiTranslating || latestUiState.selectedSubtitle?.let { sub ->
                                        subtitleGroups.none { (_, items) -> items.any { (_, s) -> s.id == sub.id } }
                                    } == true)) {
                                    val aiLangName = latestUiState.aiTargetLanguageName
                                    val idx = subtitleGroups.indexOfFirst { (name, _) -> name.equals(aiLangName, ignoreCase = true) }
                                    subtitleLangIndex = if (idx >= 0) idx + 1 else 0
                                    subtitleTrackIndex = 0
                                } else {
                                    val langName = getFullLanguageName(selected.lang)
                                    val idx = subtitleGroups.indexOfFirst { (name, _) -> name.equals(langName, ignoreCase = true) }
                                    subtitleLangIndex = if (idx >= 0) idx + 1 else 0
                                    subtitleTrackIndex = subtitleGroups.getOrNull(subtitleLangIndex - 1)?.second
                                        ?.indexOfFirst { (_, sub) -> sub.id == selected.id }?.coerceAtLeast(0) ?: 0
                                }
                                showSubtitleMenu = true
                                // Move focus to container so all D-pad keys go to the menu handler
                                coroutineScope.launch {
                                    delay(50)
                                    try { containerFocusRequester.requestFocus() } catch (_: Exception) {}
                                }
                            },
                            onLeftKey = { if (mediaType == MediaType.TV) nextEpisodeButtonFocusRequester.requestFocus() else aspectButtonFocusRequester.requestFocus() },
                            onRightKey = { subtitleSettingsBtnFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        Spacer(modifier = Modifier.width(gap))

                        // Subtitle settings (delay, size, vertical position)
                        PlayerIconButton(icon = Icons.Default.Tune, contentDescription = stringResource(R.string.subtitle_settings_title),
                            focusRequester = subtitleSettingsBtnFocusRequester, size = smallBtn, iconSize = smallIcon,
                            onFocusChanged = {},
                            onClick = {
                                showSubtitleSettings = !showSubtitleSettings
                                if (showSubtitleSettings) {
                                    subtitleSettingsRow = 0
                                    coroutineScope.launch {
                                        delay(50)
                                        runCatching { containerFocusRequester.requestFocus() }
                                    }
                                }
                            },
                            onLeftKey = { subtitleButtonFocusRequester.requestFocus() },
                            onRightKey = { sourceButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        Spacer(modifier = Modifier.width(gap))

                        // Sources
                        PlayerIconButton(icon = Icons.Default.Folder, contentDescription = stringResource(R.string.sources),
                            focusRequester = sourceButtonFocusRequester, size = smallBtn, iconSize = smallIcon,
                            onFocusChanged = {},
                            onClick = { showSourceMenu = true; showControls = true },
                            onLeftKey = { subtitleSettingsBtnFocusRequester.requestFocus() },
                            onRightKey = { if (isTouchDevice) playButtonFocusRequester.requestFocus() else rewindButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        if (!isTouchDevice) {
                            Spacer(modifier = Modifier.width(wideGap))

                            // Rewind 10s
                            PlayerIconButton(icon = Icons.Default.Replay10, contentDescription = stringResource(R.string.player_cd_rewind),
                                focusRequester = rewindButtonFocusRequester, size = midBtn, iconSize = midIcon,
                                onFocusChanged = {},
                                onClick = { queueControlsSeek(-10_000L) },
                                onLeftKey = { sourceButtonFocusRequester.requestFocus() },
                                onRightKey = { playButtonFocusRequester.requestFocus() },
                                onDownKey = { trackbarFocusRequester.requestFocus() })

                            Spacer(modifier = Modifier.width(gap))
                        } else {
                            Spacer(modifier = Modifier.width(wideGap))
                        }

                        // Play/Pause - center, largest
                        PlayerIconButton(icon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) stringResource(R.string.player_cd_pause) else stringResource(R.string.play),
                            focusRequester = playButtonFocusRequester, size = bigBtn, iconSize = bigIcon,
                            onFocusChanged = { if (it) focusedButton = 0 },
                            onClick = {
                                if (isCasting) {
                                    if (castManager.isRemotePlaying()) castManager.pause()
                                    else castManager.play()
                                } else {
                                    if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                                }
                            },
                            onLeftKey = { if (isTouchDevice) sourceButtonFocusRequester.requestFocus() else rewindButtonFocusRequester.requestFocus() },
                            onRightKey = { if (isTouchDevice) aspectButtonFocusRequester.requestFocus() else forwardButtonFocusRequester.requestFocus() },
                            onDownKey = { trackbarFocusRequester.requestFocus() },
                            onUpKey = { val sv = uiState.activeSkipInterval != null && !uiState.skipIntervalDismissed; if (sv) skipIntroFocusRequester.requestFocus() })

                        if (!isTouchDevice) {
                            Spacer(modifier = Modifier.width(gap))

                            // Forward 10s - own focus requester
                            PlayerIconButton(icon = Icons.Default.Forward10, contentDescription = stringResource(R.string.player_cd_forward),
                                focusRequester = forwardButtonFocusRequester, size = midBtn, iconSize = midIcon,
                                onFocusChanged = {},
                                onClick = { queueControlsSeek(10_000L) },
                                onLeftKey = { playButtonFocusRequester.requestFocus() },
                                onRightKey = { aspectButtonFocusRequester.requestFocus() },
                                onDownKey = { trackbarFocusRequester.requestFocus() })

                            Spacer(modifier = Modifier.width(wideGap))
                        } else {
                            Spacer(modifier = Modifier.width(wideGap))
                        }

                        // Aspect Ratio
                        PlayerIconButton(icon = Icons.Default.AspectRatio, contentDescription = stringResource(R.string.player_cd_aspect, aspectModeLabel),
                            focusRequester = aspectButtonFocusRequester, size = smallBtn, iconSize = smallIcon,
                            onFocusChanged = {},
                            onClick = cycleAspectRatio,
                            onLeftKey = { if (isTouchDevice) playButtonFocusRequester.requestFocus() else forwardButtonFocusRequester.requestFocus() },
                            onRightKey = {
                                when {
                                    mediaType == MediaType.TV -> nextEpisodeButtonFocusRequester.requestFocus()
                                    isTouchDevice && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> pipButtonFocusRequester.requestFocus()
                                    else -> subtitleButtonFocusRequester.requestFocus()
                                }
                            },
                            onDownKey = { trackbarFocusRequester.requestFocus() })

                        if (mediaType == MediaType.TV) {
                            Spacer(modifier = Modifier.width(gap))
                            PlayerIconButton(icon = Icons.Default.SkipNext, contentDescription = stringResource(R.string.next_episode),
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

                        // PiP button — touch devices only, just right of other buttons, Android 8+
                        if (isTouchDevice && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Spacer(modifier = Modifier.width(gap))
                            PlayerIconButton(
                                icon = Icons.Default.PictureInPicture,
                                contentDescription = stringResource(R.string.player_cd_pip),
                                focusRequester = pipButtonFocusRequester,
                                size = smallBtn, iconSize = smallIcon,
                                onFocusChanged = {},
                                onClick = { enterPipMode() },
                                onLeftKey = { if (mediaType == MediaType.TV) nextEpisodeButtonFocusRequester.requestFocus() else aspectButtonFocusRequester.requestFocus() },
                                onRightKey = { subtitleButtonFocusRequester.requestFocus() },
                                onDownKey = { trackbarFocusRequester.requestFocus() }
                            )
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
                                .pointerInput(duration, isCasting) {
                                    detectHorizontalDragGestures(
                                        onDragStart = { offset -> if (duration > 0L && trackbarWidthPx > 0) { scrubPreviewPosition = ((offset.x / trackbarWidthPx).coerceIn(0f, 1f) * duration).toLong(); isControlScrubbing = true } },
                                        onDragEnd = { if (isControlScrubbing) { if (isCasting) castManager.seekTo(scrubPreviewPosition) else if (!playerReleased) exoPlayer.seekTo(scrubPreviewPosition); isControlScrubbing = false } },
                                        onDragCancel = { if (isControlScrubbing) { if (isCasting) castManager.seekTo(scrubPreviewPosition) else if (!playerReleased) exoPlayer.seekTo(scrubPreviewPosition); isControlScrubbing = false } },
                                        onHorizontalDrag = { _, dragAmount -> if (duration > 0L && trackbarWidthPx > 0) { val delta = (dragAmount / trackbarWidthPx * duration).toLong(); scrubPreviewPosition = (scrubPreviewPosition + delta).coerceIn(0L, duration); isControlScrubbing = true } }
                                    )
                                }
                                .pointerInput(duration, isCasting) {
                                    detectTapGestures { offset -> if (duration > 0L && trackbarWidthPx > 0) { val pos = ((offset.x / trackbarWidthPx).coerceIn(0f, 1f) * duration).toLong(); if (isCasting) castManager.seekTo(pos) else if (!playerReleased) exoPlayer.seekTo(pos) } }
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
                                if (trackbarFocused) playerAccent else playerAccent.copy(alpha = 0.8f), RoundedCornerShape(3.dp)
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

        // In-player subtitle settings panel (Delay, Size, Vertical Position)
        AnimatedVisibility(
            visible = showSubtitleSettings && hasPlaybackStarted,
            enter = fadeIn(animTween(150)),
            exit = fadeOut(animTween(150)),
            modifier = Modifier.align(Alignment.Center).zIndex(8f)
        ) {
            PlayerSubtitleSettingsPanel(
                selectedRow = subtitleSettingsRow,
                syncOffsetMs = subtitleSyncOffsetMs,
                sizePct = subtitleSizePct,
                verticalPct = subtitleVerticalPct,
                onRowSelect = { subtitleSettingsRow = it },
                onOffsetDecrease = { subtitleSyncOffsetMs = (subtitleSyncOffsetMs - 100L).coerceAtLeast(-10000L) },
                onOffsetIncrease = { subtitleSyncOffsetMs = (subtitleSyncOffsetMs + 100L).coerceAtMost(10000L) },
                onSizeDecrease = { subtitleSizePct = (subtitleSizePct - 10).coerceAtLeast(50) },
                onSizeIncrease = { subtitleSizePct = (subtitleSizePct + 10).coerceAtMost(300) },
                onVerticalDecrease = { subtitleVerticalPct = (subtitleVerticalPct - 1).coerceAtLeast(0) },
                onVerticalIncrease = { subtitleVerticalPct = (subtitleVerticalPct + 1).coerceAtMost(50) }
            )
        }

        // Subtitle/Audio menu
        AnimatedVisibility(
            visible = showSubtitleMenu,
            enter = fadeIn(androidx.compose.animation.core.tween(150)),
            exit = fadeOut(androidx.compose.animation.core.tween(200))
        ) {
            SubtitleMenu(
                subtitles = uiState.subtitles,
                selectedSubtitle = uiState.selectedSubtitle,
                isAiTranslating = uiState.isAiTranslating,
                isAiAvailable = uiState.isAiAvailable,
                aiTargetLanguageName = uiState.aiTargetLanguageName,
                audioTracks = audioTracks,
                selectedAudioIndex = selectedAudioIndex,
                activeTab = subtitleMenuTab,
                focusedIndex = subtitleMenuIndex,
                subtitleGroups = subtitleGroups,
                streamSource = uiState.selectedStream?.source ?: "",
                subtitleLangIndex = subtitleLangIndex,
                subtitleTrackIndex = subtitleTrackIndex,
                subtitlePanelFocus = subtitlePanelFocus,
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
                    coroutineScope.launch {
                        delay(150)
                        try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                    }
                },
                onSelectAudio = { track ->
                    userPickedAudioForStream = true
                    applyAudioTrackSelection(exoPlayer, track, audioTracks)?.let {
                        selectedAudioIndex = it
                    }
                    showSubtitleMenu = false
                    showControls = true
                    coroutineScope.launch {
                        delay(150)
                        try { subtitleButtonFocusRequester.requestFocus() } catch (_: Exception) {}
                    }
                },
                onToggleAi = { viewModel.activateAiSubtitle() },
                onClose = {
                    showSubtitleMenu = false
                    showControls = true
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
            addonOrderedIds = uiState.addonOrderedIds,
            title = uiState.title,
            subtitle = if (seasonNumber != null && episodeNumber != null) {
                "S$seasonNumber E$episodeNumber"
            } else {
                ""
            },
            onFocusedStream = { stream ->
                viewModel.prewarmStreamsAround(stream, uiState.streams)
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
                viewModel.selectStream(stream, exoPlayer.currentPosition)
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

        // Post-episode "Up Next" prompt (issue #86). Shown when a TV episode ends and
        // autoPlayNext is enabled. 10-second countdown auto-advances, or the user can
        // hit Enter to continue immediately or Back/Escape/Close to cancel and stay on
        // the ended frame. Placed after StreamSelector so it renders above the player
        // but below any error/source overlays that might appear simultaneously.
        NextEpisodeOverlay(
            isVisible = showNextEpisodePrompt,
            showTitle = uiState.title,
            // We only know the current episode's title at this point; fetching the next
            // episode's metadata would require an extra TMDB round-trip during playback.
            // Fall back to a generic "Episode N" label — the show title, S/E number, and
            // backdrop image still give users enough context to decide Continue/Cancel.
            episodeTitle = "Episode $pendingNextEpisode",
            seasonNumber = pendingNextSeason,
            episodeNumber = pendingNextEpisode,
            episodeImage = uiState.backdropUrl,
            countdownSeconds = 10,
            focusedButtonOverride = nextEpisodePromptButton,
            onFocusedButtonChange = { nextEpisodePromptButton = it },
            onPlayNext = {
                showNextEpisodePrompt = false
                onPlayNext(
                    pendingNextSeason,
                    pendingNextEpisode,
                    pendingNextAddonId,
                    pendingNextSourceName,
                    pendingNextBingeGroup
                )
            },
            onCancel = {
                showNextEpisodePrompt = false
                // Stay on the ended frame — user can hit Back to leave the player.
            }
        )

        // Volume indicator
        AnimatedVisibility(
            visible = showVolumeIndicator,
            enter = fadeIn(androidx.compose.animation.core.tween(150)),
            exit = fadeOut(androidx.compose.animation.core.tween(200)),
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
                    contentDescription = stringResource(R.string.player_cd_volume),
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
                            .background(playerAccent, RoundedCornerShape(4.dp))
                            .align(Alignment.BottomCenter)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isMuted) stringResource(R.string.player_muted) else "${currentVolume * 100 / maxVolume}%",
                    style = ArflixTypography.caption,
                    color = Color.White
                )
            }
        }

        // Aspect ratio indicator - brief center popup
        AnimatedVisibility(
            visible = showAspectIndicator,
            enter = fadeIn(androidx.compose.animation.core.tween(150)),
            exit = fadeOut(androidx.compose.animation.core.tween(200)),
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

        // Skip overlay — floats near the bottom while the user spams ±10s.
        // Now sits ~48dp from the bottom (was 120dp, which wasted vertical
        // space and felt too detached). Time labels flanking the progress
        // bar show exactly how far along the user is.
        AnimatedVisibility(
            visible = showSkipOverlay,
            enter = fadeIn(androidx.compose.animation.core.tween(150)),
            exit = fadeOut(androidx.compose.animation.core.tween(200)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(0.72f)
                    .background(Color.Black.copy(alpha = 0.55f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                Text(
                    text = if (skipAmount >= 0) "+${skipAmount}s" else "${skipAmount}s",
                    style = ArflixTypography.sectionTitle.copy(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 8f
                        )
                    ),
                    color = Color.White
                )

                if (duration > 0L) {
                    val previewPosition = skipPreviewPosition
                        .takeIf { it > 0L }
                        ?: currentPosition
                    val previewProgress = (previewPosition.toFloat() / duration.toFloat()).coerceIn(0f, 1f)

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = formatTime(previewPosition),
                            style = ArflixTypography.caption.copy(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                            ),
                            color = Color.White,
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(5.dp)
                                .background(Color.White.copy(alpha = 0.25f), RoundedCornerShape(3.dp))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(previewProgress)
                                    .height(5.dp)
                                    .background(Color.White, RoundedCornerShape(3.dp))
                            )
                        }
                        Text(
                            text = formatTime(duration),
                            style = ArflixTypography.caption.copy(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                            ),
                            color = Color.White.copy(alpha = 0.75f),
                        )
                    }
                }
            }
        }

        // Error modal — friendly setup guide for no-addons, red error for actual playback failures
        AnimatedVisibility(
            visible = uiState.error != null,
            enter = fadeIn(androidx.compose.animation.core.tween(150)),
            exit = fadeOut(androidx.compose.animation.core.tween(200))
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
                            contentDescription = if (isSetup) stringResource(R.string.player_cd_setup) else stringResource(R.string.player_cd_error),
                            tint = accentColor,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = if (isSetup) stringResource(R.string.player_addon_setup_required) else stringResource(R.string.player_playback_error),
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = uiState.error ?: stringResource(R.string.player_error_generic),
                        style = ArflixTypography.body,
                        color = TextSecondary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (isSetup) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.no_results),
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
                                text = stringResource(R.string.retry).uppercase(),
                                icon = Icons.Default.Refresh,
                                isFocused = errorModalFocusIndex == 0,
                                isPrimary = true,
                                onClick = { viewModel.retry() }
                            )
                        }
                        ErrorButton(
                            text = stringResource(R.string.back).uppercase(),
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
    val btnAccent = LocalAccentColorOverride.current ?: Color.White
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
                color = if (focused) btnAccent else Color.Transparent,
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
private fun PulsingLogo(
    logoUrl: String?,
    title: String,
    modifier: Modifier = Modifier,
    progress: Float? = null,
    phaseLabel: String? = null
) {
    val infiniteTransition = rememberInfiniteTransition(label = "heartbeat")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1500
                // Two quick beats followed by a short rest (heartbeat).
                1.0f at 0
                1.08f at 160 using FastOutSlowInEasing
                1.02f at 280 using FastOutSlowInEasing
                1.12f at 420 using FastOutSlowInEasing
                1.0f at 620 using FastOutSlowInEasing
                1.0f at 1500
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "heartbeatScale"
    )

    // Smoothly interpolate discrete progress jumps from the ViewModel so the
    // ring doesn't snap between values. Null = indeterminate (no ring shown).
    val animatedProgress by animateFloatAsState(
        targetValue = progress?.coerceIn(0f, 1f) ?: 0f,
        animationSpec = animTween(durationMillis = 400, easing = FastOutSlowInEasing),
        label = "progressFraction"
    )

    Column(
        modifier = modifier.padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(196.dp),
            contentAlignment = Alignment.Center
        ) {
            if (progress != null) {
                // Track + arc progress ring — renders even at 0% so users see
                // the loader frame immediately rather than a bare logo.
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidthPx = 4.dp.toPx()
                    val diameter = size.minDimension - strokeWidthPx
                    val topLeft = Offset(
                        (size.width - diameter) / 2f,
                        (size.height - diameter) / 2f
                    )
                    val arcSize = Size(diameter, diameter)
                    // Track
                    drawArc(
                        color = Color.White.copy(alpha = 0.15f),
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        topLeft = topLeft,
                        size = arcSize,
                        style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                    )
                    // Filled arc
                    drawArc(
                        color = Color.White,
                        startAngle = -90f,
                        sweepAngle = 360f * animatedProgress,
                        useCenter = false,
                        topLeft = topLeft,
                        size = arcSize,
                        style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                    )
                }
            }
            Box(
                modifier = Modifier.graphicsLayer { scaleX = scale; scaleY = scale },
                contentAlignment = Alignment.Center
            ) {
                if (!logoUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = logoUrl, contentDescription = title, contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxWidth(0.76f).height(152.dp)
                    )
                }
            }
        }

        if (progress != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${(animatedProgress * 100f).toInt()}%",
                style = ArflixTypography.sectionTitle.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
                color = Color.White
            )
            if (!phaseLabel.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = phaseLabel,
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
            }
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
    val btnAccent = LocalAccentColorOverride.current ?: Color.White
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
                    isPrimary -> btnAccent.copy(alpha = 0.5f)
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
                    tint = if (isFocused) Color.Black else if (isPrimary) btnAccent else TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = text,
                style = ArflixTypography.button,
                color = if (isFocused) Color.Black else if (isPrimary) btnAccent else TextSecondary
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
 * Apply an audio-track selection to the player defensively.
 *
 * The stored [AudioTrackInfo] captures `groupIndex` / `trackIndex` at the moment the
 * `onTracksChanged` listener fires. Between that moment and the user actually picking
 * a track from the menu, the player may have re-prepared (e.g. adaptive stream switch,
 * source reselection, MediaItem rebuild for a new external subtitle), and the current
 * `exoPlayer.currentTracks.groups` layout may no longer match those indices. Calling
 * `TrackSelectionOverride(group, trackIndex)` with a stale `trackIndex >= group.length`
 * throws `IllegalArgumentException` inside Media3 and crashes the player.
 *
 * This helper wraps the selection in try/catch, validates every index before use, and
 * clears any existing audio override before applying the new one so stale overrides
 * from prior selections don't pin the player to a no-longer-present track. Fixes #89.
 *
 * @return the index in [audioTracks] that was actually applied, or `null` if the
 *         selection could not be applied (caller should leave the previous index).
 */
private fun applyAudioTrackSelection(
    exoPlayer: ExoPlayer,
    track: AudioTrackInfo,
    audioTracks: List<AudioTrackInfo>
): Int? {
    return try {
        val params = exoPlayer.trackSelectionParameters.buildUpon()
            .clearOverridesOfType(C.TRACK_TYPE_AUDIO)
            .setPreferredAudioLanguage(track.language)

        val trackGroups = exoPlayer.currentTracks.groups
        val groupInRange = track.groupIndex in trackGroups.indices
        if (groupInRange) {
            val group = trackGroups[track.groupIndex]
            val isAudioGroup = group.type == C.TRACK_TYPE_AUDIO
            val trackInRange = track.trackIndex in 0 until group.length
            if (isAudioGroup && trackInRange) {
                params.setOverrideForType(
                    TrackSelectionOverride(
                        group.mediaTrackGroup,
                        track.trackIndex
                    )
                )
            }
            // If the group is stale we still fall through and apply the
            // preferredAudioLanguage hint above — Media3 will pick the closest
            // matching track on its own rather than crashing.
        }

        exoPlayer.trackSelectionParameters = params.build()

        audioTracks.indexOfFirst {
            it.groupIndex == track.groupIndex && it.trackIndex == track.trackIndex
        }.takeIf { it >= 0 } ?: track.index
    } catch (e: IllegalArgumentException) {
        // Stale track/group index after a player re-prepare. Leave the current
        // selection alone instead of crashing; user can retry the menu.
        android.util.Log.w("PlayerScreen", "applyAudioTrackSelection rejected stale index: ${e.message}")
        null
    } catch (e: IllegalStateException) {
        // Player released or in an invalid state.
        android.util.Log.w("PlayerScreen", "applyAudioTrackSelection on invalid player: ${e.message}")
        null
    } catch (e: Exception) {
        android.util.Log.e("PlayerScreen", "applyAudioTrackSelection unexpected error", e)
        null
    }
}

/**
 * Find the audio track that best matches the user's preferred audio language.
 *
 * Matching is done on the canonical language name (so "pl", "pol" and "polish" all
 * resolve to the same language) and falls back to the track label, which is where
 * Polish releases commonly carry the language for tracks that ship with a missing or
 * non-standard language tag (e.g. "Lektor PL", "Dubbing", "Polski"). Returns the index
 * into [audioTracks] of the first match, or `null` when no track matches.
 */
private fun findPreferredAudioTrackIndex(
    audioTracks: List<AudioTrackInfo>,
    preferredCode: String
): Int? {
    val prefName = getFullLanguageName(preferredCode)
    if (prefName == "Unknown") return null
    val labelHints = nativeAudioLanguageHints(preferredCode) + prefName.lowercase()
    val index = audioTracks.indexOfFirst { track ->
        val trackLangName = getFullLanguageName(track.language)
        if (trackLangName != "Unknown" && trackLangName.equals(prefName, ignoreCase = true)) {
            return@indexOfFirst true
        }
        val label = track.label?.lowercase()?.trim().orEmpty()
        label.isNotBlank() && labelHints.any { hint -> hint.isNotBlank() && label.contains(hint) }
    }
    return index.takeIf { it >= 0 }
}

/**
 * Common label hints (native names / colloquial terms) used to recognise an audio
 * track's language when its language tag is missing or non-standard. Kept conservative
 * to avoid false positives; covers the languages most affected by untagged tracks.
 */
private fun nativeAudioLanguageHints(preferredCode: String): List<String> {
    return when (getFullLanguageName(preferredCode)) {
        "Polish" -> listOf("polski", "polskie", "polsku", "lektor", "dubbing pl")
        "Russian" -> listOf("русский", "русская", "rus")
        "Ukrainian" -> listOf("українська", "ukr")
        "German" -> listOf("deutsch")
        "French" -> listOf("français", "francais")
        "Spanish" -> listOf("español", "espanol", "castellano")
        "Italian" -> listOf("italiano")
        "Portuguese" -> listOf("português", "portugues")
        "Czech" -> listOf("čeština", "cesky", "dabing")
        else -> emptyList()
    }
}

/**
 * True if the subtitle MIME type is an image/bitmap format (PGS, VOBSUB, DVB).
 * These tracks render as images and carry no text, so they can't be AI-translated.
 */
private fun isBitmapSubtitleMime(mimeType: String?): Boolean {
    val mime = mimeType?.lowercase()?.trim() ?: return false
    return mime == MimeTypes.APPLICATION_PGS ||      // application/pgs (Blu-ray)
        mime == MimeTypes.APPLICATION_VOBSUB ||      // application/vobsub (DVD)
        mime == MimeTypes.APPLICATION_DVBSUBS ||     // application/dvbsubs
        mime.contains("pgs") || mime.contains("vobsub") || mime.contains("dvbsub")
}

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

@Composable
private fun rememberPlayerClockFormat(): String {
    val context = LocalContext.current
    var resolvedFormat by remember { mutableStateOf("24h") }

    LaunchedEffect(context) {
        runCatching {
            val prefs = context.settingsDataStore.data.first()
            val saved = prefs.asMap().entries
                .firstOrNull { (key, _) -> key.name.endsWith("_clock_format") }
                ?.value as? String
            resolvedFormat = saved ?: "24h"
        }
    }

    return resolvedFormat
}

private fun formatPlayerClockTime(timestampMs: Long, clockFormat: String): String {
    val pattern = when (clockFormat) {
        "12h" -> "h:mm a"
        else -> "HH:mm"
    }
    val sdf = java.text.SimpleDateFormat(pattern, java.util.Locale.getDefault())
    return sdf.format(java.util.Date(timestampMs))
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
    isAiTranslating: Boolean = false,
    isAiAvailable: Boolean = false,
    aiTargetLanguageName: String = "",
    audioTracks: List<AudioTrackInfo>,
    selectedAudioIndex: Int,
    activeTab: Int,
    focusedIndex: Int,
    subtitleGroups: List<Pair<String, List<Pair<Int, Subtitle>>>>,
    subtitleLangIndex: Int,
    subtitleTrackIndex: Int,
    subtitlePanelFocus: Int,
    streamSource: String = "",
    onTabChanged: (Int) -> Unit,
    onSelectSubtitle: (Int) -> Unit,
    onSelectAudio: (AudioTrackInfo) -> Unit,
    onToggleAi: () -> Unit = {},
    onClose: () -> Unit
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val langListState = rememberLazyListState()
    val trackListState = rememberLazyListState()
    val audioListState = rememberLazyListState()

    if (!isMobile) {
        // ── TV layout: two-panel (language list | track list) + Audio tab ─
        LaunchedEffect(subtitleLangIndex) {
            langListState.animateScrollToItem(subtitleLangIndex.coerceAtLeast(0))
        }
        LaunchedEffect(subtitleTrackIndex, subtitlePanelFocus) {
            if (subtitlePanelFocus == 1 && subtitleTrackIndex >= 0) {
                trackListState.animateScrollToItem(subtitleTrackIndex)
            }
        }
        LaunchedEffect(focusedIndex, activeTab) {
            if (activeTab == 1 && focusedIndex >= 0) {
                audioListState.animateScrollToItem(focusedIndex)
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
                    .width(480.dp)
                    .padding(end = 32.dp)
                    .background(
                        Color.Black.copy(alpha = 0.85f),
                        RoundedCornerShape(16.dp)
                    )
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .clickable(enabled = false) {}
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TabButton(
                        text = stringResource(R.string.subtitles),
                        isSelected = activeTab == 0,
                        onClick = { onTabChanged(0) }
                    )
                    TabButton(
                        text = stringResource(R.string.audio),
                        isSelected = activeTab == 1,
                        onClick = { onTabChanged(1) }
                    )
                }

                if (streamSource.isNotBlank()) {
                    Text(
                        text = streamSource,
                        style = ArflixTypography.caption.copy(fontSize = 11.sp),
                        color = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    )
                }

                Box(modifier = Modifier.height(300.dp)) {
                    if (activeTab == 0) {
                        // Two-panel layout: language list (left) | tracks for selected language (right)
                        Row(modifier = Modifier.fillMaxSize()) {
                            // Left panel: language list
                            LazyColumn(
                                state = langListState,
                                modifier = Modifier
                                    .width(150.dp)
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                item {
                                    LangPanelItem(
                                        name = "Off",
                                        count = 0,
                                        isFocused = subtitlePanelFocus == 0 && subtitleLangIndex == 0,
                                        isActivePanel = subtitleLangIndex == 0,
                                        isSelected = selectedSubtitle == null
                                    )
                                }
                                itemsIndexed(subtitleGroups) { idx, (langName, items) ->
                                    LangPanelItem(
                                        name = langName,
                                        count = items.size,
                                        isFocused = subtitlePanelFocus == 0 && subtitleLangIndex == idx + 1,
                                        isActivePanel = subtitleLangIndex == idx + 1,
                                        isSelected = if (isAiTranslating && aiTargetLanguageName.isNotBlank() &&
                                            langName.equals(aiTargetLanguageName, ignoreCase = true)) {
                                            true
                                        } else {
                                            selectedSubtitle != null &&
                                                items.any { (_, sub) -> sub.id == selectedSubtitle.id }
                                        }
                                    )
                                }
                            }

                            // Vertical divider
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight()
                                    .padding(vertical = 4.dp)
                                    .background(Color.White.copy(alpha = 0.1f))
                            )

                            // Right panel: tracks for selected language
                            val selectedGroup = subtitleGroups.getOrNull(subtitleLangIndex - 1)
                            if (selectedGroup == null) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "—",
                                        style = ArflixTypography.caption,
                                        color = TextSecondary.copy(alpha = 0.3f)
                                    )
                                }
                            } else {
                                val isAiGroup = isAiAvailable && aiTargetLanguageName.isNotBlank() &&
                                    selectedGroup.first.equals(aiTargetLanguageName, ignoreCase = true)
                                LazyColumn(
                                    state = trackListState,
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .padding(start = 8.dp),
                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    if (isAiGroup) {
                                        item {
                                            TrackMenuItem(
                                                label = aiTargetLanguageName,
                                                subtitle = "AI",
                                                subtitleDetail = null,
                                                isSelected = isAiTranslating,
                                                isFocused = subtitlePanelFocus == 1 && subtitleTrackIndex == 0,
                                                onClick = { /* D-pad only */ }
                                            )
                                        }
                                    }
                                    itemsIndexed(selectedGroup.second) { idx, (_, subtitle) ->
                                        val score = subtitleMatchScore(streamSource, subtitle)
                                        val langName = getFullLanguageName(subtitle.lang)
                                        val mainLabel = if (score > 0) "$langName ($score%)" else langName
                                        val badge: String?
                                        val detail: String?
                                        if (subtitle.isEmbedded && subtitle.url.isBlank()) {
                                            val langFullName = getFullLanguageName(subtitle.lang)
                                            val trackLabel = subtitle.label.takeIf { it.isNotBlank() &&
                                                !it.equals(langFullName, ignoreCase = true) }
                                            badge = if (trackLabel != null) "Built-in · $trackLabel" else "Built-in"
                                            detail = null
                                        } else {
                                            badge = subtitle.provider.ifBlank { null }
                                            detail = subtitle.id
                                                .replace(PlayerScreenRegexes.BRACKET_REGEX, "").trim()
                                                .ifBlank { subtitle.id }
                                                .ifBlank { null }
                                        }
                                        val itemIdx = if (isAiGroup) idx + 1 else idx
                                        TrackMenuItem(
                                            label = mainLabel,
                                            subtitle = badge,
                                            subtitleDetail = detail,
                                            isSelected = !isAiTranslating && selectedSubtitle?.id == subtitle.id,
                                            isFocused = subtitlePanelFocus == 1 && subtitleTrackIndex == itemIdx,
                                            onClick = { /* D-pad only */ }
                                        )
                                    }
                                }
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
                                        text = stringResource(R.string.no_audio_tracks),
                                        style = ArflixTypography.body,
                                        color = TextSecondary,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            } else {
                                itemsIndexed(audioTracks, key = { _, track -> audioTrackKey(track) }) { index, track ->
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${stringResource(R.string.subtitles)} • ${stringResource(R.string.back)} • ${stringResource(R.string.close)}",
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
                            contentDescription = stringResource(R.string.close),
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
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                                    .height(1.dp)
                                    .background(Color.White.copy(alpha = 0.06f))
                            )
                        }

                        // Grouped by language
                        subtitleGroups.forEach { (langName, indexedSubs) ->
                            val isAiGroup = isAiAvailable && aiTargetLanguageName.isNotBlank() &&
                                langName.equals(aiTargetLanguageName, ignoreCase = true)
                            item(key = "mobile_header_$langName") {
                                Text(
                                    text = langName.uppercase(),
                                    style = ArflixTypography.caption.copy(
                                        fontSize = 10.sp,
                                        letterSpacing = 1.sp
                                    ),
                                    color = TextSecondary.copy(alpha = 0.45f),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, top = 8.dp, bottom = 2.dp)
                                )
                            }
                            if (isAiGroup) {
                                item(key = "mobile_ai_item") {
                                    MobileTrackItem(
                                        name = aiTargetLanguageName,
                                        description = "AI",
                                        isSelected = isAiTranslating,
                                        onClick = { onToggleAi(); onClose() }
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp)
                                            .height(1.dp)
                                            .background(Color.White.copy(alpha = 0.06f))
                                    )
                                }
                            }
                            indexedSubs.forEach { (originalIndex, sub) ->
                                item(key = "mobile_${sub.id}") {
                                    val score = subtitleMatchScore(streamSource, sub)
                                    val langFullName = getFullLanguageName(sub.lang)
                                    val displayName = if (score > 0) "$langFullName ($score%)" else langFullName
                                    val description = when {
                                        sub.isEmbedded && sub.url.isBlank() -> {
                                            val trackLabel = sub.label.takeIf { it.isNotBlank() &&
                                                !it.equals(langFullName, ignoreCase = true) }
                                            if (trackLabel != null) "Built-in · $trackLabel" else "Built-in"
                                        }
                                        sub.provider.isNotBlank() -> sub.provider
                                        else -> null
                                    }
                                    MobileTrackItem(
                                        name = displayName,
                                        description = description,
                                        isSelected = !isAiTranslating && selectedSubtitle?.id == sub.id,
                                        onClick = { onSelectSubtitle(originalIndex + 1) }
                                    )
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
                    } else {
                        // Audio tab
                        if (audioTracks.isEmpty()) {
                            item {
                                Text(
                                    text = stringResource(R.string.no_audio_tracks),
                                    style = ArflixTypography.body.copy(fontSize = 14.sp),
                                    color = TextSecondary,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        } else {
                            itemsIndexed(audioTracks, key = { _, track -> audioTrackKey(track) }) { index, track ->
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
    onClick: () -> Unit,
    subtitleDetail: String? = null
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
                    style = ArflixTypography.caption.copy(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = if (isFocused) Color.Black.copy(alpha = 0.85f) else Color.White.copy(alpha = 0.85f)
                )
            }
            if (subtitleDetail != null) {
                Text(
                    text = subtitleDetail,
                    style = ArflixTypography.caption.copy(fontSize = 10.sp),
                    color = if (isFocused) Color.Black.copy(alpha = 0.55f) else Color.White.copy(alpha = 0.5f)
                )
            }
        }

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.selected),
                tint = if (isFocused) Color.Black else Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun LangPanelItem(
    name: String,
    count: Int,
    isFocused: Boolean,
    isActivePanel: Boolean,
    isSelected: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                when {
                    isFocused -> Color.White
                    isActivePanel -> Color.White.copy(alpha = 0.12f)
                    else -> Color.Transparent
                },
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = ArflixTypography.body.copy(fontSize = 13.sp),
            color = if (isFocused) Color.Black else Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        if (count > 0) {
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .background(
                        if (isFocused) Color.Black.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.15f),
                        RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 5.dp, vertical = 1.dp)
            ) {
                Text(
                    text = "$count",
                    style = ArflixTypography.caption.copy(fontSize = 10.sp),
                    color = if (isFocused) Color.Black else Color.White
                )
            }
        } else if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = if (isFocused) Color.Black else Color.White,
                modifier = Modifier.size(14.dp)
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
                contentDescription = stringResource(R.string.selected),
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

private fun audioTrackKey(track: AudioTrackInfo): String {
    return listOf(
        track.index,
        track.groupIndex,
        track.trackIndex,
        track.language.orEmpty(),
        track.label.orEmpty(),
        track.channelCount,
        track.sampleRate,
        track.codec.orEmpty(),
    ).joinToString(separator = "|")
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

private data class PlaybackBufferProfile(
    val minBufferMs: Int,
    val maxBufferMs: Int,
    val bufferForPlaybackMs: Int,
    val bufferForPlaybackAfterRebufferMs: Int,
    val targetBufferBytes: Int,
    val backBufferMs: Int
)

private fun buildPlaybackBufferProfile(
    memoryClassMb: Int,
    isLowRamDevice: Boolean,
    isTvDevice: Boolean
): PlaybackBufferProfile {
    val heapMb = memoryClassMb.coerceAtLeast(256)
    val targetMb = when {
        isLowRamDevice || heapMb <= 256 -> 64
        heapMb <= 384 -> 96
        heapMb <= 512 -> 128
        heapMb <= 768 -> 192
        else -> 256
    }
    val minBufferMs = when {
        isLowRamDevice || heapMb <= 256 -> 18_000
        heapMb <= 384 -> 22_000
        heapMb <= 512 -> 28_000
        else -> 32_000
    }
    val maxBufferMs = when {
        isLowRamDevice || heapMb <= 256 -> 60_000
        heapMb <= 384 -> 80_000
        heapMb <= 512 -> 105_000
        else -> 135_000
    }
    val startBufferMs = when {
        isTvDevice && (isLowRamDevice || heapMb <= 384) -> 550
        isTvDevice -> 450
        else -> 350
    }
    val rebufferMs = when {
        isLowRamDevice || heapMb <= 256 -> 3_000
        heapMb <= 384 -> 3_500
        else -> 4_000
    }
    val backBufferMs = when {
        isLowRamDevice || heapMb <= 256 -> 2_000
        heapMb <= 384 -> 3_000
        else -> 5_000
    }

    return PlaybackBufferProfile(
        minBufferMs = minBufferMs,
        maxBufferMs = maxBufferMs,
        bufferForPlaybackMs = startBufferMs,
        bufferForPlaybackAfterRebufferMs = rebufferMs,
        targetBufferBytes = targetMb * 1024 * 1024,
        backBufferMs = backBufferMs
    )
}

private fun estimateInitialStartupTimeoutMs(
    stream: StreamSource?,
    isManualSelection: Boolean
): Long {
    var timeoutMs = if (isManualSelection) 12_000L else 6_000L
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
        timeoutMs = timeoutMs.coerceAtLeast(if (isManualSelection) 14_000L else 7_500L)
    }
    if (haystack.contains("remux") || haystack.contains("dolby vision") || haystack.contains(" dovi")) {
        timeoutMs = timeoutMs.coerceAtLeast(if (isManualSelection) 16_000L else 9_000L)
    }

    timeoutMs = when {
        sizeBytes >= 60L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(if (isManualSelection) 22_000L else 12_000L)
        sizeBytes >= 40L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(if (isManualSelection) 20_000L else 11_000L)
        sizeBytes >= 30L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(if (isManualSelection) 18_000L else 10_000L)
        sizeBytes >= 20L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(if (isManualSelection) 16_000L else 8_500L)
        sizeBytes >= 10L * 1024 * 1024 * 1024 -> timeoutMs.coerceAtLeast(if (isManualSelection) 14_000L else 7_500L)
        else -> timeoutMs
    }

    return timeoutMs.coerceAtMost(if (isManualSelection) 24_000L else 12_000L)
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
        .replace(PlayerScreenRegexes.MULTI_SPACE_REGEX, " ")
        .trim()

    val match = PlayerScreenRegexes.SIZE_REGEX.find(normalized) ?: return 0L
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

private const val PLAYER_SCREEN_DIAGNOSTICS = true

private fun playbackStartupDiag(message: String) {
    if (PLAYER_SCREEN_DIAGNOSTICS) {
        System.err.println("[PlaybackStartup] $message")
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

@Composable
private fun PlayerMetadataChrome(
    uiState: PlayerUiState,
    mediaType: MediaType,
    seasonNumber: Int?,
    episodeNumber: Int?,
    isPaused: Boolean,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    val displayTitle = when {
        mediaType == MediaType.TV && !uiState.episodeTitle.isNullOrBlank() -> uiState.episodeTitle
        else -> uiState.title
    }
    val metaLine = buildPlaybackMetaLine(uiState, mediaType, seasonNumber, episodeNumber)
    val overview = uiState.overview?.trim().orEmpty()
    val logoHeight = 44.dp
    val logoWidth = 230.dp
    val chromeHeight = when {
        isPaused && overview.isNotBlank() -> 138.dp
        isPaused -> 104.dp
        else -> 86.dp
    }

    Row(
        modifier = modifier.widthIn(max = if (isPaused) 620.dp else 520.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .width(2.dp)
                .height(chromeHeight)
                .background(accentColor.copy(alpha = if (isPaused) 0.78f else 0.46f))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.widthIn(max = if (isPaused) 560.dp else 470.dp),
            verticalArrangement = Arrangement.spacedBy(if (isPaused) 5.dp else 4.dp)
        ) {
            if (!uiState.logoUrl.isNullOrBlank()) {
                AsyncImage(
                    model = uiState.logoUrl,
                    contentDescription = uiState.title,
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(logoWidth)
                        .height(logoHeight)
                )
            } else if (displayTitle.isNotBlank()) {
                Text(
                    text = displayTitle,
                    style = ArflixTypography.sectionTitle.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (!uiState.logoUrl.isNullOrBlank() && displayTitle.isNotBlank()) {
                Text(
                    text = displayTitle,
                    style = ArflixTypography.sectionTitle.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (metaLine.isNotBlank()) {
                Text(
                    text = metaLine,
                    style = ArflixTypography.caption.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = TextPrimary.copy(alpha = 0.78f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (isPaused && overview.isNotBlank()) {
                Text(
                    text = overview,
                    style = ArflixTypography.body.copy(fontSize = 13.sp),
                    color = TextPrimary.copy(alpha = 0.76f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = 540.dp)
                )
            }
        }
    }
}

private fun buildPlaybackMetaLine(
    uiState: PlayerUiState,
    mediaType: MediaType,
    seasonNumber: Int?,
    episodeNumber: Int?
): String {
    val parts = mutableListOf<String>()
    if (mediaType == MediaType.TV) {
        seasonNumber?.let { parts.add("Season $it") }
        episodeNumber?.let { parts.add("Episode $it") }
    } else {
        uiState.releaseYear?.trim()?.takeIf { it.isNotBlank() }?.let { parts.add(it) }
    }

    uiState.selectedStream?.let { stream ->
        stream.quality.trim().takeIf { it.isNotBlank() }?.let { parts.add(it) }
        val size = stream.size.trim().takeIf { it.isNotBlank() }
            ?: stream.sizeBytes?.let { formatFileSize(it) }
        size?.let { parts.add(it) }
    }

    return parts.distinct().joinToString(" | ")
}

private fun subtitleMatchScore(streamSource: String, subtitle: Subtitle): Int {
    if (subtitle.isEmbedded) return 100
    return weightedSubtitleScore(streamSource, subtitle.id)
}

private object PlayerScreenRegexes {
    val BRACKET_REGEX = Regex("^\\[[^]]+]")
    val MULTI_SPACE_REGEX = Regex("\\s+")
    val SIZE_REGEX = Regex("""(\d+(?:\.\d+)?)\s*(TB|GB|MB|KB)""")
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PlayerSubtitleSettingsPanel(
    selectedRow: Int,
    syncOffsetMs: Long,
    sizePct: Int,
    verticalPct: Int,
    onRowSelect: (Int) -> Unit,
    onOffsetDecrease: () -> Unit,
    onOffsetIncrease: () -> Unit,
    onSizeDecrease: () -> Unit,
    onSizeIncrease: () -> Unit,
    onVerticalDecrease: () -> Unit,
    onVerticalIncrease: () -> Unit
) {
    val accent = LocalAccentColorOverride.current ?: Color.White

    val absMs = if (syncOffsetMs < 0) -syncOffsetMs else syncOffsetMs
    val offsetLabel = if (syncOffsetMs == 0L) "0.0s"
    else "${if (syncOffsetMs > 0) "+" else "-"}${absMs / 1000}.${(absMs % 1000) / 100}s"

    Column(
        modifier = Modifier
            .width(280.dp)
            .background(Color.Black.copy(alpha = 0.92f), RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(R.string.subtitle_settings_title),
            style = ArflixTypography.sectionTitle.copy(fontSize = 16.sp),
            color = Color.White,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        PlayerSubtitleSettingRow(
            label = stringResource(R.string.subtitle_delay),
            value = offsetLabel,
            selected = selectedRow == 0,
            accent = accent,
            onClick = { onRowSelect(0) },
            onDecrease = onOffsetDecrease,
            onIncrease = onOffsetIncrease
        )
        PlayerSubtitleSettingRow(
            label = stringResource(R.string.subtitle_size_label),
            value = "${sizePct}%",
            selected = selectedRow == 1,
            accent = accent,
            onClick = { onRowSelect(1) },
            onDecrease = onSizeDecrease,
            onIncrease = onSizeIncrease
        )
        PlayerSubtitleSettingRow(
            label = stringResource(R.string.subtitle_vertical_position),
            value = "${verticalPct}%",
            selected = selectedRow == 2,
            accent = accent,
            onClick = { onRowSelect(2) },
            onDecrease = onVerticalDecrease,
            onIncrease = onVerticalIncrease
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PlayerSubtitleSettingRow(
    label: String,
    value: String,
    selected: Boolean,
    accent: Color,
    onClick: () -> Unit,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    val rowBg = if (selected) Color.White.copy(alpha = 0.08f) else Color.Transparent
    val valueColor = if (selected) accent else Color.White

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(rowBg, RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = ArflixTypography.label.copy(fontWeight = FontWeight.Normal),
            color = Color.White.copy(alpha = 0.55f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDecrease
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "−",
                    style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
            Text(
                text = value,
                style = ArflixTypography.body.copy(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                color = valueColor
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onIncrease
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
        }
    }
}

private fun guessCastMimeType(url: String): String = when {
    url.contains(".m3u8", ignoreCase = true) -> "application/x-mpegURL"
    url.contains(".mpd", ignoreCase = true)  -> "application/dash+xml"
    else                                     -> "video/mp4"
}
