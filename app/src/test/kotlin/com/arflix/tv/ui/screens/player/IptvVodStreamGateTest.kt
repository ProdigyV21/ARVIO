package com.arflix.tv.ui.screens.player

import android.app.Application
import androidx.media3.common.C
import androidx.media3.common.Format
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.exoplayer.BaseRenderer
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.RendererCapabilities
import androidx.media3.exoplayer.RenderersFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import com.arflix.tv.data.repository.IptvConnectionLimit
import com.arflix.tv.data.repository.iptvStreamPolicy
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvAdmission
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamServerFixture
import com.arflix.tv.network.IptvStreamSession
import com.arflix.tv.network.IptvStreamSessionTable
import com.arflix.tv.ui.screens.tv.live.LiveDrop
import io.mockk.mockk
import io.mockk.every
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import okhttp3.Call
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvVodStreamGateTest {
    private val alice = IptvProviderAccount.xtream("http://panel.test", "alice", "pass")!!
    private val player = mockk<Player>(relaxed = true)
    private val limitOne = iptvStreamPolicy(alice, IptvConnectionLimit.ONE, null)!!
    private val limitTwo = iptvStreamPolicy(alice, IptvConnectionLimit.TWO, null)!!
    private val checked = iptvStreamPolicy(alice, IptvConnectionLimit.AUTO, 1)!!
    private fun TestScope.table() = IptvStreamSessionTable({ testScheduler.currentTime }, StandardTestDispatcher(testScheduler))

    @Test fun backgroundReopenChecksEvenAfterARecentCloseAndPreservesThePausedPosition() = runTest {
        val table = table()
        val gate = IptvVodStreamGate(table)
        every { player.currentPosition } returns 22_000L
        gate.open(player, limitOne)
        gate.closeForBackground(player)
        gate.reopenAfterBackground()
        assertFalse(table.isColdStart(alice))
        var checks = 0
        assertEquals(IptvAdmission.Full(1, 1), gate.admitReopen(limitOne) { checks++; IptvAccountUsage(1, 1) })
        assertTrue(checks > 0)
        assertNull(gate.session)
        assertEquals(0, table.openCount())
        assertEquals(IptvAdmission.Proceed, gate.admitReopen(limitOne) { IptvAccountUsage(0, 1) })
        assertEquals(IptvVodReopen(22_000L, false), gate.takeReopen())
    }

    @Test fun limitOneStopsAndClosesBeforeOpeningTheNextVersion() = runTest {
        val table = table()
        val gate = IptvVodStreamGate(table)
        val first = gate.open(player, limitOne)!!
        assertTrue("VOD sessions reuse the panel's redirect", first.reusesRedirects)
        val cancelled = mockk<Call>(relaxed = true)
        assertTrue(first.register(cancelled))
        val second = gate.open(player, limitOne)!!
        assertTrue(first.isClosed)
        assertTrue("its close was waited for", first.close().isCompleted)
        verifyOrder { player.stop(); cancelled.cancel() }
        assertFalse(second.isClosed)
        assertEquals(1, table.openCount(alice))
    }

    @Test fun limitTwoKeepsTheOldFrameUntilTheNextPrepares() = runTest {
        val table = table()
        val gate = IptvVodStreamGate(table)
        val first = gate.open(player, limitTwo)!!
        val second = gate.open(player, limitTwo)!!
        assertFalse("still on screen", first.isClosed)
        assertEquals(2, table.openCount(alice))
        verify(exactly = 0) { player.stop() }
        gate.afterPrepare()
        assertTrue(first.isClosed)
        assertFalse(second.isClosed)
        assertEquals(1, table.openCount(alice))
    }

    @Test fun anOrdinarySourceOpensNothingAndLeavingIptvForItClosesTheSession() = runTest {
        val table = table()
        val gate = IptvVodStreamGate(table)
        assertNull(gate.open(player, null))
        verify(exactly = 0) { player.stop() }
        val iptv = gate.open(player, limitTwo)!!
        assertNull(gate.open(player, null))
        assertTrue(iptv.isClosed)
        assertNull(gate.session)
        assertEquals(0, table.openCount())
        assertEquals(0L, currentTime)
    }

    @Test fun anAmbiguousRungReopensOnAFreePanelAndReleasesTheStreamOnAFullOne() = runTest {
        val table = table()
        val gate = IptvVodStreamGate(table)
        gate.open(player, checked)
        var fetches = 0
        assertEquals(IptvAdmission.Proceed, gate.recoverRung(player, 1L, true, LiveDrop.Ambiguous) { fetches++; IptvAccountUsage(0, 1) })
        assertEquals(1, fetches)
        verify(exactly = 1) { player.prepare() }

        val session = gate.session!!
        assertEquals(IptvAdmission.Full(1, 1), gate.recoverRung(player, 1L, true, LiveDrop.Ambiguous) { IptvAccountUsage(1, 1) })
        verify(exactly = 1) { player.prepare() }
        assertTrue(session.isClosed)
        assertNull(gate.session)
        assertEquals(0, table.openCount())
    }

    // The real media source factories over a socket: nothing a loader asks for may escape the session.

    private class AcceptingRenderer(private val type: Int) : BaseRenderer(type) {
        override fun getName() = "accepting"
        override fun supportsFormat(format: Format) = RendererCapabilities.create(
            if (MimeTypes.getTrackType(format.sampleMimeType) == type) C.FORMAT_HANDLED else C.FORMAT_UNSUPPORTED_TYPE
        )
        override fun render(positionUs: Long, elapsedRealtimeUs: Long) = Unit
        override fun isReady() = false
        override fun isEnded() = false
    }

    private class Playing(val session: IptvStreamSession, val tagged: MutableMap<String, Boolean>, val player: ExoPlayer)

    private fun play(panel: IptvStreamServerFixture, path: String, mime: String?, isHls: Boolean, isDash: Boolean): Playing {
        val session = IptvStreamSessionTable().open(alice, 1)
        val tagged = ConcurrentHashMap<String, Boolean>()
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                tagged[chain.request().url.encodedPath] = chain.request().tag(IptvStreamSession::class.java) === session
                chain.proceed(chain.request())
            }
            .readTimeout(40, TimeUnit.SECONDS)
            .build()
        val sources = IptvVodMediaSources(RuntimeEnvironment.getApplication(), client, "ARVIO-test", DefaultExtractorsFactory())
        val item = MediaItem.Builder().setUri("${panel.baseUrl}$path").setMimeType(mime).build()
        val renderers = RenderersFactory { _, _, _, _, _ -> arrayOf(AcceptingRenderer(C.TRACK_TYPE_VIDEO), AcceptingRenderer(C.TRACK_TYPE_AUDIO)) }
        val player = ExoPlayer.Builder(RuntimeEnvironment.getApplication(), renderers).build()
        player.setMediaSource(sources.create(session, mapOf("X-Test" to "1"), item, isHls, isDash, hasSidecars = false))
        player.prepare()
        return Playing(session, tagged, player)
    }

    private fun Playing.awaitRequests(paths: Set<String>) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(40)
        while (!tagged.keys.containsAll(paths)) {
            assertTrue("seen ${tagged.keys}, wanted $paths", System.nanoTime() < deadline)
            Thread.yield()
        }
        assertEquals("a request left the session: $tagged", emptyList<String>(), tagged.filterValues { !it }.keys.toList())
    }

    // The failed version is kept streaming here, which is stricter than a decoder error: that one has already stopped its loader.
    @Test fun aStartupFailoverAtLimitOneEndsTheFailedVersionBeforeTheNextReachesThePanel() {
        IptvStreamServerFixture().use { panel ->
            val table = IptvStreamSessionTable()
            val gate = IptvVodStreamGate(table)
            val openWhenNextLeft = ConcurrentHashMap<String, Int>()
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val path = chain.request().url.encodedPath
                    if (path.endsWith("/fhd.mp4")) openWhenNextLeft[path] = panel.openStreamCount()
                    chain.proceed(chain.request())
                }
                .readTimeout(40, TimeUnit.SECONDS)
                .build()
            val sources = IptvVodMediaSources(RuntimeEnvironment.getApplication(), client, "ARVIO-test", DefaultExtractorsFactory())
            val renderers = RenderersFactory { _, _, _, _, _ -> arrayOf(AcceptingRenderer(C.TRACK_TYPE_VIDEO), AcceptingRenderer(C.TRACK_TYPE_AUDIO)) }
            val player = ExoPlayer.Builder(RuntimeEnvironment.getApplication(), renderers).build()
            // The same order as PlayerScreen's stream effect once tryAdvanceToNextStream has picked a version.
            fun play(session: IptvStreamSession, path: String) {
                val item = MediaItem.fromUri("${panel.baseUrl}$path")
                player.setMediaSource(sources.create(session, emptyMap(), item, isHls = false, isDash = false, hasSidecars = false))
                player.prepare()
                gate.afterPrepare()
                val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(40)
                while (panel.events.none { it.kind == IptvStreamServerFixture.EventKind.OPEN && it.path == path }) {
                    assertTrue("no request for $path", System.nanoTime() < deadline)
                    Thread.yield()
                }
            }
            try {
                val uhd = runBlocking { gate.open(player, limitOne) }!!
                play(uhd, "/movie/alice/pass/uhd.mkv")
                assertEquals("the failed version still streams when failover starts", 1, panel.openStreamCount())
                val fhd = runBlocking { gate.open(player, limitOne) }!!
                assertTrue(uhd.isClosed)
                assertEquals("its socket closed before the next source was built", 0, panel.openStreamCount())
                play(fhd, "/movie/alice/pass/fhd.mp4")
                assertEquals("and was still closed when the next request left", 0, openWhenNextLeft["/movie/alice/pass/fhd.mp4"])
                assertEquals(1, panel.events.count { it.kind == IptvStreamServerFixture.EventKind.OPEN && it.path.endsWith("/uhd.mkv") })
                assertEquals(1, panel.peakOpenSessions)
                assertEquals(1, table.openCount(alice))
            } finally {
                player.release()
                gate.close()
            }
        }
    }

    @Test fun demuxedHlsWithAKeyLoadsEverythingThroughOneSession() {
        IptvStreamServerFixture().use { panel ->
            panel.answer("/vod/master.m3u8", 200, """
                #EXTM3U
                #EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID="a",NAME="en",DEFAULT=YES,URI="audio.m3u8"
                #EXT-X-STREAM-INF:BANDWIDTH=1000000,AUDIO="a"
                video.m3u8
            """.trimIndent())
            panel.answer("/vod/video.m3u8", 200, """
                #EXTM3U
                #EXT-X-TARGETDURATION:6
                #EXT-X-MEDIA-SEQUENCE:0
                #EXT-X-KEY:METHOD=AES-128,URI="/vod/key.bin",IV=0x00000000000000000000000000000000
                #EXTINF:6.0,
                /hold/video1.ts
                #EXT-X-ENDLIST
            """.trimIndent())
            panel.answer("/vod/audio.m3u8", 200, """
                #EXTM3U
                #EXT-X-TARGETDURATION:6
                #EXT-X-MEDIA-SEQUENCE:0
                #EXTINF:6.0,
                /hold/audio1.ts
                #EXT-X-ENDLIST
            """.trimIndent())
            panel.answer("/vod/key.bin", 200, "0123456789abcdef")
            // The audio and video segments must be in flight together, so a per-request limit would time out.
            panel.holdRequests(parties = 2)
            val playing = play(panel, "/vod/master.m3u8", MimeTypes.APPLICATION_M3U8, isHls = true, isDash = false)
            try {
                playing.awaitRequests(
                    setOf("/vod/master.m3u8", "/vod/video.m3u8", "/vod/audio.m3u8", "/vod/key.bin", "/hold/audio1.ts", "/hold/video1.ts")
                )
            } finally {
                playing.player.release()
                playing.session.close()
            }
        }
    }

    @Test fun dashLoadsItsManifestAndBothInitSegmentsThroughOneSession() {
        IptvStreamServerFixture().use { panel ->
            panel.answer("/vod/manifest.mpd", 200, """
                <?xml version="1.0" encoding="UTF-8"?>
                <MPD xmlns="urn:mpeg:dash:schema:mpd:2011" type="static" mediaPresentationDuration="PT60S" minBufferTime="PT2S" profiles="urn:mpeg:dash:profile:isoff-on-demand:2011">
                  <Period start="PT0S" duration="PT60S">
                    <AdaptationSet contentType="video" mimeType="video/mp4" codecs="avc1.42c01e">
                      <SegmentTemplate initialization="/hold/video-init.mp4" media="/hold/video${'$'}Number${'$'}.m4s" startNumber="1" timescale="1000" duration="6000"/>
                      <Representation id="v" bandwidth="1000000" width="1280" height="720"/>
                    </AdaptationSet>
                    <AdaptationSet contentType="audio" mimeType="audio/mp4" codecs="mp4a.40.2">
                      <SegmentTemplate initialization="/hold/audio-init.mp4" media="/hold/audio${'$'}Number${'$'}.m4s" startNumber="1" timescale="1000" duration="6000"/>
                      <Representation id="a" bandwidth="128000" audioSamplingRate="48000"/>
                    </AdaptationSet>
                  </Period>
                </MPD>
            """.trimIndent())
            panel.holdRequests(parties = 2)
            val playing = play(panel, "/vod/manifest.mpd", MimeTypes.APPLICATION_MPD, isHls = false, isDash = true)
            try {
                playing.awaitRequests(setOf("/vod/manifest.mpd", "/hold/video-init.mp4", "/hold/audio-init.mp4"))
            } finally {
                playing.player.release()
                playing.session.close()
            }
        }
    }
}
