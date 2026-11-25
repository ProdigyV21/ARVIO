package com.arflix.tv.data.repository

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RefreshGateTest {
    @Test fun rejectedTokenIsNeverRetriedButANewLoginIs() {
        val gate = RefreshGate()
        assertTrue(gate.mayRefresh("old", 0L))
        gate.onFailure("old", 401, 0L)
        assertFalse(gate.mayRefresh("old", 0L))
        assertFalse(gate.mayRefresh("old", 10 * 60_000L))
        assertTrue(gate.isRejected("old"))
        // Logging in again stores a new refresh token.
        assertTrue(gate.mayRefresh("new", 1L))
    }

    @Test fun networkFailuresBackOffThenRetry() {
        val gate = RefreshGate()
        gate.onFailure("t", null, 1_000L)
        assertFalse(gate.mayRefresh("t", 1_000L + REFRESH_RETRY_MS - 1))
        assertTrue(gate.mayRefresh("t", 1_000L + REFRESH_RETRY_MS))
        gate.onFailure("t", 503, 50_000L)
        assertFalse(gate.mayRefresh("t", 60_000L))
        assertFalse(gate.isRejected("t"))
    }

    @Test fun missingTokenNeverRefreshes() {
        assertFalse(RefreshGate().mayRefresh(null, 0L))
        assertFalse(RefreshGate().mayRefresh("", 0L))
    }
}
