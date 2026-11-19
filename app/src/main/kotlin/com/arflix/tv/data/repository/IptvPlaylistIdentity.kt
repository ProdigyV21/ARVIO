package com.arflix.tv.data.repository

/** Repair old count-based IDs without merging distinct providers or stealing a later entry's ID. */
internal fun repairIptvPlaylistIds(playlists: List<IptvPlaylistEntry>): List<IptvPlaylistEntry> {
    val reserved = playlists.map { it.id.trim() }.toMutableSet()
    val used = mutableSetOf<String>()
    return playlists.mapIndexed { index, playlist ->
        var id = playlist.id.trim()
        if (id.isBlank() || id in used) {
            val base = "list_recovered_${index + 1}"
            id = base
            var suffix = 1
            while (id in reserved) id = "${base}_${suffix++}"
            reserved.add(id)
        }
        used.add(id)
        if (id == playlist.id) playlist else playlist.copy(id = id)
    }
}
