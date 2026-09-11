package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

class IptvCatchupAvailabilityTest {
    private val client = mockk<OkHttpClient>(relaxed = true)
    private val repository = IptvRepository(mockk<Context>(relaxed = true), client,
        mockk<ProfileManager>(relaxed = true), mockk<CloudSyncInvalidationBus>(relaxed = true))
    private val channel = IptvChannel("one:1", "News", "https://example.invalid/channel.m3u8", "News", catchupDays = 3)
    private val programme = IptvProgram("Aired", startUtcMillis = 1_000, endUtcMillis = 60_000)

    @Test fun unavailableArchiveNeverProbesProvider() = runTest {
        val unavailable = programme.copy(catchupAvailable = false)
        assertTrue(repository.getCatchupUrlCandidates(channel, unavailable).isEmpty())
        assertTrue(runCatching { repository.resolvePlayableCatchupUrl(channel, unavailable) }.exceptionOrNull() is IOException)
        verify(exactly = 0) { client.newCall(any()) }
    }

    @Test fun missingArchiveRouteDoesNotSilentlyPlayLive() = runTest {
        assertTrue(repository.getCatchupUrlCandidates(channel, programme).isEmpty())
        assertTrue(runCatching { repository.resolvePlayableCatchupUrl(channel, programme) }.exceptionOrNull() is IOException)
        verify(exactly = 0) { client.newCall(any()) }
    }
}
