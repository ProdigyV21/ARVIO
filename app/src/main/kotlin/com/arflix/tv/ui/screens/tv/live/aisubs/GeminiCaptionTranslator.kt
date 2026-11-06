package com.arflix.tv.ui.screens.tv.live.aisubs

import com.arflix.tv.ui.screens.player.subtitles.SubtitleAiModel
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService

/** Translates one sentence of a channel's own captions into timed lines. */
internal interface LiveCaptionTranslator {
    suspend fun translate(sentence: CaptionSentence, targetLanguageName: String, rtl: Boolean): LiveEngineResult
}

/**
 * Caption sentences go through the app's existing Gemini subtitle translation (the same model,
 * prompt and retry rules as AI subtitles for films), as one-line batches: the text is already
 * there, so this is faster and cheaper than sending audio, and makes no listening mistakes.
 */
internal class GeminiCaptionTranslator(private val apiKey: () -> String) : LiveCaptionTranslator {
    private val service = SubtitleTranslationService(
        apiKeyProvider = apiKey,
        modelProvider = { SubtitleAiModel.GEMINI_FLASH_25 },
    )

    override suspend fun translate(sentence: CaptionSentence, targetLanguageName: String, rtl: Boolean): LiveEngineResult {
        if (apiKey().isBlank()) return LiveEngineResult.InvalidKey
        val result = service.translateBatch(listOf(sentence.text), targetLanguageName)
        if (!result.success) return errorResult(result.errorMessage.orEmpty())
        val line = result.lines.firstOrNull()?.trim().orEmpty()
        if (line.isEmpty()) return LiveEngineResult.Cues(emptyList(), null)
        val endUs = maxOf(sentence.endUs, sentence.startUs) + CAPTION_TAIL_US
        return LiveEngineResult.Cues(listOf(LiveCue(sentence.startUs, endUs, line, rtl)), null)
    }

    companion object {
        /** Captions are typed as they are spoken; the line stays a little after its last word. */
        const val CAPTION_TAIL_US = 1_500_000L

        fun errorResult(message: String): LiveEngineResult = when {
            message == "RATE_LIMITED" -> LiveEngineResult.QuotaExceeded
            message.startsWith("HTTP 401") || message.startsWith("HTTP 403") ||
                message.contains("API_KEY_INVALID") || message.contains("API key not valid") -> LiveEngineResult.InvalidKey
            message.contains("Unable to resolve host", ignoreCase = true) ||
                message.contains("Failed to connect", ignoreCase = true) -> LiveEngineResult.Offline
            else -> LiveEngineResult.Failed(message.take(80))
        }
    }
}
