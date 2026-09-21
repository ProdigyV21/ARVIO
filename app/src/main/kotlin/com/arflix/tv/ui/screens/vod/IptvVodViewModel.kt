package com.arflix.tv.ui.screens.vod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.IptvRepository.IptvVodBrowseCategory
import com.arflix.tv.data.repository.IptvRepository.IptvVodBrowseItem
import com.arflix.tv.data.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

/** Virtual categories shown above the provider's own categories. */
internal const val VOD_CATEGORY_ALL = "__all__"
internal const val VOD_CATEGORY_RECENT = "__recent__"

data class IptvVodUiState(
    val isSeries: Boolean = false,
    val isLoading: Boolean = true,
    val hasSource: Boolean = true,
    val error: String? = null,
    val categories: List<IptvVodBrowseCategory> = emptyList(),
    val categoryCounts: Map<String, Int> = emptyMap(),
    val selectedCategoryId: String = VOD_CATEGORY_ALL,
    val query: String = "",
    val items: List<IptvVodBrowseItem> = emptyList(),
    val resolvingKey: String? = null,
    val message: String? = null
)

sealed interface IptvVodEvent {
    data class OpenDetails(val mediaType: MediaType, val tmdbId: Int) : IptvVodEvent
    data class PlayDirect(val mediaId: Int, val streamUrl: String, val title: String) : IptvVodEvent
}

