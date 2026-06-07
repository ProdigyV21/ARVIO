package com.arflix.tv.ui.screens.downloads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.db.DownloadEntity
import com.arflix.tv.data.repository.DownloadsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadsViewModel @Inject constructor(
    private val repository: DownloadsRepository
) : ViewModel() {

    data class UiState(
        val movieDownloads: List<DownloadEntity> = emptyList(),
        val seriesDownloads: Map<Int, List<DownloadEntity>> = emptyMap(),
        val seriesMetadata: Map<Int, DownloadEntity> = emptyMap()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observeAllDownloads().collect { all ->
                val movies = all.filter { it.mediaType == "movie" }
                val tvAll = all.filter { it.mediaType == "tv" }
                val seriesGroups = tvAll.groupBy { it.tmdbId }
                val seriesMeta = seriesGroups.mapValues { (_, eps) ->
                    eps.maxByOrNull { it.createdAt } ?: eps.first()
                }
                _uiState.update {
                    it.copy(
                        movieDownloads = movies,
                        seriesDownloads = seriesGroups,
                        seriesMetadata = seriesMeta
                    )
                }
            }
        }
    }

    fun pause(id: Long) = viewModelScope.launch { repository.pauseDownload(id) }
    fun resume(id: Long) = viewModelScope.launch { repository.resumeDownload(id) }
    fun cancel(id: Long) = viewModelScope.launch { repository.cancelDownload(id) }
    fun delete(id: Long) = viewModelScope.launch { repository.deleteDownload(id) }
    fun retry(id: Long) = viewModelScope.launch { repository.retryDownload(id) }
    fun deleteAllForSeries(tmdbId: Int) = viewModelScope.launch { repository.deleteAllForSeries(tmdbId) }
}
