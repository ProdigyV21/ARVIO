package com.arflix.tv.ui.screens.player.subtitles

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

/** org.json and Log are part of the Android framework, so these run under Robolectric. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = android.app.Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class ClaudeMessagesTest {

    @Test fun textBlocksAreJoined() {
        val reply = parseClaudeReply(
            """{"content":[{"type":"text","text":"[\"0: Γεια\","},{"type":"text","text":"\"1: Τι κάνεις;\"]"}],"stop_reason":"end_turn"}"""
        )
        assertEquals(ClaudeReply.Text("[\"0: Γεια\",\"1: Τι κάνεις;\"]"), reply)
    }

    @Test fun nonTextBlocksAreIgnored() {
        val reply = parseClaudeReply(
            """{"content":[{"type":"thinking","thinking":"..."},{"type":"text","text":"[\"0: Ναι\"]"}],"stop_reason":"end_turn"}"""
        )
        assertEquals(ClaudeReply.Text("[\"0: Ναι\"]"), reply)
    }

    @Test fun refusalIsReportedLikeAContentBlock() {
        assertEquals(ClaudeReply.Refused, parseClaudeReply("""{"content":[],"stop_reason":"refusal"}"""))
    }

    @Test fun aReplyCutOffAtMaxTokensIsReportedForBisection() {
        assertEquals(
            ClaudeReply.Failed(CLAUDE_ERROR_MAX_TOKENS),
            parseClaudeReply("""{"content":[{"type":"text","text":"[\"0: Γεια\","}],"stop_reason":"max_tokens"}""")
        )
    }

    @Test fun emptyOrUnreadableRepliesFail() {
        assertTrue(parseClaudeReply("""{"content":[],"stop_reason":"end_turn"}""") is ClaudeReply.Failed)
        assertTrue(parseClaudeReply("not json") is ClaudeReply.Failed)
    }

    @Test fun keyPrefixPicksTheProvider() {
        assertEquals(SubtitleAiModel.CLAUDE_HAIKU, subtitleAiModelForKey("  sk-ant-api03-abc "))
        assertEquals(SubtitleAiModel.GEMINI_FLASH_25, subtitleAiModelForKey("AIzaSyXYZ"))
        assertEquals(SubtitleAiModel.GROQ_LLAMA_70B, subtitleAiModelForKey("gsk_123"))
        assertNull(subtitleAiModelForKey(""))
        assertNull(subtitleAiModelForKey("something-else"))
    }
}
