package com.arflix.tv.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val results: List<MediaItem> = emptyList(),
    val movieResults: List<MediaItem> = emptyList(),
    val tvResults: List<MediaItem> = emptyList(),
    val cardLogoUrls: Map<String, String> = emptyMap(),
    val error: String? = null,
    // Discover rows - always 5 rows, dynamically built from active filters
    val discoverCategories: List<Category> = emptyList(),
    val discoverLogoUrls: Map<String, String> = emptyMap(),
    val isDiscoverLoading: Boolean = false,
    // Filters
    val selectedType: DiscoverType = DiscoverType.ALL,
    val selectedGenre: Genre? = null,
    val selectedCountry: Country? = null,
    // AI
    val aiInterpretation: String? = null,
    val aiResults: List<MediaItem> = emptyList(),
    val isAiSearch: Boolean = false
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mediaRepository: MediaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null
    private var discoverJob: Job? = null
    private var cachedSuggestionQuery = ""
    private var cachedSuggestionResults: List<MediaItem> = emptyList()

    init { loadDiscoverRows() }

    // ── Discover Rows (5 dynamic rows based on filters) ─────────────────

    private fun loadDiscoverRows() {
        discoverJob?.cancel()
        val state = _uiState.value
        _uiState.value = state.copy(isDiscoverLoading = true)

        discoverJob = viewModelScope.launch {
            try {
                val type = state.selectedType
                val genre = state.selectedGenre?.id?.toString()
                val lang = state.selectedCountry?.code
                val isAnime = type == DiscoverType.ANIME

                val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
                val cal = java.util.Calendar.getInstance()
                cal.add(java.util.Calendar.DAY_OF_YEAR, -90)
                val threeMonthsAgo = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(cal.time)
                cal.time = java.util.Date()
                cal.add(java.util.Calendar.YEAR, -1)
                val oneYearAgo = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(cal.time)

                val categories = withContext(Dispatchers.IO) {
                    coroutineScope {
                        // Row 1: Trending - popular with minimum votes to filter garbage
                        val row1 = async { buildRow("Trending", type, genre, "popularity.desc", 50, lang, isAnime, 1, releaseDateLte = today) }
                        // Row 2: Popular This Year - recent + popular, no obscure stuff
                        val row2 = async { buildRow("Popular This Year", type, genre, "popularity.desc", 20, lang, isAnime, 1, releaseDateGte = oneYearAgo, releaseDateLte = today) }
                        // Row 3: Top Rated - high quality, well-known titles
                        val row3 = async { buildRow("Top Rated", type, genre, "vote_average.desc", 1000, lang, isAnime, 1, releaseDateLte = today) }
                        // Row 4: New Releases - last 90 days ONLY, must be actually released (date <= today)
                        val row4 = async { buildRow("New Releases", type, genre, "popularity.desc", 10, lang, isAnime, 1, releaseDateGte = threeMonthsAgo, releaseDateLte = today) }
                        // Row 5: Hidden Gems - good ratings but less mainstream
                        val row5 = async { buildRow("Hidden Gems", type, genre, "vote_average.desc", 200, lang, isAnime, 2, releaseDateLte = today) }
                        listOfNotNull(row1.await(), row2.await(), row3.await(), row4.await(), row5.await())
                    }
                }
                _uiState.value = _uiState.value.copy(discoverCategories = categories, isDiscoverLoading = false)
                // Fetch logos for top items in each row (background, non-blocking)
                launch(Dispatchers.IO) {
                    val allItems = categories.flatMap { it.items }.distinctBy { "${it.mediaType}_${it.id}" }.take(60)
                    val logos = allItems.map { item ->
                        async {
                            val key = "${item.mediaType}_${item.id}"
                            val logo = runCatching { mediaRepository.getLogoUrl(item.mediaType, item.id) }.getOrNull()
                            if (logo.isNullOrBlank()) null else key to logo
                        }
                    }.awaitAll().filterNotNull().toMap()
                    _uiState.value = _uiState.value.copy(discoverLogoUrls = _uiState.value.discoverLogoUrls + logos)
                }
            } catch (_: Exception) {
                _uiState.value = _uiState.value.copy(isDiscoverLoading = false)
            }
        }
    }

    private suspend fun buildRow(
        title: String, type: DiscoverType, genre: String?, sort: String,
        minVotes: Int?, lang: String?, isAnime: Boolean, page: Int,
        releaseDateGte: String? = null, releaseDateLte: String? = null
    ): Category? {
        return try {
            val items = when (type) {
                DiscoverType.MOVIES -> mediaRepository.discoverMovies(genre, sort, minVotes, page, language = lang, releaseDateLte = releaseDateLte, releaseDateGte = releaseDateGte)
                DiscoverType.TV_SHOWS -> mediaRepository.discoverTv(genre, sort, minVotes, page, language = lang, airDateLte = releaseDateLte, airDateGte = releaseDateGte)
                DiscoverType.ANIME -> {
                    val animeGenre = if (genre != null) "16,$genre" else "16"
                    mediaRepository.discoverTv(animeGenre, sort, minVotes, page, language = lang, keywords = "210024", airDateLte = releaseDateLte, airDateGte = releaseDateGte)
                }
                DiscoverType.ALL -> {
                    coroutineScope {
                        val m = async { mediaRepository.discoverMovies(genre, sort, minVotes, page, language = lang, releaseDateLte = releaseDateLte, releaseDateGte = releaseDateGte) }
                        val t = async { mediaRepository.discoverTv(genre, sort, minVotes, page, language = lang, airDateLte = releaseDateLte, airDateGte = releaseDateGte) }
                        interleave(m.await(), t.await())
                    }
                }
            }
            if (items.isEmpty()) null else Category(id = "${type}_${title}_${genre}_${lang}_$page", title = title, items = items.take(20))
        } catch (_: Exception) { null }
    }

    // ── Filters → reload discover rows ──────────────────────────────────

    fun selectType(type: DiscoverType) {
        _uiState.value = _uiState.value.copy(selectedType = type, selectedGenre = null)
        loadDiscoverRows()
    }

    fun selectGenre(genre: Genre?) {
        _uiState.value = _uiState.value.copy(selectedGenre = genre)
        loadDiscoverRows()
    }

    fun selectCountry(country: Country?) {
        _uiState.value = _uiState.value.copy(selectedCountry = country)
        loadDiscoverRows()
    }

    // ── Search + AI ─────────────────────────────────────────────────────

    fun addChar(char: String) { updateQuery(_uiState.value.query + char) }
    fun deleteChar() { if (_uiState.value.query.isNotEmpty()) updateQuery(_uiState.value.query.dropLast(1)) }

    fun updateQuery(newQuery: String) {
        _uiState.value = _uiState.value.copy(query = newQuery, isAiSearch = false, aiInterpretation = null, aiResults = emptyList())
        if (newQuery.trim().isEmpty()) {
            cachedSuggestionQuery = ""; cachedSuggestionResults = emptyList()
            _uiState.value = _uiState.value.copy(query = "", isLoading = false, results = emptyList(), movieResults = emptyList(), tvResults = emptyList(), cardLogoUrls = emptyMap(), error = null, isAiSearch = false, aiInterpretation = null, aiResults = emptyList())
            searchJob?.cancel(); return
        }
        debounceSearch()
    }

    fun search() {
        val query = _uiState.value.query.trim(); if (query.isEmpty()) return
        val aiQuery = parseSmartQuery(query); if (aiQuery != null) { executeSmartSearch(aiQuery); return }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null, isAiSearch = false)
            try {
                val sorted = if (cachedSuggestionQuery.equals(query, true) && cachedSuggestionResults.isNotEmpty()) cachedSuggestionResults
                else { val f = mediaRepository.search(query); val s = sortResults(query, f); cachedSuggestionQuery = query; cachedSuggestionResults = s; s }
                val movies = sorted.filter { it.mediaType == MediaType.MOVIE }; val tv = sorted.filter { it.mediaType == MediaType.TV }
                val top = (movies.take(16) + tv.take(16)).distinctBy { "${it.mediaType}_${it.id}" }
                val logos = withContext(Dispatchers.IO) { top.map { item -> async { val k = "${item.mediaType}_${item.id}"; val l = runCatching { mediaRepository.getLogoUrl(item.mediaType, item.id) }.getOrNull(); if (l.isNullOrBlank()) null else k to l } }.awaitAll().filterNotNull().toMap() }
                _uiState.value = _uiState.value.copy(isLoading = false, results = sorted, movieResults = movies, tvResults = tv, cardLogoUrls = logos)
            } catch (e: Exception) { _uiState.value = _uiState.value.copy(isLoading = false, error = e.message) }
        }
    }

    private data class SmartQuery(val interpretation: String, val type: DiscoverType, val genreId: String?, val sort: String, val minVotes: Int?, val limit: Int?, val similarTo: String?)

    private fun parseSmartQuery(raw: String): SmartQuery? {
        val q = raw.lowercase().trim()
        val genreKeywords = mapOf("horror" to "27", "comedy" to "35", "action" to "28", "drama" to "18", "thriller" to "53", "sci-fi" to "878", "science fiction" to "878", "romance" to "10749", "animation" to "16", "anime" to "16", "documentary" to "99", "crime" to "80", "fantasy" to "14", "adventure" to "12", "mystery" to "9648", "war" to "10752", "western" to "37", "family" to "10751", "history" to "36")
        val likeMatch = Regex("(?:movies?|shows?|series|films?)\\s+like\\s+(.+)", RegexOption.IGNORE_CASE).find(q)
        if (likeMatch != null) { val t = likeMatch.groupValues[1].trim(); return SmartQuery("Similar to \"${t.replaceFirstChar { it.uppercase() }}\"", if (q.contains("show") || q.contains("series")) DiscoverType.TV_SHOWS else DiscoverType.MOVIES, null, "popularity.desc", null, null, t) }
        if (!(q.contains("top") || q.contains("best") || q.contains("popular") || q.contains("trending") || q.contains("new") || q.contains("latest"))) return null
        var gId: String? = null; var gName: String? = null; for ((kw, id) in genreKeywords) { if (q.contains(kw)) { gId = id; gName = kw.replaceFirstChar { it.uppercase() }; break } }
        if (gId == null && !q.contains("movie") && !q.contains("show") && !q.contains("series") && !q.contains("film") && !q.contains("trending") && !q.contains("anime")) return null
        val isAnime = q.contains("anime"); val isTV = q.contains("show") || q.contains("series"); val isMovie = q.contains("movie") || q.contains("film")
        val type = when { isAnime -> DiscoverType.ANIME; isTV && !isMovie -> DiscoverType.TV_SHOWS; isMovie && !isTV -> DiscoverType.MOVIES; else -> DiscoverType.ALL }
        val limit = Regex("top\\s+(\\d+)").find(q)?.groupValues?.get(1)?.toIntOrNull()
        val sort = when { q.contains("best") || q.contains("top rated") || limit != null -> "vote_average.desc"; q.contains("new") || q.contains("latest") -> if (isTV || isAnime) "first_air_date.desc" else "primary_release_date.desc"; else -> "popularity.desc" }
        val parts = mutableListOf<String>(); if (limit != null) parts.add("Top $limit"); if (sort == "vote_average.desc" && limit == null) parts.add("Best") else if (sort.contains("date")) parts.add("Newest") else parts.add("Popular")
        if (gName != null) parts.add(gName); parts.add(when(type) { DiscoverType.MOVIES -> "Movies"; DiscoverType.TV_SHOWS -> "Series"; DiscoverType.ANIME -> "Anime"; DiscoverType.ALL -> "Movies & Series" })
        return SmartQuery(parts.joinToString(" "), type, gId, sort, if (sort == "vote_average.desc") 500 else null, limit, null)
    }

    private fun executeSmartSearch(sq: SmartQuery) {
        searchJob?.cancel(); searchJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, isAiSearch = true, aiInterpretation = sq.interpretation, error = null, movieResults = emptyList(), tvResults = emptyList())
            try {
                val items = withContext(Dispatchers.IO) {
                    if (sq.similarTo != null) { val r = mediaRepository.search(sq.similarTo); val m = r.firstOrNull(); if (m != null) mediaRepository.getSimilar(m.mediaType, m.id) else emptyList() }
                    else when (sq.type) { DiscoverType.MOVIES -> mediaRepository.discoverMovies(sq.genreId, sq.sort, sq.minVotes, 1); DiscoverType.TV_SHOWS -> mediaRepository.discoverTv(sq.genreId, sq.sort, sq.minVotes, 1); DiscoverType.ANIME -> mediaRepository.discoverTv(if (sq.genreId != null) "16,${sq.genreId}" else "16", sq.sort, sq.minVotes, 1, keywords = "210024"); DiscoverType.ALL -> { coroutineScope { val a = async { mediaRepository.discoverMovies(sq.genreId, sq.sort, sq.minVotes, 1) }; val b = async { mediaRepository.discoverTv(sq.genreId, sq.sort, sq.minVotes, 1) }; interleave(a.await(), b.await()) } } }
                }
                _uiState.value = _uiState.value.copy(isLoading = false, aiResults = if (sq.limit != null) items.take(sq.limit) else items)
            } catch (e: Exception) { _uiState.value = _uiState.value.copy(isLoading = false, error = e.message) }
        }
    }

    private fun debounceSearch() { searchJob?.cancel(); searchJob = viewModelScope.launch { delay(450); if (_uiState.value.query.length >= 2) search() } }

    /** Normalize text for search matching: lowercase, replace & with and, strip articles */
    private fun normalizeForSearch(text: String): String {
        return text.lowercase()
            .replace("&", "and")
            .replace("'", "")
            .replace(":", " ")
            .replace("  ", " ")
            .trim()
    }

    private fun sortResults(query: String, results: List<MediaItem>): List<MediaItem> {
        val ql = normalizeForSearch(query)
        return results.sortedWith(
            compareBy<MediaItem> { item ->
                val t = normalizeForSearch(item.title)
                when {
                    t == ql -> 0                    // exact match
                    t.startsWith(ql) -> 1           // starts with query
                    t.contains(ql) -> 2             // contains query
                    ql.split(" ").all { word -> t.contains(word) } -> 2  // all words present
                    else -> 3
                }
            }
            .thenByDescending { item ->
                val isDoc = item.genreIds.contains(99) || item.genreIds.contains(10763)
                val isSp = item.title.lowercase().let { t ->
                    t.contains("making of") || t.contains("behind the") ||
                    t.contains("featurette") || t.contains("special")
                }
                if (isDoc || isSp) item.popularity * 0.05f else item.popularity
            }
            .thenByDescending { it.year.toIntOrNull() ?: 0 }
        )
    }

    fun clearSearch() { searchJob?.cancel(); cachedSuggestionQuery = ""; cachedSuggestionResults = emptyList(); _uiState.value = _uiState.value.copy(query = "", isLoading = false, results = emptyList(), movieResults = emptyList(), tvResults = emptyList(), cardLogoUrls = emptyMap(), error = null, isAiSearch = false, aiInterpretation = null, aiResults = emptyList()) }
    fun getGenresForType(): List<Genre> = when (_uiState.value.selectedType) { DiscoverType.MOVIES -> MOVIE_GENRES; DiscoverType.TV_SHOWS -> TV_GENRES; DiscoverType.ALL -> ALL_GENRES; DiscoverType.ANIME -> ANIME_GENRES }
    private fun interleave(a: List<MediaItem>, b: List<MediaItem>): List<MediaItem> { val r = mutableListOf<MediaItem>(); for (i in 0 until maxOf(a.size, b.size)) { if (i < a.size) r.add(a[i]); if (i < b.size) r.add(b[i]) }; return r }
}
