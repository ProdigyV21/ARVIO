package com.arflix.tv.data.repository.sync

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.traktDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Owns the per-profile choice of remote sync provider and the MDBList API key.
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
    private fun providerKey() = profileManager.profileStringKey("sync_provider")
    private fun providerKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "sync_provider")
    private fun mdbListKey() = profileManager.profileStringKey("mdblist_api_key")
    private fun mdbListKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "mdblist_api_key")

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
        }
    }

    private fun simklAccessTokenKey() = profileManager.profileStringKey("simkl_access_token")
    private fun simklAccessTokenKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, "simkl_access_token")

    suspend fun getSimklAccessToken(): String? {
        val prefs = context.traktDataStore.data.first()
        return prefs[simklAccessTokenKey()]?.trim()?.takeIf { it.isNotEmpty() }
    }

    suspend fun setSimklAccessToken(token: String?) {
        context.traktDataStore.edit { prefs ->
            val trimmed = token?.trim().orEmpty()
            if (trimmed.isEmpty()) {
                prefs.remove(simklAccessTokenKey())
            } else {
                prefs[simklAccessTokenKey()] = trimmed
            }
        }
    }

    suspend fun getMdbListApiKey(): String? {
        val prefs = context.traktDataStore.data.first()
        return prefs[mdbListKey()]?.trim()?.takeIf { it.isNotEmpty() }
    }

    suspend fun setMdbListApiKey(apiKey: String?) {
        context.traktDataStore.edit { prefs ->
            val trimmed = apiKey?.trim().orEmpty()
            if (trimmed.isEmpty()) {
                prefs.remove(mdbListKey())
            } else {
                prefs[mdbListKey()] = trimmed
            }
        }
    }

    // ===== Cloud backup / restore (per profile) =====

    suspend fun exportForProfiles(profileIds: List<String>): Map<String, ProfileSyncSelection> {
        val settingsPrefs = context.settingsDataStore.data.first()
        val traktPrefs = context.traktDataStore.data.first()
        val out = LinkedHashMap<String, ProfileSyncSelection>()
        profileIds.forEach { profileId ->
            val provider = SyncProvider.fromStorage(settingsPrefs[providerKeyFor(profileId)])
            val key = traktPrefs[mdbListKeyFor(profileId)]?.trim()?.takeIf { it.isNotEmpty() }
            if (provider != SyncProvider.NONE || key != null) {
                out[profileId] = ProfileSyncSelection(provider, key)
            }
        }
        return out
    }

    suspend fun importForProfiles(values: Map<String, ProfileSyncSelection>) {
        if (values.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            values.forEach { (profileId, selection) ->
                if (selection.provider == SyncProvider.NONE) {
                    prefs.remove(providerKeyFor(profileId))
                } else {
                    prefs[providerKeyFor(profileId)] = selection.provider.toStorage()
                }
            }
        }
        context.traktDataStore.edit { prefs ->
            values.forEach { (profileId, selection) ->
                val key = selection.mdbListApiKey?.trim().orEmpty()
                if (key.isEmpty()) {
                    prefs.remove(mdbListKeyFor(profileId))
                } else {
                    prefs[mdbListKeyFor(profileId)] = key
                }
            }
        }
    }

    data class ProfileSyncSelection(
        val provider: SyncProvider,
        val mdbListApiKey: String?
    )
}
