package com.arflix.tv.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.TraktSyncService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

/**
 * Background worker for syncing Trakt data periodically.
 * Syncs:
 * - Trakt watched state (movies/episodes)
 * - Trakt playback progress (Continue Watching)
 * - Pending outbox writes
 */
@HiltWorker
class TraktSyncWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val traktRepository: TraktRepository,
    private val traktSyncService: TraktSyncService
) : CoroutineWorker(context, params) {

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
        val isAuth = traktRepository.isAuthenticated.first()
        if (!isAuth) {
            return Result.success()
        }

        val syncMode = inputData.getString(INPUT_SYNC_MODE) ?: SYNC_MODE_INCREMENTAL

        return try {
            when (syncMode) {
                SYNC_MODE_FULL -> traktSyncService.performFullSync()
                else -> traktSyncService.performIncrementalSync()
            }
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    // TraktSyncService handles the sync pipeline.
}
