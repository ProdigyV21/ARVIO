package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StremioCatalogResponse
import com.arflix.tv.data.api.StremioMetaPreview
import com.arflix.tv.data.model.*
import com.arflix.tv.data.repository.simkl.SimklSyncService
import io.mockk.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ClientDelegatedCatalogTest {
    private val streams = mockk<StreamRepository>()
    private val trakt = mockk<TraktRepository>()
    private val simkl = mockk<SimklSyncService>()
    private val home = mockk<HomeServerRepository>()
    private val media = spyk(MediaRepository(mockk(relaxed = true), mockk(), trakt, mockk(), mockk(), streams, home,
        simklSyncService = simkl))
    private val catalog = CatalogConfig("personal", "Personal", CatalogSourceType.ADDON,
        addonId = "fixture", addonCatalogId = "personal", addonCatalogType = "movie")

    init {
        every { streams.installedAddons } returns MutableStateFlow(emptyList())
        coEvery { media.getMovieDetails(any()) } answers {
            MediaItem(firstArg(), "Movie ${firstArg<Int>()}", year = if (firstArg<Int>() % 2 == 0) "2024" else "1999", rating = "8.0", genreIds = listOf(28))
        }
        coEvery { media.getTvDetails(any()) } answers { MediaItem(firstArg(), "Show ${firstArg<Int>()}", mediaType = MediaType.TV) }
    }

    @Test fun `manifest delegates and filters survive into the real catalog loader`() = runBlocking {
        val declaration = AddonCatalog("movie", "personal", clientDelegate = "trakt://watchlist?type=movie",
            clientFilter = "{\"minYear\":2020}")
        every { streams.installedAddons } returns MutableStateFlow(listOf(Addon(
            "fixture", "Fixture", "1", "Fixture", true, type = AddonType.COMMUNITY,
            manifest = AddonManifest("fixture", "Fixture", "1", catalogs = listOf(declaration)))))
        coEvery { trakt.getWatchlist() } returns (1..6).map { MediaItem(it, "Seed") }
        val page = media.loadCustomCatalogPage(catalog, 0, 2)
        assertEquals(listOf(2, 4), page.items.map { it.id })
        assertEquals(4, page.nextOffset)
        assertTrue(page.hasMore)
        assertEquals(listOf(6), media.loadCustomCatalogPage(catalog, page.nextOffset!!, 2).items.map { it.id })
        coVerify(exactly = 0) { streams.getAddonCatalogPage(any(), any(), any(), any()) }
    }

    @Test fun `simkl and hybrid use simkl library status not the selected trakt account`() = runBlocking {
        coEvery { simkl.getLibraryItems("plantowatch", any()) } returns listOf(MediaItem(2, "Simkl"))
        coEvery { simkl.getLibraryItems("completed", any()) } returns listOf(MediaItem(4, "Completed"))
        assertEquals(listOf(2), media.loadCustomCatalogPage(catalog.copy(clientDelegate = "simkl:plan"), 0, 5).items.map { it.id })
        assertEquals(listOf(4), media.loadCustomCatalog(catalog.copy(clientDelegate = "hybrid:completed?tracker=simkl"))!!.items.map { it.id })
        coVerify(exactly = 0) { trakt.getWatchlist() }
    }

    @Test fun `watched delegates fetch current history and preserve movie tv identity`() = runBlocking {
        coEvery { trakt.getWatchedMovies() } returns setOf(2)
        coEvery { trakt.getWatchedEpisodes() } returns setOf("show_tmdb:2:1:1", "show_tmdb:2:1:2")
        val page = media.loadCustomCatalogPage(catalog.copy(clientDelegate = "trakt:watched?type=all"), 0, 10)
        assertEquals(listOf(MediaType.MOVIE, MediaType.TV), page.items.map { it.mediaType })
        assertEquals(listOf(2, 2), page.items.map { it.id })
    }

    @Test fun `home-server delegation uses existing metadata matching and filtered paging`() = runBlocking {
        val source = "home_server_catalog|fixture-server|films|movie"
        coEvery { home.loadCatalogItems(source, any(), any(), any(), any(), any(), false) } answers {
            val offset = arg<Int>(1)
            val size = arg<Int>(2)
            val all = listOf(1999, 2024).mapIndexed { index, year ->
                HomeServerCatalogItem("native-$index", "Film $index", MediaType.MOVIE, year, mapOf("tmdb" to "${index + 1}"), sourceRef = source)
            }
            HomeServerCatalogPage(all.drop(offset).take(size), offset + size < all.size, minOf(offset + size, all.size))
        }
        val delegated = catalog.copy(clientDelegate = "homeserver:library?source=${java.net.URLEncoder.encode(source, "UTF-8")}", clientFilter = "{\"minYear\":2020}")
        val page = media.loadCustomCatalogPage(delegated, 0, 1)
        assertEquals(2, page.items.single().id)
        assertTrue(page.items.single().hasOpenableId)
        assertEquals(2, page.nextOffset)
        assertFalse(page.hasMore)
        coVerify(exactly = 1) { media.getMovieDetails(2) }
    }

    @Test fun `delegate failure and cancellation remain retryable instead of empty success`() = runBlocking {
        coEvery { trakt.getWatchlist() } throws CancellationException("cancelled")
        try {
            media.loadCustomCatalogPage(catalog.copy(clientDelegate = "trakt:watchlist"), 0, 10)
            fail("Expected cancellation")
        } catch (_: CancellationException) { }
    }

    @Test fun `ordinary addon catalogs retain their normal HTTP path`() = runBlocking {
        coEvery { streams.getAddonCatalogPage("fixture", "movie", "personal", 0) } returns
            StremioCatalogResponse(metas = listOf(StremioMetaPreview("tmdb:2", name = "Film")))
        assertEquals(listOf(2), media.loadCustomCatalogPage(catalog, 0, 1).items.map { it.id })
        coVerify(exactly = 0) { trakt.getWatchlist() }
    }
}
