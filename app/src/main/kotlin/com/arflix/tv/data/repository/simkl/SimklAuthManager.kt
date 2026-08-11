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
        check(clientId.isNotBlank()) { "Simkl Client ID is missing" }
        return simklApi.getPinCode(clientId)
    }

    suspend fun pollPinAuth(userCode: String): Boolean {
        check(clientId.isNotBlank()) { "Simkl Client ID is missing" }
        val response = simklApi.pollPinToken(userCode, clientId)
        if (response.result.equals("OK", ignoreCase = true) && !response.accessToken.isNullOrBlank()) {
            syncProviderStore.setSimklAccessToken(response.accessToken)
            syncProviderStore.setProvider(SyncProvider.SIMKL)
            return true
        }
        return false
    }

    suspend fun disconnect() {
        syncProviderStore.setSimklAccessToken(null)
        if (syncProviderStore.getProvider() == SyncProvider.SIMKL) {
            syncProviderStore.setProvider(SyncProvider.NONE)
        }
    }
}
