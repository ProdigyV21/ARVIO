package com.arflix.tv.ui.screens.player.subtitles

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

/*
 * Anthropic Messages API transport for AI subtitles (SubtitleAiModel.CLAUDE_HAIKU).
 *
 * Claude gets the same system prompt, indexed batches and parser as Gemini and Groq; only the
 * request and reply shapes differ. Text only: the audio features that stream to the Gemini Live
 * API (hearing reference, live TV audio) stay Gemini-only, exactly as they are for Groq.
 */

private const val TAG = "ClaudeMessages"

internal const val CLAUDE_MODEL_ID = "claude-haiku-5-5"
private const val CLAUDE_URL = "https://api.anthropic.com/v1/messages"
private const val CLAUDE_API_VERSION = "2023-06-01"
// A 40-line batch in Greek is ~3–4k output tokens; leave room for long lines.
private const val CLAUDE_MAX_TOKENS = 8000
private const val CLAUDE_MAX_TRANSIENT_RETRIES = 2   // 3 attempts total, like Gemini
private const val CLAUDE_RETRY_BASE_DELAY_MS = 400L
// 529 is Anthropic's "overloaded".
private val CLAUDE_RETRYABLE_HTTP = setOf(500, 502, 503, 529)
// The call is not streamed, so nothing arrives until the whole batch is translated: the shared
// client's 30 s read timeout is too short for a slow reply and would retry (and bill) it again.
private const val CLAUDE_READ_TIMEOUT_SECONDS = 90L

/** The reply's text was cut off at max_tokens; the batch is bisected like a content block. */
internal const val CLAUDE_ERROR_MAX_TOKENS = "MAX_TOKENS"

@Volatile private var claudeClient: Pair<OkHttpClient, OkHttpClient>? = null

/** [base] with Claude's longer read timeout, built once per base client. */
private fun claudeClientFor(base: OkHttpClient): OkHttpClient {
    claudeClient?.takeIf { it.first === base }?.let { return it.second }
    val builder = base.newBuilder().readTimeout(CLAUDE_READ_TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS)
    if (base.callTimeoutMillis in 1 until (CLAUDE_READ_TIMEOUT_SECONDS + 30) * 1000) {
        builder.callTimeout(CLAUDE_READ_TIMEOUT_SECONDS + 30, java.util.concurrent.TimeUnit.SECONDS)
    }
    return builder.build().also { claudeClient = base to it }
}

/** Anthropic keys start with this; used for the key label in Settings. */
internal const val CLAUDE_KEY_PREFIX = "sk-ant-"

/** What one Messages call produced. */
internal sealed class ClaudeReply {
    data class Text(val text: String) : ClaudeReply()
    /** stop_reason "refusal": handled like Gemini's content block (the batch is bisected). */
    object Refused : ClaudeReply()
    data class Failed(val message: String) : ClaudeReply()
}

/**
 * One Messages call. No temperature (current Claude models reject a non-default value), thinking
 * off and low effort: subtitle batches need speed, not reasoning (Gemini's path likewise sets
 * thinkingLevel "minimal"). Thinking tokens would also count toward max_tokens. Transient failures (5xx/529, network) are
 * retried with jittered backoff; 429 maps to "RATE_LIMITED" like the other providers.
 */
internal suspend fun claudeMessagesCall(
    client: OkHttpClient,
    apiKey: String,
    systemPrompt: String,
    userText: String,
    transientAttempt: Int = 0
): ClaudeReply {
    val body = JSONObject().apply {
        put("model", CLAUDE_MODEL_ID)
        put("max_tokens", CLAUDE_MAX_TOKENS)
        put("system", systemPrompt)
        put("messages", JSONArray().put(JSONObject().apply {
            put("role", "user")
            put("content", userText)
        }))
        put("output_config", JSONObject().put("effort", "low"))
        put("thinking", JSONObject().put("type", "disabled"))
    }
    val request = Request.Builder()
        .url(CLAUDE_URL)
        .header("x-api-key", apiKey)
        .header("anthropic-version", CLAUDE_API_VERSION)
        .header("content-type", "application/json")
        .post(body.toString().toRequestBody("application/json".toMediaType()))
        .build()

    val reply: ClaudeReply? = withContext(Dispatchers.IO) {
        try {
            claudeClientFor(client).newCall(request).execute().use { response ->
                val text = response.body?.string().orEmpty()
                when {
                    response.isSuccessful -> parseClaudeReply(text)
                    response.code in CLAUDE_RETRYABLE_HTTP &&
                        transientAttempt < CLAUDE_MAX_TRANSIENT_RETRIES -> null
                    response.code == 429 -> ClaudeReply.Failed("RATE_LIMITED")
                    else -> ClaudeReply.Failed("HTTP ${response.code}: ${text.take(300)}")
                }
            }
        } catch (e: java.io.IOException) {
            if (transientAttempt < CLAUDE_MAX_TRANSIENT_RETRIES) null
            else ClaudeReply.Failed(e.message ?: "Network error")
        }
    }
    if (reply != null) return reply

    Log.w(TAG, "Claude transient failure — retry ${transientAttempt + 1}/$CLAUDE_MAX_TRANSIENT_RETRIES")
    val jitter = 0.7 + Math.random() * 0.6
    delay((CLAUDE_RETRY_BASE_DELAY_MS * (1L shl transientAttempt) * jitter).toLong())
    return claudeMessagesCall(client, apiKey, systemPrompt, userText, transientAttempt + 1)
}

internal fun parseClaudeReply(body: String): ClaudeReply {
    val json = runCatching { JSONObject(body) }.getOrNull()
        ?: return ClaudeReply.Failed("Unreadable Claude response")
    if (json.optString("stop_reason") == "refusal") return ClaudeReply.Refused
    val content = json.optJSONArray("content")
        ?: return ClaudeReply.Failed("No content in Claude response")
    val text = buildString {
        for (i in 0 until content.length()) {
            val block = content.optJSONObject(i) ?: continue
            if (block.optString("type") == "text") append(block.optString("text"))
        }
    }.trim()
    if (json.optString("stop_reason") == "max_tokens") {
        // A cut-off batch won't parse, and sending it again unchanged hits the same limit.
        Log.w(TAG, "Claude reply hit max_tokens")
        return ClaudeReply.Failed(CLAUDE_ERROR_MAX_TOKENS)
    }
    return if (text.isBlank()) ClaudeReply.Failed("Empty Claude response") else ClaudeReply.Text(text)
}
