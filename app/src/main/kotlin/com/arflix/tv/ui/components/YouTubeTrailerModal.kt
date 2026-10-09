package com.arflix.tv.ui.components

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.SystemClock
import android.view.ViewGroup
import android.view.View
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.arflix.tv.R
import com.arflix.tv.ui.screens.player.common.PlayerSystemBarsEffect
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.findActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.delay

/**
 * Height of the TV control strip below the video.
 *
 * The strip is reserved permanently, even while it looks empty: the modal
 * background is black, so an empty strip is invisible, and the video never has
 * to change size when our bar appears or hides.
 */
private val tvStripHeight = 64.dp

/** Space reserved for our close button on mobile. Never sits over the player. */
private val mobileCloseSlot = 72.dp

private fun formatTime(seconds: Float): String {
    val totalSec = seconds.toInt().coerceAtLeast(0)
    val minutes = totalSec / 60
    val remSec = totalSec % 60
    return "%02d:%02d".format(minutes, remSec)
}

private enum class IndicatorType {
    PLAY, PAUSE, SEEK_BACK, SEEK_FORWARD
}

private data class TransientIndicator(
    val type: IndicatorType,
    val text: String,
    val icon: ImageVector,
    val timestamp: Long = SystemClock.uptimeMillis()
)

/**
 * The embedded player itself. Nothing is ever drawn on top of this.
 *
 * YouTube owns its controls on every device. TV shortcuts are handled by a
 * separate focus target; entering the WebView leaves its key handling intact.
 */
@Composable
internal fun TrailerPlayerSurface(
    youtubeKey: String,
    modifier: Modifier = Modifier,
    showControls: Boolean = true,
    visible: Boolean = true,
    onViewReady: (YouTubePlayerView) -> Unit = {},
    onReady: (YouTubePlayer) -> Boolean = { true },
    onStateChange: (PlayerConstants.PlayerState) -> Unit = {},
    onSecond: (Float) -> Unit = {},
    onDuration: (Float) -> Unit = {},
    onError: () -> Unit = {},
    onReleased: () -> Unit = {}
) {
    val onReadyCb by rememberUpdatedState(onReady)
    val onStateChangeCb by rememberUpdatedState(onStateChange)
    val onSecondCb by rememberUpdatedState(onSecond)
    val onDurationCb by rememberUpdatedState(onDuration)
    val onErrorCb by rememberUpdatedState(onError)
    val onReleasedCb by rememberUpdatedState(onReleased)
    val currentKey by rememberUpdatedState(youtubeKey)
    // Issue 3: persistent-instance primitive. The WebView is created once by the
    // factory below; video switches while it is alive go through cueVideo() in
    // `update` instead of tearing the renderer down and cold-starting a new one.
    var boundPlayer by remember { mutableStateOf<YouTubePlayer?>(null) }
    var loadedKey by remember { mutableStateOf<String?>(null) }
    // Remembers the init-time autoplay decision so a mid-modal key switch
    // starts the new playback lifecycle the same way (load vs cue).
    var autoplayOnReady by remember { mutableStateOf<Boolean?>(null) }
    var rendererGone by remember { mutableStateOf(false) }
    var disposed by remember { mutableStateOf(false) }

    AndroidView(
        factory = { ctx ->
            YouTubePlayerView(ctx).apply {
                visibility = if (visible) View.VISIBLE else View.INVISIBLE
                disposed = false
                rendererGone = false
                enableAutomaticInitialization = false
                installTrailerWebViewRecovery(this) {
                    rendererGone = true
                    boundPlayer = null
                    loadedKey = null
                    if (!disposed) onErrorCb()
                }

                // Switches off the embedding library's own overlay UI, which
                // would itself sit on top of the player.
                setCustomPlayerUi(android.view.View(ctx).apply { visibility = android.view.View.GONE })

                // Leave the embedded controls, captions and links reachable on TV too.
                descendantFocusability = ViewGroup.FOCUS_AFTER_DESCENDANTS
                onViewReady(this)

                val iFrameOptions = IFramePlayerOptions.Builder(ctx)
                    .controls(if (showControls) 1 else 0)
                    .rel(0)
                    .ivLoadPolicy(3)
                    .ccLoadPolicy(0)
                    .fullscreen(0)
                    .build()

                initialize(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            if (disposed || rendererGone) return
                            boundPlayer = youTubePlayer
                            loadedKey = currentKey
                            val autoplay = onReadyCb(youTubePlayer)
                            autoplayOnReady = autoplay
                            if (autoplay) {
                                youTubePlayer.loadVideo(currentKey, 0f)
                            } else {
                                youTubePlayer.cueVideo(currentKey, 0f)
                            }
                        }

                        override fun onStateChange(
                            youTubePlayer: YouTubePlayer,
                            state: PlayerConstants.PlayerState
                        ) {
                            onStateChangeCb(state)
                        }

                        override fun onCurrentSecond(
                            youTubePlayer: YouTubePlayer,
                            second: Float
                        ) {
                            onSecondCb(second)
                        }

                        override fun onVideoDuration(
                            youTubePlayer: YouTubePlayer,
                            duration: Float
                        ) {
                            onDurationCb(duration)
                        }

                        override fun onError(
                            youTubePlayer: YouTubePlayer,
                            error: PlayerConstants.PlayerError
                        ) {
                            onErrorCb()
                        }
                    },
                    iFrameOptions
                )
            }
        },
        modifier = modifier,
        update = {
            it.visibility = if (visible) View.VISIBLE else View.INVISIBLE
            // Key changed while the WebView is alive (e.g. trailer metadata
            // resolving mid-modal): start the new video the same way init
            // would — no WebView reinit, no iframe JS re-parse, no teardown race.
            if (loadedKey != null && loadedKey != youtubeKey) {
                loadedKey = youtubeKey
                runCatching {
                    if (autoplayOnReady == true) {
                        boundPlayer?.loadVideo(youtubeKey, 0f)
                    } else {
                        boundPlayer?.cueVideo(youtubeKey, 0f)
                    }
                }
            }
        },
        onRelease = { playerView ->
            disposed = true
            // Pause before release so the renderer is not torn down mid-decode;
            // narrows the async WebView-teardown window on rapid reopen.
            runCatching { boundPlayer?.pause() }
            boundPlayer = null
            loadedKey = null
            runCatching { playerView.release() }
            onReleasedCb()
        }
    )
}

