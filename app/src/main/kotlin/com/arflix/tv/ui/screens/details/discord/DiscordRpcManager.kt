package com.arflix.tv.ui.screens.details.discord

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arflix.tv.BuildConfig
import com.arflix.tv.util.Constants
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.SecureRandom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.json.JSONObject

internal const val DISCORD_MOBILE_OAUTH_STATE_PREFIX = "mobile_"

internal fun isValidDiscordMobileOAuthState(storedState: String?, callbackState: String?): Boolean =
    !storedState.isNullOrBlank() &&
        storedState.startsWith(DISCORD_MOBILE_OAUTH_STATE_PREFIX) &&
        callbackState == storedState

object DiscordRpcManager {
    private const val TAG = "DiscordRpcManager"
    private const val PREFS_NAME = "discord_rpc_prefs"
    private const val KEY_ACCESS_TOKEN = "access_token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"
    private const val KEY_ACCESS_TOKEN_EXPIRES_AT = "access_token_expires_at"
    private const val KEY_CODE_VERIFIER = "code_verifier"
    private const val KEY_OAUTH_STATE = "oauth_state"
    private const val KEY_USERNAME = "username"
    private const val REDIRECT_URI_WEB = "https://auth.arvio.tv/discord/callback"
    private const val TOKEN_REFRESH_MARGIN_MS = 60_000L

    private val discordClientId: String
        get() = BuildConfig.DISCORD_APPLICATION_ID.trim()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val tokenMutex = Mutex()
    private var tickJob: Job? = null
    private var lastUpdateJob: Job? = null
    private var reconnectJob: Job? = null
    private var tokenRefreshJob: Job? = null
    private var disconnectTimeoutJob: Job? = null
    private var authPollingJob: Job? = null

    private var initialized = false
    private var initializationJob: Job? = null
    private var bridgeReady by mutableStateOf(false)
    @Volatile private var connectionState = ConnectionState.DISCONNECTED
    @Volatile private var currentAccessToken: String? = null
    @Volatile private var currentRefreshToken: String? = null
    @Volatile private var accessTokenExpiresAt = 0L
    private lateinit var appContext: Context

    val isSupported: Boolean
        get() = BuildConfig.DISCORD_RICH_PRESENCE_AVAILABLE &&
            discordClientId.matches(Regex("^\\d{17,20}$")) &&
            bridgeReady

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedInFlow: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _username = MutableStateFlow<String?>(null)
    val usernameFlow: StateFlow<String?> = _username.asStateFlow()

    private val _authUrl = MutableStateFlow<String?>(null)
    val authUrlFlow: StateFlow<String?> = _authUrl.asStateFlow()

    private val _isAuthDialogVisible = MutableStateFlow(false)
    val isAuthDialogVisibleFlow: StateFlow<Boolean> = _isAuthDialogVisible.asStateFlow()

    private val _isAuthLoading = MutableStateFlow(false)
    val isAuthLoadingFlow: StateFlow<Boolean> = _isAuthLoading.asStateFlow()

    private enum class ConnectionState {
        DISCONNECTED,
        CONNECTING,
        CONNECTED
    }

    private data class PairingSession(
        val deviceCode: String,
        val verificationUrl: String,
        val intervalSeconds: Long
    )

    private data class PairingStatus(
        val status: String,
        val code: String? = null
    )

    private data class OAuthTokens(
        val accessToken: String,
        val refreshToken: String?,
        val expiresAt: Long
    )

    private val jniCallback = object : DiscordBridge.Callback {
        override fun onStatusChanged(status: Int, error: Int, errorDetail: Int) {
            Log.i(TAG, "Discord status changed: status=$status, error=$error, detail=$errorDetail")
            when (status) {
                1 -> {
                    connectionState = ConnectionState.CONNECTED
                    startTickLoop()
                }
                0 -> {
                    connectionState = ConnectionState.DISCONNECTED
                    stopTickLoop()
                    if (errorDetail == 4004) {
                        coroutineScope.launch {
                            if (refreshCurrentToken() == null) logout()
                        }
                    }
                }
            }
        }
    }

