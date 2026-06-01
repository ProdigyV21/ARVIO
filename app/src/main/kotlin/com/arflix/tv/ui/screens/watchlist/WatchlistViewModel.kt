package com.arflix.tv.ui.screens.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType.MOVIE
import com.arflix.tv.data.model.MediaType.TV
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.util.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
            if (watchlistRepository.getCachedItems().isEmpty()) {
                runCatching { cloudSyncRepository.pullFromCloud() }
                    .onFailure { error ->
                        AppLogger.recordException(
                            throwable = error,
                            context = watchlistDiagnosticContext("startup_cloud_pull")
                        )
                    }
            }
            val traktConnected = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
            if (traktConnected) {
                val cachedItems = (watchlistRepository.getCachedItems().ifEmpty {
                    watchlistRepository.getWatchlistItems()
                }).watchlistDisplayOrder()
                _uiState.value = cachedItems.toSplitState(isLoading = cachedItems.isEmpty())
                if (cachedItems.isNotEmpty()) fetchLogos(cachedItems)
            } else {
                val cachedItems = watchlistRepository.getCachedItems()
                if (cachedItems.isNotEmpty()) {
                    _uiState.value = cachedItems.toSplitState(isLoading = false)
                } else {
                    _uiState.value = WatchlistUiState(isLoading = true)
                }
            }

            // Trakt must win over stale local cache when the profile is connected.
            try {
                val syncedFromTrakt = syncTraktWatchlistSuspend()
                if (!syncedFromTrakt && !traktConnected) {
                    val items = watchlistRepository.getWatchlistItems().watchlistDisplayOrder()
                    _uiState.value = items.toSplitState(isLoading = false)
                } else if (!syncedFromTrakt) {
                    showLocalWatchlistOrError("Failed to load Trakt watchlist")
                }
            } catch (e: Exception) {
                AppLogger.recordException(
                    throwable = e,
                    context = watchlistDiagnosticContext(
                        phase = "load_instant",
                        extra = mapOf("trakt_connected" to traktConnected.toString())
                    )
                )
                if (traktConnected) {
                    showLocalWatchlistOrError(e.message ?: "Failed to load Trakt watchlist")
                } else if (_uiState.value.isEmpty) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message
                    )
                } else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val syncedFromTrakt = syncTraktWatchlistSuspend()
                val traktConnected = runCatching { traktRepository.hasTrakt() }.getOrDefault(false)
                if (!syncedFromTrakt && !traktConnected) {
                    val items = watchlistRepository.refreshWatchlistItems().watchlistDisplayOrder()
                    _uiState.value = items.toSplitState(isLoading = false)
                } else if (!syncedFromTrakt) {
                    showLocalWatchlistOrError("Failed to load Trakt watchlist")
                }
            } catch (e: Exception) {
                AppLogger.recordException(
                    throwable = e,
                    context = watchlistDiagnosticContext("refresh")
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    toastMessage = "Failed to refresh",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    private suspend fun showLocalWatchlistOrError(message: String) {
        val cachedItems = watchlistRepository.getWatchlistItems().watchlistDisplayOrder()
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
                    throw IllegalStateException("Failed to remove from Trakt watchlist")
                }

                watchlistRepository.removeFromWatchlist(item.mediaType, item.id)

                // Optimistic update - remove from local state immediately
                val current = _uiState.value
                _uiState.value = current.copy(
                    movies = current.movies.filter { it.id != item.id || it.mediaType != item.mediaType },
                    series = current.series.filter { it.id != item.id || it.mediaType != item.mediaType },
                    toastMessage = "Removed from watchlist",
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
                AppLogger.recordException(
                    throwable = e,
                    context = watchlistDiagnosticContext(
                        phase = "remove",
                        extra = mapOf("media_type" to item.mediaType.name.lowercase())
                    )
                )
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to remove from watchlist",
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


