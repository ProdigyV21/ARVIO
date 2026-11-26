package com.arflix.tv.cast

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class DlnaSessionTrackerTest {
    private class Clock(var now: Long = 0L) {
        fun tick(ms: Long) { now += ms }
        fun asProvider(): () -> Long = { now }
    }

    private fun tracker(clock: Clock, playRejected: Boolean = false): DlnaSessionTracker {
        val t = DlnaSessionTracker(nowMs = clock.asProvider())
        t.start(playRejected)
        return t
    }

    @Test fun stoppedBeforeAnyPlaybackNeverEndsTheSession() {
        val clock = Clock()
        val t = tracker(clock)
        repeat(10) {
            clock.tick(1_000)
            assertNull("a fresh load may report STOPPED before Play takes effect", t.onPoll(true, "STOPPED"))
        }
    }

    @Test fun noMediaBeforeAnyPlaybackNeverEndsTheSession() {
        val clock = Clock()
        val t = tracker(clock)
        repeat(10) {
            clock.tick(1_000)
            assertNull(t.onPoll(true, "NO_MEDIA_PRESENT"))
        }
    }

    @Test fun stoppedAfterPlayingEndsOnlyOnTheSecondObservation() {
        val clock = Clock()
        val t = tracker(clock)
        clock.tick(1_000); assertNull(t.onPoll(true, "PLAYING"))
        clock.tick(1_000); assertNull("one STOPPED can be a transient seek/bitrate switch", t.onPoll(true, "STOPPED"))
        clock.tick(1_000)
        assertEquals(DlnaSessionTracker.EndReason.REMOTE_STOPPED, t.onPoll(true, "STOPPED"))
    }

    @Test fun noMediaAfterPlayingAlsoEndsTheSession() {
        val clock = Clock()
        val t = tracker(clock)
        clock.tick(1_000); assertNull(t.onPoll(true, "PLAYING"))
        clock.tick(1_000); assertNull(t.onPoll(true, "NO_MEDIA_PRESENT"))
        clock.tick(1_000)
        assertEquals(DlnaSessionTracker.EndReason.REMOTE_STOPPED, t.onPoll(true, "NO_MEDIA_PRESENT"))
    }

    @Test fun pausedAndTransitioningNeverEndTheSession() {
        val clock = Clock()
        val t = tracker(clock)
        repeat(30) {
            clock.tick(1_000)
            assertNull(t.onPoll(true, "PAUSED_PLAYBACK"))
            assertNull(t.onPoll(true, "TRANSITIONING"))
        }
    }

    @Test fun aPlayingPollResetsTheStopStreak() {
        val clock = Clock()
        val t = tracker(clock)
        clock.tick(1_000); assertNull(t.onPoll(true, "PLAYING"))
        clock.tick(1_000); assertNull(t.onPoll(true, "STOPPED"))
        clock.tick(1_000); assertNull(t.onPoll(true, "PLAYING"))
        clock.tick(1_000); assertNull(t.onPoll(true, "STOPPED"))
        clock.tick(1_000)
        assertEquals(DlnaSessionTracker.EndReason.REMOTE_STOPPED, t.onPoll(true, "STOPPED"))
    }

    @Test fun unreachableIsDecidedByAWallClockWindow() {
        val clock = Clock()
        val t = tracker(clock)
        clock.tick(1_000); assertNull(t.onPoll(true, "PLAYING"))
        // A poll can take up to ~18 s (two SOAP calls with 3 s connect + 6 s read timeouts),
        // so a raw iteration count would end the session too early.
        clock.tick(14_000); assertNull(t.onPoll(false, null))
        clock.tick(1_000)
        assertEquals(DlnaSessionTracker.EndReason.UNREACHABLE, t.onPoll(false, null))
    }

    @Test fun anySuccessfulSoapCallResetsTheUnreachableWindow() {
        val clock = Clock()
        val t = tracker(clock)
        clock.tick(1_000); assertNull(t.onPoll(true, "PLAYING"))
        repeat(12) { clock.tick(1_000); assertNull(t.onPoll(false, null)) }
        clock.tick(1_000); assertNull("GetTransportInfo answering is enough to keep the session", t.onPoll(false, "PAUSED_PLAYBACK"))
        repeat(14) { clock.tick(1_000); assertNull(t.onPoll(false, null)) }
        clock.tick(1_000)
        assertEquals(DlnaSessionTracker.EndReason.UNREACHABLE, t.onPoll(false, null))
    }

    @Test fun oneFailingSoapCallDoesNotCountAsUnreachable() {
        val clock = Clock()
        val t = tracker(clock)
        repeat(20) {
            clock.tick(1_000)
            assertNull("GetPositionInfo may be unimplemented on some renderers", t.onPoll(true, null))
        }
    }

    @Test fun playRejectedEndsWhenPlaybackNeverStarts() {
        val clock = Clock()
        val t = tracker(clock, playRejected = true)
        repeat(19) { clock.tick(1_000); assertNull(t.onPoll(true, "STOPPED")) }
        clock.tick(1_000)
        assertEquals(DlnaSessionTracker.EndReason.NO_PLAYBACK, t.onPoll(true, "STOPPED"))
    }

    @Test fun playRejectedKeepsWaitingWhileTheRendererIsTransitioning() {
        val clock = Clock()
        val t = tracker(clock, playRejected = true)
        repeat(30) { clock.tick(1_000); assertNull(t.onPoll(true, "TRANSITIONING")) }
    }

    @Test fun seeingPlayingCancelsTheNoPlaybackDeadline() {
        val clock = Clock()
        val t = tracker(clock, playRejected = true)
        clock.tick(19_000); assertNull(t.onPoll(true, "STOPPED"))
        clock.tick(1_000); assertNull(t.onPoll(true, "PLAYING"))
        clock.tick(10_000); assertNull(t.onPoll(true, "STOPPED"))
        clock.tick(1_000)
        assertEquals(DlnaSessionTracker.EndReason.REMOTE_STOPPED, t.onPoll(true, "STOPPED"))
    }

    @Test fun playRetriesAreBoundedAndGatedByStateAndPause() {
        val clock = Clock()
        val t = tracker(clock, playRejected = true)
        assertFalse(t.shouldRetryPlay(null, false))
        assertFalse(t.shouldRetryPlay("TRANSITIONING", false))
        assertFalse(t.shouldRetryPlay("PLAYING", false))
        assertFalse(t.shouldRetryPlay("STOPPED", true))
        clock.tick(2_000)
        assertTrue(t.shouldRetryPlay("STOPPED", false))
        t.notePlayRetry()
        clock.tick(2_000)
        assertTrue(t.shouldRetryPlay("NO_MEDIA_PRESENT", false))
        t.notePlayRetry()
        clock.tick(2_000)
        assertFalse("only two retries are allowed", t.shouldRetryPlay("STOPPED", false))
    }

    @Test fun playRetriesWaitForTheMinimumInterval() {
        val clock = Clock()
        val t = tracker(clock, playRejected = true)
        assertFalse(t.shouldRetryPlay("STOPPED", false))
        clock.tick(2_000); t.notePlayRetry()
        assertFalse(t.shouldRetryPlay("STOPPED", false))
        clock.tick(2_000)
        assertTrue(t.shouldRetryPlay("STOPPED", false))
    }

    @Test fun aNormalLoadNeverRetriesPlay() {
        val clock = Clock()
        val t = tracker(clock, playRejected = false)
        clock.tick(5_000)
        assertFalse(t.shouldRetryPlay("STOPPED", false))
    }
}
