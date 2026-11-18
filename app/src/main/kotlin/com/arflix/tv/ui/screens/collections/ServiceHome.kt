package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.repository.WatchProviderSort
import com.arflix.tv.ui.screens.home.RANKED_ROW_ID_PREFIX
import java.time.LocalDate

/**
 * A streaming-service page laid out like the Netflix-style Home: a big card, then rows that
 * all come from that service (Top 10 series and movies, new arrivals, the service's own
 * series, top rated, genres).
 *
 * Every row is one TMDB discover query limited to the service's watch providers. TMDB has no
 * per-service viewing charts, so "Top 10" is TMDB popularity among the titles on the service.
 * Popularity alone favours long-running classics, so popularity rows only count titles from
 * the last few years, and every row asks for a few votes to skip placeholder entries.
 */
internal object ServiceHome {
    /** The Top 10 rows carry the ranked prefix, so their translated titles keep rank ribbons. */
    const val TOP10_SERIES_ROW_ID = RANKED_ROW_ID_PREFIX + "service_top10_series"
    const val TOP10_MOVIES_ROW_ID = RANKED_ROW_ID_PREFIX + "service_top10_movies"
    const val NEW_ROW_ID = "service_new"
    const val ORIGINALS_ROW_ID = "service_originals"
    const val TOP_RATED_SERIES_ROW_ID = "service_top_rated_series"
    const val TOP_RATED_MOVIES_ROW_ID = "service_top_rated_movies"
    const val GENRE_ROW_PREFIX = "service_genre_"

    const val TOP10_SIZE = 10
    const val ROW_SIZE = 20
    private const val POPULAR_YEARS = 3L
    private const val GENRE_YEARS = 5L
    private const val MIN_VOTES = 20
    private const val TOP_RATED_MOVIE_VOTES = 500
    private const val TOP_RATED_SERIES_VOTES = 300

    /**
     * TMDB networks of each service's own series, by watch-provider id. Checked against
     * TMDB's network names; a service missing here simply has no originals row.
     */
    private val ORIGINALS_NETWORKS: Map<Int, String> = mapOf(
        8 to "213",          // Netflix
        337 to "2739",       // Disney+
        350 to "2552",       // Apple TV+
        9 to "1024",         // Prime Video
        1899 to "3186|49",   // HBO Max, HBO
        15 to "453",         // Hulu
        2303 to "4330",      // Paramount+
        2616 to "4330",      // Paramount+ (second provider id)
        386 to "3353",       // Peacock
        43 to "318",         // Starz
        34 to "6219",        // MGM+
        520 to "4353",       // Discovery+
        283 to "1112"        // Crunchyroll
    )

    enum class RowKind { TOP10_SERIES, TOP10_MOVIES, NEW, ORIGINALS, TOP_RATED_SERIES, TOP_RATED_MOVIES, GENRE }

    /** One genre row: the TMDB genre for movies and for series (either may be absent). */
    data class Genre(val key: String, val movieGenreId: Int?, val seriesGenreId: Int?) {
        /** Series use their own list for a few genres ("Sci-Fi & Fantasy"); label from it when present. */
        val labelIsSeries: Boolean get() = movieGenreId == null || key == "scifi" || key == "kids"
    }

    val genres: List<Genre> = listOf(
        Genre("action", 28, 10759),
        Genre("comedy", 35, 35),
        Genre("crime", 80, 80),
        Genre("drama", 18, 18),
        Genre("scifi", 878, 10765),
        Genre("thriller", 53, 9648),
        Genre("kids", 10751, 10762),
        Genre("animation", 16, 16),
        Genre("documentary", 99, 99),
        Genre("horror", 27, null),
        Genre("reality", null, 10764),
        Genre("romance", 10749, null)
    )

    /** Enough to rebuild the exact row's catalog when opening its grid. */
    data class ViewAll(val rowId: String?, val tab: CollectionTab, val filter: Filter = Filter.ALL)

    data class Row(
        val id: String,
        val kind: RowKind,
        val catalog: CatalogConfig,
        val limit: Int,
        val genre: Genre? = null,
        /** Null when the row has no "View all" (the Top 10 rows end at ten). */
        val viewAll: ViewAll?
    )

    /** True when every source is a watch-provider query, the same pages the genre filter serves. */
    fun supports(catalog: CatalogConfig): Boolean = ServiceGenreFilter.supports(catalog)

    /** What the page lists: both kinds of title, or only series or only movies. */
    enum class Filter { ALL, SERIES, MOVIES }

    fun gridRow(service: CatalogConfig, today: LocalDate, rowId: String, filter: Filter): Row? =
        if (!supports(service)) null else rows(service, today, filter).firstOrNull { it.id == rowId && it.viewAll != null }

    /** The grid the header's Filter button opens: the series tab under Series, else movies, no genre yet. */
    fun grid(filter: Filter): ViewAll =
        ViewAll(null, if (filter == Filter.SERIES) CollectionTab.SERIES else CollectionTab.MOVIES, filter)

    /**
     * The page's rows for [filter]. A filtered page keeps each row's sources of that kind
     * (a genre row keeps its series or its movie query) and drops rows left with none.
     */
    fun rows(service: CatalogConfig, today: LocalDate, filter: Filter = Filter.ALL): List<Row> {
        val all = allRows(service, today)
        if (filter == Filter.ALL) return all
        val type = if (filter == Filter.SERIES) "series" else "movie"
        val tab = if (filter == Filter.SERIES) CollectionTab.SERIES else CollectionTab.MOVIES
        return all.mapNotNull { row ->
            val sources = row.catalog.collectionSources.filter { it.mediaType == type }
            if (sources.isEmpty()) return@mapNotNull null
            row.copy(
                catalog = row.catalog.copy(
                    id = "${row.catalog.id}#$type",
                    collectionRailKey = "${row.catalog.collectionRailKey}#$type",
                    collectionGroup = CollectionGroupKind.SERVICE,
                    collectionSources = sources
                ),
                viewAll = row.viewAll?.copy(tab = tab, filter = filter)
            )
        }
    }

