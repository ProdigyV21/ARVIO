package com.arflix.tv.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arflix.tv.data.local.OfflineMetadataCache
import com.arflix.tv.data.repository.MediaRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MediaMetadataRefreshWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val mediaRepository: MediaRepository,
    private val offlineMetadataCache: OfflineMetadataCache
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val staleDetails = offlineMetadataCache.getStaleRecordKeys(
                OfflineMetadataCache.RECORD_KIND_DETAILS,
                MediaMetadataRefreshWorker.REFRESH_DETAILS_INTERVAL_MS,
                30
            )
            staleDetails.forEach { (mediaType, mediaId) ->
                try {
                    if (mediaType == com.arflix.tv.data.model.MediaType.MOVIE) {
                        mediaRepository.getMovieDetails(mediaId)
                    } else {
                        mediaRepository.getTvDetails(mediaId)
                    }
                } catch (_: Exception) {
                    // Continue refreshing remaining records.
                }
            }

            val staleCast = offlineMetadataCache.getStaleRecordKeys(
                OfflineMetadataCache.RECORD_KIND_CAST,
                MediaMetadataRefreshWorker.REFRESH_CAST_INTERVAL_MS,
                30
            )
            staleCast.forEach { (mediaType, mediaId) ->
                try {
                    mediaRepository.getCast(mediaType, mediaId)
                } catch (_: Exception) {
                    // Continue refreshing remaining records.
                }
            }

            Result.success()
        } catch (error: Throwable) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "MediaMetadataRefreshWorker"
        private const val REFRESH_DETAILS_INTERVAL_MS = 7 * 24 * 60 * 60 * 1000L
        private const val REFRESH_CAST_INTERVAL_MS = 14 * 24 * 60 * 60 * 1000L
    }
}
