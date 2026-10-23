package com.arflix.tv.ui.screens.player.subtitles.ai

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

/** Wire protocol of a user-configured translation model. */
enum class AiProtocol(val id: String) {
    /** OpenAI-compatible `POST …/chat/completions` (OpenAI, DeepSeek, Qwen/DashScope, SiliconFlow, OpenRouter, Groq, Ollama, LM Studio…). */
    OPENAI_CHAT("chat"),
    /** OpenAI Responses API `POST …/responses`. */
    OPENAI_RESPONSES("responses"),
    /** Anthropic Messages API `POST …/messages`. */
    ANTHROPIC("anthropic");

    val endpointSuffix: String
        get() = when (this) {
            OPENAI_CHAT -> "/chat/completions"
            OPENAI_RESPONSES -> "/responses"
            ANTHROPIC -> "/messages"
        }

    companion object {
        fun of(id: String?): AiProtocol = entries.firstOrNull { it.id == id || it.name == id } ?: OPENAI_CHAT
    }
}

/**
 * A custom translation model: any endpoint speaking one of the three protocols. When [enabled]
 * and complete, it replaces the built-in Gemini/Groq models for subtitle translation and line
 * matching. (Gemini "sync by hearing" still needs a Gemini key — it streams audio.)
 */
data class CustomAiConfig(
    /** Stable id of this saved service. */
    val id: String = "",
    /** User-visible name ("DeepSeek", "My OpenRouter"…). */
    val name: String = "",
    val enabled: Boolean = false,
    val protocol: AiProtocol = AiProtocol.OPENAI_CHAT,
    /** e.g. https://api.openai.com/v1 — or a full endpoint URL ending in the protocol path. */
    val baseUrl: String = "",
    val apiKey: String = "",
    val model: String = "",
    /** Blank = not sent (some reasoning models reject temperature). */
    val temperature: String = "0.2",
    /** Optional extra request headers, one `Name: value` per line. */
    val extraHeaders: String = "",
    /** Max output tokens (Anthropic requires one; others only when > 0). */
    val maxTokens: Int = 4096,
    /**
     * Turn the model's "thinking"/reasoning off. Translation gains nothing from it, while reasoning
     * tokens are billed as output and can be 10–50x the translation itself (DeepSeek V4/V4.1 think
     * by default). On by default.
     */
    val disableThinking: Boolean = true,
    /** Optional JSON object merged into the request body (advanced, e.g. {"top_p":0.9}). */
    val extraBody: String = "",
) {
    val displayName: String get() = name.ifBlank { model.ifBlank { "AI" } }

    val isComplete: Boolean
        get() = baseUrl.isNotBlank() && model.isNotBlank() &&
            (apiKey.isNotBlank() || isLocalUrl(baseUrl))

    val isActive: Boolean get() = enabled && isComplete

    /** The URL actually called. */
    fun endpointUrl(): String = resolveEndpoint(baseUrl, protocol)

    fun headerPairs(): List<Pair<String, String>> = extraHeaders.lines().mapNotNull { line ->
        val i = line.indexOf(':')
        if (i <= 0) null else line.substring(0, i).trim().takeIf { it.isNotEmpty() }?.let { it to line.substring(i + 1).trim() }
    }

    fun temperatureOrNull(): Double? = temperature.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull()

    companion object {
        fun resolveEndpoint(baseUrl: String, protocol: AiProtocol): String {
            val base = baseUrl.trim().trimEnd('/')
            if (base.isEmpty()) return ""
            val known = listOf("/chat/completions", "/responses", "/messages")
            if (known.any { base.endsWith(it) }) return base
            return base + protocol.endpointSuffix
        }

        fun isLocalUrl(url: String): Boolean {
            val u = url.lowercase()
            return u.contains("://localhost") || u.contains("://127.") || u.contains("://10.") ||
                u.contains("://192.168.") || Regex("://172\\.(1[6-9]|2\\d|3[01])\\.").containsMatchIn(u)
        }
    }
}

/** Ready-made starting points; the user can edit every field afterwards. */
data class AiPreset(val name: String, val protocol: AiProtocol, val baseUrl: String, val model: String)

