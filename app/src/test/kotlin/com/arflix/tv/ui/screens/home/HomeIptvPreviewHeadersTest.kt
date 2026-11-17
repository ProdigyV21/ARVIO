package com.arflix.tv.ui.screens.home

import android.net.Uri
import androidx.media3.datasource.DataSpec
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamSessionTable
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class HomeIptvPreviewHeadersTest {
    private val appAgent = "ARVIO/test"
    private val referer = "https://broadcaster.test/live/"
    private lateinit var server: MockWebServer

    @Before fun start() {
        server = MockWebServer()
        server.start()
    }

    @After fun stop() = server.shutdown()

    @Test fun `a channel without playlist headers sends the app's user agent only`() {
        assertEquals(mapOf("User-Agent" to appAgent), homeIptvPreviewHeaders(appAgent, emptyMap()))
    }

    @Test fun `the playlist's referer is kept next to the app's user agent`() {
        assertEquals(
            mapOf("User-Agent" to appAgent, "Referer" to referer),
            homeIptvPreviewHeaders(appAgent, mapOf("Referer" to referer)),
        )
    }

    @Test fun `a playlist user agent, in any case, replaces the app's`() {
        val headers = homeIptvPreviewHeaders(appAgent, mapOf("user-agent" to "PlaylistAgent/1.0", "Referer" to referer))
        assertEquals(mapOf("user-agent" to "PlaylistAgent/1.0", "Referer" to referer), headers)
    }

    @Test fun `a header that could split the request is dropped`() {
        val headers = homeIptvPreviewHeaders(appAgent, mapOf("Referer" to "$referer\r\nX-Injected: 1", "Origin" to "https://broadcaster.test"))
        assertEquals(mapOf("User-Agent" to appAgent, "Origin" to "https://broadcaster.test"), headers)
    }

    @Test fun `an uncounted preview sends the channel's referer and user agent`() {
        val request = open(homeIptvPreviewHeaders(appAgent, mapOf("Referer" to referer, "User-Agent" to "PlaylistAgent/1.0")), counted = false)
        assertEquals(referer, request.getHeader("Referer"))
        assertEquals("PlaylistAgent/1.0", request.getHeader("User-Agent"))
        assertEquals(1, request.headers.values("User-Agent").size)
    }

    @Test fun `a counted preview sends the channel's referer through its session`() {
        val request = open(homeIptvPreviewHeaders(appAgent, mapOf("Referer" to referer)), counted = true)
        assertEquals(referer, request.getHeader("Referer"))
        assertEquals(appAgent, request.getHeader("User-Agent"))
    }

    @Test fun `a channel without headers sends no referer`() {
        val request = open(homeIptvPreviewHeaders(appAgent, emptyMap()), counted = false)
        assertNull(request.getHeader("Referer"))
        assertEquals(appAgent, request.getHeader("User-Agent"))
    }

    private fun open(headers: Map<String, String>, counted: Boolean): RecordedRequest {
        server.enqueue(MockResponse().setBody("#EXTM3U\n"))
        val client = homeHeroIptvClient(OkHttpClient())
        val session = if (counted) {
            val account = IptvProviderAccount.xtream("http://panel.test", "user", "pass")!!
            IptvStreamSessionTable().tryOpenSpare(account, 1)!!
        } else {
            null
        }
        val source = homeIptvPreviewDataSource(client, headers, session).createDataSource()
        try {
            source.open(DataSpec(Uri.parse(server.url("/live/channel.m3u8").toString())))
            source.read(ByteArray(64), 0, 64)
        } finally {
            source.close()
            session?.close()
        }
        val request = server.takeRequest()
        assertFalse(request.headers.names().isEmpty())
        return request
    }
}
