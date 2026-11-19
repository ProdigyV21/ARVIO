package com.arflix.tv.network

import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull

internal const val MAX_IPTV_FIRST_CHECK_MS = 2_500L
internal const val MAX_IPTV_CHECK_WAIT_MS = 6_000L
internal const val DEFAULT_IPTV_CHECK_POLL_MS = 2_000L
internal const val MAX_IPTV_RECONNECT_CHECK_WAIT_MS = 15_000L
internal const val DEFAULT_IPTV_RECONNECT_CHECK_POLL_MS = 3_000L
internal const val MAX_IPTV_USAGE_AGE_MS = 60_000L

internal data class IptvAccountUsage(val active: Int, val max: Int)

/** Auto reads only answers the panel already gave, and none older than a minute, so it never asks on its own. */
internal class IptvLatestAccountUsage(private val clock: () -> Long = System::currentTimeMillis) {
    private val answers = ConcurrentHashMap<IptvProviderAccount, Pair<IptvAccountUsage, Long>>()

    /** Null forgets the account: a check that found nothing leaves no older answer behind. */
    fun record(account: IptvProviderAccount, usage: IptvAccountUsage?) {
        if (usage == null) answers.remove(account) else answers[account] = usage to clock()
    }

    fun fresh(account: IptvProviderAccount): IptvAccountUsage? =
        answers[account]?.takeIf { clock() - it.second < MAX_IPTV_USAGE_AGE_MS }?.first
}

internal sealed class IptvAdmission {
    data object Proceed : IptvAdmission()
    data class Full(val active: Int, val max: Int) : IptvAdmission()
}

/** active_cons is best effort: only an answer of full delays a stream, for a bounded time, and silence never blocks one. */
internal object IptvAccountAvailability {
    /** Process-wide, like the session table, so a zap reads what the cold start's check found. */
    val latestUsage = IptvLatestAccountUsage()

    suspend fun admit(check: suspend () -> IptvAccountUsage?): IptvAdmission =
        poll(DEFAULT_IPTV_CHECK_POLL_MS, MAX_IPTV_CHECK_WAIT_MS, check)

    /** After a dropped stream the panel can still count this device's own connection for a few seconds, so it waits longer. */
    suspend fun admitReconnect(check: suspend () -> IptvAccountUsage?): IptvAdmission =
        poll(DEFAULT_IPTV_RECONNECT_CHECK_POLL_MS, MAX_IPTV_RECONNECT_CHECK_WAIT_MS, check)

    /** An optional stream asks once and fails closed if the provider cannot confirm room. */
    suspend fun hasVerifiedSpareSlot(check: suspend () -> IptvAccountUsage?): Boolean {
        val answer = withTimeoutOrNull(MAX_IPTV_FIRST_CHECK_MS) { ask(check) } ?: return false
        return answer.active in 0 until answer.max
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
