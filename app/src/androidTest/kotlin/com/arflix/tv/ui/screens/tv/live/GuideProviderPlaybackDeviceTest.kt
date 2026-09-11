package com.arflix.tv.ui.screens.tv.live

import android.os.Bundle
import android.os.SystemClock
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.datasource.okhttp.OkHttpDataSource
import okhttp3.OkHttpClient
import androidx.media3.ui.PlayerView
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.di.GuideAuditEntryPoint
import dagger.hilt.android.EntryPointAccessors
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test

@androidx.annotation.OptIn(UnstableApi::class)
class GuideProviderPlaybackDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun declaredProviderFormatRendersVideoAndAdvances() {
        assumeTrue(InstrumentationRegistry.getArguments().getString("livePlayback") == "true")
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        check(context.packageName.endsWith(".iptvaudit"))
        val repository = EntryPointAccessors.fromApplication(context, GuideAuditEntryPoint::class.java).iptvRepository()
        val channel = runBlocking {
            repository.warmupFromCacheOnly()
            repository.pagedChannelWindow(null, null, 0, 1).first()
        }
        assertTrue("Declared HLS must survive the persisted channel cache", channel.streamUrl.substringBefore('?').endsWith(".m3u8"))
        lateinit var player: ExoPlayer
        val frame = AtomicBoolean(false)
        val frames = AtomicInteger()
        val previousFrameAt = AtomicLong()
        val longestGapMs = AtomicLong()
        val rebufferCount = AtomicInteger()
        val start = SystemClock.elapsedRealtime()
        compose.runOnIdle {
            val buffer = buildLiveTvBufferProfile(256, true)
            val loadControl = DefaultLoadControl.Builder()
                .setBufferDurationsMs(buffer.minBufferMs, buffer.maxBufferMs,
                    buffer.bufferForPlaybackMs, buffer.bufferForPlaybackAfterRebufferMs)
                .setTargetBufferBytes(buffer.targetBufferBytes)
                .setPrioritizeTimeOverSizeThresholds(false)
                .setBackBuffer(buffer.backBufferMs, true).build()
            val source = DefaultMediaSourceFactory(context).setDataSourceFactory(
                OkHttpDataSource.Factory(OkHttpClient()).setUserAgent("VLC/3.0.20 LibVLC/3.0.20"))
            player = ExoPlayer.Builder(context).setLoadControl(loadControl).setMediaSourceFactory(source).build()
            player.setVideoFrameMetadataListener { _, _, _, _ ->
                val now = SystemClock.elapsedRealtime()
                val previous = previousFrameAt.getAndSet(now)
                if (previous > 0) longestGapMs.updateAndGet { maxOf(it, now - previous) }
                frames.incrementAndGet()
            }
            player.addListener(object : Player.Listener {
                override fun onRenderedFirstFrame() { frame.set(true) }
                override fun onPlaybackStateChanged(state: Int) {
                    if (frame.get() && state == Player.STATE_BUFFERING) rebufferCount.incrementAndGet()
                }
            })
        }
        try {
            compose.setContent {
                AndroidView(factory = { PlayerView(it).apply { this.player = player; useController = false } },
                    modifier = Modifier.fillMaxSize())
            }
            compose.runOnIdle {
                player.setMediaItem(MediaItem.Builder().setUri(channel.streamUrl)
                    .setMimeType(MimeTypes.APPLICATION_M3U8)
                    .setLiveConfiguration(buildLiveTvConfiguration()).build())
                player.prepare()
                player.playWhenReady = true
            }
            compose.waitUntil(60_000) { frame.get() || compose.runOnIdle { player.playerError != null } }
            compose.runOnIdle {
                assertNull("Provider playback failed: ${player.playerError?.errorCodeName}", player.playerError)
                assertTrue(frame.get())
            }
            val firstFrameMs = SystemClock.elapsedRealtime() - start
            val initialFrames = frames.get()
            longestGapMs.set(0)
            SystemClock.sleep(40_000)
            compose.runOnIdle {
                assertNull(player.playerError)
                // HLS currentPosition moves backwards as the sliding window advances.
                val rendered = frames.get() - initialFrames
                assertTrue("Video must keep producing frames: $rendered", rendered > 100)
                instrumentation.sendStatus(0, Bundle().apply {
                    putString("stream", "live_first_frame_ms=$firstFrameMs frames_in_40s=$rendered longest_frame_gap_ms=${longestGapMs.get()} rebuffers=${rebufferCount.get()} video=${player.videoSize.width}x${player.videoSize.height}\n")
                })
            }
        } finally {
            compose.runOnIdle { player.release() }
        }
    }
}
