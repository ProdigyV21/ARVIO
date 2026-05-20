package com.arflix.tv.util

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.compose.runtime.compositionLocalOf
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

enum class DeviceType {
    TV,
    TABLET,
    PHONE;

    fun isTouchDevice(): Boolean = this == PHONE || this == TABLET

    fun isMobile(): Boolean = isTouchDevice()
}

val LocalDeviceType = compositionLocalOf { DeviceType.TV }

/** True if the physical device has a touchscreen. Use this to decide navigation style. */
val LocalHasTouchScreen = compositionLocalOf { true }

fun deviceHasTouchScreen(context: Context): Boolean {
    return context.packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)
}

/** Key for the user's UI mode override in settingsDataStore */
val DEVICE_MODE_OVERRIDE_KEY = stringPreferencesKey("device_mode_override")

/** Key for skipping profile selection on startup */
val SKIP_PROFILE_SELECTION_KEY = booleanPreferencesKey("skip_profile_selection")

/** Key for forcing pure-black (OLED) app background */
val OLED_BLACK_BACKGROUND_KEY = booleanPreferencesKey("oled_black_background")

/** Key for the user-selected accent colour (e.g. "White", "Red", "Blue") */
val ACCENT_COLOR_KEY = stringPreferencesKey("accent_color")

/**
 * Fast-path cache for the device-mode override. Read before onCreate() during
 * cold start, where the DataStore IO would otherwise block the main thread for
 * ~50–200 ms. [setDeviceModeOverrideCache] keeps this in lock-step with the
 * DataStore value whenever the user changes the setting.
 */
private const val DEVICE_MODE_PREFS = "arvio_device_mode_cache"
private const val DEVICE_MODE_PREF_KEY = "device_mode_override"

fun setDeviceModeOverrideCache(context: Context, value: String?) {
    val prefs = context.applicationContext
        .getSharedPreferences(DEVICE_MODE_PREFS, Context.MODE_PRIVATE)
    prefs.edit().apply {
        if (value == null) remove(DEVICE_MODE_PREF_KEY)
        else putString(DEVICE_MODE_PREF_KEY, value)
    }.apply() // apply() is async to disk, returns immediately
}

/** Values: "auto" (default), "tv", "tablet", "phone" */

/** Key for device-wide quality regex filters (applies to all profiles on this device) */
val QUALITY_FILTERS_KEY = stringPreferencesKey("quality_filters")
fun detectDeviceType(context: Context): DeviceType {
    // Check for user override first via synchronous SharedPreferences mirror.
    // DataStore.first() on the main thread was adding ~50–200 ms of IO stall
    // at cold start before super.onCreate() — the splash screen showed
    // `Theme.ArflixTV.Mobile` even on TV until this completed.
    val override = try {
        context.applicationContext
            .getSharedPreferences(DEVICE_MODE_PREFS, Context.MODE_PRIVATE)
            .getString(DEVICE_MODE_PREF_KEY, null)
    } catch (_: Exception) { null }

    when (override) {
        "tv" -> return DeviceType.TV
        "tablet" -> return DeviceType.TABLET
        "phone" -> return DeviceType.PHONE
        // "auto" or null -> fall through to auto-detection
    }

    val packageManager = context.packageManager

    if (packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK) ||
        packageManager.hasSystemFeature(PackageManager.FEATURE_TELEVISION)
    ) {
        return DeviceType.TV
    }

    // No touchscreen = it's a TV even if Android thinks otherwise
    // (Chinese TVs, projectors, Fire Stick sideloads, etc.)
    if (!packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)) {
        return DeviceType.TV
    }

    val smallestWidthDp = context.resources.configuration.smallestScreenWidthDp
    if (smallestWidthDp >= 600) {
        return DeviceType.TABLET
    }

    return DeviceType.PHONE
}
