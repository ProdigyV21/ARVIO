package com.arflix.tv.music

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.time.LocalDate

class MusicChartsTest {
    @get:Rule val temp = TemporaryFolder()
    private val http = OkHttpClient()
    private val day = LocalDate.of(2026, 11, 25)

    @Test fun germanAppGetsGermanChartsFirstAndRowsRotateDaily() {
        val rows = ChartCatalog.rows("de-DE", day)
        assertEquals("Top Germany", rows[0].query)
        assertEquals("Top Deutschland", rows[0].title)
        assertEquals("Top 50 Germany", rows[1].query)
        assertEquals("Deutsche Hits", rows[2].title)
        assertTrue(rows.size >= 40)
        assertTrue(rows.any { it.key == "christmas" })
        assertFalse(rows.any { it.key == "summer" })
        val tomorrow = ChartCatalog.rows("de-DE", day.plusDays(1))
        assertEquals(rows.map { it.key }.toSet(), tomorrow.map { it.key }.toSet())
        assertTrue(rows.map { it.key } != tomorrow.map { it.key })
        // Unknown languages fall back to the English rows.
        assertEquals("Top USA", ChartCatalog.rows("xx", day)[0].query)
        assertEquals("Top Nederland", ChartCatalog.rows("nl-NL", day)[0].title)
    }

    @Test fun chartsFollowTheCountryNotTheLanguage() {
        // English app in the Netherlands: Dutch charts and local rows, English titles.
        val rows = ChartCatalog.rows("en-US", day, "nl")
        assertEquals("Top Netherlands", rows[0].query)
        assertEquals("Top 50 Netherlands", rows[1].query)
        assertEquals("Charts", rows[1].title)
        assertEquals("Top 40 Nederland", rows[2].query)
        // Dutch app in Belgium: Belgian charts, Dutch names.
        val belgium = ChartCatalog.rows("nl", day, "BE")
        assertEquals("Top Belgium", belgium[0].query)
        assertEquals("Top België", belgium[0].title)
        assertEquals("Hitlijsten", belgium[1].title)
        // A country without its own rows still gets its chart by name.
        val greece = ChartCatalog.rows("en", day, "GR")
        assertEquals("Top Greece", greece[0].query)
        assertEquals("top40", greece[2].key)
        // No country from the device: the language's region, else the language's country.
        assertEquals("BE", ChartCatalog.country("nl-BE", null))
        assertEquals("NL", ChartCatalog.country("nl", null))
        assertEquals("GB", ChartCatalog.country("en-GB", ""))
        assertEquals("US", ChartCatalog.country("xx", null))
    }

    @Test fun deviceCountryPrefersNetworkThenSimThenTimeZone() {
        assertEquals("NL", DeviceCountry.pick("nl", "de", "Europe/Berlin", "US"))
        assertEquals("DE", DeviceCountry.pick("", "de", "Europe/Amsterdam", "US"))
        // A TV without SIM that kept "en-US": its time zone wins.
        assertEquals("NL", DeviceCountry.pick(null, null, "Europe/Amsterdam", "US"))
        assertEquals("FR", DeviceCountry.pick(null, null, "UTC", "FR"))
        assertEquals(null, DeviceCountry.pick(null, null, "UTC", ""))
    }

    @Test fun tasteKeepsPlayedArtistsAndSettledSearches() {
        val t0 = 1_700_000_000_000L
        val day = 86_400_000L
        var taste = MusicTaste()
            .recordPlay("Old Band", t0 - 120 * day)
            .recordPlay("Dua Lipa feat. DaBaby", t0)
            .recordPlay("Dua Lipa", t0 + 1)
            .recordPlay("Simon & Garfunkel", t0 + 2)
        assertEquals(listOf("Dua Lipa", "Simon & Garfunkel", "Old Band"), taste.artists.map { it.name })
        assertEquals(2, taste.artists[0].plays)
        // The favourite always leads; another one joins it.
        val today = taste.artistsForToday(t0 + day, 20_000)
        assertEquals("Dua Lipa", today[0])
        assertEquals(2, today.size)

        taste = taste.recordSearch("du", t0).recordSearch("dua", t0).recordSearch("dua lipa", t0 + 1)
            .recordSearch("top gym hits", t0 + 2)
        assertEquals(listOf("top gym hits", "dua lipa"), taste.recentSearches())
    }