object AiPresets {
    val all = listOf(
        AiPreset("OpenAI (Chat)", AiProtocol.OPENAI_CHAT, "https://api.openai.com/v1", "gpt-4.1-mini"),
        AiPreset("OpenAI (Responses)", AiProtocol.OPENAI_RESPONSES, "https://api.openai.com/v1", "gpt-4.1-mini"),
        AiPreset("Anthropic Claude", AiProtocol.ANTHROPIC, "https://api.anthropic.com/v1", "claude-3-5-haiku-latest"),
        AiPreset("DeepSeek", AiProtocol.OPENAI_CHAT, "https://api.deepseek.com", "deepseek-flash"),
        AiPreset("Qwen (DashScope)", AiProtocol.OPENAI_CHAT, "https://dashscope.aliyuncs.com/compatible-mode/v1", "qwen-plus"),
        AiPreset("SiliconFlow", AiProtocol.OPENAI_CHAT, "https://api.siliconflow.cn/v1", "Qwen/Qwen2.5-72B-Instruct"),
        AiPreset("Moonshot Kimi", AiProtocol.OPENAI_CHAT, "https://api.moonshot.cn/v1", "moonshot-v1-8k"),
        AiPreset("Zhipu GLM", AiProtocol.OPENAI_CHAT, "https://open.bigmodel.cn/api/paas/v4", "glm-4-flash"),
        AiPreset("OpenRouter", AiProtocol.OPENAI_CHAT, "https://openrouter.ai/api/v1", "openai/gpt-4o-mini"),
        AiPreset("Groq", AiProtocol.OPENAI_CHAT, "https://api.groq.com/openai/v1", "llama-3.1-8b-instant"),
        AiPreset("Gemini (OpenAI compatible)", AiProtocol.OPENAI_CHAT, "https://generativelanguage.googleapis.com/v1beta/openai", "gemini-2.5-flash"),
        AiPreset("Ollama (local)", AiProtocol.OPENAI_CHAT, "http://192.168.1.100:11434/v1", "qwen2.5:7b"),
    )
}

/** Every saved service, and which one translates (null = none: built-in Gemini/Groq). */
data class CustomAiProfiles(
    val profiles: List<CustomAiConfig> = emptyList(),
    val activeId: String? = null,
) {
    /** The service in use, flagged enabled; a disabled empty config when none is. */
    val active: CustomAiConfig
        get() = profiles.firstOrNull { it.id == activeId }?.copy(enabled = true) ?: CustomAiConfig()
}

/**
 * Device-local storage of the saved translation services (not cloud-synced: they hold API keys).
 * Nothing here changes while the user types — the editor keeps a draft and calls [save].
 */
class CustomAiConfigStore private constructor(private val prefs: SharedPreferences) {
    private val _profiles = MutableStateFlow(read())
    val profiles: StateFlow<CustomAiProfiles> = _profiles.asStateFlow()

    private val _config = MutableStateFlow(_profiles.value.active)
    /** The active service (enabled = true), or a disabled empty config. */
    val config: StateFlow<CustomAiConfig> = _config.asStateFlow()

    val current: CustomAiConfig get() = _config.value

    @Synchronized
    private fun commit(next: CustomAiProfiles) {
        // commit(), not apply(): the user expects "Save" to survive an immediate app kill.
        prefs.edit().putString(KEY_V2, encodeProfiles(next)).commit()
        _profiles.value = next
        _config.value = next.active
    }

    /** Adds or replaces [profile] (by id); [activate] makes it the translating service. */
    fun save(profile: CustomAiConfig, activate: Boolean = true): CustomAiConfig {
        val withId = if (profile.id.isBlank()) profile.copy(id = newId()) else profile
        val cur = _profiles.value
        val list = if (cur.profiles.any { it.id == withId.id }) cur.profiles.map { if (it.id == withId.id) withId else it }
        else cur.profiles + withId
        commit(cur.copy(profiles = list, activeId = if (activate) withId.id else cur.activeId))
        return withId
    }

    fun delete(id: String) {
        val cur = _profiles.value
        commit(cur.copy(profiles = cur.profiles.filterNot { it.id == id }, activeId = cur.activeId.takeIf { it != id }))
    }

    /** null = stop using a custom service (built-in Gemini/Groq). */
    fun activate(id: String?) {
        val cur = _profiles.value
        commit(cur.copy(activeId = id?.takeIf { i -> cur.profiles.any { it.id == i } }))
    }

    /** Legacy single-config edit (tests / old callers): edits the active service in place. */
    fun update(transform: (CustomAiConfig) -> CustomAiConfig) {
        val next = transform(current)
        if (!next.enabled) { activate(null); return }
        save(next, activate = true)
    }

