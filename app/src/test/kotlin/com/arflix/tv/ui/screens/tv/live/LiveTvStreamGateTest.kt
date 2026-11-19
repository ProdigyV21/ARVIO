package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.Player
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.IptvConnectionLimit
import com.arflix.tv.data.repository.IptvStreamPolicy
import com.arflix.tv.data.repository.iptvStreamPolicy
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvPlaybackConnections
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamServerFixture
import com.arflix.tv.network.IptvStreamServerFixture.Drop
import com.arflix.tv.network.IptvStreamServerFixture.Event
import com.arflix.tv.network.IptvStreamServerFixture.EventKind.CLOSE
import com.arflix.tv.network.IptvStreamServerFixture.EventKind.DROP
import com.arflix.tv.network.IptvStreamServerFixture.EventKind.OPEN
import com.arflix.tv.network.IptvStreamSession
import com.arflix.tv.network.IptvStreamSessionTable
import com.arflix.tv.network.MAX_IPTV_CLOSE_JOIN_MS
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
class LiveTvStreamGateTest {
    private val alice = IptvProviderAccount.xtream("http://panel.test", "alice", "pass")!!
    private val bob = IptvProviderAccount.xtream("http://panel.test", "bob", "pass")!!
    private val player = mockk<Player>(relaxed = true)

    private fun channel(id: Int = 1) = IptvChannel(id = "list_a:xtream:$id", name = "$id", streamUrl = "http://panel.test/live/$id.ts", group = "", xtreamStreamId = id)
    private fun limited(limit: IptvConnectionLimit = IptvConnectionLimit.ONE, account: IptvProviderAccount = alice) = iptvStreamPolicy(account, limit, null)!!
    private fun checked(allowance: Int = 1, account: IptvProviderAccount = alice) = iptvStreamPolicy(account, IptvConnectionLimit.AUTO, allowance)!!

    private fun TestScope.virtualTable(clock: () -> Long = { testScheduler.currentTime }) =
        IptvStreamSessionTable(clock, StandardTestDispatcher(testScheduler))

    private fun gate(
        table: IptvStreamSessionTable,
        policy: IptvStreamPolicy?,
        check: suspend (IptvProviderAccount) -> IptvAccountUsage? = { null },
        network: () -> Boolean = { true },
    ) = LiveTvStreamGate(player, table, { policy }, check, {}, network)

    private fun blockingCall(entered: CountDownLatch, release: CountDownLatch): Call = mockk {
        every { cancel() } answers {
            entered.countDown()
            release.await(10, TimeUnit.SECONDS)
            Unit
        }
    }

    /** A client built like Live TV's. */
    private class Rig : AutoCloseable {
        val panel = IptvStreamServerFixture()
        private val responses = ArrayList<Response>()
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(IptvPlaybackConnections())
            .retryOnConnectionFailure(true)
            .readTimeout(40, TimeUnit.SECONDS)
            .build()

        fun play(session: IptvStreamSession, path: String): Response {
            val response = session.callFactory(client).newCall(Request.Builder().url("${panel.baseUrl}$path").build()).execute()
            responses.add(response)
            response.body!!.source().readByte()
            return response
        }

        override fun close() {
            responses.forEach { runCatching { it.close() } }
            panel.close()
            client.dispatcher.executorService.shutdownNow()
        }
    }

    private fun Response.drain() {
        val sink = Buffer()
        var total = 0L
        while (true) {
            val read = body!!.source().read(sink, 64L * 1024)
            if (read == -1L) return
            sink.clear()
            total += read
            assertTrue("stream did not end", total < 16L * 1024 * 1024)
        }
    }

    // Rapid channel changes, through the production order

