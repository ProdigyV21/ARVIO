package com.arflix.tv.data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.arflix.tv.worker.CloudSyncWorker

@Singleton
class CloudSyncCoordinator @Inject constructor(
    @ApplicationContext private val context: Context,
    private val invalidationBus: CloudSyncInvalidationBus,
    private val cloudSyncRepository: CloudSyncRepository,
    private val authRepository: AuthRepository
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val lifecycleLock = Any()
    private var collectorJob: Job? = null
    private var flushJob: Job? = null

    private val started = AtomicBoolean(false)

    fun start() {
        synchronized(lifecycleLock) {
            if (!started.compareAndSet(false, true)) return
            collectorJob = scope.launch {
                invalidationBus.events.collectLatest { invalidation ->
                    val userId = runCatching { authRepository.getCurrentUserIdForSync() }.getOrNull()
                    if (userId.isNullOrBlank()) {
                        cloudSyncRepository.markLocalStateDirtyNow()
                        CloudSyncWorker.enqueueRecovery(context)
                        Log.w("CloudSyncCoordinator", "Queued dirty ${invalidation.scope}: auth not ready")
                        return@collectLatest
                    }
                    Log.i(
                        "CloudSyncCoordinator",
                        "Dirty ${invalidation.scope} profile=${invalidation.profileId.orEmpty()} reason=${invalidation.reason}"
                    )
                    cloudSyncRepository.markLocalStateDirtyNow()
                    scheduleFlush(invalidation)
                }
            }
        }
    }

    fun stop() {
        synchronized(lifecycleLock) {
            started.set(false)
            collectorJob?.cancel()
            flushJob?.cancel()
            collectorJob = null
            flushJob = null
        }
    }

    private fun scheduleFlush(invalidation: CloudSyncInvalidation) {
        synchronized(lifecycleLock) {
            if (!started.get()) return
            flushJob?.cancel()
            flushJob = scope.launch {
                val backoffMs = if (cloudSyncRepository.pushFailureCount > 0) {
                    // Exponential backoff: 2s, 4s, 8s, 16s... max 1 minute (for active app)
                    (2_000L * (1 shl (cloudSyncRepository.pushFailureCount - 1).coerceAtMost(5))).coerceAtMost(60_000L)
                } else {
                    debounceMsFor(invalidation.scope)
                }
                delay(backoffMs)

                val userId = runCatching { authRepository.getCurrentUserIdForSync() }.getOrNull()
                if (userId.isNullOrBlank()) {
                    cloudSyncRepository.markLocalStateDirtyNow()
                    CloudSyncWorker.enqueueRecovery(context)
                    Log.w("CloudSyncCoordinator", "Deferred cloud sync for ${invalidation.scope}: auth not ready")
                    return@launch
                }
                Log.i("CloudSyncCoordinator", "Flushing cloud sync for ${invalidation.scope}")
                runCatching { cloudSyncRepository.pushToCloud(force = true) }
                    .onFailure { error ->
                        Log.w("CloudSyncCoordinator", "Cloud push failed after ${invalidation.scope}: ${error.message}")
                        cloudSyncRepository.markLocalStateDirty()
                        CloudSyncWorker.enqueueRecovery(context)
                    }
            }
        }
    }

    private fun debounceMsFor(scope: CloudSyncScope): Long {
        return when (scope) {
            CloudSyncScope.LOCAL_HISTORY -> 2_000L
            CloudSyncScope.IPTV -> 750L
            CloudSyncScope.PLUGINS -> 1_000L
            else -> 500L
        }
    }
}
