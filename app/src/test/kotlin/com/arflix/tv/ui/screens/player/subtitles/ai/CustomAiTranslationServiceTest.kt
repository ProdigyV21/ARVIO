package com.arflix.tv.ui.screens.player.subtitles.ai

import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = android.app.Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class CustomAiTranslationServiceTest {
    private val config = CustomAiConfig(
        enabled = true,
        baseUrl = "https://example.test/v1",
        model = "subtitle-model",
        apiKey = "test-key",
    )

    @Test fun malformedUrlCannotBeActivatedOrCrashConnectionTest() = runBlocking {
        val malformed = config.copy(baseUrl = "example.test/v1")
        val service = SubtitleTranslationService(apiKeyProvider = { "" }, customConfigProvider = { malformed })
        assertFalse(malformed.isComplete)
        assertFalse(service.isConfigured())
        assertFalse(service.testCustom(malformed, "French").success)
    }

    @Test fun invalidHeaderCannotEscapeThePlaybackTranslationCall() = runBlocking {
        val malformed = config.copy(extraHeaders = "X-Test: café")
        val service = SubtitleTranslationService(apiKeyProvider = { "" }, customConfigProvider = { malformed })
        assertFalse(service.translateBatch(listOf("Hello"), "French").success)
    }

    @Test fun customOnlyConfigurationCanTranslate() = runBlocking {
        MockWebServer().use { server ->
            server.enqueue(chatReply("0: Bonjour"))
            val service = serviceFor(server)
            assertTrue(service.isConfigured())
            assertFalse(service.canMatchSubtitleLines())
            assertEquals(listOf("Bonjour"), service.translateBatch(listOf("Hello"), "French").lines)
        }
    }

    @Test fun quotaAndKeyErrorsUseThePlayerErrorContract() = runBlocking {
        MockWebServer().use { server ->
            server.enqueue(MockResponse().setResponseCode(429).setBody("""{"error":{"message":"Limit"}}"""))
            server.enqueue(MockResponse().setResponseCode(403).setBody("""{"error":{"message":"Bad key"}}"""))
            val service = serviceFor(server)
            assertEquals("RATE_LIMITED", service.translateBatch(listOf("Hello"), "French").errorMessage)
            assertEquals("HTTP 403", service.translateBatch(listOf("Hello"), "French").errorMessage)
        }
    }

    @Test fun badRequestWithoutThinkingControlIsNotRetried() = runBlocking {
        MockWebServer().use { server ->
            repeat(2) { server.enqueue(MockResponse().setResponseCode(400)) }
            val result = serviceFor(server).translateBatch(listOf("Hello"), "French")
            assertFalse(result.success)
            assertEquals(1, server.requestCount)
        }
    }

    @Test fun truncatedChatBatchIsBisectedBeforeParsing() = runBlocking {
        MockWebServer().use { server ->
            server.enqueue(MockResponse().setBody("""{"choices":[{"finish_reason":"length","message":{"content":""}}]}"""))
            server.enqueue(chatReply("0: Bonjour"))
            server.enqueue(chatReply("0: Au revoir"))
            val result = serviceFor(server).translateBatch(listOf("Hello", "Goodbye"), "French")
            assertTrue(result.success)
            assertEquals(listOf("Bonjour", "Au revoir"), result.lines)
            assertEquals(3, server.requestCount)
        }
    }

    private fun serviceFor(server: MockWebServer) = SubtitleTranslationService(
        apiKeyProvider = { "" },
        customConfigProvider = { config.copy(baseUrl = server.url("/v1").toString()) }
    )

    private fun chatReply(line: String) =
        MockResponse().setBody("""{"choices":[{"finish_reason":"stop","message":{"content":"[\"$line\"]"}}]}""")
}
