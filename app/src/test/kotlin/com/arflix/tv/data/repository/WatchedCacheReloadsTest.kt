package com.arflix.tv.data.repository

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class WatchedCacheReloadsTest {

    @Test fun withoutCloudAccountTraktIsReadEvenWhenTheLastSyncLeftHistoryInMemory() {
        // Without an account the sync service only holds the last full sync (15 recent shows).
        assertTrue(watchedCacheNeedsTrakt(hasTraktAuth = true, hasCloudAccount = false, cloudHistoryEmpty = false))
        assertTrue(watchedCacheNeedsTrakt(hasTraktAuth = true, hasCloudAccount = false, cloudHistoryEmpty = true))
    }

    @Test fun cloudAccountHistoryStillReplacesTrakt() {
        assertFalse(watchedCacheNeedsTrakt(hasTraktAuth = true, hasCloudAccount = true, cloudHistoryEmpty = false))
        assertTrue(watchedCacheNeedsTrakt(hasTraktAuth = true, hasCloudAccount = true, cloudHistoryEmpty = true))
    }

    @Test fun noTraktAuthNeverReadsTrakt() {
        assertFalse(watchedCacheNeedsTrakt(hasTraktAuth = false, hasCloudAccount = false, cloudHistoryEmpty = true))
        assertFalse(watchedCacheNeedsTrakt(hasTraktAuth = false, hasCloudAccount = true, cloudHistoryEmpty = true))
    }

    @Test fun onlyAReloadAfterInvalidationIsReported() = runTest {
        val reloads = WatchedCacheReloads()
        val seen = async(start = CoroutineStart.UNDISPATCHED) { reloads.reloads.take(2).toList() }

        reloads.loaded() // first load after start: screens run their own pass
        runCurrent()
        reloads.invalidated()
        reloads.loaded()
        runCurrent()
        reloads.loaded() // a later lazy init without a new invalidation stays quiet
        runCurrent()
        reloads.invalidated()
        reloads.loaded()
        runCurrent()

        assertEquals(2, seen.await().size)
    }

    @Test fun profileResetDropsAPendingReload() = runTest {
        val reloads = WatchedCacheReloads()
        var count = 0
        val job = backgroundScope.async(start = CoroutineStart.UNDISPATCHED) { reloads.reloads.collect { count++ } }

        reloads.invalidated()
        reloads.reset()
        reloads.loaded()
        runCurrent()
        assertEquals(0, count)

        reloads.invalidated()
        reloads.loaded()
        runCurrent()
        assertEquals(1, count)
        job.cancel()
    }
}
