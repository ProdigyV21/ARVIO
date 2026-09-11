package com.arflix.tv.ui.screens.tv.live

private val terminalPlaybackHttpCodes = setOf(401, 403, 429, 444, 451, 513)

internal fun isMissingPlaybackResource(httpCode: Int?): Boolean = httpCode == 404 || httpCode == 410

internal fun shouldReusePreparedLiveHls(
    preparedIsHls: Boolean,
    isCatchup: Boolean,
    unsupportedContainer: Boolean,
    httpCode: Int?,
): Boolean = preparedIsHls && !isCatchup && !unsupportedContainer && !isMissingPlaybackResource(httpCode)

internal fun shouldRetryLiveTvPlayback(
    httpCode: Int?,
    nextAttempt: Int,
    maxRetryCount: Int,
    isCatchup: Boolean,
): Boolean {
    if (nextAttempt > maxRetryCount || httpCode in terminalPlaybackHttpCodes) return false
    // Any live source can redirect to an expired URL. Refresh once before giving up;
    // catch-up retains its existing bounded budget for different archive candidates.
    return isCatchup || !isMissingPlaybackResource(httpCode) || nextAttempt == 1
}
