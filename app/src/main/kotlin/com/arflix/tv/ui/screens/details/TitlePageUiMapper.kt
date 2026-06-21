package com.arflix.tv.ui.screens.details

import androidx.compose.runtime.Immutable
import com.arflix.tv.data.api.TmdbCreditsResponse
import com.arflix.tv.data.api.TmdbMediaItem
import com.arflix.tv.data.api.TmdbPersonDetails
import com.arflix.tv.data.api.TmdbVideo
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.Constants
import java.util.Locale

enum class TitlePageMode {
    Movie,
    Series
}

@Immutable
data class TitleExtraUi(
    val id: String,
    val name: String,
    val type: String,
    val youtubeKey: String,
    val official: Boolean,
    val size: Int,
    val thumbnailUrl: String
)

@Immutable
data class TitleExtrasState(
    val items: List<TitleExtraUi> = emptyList(),
    val isLoading: Boolean = false,
    val hasAttemptedLoad: Boolean = false
)

@Immutable
data class SeriesPlaybackUi(
    val nextEpisode: Episode? = null,
    val seasonProgress: Map<Int, Pair<Int, Int>> = emptyMap(),
    val selectedSeason: Int = 1
)

@Immutable
data class TitleKnownForUi(
    val id: Int,
    val mediaType: MediaType,
    val title: String,
    val posterUrl: String?,
    val year: String
)

@Immutable
data class TitlePersonUi(
    val id: Int,
    val name: String,
    val role: String,
    val profileUrl: String?,
    val knownFor: List<TitleKnownForUi>
)

@Immutable
data class TitleCreditSummaryUi(
    val directors: List<String> = emptyList(),
    val writers: List<String> = emptyList(),
    val creators: List<String> = emptyList()
) {
    val hasContent: Boolean
        get() = directors.isNotEmpty() || writers.isNotEmpty() || creators.isNotEmpty()
}

private data class PersonSeed(
    val id: Int,
    val name: String,
    val role: String,
    val profilePath: String?
)

private val allowedExtraTypes = setOf(
    "behind the scenes",
    "featurette",
    "clip",
    "bloopers",
    "trailer",
    "teaser"
)

internal fun buildTitleExtras(
    videos: List<TmdbVideo>,
    limit: Int = 12
): List<TitleExtraUi> {
    return videos
        .asSequence()
        .filter { it.site.equals("YouTube", ignoreCase = true) }
        .filter { it.key.isNotBlank() }
        .filter { it.type.lowercase(Locale.US) in allowedExtraTypes }
        .distinctBy { it.key }
        .sortedWith(
            compareBy<TmdbVideo> { extraTypePriority(it.type) }
                .thenByDescending { it.official }
                .thenByDescending { it.size }
                .thenByDescending { it.publishedAt.orEmpty() }
        )
        .take(limit)
        .map { video ->
            TitleExtraUi(
                id = video.id.ifBlank { video.key },
                name = video.name.ifBlank { video.type.ifBlank { "Video" } },
                type = normalizeExtraType(video.type),
                youtubeKey = video.key,
                official = video.official,
                size = video.size,
                thumbnailUrl = "https://img.youtube.com/vi/${video.key}/hqdefault.jpg"
            )
        }
        .toList()
}

internal fun buildTitleExtrasState(
    videos: List<TmdbVideo>,
    fallbackTrailerKey: String?,
    isLoading: Boolean = false,
    hasAttemptedLoad: Boolean = true
): TitleExtrasState {
    val extras = buildTitleExtras(videos)
    val items = if (extras.isNotEmpty() || fallbackTrailerKey.isNullOrBlank()) {
        extras
    } else {
        listOf(syntheticTrailerExtra(fallbackTrailerKey))
    }
    return TitleExtrasState(
        items = items,
        isLoading = isLoading,
        hasAttemptedLoad = hasAttemptedLoad
    )
}

internal fun buildSeriesPlaybackUi(
    episodes: List<Episode>,
    currentSeason: Int,
    seasonProgress: Map<Int, Pair<Int, Int>>,
    playSeason: Int?,
    playEpisode: Int?
): SeriesPlaybackUi {
    val explicitTarget = episodes.firstOrNull { episode ->
        episode.seasonNumber == playSeason && episode.episodeNumber == playEpisode
    }
    val firstUnwatched = episodes.firstOrNull { !it.isWatched }
    return SeriesPlaybackUi(
        nextEpisode = explicitTarget ?: firstUnwatched ?: episodes.firstOrNull(),
        seasonProgress = seasonProgress,
        selectedSeason = currentSeason.coerceAtLeast(1)
    )
}

