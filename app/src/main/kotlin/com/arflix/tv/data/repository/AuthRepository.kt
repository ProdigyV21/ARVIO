package com.arflix.tv.data.repository

import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.R
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import com.arflix.tv.util.AuthEmailValidator
import com.arflix.tv.util.authDataStore
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.hash
import com.arflix.tv.util.sanitizeEmail
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.gson.JsonParser
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.MemoryCodeVerifierCache
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import io.github.jan.supabase.gotrue.user.UserSession
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

// authDataStore is defined in com.arflix.tv.util.DataStores to avoid duplicate DataStore instances

/**
 * User profile data from Supabase
 */
@Serializable
data class UserProfile(
    val id: String = "",
    val email: String = "",
    val trakt_token: JsonObject? = null,
    val addons: String? = null,
    val default_subtitle: String? = null,
    val auto_play_next: Boolean? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)

@Serializable
private data class AccountSyncStateRow(
    val user_id: String,
    val payload: String? = null,
    val updated_at: String? = null
)

@Serializable
private data class UserSettingsAccountSyncRow(
    val settings: JsonObject? = null
)

@Serializable
private data class UserSettingsAccountSyncUpdate(
    val user_id: String,
    val settings: JsonObject
)

@Serializable
private data class UserSettingsSettingsUpdate(
    val settings: JsonObject
)

@Serializable
private data class ProfileAccountSyncRow(
    val addons: String? = null
)

@Serializable
private data class ProfileAccountSyncUpdate(
    val addons: String
)

private data class AccountSyncPayloadCandidate(
    val source: String,
    val payload: String,
    val updatedAtMillis: Long
)

private class AccountSyncPayloadRejectedException(message: String) : Exception(message)

private fun parseJsonObject(payload: String): com.google.gson.JsonObject? {
    return runCatching { JsonParser().parse(payload).asJsonObject }.getOrNull()
}

internal fun accountSyncPayloadProfileCount(payload: String): Int? {
    val root = parseJsonObject(payload) ?: return null
    if (!root.has("profiles")) return null
    return root.get("profiles")
        ?.takeIf { !it.isJsonNull && it.isJsonArray }
        ?.asJsonArray
        ?.size()
        ?: 0
}

internal fun accountSyncPayloadScopedCoverage(payload: String): Int {
    val root = parseJsonObject(payload) ?: return 0
    val profileIds = root.get("profiles")
        ?.takeIf { !it.isJsonNull && it.isJsonArray }
        ?.asJsonArray
        ?.mapNotNull { profile ->
            profile
                ?.takeIf { !it.isJsonNull && it.isJsonObject }
                ?.asJsonObject
                ?.stringValue("id")
                ?.takeIf { it.isNotBlank() }
        }
        ?.toSet()
        .orEmpty()
    if (profileIds.isEmpty()) return 0

    val scopedKeys = listOf(
        "profileSettingsById",
        "addonsByProfile",
        "catalogsByProfile",
        "hiddenPreinstalledByProfile",
        "hiddenAddonByProfile",
        "hiddenHomeServerByProfile",
        "iptvByProfile",
        "watchlistByProfile"
    )

    return scopedKeys.sumOf { key ->
        val obj = root.getObject(key) ?: return@sumOf 0
        profileIds.count { profileId -> obj.has(profileId) }
    }
}

internal fun accountSyncPayloadRestoreRank(payload: String): Int {
    val root = parseJsonObject(payload) ?: return 0
    val profiles = root.get("profiles")
        ?.takeIf { !it.isJsonNull && it.isJsonArray }
        ?.asJsonArray
    val profileCount = if (root.has("profiles")) profiles?.size() ?: 0 else null
    val hasUsefulProfiles = when {
        profileCount == null -> false
        profileCount > 1 -> true
        profileCount == 1 -> profiles?.get(0)
            ?.takeIf { !it.isJsonNull && it.isJsonObject }
            ?.asJsonObject
            ?.let { !it.isPlaceholderCloudProfile() }
            ?: false
        else -> false
    }
    val hasConfiguredState = root.hasConfiguredAccountState()
    val hasFullSnapshotShape = root.hasFullAccountSnapshotShape()

    return when {
        profileCount != null && profileCount <= 0 -> 0
        profileCount != null && profileCount > 1 && hasFullSnapshotShape -> 80
        profileCount != null && profileCount > 1 -> 70
        (hasUsefulProfiles || hasConfiguredState) && hasFullSnapshotShape -> 50
        hasUsefulProfiles || hasConfiguredState -> 40
        profileCount == null && hasFullSnapshotShape -> 30
        profileCount == null -> 20
        else -> 10
    }
}

internal fun accountSyncPayloadSaveSucceeded(
    accountSyncSaved: Boolean,
    userSettingsSaved: Boolean,
    profileAddonsSaved: Boolean
): Boolean {
    return accountSyncSaved || userSettingsSaved
}

private fun accountSyncPayloadsMatch(expected: String, actual: String?): Boolean {
    if (actual.isNullOrBlank()) return false
    if (expected == actual) return true
    return runCatching {
        val expectedJson = JSONObject(expected)
        val actualJson = JSONObject(actual)
        expectedJson.remove("updatedAt")
        actualJson.remove("updatedAt")
        expectedJson.toString() == actualJson.toString()
    }.getOrDefault(false)
}

private fun safePostgrestError(body: String): String {
    if (body.isBlank()) return "empty response"
    val parsed = runCatching { JSONObject(body) }.getOrNull()
    return parsed?.optString("message")?.takeIf { it.isNotBlank() }
        ?: parsed?.optString("error")?.takeIf { it.isNotBlank() }
        ?: body.take(180)
}

private fun com.google.gson.JsonObject.isPlaceholderCloudProfile(): Boolean {
    val name = stringValue("name").trim()
    val avatarId = intValue("avatarId")
    val avatarImageVersion = longValue("avatarImageVersion")
    val isKidsProfile = booleanValue("isKidsProfile")
    val isLocked = booleanValue("isLocked")
    val pin = stringValue("pin").trim()

    return name.equals("Profile 1", ignoreCase = true) &&
        avatarId == 0 &&
        avatarImageVersion <= 0L &&
        !isKidsProfile &&
        !isLocked &&
        pin.isBlank()
}

private fun com.google.gson.JsonObject.hasFullAccountSnapshotShape(): Boolean {
    return has("profileSettingsById") ||
        has("addonsByProfile") ||
        has("catalogsByProfile") ||
        has("iptvByProfile") ||
        has("watchlistByProfile")
}

private fun com.google.gson.JsonObject.hasConfiguredAccountState(): Boolean {
    if (arraySize("addons") > 0) return true
    if (stringValue("iptvM3uUrl").isNotBlank()) return true

    return objectHasNonEmptyArray("addonsByProfile") ||
        objectHasNonEmptyArray("watchlistByProfile") ||
        objectHasConfiguredIptvProfile("iptvByProfile")
}

private fun com.google.gson.JsonObject.objectHasNonEmptyArray(key: String): Boolean {
    val obj = getObject(key) ?: return false
    obj.entrySet().forEach { (_, value) ->
        if (value != null && !value.isJsonNull && value.isJsonArray && value.asJsonArray.size() > 0) {
            return true
        }
    }
    return false
}

private fun com.google.gson.JsonObject.objectHasConfiguredIptvProfile(key: String): Boolean {
    val obj = getObject(key) ?: return false
    obj.entrySet().forEach { (_, rawValue) ->
        val value = rawValue
            ?.takeIf { !it.isJsonNull && it.isJsonObject }
            ?.asJsonObject
            ?: return@forEach
        if (value.stringValue("m3uUrl").isNotBlank()) return true
        if (value.stringValue("epgUrl").isNotBlank()) return true
        if (value.arraySize("playlists") > 0) return true
        if (value.arraySize("favoriteChannels") > 0) return true
        if (value.arraySize("favoriteGroups") > 0) return true
    }
    return false
}

private fun com.google.gson.JsonObject.getObject(key: String): com.google.gson.JsonObject? {
    return get(key)
        ?.takeIf { !it.isJsonNull && it.isJsonObject }
        ?.asJsonObject
}

private fun com.google.gson.JsonObject.arraySize(key: String): Int {
    return get(key)
        ?.takeIf { !it.isJsonNull && it.isJsonArray }
        ?.asJsonArray
        ?.size()
        ?: 0
}

