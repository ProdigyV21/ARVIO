package com.arflix.tv.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class IdleReturnCoordinatorTest {
    private val cloud = mockk<CloudSyncRepository>(relaxed = true)
    private val history = mockk<WatchHistoryRepository>(relaxed = true)
    private var now = 1_000_000L

    private fun TestScope.coordinator() = IdleReturnCoordinator(cloud, history, backgroundScope).also {
        it.clock = { now }
        it.markActive()
    }

    private fun goIdle() { now += IdleReturnCoordinator.IDLE_THRESHOLD_MS + 1_000L }

    @Test fun inputWithinThresholdIsNotAnIdleReturn() = runTest {
        val coordinator = coordinator()
        now += IdleReturnCoordinator.IDLE_THRESHOLD_MS - 1L
        assertFalse(coordinator.onUserInteraction())
        assertEquals(0L, coordinator.idleReturns.value)
        coVerify(exactly = 0) { cloud.pullFromCloud(any()) }
    }

    @Test fun firstInputAfterIdlePullsWithoutPushingOnce() = runTest {
        val coordinator = coordinator()
        coEvery { cloud.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.RESTORED
        every { history.clearProfileCaches() } answers {
            assertTrue(coordinator.isSyncing.value)
            assertEquals(0L, coordinator.syncCompleted.value)
        }
        goIdle()
        assertTrue(coordinator.onUserInteraction())
        assertFalse(coordinator.onUserInteraction())
        assertTrue(coordinator.isSyncing.value)
        runCurrent()
        assertEquals(1L, coordinator.idleReturns.value)
        assertEquals(1L, coordinator.syncCompleted.value)
        assertFalse(coordinator.isSyncing.value)
        coVerify(exactly = 1) { cloud.pullFromCloud(pushPendingLocalFirst = false) }
        verify(exactly = 1) { history.clearProfileCaches() }
    }

    @Test fun playbackKeepsTheDeviceActive() = runTest {
        val coordinator = coordinator()
        repeat(4) {
            now += IdleReturnCoordinator.IDLE_THRESHOLD_MS / 2
            coordinator.markActive()
        }
        assertFalse(coordinator.isIdle())
        assertFalse(coordinator.onAppForeground())
        coVerify(exactly = 0) { cloud.pullFromCloud(any()) }
    }

    @Test fun failedPullKeepsLocalHistoryAndReleasesTheBarrier() = runTest {
        val coordinator = coordinator()
        coEvery { cloud.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.FAILED
        goIdle()
        assertTrue(coordinator.onAppForeground())
        runCurrent()
        assertFalse(coordinator.isSyncing.value)
        assertEquals(1L, coordinator.syncCompleted.value)
        verify(exactly = 0) { history.clearProfileCaches() }
    }

    @Test fun noBackupDoesNotDiscardLocalProgress() = runTest {
        val coordinator = coordinator()
        coEvery { cloud.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.NO_BACKUP
        goIdle()
        coordinator.onUserInteraction()
        runCurrent()
        assertFalse(coordinator.isSyncing.value)
        verify(exactly = 0) { history.clearProfileCaches() }
    }

    @Test fun exceptionDoesNotLeaveTheScreenBlocked() = runTest {
        val coordinator = coordinator()
        coEvery { cloud.pullFromCloud(any()) } throws java.io.IOException("offline")
        goIdle()
        coordinator.onAppForeground()
        runCurrent()
        assertFalse(coordinator.isSyncing.value)
        assertEquals(1L, coordinator.syncCompleted.value)
        verify(exactly = 0) { history.clearProfileCaches() }
    }

    @Test fun timeoutCancelsThePullWithoutClearingLocalHistory() = runTest {
        val coordinator = coordinator()
        var cancelled = false
        coEvery { cloud.pullFromCloud(any()) } coAnswers {
            try { awaitCancellation() } finally { cancelled = true }
        }
        goIdle()
        coordinator.onAppForeground()
        runCurrent()
        advanceTimeBy(IdleReturnCoordinator.SYNC_TIMEOUT_MS - 1L)
        assertTrue(coordinator.isSyncing.value)
        advanceTimeBy(1L)
        runCurrent()
        assertTrue(cancelled)
        assertFalse(coordinator.isSyncing.value)
        assertEquals(1L, coordinator.syncCompleted.value)
        verify(exactly = 0) { history.clearProfileCaches() }
    }

    @Test fun aSecondIdleReturnDoesNotDuplicateAnInFlightPull() = runTest {
        val coordinator = coordinator()
        val pull = CompletableDeferred<CloudSyncRepository.RestoreResult>()
        coEvery { cloud.pullFromCloud(any()) } coAnswers { pull.await() }
        repeat(2) {
            goIdle()
            coordinator.onUserInteraction()
            runCurrent()
        }
        coVerify(exactly = 1) { cloud.pullFromCloud(false) }
        pull.complete(CloudSyncRepository.RestoreResult.RESTORED)
        runCurrent()
        assertEquals(1L, coordinator.syncCompleted.value)
    }

    @Test fun completionIsRetainedForAScreenThatSubscribesLater() = runTest {
        val coordinator = coordinator()
        coEvery { cloud.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.RESTORED
        goIdle()
        coordinator.onUserInteraction()
        runCurrent()
        assertEquals(1L, coordinator.syncCompleted.first())
    }

    @Test fun resettingTheIdleClockDoesNotMakeTheOldPlayerSafe() = runTest {
        val coordinator = coordinator()
        coordinator.isPlayerOpen = true
        goIdle()
        assertTrue(coordinator.shouldDiscardPlayer)
        coordinator.onAppForeground()
        assertFalse(coordinator.isIdle())
        assertTrue(coordinator.shouldDiscardPlayer)
        coordinator.markActive()
        assertTrue(coordinator.shouldDiscardPlayer)
        coordinator.isPlayerOpen = false
        assertTrue(coordinator.shouldDiscardPlayer)
        coordinator.isPlayerOpen = true
        assertFalse(coordinator.shouldDiscardPlayer)
    }
}
