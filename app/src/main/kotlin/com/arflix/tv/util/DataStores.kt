package com.arflix.tv.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings_prefs",
    produceMigrations = { listOf(HomePresentationMigration()) }
)

/**
 * Per-profile boolean: pin the IPTV "Favorite TV" row to the top of the home screen.
 * Read by both HomeViewModel (placement) and SettingsViewModel (the toggle), so the
 * name lives here rather than being retyped in each. Defaults to true.
 */
const val IPTV_FAVORITES_ON_HOME = "iptv_favorites_on_home"
const val WATCHLIST_ON_HOME = "watchlist_on_home"
val Context.traktDataStore: DataStore<Preferences> by preferencesDataStore(name = "trakt_prefs")
val Context.profilesDataStore: DataStore<Preferences> by preferencesDataStore(name = "profiles_prefs")
val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")
val Context.telegramDataStore: DataStore<Preferences> by preferencesDataStore(name = "telegram_prefs")
