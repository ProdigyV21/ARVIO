package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.repository.CustomCollections
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CollectionFolderRowsTest {
    private val today = LocalDate.of(2026, 11, 25)

    private fun addon(type: String, id: String, title: String?) = CollectionSourceConfig(
        kind = CollectionSourceKind.ADDON_CATALOG, mediaType = type, addonId = "aio",
        addonCatalogType = type, addonCatalogId = id, title = title
    )

    private fun folder(vararg sources: CollectionSourceConfig, packId: String? = null) = CatalogConfig(
        id = "collection_custom_telugu", title = "Telugu", sourceType = CatalogSourceType.PREINSTALLED,
        kind = CatalogKind.COLLECTION, collectionGroup = CollectionGroupKind.NETWORK,
        collectionRailKey = "custom_rail", packId = packId, collectionSources = sources.toList()
    )

    private val telugu = folder(
        addon("movie", "latest_te_movies", "Latest Telugu Movies"),
        addon("series", "latest_te_series", "Latest Telugu Series"),
        CollectionSourceConfig(kind = CollectionSourceKind.TMDB_DISCOVER, mediaType = "movie",
            discoverParams = mapOf("with_original_language" to "te"), title = "Popular Telugu Movies")
    )

    @Test fun `a folder of named catalogs shows one row per catalog, in order`() {
        assertTrue(ServiceHome.isCatalogFolder(telugu))
        assertTrue(ServiceHome.supports(telugu))
        val rows = ServiceHome.rows(telugu, today)
        assertEquals(listOf("Latest Telugu Movies", "Latest Telugu Series", "Popular Telugu Movies"), rows.map { it.title })
        rows.forEachIndexed { index, row ->
            assertEquals(ServiceHome.RowKind.CATALOG, row.kind)
            assertEquals(listOf(telugu.collectionSources[index]), row.catalog.collectionSources)
            assertEquals(row.title, row.catalog.title)
        }
        assertEquals(CollectionTab.SERIES, rows[1].viewAll?.tab)
        assertEquals(CollectionTab.MOVIES, rows[2].viewAll?.tab)
    }

    @Test fun `a catalog row's view all opens only that catalog`() {
        val row = ServiceHome.gridRow(telugu, today, ServiceHome.CATALOG_ROW_PREFIX + "1", ServiceHome.Filter.ALL)
        assertEquals(listOf(telugu.collectionSources[1]), row?.catalog?.collectionSources)
        assertEquals("Latest Telugu Series", row?.catalog?.title)
    }

    @Test fun `filters keep the catalogs of that kind, TMDB tv included`() {
        val withTv = folder(
            addon("movie", "m", "Movies"),
            CollectionSourceConfig(kind = CollectionSourceKind.TMDB_DISCOVER, mediaType = "tv", title = "Shows")
        )
        assertEquals(listOf("Shows"), ServiceHome.rows(withTv, today, ServiceHome.Filter.SERIES).map { it.title })
        assertEquals(listOf("Movies"), ServiceHome.rows(withTv, today, ServiceHome.Filter.MOVIES).map { it.title })
    }

    @Test fun `folders imported before catalog names were kept still split`() {
        val old = folder(addon("movie", "a", null), addon("series", "b", null), packId = CustomCollections.PACK_ID_PREFIX + "abc")
        assertTrue(ServiceHome.isCatalogFolder(old))
        assertEquals(2, ServiceHome.rows(old, today).size)
    }

    @Test fun `built-in collections with unnamed sources keep their grid`() {
        val builtIn = folder(addon("movie", "a", null), addon("series", "b", null))
        assertFalse(ServiceHome.isCatalogFolder(builtIn))
        assertFalse(ServiceHome.isCatalogFolder(folder(addon("movie", "a", "Only one"))))
    }
}
