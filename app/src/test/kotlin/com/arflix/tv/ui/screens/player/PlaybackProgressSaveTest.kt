package com.arflix.tv.ui.screens.player

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlaybackProgressSaveTest {
    @Test fun finalWriteSurvivesNavigationAndTheCallersShortTimeout() = runTest {
        val scope = CoroutineScope(SupervisorJob() + StandardTestDispatcher(testScheduler))
        var saved = false
        val job = scope.launchPlaybackProgressSave(true, StandardTestDispatcher(testScheduler)) {
            delay(5_000); saved = true
        }
        assertNull(withTimeoutOrNull(2_000) { job.join(); true })
        scope.cancel() // The old player ViewModel is cleared when navigation completes.
        advanceTimeBy(3_001); runCurrent()
        assertTrue(saved)
        assertTrue(job.isCompleted)
        assertFalse(job.isCancelled)
    }

    @Test fun ordinaryProgressStillStopsWhenPlayerIsDestroyed() = runTest {
        val scope = CoroutineScope(SupervisorJob() + StandardTestDispatcher(testScheduler))
        var saved = false
        val job = scope.launchPlaybackProgressSave(false, StandardTestDispatcher(testScheduler)) {
            delay(5_000); saved = true
        }
        runCurrent(); scope.cancel(); advanceTimeBy(5_001); runCurrent()
        assertFalse(saved)
        assertTrue(job.isCancelled)
    }

    @Test fun unreachableTrackerCannotKeepCompletionWorkAliveIndefinitely() = runTest {
        val scope = CoroutineScope(SupervisorJob() + StandardTestDispatcher(testScheduler))
        var finished = false
        val job = scope.launchPlaybackProgressSave(true, StandardTestDispatcher(testScheduler)) {
            try { delay(60_000) } finally { finished = true }
        }
        runCurrent(); scope.cancel(); advanceTimeBy(30_001); runCurrent()
        assertTrue(finished)
        assertTrue(job.isCompleted)
    }

    @Test fun switchingProfilesStopsTheSurvivingWriteBeforeItCanUseAnotherAccount() = runTest {
        val scope = CoroutineScope(SupervisorJob() + StandardTestDispatcher(testScheduler))
        val profile = MutableStateFlow("original")
        var saved = false
        val job = scope.launchPlaybackProgressSave(true, StandardTestDispatcher(testScheduler),
            stopWhen = { profile.first { it != "original" } }) { delay(5_000); saved = true }
        runCurrent(); scope.cancel(); profile.value = "other"; runCurrent()
        advanceTimeBy(5_001); runCurrent()
        assertFalse(saved)
        assertTrue(job.isCompleted)
    }
}
