package com.arflix.tv.data.repository

import android.content.Context
import androidx.annotation.Keep
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

private val Context.traktOutboxDataStore: DataStore<Preferences> by preferencesDataStore(name = "trakt_outbox")

@Keep
enum class TraktOutboxAction {
    MARK_MOVIE_WATCHED,
    MARK_EPISODE_WATCHED,
    REMOVE_PLAYBACK_ITEM
}

@Keep
data class TraktOutboxItem(
    val id: String = UUID.randomUUID().toString(),
    val action: TraktOutboxAction,
    val tmdbId: Int? = null,
    val showTraktId: Int? = null,
    val traktEpisodeId: Int? = null,
    val season: Int? = null,
    val episode: Int? = null,
    val playbackId: Long? = null,
    val createdAt: String = Instant.now().toString(),
    val attempts: Int = 0
)

@Singleton
class TraktOutboxRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()
    private val outboxKey = stringPreferencesKey("trakt_outbox_items")

    suspend fun loadAll(): List<TraktOutboxItem> {
        val prefs = context.traktOutboxDataStore.data.first()
        val json = prefs[outboxKey] ?: return emptyList()
        return decode(json)
    }

    suspend fun enqueue(item: TraktOutboxItem) {
        context.traktOutboxDataStore.edit { prefs ->
            val current = decode(prefs[outboxKey])
            current.add(item)
            prefs[outboxKey] = gson.toJson(current)
        }
    }

    suspend fun remove(ids: Set<String>) {
        if (ids.isEmpty()) return
        context.traktOutboxDataStore.edit { prefs ->
            val current = decode(prefs[outboxKey])
            val updated = current.filterNot { ids.contains(it.id) }
            prefs[outboxKey] = gson.toJson(updated)
        }
    }

    suspend fun incrementAttempts(ids: Set<String>) {
        if (ids.isEmpty()) return
        context.traktOutboxDataStore.edit { prefs ->
            val current = decode(prefs[outboxKey])
            val updated = current.map { item ->
                if (ids.contains(item.id)) item.copy(attempts = item.attempts + 1) else item
            }
            prefs[outboxKey] = gson.toJson(updated)
        }
    }

    private fun decode(json: String?): MutableList<TraktOutboxItem> {
        if (json.isNullOrBlank()) return mutableListOf()
        val type = TypeToken.getParameterized(MutableList::class.java, TraktOutboxItem::class.java).type
        return gson.fromJson(json, type) ?: mutableListOf()
    }
}
