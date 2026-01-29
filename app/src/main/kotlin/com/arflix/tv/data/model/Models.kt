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
    val duration: String = "",
    val imdbRating: String = "",
    val tmdbRating: String = "",
    val mediaType: MediaType = MediaType.MOVIE,
    val image: String = "",
    val backdrop: String? = null,
    val progress: Int = 0,
    val isWatched: Boolean = false,
    val traktId: Int? = null,
    val badge: String? = null,
    val genreIds: List<Int> = emptyList(),
    val originalLanguage: String? = null,
    val isOngoing: Boolean = false,
    val totalEpisodes: Int? = null,
    val watchedEpisodes: Int? = null,
    val nextEpisode: NextEpisode? = null,
    // Additional movie-specific fields
    val budget: Long? = null,
    val revenue: Long? = null,
    // TV show status
    val status: String? = null, // "Returning Series", "Ended", "Canceled"
    // Character name (for person filmography / known for)
    val character: String = "",
    // Popularity score from TMDB (higher = more mainstream content)
    val popularity: Float = 0f
) : Serializable

enum class MediaType {
    MOVIE, TV
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
data class Episode(
    val id: Int,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val name: String,
    val overview: String = "",
    val stillPath: String? = null,
    val voteAverage: Float = 0f,
    val runtime: Int = 0,
    val airDate: String = "",
    val isWatched: Boolean = false
) : Serializable

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
 * Review from TMDB
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

/**
 * Stream source from addons - enhanced with behavior hints like NuvioStreaming
 */
data class StreamSource(
    val source: String,
    val addonName: String,
    val addonId: String = "",
    val quality: String,
    val size: String,
    val sizeBytes: Long? = null,
    val seeders: Int? = null,
    val url: String? = null,
    val infoHash: String? = null,
    val fileIdx: Int? = null,
    val isDebrid: Boolean = false,
    val isCached: Boolean = false,
    val behaviorHints: StreamBehaviorHints? = null,
    val subtitles: List<Subtitle> = emptyList() // Embedded subtitles from stream
) : Serializable

/**
 * Stream behavior hints - matches Stremio protocol
 */
data class StreamBehaviorHints(
    val notWebReady: Boolean = false,
    val cached: Boolean? = null,        // From debrid services
    val bingeGroup: String? = null,
    val countryWhitelist: List<String>? = null,
    val proxyHeaders: ProxyHeaders? = null,
    val videoHash: String? = null,
    val videoSize: Long? = null,
    val filename: String? = null
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
    val isEmbedded: Boolean = false,
    val groupIndex: Int? = null,
    val trackIndex: Int? = null
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
    val url: String? = null,
    val logo: String? = null,
    val manifest: AddonManifest? = null,           // Full manifest for advanced filtering
    val transportUrl: String? = null               // Base URL for API calls (without manifest.json)
)

enum class AddonType {
    OFFICIAL, COMMUNITY, SUBTITLE, METADATA, CUSTOM
}

/**
 * Stream fetch result with addon info - for callback-based fetching like NuvioStreaming
 */
data class AddonStreamResult(
    val streams: List<StreamSource>,
    val addonId: String,
    val addonName: String,
    val error: Exception? = null
) : Serializable


