package com.arflix.tv.ui.screens.player.subtitles

import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

/** org.json and Log are part of the Android framework, so these run under Robolectric. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = android.app.Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class LiteLlmChatTest {

    private lateinit var server: MockWebServer
    private val client = OkHttpClient()

    @Before fun setUp() {
        server = MockWebServer().apply { start() }
    }

    @After fun tearDown() {
        server.shutdown()
    }

    private fun config(model: String = "gpt-4.1-mini") =
        LiteLlmConfig(baseUrl = server.url("/").toString(), model = model)

    private fun completion(content: String, finishReason: String = "stop") =
        MockResponse().setBody(
            JSONObject().put("choices", org.json.JSONArray().put(
                JSONObject()
                    .put("message", JSONObject().put("role", "assistant").put("content", content))
                    .put("finish_reason", finishReason)
            )).toString()
        )

    @Test fun baseUrlIsNormalised() {
        assertEquals("http://host:4000", normalizeLiteLlmBaseUrl(" http://host:4000/ "))
        assertEquals("http://host:4000", normalizeLiteLlmBaseUrl("http://host:4000/v1"))
        assertEquals("https://proxy.example.com", normalizeLiteLlmBaseUrl("https://proxy.example.com/v1/"))
        assertEquals("http://192.168.1.10:4000", normalizeLiteLlmBaseUrl("192.168.1.10:4000"))
        assertEquals("", normalizeLiteLlmBaseUrl("  "))
        assertFalse(LiteLlmConfig("http://host:4000", " ").isConfigured)
        assertFalse(LiteLlmConfig("", "gpt-4.1-mini").isConfigured)
        assertTrue(LiteLlmConfig("http://host:4000", "gpt-4.1-mini").isConfigured)
    }

    @Test fun requestIsAnOpenAiChatCompletionWithDropParams() = runTest {
        server.enqueue(completion("[\"0: Hola\"]"))
        val reply = liteLlmChatCall(client, config(" my-alias "), "sk-proxy", "system prompt", "user text")
        assertEquals(LiteLlmReply.Text("[\"0: Hola\"]"), reply)

        val request = server.takeRequest()
        assertEquals("/v1/chat/completions", request.path)
        assertEquals("Bearer sk-proxy", request.getHeader("Authorization"))
        val body = JSONObject(request.body.readUtf8())
        assertEquals("my-alias", body.getString("model"))
        assertTrue(body.getBoolean("drop_params"))
        val messages = body.getJSONArray("messages")
        assertEquals("system", messages.getJSONObject(0).getString("role"))
        assertEquals("system prompt", messages.getJSONObject(0).getString("content"))
        assertEquals("user", messages.getJSONObject(1).getString("role"))
        assertEquals("user text", messages.getJSONObject(1).getString("content"))
    }

    @Test fun transientErrorsAreRetried() = runTest {
        server.enqueue(MockResponse().setResponseCode(503))
        server.enqueue(MockResponse().setResponseCode(502))
        server.enqueue(completion("[\"0: Ciao\"]"))
        assertEquals(LiteLlmReply.Text("[\"0: Ciao\"]"), liteLlmChatCall(client, config(), "k", "s", "u"))
        assertEquals(3, server.requestCount)
    }

    @Test fun rateLimitAndClientErrorsAreNotRetried() = runTest {
        server.enqueue(MockResponse().setResponseCode(429))
        assertEquals(LiteLlmReply.Failed("RATE_LIMITED"), liteLlmChatCall(client, config(), "k", "s", "u"))

        server.enqueue(MockResponse().setResponseCode(401).setBody("""{"error":{"message":"Authentication Error, invalid key","type":"auth_error"}}"""))
        assertEquals(
            LiteLlmReply.Failed("HTTP 401: Authentication Error, invalid key"),
            liteLlmChatCall(client, config(), "bad", "s", "u")
        )
        assertEquals(2, server.requestCount)
    }

    @Test fun unconfiguredProxyFailsWithoutACall() = runTest {
        assertTrue(liteLlmChatCall(client, LiteLlmConfig(), "k", "s", "u") is LiteLlmReply.Failed)
        assertEquals(0, server.requestCount)
    }

    @Test fun outputLimitIsReportedForBisection() {
        assertEquals(
            LiteLlmReply.Failed(CLAUDE_ERROR_MAX_TOKENS),
            parseLiteLlmReply("""{"choices":[{"message":{"content":"[\"0: Hola\","},"finish_reason":"length"}]}""")
        )
    }

    @Test fun contentFilterAndRefusalAreReportedLikeAContentBlock() {
        assertEquals(LiteLlmReply.Refused, parseLiteLlmReply("""{"choices":[{"message":{"content":null},"finish_reason":"content_filter"}]}"""))
        assertEquals(LiteLlmReply.Refused, parseLiteLlmReply("""{"choices":[{"message":{"content":"","refusal":"I can't help"},"finish_reason":"stop"}]}"""))
    }

    @Test fun contentPartsAreJoined() {
        assertEquals(
            LiteLlmReply.Text("[\"0: Hallo\"]"),
            parseLiteLlmReply("""{"choices":[{"message":{"content":[{"type":"text","text":"[\"0: "},{"type":"text","text":"Hallo\"]"}]},"finish_reason":"stop"}]}""")
        )
    }

    @Test fun emptyOrUnreadableRepliesFail() {
        assertTrue(parseLiteLlmReply("""{"choices":[{"message":{"content":""},"finish_reason":"stop"}]}""") is LiteLlmReply.Failed)
        assertTrue(parseLiteLlmReply("""{"choices":[]}""") is LiteLlmReply.Failed)
        assertTrue(parseLiteLlmReply("not json") is LiteLlmReply.Failed)
    }

    @Test fun modelsAreListedFromTheProxy() = runTest {
        server.enqueue(MockResponse().setBody("""{"object":"list","data":[{"id":"gpt-4.1-mini"},{"id":"gemini-2.5-flash"},{"id":"gpt-4.1-mini"},{"id":""}]}"""))
        val models = fetchLiteLlmModels(client, server.url("/v1").toString(), "sk-proxy").getOrThrow()
        assertEquals(listOf("gemini-2.5-flash", "gpt-4.1-mini"), models)
        val request = server.takeRequest()
        assertEquals("/v1/models", request.path)
        assertEquals("Bearer sk-proxy", request.getHeader("Authorization"))

        server.enqueue(MockResponse().setResponseCode(401).setBody("""{"error":{"message":"invalid key"}}"""))
        val failure = fetchLiteLlmModels(client, server.url("/").toString(), "bad").exceptionOrNull()
        assertEquals("HTTP 401: invalid key", failure?.message)
    }

    @Test fun aLiteLlmKeyNamesNoProvider() {
        // A proxy key must not switch the model away from LiteLLM.
        assertNull(subtitleAiModelForKey("sk-1234"))
    }

    @Test fun serviceTranslatesThroughTheProxy() = runTest {
        server.enqueue(completion("[\"0: Hola\",\"1: Adiós\"]"))
        val service = SubtitleTranslationService(
            apiKeyProvider = { "sk-proxy" },
            modelProvider = { SubtitleAiModel.LITELLM },
            liteLlmConfigProvider = { config() }
        )
        val result = service.translateBatch(listOf("Hello", "Goodbye"), "Spanish")
        assertTrue(result.errorMessage, result.success)
        assertEquals(listOf("Hola", "Adiós"), result.lines)
        val body = JSONObject(server.takeRequest().body.readUtf8())
        assertTrue(body.getJSONArray("messages").getJSONObject(1).getString("content").contains("0: Hello"))
    }
}
