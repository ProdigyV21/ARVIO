package com.arflix.tv.server

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject

/** org.json is part of the Android framework, so this runs under Robolectric. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = android.app.Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class PhoneInputServerTest {

    private val texts = PhoneInputServer.Texts("hint", "Send", "Sent", "Not sent", "Expired")
    private var received: List<String>? = null
    private val server = PhoneInputServer.start(
        title = "Add addon </script><b>",
        fields = listOf(
            PhoneInputServer.Field("URL", "https://example.com"),
            PhoneInputServer.Field("Key", "secret-value", secret = true),
            PhoneInputServer.Field("EPG", GUIDES, multiline = true),
        ),
        texts = texts,
    ) { received = it }

    @After fun stop() {
        server?.stop()
    }

    private fun base() = "http://127.0.0.1:${server!!.listeningPort}"

    private fun request(path: String, body: String? = null): Pair<Int, String> {
        val connection = URL(base() + path).openConnection() as HttpURLConnection
        if (body != null) {
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/json")
            connection.outputStream.use { it.write(body.toByteArray()) }
        }
        val code = connection.responseCode
        val text = (if (code < 400) connection.inputStream else connection.errorStream)?.bufferedReader()?.readText().orEmpty()
        return code to text
    }

    @Test fun refusesRequestsWithoutTheToken() {
        assertNotNull(server)
        assertEquals(403, request("/").first)
        assertEquals(403, request("/?t=wrong").first)
        assertEquals(403, request("/submit?t=wrong", """{"values":["x","y"]}""").first)
        assertEquals(null, received)
    }

    @Test fun pageShowsTheFieldsButNeverASecretValue() {
        val (code, page) = request("/?t=${server!!.token}")
        assertEquals(200, code)
        assertTrue(page.contains("example.com"))
        assertFalse(page.contains("secret-value"))
        // The title is data inside a <script>: it must not be able to close it.
        assertFalse(page.contains("</script><b>"))
        val config = JSONObject(page.lineSequence().first { it.startsWith("const C=") }.removePrefix("const C=").removeSuffix(";"))
        val epg = config.getJSONArray("fields").getJSONObject(2)
        assertTrue(epg.getBoolean("multiline"))
        assertEquals(GUIDES, epg.getString("value"))
    }

    @Test fun submittedValuesReachTheDialogInFieldOrder() {
        val (code, _) = request("/submit?t=${server!!.token}", """{"values":["https://new.example","abc"]}""")
        assertEquals(200, code)
        assertEquals(listOf("https://new.example", "abc", ""), received)
    }

    @Test fun missingValuesArriveEmpty() {
        request("/submit?t=${server!!.token}", """{"values":["only-one"]}""")
        assertEquals(listOf("only-one", "", ""), received)
    }

    @Test fun multilineGuideSourcesArriveUnchanged() {
        val body = JSONObject().put("values", JSONArray(listOf("", "", GUIDES))).toString()
        assertEquals(200, request("/submit?t=${server!!.token}", body).first)
        assertEquals(listOf("", "", GUIDES), received)
    }

    companion object {
        private const val GUIDES = "https://guide.example/epg.xml\nhttps://backup.example/epg.xml"
    }
}
