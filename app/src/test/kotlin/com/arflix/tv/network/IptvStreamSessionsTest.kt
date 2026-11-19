package com.arflix.tv.network

import com.arflix.tv.network.IptvStreamServerFixture.Event
import com.arflix.tv.network.IptvStreamServerFixture.EventKind.CLOSE
import com.arflix.tv.network.IptvStreamServerFixture.EventKind.OPEN
import com.arflix.tv.network.IptvStreamServerFixture.Hit
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

class IptvStreamSessionsTest {
    private val alice = IptvProviderAccount.xtream("http://panel.test", "alice", "pass")!!
    private val bob = IptvProviderAccount.xtream("http://panel.test", "bob", "pass")!!

    /** A client built like Live TV's, with a private guard and table. */
    private class Rig(clock: () -> Long = System::currentTimeMillis) : AutoCloseable {
        val panel = IptvStreamServerFixture()
        val table = IptvStreamSessionTable(clock)
        val connections = IptvPlaybackConnections()
        private val guard = IptvProviderRequestGuard()
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(connections)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .addInterceptor(guard.preflightInterceptor(playback = true))
            .addNetworkInterceptor(guard.playbackInterceptor())
            .readTimeout(40, TimeUnit.SECONDS)
            .build()
        private val responses = ArrayList<Response>()

        fun request(path: String, base: String = panel.baseUrl, range: String? = null) =
            Request.Builder().url("$base$path").apply { if (range != null) header("Range", range) }.build()

        /** Leaves the body open and unread after one byte, as a player holding a buffer would. */
        fun play(session: IptvStreamSession, path: String, base: String = panel.baseUrl, range: String? = null): Response {
            val response = session.callFactory(client).newCall(request(path, base, range)).execute()
            responses.add(response)
            response.body!!.source().readByte()
            return response
        }

        fun playUntagged(path: String, on: OkHttpClient = client, range: String? = null): Pair<Call, Response> {
            val call = on.newCall(request(path, range = range))
            val response = call.execute()
            responses.add(response)
            response.body!!.source().readByte()
            return call to response
        }

        override fun close() {
            connections.cancelAll()
            responses.forEach { runCatching { it.close() } }
            panel.close()
            client.connectionPool.evictAll()
            client.dispatcher.executorService.shutdownNow()
        }
    }

    /** A cancelled Call has closed its socket, so any further read throws even with bytes still queued. */
    private fun Response.assertStillStreaming() {
        val source = body!!.source()
        var remaining = 1024L * 1024
        val sink = Buffer()
        while (remaining > 0) {
            val read = source.read(sink, minOf(remaining, 64L * 1024))
            assertTrue("stream ended early", read > 0)
            remaining -= read
            sink.clear()
        }
    }

    /** The panel's own order hides a late close, so this asks the stream that had to end. */
    private fun assertFinishedClosing(session: IptvStreamSession) {
        assertTrue("old stream not closed", session.isClosed)
        assertTrue("its close was not waited for", session.close().isCompleted)
        assertEquals(0, session.openCallCount)
    }

    // Rapid channel and version changes

    @Test fun channelChangesAtLimitTwoEvictTheOldestFirst() = runBlocking {
        Rig().use { rig ->
            val sessions = ArrayList<IptvStreamSession>()
            repeat(6) { channel ->
                rig.table.makeRoom(alice, 2)
                sessions.add(rig.table.open(alice, 2).also { rig.play(it, "/live/alice/pass/$channel.ts") })
                assertTrue(rig.table.openCount(alice) <= 2)
            }
            assertEquals(2, rig.panel.peakOpenSessions)
            assertEquals(listOf(true, true, true, true, false, false), sessions.map { it.isClosed })
            val opens = rig.panel.events.filter { it.kind == OPEN }.map { it.path.substringAfterLast('/') }
            assertEquals((0 until 6).map { "$it.ts" }, opens)
            (2 until 6).forEach { channel ->
                val closed = rig.panel.events.indexOf(Event(CLOSE, "alice", "/live/alice/pass/${channel - 2}.ts"))
                val opened = rig.panel.events.indexOf(Event(OPEN, "alice", "/live/alice/pass/$channel.ts"))
                assertTrue("channel $channel opened before the oldest closed", closed in 0 until opened)
            }
            assertEquals(2, rig.panel.openStreamCount())
        }
    }

