package com.arflix.tv.data.repository.sync

enum class TrackingFeature {
    WATCHLIST,
    CONTINUE_WATCHING,
    WATCHED
}

enum class TrackingReadMode {
    AUTO,
    TRAKT,
    SIMKL,
    BOTH,
    MDBLIST,
    WETRAKR;

    companion object {
        fun fromStorage(value: String?): TrackingReadMode = entries.firstOrNull {
            it.name.equals(value, ignoreCase = true)
        } ?: AUTO
    }

    fun toStorage(): String = name.lowercase()
}

data class TrackingPreferences(
    val watchlistReadMode: TrackingReadMode = TrackingReadMode.AUTO,
    val continueWatchingReadMode: TrackingReadMode = TrackingReadMode.AUTO,
    val watchedReadMode: TrackingReadMode = TrackingReadMode.AUTO,
    val writeToTrakt: Boolean? = null,
    val writeToSimkl: Boolean? = null,
    val writeToWeTrakr: Boolean? = null
)