    private fun read(): CustomAiProfiles {
        prefs.getString(KEY_V2, null)?.let { return decodeProfiles(it) }
        // Migrate the single config of 2.2-bilingual.1/.2.
        val legacy = decode(prefs.getString(KEY, null))
        if (legacy.baseUrl.isBlank() && legacy.model.isBlank()) return CustomAiProfiles()
        val p = legacy.copy(id = newId(), name = legacy.name.ifBlank { legacy.model })
        return CustomAiProfiles(listOf(p), if (legacy.enabled) p.id else null)
    }

    companion object {
        private const val PREFS = "arvio_bilingual_custom_ai"
        private const val KEY = "config_v1"
        private const val KEY_V2 = "profiles_v2"
        @Volatile private var instance: CustomAiConfigStore? = null

        fun from(context: Context): CustomAiConfigStore = instance ?: synchronized(this) {
            instance ?: CustomAiConfigStore(
                context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            ).also { instance = it }
        }

        fun newId(): String = java.util.UUID.randomUUID().toString().take(8)

        fun encodeJson(c: CustomAiConfig): JSONObject = JSONObject().apply {
            put("id", c.id)
            put("name", c.name)
            put("enabled", c.enabled)
            put("protocol", c.protocol.id)
            put("baseUrl", c.baseUrl)
            put("apiKey", c.apiKey)
            put("model", c.model)
            put("temperature", c.temperature)
            put("extraHeaders", c.extraHeaders)
            put("maxTokens", c.maxTokens)
            put("disableThinking", c.disableThinking)
            put("extraBody", c.extraBody)
        }

        fun encode(c: CustomAiConfig): String = encodeJson(c).toString()

        fun decodeJson(o: JSONObject): CustomAiConfig = CustomAiConfig(
            id = o.optString("id"),
            name = o.optString("name"),
            enabled = o.optBoolean("enabled", false),
            protocol = AiProtocol.of(o.optString("protocol")),
            baseUrl = o.optString("baseUrl"),
            apiKey = o.optString("apiKey"),
            model = o.optString("model"),
            temperature = o.optString("temperature", "0.2"),
            extraHeaders = o.optString("extraHeaders"),
            maxTokens = o.optInt("maxTokens", 4096).coerceIn(256, 65536),
            disableThinking = o.optBoolean("disableThinking", true),
            extraBody = o.optString("extraBody"),
        )

        fun decode(raw: String?): CustomAiConfig {
            if (raw.isNullOrBlank()) return CustomAiConfig()
            return runCatching { decodeJson(JSONObject(raw)) }.getOrDefault(CustomAiConfig())
        }

        fun encodeProfiles(p: CustomAiProfiles): String = JSONObject().apply {
            put("activeId", p.activeId ?: "")
            put("profiles", JSONArray().apply { p.profiles.forEach { put(encodeJson(it.copy(enabled = false))) } })
        }.toString()

        fun decodeProfiles(raw: String): CustomAiProfiles = runCatching {
            val o = JSONObject(raw)
            val arr = o.optJSONArray("profiles") ?: JSONArray()
            val list = (0 until arr.length()).mapNotNull { arr.optJSONObject(it)?.let(::decodeJson) }
                .map { if (it.id.isBlank()) it.copy(id = newId()) else it }
            CustomAiProfiles(list, o.optString("activeId").takeIf { id -> id.isNotBlank() && list.any { it.id == id } })
        }.getOrDefault(CustomAiProfiles())
    }
}

/** Tokens a request used, as the API reported them. */
data class AiUsage(val input: Int, val output: Int, val cachedInput: Int = 0, val reasoning: Int = 0)

/** Running token count for the current app session (shown in settings so cost is never a surprise). */
object AiUsageMeter {
    private val _total = MutableStateFlow(Totals())
    val total: StateFlow<Totals> = _total.asStateFlow()

    data class Totals(val requests: Int = 0, val input: Long = 0, val output: Long = 0, val cachedInput: Long = 0, val reasoning: Long = 0)

    @Synchronized
    fun add(u: AiUsage?) {
        val t = _total.value
        _total.value = t.copy(
            requests = t.requests + 1,
            input = t.input + (u?.input ?: 0),
            output = t.output + (u?.output ?: 0),
            cachedInput = t.cachedInput + (u?.cachedInput ?: 0),
            reasoning = t.reasoning + (u?.reasoning ?: 0),
        )
    }

    fun reset() { _total.value = Totals() }
}

