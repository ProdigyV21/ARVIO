package com.arflix.tv.ui.screens.player.engine.exoplayer

import android.os.SystemClock
import androidx.media3.common.C
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

internal const val VOD_READ_TIMEOUT_SECONDS = 30L

internal fun useVodPlaybackPolicy(stream: com.arflix.tv.data.model.StreamSource?, isLive: Boolean, url: String?): Boolean {
    if (isLive || url == null || stream?.iptvSourceId != null ||
        com.arflix.tv.data.model.IptvVodSourceIds.isIptvVodAddonId(stream?.addonId)) return false
    val host = runCatching { java.net.URI(url).host }.getOrNull()
    return host != null && host !in setOf("localhost", "127.0.0.1", "::1", "[::1]")
}

internal fun vodRetryAfterMs(value: String?, nowMs: Long): Long? {
    val text = value?.trim()?.takeIf { it.isNotEmpty() } ?: return null
    text.toLongOrNull()?.let { seconds ->
        return seconds.coerceIn(0L, Long.MAX_VALUE / 1000L) * 1000L
    }
    return runCatching {
        val parser = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US).apply {
            timeZone = TimeZone.getTimeZone("GMT")
            isLenient = false
        }
        parser.parse(text)?.time?.let { (it - nowMs).coerceAtLeast(0L) }
    }.getOrNull()
}

/** VOD only: IPTV keeps its provider/session-aware retry and URL renewal policy. */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal class VodLoadErrorHandlingPolicy(
    private val progressive: Boolean,
    private val elapsedMs: () -> Long = SystemClock::elapsedRealtime,
    private val wallMs: () -> Long = System::currentTimeMillis,
) : DefaultLoadErrorHandlingPolicy(8) {
    private var throttleTask: Long? = null
    private var throttleStarted = 0L

    @Synchronized
    override fun getRetryDelayMsFor(info: LoadErrorHandlingPolicy.LoadErrorInfo): Long {
        val causes = generateSequence<Throwable>(info.exception) { it.cause }.take(16).toList()
        val http = causes.filterIsInstance<HttpDataSource.InvalidResponseCodeException>().firstOrNull()
        val code = http?.responseCode
        // Expired credentials reach ARVIO's existing same-source URL refresh immediately.
        if (code == 401 || code == 403 ||
            (code in listOf(400, 404, 410) && (progressive || info.mediaLoadData.dataType == C.DATA_TYPE_MANIFEST))) {
            return C.TIME_UNSET
        }
        if (code == 429 || code == 503) {
            val task = info.loadEventInfo.loadTaskId
            if (throttleTask != task) {
                throttleTask = task
                throttleStarted = elapsedMs()
            }
            val remaining = 30_000L - (elapsedMs() - throttleStarted).coerceAtLeast(0L)
            val serverDelay = http?.headerFields.orEmpty().entries
                .firstOrNull { it.key.equals("Retry-After", ignoreCase = true) }?.value?.firstOrNull()
                ?.let { vodRetryAfterMs(it, wallMs()) }
            val delay = maxOf(serverDelay ?: 0L, minOf(info.errorCount * 1000L, 5_000L))
            // Do not retry earlier than requested, or leave a loader retrying without a bound.
            return if (info.errorCount >= 8 || remaining <= 0L || delay > remaining) C.TIME_UNSET else delay
        }
        if (causes.any { it is SocketTimeoutException }) {
            return when (info.errorCount) {
                1 -> 500L
                2 -> 1_000L
                else -> C.TIME_UNSET
            }
        }
        return if (info.errorCount > 3) C.TIME_UNSET else super.getRetryDelayMsFor(info)
    }

    @Synchronized
    override fun getMinimumLoadableRetryCount(dataType: Int): Int = 8

    @Synchronized
    override fun onLoadTaskConcluded(loadTaskId: Long) {
        if (throttleTask == loadTaskId) throttleTask = null
        super.onLoadTaskConcluded(loadTaskId)
    }
}
