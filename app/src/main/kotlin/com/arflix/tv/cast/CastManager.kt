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

    val isDlnaSession: Boolean get() = dlna != null

    /** Start casting to a DLNA renderer; the player then calls [loadMedia] as for Chromecast. */
    fun connectDlna(device: DlnaDevice) {
        if (currentSession != null) castContext?.sessionManager?.endCurrentSession(true)
        disconnectDlna(stopRemote = true)
        dlna = DlnaController(device)
        dlnaPositionMs = 0L; dlnaDurationMs = 0L; dlnaPlaying = false
        _castState.value = CastState.Casting(device.friendlyName)
    }

    private fun disconnectDlna(stopRemote: Boolean) {
        val ctrl = dlna ?: return
        dlna = null
        dlnaPollJob?.cancel(); dlnaPollJob = null
        if (stopRemote) scope.launch { ctrl.stop() }
    }

    private fun startDlnaPolling(ctrl: DlnaController) {
        dlnaPollJob?.cancel()
        dlnaPollJob = scope.launch {
            while (isActive && dlna === ctrl) {
                ctrl.position()?.let {
                    if (it.positionMs > 0L) dlnaPositionMs = it.positionMs
                    if (it.durationMs > 0L) dlnaDurationMs = it.durationMs
                }
                val state = ctrl.transportState()
                if (state != null) dlnaPlaying = state == "PLAYING" || state == "TRANSITIONING"
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

    fun loadMedia(url: String, title: String, imageUrl: String?, mimeType: String, positionMs: Long) {
        val host = runCatching { android.net.Uri.parse(url).host?.lowercase() }.getOrNull()
        if (host == null || host == "localhost" || host == "127.0.0.1" || host == "::1" || host == "[::1]") {
            _castError.value = R.string.cast_error_local_source
            return
        }
        dlna?.let { ctrl ->
            dlnaPositionMs = positionMs
            dlnaPendingSeekMs = if (positionMs > 3_000L) positionMs else -1L
            scope.launch {
                val ok = runCatching { ctrl.load(url, title, dlnaMimeType(url, mimeType)) }
                    .onFailure { Log.w("CastManager", "DLNA load failed", it) }
                    .getOrDefault(false)
                if (dlna !== ctrl) return@launch
                if (ok) startDlnaPolling(ctrl) else {
                    _castError.value = R.string.cast_error_format_refused
                    disconnectDlna(stopRemote = false)
                    _castState.value = CastState.NotConnected
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

    fun play() {
        dlna?.let { c -> dlnaPlaying = true; scope.launch { c.play() }; return }
        currentSession?.remoteMediaClient?.play()
    }

    fun pause() {
        dlna?.let { c -> dlnaPlaying = false; scope.launch { c.pause() }; return }
        currentSession?.remoteMediaClient?.pause()
    }

    fun seekTo(positionMs: Long) {
        dlna?.let { c -> dlnaPositionMs = positionMs; scope.launch { c.seek(positionMs) }; return }
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
