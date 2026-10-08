package com.arflix.tv.data.model

/**
 * The source a title was last really watched from, so the source picker can pin it to the top.
 *
 * Only a source that actually played is recorded (see RecentSourcePlayTracker): one the user
 * opened and abandoned, or that failover replaced because it was slow or broken, never is.
 * There is one record per title — playing another source or episode replaces it.
 *
 * [season]/[episode] are TMDB numbers, null for movies.
 */
data class RecentPlayedSource(
    val mediaType: MediaType,
    val mediaId: Int,
    val season: Int? = null,
    val episode: Int? = null,
    val addonId: String = "",
    val source: String = "",
    val url: String? = null,
    val infoHash: String? = null,
    val fileIdx: Int? = null,
    val filename: String? = null,
    val playedAtMs: Long = 0L,
) {
    fun appliesTo(mediaType: MediaType, mediaId: Int, season: Int?, episode: Int?): Boolean =
        this.mediaType == mediaType &&
            this.mediaId == mediaId &&
            this.season == season &&
            this.episode == episode

    companion object {
        fun of(
            mediaType: MediaType,
            mediaId: Int,
            season: Int?,
            episode: Int?,
            stream: StreamSource,
            playedAtMs: Long,
        ) = RecentPlayedSource(
            mediaType = mediaType,
            mediaId = mediaId,
            season = season,
            episode = episode,
            addonId = stream.addonId.trim(),
            source = stream.source.trim(),
            url = stream.url?.trim()?.takeIf { it.isNotBlank() },
            infoHash = stream.infoHash?.trim()?.lowercase()?.takeIf { it.isNotBlank() },
            fileIdx = stream.fileIdx,
            filename = stream.behaviorHints?.filename?.trim()?.takeIf { it.isNotBlank() },
            playedAtMs = playedAtMs,
        )
    }
}

/**
 * The stream in [streams] that is [recent]'s source, or null. Addon results are fetched fresh
 * each time, so this matches on identity rather than object equality, strongest field first:
 * URL, then torrent hash + file, then filename, then the addon's source text. Two addons can
 * list the same file, and the user picked one of them, so the addon must match too.
 */
fun findRecentSourceMatch(streams: List<StreamSource>, recent: RecentPlayedSource?): StreamSource? {
    recent ?: return null
    var best: StreamSource? = null
    var bestScore = 0
    for (stream in streams) {
        val score = recentSourceMatchScore(stream, recent)
        if (score > bestScore) {
            best = stream
            bestScore = score
        }
    }
    return best
}

private fun recentSourceMatchScore(stream: StreamSource, recent: RecentPlayedSource): Int {
    if (stream.addonId.trim() != recent.addonId) return 0
    val url = stream.url?.trim()
    if (!url.isNullOrBlank() && url == recent.url) return 4
    val infoHash = stream.infoHash?.trim()?.lowercase()
    if (!infoHash.isNullOrBlank() && infoHash == recent.infoHash && stream.fileIdx == recent.fileIdx) return 3
    val filename = stream.behaviorHints?.filename?.trim()
    if (!filename.isNullOrBlank() && filename.equals(recent.filename, ignoreCase = true)) return 2
    val source = stream.source.trim()
    if (source.isNotBlank() && source == recent.source) return 1
    return 0
}
