package com.arflix.tv.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Stremio addon API interface for stream resolution
 * Enhanced to support any Stremio addon like NuvioStreaming
 */
interface StreamApi {

    // ========== Stremio Addon Manifest ==========

    /**
     * Fetch addon manifest from any Stremio addon URL
     * URL format: https://addon.example.com/manifest.json
     */
    @GET
    suspend fun getAddonManifest(
        @Url url: String
    ): StremioManifestResponse

    // ========== Generic Stremio Addon ==========

    @GET
    suspend fun getAddonStreams(
        @Url url: String
    ): StremioStreamResponse

    // ========== OpenSubtitles ==========

    @GET
    suspend fun getSubtitles(
        @Url url: String
    ): StremioSubtitleResponse

    // ========== Kitsu API (for anime ID lookup) ==========

    @GET
    suspend fun searchKitsuAnime(
        @Url url: String
    ): KitsuSearchResponse

    /**
     * Get Kitsu mappings by external site and ID (e.g., TVDB -> Kitsu)
     * URL: https://kitsu.io/api/edge/mappings?filter[externalSite]=thetvdb/series&filter[externalId]=ID&include=item
     */
    @GET
    suspend fun getKitsuMappings(
        @Url url: String
    ): KitsuMappingResponse

    /**
     * Get Kitsu anime detail by ID
     * URL: https://kitsu.io/api/edge/anime/KITSU_ID
     */
    @GET
    suspend fun getKitsuAnimeDetail(
        @Url url: String
    ): KitsuAnimeDetailResponse

    /**
     * Get Kitsu anime media-relationships (sequel, prequel, etc.)
     * URL: https://kitsu.io/api/edge/anime/KITSU_ID/relationships/media-relationships
     *   or: https://kitsu.io/api/edge/anime/KITSU_ID?include=mediaRelationships.destination
     */
    @GET
    suspend fun getKitsuMediaRelationships(
        @Url url: String
    ): KitsuMediaRelationshipsResponse

    // ========== ARM API (Anime ID Resolution) ==========

    /**
     * Resolve TMDB ID to anime IDs (Kitsu, MAL, AniList) via ARM API
     * URL: https://arm.haglund.dev/api/v2/themoviedb?id=TMDB_ID
     * Returns list of matching entries (multiple for multi-season anime)
     */
    @GET
    suspend fun getArmMappingByTmdb(
        @Url url: String
    ): List<ArmMappingEntry>

    /**
     * Resolve IMDB ID to anime IDs via ARM API
     * URL: https://arm.haglund.dev/api/v2/imdb?id=IMDB_ID
     */
    @GET
    suspend fun getArmMappingByImdb(
        @Url url: String
    ): List<ArmMappingEntry>
}

// ========== Stremio Manifest Models ==========

/**
 * Stremio addon manifest response - matches Stremio protocol
 * https://github.com/Stremio/stremio-addon-sdk/blob/master/docs/api/responses/manifest.md
 */
data class StremioManifestResponse(
    val id: String,
    val name: String,
    val version: String,
    val description: String? = null,
    val logo: String? = null,
    val background: String? = null,
    val types: List<String>? = null,
    val resources: List<Any>? = null,  // Can be String or StremioResourceDescriptor
    val catalogs: List<StremioCatalog>? = null,
    val idPrefixes: List<String>? = null,
    val behaviorHints: StremioAddonBehaviorHints? = null
)

data class StremioResourceDescriptor(
    val name: String,
    val types: List<String>? = null,
    val idPrefixes: List<String>? = null
)

data class StremioCatalog(
    val type: String,
    val id: String,
    val name: String? = null,
    val genres: List<String>? = null,
    val extra: List<StremioCatalogExtra>? = null
)

data class StremioCatalogExtra(
    val name: String,
    val isRequired: Boolean? = null,
    val options: List<String>? = null
)

data class StremioAddonBehaviorHints(
    val adult: Boolean? = null,
    val p2p: Boolean? = null,
    val configurable: Boolean? = null,
    val configurationRequired: Boolean? = null
)

// ========== Stremio Stream Models ==========

data class StremioStreamResponse(
    val streams: List<StremioStream>? = null
)

