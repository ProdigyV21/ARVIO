package com.arflix.tv.util

import androidx.datastore.core.DataMigration
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

internal const val HOME_PRESENTATION_VERSION = 1
private val migrationKey = intPreferencesKey("home_presentation_defaults_version")
private val homeToggleNames = listOf("trailer_auto_play", "netflix_home_style")
private val homeCloudFields = setOf("homePresentationVersion", "trailerAutoPlay", "netflixHomeStyle")

internal fun canMergeHomePresentationSetting(field: String, baseVersion: Int, otherVersion: Int): Boolean =
    field !in homeCloudFields ||
        otherVersion >= baseVersion

/** Enable the 2.3 presentation once, before any settings readers start collecting. */
internal class HomePresentationMigration : DataMigration<Preferences> {
    override suspend fun shouldMigrate(currentData: Preferences): Boolean =
        (currentData[migrationKey] ?: 0) < HOME_PRESENTATION_VERSION

    override suspend fun migrate(currentData: Preferences): Preferences {
        if (!shouldMigrate(currentData)) return currentData
        return currentData.toMutablePreferences().apply {
            currentData.asMap().keys.forEach { key ->
                if (key.name.startsWith("profile_") &&
                    homeToggleNames.any { key.name.endsWith("_$it") }
                ) {
                    this[booleanPreferencesKey(key.name)] = true
                }
            }
            this[migrationKey] = HOME_PRESENTATION_VERSION
        }
    }

    override suspend fun cleanUp() = Unit
}

/** Older backups must not undo the migration or a subsequent local opt-out. */
internal fun restoreHomePresentation(
    prefs: MutablePreferences,
    profileId: String,
    cloudVersion: Int,
    trailerAutoPlay: Boolean,
    netflixHomeStyle: Boolean?
) {
    if (cloudVersion < HOME_PRESENTATION_VERSION) return
    prefs[booleanPreferencesKey("profile_${profileId}_trailer_auto_play")] = trailerAutoPlay
    netflixHomeStyle?.let {
        prefs[booleanPreferencesKey("profile_${profileId}_netflix_home_style")] = it
    }
}
