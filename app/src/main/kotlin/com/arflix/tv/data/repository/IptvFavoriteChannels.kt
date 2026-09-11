package com.arflix.tv.data.repository

/** Explicit menu actions are idempotent, even if a stale popup is activated twice. */
internal fun favoriteChannelsWithMembership(existing: List<String>, channelId: String, favorite: Boolean): List<String> {
    if (channelId.isBlank() || (channelId in existing) == favorite) return existing
    return if (favorite) listOf(channelId) + existing else existing.filterNot { it == channelId }
}
