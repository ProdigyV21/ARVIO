package com.arflix.tv.ui.screens.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.music.AlbumShelf
import com.arflix.tv.music.MusicAlbum
import com.arflix.tv.music.MusicArtist
import com.arflix.tv.music.MusicPlaybackState
import com.arflix.tv.music.MusicPlayer
import com.arflix.tv.music.MusicRepository
import com.arflix.tv.music.MusicSearchResult
import com.arflix.tv.music.MusicServerConfig
import com.arflix.tv.music.MusicServerType
import com.arflix.tv.music.MusicSource
import com.arflix.tv.music.MusicTrack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MusicTab { HOME, ALBUMS, ARTISTS }

sealed interface MusicPage {
    data class Album(
        val album: MusicAlbum,
        val tracks: List<MusicTrack> = emptyList(),
        val loading: Boolean = true,
        val error: String? = null,
    ) : MusicPage

    data class Artist(
        val artist: MusicArtist,
        val albums: List<MusicAlbum> = emptyList(),
        val loading: Boolean = true,
        val error: String? = null,
    ) : MusicPage
}

data class MusicUiState(
    val configLoaded: Boolean = false,
    val config: MusicServerConfig? = null,
    val connecting: Boolean = false,
    val connectError: String? = null,
    val tab: MusicTab = MusicTab.HOME,
    val loading: Boolean = false,
    val error: String? = null,
    val newest: List<MusicAlbum> = emptyList(),
    val recent: List<MusicAlbum> = emptyList(),
    val frequent: List<MusicAlbum> = emptyList(),
    val random: List<MusicAlbum> = emptyList(),
    val allAlbums: List<MusicAlbum>? = null,
    val artists: List<MusicArtist>? = null,
    val query: String = "",
    val searching: Boolean = false,
    val results: MusicSearchResult? = null,
    val pages: List<MusicPage> = emptyList(),
    val playerOpen: Boolean = false,
)

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val repository: MusicRepository,
    private val player: MusicPlayer,
) : ViewModel() {

    private val _state = MutableStateFlow(MusicUiState())
    val state: StateFlow<MusicUiState> = _state.asStateFlow()
    val playback: StateFlow<MusicPlaybackState> = player.state

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            repository.config.collect { config ->
                val changed = config != _state.value.config
                _state.update { it.copy(configLoaded = true, config = config) }
                if (config != null && changed) {
                    _state.update { MusicUiState(configLoaded = true, config = config) }
                    loadHome()
                }
            }
        }
    }

    private fun source(): MusicSource? = _state.value.config?.let { repository.sourceFor(it) }

    fun connect(type: MusicServerType, url: String, username: String, password: String) {
        if (_state.value.connecting) return
        _state.update { it.copy(connecting = true, connectError = null) }
        viewModelScope.launch {
            runCatching { repository.connect(type, url, username, password) }
                .onSuccess { _state.update { it.copy(connecting = false) } }
                .onFailure { error ->
                    _state.update { it.copy(connecting = false, connectError = error.message ?: error.javaClass.simpleName) }
                }
        }
    }

    fun disconnect() {
        player.stop()
        viewModelScope.launch {
            repository.disconnect()
            _state.update { MusicUiState(configLoaded = true) }
        }
    }

    fun refresh() = loadHome()

    private fun loadHome() {
        val source = source() ?: return
        _state.update { it.copy(loading = true, error = null) }
        viewModelScope.launch {
            runCatching { coroutineScope {
                val newest = async { source.albums(AlbumShelf.NEWEST, 24) }
                val recent = async { runCatching { source.albums(AlbumShelf.RECENT, 24) }.getOrDefault(emptyList()) }
                val frequent = async { runCatching { source.albums(AlbumShelf.FREQUENT, 24) }.getOrDefault(emptyList()) }
                val random = async { runCatching { source.albums(AlbumShelf.RANDOM, 24) }.getOrDefault(emptyList()) }
                _state.update {
                    it.copy(
                        loading = false,
                        newest = newest.await(),
                        recent = recent.await(),
                        frequent = frequent.await(),
                        random = random.await(),
                    )
                }
            } }.onFailure { error -> _state.update { it.copy(loading = false, error = error.message) } }
        }
    }

    fun selectTab(tab: MusicTab) {
        _state.update { it.copy(tab = tab) }
        val source = source() ?: return
        when (tab) {
            MusicTab.ALBUMS -> if (_state.value.allAlbums == null) viewModelScope.launch {
                runCatching { source.albums(AlbumShelf.ALPHABETICAL, 500) }
                    .onSuccess { albums -> _state.update { it.copy(allAlbums = albums) } }
                    .onFailure { error -> _state.update { it.copy(allAlbums = emptyList(), error = error.message) } }
            }
            MusicTab.ARTISTS -> if (_state.value.artists == null) viewModelScope.launch {
                runCatching { source.artists() }
                    .onSuccess { artists -> _state.update { it.copy(artists = artists) } }
                    .onFailure { error -> _state.update { it.copy(artists = emptyList(), error = error.message) } }
            }
            MusicTab.HOME -> Unit
        }
    }

    fun setQuery(query: String) {
        _state.update { it.copy(query = query) }
        searchJob?.cancel()
        val source = source() ?: return
        if (query.isBlank()) {
            _state.update { it.copy(searching = false, results = null) }
            return
        }
        searchJob = viewModelScope.launch {
            delay(300)
            _state.update { it.copy(searching = true) }
            val result = runCatching { source.search(query.trim()) }.getOrDefault(MusicSearchResult())
            _state.update { it.copy(searching = false, results = result) }
        }
    }

    fun openAlbum(album: MusicAlbum) {
        val source = source() ?: return
        _state.update { it.copy(pages = it.pages + MusicPage.Album(album)) }
        viewModelScope.launch {
            val result = runCatching { source.albumTracks(album.id) }
            replacePage<MusicPage.Album>({ it.album.id == album.id }) { page ->
                result.fold(
                    onSuccess = { page.copy(tracks = it, loading = false) },
                    onFailure = { page.copy(loading = false, error = it.message) },
                )
            }
        }
    }

    fun openArtist(artist: MusicArtist) {
        val source = source() ?: return
        _state.update { it.copy(pages = it.pages + MusicPage.Artist(artist)) }
        viewModelScope.launch {
            val result = runCatching { source.artistAlbums(artist.id) }
            replacePage<MusicPage.Artist>({ it.artist.id == artist.id }) { page ->
                result.fold(
                    onSuccess = { page.copy(albums = it, loading = false) },
                    onFailure = { page.copy(loading = false, error = it.message) },
                )
            }
        }
    }

    /** Opens the artist of an album or track when only the name and id are known. */
    fun openArtist(id: String?, name: String) {
        if (id.isNullOrBlank()) return
        openArtist(MusicArtist(id = id, name = name, coverUrl = null))
    }

    private inline fun <reified T : MusicPage> replacePage(crossinline match: (T) -> Boolean, crossinline transform: (T) -> T) {
        _state.update { state ->
            state.copy(pages = state.pages.map { page -> if (page is T && match(page)) transform(page) else page })
        }
    }

    /** Returns false when there was nothing to close, so the caller can leave the tab. */
    fun back(): Boolean {
        val current = _state.value
        return when {
            current.playerOpen -> { _state.update { it.copy(playerOpen = false) }; true }
            current.pages.isNotEmpty() -> { _state.update { it.copy(pages = it.pages.dropLast(1)) }; true }
            current.query.isNotEmpty() -> { setQuery(""); true }
            else -> false
        }
    }

    fun setPlayerOpen(open: Boolean) = _state.update { it.copy(playerOpen = open) }

    fun play(tracks: List<MusicTrack>, index: Int = 0) = player.playQueue(tracks, index)
    fun shuffle(tracks: List<MusicTrack>) = player.playQueue(tracks, shuffle = true)
    fun togglePlay() = player.togglePlay()
    fun next() = player.next()
    fun previous() = player.previous()
    fun seekTo(positionMs: Long) = player.seekTo(positionMs)
    fun skipTo(index: Int) = player.skipTo(index)
    fun toggleShuffle() = player.toggleShuffle()
    fun cycleRepeat() = player.cycleRepeat()

    /** Plays a whole album from the home shelves without opening it first. */
    fun playAlbum(album: MusicAlbum) {
        val source = source() ?: return
        viewModelScope.launch {
            runCatching { source.albumTracks(album.id) }.onSuccess { player.playQueue(it) }
        }
    }
}
