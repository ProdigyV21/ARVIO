package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.C
import androidx.media3.common.ParserException
import androidx.media3.common.Player
import androidx.media3.common.util.StuckPlayerException
import androidx.media3.datasource.HttpDataSource
import java.io.EOFException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.ProtocolException
import java.net.SocketException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import okhttp3.internal.http2.StreamResetException

/** How long a live stream may buffer without its buffer moving before it counts as stalled. */
internal const val LIVE_STUCK_BUFFERING_TIMEOUT_MS = 12_000

/** True when the player gave up because buffering stopped making progress, not on a load error. */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal fun Throwable.isStuckBuffering(): Boolean =
    generateSequence(this) { it.cause }.any { cause ->
        cause is StuckPlayerException && (
            cause.stuckType == StuckPlayerException.STUCK_BUFFERING_NO_PROGRESS ||
                cause.stuckType == StuckPlayerException.STUCK_BUFFERING_NOT_LOADING
            )
    }

private val terminalPlaybackHttpCodes = setOf(401, 403, 429, 444, 451, 513)

internal fun isMissingPlaybackResource(httpCode: Int?): Boolean = httpCode == 404 || httpCode == 410

internal fun shouldReusePreparedLiveHls(
    preparedIsHls: Boolean,
    isCatchup: Boolean,
    unsupportedContainer: Boolean,
    httpCode: Int?,
    // A Stalker link is temporary, so a reconnect must ask the portal for a new one.
    isStalker: Boolean = false,
): Boolean = preparedIsHls && !isCatchup && !unsupportedContainer && !isMissingPlaybackResource(httpCode) && !isStalker

internal fun shouldRetryLiveTvPlayback(
    httpCode: Int?,
    nextAttempt: Int,
    maxRetryCount: Int,
    isCatchup: Boolean,
): Boolean {
    if (nextAttempt > maxRetryCount || httpCode in terminalPlaybackHttpCodes) return false
    // Any live source can redirect to an expired URL. Refresh once before giving up;
    // catch-up retains its existing bounded budget for different archive candidates.
    return isCatchup || !isMissingPlaybackResource(httpCode) || nextAttempt == 1
}

internal const val MAX_LIVE_EOF_RESTARTS = 3
internal const val DEFAULT_LIVE_RETRY_REFILL_MS = 60_000L

/** Why a limited stream dropped, which decides whether the panel is worth asking before reconnecting. */
internal enum class LiveDrop { NetworkLost, Ambiguous, Other }

/** A read reset or EOF on a healthy network is what a panel's eviction looks like; an HTTP status keeps today's path. */
internal fun classifyLiveDrop(error: Throwable, hasActiveNetwork: Boolean, offlineWithin60s: Boolean): LiveDrop {
    val chain = generateSequence(error) { it.cause }.take(16).toList()
    if (chain.any { it is HttpDataSource.InvalidResponseCodeException || it is ParserException }) return LiveDrop.Other
    if (chain.any { it is UnknownHostException || it is ConnectException || it is NoRouteToHostException || it is InterruptedIOException }) {
        return LiveDrop.NetworkLost
    }
    val readFailed = chain.any {
        it is SocketException || it is EOFException || it is ProtocolException || it is SSLException || it is StreamResetException
    }
    return when {
        !readFailed -> LiveDrop.Other
        !hasActiveNetwork || offlineWithin60s -> LiveDrop.NetworkLost
        else -> LiveDrop.Ambiguous
    }
}

/** The next attempt number; a stream that has played for a while starts a fresh budget at its next error. No timer. */
internal fun liveRetryAttempt(current: Int, readySinceMs: Long?, nowMs: Long): Int =
    if (readySinceMs != null && nowMs - readySinceMs >= DEFAULT_LIVE_RETRY_REFILL_MS) 1 else current + 1

/** A clean end never refills the budget, so a finite TS item cannot loop for ever. */
internal fun liveDropAttempt(current: Int, readySinceMs: Long?, nowMs: Long, cleanEof: Boolean): Int =
    if (cleanEof) current + 1 else liveRetryAttempt(current, readySinceMs, nowMs)

internal fun liveEofRestartAllowed(restarts: Int): Boolean = restarts < MAX_LIVE_EOF_RESTARTS

/** A live stream has no legitimate end, so reaching one is a drop that Media3 reports as finished. */
internal fun isLiveDropEnd(
    playbackState: Int,
    durationMs: Long,
    isCatchup: Boolean,
    limited: Boolean,
    isLiveItem: Boolean = false,
): Boolean =
    // A live TS that ends cleanly gets a duration from its last sample, but its window stays live.
    limited && !isCatchup && playbackState == Player.STATE_ENDED && (durationMs == C.TIME_UNSET || isLiveItem)

/** The cause of the synthetic error a clean end goes down the retry path with. */
internal class LiveCleanEofException : EOFException("Live stream ended")
