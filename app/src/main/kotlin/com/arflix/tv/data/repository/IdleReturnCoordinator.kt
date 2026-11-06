package com.arflix.tv.data.repository

import android.os.SystemClock
import android.util.Log
import com.arflix.tv.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Notices when nobody has used this device for a long time, and brings its watch progress up to
 * date when somebody comes back.
 *
 * A TV is often switched off while ARVIO sits on a paused player. If the same profile then
 * watches further on another device, this device still holds the old episode and position, and
 * the first thing done here (resuming, or even just leaving the player) would write that old
 * position back with a fresh timestamp, burying the newer progress. So:
 *
 * - Idle means no remote / touch input and no video playing ([markActive] is called while one
 *   plays) for [idleThresholdMs]. It is measured on [SystemClock.elapsedRealtime], which keeps
 *   counting while the box sleeps, so a TV that put the box to standby is caught on wake.
 * - When idle ends (the app returns to the foreground, or the first input arrives) a cloud pull
 *   runs *without* pushing first — nothing new was done here while idle, and pushing would upload
 *   this device's stale list over the newer one. [isSyncing] is true while it runs, and
 *   [syncCompleted] fires afterwards so screens rebuild their resume targets.
 * - The player watches [idleReturns] and its own idle time, and leaves (without saving) when it
 *   has been sitting paused through an idle period.
 */
@Singleton
class IdleReturnCoordinator internal constructor(
    private val cloudSyncRepository: CloudSyncRepository,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val scope: CoroutineScope,
) {
    @Inject constructor(cloudSyncRepository: CloudSyncRepository, watchHistoryRepository: WatchHistoryRepository) :
        this(cloudSyncRepository, watchHistoryRepository, CoroutineScope(SupervisorJob() + Dispatchers.IO))

    /** Monotonic clock that keeps counting in deep sleep; replaceable in tests. */
    internal var clock: () -> Long = { SystemClock.elapsedRealtime() }

    /** Read lazily: the first use (not app start-up order) anchors the idle clock. */
    @Volatile
    private var lastActiveAt: Long = UNSET

    private val _isSyncing = MutableStateFlow(false)
    /** A post-idle cloud pull is running; resume targets on screen may be out of date. */
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private val _syncCompleted = MutableStateFlow(0L)
    /** Retained revision, including failed/timed-out pulls, so a loading screen cannot miss it. */
    val syncCompleted: StateFlow<Long> = _syncCompleted.asStateFlow()

    private val _idleReturns = MutableStateFlow(0L)
    /** Bumped each time an idle period ends. */
    val idleReturns: StateFlow<Long> = _idleReturns.asStateFlow()

    private var syncJob: Job? = null

    /** A player screen is on display (set by the player while it is composed). */
    @Volatile
    var isPlayerOpen: Boolean = false
        set(value) {
            field = value
            if (value) stalePlayer = false
        }

    @Volatile
    private var stalePlayer = false

    /** Foreground/input resets the idle clock, but must not make the old player safe again. */
    val shouldDiscardPlayer: Boolean
        get() = stalePlayer || (isPlayerOpen && isIdle())

    val idleThresholdMs: Long
        get() = debugIdleThresholdOverrideMs() ?: IDLE_THRESHOLD_MS

    fun idleForMs(): Long {
        val now = clock()
        if (lastActiveAt == UNSET) lastActiveAt = now
        return now - lastActiveAt
    }

    fun isIdle(): Boolean = idleForMs() >= idleThresholdMs

    /** Something is being watched: playback keeps the device out of idle. */
    fun markActive() {
        lastActiveAt = clock()
    }

    /**
     * Remote, keyboard or touch input. Returns true when this input ended an idle period, so the
     * caller can keep it from also acting on a screen that is about to change.
     */
    fun onUserInteraction(): Boolean = endIdleIfNeeded("input")

    /** The app came back to the foreground (e.g. the TV woke the box over HDMI-CEC). */
    fun onAppForeground(): Boolean = endIdleIfNeeded("foreground")

    private fun endIdleIfNeeded(reason: String): Boolean {
        val wasIdle = isIdle()
        val idleMs = idleForMs()
        markActive()
        if (!wasIdle) return false
        if (isPlayerOpen) stalePlayer = true
        log("Idle ended by $reason after ${idleMs / 1000}s; pulling latest progress")
        _idleReturns.value = _idleReturns.value + 1
        startSync()
        return true
    }

    private fun startSync() {
        if (syncJob?.isActive == true) return
        _isSyncing.value = true
        syncJob = scope.launch {
            try {
                val result = withTimeoutOrNull(SYNC_TIMEOUT_MS) {
                    try {
                        cloudSyncRepository.pullFromCloud(pushPendingLocalFirst = false)
                    } catch (error: CancellationException) {
                        throw error
                    } catch (error: Exception) {
                        log("Post-idle pull failed: ${error.message}")
                        null
                    }
                }
                log("Post-idle pull finished: ${result ?: "timed out"}")
                // This device's in-memory watch history only holds its own plays, so after an
                // idle period it is the stale side by definition; the synced Continue Watching
                // list (just pulled) carries the same entries plus the other devices' progress.
                if (result == CloudSyncRepository.RestoreResult.RESTORED) {
                    watchHistoryRepository.clearProfileCaches()
                }
            } finally {
                _syncCompleted.value += 1L
                _isSyncing.value = false
            }
        }
    }

    /**
     * Debug builds only: `adb shell setprop debug.arvio.idle_ms 60000` shortens the idle threshold
     * so the flow can be tested without waiting an hour.
     */
    private fun debugIdleThresholdOverrideMs(): Long? {
        if (!BuildConfig.DEBUG) return null
        return runCatching {
            val clazz = Class.forName("android.os.SystemProperties")
            val value = clazz.getMethod("get", String::class.java).invoke(null, "debug.arvio.idle_ms") as? String
            value?.toLongOrNull()?.takeIf { it > 0L }
        }.getOrNull()
    }

    private fun log(message: String) {
        runCatching { Log.i(TAG, message) }
    }

    companion object {
        private const val TAG = "IdleReturn"
        private const val UNSET = Long.MIN_VALUE
        const val IDLE_THRESHOLD_MS = 60L * 60L * 1000L
        const val SYNC_TIMEOUT_MS = 8_000L
    }
}
