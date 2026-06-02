package com.arflix.tv.worker

import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.arflix.tv.data.db.DownloadStatus
import com.arflix.tv.data.repository.DownloadsRepository
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.util.formatBytes
import dagger.hilt.android.EntryPointAccessors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class DownloadWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    companion object {
        const val KEY_DOWNLOAD_ID = "download_id"
        const val KEY_STREAM_URL = "stream_url"
        const val KEY_SUBTITLE_URL = "subtitle_url"
        const val KEY_USER_AGENT = "user_agent"
        const val KEY_HEADERS = "headers"
        const val KEY_TITLE = "title"
        const val KEY_SEASON = "season"
        const val KEY_EPISODE = "episode"
        const val NOTIFICATION_CHANNEL_ID = "downloads"
        private const val PROGRESS_UPDATE_INTERVAL_MS = 500L
        private const val BUFFER_SIZE = 8 * 1024
    }

    private val repository: DownloadsRepository by lazy {
        EntryPointAccessors.fromApplication(
            applicationContext,
            RepositoryAccessEntryPoint::class.java
        ).downloadsRepository()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .build()
    }

    override suspend fun doWork(): Result {
        val downloadId = inputData.getLong(KEY_DOWNLOAD_ID, -1L)
        val streamUrl = inputData.getString(KEY_STREAM_URL) ?: return Result.failure()
        val subtitleUrl = inputData.getString(KEY_SUBTITLE_URL)
        val userAgent = inputData.getString(KEY_USER_AGENT).orEmpty()
        val headersJson = inputData.getString(KEY_HEADERS).orEmpty()
        val streamHeaders: Map<String, String> = if (headersJson.isNotBlank()) {
            runCatching {
                val type = object : TypeToken<Map<String, String>>() {}.type
                Gson().fromJson<Map<String, String>>(headersJson, type) ?: emptyMap()
            }.getOrElse { emptyMap() }
        } else emptyMap()

        if (downloadId < 0) return Result.failure()

        val downloadsDir = repository.downloadsDir().also { it.mkdirs() }
        val urlPath = streamUrl.substringBefore('?').substringBefore('#')
        val ext = urlPath.substringAfterLast('.', "").take(4).filter { it.isLetterOrDigit() }
            .ifEmpty { "mp4" }
        val title = inputData.getString(KEY_TITLE).orEmpty()
        val season = inputData.getInt(KEY_SEASON, -1).takeIf { it > 0 }
        val episode = inputData.getInt(KEY_EPISODE, -1).takeIf { it > 0 }
        val baseName = buildFilename(title, season, episode, downloadId)
        val videoFile = File(downloadsDir, "$baseName.$ext")

        return try {
            runCatching { setForeground(buildForegroundInfo(downloadId, "Starting…", 0)) }
            repository.updateStatusInternal(downloadId, DownloadStatus.DOWNLOADING)

            downloadVideo(streamUrl, videoFile, downloadId, userAgent, streamHeaders)

            if (isStopped) return Result.success()

            val subtitleResult = if (!subtitleUrl.isNullOrBlank()) {
                downloadSubtitle(subtitleUrl, downloadId, downloadsDir, userAgent, streamHeaders)
            } else null

            repository.markCompletedInternal(
                id = downloadId,
                localUri = videoFile.absolutePath,
                fileSize = videoFile.length(),
                subtitleLocalUri = subtitleResult?.first,
                subtitleLang = subtitleResult?.second
            )

            notifyComplete(downloadId)
            Result.success()
        } catch (e: Exception) {
            if (isStopped) return Result.success()
            repository.markFailedInternal(downloadId)
            if (runAttemptCount < 2) Result.retry() else Result.failure()
        }
    }

    private suspend fun downloadVideo(
        url: String,
        dest: File,
        downloadId: Long,
        userAgent: String,
        headers: Map<String, String> = emptyMap()
    ) {
        val existingBytes = if (dest.exists()) dest.length() else 0L
        val request = buildRequest(url, userAgent, headers, rangeStart = existingBytes)

        okHttpClient.newCall(request).execute().use { response ->
            // 206 Partial Content = server supports range request, resume from offset.
            // 200 OK = no range support, must restart from zero.
            val isResume = response.code == 206 && existingBytes > 0
            if (!response.isSuccessful) error("HTTP ${response.code}")

            val body = response.body ?: error("Empty body")
            val contentLength = body.contentLength().takeIf { it > 0L }
            val total = if (isResume && contentLength != null) existingBytes + contentLength
                        else contentLength
            var downloaded = if (isResume) existingBytes else 0L
            var lastUpdate = System.currentTimeMillis()

            // Append if resuming; overwrite (clearing stale partial data) if server ignored Range.
            if (!isResume && dest.exists()) dest.delete()

            body.byteStream().use { input ->
                FileOutputStream(dest, isResume).use { output ->
                    val buffer = ByteArray(BUFFER_SIZE)
                    while (!isStopped) {
                        val read = input.read(buffer)
                        if (read <= 0) break
                        output.write(buffer, 0, read)
                        downloaded += read

                        val now = System.currentTimeMillis()
                        if (now - lastUpdate >= PROGRESS_UPDATE_INTERVAL_MS) {
                            lastUpdate = now
                            val progress = total?.let {
                                ((downloaded * 100) / it).toInt().coerceIn(0, 99)
                            } ?: 0
                            repository.updateProgressInternal(downloadId, progress, downloaded)
                            runCatching {
                                setForeground(
                                    buildForegroundInfo(
                                        downloadId,
                                        total?.let { "$progress%" } ?: formatBytes(downloaded),
                                        progress
                                    )
                                )
                            }
                        }
                    }
                    output.flush()
                }
            }
        }
    }

    private fun downloadSubtitle(
        url: String,
        downloadId: Long,
        dir: File,
        userAgent: String,
        headers: Map<String, String> = emptyMap()
    ): Pair<String, String>? = runCatching {
        val ext = url.substringAfterLast('.', "srt").take(4).filter { it.isLetterOrDigit() }
        val file = File(dir, "subtitle_${downloadId}.$ext")
        val request = buildRequest(url, userAgent, headers)
        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return@runCatching null
            val body = response.body ?: return@runCatching null
            body.byteStream().use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                    output.flush()
                }
            }
        }
        file.absolutePath to (url.substringAfterLast('/').substringBefore('.'))
    }.getOrNull()

    private fun buildRequest(
        url: String,
        userAgent: String,
        headers: Map<String, String> = emptyMap(),
        rangeStart: Long = 0L
    ): Request {
        val builder = Request.Builder().url(url)
        if (userAgent.isNotBlank()) builder.header("User-Agent", userAgent)
        headers.forEach { (key, value) -> if (key.isNotBlank()) builder.header(key, value) }
        if (rangeStart > 0) builder.header("Range", "bytes=$rangeStart-")
        return builder.build()
    }

    private fun buildForegroundInfo(id: Long, text: String, progress: Int): ForegroundInfo {
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle("Downloading")
            .setContentText(text)
            .setProgress(100, progress, progress == 0)
            .setOngoing(true)
            .setSilent(true)
            .build()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(
                (10000 + id).toInt(),
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            ForegroundInfo((10000 + id).toInt(), notification)
        }
    }

    private fun notifyComplete(id: Long) {
        val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_download_done)
            .setContentTitle("Download complete")
            .setAutoCancel(true)
            .build()
        nm.notify((10000 + id).toInt(), notification)
    }

    private fun buildFilename(title: String, season: Int?, episode: Int?, fallbackId: Long): String {
        val safe = title.replace(Regex("[^A-Za-z0-9 ]"), "").trim().replace(" ", ".")
        val base = safe.ifEmpty { "download_$fallbackId" }
        val episodePart = if (season != null && episode != null) {
            ".S${season.toString().padStart(2, '0')}E${episode.toString().padStart(2, '0')}"
        } else ""
        return "$base$episodePart"
    }
}
