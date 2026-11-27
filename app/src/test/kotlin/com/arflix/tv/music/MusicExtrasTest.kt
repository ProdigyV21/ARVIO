package com.arflix.tv.music

import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

/** Lyrics, the Speed dial, recently played, "Surprise me", the radio and the player's cover colours. */
class MusicExtrasTest {

    private fun track(id: String, title: String = id, artist: String = "Artist", duration: Int = 200) =
        MusicTrack(id, title, artist, "Album", null, duration, null, "https://example.com/$id")

    // -- Lyrics --------------------------------------------------------------------------------

    @Test fun lrcLinesAreTimedAndSorted() {
        val lines = Lrc.parse(
            """
            [ar:Someone]
            [00:12.50]Second
            [00:05.2]First
            [01:02.345][01:30.00]Chorus
            [00:20.00]
            not a timed line
            """.trimIndent()
        )
        assertEquals(listOf(5_200L, 12_500L, 20_000L, 62_345L, 90_000L), lines.map { it.timeMs })
        assertEquals(listOf("First", "Second", "", "Chorus", "Chorus"), lines.map { it.text })
    }

    @Test fun lrcOffsetShiftsEveryLine() {
        val lines = Lrc.parse("[offset:+500]\n[00:10.00]Line")
        assertEquals(9_500L, lines.single().timeMs)
    }

    @Test fun activeLineFollowsThePosition() {
        val lyrics = MusicLyrics(synced = Lrc.parse("[00:01.00]a\n[00:05.00]b\n[00:09.00]c"))
        assertEquals(-1, lyrics.activeIndex(500))
        assertEquals(0, lyrics.activeIndex(1_000))
        assertEquals(1, lyrics.activeIndex(8_999))
        assertEquals(2, lyrics.activeIndex(60_000))
    }

    @Test fun titlesAreCleanedForTheLookup() {
        assertEquals("Song", LyricsSource.cleanTitle("Song - Remastered 2011"))
        assertEquals("Song", LyricsSource.cleanTitle("Song (feat. Guest)"))
        assertEquals("Song - Part 2", LyricsSource.cleanTitle("Song - Part 2"))
        assertEquals("(feat. X)", LyricsSource.cleanTitle("(feat. X)"))
    }

    @Test fun searchPrefersSyncedLyricsOfTheRightLength() {
        val results = JsonParser.parseString(
            """[
              {"duration": 320, "syncedLyrics": "[00:01.00]wrong length"},
              {"duration": 201, "plainLyrics": "plain only"},
              {"duration": 199, "syncedLyrics": "[00:01.00]right"}
            ]"""
        ).asJsonArray.map { it.asJsonObject }
        val best = LyricsSource.pickBest(results, 200)!!
        assertEquals("right", best.synced.single().text)
        assertNull(LyricsSource.pickBest(results.take(1), 200))
    }

