package com.arflix.tv.cast

import android.content.Context
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
        object NotAvailable : CastState()
        object NotConnected : CastState()
        object Connecting : CastState()
        data class Casting(val deviceName: String) : CastState()
    }

    private val _castState = MutableStateFlow<CastState>(CastState.NotConnected)
    val castState: StateFlow<CastState> = _castState.asStateFlow()

    private var castContext: CastContext? = null
    private var currentSession: CastSession? = null

    private val sessionListener = object : SessionManagerListener<CastSession> {
        override fun onSessionStarting(session: CastSession) {
            _castState.value = CastState.Connecting
        }

        override fun onSessionStarted(session: CastSession, sessionId: String) {
            currentSession = session
            _castState.value = CastState.Casting(session.castDevice?.friendlyName ?: "Chromecast")
        }

        override fun onSessionStartFailed(session: CastSession, error: Int) {
            currentSession = null
            _castState.value = CastState.NotConnected
        }

        override fun onSessionEnding(session: CastSession) {}

        override fun onSessionEnded(session: CastSession, error: Int) {
            currentSession = null
            _castState.value = CastState.NotConnected
        }

        override fun onSessionResuming(session: CastSession, sessionId: String) {
            _castState.value = CastState.Connecting
        }

        override fun onSessionResumed(session: CastSession, wasSuspended: Boolean) {
            currentSession = session
            _castState.value = CastState.Casting(session.castDevice?.friendlyName ?: "Chromecast")
        }

        override fun onSessionResumeFailed(session: CastSession, error: Int) {
            currentSession = null
            _castState.value = CastState.NotConnected
        }

        override fun onSessionSuspended(session: CastSession, reason: Int) {}
    }

    fun initialize(isMobile: Boolean) {
        if (!isMobile) {
            _castState.value = CastState.NotAvailable
            return
        }
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
                    _castState.value = CastState.NotAvailable
                }
        } catch (_: Exception) {
            _castState.value = CastState.NotAvailable
        }
    }

    fun loadMedia(url: String, title: String, imageUrl: String?, mimeType: String, positionMs: Long) {
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
        client.load(request)
    }

    fun play() {
        currentSession?.remoteMediaClient?.play()
    }

    fun pause() {
        currentSession?.remoteMediaClient?.pause()
    }

    fun seekTo(positionMs: Long) {
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
        currentSession?.remoteMediaClient?.approximateStreamPosition ?: 0L

    fun getApproximateDuration(): Long =
        currentSession?.remoteMediaClient?.mediaInfo?.streamDuration ?: 0L

    fun isRemotePlaying(): Boolean =
        currentSession?.remoteMediaClient?.isPlaying == true

    fun disconnect() {
        castContext?.sessionManager?.endCurrentSession(true)
    }

    fun getRouteSelector(): MediaRouteSelector =
        MediaRouteSelector.Builder()
            .addControlCategory(
                CastMediaControlIntent.categoryForCast(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID)
            )
            .build()
}
