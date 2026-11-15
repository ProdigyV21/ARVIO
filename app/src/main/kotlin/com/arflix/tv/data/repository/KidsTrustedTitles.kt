package com.arflix.tv.data.repository

import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaType

/** Local, explicit approvals. A row refresh can revoke entries, never create them. */
internal object KidsTrustedTitles {
    fun preferenceKey(profileId: String) = stringSetPreferencesKey("profile_${profileId}_kids_list_approvals_v2")

    fun isTrustedSource(catalog: CatalogConfig): Boolean =
        !catalog.isPreinstalled && catalog.kind == CatalogKind.STANDARD &&
            (catalog.sourceType == CatalogSourceType.MDBLIST || catalog.sourceType == CatalogSourceType.TRAKT)

    // Include the source as well as the row ID: editing or restoring a row cannot transfer approval.
    fun rowKey(catalog: CatalogConfig): String =
        listOf(catalog.id, catalog.sourceType.name, catalog.sourceUrl.orEmpty(), catalog.sourceRef.orEmpty())
            .joinToString("") { "${it.length}:$it" }

    fun key(mediaType: MediaType, tmdbId: Int): String = "${mediaType.name}/$tmdbId"

    fun entry(catalog: CatalogConfig, type: MediaType, id: Int): String = "${rowKey(catalog)}|${key(type, id)}"

    fun forRow(entries: Set<String>, catalog: CatalogConfig): Set<String> {
        val row = rowKey(catalog)
        return entries.filterTo(hashSetOf()) { it.substringBeforeLast('|') == row }
    }

    fun retainRows(entries: Set<String>, catalogs: List<CatalogConfig>): Set<String> {
        val rows = catalogs.filter(::isTrustedSource).mapTo(hashSetOf(), ::rowKey)
        return entries.filterTo(hashSetOf()) { it.substringBeforeLast('|') in rows }
    }

    fun keys(entries: Set<String>): Set<String> = entries.mapTo(hashSetOf()) { it.substringAfterLast('|') }

    fun retainMembers(
        entries: Set<String>,
        catalog: CatalogConfig,
        previouslyApproved: Set<String>,
        refs: List<Pair<MediaType, Int>>
    ): Set<String> {
        val present = refs.mapTo(hashSetOf()) { (type, id) -> entry(catalog, type, id) }
        return entries - (previouslyApproved - present)
    }
}

/**
 * Addon catalogs a parent approved for a kids profile (with the parent PIN), such as a
 * broadcaster's kids' catalogue. Their titles often have no TMDB entry at all — an addon's own
 * items — so no rating can ever be looked up; the approval is what lets them through.
 *
 * A title is trusted once an approved catalog listed it on that profile: every page of an
 * approved catalog that loads records its titles ([members]). A TMDB title with a known
 * certification still follows the age limit; approval only stands in for a missing rating.
 * Local to the device, like the list approvals.
 */
internal object KidsTrustedCatalogs {
    fun approvalsKey(profileId: String) = stringSetPreferencesKey("profile_${profileId}_kids_catalog_approvals_v1")
    fun membersKey(profileId: String) = stringSetPreferencesKey("profile_${profileId}_kids_catalog_members_v1")

    /** Titles kept per profile; the oldest are dropped past this, so the set stays small. */
    const val MAX_MEMBERS = 20_000

    /** One addon catalog: the addon that serves it, its type and its id. */
    fun catalogKey(addonId: String, catalogType: String, catalogId: String): String =
        listOf(addonId.trim(), catalogType.trim().lowercase(), catalogId.trim())
            .joinToString("") { "${it.length}:$it" }

    fun member(catalogKey: String, type: MediaType, id: Int): String = "$catalogKey|${KidsTrustedTitles.key(type, id)}"

    fun titleKeys(members: Set<String>): Set<String> = members.mapTo(hashSetOf()) { it.substringAfterLast('|') }

    fun withoutCatalogs(members: Set<String>, catalogKeys: Collection<String>): Set<String> =
        members.filterTo(linkedSetOf()) { it.substringBeforeLast('|') !in catalogKeys }

    /** An addon catalog row, or a collection tile drawing from addon catalogs. */
    fun isApprovable(catalog: CatalogConfig): Boolean =
        (catalog.kind == CatalogKind.STANDARD && catalog.sourceType == CatalogSourceType.ADDON) ||
            (catalog.kind == CatalogKind.COLLECTION &&
                catalog.collectionSources.any { it.kind == com.arflix.tv.data.model.CollectionSourceKind.ADDON_CATALOG })
}

internal data class KidsListApprovalSession(val profileId: String, val catalog: CatalogConfig, val parentPinHash: String)

internal data class KidsListRefresh(val profileId: String, val previouslyApproved: Set<String>)