    @Test fun versionSwitchAtLimitOneClosesTheOldStreamBeforeOpeningTheNew() = runBlocking {
        Rig().use { rig ->
            val first = rig.table.open(alice, 1)
            rig.play(first, "/movie/alice/pass/1080.mkv")
            rig.table.makeRoom(alice, 1, first)
            assertFinishedClosing(first)
            rig.play(rig.table.open(alice, 1), "/movie/alice/pass/2160.mkv")
            assertEquals(1, rig.panel.peakOpenSessions)
            assertEquals(
                listOf(
                    Event(OPEN, "alice", "/movie/alice/pass/1080.mkv"),
                    Event(CLOSE, "alice", "/movie/alice/pass/1080.mkv"),
                    Event(OPEN, "alice", "/movie/alice/pass/2160.mkv"),
                ),
                rig.panel.events,
            )
        }
    }

    @Test fun versionSwitchAtLimitTwoOpensTheNewThenClosesTheOld() = runBlocking {
        Rig().use { rig ->
            val first = rig.table.open(alice, 2)
            rig.play(first, "/movie/alice/pass/1080.mkv")
            rig.table.makeRoom(alice, 2)
            assertFalse(first.isClosed)
            val second = rig.table.open(alice, 2)
            rig.play(second, "/movie/alice/pass/2160.mkv")
            assertEquals(2, rig.panel.peakOpenSessions)
            first.close().join()
            assertEquals(1, rig.panel.openStreamCount())
            assertEquals(
                listOf(
                    Event(OPEN, "alice", "/movie/alice/pass/1080.mkv"),
                    Event(OPEN, "alice", "/movie/alice/pass/2160.mkv"),
                    Event(CLOSE, "alice", "/movie/alice/pass/1080.mkv"),
                ),
                rig.panel.events,
            )
            assertFalse(second.isClosed)
        }
    }

    // Duplicate accounts

    @Test fun duplicatePlaylistsOfOneAccountPeakAtOneStreamWhileAnotherAccountKeepsItsOwn() = runBlocking {
        Rig().use { rig ->
            val entryOne = IptvProviderAccount.xtream("http://panel.test/get.php", "alice", "pass")!!
            val entryTwo = IptvProviderAccount.xtream("https://panel.test:443/player_api.php", "alice", "pass")!!
            val first = rig.table.open(entryOne, 1)
            rig.play(first, "/live/alice/pass/1.ts")
            rig.table.makeRoom(entryTwo, 1)
            assertFinishedClosing(first)
            rig.play(rig.table.open(entryTwo, 1), "/live/alice/pass/2.ts")
            rig.table.makeRoom(bob, 1)
            rig.play(rig.table.open(bob, 1), "/live/bob/pass/1.ts")
            assertEquals(1, rig.panel.peakOpenSessions("alice"))
            assertEquals(1, rig.panel.peakOpenSessions("bob"))
            assertEquals(1, rig.table.openCount(entryOne))
            assertEquals(1, rig.table.openCount(bob))
            assertEquals(2, rig.panel.openStreamCount())
        }
    }

    // Redirects

    @Test fun redirectToACdnCountsAgainstTheOriginalAccountAndReleasesOnce() = runBlocking {
        val clock = AtomicLong(0L)
        Rig(clock::get).use { rig ->
            IptvStreamServerFixture().use { cdn ->
                rig.panel.redirect("/live/alice/pass/1.ts", "${cdn.baseUrl}/cdn/opaque-token.ts")
                val session = rig.table.open(alice, 1)
                rig.play(session, "/live/alice/pass/1.ts")
                assertEquals(1, session.openCallCount)
                assertEquals(1, rig.table.openCount(alice))
                assertEquals(1, rig.panel.streamRequestCount)
                assertEquals(listOf(Event(OPEN, "", "/cdn/opaque-token.ts")), cdn.events)

                val closing = session.close()
                closing.join()
                assertEquals(0, cdn.openStreamCount())
                assertEquals(0, rig.table.openCount(alice))
                assertEquals(0, session.openCallCount)

                clock.set(DEFAULT_IPTV_COLD_START_MS)
                assertSame(closing, session.close())
                assertTrue("a second close must not count as a new close", rig.table.isColdStart(alice))
                assertEquals(
                    listOf(Event(OPEN, "", "/cdn/opaque-token.ts"), Event(CLOSE, "", "/cdn/opaque-token.ts")),
                    cdn.events,
                )
            }
        }
    }

