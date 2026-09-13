package com.arflix.tv.ui.components

import android.view.LayoutInflater
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MergingMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.arflix.tv.R
import com.arflix.tv.core.player.LocalTrailerPlayerPool
import com.arflix.tv.core.player.TrailerPlayerPool
import com.arflix.tv.data.api.InAppYouTubeExtractor
import com.arflix.tv.data.api.YoutubeChunkedDataSourceFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.isActive

/**
 * YouTube / direct stream trailer player reusing the shared [TrailerPlayerPool] singleton.
 * Waits [delayMs] before resolving and playing (shows static backdrop first).
 *
 * Uses TextureView (via layout XML) and synchronizes visibility to [Player.Listener.onRenderedFirstFrame]
 * so playback crossfades seamlessly without black frames while ExoPlayer buffers in background.
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface TrailerPlayerEntryPoint {
    fun inAppYouTubeExtractor(): InAppYouTubeExtractor
}

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun TrailerPlayer(
    youtubeKey: String? = null,
    trailerUrl: String? = null,
    trailerAudioUrl: String? = null,
    modifier: Modifier = Modifier,
    delayMs: Long = 0L,
    volume: Float = 0f,
    cropToFill: Boolean = true,
    overscanZoom: Float = 1.35f,
    ownerToken: String? = null,
    onPlayingChanged: (Boolean) -> Unit = {},
    onFirstFrameRendered: () -> Unit = {},
    trailerPlayerPool: TrailerPlayerPool? = null
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val currentOnPlayingChanged by rememberUpdatedState(onPlayingChanged)
    val currentOnFirstFrameRendered by rememberUpdatedState(onFirstFrameRendered)

    var shouldPlay by remember { mutableStateOf(false) }
    var resolvedVideoUrl by remember { mutableStateOf(trailerUrl) }
    var resolvedAudioUrl by remember { mutableStateOf(trailerAudioUrl) }
    var hasRenderedFirstFrame by remember(youtubeKey, trailerUrl) { mutableStateOf(false) }

    val resolvedToken = ownerToken ?: youtubeKey ?: trailerUrl ?: "trailer_default"
    val resolvedPool = trailerPlayerPool ?: LocalTrailerPlayerPool.current

    val entryPoint = remember {
        EntryPointAccessors.fromApplication(context, TrailerPlayerEntryPoint::class.java)
    }
    val extractor = remember { entryPoint.inAppYouTubeExtractor() }

    val resolvedKey = youtubeKey?.takeIf { it.isNotBlank() }

    // Resolve playback URLs
    LaunchedEffect(resolvedKey, trailerUrl, trailerAudioUrl, delayMs, resolvedToken) {
        shouldPlay = false
        hasRenderedFirstFrame = false

        if (delayMs > 0L) {
            delay(delayMs)
        }

        if (resolvedPool != null && !resolvedPool.isCurrentOwner(resolvedToken)) {
            return@LaunchedEffect
        }

        if (!trailerUrl.isNullOrBlank()) {
            resolvedVideoUrl = trailerUrl
            resolvedAudioUrl = trailerAudioUrl
            shouldPlay = true
            return@LaunchedEffect
        }

        if (resolvedKey == null) {
            resolvedVideoUrl = null
            resolvedAudioUrl = null
            currentOnPlayingChanged(false)
            return@LaunchedEffect
        }

        var videoUrl: String? = null
        var audioUrl: String? = null
        withContext(Dispatchers.IO) {
            try {
                val source = extractor.extractPlaybackSource("https://www.youtube.com/watch?v=$resolvedKey")
                if (source != null) {
                    videoUrl = source.videoUrl
                    audioUrl = source.audioUrl
                }
            } catch (_: Exception) {}
        }

        // Abort if coroutine was cancelled or focus changed to another card during network extraction
        if (!isActive || (resolvedPool != null && !resolvedPool.isCurrentOwner(resolvedToken))) {
            return@LaunchedEffect
        }

        resolvedVideoUrl = videoUrl
        resolvedAudioUrl = audioUrl

        if (!resolvedVideoUrl.isNullOrBlank()) {
            shouldPlay = true
        } else {
            currentOnPlayingChanged(false)
        }
    }

    // Acquire shared player from pool, or create a safe fallback if unprovided
    val trailerPlayer = remember(resolvedPool, resolvedToken) {
        resolvedPool?.acquire(resolvedToken) ?: ExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_OFF
        }
    }

    // Configure playback when shouldPlay changes
    LaunchedEffect(shouldPlay, resolvedVideoUrl, resolvedAudioUrl, trailerPlayer, volume, cropToFill, resolvedToken) {
        val player = trailerPlayer ?: return@LaunchedEffect
        val vUrl = resolvedVideoUrl

        if (shouldPlay && !vUrl.isNullOrBlank()) {
            if (resolvedPool != null && !resolvedPool.isCurrentOwner(resolvedToken)) {
                return@LaunchedEffect
            }
            hasRenderedFirstFrame = false
            player.volume = volume.coerceIn(0f, 1f)
            player.videoScalingMode = if (cropToFill) {
                C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            } else {
                C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            }

            val aUrl = resolvedAudioUrl
            if (!aUrl.isNullOrBlank()) {
                val factory = DefaultMediaSourceFactory(YoutubeChunkedDataSourceFactory())
                val videoSource = factory.createMediaSource(MediaItem.fromUri(vUrl))
                val audioSource = factory.createMediaSource(MediaItem.fromUri(aUrl))
                player.setMediaSource(MergingMediaSource(videoSource, audioSource))
            } else {
                player.setMediaItem(MediaItem.fromUri(vUrl))
            }
            player.prepare()
            player.playWhenReady = true
        } else {
            hasRenderedFirstFrame = false
            player.playWhenReady = false
            // Immediate stop and clear on focus loss or cancellation — no delay
            runCatching {
                player.stop()
                player.clearMediaItems()
            }
        }
    }

    DisposableEffect(lifecycleOwner, trailerPlayer, resolvedToken) {
        val player = trailerPlayer ?: return@DisposableEffect onDispose {}
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    shouldPlay = false
                    currentOnPlayingChanged(false)
                }
            }

            override fun onRenderedFirstFrame() {
                hasRenderedFirstFrame = true
                currentOnPlayingChanged(true)
                currentOnFirstFrameRendered()
            }

            override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                resolvedKey?.let { extractor.evictCache(it) }
                shouldPlay = false
                currentOnPlayingChanged(false)
            }
        }

        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE,
                Lifecycle.Event.ON_STOP -> {
                    player.playWhenReady = false
                    if (resolvedPool != null) {
                        resolvedPool.stop(resolvedToken)
                    } else {
                        runCatching {
                            player.stop()
                            player.clearMediaItems()
                        }
                    }
                }
                Lifecycle.Event.ON_RESUME -> {
                    if (shouldPlay && !resolvedVideoUrl.isNullOrBlank() && (resolvedPool == null || resolvedPool.isCurrentOwner(resolvedToken))) {
                        player.playWhenReady = true
                    }
                }
                else -> Unit
            }
        }

        player.addListener(listener)
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            try {
                lifecycleOwner.lifecycle.removeObserver(observer)
            } catch (_: Throwable) {}
            try {
                player.removeListener(listener)
            } catch (_: Throwable) {}
            currentOnPlayingChanged(false)
            if (resolvedPool != null) {
                resolvedPool.stop(resolvedToken)
            } else {
                try {
                    player.stop()
                    player.clearMediaItems()
                    player.release()
                } catch (_: Throwable) {}
            }
        }
    }

    val playerAlphaState = animateFloatAsState(
        targetValue = if (shouldPlay && hasRenderedFirstFrame) 1f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "trailerFirstFrameAlpha"
    )

    val zoomScale = if (cropToFill) overscanZoom.coerceAtLeast(1f) else 1f

    if (trailerPlayer != null && shouldPlay) {
        AndroidView(
            factory = { ctx ->
                (LayoutInflater.from(ctx).inflate(R.layout.trailer_player_view, null) as PlayerView).apply {
                    this.player = trailerPlayer
                    useController = false
                    setControllerAutoShow(false)
                    hideController()
                    isFocusable = false
                    isFocusableInTouchMode = false
                    descendantFocusability = android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS
                    resizeMode = if (cropToFill) {
                        AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    } else {
                        AspectRatioFrameLayout.RESIZE_MODE_FIT
                    }
                    keepScreenOn = true
                }
            },
            update = { view ->
                if (view.player !== trailerPlayer) {
                    view.player = trailerPlayer
                }
                view.resizeMode = if (cropToFill) {
                    AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                } else {
                    AspectRatioFrameLayout.RESIZE_MODE_FIT
                }
            },
            onRelease = { view ->
                view.player = null
                view.keepScreenOn = false
            },
            modifier = modifier
                .fillMaxSize()
                .clipToBounds()
                .graphicsLayer {
                    alpha = playerAlphaState.value
                    scaleX = zoomScale
                    scaleY = zoomScale
                }
        )
    }
}
