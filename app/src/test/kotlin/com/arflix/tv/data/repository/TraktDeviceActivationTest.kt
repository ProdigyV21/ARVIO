package com.arflix.tv.data.repository

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
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

    @Test fun retriesAfterServerCooldown() = runTest {
        val activation = TraktDeviceActivation({ testScheduler.currentTime })
        var calls = 0
        assertEquals("code", activation.request { if (++calls == 1) throw failure(); "code" })
        assertEquals(120_000L, testScheduler.currentTime)
        assertEquals(2, calls)
    }

    @Test fun persistentRateLimitIsBoundedAndCooldownSurvivesRetry() = runTest {
        val activation = TraktDeviceActivation({ testScheduler.currentTime })
        var calls = 0
        try { activation.request { calls++; throw failure() }; fail() } catch (_: HttpException) { }
        assertEquals(3, calls)
        activation.request { "code" }
        assertEquals(360_000L, testScheduler.currentTime)
    }

    @Test fun cancellationStopsRequestsButRetainsCooldown() = runTest {
        val activation = TraktDeviceActivation({ testScheduler.currentTime })
        var calls = 0
        val job = launch { activation.request { calls++; throw failure() } }
        runCurrent()
        job.cancelAndJoin()
        activation.request { calls++; "code" }
        assertEquals(2, calls)
        assertEquals(120_000L, testScheduler.currentTime)
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
        assertFalse(isTransientTraktPollFailure(IllegalStateException("Trakt credentials missing in this APK")))
    }

    @Test fun retryAfterSupportsDatesAndNeverShortensServerDelay() {
        assertEquals(120_000L, traktRetryDelayMs("120", 6_000L))
        assertEquals(120_000L, traktRetryDelayMs("Thu, 1 Jan 1970 00:02:00 GMT", 6_000L, 0L))
        assertEquals(60_000L, traktRetryDelayMs("invalid", 60_000L))
        assertEquals(60_000L, traktRetryDelayMs(null, 60_000L))
    }
}