    private fun allRows(service: CatalogConfig, today: LocalDate): List<Row> {
        val providerSources = service.collectionSources.filter { it.kind == CollectionSourceKind.TMDB_WATCH_PROVIDER }
        val providerIds = providerSources.mapNotNull { it.tmdbWatchProviderId }.distinct()
        if (providerIds.isEmpty()) return emptyList()
        val region = providerSources.firstNotNullOfOrNull { it.watchRegion?.takeIf(String::isNotBlank) } ?: "US"
        val base = mapOf(
            "with_watch_providers" to providerIds.joinToString("|"),
            "watch_region" to region
        )
        val todayText = today.toString()
        val popularSince = today.minusYears(POPULAR_YEARS).toString()
        val genreSince = today.minusYears(GENRE_YEARS).toString()

        fun movie(sort: String, params: Map<String, String>) = source("movie", sort, base + params)
        fun series(sort: String, params: Map<String, String>) = source("series", sort, base + params)
        fun recentMovies(since: String) = mapOf(
            "primary_release_date.gte" to since, "primary_release_date.lte" to todayText,
            "vote_count.gte" to MIN_VOTES.toString()
        )
        fun recentSeries(since: String) = mapOf(
            "first_air_date.gte" to since, "first_air_date.lte" to todayText,
            "vote_count.gte" to MIN_VOTES.toString()
        )
        val newest = mapOf("vote_count.gte" to WatchProviderSort.NEWEST_MIN_VOTES.toString())

        val rows = mutableListOf(
            row(service, TOP10_SERIES_ROW_ID, RowKind.TOP10_SERIES, TOP10_SIZE, null,
                series("popularity.desc", recentSeries(popularSince))),
            row(service, TOP10_MOVIES_ROW_ID, RowKind.TOP10_MOVIES, TOP10_SIZE, null,
                movie("popularity.desc", recentMovies(popularSince))),
            row(service, NEW_ROW_ID, RowKind.NEW, ROW_SIZE, CollectionTab.MOVIES,
                movie(WatchProviderSort.MOVIES_NEWEST, newest + ("primary_release_date.lte" to todayText)),
                series(WatchProviderSort.SERIES_NEWEST, newest + ("first_air_date.lte" to todayText)),
                mixed = true)
        )
        providerIds.firstNotNullOfOrNull { ORIGINALS_NETWORKS[it] }?.let { networks ->
            // The service's own series: by network, without the provider filter, since a
            // network's shows are on its service even where the availability data lags.
            rows += row(service, ORIGINALS_ROW_ID, RowKind.ORIGINALS, ROW_SIZE, CollectionTab.SERIES,
                source("series", "popularity.desc", mapOf("with_networks" to networks) + recentSeries(genreSince)))
        }
        rows += row(service, TOP_RATED_SERIES_ROW_ID, RowKind.TOP_RATED_SERIES, ROW_SIZE, CollectionTab.SERIES,
            series("vote_average.desc", mapOf("vote_count.gte" to TOP_RATED_SERIES_VOTES.toString())))
        rows += row(service, TOP_RATED_MOVIES_ROW_ID, RowKind.TOP_RATED_MOVIES, ROW_SIZE, CollectionTab.MOVIES,
            movie("vote_average.desc", mapOf("vote_count.gte" to TOP_RATED_MOVIE_VOTES.toString())))
        genres.forEach { genre ->
            val sources = listOfNotNull(
                genre.movieGenreId?.let { movie("popularity.desc", recentMovies(genreSince) + ("with_genres" to it.toString())) },
                genre.seriesGenreId?.let { series("popularity.desc", recentSeries(genreSince) + ("with_genres" to it.toString())) }
            )
            val tab = if (genre.movieGenreId != null) CollectionTab.MOVIES else CollectionTab.SERIES
            rows += row(service, GENRE_ROW_PREFIX + genre.key, RowKind.GENRE, ROW_SIZE, tab, *sources.toTypedArray(),
                mixed = sources.size > 1, genre = genre)
        }
        return rows
    }

    private fun source(type: String, sort: String, params: Map<String, String>) = CollectionSourceConfig(
        kind = CollectionSourceKind.TMDB_DISCOVER,
        mediaType = type,
        sortBy = sort,
        discoverParams = params
    )

    private fun row(
        service: CatalogConfig,
        id: String,
        kind: RowKind,
        limit: Int,
        gridTab: CollectionTab?,
        vararg sources: CollectionSourceConfig,
        mixed: Boolean = false,
        genre: Genre? = null
    ) = Row(
        id = id,
        kind = kind,
        catalog = CatalogConfig(
            id = "${service.id}#$id",
            title = service.title,
            sourceType = CatalogSourceType.PREINSTALLED,
            isPreinstalled = true,
            kind = CatalogKind.COLLECTION,
            // A genre collection interleaves its movie and series sources; other groups list them in turn.
            collectionGroup = if (mixed) CollectionGroupKind.GENRE else CollectionGroupKind.SERVICE,
            collectionRailKey = "${service.id}#$id",
            collectionSources = sources.toList()
        ),
        limit = limit,
        genre = genre,
        viewAll = gridTab?.let { ViewAll(id, it) }
    )
}
