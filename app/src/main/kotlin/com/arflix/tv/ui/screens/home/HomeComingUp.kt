package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.CalendarRelease
import com.arflix.tv.data.model.CalendarReleaseKind
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.calendarReleaseOrder
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal const val HOME_COMING_UP_ROW_ID = "home_coming_up"
internal const val COMING_UP_ON_HOME = "coming_up_on_home"

internal fun upcomingHomeItems(
    releases: List<CalendarRelease>,
    today: LocalDate,
    now: Instant,
    dateFormat: DateTimeFormatter
): List<MediaItem> = releases.asSequence()
    .filter { it.kind == CalendarReleaseKind.EPISODE && it.date >= today && it.date <= today.plusDays(14) }
    .filter { !it.media.isPlaceholder && it.media.title.isNotBlank() }
    .filter { it.releaseInstant?.isAfter(now) ?: (it.date > today) }
    .sortedWith(calendarReleaseOrder)
    .distinctBy { it.media.mediaType to it.media.id }
    .take(20)
    .map { release ->
        release.media.copy(
            releaseDate = release.date.format(dateFormat),
            progress = 0,
            showPlaybackProgress = false,
            timeRemainingMinutes = null,
            nextEpisode = null
        )
    }.toList()

internal fun withHomeComingUpRow(categories: List<Category>, items: List<MediaItem>): List<Category> {
    val base = if (categories.any { it.id == HOME_COMING_UP_ROW_ID }) {
        categories.filterNot { it.id == HOME_COMING_UP_ROW_ID }
    } else categories
    if (items.isEmpty()) return base
    val insertion = base.indexOfLast { it.id == "continue_watching" || it.id == HOME_WATCHLIST_ROW_ID } + 1
    return base.toMutableList().apply { add(insertion, Category(HOME_COMING_UP_ROW_ID, "Coming Up", items)) }
}
