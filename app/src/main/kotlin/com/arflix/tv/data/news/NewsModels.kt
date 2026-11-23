package com.arflix.tv.data.news

/** A news source; [url] is always an RSS 2.0 or Atom feed that the publisher offers for reading. */
data class NewsProvider(
    val id: String,
    val name: String,
    val url: String,
    /** BCP-47 language of the provider's content, used for text direction and defaults. */
    val language: String,
    val isCustom: Boolean = false,
    /** Where this provider's stories on each topic come from; a topic missing here is not covered. */
    val topics: Map<NewsTopic, NewsTopicSource> = emptyMap(),
) {
    val isRtl: Boolean get() = language.substringBefore('-') in RTL_LANGUAGES

    private companion object {
        val RTL_LANGUAGES = setOf("he", "iw", "ar", "fa", "ur")
    }
}

/** Interests a profile can narrow the News page to, stored by [id]. */
enum class NewsTopic(val id: String) {
    WORLD("world"),
    POLITICS("politics"),
    BUSINESS("business"),
    TECH("tech"),
    SPORTS("sports"),
    HEALTH("health"),
    SCIENCE("science"),
    ENTERTAINMENT("entertainment");

    companion object {
        fun fromId(id: String): NewsTopic? = entries.firstOrNull { it.id == id }
    }
}

/**
 * A topic comes either from the provider's own section feed, or from its main feed narrowed to the
 * stories whose URL path has one of [pathSections] as a segment (not every site offers section feeds).
 */
data class NewsTopicSource(val feedUrl: String? = null, val pathSections: List<String> = emptyList()) {
    fun matches(link: String): Boolean {
        val path = runCatching { java.net.URI(link).path }.getOrNull() ?: return false
        return pathSections.any { path.contains("/$it/") }
    }
}

private fun feed(url: String) = NewsTopicSource(feedUrl = url)
private fun sections(vararg names: String) = NewsTopicSource(pathSections = names.toList())

data class NewsArticle(
    /** The article's canonical link; also its identity. */
    val link: String,
    val title: String,
    val summary: String = "",
    val imageUrl: String? = null,
    /** Epoch millis, or null when the feed gives no usable date. */
    val publishedAt: Long? = null,
    val providerId: String,
)

