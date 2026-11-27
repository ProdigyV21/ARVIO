package com.arflix.tv.music

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

data class MusicPlaybackState(
    val queue: List<MusicTrack> = emptyList(),
    val index: Int = -1,
    val isPlaying: Boolean = false,
    val isBuffering: Boolean = false,
    val positionMs: Long = 0L,
    val durationMs: Long = 0L,
    val shuffle: Boolean = false,
    val repeatMode: Int = Player.REPEAT_MODE_OFF,
    /** Why the current song stopped or never started; shown in the player until the next try. */
    val problem: MusicPlaybackProblem? = null,
    /** How long the song has been loading: [LoadingWait.NONE] at first, then a note, then the reason it can take long. */
    val loadingWait: LoadingWait = LoadingWait.NONE,
) {
    enum class LoadingWait { NONE, PREPARING, SLOW }

    val current: MusicTrack? get() = queue.getOrNull(index)
    val hasNext: Boolean get() = index in 0 until queue.lastIndex || repeatMode != Player.REPEAT_MODE_OFF
}

/**
 * The app's handle on [MusicPlaybackService]: starts the queue, sends the controls and
 * mirrors what is playing as a [StateFlow] for the Music screens.
 */
@Singleton
class MusicPlayer @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: MusicRepository,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val _state = MutableStateFlow(MusicPlaybackState())
    val state: StateFlow<MusicPlaybackState> = _state.asStateFlow()

    private var controller: MediaController? = null
    private var ticker: Job? = null
    private var loadingWatch: Job? = null
    private var loadingSong: String? = null
    private val tracks = mutableMapOf<String, MusicTrack>()
    private var reportedFor: String? = null

    init {
        MusicNowPlaying.togglePlay = ::togglePlay
        MusicNowPlaying.stopPlayback = ::stop
    }

    private val listener = object : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) = sync(player)
    }

    private suspend fun controller(): MediaController {
        controller?.let { return it }
        val token = SessionToken(context, ComponentName(context, MusicPlaybackService::class.java))
        val future = MediaController.Builder(context, token).buildAsync()
        val built = suspendCancellableCoroutine { cont ->
            future.addListener({
                runCatching { future.get() }
                    .onSuccess { cont.resume(it) }
                    .onFailure { cont.resumeWithException(it) }
            }, ContextCompat.getMainExecutor(context))
            cont.invokeOnCancellation { MediaController.releaseFuture(future) }
        }
        built.addListener(listener)
        controller = built
        sync(built)
        return built
    }

    fun playQueue(queue: List<MusicTrack>, startIndex: Int = 0, shuffle: Boolean = false) {
        if (queue.isEmpty()) return
        scope.launch {
            runCatching {
                val player = controller()
                queue.forEach { tracks[it.id] = it }
                player.shuffleModeEnabled = shuffle
                val start = if (shuffle) queue.indices.random() else startIndex.coerceIn(queue.indices)
                player.setMediaItems(queue.map { it.toMediaItem() }, start, 0L)
                player.prepare()
                player.play()
            }.onFailure { error ->
                _state.update { it.copy(problem = MusicPlaybackProblem(MusicPlaybackProblem.Kind.OTHER, detail = error.message)) }
            }
        }
    }

    fun togglePlay() = withController { if (it.isPlaying) it.pause() else { if (it.playbackState == Player.STATE_IDLE) it.prepare(); it.play() } }
    fun next() = withController { it.seekToNext() }
    fun previous() = withController { it.seekToPrevious() }
    fun seekTo(positionMs: Long) = withController { it.seekTo(positionMs) }
    fun skipTo(index: Int) = withController { if (index in 0 until it.mediaItemCount) it.seekToDefaultPosition(index) }
    fun toggleShuffle() = withController { it.shuffleModeEnabled = !it.shuffleModeEnabled }
    fun cycleRepeat() = withController {
        it.repeatMode = when (it.repeatMode) {
            Player.REPEAT_MODE_OFF -> Player.REPEAT_MODE_ALL
            Player.REPEAT_MODE_ALL -> Player.REPEAT_MODE_ONE
            else -> Player.REPEAT_MODE_OFF
        }
    }

    fun stop() = withController {
        it.stop()
        it.clearMediaItems()
    }

    private fun withController(action: (MediaController) -> Unit) {
        scope.launch { runCatching { action(controller()) } }
    }

    private fun sync(player: Player) {
        val queue = (0 until player.mediaItemCount).mapNotNull { tracks[player.getMediaItemAt(it).mediaId] }
        _state.update {
            it.copy(
                queue = queue,
                index = if (queue.size == player.mediaItemCount) player.currentMediaItemIndex else -1,
                isPlaying = player.isPlaying,
                isBuffering = player.playbackState == Player.STATE_BUFFERING,
                positionMs = player.currentPosition.coerceAtLeast(0L),
                durationMs = player.duration.takeIf { d -> d > 0 } ?: ((queue.getOrNull(player.currentMediaItemIndex)?.durationSec ?: 0) * 1000L),
                shuffle = player.shuffleModeEnabled,
                repeatMode = player.repeatMode,
                problem = player.playerError?.let(MusicPlaybackProblem::of),
            )
        }
        MusicNowPlaying.publish(_state.value.current?.let { MusicNowPlaying.Song(it.title, it.artist, it.coverUrl, player.isPlaying) })
        if (player.isPlaying) startTicker(player) else stopTicker()
        val loading = player.playbackState == Player.STATE_BUFFERING && !player.isPlaying
        watchLoading(if (loading) "${player.currentMediaItemIndex}/${player.currentMediaItem?.mediaId}" else null)
    }

    /**
     * Some addons fetch a song on its first play and take up to a minute or two before it can
     * start; after a few seconds the player says it is still preparing, later why it can take that long.
     */
    private fun watchLoading(song: String?) {
        // Still the same wait; another song (or the same one loading again later) starts its own.
        if (song == loadingSong) return
        loadingWatch?.cancel()
        loadingWatch = null
        loadingSong = song
        if (_state.value.loadingWait != MusicPlaybackState.LoadingWait.NONE) {
            _state.update { it.copy(loadingWait = MusicPlaybackState.LoadingWait.NONE) }
        }
        if (song == null) return
        loadingWatch = scope.launch {
            delay(PREPARING_NOTE_MS)
            _state.update { it.copy(loadingWait = MusicPlaybackState.LoadingWait.PREPARING) }
            delay(SLOW_NOTE_MS - PREPARING_NOTE_MS)
            _state.update { it.copy(loadingWait = MusicPlaybackState.LoadingWait.SLOW) }
        }
    }

    private fun startTicker(player: Player) {
        if (ticker?.isActive == true) return
        ticker = scope.launch {
            while (isActive) {
                val position = player.currentPosition.coerceAtLeast(0L)
                _state.update { it.copy(positionMs = position) }
                maybeReportPlayed(player, position)
                delay(500)
            }
        }
    }

    private fun stopTicker() {
        ticker?.cancel()
        ticker = null
    }

    /** Counts a play once half the track (or four minutes) has been heard, like scrobblers do. */
    private fun maybeReportPlayed(player: Player, positionMs: Long) {
        val id = player.currentMediaItem?.mediaId ?: return
        val playKey = "$id#${player.currentMediaItemIndex}"
        if (playKey == reportedFor) return
        val duration = player.duration.takeIf { it > 0 } ?: return
        if (positionMs < minOf(duration / 2, 240_000L)) return
        reportedFor = playKey
        scope.launch(Dispatchers.IO) { runCatching { repository.currentSource()?.reportPlayed(id) } }
    }

    private fun MusicTrack.toMediaItem(): MediaItem {
        val uri = Uri.parse(streamUrl)
        return MediaItem.Builder()
            .setMediaId(id)
            .setUri(uri)
            .setRequestMetadata(MediaItem.RequestMetadata.Builder().setMediaUri(uri).build())
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(title)
                    .setArtist(artist)
                    .setAlbumTitle(album)
                    .setArtworkUri(coverUrl?.let(Uri::parse))
                    .setIsPlayable(true)
                    .setIsBrowsable(false)
                    .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                    .build()
            )
            .build()
    }

    private companion object {
        const val PREPARING_NOTE_MS = 3_000L
        const val SLOW_NOTE_MS = 15_000L
    }
}
