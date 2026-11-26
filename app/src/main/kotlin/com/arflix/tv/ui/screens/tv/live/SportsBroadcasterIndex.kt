package com.arflix.tv.ui.screens.tv.live

internal data class SportsBroadcasterIndexKey(
    val profileId: String?,
    val providerId: String,
    val sourceVersion: Long,
    val excludedGroups: Set<String>,
)

/** Index only labels/IDs; full channel records are hydrated for actual matches. */
internal class SportsBroadcasterIndex private constructor(
    val key: SportsBroadcasterIndexKey,
    private val idsByName: Map<String, List<String>>,
    private val sourceOrder: Map<String, Int>,
) {
    fun matchingIds(names: Set<String>): List<String> =
        names.flatMap { idsByName[it].orEmpty() }.distinct().sortedBy { sourceOrder[it] }

    /** Channel ids per group for every channel name [group] places, in playlist order. */
    fun groupedIds(group: (String) -> String?): Map<String, List<String>> {
        val output = LinkedHashMap<String, MutableList<String>>()
        for ((name, ids) in idsByName) {
            val id = group(name) ?: continue
            output.getOrPut(id) { ArrayList() }.addAll(ids)
        }
        return output.mapValues { (_, ids) -> ids.distinct().sortedBy { sourceOrder[it] } }
    }

    companion object {
        fun build(key: SportsBroadcasterIndexKey, visit: (visitor: (String, String, String) -> Unit) -> Unit): SportsBroadcasterIndex {
            val normalized = HashMap<String, String>()
            val matches = LinkedHashMap<String, MutableList<String>>()
            val order = HashMap<String, Int>()
            visit { id, name, group ->
                val groupKey = com.arflix.tv.data.model.PlaylistGroupKey.build(channelPlaylistId(id), group.trim())
                if (groupKey !in key.excludedGroups && group !in key.excludedGroups) {
                    val nameKey = normalized.getOrPut(name) { sportsChannelKey(name) }
                    order.putIfAbsent(id, order.size)
                    matches.getOrPut(nameKey) { ArrayList() }.add(id)
                }
            }
            return SportsBroadcasterIndex(key, matches, order)
        }
    }
}
