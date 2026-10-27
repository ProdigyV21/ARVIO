package com.arflix.tv.ui.screens.player.subtitles

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.CancellationException

class SubtitleTranslationManager(
    private var service: SubtitleTranslationService,
    targetLanguage: String,
    private val scope: CoroutineScope
) {
    /**
     * Language the AI translates into (a name, e.g. "Simplified Chinese"). Cached translations
     * are keyed by it, so switching languages never shows a line translated for the old one, and
     * the on-screen frame is invalidated so an in-flight result for the old language is dropped.
     */
    @Volatile
    internal var targetLanguage: String = targetLanguage
        set(value) {
            if (field != value) {
                field = value
                bilingualBus.resetSecondary()
            }
        }
    companion object {
        const val MOCK_MODE = false
        // Groq free tier: 30 RPM. Subtitles change every 2-4s naturally (~15-20 RPM).
        // No artificial rate limit needed — just don't fire concurrent requests.
        // If we get a 429, we back off 5s.
        private const val BATCH_WINDOW_MS = 150L  // wait up to 150ms for more items to batch
        /** Earlier lines (with their translations) sent as read-only context per request. */
        const val CONTEXT_LINES = 8
        /** Lines per look-ahead request: big enough that fixed prompt overhead is small. */
        const val CHUNK_LINES = 50
    }

    var isEnabled: Boolean = false
        set(value) {
            if (field != value) bilingualBus.clear()
            field = value
        }

    // ── Bilingual subtitles ─────────────────────────────────────────────────
    /**
     * On-screen AI subtitle frame (source text + translation) consumed by the Compose
     * BilingualSubtitleOverlay. While AI translation is active the renderer publishes here
     * instead of rewriting Media3 cue text.
     */
    val bilingualBus = com.arflix.tv.ui.screens.player.subtitles.bilingual.BilingualCueBus()

    /** What the AI subtitle layer shows. Synced from the persisted settings by PlayerScreen. */
    @Volatile
    var displayMode: com.arflix.tv.ui.screens.player.subtitles.bilingual.BilingualDisplayMode =
        com.arflix.tv.ui.screens.player.subtitles.bilingual.BilingualDisplayMode.BILINGUAL

    /**
     * Where the second language comes from while the bilingual layer is active:
     * AI translation of the primary (path B, ARVIO's existing Gemini/Groq flow), or a second
     * embedded subtitle track of the same file (path A — never calls the translation API).
     */
    enum class BilingualSource { AI_TRANSLATION, EMBEDDED_TRACK }

    @Volatile
    var bilingualSource: BilingualSource = BilingualSource.AI_TRANSLATION
        set(value) {
            if (field != value) bilingualBus.resetSecondary()
            field = value
        }

    /** Path A's secondary-line provider (null = primary only). */
    @Volatile
    var secondarySource: com.arflix.tv.ui.screens.player.subtitles.bilingual.DualTrackSecondarySource? = null
        set(value) {
            if (field !== value) bilingualBus.resetSecondary()
            field = value
        }

    /** Short user-facing notice about the smart bilingual mode (shown briefly by the overlay). */
    val bilingualNotice = kotlinx.coroutines.flow.MutableStateFlow<com.arflix.tv.ui.screens.player.subtitles.bilingual.BilingualNotice?>(null)

    /** Called by the cue publisher after every path-A lookup (drives the low-confidence fallback). */
    @Volatile
    var onSecondaryLookup: ((com.arflix.tv.ui.screens.player.subtitles.bilingual.DualTrackSecondarySource.Lookup) -> Unit)? = null

    /** True when the current display mode needs translations at all (saves API calls otherwise). */
    val translationNeeded: Boolean
        get() = isEnabled && displayMode.needsTranslation && bilingualSource == BilingualSource.AI_TRANSLATION

    /**
     * Translates [text] through the normal queue/cache and reports whether it really succeeded.
     * On failure the existing queue completes with the source text and does not cache it.
     */
    suspend fun translateDetailed(text: String): Pair<String, Boolean> {
        val lang = targetLanguage
        val result = translate(text, lang)
        val ok = cached(cacheKey(lang, text)) != null
        return result to ok
    }

    private fun cacheKey(lang: String, text: String): String = lang + '\u0001' + text
    var removeHearingImpaired: Boolean = true
    @Volatile var removeSubtitleHearingImpaired: Boolean = false

    var onTranslatingChanged: ((Boolean) -> Unit)? = null
    var onBatchResult: ((success: Boolean, error: String?) -> Unit)? = null
    /**
     * Fired when translation is active but the cues carry no extractable text — i.e. the chosen
     * AI source is an image track (PGS/VOBSUB). Metadata alone can't be trusted to catch this
     * (containers report null/generic MIME), so this is the runtime backstop that stops AI from
     * silently rendering the untranslated source language.
     */
    var onUntranslatableSource: (() -> Unit)? = null

    val translatedCount: Int get() = cache.size

    @Volatile var isTranslating: Boolean = false
        private set

    private val cache = ConcurrentHashMap<String, String>()

    /** Persistent store behind [cache] (set by the player; null in tests). */
    @Volatile var diskCache: TranslationDiskCache? = null
        set(value) {
            field = value
            if (value != null) scope.launch(kotlinx.coroutines.Dispatchers.IO) { value.load() }
        }

    /** Title of what is playing ("Show S01E02 – Episode"), sent so the model knows the setting. */
    @Volatile var contentTitle: String? = null

    /** The latest translated lines in playback order (source → translation), sent as context. */
    private val recent = ArrayDeque<Pair<String, String>>()
    private val recentLock = Any()

    private fun contextFor(batch: List<String>, lang: String): List<Pair<String, String>> = synchronized(recentLock) {
        val skip = batch.toHashSet()
        recent.filter { it.first !in skip && cache[cacheKey(lang, it.first)] != null }.takeLast(CONTEXT_LINES)
    }

    private fun remember(lang: String, pairs: List<Pair<String, String>>) {
        synchronized(recentLock) {
            pairs.forEach { recent.addLast(it) }
            while (recent.size > CONTEXT_LINES * 2) recent.removeFirst()
        }
        diskCache?.let { disk ->
            val entries = pairs.filter { it.first != it.second }.associate { cacheKey(lang, it.first) to it.second }
            if (entries.isNotEmpty()) scope.launch(kotlinx.coroutines.Dispatchers.IO) { disk.putAll(entries) }
        }
    }

    /** Memory first, then the persistent cache (promoted into memory). */
    private fun cached(key: String): String? =
        cache[key] ?: diskCache?.get(key)?.also { cache[key] = it }
    // Tracks texts currently queued but not yet translated, to avoid double-queuing the same text
    // from both the real-time path and preTranslateWindow racing on the same cue.
    private val inFlight = ConcurrentHashMap<String, CompletableDeferred<String>>()
    private val pendingCount = AtomicInteger(0)
    private var hideTranslatingJob: Job? = null

    private data class PendingItem(val text: String, val lang: String, val deferred: CompletableDeferred<String>)
    private val queue = Channel<PendingItem>(Channel.UNLIMITED)

    init {
        if (!MOCK_MODE) {
            scope.launch { processBatches() }
        }
    }

    fun updateService(
        apiKey: String,
        model: SubtitleAiModel,
        customConfigProvider: () -> com.arflix.tv.ui.screens.player.subtitles.ai.CustomAiConfig? = { null },
    ) {
        service = SubtitleTranslationService(
            apiKeyProvider = { apiKey },
            modelProvider = { model },
            customConfigProvider = customConfigProvider,
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

            // One API request per target language (a language switch mid-batch is rare).
            val lang = batch.first().lang
            val sameLang = batch.filter { it.lang == lang }
            batch.filter { it.lang != lang }.forEach { queue.trySend(it) }
            batch.retainAll(sameLang)
            val texts = batch.map { it.text }
            val result = try {
                service.translateBatch(texts, lang, contextFor(texts, lang), contentTitle)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                TranslationResult(
                    lines = emptyList(),
                    success = false,
                    errorMessage = e.message ?: "Translation error"
                )
            }
            if (!result.success) {
                onBatchResult?.invoke(false, result.errorMessage)
                // On error: complete deferreds with original text so the caller doesn't hang,
                // but do NOT cache — the next render will retry rather than permanently show English.
                batch.forEachIndexed { i, item ->
                    inFlight.remove(cacheKey(item.lang, item.text))
                    item.deferred.complete(item.text)
                }
                batch.clear()
                delay(5_000L)
                continue
            }
            onBatchResult?.invoke(true, null)
            val pairs = ArrayList<Pair<String, String>>(batch.size)
            batch.forEachIndexed { i, item ->
                val translated = result.lines.getOrElse(i) { item.text }
                cache[cacheKey(item.lang, item.text)] = translated
                inFlight.remove(cacheKey(item.lang, item.text))
                item.deferred.complete(translated)
                pairs += item.text to translated
            }
            remember(lang, pairs)
            batch.clear()
        }
    }

    /** Cached translation of [text] into the CURRENT target language. */
    fun getCached(text: String): String? = cached(cacheKey(targetLanguage, text))

    fun isInFlight(text: String): Boolean = inFlight.containsKey(cacheKey(targetLanguage, text))

    suspend fun translate(text: String): String = translate(text, targetLanguage)

    private suspend fun translate(text: String, lang: String): String {
        val key = cacheKey(lang, text)
        cached(key)?.let { return it }
        // If the same text is already queued (e.g. preTranslateWindow raced us), join it.
        inFlight[key]?.let { return it.await() }

        val deferred = CompletableDeferred<String>()
        inFlight[key] = deferred
        val depth = pendingCount.getAndIncrement()
        if (depth == 0) {
            isTranslating = true
            onTranslatingChanged?.invoke(true)
        }
        queue.send(PendingItem(text, lang, deferred))
        return try {
            deferred.await()
        } finally {
            if (pendingCount.decrementAndGet() == 0) {
                hideTranslatingJob?.cancel()
                hideTranslatingJob = scope.launch {
                    delay(1500)
                    if (pendingCount.get() == 0) {
                        isTranslating = false
                        onTranslatingChanged?.invoke(false)
                    }
                }
            }
        }
    }

    /**
     * Clears what is on screen and what is queued. Translations stay cached: they are keyed by
     * target language and source text, so they are valid for any track, episode or re-watch, and
     * clearing them only paid for the same lines again.
     */
    fun reset() {
        bilingualBus.clear()
        inFlight.clear()
        synchronized(recentLock) { recent.clear() }
        if (pendingCount.get() == 0) {
            isTranslating = false
            onTranslatingChanged?.invoke(false)
        }
    }

    suspend fun preTranslateWindow(texts: List<String>) {
        // The lookahead triggers fire whenever a text track renders cues — including tracks
        // selected under the hood by "find best match" with AI translation off. Never spend
        // API requests unless translation is actually active.
        if (!isEnabled) return
        // Bilingual subtitles: "Original only" / "Off" never need translations.
        if (!displayMode.needsTranslation) return
        // Path A (second embedded track) never translates.
        if (bilingualSource != BilingualSource.AI_TRANSLATION) return
        val lang = targetLanguage
        val uncached = texts.distinct().filter { cached(cacheKey(lang, it)) == null && !inFlight.containsKey(cacheKey(lang, it)) }
        if (uncached.isEmpty()) return
        uncached.chunked(CHUNK_LINES).forEach { chunk ->
            // Claim the lines so the real-time path joins this request instead of paying twice.
            val claims = chunk.associateWith { CompletableDeferred<String>() }
            claims.forEach { (t, d) -> inFlight.putIfAbsent(cacheKey(lang, t), d) }
            val result = try {
                service.translateBatch(chunk, lang, contextFor(chunk, lang), contentTitle)
            } catch (e: CancellationException) {
                claims.forEach { (t, d) -> inFlight.remove(cacheKey(lang, t), d); d.complete(t) }
                throw e
            }
            if (result.success) {
                onBatchResult?.invoke(true, null)
                val pairs = ArrayList<Pair<String, String>>(chunk.size)
                chunk.forEachIndexed { i, text ->
                    val tr = result.lines.getOrElse(i) { text }
                    cache[cacheKey(lang, text)] = tr
                    pairs += text to tr
                }
                remember(lang, pairs)
                claims.forEach { (t, d) -> inFlight.remove(cacheKey(lang, t), d); d.complete(cache[cacheKey(lang, t)] ?: t) }
            } else {
                claims.forEach { (t, d) -> inFlight.remove(cacheKey(lang, t), d); d.complete(t) }
                onBatchResult?.invoke(false, result.errorMessage)
                delay(5_000L)
                return
            }
        }
    }
}
