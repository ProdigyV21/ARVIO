package com.arflix.tv.data.model

import java.util.Locale

object SportsAddonCapabilities {
    const val SPORTS_CATEGORY_ROW_ID = "sports"
    const val POPULAR_LIVE_TV_ROW_ID = "popular_live_tv"
    const val SPORTS_STATUS_PREFIX = "sports:"
    const val SPORTS_LOCKED_STATUS_PREFIX = "sports_locked:"
    const val SPORTS_EVENT_STATUS_PREFIX = "sports_event:"
    const val SPORTS_COLLECTION_PREFIX = "sports_collection:"

    private val sportTerms = setOf(
        "sport",
        "sports",
        "football",
        "soccer",
        "basketball",
        "tennis",
        "motorsport",
        "formula",
        "racing",
        "rugby",
        "hockey",
        "baseball",
        "boxing",
        "ufc",
        "mma",
        "cricket",
        "golf"
    )

    private val liveTerms = setOf("live", "event", "events", "match", "matches", "game", "games")

    private val explicitVodTypes = setOf("movie", "film", "series", "show", "anime")

    // Internal sources that contain "iptv" in their ID but serve on-demand media.
    // Keep this exact so third-party IPTV/live add-ons remain classified as live.
    private val explicitVodStreamAddonIds = IptvVodSourceIds.ALL

    fun isSportsHomeStatus(status: String?): Boolean {
        val value = status ?: return false
        return value.startsWith(SPORTS_STATUS_PREFIX) ||
            value.startsWith(SPORTS_LOCKED_STATUS_PREFIX) ||
            value.startsWith(SPORTS_EVENT_STATUS_PREFIX)
    }

    fun isSportsLockedStatus(status: String?): Boolean =
        status?.startsWith(SPORTS_LOCKED_STATUS_PREFIX) == true

    fun isSportsCategoryStatus(status: String?): Boolean =
        status?.startsWith(SPORTS_STATUS_PREFIX) == true

    fun isSportsEventStatus(status: String?): Boolean =
        status?.startsWith(SPORTS_EVENT_STATUS_PREFIX) == true

    fun sportsCollectionCatalogId(sportId: String): String =
        "$SPORTS_COLLECTION_PREFIX$sportId"

    fun isSportsCollectionCatalogId(catalogId: String?): Boolean =
        catalogId?.startsWith(SPORTS_COLLECTION_PREFIX) == true

    fun sportIdFromCollectionCatalogId(catalogId: String): String? =
        catalogId.takeIf { isSportsCollectionCatalogId(it) }
            ?.removePrefix(SPORTS_COLLECTION_PREFIX)
            ?.takeIf { it.isNotBlank() }

    fun isSportsLiveTvAddon(addon: Addon): Boolean =
        addon.manifest?.let(::isSportsLiveTvManifest) == true

    fun isSportsOnlyLiveTvAddon(addon: Addon): Boolean =
        addon.manifest?.let(::isSportsOnlyLiveTvManifest) == true

    fun isSportsOnlyLiveTvManifest(manifest: AddonManifest): Boolean =
        isSportsLiveTvManifest(manifest) && !manifestDeclaresVodStreams(manifest)

    fun isSportsLiveTvManifest(manifest: AddonManifest): Boolean {
        val resources = manifest.safeResources()
        val catalogs = manifest.safeCatalogs()
        val hasStream = resources.any { resource ->
            resource.safeName().equals("stream", ignoreCase = true) ||
                resource.safeName().equals("streams", ignoreCase = true)
        }
        val hasCatalog = resources.any { resource ->
            resource.safeName().equals("catalog", ignoreCase = true) ||
                resource.safeName().equals("catalogs", ignoreCase = true)
        } || catalogs.isNotEmpty()

        if (!hasStream || !hasCatalog) return false

        if (catalogs.any(::isSportsCatalog)) return true

        val manifestText = normalizedText(
            manifest.id,
            manifest.name,
            manifest.description,
            manifest.safeTypes().joinToString(" "),
            resources.flatMap { it.safeTypes() }.joinToString(" ")
        )
        return containsAny(manifestText, sportTerms) && containsAny(manifestText, liveTerms)
    }

    fun isSportsCatalog(catalog: AddonCatalog): Boolean {
        val text = normalizedText(
            catalog.type,
            catalog.id,
            catalog.name,
            catalog.genres.orEmpty().joinToString(" ")
        )
        return containsAny(text, sportTerms)
    }

    fun isLiveSportsCatalog(catalog: AddonCatalog): Boolean {
        val text = normalizedText(catalog.type, catalog.id, catalog.name)
        return isSportsCatalog(catalog) || containsAny(text, liveTerms)
    }

