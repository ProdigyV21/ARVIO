package com.arflix.tv.data.repository

import android.util.Log
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.arflix.tv.BuildConfig
import com.arflix.tv.util.Constants
import com.arflix.tv.util.AppLogger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

/** Cloud sync WebSocket connection status for the UI indicator. */
enum class CloudSyncStatus { CONNECTED, RECONNECTING, NOT_SIGNED_IN }

/**
 * Manages a Supabase Realtime WebSocket connection to receive instant
 * notifications when `account_sync_state` or `watch_history` changes on any device.
 *
 * Three channels are joined on the same socket:
 *
 * 1. `realtime:account_sync` — listens for UPDATEs on `account_sync_state`. On
 *    change, the manager triggers [CloudSyncRepository.pullFromCloud] to reapply the
 *    full JSON snapshot (addons, profiles, catalogs, IPTV config, preferences).
 *
 * 2. `realtime:watch_history` — listens for INSERTs, UPDATEs, AND DELETEs on
 *    `watch_history` so the Home screen's Continue Watching row can refresh on
 *    other devices within seconds of a progress update or CW removal.
 *
 * Fixes applied in this version:
 * - Reuses a single OkHttpClient for WebSocket connections (was leaking connection
 *   pools on every reconnect).
 * - Subscribes to DELETE events on watch_history (was missing — CW removal on
 *   device A wasn't reflected on device B in real-time).
 * - Periodic token refresh: reconnects with a fresh JWT every 30 minutes so
 *   realtime events don't silently stop after token expiry.
 * - Exposes a [syncStatusFlow] for the UI to show a connection indicator.
 * - Exponential backoff on reconnect (5s → 10s → 20s → 40s cap).
 */
