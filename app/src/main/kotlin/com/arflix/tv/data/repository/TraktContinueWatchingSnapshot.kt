package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TraktWatchedShow
import kotlinx.coroutines.CancellationException

internal fun traktWatchedEpisodeKeys(shows: List<TraktWatchedShow>): Set<String> = buildSet {
    for (watched in shows) {
        val id = watched.show.ids.tmdb ?: continue
        for (season in watched.seasons.orEmpty()) {
            for (episode in season.episodes) {
                if (episode.plays > 0) add("show_tmdb:$id:${season.number}:${episode.number}")
            }
        }
    }
}

// Keep a failed sibling request from cancelling successful reads, but never swallow cancellation.
internal suspend fun <T> traktSnapshotRead(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Exception) {
    Result.failure(e)
}
