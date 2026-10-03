package com.arflix.tv.data.model

import com.arflix.tv.data.api.StremioAddonCollectionResponse
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.util.Locale

/**
 * An addon listed in the Stremio community catalog, not necessarily installed.
 *
 * The catalog is Cinemeta's `addon_catalog` resource with the id `community`, the same list
 * the Stremio apps show under Addons > Community. Each entry carries its full manifest, so
 * the list can be shown without fetching every addon.
 */
data class CommunityAddon(
    val manifestId: String,
    val name: String,
    val version: String,
    val description: String,
    val logo: String?,
    val types: List<String>,
    val transportUrl: String,
    val configurable: Boolean,
    val configurationRequired: Boolean
) {
    /** The addon's settings page, or null when it has none. */
    val configureUrl: String?
        get() = AddonSetup.advertisedConfigureUrl(
            transportUrl,
            AddonBehaviorHints(configurable = configurable, configurationRequired = configurationRequired)
        )

    /** The installed setups of this addon. A configured setup has its own URL but the same manifest id. */
    fun installedSetups(installed: List<Addon>): List<Addon> =
        installed.filter { it.manifest?.id == manifestId }
}

object CommunityAddons {
    const val CATALOG_URL = "https://v3-cinemeta.strem.io/addon_catalog/all/community.json"

    /**
     * Keeps the entries ARVIO can install: a usable http(s) manifest URL and a manifest id.
     * Adult addons are left out. A manifest id listed twice keeps its first entry.
     */
    fun fromResponse(response: StremioAddonCollectionResponse): List<CommunityAddon> {
        val seen = HashSet<String>()
        return response.addons.orEmpty().mapNotNull { descriptor ->
            val manifest = descriptor.manifest ?: return@mapNotNull null
            val manifestId = manifest.id?.trim()?.takeIf { it.isNotEmpty() } ?: return@mapNotNull null
            val transportUrl = descriptor.transportUrl?.trim()
                ?.takeIf { it.toHttpUrlOrNull() != null }
                ?: return@mapNotNull null
            val hints = manifest.behaviorHints
            if (hints?.adult == true) return@mapNotNull null
            if (!seen.add(manifestId)) return@mapNotNull null
            CommunityAddon(
                manifestId = manifestId,
                name = manifest.name?.trim()?.takeIf { it.isNotEmpty() } ?: manifestId,
                version = manifest.version?.trim().orEmpty(),
                description = plainText(manifest.description),
                logo = manifest.logo?.trim()?.takeIf { it.startsWith("http", ignoreCase = true) },
                types = manifest.types.orEmpty()
                    .mapNotNull { it?.trim()?.lowercase(Locale.US)?.takeIf { type -> type.isNotEmpty() } }
                    .distinct(),
                transportUrl = transportUrl,
                configurable = hints?.configurable == true,
                configurationRequired = hints?.configurationRequired == true
            )
        }
    }

    /**
     * Some descriptions carry HTML (links, line breaks), which Stremio renders. Show the text only.
     */
    private fun plainText(html: String?): String =
        html.orEmpty()
            .replace(Regex("<[^>]*>"), " ")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&quot;", "\"")
            .replace("&#39;", "'")
            .replace("&nbsp;", " ")
            .replace("&amp;", "&")
            .replace(Regex("\\s+"), " ")
            .trim()

    /**
     * The most common content types, most common first, for the filter chips. A type only one
     * addon uses (several addons invent their own) is not worth a chip.
     */
    fun typeFilters(addons: List<CommunityAddon>, max: Int = 6, minCount: Int = 2): List<String> =
        addons.flatMap { it.types }
            .groupingBy { it }
            .eachCount()
            .entries
            .filter { it.value >= minCount }
            .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
            .take(max)
            .map { it.key }

    /** Entries matching [query] (name, description or type) and [type] (null = any), in catalog order. */
    fun filter(addons: List<CommunityAddon>, query: String, type: String?): List<CommunityAddon> {
        val terms = query.trim().lowercase(Locale.ROOT).split(Regex("\\s+")).filter { it.isNotEmpty() }
        return addons.filter { addon ->
            (type == null || type in addon.types) &&
                terms.all { term ->
                    addon.name.lowercase(Locale.ROOT).contains(term) ||
                        addon.description.lowercase(Locale.ROOT).contains(term) ||
                        addon.types.any { it.contains(term) }
                }
        }
    }
}
