package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
import com.arflix.tv.network.iptvProviderCooldownMs
import com.arflix.tv.network.isIptvProviderRequestPaused
import com.arflix.tv.network.isIptvStreamSessionClosed
import java.util.concurrent.atomic.AtomicLong

internal const val DEFAULT_IPTV_REPEAT_DROP_WINDOW_MS = 60_000L

private const val NO_DROP_YET = Long.MIN_VALUE

/** What a load that already read bytes does: keep Media3's retry, or surface once the buffer plays out. */
internal enum class IptvMidStreamDrop { RETRY, SURFACE, SURFACE_REPEATED }

/**
 * One request is the whole stream only for progressive: a DASH chunk keeps Media3's own retry.
 * Surfacing exists so the app can ask the panel before reconnecting, so without that check
 * or a Stalker link to renew it only spends the retry budget. Catch-up keeps the retry because the app's own moves to another archive URL.
 */
internal fun liveMidStreamDrop(
    item: MediaItem,
    sessionBound: Boolean,
    panelCheckAllowed: Boolean,
    isCatchup: Boolean,
    // Media3 would replay a temporary Stalker link the portal may refuse, the app's reconnect asks for a new one.
    isStalker: Boolean = false,
): IptvMidStreamDrop {
    val config = item.localConfiguration
    val progressive = config != null && Util.inferContentTypeForUriAndMimeType(config.uri, config.mimeType) == C.CONTENT_TYPE_OTHER
    val appReconnects = panelCheckAllowed || isStalker
    return if (sessionBound && appReconnects && !isCatchup && progressive) IptvMidStreamDrop.SURFACE else IptvMidStreamDrop.RETRY
}

/** A file of known length resumes by range, so only a drop that keeps coming back reaches the rungs, and only when they can ask the panel. */
internal fun vodMidStreamDrop(isHls: Boolean, isDash: Boolean, panelCheckAllowed: Boolean): IptvMidStreamDrop =
    if (isHls || isDash || !panelCheckAllowed) IptvMidStreamDrop.RETRY else IptvMidStreamDrop.SURFACE_REPEATED

/**
 * Keeps provider rejections away from Media3's own retries. A surfaced mid-stream drop reaches
 * the app's reconnect, which releases the session's allowance instead of silently reopening the URL.
 */
internal class IptvLoadErrorHandlingPolicy(
    private val midStreamDrop: IptvMidStreamDrop = IptvMidStreamDrop.RETRY,
    private val clock: () -> Long = System::currentTimeMillis,
) : DefaultLoadErrorHandlingPolicy(2) {
    private val lastDropAtMs = AtomicLong(NO_DROP_YET)

    override fun getRetryDelayMsFor(errorInfo: LoadErrorHandlingPolicy.LoadErrorInfo): Long {
        if (errorInfo.exception.iptvHlsFormatDetected() != null) return C.TIME_UNSET
        if (isIptvProviderRequestPaused(errorInfo.exception)) return C.TIME_UNSET
        if (isIptvStreamSessionClosed(errorInfo.exception)) return C.TIME_UNSET
        if (errorInfo.loadEventInfo.bytesLoaded > 0L && surfacesDrop()) return C.TIME_UNSET
        val status = generateSequence<Throwable>(errorInfo.exception) { it.cause }
            .take(16).filterIsInstance<HttpDataSource.InvalidResponseCodeException>()
            .firstOrNull()?.responseCode
        return if (iptvProviderCooldownMs(status ?: 0, null, 0L) > 0L) C.TIME_UNSET
        else super.getRetryDelayMsFor(errorInfo)
    }

    private fun surfacesDrop(): Boolean = when (midStreamDrop) {
        IptvMidStreamDrop.RETRY -> false
        IptvMidStreamDrop.SURFACE -> true
        IptvMidStreamDrop.SURFACE_REPEATED -> {
            val now = clock()
            val last = lastDropAtMs.getAndSet(now)
            last != NO_DROP_YET && now - last <= DEFAULT_IPTV_REPEAT_DROP_WINDOW_MS
        }
    }
}
