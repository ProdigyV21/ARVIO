package com.arflix.tv.ui.screens.tv.live.aisubs

import android.util.Log

/**
 * Live TV AI subtitles' log (tag `LiveAiSubs`). Links are reduced to their host, since IPTV stream
 * URLs carry account credentials; the log holds timings and decisions, never audio or text.
 */
internal object LiveAiSubsLog {
    private const val TAG = "LiveAiSubs"
    private val url = Regex("""https?://([^/\s?#]+)[^\s]*""")

    fun i(message: String) {
        Log.i(TAG, redact(message))
    }

    fun w(message: String) {
        Log.w(TAG, redact(message))
    }

    private fun redact(message: String): String = url.replace(message) { "<${it.groupValues[1]}>" }
}
