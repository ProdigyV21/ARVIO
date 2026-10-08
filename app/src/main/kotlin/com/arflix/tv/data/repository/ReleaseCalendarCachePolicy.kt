package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.api.TmdbSeasonDetails
import com.arflix.tv.data.api.TmdbTvDetails
import java.time.Instant
import java.time.ZoneOffset

internal const val CALENDAR_ACTIVE_TTL = 30 * 60_000L
internal const val CALENDAR_HISTORY_TTL = 24 * 60 * 60_000L

/** Unknown or future dates must stay on the short refresh interval. */
internal fun calendarMetadataTtl(value: Any, now: Long): Long {
    val cutoff = Instant.ofEpochMilli(now).atZone(ZoneOffset.UTC).toLocalDate().minusDays(30)
    fun historical(date: String?) = calendarDate(date)?.let { it < cutoff } == true
    val stable = when (value) {
        is TmdbMovieDetails -> historical(value.releaseDate) &&
            value.releaseDates?.results.orEmpty().flatMap { it.releaseDates }.all { historical(it.releaseDate?.take(10)) }
        is TmdbTvDetails -> value.status in setOf("Ended", "Canceled") && historical(value.lastEpisodeToAir?.airDate)
        is TmdbSeasonDetails -> value.episodes.isNotEmpty() && value.episodes.all { historical(it.airDate) }
        else -> false
    }
    return if (stable) CALENDAR_HISTORY_TTL else CALENDAR_ACTIVE_TTL
}
