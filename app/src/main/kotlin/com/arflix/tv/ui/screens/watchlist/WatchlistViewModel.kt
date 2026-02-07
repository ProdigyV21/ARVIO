package com.arflix.tv.ui.screens.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.WatchlistRepository
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
    val items: List<MediaItem> = emptyList(),
    val error: String? = null,
    // Toast
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.INFO
)

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val watchlistRepository: WatchlistRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WatchlistUiState())
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()

    init {
        // Show cached items instantly, then refresh in background
        loadWatchlistInstant()
        // Also observe the repository's StateFlow for live updates
        observeWatchlistChanges()
    }

    private fun observeWatchlistChanges() {
        viewModelScope.launch {
            watchlistRepository.watchlistItems.collect { items ->
                if (items.isNotEmpty() || _uiState.value.items.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        items = items,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun loadWatchlistInstant() {
        viewModelScope.launch {
            // Show cached items INSTANTLY (no loading state if we have cache)
            val cachedItems = watchlistRepository.getCachedItems()
            if (cachedItems.isNotEmpty()) {
                _uiState.value = WatchlistUiState(
                    isLoading = false,
                    items = cachedItems
                )
            } else {
                // Only show loading if no cache
                _uiState.value = WatchlistUiState(isLoading = true)
            }

            // Fetch fresh data (will update via StateFlow)
            try {
                val items = watchlistRepository.getWatchlistItems()
                _uiState.value = WatchlistUiState(
                    isLoading = false,
                    items = items
                )
            } catch (e: Exception) {
                // Keep showing cached items on error
                if (_uiState.value.items.isEmpty()) {
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
                val items = watchlistRepository.refreshWatchlistItems()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    items = items
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    toastMessage = "Failed to refresh",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun removeFromWatchlist(item: MediaItem) {
        viewModelScope.launch {
            try {
                // Optimistic update - remove from local state immediately
                val updatedItems = _uiState.value.items.filter { it.id != item.id || it.mediaType != item.mediaType }
                _uiState.value = _uiState.value.copy(
                    items = updatedItems,
                    toastMessage = "Removed from watchlist",
                    toastType = ToastType.SUCCESS
                )
                // Then sync to backend
                watchlistRepository.removeFromWatchlist(item.mediaType, item.id)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    toastMessage = "Failed to remove from watchlist",
                    toastType = ToastType.ERROR
                )
            }
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }
}


