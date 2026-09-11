package com.arflix.tv.network

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.arflix.tv.BuildConfig
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Dns
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.dnsoverhttps.DnsOverHttps
import okhttp3.logging.HttpLoggingInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.net.Inet4Address
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Provides a configured OkHttpClient instance.
 *
 * All TMDB and Trakt API calls are routed through Supabase Edge Functions
 * via ApiProxyInterceptor. This keeps API keys secure on the server.
 *
 * SSL/TLS validation is handled by NetworkSecurityConfig (res/xml/network_security_config.xml):
 * - Release: System certificates only (secure)
 * - Debug: User + System certificates (allows proxy debugging)
 *
 * DO NOT add custom TrustManager - it defeats certificate validation.
 */
object OkHttpProvider {
    private const val TAG = "AppDns"
    /** 50 MB disk cache for API responses (TMDB metadata, Trakt data, etc.) */
    private const val HTTP_CACHE_SIZE = 50L * 1024L * 1024L
    private const val IMAGE_DISK_CACHE_SIZE = 48L * 1024L * 1024L
    private const val CLOUDFLARE_DOH_HOST = "cloudflare-dns.com"
    private const val CLOUDFLARE_DOH_URL = "https://cloudflare-dns.com/dns-query"
    private const val GOOGLE_DOH_HOST = "dns.google"
    private const val GOOGLE_DOH_URL = "https://dns.google/dns-query"
    private const val ADGUARD_DOH_HOST = "dns.adguard-dns.com"
    private const val ADGUARD_DOH_URL = "https://dns.adguard-dns.com/dns-query"

    const val DNS_PROVIDER_PREF_KEY = "dns_provider_global"
    const val DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
    const val USER_AGENT_PREF_KEY = "custom_user_agent_global"

    enum class AppDnsProvider {
        SYSTEM,
        CLOUDFLARE,
        GOOGLE,
        ADGUARD
    }

    /**
     * Must be called once from Application.onCreate() before any network calls.
     * Provides the application context needed for the disk cache directory.
     */
    fun init(context: Context) {
        // `applicationContext` is null when called from Application.attachBaseContext — the
        // Application is not registered with the framework yet. That is precisely where this has
        // to run (Hilt builds Retrofit, and therefore this client, inside Application.onCreate's
        // super call), so fall back to the context we were handed: it is the Application itself.
        appContext = context.applicationContext ?: context
    }

    @Volatile
    private var appContext: Context? = null

    @Volatile
    private var selectedDnsProvider: AppDnsProvider = AppDnsProvider.SYSTEM

    @Volatile
    private var _customUserAgent: String = ""

    val customUserAgent: String get() = _customUserAgent
    val userAgent: String get() = userAgentOr(DEFAULT_USER_AGENT)

    fun setCustomUserAgent(value: String) {
        _customUserAgent = value.trim().takeIf(::isSafeHeaderValue).orEmpty()
    }

    fun userAgentOr(defaultUserAgent: String): String {
        return _customUserAgent
            .takeIf { it.isNotBlank() && isSafeHeaderValue(it) }
            ?: defaultUserAgent.takeIf(::isSafeHeaderValue)
            ?: DEFAULT_USER_AGENT
    }

    private val dnsScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val clientLock = Any()

    private val appConnectionPool = ConnectionPool(32, 5, TimeUnit.MINUTES)
    private val playbackConnectionPool = ConnectionPool(16, 5, TimeUnit.MINUTES)

    @Volatile
    private var appClient: OkHttpClient? = null

    @Volatile
    private var playbackSharedClient: OkHttpClient? = null

    @Volatile
    private var appHttpCache: Cache? = null

    @Volatile
    private var coilSharedClient: OkHttpClient? = null

    private val systemDns: Dns by lazy {
        preferIpv4ForTmdb(Dns.SYSTEM)
    }

