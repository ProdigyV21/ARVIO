package com.arflix.tv.ui.screens.player.subtitles

import android.util.Base64
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

private const val TAG = "GeminiLive"
// Specialized streaming speech-to-text-translation model: lowest latency (~0.8s) because it
// translates continuously without waiting for speech turns. Trade-off: it ignores system
// instructions and exposes no quality/style/gender controls, so translation quality is fixed.
// The general (prompt-steerable) Live models were evaluated but rejected: the TEXT-capable
// ones aren't available on this key, and the reachable native-audio model adds ~3s latency.
private const val MODEL = "models/gemini-3.5-live-translate-preview"
private const val WS_BASE = "wss://generativelanguage.googleapis.com/ws/google.ai.generativelanguage.v1beta.GenerativeService.BidiGenerateContent"
private const val MIME_PCM_16K = "audio/pcm;rate=16000"
private const val CLEAR_DELAY_MS = 3_000L
// Minimum time a completed sentence stays on screen before the next queued one replaces it.
private const val MIN_LINE_MS = 900L
// If we fall this far behind on rapid dialogue, drop the oldest lines to catch back up.
private const val MAX_PENDING_LINES = 3
private val SENTENCE_TERMINATORS = charArrayOf('.', '!', '?', '…')
// Split a run of completed text into individual sentences (break after each terminator).
private val SENTENCE_SPLIT = Regex("(?<=[.!?…])\\s+")

enum class GeminiLiveState { DISCONNECTED, CONNECTING, READY, ERROR }

