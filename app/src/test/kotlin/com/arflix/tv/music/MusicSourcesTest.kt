package com.arflix.tv.music

import com.google.gson.JsonParser
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class MusicSourcesTest {
    private val http = OkHttpClient()

    @Test fun serverAddressesGetASensibleScheme() {
        assertEquals("http://192.168.1.10:4533", normalizeServerUrl(" 192.168.1.10:4533/ "))
        assertEquals("http://nas.local", normalizeServerUrl("nas.local"))
        assertEquals("http://nas", normalizeServerUrl("nas"))
        assertEquals("https://music.example.com", normalizeServerUrl("music.example.com"))
        assertEquals("https://music.example.com/navidrome", normalizeServerUrl("music.example.com/navidrome/"))
        assertEquals("http://music.example.com", normalizeServerUrl("http://music.example.com/"))
        assertEquals("", normalizeServerUrl("   "))
    }

    @Test fun subsonicUrlsCarryTokenAuthAndKeepTheServerPath() {
        val config = MusicServerConfig(MusicServerType.SUBSONIC, "https://example.com/navidrome", "me", "tok", "salt")
        val url = SubsonicSource.buildUrl(config, "getAlbum", "id" to "a 1")
        assertEquals("/navidrome/rest/getAlbum", url.encodedPath)
        assertEquals("me", url.queryParameter("u"))
        assertEquals("tok", url.queryParameter("t"))
        assertEquals("salt", url.queryParameter("s"))
        assertEquals("json", url.queryParameter("f"))
        assertEquals("a 1", url.queryParameter("id"))
        assertNull(url.queryParameter("p"))

        val root = SubsonicSource.buildUrl(config.copy(baseUrl = "http://10.0.0.2:4533"), "ping")
        assertEquals("/rest/ping", root.encodedPath)
    }

    @Test fun subsonicTokenIsMd5OfPasswordAndSalt() {
        // Example from the Subsonic API documentation.
        assertEquals("26719a1196d2a940705a59634eb18eab", SubsonicSource.md5Hex("sesame" + "c19b2d"))
        val (token, salt) = SubsonicSource.credentials("sesame")
        assertEquals(SubsonicSource.md5Hex("sesame$salt"), token)
        assertEquals(16, salt.length)
    }

    @Test fun subsonicErrorsSurfaceTheServerMessage() {
        try {
            SubsonicSource.parseResponse("""{"subsonic-response":{"status":"failed","error":{"code":40,"message":"Wrong username or password"}}}""")
            fail()
        } catch (e: MusicServerException) {
            assertEquals("Wrong username or password", e.message)
        }
        try {
            SubsonicSource.parseResponse("<html>")
            fail()
        } catch (e: MusicServerException) {
            assertEquals("Not a Subsonic server", e.message)
        }
    }

    @Test fun subsonicAlbumAndTracksParse() {
        val config = MusicServerConfig(MusicServerType.SUBSONIC, "http://nas:4533", "me", "tok", "salt")
        val source = SubsonicSource(http, config)
        val body = SubsonicSource.parseResponse(
            """{"subsonic-response":{"status":"ok","album":{"id":"al1","name":"Album","artist":"Band","artistId":"ar1",
               "coverArt":"al-1","year":2020,"songCount":2,
               "song":{"id":"s1","title":"Song","artist":"Band","album":"Album","albumId":"al1","duration":215,"track":3,"coverArt":"al-1"}}}}"""
        )
        val album = source.parseAlbum(body.obj("album"))
        assertEquals("Album", album.name)
        assertEquals(2020, album.year)
        assertEquals("al-1", album.coverUrl!!.toHttpUrl().queryParameter("id"))

        // A lone object where a list is expected still reads as one track.
        val tracks = body.obj("album").list("song").map { source.parseTrack(it) }
        assertEquals(1, tracks.size)
        assertEquals(215, tracks[0].durationSec)
        assertEquals(3, tracks[0].trackNumber)
        val stream = tracks[0].streamUrl.toHttpUrl()
        assertEquals("/rest/stream", stream.encodedPath)
        assertEquals("s1", stream.queryParameter("id"))
    }

    @Test fun jellyfinItemsParse() {
        val config = MusicServerConfig(MusicServerType.JELLYFIN, "http://jf:8096", "me", "jtok", userId = "u1", deviceId = "d1")
        val source = JellyfinMusicSource(http, config)
        val album = source.parseAlbum(JsonParser.parseString(
            """{"Id":"al1","Name":"Album","AlbumArtist":"Band","AlbumArtists":[{"Id":"ar1","Name":"Band"}],
               "ProductionYear":1999,"ChildCount":12,"ImageTags":{"Primary":"x"},"Type":"MusicAlbum"}"""
        ).asJsonObject)
        assertEquals("ar1", album.artistId)
        assertEquals(12, album.trackCount)
        assertTrue(album.coverUrl!!.startsWith("http://jf:8096/Items/al1/Images/Primary"))

        val track = source.parseTrack(JsonParser.parseString(
            """{"Id":"t1","Name":"Song","ArtistItems":[{"Name":"A"},{"Name":"B"}],"Album":"Album","AlbumId":"al1",
               "AlbumPrimaryImageTag":"y","RunTimeTicks":1850000000,"IndexNumber":4,"ImageTags":{}}"""
        ).asJsonObject)
        assertEquals("A, B", track.artist)
        assertEquals(185, track.durationSec)
        assertTrue(track.coverUrl!!.contains("/Items/al1/"))
        val stream = track.streamUrl.toHttpUrl()
        assertEquals("/Audio/t1/stream", stream.encodedPath)
        assertEquals("jtok", stream.queryParameter("api_key"))

        val noCover = source.parseAlbum(JsonParser.parseString("""{"Id":"al2","Name":"X"}""").asJsonObject)
        assertNull(noCover.coverUrl)
    }

    @Test fun jellyfinAuthorizationHeader() {
        assertEquals(
            "MediaBrowser Client=\"ARVIO\", Device=\"Android\", DeviceId=\"d1\", Version=\"1.0\", Token=\"t\"",
            JellyfinMusicSource.authorization("d1", "t"),
        )
    }
}

