package com.arflix.tv.music

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * The song the Music player holds, for the indicators outside the Music screen (the TV top bar,
 * the phone's bottom tabs). Music keeps playing after the user leaves the Music screen, so the
 * rest of the app needs to show it without holding the player itself.
 */
object MusicNowPlaying {
    data class Song(val title: String, val artist: String, val coverUrl: String?, val isPlaying: Boolean)

    private val _song = MutableStateFlow<Song?>(null)
    /** Changes only with the song or play/pause, never with the position, so it is cheap to watch. */
    val song: StateFlow<Song?> = _song.asStateFlow()

    private val _openPlayer = MutableStateFlow(false)
    /** True after an indicator was tapped: the Music screen opens its full player. */
    val openPlayerRequest: StateFlow<Boolean> = _openPlayer.asStateFlow()

    internal var togglePlay: () -> Unit = {}
    internal var stopPlayback: () -> Unit = {}

    internal fun publish(song: Song?) {
        _song.value = song
    }

    fun toggle() = togglePlay()

    /** Stops the song and empties the queue: the indicators and the media notification go away. */
    fun close() = stopPlayback()

    fun requestOpenPlayer() {
        _openPlayer.value = true
    }

    /** Takes the request; false when nothing asked for the player since the last call. */
    fun takeOpenPlayerRequest(): Boolean {
        if (!_openPlayer.value) return false
        _openPlayer.value = false
        return true
    }
}