/** Shown in place of the player when YouTube refuses to embed the video. */
@Composable
private fun TrailerEmbedFallback(onOpenExternally: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This trailer cannot be embedded directly by YouTube.",
            style = ArvioSkin.typography.body.copy(color = Color.White.copy(alpha = 0.8f)),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        var fallbackBtnFocused by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(if (fallbackBtnFocused) ArvioSkin.colors.focusOutline else Color(0xFFCC0000))
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .onFocusChanged { fallbackBtnFocused = it.isFocused }
                .clickable { onOpenExternally() }
        ) {
            Text(
                text = "Watch on YouTube",
                style = ArvioSkin.typography.button.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (fallbackBtnFocused) Color.Black else Color.White
                )
            )
        }
    }
}

/**
 * Clean cinema-grade YouTube trailer modal for TV and Mobile.
 *
 * Nothing of ours is ever drawn on top of the player - our own controls live
 * beside it, which is what YouTube's "no overlays or frames in front of any
 * part of an embedded player" rule requires.
 *
 * Mobile: YouTube's own controls are switched on (controls=1). They bring
 * play/pause, a draggable scrubber, captions, quality and YouTube's own
 * attribution, so the only thing we add is a close button - below the video in
 * portrait, in the black letterbox strip beside it in landscape.
 *
 * TV: native YouTube controls remain available. Playback shortcuts use our
 * own bar, drawn in a permanently reserved strip *below* the video:
 *   - [DPAD_CENTER] / [ENTER]: Play / Pause toggle
 *   - [DPAD_LEFT] / [MediaRewind]: Seek backward 10s
 *   - [DPAD_RIGHT] / [MediaFastForward]: Seek forward 10s
 *   - [DPAD_DOWN]: reveal the bar and move focus into the strip
 *   - [DPAD_UP]: enter YouTube's controls (directions traverse, OK activates)
 *   - [BACK]: Dismiss modal cleanly and restore focus to Details
 * The strip also offers access to the embedded controls and the YouTube app.
 */
