package com.arflix.tv.data.repository

import org.junit.Assert.assertEquals
import org.junit.Test

class HomeServerHomeRowSortTest {
    private fun server(kind: HomeServerKind) = HomeServerConnection(
        connectionId = kind.name, displayName = kind.name, serverKind = kind,
        serverUrl = "https://example.invalid", userId = "test", accessToken = "test-only"
    )

    @Test fun libraryRowsShowNewestAdditionsFirstOnEveryServerKind() {
        listOf(HomeServerKind.EMBY, HomeServerKind.JELLYFIN, HomeServerKind.PLEX).forEach { kind ->
            val ref = HomeServerRepository.buildCatalogSourceRef(server(kind), HomeServerCollection("12", "Movies", "movies"))
            assertEquals(kind.name, HomeServerLibrarySort.RECENTLY_ADDED, homeServerHomeRowSort(ref))
        }
    }

    @Test fun serverCollectionRowsKeepTitleOrder() {
        val ref = HomeServerRepository.buildCatalogSourceRef(
            server(HomeServerKind.EMBY), HomeServerCollection("collection:99", "Marvel", "boxsets")
        )
        assertEquals(HomeServerLibrarySort.TITLE, homeServerHomeRowSort(ref))
    }

    @Test fun recentlyAddedMapsToEachServersDateAddedSort() {
        assertEquals("DateCreated", HomeServerLibrarySort.RECENTLY_ADDED.jellyfinSort)
        assertEquals("addedAt:desc", HomeServerLibrarySort.RECENTLY_ADDED.plexSort)
        assertEquals(false, HomeServerLibrarySort.RECENTLY_ADDED.ascending)
    }
}
