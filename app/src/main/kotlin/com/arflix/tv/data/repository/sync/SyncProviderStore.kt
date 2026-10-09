package com.arflix.tv.data.repository.sync

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.SecureStorage
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.traktDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

internal fun defaultTrackingReadMode(
    hasTrakt: Boolean,
    hasSimkl: Boolean,
    hasMdbList: Boolean,
    preferredProvider: SyncProvider = SyncProvider.NONE
): TrackingReadMode = when {
    preferredProvider == SyncProvider.TRAKT && hasTrakt -> TrackingReadMode.TRAKT
    preferredProvider == SyncProvider.SIMKL && hasSimkl -> TrackingReadMode.SIMKL
    preferredProvider == SyncProvider.MDBLIST && hasMdbList -> TrackingReadMode.MDBLIST
    hasTrakt -> TrackingReadMode.TRAKT
    hasSimkl -> TrackingReadMode.SIMKL
    hasMdbList -> TrackingReadMode.MDBLIST
    else -> TrackingReadMode.AUTO
}

internal fun shouldApplyCloudTrackingSelection(
    incomingUpdatedAt: Long?,
    localUpdatedAt: Long?,
    hasLocalSelection: Boolean
): Boolean {
    val incoming = incomingUpdatedAt ?: 0L
    val local = localUpdatedAt ?: 0L
    return when {
        incoming > local -> true
        incoming < local -> false
        incoming > 0L -> false
        else -> !hasLocalSelection
    }
}

internal fun shouldApplyCloudCredential(
    incomingUpdatedAt: Long?,
    localUpdatedAt: Long?,
    incomingHasCredential: Boolean,
    localHasCredential: Boolean
): Boolean {
    val incoming = incomingUpdatedAt ?: 0L
    val local = localUpdatedAt ?: 0L
    return when {
        incoming > local -> true
        incoming < local -> false
        incoming > 0L -> false
        else -> incomingHasCredential && !localHasCredential
    }
}

internal fun repairUnavailableTrackingReadMode(
    mode: TrackingReadMode,
    hasTrakt: Boolean,
    hasSimkl: Boolean,
    hasMdbList: Boolean,
    replacement: TrackingReadMode
): TrackingReadMode = when (mode) {
    TrackingReadMode.TRAKT -> if (hasTrakt) mode else replacement
    TrackingReadMode.SIMKL -> if (hasSimkl) mode else replacement
    TrackingReadMode.MDBLIST -> if (hasMdbList) mode else replacement
    TrackingReadMode.BOTH -> if (hasTrakt && hasSimkl) mode else replacement
    TrackingReadMode.AUTO -> mode
}

/**
 * Owns per-profile tracking connections and routing preferences.
 *
 * - `sync_provider` lives in [settingsDataStore] (profile-scoped).
 * - `mdblist_api_key` lives in [traktDataStore] alongside the Trakt tokens (the
 *   store name is historical; it is the profile-scoped credential store).
 *
 * Trakt tokens continue to be owned by TraktRepository; this store never touches
 * them. Which provider is *active* is derived here: if a profile is set to TRAKT
 * but has no token, or MDBLIST but no key, callers should treat it as NONE — the
 * providers themselves report `isConnected()` for the authoritative check.
 */
