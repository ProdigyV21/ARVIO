package com.arflix.tv.ui.screens.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.music.AlbumShelf
import com.arflix.tv.music.MusicAlbum
import com.arflix.tv.music.MusicAddon
import com.arflix.tv.music.MusicCollections
import com.arflix.tv.music.MusicArtist
import com.arflix.tv.music.MusicLibrary
import com.arflix.tv.music.MusicNameLookup
import com.arflix.tv.music.MusicPlaybackState
import com.arflix.tv.music.MusicPlayer
import com.arflix.tv.music.MusicRepository
import com.arflix.tv.music.MusicSearchRanking
import com.arflix.tv.music.MusicSearchResult
import com.arflix.tv.music.MusicServerConfig
import com.arflix.tv.music.MusicServerType
import com.arflix.tv.music.MusicShelf
import com.arflix.tv.music.MusicSource
import com.arflix.tv.music.MusicTrack
import com.arflix.tv.music.MusicLucky
import com.arflix.tv.music.MusicLyrics
import com.arflix.tv.music.MusicPin
import com.arflix.tv.music.MusicPinType
import com.arflix.tv.music.MusicTaste
import android.net.Uri
import com.arflix.tv.music.SpotifyImport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

enum class MusicTab { HOME, MINE, ALBUMS, ARTISTS }

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

    /** One of the user's own playlists, or their liked songs ([LIKED]); read live from the collections. */
    data class Playlist(val id: String) : MusicPage

    companion object {
        const val LIKED = MusicCollections.LIKED_ID
    }
}

data class MusicUiState(
    val configLoaded: Boolean = false,
    val library: MusicLibrary = MusicLibrary(),
    val connecting: Boolean = false,
    val connectError: String? = null,
    val addingAddon: Boolean = false,
    val addonError: String? = null,
    val tab: MusicTab = MusicTab.HOME,
    val loading: Boolean = false,
    val error: String? = null,
    val shelves: List<MusicShelf> = emptyList(),
    val allAlbums: List<MusicAlbum>? = null,
    val artists: List<MusicArtist>? = null,
    val query: String = "",
    val searching: Boolean = false,
    val results: MusicSearchResult? = null,
    val pages: List<MusicPage> = emptyList(),
    val playerOpen: Boolean = false,
    val collections: MusicCollections = MusicCollections(),
    /** Tracks waiting for the user to pick a playlist to add them to. */
    val addingToPlaylist: List<MusicTrack>? = null,
    /** Songs looked up so far and in all, while a Spotify import runs. */
    val importProgress: Pair<Int, Int>? = null,
    val importResult: SpotifyImportResult? = null,
    /** What the profile listened to; its recently played songs make a home row. */
    val taste: MusicTaste = MusicTaste(),
    /** The lyrics shown in the player, for the song [LyricsUi.trackId]. */
    val lyrics: LyricsUi? = null,
) {
    val config: MusicServerConfig? get() = library.server
    val hasSources: Boolean get() = !library.isEmpty
}

/** Lyrics of one song: still loading, found ([lyrics] may be empty), or [failed] to load. */
data class LyricsUi(
    val trackId: String,
    val loading: Boolean = true,
    val lyrics: MusicLyrics? = null,
    val failed: Boolean = false,
)

/** How a Spotify import went; [error] when it could not run at all. */
data class SpotifyImportResult(
    val playlists: Int = 0,
    val found: Int = 0,
    val total: Int = 0,
    val error: String? = null,
)

/** What the Music screens can ask for; the view model in the app, a stand-in in screenshot tests. */
interface MusicActions {
    fun connect(type: MusicServerType, url: String, username: String, password: String)
    fun addAddon(url: String)
    fun disconnect()
    fun removeAddon(addon: MusicAddon)
    fun refresh()
    fun selectTab(tab: MusicTab)
    fun setQuery(query: String)
    fun openAlbum(album: MusicAlbum)
    fun openArtist(artist: MusicArtist)
    fun openArtist(id: String?, name: String)
    /** Returns false when there was nothing to close, so the caller can leave the tab. */
    fun back(): Boolean
    fun setPlayerOpen(open: Boolean)
    fun play(tracks: List<MusicTrack>, index: Int = 0)
    fun shuffle(tracks: List<MusicTrack>)
    fun togglePlay()
    fun next()
    fun previous()
    fun seekTo(positionMs: Long)
    fun skipTo(index: Int)
    fun toggleShuffle()
    fun cycleRepeat()
    fun playAlbum(album: MusicAlbum)

