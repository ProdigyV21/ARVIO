package com.arflix.tv.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
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

    // ========== Torrentio ==========

    @GET
    suspend fun getTorrentioStreams(
        @Url url: String
    ): StremioStreamResponse

    // ========== Generic Stremio Addon ==========

    @GET
    suspend fun getAddonStreams(
        @Url url: String
    ): StremioStreamResponse
    
    // ========== Real-Debrid ==========

    @FormUrlEncoded
    @POST
    suspend fun unrestrictLink(
        @Url url: String = "https://api.real-debrid.com/rest/1.0/unrestrict/link",
        @Header("Authorization") auth: String,
        @Field("link") link: String
    ): RealDebridUnrestrictResponse

    @GET("rest/1.0/torrents/instantAvailability/{hash}")
    suspend fun checkInstantAvailability(
        @Header("Authorization") auth: String,
        @Path("hash") hash: String
    ): Map<String, RealDebridAvailability>

    @GET("rest/1.0/user")
    suspend fun getRealDebridUser(
        @Header("Authorization") auth: String
    ): RealDebridUser

    // Real-Debrid Torrent/Magnet endpoints
    @FormUrlEncoded
    @POST
    suspend fun addMagnet(
        @Url url: String = "https://api.real-debrid.com/rest/1.0/torrents/addMagnet",
        @Header("Authorization") auth: String,
        @Field("magnet") magnet: String
    ): RealDebridAddMagnetResponse

    @FormUrlEncoded
    @POST
    suspend fun selectTorrentFiles(
        @Url url: String,
        @Header("Authorization") auth: String,
        @Field("files") files: String = "all"
    )

    @GET
    suspend fun getTorrentInfo(
        @Url url: String,
        @Header("Authorization") auth: String
    ): RealDebridTorrentInfo
    
    // ========== Real-Debrid Device Auth ==========
    
    @GET
    suspend fun getRdDeviceCode(
        @Url url: String = "https://api.real-debrid.com/oauth/v2/device/code",
        @Query("client_id") clientId: String,
        @Query("new_credentials") newCredentials: String = "yes"
    ): RealDebridDeviceCode
    
    @GET
    suspend fun pollRdToken(
        @Url url: String = "https://api.real-debrid.com/oauth/v2/device/credentials",
        @Query("client_id") clientId: String,
        @Query("code") code: String
    ): RealDebridCredentials
    
    @GET
    suspend fun getRdAccessToken(
        @Url url: String = "https://api.real-debrid.com/oauth/v2/token",
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String = "http://oauth.net/grant_type/device/1.0"
    ): RealDebridToken
    
    // ========== OpenSubtitles ==========

    @GET
    suspend fun getSubtitles(
        @Url url: String
    ): StremioSubtitleResponse

    // ========== TorBox ==========
    // Using correct TorBox API v1 endpoints (verified from API docs)

    @Multipart
    @POST
    suspend fun torboxCreateTorrent(
        @Url url: String = "https://api.torbox.app/v1/api/torrents/createtorrent",
        @Header("Authorization") auth: String,
        @Part("magnet") magnet: okhttp3.RequestBody,
        @Part("seed") seed: okhttp3.RequestBody,
        @Part("allow_zip") allowZip: okhttp3.RequestBody
    ): TorboxCreateTorrentResponse

    @GET
    suspend fun torboxGetTorrentList(
        @Url url: String = "https://api.torbox.app/v1/api/torrents/mylist?bypass_cache=true",
        @Header("Authorization") auth: String
    ): TorboxTorrentListResponse

    // TorBox requestdl is GET with query params (correct API endpoint)
    @GET
    suspend fun torboxRequestDownloadLink(
        @Url url: String,
        @Header("Authorization") auth: String
    ): TorboxDownloadLinkResponse

    @GET
    suspend fun torboxGetUser(
        @Url url: String = "https://api.torbox.app/v1/api/user/me",
        @Header("Authorization") auth: String
    ): TorboxUserResponse

    // TorBox checkcached - batch check if hashes are cached (instant availability)
    // GET https://api.torbox.app/v1/api/torrents/checkcached?hash=hash1&hash=hash2&format=object
    @GET
    suspend fun torboxCheckCached(
        @Url url: String,
        @Header("Authorization") auth: String
    ): TorboxCheckCachedResponse
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

