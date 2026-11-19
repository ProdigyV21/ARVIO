package com.arflix.tv.data.repository

import java.time.LocalDate
import java.util.Locale

/**
 * Newest-first ordering for streaming-service (watch-provider) queries.
 *
 * TMDB sorts movies by `primary_release_date` and series by `first_air_date`, the dates the
 * cards print. Sorted that way, a service's list starts with titles dated in the future and with
 * placeholder entries nobody has rated yet. So a newest-first query stops at today and asks for
 * a few votes, which keeps this week's real releases at the top.
 */
internal object WatchProviderSort {
    const val MOVIES_NEWEST = "primary_release_date.desc"
    const val SERIES_NEWEST = "first_air_date.desc"
    const val NEWEST_MIN_VOTES = 10

    /** The newest-first sort for a source's media type ("movie", or "series"/"tv"/"show"). */
    fun newestFor(mediaType: String?): String =
        if (isSeries(mediaType)) SERIES_NEWEST else MOVIES_NEWEST

    fun isSeries(mediaType: String?): Boolean =
        mediaType?.lowercase(Locale.US) in setOf("series", "tv", "show")

    /** Extra discover limits for [sortBy]: none unless it is a newest-first sort. */
    data class Window(val releasedOnOrBefore: String?, val minVoteCount: Int?)

    fun window(sortBy: String, today: LocalDate): Window =
        if (sortBy == MOVIES_NEWEST || sortBy == SERIES_NEWEST) {
            Window(releasedOnOrBefore = today.toString(), minVoteCount = NEWEST_MIN_VOTES)
        } else {
            Window(releasedOnOrBefore = null, minVoteCount = null)
        }
}
