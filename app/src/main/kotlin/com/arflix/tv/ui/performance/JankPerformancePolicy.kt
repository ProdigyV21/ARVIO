package com.arflix.tv.ui.performance

internal fun shouldTrackJankStats(
    isDebug: Boolean,
    buildType: String
): Boolean {
    return isDebug || buildType == "benchmark" || buildType == "qa"
}

internal fun performanceScreenName(route: String?): String {
    val clean = route
        ?.substringBefore('?')
        ?.trim()
        ?.takeIf { it.isNotBlank() }
        ?: return "unknown"

    return when {
        clean == "home" -> "home"
        clean == "search" -> "search"
        clean == "discover" -> "discover"
        clean == "watchlist" -> "watchlist"
        clean == "settings" -> "settings"
        clean.startsWith("settings") -> "settings"
        clean.startsWith("collections/") -> "collections"
        clean.startsWith("collections/{") -> "collections"
        clean.startsWith("details/") -> "details"
        clean.startsWith("details/{") -> "details"
        clean.startsWith("player/") -> "player"
        clean.startsWith("player/{") -> "player"
        else -> clean.substringBefore('/')
    }
}
