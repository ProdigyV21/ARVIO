package com.arflix.tv.data.telegram

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TelegramPhraseSearchTest {

    private fun video(name: String, chatId: Long = 1L, size: Long = name.length * 1_000L) = TelegramVideoMessage(
        messageId = size,
        chatId = chatId,
        fileName = name,
        fileId = name.hashCode(),
        fileSize = size,
        duration = 0,
        mimeType = "video/x-matroska",
        caption = ""
    )

    private val episodeMatcher: (TelegramVideoMessage) -> Boolean = { it.fileName.contains("S01E04") }

    /** Answers from [pages] by (query, filter), recording every request. */
    private class FakeTelegram(private val pages: Map<Pair<String, TelegramSearchFilter>, TelegramSearchPage>) {
        val requests = mutableListOf<Pair<String, TelegramSearchFilter>>()
        suspend fun fetch(query: String, filter: TelegramSearchFilter): TelegramSearchPage {
            synchronized(requests) { requests += query to filter }
            return pages[query to filter] ?: TelegramSearchPage(emptyList(), answered = true, hasMore = false)
        }
    }

    private fun answered(vararg videos: TelegramVideoMessage, hasMore: Boolean = false) =
        TelegramSearchPage(videos.toList(), answered = true, hasMore = hasMore)

    private val unanswered = TelegramSearchPage(emptyList(), answered = false, hasMore = false)

    @Test
    fun `a request Telegram did not answer makes the lookup incomplete but keeps what was found`() = runBlocking {
        val telegram = FakeTelegram(
            mapOf(
                ("show ע1פ4" to TelegramSearchFilter.ALL) to unanswered,
                ("show ע1 פ4" to TelegramSearchFilter.ALL) to answered(video("Show.S01E04.1080p.mkv"))
            )
        )
        val outcome = TelegramPhraseSearch(telegram::fetch).run(
            core = listOf("show ע1פ4", "show ע1 פ4"),
            fallback = emptyList(),
            keep = { true },
            matches = episodeMatcher
        )
        assertFalse("a lookup with an unanswered request must not be cached as an answer", outcome.complete)
        assertEquals(listOf("Show.S01E04.1080p.mkv"), outcome.matched.map { it.fileName })
    }

    @Test
    fun `a lookup where every request was answered is complete even with no matches`() = runBlocking {
        val telegram = FakeTelegram(emptyMap())
        val outcome = TelegramPhraseSearch(telegram::fetch).run(
            core = listOf("show ע1פ4", "show ע1 פ4"),
            fallback = emptyList(),
            keep = { true },
            matches = episodeMatcher
        )
        assertTrue(outcome.complete)
        assertTrue(outcome.matched.isEmpty())
    }

    @Test
    fun `a first page cut off by non-video posts is repeated for videos and documents`() = runBlocking {
        // The unfiltered page holds only text/photo posts (no videos) and has more behind it —
        // the matching file sits further back and only the typed searches reach it.
        val telegram = FakeTelegram(
            mapOf(
                ("show פרק 4" to TelegramSearchFilter.ALL) to answered(hasMore = true),
                ("show פרק 4" to TelegramSearchFilter.VIDEO) to answered(video("Show.S01E04.720p.mp4")),
                ("show פרק 4" to TelegramSearchFilter.DOCUMENT) to answered(video("Show.S01E04.1080p.mkv"))
            )
        )
        val outcome = TelegramPhraseSearch(telegram::fetch).run(
            core = listOf("show פרק 4"),
            fallback = emptyList(),
            keep = { true },
            matches = episodeMatcher
        )
        assertEquals(
            listOf(
                "show פרק 4" to TelegramSearchFilter.ALL,
                "show פרק 4" to TelegramSearchFilter.VIDEO,
                "show פרק 4" to TelegramSearchFilter.DOCUMENT
            ),
            telegram.requests
        )
        assertEquals(setOf("Show.S01E04.720p.mp4", "Show.S01E04.1080p.mkv"), outcome.matched.map { it.fileName }.toSet())
        assertTrue(outcome.complete)
    }

    @Test
    fun `a page with nothing behind it costs one request`() = runBlocking {
        val telegram = FakeTelegram(
            mapOf(("show ע1פ4" to TelegramSearchFilter.ALL) to answered(video("Show.S01E04.mkv")))
        )
        TelegramPhraseSearch(telegram::fetch).run(
            core = listOf("show ע1פ4"),
            fallback = emptyList(),
            keep = { true },
            matches = episodeMatcher
        )
        assertEquals(listOf("show ע1פ4" to TelegramSearchFilter.ALL), telegram.requests)
    }

    @Test
    fun `an unanswered first page is not retried with filters`() = runBlocking {
        // Telegram is throttling: more requests only queue behind the held ones.
        val telegram = FakeTelegram(mapOf(("show ע1פ4" to TelegramSearchFilter.ALL) to unanswered))
        val outcome = TelegramPhraseSearch(telegram::fetch).run(
            core = listOf("show ע1פ4"),
            fallback = emptyList(),
            keep = { true },
            matches = episodeMatcher
        )
        assertEquals(1, telegram.requests.size)
        assertFalse(outcome.complete)
    }

    @Test
    fun `fallback phrasings run only when the core found nothing and stop at the first match`() = runBlocking {
        val telegram = FakeTelegram(
            mapOf(("show s1 e4" to TelegramSearchFilter.ALL) to answered(video("Show S01E04.mkv")))
        )
        val outcome = TelegramPhraseSearch(telegram::fetch, parallelQueries = 2).run(
            core = listOf("show ע1פ4", "show ע1 פ4"),
            fallback = listOf("show s1e4", "show s1 e4", "show s01 e04", "show פ4"),
            keep = { true },
            matches = episodeMatcher
        )
        assertEquals(listOf("Show S01E04.mkv"), outcome.matched.map { it.fileName })
        // Two core + the first fallback pair; the second fallback pair is never sent.
        assertEquals(4, outcome.phrasingsSent)
        assertFalse(telegram.requests.any { it.first == "show s01 e04" || it.first == "show פ4" })

        val coreHit = FakeTelegram(
            mapOf(("show ע1פ4" to TelegramSearchFilter.ALL) to answered(video("Show.S01E04.mkv")))
        )
        TelegramPhraseSearch(coreHit::fetch).run(
            core = listOf("show ע1פ4"),
            fallback = listOf("show s1e4"),
            keep = { true },
            matches = episodeMatcher
        )
        assertFalse(coreHit.requests.any { it.first == "show s1e4" })
    }

    @Test
    fun `excluded chats and non-matching files are dropped`() = runBlocking {
        val telegram = FakeTelegram(
            mapOf(
                ("show ע1פ4" to TelegramSearchFilter.ALL) to answered(
                    video("Show.S01E04.excluded.mkv", chatId = 99L),
                    video("Show.S01E05.mkv"),
                    video("Show.S01E04.kept.mkv")
                )
            )
        )
        val outcome = TelegramPhraseSearch(telegram::fetch).run(
            core = listOf("show ע1פ4"),
            fallback = emptyList(),
            keep = { it.chatId != 99L },
            matches = episodeMatcher
        )
        assertEquals(listOf("Show.S01E04.kept.mkv"), outcome.matched.map { it.fileName })
    }

    @Test
    fun `result cache keeps only complete lookups and expires them`() {
        var now = 1_000L
        val cache = TelegramResultCache<String>(now = { now })
        assertFalse(cache.put("tt1:1:4", listOf("partial"), complete = false, ttlMs = 60_000L))
        assertEquals(null, cache.get("tt1:1:4"))
        assertTrue(cache.put("tt1:1:4", listOf("a", "b"), complete = true, ttlMs = 60_000L))
        assertEquals(listOf("a", "b"), cache.get("tt1:1:4"))
        now += 60_000L
        assertEquals(null, cache.get("tt1:1:4"))
    }
}
