package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/** The Trakt-labelled action must not succeed because another provider synced. */
class TraktManualSync @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: TraktRepository,
    private val service: TraktSyncService,
    private val launcher: LauncherContinueWatchingRepository,
    private val profiles: ProfileManager
) {
    suspend fun sync(): SyncResult {
        val id = profiles.getProfileIdSync()
        val connection = repository.connection.first()
        if (connection.profileId != id) throw CancellationException("Profile changed before Trakt sync")
        if (!connection.connected) return SyncResult.Error(context.getString(
            if (connection.reconnectRequired) R.string.trakt_reconnect_required else R.string.trakt_not_authenticated
        ), reconnectRequired = connection.reconnectRequired)
        val result = service.performFullSync()
        if (profiles.getProfileIdSync() != id) throw CancellationException("Profile changed during Trakt sync")
        if (result is SyncResult.Error) return result
        return try {
            repository.invalidateWatchedCache()
            repository.initializeWatchedCache()
            // Do not replace another provider's Home row just to sync Trakt's history.
            if (!repository.isAlternativeRemoteActive()) {
                repository.getContinueWatching(forceRefresh = true, requireFreshTrakt = true)
            }
            if (profiles.getProfileIdSync() != id) throw CancellationException("Profile changed during Trakt refresh")
            launcher.refreshForCurrentProfile()
            result
        } catch (error: Exception) {
            if (error is CancellationException) throw error
            val state = repository.connection.first()
            SyncResult.Error(context.getString(R.string.trakt_sync_refresh_failed), state.reconnectRequired)
        }
    }
}
