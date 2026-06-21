package com.arflix.tv.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.MediaRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

/**
 * Keeps Swedish service catalog refs warm so opening Netflix/Viaplay/etc. does
 * not have to pay the Watchmode fetch cost on the user's first click.
 */
class StreamingCatalogRefreshWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface StreamingCatalogRefreshEntryPoint {
        fun catalogRepository(): CatalogRepository
        fun mediaRepository(): MediaRepository
    }

    private val deps: StreamingCatalogRefreshEntryPoint by lazy {
        EntryPointAccessors.fromApplication(
            applicationContext,
            StreamingCatalogRefreshEntryPoint::class.java
        )
    }

    companion object {
        const val TAG = "StreamingCatalogRefreshWorker"
        const val WORK_NAME = "streaming_catalog_refresh_worker"
        const val WORK_NAME_ON_OPEN = "streaming_catalog_refresh_on_open"
        const val REFRESH_INTERVAL_HOURS = 24L
        const val STARTUP_DELAY_MINUTES = 5L
    }

    override suspend fun doWork(): Result {
        return try {
            val mediaRepository = deps.mediaRepository()
            val catalogs = runCatching {
                deps.catalogRepository().getCatalogs()
            }.getOrDefault(mediaRepository.getDefaultCatalogConfigs())
            mediaRepository.refreshStreamingServiceCatalogRefs(catalogs = catalogs)
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }
}
