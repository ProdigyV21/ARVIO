package com.arflix.tv.data.model

import com.arflix.tv.data.api.StremioAddonBehaviorHints
import com.arflix.tv.data.api.StremioAddonCollectionResponse
import com.arflix.tv.data.api.StremioAddonDescriptor
import com.arflix.tv.data.api.StremioAddonDescriptorManifest
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CommunityAddonTest {

    private fun descriptor(
        id: String?,
        url: String? = "https://$id.example/manifest.json",
        name: String? = id,
        types: List<String?>? = listOf("movie"),
        hints: StremioAddonBehaviorHints? = null
    ) = StremioAddonDescriptor(
        transportUrl = url,
        transportName = "http",
        manifest = StremioAddonDescriptorManifest(id = id, name = name, version = "1.0.0", types = types, behaviorHints = hints)
    )

    @Test
    fun `parses the addon_catalog shape served by Cinemeta`() {
        val json = """
            {"addons":[{"transportUrl":"https://anime-kitsu.strem.fun/manifest.json","transportName":"http",
              "manifest":{"id":"community.anime.kitsu","version":"0.0.10","name":"Anime Kitsu",
                "description":"Unofficial Kitsu.io anime catalog addon","logo":"https://i.imgur.com/7N6XGoO.png",
                "resources":["catalog","meta"],"types":["anime","movie","series"],
                "behaviorHints":{"configurable":true}}}]}
        """.trimIndent()
        val addons = CommunityAddons.fromResponse(Gson().fromJson(json, StremioAddonCollectionResponse::class.java))

        assertEquals(1, addons.size)
        val kitsu = addons.single()
        assertEquals("community.anime.kitsu", kitsu.manifestId)
        assertEquals("Anime Kitsu", kitsu.name)
        assertEquals(listOf("anime", "movie", "series"), kitsu.types)
        assertTrue(kitsu.configurable)
        assertEquals("https://anime-kitsu.strem.fun/configure", kitsu.configureUrl)
    }

    @Test
    fun `drops entries that cannot be installed, adult addons and duplicates`() {
        val addons = CommunityAddons.fromResponse(
            StremioAddonCollectionResponse(
                listOf(
                    descriptor("a"),
                    descriptor(null),
                    descriptor("no-url", url = null),
                    descriptor("bad-url", url = "stremio://bad/manifest.json"),
                    descriptor("adult", hints = StremioAddonBehaviorHints(adult = true)),
                    descriptor("a", url = "https://other.example/manifest.json"),
                    StremioAddonDescriptor(transportUrl = "https://x.example/manifest.json", manifest = null)
                )
            )
        )
        assertEquals(listOf("a"), addons.map { it.manifestId })
        assertEquals("https://a.example/manifest.json", addons.single().transportUrl)
    }

    @Test
    fun `missing fields fall back instead of failing`() {
        val addon = CommunityAddons.fromResponse(
            StremioAddonCollectionResponse(listOf(descriptor("plain", name = " ", types = listOf(" Movie ", null, "movie"))))
        ).single()
        assertEquals("plain", addon.name)
        assertEquals(listOf("movie"), addon.types)
        assertNull(addon.configureUrl)
    }

    @Test
    fun `filter matches every search word against name, description and type`() {
        val addons = CommunityAddons.fromResponse(
            StremioAddonCollectionResponse(
                listOf(
                    descriptor("kitsu", name = "Anime Kitsu", types = listOf("anime", "series")),
                    descriptor("radio", name = "Radios", types = listOf("music")),
                    descriptor("ratings", name = "IMDb Ratings", types = listOf("movie", "series"))
                )
            )
        )
        assertEquals(listOf("kitsu"), CommunityAddons.filter(addons, "anime kit", null).map { it.manifestId })
        assertEquals(listOf("kitsu", "ratings"), CommunityAddons.filter(addons, "", "series").map { it.manifestId })
        assertEquals(listOf("radio"), CommunityAddons.filter(addons, " MUSIC ", null).map { it.manifestId })
        assertTrue(CommunityAddons.filter(addons, "kitsu", "music").isEmpty())
    }

    @Test
    fun `type filters are ordered by how many addons use them and skip one-off types`() {
        val addons = CommunityAddons.fromResponse(
            StremioAddonCollectionResponse(
                listOf(
                    descriptor("a", types = listOf("movie", "series", "anime")),
                    descriptor("b", types = listOf("series", "dice")),
                    descriptor("c", types = listOf("anime", "series")),
                    descriptor("d", types = listOf("movie", "movie"))
                )
            )
        )
        assertEquals(listOf("series", "anime", "movie"), CommunityAddons.typeFilters(addons))
        assertEquals(listOf("series"), CommunityAddons.typeFilters(addons, max = 1))
    }

    @Test
    fun `html in descriptions is reduced to its text`() {
        val response = StremioAddonCollectionResponse(
            listOf(
                StremioAddonDescriptor(
                    transportUrl = "https://jump.example/manifest.json",
                    manifest = StremioAddonDescriptorManifest(
                        id = "jump",
                        description = "Open source at <a href='https://github.com/x'>GitHub</a>.<br/>Tom &amp; Jerry"
                    )
                )
            )
        )
        assertEquals("Open source at GitHub . Tom & Jerry", CommunityAddons.fromResponse(response).single().description)
    }

    @Test
    fun `an installed setup with other settings still counts as installed`() {
        val entry = CommunityAddons.fromResponse(StremioAddonCollectionResponse(listOf(descriptor("cfg")))).single()
        val configured = Addon(
            id = "cfg_abc123",
            name = "Configured",
            version = "1.0.0",
            description = "",
            isInstalled = true,
            isEnabled = true,
            type = AddonType.CUSTOM,
            url = "https://cfg.example/eyJ0b2tlbiI6IngifQ/manifest.json",
            manifest = AddonManifest(id = "cfg", name = "Configured", version = "1.0.0", description = "")
        )
        val other = configured.copy(id = "other", manifest = configured.manifest?.copy(id = "other"))
        assertEquals(listOf("cfg_abc123"), entry.installedSetups(listOf(configured, other)).map { it.id })
    }
}
