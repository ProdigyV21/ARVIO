// app/src/main/java/com/arvio/tv/player/subtitle/SubtitlePrefsRepository.kt
package com.arvio.tv.player.subtitle

import android.content.Context
import android.graphics.Color
import androidx.core.content.edit

private const val PREFS_NAME = "subtitle_prefs"
private const val KEY_FONT_SIZE = "font_size"
private const val KEY_TEXT_COLOR = "text_color"
private const val KEY_BG_COLOR = "bg_color"
private const val KEY_USE_SYSTEM = "use_system_captions"

class SubtitlePrefsRepository(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun load(): SubtitlePreferences = SubtitlePreferences(
        fontSize = prefs.getFloat(KEY_FONT_SIZE, SubtitleFontSize.MEDIUM.sp),
        textColor = prefs.getInt(KEY_TEXT_COLOR, Color.WHITE),
        backgroundColor = prefs.getInt(KEY_BG_COLOR, Color.parseColor("#80000000")),
        useSystemCaptions = prefs.getBoolean(KEY_USE_SYSTEM, true)
    )

    fun save(prefs: SubtitlePreferences) {
        this.prefs.edit {
            putFloat(KEY_FONT_SIZE, prefs.fontSize)
            putInt(KEY_TEXT_COLOR, prefs.textColor)
            putInt(KEY_BG_COLOR, prefs.backgroundColor)
            putBoolean(KEY_USE_SYSTEM, prefs.useSystemCaptions)
        }
    }
}