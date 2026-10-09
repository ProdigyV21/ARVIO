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
            when {
                local == null -> remote
                // The tracker has moved this episode on well after anything was
                // saved here, which means another client watched it and wrote no
                // position — only a percentage. Taking the fields apart with
                // maxOf would pair that new percentage with the old position and
                // resume from the stale one, minutes behind what every other
                // client shows. Let the tracker's record stand whole instead.
                isLocalPositionStale(remote, local) -> mergeVisuals(
                    preferred = remote,
                    fallback = local
                )
                else -> mergeVisuals(
                    preferred = remote.copy(
                        resumePositionSeconds = maxOf(remote.resumePositionSeconds, local.resumePositionSeconds),
                        durationSeconds = maxOf(remote.durationSeconds, local.durationSeconds),
                        progress = maxOf(remote.progress, local.progress)
                    ),
                    fallback = local
                )
            }
        }

        // Trackers can omit IPTV VOD playback entirely. Keep these saved sessions,
        // but never replace a tracker's next-episode pointer with an older episode.
        val remoteShows = remoteItems.mapTo(hashSetOf()) { it.showKey() }
        val localVod = freshestLocal.values.groupBy { it.showKey() }.values
            .map { it.maxWithOrNull(localRecency)!! }
            .filter { item ->
                item.showKey() !in remoteShows &&
                    (IptvVodSourceIds.isIptvVodAddonId(item.streamAddonId) || !item.addonNativeId.isNullOrBlank()) &&
                    !SportsAddonCapabilities.isLiveStreamOrSportsItem(
                        mediaType = item.mediaType, id = item.id,
                        streamAddonId = item.streamAddonId, title = item.title,
                        isAddonNative = !item.addonNativeId.isNullOrBlank()
                    ) &&
                    item.progress < Constants.WATCHED_THRESHOLD &&
                    (item.durationSeconds <= 0L ||
                        item.resumePositionSeconds.toDouble() / item.durationSeconds < Constants.WATCHED_THRESHOLD / 100.0) &&
                    (item.progress >= Constants.MIN_PROGRESS_THRESHOLD || item.resumePositionSeconds >= 60L ||
                        (item.isUpNext && !item.addonNativeId.isNullOrBlank()))
            }
        return (mergedRemote + localVod).sortedByDescending { it.updatedAtMs }
    }

    /**
     * True when [local] holds a position the tracker has since overtaken, so it
     * describes an older session than [remote] and must not override it.
     *
     * Both timestamps have to be real for this to mean anything: an entry with
     * no timestamp sorts as epoch and would look stale against everything.
     */
    fun isLocalPositionStale(remote: ContinueWatchingItem, local: ContinueWatchingItem): Boolean =
        local.resumePositionSeconds > 0L &&
            local.updatedAtMs > 0L &&
            remote.updatedAtMs > 0L &&
            remote.updatedAtMs - local.updatedAtMs > Constants.TRACKER_OVERRIDES_LOCAL_POSITION_AFTER_MS

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
            addonNativeId = preferred.addonNativeId ?: fallback.addonNativeId,
            addonNativeAddonId = preferred.addonNativeAddonId ?: fallback.addonNativeAddonId,
            addonNativeType = preferred.addonNativeType ?: fallback.addonNativeType,
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
