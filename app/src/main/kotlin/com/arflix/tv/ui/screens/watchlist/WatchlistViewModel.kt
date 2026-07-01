package com.arflix.tv.ui.screens.watchlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.R
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType.MOVIE
import com.arflix.tv.data.model.MediaType.TV
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.util.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

enum class ToastType {
    SUCCESS, ERROR, INFO
}

data class WatchlistUiState(
    val isLoading: Boolean = true,
    val movies: List<MediaItem> = emptyList(),
    val series: List<MediaItem> = emptyList(),
    val error: String? = null,
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.INFO
) {
    val isEmpty: Boolean get() = movies.isEmpty() && series.isEmpty()
    val allItems: List<MediaItem> get() = movies + series
}

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val watchlistRepository: WatchlistRepository,
    private val cloudSyncRepository: CloudSyncRepository,
    private val traktRepository: TraktRepository,
    private val mediaRepository: MediaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(WatchlistUiState())
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()

    private val _logoUrls = MutableStateFlow<Map<String, String>>(emptyMap())
    val logoUrls: StateFlow<Map<String, String>> = _logoUrls.asStateFlow()
    private var traktSyncInFlight = false
    private var initialLoadComplete = false
    private var enrichmentInFlight = false

    private fun watchlistDiagnosticContext(
        phase: String,
        extra: Map<String, String> = emptyMap()
    ): Map<String, String> = mutableMapOf(
        "error_area" to "Watchlist",
        "watchlist_phase" to phase,
        "visible_count" to _uiState.value.allItems.size.toString()
    ).apply { putAll(extra) }

    private fun List<MediaItem>.watchlistDisplayOrder(): List<MediaItem> {
        return sortedWith(
            compareBy<MediaItem> { it.sourceOrder }
                .thenByDescending { it.addedAt }
        )
    }

    private fun List<MediaItem>.toSplitState(
        isLoading: Boolean = false,
        error: String? = null,
        toastMessage: String? = null,
        toastType: ToastType = ToastType.INFO
    ): WatchlistUiState = WatchlistUiState(
        isLoading = isLoading,
        movies = filter { it.mediaType == MOVIE },
        series = filter { it.mediaType == TV },
        error = error,
        toastMessage = toastMessage,
        toastType = toastType
    )

    init {
        observeWatchlistChanges()
        loadWatchlistInstant()
    }

    private fun observeWatchlistChanges() {
        viewModelScope.launch {
            watchlistRepository.watchlistItems.collect { items ->
                if (traktSyncInFlight) return@collect
                val current = _uiState.value
                if (items.isNotEmpty() || (!current.isLoading && current.isEmpty)) {
                    val orderedItems = items.watchlistDisplayOrder()
                    _uiState.value = orderedItems.toSplitState(isLoading = false)
                    fetchLogos(orderedItems)
                }
            }
        }
    }

    private fun fetchLogos(items: List<MediaItem>) {
        viewModelScope.launch {
            val currentLogos = _logoUrls.value.toMutableMap()
            for (item in items) {
                val key = "${item.mediaType}_${item.id}"
                if (key in currentLogos) continue
                val url = runCatching { mediaRepository.getLogoUrl(item.mediaType, item.id) }.getOrNull()
                if (url != null) {
                    currentLogos[key] = url
                    _logoUrls.value = currentLogos.toMap()
                }
            }
        }
    }

    private fun loadWatchlistInstant() {
        viewModelScope.launch {
            val initialLocalItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder()
            if (initialLocalItems.isNotEmpty()) {
                _uiState.value = initialLocalItems.toSplitState(isLoading = false)
                fetchLogos(initialLocalItems)
            }

            if (initialLocalItems.isEmpty()) {
                withTimeoutOrNull(3_500) {
                    runCatching { cloudSyncRepository.pullFromCloud() }
                        .onFailure { error ->
                            AppLogger.recordException(
                                throwable = error,
                                context = watchlistDiagnosticContext("startup_cloud_pull")
                            )
                        }
                }
                val cloudItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder()
                if (cloudItems.isNotEmpty()) {
                    _uiState.value = cloudItems.toSplitState(isLoading = false)
                    fetchLogos(cloudItems)
                }
            }

            val traktConnected = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
            if (!traktConnected) {
                val items = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder()
                _uiState.value = items.toSplitState(isLoading = false)
                if (items.isNotEmpty()) fetchLogos(items)
                if (items.isNotEmpty()) enrichLocalWatchlistInBackground()
                initialLoadComplete = true
                return@launch
            }

            val visibleItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder()
            _uiState.value = visibleItems.toSplitState(isLoading = visibleItems.isEmpty())
            if (visibleItems.isNotEmpty()) fetchLogos(visibleItems)
            initialLoadComplete = true

            // Trakt is authoritative when connected, but it must not hold the page hostage.
            val syncedFromTrakt = withTimeoutOrNull(12_000) {
                syncTraktWatchlistSuspend()
            } ?: false

            if (!syncedFromTrakt) {
                val fallbackItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder()
                if (fallbackItems.isNotEmpty()) {
                    _uiState.value = fallbackItems.toSplitState(isLoading = false)
                    fetchLogos(fallbackItems)
                    enrichLocalWatchlistInBackground()
                } else {
                    _uiState.value = WatchlistUiState(
                        isLoading = false,
                        error = context.getString(R.string.watchlist_error_load_trakt)
                    )
                }
            } else {
                enrichLocalWatchlistInBackground()
            }
        }
    }

    private fun enrichLocalWatchlistInBackground() {
        if (enrichmentInFlight) return
        enrichmentInFlight = true
        viewModelScope.launch {
            try {
                val enrichedItems = watchlistRepository.refreshWatchlistItems().watchlistDisplayOrder()
                if (enrichedItems.isNotEmpty()) {
                    _uiState.value = enrichedItems.toSplitState(isLoading = false)
                    fetchLogos(enrichedItems)
                } else if (_uiState.value.isLoading) {
                    _uiState.value = WatchlistUiState(isLoading = false)
                }
            } catch (error: Exception) {
                if (error is kotlinx.coroutines.CancellationException) throw error
                AppLogger.recordException(
                    throwable = error,
                    context = watchlistDiagnosticContext("background_enrich")
                )
                if (_uiState.value.isLoading) {
                    val fallbackItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder()
                    _uiState.value = fallbackItems.toSplitState(isLoading = false)
                }
            } finally {
                enrichmentInFlight = false
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            val hadItems = _uiState.value.allItems.isNotEmpty()
            _uiState.value = _uiState.value.copy(isLoading = !hadItems)
            try {
                val traktConnected = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
                val syncedFromTrakt = if (traktConnected) {
                    withTimeoutOrNull(15_000) { syncTraktWatchlistSuspend() } ?: false
                } else {
                    false
                }
                if (!syncedFromTrakt && !traktConnected) {
                    val items = watchlistRepository.refreshWatchlistItems().watchlistDisplayOrder()
                    _uiState.value = items.toSplitState(isLoading = false)
                } else if (!syncedFromTrakt) {
                    showLocalWatchlistOrError(context.getString(R.string.watchlist_error_load_trakt))
                } else {
                    enrichLocalWatchlistInBackground()
                }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e

                AppLogger.recordException(
                    throwable = e,
                    context = watchlistDiagnosticContext("refresh")
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    toastMessage = context.getString(R.string.watchlist_error_refresh),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun refreshAfterResume() {
        if (!initialLoadComplete) return
        viewModelScope.launch {
            val traktConnected = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
            if (!traktConnected || traktSyncInFlight) return@launch
            val syncedFromTrakt = withTimeoutOrNull(10_000) { syncTraktWatchlistSuspend() } ?: false
            if (!syncedFromTrakt && _uiState.value.isLoading) {
                val fallbackItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder()
                _uiState.value = fallbackItems.toSplitState(isLoading = false)
            }
        }
    }

    private suspend fun showLocalWatchlistOrError(message: String) {
        val cachedItems = watchlistRepository.getLocalWatchlistItems().watchlistDisplayOrder()
        if (cachedItems.isNotEmpty()) {
            _uiState.value = cachedItems.toSplitState(isLoading = false)
            fetchLogos(cachedItems)
        } else {
            _uiState.value = WatchlistUiState(isLoading = false, error = message)
        }
    }

    fun removeFromWatchlist(item: MediaItem) {
        viewModelScope.launch {
            try {
                val traktConnected = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
                if (traktConnected && !traktRepository.removeFromWatchlist(item.mediaType, item.id)) {
                    throw IllegalStateException(context.getString(R.string.watchlist_failed_remove_trakt))
                }

                watchlistRepository.removeFromWatchlist(item.mediaType, item.id)

                // Optimistic update - remove from local state immediately
                val current = _uiState.value
                _uiState.value = current.copy(
                    movies = current.movies.filter { it.id != item.id || it.mediaType != item.mediaType },
                    series = current.series.filter { it.id != item.id || it.mediaType != item.mediaType },
                    toastMessage = context.getString(R.string.watchlist_toast_removed),
                    toastType = ToastType.SUCCESS
                )
                runCatching { cloudSyncRepository.pushToCloud() }
                    .onFailure { error ->
                        AppLogger.recordException(
                            throwable = error,
                            context = watchlistDiagnosticContext("remove_cloud_push")
                        )
                    }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e

                AppLogger.recordException(
                    throwable = e,
                    context = watchlistDiagnosticContext(
                        phase = "remove",
                        extra = mapOf("media_type" to item.mediaType.name.lowercase())
                    )
                )
                _uiState.value = _uiState.value.copy(
                    toastMessage = context.getString(R.string.watchlist_failed_remove),
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    /**
     * Pull Trakt watchlist and mirror it locally. Trakt is the source of truth
     * for both order and IDs when connected.
     */
    private suspend fun syncTraktWatchlistSuspend(): Boolean {
        if (traktSyncInFlight) return true
        traktSyncInFlight = true
        return try {
            val (hasTraktAuth, syncResult) = traktRepository.getWatchlistSyncResultWithAuthState()
            if (!hasTraktAuth) {
                AppLogger.breadcrumb(
                    tag = "Watchlist",
                    message = "trakt_sync_no_auth",
                    severity = "info"
                )
                false
            } else {
                val traktItems = syncResult?.items.orEmpty()
                val rawCount = syncResult?.rawCount ?: 0
                AppLogger.breadcrumb(
                    tag = "Watchlist",
                    message = "trakt_sync_result raw=$rawCount hydrated=${traktItems.size}",
                    severity = "info"
                )
                if (traktItems.isNotEmpty()) {
                    watchlistRepository.clearWatchlistCache()
                    val orderedTraktItems = traktItems.watchlistDisplayOrder()
                    _uiState.value = orderedTraktItems.toSplitState(isLoading = false)
                    fetchLogos(orderedTraktItems)

                    watchlistRepository.syncFromTraktOrder(orderedTraktItems)
                    _uiState.value = orderedTraktItems.toSplitState(isLoading = false)
                    runCatching { cloudSyncRepository.pushToCloud() }
                        .onFailure { error ->
                            AppLogger.recordException(
                                throwable = error,
                                context = watchlistDiagnosticContext(
                                    phase = "trakt_sync_cloud_push",
                                    extra = mapOf(
                                        "raw_count" to rawCount.toString(),
                                        "hydrated_count" to orderedTraktItems.size.toString()
                                    )
                                )
                            )
                        }
                } else if (rawCount == 0) {
                    val cachedItems = (watchlistRepository.getCachedItems().ifEmpty {
                        watchlistRepository.getWatchlistItems()
                    }).watchlistDisplayOrder()
                    _uiState.value = cachedItems.toSplitState(isLoading = false)
                    if (cachedItems.isNotEmpty()) {
                        fetchLogos(cachedItems)
                    } else {
                        _uiState.value = WatchlistUiState(isLoading = false)
                    }
                } else {
                    AppLogger.recordException(
                        throwable = IllegalStateException("Trakt watchlist hydrated zero items"),
                        context = watchlistDiagnosticContext(
                            phase = "trakt_hydration_empty",
                            extra = mapOf(
                                "raw_count" to rawCount.toString(),
                                "cached_count" to watchlistRepository.getCachedItems().size.toString()
                            )
                        )
                    )
                    val cachedItems = watchlistRepository.getWatchlistItems().watchlistDisplayOrder()
                    _uiState.value = cachedItems.toSplitState(isLoading = false)
                    fetchLogos(cachedItems)
                }
                true
            }
        } catch (error: Exception) {
            if (error is kotlinx.coroutines.CancellationException) throw error
            AppLogger.recordException(
                throwable = error,
                context = watchlistDiagnosticContext("trakt_sync")
            )
            false
        } finally {
            traktSyncInFlight = false
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }
}
