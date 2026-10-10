package com.arflix.tv.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.MediaSearchResults
import com.arflix.tv.data.repository.PersonMediaSearchResult
import com.arflix.tv.data.repository.RecentSearchRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.util.ContentRating
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import javax.inject.Inject

data class Genre(val id: Int, val name: String)

val MOVIE_GENRES = listOf(
    Genre(28, "Action"), Genre(12, "Adventure"), Genre(16, "Animation"),
    Genre(35, "Comedy"), Genre(80, "Crime"), Genre(99, "Documentary"),
    Genre(18, "Drama"), Genre(10751, "Family"), Genre(14, "Fantasy"),
    Genre(36, "History"), Genre(27, "Horror"), Genre(10402, "Music"),
    Genre(9648, "Mystery"), Genre(10749, "Romance"), Genre(878, "Sci-Fi"),
    Genre(53, "Thriller"), Genre(10752, "War"), Genre(37, "Western")
)
val TV_GENRES = listOf(
    Genre(10759, "Action & Adventure"), Genre(16, "Animation"),
    Genre(35, "Comedy"), Genre(80, "Crime"), Genre(99, "Documentary"),
    Genre(18, "Drama"), Genre(10751, "Family"), Genre(10762, "Kids"),
    Genre(9648, "Mystery"), Genre(10765, "Sci-Fi & Fantasy"),
    Genre(10768, "War & Politics"), Genre(37, "Western")
)
val ALL_GENRES = listOf(
    Genre(28, "Action"), Genre(12, "Adventure"), Genre(16, "Animation"),
    Genre(35, "Comedy"), Genre(80, "Crime"), Genre(99, "Documentary"),
    Genre(18, "Drama"), Genre(10751, "Family"), Genre(14, "Fantasy"),
    Genre(27, "Horror"), Genre(9648, "Mystery"), Genre(10749, "Romance"),
    Genre(878, "Sci-Fi"), Genre(53, "Thriller"), Genre(10752, "War"),
    Genre(37, "Western")
)
val ANIME_GENRES = listOf(
    Genre(28, "Action"), Genre(12, "Adventure"), Genre(35, "Comedy"),
    Genre(18, "Drama"), Genre(14, "Fantasy"), Genre(27, "Horror"),
    Genre(10749, "Romance"), Genre(878, "Sci-Fi"), Genre(9648, "Mystery")
)

data class Country(val code: String, val name: String)
val COUNTRIES = listOf(
    Country("en", "English"), Country("ja", "Japanese"), Country("ko", "Korean"),
    Country("es", "Spanish"), Country("fr", "French"), Country("de", "German"),
    Country("it", "Italian"), Country("pt", "Portuguese"), Country("hi", "Hindi"),
    Country("zh", "Chinese"), Country("tr", "Turkish"), Country("ar", "Arabic"),
    Country("th", "Thai"), Country("nl", "Dutch"), Country("ru", "Russian")
)

enum class DiscoverType(val label: String) { ALL("All"), MOVIES("Movies"), TV_SHOWS("TV Shows"), ANIME("Anime") }
enum class SortOption(val label: String, val apiValue: String) { POPULAR("Popular", "popularity.desc"), TOP_RATED("Top Rated", "vote_average.desc"), NEWEST("Newest", "primary_release_date.desc") }

/** A filter change loads straight away (E4) — debounced so two quick taps cost one load. */
private const val DISCOVER_FILTER_DEBOUNCE_MS = 350L
/** Same floor the "Trending" row uses when the user has not set one: keeps single-vote entries out. */
private const val DISCOVER_GRID_MIN_VOTES = 50
/**
 * How many TMDB pages one scroll step may ask for while "hide watched" throws pages away.
 * Without a cap, a user who has watched everything in a narrow filter would keep the grid
 * fetching to page 500 in one go.
 */
private const val MAX_GRID_PAGE_ATTEMPTS = 5
/** TMDB's own id for the animation genre — an anime request is never without it. */
private const val ANIMATION_GENRE_ID = "16"
/** TMDB keyword id "anime": what separates anime from western animation. */
private const val ANIME_KEYWORD = "210024"
/** TMDB serves at most 500 discover pages; asking beyond that only returns errors. */
private const val TMDB_MAX_DISCOVER_PAGE = 500

