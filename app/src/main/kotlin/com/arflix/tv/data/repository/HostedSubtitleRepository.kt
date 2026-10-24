package com.arflix.tv.data.repository

import android.util.Log
import com.arflix.tv.ui.screens.player.subtitles.TranslationResult
import com.arflix.tv.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Hosted AI subtitles, included in ARVIO Premium.
 *
 * Premium members translate subtitles into their own language without a Groq or Gemini key:
 * the ARVIO backend (`ai-subtitles-translate`) translates with ARVIO's own provider keys and
 * caches popular lines. Access follows the account's Premium record (`entitlement-status`),
 * so a member who subscribed with their ARVIO email — or linked their Ko-fi email — unlocks it
 * on every device they sign in on. Only plain subtitle text is sent.
 */
@Singleton
class HostedSubtitleRepository @Inject constructor(
    private val authRepository: AuthRepository
) {
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    @Volatile private var premium: Boolean? = null
    @Volatile private var checkedAtMs = 0L
    /** Set when the backend refused hosted translation for good (Premium ended, monthly limit). */
    @Volatile var lastRefusal: String? = null
        private set

    /** Hosted subtitles need ARVIO Cloud (the auth backend); builds without it never offer them. */
    val isSupported: Boolean get() = Constants.USE_NETLIFY_CLOUD_SYNC

    /** The last known answer, without a network request. */
    fun cachedPremium(): Boolean = premium == true

    /**
     * True when the signed-in account has an active Premium membership. Answers from a
     * 30-minute cache; a failed check keeps the last known answer rather than switching
     * a paying member off because of a network blip.
     */
    suspend fun isPremium(forceRefresh: Boolean = false): Boolean {
        if (!isSupported) return false
        val fresh = System.currentTimeMillis() - checkedAtMs < CACHE_MS
        if (!forceRefresh && fresh && premium != null) return premium == true
        val result = withTimeoutOrNull(CHECK_TIMEOUT_MS) { fetchPremium() }
        if (result != null) {
            premium = result
            checkedAtMs = System.currentTimeMillis()
            if (result) lastRefusal = null
        }
        return premium == true
    }

    private suspend fun fetchPremium(): Boolean? = withContext(Dispatchers.IO) {
        val token = authRepository.getAccessToken()?.takeIf { it.isNotBlank() } ?: return@withContext false
        val request = Request.Builder()
            .url("${Constants.NETLIFY_BACKEND_URL}/entitlement-status")
            .header("Authorization", "Bearer $token")
            .get()
            .build()
        runCatching {
            client.newCall(request).execute().use { response ->
                when {
                    response.isSuccessful -> JSONObject(response.body?.string().orEmpty()).optBoolean("entitled", false)
                    response.code == 401 || response.code == 403 -> false
                    else -> null
                }
            }
        }.onFailure { Log.w(TAG, "Premium check failed: ${it.message}") }.getOrNull()
    }

    /**
     * Translates [lines] into [targetLanguage] (an English language name, e.g. "Dutch").
     * After a final refusal (Premium ended, monthly limit) no further requests are sent this
     * session; the player keeps showing the original subtitles.
     */
    suspend fun translate(lines: List<String>, targetLanguage: String): TranslationResult = withContext(Dispatchers.IO) {
        if (lines.isEmpty()) return@withContext TranslationResult(lines, true)
        lastRefusal?.let { return@withContext TranslationResult(lines, false, it) }
        val token = authRepository.getAccessToken()?.takeIf { it.isNotBlank() }
            ?: return@withContext TranslationResult(lines, false, "Not signed in")
        val translated = lines.toMutableList()
        // Lines longer than the service accepts stay in their original language.
        val indices = lines.indices.filter { lines[it].isNotBlank() && lines[it].length <= MAX_LINE_CHARS }
        for (chunk in chunkIndices(indices, lines)) {
            val result = postBatch(token, chunk.map { lines[it] }, targetLanguage)
            if (!result.success) return@withContext TranslationResult(lines, false, result.errorMessage)
            chunk.forEachIndexed { i, index -> translated[index] = result.lines[i] }
        }
        TranslationResult(translated, true)
    }

    /** Splits into requests of at most 60 lines and 11,000 characters (the service's limits). */
    @androidx.annotation.VisibleForTesting
    internal fun chunkIndices(indices: List<Int>, lines: List<String>): List<List<Int>> {
        val chunks = mutableListOf<MutableList<Int>>()
        var chars = 0
        for (index in indices) {
            val current = chunks.lastOrNull()
            if (current == null || current.size >= MAX_LINES_PER_REQUEST || chars + lines[index].length > MAX_BATCH_CHARS) {
                chunks += mutableListOf(index)
                chars = lines[index].length
            } else {
                current += index
                chars += lines[index].length
            }
        }
        return chunks
    }

    private fun postBatch(token: String, chunk: List<String>, targetLanguage: String): TranslationResult {
        val body = JSONObject()
            .put("lines", JSONArray(chunk))
            .put("targetLanguage", targetLanguage)
            .toString()
            .toRequestBody(JSON)
        val request = Request.Builder()
            .url("${Constants.NETLIFY_BACKEND_URL}/ai-subtitles-translate")
            .header("Authorization", "Bearer $token")
            .post(body)
            .build()
        return runCatching {
            client.newCall(request).execute().use { response ->
                val payload = runCatching { JSONObject(response.body?.string().orEmpty()) }.getOrNull()
                if (!response.isSuccessful) {
                    val error = payload?.optString("error").orEmpty().ifBlank { "HTTP ${response.code}" }
                    if (response.code == 403) {
                        premium = false
                        checkedAtMs = System.currentTimeMillis()
                    }
                    if (error == ERROR_PREMIUM_REQUIRED || error == ERROR_MONTHLY_LIMIT) lastRefusal = error
                    return@use TranslationResult(chunk, false, if (error == "rate_limited") "RATE_LIMITED" else error)
                }
                val array = payload?.optJSONArray("translations")
                if (array == null || array.length() != chunk.size) {
                    return@use TranslationResult(chunk, false, "Unexpected response")
                }
                TranslationResult(List(array.length()) { array.optString(it, chunk[it]) }, true)
            }
        }.getOrElse { TranslationResult(chunk, false, it.message ?: "Network error") }
    }

    companion object {
        const val ERROR_PREMIUM_REQUIRED = "premium_required"
        const val ERROR_MONTHLY_LIMIT = "monthly_limit_reached"
        private const val TAG = "HostedSubtitles"
        private const val CACHE_MS = 30 * 60 * 1000L
        private const val CHECK_TIMEOUT_MS = 2_500L
        private const val MAX_LINES_PER_REQUEST = 60
        private const val MAX_LINE_CHARS = 400
        private const val MAX_BATCH_CHARS = 11_000
        private val JSON = "application/json; charset=utf-8".toMediaType()
    }
}
