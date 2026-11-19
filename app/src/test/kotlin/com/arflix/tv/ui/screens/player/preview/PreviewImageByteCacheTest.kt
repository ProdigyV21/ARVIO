package com.arflix.tv.ui.screens.player.preview

import org.junit.Assert.*
import org.junit.Test

class PreviewImageByteCacheTest {
    @Test fun `recent sheets survive crossing a boundary within a byte budget`() {
        val cache = PreviewImageByteCache(10)
        val a = PreviewImageByteCache.Key("a")
        val b = PreviewImageByteCache.Key("b")
        val c = PreviewImageByteCache.Key("c")
        cache.put(a, ByteArray(4) { 1 })
        cache.put(b, ByteArray(4) { 2 })
        assertEquals(1, cache.get(a)!!.first().toInt())
        cache.put(c, ByteArray(4) { 3 })
        assertNotNull(cache.get(a))
        assertNull(cache.get(b))
        assertNotNull(cache.get(c))
        cache.clear()
        assertNull(cache.get(a))
    }

    @Test fun `BIF ranges are distinct and oversized data cannot evict useful sheets`() {
        val cache = PreviewImageByteCache(8)
        val first = PreviewImageByteCache.Key("bif", 64, 4)
        val next = PreviewImageByteCache.Key("bif", 68, 4)
        cache.put(first, byteArrayOf(1, 2, 3, 4))
        cache.put(next, byteArrayOf(5, 6, 7, 8))
        assertEquals(1, cache.get(first)!!.first().toInt())
        cache.put(PreviewImageByteCache.Key("large"), ByteArray(9))
        assertNotNull(cache.get(first))
        assertNotNull(cache.get(next))
    }
}
