package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.C
import androidx.media3.common.ParserException
import androidx.media3.common.Player
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.HttpDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.EOFException
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import io.mockk.mockk
import okhttp3.internal.http2.ErrorCode
import okhttp3.internal.http2.StreamResetException

class LiveDropRecoveryTest {
    private fun classify(error: Throwable, network: Boolean = true, offline: Boolean = false) = classifyLiveDrop(error, network, offline)

    @Test fun dropsAreClassifiedOnTheCauseChainAndTheNetwork() {
        val status = HttpDataSource.InvalidResponseCodeException(503, "Busy", null, emptyMap(), mockk<DataSpec>(relaxed = true), byteArrayOf())
        val onAHealthyNetwork = listOf(
            UnknownHostException("h") to LiveDrop.NetworkLost,
            ConnectException("c") to LiveDrop.NetworkLost,
            NoRouteToHostException("r") to LiveDrop.NetworkLost,
            SocketTimeoutException("t") to LiveDrop.NetworkLost,
            SocketException("Connection reset") to LiveDrop.Ambiguous,
            EOFException() to LiveDrop.Ambiguous,
            ProtocolException("unexpected end of stream") to LiveDrop.Ambiguous,
            SSLException("reset by peer") to LiveDrop.Ambiguous,
            StreamResetException(ErrorCode.CANCEL) to LiveDrop.Ambiguous,
            LiveCleanEofException() to LiveDrop.Ambiguous,
            status to LiveDrop.Other,
            IllegalStateException("decoder") to LiveDrop.Other,
            ParserException.createForMalformedContainer("bad", null) to LiveDrop.Other,
            IOException("plain") to LiveDrop.Other,
        )
        onAHealthyNetwork.forEach { (error, expected) ->
            assertEquals(error.toString(), expected, classify(error))
            assertEquals("wrapped $error", expected, classify(IOException("load", IOException("open", error))))
        }
        var tooDeep: Throwable = SocketException("Connection reset")
        repeat(16) { tooDeep = IOException("wrap", tooDeep) }
        assertEquals("the cause chain is bounded at sixteen links", LiveDrop.Other, classify(tooDeep))
        // No network or a recent outage turns a reset into a lost network; an HTTP status keeps today's path.
        for ((network, offline) in listOf(false to false, true to true)) {
            assertEquals(LiveDrop.NetworkLost, classify(SocketException("Connection reset"), network, offline))
            assertEquals(LiveDrop.NetworkLost, classify(EOFException(), network, offline))
            assertEquals(LiveDrop.Other, classify(status, network, offline))
        }
    }

    @Test fun theBudgetRefillsAfterAMinuteReadyButNeverForACleanEnd() {
        assertEquals(3, liveRetryAttempt(current = 2, readySinceMs = null, nowMs = 100_000L))
        assertEquals(3, liveRetryAttempt(current = 2, readySinceMs = 50_000L, nowMs = 50_000L + DEFAULT_LIVE_RETRY_REFILL_MS - 1))
        assertEquals(1, liveRetryAttempt(current = 2, readySinceMs = 50_000L, nowMs = 50_000L + DEFAULT_LIVE_RETRY_REFILL_MS))
        val longAfter = 10 * DEFAULT_LIVE_RETRY_REFILL_MS
        assertEquals(3, liveDropAttempt(2, 0L, longAfter, cleanEof = true))
        assertEquals(1, liveDropAttempt(2, 0L, longAfter, cleanEof = false))
    }

    @Test fun aCleanLiveEndIsADropOnlyOnALimitedChannelAndRestartsAtMostThreeTimes() {
        assertTrue(isLiveDropEnd(Player.STATE_ENDED, C.TIME_UNSET, isCatchup = false, limited = true))
        assertTrue("a live item that learnt a duration at its end", isLiveDropEnd(Player.STATE_ENDED, 19_000L, isCatchup = false, limited = true, isLiveItem = true))
        assertFalse("sports add-ons and No limit keep today's path", isLiveDropEnd(Player.STATE_ENDED, C.TIME_UNSET, isCatchup = false, limited = false))
        assertFalse("catch-up really ends", isLiveDropEnd(Player.STATE_ENDED, C.TIME_UNSET, isCatchup = true, limited = true))
        assertFalse("a finite item really ends", isLiveDropEnd(Player.STATE_ENDED, 60_000L, isCatchup = false, limited = true))

        var restarts = 0
        var budget = 0
        // What the listener does for a TS item that plays a while and ends, over and over.
        while (liveEofRestartAllowed(restarts)) {
            budget = liveDropAttempt(budget, 0L, 10 * DEFAULT_LIVE_RETRY_REFILL_MS, cleanEof = true)
            restarts++
            assertTrue("must stop", restarts < 10)
        }
        assertEquals(3, restarts)
        assertEquals(3, budget)
    }
}
