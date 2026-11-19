package com.arflix.tv.ui.screens.tv.live

import android.os.SystemClock
import android.util.Log
import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs

class LargeSportsMatchingDeviceTest {
    @Test fun timeIndexMatchesTheFullScanInTheSameOrderOnALargeGuide() {
        val hour = 3_600_000L
        val now = 1_795_000_000_000L
        val guide = List(55_000) { number ->
            val start = now + (number % 96) * (hour / 2)
            val title = "Club ${number % 257} vs Town ${number % 31}"
            SportsGuideEvent("$number", title, if (number % 7 == 0) GuideSport.BASKETBALL else GuideSport.FOOTBALL,
                IptvProgram(title, startUtcMillis = start, endUtcMillis = start + hour), emptyList())
        }
        val fixtures = (0 until 240).map { guide[(it * 197) % guide.size] }
        fun matches(candidate: SportsGuideEvent, fixture: SportsGuideEvent) = candidate.title == fixture.title
        val baselineAt = SystemClock.elapsedRealtimeNanos()
        val baseline = fixtures.map { fixture ->
            guide.filter {
                it.sport == fixture.sport && matches(it, fixture) &&
                    abs(it.programme.startUtcMillis - fixture.programme.startUtcMillis) <= 2 * hour
            }.map { it.id }
        }
        val baselineMs = (SystemClock.elapsedRealtimeNanos() - baselineAt) / 1_000_000
        val indexedAt = SystemClock.elapsedRealtimeNanos()
        val index = SportsGuideTimeIndex(guide)
        val indexed = fixtures.map { fixture ->
            index.matching(fixture.sport, fixture.programme.startUtcMillis) { matches(it, fixture) }.map { it.id }
        }
        val indexedMs = (SystemClock.elapsedRealtimeNanos() - indexedAt) / 1_000_000
        assertEquals(baseline, indexed)
        assertTrue(indexed.all { it.isNotEmpty() })
        Log.i("SportsMatchingBench", "guide=55000 fixtures=240 matches=${indexed.sumOf { it.size }} fullScanMs=$baselineMs indexedMs=$indexedMs")
    }
}
