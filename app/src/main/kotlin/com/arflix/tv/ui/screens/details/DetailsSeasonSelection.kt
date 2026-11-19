package com.arflix.tv.ui.screens.details

import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.EpisodeIdentity

/** Match canonical coordinates: displayed anime numbering can differ from TMDB numbering. */
internal fun detailEpisodeReturnIndex(episodes: List<Episode>, target: EpisodeIdentity?): Int? =
    target?.let { identity ->
        episodes.indexOfFirst {
            it.tmdbSeasonNumber == identity.tmdbSeason && it.tmdbEpisodeNumber == identity.tmdbEpisode
        }.takeIf { it >= 0 }
    }

/** Season rows are 1-based, but a partner/launcher link may explicitly open TMDB specials. */
internal fun detailSeasonForAutomaticSelection(index: Int, currentSeason: Int, linkedSeason: Int?): Int =
    if (index == 0 && currentSeason == 0 && linkedSeason == 0) 0 else index + 1

internal fun detailInitialEpisodeSeasonMatches(currentSeason: Int, initialIndex: Int, linkedSeason: Int?): Boolean =
    currentSeason == initialIndex + 1 || (currentSeason == 0 && linkedSeason == 0)
