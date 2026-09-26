package com.arflix.tv.data.repository

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Tells screens that the watched cache was loaded again after an explicit invalidation (a sync
 * finished). [TraktSyncService] reports COMPLETED before that reload, so listening to the sync
 * alone would read the old cache. The first load after start or a profile switch is not reported:
 * screens run their own pass for it.
 */
internal class WatchedCacheReloads {
    private val _reloads = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val reloads: SharedFlow<Unit> = _reloads.asSharedFlow()

    @Volatile private var reloadPending = false

    fun invalidated() {
        reloadPending = true
    }

    fun reset() {
        reloadPending = false
    }

    fun loaded() {
        if (!reloadPending) return
        reloadPending = false
        _reloads.tryEmit(Unit)
    }
}
