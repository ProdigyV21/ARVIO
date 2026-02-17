package com.arflix.tv.data.repository

import com.arflix.tv.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

data class TvDeviceAuthSession(
    val userCode: String,
    val deviceCode: String,
    val verificationUrl: String,
    val expiresInSeconds: Int,
    val intervalSeconds: Int
)

enum class TvDeviceAuthStatusType {
    PENDING,
    APPROVED,
    EXPIRED,
    ERROR
}

data class TvDeviceAuthStatus(
    val status: TvDeviceAuthStatusType,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val email: String? = null,
    val message: String? = null
)

data class TvDeviceAuthCompleteResult(
    val ok: Boolean,
    val message: String? = null
)

@Singleton
class TvDeviceAuthRepository @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

    suspend fun startSession(): Result<TvDeviceAuthSession> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val request = Request.Builder()
                    .url(Constants.TV_AUTH_START_URL)
                    .header("apikey", Constants.SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.SUPABASE_ANON_KEY}")
                    .post("{}".toRequestBody(jsonMediaType))
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    val body = response.body?.string().orEmpty()
                    if (!response.isSuccessful) {
                        throw IllegalStateException(parseError(body, "Failed to start TV auth"))
                    }
                    val json = JSONObject(body)
                    TvDeviceAuthSession(
                        userCode = json.getString("user_code"),
                        deviceCode = json.getString("device_code"),
                        verificationUrl = json.getString("verification_url"),
                        expiresInSeconds = json.optInt("expires_in", 600),
                        intervalSeconds = json.optInt("interval", 3)
                    )
                }
            }
        }
    }

    suspend fun pollStatus(deviceCode: String): Result<TvDeviceAuthStatus> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val payload = JSONObject().put("device_code", deviceCode).toString()
                val request = Request.Builder()
                    .url(Constants.TV_AUTH_STATUS_URL)
                    .header("apikey", Constants.SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.SUPABASE_ANON_KEY}")
                    .post(payload.toRequestBody(jsonMediaType))
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    val body = response.body?.string().orEmpty()
                    if (!response.isSuccessful) {
                        throw IllegalStateException(parseError(body, "Failed to poll TV auth status"))
                    }
                    val json = JSONObject(body)
                    val status = when (json.optString("status").lowercase()) {
                        "pending" -> TvDeviceAuthStatusType.PENDING
                        "approved" -> TvDeviceAuthStatusType.APPROVED
                        "expired" -> TvDeviceAuthStatusType.EXPIRED
                        else -> TvDeviceAuthStatusType.ERROR
                    }
                    TvDeviceAuthStatus(
                        status = status,
                        accessToken = json.optString("access_token").ifBlank { null },
                        refreshToken = json.optString("refresh_token").ifBlank { null },
                        email = json.optString("email").ifBlank { null },
                        message = json.optString("message").ifBlank { null }
                    )
                }
            }
        }
    }

    suspend fun completeWithEmailPassword(
        userCode: String,
        email: String,
        password: String,
        intent: String
    ): Result<TvDeviceAuthCompleteResult> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val payload = JSONObject()
                    .put("code", userCode)
                    .put("email", email.trim().lowercase())
                    .put("password", password)
                    .put("intent", intent)
                    .toString()

                val request = Request.Builder()
                    .url(Constants.TV_AUTH_COMPLETE_URL)
                    .header("apikey", Constants.SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.SUPABASE_ANON_KEY}")
                    .post(payload.toRequestBody(jsonMediaType))
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    val body = response.body?.string().orEmpty()
                    if (!response.isSuccessful) {
                        throw IllegalStateException(parseError(body, "Failed to link TV"))
                    }
                    TvDeviceAuthCompleteResult(ok = true)
                }
            }
        }
    }

    private fun parseError(body: String, fallback: String): String {
        return runCatching {
            val json = JSONObject(body)
            json.optString("error").ifBlank {
                json.optString("message").ifBlank {
                    json.optString("error_description").ifBlank { fallback }
                }
            }
        }.getOrDefault(fallback)
    }
}
