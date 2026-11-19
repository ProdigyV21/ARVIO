package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.Player
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.IptvStreamPolicy
import com.arflix.tv.network.IptvAccountAvailability
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvAdmission
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamSession
import com.arflix.tv.network.IptvStreamSessionTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

internal const val MAX_LIVE_NETWORK_WAIT_MS = 30_000L
internal const val DEFAULT_LIVE_NETWORK_POLL_MS = 1_000L

/** Spends no attempt: an outage up to the cap ends in one reconnect, and a longer one proceeds at the cap. */
internal suspend fun awaitLiveNetwork(hasActiveNetwork: () -> Boolean) {
    withTimeoutOrNull(MAX_LIVE_NETWORK_WAIT_MS) {
        while (!hasActiveNetwork()) delay(DEFAULT_LIVE_NETWORK_POLL_MS)
    }
}

internal sealed class LiveTvGateResult {
    data object Ready : LiveTvGateResult()
    data class Busy(val active: Int, val max: Int) : LiveTvGateResult()
}

/**
 * Keeps Live TV's order on a limited account: stop, close the old stream, make room, ask the
 * panel only when required, and only then open a session. A cancelled zap opens nothing.
 */
internal class LiveTvStreamGate(
    private val player: Player,
    private val sessions: IptvStreamSessionTable,
    private val policyFor: suspend (IptvChannel) -> IptvStreamPolicy?,
    private val checkAccount: suspend (IptvProviderAccount) -> IptvAccountUsage?,
    private val onAllowanceUnknown: (IptvChannel) -> Unit = {},
    private val hasActiveNetwork: () -> Boolean = { true },
) {
    private var policy: IptvStreamPolicy? = null
    private var session: IptvStreamSession? = null
    private var pendingJob: Job? = null
    private var skipCheckOnce = false
    private var busyAccount: IptvProviderAccount? = null

    /** A null policy (No limit, sports add-ons, unknown owner) runs today's code path. */
    val isLimited: Boolean get() = policy != null

    /** Only a stream whose reconnect can ask the panel has a reason to surface its drops. */
    val panelCheckAllowed: Boolean get() = policy?.panelCheckAllowed == true

    /** After a close, a retained source's factory is bound to a dead session. */
    val retainedSourceUsable: Boolean get() = policy == null || session != null

    /** Play anyway: the next tune does not ask the panel. */
    fun skipNextCheck() {
        skipCheckOnce = true
    }

    /** Runs when a channel is tuned, before anything resolves or probes the new stream. */
    suspend fun tune(channel: IptvChannel?): LiveTvGateResult {
        cancelPending()
        player.stop()
        player.clearMediaItems()
        val previous = session
        session = null
        previous?.close()
        policy = null
        val read = channel?.let { readPolicy(it) }
        policy = read
        if (read == null) return LiveTvGateResult.Ready
        sessions.makeRoom(read.account, read.effectiveLimit, previous)
        if (!read.panelCheckAllowed) channel?.let(onAllowanceUnknown)
        val skipCheck = skipCheckOnce
        skipCheckOnce = false
        // The close before a Busy keeps the account inside the cold-start window, and opening then would evict someone.
        val answeredBusy = busyAccount == read.account
        if (read.panelCheckAllowed && !skipCheck && (answeredBusy || sessions.isColdStart(read.account))) return admit(read.account)
        if (answeredBusy) busyAccount = null
        return LiveTvGateResult.Ready
    }

    /** Called by the tune's prepare, and never waits. Null means the request stays untagged. */
    fun openSession(): IptvStreamSession? {
        val current = policy ?: return null
        session?.close()
        return sessions.open(current.account, current.effectiveLimit).also { session = it }
    }

    /** The retry job's half, after its delay: release the old stream before any reconnect. */
    suspend fun reconnect(drop: LiveDrop): LiveTvGateResult {
        val current = policy ?: return LiveTvGateResult.Ready
        release(current)
        return when (drop) {
            LiveDrop.NetworkLost -> {
                awaitLiveNetwork(hasActiveNetwork)
                LiveTvGateResult.Ready
            }
            LiveDrop.Ambiguous -> if (current.panelCheckAllowed) admit(current.account, reconnect = true) else LiveTvGateResult.Ready
            LiveDrop.Other -> LiveTvGateResult.Ready
        }
    }

    /** For callers that cannot suspend. A null policy opens synchronously, as today. */
    fun reopen(scope: CoroutineScope, stillCurrent: () -> Boolean, open: suspend () -> Unit, openNow: () -> Unit) {
        val current = policy
        if (current == null) {
            openNow()
            return
        }
        cancelPending()
        player.stop()
        val previous = session
        session = null
        previous?.close()
        pendingJob = scope.launch {
            sessions.makeRoom(current.account, current.effectiveLimit, previous)
            if (stillCurrent()) open()
        }
    }

    /** Lets a tune or suspend stop a retry that is still on its way to reopening. */
    fun track(job: Job) {
        if (policy == null) return
        pendingJob?.cancel()
        pendingJob = job
    }

    fun cancelPending() {
        pendingJob?.cancel()
        pendingJob = null
    }

    /** Backgrounding or leaving: the allowance goes back now, not when a body happens to close. */
    fun close() {
        cancelPending()
        session?.close()
        session = null
    }

    private suspend fun release(current: IptvStreamPolicy) {
        player.stop()
        val previous = session
        session = null
        sessions.makeRoom(current.account, current.effectiveLimit, previous)
    }

    private suspend fun admit(account: IptvProviderAccount, reconnect: Boolean = false): LiveTvGateResult {
        val check: suspend () -> IptvAccountUsage? = { checkAccount(account) }
        val answer = if (reconnect) IptvAccountAvailability.admitReconnect(check) else IptvAccountAvailability.admit(check)
        return when (answer) {
            IptvAdmission.Proceed -> {
                if (busyAccount == account) busyAccount = null
                LiveTvGateResult.Ready
            }
            is IptvAdmission.Full -> {
                busyAccount = account
                LiveTvGateResult.Busy(answer.active, answer.max)
            }
        }
    }

    private suspend fun readPolicy(channel: IptvChannel): IptvStreamPolicy? = try {
        policyFor(channel)
    } catch (e: Exception) {
        if (e is kotlinx.coroutines.CancellationException) throw e
        null
    }
}
