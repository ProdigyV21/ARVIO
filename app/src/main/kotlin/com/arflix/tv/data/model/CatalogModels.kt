package com.arflix.tv.data.model

import androidx.annotation.StringRes
import java.io.Serializable

enum class CatalogSourceType {
    PREINSTALLED,
    TRAKT,
    SIMKL,
    MDBLIST,
    // A TMDB page added by its URL: list, collection, company, network,
    // person, keyword or genre.
    TMDB,
    ADDON,
    HOME_SERVER
}

enum class CatalogKind {
    STANDARD,
    COLLECTION,
    COLLECTION_RAIL
}

enum class CollectionGroupKind {
    FEATURED,
    SERVICE,
    GENRE,
    DECADE,
    FRANCHISE,
    NETWORK
}

enum class CollectionTileShape {
    LANDSCAPE,
    POSTER
}

enum class CollectionSourceKind {
    ADDON_CATALOG,
    TMDB_GENRE,
    TMDB_PERSON,
    // Resolves a franchise via TMDB's /collection/{id} endpoint (returns the
    // explicit list of films in a collection like Harry Potter = 1241).
    TMDB_COLLECTION,
    // Resolves a franchise / studio via TMDB keyword discovery (e.g. Pixar,
    // DreamWorks, broader universes that aren't modelled as a TMDB collection).
    TMDB_KEYWORD,
    // Resolves trending/popular content gated by a TMDB watch provider id —
    // used for streaming-service collections (Apple TV+, Paramount+, Hulu,
    // Peacock) when no addon catalog is available.
    TMDB_WATCH_PROVIDER,
    // Explicit list of TMDB (MediaType, id) pairs in canonical order. Used for
    // franchises where neither release-date sort nor a single TMDB collection
    // captures the correct ordering — e.g. Star Wars (timeline order spans
    // movies and TV shows) or MCU release order blending Disney+ series.
    CURATED_IDS,
    // Public mdblist list JSON (`mdblist.com/lists/{user}/{slug}/json`). Used
    // to complete curated franchise rows with community-maintained extras
    // (upcoming titles, spin-offs) without requiring the user to have any
    // Stremio addon installed — the endpoint is anonymous and cache-friendly.
    MDBLIST_PUBLIC,
    // Generic TMDB /discover query built from `discoverParams` (raw TMDB query
    // keys such as with_companies / with_networks / primary_release_date.gte).
    // Lets imported collections (e.g. Nuvio exports) describe any discover
    // filter without a dedicated source kind per filter.
    TMDB_DISCOVER,
    // Public TMDB v3 list (`/list/{id}`), mixed movies and series in list order.
    TMDB_LIST,
    // Public Trakt list by numeric id or slug (`trakt.tv/lists/{id}`).
    TRAKT_LIST
}

data class CollectionSourceConfig(
    val kind: CollectionSourceKind,
    val mediaType: String? = null,
    val addonId: String? = null,
    val addonCatalogType: String? = null,
    val addonCatalogId: String? = null,
    val addonGenre: String? = null,
    val tmdbGenreId: Int? = null,
    val tmdbPersonId: Int? = null,
    val tmdbCreditRole: String? = null,
    val tmdbCollectionId: Int? = null,
    val tmdbKeywordId: Int? = null,
    val tmdbWatchProviderId: Int? = null,
    val watchRegion: String? = null,
    val sortBy: String? = null,
    // Encoded as e.g. "movie:11" / "tv:82856". Preserves order — the list is
    // rendered in exactly this sequence in collection detail screens.
    val curatedRefs: List<String>? = null,
    // Path component after /lists/, e.g. "jxduffy/star-wars-chronological-order".
    // Resolved against the public mdblist JSON endpoint at runtime.
    val mdblistSlug: String? = null,
    val discoverParams: Map<String, String>? = null,
    val tmdbListId: Int? = null,
    val traktListId: String? = null
) : Serializable