    private val cloudflareDns: Dns by lazy {
        preferIpv4ForTmdb(
            buildDohDns(
                url = CLOUDFLARE_DOH_URL,
                dohHost = CLOUDFLARE_DOH_HOST,
                bootstrapHosts = cloudflareBootstrapHosts
            )
        )
    }

    private val googleDns: Dns by lazy {
        preferIpv4ForTmdb(
            buildDohDns(
                url = GOOGLE_DOH_URL,
                dohHost = GOOGLE_DOH_HOST,
                bootstrapHosts = googleBootstrapHosts
            )
        )
    }

    private val adguardDns: Dns by lazy {
        preferIpv4ForTmdb(
            buildDohDns(
                url = ADGUARD_DOH_URL,
                dohHost = ADGUARD_DOH_HOST,
                bootstrapHosts = adguardBootstrapHosts
            )
        )
    }

    val dns: Dns = object : Dns {
        override fun lookup(hostname: String): List<InetAddress> {
            val provider = selectedDnsProvider
            return try {
                selectedDns(provider).lookup(hostname)
            } catch (selectedFailure: UnknownHostException) {
                if (provider == AppDnsProvider.SYSTEM) {
                    throw selectedFailure
                }

                try {
                    val fallback = systemDns.lookup(hostname)
                    Log.w(TAG, "DNS provider=$provider failed for $hostname, using system DNS fallback")
                    fallback
                } catch (_: UnknownHostException) {
                    throw selectedFailure
                }
            }
        }
    }

    private val apiDnsLoggingInterceptor = Interceptor { chain ->
        val request = chain.request()
        Log.i(
            TAG,
            "API request dnsProvider=$selectedDnsProvider method=${request.method} host=${request.url.host} url=${request.url}"
        )
        chain.proceed(request)
    }

    private val lenientJsonGzipInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        if (!shouldDecodeLenientJsonGzip(response)) {
            return@Interceptor response
        }

        val body = response.body ?: return@Interceptor response
        val decodedBody = try {
            streamingJsonGzipBody(body)
        } catch (_: IOException) {
            return@Interceptor gzipErrorResponse(response, "Compressed API response could not be decoded.")
        }

