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
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()

    companion object {
        private val PROFILES_KEY = stringPreferencesKey("profiles")
        private val ACTIVE_PROFILE_KEY = stringPreferencesKey("active_profile_id")
    }

    /**
     * Flow of all profiles
     */
    val profiles: Flow<List<Profile>> = context.profilesDataStore.data.map { prefs ->
        val json = prefs[PROFILES_KEY]
        if (json.isNullOrBlank()) {
            emptyList()
        } else {
            try {
                val type = object : TypeToken<List<Profile>>() {}.type
                gson.fromJson(json, type)
            } catch (e: Exception) {
                emptyList()
            }
        }
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
        val json = prefs[PROFILES_KEY] ?: return@map null
        try {
            val type = object : TypeToken<List<Profile>>() {}.type
            val profileList: List<Profile> = gson.fromJson(json, type)
            profileList.find { it.id == activeId }
        } catch (e: Exception) {
            null
        }
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
    suspend fun createProfile(name: String, avatarColor: Long, isKidsProfile: Boolean = false): Profile {
        val profile = Profile(
            name = name,
            avatarColor = avatarColor,
            isKidsProfile = isKidsProfile
        )

        context.profilesDataStore.edit { prefs ->
            val currentList = getProfiles().toMutableList()
            currentList.add(profile)
            prefs[PROFILES_KEY] = gson.toJson(currentList)
        }

        return profile
    }

    /**
     * Update an existing profile
     */
    suspend fun updateProfile(profile: Profile) {
        context.profilesDataStore.edit { prefs ->
            val currentList = getProfiles().toMutableList()
            val index = currentList.indexOfFirst { it.id == profile.id }
            if (index >= 0) {
                currentList[index] = profile
                prefs[PROFILES_KEY] = gson.toJson(currentList)
            }
        }
    }

    /**
     * Delete a profile
     */
    suspend fun deleteProfile(profileId: String) {
        context.profilesDataStore.edit { prefs ->
            val currentList = getProfiles().toMutableList()
            currentList.removeAll { it.id == profileId }
            prefs[PROFILES_KEY] = gson.toJson(currentList)

            // If we deleted the active profile, clear it
            if (prefs[ACTIVE_PROFILE_KEY] == profileId) {
                prefs.remove(ACTIVE_PROFILE_KEY)
            }
        }
    }

    /**
     * Set the active profile
     */
    suspend fun setActiveProfile(profileId: String) {
        context.profilesDataStore.edit { prefs ->
            prefs[ACTIVE_PROFILE_KEY] = profileId

            // Update lastUsedAt
            val currentList = getProfiles().toMutableList()
            val index = currentList.indexOfFirst { it.id == profileId }
            if (index >= 0) {
                currentList[index] = currentList[index].copy(lastUsedAt = System.currentTimeMillis())
                prefs[PROFILES_KEY] = gson.toJson(currentList)
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
}
