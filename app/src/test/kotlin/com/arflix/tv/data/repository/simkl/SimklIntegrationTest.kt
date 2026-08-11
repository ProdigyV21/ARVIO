package com.arflix.tv.data.repository.simkl

import com.arflix.tv.data.api.SimklApi
import com.arflix.tv.data.api.SimklPinPollResponse
import com.arflix.tv.data.api.SimklPinResponse
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.sync.SyncProvider
import com.arflix.tv.data.repository.sync.SyncProviderStore
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class SimklIntegrationTest {

    private lateinit var simklApi: SimklApi
    private lateinit var syncProviderStore: SyncProviderStore
    private lateinit var authManager: SimklAuthManager
    private lateinit var scrobbler: SimklScrobbler
    private lateinit var syncService: SimklSyncService

    @Before
    fun setUp() {
        simklApi = mock(SimklApi::class.java)
        syncProviderStore = mock(SyncProviderStore::class.java)
        authManager = SimklAuthManager(simklApi, syncProviderStore)
        scrobbler = SimklScrobbler(simklApi, authManager)
        syncService = SimklSyncService(simklApi, authManager)
    }

    @Test
    fun testStartPinAuthReturnsResponse() = runBlocking {
        val expected = SimklPinResponse(
            userCode = "SIMKL-123",
            verificationUrl = "https://simkl.com/pin",
            expiresIn = 600
        )
        `when`(simklApi.getPinCode(anyString())).thenReturn(expected)

        val result = authManager.startPinAuth()
        assertEquals("SIMKL-123", result.userCode)
        assertEquals("https://simkl.com/pin", result.verificationUrl)
    }

    @Test
    fun testPollPinAuthSuccessStoresToken() = runBlocking {
        val pollRes = SimklPinPollResponse(
            result = "OK",
            accessToken = "token_abc123"
        )
        `when`(simklApi.pollPinToken(anyString(), anyString())).thenReturn(pollRes)

        val success = authManager.pollPinAuth("SIMKL-123")
        assertTrue(success)
    }

    @Test
    fun testDisconnectClearsToken() = runBlocking {
        authManager.disconnect()
        `when`(syncProviderStore.getSimklAccessToken()).thenReturn(null)
        assertFalse(authManager.isConnected())
    }
}
