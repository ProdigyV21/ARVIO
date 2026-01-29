package com.arflix.tv.ui.screens.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.data.repository.WatchlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ToastType {
    SUCCESS, ERROR, INFO
}

data class WatchlistUiState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = false,
    val items: List<MediaItem> = emptyList(),
    val error: String? = null,
    // Toast
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.INFO
)

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val watchlistRepository: WatchlistRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(WatchlistUiState())
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()
    
    init {
        loadWatchlist()
    }
    
    private fun loadWatchlist() {
        viewModelScope.launch {
            _uiState.value = WatchlistUiState(isLoading = true)
            
            val authState = authRepository.authState.first()
            val isAuth = authState is AuthState.Authenticated
            
            if (!isAuth) {
                _uiState.value = WatchlistUiState(
                    isLoading = false,
                    isAuthenticated = false
                )
                return@launch
            }
            
            try {
                val items = watchlistRepository.getWatchlistItems()
                _uiState.value = WatchlistUiState(
                    isLoading = false,
                    isAuthenticated = true,
                    items = items
                )
            } catch (e: Exception) {
                _uiState.value = WatchlistUiState(
                    isLoading = false,
                    isAuthenticated = true,
                    error = e.message
                )
            }
        }
    }
    
    fun refresh() {
        loadWatchlist()
    }

    fun removeFromWatchlist(item: MediaItem) {
        viewModelScope.launch {
            try {
                watchlistRepository.removeFromWatchlist(item.mediaType, item.id)
                // Update local state
                val updatedItems = _uiState.value.items.filter { it.id != item.id }
                _uiState.value = _uiState.value.copy(
                    items = updatedItems,
                    toastMessage = "Removed from watchlist",
                    toastType = ToastType.SUCCESS
                )
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


