package com.arflix.tv.ui.screens.player

import androidx.media3.common.PlaybackException
import com.arflix.tv.data.model.RecentPlayedSource
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.findRecentSourceMatch

/** Per-startup recovery budget. Fetching a fresh list must not consume the resolver retry. */
internal class StartupSourceRecovery {
    var freshRefetchAttempted = false
        private set
    var sameSourceRefreshAttempted = false
        private set

    val attemptCount: Int
        get() = (if (freshRefetchAttempted) 1 else 0) + (if (sameSourceRefreshAttempted) 1 else 0)

    fun tryFreshRefetch(
        hasPlaybackStarted: Boolean,
        errorCode: Int,
        isTimeoutError: Boolean,
        userSelectedSourceManually: Boolean,
        startPositionMs: Long?,
        savedPositionMs: Long,
        selectedStream: StreamSource?,
        recentSource: RecentPlayedSource?,
    ): Boolean {
        if (hasPlaybackStarted || freshRefetchAttempted || userSelectedSourceManually || selectedStream == null) return false
        val networkOrHttpFailure = isTimeoutError || errorCode in NETWORK_ERRORS
        // A recent record is passed only for the current title/episode. Its source must still match:
        // merely having watched a different release once does not make a new autoplay a resume.
        val resumeOrRestored = (startPositionMs ?: 0L) > 0L || savedPositionMs > 0L ||
            findRecentSourceMatch(listOf(selectedStream), recentSource) != null
        if (!networkOrHttpFailure || !resumeOrRestored) return false
        freshRefetchAttempted = true
        return true
    }

    fun trySameSourceRefresh(): Boolean {
        if (sameSourceRefreshAttempted) return false
        sameSourceRefreshAttempted = true
        return true
    }

    fun reset() {
        freshRefetchAttempted = false
        sameSourceRefreshAttempted = false
    }

    private companion object {
        val NETWORK_ERRORS = setOf(
            PlaybackException.ERROR_CODE_IO_UNSPECIFIED,
            PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
            PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS,
        )
    }
}
