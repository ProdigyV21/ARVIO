package com.arflix.tv.data.model

import androidx.compose.runtime.Immutable
import java.io.Serializable

/**
 * Media item - represents a movie or TV show
 * Matches webapp's MediaItem type
 */
@Immutable
data class MediaItem(
    val id: Int,
    val title: String,
    val subtitle: String = "",
    val overview: String = "",
    val year: String = "",
    val releaseDate: String? = null,
    val rating: String = "",
    // Age certification, already labelled for display ("FSK 16", "R", "US · R").
    // Null when TMDB has none for the content country or the US fallback.
    val contentRating: String? = null,
    val duration: String = "",
    val imdbRating: String = "",
    val tmdbRating: String = "",
    val mediaType: MediaType = MediaType.MOVIE,
    val image: String = "",
    val backdrop: String? = null,
    // Episode-specific landscape artwork for Continue Watching cards. Keeping
    // it separate prevents episode stills from replacing series hero artwork.
    val episodeStill: String? = null,
    val progress: Int = 0,
    val isWatched: Boolean = false,
    val traktId: Int? = null,
    val badge: String? = null,
    val genreIds: List<Int> = emptyList(),
    val originalLanguage: String? = null,
    // The native TMDB name, kept next to the localized [title] because some
    // providers list a title only under its original name. Null when TMDB has
    // none, and null on items restored from an older JSON cache - every reader
    // must treat it as "unknown", never as "same as the title".
    val originalTitle: String? = null,
    val primaryNetworkLogo: String? = null,
    val isOngoing: Boolean = false,
    val totalEpisodes: Int? = null,
    val watchedEpisodes: Int? = null,
    val nextEpisode: NextEpisode? = null,
    // Additional movie-specific fields
    val budget: Long? = null,
    val revenue: Long? = null,
    // TV show status
    val status: String? = null, // "Returning Series", "Ended", "Canceled"
    val collectionGroup: CollectionGroupKind? = null,
    val collectionTileShape: CollectionTileShape? = null,
    val collectionHideTitle: Boolean = false,
    // Character name (for person filmography / known for)
    val character: String = "",
    // Popularity score from TMDB (higher = more mainstream content)
    val popularity: Float = 0f,
    // Source-specific added timestamp, used for exact newest-first watchlist ordering.
    val addedAt: Long = 0L,
    // Explicit source order when a remote list already gives the correct order.
    val sourceOrder: Int = Int.MAX_VALUE,
    // Placeholder card - shows skeleton loading animation
    val isPlaceholder: Boolean = false,
    // Continue Watching: formatted time remaining (e.g., "23min left", "1hr 15min left")
    val timeRemainingLabel: String? = null,
    // Continue Watching: true only when progress represents current movie/episode playback.
    val showPlaybackProgress: Boolean = true,
    // Native home-server identity. The TMDB id remains `id` when available;
    // unmatched server items use a stable negative id and still render/open.
    val isHomeServer: Boolean = false,
    val homeServerItemId: String? = null,
    val homeServerSourceRef: String? = null,
    val homeServerProvider: String? = null,
    val homeServerImdbId: String? = null,
) : Serializable

enum class MediaType {
    @com.google.gson.annotations.SerializedName(value = "MOVIE", alternate = ["movie"])
    MOVIE,
    @com.google.gson.annotations.SerializedName(value = "TV", alternate = ["tv"])
    TV
}

/**
 * Next episode to watch
 */
@Immutable
data class NextEpisode(
    val id: Int,
    val seasonNumber: Int,
    val episodeNumber: Int,
    val name: String,
    val overview: String = ""
) : Serializable

/**
 * Episode details
 */
@Immutable
data class EpisodeIdentity(
    val displaySeason: Int,
    val displayEpisode: Int,
    val tmdbSeason: Int,
    val tmdbEpisode: Int,
    val kitsuId: Int? = null,
    val kitsuEpisode: Int? = null,
    val armEpisodeId: Int? = null
) : Serializable {
    val kitsuQuery: String?
        get() = kitsuId?.let { id -> kitsuEpisode?.let { episode -> "kitsu:$id:$episode" } }

    companion object {
        fun canonical(season: Int, episode: Int) = EpisodeIdentity(
            displaySeason = season,
            displayEpisode = episode,
            tmdbSeason = season,
            tmdbEpisode = episode
        )
    }
}