// ========== Real-Debrid Models ==========

data class RealDebridUnrestrictResponse(
    val id: String,
    val filename: String,
    val mimeType: String?,
    val filesize: Long,
    val link: String,
    val host: String,
    val chunks: Int,
    val crc: Int?,
    val download: String,
    val streamable: Int
)

data class RealDebridAvailability(
    val rd: List<Map<String, RealDebridFileInfo>>?
)

data class RealDebridFileInfo(
    val filename: String,
    val filesize: Long
)

data class RealDebridUser(
    val id: Int,
    val username: String,
    val email: String,
    val points: Int,
    val locale: String,
    val avatar: String,
    val type: String,
    val premium: Int,
    val expiration: String
)

data class RealDebridDeviceCode(
    @SerializedName("device_code") val deviceCode: String,
    @SerializedName("user_code") val userCode: String,
    val interval: Int,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("verification_url") val verificationUrl: String,
    @SerializedName("direct_verification_url") val directVerificationUrl: String?
)

data class RealDebridCredentials(
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String
)

data class RealDebridToken(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("refresh_token") val refreshToken: String
)

data class RealDebridAddMagnetResponse(
    val id: String,
    val uri: String
)

data class RealDebridTorrentInfo(
    val id: String,
    val filename: String?,
    val hash: String?,
    val status: String,
    val links: List<String>?
)

// ========== TorBox Models ==========

data class TorboxCreateTorrentResponse(
    val success: Boolean,
    val detail: String?,
    val data: TorboxTorrentData?
)

data class TorboxTorrentData(
    val id: Int,
    val hash: String?,
    val name: String?,
    @SerializedName("torrent_id") val torrentId: Int?
)

data class TorboxTorrentInfoResponse(
    val success: Boolean,
    val detail: String?,
    val data: TorboxTorrentInfo?
)

data class TorboxTorrentListResponse(
    val success: Boolean,
    val detail: String?,
    val data: List<TorboxTorrentInfo>?
)

data class TorboxRequestLinkBody(
    val token: String,
    @SerializedName("torrent_id") val torrentId: Int,
    @SerializedName("file_id") val fileId: Int,
    val zip: Boolean = false
)

data class TorboxTorrentInfo(
    val id: Int,
    val hash: String?,
    val name: String?,
    @SerializedName("download_state") val downloadState: String?,
    @SerializedName("download_speed") val downloadSpeed: Long?,
    val progress: Float?,
    val files: List<TorboxFile>?
)

data class TorboxFile(
    val id: Int,
    val name: String?,
    val size: Long?,
    @SerializedName("short_name") val shortName: String?
)

data class TorboxDownloadLinkResponse(
    val success: Boolean,
    val detail: String?,
    val data: String? // Direct download URL
)

data class TorboxUserResponse(
    val success: Boolean,
    val detail: String?,
    val data: TorboxUser?
)

data class TorboxUser(
    val id: Int,
    val email: String?,
    val plan: Int?,
    @SerializedName("total_downloaded") val totalDownloaded: Long?,
    @SerializedName("premium_expires_at") val premiumExpiresAt: String?
)

// TorBox checkcached response - returns which hashes are cached
// Response format with format=object: { "success": true, "data": { "hash1": {...}, "hash2": null } }
// If cached, hash value contains torrent info; if not cached, hash value is null
data class TorboxCheckCachedResponse(
    val success: Boolean,
    val detail: String?,
    val data: Map<String, TorboxCachedTorrentInfo?>?
)

data class TorboxCachedTorrentInfo(
    val name: String?,
    val size: Long?,
    val hash: String?,
    val files: List<TorboxCachedFile>?
)

data class TorboxCachedFile(
    val name: String?,
    val size: Long?
)