class MusicAddonSourceTest {
    private val http = OkHttpClient()

    private val manifest = JsonParser.parseString(
        """{"id":"tv.arvio.music.audius","name":"Audius","types":["music"],"catalogs":[
             {"type":"music","id":"trending","name":"Trending"},
             {"type":"movie","id":"films","name":"Films"},
             {"type":"music","id":"search","name":"Search","extra":[{"name":"search","isRequired":true}]}]}"""
    ).asJsonObject

    @Test fun addonUrlsAreNormalized() {
        assertEquals("https://resolve.arvio.tv/music/audius", MusicAddonSource.normalizeAddonUrl("https://resolve.arvio.tv/music/audius/manifest.json"))
        assertEquals("https://resolve.arvio.tv/music/audius", MusicAddonSource.normalizeAddonUrl("stremio://resolve.arvio.tv/music/audius/manifest.json"))
        assertEquals("https://addon.example.com", MusicAddonSource.normalizeAddonUrl("addon.example.com/"))
    }

    @Test fun manifestKeepsOnlyMusicCatalogs() {
        val addon = MusicAddonSource.parseManifest("https://a.example", manifest)
        assertEquals("Audius", addon.name)
        assertEquals(listOf("trending", "search"), addon.catalogs.map { it.id })
        assertEquals(listOf(false, true), addon.catalogs.map { it.searchOnly })
        try {
            MusicAddonSource.parseManifest("https://a.example", JsonParser.parseString("""{"id":"x","types":["movie"],"catalogs":[]}""").asJsonObject)
            fail()
        } catch (e: MusicServerException) {
            assertEquals("This is not a music addon", e.message)
        }
    }

