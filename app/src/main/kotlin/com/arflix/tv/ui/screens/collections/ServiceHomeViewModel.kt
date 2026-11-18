package com.arflix.tv.ui.screens.collections

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.api.InAppYouTubeExtractor
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.KidsContentFilter
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.effectiveSettingsDataStore as settingsDataStore
import com.arflix.tv.ui.screens.home.HomeProfilePreferences
import com.arflix.tv.ui.screens.home.observeHomeProfilePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

/** A loaded row of a service page: its spec and its titles, in TMDB's order. */
internal data class ServiceHomeRowState(
    val row: ServiceHome.Row,
    val items: List<MediaItem>,
    val sourceLoadFailed: Boolean = false
)

/** One filter's page: its rows and the title its big card shows. */
internal data class ServiceHomePage(
    /** Loaded rows with at least one title, in the page's order. */
    val rows: List<ServiceHomeRowState> = emptyList(),
    val billboard: MediaItem? = null,
    val isLoading: Boolean = true,
    val sourceLoadFailed: Boolean = false
)

/** A title's trailer, kept with the title it belongs to so it never plays on another card. */
internal data class ServiceHomeTrailer(val mediaType: MediaType, val id: Int, val youtubeKey: String?) {
    fun isFor(item: MediaItem?): Boolean = item != null && item.mediaType == mediaType && item.id == id
}

/** The profile's trailer settings, the same ones Home follows. */
internal data class ServiceHomeTrailerSettings(
    val autoPlay: Boolean = true,
    val inCards: Boolean = true,
    val soundEnabled: Boolean = false,
    val delaySeconds: Int = 1
)

internal data class ServiceHomeUiState(
    val catalogId: String? = null,
    val filter: ServiceHome.Filter = ServiceHome.Filter.ALL,
    val page: ServiceHomePage = ServiceHomePage(),
    /** The focused card with its full details (runtime, rating...), once they arrive. */
    val featured: MediaItem? = null,
    val trailer: ServiceHomeTrailer? = null
)

