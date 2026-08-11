package com.arflix.tv.data.repository.simkl

import com.arflix.tv.data.api.SimklApi
import com.arflix.tv.data.api.SimklEpisodeRef
import com.arflix.tv.data.api.SimklIds
import com.arflix.tv.data.api.SimklMovieRef
import com.arflix.tv.data.api.SimklSeasonRef
import com.arflix.tv.data.api.SimklShowRef
import com.arflix.tv.data.api.SimklSyncHistoryBody
import com.arflix.tv.data.api.SimklSyncWatchlistBody
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimklSyncService @Inject constructor(
    private val simklApi: SimklApi,
    private val authManager: SimklAuthManager
) {
    private val clientId: String get() = Constants.SIMKL_CLIENT_ID

    suspend fun getWatchedMovies(): Set<Int> {
        val token = authManager.getAccessToken() ?: return emptySet()
        val authHeader = "Bearer $token"
        return try {
            val response = simklApi.getAllItems(authHeader, clientId, "movies")
            response.movies
                ?.filter { it.status == "completed" || it.status == "watching" }
                ?.mapNotNull { it.movie?.ids?.tmdb }
                ?.toSet() ?: emptySet()
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error fetching watched movies: ${e.message}")
            emptySet()
        }
    }

    suspend fun getWatchedEpisodes(): Set<String> {
        val token = authManager.getAccessToken() ?: return emptySet()
        val authHeader = "Bearer $token"
        return try {
            val response = simklApi.getAllItems(authHeader, clientId, "shows")
            val watched = mutableSetOf<String>()
            response.shows?.forEach { showItem ->
                val showTmdb = showItem.show?.ids?.tmdb ?: return@forEach
                showItem.seasons?.forEach { season ->
                    season.episodes.forEach { episode ->
                        watched.add("${showTmdb}_S${season.number}_E${episode.number}")
                    }
                }
            }
            watched
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error fetching watched episodes: ${e.message}")
            emptySet()
        }
    }

    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"
        val body = if (mediaType == MediaType.MOVIE) {
            SimklSyncWatchlistBody(movies = listOf(SimklMovieRef(ids = SimklIds(tmdb = tmdbId))))
        } else {
            SimklSyncWatchlistBody(shows = listOf(SimklShowRef(ids = SimklIds(tmdb = tmdbId))))
        }
        return try {
            val res = simklApi.addToWatchlist(authHeader, clientId, body)
            res.isSuccessful
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error adding to watchlist: ${e.message}")
            false
        }
    }

    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"
        val body = if (mediaType == MediaType.MOVIE) {
            SimklSyncWatchlistBody(movies = listOf(SimklMovieRef(ids = SimklIds(tmdb = tmdbId))))
        } else {
            SimklSyncWatchlistBody(shows = listOf(SimklShowRef(ids = SimklIds(tmdb = tmdbId))))
        }
        return try {
            val res = simklApi.removeFromWatchlist(authHeader, clientId, body)
            res.isSuccessful
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error removing from watchlist: ${e.message}")
            false
        }
    }

    suspend fun markWatched(mediaType: MediaType, tmdbId: Int, season: Int? = null, episode: Int? = null): Boolean {
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"
        val body = if (mediaType == MediaType.MOVIE) {
            SimklSyncHistoryBody(movies = listOf(SimklMovieRef(ids = SimklIds(tmdb = tmdbId))))
        } else {
            SimklSyncHistoryBody(
                shows = listOf(
                    SimklShowRef(
                        ids = SimklIds(tmdb = tmdbId),
                        seasons = if (season != null && episode != null) {
                            listOf(SimklSeasonRef(number = season, episodes = listOf(SimklEpisodeRef(number = episode))))
                        } else null
                    )
                )
            )
        }
        return try {
            val res = simklApi.addToHistory(authHeader, clientId, body, allowRewatch = "yes")
            res.isSuccessful
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error marking watched: ${e.message}")
            false
        }
    }
}
