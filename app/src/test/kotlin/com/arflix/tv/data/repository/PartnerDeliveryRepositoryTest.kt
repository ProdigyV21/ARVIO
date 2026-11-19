package com.arflix.tv.data.repository

import android.content.Context
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.time.Instant
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import com.google.gson.JsonParser

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = android.app.Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class PartnerDeliveryRepositoryTest {
    @org.junit.Before fun requirePartnerBuild() {
        org.junit.Assume.assumeTrue(com.arflix.tv.BuildConfig.PARTNER_MANAGEMENT_ENABLED)
    }

    @Test fun `public addon accepts absent or null customer credentials and respects manifest override`() = runBlocking {
        val auth = mockk<AuthRepository>()
        every { auth.authState } returns MutableStateFlow<AuthState>(AuthState.Authenticated("qa-addon", "qa@example.test", null))
        coEvery { auth.getAccessToken() } returns "fixture-token"
        val streams = mockk<StreamRepository>()
        val publicUrl = "https://example.test/manifest.json"
        val overrideUrl = "https://example.test/customer/manifest.json"
        for ((credentials, expectedUrl) in listOf(
            "" to publicUrl,
            ",\"credentials\":null" to publicUrl,
            ",\"credentials\":{\"manifestUrl\":null}" to publicUrl,
            ",\"credentials\":{\"manifestUrl\":\"$overrideUrl\"}" to overrideUrl
        )) {
            coEvery { streams.prepareCustomAddon(expectedUrl) } returns Result.success(
                com.arflix.tv.data.model.Addon("original-id", "QA", "1", "QA", false,
                    type = com.arflix.tv.data.model.AddonType.CUSTOM, url = expectedUrl))
            val client = OkHttpClient.Builder().addInterceptor { chain ->
                Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK")
                    .body("""{"managed":true,"active":true,"organizationId":"qa-org","revision":7,
                      "leaseUntil":"${Instant.now().plusSeconds(3600)}",
                      "config":{"catalogs":[],"collectionUrls":[],"settings":{}},
                      "permissions":{"editCatalogs":false,"addConnections":false,"editAppearance":true,"editPlayback":true,"editMetadata":false},
                      "services":[{"kind":"addon","id":"public-addon","name":"QA","url":"$publicUrl"$credentials}]}""".toResponseBody()).build()
            }.build()
            val repository = PartnerDeliveryRepository(RuntimeEnvironment.getApplication(), auth, client, dagger.Lazy { streams })
            val job = launch { repository.runForegroundSession() }
            try {
                val delivery = withTimeout(10_000) { repository.state.filterNotNull().first() }.delivery
                assertEquals("partner-qa-org-public-addon", delivery.addons.single().id)
                assertEquals(expectedUrl, delivery.addons.single().url)
                assertTrue(delivery.addons.single().isInstalled)
            } finally { job.cancelAndJoin() }
        }
    }

    @Test fun `device registers then acknowledges applied revision and removes delivery on sign out`() = runBlocking {
        val auth = mockk<AuthRepository>()
        val authState = MutableStateFlow<AuthState>(AuthState.Authenticated("qa-account", "qa@example.test", null))
        every { auth.authState } returns authState
        coEvery { auth.getAccessToken() } returns "fixture-token"
        val requests = LinkedBlockingQueue<String>()
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val buffer = okio.Buffer()
            chain.request().body!!.writeTo(buffer)
            requests.add(buffer.readUtf8())
            assertEquals("Bearer fixture-token", chain.request().header("Authorization"))
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK")
                .body("""{"managed":true,"active":true,"organizationId":"qa-org","revision":7,
                  "leaseUntil":"${Instant.now().plusSeconds(3600)}",
                  "config":{"catalogs":[],"collectionUrls":[],"settings":{}},
                  "permissions":{"editCatalogs":true,"addConnections":true,"editAppearance":true,"editPlayback":true,"editMetadata":true},
                  "services":[{"kind":"iptv","id":"qa-service","name":"QA","url":"https://example.test",
                    "credentials":{"username":"qa","password":"fixture-only"}}]}""".toResponseBody()).build()
        }.build()
        val context = RuntimeEnvironment.getApplication()
        val repository = PartnerDeliveryRepository(context, auth, client)
        val job = launch { repository.runForegroundSession() }
        try {
            val delivered = withTimeout(10_000) { repository.state.filterNotNull().first() }
            assertEquals("qa-account", delivered.accountId)
            assertEquals(7, delivered.delivery.revision)
            val first = JsonParser.parseString(requests.poll(1, TimeUnit.SECONDS)!!).asJsonObject.getAsJsonObject("device")
            assertEquals(0, first.get("appliedRevision").asInt)
            kotlinx.coroutines.delay(2300)
            val ack = JsonParser.parseString(requests.poll(2, TimeUnit.SECONDS)!!).asJsonObject.getAsJsonObject("device")
            assertEquals(7, ack.get("appliedRevision").asInt)
            assertEquals(first.get("key"), ack.get("key"))
            authState.value = AuthState.NotAuthenticated
            withTimeout(5000) { repository.state.first { it == null } }
            val prefs = context.getSharedPreferences("partner_devices", Context.MODE_PRIVATE)
            assertEquals(setOf("qa-account"), prefs.all.keys)
            assertFalse(prefs.all.toString().contains("fixture-only"))
        } finally { job.cancelAndJoin() }
    }
}
