@file:OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.player.cache

import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheSpan
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PlaybackCacheTest {
    private val gb = 1L shl 30

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
        val removed = mutableListOf<CacheSpan>()
        val evictor = AdjustableLruCacheEvictor(maxBytes = 300)
        val cache = mockk<Cache>()
        every { cache.removeSpan(any()) } answers {
            val span = firstArg<CacheSpan>()
            removed += span
            evictor.onSpanRemoved(cache, span)
        }
        val oldest = span(position = 0, length = 100, touched = 1)
        val middle = span(position = 100, length = 100, touched = 2)
        val newest = span(position = 200, length = 100, touched = 3)
        evictor.onSpanAdded(cache, oldest)
        evictor.onSpanAdded(cache, middle)
        evictor.onSpanAdded(cache, newest)
        assertTrue(removed.isEmpty())

        evictor.onSpanAdded(cache, span(position = 300, length = 100, touched = 4))
        assertEquals(listOf(oldest), removed)
    }

    @Test
    fun `shrinking the limit trims at once`() {
        val removed = mutableListOf<CacheSpan>()
        val evictor = AdjustableLruCacheEvictor(maxBytes = 1_000)
        val cache = mockk<Cache>()
        every { cache.removeSpan(any()) } answers {
            val span = firstArg<CacheSpan>()
            removed += span
            evictor.onSpanRemoved(cache, span)
        }
        val a = span(position = 0, length = 100, touched = 1)
        val b = span(position = 100, length = 100, touched = 2)
        val c = span(position = 200, length = 100, touched = 3)
        listOf(a, b, c).forEach { evictor.onSpanAdded(cache, it) }

        evictor.maxBytes = 150
        evictor.trim(cache)
        assertEquals(listOf(a, b), removed)
    }

    @Test
    fun `touching a span makes it most recently used`() {
        val removed = mutableListOf<CacheSpan>()
        val evictor = AdjustableLruCacheEvictor(maxBytes = 200)
        val cache = mockk<Cache>()
        every { cache.removeSpan(any()) } answers {
            val span = firstArg<CacheSpan>()
            removed += span
            evictor.onSpanRemoved(cache, span)
        }
        val a = span(position = 0, length = 100, touched = 1)
        val b = span(position = 100, length = 100, touched = 2)
        evictor.onSpanAdded(cache, a)
        evictor.onSpanAdded(cache, b)
        val aTouched = span(position = 0, length = 100, touched = 3)
        evictor.onSpanTouched(cache, a, aTouched)

        evictor.onSpanAdded(cache, span(position = 200, length = 100, touched = 4))
        assertEquals(listOf(b), removed)
    }

    private fun span(position: Long, length: Long, touched: Long) =
        CacheSpan("key", position, length, touched, null)
}