private fun com.google.gson.JsonObject.stringValue(key: String): String {
    return runCatching {
        get(key)
            ?.takeIf { !it.isJsonNull && it.isJsonPrimitive }
            ?.asString
            .orEmpty()
    }.getOrDefault("")
}

private fun com.google.gson.JsonObject.intValue(key: String): Int {
    return runCatching {
        get(key)
            ?.takeIf { !it.isJsonNull && it.isJsonPrimitive }
            ?.asInt
            ?: 0
    }.getOrDefault(0)
}

private fun com.google.gson.JsonObject.longValue(key: String): Long {
    return runCatching {
        get(key)
            ?.takeIf { !it.isJsonNull && it.isJsonPrimitive }
            ?.asLong
            ?: 0L
    }.getOrDefault(0L)
}

private fun com.google.gson.JsonObject.booleanValue(key: String): Boolean {
    return runCatching {
        get(key)
            ?.takeIf { !it.isJsonNull && it.isJsonPrimitive }
            ?.asBoolean
            ?: false
    }.getOrDefault(false)
}

/**
 * Authentication state
 */
sealed class AuthState {
    object Loading : AuthState()
    object NotAuthenticated : AuthState()
    data class Authenticated(
        val userId: String,
        val email: String,
        val profile: UserProfile?
    ) : AuthState()
    data class Error(val message: String) : AuthState()
}

/**
 * Repository for Supabase authentication and user profile management
 */
