package com.arflix.tv.data.repository.simkl

import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.util.CatalogUrlParser
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Test
import java.net.URLDecoder
import java.security.MessageDigest
import okio.ByteString.Companion.toByteString

class SimklListsClientTest {
    @Test fun `SIMKL links accept real owner URLs and canonical numeric links only`() {
        for (url in listOf("https://simkl.com/5/list/14462/best-mindfucks-tv-shows/?sort=position", "simkl.com/lists/14462", "https://www.simkl.com/5/list/14462")) {
            assertEquals(14462L, CatalogUrlParser.parseSimkl(url)?.id)
            assertEquals(CatalogSourceType.SIMKL, CatalogUrlParser.detectSource(url))
        }
        for (url in listOf("https://simkl.com/5/tv/", "https://simkl.com/lists/official", "https://simkl.com/lists/0", "https://simkl.com.evil.test/5/list/14462", "https://evil.simkl.com/5/list/14462", "https://simkl.com@evil.test/5/list/14462")) assertNull(url, CatalogUrlParser.parseSimkl(url))
    }

    @Test fun `V2 device flow uses fresh S256 PKCE and checks write scope`() = runTest {
        withServer { server, api ->
            server.enqueue(json("""{"device_code":"private-device-code","user_code":"ABCD-EFGH","verification_uri":"https://simkl.com/pin","verification_uri_complete":"https://simkl.com/pin?user_code=ABCD-EFGH","expires_in":900,"interval":5}"""))
            val code = api.startDevice()
            val start = server.takeRequest()
            assertEquals("POST", start.method)
            assertEquals("/oauth2/device", start.requestUrl!!.encodedPath)
            assertEquals("arvio", start.requestUrl!!.queryParameter("app-name"))
            val fields = fields(start.body.readUtf8())
            assertEquals("media:read media:write", fields["scope"])
            assertEquals("S256", fields["code_challenge_method"])
            assertEquals(MessageDigest.getInstance("SHA-256").digest(code.verifier.toByteArray()).toByteString().base64Url().trimEnd('='), fields["code_challenge"])
            server.enqueue(json("""{"access_token":"simkl_at_test","refresh_token":"simkl_rt_test","token_type":"Bearer","expires_in":604800,"scope":"media:read media:write"}"""))
            val grant = api.poll(code)
            assertEquals("simkl_at_test", grant.accessToken)
            val poll = fields(server.takeRequest().body.readUtf8())
            assertEquals(code.verifier, poll["code_verifier"])
            assertEquals("urn:ietf:params:oauth:grant-type:device_code", poll["grant_type"])
        }
    }

    @Test fun `200 premium_only and HTTP auth and privacy errors never become empty success`() = runTest {
        for ((status, reason) in listOf(200 to "premium_only", 403 to "oauth2_token_required", 403 to "private_list", 404 to "url_failed", 401 to "user_token_required", 429 to "user_limit_exceeded")) {
            withServer { server, api ->
                server.enqueue(json("""{"error":"$reason","item":{"title":"Upgrade"}}""").setResponseCode(status))
                val error = runCatching { api.list(14462, "simkl_at_test") }.exceptionOrNull() as SimklListsException
                assertEquals(reason, error.reason)
                assertEquals(status, error.status)
            }
        }
    }

    @Test fun `list pagination preserves owner order and maps movie TV and anime string IDs`() = runTest {
        withServer { server, api ->
            server.enqueue(json(list("""{"title":"Dark","type":"tv","ids":{"tmdb":"70523","imdb":"tt5753856"}},{"title":"Perfect Blue","type":"anime","anime_type":"movie","ids":{"tmdb":10494}}""", 2)))
            server.enqueue(json(list("""{"title":"Inception","type":"movie","ids":{"tmdb":"27205"}}""", 2)))
            val list = api.list(14462, "simkl_at_test")
            assertEquals(listOf("Dark", "Perfect Blue", "Inception"), list.items.map { it.title })
            assertEquals(listOf(70523, 10494, 27205), list.items.map { it.tmdb })
            assertEquals("movie", list.items[1].animeType)
            assertEquals("https://simkl.com/5/list/14462", list.url)
            for (page in 1..2) {
                val request = server.takeRequest()
                assertEquals(page.toString(), request.requestUrl!!.queryParameter("page"))
                assertNull(request.requestUrl!!.queryParameter("sort"))
                assertEquals("Bearer simkl_at_test", request.getHeader("Authorization"))
            }
        }
    }

    @Test fun `list index uses top posters and includes followed and shared lists without loading contents`() = runTest {
        withServer { server, api ->
            server.enqueue(json("""{"pagination":{"total_pages":1},"lists":[{"id":14462,"name":"Mindfucks","media_type":"tv","user":{"id":5},"counts":{"items":333,"likes":123},"top_items":[{"poster":"63/6339166c5664ac055"}]}]}"""))
            val lists = api.userLists(5, "simkl_at_test")
            assertEquals(333, lists.single().count)
            assertEquals("https://simkl.in/posters/63/6339166c5664ac055_m.webp", lists.single().posters.single())
            val request = server.takeRequest()
            assertEquals("true", request.requestUrl!!.queryParameter("followed"))
            assertEquals("true", request.requestUrl!!.queryParameter("collaborants"))
            assertEquals(1, server.requestCount)
        }
    }

    @Test fun `official discovery previews remain usable when the index exceeds the paging ceiling`() = runTest {
        withServer { server, api ->
            repeat(2) { server.enqueue(json("""{"pagination":{"total_pages":22},"lists":[{"id":14462,"name":"Mindfucks","media_type":"tv","user":{"id":5}}]}""")) }
            assertEquals(1, api.officialLists("simkl_at_test").size)
            assertEquals("updated", server.takeRequest().requestUrl!!.queryParameter("sort"))
            assertEquals("popularity", server.takeRequest().requestUrl!!.queryParameter("sort"))
            assertEquals(2, server.requestCount)
        }
    }

    @Test fun `refresh uses correct grant and refuses downgraded tracking scope`() = runTest {
        withServer { server, api ->
            server.enqueue(json("""{"access_token":"simkl_at_new","refresh_token":"simkl_rt_new","token_type":"bearer","expires_in":604800,"scope":"media:read"}"""))
            assertEquals("invalid_scope", (runCatching { api.refresh("private-refresh") }.exceptionOrNull() as SimklListsException).reason)
            val request = fields(server.takeRequest().body.readUtf8())
            assertEquals("refresh_token", request["grant_type"])
            assertEquals("private-refresh", request["refresh_token"])
        }
    }

    private fun json(body: String) = MockResponse().setHeader("Content-Type", "application/json").setBody(body)
    private fun list(items: String, pages: Int) = """{"id":14462,"name":"Mindfucks","media_type":"tv","user":{"id":5},"counts":{"items":3},"pagination":{"total_pages":$pages},"items":[$items]}"""
    private fun fields(body: String) = body.split('&').associate { field -> field.substringBefore('=') to URLDecoder.decode(field.substringAfter('='), "UTF-8") }
    private suspend fun withServer(block: suspend (MockWebServer, SimklListsClient) -> Unit) {
        val server = MockWebServer()
        server.start()
        try { block(server, SimklListsClient(OkHttpClient(), "public-test-client", server.url("/"))) } finally { server.shutdown() }
    }
}
