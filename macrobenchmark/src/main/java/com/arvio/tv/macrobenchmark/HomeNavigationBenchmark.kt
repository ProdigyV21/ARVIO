package com.arvio.tv.macrobenchmark

import android.view.KeyEvent
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeNavigationBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun homeDpadNavigationFrameTiming() = benchmarkRule.measureRepeated(
        packageName = TARGET_PACKAGE,
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        startupMode = StartupMode.COLD,
        iterations = BENCHMARK_ITERATIONS,
        setupBlock = {
            pressHome()
        }
    ) {
            startActivityAndWait()
            device.wait(Until.hasObject(By.pkg(TARGET_PACKAGE)), STARTUP_TIMEOUT_MS)
            Thread.sleep(HOME_SETTLE_MS)
            device.pressKeyCode(KeyEvent.KEYCODE_DPAD_DOWN)
            Thread.sleep(KEY_SETTLE_MS)
            performHomeDpadSweep()
    }

    @Test
    fun warmHomeDpadNavigationFrameTiming() = benchmarkRule.measureRepeated(
        packageName = TARGET_PACKAGE,
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        startupMode = StartupMode.WARM,
        iterations = BENCHMARK_ITERATIONS,
        setupBlock = {
            pressHome()
            startActivityAndWait()
            device.wait(Until.hasObject(By.pkg(TARGET_PACKAGE)), STARTUP_TIMEOUT_MS)
            Thread.sleep(WARM_HOME_SETTLE_MS)
            device.waitForIdle()
        }
    ) {
        device.pressKeyCode(KeyEvent.KEYCODE_DPAD_DOWN)
        Thread.sleep(KEY_SETTLE_MS)
        performHomeDpadSweep()
    }

    @Test
    fun warmHomeHorizontalDpadFrameTiming() = benchmarkRule.measureRepeated(
        packageName = TARGET_PACKAGE,
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        startupMode = StartupMode.WARM,
        iterations = BENCHMARK_ITERATIONS,
        setupBlock = {
            pressHome()
            startActivityAndWait()
            device.wait(Until.hasObject(By.pkg(TARGET_PACKAGE)), STARTUP_TIMEOUT_MS)
            Thread.sleep(WARM_HOME_SETTLE_MS)
            device.pressKeyCode(KeyEvent.KEYCODE_DPAD_DOWN)
            Thread.sleep(KEY_SETTLE_MS)
            device.waitForIdle()
        }
    ) {
        performHomeHorizontalSweep()
    }

    @Test
    fun warmHomeVerticalDpadFrameTiming() = benchmarkRule.measureRepeated(
        packageName = TARGET_PACKAGE,
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        startupMode = StartupMode.WARM,
        iterations = BENCHMARK_ITERATIONS,
        setupBlock = {
            pressHome()
            startActivityAndWait()
            device.wait(Until.hasObject(By.pkg(TARGET_PACKAGE)), STARTUP_TIMEOUT_MS)
            Thread.sleep(WARM_HOME_SETTLE_MS)
            device.pressKeyCode(KeyEvent.KEYCODE_DPAD_DOWN)
            Thread.sleep(KEY_SETTLE_MS)
            device.waitForIdle()
        }
    ) {
        performHomeVerticalSweep()
    }

    private fun MacrobenchmarkScope.performHomeDpadSweep() {
        performHomeHorizontalSweep()
        performHomeVerticalStepPair()
    }

    private fun MacrobenchmarkScope.performHomeHorizontalSweep() {
        repeat(3) {
            device.pressKeyCode(KeyEvent.KEYCODE_DPAD_RIGHT)
            Thread.sleep(KEY_STEP_MS)
        }
        repeat(2) {
            device.pressKeyCode(KeyEvent.KEYCODE_DPAD_LEFT)
            Thread.sleep(KEY_STEP_MS)
        }
    }

    private fun MacrobenchmarkScope.performHomeVerticalSweep() {
        repeat(3) {
            performHomeVerticalStepPair()
        }
    }

    private fun MacrobenchmarkScope.performHomeVerticalStepPair() {
        device.pressKeyCode(KeyEvent.KEYCODE_DPAD_DOWN)
        Thread.sleep(KEY_STEP_MS)
        device.pressKeyCode(KeyEvent.KEYCODE_DPAD_UP)
        Thread.sleep(KEY_STEP_MS)
    }

    private companion object {
        const val TARGET_PACKAGE = "com.arvio.tv"
        const val BENCHMARK_ITERATIONS = 3
        const val STARTUP_TIMEOUT_MS = 15_000L
        const val HOME_SETTLE_MS = 5_000L
        const val WARM_HOME_SETTLE_MS = 7_000L
        const val KEY_SETTLE_MS = 500L
        const val KEY_STEP_MS = 120L
    }
}
