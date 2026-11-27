package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StreamApi
import com.arflix.tv.data.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import javax.inject.Inject
import javax.inject.Singleton

data class SportsAddonStream(val name: String, val url: String, val headers: Map<String, String>, val external: Boolean, val description: String? = null)

internal fun playbackSportsSources(sources: List<SportsAddonStream>) = sources.filterNot {
    it.external && Regex("\\b(support the project|donate|donation)\\b", RegexOption.IGNORE_CASE)
        .containsMatchIn("${it.name} ${it.description.orEmpty()}")
}.distinctBy { Triple(it.url, it.headers, it.external) }.sortedBy { it.external }

@Singleton
class SportsAddonRepository @Inject constructor(private val streams: StreamRepository, private val api: StreamApi) {
    val installedAddons get() = streams.installedAddons
    // OkHttp runs at most five requests per host at once; more only queue there.
    private val permits = Semaphore(5)
    private companion object {
        const val PAGE_WAVE = 4
        const val MAX_PAGES = 50
        const val PUBLISH_INTERVAL_MS = 400L
    }
    private val mutex = Mutex()
    private data class Cached(val at: Long, val events: List<SportsAddonEvent>)
    private val cache = linkedMapOf<String, Cached>()
    // Last complete result per catalog, kept past the cache lifetime as a fallback.
    private val lastGood = linkedMapOf<String, List<SportsAddonEvent>>()

    /**
     * Loads every sports catalog of [addons]. Catalogs load side by side, and after the
     * first page of a paginated catalog the following pages are fetched in parallel waves
     * (the page size is known from the first page), so a 30-page "Today" catalog takes a
     * few round trips instead of thirty. [publish] receives growing snapshots at most a
     * few times per second, so the page is not rebuilt for every single page.
     */
    suspend fun load(addons: List<Addon>, publish: suspend (List<SportsAddonEvent>) -> Unit): List<SportsAddonEvent> {
        val publishContext = currentCoroutineContext().minusKey(Job)
        return withContext(Dispatchers.Default) {
        val startedAt = android.os.SystemClock.elapsedRealtime()
        val results = linkedMapOf<String, SportsAddonEvent>()
        val publishMutex = Mutex()
        var lastPublish = 0L
        suspend fun merge(items: Collection<SportsAddonEvent>, force: Boolean) {
            publishMutex.withLock {
                items.forEach { item -> results[item.key] = results[item.key]?.let { mergeSportsAddonEvent(it, item) } ?: item }
                val now = android.os.SystemClock.elapsedRealtime()
                if (force || now - lastPublish >= PUBLISH_INTERVAL_MS) {
                    lastPublish = now
                    val snapshot = results.values.toList()
                    withContext(publishContext) { publish(snapshot) }
                }
            }
        }
        addons.flatMap { addon -> sportsEventCatalogs(addon).map { addon to it } }.map { (addon, catalog) -> async {
            val installation = sportsAddonInstallation(addon)
            val key = "$installation|${addon.version}|${catalog.type}|${catalog.id}"
            val now = System.currentTimeMillis()
            val cached = mutex.withLock { cache[key]?.takeIf { now - it.at in 0..119_999 } }
            val events = cached?.events ?: run {
                val items = linkedMapOf<String, SportsAddonEvent>()
                val seen = hashSetOf<String>()
                var failed = false
                val paginated = catalog.extra.orEmpty().any { it.name == "skip" }
                suspend fun page(skip: Int?): List<com.arflix.tv.data.api.StremioMetaPreview>? {
                    val url = sportsAddonUrl(addon, "catalog", catalog.type, catalog.id, skip) ?: return emptyList()
                    return permits.withPermit {
                        try { withTimeoutOrNull(8_000) { api.getAddonCatalog(url) } }
                        catch (e: CancellationException) { throw e } catch (_: Exception) { null }
                    }?.let { it.metas ?: it.items ?: emptyList() }
                }
                fun accept(metas: List<com.arflix.tv.data.api.StremioMetaPreview>): Int {
                    val fresh = metas.mapNotNull { it.id }.count { seen.add(it) }
                    metas.mapNotNull { it.toSportsAddonEvent(addon, catalog, now, installation) }.forEach { item ->
                        items[item.key] = items[item.key]?.let { mergeSportsAddonEvent(it, item) } ?: item
                    }
                    return fresh
                }
                val first = page(if (paginated) 0 else null)
                if (first == null) failed = true
                else if (first.isNotEmpty()) {
                    accept(first)
                    merge(items.values, force = false)
                    val pageSize = first.size
                    var nextPage = 1
                    // Bound broken providers that ignore skip or produce an infinite catalog.
                    var more = paginated
                    while (more && nextPage < MAX_PAGES) {
                        val wave = (nextPage until minOf(nextPage + PAGE_WAVE, MAX_PAGES)).toList()
                        val pages = coroutineScope { wave.map { index -> async { page(index * pageSize) } }.awaitAll() }
                        nextPage += wave.size
                        for (metas in pages) {
                            if (metas == null) { failed = true; more = false; break }
                            if (metas.isEmpty() || accept(metas) == 0 || metas.size < pageSize) { more = false; break }
                        }
                        merge(items.values, force = false)
                    }
                }
                mutex.withLock {
                    if (!failed) {
                        val loaded = items.values.toList()
                        cache[key] = Cached(now, loaded)
                        lastGood[key] = loaded
                        while (cache.size > 100) cache.remove(cache.keys.first())
                        while (lastGood.size > 100) lastGood.remove(lastGood.keys.first())
                        loaded
                    } else {
                        // A slow or failing request must never wipe games that loaded
                        // before: keep the last complete result of this catalog and lay
                        // whatever did arrive this time over it.
                        val previous = lastGood[key].orEmpty()
                        if (previous.isEmpty()) items.values.toList()
                        else (previous.associateBy { it.key } + items).values.toList()
                    }
                }
            }
            merge(events, force = false)
        } }.awaitAll()
        merge(emptyList(), force = true)
        System.err.println("[Sports-Addons] events=${results.size} elapsed=${android.os.SystemClock.elapsedRealtime() - startedAt}ms")
        results.values.toList()
        }
    }

    suspend fun resolve(event: SportsAddonEvent): List<SportsAddonStream> = withContext(Dispatchers.IO) {
        val addon = installedAddons.first().firstOrNull {
            it.isEnabled && it.isInstalled && sportsAddonInstallation(it) == event.installation
        } ?: return@withContext emptyList()
        val url = sportsAddonUrl(addon, "stream", event.type, event.eventId) ?: return@withContext emptyList()
        val response = withTimeout(15_000) { api.getAddonStreams(url) }
        playbackSportsSources(response.streams.orEmpty().mapNotNull { stream ->
            val direct = stream.url?.takeIf { it.isNotBlank() }
            val external = direct == null && (!stream.externalUrl.isNullOrBlank() || !stream.ytId.isNullOrBlank())
            val target = direct ?: stream.externalUrl?.takeIf { it.isNotBlank() } ?: stream.ytId?.takeIf { it.isNotBlank() }?.let { "https://www.youtube.com/watch?v=$it" }
            if (target == null || !target.startsWith("https://") && !target.startsWith("http://")) return@mapNotNull null
            SportsAddonStream(stream.name ?: stream.title ?: addon.name, target,
                stream.headers.orEmpty() + stream.behaviorHints?.headers.orEmpty() + stream.behaviorHints?.proxyHeaders?.request.orEmpty(), external,
                stream.title?.takeIf { it.isNotBlank() && it != stream.name })
        })
    }
}
