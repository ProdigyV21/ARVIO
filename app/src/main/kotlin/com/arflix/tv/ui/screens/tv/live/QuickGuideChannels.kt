package com.arflix.tv.ui.screens.tv.live

internal fun quickGuideChannels(
    channels: List<EnrichedChannel>,
    favorites: List<String>,
    recents: Collection<String>,
    hidden: Set<String>,
    restricted: Set<String>,
    collapseVariants: Boolean,
): Map<String, List<EnrichedChannel>> {
    val byId = channels.associateBy { it.id }
    return mapOf("fav" to favorites, "recent" to recents.toList().asReversed()).mapValues { (category, ids) ->
        val visible = prepareGuideChannels(ids.mapNotNull(byId::get), category, "provider", hidden, restricted)
        if (collapseVariants) collapseChannelVariants(visible, buildVariantGroups(visible)) else visible
    }
}
