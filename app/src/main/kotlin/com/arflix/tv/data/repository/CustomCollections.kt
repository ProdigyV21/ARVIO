package com.arflix.tv.data.repository

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.CollectionTileShape
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.security.MessageDigest
import java.util.Locale

/** One imported collection = one Home rail with its folders as tiles. */
internal data class CustomCollectionRail(
    val key: String,
    val title: String,
    val packId: String,
    val packName: String,
    val entries: List<CollectionTemplateEntry>
)

/**
 * Stateless collections importer. CatalogRepository persists the resulting catalogs per profile.
 *
 * Accepts the Nuvio collections export format (a JSON array of collections, each
 * with `folders` and `sources`), a single collection object, or a wrapper
 * `{ "name": "...", "collections": [ ... ] }`. Each imported document becomes a
 * "pack" that can be removed as a whole from Settings → Catalogs.
 *
 * The resulting catalogs use the existing catalog cloud-sync format, not a second local store.
 */
internal object CustomCollections {
    const val PACK_ID_PREFIX = "usercol_"

    fun isCustom(config: CatalogConfig): Boolean =
        (config.packId?.startsWith(PACK_ID_PREFIX) == true ||
            PartnerCatalogs.isManaged(config.id) && config.packId?.contains("-$PACK_ID_PREFIX") == true) &&
            !config.collectionRailKey.isNullOrBlank() && !config.isPreinstalled

    /** True when [json] parses as a collections document (as opposed to a catalog pack manifest). */
    fun looksLikeCollections(json: String): Boolean =
        runCatching { collectionObjects(JsonParser.parseString(json)).isNotEmpty() }.getOrDefault(false)

    /**
     * Parses a document without changing profile settings or built-in defaults.
     */
    fun parse(json: String, url: String?): Result<List<CustomCollectionRail>> = runCatching {
        require(json.length <= 2_000_000) { "Collections document is too large" }
        val root = JsonParser.parseString(json)
        val collections = collectionObjects(root)
        require(collections.isNotEmpty() && collections.size <= 100) { "Invalid collections count" }
        val packId = PACK_ID_PREFIX + sha256Short(url?.trim() ?: json)
        val name = (root as? JsonObject)?.str("name")
            ?: collections.singleOrNull()?.str("title")
            ?: url?.let { runCatching { java.net.URI(it).host }.getOrNull() }
            ?: "Imported collections"
        val rails = parseRails(collections, packId, name).filter { it.entries.isNotEmpty() }
        require(rails.isNotEmpty()) { "No supported folders/sources found" }
        require(rails.sumOf { it.entries.size } <= 500) { "Too many folders" }
        require(rails.distinctBy { it.key }.size == rails.size) { "Duplicate collection IDs" }
        require(rails.flatMap { it.entries }.distinctBy { it.id }.size == rails.sumOf { it.entries.size }) { "Duplicate folder IDs" }
        rails
    }

    fun catalogs(rails: List<CustomCollectionRail>): List<CatalogConfig> = rails.flatMap { rail ->
        listOf(CatalogConfig(
            id = "collection_rail_${rail.key}", title = rail.title,
            sourceType = CatalogSourceType.PREINSTALLED, isPreinstalled = false,
            kind = CatalogKind.COLLECTION_RAIL, collectionGroup = CollectionGroupKind.NETWORK,
            collectionRailKey = rail.key, packId = rail.packId, packName = rail.packName
        )) + rail.entries.map { entry ->
            CatalogConfig(
                id = entry.id, title = entry.title, sourceType = CatalogSourceType.PREINSTALLED,
                isPreinstalled = false, kind = CatalogKind.COLLECTION, collectionGroup = entry.group,
                collectionRailKey = rail.key, packId = rail.packId, packName = rail.packName,
                collectionDescription = entry.description, collectionCoverImageUrl = entry.coverImageUrl,
                collectionFocusGifUrl = entry.focusGifUrl, collectionHeroImageUrl = entry.heroImageUrl,
                collectionHeroVideoUrl = entry.heroVideoUrl, collectionClearLogoUrl = entry.clearLogoUrl,
                collectionTileShape = entry.tileShape, collectionHideTitle = entry.hideTitle,
                collectionSources = entry.sources
            )
        }
    }

    fun merge(current: List<CatalogConfig>, imported: List<CatalogConfig>): List<CatalogConfig> {
        val packId = imported.firstOrNull()?.packId ?: return current
        val pending = imported.associateByTo(LinkedHashMap()) { it.id }
        val kept = current.mapNotNull { existing ->
            if (existing.packId != packId) existing
            else pending.remove(existing.id)?.copy(title = existing.title)
        }
        return kept + pending.values
    }

    // ── Parsing ─────────────────────────────────────────────────────────

