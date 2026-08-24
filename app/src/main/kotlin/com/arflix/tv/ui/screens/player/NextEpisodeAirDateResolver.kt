package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.api.TmdbSeasonDetails
import com.arflix.tv.data.model.EpisodeIdentity
import kotlinx.coroutines.CancellationException
import java.time.Clock
import java.time.LocalDate
import java.time.format.DateTimeParseException

internal enum class NextEpisodeAirDateBlockReason {
    FutureAirDate,
    MissingEpisode,
    MissingAirDate,
    MalformedAirDate,
    MetadataUnavailable,
}

internal sealed interface NextEpisodeAirDateResolution {
    data object Pending : NextEpisodeAirDateResolution
    data object Allowed : NextEpisodeAirDateResolution
    data class Blocked(
        val reason: NextEpisodeAirDateBlockReason,
    ) : NextEpisodeAirDateResolution
}

/**
 * Resolves whether an autoplay target has aired according to TMDB metadata.
 *
 * The caller owns the [Pending] state while this suspend operation is running. Failures are
 * fail-closed so metadata problems can never autoplay an unavailable episode.
 */
internal class NextEpisodeAirDateResolver(
    private val loadSeason: suspend (tmdbId: Int, seasonNumber: Int) -> TmdbSeasonDetails,
    private val clock: Clock,
) {
    suspend fun resolve(
        tmdbId: Int,
        target: EpisodeIdentity,
    ): NextEpisodeAirDateResolution {
        val season = try {
            loadSeason(tmdbId, target.tmdbSeason)
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (_: Exception) {
            return NextEpisodeAirDateResolution.Blocked(
                NextEpisodeAirDateBlockReason.MetadataUnavailable,
            )
        }

        val episode = season.episodes.firstOrNull {
            it.seasonNumber == target.tmdbSeason && it.episodeNumber == target.tmdbEpisode
        } ?: return NextEpisodeAirDateResolution.Blocked(
            NextEpisodeAirDateBlockReason.MissingEpisode,
        )

        val rawAirDate = episode.airDate?.trim().orEmpty()
        if (rawAirDate.isEmpty()) {
            return NextEpisodeAirDateResolution.Blocked(
                NextEpisodeAirDateBlockReason.MissingAirDate,
            )
        }

        val airDate = try {
            LocalDate.parse(rawAirDate)
        } catch (_: DateTimeParseException) {
            return NextEpisodeAirDateResolution.Blocked(
                NextEpisodeAirDateBlockReason.MalformedAirDate,
            )
        }

        return if (airDate.isAfter(LocalDate.now(clock))) {
            NextEpisodeAirDateResolution.Blocked(
                NextEpisodeAirDateBlockReason.FutureAirDate,
            )
        } else {
            NextEpisodeAirDateResolution.Allowed
        }
    }
}
