package com.arflix.tv.data.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SportsAddonCapabilitiesTest {
    @Test
    fun `sports live manifest is detected`() {
        val manifest = AddonManifest(
            id = "sports-live",
            name = "Sports Live TV",
            version = "1.0.0",
            description = "Live sports events",
            types = listOf("sports"),
            resources = listOf(
                AddonResource(name = "catalog", types = listOf("sports")),
                AddonResource(name = "stream", types = listOf("sports"))
            ),
            catalogs = listOf(
                AddonCatalog(type = "sports", id = "popular-live", name = "Popular Live")
            )
        )

        assertTrue(SportsAddonCapabilities.isSportsLiveTvManifest(manifest))
    }

    @Test
    fun `regular vod manifest is not detected as sports live tv`() {
        val manifest = AddonManifest(
            id = "movie-source",
            name = "Movie Source",
            version = "1.0.0",
            description = "Movie and series sources",
            types = listOf("movie", "series"),
            resources = listOf(
                AddonResource(name = "catalog", types = listOf("movie", "series")),
                AddonResource(name = "stream", types = listOf("movie", "series"))
            ),
            catalogs = listOf(
                AddonCatalog(type = "movie", id = "popular", name = "Popular Movies")
            )
        )

        assertFalse(SportsAddonCapabilities.isSportsLiveTvManifest(manifest))
    }

    @Test
    fun `highfly style sport manifest is detected`() {
        val manifest = AddonManifest(
            id = "community.sports.fly",
            name = "Sports Streams",
            version = "1.2.0",
            description = "Live and upcoming sports matches",
            types = listOf("sport"),
            resources = listOf(
                AddonResource(name = "catalog", types = listOf("sport")),
                AddonResource(name = "meta", types = listOf("sport"), idPrefixes = listOf("streamed", "sf", "recap", "leaf")),
                AddonResource(name = "stream", types = listOf("sport"), idPrefixes = listOf("streamed", "sf", "recap", "leaf"))
            ),
            catalogs = listOf(
                AddonCatalog(type = "sport", id = "sports_live", name = "Live Now"),
                AddonCatalog(type = "sport", id = "sports_football", name = "Football")
            ),
            idPrefixes = listOf("streamed", "sf", "recap", "leaf")
        )

        assertTrue(SportsAddonCapabilities.isSportsLiveTvManifest(manifest))
    }

    @Test
    fun `sports statuses are recognized as home sports items`() {
        assertTrue(SportsAddonCapabilities.isSportsHomeStatus("sports:football"))
        assertTrue(SportsAddonCapabilities.isSportsHomeStatus("sports_event:addon|sports|event"))
        assertTrue(SportsAddonCapabilities.isSportsHomeStatus("sports_locked:locked"))
        assertFalse(SportsAddonCapabilities.isSportsHomeStatus("collection:netflix"))
    }

    @Test
    fun `sports collection catalog ids are recognized`() {
        val catalogId = SportsAddonCapabilities.sportsCollectionCatalogId("basketball")

        assertTrue(SportsAddonCapabilities.isSportsCollectionCatalogId(catalogId))
        assertTrue(SportsAddonCapabilities.sportIdFromCollectionCatalogId(catalogId) == "basketball")
        assertFalse(SportsAddonCapabilities.isSportsCollectionCatalogId("collection_service_netflix"))
        assertNull(SportsAddonCapabilities.sportIdFromCollectionCatalogId("collection_service_netflix"))
    }
}
