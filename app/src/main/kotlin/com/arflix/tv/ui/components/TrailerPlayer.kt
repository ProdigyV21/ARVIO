package com.arflix.tv.ui.components

import android.view.LayoutInflater
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.currentStateAsState
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MergingMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.arflix.tv.R
import com.arflix.tv.data.api.InAppYouTubeExtractor
import com.arflix.tv.data.api.YoutubeChunkedDataSourceFactory
import com.arflix.tv.data.api.YOUTUBE_TRAILER_USER_AGENT
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Muted YouTube trailer player using ExoPlayer with direct YouTube stream extraction.
 * Waits [delayMs] before resolving and playing (shows static backdrop first).
 * Uses a TextureView and fades in after the decoder renders its first frame,
 * keeping the artwork visible while ExoPlayer buffers.
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface TrailerPlayerEntryPoint {
    fun inAppYouTubeExtractor(): InAppYouTubeExtractor
}

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun TrailerPlayer(
    youtubeKey: String,
    modifier: Modifier = Modifier,
    delayMs: Long = 0L,
    volume: Float = 0f,
    onPlayingChanged: (Boolean) -> Unit = {}
) {
    val context = LocalContext.current
    val currentOnPlayingChanged by rememberUpdatedState(onPlayingChanged)

    var shouldPlay by remember(youtubeKey) { mutableStateOf(false) }
    var videoUrl by remember(youtubeKey) { mutableStateOf<String?>(null) }
    var audioUrl by remember(youtubeKey) { mutableStateOf<String?>(null) }
    var fallbackVideoUrl by remember(youtubeKey) { mutableStateOf<String?>(null) }

    val entryPoint = remember {
        EntryPointAccessors.fromApplication(context, TrailerPlayerEntryPoint::class.java)
    }
    val extractor = remember { entryPoint.inAppYouTubeExtractor() }

    LaunchedEffect(youtubeKey) {
        shouldPlay = false
        videoUrl = null
        audioUrl = null
        fallbackVideoUrl = null
        currentOnPlayingChanged(false)
        delay(delayMs)
        withContext(Dispatchers.IO) {
            try {
                val source = extractor.extractPlaybackSource("https://www.youtube.com/watch?v=$youtubeKey")
                if (source != null) {
                    videoUrl = source.videoUrl
                    audioUrl = source.audioUrl
                    fallbackVideoUrl = source.fallbackVideoUrl
                }
            } catch (error: Exception) {
                if (error is kotlinx.coroutines.CancellationException) throw error
                Log.w("TrailerQuality", "Trailer preparation failed (${error.javaClass.simpleName})")
            }
        }
        if (videoUrl != null) {
            shouldPlay = true
        } else {
            currentOnPlayingChanged(false)
        }
    }

    // Mount a transparent TextureView while buffering, then fade only after the
    // decoder renders a frame. The same behavior is used in cards and the billboard.
    AnimatedVisibility(
        visible = shouldPlay && videoUrl != null,
        enter = EnterTransition.None,
        exit = fadeOut(),
        modifier = modifier
    ) {
        var frameReady by remember(youtubeKey) { mutableStateOf(false) }
        var resumePositionMs by remember(youtubeKey) { mutableLongStateOf(0L) }
        val previewAlpha by animateFloatAsState(if (frameReady) 1f else 0f,
            tween(600), label = "trailerFirstFrameFade")
        val lifecycleOwner = LocalLifecycleOwner.current
        val lifecycleState by lifecycleOwner.lifecycle.currentStateAsState()
        val availability by TrailerPlaybackPool.availability.collectAsState()
        var lease by remember(youtubeKey) { mutableStateOf<TrailerPlaybackPool.Lease?>(null) }
        DisposableEffect(youtubeKey, lifecycleState, availability) {
            val acquired = if (lifecycleState.isAtLeast(Lifecycle.State.RESUMED)) {
                TrailerPlaybackPool.acquire(context) {
                    lease?.player?.let { current ->
                        if (current.playbackState != Player.STATE_ENDED) resumePositionMs = current.currentPosition.coerceAtLeast(0L)
                    }
                    lease = null
                    frameReady = false
                    currentOnPlayingChanged(false)
                }
            } else null
            lease = acquired
            onDispose {
                acquired?.close()
                if (lease === acquired) lease = null
            }
        }
        val activeLease = lease
        val player = activeLease?.takeIf { it.isOwner }?.player
        if (player != null && activeLease != null) {

            LaunchedEffect(player, volume) {
                player.volume = volume.coerceIn(0f, 1f)
            }

            LaunchedEffect(player, videoUrl, audioUrl, youtubeKey) {
                val vUrl = videoUrl ?: return@LaunchedEffect
                frameReady = false
                currentOnPlayingChanged(false)
                if (!audioUrl.isNullOrBlank()) {
                    val factory = DefaultMediaSourceFactory(YoutubeChunkedDataSourceFactory())
                    val videoSource = factory.createMediaSource(MediaItem.fromUri(vUrl))
                    val audioSource = factory.createMediaSource(MediaItem.fromUri(audioUrl!!))
                    player.setMediaSource(MergingMediaSource(videoSource, audioSource), resumePositionMs)
                } else {
                    val factory = DefaultMediaSourceFactory(
                        androidx.media3.datasource.DefaultHttpDataSource.Factory()
                            .setUserAgent(YOUTUBE_TRAILER_USER_AGENT)
                    )
                    player.setMediaSource(factory.createMediaSource(MediaItem.fromUri(vUrl)), resumePositionMs)
                }
                player.playWhenReady = true
                player.prepare()
            }

            DisposableEffect(lifecycleOwner, player) {
                val listener = object : Player.Listener {
                    override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                        if (!activeLease.isOwner) return
                        frameReady = false
                        currentOnPlayingChanged(false)
                        extractor.evictCache(youtubeKey)
                        val fallback = fallbackVideoUrl
                        if (fallback != null) {
                            fallbackVideoUrl = null
                            audioUrl = null
                            videoUrl = fallback
                        } else {
                            shouldPlay = false
                            currentOnPlayingChanged(false)
                        }
                    }
                    override fun onVideoSizeChanged(videoSize: androidx.media3.common.VideoSize) {
                        if (!activeLease.isOwner) return
                        Log.i("TrailerQuality", "Native trailer ${videoSize.width}x${videoSize.height}")
                    }
                    override fun onRenderedFirstFrame() {
                        if (!activeLease.isOwner) return
                        frameReady = true
                        currentOnPlayingChanged(player.isPlaying)
                    }
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        if (!activeLease.isOwner) return
                        currentOnPlayingChanged(frameReady && isPlaying)
                    }
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        if (!activeLease.isOwner) return
                        if (playbackState == Player.STATE_ENDED) {
                        resumePositionMs = 0L
                            shouldPlay = false
                            currentOnPlayingChanged(false)
                        }
                    }
                }
                val observer = LifecycleEventObserver { _, event ->
                    if (!activeLease.isOwner) return@LifecycleEventObserver
                    when (event) {
                        Lifecycle.Event.ON_PAUSE -> player.pause()
                        Lifecycle.Event.ON_RESUME -> if (shouldPlay) player.play()
                        else -> {}
                    }
                }
                player.addListener(listener)
                lifecycleOwner.lifecycle.addObserver(observer)
                if (!lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                    player.pause()
                }
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                    player.removeListener(listener)
                }
            }

            AndroidView(
                factory = { ctx ->
                    (LayoutInflater.from(ctx).inflate(R.layout.trailer_player_view, null) as PlayerView).apply {
                        this.player = player
                        useController = false
                        controllerAutoShow = false
                        hideController()
                        descendantFocusability = android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS
                        isFocusable = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                        keepScreenOn = true
                    }
                },
                update = { view ->
                    if (view.player !== player) view.player = player
                },
                onReset = null,
                onRelease = { view -> view.player = null },
                modifier = Modifier.fillMaxSize().clipToBounds()
                    .graphicsLayer { alpha = previewAlpha }
                    .testTag("native_trailer_$youtubeKey")
            )
        }
    }
}
