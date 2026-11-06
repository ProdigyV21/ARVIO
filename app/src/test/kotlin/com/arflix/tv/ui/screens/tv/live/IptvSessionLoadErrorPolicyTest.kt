package com.arflix.tv.ui.screens.tv.live

import android.app.Application
import android.net.Uri
import android.os.Looper
import androidx.media3.common.C
import androidx.media3.common.Format
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.TransferListener
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.BaseRenderer
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.RendererCapabilities
import androidx.media3.exoplayer.RenderersFactory
import androidx.media3.exoplayer.source.LoadEventInfo
import androidx.media3.exoplayer.source.MediaLoadData
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
import com.arflix.tv.network.IptvPlaybackConnections
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamServerFixture
import com.arflix.tv.network.IptvStreamSessionClosedException
import com.arflix.tv.network.IptvStreamSessionTable
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvSessionLoadErrorPolicyTest {
    private val spec = DataSpec(Uri.parse("https://provider.test/live/u/p/1.ts"))
    private val account = IptvProviderAccount.xtream("http://panel.test", "u", "p")!!

    private fun delay(policy: IptvLoadErrorHandlingPolicy, error: IOException, bytesLoaded: Long): Long =
        policy.getRetryDelayMsFor(
            LoadErrorHandlingPolicy.LoadErrorInfo(
                LoadEventInfo(1L, spec, spec.uri, emptyMap(), 0L, 1L, bytesLoaded),
                MediaLoadData(C.DATA_TYPE_MEDIA),
                error,
                2,
            )
        )

    private val sessionBound = IptvLoadErrorHandlingPolicy(IptvMidStreamDrop.SURFACE)
    private val unbound = IptvLoadErrorHandlingPolicy()

    @Test fun onlyASessionBoundMidStreamDropLeavesTheReconnectToTheApp() {
        assertEquals(C.TIME_UNSET, delay(sessionBound, IOException("Connection reset"), bytesLoaded = 1L))
        assertEquals("an initial connect failure stays inside Media3", 1_000L, delay(sessionBound, IOException("Connection refused"), bytesLoaded = 0L))
        assertEquals("any other source keeps retrying", 1_000L, delay(unbound, IOException("Connection reset"), bytesLoaded = 1_000_000L))
        val closed = IOException("Load failed", IOException("Wrapped", IptvStreamSessionClosedException()))
        assertEquals("a closed session is never retried", C.TIME_UNSET, delay(unbound, closed, bytesLoaded = 0L))
        assertEquals(C.TIME_UNSET, delay(sessionBound, closed, bytesLoaded = 0L))
    }

    private fun liveDrop(url: String, mime: String? = null, sessionBound: Boolean = true, panelCheckAllowed: Boolean = true, isCatchup: Boolean = false, isStalker: Boolean = false) =
        liveMidStreamDrop(MediaItem.Builder().setUri(url).setMimeType(mime).build(), sessionBound, panelCheckAllowed, isCatchup, isStalker)

    @Test fun liveDropsSurfaceOnlyWhenThePanelCanBeAskedOrTheLinkIsStalkerAndCatchupKeepsMedia3sRetry() {
        val ts = "http://panel.test/live/u/p/1.ts"
        assertEquals(IptvMidStreamDrop.SURFACE, liveDrop(ts, MimeTypes.VIDEO_MP2T))
        assertEquals(IptvMidStreamDrop.SURFACE, liveDrop("http://panel.test/live/u/p/1"))
        assertEquals("live DASH keeps chunk retries", IptvMidStreamDrop.RETRY, liveDrop("http://panel.test/live/u/p/1.mpd"))
        assertEquals(IptvMidStreamDrop.RETRY, liveDrop("http://panel.test/live/u/p/1", MimeTypes.APPLICATION_MPD))
        assertEquals("no session", IptvMidStreamDrop.RETRY, liveDrop(ts, sessionBound = false))
        assertEquals("no panel to ask", IptvMidStreamDrop.RETRY, liveDrop(ts, MimeTypes.VIDEO_MP2T, panelCheckAllowed = false))
        assertEquals("catch-up", IptvMidStreamDrop.RETRY, liveDrop("http://panel.test/timeshift/u/p/60/2026-11-02:20-00/1.ts", MimeTypes.VIDEO_MP2T, isCatchup = true))
        assertEquals("catch-up", IptvMidStreamDrop.RETRY, liveDrop("http://panel.test/live/u/p/1.ts?utc=1&lutc=2", isCatchup = true))
        val stalker = "http://portal.test/play/live.php?mac=m&stream=1&extension=ts&play_token=t"
        assertEquals("a Stalker link is refetched by the app", IptvMidStreamDrop.SURFACE, liveDrop(stalker, panelCheckAllowed = false, isStalker = true))
        assertEquals(IptvMidStreamDrop.RETRY, liveDrop(stalker, sessionBound = false, panelCheckAllowed = false, isStalker = true))
        assertEquals(IptvMidStreamDrop.RETRY, liveDrop(stalker, panelCheckAllowed = false, isCatchup = true, isStalker = true))
    }

    private var nowMs = 1_000_000L

    @Test fun progressiveVodResumesAFirstDropAndSurfacesASecondInsideTheWindow() {
        assertEquals(IptvMidStreamDrop.SURFACE_REPEATED, vodMidStreamDrop(isHls = false, isDash = false, panelCheckAllowed = true))
        assertEquals(IptvMidStreamDrop.RETRY, vodMidStreamDrop(isHls = true, isDash = false, panelCheckAllowed = true))
        assertEquals(IptvMidStreamDrop.RETRY, vodMidStreamDrop(isHls = false, isDash = true, panelCheckAllowed = true))
        assertEquals(IptvMidStreamDrop.RETRY, vodMidStreamDrop(isHls = false, isDash = false, panelCheckAllowed = false))
        val policy = IptvLoadErrorHandlingPolicy(IptvMidStreamDrop.SURFACE_REPEATED) { nowMs }
        assertEquals(1_000L, delay(policy, IOException("Connection reset"), bytesLoaded = 1_000L))
        nowMs += DEFAULT_IPTV_REPEAT_DROP_WINDOW_MS + 1L
        assertEquals("past the window it starts over", 1_000L, delay(policy, IOException("Connection reset"), bytesLoaded = 1_000L))
        nowMs += DEFAULT_IPTV_REPEAT_DROP_WINDOW_MS
        assertEquals(C.TIME_UNSET, delay(policy, IOException("Connection reset"), bytesLoaded = 1L))
    }

    // Real ProgressiveMediaPeriod through a session factory: what Media3 1.9.0 really passes to the policy.

    private class IdleRenderer : BaseRenderer(C.TRACK_TYPE_VIDEO) {
        override fun getName() = "idle"
        override fun supportsFormat(format: Format) = RendererCapabilities.create(C.FORMAT_UNSUPPORTED_TYPE)
        override fun render(positionUs: Long, elapsedRealtimeUs: Long) = Unit
        override fun isReady() = false
        override fun isEnded() = false
    }

    private class Decision(val bytesLoaded: Long, val delayMs: Long, val error: IOException)

    private class RecordingPolicy(private val real: IptvLoadErrorHandlingPolicy) : LoadErrorHandlingPolicy {
        val decisions = LinkedBlockingQueue<Decision>()
        override fun getFallbackSelectionFor(
            fallbackOptions: LoadErrorHandlingPolicy.FallbackOptions,
            loadErrorInfo: LoadErrorHandlingPolicy.LoadErrorInfo,
        ) = real.getFallbackSelectionFor(fallbackOptions, loadErrorInfo)
        override fun getRetryDelayMsFor(loadErrorInfo: LoadErrorHandlingPolicy.LoadErrorInfo): Long =
            real.getRetryDelayMsFor(loadErrorInfo).also {
                decisions.add(Decision(loadErrorInfo.loadEventInfo.bytesLoaded, it, loadErrorInfo.exception))
            }
        override fun getMinimumLoadableRetryCount(dataType: Int) = real.getMinimumLoadableRetryCount(dataType)
    }

    private fun bytesSeen(latch: CountDownLatch) = object : TransferListener {
        override fun onTransferInitializing(source: DataSource, dataSpec: DataSpec, isNetwork: Boolean) = Unit
        override fun onTransferStart(source: DataSource, dataSpec: DataSpec, isNetwork: Boolean) = Unit
        override fun onTransferEnd(source: DataSource, dataSpec: DataSpec, isNetwork: Boolean) = Unit
        override fun onBytesTransferred(source: DataSource, dataSpec: DataSpec, isNetwork: Boolean, bytesTransferred: Int) {
            if (bytesTransferred > 0) latch.countDown()
        }
    }

    private fun playerFor(url: String, policy: LoadErrorHandlingPolicy, listener: TransferListener): Pair<ExoPlayer, OkHttpClient> {
        val client = OkHttpClient.Builder().addInterceptor(IptvPlaybackConnections()).retryOnConnectionFailure(true).build()
        val session = IptvStreamSessionTable().open(account, 1)
        val upstream = OkHttpDataSource.Factory(session.callFactory(client)).setTransferListener(listener)
        val source = ProgressiveMediaSource.Factory(upstream).setLoadErrorHandlingPolicy(policy)
            .createMediaSource(MediaItem.fromUri(url))
        val idle = RenderersFactory { _, _, _, _, _ -> arrayOf(IdleRenderer()) }
        val player = ExoPlayer.Builder(RuntimeEnvironment.getApplication(), idle).build()
        player.setMediaSource(source)
        player.prepare()
        return player to client
    }

    private fun RecordingPolicy.next(): Decision {
        val decision = decisions.poll(15, TimeUnit.SECONDS)
        assertNotNull("Media3 never consulted the policy", decision)
        return decision!!
    }

    @Test fun media3ReportsBytesReadForAMidStreamDropAndTheSessionPolicyStopsItsRetry() {
        IptvStreamServerFixture().use { panel ->
            val policy = RecordingPolicy(sessionBound)
            val read = CountDownLatch(1)
            val (player, _) = playerFor("${panel.baseUrl}/live/u/p/1.ts", policy, bytesSeen(read))
            try {
                assertTrue(read.await(15, TimeUnit.SECONDS))
                panel.dropStream(IptvStreamServerFixture.Drop.RESET)
                val decision = policy.next()
                assertTrue("bytesLoaded was ${decision.bytesLoaded}", decision.bytesLoaded > 0L)
                assertEquals(C.TIME_UNSET, decision.delayMs)
                assertTrue("error was ${decision.error.cause}", decision.error.cause is java.net.SocketException)
                assertEquals(1, panel.streamRequestCount)
                // Robolectric pauses the main looper, and it delivers the player's error.
                var waits = 0
                while (player.playerError == null && waits++ < 1_500) {
                    shadowOf(Looper.getMainLooper()).idle()
                    assertEquals("no second decision: Media3 must not retry", null, policy.decisions.poll(10, TimeUnit.MILLISECONDS))
                }
                assertNotNull("the fatal drop must surface to onPlayerError", player.playerError)
                assertEquals(1, panel.streamRequestCount)
            } finally {
                player.release()
            }
        }
    }

    @Test fun aCatchupDropOnALimitedAccountIsRetriedInPlaceByMedia3() {
        val catchup = MediaItem.Builder().setUri("http://panel.test/timeshift/u/p/60/2026-11-02:20-00/1.ts").setMimeType(MimeTypes.VIDEO_MP2T).build()
        val drop = liveMidStreamDrop(catchup, sessionBound = true, panelCheckAllowed = true, isCatchup = true)
        IptvStreamServerFixture().use { panel ->
            val policy = RecordingPolicy(IptvLoadErrorHandlingPolicy(drop))
            val read = CountDownLatch(1)
            val (player, _) = playerFor("${panel.baseUrl}/live/u/p/1.ts", policy, bytesSeen(read))
            try {
                assertTrue(read.await(15, TimeUnit.SECONDS))
                panel.dropStream(IptvStreamServerFixture.Drop.RESET)
                val decision = policy.next()
                assertTrue("bytesLoaded was ${decision.bytesLoaded}", decision.bytesLoaded > 0L)
                assertNotEquals(C.TIME_UNSET, decision.delayMs)
                val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(15)
                while (panel.streamRequestCount < 2 && System.nanoTime() < deadline) Thread.yield()
                assertEquals("Media3 reopens the same URL on its own", 2, panel.streamRequestCount)
                assertEquals(null, player.playerError)
            } finally {
                player.release()
            }
        }
    }
}
