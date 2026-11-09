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

internal data class KidsListApprovalSession(val profileId: String, val catalog: CatalogConfig, val parentPinHash: String)

internal data class KidsListRefresh(val profileId: String, val previouslyApproved: Set<String>)
