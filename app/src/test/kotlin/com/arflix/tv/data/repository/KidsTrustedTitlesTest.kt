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
    fun `row removal or source replacement revokes only its approvals`() {
        val list = row(CatalogSourceType.MDBLIST)
        val other = row(CatalogSourceType.TRAKT).copy(id = "other")
        val entries = setOf(KidsTrustedTitles.entry(list, MediaType.TV, 1), KidsTrustedTitles.entry(other, MediaType.MOVIE, 2))
        val remaining = setOf("MOVIE/2")
        assertEquals(remaining, KidsTrustedTitles.keys(KidsTrustedTitles.retainRows(entries, listOf(other))))
        assertEquals(remaining, KidsTrustedTitles.keys(KidsTrustedTitles.retainRows(
            entries, listOf(other, list.copy(sourceRef = "replacement"))
        )))
        assertEquals(entries, KidsTrustedTitles.retainRows(entries, listOf(other, list.copy(title = "Renamed"))))
        assertFalse(KidsTrustedTitles.preferenceKey("one") == KidsTrustedTitles.preferenceKey("two"))
    }

    @Test
    fun `refresh only revokes missing members including a successful empty list`() {
        val list = row(CatalogSourceType.TRAKT)
        val old = setOf(KidsTrustedTitles.entry(list, MediaType.TV, 1))
        val newerApproval = KidsTrustedTitles.entry(list, MediaType.TV, 2)
        val current = old + newerApproval
        assertEquals(setOf(newerApproval), KidsTrustedTitles.retainMembers(current, list, old, emptyList()))
        assertEquals(old, KidsTrustedTitles.retainMembers(old, list, old, listOf(MediaType.TV to 1, MediaType.TV to 2)))
        assertTrue(KidsTrustedTitles.retainMembers(old, list, old, emptyList()).isEmpty())
    }

    @Test
    fun `approval only exempts a verified missing certification`() {
        val unrated = MediaItem(id = 10, title = "unrated show", mediaType = MediaType.TV)
        val sameIdMovie = MediaItem(id = 10, title = "other", mediaType = MediaType.MOVIE)
        val trusted = setOf(KidsTrustedTitles.key(MediaType.TV, 10))
        val key = KidsContentFilter.key("US", MediaType.TV, 10)
        val ages = mapOf(key to KidsContentFilter.NO_CERTIFICATION)
        assertFalse(KidsContentFilter.State(KidsAgeLevel.DEFAULT, "US", ages).allows(unrated))
        val withTrust = KidsContentFilter.State(KidsAgeLevel.DEFAULT, "US", ages, trusted)
        assertTrue(withTrust.allows(unrated))
        assertFalse(withTrust.isUnknown(unrated))
        // Trust is per media type: a movie sharing the TMDB id is still judged by its rating.
        assertFalse(withTrust.allows(sameIdMovie))
        assertTrue(withTrust.isUnknown(sameIdMovie))
        for (blockedAge in listOf(17, KidsContentFilter.UNRATED)) {
            assertFalse(withTrust.copy(ages = mapOf(key to blockedAge)).allows(unrated))
        }
        assertFalse(withTrust.copy(ages = emptyMap()).allows(unrated))
        assertTrue(withTrust.copy(ages = emptyMap()).isUnknown(unrated))
    }

    @Test
    fun `an addon's own item passes only once an approved catalog listed it`() {
        val native = MediaItem(id = -321, title = "Example kids show", mediaType = MediaType.TV,
            addonNativeId = "example:show", addonNativeAddonId = "example.addon")
        val catalog = KidsTrustedCatalogs.catalogKey("example.addon", "Series", "kids")
        val members = setOf(KidsTrustedCatalogs.member(catalog, MediaType.TV, -321))

        assertFalse(KidsContentFilter.State(KidsAgeLevel.DEFAULT, "US", emptyMap()).allows(native))
        val trusted = KidsTrustedCatalogs.titleKeys(members)
        assertTrue(KidsContentFilter.State(KidsAgeLevel.DEFAULT, "US", emptyMap(), trusted).allows(native))
        // Adult profiles are not filtered at all.
        assertTrue(KidsContentFilter.State(null, "US", emptyMap()).allows(native))
    }

    @Test
    fun `removing a catalog's approval drops only its titles`() {
        val kids = KidsTrustedCatalogs.catalogKey("example.addon", "series", "kids")
        val other = KidsTrustedCatalogs.catalogKey("example.addon", "series", "other")
        val members = setOf(
            KidsTrustedCatalogs.member(kids, MediaType.TV, -1),
            KidsTrustedCatalogs.member(other, MediaType.TV, -2)
        )
        assertEquals(setOf("TV/-2"), KidsTrustedCatalogs.titleKeys(KidsTrustedCatalogs.withoutCatalogs(members, listOf(kids))))
        // The type is compared case-insensitively, the way loading and approving may spell it.
        assertEquals(kids, KidsTrustedCatalogs.catalogKey(" example.addon ", "SERIES", "kids"))
    }

    @Test
    fun `addon rows and collection tiles with addon sources can be approved`() {
        assertTrue(KidsTrustedCatalogs.isApprovable(row(CatalogSourceType.ADDON)))
        assertFalse(KidsTrustedCatalogs.isApprovable(row(CatalogSourceType.MDBLIST)))
        assertFalse(KidsTrustedCatalogs.isApprovable(row(CatalogSourceType.TMDB)))
        val tile = row(CatalogSourceType.ADDON, kind = CatalogKind.COLLECTION).copy(
            collectionSources = listOf(com.arflix.tv.data.model.CollectionSourceConfig(
                kind = com.arflix.tv.data.model.CollectionSourceKind.ADDON_CATALOG,
                addonCatalogType = "series", addonCatalogId = "kids"
            ))
        )
        assertTrue(KidsTrustedCatalogs.isApprovable(tile))
        assertFalse(KidsTrustedCatalogs.isApprovable(tile.copy(collectionSources = emptyList())))
    }

    @Test
    fun `kids catalog approvals ride the profile's cloud settings, and older clients don't clear them`() {
        val gson = com.google.gson.Gson()
        val key = KidsTrustedCatalogs.catalogKey("example.addon", "series", "kids")
        val sent = CloudSyncRepository.CloudProfileSettings(kidsCatalogApprovals = listOf(key))
        val received = gson.fromJson(gson.toJson(sent), CloudSyncRepository.CloudProfileSettings::class.java)
        assertEquals(listOf(key), received.kidsCatalogApprovals)
        // A payload from a client that doesn't know the field leaves it null: nothing is cleared.
        val older = gson.fromJson("{\"defaultSubtitle\":\"Off\"}", CloudSyncRepository.CloudProfileSettings::class.java)
        assertEquals(null, older.kidsCatalogApprovals)
    }
}
