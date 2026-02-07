package com.arflix.tv.network

import com.arflix.tv.util.Constants
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Intercepts API calls to TMDB and Trakt and routes them through Supabase Edge Functions.
 * This keeps API keys secure on the server - they never exist in the app.
 */
class ApiProxyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val host = originalUrl.host

        return when {
            host.contains("themoviedb.org") -> {
                // Route TMDB requests through proxy
                val proxyRequest = rewriteForTmdbProxy(originalRequest)
                chain.proceed(proxyRequest)
            }
            host.contains("trakt.tv") -> {
                // Route Trakt requests through proxy
                val proxyRequest = rewriteForTraktProxy(originalRequest)
                chain.proceed(proxyRequest)
            }
            else -> {
                // Pass through other requests unchanged
                chain.proceed(originalRequest)
            }
        }
    }

    private fun rewriteForTmdbProxy(originalRequest: Request): Request {
        val originalUrl = originalRequest.url

        // Extract the path and remove /3 prefix (proxy adds it)
        // e.g., /3/trending/movie/day -> /trending/movie/day
        var path = originalUrl.encodedPath
        if (path.startsWith("/3/")) {
            path = path.removePrefix("/3")
        }

        // Build proxy URL with path parameter
        val proxyUrlBuilder = Constants.TMDB_PROXY_URL.toHttpUrl().newBuilder()
            .addQueryParameter("path", path)

        // Forward all original query parameters except api_key
        originalUrl.queryParameterNames.forEach { name ->
            if (name != "api_key") {
                originalUrl.queryParameter(name)?.let { value ->
                    proxyUrlBuilder.addQueryParameter(name, value)
                }
            }
        }

        return originalRequest.newBuilder()
            .url(proxyUrlBuilder.build())
            .header("apikey", Constants.SUPABASE_ANON_KEY)
            .header("Authorization", "Bearer ${Constants.SUPABASE_ANON_KEY}")
            .build()
    }

    private fun rewriteForTraktProxy(originalRequest: Request): Request {
        val originalUrl = originalRequest.url

        // Extract the path
        val path = originalUrl.encodedPath

        // Build proxy URL with path and method parameters
        val proxyUrlBuilder = Constants.TRAKT_PROXY_URL.toHttpUrl().newBuilder()
            .addQueryParameter("path", path)
            .addQueryParameter("method", originalRequest.method)

        // Forward all original query parameters
        originalUrl.queryParameterNames.forEach { name ->
            originalUrl.queryParameter(name)?.let { value ->
                proxyUrlBuilder.addQueryParameter(name, value)
            }
        }

        // Get the user's auth token from original request if present
        val authHeader = originalRequest.header("Authorization")
        val userToken = authHeader?.removePrefix("Bearer ")?.trim()

        val requestBuilder = originalRequest.newBuilder()
            .url(proxyUrlBuilder.build())
            .header("apikey", Constants.SUPABASE_ANON_KEY)
            .header("Authorization", "Bearer ${Constants.SUPABASE_ANON_KEY}")

        // Forward user token in custom header
        if (userToken != null && userToken.isNotEmpty()) {
            requestBuilder.header("x-user-token", userToken)
        }

        // For POST/DELETE, keep the body but remove trakt-specific headers (proxy adds them)
        requestBuilder.removeHeader("trakt-api-key")
        requestBuilder.removeHeader("trakt-api-version")

        return requestBuilder.build()
    }
}
