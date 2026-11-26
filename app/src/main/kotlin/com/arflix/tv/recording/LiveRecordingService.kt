package com.arflix.tv.recording

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.StalkerPortalSupport
import com.arflix.tv.network.IptvPlaybackConnections
import com.arflix.tv.network.IptvProviderRequestGuard
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.screens.player.safePlaybackHeaders
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Runs recordings while their window is open, and stays up (with its notification) for ones
 * that start soon, so a game scheduled this evening starts on time even when the app is in
 * the background. Further-off recordings are woken by [LiveRecordingScheduler].
 */
@AndroidEntryPoint
class LiveRecordingService : Service() {
    @Inject lateinit var iptvRepository: IptvRepository

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val running = java.util.concurrent.ConcurrentHashMap<String, Job>()
    private var loop: Job? = null

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(IptvPlaybackConnections())
            .connectionPool(ConnectionPool(4, 2, TimeUnit.MINUTES))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .addInterceptor(IptvProviderRequestGuard.shared.preflightInterceptor(playback = true))
            .addNetworkInterceptor(IptvProviderRequestGuard.shared.playbackInterceptor())
            .dns(OkHttpProvider.dns)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startInForeground()
        when (intent?.action) {
            ACTION_STOP -> intent.getStringExtra(EXTRA_ID)?.let(::stopRecording)
        }
        if (loop?.isActive != true) loop = scope.launch { runLoop() }
        return START_STICKY
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    private suspend fun runLoop() {
        while (true) {
            val now = System.currentTimeMillis()
            val list = LiveRecordingStore.snapshot(this)
            list.forEach { recording ->
                when (recording.dueAt(now)) {
                    RecordingDue.Start -> if (running[recording.id]?.isActive != true) {
                        running[recording.id] = scope.launch { record(recording.id) }
                    }
                    RecordingDue.Missed -> if (running[recording.id]?.isActive != true) {
                        LiveRecordingStore.modify(this, recording.id) {
                            if (it.bytes > 0) it.copy(status = LiveRecording.STATUS_DONE)
                            else it.copy(status = LiveRecording.STATUS_FAILED, error = getString(R.string.recording_error_missed))
                        }
                    }
                    else -> Unit
                }
            }
            running.entries.removeAll { !it.value.isActive }
            val soon = LiveRecordingStore.snapshot(this).any {
                it.dueAt(now) == RecordingDue.Wait && it.recordFrom - now <= STAY_AHEAD_MS
            }
            updateNotification()
            if (running.isEmpty() && !soon) break
            delay(LOOP_MS)
        }
        LiveRecordingScheduler.schedule(this)
        ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun stopRecording(id: String) {
        running.remove(id)?.cancel()
        LiveRecordingStore.modify(this, id) {
            if (it.bytes > 0) it.copy(status = LiveRecording.STATUS_DONE)
            else it.copy(status = LiveRecording.STATUS_CANCELLED)
        }
        updateNotification()
    }

    private suspend fun record(id: String) {
        val recording = LiveRecordingStore.snapshot(this).firstOrNull { it.id == id } ?: return
        val until = recording.recordUntil
        var filePath = recording.filePath
        var bytes = recording.bytes
        var lastError: String? = null
        val tried = HashSet<String>()
        val unusable = HashSet<String>()
        while (System.currentTimeMillis() < until) {
            val channel = recording.channels.firstOrNull { it.id !in tried && it.id !in unusable }
            if (channel == null) {
                // Every channel dropped: the game is still on, so start the round again shortly.
                if (recording.channels.all { it.id in unusable }) break
                tried.clear()
                delay(RETRY_ROUND_MS)
                continue
            }
            if (channel.drmInfo != null) {
                unusable += channel.id
                lastError = getString(R.string.recording_error_protected)
                continue
            }
            val policy = runCatching { iptvRepository.streamPolicyFor(channel) }.getOrNull()
            val session = policy?.let { IptvPlaybackConnections.sessions.tryOpenSpare(it.account, it.effectiveLimit) }
            if (policy != null && session == null) {
                // Every connection the subscription allows is in use (on this device). Wait for one
                // rather than cut off whoever is watching.
                LiveRecordingStore.modify(this, id) { it.copy(status = LiveRecording.STATUS_WAITING, error = getString(R.string.recording_waiting_connection)) }
                updateNotification()
                delay(WAIT_FOR_CONNECTION_MS)
                continue
            }
            LiveRecordingStore.modify(this, id) {
                it.copy(status = LiveRecording.STATUS_RECORDING, error = null, recordedChannelName = channel.name)
            }
            updateNotification()
            val outcome = try {
                val url = playableUrl(channel)
                val headers = (BASE_HEADERS + channel.requestHeaders).safePlaybackHeaders()
                LiveStreamRecorder(session?.callFactory(client) ?: client, headers).record(
                    url = url,
                    until = until,
                    output = { extension ->
                        filePath?.let { File(it) } ?: File(
                            LiveRecordingStore.directory(this),
                            recordingFileName(recording.title, recording.programmeStart, extension),
                        ).also { file ->
                            filePath = file.absolutePath
                            LiveRecordingStore.modify(this, id) { it.copy(filePath = file.absolutePath) }
                        }
                    },
                    onProgress = { total ->
                        val sum = bytes + total
                        LiveRecordingStore.modify(this, id) { it.copy(bytes = sum) }
                    },
                )
            } catch (e: Exception) {
                if (e.isRecordingCancellation()) throw e
                RecordOutcome.StreamFailed(e.message ?: e.javaClass.simpleName, 0L)
            } finally {
                session?.close()
            }
            bytes += outcome.bytes
            LiveRecordingStore.modify(this, id) { it.copy(bytes = bytes) }
            when (outcome) {
                is RecordOutcome.Completed -> break
                is RecordOutcome.DiskFull -> {
                    lastError = getString(R.string.recording_error_disk_full)
                    break
                }
                is RecordOutcome.Unsupported -> {
                    unusable += channel.id
                    lastError = getString(R.string.recording_error_protected)
                }
                is RecordOutcome.StreamFailed -> {
                    // The next channel carrying the game continues the same file.
                    tried += channel.id
                    lastError = getString(R.string.recording_error_stream, outcome.reason)
                }
            }
        }
        LiveRecordingStore.modify(this, id) {
            if (it.status == LiveRecording.STATUS_CANCELLED) it
            else if (bytes > 0) it.copy(status = LiveRecording.STATUS_DONE, error = lastError?.takeIf { e -> e == getString(R.string.recording_error_disk_full) })
            else it.copy(status = LiveRecording.STATUS_FAILED, error = lastError ?: getString(R.string.recording_error_missed))
        }
        updateNotification()
    }

    private suspend fun playableUrl(channel: IptvChannel): String {
        val raw = channel.streamUrl.trim()
        if (!channel.id.startsWith("stalker:") || StalkerPortalSupport.canPlayDirectLiveStream(channel, raw, isCatchup = false)) return raw
        val resolved = iptvRepository.resolveStalkerStreamUrl(channel.id, raw)?.trim().orEmpty()
        if (resolved.isNotBlank()) return resolved
        val direct = StalkerPortalSupport.sanitizePlaybackCommand(raw)
        if (StalkerPortalSupport.isRoutableStreamAddress(direct)) return direct
        throw IllegalStateException("Stalker portal returned no playable link")
    }

    private fun startInForeground() {
        ensureChannel(this)
        val notification = buildNotification()
        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC else 0
        ServiceCompat.startForeground(this, NOTIFICATION_ID, notification, type)
    }

    private fun updateNotification() {
        runCatching { NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, buildNotification()) }
    }