    @Test fun lyricsComeFromLrclibWithAFallbackSearch() = runBlocking {
        val server = MockWebServer()
        val asked = mutableListOf<String>()
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                asked += request.path.orEmpty()
                assertTrue(request.getHeader("User-Agent").orEmpty().startsWith("ARVIO"))
                return when {
                    request.path!!.startsWith("/api/get") && request.requestUrl!!.queryParameter("track_name") == "Found" ->
                        MockResponse().setBody("""{"syncedLyrics":"[00:02.00]hello","plainLyrics":"hello","instrumental":false}""")
                    request.path!!.startsWith("/api/get") -> MockResponse().setResponseCode(404).setBody("{}")
                    request.path!!.startsWith("/api/search") -> MockResponse().setBody("""[{"duration":200,"plainLyrics":"from search"}]""")
                    else -> MockResponse().setResponseCode(500)
                }
            }
        }
        server.start()
        try {
            val source = LyricsSource(OkHttpClient(), server.url("/").toString().trimEnd('/'))
            val found = source.find(track("1", "Found", "Band feat. Guest"))
            assertEquals("hello", found.synced.single().text)
            assertTrue(asked.single().contains("artist_name=Band&"))

            val searched = source.find(track("2", "Elsewhere - Remastered"))
            assertEquals("from search", searched.plain)
            assertTrue(asked.last().startsWith("/api/search") && asked.last().contains("track_name=Elsewhere&"))

            // Asked once per song.
            val before = asked.size
            source.find(track("2", "Elsewhere - Remastered"))
            assertEquals(before, asked.size)
        } finally {
            server.shutdown()
        }
    }

    // -- Speed dial and history ------------------------------------------------------------------

    @Test fun pinsToggleNewestFirstAndAreCapped() {
        var c = MusicCollections()
        repeat(MusicCollections.MAX_PINS + 2) { i ->
            c = c.togglePin(MusicPin.of(MusicAlbum("a$i", "Album $i", "X", null, null)))
        }
        assertEquals(MusicCollections.MAX_PINS, c.pins.size)
        assertEquals("a${MusicCollections.MAX_PINS + 1}", c.pins.first().id)
        assertTrue(c.isPinned(MusicPinType.ALBUM, "a5"))
        assertFalse(c.isPinned(MusicPinType.ALBUM, "a0"))
        c = c.togglePin(MusicPin.of(MusicAlbum("a5", "Album 5", "X", null, null)))
        assertFalse(c.isPinned(MusicPinType.ALBUM, "a5"))
        // The same id as another kind is a different pin.
        assertFalse(c.isPinned(MusicPinType.ARTIST, "a6"))
    }

    @Test fun playlistPinsFollowThePlaylist() {
        var c = MusicCollections().createPlaylist("p1", "Old name", listOf(track("t").copy(coverUrl = "cover")))
        c = c.togglePin(MusicPin.of(c.playlist("p1")!!))
        c = c.renamePlaylist("p1", "New name")
        assertEquals("New name", c.visiblePins().single().name)
        assertEquals("cover", c.visiblePins().single().coverUrl)
        c = c.deletePlaylist("p1")
        assertTrue(c.pins.isEmpty())
    }

    @Test fun pinsSurviveTheSyncAndOldSavesStillLoad() {
        val gson = Gson()
        val c = MusicCollections().togglePin(MusicPin.of(MusicArtist("ar", "Artist", "img")))
        val back = gson.fromJson(gson.toJson(c), MusicCollections::class.java).sanitized()
        assertEquals(c.pins, back.pins)
        val old = gson.fromJson("""{"favoriteTracks":[],"favoriteAlbums":[],"playlists":[]}""", MusicCollections::class.java).sanitized()
        assertTrue(old.pins.isEmpty())
        // A pin written by a newer app with a kind this one does not know is dropped, not a crash.
        val odd = gson.fromJson("""{"pins":[{"type":"PODCAST","id":"x","name":"x"}]}""", MusicCollections::class.java).sanitized()
        assertTrue(odd.pins.isEmpty())
    }

    @Test fun recentlyPlayedKeepsTheNewestOnce() {
        var taste = MusicTaste()
        repeat(MusicTaste.MAX_RECENT + 5) { i -> taste = taste.recordPlay(track("t$i"), i.toLong()) }
        taste = taste.recordPlay(track("t10"), 1_000L)
        assertEquals(MusicTaste.MAX_RECENT, taste.recent.size)
        assertEquals("t10", taste.recent.first().id)
        assertEquals(1, taste.recent.count { it.id == "t10" })
        assertEquals(MusicTaste.MAX_RECENT + 6, taste.artists.single().plays)

        val gson = Gson()
        val old = gson.fromJson("""{"artists":[{"name":"A","plays":2,"lastPlayed":1}],"searches":[]}""", MusicTaste::class.java).sanitized()
        assertTrue(old.recent.isEmpty())
    }

    // -- Surprise me -------------------------------------------------------------------------------

    @Test fun surpriseMePlaysTheUsersOwnSongsOrAMix() {
        val liked = (1..10).map { track("l$it") }
        val collections = liked.fold(MusicCollections()) { c, t -> c.toggleFavorite(t) }
        val mix = MusicAlbum("charts~mix:1", "Mix", "Mix", null, null)
        val shelves = listOf(
            MusicShelf("0/charts:top", listOf(MusicAlbum("charts~playlist:9", "Top", "Deezer", null, null)), title = "Top"),
            MusicShelf("0/charts:you:artist:1", listOf(mix), title = "Because"),
        )
        val picks = (0 until 20).map { MusicLucky.pick(collections, MusicTaste(), shelves, Random(it)) }
        assertTrue(picks.any { it is MusicLucky.Pick.Tracks })
        assertTrue(picks.any { it == MusicLucky.Pick.Album(mix) })
        picks.filterIsInstance<MusicLucky.Pick.Tracks>().forEach { assertEquals(liked.toSet(), it.tracks.toSet()) }

        // Someone new gets a playlist from the home rows; with nothing at all, nothing happens.
        assertEquals(MusicLucky.Pick.Album(shelves[0].albums[0]), MusicLucky.pick(MusicCollections(), MusicTaste(), shelves.take(1), Random(1)))
        assertNull(MusicLucky.pick(MusicCollections(), MusicTaste(), emptyList()))
    }

    // -- Radio ----------------------------------------------------------------------------------------

    private class FakeSource(private val radio: List<MusicTrack>, private val fails: Boolean = false) : MusicSource {
        var seeds = mutableListOf<MusicTrack>()
        override suspend fun albums(shelf: AlbumShelf, size: Int) = emptyList<MusicAlbum>()
        override suspend fun artists() = emptyList<MusicArtist>()
        override suspend fun artistAlbums(artistId: String) = emptyList<MusicAlbum>()
        override suspend fun albumTracks(albumId: String) = emptyList<MusicTrack>()
        override suspend fun search(query: String) = MusicSearchResult()
        override suspend fun reportPlayed(trackId: String) = Unit
        override suspend fun radio(seed: MusicTrack): List<MusicTrack> {
            seeds += seed
            if (fails) throw MusicServerException("no")
            return radio
        }
    }

    @Test fun radioAsksTheSongsOwnSourceFirst() = runBlocking {
        val server = FakeSource(listOf(track("s1"), track("seed")))
        val charts = FakeSource(listOf(track("deezer:1")))
        val composite = CompositeMusicSource(listOf(server, charts), listOf("server", "charts"))
        val result = composite.radio(track("server~seed"))
        assertEquals(listOf("server~s1"), result.map { it.id })
        assertEquals("seed", server.seeds.single().id)
        assertTrue(charts.seeds.isEmpty())
    }

    @Test fun radioFallsBackToTheNextSource() = runBlocking {
        val server = FakeSource(emptyList(), fails = true)
        val charts = FakeSource(listOf(track("deezer:1")))
        val composite = CompositeMusicSource(listOf(server, charts), listOf("server", "charts"))
        assertEquals(listOf("charts~deezer:1"), composite.radio(track("server~seed")).map { it.id })
    }

    @Test fun chartsRadioIsTheArtistsMixWithoutTheSeed() = runBlocking {
        val server = MockWebServer()
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse = when {
                request.path!!.startsWith("/search/artist") ->
                    MockResponse().setBody("""{"data":[{"id":"7","name":"Someone Else"},{"id":"42","name":"Dua Lipa"}]}""")
                request.path!!.startsWith("/artist/42/radio") -> MockResponse().setBody(
                    """{"data":[
                      {"id":"1","title":"Levitating","artist":{"name":"Dua Lipa"},"album":{"title":"FN"},"duration":203},
                      {"id":"2","title":"Physical","artist":{"name":"Dua Lipa"},"album":{"title":"FN"},"duration":193}
                    ]}"""
                )
                else -> MockResponse().setResponseCode(404)
            }
        }
        server.start()
        try {
            val charts = ChartsSource(OkHttpClient(), "en-US", baseUrl = server.url("/").toString().trimEnd('/'))
            val radio = charts.radio(track("x", "Levitating", "Dua Lipa feat. DaBaby"))
            assertEquals(listOf("Physical"), radio.map { it.title })
            assertTrue(radio.single().streamUrl.startsWith(MusicMatch.SCHEME + ":"))
        } finally {
            server.shutdown()
        }
    }

    // -- Cover colours -----------------------------------------------------------------------------

    @Test fun coverColoursKeepTextReadable() {
        val (background, accent) = CoverTint.pick(
            vibrant = 0xFF1E3A8A.toInt(), lightVibrant = null, dominant = 0xFFF5E6C8.toInt(), darkMuted = null, muted = null,
        )!!
        assertTrue(CoverTint.luminance(background) <= 0.06)
        assertTrue(CoverTint.luminance(accent) >= 0.35)
        assertEquals(0xFF, (background ushr 24) and 0xFF)
        assertNull(CoverTint.pick(null, null, null, null, null))
        // Already dark or already bright colours are left as they are.
        assertEquals(0xFF101010.toInt(), CoverTint.background(0xFF101010.toInt()))
        assertEquals(0xFFFFD54F.toInt(), CoverTint.accent(0xFFFFD54F.toInt()))
        assertNotNull(CoverTint.accent(0xFF000000.toInt()))
    }
}
