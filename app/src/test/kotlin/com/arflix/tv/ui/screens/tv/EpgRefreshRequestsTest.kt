package com.arflix.tv.ui.screens.tv

import kotlinx.coroutines.async
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class EpgRefreshRequestsTest {
    @Test
    fun shortGuideDoesNotDelayArchiveForTwoMinutes() = runTest {
        val requests = EpgRefreshRequests(clock = { testScheduler.currentTime })
        assertEquals(setOf("channel"), requests.claim(listOf("channel")))
        requests.release(listOf("channel"))
        assertEquals(setOf("channel"), requests.claimArchive("channel"))
        requests.release(listOf("channel"))
        assertTrue(requests.claimArchive("channel").isEmpty())
    }

    @Test
    fun archiveWaitsForShortGuideWithoutStartingConcurrentRequests() = runTest {
        val requests = EpgRefreshRequests(clock = { testScheduler.currentTime })
        requests.claim(listOf("channel"))
        val archive = async { requests.claimArchive("channel") }
        runCurrent()
        advanceTimeBy(300)
        assertFalse(archive.isCompleted)
        requests.release(listOf("channel"))
        advanceTimeBy(100)
        runCurrent()
        assertEquals(setOf("channel"), archive.await())
        assertTrue(requests.claim(listOf("channel")).isEmpty())
    }

    @Test
    fun cancelledScrollRefreshCanRetryImmediately() {
        val requests = EpgRefreshRequests(clock = { 0L })
        requests.claim(listOf("channel"))
        requests.release(listOf("channel"), aborted = true)
        assertEquals(setOf("channel"), requests.claim(listOf("channel")))
        requests.release(listOf("channel"))
        assertTrue(requests.claim(listOf("channel")).isEmpty())
    }

    @Test
    fun differentProvidersDoNotBlockEachOtherAndCooldownExpires() {
        var now = 0L
        val requests = EpgRefreshRequests(clock = { now })
        assertEquals(setOf("one:1", "two:1"), requests.claim(listOf("one:1", "one:1", "two:1", "")))
        requests.release(listOf("one:1", "two:1"))
        assertTrue(requests.claim(listOf("one:1", "two:1")).isEmpty())
        now += 120_000L
        assertEquals(setOf("one:1", "two:1"), requests.claim(listOf("one:1", "two:1")))
    }
}