    private fun buildNotification(): Notification {
        val list = LiveRecordingStore.snapshot(this)
        val active = list.filter { it.isActive }
        val next = list.filter { it.status == LiveRecording.STATUS_SCHEDULED }.minByOrNull { it.recordFrom }
        val title = when {
            active.isNotEmpty() -> getString(R.string.recording_notification_recording, active.joinToString(", ") { it.title })
            next != null -> getString(R.string.recording_notification_scheduled, next.title)
            else -> getString(R.string.recording_notification_idle)
        }
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_monochrome)
            .setContentTitle(title)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_PROGRESS)
        packageManager.getLaunchIntentForPackage(packageName)?.let { launch ->
            builder.setContentIntent(PendingIntent.getActivity(this, 0, launch, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT))
        }
        active.firstOrNull()?.let { recording ->
            val stop = Intent(this, LiveRecordingService::class.java).setAction(ACTION_STOP).putExtra(EXTRA_ID, recording.id)
            builder.addAction(0, getString(R.string.recording_stop),
                PendingIntent.getService(this, recording.id.hashCode(), stop, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT))
        }
        return builder.build()
    }

    companion object {
        const val CHANNEL_ID = "live_recordings"
        private const val NOTIFICATION_ID = 0x5EC0
        private const val ACTION_STOP = "com.arflix.tv.recording.STOP"
        private const val EXTRA_ID = "id"
        private const val LOOP_MS = 10_000L
        private const val WAIT_FOR_CONNECTION_MS = 20_000L
        private const val RETRY_ROUND_MS = 30_000L
        /** Recordings starting within this time keep the service up, so they start on the minute. */
        const val STAY_AHEAD_MS = 3 * 60 * 60_000L
        private val BASE_HEADERS = mapOf(
            "Accept" to "*/*",
            "Accept-Encoding" to "identity",
            "User-Agent" to OkHttpProvider.userAgentOr("VLC/3.0.20 LibVLC/3.0.20"),
        )

        fun ensureChannel(context: Context) {
            NotificationManagerCompat.from(context).createNotificationChannel(
                NotificationChannelCompat.Builder(CHANNEL_ID, NotificationManagerCompat.IMPORTANCE_LOW)
                    .setName(context.getString(R.string.recording_channel_name))
                    .build()
            )
        }

        /** Must run while the app is in the foreground (or from a notification tap) on Android 12+. */
        fun start(context: Context): Boolean = try {
            ContextCompat.startForegroundService(context, Intent(context, LiveRecordingService::class.java))
            true
        } catch (e: Exception) {
            // ForegroundServiceStartNotAllowedException: the app is in the background.
            false
        }

        fun stop(context: Context, id: String) {
            runCatching {
                context.startService(Intent(context, LiveRecordingService::class.java).setAction(ACTION_STOP).putExtra(EXTRA_ID, id))
            }
        }

        fun startIntent(context: Context): Intent = Intent(context, LiveRecordingService::class.java)
    }
}