    @Test fun twentyZapsHoldOneStreamAndFinishEachCloseBeforeTheNextOpens() = runBlocking {
        Rig().use { rig ->
            val table = IptvStreamSessionTable()
            val gate = gate(table, limited())
            var playing: IptvStreamSession? = null
            repeat(20) { number ->
                assertEquals(LiveTvGateResult.Ready, gate.tune(channel(number)))
                playing?.let { old ->
                    assertTrue("old stream not closed", old.isClosed)
                    assertTrue("its close was not waited for", old.close().isCompleted)
                    assertEquals(0, old.openCallCount)
                }
                assertEquals(0, table.openCount(alice))
                val session = gate.openSession()!!
                assertEquals(1, table.openCount(alice))
                rig.play(session, "/live/alice/pass/$number.ts")
                playing = session
            }
            gate.close()
            playing!!.close().join()
            assertEquals(0, rig.panel.openStreamCount())
            assertEquals(0, table.openCount())
            assertEquals(1, rig.panel.peakOpenSessions)
            assertEquals(20, rig.panel.streamRequestCount)
            assertEquals(
                (0 until 20).flatMap { listOf(Event(OPEN, "alice", "/live/alice/pass/$it.ts"), Event(CLOSE, "alice", "/live/alice/pass/$it.ts")) },
                rig.panel.events,
            )
        }
    }

    @Test fun theTuneRunsStopClearCloseCheckInThatOrder() = runTest {
        val table = virtualTable()
        val order = ArrayList<String>()
        every { player.stop() } answers { order += "stop" }
        every { player.clearMediaItems() } answers { order += "clear" }
        // Starts on Bob's stream, so that Alice's account is a cold start and has to be asked.
        var policy = limited(account = bob)
        val gate = LiveTvStreamGate(player, table, { order += "policy"; policy }, { order += "check"; IptvAccountUsage(0, 2) })
        gate.tune(channel())
        assertTrue(gate.openSession()!!.register(mockk { every { cancel() } answers { order += "cancel" } }))
        order.clear()
        policy = checked()
        assertEquals(LiveTvGateResult.Ready, gate.tune(channel(2)))
        assertEquals(listOf("stop", "clear", "policy", "cancel", "check"), order)
        assertEquals(alice, gate.openSession()!!.account)
    }

    @Test fun theNextStreamWaitsForTheOldStreamsCloseUpToItsCap() = runTest {
        val table = IptvStreamSessionTable(clock = { 0L })
        val gate = gate(table, limited())
        gate.tune(channel())
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        assertTrue(gate.openSession()!!.register(blockingCall(entered, release)))
        assertEquals(LiveTvGateResult.Ready, gate.tune(channel(2)))
        assertEquals(MAX_IPTV_CLOSE_JOIN_MS, currentTime)
        release.countDown()
    }

    @Test fun aZapDuringTheJoinOpensOnlyTheLastChannel() = runBlocking {
        val table = IptvStreamSessionTable()
        val gate = gate(table, limited())
        gate.tune(channel())
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        val first = gate.openSession()!!
        assertTrue(first.register(blockingCall(entered, release)))
        var openedDuringFirstZap: IptvStreamSession? = null
        val zap = launch(Dispatchers.Default) {
            gate.tune(channel(2))
            openedDuringFirstZap = gate.openSession()
        }
        assertTrue(entered.await(5, TimeUnit.SECONDS))
        zap.cancelAndJoin()
        release.countDown()
        assertNull(openedDuringFirstZap)
        assertEquals(0, table.openCount())

        assertEquals(LiveTvGateResult.Ready, gate.tune(channel(3)))
        val last = gate.openSession()!!
        assertEquals(1, table.openCount(alice))
        assertFalse(last.isClosed)
        assertTrue(first.isClosed)
    }

    @Test fun aZapDuringThePanelCheckCancelsItAndOpensOnlyTheLastChannel() = runTest {
        var fetches = 0
        var fetchCancelled = false
        var hang = true
        val table = virtualTable()
        val gate = gate(table, checked(), check = {
            fetches++
            if (hang) {
                try { awaitCancellation() } catch (e: CancellationException) { fetchCancelled = true; throw e }
            }
            IptvAccountUsage(0, 2)
        })
        val zap = launch { gate.tune(channel()); gate.openSession() }
        runCurrent()
        zap.cancelAndJoin()
        advanceUntilIdle()
        assertTrue(fetchCancelled)
        assertEquals("no fetch after the cancel", 1, fetches)
        assertEquals(0, table.openCount())
        hang = false
        assertEquals(LiveTvGateResult.Ready, gate.tune(channel(2)))
        assertNotNull(gate.openSession())
        assertEquals(1, table.openCount())
    }

