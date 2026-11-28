package com.arflix.tv.ui.screens.player

import androidx.media3.common.PlaybackException
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.RecentPlayedSource
import com.arflix.tv.data.model.StreamSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StartupSourceRecoveryTest {
    private val stream = StreamSource(
        source = "Cached release",
        addonName = "Addon",
        addonId = "addon-a",
        quality = "1080p",
        size = "1 GB",
        url = "https://example.test/old.mkv",
    )
    private val recent = RecentPlayedSource.of(
        mediaType = MediaType.MOVIE,
        mediaId = 7,
        season = null,
        episode = null,
        stream = stream,
        playedAtMs = 1L,
    )

    @Test fun resumedNetworkFailureRefetchesTheSameSource() {
        val recovery = StartupSourceRecovery()
        assertTrue(recovery.tryFreshRefetch(
            hasPlaybackStarted = false,
            errorCode = PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS,
            isTimeoutError = false,
            userSelectedSourceManually = false,
            startPositionMs = 90_000L,
            savedPositionMs = 0L,
            selectedStream = stream,
            recentSource = recent,
        ))
        assertTrue(recovery.freshRefetchAttempted)
        assertFalse(recovery.sameSourceRefreshAttempted)
    }

    @Test fun containerFailureOnResumeDoesNotRefetchASourceList() {
        val recovery = StartupSourceRecovery()
        assertFalse(recovery.tryFreshRefetch(
            hasPlaybackStarted = false,
            errorCode = PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED,
            isTimeoutError = false,
            userSelectedSourceManually = false,
            startPositionMs = 90_000L,
            savedPositionMs = 0L,
            selectedStream = stream,
            recentSource = recent,
        ))
        assertFalse(recovery.freshRefetchAttempted)
    }

    @Test fun freshAutoplayNetworkFailureDoesNotWaitForAFullRefetch() {
        val recovery = StartupSourceRecovery()
        assertFalse(recovery.tryFreshRefetch(
            hasPlaybackStarted = false,
            errorCode = PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
            isTimeoutError = false,
            userSelectedSourceManually = false,
            startPositionMs = 0L,
            savedPositionMs = 0L,
            selectedStream = stream,
            recentSource = null,
        ))
    }

    @Test fun freshRefetchBudgetIsSeparateFromSameSourceRefresh() {
        val recovery = StartupSourceRecovery()
        assertTrue(recovery.tryFreshRefetch(
            hasPlaybackStarted = false,
            errorCode = PlaybackException.ERROR_CODE_IO_UNSPECIFIED,
            isTimeoutError = false,
            userSelectedSourceManually = false,
            startPositionMs = 0L,
            savedPositionMs = 10_000L,
            selectedStream = stream,
            recentSource = recent,
        ))
        assertTrue(recovery.trySameSourceRefresh())
        assertFalse(recovery.trySameSourceRefresh())
        assertEquals(2, recovery.attemptCount)
    }
}
