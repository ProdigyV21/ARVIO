package com.arflix.tv.data.telegram

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

/** Which messages a global search page asks Telegram for. */
enum class TelegramSearchFilter { ALL, VIDEO, DOCUMENT }

/**
 * One page of a global Telegram search.
 *
 * [answered] is false when Telegram gave no answer in time (TDLib holds rate-limited requests
 * rather than failing them) — the page says nothing about what the groups hold. [hasMore] is true
 * when the search has results beyond this page.
 */
data class TelegramSearchPage(
    val videos: List<TelegramVideoMessage>,
    val answered: Boolean,
    val hasMore: Boolean
)

/**
 * The phrase-by-phrase part of a Telegram lookup, apart from Android and TDLib so it can be tested.
 *
 * - Phrasings go out a pair at a time: global search is what Telegram rate-limits, and a burst of
 *   all of them made most go unanswered.
 * - The core phrasings always run; the fallback ones only when the core found no matching file,
 *   and they stop at the first pair that does.
 * - One unfiltered page per phrasing. When that page has more results behind it, newer text or
 *   photo posts may have pushed the videos off it (the search is newest-first), so the phrasing is
 *   repeated for videos and for documents — only then, to keep the request count down.
 * - [Outcome.complete] is false as soon as any request went unanswered: a lookup with a gap is not
 *   an answer and must not be cached as one.
 */
class TelegramPhraseSearch(
    private val fetchPage: suspend (query: String, filter: TelegramSearchFilter) -> TelegramSearchPage,
    private val parallelQueries: Int = 2
) {
    data class Outcome(
        /** Matching files, first-found order, one per (file name, size). */
        val matched: List<TelegramVideoMessage>,
        val complete: Boolean,
        val phrasingsSent: Int,
        val requestsSent: Int,
        val distinctFiles: Int
    )

    suspend fun run(
        core: List<String>,
        fallback: List<String>,
        keep: (TelegramVideoMessage) -> Boolean,
        matches: (TelegramVideoMessage) -> Boolean,
        onMatchedGrew: suspend (List<TelegramVideoMessage>) -> Unit = {}
    ): Outcome {
        val seen = HashSet<Pair<String, Long>>()
        val matched = LinkedHashMap<Pair<String, Long>, TelegramVideoMessage>()
        var complete = true
        var phrasingsSent = 0
        var requestsSent = 0

        suspend fun searchBatch(batch: List<String>) {
            val results = coroutineScope {
                batch.map { query -> async { searchPhrase(query) } }.awaitAll()
            }
            var grew = false
            for (result in results) {
                requestsSent += result.requests
                if (!result.complete) complete = false
                for (msg in result.videos) {
                    if (!keep(msg)) continue
                    val fileKey = msg.fileName to msg.fileSize
                    if (!seen.add(fileKey) || !matches(msg)) continue
                    matched[fileKey] = msg
                    grew = true
                }
            }
            phrasingsSent += batch.size
            if (grew) onMatchedGrew(matched.values.toList())
        }

        for (batch in core.chunked(parallelQueries)) searchBatch(batch)
        if (matched.isEmpty()) {
            for (batch in fallback.chunked(parallelQueries)) {
                searchBatch(batch)
                if (matched.isNotEmpty()) break
            }
        }
        return Outcome(matched.values.toList(), complete, phrasingsSent, requestsSent, seen.size)
    }

    private class PhraseResult(val videos: List<TelegramVideoMessage>, val complete: Boolean, val requests: Int)

    private suspend fun searchPhrase(query: String): PhraseResult {
        val first = fetchPage(query, TelegramSearchFilter.ALL)
        if (!first.answered) return PhraseResult(emptyList(), complete = false, requests = 1)
        if (!first.hasMore) return PhraseResult(first.videos, complete = true, requests = 1)
        // The unfiltered page was cut off: ask for the file types themselves.
        val videos = first.videos.toMutableList()
        var complete = true
        for (filter in listOf(TelegramSearchFilter.VIDEO, TelegramSearchFilter.DOCUMENT)) {
            val page = fetchPage(query, filter)
            if (!page.answered) complete = false
            videos += page.videos
        }
        return PhraseResult(videos, complete, requests = 3)
    }
}

/**
 * Completed Telegram lookups by title/episode. Only a complete lookup is stored: a timed-out or
 * interrupted one says nothing about what the groups hold, and caching it hid Telegram sources for
 * hours (2h for a series).
 */
class TelegramResultCache<T>(private val now: () -> Long = System::currentTimeMillis) {
    private class Entry<T>(val results: List<T>, val expiresAt: Long)

    private val entries = java.util.concurrent.ConcurrentHashMap<String, Entry<T>>()

    /** Returns whether [results] were stored. */
    fun put(key: String, results: List<T>, complete: Boolean, ttlMs: Long): Boolean {
        if (!complete) return false
        entries[key] = Entry(results, now() + ttlMs)
        return true
    }

    fun get(key: String): List<T>? = entries[key]?.takeIf { now() < it.expiresAt }?.results
}