class GeminiLiveTranslationService(
    private val apiKeyProvider: () -> String,
    private val scope: CoroutineScope
) {
    /** BCP-47 code the live translation outputs (the user's preferred subtitle language). */
    @Volatile var targetLanguageCode: String = "he"

    private val _text = MutableStateFlow<String?>(null)
    val translatedText: StateFlow<String?> = _text.asStateFlow()

    private val _state = MutableStateFlow(GeminiLiveState.DISCONNECTED)
    val state: StateFlow<GeminiLiveState> = _state.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private var ws: WebSocket? = null
    private val audioQueue = Channel<ByteArray>(Channel.UNLIMITED)
    private var senderJob: Job? = null
    private var clearJob: Job? = null

    private var sentenceStartMs = 0L   // wall time when first chunk was queued after last sentence end
    private var firstFragmentLogged = false
    private var lastFragmentTimeMs = 0L

    // Segmentation: show one sentence at a time so lines from different speakers/utterances
    // never merge onto a single subtitle line. Completed sentences queue up and are drained
    // one at a time; the trailing in-progress text shows live once the queue is empty.
    private val segmentationLock = Any()
    private val pendingLines = ArrayDeque<String>()
    private var livePartial = StringBuilder()
    private var lineJob: Job? = null

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.SECONDS)
        .build()

    fun connect() {
        Log.d(TAG, "connect() state=${_state.value}")
        if (_state.value == GeminiLiveState.CONNECTING || _state.value == GeminiLiveState.READY) return
        val apiKey = apiKeyProvider()
        if (apiKey.isBlank()) {
            _state.value = GeminiLiveState.ERROR
            _errorMessage.value = "API key missing"
            return
        }
        _state.value = GeminiLiveState.CONNECTING
        _errorMessage.value = null

        val request = Request.Builder().url("$WS_BASE?key=$apiKey").build()
        ws = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(socket: WebSocket, response: Response) {
                Log.d(TAG, "WS open, sending setup")
                socket.send(buildSetupMessage())
                _state.value = GeminiLiveState.READY
                startSender()
            }
            override fun onMessage(socket: WebSocket, text: String) {
                handleMessage(text)
            }
            override fun onMessage(socket: WebSocket, bytes: ByteString) {
                handleMessage(bytes.utf8())
            }
            override fun onFailure(socket: WebSocket, t: Throwable, response: Response?) {
                val body = try { response?.body?.string() } catch (_: Exception) { null }
                Log.e(TAG, "WS failure code=${response?.code} body=$body err=${t.message}")
                _state.value = GeminiLiveState.ERROR
                _errorMessage.value = t.message ?: "Connection failed"
                stopSender()
            }
            override fun onClosed(socket: WebSocket, code: Int, reason: String) {
                Log.e(TAG, "WS closed code=$code reason=$reason")
                _state.value = GeminiLiveState.DISCONNECTED
                stopSender()
            }
        })
    }

    private fun buildSetupMessage() = JSONObject().apply {
        put("setup", JSONObject().apply {
            put("model", MODEL)
            put("generation_config", JSONObject().apply {
                // TEXT output keeps latency low (no dubbed-speech generation).
                put("response_modalities", JSONArray().apply { put("TEXT") })
                // The translate model's only real control: target language. It ignores
                // system instructions and has no quality/style/gender knobs, so we don't send any.
                put("translation_config", JSONObject().apply {
                    put("target_language_code", targetLanguageCode)
                })
            })
        })
    }.toString()

    /** Concatenate all text parts from serverContent.modelTurn (TEXT response modality). */
    private fun extractModelTurnText(content: JSONObject): String? {
        val modelTurn = content.optJSONObject("modelTurn")
            ?: content.optJSONObject("model_turn") ?: return null
        val parts = modelTurn.optJSONArray("parts") ?: return null
        val sb = StringBuilder()
        for (i in 0 until parts.length()) {
            val text = parts.optJSONObject(i)?.optString("text", "").orEmpty()
            if (text.isNotEmpty()) sb.append(text)
        }
        return sb.toString().ifBlank { null }
    }

    private fun handleMessage(raw: String) {
        try {
            val json = JSONObject(raw)
            if (json.has("setupComplete") || json.has("setup_complete")) {
                Log.d(TAG, "setupComplete")
                return
            }
            val content = json.optJSONObject("serverContent")
                ?: json.optJSONObject("server_content") ?: return

            // With TEXT modality the translated text arrives in modelTurn.parts[].text.
            // Fall back to outputTranscription for the AUDIO-modality shape so either works.
            val fragment = extractModelTurnText(content)
                ?: content.optJSONObject("outputTranscription")?.optString("text", "")
                ?: content.optJSONObject("output_transcription")?.optString("text", "")

            if (!fragment.isNullOrBlank()) {
                val now = System.currentTimeMillis()
                // A gap of >3s means the previous utterance ended without a clean boundary — reset
                // so stale text doesn't bleed into the next speaker's line.
                if (lastFragmentTimeMs > 0 && now - lastFragmentTimeMs > 3_000L) {
                    resetSegmentation()
                }
                lastFragmentTimeMs = now
                if (!firstFragmentLogged) {
                    val elapsedMs = if (sentenceStartMs > 0L) now - sentenceStartMs else -1L
                    Log.i(TAG, "⏱ first fragment after ${elapsedMs}ms | text=\"${fragment.trim()}\"")
                    firstFragmentLogged = true
                }

                // TEXT parts are incremental token chunks that carry their own spacing,
                // so append verbatim (no trim / no injected space) to avoid splitting words.
                synchronized(segmentationLock) {
                    livePartial.append(fragment)
                    extractCompletedSentences()
                    Log.d(TAG, "live: partial=\"${livePartial.toString().trim()}\" queued=${pendingLines.size}")
                    startDrainer()
                }
            }

            // Turn-based (general) model: a completed turn is a clean utterance boundary — flush
            // the trailing text as its own line and reset per-turn latency timing.
            if (content.optBoolean("turnComplete", false) || content.optBoolean("turn_complete", false)) {
                onTurnComplete()
            }
        } catch (e: Exception) {
            Log.w(TAG, "parse error: ${e.message}")
        }
    }

    private fun onTurnComplete() {
        synchronized(segmentationLock) {
            val rest = livePartial.toString().trim()
            if (rest.isNotEmpty()) {
                pendingLines.addLast(rest)
                while (pendingLines.size > MAX_PENDING_LINES) pendingLines.removeFirst()
                livePartial = StringBuilder()
                startDrainer()
            }
        }
        sentenceStartMs = 0L
        firstFragmentLogged = false
    }

    /**
     * Move every complete sentence out of [livePartial] into the [pendingLines] queue, leaving
     * only the trailing in-progress fragment. This is what stops two speakers' sentences from
     * ever being concatenated onto one line — each terminated sentence becomes its own line.
     */
    private fun extractCompletedSentences() {
        val s = livePartial.toString()
        val lastTerm = s.indexOfLast { it in SENTENCE_TERMINATORS }
        if (lastTerm < 0) return
        val completed = s.substring(0, lastTerm + 1)
        val rest = s.substring(lastTerm + 1)
        for (sentence in SENTENCE_SPLIT.split(completed)) {
            val t = sentence.trim()
            if (t.isNotEmpty()) pendingLines.addLast(t)
        }
        livePartial = StringBuilder(rest)
        // Bound the backlog so rapid dialogue doesn't drift ever further behind.
        while (pendingLines.size > MAX_PENDING_LINES) pendingLines.removeFirst()
    }

    /**
     * Drains queued sentences one at a time, holding each for [MIN_LINE_MS] so it's readable,
     * then shows the live in-progress text once caught up. Only one drainer runs at a time;
     * new sentences appended while it's draining are picked up by the loop.
     */
    private fun startDrainer() {
        synchronized(segmentationLock) {
            if (lineJob?.isActive == true) return
            lineJob = scope.launch {
                while (true) {
                    val line = synchronized(segmentationLock) {
                        if (pendingLines.isNotEmpty()) pendingLines.removeFirst() else null
                    } ?: break
                    _text.value = line
                    sentenceStartMs = 0L
                    firstFragmentLogged = false
                    delay(MIN_LINE_MS)
                }
                // Caught up — show whatever partial sentence is currently being spoken.
                val partial = synchronized(segmentationLock) {
                    livePartial.toString().trim()
                }
                if (partial.isNotEmpty()) _text.value = partial
                scheduleClear()
            }
        }
    }

    private fun scheduleClear() {
        clearJob?.cancel()
        clearJob = scope.launch {
            delay(CLEAR_DELAY_MS)
            val shouldClear = synchronized(segmentationLock) {
                pendingLines.isEmpty() && livePartial.isBlank()
            }
            if (shouldClear) _text.value = null
        }
    }

    private fun resetSegmentation() {
        synchronized(segmentationLock) {
            lineJob?.cancel()
            lineJob = null
            clearJob?.cancel()
            clearJob = null
            pendingLines.clear()
            livePartial = StringBuilder()
        }
        sentenceStartMs = 0L
        firstFragmentLogged = false
        _text.value = null
    }

    fun sendAudioChunk(pcm16Bytes: ByteArray, captureTimeMs: Long) {
        if (_state.value != GeminiLiveState.READY) return
        if (sentenceStartMs == 0L) {
            sentenceStartMs = System.currentTimeMillis()
            firstFragmentLogged = false
        }
        audioQueue.trySend(pcm16Bytes)
    }

    private fun startSender() {
        Log.d(TAG, "startSender launched")
        var n = 0
        senderJob = scope.launch {
            for (chunk in audioQueue) {
                val socket = ws ?: break
                n++
                if (n <= 3 || n % 50 == 0) Log.d(TAG, "chunk #$n")
                val encoded = Base64.encodeToString(chunk, Base64.NO_WRAP)
                socket.send(JSONObject().apply {
                    put("realtimeInput", JSONObject().apply {
                        put("audio", JSONObject().apply {
                            put("data", encoded)
                            put("mimeType", MIME_PCM_16K)
                        })
                    })
                }.toString())
            }
            Log.d(TAG, "sender ended after $n chunks")
        }
    }

    private fun stopSender() {
        senderJob?.cancel()
        senderJob = null
    }

    fun disconnect() {
        stopSender()
        while (audioQueue.tryReceive().isSuccess) { /* discard */ }
        synchronized(segmentationLock) {
            lineJob?.cancel()
            lineJob = null
            clearJob?.cancel()
            clearJob = null
            pendingLines.clear()
            livePartial = StringBuilder()
        }
        ws?.close(1000, "stopped")
        ws = null
        _state.value = GeminiLiveState.DISCONNECTED
        _text.value = null
        sentenceStartMs = 0L
        firstFragmentLogged = false
        lastFragmentTimeMs = 0L
    }
}