internal fun buildTitlePeople(
    credits: TmdbCreditsResponse,
    personDetailsById: Map<Int, TmdbPersonDetails>,
    currentMediaType: MediaType,
    currentMediaId: Int,
    limit: Int = 10,
    knownForLimit: Int = 10
): List<TitlePersonUi> {
    val directors = credits.crew
        .asSequence()
        .filter { it.id > 0 && it.name.isNotBlank() }
        .filter { crew ->
            val job = crew.job.lowercase(Locale.US)
            job == "director" || job == "creator" || job == "showrunner"
        }
        .map { PersonSeed(it.id, it.name, it.job.ifBlank { "Crew" }, it.profilePath) }

    val cast = credits.cast
        .asSequence()
        .filter { it.id > 0 && it.name.isNotBlank() }
        .sortedBy { it.order }
        .map { PersonSeed(it.id, it.name, it.character?.takeIf(String::isNotBlank) ?: "Cast", it.profilePath) }

    return (directors + cast)
        .distinctBy { it.id }
        .take(limit)
        .map { seed ->
            val person = personDetailsById[seed.id]
            TitlePersonUi(
                id = seed.id,
                name = seed.name,
                role = seed.role,
                profileUrl = imageUrl(seed.profilePath),
                knownFor = buildKnownFor(
                    person = person,
                    currentMediaType = currentMediaType,
                    currentMediaId = currentMediaId,
                    limit = knownForLimit
                )
            )
        }
        .toList()
}

internal fun buildTitleCreditSummary(
    credits: TmdbCreditsResponse,
    limitPerRole: Int = 3
): TitleCreditSummaryUi {
    val crew = credits.crew
        .asSequence()
        .filter { it.name.isNotBlank() }
        .toList()

    fun namesFor(vararg jobs: String): List<String> {
        val normalizedJobs = jobs.map { it.lowercase(Locale.US) }.toSet()
        return crew
            .asSequence()
            .filter { it.job.lowercase(Locale.US) in normalizedJobs }
            .map { it.name }
            .distinct()
            .take(limitPerRole)
            .toList()
    }

    val directors = namesFor("Director")
    val creators = namesFor("Creator", "Showrunner")
    val writers = namesFor(
        "Writer",
        "Screenplay",
        "Story",
        "Characters",
        "Novel",
        "Teleplay"
    )

    return TitleCreditSummaryUi(
        directors = directors,
        writers = writers,
        creators = creators
    )
}

private fun buildKnownFor(
    person: TmdbPersonDetails?,
    currentMediaType: MediaType,
    currentMediaId: Int,
    limit: Int
): List<TitleKnownForUi> {
    val combined = person?.combinedCredits ?: return emptyList()
    return (combined.cast.asSequence() + combined.crew.asSequence())
        .filter { it.id > 0 }
        .filter { it.posterPath != null }
        .filter { !it.adult }
        .filter { it.resolveMediaTypeOrNull() != null }
        .filterNot { it.id == currentMediaId && it.resolveMediaTypeOrNull() == currentMediaType }
        .distinctBy { "${it.resolveMediaTypeOrNull()?.name}:${it.id}" }
        .sortedWith(
            compareByDescending<TmdbMediaItem> { it.voteCount }
                .thenByDescending { it.popularity }
        )
        .take(limit)
        .mapNotNull { item ->
            val type = item.resolveMediaTypeOrNull() ?: return@mapNotNull null
            TitleKnownForUi(
                id = item.id,
                mediaType = type,
                title = item.title ?: item.name ?: item.originalTitle ?: item.originalName ?: return@mapNotNull null,
                posterUrl = imageUrl(item.posterPath),
                year = (item.releaseDate ?: item.firstAirDate).orEmpty().take(4)
            )
        }
        .toList()
}

private fun syntheticTrailerExtra(youtubeKey: String): TitleExtraUi {
    return TitleExtraUi(
        id = "fallback_trailer_$youtubeKey",
        name = "Official Trailer",
        type = "Trailer",
        youtubeKey = youtubeKey,
        official = true,
        size = 0,
        thumbnailUrl = "https://img.youtube.com/vi/$youtubeKey/hqdefault.jpg"
    )
}

private fun extraTypePriority(type: String): Int = when (type.lowercase(Locale.US)) {
    "behind the scenes" -> 0
    "featurette" -> 1
    "clip" -> 2
    "bloopers" -> 3
    "trailer" -> 4
    "teaser" -> 5
    else -> 10
}

private fun normalizeExtraType(type: String): String = when (type.lowercase(Locale.US)) {
    "behind the scenes" -> "Behind the Scenes"
    "featurette" -> "Featurette"
    "clip" -> "Clip"
    "bloopers" -> "Bloopers"
    "trailer" -> "Trailer"
    "teaser" -> "Teaser"
    else -> type.ifBlank { "Video" }
}

private fun TmdbMediaItem.resolveMediaTypeOrNull(): MediaType? = when (mediaType?.lowercase(Locale.US)) {
    "movie" -> MediaType.MOVIE
    "tv" -> MediaType.TV
    else -> null
}

private fun imageUrl(path: String?): String? {
    val value = path?.takeIf { it.isNotBlank() } ?: return null
    return if (value.startsWith("http", ignoreCase = true)) value else "${Constants.IMAGE_BASE}$value"
}
