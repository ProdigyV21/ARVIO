package com.arflix.tv.ui.screens.tv.live

import android.content.Context

/**
 * Remembers, per profile and per device, whether Live TV was left on the Sports page,
 * so the next visit opens there again instead of on the channel list.
 *
 * The screen keeps its own choice in rememberSaveable, which is lost as soon as the
 * Live TV destination leaves the back stack (switching tabs, a cold start).
 */
internal object LiveSportsMemory {
    private const val FILE = "live_tv_player"
    private const val KEY_PREFIX = "sports_open:"

    /** Decision kept pure so it can be unit tested without a Context. */
    fun shouldReopen(remembered: Boolean, initialChannelId: String?, initialStreamUrl: String?): Boolean =
        remembered && initialChannelId.isNullOrBlank() && initialStreamUrl.isNullOrBlank()

    fun reopen(context: Context, profileId: String?, initialChannelId: String?, initialStreamUrl: String?): Boolean =
        shouldReopen(
            remembered = runCatching {
                context.getSharedPreferences(FILE, Context.MODE_PRIVATE).getBoolean(key(profileId), false)
            }.getOrDefault(false),
            initialChannelId = initialChannelId,
            initialStreamUrl = initialStreamUrl,
        )

    fun save(context: Context, profileId: String?, open: Boolean) {
        runCatching {
            val prefs = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
            if (prefs.getBoolean(key(profileId), false) != open) {
                prefs.edit().putBoolean(key(profileId), open).apply()
            }
        }
    }

    /** Touch devices only show Sports in the Guide mode, so a restored Sports page needs it. */
    fun startupMode(
        isTouchDevice: Boolean,
        initialChannelId: String?,
        initialStreamUrl: String?,
        reopenSports: Boolean,
    ): LiveTvStartup.LiveTvMode =
        if (reopenSports) LiveTvStartup.LiveTvMode.Guide
        else LiveTvStartup.initialMode(isTouchDevice, initialChannelId, initialStreamUrl)

    private fun key(profileId: String?) = KEY_PREFIX + (profileId?.takeIf { it.isNotBlank() } ?: "default")
}
