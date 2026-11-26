package com.arflix.tv.recording

import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.arflix.tv.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.UUID
import java.util.concurrent.TimeUnit

/**
 * Wakes the recording service for the next recording. Within [LiveRecordingService.STAY_AHEAD_MS]
 * of a start the service is already waiting; further off, WorkManager wakes the app a few
 * minutes early. Android 12+ may refuse a service start from the background: the user then
 * gets a notification whose tap starts the recording.
 */
object LiveRecordingScheduler {
    private const val WORK_NAME = "live-recording-wake"
    private const val EARLY_MS = 5 * 60_000L
    private const val NOTIFICATION_ID = 0x5EC1

    /** Adds a recording and makes sure something will start it. */
    fun add(context: Context, recording: LiveRecording) {
        LiveRecordingStore.upsert(context, recording)
        kick(context)
    }

    fun cancel(context: Context, id: String) {
        val recording = LiveRecordingStore.snapshot(context).firstOrNull { it.id == id } ?: return
        if (recording.isActive) LiveRecordingService.stop(context, id)
        else LiveRecordingStore.modify(context, id) { it.copy(status = LiveRecording.STATUS_CANCELLED) }
        schedule(context)
    }

    /**
     * Call while the app is in front (app start, scheduling): starts the service when a
     * recording is running, due, or close, and otherwise plans a wake-up.
     */
    fun kick(context: Context) {
        val now = System.currentTimeMillis()
        val list = LiveRecordingStore.snapshot(context)
        val needsService = list.any { recording ->
            when (recording.dueAt(now)) {
                RecordingDue.Start, RecordingDue.Missed -> true
                RecordingDue.Wait -> recording.recordFrom - now <= LiveRecordingService.STAY_AHEAD_MS
                RecordingDue.Done -> false
            }
        }
        if (needsService) LiveRecordingService.start(context)
        schedule(context)
    }

    /** Plans one wake-up: shortly before the next recording that the service is not already waiting for. */
    fun schedule(context: Context) {
        val now = System.currentTimeMillis()
        val next = LiveRecordingStore.snapshot(context)
            .filter { it.dueAt(now) == RecordingDue.Wait }
            .minByOrNull { it.recordFrom }
        val manager = WorkManager.getInstance(context.applicationContext)
        if (next == null) {
            manager.cancelUniqueWork(WORK_NAME)
            return
        }
        val delay = (next.recordFrom - EARLY_MS - now).coerceAtLeast(0L)
        manager.enqueueUniqueWork(
            WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequestBuilder<LiveRecordingWakeWorker>().setInitialDelay(delay, TimeUnit.MILLISECONDS).build(),
        )
    }

    fun newId(): String = UUID.randomUUID().toString()

    internal fun notifyStartBlocked(context: Context) {
        val next = LiveRecordingStore.snapshot(context).filter { !it.isFinal }.minByOrNull { it.recordFrom } ?: return
        LiveRecordingService.ensureChannel(context)
        val flags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        val intent = LiveRecordingService.startIntent(context)
        val tap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) PendingIntent.getForegroundService(context, 1, intent, flags)
        else PendingIntent.getService(context, 1, intent, flags)
        val notification = NotificationCompat.Builder(context, LiveRecordingService.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_monochrome)
            .setContentTitle(context.getString(R.string.recording_notification_tap_to_start, next.title))
            .setContentText(context.getString(R.string.recording_notification_tap_hint))
            .setContentIntent(tap)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        runCatching { NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification) }
    }
}

@HiltWorker
class LiveRecordingWakeWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) : Worker(context, params) {
    override fun doWork(): Result {
        val now = System.currentTimeMillis()
        val due = LiveRecordingStore.snapshot(applicationContext).any {
            val state = it.dueAt(now)
            state == RecordingDue.Start || (state == RecordingDue.Wait && it.recordFrom - now <= LiveRecordingService.STAY_AHEAD_MS)
        }
        if (due && !LiveRecordingService.start(applicationContext)) LiveRecordingScheduler.notifyStartBlocked(applicationContext)
        if (!due) LiveRecordingScheduler.schedule(applicationContext)
        return Result.success()
    }
}
