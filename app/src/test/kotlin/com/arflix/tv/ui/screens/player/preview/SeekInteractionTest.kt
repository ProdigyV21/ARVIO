package com.arflix.tv.ui.screens.player.preview

import org.junit.Assert.*
import org.junit.Test

class SeekInteractionTest {
    private val duration = 3_600_000L

    @Test fun `remote browsing commits two seconds after the most recent key`() {
        val first = SeekInteraction().step(SeekSurface.Controls, 10_000, 300_000, duration, true, 100)
        assertEquals(2_000L, first.autoCommitDelayMs(100))
        assertEquals(1L, first.autoCommitDelayMs(2_099))
        assertEquals(0L, first.autoCommitDelayMs(2_100))
        val next = first.step(SeekSurface.Controls, 10_000, 300_000, duration, false, 2_000)
        assertEquals(1_900L, next.autoCommitDelayMs(2_100))
        assertTrue(next.resumeAfterBrowse)
        assertNull(next.finish().autoCommitDelayMs(2_100))
    }

    @Test fun `automatic seek never resumes deliberately paused playback or commits a touch drag`() {
        val paused = SeekInteraction().step(SeekSurface.Quick, 30_000, 300_000, duration, false, 100)
        assertEquals(0L, paused.autoCommitDelayMs(2_100))
        assertFalse(paused.resumeAfterBrowse)
        assertNull(paused.dragTo(400_000, 300_000, duration, false).autoCommitDelayMs(5_000))
        assertNull(SeekInteraction().autoCommitDelayMs(5_000))
    }

    @Test fun `finishing without an active seek does not render a zero target`() {
        val idle = SeekInteraction()
        assertSame(idle, idle.finish())
        assertEquals(SeekPhase.Idle, idle.finish().phase)
        val exited = idle.step(SeekSurface.Controls, 10_000, 300_000, duration, true, 100).finish()
        assertSame(exited, exited.finish())
        assertEquals(310_000L, exited.finish().targetMs)
    }

    @Test fun `single tap is a quick skip and repeat becomes explicit browsing`() {
        val tap = SeekInteraction().step(SeekSurface.Quick, 10_000, 300_000, duration, true, 100)
        assertEquals(SeekPhase.QuickSkip, tap.phase)
        assertEquals(310_000L, tap.targetMs)
        val repeat = tap.step(SeekSurface.Quick, 10_000, 310_000, duration, true, 200)
        assertTrue(repeat.browsing)
        assertEquals(320_000L, repeat.targetMs)
        val reverse = repeat.step(SeekSurface.Quick, -10_000, 310_000, duration, false, 300)
        assertTrue(reverse.browsing)
        assertEquals(310_000L, reverse.targetMs)
        assertTrue(reverse.resumeAfterBrowse)
    }

    @Test fun `exit retains target until animation completes and next session uses player position`() {
        val browse = SeekInteraction().step(SeekSurface.Controls, 300_000, 120_000, duration, false, 100)
        val exiting = browse.finish()
        assertEquals(420_000L, exiting.targetMs)
        assertFalse(exiting.quickVisible)
        assertFalse(exiting.resumeAfterBrowse)
        val next = exiting.afterExit().step(SeekSurface.Quick, -10_000, 180_000, duration, false, 500)
        assertEquals(170_000L, next.targetMs)
    }

    @Test fun `long browsing pauses never reset target or accelerate from playback position`() {
        val first = SeekInteraction().step(SeekSurface.Controls, 60_000, 300_000, duration, true, 100)
        val next = first.step(SeekSurface.Controls, 60_000, 300_000, duration, false, 20_000)
        assertEquals(420_000L, next.targetMs)
        assertTrue(next.resumeAfterBrowse)
    }

    @Test fun `drag preserves resume state and clamps endpoints`() {
        val first = SeekInteraction().dragTo(500_000, 120_000, duration, true)
        val last = first.dragTo(duration + 10_000, 120_000, duration, false)
        assertEquals(duration, last.targetMs)
        assertTrue(last.resumeAfterBrowse)
        assertEquals(0L, last.dragTo(-100, 120_000, duration, false).targetMs)
    }

    @Test fun `switching seek surfaces retains target and original playing intent`() {
        val controls = SeekInteraction().step(SeekSurface.Controls, 60_000, 300_000, duration, true, 100)
        val quick = controls.step(SeekSurface.Quick, 10_000, 300_000, duration, false, 200)
        assertEquals(370_000L, quick.targetMs)
        assertTrue(quick.resumeAfterBrowse)
        assertTrue(quick.browsing)
        val touch = quick.dragTo(400_000, 300_000, duration, false)
        assertTrue(touch.resumeAfterBrowse)
        assertEquals(300_000L, touch.originMs)
    }
}
