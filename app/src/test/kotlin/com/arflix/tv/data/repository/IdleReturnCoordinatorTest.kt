package com.arflix.tv.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class IdleReturnCoordinatorTest {
    private val cloud = mockk<CloudSyncRepository>(relaxed = true)
    private val history = mockk<WatchHistoryRepository>(relaxed = true)
    private val coordinator = IdleReturnCoordinator(cloud, history)
    private var now = 1_000_000L

    @Before fun setUp() {
        coordinator.clock = { now }
        coordinator.markActive()
    }

    private fun advance(ms: Long) {
        now += ms
    }

    @Test fun inputWithinThresholdIsNotAnIdleReturn() {
        advance(IdleReturnCoordinator.IDLE_THRESHOLD_MS - 1_000L)

        assertFalse(coordinator.onUserInteraction())
        assertEquals(0L, coordinator.idleReturns.value)
        coVerify(exactly = 0) { cloud.pullFromCloud(any()) }
    }

    @Test fun firstInputAfterIdlePullsWithoutPushingOnce() {
        coEvery { cloud.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.RESTORED
        advance(IdleReturnCoordinator.IDLE_THRESHOLD_MS + 1_000L)

        assertTrue(coordinator.isIdle())
        assertTrue(coordinator.onUserInteraction())
        // The idle period is over: the next press is ordinary input.
        assertFalse(coordinator.onUserInteraction())

        assertEquals(1L, coordinator.idleReturns.value)
        coVerify(exactly = 1, timeout = 2_000L) { cloud.pullFromCloud(pushPendingLocalFirst = false) }
        // This device's own in-memory history is the stale side after an idle period.
        verify(timeout = 2_000L) { history.clearProfileCaches() }
    }

    @Test fun playbackKeepsTheDeviceActive() {
        advance(IdleReturnCoordinator.IDLE_THRESHOLD_MS - 1_000L)
        coordinator.markActive()
        advance(IdleReturnCoordinator.IDLE_THRESHOLD_MS - 1_000L)

        assertFalse(coordinator.isIdle())
        assertFalse(coordinator.onAppForeground())
    }

    @Test fun failedPullKeepsTheLocalHistory() {
        coEvery { cloud.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.FAILED
        advance(IdleReturnCoordinator.IDLE_THRESHOLD_MS + 1_000L)

        assertTrue(coordinator.onAppForeground())

        coVerify(timeout = 2_000L) { cloud.pullFromCloud(pushPendingLocalFirst = false) }
        val deadline = System.currentTimeMillis() + 2_000L
        while (coordinator.isSyncing.value && System.currentTimeMillis() < deadline) Thread.sleep(20)
        assertFalse(coordinator.isSyncing.value)
        verify(exactly = 0) { history.clearProfileCaches() }
    }
}
