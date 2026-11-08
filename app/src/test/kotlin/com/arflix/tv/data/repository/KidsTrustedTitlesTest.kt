package com.arflix.tv.data.repository

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.KidsAgeLevel
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KidsTrustedTitlesTest {

    private fun row(type: CatalogSourceType, preinstalled: Boolean = false, kind: CatalogKind = CatalogKind.STANDARD) =
        CatalogConfig(
            id = "custom_1",
            title = "Row",
            sourceType = type,
            isPreinstalled = preinstalled,
            kind = kind,
            sourceUrl = "https://example.com/lists/someone/some-list"
        )

    @Test
    fun `only list rows the parent added are trusted`() {
        assertTrue(KidsTrustedTitles.isTrustedSource(row(CatalogSourceType.MDBLIST)))
        assertTrue(KidsTrustedTitles.isTrustedSource(row(CatalogSourceType.TRAKT)))
        assertFalse(KidsTrustedTitles.isTrustedSource(row(CatalogSourceType.MDBLIST, preinstalled = true)))
        assertFalse(KidsTrustedTitles.isTrustedSource(row(CatalogSourceType.TMDB)))
        assertFalse(KidsTrustedTitles.isTrustedSource(row(CatalogSourceType.ADDON)))
        assertFalse(KidsTrustedTitles.isTrustedSource(row(CatalogSourceType.MDBLIST, kind = CatalogKind.COLLECTION)))
    }

    @Test
    fun `trusted keys are the union of one profile's rows`() {
        val all = mapOf(
            "kid" to mapOf("a" to setOf("TV/1"), "b" to setOf("MOVIE/2", "TV/1")),
            "adult" to mapOf("c" to setOf("MOVIE/3"))
        )
        assertEquals(setOf("TV/1", "MOVIE/2"), KidsTrustedTitles.keysIn(all, "kid"))
        assertEquals(emptySet<String>(), KidsTrustedTitles.keysIn(all, "other"))
        assertEquals(emptySet<String>(), KidsTrustedTitles.keysIn(all, null))
    }

    @Test
    fun `a trusted unrated title passes and is not looked up`() {
        val unrated = MediaItem(id = 10, title = "unrated show", mediaType = MediaType.TV)
        val sameIdMovie = MediaItem(id = 10, title = "other", mediaType = MediaType.MOVIE)
        val ages = mapOf(KidsContentFilter.key("US", MediaType.TV, 10) to KidsContentFilter.UNRATED)
        val trusted = setOf(KidsTrustedTitles.key(MediaType.TV, 10))

        val plain = KidsContentFilter.State(KidsAgeLevel.DEFAULT, "US", ages)
        assertFalse(plain.allows(unrated))

        val withTrust = KidsContentFilter.State(KidsAgeLevel.DEFAULT, "US", emptyMap(), trusted)
        assertTrue(withTrust.allows(unrated))
        assertFalse(withTrust.isUnknown(unrated))
        // Trust is per media type: a movie sharing the TMDB id is still judged by its rating.
        assertFalse(withTrust.allows(sameIdMovie))
        assertTrue(withTrust.isUnknown(sameIdMovie))
    }
}