@Composable
fun YouTubeTrailerModal(
    youtubeKey: String,
    soundEnabled: Boolean = true,
    onPlaybackStateChanged: (PlayerConstants.PlayerState) -> Unit = {},
    onClose: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val isLandscape =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val playerFocusRequester = remember { FocusRequester() }
    val embeddedFocusRequester = remember { FocusRequester() }
    val youtubeFocusRequester = remember { FocusRequester() }
    val settingsFocusRequester = remember { FocusRequester() }
    var settingsFocused by remember { mutableStateOf(false) }

    var isYouTubeFocused by remember { mutableStateOf(false) }

    var activePlayer by remember { mutableStateOf<YouTubePlayer?>(null) }
    var nativePlayerView by remember { mutableStateOf<YouTubePlayerView?>(null) }
    // Per-video state resets on key change; the player/view handles above persist
    // so a mid-modal key switch cues via cueVideo() without losing D-pad control.
    val playbackLifecycle = remember(youtubeKey) { TrailerPlaybackLifecycle() }
    var playbackError by remember(youtubeKey) { mutableStateOf(false) }

    var isPlaying by remember { mutableStateOf(false) }
    KeepScreenOn(active = isPlaying)
    var currentSecond by remember { mutableFloatStateOf(0f) }
    var duration by remember { mutableFloatStateOf(0f) }
    // New video cued into the persistent player: drop stale progress and
    // playback state until the player's own callbacks repopulate.
    LaunchedEffect(youtubeKey) {
        currentSecond = 0f
        duration = 0f
        isPlaying = false
    }

    // Our own bar starts hidden and is only ever shown on a key press (TV).
    var showBar by remember { mutableStateOf(false) }
    var lastInteractionTime by remember { mutableLongStateOf(SystemClock.uptimeMillis()) }
    var transientIndicator by remember { mutableStateOf<TransientIndicator?>(null) }

    // Intercept back key on TV and mobile exclusively
    BackHandler(enabled = true) {
        onClose()
    }

    // Turning the phone sideways goes fullscreen, which is what gets the
    // navigation bar off the video. We do it ourselves rather than through
    // YouTube's fullscreen button, which stays switched off (fullscreen=0).
    val activity = remember(context) { context.findActivity() }
    PlayerSystemBarsEffect(
        activity = activity,
        showBars = false,
        enabled = isMobile && isLandscape,
        deviceType = LocalDeviceType.current
    )

    // TV drives the player with the D-pad, so the video takes focus on open.
    // On mobile the taps go to YouTube's own controls instead.
    LaunchedEffect(isMobile) {
        if (!isMobile) {
            delay(150)
            runCatching { playerFocusRequester.requestFocus() }
        }
    }

    // Auto-hide our bar after 3.5s - kept from the original implementation
    LaunchedEffect(showBar, isPlaying, lastInteractionTime, isYouTubeFocused) {
        if (showBar && isPlaying && !isYouTubeFocused) {
            delay(3500)
            showBar = false
        }
    }

    // Transient indicator auto-clear
    LaunchedEffect(transientIndicator) {
        if (transientIndicator != null) {
            delay(900)
            transientIndicator = null
        }
    }

    fun triggerIndicator(type: IndicatorType, text: String, icon: ImageVector) {
        transientIndicator = TransientIndicator(type, text, icon)
    }

    fun markInteraction() {
        showBar = true
        lastInteractionTime = SystemClock.uptimeMillis()
    }

    fun focusEmbeddedControls() {
        runCatching { embeddedFocusRequester.requestFocus() }
        nativePlayerView?.requestFocus(View.FOCUS_DOWN)
    }

    fun openExternalYouTube() {
        try {
            val url = "https://www.youtube.com/watch?v=$youtubeKey"
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
            onClose()
        } catch (_: Exception) {
            Toast.makeText(context, R.string.trailer_open_failed, Toast.LENGTH_SHORT).show()
        }
    }

    val barAlpha by animateFloatAsState(
        targetValue = if (showBar) 1f else 0f,
        label = "trailerBarFade"
    )

    val videoSurface: @Composable (Modifier) -> Unit = { modifier ->
        Box(
            modifier = modifier
                .background(Color.Black)
        ) {
            if (!playbackError) {
                TrailerPlayerSurface(
                    youtubeKey = youtubeKey,
                    modifier = Modifier.fillMaxSize().focusRequester(embeddedFocusRequester),
                    onViewReady = { nativePlayerView = it },
                    onReady = { player ->
                        activePlayer = player
                        if (soundEnabled) player.unMute() else player.mute()
                        playbackLifecycle.requestStart()
                    },
                    onStateChange = { state ->
                        onPlaybackStateChanged(state)
                        when (state) {
                            PlayerConstants.PlayerState.PLAYING -> {
                                playbackLifecycle.onPlaying()
                                isPlaying = true
                            }
                            PlayerConstants.PlayerState.PAUSED -> {
                                playbackLifecycle.onPaused()
                                isPlaying = false
                                if (!isMobile) showBar = true
                            }
                            PlayerConstants.PlayerState.ENDED -> {
                                // Keep the end screen and its links accessible until Back.
                                playbackLifecycle.onEnded()
                                isPlaying = false
                                showBar = true
                            }
                            else -> {}
                        }
                    },
                    onSecond = { currentSecond = it },
                    onDuration = { duration = it },
                    onError = { playbackError = true },
                    onReleased = { activePlayer = null; nativePlayerView = null }
                )
            } else {
                TrailerEmbedFallback(onOpenExternally = { openExternalYouTube() })
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.94f))
            .zIndex(100f)
            .onPreviewKeyEvent { event ->
                if (event.key == Key.Back || event.key == Key.Escape) {
                    if (event.type == KeyEventType.KeyUp) {
                        onClose()
                    }
                    true
                } else false
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (isMobile) onClose()
            },
        contentAlignment = Alignment.Center
    ) {
        if (isMobile) {
            // Size the player ourselves so it is exactly 16:9 and never
            // screen-filling: then the black letterbox strips belong to us and
            // a button placed there sits beside the player, not over it.
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // One frame for both orientations: side slots in landscape, a
                // slot underneath in portrait. The player keeps the same place
                // in the layout either way, so turning the phone does not
                // dispose it and restart the trailer - the activity handles the
                // orientation change itself (configChanges in the manifest).
                val sideSlot: Dp = if (isLandscape) mobileCloseSlot else 0.dp
                val availableWidth: Dp =
                    if (isLandscape) maxWidth - sideSlot * 2 else maxWidth * 0.96f
                val availableHeight: Dp =
                    if (isLandscape) maxHeight else maxHeight - mobileCloseSlot
                val videoWidth = minOf(availableWidth, availableHeight * (16f / 9f))
                val videoHeight = videoWidth * (9f / 16f)

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Mirror of the button slot, so the video stays centred
                        Spacer(modifier = Modifier.width(sideSlot))
                        videoSurface(
                            Modifier
                                .width(videoWidth)
                                .height(videoHeight)
                        )
                        Box(
                            modifier = Modifier.width(sideSlot),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLandscape) {
                                TrailerCloseButton(onClose = onClose)
                            }
                        }
                    }
                    if (!isLandscape) {
                        Spacer(modifier = Modifier.height(20.dp))
                        TrailerCloseButton(onClose = onClose)
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .widthIn(max = 1000.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // No ring around the video, at any focus state. A 3dp gap still
                // reads as a frame on the picture, and the office decision on
                // the TV layout is explicit that the white frame goes. Focus is
                // legible without it: the strip below highlights its own items,
                // so "nothing highlighted" means the video has the keys.
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(playerFocusRequester)
                        .focusable()
                        .onPreviewKeyEvent { event ->
                            // Once the user enters YouTube's controls, do not steal
                            // navigation or OK from captions, settings and links.
                            if (nativePlayerView?.hasFocus() == true) {
                                // Mobile WebView controls use keyboard focus traversal.
                                // Translate the remote into ordinary keyboard events,
                                // without inspecting or modifying YouTube's document.
                                val keyCode = when (event.key) {
                                    Key.DirectionLeft, Key.DirectionRight,
                                    Key.DirectionUp, Key.DirectionDown -> android.view.KeyEvent.KEYCODE_TAB
                                    Key.DirectionCenter -> android.view.KeyEvent.KEYCODE_SPACE
                                    Key.Enter, Key.NumPadEnter -> android.view.KeyEvent.KEYCODE_ENTER
                                    else -> null
                                }
                                if (keyCode != null) {
                                    val original = event.nativeKeyEvent
                                    val reverse = event.key == Key.DirectionLeft || event.key == Key.DirectionUp
                                    nativePlayerView?.dispatchKeyEvent(android.view.KeyEvent(
                                        original.downTime, original.eventTime, original.action,
                                        keyCode, original.repeatCount,
                                        if (reverse) android.view.KeyEvent.META_SHIFT_ON else 0
                                    ))
                                    return@onPreviewKeyEvent true
                                }
                                return@onPreviewKeyEvent false
                            }
                            if (event.type == KeyEventType.KeyDown) {
                                when (event.key) {
                                    Key.DirectionCenter, Key.Enter, Key.NumPadEnter, Key.MediaPlayPause -> {
                                        if (isPlaying) {
                                            activePlayer?.pause()
                                            isPlaying = false
                                            triggerIndicator(IndicatorType.PAUSE, "Paused", Icons.Default.Pause)
                                        } else {
                                            activePlayer?.play()
                                            isPlaying = true
                                            triggerIndicator(IndicatorType.PLAY, "Playing", Icons.Default.PlayArrow)
                                        }
                                        markInteraction()
                                        true
                                    }
                                    Key.MediaPlay -> {
                                        activePlayer?.play()
                                        isPlaying = true
                                        triggerIndicator(IndicatorType.PLAY, "Playing", Icons.Default.PlayArrow)
                                        markInteraction()
                                        true
                                    }
                                    Key.MediaPause -> {
                                        activePlayer?.pause()
                                        isPlaying = false
                                        triggerIndicator(IndicatorType.PAUSE, "Paused", Icons.Default.Pause)
                                        markInteraction()
                                        true
                                    }
                                    Key.DirectionLeft, Key.MediaRewind -> {
                                        val target = (currentSecond - 10f).coerceAtLeast(0f)
                                        activePlayer?.seekTo(target)
                                        currentSecond = target
                                        triggerIndicator(IndicatorType.SEEK_BACK, "-10s", Icons.Default.FastRewind)
                                        markInteraction()
                                        true
                                    }
                                    Key.DirectionRight, Key.MediaFastForward -> {
                                        val target = (currentSecond + 10f).coerceAtMost(duration)
                                        activePlayer?.seekTo(target)
                                        currentSecond = target
                                        triggerIndicator(IndicatorType.SEEK_FORWARD, "+10s", Icons.Default.FastForward)
                                        markInteraction()
                                        true
                                    }
                                    Key.DirectionUp -> {
                                        focusEmbeddedControls()
                                        markInteraction()
                                        true
                                    }
                                    Key.DirectionDown -> {
                                        markInteraction()
                                        runCatching { youtubeFocusRequester.requestFocus() }
                                        true
                                    }
                                    Key.Back, Key.Escape -> {
                                        onClose()
                                        true
                                    }
                                    else -> false
                                }
                            } else if (event.type == KeyEventType.KeyUp) {
                                when (event.key) {
                                    Key.DirectionCenter, Key.Enter, Key.NumPadEnter,
                                    Key.DirectionLeft, Key.DirectionRight, Key.DirectionUp, Key.DirectionDown,
                                    Key.MediaPlayPause, Key.MediaPlay, Key.MediaPause,
                                    Key.MediaFastForward, Key.MediaRewind,
                                    Key.Back, Key.Escape -> true
                                    else -> false
                                }
                            } else false
                        }
                ) {
                    videoSurface(
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                    )
                }

                // The strip below the video: reserved permanently, so the video
                // never resizes. Everything of ours lives in here.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(tvStripHeight)
                        .padding(top = 10.dp)
                ) {
                    val progress = if (duration > 0f) {
                        (currentSecond / duration).coerceIn(0f, 1f)
                    } else {
                        0f
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .alpha(barAlpha)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color.White.copy(alpha = 0.25f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction = progress)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(ArvioSkin.colors.focusOutline)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${formatTime(currentSecond)} / ${formatTime(duration)}",
                            style = ArvioSkin.typography.caption.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            ),
                            modifier = Modifier.alpha(barAlpha)
                        )

                        // The +/-10s badge used to sit in the middle of the
                        // video. It lives down here now.
                        AnimatedVisibility(
                            visible = transientIndicator != null,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            transientIndicator?.let { ind ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = ind.icon,
                                        contentDescription = null,
                                        tint = ArvioSkin.colors.focusOutline,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = ind.text,
                                        style = ArvioSkin.typography.caption.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = Color.White
                                        )
                                    )
                                }
                            }
                        }

                        YouTubeStripButton(
                            label = "YouTube · " + stringResource(R.string.settings),
                            isFocused = settingsFocused,
                            onFocusChanged = { settingsFocused = it },
                            focusRequester = settingsFocusRequester,
                            onActivate = { focusEmbeddedControls() },
                            onUp = { focusEmbeddedControls() },
                            onBack = onClose
                        )
                        YouTubeStripButton(
                            isFocused = isYouTubeFocused,
                            onFocusChanged = { isYouTubeFocused = it },
                            focusRequester = youtubeFocusRequester,
                            onActivate = { openExternalYouTube() },
                            onUp = { runCatching { playerFocusRequester.requestFocus() } },
                            onBack = onClose
                        )
                    }
                }
            }
        }
    }

    val latestActivePlayer by rememberUpdatedState(activePlayer)
    DisposableEffect(lifecycleOwner, youtubeKey) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    playbackLifecycle.onBackground()
                    latestActivePlayer?.pause()
                }
                Lifecycle.Event.ON_RESUME -> if (playbackLifecycle.onForeground()) latestActivePlayer?.play()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            latestActivePlayer?.pause()
        }
    }
}

