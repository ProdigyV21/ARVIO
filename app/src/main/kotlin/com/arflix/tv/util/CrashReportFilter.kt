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
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import javax.net.ssl.SSLHandshakeException

/**
 * Filters expected, handled failures out of paid crash-reporting quota.
 *
 * Real crashes still go through Sentry. These rules are for failures that are
 * already handled by the app or caused by user/network/provider state.
 */
object CrashReportFilter {
    private const val MAX_TRACKED_HANDLED_SIGNATURES = 256

    private val alwaysIgnoredClassNames = setOf(
        "JobCancellationException",
        "LeftCompositionCancellationException",
        "ModifierNodeDetachedCancellationException",
        "PointerEventTimeoutCancellationException"
    )

    private val handledOnlyClassNames = setOf(
        "HttpRequestException",
        "HttpRequestTimeoutException",
        "ClientRequestException",
        "ServerResponseException",
        "RedirectResponseException",
        "UnknownRestException",
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
        "provided playback url could not be resolved",
        "source lookup failed",
        "complete epg backfill timed out",
        "complete epg backfill returned empty guide",
        "iptv load timed out",
        "playlist loaded but contains no channels",
        "m3u request failed",
        "expected url scheme 'http' or 'https'",
        "expected url scheme \"http\" or \"https\"",
        "incomplete trakt watchlist fetch",
        "trakt credentials not configured",
        "trakt token request failed",
        "trakt continue watching hydration returned zero items",
        "trakt rate limit",
        "rate limit",
        "too many requests",
        "http 401",
        "http 403",
        "http 404",
        "http 429",
        "bad http status",
        "exponential backoff active",
        "cache overflow",
        "queue overflow",
        "ratelimit_backoff",
        "error_usage_exceeded",
        "send_error",
        "network_error",
        "jwt expired",
        "invalid jwt",
        "token is expired",
        "unable to parse or verify signature",
        "supabase.co",
        "auth/v1/logout",
        "row-level security policy",
        "chain validation failed",
        "unable to resolve host",
        "failed to connect",
        "request timeout has expired"
    )

    private val handledExceptionCounts = ConcurrentHashMap<String, AtomicInteger>()

    fun shouldReportHandledException(throwable: Throwable): Boolean {
        return dropReasonForHandledException(throwable) == null
    }

    fun shouldSendSentryEvent(throwable: Throwable?, level: SentryLevel?): Boolean {
        if (throwable == null) return true
        if (isAlwaysIgnored(throwable)) return false
        if (level == SentryLevel.FATAL) return true
        return shouldReportHandledException(throwable)
    }

    fun shouldSampleHandledException(throwable: Throwable, context: Map<String, String> = emptyMap()): Boolean {
        if (handledExceptionCounts.size > MAX_TRACKED_HANDLED_SIGNATURES) {
            handledExceptionCounts.clear()
        }
        val count = handledExceptionCounts
            .getOrPut(handledExceptionSignature(throwable, context)) { AtomicInteger(0) }
            .incrementAndGet()
        return count <= 3 || count == 10 || count == 50 || count == 100
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

    private fun handledExceptionSignature(throwable: Throwable, context: Map<String, String>): String {
        val root = rootCause(throwable)
        val area = context["error_area"].orEmpty()
        val flow = context["cloud_flow"].orEmpty()
        val phase = context["trakt_phase"]
            ?: context["player_phase"]
            ?: context["iptv_phase"]
            ?: context["phase"]
            ?: ""
        val message = root.message
            .orEmpty()
            .replace(URL_PATTERN, "[URL]")
            .replace(TOKEN_PATTERN, "[TOKEN]")
            .take(140)
        return "${root::class.java.name}|$area|$flow|$phase|$message"
    }

    private fun rootCause(throwable: Throwable): Throwable {
        var current = throwable
        val seen = mutableSetOf<Throwable>()
        while (current.cause != null && seen.add(current)) {
            current = current.cause ?: break
        }
        return current
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

    private val URL_PATTERN = Regex("\\b(?:https?|wss?|ftp|file|content)://[^\\s\"'<>]+", RegexOption.IGNORE_CASE)
    private val TOKEN_PATTERN = Regex("\\b[A-Za-z0-9_-]{16,}\\.[A-Za-z0-9_-]{16,}\\.[A-Za-z0-9_-]{16,}\\b")
}
