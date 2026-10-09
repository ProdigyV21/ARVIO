package com.arflix.tv.util

import android.content.Context
import android.net.Uri
import android.os.SystemClock
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.ui.screens.player.engine.exoplayer.AiSubtitleRenderersFactory
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import java.io.File
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Actual decoder callbacks; no network, HDMI hardware or provider credentials required. */
@androidx.annotation.OptIn(UnstableApi::class)
@RunWith(AndroidJUnit4::class)
class PlaybackFrameRateDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun mp4CadenceMatchesDecodedFrames() = checkPlayback("pal-25.mp4", 25f)
    @Test fun fractionalMatroskaStartsAfr() = checkPlayback("film-23976.mkv", 24000f / 1001f)
    @Test fun missingMatroskaMetadataUsesDecodedPts() = checkPlayback("film-23976.mkv", 24000f / 1001f, -1f)
    @Test fun wrongMatroskaMetadataIsCorrected() = checkPlayback("film-23976.mkv", 24000f / 1001f, 30f)
    @Test fun highFrameRateMatroskaUsesDecodedPts() = checkPlayback("video-5994.mkv", 60000f / 1001f, -1f)

    private fun checkPlayback(asset: String, expected: Float, declaredOverride: Float? = null) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = File(context.cacheDir, "afr-$asset")
        InstrumentationRegistry.getInstrumentation().context.assets.open("afr/$asset").use { input ->
            file.outputStream().use(input::copyTo)
        }
        val detector = PlaybackFrameRate()
        val frames = AtomicInteger()
        val detectedAt = AtomicLong(-1L)
        val firstFrameAt = AtomicLong(-1L)
        val actualMetadata = AtomicReference<Float>()
        val error = AtomicReference<PlaybackException>()
        val startedAt = SystemClock.elapsedRealtime()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        lateinit var player: ExoPlayer
        compose.runOnIdle {
            val subtitles = SubtitleTranslationManager(SubtitleTranslationService({ "" }), "English", scope)
            val renderers = AiSubtitleRenderersFactory(context, subtitles, scope)
                .forceDisableMediaCodecAsynchronousQueueing()
                .setEnableDecoderFallback(true)
            player = ExoPlayer.Builder(context, renderers).build().apply {
                volume = 0f
                setVideoChangeFrameRateStrategy(C.VIDEO_CHANGE_FRAME_RATE_STRATEGY_OFF)
                setVideoFrameMetadataListener { timestamp, _, format, _ ->
                    firstFrameAt.compareAndSet(-1L, SystemClock.elapsedRealtime() - startedAt)
                    actualMetadata.compareAndSet(null, format.frameRate)
                    detector.onFrame(timestamp, declaredOverride ?: format.frameRate)
                    frames.incrementAndGet()
                    if (detector.rate.value == expected) {
                        detectedAt.compareAndSet(-1L, SystemClock.elapsedRealtime() - startedAt)
                    }
                }
                addListener(object : Player.Listener {
                    override fun onPlayerError(playbackError: PlaybackException) { error.set(playbackError) }
                })
            }
        }
        try {
            compose.setContent {
                AndroidView(factory = { PlayerView(it).apply { this.player = player; useController = false } },
                    modifier = Modifier.fillMaxSize())
            }
            compose.runOnIdle {
                player.setMediaItem(MediaItem.fromUri(Uri.fromFile(file)))
                player.prepare()
                player.play()
            }
            compose.waitUntil(20_000L) { error.get() != null || (detectedAt.get() >= 0 && frames.get() >= 110) }
            assertNull("$asset playback failed", error.get())
            assertEquals("$asset must retain the correct AFR cadence", expected, detector.rate.value, 0.001f)
            Log.i("AfrDeviceTest", "$asset metadata=${actualMetadata.get()} override=$declaredOverride " +
                "detected=${detector.rate.value} startup=${firstFrameAt.get()}ms " +
                "detection=${detectedAt.get() - firstFrameAt.get()}ms frames=${frames.get()}")
        } finally {
            compose.runOnIdle { player.release() }
            scope.cancel()
            file.delete()
        }
    }
}
