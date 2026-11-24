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
