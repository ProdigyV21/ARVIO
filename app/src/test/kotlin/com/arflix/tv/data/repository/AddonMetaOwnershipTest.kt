package com.arflix.tv.data.repository

import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonManifest
import com.arflix.tv.data.model.AddonResource
import com.arflix.tv.data.model.AddonType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AddonMetaOwnershipTest {
    private fun addon(id: String, prefixes: List<String>?, resources: List<String> = listOf("catalog", "meta")) = Addon(
        id = id, name = id, version = "1", description = "", isInstalled = true, type = AddonType.CUSTOM,
        manifest = AddonManifest(id = id, name = id, version = "1",
            resources = resources.map { AddonResource(name = it) }, idPrefixes = prefixes)
    )

    // A metadata addon re-listing a broadcaster's catalog: it doesn't own the broadcaster's ids.
    private val metadata = addon("aio-metadata_1", listOf("tmdb:", "tt", "mal:"))
    private val broadcaster = addon("example.channels_2", listOf("chx_", "tmdb:", "tt"),
        listOf("catalog", "meta", "stream"))

    @Test fun reListedIdsBelongToTheAddonThatServesTheirMeta() {
        val addons = listOf(metadata, broadcaster)
        assertFalse(addonServesOwnMeta(metadata, "series", "chx_show_1"))
        assertEquals(broadcaster.id, findAddonServingOwnMeta(addons, "series", "chx_show_1", metadata.id))
        // TMDB/IMDb ids stay on the TMDB path, whoever lists them.
        assertNull(findAddonServingOwnMeta(addons, "series", "tt123", metadata.id))
    }

    @Test fun ownerIsNotFoundWhenItsAddonIsMissingOrDisabled() {
        assertNull(findAddonServingOwnMeta(listOf(metadata), "series", "chx_show_1", metadata.id))
        assertNull(findAddonServingOwnMeta(listOf(metadata, broadcaster.copy(isEnabled = false)),
            "series", "chx_show_1", metadata.id))
    }

    @Test fun metaIsNotRequestedFromAnAddonWhosePrefixesExcludeTheId() {
        assertFalse(addonMayServeMeta(metadata, "chx_show_1"))
        assertTrue(addonMayServeMeta(metadata, "tt123"))
        assertFalse(addonMayServeMeta(addon("catalog-only", null, listOf("catalog")), "x:1"))
        // Unknown manifests and prefix-less meta keep the old behaviour.
        assertTrue(addonMayServeMeta(null, "chx_1"))
        assertTrue(addonMayServeMeta(addon("open", null), "anything:1"))
    }
}
