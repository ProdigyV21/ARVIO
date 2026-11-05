package com.arflix.tv.network

import com.arflix.tv.data.repository.IptvAccountInfoParser
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import java.util.concurrent.atomic.AtomicLong

// That zaps ask nothing is LiveTvStreamGateTest's job; this pins the answers the gate reads.
@OptIn(ExperimentalCoroutinesApi::class)
class IptvAccountAvailabilityTest {
    @Test fun aFullPanelEndsFullAtSixSecondsOnAColdStartAndFifteenOnAReconnect() = runTest {
        val coldAskedAt = ArrayList<Long>()
        assertEquals(IptvAdmission.Full(2, 2), IptvAccountAvailability.admit { coldAskedAt.add(currentTime); IptvAccountUsage(2, 2) })
        assertEquals(listOf(0L, 2_000L, 4_000L), coldAskedAt)
        assertEquals(MAX_IPTV_CHECK_WAIT_MS, currentTime)

        val start = currentTime
        val reconnectAskedAt = ArrayList<Long>()
        assertEquals(
            IptvAdmission.Full(2, 2),
            IptvAccountAvailability.admitReconnect { reconnectAskedAt.add(currentTime - start); IptvAccountUsage(2, 2) },
        )
        assertEquals(listOf(0L, 3_000L, 6_000L, 9_000L, 12_000L), reconnectAskedAt)
        assertEquals(MAX_IPTV_RECONNECT_CHECK_WAIT_MS, currentTime - start)
    }

    @Test fun aFreePanelProceedsAtOnceAndSpaceAtTwoSecondsProceedsThen() = runTest {
        var fetches = 0
        assertEquals(IptvAdmission.Proceed, IptvAccountAvailability.admit { fetches++; IptvAccountUsage(1, 2) })
        assertEquals(1, fetches)
        assertEquals(0L, currentTime)
        val answers = ArrayDeque(listOf(IptvAccountUsage(1, 1), IptvAccountUsage(0, 1)))
        assertEquals(IptvAdmission.Proceed, IptvAccountAvailability.admit { answers.removeFirst() })
        assertEquals(2_000L, currentTime)
        assertTrue(answers.isEmpty())
    }

    @Test fun unknownAnswersFromARealPanelProceedWithoutPolling() = runTest {
        IptvStreamServerFixture().use { panel ->
            val client = checkClient()
            for ((status, body) in listOf(200 to "<html>maintenance</html>", 500 to "", 200 to """{"user_info":{"auth":0}}""")) {
                panel.setAccountAnswer(status, body)
                assertEquals("HTTP $status $body", IptvAdmission.Proceed, IptvAccountAvailability.admit { fetchUsage(client, panel) })
            }
            assertEquals(IptvAdmission.Proceed, IptvAccountAvailability.admit { throw IOException("offline") })
            assertEquals(3, panel.accountCheckCount)
            assertEquals(0, panel.streamRequestCount)
            assertEquals(0L, currentTime)
        }
    }

    @Test fun hangingFirstFetchProceedsAtItsCapAndIsCancelled() = runTest {
        var cancelled = false
        val result = IptvAccountAvailability.admit {
            try { awaitCancellation() } catch (e: CancellationException) { cancelled = true; throw e }
        }
        assertEquals(IptvAdmission.Proceed, result)
        assertEquals(MAX_IPTV_FIRST_CHECK_MS, currentTime)
        assertTrue(cancelled)
    }

    @Test fun cancellingDuringAdmitStopsFurtherFetches() = runTest {
        var fetches = 0
        val waiting = launch { IptvAccountAvailability.admit { fetches++; IptvAccountUsage(1, 1) } }
        advanceTimeBy(3_000L)
        runCurrent()
        assertEquals(2, fetches)
        waiting.cancel()
        advanceUntilIdle()
        assertEquals(2, fetches)
        assertTrue(waiting.isCancelled)
        assertEquals(3_000L, currentTime)
    }

    @Test fun availabilityReadsOnlyValidLiveCounts() {
        assertEquals(
            IptvAccountUsage(1, 2),
            IptvAccountInfoParser.parseXtreamAvailability("""{"user_info":{"auth":1,"status":"Active","active_cons":"1","max_connections":"2"}}"""),
        )
        assertEquals(
            IptvAccountUsage(0, 3),
            IptvAccountInfoParser.parseXtreamAvailability("""{"user_info":{"active_cons":0,"max_connections":3}}"""),
        )
        listOf(
            null,
            "",
            "<html>Forbidden</html>",
            "[]",
            """{"user_info":""}""",
            """{"user_info":{"auth":0,"active_cons":"0","max_connections":"1"}}""",
            """{"user_info":{"status":"Banned","active_cons":"0","max_connections":"1"}}""",
            """{"user_info":{"max_connections":"1"}}""",
            """{"user_info":{"active_cons":"1"}}""",
            """{"user_info":{"active_cons":"-1","max_connections":"1"}}""",
            """{"user_info":{"active_cons":"some","max_connections":"1"}}""",
            """{"user_info":{"active_cons":"0","max_connections":"0"}}""",
        ).forEach { body -> assertNull(body, IptvAccountInfoParser.parseXtreamAvailability(body)) }
    }

    private fun checkClient(): OkHttpClient {
        val clock = AtomicLong(1_000L)
        val guard = IptvProviderRequestGuard(clock::get) { clock.addAndGet(it) }
        return OkHttpClient.Builder().retryOnConnectionFailure(false)
            .addInterceptor(guard.preflightInterceptor())
            .addNetworkInterceptor(guard.accountCheckInterceptor()).build()
    }

    @Suppress("SENSELESS_COMPARISON")
    private fun fetchUsage(client: OkHttpClient, panel: IptvStreamServerFixture): IptvAccountUsage? =
        client.newCall(Request.Builder().url("${panel.baseUrl}/player_api.php?username=u&password=p").build())
            .execute().use { response ->
                IptvAccountInfoParser.parseXtreamAvailability(response.body?.string())
            }
}
