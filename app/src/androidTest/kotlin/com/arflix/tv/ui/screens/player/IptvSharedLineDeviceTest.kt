package com.arflix.tv.ui.screens.player

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.repository.IptvAccountInfoParser
import com.arflix.tv.data.repository.IptvConnectionLimit
import com.arflix.tv.data.repository.ResolvedIptvChannelPolicy
import com.arflix.tv.data.repository.iptvStreamPolicy
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvAdmission
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamSessionTable
import com.arflix.tv.ui.screens.home.HomeIptvPreviewSessions
import com.arflix.tv.ui.screens.home.homeHeroIptvClient
import com.arflix.tv.ui.screens.home.homeIptvPreviewSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/** Exercises the limiter and real Media3 HTTP requests without touching a saved subscription. */
@RunWith(AndroidJUnit4::class)
@androidx.annotation.OptIn(UnstableApi::class)
class IptvSharedLineDeviceTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext

    @Test
    fun returningToPausedVodDoesNotOpenAFullLine() {
        onDevice { panel, player ->
            val sessions = IptvStreamSessionTable()
            val gate = IptvVodStreamGate(sessions)
            val sources = IptvVodMediaSources(context, panel.client, "ARVIO test", DefaultExtractorsFactory())
            val policy = panel.policy
            try {
                val first = checkNotNull(gate.open(player, policy))
                player.setMediaSource(sources.create(first, emptyMap(), panel.item, false, false, false))
                player.prepare()
                awaitReady(player)
                player.seekTo(250L)
                val position = player.currentPosition
                gate.closeForBackground(player)
                gate.reopenAfterBackground()
                panel.otherViewer.set(true)
                val requestsBefore = panel.streams.get()
                assertFalse(sessions.isColdStart(panel.account))
                assertEquals(IptvAdmission.Full(1, 1), gate.admitReopen(policy) { panel.usage() })
                assertEquals(requestsBefore, panel.streams.get())
                assertEquals(0, sessions.openCount())

                panel.otherViewer.set(false)
                assertEquals(IptvAdmission.Proceed, gate.admitReopen(policy) { panel.usage() })
                val reopen = checkNotNull(gate.takeReopen())
                assertEquals(position, reopen.positionMs)
                assertFalse(reopen.playWhenReady)
                val next = checkNotNull(gate.open(player, policy))
                player.setMediaSource(sources.create(next, emptyMap(), panel.item, false, false, false), reopen.positionMs)
                player.playWhenReady = reopen.playWhenReady
                player.prepare()
                awaitReady(player)
                assertTrue(panel.streams.get() > requestsBefore)
                assertFalse(player.isPlaying)
            } finally {
                gate.close()
            }
        }
    }

    @Test
    fun homePreviewNeedsVerifiedRoomAndDoesNotDisplaceLocalPlayback() {
        onDevice { panel, player ->
            val sessions = IptvStreamSessionTable()
            val policy = ResolvedIptvChannelPolicy(panel.policy)
            val active = sessions.open(panel.account, 1)
            val preview = HomeIptvPreviewSessions(sessions) { panel.usage() }
            try {
                assertNull(preview.admit(player) { policy })
                assertFalse(active.isClosed)
                assertEquals(0, panel.checks.get())
                active.close().join()
                panel.otherViewer.set(true)
                assertNull(preview.admit(player) { policy })
                assertEquals(1, panel.checks.get())
                assertEquals(0, panel.streams.get())

                panel.otherViewer.set(false)
                val available = HomeIptvPreviewSessions(sessions) { panel.usage() }
                try {
                    val session = checkNotNull(available.admit(player) { policy }?.session)
                    assertNotNull(session)
                    player.setMediaSource(homeIptvPreviewSource(
                        context, homeHeroIptvClient(panel.client), "ARVIO test", session, panel.item, false
                    ))
                    player.prepare()
                    awaitReady(player)
                    assertTrue(panel.streams.get() > 0)
                    assertTrue(available.leave(player, stopOnLeave = true))
                    assertTrue(session.isClosed)
                    assertEquals(0, sessions.openCount())
                } finally {
                    available.close()
                }
            } finally {
                active.close()
                preview.close()
            }
        }
    }

    private fun onDevice(test: suspend (Panel, ExoPlayer) -> Unit) = runBlocking {
        Panel().use { panel ->
            withContext(Dispatchers.Main) {
                val player = ExoPlayer.Builder(context).build()
                try {
                    test(panel, player)
                } finally {
                    player.release()
                }
            }
        }
    }

    private suspend fun awaitReady(player: Player) = withTimeout(20_000) {
        while (player.playbackState != Player.STATE_READY) {
            player.playerError?.let { throw AssertionError("Fixture media failed", it) }
            delay(25)
        }
    }

    private inner class Panel : AutoCloseable {
        val client = OkHttpClient()
        val otherViewer = AtomicBoolean(false)
        val checks = AtomicInteger()
        val streams = AtomicInteger()
        private val bytes = instrumentation.context.assets.open("seek_preview_device_test.mp4").use { it.readBytes() }
        private val server = MockWebServer()
        val account: IptvProviderAccount
        val policy: com.arflix.tv.data.repository.IptvStreamPolicy
        val item: MediaItem

        init {
            server.dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse = when (request.requestUrl?.encodedPath) {
                    "/player_api.php" -> {
                        checks.incrementAndGet()
                        val active = if (otherViewer.get()) 1 else 0
                        MockResponse().setBody("""{"user_info":{"auth":1,"status":"Active","active_cons":"$active","max_connections":"1"}}""")
                    }
                    "/video.mp4" -> {
                        streams.incrementAndGet()
                        if (otherViewer.get()) returnFull() else mediaResponse(request)
                    }
                    else -> MockResponse().setResponseCode(404)
                }
            }
            server.start()
            account = checkNotNull(IptvProviderAccount.xtream(server.url("/").toString(), "qa", "fixture"))
            policy = checkNotNull(iptvStreamPolicy(account, IptvConnectionLimit.AUTO, 1))
            item = MediaItem.fromUri(server.url("/video.mp4").toString())
        }

        suspend fun usage(): IptvAccountUsage? = withContext(Dispatchers.IO) {
            val request = Request.Builder().url(server.url("/player_api.php")).build()
            client.newCall(request).execute().use { IptvAccountInfoParser.parseXtreamAvailability(it.body.string()) }
        }

        private fun returnFull() = MockResponse().setResponseCode(503)

        private fun mediaResponse(request: RecordedRequest): MockResponse {
            val range = request.getHeader("Range")
            val start = range?.substringAfter("bytes=")?.substringBefore('-')?.toIntOrNull() ?: 0
            if (start !in bytes.indices) return MockResponse().setResponseCode(416)
            return MockResponse()
                .addHeader("Content-Type", "video/mp4")
                .addHeader("Accept-Ranges", "bytes")
                .setBody(Buffer().write(bytes, start, bytes.size - start))
                .apply {
                    if (range != null) {
                        setResponseCode(206)
                        addHeader("Content-Range", "bytes $start-${bytes.lastIndex}/${bytes.size}")
                    }
                }
        }

        override fun close() {
            server.shutdown()
            client.connectionPool.evictAll()
        }
    }
}
