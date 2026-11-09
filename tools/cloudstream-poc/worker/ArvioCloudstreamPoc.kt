package arvio.poc

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.lagradost.cloudstream3.utils.DrmExtractorLink
import com.sun.net.httpserver.HttpServer
import desktop.api.Catalog
import desktop.api.SearchItem
import desktop.compat.DesktopContext
import desktop.config.Config
import desktop.config.Paths
import desktop.config.Settings
import desktop.db.Database
import desktop.db.Store
import desktop.plugins.PluginHost
import desktop.plugins.PluginRuntime
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.io.File
import java.net.InetSocketAddress
import java.net.URLDecoder
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors

/** Local experiment only. Uses the separately built runtime's public API; never serves media. */
fun main() {
    val token = requireNotNull(System.getenv("ARVIO_CS_WORKER_TOKEN"))
    require(token.length >= 32)
    DesktopContext.dataDir = Paths.dataDir
    // Never start Chrome on the user's PC. WebView-only extractors remain unsupported.
    val config = Config.load().copy(chromiumPath = "/ARVIO_POC_BROWSER_DISABLED/no-browser")
    val db = Database(File(Paths.dataDir, "poc.db"))
    val settings = Settings(db, config.settingDefaults)
    PluginRuntime.configure(config, settings)
    val host = PluginHost(config, settings)
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    prepareJvmCompatibility()
    runBlocking { host.start(scope) }
    // Reload a fresh conversion so the class loader sees the compatibility copy.
    if (prepareJvmCompatibility()) runBlocking { host.update(listOf("StreamPlay")) }
    val catalog = Catalog(host, settings, Store(db))
    val json = jacksonObjectMapper()
    val known = ConcurrentHashMap.newKeySet<String>()
    class Resolution {
        val links = CopyOnWriteArrayList<Map<String, Any?>>()
        val subtitles = CopyOnWriteArrayList<Map<String, String>>()
        val created = System.currentTimeMillis()
        @Volatile var done = false
        @Volatile var error: String? = null
    }
    val resolutions = ConcurrentHashMap<String, Resolution>()
    val extractionSlots = Semaphore(2)
    val server = HttpServer.create(InetSocketAddress("127.0.0.1", 8951), 8)
    server.executor = Executors.newFixedThreadPool(4)
    server.createContext("/") { exchange ->
        var status = 200
        val result: Any = try {
            require(exchange.requestMethod == "GET") { "GET required" }
            require(exchange.requestHeaders.getFirst("Authorization") == "Bearer $token") { "Unauthorized" }
            val query = exchange.requestURI.rawQuery.orEmpty().split('&').filter { it.contains('=') }
                .associate { URLDecoder.decode(it.substringBefore('='), "UTF-8") to URLDecoder.decode(it.substringAfter('='), "UTF-8") }
            runBlocking {
                val provider = host.providers().firstOrNull { it.plugin.name == "StreamPlay" }?.api?.name
                when (exchange.requestURI.path) {
                    "/health" -> mapOf("plugins" to host.plugins.filter { it.name == "StreamPlay" }.map {
                        mapOf("name" to it.name, "status" to it.status.name, "error" to it.error)
                    }, "provider" to provider, "mediaRelay" to false, "unsupported" to listOf("MovieBox JVM conversion", "Anime tracking", "Android-only WebView APIs"))
                    "/search" -> {
                        requireNotNull(provider) { "StreamPlay is not loaded" }
                        val q = query["q"].orEmpty().trim()
                        require(q.length in 2..100)
                        val items = mutableListOf<SearchItem>()
                        var failure: String? = null
                        withTimeout(20_000) { catalog.search(q, setOf(provider)) { _, found, error ->
                            if (found != null) items.addAll(found.take(60))
                            failure = error
                        } }
                        items.forEach { known.add(it.url) }
                        mapOf("items" to items, "error" to failure)
                    }
                    "/load", "/resolve" -> {
                        requireNotNull(provider) { "StreamPlay is not loaded" }
                        val url = requireNotNull(query["url"])
                        val standardId = Regex("""\{"id":\d{1,10},"type":"(movie|tv)","aniId":null,"malId":null\}""").matches(url)
                        require(url in known || standardId) { "Search for this item first" }
                        val details = catalog.details(provider, url)
                        if (exchange.requestURI.path == "/load") details else {
                            val season = query["season"]?.toIntOrNull()
                            val episode = query["episode"]?.toIntOrNull()
                            val data = if (season != null && episode != null) details.episodes.firstOrNull {
                                it.season == season && it.episode == episode
                            }?.data else details.movieData
                            requireNotNull(data) { "No matching movie or episode" }
                            resolutions.entries.removeIf { System.currentTimeMillis() - it.value.created > 120_000 && it.value.done }
                            require(resolutions.size < 20 || resolutions.containsKey(data)) { "Resolution cache full" }
                            val state = resolutions.computeIfAbsent(data) {
                                Resolution().also { state -> scope.launch {
                                    try {
                                        val api = catalog.provider(provider).api
                                        extractionSlots.withPermit { withTimeout(60_000) {
                                            api.loadLinks(data, false, { sub -> state.subtitles.add(mapOf("lang" to sub.lang, "url" to sub.url)) }) { link ->
                                                if (state.links.size < 512) state.links.add(mapOf("url" to link.url, "name" to link.name, "source" to link.source,
                                                    "quality" to link.quality, "type" to link.type.name, "referer" to link.referer,
                                                    "headers" to link.headers, "drm" to (link is DrmExtractorLink),
                                                    "interceptor" to runCatching { api.getVideoInterceptor(link) != null }.getOrDefault(true)))
                                            }
                                        } }
                                    } catch (e: Throwable) { if (e is VirtualMachineError) throw e; state.error = e.javaClass.simpleName }
                                    finally { state.done = true }
                                } }
                            }
                            val deadline = System.currentTimeMillis() + 21_000
                            while (!state.done && state.links.isEmpty() && System.currentTimeMillis() < deadline) delay(100)
                            if (!state.done && state.links.isNotEmpty()) delay(1200)
                            mapOf("links" to state.links.toList(), "subtitles" to state.subtitles.toList(), "done" to state.done, "error" to state.error)
                        }
                    }
                    else -> { status = 404; mapOf("error" to "Not found") }
                }
            }
        } catch (e: Exception) {
            status = if (e.message == "Unauthorized") 401 else 502
            // Do not put provider URLs, cookies or access tokens in error responses.
            mapOf("error" to e.javaClass.simpleName)
        }
        val bytes = json.writeValueAsBytes(result)
        exchange.responseHeaders.add("Content-Type", "application/json")
        exchange.responseHeaders.add("Cache-Control", "no-store")
        exchange.sendResponseHeaders(status, bytes.size.toLong())
        exchange.responseBody.use { it.write(bytes) }
    }
    server.start()
    println("ARVIO Cloudstream POC worker listening on 127.0.0.1:8951; media relay disabled")
}
