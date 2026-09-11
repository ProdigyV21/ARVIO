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
    private val syncProviderStore: SyncProviderStore
) {
    private val clientId: String get() = Constants.SIMKL_CLIENT_ID

    suspend fun getAccessToken(): String? {
        return syncProviderStore.getSimklAccessToken()
    }

    suspend fun isConnected(): Boolean {
        val token = getAccessToken()
        return !token.isNullOrBlank()
    }

    suspend fun startPinAuth(): SimklPinResponse {
        val effectiveClientId = clientId.ifBlank { "simkl_proxy" }
        return simklApi.getPinCode(effectiveClientId)
    }

    suspend fun pollPinAuth(userCode: String): Boolean {
        val effectiveClientId = clientId.ifBlank { "simkl_proxy" }
        val response = simklApi.pollPinToken(userCode, effectiveClientId)
        if (response.result.equals("OK", ignoreCase = true) && !response.accessToken.isNullOrBlank()) {
            syncProviderStore.setSimklAccessToken(response.accessToken)
            syncProviderStore.onProviderConnected(SyncProvider.SIMKL)
            return true
        }
        if (response.result.equals("KO", ignoreCase = true) || !response.deviceCode.isNullOrBlank()) {
            throw SimklPinExpiredException("Simkl PIN expired or invalid")
        }
        return false
    }

    suspend fun fetchUsername(): String? {
        val token = getAccessToken() ?: return null
        val authHeader = "Bearer $token"
        val effectiveClientId = clientId.ifBlank { "simkl_proxy" }
        return try {
            val res = simklApi.getUserSettings(authHeader, effectiveClientId)
            res.user?.name ?: res.user?.username
        } catch (e: Exception) {
            null
        }
    }

    suspend fun disconnect() {
        syncProviderStore.setSimklAccessToken(null)
        syncProviderStore.onProviderDisconnected(SyncProvider.SIMKL)
    }
}