@Immutable
data class Episode(
    val id: Int,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val name: String,
    val overview: String = "",
    val stillPath: String? = null,
    val voteAverage: Float = 0f,
    val imdbRating: String = "",
    val runtime: Int = 0,
    val airDate: String = "",
    val isWatched: Boolean = false,
    /** Single source of truth for display, TMDB and anime-provider coordinates. */
    val identity: EpisodeIdentity = EpisodeIdentity.canonical(seasonNumber, episodeNumber)
) : Serializable {
    val tmdbSeasonNumber: Int get() = identity.tmdbSeason
    val tmdbEpisodeNumber: Int get() = identity.tmdbEpisode
    val kitsuId: Int? get() = identity.kitsuId
    val kitsuEpisodeNumber: Int? get() = identity.kitsuEpisode
}

/**
 * Cast member
 */
@Immutable
data class CastMember(
    val id: Int,
    val name: String,
    val character: String = "",
    val profilePath: String? = null
) : Serializable

/**
 * Community review shown on details pages.
 */
@Immutable
data class Review(
    val id: String,
    val author: String,
    val authorUsername: String = "",
    val authorAvatar: String? = null,
    val content: String,
    val rating: Float? = null,
    val createdAt: String = ""
) : Serializable

/**
 * Person details (for cast modal)
 */
@Immutable
data class PersonDetails(
    val id: Int,
    val name: String,
    val biography: String = "",
    val placeOfBirth: String? = null,
    val birthday: String? = null,
    val profilePath: String? = null,
    val knownFor: List<MediaItem> = emptyList()
) : Serializable

/**
 * Category/Row of media items
 */
@Immutable
data class Category(
    val id: String,
    val title: String,
    val items: List<MediaItem>
) : Serializable

fun Category.isPortrait(globalPosterMode: Boolean): Boolean {
    val isCollectionRow = id.startsWith("collection_row_")
    return if (isCollectionRow) {
        items.firstOrNull()?.collectionTileShape == CollectionTileShape.POSTER
    } else {
        globalPosterMode
    }
}

/**
 * Stream source from addons - enhanced with behavior hints.
 *
 * Marked @Immutable so Compose can skip recomposition on stable list renders
 * in the source picker. All fields are read-only primitives or nested
 * immutable types (StreamBehaviorHints is also @Immutable; subtitles/sources
 * lists are constructed once and treated as immutable by convention).
 */
@Immutable
data class StreamSource(
    val source: String,
    val addonName: String,
    val addonId: String = "",
    val quality: String,
    val size: String,
    val sizeBytes: Long? = null,
    val url: String? = null,
    val infoHash: String? = null,
    val fileIdx: Int? = null,
    val behaviorHints: StreamBehaviorHints? = null,
    val subtitles: List<Subtitle> = emptyList(),
    // Stremio "sources" are commonly tracker URLs. Keeping them helps P2P playback (TorrServer) work
    // across more addons.
    val sources: List<String> = emptyList(),
    val description: String? = null,
    // Raw Stremio "name" field (e.g. AIOStreams' "2160p (4k) [TB]\nRemux\nExtended"). Carries
    // clean, delimited quality tags that garbage filenames run together ("4Kremux") — used for
    // chip/label detection in the source menu.
    val rawLabel: String? = null,
    // Original Stremio title. Some addons use it for source/indexer details
    // that are intentionally separate from behaviorHints.filename.
    val addonTitle: String? = null,
    // Explicit source metadata only; discovery is deferred until a preview is requested.
    val preview: StreamPreviewMetadata? = null
) : Serializable

enum class StreamPreviewKind { JELLYFIN, PLEX, EMBY, WEBVTT, IMAGE_HLS, BIF }

/**
 * Identifies existing source-owned images, never a request to generate them. Server/item/version
 * and account scope must survive playback URL rotation. Generic tracks must be explicitly supplied;
 * no addon storyboard convention is inferred. Headers are secrets, not cache-key material.
 */
@Immutable
data class StreamPreviewMetadata(
    val kind: StreamPreviewKind,
    val serverId: String = "",
    val accountId: String = "",
    val itemId: String = "",
    val mediaSourceId: String = "",
    val mediaVersion: String = "",
    val mediaETag: String = "",
    val serverUrl: String? = null,
    val manifestUrl: String? = null,
    val userId: String = "",
    val headers: Map<String, String> = emptyMap(),
    val durationMs: Long = 0L,
    // Original media time = player time + offset. Resume alone does not change this offset.
    val timelineOffsetMs: Long = 0L
) : Serializable

/**
 * Stream behavior hints - all primitive / immutable fields, safe for
 * @Immutable so it composes stably inside a StreamSource list.
 */
