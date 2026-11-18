package com.arflix.tv.cast

import android.content.Context
import com.arflix.tv.R
import androidx.core.content.ContextCompat
import androidx.mediarouter.media.MediaRouteSelector
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.MediaSeekOptions
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener
import com.google.android.gms.cast.CastMediaControlIntent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/** A TV cannot reach a stream served by a local proxy on the phone. */
internal fun isLocalHost(host: String?): Boolean {
    val h = host?.lowercase() ?: return true
    return h == "localhost" || h == "127.0.0.1" || h == "::1" || h == "[::1]"
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CastManagerEntryPoint {
    fun castManager(): CastManager
}

@Singleton
class CastManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    sealed class CastState {
        data object NotAvailable : CastState()
        data object NotConnected : CastState()
        data object Connecting : CastState()
        data class Casting(val deviceName: String) : CastState()
    }

    private val _castState = MutableStateFlow<CastState>(CastState.NotConnected)
    val castState: StateFlow<CastState> = _castState.asStateFlow()

    private val _googleCastReady = MutableStateFlow(false)
    /** Emits true once the Google Cast framework finished initialising (it starts asynchronously). */
    val googleCastReady: StateFlow<Boolean> = _googleCastReady.asStateFlow()

    private var castContext: CastContext? = null
    private var currentSession: CastSession? = null

    /** True once the Google Cast framework initialised (needs Google Play services). */
    val isGoogleCastReady: Boolean get() = castContext != null

    // ── DLNA / UPnP renderers ──
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var dlna: DlnaController? = null
    private var dlnaPollJob: Job? = null
    @Volatile private var dlnaPositionMs = 0L
    @Volatile private var dlnaDurationMs = 0L
    @Volatile private var dlnaPlaying = false
    @Volatile private var dlnaPendingSeekMs = -1L
    @Volatile private var dlnaUserPaused = false
    @Volatile private var dlnaLoadCommitted = false

    val isDlnaSession: Boolean get() = dlna != null

    /** Start casting to a DLNA renderer; the player then calls [loadMedia] as for Chromecast. */
    fun connectDlna(device: DlnaDevice) {
        if (currentSession != null) castContext?.sessionManager?.endCurrentSession(true)
        disconnectDlna(stopRemote = true)
        dlna = DlnaController(device)
        dlnaPositionMs = 0L; dlnaDurationMs = 0L; dlnaPlaying = false
        dlnaUserPaused = false; dlnaLoadCommitted = false; dlnaPendingSeekMs = -1L
        _castState.value = CastState.Casting(device.friendlyName)
    }

    private fun disconnectDlna(stopRemote: Boolean) {
        val ctrl = dlna ?: return
        dlna = null
        dlnaPollJob?.cancel(); dlnaPollJob = null
        dlnaLoadCommitted = false
        dlnaPendingSeekMs = -1L
        if (stopRemote) scope.launch { ctrl.stop() }
    }

    /**
     * Single exit for a DLNA session. Only the session that currently owns [dlna] may end it,
     * so a stale poll loop cannot tear down a newer session. The player's NotConnected effect
     * then resumes local playback from the last reported position.
     */
    private fun finishDlnaSession(ctrl: DlnaController, reason: String) {
        if (ctrl !== dlna) return
        Log.d("CastManager", "ending DLNA session: $reason")
        disconnectDlna(stopRemote = false)
        _castState.value = CastState.NotConnected
    }

    private fun startDlnaPolling(ctrl: DlnaController, playRejected: Boolean) {
        dlnaPollJob?.cancel()
        val tracker = DlnaSessionTracker()
        tracker.start(playRejected)
        dlnaLoadCommitted = true
        dlnaPollJob = scope.launch {
            while (isActive && dlna === ctrl) {
                val pos = ctrl.position()
                val state = ctrl.transportState()
                val reason = tracker.onPoll(positionOk = pos != null, state = state)
                if (reason != null) {
                    if (reason == DlnaSessionTracker.EndReason.NO_PLAYBACK) {
                        _castError.value = R.string.cast_error_no_playback
                    }
                    finishDlnaSession(ctrl, reason.name)
                    return@launch
                }
                pos?.let {
                    if (it.positionMs > 0L) dlnaPositionMs = it.positionMs
                    if (it.durationMs > 0L) dlnaDurationMs = it.durationMs
                }
                if (state != null) dlnaPlaying = state == "PLAYING" || state == "TRANSITIONING"
                // A Play rejected during TRANSITIONING is retried once the renderer settles.
                if (tracker.shouldRetryPlay(state, dlnaUserPaused)) {
                    tracker.notePlayRetry()
                    runCatching { ctrl.play() }
                    if (dlna !== ctrl) return@launch
                }
                val pending = dlnaPendingSeekMs
                if (pending > 0L && state == "PLAYING") {
                    dlnaPendingSeekMs = -1L
                    ctrl.seek(pending)
                }
                delay(1_000L)
            }
        }
    }

    private val sessionListener = object : SessionManagerListener<CastSession> {
        override fun onSessionStarting(session: CastSession) {
            if (dlna == null) _castState.value = CastState.Connecting
        }

        override fun onSessionStarted(session: CastSession, sessionId: String) {
            disconnectDlna(stopRemote = true)
            currentSession = session
            _castState.value = CastState.Casting(session.castDevice?.friendlyName ?: "Chromecast")
        }

        override fun onSessionStartFailed(session: CastSession, error: Int) {
            currentSession = null
            if (dlna == null) _castState.value = CastState.NotConnected
        }

        override fun onSessionEnding(session: CastSession) {}

        override fun onSessionEnded(session: CastSession, error: Int) {
            currentSession = null
            if (dlna == null) _castState.value = CastState.NotConnected
        }

        override fun onSessionResuming(session: CastSession, sessionId: String) {
            if (dlna == null) _castState.value = CastState.Connecting
        }

        override fun onSessionResumed(session: CastSession, wasSuspended: Boolean) {
            currentSession = session
            _castState.value = CastState.Casting(session.castDevice?.friendlyName ?: "Chromecast")
        }

        override fun onSessionResumeFailed(session: CastSession, error: Int) {
            currentSession = null
            if (dlna == null) _castState.value = CastState.NotConnected
        }

        override fun onSessionSuspended(session: CastSession, reason: Int) {}
    }

    fun initialize(isMobile: Boolean) {
        if (!isMobile) {
            _castState.value = CastState.NotAvailable
            return
        }
        if (_castState.value is CastState.NotAvailable) _castState.value = CastState.NotConnected
        if (castContext != null) return
        val executor = ContextCompat.getMainExecutor(context)
        try {
            CastContext.getSharedInstance(context, executor)
                .addOnSuccessListener(executor) { ctx ->
                    castContext = ctx
                    _googleCastReady.value = true
                    ctx.sessionManager.addSessionManagerListener(sessionListener, CastSession::class.java)
                    val active = ctx.sessionManager.currentCastSession
                    if (active != null) {
                        currentSession = active
                        _castState.value = CastState.Casting(active.castDevice?.friendlyName ?: "Chromecast")
                    }
                }
                .addOnFailureListener(executor) {
                    // No Google Play services: Chromecast is unavailable but DLNA still works.
                    Log.w("CastManager", "Google Cast unavailable", it)
                }
        } catch (e: Exception) {
            Log.w("CastManager", "Google Cast unavailable", e)
        }
    }

    /** Whether a URL can be reached by a TV (no local proxy / loopback source). */
    fun isCastableUrl(url: String?): Boolean = !isLocalHost(
        url?.let { runCatching { android.net.Uri.parse(it).host?.lowercase() }.getOrNull() }
    )

    fun loadMedia(url: String, title: String, imageUrl: String?, mimeType: String, positionMs: Long) {
        val host = runCatching { android.net.Uri.parse(url).host?.lowercase() }.getOrNull()
        if (isLocalHost(host)) {
            // Keep the local position available in case the player's poll loop reads it
            // before the session is torn down.
            dlnaPositionMs = positionMs
            _castError.value = R.string.cast_error_local_source
            // By now the player has already paused its local ExoPlayer. Ending the session
            // lets its NotConnected effect resume playback; leaving it connected would keep
            // a black screen until the user manually stops casting.
            dlna?.let { finishDlnaSession(it, "LocalSource") } ?: run {
                val manager = castContext?.sessionManager
                if (manager != null) {
                    // Let the framework listener drive NotConnected; writing it by hand here
                    // would race onSessionEnded and could reset a session the user just opened.
                    manager.endCurrentSession(true)
                } else {
                    _castState.value = CastState.NotConnected
                }
            }
            return
        }
        dlna?.let { ctrl ->
            dlnaPositionMs = positionMs
            dlnaPendingSeekMs = if (positionMs > 3_000L) positionMs else -1L
            dlnaLoadCommitted = false
            scope.launch {
                val outcome = runCatching { ctrl.load(url, title, dlnaMimeType(url, mimeType)) }
                    .onFailure { Log.w("CastManager", "DLNA load failed", it) }
                    .getOrDefault(DlnaLoadOutcome.UriRejected)
                if (dlna !== ctrl) return@launch
                when (outcome) {
                    DlnaLoadOutcome.UriRejected -> {
                        _castError.value = R.string.cast_error_format_refused
                        finishDlnaSession(ctrl, "UriRejected")
                    }
                    DlnaLoadOutcome.Ok -> startDlnaPolling(ctrl, playRejected = false)
                    DlnaLoadOutcome.PlayRejected -> startDlnaPolling(ctrl, playRejected = true)
                }
            }
            return
        }
        val client = currentSession?.remoteMediaClient ?: return
        val metadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE).apply {
            putString(MediaMetadata.KEY_TITLE, title)
        }
        val mediaInfo = MediaInfo.Builder(url)
            .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
            .setContentType(mimeType)
            .setMetadata(metadata)
            .build()
        val request = MediaLoadRequestData.Builder()
            .setMediaInfo(mediaInfo)
            .setCurrentTime(positionMs)
            .setAutoplay(true)
            .build()
        client.load(request).setResultCallback { result ->
            if (!result.status.isSuccess) {
                Log.w("CastManager", "Cast load failed: ${result.status}")
                _castError.value = R.string.cast_error_format_refused
            }
        }
    }

    private val _castError = MutableStateFlow<Int?>(null)
    /** String resource of the last cast failure for the UI to show once; cleared with [clearCastError]. */
    val castError: StateFlow<Int?> = _castError.asStateFlow()
    fun clearCastError() { _castError.value = null }

    /** Called by the chooser before it connects, when the current source cannot be cast. */
    fun reportLocalSource() { _castError.value = R.string.cast_error_local_source }

    fun play() {
        dlna?.let { c ->
            dlnaPlaying = true; dlnaUserPaused = false
            scope.launch { c.play() }
            return
        }
        currentSession?.remoteMediaClient?.play()
    }

    fun pause() {
        dlna?.let { c ->
            dlnaPlaying = false; dlnaUserPaused = true
            scope.launch { c.pause() }
            return
        }
        currentSession?.remoteMediaClient?.pause()
    }

    fun seekTo(positionMs: Long) {
        dlna?.let { c ->
            dlnaPositionMs = positionMs
            if (dlnaLoadCommitted) {
                // A user seek supersedes the pending resume seek, which would otherwise
                // jump back to the old position when the renderer first reports PLAYING.
                dlnaPendingSeekMs = -1L
                scope.launch { c.seek(positionMs) }
            } else {
                // The URI is not loaded yet: a Seek now would be rejected. Keep the user's
                // target and apply it on the first PLAYING poll instead.
                dlnaPendingSeekMs = if (positionMs > 0L) positionMs else -1L
            }
            return
        }
        currentSession?.remoteMediaClient?.seek(
            MediaSeekOptions.Builder().setPosition(positionMs).build()
        )
    }

    fun skipForward(amountMs: Long = 10_000L) {
        seekTo(getApproximatePosition() + amountMs)
    }

    fun skipBack(amountMs: Long = 10_000L) {
        seekTo((getApproximatePosition() - amountMs).coerceAtLeast(0L))
    }

    fun getApproximatePosition(): Long =
        if (dlna != null) dlnaPositionMs
        else currentSession?.remoteMediaClient?.approximateStreamPosition ?: 0L

    fun getApproximateDuration(): Long =
        if (dlna != null) dlnaDurationMs
        else currentSession?.remoteMediaClient?.mediaInfo?.streamDuration ?: 0L

    fun isRemotePlaying(): Boolean =
        if (dlna != null) dlnaPlaying
        else currentSession?.remoteMediaClient?.isPlaying == true

    fun disconnect() {
        if (dlna != null) {
            disconnectDlna(stopRemote = true)
            _castState.value = CastState.NotConnected
            return
        }
        castContext?.sessionManager?.endCurrentSession(true)
    }

    fun getRouteSelector(): MediaRouteSelector =
        MediaRouteSelector.Builder()
            .addControlCategory(
                CastMediaControlIntent.categoryForCast(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID)
            )
            .build()

    private fun dlnaMimeType(url: String, fallback: String): String {
        val path = url.substringBefore('?').lowercase()
        return when {
            path.endsWith(".mkv") -> "video/x-matroska"
            path.endsWith(".webm") -> "video/webm"
            path.endsWith(".avi") -> "video/x-msvideo"
            path.endsWith(".ts") || path.endsWith(".m2ts") -> "video/mp2t"
            path.endsWith(".m3u8") || url.contains(".m3u8", ignoreCase = true) -> "application/vnd.apple.mpegurl"
            else -> fallback
        }
    }
}