    fun toggleFavorite(track: MusicTrack)
    fun toggleFavorite(album: MusicAlbum)
    fun openPlaylist(id: String)
    /** Makes a playlist; with tracks it also adds them, as from "Add to playlist". */
    fun createPlaylist(name: String, tracks: List<MusicTrack> = emptyList())
    fun renamePlaylist(id: String, name: String)
    fun deletePlaylist(id: String)
    fun removeFromPlaylist(id: String, index: Int)
    fun movePlaylistTrack(id: String, from: Int, to: Int)
    /** Opens (or with null closes) the playlist picker for these tracks. */
    fun showAddToPlaylist(tracks: List<MusicTrack>?)
    fun addToPlaylist(id: String, tracks: List<MusicTrack>)
    /** Reads Spotify export files the user picked and rebuilds those playlists from their own sources. */
    fun importSpotify(files: List<Uri>)
    fun dismissImportResult()

    // Speed dial, Surprise me, queue, sleep timer and lyrics; no-ops for screenshot stand-ins.
    fun togglePin(pin: MusicPin) = Unit
    fun openPin(pin: MusicPin) = Unit
    fun surpriseMe() = Unit
    fun playNext(track: MusicTrack) = Unit
    fun addToQueue(track: MusicTrack) = Unit
    fun removeFromQueue(index: Int) = Unit
    fun moveInQueue(from: Int, to: Int) = Unit
    fun setSleepTimer(minutes: Int) = Unit
    fun sleepAtEndOfTrack() = Unit
    fun cancelSleepTimer() = Unit
    fun loadLyrics(track: MusicTrack) = Unit
}

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val repository: MusicRepository,
    private val player: MusicPlayer,
) : ViewModel(), MusicActions {

    private val _state = MutableStateFlow(MusicUiState())
    val state: StateFlow<MusicUiState> = _state.asStateFlow()
    val playback: StateFlow<MusicPlaybackState> = player.state

    private var searchJob: Job? = null

    private companion object {
        /** How long a search's results stay on screen before it counts as one the user meant. */
        const val SEARCH_SETTLED_MS = 2_500L
    }

    init {
        viewModelScope.launch {
            repository.library.collect { library ->
                val changed = library != _state.value.library
                if (!changed && _state.value.configLoaded) return@collect
                // A new or removed source starts the library over, but keeps the open player.
                _state.update {
                    MusicUiState(
                        configLoaded = true, library = library, playerOpen = it.playerOpen,
                        collections = it.collections, taste = it.taste, lyrics = it.lyrics,
                    )
                }
                if (!library.isEmpty) loadHome()
            }
        }
        viewModelScope.launch {
            repository.collections.collect { collections -> _state.update { it.copy(collections = collections) } }
        }
        // The now-playing bar outside Music was tapped: show the song.
        viewModelScope.launch {
            com.arflix.tv.music.MusicNowPlaying.openPlayerRequest.collect { requested ->
                if (requested && com.arflix.tv.music.MusicNowPlaying.takeOpenPlayerRequest()) setPlayerOpen(true)
            }
        }
        viewModelScope.launch {
            repository.taste.collect { taste -> _state.update { it.copy(taste = taste) } }
        }
    }

    override fun togglePin(pin: MusicPin) = edit { it.togglePin(pin) }

    override fun openPin(pin: MusicPin) = when (pin.type) {
        MusicPinType.ALBUM -> openAlbum(pin.toAlbum())
        MusicPinType.ARTIST -> openArtist(pin.toArtist())
        MusicPinType.PLAYLIST -> openPlaylist(pin.id)
    }

    override fun surpriseMe() {
        val current = _state.value
        when (val pick = MusicLucky.pick(current.collections, current.taste, current.shelves)) {
            is MusicLucky.Pick.Tracks -> player.playQueue(pick.tracks)
            is MusicLucky.Pick.Album -> {
                val source = source() ?: return
                viewModelScope.launch {
                    runCatching { source.albumTracks(pick.album.id) }
                        .onSuccess { tracks -> if (tracks.isNotEmpty()) player.playQueue(tracks.shuffled()) }
                }
            }
            null -> return
        }
        _state.update { it.copy(playerOpen = true) }
    }

    override fun playNext(track: MusicTrack) = player.playNext(track)
    override fun addToQueue(track: MusicTrack) = player.addToQueue(track)
    override fun removeFromQueue(index: Int) = player.removeFromQueue(index)
    override fun moveInQueue(from: Int, to: Int) = player.moveInQueue(from, to)
    override fun setSleepTimer(minutes: Int) = player.setSleepTimer(minutes)
    override fun sleepAtEndOfTrack() = player.sleepAtEndOfTrack()
    override fun cancelSleepTimer() = player.cancelSleepTimer()

    override fun loadLyrics(track: MusicTrack) {
        val shown = _state.value.lyrics
        if (shown?.trackId == track.id && !shown.failed) return
        _state.update { it.copy(lyrics = LyricsUi(track.id)) }
        viewModelScope.launch {
            val result = runCatching { repository.lyrics.find(track) }
            _state.update { state ->
                if (state.lyrics?.trackId != track.id) state
                else state.copy(lyrics = LyricsUi(track.id, loading = false, lyrics = result.getOrNull(), failed = result.isFailure))
            }
        }
    }

    private fun edit(transform: (MusicCollections) -> MusicCollections) {
        viewModelScope.launch { repository.updateCollections(transform) }
    }

    override fun toggleFavorite(track: MusicTrack) = edit { it.toggleFavorite(track) }
    override fun toggleFavorite(album: MusicAlbum) = edit { it.toggleFavorite(album) }

    override fun openPlaylist(id: String) = _state.update { it.copy(pages = it.pages + MusicPage.Playlist(id)) }

    override fun createPlaylist(name: String, tracks: List<MusicTrack>) {
        _state.update { it.copy(addingToPlaylist = null) }
        edit { it.createPlaylist(UUID.randomUUID().toString(), name, tracks) }
    }

    override fun renamePlaylist(id: String, name: String) = edit { it.renamePlaylist(id, name) }

    override fun deletePlaylist(id: String) {
        _state.update { state -> state.copy(pages = state.pages.filterNot { it is MusicPage.Playlist && it.id == id }) }
        edit { it.deletePlaylist(id) }
    }

    override fun removeFromPlaylist(id: String, index: Int) = edit { it.removeFromPlaylist(id, index) }
    override fun movePlaylistTrack(id: String, from: Int, to: Int) = edit { it.moveInPlaylist(id, from, to) }
    override fun showAddToPlaylist(tracks: List<MusicTrack>?) = _state.update { it.copy(addingToPlaylist = tracks?.takeIf { t -> t.isNotEmpty() }) }

    override fun importSpotify(files: List<Uri>) {
        if (files.isEmpty() || _state.value.importProgress != null) return
        val source = source() ?: return
        _state.update { it.copy(importProgress = 0 to 0, importResult = null) }
        viewModelScope.launch {
            val result = runCatching {
                val playlists = withContext(Dispatchers.IO) {
                    files.flatMap { uri -> repository.readDocument(uri).let { (name, bytes) -> SpotifyImport.parse(name, bytes) } }
                }
                if (playlists.isEmpty()) return@runCatching SpotifyImportResult(error = "no_playlists")
                // A song on several playlists is looked up once.
                val unique = playlists.flatMap { it.tracks }.distinct()
                val matches = SpotifyImport.match(source, unique) { done, total ->
                    _state.update { it.copy(importProgress = done to total) }
                }
                val byTrack = unique.zip(matches).toMap()
                repository.updateCollections { collections ->
                    playlists.fold(collections) { acc, playlist ->
                        val tracks = playlist.tracks.mapNotNull { byTrack[it] }
                        when {
                            tracks.isEmpty() -> acc
                            playlist.liked -> tracks.asReversed().fold(acc) { c, t -> if (c.isFavorite(t)) c else c.toggleFavorite(t) }
                            else -> acc.replaceImported(UUID.randomUUID().toString(), playlist.name, tracks, "spotify:" + playlist.name)
                        }
                    }
                }
                SpotifyImportResult(
                    playlists = playlists.size,
                    found = playlists.sumOf { p -> p.tracks.count { byTrack[it] != null } },
                    total = playlists.sumOf { it.tracks.size },
                )
            }.getOrElse { SpotifyImportResult(error = it.message ?: it.javaClass.simpleName) }
            _state.update { it.copy(importProgress = null, importResult = result) }
        }
    }

    override fun dismissImportResult() = _state.update { it.copy(importResult = null) }

    override fun addToPlaylist(id: String, tracks: List<MusicTrack>) {
        _state.update { it.copy(addingToPlaylist = null) }
        edit { it.addToPlaylist(id, tracks) }
    }

    private fun source(): MusicSource? = repository.sourceFor(_state.value.library)

    override fun connect(type: MusicServerType, url: String, username: String, password: String) {
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

    override fun disconnect() {
        player.stop()
        viewModelScope.launch { repository.disconnect() }
    }

    override fun addAddon(url: String) {
        if (_state.value.addingAddon) return
        _state.update { it.copy(addingAddon = true, addonError = null) }
        viewModelScope.launch {
            runCatching { repository.addAddon(url) }
                .onSuccess { _state.update { it.copy(addingAddon = false) } }
                .onFailure { error ->
                    _state.update { it.copy(addingAddon = false, addonError = error.message ?: error.javaClass.simpleName) }
                }
        }
    }

    fun clearAddonError() = _state.update { it.copy(addonError = null) }

    override fun removeAddon(addon: MusicAddon) {
        player.stop()
        viewModelScope.launch { repository.removeAddon(addon.baseUrl) }
    }

    override fun refresh() = loadHome()

    private fun loadHome() {
        val source = source() ?: return
        _state.update { it.copy(loading = true, error = null) }
        viewModelScope.launch {
            runCatching { source.shelves() }
                .onSuccess { shelves -> _state.update { it.copy(loading = false, shelves = MusicSearchRanking.arrangeHome(shelves)) } }
                .onFailure { error -> _state.update { it.copy(loading = false, error = error.message) } }
        }
    }

    override fun selectTab(tab: MusicTab) {
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
            MusicTab.HOME, MusicTab.MINE -> Unit
        }
    }

    override fun setQuery(query: String) {
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
            val result = MusicNameLookup.search(source, query.trim(), repository::latinNames)
                .let { it.copy(tracks = MusicSearchRanking.rankTracks(query, it.tracks, it.aliases)) }
            _state.update { it.copy(searching = false, results = result) }
            // A search the user stays on (not one they are still typing) feeds the recommendations.
            if (!result.isEmpty) {
                delay(SEARCH_SETTLED_MS)
                runCatching { repository.recordSearch(query) }
            }
        }
    }

    override fun openAlbum(album: MusicAlbum) {
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

    override fun openArtist(artist: MusicArtist) {
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
    override fun openArtist(id: String?, name: String) {
        if (id.isNullOrBlank()) return
        openArtist(MusicArtist(id = id, name = name, coverUrl = null))
    }

    private inline fun <reified T : MusicPage> replacePage(crossinline match: (T) -> Boolean, crossinline transform: (T) -> T) {
        _state.update { state ->
            state.copy(pages = state.pages.map { page -> if (page is T && match(page)) transform(page) else page })
        }
    }

    /** Returns false when there was nothing to close, so the caller can leave the tab. */
    override fun back(): Boolean {
        val current = _state.value
        return when {
            current.addingToPlaylist != null -> { _state.update { it.copy(addingToPlaylist = null) }; true }
            current.playerOpen -> { _state.update { it.copy(playerOpen = false) }; true }
            current.pages.isNotEmpty() -> { _state.update { it.copy(pages = it.pages.dropLast(1)) }; true }
            current.query.isNotEmpty() -> { setQuery(""); true }
            else -> false
        }
    }

    override fun setPlayerOpen(open: Boolean) = _state.update { it.copy(playerOpen = open) }

    override fun play(tracks: List<MusicTrack>, index: Int) = player.playQueue(tracks, index)
    override fun shuffle(tracks: List<MusicTrack>) = player.playQueue(tracks, shuffle = true)
    override fun togglePlay() = player.togglePlay()
    override fun next() = player.next()
    override fun previous() = player.previous()
    override fun seekTo(positionMs: Long) = player.seekTo(positionMs)
    override fun skipTo(index: Int) = player.skipTo(index)
    override fun toggleShuffle() = player.toggleShuffle()
    override fun cycleRepeat() = player.cycleRepeat()

    /** Plays a whole album from the home shelves without opening it first. */
    override fun playAlbum(album: MusicAlbum) {
        val source = source() ?: return
        viewModelScope.launch {
            runCatching { source.albumTracks(album.id) }.onSuccess { player.playQueue(it) }
        }
    }
}