data class StremioStream(
    val name: String? = null,
    val title: String? = null,
    val description: String? = null,        // Some addons put size/quality info here
    val url: String? = null,
    val infoHash: String? = null,
    val fileIdx: Int? = null,
    val ytId: String? = null,              // YouTube video ID
    val externalUrl: String? = null,        // External URL to open
    val behaviorHints: StreamBehaviorHints? = null,
    val sources: List<String>? = null,
    val subtitles: List<StremioSubtitle>? = null
) {
    // Parse quality from title or name
    fun getQuality(): String {
        // Check all text fields for quality indicators
        val textsToCheck = listOfNotNull(name, title, description)
        val combinedText = textsToCheck.joinToString(" ")

        // Look for specific quality patterns
        return when {
            combinedText.contains("2160p", ignoreCase = true) || combinedText.contains("4K", ignoreCase = true) -> "4K"
            combinedText.contains("1080p", ignoreCase = true) -> "1080p"
            combinedText.contains("720p", ignoreCase = true) -> "720p"
            combinedText.contains("480p", ignoreCase = true) -> "480p"
            else -> {
                // Fallback: try Torrentio format (second line of title)
                val titleParts = (title ?: name ?: "").split("\n")
                titleParts.getOrNull(1)?.trim()?.takeIf { it.isNotBlank() } ?: "Unknown"
            }
        }
    }

    fun getSourceName(): String {
        val titleParts = (title ?: name ?: "").split("\n")
        return titleParts.getOrNull(0)?.trim() ?: "Unknown"
    }

    fun getTorrentName(): String {
        // Priority 1: behaviorHints.filename (most accurate)
        behaviorHints?.filename?.takeIf { it.isNotBlank() }?.let { return it }

        // Priority 2: Check description for filename (Plexio format)
        // Plexio puts filename in first line of description like "Movie Name (Year) - S01E01 [Quality]-Group.mkv"
        description?.let { desc ->
            val firstLine = desc.split("\n").firstOrNull()?.trim() ?: ""
            // Check if it looks like a filename (contains extension or quality tags)
            if (firstLine.isNotBlank() &&
                (firstLine.contains(".mkv", ignoreCase = true) ||
                 firstLine.contains(".mp4", ignoreCase = true) ||
                 firstLine.contains(".avi", ignoreCase = true) ||
                 firstLine.matches(Regex(".*\\[.*\\].*")))) {  // Contains [quality] tags
                return firstLine
            }
        }

        val fullTitle = title ?: name ?: ""
        val titleParts = fullTitle.split("\n")

        // Torrentio format typically has torrent name in later lines
        // Try to find the line that looks like a torrent name (contains dots, no emojis)
        for (i in titleParts.indices.reversed()) {
            val part = titleParts[i].trim()
            // A torrent name typically contains dots and no emojis
            if (part.isNotBlank() &&
                part.contains(".") &&
                !part.contains("👤") &&
                !part.contains("💾") &&
                !part.contains("⚙️") &&
                !part.contains("🔗")) {
                return part
            }
        }

        // Fallback: try 3rd line (index 2), then 2nd line, then full title
        return titleParts.getOrNull(2)?.takeIf { it.isNotBlank() }?.trim()
            ?: titleParts.getOrNull(1)?.takeIf { it.isNotBlank() && !it.contains("👤") }?.trim()
            ?: fullTitle.trim().ifBlank { "Unknown" }
    }

    fun getSize(): String {
        // Priority 1: behaviorHints.videoSize (in bytes)
        behaviorHints?.videoSize?.let { bytes ->
            if (bytes > 0) {
                return formatBytes(bytes)
            }
        }

        // Check all text fields: title, name, description
        val textsToCheck = listOfNotNull(title, name, description)

        for (text in textsToCheck) {
            // Priority 2: Extract size with emoji (Torrentio format: "💾 15.2 GB")
            val emojiRegex = """💾\s*([\d.]+\s*[GMKT]B)""".toRegex(RegexOption.IGNORE_CASE)
            emojiRegex.find(text)?.groupValues?.getOrNull(1)?.let { return it }

            // Priority 3: Extract size without emoji (AIOStreams/other formats: "15.2 GB", "15.2GB")
            val plainRegex = """(\d+\.?\d*)\s*(GB|MB|TB|KB)""".toRegex(RegexOption.IGNORE_CASE)
            plainRegex.find(text)?.let { match ->
                val value = match.groupValues[1]
                val unit = match.groupValues[2].uppercase()
                return "$value $unit"
            }
        }

        return ""
    }

    private fun formatBytes(bytes: Long): String {
        return when {
            bytes >= 1_000_000_000_000 -> String.format("%.2f TB", bytes / 1_000_000_000_000.0)
            bytes >= 1_000_000_000 -> String.format("%.2f GB", bytes / 1_000_000_000.0)
            bytes >= 1_000_000 -> String.format("%.1f MB", bytes / 1_000_000.0)
            bytes >= 1_000 -> String.format("%.0f KB", bytes / 1_000.0)
            else -> "$bytes B"
        }
    }

    fun getSeeders(): Int? {
        // Extract seeders from title if present (e.g., "👤 125")
        val seederRegex = """👤\s*(\d+)""".toRegex()
        val match = seederRegex.find(title ?: "")
        return match?.groupValues?.getOrNull(1)?.toIntOrNull()
    }

    /**
     * Check if this stream has a playable link (like NuvioStreaming processStreams)
     */
    fun hasPlayableLink(): Boolean {
        return url != null || infoHash != null || ytId != null || externalUrl != null
    }

    /**
     * Get the best available stream URL
     */
    fun getStreamUrl(): String? {
        return url ?: externalUrl
    }

    /**
     * Check if this is a direct streaming URL (no debrid needed)
     */
    fun isDirectStreamingUrl(): Boolean {
        val streamUrl = getStreamUrl() ?: return false
        val directPatterns = listOf(
            ".mp4", ".mkv", ".webm", ".avi", ".mov",
            ".m3u8", ".mpd",
            "googlevideo.com", "youtube.com", "youtu.be",
            "cloudflare", "akamaized", "fastly"
        )
        return directPatterns.any { streamUrl.contains(it, ignoreCase = true) }
    }
}

