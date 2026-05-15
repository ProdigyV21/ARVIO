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

@Singleton
class CloudSyncCoordinator @Inject constructor(
    private val invalidationBus: CloudSyncInvalidationBus,
    private val cloudSyncRepository: CloudSyncRepository,
    private val authRepository: AuthRepository
) {
    companion object {
        private const val TAG = "CloudSyncCoordinator"
    }

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
                    if (authRepository.getCurrentUserId().isNullOrBlank()) return@collectLatest
                    cloudSyncRepository.markLocalStateDirty()
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

    /**
     * Debounce invalidation events, then push to cloud.
     *
     * Retry logic lives entirely inside [CloudSyncRepository.pushToCloud] (up to 3
     * attempts with 1.5s gaps). The coordinator does not add its own retry loop to
     * avoid stacking backoffs and holding [cloudSyncMutex] for tens of seconds
     * across nested retry cycles. If pushToCloud fails after all internal retries,
     * the dirty flag is set so the periodic sync (45s) or foreground-resume retry
     * (ArflixApplication) picks it up.
     */
    private fun scheduleFlush(invalidation: CloudSyncInvalidation) {
        synchronized(lifecycleLock) {
            if (!started.get()) return
            flushJob?.cancel()
            flushJob = scope.launch {
                delay(debounceMsFor(invalidation.scope))
                if (authRepository.getCurrentUserId().isNullOrBlank()) return@launch

                val result = runCatching { cloudSyncRepository.pushToCloud() }
                if (result.isFailure) {
                    Log.w(TAG, "Push failed after ${invalidation.scope}: ${result.exceptionOrNull()?.message}")
                    // Mark dirty so periodic/foreground sync retries
                    cloudSyncRepository.markLocalStateDirty()
                }
            }
        }
    }

    private fun debounceMsFor(scope: CloudSyncScope): Long {
        return when (scope) {
            CloudSyncScope.LOCAL_HISTORY -> 2_000L
            CloudSyncScope.IPTV -> 750L
            else -> 500L
        }
    }
}
