package com.arflix.tv.ui.screens.player.preview

import com.google.common.util.concurrent.SettableFuture
import java.io.IOException
import java.util.concurrent.Executor
import kotlinx.coroutines.async
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Assert.*
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class SeekPreviewDecoderSessionTest {
    private class Decoder : SeekPreviewDecoder<Long> {
        val requests = mutableListOf<Pair<Long, SettableFuture<Long>>>()
        var closes = 0
        override fun frameAt(positionMs: Long) = SettableFuture.create<Long>().also {
            requests += positionMs to it
        }
        override fun close() { closes++ }
    }
    private val direct = Executor { it.run() }

    @Test fun `cancelled caller does not destroy decoding and late frame is retained`() = runTest {
        val decoder = Decoder()
        val frames = mutableListOf<Long>()
        val session = SeekPreviewDecoderSession<Long>(this, direct)
        val first = async { session.frameAt("a", 10, { decoder }, frames::add) }
        runCurrent()
        first.cancel()
        runCurrent()
        assertFalse(decoder.requests.single().second.isCancelled)
        assertEquals(0, decoder.closes)
        decoder.requests.single().second.set(10)
        runCurrent()
        assertEquals(listOf(10L), frames)
        session.close()
        assertEquals(1, decoder.closes)
    }

    @Test fun `newest target drains active decode and reuses decoder instead of preparing from zero`() = runTest {
        val decoder = Decoder()
        var created = 0
        val session = SeekPreviewDecoderSession<Long>(this, direct)
        val factory = { created++; decoder }
        val first = async { session.frameAt("a", 10, factory) {} }
        runCurrent()
        first.cancel()
        val second = async { session.frameAt("a", 40, factory) {} }
        runCurrent()
        assertEquals(1, decoder.requests.size)
        decoder.requests[0].second.set(10)
        runCurrent()
        assertEquals(listOf(10L, 40L), decoder.requests.map { it.first })
        assertEquals(1, created)
        decoder.requests[1].second.set(40)
        assertEquals(40L, second.await())
        session.close()
    }

    @Test fun `timed out UI can receive late completion without another retry or cooldown`() = runTest {
        val decoder = Decoder()
        val frames = mutableListOf<Long>()
        val session = SeekPreviewDecoderSession<Long>(this, direct)
        val caller = async { withTimeoutOrNull(100) { session.frameAt("a", 30, { decoder }, frames::add) } }
        runCurrent()
        advanceTimeBy(101)
        assertNull(caller.await())
        decoder.requests.single().second.set(30)
        runCurrent()
        assertEquals(listOf(30L), frames)
        session.close()
    }

    @Test fun `old decoder failure does not poison replacement target`() = runTest {
        val decoder = Decoder()
        val session = SeekPreviewDecoderSession<Long>(this, direct)
        val first = async { session.frameAt("a", 10, { decoder }) {} }
        runCurrent()
        first.cancel()
        val second = async { session.frameAt("a", 20, { decoder }) {} }
        runCurrent()
        decoder.requests[0].second.setException(IOException("test"))
        runCurrent()
        decoder.requests[1].second.set(20)
        assertEquals(20L, second.await())
        session.close()
    }

    @Test fun `idle disposal releases decoder once and source replacement creates a new decoder`() = runTest {
        val a = Decoder()
        val b = Decoder()
        val session = SeekPreviewDecoderSession<Long>(this, direct)
        val first = async { session.frameAt("a", 10, { a }) {} }
        runCurrent()
        a.requests[0].second.set(10)
        first.await()
        val second = async { session.frameAt("b", 20, { b }) {} }
        runCurrent()
        assertEquals(1, a.closes)
        b.requests[0].second.set(20)
        second.await()
        advanceTimeBy(1_501)
        runCurrent()
        assertEquals(1, b.closes)
        session.close()
        assertEquals(1, b.closes)
    }

    @Test fun `closing prevents late results and does not leave an idle decoder`() = runTest {
        val decoder = Decoder()
        val frames = mutableListOf<Long>()
        val session = SeekPreviewDecoderSession<Long>(this, direct)
        val caller = async { session.frameAt("a", 10, { decoder }, frames::add) }
        runCurrent()
        caller.cancel()
        session.close()
        decoder.requests[0].second.set(10)
        runCurrent()
        assertTrue(frames.isEmpty())
        assertEquals(1, decoder.closes)
    }
}