    private fun collectionObjects(root: JsonElement): List<JsonObject> {
        val array: JsonArray? = when {
            root.isJsonArray -> root.asJsonArray
            root.isJsonObject && root.asJsonObject.get("collections")?.isJsonArray == true ->
                root.asJsonObject.getAsJsonArray("collections")
            root.isJsonObject && root.asJsonObject.get("folders")?.isJsonArray == true ->
                JsonArray().apply { add(root) }
            else -> null
        }
        return array?.mapNotNull { el ->
            (el as? JsonObject)?.takeIf { it.get("folders")?.isJsonArray == true && it.str("title") != null }
        }.orEmpty()
    }

    private fun parseRails(collections: List<JsonObject>, packId: String, packName: String): List<CustomCollectionRail> {
        return collections.mapIndexed { index, collection ->
            val collectionId = collection.str("id") ?: "c$index"
            val railKey = "custom_${packId}_${slugify(collectionId)}"
            val entries = (collection.arr("folders") ?: JsonArray()).mapIndexedNotNull { folderIndex, el ->
                val folder = el as? JsonObject ?: return@mapIndexedNotNull null
                parseFolder(folder, folderIndex, railKey, packId, packName)
            }
            CustomCollectionRail(
                key = railKey,
                title = collection.str("title") ?: packName,
                packId = packId,
                packName = packName,
                entries = entries
            )
        }
    }

    private fun parseFolder(
        folder: JsonObject,
        index: Int,
        railKey: String,
        packId: String,
        packName: String
    ): CollectionTemplateEntry? {
        val title = folder.str("title") ?: return null
        val sourcesJson = folder.arr("sources")?.takeIf { it.size() > 0 }
            ?: folder.arr("catalogSources")
            ?: JsonArray()
        val sources = sourcesJson.flatMap { el -> (el as? JsonObject)?.let(::parseSource).orEmpty() }
        if (sources.isEmpty()) return null
        val folderId = folder.str("id") ?: "f$index"
        val cover = folder.str("coverImageUrl")
        val focusGif = folder.str("focusGifUrl")
            ?.takeIf { folder.get("focusGifEnabled")?.takeIf { it.isJsonPrimitive }?.asBoolean != false }
        return CollectionTemplateEntry(
            id = "collection_${railKey}_${slugify(folderId)}",
            title = title,
            group = CollectionGroupKind.NETWORK,
            coverImageUrl = cover ?: focusGif.orEmpty(),
            tileShape = if (folder.str("tileShape").equals("POSTER", ignoreCase = true)) {
                CollectionTileShape.POSTER
            } else {
                CollectionTileShape.LANDSCAPE
            },
            // Without artwork the tile would be blank, so always show the title then.
            hideTitle = (folder.get("hideTitle")?.takeIf { it.isJsonPrimitive }?.asBoolean ?: false) &&
                (cover != null || focusGif != null),
            heroVideoUrl = folder.str("heroVideoUrl"),
            sources = sources,
            listMetadata = emptyList(),
            railKey = railKey,
            description = folder.str("description"),
            heroImageUrl = folder.str("heroBackdropUrl"),
            focusGifUrl = focusGif,
            clearLogoUrl = folder.str("titleLogoUrl"),
            packId = packId,
            packName = packName
        )
    }

    private fun parseSource(source: JsonObject): List<CollectionSourceConfig> {
        return when (source.str("provider")?.lowercase(Locale.US) ?: "addon") {
            "addon" -> {
                val type = source.str("type") ?: return emptyList()
                val catalogId = source.str("catalogId") ?: return emptyList()
                listOf(
                    CollectionSourceConfig(
                        kind = CollectionSourceKind.ADDON_CATALOG,
                        mediaType = type,
                        addonId = source.str("addonId"),
                        addonCatalogType = type,
                        addonCatalogId = catalogId,
                        addonGenre = source.str("genre")
                    )
                )
            }
            "trakt" -> {
                val listId = source.get("traktListId")?.takeIf { it.isJsonPrimitive }?.asString?.trim()
                    ?.takeIf { it.isNotEmpty() && it != "0" } ?: return emptyList()
                listOf(CollectionSourceConfig(kind = CollectionSourceKind.TRAKT_LIST, traktListId = listId))
            }
            "tmdb" -> parseTmdbSource(source)
            "mdblist" -> {
                val slug = source.str("slug") ?: source.str("mdblistSlug") ?: return emptyList()
                listOf(CollectionSourceConfig(kind = CollectionSourceKind.MDBLIST_PUBLIC, mdblistSlug = slug))
            }
            else -> emptyList()
        }
    }