    @Test fun tracksUseTheirOwnStreamOrAskTheAddonLater() {
        val source = MusicAddonSource(http, MusicAddonSource.parseManifest("https://a.example/music", manifest))
        val album = source.parseAlbum(JsonParser.parseString("""{"id":"playlist:1","name":"PL","artist":"Band","poster":"https://img/1","releaseInfo":"2021"}""").asJsonObject)
        assertEquals(2021, album.year)
        val direct = source.parseTrack(JsonParser.parseString("""{"id":"t1","title":"Song","duration":200,"streams":[{"url":"https://cdn/t1.mp3"}]}""").asJsonObject, album, 0)
        assertEquals("https://cdn/t1.mp3", direct.streamUrl)
        assertEquals("Band", direct.artist)
        assertEquals("https://img/1", direct.coverUrl)
        assertEquals(1, direct.trackNumber)
        val lazy = source.parseTrack(JsonParser.parseString("""{"id":"t 2","title":"Song 2"}""").asJsonObject, album, 1)
        assertTrue(lazy.streamUrl.startsWith("${MusicAddonSource.STREAM_SCHEME}://stream?base=https%3A%2F%2Fa.example%2Fmusic&id=t+2"))
    }

    @Test fun compositeRoutesIdsBackToTheirSource() = kotlinx.coroutines.runBlocking {
        class Fake(val tag: String) : MusicSource {
            var lastAlbum: String? = null
            override suspend fun albums(shelf: AlbumShelf, size: Int) = listOf(MusicAlbum("a", "$tag album", "x", "ar", null))
            override suspend fun artists() = emptyList<MusicArtist>()
            override suspend fun artistAlbums(artistId: String) = emptyList<MusicAlbum>()
            override suspend fun albumTracks(albumId: String): List<MusicTrack> {
                lastAlbum = albumId
                return listOf(MusicTrack("t", "T", "x", "A", albumId, 1, null, "u"))
            }
            override suspend fun search(query: String) = MusicSearchResult()
            override suspend fun reportPlayed(trackId: String) = Unit
        }
        val first = Fake("one")
        val second = object : MusicSource by Fake("two") {
            override suspend fun albums(shelf: AlbumShelf, size: Int): List<MusicAlbum> = error("offline")
        }
        val third = Fake("three")
        val composite = CompositeMusicSource(listOf(first, second, third))
        val albums = composite.albums(AlbumShelf.ALPHABETICAL, 10)
        assertEquals(listOf("0~a", "2~a"), albums.map { it.id })
        assertEquals("2~ar", albums[1].artistId)
        val tracks = composite.albumTracks("2~a")
        assertEquals("a", third.lastAlbum)
        assertNull(first.lastAlbum)
        assertEquals("2~t", tracks[0].id)
        assertEquals("2~a", tracks[0].albumId)
    }
}

class MusicCollectionsTest {
    private fun track(id: String) = MusicTrack(id, "T$id", "A", "Al", "al", 100, null, "u$id")
    private val album = MusicAlbum("al", "Album", "A", null, null)

    @Test fun favouritesToggleAndNewestComesFirst() {
        val c = MusicCollections().toggleFavorite(track("1")).toggleFavorite(track("2")).toggleFavorite(album)
        assertEquals(listOf("2", "1"), c.favoriteTracks.map { it.id })
        assertTrue(c.isFavorite(album))
        val off = c.toggleFavorite(track("1")).toggleFavorite(album)
        assertEquals(listOf("2"), off.favoriteTracks.map { it.id })
        assertTrue(off.favoriteAlbums.isEmpty())
    }

    @Test fun playlistsAddRemoveMoveAndSkipDuplicates() {
        var c = MusicCollections().createPlaylist("p", "  ", listOf(track("1"), track("1"), track("2")))
        assertEquals(MusicCollections.DEFAULT_NAME, c.playlist("p")!!.name)
        assertEquals(listOf("1", "2"), c.playlist("p")!!.tracks.map { it.id })
        c = c.addToPlaylist("p", listOf(track("2"), track("3")))
        assertEquals(listOf("1", "2", "3"), c.playlist("p")!!.tracks.map { it.id })
        c = c.moveInPlaylist("p", 2, 0).removeFromPlaylist("p", 1).renamePlaylist("p", " Gym ").renamePlaylist("p", "")
        assertEquals(listOf("3", "2"), c.playlist("p")!!.tracks.map { it.id })
        assertEquals("Gym", c.playlist("p")!!.name)
        assertEquals(c, c.moveInPlaylist("p", 0, 9).removeFromPlaylist("p", 5))
        assertTrue(c.deletePlaylist("p").playlists.isEmpty())
    }

