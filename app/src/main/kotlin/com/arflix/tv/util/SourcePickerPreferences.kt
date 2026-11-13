package com.arflix.tv.util

import androidx.datastore.preferences.core.booleanPreferencesKey

fun addonStreamDetailsKey(profileId: String) =
    booleanPreferencesKey("profile_${profileId}_show_addon_stream_details")
