package com.arflix.tv.data.repository

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RemoteRevisionTrackerTest {
    @Test fun `the first revision only sets the baseline`() {
        val tracker = RemoteRevisionTracker()
        assertFalse(tracker.observe("r1"))
    }

    @Test fun `an unchanged revision is not a change`() {
        val tracker = RemoteRevisionTracker()
        tracker.observe("r1")
        assertFalse(tracker.observe("r1"))
        assertFalse(tracker.observe("r1"))
    }

    @Test fun `a new revision is one change and then the new baseline`() {
        val tracker = RemoteRevisionTracker()
        tracker.observe("r1")
        assertTrue(tracker.observe("r2"))
        assertFalse(tracker.observe("r2"))
        assertTrue(tracker.observe("r3"))
    }

    @Test fun `a missing or blank revision never counts as a change and keeps the baseline`() {
        val tracker = RemoteRevisionTracker()
        tracker.observe("r1")
        assertFalse(tracker.observe(null))
        assertFalse(tracker.observe(""))
        assertFalse(tracker.observe("r1"))
        assertTrue(tracker.observe("r2"))
    }

    @Test fun `rebasing after our own pull hides that revision but not later ones`() {
        val tracker = RemoteRevisionTracker()
        tracker.observe("r1")
        tracker.rebase("r2")
        assertFalse(tracker.observe("r2"))
        assertTrue(tracker.observe("r3"))
    }

    @Test fun `reset forgets the baseline so the next revision is only a baseline again`() {
        val tracker = RemoteRevisionTracker()
        tracker.observe("r1")
        tracker.reset()
        assertFalse(tracker.observe("r2"))
    }
}
