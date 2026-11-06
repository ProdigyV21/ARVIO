package com.arflix.tv.ui.screens.tv.live.aisubs

import com.arflix.tv.ui.screens.player.subtitles.BLOCKED_FINISH_REASONS
import com.arflix.tv.ui.screens.player.subtitles.GEMINI_MAX_TRANSIENT_RETRIES
import com.arflix.tv.ui.screens.player.subtitles.GEMINI_RETRYABLE_HTTP
import com.arflix.tv.ui.screens.player.subtitles.GEMINI_RETRY_BASE_DELAY_MS
import com.arflix.tv.ui.screens.player.subtitles.extractJsonArray
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.TimeUnit

/** One stretch of speech cut from the channel's audio, on the media timeline. */
internal class SpeechSegment(
    val startUs: Long,
    val endUs: Long,
    /** 16 kHz mono samples in [-1, 1]. */
    val pcm16k: FloatArray,
)

/** What an engine made of one segment. */
internal sealed interface LiveEngineResult {
    data class Cues(val cues: List<LiveCue>, val sourceLanguage: String?) : LiveEngineResult

    /** Nothing more will work until the user fixes it (wrong key). */
    data object InvalidKey : LiveEngineResult

    /** Quota or rate limit (HTTP 429): pause and back off. */
    data object QuotaExceeded : LiveEngineResult

    /** No connection. */
    data object Offline : LiveEngineResult

    /** This segment only (content policy, malformed reply, server error). */
    data class Failed(val reason: String) : LiveEngineResult
}

/** Turns speech segments into timed subtitle lines in the viewer's language. */
internal interface LiveSubtitleEngine {
    /**
     * [context] holds the last few lines already shown (in the target language), so names,
     * gender and register stay consistent across segments.
     */
    suspend fun process(segment: SpeechSegment, context: List<String>, targetLanguageName: String, rtl: Boolean): LiveEngineResult
}

/**
 * Gemini `generateContent` with the segment as inline WAV audio: transcribes and translates in
 * one call, and returns lines that are timed from the segment's own media time. Plain HTTP per
 * segment, so there is no long-lived session to expire over hours of TV.
 *
 * Follows the app's Gemini rules (see SubtitleTranslationService): JSON response mode, minimal
 * thinking, BLOCK_NONE safety settings, short retries on 500/502/503 and network errors, never on
 * 429. The key goes in a header, never in a URL or the log.
 */
internal class GeminiSegmentEngine(
    private val apiKey: () -> String,
    private val client: OkHttpClient = DEFAULT_CLIENT,
    private val baseUrl: String = GEMINI_LIVE_URL,
) : LiveSubtitleEngine {

    override suspend fun process(
        segment: SpeechSegment,
        context: List<String>,
        targetLanguageName: String,
        rtl: Boolean,
    ): LiveEngineResult = withContext(Dispatchers.IO) {
        val key = apiKey()
        if (key.isBlank()) return@withContext LiveEngineResult.InvalidKey
        val body = GeminiLiveProtocol.requestBody(segment, context, targetLanguageName)
        var transient = 0
        while (true) {
            val request = Request.Builder()
                .url(baseUrl)
                .header("x-goog-api-key", key)
                .post(body.toRequestBody(JSON_MEDIA_TYPE))
                .build()
            try {
                client.newCall(request).execute().use { response ->
                    val text = response.body?.string().orEmpty()
                    when {
                        response.isSuccessful ->
                            return@withContext GeminiLiveProtocol.parseResponse(text, segment, rtl)
                        response.code == 429 -> return@withContext LiveEngineResult.QuotaExceeded
                        GeminiLiveProtocol.isInvalidKey(response.code, text) -> return@withContext LiveEngineResult.InvalidKey
                        response.code in GEMINI_RETRYABLE_HTTP && transient < GEMINI_MAX_TRANSIENT_RETRIES -> Unit
                        else -> return@withContext LiveEngineResult.Failed("HTTP ${response.code}")
                    }
                }
            } catch (error: CancellationException) {
                throw error
            } catch (error: IOException) {
                if (transient >= GEMINI_MAX_TRANSIENT_RETRIES) {
                    return@withContext if (error is java.net.UnknownHostException || error is java.net.ConnectException) {
                        LiveEngineResult.Offline
                    } else {
                        LiveEngineResult.Failed("network: ${error.javaClass.simpleName}")
                    }
                }
            }
            // ±30% jittered exponential backoff, as in SubtitleTranslationService.
            val jitter = 0.7 + Math.random() * 0.6
            delay((GEMINI_RETRY_BASE_DELAY_MS * (1L shl transient) * jitter).toLong())
            transient++
        }
        @Suppress("UNREACHABLE_CODE")
        LiveEngineResult.Failed("unreachable")
    }

    /** A cheap call that tells whether [key] is accepted: true, false, or null when it could not be checked. */
    suspend fun validateKey(key: String): Boolean? = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url(GEMINI_MODEL_INFO_URL)
            .header("x-goog-api-key", key.trim())
            .get()
            .build()
        try {
            client.newCall(request).execute().use { response ->
                when {
                    response.isSuccessful -> true
                    GeminiLiveProtocol.isInvalidKey(response.code, response.body?.string().orEmpty()) -> false
                    else -> null
                }
            }
        } catch (error: CancellationException) {
            throw error
        } catch (_: IOException) {
            null
        }
    }

    companion object {
        /**
         * Same model as the app's subtitle translation: fast and cheap enough for continuous use,
         * and it takes audio input.
         */
        const val MODEL_ID = "gemini-3.5-flash-lite"
        const val GEMINI_LIVE_URL = "https://generativelanguage.googleapis.com/v1beta/models/$MODEL_ID:generateContent"
        private const val GEMINI_MODEL_INFO_URL = "https://generativelanguage.googleapis.com/v1beta/models/$MODEL_ID"
        private val JSON_MEDIA_TYPE = "application/json".toMediaType()
        private val DEFAULT_CLIENT: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                // A segment's answer is useless once its moment has passed.
                .readTimeout(12, TimeUnit.SECONDS)
                .callTimeout(15, TimeUnit.SECONDS)
                .build()
        }
    }
}

