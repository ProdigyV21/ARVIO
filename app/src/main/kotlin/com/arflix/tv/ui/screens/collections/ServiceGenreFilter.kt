package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind

/**
 * Genre filter for streaming-service pages (Netflix, HBO Max, ...). Those pages are built from
 * TMDB watch-provider queries, which TMDB can narrow with `with_genres`. Movies and series use
 * separate TMDB genre lists, so each tab offers its own ids (only series have Reality).
 */
internal object ServiceGenreFilter {
    val movieGenreIds = listOf(
        28, 12, 16, 35, 80, 99, 18, 10751, 14, 36, 27, 10402, 9648, 10749, 878, 53, 10752, 37
    )
    val seriesGenreIds = listOf(
        10759, 16, 35, 80, 99, 18, 10751, 10762, 9648, 10763, 10764, 10765, 10766, 10767, 10768, 37
    )

    fun genreIds(tab: CollectionTab): List<Int> =
        if (tab == CollectionTab.MOVIES) movieGenreIds else seriesGenreIds

    /** True when every source is a watch-provider query, so a genre applies to the whole page. */
    fun supports(catalog: CatalogConfig): Boolean =
        catalog.collectionSources.isNotEmpty() &&
            catalog.collectionSources.all { it.kind == CollectionSourceKind.TMDB_WATCH_PROVIDER }

    fun apply(sources: List<CollectionSourceConfig>, genreId: Int?): List<CollectionSourceConfig> {
        if (genreId == null) return sources
        return sources.map { source ->
            if (source.kind == CollectionSourceKind.TMDB_WATCH_PROVIDER) source.copy(tmdbGenreId = genreId) else source
        }
    }
}
