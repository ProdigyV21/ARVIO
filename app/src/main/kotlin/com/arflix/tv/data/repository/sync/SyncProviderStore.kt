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
    private val profileManager: ProfileManager
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
        context.settingsDataStore.edit { prefs ->
            if (provider == SyncProvider.NONE) {
                prefs.remove(providerKey())
            } else {
                prefs[providerKey()] = provider.toStorage()
            }
            prefs[trackingUpdatedAtKey()] = System.currentTimeMillis()
        }
    }

    suspend fun getTrackingPreferences(): TrackingPreferences {
        val settings = context.settingsDataStore.data.first()
        val credentials = context.traktDataStore.data.first()
        val hasTrakt = !credentials[traktAccessTokenKey()].isNullOrBlank()
        val hasSimkl = !SecureStorage.decrypt(credentials[simklAccessTokenKey()], SIMKL_TOKEN_ALIAS).isNullOrBlank()
        val hasMdbList = !credentials[mdbListKey()].isNullOrBlank()
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
        context.settingsDataStore.edit { prefs ->
            val key = when (feature) {
                TrackingFeature.WATCHLIST -> watchlistReadModeKey()
                TrackingFeature.CONTINUE_WATCHING -> continueWatchingReadModeKey()
                TrackingFeature.WATCHED -> watchedReadModeKey()
            }
            if (mode == TrackingReadMode.AUTO) prefs.remove(key) else prefs[key] = mode.toStorage()
            prefs[trackingUpdatedAtKey()] = System.currentTimeMillis()
        }
    }

    suspend fun setWriteTarget(provider: SyncProvider, enabled: Boolean) {
        context.settingsDataStore.edit { prefs ->
            when (provider) {
                SyncProvider.TRAKT -> prefs[writeToTraktKey()] = enabled
                SyncProvider.SIMKL -> prefs[writeToSimklKey()] = enabled
                else -> Unit
            }
            if (provider == SyncProvider.TRAKT || provider == SyncProvider.SIMKL) {
                prefs[trackingUpdatedAtKey()] = System.currentTimeMillis()
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
        val hasMdbList = !credentials[mdbListKey()].isNullOrBlank()
        return buildSet {
            if (preferences.writeToTrakt == true) add(SyncProvider.TRAKT)
            if (preferences.writeToSimkl == true) add(SyncProvider.SIMKL)
            if (hasMdbList) add(SyncProvider.MDBLIST)
        }
    }

    suspend fun onProviderConnected(provider: SyncProvider) {
        val settings = context.settingsDataStore.data.first()
        val credentials = context.traktDataStore.data.first()
        val hasTrakt = !credentials[traktAccessTokenKey()].isNullOrBlank()
        val hasSimkl = !SecureStorage.decrypt(credentials[simklAccessTokenKey()], SIMKL_TOKEN_ALIAS).isNullOrBlank()
        val hasMdbList = !credentials[mdbListKey()].isNullOrBlank()
        val currentProvider = SyncProvider.fromStorage(settings[providerKey()])
        val currentProviderStillConnected = when (currentProvider) {
            SyncProvider.TRAKT -> hasTrakt
            SyncProvider.SIMKL -> hasSimkl
            SyncProvider.MDBLIST -> hasMdbList
            SyncProvider.NONE -> false
        }
        if (!currentProviderStillConnected) setProvider(provider)

        val replacement = defaultTrackingReadMode(
            hasTrakt = hasTrakt,
            hasSimkl = hasSimkl,
            hasMdbList = hasMdbList,
            preferredProvider = currentProvider.takeIf { currentProviderStillConnected } ?: provider
        )
        val storedWatchlistMode = TrackingReadMode.fromStorage(settings[watchlistReadModeKey()])
        val storedContinueMode = TrackingReadMode.fromStorage(settings[continueWatchingReadModeKey()])
        val storedWatchedMode = TrackingReadMode.fromStorage(settings[watchedReadModeKey()])
        fun repaired(mode: TrackingReadMode) = repairUnavailableTrackingReadMode(
            mode = mode,
            hasTrakt = hasTrakt,
            hasSimkl = hasSimkl,
            hasMdbList = hasMdbList,
            replacement = replacement
        )
        repaired(storedWatchlistMode).takeIf { it != storedWatchlistMode }
            ?.let { setReadMode(TrackingFeature.WATCHLIST, it) }
        repaired(storedContinueMode).takeIf { it != storedContinueMode }
            ?.let { setReadMode(TrackingFeature.CONTINUE_WATCHING, it) }
        repaired(storedWatchedMode).takeIf { it != storedWatchedMode }
            ?.let { setReadMode(TrackingFeature.WATCHED, it) }
        if (provider == SyncProvider.TRAKT) setWriteTarget(SyncProvider.TRAKT, true)
        if (provider == SyncProvider.SIMKL) setWriteTarget(SyncProvider.SIMKL, true)
    }

    suspend fun onProviderDisconnected(provider: SyncProvider) {
        setWriteTarget(provider, false)
        val credentials = context.traktDataStore.data.first()
        val hasTrakt = !credentials[traktAccessTokenKey()].isNullOrBlank()
        val hasSimkl = !SecureStorage.decrypt(credentials[simklAccessTokenKey()], SIMKL_TOKEN_ALIAS).isNullOrBlank()
        val hasMdbList = !credentials[mdbListKey()].isNullOrBlank()
        val settings = context.settingsDataStore.data.first()
        val replacement = defaultTrackingReadMode(
            hasTrakt = hasTrakt,
            hasSimkl = hasSimkl,
            hasMdbList = hasMdbList,
            preferredProvider = SyncProvider.fromStorage(settings[providerKey()])
        )
        val current = TrackingPreferences(
            watchlistReadMode = TrackingReadMode.fromStorage(settings[watchlistReadModeKey()]),
            continueWatchingReadMode = TrackingReadMode.fromStorage(settings[continueWatchingReadModeKey()]),
            watchedReadMode = TrackingReadMode.fromStorage(settings[watchedReadModeKey()])
        )
        val affected = when (provider) {
            SyncProvider.TRAKT -> setOf(TrackingReadMode.TRAKT, TrackingReadMode.BOTH)
            SyncProvider.SIMKL -> setOf(TrackingReadMode.SIMKL, TrackingReadMode.BOTH)
            SyncProvider.MDBLIST -> setOf(TrackingReadMode.MDBLIST)
            SyncProvider.NONE -> emptySet()
        }
        if (current.watchlistReadMode in affected) setReadMode(TrackingFeature.WATCHLIST, replacement)
        if (current.continueWatchingReadMode in affected) setReadMode(TrackingFeature.CONTINUE_WATCHING, replacement)
        if (current.watchedReadMode in affected) setReadMode(TrackingFeature.WATCHED, replacement)
        if (provider == SyncProvider.SIMKL) {
            setSimklWatermark(null)
        }
        setProvider(
            when {
                hasTrakt -> SyncProvider.TRAKT
                hasSimkl -> SyncProvider.SIMKL
                hasMdbList -> SyncProvider.MDBLIST
                else -> SyncProvider.NONE
            }
        )
    }

    private fun simklAccessTokenKey() = profileManager.profileStringKey("simkl_access_token")
    private fun simklAccessTokenKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "simkl_access_token")
    private fun simklWatermarkKey() = profileManager.profileStringKey("simkl_sync_watermark")
    private fun simklWatermarkKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "simkl_sync_watermark")

    suspend fun getSimklAccessToken(): String? {
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

    suspend fun getMdbListApiKey(): String? {
        val prefs = context.traktDataStore.data.first()
        return prefs[mdbListKey()]?.trim()?.takeIf { it.isNotEmpty() }
    }

    suspend fun setMdbListApiKey(apiKey: String?) {
        val updatedAt = System.currentTimeMillis()
        context.traktDataStore.edit { prefs ->
            val trimmed = apiKey?.trim().orEmpty()
            if (trimmed.isEmpty()) {
                prefs.remove(mdbListKey())
            } else {
                prefs[mdbListKey()] = trimmed
            }
        }
        context.settingsDataStore.edit { prefs ->
            prefs[mdbListCredentialUpdatedAtKey()] = updatedAt
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
            val key = traktPrefs[mdbListKeyFor(profileId)]?.trim()?.takeIf { it.isNotEmpty() }
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
                ?: key?.let {
                    migratedMdbListProfiles += profileId
                    migrationUpdatedAt
                }
            if (provider != SyncProvider.NONE || key != null || simklToken != null ||
                watchlistMode != TrackingReadMode.AUTO || continueMode != TrackingReadMode.AUTO ||
                watchedMode != TrackingReadMode.AUTO || writeTrakt != null || writeSimkl != null ||
                updatedAt != null || simklCredentialUpdatedAt != null ||
                mdbListCredentialUpdatedAt != null
            ) {
                out[profileId] = ProfileSyncSelection(
                    provider = provider,
                    mdbListApiKey = key,
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
            shouldApplyCloudCredential(
                incomingUpdatedAt = selection.mdbListCredentialUpdatedAt,
                localUpdatedAt = localSettings[mdbListCredentialUpdatedAtKeyFor(profileId)],
                incomingHasCredential = !selection.mdbListApiKey.isNullOrBlank(),
                localHasCredential = !localCredentials[mdbListKeyFor(profileId)].isNullOrBlank()
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
                val key = selection.mdbListApiKey?.trim().orEmpty()
                if (key.isEmpty()) {
                    prefs.remove(mdbListKeyFor(profileId))
                } else {
                    prefs[mdbListKeyFor(profileId)] = key
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
