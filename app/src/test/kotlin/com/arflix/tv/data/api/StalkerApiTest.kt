package com.arflix.tv.data.api

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.Reader

private const val PORTAL = "http://portal.example.com"
private const val MAC = "00:1A:79:AA:BB:CC"

class StalkerApiTest {

    private fun stubApi(
        portal: String = PORTAL,
        requests: MutableList<String>,
        respond: (String) -> String?
    ): StalkerApi =
        object : StalkerApi(portal, MAC) {
            override fun doGet(url: String): String {
                requests += url
                return respond(url) ?: error("Unexpected url: $url")
            }

            override fun doGetReader(url: String): Reader {
                requests += url
                return (respond(url) ?: error("Unexpected url: $url")).reader()
            }
        }

    @Test
    fun `handshake stops probing once root returns JSON token`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=handshake") -> """{ "js": { "token": "ABC123" } }"""
                else -> null
            }
        }

        val ok = api.handshake()

        assertTrue(ok)
        // The base-path probe is itself a handshake; repeating it would only throw
        // away the token it just returned.
        assertEquals(
            listOf(
                "$PORTAL/server/load.php?type=stb&action=handshake"
            ),
            requests
        )
    }

    @Test
    fun `failed catalog page never returns a partial successful channel list`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_genres") -> """{"js":[]}"""
                url.contains("action=get_all_channels&p=1&") ->
                    """{"js":{"total_items":2,"max_page_items":1,"data":[{"id":1,"name":"One","cmd":"http://provider.test/1"}]}}"""
                else -> throw com.arflix.tv.network.IptvProviderRequestDeferredException()
            }
        }
        var rejected = false
        try { api.getChannels() } catch (_: com.arflix.tv.network.IptvProviderRequestDeferredException) { rejected = true }
        assertTrue("An incomplete catalog must be reported as a failure", rejected)
    }

    @Test
    fun `handshake skips HTML responses and falls through to stalker_portal`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url == "$PORTAL/server/load.php?type=stb&action=handshake" -> "<html>404</html>"
                url.contains("/stalker_portal/server/load.php?type=stb&action=handshake") ->
                    """{ "js": { "token": "T" } }"""
                else -> null
            }
        }

        val ok = api.handshake()

        assertTrue(ok)
        assertEquals(
            listOf(
                "$PORTAL/server/load.php?type=stb&action=handshake",
                "$PORTAL/stalker_portal/server/load.php?type=stb&action=handshake"
            ),
            requests
        )
    }

    @Test
    fun `handshake fails when no token is present`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=handshake")) """{ "js": {} }""" else null
        }

        val ok = api.handshake()

        assertFalse(ok)
        assertTrue("all requests must be handshake probes", requests.all { it.contains("action=handshake") })
    }

    @Test
    fun `handshake resolves API at root when portal URL ends with slash c`() = runTest {
        val requests = mutableListOf<String>()
        // Typical /c/ portals answer the handshake with an HTML 404 page under /c/,
        // the real API lives at the root.
        val api = stubApi(portal = "$PORTAL/c", requests = requests) { url ->
            when {
                url == "$PORTAL/c/server/load.php?type=stb&action=handshake" ->
                    "<!DOCTYPE html><html><body>404 Not Found</body></html>"
                url == "$PORTAL/server/load.php?type=stb&action=handshake" ->
                    """{ "js": { "token": "ROOT" } }"""
                else -> null
            }
        }

        val ok = api.handshake()

        assertTrue(ok)
        assertEquals(
            listOf(
                "$PORTAL/c/server/load.php?type=stb&action=handshake",
                "$PORTAL/server/load.php?type=stb&action=handshake"
            ),
            requests
        )
    }

    @Test
    fun `handshake skips empty responses for slash c portal and falls through to stalker_portal`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(portal = "$PORTAL/c", requests = requests) { url ->
            when {
                url == "$PORTAL/c/server/load.php?type=stb&action=handshake" -> ""
                url == "$PORTAL/server/load.php?type=stb&action=handshake" -> "<html>404</html>"
                url == "$PORTAL/stalker_portal/server/load.php?type=stb&action=handshake" ->
                    """{ "js": { "token": "SP" } }"""
                else -> null
            }
        }

        val ok = api.handshake()

        assertTrue(ok)
        assertEquals(
            listOf(
                "$PORTAL/c/server/load.php?type=stb&action=handshake",
                "$PORTAL/server/load.php?type=stb&action=handshake",
                "$PORTAL/stalker_portal/server/load.php?type=stb&action=handshake"
            ),
            requests
        )
    }

    @Test
    fun `handshake resolves API for slash stalker_portal slash c portal URL`() = runTest {
        val requests = mutableListOf<String>()
        // /stalker_portal/c/ style URL: the UI path must be stripped to /stalker_portal.
        val api = stubApi(portal = "$PORTAL/stalker_portal/c", requests = requests) { url ->
            when {
                url == "$PORTAL/stalker_portal/c/server/load.php?type=stb&action=handshake" ->
                    "<!DOCTYPE html><html><body>404 Not Found</body></html>"
                url == "$PORTAL/stalker_portal/server/load.php?type=stb&action=handshake" ->
                    """{ "js": { "token": "SPC" } }"""
                else -> null
            }
        }

        val ok = api.handshake()

        assertTrue(ok)
        assertEquals(
            listOf(
                "$PORTAL/stalker_portal/c/server/load.php?type=stb&action=handshake",
                "$PORTAL/stalker_portal/server/load.php?type=stb&action=handshake"
            ),
            requests
        )
    }

    @Test
    fun `handshake does not stop probing on HTML 404 without token`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url == "$PORTAL/server/load.php?type=stb&action=handshake" ->
                    "<!DOCTYPE html><html><body>404 Not Found</body></html>"
                url == "$PORTAL/stalker_portal/server/load.php?type=stb&action=handshake" ->
                    """{ "js": { "token": "LATE" } }"""
                else -> null
            }
        }

        val ok = api.handshake()

        assertTrue(ok)
        assertTrue(requests.any { it.contains("/stalker_portal/") })
    }

    @Test
    fun `channel pagination stops and deduplicates when a portal repeats the first page`() = runTest {
        val requests = mutableListOf<String>()
        val repeatedPage = """{
            "js": {
                "data": [
                    { "id": 1, "name": "One", "cmd": "ffmpeg http://one", "tv_genre_id": "1" },
                    { "id": 2, "name": "Two", "cmd": "ffmpeg http://two", "tv_genre_id": "1" }
                ],
                "total_items": 6,
                "max_page_items": 2
            }
        }"""
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_genres") ->
                    """{ "js": [{ "id": "1", "title": "News" }] }"""
                url.contains("action=get_all_channels") -> repeatedPage
                else -> null
            }
        }

        val channels = api.getChannels()

        assertEquals(listOf("1", "2"), channels.map { it.id })
        assertEquals(2, requests.count { it.contains("action=get_all_channels") })
        assertFalse(requests.any { it.contains("p=3") })
    }

    @Test
    fun `getEpg builds url without date param when date is blank`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_simple_data_table")) {
                """{ "js": [{ "ch_id": "1", "name": "News", "start_timestamp": "1000", "stop_timestamp": "2000" }] }"""
            } else null
        }

        api.getEpg()

        // get_simple_data_table returned data, so no fallback request is made.
        assertEquals(
            listOf("$PORTAL/server/load.php?type=epg&action=get_simple_data_table&ch_id=all&JsHttpRequest=1-xml"),
            requests
        )
    }

    @Test
    fun `getEpg appends encoded date param when date is set`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_simple_data_table")) {
                """{ "js": [{ "ch_id": "1", "name": "News", "start_timestamp": "1000", "stop_timestamp": "2000" }] }"""
            } else null
        }

        api.getEpg(date = "2026-08-29")

        assertEquals(
            listOf(
                "$PORTAL/server/load.php?type=epg&action=get_simple_data_table&ch_id=all&date=2026-08-29&JsHttpRequest=1-xml"
            ),
            requests
        )
    }

    @Test
    fun `getEpg parses standard field names`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_simple_data_table")) {
                """{ "js": [
                    { "ch_id": "1", "name": "News", "descr": "Daily news", "start_timestamp": "1000", "stop_timestamp": "2000" }
                ] }"""
            } else null
        }

        val programs = api.getEpg()

        assertEquals(1, programs.size)
        assertEquals("1", programs[0].chId)
        assertEquals("News", programs[0].name)
        assertEquals("Daily news", programs[0].descr)
        assertEquals("1000", programs[0].startTimestamp)
        assertEquals("2000", programs[0].stopTimestamp)
    }

    @Test
    fun `getEpg parses alternate field names from other portal software`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_simple_data_table")) {
                """{ "js": [
                    { "channel_id": "7", "title": "Movie", "description": "A film", "start": "1500", "end": "3000" }
                ] }"""
            } else null
        }

        val programs = api.getEpg()

        assertEquals(1, programs.size)
        assertEquals("7", programs[0].chId)
        assertEquals("Movie", programs[0].name)
        assertEquals("A film", programs[0].descr)
        assertEquals("1500", programs[0].startTimestamp)
        assertEquals("3000", programs[0].stopTimestamp)
    }

    @Test
    fun `getEpg bounded mode keeps only the nearest current and future programs per channel`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_simple_data_table")) {
                """{ "js": [
                    { "ch_id": "1", "name": "Past", "start_timestamp": "800", "stop_timestamp": "900" },
                    { "ch_id": "1", "name": "Now", "start_timestamp": "900", "stop_timestamp": "1100" },
                    { "ch_id": "1", "name": "Next", "start_timestamp": "1100", "stop_timestamp": "1200" },
                    { "ch_id": "1", "name": "Later", "start_timestamp": "1200", "stop_timestamp": "1300" },
                    { "ch_id": "1", "name": "Too far", "start_timestamp": "1300", "stop_timestamp": "1400" },
                    { "ch_id": "2", "name": "Other channel", "start_timestamp": "900", "stop_timestamp": "1100" }
                ] }"""
            } else null
        }

        val programs = api.getEpg(notBeforeEpochSeconds = 1_000L, maxProgramsPerChannel = 3)

        assertEquals(listOf("Now", "Next", "Later"), programs.filter { it.chId == "1" }.map { it.name })
        assertEquals(listOf("Other channel"), programs.filter { it.chId == "2" }.map { it.name })
        assertEquals(1, requests.size)
    }

    @Test
    fun `getEpg falls back to get_epg_info when get_simple_data_table is malformed`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_simple_data_table") -> "<html>not json</html>"
                url.contains("action=get_epg_info") -> """{ "js": [] }"""
                else -> null
            }
        }

        val programs = api.getEpg()

        assertTrue(programs.isEmpty())
        assertEquals(2, requests.size)
        assertTrue(requests[0].contains("action=get_simple_data_table"))
        assertTrue(requests[1].contains("action=get_epg_info"))
    }

    @Test
    fun `getEpg falls back to get_epg_info when js is null or absent`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_simple_data_table") -> """{ "js": null }"""
                url.contains("action=get_epg_info") -> """{ "js": null }"""
                else -> null
            }
        }

        val programs = api.getEpg()

        assertTrue(programs.isEmpty())
        assertEquals(2, requests.size)
    }

    @Test
    fun `getEpg get_epg_info fallback parses a flat js list like get_simple_data_table`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_simple_data_table") -> """{ "js": [] }"""
                url.contains("action=get_epg_info") ->
                    """{ "js": [{ "ch_id": "1", "name": "News", "start_timestamp": "1000", "stop_timestamp": "2000" }] }"""
                else -> null
            }
        }

        val programs = api.getEpg()

        assertEquals(1, programs.size)
        assertEquals("1", programs[0].chId)
        assertEquals("News", programs[0].name)
    }

    @Test
    fun `getEpg get_epg_info fallback parses the confirmed data-wrapped per-channel shape`() = runTest {
        // Confirmed on-device response shape: {"js":{"data":{"<ch_id>":[...]}}}.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_simple_data_table") -> """{ "js": [] }"""
                url.contains("action=get_epg_info") ->
                    """{ "js": { "data": {
                        "1359": [{ "ch_id": "1359", "name": "Beestenboel", "start_timestamp": "1788110700", "stop_timestamp": "1788113400" }]
                    } } }"""
                else -> null
            }
        }

        val programs = api.getEpg()

        assertEquals(1, programs.size)
        assertEquals("1359", programs[0].chId)
        assertEquals("Beestenboel", programs[0].name)
    }

    @Test
    fun `getEpg get_epg_info fallback returns empty list for the confirmed empty data-wrapped shape`() = runTest {
        // Confirmed on-device response shape when a portal has no programs: {"js":{"data":[]}}.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_simple_data_table") -> """{ "js": [] }"""
                url.contains("action=get_epg_info") -> """{ "js": { "data": [] } }"""
                else -> null
            }
        }

        val programs = api.getEpg()

        assertTrue(programs.isEmpty())
    }

    @Test
    fun `getEpg get_epg_info fallback parses a per-channel object shape`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_simple_data_table") -> """{ "js": [] }"""
                url.contains("action=get_epg_info") ->
                    """{ "js": {
                        "1": [{ "name": "News", "start_timestamp": "1000", "stop_timestamp": "2000" }],
                        "2": [{ "name": "Movie", "start_timestamp": "1500", "stop_timestamp": "3000" }]
                    } }"""
                else -> null
            }
        }

        val programs = api.getEpg()

        assertEquals(2, programs.size)
        // The per-channel object's key becomes ch_id since the program entry itself has none.
        assertEquals(setOf("1", "2"), programs.map { it.chId }.toSet())
        assertEquals(setOf("News", "Movie"), programs.map { it.name }.toSet())
    }

    @Test
    fun `getShortEpg builds url with ch_id and size`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_short_epg")) """{ "js": [] }""" else null
        }

        api.getShortEpg("1441855")

        assertEquals(
            listOf("$PORTAL/server/load.php?type=itv&action=get_short_epg&ch_id=1441855&size=10&JsHttpRequest=1-xml"),
            requests
        )
    }

    @Test
    fun `getShortEpg parses the confirmed real-world response shape (integer id field)`() = runTest {
        // Confirmed live on-device: js is a flat array directly (same container shape as
        // get_simple_data_table), but the "id" field is a number, not a string, on this
        // portal build - our model doesn't map "id" at all, so this must still parse fine.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_short_epg")) {
                """{ "js": [{
                    "id": 1788113700, "ch_id": "1441855",
                    "start_timestamp": 1788113700, "stop_timestamp": 1788118800,
                    "name": "Tatort: Roomservice", "descr": "Ein Krimi."
                }] }"""
            } else null
        }

        val programs = api.getShortEpg("1441855")

        assertEquals(1, programs.size)
        assertEquals("1441855", programs[0].chId)
        assertEquals("Tatort: Roomservice", programs[0].name)
        assertEquals("1788113700", programs[0].startTimestamp)
        assertEquals("1788118800", programs[0].stopTimestamp)
    }

    @Test
    fun `getShortEpg returns empty list for a channel with no programs`() = runTest {
        // Confirmed live: a group-placeholder channel with blank xmltv_id returns {"js":[]}.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_short_epg")) """{ "js": [] }""" else null
        }

        val programs = api.getShortEpg("679826")

        assertTrue(programs.isEmpty())
    }

    @Test
    fun `getShortEpg fills the requested channel id when the portal omits it`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_short_epg")) {
                """{ "js": [{ "name": "News", "start_timestamp": "1000", "stop_timestamp": "2000" }] }"""
            } else null
        }

        val programs = api.getShortEpg("42")

        assertEquals("42", programs.single().chId)
    }

    @Test
    fun `getShortEpg returns empty list on malformed response instead of throwing`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_short_epg")) "<html>error</html>" else null
        }

        val programs = api.getShortEpg("1")

        assertTrue(programs.isEmpty())
    }

    @Test
    fun `channels of a portal that needs no temporary link keep their finished address`() = runTest {
        // Measured portal: all 21295 channels report use_http_tmp_link 0 and publish a
        // complete address, so playback must not ask create_link for one.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_genres") -> """{ "js": [{ "id": "1", "title": "News" }] }"""
                url.contains("action=get_all_channels") -> """{
                    "js": {
                        "data": [
                            {
                                "id": 1,
                                "name": "Direct",
                                "cmd": "ffmpeg http://portal.example.com/play/live.php?stream=1&extension=ts",
                                "tv_genre_id": "1",
                                "use_http_tmp_link": "0",
                                "wowza_tmp_link": "0",
                                "flussonic_tmp_link": "0"
                            }
                        ],
                        "total_items": 1,
                        "max_page_items": 1
                    }
                }"""
                else -> null
            }
        }

        val channels = api.getChannels()

        assertEquals(
            "http://portal.example.com/play/live.php?stream=1&extension=ts",
            channels.single().streamUrl
        )
        assertTrue(channels.single().stalkerDirectStream)
    }

    @Test
    fun `channels of a portal that asks for a temporary link keep the raw command`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_genres") -> """{ "js": [] }"""
                url.contains("action=get_all_channels") -> """{
                    "js": {
                        "data": [
                            {
                                "id": 7,
                                "name": "Placeholder",
                                "cmd": "ffmpeg http://localhost/ch/7_",
                                "use_http_tmp_link": 1
                            }
                        ],
                        "total_items": 1,
                        "max_page_items": 1
                    }
                }"""
                else -> null
            }
        }

        val channels = api.getChannels()

        assertEquals("ffmpeg http://localhost/ch/7_", channels.single().streamUrl)
        assertFalse(channels.single().stalkerDirectStream)
    }

    @Test
    fun `channels of a portal that states nothing keep the raw command`() = runTest {
        // No flag at all is not a statement, so the create_link round trip stays.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_genres") -> """{ "js": [] }"""
                url.contains("action=get_all_channels") -> """{
                    "js": {
                        "data": [
                            { "id": 3, "name": "Unknown", "cmd": "ffmpeg http://host/live/3" }
                        ],
                        "total_items": 1,
                        "max_page_items": 1
                    }
                }"""
                else -> null
            }
        }

        val channels = api.getChannels()

        assertEquals("ffmpeg http://host/live/3", channels.single().streamUrl)
        assertFalse(channels.single().stalkerDirectStream)
    }

    @Test
    fun `bare URL does not erase temporary link requirements`() = runTest {
        val cases = listOf(
            "" to false,
            "\"use_http_tmp_link\": 1," to false,
            "\"use_http_tmp_link\": \"1\"," to false,
            "\"use_http_tmp_link\": \"\"," to false,
            "\"use_http_tmp_link\": 0, \"wowza_tmp_link\": 1," to false,
            "\"use_http_tmp_link\": 0, \"flussonic_tmp_link\": \"1\"," to false,
            "\"use_http_tmp_link\": 0," to true,
            "\"use_http_tmp_link\": \"0\"," to true,
        )
        for ((flags, direct) in cases) {
            val api = stubApi(requests = mutableListOf()) { url ->
                when {
                    url.contains("action=get_genres") -> """{"js": []}"""
                    url.contains("action=get_all_channels") -> """{"js": {
                        "data": [{$flags "id": 1, "name": "News", "cmd": "https://portal.test/live/one"}],
                        "total_items": 1, "max_page_items": 1
                    }}"""
                    else -> null
                }
            }
            val channel = api.getChannels().single().copy(id = "stalker:stalker1:1")
            assertEquals(flags, "https://portal.test/live/one", channel.streamUrl)
            assertEquals(flags, direct, channel.stalkerDirectStream)
            assertEquals(flags, direct, com.arflix.tv.data.repository.StalkerPortalSupport
                .canPlayDirectLiveStream(channel, channel.streamUrl, isCatchup = false))
            assertFalse(com.arflix.tv.data.repository.StalkerPortalSupport
                .canPlayDirectLiveStream(channel, channel.streamUrl, isCatchup = true))
        }
    }

    @Test
    fun `an empty tmp link flag does not take the whole channel page down`() = runTest {
        // Portals have been seen sending "" where a number belongs; a numeric field
        // would abort parsing and lose every channel of the page.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_genres") -> """{ "js": [] }"""
                url.contains("action=get_all_channels") -> """{
                    "js": {
                        "data": [
                            { "id": 5, "name": "Odd", "cmd": "ffmpeg http://host/live/5", "use_http_tmp_link": "" }
                        ],
                        "total_items": 1,
                        "max_page_items": 1
                    }
                }"""
                else -> null
            }
        }

        val channels = api.getChannels()

        assertEquals(listOf("5"), channels.map { it.id })
        assertEquals("ffmpeg http://host/live/5", channels.single().streamUrl)
    }

    @Test
    fun `resolveStreamUrl strips whichever command word the portal used`() = runTest {
        // A third portal writes "auto http://..."; only "ffmpeg " used to be removed,
        // which left an address no player can open.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=create_link")) {
                """{ "js": { "cmd": "auto http://host/live.ts?channelId=9" } }"""
            } else null
        }

        val resolved = api.resolveStreamUrl("ffmpeg http://host/ch/9_")

        assertEquals("http://host/live.ts?channelId=9", resolved)
    }

    @Test
    fun `resolveStreamUrl returns null when the portal answers without a command`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=create_link")) """{ "js": { "id": null } }""" else null
        }

        assertNull(api.resolveStreamUrl("ffmpeg http://host/ch/9_"))
    }

    // ── VOD ───────────────────────────────────────────────────────────────

    @Test
    fun `searchVod asks the portal instead of walking the catalog`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_ordered_list") -> """
                    {"js":{"total_items":1,"max_page_items":14,"data":[
                      {"id":"42","name":"Dune (2021)","cmd":"/media/dune.mpg","year":"2021","tmdb_id":"438631"}
                    ]}}
                """.trimIndent()
                else -> null
            }
        }

        val items = api.searchVod("Dune")!!

        assertEquals(1, items.size)
        assertEquals("Dune (2021)", items.first().name)
        assertEquals("438631", items.first().tmdbId)
        assertEquals(1, requests.size)
        assertTrue(requests.single().contains("type=vod&action=get_ordered_list"))
        assertTrue(requests.single().contains("search=Dune"))
        assertTrue(requests.single().contains("category=0"))
        // Matching must never cost a link: create_link happens at playback only.
        assertTrue(requests.none { it.contains("action=create_link") })
    }

    @Test
    fun `searchVod asks every category and lets the portal sort by name`() = runTest {
        // Measured against a working portal: a full client asks
        // category=0&sortby=name and gets its matches. category=* is the
        // category list's word for "all" and get_ordered_list does not take it;
        // sortby=added buries a match behind everything added since.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_ordered_list") -> """
                    {"js":{"total_items":1,"max_page_items":14,"data":[
                      {"id":"42","name":"Dune (2021)","cmd":"/media/dune.mpg"}
                    ]}}
                """.trimIndent()
                else -> null
            }
        }

        api.searchVod("Dune")

        val url = requests.single()
        assertTrue(url.contains("&category=0&"))
        assertTrue(url.contains("&sortby=name&"))
        assertFalse(url.contains("category=*"))
        assertFalse(url.contains("sortby=added"))
    }

    @Test
    fun `searchSeries asks every category and lets the portal sort by name`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_ordered_list") -> """
                    {"js":{"total_items":1,"max_page_items":14,"data":[
                      {"id":"7","name":"Breaking Bad","cmd":"/media/bb"}
                    ]}}
                """.trimIndent()
                else -> null
            }
        }

        api.searchSeries("Breaking Bad")

        val url = requests.single()
        assertTrue(url.contains("&category=0&"))
        assertTrue(url.contains("&sortby=name&"))
        assertFalse(url.contains("category=*"))
        assertFalse(url.contains("sortby=added"))
    }

    @Test
    fun `getSeasons asks for the seasons of one show without imposing an order`() = runTest {
        // A show addressed by movie_id needs no sorting at all - a full client
        // sends none, and a build that reads sortby as a filter would answer
        // this call with nothing.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("movie_id=7") -> """
                    {"js":{"total_items":1,"max_page_items":14,"data":[
                      {"id":"71","name":"Season 1","cmd":"/media/bb/s1","series":[1,2]}
                    ]}}
                """.trimIndent()
                else -> null
            }
        }

        api.getSeasons("7")

        val url = requests.single()
        assertTrue(url.contains("&movie_id=7"))
        assertFalse(url.contains("sortby"))
    }

    @Test
    fun `searchVod never asks for more pages than its cap allows`() = runTest {
        // A portal that reports a total far beyond what we page for must not
        // pull the whole catalogue down: the cap is what keeps a search a
        // search. Sorted by name, the matches for one term stay inside it.
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            val page = Regex("&p=(\\d+)").find(url)?.groupValues?.get(1) ?: "1"
            when {
                url.contains("action=get_ordered_list") -> """
                    {"js":{"total_items":104021,"max_page_items":14,"data":[
                      {"id":"$page","name":"Hulk $page","cmd":"/media/hulk$page.mpg"}
                    ]}}
                """.trimIndent()
                else -> null
            }
        }

        api.searchVod("Hulk")

        assertEquals(StalkerApi.DEFAULT_VOD_SEARCH_PAGES, requests.size)
        assertTrue(requests.any { it.contains("&p=1&") })
        assertTrue(requests.none { it.contains("&p=${StalkerApi.DEFAULT_VOD_SEARCH_PAGES + 1}&") })
    }

    @Test
    fun `searchVod pages until the reported total is covered`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("&p=1") -> """
                    {"js":{"total_items":3,"max_page_items":2,"data":[
                      {"id":"1","name":"Alien","cmd":"/a.mpg"},
                      {"id":"2","name":"Aliens","cmd":"/b.mpg"}
                    ]}}
                """.trimIndent()
                url.contains("&p=2") -> """
                    {"js":{"total_items":3,"max_page_items":2,"data":[
                      {"id":"3","name":"Alien 3","cmd":"/c.mpg"}
                    ]}}
                """.trimIndent()
                else -> null
            }
        }

        val items = api.searchVod("Alien")!!

        assertEquals(listOf("Alien", "Aliens", "Alien 3"), items.map { it.name })
        assertEquals(2, requests.size)
    }

    @Test
    fun `searchVod stops when a portal ignores paging and repeats itself`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            if (url.contains("action=get_ordered_list")) {
                """
                {"js":{"total_items":999,"max_page_items":1,"data":[
                  {"id":"7","name":"Heat","cmd":"/heat.mpg"}
                ]}}
                """.trimIndent()
            } else {
                null
            }
        }

        val items = api.searchVod("Heat")!!

        assertEquals(1, items.size)
        // Page 2 repeats page 1 - no new ids means stop, not 999 requests.
        assertEquals(2, requests.size)
    }

    @Test
    fun `searchVod reports an HTML 200 answer as a failure, not as no results`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { "<html><body>Not found</body></html>" }

        // null, not emptyList: the caller caches answers, and a broken reply
        // cached as "no such film" hides the title until the entry expires.
        assertNull(api.searchVod("Dune"))
    }

    @Test
    fun `searchVod skips entries without a playable cmd`() = runTest {
        val api = stubApi(requests = mutableListOf()) {
            """
            {"js":{"total_items":2,"max_page_items":14,"data":[
              {"id":"1","name":"No Command"},
              {"id":"2","name":"Playable","cmd":"/ok.mpg"}
            ]}}
            """.trimIndent()
        }

        assertEquals(listOf("Playable"), api.searchVod("x")!!.map { it.name })
    }

    @Test
    fun `searchVod ignores a blank query without touching the portal`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { null }

        assertTrue(api.searchVod("   ")!!.isEmpty())
        assertTrue(requests.isEmpty())
    }

    @Test
    fun `resolveVodStreamUrl exchanges the cmd for a playable url`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("type=vod&action=create_link") ->
                    """{"js":{"cmd":"ffmpeg http://cdn.example.com/movie.mp4"}}"""
                else -> null
            }
        }

        val url = api.resolveVodStreamUrl("/media/file_1.mpg")

        assertEquals("http://cdn.example.com/movie.mp4", url)
        assertTrue(requests.single().contains("cmd=%2Fmedia%2Ffile_1.mpg"))
    }

    @Test
    fun `resolveVodStreamUrl returns null when the portal answers without a link`() = runTest {
        val api = stubApi(requests = mutableListOf()) { """{"js":{"cmd":""}}""" }

        assertNull(api.resolveVodStreamUrl("/media/file.mpg"))
    }

    @Test
    fun `sanitizePlaybackCommand strips the player hint but keeps bare urls`() {
        assertEquals(
            "http://cdn.example.com/a.mp4",
            StalkerApi.sanitizePlaybackCommand("ffmpeg http://cdn.example.com/a.mp4")
        )
        assertEquals(
            "http://cdn.example.com/a.mp4",
            StalkerApi.sanitizePlaybackCommand("auto http://cdn.example.com/a.mp4")
        )
        assertEquals(
            "http://cdn.example.com/a.mp4",
            StalkerApi.sanitizePlaybackCommand("  http://cdn.example.com/a.mp4  ")
        )
        assertNull(StalkerApi.sanitizePlaybackCommand(""))
        assertNull(StalkerApi.sanitizePlaybackCommand(null))
        assertNull(StalkerApi.sanitizePlaybackCommand("   "))
        // A lone token carries no hint to strip and is returned unchanged; the
        // caller drops it because it is not an http(s) URL.
        assertEquals("ffmpeg", StalkerApi.sanitizePlaybackCommand("ffmpeg   "))
    }

    // ── Series ────────────────────────────────────────────────────────────

    @Test
    fun `searchSeries asks the portal for shows, not for the whole catalog`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("action=get_ordered_list") -> """
                    {"js":{"total_items":1,"max_page_items":14,"data":[
                      {"id":"7","name":"Breaking Bad","cmd":"/media/bb","year":"2008","tmdb_id":"1396"}
                    ]}}
                """.trimIndent()
                else -> null
            }
        }

        val items = api.searchSeries("Breaking Bad")!!

        assertEquals(1, items.size)
        assertEquals("Breaking Bad", items.first().name)
        assertEquals("1396", items.first().tmdbId)
        assertEquals(1, requests.size)
        assertTrue(requests.single().contains("type=series&action=get_ordered_list"))
        assertTrue(requests.single().contains("search=Breaking"))
        assertTrue(requests.single().contains("category=0"))
        // Binding a show must never cost a link either.
        assertTrue(requests.none { it.contains("action=create_link") })
    }

    @Test
    fun `searchSeries reports an HTML 200 answer as a failure, not as no results`() = runTest {
        val api = stubApi(requests = mutableListOf()) { "<html>not a portal api</html>" }

        assertNull(api.searchSeries("Breaking Bad"))
    }

    @Test
    fun `searchSeries reports an empty result set as an empty list, not as a failure`() = runTest {
        val api = stubApi(requests = mutableListOf()) {
            """{"js":{"total_items":0,"max_page_items":14,"data":[]}}"""
        }

        // The portal answered and knows no such show. That is an answer, and it
        // has to stay distinguishable from a request that never got through.
        assertEquals(emptyList<StalkerApi.StalkerSeriesItem>(), api.searchSeries("Silo"))
    }

    @Test
    fun `getSeasons reports a failure as null rather than an empty season list`() = runTest {
        val api = stubApi(requests = mutableListOf()) { "<html>gateway timeout</html>" }

        assertNull(api.getSeasons("7"))
    }

    @Test
    fun `searchSeries ignores a blank query without touching the portal`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { error("must not be called") }

        assertTrue(api.searchSeries("   ")!!.isEmpty())
        assertTrue(requests.isEmpty())
    }

    @Test
    fun `getSeasons asks by movie_id and reads the episode numbers`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { url ->
            when {
                url.contains("movie_id=7") -> """
                    {"js":{"total_items":2,"max_page_items":14,"data":[
                      {"id":"71","name":"Season 1","cmd":"/media/bb/s1","series":[1,2,3]},
                      {"id":"72","name":"Season 2","cmd":"/media/bb/s2","series":[1,2]}
                    ]}}
                """.trimIndent()
                else -> null
            }
        }

        val seasons = api.getSeasons("7")!!

        assertEquals(listOf("Season 1", "Season 2"), seasons.map { it.name })
        assertEquals(listOf(1, 2, 3), StalkerApi.episodeNumbers(seasons.first().series))
        assertEquals(1, requests.size)
        assertTrue(requests.single().contains("type=series&action=get_ordered_list"))
        assertTrue(requests.single().contains("movie_id=7"))
    }

    @Test
    fun `getSeasons stops when a portal ignores paging and repeats itself`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) {
            // No total reported and the same entry on every page - the guard
            // against portals that ignore `p`.
            """{"js":{"data":[{"id":"71","name":"Season 1","cmd":"/media/s1","series":[1]}]}}"""
        }

        val seasons = api.getSeasons("7")!!

        assertEquals(1, seasons.size)
        assertEquals(2, requests.size)
    }

    @Test
    fun `getSeasons ignores a blank series id without touching the portal`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) { error("must not be called") }

        assertTrue(api.getSeasons("  ")!!.isEmpty())
        assertTrue(requests.isEmpty())
    }

    @Test
    fun `episodeNumbers accepts numbers and numeric strings and drops the rest`() {
        val gson = com.google.gson.Gson()
        fun parse(json: String) = StalkerApi.episodeNumbers(
            gson.fromJson(json, com.google.gson.JsonElement::class.java)
        )

        assertEquals(listOf(1, 2, 3), parse("[3,1,2]"))
        assertEquals(listOf(1, 2), parse("""["1","2"]"""))
        // Portals pad the array with labels or nulls; those must not take the
        // whole season down with them.
        assertEquals(listOf(4), parse("""[null,"extras",4]"""))
        // A show entry has no season list at all, and some builds send "".
        assertTrue(parse("\"\"").isEmpty())
        assertTrue(parse("[]").isEmpty())
        assertTrue(StalkerApi.episodeNumbers(null).isEmpty())
    }

    @Test
    fun `resolveVodStreamUrl passes the episode number as the series parameter`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) {
            """{"js":{"cmd":"ffmpeg http://cdn.example.com/bb-s2e5.mp4"}}"""
        }

        val url = api.resolveVodStreamUrl("/media/bb/s2", series = 5)

        assertEquals("http://cdn.example.com/bb-s2e5.mp4", url)
        assertTrue(requests.single().contains("action=create_link"))
        assertTrue(requests.single().contains("series=5"))
    }

    @Test
    fun `resolveVodStreamUrl omits the series parameter for a movie`() = runTest {
        val requests = mutableListOf<String>()
        val api = stubApi(requests = requests) {
            """{"js":{"cmd":"http://cdn.example.com/movie.mp4"}}"""
        }

        api.resolveVodStreamUrl("/media/movie.mpg")

        assertFalse(requests.single().contains("series="))
    }
}
