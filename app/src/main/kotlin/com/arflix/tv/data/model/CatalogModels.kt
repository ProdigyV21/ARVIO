package com.arflix.tv.data.model

import androidx.annotation.StringRes
import java.io.Serializable

enum class CatalogSourceType {
    PREINSTALLED,
    TRAKT,
    MDBLIST,
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
    MDBLIST_PUBLIC
}

data class CollectionSourceConfig(
    val kind: CollectionSourceKind,
    val mediaType: String? = null,
    val addonId: String? = null,
    val addonCatalogType: String? = null,
    val addonCatalogId: String? = null,
    val tmdbGenreId: Int? = null,
    val tmdbPersonId: Int? = null,
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
    val mdblistSlug: String? = null
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
    val packName: String? = null
) : Serializable

val CatalogConfig.effectivePackId: String
    get() = packId ?: when (sourceType) {
        CatalogSourceType.PREINSTALLED -> "system"
        CatalogSourceType.ADDON -> "addon"
        CatalogSourceType.TRAKT -> "trakt"
        CatalogSourceType.MDBLIST -> "mdblist"
        CatalogSourceType.HOME_SERVER -> "home_server"
    }

val CatalogConfig.effectivePackName: String
    get() = packName ?: when (sourceType) {
        CatalogSourceType.PREINSTALLED -> "System Catalogs"
        CatalogSourceType.ADDON -> "Addon Catalogs"
        CatalogSourceType.TRAKT -> "Trakt Catalogs"
        CatalogSourceType.MDBLIST -> "MDBlist Catalogs"
        CatalogSourceType.HOME_SERVER -> "Home Server Catalogs"
    }

val CatalogConfig.isBulkDeletablePack: Boolean
    get() = packId != null &&
            packId != "system" &&
            packId != "addon" &&
            packId != "trakt" &&
            packId != "mdblist" &&
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
