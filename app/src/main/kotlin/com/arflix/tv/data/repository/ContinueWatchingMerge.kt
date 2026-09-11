package com.arflix.tv.data.repository

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.arflix.tv.util.Constants
import java.time.Instant

/** Combines tracker results with profile-scoped resume data. */
internal object ContinueWatchingMerge {
    private fun ContinueWatchingItem.showKey() = "${mediaType}:${id}"

    private fun ContinueWatchingItem.exactKey() =
        "${mediaType}:${id}:${season ?: -1}:${episode ?: -1}"

    private val localRecency = compareBy<ContinueWatchingItem> { it.updatedAtMs }
        .thenBy { it.resumePositionSeconds }
        .thenBy { it.progress }

    fun merge(
        remoteItems: List<ContinueWatchingItem>,
        localItems: List<ContinueWatchingItem>,
        historyItems: List<ContinueWatchingItem> = emptyList()
    ): List<ContinueWatchingItem> {
        val freshestLocal = (localItems + historyItems)
            .groupBy { it.exactKey() }
            .mapValues { (_, candidates) ->
                candidates.sortedWith(localRecency.reversed()).reduce(::mergeVisuals)
            }

        val mergedRemote = remoteItems.map { remote ->
            val local = freshestLocal[remote.exactKey()]
            if (local == null) remote else mergeVisuals(
                preferred = remote.copy(
                    resumePositionSeconds = maxOf(remote.resumePositionSeconds, local.resumePositionSeconds),
                    durationSeconds = maxOf(remote.durationSeconds, local.durationSeconds),
                    progress = maxOf(remote.progress, local.progress)
                ),
                fallback = local
            )
        }

        // Trackers can omit IPTV VOD playback entirely. Keep these saved sessions,
        // but never replace a tracker's next-episode pointer with an older episode.
        val remoteShows = remoteItems.mapTo(hashSetOf()) { it.showKey() }
        val localVod = freshestLocal.values.groupBy { it.showKey() }.values
            .map { it.maxWithOrNull(localRecency)!! }
            .filter { item ->
                item.showKey() !in remoteShows &&
                    IptvVodSourceIds.isIptvVodAddonId(item.streamAddonId) &&
                    !SportsAddonCapabilities.isLiveStreamOrSportsItem(
                        mediaType = item.mediaType, id = item.id,
                        streamAddonId = item.streamAddonId, title = item.title
                    ) &&
                    item.progress < Constants.WATCHED_THRESHOLD &&
                    (item.durationSeconds <= 0L ||
                        item.resumePositionSeconds.toDouble() / item.durationSeconds < Constants.WATCHED_THRESHOLD / 100.0) &&
                    (item.progress >= Constants.MIN_PROGRESS_THRESHOLD || item.resumePositionSeconds >= 60L)
            }
        return (mergedRemote + localVod).sortedByDescending { it.updatedAtMs }
    }

    fun fromHistory(entry: WatchHistoryEntry): ContinueWatchingItem {
        val storedPct = (entry.progress * 100f).toInt()
        val progress = when {
            storedPct > 0 -> storedPct
            entry.duration_seconds > 0 && entry.position_seconds > 0 ->
                (entry.position_seconds.toDouble() / entry.duration_seconds * 100).toInt()
            entry.position_seconds > 0 -> 1
            else -> 0
        }
        fun timestamp(value: String?) = value?.let {
            runCatching { Instant.parse(it).toEpochMilli() }.getOrDefault(0L)
        } ?: 0L
        return ContinueWatchingItem(
            id = entry.show_tmdb_id,
            title = entry.title?.trim()?.takeIf { it.isNotEmpty() }
                ?: entry.episode_title?.trim()?.takeIf { it.isNotEmpty() } ?: "Untitled",
            mediaType = if (entry.media_type == "tv") MediaType.TV else MediaType.MOVIE,
            progress = progress.coerceIn(0, 100),
            resumePositionSeconds = entry.position_seconds.coerceAtLeast(0L),
            durationSeconds = entry.duration_seconds.coerceAtLeast(0L),
            season = entry.season,
            episode = entry.episode,
            episodeTitle = entry.episode_title,
            backdropPath = entry.backdrop_path,
            posterPath = entry.poster_path,
            streamKey = entry.stream_key,
            streamAddonId = entry.stream_addon_id,
            streamTitle = entry.stream_title,
            updatedAtMs = maxOf(timestamp(entry.updated_at), timestamp(entry.paused_at))
        )
    }

    fun mergeVisuals(
        preferred: ContinueWatchingItem,
        fallback: ContinueWatchingItem
    ): ContinueWatchingItem {
        val sameEpisode = preferred.season == fallback.season && preferred.episode == fallback.episode
        return preferred.copy(
            title = preferred.title.ifBlank { fallback.title },
            episodeTitle = preferred.episodeTitle ?: fallback.episodeTitle,
            backdropPath = preferred.backdropPath ?: fallback.backdropPath,
            episodeStillPath = preferred.episodeStillPath ?: fallback.episodeStillPath.takeIf { sameEpisode },
            posterPath = preferred.posterPath ?: fallback.posterPath,
            streamKey = preferred.streamKey ?: fallback.streamKey,
            streamAddonId = preferred.streamAddonId ?: fallback.streamAddonId,
            streamTitle = preferred.streamTitle ?: fallback.streamTitle,
            year = preferred.year.ifBlank { fallback.year },
            releaseDate = preferred.releaseDate.ifBlank { fallback.releaseDate },
            overview = preferred.overview.ifBlank { fallback.overview },
            imdbRating = preferred.imdbRating.ifBlank { fallback.imdbRating },
            duration = preferred.duration.ifBlank { fallback.duration },
            durationSeconds = maxOf(preferred.durationSeconds, fallback.durationSeconds),
            budget = preferred.budget ?: fallback.budget,
            totalEpisodes = if (preferred.totalEpisodes > 0) preferred.totalEpisodes else fallback.totalEpisodes,
            watchedEpisodes = if (preferred.watchedEpisodes > 0) preferred.watchedEpisodes else fallback.watchedEpisodes,
            updatedAtMs = maxOf(preferred.updatedAtMs, fallback.updatedAtMs)
        )
    }
}