    fun init(context: Context) {
        if (initialized) return
        appContext = context.applicationContext
        initialized = true

        if (!BuildConfig.DISCORD_RICH_PRESENCE_AVAILABLE ||
            !discordClientId.matches(Regex("^\\d{17,20}$"))
        ) {
            Log.i(TAG, "Discord Rich Presence is not included in this build.")
            return
        }

        val activity = java.lang.ref.WeakReference(context as? Activity)
        initializationJob = coroutineScope.launch {
            // SDK static initializers load native libraries. On some TVs this takes
            // seconds; never block MainActivity.onCreate or input dispatch on dlopen.
            val initClass = withContext(Dispatchers.IO) {
                try {
                    Class.forName("com.discord.socialsdk.DiscordSocialSdkInit").also {
                        DiscordBridge.isAvailable
                    }
                } catch (error: Exception) {
                    Log.e(TAG, "Failed to load Discord Social SDK", error)
                    null
                } catch (error: LinkageError) {
                    Log.e(TAG, "Discord Social SDK native library is unavailable", error)
                    null
                }
            } ?: return@launch

            activity.get()?.takeUnless { it.isFinishing || it.isDestroyed }?.let { currentActivity ->
                runCatching {
                    initClass.getMethod("setEngineActivity", Activity::class.java).invoke(null, currentActivity)
                }.onFailure { error ->
                    Log.e(TAG, "Failed to attach the Android activity to Discord Social SDK", error)
                }
            }

            bridgeReady = DiscordBridge.init(discordClientId, jniCallback)
            if (!bridgeReady) return@launch

            val prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            currentAccessToken = prefs.getString(KEY_ACCESS_TOKEN, null)
            currentRefreshToken = prefs.getString(KEY_REFRESH_TOKEN, null)
            accessTokenExpiresAt = prefs.getLong(KEY_ACCESS_TOKEN_EXPIRES_AT, 0L)
            _username.value = prefs.getString(KEY_USERNAME, null)

            if (currentAccessToken != null || currentRefreshToken != null) {
                val token = ensureValidAccessToken()
                if (token == null) {
                    logout()
                    return@launch
                }
                _isLoggedIn.value = true
                connectInternal(token)
                if (_username.value == null) updateUsername(token)
            }
        }
    }

    fun login(context: Context) {
        if (!initialized) init(context)
        if (initializationJob?.isActive == true) {
            coroutineScope.launch {
                initializationJob?.join()
                login(context)
            }
            return
        }
        if (!isSupported) {
            Log.w(TAG, "Discord Rich Presence is unavailable in this build.")
            return
        }
        val isTv = com.arflix.tv.util.detectDeviceType(context) == com.arflix.tv.util.DeviceType.TV
        if (isTv) {
            openAuthDialog()
        } else {
            startBrowserAuth(context)
        }
    }

    fun getDirectOAuthUrl(): String? {
        if (!::appContext.isInitialized) return null
        val verifier = PkceUtil.generateCodeVerifier()
        val state = DISCORD_MOBILE_OAUTH_STATE_PREFIX + PkceUtil.generateCodeVerifier()
        val saved = runCatching {
            appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_CODE_VERIFIER, verifier)
                .putString(KEY_OAUTH_STATE, state)
                .commit()
        }.getOrDefault(false)
        if (!saved) return null

