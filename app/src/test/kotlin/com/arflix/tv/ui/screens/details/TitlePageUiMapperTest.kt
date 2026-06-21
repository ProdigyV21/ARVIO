package com.arflix.tv.ui.screens.details

import com.arflix.tv.data.api.TmdbCastMember
import com.arflix.tv.data.api.TmdbCombinedCredits
import com.arflix.tv.data.api.TmdbCreditsResponse
import com.arflix.tv.data.api.TmdbCrewMember
import com.arflix.tv.data.api.TmdbMediaItem
import com.arflix.tv.data.api.TmdbPersonDetails
import com.arflix.tv.data.api.TmdbVideo
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TitlePageUiMapperTest {

    @Test
    fun `extras keep youtube videos, dedupe keys and prioritize real extras`() {
        val videos = listOf(
            video(key = "trailer", name = "Official Trailer", type = "Trailer", size = 2160),
            video(key = "feature", name = "Making Arrakis", type = "Featurette", size = 1080),
            video(key = "feature", name = "Duplicate", type = "Featurette", size = 720),
            video(key = "bts", name = "Behind the Camera", type = "Behind the Scenes", official = false, size = 1080),
            video(key = "vimeo", name = "Wrong site", type = "Clip", site = "Vimeo")
        )

        val extras = buildTitleExtras(videos)

        assertEquals(listOf("bts", "feature", "trailer"), extras.map { it.youtubeKey })
        assertEquals("Behind the Scenes", extras[0].type)
        assertEquals(3, extras.size)
    }

    @Test
    fun `extras state creates synthetic trailer when videos are empty but trailer key exists`() {
        val state = buildTitleExtrasState(
            videos = emptyList(),
            fallbackTrailerKey = "abc123",
            isLoading = false,
            hasAttemptedLoad = true
        )

        assertTrue(state.hasAttemptedLoad)
        assertFalse(state.isLoading)
        assertEquals(1, state.items.size)
        assertEquals("Trailer", state.items.single().type)
        assertEquals("abc123", state.items.single().youtubeKey)
    }

    @Test
    fun `extras state stays empty when neither tmdb videos nor trailer fallback exists`() {
        val state = buildTitleExtrasState(
            videos = emptyList(),
            fallbackTrailerKey = null,
            isLoading = false,
            hasAttemptedLoad = true
        )

        assertTrue(state.hasAttemptedLoad)
        assertTrue(state.items.isEmpty())
    }

    @Test
    fun `series playback prefers explicit play target`() {
        val episodes = listOf(
            episode(season = 1, number = 1, watched = true),
            episode(season = 1, number = 2, watched = false),
            episode(season = 1, number = 3, watched = false)
        )

        val playback = buildSeriesPlaybackUi(
            episodes = episodes,
            currentSeason = 1,
            seasonProgress = mapOf(1 to (1 to 3)),
            playSeason = 1,
            playEpisode = 3
        )

        assertEquals(3, playback.nextEpisode?.episodeNumber)
        assertEquals(1, playback.selectedSeason)
    }

    @Test
    fun `series playback falls back to first unwatched then first episode`() {
        val withUnwatched = buildSeriesPlaybackUi(
            episodes = listOf(
                episode(season = 1, number = 1, watched = true),
                episode(season = 1, number = 2, watched = false)
            ),
            currentSeason = 1,
            seasonProgress = emptyMap(),
            playSeason = null,
            playEpisode = null
        )
        val allWatched = buildSeriesPlaybackUi(
            episodes = listOf(
                episode(season = 1, number = 1, watched = true),
                episode(season = 1, number = 2, watched = true)
            ),
            currentSeason = 1,
            seasonProgress = emptyMap(),
            playSeason = null,
            playEpisode = null
        )

        assertEquals(2, withUnwatched.nextEpisode?.episodeNumber)
        assertEquals(1, allWatched.nextEpisode?.episodeNumber)
    }

    @Test
    fun `people include director as normal card before cast`() {
        val credits = TmdbCreditsResponse(
            crew = listOf(TmdbCrewMember(id = 1, name = "Denis Villeneuve", job = "Director", profilePath = "/denis.jpg")),
            cast = listOf(
                TmdbCastMember(id = 2, name = "Timothee Chalamet", character = "Paul", profilePath = "/tim.jpg"),
                TmdbCastMember(id = 1, name = "Denis Villeneuve", character = "Cameo", profilePath = "/denis.jpg")
            )
        )

        val people = buildTitlePeople(
            credits = credits,
            personDetailsById = emptyMap(),
            currentMediaType = MediaType.MOVIE,
            currentMediaId = 693134
        )

        assertEquals(listOf(1, 2), people.map { it.id })
        assertEquals("Director", people.first().role)
        assertEquals("Paul", people[1].role)
    }

    @Test
    fun `credit summary extracts directors creators and writers without duplicates`() {
        val credits = TmdbCreditsResponse(
            crew = listOf(
                TmdbCrewMember(id = 1, name = "Director One", job = "Director"),
                TmdbCrewMember(id = 2, name = "Writer One", job = "Screenplay"),
                TmdbCrewMember(id = 3, name = "Writer Two", job = "Story"),
                TmdbCrewMember(id = 4, name = "Writer One", job = "Writer"),
                TmdbCrewMember(id = 5, name = "Creator One", job = "Creator"),
                TmdbCrewMember(id = 6, name = "", job = "Director")
            )
        )

        val summary = buildTitleCreditSummary(credits)

        assertEquals(listOf("Director One"), summary.directors)
        assertEquals(listOf("Writer One", "Writer Two"), summary.writers)
        assertEquals(listOf("Creator One"), summary.creators)
        assertTrue(summary.hasContent)
    }

    @Test
    fun `known for uses person cast and crew, filters current item and dedupes`() {
        val credits = TmdbCreditsResponse(
            crew = listOf(TmdbCrewMember(id = 1, name = "Director", job = "Director", profilePath = "/director.jpg"))
        )
        val person = TmdbPersonDetails(
            id = 1,
            name = "Director",
            combinedCredits = TmdbCombinedCredits(
                cast = listOf(
                    media(id = 100, title = "Current", type = "movie", poster = "/current.jpg"),
                    media(id = 200, title = "Acting", type = "movie", poster = "/acting.jpg", voteCount = 900)
                ),
                crew = listOf(
                    media(id = 300, title = "Directed", type = "movie", poster = "/directed.jpg", voteCount = 1200),
                    media(id = 200, title = "Acting duplicate", type = "movie", poster = "/acting2.jpg", voteCount = 700)
                )
            )
        )

        val people = buildTitlePeople(
            credits = credits,
            personDetailsById = mapOf(1 to person),
            currentMediaType = MediaType.MOVIE,
            currentMediaId = 100
        )

        val knownFor = people.single().knownFor
        assertEquals(listOf(300, 200), knownFor.map { it.id })
        assertFalse(knownFor.any { it.id == 100 })
        assertTrue(knownFor.all { it.posterUrl != null })
    }

    private fun video(
        key: String,
        name: String,
        type: String,
        site: String = "YouTube",
        official: Boolean = true,
        size: Int = 1080
    ) = TmdbVideo(
        id = key,
        key = key,
        name = name,
        site = site,
        type = type,
        official = official,
        size = size
    )

    private fun episode(
        season: Int,
        number: Int,
        watched: Boolean
    ) = Episode(
        id = season * 100 + number,
        seasonNumber = season,
        episodeNumber = number,
        name = "Episode $number",
        isWatched = watched
    )

    private fun media(
        id: Int,
        title: String,
        type: String,
        poster: String?,
        voteCount: Int = 100,
        popularity: Float = 10f
    ) = TmdbMediaItem(
        id = id,
        title = title,
        name = title,
        mediaType = type,
        posterPath = poster,
        voteCount = voteCount,
        popularity = popularity
    )
}
