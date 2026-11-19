package com.arflix.tv.data.repository

import com.google.gson.JsonParser
import org.junit.Assert.*
import org.junit.Test
import java.time.Instant

class PartnerIptvDeliveryTest {
    private val now = Instant.parse("2026-10-31T12:00:00Z").toEpochMilli()
    private fun payload() = JsonParser.parseString("""{
        "managed":true,"active":true,"organizationId":"org-1","revision":4,
        "leaseUntil":"2026-11-01T12:00:00Z",
        "config":{"catalogs":[],"collectionUrls":[],"settings":{}},
        "permissions":{"editCatalogs":true,"addConnections":true,"editAppearance":true,"editPlayback":true,"editMetadata":true},
        "services":[{"id":"service-1","name":"Test IPTV","kind":"iptv",
          "url":"https://provider.example/subpath/player_api.php",
          "credentials":{"username":"user+name","password":"p&? word"},
          "iptv":{"mode":"xtream","liveTv":true,"movies":false,"series":true}}]
    }""").asJsonObject

    @Test fun `xtream path and credentials are encoded and capabilities preserved`() {
        val result = PartnerIptvDelivery.parse(payload(), now)!!
        assertEquals(4, result.revision)
        val list = result.playlists.single()
        assertEquals("partner-org-1-service-1", list.id)
        assertEquals("https://provider.example/subpath/get.php?username=user%2Bname&password=p%26%3F%20word&type=m3u_plus&output=ts", list.m3uUrl)
        assertTrue(list.epgUrl.contains("/subpath/xmltv.php?"))
        assertFalse(list.epgUrl.contains("output="))
        assertEquals(false, list.importVod)
        assertEquals(true, list.importSeries)
    }

    @Test fun `inactive and unmanaged responses remove package`() {
        val body = payload()
        body.addProperty("active", false)
        assertNull(PartnerIptvDelivery.parse(body, now))
        assertNull(PartnerIptvDelivery.parse(JsonParser.parseString("{\"managed\":false}").asJsonObject, now))
    }

    @Test fun `explicit M3U and guide URLs are preserved`() {
        val body = payload()
        val service = body.getAsJsonArray("services")[0].asJsonObject
        service.getAsJsonObject("credentials").addProperty("playlistUrl", "https://provider.example/list.m3u?token=abc")
        service.getAsJsonObject("credentials").addProperty("epgUrl", "https://provider.example/guide.xml")
        val list = PartnerIptvDelivery.parse(body, now)!!.playlists.single()
        assertEquals("https://provider.example/list.m3u?token=abc", list.m3uUrl)
        assertEquals("https://provider.example/guide.xml", list.epgUrl)
    }

    @Test fun `expired lease rejected`() {
        assertThrows(IllegalArgumentException::class.java) { PartnerIptvDelivery.parse(payload(), now + 86_400_000L) }
    }

    @Test fun `public m3u needs no separate credentials and guide restrictions are applied`() {
        val body = payload()
        val service = body.getAsJsonArray("services")[0].asJsonObject
        service.add("credentials", com.google.gson.JsonNull.INSTANCE)
        service.addProperty("url", "https://example.test/public.m3u")
        val options = service.getAsJsonObject("iptv")
        options.addProperty("mode", "m3u")
        options.addProperty("epg", false)
        options.addProperty("catchup", false)
        val list = PartnerIptvDelivery.parse(body, now)!!.playlists.single()
        assertEquals("https://example.test/public.m3u", list.m3uUrl)
        assertEquals("", list.epgUrl)
        assertEquals(false, list.epgEnabled)
        assertEquals(false, list.catchupEnabled)
    }

    @Test fun `xtream without credentials still fails closed`() {
        val body = payload()
        body.getAsJsonArray("services")[0].asJsonObject.add("credentials", com.google.gson.JsonNull.INSTANCE)
        assertThrows(IllegalArgumentException::class.java) { PartnerIptvDelivery.parse(body, now) }
    }

    @Test fun `missing customer credentials rejected instead of acknowledging empty package`() {
        val body = payload()
        body.getAsJsonArray("services")[0].asJsonObject.getAsJsonObject("credentials").remove("password")
        assertThrows(IllegalArgumentException::class.java) { PartnerIptvDelivery.parse(body, now) }
    }

    @Test fun `unsupported service fails entire revision`() {
        val body = payload()
        body.getAsJsonArray("services")[0].asJsonObject.addProperty("kind", "unknown")
        assertThrows(IllegalArgumentException::class.java) { PartnerIptvDelivery.parse(body, now) }
    }