/**
 * Stream behavior hints - enhanced to match full Stremio protocol
 */
data class StreamBehaviorHints(
    val notWebReady: Boolean? = null,       // Stream needs transcoding
    val cached: Boolean? = null,             // Already cached in debrid
    val bingeGroup: String? = null,          // Group for binge watching
    val countryWhitelist: List<String>? = null,
    val proxyHeaders: StremioProxyHeaders? = null,
    val videoHash: String? = null,
    val videoSize: Long? = null,
    val filename: String? = null
)

data class StremioProxyHeaders(
    val request: Map<String, String>? = null,
    val response: Map<String, String>? = null
)

data class StremioSubtitle(
    val id: String? = null,
    val url: String? = null,
    val lang: String? = null,
    val label: String? = null
)

data class StremioSubtitleResponse(
    val subtitles: List<StremioSubtitle>? = null
)

// ========== Kitsu API Models ==========

data class KitsuSearchResponse(
    val data: List<KitsuAnime>?
)

data class KitsuAnime(
    val id: String,
    val type: String?,
    val attributes: KitsuAnimeAttributes?
)

data class KitsuAnimeAttributes(
    val canonicalTitle: String?,
    val titles: Map<String, String>?,
    val slug: String?,
    val episodeCount: Int?,
    val status: String?
)

// ========== Kitsu Mapping API Models ==========

data class KitsuMappingResponse(
    val data: List<KitsuMapping>?,
    val included: List<KitsuIncludedItem>?
)

data class KitsuMapping(
    val id: String,
    val type: String?,
    val attributes: KitsuMappingAttributes?,
    val relationships: KitsuMappingRelationships?
)

data class KitsuMappingAttributes(
    val externalSite: String?,
    val externalId: String?
)

data class KitsuMappingRelationships(
    val item: KitsuRelationshipData?
)

data class KitsuRelationshipData(
    val data: KitsuRelationshipItem?
)

data class KitsuRelationshipItem(
    val id: String?,
    val type: String?
)

data class KitsuIncludedItem(
    val id: String,
    val type: String?,
    val attributes: KitsuAnimeAttributes?
)

// ========== Kitsu Anime Detail API Models ==========

data class KitsuAnimeDetailResponse(
    val data: KitsuAnimeDetail?
)

data class KitsuAnimeDetail(
    val id: String,
    val type: String?,
    val attributes: KitsuAnimeAttributes?
)

// ========== Kitsu Media Relationships API Models ==========

data class KitsuMediaRelationshipsResponse(
    val data: List<KitsuMediaRelationship>?,
    val included: List<KitsuIncludedAnime>?
)

data class KitsuMediaRelationship(
    val id: String,
    val type: String?,
    val attributes: KitsuMediaRelationshipAttributes?,
    val relationships: KitsuMediaRelationshipRels?
)

data class KitsuMediaRelationshipAttributes(
    val role: String?  // "sequel", "prequel", "side_story", "alternative_setting", etc.
)

data class KitsuMediaRelationshipRels(
    val destination: KitsuRelationshipData?
)

data class KitsuIncludedAnime(
    val id: String,
    val type: String?,
    val attributes: KitsuAnimeAttributes?
)

// ========== ARM API Models (arm.haglund.dev) ==========

/**
 * ARM API mapping entry - maps between anime database IDs
 * Each entry represents one season/entry in different databases
 */
data class ArmMappingEntry(
    val kitsu: Int? = null,
    val anilist: Int? = null,
    val myanimelist: Int? = null,
    val anidb: Int? = null,
    @SerializedName("anime-planet") val animePlanet: String? = null,
    val anisearch: Int? = null,
    val livechart: Int? = null,
    @SerializedName("notify-moe") val notifyMoe: String? = null,
    val imdb: String? = null,
    val themoviedb: Int? = null,
    val thetvdb: Int? = null
)
