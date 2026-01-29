package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arflix.tv.data.api.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

private val Context.debridDataStore: DataStore<Preferences> by preferencesDataStore(name = "debrid_prefs")

/**
 * Debrid service type - extensible for all major providers
 */
enum class DebridService {
    REAL_DEBRID,
    TORBOX,
    PREMIUMIZE,
    ALLDEBRID,
    DEBRID_LINK,
    NONE
}

/**
 * Generic debrid service configuration
 * Allows supporting any debrid service via URL patterns
 */
data class DebridServiceConfig(
    val service: DebridService,
    val name: String,
    val apiBaseUrl: String,
    val authType: DebridAuthType,
    val unrestrictEndpoint: String,
    val instantCheckEndpoint: String?
)

enum class DebridAuthType {
    BEARER_TOKEN,    // Real-Debrid style
    API_KEY_HEADER,  // Premiumize style
    API_KEY_PARAM    // Some services use URL params
}

/**
 * Repository for Real-Debrid and TorBox integration
 * Matches webapp's RealDebrid.ts and TorBox.ts functionality
 */
@Singleton
class DebridRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val streamApi: StreamApi
) {
    private val TAG = "DebridRepository"

    // Real-Debrid keys
    private val RD_ACCESS_TOKEN_KEY = stringPreferencesKey("rd_access_token")
    private val RD_REFRESH_TOKEN_KEY = stringPreferencesKey("rd_refresh_token")
    private val RD_CLIENT_ID_KEY = stringPreferencesKey("rd_client_id")
    private val RD_CLIENT_SECRET_KEY = stringPreferencesKey("rd_client_secret")
    private val RD_EXPIRES_AT_KEY = stringPreferencesKey("rd_expires_at")
    
    // TorBox keys
    private val TB_API_KEY = stringPreferencesKey("torbox_api_key")

    // Premiumize keys
    private val PM_API_KEY = stringPreferencesKey("premiumize_api_key")

    // AllDebrid keys
    private val AD_API_KEY = stringPreferencesKey("alldebrid_api_key")

    // Debrid-Link keys
    private val DL_API_KEY = stringPreferencesKey("debridlink_api_key")

    // Default RD client ID for device auth
    private val RD_DEFAULT_CLIENT_ID = "X245A4XAIBGVM"

    // Service configurations for extensibility
    private val serviceConfigs = mapOf(
        DebridService.REAL_DEBRID to DebridServiceConfig(
            service = DebridService.REAL_DEBRID,
            name = "Real-Debrid",
            apiBaseUrl = "https://api.real-debrid.com/rest/1.0",
            authType = DebridAuthType.BEARER_TOKEN,
            unrestrictEndpoint = "/unrestrict/link",
            instantCheckEndpoint = "/torrents/instantAvailability"
        ),
        DebridService.PREMIUMIZE to DebridServiceConfig(
            service = DebridService.PREMIUMIZE,
            name = "Premiumize",
            apiBaseUrl = "https://www.premiumize.me/api",
            authType = DebridAuthType.API_KEY_PARAM,
            unrestrictEndpoint = "/transfer/directdl",
            instantCheckEndpoint = null
        ),
        DebridService.ALLDEBRID to DebridServiceConfig(
            service = DebridService.ALLDEBRID,
            name = "AllDebrid",
            apiBaseUrl = "https://api.alldebrid.com/v4",
            authType = DebridAuthType.API_KEY_PARAM,
            unrestrictEndpoint = "/link/unlock",
            instantCheckEndpoint = "/magnet/instant"
        ),
        DebridService.DEBRID_LINK to DebridServiceConfig(
            service = DebridService.DEBRID_LINK,
            name = "Debrid-Link",
            apiBaseUrl = "https://debrid-link.fr/api/v2",
            authType = DebridAuthType.BEARER_TOKEN,
            unrestrictEndpoint = "/downloader/add",
            instantCheckEndpoint = null
        )
    )
    
    // ========== Authentication Status ==========
    
    val isRealDebridAuthenticated: Flow<Boolean> = context.debridDataStore.data.map { prefs ->
        prefs[RD_ACCESS_TOKEN_KEY] != null
    }
    
    val isTorBoxAuthenticated: Flow<Boolean> = context.debridDataStore.data.map { prefs ->
        prefs[TB_API_KEY] != null
    }

    val isPremiumizeAuthenticated: Flow<Boolean> = context.debridDataStore.data.map { prefs ->
        prefs[PM_API_KEY] != null
    }

    val isAllDebridAuthenticated: Flow<Boolean> = context.debridDataStore.data.map { prefs ->
        prefs[AD_API_KEY] != null
    }

    val isDebridLinkAuthenticated: Flow<Boolean> = context.debridDataStore.data.map { prefs ->
        prefs[DL_API_KEY] != null
    }

    suspend fun getActiveDebridService(): DebridService {
        val prefs = context.debridDataStore.data.first()
        return when {
            prefs[RD_ACCESS_TOKEN_KEY] != null -> DebridService.REAL_DEBRID
            prefs[PM_API_KEY] != null -> DebridService.PREMIUMIZE
            prefs[AD_API_KEY] != null -> DebridService.ALLDEBRID
            prefs[DL_API_KEY] != null -> DebridService.DEBRID_LINK
            prefs[TB_API_KEY] != null -> DebridService.TORBOX
            else -> DebridService.NONE
        }
    }

    /**
     * Get configuration for a debrid service
     */
    fun getServiceConfig(service: DebridService): DebridServiceConfig? {
        return serviceConfigs[service]
    }

    /**
     * Get all available debrid services
     */
    fun getAllServices(): List<DebridServiceConfig> {
        return serviceConfigs.values.toList()
    }
    
    // ========== Real-Debrid Authentication ==========
    
    /**
     * Get device code for Real-Debrid OAuth
     */
    suspend fun getRdDeviceCode(): RealDebridDeviceCode = withContext(Dispatchers.IO) {
        streamApi.getRdDeviceCode(clientId = RD_DEFAULT_CLIENT_ID)
    }
    
    /**
     * Poll for Real-Debrid credentials after user authorizes
     * Returns true when authentication is complete
     */
    suspend fun pollRdCredentials(deviceCode: String, interval: Int): Boolean = withContext(Dispatchers.IO) {
        try {
            // Poll for credentials
            val credentials = streamApi.pollRdToken(
                clientId = RD_DEFAULT_CLIENT_ID,
                code = deviceCode
            )
            
            // Get access token
            val token = streamApi.getRdAccessToken(
                clientId = credentials.clientId,
                clientSecret = credentials.clientSecret,
                code = deviceCode
            )
            
            // Save credentials
            context.debridDataStore.edit { prefs ->
                prefs[RD_ACCESS_TOKEN_KEY] = token.accessToken
                prefs[RD_REFRESH_TOKEN_KEY] = token.refreshToken
                prefs[RD_CLIENT_ID_KEY] = credentials.clientId
                prefs[RD_CLIENT_SECRET_KEY] = credentials.clientSecret
                prefs[RD_EXPIRES_AT_KEY] = (System.currentTimeMillis() / 1000 + token.expiresIn).toString()
            }

            true
        } catch (e: Exception) {
            // Still waiting for user to authorize
            false
        }
    }
    
    /**
     * Refresh Real-Debrid token if expired
     */
    private suspend fun refreshRdTokenIfNeeded(): String? {
        val prefs = context.debridDataStore.data.first()
        val accessToken = prefs[RD_ACCESS_TOKEN_KEY] ?: return null
        val refreshToken = prefs[RD_REFRESH_TOKEN_KEY] ?: return null
        val clientId = prefs[RD_CLIENT_ID_KEY] ?: return null
        val clientSecret = prefs[RD_CLIENT_SECRET_KEY] ?: return null
        val expiresAt = prefs[RD_EXPIRES_AT_KEY]?.toLongOrNull() ?: return null
        
        val now = System.currentTimeMillis() / 1000
        
        // If not expired, return current token
        if (now < expiresAt - 3600) {
            return accessToken
        }
        
        // Refresh token
        return try {
            val newToken = streamApi.getRdAccessToken(
                clientId = clientId,
                clientSecret = clientSecret,
                code = refreshToken,
                grantType = "refresh_token"
            )
            
            context.debridDataStore.edit { prefs ->
                prefs[RD_ACCESS_TOKEN_KEY] = newToken.accessToken
                prefs[RD_REFRESH_TOKEN_KEY] = newToken.refreshToken
                prefs[RD_EXPIRES_AT_KEY] = (System.currentTimeMillis() / 1000 + newToken.expiresIn).toString()
            }
            
            newToken.accessToken
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Logout from Real-Debrid
     */
    suspend fun logoutRealDebrid() {
        context.debridDataStore.edit { prefs ->
            prefs.remove(RD_ACCESS_TOKEN_KEY)
            prefs.remove(RD_REFRESH_TOKEN_KEY)
            prefs.remove(RD_CLIENT_ID_KEY)
            prefs.remove(RD_CLIENT_SECRET_KEY)
            prefs.remove(RD_EXPIRES_AT_KEY)
        }

    }
    
    // ========== TorBox Authentication ==========
    
    /**
     * Set TorBox API key
     */
    suspend fun setTorBoxApiKey(apiKey: String) {
        context.debridDataStore.edit { prefs ->
            prefs[TB_API_KEY] = apiKey
        }
    }
    
    /**
     * Logout from TorBox
     */
    suspend fun logoutTorBox() {
        context.debridDataStore.edit { prefs ->
            prefs.remove(TB_API_KEY)
        }
    }

    // ========== Additional Debrid Services ==========

    /**
     * Set Premiumize API key
     */
    suspend fun setPremiumizeApiKey(apiKey: String) {
        context.debridDataStore.edit { prefs ->
            prefs[PM_API_KEY] = apiKey
        }
    }

    /**
     * Set AllDebrid API key
     */
    suspend fun setAllDebridApiKey(apiKey: String) {
        context.debridDataStore.edit { prefs ->
            prefs[AD_API_KEY] = apiKey
        }
    }

    /**
     * Set Debrid-Link API key
     */
    suspend fun setDebridLinkApiKey(apiKey: String) {
        context.debridDataStore.edit { prefs ->
            prefs[DL_API_KEY] = apiKey
        }
    }

    /**
     * Logout from any debrid service
     */
    suspend fun logoutDebridService(service: DebridService) {
        context.debridDataStore.edit { prefs ->
            when (service) {
                DebridService.REAL_DEBRID -> {
                    prefs.remove(RD_ACCESS_TOKEN_KEY)
                    prefs.remove(RD_REFRESH_TOKEN_KEY)
                    prefs.remove(RD_CLIENT_ID_KEY)
                    prefs.remove(RD_CLIENT_SECRET_KEY)
                    prefs.remove(RD_EXPIRES_AT_KEY)
                }
                DebridService.TORBOX -> prefs.remove(TB_API_KEY)
                DebridService.PREMIUMIZE -> prefs.remove(PM_API_KEY)
                DebridService.ALLDEBRID -> prefs.remove(AD_API_KEY)
                DebridService.DEBRID_LINK -> prefs.remove(DL_API_KEY)
                DebridService.NONE -> {}
            }
        }
    }
    
    // ========== Stream Unrestriction ==========
    
    /**
     * Unrestrict a link using the active debrid service
     * Returns the unrestricted direct URL or null on failure
     */
    suspend fun unrestrictLink(magnetOrUrl: String): String? = withContext(Dispatchers.IO) {
        when (getActiveDebridService()) {
            DebridService.REAL_DEBRID -> unrestrictWithRealDebrid(magnetOrUrl)
            DebridService.PREMIUMIZE -> unrestrictWithPremiumize(magnetOrUrl)
            DebridService.ALLDEBRID -> unrestrictWithAllDebrid(magnetOrUrl)
            DebridService.DEBRID_LINK -> unrestrictWithDebridLink(magnetOrUrl)
            DebridService.TORBOX -> unrestrictWithTorBox(magnetOrUrl)
            DebridService.NONE -> null
        }
    }
    
    private suspend fun unrestrictWithRealDebrid(link: String): String? {
        val token = refreshRdTokenIfNeeded() ?: return null

        return try {
            // Check if it's a magnet link - needs special handling
            if (link.startsWith("magnet:")) {
                return resolveMagnetWithRealDebrid(link, token)
            }

            // Direct link unrestriction
            val response = streamApi.unrestrictLink(
                auth = "Bearer $token",
                link = link
            )
            response.download
        } catch (e: Exception) {
            Log.e(TAG, "RD unrestrict failed: ${e.message}")
            null
        }
    }

    /**
     * Resolve magnet URL to direct streaming URL via Real-Debrid
     * Flow: addMagnet -> selectFiles -> getTorrentInfo -> unrestrictLink
     */
    private suspend fun resolveMagnetWithRealDebrid(magnet: String, token: String): String? {
        return try {
            Log.d(TAG, "Adding magnet to Real-Debrid...")

            // Step 1: Add magnet to Real-Debrid
            val addResponse = streamApi.addMagnet(
                auth = "Bearer $token",
                magnet = magnet
            )
            val torrentId = addResponse.id
            Log.d(TAG, "Magnet added, torrent ID: $torrentId")

            // Step 2: Select all files
            streamApi.selectTorrentFiles(
                url = "https://api.real-debrid.com/rest/1.0/torrents/selectFiles/$torrentId",
                auth = "Bearer $token"
            )
            Log.d(TAG, "Files selected")

            // Step 3: Wait briefly and get torrent info with links
            delay(1000) // Give RD time to process

            var attempts = 0
            var torrentInfo: com.arflix.tv.data.api.RealDebridTorrentInfo? = null

            while (attempts < 10) {
                torrentInfo = streamApi.getTorrentInfo(
                    url = "https://api.real-debrid.com/rest/1.0/torrents/info/$torrentId",
                    auth = "Bearer $token"
                )

                Log.d(TAG, "Torrent status: ${torrentInfo.status}, links: ${torrentInfo.links?.size ?: 0}")

                // Check if torrent has links ready
                if (!torrentInfo.links.isNullOrEmpty()) {
                    break
                }

                // If still converting, wait and retry
                if (torrentInfo.status == "magnet_conversion" ||
                    torrentInfo.status == "waiting_files_selection" ||
                    torrentInfo.status == "queued") {
                    delay(1500)
                    attempts++
                } else if (torrentInfo.status == "downloaded") {
                    // Downloaded but no links? Unlikely but handle it
                    delay(500)
                    attempts++
                } else {
                    // Unknown status, might be error
                    Log.w(TAG, "Unknown torrent status: ${torrentInfo.status}")
                    break
                }
            }

            val links = torrentInfo?.links
            if (links.isNullOrEmpty()) {
                Log.e(TAG, "No links available from torrent")
                return null
            }

            // Step 4: Unrestrict the first link
            val firstLink = links.first()
            Log.d(TAG, "Unrestricting link: $firstLink")

            val unrestrictResponse = streamApi.unrestrictLink(
                auth = "Bearer $token",
                link = firstLink
            )

            Log.d(TAG, "Got download URL: ${unrestrictResponse.download}")
            unrestrictResponse.download

        } catch (e: Exception) {
            Log.e(TAG, "Failed to resolve magnet with Real-Debrid", e)
            null
        }
    }
    
    private suspend fun unrestrictWithTorBox(link: String): String? {
        val prefs = context.debridDataStore.data.first()
        val apiKey = prefs[TB_API_KEY] ?: return null

        return try {
            // Check if it's a magnet link - needs torrent creation flow
            if (link.startsWith("magnet:")) {
                return resolveMagnetWithTorBox(link, apiKey)
            }

            // For direct links, TorBox can also handle hoster links
            // But typically we deal with magnets from torrent addons
            Log.d(TAG, "TorBox: Direct link unrestrict not implemented, returning as-is")
            link
        } catch (e: Exception) {
            Log.e(TAG, "TorBox unrestrict failed", e)
            null
        }
    }

    /**
     * Resolve magnet URL to direct streaming URL via TorBox
     * Flow: createTorrent -> get list by hash -> requestDownloadLink (POST)
     * Matches webapp TorBox.ts implementation
     */
    private suspend fun resolveMagnetWithTorBox(magnet: String, apiKey: String): String? {
        return try {
            // Extract hash from magnet for lookup
            val hashRegex = """urn:btih:([a-fA-F0-9]+)""".toRegex()
            val hashMatch = hashRegex.find(magnet)
            val infoHash = hashMatch?.groupValues?.getOrNull(1)?.lowercase()

            Log.d(TAG, "TorBox: Adding magnet (hash: ${infoHash?.take(8)}...)")

            // Use the shared resolution method
            return infoHash?.let { resolveTorBoxByHash(it, 0, apiKey) }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to resolve magnet with TorBox", e)
            null
        }
    }

    /**
     * Resolve TorBox proxy URL (from Torrentio) to direct streaming URL.
     * URL format: https://torrentio.strem.fun/torbox/API_KEY/HASH/FILE_INDEX
     * Matches webapp TorBox.ts resolveStream() implementation
     */
    suspend fun resolveTorBoxUrl(url: String): String? {
        // Extract API key from URL or use stored key
        val parts = url.split("/")
        val torboxIndex = parts.indexOf("torbox")

        if (torboxIndex == -1 || parts.size <= torboxIndex + 2) {
            Log.e(TAG, "Invalid TorBox URL format: $url")
            return null
        }

        val storedApiKey = context.debridDataStore.data.first()[TB_API_KEY]
        val apiKey = parts.getOrNull(torboxIndex + 1) ?: storedApiKey ?: run {
            Log.e(TAG, "No TorBox API key found")
            return null
        }

        val hash = parts.getOrNull(torboxIndex + 2)?.lowercase() ?: run {
            Log.e(TAG, "No hash in TorBox URL")
            return null
        }

        val fileIndex = parts.getOrNull(torboxIndex + 3)?.toIntOrNull() ?: 0

        Log.d(TAG, "TorBox URL: hash=${hash.take(8)}..., fileIndex=$fileIndex")

        // Use the hash to resolve via TorBox API
        return resolveTorBoxByHash(hash, fileIndex, apiKey)
    }

    /**
     * Resolve torrent by hash using TorBox API - INSTANT for cached torrents
     * Uses the same endpoints and flow as the webapp TorBox.ts
     */
    private suspend fun resolveTorBoxByHash(hash: String, fileIndex: Int, apiKey: String): String? {
        return try {
            Log.d(TAG, "TorBox INSTANT: Resolving hash ${hash.take(8)}...")

            // Step 1: Check if torrent is already in list (INSTANT for cached)
            var torrentInfo: com.arflix.tv.data.api.TorboxTorrentInfo? = null

            try {
                val listResponse = streamApi.torboxGetTorrentList(auth = "Bearer $apiKey")
                if (listResponse.success && !listResponse.data.isNullOrEmpty()) {
                    torrentInfo = listResponse.data.find { it.hash?.lowercase() == hash.lowercase() }
                    if (torrentInfo != null) {
                        val state = torrentInfo.downloadState?.lowercase() ?: ""
                        val hasFiles = torrentInfo.files?.isNotEmpty() == true
                        Log.d(TAG, "TorBox: Found existing torrent, state=$state, files=${torrentInfo.files?.size ?: 0}")

                        // If ready, skip to download link - INSTANT!
                        if (state in listOf("cached", "completed", "seeding", "uploading") && hasFiles) {
                            Log.d(TAG, "TorBox INSTANT: Torrent ready, getting download link...")
                        } else {
                            // Not ready, need to wait
                            torrentInfo = null
                        }
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "TorBox: Error checking existing: ${e.message}")
            }

            // Step 2: If not found/ready, add torrent and wait (slower path)
            if (torrentInfo == null) {
                val magnet = "magnet:?xt=urn:btih:$hash"
                Log.d(TAG, "TorBox: Adding torrent...")

                try {
                    val textMediaType = "text/plain".toMediaTypeOrNull()
                    streamApi.torboxCreateTorrent(
                        auth = "Bearer $apiKey",
                        magnet = magnet.toRequestBody(textMediaType),
                        seed = "1".toRequestBody(textMediaType),
                        allowZip = "false".toRequestBody(textMediaType)
                    )
                } catch (e: Exception) {
                    Log.w(TAG, "TorBox: Create may have failed: ${e.message}")
                }

                // Poll for torrent to be ready (limited retries)
                var attempts = 0
                while (attempts < 5 && torrentInfo == null) {
                    delay(1000)
                    try {
                        val listResponse = streamApi.torboxGetTorrentList(auth = "Bearer $apiKey")
                        if (listResponse.success && !listResponse.data.isNullOrEmpty()) {
                            torrentInfo = listResponse.data.find { it.hash?.lowercase() == hash.lowercase() }
                            if (torrentInfo != null) {
                                val state = torrentInfo.downloadState?.lowercase() ?: ""
                                val hasFiles = torrentInfo.files?.isNotEmpty() == true
                                if (state in listOf("cached", "completed", "seeding", "uploading") && hasFiles) {
                                    break
                                }
                                torrentInfo = null // Not ready
                            }
                        }
                    } catch (e: Exception) {
                        Log.w(TAG, "TorBox: Poll error: ${e.message}")
                    }
                    attempts++
                }
            }

            if (torrentInfo == null) {
                Log.e(TAG, "TorBox: Could not find ready torrent")
                return null
            }

            // Step 3: Select the largest video file (to avoid grabbing .url, .nfo, .txt files)
            val files = torrentInfo.files
            if (files.isNullOrEmpty()) {
                Log.e(TAG, "TorBox: No files available in torrent")
                return null
            }

            // Video file extensions
            val videoExtensions = listOf(".mkv", ".mp4", ".avi", ".webm", ".m4v", ".mov", ".wmv", ".flv", ".ts", ".m2ts")

            // Find the largest video file
            val targetFile = files
                .filter { file ->
                    val name = file.name?.lowercase() ?: ""
                    videoExtensions.any { ext -> name.endsWith(ext) }
                }
                .maxByOrNull { it.size ?: 0L }
                ?: files.maxByOrNull { it.size ?: 0L } // Fallback to largest file if no video found

            if (targetFile == null) {
                Log.e(TAG, "TorBox: Could not find file")
                return null
            }

            Log.d(TAG, "TorBox: Using file ID=${targetFile.id}, name=${targetFile.name?.take(40)}, size=${targetFile.size?.div(1024*1024) ?: 0}MB")

            // Step 4: Request download link via GET with query params (correct TorBox API)
            val requestUrl = "https://api.torbox.app/v1/api/torrents/requestdl?token=$apiKey&torrent_id=${torrentInfo.id}&file_id=${targetFile.id}&zip_link=false"

            val downloadResponse = streamApi.torboxRequestDownloadLink(
                url = requestUrl,
                auth = "Bearer $apiKey"
            )

            if (!downloadResponse.success || downloadResponse.data.isNullOrEmpty()) {
                Log.e(TAG, "TorBox: Failed to get download link - ${downloadResponse.detail}")
                return null
            }

            Log.d(TAG, "TorBox: Got download URL!")
            downloadResponse.data

        } catch (e: Exception) {
            Log.e(TAG, "TorBox resolution failed", e)
            null
        }
    }

    private suspend fun unrestrictWithPremiumize(link: String): String? {
        val prefs = context.debridDataStore.data.first()
        val apiKey = prefs[PM_API_KEY] ?: return null

        return try {
            // Premiumize uses API key as URL parameter
            // Implementation would make request to:
            // https://www.premiumize.me/api/transfer/directdl?apikey={apiKey}&src={link}
            Log.d(TAG, "Premiumize unrestrict not yet implemented")
            null
        } catch (e: Exception) {
            Log.e(TAG, "Premiumize unrestrict failed", e)
            null
        }
    }

    private suspend fun unrestrictWithAllDebrid(link: String): String? {
        val prefs = context.debridDataStore.data.first()
        val apiKey = prefs[AD_API_KEY] ?: return null

        return try {
            // AllDebrid uses API key as URL parameter
            // Implementation would make request to:
            // https://api.alldebrid.com/v4/link/unlock?agent=arflix&apikey={apiKey}&link={link}
            Log.d(TAG, "AllDebrid unrestrict not yet implemented")
            null
        } catch (e: Exception) {
            Log.e(TAG, "AllDebrid unrestrict failed", e)
            null
        }
    }

    private suspend fun unrestrictWithDebridLink(link: String): String? {
        val prefs = context.debridDataStore.data.first()
        val apiKey = prefs[DL_API_KEY] ?: return null

        return try {
            // Debrid-Link uses bearer token
            // Implementation would make request to:
            // https://debrid-link.fr/api/v2/downloader/add
            // Authorization: Bearer {apiKey}
            Log.d(TAG, "Debrid-Link unrestrict not yet implemented")
            null
        } catch (e: Exception) {
            Log.e(TAG, "Debrid-Link unrestrict failed", e)
            null
        }
    }
    
    /**
     * Check if a torrent hash is cached (instant availability)
     */
    suspend fun checkInstantAvailability(infoHash: String): Boolean = withContext(Dispatchers.IO) {
        when (getActiveDebridService()) {
            DebridService.REAL_DEBRID -> checkRdInstantAvailability(infoHash)
            DebridService.ALLDEBRID -> checkAdInstantAvailability(infoHash)
            DebridService.TORBOX -> checkTbInstantAvailability(infoHash)
            DebridService.PREMIUMIZE, DebridService.DEBRID_LINK, DebridService.NONE -> false
        }
    }
    
    private suspend fun checkRdInstantAvailability(hash: String): Boolean {
        val token = refreshRdTokenIfNeeded() ?: return false
        
        return try {
            val response = streamApi.checkInstantAvailability(
                auth = "Bearer $token",
                hash = hash
            )
            response.isNotEmpty() && response.values.any { it.rd?.isNotEmpty() == true }
        } catch (e: Exception) {
            false
        }
    }
    
    private suspend fun checkTbInstantAvailability(hash: String): Boolean {
        val prefs = context.debridDataStore.data.first()
        val apiKey = prefs[TB_API_KEY] ?: return false

        return try {
            val cachedHashes = checkTorBoxCached(listOf(hash), apiKey)
            cachedHashes.contains(hash.lowercase())
        } catch (e: Exception) {
            Log.e(TAG, "TorBox instant check failed", e)
            false
        }
    }

    /**
     * Batch check which hashes are cached in TorBox
     * Returns a set of hashes that are cached (ready for instant playback)
     * This is much more efficient than calling resolveTorBoxUrl for each hash
     */
    suspend fun checkTorBoxCached(hashes: List<String>, apiKey: String? = null): Set<String> {
        val key = apiKey ?: context.debridDataStore.data.first()[TB_API_KEY] ?: return emptySet()

        if (hashes.isEmpty()) return emptySet()

        return try {
            // Build URL with all hashes as query params
            // GET /v1/api/torrents/checkcached?hash=hash1&hash=hash2&format=object
            val hashParams = hashes.joinToString("&") { "hash=${it.lowercase()}" }
            val url = "https://api.torbox.app/v1/api/torrents/checkcached?$hashParams&format=object&list_files=false"

            Log.d(TAG, "TorBox: Checking cache for ${hashes.size} hashes...")

            val response = streamApi.torboxCheckCached(
                url = url,
                auth = "Bearer $key"
            )

            if (!response.success || response.data == null) {
                Log.w(TAG, "TorBox checkcached failed: ${response.detail}")
                return emptySet()
            }

            // Extract hashes that are cached (non-null values in the map)
            val cachedHashes = response.data
                .filter { (_, info) -> info != null }
                .keys
                .map { it.lowercase() }
                .toSet()

            Log.d(TAG, "TorBox: ${cachedHashes.size}/${hashes.size} hashes are cached")
            cachedHashes

        } catch (e: Exception) {
            Log.e(TAG, "TorBox checkcached error: ${e.message}")
            emptySet()
        }
    }

    private suspend fun checkAdInstantAvailability(hash: String): Boolean {
        val prefs = context.debridDataStore.data.first()
        val apiKey = prefs[AD_API_KEY] ?: return false

        return try {
            // AllDebrid instant check:
            // https://api.alldebrid.com/v4/magnet/instant?agent=arflix&apikey={apiKey}&magnets[]={hash}
            Log.d(TAG, "AllDebrid instant check not yet implemented")
            false
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Get Real-Debrid user info
     */
    suspend fun getRdUserInfo(): RealDebridUser? = withContext(Dispatchers.IO) {
        val token = refreshRdTokenIfNeeded() ?: return@withContext null
        
        try {
            streamApi.getRealDebridUser(auth = "Bearer $token")
        } catch (e: Exception) {
            null
        }
    }
}

/**
 * Device code response for UI display
 */
data class DebridDeviceCode(
    val userCode: String,
    val verificationUrl: String,
    val deviceCode: String,
    val interval: Int,
    val expiresIn: Int
)
