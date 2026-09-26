package com.arflix.tv.data.repository

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Whether the watched cache has to read Trakt itself. The sync service's history is complete only
 * when it comes from the cloud account. Without one it is the in-memory result of the last full
 * sync, which expands only the 15 most recently watched shows when a show was rewatched, so it
 * must not stand in for the Trakt history.
 */
internal fun watchedCacheNeedsTrakt(
    hasTraktAuth: Boolean,
    hasCloudAccount: Boolean,
    cloudHistoryEmpty: Boolean
): Boolean = hasTraktAuth && (!hasCloudAccount || cloudHistoryEmpty)

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
