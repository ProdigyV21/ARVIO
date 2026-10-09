package com.arflix.tv.data.repository

import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonResource
import java.util.Locale

// Checks against an already-read addon list. Reading StreamRepository.installedAddons parses
// every installed manifest, so a catalog page must read it once, not once per item.

/**
 * True when [addon] serves its own metadata for [contentId]: its manifest offers a `meta`
 * resource whose id prefixes cover that id (a broadcaster VOD addon, say). Such items can be
 * shown natively instead of being matched to TMDB, which would hide every title TMDB doesn't
 * list. Ids TMDB lookups understand and the anime id families ARVIO maps through its own anime
 * pipeline are left out, so those addons behave as before.
 */
internal fun addonServesOwnMeta(addon: Addon, type: String, contentId: String): Boolean {
    val id = contentId.trim()
    if (id.isBlank() || NON_NATIVE_META_ID_FAMILIES.any { id.startsWith(it, ignoreCase = true) }) return false
    if (!addon.isEnabled) return false
    val manifest = addon.manifest ?: return false
    val normalizedType = type.trim().lowercase(Locale.US)
    return manifest.resources.any { resource ->
        if (!resource.name.equals("meta", ignoreCase = true)) return@any false
        if (!supportsResourceType(resource.types, normalizedType)) return@any false
        // An addon that names no prefix at all can't be said to own any id.
        val prefixes = resource.idPrefixes ?: manifest.idPrefixes
        prefixes.orEmpty().any { prefix ->
            prefix.isNotBlank() && id.startsWith(prefix.trim(), ignoreCase = true)
        }
    }
}

/**
 * The addon in [addons] (other than [excludeAddonId]) that serves its own metadata for
 * [contentId]. Metadata addons such as AIOMetadata re-list another addon's catalog (a channel
 * addon's own ids) without serving its metadata or streams; the owner does.
 */
internal fun findAddonServingOwnMeta(
    addons: List<Addon>,
    type: String,
    contentId: String,
    excludeAddonId: String? = null
): String? = addons
    .filter { it.isInstalled && it.isEnabled && it.id != excludeAddonId }
    .firstOrNull { addonServesOwnMeta(it, type, contentId) }
    ?.id

/**
 * False when [addon]'s manifest shows it cannot answer /meta for [contentId]: no meta resource,
 * or meta id prefixes that don't cover the id. Asking anyway costs a request per type alias per
 * item, enough to get an addon's server to rate-limit us.
 */
internal fun addonMayServeMeta(addon: Addon?, contentId: String): Boolean {
    val manifest = addon?.manifest ?: return true
    // Gson leaves absent manifest fields null despite the Kotlin types.
    val resources: List<AddonResource?> = (manifest.resources as List<AddonResource?>?).orEmpty()
    if (resources.isEmpty()) return true
    return resources.filterNotNull().filter { it.name.equals("meta", ignoreCase = true) }.any { resource ->
        val prefixes = resource.idPrefixes ?: manifest.idPrefixes
        prefixes.isNullOrEmpty() || prefixes.any { prefix ->
            prefix.isNotBlank() && contentId.startsWith(prefix.trim(), ignoreCase = true)
        }
    }
}

internal fun supportsResourceType(resourceTypes: List<String>?, requestedType: String): Boolean {
    if (resourceTypes.isNullOrEmpty()) return true
    val normalized = resourceTypes.map { it.trim().lowercase(Locale.US) }
    val aliases = when (requestedType) {
        "series", "tv", "show" -> setOf("series", "tv", "show")
        "anime" -> setOf("anime", "series", "tv", "show")
        "movie", "film" -> setOf("movie", "film")
        else -> setOf(requestedType)
    }
    return normalized.any { it in aliases }
}