    @Test fun aZapStopsARetryThatIsStillOnItsWayToReopen() = runTest {
        val table = virtualTable()
        val gate = gate(table, limited())
        gate.tune(channel())
        gate.openSession()
        var reopened = false
        gate.reopen(this, { true }, { reopened = true }, { error("limited path only") })
        gate.tune(channel(2))
        advanceUntilIdle()
        assertFalse(reopened)

        val retry = launch { delay(10_000L); reopened = true }
        gate.track(retry)
        gate.tune(channel(3))
        advanceUntilIdle()
        assertFalse(reopened)
        assertTrue(retry.isCancelled)
    }

    // The panel's answer

    @Test fun aColdStartAsksOnceThenTwentySameAccountZapsAskNothingAndWaitNoTime() = runTest {
        var fetches = 0
        var usage = IptvAccountUsage(0, 1)
        val gate = gate(virtualTable(), checked(), check = { fetches++; usage })
        assertEquals(LiveTvGateResult.Ready, gate.tune(channel()))
        gate.openSession()
        assertEquals(1, fetches)
        // Any check from here on would answer full and wait out the deadline.
        usage = IptvAccountUsage(1, 1)
        val start = currentTime
        repeat(20) { number ->
            assertEquals(LiveTvGateResult.Ready, gate.tune(channel(number + 2)))
            gate.openSession()
        }
        assertEquals(1, fetches)
        assertEquals(0L, currentTime - start)
    }

    @Test fun aFullColdStartIsBusyAfterTheDeadlineAndOpensNothing() = runTest {
        var fetches = 0
        val table = virtualTable()
        val gate = gate(table, checked(), check = { fetches++; IptvAccountUsage(1, 1) })
        assertEquals(LiveTvGateResult.Busy(1, 1), gate.tune(channel()))
        assertEquals(3, fetches)
        assertEquals(6_000L, currentTime)
        assertEquals(0, table.openCount())
    }

    // A null policy runs today's path

    @Test fun noPolicyOpensUntaggedWithoutWaitingOrAsking() = runTest {
        var fetches = 0
        val table = virtualTable()
        val gate = gate(table, null, check = { fetches++; null })
        assertEquals(LiveTvGateResult.Ready, gate.tune(channel()))
        assertFalse(gate.isLimited)
        assertNull(gate.openSession())
        assertTrue(gate.retainedSourceUsable)
        var openedNow = false
        gate.reopen(this, { false }, { error("limited path only") }, { openedNow = true })
        assertTrue("synchronously, as today", openedNow)
        assertEquals(0, table.openCount())
        assertEquals(0, fetches)
        assertEquals(0L, currentTime)
        verify { player.stop(); player.clearMediaItems() }
    }

    // Recovery

    @Test fun networkLostWaitsForTheNetworkWithoutAskingThePanel() = runTest {
        var fetches = 0
        var polls = 0
        val table = virtualTable()
        val gate = gate(table, checked(), check = { fetches++; IptvAccountUsage(1, 1) }, network = { ++polls > 3 })
        gate.skipNextCheck()
        gate.tune(channel())
        val playing = gate.openSession()!!
        assertEquals(LiveTvGateResult.Ready, gate.reconnect(LiveDrop.NetworkLost))
        assertTrue(playing.isClosed)
        assertEquals(0, table.openCount())
        assertEquals(3 * DEFAULT_LIVE_NETWORK_POLL_MS, currentTime)
        assertEquals(0, fetches)
    }

