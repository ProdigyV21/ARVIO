package com.arflix.tv.ui.screens.player.preview

import java.io.IOException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class SeekPreviewCacheWriterTest {
    @Test fun `offers never wait for disk and rapid requests retain only newest pending entry`() = runTest {
        val release = CompletableDeferred<Unit>()
        val written = mutableListOf<Int>()
        val writer = SeekPreviewCacheWriter<Int>(backgroundScope) {
            written += it
            if (it == 1) release.await()
        }
        writer.offer(1)
        runCurrent()
        for (entry in 2..100) writer.offer(entry)
        runCurrent()
        assertEquals(listOf(1), written)
        release.complete(Unit)
        runCurrent()
        assertEquals(listOf(1, 100), written)
        writer.close()
    }

    @Test fun `cache IO and compression failures do not stop subsequent writes`() = runTest {
        val written = mutableListOf<Int>()
        val writer = SeekPreviewCacheWriter<Int>(backgroundScope) {
            when (it) {
                1 -> throw IOException("disk unavailable")
                2 -> throw OutOfMemoryError("compression allocation")
                else -> written += it
            }
        }
        for (entry in 1..3) {
            writer.offer(entry)
            runCurrent()
        }
        assertEquals(listOf(3), written)
        writer.close()
    }

    @Test fun `close cancels active work and drops pending images`() = runTest {
        val started = mutableListOf<Int>()
        var cancelled = false
        val writer = SeekPreviewCacheWriter<Int>(backgroundScope) {
            started += it
            try { awaitCancellation() } finally { cancelled = true }
        }
        writer.offer(1)
        runCurrent()
        writer.offer(2)
        writer.close()
        writer.offer(3)
        runCurrent()
        assertEquals(listOf(1), started)
        assertTrue(cancelled)
    }
}
