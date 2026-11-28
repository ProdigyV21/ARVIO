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
import org.junit.Test

class MusicNameLookupTest {

    private fun track(id: String, title: String, artist: String) =
        MusicTrack(id = id, title = title, artist = artist, album = "", albumId = null, durationSec = 200, coverUrl = null, streamUrl = "s:$id")

    /** Like an addon that only searches in Latin letters: anything else gets the same unrelated list. */
    private class LatinOnlySource(private val byName: Map<String, MusicSearchResult>, private val junk: MusicSearchResult) : MusicSource {
        val queries = mutableListOf<String>()
        override suspend fun albums(shelf: AlbumShelf, size: Int) = emptyList<MusicAlbum>()
        override suspend fun artists() = emptyList<MusicArtist>()
        override suspend fun artistAlbums(artistId: String) = emptyList<MusicAlbum>()
        override suspend fun albumTracks(albumId: String) = emptyList<MusicTrack>()
        override suspend fun reportPlayed(trackId: String) = Unit
        override suspend fun search(query: String): MusicSearchResult {
            synchronized(queries) { queries += query }
            return if (MusicNameLookup.needsLookup(query)) junk else byName[query] ?: MusicSearchResult()
        }
    }

    private val junk = MusicSearchResult(
        artists = listOf(MusicArtist("j1", "מישהו אחר", null)),
        tracks = listOf(track("j2", "ה", "Someone Else")),
    )
    private val band = MusicSearchResult(
        artists = listOf(MusicArtist("b0", "Hakochavim Tribute", null), MusicArtist("b1", "Hakochavim", null)),
        albums = listOf(MusicAlbum("a1", "First", "Hakochavim", "b1", null), MusicAlbum("a2", "Other", "Nobody", null, null)),
        tracks = listOf(
            track("t1", "שיר אחר", "Hakochavim"),
            track("t2", "שיר ראשון", "Hakochavim, Guest"),
            track("t3", "Unrelated", "Nobody"),
        ),
    )

    @Test fun onlyNonLatinQueriesNeedALookup() {
        assertTrue(MusicNameLookup.needsLookup("הכוכבים"))
        assertTrue(MusicNameLookup.needsLookup("Кино"))
        assertTrue(MusicNameLookup.needsLookup("Band שיר"))
        assertFalse(MusicNameLookup.needsLookup("Beyoncé"))
        assertFalse(MusicNameLookup.needsLookup("AC/DC 1979"))
    }

    @Test fun latinQueryIsSearchedOnceWithoutALookup() = runBlocking {
        val source = LatinOnlySource(mapOf("Hakochavim" to band), junk)
        var asked = false
        val result = MusicNameLookup.search(source, "Hakochavim") { asked = true; listOf("x") }
        assertFalse(asked)
        assertEquals(listOf("Hakochavim"), source.queries)
        assertEquals(band, result)
    }

    @Test fun artistTypedInHebrewFindsTheArtistAndPutsItFirst() = runBlocking {
        val source = LatinOnlySource(mapOf("Hakochavim" to band), junk)
        val result = MusicNameLookup.search(source, "הכוכבים") { listOf("Hakochavim") }
        assertEquals(listOf("b1", "j1"), result.artists.map { it.id })
        assertEquals(listOf("a1"), result.albums.map { it.id })
        assertEquals(listOf("Hakochavim"), result.aliases)
        val ranked = MusicSearchRanking.rankTracks("הכוכבים", result.tracks, result.aliases)
        assertEquals(listOf("t1", "t2", "j2"), ranked.map { it.id })
        assertEquals(SearchSection.ARTISTS, MusicSearchRanking.order("הכוכבים", result.copy(tracks = ranked)).first())
    }

    @Test fun songTypedInHebrewComesFirstAndSongsStayAboveArtists() = runBlocking {
        val source = LatinOnlySource(mapOf("Hakochavim" to band), junk)
        val result = MusicNameLookup.search(source, "שיר ראשון") { listOf("Hakochavim") }
        val ranked = MusicSearchRanking.rankTracks("שיר ראשון", result.tracks, result.aliases)
        assertEquals("t2", ranked.first().id)
        assertEquals(SearchSection.TRACKS, MusicSearchRanking.order("שיר ראשון", result.copy(tracks = ranked)).first())
    }

    @Test fun failedLookupKeepsTheDirectResults() = runBlocking {
        val source = LatinOnlySource(emptyMap(), junk)
        val result = MusicNameLookup.search(source, "הכוכבים") { throw java.io.IOException("offline") }
        assertEquals(junk, result)
    }

    @Test fun deezerGivesTheArtistAndTheSingersOfASongTypedExactly() = runBlocking {
        val server = MockWebServer()
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val q = request.requestUrl?.queryParameter("q")
                val body = when (request.requestUrl?.encodedPath) {
                    "/search/artist" -> if (q == "הכוכבים") """{"data":[{"name":"Hakochavim"},{"name":"Someone"}]}""" else """{"data":[{"name":"הכוכבים"}]}"""
                    "/search/track" -> if (q == "שיר ראשון") """{"data":[
                        {"title":"שיר ראשון","artist":{"name":"Dana Levi"}},
                        {"title":"שיר ראשון","artist":{"name":"דנה"}},
                        {"title":"שיר ראשון (Live)","artist":{"name":"Dana Levi"}},
                        {"title":"שיר שני","artist":{"name":"Other Singer"}},
                        {"title":"שיר ראשון","artist":{"name":"Hakochavim"}}]}""" else """{"data":[]}"""
                    else -> """{"error":{"code":800}}"""
                }
                return MockResponse().setBody(body)
            }
        }
        server.start()
        try {
            val base = server.url("/").toString().trimEnd('/')
            assertEquals(listOf("Hakochavim"), MusicNameLookup.latinNames(OkHttpClient(), "הכוכבים", base))
            // A Hebrew-only artist name is no help; the song's singers are, each once.
            assertEquals(listOf("Dana Levi", "Hakochavim"), MusicNameLookup.latinNames(OkHttpClient(), "שיר ראשון", base))
        } finally {
            server.shutdown()
        }
    }
}
