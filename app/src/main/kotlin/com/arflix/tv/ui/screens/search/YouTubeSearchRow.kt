package com.arflix.tv.ui.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.YouTubeCollection

/**
 * The YouTube row of the Search results: first, above the movie and series rows.
 *
 * Until the viewer presses it, the row holds a single "Search YouTube" card and YouTube is not
 * asked anything. Once pressed, the found videos replace the card. Being an ordinary row keeps
 * the screen's D-pad handling (one row index, one card index) unchanged.
 */
internal const val YOUTUBE_SEARCH_ROW_ID = "s_yt"

/** The id of the action card. Native addon cards are never this low (see AddonNativeCatalog.stableId). */
private const val YOUTUBE_SEARCH_ACTION_ID = Int.MIN_VALUE

internal fun MediaItem.isYouTubeSearchAction(): Boolean = id == YOUTUBE_SEARCH_ACTION_ID

internal fun MediaItem.isYouTubeVideo(): Boolean = addonNativeAddonId == YouTubeCollection.ADDON_ID

/**
 * The row for [state], or null when there is none: Play builds, no query, AI searches, and while
 * the regular search is still on its first load (the row would sit alone under the spinner).
 */
@Composable
internal fun youTubeSearchCategory(
    state: SearchUiState,
    available: Boolean,
    isKidsProfile: Boolean,
    hasSearchResults: Boolean
): Category? {
    val query = state.query.trim()
    if (!available || query.isEmpty() || state.isAiSearch) return null
    if (state.isLoading && !hasSearchResults) return null
    val youTube = state.youTube.takeIf { it.query == query } ?: YouTubeSearch()
    if (youTube.status == YouTubeSearchStatus.LOADED && youTube.videos.isNotEmpty()) {
        return Category(YOUTUBE_SEARCH_ROW_ID, stringResource(R.string.youtube_search_row_title), youTube.videos)
    }
    val forQuery = stringResource(R.string.youtube_search_for, query)
    val (title, subtitle) = when (youTube.status) {
        YouTubeSearchStatus.LOADING -> stringResource(R.string.youtube_search_loading) to forQuery
        YouTubeSearchStatus.LOADED -> stringResource(R.string.youtube_search_no_results) to forQuery
        YouTubeSearchStatus.FAILED -> stringResource(R.string.youtube_search_failed) to stringResource(R.string.youtube_search_retry)
        YouTubeSearchStatus.IDLE -> stringResource(
            if (isKidsProfile) R.string.youtube_kids_search_button else R.string.youtube_search_button
        ) to forQuery
    }
    val action = MediaItem(
        id = YOUTUBE_SEARCH_ACTION_ID,
        title = title,
        subtitle = subtitle,
        mediaType = MediaType.MOVIE
    )
    return Category(YOUTUBE_SEARCH_ROW_ID, stringResource(R.string.youtube_search_row_title), listOf(action))
}

/** The artwork of the action card; other cards keep their own images. */
@Composable
internal fun youTubeSearchCardPainter(item: MediaItem): Painter? =
    if (item.isYouTubeSearchAction()) painterResource(R.drawable.youtube_search_card) else null