    @Test fun importedPlaylistIsReplacedNotDuplicated() {
        val c = MusicCollections().replaceImported("a", "Mix", listOf(track("1")), "spotify:x")
            .replaceImported("b", "Mix", listOf(track("2"), track("3")), "spotify:x")
        assertEquals(1, c.playlists.size)
        assertEquals("a", c.playlists[0].id)
        assertEquals(listOf("2", "3"), c.playlists[0].tracks.map { it.id })
    }

    @Test fun oldSavesWithMissingFieldsStillLoad() {
        val c = com.google.gson.Gson().fromJson("""{"playlists":[{"id":"p","tracks":null}]}""", MusicCollections::class.java).sanitized()
        assertTrue(c.favoriteTracks.isEmpty())
        assertEquals(MusicCollections.DEFAULT_NAME, c.playlists[0].name)
        assertTrue(c.playlists[0].tracks.isEmpty())
    }
}

class EclipseAddonSourceTest {
    private val http = OkHttpClient()
    private val manifest = JsonParser.parseString(
        """{"id":"com.example.music","name":"Example","version":"1.0.0","resources":["search","stream","catalog"],
           "types":["track","album","playlist"],"catalogs":[{"id":"hot","type":"track","name":"Hot"},{"id":"new","type":"album","name":"New"}]}"""
    ).asJsonObject

    @Test fun manifestIsRecognisedAndKeepsCatalogTypes() {
        assertTrue(EclipseAddonSource.isEclipseManifest(manifest))
        val stremio = JsonParser.parseString("""{"id":"x","resources":["catalog","meta","stream"],"types":["music"]}""").asJsonObject
        assertTrue(!EclipseAddonSource.isEclipseManifest(stremio))
        val addon = EclipseAddonSource.parseManifest("https://a.example/tok", manifest)
        assertEquals(EclipseAddonSource.PROTOCOL, addon.protocol)
        assertEquals(listOf("track", "album"), addon.catalogs.map { it.type })
    }

    @Test fun tracksAlbumsAndStreamsParse() {
        val source = EclipseAddonSource(http, EclipseAddonSource.parseManifest("https://a.example/tok", manifest))
        val album = source.parseAlbum(JsonParser.parseString("""{"id":"9","title":"LP","artist":"Band","artworkURL":"https://img/9","year":"2019"}""").asJsonObject, "album")
        assertEquals("album:9", album.id)
        assertEquals(2019, album.year)
        val playlist = source.parseAlbum(JsonParser.parseString("""{"id":"5","title":"Mix","creator":"Me"}""").asJsonObject, "playlist")
        assertEquals("playlist:5", playlist.id)
        assertEquals("Me", playlist.artist)

        val lazy = source.parseTrack(JsonParser.parseString("""{"id":"t/1","title":"Song","artist":"Band","durationMs":181000}""").asJsonObject, album, 0)
        assertEquals(181, lazy.durationSec)
        assertEquals("https://img/9", lazy.coverUrl)
        assertEquals(
            "${MusicAddonSource.STREAM_SCHEME}://stream?base=https%3A%2F%2Fa.example%2Ftok&id=t%2F1&p=eclipse",
            lazy.streamUrl,
        )
        val direct = source.parseTrack(JsonParser.parseString("""{"id":"t2","title":"S2","artist":"B","duration":200,"streamURL":"https://cdn/2.flac"}""").asJsonObject, null, null)
        assertEquals("https://cdn/2.flac", direct.streamUrl)
        assertEquals(200, direct.durationSec)
    }