@HiltViewModel
class IptvVodViewModel @Inject constructor(
    private val iptvRepository: IptvRepository,
    private val mediaRepository: MediaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(IptvVodUiState())
    val uiState: StateFlow<IptvVodUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<IptvVodEvent>(extraBufferCapacity = 1)
    val events: SharedFlow<IptvVodEvent> = _events.asSharedFlow()

    private var allItems: List<IptvVodBrowseItem> = emptyList()

    init {
        load()
    }

    fun selectTab(series: Boolean) {
        if (_uiState.value.isSeries == series && !_uiState.value.isLoading) return
        _uiState.value = _uiState.value.copy(
            isSeries = series,
            selectedCategoryId = VOD_CATEGORY_ALL,
            query = ""
        )
        load()
    }

    fun selectCategory(categoryId: String) {
        _uiState.value = _uiState.value.copy(selectedCategoryId = categoryId)
        applyFilter()
    }

    fun setQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query.trim())
        applyFilter()
    }

    fun refresh() = load()

    fun dismissMessage() {
        _uiState.value = _uiState.value.copy(message = null)
    }

    private fun load() {
        val series = _uiState.value.isSeries
        _uiState.value = _uiState.value.copy(isLoading = true, error = null, items = emptyList())
        viewModelScope.launch {
            val hasSource = runCatching { iptvRepository.hasVodBrowseSource() }.getOrDefault(false)
            if (!hasSource) {
                allItems = emptyList()
                _uiState.value = _uiState.value.copy(isLoading = false, hasSource = false)
                return@launch
            }
            val result = runCatching { iptvRepository.getVodBrowseCatalog(series) }
            if (_uiState.value.isSeries != series) return@launch // tab changed while loading
            result.onSuccess { catalog ->
                allItems = catalog.items
                val counts = catalog.items.groupingBy { it.categoryId.orEmpty() }.eachCount()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    hasSource = true,
                    // Hide empty provider categories; they only add noise to the list.
                    categories = catalog.categories.filter { (counts[it.id] ?: 0) > 0 },
                    categoryCounts = counts
                )
                applyFilter()
            }.onFailure { e ->
                allItems = emptyList()
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "Load failed")
            }
        }
    }

    private fun applyFilter() {
        val state = _uiState.value
        val query = state.query.lowercase(Locale.ROOT)
        val filtered = when (state.selectedCategoryId) {
            VOD_CATEGORY_ALL -> allItems
            VOD_CATEGORY_RECENT -> allItems.filter { it.addedAt > 0 }.sortedByDescending { it.addedAt }.take(300)
            else -> allItems.filter { it.categoryId == state.selectedCategoryId }
        }.let { list ->
            if (query.isEmpty()) list else list.filter { it.name.lowercase(Locale.ROOT).contains(query) }
        }
        _uiState.value = state.copy(items = filtered)
    }

    /**
     * Opens a title. Items the provider tagged with a TMDB id open ARVIO's normal
     * Details page (the IPTV copy is found there as a source, next to the addons).
     * Untagged items are matched on TMDB by cleaned name + year; movies that still
     * can't be matched play straight from the provider.
     */
    fun open(item: IptvVodBrowseItem) {
        if (_uiState.value.resolvingKey != null) return
        val mediaType = if (item.isSeries) MediaType.TV else MediaType.MOVIE
        item.tmdbId?.takeIf { it > 0 }?.let {
            _events.tryEmit(IptvVodEvent.OpenDetails(mediaType, it))
            return
        }
        _uiState.value = _uiState.value.copy(resolvingKey = item.key)
        viewModelScope.launch {
            val matchId = runCatching { findTmdbMatch(item, mediaType) }.getOrNull()
            _uiState.value = _uiState.value.copy(resolvingKey = null)
            when {
                matchId != null -> _events.tryEmit(IptvVodEvent.OpenDetails(mediaType, matchId))
                !item.isSeries && item.streamUrl != null -> _events.tryEmit(
                    // Negative synthetic id: never collides with TMDB, and the player
                    // skips history/scrobbling for ids <= 0.
                    IptvVodEvent.PlayDirect(
                        mediaId = -(item.key.hashCode() and 0x7fffffff).coerceAtLeast(1),
                        streamUrl = item.streamUrl,
                        title = item.name
                    )
                )
                // The screen formats this as "could not be matched".
                else -> _uiState.value = _uiState.value.copy(message = item.name)
            }
        }
    }

    private suspend fun findTmdbMatch(item: IptvVodBrowseItem, mediaType: MediaType): Int? {
        val title = cleanIptvTitle(item.name)
        if (title.isBlank()) return null
        val candidates = mediaRepository.search(title).filter { it.mediaType == mediaType }
        if (candidates.isEmpty()) return null
        val year = item.year
        if (year != null) {
            candidates.firstOrNull { it.year.take(4).toIntOrNull()?.let { y -> kotlin.math.abs(y - year) <= 1 } == true }
                ?.let { return it.id }
        }
        val normalized = title.lowercase(Locale.ROOT)
        return candidates.firstOrNull { it.title.lowercase(Locale.ROOT) == normalized }?.id
            ?: candidates.first().id.takeIf { year == null }
    }
}

private val LEADING_TAG = Regex("""^\s*(?:[\[|(][^\]|)]{1,12}[\]|)]\s*[-:|]?\s*|[A-Z]{2,4}\s*[-:|]\s+)+""")
private val QUALITY_TAGS = Regex("""(?i)\b(4k|uhd|fhd|hd|sd|1080p|720p|2160p|hevc|x265|x264|multi|sub|dub(?:bed)?)\b""")
private val EMPTY_BRACKETS = Regex("""[(\[]\s*[)\]]""")
private val TRAILING_YEAR = Regex("""[\s._-]*[(\[]?(19|20)\d{2}[)\]]?\s*$""")
private val MULTI_SPACE = Regex("""\s{2,}""")

/** Strips provider prefixes ("EN - ", "|HE| ", "[4K]"), quality tags and a trailing year. */
internal fun cleanIptvTitle(raw: String): String =
    raw.replace(LEADING_TAG, "")
        .replace(QUALITY_TAGS, "")
        .replace(EMPTY_BRACKETS, "")
        .trim()
        .replace(TRAILING_YEAR, "")
        .replace(MULTI_SPACE, " ")
        .trim(' ', '-', '|', ':', '.')
