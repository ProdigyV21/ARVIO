package com.arflix.tv.ui.screens.tv.live

import android.content.Context
import androidx.media3.ui.AspectRatioFrameLayout
import org.json.JSONObject

/**
 * Small per-device Live TV player choices that should outlive the screen:
 * the audio language picked for each channel and the picture shape.
 */
internal object LivePlayerPrefs {
    private const val FILE = "live_tv_player"
    private const val AUDIO = "audio_by_channel"
    private const val PICTURE = "picture_mode"
    private const val AUDIO_LIMIT = 200

    fun audioChoices(context: Context): Map<String, String> = runCatching {
        val json = JSONObject(context.getSharedPreferences(FILE, Context.MODE_PRIVATE).getString(AUDIO, null) ?: return emptyMap())
        buildMap { json.keys().forEach { key -> json.optString(key).takeIf { it.isNotBlank() }?.let { put(key, it) } } }
    }.getOrDefault(emptyMap())

    fun saveAudioChoices(context: Context, choices: Map<String, String>) {
        val kept = choices.entries.toList().takeLast(AUDIO_LIMIT)
        val json = JSONObject().apply { kept.forEach { (k, v) -> put(k, v) } }
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE).edit().putString(AUDIO, json.toString()).apply()
    }

    fun pictureMode(context: Context): LivePictureMode =
        LivePictureMode.entries.getOrNull(context.getSharedPreferences(FILE, Context.MODE_PRIVATE).getInt(PICTURE, 0))
            ?: LivePictureMode.Fit

    fun savePictureMode(context: Context, mode: LivePictureMode) {
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE).edit().putInt(PICTURE, mode.ordinal).apply()
    }
}

/** Picture shape on the play bar: whole picture, fill the screen (crop), or stretch. */
internal enum class LivePictureMode(val resizeMode: Int) {
    Fit(AspectRatioFrameLayout.RESIZE_MODE_FIT),
    Fill(AspectRatioFrameLayout.RESIZE_MODE_ZOOM),
    Stretch(AspectRatioFrameLayout.RESIZE_MODE_FILL);

    fun next(): LivePictureMode = entries[(ordinal + 1) % entries.size]
}

/** Sleep timer steps on the play bar, in minutes; 0 is off. */
internal val LiveSleepSteps = listOf(0, 30, 60, 90, 120)

internal fun nextSleepStep(current: Int): Int =
    LiveSleepSteps[(LiveSleepSteps.indexOf(current).coerceAtLeast(0) + 1) % LiveSleepSteps.size]
