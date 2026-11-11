package com.arflix.tv.data.repository

import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonCatalog
import com.arflix.tv.data.model.AddonManifest
import java.net.URI
import java.util.Locale

data class AddonChange(val updatedAt: Long = 0L, val removed: Boolean = false)

data class AddonManifestCacheEntry(
    val manifest: AddonManifest,
    val updatedAt: Long = 0L
)

data class AddonCloudState(
    val addons: List<Addon>,
    val updatedAt: Long,
    val changes: Map<String, AddonChange>
)

internal fun normalizeAddonUrlKey(rawUrl: String?): String {
    val trimmed = rawUrl?.trim()?.lowercase(Locale.US).orEmpty()
    if (trimmed.isBlank()) return ""
    val withoutManifest = trimmed.replace(Regex("""/manifest\.json(\?.*)?$"""), "$1")
    return withoutManifest.trimEnd('/')
}

internal fun isConfiguredAddonUrl(rawUrl: String?): Boolean {
    val value = rawUrl?.trim().orEmpty()
    if (value.isBlank()) return false
    return try {
        val uri = URI(value)
        if (!uri.rawQuery.isNullOrBlank()) return true
        val path = uri.rawPath.orEmpty()
            .replace(Regex("""/manifest\.json$""", RegexOption.IGNORE_CASE), "")
            .trim('/')
        path.isNotEmpty()
    } catch (_: Exception) {
        value.contains('?')
    }
}

private fun mergeCatalogMetadata(
    primary: List<AddonCatalog>,
    fallback: List<AddonCatalog>,
    allowClearOmitted: Boolean
): List<AddonCatalog> {
    if (allowClearOmitted) return primary
    if (primary.isEmpty()) return fallback
    if (fallback.isEmpty()) return primary
    val fallbackByKey = fallback.associateBy { "${it.type.lowercase(Locale.US)}:${it.id}" }
    return primary.map { catalog ->
        val fb = fallbackByKey["${catalog.type.lowercase(Locale.US)}:${catalog.id}"] ?: return@map catalog
        catalog.copy(
            clientDelegate = catalog.clientDelegate ?: fb.clientDelegate,
            clientFilter = catalog.clientFilter ?: fb.clientFilter
        )
    }
}

internal fun mergeAddonManifests(
    stored: AddonManifest?,
    incoming: AddonManifest?,
    storedMetadataUpdatedAt: Long,
    incomingMetadataUpdatedAt: Long,
    preferIncomingOnTie: Boolean
): AddonManifest? {
    if (stored == null) return incoming
    if (incoming == null) return stored
    val incomingWins = when {
        incomingMetadataUpdatedAt > storedMetadataUpdatedAt -> true
        storedMetadataUpdatedAt > incomingMetadataUpdatedAt -> false
        else -> preferIncomingOnTie
    }
    val primary = if (incomingWins) incoming else stored
    val fallback = if (incomingWins) stored else incoming
    val allowClearOmitted = (incomingWins && incomingMetadataUpdatedAt > storedMetadataUpdatedAt) ||
        (!incomingWins && storedMetadataUpdatedAt > incomingMetadataUpdatedAt)
    val mergedCatalogs = mergeCatalogMetadata(primary.catalogs, fallback.catalogs, allowClearOmitted)
    return primary.copy(
        resources = if (allowClearOmitted) primary.resources else primary.resources.ifEmpty { fallback.resources },
        types = if (allowClearOmitted) primary.types else primary.types.ifEmpty { fallback.types },
        idPrefixes = primary.idPrefixes ?: fallback.idPrefixes,
        behaviorHints = primary.behaviorHints ?: fallback.behaviorHints,
        catalogs = mergedCatalogs
    )
}

/**
 * Merges setup fields ([Addon.url], [Addon.name], [Addon.isEnabled]) separately from
 * refreshable manifest metadata ([Addon.manifest]) so legacy sync payloads or stale
 * refreshes cannot erase configured URLs or delegation/filter fields.
 */