    /** Matroska reads from 0, then the Cues near the end, then the first cluster: three requests for one viewer. */
    @Test fun aVodSessionAsksThePanelOnceForItsRangeRequestsWhileLiveAndUntaggedFollowItEveryTime() = runBlocking {
        Rig().use { rig ->
            IptvStreamServerFixture().use { cdn ->
                val path = "/movie/alice/pass/7.mkv"
                rig.panel.redirect(path, "${cdn.baseUrl}/cdn/lb-token.mkv")
                val ranges = listOf(null, "bytes=5750304750-", "bytes=4814-")
                val vod = rig.table.open(alice, 1, reusesRedirects = true)
                ranges.forEach { range -> rig.play(vod, path, range = range).close() }
                assertEquals(listOf(Hit(path, null)), rig.panel.hits)
                assertEquals(ranges.map { Hit("/cdn/lb-token.mkv", it) }, cdn.hits)
                assertEquals(0, vod.openCallCount)

                vod.close().join()
                val live = rig.table.open(alice, 1)
                ranges.forEach { range -> rig.play(live, path, range = range).close() }
                ranges.forEach { range -> rig.playUntagged(path, range = range).second.close() }
                assertEquals(listOf(Hit(path, null)) + (ranges + ranges).map { Hit(path, it) }, rig.panel.hits)
                assertEquals((ranges + ranges + ranges).map { Hit("/cdn/lb-token.mkv", it) }, cdn.hits)
            }
        }
    }

    @Test fun aRefusedRedirectTargetSendsTheVodSessionBackToThePanelOnceAndNeverLoops() = runBlocking {
        Rig().use { rig ->
            IptvStreamServerFixture().use { cdn ->
                val path = "/movie/alice/pass/7.mkv"
                rig.panel.redirect(path, "${cdn.baseUrl}/cdn/old.mkv")
                val session = rig.table.open(alice, 1, reusesRedirects = true)
                rig.play(session, path).close()
                cdn.answer("/cdn/old.mkv", 403)
                rig.panel.redirect(path, "${cdn.baseUrl}/cdn/new.mkv")
                rig.play(session, path, range = "bytes=4814-").close()
                rig.play(session, path, range = "bytes=9000-").close()
                assertEquals(2, rig.panel.streamRequestCount)
                assertEquals(
                    listOf(
                        Hit("/cdn/old.mkv", null),
                        Hit("/cdn/old.mkv", "bytes=4814-"),
                        Hit("/cdn/new.mkv", "bytes=4814-"),
                        Hit("/cdn/new.mkv", "bytes=9000-"),
                    ),
                    cdn.hits,
                )

                // Target and fresh redirect both refused: one answer comes back, with no third try.
                cdn.answer("/cdn/new.mkv", 403)
                cdn.answer("/cdn/gone.mkv", 404)
                rig.panel.redirect(path, "${cdn.baseUrl}/cdn/gone.mkv")
                session.callFactory(rig.client).newCall(rig.request(path, range = "bytes=12000-")).execute().use {
                    assertEquals(404, it.code)
                }
                assertEquals(3, rig.panel.streamRequestCount)
                assertEquals(listOf(Hit("/cdn/new.mkv", "bytes=12000-"), Hit("/cdn/gone.mkv", "bytes=12000-")), cdn.hits.drop(4))
                assertEquals(0, session.openCallCount)
            }
        }
    }

    // HLS and DASH

