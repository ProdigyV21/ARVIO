package com.arflix.tv.data.repository.simkl

import com.arflix.tv.data.api.SimklApi
import com.arflix.tv.data.api.SimklScrobbleResponse
import com.arflix.tv.data.model.MediaType
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class SimklScrobbleQueueTest {
    @Test fun queuedStopUsesRefreshedTokenForSameConnection() = runTest {
        val api = mockk<SimklApi>(relaxed = true)
        val auth = mockk<SimklAuthManager>()
        var token = "old-token"
        coEvery { auth.getAccessToken() } answers { token }
        every { auth.cacheIdentity(any()) } returns "same-connection"
        every { auth.effectiveClientId } returns "v2-client"
        coEvery { api.scrobbleStart(any(), any(), any(), any()) } returns Response.success(SimklScrobbleResponse(action = "start"))
        coEvery { api.scrobbleStop(any(), any(), any(), any()) } returns Response.success(SimklScrobbleResponse(action = "pause"))
        val scrobbler = SimklScrobbler(api, auth).apply {
            queueScope = backgroundScope
            elapsedRealtimeMs = { testScheduler.currentTime }
        }
        scrobbler.scrobbleStart(MediaType.TV, 200, 0f, 1, 3)
        scrobbler.scrobbleStop(MediaType.TV, 200, 40f, 1, 3)
        token = "refreshed-token"
        runCurrent(); advanceTimeBy(20_501); runCurrent()
        coVerify(exactly = 1) { api.scrobbleStop("Bearer refreshed-token", "v2-client",
            match { it.progress == 40f && it.episode?.number == 3 }, any()) }
    }

    @Test fun queuedPauseCannotCrossAnAccountChange() = runTest {
        val api = mockk<SimklApi>(relaxed = true)
        val auth = mockk<SimklAuthManager>()
        var identity = "first-connection"
        coEvery { auth.getAccessToken() } returns "token"
        every { auth.cacheIdentity(any()) } answers { identity }
        every { auth.effectiveClientId } returns "v2-client"
        coEvery { api.scrobbleStart(any(), any(), any(), any()) } returns Response.success(SimklScrobbleResponse(action = "start"))
        val scrobbler = SimklScrobbler(api, auth).apply {
            queueScope = backgroundScope
            elapsedRealtimeMs = { testScheduler.currentTime }
        }
        scrobbler.scrobbleStart(MediaType.TV, 200, 0f, 1, 3)
        scrobbler.scrobblePause(MediaType.TV, 200, 40f, 1, 3)
        identity = "other-connection"
        runCurrent(); advanceTimeBy(20_501); runCurrent()
        coVerify(exactly = 0) { api.scrobblePause(any(), any(), any()) }
    }
}
