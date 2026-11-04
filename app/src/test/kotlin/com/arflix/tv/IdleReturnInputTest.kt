package com.arflix.tv

import android.app.Application
import android.view.KeyEvent
import android.view.MotionEvent
import com.arflix.tv.data.repository.IdleReturnCoordinator
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IdleReturnInputTest {
    @Test fun wakeTouchCannotReachTheStalePlayerAndItsReleaseIsConsumed() = runTest {
        var now = 1_000L
        val coordinator = IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true), backgroundScope)
        coordinator.clock = { now }
        coordinator.markActive()
        coordinator.isPlayerOpen = true
        now += IdleReturnCoordinator.IDLE_THRESHOLD_MS
        coordinator.onAppForeground()
        assertFalse(coordinator.isIdle())
        val activity = Robolectric.buildActivity(MainActivity::class.java).get()
        activity.idleReturnCoordinator = coordinator
        val down = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 10f, 10f, 0)
        val up = MotionEvent.obtain(0, 10, MotionEvent.ACTION_UP, 10f, 10f, 0)
        try {
            assertTrue(activity.dispatchTouchEvent(down))
            coordinator.isPlayerOpen = false
            assertTrue(activity.dispatchTouchEvent(up))
        } finally {
            down.recycle()
            up.recycle()
        }
    }

    @Test fun keyRepeatsAndMultipleReleasesCannotActOnTheReturningDetailsPage() = runTest {
        var now = 1_000L
        val coordinator = IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true), backgroundScope)
        coordinator.clock = { now }
        coordinator.markActive()
        coordinator.isPlayerOpen = true
        now += IdleReturnCoordinator.IDLE_THRESHOLD_MS
        val activity = Robolectric.buildActivity(MainActivity::class.java).get()
        activity.idleReturnCoordinator = coordinator
        val keys = listOf(KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_BACK)
        keys.forEach { key -> assertTrue(activity.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, key))) }
        coordinator.isPlayerOpen = false
        keys.forEach { key ->
            assertTrue(activity.dispatchKeyEvent(KeyEvent(0, 1, KeyEvent.ACTION_DOWN, key, 1)))
            assertTrue(activity.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, key)))
        }
    }
}
