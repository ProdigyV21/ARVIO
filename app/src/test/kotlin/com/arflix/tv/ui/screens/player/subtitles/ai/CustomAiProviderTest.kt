package com.arflix.tv.ui.screens.player.subtitles.ai

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CustomAiProviderTest {

    private val chat = CustomAiConfig(enabled = true, protocol = AiProtocol.OPENAI_CHAT, baseUrl = "https://api.deepseek.com/v1/", apiKey = "sk-x", model = "deepseek-chat")

    @Test fun `endpoint is appended to a base url and kept when already complete`() {
        assertEquals("https://api.deepseek.com/v1/chat/completions", chat.endpointUrl())
        assertEquals("https://api.openai.com/v1/responses",
            CustomAiConfig.resolveEndpoint("https://api.openai.com/v1", AiProtocol.OPENAI_RESPONSES))
        assertEquals("https://api.anthropic.com/v1/messages",
            CustomAiConfig.resolveEndpoint("https://api.anthropic.com/v1", AiProtocol.ANTHROPIC))
        assertEquals("https://x.example/openai/chat/completions",
            CustomAiConfig.resolveEndpoint("https://x.example/openai/chat/completions", AiProtocol.ANTHROPIC))
    }

    @Test fun `completeness - key optional only for local servers`() {
        assertTrue(chat.isActive)
        assertFalse(chat.copy(apiKey = "").isComplete)
        assertTrue(chat.copy(apiKey = "", baseUrl = "http://192.168.1.5:11434/v1").isComplete)
        assertFalse(chat.copy(model = "").isComplete)
        assertFalse(chat.copy(enabled = false).isActive)
    }

    @Test fun `request bodies per protocol`() {
        val c = CustomAiWire.buildBody(chat, "SYS", "USER", true)
        assertEquals("deepseek-chat", c.getString("model"))
        assertEquals("system", c.getJSONArray("messages").getJSONObject(0).getString("role"))
        assertEquals("USER", c.getJSONArray("messages").getJSONObject(1).getString("content"))

        val r = CustomAiWire.buildBody(chat.copy(protocol = AiProtocol.OPENAI_RESPONSES), "SYS", "USER", true)
        assertEquals("SYS", r.getString("instructions"))
        assertEquals("USER", r.getString("input"))

        val a = CustomAiWire.buildBody(chat.copy(protocol = AiProtocol.ANTHROPIC, temperature = ""), "SYS", "USER", true)
        assertEquals("SYS", a.getString("system"))
        assertTrue(a.getInt("max_tokens") > 0)
        assertFalse(a.has("temperature"))
    }

    @Test fun `auth headers per protocol, extra headers override`() {
        assertTrue(CustomAiWire.headers(chat).contains("Authorization" to "Bearer sk-x"))
        val anth = CustomAiWire.headers(chat.copy(protocol = AiProtocol.ANTHROPIC, extraHeaders = "anthropic-version: 2024-01-01\nX-Test: 1"))
        assertTrue(anth.contains("x-api-key" to "sk-x"))
        assertTrue(anth.contains("anthropic-version" to "2024-01-01"))
        assertEquals(1, anth.count { it.first.equals("anthropic-version", true) })
        assertTrue(anth.contains("X-Test" to "1"))
    }

    @Test fun `response text extraction for the three APIs`() {
        assertEquals("[\"0: 你好\"]", CustomAiWire.extractText(AiProtocol.OPENAI_CHAT,
            """{"choices":[{"message":{"role":"assistant","content":"<think>x</think>[\"0: 你好\"]"}}]}"""))
        assertEquals("ok", CustomAiWire.extractText(AiProtocol.OPENAI_RESPONSES, """{"output_text":"ok"}"""))
        assertEquals("ok2", CustomAiWire.extractText(AiProtocol.OPENAI_RESPONSES,
            """{"output":[{"type":"reasoning"},{"type":"message","content":[{"type":"output_text","text":"ok2"}]}]}"""))
        assertEquals("hi", CustomAiWire.extractText(AiProtocol.ANTHROPIC,
            """{"content":[{"type":"thinking","thinking":"..."},{"type":"text","text":"hi"}]}"""))
        assertNull(CustomAiWire.extractText(AiProtocol.ANTHROPIC, """{"content":[]}"""))
        assertEquals("bad key", CustomAiWire.errorMessage("""{"error":{"message":"bad key","type":"auth"}}"""))
    }

    @Test fun `config round-trips through storage encoding`() {
        val c = chat.copy(temperature = "", extraHeaders = "A: b", maxTokens = 2048, protocol = AiProtocol.ANTHROPIC)
        assertEquals(c, CustomAiConfigStore.decode(CustomAiConfigStore.encode(c)))
        assertEquals(CustomAiConfig(), CustomAiConfigStore.decode("not json"))
    }

    @Test fun `thinking is switched off per provider and can be disabled`() {
        val ds = CustomAiWire.buildBody(chat, "S", "U", true)
        assertEquals("disabled", ds.getJSONObject("thinking").getString("type"))
        assertFalse(CustomAiWire.buildBody(chat.copy(disableThinking = false), "S", "U", true).has("thinking"))
        assertFalse(CustomAiWire.buildBody(chat, "S", "U", true, withThinkingControl = false).has("thinking"))
        val qwen = CustomAiWire.buildBody(chat.copy(baseUrl = "https://dashscope.aliyuncs.com/compatible-mode/v1", model = "qwen-plus"), "S", "U", true)
        assertEquals(false, qwen.getBoolean("enable_thinking"))
        val gpt5 = CustomAiWire.buildBody(chat.copy(baseUrl = "https://api.openai.com/v1", model = "gpt-5-mini"), "S", "U", true)
        assertEquals("minimal", gpt5.getString("reasoning_effort"))
        val gpt41 = CustomAiWire.buildBody(chat.copy(baseUrl = "https://api.openai.com/v1", model = "gpt-4.1-mini"), "S", "U", true)
        assertFalse(gpt41.has("reasoning_effort"))
        val resp = CustomAiWire.buildBody(chat.copy(protocol = AiProtocol.OPENAI_RESPONSES, baseUrl = "https://api.openai.com/v1", model = "o4-mini"), "S", "U", true)
        assertEquals("low", resp.getJSONObject("reasoning").getString("effort"))
        val anth = CustomAiWire.buildBody(chat.copy(protocol = AiProtocol.ANTHROPIC), "S", "U", true)
        assertEquals("disabled", anth.getJSONObject("thinking").getString("type"))
        val unknown = CustomAiWire.buildBody(chat.copy(baseUrl = "https://my.proxy/v1", model = "llama"), "S", "U", true)
        assertFalse(unknown.has("thinking") || unknown.has("reasoning_effort") || unknown.has("enable_thinking"))
    }

    @Test fun `extra body json is merged and invalid json ignored`() {
        val b = CustomAiWire.buildBody(chat.copy(extraBody = "{\"top_p\":0.5,\"thinking\":{\"type\":\"enabled\"}}"), "S", "U", true)
        assertEquals(0.5, b.getDouble("top_p"), 1e-9)
        assertEquals("enabled", b.getJSONObject("thinking").getString("type"))
        assertFalse(CustomAiWire.buildBody(chat.copy(extraBody = "{oops"), "S", "U", true).has("oops"))
    }

    @Test fun `usage is read from all three APIs`() {
        val c = CustomAiWire.extractUsage(AiProtocol.OPENAI_CHAT,
            """{"usage":{"prompt_tokens":100,"completion_tokens":40,"prompt_cache_hit_tokens":64,"completion_tokens_details":{"reasoning_tokens":30}}}""")!!
        assertEquals(AiUsage(100, 40, 64, 30), c)
        val r = CustomAiWire.extractUsage(AiProtocol.OPENAI_RESPONSES,
            """{"usage":{"input_tokens":10,"output_tokens":5,"input_tokens_details":{"cached_tokens":2},"output_tokens_details":{"reasoning_tokens":1}}}""")!!
        assertEquals(AiUsage(10, 5, 2, 1), r)
        val a = CustomAiWire.extractUsage(AiProtocol.ANTHROPIC, """{"usage":{"input_tokens":7,"output_tokens":3,"cache_read_input_tokens":5}}""")!!
        assertEquals(AiUsage(12, 3, 5, 0), a)
        assertNull(CustomAiWire.extractUsage(AiProtocol.OPENAI_CHAT, "{}"))
    }

    @Test fun `several services round-trip and the active one is flagged enabled`() {
        val a = chat.copy(id = "a", name = "DeepSeek", enabled = false)
        val b = chat.copy(id = "b", name = "Claude", protocol = AiProtocol.ANTHROPIC, enabled = false)
        val p = CustomAiProfiles(listOf(a, b), activeId = "b")
        val back = CustomAiConfigStore.decodeProfiles(CustomAiConfigStore.encodeProfiles(p))
        assertEquals(listOf(a, b), back.profiles)
        assertEquals("b", back.activeId)
        assertTrue(back.active.isActive)
        assertEquals("Claude", back.active.name)
        assertFalse(CustomAiProfiles(listOf(a), null).active.isActive)
        assertNull(CustomAiConfigStore.decodeProfiles("""{"activeId":"zzz","profiles":[]}""").activeId)
    }
}
