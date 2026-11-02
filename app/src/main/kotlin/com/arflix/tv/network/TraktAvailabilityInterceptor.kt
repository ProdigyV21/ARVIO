package com.arflix.tv.network

import com.arflix.tv.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/** An edge HTML denial is not an OAuth rejection. Never let it invalidate a saved token. */
class TraktAvailabilityInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = if (original.url.host == "api.trakt.tv") original.newBuilder()
            .header("User-Agent", "ARVIO/${BuildConfig.VERSION_NAME} (+https://arvio.tv)")
            .build() else original
        val response = chain.proceed(request)
        if (response.request.url.host != "api.trakt.tv" || response.code !in setOf(401, 403)) return response
        val preview = response.peekBody(4096).string()
        val html = response.header("Content-Type").orEmpty().contains("text/html", ignoreCase = true) ||
            Regex("<!doctype\\s+html|<html(?:\\s|>)", RegexOption.IGNORE_CASE).containsMatchIn(preview)
        if (!html) return response
        val unavailable = response.newBuilder()
            .code(503)
            .message("Trakt temporarily unavailable")
            .removeHeader("Content-Length")
            .removeHeader("Content-Encoding")
            .header("Content-Type", "application/json")
            .header("Cache-Control", "no-store")
            .body("""{"error":"trakt_upstream_blocked"}""".toResponseBody("application/json".toMediaType()))
            .build()
        response.close()
        return unavailable
    }
}
