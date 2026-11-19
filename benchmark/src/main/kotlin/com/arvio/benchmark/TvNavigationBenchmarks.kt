package com.arvio.benchmark

import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Black-box navigation against the optimized app; no cloud account or setting changes. */
@RunWith(AndroidJUnit4::class)
@LargeTest
@OptIn(androidx.benchmark.macro.ExperimentalMacrobenchmarkApi::class)
class TvNavigationBenchmarks {
    @get:Rule val rule = MacrobenchmarkRule()

    @Test fun homeHorizontal() = measure(
        "home-horizontal", home = true,
        keys = (List(6) { RIGHT } + List(6) { LEFT }).repeatPattern(3),
        intervalMs = 120L,
    )

    @Test fun homeVertical() = measure(
        "home-vertical", home = true,
        keys = (List(3) { DOWN } + List(3) { UP }).repeatPattern(3),
        intervalMs = 180L,
    )

    @Test fun settingsSidebar() = measure(
        "settings-sidebar", home = false,
        keys = (List(10) { DOWN } + List(10) { UP }).repeatPattern(2),
        intervalMs = 120L,
    )

    @Test fun settingsPlayback() = measure(
        "settings-playback", home = false, playback = true,
        keys = (List(11) { DOWN } + List(11) { UP }).repeatPattern(2),
        intervalMs = 120L,
    )

    private fun measure(
        scenario: String,
        home: Boolean,
        playback: Boolean = false,
        keys: List<Int>,
        intervalMs: Long,
    ) {
        val arguments = InstrumentationRegistry.getArguments()
        val iterations = arguments.getString("iterations")?.toIntOrNull()?.coerceIn(1, 10) ?: 3
        val compilation = if (arguments.getString("compilation") == "partial") {
            CompilationMode.Partial(baselineProfileMode = BaselineProfileMode.Disable, warmupIterations = 2)
        } else CompilationMode.Ignore()
        rule.measureRepeated(
            packageName = TARGET,
            metrics = listOf(FrameTimingMetric()),
            compilationMode = compilation,
            startupMode = StartupMode.WARM,
            iterations = iterations,
            setupBlock = {
                pressHome()
                startActivityAndWait()
                device.selectTvPerformanceProfileIfShown()
                openHome()
                if (!home) openSettings(playback)
                Thread.sleep(500)
            },
        ) {
            val dispatchUs = ArrayList<Long>(keys.size)
            keys.forEach { key ->
                val started = SystemClock.elapsedRealtimeNanos()
                check(device.pressKeyCode(key)) { "Input injection failed for $key" }
                dispatchUs += (SystemClock.elapsedRealtimeNanos() - started) / 1_000L
                Thread.sleep(intervalMs)
            }
            Thread.sleep(350)
            Log.i("TvNavigationBenchmark", "scenario=$scenario dispatchUs=$dispatchUs")
        }
    }

    private fun List<Int>.repeatPattern(count: Int): List<Int> = List(count) { this }.flatten()

    companion object {
        private const val TARGET = "com.arvio.tv"
        private const val UP = KeyEvent.KEYCODE_DPAD_UP
        private const val DOWN = KeyEvent.KEYCODE_DPAD_DOWN
        private const val LEFT = KeyEvent.KEYCODE_DPAD_LEFT
        private const val RIGHT = KeyEvent.KEYCODE_DPAD_RIGHT
    }
}
