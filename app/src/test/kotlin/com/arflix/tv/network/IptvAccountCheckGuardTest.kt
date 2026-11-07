package com.arflix.tv.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import java.util.concurrent.atomic.AtomicLong

class IptvAccountCheckGuardTest {
    private class Rig : AutoCloseable {
        val panel = IptvStreamServerFixture()
        val clock = AtomicLong(1_000L)
        val guard = IptvProviderRequestGuard(clock::get) { clock.addAndGet(it) }
        val metadata = OkHttpClient.Builder().retryOnConnectionFailure(false)
            .addInterceptor(guard.preflightInterceptor()).addNetworkInterceptor(guard).build()
        val check = OkHttpClient.Builder().retryOnConnectionFailure(false)
            .addInterceptor(guard.preflightInterceptor()).addNetworkInterceptor(guard.accountCheckInterceptor()).build()

        fun get(client: OkHttpClient, path: String): Response =
            client.newCall(Request.Builder().url("${panel.baseUrl}$path").build()).execute()
        fun accountCheck() = get(check, "/player_api.php?username=u&password=p")
        override fun close() = panel.close()
    }

    @Test fun failedCheckDoesNotDeferTheNextProbe() {
        listOf(401, 403, 429, 500, 503, 513).forEach { status ->
            Rig().use { rig ->
                rig.panel.answer("/probe", 200)
                rig.panel.setAccountAnswer(status, "")
                rig.accountCheck().use { assertEquals(status, it.code) }
                rig.get(rig.metadata, "/probe").use { assertEquals("HTTP $status", 200, it.code) }
                assertEquals(1, rig.panel.accountCheckCount)
                assertEquals(1, rig.panel.streamRequestCount)
            }
        }
    }

    @Test fun checkStillRespectsAnExistingBlockAndReachesNoServer() {
        Rig().use { rig ->
            rig.panel.answer("/guide", 403)
            rig.get(rig.metadata, "/guide").close()
            assertThrows(IptvProviderRequestDeferredException::class.java) { rig.accountCheck() }
            assertEquals(0, rig.panel.accountCheckCount)
        }
    }
}