@Singleton
class AuthRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient,
    private val traktRepositoryProvider: Provider<TraktRepository>,
    private val cloudSyncRepositoryProvider: Provider<CloudSyncRepository>
) {
    private val TAG = "AuthRepository"
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()
    private val accountSyncMutationMutex = Mutex()
    private val ACCOUNT_SYNC_PAYLOAD_KEY = "accountSyncPayload"
    private val ACCOUNT_SYNC_UPDATED_AT_KEY = "accountSyncUpdatedAt"
    private val PROFILE_SYNC_PAYLOAD_KEY = "__arvioAccountSyncPayload"
    private val PROFILE_SYNC_UPDATED_AT_KEY = "__arvioAccountSyncUpdatedAt"
    private val PROFILE_SYNC_LEGACY_ADDONS_KEY = "__arvioLegacyAddons"
    private val ACCOUNT_SYNC_SOURCE_NETLIFY = "netlify_account_sync"
    private val ACCOUNT_SYNC_SOURCE_PRIMARY = "account_sync_state"
    private val ACCOUNT_SYNC_SOURCE_USER_SETTINGS = "user_settings"
    private val ACCOUNT_SYNC_SOURCE_PROFILE_ADDONS = "profile_addons"

    // DataStore keys
    private object PrefsKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val USER_ID = stringPreferencesKey("user_id")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    // Keep reference to session manager for explicit saves
    private val sessionManager = DataStoreSessionManager(context.authDataStore)

    // Supabase client (lazy to avoid startup overhead when unauthenticated)
    private val supabase: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = Constants.SUPABASE_URL,
            supabaseKey = Constants.SUPABASE_ANON_KEY
        ) {
            install(Auth) {
                sessionManager = this@AuthRepository.sessionManager
                codeVerifierCache = MemoryCodeVerifierCache()
                autoLoadFromStorage = true
                autoSaveToStorage = true
            }
            install(Postgrest)
        }
    }

    // Auth state
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    // User profile
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    /**
     * Check if user is logged in on app start
     * Note: Supabase SDK requires main thread for initialization (lifecycle observers)
     */
    suspend fun checkAuthState() {
        try {
            val prefs = context.authDataStore.data.first()
            val accessToken = prefs[PrefsKeys.ACCESS_TOKEN]
            val refreshToken = prefs[PrefsKeys.REFRESH_TOKEN]
            val cachedUserId = prefs[PrefsKeys.USER_ID]
            val cachedEmail = prefs[PrefsKeys.USER_EMAIL]

            val hasAccessToken = !accessToken.isNullOrBlank()
            val hasRefreshToken = !refreshToken.isNullOrBlank()

            if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                val shouldRefreshNetlifyToken = !hasAccessToken || isJwtExpired(accessToken ?: "")
                val usableAccessToken = when {
                    !shouldRefreshNetlifyToken -> accessToken
                    hasRefreshToken -> refreshNetlifyAccessToken(refreshToken ?: "")
                    else -> null
                }
                val userId = cachedUserId
                    ?: usableAccessToken?.let { extractUserIdFromAccessToken(it) }
                val email = cachedEmail
                    ?: usableAccessToken?.let { extractUserEmailFromAccessToken(it) }
                    ?: ""

                if (!userId.isNullOrBlank()) {
                    if (!shouldRefreshNetlifyToken && !usableAccessToken.isNullOrBlank() && !refreshToken.isNullOrBlank()) {
                        storeRawSessionTokens(
                            accessToken = usableAccessToken,
                            refreshToken = refreshToken,
                            userId = userId,
                            email = email
                        )
                    }
                    val profile = UserProfile(id = userId, email = email)
                    traktRepositoryProvider.get().setUserId(userId)
                    _userProfile.value = profile
                    _authState.value = AuthState.Authenticated(userId, email, profile)
                } else {
                    _authState.value = AuthState.NotAuthenticated
                }
                return
            }

            var session: UserSession? = null

            // Supabase SDK requires main thread for initialization (lifecycle observers)
            // First try: Load from SessionManager via Supabase SDK
            try {
                session = withTimeoutOrNull(2_500L) {
                    withContext(Dispatchers.Main) {
                        supabase.auth.loadFromStorage(true)
                        supabase.auth.currentSessionOrNull()
                    }
                }
            } catch (e: Exception) {
            }

            // Second try: Import from cached tokens
            if (session == null && hasAccessToken && hasRefreshToken) {
                try {
                    session = withTimeoutOrNull(2_500L) {
                        withContext(Dispatchers.Main) {
                            supabase.auth.importAuthToken(accessToken ?: "", refreshToken ?: "", false, true)
                            supabase.auth.currentSessionOrNull()
                        }
                    }
                    if (session != null) {
                        // Save the imported session to SessionManager
                        storeSession(session)
                    }
                } catch (e: Exception) {
                }
            }

            // Third try: Refresh the session
            if (session == null && hasRefreshToken) {
                session = withTimeoutOrNull(3_000L) {
                    withContext(Dispatchers.Main) {
                        ensureValidSession()
                    }
                }
            }
            if (hasAccessToken || hasRefreshToken || session != null || !cachedUserId.isNullOrBlank()) {
                val userId = session?.user?.id ?: cachedUserId
                val email = session?.user?.email ?: cachedEmail

                if (session != null && userId != null && email != null) {
                    storeSession(session)
                    // Load profile
                    val profile = withTimeoutOrNull(3_000L) { loadUserProfile(userId) }
                    _userProfile.value = profile
                    _authState.value = AuthState.Authenticated(userId, email, profile)
                } else if (userId != null && email != null) {
                    // Fallback to cached identity even if refresh failed (avoid forcing daily logins).
                    val profile = try {
                        withTimeoutOrNull(3_000L) { loadUserProfile(userId) }
                    } catch (_: Exception) {
                        null
                    } ?: UserProfile(id = userId, email = email)
                    traktRepositoryProvider.get().setUserId(userId)
                    withTimeoutOrNull(1_500L) {
                        traktRepositoryProvider.get().syncLocalTokensToProfileIfNeeded()
                    }
                    _userProfile.value = profile
                    _authState.value = AuthState.Authenticated(userId, email, profile)
                } else {
                    _authState.value = AuthState.NotAuthenticated
                }
            } else {
                _authState.value = AuthState.NotAuthenticated
            }
        } catch (e: Exception) {
            _authState.value = AuthState.NotAuthenticated
        }
    }

    /**
     * Sign in with email and password
     */
    suspend fun signIn(email: String, password: String): Result<Unit> {
        val normalizedEmail = AuthEmailValidator.normalize(email)
        AuthEmailValidator.validate(normalizedEmail, rejectDisposable = false)?.let { messageRes ->
            val message = context.getString(messageRes)
            _authState.value = AuthState.Error(message)
            return Result.failure(Exception(message))
        }
        return try {
            AppLogger.breadcrumb("Auth", "email_sign_in_start")
            _authState.value = AuthState.Loading

            if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                val tokens = signInCloudAccountSession(normalizedEmail, password)
                return signInWithSessionTokens(tokens.accessToken, tokens.refreshToken).also {
                    if (it.isSuccess) {
                        AppLogger.breadcrumb("Auth", "email_sign_in_success_netlify")
                    }
                }
            }

            supabase.auth.signInWith(Email) {
                this.email = normalizedEmail
                this.password = password
            }

            val session = supabase.auth.currentSessionOrNull()
            val user = session?.user

            if (user != null) {
                storeSession(session)

                // Load or create profile
                var profile = loadUserProfile(user.id)
                if (profile == null) {
                    profile = createDefaultProfile(user.id, user.email ?: normalizedEmail)
                }

                _userProfile.value = profile
                _authState.value = AuthState.Authenticated(user.id, user.email ?: normalizedEmail, profile)
                AppLogger.breadcrumb("Auth", "email_sign_in_success")
                Result.success(Unit)
            } else {
                val message = safeErrorMessage(null, context.getString(R.string.auth_signin_failed))
                _authState.value = AuthState.Error(message)
                AppLogger.breadcrumb("Auth", "email_sign_in_no_session", severity = "warning")
                Result.failure(Exception(message))
            }
        } catch (e: Exception) {
            val message = safeErrorMessage(e, context.getString(R.string.auth_signin_failed))
            _authState.value = AuthState.Error(message)
            AppLogger.breadcrumb("Auth", "email_sign_in_failed ${e::class.java.simpleName}", severity = "warning")
            Result.failure(Exception(message))
        }
    }

    /**
     * Sign up with email and password
     */
    suspend fun signUp(email: String, password: String): Result<Unit> {
        val normalizedEmail = AuthEmailValidator.normalize(email)
        AuthEmailValidator.validate(normalizedEmail)?.let { messageRes ->
            val message = context.getString(messageRes)
            _authState.value = AuthState.Error(message)
            return Result.failure(Exception(message))
        }
        return try {
            AppLogger.breadcrumb("Auth", "email_sign_up_start")
            _authState.value = AuthState.Loading

            val tokens = createCloudAccountSession(normalizedEmail, password)
            signInWithSessionTokens(tokens.accessToken, tokens.refreshToken).also {
                if (it.isSuccess) {
                    AppLogger.breadcrumb("Auth", "email_sign_up_success")
                }
            }
        } catch (e: Exception) {
            val message = safeErrorMessage(e, context.getString(R.string.auth_signup_failed))
            _authState.value = AuthState.Error(message)
            AppLogger.recordException(
                throwable = e,
                context = mapOf(
                    "error_area" to "Auth",
                    "auth_flow" to "email_sign_up",
                    "auth_error" to message
                )
            )
            Result.failure(Exception(message))
        }
    }

    private data class CloudAccountSession(
        val accessToken: String,
        val refreshToken: String
    )

    private suspend fun signInCloudAccountSession(email: String, password: String): CloudAccountSession {
        return requestCloudAccountSession(
            url = Constants.AUTH_LOGIN_URL,
            email = email,
            password = password,
            defaultError = context.getString(R.string.auth_signin_failed)
        )
    }

    private suspend fun createCloudAccountSession(email: String, password: String): CloudAccountSession {
        return requestCloudAccountSession(
            url = Constants.CLOUD_AUTH_EMAIL_URL,
            email = email,
            password = password,
            defaultError = context.getString(R.string.auth_unable_create_account)
        )
    }

    private suspend fun requestCloudAccountSession(
        url: String,
        email: String,
        password: String,
        defaultError: String
    ): CloudAccountSession {
        return withContext(Dispatchers.IO) {
            val payload = JSONObject()
                .put("email", email)
                .put("password", password)
                .toString()

            val request = Request.Builder()
                .url(url)
                .header("apikey", Constants.APP_ANON_KEY)
                .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
                .post(payload.toRequestBody(jsonMediaType))
                .build()

            okHttpClient.newCall(request).execute().use { response ->
                val body = response.body?.string().orEmpty()
                val json = runCatching { JSONObject(body) }.getOrNull()
                if (!response.isSuccessful) {
                    val message = cloudAuthErrorMessage(json, defaultError)
                    throw IllegalStateException(message)
                }

                val accessToken = json?.optString("access_token").orEmpty()
                val refreshToken = json?.optString("refresh_token").orEmpty()
                if (accessToken.isBlank() || refreshToken.isBlank()) {
                    throw IllegalStateException(context.getString(R.string.auth_response_incomplete))
                }
                CloudAccountSession(accessToken, refreshToken)
            }
        }
    }

    /**
     * Sign in by importing Supabase access/refresh tokens obtained from device auth flow.
     */
    suspend fun signInWithSessionTokens(
        accessToken: String,
        refreshToken: String
    ): Result<Unit> {
        return try {
            _authState.value = AuthState.Loading
            val session = if (Constants.USE_NETLIFY_CLOUD_SYNC && !extractUserIdFromAccessToken(accessToken).isNullOrBlank()) {
                null
            } else {
                withContext(Dispatchers.Main) {
                    runCatching { supabase.auth.importAuthToken(accessToken, refreshToken, false, true) }
                    supabase.auth.currentSessionOrNull() ?: run {
                        runCatching { supabase.auth.loadFromStorage(true) }
                        supabase.auth.currentSessionOrNull()
                    }
                }
            }
            val resolvedUserId = session?.user?.id ?: extractUserIdFromAccessToken(accessToken)
            val resolvedEmail = session?.user?.email
                ?: extractUserEmailFromAccessToken(accessToken)
                ?: context.authDataStore.data.first()[PrefsKeys.USER_EMAIL]

            if (session != null) {
                storeSession(session)
            } else if (!resolvedUserId.isNullOrBlank()) {
                storeRawSessionTokens(
                    accessToken = accessToken,
                    refreshToken = refreshToken,
                    userId = resolvedUserId,
                    email = resolvedEmail
                )
            }

            if (!resolvedUserId.isNullOrBlank()) {
                var profile = if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                    null
                } else {
                    runCatching { loadUserProfile(resolvedUserId) }.getOrNull()
                }
                if (profile == null && !Constants.USE_NETLIFY_CLOUD_SYNC) {
                    profile = createDefaultProfile(resolvedUserId, resolvedEmail ?: "")
                }
                if (profile == null) {
                    profile = UserProfile(id = resolvedUserId, email = resolvedEmail ?: "")
                }
                _userProfile.value = profile
                _authState.value = AuthState.Authenticated(
                    resolvedUserId,
                    resolvedEmail ?: profile.email,
                    profile
                )
                AppLogger.breadcrumb("Auth", "session_import_success")
                Result.success(Unit)
            } else {
                val message = context.getString(R.string.auth_failed_import_session)
                _authState.value = AuthState.Error(message)
                AppLogger.breadcrumb("Auth", "session_import_missing_user", severity = "warning")
                Result.failure(Exception(message))
            }
        } catch (e: Exception) {
            val message = safeErrorMessage(e, context.getString(R.string.auth_signin_failed))
            _authState.value = AuthState.Error(message)
            AppLogger.recordException(
                throwable = e,
                context = mapOf(
                    "error_area" to "Auth",
                    "auth_flow" to "session_import",
                    "auth_error" to message
                )
            )
            Result.failure(Exception(message))
        }
    }

    /**
     * Sign in with Google using Credential Manager
     * Returns the GetCredentialRequest for the Activity to handle
     * Uses GetSignInWithGoogleOption which works better on TV devices
     */
    fun getGoogleSignInRequest(): GetCredentialRequest {
        // Use GetSignInWithGoogleOption for TV - this opens the Google Sign-In UI
        val signInWithGoogleOption = GetSignInWithGoogleOption.Builder(Constants.GOOGLE_WEB_CLIENT_ID)
            .setNonce(generateNonce())
            .build()

        return GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()
    }

    /**
     * Generate a random nonce for Google Sign-In security
     */
    private fun generateNonce(): String {
        val bytes = ByteArray(16)
        java.security.SecureRandom().nextBytes(bytes)
        return bytes.joinToString("") { "%02x".format(it) }
    }

    /**
     * Handle Google Sign-In credential response
     */
    suspend fun handleGoogleSignInResult(result: GetCredentialResponse): Result<Unit> {
        return try {
            _authState.value = AuthState.Loading

            val credential = result.credential

            when (credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        val idToken = googleIdTokenCredential.idToken

                        // Sign in to Supabase with the Google ID token
                        supabase.auth.signInWith(IDToken) {
                            this.idToken = idToken
                            provider = Google
                        }

                        val session = supabase.auth.currentSessionOrNull()
                        val user = session?.user

                        if (user != null) {
                            storeSession(session)

                            // Load or create profile
                            var profile = loadUserProfile(user.id)
                            if (profile == null) {
                                profile = createDefaultProfile(user.id, user.email ?: "")
                            }

                            _userProfile.value = profile
                            _authState.value = AuthState.Authenticated(user.id, user.email ?: "", profile)
                            Result.success(Unit)
                        } else {
                            val message = context.getString(R.string.auth_google_failed)
                            _authState.value = AuthState.Error(message)
                            Result.failure(Exception(message))
                        }
                    } else {
                        val message = context.getString(R.string.auth_unexpected_credential)
                        _authState.value = AuthState.Error(message)
                        Result.failure(Exception(message))
                    }
                }
                else -> {
                    val message = context.getString(R.string.auth_unexpected_credential)
                    _authState.value = AuthState.Error(message)
                    Result.failure(Exception(message))
                }
            }
        } catch (e: GoogleIdTokenParsingException) {
            _authState.value = AuthState.Error(context.getString(R.string.auth_failed_parse_google))
            Result.failure(e)
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: context.getString(R.string.auth_google_failed))
            Result.failure(e)
        }
    }

    /**
     * Sign out
     */
    suspend fun signOut() {
        // Push final state before signing out, but never let a slow network/cloud
        // request trap the user on the settings screen.
        withTimeoutOrNull(2_500L) {
            runCatching { cloudSyncRepositoryProvider.get().pushToCloud(force = true) }
                .onFailure { error ->
                    AppLogger.breadcrumb(
                        tag = "Auth",
                        message = "sign_out_final_push_failed ${error::class.java.simpleName}",
                        severity = "warning"
                    )
                }
        } ?: AppLogger.breadcrumb(
            tag = "Auth",
            message = "sign_out_final_push_timeout",
            severity = "warning"
        )

        if (!Constants.USE_NETLIFY_CLOUD_SYNC) {
            withTimeoutOrNull(2_000L) {
                runCatching { supabase.auth.signOut() }
            }
        }

        runCatching { traktRepositoryProvider.get().logout() }

        // Clear ALL local data (auth + settings + user preferences)
        runCatching { context.authDataStore.edit { prefs -> prefs.clear() } }
        runCatching { context.settingsDataStore.edit { prefs -> prefs.clear() } }

        _userProfile.value = null
        _authState.value = AuthState.NotAuthenticated
    }

    /**
     * Load user profile from Supabase
     */
    private suspend fun loadUserProfile(userId: String): UserProfile? {
        return try {
            val result = supabase.postgrest
                .from("profiles")
                .select {
                    filter { eq("id", userId) }
                }
                .decodeSingleOrNull<UserProfile>()

            if (result != null) {
                // Set user ID in Trakt repo for Supabase sync
                traktRepositoryProvider.get().setUserId(userId)

                // Load tokens from profile or sync local tokens if profile is empty
                if (result.trakt_token != null) {
                    traktRepositoryProvider.get().loadTokensFromProfile(result.trakt_token)
                } else {
                    traktRepositoryProvider.get().syncLocalTokensToProfileIfNeeded()
                }
            }

            result
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Create default profile for new user
     */
    private suspend fun createDefaultProfile(userId: String, email: String): UserProfile {
        try {
            supabase.postgrest
                .from("profiles")
                .insert(
                    mapOf(
                        "id" to userId,
                        "email" to email
                    )
                )

            // Set user ID in Trakt repo for Supabase sync
            traktRepositoryProvider.get().setUserId(userId)
            traktRepositoryProvider.get().syncLocalTokensToProfileIfNeeded()
        } catch (e: Exception) {
        }

        return UserProfile(
            id = userId,
            email = email
        )
    }

    private fun safeErrorMessage(error: Exception?, fallback: String): String {
        val rawMessage = error?.message ?: return fallback
        val message = rawMessage.lowercase()
        val netlifyPasswordHelp =
            "Invalid email or password. If this is an existing ARVIO Cloud account, create a new password at auth.arvio.tv and then sign in again."
        return when {
            "arvio cloud moved" in message || "password setup" in message -> rawMessage
            Constants.USE_NETLIFY_CLOUD_SYNC && "invalid email or password" in message -> netlifyPasswordHelp
            "database error saving new user" in message -> context.getString(R.string.auth_account_exists)
            "settingssessionmanager" in message -> context.getString(R.string.auth_signin_retry)
            "invalid login credentials" in message -> context.getString(R.string.auth_invalid_credentials)
            "email not confirmed" in message || "confirm" in message -> context.getString(R.string.auth_verify_email)
            "user already" in message || "already registered" in message -> context.getString(R.string.auth_account_exists)
            else -> fallback
        }
    }

    /**
     * Update user profile
     */
    suspend fun updateProfile(profile: UserProfile): Result<Unit> {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            _userProfile.value = profile
            return Result.success(Unit)
        }
        return try {
            supabase.postgrest
                .from("profiles")
                .update(profile) {
                    filter { eq("id", profile.id) }
                }

            _userProfile.value = profile
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get Trakt token from profile
     */
    fun getTraktAccessToken(): String? {
        return _userProfile.value?.trakt_token?.get("access_token")?.jsonPrimitive?.content
    }

    /**
     * Check if user has Trakt linked
     */
    fun isTraktLinked(): Boolean {
        return _userProfile.value?.trakt_token != null
    }

    /**
     * Get current user ID
     */
    fun getCurrentUserId(): String? {
        return when (val state = _authState.value) {
            is AuthState.Authenticated -> state.userId
            else -> null
        }
    }

    fun getCurrentUserEmail(): String? {
        return when (val state = _authState.value) {
            is AuthState.Authenticated -> state.email
            else -> _userProfile.value?.email
        }?.takeIf { it.isNotBlank() }
    }

    suspend fun getCurrentUserIdForSync(): String? {
        getCurrentUserId()?.takeIf { it.isNotBlank() }?.let { return it }

        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            val prefs = context.authDataStore.data.first()
            val userId = prefs[PrefsKeys.USER_ID]?.takeIf { it.isNotBlank() }
            val email = prefs[PrefsKeys.USER_EMAIL]?.takeIf { it.isNotBlank() } ?: ""
            if (!userId.isNullOrBlank()) {
                val currentProfile = _userProfile.value
                if (_authState.value !is AuthState.Authenticated) {
                    _authState.value = AuthState.Authenticated(
                        userId = userId,
                        email = email,
                        profile = currentProfile ?: UserProfile(id = userId, email = email)
                    )
                }
                return userId
            }
        }

        val session = ensureValidSession()
        session?.user?.id?.takeIf { it.isNotBlank() }?.let { userId ->
            val email = session.user?.email
                ?: context.authDataStore.data.first()[PrefsKeys.USER_EMAIL]
                ?: ""
            val currentProfile = _userProfile.value
            if (_authState.value !is AuthState.Authenticated) {
                _authState.value = AuthState.Authenticated(
                    userId = userId,
                    email = email,
                    profile = currentProfile ?: UserProfile(id = userId, email = email)
                )
            }
            return userId
        }

        return context.authDataStore.data.first()[PrefsKeys.USER_ID]?.takeIf { it.isNotBlank() }
    }

    suspend fun hasValidCloudSyncSession(): Boolean {
        return !getAccessToken().isNullOrBlank()
    }

    /**
     * Get Supabase access token for API calls
     */
    suspend fun getAccessToken(): String? {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            val prefs = context.authDataStore.data.first()
            val cached = prefs[PrefsKeys.ACCESS_TOKEN]
            return if (!cached.isNullOrBlank() && !isJwtExpired(cached)) cached else refreshAccessToken()
        }

        val session = ensureValidSession()
        if (session != null && !isSessionExpired(session)) {
            return session.accessToken
        }

        val prefs = context.authDataStore.data.first()
        val cached = prefs[PrefsKeys.ACCESS_TOKEN]
        return if (!cached.isNullOrBlank() && !isJwtExpired(cached)) cached else refreshAccessToken()
    }

    suspend fun refreshAccessToken(): String? {
        val prefs = context.authDataStore.data.first()
        val refreshToken = prefs[PrefsKeys.REFRESH_TOKEN]
        if (refreshToken.isNullOrBlank()) return null

        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            refreshNetlifyAccessToken(refreshToken)?.let { return it }
        }

        return try {
            val refreshed = supabase.auth.refreshSession(refreshToken)
            storeSession(refreshed)
            refreshed.accessToken
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun refreshNetlifyAccessToken(refreshToken: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url(Constants.AUTH_REFRESH_URL)
                    .header("apikey", Constants.APP_ANON_KEY)
                    .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
                    .post(
                        JSONObject()
                            .put("refresh_token", refreshToken)
                            .toString()
                            .toRequestBody(jsonMediaType)
                    )
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    val body = response.body?.string().orEmpty()
                    if (!response.isSuccessful) return@withContext null
                    val json = JSONObject(body)
                    val accessToken = json.optString("access_token")
                    val newRefreshToken = json.optString("refresh_token")
                    if (accessToken.isBlank() || newRefreshToken.isBlank()) return@withContext null
                    val user = json.optJSONObject("user")
                    val userId = user?.optString("id")?.takeIf { it.isNotBlank() }
                        ?: extractUserIdFromAccessToken(accessToken)
                    val email = user?.optString("email")?.takeIf { it.isNotBlank() }
                        ?: extractUserEmailFromAccessToken(accessToken)
                        ?: context.authDataStore.data.first()[PrefsKeys.USER_EMAIL]
                    if (userId.isNullOrBlank()) return@withContext null
                    storeRawSessionTokens(
                        accessToken = accessToken,
                        refreshToken = newRefreshToken,
                        userId = userId,
                        email = email
                    )
                    accessToken
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    private suspend fun ensureValidSession(): UserSession? {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            return null
        }

        var session = supabase.auth.currentSessionOrNull()
        if (session == null) {
            try {
                supabase.auth.loadFromStorage(false)
                session = supabase.auth.currentSessionOrNull()
            } catch (e: Exception) {
            }
        }
        if (session != null && !isSessionExpired(session)) {
            storeSession(session)
            return session
        }

        val prefs = context.authDataStore.data.first()
        val refreshToken = prefs[PrefsKeys.REFRESH_TOKEN]
        if (refreshToken.isNullOrBlank()) {
            return null
        }

        return try {
            val refreshed = supabase.auth.refreshSession(refreshToken)
            storeSession(refreshed)
            refreshed
        } catch (e: Exception) {
            null
        }
    }

    private fun isSessionExpired(session: UserSession, bufferSeconds: Long = 60): Boolean {
        val now = Clock.System.now().epochSeconds
        return session.expiresAt.epochSeconds <= now + bufferSeconds
    }

    /**
     * Checks if a JWT token is expired.
     *
     * SECURITY: Tokens without an `exp` claim are considered INVALID/EXPIRED.
     * This prevents accepting tokens that never expire.
     *
     * @param token The JWT token to check
     * @param bufferSeconds Buffer time before actual expiration (default 60s)
     * @return true if expired or invalid, false if still valid
     */
    private fun isJwtExpired(token: String, bufferSeconds: Long = 60): Boolean {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return true
            val payload = String(
                Base64.decode(parts[1], Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP),
                Charsets.UTF_8
            )
            val json = JSONObject(payload)
            // SECURITY FIX: Reject tokens without exp claim
            if (!json.has("exp")) {
                return true
            }
            val exp = json.getLong("exp")
            if (exp <= 0L) {
                return true
            }
            val now = Clock.System.now().epochSeconds
            exp <= now + bufferSeconds
        } catch (e: Exception) {
            true
        }
    }

    private suspend fun storeSession(session: UserSession) {
        // 1. Explicitly save through session manager (for Supabase SDK auto-restore)
        try {
            sessionManager.saveSession(session)
        } catch (e: Exception) {
        }

        // 2. Also save tokens directly (fallback for manual restoration)
        context.authDataStore.edit { prefs ->
            prefs[PrefsKeys.ACCESS_TOKEN] = session.accessToken
            prefs[PrefsKeys.REFRESH_TOKEN] = session.refreshToken
            val user = session.user
            if (user != null) {
                prefs[PrefsKeys.USER_ID] = user.id
                user.email?.let { prefs[PrefsKeys.USER_EMAIL] = it }
            }
        }
    }

    private suspend fun storeRawSessionTokens(
        accessToken: String,
        refreshToken: String,
        userId: String,
        email: String?
    ) {
        context.authDataStore.edit { prefs ->
            prefs[PrefsKeys.ACCESS_TOKEN] = accessToken
            prefs[PrefsKeys.REFRESH_TOKEN] = refreshToken
            prefs[PrefsKeys.USER_ID] = userId
            if (!email.isNullOrBlank()) {
                prefs[PrefsKeys.USER_EMAIL] = email
            }
        }
    }

    private fun extractUserIdFromAccessToken(accessToken: String): String? {
        return decodeJwtPayload(accessToken)?.optString("sub")?.takeIf { it.isNotBlank() }
    }

    private fun extractUserEmailFromAccessToken(accessToken: String): String? {
        return decodeJwtPayload(accessToken)?.optString("email")?.takeIf { it.isNotBlank() }
    }

    private fun decodeJwtPayload(token: String): JSONObject? {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return null
            val payload = String(
                Base64.decode(parts[1], Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP),
                Charsets.UTF_8
            )
            JSONObject(payload)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Get addons JSON from profile
     */
    fun getAddonsFromProfile(): String? {
        return _userProfile.value?.addons
    }

    /**
     * Load addons directly from Supabase to avoid stale in-memory profile state.
     */
    suspend fun getAddonsFromProfileFresh(): Result<String?> {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            return Result.success(_userProfile.value?.addons)
        }
        return try {
            val session = ensureValidSession()
            if (session == null) return Result.failure(Exception("Session expired"))

            @Serializable
            data class AddonsProjection(val addons: String? = null, val email: String? = null)

            val row = supabase.postgrest
                .from("profiles")
                .select {
                    filter { eq("id", userId) }
                }
                .decodeSingleOrNull<AddonsProjection>()

            val current = _userProfile.value
            val resolvedEmail = current?.email
                ?: (authState.value as? AuthState.Authenticated)?.email
                ?: row?.email
                ?: ""

            _userProfile.value = (current ?: UserProfile(id = userId, email = resolvedEmail)).copy(
                id = userId,
                email = resolvedEmail,
                addons = row?.addons
            )

            Result.success(row?.addons)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Save addons to Supabase profile
     */
    suspend fun saveAddonsToProfile(addonsJson: String): Result<Unit> {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            val currentProfile = _userProfile.value
            val resolvedEmail = currentProfile?.email
                ?: (authState.value as? AuthState.Authenticated)?.email
                ?: ""
            _userProfile.value = (currentProfile ?: UserProfile(id = userId, email = resolvedEmail)).copy(
                id = userId,
                email = resolvedEmail,
                addons = addonsJson
            )
            return Result.success(Unit)
        }
        return try {
            val session = ensureValidSession()
            if (session == null) return Result.failure(Exception("Session expired"))

            @Serializable
            data class AddonsUpdate(val addons: String)

            supabase.postgrest
                .from("profiles")
                .update(AddonsUpdate(addons = addonsJson)) {
                    filter { eq("id", userId) }
                }

            val currentProfile = _userProfile.value
            val resolvedEmail = currentProfile?.email
                ?: (authState.value as? AuthState.Authenticated)?.email
                ?: ""
            _userProfile.value = (currentProfile ?: UserProfile(id = userId, email = resolvedEmail)).copy(
                id = userId,
                email = resolvedEmail,
                addons = addonsJson
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get default subtitle from profile
     */
    fun getDefaultSubtitleFromProfile(): String? {
        return _userProfile.value?.default_subtitle
    }

    /**
     * Save default subtitle to Supabase profile
     */
    suspend fun saveDefaultSubtitleToProfile(subtitle: String?): Result<Unit> {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
        val currentProfile = _userProfile.value ?: return Result.failure(Exception("No profile"))

        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            _userProfile.value = currentProfile.copy(default_subtitle = subtitle)
            return Result.success(Unit)
        }

        return try {
            ensureValidSession()
            @Serializable
            data class SubtitleUpdate(val default_subtitle: String?)

            supabase.postgrest
                .from("profiles")
                .update(SubtitleUpdate(default_subtitle = subtitle)) {
                    filter { eq("id", userId) }
                }

            _userProfile.value = currentProfile.copy(default_subtitle = subtitle)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get auto play next from profile
     */
    fun getAutoPlayNextFromProfile(): Boolean? {
        return _userProfile.value?.auto_play_next
    }

    /**
     * Save auto play next to Supabase profile
     */
    suspend fun saveAutoPlayNextToProfile(autoPlayNext: Boolean): Result<Unit> {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
        val currentProfile = _userProfile.value ?: return Result.failure(Exception("No profile"))

        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            _userProfile.value = currentProfile.copy(auto_play_next = autoPlayNext)
            return Result.success(Unit)
        }

        return try {
            ensureValidSession()
            @Serializable
            data class AutoPlayUpdate(val auto_play_next: Boolean)

            supabase.postgrest
                .from("profiles")
                .update(AutoPlayUpdate(auto_play_next = autoPlayNext)) {
                    filter { eq("id", userId) }
                }

            _userProfile.value = currentProfile.copy(auto_play_next = autoPlayNext)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loadAccountSyncPayload(): Result<String?> {
        val userId = getCurrentUserIdForSync() ?: return Result.failure(Exception("Not logged in"))
        val netlifyResult = if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            loadAccountSyncPayloadFromNetlify()
        } else {
            Result.success(null)
        }
        val netlifyPayload = netlifyResult.getOrNull()?.payload
        val shouldCheckSupabaseFallback = !Constants.USE_NETLIFY_CLOUD_SYNC && (
            netlifyPayload.isNullOrBlank() ||
            accountSyncPayloadRestoreRank(netlifyPayload) < 70
        )

        val accountSyncResult = if (shouldCheckSupabaseFallback) {
            loadAccountSyncPayloadFromAccountSyncState(userId)
        } else {
            Result.success(null)
        }
        val userSettingsResult = if (shouldCheckSupabaseFallback) {
            loadAccountSyncPayloadFromUserSettings()
        } else {
            Result.success(null)
        }
        val profileResult = if (shouldCheckSupabaseFallback) {
            loadAccountSyncPayloadFromProfileAddons()
        } else {
            Result.success(null)
        }

        val bestPayload = listOf(netlifyResult, accountSyncResult, userSettingsResult, profileResult)
            .mapNotNull { it.getOrNull() }
            .filter { it.payload.isNotBlank() }
            .maxWithOrNull(
                compareBy<AccountSyncPayloadCandidate> { accountSyncPayloadRestoreRank(it.payload) }
                    .thenBy { accountSyncPayloadProfileCount(it.payload) ?: -1 }
                    .thenBy { accountSyncPayloadScopedCoverage(it.payload) }
                    .thenBy { it.updatedAtMillis }
            )

        if (bestPayload != null) {
            Log.i(
                TAG,
                "Selected account sync payload source=${bestPayload.source} " +
                    "rank=${accountSyncPayloadRestoreRank(bestPayload.payload)} " +
                    "profiles=${accountSyncPayloadProfileCount(bestPayload.payload)} " +
                    "coverage=${accountSyncPayloadScopedCoverage(bestPayload.payload)} " +
                    "updated=${bestPayload.updatedAtMillis}"
            )
            val canonicalSource = if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                ACCOUNT_SYNC_SOURCE_NETLIFY
            } else {
                ACCOUNT_SYNC_SOURCE_PRIMARY
            }
            if (bestPayload.source != canonicalSource) {
                runCatching { saveAccountSyncPayload(bestPayload.payload) }
                    .onSuccess { result ->
                        if (result.isFailure) {
                            AppLogger.recordException(
                                throwable = result.exceptionOrNull() ?: IllegalStateException("Canonical account sync save failed"),
                                context = mapOf(
                                    "error_area" to "CloudSync",
                                    "cloud_flow" to "canonicalize_account_sync_payload",
                                    "source" to bestPayload.source
                                )
                            )
                        }
                    }
                    .onFailure {
                        AppLogger.recordException(
                            throwable = it,
                            context = mapOf(
                                "error_area" to "CloudSync",
                                "cloud_flow" to "canonicalize_account_sync_payload",
                                "payload_source" to bestPayload.source
                            )
                        )
                    }
            }
            return Result.success(bestPayload.payload)
        }

        if (netlifyResult.isSuccess || accountSyncResult.isSuccess || userSettingsResult.isSuccess || profileResult.isSuccess) {
            return Result.success(null)
        }

        return Result.failure(
            netlifyResult.exceptionOrNull()
                ?: accountSyncResult.exceptionOrNull()
                ?: userSettingsResult.exceptionOrNull()
                ?: profileResult.exceptionOrNull()
                ?: IllegalStateException("Cloud sync payload unavailable")
        )
    }

    private suspend fun loadAccountSyncPayloadFromNetlify(): Result<AccountSyncPayloadCandidate?> {
        return runCatching {
            val responseBody = callNetlifyFunction(
                url = Constants.NETLIFY_ACCOUNT_SYNC_PULL_URL,
                body = JSONObject().toString()
            )
            val json = JSONObject(responseBody)
            val payloadValue = json.opt("payload") ?: return@runCatching null
            if (payloadValue == JSONObject.NULL) return@runCatching null
            val payload = when (payloadValue) {
                is JSONObject -> payloadValue.toString()
                is String -> payloadValue
                else -> payloadValue.toString()
            }.takeIf { it.isNotBlank() && it != "null" } ?: return@runCatching null

            AccountSyncPayloadCandidate(
                source = ACCOUNT_SYNC_SOURCE_NETLIFY,
                payload = payload,
                updatedAtMillis = maxOf(
                    payloadUpdatedAtMillis(payload),
                    parseInstantMillis(json.optString("updatedAt").takeIf { it.isNotBlank() }),
                    parseInstantMillis(json.optString("payloadUpdatedAt").takeIf { it.isNotBlank() })
                )
            )
        }
    }

    private suspend fun loadAccountSyncPayloadFromAccountSyncState(userId: String): Result<AccountSyncPayloadCandidate?> {
        return runCatching {
            ensureValidSession()
            val row = supabase.postgrest
                .from("account_sync_state")
                .select {
                    filter { eq("user_id", userId) }
                }
                .decodeSingleOrNull<AccountSyncStateRow>()
            val payload = row?.payload?.takeIf { it.isNotBlank() } ?: return@runCatching null
            AccountSyncPayloadCandidate(
                source = ACCOUNT_SYNC_SOURCE_PRIMARY,
                payload = payload,
                updatedAtMillis = maxOf(payloadUpdatedAtMillis(payload), parseInstantMillis(row.updated_at))
            )
        }
    }

    suspend fun saveAccountSyncPayload(payload: String): Result<Unit> {
        val userId = getCurrentUserIdForSync() ?: return Result.failure(Exception("Not logged in"))
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            val netlifyResult = saveAccountSyncPayloadToNetlify(payload)
            if (netlifyResult.isSuccess) {
                if (com.arflix.tv.BuildConfig.ENABLE_SUPABASE_SYNC_MIRROR) {
                    runCatching { saveAccountSyncPayloadViaRpc(userId, payload) }
                }
                return Result.success(Unit)
            }
            val netlifyException = netlifyResult.exceptionOrNull()
            if (netlifyException is AccountSyncPayloadRejectedException) {
                return Result.failure(netlifyException)
            }
            if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                return Result.failure(netlifyException ?: Exception("Netlify cloud sync upload failed"))
            }
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "netlify_save_failed_falling_back_to_supabase",
                severity = "warning"
            )
        }

        val rpcResult = saveAccountSyncPayloadViaRpc(userId, payload)
        if (rpcResult.isSuccess) {
            val profileAddonsResult = saveAccountSyncPayloadToProfileAddons(userId, payload)
            if (profileAddonsResult.isFailure) {
                AppLogger.breadcrumb(
                    tag = "CloudSync",
                    message = "rpc_saved_profile_addons_mirror_failed",
                    severity = "warning"
                )
            }
            return Result.success(Unit)
        }

        val rpcException = rpcResult.exceptionOrNull()
        if (rpcException is AccountSyncPayloadRejectedException) {
            AppLogger.breadcrumb(
                tag = "CloudSync",
                message = "rpc_rejected_weaker_snapshot_no_fallback",
                severity = "warning"
            )
            return Result.failure(rpcException)
        }

        val accountSyncResult = saveAccountSyncPayloadToAccountSyncState(userId, payload)
        val userSettingsResult = saveAccountSyncPayloadToUserSettings(userId, payload)
        val profileAddonsResult = saveAccountSyncPayloadToProfileAddons(userId, payload)

        if (accountSyncResult.isSuccess) {
            if (userSettingsResult.isFailure || profileAddonsResult.isFailure) {
                AppLogger.breadcrumb(
                    tag = "CloudSync",
                    message = "primary_saved_mirror_failed user_settings=${userSettingsResult.isFailure} profile_addons=${profileAddonsResult.isFailure}",
                    severity = "warning"
                )
            }
            return Result.success(Unit)
        }
        AppLogger.breadcrumb(
            tag = "CloudSync",
            message = "primary_save_failed fallback_user_settings=${userSettingsResult.isSuccess} fallback_profile_addons=${profileAddonsResult.isSuccess}",
            severity = "warning"
        )
        Log.w(
            TAG,
            "Primary account sync save failed; fallback_user_settings=${userSettingsResult.isSuccess} " +
                "fallback_profile_addons=${profileAddonsResult.isSuccess}",
            accountSyncResult.exceptionOrNull()
        )

        if (
            accountSyncPayloadSaveSucceeded(
                accountSyncSaved = false,
                userSettingsSaved = userSettingsResult.isSuccess,
                profileAddonsSaved = profileAddonsResult.isSuccess
            )
        ) {
            return Result.success(Unit)
        }

        return Result.failure(
            rpcResult.exceptionOrNull()
                ?: accountSyncResult.exceptionOrNull()
                ?: userSettingsResult.exceptionOrNull()
                ?: profileAddonsResult.exceptionOrNull()
                ?: Exception("Cloud sync save failed")
        )
    }

    private suspend fun saveAccountSyncPayloadToNetlify(payload: String): Result<Unit> {
        return try {
            val payloadValue = runCatching { JSONObject(payload) }.getOrNull() ?: payload
            val body = JSONObject()
                .put("payload", payloadValue)
                .toString()
            val responseBody = callNetlifyFunction(
                url = Constants.NETLIFY_ACCOUNT_SYNC_PUSH_URL,
                body = body
            )
            val responseJson = runCatching { JSONObject(responseBody) }.getOrNull()
            if (responseJson?.optBoolean("accepted", true) == false) {
                val reason = responseJson.optString("reason", "existing_snapshot_is_richer")
                throw AccountSyncPayloadRejectedException("Cloud sync upload rejected: $reason")
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun saveAccountSyncPayloadViaRpc(userId: String, payload: String): Result<Unit> {
        return try {
            val session = ensureValidSession() ?: return Result.failure(Exception("Session expired"))
            withContext(Dispatchers.IO) {
                val body = JSONObject()
                    .put("p_payload", payload)
                    .toString()
                    .toRequestBody(jsonMediaType)
                val request = Request.Builder()
                    .url("${Constants.SUPABASE_URL}/rest/v1/rpc/save_account_sync_payload")
                    .header("apikey", Constants.SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer ${session.accessToken}")
                    .header("Cache-Control", "no-cache, no-store")
                    .post(body)
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    val responseBody = response.body?.string().orEmpty()
                    if (!response.isSuccessful) {
                        throw IllegalStateException(
                            "Cloud sync upload failed (${response.code}): ${safePostgrestError(responseBody)}"
                        )
                    }
                    val rpcJson = runCatching { JSONObject(responseBody) }.getOrNull()
                    if (rpcJson?.optBoolean("accepted", true) == false) {
                        val reason = rpcJson.optString("reason", "existing_snapshot_is_richer")
                        throw AccountSyncPayloadRejectedException("Cloud sync upload rejected: $reason")
                    }
                }
            }

            val savedPayload = loadAccountSyncPayloadFromAccountSyncState(userId)
                .getOrNull()
                ?.payload
            if (!accountSyncPayloadsMatch(expected = payload, actual = savedPayload)) {
                return Result.failure(Exception("Cloud sync upload was not saved"))
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAccountSyncEventCursor(): Result<Long> {
        return runCatching {
            if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                val response = callNetlifyFunction(
                    url = Constants.NETLIFY_ACCOUNT_SYNC_CURSOR_URL,
                    body = JSONObject().toString()
                )
                return@runCatching JSONObject(response).optLong("cursor", 0L)
            }
            val body = JSONObject().toString()
            val response = callSupabaseRpc("account_sync_event_cursor", body)
            response.trim().trim('"').toLongOrNull()
                ?: JSONArray(response).optLong(0, 0L)
        }
    }

    suspend fun pushAccountSyncItems(itemsJson: String): Result<String> {
        return runCatching {
            if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                return@runCatching JSONObject()
                    .put("accepted", true)
                    .put("events", JSONArray())
                    .toString()
            }
            val items = JSONArray(itemsJson)
            val body = JSONObject()
                .put("p_items", items)
                .toString()
            callSupabaseRpc("push_account_sync_items", body)
        }
    }

    suspend fun pullAccountSyncDelta(sinceEventId: Long, limit: Int = 500): Result<String> {
        return runCatching {
            if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                val body = JSONObject()
                    .put("sinceEventId", sinceEventId.coerceAtLeast(0L))
                    .put("limit", limit.coerceIn(1, 1000))
                    .toString()
                return@runCatching callNetlifyFunction(
                    url = Constants.NETLIFY_ACCOUNT_SYNC_DELTA_URL,
                    body = body
                )
            }
            val body = JSONObject()
                .put("p_since_event_id", sinceEventId.coerceAtLeast(0L))
                .put("p_limit", limit.coerceIn(1, 1000))
                .toString()
            callSupabaseRpc("pull_account_sync_delta", body)
        }
    }

    suspend fun pullAccountSyncItems(
        scope: String? = null,
        profileId: String? = null,
        limit: Int = 1000
    ): Result<String> {
        return runCatching {
            if (Constants.USE_NETLIFY_CLOUD_SYNC) {
                return@runCatching JSONArray().toString()
            }
            val body = JSONObject()
                .put("p_scope", scope?.takeIf { it.isNotBlank() } ?: JSONObject.NULL)
                .put("p_profile_id", profileId?.takeIf { it.isNotBlank() } ?: JSONObject.NULL)
                .put("p_limit", limit.coerceIn(1, 5000))
                .toString()
            callSupabaseRpc("pull_account_sync_items", body)
        }
    }

    private suspend fun callNetlifyFunction(url: String, body: String): String {
        val accessToken = getAccessToken()?.takeIf { it.isNotBlank() }
            ?: throw IllegalStateException("Session expired")
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .header("Authorization", "Bearer $accessToken")
                .header("Cache-Control", "no-cache, no-store")
                .post(body.toRequestBody(jsonMediaType))
                .build()

            okHttpClient.newCall(request).execute().use { response ->
                val responseBody = response.body?.string().orEmpty()
                if (!response.isSuccessful) {
                    throw IllegalStateException(
                        "Netlify cloud sync failed (${response.code}): ${safePostgrestError(responseBody)}"
                    )
                }
                responseBody
            }
        }
    }

    private fun cloudAuthErrorMessage(json: JSONObject?, defaultError: String): String {
        if (json == null) return defaultError
        val code = json.optString("code")
        val message = json.optString("error").takeIf { it.isNotBlank() } ?: defaultError
        if (code == "password_setup_required") {
            val setupError = json.optString("setup_error").takeIf { it.isNotBlank() && it != "null" }
            return if (json.optBoolean("email_sent", false)) {
                "$message Check your email to create the new password."
            } else if (!setupError.isNullOrBlank()) {
                "$message Password setup email could not be sent: $setupError"
            } else {
                message
            }
        }
        return message
    }

    private suspend fun callSupabaseRpc(functionName: String, body: String): String {
        val session = ensureValidSession() ?: throw IllegalStateException("Session expired")
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url("${Constants.SUPABASE_URL}/rest/v1/rpc/$functionName")
                .header("apikey", Constants.SUPABASE_ANON_KEY)
                .header("Authorization", "Bearer ${session.accessToken}")
                .header("Cache-Control", "no-cache, no-store")
                .post(body.toRequestBody(jsonMediaType))
                .build()

            okHttpClient.newCall(request).execute().use { response ->
                val responseBody = response.body?.string().orEmpty()
                if (!response.isSuccessful) {
                    throw IllegalStateException(
                        "$functionName failed (${response.code}): ${safePostgrestError(responseBody)}"
                    )
                }
                responseBody
            }
        }
    }

    private suspend fun saveAccountSyncPayloadToAccountSyncState(userId: String, payload: String): Result<Unit> {
        return try {
            ensureValidSession()
            val updatedAt = Clock.System.now().toString()
            val existing = supabase.postgrest
                .from("account_sync_state")
                .select {
                    filter { eq("user_id", userId) }
                }
                .decodeSingleOrNull<AccountSyncStateRow>()

            if (existing != null) {
                supabase.postgrest
                    .from("account_sync_state")
                    .update(
                        mapOf(
                            "payload" to payload,
                            "updated_at" to updatedAt
                        )
                    ) {
                        filter { eq("user_id", userId) }
                    }
            } else {
                supabase.postgrest
                    .from("account_sync_state")
                    .insert(
                        mapOf(
                            "user_id" to userId,
                            "payload" to payload,
                            "updated_at" to updatedAt
                        )
                    )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun loadAccountSyncPayloadFromUserSettings(): Result<AccountSyncPayloadCandidate?> {
        val userId = getCurrentUserIdForSync() ?: return Result.failure(Exception("Not logged in"))
        return try {
            ensureValidSession()
            val row = supabase.postgrest
                .from("user_settings")
                .select {
                    filter { eq("user_id", userId) }
                }
                .decodeSingleOrNull<UserSettingsAccountSyncRow>()
            val payload = row?.settings
                ?.get(ACCOUNT_SYNC_PAYLOAD_KEY)
                ?.jsonPrimitive
                ?.contentOrNull
                ?.takeIf { it.isNotBlank() }
            val updatedAt = row?.settings
                ?.get(ACCOUNT_SYNC_UPDATED_AT_KEY)
                ?.jsonPrimitive
                ?.contentOrNull
            Result.success(
                payload?.let {
                    AccountSyncPayloadCandidate(
                        source = ACCOUNT_SYNC_SOURCE_USER_SETTINGS,
                        payload = it,
                        updatedAtMillis = maxOf(payloadUpdatedAtMillis(it), parseInstantMillis(updatedAt))
                    )
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun saveAccountSyncPayloadToUserSettings(userId: String, payload: String): Result<Unit> {
        return try {
            ensureValidSession()
            val existingSettings = runCatching {
                supabase.postgrest
                    .from("user_settings")
                    .select {
                        filter { eq("user_id", userId) }
                    }
                    .decodeSingleOrNull<UserSettingsAccountSyncRow>()
                    ?.settings
            }.getOrNull()

            val updatedSettings = buildJsonObject {
                existingSettings?.forEach { (key, value) -> put(key, value) }
                put(ACCOUNT_SYNC_PAYLOAD_KEY, JsonPrimitive(payload))
                put(ACCOUNT_SYNC_UPDATED_AT_KEY, JsonPrimitive(Clock.System.now().toString()))
            }

            if (existingSettings != null) {
                supabase.postgrest
                    .from("user_settings")
                    .update(UserSettingsSettingsUpdate(settings = updatedSettings)) {
                        filter { eq("user_id", userId) }
                    }
            } else {
                supabase.postgrest
                    .from("user_settings")
                    .insert(UserSettingsAccountSyncUpdate(user_id = userId, settings = updatedSettings))
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun loadAccountSyncPayloadFromProfileAddons(): Result<AccountSyncPayloadCandidate?> {
        val userId = getCurrentUserIdForSync() ?: return Result.failure(Exception("Not logged in"))
        return try {
            ensureValidSession()
            val row = supabase.postgrest
                .from("profiles")
                .select {
                    filter { eq("id", userId) }
                }
                .decodeSingleOrNull<ProfileAccountSyncRow>()
            val payload = decodeProfileAccountSyncPayload(row?.addons)?.takeIf { it.isNotBlank() }
            Result.success(
                payload?.let {
                    AccountSyncPayloadCandidate(
                        source = ACCOUNT_SYNC_SOURCE_PROFILE_ADDONS,
                        payload = it,
                        updatedAtMillis = maxOf(payloadUpdatedAtMillis(it), decodeProfileAccountSyncUpdatedAt(row?.addons))
                    )
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun saveAccountSyncPayloadToProfileAddons(userId: String, payload: String): Result<Unit> {
        return try {
            ensureValidSession()
            val existingAddons = runCatching {
                supabase.postgrest
                    .from("profiles")
                    .select {
                        filter { eq("id", userId) }
                    }
                    .decodeSingleOrNull<ProfileAccountSyncRow>()
                    ?.addons
            }.getOrNull()

            val encoded = encodeProfileAccountSyncPayload(existingAddons, payload)
            supabase.postgrest
                .from("profiles")
                .update(ProfileAccountSyncUpdate(addons = encoded)) {
                    filter { eq("id", userId) }
                }

            val currentProfile = _userProfile.value
            val resolvedEmail = currentProfile?.email
                ?: (authState.value as? AuthState.Authenticated)?.email
                ?: ""
            _userProfile.value = (currentProfile ?: UserProfile(id = userId, email = resolvedEmail)).copy(
                id = userId,
                email = resolvedEmail,
                addons = encoded
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun decodeProfileAccountSyncPayload(raw: String?): String? {
        if (raw.isNullOrBlank()) return null
        return runCatching {
            val obj = JSONObject(raw)
            obj.optString(PROFILE_SYNC_PAYLOAD_KEY).takeIf { it.isNotBlank() }
        }.getOrNull()
    }

    private fun decodeProfileAccountSyncUpdatedAt(raw: String?): Long {
        if (raw.isNullOrBlank()) return 0L
        return runCatching {
            val obj = JSONObject(raw)
            parseInstantMillis(obj.optString(PROFILE_SYNC_UPDATED_AT_KEY))
        }.getOrDefault(0L)
    }

    private fun payloadUpdatedAtMillis(payload: String?): Long {
        if (payload.isNullOrBlank()) return 0L
        return runCatching {
            JSONObject(payload).optLong("updatedAt", 0L)
        }.getOrDefault(0L)
    }

    private fun parseInstantMillis(value: String?): Long {
        if (value.isNullOrBlank()) return 0L
        return runCatching { Instant.parse(value).toEpochMilliseconds() }.getOrDefault(0L)
    }

    private fun encodeProfileAccountSyncPayload(existingAddons: String?, payload: String): String {
        val existing = existingAddons.orEmpty()
        val legacyAddons = if (
            existing.isNotBlank() &&
            decodeProfileAccountSyncPayload(existing) == null
        ) {
            existing
        } else {
            runCatching {
                JSONObject(existing).optString(PROFILE_SYNC_LEGACY_ADDONS_KEY)
            }.getOrNull().orEmpty()
        }
        return JSONObject().apply {
            put(PROFILE_SYNC_PAYLOAD_KEY, payload)
            put(PROFILE_SYNC_UPDATED_AT_KEY, Clock.System.now().toString())
            if (legacyAddons.isNotBlank()) put(PROFILE_SYNC_LEGACY_ADDONS_KEY, legacyAddons)
        }.toString()
    }

    suspend fun mutateAccountSyncPayload(mutator: (JSONObject) -> Unit): Result<Unit> {
        return accountSyncMutationMutex.withLock {
            val userId = getCurrentUserIdForSync() ?: return@withLock Result.failure(Exception("Not logged in"))
            val existingPayload = loadAccountSyncPayload().getOrNull().orEmpty()
            val root = if (existingPayload.isBlank()) {
                JSONObject()
            } else {
                runCatching { JSONObject(existingPayload) }.getOrElse { JSONObject() }
            }

            root.put("version", root.optInt("version", 1))
            root.put("userId", userId)
            mutator(root)
            root.put("updatedAt", System.currentTimeMillis())

            saveAccountSyncPayload(root.toString())
        }
    }
}
