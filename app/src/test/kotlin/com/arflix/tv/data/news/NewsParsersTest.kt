package com.arflix.tv.data.news

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Synthetic feeds shaped like the real providers' (entity-escaped, CDATA, Atom, Lite HTML). */
class NewsParsersTest {
    private val rss = NewsProvider("rss", "Example", "https://news.example.com/rss.xml", "en")
    private val he = NewsProvider("he", "דוגמה", "https://news.example.co.il/rss.xml", "he")

    @Test fun rssWithEscapedContentKeepsFullTextAndDecodesDoubleEscapedEntities() {
        val xml = """
            <rss xmlns:media="http://search.yahoo.com/mrss/" xmlns:content="http://purl.org/rss/1.0/modules/content/" version="2.0">
            <channel><title>Example &amp; Co</title>
            <item>
              <link>https://news.example.com/a</link>
              <title>First headline</title>
              <description>The team&amp;apos;s plan, explained.</description>
              <content:encoded>&lt;p&gt;Opening paragraph.&lt;/p&gt;&lt;p&gt;Second &lt;a href="x"&gt;linked&lt;/a&gt; paragraph.&lt;/p&gt;</content:encoded>
              <pubDate> Fri, 20 Nov 2026 22:55:09 -0500 </pubDate>
              <media:content url="https://img.example.com/a.jpg" type="image/jpeg" medium="image"/>
            </item>
            <item><link>https://news.example.com/a</link><title>Duplicate</title></item>
            <item><title>No link</title></item>
            </channel></rss>
        """.trimIndent()
        val items = NewsParsers.parseFeed(xml, rss, now = Long.MAX_VALUE)
        assertEquals(1, items.size)
        val a = items.single()
        assertEquals("First headline", a.title)
        assertEquals("The team's plan, explained.", a.summary)
        assertEquals(listOf("Opening paragraph.", "Second linked paragraph."), a.paragraphs)
        assertEquals("https://img.example.com/a.jpg", a.imageUrl)
        assertEquals(java.time.OffsetDateTime.parse("2026-11-21T03:55:09Z").toInstant().toEpochMilli(), a.publishedAt)
        assertEquals("Example & Co", NewsParsers.feedTitle(xml))
    }

    @Test fun cdataDescriptionGivesImageAndTextAndYnetCropIsUpgraded() {
        val xml = """
            <?xml version='1.0' encoding='utf-8' ?>
            <rss version="2.0"><channel><title><![CDATA[חדשות]]></title>
            <item>
              <title><![CDATA[כותרת ראשית]]></title>
              <description><![CDATA[<div><a href='https://x.co.il/1'><img src='https://pic.yit.co.il/p/abc_medium.jpg' width='100'></a></div>תקציר הכתבה]]></description>
              <link><![CDATA[https://news.example.co.il/article/1]]></link>
              <pubDate><![CDATA[Sat, 21 Nov 2026 09:54:18 +0200]]></pubDate>
            </item></channel></rss>
        """.trimIndent()
        val a = NewsParsers.parseFeed(xml, he, now = Long.MAX_VALUE).single()
        assertEquals("כותרת ראשית", a.title)
        assertEquals("תקציר הכתבה", a.summary)
        assertEquals("https://pic.yit.co.il/p/abc_large.jpg", a.imageUrl)
        assertTrue(he.isRtl)
        assertFalse(rss.isRtl)
    }

    @Test fun atomEntriesAreReadAndFarFutureDatesAreDropped() {
        val xml = """
            <feed xmlns="http://www.w3.org/2005/Atom"><title>Atom feed</title>
            <entry><title>Atom story</title><link rel="alternate" href="https://atom.example.com/s"/>
              <summary>Short &lt;b&gt;bold&lt;/b&gt; text</summary><updated>2030-01-01T00:00:00Z</updated></entry>
            </feed>
        """.trimIndent()
        val now = 1_800_000_000_000L
        val a = NewsParsers.parseFeed(xml, rss, now = now).single()
        assertEquals("https://atom.example.com/s", a.link)
        assertEquals("Short bold text", a.summary)
        assertNull(a.publishedAt)
    }

    @Test fun theWidestOfSeveralMediaSizesIsUsed() {
        val xml = """
            <rss xmlns:media="http://search.yahoo.com/mrss/" version="2.0"><channel>
            <item><title>Sized</title><link>https://news.example.com/s</link>
              <media:content width="140" url="https://img.example.com/small.jpg"/>
              <media:content width="460" url="https://img.example.com/large.jpg"/>
            </item></channel></rss>
        """.trimIndent()
        assertEquals("https://img.example.com/large.jpg", NewsParsers.parseFeed(xml, rss).single().imageUrl)
    }

    @Test fun cnnLiteListUsesAbsoluteLinksAndPathDates() {
        val cnn = NewsProviders.presets.first { it.id == "cnn" }
        val html = """
            <html><body><ul>
            <li class="card--lite"><a href="/2026/11/21/world/story-one">  Story one  </a></li>
            <li class="card--lite"><a href="/2026/11/20/politics/story-two">Story two</a></li>
            <li class="other"><a href="/ignored">Ignored</a></li>
            </ul></body></html>
        """.trimIndent()
        val items = NewsParsers.parseCnnLite(html, cnn)
        assertEquals(listOf("Story one", "Story two"), items.map { it.title })
        assertEquals("https://lite.cnn.com/2026/11/21/world/story-one", items[0].link)
        assertTrue(items[0].isDayOnly)
        assertEquals(java.time.LocalDate.of(2026, 11, 21).atStartOfDay(java.time.ZoneOffset.UTC).toInstant().toEpochMilli(),
            items[0].publishedAt)
    }

    @Test fun articlePagesPreferKnownBlocksThenFallBackToArticleParagraphs() {
        val lite = """<html><head><meta property="og:image" content="https://img/c.jpg"></head><body>
            <p class="paragraph--lite">One.</p><p class="paragraph--lite"> Two. </p><p>Footer text that is long enough to count anyway.</p></body></html>"""
        val body = NewsParsers.parseArticlePage(lite, "https://lite.cnn.com/x")
        assertEquals(listOf("One.", "Two."), body.paragraphs)
        assertEquals("https://img/c.jpg", body.imageUrl)

        val generic = """<html><body><nav><p>Navigation paragraph that is long enough to be counted.</p></nav>
            <article><p>Short.</p><p>The article's real first paragraph, comfortably over forty characters.</p></article></body></html>"""
        val fallback = NewsParsers.parseArticlePage(generic, "https://example.com/x")
        assertEquals(listOf("The article's real first paragraph, comfortably over forty characters."), fallback.paragraphs)
        assertNull(fallback.imageUrl)
    }

    @Test fun datesInCommonFeedShapesParse() {
        for (raw in listOf("Sat, 21 Nov 2026 06:00:09 GMT", "Sat, 21 Nov 2026 07:38:38 +0000", "2026-11-21T07:38:38+02:00",
            " Wed, 25 Nov 2026 05:00:00 -0500 ")) {
            assertTrue(raw, NewsParsers.parseDate(raw) != null)
        }
        assertNull(NewsParsers.parseDate("yesterday"))
    }
}
