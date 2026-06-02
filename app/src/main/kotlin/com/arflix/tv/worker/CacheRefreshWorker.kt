package com.arflix.tv.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arflix.tv.data.local.CacheDao
import com.arflix.tv.data.local.CachePolicyManager
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Background worker to refresh stale cached metadata periodically (e.g. daily).
 * Fetches fresh details for media items that are close to expiration or are stale,
 * and purges extremely old cache entries.
 */
class CacheRefreshWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface CacheRefreshWorkerEntryPoint {
        fun mediaRepository(): MediaRepository
        fun cacheDao(): CacheDao
    }

    private val deps: CacheRefreshWorkerEntryPoint by lazy {
        EntryPointAccessors.fromApplication(
            applicationContext,
            CacheRefreshWorkerEntryPoint::class.java
        )
    }

    companion object {
        const val TAG = "CacheRefreshWorker"
        const val WORK_NAME = "cache_refresh_worker"
        const val REFRESH_INTERVAL_HOURS = 24L
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val mediaRepository = deps.mediaRepository()
            val cacheDao = deps.cacheDao()

            // 1. Purge extremely old stale items to keep database size under control
            // Max TTLs
            val oldestAllowedMedia = System.currentTimeMillis() - CachePolicyManager.MOVIE_TTL_MS
            val oldestAllowedCast = System.currentTimeMillis() - CachePolicyManager.CAST_TTL_MS
            val oldestAllowedEpisodes = System.currentTimeMillis() - CachePolicyManager.EPISODES_TTL_MS

            cacheDao.deleteStaleMediaItems(oldestAllowedMedia)
            cacheDao.deleteStaleCastMembers(oldestAllowedCast)
            cacheDao.deleteStaleEpisodes(oldestAllowedEpisodes)

            // 2. Refresh recently accessed stale items to preemptively cache them (max 20 items per run)
            // Query media items that have updatedAt older than their TTL
            // We use TV TTL as a conservative threshold for staled items.
            val tvStaleTime = System.currentTimeMillis() - CachePolicyManager.TV_TTL_MS
            val staleItems = cacheDao.getStaleMediaItems(staleTime = tvStaleTime, limit = 20)

            for (cachedItem in staleItems) {
                try {
                    val mediaType = MediaType.valueOf(cachedItem.mediaType)
                    if (mediaType == MediaType.MOVIE) {
                        mediaRepository.getMovieDetails(cachedItem.id, forceRefresh = true)
                    } else {
                        mediaRepository.getTvDetails(cachedItem.id, forceRefresh = true)
                    }
                } catch (e: Exception) {
                    // Ignore errors for individual items to let other items refresh
                }
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
}
