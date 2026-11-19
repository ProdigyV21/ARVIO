package com.arflix.tv.ui.screens.player

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.ui.screens.player.engine.exoplayer.AiSubtitleRenderersFactory
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import kotlinx.coroutines.*
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class TrueHdPlaybackDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun trueHdProducesAudioThroughProductionRenderers() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = File(context.cacheDir, "truehd-5-1.mka")
        InstrumentationRegistry.getInstrumentation().context.assets.open(file.name).use { input ->
            file.outputStream().use { input.copyTo(it) }
        }
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        lateinit var player: ExoPlayer
        val audioAdvancing = AtomicBoolean(false)
        compose.runOnIdle {
            val subtitles = SubtitleTranslationManager(SubtitleTranslationService({ "" }), "English", scope)
            player = ExoPlayer.Builder(context, AiSubtitleRenderersFactory(context, subtitles, scope)).build()
            player.addAnalyticsListener(object : AnalyticsListener {
                override fun onAudioPositionAdvancing(eventTime: AnalyticsListener.EventTime, playoutStartSystemTimeMs: Long) {
                    audioAdvancing.set(true)
                }
            })
            player.setMediaItem(MediaItem.fromUri(android.net.Uri.fromFile(file)))
            player.prepare()
            player.play()
        }
        try {
            compose.waitUntil(20000) { compose.runOnIdle { player.playerError != null || (audioAdvancing.get() && player.currentPosition >= 1000) } }
            compose.runOnIdle {
                assertNull("TrueHD error: ${player.playerError}", player.playerError)
                assertTrue("AudioTrack must actually advance", audioAdvancing.get())
                assertEquals(6, player.audioFormat?.channelCount)
            }
        } finally {
            compose.runOnIdle { player.release(); scope.cancel() }
            file.delete()
        }
    }
}
