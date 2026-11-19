package com.arflix.tv.ui.screens.tv.live

import android.os.SystemClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.arflix.tv.network.NetworkMonitor

/** An outage means no network at all, as a reconnect's wait reads it, so a network Android never validated is not one. */
internal class IptvNetworkWatch(val hasActiveNetwork: () -> Boolean) {
    private var lastOfflineAtMs: Long? = null

    fun onConnectivity(connected: Boolean, nowMs: Long) {
        // The flow also reports false for a connected network that failed validation.
        if (!connected && !hasActiveNetwork()) lastOfflineAtMs = nowMs
    }

    fun classifyDrop(error: Throwable, nowMs: Long): LiveDrop = classifyLiveDrop(
        error,
        hasActiveNetwork = hasActiveNetwork(),
        offlineWithin60s = lastOfflineAtMs?.let { nowMs - it < DEFAULT_LIVE_RETRY_REFILL_MS } == true,
    )
}

/** One watch per screen, so a recovery can tell an outage it missed from an eviction. */
@Composable
internal fun rememberIptvNetworkWatch(): IptvNetworkWatch {
    val context = LocalContext.current
    val monitor = remember { NetworkMonitor(context.applicationContext) }
    val watch = remember { IptvNetworkWatch { monitor.hasActiveNetwork } }
    LaunchedEffect(watch) {
        try {
            monitor.isConnectedFlow.collect { connected -> watch.onConnectivity(connected, SystemClock.elapsedRealtime()) }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
        }
    }
    return watch
}
