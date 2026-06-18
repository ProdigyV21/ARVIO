package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbTvSeason
import com.arflix.tv.data.api.TmdbVideo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TrailerResolverTest {

    @Test
    fun `selectBestTrailerKey ignores non-youtube entries and blank keys`() {
        val videos = listOf(
            video(key = "vimeo-trailer", site = "Vimeo", type = "Trailer", official = true),
            video(key = "", site = "YouTube", type = "Trailer", official = true),
            video(key = "youtube-clip", site = "YouTube", type = "Clip")
        )

        assertEquals("youtube-clip", TrailerResolver.selectBestTrailerKey(videos, preferredLanguage = "en-US"))
    }

    @Test
    fun `selectBestTrailerKey prefers official trailers before teasers and clips`() {
        val videos = listOf(
            video(key = "clip", type = "Clip", official = true, name = "Official Clip"),
            video(key = "teaser", type = "Teaser", official = true, name = "Official Teaser"),
            video(key = "trailer", type = "Trailer", official = true, name = "Official Trailer")
        )

        assertEquals("trailer", TrailerResolver.selectBestTrailerKey(videos, preferredLanguage = "en-US"))
    }

    @Test
    fun `selectBestTrailerKey prefers requested language and falls back to english`() {
        val videos = listOf(
            video(key = "english", type = "Trailer", official = true, iso6391 = "en"),
            video(key = "spanish", type = "Trailer", official = true, iso6391 = "es"),
            video(key = "swedish", type = "Trailer", official = true, iso6391 = "sv")
        )

        assertEquals("swedish", TrailerResolver.selectBestTrailerKey(videos, preferredLanguage = "sv-SE"))
        assertEquals("english", TrailerResolver.selectBestTrailerKey(videos, preferredLanguage = "fr-FR"))
    }

    @Test
    fun `selectBestTrailerKey demotes weak trailer names when a real trailer exists`() {
        val videos = listOf(
            video(key = "behind-scenes", type = "Trailer", official = true, name = "Behind the Scenes"),
            video(key = "interview", type = "Trailer", official = true, name = "Cast Interview"),
            video(key = "official", type = "Trailer", official = true, name = "Official Trailer")
        )

        assertEquals("official", TrailerResolver.selectBestTrailerKey(videos, preferredLanguage = "en-US"))
    }

    @Test
    fun `selectBestTrailerKey returns null when no playable youtube candidate exists`() {
        val videos = listOf(
            video(key = "vimeo", site = "Vimeo", type = "Trailer", official = true),
            video(key = "", site = "YouTube", type = "Trailer", official = true)
        )

        assertNull(TrailerResolver.selectBestTrailerKey(videos, preferredLanguage = "en-US"))
    }

    @Test
    fun `selectBestTrailerKey can require real trailer-like types`() {
        val videos = listOf(
            video(key = "clip", type = "Clip", official = true),
            video(key = "featurette", type = "Featurette", official = true)
        )

        assertNull(
            TrailerResolver.selectBestTrailerKey(
                videos = videos,
                preferredLanguage = "en-US",
                includeFallbackTypes = false
            )
        )
    }

    @Test
    fun `seasonFallbackOrder tries newest regular season first and specials last`() {
        val seasons = listOf(
            TmdbTvSeason(seasonNumber = 0, episodeCount = 4),
            TmdbTvSeason(seasonNumber = 1, episodeCount = 8),
            TmdbTvSeason(seasonNumber = 2, episodeCount = 0),
            TmdbTvSeason(seasonNumber = 3, episodeCount = 10)
        )

        assertEquals(listOf(3, 1, 0), TrailerResolver.seasonFallbackOrder(seasons))
    }

    private fun video(
        key: String,
        site: String = "YouTube",
        type: String = "Trailer",
        official: Boolean = false,
        name: String = type,
        iso6391: String? = "en"
    ): TmdbVideo {
        return TmdbVideo(
            id = key,
            key = key,
            name = name,
            site = site,
            type = type,
            official = official,
            iso6391 = iso6391
        )
    }
}
