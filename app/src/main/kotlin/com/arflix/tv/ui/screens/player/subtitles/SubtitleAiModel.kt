package com.arflix.tv.ui.screens.player.subtitles

// Both names are historical: the enum value is persisted in DataStore and cloud backups, so
// renaming either one breaks restore on other devices. They mean "the Groq model" and "the Gemini
// model" — the actual model ids live in SubtitleTranslationService.
enum class SubtitleAiModel {
    // Now maps to openai/gpt-oss-120b (llama-3.3-70b decommissioned August 2026).
    GROQ_LLAMA_70B,
    // Now maps to gemini-3.5-flash-lite (2.5 retired July 2026). Name kept: the enum value is
    // persisted in DataStore and cloud backups — renaming breaks restore on other devices.
    GEMINI_FLASH_25,
    // Anthropic Claude Haiku (model id in ClaudeMessages.kt). Text only: features that stream audio
    // to the Gemini Live API still need the Gemini model, as with Groq.
    CLAUDE_HAIKU
}

/**
 * The provider a key belongs to, from its fixed prefix; null when the prefix says nothing. Lets a key
 * entered on the phone (where the provider page was picked, not the TV's model setting) switch the
 * model to match, so a Claude or Gemini key never sits under the wrong provider.
 */
fun subtitleAiModelForKey(key: String): SubtitleAiModel? {
    val trimmed = key.trim()
    return when {
        trimmed.startsWith("gsk_") -> SubtitleAiModel.GROQ_LLAMA_70B
        trimmed.startsWith("AIza") -> SubtitleAiModel.GEMINI_FLASH_25
        trimmed.startsWith(CLAUDE_KEY_PREFIX) -> SubtitleAiModel.CLAUDE_HAIKU
        else -> null
    }
}
