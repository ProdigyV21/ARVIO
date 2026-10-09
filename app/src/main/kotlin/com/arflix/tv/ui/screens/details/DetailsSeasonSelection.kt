package com.arflix.tv.ui.screens.details

/** Season rows are 1-based, but a partner/launcher link may explicitly open TMDB specials. */
internal fun detailSeasonForAutomaticSelection(index: Int, currentSeason: Int, linkedSeason: Int?): Int =
    if (index == 0 && currentSeason == 0 && linkedSeason == 0) 0 else index + 1

internal fun detailInitialEpisodeSeasonMatches(currentSeason: Int, initialIndex: Int, linkedSeason: Int?): Boolean =
    currentSeason == initialIndex + 1 || (currentSeason == 0 && linkedSeason == 0)