    @Test fun searchAlbumAndStreamAgainstAServer() = kotlinx.coroutines.runBlocking {
        val server = okhttp3.mockwebserver.MockWebServer()
        server.dispatcher = object : okhttp3.mockwebserver.Dispatcher() {
            override fun dispatch(request: okhttp3.mockwebserver.RecordedRequest): okhttp3.mockwebserver.MockResponse {
                val body = when (request.requestUrl!!.encodedPath) {
                    "/tok/manifest.json" -> manifest.toString()
                    "/tok/search" -> """{"tracks":[{"id":"t1","title":"Song","artist":"Band"}],"playlists":[{"id":"5","title":"Mix"}]}"""
                    "/tok/playlist/5" -> """{"id":"5","title":"Mix","tracks":[{"id":"t1","title":"Song","artist":"Band","artworkURL":"https://img/t1"}]}"""
                    "/tok/catalog/hot" -> """{"items":[{"id":"t9","type":"track","title":"Hit","artist":"X","durationMs":90000,"artworkURL":"https://img/9"}]}"""
                    "/tok/catalog/new" -> """{"items":[{"id":"8","type":"album","title":"Fresh","artist":"Y"}]}"""
                    "/tok/stream/t1" -> """{"url":"https://cdn/t1.mp3","format":"mp3"}"""
                    else -> return okhttp3.mockwebserver.MockResponse().setResponseCode(404)
                }
                return okhttp3.mockwebserver.MockResponse().setBody(body).setHeader("Content-Type", "application/json")
            }
        }
        server.start()
        try {
            val base = server.url("/tok/manifest.json").toString()
            val addon = MusicAddonSource.install(http, base)
            assertEquals(EclipseAddonSource.PROTOCOL, addon.protocol)
            val source = EclipseAddonSource(http, addon)
            val found = source.search("song")
            assertEquals("Song", found.tracks.single().title)
            assertEquals("playlist:5", found.playlists.single().id)
            assertTrue(found.albums.isEmpty())
            assertEquals("https://img/t1", source.albumTracks("playlist:5").single().coverUrl)
            val shelves = source.shelves()
            assertEquals(listOf("Hot", "New"), shelves.map { it.title })
            assertEquals("catalog:hot", shelves[0].albums.single().id)
            assertEquals("Hit", source.albumTracks("catalog:hot").single().title)
            assertEquals("https://cdn/t1.mp3", MusicAddonSource.resolveStream(http, addon.baseUrl, "t1", EclipseAddonSource.PROTOCOL))
        } finally {
            server.shutdown()
        }
    }

    @Test fun compositeKeysStayStableWhenSourcesChange() = kotlinx.coroutines.runBlocking {
        val one = object : MusicSource {
            override suspend fun albums(shelf: AlbumShelf, size: Int) = listOf(MusicAlbum("a", "A", "x", null, null))
            override suspend fun artists() = emptyList<MusicArtist>()
            override suspend fun artistAlbums(artistId: String) = emptyList<MusicAlbum>()
            override suspend fun albumTracks(albumId: String) = listOf(MusicTrack("t", "T", "x", "A", albumId, 1, null, "u"))
            override suspend fun search(query: String) = MusicSearchResult()
            override suspend fun reportPlayed(trackId: String) = Unit
        }
        val before = CompositeMusicSource(listOf(one), listOf("com.example~odd"))
        val id = before.albums(AlbumShelf.ALPHABETICAL, 1).single().id
        assertEquals("com.example_odd~a", id)
        val after = CompositeMusicSource(listOf(one, one), listOf("server", "com.example~odd"))
        assertEquals("com.example_odd~a", after.albumTracks(id).single().albumId)
    }
}

class SpotifyImportTest {
    @Test fun exportifyCsvWithQuotesAndSeveralArtists() {
        val csv = "﻿\"Track URI\",\"Track Name\",\"Album Name\",\"Artist Name(s)\",\"Duration (ms)\"\r\n" +
            "\"spotify:track:1\",\"Hello, \"\"World\"\"\",\"Album\",\"Band,Guest\",\"200000\"\r\n" +
            "\"spotify:track:2\",\"Second\",\"\",\"Solo\",\"\"\r\n"
        val playlist = SpotifyImport.parse("Road_Trip.csv", csv.toByteArray())
        assertEquals(1, playlist.size)
        assertEquals("Road Trip", playlist[0].name)
        assertEquals(ImportedTrack("Hello, \"World\"", "Band", "Album", 200000), playlist[0].tracks[0])
        assertEquals("Solo", playlist[0].tracks[1].artist)
        assertTrue(SpotifyImport.parse("Liked_Songs.csv", csv.toByteArray())[0].liked)
    }

