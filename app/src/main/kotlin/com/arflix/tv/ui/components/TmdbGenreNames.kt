package com.arflix.tv.ui.components

import androidx.annotation.StringRes
import com.arflix.tv.R

/**
 * Display-only localization of the TMDB genre names shown on the home screen
 * and in the search filter chips.
 *
 * The numeric TMDB genre id is the stable key — it is what the API is queried
 * with and what a selected filter is compared by — so only the rendered label
 * is translated. Same approach as `liveCategoryLabel` in `LiveCategory.kt`.
 *
 * Movie and TV ids are kept apart on purpose: TMDB uses separate genre lists
 * per media type, and an id that is absent from a list must stay absent so the
 * rendered metadata does not change.
 */
@StringRes
fun movieGenreNameRes(genreId: Int): Int? = when (genreId) {
    28 -> R.string.collections_genre_action
    12 -> R.string.collections_genre_adventure
    16 -> R.string.collections_genre_animation
    35 -> R.string.collections_genre_comedy
    80 -> R.string.tmdb_genre_crime
    99 -> R.string.collections_genre_documentary
    18 -> R.string.collections_genre_drama
    10751 -> R.string.collections_genre_family
    14 -> R.string.collections_genre_fantasy
    36 -> R.string.tmdb_genre_history
    27 -> R.string.collections_genre_horror
    10402 -> R.string.tmdb_genre_music
    9648 -> R.string.tmdb_genre_mystery
    10749 -> R.string.collections_genre_romance
    878 -> R.string.collections_genre_sci_fi
    10770 -> R.string.tmdb_genre_tv_movie
    53 -> R.string.collections_genre_thriller
    10752 -> R.string.tmdb_genre_war
    37 -> R.string.tmdb_genre_western
    else -> null
}

@StringRes
fun tvGenreNameRes(genreId: Int): Int? = when (genreId) {
    10759 -> R.string.tmdb_genre_action_adventure
    16 -> R.string.collections_genre_animation
    35 -> R.string.collections_genre_comedy
    80 -> R.string.tmdb_genre_crime
    99 -> R.string.collections_genre_documentary
    18 -> R.string.collections_genre_drama
    10751 -> R.string.collections_genre_family
    10762 -> R.string.tmdb_genre_kids
    9648 -> R.string.tmdb_genre_mystery
    10763 -> R.string.tmdb_genre_news
    10764 -> R.string.tmdb_genre_reality
    10765 -> R.string.tmdb_genre_sci_fi_fantasy
    10766 -> R.string.tmdb_genre_soap
    10767 -> R.string.tmdb_genre_talk
    10768 -> R.string.tmdb_genre_war_politics
    37 -> R.string.tmdb_genre_western
    else -> null
}
