package com.arflix.tv.ui.screens.tv.live.aisubs

import com.arflix.tv.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * Phase 0 switches for the Live TV AI subtitles proof of concept, set over adb so no UI is
 * needed yet (debug builds only; release builds always read "off"):
 *
 * - `adb shell setprop debug.arvio.liveaisubs 1` taps the channel's audio and logs segments.
 * - `adb shell setprop debug.arvio.liveaisubs.delay 10` holds playback until 10 s are buffered
 *   (0 = no hold). Read when a channel is prepared.
 * - `adb shell setprop debug.arvio.liveaisubs.verify 1` also runs the VAD on the audio as it is
 *   played, to compare its segment times with the look-ahead tap's (doubles the VAD cost).
 *
 * `debug.*` properties are writable from `adb shell` without root. They are polled on a daemon
 * thread, because reading them means running `getprop`, which must not happen on the UI thread.
 */
internal object LiveAiSubsDebug {
    @Volatile
    var enabled: Boolean = false
        private set

    @Volatile
    var delayMs: Long = 0L
        private set

    @Volatile
    var verify: Boolean = false
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
            val nowEnabled = read("debug.arvio.liveaisubs") == "1"
            val nowDelay = read("debug.arvio.liveaisubs.delay")?.toLongOrNull()?.coerceIn(0L, 30L) ?: 0L
            val nowVerify = read("debug.arvio.liveaisubs.verify") == "1"
            if (nowEnabled != enabled || nowDelay * 1_000L != delayMs || nowVerify != verify) {
                LiveAiSubsLog.i("debug switches: enabled=$nowEnabled delay=${nowDelay}s verify=$nowVerify")
            }
            enabled = nowEnabled
            delayMs = if (nowEnabled) nowDelay * 1_000L else 0L
            verify = nowEnabled && nowVerify
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

    private const val POLL_MS = 2_000L
}