internal fun mergeAddonPreservingSetupAndMetadata(
    stored: Addon,
    incoming: Addon,
    preferIncomingOnTie: Boolean = true,
    storedUpdatedAt: Long = 0L,
    incomingUpdatedAt: Long = 0L
): Addon {
    val storedSetupAt = stored.setupUpdatedAt.takeIf { it > 0L } ?: storedUpdatedAt
    val incomingSetupAt = incoming.setupUpdatedAt.takeIf { it > 0L } ?: incomingUpdatedAt
    val setupWinner = when {
        incomingSetupAt > storedSetupAt -> incoming
        storedSetupAt > incomingSetupAt -> stored
        preferIncomingOnTie -> incoming
        else -> stored
    }
    val setupFallback = if (setupWinner === incoming) stored else incoming

    val winnerUrl = setupWinner.url?.takeIf { it.isNotBlank() }
    val fallbackUrl = setupFallback.url?.takeIf { it.isNotBlank() }
    val keepFallbackConfiguredUrl = isConfiguredAddonUrl(fallbackUrl) && !isConfiguredAddonUrl(winnerUrl) &&
        setupWinner.setupUpdatedAt <= (if (setupWinner === incoming) storedSetupAt else incomingSetupAt)

    val resolvedUrl = if (keepFallbackConfiguredUrl) fallbackUrl else winnerUrl ?: fallbackUrl
    val resolvedTransport = if (keepFallbackConfiguredUrl) {
        setupFallback.transportUrl ?: setupWinner.transportUrl
    } else {
        setupWinner.transportUrl ?: setupFallback.transportUrl
    }

    val mergedManifest = mergeAddonManifests(
        stored = stored.manifest,
        incoming = incoming.manifest,
        storedMetadataUpdatedAt = stored.metadataUpdatedAt,
        incomingMetadataUpdatedAt = incoming.metadataUpdatedAt,
        preferIncomingOnTie = preferIncomingOnTie
    )
    val metaWinner = if (incoming.metadataUpdatedAt >= stored.metadataUpdatedAt) incoming else stored
    val metaFallback = if (metaWinner === incoming) stored else incoming

    return setupWinner.copy(
        name = setupWinner.name.ifBlank { setupFallback.name },
        version = metaWinner.version.ifBlank { metaFallback.version },
        description = metaWinner.description.ifBlank { metaFallback.description },
        logo = metaWinner.logo ?: metaFallback.logo,
        configureUrl = setupWinner.configureUrl ?: setupFallback.configureUrl,
        url = resolvedUrl,
        transportUrl = resolvedTransport,
        manifest = mergedManifest,
        setupUpdatedAt = maxOf(storedSetupAt, incomingSetupAt),
        metadataUpdatedAt = maxOf(stored.metadataUpdatedAt, incoming.metadataUpdatedAt)
    )
}

internal fun enrichAddonWithMetadata(
    addon: Addon,
    manifest: AddonManifest?,
    metadataUpdatedAt: Long
): Addon {
    if (manifest == null) return addon
    val mergedManifest = mergeAddonManifests(
        stored = manifest,
        incoming = addon.manifest,
        storedMetadataUpdatedAt = metadataUpdatedAt,
        incomingMetadataUpdatedAt = addon.metadataUpdatedAt,
        preferIncomingOnTie = false
    )
    return addon.copy(
        version = mergedManifest?.version ?: addon.version,
        description = mergedManifest?.description ?: addon.description,
        logo = mergedManifest?.logo ?: addon.logo,
        manifest = mergedManifest,
        metadataUpdatedAt = maxOf(addon.metadataUpdatedAt, metadataUpdatedAt)
    )
}

internal fun mergeAddonChanges(
    local: Map<String, AddonChange>,
    remote: Map<String, AddonChange>
): Map<String, AddonChange> = buildMap {
    for ((id, change) in local.entries + remote.entries) {
        if (id.isBlank() || change.updatedAt <= 0 || (id == "opensubtitles" && change.removed)) continue
        val previous = get(id)
        if (previous == null || change.updatedAt > previous.updatedAt ||
            (change.updatedAt == previous.updatedAt && change.removed)) put(id, change)
    }
}

internal fun reconcileExplicitAddonState(local: AddonCloudState, remote: AddonCloudState): AddonCloudState {
    val changes = mergeAddonChanges(local.changes, remote.changes)
    val localWinsOnTie = local.updatedAt > remote.updatedAt
    val ordered = if (localWinsOnTie) local.addons + remote.addons else remote.addons + local.addons
    val localById = local.addons.associateBy { it.id }
    val remoteById = remote.addons.associateBy { it.id }
    val mergedById = LinkedHashMap<String, Addon>()
    for (entry in ordered) {
        if (entry.id.isBlank() || changes[entry.id]?.removed == true) continue
        if (entry.id in mergedById) continue
        val l = localById[entry.id]
        val r = remoteById[entry.id]
        mergedById[entry.id] = when {
            l != null && r != null -> mergeAddonPreservingSetupAndMetadata(
                stored = r,
                incoming = l,
                preferIncomingOnTie = localWinsOnTie,
                storedUpdatedAt = remote.updatedAt,
                incomingUpdatedAt = local.updatedAt
            )
            else -> entry
        }
    }
    return AddonCloudState(
        addons = mergedById.values.toList(),
        updatedAt = maxOf(local.updatedAt, remote.updatedAt, changes.values.maxOfOrNull { it.updatedAt } ?: 0L),
        changes = changes
    )
}

internal fun recordAddonChanges(previous: Map<String, AddonChange>, added: Set<String>, removed: Set<String>, timestamp: Long): Map<String, AddonChange> {
    val edits = added.associateWith { AddonChange(timestamp, false) } + removed.associateWith { AddonChange(timestamp, true) }
    return mergeAddonChanges(previous, edits)
}
