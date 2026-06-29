package com.arflix.tv.data.repository

import android.content.Context
import android.os.Build
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.BuildConfig
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import com.arflix.tv.util.detectDeviceType
import com.arflix.tv.util.settingsDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppUsageAnalyticsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient,
    private val authRepository: AuthRepository,
    private val profileManager: ProfileManager
) {
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

    suspend fun recordAppOpen() = withContext(Dispatchers.IO) {
        if (!Constants.USE_NETLIFY_CLOUD_SYNC &&
            (Constants.SUPABASE_URL.isBlank() || Constants.SUPABASE_ANON_KEY.isBlank())
        ) return@withContext

        runCatching {
            val now = System.currentTimeMillis()
            val lastSentAt = context.settingsDataStore.data.first()[LAST_APP_OPEN_SENT_AT_KEY] ?: 0L
            if (now - lastSentAt < APP_OPEN_MIN_INTERVAL_MS) {
                return@runCatching
            }

            withTimeoutOrNull(AUTH_STATE_WAIT_MS) {
                authRepository.authState.first { it !is AuthState.Loading }
            }

            val installId = getOrCreateInstallId()
            val accessToken = runCatching { authRepository.getAccessToken() }.getOrNull().orEmpty()
            val profileId = runCatching { profileManager.getProfileId() }
                .getOrDefault(profileManager.getProfileIdSync())
                .takeIf { it.isNotBlank() }
            val deviceType = detectDeviceType(context).name.lowercase()

            val metadata = JSONObject()
                .put("android_sdk", Build.VERSION.SDK_INT)

            val payload = JSONObject()
                .put("event_name", "app_open")
                .put("install_id", installId)
                .put("profile_id", profileId)
                .put("platform", "android")
                .put("device_type", deviceType)
                .put("app_version", BuildConfig.VERSION_NAME)
                .put("app_version_code", BuildConfig.VERSION_CODE)
                .put("distribution", if (BuildConfig.SELF_UPDATE_ENABLED) "sideload" else "play")
                .put("metadata", metadata)

            if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                authRepository.getCurrentUserIdForSync()?.takeIf { it.isNotBlank() }?.let { userId ->
                    payload.put("user_id", userId)
                }
                authRepository.getCurrentUserEmail()?.takeIf { it.isNotBlank() }?.let { email ->
                    payload.put("email", email)
                }
            }

            val requestBuilder = Request.Builder()
                .url(Constants.APP_USAGE_EVENT_URL)
                .post(payload.toString().toRequestBody(jsonMediaType))

            if (!Constants.USE_NETLIFY_CLOUD_SYNC) {
                requestBuilder
                    .header("apikey", Constants.SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.SUPABASE_ANON_KEY}")
                if (accessToken.isNotBlank()) {
                    requestBuilder.header("x-user-token", accessToken)
                }
            } else {
                requestBuilder
                    .header("apikey", Constants.APP_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
                    .header("Cache-Control", "no-cache, no-store")
            }

            okHttpClient.newCall(requestBuilder.build()).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IllegalStateException("Usage event failed: HTTP ${response.code}")
                }
            }
            context.settingsDataStore.edit { prefs ->
                prefs[LAST_APP_OPEN_SENT_AT_KEY] = now
            }
        }.onFailure { error ->
            AppLogger.w(TAG, "App usage analytics event failed", error)
        }
    }

    private suspend fun getOrCreateInstallId(): String {
        val key = INSTALL_ID_KEY
        val existing = context.settingsDataStore.data.first()[key]
        if (!existing.isNullOrBlank()) return existing

        val created = UUID.randomUUID().toString()
        var resolved = created
        context.settingsDataStore.edit { prefs ->
            val stored = prefs[key]
            if (stored.isNullOrBlank()) {
                prefs[key] = created
            } else {
                resolved = stored
            }
        }
        return resolved
    }

    private companion object {
        const val TAG = "AppUsageAnalytics"
        const val AUTH_STATE_WAIT_MS = 10_000L
        const val APP_OPEN_MIN_INTERVAL_MS = 24 * 60 * 60 * 1000L
        val INSTALL_ID_KEY = stringPreferencesKey("analytics_install_id_v1")
        val LAST_APP_OPEN_SENT_AT_KEY = longPreferencesKey("analytics_last_app_open_sent_at_v1")
    }
}