@Singleton
class RealtimeSyncManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cloudSyncRepository: CloudSyncRepository,
    private val authRepository: AuthRepository
) {
    companion object {
        private const val TAG = "RealtimeSync"
        private const val HEARTBEAT_INTERVAL_MS = 30_000L
        private const val INITIAL_RECONNECT_DELAY_MS = 5_000L
        private const val MAX_RECONNECT_DELAY_MS = 40_000L
        // Lowered from 90s → 45s. This is the fallback polling that catches
        // cases where the WebSocket delivered no event (network dropped mid-
        // change, server filter missed it). Halving the interval tightens
        // the worst-case propagation window for cross-device sync without
        // meaningfully increasing load (one GET every 45s while signed in).
        private const val PERIODIC_SYNC_INTERVAL_MS = 15 * 60 * 1000L
        private const val DEBOUNCE_MS = 2_000L
        private const val WATCH_HISTORY_DEBOUNCE_MS = 1_000L
        private const val WATCH_HISTORY_SELF_ECHO_GUARD_MS = 1_500L
        // Reconnect with fresh token every 30 minutes to prevent silent auth expiry
        private const val TOKEN_REFRESH_INTERVAL_MS = 30 * 60 * 1000L
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val isRunning = AtomicBoolean(false)
    private val msgRef = AtomicInteger(1)

    // Single OkHttpClient reused across reconnects — the previous implementation
    // created a new client on every connectWebSocketWithToken() call, leaking
    // connection pools and thread pools on flaky connections.
    private val wsClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS) // No timeout for WebSocket
            .pingInterval(25, TimeUnit.SECONDS)
            .build()
    }

    private var webSocket: WebSocket? = null
    private var heartbeatJob: Job? = null
    private var periodicSyncJob: Job? = null
    private var reconnectJob: Job? = null
    private var pendingPullJob: Job? = null
    private var pendingWatchHistoryEmitJob: Job? = null
    private var tokenRefreshJob: Job? = null

    // Exponential backoff state for reconnect
    private var currentReconnectDelay = INITIAL_RECONNECT_DELAY_MS

    @Volatile
    private var lastPushTimestamp = 0L
    @Volatile
    private var lastLocalWatchHistoryWriteTimestamp = 0L
    @Volatile
    private var currentAccessToken: String? = null

    // Event stream for watch_history realtime notifications
    private val _watchHistoryEvents = MutableSharedFlow<Unit>(extraBufferCapacity = 4)
    val watchHistoryEvents: SharedFlow<Unit> = _watchHistoryEvents.asSharedFlow()

    // Event stream for account_sync realtime notifications (catalogs, addons, settings changed on another device)
    private val _accountSyncEvents = MutableSharedFlow<Unit>(extraBufferCapacity = 4)
    val accountSyncEvents: SharedFlow<Unit> = _accountSyncEvents.asSharedFlow()

    // Sync status for UI indicator
    private val _syncStatusFlow = MutableStateFlow(CloudSyncStatus.NOT_SIGNED_IN)
    val syncStatusFlow: StateFlow<CloudSyncStatus> = _syncStatusFlow.asStateFlow()

    fun markPush() {
        lastPushTimestamp = System.currentTimeMillis()
    }

    fun markLocalWatchHistoryWrite() {
        lastLocalWatchHistoryWriteTimestamp = System.currentTimeMillis()
    }

    fun start() {
        if (isRunning.getAndSet(true)) return
        Log.i(TAG, "Starting realtime sync")
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            Log.i(TAG, "Netlify cloud sync enabled; Supabase realtime socket disabled")
            scope.launch {
                _syncStatusFlow.value = if (authRepository.getCurrentUserIdForSync().isNullOrBlank()) {
                    CloudSyncStatus.NOT_SIGNED_IN
                } else {
                    CloudSyncStatus.CONNECTED
                }
            }
            return
        }
        _syncStatusFlow.value = CloudSyncStatus.RECONNECTING
        connectWebSocket()
        if (BuildConfig.ENABLE_PERIODIC_CLOUD_PULL) {
            startPeriodicSync()
        }
        startTokenRefreshLoop()
    }

    fun stop() {
        if (!isRunning.getAndSet(false)) return
        Log.i(TAG, "Stopping realtime sync")
        webSocket?.close(1000, "App stopping")
        webSocket = null
        heartbeatJob?.cancel()
        periodicSyncJob?.cancel()
        reconnectJob?.cancel()
        pendingPullJob?.cancel()
        pendingWatchHistoryEmitJob?.cancel()
        tokenRefreshJob?.cancel()
        _syncStatusFlow.value = CloudSyncStatus.NOT_SIGNED_IN
    }

    // ── WebSocket Connection ────────────────────────────────────────

    private fun connectWebSocket() {
        if (!isRunning.get()) return

        _syncStatusFlow.value = CloudSyncStatus.RECONNECTING
        scope.launch {
            val userId = authRepository.getCurrentUserIdForSync()
            if (userId.isNullOrBlank()) {
                Log.w(TAG, "Not logged in, skipping WebSocket connection")
                _syncStatusFlow.value = CloudSyncStatus.NOT_SIGNED_IN
                scheduleReconnect()
                return@launch
            }
            val accessToken = authRepository.getAccessToken()
            if (accessToken.isNullOrBlank()) {
                Log.w(TAG, "No access token, skipping WebSocket connection")
                _syncStatusFlow.value = CloudSyncStatus.NOT_SIGNED_IN
                scheduleReconnect()
                return@launch
            }
            connectWebSocketWithToken(userId, accessToken)
        }
    }

    private fun connectWebSocketWithToken(userId: String, accessToken: String) {
        if (!isRunning.get()) return

        val supabaseUrl = Constants.SUPABASE_URL
            .replace("https://", "wss://")
            .replace("http://", "ws://")
        val wsUrl = "$supabaseUrl/realtime/v1/websocket?apikey=${Constants.SUPABASE_ANON_KEY}&vsn=1.0.0"

        val request = Request.Builder().url(wsUrl).build()
        currentAccessToken = accessToken

        webSocket = wsClient.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.i(TAG, "WebSocket connected")
                // Reset backoff on successful connection
                currentReconnectDelay = INITIAL_RECONNECT_DELAY_MS
                _syncStatusFlow.value = CloudSyncStatus.CONNECTED
                joinChannel(webSocket, userId)
                startHeartbeat(webSocket)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                handleMessage(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.w(TAG, "WebSocket failure: ${t.message}")
                _syncStatusFlow.value = CloudSyncStatus.RECONNECTING
                scheduleReconnect()
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Log.i(TAG, "WebSocket closing: $code $reason")
                webSocket.close(1000, null)
                if (isRunning.get()) {
                    _syncStatusFlow.value = CloudSyncStatus.RECONNECTING
                    scheduleReconnect()
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.i(TAG, "WebSocket closed: $code")
                if (isRunning.get()) {
                    _syncStatusFlow.value = CloudSyncStatus.RECONNECTING
                    scheduleReconnect()
                }
            }
        })
    }

    private fun joinChannel(ws: WebSocket, userId: String) {
        // Channel 1: account_sync_state and user_settings changes. user_settings is
        // a sync fallback mirror, so it must also wake other signed-in devices.
        val accountSyncJoin = JSONObject().apply {
            put("topic", "realtime:account_sync")
            put("event", "phx_join")
            put("payload", JSONObject().apply {
                put("config", JSONObject().apply {
                    put("postgres_changes", JSONArray().apply {
                        put(JSONObject().apply {
                            put("event", "INSERT")
                            put("schema", "public")
                            put("table", "account_sync_state")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "UPDATE")
                            put("schema", "public")
                            put("table", "account_sync_state")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "DELETE")
                            put("schema", "public")
                            put("table", "account_sync_state")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "INSERT")
                            put("schema", "public")
                            put("table", "user_settings")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "UPDATE")
                            put("schema", "public")
                            put("table", "user_settings")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "DELETE")
                            put("schema", "public")
                            put("table", "user_settings")
                            put("filter", "user_id=eq.$userId")
                        })
                    })
                })
                currentAccessToken?.let { put("access_token", it) }
            })
            put("ref", msgRef.getAndIncrement().toString())
        }
        ws.send(accountSyncJoin.toString())

        if (BuildConfig.ENABLE_REALTIME_WATCH_SYNC) {
        // Channel 2: watch_history INSERT + UPDATE + DELETE events.
        // DELETE was missing previously — when a user removed an item from Continue
        // Watching on device A, device B never got a realtime notification for the
        // removal and kept showing the stale entry until the next CW refresh.
        val watchHistoryJoin = JSONObject().apply {
            put("topic", "realtime:watch_history")
            put("event", "phx_join")
            put("payload", JSONObject().apply {
                put("config", JSONObject().apply {
                    put("postgres_changes", JSONArray().apply {
                        put(JSONObject().apply {
                            put("event", "INSERT")
                            put("schema", "public")
                            put("table", "watch_history")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "UPDATE")
                            put("schema", "public")
                            put("table", "watch_history")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "DELETE")
                            put("schema", "public")
                            put("table", "watch_history")
                            put("filter", "user_id=eq.$userId")
                        })
                    })
                })
                currentAccessToken?.let { put("access_token", it) }
            })
            put("ref", msgRef.getAndIncrement().toString())
        }
        ws.send(watchHistoryJoin.toString())

        // Channel 3: watched_movies and watched_episodes table changes.
        // These are written by TraktSyncService when a Trakt sync completes on another
        // device. Subscribing means watched badges update across devices in real-time
        // instead of waiting for the periodic 90s sync.
        val watchedTablesJoin = JSONObject().apply {
            put("topic", "realtime:watched_status")
            put("event", "phx_join")
            put("payload", JSONObject().apply {
                put("config", JSONObject().apply {
                    put("postgres_changes", JSONArray().apply {
                        // watched_movies: INSERT, UPDATE, DELETE
                        put(JSONObject().apply {
                            put("event", "INSERT")
                            put("schema", "public")
                            put("table", "watched_movies")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "DELETE")
                            put("schema", "public")
                            put("table", "watched_movies")
                            put("filter", "user_id=eq.$userId")
                        })
                        // watched_episodes: INSERT, UPDATE, DELETE
                        put(JSONObject().apply {
                            put("event", "INSERT")
                            put("schema", "public")
                            put("table", "watched_episodes")
                            put("filter", "user_id=eq.$userId")
                        })
                        put(JSONObject().apply {
                            put("event", "DELETE")
                            put("schema", "public")
                            put("table", "watched_episodes")
                            put("filter", "user_id=eq.$userId")
                        })
                    })
                })
                currentAccessToken?.let { put("access_token", it) }
            })
            put("ref", msgRef.getAndIncrement().toString())
        }
        ws.send(watchedTablesJoin.toString())

        }

        val channels = if (BuildConfig.ENABLE_REALTIME_WATCH_SYNC) {
            "account_sync + watch_history + watched_status"
        } else {
            "account_sync"
        }
        Log.i(TAG, "Joined $channels channels for user $userId")
    }

    private fun handleMessage(text: String) {
        try {
            val msg = JSONObject(text)
            val event = msg.optString("event", "")
            val topic = msg.optString("topic", "")

            when (event) {
                "postgres_changes" -> {
                    when (topic) {
                        "realtime:account_sync" -> {
                            Log.i(TAG, "Received account_sync change")
                            debouncedPull()
                        }
                        "realtime:watch_history" -> {
                            Log.i(TAG, "Received watch_history change")
                            debouncedWatchHistoryEmit()
                        }
                        "realtime:watched_status" -> {
                            // watched_movies or watched_episodes changed on another device
                            // (e.g., Trakt sync completed). Trigger CW refresh so watched
                            // badges update and completed items leave the CW row.
                            Log.i(TAG, "Received watched_status change (movies/episodes)")
                            debouncedWatchHistoryEmit()
                        }
                        else -> {
                            Log.w(TAG, "postgres_changes on unknown topic: $topic")
                        }
                    }
                }
                "phx_reply" -> {
                    val status = msg.optJSONObject("payload")?.optString("status")
                    Log.d(TAG, "Channel reply ($topic): $status")
                }
                "phx_error" -> {
                    Log.w(TAG, "Channel error: $text")
                }
                "system" -> {
                    val payload = msg.optJSONObject("payload")
                    if (payload?.optString("status") == "ok") {
                        Log.i(TAG, "Subscription confirmed ($topic)")
                    }
                }
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            Log.w(TAG, "Failed to parse realtime message: ${e.message}")
        }
    }

    private fun debouncedPull() {
        if (System.currentTimeMillis() - lastPushTimestamp < 3_000L) {
            Log.d(TAG, "Skipping pull - recent push detected")
            return
        }

        pendingPullJob?.cancel()
        pendingPullJob = scope.launch {
            delay(DEBOUNCE_MS)
            Log.i(TAG, "Pulling cloud state after realtime notification")
            runCatching { cloudSyncRepository.pullFromCloud() }
                .onSuccess { result ->
                    if (result == CloudSyncRepository.RestoreResult.RESTORED) {
                        _accountSyncEvents.tryEmit(Unit)
                    }
                }
                .onFailure { Log.w(TAG, "Realtime pull failed: ${it.message}") }
        }
    }

    private fun debouncedWatchHistoryEmit() {
        if (System.currentTimeMillis() - lastLocalWatchHistoryWriteTimestamp < WATCH_HISTORY_SELF_ECHO_GUARD_MS) {
            Log.d(TAG, "Skipping watch_history emit - recent local write")
            return
        }

        pendingWatchHistoryEmitJob?.cancel()
        pendingWatchHistoryEmitJob = scope.launch {
            delay(WATCH_HISTORY_DEBOUNCE_MS)
            Log.i(TAG, "Emitting watch_history event for Home refresh")
            _watchHistoryEvents.tryEmit(Unit)
        }
    }

    // ── Heartbeat ───────────────────────────────────────────────────

    private fun startHeartbeat(ws: WebSocket) {
        heartbeatJob?.cancel()
        heartbeatJob = scope.launch {
            while (isActive && isRunning.get()) {
                delay(HEARTBEAT_INTERVAL_MS)
                val hb = JSONObject().apply {
                    put("topic", "phoenix")
                    put("event", "heartbeat")
                    put("payload", JSONObject())
                    put("ref", msgRef.getAndIncrement().toString())
                }
                try {
                    ws.send(hb.toString())
                } catch (e: Exception) {
                    AppLogger.e("RealtimeSyncManager", "Error parsing watch history event", e)
                    break
                }
            }
        }
    }

    // ── Reconnect with exponential backoff ──────────────────────────

    private fun scheduleReconnect() {
        heartbeatJob?.cancel()
        reconnectJob?.cancel()
        reconnectJob = scope.launch {
            delay(currentReconnectDelay)
            // Exponential backoff: 5s → 10s → 20s → 40s cap
            currentReconnectDelay = (currentReconnectDelay * 2).coerceAtMost(MAX_RECONNECT_DELAY_MS)
            if (isRunning.get()) {
                Log.i(TAG, "Reconnecting WebSocket (backoff: ${currentReconnectDelay / 1000}s next)...")
                connectWebSocket()
            }
        }
    }

    // ── Periodic Fallback Sync ──────────────────────────────────────

    private fun startPeriodicSync() {
        periodicSyncJob?.cancel()
        periodicSyncJob = scope.launch {
            while (isActive && isRunning.get()) {
                delay(PERIODIC_SYNC_INTERVAL_MS)
                if (!isRunning.get()) break
                // If the last push failed, retry it first. Without this step a
                // transient network hiccup during Save would leave the cloud
                // diverged from local until the user explicitly made another
                // change. The dirty flag is cleared on success inside
                // pushToCloud, so there's no retry loop risk.
                if (cloudSyncRepository.isPushDirty) {
                    Log.i(TAG, "Periodic sync: retrying dirty push")
                    runCatching { cloudSyncRepository.pushToCloud() }
                        .onFailure {
                            Log.w(TAG, "Dirty push retry failed: ${it.message}")
                            com.arflix.tv.worker.CloudSyncWorker.enqueueRecovery(context)
                        }
                }
                Log.d(TAG, "Periodic sync tick")
                runCatching { cloudSyncRepository.pullFromCloud() }
                    .onFailure { Log.w(TAG, "Periodic sync failed: ${it.message}") }
            }
        }
    }

    // ── Token Refresh Loop ──────────────────────────────────────────

    /**
     * Periodically checks if the access token has changed (due to refresh) and
     * reconnects the WebSocket with the new token. Without this, the Supabase
     * server will silently stop delivering events once the original JWT expires
     * (~1 hour default), and the user would see no sync activity until the
     * periodic fallback pull detects the divergence.
     */
    private fun startTokenRefreshLoop() {
        tokenRefreshJob?.cancel()
        tokenRefreshJob = scope.launch {
            while (isActive && isRunning.get()) {
                delay(TOKEN_REFRESH_INTERVAL_MS)
                if (!isRunning.get()) break
                try {
                    val freshToken = authRepository.getAccessToken()
                    if (!freshToken.isNullOrBlank() && freshToken != currentAccessToken) {
                        Log.i(TAG, "Access token changed, reconnecting WebSocket with fresh token")
                        webSocket?.close(1000, "Token refresh")
                        webSocket = null
                        heartbeatJob?.cancel()
                        // connectWebSocket will fetch the token again and reconnect
                        connectWebSocket()
                    }
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e

                    Log.w(TAG, "Token refresh check failed: ${e.message}")
                }
            }
        }
    }
}
