package com.arflix.tv.data.repository

import android.util.Base64
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.UUID

class MixedIptvGuideDeviceTest {
    @Test fun xtreamShortGuideAndTwoScopedXmltvFeedsCoexistInOneVisibleRefresh() = runBlocking {
        MockWebServer().use { server ->
            val profileId = "mixed-guide-${UUID.randomUUID()}"
            val profiles = mockk<ProfileManager>(relaxed = true)
            every { profiles.activeProfileId } returns MutableStateFlow(profileId)
            every { profiles.getProfileIdSync() } returns profileId
            every { profiles.profileStringKey(any()) } answers { stringPreferencesKey("profile_${profileId}_${firstArg<String>()}") }
            every { profiles.profileStringKeyFor(any(), any()) } answers { stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
            val now = Instant.now()
            val format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss Z").withZone(ZoneOffset.UTC)
            val start = now.minusSeconds(600)
            val end = now.plusSeconds(3600)
            fun xml(title: String) =
                """<tv><channel id="local"><display-name>Local Channel</display-name></channel><programme channel="local" start="${format.format(start)}" stop="${format.format(end)}"><title>$title</title></programme></tv>"""
            server.dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    val url = requireNotNull(request.requestUrl)
                    val body = when (url.encodedPath) {
                        "/a.m3u", "/b.m3u" -> "#EXTM3U\n#EXTINF:-1 tvg-id=\"local\" group-title=\"Local\",Local Channel\n${server.url("/video.ts")}\n"
                        "/a.xml" -> xml("Guide A")
                        "/b.xml" -> xml("Guide B")
                        "/get.php" -> "#EXTM3U\n#EXTINF:-1 tvg-id=\"api\" group-title=\"Sports\",API Channel\n${server.url("/live/test/fixture/101.ts")}\n"
                        "/player_api.php" -> when (url.queryParameter("action")) {
                            "get_live_categories" -> """[{"category_id":"1","category_name":"Sports"}]"""
                            "get_live_streams" -> """[{"stream_id":101,"name":"API Channel","category_id":"1","epg_channel_id":"api"}]"""
                            "get_short_epg", "get_simple_data_table" -> {
                                val title = Base64.encodeToString("API Guide".toByteArray(), Base64.NO_WRAP)
                                """{"epg_listings":[{"id":"1","epg_id":"api","channel_id":"api","title":"$title","start_timestamp":"${start.epochSecond}","stop_timestamp":"${end.epochSecond}"}]}"""
                            }
                            else -> """{"user_info":{"auth":1,"status":"Active","max_connections":"2"}}"""
                        }
                        else -> return MockResponse().setResponseCode(404)
                    }
                    return MockResponse().setBody(body)
                }
            }
            server.start()
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val repository = IptvRepository(context, OkHttpClient(), profiles, mockk(relaxed = true))
            val lists = listOf(
                IptvPlaylistEntry("api", "API", server.url("/get.php?username=test&password=fixture&type=m3u_plus").toString()),
                IptvPlaylistEntry("m3ua", "M3U A", server.url("/a.m3u").toString(), server.url("/a.xml").toString()),
                IptvPlaylistEntry("m3ub", "M3U B", server.url("/b.m3u").toString(), server.url("/b.xml").toString()),
            )
            try {
                repository.savePlaylists(lists)
                val channels = repository.loadSnapshot(allowNetworkEpgFetch = false, allowBroadShortEpg = false).channels
                assertEquals(setOf("api", "m3ua", "m3ub"), channels.map { it.id.substringBefore(':') }.toSet())
                val result = repository.refreshEpgForChannels(channels.mapTo(HashSet()) { it.id }, maxChannels = 0).orEmpty()
                val titles = result.mapKeys { it.key.substringBefore(':') }.mapValues { it.value.now?.title }
                assertEquals(mapOf("api" to "API Guide", "m3ua" to "Guide A", "m3ub" to "Guide B"), titles)
                assertTrue(result.values.all { it.now != null })
            } finally {
                repository.savePlaylists(emptyList())
            }
        }
    }
}