/** The one thing we draw on mobile - and never over the player. */
@Composable
private fun TrailerCloseButton(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.18f))
            .clickable { onClose() },
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

/**
 * Focusable action beside the player, for its settings or the external app.
 */
@Composable
private fun YouTubeStripButton(
    label: String = "YouTube",
    isFocused: Boolean,
    onFocusChanged: (Boolean) -> Unit,
    focusRequester: FocusRequester,
    onActivate: () -> Unit,
    onUp: () -> Unit,
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (isFocused) ArvioSkin.colors.focusOutline else Color.White.copy(alpha = 0.12f)
            )
            .focusRequester(focusRequester)
            .onFocusChanged { onFocusChanged(it.isFocused) }
            .focusable()
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.DirectionUp -> {
                            onUp()
                            true
                        }
                        Key.DirectionCenter, Key.Enter, Key.NumPadEnter -> {
                            onActivate()
                            true
                        }
                        Key.Back, Key.Escape -> {
                            onBack()
                            true
                        }
                        else -> false
                    }
                } else false
            }
            .clickable { onActivate() }
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
            contentDescription = null,
            tint = if (isFocused) Color.Black else Color.White,
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = label,
            style = ArvioSkin.typography.caption.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = if (isFocused) Color.Black else Color.White
            )
        )
    }
}