        val challenge = PkceUtil.generateCodeChallenge(verifier)
        return "https://discord.com/api/oauth2/authorize?" +
            "client_id=$discordClientId" +
            "&response_type=code" +
            "&redirect_uri=${URLEncoder.encode(REDIRECT_URI_WEB, "UTF-8")}" +
            "&scope=${URLEncoder.encode("identify sdk.social_layer_presence", "UTF-8")}" +
            "&state=$state" +
            "&code_challenge=$challenge" +
            "&code_challenge_method=S256" +
            "&prompt=consent"
    }

    fun startBrowserAuth(context: Context) {
        if (!initialized) init(context)
        if (initializationJob?.isActive == true) {
            coroutineScope.launch {
                initializationJob?.join()
                startBrowserAuth(context)
            }
            return
        }
        if (!isSupported) return
        val url = getDirectOAuthUrl() ?: return
        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching {
            context.startActivity(intent)
        }.onFailure { error ->
            Log.e(TAG, "Failed to launch browser for Discord OAuth", error)
            openAuthDialog()
        }
    }

    private fun openAuthDialog() {
        authPollingJob?.cancel()
        coroutineScope.launch {
            val verifier = PkceUtil.generateCodeVerifier()
            appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_CODE_VERIFIER, verifier)
                .apply()
            val challenge = PkceUtil.generateCodeChallenge(verifier)
            val session = startCloudSession(challenge)
            if (session == null) {
                Log.e(TAG, "Could not start Discord pairing session.")
                appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .remove(KEY_CODE_VERIFIER)
                    .apply()
                return@launch
            }

            _authUrl.value = session.verificationUrl
            _isAuthDialogVisible.value = true
            authPollingJob = launch(Dispatchers.IO) {
                while (isActive && _isAuthDialogVisible.value) {
                    delay(session.intervalSeconds * 1_000L)
                    when (val result = pollCloudStatus(session.deviceCode)) {
                        null -> Unit
                        else -> when (result.status) {
                            "approved" -> {
                                val code = result.code
                                if (!code.isNullOrBlank()) {
                                    withContext(Dispatchers.Main) { completeAuthWithCode(code) }
                                }
                                return@launch
                            }
                            "expired" -> {
                                withContext(Dispatchers.Main) { closeAuthDialog() }
                                return@launch
                            }
                        }
                    }
                }
            }
        }
    }

    fun closeAuthDialog() {
        _isAuthDialogVisible.value = false
        _isAuthLoading.value = false
        _authUrl.value = null
        authPollingJob?.cancel()
        authPollingJob = null
        if (::appContext.isInitialized) {
            appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .remove(KEY_CODE_VERIFIER)
                .remove(KEY_OAUTH_STATE)
                .apply()
        }
    }

    fun completeAuthWithCode(code: String) {
        if (code.isBlank() || code.length > 2048) return
        coroutineScope.launch {
            initializationJob?.join()
            if (!isSupported) return@launch
            val prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val verifier = prefs.getString(KEY_CODE_VERIFIER, null)
            if (verifier.isNullOrBlank()) {
                Log.e(TAG, "No PKCE verifier found for Discord token exchange.")
                return@launch
            }

            _isAuthLoading.value = true
            val tokens = exchangeCodeForToken(code, verifier)
            if (tokens == null) {
                Log.e(TAG, "Discord token exchange failed.")
                clearPendingOAuth(prefs)
                _isAuthLoading.value = false
                return@launch
            }

            saveTokens(tokens)
            prefs.edit()
                .remove(KEY_CODE_VERIFIER)
                .remove(KEY_OAUTH_STATE)
                .apply()
            _isLoggedIn.value = true
            updateUsername(tokens.accessToken)
            connectInternal(tokens.accessToken)
            closeAuthDialog()
        }
    }

    fun onLoginDeepLink(uri: Uri) {
        if (!::appContext.isInitialized) return

        val prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val storedState = prefs.getString(KEY_OAUTH_STATE, null)
        val callbackState = uri.getQueryParameter("state")
        if (!isValidDiscordMobileOAuthState(storedState, callbackState)) {
            Log.w(TAG, "Missing or mismatched OAuth state in Discord deep link; ignoring.")
            clearPendingOAuth(prefs)
            _isAuthLoading.value = false
            return
        }

        val error = uri.getQueryParameter("error")
        if (error != null) {
            Log.e(TAG, "Discord authorization failed: $error")
            clearPendingOAuth(prefs)
            _isAuthLoading.value = false
            return
        }

        val code = uri.getQueryParameter("code")
        if (code.isNullOrBlank() || code.length > 2048) {
            Log.w(TAG, "Missing or invalid Discord authorization code; ignoring.")
            clearPendingOAuth(prefs)
            _isAuthLoading.value = false
            return
        }

        prefs.edit().remove(KEY_OAUTH_STATE).apply()
        completeAuthWithCode(code)
    }

    private fun clearPendingOAuth(prefs: SharedPreferences) {
        prefs.edit()
            .remove(KEY_CODE_VERIFIER)
            .remove(KEY_OAUTH_STATE)
            .apply()
    }

    private suspend fun startCloudSession(challenge: String): PairingSession? =
        withContext(Dispatchers.IO) {
            runCatching {
                val connection = appConnection(Constants.DISCORD_AUTH_START_URL)
                val payload = JSONObject()
                    .put("client_id", discordClientId)
                    .put("code_challenge", challenge)
                    .toString()
                connection.outputStream.use { it.write(payload.toByteArray(Charsets.UTF_8)) }
                if (connection.responseCode !in 200..299) return@runCatching null
                val body = JSONObject(connection.inputStream.bufferedReader().use { it.readText() })
                val deviceCode = body.optString("device_code")
                val verificationUrl = body.optString("verification_uri_complete")
                if (deviceCode.isBlank() || verificationUrl.isBlank()) return@runCatching null
                PairingSession(
                    deviceCode = deviceCode,
                    verificationUrl = verificationUrl,
                    intervalSeconds = body.optLong("interval", 3L).coerceIn(2L, 10L)
                )
            }.onFailure { error ->
                Log.w(TAG, "Could not start Discord pairing", error)
            }.getOrNull()
        }

    private suspend fun pollCloudStatus(deviceCode: String): PairingStatus? =
        withContext(Dispatchers.IO) {
            runCatching {
                val connection = appConnection(Constants.DISCORD_AUTH_STATUS_URL)
                val payload = JSONObject().put("device_code", deviceCode).toString()
                connection.outputStream.use { it.write(payload.toByteArray(Charsets.UTF_8)) }
                if (connection.responseCode !in 200..299) return@runCatching null
                val body = JSONObject(connection.inputStream.bufferedReader().use { it.readText() })
                PairingStatus(body.optString("status", "pending"), body.optString("code").takeIf { it.isNotBlank() })
            }.onFailure { error ->
                Log.w(TAG, "Could not poll Discord pairing", error)
            }.getOrNull()
        }

    private fun appConnection(url: String): HttpURLConnection =
        (URL(url).openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("apikey", Constants.APP_ANON_KEY)
            setRequestProperty("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
            doOutput = true
            connectTimeout = 5_000
            readTimeout = 5_000
        }

    private fun connectInternal(token: String) {
        if (!isSupported || token.isBlank()) return
        connectionState = ConnectionState.CONNECTING
        startTickLoop()
        coroutineScope.launch(Dispatchers.IO) {
            if (!DiscordBridge.connect(token)) {
                connectionState = ConnectionState.DISCONNECTED
                stopTickLoop()
            }
        }
    }

    fun disconnect() {
        stopTickLoop()
        disconnectTimeoutJob?.cancel()
        lastUpdateJob?.cancel()
        reconnectJob?.cancel()
        connectionState = ConnectionState.DISCONNECTED
        if (isSupported) DiscordBridge.disconnect()
    }

    fun isLoggedIn(): Boolean = currentAccessToken != null || currentRefreshToken != null

    fun logout() {
        disconnect()
        tokenRefreshJob?.cancel()
        currentAccessToken = null
        currentRefreshToken = null
        accessTokenExpiresAt = 0L
        _isLoggedIn.value = false
        _username.value = null
        if (::appContext.isInitialized) {
            appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .remove(KEY_ACCESS_TOKEN)
                .remove(KEY_REFRESH_TOKEN)
                .remove(KEY_ACCESS_TOKEN_EXPIRES_AT)
                .remove(KEY_CODE_VERIFIER)
                .remove(KEY_USERNAME)
                .apply()
        }
    }

    private suspend fun updateUsername(token: String) {
        val username = fetchUserProfile(token) ?: return
        appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_USERNAME, username)
            .apply()
        _username.value = username
    }

    private suspend fun fetchUserProfile(token: String): String? = withContext(Dispatchers.IO) {
        runCatching {
            val connection = URL("https://discord.com/api/v10/users/@me").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.connectTimeout = 5_000
            connection.readTimeout = 5_000
            if (connection.responseCode != 200) return@runCatching null
            val body = JSONObject(connection.inputStream.bufferedReader().use { it.readText() })
            body.optString("global_name").takeIf { it.isNotBlank() }
                ?: body.optString("username").takeIf { it.isNotBlank() }
        }.onFailure { error ->
            Log.w(TAG, "Could not load Discord profile", error)
        }.getOrNull()
    }

    fun updatePlayback(
        title: String,
        subtitle: String,
        isPlaying: Boolean,
        progressMs: Long,
        durationMs: Long,
        largeImage: String = ""
    ) {
        if (!initialized || !isSupported || !_isLoggedIn.value) return
        handlePauseTimeout(isPlaying)

        if (isPlaying && connectionState == ConnectionState.DISCONNECTED) {
            reconnectJob?.cancel()
            reconnectJob = coroutineScope.launch {
                ensureValidAccessToken()?.let(::connectInternal)
            }
        }

        lastUpdateJob?.cancel()
        lastUpdateJob = coroutineScope.launch {
            delay(350)
            if (connectionState != ConnectionState.CONNECTED) return@launch
            if (!isPlaying) {
                DiscordBridge.clearActivity()
                return@launch
            }

            val startTime = if (progressMs >= 0) System.currentTimeMillis() - progressMs else 0L
            DiscordBridge.updateActivity(
                details = title,
                state = subtitle.takeIf { it.isNotBlank() },
                startTime = startTime / 1_000,
                endTime = 0L,
                largeImage = largeImage,
                largeText = "ARVIO"
            )
        }
    }

    private fun handlePauseTimeout(isPlaying: Boolean) {
        disconnectTimeoutJob?.cancel()
        if (!isPlaying && connectionState == ConnectionState.CONNECTED) {
            disconnectTimeoutJob = coroutineScope.launch {
                delay(60_000)
                disconnect()
            }
        }
    }

    private fun startTickLoop() {
        tickJob?.cancel()
        tickJob = coroutineScope.launch(Dispatchers.Default) {
            while (isActive) {
                DiscordBridge.tick()
                delay(500)
            }
        }
    }

    private fun stopTickLoop() {
        tickJob?.cancel()
        tickJob = null
    }

    private suspend fun ensureValidAccessToken(): String? {
        val token = currentAccessToken
        if (!token.isNullOrBlank() && accessTokenExpiresAt > System.currentTimeMillis() + TOKEN_REFRESH_MARGIN_MS) {
            return token
        }
        return refreshCurrentToken()
    }

    private suspend fun refreshCurrentToken(): String? = tokenMutex.withLock {
        val token = currentAccessToken
        if (!token.isNullOrBlank() && accessTokenExpiresAt > System.currentTimeMillis() + TOKEN_REFRESH_MARGIN_MS) {
            return@withLock token
        }
        val refreshToken = currentRefreshToken ?: return@withLock null
        val refreshed = requestTokens(
            mapOf(
                "client_id" to discordClientId,
                "grant_type" to "refresh_token",
                "refresh_token" to refreshToken
            )
        ) ?: return@withLock null
        saveTokens(refreshed.copy(refreshToken = refreshed.refreshToken ?: refreshToken))
        _isLoggedIn.value = true
        return@withLock refreshed.accessToken
    }

    private suspend fun exchangeCodeForToken(code: String, verifier: String): OAuthTokens? =
        requestTokens(
            mapOf(
                "client_id" to discordClientId,
                "grant_type" to "authorization_code",
                "code" to code,
                "redirect_uri" to REDIRECT_URI_WEB,
                "code_verifier" to verifier
            )
        )

    private suspend fun requestTokens(parameters: Map<String, String>): OAuthTokens? =
        withContext(Dispatchers.IO) {
            runCatching {
                val connection = URL("https://discord.com/api/oauth2/token").openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.connectTimeout = 8_000
                connection.readTimeout = 8_000
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                val body = parameters.entries.joinToString("&") { (key, value) ->
                    "${URLEncoder.encode(key, "UTF-8")}=${URLEncoder.encode(value, "UTF-8")}"
                }
                connection.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }
                if (connection.responseCode !in 200..299) {
                    val errorBody = connection.errorStream?.bufferedReader()?.use { it.readText() }.orEmpty()
                    Log.w(TAG, "Discord token endpoint returned ${connection.responseCode}: $errorBody")
                    return@runCatching null
                }
                val response = JSONObject(connection.inputStream.bufferedReader().use { it.readText() })
                val accessToken = response.optString("access_token")
                if (accessToken.isBlank()) return@runCatching null
                OAuthTokens(
                    accessToken = accessToken,
                    refreshToken = response.optString("refresh_token").takeIf { it.isNotBlank() },
                    expiresAt = System.currentTimeMillis() + response.optLong("expires_in", 3600L) * 1_000L
                )
            }.onFailure { error ->
                Log.e(TAG, "Discord token request failed", error)
            }.getOrNull()
        }

    private fun saveTokens(tokens: OAuthTokens) {
        currentAccessToken = tokens.accessToken
        currentRefreshToken = tokens.refreshToken
        accessTokenExpiresAt = tokens.expiresAt
        appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_ACCESS_TOKEN, tokens.accessToken)
            .putString(KEY_REFRESH_TOKEN, tokens.refreshToken)
            .putLong(KEY_ACCESS_TOKEN_EXPIRES_AT, tokens.expiresAt)
            .apply()
        scheduleTokenRefresh()
    }

    private fun scheduleTokenRefresh() {
        tokenRefreshJob?.cancel()
        val delayMs = (accessTokenExpiresAt - System.currentTimeMillis() - TOKEN_REFRESH_MARGIN_MS).coerceAtLeast(1_000L)
        tokenRefreshJob = coroutineScope.launch {
            delay(delayMs)
            val refreshed = refreshCurrentToken()
            if (refreshed == null) {
                logout()
            } else if (connectionState != ConnectionState.DISCONNECTED) {
                connectInternal(refreshed)
            }
        }
    }

    private object PkceUtil {
        fun generateCodeVerifier(): String {
            val bytes = ByteArray(64)
            SecureRandom().nextBytes(bytes)
            return Base64.encodeToString(bytes, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
        }

        fun generateCodeChallenge(verifier: String): String {
            val digest = MessageDigest.getInstance("SHA-256")
                .digest(verifier.toByteArray(Charsets.US_ASCII))
            return Base64.encodeToString(digest, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
        }
    }
}
