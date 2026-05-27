package com.arflix.tv.util

import io.sentry.SentryLevel
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.PortUnreachableException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLHandshakeException

/**
 * Filters expected, handled failures out of paid crash-reporting quota.
 *
 * Real crashes still go through Sentry. These rules are for failures that are
 * already handled by the app or caused by user/network/provider state.
 */
object CrashReportFilter {
    private val alwaysIgnoredClassNames = setOf(
        "JobCancellationException",
        "LeftCompositionCancellationException",
        "ModifierNodeDetachedCancellationException",
        "PointerEventTimeoutCancellationException"
    )

    private val handledOnlyClassNames = setOf(
        "HttpRequestTimeoutException",
        "TimeoutCancellationException"
    )

    private val ignoredMessageFragments = listOf(
        "not logged in",
        "job was cancelled",
        "was cancelled",
        "playback error displayed",
        "selected stream playback failed",
        "playback source list empty",
        "playback imdb id missing",
        "complete epg backfill timed out",
        "iptv load timed out",
        "m3u request failed",
        "incomplete trakt watchlist fetch",
        "http 401",
        "jwt expired",
        "invalid jwt",
        "token is expired",
        "row-level security policy",
        "chain validation failed",
        "unable to resolve host",
        "failed to connect",
        "request timeout has expired"
    )

    fun shouldReportHandledException(throwable: Throwable): Boolean {
        return dropReasonForHandledException(throwable) == null
    }

    fun shouldSendSentryEvent(throwable: Throwable?, level: SentryLevel?): Boolean {
        if (throwable == null) return true
        if (isAlwaysIgnored(throwable)) return false
        if (level == SentryLevel.FATAL) return true
        return shouldReportHandledException(throwable)
    }

    fun dropReasonForHandledException(throwable: Throwable): String? {
        if (isAlwaysIgnored(throwable)) return "cancellation"
        if (containsClassName(throwable, handledOnlyClassNames)) return "timeout"
        if (containsNetworkFailure(throwable)) return "network"
        if (containsIgnoredMessage(throwable)) return "expected_state"
        return null
    }

    private fun isAlwaysIgnored(throwable: Throwable): Boolean {
        return throwable is CancellationException ||
            containsClassName(throwable, alwaysIgnoredClassNames)
    }

    private fun containsNetworkFailure(throwable: Throwable): Boolean {
        return throwable.anyCause { cause ->
            cause is UnknownHostException ||
                cause is SocketTimeoutException ||
                cause is ConnectException ||
                cause is NoRouteToHostException ||
                cause is PortUnreachableException ||
                cause is SSLHandshakeException ||
                cause is InterruptedIOException ||
                (cause is SocketException && cause.message.orEmpty().contains("timed out", ignoreCase = true))
        }
    }

    private fun containsClassName(throwable: Throwable, classNames: Set<String>): Boolean {
        return throwable.anyCause { cause ->
            cause::class.java.simpleName in classNames ||
                cause::class.java.name in classNames
        }
    }

    private fun containsIgnoredMessage(throwable: Throwable): Boolean {
        return throwable.anyCause { cause ->
            val text = buildString {
                append(cause::class.java.simpleName)
                append(' ')
                append(cause.message.orEmpty())
            }
            ignoredMessageFragments.any { fragment ->
                text.contains(fragment, ignoreCase = true)
            }
        }
    }

    private fun Throwable.anyCause(predicate: (Throwable) -> Boolean): Boolean {
        var current: Throwable? = this
        val seen = mutableSetOf<Throwable>()
        while (current != null && seen.add(current)) {
            if (predicate(current)) return true
            current = current.cause
        }
        return false
    }
}