// Memoized empty collections to reduce GC pressure
private val EMPTY_MEDIA_ITEMS: List<MediaItem> = emptyList()
private val EMPTY_CATEGORIES: List<Category> = emptyList()
private val EMPTY_LOGO_URLS: Map<String, String> = emptyMap()

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val results: List<MediaItem> = EMPTY_MEDIA_ITEMS,
    val movieResults: List<MediaItem> = EMPTY_MEDIA_ITEMS,
    val tvResults: List<MediaItem> = EMPTY_MEDIA_ITEMS,
    val personResults: List<Category> = EMPTY_CATEGORIES,
    val cardLogoUrls: Map<String, String> = EMPTY_LOGO_URLS,
    val error: String? = null,
    // Discover rows - always 5 rows, dynamically built from active filters
    val discoverCategories: List<Category> = EMPTY_CATEGORIES,
    val discoverLogoUrls: Map<String, String> = EMPTY_LOGO_URLS,
    val isDiscoverLoading: Boolean = false,
    // Filters. The media type is mandatory while discovering (E2): "All" would need two
    // discover calls with two page counters, and TMDB's sort order stops holding once the
    // two halves are merged — which breaks an endlessly paging grid.
    val selectedType: DiscoverType = DiscoverType.MOVIES,
    /** Several genres at once, combined with AND unless [matchAllGenres] is turned off (E3). */
    val selectedGenres: List<Genre> = emptyList(),
    val matchAllGenres: Boolean = true,
    val sortOption: SortOption = SortOption.POPULAR,
    val rating: RatingFilter = RatingFilter(),
    /** The chosen decade (J1). It filters on its own; [year] narrows it further, optionally. */
    val decade: Decade? = null,
    val year: Int? = null,
    /** Movies only — `discover/tv` has no certification parameter at TMDB. */
    val certification: String? = null,
    /**
     * The title's original language as a TMDB code, `null` for "any" (S3: one at a time).
     *
     * The original language, not the app's: it decides which titles the grid shows, never which
     * words the app uses. The two live far apart on purpose — see [DiscoverRequest.language].
     */
    val language: String? = null,
    val hideWatched: Boolean = false,
    // Discover grid - shown instead of the five rows as soon as a filter is set
    val discoverGridItems: List<MediaItem> = EMPTY_MEDIA_ITEMS,
    val isGridLoading: Boolean = false,
    val isGridLoadingMore: Boolean = false,
    val gridEndReached: Boolean = false,
    val gridLoadFailed: Boolean = false,
    val gridScanPaused: Boolean = false,
    // AI
    val aiInterpretation: String? = null,
    val aiResults: List<MediaItem> = EMPTY_MEDIA_ITEMS,
    val isAiSearch: Boolean = false
) {
    /**
     * Rows or grid: the media type alone is not a filter (it is always set), and neither is the
     * sort order — sorting five browse rows that each have their own sort would mean nothing.
     * Everything else switches the rows over to the filtered grid.
     */
    val hasDiscoverFilters: Boolean
        get() = selectedGenres.isNotEmpty() || rating.isSet || decade != null || year != null ||
            certification != null || language != null || hideWatched
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val traktRepository: TraktRepository,
    private val recentSearchRepository: RecentSearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    /** The profile's recent queries, newest first; shown while the search bar is empty. */
    val recentSearches: StateFlow<List<String>> = recentSearchRepository.recentSearches
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    /** A submitted query waiting for its results: only a query that found something is kept. */
    private var pendingRecentSearch: String? = null

    private var searchJob: Job? = null
    private var discoverJob: Job? = null
    private var filterDebounceJob: Job? = null
    private var gridPage = 0
    private var gridGeneration = 0L
    private var gridJob: Job? = null
    private var watchedMarksJob: Job? = null

    private fun cancelGridLoad() {
        gridGeneration++
        gridJob?.cancel()
        gridJob = null
    }
    private var cachedSuggestionQuery = ""
    private var cachedSuggestionResults: List<MediaItem> = EMPTY_MEDIA_ITEMS
    private var cachedPeopleQuery = ""
    private var cachedPeopleResults: List<Category> = EMPTY_CATEGORIES
    private var activeSearchQuery: String? = null
    private var peopleNeedingCredits: List<PersonMediaSearchResult> = emptyList()

    /**
     * The profile's TMDB content language, e.g. "de-DE".
     *
     * The age-rating panel needs the country behind it: certifications are country-specific
     * strings, so which list to offer follows from the same setting the age badge already uses.
     */
    val contentLanguage: String get() = mediaRepository.contentLanguage

    init { startDiscoverLoad() }

    // ── Discover Rows (5 dynamic rows based on filters) ─────────────────

    private fun loadDiscoverRows() {
        discoverJob?.cancel()
        val state = _uiState.value
        _uiState.value = state.copy(isDiscoverLoading = true)

        discoverJob = viewModelScope.launch {
            try {
                val type = state.selectedType
                val genres = genresParam(state.selectedGenres, state.matchAllGenres)

                val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
                val cal = java.util.Calendar.getInstance()
                cal.add(java.util.Calendar.DAY_OF_YEAR, -90)
                val threeMonthsAgo = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(cal.time)
                cal.time = java.util.Date()
                cal.add(java.util.Calendar.YEAR, -1)
                val oneYearAgo = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(cal.time)

                // Each row brings its own sort and vote floor — that is what makes it a row and
                // not a slice of the grid, so the sort chip deliberately does not reach here.
                //
                // The rows stay on the any-release window on purpose (B34): "New Releases" asks
                // what turned up in the last 90 days, and a film that just landed on a platform
                // belongs there. Only the grid, where the user compares the year against the
                // card, moved to the premiere date.
                fun row(sort: String, minVotes: Int, page: Int = 1, from: String? = null) = DiscoverRequest(
                    type = type, genres = genres, sort = sort, minVotes = minVotes, page = page,
                    releaseDateGte = from, releaseDateLte = today
                )

                val categories = withContext(Dispatchers.IO) {
                    coroutineScope {
                        // Row titles stay English: they are part of the Category id used
                        // as the row's focus key. SearchScreen localizes them for display
                        // only (localizedDiscoverRowTitle).
                        // Row 1: Trending - popular with minimum votes to filter garbage
                        val row1 = async { buildRow("Trending", row("popularity.desc", 50)) }
                        // Row 2: Popular This Year - recent + popular, no obscure stuff
                        val row2 = async { buildRow("Popular This Year", row("popularity.desc", 20, from = oneYearAgo)) }
                        // Row 3: Top Rated - high quality, well-known titles
                        val row3 = async { buildRow("Top Rated", row("vote_average.desc", 1000)) }
                        // Row 4: New Releases - last 90 days ONLY, must be actually released (date <= today)
                        val row4 = async { buildRow("New Releases", row("popularity.desc", 10, from = threeMonthsAgo)) }
                        // Row 5: Hidden Gems - good ratings but less mainstream
                        val row5 = async { buildRow("Hidden Gems", row("vote_average.desc", 200, page = 2)) }
                        listOfNotNull(row1.await(), row2.await(), row3.await(), row4.await(), row5.await())
                    }
                }
                categories.forEach { cat -> cat.items.forEach { mediaRepository.cacheItem(it) } }
                val isWatched = watchedMatcher()
                _uiState.value = _uiState.value.copy(discoverCategories = markWatched(categories, isWatched), isDiscoverLoading = false)
                // Fetch logos for top items in each row (background, non-blocking)
                launch(Dispatchers.IO) {
                    val slots = Semaphore(3)
                    val allItems = categories.flatMap { it.items }.distinctBy { "${it.mediaType}_${it.id}" }.take(24)
                    val logos = allItems.map { item ->
                        async {
                            val key = "${item.mediaType}_${item.id}"
                            val logo = slots.withPermit {
                                try { withTimeoutOrNull(2_000) { mediaRepository.getLogoUrl(item.mediaType, item.id) } }
                                catch (e: CancellationException) { throw e }
                                catch (_: Exception) { null }
                            }
                            if (!logo.isNullOrBlank()) {
                                mediaRepository.cacheLogoUrl(item.mediaType, item.id, logo)
                                key to logo
                            } else null
                        }
                    }.awaitAll().filterNotNull().toMap()
                    _uiState.update { it.copy(discoverLogoUrls = it.discoverLogoUrls + logos) }
                }
            } catch (e: CancellationException) { throw e }
            catch (_: Exception) {
                _uiState.value = _uiState.value.copy(isDiscoverLoading = false)
            }
        }
    }

    private suspend fun buildRow(title: String, request: DiscoverRequest): Category? {
        return try {
            val items = fetchDiscoverPage(request)
            if (items.isEmpty()) null
            else Category(
                id = "${request.type}_${title}_${request.genres}_${request.page}",
                title = title,
                items = items.take(20)
            )
        } catch (e: CancellationException) { throw e }
        catch (_: Exception) { null }
    }

    /**
     * Everything one discover request needs, in one object.
     *
     * The wrappers in `MediaRepository` are called positionally in a dozen places, so a
     * parameter added in the middle silently shifts `page` and everything after it (T9).
     * Collecting the filters here means a new filter touches this class and nothing else.
     */
    private data class DiscoverRequest(
        val type: DiscoverType,
        val genres: String?,
        val sort: String,
        val minVotes: Int?,
        val page: Int,
        val minRating: Double? = null,
        val maxRating: Double? = null,
        val year: Int? = null,
        val certification: String? = null,
        val certificationCountry: String? = null,
        /**
         * The original language wanted, or `null` for any of them.
         *
         * `MediaRepository` calls its parameter `language` too, and there it becomes
         * `with_original_language` — the language a title was made in. The language the app is
         * read in travels beside it as `contentLanguage` and is none of this class's business
         * (T8). Reading this name as "the app's language" builds the filter the wrong way round.
         */
        val language: String? = null,
        val releaseDateGte: String? = null,
        val releaseDateLte: String? = null,
        // The same window, but measured on the FIRST release instead of any release. The grid
        // uses this one because the year on the card is the first release too; the browse rows
        // above keep the old pair, where "anything out in the last 90 days" is what is meant.
        val premiereDateGte: String? = null,
        val premiereDateLte: String? = null
    )

    /**
     * The single place that turns a filter set into TMDB items. Both the browse rows and the
     * filtered grid go through it, so the movies/series/anime/all split and the movie→series
     * genre remap exist exactly once instead of drifting apart in two copies.
     */
    private suspend fun fetchDiscoverPage(request: DiscoverRequest): List<MediaItem> {
        val movieGenres = request.genres
        val tvGenres = mapMovieGenresToTvGenres(request.genres)
        return when (request.type) {
            DiscoverType.MOVIES -> discoverMoviesFor(request, movieGenres)
            DiscoverType.TV_SHOWS -> discoverTvFor(request, tvGenres)
            DiscoverType.ANIME -> discoverTvFor(request, buildAnimeGenre(tvGenres), keywords = ANIME_KEYWORD)
            DiscoverType.ALL -> coroutineScope {
                val m = async { discoverMoviesFor(request, movieGenres) }
                val t = async { discoverTvFor(request, tvGenres) }
                interleave(m.await(), t.await())
            }
        }
    }

    private suspend fun discoverMoviesFor(request: DiscoverRequest, genres: String?): List<MediaItem> =
        // Named throughout on purpose (T9): the wrapper takes thirteen parameters and a
        // positional call silently shifts `page` the day somebody inserts one in the middle.
        mediaRepository.discoverMovies(
            genres = genres,
            sortBy = request.sort,
            minVoteCount = request.minVotes,
            page = request.page,
            year = request.year,
            releaseDateLte = request.releaseDateLte,
            releaseDateGte = request.releaseDateGte,
            primaryReleaseDateLte = request.premiereDateLte,
            primaryReleaseDateGte = request.premiereDateGte,
            minVoteAverage = request.minRating,
            maxVoteAverage = request.maxRating,
            certificationCountry = request.certification?.let { request.certificationCountry },
            certificationLte = request.certification,
            language = request.language
        )

    private suspend fun discoverTvFor(
        request: DiscoverRequest,
        genres: String?,
        keywords: String? = null
    ): List<MediaItem> =
        mediaRepository.discoverTv(
            genres = genres,
            sortBy = request.sort,
            minVoteCount = request.minVotes,
            page = request.page,
            year = request.year,
            keywords = keywords,
            airDateLte = request.releaseDateLte,
            airDateGte = request.releaseDateGte,
            firstAirDateLte = request.premiereDateLte,
            firstAirDateGte = request.premiereDateGte,
            minVoteAverage = request.minRating,
            maxVoteAverage = request.maxRating,
            language = request.language
        )

    /**
     * Every id of a `with_genres` value translated into the series numbering, separator kept.
     *
     * The separator carries E3 (comma = AND, pipe = OR), so it has to survive the remap — which
     * is why this splits on both instead of assuming one of them.
     */
    private fun mapMovieGenresToTvGenres(genres: String?): String? {
        if (genres.isNullOrBlank()) return genres
        val separator = if (genres.contains('|')) "|" else ","
        return genres.split(',', '|')
            .mapNotNull { it.trim().toIntOrNull() }
            .map { remapGenreId(it, DiscoverType.TV_SHOWS) }
            .distinct()
            .joinToString(separator) { it.toString() }
    }

    /** Anime is "animation plus whatever else was asked for", and animation is never optional. */
    private fun buildAnimeGenre(genres: String?): String = when {
        genres.isNullOrBlank() -> ANIMATION_GENRE_ID
        genres.split(',', '|').any { it.trim() == ANIMATION_GENRE_ID } -> genres
        // The comma is deliberate even in "match any" mode: animation is a floor, not a choice.
        else -> "$ANIMATION_GENRE_ID,$genres"
    }

    // ── Discover grid (shown as soon as a filter is set) ────────────────

    private fun loadDiscoverGrid() {
        discoverJob?.cancel()
        cancelGridLoad()
        gridPage = 0
        _uiState.value = _uiState.value.copy(isGridLoading = true, isGridLoadingMore = false, gridEndReached = false, gridLoadFailed = false, gridScanPaused = false)
        val generation = gridGeneration
        gridJob = viewModelScope.launch { fetchGridPage(append = false, generation) }
    }

    /**
     * Endless paging (H9). The grid asks for the next TMDB page when the user nears its end;
     * everything that could turn that into a request storm is guarded here.
     */
    fun loadMoreDiscoverGrid() {
        val state = _uiState.value
        if (state.query.isNotEmpty() || !state.hasDiscoverFilters) return
        if (state.isGridLoading || state.isGridLoadingMore || state.gridEndReached || state.gridLoadFailed || state.gridScanPaused) return
        _uiState.value = state.copy(isGridLoadingMore = true)
        val generation = gridGeneration
        gridJob = viewModelScope.launch { fetchGridPage(append = true, generation) }
    }

    fun retryDiscoverGrid() {
        val state = _uiState.value
        if ((!state.gridLoadFailed && !state.gridScanPaused) || state.query.isNotEmpty() || !state.hasDiscoverFilters) return
        val append = gridPage > 0
        _uiState.value = state.copy(gridLoadFailed = false, gridScanPaused = false, isGridLoading = !append, isGridLoadingMore = append)
        val generation = gridGeneration
        gridJob = viewModelScope.launch { fetchGridPage(append, generation) }
    }

    private suspend fun fetchGridPage(append: Boolean, generation: Long) {
        val started = _uiState.value
        val signature = filterSignature(started)
        try {
            val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
            val startPage = gridPage
            val collected = withContext(Dispatchers.IO) { collectGridPages(started, today, startPage) }
            val isWatched = watchedMatcher()
            val current = _uiState.value
            // The filter moved on while this page was in flight — its answer is stale.
            if (generation != gridGeneration || filterSignature(current) != signature || current.query.isNotEmpty()) return
            collected.items.forEach { mediaRepository.cacheItem(it) }
            val existing = if (append) current.discoverGridItems else EMPTY_MEDIA_ITEMS
            val known = existing.mapTo(HashSet()) { it.mediaType to it.id }
            val fresh = collected.items.filter { known.add(it.mediaType to it.id) }
            gridPage = collected.lastPage
            _uiState.value = current.copy(
                discoverGridItems = existing + markWatched(fresh, isWatched),
                isGridLoading = false,
                isGridLoadingMore = false,
                gridEndReached = collected.endReached,
                gridScanPaused = fresh.isEmpty() && !collected.endReached
            )
        } catch (e: CancellationException) { throw e }
        catch (_: Exception) {
            val current = _uiState.value
            if (generation != gridGeneration || filterSignature(current) != signature || current.query.isNotEmpty()) return
            // Keep the page counter and existing titles; only explicit Retry resumes loading.
            _uiState.value = current.copy(isGridLoading = false, isGridLoadingMore = false, gridLoadFailed = true)
        }
    }

    /** One batch of grid items plus where the paging got to. */
    private data class GridPageResult(val items: List<MediaItem>, val lastPage: Int, val endReached: Boolean)

    /**
     * Fetches pages until there is something to show.
     *
     * "Hide watched" is not a TMDB parameter — it can only be applied after the answer arrives,
     * and a page where every title is already watched would otherwise hand the grid nothing and
     * look like the end of the list. So the paging keeps going for a bounded number of extra
     * pages instead, which is the price of that filter and the reason for the cap.
     */
    private suspend fun collectGridPages(state: SearchUiState, today: String, startPage: Int): GridPageResult {
        val watchedFilter = if (state.hideWatched) watchedMatcher() else null
        val collected = mutableListOf<MediaItem>()
        var page = startPage
        var endReached = false
        var attempts = 0
        while (attempts < MAX_GRID_PAGE_ATTEMPTS) {
            attempts++
            page++
            if (page > TMDB_MAX_DISCOVER_PAGE) { endReached = true; break }
            val items = fetchDiscoverPage(gridRequestFor(state, page, today))
            if (items.isEmpty()) { endReached = true; break }
            collected += if (watchedFilter == null) items else items.filterNot { watchedFilter(it) }
            if (collected.isNotEmpty()) break
        }
        return GridPageResult(collected, page, endReached)
    }

    private fun gridRequestFor(state: SearchUiState, page: Int, today: String): DiscoverRequest {
        // A release date in the future has no rating and usually no poster either, so the grid
        // stays on what is actually out — except when a year is asked for explicitly. A decade
        // turns that cap into a window; the rule itself lives in releaseWindowFor.
        //
        // The window travels as the PREMIERE date (B34): asked by any release, a film from 1994
        // that came back to cinemas in 2021 answered the 2020s and then printed 1994 on its own
        // card. The browse rows above are a different question and keep the old pair.
        val window = releaseWindowFor(state.decade, state.year, today)
        return DiscoverRequest(
            type = state.selectedType,
            genres = genresParam(state.selectedGenres, state.matchAllGenres),
            sort = state.sortOption.apiValue,
            minVotes = state.rating.minVotes ?: DISCOVER_GRID_MIN_VOTES,
            page = page,
            minRating = state.rating.min,
            maxRating = state.rating.max,
            year = state.year,
            certification = state.certification.takeIf { supportsCertification(state.selectedType) },
            certificationCountry = ContentRating.regionOf(mediaRepository.contentLanguage),
            language = state.language,
            premiereDateGte = window.from,
            premiereDateLte = window.to
        )
    }

    /**
     * Tells a watched title from an unwatched one.
     *
     * A film is watched when it is in the watched list. A series has no such single mark, so
     * "watched" means "already started" — the reading that actually helps while discovering,
     * since a series you are halfway through is not something you need offered again. These are
     * the two lookups Home marks its cards by.
     */
    private suspend fun watchedMatcher(): (MediaItem) -> Boolean = withContext(Dispatchers.Default) {
        val watchedMovies = traktRepository.getWatchedMoviesFromCache()
        // Index the history once off Main, instead of scanning it for every distinct show.
        val startedShows = traktRepository.getWatchedEpisodesFromCache().mapNotNullTo(HashSet<Int>()) { key ->
            if (key.startsWith("show_tmdb:")) {
                key.removePrefix("show_tmdb:").substringBefore(':', "").toIntOrNull()
            } else null
        }
        val matches: (MediaItem) -> Boolean = { item ->
            when (item.mediaType) {
                MediaType.MOVIE -> item.id in watchedMovies
                MediaType.TV -> item.id in startedShows
            }
        }
        matches
    }

    /**
     * Sets [MediaItem.isWatched] so the cards show the same watched tick as on Home.
     *
     * TMDB answers never carry the flag, so without this every title on this screen looked
     * unwatched. Applied only to what goes into the UI state: the items handed to
     * [MediaRepository.cacheItem] and the cached search answers stay unmarked, so a later
     * publish reads the watched list as it is then, not as it was when the answer arrived.
     * An unchanged list comes back as the same instance, so a refresh that finds nothing new
     * recomposes nothing.
     */
    private fun markWatched(
        items: List<MediaItem>,
        isWatched: (MediaItem) -> Boolean
    ): List<MediaItem> {
        if (items.none { it.isWatched != isWatched(it) }) return items
        return items.map { item ->
            val watched = isWatched(item)
            if (item.isWatched == watched) item else item.copy(isWatched = watched)
        }
    }

    @JvmName("markWatchedRows")
    private fun markWatched(
        rows: List<Category>,
        isWatched: (MediaItem) -> Boolean
    ): List<Category> {
        if (rows.none { row -> row.items.any { it.isWatched != isWatched(it) } }) return rows
        return rows.map { row -> row.copy(items = markWatched(row.items, isWatched)) }
    }

    /**
     * Brings the ticks up to date on everything already on screen.
     *
     * The screen keeps its rows, grid and results for the session (E5), so a title watched after
     * opening it from here came back without its tick. Called whenever the screen resumes; it
     * also covers the watched list not being loaded yet when the results first arrived, the
     * same list Home loads before marking its own cards.
     */
    fun refreshWatchedMarks() {
        watchedMarksJob?.cancel()
        watchedMarksJob = viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { traktRepository.initializeWatchedCache() }
            } catch (e: CancellationException) { throw e }
            catch (_: Exception) { /* keep whatever the cache already holds */ }
            val isWatched = watchedMatcher()
            withContext(Dispatchers.Default) {
                // Retained grids can be large. Re-mark the latest state off Main without
                // restoring an old query or dropping a page published during the refresh.
                _uiState.update { state ->
                    state.copy(
                        discoverCategories = markWatched(state.discoverCategories, isWatched),
                        discoverGridItems = markWatched(state.discoverGridItems, isWatched),
                        results = markWatched(state.results, isWatched),
                        movieResults = markWatched(state.movieResults, isWatched),
                        tvResults = markWatched(state.tvResults, isWatched),
                        personResults = markWatched(state.personResults, isWatched),
                        aiResults = markWatched(state.aiResults, isWatched)
                    )
                }
            }
        }
    }

    /**
     * Identifies the filter set a request was started for. A page that comes back after the
     * filters moved on belongs to the old set and is dropped, so every filter has to appear
     * here — one missing field is a stale page landing in the grid.
     */
    private fun filterSignature(state: SearchUiState): String = listOf(
        state.selectedType,
        state.selectedGenres.joinToString(",") { it.id.toString() },
        state.matchAllGenres,
        state.sortOption,
        state.rating.min, state.rating.max, state.rating.minVotes,
        state.decade,
        state.year,
        state.certification,
        state.hideWatched
    ).joinToString("|")

    // ── Filters → reload rows or grid ───────────────────────────────────

    /**
     * One entry point for every filter change. E4: no "apply" button — the change loads
     * immediately, debounced by [DISCOVER_FILTER_DEBOUNCE_MS] so two quick taps cost one load
     * instead of two (and spare the logo requests of the discarded one).
     */
    private fun applyDiscoverSelection(debounce: Boolean = true) {
        cancelGridLoad()
        filterDebounceJob?.cancel()
        discoverJob?.cancel()
        gridPage = 0
        val state = _uiState.value
        _uiState.value = state.copy(
            discoverCategories = EMPTY_CATEGORIES,
            discoverLogoUrls = EMPTY_LOGO_URLS,
            discoverGridItems = EMPTY_MEDIA_ITEMS,
            isDiscoverLoading = !state.hasDiscoverFilters,
            isGridLoading = state.hasDiscoverFilters,
            isGridLoadingMore = false,
            gridEndReached = false,
            gridLoadFailed = false,
            gridScanPaused = false
        )
        if (!debounce) { startDiscoverLoad(); return }
        filterDebounceJob = viewModelScope.launch {
            delay(DISCOVER_FILTER_DEBOUNCE_MS)
            startDiscoverLoad()
        }
    }

    private fun startDiscoverLoad() {
        if (_uiState.value.hasDiscoverFilters) loadDiscoverGrid() else loadDiscoverRows()
    }

    fun selectType(type: DiscoverType) {
        val state = _uiState.value
        if (state.selectedType == type) return
        // Genre ids differ per media type (movie "Action" 28 vs. series "Action & Adventure"
        // 10759). Until now the type switch dropped the genre outright; it is remapped instead,
        // and only a genre with no counterpart at all is let go (FUND C in the file).
        _uiState.value = state.copy(
            selectedType = type,
            selectedGenres = remapGenresForType(state.selectedGenres, type),
            // TMDB can only filter certifications for movies, so this cannot survive the switch.
            certification = if (supportsCertification(type)) state.certification else null
        )
        applyDiscoverSelection()
    }

    /** Adds or removes one genre — tapping a set genre again is how it is cleared. */
    fun toggleGenre(genre: Genre) {
        val state = _uiState.value
        val genres = if (state.selectedGenres.any { it.id == genre.id }) {
            state.selectedGenres.filterNot { it.id == genre.id }
        } else {
            state.selectedGenres + genre
        }
        _uiState.value = state.copy(selectedGenres = genres)
        applyDiscoverSelection()
    }

    /** E3: match all chosen genres (TMDB's comma) or any of them (its pipe). */
    fun setMatchAllGenres(matchAll: Boolean) {
        val state = _uiState.value
        if (state.matchAllGenres == matchAll) return
        _uiState.value = state.copy(matchAllGenres = matchAll)
        // Nothing changes for a single genre, so the reload would only cost a request.
        if (state.selectedGenres.size > 1) applyDiscoverSelection()
    }

    fun selectSort(sort: SortOption) {
        val state = _uiState.value
        if (state.sortOption == sort) return
        _uiState.value = state.copy(sortOption = sort)
        // The sort order only reaches the grid; the browse rows each carry their own.
        if (state.hasDiscoverFilters) applyDiscoverSelection()
    }

    fun setRating(rating: RatingFilter) {
        _uiState.value = _uiState.value.copy(rating = rating)
        applyDiscoverSelection()
    }

    /**
     * Picks a decade, and drops an exact year that no longer sits inside it.
     *
     * Keeping it would leave the chip saying "2014" under the heading "2020s" and the grid
     * showing neither — the year is the narrower filter, so it wins until it stops fitting.
     */
    fun selectDecade(decade: Decade?) {
        val year = _uiState.value.year?.takeIf { decade != null && it in decade.start..decade.end }
        _uiState.value = _uiState.value.copy(decade = decade, year = year)
        applyDiscoverSelection()
    }

    fun selectYear(year: Int?) {
        _uiState.value = _uiState.value.copy(year = year)
        applyDiscoverSelection()
    }

    fun selectCertification(certification: String?) {
        if (!supportsCertification(_uiState.value.selectedType)) return
        _uiState.value = _uiState.value.copy(certification = certification)
        applyDiscoverSelection()
    }

    /**
     * Picks the original language a title has to be in, or drops the filter again with `null`.
     *
     * One at a time (S3), so a second press on another tile replaces the first rather than
     * adding to it — and only ever one code leaves for TMDB.
     */
    fun selectLanguage(code: String?) {
        val state = _uiState.value
        if (state.language == code) return
        _uiState.value = state.copy(language = code)
        applyDiscoverSelection()
    }

    fun setHideWatched(hide: Boolean) {
        val state = _uiState.value
        if (state.hideWatched == hide) return
        _uiState.value = state.copy(hideWatched = hide)
        applyDiscoverSelection()
    }

    /**
     * Back to the browse rows in one step — the way out of a filter set that found nothing.
     *
     * Every field [SearchUiState.hasDiscoverFilters] counts has to be cleared here. One left
     * behind is worse than no reset at all: the guard above lets the call through, the screen
     * stays on the grid, and nothing on it explains why. The media type deliberately survives —
     * it is always set, so it is not one of the filters this undoes.
     */
    fun clearDiscoverFilters() {
        val state = _uiState.value
        if (!state.hasDiscoverFilters) return
        _uiState.value = state.copy(
            selectedGenres = emptyList(),
            rating = RatingFilter(),
            decade = null,
            year = null,
            certification = null,
            language = null,
            hideWatched = false
        )
        applyDiscoverSelection(debounce = false)
    }

    // ── Search + AI ─────────────────────────────────────────────────────

    fun addChar(char: String) { updateQuery(_uiState.value.query + char) }
    fun deleteChar() { if (_uiState.value.query.isNotEmpty()) updateQuery(_uiState.value.query.dropLast(1)) }

    fun updateQuery(newQuery: String) {
        if (newQuery == _uiState.value.query) return
        searchJob?.cancel()
        activeSearchQuery = null
        _uiState.value = _uiState.value.copy(query = newQuery, isAiSearch = false, aiInterpretation = null, aiResults = EMPTY_MEDIA_ITEMS)
        if (newQuery.trim().isEmpty()) {
            clearSearch()
            return
        }
        filterDebounceJob?.cancel()
        discoverJob?.cancel()
        cancelGridLoad()
        _uiState.value = _uiState.value.copy(isLoading = true, isDiscoverLoading = false,
            isGridLoading = false, isGridLoadingMore = false, error = null, results = EMPTY_MEDIA_ITEMS,
            movieResults = EMPTY_MEDIA_ITEMS, tvResults = EMPTY_MEDIA_ITEMS,
            personResults = EMPTY_CATEGORIES, cardLogoUrls = EMPTY_LOGO_URLS)
        debounceSearch()
    }

    fun search() {
        val query = _uiState.value.query.trim(); if (query.isEmpty()) return
        if (searchJob?.isActive == true && activeSearchQuery == query && _uiState.value.error == null) return
        val aiQuery = parseSmartQuery(query); if (aiQuery != null) { executeSmartSearch(aiQuery); return }
        searchJob?.cancel()
        activeSearchQuery = query
        searchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = it.results.isEmpty(), error = null, isAiSearch = false) }
            try {
                val nativeResults = if (cachedSuggestionQuery != query || cachedPeopleQuery != query) {
                    async(Dispatchers.IO) { mediaRepository.searchNativeAddonItems(query) }
                } else null
                if (cachedSuggestionQuery != query || cachedPeopleQuery != query) {
                    val response = try {
                        withContext(Dispatchers.IO) { mediaRepository.searchWithPeople(query) }
                    } catch (e: CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        val native = nativeResults?.await().orEmpty()
                        if (native.isEmpty()) throw e
                        MediaSearchResults(native, emptyList())
                    }
                    val sorted = withContext(Dispatchers.Default) { rankSearchResults(query, response.items) }
                    cachedSuggestionQuery = query
                    cachedSuggestionResults = sorted
                    cachedPeopleQuery = query
                    peopleNeedingCredits = response.people.filter { it.items.isEmpty() }
                    cachedPeopleResults = response.people.filter { it.items.isNotEmpty() }
                        .map { Category("person_${it.personId}", it.name, it.items) }
                }
                val sorted = cachedSuggestionResults
                val peopleRows = cachedPeopleResults
                val isWatched = watchedMatcher()
                val marked = markWatched(sorted, isWatched)
                val markedPeople = markWatched(peopleRows, isWatched)
                _uiState.update { it.copy(isLoading = sorted.isEmpty() && peopleRows.isEmpty() && peopleNeedingCredits.isNotEmpty(), results = marked,
                    movieResults = marked.filter { item -> item.mediaType == MediaType.MOVIE },
                    tvResults = marked.filter { item -> item.mediaType == MediaType.TV }, personResults = markedPeople) }
                if (sorted.isNotEmpty() || peopleRows.isNotEmpty()) savePendingRecentSearch(query)

                // Cards are usable now. Bounded, cancellable logo enrichment never replaces the rows.
                val slots = Semaphore(3)
                val top = (sorted + peopleRows.flatMap { it.items }).distinctBy { it.mediaType to it.id }.take(12)
                coroutineScope {
                    launch {
                        val native = nativeResults?.await().orEmpty()
                        if (native.isEmpty()) return@launch
                        coroutineContext.ensureActive()
                        val merged = rankSearchResults(query,
                            (cachedSuggestionResults + native).distinctBy { it.mediaType to it.id })
                        cachedSuggestionResults = merged
                        val markedMerged = markWatched(merged, watchedMatcher())
                        _uiState.update { it.copy(isLoading = false, results = markedMerged,
                            movieResults = markedMerged.filter { item -> item.mediaType == MediaType.MOVIE },
                            tvResults = markedMerged.filter { item -> item.mediaType == MediaType.TV }) }
                        savePendingRecentSearch(query)
                    }
                    launch {
                        for (person in peopleNeedingCredits) {
                            val credits = try {
                                withTimeoutOrNull(2_000) {
                                    withContext(Dispatchers.IO) { mediaRepository.getPersonDetails(person.personId).knownFor }
                                }.orEmpty()
                            } catch (e: CancellationException) { throw e }
                            catch (_: Exception) { emptyList() }
                            if (credits.isNotEmpty()) {
                                val row = Category("person_${person.personId}", person.name, credits.distinctBy { it.mediaType to it.id })
                                cachedPeopleResults = cachedPeopleResults + row
                                savePendingRecentSearch(query)
                                val creditsWatched = watchedMatcher()
                                _uiState.update { it.copy(personResults = it.personResults + row.copy(items = markWatched(row.items, creditsWatched)), isLoading = false) }
                            }
                        }
                        peopleNeedingCredits = emptyList()
                        _uiState.update { it.copy(isLoading = false) }
                    }
                    top.forEach { item -> launch {
                        val key = "${item.mediaType}_${item.id}"
                        val logo = slots.withPermit {
                            try {
                                withTimeoutOrNull(2_000) {
                                    withContext(Dispatchers.IO) { mediaRepository.getLogoUrl(item.mediaType, item.id) }
                                }
                            } catch (e: CancellationException) { throw e }
                            catch (_: Exception) { null }
                        }
                        if (!logo.isNullOrBlank()) {
                            _uiState.update { it.copy(cardLogoUrls = it.cardLogoUrls + (key to logo)) }
                        }
                    } }
                }
            } catch (e: CancellationException) { throw e }
            catch (e: Exception) { _uiState.update { it.copy(isLoading = false, error = e.message) } }
        }
    }

    private data class SmartQuery(val interpretation: String, val type: DiscoverType, val genreId: String?, val sort: String, val minVotes: Int?, val limit: Int?, val similarTo: String?)

    private fun parseSmartQuery(raw: String): SmartQuery? {
        if (!isSmartDiscoveryQuery(raw)) return null
        val q = raw.lowercase().trim()
        val genreKeywords = mapOf("horror" to "27", "comedy" to "35", "action" to "28", "drama" to "18", "thriller" to "53", "sci-fi" to "878", "science fiction" to "878", "romance" to "10749", "animation" to "16", "anime" to "16", "documentary" to "99", "crime" to "80", "fantasy" to "14", "adventure" to "12", "mystery" to "9648", "war" to "10752", "western" to "37", "family" to "10751", "history" to "36")
        val likeMatch = SearchRegexes.LIKE_MATCH_REGEX.find(q)
        if (likeMatch != null) { val t = likeMatch.groupValues[1].trim(); return SmartQuery("Similar to \"${t.replaceFirstChar { it.uppercase() }}\"", if (q.contains("show") || q.contains("series")) DiscoverType.TV_SHOWS else DiscoverType.MOVIES, null, "popularity.desc", null, null, t) }
        if (!(q.contains("top") || q.contains("best") || q.contains("popular") || q.contains("trending") || q.contains("new") || q.contains("latest"))) return null
        var gId: String? = null; var gName: String? = null; for ((kw, id) in genreKeywords) { if (q.contains(kw)) { gId = id; gName = kw.replaceFirstChar { it.uppercase() }; break } }
        if (gId == null && !q.contains("movie") && !q.contains("show") && !q.contains("series") && !q.contains("film") && !q.contains("trending") && !q.contains("anime")) return null
        val isAnime = q.contains("anime"); val isTV = q.contains("show") || q.contains("series"); val isMovie = q.contains("movie") || q.contains("film")
        val type = when { isAnime -> DiscoverType.ANIME; isTV && !isMovie -> DiscoverType.TV_SHOWS; isMovie && !isTV -> DiscoverType.MOVIES; else -> DiscoverType.ALL }
        val limit = SearchRegexes.LIMIT_MATCH_REGEX.find(q)?.groupValues?.get(1)?.toIntOrNull()
        val sort = when { q.contains("best") || q.contains("top rated") || limit != null -> "vote_average.desc"; q.contains("new") || q.contains("latest") -> if (isTV || isAnime) "first_air_date.desc" else "primary_release_date.desc"; else -> "popularity.desc" }
        val parts = mutableListOf<String>(); if (limit != null) parts.add("Top $limit"); if (sort == "vote_average.desc" && limit == null) parts.add("Best") else if (sort.contains("date")) parts.add("Newest") else parts.add("Popular")
        if (gName != null) parts.add(gName); parts.add(when(type) { DiscoverType.MOVIES -> "Movies"; DiscoverType.TV_SHOWS -> "Series"; DiscoverType.ANIME -> "Anime"; DiscoverType.ALL -> "Movies & Series" })
        return SmartQuery(parts.joinToString(" "), type, gId, sort, if (sort == "vote_average.desc") 500 else null, limit, null)
    }

    private fun executeSmartSearch(sq: SmartQuery) {
        val query = _uiState.value.query.trim()
        searchJob?.cancel(); searchJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, isAiSearch = true, aiInterpretation = sq.interpretation, error = null, movieResults = EMPTY_MEDIA_ITEMS, tvResults = EMPTY_MEDIA_ITEMS, personResults = EMPTY_CATEGORIES)
            try {
                val items = withContext(Dispatchers.IO) {
                    if (sq.similarTo != null) { val r = mediaRepository.search(sq.similarTo); val m = r.firstOrNull(); if (m != null) mediaRepository.getSimilar(m.mediaType, m.id) else EMPTY_MEDIA_ITEMS }
                    else {
                        val tvGenre = mapMovieGenresToTvGenres(sq.genreId)
                        when (sq.type) {
                            DiscoverType.MOVIES -> mediaRepository.discoverMovies(sq.genreId, sq.sort, sq.minVotes, 1)
                            DiscoverType.TV_SHOWS -> mediaRepository.discoverTv(tvGenre, sq.sort, sq.minVotes, 1)
                            DiscoverType.ANIME -> mediaRepository.discoverTv(buildAnimeGenre(tvGenre), sq.sort, sq.minVotes, 1, keywords = ANIME_KEYWORD)
                            DiscoverType.ALL -> {
                                coroutineScope {
                                    val a = async { mediaRepository.discoverMovies(sq.genreId, sq.sort, sq.minVotes, 1) }
                                    val b = async { mediaRepository.discoverTv(tvGenre, sq.sort, sq.minVotes, 1) }
                                    interleave(a.await(), b.await())
                                }
                            }
                        }
                    }
                }
                items.forEach { mediaRepository.cacheItem(it) }
                val isWatched = watchedMatcher()
                _uiState.value = _uiState.value.copy(isLoading = false, aiResults = markWatched(if (sq.limit != null) items.take(sq.limit) else items, isWatched))
                if (items.isNotEmpty()) savePendingRecentSearch(query)
            } catch (e: Exception) { if (e is kotlinx.coroutines.CancellationException) throw e
 _uiState.value = _uiState.value.copy(isLoading = false, error = e.message) }
        }
    }

    // ── Recent searches ─────────────────────────────────────────────────

    /**
     * Keeps the current query once it has results: on submit, and when a result is opened.
     * Typing alone never saves, or every pause on the remote keyboard would land in the list.
     */
    fun rememberSearch() {
        val query = _uiState.value.query.trim()
        if (query.length < MIN_RECENT_SEARCH_LENGTH) return
        pendingRecentSearch = query
        // A changed query clears the results (updateQuery), so any on screen are this query's.
        val state = _uiState.value
        val hasResults = if (state.isAiSearch) state.aiResults.isNotEmpty()
            else state.results.isNotEmpty() || state.personResults.isNotEmpty()
        if (hasResults) savePendingRecentSearch(query)
    }

    /** Searches a recent query straight away, without the typing debounce. */
    fun searchRecent(query: String) {
        val trimmed = query.trim()
        if (trimmed.isEmpty()) return
        pendingRecentSearch = trimmed
        updateQuery(trimmed)
        search()
    }

    fun clearRecentSearches() {
        viewModelScope.launch { recentSearchRepository.clear() }
    }

    private fun savePendingRecentSearch(query: String) {
        if (!pendingRecentSearch.equals(query, ignoreCase = true)) return
        pendingRecentSearch = null
        viewModelScope.launch { recentSearchRepository.add(query) }
    }

    private fun debounceSearch() { searchJob?.cancel(); searchJob = viewModelScope.launch { delay(260); search() } }

    fun clearSearch() {
        cancelGridLoad()
        searchJob?.cancel()
        activeSearchQuery = null
        peopleNeedingCredits = emptyList()
        cachedSuggestionQuery = ""; cachedSuggestionResults = EMPTY_MEDIA_ITEMS
        cachedPeopleQuery = ""; cachedPeopleResults = EMPTY_CATEGORIES
        _uiState.value = _uiState.value.copy(query = "", isLoading = false, isGridLoading = false, isGridLoadingMore = false, results = EMPTY_MEDIA_ITEMS,
            movieResults = EMPTY_MEDIA_ITEMS, tvResults = EMPTY_MEDIA_ITEMS, personResults = EMPTY_CATEGORIES,
            cardLogoUrls = EMPTY_LOGO_URLS, error = null, isAiSearch = false, aiInterpretation = null, aiResults = EMPTY_MEDIA_ITEMS)
        val state = _uiState.value
        if (state.hasDiscoverFilters) {
            if (state.discoverGridItems.isEmpty()) loadDiscoverGrid()
        } else if (state.discoverCategories.isEmpty()) {
            loadDiscoverRows()
        }
    }
    fun getGenresForType(): List<Genre> = when (_uiState.value.selectedType) { DiscoverType.MOVIES -> MOVIE_GENRES; DiscoverType.TV_SHOWS -> TV_GENRES; DiscoverType.ALL -> ALL_GENRES; DiscoverType.ANIME -> ANIME_GENRES }
    private fun interleave(a: List<MediaItem>, b: List<MediaItem>): List<MediaItem> { val r = mutableListOf<MediaItem>(); for (i in 0 until maxOf(a.size, b.size)) { if (i < a.size) r.add(a[i]); if (i < b.size) r.add(b[i]) }; return r }
}

/** A single letter is a search still being typed, not one worth offering again. */
private const val MIN_RECENT_SEARCH_LENGTH = 2

private object SearchRegexes {
    val LIKE_MATCH_REGEX = Regex("(?:movies?|shows?|series|films?)\\s+like\\s+(.+)", RegexOption.IGNORE_CASE)
    val LIMIT_MATCH_REGEX = Regex("top\\s+(\\d+)", RegexOption.IGNORE_CASE)
}