    @Test fun spotifyDataExportJsonAndZip() {
        val library = """{"tracks":[{"artist":"A","album":"B","track":"Song","uri":"spotify:track:x"}],"albums":[]}"""
        val playlists = """{"playlists":[{"name":"Gym","items":[{"track":{"trackName":"Run","artistName":"C","albumName":"D"}},{"episode":null}]}]}"""
        val liked = SpotifyImport.parse("YourLibrary.json", library.toByteArray()).single()
        assertTrue(liked.liked)
        assertEquals("Song", liked.tracks.single().title)
        val bytes = java.io.ByteArrayOutputStream().also { out ->
            java.util.zip.ZipOutputStream(out).use { zip ->
                zip.putNextEntry(java.util.zip.ZipEntry("Spotify Account Data/YourLibrary.json")); zip.write(library.toByteArray()); zip.closeEntry()
                zip.putNextEntry(java.util.zip.ZipEntry("Spotify Account Data/Playlist1.json")); zip.write(playlists.toByteArray()); zip.closeEntry()
            }
        }.toByteArray()
        val all = SpotifyImport.parse("my_spotify_data.zip", bytes)
        assertEquals(listOf(SpotifyImport.LIKED_NAME, "Gym"), all.map { it.name })
        assertEquals("Run", all[1].tracks.single().title)
    }

    private fun found(title: String, artist: String, sec: Int = 0) = MusicTrack(title, title, artist, "", null, sec, null, "u")

    @Test fun matchingIgnoresDecorationsButNeedsTheArtist() {
        val wanted = ImportedTrack("Bohemian Rhapsody - Remastered 2011", "Queen", durationMs = 354000)
        assertTrue(SpotifyImport.score(wanted, found("Bohemian Rhapsody", "Queen", 355)) >= SpotifyImport.MATCH_THRESHOLD)
        assertTrue(SpotifyImport.score(wanted, found("Bohemian Rhapsody (Live)", "Queen")) >= SpotifyImport.MATCH_THRESHOLD)
        assertTrue(SpotifyImport.score(wanted, found("Bohemian Rhapsody", "Cover Band", 354)) < SpotifyImport.MATCH_THRESHOLD)
        assertTrue(SpotifyImport.score(wanted, found("Another Song", "Queen", 354)) < SpotifyImport.MATCH_THRESHOLD)
        assertTrue(SpotifyImport.score(ImportedTrack("Café", "Beyoncé"), found("Cafe", "Beyonce")) >= SpotifyImport.MATCH_THRESHOLD)
    }

    @Test fun matchPicksTheBestAndLeavesMissingOut() = kotlinx.coroutines.runBlocking {
        val source = object : MusicSource {
            override suspend fun albums(shelf: AlbumShelf, size: Int) = emptyList<MusicAlbum>()
            override suspend fun artists() = emptyList<MusicArtist>()
            override suspend fun artistAlbums(artistId: String) = emptyList<MusicAlbum>()
            override suspend fun albumTracks(albumId: String) = emptyList<MusicTrack>()
            override suspend fun search(query: String) = MusicSearchResult(
                tracks = if (query.contains("Run")) listOf(found("Run (Remix)", "C"), found("Run", "C")) else emptyList()
            )
            override suspend fun reportPlayed(trackId: String) = Unit
        }
        var last = 0 to 0
        val result = SpotifyImport.match(source, listOf(ImportedTrack("Run", "C"), ImportedTrack("Nope", "Z"))) { d, t -> last = d to t }
        assertEquals("Run", result[0]!!.title)
        assertNull(result[1])
        assertEquals(2 to 2, last)
    }
}
