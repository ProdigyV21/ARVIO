package com.arflix.tv.ui.screens.tv.live.aisubs

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LiveCueSchedulerTest {
    private fun cue(startMs: Long, endMs: Long, text: String = "line") = LiveCue(startMs * 1_000, endMs * 1_000, text)
    private fun LiveCueScheduler.at(ms: Long) = cueAt(ms * 1_000)?.text

    @Test fun cueShowsFromItsStartUntilItsEnd() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(10_000, 12_500, "hello")), positionUs = 0)
        assertNull(scheduler.at(9_999))
        assertEquals("hello", scheduler.at(10_000))
        assertEquals("hello", scheduler.at(12_499))
        assertNull(scheduler.at(12_500))
    }

    @Test fun shortCueStaysUpForTheMinimum() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(10_000, 10_300, "hi")), positionUs = 0)
        assertEquals("hi", scheduler.at(11_100))
        assertNull(scheduler.at(11_200))
    }

    @Test fun longCueIsCappedAtTheMaximum() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(10_000, 30_000, "long")), positionUs = 0)
        assertEquals("long", scheduler.at(15_900))
        assertNull(scheduler.at(16_000))
    }

    @Test fun nextCueCutsThePreviousOne() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(10_000, 13_000, "one"), cue(11_000, 13_000, "two")), positionUs = 0)
        assertEquals("one", scheduler.at(10_500))
        assertEquals("two", scheduler.at(11_000))
    }

    @Test fun cuesFromSegmentsArrivingOutOfOrderAreSorted() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(20_000, 22_000, "later")), positionUs = 0)
        scheduler.add(listOf(cue(10_000, 12_000, "earlier")), positionUs = 0)
        assertEquals("earlier", scheduler.at(11_000))
        assertEquals("later", scheduler.at(21_000))
    }

    @Test fun lateCueStillInsideItsWindowShowsImmediately() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(10_000, 13_000, "late")), positionUs = 11_000_000)
        assertEquals("late", scheduler.at(11_000))
        assertEquals(0, scheduler.lateDropped)
    }

    @Test fun cueArrivingAfterItsWindowIsDroppedAndCounted() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(10_000, 11_000, "gone")), positionUs = 12_000_000)
        assertNull(scheduler.at(12_000))
        assertEquals(1, scheduler.lateDropped)
        assertEquals(0, scheduler.pendingCount)
    }

    @Test fun clearForgetsEverything() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(10_000, 12_000)), positionUs = 0)
        scheduler.clear()
        assertNull(scheduler.at(11_000))
        assertEquals(0, scheduler.pendingCount)
    }

    @Test fun blankLinesAreIgnored() {
        val scheduler = LiveCueScheduler()
        scheduler.add(listOf(cue(10_000, 12_000, "   ")), positionUs = 0)
        assertEquals(0, scheduler.pendingCount)
    }

    @Test fun shortTextIsNotSplit() {
        val parts = LiveCueScheduler.split(cue(0, 4_000, "A short line"))
        assertEquals(1, parts.size)
    }

    @Test fun longTextSplitsAtAWordNearTheMiddleWithProportionalTime() {
        val text = "This is a rather long subtitle line that keeps going " +
            "and going until it no longer fits on the screen at once"
        val parts = LiveCueScheduler.split(cue(0, 6_000, text))
        assertEquals(2, parts.size)
        assertEquals(text, parts[0].text + " " + parts[1].text)
        assertEquals(parts[0].endUs, parts[1].startUs)
        val expectedSplit = 6_000_000L * parts[0].text.length / (parts[0].text.length + parts[1].text.length)
        assertEquals(expectedSplit, parts[0].endUs)
        assertTrue(parts.all { it.text.length <= LiveCueScheduler.SPLIT_CHARS })
    }

    @Test fun longTextWithoutSpacesStaysWhole() {
        val parts = LiveCueScheduler.split(cue(0, 6_000, "x".repeat(120)))
        assertEquals(1, parts.size)
    }
}

class LiveAiSubsDelayTest {
    @Test fun autoAssumesFiveSecondsBeforeAnyMeasurement() {
        assertEquals(11_000L, autoLiveAiSubsDelayMs(null))
    }

    @Test fun autoAddsMeasuredLatencyToSegmentLengthAndMargin() {
        assertEquals(8_200L, autoLiveAiSubsDelayMs(2_200L))
    }

    @Test fun autoIsClampedToTheOfferedRange() {
        assertEquals(6_000L, autoLiveAiSubsDelayMs(0L))
        assertEquals(15_000L, autoLiveAiSubsDelayMs(30_000L))
    }

    @Test fun percentileNeedsAFewSamples() {
        assertNull(percentile95(listOf(1L, 2L)))
        assertEquals(96L, percentile95((1L..100L).toList()))
        assertEquals(1_900L, percentile95(listOf(1_000L, 1_200L, 1_900L, 1_100L)))
    }
}
