package com.arflix.tv.ui.screens.player.preview

import org.junit.Assert.*
import org.junit.Test

class SeekPreviewCoverageTest {
    @Test fun `moving playback finishes distant strips before shifting the coverage window`() {
        val planner = SeekPreviewCoverage()
        val cached = mutableSetOf<Long>()
        repeat(70) { tick ->
            planner.next(60_000 + tick * 3_000L, 9_000_000, 86_000_000_000,
                tick * 3_000L, 0, true) { it in cached }?.let { cached += it }
        }
        for (center in listOf(360_000L, 660_000L, 1_260_000L)) {
            assertTrue("Missing prepared strip at $center", cached.containsAll(seekPreviewWindow(center, 9_000_000)))
        }
        assertTrue("Intermediate coverage must follow priority strips", cached.contains(240_000L))
    }
    @Test fun `moving 4K playback prepares five ten and twenty minute jumps early`() {
        val planner = SeekPreviewCoverage()
        val cached = mutableSetOf<Long>()
        val targets = (0..14).mapNotNull { tick ->
            planner.next(60_000 + tick * 3_000L, 9_000_000, 86_000_000_000,
                tick * 3_000L, 0, true) { it in cached }?.also { cached += it }
        }
        assertTrue(targets.containsAll(listOf(360_000L, 660_000L, 1_260_000L)))
        assertEquals(targets.size, targets.distinct().size)
        assertTrue(targets.take(5).all { it < 150_000 })
    }

    @Test fun `forward preparation respects budget and movie end`() {
        val planner = SeekPreviewCoverage()
        repeat(40) { tick ->
            val target = planner.next(100_000, 150_000, 86_000_000_000, tick.toLong(),
                PREVIEW_SPECULATIVE_BYTES, true) { false }
            if (target != null) assertTrue(target in 60_000 until 150_000)
        }
    }

    @Test fun `long jump intent prepares its repeat without starving the current strip`() {
        val planner = SeekPreviewCoverage()
        planner.noteSeek(360_000, 0, originMs = 60_000)
        planner.endGesture()
        val cached = mutableSetOf<Long>()
        repeat(5) { tick ->
            planner.next(360_000, 9_000_000, 86_000_000_000, tick.toLong(), 0, true) { it in cached }
                ?.let { cached += it }
        }
        assertEquals(setOf(340_000L, 350_000L, 360_000L, 370_000L, 380_000L), cached)
        assertEquals(660_000L, planner.next(360_000, 9_000_000, 86_000_000_000, 6, 0, true) { it in cached })
    }

    @Test fun `prediction respects expiry budget boundaries and rewind direction`() {
        fun planned(now: Long = 10, bytes: Long = 0, position: Long = 2_000_000): Long? {
            val planner = SeekPreviewCoverage()
            planner.noteSeek(2_000_000, 0, originMs = 3_200_000)
            planner.endGesture()
            repeat(5) { planner.next(position, 9_000_000, 86_000_000_000, now, bytes, true) { false } }
            return planner.next(position, 9_000_000, 86_000_000_000, now, bytes, true) { false }
        }
        assertEquals(800_000L, planned())
        assertNotEquals(800_000L, planned(now = 120_001))
        assertNotEquals(800_000L, planned(bytes = PREVIEW_SPECULATIVE_BYTES))
        assertTrue(planned(position = 600_000)!! >= 0)
    }

    @Test fun `small gestures replace old long jump predictions`() {
        val planner = SeekPreviewCoverage()
        planner.noteSeek(360_000, 0, originMs = 60_000)
        planner.endGesture()
        planner.noteSeek(370_000, 1, originMs = 360_000)
        planner.endGesture()
        val fresh = SeekPreviewCoverage()
        fresh.noteSeek(370_000, 1, originMs = 360_000)
        fresh.endGesture()
        repeat(12) {
            assertEquals(fresh.next(370_000, 9_000_000, 86_000_000_000, 2, 0, true) { false },
                planner.next(370_000, 9_000_000, 86_000_000_000, 2, 0, true) { false })
        }
    }
    @Test fun `adaptive preparation finishes a nearby window before sparse coverage`() {
        val planner = SeekPreviewCoverage()
        val cached = mutableSetOf<Long>()
        repeat(5) { tick ->
            planner.next(60_000, 7_200_000, 6_000_000_000, tick.toLong(), 0, true) { it in cached }
                ?.let { cached += it }
        }
        assertEquals(setOf(40_000L, 50_000L, 60_000L, 70_000L, 80_000L), cached)
        val broad = planner.next(60_000, 7_200_000, 6_000_000_000, 6, 0, true) { it in cached }
        assertNotNull(broad)
        assertTrue(broad!! >= 120_000)
    }

    @Test fun `rewind intent changes priorities and expires instead of following old gestures forever`() {
        val planner = SeekPreviewCoverage()
        planner.noteSeek(90_000, 0)
        planner.noteSeek(60_000, 1)
        assertEquals(-1, planner.direction(2))
        assertEquals(50_000L, planner.next(60_000, 120_000, 1000, 2, 0, true) { false })
        assertEquals(1, planner.direction(60_001))
        planner.noteSeek(80_000, 60_002)
        assertEquals(1, planner.direction(60_003))
        planner.endGesture()
        planner.noteSeek(100_000, 60_004, originMs = 120_000)
        assertEquals(-1, planner.direction(60_005))
    }

    @Test fun `moving playback does not starve wider coverage`() {
        val planner = SeekPreviewCoverage()
        val cached = mutableSetOf<Long>()
        val targets = (0..19).mapNotNull { tick ->
            planner.next(tick * 3_000L, 7_200_000, 6_000_000_000, tick * 3_000L, 0) { it in cached }
                ?.also { cached += it }
        }
        assertTrue(targets.take(4).any { it >= 120_000 })
        assertTrue(targets.any { it >= 300_000 })
        assertEquals(targets.size, targets.distinct().size)
        assertTrue(targets.any { it <= 20_000 })
    }

    @Test fun `large or unknown files remain within ten minutes ahead`() {
        for (size in listOf(null, 30_000_000_000L)) {
            val planner = SeekPreviewCoverage()
            val cached = mutableSetOf<Long>()
            repeat(120) { tick ->
                planner.next(1_000_000, 9_000_000, size, tick * 3_000L, 0) { it in cached }
                    ?.let { assertTrue(it <= 1_600_000); cached += it }
            }
            assertTrue(cached.any { it > 1_300_000 })
        }
    }

    @Test fun `exhausted wide budget preserves nearby previews for long movies`() {
        val planner = SeekPreviewCoverage()
        val cached = mutableSetOf<Long>()
        repeat(8) { tick ->
            planner.next(0, 7_200_000, 1_000, tick.toLong(), PREVIEW_SPECULATIVE_BYTES) { it in cached }
                ?.let { assertTrue(it <= 40_000); cached += it }
        }
        assertTrue(cached.contains(10_000))
        assertTrue(cached.contains(40_000))
    }

    @Test fun `failures do not trap scheduler and short media never uses EOF`() {
        val planner = SeekPreviewCoverage()
        val targets = (0..9).mapNotNull { planner.next(0, 21_000, 1_000, it.toLong(), 0) { false } }
        assertEquals(targets.size, targets.distinct().size)
        assertTrue(targets.all { it in 0 until 21_000 })
        assertNull(planner.next(0, 0, 1_000, 0, 0) { false })
    }
}