    @Test fun personalRowsComeFromTasteAfterTheCharts() = runBlocking {
        val server = MockWebServer()
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val url = request.requestUrl!!
                val body = when {
                    url.encodedPath == "/search/playlist" && url.queryParameter("q") == "Top Netherlands" -> """{"data":${playlists("1", 4)}}"""
                    url.encodedPath == "/search/playlist" && url.queryParameter("q") == "Top 50 Netherlands" -> """{"data":${playlists("2", 4)}}"""
                    url.encodedPath == "/search/playlist" && url.queryParameter("q") == "Top 40 Nederland" -> """{"data":${playlists("3", 4)}}"""
                    url.encodedPath == "/search/playlist" && url.queryParameter("q") == "techno" -> """{"data":${playlists("4", 4)}}"""
                    url.encodedPath == "/search/artist" ->
                        """{"data":[{"id":5,"name":"Dua Lipa Tribute"},{"id":7,"name":"Dua Lipa","picture_xl":"https://img/dua"}]}"""
                    url.encodedPath == "/artist/7/related" ->
                        """{"data":[{"id":8,"name":"Miley Cyrus","picture_xl":"https://img/m"},{"id":9,"name":"Doja Cat"}]}"""
                    url.encodedPath == "/artist/8/radio" ->
                        """{"data":[{"id":50,"title":"Flowers","duration":200,"artist":{"name":"Miley Cyrus"},"album":{"title":"Endless"}}]}"""
                    else -> """{"data":[]}"""
                }
                return MockResponse().setBody(body).setHeader("Content-Type", "application/json")
            }
        }
        server.start()
        try {
            val taste = MusicTaste().recordPlay("Dua Lipa", 1L).recordSearch("techno", 1L)
            val source = ChartsSource(
                http, "nl-NL", null, server.url("/").toString().trimEnd('/'),
                deviceCountry = "NL", taste = { taste }, now = { 2L },
            ) { day }
            val shelves = source.shelves()
            assertEquals(
                listOf("charts:country", "charts:charts", "charts:you:artist:7", "charts:you:searches", "charts:local0"),
                shelves.map { it.key },
            )
            assertEquals("Omdat je luisterde naar Dua Lipa", shelves[2].title)
            assertEquals(listOf("mix:7", "mix:8", "mix:9"), shelves[2].albums.map { it.id })
            assertEquals("Op basis van je zoekopdrachten", shelves[3].title)
            assertEquals(listOf("Flowers"), source.albumTracks("mix:8").map { it.title })
            // The home keeps the personal rows with the leading chart rows.
            val arranged = MusicSearchRanking.arrangeHome(
                listOf(MusicShelf("0/NEWEST", emptyList(), AlbumShelf.NEWEST)) + shelves.map { it.copy(key = "1/" + it.key) },
            )
            assertEquals("0/NEWEST", arranged.last().key)
        } finally {
            server.shutdown()
        }
    }

    private fun playlists(prefix: String, count: Int) = (1..count).joinToString(",", "[", "]") {
        """{"id":$prefix$it,"title":"$prefix list $it","nb_tracks":50,"picture_xl":"https://img/$prefix$it","user":{"name":"Deezer"}}"""
    }

    @Test fun shelvesTracksAndSearchFromTheApi() = runBlocking {
        val server = MockWebServer()
        var calls = 0
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                calls++
                val url = request.requestUrl!!
                val body = when {
                    url.encodedPath == "/search/playlist" && url.queryParameter("q") == "Top 50 Germany" -> """{"data":${playlists("9", 5)}}"""
                    url.encodedPath == "/search/playlist" && url.queryParameter("q") == "Top Germany" ->
                        """{"data":${playlists("1", 4)}}"""
                    url.encodedPath == "/search/playlist" && url.queryParameter("q") == "top gym hits" ->
                        """{"data":[{"id":77,"title":"Gym","nb_tracks":40},{"id":78,"title":"Tiny","nb_tracks":2}]}"""
                    url.encodedPath == "/search/playlist" -> """{"data":${playlists("1", 2)}}"""
                    url.encodedPath == "/playlist/11/tracks" && url.queryParameter("index") == "0" ->
                        """{"data":[{"id":5,"title":"Song (feat. X)","title_short":"Song","duration":201,"artist":{"name":"Band"},"album":{"title":"LP","cover_xl":"https://img/lp"}}],"next":"more"}"""
                    url.encodedPath == "/playlist/11/tracks" -> """{"data":[{"id":6,"title":"Other","duration":100,"artist":{"name":"B"},"album":{"title":"LP"}}]}"""
                    else -> """{"error":{"type":"DataException","message":"no data","code":800}}"""
                }
                return MockResponse().setBody(body).setHeader("Content-Type", "application/json")
            }
        }
        server.start()
        try {
            val source = ChartsSource(http, "de-DE", temp.root, server.url("/").toString().trimEnd('/')) { day }
            val shelves = source.shelves()
            assertEquals("charts:country", shelves[0].key)
            assertEquals(listOf("playlist:11", "playlist:12", "playlist:13", "playlist:14"), shelves[0].albums.map { it.id })
            assertEquals("https://img/11", shelves[0].albums[0].coverUrl)
            // Playlists already shown in an earlier row are not repeated; rows that end up too short go.
            assertEquals("charts:charts", shelves[1].key)
            assertEquals(2, shelves.size)

            val before = calls
            assertEquals(shelves, source.shelves().let { ChartsSource(http, "de-DE", temp.root, "http://unused.invalid") { day }.shelves() })
            assertEquals(before, calls)

            val tracks = source.albumTracks("playlist:11")
            assertEquals(listOf("deezer:5", "deezer:6"), tracks.map { it.id })
            assertEquals("Song", tracks[0].title)
            assertEquals(MusicMatch.uri("Song", "Band", 201), tracks[0].streamUrl)
            assertEquals("https://img/lp", tracks[0].coverUrl)

            val found = source.search("top gym hits")
            assertEquals(listOf("playlist:77"), found.playlists.map { it.id })
            assertTrue(found.tracks.isEmpty())
        } finally {
            server.shutdown()
        }
    }

    private fun track(id: String, title: String, artist: String, seconds: Int = 200, stream: String = "https://cdn/$id") =
        MusicTrack(id, title, artist, "", null, seconds, null, stream)

    @Test fun chartSongsPlayFromTheUsersOwnSources() = runBlocking {
        val own = object : MusicSource {
            override suspend fun albums(shelf: AlbumShelf, size: Int) = emptyList<MusicAlbum>()
            override suspend fun artists() = emptyList<MusicArtist>()
            override suspend fun artistAlbums(artistId: String) = emptyList<MusicAlbum>()
            override suspend fun albumTracks(albumId: String) = emptyList<MusicTrack>()
            override suspend fun search(query: String) = MusicSearchResult(
                tracks = listOf(
                    track("x", "Song", "Band", stream = MusicMatch.uri("Song", "Band", 200)),
                    track("live", "Song (Live)", "Band"),
                    track("a", "Song", "Band", 199),
                ),
            )
            override suspend fun reportPlayed(trackId: String) = Unit
        }
        assertEquals("a", MusicMatch.find(own, "Song", "Band", 201).id)
        try {
            MusicMatch.find(own, "Nothing alike", "Nobody", 100)
            throw AssertionError("expected a miss")
        } catch (_: MusicMatchMissingException) {
        }
    }

    @Test fun searchPutsWhatTheUserTypedFirst() {
        val songs = listOf(track("1", "Blinding Lights (Remix)", "Someone"), track("2", "Blinding Lights", "The Weeknd"))
        val artists = listOf(MusicArtist("w", "The Weeknd", null), MusicArtist("b", "Blinding", null))
        val lists = listOf(MusicAlbum("p", "Gym", "Deezer", null, null))

        val song = MusicSearchResult(artists = artists, tracks = songs, playlists = lists)
        assertEquals(SearchSection.TRACKS, MusicSearchRanking.order("blinding lights", song).first())
        assertEquals("2", MusicSearchRanking.rankTracks("blinding lights", songs).first().id)
        assertEquals("2", MusicSearchRanking.rankTracks("the weeknd blinding lights", songs).first().id)

        assertEquals(SearchSection.ARTISTS, MusicSearchRanking.order("The Weeknd", song).first())

        val gym = MusicSearchResult(tracks = listOf(track("3", "Hits", "Gym Band")), playlists = lists)
        assertTrue(MusicSearchRanking.wantsPlaylists("top gym hits"))
        assertTrue(MusicSearchRanking.wantsPlaylists("Nederlandstalige feest"))
        assertTrue(MusicSearchRanking.wantsPlaylists("90s"))
        assertFalse(MusicSearchRanking.wantsPlaylists("bohemian rhapsody"))
        assertEquals(SearchSection.PLAYLISTS, MusicSearchRanking.order("top gym hits", gym).first())
        // Without playlists to show, songs still come first.
        assertEquals(SearchSection.TRACKS, MusicSearchRanking.order("top gym hits", gym.copy(playlists = emptyList())).first())
    }

    @Test fun homeLeadsWithTheCountryChartsThenTheUsersOwnRows() {
        fun shelf(key: String) = MusicShelf(key, emptyList())
        val arranged = MusicSearchRanking.arrangeHome(
            listOf(shelf("0/NEWEST"), shelf("1/charts:country"), shelf("1/charts:charts"), shelf("1/charts:gym")),
            lead = 2,
        )
        assertEquals(listOf("1/charts:country", "1/charts:charts", "0/NEWEST", "1/charts:gym"), arranged.map { it.key })
    }
}
