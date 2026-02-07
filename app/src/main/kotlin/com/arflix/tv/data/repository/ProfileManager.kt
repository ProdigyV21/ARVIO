package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.util.profilesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages the current active profile and provides profile-scoped preference keys.
 *
 * All repositories that need profile isolation should use this to get prefixed keys.
 * Example: Instead of "access_token", use profileManager.profileKey("access_token")
 * which returns "profile_<id>_access_token"
 */
@Singleton
class ProfileManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileRepository: ProfileRepository
) {
    // Default profile ID used when no profile is selected (for backwards compatibility)
    private val DEFAULT_PROFILE_ID = "default"

    // Cache the current profile ID for synchronous access
    private val _currentProfileId = MutableStateFlow<String>(DEFAULT_PROFILE_ID)
    val currentProfileId: StateFlow<String> = _currentProfileId.asStateFlow()

    /**
     * Flow of the current profile ID
     */
    val activeProfileId: Flow<String> = profileRepository.activeProfileId.map { id ->
        val profileId = id ?: DEFAULT_PROFILE_ID
        _currentProfileId.value = profileId
        profileId
    }

    /**
     * Get the current profile ID synchronously (cached value)
     * Use this when you need immediate access without suspend
     */
    fun getProfileIdSync(): String = _currentProfileId.value

    /**
     * Get the current profile ID (suspend version, always fresh)
     */
    suspend fun getProfileId(): String {
        val id = profileRepository.getActiveProfileId() ?: DEFAULT_PROFILE_ID
        _currentProfileId.value = id
        return id
    }

    /**
     * Initialize the profile manager - call this early in app startup
     */
    suspend fun initialize() {
        val id = profileRepository.getActiveProfileId() ?: DEFAULT_PROFILE_ID
        _currentProfileId.value = id
    }

    /**
     * Directly update the current profile ID - use when switching profiles
     * to ensure the cached ID is updated before any profile-scoped operations
     */
    fun setCurrentProfileId(profileId: String) {
        _currentProfileId.value = profileId
    }

    // ========== Profile-Scoped Key Generators ==========

    /**
     * Create a profile-scoped string preference key
     */
    fun profileStringKey(name: String): Preferences.Key<String> {
        return stringPreferencesKey("profile_${getProfileIdSync()}_$name")
    }

    /**
     * Create a profile-scoped long preference key
     */
    fun profileLongKey(name: String): Preferences.Key<Long> {
        return longPreferencesKey("profile_${getProfileIdSync()}_$name")
    }

    /**
     * Create a profile-scoped boolean preference key
     */
    fun profileBooleanKey(name: String): Preferences.Key<Boolean> {
        return booleanPreferencesKey("profile_${getProfileIdSync()}_$name")
    }

    /**
     * Get the key prefix for the current profile
     */
    fun getKeyPrefix(): String = "profile_${getProfileIdSync()}_"

    /**
     * Check if this is the default profile (no user profile selected)
     */
    fun isDefaultProfile(): Boolean = getProfileIdSync() == DEFAULT_PROFILE_ID
}
