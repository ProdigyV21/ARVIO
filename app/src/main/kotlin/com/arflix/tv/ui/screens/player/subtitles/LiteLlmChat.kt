package com.arflix.tv.ui.screens.player.subtitles

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

/*
 * LiteLLM proxy transport for AI subtitles (SubtitleAiModel.LITELLM).
 *
 * A LiteLLM proxy (https://docs.litellm.ai/docs/simple_proxy) serves one OpenAI-style
 * /v1/chat/completions endpoint in front of whatever the user runs: OpenAI, Gemini, Bedrock,
 * Vertex, Azure, Mistral, a local Ollama... The model is whatever name the proxy lists in
 * /v1/models. Same system prompt, indexed batches and parser as the other providers; text only,
 * so the audio features that stream to the Gemini Live API stay Gemini-only, as for Groq and Claude.
 */

private const val TAG = "LiteLlmChat"

private const val LITELLM_TEMPERATURE = 0.1   // same as Groq
private const val LITELLM_MAX_TRANSIENT_RETRIES = 2   // 3 attempts total, like Gemini and Claude
private const val LITELLM_RETRY_BASE_DELAY_MS = 400L
private val LITELLM_RETRYABLE_HTTP = setOf(500, 502, 503, 504)
// Not streamed: the whole batch has to arrive within the read timeout, and the proxy may sit in
// front of a slow or local model. Same reasoning as CLAUDE_READ_TIMEOUT_SECONDS.
private const val LITELLM_READ_TIMEOUT_SECONDS = 90L

/** Where the proxy is and which of its models to use. Both are needed; the key is the shared AI key. */
data class LiteLlmConfig(val baseUrl: String = "", val model: String = "") {
    val isConfigured: Boolean get() = normalizeLiteLlmBaseUrl(baseUrl).isNotEmpty() && model.isNotBlank()
}

/**
 * "http://host:4000", "http://host:4000/" and "http://host:4000/v1" all mean the same proxy; a
 * bare host gets http://, since a proxy on the local network usually has no TLS.
 */
internal fun normalizeLiteLlmBaseUrl(url: String): String {
    var u = url.trim().trimEnd('/')
    if (u.isEmpty()) return ""
    if (u.endsWith("/v1")) u = u.removeSuffix("/v1").trimEnd('/')
    if (!u.startsWith("http://") && !u.startsWith("https://")) u = "http://$u"
    return u
}

/** What one chat completions call produced. */
internal sealed class LiteLlmReply {
    data class Text(val text: String) : LiteLlmReply()
    /** finish_reason "content_filter": handled like Gemini's content block (the batch is bisected). */
    object Refused : LiteLlmReply()
    data class Failed(val message: String) : LiteLlmReply()
}

@Volatile private var liteLlmClient: Pair<OkHttpClient, OkHttpClient>? = null

/** [base] with the longer read timeout, built once per base client. */
private fun liteLlmClientFor(base: OkHttpClient): OkHttpClient {
    liteLlmClient?.takeIf { it.first === base }?.let { return it.second }
    val builder = base.newBuilder().readTimeout(LITELLM_READ_TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS)
    if (base.callTimeoutMillis in 1 until (LITELLM_READ_TIMEOUT_SECONDS + 30) * 1000) {
        builder.callTimeout(LITELLM_READ_TIMEOUT_SECONDS + 30, java.util.concurrent.TimeUnit.SECONDS)
    }
    return builder.build().also { liteLlmClient = base to it }
}

internal fun buildLiteLlmRequestBody(model: String, systemPrompt: String, userText: String): JSONObject =
    JSONObject().apply {
        put("model", model.trim())
        put("temperature", LITELLM_TEMPERATURE)
        put("messages", JSONArray()
            .put(JSONObject().put("role", "system").put("content", systemPrompt))
            .put(JSONObject().put("role", "user").put("content", userText)))
        // The proxy drops parameters the routed model doesn't accept (some reject a non-default
        // temperature) instead of failing the batch.
        put("drop_params", true)
    }

/**
 * One chat completions call to the proxy. Transient failures (5xx, network) are retried with
 * jittered backoff; 429 maps to "RATE_LIMITED" like the other providers. A reply cut off by the
 * model's output limit fails as [CLAUDE_ERROR_MAX_TOKENS], so the batch is bisected as for Claude.
 */
