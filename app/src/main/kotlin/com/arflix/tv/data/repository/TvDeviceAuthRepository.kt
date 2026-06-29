package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.R
import com.arflix.tv.util.Constants
import com.arflix.tv.util.AuthEmailValidator
import dagger.hilt.android.qualifiers.ApplicationContext
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
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient
) {
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

    suspend fun startSession(): Result<TvDeviceAuthSession> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val request = Request.Builder()
                    .url(Constants.TV_AUTH_START_URL)
                    .header("apikey", Constants.APP_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
                    .post("{}".toRequestBody(jsonMediaType))
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    val body = response.body?.string().orEmpty()
                    if (!response.isSuccessful) {
                        throw IllegalStateException(parseError(body, context.getString(R.string.tv_link_failed_start)))
                    }
                    val json = JSONObject(body)
                    val userCode = json.getString("user_code")
                    val verificationUrl = json.optString("verification_url")
                        .ifBlank { json.optString("verification_uri") }
                        .ifBlank { "https://auth.arvio.tv/?code=${java.net.URLEncoder.encode(userCode, "UTF-8")}" }
                    TvDeviceAuthSession(
                        userCode = userCode,
                        deviceCode = json.getString("device_code"),
                        verificationUrl = verificationUrl,
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
                val statusRequest = Request.Builder()
                    .url(Constants.TV_AUTH_STATUS_URL)
                    .header("apikey", Constants.APP_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
                    .post(payload.toRequestBody(jsonMediaType))
                    .build()

                okHttpClient.newCall(statusRequest).execute().use { response ->
                    val body = response.body?.string().orEmpty()
                    if (response.code == 404) {
                        // Backward compatibility for older deployments still using /tv-auth-poll
                        val pollRequest = Request.Builder()
                            .url(Constants.TV_AUTH_POLL_URL)
                            .header("apikey", Constants.APP_ANON_KEY)
                            .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
                            .post(payload.toRequestBody(jsonMediaType))
                            .build()
                        okHttpClient.newCall(pollRequest).execute().use { fallback ->
                            val fallbackBody = fallback.body?.string().orEmpty()
                            if (!fallback.isSuccessful) {
                                throw IllegalStateException(parseError(fallbackBody, context.getString(R.string.tv_link_failed_poll)))
                            }
                            return@use parseStatus(fallbackBody)
                        }
                    }
                    if (!response.isSuccessful) {
                        throw IllegalStateException(parseError(body, context.getString(R.string.tv_link_failed_poll)))
                    }
                    parseStatus(body)
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
        val normalizedEmail = AuthEmailValidator.normalize(email)
        val isSignup = intent.equals("signup", ignoreCase = true)
        AuthEmailValidator.validate(normalizedEmail, rejectDisposable = isSignup)?.let { messageRes ->
            val message = context.getString(messageRes)
            return Result.failure(IllegalArgumentException(message))
        }
        return withContext(Dispatchers.IO) {
            runCatching {
                val payload = JSONObject()
                    .put("code", userCode)
                    .put("email", normalizedEmail)
                    .put("password", password)
                    .put("intent", intent)
                    .toString()

                val request = Request.Builder()
                    .url(Constants.TV_AUTH_COMPLETE_URL)
                    .header("apikey", Constants.APP_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
                    .post(payload.toRequestBody(jsonMediaType))
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    val body = response.body?.string().orEmpty()
                    if (!response.isSuccessful) {
                        throw IllegalStateException(parseError(body, context.getString(R.string.tv_link_failed)))
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

    private fun parseStatus(body: String): TvDeviceAuthStatus {
        val json = JSONObject(body)
        val status = when (json.optString("status").lowercase()) {
            "pending" -> TvDeviceAuthStatusType.PENDING
            "approved" -> TvDeviceAuthStatusType.APPROVED
            "expired" -> TvDeviceAuthStatusType.EXPIRED
            else -> TvDeviceAuthStatusType.ERROR
        }
        return TvDeviceAuthStatus(
            status = status,
            accessToken = json.optString("access_token").ifBlank { null },
            refreshToken = json.optString("refresh_token").ifBlank { null },
            email = json.optString("email").ifBlank { null },
            message = json.optString("message").ifBlank { null }
        )
    }
}
