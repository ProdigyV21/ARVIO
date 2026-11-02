package com.arflix.tv.network

import io.mockk.every
import io.mockk.mockk
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test

class TraktAvailabilityInterceptorTest {
    private fun response(status: Int, body: String, type: String = "application/json", host: String = "api.trakt.tv"): Response {
        val request = Request.Builder().url("https://$host/oauth/token").build()
        val original = Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(status)
            .message("fixture").header("Content-Type", type).header("Retry-After", "30")
            .body(body.toResponseBody(type.toMediaType())).build()
        val chain = mockk<Interceptor.Chain>()
        every { chain.request() } returns request
        every { chain.proceed(any()) } answers {
            val sent = firstArg<Request>()
            if (host == "api.trakt.tv") assertTrue(sent.header("User-Agent").orEmpty().startsWith("ARVIO/"))
            else assertNull(sent.header("User-Agent"))
            original.newBuilder().request(sent).build()
        }
        return TraktAvailabilityInterceptor().intercept(chain)
    }

    @Test fun edgeDenialCannotBeMistakenForRejectedCredentials() {
        for (status in listOf(401, 403)) {
            response(status, "<!DOCTYPE html><html>Blocked</html>", "text/html").use {
                assertEquals(503, it.code)
                assertEquals("30", it.header("Retry-After"))
                assertEquals("no-store", it.header("Cache-Control"))
                assertEquals("""{"error":"trakt_upstream_blocked"}""", it.body!!.string())
            }
        }
    }

    @Test fun missingHtmlContentTypeStillClassifiesTheDenial() {
        response(403, "<HTML lang=\"en\">Blocked</HTML>").use { assertEquals(503, it.code) }
    }

    @Test fun realApiErrorsAndRateLimitsArePreserved() {
        for (status in listOf(400, 401, 403, 404, 409, 410, 418, 429)) {
            response(status, """{"error":"fixture"}""").use {
                assertEquals(status, it.code)
                assertEquals("""{"error":"fixture"}""", it.body!!.string())
            }
        }
    }

    @Test fun anotherServiceAndSuccessfulResponsesAreUntouched() {
        response(403, "<html>Blocked</html>", "text/html", "example.com").use { assertEquals(403, it.code) }
        response(200, """{"user_code":"TEST"}""").use { assertEquals(200, it.code) }
    }
}