/** Request bodies and response parsing for the three protocols (pure, unit-tested). */
object CustomAiWire {
    fun buildBody(c: CustomAiConfig, system: String, user: String, jsonMode: Boolean, withThinkingControl: Boolean = true): JSONObject {
        val body = when (c.protocol) {
            AiProtocol.OPENAI_CHAT -> JSONObject().apply {
                put("model", c.model)
                c.temperatureOrNull()?.let { put("temperature", it) }
                if (c.maxTokens > 0 && c.maxTokens != 4096) put("max_tokens", c.maxTokens)
                put("stream", false)
                put("messages", JSONArray().apply {
                    put(JSONObject().put("role", "system").put("content", system))
                    put(JSONObject().put("role", "user").put("content", user))
                })
            }
            AiProtocol.OPENAI_RESPONSES -> JSONObject().apply {
                put("model", c.model)
                put("instructions", system)
                put("input", user)
                c.temperatureOrNull()?.let { put("temperature", it) }
                if (c.maxTokens > 0) put("max_output_tokens", c.maxTokens)
                put("store", false)
            }
            AiProtocol.ANTHROPIC -> JSONObject().apply {
                put("model", c.model)
                put("max_tokens", if (c.maxTokens > 0) c.maxTokens else 4096)
                c.temperatureOrNull()?.let { put("temperature", it.coerceIn(0.0, 1.0)) }
                put("system", system)
                put("messages", JSONArray().apply {
                    put(JSONObject().put("role", "user").put("content", user))
                })
            }
        }
        if (c.disableThinking && withThinkingControl) thinkingOff(c).forEach { (k, v) -> body.put(k, v) }
        mergeExtraBody(body, c.extraBody)
        return body
    }

    /**
     * Provider-specific switches that turn reasoning off. Translation is mechanical: reasoning
     * only adds billed output tokens and latency. Unknown providers get nothing (an unknown field
     * can be rejected); the service retries once without these on an HTTP 400.
     */
    fun thinkingOff(c: CustomAiConfig): List<Pair<String, Any>> {
        val url = c.baseUrl.lowercase()
        val model = c.model.lowercase()
        fun openAiEffort(): String? = when {
            Regex("^o\\d").containsMatchIn(model) -> "low"
            model.startsWith("gpt-5.") -> "none"
            model.startsWith("gpt-5") -> "minimal"
            else -> null
        }
        return when (c.protocol) {
            AiProtocol.ANTHROPIC -> listOf("thinking" to JSONObject().put("type", "disabled"))
            AiProtocol.OPENAI_RESPONSES -> when {
                "deepseek" in url -> listOf("thinking" to JSONObject().put("type", "disabled"))
                else -> openAiEffort()?.let { listOf("reasoning" to JSONObject().put("effort", it)) }.orEmpty()
            }
            AiProtocol.OPENAI_CHAT -> when {
                "api.deepseek.com" in url -> listOf("thinking" to JSONObject().put("type", "disabled"))
                "bigmodel.cn" in url || "z.ai" in url -> listOf("thinking" to JSONObject().put("type", "disabled"))
                "dashscope" in url || "siliconflow" in url || "modelscope" in url -> listOf("enable_thinking" to false)
                "openrouter.ai" in url -> listOf("reasoning" to JSONObject().put("enabled", false))
                "api.openai.com" in url -> openAiEffort()?.let { listOf("reasoning_effort" to it) }.orEmpty()
                "generativelanguage.googleapis.com" in url ->
                    listOf("reasoning_effort" to if ("2.5" in model) "none" else "low")
                "groq.com" in url -> when {
                    "qwen" in model -> listOf("reasoning_effort" to "none")
                    "gpt-oss" in model -> listOf("reasoning_effort" to "low")
                    else -> emptyList()
                }
                "deepseek" in model && ("localhost" !in url) -> listOf("thinking" to JSONObject().put("type", "disabled"))
                else -> emptyList()
            }
        }
    }

    /** Merges the user's extra JSON (top-level keys) into [body]; invalid JSON is ignored. */
    fun mergeExtraBody(body: JSONObject, extra: String) {
        if (extra.isBlank()) return
        val o = runCatching { JSONObject(extra) }.getOrNull() ?: return
        o.keys().forEach { k -> body.put(k, o.get(k)) }
    }

