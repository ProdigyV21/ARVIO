package com.arflix.tv.data.repository

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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileAvatarImageManager: ProfileAvatarImageManager
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
}

    /**
     * Clear active profile (for switching)
     */
    suspend fun clearActiveProfile() {
        context.profilesDataStore.edit { prefs ->
            prefs.remove(ACTIVE_PROFILE_KEY)
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
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun encodeProfiles(profiles: List<Profile>): String {
        return gson.toJson(profiles, profileListType)
    }
}