@Singleton
class SyncProviderStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val simklV2: com.arflix.tv.data.repository.simkl.SimklListsRepository? = null
) {
    private companion object {
        const val SIMKL_TOKEN_ALIAS = "arvio_simkl_access_token"
    }
    private fun providerKey() = profileManager.profileStringKey("sync_provider")
    private fun providerKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "sync_provider")
    private fun mdbListKey() = profileManager.profileStringKey("mdblist_api_key")
    private fun mdbListKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "mdblist_api_key")
    private fun mdbListAccessTokenKey() = profileManager.profileStringKey("mdblist_access_token")
    private fun mdbListAccessTokenKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "mdblist_access_token")
    private fun mdbListRefreshTokenKey() = profileManager.profileStringKey("mdblist_refresh_token")
    private fun mdbListRefreshTokenKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "mdblist_refresh_token")
    private fun mdbListTokenExpiresAtKey() = profileManager.profileLongKey("mdblist_token_expires_at")
    private fun mdbListTokenExpiresAtKeyFor(profileId: String) =
        profileManager.profileLongKeyFor(profileId, "mdblist_token_expires_at")
    private fun traktAccessTokenKey() = profileManager.profileStringKey("trakt_access_token")
    private fun watchlistReadModeKey() = profileManager.profileStringKey("tracking_watchlist_read_mode_v2")
    private fun continueWatchingReadModeKey() = profileManager.profileStringKey("tracking_continue_read_mode_v2")
    private fun watchedReadModeKey() = profileManager.profileStringKey("tracking_watched_read_mode_v2")
    private fun writeToTraktKey() = profileManager.profileBooleanKey("tracking_write_trakt_v2")
    private fun writeToSimklKey() = profileManager.profileBooleanKey("tracking_write_simkl_v2")
    private fun watchlistReadModeKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "tracking_watchlist_read_mode_v2")
    private fun continueWatchingReadModeKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "tracking_continue_read_mode_v2")
    private fun watchedReadModeKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "tracking_watched_read_mode_v2")
    private fun writeToTraktKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "tracking_write_trakt_v2")
    private fun writeToSimklKeyFor(profileId: String) =
        profileManager.profileBooleanKeyFor(profileId, "tracking_write_simkl_v2")
    private fun trackingUpdatedAtKey() =
        profileManager.profileLongKey("tracking_preferences_updated_at_v3")
    private fun trackingUpdatedAtKeyFor(profileId: String) =
        profileManager.profileLongKeyFor(profileId, "tracking_preferences_updated_at_v3")
    private fun simklCredentialUpdatedAtKey() =
        profileManager.profileLongKey("simkl_credential_updated_at_v3")
    private fun simklCredentialUpdatedAtKeyFor(profileId: String) =
        profileManager.profileLongKeyFor(profileId, "simkl_credential_updated_at_v3")
    private fun mdbListCredentialUpdatedAtKey() =
        profileManager.profileLongKey("mdblist_credential_updated_at_v3")
    private fun mdbListCredentialUpdatedAtKeyFor(profileId: String) =
        profileManager.profileLongKeyFor(profileId, "mdblist_credential_updated_at_v3")

    suspend fun getProvider(): SyncProvider {
        val prefs = context.settingsDataStore.data.first()
        return SyncProvider.fromStorage(prefs[providerKey()])
    }

    val providerFlow: Flow<SyncProvider> = context.settingsDataStore.data.map { prefs ->
        SyncProvider.fromStorage(prefs[providerKey()])
    }

    suspend fun setProvider(provider: SyncProvider) {
        setProvider(provider, profileManager.getProfileIdSync())
    }

    suspend fun setProvider(provider: SyncProvider, profileId: String) {
        context.settingsDataStore.edit { prefs ->
            if (provider == SyncProvider.NONE) {
                prefs.remove(providerKeyFor(profileId))
            } else {
                prefs[providerKeyFor(profileId)] = provider.toStorage()
            }
            prefs[trackingUpdatedAtKeyFor(profileId)] = System.currentTimeMillis()
        }
    }

    suspend fun getTrackingPreferences(): TrackingPreferences {
        val settings = context.settingsDataStore.data.first()
        val credentials = context.traktDataStore.data.first()
        val hasTrakt = !credentials[traktAccessTokenKey()].isNullOrBlank()
        val hasSimkl = simklV2?.isConnected() == true || !SecureStorage.decrypt(credentials[simklAccessTokenKey()], SIMKL_TOKEN_ALIAS).isNullOrBlank()
        val hasMdbList = !credentials[mdbListKey()].isNullOrBlank() || !credentials[mdbListAccessTokenKey()].isNullOrBlank()
        val fallback = defaultTrackingReadMode(
            hasTrakt = hasTrakt,
            hasSimkl = hasSimkl,
            hasMdbList = hasMdbList,
            preferredProvider = SyncProvider.fromStorage(settings[providerKey()])
        )
        return TrackingPreferences(
            watchlistReadMode = TrackingReadMode.fromStorage(settings[watchlistReadModeKey()]).let {
                if (it == TrackingReadMode.AUTO) fallback else it
            },
            continueWatchingReadMode = TrackingReadMode.fromStorage(settings[continueWatchingReadModeKey()]).let {
                if (it == TrackingReadMode.AUTO) fallback else it
            },
            watchedReadMode = TrackingReadMode.fromStorage(settings[watchedReadModeKey()]).let {
                if (it == TrackingReadMode.AUTO) fallback else it
            },
            writeToTrakt = settings[writeToTraktKey()] ?: hasTrakt,
            writeToSimkl = settings[writeToSimklKey()] ?: hasSimkl
        )
    }

    suspend fun setReadMode(feature: TrackingFeature, mode: TrackingReadMode) {
        setReadMode(feature, mode, profileManager.getProfileIdSync())
    }

    suspend fun setReadMode(feature: TrackingFeature, mode: TrackingReadMode, profileId: String) {
        context.settingsDataStore.edit { prefs ->
            val key = when (feature) {
                TrackingFeature.WATCHLIST -> watchlistReadModeKeyFor(profileId)
                TrackingFeature.CONTINUE_WATCHING -> continueWatchingReadModeKeyFor(profileId)
                TrackingFeature.WATCHED -> watchedReadModeKeyFor(profileId)
            }
            if (mode == TrackingReadMode.AUTO) prefs.remove(key) else prefs[key] = mode.toStorage()
            prefs[trackingUpdatedAtKeyFor(profileId)] = System.currentTimeMillis()
        }
    }

    suspend fun setWriteTarget(provider: SyncProvider, enabled: Boolean) {
        setWriteTarget(provider, enabled, profileManager.getProfileIdSync())
    }

    suspend fun setWriteTarget(provider: SyncProvider, enabled: Boolean, profileId: String) {
        context.settingsDataStore.edit { prefs ->
            when (provider) {
                SyncProvider.TRAKT -> prefs[writeToTraktKeyFor(profileId)] = enabled
                SyncProvider.SIMKL -> prefs[writeToSimklKeyFor(profileId)] = enabled
                else -> Unit
            }
            if (provider == SyncProvider.TRAKT || provider == SyncProvider.SIMKL) {
                prefs[trackingUpdatedAtKeyFor(profileId)] = System.currentTimeMillis()
            }
        }
    }

    suspend fun readProviders(feature: TrackingFeature): Set<SyncProvider> {
        val preferences = getTrackingPreferences()
        val mode = when (feature) {
            TrackingFeature.WATCHLIST -> preferences.watchlistReadMode
            TrackingFeature.CONTINUE_WATCHING -> preferences.continueWatchingReadMode
            TrackingFeature.WATCHED -> preferences.watchedReadMode
        }
        return when (mode) {
            TrackingReadMode.TRAKT -> setOf(SyncProvider.TRAKT)
            TrackingReadMode.SIMKL -> setOf(SyncProvider.SIMKL)
            TrackingReadMode.BOTH -> setOf(SyncProvider.TRAKT, SyncProvider.SIMKL)
            TrackingReadMode.MDBLIST -> setOf(SyncProvider.MDBLIST)
            TrackingReadMode.AUTO -> emptySet()
        }
    }

    suspend fun writeProviders(): Set<SyncProvider> {
        val preferences = getTrackingPreferences()
        val credentials = context.traktDataStore.data.first()
        val hasMdbList = !credentials[mdbListKey()].isNullOrBlank() || !credentials[mdbListAccessTokenKey()].isNullOrBlank()
        return buildSet {
            if (preferences.writeToTrakt == true) add(SyncProvider.TRAKT)
            if (preferences.writeToSimkl == true) add(SyncProvider.SIMKL)
            if (hasMdbList) add(SyncProvider.MDBLIST)
        }
    }

    suspend fun onProviderConnected(provider: SyncProvider) {
        onProviderConnected(provider, profileManager.getProfileIdSync())
    }

    suspend fun onProviderConnected(provider: SyncProvider, profileId: String) {
        val settings = context.settingsDataStore.data.first()
        val credentials = context.traktDataStore.data.first()
        val hasTrakt = !credentials[profileManager.profileStringKeyFor(profileId, "trakt_access_token")].isNullOrBlank()
        val hasSimkl = simklV2?.isConnected(profileId) == true || !SecureStorage.decrypt(credentials[simklAccessTokenKeyFor(profileId)], SIMKL_TOKEN_ALIAS).isNullOrBlank()
        val hasMdbList = !credentials[mdbListKeyFor(profileId)].isNullOrBlank() || !credentials[mdbListAccessTokenKeyFor(profileId)].isNullOrBlank()
        val currentProvider = SyncProvider.fromStorage(settings[providerKeyFor(profileId)])
        val connected = when (provider) {
            SyncProvider.TRAKT -> hasTrakt
            SyncProvider.SIMKL -> hasSimkl
            SyncProvider.MDBLIST -> hasMdbList
            SyncProvider.NONE -> false
        }
        if (!connected) return
        val currentProviderStillConnected = when (currentProvider) {
            SyncProvider.TRAKT -> hasTrakt
            SyncProvider.SIMKL -> hasSimkl
            SyncProvider.MDBLIST -> hasMdbList
            SyncProvider.NONE -> false
        }
        if (!currentProviderStillConnected) setProvider(provider, profileId)

        val replacement = defaultTrackingReadMode(
            hasTrakt = hasTrakt,
            hasSimkl = hasSimkl,
            hasMdbList = hasMdbList,
            preferredProvider = currentProvider.takeIf { currentProviderStillConnected } ?: provider
        )
        val storedWatchlistMode = TrackingReadMode.fromStorage(settings[watchlistReadModeKeyFor(profileId)])
        val storedContinueMode = TrackingReadMode.fromStorage(settings[continueWatchingReadModeKeyFor(profileId)])
        val storedWatchedMode = TrackingReadMode.fromStorage(settings[watchedReadModeKeyFor(profileId)])
        fun repaired(mode: TrackingReadMode) = repairUnavailableTrackingReadMode(
            mode = mode,
            hasTrakt = hasTrakt,
            hasSimkl = hasSimkl,
            hasMdbList = hasMdbList,
            replacement = replacement
        )
        repaired(storedWatchlistMode).takeIf { it != storedWatchlistMode }
            ?.let { setReadMode(TrackingFeature.WATCHLIST, it, profileId) }
        repaired(storedContinueMode).takeIf { it != storedContinueMode }
            ?.let { setReadMode(TrackingFeature.CONTINUE_WATCHING, it, profileId) }
        repaired(storedWatchedMode).takeIf { it != storedWatchedMode }
            ?.let { setReadMode(TrackingFeature.WATCHED, it, profileId) }
        if (provider == SyncProvider.TRAKT) setWriteTarget(SyncProvider.TRAKT, true, profileId)
        if (provider == SyncProvider.SIMKL) setWriteTarget(SyncProvider.SIMKL, true, profileId)
    }

    suspend fun onProviderDisconnected(provider: SyncProvider) {
        onProviderDisconnected(provider, profileManager.getProfileIdSync())
    }

    suspend fun onProviderDisconnected(provider: SyncProvider, profileId: String) {
        setWriteTarget(provider, false, profileId)
        val credentials = context.traktDataStore.data.first()
        val hasTrakt = !credentials[profileManager.profileStringKeyFor(profileId, "trakt_access_token")].isNullOrBlank()
        val hasSimkl = simklV2?.isConnected(profileId) == true || !SecureStorage.decrypt(credentials[simklAccessTokenKeyFor(profileId)], SIMKL_TOKEN_ALIAS).isNullOrBlank()
        val hasMdbList = !credentials[mdbListKeyFor(profileId)].isNullOrBlank() || !credentials[mdbListAccessTokenKeyFor(profileId)].isNullOrBlank()
        val settings = context.settingsDataStore.data.first()
        val replacement = defaultTrackingReadMode(
            hasTrakt = hasTrakt,
            hasSimkl = hasSimkl,
            hasMdbList = hasMdbList,
            preferredProvider = SyncProvider.fromStorage(settings[providerKeyFor(profileId)])
        )
        val current = TrackingPreferences(
            watchlistReadMode = TrackingReadMode.fromStorage(settings[watchlistReadModeKeyFor(profileId)]),
            continueWatchingReadMode = TrackingReadMode.fromStorage(settings[continueWatchingReadModeKeyFor(profileId)]),
            watchedReadMode = TrackingReadMode.fromStorage(settings[watchedReadModeKeyFor(profileId)])
        )
        val affected = when (provider) {
            SyncProvider.TRAKT -> setOf(TrackingReadMode.TRAKT, TrackingReadMode.BOTH)
            SyncProvider.SIMKL -> setOf(TrackingReadMode.SIMKL, TrackingReadMode.BOTH)
            SyncProvider.MDBLIST -> setOf(TrackingReadMode.MDBLIST)
            SyncProvider.NONE -> emptySet()
        }
        if (current.watchlistReadMode in affected) setReadMode(TrackingFeature.WATCHLIST, replacement, profileId)
        if (current.continueWatchingReadMode in affected) setReadMode(TrackingFeature.CONTINUE_WATCHING, replacement, profileId)
        if (current.watchedReadMode in affected) setReadMode(TrackingFeature.WATCHED, replacement, profileId)
        if (provider == SyncProvider.SIMKL) {
            context.traktDataStore.edit { it.remove(simklWatermarkKeyFor(profileId)) }
        }
        setProvider(
            when {
                hasTrakt -> SyncProvider.TRAKT
                hasSimkl -> SyncProvider.SIMKL
                hasMdbList -> SyncProvider.MDBLIST
                else -> SyncProvider.NONE
            },
            profileId
        )
    }

    private fun simklAccessTokenKey() = profileManager.profileStringKey("simkl_access_token")
    private fun simklAccessTokenKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "simkl_access_token")
    private fun simklWatermarkKey() = profileManager.profileStringKey("simkl_sync_watermark")
    private fun simklWatermarkKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "simkl_sync_watermark")

    suspend fun getSimklAccessToken(): String? {
        if (simklV2?.isConnected() == true) return simklV2.accessToken()
        val prefs = context.traktDataStore.data.first()
        val stored = prefs[simklAccessTokenKey()]
        val token = SecureStorage.decrypt(stored, SIMKL_TOKEN_ALIAS)
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
        if (token != null && !SecureStorage.isEncrypted(stored)) {
            context.traktDataStore.edit { current ->
                current[simklAccessTokenKey()] = SecureStorage.encrypt(token, SIMKL_TOKEN_ALIAS)
            }
        }
        return token
    }

    suspend fun setSimklAccessToken(token: String?) {
        val updatedAt = System.currentTimeMillis()
        context.traktDataStore.edit { prefs ->
            val trimmed = token?.trim().orEmpty()
            if (trimmed.isEmpty()) {
                prefs.remove(simklAccessTokenKey())
                prefs.remove(simklWatermarkKey())
            } else {
                prefs[simklAccessTokenKey()] = SecureStorage.encrypt(trimmed, SIMKL_TOKEN_ALIAS)
            }
        }
        context.settingsDataStore.edit { prefs ->
            prefs[simklCredentialUpdatedAtKey()] = updatedAt
        }
    }

    suspend fun getSimklWatermark(): String? {
        val prefs = context.traktDataStore.data.first()
        return prefs[simklWatermarkKey()]?.trim()?.takeIf { it.isNotEmpty() }
    }

    suspend fun setSimklWatermark(watermark: String?) {
        context.traktDataStore.edit { prefs ->
            val trimmed = watermark?.trim().orEmpty()
            if (trimmed.isEmpty()) {
                prefs.remove(simklWatermarkKey())
            } else {
                prefs[simklWatermarkKey()] = trimmed
            }
        }
    }

    sealed interface MdbListCredential {
        data class OAuth(
            val accessToken: String,
            val refreshToken: String? = null,
            val expiresAt: Long? = null
        ) : MdbListCredential

        data class ApiKey(
            val apiKey: String
        ) : MdbListCredential
    }

    suspend fun getMdbListCredential(profileId: String? = null): MdbListCredential? {
        val prefs = context.traktDataStore.data.first()
        val accessKey = if (profileId != null) mdbListAccessTokenKeyFor(profileId) else mdbListAccessTokenKey()
        val token = prefs[accessKey]?.trim()?.takeIf { it.isNotEmpty() }
        if (token != null) {
            val refreshKey = if (profileId != null) mdbListRefreshTokenKeyFor(profileId) else mdbListRefreshTokenKey()
            val expiresKey = if (profileId != null) mdbListTokenExpiresAtKeyFor(profileId) else mdbListTokenExpiresAtKey()
            return MdbListCredential.OAuth(
                accessToken = token,
                refreshToken = prefs[refreshKey]?.trim()?.takeIf { it.isNotEmpty() },
                expiresAt = prefs[expiresKey]
            )
        }
        val legacyKey = if (profileId != null) mdbListKeyFor(profileId) else mdbListKey()
        val key = prefs[legacyKey]?.trim()?.takeIf { it.isNotEmpty() }
        return key?.let { MdbListCredential.ApiKey(it) }
    }

    suspend fun getMdbListAuthToken(profileId: String? = null): String? {
        val prefs = context.traktDataStore.data.first()
        val accessKey = if (profileId != null) mdbListAccessTokenKeyFor(profileId) else mdbListAccessTokenKey()
        val token = prefs[accessKey]?.trim()?.takeIf { it.isNotEmpty() }
        if (token != null) return token
        val legacyKey = if (profileId != null) mdbListKeyFor(profileId) else mdbListKey()
        return prefs[legacyKey]?.trim()?.takeIf { it.isNotEmpty() }
    }

    suspend fun getMdbListAccessToken(profileId: String? = null): String? {
        val prefs = context.traktDataStore.data.first()
        val accessKey = if (profileId != null) mdbListAccessTokenKeyFor(profileId) else mdbListAccessTokenKey()
        return prefs[accessKey]?.trim()?.takeIf { it.isNotEmpty() }
    }

    suspend fun getMdbListRefreshToken(profileId: String? = null): String? {
        val prefs = context.traktDataStore.data.first()
        val refreshKey = if (profileId != null) mdbListRefreshTokenKeyFor(profileId) else mdbListRefreshTokenKey()
        return prefs[refreshKey]?.trim()?.takeIf { it.isNotEmpty() }
    }

    suspend fun getMdbListTokenExpiresAt(profileId: String? = null): Long? {
        val prefs = context.traktDataStore.data.first()
        val expiresKey = if (profileId != null) mdbListTokenExpiresAtKeyFor(profileId) else mdbListTokenExpiresAtKey()
        return prefs[expiresKey]
    }

    suspend fun getMdbListApiKey(profileId: String? = null): String? {
        val prefs = context.traktDataStore.data.first()
        val legacyKey = if (profileId != null) mdbListKeyFor(profileId) else mdbListKey()
        return prefs[legacyKey]?.trim()?.takeIf { it.isNotEmpty() }
    }

    suspend fun setMdbListOAuthTokens(
        accessToken: String?,
        refreshToken: String?,
        expiresInSeconds: Long?,
        profileId: String? = null,
        expected: MdbListCredential.OAuth? = null
    ): Boolean {
        val updatedAt = System.currentTimeMillis()
        val expiresAt = expiresInSeconds?.let { updatedAt + (it * 1000L) }
        val accessKey = if (profileId != null) mdbListAccessTokenKeyFor(profileId) else mdbListAccessTokenKey()
        val refreshKey = if (profileId != null) mdbListRefreshTokenKeyFor(profileId) else mdbListRefreshTokenKey()
        val expiresKey = if (profileId != null) mdbListTokenExpiresAtKeyFor(profileId) else mdbListTokenExpiresAtKey()
        val legacyKey = if (profileId != null) mdbListKeyFor(profileId) else mdbListKey()
        val updatedKey = if (profileId != null) mdbListCredentialUpdatedAtKeyFor(profileId) else mdbListCredentialUpdatedAtKey()

        var applied = false
        context.traktDataStore.edit { prefs ->
            // A refresh must not overwrite a disconnect, cloud restore, or another login.
            if (expected != null && (prefs[accessKey] != expected.accessToken ||
                    prefs[refreshKey] != expected.refreshToken || prefs[expiresKey] != expected.expiresAt)) {
                return@edit
            }
            applied = true
            val cleanAccess = accessToken?.trim().orEmpty()
            val cleanRefresh = refreshToken?.trim().orEmpty()
            if (cleanAccess.isEmpty()) {
                prefs.remove(accessKey)
                prefs.remove(refreshKey)
                prefs.remove(expiresKey)
            } else {
                prefs[accessKey] = cleanAccess
                if (cleanRefresh.isNotEmpty()) {
                    prefs[refreshKey] = cleanRefresh
                } else prefs.remove(refreshKey)
                if (expiresAt != null) {
                    prefs[expiresKey] = expiresAt
                } else prefs.remove(expiresKey)
                prefs.remove(legacyKey)
            }
        }
        if (applied) context.settingsDataStore.edit { prefs ->
            prefs[updatedKey] = updatedAt
        }
        return applied
    }

    suspend fun setMdbListApiKey(apiKey: String?, profileId: String? = null) {
        val updatedAt = System.currentTimeMillis()
        val accessKey = if (profileId != null) mdbListAccessTokenKeyFor(profileId) else mdbListAccessTokenKey()
        val refreshKey = if (profileId != null) mdbListRefreshTokenKeyFor(profileId) else mdbListRefreshTokenKey()
        val expiresKey = if (profileId != null) mdbListTokenExpiresAtKeyFor(profileId) else mdbListTokenExpiresAtKey()
        val legacyKey = if (profileId != null) mdbListKeyFor(profileId) else mdbListKey()
        val updatedKey = if (profileId != null) mdbListCredentialUpdatedAtKeyFor(profileId) else mdbListCredentialUpdatedAtKey()

        context.traktDataStore.edit { prefs ->
            val trimmed = apiKey?.trim().orEmpty()
            if (trimmed.isEmpty()) {
                prefs.remove(legacyKey)
                prefs.remove(accessKey)
                prefs.remove(refreshKey)
                prefs.remove(expiresKey)
            } else {
                prefs[legacyKey] = trimmed
                prefs.remove(accessKey)
                prefs.remove(refreshKey)
                prefs.remove(expiresKey)
            }
        }
        context.settingsDataStore.edit { prefs ->
            prefs[updatedKey] = updatedAt
        }
    }

    suspend fun clearAllMdbListCredentials(profileId: String? = null) {
        val updatedAt = System.currentTimeMillis()
        val accessKey = if (profileId != null) mdbListAccessTokenKeyFor(profileId) else mdbListAccessTokenKey()
        val refreshKey = if (profileId != null) mdbListRefreshTokenKeyFor(profileId) else mdbListRefreshTokenKey()
        val expiresKey = if (profileId != null) mdbListTokenExpiresAtKeyFor(profileId) else mdbListTokenExpiresAtKey()
        val legacyKey = if (profileId != null) mdbListKeyFor(profileId) else mdbListKey()
        val updatedKey = if (profileId != null) mdbListCredentialUpdatedAtKeyFor(profileId) else mdbListCredentialUpdatedAtKey()

        context.traktDataStore.edit { prefs ->
            prefs.remove(legacyKey)
            prefs.remove(accessKey)
            prefs.remove(refreshKey)
            prefs.remove(expiresKey)
        }
        context.settingsDataStore.edit { prefs ->
            prefs[updatedKey] = updatedAt
        }
    }

    // ===== Cloud backup / restore (per profile) =====

    suspend fun exportForProfiles(profileIds: List<String>): Map<String, ProfileSyncSelection> {
        val settingsPrefs = context.settingsDataStore.data.first()
        val traktPrefs = context.traktDataStore.data.first()
        val out = LinkedHashMap<String, ProfileSyncSelection>()
        val migratedTrackingProfiles = mutableListOf<String>()
        val migratedSimklProfiles = mutableListOf<String>()
        val migratedMdbListProfiles = mutableListOf<String>()
        val migrationUpdatedAt = System.currentTimeMillis()
        profileIds.forEach { profileId ->
            val provider = SyncProvider.fromStorage(settingsPrefs[providerKeyFor(profileId)])
            val legacyKey = traktPrefs[mdbListKeyFor(profileId)]?.trim()?.takeIf { it.isNotEmpty() }
            val oAuthAccessToken = traktPrefs[mdbListAccessTokenKeyFor(profileId)]?.trim()?.takeIf { it.isNotEmpty() }
            val oAuthRefreshToken = traktPrefs[mdbListRefreshTokenKeyFor(profileId)]?.trim()?.takeIf { it.isNotEmpty() }
            val oAuthExpiresAt = traktPrefs[mdbListTokenExpiresAtKeyFor(profileId)]
            val hasMdbListCred = legacyKey != null || oAuthAccessToken != null

            val simklToken = SecureStorage.decrypt(
                traktPrefs[simklAccessTokenKeyFor(profileId)],
                SIMKL_TOKEN_ALIAS
            )?.trim()?.takeIf { it.isNotEmpty() }
            val watchlistMode = TrackingReadMode.fromStorage(settingsPrefs[watchlistReadModeKeyFor(profileId)])
            val continueMode = TrackingReadMode.fromStorage(settingsPrefs[continueWatchingReadModeKeyFor(profileId)])
            val watchedMode = TrackingReadMode.fromStorage(settingsPrefs[watchedReadModeKeyFor(profileId)])
            val writeTrakt = settingsPrefs[writeToTraktKeyFor(profileId)]
            val writeSimkl = settingsPrefs[writeToSimklKeyFor(profileId)]
            val hasExplicitTrackingSelection = provider != SyncProvider.NONE ||
                watchlistMode != TrackingReadMode.AUTO || continueMode != TrackingReadMode.AUTO ||
                watchedMode != TrackingReadMode.AUTO || writeTrakt != null || writeSimkl != null
            val updatedAt = settingsPrefs[trackingUpdatedAtKeyFor(profileId)]
                ?.takeIf { it > 0L }
                ?: hasExplicitTrackingSelection.takeIf { it }?.let {
                    migratedTrackingProfiles += profileId
                    migrationUpdatedAt
                }
            val simklCredentialUpdatedAt = settingsPrefs[simklCredentialUpdatedAtKeyFor(profileId)]
                ?.takeIf { it > 0L }
                ?: simklToken?.let {
                    migratedSimklProfiles += profileId
                    migrationUpdatedAt
                }
            val mdbListCredentialUpdatedAt = settingsPrefs[mdbListCredentialUpdatedAtKeyFor(profileId)]
                ?.takeIf { it > 0L }
                ?: (if (hasMdbListCred) {
                    migratedMdbListProfiles += profileId
                    migrationUpdatedAt
                } else null)
            if (provider != SyncProvider.NONE || hasMdbListCred || simklToken != null ||
                watchlistMode != TrackingReadMode.AUTO || continueMode != TrackingReadMode.AUTO ||
                watchedMode != TrackingReadMode.AUTO || writeTrakt != null || writeSimkl != null ||
                updatedAt != null || simklCredentialUpdatedAt != null ||
                mdbListCredentialUpdatedAt != null
            ) {
                out[profileId] = ProfileSyncSelection(
                    provider = provider,
                    mdbListApiKey = legacyKey,
                    mdbListAccessToken = oAuthAccessToken,
                    mdbListRefreshToken = oAuthRefreshToken,
                    mdbListTokenExpiresAt = oAuthExpiresAt,
                    simklAccessToken = simklToken,
                    watchlistReadMode = watchlistMode,
                    continueWatchingReadMode = continueMode,
                    watchedReadMode = watchedMode,
                    writeToTrakt = writeTrakt,
                    writeToSimkl = writeSimkl,
                    updatedAt = updatedAt,
                    simklCredentialUpdatedAt = simklCredentialUpdatedAt,
                    mdbListCredentialUpdatedAt = mdbListCredentialUpdatedAt
                )
            }
        }
        if (migratedTrackingProfiles.isNotEmpty() || migratedSimklProfiles.isNotEmpty() ||
            migratedMdbListProfiles.isNotEmpty()
        ) {
            context.settingsDataStore.edit { prefs ->
                migratedTrackingProfiles.forEach { profileId ->
                    prefs[trackingUpdatedAtKeyFor(profileId)] = migrationUpdatedAt
                }
                migratedSimklProfiles.forEach { profileId ->
                    prefs[simklCredentialUpdatedAtKeyFor(profileId)] = migrationUpdatedAt
                }
                migratedMdbListProfiles.forEach { profileId ->
                    prefs[mdbListCredentialUpdatedAtKeyFor(profileId)] = migrationUpdatedAt
                }
            }
        }
        return out
    }

    suspend fun importForProfiles(values: Map<String, ProfileSyncSelection>) {
        if (values.isEmpty()) return
        val localSettings = context.settingsDataStore.data.first()
        val localCredentials = context.traktDataStore.data.first()
        val preferenceValuesToApply = values.filter { (profileId, selection) ->
            val localProvider = SyncProvider.fromStorage(localSettings[providerKeyFor(profileId)])
            val localWatchlistMode = TrackingReadMode.fromStorage(localSettings[watchlistReadModeKeyFor(profileId)])
            val localContinueMode = TrackingReadMode.fromStorage(localSettings[continueWatchingReadModeKeyFor(profileId)])
            val localWatchedMode = TrackingReadMode.fromStorage(localSettings[watchedReadModeKeyFor(profileId)])
            val hasLocalSelection = localProvider != SyncProvider.NONE ||
                localWatchlistMode != TrackingReadMode.AUTO ||
                localContinueMode != TrackingReadMode.AUTO ||
                localWatchedMode != TrackingReadMode.AUTO ||
                localSettings[writeToTraktKeyFor(profileId)] != null ||
                localSettings[writeToSimklKeyFor(profileId)] != null
            shouldApplyCloudTrackingSelection(
                incomingUpdatedAt = selection.updatedAt,
                localUpdatedAt = localSettings[trackingUpdatedAtKeyFor(profileId)],
                hasLocalSelection = hasLocalSelection
            )
        }
        val simklValuesToApply = values.filter { (profileId, selection) ->
            val localToken = SecureStorage.decrypt(
                localCredentials[simklAccessTokenKeyFor(profileId)],
                SIMKL_TOKEN_ALIAS
            )?.trim().orEmpty()
            shouldApplyCloudCredential(
                incomingUpdatedAt = selection.simklCredentialUpdatedAt,
                localUpdatedAt = localSettings[simklCredentialUpdatedAtKeyFor(profileId)],
                incomingHasCredential = !selection.simklAccessToken.isNullOrBlank(),
                localHasCredential = localToken.isNotEmpty()
            )
        }
        val mdbListValuesToApply = values.filter { (profileId, selection) ->
            val incomingHasCred = !selection.mdbListApiKey.isNullOrBlank() ||
                !selection.mdbListAccessToken.isNullOrBlank()
            val localHasCred = !localCredentials[mdbListKeyFor(profileId)].isNullOrBlank() ||
                !localCredentials[mdbListAccessTokenKeyFor(profileId)].isNullOrBlank()
            shouldApplyCloudCredential(
                incomingUpdatedAt = selection.mdbListCredentialUpdatedAt,
                localUpdatedAt = localSettings[mdbListCredentialUpdatedAtKeyFor(profileId)],
                incomingHasCredential = incomingHasCred,
                localHasCredential = localHasCred
            )
        }
        if (preferenceValuesToApply.isEmpty() && simklValuesToApply.isEmpty() &&
            mdbListValuesToApply.isEmpty()
        ) return
        context.settingsDataStore.edit { prefs ->
            preferenceValuesToApply.forEach { (profileId, selection) ->
                if (selection.provider == SyncProvider.NONE) {
                    prefs.remove(providerKeyFor(profileId))
                } else {
                    prefs[providerKeyFor(profileId)] = selection.provider.toStorage()
                }
                fun storeMode(key: androidx.datastore.preferences.core.Preferences.Key<String>, mode: TrackingReadMode) {
                    if (mode == TrackingReadMode.AUTO) prefs.remove(key) else prefs[key] = mode.toStorage()
                }
                selection.watchlistReadMode?.let { storeMode(watchlistReadModeKeyFor(profileId), it) }
                selection.continueWatchingReadMode?.let { storeMode(continueWatchingReadModeKeyFor(profileId), it) }
                selection.watchedReadMode?.let { storeMode(watchedReadModeKeyFor(profileId), it) }
                selection.writeToTrakt?.let { prefs[writeToTraktKeyFor(profileId)] = it }
                selection.writeToSimkl?.let { prefs[writeToSimklKeyFor(profileId)] = it }
                selection.updatedAt?.takeIf { it > 0L }?.let {
                    prefs[trackingUpdatedAtKeyFor(profileId)] = it
                }
            }
            simklValuesToApply.forEach { (profileId, selection) ->
                selection.simklCredentialUpdatedAt?.takeIf { it > 0L }?.let {
                    prefs[simklCredentialUpdatedAtKeyFor(profileId)] = it
                }
            }
            mdbListValuesToApply.forEach { (profileId, selection) ->
                selection.mdbListCredentialUpdatedAt?.takeIf { it > 0L }?.let {
                    prefs[mdbListCredentialUpdatedAtKeyFor(profileId)] = it
                }
            }
        }
        context.traktDataStore.edit { prefs ->
            mdbListValuesToApply.forEach { (profileId, selection) ->
                val apiKey = selection.mdbListApiKey?.trim().orEmpty()
                val accessToken = selection.mdbListAccessToken?.trim().orEmpty()
                val refreshToken = selection.mdbListRefreshToken?.trim().orEmpty()
                val expiresAt = selection.mdbListTokenExpiresAt

                if (accessToken.isNotEmpty()) {
                    prefs[mdbListAccessTokenKeyFor(profileId)] = accessToken
                    if (refreshToken.isNotEmpty()) {
                        prefs[mdbListRefreshTokenKeyFor(profileId)] = refreshToken
                    } else {
                        prefs.remove(mdbListRefreshTokenKeyFor(profileId))
                    }
                    if (expiresAt != null && expiresAt > 0L) {
                        prefs[mdbListTokenExpiresAtKeyFor(profileId)] = expiresAt
                    } else {
                        prefs.remove(mdbListTokenExpiresAtKeyFor(profileId))
                    }
                    prefs.remove(mdbListKeyFor(profileId))
                } else if (apiKey.isNotEmpty()) {
                    prefs[mdbListKeyFor(profileId)] = apiKey
                    prefs.remove(mdbListAccessTokenKeyFor(profileId))
                    prefs.remove(mdbListRefreshTokenKeyFor(profileId))
                    prefs.remove(mdbListTokenExpiresAtKeyFor(profileId))
                } else {
                    // Complete disconnect in cloud: clear both API key and OAuth tokens
                    prefs.remove(mdbListKeyFor(profileId))
                    prefs.remove(mdbListAccessTokenKeyFor(profileId))
                    prefs.remove(mdbListRefreshTokenKeyFor(profileId))
                    prefs.remove(mdbListTokenExpiresAtKeyFor(profileId))
                }
            }
            simklValuesToApply.forEach { (profileId, selection) ->
                val simklToken = selection.simklAccessToken?.trim().orEmpty()
                if (simklToken.isEmpty()) {
                    prefs.remove(simklAccessTokenKeyFor(profileId))
                } else {
                    prefs[simklAccessTokenKeyFor(profileId)] =
                        SecureStorage.encrypt(simklToken, SIMKL_TOKEN_ALIAS)
                }
            }
        }
    }

    data class ProfileSyncSelection(
        val provider: SyncProvider,
        val mdbListApiKey: String? = null,
        val mdbListAccessToken: String? = null,
        val mdbListRefreshToken: String? = null,
        val mdbListTokenExpiresAt: Long? = null,
        val simklAccessToken: String? = null,
        val watchlistReadMode: TrackingReadMode? = null,
        val continueWatchingReadMode: TrackingReadMode? = null,
        val watchedReadMode: TrackingReadMode? = null,
        val writeToTrakt: Boolean? = null,
        val writeToSimkl: Boolean? = null,
        val updatedAt: Long? = null,
        val simklCredentialUpdatedAt: Long? = null,
        val mdbListCredentialUpdatedAt: Long? = null
    )
}
