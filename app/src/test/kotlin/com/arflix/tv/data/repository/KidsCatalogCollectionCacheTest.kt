package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StremioCatalogResponse
import com.arflix.tv.data.api.StremioMetaPreview
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/** A collection opened before a parent approved its addon catalog must load again afterwards. */
class KidsCatalogCollectionCacheTest {
    private val catalogKey = KidsTrustedCatalogs.catalogKey("kids.addon", "movie", "kids-shows")
    private val stream = mockk<StreamRepository>(relaxed = true)
    private val catalogs = mockk<CatalogRepository>(relaxed = true)
    private var approvals = emptySet<String>()

    private val collection = CatalogConfig(
        id = "kids-collection", title = "Kids collection", sourceType = CatalogSourceType.PREINSTALLED,
        kind = CatalogKind.COLLECTION, collectionRailKey = "imported",
        collectionSources = listOf(
            CollectionSourceConfig(
                CollectionSourceKind.ADDON_CATALOG,
                addonId = "kids.addon", addonCatalogType = "movie", addonCatalogId = "kids-shows"
            )
        )
    )

    private fun repository(): MediaRepository {
        coEvery { stream.findInstalledAddonIdForCatalog("movie", "kids-shows", "kids.addon") } returns "kids.addon"
        coEvery { stream.getAddonCatalogPage("kids.addon", "movie", "kids-shows", any(), any()) } returns
            StremioCatalogResponse(metas = listOf(StremioMetaPreview(id = "tmdb:101", type = "movie", name = "Example Show")))
        coEvery { catalogs.activeKidsCatalogApprovals() } answers { approvals }
        return MediaRepository(
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), stream, mockk(relaxed = true), catalogRepository = catalogs
        ).also { it.cacheItem(MediaItem(id = 101, title = "Example Show")) }
    }

    @Test fun `an unchanged approval keeps serving the cached collection`() = runTest {
        val repository = repository()
        repeat(2) { assertEquals(listOf(101), repository.loadCollectionCatalogPage(collection, 0, 8).items.map { it.id }) }
        coVerify(exactly = 1) { stream.getAddonCatalogPage("kids.addon", "movie", "kids-shows", any(), any()) }
    }

    @Test fun `approving the catalog loads the collection again and records its titles`() = runTest {
        val repository = repository()
        repository.loadCollectionCatalogPage(collection, 0, 8)
        approvals = setOf(catalogKey)
        repository.loadCollectionCatalogPage(collection, 0, 8)
        coVerify(exactly = 2) { stream.getAddonCatalogPage("kids.addon", "movie", "kids-shows", any(), any()) }
        coVerify(exactly = 2) { catalogs.recordKidsCatalogMembers(catalogKey, listOf(MediaType.MOVIE to 101)) }
    }

    @Test fun `an approval restored from the cloud or revoked also reloads`() = runTest {
        val repository = repository()
        approvals = setOf(catalogKey)
        repository.loadCollectionCatalogPage(collection, 0, 8)
        approvals = emptySet()
        repository.loadCollectionCatalogPage(collection, 0, 8)
        approvals = setOf(catalogKey, KidsTrustedCatalogs.catalogKey("other.addon", "series", "more"))
        repository.loadCollectionCatalogPage(collection, 0, 8)
        coVerify(exactly = 3) { stream.getAddonCatalogPage("kids.addon", "movie", "kids-shows", any(), any()) }
    }
}
