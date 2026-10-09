package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/** The active profile's last [MAX_RECENT_SEARCHES] search queries, newest first. */
@Singleton
class RecentSearchRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
) {
    private val gson = Gson()

    @OptIn(ExperimentalCoroutinesApi::class)
    val recentSearches: Flow<List<String>> = profileManager.currentProfileId
        .flatMapLatest { profileId ->
            val key = profileManager.profileStringKeyFor(profileId, KEY)
            context.settingsDataStore.data.map { decode(it[key]) }
        }
        .catch { e ->
            Log.w(TAG, "read failed: ${e.message}")
            emit(emptyList())
        }
        .distinctUntilChanged()

    suspend fun add(query: String) = update { withRecentSearch(it, query) }

    suspend fun clear() = update { emptyList() }

    private suspend fun update(change: (List<String>) -> List<String>) {
        try {
            val key = profileManager.profileStringKey(KEY)
            context.settingsDataStore.edit { prefs ->
                val updated = change(decode(prefs[key]))
                if (updated.isEmpty()) prefs.remove(key) else prefs[key] = gson.toJson(updated)
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            Log.w(TAG, "save failed: ${e.message}")
        }
    }

    private fun decode(raw: String?): List<String> {
        if (raw.isNullOrBlank()) return emptyList()
        // Gson can put nulls into a List<String> despite its type.
        return runCatching { gson.fromJson<List<String?>>(raw, LIST_TYPE) }.getOrNull()
            .orEmpty()
            .mapNotNull { it?.trim()?.takeIf { query -> query.isNotEmpty() } }
            .distinctBy { it.lowercase() }
            .take(MAX_RECENT_SEARCHES)
    }

    private companion object {
        const val TAG = "RecentSearches"
        const val KEY = "recent_searches_v1"
        val LIST_TYPE = object : TypeToken<List<String?>>() {}.type
    }
}

const val MAX_RECENT_SEARCHES = 8

/**
 * [query] moved to the front of [current]. The same query in another case counts as the same
 * search, so it is moved rather than listed twice.
 */
fun withRecentSearch(current: List<String>, query: String, max: Int = MAX_RECENT_SEARCHES): List<String> {
    val trimmed = query.trim()
    if (trimmed.isEmpty()) return current
    return (listOf(trimmed) + current.filterNot { it.equals(trimmed, ignoreCase = true) }).take(max)
}
