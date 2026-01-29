package com.arflix.tv.ui.startup

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.tracing.Trace
import javax.inject.Inject

/**
 * StartupViewModel - Handles parallel loading during splash screen
 * Pre-loads all data needed for instant home screen display
 */
data class StartupState(
    val isLoading: Boolean = true,
    val isReady: Boolean = false,
    val loadingProgress: Float = 0f,
    val loadingMessage: String = "Starting...",
    val categories: List<Category> = emptyList(),
    val heroItem: MediaItem? = null,
    val heroLogoUrl: String? = null,
    val logoCache: Map<String, String> = emptyMap(),
    val isAuthenticated: Boolean = false,
    val error: String? = null
)

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class StartupViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val imageLoader: ImageLoader,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val networkDispatcher = Dispatchers.IO.limitedParallelism(8)
    private val heroLogoPreloadWidth = 300
    private val heroLogoPreloadHeight = 70
    private val heroBackdropPreloadWidth = 1920
    private val heroBackdropPreloadHeight = 1080

    private val _state = MutableStateFlow(StartupState())
    val state: StateFlow<StartupState> = _state.asStateFlow()

    init {
        startParallelLoading()
    }

    private fun startParallelLoading() {
        viewModelScope.launch {
            Trace.beginSection("StartupViewModel.startParallelLoading")
            try {
                // Phase 1: Load categories in parallel (auth runs after first draw)
                updateProgress(0.1f, "Loading content...")

                val categoriesDeferred = async(networkDispatcher) {
                    try {
                        mediaRepository.getHomeCategories()
                    } catch (e: Exception) {
                        Log.e("StartupViewModel", "Failed to load categories", e)
                        emptyList()
                    }
                }
                val categories = categoriesDeferred.await().toMutableList()

                updateProgress(0.3f, "Loading your library...")

                updateProgress(0.5f, "Loading artwork...")

                // Get hero item
                val heroItem = categories.firstOrNull()?.items?.firstOrNull()
                prefetchHeroAssets(heroItem)

                _state.value = _state.value.copy(
                    isLoading = false,
                    isReady = true,
                    categories = categories,
                    heroItem = heroItem,
                    isAuthenticated = false
                )

                updateProgress(1.0f, "Ready!")
                Log.d("StartupViewModel", "Startup complete: ${categories.size} categories")

            } catch (e: Exception) {
                Log.e("StartupViewModel", "Startup failed", e)
                _state.value = _state.value.copy(
                    isLoading = false,
                    isReady = true,
                    error = e.message
                )
            } finally {
                Trace.endSection()
            }
        }
    }

    private fun updateProgress(progress: Float, message: String) {
        _state.value = _state.value.copy(
            loadingProgress = progress,
            loadingMessage = message
        )
    }

    private fun prefetchHeroAssets(heroItem: MediaItem?) {
        if (heroItem == null) return

        val backdropUrl = heroItem.backdrop ?: heroItem.image
        if (!backdropUrl.isNullOrBlank()) {
            val request = ImageRequest.Builder(context)
                .data(backdropUrl)
                .size(heroBackdropPreloadWidth, heroBackdropPreloadHeight)
                .precision(Precision.INEXACT)
                .allowHardware(true)
                .build()
            imageLoader.enqueue(request)
        }

        viewModelScope.launch(networkDispatcher) {
            try {
                val logoUrl = mediaRepository.getLogoUrl(heroItem.mediaType, heroItem.id)
                if (!logoUrl.isNullOrBlank()) {
                    val request = ImageRequest.Builder(context)
                        .data(logoUrl)
                        .size(heroLogoPreloadWidth, heroLogoPreloadHeight)
                        .precision(Precision.INEXACT)
                        .allowHardware(true)
                        .build()
                    imageLoader.enqueue(request)
                    val cacheKey = "${heroItem.mediaType}_${heroItem.id}"
                    val currentCache = _state.value.logoCache.toMutableMap()
                    currentCache[cacheKey] = logoUrl
                    _state.value = _state.value.copy(
                        heroLogoUrl = logoUrl,
                        logoCache = currentCache
                    )
                }
            } catch (e: Exception) {
                Log.e("StartupViewModel", "Hero logo preload failed", e)
            }
        }
    }
}