    private fun parseTmdbSource(source: JsonObject): List<CollectionSourceConfig> {
        val sourceType = source.str("tmdbSourceType")?.uppercase(Locale.US) ?: return emptyList()
        val tmdbId = source.get("tmdbId")?.takeUnless { it.isJsonNull }?.let {
            require(it.isJsonPrimitive) { "Invalid TMDB ID" }
            val id = it.asString.toIntOrNull()
            require(id != null && id > 0) { "Invalid TMDB ID" }
            id
        }
        val isTv = sourceType == "NETWORK" || source.str("mediaType")?.uppercase(Locale.US) in setOf("TV", "SERIES", "SHOW")
        val media = if (isTv) "tv" else "movie"
        val sortBy = source.str("sortBy")?.takeUnless { it == "original" }
        return when (sourceType) {
            "PERSON", "DIRECTOR" -> {
                require((source.get("filters") as? JsonObject)?.entrySet().orEmpty().none { !it.value.isJsonNull && it.value.asString.isNotBlank() }) {
                    "Additional filters on person/director collections are not supported"
                }
                tmdbId?.let { listOf(CollectionSourceConfig(kind = CollectionSourceKind.TMDB_PERSON,
                    tmdbPersonId = it, tmdbCreditRole = if (sourceType == "DIRECTOR") "Director" else "Cast",
                    mediaType = media, sortBy = sortBy)) }.orEmpty()
            }
            "COLLECTION" -> tmdbId?.let {
                listOf(CollectionSourceConfig(kind = CollectionSourceKind.TMDB_COLLECTION, tmdbCollectionId = it))
            }.orEmpty()
            "LIST" -> tmdbId?.let {
                listOf(CollectionSourceConfig(kind = CollectionSourceKind.TMDB_LIST, tmdbListId = it))
            }.orEmpty()
            else -> {
                val params = LinkedHashMap<String, String>()
                when (sourceType) {
                    "COMPANY" -> params["with_companies"] = tmdbId?.toString() ?: return emptyList()
                    "NETWORK" -> params["with_networks"] = tmdbId?.toString() ?: return emptyList()
                    "PERSON" -> params[if (isTv) "with_people" else "with_cast"] = tmdbId?.toString() ?: return emptyList()
                    "DIRECTOR" -> params["with_crew"] = tmdbId?.toString() ?: return emptyList()
                    "DISCOVER" -> Unit
                    else -> return emptyList()
                }
                (source.get("filters") as? JsonObject)?.let { putDiscoverFilters(it, isTv, params) }
                listOf(
                    CollectionSourceConfig(
                        kind = CollectionSourceKind.TMDB_DISCOVER,
                        mediaType = media,
                        sortBy = sortBy,
                        discoverParams = params
                    )
                )
            }
        }
    }

    private fun putDiscoverFilters(filters: JsonObject, isTv: Boolean, params: MutableMap<String, String>) {
        fun put(key: String, value: String?) {
            if (!value.isNullOrBlank()) params[key] = value
        }
        put("with_genres", filters.str("withGenres"))
        put("without_genres", filters.str("withoutGenres"))
        put(if (isTv) "first_air_date.gte" else "primary_release_date.gte", filters.str("releaseDateGte"))
        put(if (isTv) "first_air_date.lte" else "primary_release_date.lte", filters.str("releaseDateLte"))
        put("vote_average.gte", filters.str("voteAverageGte"))
        put("vote_average.lte", filters.str("voteAverageLte"))
        put("vote_count.gte", filters.str("voteCountGte"))
        put("with_original_language", filters.str("withOriginalLanguage"))
        put("with_origin_country", filters.str("withOriginCountry"))
        put("with_keywords", filters.str("withKeywords"))
        put("without_keywords", filters.str("withoutKeywords"))
        put("with_companies", filters.str("withCompanies"))
        put("without_companies", filters.str("withoutCompanies"))
        if (isTv) put("with_networks", filters.str("withNetworks"))
        put(if (isTv) "first_air_date_year" else "primary_release_year", filters.str("year"))
        put("watch_region", filters.str("watchRegion"))
        put("with_watch_providers", filters.str("withWatchProviders"))
        put("without_watch_providers", filters.str("withoutWatchProviders"))
    }

    private fun JsonObject.arr(key: String): JsonArray? = get(key) as? JsonArray

    private fun JsonObject.str(key: String): String? {
        val el = get(key) ?: return null
        if (!el.isJsonPrimitive) return null
        return el.asString.trim().takeIf { it.isNotEmpty() }
    }

    private fun slugify(value: String): String =
        value.lowercase(Locale.US).replace(Regex("[^a-z0-9]+"), "_").trim('_').ifEmpty { "x" }

    private fun sha256Short(value: String): String =
        MessageDigest.getInstance("SHA-256").digest(value.toByteArray())
            .take(6).joinToString("") { "%02x".format(it) }
}
