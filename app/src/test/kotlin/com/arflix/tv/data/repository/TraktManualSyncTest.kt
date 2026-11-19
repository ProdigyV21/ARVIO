package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.R
import io.mockk.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TraktManualSyncTest {
    private val context = mockk<Context> {
        every { getString(R.string.trakt_reconnect_required) } returns "Reconnect Trakt"
        every { getString(R.string.trakt_not_authenticated) } returns "Connect Trakt"
        every { getString(R.string.trakt_sync_refresh_failed) } returns "History synced, refresh failed"
    }
    private val repository = mockk<TraktRepository>(relaxed = true)
    private val service = mockk<TraktSyncService>()
    private val launcher = mockk<LauncherContinueWatchingRepository>(relaxed = true)
    private val profiles = mockk<ProfileManager> { every { getProfileIdSync() } returns "a" }
    private val connection = MutableStateFlow(TraktConnection("a", true, false))
    private val sync = TraktManualSync(context, repository, service, launcher, profiles)

    @Before fun setup() {
        every { repository.connection } returns connection
        coEvery { service.performFullSync() } returns SyncResult.Success(12, 34)
        coEvery { repository.isAlternativeRemoteActive() } returns false
    }

    @Test fun successWaitsForFreshContinueWatching() = runBlocking {
        assertEquals(SyncResult.Success(12, 34), sync.sync())
        coVerifyOrder {
            service.performFullSync()
            repository.initializeWatchedCache()
            repository.getContinueWatching(forceRefresh = true, requireFreshTrakt = true)
            launcher.refreshForCurrentProfile()
        }
        verify(exactly = 0) { repository.clearContinueWatchingCache() }
    }

    @Test fun refreshFailureIsNotReportedAsFullSuccess() = runBlocking {
        coEvery { repository.getContinueWatching(true, true) } throws java.io.IOException("offline")
        assertEquals(SyncResult.Error("History synced, refresh failed"), sync.sync())
    }

    @Test fun serviceFailureCannotBeHiddenByOtherProviderOrCacheRefresh() = runBlocking {
        coEvery { service.performFullSync() } returns SyncResult.Error("Trakt offline")
        assertEquals(SyncResult.Error("Trakt offline"), sync.sync())
        coVerify(exactly = 0) { repository.getContinueWatching(any(), any()) }
    }

    @Test fun disconnectedTraktDoesNotRunTheSync() = runBlocking {
        connection.value = TraktConnection("a", false, false)
        assertEquals(SyncResult.Error("Connect Trakt"), sync.sync())
        coVerify(exactly = 0) { service.performFullSync() }
    }

    @Test fun invalidCredentialsExplainHowToReconnect() = runBlocking {
        connection.value = TraktConnection("a", false, true)
        assertEquals(SyncResult.Error("Reconnect Trakt", true), sync.sync())
    }

    @Test fun anotherProvidersHomeRowIsNotReplaced() = runBlocking {
        coEvery { repository.isAlternativeRemoteActive() } returns true
        assertTrue(sync.sync() is SyncResult.Success)
        coVerify(exactly = 0) { repository.getContinueWatching(any(), any()) }
    }

    @Test fun aProfileSwitchCancelsTheOldResult() = runBlocking {
        coEvery { service.performFullSync() } coAnswers {
            every { profiles.getProfileIdSync() } returns "b"
            SyncResult.Success(12, 34)
        }
        try {
            sync.sync()
            fail("Expected cancellation")
        } catch (_: CancellationException) { }
        coVerify(exactly = 0) { repository.initializeWatchedCache() }
    }
}
