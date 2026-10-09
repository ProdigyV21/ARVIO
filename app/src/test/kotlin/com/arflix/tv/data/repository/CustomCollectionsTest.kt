package com.arflix.tv.data.repository

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionSourceKind
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CustomCollectionsTest {

    // Nuvio collections export shape: array of collections -> folders -> sources.
    private val nuvioExport = """
        [
          {
            "id": "studios", "title": "Studios", "viewMode": "TABBED_GRID",
            "folders": [
              { "id": "pixar", "title": "Pixar", "coverImageUrl": "https://x/pixar.jpg", "tileShape": "POSTER", "hideTitle": true,
                "sources": [ { "provider": "tmdb", "tmdbSourceType": "COMPANY", "tmdbId": 3, "mediaType": "MOVIE" } ] },
              { "id": "netflix", "title": "Netflix",
                "sources": [ { "provider": "addon", "addonId": "aio", "type": "series", "catalogId": "streaming.nfx" } ] },
              { "id": "il", "title": "Israeli",
                "sources": [ { "provider": "tmdb", "tmdbSourceType": "DISCOVER", "mediaType": "TV",
                               "filters": { "withOriginCountry": "IL", "releaseDateGte": "2020-01-01" } } ] }
            ]
          },
          {
            "id": "lists", "title": "Lists",
            "folders": [
              { "id": "hp", "title": "Harry Potter",
                "sources": [ { "provider": "tmdb", "tmdbSourceType": "COLLECTION", "tmdbId": 1241, "mediaType": "MOVIE" } ] },
              { "id": "trakt", "title": "Trakt list",
                "sources": [ { "provider": "trakt", "traktListId": 12345 } ] },
              { "id": "empty", "title": "Unsupported only",
                "sources": [ { "provider": "unknown" } ] }
            ]
          }
        ]
    """.trimIndent()

    @Test
    fun `installs a Nuvio export as one rail per collection`() {
        assertTrue(CustomCollections.looksLikeCollections(nuvioExport))
        val result = CustomCollections.parse(nuvioExport, url = "https://example.com/collections.json")
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrThrow().size)

        val rails = result.getOrThrow()
        assertEquals(listOf("Studios", "Lists"), rails.map { it.title })
        assertEquals(3, rails[0].entries.size)
        // Folders whose sources are all unsupported are skipped.
        assertEquals(2, rails[1].entries.size)

        val pixar = rails[0].entries[0]
        assertEquals(CollectionSourceKind.TMDB_DISCOVER, pixar.sources.single().kind)
        assertEquals(mapOf("with_companies" to "3"), pixar.sources.single().discoverParams)
        assertTrue(pixar.hideTitle)

        val israeli = rails[0].entries[2].sources.single()
        assertEquals("tv", israeli.mediaType)
        assertEquals("IL", israeli.discoverParams?.get("with_origin_country"))
        assertEquals("2020-01-01", israeli.discoverParams?.get("first_air_date.gte"))

        assertEquals(CollectionSourceKind.ADDON_CATALOG, rails[0].entries[1].sources.single().kind)
        assertEquals(1241, rails[1].entries[0].sources.single().tmdbCollectionId)
        assertEquals("12345", rails[1].entries[1].sources.single().traktListId)
    }

    @Test
    fun `cloud restored catalogs are valid without a device local registry`() {
        val configs = CustomCollections.catalogs(CustomCollections.parse(nuvioExport, null).getOrThrow())
        val gson = com.google.gson.Gson()
        val restored = gson.fromJson(gson.toJson(configs), Array<CatalogConfig>::class.java).toList()
        assertEquals(configs, restored)
        assertTrue(restored.all(CollectionTemplateManifest::isValidCollectionConfig))
        assertTrue(restored.none { it.isPreinstalled })
        assertTrue(MediaRepository.buildPreinstalledDefaults().none(CustomCollections::isCustom))
    }

    @Test
    fun `separate imports do not change built in defaults or each other`() {
        val before = MediaRepository.buildPreinstalledDefaults()
        val first = CustomCollections.parse(nuvioExport, "https://example.com/A.json").getOrThrow()
        val second = CustomCollections.parse(nuvioExport, "https://example.com/a.json").getOrThrow()
        assertFalse(first.first().packId == second.first().packId)
        assertEquals(before, MediaRepository.buildPreinstalledDefaults())
    }

    @Test
    fun `rejects documents that are not collections`() {
        assertFalse(CustomCollections.looksLikeCollections("""{"id":"pack","name":"x","catalogs":[]}"""))
        assertTrue(CustomCollections.parse("not json", null).isFailure)
        assertTrue(CustomCollections.parse(nuvioExport.replace("\"tmdbId\": 3", "\"tmdbId\": \"invalid\""), null).isFailure)
        assertTrue(CustomCollections.parse(nuvioExport.replace("\"tmdbId\": 3", "\"tmdbId\": -1"), null).isFailure)
    }

    @Test
    fun `reimport preserves position and renamed titles without duplicating catalogs`() {
        val imported = CustomCollections.catalogs(CustomCollections.parse(nuvioExport, null).getOrThrow())
        val other = CatalogConfig(id = "other", title = "Other", sourceType = CatalogSourceType.ADDON)
        val current = listOf(other) + imported.reversed().map { it.copy(title = "Renamed ${it.id}") }
        val merged = CustomCollections.merge(current, imported)
        assertEquals(current, merged)
        assertEquals(current, CustomCollections.merge(merged, imported))
        assertEquals(listOf(other), merged.filterNot { it.packId == imported.first().packId })
    }

    @Test
    fun `URL import identifiers match the web format`() {
        val rails = CustomCollections.parse(nuvioExport, "https://example.com/collections.json").getOrThrow()
        val expected = java.security.MessageDigest.getInstance("SHA-256")
            .digest("https://example.com/collections.json".toByteArray()).take(6)
            .joinToString("") { "%02x".format(it) }
        assertEquals("custom_usercol_${expected}_studios", rails.first().key)
    }

    @Test
    fun `addon genre remains encoded in first and subsequent page requests`() {
        val document = """{"title":"Genres","folders":[{"title":"Science Fiction","sources":[{"provider":"addon","type":"movie","catalogId":"top","genre":"Science Fiction"}]}]}"""
        val source = CustomCollections.parse(document, null).getOrThrow().single().entries.single().sources.single()
        assertEquals("Science Fiction", source.addonGenre)
        assertEquals(listOf("https://addon/catalog/movie/top/genre=Science%20Fiction.json?token=x"),
            buildCatalogRequestUrls("https://addon", "movie", "top", 0, "token=x", source.addonGenre))
        assertEquals(listOf("https://addon/catalog/movie/top/genre=Science%20Fiction&skip=20.json"),
            buildCatalogRequestUrls("https://addon", "movie", "top", 20, null, source.addonGenre))
    }

    @Test
    fun `addon-defined catalog type with a space is percent-encoded in the path`() {
        assertEquals(listOf("https://addon/catalog/Live%20Docs/kids.json"),
            buildCatalogRequestUrls("https://addon", "Live Docs", "kids", 0, null))
    }

    @Test
    fun `person and director sources use credits rather than unsupported TV discovery filters`() {
        val document = """{"title":"People","folders":[{"title":"Director","sources":[{"provider":"tmdb","tmdbSourceType":"DIRECTOR","tmdbId":123,"mediaType":"TV"}]}]}"""
        val source = CustomCollections.parse(document, null).getOrThrow().single().entries.single().sources.single()
        assertEquals(CollectionSourceKind.TMDB_PERSON, source.kind)
        assertEquals("Director", source.tmdbCreditRole)
        assertEquals("tv", source.mediaType)
        assertEquals(123, source.tmdbPersonId)
    }

    private fun railConfig(key: String) = CatalogConfig(
        id = "collection_rail_$key", title = "r", sourceType = CatalogSourceType.PREINSTALLED,
        kind = CatalogKind.COLLECTION_RAIL, collectionRailKey = key
    )

    private fun tileConfig(id: String) = CatalogConfig(
        id = id, title = "t", sourceType = CatalogSourceType.PREINSTALLED, kind = CatalogKind.COLLECTION
    )
}
