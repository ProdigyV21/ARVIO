package com.arflix.tv.ui.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class TrailerPoolDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val context get() = ApplicationProvider.getApplicationContext<Context>()
    private var active: TrailerPlaybackPool.Lease? = null
    @After fun cleanup() { compose.runOnIdle {
        active?.close()
        TrailerPlaybackPool.fullPlayback().close()
        TrailerPlaybackPool.releaseIdle()
    } }

    @Test fun rapidFocusChangesReuseOnePlayerAndIgnoreOldDisposals() {
        compose.runOnIdle {
            var revoked = 0
            var previous = TrailerPlaybackPool.acquire(context) { revoked++ }!!
            val original = previous.player
            repeat(30) {
                val current = TrailerPlaybackPool.acquire(context) { revoked++ }!!
                previous.close()
                assertTrue(current.isOwner)
                assertFalse(previous.isOwner)
                assertSame(original, current.player)
                previous = current
            }
            active = previous
            assertEquals(30, revoked)
        }
    }

    @Test fun fullPlaybackExclusivelyOwnsDecoderResourcesUntilAllOwnersLeave() {
        compose.runOnIdle {
            var revoked = false
            val old = TrailerPlaybackPool.acquire(context) { revoked = true }!!
            val full = TrailerPlaybackPool.fullPlayback()
            val nested = TrailerPlaybackPool.fullPlayback()
            try {
                assertTrue(revoked)
                old.close()
                assertNull(TrailerPlaybackPool.acquire(context) {})
                full.close()
                full.close()
                assertNull(TrailerPlaybackPool.acquire(context) {})
            } finally { full.close(); nested.close() }
            active = TrailerPlaybackPool.acquire(context) {}!!
            assertNotSame(old.player, active!!.player)
        }
    }

    @Test fun idleTrimDropsThePoolButNotAnActiveTrailer() {
        compose.runOnIdle {
            val first = TrailerPlaybackPool.acquire(context) {}!!
            TrailerPlaybackPool.releaseIdle()
            assertTrue(first.isOwner)
            first.close()
            TrailerPlaybackPool.releaseIdle()
            active = TrailerPlaybackPool.acquire(context) {}!!
            assertNotSame(first.player, active!!.player)
        }
    }
    @Test fun backgroundingReleasesTheActivePreviewAndAllowsAResumeLease() {
        compose.runOnIdle {
            var revoked = false
            val before = TrailerPlaybackPool.acquire(context) { revoked = true }!!
            val revision = TrailerPlaybackPool.availability.value
            TrailerPlaybackPool.background()
            assertTrue(revoked)
            assertFalse(before.isOwner)
            assertTrue(TrailerPlaybackPool.availability.value > revision)
            active = TrailerPlaybackPool.acquire(context) {}!!
            assertNotSame(before.player, active!!.player)
        }
    }

    @Test fun measuresPlayerConstructionVersusWarmHandoff() {
        val fresh = mutableListOf<Long>()
        val pooled = mutableListOf<Long>()
        repeat(12) { index ->
            compose.runOnIdle {
                var start = android.os.SystemClock.elapsedRealtimeNanos()
                ExoPlayer.Builder(context).build().release()
                val freshUs = (android.os.SystemClock.elapsedRealtimeNanos() - start) / 1000
                start = android.os.SystemClock.elapsedRealtimeNanos()
                active = TrailerPlaybackPool.acquire(context) {}!!
                active!!.close()
                val pooledUs = (android.os.SystemClock.elapsedRealtimeNanos() - start) / 1000
                if (index >= 2) { fresh += freshUs; pooled += pooledUs }
            }
        }
        fun median(values: List<Long>) = values.sorted()[values.size / 2]
        android.util.Log.i("PlaybackBench", "constructionMedianUs=${median(fresh)} handoffMedianUs=${median(pooled)} samples=${fresh.size}")
        assertEquals(10, fresh.size)
        assertEquals(10, pooled.size)
    }

    @Test fun reusedPlayerRendersAndSeeksWithoutGhostPlayback() {
        val file = File(context.cacheDir, "trailer-pool-qa.mp4")
        InstrumentationRegistry.getInstrumentation().context.assets.open("seek_preview_device_test.mp4").use { input ->
            file.outputStream().use(input::copyTo)
        }
        val firstFrame = AtomicBoolean(false)
        compose.runOnIdle { active = TrailerPlaybackPool.acquire(context) {}!! }
        val player = active!!.player
        lateinit var playerView: PlayerView
        compose.setContent {
            AndroidView(factory = { PlayerView(it).apply {
                playerView = this
                this.player = player
                useController = false
            } }, onReset = null, onRelease = { it.player = null },
                modifier = Modifier.fillMaxSize())
        }
        try {
            compose.runOnIdle {
                player.volume = 0f
                player.addListener(object : Player.Listener {
                    override fun onRenderedFirstFrame() { firstFrame.set(true) }
                })
                player.setMediaItem(MediaItem.fromUri(Uri.fromFile(file)))
                player.prepare(); player.play()
            }
            compose.waitUntil(15_000) { firstFrame.get() && compose.runOnIdle { player.currentPosition > 500 } }
            compose.runOnIdle {
                val old = active!!
                old.close()
                playerView.player = null
                active = TrailerPlaybackPool.acquire(context) {}!!
                assertSame(player, active!!.player)
                playerView.player = player
                firstFrame.set(false)
                player.setMediaItem(MediaItem.fromUri(Uri.fromFile(file)))
                player.prepare(); player.play()
                old.close()
                assertTrue(player.playWhenReady)
                player.seekTo(20_000)
            }
            compose.waitUntil(15_000) { firstFrame.get() && compose.runOnIdle { player.isPlaying && player.currentPosition >= 20_500 } }
            compose.runOnIdle { assertNull(player.playerError); assertEquals(0f, player.volume, 0f) }
        } finally {
            compose.runOnIdle { active?.close(); active = null; TrailerPlaybackPool.releaseIdle() }
            file.delete()
        }
    }
}
