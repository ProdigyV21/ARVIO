package com.arflix.tv.ui.screens.tv.live

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LiveTvRetryPolicyTest {
    @Test fun healthyHlsAddressSurvivesPlaylistResetWithoutAnotherProbe() {
        assertTrue(shouldReusePreparedLiveHls(true, false, false, null))
    }

    @Test fun expiredHlsAddressMustBeResolvedAgain() {
        for (status in listOf(404, 410)) {
            assertFalse(shouldReusePreparedLiveHls(true, false, false, status))
        }
    }

    @Test fun catchupAndContainerRecoveryStillResolveTheirTargets() {
        assertFalse(shouldReusePreparedLiveHls(true, true, false, null))
        assertFalse(shouldReusePreparedLiveHls(true, false, true, null))
        assertFalse(shouldReusePreparedLiveHls(false, false, false, null))
    }

    @Test fun missingLiveResourceGetsOneFreshResolution() {
        for (status in listOf(404, 410)) {
            assertTrue(shouldRetryLiveTvPlayback(status, 1, 3, isCatchup = false))
            assertFalse(shouldRetryLiveTvPlayback(status, 2, 3, isCatchup = false))
            assertFalse(shouldRetryLiveTvPlayback(status, 3, 3, isCatchup = false))
            assertTrue(isMissingPlaybackResource(status))
        }
    }

    @Test fun catchupKeepsItsBoundedCandidateBudget() {
        for (status in listOf(404, 410)) {
            assertTrue(shouldRetryLiveTvPlayback(status, 1, 2, isCatchup = true))
            assertTrue(shouldRetryLiveTvPlayback(status, 2, 2, isCatchup = true))
            assertFalse(shouldRetryLiveTvPlayback(status, 3, 2, isCatchup = true))
            assertFalse(shouldRetryLiveTvPlayback(status, 1, 0, isCatchup = true))
        }
    }

    @Test fun terminalResponsesStillStopImmediately() {
        for (status in listOf(401, 403, 429, 444, 451, 513)) {
            assertFalse(shouldRetryLiveTvPlayback(status, 1, 3, isCatchup = false))
            assertFalse(shouldRetryLiveTvPlayback(status, 1, 2, isCatchup = true))
        }
    }

    @Test fun otherFailuresKeepExistingRetryBudget() {
        for (status in listOf(null, 500, 502, 503)) {
            assertTrue(shouldRetryLiveTvPlayback(status, 3, 3, isCatchup = false))
            assertFalse(shouldRetryLiveTvPlayback(status, 4, 3, isCatchup = false))
            assertFalse(isMissingPlaybackResource(status))
        }
    }
}