/** The request and response format for [GeminiSegmentEngine]; pure, so it is unit tested. */
internal object GeminiLiveProtocol {

    fun systemPrompt(targetLanguageName: String): String = """
        You write live TV subtitles. The audio is a few seconds of a TV broadcast.
        Transcribe what is said and translate it into $targetLanguageName. If the speech is
        already in $targetLanguageName, return the transcription.
        Write natural, idiomatic $targetLanguageName as a professional subtitler would: short
        lines, correct grammatical gender, keep names as they are spoken.
        Split the text into subtitle lines of at most about 42 characters, in spoken order.
        For each line give startOffsetMs, the time from the start of the audio when it begins.
        If there is no intelligible speech (music, noise), return an empty "lines" array.
        Never describe sounds, never add notes or explanations.
        Reply with JSON only:
        {"lines":[{"text":"...","startOffsetMs":0}],"sourceLanguage":"en"}
    """.trimIndent()

    fun requestBody(segment: SpeechSegment, context: List<String>, targetLanguageName: String): String {
        val wav = android.util.Base64.encodeToString(wav16kMono(segment.pcm16k), android.util.Base64.NO_WRAP)
        val userText = buildString {
            if (context.isNotEmpty()) {
                append("Previous subtitles, for context only (do not repeat them):\n")
                context.forEach { append("- ").append(it).append('\n') }
            }
            append("Subtitle this audio in ").append(targetLanguageName).append('.')
        }
        return JSONObject().apply {
            put("system_instruction", JSONObject().put("parts", JSONArray().put(JSONObject().put("text", systemPrompt(targetLanguageName)))))
            put(
                "contents",
                JSONArray().put(
                    JSONObject().put(
                        "parts",
                        JSONArray()
                            .put(JSONObject().put("inline_data", JSONObject().put("mime_type", "audio/wav").put("data", wav)))
                            .put(JSONObject().put("text", userText)),
                    ),
                ),
            )
            put(
                "generationConfig",
                JSONObject()
                    .put("temperature", 0.2)
                    .put("responseMimeType", "application/json")
                    .put("thinkingConfig", JSONObject().put("thinkingLevel", "minimal")),
            )
            put(
                "safetySettings",
                JSONArray().apply {
                    listOf(
                        "HARM_CATEGORY_HARASSMENT",
                        "HARM_CATEGORY_HATE_SPEECH",
                        "HARM_CATEGORY_SEXUALLY_EXPLICIT",
                        "HARM_CATEGORY_DANGEROUS_CONTENT",
                    ).forEach { put(JSONObject().put("category", it).put("threshold", "BLOCK_NONE")) }
                },
            )
        }.toString()
    }

