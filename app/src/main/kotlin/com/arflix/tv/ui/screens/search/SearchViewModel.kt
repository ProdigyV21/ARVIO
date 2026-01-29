package com.arflix.tv.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val results: List<MediaItem> = emptyList(),
    val movieResults: List<MediaItem> = emptyList(),
    val tvResults: List<MediaItem> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mediaRepository: MediaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun addChar(char: String) {
        _uiState.value = _uiState.value.copy(
            query = _uiState.value.query + char
        )
        debounceSearch()
    }

    fun deleteChar() {
        if (_uiState.value.query.isNotEmpty()) {
            _uiState.value = _uiState.value.copy(
                query = _uiState.value.query.dropLast(1)
            )
            debounceSearch()
        }
    }

    fun updateQuery(newQuery: String) {
        _uiState.value = _uiState.value.copy(query = newQuery)
        debounceSearch()
    }

    fun search() {
        val query = _uiState.value.query.trim()
        if (query.isEmpty()) return

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                val results = mediaRepository.search(query)

                // Smart sorting: prioritize main content over documentaries/specials
                // 1. Exact/close title matches first
                // 2. High popularity (main releases) before low popularity (documentaries)
                // 3. Then by year descending (newest first)
                val queryLower = query.lowercase()
                val sortedResults = results.sortedWith(
                    compareBy<MediaItem> { item ->
                        // Priority 1: Title match score (lower = better match)
                        val titleLower = item.title.lowercase()
                        when {
                            titleLower == queryLower -> 0  // Exact match
                            titleLower.startsWith(queryLower) -> 1  // Starts with query
                            titleLower.contains(queryLower) -> 2  // Contains query
                            else -> 3  // Partial/fuzzy match
                        }
                    }.thenByDescending { item ->
                        // Priority 2: Popularity (higher = main content)
                        // Documentary genre IDs: 99 (movie), 10763 (TV documentary)
                        // Lower popularity for documentaries/specials
                        val isDocumentary = item.genreIds.contains(99) || item.genreIds.contains(10763)
                        val titleLower = item.title.lowercase()
                        val isSpecial = titleLower.contains("making of") ||
                                titleLower.contains("behind the") ||
                                titleLower.contains("special") ||
                                titleLower.contains("documentary") ||
                                titleLower.contains("featurette")

                        if (isDocumentary || isSpecial) {
                            item.popularity * 0.1f  // Deprioritize documentaries/specials
                        } else {
                            item.popularity
                        }
                    }.thenByDescending { item ->
                        // Priority 3: Year (newest first)
                        item.year.toIntOrNull() ?: 0
                    }
                )

                // Separate into movies and TV shows
                val movies = sortedResults.filter { it.mediaType == MediaType.MOVIE }
                val tvShows = sortedResults.filter { it.mediaType == MediaType.TV }

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    results = sortedResults,
                    movieResults = movies,
                    tvResults = tvShows
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun debounceSearch() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(800) // Debounce - matches webapp timing
            if (_uiState.value.query.length >= 2) {
                search()
            }
        }
    }

    fun clearSearch() {
        _uiState.value = SearchUiState()
    }
}
