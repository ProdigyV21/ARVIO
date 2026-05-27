package com.arflix.tv.data.repository

import android.content.Context
import android.util.Base64
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
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
private data class ProfileAccountSyncRow(
    val addons: String? = null
)

@Serializable
private data class ProfileAccountSyncUpdate(
    val addons: String
)

private data class AccountSyncPayloadCandidate(
    val payload: String,
    val updatedAtMillis: Long
)

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
        AuthEmailValidator.validate(normalizedEmail, rejectDisposable = false)?.let { message ->
            _authState.value = AuthState.Error(message)
            return Result.failure(Exception(message))
        }
        return try {
            AppLogger.breadcrumb("Auth", "email_sign_in_start")
            _authState.value = AuthState.Loading

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
                val message = safeErrorMessage(null, "Sign in failed")
                _authState.value = AuthState.Error(message)
                AppLogger.breadcrumb("Auth", "email_sign_in_no_session", severity = "warning")
                Result.failure(Exception(message))
            }
        } catch (e: Exception) {
            val message = safeErrorMessage(e, "Sign in failed")
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
        AuthEmailValidator.validate(normalizedEmail)?.let { message ->
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
            val message = safeErrorMessage(e, "Sign up failed")
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

    private suspend fun createCloudAccountSession(email: String, password: String): CloudAccountSession {
        return withContext(Dispatchers.IO) {
            val payload = JSONObject()
                .put("email", email)
                .put("password", password)
                .toString()

            val request = Request.Builder()
                .url(Constants.CLOUD_AUTH_EMAIL_URL)
                .header("apikey", Constants.SUPABASE_ANON_KEY)
                .header("Authorization", "Bearer ${Constants.SUPABASE_ANON_KEY}")
                .post(payload.toRequestBody(jsonMediaType))
                .build()

            okHttpClient.newCall(request).execute().use { response ->
                val body = response.body?.string().orEmpty()
                val json = runCatching { JSONObject(body) }.getOrNull()
                if (!response.isSuccessful) {
                    val message = json?.optString("error")?.takeIf { it.isNotBlank() }
                        ?: "Unable to create account"
                    throw IllegalStateException(message)
                }

                val accessToken = json?.optString("access_token").orEmpty()
                val refreshToken = json?.optString("refresh_token").orEmpty()
                if (accessToken.isBlank() || refreshToken.isBlank()) {
                    throw IllegalStateException("Auth response incomplete")
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
            val session = withContext(Dispatchers.Main) {
                supabase.auth.importAuthToken(accessToken, refreshToken, false, true)
                supabase.auth.currentSessionOrNull() ?: run {
                    supabase.auth.loadFromStorage(true)
                    supabase.auth.currentSessionOrNull()
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
                var profile = loadUserProfile(resolvedUserId)
                if (profile == null) {
                    profile = createDefaultProfile(resolvedUserId, resolvedEmail ?: "")
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
                _authState.value = AuthState.Error("Failed to import auth session")
                AppLogger.breadcrumb("Auth", "session_import_missing_user", severity = "warning")
                Result.failure(Exception("Failed to import auth session"))
            }
        } catch (e: Exception) {
            val message = safeErrorMessage(e, "Sign in failed")
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
                            _authState.value = AuthState.Error("Google Sign-In failed")
                            Result.failure(Exception("Google Sign-In failed"))
                        }
                    } else {
                        _authState.value = AuthState.Error("Unexpected credential type")
                        Result.failure(Exception("Unexpected credential type"))
                    }
                }
                else -> {
                    _authState.value = AuthState.Error("Unexpected credential type")
                    Result.failure(Exception("Unexpected credential type"))
                }
            }
        } catch (e: GoogleIdTokenParsingException) {
            _authState.value = AuthState.Error("Failed to parse Google credentials")
            Result.failure(e)
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Google Sign-In failed")
            Result.failure(e)
        }
    }

    /**
     * Sign out
     */
    suspend fun signOut() {
        // Push final state before signing out
        try { cloudSyncRepositoryProvider.get().pushToCloud() } catch (_: Exception) {}

        try {
            supabase.auth.signOut()
        } catch (e: Exception) {
        }

        try {
            traktRepositoryProvider.get().logout()
        } catch (e: Exception) {
        }

        // Clear ALL local data (auth + settings + user preferences)
        context.authDataStore.edit { prefs -> prefs.clear() }
        context.settingsDataStore.edit { prefs -> prefs.clear() }

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
        val message = error?.message?.lowercase() ?: return fallback
        return when {
            "database error saving new user" in message -> "Account already exists. Sign in instead."
            "settingssessionmanager" in message -> "Sign in failed. Please try again."
            "invalid login credentials" in message -> "Invalid email or password."
            "email not confirmed" in message || "confirm" in message -> "Please verify your email to continue."
            "user already" in message || "already registered" in message -> "Account already exists. Sign in instead."
            else -> fallback
        }
    }
    
    /**
     * Update user profile
     */
    suspend fun updateProfile(profile: UserProfile): Result<Unit> {
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

    /**
     * Get Supabase access token for API calls
     */
    suspend fun getAccessToken(): String? {
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

        return try {
            val refreshed = supabase.auth.refreshSession(refreshToken)
            storeSession(refreshed)
            refreshed.accessToken
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun ensureValidSession(): UserSession? {
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
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
        val accountSyncResult = loadAccountSyncPayloadFromAccountSyncState(userId)
        val userSettingsResult = loadAccountSyncPayloadFromUserSettings()
        val profileResult = loadAccountSyncPayloadFromProfileAddons()

        val bestPayload = listOf(accountSyncResult, userSettingsResult, profileResult)
            .mapNotNull { it.getOrNull() }
            .filter { it.payload.isNotBlank() }
            .maxByOrNull { it.updatedAtMillis }

        if (bestPayload != null) {
            return Result.success(bestPayload.payload)
        }

        if (accountSyncResult.isSuccess || userSettingsResult.isSuccess || profileResult.isSuccess) {
            return Result.success(null)
        }

        return Result.failure(
            accountSyncResult.exceptionOrNull()
                ?: userSettingsResult.exceptionOrNull()
                ?: profileResult.exceptionOrNull()
                ?: IllegalStateException("Cloud sync payload unavailable")
        )
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
                payload = payload,
                updatedAtMillis = maxOf(payloadUpdatedAtMillis(payload), parseInstantMillis(row.updated_at))
            )
        }
    }

    suspend fun saveAccountSyncPayload(payload: String): Result<Unit> {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
        val accountSyncResult = runCatching {
            ensureValidSession()
            supabase.postgrest
                .from("account_sync_state")
                .upsert(
                    mapOf(
                        "user_id" to userId,
                        "payload" to payload,
                        "updated_at" to Clock.System.now().toString()
                    )
                )
        }
        if (accountSyncResult.isSuccess) return Result.success(Unit)

        val userSettingsResult = saveAccountSyncPayloadToUserSettings(userId, payload)
        if (userSettingsResult.isSuccess) return Result.success(Unit)

        return saveAccountSyncPayloadToProfileAddons(userId, payload)
            .recoverCatching {
                throw accountSyncResult.exceptionOrNull()
                    ?: userSettingsResult.exceptionOrNull()
                    ?: it
            }
    }

    private suspend fun loadAccountSyncPayloadFromUserSettings(): Result<AccountSyncPayloadCandidate?> {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
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

            supabase.postgrest
                .from("user_settings")
                .upsert(UserSettingsAccountSyncUpdate(user_id = userId, settings = updatedSettings))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun loadAccountSyncPayloadFromProfileAddons(): Result<AccountSyncPayloadCandidate?> {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
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
            val userId = getCurrentUserId() ?: return@withLock Result.failure(Exception("Not logged in"))
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