@HiltViewModel
internal class ServiceHomeViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val mediaRepository: MediaRepository,
    private val kidsContentFilter: KidsContentFilter,
    private val youTubeExtractor: InAppYouTubeExtractor,
    profileManager: ProfileManager
) : ViewModel() {
    val kidsFilterState: StateFlow<KidsContentFilter.State> = kidsContentFilter.state

    val trailerSettings: StateFlow<ServiceHomeTrailerSettings> = observeHomeProfilePreferences(
        activeProfileId = profileManager.activeProfileId,
        preferences = context.settingsDataStore.data
    ).map(HomeProfilePreferences::toTrailerSettings)
        .stateIn(viewModelScope, SharingStarted.Eagerly, ServiceHomeTrailerSettings())

    private val _uiState = MutableStateFlow(ServiceHomeUiState())
    val uiState: StateFlow<ServiceHomeUiState> = _uiState.asStateFlow()
    private val _cardLogoUrls = MutableStateFlow<Map<String, String>>(emptyMap())
    val cardLogoUrls: StateFlow<Map<String, String>> = _cardLogoUrls.asStateFlow()

    private var service: CatalogConfig? = null
    /** Each filter's page, kept so switching back is instant. */
    private val pages = mutableMapOf<ServiceHome.Filter, ServiceHomePage>()
    private val loadJobs = mutableMapOf<ServiceHome.Filter, Job>()
    private var featuredJob: Job? = null
    private var trailerJob: Job? = null
    private val trailerCache = mutableMapOf<Pair<MediaType, Int>, String?>()
    private val logoRequests = mutableSetOf<String>()

    fun load(service: CatalogConfig) {
        if (this.service?.id == service.id) return
        this.service = service
        loadJobs.values.forEach { it.cancel() }
        loadJobs.clear()
        pages.clear()
        logoRequests.clear()
        _cardLogoUrls.value = emptyMap()
        _uiState.value = ServiceHomeUiState(catalogId = service.id)
        loadPage(ServiceHome.Filter.ALL)
    }

    /** Shows [filter]'s rows, loading them the first time. */
    fun selectFilter(filter: ServiceHome.Filter) {
        if (_uiState.value.filter == filter) return
        _uiState.update { it.copy(filter = filter, page = pages[filter] ?: ServiceHomePage()) }
        loadPage(filter)
    }

    fun retry() {
        val filter = _uiState.value.filter
        if (loadJobs[filter]?.isActive == true) return
        pages.remove(filter)
        _uiState.update { it.copy(page = ServiceHomePage()) }
        loadPage(filter)
    }

    private fun loadPage(filter: ServiceHome.Filter) {
        val service = service ?: return
        if (loadJobs[filter]?.isActive == true ||
            pages[filter]?.let { !it.isLoading && (!it.sourceLoadFailed || it.rows.isNotEmpty()) } == true
        ) return
        val rows = ServiceHome.rows(service, LocalDate.now(), filter)
        val loaded = arrayOfNulls<ServiceHomeRowState>(rows.size)
        val rowSlots = Semaphore(ROW_CONCURRENCY)
        loadJobs[filter] = viewModelScope.launch {
            rows.mapIndexed { index, row ->
                launch {
                    loaded[index] = rowSlots.withPermit { loadRow(row) }
                    publish(filter, loaded, done = false)
                }
            }.forEach { it.join() }
            publish(filter, loaded, done = true)
        }
    }

    private suspend fun loadRow(row: ServiceHome.Row): ServiceHomeRowState = try {
        val page = mediaRepository.loadCollectionCatalogPage(row.catalog, offset = 0, limit = row.limit)
        val items = page.items
            .filter { it.id > 0 }
            .distinctBy { it.mediaType to it.id }
            .take(row.limit)
        ServiceHomeRowState(row, items, page.sourceLoadFailed)
    } catch (e: CancellationException) {
        throw e
    } catch (_: Exception) {
        ServiceHomeRowState(row, emptyList(), sourceLoadFailed = true)
    }

    /** Rows appear in the page's order as they arrive; empty ones never show. */
    private fun publish(filter: ServiceHome.Filter, loaded: Array<ServiceHomeRowState?>, done: Boolean) {
        val rows = loaded.filterNotNull().filter { it.items.isNotEmpty() }
        val previous = pages[filter]
        val billboard = previous?.billboard ?: pickBillboard(rows, done)
        val page = ServiceHomePage(
            rows = rows, billboard = billboard, isLoading = !done,
            sourceLoadFailed = loaded.any { it?.sourceLoadFailed == true }
        )
        pages[filter] = page
        if (_uiState.value.filter == filter) _uiState.update { it.copy(page = page) }
        billboard?.let(::loadLogo)
    }

    /**
     * The first Top 10 title with artwork; it then stays put while rows arrive. Without a
     * Top 10 title, any row's first title with artwork once the page has loaded.
     */
    private fun pickBillboard(rows: List<ServiceHomeRowState>, done: Boolean): MediaItem? {
        val top10 = rows.filter {
            it.row.kind == ServiceHome.RowKind.TOP10_SERIES || it.row.kind == ServiceHome.RowKind.TOP10_MOVIES
        }
        val candidates = if (top10.isNotEmpty()) top10 else if (done) rows else return null
        return candidates.flatMap { it.items }.firstOrNull { !it.backdrop.isNullOrBlank() }
    }

    private fun loadLogo(item: MediaItem) {
        val key = "${item.mediaType}_${item.id}"
        if (!logoRequests.add(key)) return
        mediaRepository.peekCachedLogoUrl(item.mediaType, item.id)?.let { cached ->
            _cardLogoUrls.update { it + (key to cached) }
            return
        }
        viewModelScope.launch {
            val logo = runCatching { mediaRepository.getLogoUrl(item.mediaType, item.id) }
                .onFailure { if (it is CancellationException) throw it }
                .getOrNull()
            if (!logo.isNullOrBlank()) _cardLogoUrls.update { it + (key to logo) }
        }
    }

    /** Clear logos for the focused card and its neighbours, as Home shows them on its big card. */
    fun preloadLogos(items: List<MediaItem>) {
        items.filter { it.id > 0 }.forEach(::loadLogo)
    }

    /**
     * Focus rests on [item] (a card or the big card): load its full details and, when the
     * profile plays trailers, its trailer, the way Home does for its hero.
     */
    fun focus(item: MediaItem?) {
        featuredJob?.cancel()
        trailerJob?.cancel()
        if (item == null || item.id <= 0) return
        if (_uiState.value.featured?.let { it.mediaType == item.mediaType && it.id == item.id } != true) {
            featuredJob = viewModelScope.launch {
                delay(FEATURED_DELAY_MS)
                val details = runCatching {
                    when (item.mediaType) {
                        MediaType.MOVIE -> mediaRepository.getMovieDetails(item.id)
                        MediaType.TV -> mediaRepository.getTvDetails(item.id)
                    }
                }.onFailure { if (it is CancellationException) throw it }.getOrNull() ?: return@launch
                if (details.id == item.id && details.mediaType == item.mediaType) {
                    _uiState.update { it.copy(featured = details) }
                }
            }
        }
        requestTrailer(item)
    }

    private fun requestTrailer(item: MediaItem) {
        if (!trailerSettings.value.autoPlay || _uiState.value.trailer?.isFor(item) == true) return
        val key = item.mediaType to item.id
        if (trailerCache.containsKey(key)) {
            _uiState.update { it.copy(trailer = ServiceHomeTrailer(item.mediaType, item.id, trailerCache[key])) }
            return
        }
        trailerJob = viewModelScope.launch {
            delay(TRAILER_LOOKUP_DELAY_MS)
            val youtubeKey = runCatching { mediaRepository.getTrailerKey(item.mediaType, item.id) }
                .onFailure { if (it is CancellationException) throw it }
                .getOrNull()
            trailerCache[key] = youtubeKey
            _uiState.update { it.copy(trailer = ServiceHomeTrailer(item.mediaType, item.id, youtubeKey)) }
            // Resolving the stream ahead of the card's delay lets the trailer start on time.
            youtubeKey?.let { trailer ->
                launch(Dispatchers.IO) {
                    runCatching { youTubeExtractor.extractPlaybackSource("https://www.youtube.com/watch?v=$trailer") }
                }
            }
        }
    }

    /** The page's rows as a kids profile may see them; empty rows are dropped. */
    fun visibleRows(page: ServiceHomePage, kids: KidsContentFilter.State): List<ServiceHomeRowState> {
        if (!kids.isActive) return page.rows
        return page.rows.mapNotNull { row ->
            kidsContentFilter.filter(row.items, kids).takeIf { it.isNotEmpty() }?.let { row.copy(items = it) }
        }
    }

    fun allows(item: MediaItem, kids: KidsContentFilter.State): Boolean =
        !kids.isActive || kidsContentFilter.filter(listOf(item), kids).isNotEmpty()

    private companion object {
        const val ROW_CONCURRENCY = 3
        const val FEATURED_DELAY_MS = 350L
        const val TRAILER_LOOKUP_DELAY_MS = 220L
    }
}

private fun HomeProfilePreferences.toTrailerSettings() = ServiceHomeTrailerSettings(
    autoPlay = trailerAutoPlay,
    inCards = trailerInCards,
    soundEnabled = trailerSoundEnabled,
    delaySeconds = trailerDelaySeconds
)