    fun sportsSyntheticId(raw: String): Int =
        (raw.hashCode() and Int.MAX_VALUE).takeIf { it > 0 } ?: 1

    private fun manifestDeclaresVodStreams(manifest: AddonManifest): Boolean {
        if (manifest.safeTypes().any(::isExplicitVodType)) return true
        if (manifest.safeCatalogs().any { catalog -> isExplicitVodType(catalog.type) }) return true

        return manifest.safeResources().any { resource ->
            val isStreamResource = resource.safeName().equals("stream", ignoreCase = true) ||
                resource.safeName().equals("streams", ignoreCase = true)
            isStreamResource && resource.safeTypes().any(::isExplicitVodType)
        }
    }

    private fun isExplicitVodType(value: String): Boolean =
        value.trim().lowercase(Locale.US) in explicitVodTypes

    private fun normalizedText(vararg parts: String?): String =
        parts.filterNotNull().joinToString(" ").lowercase(Locale.US)

    private fun containsAny(text: String, terms: Set<String>): Boolean =
        terms.any { term -> text.contains(term) }

    private fun AddonManifest.safeResources() = runCatching { resources }.getOrNull().orEmpty()

    private fun AddonManifest.safeCatalogs() = runCatching { catalogs }.getOrNull().orEmpty()

    private fun AddonManifest.safeTypes() = runCatching { types }.getOrNull().orEmpty()

    private fun AddonResource.safeName() = runCatching { name }.getOrNull().orEmpty()

    private fun AddonResource.safeTypes() = runCatching { types }.getOrNull().orEmpty()

    fun isLiveStreamAddonId(addonId: String?): Boolean {
        val id = addonId?.trim()?.lowercase(Locale.US) ?: return false
        if (id in explicitVodStreamAddonIds) return false
        if (id.contains("cinemeta") || id.contains("tmdb") || id.contains("torrentio")) return false
        return id.contains("livetv") || id.contains("live_tv") ||
            id.contains("live-tv") || id.contains("live_stream") ||
            id.contains("livestream") || id.contains("live-stream") ||
            id.contains("tvchannels") || id.contains("tv-channels") ||
            id.contains("iptv") || id.contains("sports")
    }

    fun isLiveStreamAddon(addon: Addon?): Boolean {
        if (addon == null) return false
        val manifest = addon.manifest
        return if (manifest != null) {
            isLiveStreamManifest(manifest)
        } else {
            isLiveStreamAddonId(addon.id) || isLiveStreamAddonId(addon.name)
        }
    }

    fun isLiveStreamManifest(manifest: AddonManifest): Boolean {
        // Hybrid add-ons can expose both VOD and live catalogs. Their regular movie
        // and series streams must still be allowed into Continue Watching.
        if (manifestDeclaresVodStreams(manifest)) return false
        if (isSportsLiveTvManifest(manifest)) return true
        if (isLiveStreamAddonId(manifest.id) || isLiveStreamAddonId(manifest.name)) return true
        val types = manifest.safeResources().flatMap { it.safeTypes() } + manifest.safeTypes() + manifest.safeCatalogs().map { it.type }
        val normalizedTypes = types.map { it.trim().lowercase(Locale.US) }
        val liveTypes = setOf("tv", "channel", "channels", "live", "sports", "tvchannel", "live_tv", "livestream")
        return normalizedTypes.any { it in liveTypes } && !normalizedTypes.contains("movie") && !normalizedTypes.contains("series")
    }

    fun isLiveStreamOrSportsItem(
        mediaType: MediaType? = null,
        id: Int? = null,
        status: String? = null,
        streamAddonId: String? = null,
        title: String? = null,
        isLiveStream: Boolean = false,
        addons: List<Addon> = emptyList()
    ): Boolean {
        if (isLiveStream) return true
        if (status != null && (isSportsHomeStatus(status) || status.startsWith("iptv:") || status.startsWith("live:") || status.startsWith("channel:"))) {
            return true
        }
        if (id != null && id <= 0) {
            return true
        }
        if (!streamAddonId.isNullOrBlank()) {
            val addon = addons.firstOrNull { it.id.equals(streamAddonId, ignoreCase = true) }
            if (addon != null) {
                if (isLiveStreamAddon(addon)) return true
            } else if (isLiveStreamAddonId(streamAddonId)) {
                return true
            }
        }
        if (title != null) {
            val lowerTitle = title.lowercase(Locale.US)
            if (lowerTitle.startsWith("live:") || lowerTitle.startsWith("[live]")) return true
        }
        return false
    }
}
