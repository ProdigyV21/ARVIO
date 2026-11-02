package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.arflix.tv.BuildConfig
import com.arflix.tv.util.settingsDataStore
import com.google.gson.JsonObject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

internal object PartnerPreferencePolicy {
    val names = mapOf(
        "netflixHomeStyle" to "netflix_home_style", "watchlistOnHome" to "watchlist_on_home",
        "trailerAutoPlay" to "trailer_auto_play", "trailerInCards" to "trailer_in_cards",
        "trailerSoundEnabled" to "trailer_sound_enabled", "autoPlayNext" to "auto_play_next",
        "autoPlaySingleSource" to "auto_play_single_source", "showBudget" to "show_budget_on_home",
        "showEpisodeRatings" to "show_episode_ratings", "iptvFavoritesOnHome" to "iptv_favorites_on_home",
        "spoilerBlurEnabled" to "spoiler_blur", "includeSpecials" to "include_specials",
        "cardLayoutMode" to "card_layout_mode", "clockFormat" to "clock_format",
        "streamSearchMode" to "stream_search_mode", "animeEpisodeStructuring" to "anime_episode_structuring"
    )
    private val enums = mapOf("cardLayoutMode" to setOf("landscape", "poster"),
        "clockFormat" to setOf("12h", "24h"), "streamSearchMode" to setOf("parallel", "sequential"),
        "animeEpisodeStructuring" to setOf("broadcast", "standard"))
    fun parse(json: JsonObject): Map<String, Any> = json.entrySet().associate { (key, value) ->
        val name = names[key] ?: error("Unsupported preference")
        val options = enums[key]
        val parsed: Any = if (options == null) {
            require(value.isJsonPrimitive && value.asJsonPrimitive.isBoolean) { "Invalid preference" }
            value.asBoolean
        } else {
            require(value.isJsonPrimitive && value.asString in options) { "Invalid preference" }
            value.asString
        }
        name to parsed
    }

    fun allowed(key: String, permissions: PartnerPermissions): Boolean = when {
        key.contains("catalog") -> permissions.editCatalogs
        key.contains("metadata") || key.contains("tmdb_api") || key.contains("tvdb_") ||
            key.endsWith("anime_episode_structuring") -> permissions.editMetadata
        key.contains("iptv_m3u") || key.contains("iptv_epg_url") || key.contains("iptv_playlists") ||
            key.contains("stalker") || key.contains("home_server_connection") || key.contains("torrserver") ||
            key.contains("stream_provider") || key.contains("stream_integration") -> permissions.addConnections
        listOf("netflix_home_style", "watchlist_on_home", "card_layout_mode", "show_budget_on_home",
            "show_episode_ratings", "iptv_favorites_on_home", "spoiler_blur", "clock_format", "accent_color",
            "oled_black_background", "smooth_scrolling", "guide_row_count").any { key.endsWith(it) } -> permissions.editAppearance
        listOf("auto_play", "trailer_", "subtitle_size", "subtitle_color", "subtitle_style", "subtitle_font",
            "subtitle_offset", "subtitle_ai_", "dolby_vision", "frame_rate_matching", "volume_boost",
            "include_specials", "quality_filters", "stream_search_mode").any { key.contains(it) } -> permissions.editPlayback
        else -> true
    }
}

/** A runtime view only. Personal cloud export always reads the original DataStore. */
@Singleton
class PartnerPreferences @Inject constructor(
    @ApplicationContext context: Context,
    private val profiles: ProfileManager,
    private val partner: dagger.Lazy<PartnerDeliveryRepository>
) : DataStore<Preferences> {
    private val personal = context.settingsDataStore
    private fun overrideKey(delivery: PartnerIptvDelivery, profile: String) =
        stringSetPreferencesKey("partner_overrides_${partner.get().accountId()}_${delivery.organizationId}_$profile")

    override val data = combine(personal.data, profiles.activeProfileId, partner.get().deliveries) { prefs, profile, delivery ->
        overlay(prefs, profile, delivery)
    }

    private fun overlay(prefs: Preferences, profile: String, delivery: PartnerIptvDelivery?): Preferences =
        if (delivery == null) prefs else prefs.toMutablePreferences().apply {
            val overrides = prefs[overrideKey(delivery, profile)].orEmpty()
            delivery.settings.forEach { (name, value) ->
                val key = "profile_${profile}_$name"
                if (key !in overrides || !PartnerPreferencePolicy.allowed(key, delivery.permissions)) {
                    when (value) {
                        is Boolean -> this[booleanPreferencesKey(key)] = value
                        is String -> this[stringPreferencesKey(key)] = value
                    }
                }
            }
        }

    override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences {
        return personal.updateData { before ->
            val delivery = partner.get().activeDelivery() ?: return@updateData transform(before)
            val profile = profiles.getProfileIdSync()
            val effective = overlay(before, profile, delivery)
            val proposed = transform(effective)
            val result = before.toMutablePreferences()
            val key = overrideKey(delivery, profile)
            val overrides = before[key].orEmpty().toMutableSet()
            (effective.asMap().keys + proposed.asMap().keys).forEach { changed ->
                if (effective.asMap()[changed] != proposed.asMap()[changed] &&
                    PartnerPreferencePolicy.allowed(changed.name, delivery.permissions)) {
                    @Suppress("UNCHECKED_CAST") val typed = changed as Preferences.Key<Any>
                    proposed[typed]?.let { result[typed] = it } ?: result.remove(typed)
                    if (delivery.settings.keys.any { changed.name == "profile_${profile}_$it" }) {
                        overrides += changed.name
                    }
                }
            }
            result[key] = overrides
            result
        }
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PartnerPreferencesEntryPoint {
    fun partnerPreferences(): PartnerPreferences
}

val Context.effectiveSettingsDataStore: DataStore<Preferences>
    get() = if (!BuildConfig.PARTNER_MANAGEMENT_ENABLED) settingsDataStore else
        EntryPointAccessors.fromApplication(applicationContext, PartnerPreferencesEntryPoint::class.java).partnerPreferences()