@Immutable
data class StreamBehaviorHints(
    val notWebReady: Boolean = false,
    val cached: Boolean? = null,
    val bingeGroup: String? = null,
    val countryWhitelist: List<String>? = null,
    val proxyHeaders: ProxyHeaders? = null,
    val videoHash: String? = null,
    val videoSize: Long? = null,
    val filename: String? = null,
    val provider: String? = null,
    val providerCode: String? = null,
    val sourceLabel: String? = null,
    val indexer: String? = null,
    val indexerCode: String? = null,
    val language: String? = null
) : Serializable

data class ProxyHeaders(
    val request: Map<String, String>? = null,
    val response: Map<String, String>? = null
) : Serializable

/**
 * Subtitle track
 */
data class Subtitle(
    val id: String,
    val url: String,
    val lang: String,
    val label: String,
    val provider: String = "",
    val isEmbedded: Boolean = false,
    val groupIndex: Int? = null,
    val trackIndex: Int? = null,
    val isForced: Boolean = false,
    // True for image-based subtitle tracks (PGS/VOBSUB/DVB). These carry no text and
    // therefore cannot be used as an AI translation source.
    val isBitmap: Boolean = false,
) : Serializable

/**
 * Stremio Addon Manifest - full support for any Stremio addon
 * Based on: https://github.com/Stremio/stremio-addon-sdk/blob/master/docs/api/responses/manifest.md
 */
data class AddonManifest(
    val id: String,
    val name: String,
    val version: String,
    val description: String = "",
    val logo: String? = null,
    val background: String? = null,
    val types: List<String> = emptyList(),         // ["movie", "series", "channel", "tv"]
    val resources: List<AddonResource> = emptyList(),
    val catalogs: List<AddonCatalog> = emptyList(),
    val idPrefixes: List<String>? = null,          // ["tt"] for IMDB, ["kitsu:"] for Kitsu
    val behaviorHints: AddonBehaviorHints? = null
) : Serializable

/**
 * Addon resource descriptor
 */
data class AddonResource(
    val name: String,                              // "stream", "meta", "catalog", "subtitles"
    val types: List<String> = emptyList(),         // ["movie", "series"]
    val idPrefixes: List<String>? = null           // ID prefix filter
) : Serializable

/**
 * Addon catalog descriptor
 */
data class AddonCatalog(
    val type: String,                              // "movie", "series"
    val id: String,                                // catalog ID
    val name: String = "",
    val genres: List<String>? = null,
    val extra: List<AddonCatalogExtra>? = null
) : Serializable

data class AddonCatalogExtra(
    val name: String,                              // "search", "genre", "skip"
    val isRequired: Boolean = false,
    val options: List<String>? = null
) : Serializable

data class AddonBehaviorHints(
    val adult: Boolean = false,
    val p2p: Boolean = false,
    val configurable: Boolean = false,
    val configurationRequired: Boolean = false
) : Serializable

/**
 * Installed addon with manifest data
 */
data class Addon(
    val id: String,
    val name: String,
    val version: String,
    val description: String,
    val isInstalled: Boolean,
    val isEnabled: Boolean = true,
    val type: AddonType,
    val runtimeKind: RuntimeKind = RuntimeKind.STREMIO,
    val installSource: AddonInstallSource = AddonInstallSource.DIRECT_URL,
    val url: String? = null,
    val logo: String? = null,
    val manifest: AddonManifest? = null,           // Full manifest for advanced filtering
    val transportUrl: String? = null               // Base URL for API calls (without manifest.json)
)

enum class AddonType {
    OFFICIAL, COMMUNITY, SUBTITLE, METADATA, CUSTOM
}

enum class RuntimeKind {
    STREMIO, TELEGRAM
}

enum class AddonInstallSource {
    DIRECT_URL
}

/**
 * Stream fetch result with addon info for callback-based fetching
 */
data class AddonStreamResult(
    val streams: List<StreamSource>,
    val addonId: String,
    val addonName: String,
    val error: Exception? = null
) : Serializable

/**
 * Quality filter entry - device-scoped regex patterns to exclude quality tiers.
 * These filters apply to ALL profiles on this device (e.g., 1080p TV always excludes 4K)
 * regardless of which profile is logged in. This ensures device capabilities limit quality,
 * not user profiles.
 *
 * Example: 1080p TV with regex "4K|2160p" excludes 4K streams for all users
 */
@Immutable
data class QualityFilterConfig(
    val id: String = "", // UUID for unique identification
    val deviceName: String = "", // Display name (e.g., "Living Room TV", "Bedroom Fire TV")
    val regexPattern: String = "", // Regex pattern to EXCLUDE matching qualities (e.g., "4K|2160p")
    val enabled: Boolean = true, // Enable/disable filter without deleting
    val createdAt: Long = System.currentTimeMillis()
) : Serializable