    @Test fun `collection feeds are retained for resolution before acknowledgement`() {
        val body = payload()
        body.getAsJsonObject("config").getAsJsonArray("collectionUrls").add("https://example.test/collection.json")
        val result = PartnerIptvDelivery.parse(body, now)!!
        assertEquals(listOf("https://example.test/collection.json"), result.collectionUrls)
        assertTrue(result.hasCatalogConfiguration)
    }

    @Test fun `duplicate services rejected`() {
        val body = payload()
        body.getAsJsonArray("services").add(body.getAsJsonArray("services")[0].deepCopy())
        assertThrows(IllegalArgumentException::class.java) { PartnerIptvDelivery.parse(body, now) }
    }

    @Test fun `editing restrictions are retained for enforcement`() {
        val body = payload()
        body.getAsJsonObject("permissions").addProperty("addConnections", false)
        assertFalse(PartnerIptvDelivery.parse(body, now)!!.permissions.addConnections)
    }

    @Test fun `managed catalogs use separate identities and preserve personal catalogs`() {
        val body = payload()
        body.getAsJsonObject("config").getAsJsonArray("catalogs").add(JsonParser.parseString("""{
            "id":"movies","name":"QA Movies","sourceType":"tmdb","sourceUrl":"https://www.themoviedb.org/list/1"
        }"""))
        body.getAsJsonObject("permissions").addProperty("editCatalogs", false)
        val result = PartnerIptvDelivery.parse(body, now)!!
        val personal = listOf(com.arflix.tv.data.model.CatalogConfig("mine", "Mine", com.arflix.tv.data.model.CatalogSourceType.TMDB))
        assertEquals("partner-org-1-movies", PartnerCatalogs.overlay(personal, result).single().id)
        assertEquals(personal, PartnerCatalogs.overlay(personal, null))
        assertEquals(emptyList<com.arflix.tv.data.model.CollectionSourceConfig>(), result.catalogs.single().collectionSources)
    }

    @Test fun `template preferences are typed and invalid values reject revision`() {
        val body = payload()
        val settings = body.getAsJsonObject("config").getAsJsonObject("settings")
        settings.addProperty("netflixHomeStyle", false)
        assertEquals(false, PartnerIptvDelivery.parse(body, now)!!.settings["netflix_home_style"])
        settings.addProperty("clockFormat", "wrong")
        assertThrows(IllegalArgumentException::class.java) { PartnerIptvDelivery.parse(body, now) }
    }

    @Test fun `missing permissions fail closed`() {
        val body = payload()
        body.getAsJsonObject("permissions").remove("editMetadata")
        assertThrows(IllegalArgumentException::class.java) { PartnerIptvDelivery.parse(body, now) }
    }

    @Test fun `built in sources survive managed namespacing`() {
        val catalog = PartnerCatalogs.parse(JsonParser.parseString("""{
            "id":"trending_movies","name":"Popular movies","sourceType":"preinstalled"
        }""").asJsonObject, "org")
        assertEquals("partner-org-trending_movies", catalog.id)
        assertEquals("trending_movies", catalog.sourceRef)
    }

    @Test fun `managed collection rails remain recognized as imported collections`() {
        val rail = PartnerCatalogs.parse(JsonParser.parseString("""{
            "id":"rail","name":"QA collection","sourceType":"preinstalled","kind":"collection_rail",
            "packId":"usercol_test","collectionRailKey":"films"
        }""").asJsonObject, "org")
        assertTrue(CustomCollections.isCustom(rail))
        assertEquals("partner-org-films", rail.collectionRailKey)
        val collection = PartnerCatalogs.parse(JsonParser.parseString("""{
            "id":"films","name":"Movies","sourceType":"preinstalled","kind":"collection",
            "collectionSources":[{"kind":"tmdb_collection","tmdbCollectionId":1241}]
        }""").asJsonObject, "org")
        assertEquals(com.arflix.tv.data.model.CollectionSourceKind.TMDB_COLLECTION, collection.collectionSources.single().kind)
    }

    @Test fun `encoded addon references bind to the delivered service`() {
        val catalog = com.arflix.tv.data.model.CatalogConfig("qa", "QA", com.arflix.tv.data.model.CatalogSourceType.ADDON,
            sourceRef = "addon_catalog|service%2Bid|series|popular", addonId = "service+id")
        val mapped = PartnerCatalogs.remapReferences(catalog, mapOf("service+id" to "partner-org-service"))
        assertEquals("partner-org-service", mapped.addonId)
        assertEquals("addon_catalog|partner-org-service|series|popular", mapped.sourceRef)
    }
}
