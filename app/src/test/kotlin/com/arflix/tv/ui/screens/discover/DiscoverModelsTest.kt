package com.arflix.tv.ui.screens.discover

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.NextEpisode
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DiscoverModelsTest {
    @Test
    fun `movies and series use separate default sections`() {
        val movies = defaultDiscoverSections(
            mode = DiscoverMediaMode.MOVIES,
            date = LocalDate.of(2026, 5, 10)
        )
        val series = defaultDiscoverSections(
            mode = DiscoverMediaMode.SERIES,
            date = LocalDate.of(2026, 5, 10)
        )

        assertTrue(movies.any { it.id == "movie_trending" })
        assertTrue(series.any { it.id == "series_trending" })
        assertFalse(movies.any { it.id.startsWith("series_") })
        assertFalse(series.any { it.id.startsWith("movie_") })
    }

    @Test
    fun `discover starts with decision intent rows for each media mode`() {
        val movies = defaultDiscoverSections(
            mode = DiscoverMediaMode.MOVIES,
            date = LocalDate.of(2026, 5, 10)
        )
        val series = defaultDiscoverSections(
            mode = DiscoverMediaMode.SERIES,
            date = LocalDate.of(2026, 5, 10)
        )

        assertEquals("movie_decide_now", movies.first().id)
        assertEquals(
            listOf(
                "collection_intent_short_movie",
                "collection_intent_best_movies",
                "collection_intent_new_streaming_movies"
            ),
            movies.first().catalogIds
        )
        assertEquals("series_decide_now", series.first().id)
        assertEquals(
            listOf(
                "collection_intent_best_series",
                "collection_intent_series_buzz",
                "collection_intent_new_streaming_series",
                "top10_shows_today"
            ),
            series.first().catalogIds
        )
    }

    @Test
    fun `christmas is active from one month before through one week after`() {
        val before = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 11, 23),
            mode = DiscoverMediaMode.MOVIES
        )
        val starts = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 11, 24),
            mode = DiscoverMediaMode.MOVIES
        )
        val stillActive = activeSwedishSeasonalSections(
            date = LocalDate.of(2027, 1, 1),
            mode = DiscoverMediaMode.MOVIES
        )
        val after = activeSwedishSeasonalSections(
            date = LocalDate.of(2027, 1, 2),
            mode = DiscoverMediaMode.MOVIES
        )

        assertFalse(before.any { it.id == "season_christmas_movies" })
        assertTrue(starts.any { it.id == "season_christmas_movies" })
        assertTrue(stillActive.any { it.id == "season_christmas_movies" })
        assertFalse(after.any { it.id == "season_christmas_movies" })
    }

    @Test
    fun `halloween is active in october through first november week`() {
        val halloween = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 10, 12),
            mode = DiscoverMediaMode.SERIES
        )
        val late = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 11, 7),
            mode = DiscoverMediaMode.SERIES
        )
        val after = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 11, 8),
            mode = DiscoverMediaMode.SERIES
        )

        assertTrue(halloween.any { it.id == "season_halloween_series" })
        assertTrue(late.any { it.id == "season_halloween_series" })
        assertFalse(after.any { it.id == "season_halloween_series" })
    }

    @Test
    fun `midsummer is active during june`() {
        val june = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 6, 20),
            mode = DiscoverMediaMode.MOVIES
        )
        val july = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 7, 4),
            mode = DiscoverMediaMode.MOVIES
        )

        assertTrue(june.any { it.id == "season_midsummer_movies" })
        assertFalse(july.any { it.id == "season_midsummer_movies" })
    }

    @Test
    fun `sport break creates Swedish family discovery sections`() {
        val movies = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 2, 18),
            mode = DiscoverMediaMode.MOVIES
        )
        val series = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 3, 8),
            mode = DiscoverMediaMode.SERIES
        )
        val after = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 3, 11),
            mode = DiscoverMediaMode.MOVIES
        )

        assertTrue(movies.any { it.id == "season_sport_break_movies" })
        assertTrue(series.any { it.id == "season_sport_break_series" })
        assertFalse(after.any { it.id == "season_sport_break_movies" })
    }

    @Test
    fun `easter creates holiday rows around Swedish school break timing`() {
        val before = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 3, 28),
            mode = DiscoverMediaMode.MOVIES
        )
        val active = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 4, 5),
            mode = DiscoverMediaMode.MOVIES
        )
        val after = activeSwedishSeasonalSections(
            date = LocalDate.of(2026, 4, 16),
            mode = DiscoverMediaMode.MOVIES
        )

        assertFalse(before.any { it.id == "season_easter_movies" })
        assertTrue(active.any { it.id == "season_easter_movies" })
        assertFalse(after.any { it.id == "season_easter_movies" })
    }

    @Test
    fun `seasonal sections come before evergreen sections`() {
        val sections = defaultDiscoverSections(
            mode = DiscoverMediaMode.MOVIES,
            date = LocalDate.of(2026, 12, 10)
        )

        assertEquals("season_christmas_movies", sections.first().id)
        assertTrue(sections.drop(1).any { it.id == "movie_trending" })
    }

    @Test
    fun `catalog cards resolve from configured ids and keep stable image priority`() {
        val specs = listOf(
            DiscoverSectionSpec(
                id = "quality",
                title = "Högt betyg",
                subtitle = "Säkra kort",
                catalogIds = listOf("missing", "trending_movies", "top_movies_week"),
                accent = 0xFF60A5FA
            )
        )
        val catalogs = listOf(
            CatalogConfig(
                id = "top_movies_week",
                title = "Top movies this week",
                sourceType = CatalogSourceType.MDBLIST,
                collectionDescription = "Community-curated",
                collectionCoverImageUrl = "cover.jpg"
            ),
            CatalogConfig(
                id = "trending_movies",
                title = "Trending movies",
                sourceType = CatalogSourceType.TRAKT,
                collectionDescription = "",
                collectionCoverImageUrl = "cover-low.jpg",
                collectionHeroImageUrl = "hero.jpg"
            )
        )

        val sections = buildDiscoverUiSections(specs, catalogs)

        assertEquals(1, sections.size)
        assertEquals("quality", sections.first().id)
        assertEquals(listOf("trending_movies", "top_movies_week"), sections.first().cards.map { it.catalogId })
        assertEquals("hero.jpg", sections.first().cards.first().imageUrl)
        assertEquals("Trakt", sections.first().cards.first().sourceLabel)
        assertEquals("MDBList", sections.first().cards.last().sourceLabel)
    }

    @Test
    fun `catalog card descriptions follow selected media mode`() {
        val specs = listOf(
            DiscoverSectionSpec(
                id = "mood",
                title = "Välj efter känsla",
                subtitle = "Fallback",
                catalogIds = listOf("collection_genre_comedy"),
                accent = 0xFFA78BFA
            )
        )
        val catalogs = listOf(
            CatalogConfig(
                id = "collection_genre_comedy",
                title = "Comedy",
                sourceType = CatalogSourceType.PREINSTALLED,
                collectionDescription = "Comedy movies and series."
            )
        )

        val movieSections = buildDiscoverUiSections(specs, catalogs, mode = DiscoverMediaMode.MOVIES)
        val seriesSections = buildDiscoverUiSections(specs, catalogs, mode = DiscoverMediaMode.SERIES)

        assertEquals("Comedy movies.", movieSections.first().cards.first().subtitle)
        assertEquals("Comedy series.", seriesSections.first().cards.first().subtitle)
    }

    @Test
    fun `continue watching cards stay separated by selected media mode`() {
        val items = listOf(
            MediaItem(
                id = 11,
                title = "Short Night Movie",
                year = "2026",
                imdbRating = "7.8",
                mediaType = MediaType.MOVIE,
                image = "movie-poster.jpg",
                backdrop = "movie-backdrop.jpg",
                progress = 42
            ),
            MediaItem(
                id = 22,
                title = "Slow Burn Show",
                subtitle = "Continue S1.E3",
                mediaType = MediaType.TV,
                image = "show-poster.jpg",
                year = "2026",
                genreIds = listOf(18, 9648),
                originalLanguage = "en",
                progress = 17,
                nextEpisode = NextEpisode(
                    id = 100,
                    seasonNumber = 1,
                    episodeNumber = 3,
                    name = "Episode 3"
                )
            )
        )

        val movieSection = buildContinueWatchingDiscoverSection(DiscoverMediaMode.MOVIES, items)
        val seriesSection = buildContinueWatchingDiscoverSection(DiscoverMediaMode.SERIES, items)

        assertEquals(listOf("Short Night Movie"), movieSection?.cards?.map { it.title })
        assertEquals(MediaType.MOVIE, movieSection?.cards?.first()?.mediaType)
        assertEquals(42, movieSection?.cards?.first()?.progress)
        assertEquals("movie-backdrop.jpg", movieSection?.cards?.first()?.imageUrl)

        val seriesCard = seriesSection?.cards?.single()
        assertEquals("Slow Burn Show", seriesCard?.title)
        assertEquals(MediaType.TV, seriesCard?.mediaType)
        assertEquals(1, seriesCard?.initialSeason)
        assertEquals(3, seriesCard?.initialEpisode)
        assertEquals("2026", seriesCard?.year)
        assertEquals(listOf(18, 9648), seriesCard?.genreIds)
        assertEquals("en", seriesCard?.originalLanguage)
    }

    @Test
    fun `because you watched row filters recommendations to active mode`() {
        val section = buildBecauseYouWatchedDiscoverSection(
            mode = DiscoverMediaMode.MOVIES,
            seedTitle = "Heat",
            items = listOf(
                MediaItem(id = 1, title = "Thief", mediaType = MediaType.MOVIE, image = "thief.jpg"),
                MediaItem(id = 2, title = "Miami Vice", mediaType = MediaType.TV, image = "vice.jpg"),
                MediaItem(id = 1, title = "Thief duplicate", mediaType = MediaType.MOVIE, image = "dupe.jpg")
            )
        )

        assertEquals("personal_similar_movies", section?.id)
        assertEquals("Liknande Heat", section?.title)
        assertEquals(listOf("Thief"), section?.cards?.map { it.title })
        assertTrue(section?.cards?.first()?.isMediaTitle == true)
    }
}
