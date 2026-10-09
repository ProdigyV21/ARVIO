package com.arflix.tv.data.repository.simkl

import com.arflix.tv.data.api.SimklApi
import com.arflix.tv.data.api.SimklPinResponse
import com.arflix.tv.data.repository.sync.SyncProvider
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.arflix.tv.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class SimklPinExpiredException(message: String) : Exception(message)

sealed class SimklPinAuthState {
    object Idle : SimklPinAuthState()
    data class CodeRequested(val userCode: String, val verificationUrl: String, val expiresIn: Int) : SimklPinAuthState()
    object Success : SimklPinAuthState()
    data class Error(val message: String) : SimklPinAuthState()
}

@Singleton
class SimklAuthManager @Inject constructor(
    private val simklApi: SimklApi,
    private val syncProviderStore: SyncProviderStore,
    private val v2: SimklListsRepository? = null
) {
    private val clientId: String get() = Constants.SIMKL_CLIENT_ID
    val effectiveClientId: String get() = if (isV2Connected()) Constants.SIMKL_V2_CLIENT_ID else clientId
    fun isV2Connected(): Boolean = v2?.isConnected() == true
    fun cacheIdentity(token: String): String = v2?.connectionId() ?: token
    private var session: SimklListsDeviceCode? = null
    private var sessionProfile: String? = null
    private var nextPollAt = 0L
    private var pollInterval = 5
    fun cancelPinAuth() { session = null; sessionProfile = null }


    suspend fun getAccessToken(): String? {
        return syncProviderStore.getSimklAccessToken()
    }

    suspend fun isConnected(): Boolean {
        val token = getAccessToken()
        return !token.isNullOrBlank()
    }

    suspend fun startPinAuth(): SimklPinResponse {
        if (v2 != null && Constants.SIMKL_V2_CLIENT_ID.isNotBlank()) {
            cancelPinAuth()
            val profile = v2.profileId()
            val code = v2.startDevice()
            if (profile != v2.profileId()) throw SimklPinExpiredException("SIMKL profile changed")
            session = code
            sessionProfile = profile
            pollInterval = code.interval
            nextPollAt = System.currentTimeMillis() + pollInterval * 1000L
            return SimklPinResponse(code.userCode, code.url, code.expiresIn, code.interval)
        }
        val effectiveClientId = clientId.ifBlank { "simkl_proxy" }
        return simklApi.getPinCode(effectiveClientId)
    }

    suspend fun pollPinAuth(userCode: String): Boolean {
        session?.let { code ->
            if (code.userCode != userCode || sessionProfile != v2?.profileId()) throw SimklPinExpiredException("SIMKL sign-in was cancelled")
            if (System.currentTimeMillis() < nextPollAt) return false
            nextPollAt = System.currentTimeMillis() + pollInterval * 1000L
            try {
                v2!!.poll(code, sessionProfile!!)
                cancelPinAuth()
                syncProviderStore.onProviderConnected(SyncProvider.SIMKL)
                return true
            } catch (e: SimklListsException) {
                when (e.reason) {
                    "authorization_pending" -> return false
                    "slow_down" -> { pollInterval += 5; nextPollAt = System.currentTimeMillis() + pollInterval * 1000L; return false }
                    else -> { cancelPinAuth(); throw SimklPinExpiredException(e.message.orEmpty()) }
                }
            }
        }
        if (Constants.SIMKL_V2_CLIENT_ID.isNotBlank() && v2 != null) throw SimklPinExpiredException("SIMKL sign-in was cancelled")
        val effectiveClientId = clientId.ifBlank { "simkl_proxy" }
        val response = simklApi.pollPinToken(userCode, effectiveClientId)
        if (response.result.equals("OK", ignoreCase = true) && !response.accessToken.isNullOrBlank()) {
            syncProviderStore.setSimklAccessToken(response.accessToken)
            syncProviderStore.onProviderConnected(SyncProvider.SIMKL)
            return true
        }
        if (!response.deviceCode.isNullOrBlank()) {
            throw SimklPinExpiredException("Simkl PIN expired or invalid")
        }
        return false
    }

    suspend fun fetchUsername(): String? {
        val token = getAccessToken() ?: return null
        val authHeader = "Bearer $token"
        val effectiveClientId = this.effectiveClientId.ifBlank { "simkl_proxy" }
        return try {
            val res = simklApi.getUserSettings(authHeader, effectiveClientId)
            res.user?.name ?: res.user?.username
        } catch (e: Exception) {
            null
        }
    }

    suspend fun disconnect() {
        cancelPinAuth()
        v2?.disconnect()
        syncProviderStore.setSimklAccessToken(null)
        syncProviderStore.onProviderDisconnected(SyncProvider.SIMKL)
    }
}

