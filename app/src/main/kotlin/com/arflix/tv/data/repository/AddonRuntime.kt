package com.arflix.tv.data.repository

import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.RuntimeKind
import com.arflix.tv.data.model.StreamSource

data class MovieRuntimeRequest(
    val imdbId: String,
    val title: String,
    val year: Int?,
    /** A native addon item's own type ("tv"); streams are requested with it instead of "movie". */
    val nativeType: String? = null
)

data class EpisodeRuntimeRequest(
    val imdbId: String,
    val season: Int,
    val episode: Int,
    val tmdbId: Int?,
    val tvdbId: Int?,
    val genreIds: List<Int>,
    val originalLanguage: String?,
    val title: String,
    val airDate: String?,
    val animeQueryOverride: String? = null,
    /** A native addon item's own type ("Podcasts"); strict addons route /meta and /stream by it. */
    val nativeType: String? = null
)

interface AddonRuntime {
    val kind: RuntimeKind

    suspend fun resolveMovieStreams(
        addons: List<Addon>,
        request: MovieRuntimeRequest
    ): List<StreamSource>

    suspend fun resolveEpisodeStreams(
        addons: List<Addon>,
        request: EpisodeRuntimeRequest
    ): List<StreamSource>
}