data class CatalogConfig(
    val id: String,
    val title: String,
    val sourceType: CatalogSourceType,
    val sourceUrl: String? = null,
    val sourceRef: String? = null,
    val isPreinstalled: Boolean = false,
    val addonId: String? = null,
    val addonCatalogType: String? = null,
    val addonCatalogId: String? = null,
    val addonName: String? = null,
    val kind: CatalogKind = CatalogKind.STANDARD,
    val collectionGroup: CollectionGroupKind? = null,
    val collectionDescription: String? = null,
    val collectionCoverImageUrl: String? = null,
    val collectionFocusGifUrl: String? = null,
    val collectionHeroImageUrl: String? = null,
    val collectionHeroGifUrl: String? = null,
    val collectionHeroVideoUrl: String? = null,
    val collectionClearLogoUrl: String? = null,
    val collectionTileShape: CollectionTileShape = CollectionTileShape.LANDSCAPE,
    val collectionHideTitle: Boolean = false,
    val collectionSources: List<CollectionSourceConfig> = emptyList(),
    val requiredAddonUrls: List<String> = emptyList(),
    val packId: String? = null,
    val packName: String? = null,
    // Rail grouping key for user-imported collections. Built-in collections leave
    // it null and group by `collectionGroup`; imported ones get one rail per
    // imported collection, so they can't be squeezed into the fixed enum.
    val collectionRailKey: String? = null
) : Serializable

/** Key that ties collection tiles to their rail: the imported rail key, else the built-in group. */
val CatalogConfig.collectionRailKeyOrGroup: String?
    get() = collectionRailKey ?: collectionGroup?.name

val CatalogConfig.effectivePackId: String
    get() = packId ?: when (sourceType) {
        CatalogSourceType.PREINSTALLED -> "system"
        CatalogSourceType.ADDON -> "addon"
        CatalogSourceType.TRAKT -> "trakt"
        CatalogSourceType.SIMKL -> "simkl"
        CatalogSourceType.MDBLIST -> "mdblist"
        CatalogSourceType.TMDB -> "tmdb"
        CatalogSourceType.HOME_SERVER -> "home_server"
    }

val CatalogConfig.effectivePackName: String
    get() = packName ?: when (sourceType) {
        CatalogSourceType.PREINSTALLED -> "System Catalogs"
        CatalogSourceType.ADDON -> "Addon Catalogs"
        CatalogSourceType.TRAKT -> "Trakt Catalogs"
        CatalogSourceType.SIMKL -> "SIMKL Catalogs"
        CatalogSourceType.MDBLIST -> "MDBlist Catalogs"
        CatalogSourceType.TMDB -> "TMDB Catalogs"
        CatalogSourceType.HOME_SERVER -> "Home Server Catalogs"
    }

val CatalogConfig.isBulkDeletablePack: Boolean
    get() = packId != null &&
            packId != "system" &&
            packId != "addon" &&
            packId != "trakt" &&
            packId != "simkl" &&
            packId != "mdblist" &&
            packId != "tmdb" &&
            packId != "home_server" &&
            packId != "individual"

data class CatalogDiscoveryResult(
    val id: String,
    val title: String,
    val description: String?,
    val sourceType: CatalogSourceType,
    val sourceUrl: String,
    val creatorName: String?,
    val creatorHandle: String?,
    val updatedAt: String?,
    val itemCount: Int?,
    val likes: Int?,
    val previewPosterUrls: List<String> = emptyList()
)

data class CatalogValidationResult(
    val isValid: Boolean,
    val normalizedUrl: String? = null,
    val sourceType: CatalogSourceType? = null,
    /** Resource of the validation error; resolved where it is displayed. */
    @param:StringRes val errorRes: Int? = null
)

data class CatalogPackManifest(
    val id: String?,
    val name: String?,
    val author: String? = null,
    val version: String? = null,
    val description: String? = null,
    val catalogs: List<CatalogPackItem>?
) : Serializable

data class CatalogPackItem(
    val name: String?,
    val url: String?
) : Serializable