    /** Enqueues through OkHttp's real dispatcher, so anything the session's own count held back would miss the barrier. */
    private fun enqueued(factory: Call.Factory, request: Request): CompletableFuture<Int> {
        val code = CompletableFuture<Int>()
        factory.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { code.completeExceptionally(e) }
            override fun onResponse(call: Call, response: Response) { response.use { code.complete(it.code) } }
        })
        return code
    }

    /** The server answers 504 to every request unless all of them are in flight together. */
    private fun oneSessionOpensEveryRequestTogether(paths: List<String>) {
        Rig().use { rig ->
            rig.panel.holdRequests(paths.size)
            val session = rig.table.open(alice, 1)
            val factory = session.callFactory(rig.client)
            val pending = paths.map { path -> enqueued(factory, rig.request(path)) }
            assertEquals(paths.map { 200 }, pending.map { it.get(40, TimeUnit.SECONDS) })
            assertEquals(paths.size, rig.panel.streamRequestCount)
            assertEquals(1, rig.table.openCount(alice))
            assertFalse(session.isClosed)
        }
    }

    /** Demuxed audio at its busiest: two media playlists, two segments and one key, all inside OkHttp's five per host. */
    @Test fun oneHlsSessionAtLimitOneOpensPlaylistsKeyAndSegmentsTogether() = oneSessionOpensEveryRequestTogether(
        listOf("/hold/video.m3u8", "/hold/audio.m3u8", "/hold/key.bin", "/hold/video1.ts", "/hold/audio1.aac")
    )

    @Test fun oneDashSessionAtLimitOneOpensInitsKeyAndSegmentsTogether() = oneSessionOpensEveryRequestTogether(
        listOf("/hold/video-init.mp4", "/hold/audio-init.mp4", "/hold/key.bin", "/hold/video1.m4s", "/hold/audio1.m4s")
    )

    // Cancellation

    @Test fun cancelDuringMakeRoomOpensNothing() = runBlocking {
        val table = IptvStreamSessionTable()
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        val old = table.open(alice, 1)
        assertTrue(old.register(mockk {
            every { cancel() } answers {
                entered.countDown()
                release.await(10, TimeUnit.SECONDS)
                Unit
            }
        }))
        var opened: IptvStreamSession? = null
        val zap = launch(Dispatchers.Default) {
            table.makeRoom(alice, 1, old)
            opened = table.open(alice, 1)
        }
        assertTrue(entered.await(5, TimeUnit.SECONDS))
        zap.cancelAndJoin()
        release.countDown()
        assertNull(opened)
        assertEquals(0, table.openCount())
    }

    @Test fun closedSessionsStrayCallFailsWithoutReachingTheServer() = runBlocking {
        Rig().use { rig ->
            val session = rig.table.open(alice, 1)
            val factory = session.callFactory(rig.client)
            session.close().join()
            val error = assertThrows(IptvStreamSessionClosedException::class.java) {
                factory.newCall(rig.request("/live/alice/pass/1.ts")).execute()
            }
            assertFalse(error.message.orEmpty().contains("alice"))
            assertTrue(isIptvStreamSessionClosed(IOException("wrapped", IOException("again", error))))
            assertEquals(0, rig.panel.streamRequestCount)
            assertEquals(0, session.openCallCount)
        }
    }

    // Cold start

    @Test fun coldStartNeedsNoOpenSessionAndAQuietPeriodPerAccount() = runBlocking {
        val clock = AtomicLong(1_000L)
        val table = IptvStreamSessionTable(clock::get)
        assertTrue(table.isColdStart(alice))
        val session = table.open(alice, 1)
        assertFalse(table.isColdStart(alice))
        assertTrue(table.isColdStart(bob))
        clock.addAndGet(60_000L)
        assertFalse("an open session is not a cold start at any age", table.isColdStart(alice))
        session.close().join()
        assertFalse(table.isColdStart(alice))
        clock.addAndGet(DEFAULT_IPTV_COLD_START_MS - 1)
        assertFalse(table.isColdStart(alice))
        clock.addAndGet(1L)
        assertTrue(table.isColdStart(alice))
    }

    // Non-IPTV and untagged traffic

    @Test fun untaggedRequestsAreNotCountedDelayedOrCancelledByAFullAccount() = runBlocking {
        Rig().use { rig ->
            val session = rig.table.open(alice, 1)
            rig.play(session, "/live/alice/pass/1.ts")
            val derived = rig.client.newBuilder().build()
            val untagged = (0 until 3).map { index -> rig.playUntagged("/live/other/pass/$index.ts", if (index == 0) derived else rig.client) }
            untagged.forEach { (call, _) -> assertNull(call.request().tag(IptvStreamSession::class.java)) }
            assertEquals(3, rig.panel.peakOpenSessions("other"))
            assertEquals(1, rig.table.openCount())

            session.close().join()

            untagged.forEach { (call, response) ->
                assertFalse(call.isCanceled())
                response.assertStillStreaming()
            }
            assertEquals(3, rig.panel.openStreamCount())
        }
    }
}
