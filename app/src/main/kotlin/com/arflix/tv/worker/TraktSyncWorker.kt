package com.arflix.tv.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.TraktSyncService
import com.arflix.tv.data.repository.SyncResult
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first

/**
 * Background worker for syncing Trakt data periodically.
 * Syncs:
 * - Trakt watched state (movies/episodes)
 * - Trakt playback progress (Continue Watching)
 * - Pending outbox writes
 */
class TraktSyncWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface TraktSyncWorkerEntryPoint {
        fun traktRepository(): TraktRepository
        fun traktSyncService(): TraktSyncService
    }

    private val deps: TraktSyncWorkerEntryPoint by lazy {
        EntryPointAccessors.fromApplication(
            applicationContext,
            TraktSyncWorkerEntryPoint::class.java
        )
    }

    companion object {
        const val TAG = "TraktSyncWorker"
        const val WORK_NAME = "trakt_sync_worker"
        const val WORK_NAME_ON_OPEN = "trakt_sync_on_open"
        const val SYNC_INTERVAL_HOURS = 6L
        const val INPUT_SYNC_MODE = "sync_mode"
        const val SYNC_MODE_FULL = "full"
        const val SYNC_MODE_INCREMENTAL = "incremental"
    }

    override suspend fun doWork(): Result {
        // Check if user is authenticated
        val isAuth = deps.traktRepository().isAuthenticated.first()
        if (!isAuth) {
            return Result.success()
        }

        val syncMode = inputData.getString(INPUT_SYNC_MODE) ?: SYNC_MODE_INCREMENTAL

        return try {
            val syncResult = when (syncMode) {
                SYNC_MODE_FULL -> deps.traktSyncService().performFullSync()
                else -> deps.traktSyncService().performIncrementalSync()
            }
            workResult(syncResult, runAttemptCount)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    // TraktSyncService handles the sync pipeline.
}

internal fun workResult(result: SyncResult, attempts: Int): androidx.work.ListenableWorker.Result = when {
    result is SyncResult.Success -> androidx.work.ListenableWorker.Result.success()
    result is SyncResult.Error && !result.reconnectRequired && attempts < 3 -> androidx.work.ListenableWorker.Result.retry()
    else -> androidx.work.ListenableWorker.Result.failure()
}