object NewsProviders {
    val presets: List<NewsProvider> = listOf(
        // Fox has no working business feed, and its science feed stopped updating.
        NewsProvider("fox", "Fox News", "https://moxie.foxnews.com/google-publisher/latest.xml", "en", topics = mapOf(
            NewsTopic.WORLD to feed("https://moxie.foxnews.com/google-publisher/world.xml"),
            NewsTopic.POLITICS to feed("https://moxie.foxnews.com/google-publisher/politics.xml"),
            NewsTopic.TECH to feed("https://moxie.foxnews.com/google-publisher/tech.xml"),
            NewsTopic.SPORTS to feed("https://moxie.foxnews.com/google-publisher/sports.xml"),
            NewsTopic.HEALTH to feed("https://moxie.foxnews.com/google-publisher/health.xml"),
            NewsTopic.ENTERTAINMENT to feed("https://moxie.foxnews.com/google-publisher/entertainment.xml"),
        )),
        NewsProvider("bbc", "BBC News", "https://feeds.bbci.co.uk/news/rss.xml", "en", topics = mapOf(
            NewsTopic.WORLD to feed("https://feeds.bbci.co.uk/news/world/rss.xml"),
            NewsTopic.POLITICS to feed("https://feeds.bbci.co.uk/news/politics/rss.xml"),
            NewsTopic.BUSINESS to feed("https://feeds.bbci.co.uk/news/business/rss.xml"),
            NewsTopic.TECH to feed("https://feeds.bbci.co.uk/news/technology/rss.xml"),
            NewsTopic.SPORTS to feed("https://feeds.bbci.co.uk/sport/rss.xml"),
            NewsTopic.HEALTH to feed("https://feeds.bbci.co.uk/news/health/rss.xml"),
            NewsTopic.SCIENCE to feed("https://feeds.bbci.co.uk/news/science_and_environment/rss.xml"),
            NewsTopic.ENTERTAINMENT to feed("https://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml"),
        )),
        NewsProvider("sky", "Sky News", "https://feeds.skynews.com/feeds/rss/home.xml", "en", topics = mapOf(
            NewsTopic.WORLD to feed("https://feeds.skynews.com/feeds/rss/world.xml"),
            NewsTopic.POLITICS to feed("https://feeds.skynews.com/feeds/rss/politics.xml"),
            NewsTopic.BUSINESS to feed("https://feeds.skynews.com/feeds/rss/business.xml"),
            NewsTopic.TECH to feed("https://feeds.skynews.com/feeds/rss/technology.xml"),
            NewsTopic.SPORTS to feed("https://feeds.skynews.com/feeds/rss/sports.xml"),
            NewsTopic.ENTERTAINMENT to feed("https://feeds.skynews.com/feeds/rss/entertainment.xml"),
        )),
        NewsProvider("npr", "NPR", "https://feeds.npr.org/1001/rss.xml", "en", topics = mapOf(
            NewsTopic.WORLD to feed("https://feeds.npr.org/1004/rss.xml"),
            NewsTopic.POLITICS to feed("https://feeds.npr.org/1014/rss.xml"),
            NewsTopic.BUSINESS to feed("https://feeds.npr.org/1006/rss.xml"),
            NewsTopic.TECH to feed("https://feeds.npr.org/1019/rss.xml"),
            NewsTopic.SPORTS to feed("https://feeds.npr.org/1055/rss.xml"),
            NewsTopic.HEALTH to feed("https://feeds.npr.org/1128/rss.xml"),
            NewsTopic.SCIENCE to feed("https://feeds.npr.org/1007/rss.xml"),
            NewsTopic.ENTERTAINMENT to feed("https://feeds.npr.org/1008/rss.xml"),
        )),
        NewsProvider("timesofisrael", "The Times of Israel", "https://www.timesofisrael.com/feed/", "en", topics = mapOf(
            NewsTopic.TECH to feed("https://www.timesofisrael.com/tech-israel/feed/"),
        )),
        NewsProvider("ynet", "ynet", "https://www.ynet.co.il/Integration/StoryRss2.xml", "he", topics = mapOf(
            NewsTopic.POLITICS to feed("https://www.ynet.co.il/Integration/StoryRss317.xml"),
            NewsTopic.BUSINESS to feed("https://www.ynet.co.il/Integration/StoryRss6.xml"),
            NewsTopic.TECH to feed("https://www.ynet.co.il/Integration/StoryRss544.xml"),
            NewsTopic.SPORTS to feed("https://www.ynet.co.il/Integration/StoryRss3.xml"),
            NewsTopic.HEALTH to feed("https://www.ynet.co.il/Integration/StoryRss1208.xml"),
            NewsTopic.SCIENCE to feed("https://www.ynet.co.il/Integration/StoryRss2142.xml"),
            NewsTopic.ENTERTAINMENT to feed("https://www.ynet.co.il/Integration/StoryRss538.xml"),
        )),
        NewsProvider("ynet_flash", "ynet מבזקים", "https://www.ynet.co.il/Integration/StoryRss1854.xml", "he"),
        NewsProvider("israelhayom", "ישראל היום", "https://www.israelhayom.co.il/rss.xml", "he", topics = mapOf(
            NewsTopic.WORLD to sections("world-news"),
            NewsTopic.POLITICS to sections("politics"),
            NewsTopic.BUSINESS to sections("business", "economy"),
            NewsTopic.TECH to sections("tech"),
            NewsTopic.SPORTS to sections("sport"),
            NewsTopic.HEALTH to sections("health"),
            NewsTopic.ENTERTAINMENT to sections("culture"),
        )),
        // Maariv's per-section feed addresses all return the main feed, so its topics come from paths.
        NewsProvider("maariv", "מעריב", "https://www.maariv.co.il/Rss/RssChadashot", "he", topics = mapOf(
            NewsTopic.WORLD to sections("world"),
            NewsTopic.POLITICS to sections("politics"),
            NewsTopic.BUSINESS to sections("business", "economy"),
            NewsTopic.SPORTS to sections("sport"),
            NewsTopic.HEALTH to sections("health"),
        )),
    )

    private val presetIds = presets.map { it.id }.toSet()

    fun isPreset(id: String) = id in presetIds

    /** First-run selection for every profile, so nobody starts with an empty page. */
    val defaultSelection: List<String> = listOf("fox", "sky", "bbc")
}

object NewsMerge {
    /** Round-robin across lists: each keeps its own editorial order and none drowns the rest. */
    fun interleave(lists: List<List<NewsArticle>>): List<NewsArticle> {
        val out = ArrayList<NewsArticle>(lists.sumOf { it.size })
        val longest = lists.maxOfOrNull { it.size } ?: 0
        for (i in 0 until longest) lists.forEach { list -> list.getOrNull(i)?.let(out::add) }
        // A main feed and its breaking-news feed often carry the same story under different links.
        return out.distinctBy { it.link }.distinctBy { it.title.trim().lowercase() }
    }
}
