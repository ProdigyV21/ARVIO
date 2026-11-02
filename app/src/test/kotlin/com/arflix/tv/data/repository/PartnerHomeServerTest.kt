package com.arflix.tv.data.repository

import android.app.Application
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.Lazy
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class PartnerHomeServerTest {
    @org.junit.Before fun requirePartnerBuild() {
        org.junit.Assume.assumeTrue(com.arflix.tv.BuildConfig.PARTNER_MANAGEMENT_ENABLED)
    }

    @Test fun `managed servers reach source lookup but never personal export and disappear on revocation`() = runBlocking {
        val profiles = mockk<ProfileManager> {
            every { activeProfileId } returns MutableStateFlow("partner-server-qa")
            coEvery { getProfileId() } returns "partner-server-qa"
            every { profileStringKeyFor(any(), any()) } answers {
                stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}")
            }
        }
        val managed = listOf(HomeServerKind.PLEX, HomeServerKind.JELLYFIN, HomeServerKind.EMBY).map {
            HomeServerConnection(connectionId = "partner-org-${it.name}", serverKind = it,
                serverUrl = "https://example.invalid", userId = "qa", accessToken = "fixture-token",
                collections = listOf(HomeServerCollection("movies", "Movies", "movies")))
        }
        val state = MutableStateFlow<PartnerIptvDelivery?>(PartnerIptvDelivery(1, Long.MAX_VALUE,
            emptyList(), "org", homeServers = managed))
        val partner = mockk<PartnerDeliveryRepository> {
            every { deliveries } returns state
            every { activeDelivery() } answers { state.value }
        }
        val repository = HomeServerRepository(RuntimeEnvironment.getApplication(), OkHttpClient(), profiles, Lazy { partner })
        assertEquals(managed, repository.currentConnections())
        assertEquals(managed, repository.connections.first())
        assertEquals(3, repository.getSavedCatalogCandidates(repository.currentConnections()).size)
        assertTrue(repository.personalConnections.first().isEmpty())
        assertEquals("", repository.exportCloudConnectionsJsonForProfile("partner-server-qa"))
        state.value = null
        assertTrue(repository.currentConnections().isEmpty())
        assertTrue(repository.connections.first().isEmpty())
    }
}