    /** Token usage the response reports (null when it reports none). */
    fun extractUsage(protocol: AiProtocol, body: String): AiUsage? {
        val o = runCatching { JSONObject(body) }.getOrNull() ?: return null
        val u = o.optJSONObject("usage") ?: return null
        return when (protocol) {
            AiProtocol.OPENAI_CHAT -> AiUsage(
                input = u.optInt("prompt_tokens"),
                output = u.optInt("completion_tokens"),
                cachedInput = u.optJSONObject("prompt_tokens_details")?.optInt("cached_tokens")?.takeIf { it > 0 }
                    ?: u.optInt("prompt_cache_hit_tokens"),
                reasoning = u.optJSONObject("completion_tokens_details")?.optInt("reasoning_tokens") ?: 0,
            )
            AiProtocol.OPENAI_RESPONSES -> AiUsage(
                input = u.optInt("input_tokens"),
                output = u.optInt("output_tokens"),
                cachedInput = u.optJSONObject("input_tokens_details")?.optInt("cached_tokens") ?: 0,
                reasoning = u.optJSONObject("output_tokens_details")?.optInt("reasoning_tokens") ?: 0,
            )
            AiProtocol.ANTHROPIC -> AiUsage(
                input = u.optInt("input_tokens") + u.optInt("cache_read_input_tokens") + u.optInt("cache_creation_input_tokens"),
                output = u.optInt("output_tokens"),
                cachedInput = u.optInt("cache_read_input_tokens"),
            )
        }
    }

    fun headers(c: CustomAiConfig): List<Pair<String, String>> {
        val base = when (c.protocol) {
            AiProtocol.ANTHROPIC -> listOfNotNull(
                c.apiKey.takeIf { it.isNotBlank() }?.let { "x-api-key" to it },
                "anthropic-version" to "2023-06-01",
            )
            else -> listOfNotNull(c.apiKey.takeIf { it.isNotBlank() }?.let { "Authorization" to "Bearer $it" })
        }
        val extra = c.headerPairs()
        return base.filter { b -> extra.none { it.first.equals(b.first, ignoreCase = true) } } + extra
    }

    /** The model's text, or null when the response carries none. */
    fun extractText(protocol: AiProtocol, body: String): String? {
        val o = runCatching { JSONObject(body) }.getOrNull() ?: return null
        return when (protocol) {
            AiProtocol.OPENAI_CHAT -> {
                val msg = o.optJSONArray("choices")?.optJSONObject(0)?.optJSONObject("message") ?: return null
                when (val content = msg.opt("content")) {
                    is String -> content
                    is JSONArray -> buildString {
                        for (i in 0 until content.length()) {
                            val part = content.optJSONObject(i) ?: continue
                            if (part.optString("type") in setOf("text", "output_text", "")) append(part.optString("text"))
                        }
                    }
                    else -> null
                }?.let(::stripThinking)?.takeIf { it.isNotBlank() }
            }
            AiProtocol.OPENAI_RESPONSES -> {
                o.optString("output_text").takeIf { it.isNotBlank() }?.let { return stripThinking(it) }
                val out = o.optJSONArray("output") ?: return null
                buildString {
                    for (i in 0 until out.length()) {
                        val item = out.optJSONObject(i) ?: continue
                        if (item.optString("type") != "message") continue
                        val parts = item.optJSONArray("content") ?: continue
                        for (j in 0 until parts.length()) {
                            val p = parts.optJSONObject(j) ?: continue
                            if (p.optString("type") == "output_text" || p.optString("type") == "text") append(p.optString("text"))
                        }
                    }
                }.let(::stripThinking).takeIf { it.isNotBlank() }
            }
            AiProtocol.ANTHROPIC -> {
                val parts = o.optJSONArray("content") ?: return null
                buildString {
                    for (i in 0 until parts.length()) {
                        val p = parts.optJSONObject(i) ?: continue
                        if (p.optString("type") == "text") append(p.optString("text"))
                    }
                }.let(::stripThinking).takeIf { it.isNotBlank() }
            }
        }
    }

    /** A short reason from an error body (`{"error":{"message":…}}` in all three APIs). */
    fun errorMessage(body: String?): String? {
        if (body.isNullOrBlank()) return null
        val o = runCatching { JSONObject(body) }.getOrNull() ?: return body.take(160)
        val err = o.opt("error")
        return when (err) {
            is JSONObject -> err.optString("message").ifBlank { err.toString() }
            is String -> err
            else -> o.optString("message").ifBlank { body.take(160) }
        }.take(200)
    }

    /** Reasoning models served through chat endpoints sometimes inline `<think>…</think>`. */
    fun stripThinking(text: String): String =
        text.replace(Regex("(?s)<think>.*?</think>"), "").trim()
}
