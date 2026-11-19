package com.arflix.tv.worker

import androidx.work.ListenableWorker.Result
import com.arflix.tv.data.repository.SyncResult
import org.junit.Assert.assertEquals
import org.junit.Test

class TraktSyncWorkerTest {
    @Test fun successfulSyncCompletesWork() {
        assertEquals(Result.success(), workResult(SyncResult.Success(1, 2), 0))
    }
    @Test fun returnedTransientFailureRetries() {
        assertEquals(Result.retry(), workResult(SyncResult.Error("offline"), 0))
    }
    @Test fun retryLimitIsBounded() {
        assertEquals(Result.failure(), workResult(SyncResult.Error("offline"), 3))
    }
    @Test fun reconnectRequiredDoesNotKeepRetrying() {
        assertEquals(Result.failure(), workResult(SyncResult.Error("reconnect", true), 0))
    }
}
