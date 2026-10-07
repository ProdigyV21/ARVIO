package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.RecentPlayedSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Per-profile store of each title's [RecentPlayedSource]: one record per title, the most
 * recently played [MAX_TITLES] titles kept.
 */
@Singleton
class RecentPlayedSourceRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
) {
    private val gson = Gson()
    private val recordsType = object : TypeToken<Map<String, RecentPlayedSource>>() {}.type
    // Saves outlive the player screen: leaving right after the threshold must not drop the write.
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /** The active profile's record for this title, whichever episode it was for. */
    suspend fun get(mediaType: MediaType, mediaId: Int): RecentPlayedSource? = withContext(Dispatchers.IO) {
        try {
            val raw = context.streamDataStore.data.first()[profileManager.profileStringKey(RECORDS_KEY)].orEmpty()
            decode(raw)[titleKey(mediaType, mediaId)]
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            Log.w(TAG, "read failed: ${e.message}")
            null
        }
    }

    /** Replaces the title's record. [profileId] is the profile that watched it. */
    fun save(profileId: String, record: RecentPlayedSource) {
        scope.launch {
            try {
                val key = profileManager.profileStringKeyFor(profileId, RECORDS_KEY)
                context.streamDataStore.edit { prefs ->
                    val records = decode(prefs[key].orEmpty()) +
                        (titleKey(record.mediaType, record.mediaId) to record)
                    val kept = records.entries
                        .sortedByDescending { it.value.playedAtMs }
                        .take(MAX_TITLES)
                        .associate { it.key to it.value }
                    prefs[key] = gson.toJson(kept)
                }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                Log.w(TAG, "save failed: ${e.message}")
            }
        }
    }

    private fun decode(raw: String): Map<String, RecentPlayedSource> {
        if (raw.isBlank()) return emptyMap()
        return runCatching {
            gson.fromJson<Map<String, RecentPlayedSource>>(raw, recordsType)
        }.getOrNull().orEmpty()
    }

    private fun titleKey(mediaType: MediaType, mediaId: Int) = "${mediaType.name}:$mediaId"

    private companion object {
        const val TAG = "RecentPlayedSource"
        const val RECORDS_KEY = "recent_played_sources_v1"
        const val MAX_TITLES = 200
    }
}