    /** A Gemini `generateContent` HTTP 200 body → cues on the segment's media time. */
    fun parseResponse(body: String, segment: SpeechSegment, rtl: Boolean): LiveEngineResult {
        val json = runCatching { JSONObject(body) }.getOrNull() ?: return LiveEngineResult.Failed("unreadable reply")
        val candidate = json.optJSONArray("candidates")?.optJSONObject(0)
            ?: return LiveEngineResult.Failed(
                if (json.optJSONObject("promptFeedback")?.optString("blockReason").isNullOrBlank()) "no candidates" else "blocked",
            )
        val parts = candidate.optJSONObject("content")?.optJSONArray("parts")
        val text = buildString {
            for (i in 0 until (parts?.length() ?: 0)) {
                val part = parts!!.optJSONObject(i) ?: continue
                if (part.optBoolean("thought", false)) continue
                append(part.optString("text"))
            }
        }.trim()
        if (text.isBlank()) {
            return LiveEngineResult.Failed(
                if (candidate.optString("finishReason") in BLOCKED_FINISH_REASONS) "blocked" else "empty reply",
            )
        }
        return parseLines(text, segment, rtl)
    }

    /**
     * The model's JSON text → cues. Accepts the documented object, a bare array of lines or strings
     * (JSON mode sometimes drops the wrapper), and fenced or truncated output via the shared
     * [extractJsonArray].
     */
    fun parseLines(text: String, segment: SpeechSegment, rtl: Boolean): LiveEngineResult {
        val obj = runCatching { JSONObject(text.trim()) }.getOrNull()
        val array: JSONArray = obj?.optJSONArray("lines")
            ?: extractJsonArray(text)
            ?: return LiveEngineResult.Failed("malformed reply")
        val language = obj?.optString("sourceLanguage")?.takeIf { it.isNotBlank() }
        val raw = ArrayList<Pair<String, Long?>>()
        for (i in 0 until array.length()) {
            val item = array.opt(i)
            when (item) {
                is JSONObject -> {
                    val line = cleanLine(item.optString("text"))
                    val offset = if (item.has("startOffsetMs")) item.optLong("startOffsetMs", -1L).takeIf { it >= 0 } else null
                    if (line.isNotEmpty()) raw += line to offset
                }
                is String -> cleanLine(item).takeIf { it.isNotEmpty() }?.let { raw += it to null }
            }
        }
        return LiveEngineResult.Cues(timeLines(raw, segment, rtl), language)
    }

    /**
     * Lines with offsets start there (kept in order, inside the segment); the rest share the
     * segment by text length. Each line ends where the next begins; the last at the segment end.
     */
    fun timeLines(lines: List<Pair<String, Long?>>, segment: SpeechSegment, rtl: Boolean): List<LiveCue> {
        if (lines.isEmpty()) return emptyList()
        val duration = (segment.endUs - segment.startUs).coerceAtLeast(1L)
        val totalChars = lines.sumOf { it.first.length }.coerceAtLeast(1)
        var proportional = 0L
        var previousStart = segment.startUs
        val starts = lines.map { (text, offsetMs) ->
            val byLength = segment.startUs + duration * proportional / totalChars
            proportional += text.length
            val start = offsetMs?.let { segment.startUs + it * 1_000L } ?: byLength
            start.coerceIn(previousStart, segment.endUs - 1).also { previousStart = it }
        }
        return lines.mapIndexed { index, (text, _) ->
            LiveCue(
                startUs = starts[index],
                endUs = starts.getOrNull(index + 1)?.takeIf { it > starts[index] } ?: segment.endUs,
                text = text,
                rtl = rtl,
            )
        }
    }

    fun isInvalidKey(code: Int, body: String): Boolean =
        code == 401 || code == 403 ||
            (code == 400 && (body.contains("API_KEY_INVALID") || body.contains("API key not valid")))

    private fun cleanLine(text: String): String = text.replace('\n', ' ').replace(Regex("\\s+"), " ").trim()

    /** 16-bit little-endian mono WAV at 16 kHz. */
    fun wav16kMono(samples: FloatArray): ByteArray {
        val dataBytes = samples.size * 2
        val buffer = ByteBuffer.allocate(44 + dataBytes).order(ByteOrder.LITTLE_ENDIAN)
        buffer.put("RIFF".toByteArray(Charsets.US_ASCII)).putInt(36 + dataBytes)
        buffer.put("WAVE".toByteArray(Charsets.US_ASCII))
        buffer.put("fmt ".toByteArray(Charsets.US_ASCII)).putInt(16)
            .putShort(1).putShort(1).putInt(16_000).putInt(32_000).putShort(2).putShort(16)
        buffer.put("data".toByteArray(Charsets.US_ASCII)).putInt(dataBytes)
        for (sample in samples) {
            buffer.putShort((sample.coerceIn(-1f, 1f) * Short.MAX_VALUE).toInt().toShort())
        }
        return buffer.array()
    }
}
