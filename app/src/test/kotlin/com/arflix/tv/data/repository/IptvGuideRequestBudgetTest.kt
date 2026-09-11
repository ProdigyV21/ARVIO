package com.arflix.tv.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class IptvGuideRequestBudgetTest {
    @Test fun repeatedAndCancelledViewportRequestsKeepTheirCooldown() = runTest {
        val budget = IptvGuideRequestBudget { testScheduler.currentTime }
        var calls = 0
        repeat(50) { budget.request("https://provider.test/epg?stream_id=7") { calls++; null } }
        assertEquals(1, calls)
        try {
            budget.request("https://provider.test/epg?stream_id=8") {
                calls++
                throw kotlinx.coroutines.CancellationException()
            }
        } catch (_: kotlinx.coroutines.CancellationException) { }
        assertNull(budget.request("https://provider.test/epg?stream_id=8") { calls++; 1 })
        assertEquals(2, calls)
        testScheduler.advanceTimeBy(120_001L)
        budget.request("https://provider.test/epg?stream_id=7") { calls++; 1 }
        assertEquals(3, calls)
    }

    @Test fun overlappingBatchesShareTwoConnectionsAndStartSpacing() = runTest {
        val budget = IptvGuideRequestBudget { testScheduler.currentTime }
        var active = 0
        var peak = 0
        val starts = mutableListOf<Long>()
        (1..12).map { index -> async {
            budget.request("https://provider.test/epg?id=$index") {
                starts.add(testScheduler.currentTime)
                active++
                peak = maxOf(peak, active)
                delay(1000)
                active--
                index
            }
        } }.awaitAll()
        assertEquals(2, peak)
        assertTrue(starts.zipWithNext().all { (a, b) -> b - a >= 250 })
    }

    @Test fun rateLimitStopsQueuedRequestsAndRespectsRetryAfter() = runTest {
        val budget = IptvGuideRequestBudget { testScheduler.currentTime }
        val url = "https://provider.test/epg"
        budget.onResponse(url, 429, 120)
        assertNull(budget.request(url) { fail("must not request blocked provider"); 1 })
        assertEquals(2, budget.request("https://other.test/epg") { 2 })
        testScheduler.advanceTimeBy(120_001)
        assertEquals(3, budget.request(url) { 3 })
    }

    @Test fun forbiddenAndProvider513StopFallbackStorms() = runTest {
        listOf(401, 403, 503, 513).forEach { status ->
            val budget = IptvGuideRequestBudget { testScheduler.currentTime }
            budget.onResponse("https://provider.test/api", status)
            assertNull(budget.request("https://provider.test/other") { 1 })
        }
    }
}