        response.newBuilder()
            .removeHeader("Content-Encoding")
            .removeHeader("Content-Length")
            .body(decodedBody)
            .build()
    }

    private fun shouldDecodeLenientJsonGzip(response: Response): Boolean {
        if (!response.header("Content-Encoding").equals("gzip", ignoreCase = true)) return false
        val contentType = response.header("Content-Type").orEmpty().lowercase()
        return "json" in contentType
    }

    private fun gzipErrorResponse(response: Response, message: String): Response {
        val body = """{"success":false,"status_message":"$message"}"""
            .toResponseBody("application/json; charset=utf-8".toMediaType())
        return response.newBuilder()
            .code(502)
            .message("Invalid compressed API response")
            .removeHeader("Content-Encoding")
            .removeHeader("Content-Length")
            .body(body)
            .build()
    }

    private fun selectedDns(provider: AppDnsProvider): Dns {
        return when (provider) {
            AppDnsProvider.SYSTEM -> systemDns
            AppDnsProvider.CLOUDFLARE -> cloudflareDns
            AppDnsProvider.GOOGLE -> googleDns
            AppDnsProvider.ADGUARD -> adguardDns
        }
    }

    val client: OkHttpClient
        get() = appClient ?: synchronized(clientLock) {
            appClient ?: buildAppClient().also { appClient = it }
        }

    val playbackClient: OkHttpClient
        get() = playbackSharedClient ?: synchronized(clientLock) {
            playbackSharedClient ?: buildPlaybackClient().also { playbackSharedClient = it }
        }

    private fun buildAppClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            redactHeader("Authorization")
            redactHeader("apikey")
            redactHeader("x-user-token")
            redactHeader("X-Plex-Token")
            redactHeader("X-Emby-Token")
            redactHeader("Cookie")
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.HEADERS
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        val builder = OkHttpClient.Builder()
            .addInterceptor(customUserAgentInterceptor)
            // TMDB/Trakt calls are proxied when Supabase proxy config is present.
            // Contributors can still use direct calls with their own local keys.
            .addInterceptor(ApiProxyInterceptor())
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(lenientJsonGzipInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectionPool(appConnectionPool)
            .dns(dns)
            .retryOnConnectionFailure(true)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(apiDnsLoggingInterceptor)
        }

        appContext?.let { ctx ->
            builder.cache(getOrCreateHttpCache(ctx))
            // Never persist a failure. A transient 5xx or a rate-limit 429 carrying a cacheable
            // header would otherwise be replayed from disk for its whole stated lifetime, long
            // after the server recovered.
            builder.addNetworkInterceptor(noCacheErrorsInterceptor)
        } ?: Log.w(
            TAG,
            "API client built without app context — HTTP disk cache disabled for this process"
        )

        return builder.build()
    }

    private fun buildPlaybackClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(customUserAgentInterceptor)
            .connectionPool(playbackConnectionPool)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .dns(dns)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cache(null)
            .build()
    }

    private val customUserAgentInterceptor = Interceptor { chain ->
        val request = chain.request()
        // If a call already set an explicit User-Agent (e.g. CatalogRepository,
        // StalkerApi), respect it. Otherwise apply the user's custom UA when set,
        // falling back to a real browser UA.
        //
        // Why the browser fallback matters: OkHttp's default UA is `okhttp/<ver>`,
        // which Cloudflare "Bot Fight Mode" challenges/blocks. Many Stremio addons
        // (e.g. flixnest) sit behind Cloudflare and return 403 to library UAs while
        // serving browsers fine — so the addon's streams never reach the app and it
        // silently shows no sources. Defaulting to a browser UA fixes this for every
        // Cloudflare-fronted addon without changing behaviour for hosts that don't care.
        val existingUserAgent = request.header("User-Agent")
        if (existingUserAgent != null) {
            chain.proceed(request)
        } else {
            val userAgent = userAgentOr(DEFAULT_USER_AGENT)
            chain.proceed(request.newBuilder().header("User-Agent", userAgent).build())
        }
    }

    private fun isSafeHeaderValue(value: String): Boolean {
        return value.all { ch -> ch == '\t' || ch.code in 32..126 }
    }

    /** Marks unsuccessful responses `no-store` so the disk cache never keeps a failure. */
    private val noCacheErrorsInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        if (response.isSuccessful) {
            response
        } else {
            response.newBuilder().header("Cache-Control", "no-store").build()
        }
    }

    private fun getOrCreateHttpCache(context: Context): Cache {
        return appHttpCache ?: synchronized(clientLock) {
            appHttpCache ?: Cache(File(context.cacheDir, "http_cache"), HTTP_CACHE_SIZE).also {
                appHttpCache = it
            }
        }
    }

    /** Resolve bootstrap IPs safely - IPv6 may fail on some devices/emulators */
    private fun safeResolve(vararg addresses: String): List<InetAddress> {
        return addresses.mapNotNull { addr ->
            try { InetAddress.getByName(addr) } catch (_: Exception) { null }
        }
    }

    private val cloudflareBootstrapHosts: List<InetAddress> by lazy {
        safeResolve("1.1.1.1", "1.0.0.1")
    }

    private val googleBootstrapHosts: List<InetAddress> by lazy {
        safeResolve("8.8.8.8", "8.8.4.4")
    }

    private val adguardBootstrapHosts: List<InetAddress> by lazy {
        safeResolve("94.140.14.14", "94.140.15.15")
    }

    fun parseDnsProvider(raw: String?): AppDnsProvider {
        return when (raw?.trim()?.lowercase()) {
            "system", "system dns", "system_dns" -> AppDnsProvider.SYSTEM
            "cloudflare", "cloudflare dns", "cloudflare_dns" -> AppDnsProvider.CLOUDFLARE
            "google" -> AppDnsProvider.GOOGLE
            "adguard", "ad guard" -> AppDnsProvider.ADGUARD
            else -> AppDnsProvider.SYSTEM
        }
    }

    fun setDnsProvider(provider: AppDnsProvider) {
        selectedDnsProvider = provider
        Log.i(TAG, "Using DNS provider=$provider")
        dnsScope.launch {
            appConnectionPool.evictAll()
            playbackConnectionPool.evictAll()
            Log.i(TAG, "Evicted pooled app connections after DNS change")
        }
    }

    private fun preferIpv4ForTmdb(delegate: Dns): Dns {
        return object : Dns {
            override fun lookup(hostname: String): List<InetAddress> {
                val resolved = delegate.lookup(hostname)
                if (!hostname.contains("tmdb", ignoreCase = true) &&
                    !hostname.contains("themoviedb", ignoreCase = true)
                ) {
                    return resolved
                }

                val ipv4 = resolved.filterIsInstance<Inet4Address>()
                if (ipv4.isEmpty()) {
                    return resolved
                }
                val ipv6 = resolved.filterNot { it is Inet4Address }
                return ipv4 + ipv6
            }
        }
    }

    private fun buildBootstrapDns(
        dohHost: String,
        bootstrapHosts: List<InetAddress>
    ): Dns {
        return object : Dns {
            override fun lookup(hostname: String): List<InetAddress> {
                if (hostname.equals(dohHost, ignoreCase = true)) {
                    return bootstrapHosts
                }
                throw UnknownHostException(
                    "Bootstrap DNS is restricted to $dohHost. Requested: $hostname"
                )
            }
        }
    }

    private fun buildDohDns(
        url: String,
        dohHost: String,
        bootstrapHosts: List<InetAddress>
    ): Dns {
        val bootstrapDns = buildBootstrapDns(dohHost, bootstrapHosts)
        val bootstrapClient = OkHttpClient.Builder()
            .dns(bootstrapDns)
            .cache(null)
            .build()

        return DnsOverHttps.Builder()
            .client(bootstrapClient)
            .url(url.toHttpUrl())
            .systemDns(bootstrapDns)
            .bootstrapDnsHosts(*bootstrapHosts.toTypedArray())
            .post(true)
            .resolvePrivateAddresses(false)
            .resolvePublicAddresses(true)
            .build()
    }

    val coilClient: OkHttpClient
        get() = coilSharedClient ?: synchronized(clientLock) {
            coilSharedClient ?: buildCoilClient().also { coilSharedClient = it }
        }

    private fun buildCoilClient(): OkHttpClient {
        // Build a dedicated image client with its OWN connection pool and aggressive
        // timeouts. The previous implementation shared the main API client's connection
        // pool (30s timeouts), which meant:
        // 1. A slow TMDB API call could exhaust the shared pool's max-idle connections,
        //    blocking image loads entirely until an API call finished.
        // 2. If image.tmdb.org DNS failed, Coil waited the full 30s connect timeout
        //    per card — on a home screen with 20+ cards, the entire grid appeared frozen.
        // The image CDN (image.tmdb.org) is a fast static-asset CDN that should respond
        // in <500ms; anything longer is a network issue that retrying later will fix.
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(8, 30, TimeUnit.SECONDS))
            .dns(dns)
            .retryOnConnectionFailure(true)
            .build()
    }

    fun createCoilImageLoader(context: Context): ImageLoader {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        // Keep the image cache modest: TV boxes cap the heap at ~384MB and the Live
        // TV page (player buffers + channel data) needs the headroom. Disk cache
        // still holds 48MB, so posters reload quickly without pinning Java heap.
        val imageCacheBytes = if (activityManager?.isLowRamDevice == true) {
            24 * 1024 * 1024
        } else {
            32 * 1024 * 1024
        }
        return ImageLoader.Builder(context)
            .okHttpClient(coilClient)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizeBytes(imageCacheBytes)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizeBytes(IMAGE_DISK_CACHE_SIZE)
                    .build()
            }
            .crossfade(false)
            .respectCacheHeaders(false)
            .allowRgb565(true)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }
}
