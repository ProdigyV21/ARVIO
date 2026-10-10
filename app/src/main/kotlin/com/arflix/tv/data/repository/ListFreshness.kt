package com.arflix.tv.data.repository

/**
 * Lists a user edits themselves (Trakt and MDBList lists) are served with long cache lifetimes:
 * Trakt allows a client to keep a list's items for a day, and MDBList's servers keep handing out
 * the same copy for about as long. Followed as-is, an edit took up to a day to reach a home row.
 *
 * Requests for those lists carry a marker that changes every [LIST_REFRESH_MS]. A new marker is a
 * new address, so neither the app's HTTP cache nor the service's own cache can answer it with an
 * older copy, while loads within one window still share a single cached response.
 */
internal const val LIST_REFRESH_MS = 30 * 60 * 1000L

internal fun listFreshnessMarker(nowMs: Long = System.currentTimeMillis()): Long = nowMs / LIST_REFRESH_MS

internal fun withListFreshness(url: String, nowMs: Long = System.currentTimeMillis()): String {
    val separator = if ('?' in url) '&' else '?'
    return "$url${separator}_=${listFreshnessMarker(nowMs)}"
}
