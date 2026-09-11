package com.arflix.tv.ui.screens.tv.live

import android.os.Handler
import android.os.HandlerThread
import android.os.SystemClock
import android.util.Log
import android.view.FrameMetrics
import android.view.KeyEvent
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.filters.SdkSuppress
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import java.util.Collections
import java.util.concurrent.atomic.AtomicReference
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/** Renderer-only comparison with the real display clock, no Compose test clock. */
@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 31)
class GuideRenderingBenchmark {
    @Test fun scrollDenseGuide() {
        val now = 1_783_000_000_000L / 1_800_000L * 1_800_000L
        val rows = (0 until 55_000).map { index ->
            IptvChannel(id = "render:$index", name = "Channel $index", group = "News",
                streamUrl = "https://example.test/live", catchupDays = 2).enrichForFastStartup(index + 1)
        }
        val window = rows.take(144)
        val guide = window.associate { row ->
            val programs = (0 until 24).map { slot ->
                val start = now - 120 * 60_000L + slot * 30 * 60_000L
                IptvProgram("Programme ${row.id}:$slot", "A programme description for rendering cost.",
                    start, start + 30 * 60_000L, catchupAvailable = true)
            }
            row.id to IptvNowNext(now = programs[4], next = programs[5],
                recent = programs.take(4), upcoming = programs.drop(5))
        }
        val focused = AtomicReference("")
        val durations = Collections.synchronizedList(mutableListOf<Long>())
        val deadlines = Collections.synchronizedList(mutableListOf<Boolean>())
        val thread = HandlerThread("guide-frame-metrics").apply { start() }
        val listener = Window.OnFrameMetricsAvailableListener { _, metrics, _ ->
            val duration = metrics.getMetric(FrameMetrics.TOTAL_DURATION)
            durations.add(duration)
            if (android.os.Build.VERSION.SDK_INT >= 31) {
                deadlines.add(duration > metrics.getMetric(FrameMetrics.DEADLINE))
            }
        }
        try {
            ActivityScenario.launch(ComponentActivity::class.java).use { scenario ->
                scenario.onActivity { activity ->
                    activity.setContent {
                        Box(Modifier.width(900.dp).height(400.dp)) {
                            EpgGrid(channels = window, totalChannelCount = rows.size,
                                clockTickMillis = now, nowNext = guide, selectedChannelId = "render:0",
                                focusSelectedChannelSignal = 1, scrollResetKey = "render-test",
                                favorites = emptySet(), onChannelSelect = {}, gridFocused = true,
                                onChannelFocused = { focused.set(it.id) })
                        }
                    }
                }
                fun awaitFocus(id: String) {
                    val deadline = SystemClock.uptimeMillis() + 5_000
                    while (focused.get() != id && SystemClock.uptimeMillis() < deadline) SystemClock.sleep(20)
                    assertEquals(id, focused.get())
                }
                awaitFocus("render:0")
                SystemClock.sleep(1_000)
                scenario.onActivity { it.window.addOnFrameMetricsAvailableListener(listener, Handler(thread.looper)) }
                try {
                    val automation = InstrumentationRegistry.getInstrumentation().uiAutomation
                    fun press(keyCode: Int) {
                        val time = SystemClock.uptimeMillis()
                        check(automation.injectInputEvent(KeyEvent(time, time, KeyEvent.ACTION_DOWN, keyCode, 0), true))
                        check(automation.injectInputEvent(KeyEvent(time, SystemClock.uptimeMillis(), KeyEvent.ACTION_UP, keyCode, 0), true))
                        SystemClock.sleep(150)
                    }
                    repeat(80) { press(KeyEvent.KEYCODE_DPAD_DOWN) }
                    awaitFocus("render:80")
                    repeat(80) { press(KeyEvent.KEYCODE_DPAD_UP) }
                    awaitFocus("render:0")
                } finally {
                    scenario.onActivity { it.window.removeOnFrameMetricsAvailableListener(listener) }
                }
            }
        } finally {
            thread.quitSafely()
            thread.join(2_000)
        }
        val samples = durations.sorted()
        assertTrue("Not enough rendered frames: ${samples.size}", samples.size > 160)
        fun percentile(p: Double) = samples[((samples.size - 1) * p).toInt()] / 1_000_000.0
        val latePercent = if (deadlines.isEmpty()) 0.0 else deadlines.count { it } * 100.0 / deadlines.size
        val runtime = Runtime.getRuntime()
        val heap = (runtime.totalMemory() - runtime.freeMemory()) / 1_048_576
        Log.i("GuideRenderBenchmark", "frames=${samples.size} p50ms=${percentile(0.5)} " +
            "p95ms=${percentile(0.95)} p99ms=${percentile(0.99)} latePercent=$latePercent " +
            "heapMiB=$heap maxHeapMiB=${runtime.maxMemory() / 1_048_576}")
    }
}
