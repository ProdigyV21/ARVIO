package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.api.TraktCalendarEpisode
import com.arflix.tv.data.model.CalendarRelease
import com.arflix.tv.data.model.CalendarReleaseKind
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.ReleaseCalendarSource
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId

internal fun calendarDate(value: String?): LocalDate? =
    value?.takeIf { it.length >= 10 }?.take(10)?.let { runCatching { LocalDate.parse(it) }.getOrNull() }

internal data class CalendarWatchlistTitle(val media: MediaItem, val sourceIds: Set<String>)

internal fun mergeCalendarWatchlists(watchlists: Map<ReleaseCalendarSource, List<MediaItem>>): List<CalendarWatchlistTitle> =
    watchlists.flatMap { (source, items) -> items.filter { it.id > 0 }.map { source to it } }
        .groupBy { (_, media) -> media.mediaType to media.id }
        .values.map { matches ->
            val base = matches.first().second
            CalendarWatchlistTitle(
                media = base.copy(
                    title = matches.firstOrNull { it.second.title.isNotBlank() }?.second?.title.orEmpty(),
                    image = matches.firstOrNull { it.second.image.isNotBlank() }?.second?.image.orEmpty(),
                    backdrop = matches.firstNotNullOfOrNull { it.second.backdrop?.takeIf(String::isNotBlank) },
                    traktId = matches.firstNotNullOfOrNull { it.second.traktId }
                ),
                sourceIds = matches.map { it.first.id }.toSet()
            )
        }

internal fun calendarMovieReleases(
    title: CalendarWatchlistTitle,
    details: TmdbMovieDetails,
    month: YearMonth,
    region: String
): List<CalendarRelease> {
    val regions = details.releaseDates?.results.orEmpty()
    val regional = regions.firstOrNull { it.iso31661.equals(region, true) && it.releaseDates.isNotEmpty() }
        ?: regions.firstOrNull { it.iso31661 == "US" && it.releaseDates.isNotEmpty() }
    val dated = regional?.releaseDates.orEmpty().mapNotNull { release ->
        val kind = when (release.type) {
            2, 3 -> CalendarReleaseKind.CINEMA
            4 -> CalendarReleaseKind.DIGITAL
            5 -> CalendarReleaseKind.PHYSICAL
            else -> return@mapNotNull null
        }
        val date = calendarDate(release.releaseDate) ?: return@mapNotNull null
        CalendarRelease(
            id = "movie:${title.media.id}:${kind.name}:$date",
            media = title.media,
            date = date,
            kind = kind,
            sourceIds = title.sourceIds,
            region = regional?.iso31661
        )
    }
    // A primary release is used only when no usable regional release metadata exists.
    val releases = dated.ifEmpty {
        listOfNotNull(calendarDate(details.releaseDate)?.let { date ->
            CalendarRelease(
                id = "movie:${title.media.id}:MOVIE:$date", media = title.media, date = date,
                kind = CalendarReleaseKind.MOVIE, sourceIds = title.sourceIds
            )
        })
    }
    return releases.filter { YearMonth.from(it.date) == month }.distinctBy { it.id }
}

internal fun calendarEpisodeReleases(
    title: CalendarWatchlistTitle,
    episodes: List<TmdbEpisode>,
    timedEpisodes: List<TraktCalendarEpisode>,
    month: YearMonth,
    timezone: ZoneId
): List<CalendarRelease> {
    val timed = timedEpisodes.associateBy { it.season to it.number }
    return episodes.mapNotNull { episode ->
        val exact = timed[episode.seasonNumber to episode.episodeNumber]?.firstAired
            ?.let { runCatching { Instant.parse(it) }.getOrNull() }
        val date = exact?.atZone(timezone)?.toLocalDate() ?: calendarDate(episode.airDate)
            ?: return@mapNotNull null
        if (YearMonth.from(date) != month) return@mapNotNull null
        CalendarRelease(
            id = "tv:${title.media.id}:${episode.seasonNumber}:${episode.episodeNumber}",
            media = title.media.copy(episodeStill = normalizeWatchlistArtworkUrl(episode.stillPath, true) ?: title.media.episodeStill),
            date = date, releaseInstant = exact, kind = CalendarReleaseKind.EPISODE,
            seasonNumber = episode.seasonNumber, episodeNumber = episode.episodeNumber,
            episodeTitle = episode.name.takeIf(String::isNotBlank), sourceIds = title.sourceIds
        )
    }.distinctBy { it.id }
}
