package com.arflix.tv.ui.screens.player

enum class SubtitleAiModel {
    GROQ_LLAMA_70B,
    GEMINI_FLASH_25,
    GEMINI_FLASH_LITE;

    val geminiModelId: String?
        get() = when (this) {
            GROQ_LLAMA_70B -> null
            GEMINI_FLASH_25 -> "gemini-2.5-flash"
            GEMINI_FLASH_LITE -> "gemini-2.5-flash-lite"
        }
}
