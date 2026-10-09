package com.arflix.tv.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

/** Membership operations are independent of artwork, ordering and legacy addedAt values. */
data class WatchlistChange(val updatedAt: Long = 0L, val removed: Boolean = false)

data class WatchlistSyncState(
    val items: List<LocalWatchlistItem> = emptyList(),
    val changes: Map<String, WatchlistChange> = emptyMap()
)

internal fun watchlistItemKey(item: LocalWatchlistItem): String = "${item.mediaType}:${item.tmdbId}"
private val watchlistChangeKey = Regex("^(movie|tv):[1-9][0-9]*$")

internal fun mergeWatchlistStates(local: WatchlistSyncState, remote: WatchlistSyncState): WatchlistSyncState {
    val changes = linkedMapOf<String, WatchlistChange>()
    for ((key, change) in local.changes.entries + remote.changes.entries) {
        if (!watchlistChangeKey.matches(key) || change.updatedAt !in 1L..9_007_199_254_740_991L) continue
        val previous = changes[key]
        if (previous == null || change.updatedAt > previous.updatedAt ||
            (change.updatedAt == previous.updatedAt && change.removed)) changes[key] = change
    }
    val items = linkedMapOf<String, LocalWatchlistItem>()
    for (item in local.items + remote.items) {
        if (item.tmdbId <= 0 || (item.mediaType != "movie" && item.mediaType != "tv")) continue
        val key = watchlistItemKey(item)
        if (changes[key]?.removed == true) continue
        val previous = items[key]
        if (previous == null || item.addedAt > previous.addedAt) items[key] = item
    }
    return WatchlistSyncState(
        items.values.sortedWith(compareBy<LocalWatchlistItem> { it.sourceOrder }.thenByDescending { it.addedAt }),
        changes
    )
}

internal fun nextWatchlistChange(previous: WatchlistChange?, removed: Boolean, now: Long): WatchlistChange =
    WatchlistChange(maxOf(now, (previous?.updatedAt ?: 0L) + 1L), removed)

/** Shared snapshot merge for push and pull. Missing entries are not removal instructions. */
internal object WatchlistCloudFields {
    private val gson = Gson()
    private val itemType = TypeToken.getParameterized(List::class.java, LocalWatchlistItem::class.java).type
    private val changeType = TypeToken.getParameterized(Map::class.java, String::class.java, WatchlistChange::class.java).type

    fun states(root: JSONObject): Map<String, WatchlistSyncState> {
        val lists = root.optJSONObject("watchlistByProfile") ?: JSONObject()
        val changes = root.optJSONObject("watchlistChangesByProfile") ?: JSONObject()
        return (lists.keys().asSequence().toSet() + changes.keys().asSequence().toSet()).associateWith { profileId ->
            WatchlistSyncState(
                lists.optJSONArray(profileId)?.let { gson.fromJson<List<LocalWatchlistItem>>(it.toString(), itemType) }.orEmpty(),
                changes.optJSONObject(profileId)?.let { gson.fromJson<Map<String, WatchlistChange>>(it.toString(), changeType) }.orEmpty()
            )
        }
    }

    fun put(root: JSONObject, states: Map<String, WatchlistSyncState>) {
        root.put("watchlistByProfile", JSONObject(gson.toJson(states.mapValues { it.value.items })))
        root.put("watchlistChangesByProfile", JSONObject(gson.toJson(states.mapValues { it.value.changes })))
    }

    fun merge(base: JSONObject, other: JSONObject) {
        val baseStates = states(base)
        val otherStates = states(other)
        if (baseStates.isEmpty() && otherStates.isEmpty()) return
        put(base, (baseStates.keys + otherStates.keys).associateWith { profileId ->
            mergeWatchlistStates(baseStates[profileId] ?: WatchlistSyncState(), otherStates[profileId] ?: WatchlistSyncState())
        })
    }
}
