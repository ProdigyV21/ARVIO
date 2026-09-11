package com.arflix.tv.data.repository.simkl

import com.arflix.tv.data.api.SimklApi
import com.arflix.tv.data.api.SimklEpisodeRef
import com.arflix.tv.data.api.SimklIds
import com.arflix.tv.data.api.SimklMovieRef
import com.arflix.tv.data.api.SimklScrobbleBody
import com.arflix.tv.data.api.SimklShowRef
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimklScrobbler @Inject constructor(
    private val simklApi: SimklApi,
    private val authManager: SimklAuthManager
) {
    private enum class Action { START, PAUSE, STOP }

    private data class Command(
        val action: Action,
        val authHeader: String,
        val body: SimklScrobbleBody,
        val tmdbId: Int
    )

    private companion object {
        const val WRITE_LOCK_MS = 20_500L
    }

    private val clientId: String get() = Constants.SIMKL_CLIENT_ID
    private val queueScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val queueMutex = Mutex()
    private var hasWritten = false
    private var lastWriteAt = 0L
    private val commandQueue = ArrayDeque<Command>()
    private var workerJob: Job? = null
    internal var elapsedRealtimeMs: () -> Long = { android.os.SystemClock.elapsedRealtime() }

    private fun normalizeProgress(progress: Float): Float {
        // Enforce consistent 0.0 - 100.0 scale without multiplier heuristic
        return progress.coerceIn(0f, 100f)
    }

    suspend fun scrobbleStart(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null,
        isAnime: Boolean = false
    ) {
        val token = authManager.getAccessToken() ?: return
        val authHeader = "Bearer $token"
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode, isAnime)

        submit(Command(Action.START, authHeader, body, tmdbId))
    }

    suspend fun scrobblePause(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null,
        isAnime: Boolean = false
    ) {
        val token = authManager.getAccessToken() ?: return
        val authHeader = "Bearer $token"
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode, isAnime)

        submit(Command(Action.PAUSE, authHeader, body, tmdbId))
    }

    suspend fun scrobbleStop(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null,
        isAnime: Boolean = false
    ) {
        val token = authManager.getAccessToken() ?: return
        val authHeader = "Bearer $token"
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode, isAnime)

        submit(Command(Action.STOP, authHeader, body, tmdbId))
    }

    private suspend fun submit(command: Command) {
        var immediate: Command? = null
        queueMutex.withLock {
            val now = elapsedRealtimeMs()
            val remaining = if (hasWritten) {
                WRITE_LOCK_MS - (now - lastWriteAt)
            } else {
                0L
            }
            if (remaining <= 0L && commandQueue.isEmpty() && workerJob == null) {
                hasWritten = true
                lastWriteAt = now
                immediate = command
            } else {
                commandQueue.addLast(command)
                ensureWorkerLocked()
            }
        }
        immediate?.let { execute(it) }
    }

    private fun ensureWorkerLocked() {
        if (workerJob?.isActive == true) return
        workerJob = queueScope.launch {
            while (true) {
                var waitTime = 0L
                val nextCommand: Command? = queueMutex.withLock {
                    if (commandQueue.isEmpty()) {
                        workerJob = null
                        return@launch
                    }
                    val now = elapsedRealtimeMs()
                    val remaining = if (hasWritten) {
                        WRITE_LOCK_MS - (now - lastWriteAt)
                    } else {
                        0L
                    }
                    if (remaining > 0L) {
                        waitTime = remaining
                        null
                    } else {
                        hasWritten = true
                        lastWriteAt = now
                        commandQueue.removeFirst()
                    }
                }

                if (nextCommand != null) {
                    execute(nextCommand)
                } else if (waitTime > 0L) {
                    delay(waitTime)
                }
            }
        }
    }

    private suspend fun execute(command: Command) {
        try {
            val activeToken = authManager.getAccessToken() ?: return
            if (command.authHeader != "Bearer $activeToken") return
            val response = when (command.action) {
                Action.START -> simklApi.scrobbleStart(command.authHeader, clientId, command.body)
                Action.PAUSE -> simklApi.scrobblePause(command.authHeader, clientId, command.body)
                Action.STOP -> simklApi.scrobbleStop(command.authHeader, clientId, command.body)
            }
            if (!response.isSuccessful) {
                AppLogger.e(
                    "SimklScrobbler",
                    "${command.action} rejected for tmdbId=${command.tmdbId}: HTTP ${response.code()}"
                )
            }
        } catch (e: Exception) {
            AppLogger.e(
                "SimklScrobbler",
                "Error scrobbling ${command.action} for tmdbId=${command.tmdbId}: ${e.message}"
            )
        }
    }

    private fun buildScrobbleBody(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?,
        isAnime: Boolean
    ): SimklScrobbleBody {
        val normProgress = normalizeProgress(progress)
        return if (mediaType == MediaType.MOVIE) {
            SimklScrobbleBody(
                movie = SimklMovieRef(ids = SimklIds(tmdb = tmdbId)),
                progress = normProgress
            )
        } else {
            val series = SimklShowRef(
                ids = SimklIds(tmdb = tmdbId),
                useTvdbAnimeSeasons = if (isAnime) true else null
            )
            SimklScrobbleBody(
                show = series,
                episode = if (season != null && episode != null) {
                    SimklEpisodeRef(season = season, number = episode)
                } else null,
                progress = normProgress
            )
        }
    }
}
