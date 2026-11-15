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
    private fun video(id: String, duration: String?, title: String = "Video $id", channelId: String? = null) = YouTubeSearchVideo(
        id = id,
        title = title,
        channel = "Example channel",
        duration = duration,
        views = "1K views",
        thumbnailUrl = "https://i.ytimg.com/vi/$id/mqdefault.jpg",
        channelId = channelId
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
    fun `a kids profile keeps regular YouTube videos only from channels YouTube Kids carries`() {
        // The show is missing from YouTube Kids, but its channel is there with other shows.
        val kids = listOf(
            video("EEEEEEEEEE1", "12:00", "Another show | episode 3", channelId = "UCkidsChannel"),
            video("EEEEEEEEEE2", "11:00", "Other kids show", channelId = "UCotherKids")
        )
        val web = listOf(
            video("FFFFFFFFFF1", "14:37", "Example Show | episode 1", channelId = "UCkidsChannel"),
            video("FFFFFFFFFF2", "14:40", "Example Show parody", channelId = "UCunknown"),
            video("FFFFFFFFFF3", "14:41", "Example Show reupload", channelId = null),
            video("FFFFFFFFFF4", "15:00", "Example Show | episode 2", channelId = "UCkidsChannel")
        )
        val kept = YouTubeSource.kidsChannelVideos(web, kids)
        assertEquals(
            listOf("FFFFFFFFFF1", "FFFFFFFFFF4", "EEEEEEEEEE1", "EEEEEEEEEE2"),
            kept.map { it.id }
        )
        // With the name filter, only the show's own episodes remain.
        assertEquals(
            listOf("arvio-youtube://FFFFFFFFFF1", "arvio-youtube://FFFFFFFFFF4"),
            YouTubeSource.streams(kept, listOf("Example Show")).map { it.url }
        )
    }

    private val kidsAnswer = listOf(video("GGGGGGGGGG1", "12:00", "Other kids show", channelId = "UCkidsChannel"))
    private val webAnswer = listOf(
        video("HHHHHHHHHH1", "14:37", "Example Show | episode 1", channelId = "UCkidsChannel"),
        video("HHHHHHHHHH2", "14:40", "Example Show parody", channelId = "UCunknown")
    )

    @Test
    fun `an adult profile asks regular YouTube once`() = runTest {
        val asked = mutableListOf<YouTubeKidsCorpus?>()
        val videos = YouTubeSource.search("example show", null) { _, corpus -> asked += corpus; webAnswer }
        assertEquals(listOf<YouTubeKidsCorpus?>(null), asked)
        assertEquals(webAnswer, videos)
    }

    @Test
    fun `a kids profile asks both and keeps regular videos only from YouTube Kids channels`() = runTest {
        val asked = mutableListOf<YouTubeKidsCorpus?>()
        val videos = YouTubeSource.search("example show", YouTubeKidsCorpus.YOUNGER) { _, corpus ->
            synchronized(asked) { asked += corpus }
            if (corpus == null) webAnswer else kidsAnswer
        }
        assertEquals(setOf(null, YouTubeKidsCorpus.YOUNGER), asked.toSet())
        assertEquals(listOf("HHHHHHHHHH1", "GGGGGGGGGG1"), videos.map { it.id })
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

    @Test
    fun `a failed regular answer leaves YouTube Kids' own videos`() = runTest {
        val videos = YouTubeSource.search("example show", YouTubeKidsCorpus.PRESCHOOL) { _, corpus ->
            if (corpus == null) throw IOException("regular YouTube answered HTTP 500") else kidsAnswer
        }
        assertEquals(listOf("GGGGGGGGGG1"), videos.map { it.id })
    }
}
