package com.arflix.tv.ui.screens.player

import android.content.Context
import android.graphics.SurfaceTexture
import android.os.Build
import android.view.Surface
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.audio.AudioSink
import com.arflix.tv.ui.screens.player.engine.exoplayer.AiSubtitleRenderersFactory
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal object PlaybackFormatCheck {
    suspend fun run(context: Context): String = withContext(Dispatchers.Main.immediate) {
        val app = context.applicationContext
        val stereo = check(app, "avc-aac.mp4")
        val surround = check(app, "avc-ac3.mkv")
        "H.264 + AAC stereo: $stereo\nH.264 + Dolby Digital 5.1: $surround"
    }

    private suspend fun check(context: Context, file: String): String = coroutineScope {
        val result = CompletableDeferred<String>()
        val texture = if (Build.VERSION.SDK_INT >= 26) SurfaceTexture(false) else SurfaceTexture(0)
        val surface = Surface(texture)
        val rendererScope = CoroutineScope(coroutineContext + SupervisorJob(coroutineContext[Job]))
        var player: ExoPlayer? = null
        try {
            texture.setDefaultBufferSize(320, 180)
            val subtitles = SubtitleTranslationManager(SubtitleTranslationService({ "" }), "English", rendererScope)
            val exo = ExoPlayer.Builder(context, AiSubtitleRenderersFactory(context, subtitles, rendererScope)).build()
            player = exo
            var videoRendered = false
            var audioAdvanced = false
            var output = "unknown output"
            fun finishIfReady() {
                if (videoRendered && audioAdvanced) result.complete("played; $output")
            }
            exo.addAnalyticsListener(object : AnalyticsListener {
                override fun onAudioTrackInitialized(eventTime: AnalyticsListener.EventTime, audioTrackConfig: AudioSink.AudioTrackConfig) {
                    output = configuredAudioOutput(audioTrackConfig)
                }

                override fun onAudioPositionAdvancing(eventTime: AnalyticsListener.EventTime, playoutStartSystemTimeMs: Long) {
                    audioAdvanced = true
                    finishIfReady()
                }
            })
            exo.addListener(object : Player.Listener {
                override fun onRenderedFirstFrame() {
                    videoRendered = true
                    finishIfReady()
                }

                override fun onPlayerError(error: PlaybackException) {
                    result.complete("failed (code ${error.errorCode})")
                }
            })
            exo.setVideoSurface(surface)
            exo.setMediaItem(MediaItem.fromUri("asset:///playback-check/$file"))
            exo.prepare()
            exo.play()
            withTimeoutOrNull(4_000) { result.await() } ?: "no decoded audio and video before timeout"
        } finally {
            rendererScope.cancel()
            player?.release()
            surface.release()
            texture.release()
        }
    }
}
