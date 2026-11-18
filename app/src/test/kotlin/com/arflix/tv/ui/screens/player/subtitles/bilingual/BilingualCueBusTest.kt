package com.arflix.tv.ui.screens.player.subtitles.bilingual

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class BilingualCueBusTest {

    @Test fun `translation attaches to its own cue`() {
        val bus = BilingualCueBus()
        val t = bus.showCue("Hi.", "Hi.", null)
        assertTrue(bus.deliverTranslation(t, "Hi.", "嗨。", failed = false))
        assertEquals("嗨。", bus.frame.value?.translation)
        assertEquals("Hi.", bus.frame.value?.original)
    }

    @Test fun `cached translation is shown immediately`() {
        val bus = BilingualCueBus()
        bus.showCue("Hi.", "Hi.", "嗨。")
        assertEquals("嗨。", bus.frame.value?.translation)
    }

    @Test fun `late translation after a new cue is dropped`() {
        val bus = BilingualCueBus()
        val old = bus.showCue("First line.", "First line.", null)
        bus.showCue("Second line.", "Second line.", null)
        assertFalse(bus.deliverTranslation(old, "First line.", "第一行。", failed = false))
        assertEquals("Second line.", bus.frame.value?.original)
        assertNull(bus.frame.value?.translation)
    }

    @Test fun `late translation after a seek or track switch (clear) is dropped`() {
        val bus = BilingualCueBus()
        val t = bus.showCue("Line.", "Line.", null)
        bus.clear()
        assertFalse(bus.deliverTranslation(t, "Line.", "台词。", failed = false))
        assertNull(bus.frame.value)
    }

    @Test fun `same text shown again gets a new token, old token is stale`() {
        val bus = BilingualCueBus()
        val t1 = bus.showCue("Yes.", "Yes.", null)
        bus.clear()
        val t2 = bus.showCue("Yes.", "Yes.", null)
        assertFalse(bus.deliverTranslation(t1, "Yes.", "是。", failed = false))
        assertTrue(bus.deliverTranslation(t2, "Yes.", "是。", failed = false))
    }

    @Test fun `failure keeps the original and marks the frame`() {
        val bus = BilingualCueBus()
        val t = bus.showCue("Run!", "Run!", null)
        assertTrue(bus.deliverTranslation(t, "Run!", "Run!", failed = true))
        assertEquals("Run!", bus.frame.value?.original)
        assertNull(bus.frame.value?.translation)
        assertTrue(bus.frame.value!!.translationFailed)
    }

    @Test fun `concurrent stale deliveries never overwrite the current cue`() {
        val bus = BilingualCueBus()
        val pool = Executors.newFixedThreadPool(8)
        val tokens = (0 until 200).map { bus.showCue("line $it", "line $it", null) }
        val last = tokens.last()
        val latch = CountDownLatch(tokens.size)
        tokens.forEachIndexed { i, token ->
            pool.execute {
                bus.deliverTranslation(token, "line $i", "译文 $i", failed = false)
                latch.countDown()
            }
        }
        assertTrue(latch.await(10, TimeUnit.SECONDS))
        pool.shutdown()
        val frame = bus.frame.value!!
        assertEquals(last, frame.generation)
        assertEquals("line 199", frame.original)
        assertEquals("译文 199", frame.translation)
    }

    @Test fun `resetSecondary keeps the primary and invalidates pending second-language results`() {
        val bus = BilingualCueBus()
        val token = bus.showCue("Hi", "Hi", cachedTranslation = "旧")
        val newToken = bus.resetSecondary()
        assertEquals("Hi", bus.frame.value!!.original)
        assertEquals(null, bus.frame.value!!.translation)
        assertEquals(false, bus.deliverTranslation(token, "Hi", "stale", failed = false))
        assertEquals(true, bus.deliverTranslation(newToken, "Hi", "新", failed = false))
        assertEquals("新", bus.frame.value!!.translation)
    }
}
