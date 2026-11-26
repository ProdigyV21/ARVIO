package com.arflix.tv.ui.screens.tv.live

import android.os.SystemClock
import android.util.Log

/**
 * Lightweight timing marks for Live TV ("adb logcat -s LiveTvPerf").
 *
 * One line per milestone, so zap time and time-to-first-guide can be measured on any
 * device without a profiler attached:
 *  - `enter` -> `channels` -> `guide` : opening Live TV until the channel list is drawn
 *  - `epg`                            : first frame with programme data
 *  - `zap` -> `prepare` -> `ready` -> `frame` : tuning a channel until its first video frame
 */
internal object LiveTvPerf {
    private const val TAG = "LiveTvPerf"

    private var enterAt = 0L
    private var zapAt = 0L
    private var zapChannel: String? = null
    private var guideLogged = false
    private var epgLogged = false

    fun now(): Long = SystemClock.elapsedRealtime()

    fun screenEntered() {
        enterAt = now()
        guideLogged = false
        epgLogged = false
        log("enter")
    }

    fun channelsReady(count: Int) = sinceEnter("channels n=$count")

    fun guidePainted() {
        if (guideLogged) return
        guideLogged = true
        sinceEnter("guide")
    }

    fun epgPainted() {
        if (epgLogged) return
        epgLogged = true
        sinceEnter("epg")
    }

    fun zapStarted(channelId: String?) {
        zapAt = now()
        zapChannel = channelId
        log("zap ch=${channelId.orEmpty().takeLast(24)}")
    }

    fun prepared() = sinceZap("prepare")
    fun ready() = sinceZap("ready")

    fun firstFrame() {
        sinceZap("frame")
        zapAt = 0L
    }

    private fun sinceEnter(label: String) {
        if (enterAt == 0L) return
        log("$label +${now() - enterAt}ms")
    }

    private fun sinceZap(label: String) {
        if (zapAt == 0L) return
        log("$label +${now() - zapAt}ms ch=${zapChannel.orEmpty().takeLast(24)}")
    }

    private fun log(message: String) {
        // Warn level: release builds strip Log.v/d/i (proguard-rules.pro).
        runCatching { Log.w(TAG, message) }
    }
}
