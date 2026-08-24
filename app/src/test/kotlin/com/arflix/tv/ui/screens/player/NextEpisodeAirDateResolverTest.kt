package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbSeasonDetails
import com.arflix.tv.data.model.EpisodeIdentity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class NextEpisodeAirDateResolverTest {
    private val clock = Clock.fixed(
        Instant.parse("2026-08-24T12:00:00Z"),
        ZoneOffset.UTC,
    )

    @Test
    fun `past air date allows autoplay`() = runTest {
        val resolver = resolverWithAirDate("2026-08-23")

        assertThat(resolver.resolve(TMDB_ID, identity(season = 1, episode = 2)))
            .isEqualTo(NextEpisodeAirDateResolution.Allowed)
    }

    @Test
    fun `today air date allows autoplay`() = runTest {
        val resolver = resolverWithAirDate("2026-08-24")

        assertThat(resolver.resolve(TMDB_ID, identity(season = 1, episode = 2)))
            .isEqualTo(NextEpisodeAirDateResolution.Allowed)
    }

    @Test
    fun `future air date blocks autoplay`() = runTest {
        val resolver = resolverWithAirDate("2026-08-25")

        assertThat(resolver.resolve(TMDB_ID, identity(season = 1, episode = 2)))
            .isEqualTo(
                NextEpisodeAirDateResolution.Blocked(
                    NextEpisodeAirDateBlockReason.FutureAirDate,
                )
            )
    }

    @Test
    fun `missing episode metadata blocks autoplay`() = runTest {
        val resolver = NextEpisodeAirDateResolver(
            loadSeason = { _, season -> TmdbSeasonDetails(seasonNumber = season) },
            clock = clock,
        )

        assertThat(resolver.resolve(TMDB_ID, identity(season = 1, episode = 2)))
            .isEqualTo(
                NextEpisodeAirDateResolution.Blocked(
                    NextEpisodeAirDateBlockReason.MissingEpisode,
                )
            )
    }

    @Test
    fun `missing air date blocks autoplay`() = runTest {
        val resolver = resolverWithAirDate(null)

        assertThat(resolver.resolve(TMDB_ID, identity(season = 1, episode = 2)))
            .isEqualTo(
                NextEpisodeAirDateResolution.Blocked(
                    NextEpisodeAirDateBlockReason.MissingAirDate,
                )
            )
    }

    @Test
    fun `blank air date blocks autoplay`() = runTest {
        val resolver = resolverWithAirDate("   ")

        assertThat(resolver.resolve(TMDB_ID, identity(season = 1, episode = 2)))
            .isEqualTo(
                NextEpisodeAirDateResolution.Blocked(
                    NextEpisodeAirDateBlockReason.MissingAirDate,
                )
            )
    }

    @Test
    fun `malformed air date blocks autoplay`() = runTest {
        val resolver = resolverWithAirDate("24-08-2026")

        assertThat(resolver.resolve(TMDB_ID, identity(season = 1, episode = 2)))
            .isEqualTo(
                NextEpisodeAirDateResolution.Blocked(
                    NextEpisodeAirDateBlockReason.MalformedAirDate,
                )
            )
    }

    @Test
    fun `API failure blocks autoplay`() = runTest {
        val resolver = NextEpisodeAirDateResolver(
            loadSeason = { _, _ -> error("TMDB unavailable") },
            clock = clock,
        )

        assertThat(resolver.resolve(TMDB_ID, identity(season = 1, episode = 2)))
            .isEqualTo(
                NextEpisodeAirDateResolution.Blocked(
                    NextEpisodeAirDateBlockReason.MetadataUnavailable,
                )
            )
    }

    @Test(expected = CancellationException::class)
    fun `cancellation is preserved`() = runTest {
        val resolver = NextEpisodeAirDateResolver(
            loadSeason = { _, _ -> throw CancellationException("superseded episode") },
            clock = clock,
        )

        resolver.resolve(TMDB_ID, identity(season = 1, episode = 2))
    }

    @Test
    fun `season boundary resolves the target season and episode`() = runTest {
        val requests = mutableListOf<Pair<Int, Int>>()
        val resolver = NextEpisodeAirDateResolver(
            loadSeason = { tmdbId, season ->
                requests += tmdbId to season
                TmdbSeasonDetails(
                    seasonNumber = season,
                    episodes = listOf(
                        TmdbEpisode(
                            seasonNumber = 2,
                            episodeNumber = 1,
                            airDate = "2026-08-24",
                        )
                    ),
                )
            },
            clock = clock,
        )

        val result = resolver.resolve(TMDB_ID, identity(season = 2, episode = 1))

        assertThat(result).isEqualTo(NextEpisodeAirDateResolution.Allowed)
        assertThat(requests).containsExactly(TMDB_ID to 2)
    }

    private fun resolverWithAirDate(airDate: String?) = NextEpisodeAirDateResolver(
        loadSeason = { _, season ->
            TmdbSeasonDetails(
                seasonNumber = season,
                episodes = listOf(
                    TmdbEpisode(
                        seasonNumber = season,
                        episodeNumber = 2,
                        airDate = airDate,
                    )
                ),
            )
        },
        clock = clock,
    )

    private fun identity(season: Int, episode: Int) = EpisodeIdentity(
        displaySeason = season,
        displayEpisode = episode,
        tmdbSeason = season,
        tmdbEpisode = episode,
    )

    private companion object {
        const val TMDB_ID = 42
    }
}
