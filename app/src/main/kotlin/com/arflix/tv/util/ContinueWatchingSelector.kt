package com.arflix.tv.util

data class EpisodePointer(val season: Int, val episode: Int)

data class EpisodeProgressSnapshot(
    val season: Int,
    val episode: Int,
    val completed: Boolean
)

data class WatchedEpisodeSnapshot(
    val season: Int,
    val episode: Int,
    val watchedAt: String?
)

data class InProgressSnapshot(
    val season: Int,
    val episode: Int,
    val progress: Float,
    val updatedAt: String?
)

object ContinueWatchingSelector {
    fun selectInProgressEpisode(
        inProgress: List<InProgressSnapshot>,
        watched: Set<EpisodePointer>,
        completionThreshold: Float
    ): EpisodePointer? {
        return inProgress
            .filter { it.progress > 0f && it.progress < completionThreshold }
            .filterNot { watched.contains(EpisodePointer(it.season, it.episode)) }
            .maxByOrNull { it.updatedAt ?: "" }
            ?.let { EpisodePointer(it.season, it.episode) }
    }

    fun selectNextEpisodeAfterLastWatched(
        episodes: List<EpisodeProgressSnapshot>,
        watched: Set<EpisodePointer>,
        lastWatched: WatchedEpisodeSnapshot?,
        includeSpecials: Boolean
    ): EpisodePointer? {
        val ordered = episodes
            .filter { includeSpecials || it.season != 0 }
            .sortedWith(compareBy({ it.season }, { it.episode }))

        if (ordered.isEmpty()) return null

        val lastIndex = lastWatched?.let { watchedEpisode ->
            ordered.indexOfFirst { it.season == watchedEpisode.season && it.episode == watchedEpisode.episode }
        } ?: -1

        val startIndex = if (lastIndex >= 0) lastIndex + 1 else 0

        for (i in startIndex until ordered.size) {
            val episode = ordered[i]
            val key = EpisodePointer(episode.season, episode.episode)
            if (!episode.completed && !watched.contains(key)) {
                return key
            }
        }

        return null
    }
}
