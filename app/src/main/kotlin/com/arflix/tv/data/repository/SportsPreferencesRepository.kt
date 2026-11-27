package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
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
    /** Device only: channel ids differ per playlist and per device. */
    fun channelMemory(profileId: String) = stringPreferencesKey("profile_${profileId}_sports_channel_memory_v1")
    fun scoreAlerts(profileId: String) = booleanPreferencesKey("profile_${profileId}_sports_score_alerts_v1")
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

    /** League or network key -> the channel last chosen for it, on this device. */
    val channelMemory: Flow<Map<String, String>> = combine(profileManager.currentProfileId, context.settingsDataStore.data) { profileId, prefs ->
        decodeMemory(prefs[SportsCloudKeys.channelMemory(profileId)])
    }.distinctUntilChanged()

    /** Score alerts for followed teams; on unless turned off on this device. */
    val scoreAlerts: Flow<Boolean> = combine(profileManager.currentProfileId, context.settingsDataStore.data) { profileId, prefs ->
        prefs[SportsCloudKeys.scoreAlerts(profileId)] != false
    }.distinctUntilChanged()

    suspend fun setScoreAlerts(on: Boolean) {
        val profileId = profileManager.getProfileIdSync()
        context.settingsDataStore.edit { it[SportsCloudKeys.scoreAlerts(profileId)] = on }
    }

    suspend fun rememberChannel(keys: Collection<String>, channelId: String) {
        if (keys.isEmpty() || channelId.isBlank()) return
        val profileId = profileManager.getProfileIdSync()
        context.settingsDataStore.edit { prefs ->
            val key = SportsCloudKeys.channelMemory(profileId)
            val current = decodeMemory(prefs[key])
            val next = current.withRemembered(keys, channelId)
            if (next != current) prefs[key] = gson.toJson(next)
        }
    }

    /**
     * The game being watched right now, if it was started from Sports. Score
     * alerts skip it: the stream may run behind the score feed.
     */
    private val _watchingEventId = MutableStateFlow<String?>(null)
    val watchingEventId: StateFlow<String?> = _watchingEventId.asStateFlow()
    fun setWatchingEvent(eventId: String?) { _watchingEventId.value = eventId }

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

    private fun decodeMemory(json: String?): Map<String, String> = json?.let {
        runCatching {
            (gson.fromJson(it, Map::class.java) as? Map<*, *>)?.entries
                ?.mapNotNull { (k, v) -> if (k is String && v is String && k.isNotBlank() && v.isNotBlank()) k to v else null }
                ?.toMap()
        }.getOrNull()
    }.orEmpty()

    private fun decodeFollows(json: String?): List<SportsFollow> = json?.let {
        runCatching { gson.fromJson(it, Array<SportsFollow?>::class.java)?.filterNotNull()?.sanitizedFollows() }.getOrNull()
    }.orEmpty()

    private fun decodeReminders(json: String?): List<SportsReminder> = json?.let {
        runCatching { gson.fromJson(it, Array<SportsReminder?>::class.java)?.filterNotNull()?.sanitizedReminders() }.getOrNull()
    }.orEmpty()
}

/** Newest choices first, at most [SPORTS_CHANNEL_MEMORY_LIMIT] keys. */
internal const val SPORTS_CHANNEL_MEMORY_LIMIT = 60
internal fun Map<String, String>.withRemembered(keys: Collection<String>, channelId: String): Map<String, String> {
    val fresh = keys.filter { it.isNotBlank() }.distinct().associateWith { channelId }
    val rest = filterKeys { it !in fresh }
    return LinkedHashMap<String, String>().apply { putAll(fresh); putAll(rest) }
        .entries.take(SPORTS_CHANNEL_MEMORY_LIMIT).associate { it.key to it.value }
}
