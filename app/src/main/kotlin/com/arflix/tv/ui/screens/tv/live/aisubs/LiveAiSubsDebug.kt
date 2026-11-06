package com.arflix.tv.ui.screens.tv.live.aisubs

import com.arflix.tv.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * Hidden diagnostics for Live TV AI subtitles, debug builds only:
 * `adb shell setprop debug.arvio.liveaisubs.overlay 1` shows look-ahead, AI latency p50/p95,
 * late cues and segments per minute over the picture, and logs HLS segment details under
 * `LiveAiSubs`. `debug.*` properties are writable from `adb shell` without root. They are polled
 * on a daemon thread, because reading one means running `getprop`, never on the UI thread.
 */
internal object LiveAiSubsDebug {
    @Volatile
    var enabled: Boolean = false
        private set

    @Volatile
    private var started = false

    fun start() {
        if (!BuildConfig.DEBUG || started) return
        synchronized(this) {
            if (started) return
            started = true
            Thread({ pollLoop() }, "LiveAiSubsProps").apply {
                isDaemon = true
                start()
            }
        }
    }

    private fun pollLoop() {
        while (true) {
            enabled = read("debug.arvio.liveaisubs.overlay") == "1"
            try {
                Thread.sleep(POLL_MS)
            } catch (_: InterruptedException) {
                return
            }
        }
    }

    private fun read(name: String): String? = runCatching {
        val process = ProcessBuilder("getprop", name).redirectErrorStream(true).start()
        val value = process.inputStream.bufferedReader().use { it.readText().trim() }
        process.waitFor(1, TimeUnit.SECONDS)
        value.ifEmpty { null }
    }.getOrNull()

    private const val POLL_MS = 3_000L
}