    @Test fun ambiguousWithAFullPanelStopsWithTheStreamReleased() = runTest {
        val table = virtualTable()
        val gate = gate(table, checked(), check = { IptvAccountUsage(1, 1) })
        gate.skipNextCheck()
        gate.tune(channel())
        val playing = gate.openSession()!!
        assertEquals(LiveTvGateResult.Busy(1, 1), gate.reconnect(LiveDrop.Ambiguous))
        assertTrue(playing.isClosed)
        assertEquals(0, table.openCount())
    }

    @Test fun ambiguousWithAnUnknownOrFreePanelReconnects() = runTest {
        for (answer in listOf<IptvAccountUsage?>(null, IptvAccountUsage(0, 1))) {
            var fetches = 0
            val gate = gate(virtualTable(), checked(), check = { fetches++; answer })
            gate.skipNextCheck()
            gate.tune(channel())
            gate.openSession()
            assertEquals(LiveTvGateResult.Ready, gate.reconnect(LiveDrop.Ambiguous))
            assertEquals(1, fetches)
        }
    }

    @Test fun retryAfterABusyReconnectAsksThePanelAgainAndOpensNothing() = runTest {
        val table = virtualTable()
        var usage = IptvAccountUsage(3, 3)
        var fetches = 0
        val gate = gate(table, checked(allowance = 3), check = { fetches++; usage })
        gate.skipNextCheck()
        gate.tune(channel())
        gate.openSession()
        assertEquals(LiveTvGateResult.Busy(3, 3), gate.reconnect(LiveDrop.Ambiguous))
        assertFalse("this is the window that used to skip the check", table.isColdStart(alice))

        fetches = 0
        assertEquals("Retry while still full", LiveTvGateResult.Busy(3, 3), gate.tune(channel()))
        assertTrue(fetches > 0)
        assertEquals(0, table.openCount())

        fetches = 0
        usage = IptvAccountUsage(2, 3)
        assertEquals("Retry once a slot is free", LiveTvGateResult.Ready, gate.tune(channel()))
        assertEquals(1, fetches)
        gate.openSession()

        fetches = 0
        assertEquals("the next zap is ordinary again", LiveTvGateResult.Ready, gate.tune(channel(2)))
        assertEquals(0, fetches)
    }

    private fun aDroppedStreamReconnectsAfterItsRelease(drop: Drop) = runBlocking {
        Rig().use { rig ->
            val table = IptvStreamSessionTable()
            val gate = gate(table, limited())
            gate.tune(channel())
            val first = gate.openSession()!!
            val path = "/live/alice/pass/1.ts"
            val body = rig.play(first, path)
            rig.panel.dropStream(drop)
            if (drop == Drop.RESET) assertThrows(IOException::class.java) { body.drain() } else body.drain()
            assertFalse("the drop alone releases nothing", first.isClosed)

            assertEquals(LiveTvGateResult.Ready, gate.reconnect(LiveDrop.Ambiguous))

            assertTrue(first.isClosed)
            assertTrue("its close was not waited for", first.close().isCompleted)
            assertEquals(0, first.openCallCount)
            assertEquals(0, table.openCount())
            assertEquals("OkHttp must not reopen on its own, and nothing opens before the release", 1, rig.panel.streamRequestCount)
            rig.play(gate.openSession()!!, path)
            assertEquals(listOf(Event(OPEN, "alice", path), Event(DROP, "alice", path), Event(OPEN, "alice", path)), rig.panel.events)
            assertEquals(1, rig.panel.peakOpenSessions)
            assertEquals(2, rig.panel.streamRequestCount)
            gate.close()
        }
    }

    @Test fun aResetStreamReconnectsAfterItsReleaseAtPeakOne() = aDroppedStreamReconnectsAfterItsRelease(Drop.RESET)

    @Test fun aCleanlyEndedStreamReconnectsAfterItsReleaseAtPeakOne() = aDroppedStreamReconnectsAfterItsRelease(Drop.CLEAN_EOF)
}
