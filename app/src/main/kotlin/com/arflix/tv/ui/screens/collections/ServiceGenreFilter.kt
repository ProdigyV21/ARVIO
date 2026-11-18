package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.repository.WatchProviderSort
import java.time.LocalDate

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

    /**
     * A service page lists the newest titles first, while its Home row keeps the template's
     * popularity order: only the page's queries are re-sorted.
     */
    fun newestFirst(sources: List<CollectionSourceConfig>, today: LocalDate = LocalDate.now()): List<CollectionSourceConfig> {
        // Query compatible providers together so TMDB sorts the union before it is paginated.
        val providers = sources.filter { it.kind == CollectionSourceKind.TMDB_WATCH_PROVIDER }
            .groupBy(::providerScope)
        val emitted = mutableSetOf<ProviderScope>()
        return sources.mapNotNull { source ->
            if (source.kind != CollectionSourceKind.TMDB_WATCH_PROVIDER) return@mapNotNull source
            val scope = providerScope(source)
            if (!emitted.add(scope)) return@mapNotNull null
            val sort = WatchProviderSort.newestFor(scope.type)
            val ids = providers.getValue(scope).mapNotNull { it.tmdbWatchProviderId }.distinct()
            if (ids.size <= 1) return@mapNotNull source.copy(sortBy = sort, tmdbWatchProviderId = ids.firstOrNull())
            val window = WatchProviderSort.window(sort, today)
            val dateParam = if (scope.type == "series") "first_air_date.lte" else "primary_release_date.lte"
            CollectionSourceConfig(
                kind = CollectionSourceKind.TMDB_DISCOVER,
                mediaType = scope.type,
                sortBy = sort,
                discoverParams = buildMap {
                    put("with_watch_providers", ids.joinToString("|"))
                    put("watch_region", scope.region)
                    scope.genreId?.let { put("with_genres", it.toString()) }
                    window.releasedOnOrBefore?.let { put(dateParam, it) }
                    window.minVoteCount?.let { put("vote_count.gte", it.toString()) }
                }
            )
        }
    }

    private data class ProviderScope(val type: String, val region: String, val genreId: Int?)

    private fun providerScope(source: CollectionSourceConfig) = ProviderScope(
        type = if (WatchProviderSort.isSeries(source.mediaType)) "series" else "movie",
        region = source.watchRegion?.takeIf(String::isNotBlank) ?: "US",
        genreId = source.tmdbGenreId
    )

    fun apply(sources: List<CollectionSourceConfig>, genreId: Int?): List<CollectionSourceConfig> {
        if (genreId == null) return sources
        return sources.map { source ->
            if (source.kind == CollectionSourceKind.TMDB_WATCH_PROVIDER) source.copy(tmdbGenreId = genreId) else source
        }
    }
}
