package com.arflix.tv.ui.screens.player.subtitles.bilingual

import com.arflix.tv.data.model.Subtitle
import org.junit.Assert.*
import org.junit.Test

class SmartBilingualSessionTest {
    private val original = Subtitle(id = "original", url = "", lang = "en", label = "English", isEmbedded = true)
    private fun snapshot(ai: Boolean = false, stream: String = "video-1") = SubtitleModeSnapshot(
        streamUrl = stream, selectedSubtitle = original, aiSourceSubtitle = if (ai) original else null,
        aiTranslating = ai, managerEnabled = ai, targetLanguage = "Hebrew", targetLanguageName = "Hebrew",
        aiAvailable = true, manualSelection = true, userPicked = true,
    )

    @Test fun `off clears a per-video request even when global setting was already off`() {
        val request = SmartBilingualRequest().start()
        assertTrue(request.shouldRun(globalEnabled = false, liveAudio = false))
        val stopped = request.stop()
        assertFalse(stopped.requestedForVideo)
        assertFalse(stopped.shouldRun(globalEnabled = false, liveAudio = false))
        assertFalse(stopped.shouldRun(globalEnabled = true, liveAudio = false))
        assertTrue(stopped.start().shouldRun(globalEnabled = false, liveAudio = false))
    }

    @Test fun `live audio temporarily suspends both global and per-video requests`() {
        for (request in listOf(SmartBilingualRequest(), SmartBilingualRequest().start())) {
            assertFalse(request.shouldRun(globalEnabled = true, liveAudio = true))
            assertTrue(request.shouldRun(globalEnabled = true, liveAudio = false))
        }
    }

    @Test fun `matching also owns the subtitle until it finishes`() {
        val request = SmartBilingualRequest().start()
        assertFalse(request.shouldRun(false, liveAudio = false, findingMatch = true))
        assertTrue(request.shouldRun(false, liveAudio = false, findingMatch = false))
    }

    @Test fun `a slow manual pick suspends planning so its download cannot be cancelled`() {
        val request = SmartBilingualRequest().start()
        assertFalse(request.shouldRun(false, liveAudio = false, selectionPending = true))
        assertFalse(request.shouldRun(true, liveAudio = false, selectionPending = true))
        assertTrue(request.shouldRun(false, liveAudio = false, selectionPending = false))
    }

    @Test fun `off during live cannot be undone when live ends`() {
        val request = SmartBilingualRequest().start().stop()
        assertFalse(request.shouldRun(true, liveAudio = true))
        assertFalse(request.shouldRun(true, liveAudio = false))
    }

    @Test fun `replanning does not overwrite the regular mode to restore`() {
        for (ai in listOf(false, true)) {
            val session = SmartBilingualSession()
            val before = snapshot(ai)
            session.enter(before)
            session.enter(snapshot(!ai).copy(selectedSubtitle = original.copy(id = "smart-primary")))
            assertEquals(before, session.leave("video-1"))
            assertFalse(session.isActive)
            assertNull(session.leave("video-1"))
        }
    }

    @Test fun `explicit replacement discards smart snapshot instead of changing the new pick`() {
        val session = SmartBilingualSession()
        session.enter(snapshot(true))
        assertNull(session.leave("video-1", restorePrevious = false))
        assertFalse(session.isActive)
    }

    @Test fun `a different video never receives the previous videos saved subtitle`() {
        val session = SmartBilingualSession()
        session.enter(snapshot(true))
        assertNull(session.leave("video-2"))
        session.enter(snapshot(false, "video-2"))
        assertEquals("video-2", session.leave("video-2")?.streamUrl)
    }

    @Test fun `merge blocks AI for every automatic calibration path including startup and mode switch`() {
        val modes = BilingualGenerationMode.values()
        for (current in modes) for (persisted in modes) {
            assertEquals(current != BilingualGenerationMode.MERGE && persisted != BilingualGenerationMode.MERGE,
                automaticCalibrationAiAllowed(current, persisted, true, true, false))
        }
    }

    @Test fun `AI calibration requires a key enabled AI and no live owner`() {
        val auto = BilingualGenerationMode.AUTO
        assertFalse(automaticCalibrationAiAllowed(auto, auto, false, true, false))
        assertFalse(automaticCalibrationAiAllowed(auto, auto, true, false, false))
        assertFalse(automaticCalibrationAiAllowed(auto, auto, true, true, true))
        assertTrue(automaticCalibrationAiAllowed(auto, auto, true, true, false))
    }
}
