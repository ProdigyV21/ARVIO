package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaType

internal fun currentPlayerEpisode(
    episodes: List<Episode>,
    seasonNumber: Int?,
    episodeNumber: Int?
): Episode? {
    if (seasonNumber == null || episodeNumber == null) return null
    return episodes.firstOrNull {
        it.tmdbSeasonNumber == seasonNumber && it.tmdbEpisodeNumber == episodeNumber
    }
}

internal fun playerPauseOverview(
    mediaType: MediaType,
    seasonNumber: Int?,
    episodeNumber: Int?,
    episodes: List<Episode>,
    fallbackOverview: String?
): String {
    val episodeOverview = if (mediaType == MediaType.TV) {
        currentPlayerEpisode(episodes, seasonNumber, episodeNumber)?.overview
    } else {
        null
    }
    return episodeOverview?.trim()?.takeIf { it.isNotEmpty() }
        ?: fallbackOverview?.trim().orEmpty()
}
