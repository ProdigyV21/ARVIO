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
import androidx.datastore.preferences.preferencesDataStore
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
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
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.datetime.Clock
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

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
    private val traktRepositoryProvider: Provider<TraktRepository>
) {
    private val TAG = "AuthRepository"

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
        AppLogger.d(TAG, "=== checkAuthState started ===")
        try {
            val prefs = context.authDataStore.data.first()
            val accessToken = prefs[PrefsKeys.ACCESS_TOKEN]
            val refreshToken = prefs[PrefsKeys.REFRESH_TOKEN]
            val cachedUserId = prefs[PrefsKeys.USER_ID]
            val cachedEmail = prefs[PrefsKeys.USER_EMAIL]

            val hasAccessToken = !accessToken.isNullOrBlank()
            val hasRefreshToken = !refreshToken.isNullOrBlank()

            AppLogger.d(TAG, "Cached data: hasAccessToken=$hasAccessToken, hasRefreshToken=$hasRefreshToken, hasUserId=${!cachedUserId.isNullOrBlank()}, hasEmail=${!cachedEmail.isNullOrBlank()}")

            var session: UserSession? = null

            // Supabase SDK requires main thread for initialization (lifecycle observers)
            // First try: Load from SessionManager via Supabase SDK
            try {
                AppLogger.d(TAG, "Attempting to load session from storage via Supabase SDK...")
                session = withContext(Dispatchers.Main) {
                    supabase.auth.loadFromStorage(true)
                    supabase.auth.currentSessionOrNull()
                }
                if (session != null) {
                    AppLogger.d(TAG, "Session loaded from SessionManager for user: ${session.user?.email?.sanitizeEmail()}")
                } else {
                    AppLogger.d(TAG, "No session found in SessionManager")
                }
            } catch (e: Exception) {
                AppLogger.w(TAG, "Failed to load session from storage: ${e.message}", e)
            }

            // Second try: Import from cached tokens
            if (session == null && hasAccessToken && hasRefreshToken) {
                try {
                    AppLogger.d(TAG, "Attempting to import auth tokens...")
                    session = withContext(Dispatchers.Main) {
                        supabase.auth.importAuthToken(accessToken ?: "", refreshToken ?: "", false, true)
                        supabase.auth.currentSessionOrNull()
                    }
                    if (session != null) {
                        AppLogger.d(TAG, "Session imported from cached tokens")
                        // Save the imported session to SessionManager
                        storeSession(session)
                    }
                } catch (e: Exception) {
                    AppLogger.w(TAG, "Failed to import auth tokens: ${e.message}", e)
                }
            }

            // Third try: Refresh the session
            if (session == null && hasRefreshToken) {
                AppLogger.d(TAG, "Attempting to refresh session...")
                session = withContext(Dispatchers.Main) {
                    ensureValidSession()
                }
                if (session != null) {
                    AppLogger.d(TAG, "Session refreshed successfully")
                }
            }

            AppLogger.d(TAG, "Final session state: session=${if (session != null) "present" else "null"}")

            if (hasAccessToken || hasRefreshToken || session != null || !cachedUserId.isNullOrBlank()) {
                val userId = session?.user?.id ?: cachedUserId
                val email = session?.user?.email ?: cachedEmail

                if (session != null && userId != null && email != null) {
                    storeSession(session)
                    // Load profile
                    val profile = loadUserProfile(userId)
                    _userProfile.value = profile
                    _authState.value = AuthState.Authenticated(userId, email, profile)
                    AppLogger.d(TAG, "=== Auth restored successfully: ${email.sanitizeEmail()} ===")
                } else if (userId != null && email != null) {
                    // Fallback to cached identity even if refresh failed (avoid forcing daily logins).
                    val profile = try {
                        loadUserProfile(userId)
                    } catch (_: Exception) {
                        null
                    } ?: UserProfile(id = userId, email = email)
                    traktRepositoryProvider.get().setUserId(userId)
                    traktRepositoryProvider.get().syncLocalTokensToProfileIfNeeded()
                    _userProfile.value = profile
                    _authState.value = AuthState.Authenticated(userId, email, profile)
                    AppLogger.w(TAG, "Using cached identity for ${email.sanitizeEmail()}")
                } else {
                    AppLogger.e(TAG, "=== Session refresh failed, user not authenticated ===")
                    _authState.value = AuthState.NotAuthenticated
                }
            } else {
                if (!cachedUserId.isNullOrBlank() && !cachedEmail.isNullOrBlank()) {
                    _userProfile.value = UserProfile(id = cachedUserId, email = cachedEmail)
                    _authState.value = AuthState.Authenticated(cachedUserId, cachedEmail, _userProfile.value)
                    AppLogger.w(TAG, "=== Using cached user without tokens for ${cachedEmail.sanitizeEmail()} ===")
                } else {
                    AppLogger.d(TAG, "=== No auth data found, user not authenticated ===")
                    _authState.value = AuthState.NotAuthenticated
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "=== Error checking auth state: ${e.message} ===", e)
            _authState.value = AuthState.NotAuthenticated
        }
    }
    
    /**
     * Sign in with email and password
     */
    suspend fun signIn(email: String, password: String): Result<Unit> {
        AppLogger.d(TAG, "=== signIn started for: ${email.sanitizeEmail()} ===")
        return try {
            _authState.value = AuthState.Loading

            supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            val session = supabase.auth.currentSessionOrNull()
            val user = session?.user

            if (user != null && session != null) {
                AppLogger.d(TAG, "Sign in successful, storing session...")
                storeSession(session)

                // Load or create profile
                var profile = loadUserProfile(user.id)
                if (profile == null) {
                    profile = createDefaultProfile(user.id, user.email ?: email)
                }

                _userProfile.value = profile
                _authState.value = AuthState.Authenticated(user.id, user.email ?: email, profile)
                AppLogger.d(TAG, "=== Sign in complete: ${user.email?.sanitizeEmail()} ===")
                Result.success(Unit)
            } else {
                val message = safeErrorMessage(null, "Sign in failed")
                AppLogger.e(TAG, "Sign in failed: no user in session")
                _authState.value = AuthState.Error(message)
                Result.failure(Exception(message))
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "=== Sign in error: ${e.message} ===", e)
            val message = safeErrorMessage(e, "Sign in failed")
            _authState.value = AuthState.Error(message)
            Result.failure(Exception(message))
        }
    }

    /**
     * Sign up with email and password
     */
    suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            _authState.value = AuthState.Loading
            
            supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            
            val session = supabase.auth.currentSessionOrNull()
            val user = session?.user
            
            if (user != null) {
                storeSession(session)
                
                // Create profile
                val profile = createDefaultProfile(user.id, user.email ?: email)
                _userProfile.value = profile
                _authState.value = AuthState.Authenticated(user.id, user.email ?: email, profile)
                AppLogger.d(TAG, "Sign up successful: ${user.email?.sanitizeEmail()}")
                Result.success(Unit)
            } else {
                // Account created but needs email verification
                val message = "Please check your email to verify your account"
                _authState.value = AuthState.Error(message)
                Result.failure(Exception(message))
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Sign up error", e)
            val message = safeErrorMessage(e, "Sign up failed")
            _authState.value = AuthState.Error(message)
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
                            AppLogger.d(TAG, "Google Sign-In successful: ${user.email?.sanitizeEmail()}")
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
            AppLogger.e(TAG, "Google ID token parsing error", e)
            _authState.value = AuthState.Error("Failed to parse Google credentials")
            Result.failure(e)
        } catch (e: Exception) {
            AppLogger.e(TAG, "Google Sign-In error", e)
            _authState.value = AuthState.Error(e.message ?: "Google Sign-In failed")
            Result.failure(e)
        }
    }

    /**
     * Sign out
     */
    suspend fun signOut() {
        try {
            supabase.auth.signOut()
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error signing out from Supabase", e)
        }

        try {
            traktRepositoryProvider.get().logout()
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error clearing Trakt session", e)
        }

        // Clear local data
        context.authDataStore.edit { prefs ->
            prefs.clear()
        }

        _userProfile.value = null
        _authState.value = AuthState.NotAuthenticated
        AppLogger.d(TAG, "Signed out")
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

                AppLogger.d(TAG, "Loaded profile for ${userId.hash()}")
            }

            result
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error loading profile", e)
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

            AppLogger.d(TAG, "Created default profile for ${userId.hash()}")
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error creating profile", e)
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
            AppLogger.d(TAG, "Profile updated")
            Result.success(Unit)
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error updating profile", e)
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
        return if (!cached.isNullOrBlank() && !isJwtExpired(cached)) cached else null
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
            AppLogger.e(TAG, "Failed to refresh Supabase session", e)
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
                AppLogger.w(TAG, "Failed to load session before refresh", e)
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
            AppLogger.e(TAG, "Failed to refresh Supabase session", e)
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
                AppLogger.w(TAG, "JWT token missing exp claim - rejecting as expired")
                return true
            }
            val exp = json.getLong("exp")
            if (exp <= 0L) {
                AppLogger.w(TAG, "JWT token has invalid exp claim ($exp) - rejecting as expired")
                return true
            }
            val now = Clock.System.now().epochSeconds
            exp <= now + bufferSeconds
        } catch (e: Exception) {
            AppLogger.w(TAG, "Failed to parse JWT token: ${e.message}")
            true
        }
    }

    private suspend fun storeSession(session: UserSession) {
        AppLogger.d(TAG, "Storing session for user: ${session.user?.email?.sanitizeEmail()}")

        // 1. Explicitly save through session manager (for Supabase SDK auto-restore)
        try {
            sessionManager.saveSession(session)
            AppLogger.d(TAG, "Session saved to DataStoreSessionManager")
        } catch (e: Exception) {
            AppLogger.e(TAG, "Failed to save session to SessionManager", e)
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
        AppLogger.d(TAG, "Session tokens stored to DataStore")
    }

    /**
     * Get addons JSON from profile
     */
    fun getAddonsFromProfile(): String? {
        return _userProfile.value?.addons
    }

    /**
     * Save addons to Supabase profile
     */
    suspend fun saveAddonsToProfile(addonsJson: String): Result<Unit> {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("Not logged in"))
        val currentProfile = _userProfile.value ?: return Result.failure(Exception("No profile"))

        return try {
            ensureValidSession()
            // Create updated profile with new addons
            @Serializable
            data class AddonsUpdate(val addons: String)

            supabase.postgrest
                .from("profiles")
                .update(AddonsUpdate(addons = addonsJson)) {
                    filter { eq("id", userId) }
                }

            // Update local profile
            _userProfile.value = currentProfile.copy(addons = addonsJson)
            AppLogger.d(TAG, "Addons saved to Supabase")
            Result.success(Unit)
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error saving addons to Supabase", e)
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
            AppLogger.d(TAG, "Default subtitle saved to Supabase")
            Result.success(Unit)
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error saving subtitle to Supabase", e)
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
            AppLogger.d(TAG, "Auto play next saved to Supabase: $autoPlayNext")
            Result.success(Unit)
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error saving auto play next to Supabase", e)
            Result.failure(e)
        }
    }
}


