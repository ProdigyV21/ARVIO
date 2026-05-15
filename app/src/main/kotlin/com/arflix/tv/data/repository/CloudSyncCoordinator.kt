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
        // Retry backoff schedule (ms) for when a cloud push fails.
        // The coordinator retries 0.5s → 2s → 6s after the initial debounce,
        // giving transient network issues a chance to recover before the
        // periodic 45s sync in RealtimeSyncManager picks it up.
        private val RETRY_DELAYS_MS = longArrayOf(500L, 2_000L, 6_000L)
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
     * Debounce invalidation events, then push to cloud with retry on failure.
     * If pushToCloud() fails (even after its internal retries), this method
     * schedules up to 3 additional retries with exponential backoff
     * (0.5s → 2s → 6s after debounce) so transient network issues don't
     * silently drop user changes until the 45s periodic sync.
     */
    private fun scheduleFlush(invalidation: CloudSyncInvalidation) {
        synchronized(lifecycleLock) {
            if (!started.get()) return
            flushJob?.cancel()
            flushJob = scope.launch {
                delay(debounceMsFor(invalidation.scope))
                if (authRepository.getCurrentUserId().isNullOrBlank()) return@launch

                // Attempt push with retry backoff
                for ((retryIndex, retryDelay) in RETRY_DELAYS_MS.withIndex()) {
                    if (retryIndex > 0) {
                        delay(retryDelay)
                    }
                    val result = runCatching { cloudSyncRepository.pushToCloud() }
                    if (result.isSuccess) return@launch
                    Log.w(TAG, "Push attempt ${retryIndex + 1} failed after ${invalidation.scope}: ${result.exceptionOrNull()?.message}")
                }

                // All retries exhausted — mark dirty so periodic/foreground sync picks it up
                cloudSyncRepository.markLocalStateDirty()
                Log.w(TAG, "Push exhausted retries after ${invalidation.scope}, marked dirty for later retry")
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
