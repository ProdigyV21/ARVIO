package com.arflix.tv.data.repository

import com.arflix.tv.data.api.YouTubeKidsCorpus
import com.arflix.tv.data.api.YouTubeSearchVideo
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNull
import org.junit.Test
import kotlinx.coroutines.test.runTest
import java.io.IOException

class YouTubeSourceTest {
    private fun video(id: String, duration: String?, title: String = "Video $id") = YouTubeSearchVideo(
        id = id,
        title = title,
        channel = "Example channel",
        duration = duration,
        views = "1K views",
        thumbnailUrl = "https://i.ytimg.com/vi/$id/mqdefault.jpg"
    )

    @Test
    fun `a movie is searched by title and year, an episode by season and episode`() {
        assertEquals("Example Movie 2001", YouTubeSource.query(MediaType.MOVIE, " Example Movie ", 2001, null, null))
        assertEquals("Example Movie", YouTubeSource.query(MediaType.MOVIE, "Example Movie", null, null, null))
        assertEquals(
            "Example Show season 2 episode 5",
            YouTubeSource.query(MediaType.TV, "Example Show", 2019, 2, 5)
        )
        assertNull(YouTubeSource.query(MediaType.MOVIE, "  ", 2001, null, null))
    }

    @Test
    fun `only videos of five minutes or more become sources`() {
        val streams = YouTubeSource.streams(
            listOf(
                video("AAAAAAAAAA1", "2:31"),      // trailer
                video("AAAAAAAAAA2", "4:59"),
                video("AAAAAAAAAA3", "5:00"),
                video("AAAAAAAAAA4", "1:32:10"),   // the movie
                video("AAAAAAAAAA5", null)
            ),
            names = listOf("Video")
        )
        assertEquals(listOf("arvio-youtube://AAAAAAAAAA3", "arvio-youtube://AAAAAAAAAA4"), streams.map { it.url })
        val movie = streams.last()
        assertEquals(YouTubeCollection.ADDON_ID, movie.addonId)
        assertEquals("Video AAAAAAAAAA4", movie.source)
        assertEquals("Example channel · 1:32:10 · 1K views", movie.description)
    }

    @Test
    fun `video lengths are read from YouTube's text`() {
        assertEquals(151, YouTubeSearchVideo.parseDurationSeconds("2:31"))
        assertEquals(5530, YouTubeSearchVideo.parseDurationSeconds("1:32:10"))
        assertNull(YouTubeSearchVideo.parseDurationSeconds("LIVE"))
        assertNull(YouTubeSearchVideo.parseDurationSeconds(null))
    }

    @Test
    fun `only videos named after the title are kept, in YouTube's order`() {
        // A search for a Hebrew show pads its answer with other shows' first episodes.
        val streams = YouTubeSource.streams(
            listOf(
                video("BBBBBBBBBB1", "43:26", "הסדרה לדוגמה: פרק הבכורה המלא!"),
                video("BBBBBBBBBB2", "55:01", "סדרה אחרת - פרק 1 המלא!"),
                video("BBBBBBBBBB3", "43:47", "דרמה חדשה 🔥 שלישית | פרק 1"),
                video("BBBBBBBBBB4", "41:25", "Example Series episódio 1"),
                video("BBBBBBBBBB5", "52:21", "הסדרה לדוגמה 4 | פרק 1 המלא!")
            ),
            names = listOf("הסדרה לדוגמה", "Example Series")
        )
        assertEquals(
            listOf("arvio-youtube://BBBBBBBBBB1", "arvio-youtube://BBBBBBBBBB4", "arvio-youtube://BBBBBBBBBB5"),
            streams.map { it.url }
        )
    }

    @Test
    fun `names match whole words, ignoring case and punctuation`() {
        fun kept(title: String, name: String) =
            YouTubeSource.streams(listOf(video("CCCCCCCCCC1", "1:40:00", title)), listOf(name)).isNotEmpty()
        assertTrue(kept("SPIDER MAN No Way Home (2021) full movie", "Spider-Man: No Way Home"))
        assertTrue(kept("Schindlers List 1993", "Schindler's List"))
        assertTrue(kept("סרט על צה\"ל", "צה״ל"))
        assertFalse(kept("Update 2024", "Up"))
    }

    @Test
    fun `precomposed accents match their plain letters, both ways`() {
        fun kept(title: String, name: String) =
            YouTubeSource.streams(listOf(video("DDDDDDDDDD1", "22:00", title)), listOf(name)).isNotEmpty()
        assertTrue(kept("Pok\u00e9mon the movie", "Pokemon"))
        assertTrue(kept("Pokemon the movie", "Pok\u00e9mon"))
        // The same word written with a combining accent (NFD) matches too.
        assertTrue(kept("Poke\u0301mon the movie", "Pok\u00e9mon"))
        assertTrue(kept("Am\u00e9lie 2001", "Amelie"))
    }

    private val kidsAnswer = listOf(video("GGGGGGGGGG1", "12:00", "Example Show | episode 3"))
    private val webAnswer = listOf(
        video("HHHHHHHHHH1", "14:37", "Example Show | episode 1"),
        video("HHHHHHHHHH2", "14:40", "Example Show parody")
    )

    @Test
    fun `an adult profile asks regular YouTube once`() = runTest {
        val asked = mutableListOf<YouTubeKidsCorpus?>()
        val videos = YouTubeSource.search("example show", null) { _, corpus -> asked += corpus; webAnswer }
        assertEquals(listOf<YouTubeKidsCorpus?>(null), asked)
        assertEquals(webAnswer, videos)
    }

    @Test
    fun `a kids profile gets only YouTube Kids' own videos at its tier`() = runTest {
        // Regular YouTube would offer the same channel's other uploads; none of them was vetted
        // for the tier, so regular YouTube is never asked on a kids profile.
        val asked = mutableListOf<YouTubeKidsCorpus?>()
        val videos = YouTubeSource.search("example show", YouTubeKidsCorpus.YOUNGER) { _, corpus ->
            asked += corpus
            if (corpus == null) webAnswer else kidsAnswer
        }
        assertEquals(listOf<YouTubeKidsCorpus?>(YouTubeKidsCorpus.YOUNGER), asked)
        assertEquals(listOf("GGGGGGGGGG1"), videos.map { it.id })
    }

    @Test
    fun `a failed YouTube Kids answer never falls back to regular YouTube`() = runTest {
        val failure = runCatching {
            YouTubeSource.search("example show", YouTubeKidsCorpus.OLDER) { _, corpus ->
                if (corpus == null) webAnswer else throw IOException("YouTube Kids answered HTTP 500")
            }
        }
        assertTrue(failure.exceptionOrNull() is IOException)
    }
}
