package com.arflix.tv.network

import com.arflix.tv.util.Constants
import com.arflix.tv.BuildConfig
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Intercepts selected metadata API calls and routes them through backend proxy Functions
 * only when a build flag explicitly opts in.
 */
class ApiProxyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        if (!hasProxyConfig()) {
            return chain.proceed(originalRequest)
        }

        return when (originalUrl.host) {
            "api.themoviedb.org" -> {
                // TMDB browsing is very high-volume. Proxy it only when the
                // build explicitly opts in; otherwise use the direct API key
                // and OkHttp cache to avoid runaway Function billing.
                if (BuildConfig.ENABLE_TMDB_EDGE_PROXY) {
                    val proxyRequest = rewriteForTmdbProxy(originalRequest) ?: originalRequest
                    chain.proceed(proxyRequest)
                } else {
                    chain.proceed(originalRequest)
                }
            }
            "api.trakt.tv" -> {
                // Trakt's OAuth and user endpoints must stay direct. The backend
                // function runs behind Cloudflare/Netlify and can be blocked by
                // Trakt, which surfaces as token request failures in the app.
                chain.proceed(originalRequest)
            }
            "api.mdblist.com" -> {
                // MDBList (per-profile Trakt alternative) authenticates with a
                // user API key on the query string. Keep it direct, same as Trakt.
                chain.proceed(originalRequest)
            }
            "api.simkl.com" -> {
                // A Simkl client id is public and official builds can call the API
                // directly. Contributor builds without one fall back to ARVIO's
                // credential-injecting proxy instead of shipping a broken login.
                if (Constants.SIMKL_CLIENT_ID.isBlank()) {
                    val proxyRequest = rewriteForSimklProxy(originalRequest) ?: originalRequest
                    chain.proceed(proxyRequest)
                } else {
                    chain.proceed(originalRequest)
                }
            }
            else -> {
                // Pass through other requests unchanged
                chain.proceed(originalRequest)
            }
        }
    }

    private fun rewriteForSimklProxy(originalRequest: Request): Request? {
        val originalUrl = originalRequest.url
        val path = originalUrl.encodedPath

        val cleanVersion = com.arflix.tv.BuildConfig.VERSION_NAME.substringBefore("-")

        val proxyUrlBuilder = (Constants.SIMKL_PROXY_URL.toHttpUrlOrNull() ?: return null).newBuilder()
            .addQueryParameter("path", path)
            .addQueryParameter("method", originalRequest.method)
            .setQueryParameter("app-name", "arvio")
            .setQueryParameter("app-version", cleanVersion)

        if (Constants.SIMKL_CLIENT_ID.isNotBlank()) {
            proxyUrlBuilder.setQueryParameter("client_id", Constants.SIMKL_CLIENT_ID)
        }

        for (i in 0 until originalUrl.querySize) {
            val name = originalUrl.queryParameterName(i)
            if (name !in listOf("path", "method", "client_id", "app-name", "app-version")) {
                originalUrl.queryParameterValue(i)?.let { value ->
                    proxyUrlBuilder.addQueryParameter(name, value)
                }
            }
        }

        val userToken = originalRequest.header("Authorization")?.removePrefix("Bearer ")
        val builder = originalRequest.newBuilder()
            .url(proxyUrlBuilder.build())
            .header("apikey", Constants.APP_ANON_KEY)
            .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
            .header("User-Agent", "ARVIO/$cleanVersion (Android TV)")

        if (!userToken.isNullOrBlank()) {
            builder.header("x-user-token", userToken)
        }

        return builder.build()
    }

    private fun rewriteForTmdbProxy(originalRequest: Request): Request? {
        val originalUrl = originalRequest.url

        // Extract the path and remove /3 prefix (proxy adds it)
        // e.g., /3/trending/movie/day -> /trending/movie/day
        val path = originalUrl.encodedPath.let { if (it.startsWith("/3/")) it.removePrefix("/3") else it }

        // Build proxy URL with path parameter
        val proxyUrlBuilder = (Constants.TMDB_PROXY_URL.toHttpUrlOrNull() ?: return null).newBuilder()
            .addQueryParameter("path", path)

        // Forward all original query parameters except api_key
        for (i in 0 until originalUrl.querySize) {
            val name = originalUrl.queryParameterName(i)
            if (name != "api_key") {
                originalUrl.queryParameterValue(i)?.let { value ->
                    proxyUrlBuilder.addQueryParameter(name, value)
                }
            }
        }

        return originalRequest.newBuilder()
            .url(proxyUrlBuilder.build())
            .header("apikey", Constants.APP_ANON_KEY)
            .header("Authorization", "Bearer ${Constants.APP_ANON_KEY}")
            .build()
    }

    private fun hasProxyConfig(): Boolean {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            return Constants.NETLIFY_BACKEND_URL.startsWith("https://") || Constants.NETLIFY_BACKEND_URL.startsWith("http://")
        }
        val supabaseUrl = Constants.SUPABASE_URL.trim()
        val anonKey = Constants.SUPABASE_ANON_KEY.trim()
        return (supabaseUrl.startsWith("https://") || supabaseUrl.startsWith("http://")) &&
            !supabaseUrl.contains("your-project", ignoreCase = true) &&
            anonKey.length > 40 &&
            !anonKey.startsWith("your-", ignoreCase = true)
    }
}
