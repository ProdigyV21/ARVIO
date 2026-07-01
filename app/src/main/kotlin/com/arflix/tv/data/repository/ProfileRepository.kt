package com.arflix.tv.data.repository

import com.arflix.tv.util.AppLogger

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.ProfileColors
import com.arflix.tv.util.profilesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authRepository: AuthRepository,
    private val profileAvatarImageManager: ProfileAvatarImageManager,
    private val invalidationBus: CloudSyncInvalidationBus
) {
    private val gson = Gson()
    private val profileListType = TypeToken.getParameterized(List::class.java, Profile::class.java).type

    companion object {
        private val PROFILES_KEY = stringPreferencesKey("profiles")
        private val ACTIVE_PROFILE_KEY = stringPreferencesKey("active_profile_id")
    }

    /**
     * Flow of all profiles
     */
    val profiles: Flow<List<Profile>> = context.profilesDataStore.data.map { prefs ->
        decodeProfiles(prefs[PROFILES_KEY])
    }

    /**
     * Flow of the active profile ID
     */
    val activeProfileId: Flow<String?> = context.profilesDataStore.data.map { prefs ->
        prefs[ACTIVE_PROFILE_KEY]
    }

    /**
     * Flow of the active profile
     */
    val activeProfile: Flow<Profile?> = context.profilesDataStore.data.map { prefs ->
        val activeId = prefs[ACTIVE_PROFILE_KEY] ?: return@map null
        decodeProfiles(prefs[PROFILES_KEY]).find { it.id == activeId }
    }

    /**
     * Get all profiles (one-shot)
     */
    suspend fun getProfiles(): List<Profile> = profiles.first()

    /**
     * Get active profile ID (one-shot)
     */
    suspend fun getActiveProfileId(): String? = activeProfileId.first()

    /**
     * Get active profile (one-shot)
     */
    suspend fun getActiveProfile(): Profile? = activeProfile.first()

    /**
     * Check if profiles exist
     */
    suspend fun hasProfiles(): Boolean = getProfiles().isNotEmpty()

    /**
     * Create a new profile
     */
    suspend fun createProfile(name: String, avatarColor: Long, avatarId: Int = 0, isKidsProfile: Boolean = false): Profile {
        val profile = Profile(
            name = name,
            avatarColor = avatarColor,
            avatarId = avatarId,
            isKidsProfile = isKidsProfile
        )

        context.profilesDataStore.edit { prefs ->
            val currentList = decodeProfiles(prefs[PROFILES_KEY]).toMutableList()
            currentList.add(profile)
            prefs[PROFILES_KEY] = encodeProfiles(currentList)
        }
        invalidationBus.markDirty(CloudSyncScope.PROFILES, profile.id, "create profile")
        pushProfilesStateToCloud()

        return profile
    }

    /**
     * Update an existing profile
     */
    suspend fun updateProfile(profile: Profile) {
        context.profilesDataStore.edit { prefs ->
            val currentList = decodeProfiles(prefs[PROFILES_KEY]).toMutableList()
            val index = currentList.indexOfFirst { it.id == profile.id }
            if (index >= 0) {
                currentList[index] = profile
                prefs[PROFILES_KEY] = encodeProfiles(currentList)
            }
        }
        invalidationBus.markDirty(CloudSyncScope.PROFILES, profile.id, "update profile")
        pushProfilesStateToCloud()
    }

    /**
     * Delete a profile
     */
    suspend fun deleteProfile(profileId: String) {
        context.profilesDataStore.edit { prefs ->
            val currentList = decodeProfiles(prefs[PROFILES_KEY]).toMutableList()
            currentList.removeAll { it.id == profileId }
            prefs[PROFILES_KEY] = encodeProfiles(currentList)

            // If we deleted the active profile, clear it
            if (prefs[ACTIVE_PROFILE_KEY] == profileId) {
                prefs.remove(ACTIVE_PROFILE_KEY)
            }
        }
        profileAvatarImageManager.clearLocalAvatar(profileId)
        invalidationBus.markDirty(CloudSyncScope.PROFILES, profileId, "delete profile")
        pushProfilesStateToCloud()
    }

    /**
     * Set the active profile
     */
    suspend fun setActiveProfile(profileId: String) {
        context.profilesDataStore.edit { prefs ->
            prefs[ACTIVE_PROFILE_KEY] = profileId

            // Update lastUsedAt
            val currentList = decodeProfiles(prefs[PROFILES_KEY]).toMutableList()
            val index = currentList.indexOfFirst { it.id == profileId }
            if (index >= 0) {
                currentList[index] = currentList[index].copy(lastUsedAt = System.currentTimeMillis())
                prefs[PROFILES_KEY] = encodeProfiles(currentList)
            }
        }
        invalidationBus.markDirty(CloudSyncScope.PROFILES, profileId, "set active profile")
        pushProfilesStateToCloud()
    }

    /**
     * Clear active profile (for switching)
     */
    suspend fun clearActiveProfile() {
        context.profilesDataStore.edit { prefs ->
            prefs.remove(ACTIVE_PROFILE_KEY)
        }
        invalidationBus.markDirty(CloudSyncScope.PROFILES, reason = "clear active profile")
        pushProfilesStateToCloud()
    }

    suspend fun replaceProfilesFromCloud(
        profiles: List<Profile>,
        activeProfileId: String?
    ) {
        val localProfilesById = getProfiles().associateBy { it.id }
        val mergedProfiles = profiles.map { cloudProfile ->
            val localProfile = localProfilesById[cloudProfile.id]
            if (
                localProfile != null &&
                localProfile.avatarImageVersion > 0L &&
                cloudProfile.avatarImageVersion <= 0L
            ) {
                cloudProfile.copy(
                    avatarId = 0,
                    avatarImageVersion = localProfile.avatarImageVersion,
                    avatarImageStoragePath = localProfile.avatarImageStoragePath
                )
            } else {
                cloudProfile
            }
        }

        context.profilesDataStore.edit { prefs ->
            prefs[PROFILES_KEY] = gson.toJson(mergedProfiles)
            if (!activeProfileId.isNullOrBlank() && mergedProfiles.any { it.id == activeProfileId }) {
                prefs[ACTIVE_PROFILE_KEY] = activeProfileId
            } else if (mergedProfiles.isNotEmpty()) {
                prefs[ACTIVE_PROFILE_KEY] = mergedProfiles.first().id
            } else {
                prefs.remove(ACTIVE_PROFILE_KEY)
            }
        }
        if (!invalidationBus.isApplyingRemoteState) {
            pushProfilesStateToCloud()
        }
    }

    private suspend fun pushProfilesStateToCloud() {
        val userId = authRepository.getCurrentUserIdForSync() ?: return
        val profiles = getProfiles()
        val activeProfileId = getActiveProfileId()
        authRepository.mutateAccountSyncPayload { root ->
            root.put("activeProfileId", activeProfileId ?: JSONObject.NULL)
            root.put("profiles", JSONArray(gson.toJson(profiles)))
            root.put(
                "profileAvatarImagesById",
                profileAvatarImageManager.buildInlineAvatarImagesJson(
                    profiles,
                    root.optJSONObject("profileAvatarImagesById")
                )
            )
            root.put("userId", userId)
        }
    }

    /**
     * Create a default profile if none exist
     */
    suspend fun createDefaultProfileIfNeeded(): Profile? {
        if (hasProfiles()) return null
        return createProfile(
            name = "Profile 1",
            avatarColor = ProfileColors.colors[0]
        )
    }

    private fun decodeProfiles(json: String?): List<Profile> {
        if (json.isNullOrBlank()) return emptyList()
        return try {
            gson.fromJson<List<Profile>>(json, profileListType) ?: emptyList()
        } catch (e: Exception) {
            AppLogger.e("ProfileRepository", "Error getting profiles from network", e)
            emptyList()
        }
    }

    private fun encodeProfiles(profiles: List<Profile>): String {
        return gson.toJson(profiles, profileListType)
    }
}
