package com.arflix.tv.data.repository

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.runCurrent
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import retrofit2.HttpException

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class TraktDeviceActivationTest {
    private fun failure(code: Int = 429, retry: String = "120"): HttpException {
        val raw = okhttp3.Response.Builder().request(Request.Builder().url("https://example.invalid").build())
            .protocol(Protocol.HTTP_1_1).code(code).message("test").header("Retry-After", retry).build()
        return HttpException(retrofit2.Response.error<String>("".toResponseBody(), raw))
    }

    @Test fun rateLimitReportsImmediatelyAndBlocksRequestsUntilCooldownEnds() = runTest {
        val activation = TraktDeviceActivation({ testScheduler.currentTime })
        var calls = 0
        try { activation.request { calls++; throw failure() }; fail() } catch (_: HttpException) { }
        assertEquals(0L, testScheduler.currentTime)
        try { activation.request { calls++; "code" }; fail() } catch (_: HttpException) { }
        assertEquals(1, calls)
        advanceTimeBy(120_000L)
        assertEquals("code", activation.request { calls++; "code" })
        assertEquals(120_000L, testScheduler.currentTime)
        assertEquals(2, calls)
    }

    @Test fun stalledRequestHasDeadlineAndNextAttemptCanSucceed() = runTest {
        val activation = TraktDeviceActivation({ testScheduler.currentTime })
        try { activation.request { delay(60_000); "late" }; fail() } catch (_: java.net.SocketTimeoutException) { }
        assertEquals(20_000L, testScheduler.currentTime)
        assertEquals("code", activation.request { "code" })
    }

    @Test fun cancellationStopsRequestsAndReleasesActivationLock() = runTest {
        val activation = TraktDeviceActivation({ testScheduler.currentTime })
        var calls = 0
        val job = launch { activation.request { calls++; delay(60_000); "late" } }
        runCurrent()
        job.cancelAndJoin()
        assertTrue(job.isCancelled)
        activation.request { calls++; "code" }
        assertEquals(2, calls)
        assertEquals(0L, testScheduler.currentTime)
    }

    @Test fun nonRateLimitErrorsAreNotRetried() = runTest {
        var calls = 0
        try { TraktDeviceActivation().request { calls++; throw failure(401) }; fail() } catch (_: HttpException) { }
        assertEquals(1, calls)
    }

    @Test fun pollFailuresWithoutAnAnswerFromTraktAreTransient() {
        assertTrue(isTransientTraktPollFailure(java.net.UnknownHostException("Unable to resolve host \"api.trakt.tv\"")))
        assertTrue(isTransientTraktPollFailure(java.net.SocketTimeoutException("timeout")))
        assertTrue(isTransientTraktPollFailure(java.net.ConnectException("failed to connect")))
        assertFalse(isTransientTraktPollFailure(failure(404)))
        assertFalse(isTransientTraktPollFailure(failure(410)))
        assertTrue(isTransientTraktPollFailure(failure(503)))
        assertTrue(isTransientTraktPollFailure(failure(502)))
        assertFalse(isTransientTraktPollFailure(failure(401)))
        assertFalse(isTransientTraktPollFailure(failure(403)))
        assertFalse(isTransientTraktPollFailure(IllegalStateException("Trakt credentials missing in this APK")))
        // A rejected certificate (wrong device clock) will not heal by polling on.
        assertFalse(isTransientTraktPollFailure(
            javax.net.ssl.SSLHandshakeException("handshake").apply {
                initCause(java.security.cert.CertificateExpiredException("expired"))
            }
        ))
    }

    @Test fun retryAfterSupportsDatesAndNeverShortensServerDelay() {
        assertEquals(120_000L, traktRetryDelayMs("120", 6_000L))
        assertEquals(120_000L, traktRetryDelayMs("Thu, 1 Jan 1970 00:02:00 GMT", 6_000L, 0L))
        assertEquals(60_000L, traktRetryDelayMs("invalid", 60_000L))
        assertEquals(60_000L, traktRetryDelayMs(null, 60_000L))
    }
}
