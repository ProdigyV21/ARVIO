package com.arflix.tv.data.news

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(okHttpClient: OkHttpClient) {
    // News sites are slow at times; never let one stall the shared client's queue for long.
    private val client = okHttpClient.newBuilder()
        .dispatcher(okhttp3.Dispatcher())
        .callTimeout(20, TimeUnit.SECONDS)
        .build()

    private data class CachedFeed(val articles: List<NewsArticle>, val fetchedAt: Long)

    private val feeds = HashMap<String, CachedFeed>()

    suspend fun headlines(provider: NewsProvider, forceRefresh: Boolean = false): List<NewsArticle> {
        val cacheKey = provider.id + "|" + provider.url
        val cached = synchronized(feeds) { feeds[cacheKey] }
        if (!forceRefresh && cached != null && System.currentTimeMillis() - cached.fetchedAt < FEED_TTL_MS) {
            return cached.articles
        }
        val articles = NewsParsers.parseFeed(fetch(provider.url), provider)
        if (articles.isEmpty()) throw IOException("No articles in ${provider.url}")
        synchronized(feeds) { feeds[cacheKey] = CachedFeed(articles, System.currentTimeMillis()) }
        return articles
    }

    /**
     * The provider's stories on [topics], merged; with no topics (or a user-added feed) its main feed.
     * Fails only when every source of the provider failed.
     */
    suspend fun headlines(provider: NewsProvider, topics: List<NewsTopic>, forceRefresh: Boolean): List<NewsArticle> {
        if (topics.isEmpty() || provider.isCustom) return headlines(provider, forceRefresh)
        val sources = topics.mapNotNull { provider.topics[it] }
        if (sources.isEmpty()) return emptyList()
        val feedUrls = sources.mapNotNull { it.feedUrl }.distinct()
        val sectionSource = NewsTopicSource(pathSections = sources.flatMap { it.pathSections }.distinct())
        val results = coroutineScope {
            val feedCalls = feedUrls.map { url ->
                async { runCatching { headlines(provider.copy(url = url), forceRefresh) } }
            }
            // All path-based topics share one read of the main feed.
            val sectionCall = sectionSource.pathSections.takeIf { it.isNotEmpty() }?.let {
                async { runCatching { headlines(provider, forceRefresh).filter { a -> sectionSource.matches(a.link) } } }
            }
            (feedCalls + listOfNotNull(sectionCall)).awaitAll()
        }
        results.firstOrNull { it.isFailure }?.takeIf { results.all { r -> r.isFailure } }?.getOrThrow()
        return NewsMerge.interleave(results.map { it.getOrNull().orEmpty() })
    }

    /**
     * Reads a user-entered address once before it is saved, so a typo or a web page that is not a
     * feed is rejected up front. @return the feed's title, or null when it has none.
     */
    suspend fun probeFeed(provider: NewsProvider): String? {
        val xml = fetch(provider.url)
        val articles = NewsParsers.parseFeed(xml, provider)
        if (articles.isEmpty()) throw IOException("No articles in ${provider.url}")
        synchronized(feeds) { feeds[provider.id + "|" + provider.url] = CachedFeed(articles, System.currentTimeMillis()) }
        return NewsParsers.feedTitle(xml)
    }

    private suspend fun fetch(url: String): String = withContext(Dispatchers.IO) {
        val request = Request.Builder().url(url)
            // Only feeds are read (never article pages). Some feed servers answer unknown clients with a 403.
            .header("User-Agent", BROWSER_UA)
            .header("Accept", "application/rss+xml, application/atom+xml, application/xml, text/xml;q=0.9, */*;q=0.8")
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("HTTP ${response.code} for $url")
            response.body?.string().orEmpty()
        }
    }

    private companion object {
        const val FEED_TTL_MS = 5 * 60_000L
        const val BROWSER_UA = "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0 Safari/537.36"
    }
}
