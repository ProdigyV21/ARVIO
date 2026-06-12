package com.arflix.tv.ui.screens.player

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SubtitleAiModelTest {

    @Test
    fun `Flash-Lite enum value round trips by name`() {
        val model = SubtitleAiModel.GEMINI_FLASH_LITE

        assertEquals(model, SubtitleAiModel.valueOf(model.name))
    }

    @Test
    fun `Gemini models map to Gemini API model ids`() {
        assertEquals("gemini-2.5-flash", SubtitleAiModel.GEMINI_FLASH_25.geminiModelId)
        assertEquals("gemini-2.5-flash-lite", SubtitleAiModel.GEMINI_FLASH_LITE.geminiModelId)
    }

    @Test
    fun `Groq model is not routed through Gemini model ids`() {
        assertNull(SubtitleAiModel.GROQ_LLAMA_70B.geminiModelId)
    }

    @Test
    fun `unknown persisted model string falls back to Groq`() {
        val model = runCatching {
            SubtitleAiModel.valueOf("UNKNOWN_MODEL")
        }.getOrElse {
            SubtitleAiModel.GROQ_LLAMA_70B
        }

        assertEquals(SubtitleAiModel.GROQ_LLAMA_70B, model)
    }
}
