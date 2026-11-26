package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.model.SportsFollow
import com.arflix.tv.data.model.SportsReminder
import com.arflix.tv.data.model.sanitizedFollows
import com.arflix.tv.data.model.sanitizedReminders
import com.arflix.tv.data.model.toggled
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

/** Followed teams and leagues sync with the account; reminders stay on this device. */
object SportsCloudKeys {
    fun follows(profileId: String) = stringPreferencesKey("profile_${profileId}_sports_follows_v1")
    fun reminders(profileId: String) = stringPreferencesKey("profile_${profileId}_sports_reminders_v1")
}

/** Where a kick-off reminder wants Live TV to go. Live TV consumes it once. */
data class SportsSwitchRequest(
    val eventId: String,
    val title: String,
    val channelIds: List<String>,
    val requestedAt: Long = System.currentTimeMillis(),
)

@Singleton
class SportsPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val invalidationBus: CloudSyncInvalidationBus,
) {
    private val gson = Gson()

    val follows: Flow<List<SportsFollow>> = combine(profileManager.currentProfileId, context.settingsDataStore.data) { profileId, prefs ->
        decodeFollows(prefs[SportsCloudKeys.follows(profileId)])
    }.distinctUntilChanged()

    val reminders: Flow<List<SportsReminder>> = combine(profileManager.currentProfileId, context.settingsDataStore.data) { profileId, prefs ->
        decodeReminders(prefs[SportsCloudKeys.reminders(profileId)])
    }.distinctUntilChanged()

    suspend fun toggleFollow(follow: SportsFollow) {
        val profileId = profileManager.getProfileIdSync()
        context.settingsDataStore.edit { prefs ->
            val key = SportsCloudKeys.follows(profileId)
            prefs[key] = gson.toJson(decodeFollows(prefs[key]).toggled(follow))
        }
        invalidationBus.markUserDirty(CloudSyncScope.PROFILE_SETTINGS, profileId, "sports follows")
    }

    suspend fun updateReminders(transform: (List<SportsReminder>) -> List<SportsReminder>) {
        val profileId = profileManager.getProfileIdSync()
        context.settingsDataStore.edit { prefs ->
            val key = SportsCloudKeys.reminders(profileId)
            val current = decodeReminders(prefs[key])
            val next = transform(current)
            if (next != current) prefs[key] = gson.toJson(next)
        }
    }

    private val _pendingSwitch = MutableStateFlow<SportsSwitchRequest?>(null)
    val pendingSwitch: StateFlow<SportsSwitchRequest?> = _pendingSwitch.asStateFlow()

    fun requestSwitch(request: SportsSwitchRequest) { _pendingSwitch.value = request }

    /** Returns the request once; a second caller gets null. */
    fun consumeSwitch(): SportsSwitchRequest? {
        while (true) {
            val current = _pendingSwitch.value ?: return null
            if (_pendingSwitch.compareAndSet(current, null)) return current
        }
    }

    private fun decodeFollows(json: String?): List<SportsFollow> = json?.let {
        runCatching { gson.fromJson(it, Array<SportsFollow?>::class.java)?.filterNotNull()?.sanitizedFollows() }.getOrNull()
    }.orEmpty()

    private fun decodeReminders(json: String?): List<SportsReminder> = json?.let {
        runCatching { gson.fromJson(it, Array<SportsReminder?>::class.java)?.filterNotNull()?.sanitizedReminders() }.getOrNull()
    }.orEmpty()
}
