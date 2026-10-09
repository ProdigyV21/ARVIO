package com.arflix.tv.data.model

import java.time.Instant
import java.time.LocalDate

enum class ReleaseCalendarSource(val id: String, val label: String) {
    ALL("all", "All watchlists"),
    ARVIO("arvio", "ARVIO watchlist"),
    TRAKT("trakt", "Trakt"),
    SIMKL("simkl", "SIMKL"),
    MDBLIST("mdblist", "MDBList")
}

enum class CalendarReleaseKind(val label: String) {
    EPISODE("Episode"), CINEMA("Cinema"), DIGITAL("Digital"), PHYSICAL("Physical"), MOVIE("Movie")
}

/** Date-only metadata never becomes a synthetic midnight timestamp. */
data class CalendarRelease(
    val id: String,
    val media: MediaItem,
    val date: LocalDate,
    val releaseInstant: Instant? = null,
    val kind: CalendarReleaseKind,
    val seasonNumber: Int? = null,
    val episodeNumber: Int? = null,
    val episodeTitle: String? = null,
    val sourceIds: Set<String> = emptySet(),
    val logoUrl: String? = null,
    val region: String? = null
)
