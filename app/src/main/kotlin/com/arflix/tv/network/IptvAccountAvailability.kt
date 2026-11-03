package com.arflix.tv.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull

internal const val MAX_IPTV_FIRST_CHECK_MS = 2_500L
internal const val MAX_IPTV_CHECK_WAIT_MS = 6_000L
internal const val DEFAULT_IPTV_CHECK_POLL_MS = 2_000L
internal const val MAX_IPTV_RECONNECT_CHECK_WAIT_MS = 15_000L
internal const val DEFAULT_IPTV_RECONNECT_CHECK_POLL_MS = 3_000L

internal data class IptvAccountUsage(val active: Int, val max: Int)

internal sealed class IptvAdmission {
    data object Proceed : IptvAdmission()
    data class Full(val active: Int, val max: Int) : IptvAdmission()
}

/** active_cons is best effort: only an answer of full delays a stream, for a bounded time, and silence never blocks one. */
internal object IptvAccountAvailability {
    suspend fun admit(check: suspend () -> IptvAccountUsage?): IptvAdmission =
        poll(DEFAULT_IPTV_CHECK_POLL_MS, MAX_IPTV_CHECK_WAIT_MS, check)

    /** After a dropped stream the panel can still count this device's own connection for a few seconds, so it waits longer. */
    suspend fun admitReconnect(check: suspend () -> IptvAccountUsage?): IptvAdmission =
        poll(DEFAULT_IPTV_RECONNECT_CHECK_POLL_MS, MAX_IPTV_RECONNECT_CHECK_WAIT_MS, check)

    /** An optional stream such as a Home preview asks once and never polls: only a first answer of full refuses it. */
    suspend fun admitOnce(check: suspend () -> IptvAccountUsage?): IptvAdmission {
        val answer = withTimeoutOrNull(MAX_IPTV_FIRST_CHECK_MS) { ask(check) } ?: return IptvAdmission.Proceed
        return if (answer.active < answer.max) IptvAdmission.Proceed else IptvAdmission.Full(answer.active, answer.max)
    }

    private suspend fun poll(pollMs: Long, deadlineMs: Long, check: suspend () -> IptvAccountUsage?): IptvAdmission {
        var latest: IptvAccountUsage? = null
        val admitted = withTimeoutOrNull(deadlineMs) {
            val first = withTimeoutOrNull(MAX_IPTV_FIRST_CHECK_MS) { ask(check) } ?: return@withTimeoutOrNull true
            latest = first
            var hasRoom = first.active < first.max
            while (!hasRoom) {
                delay(pollMs)
                ask(check)?.let { answer ->
                    latest = answer
                    hasRoom = answer.active < answer.max
                }
            }
            true
        }
        val full = latest
        return if (admitted == true || full == null) IptvAdmission.Proceed else IptvAdmission.Full(full.active, full.max)
    }

    private suspend fun ask(check: suspend () -> IptvAccountUsage?): IptvAccountUsage? = try {
        check()
    } catch (e: Exception) {
        if (e is kotlinx.coroutines.CancellationException) throw e
        null
    }
}
