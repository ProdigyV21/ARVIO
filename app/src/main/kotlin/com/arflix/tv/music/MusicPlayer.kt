package com.arflix.tv.music

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.media3.common.C
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
    /** The queue positions still to come, in the order they will play (shuffle included). */
    val upNext: List<Int> = emptyList(),
    /** When the sleep timer pauses the music (wall clock), or null. */
    val sleepAtMs: Long? = null,
    /** The sleep timer pauses at the end of the current song. */
    val sleepAtEndOfTrack: Boolean = false,
) {
    enum class LoadingWait { NONE, PREPARING, SLOW }

    val current: MusicTrack? get() = queue.getOrNull(index)
    val sleepTimerOn: Boolean get() = sleepAtMs != null || sleepAtEndOfTrack
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
    private var sleepJob: Job? = null
    private var radioJob: Job? = null

    private val listener = object : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) = sync(player)

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            // "End of this song": the next one has just started on its own, so stop it right there.
            if (_state.value.sleepAtEndOfTrack && reason == Player.MEDIA_ITEM_TRANSITION_REASON_AUTO) {
                controller?.pause()
                controller?.seekTo(0L)
                _state.update { it.copy(sleepAtEndOfTrack = false) }
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            if (playbackState == Player.STATE_ENDED && _state.value.sleepAtEndOfTrack) {
                _state.update { it.copy(sleepAtEndOfTrack = false) }
            }
        }
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
        radioJob?.cancel()
        // A song played on its own is followed by similar ones, so the music does not just stop.
        if (queue.size == 1 && !shuffle) startRadio(queue[0])
    }

    private fun startRadio(seed: MusicTrack) {
        radioJob = scope.launch {
            val more = runCatching {
                kotlinx.coroutines.withContext(Dispatchers.IO) { repository.currentSource()?.radio(seed) }
            }.getOrNull().orEmpty()
            val player = runCatching { controller() }.getOrNull() ?: return@launch
            // Only while that song is still the whole queue: the user may have started something else.
            if (more.isEmpty() || player.mediaItemCount != 1 || player.currentMediaItem?.mediaId != seed.id) return@launch
            val fresh = more.distinctBy { it.id }.filter { it.id != seed.id }.take(RADIO_SIZE)
            fresh.forEach { tracks[it.id] = it }
            player.addMediaItems(fresh.map { it.toMediaItem() })
        }
    }

    /** Puts a song right after the current one, or plays it when nothing is playing. */
    fun playNext(track: MusicTrack) = withController { player ->
        if (player.mediaItemCount == 0) { playQueue(listOf(track)); return@withController }
        tracks[track.id] = track
        player.addMediaItem(player.currentMediaItemIndex + 1, track.toMediaItem())
    }

    /** Adds a song at the end of the queue, or plays it when nothing is playing. */
    fun addToQueue(track: MusicTrack) = withController { player ->
        if (player.mediaItemCount == 0) { playQueue(listOf(track)); return@withController }
        tracks[track.id] = track
        player.addMediaItem(track.toMediaItem())
    }

    fun removeFromQueue(index: Int) = withController { if (index in 0 until it.mediaItemCount && index != it.currentMediaItemIndex) it.removeMediaItem(index) }

    fun moveInQueue(from: Int, to: Int) = withController {
        if (from in 0 until it.mediaItemCount && to in 0 until it.mediaItemCount && from != to) it.moveMediaItem(from, to)
    }

    /** Pauses the music after [minutes], fading out over the last seconds. */
    fun setSleepTimer(minutes: Int) {
        sleepJob?.cancel()
        val at = System.currentTimeMillis() + minutes * 60_000L
        _state.update { it.copy(sleepAtMs = at, sleepAtEndOfTrack = false) }
        sleepJob = scope.launch {
            delay((at - System.currentTimeMillis() - SLEEP_FADE_MS).coerceAtLeast(0L))
            val player = runCatching { controller() }.getOrNull()
            if (player != null) {
                val steps = 20
                repeat(steps) { step ->
                    player.volume = 1f - (step + 1f) / steps
                    delay(SLEEP_FADE_MS / steps)
                }
                player.pause()
                player.volume = 1f
            }
            _state.update { it.copy(sleepAtMs = null) }
        }
    }

    fun sleepAtEndOfTrack() {
        sleepJob?.cancel()
        restoreVolume()
        _state.update { it.copy(sleepAtMs = null, sleepAtEndOfTrack = true) }
    }

    fun cancelSleepTimer() {
        sleepJob?.cancel()
        restoreVolume()
        _state.update { it.copy(sleepAtMs = null, sleepAtEndOfTrack = false) }
    }

    private fun restoreVolume() = withController { if (it.volume < 1f) it.volume = 1f }

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
                upNext = if (queue.size == player.mediaItemCount) upcoming(player) else emptyList(),
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

    /** The positions after the current one in play order, following the shuffle when it is on. */
    private fun upcoming(player: Player): List<Int> {
        val timeline = player.currentTimeline
        if (timeline.isEmpty || player.currentMediaItemIndex == C.INDEX_UNSET) return emptyList()
        val order = mutableListOf<Int>()
        var i = player.currentMediaItemIndex
        repeat(timeline.windowCount) {
            i = timeline.getNextWindowIndex(i, Player.REPEAT_MODE_OFF, player.shuffleModeEnabled)
            if (i == C.INDEX_UNSET) return order
            order += i
        }
        return order
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
        val track = tracks[id]
        scope.launch(Dispatchers.IO) {
            runCatching { repository.currentSource()?.reportPlayed(id) }
            // The listening history behind "Recently played" and the "Because you listened to" rows.
            if (track != null) runCatching { repository.recordPlay(track) }
        }
    }

    private companion object {
        const val RADIO_SIZE = 25
        const val SLEEP_FADE_MS = 8_000L
        const val PREPARING_NOTE_MS = 3_000L
        const val SLOW_NOTE_MS = 15_000L
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
}
