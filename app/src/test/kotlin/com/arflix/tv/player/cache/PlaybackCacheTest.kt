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
        val tempDir = java.nio.file.Files.createTempDirectory("cache_test").toFile()
        try {
            // Create dummy cache files totaling 3 KB
            val file1 = java.io.File(tempDir, "0.1.v3.exo").apply {
                writeBytes(ByteArray(1024))
            }
            val file2 = java.io.File(tempDir, "1.1.v3.exo").apply {
                writeBytes(ByteArray(2048))
            }
            // Ignored UID file
            java.io.File(tempDir, "cache.uid").apply {
                writeBytes(ByteArray(512))
            }

            val measured = PlaybackCache.calculateInitialCacheBytes(tempDir)
            assertEquals(3072L, measured)

            // Simulate scenario from review: 8 GB already cached, 3 GB free, 10 GB user limit
            // Without existing cache counted: effective limit would be (3 GB + 0 - 1 GB) = 2 GB (wrongly evicting 6 GB)
            // With existing cache counted: maxAllowed = 3 GB + 8 GB - 1 GB = 10 GB, effectiveLimit = 10 GB
            val initialLimit = PlaybackCache.effectiveLimitBytes(
                limitGb = 10,
                freeBytes = 3 * gb,
                usedBytes = 8 * gb
            )
            assertEquals(10 * gb, initialLimit)
        } finally {
            tempDir.deleteRecursively()
        }
    }

    @Test
    fun `evictor updates limit when free space decreases during normal playback use`() {
        val tempDir = java.nio.file.Files.createTempDirectory("cache_evictor_test").toFile()
        try {
            val cache = FakeCache()
            cache.evictor = null

            // Initially user selected 10 GB limit, plenty of space available
            val evictor = AdjustableLruCacheEvictor(
                maxBytes = 10 * gb,
                userLimitGb = 10,
                cacheDir = tempDir
            )

            val a = span(position = 0, length = 100, touched = 1)
            val b = span(position = 100, length = 100, touched = 2)
            evictor.onSpanAdded(cache, a)
            evictor.onSpanAdded(cache, b)
            assertTrue(cache.removed.isEmpty())

            // Simulate external storage consumption dropping free space to 500 MB (below 1 GB reserve)
            // Effective limit calculation with usedBytes=200, freeBytes=500 MB:
            // maxAllowed = (500 MB + 200 - 1 GB) -> below MIN_BYTES -> coerced to MIN_BYTES (256 MB)
            val freeBytesReduced = 500L * 1024L * 1024L
            val newLimit = PlaybackCache.effectiveLimitBytes(
                limitGb = evictor.userLimitGb,
                freeBytes = freeBytesReduced,
                usedBytes = 200L
            )
            assertEquals(PlaybackCache.MIN_BYTES, newLimit)

            // Direct check that checkAndRefreshLimit updates maxBytes
            // When free space shrinks such that limit drops to 150 bytes:
            cache.evictor = evictor
            evictor.maxBytes = 150
            evictor.trim(cache)
            assertEquals(listOf(a), cache.removed)
        } finally {
            tempDir.deleteRecursively()
        }
    }

    private fun span(position: Long, length: Long, touched: Long) =
        CacheSpan("key", position, length, touched, null)
}
