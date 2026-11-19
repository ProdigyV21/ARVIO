@file:OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.player.cache

import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheEvictor
import androidx.media3.datasource.cache.CacheSpan
import androidx.media3.datasource.cache.ContentMetadata
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class PlaybackCacheTest {
    private val gb = 1L shl 30

    /** Minimal fake cache implementation to avoid ByteBuddy / mockk bytecode manipulation issues on JVM. */
    private class FakeCache : Cache {
        val removed = mutableListOf<CacheSpan>()
        var evictor: CacheEvictor? = null

        override fun removeSpan(span: CacheSpan) {
            removed += span
            evictor?.onSpanRemoved(this, span)
        }
        override fun getUid(): Long = 1L
        override fun release() {}
        override fun addListener(key: String, listener: Cache.Listener): java.util.NavigableSet<CacheSpan> = java.util.TreeSet()
        override fun removeListener(key: String, listener: Cache.Listener) {}
        override fun getCachedSpans(key: String): java.util.NavigableSet<CacheSpan> = java.util.TreeSet()
        override fun getKeys(): Set<String> = emptySet()
        override fun getCacheSpace(): Long = 0L
        override fun startReadWrite(key: String, position: Long, length: Long): CacheSpan = throw UnsupportedOperationException()
        override fun startReadWriteNonBlocking(key: String, position: Long, length: Long): CacheSpan? = null
        override fun startFile(key: String, position: Long, length: Long): File = File("fake")
        override fun commitFile(file: File, length: Long) {}
        override fun releaseHoleSpan(holeSpan: CacheSpan) {}
        override fun removeResource(key: String) {}
        override fun isCached(key: String, position: Long, length: Long): Boolean = false
        override fun getCachedLength(key: String, position: Long, length: Long): Long = 0L
        override fun getCachedBytes(key: String, position: Long, length: Long): Long = 0L
        override fun applyContentMetadataMutations(key: String, mutations: androidx.media3.datasource.cache.ContentMetadataMutations) {}
        override fun getContentMetadata(key: String): ContentMetadata = object : ContentMetadata {
            override fun get(key: String, defaultValue: ByteArray?): ByteArray? = defaultValue
            override fun get(key: String, defaultValue: String?): String? = defaultValue
            override fun get(key: String, defaultValue: Long): Long = defaultValue
            override fun contains(key: String): Boolean = false
        }
    }

    @Test
    fun `max allowed keeps a 1 GB reserve and counts what the cache already holds`() {
        assertEquals(9 * gb, PlaybackCache.maxAllowedBytes(freeBytes = 8 * gb, usedBytes = 2 * gb))
    }

    @Test
    fun `max allowed never drops below the minimum`() {
        assertEquals(PlaybackCache.MIN_BYTES, PlaybackCache.maxAllowedBytes(freeBytes = 0, usedBytes = 0))
    }

    @Test
    fun `effective limit is the user's choice when it fits`() {
        assertEquals(5 * gb, PlaybackCache.effectiveLimitBytes(limitGb = 5, maxAllowedBytes = 20 * gb))
    }

    @Test
    fun `effective limit is capped by free space`() {
        assertEquals(3 * gb, PlaybackCache.effectiveLimitBytes(limitGb = 10, maxAllowedBytes = 3 * gb))
    }

    @Test
    fun `next limit cycles through the choices`() {
        assertEquals(2, PlaybackCache.nextLimitGb(1, 100 * gb))
        assertEquals(20, PlaybackCache.nextLimitGb(10, 100 * gb))
        assertEquals(1, PlaybackCache.nextLimitGb(50, 100 * gb))
    }

    @Test
    fun `next limit skips choices that do not fit and wraps around`() {
        assertEquals(1, PlaybackCache.nextLimitGb(5, 6 * gb))
        assertEquals(5, PlaybackCache.nextLimitGb(2, 6 * gb))
    }

    @Test
    fun `unknown free space allows every choice`() {
        assertTrue(PlaybackCache.fits(50, 0))
        assertFalse(PlaybackCache.fits(50, 10 * gb))
        assertEquals(50, PlaybackCache.nextLimitGb(20, 0))
    }

    @Test
    fun `evictor removes least recently used spans when over the limit`() {
        val evictor = AdjustableLruCacheEvictor(maxBytes = 300)
        evictor.onCacheInitialized()
        val cache = FakeCache().apply { this.evictor = evictor }
        val oldest = span(position = 0, length = 100, touched = 1)
        val middle = span(position = 100, length = 100, touched = 2)
        val newest = span(position = 200, length = 100, touched = 3)
        evictor.onSpanAdded(cache, oldest)
        evictor.onSpanAdded(cache, middle)
        evictor.onSpanAdded(cache, newest)
        assertTrue(cache.removed.isEmpty())

        evictor.onSpanAdded(cache, span(position = 300, length = 100, touched = 4))
        assertEquals(listOf(oldest), cache.removed)
    }

    @Test
    fun `shrinking the limit trims at once`() {
        val evictor = AdjustableLruCacheEvictor(maxBytes = 1_000)
        evictor.onCacheInitialized()
        val cache = FakeCache().apply { this.evictor = evictor }
        val a = span(position = 0, length = 100, touched = 1)
        val b = span(position = 100, length = 100, touched = 2)
        val c = span(position = 200, length = 100, touched = 3)
        listOf(a, b, c).forEach { evictor.onSpanAdded(cache, it) }

        evictor.maxBytes = 150
        evictor.trim(cache)
        assertEquals(listOf(a, b), cache.removed)
    }

    @Test
    fun `touching a span makes it most recently used`() {
        val evictor = AdjustableLruCacheEvictor(maxBytes = 200)
        evictor.onCacheInitialized()
        val cache = FakeCache().apply { this.evictor = evictor }
        val a = span(position = 0, length = 100, touched = 1)
        val b = span(position = 100, length = 100, touched = 2)
        evictor.onSpanAdded(cache, a)
        evictor.onSpanAdded(cache, b)
        val aTouched = span(position = 0, length = 100, touched = 3)
        evictor.onSpanTouched(cache, a, aTouched)

        evictor.onSpanAdded(cache, span(position = 200, length = 100, touched = 4))
        assertEquals(listOf(b), cache.removed)
    }

    @Test
    fun `restarting with populated cache accounts for existing size when calculating startup limit`() {
        var reads = 0
        val evictor = AdjustableLruCacheEvictor(
            maxBytes = 10 * gb,
            userLimitGb = 10,
            freeBytes = { reads++; 3 * gb },
            clockMs = { 10_000L },
        )
        val cache = FakeCache().apply { this.evictor = evictor }
        repeat(8) { index ->
            evictor.onSpanAdded(cache, span(index * gb, gb, index.toLong()))
            assertTrue(cache.removed.isEmpty())
        }
        assertEquals(0, reads)
        evictor.onCacheInitialized()
        assertEquals(1, reads)
        assertEquals(10 * gb, evictor.maxBytes)
        assertTrue(cache.removed.isEmpty())
    }

    @Test
    fun `evictor updates limit when free space decreases during normal playback use`() {
        var free = 12 * gb
        var now = 10_000L
        var reads = 0
        val evictor = AdjustableLruCacheEvictor(
            maxBytes = 10 * gb, userLimitGb = 10,
            freeBytes = { reads++; free }, clockMs = { now },
        )
        val cache = FakeCache().apply { this.evictor = evictor }
        val oldest = span(0, gb, 1)
        evictor.onSpanAdded(cache, oldest)
        evictor.onSpanAdded(cache, span(gb, gb, 2))
        evictor.onCacheInitialized()
        assertEquals(10 * gb, evictor.maxBytes)

        free = gb / 2
        now += AdjustableLruCacheEvictor.STORAGE_CHECK_INTERVAL_MS
        evictor.onStartFile(cache, "key", 2 * gb, androidx.media3.common.C.LENGTH_UNSET.toLong())
        assertEquals(3 * gb / 2, evictor.maxBytes)
        assertEquals(listOf(oldest), cache.removed)
        assertEquals(2, reads)
        evictor.onStartFile(cache, "key", 2 * gb, 1L)
        assertEquals(2, reads)
    }

    private fun span(position: Long, length: Long, touched: Long) =
        CacheSpan("key", position, length, touched, null)
}
