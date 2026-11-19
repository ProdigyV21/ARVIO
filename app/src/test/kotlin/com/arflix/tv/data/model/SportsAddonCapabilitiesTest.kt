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
        assertTrue(SportsAddonCapabilities.isSportsOnlyLiveTvManifest(manifest))
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
        assertFalse(SportsAddonCapabilities.isSportsOnlyLiveTvManifest(manifest))
    }

    @Test
    fun `hybrid vod addon with sports catalogs is not sports only`() {
        val manifest = AddonManifest(
            id = "org.flickystream.addon",
            name = "Flix Streams",
            version = "1.1.20",
            description = "Movies, series, anime, and live sports",
            types = listOf("movie", "series", "anime", "tv"),
            resources = listOf(
                AddonResource(name = "stream"),
                AddonResource(name = "catalog"),
                AddonResource(name = "meta")
            ),
            catalogs = listOf(
                AddonCatalog(type = "tv", id = "live-tv-sports-top", name = "Live Sports")
            )
        )

        assertTrue(SportsAddonCapabilities.isSportsLiveTvManifest(manifest))
        assertFalse(SportsAddonCapabilities.isSportsOnlyLiveTvManifest(manifest))
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
        assertTrue(SportsAddonCapabilities.isSportsOnlyLiveTvManifest(manifest))
    }

    @Test
    fun `generic live addon is detected from manifest metadata`() {
        val manifest = AddonManifest(
            id = "community.provider",
            name = "Community Provider",
            version = "1.0.0",
            description = "Live football matches",
            types = listOf("sport"),
            resources = listOf(
                AddonResource(name = "catalog", types = listOf("sport")),
                AddonResource(name = "stream", types = listOf("sport"))
            ),
            catalogs = listOf(
                AddonCatalog(type = "sport", id = "football", name = "Football")
            )
        )
        val addon = Addon(
            id = manifest.id,
            name = manifest.name,
            version = manifest.version,
            description = manifest.description,
            isInstalled = true,
            type = AddonType.COMMUNITY,
            manifest = manifest
        )

        assertTrue(
            SportsAddonCapabilities.isLiveStreamOrSportsItem(
                mediaType = MediaType.TV,
                id = SportsAddonCapabilities.sportsSyntheticId("community.provider:event"),
                streamAddonId = addon.id,
                addons = listOf(addon)
            )
        )
    }

    @Test
    fun `hybrid addon keeps vod while explicit sports playback is excluded`() {
        val manifest = AddonManifest(
            id = "sports-documentaries",
            name = "Sports and Movies",
            version = "1.0.0",
            description = "Movies, series, and live sports",
            types = listOf("movie", "series", "tv"),
            resources = listOf(
                AddonResource(name = "catalog"),
                AddonResource(name = "stream")
            ),
            catalogs = listOf(
                AddonCatalog(type = "movie", id = "movies", name = "Movies"),
                AddonCatalog(type = "tv", id = "live-sports", name = "Live Sports")
            )
        )
        val addon = Addon(
            id = manifest.id,
            name = manifest.name,
            version = manifest.version,
            description = manifest.description,
            isInstalled = true,
            type = AddonType.COMMUNITY,
            manifest = manifest
        )

        assertFalse(SportsAddonCapabilities.isLiveStreamAddon(addon))
        assertFalse(
            SportsAddonCapabilities.isLiveStreamOrSportsItem(
                mediaType = MediaType.MOVIE,
                id = 123,
                streamAddonId = addon.id,
                addons = listOf(addon)
            )
        )
        assertTrue(
            SportsAddonCapabilities.isLiveStreamOrSportsItem(
                mediaType = MediaType.TV,
                id = SportsAddonCapabilities.sportsSyntheticId("sports-documentaries:event"),
                streamAddonId = addon.id,
                isLiveStream = true,
                addons = listOf(addon)
            )
        )
    }

    @Test
    fun `xtream iptv vod movies and episodes keep continue watching enabled`() {
        assertFalse(SportsAddonCapabilities.isLiveStreamAddonId("iptv_xtream_vod"))
        assertFalse(
            SportsAddonCapabilities.isLiveStreamOrSportsItem(
                mediaType = MediaType.MOVIE,
                id = 123,
                streamAddonId = "iptv_xtream_vod"
            )
        )
        assertFalse(
            SportsAddonCapabilities.isLiveStreamOrSportsItem(
                mediaType = MediaType.TV,
                id = 456,
                streamAddonId = "iptv_xtream_vod"
            )
        )
    }

    @Test
    fun `internal vod addon id wins over an inherited live manifest but not explicit live playback`() {
        for (id in IptvVodSourceIds.ALL) {
            val manifest = AddonManifest(id = "live-parent", name = "IPTV", version = "1", description = "Live TV",
                types = listOf("tv"), resources = listOf(AddonResource(name = "stream")), catalogs = emptyList())
            val addon = Addon(id = id, name = "IPTV", version = "1", description = "", isInstalled = true,
                type = AddonType.COMMUNITY, manifest = manifest)
            assertFalse(SportsAddonCapabilities.isLiveStreamAddon(addon))
            assertFalse(SportsAddonCapabilities.isLiveStreamOrSportsItem(MediaType.TV, 1416,
                streamAddonId = id, addons = listOf(addon)))
            assertTrue(SportsAddonCapabilities.isLiveStreamOrSportsItem(MediaType.TV, 1416,
                streamAddonId = id, addons = listOf(addon), isLiveStream = true))
            assertTrue(SportsAddonCapabilities.isLiveStreamOrSportsItem(MediaType.TV, -1,
                streamAddonId = id, addons = listOf(addon)))
        }
    }

    @Test
    fun `regular iptv and explicit live playback remain excluded from continue watching`() {
        assertTrue(SportsAddonCapabilities.isLiveStreamAddonId("my_iptv_provider"))
        assertTrue(
            SportsAddonCapabilities.isLiveStreamOrSportsItem(
                mediaType = MediaType.TV,
                id = 123,
                streamAddonId = "my_iptv_provider"
            )
        )
        assertTrue(
            SportsAddonCapabilities.isLiveStreamOrSportsItem(
                mediaType = MediaType.TV,
                id = 123,
                streamAddonId = "iptv_xtream_vod",
                isLiveStream = true
            )
        )
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
