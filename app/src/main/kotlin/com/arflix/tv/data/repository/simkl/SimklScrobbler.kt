package com.arflix.tv.data.repository.simkl

import com.arflix.tv.data.api.SimklApi
import com.arflix.tv.data.api.SimklEpisodeRef
import com.arflix.tv.data.api.SimklIds
import com.arflix.tv.data.api.SimklMovieRef
import com.arflix.tv.data.api.SimklScrobbleBody
import com.arflix.tv.data.api.SimklSeasonRef
import com.arflix.tv.data.api.SimklShowRef
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimklScrobbler @Inject constructor(
    private val simklApi: SimklApi,
    private val authManager: SimklAuthManager
) {
    private val clientId: String get() = Constants.SIMKL_CLIENT_ID

    private fun normalizeProgress(progress: Float): Float {
        // If progress is in 0.0 - 1.0 range, scale to 0.0 - 100.0
        return if (progress in 0.0f..1.0f) progress * 100f else progress.coerceIn(0f, 100f)
    }

    suspend fun scrobbleStart(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ) {
        val token = authManager.getAccessToken() ?: return
        val authHeader = "Bearer $token"
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode)

        try {
            simklApi.scrobbleStart(authHeader, clientId, body)
        } catch (e: Exception) {
            AppLogger.e("SimklScrobbler", "Error scrobbling start for tmdbId=$tmdbId: ${e.message}")
        }
    }

    suspend fun scrobblePause(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ) {
        val token = authManager.getAccessToken() ?: return
        val authHeader = "Bearer $token"
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode)

        try {
            simklApi.scrobblePause(authHeader, clientId, body)
        } catch (e: Exception) {
            AppLogger.e("SimklScrobbler", "Error scrobbling pause for tmdbId=$tmdbId: ${e.message}")
        }
    }

    suspend fun scrobbleStop(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ) {
        val token = authManager.getAccessToken() ?: return
        val authHeader = "Bearer $token"
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode)

        try {
            simklApi.scrobbleStop(authHeader, clientId, body)
        } catch (e: Exception) {
            AppLogger.e("SimklScrobbler", "Error scrobbling stop for tmdbId=$tmdbId: ${e.message}")
        }
    }

    private fun buildScrobbleBody(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?
    ): SimklScrobbleBody {
        val normProgress = normalizeProgress(progress)
        return if (mediaType == MediaType.MOVIE) {
            SimklScrobbleBody(
                movie = SimklMovieRef(ids = SimklIds(tmdb = tmdbId)),
                progress = normProgress
            )
        } else {
            SimklScrobbleBody(
                show = SimklShowRef(
                    ids = SimklIds(tmdb = tmdbId),
                    seasons = if (season != null && episode != null) {
                        listOf(
                            SimklSeasonRef(
                                number = season,
                                episodes = listOf(SimklEpisodeRef(number = episode))
                            )
                        )
                    } else null
                ),
                episode = if (season != null && episode != null) {
                    SimklEpisodeRef(number = episode)
                } else null,
                progress = normProgress
            )
        }
    }
}