internal suspend fun liteLlmChatCall(
    client: OkHttpClient,
    config: LiteLlmConfig,
    apiKey: String,
    systemPrompt: String,
    userText: String,
    transientAttempt: Int = 0
): LiteLlmReply {
    if (!config.isConfigured) return LiteLlmReply.Failed("LiteLLM proxy URL or model not set")
    val url = "${normalizeLiteLlmBaseUrl(config.baseUrl)}/v1/chat/completions".toHttpUrlOrNull()
        ?: return LiteLlmReply.Failed("Invalid LiteLLM proxy URL")
    val body = buildLiteLlmRequestBody(config.model, systemPrompt, userText)
    val request = Request.Builder()
        .url(url)
        .header("Authorization", "Bearer ${apiKey.trim()}")
        .header("content-type", "application/json")
        .post(body.toString().toRequestBody("application/json".toMediaType()))
        .build()

    val reply: LiteLlmReply? = withContext(Dispatchers.IO) {
        try {
            liteLlmClientFor(client).newCall(request).execute().use { response ->
                val text = response.body?.string().orEmpty()
                when {
                    response.isSuccessful -> parseLiteLlmReply(text)
                    response.code in LITELLM_RETRYABLE_HTTP &&
                        transientAttempt < LITELLM_MAX_TRANSIENT_RETRIES -> null
                    response.code == 429 -> LiteLlmReply.Failed("RATE_LIMITED")
                    else -> LiteLlmReply.Failed("HTTP ${response.code}: ${liteLlmErrorMessage(text)}")
                }
            }
        } catch (e: java.io.IOException) {
            if (transientAttempt < LITELLM_MAX_TRANSIENT_RETRIES) null
            else LiteLlmReply.Failed(e.message ?: "Network error")
        }
    }
    if (reply != null) return reply

    Log.w(TAG, "LiteLLM transient failure — retry ${transientAttempt + 1}/$LITELLM_MAX_TRANSIENT_RETRIES")
    val jitter = 0.7 + Math.random() * 0.6
    delay((LITELLM_RETRY_BASE_DELAY_MS * (1L shl transientAttempt) * jitter).toLong())
    return liteLlmChatCall(client, config, apiKey, systemPrompt, userText, transientAttempt + 1)
}

internal fun parseLiteLlmReply(body: String): LiteLlmReply {
    val json = runCatching { JSONObject(body) }.getOrNull()
        ?: return LiteLlmReply.Failed("Unreadable LiteLLM response")
    val choice = json.optJSONArray("choices")?.optJSONObject(0)
        ?: return LiteLlmReply.Failed("No choices in LiteLLM response")
    val finishReason = choice.optString("finish_reason")
    if (finishReason == "content_filter") return LiteLlmReply.Refused
    if (finishReason == "length") {
        // A cut-off batch won't parse, and sending it again unchanged hits the same limit.
        Log.w(TAG, "LiteLLM reply hit the output limit")
        return LiteLlmReply.Failed(CLAUDE_ERROR_MAX_TOKENS)
    }
    val message = choice.optJSONObject("message")
    val text = when (val content = message?.opt("content")) {
        is String -> content
        // Some providers come back as a list of content parts.
        is JSONArray -> buildString {
            for (i in 0 until content.length()) {
                val part = content.optJSONObject(i) ?: continue
                if (part.optString("type") == "text") append(part.optString("text"))
            }
        }
        else -> ""
    }.trim()
    if (text.isBlank() && message?.optString("refusal").orEmpty().isNotBlank()) return LiteLlmReply.Refused
    return if (text.isBlank()) LiteLlmReply.Failed("Empty LiteLLM response") else LiteLlmReply.Text(text)
}

/** The proxy's {"error": {"message": ...}}, or the raw body when it isn't that shape. */
internal fun liteLlmErrorMessage(body: String): String {
    val message = runCatching { JSONObject(body).optJSONObject("error")?.optString("message") }.getOrNull()
    return (message?.takeIf { it.isNotBlank() } ?: body).take(300)
}

/** The model names the proxy serves (GET /v1/models), sorted, for the model picker in Settings. */
internal suspend fun fetchLiteLlmModels(client: OkHttpClient, baseUrl: String, apiKey: String): Result<List<String>> =
    withContext(Dispatchers.IO) {
        runCatching {
            val base = normalizeLiteLlmBaseUrl(baseUrl)
            require(base.isNotEmpty()) { "LiteLLM proxy URL not set" }
            val url = "$base/v1/models".toHttpUrlOrNull() ?: error("Invalid LiteLLM proxy URL")
            val request = Request.Builder()
                .url(url)
                .apply { if (apiKey.isNotBlank()) header("Authorization", "Bearer ${apiKey.trim()}") }
                .get()
                .build()
            client.newCall(request).execute().use { response ->
                val text = response.body?.string().orEmpty()
                if (!response.isSuccessful) error("HTTP ${response.code}: ${liteLlmErrorMessage(text)}")
                parseLiteLlmModels(text)
            }
        }
    }

internal fun parseLiteLlmModels(body: String): List<String> {
    val data = JSONObject(body).optJSONArray("data") ?: return emptyList()
    return (0 until data.length())
        .mapNotNull { data.optJSONObject(it)?.optString("id")?.takeIf(String::isNotBlank) }
        .distinct()
        .sorted()
}
