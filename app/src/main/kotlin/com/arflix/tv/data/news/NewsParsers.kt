package com.arflix.tv.data.news

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/** Pure parsing, kept free of networking so it can be tested against saved feeds. */
object NewsParsers {
    private const val MAX_ARTICLES = 60
    private const val MAX_PARAGRAPHS = 80
    private const val FUTURE_DATE_TOLERANCE_MS = 60 * 60_000L

    fun parseFeed(xml: String, provider: NewsProvider, now: Long = System.currentTimeMillis()): List<NewsArticle> {
        val doc = Jsoup.parse(xml, provider.url, Parser.xmlParser())
        val items = doc.select("item").ifEmpty { doc.select("entry") }
        return items.asSequence().mapNotNull { parseItem(it, provider, now) }
            .distinctBy { it.link }.take(MAX_ARTICLES).toList()
    }

    private fun parseItem(item: Element, provider: NewsProvider, now: Long): NewsArticle? {
        val title = plainText(item.childText("title"))
        val link = item.children().firstOrNull { it.tagName() == "link" }?.let { linkElement ->
            linkElement.attr("href").ifBlank { linkElement.text() }
        }?.trim().orEmpty().ifBlank { item.childText("guid").trim() }
        if (title.isBlank() || !link.startsWith("http")) return null

        val descriptionHtml = item.childText("description").ifBlank { item.childText("summary") }
        val contentHtml = item.childText("content:encoded").ifBlank {
            item.children().firstOrNull { it.tagName() == "content" && it.attr("type") != "text" }?.text().orEmpty()
        }
        // Only a summary is kept: the feed's description, else the first paragraph of its content.
        val summary = plainText(descriptionHtml)
            .ifBlank { if (contentHtml.isNotBlank()) htmlParagraphs(contentHtml).firstOrNull().orEmpty() else "" }
            .let { if (it == title) "" else it }

        // Feeds such as the Guardian list the same picture at several sizes, smallest first.
        val media = item.children().flatMap { child ->
            if (child.tagName() == "media:group") child.children() else listOf(child)
        }.filter { it.tagName() == "media:content" || it.tagName() == "media:thumbnail" }
            .filter {
                it.attr("url").startsWith("http") &&
                    it.attr("medium").let { m -> m.isBlank() || m == "image" } &&
                    it.attr("type").let { t -> t.isBlank() || t.startsWith("image") }
            }
        val image = media.maxByOrNull { it.attr("width").toIntOrNull() ?: 0 }?.attr("url")
            ?: item.children().firstOrNull { it.tagName() == "enclosure" && it.attr("type").startsWith("image") }
                ?.attr("url")?.takeIf { it.startsWith("http") }
            ?: firstImage(descriptionHtml, provider.url) ?: firstImage(contentHtml, provider.url)

        val date = listOf("pubDate", "published", "updated", "dc:date")
            .firstNotNullOfOrNull { parseDate(item.childText(it)) }
            // Fox pins a promoted story dated days ahead: it has no real publish time to show.
            ?.takeIf { it - now <= FUTURE_DATE_TOLERANCE_MS }?.coerceAtMost(now)

        return NewsArticle(
            link = link,
            title = title,
            summary = summary,
            imageUrl = image?.let(::upgradeYnetImage),
            publishedAt = date,
            providerId = provider.id,
        )
    }

    /** The feed's own name, used to label a custom feed the user added by URL. */
    fun feedTitle(xml: String): String? {
        val doc = Jsoup.parse(xml, "", Parser.xmlParser())
        return (doc.selectFirst("channel > title") ?: doc.selectFirst("feed > title"))
            ?.text()?.let(::plainText)?.takeIf { it.isNotBlank() }
    }

    fun htmlParagraphs(html: String): List<String> {
        val body = Jsoup.parseBodyFragment(html).body()
        body.select("script, style, figure, iframe").remove()
        val blocks = body.select("p, h2, h3, li").map { it.text().trim() }.filter { it.isNotBlank() }
        return blocks.ifEmpty { listOf(body.text().trim()).filter { it.isNotBlank() } }.take(MAX_PARAGRAPHS)
    }

    /** Feed text may be HTML, entity-encoded HTML, or both (Fox double-escapes apostrophes). */
    fun plainText(raw: String): String {
        var text = raw
        repeat(2) { text = Jsoup.parse(text).text() }
        return text.trim()
    }

    private fun firstImage(html: String, baseUrl: String): String? {
        if (html.isBlank() || !html.contains("<img", ignoreCase = true)) return null
        return Jsoup.parseBodyFragment(html, baseUrl).selectFirst("img[src]")?.absUrl("src")?.takeIf { it.startsWith("http") }
    }

    /** ynet's feed links its 100px "medium" crop; the "large" crop of the same image exists alongside it. */
    private fun upgradeYnetImage(url: String): String =
        if (url.contains("yit.co.il") && url.endsWith("_medium.jpg")) url.removeSuffix("_medium.jpg") + "_large.jpg" else url

    private val rfc822Lenient = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm[:ss] [Z][z]", Locale.ENGLISH)

    fun parseDate(raw: String): Long? {
        val value = raw.trim().replace(Regex("\\s+"), " ")
        if (value.isEmpty()) return null
        return runCatching { ZonedDateTime.parse(value, DateTimeFormatter.RFC_1123_DATE_TIME).toInstant().toEpochMilli() }
            .recoverCatching { ZonedDateTime.parse(value, rfc822Lenient).toInstant().toEpochMilli() }
            .recoverCatching { OffsetDateTime.parse(value).toInstant().toEpochMilli() }
            .getOrNull()
    }

    private fun Element.childText(tag: String): String =
        children().firstOrNull { it.tagName() == tag }?.text().orEmpty()
}
