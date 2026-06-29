package com.arflix.tv.ui.screens.player

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.ConcurrentHashMap

class SubtitleTranslationManager(
    private var service: SubtitleTranslationService,
    internal var targetLanguage: String,
    private val scope: CoroutineScope
) {
    companion object {
        const val MOCK_MODE = false
        // Groq free tier: 30 RPM. Subtitles change every 2-4s naturally (~15-20 RPM).
        // No artificial rate limit needed — just don't fire concurrent requests.
        // If we get a 429, we back off 5s.
        private const val BATCH_WINDOW_MS = 150L  // wait up to 150ms for more items to batch
    }

    var isEnabled: Boolean = false
    var removeHearingImpaired: Boolean = true

    var onTranslatingChanged: ((Boolean) -> Unit)? = null
    var onBatchResult: ((success: Boolean, error: String?) -> Unit)? = null

    val translatedCount: Int get() = cache.size

    @Volatile var isTranslating: Boolean = false
        private set

    private val cache = ConcurrentHashMap<String, String>()
    // Tracks texts currently queued but not yet translated, to avoid double-queuing the same text
    // from both the real-time path and preTranslateWindow racing on the same cue.
    private val inFlight = ConcurrentHashMap<String, CompletableDeferred<String>>()
    @Volatile private var pendingCount = 0
    private var hideTranslatingJob: Job? = null

    private data class PendingItem(val text: String, val deferred: CompletableDeferred<String>)
    private val queue = Channel<PendingItem>(Channel.UNLIMITED)

    init {
        if (!MOCK_MODE) {
            scope.launch { processBatches() }
        }
    }

    fun updateService(apiKey: String, model: SubtitleAiModel) {
        service = SubtitleTranslationService(
            apiKeyProvider = { apiKey },
            modelProvider = { model }
        )
    }

    private suspend fun processBatches() {
        val batch = mutableListOf<PendingItem>()
        while (true) {
            // Block until the first item is available
            val first = queue.receive()
            batch.add(first)

            // Collect any additional items that arrive within BATCH_WINDOW_MS.
            // This handles burst cache misses (e.g., right after a seek) efficiently.
            val deadline = System.currentTimeMillis() + BATCH_WINDOW_MS
            while (batch.size < 40) {
                val remaining = deadline - System.currentTimeMillis()
                if (remaining <= 0L) break
                val next = withTimeoutOrNull(remaining) { queue.receive() } ?: break
                batch.add(next)
            }

            val texts = batch.map { it.text }
            val result = service.translateBatch(texts, targetLanguage)
            if (!result.success) {
                onBatchResult?.invoke(false, result.errorMessage)
                // On error: complete deferreds with original text so the caller doesn't hang,
                // but do NOT cache — the next render will retry rather than permanently show English.
                batch.forEachIndexed { i, item ->
                    inFlight.remove(item.text)
                    item.deferred.complete(item.text)
                }
                batch.clear()
                delay(5_000L)
                continue
            }
            onBatchResult?.invoke(true, null)
            batch.forEachIndexed { i, item ->
                val translated = result.lines.getOrElse(i) { item.text }
                cache[item.text] = translated
                inFlight.remove(item.text)
                item.deferred.complete(translated)
            }
            batch.clear()
        }
    }

    fun getCached(text: String): String? = cache[text]

    fun isInFlight(text: String): Boolean = inFlight.containsKey(text)

    suspend fun translate(text: String): String {
        cache[text]?.let { return it }
        // If the same text is already queued (e.g. preTranslateWindow raced us), join it.
        inFlight[text]?.let { return it.await() }

        val deferred = CompletableDeferred<String>()
        inFlight[text] = deferred
        val depth = pendingCount++
        if (depth == 0) {
            isTranslating = true
            onTranslatingChanged?.invoke(true)
        }
        queue.send(PendingItem(text, deferred))
        return try {
            deferred.await()
        } finally {
            if (--pendingCount == 0) {
                hideTranslatingJob?.cancel()
                hideTranslatingJob = scope.launch {
                    delay(1500)
                    if (pendingCount == 0) {
                        isTranslating = false
                        onTranslatingChanged?.invoke(false)
                    }
                }
            }
        }
    }

    fun reset() {
        cache.clear()
        inFlight.clear()
        pendingCount = 0
        isTranslating = false
        onTranslatingChanged?.invoke(false)
    }

    suspend fun preTranslateWindow(texts: List<String>) {
        val uncached = texts.filter { !cache.containsKey(it) && !inFlight.containsKey(it) }
        if (uncached.isEmpty()) return
        uncached.chunked(40).forEach { chunk ->
            val result = service.translateBatch(chunk, targetLanguage)
            if (result.success) {
                onBatchResult?.invoke(true, null)
                chunk.forEachIndexed { i, text ->
                    cache[text] = result.lines.getOrElse(i) { text }
                }
            } else {
                onBatchResult?.invoke(false, result.errorMessage)
                delay(5_000L)
                return
            }
        }
    }
}
