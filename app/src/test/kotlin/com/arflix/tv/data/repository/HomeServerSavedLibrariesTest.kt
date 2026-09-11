package com.arflix.tv.data.repository

import android.app.Application
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
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
class HomeServerSavedLibrariesTest {
    private val client = mockk<OkHttpClient>()
    private val profiles = mockk<ProfileManager> {
        every { activeProfileId } returns MutableStateFlow("library-test")
    }
    private val repository = HomeServerRepository(RuntimeEnvironment.getApplication(), client, profiles)

    private fun server(id: String, kind: HomeServerKind) = HomeServerConnection(
        connectionId = id, displayName = id, serverKind = kind,
        serverUrl = "https://example.invalid", userId = "test", accessToken = "test-only",
        collections = listOf(HomeServerCollection("movies", "Movies", "movies"),
            HomeServerCollection("shows", "Series", "tvshows"))
    )

    @Test fun allSavedLibrariesAreAvailableWithoutAnyNetworkRequest() {
        val connections = listOf(server("Jellyfin A", HomeServerKind.JELLYFIN),
            server("Jellyfin B", HomeServerKind.JELLYFIN), server("Emby A", HomeServerKind.EMBY),
            server("Emby B", HomeServerKind.EMBY), server("Plex", HomeServerKind.PLEX))
        val result = repository.getSavedCatalogCandidates(connections)
        assertEquals(10, result.size)
        assertEquals(10, result.map { it.sourceRef }.distinct().size)
        assertEquals(connections.flatMap { connection -> connection.collections.map { collection ->
            HomeServerRepository.buildCatalogSourceRef(connection, collection)
        } }, result.map { it.sourceRef })
        assertEquals(listOf("Movies", "Series"), result.take(2).map { it.collectionName })
        verify { client wasNot Called }
    }

    @Test fun disabledAndUnusableConnectionsAndLibrariesAreExcluded() {
        val valid = server("Valid", HomeServerKind.JELLYFIN)
        val result = repository.getSavedCatalogCandidates(listOf(
            valid.copy(collections = valid.collections + listOf(
                HomeServerCollection("", "Invalid", "movies"),
                HomeServerCollection("hidden", "Hidden", "movies", enabled = false))),
            valid.copy(connectionId = "disabled", enabled = false),
            valid.copy(connectionId = "no-token", accessToken = ""),
            valid.copy(connectionId = "no-url", serverUrl = ""),
            valid.copy(connectionId = "no-user", userId = "")
        ))
        assertEquals(listOf("Movies", "Series"), result.map { it.collectionName })
        verify { client wasNot Called }
    }

    @Test fun plexDoesNotRequireAJellyfinUserIdAndDuplicateLibrariesAreDeduplicated() {
        val plex = server("Plex", HomeServerKind.PLEX).copy(userId = "")
        val result = repository.getSavedCatalogCandidates(listOf(plex, plex))
        assertEquals(2, result.size)
        assertTrue(result.all { it.serverKind == HomeServerKind.PLEX })
        verify { client wasNot Called }
    }
}
