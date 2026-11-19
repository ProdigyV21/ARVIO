package com.arflix.tv.updater

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.util.settingsDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdatePreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val ignoredTagKey = stringPreferencesKey("app_update_ignored_release_tag")
    private val lastCheckAtKey = longPreferencesKey("app_update_last_check_at_ms")
    private val quietNotificationsKey = booleanPreferencesKey("app_update_quiet_notifications")

    val ignoredTag: Flow<String?> = context.settingsDataStore.data.map { prefs -> prefs[ignoredTagKey] }
    val lastCheckAtMs: Flow<Long> = context.settingsDataStore.data.map { prefs -> prefs[lastCheckAtKey] ?: 0L }

    /** Device-local. Off (default) shows the update popup; on shows a small notice instead. */
    val quietNotifications: Flow<Boolean> =
        context.settingsDataStore.data.map { prefs -> prefs[quietNotificationsKey] ?: false }

    suspend fun setQuietNotifications(enabled: Boolean) {
        context.settingsDataStore.edit { prefs -> prefs[quietNotificationsKey] = enabled }
    }

    suspend fun setIgnoredTag(tag: String?) {
        context.settingsDataStore.edit { prefs ->
            if (tag == null) prefs.remove(ignoredTagKey) else prefs[ignoredTagKey] = tag
        }
    }

    suspend fun setLastCheckAtMs(value: Long) {
        context.settingsDataStore.edit { prefs ->
            prefs[lastCheckAtKey] = value
        }
    }
}
