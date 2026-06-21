package com.arflix.tv.ui.components

import android.view.LayoutInflater
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MergingMediaSource
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.arflix.tv.R
import com.arflix.tv.data.api.InAppYouTubeExtractor
import com.arflix.tv.data.api.TrailerPlaybackSource
import com.arflix.tv.data.api.YoutubeChunkedDataSourceFactory
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
 * Uses TextureView (via layout XML) so AnimatedVisibility's fadeIn works without a black flash —
 * the view is transparent during the fade while ExoPlayer buffers in the background.
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

    var shouldPlay by remember { mutableStateOf(false) }
    var playbackSource by remember { mutableStateOf<TrailerPlaybackSource?>(null) }
    var fallbackIndex by remember { mutableStateOf(0) }

    val entryPoint = remember {
        EntryPointAccessors.fromApplication(context, TrailerPlayerEntryPoint::class.java)
    }
    val extractor = remember { entryPoint.inAppYouTubeExtractor() }

    LaunchedEffect(youtubeKey) {
        shouldPlay = false
        playbackSource = null
        fallbackIndex = 0
        delay(delayMs)
        withContext(Dispatchers.IO) {
            try {
                playbackSource = extractor.extractPlaybackSource("https://www.youtube.com/watch?v=$youtubeKey")
            } catch (_: Exception) {}
        }
        if (playbackSource?.videoUrl != null) {
            shouldPlay = true
            currentOnPlayingChanged(true)
        } else {
            currentOnPlayingChanged(false)
        }
    }

    // TextureView (set via XML) means the view is transparent during the fade-in,
    // so the backdrop image shows through while ExoPlayer buffers. No black flash.
    AnimatedVisibility(
        visible = shouldPlay && playbackSource?.videoUrl != null,
        enter = fadeIn(animationSpec = tween(800)),
        exit = fadeOut(),
        modifier = modifier
    ) {
        val source = playbackSource
        val videoUrls = remember(source) {
            buildList {
                source?.videoUrl?.takeIf { it.isNotBlank() }?.let(::add)
                source?.fallbackVideoUrls.orEmpty()
                    .filter { it.isNotBlank() }
                    .forEach(::add)
            }.distinct()
        }
        val activeVideoUrl = videoUrls.getOrNull(fallbackIndex)
        val activeAudioUrl = if (fallbackIndex == 0) source?.audioUrl else null
        val player = remember(youtubeKey) {
            ExoPlayer.Builder(context)
                .setTrackSelector(
                    DefaultTrackSelector(context).apply {
                        parameters = buildUponParameters()
                            .setForceHighestSupportedBitrate(true)
                            .setForceLowestBitrate(false)
                            .build()
                    }
                )
                .build()
                .apply {
                    repeatMode = Player.REPEAT_MODE_OFF
                    playWhenReady = true
                }
        }

        LaunchedEffect(volume) {
            player.volume = volume.coerceIn(0f, 1f)
        }

        LaunchedEffect(activeVideoUrl, activeAudioUrl, youtubeKey) {
            val vUrl = activeVideoUrl ?: return@LaunchedEffect
            if (!activeAudioUrl.isNullOrBlank()) {
                val factory = DefaultMediaSourceFactory(YoutubeChunkedDataSourceFactory())
                val videoSource = factory.createMediaSource(trailerMediaItem(vUrl))
                val audioSource = factory.createMediaSource(trailerMediaItem(activeAudioUrl))
                player.setMediaSource(MergingMediaSource(videoSource, audioSource))
            } else {
                player.setMediaItem(trailerMediaItem(vUrl))
            }
            player.prepare()
        }

        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(lifecycleOwner, player) {
            val listener = object : Player.Listener {
                override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                    if (fallbackIndex < videoUrls.lastIndex) {
                        fallbackIndex += 1
                    } else {
                        extractor.evictCache(youtubeKey)
                        shouldPlay = false
                        currentOnPlayingChanged(false)
                    }
                }
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_ENDED) {
                        shouldPlay = false
                        currentOnPlayingChanged(false)
                    }
                }
            }
            val observer = LifecycleEventObserver { _, event ->
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
                currentOnPlayingChanged(false)
                player.stop()
                player.release()
            }
        }

        AndroidView(
            factory = { ctx ->
                (LayoutInflater.from(ctx).inflate(R.layout.trailer_player_view, null) as PlayerView).apply {
                    this.player = player
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    keepScreenOn = true
                }
            },
            update = { view ->
                if (view.player !== player) view.player = player
            },
            modifier = Modifier.fillMaxSize().clipToBounds()
        )
    }
}

private fun trailerMediaItem(url: String): MediaItem {
    val lower = url.lowercase()
    return MediaItem.Builder()
        .setUri(url)
        .apply {
            if (lower.contains(".m3u8") || lower.contains("manifest")) {
                setMimeType(MimeTypes.APPLICATION_M3U8)
            }
        }
        .build()
}
