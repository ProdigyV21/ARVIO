package com.arflix.tv.util

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.MutablePreferences
import com.google.gson.Gson
import com.google.gson.JsonParser

/**
 * "music" is the Music tab on phones and tablets, "music_tv" the one on TV: one profile may want it
 * on one kind of device and not the other.
 */
val OPTIONAL_NAVIGATION_SECTIONS = listOf("search", "watchlist", "tv", "news", "music", "music_tv")

/** Unrated content (live channels, news, music) that a kids profile never gets a tab for. */
val KIDS_HIDDEN_NAVIGATION_SECTIONS = setOf("tv", "news", "music", "music_tv")

fun hiddenNavigationSectionsKey(profileId: String) =
    stringPreferencesKey("profile_${profileId}_hidden_navigation_sections")

fun normalizeHiddenNavigationSections(values: Collection<String>?): Set<String> =
    OPTIONAL_NAVIGATION_SECTIONS.filterTo(linkedSetOf()) { it in values.orEmpty() }

fun readHiddenNavigationSections(json: String?): Set<String> = kotlin.runCatching {
    val value = JsonParser.parseString(json ?: "[]")
    if (!value.isJsonArray) return@runCatching emptySet<String>()
    normalizeHiddenNavigationSections(value.asJsonArray.mapNotNull {
        it.takeIf { item -> item.isJsonPrimitive && item.asJsonPrimitive.isString }?.asString
    })
}.getOrDefault(emptySet())

fun encodeHiddenNavigationSections(values: Collection<String>): String =
    Gson().toJson(normalizeHiddenNavigationSections(values))

fun navigationSectionsForCloud(prefs: Preferences, profileId: String): List<String>? =
    prefs[hiddenNavigationSectionsKey(profileId)]?.let { readHiddenNavigationSections(it).toList() }

fun restoreNavigationSections(prefs: MutablePreferences, profileId: String, hidden: Collection<String>?) {
    hidden?.let { prefs[hiddenNavigationSectionsKey(profileId)] = encodeHiddenNavigationSections(it) }
}

fun isNavigationSectionVisible(section: String, hidden: Collection<String>, isKids: Boolean = false): Boolean =
    !(section in OPTIONAL_NAVIGATION_SECTIONS && section in hidden) && !(isKids && section in KIDS_HIDDEN_NAVIGATION_SECTIONS)
