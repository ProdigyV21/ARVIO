package com.arflix.tv.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Monitors network connectivity state.
 *
 * Provides:
 * - [isConnected]: Current connectivity state (synchronous check)
 * - [isConnectedFlow]: Flow that emits connectivity changes
 * - [connectionState]: StateFlow for observing current state
 *
 * Usage:
 * ```
 * // Check before making API call
 * if (!networkMonitor.isConnected) {
 *     return Result.Error(AppException.Network.NO_CONNECTION)
 * }
 *
 * // Observe in UI
 * networkMonitor.connectionState.collect { connected ->
 *     showOfflineBanner(!connected)
 * }
 * ```
 */
@Singleton
class NetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _connectionState = MutableStateFlow(checkNetworkConnectivity())

    /**
     * StateFlow of current connectivity state.
     * Suitable for collecting in Compose or observing in lifecycle-aware components.
     */
    val connectionState: StateFlow<Boolean> = _connectionState.asStateFlow()

    /**
     * Returns true if the device currently has network connectivity.
     * This is a synchronous check suitable for immediate decisions.
     */
    val isConnected: Boolean
        get() = checkNetworkConnectivity()

    /**
     * Flow that emits when network connectivity changes.
     * Emits true when connected, false when disconnected.
     * Only emits distinct values (no duplicates).
     */
    val isConnectedFlow: Flow<Boolean> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _connectionState.value = true
                trySend(true)
            }

            override fun onLost(network: Network) {
                // Double-check - another network might still be available
                val stillConnected = checkNetworkConnectivity()
                _connectionState.value = stillConnected
                trySend(stillConnected)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                val hasInternet = networkCapabilities.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET
                ) && networkCapabilities.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_VALIDATED
                )
                _connectionState.value = hasInternet
                trySend(hasInternet)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)

        // Emit initial state
        trySend(checkNetworkConnectivity())

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()

    /**
     * Checks current network connectivity.
     * Returns true if device has validated internet connectivity.
     */
    private fun checkNetworkConnectivity(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    /**
     * Returns the type of network currently connected (for logging/analytics).
     */
    fun getNetworkType(): NetworkType {
        val network = connectivityManager.activeNetwork ?: return NetworkType.NONE
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return NetworkType.NONE

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NetworkType.ETHERNET
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkType.CELLULAR
            else -> NetworkType.OTHER
        }
    }
}

/**
 * Types of network connections.
 */
enum class NetworkType {
    WIFI,
    ETHERNET,
    CELLULAR,
    OTHER,
    NONE
}
