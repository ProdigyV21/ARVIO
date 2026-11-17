package com.arflix.tv.ui.screens.player.engine.exoplayer

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class VodNetworkPlaybackDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun transientThrottleRecoversOnTheSameStreamAndRecordsSafeTiming() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val bytes = InstrumentationRegistry.getInstrumentation().context.assets.open("seek_preview_device_test.mp4").use { it.readBytes() }
        val attempts = AtomicInteger()
        val server = MockWebServer().apply {
            dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    if (attempts.incrementAndGet() == 1) return MockResponse().setResponseCode(503).addHeader("Retry-After", "1")
                    val start = request.getHeader("Range")?.substringAfter("bytes=")?.substringBefore('-')?.toIntOrNull() ?: 0
                    if (start >= bytes.size) return MockResponse().setResponseCode(416)
                    return MockResponse().setResponseCode(if (start > 0) 206 else 200)
                        .addHeader("Content-Type", "video/mp4")
                        .apply { if (start > 0) addHeader("Content-Range", "bytes $start-${bytes.size - 1}/${bytes.size}") }
                        .setBody(Buffer().write(bytes, start, bytes.size - start))
                }
            }
            start()
        }
        val trace = PlaybackStartupTrace().apply { begin() }
        val client = OkHttpClient.Builder().eventListenerFactory(trace.listenerFactory).build()
        val firstFrame = AtomicBoolean()
        lateinit var player: ExoPlayer
        compose.runOnIdle {
            player = ExoPlayer.Builder(context).build().apply {
                volume = 0f
                addListener(object : Player.Listener { override fun onRenderedFirstFrame() { firstFrame.set(true) } })
            }
        }
        compose.setContent {
            AndroidView(factory = { PlayerView(it).apply { this.player = player; useController = false } }, modifier = Modifier.fillMaxSize())
        }
        try {
            compose.runOnIdle {
                val source = ProgressiveMediaSource.Factory(OkHttpDataSource.Factory(client))
                    .setLoadErrorHandlingPolicy(VodLoadErrorHandlingPolicy(true))
                    .createMediaSource(MediaItem.fromUri(server.url("/video.mp4?secret=qa").toString()))
                player.setMediaSource(source)
                trace.preparing()
                player.prepare(); player.play()
            }
            compose.waitUntil(20_000) { firstFrame.get() && compose.runOnIdle { player.currentPosition > 500 } }
            compose.runOnIdle {
                assertNull(player.playerError)
                val sample = trace.finish()
                assertTrue(sample.calls >= 2)
                assertFalse(sample.summary().contains("secret"))
                assertTrue(sample.prepareToFrameMs > 0)
            }
            assertTrue(attempts.get() in 2..5)
        } finally {
            compose.runOnIdle { player.release() }
            client.dispatcher.executorService.shutdown()
            client.connectionPool.evictAll()
            server.shutdown()
        }
    }
}
