package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MergingMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.arflix.tv.data.api.InAppYouTubeExtractor
import com.arflix.tv.data.api.YoutubeChunkedDataSourceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Muted YouTube trailer player using ExoPlayer with direct YouTube stream extraction.
 * Waits [delayMs] before resolving and playing (shows static backdrop first).
 * Uses InAppYouTubeExtractor to get direct googlevideo.com CDN URLs.
 */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun TrailerPlayer(
    youtubeKey: String,
    modifier: Modifier = Modifier,
    delayMs: Long = 3000L
) {
    val context = LocalContext.current
    var shouldPlay by remember { mutableStateOf(false) }
    var videoUrl by remember { mutableStateOf<String?>(null) }
    var audioUrl by remember { mutableStateOf<String?>(null) }

    // Delay, then extract YouTube direct URL
    LaunchedEffect(youtubeKey) {
        shouldPlay = false
        videoUrl = null
        audioUrl = null
        delay(delayMs)
        withContext(Dispatchers.IO) {
            try {
                val extractor = InAppYouTubeExtractor()
                val source = extractor.extractPlaybackSource("https://www.youtube.com/watch?v=$youtubeKey")
                if (source != null) {
                    videoUrl = source.videoUrl
                    audioUrl = source.audioUrl
                }
            } catch (_: Exception) {}
        }
        if (videoUrl != null) shouldPlay = true
    }

    AnimatedVisibility(
        visible = shouldPlay && videoUrl != null,
        enter = fadeIn(animationSpec = androidx.compose.animation.core.tween(1000)),
        exit = fadeOut(),
        modifier = modifier
    ) {
        val player = remember(youtubeKey) {
            ExoPlayer.Builder(context).build().apply {
                volume = 0.5f // Half volume for background trailer
                repeatMode = Player.REPEAT_MODE_ONE
                playWhenReady = true
            }
        }

        LaunchedEffect(videoUrl, audioUrl, youtubeKey) {
            val vUrl = videoUrl ?: return@LaunchedEffect
            if (!audioUrl.isNullOrBlank()) {
                // Adaptive: separate video + audio streams
                val factory = DefaultMediaSourceFactory(YoutubeChunkedDataSourceFactory())
                val videoSource = factory.createMediaSource(MediaItem.fromUri(vUrl))
                val audioSource = factory.createMediaSource(MediaItem.fromUri(audioUrl!!))
                player.setMediaSource(MergingMediaSource(videoSource, audioSource))
            } else {
                // Progressive: combined video+audio
                player.setMediaItem(MediaItem.fromUri(vUrl))
            }
            player.prepare()
        }

        DisposableEffect(Unit) {
            onDispose {
                player.stop()
                player.release()
            }
        }

        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    this.player = player
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    setShutterBackgroundColor(android.graphics.Color.TRANSPARENT)
                    setKeepContentOnPlayerReset(true)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
