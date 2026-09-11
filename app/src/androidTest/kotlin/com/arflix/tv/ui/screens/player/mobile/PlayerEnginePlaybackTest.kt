package com.arflix.tv.ui.screens.player.mobile

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.ui.screens.player.engine.exoplayer.AiSubtitleRenderersFactory
import com.arflix.tv.ui.screens.player.engine.exoplayer.ExoPlayerEngine
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@androidx.annotation.OptIn(UnstableApi::class)
@RunWith(AndroidJUnit4::class)
class PlayerEnginePlaybackTest {
    @get:Rule val compose = createComposeRule()

    @Test
    fun wrappedRenderersPlaySeekAndReplaceSource() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = File(context.cacheDir, "pr610-player-engine.mp4")
        InstrumentationRegistry.getInstrumentation().context.assets.open("seek_preview_device_test.mp4").use { input ->
            file.outputStream().use(input::copyTo)
        }
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        lateinit var player: ExoPlayer
        lateinit var engine: ExoPlayerEngine
        lateinit var renderers: AiSubtitleRenderersFactory
        val firstFrame = AtomicBoolean(false)
        compose.runOnIdle {
            val subtitles = SubtitleTranslationManager(SubtitleTranslationService({ "" }), "English", scope)
            renderers = AiSubtitleRenderersFactory(context, subtitles, scope)
            player = ExoPlayer.Builder(context, renderers).build()
            engine = ExoPlayerEngine(player, scope)
            player.addListener(object : Player.Listener {
                override fun onRenderedFirstFrame() { firstFrame.set(true) }
            })
        }
        try {
            compose.setContent {
                AndroidView(
                    factory = { PlayerView(it).apply { this.player = player; useController = false } },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            compose.runOnIdle {
                player.setMediaItem(MediaItem.fromUri(Uri.fromFile(file)))
                player.prepare()
                engine.play()
            }
            compose.waitUntil(15_000L) { firstFrame.get() && engine.state.value.currentPositionMs > 500L }
            compose.runOnIdle {
                assertNull(player.playerError)
                engine.pause()
                engine.seekTo(20_000L)
            }
            compose.waitUntil(10_000L) { compose.runOnIdle { player.playbackState == Player.STATE_READY } }
            compose.runOnIdle {
                assertFalse(player.playWhenReady)
                assertTrue(player.currentPosition >= 19_500L)
                renderers.audioDelayUs.set(200_000L)
                engine.play()
            }
            compose.waitUntil(10_000L) { engine.state.value.currentPositionMs > 20_500L }
            firstFrame.set(false)
            compose.runOnIdle {
                player.setMediaItem(MediaItem.fromUri(Uri.fromFile(file)))
                player.prepare()
                engine.play()
            }
            compose.waitUntil(15_000L) { firstFrame.get() && engine.state.value.currentPositionMs in 500L..10_000L }
            compose.runOnIdle { assertNull(player.playerError) }
        } finally {
            compose.runOnIdle { engine.release(); player.release() }
            scope.cancel()
            file.delete()
        }
    }
}
