package com.arflix.tv.network

import android.content.Context
import android.util.Log
import coil.ImageLoader
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
import java.net.Inet4Address
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

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
        appContext = context.applicationContext
    }

    @Volatile
    private var appContext: Context? = null

    @Volatile
    private var selectedDnsProvider: AppDnsProvider = AppDnsProvider.SYSTEM

    private val dnsScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val clientLock = Any()

    private val appConnectionPool = ConnectionPool(32, 5, TimeUnit.MINUTES)

    @Volatile
    private var appClient: OkHttpClient? = null

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
            return selectedDns(selectedDnsProvider).lookup(hostname)
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

    private fun buildAppClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        val builder = OkHttpClient.Builder()
            // Direct API calls — no Supabase edge function proxy.
            // TMDB/Trakt keys are passed as query params / headers by Retrofit.
            .addInterceptor(loggingInterceptor)
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
        }

        return builder.build()
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
        val builder = client.newBuilder()
        builder.interceptors().remove(apiDnsLoggingInterceptor)
        builder.dns(dns)
        return builder.build()
    }

    fun createCoilImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(coilClient)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.15)
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
            .build()
    }
}
