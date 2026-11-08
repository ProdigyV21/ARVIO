package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Titles a parent put on a kids profile by hand: every title of an MDBList or Trakt list row
 * added to that profile. They skip the age filter, the way the profile's YouTube row does —
 * many kids shows have no rating on TMDB at all, and "block unrated" would hide them for good.
 *
 * Kept per profile and per row, and on disk, so a kids home restored from its cache, or a
 * title opened from search, is judged the same before the row has been loaded again. Each
 * load of a row replaces that row's titles, so a title taken out of the list loses its pass.
 */
@Singleton
class KidsTrustedTitles @Inject constructor(
    @ApplicationContext context: Context,
    private val profileRepository: ProfileRepository
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /** profile id -> row id -> title keys. */
    private val byProfile = MutableStateFlow(load())

    /** The trusted title keys of the active profile. */
    val activeKeys: Flow<Set<String>> =
        combine(profileRepository.activeProfileId, byProfile) { profileId, all -> keysIn(all, profileId) }

    fun keysFor(profileId: String?): Set<String> = keysIn(byProfile.value, profileId)

    suspend fun isTrusted(mediaType: MediaType, tmdbId: Int): Boolean =
        key(mediaType, tmdbId) in activeKeys.first()

    /** Records the titles a row just loaded; only list rows of a kids profile count. */
    fun record(catalog: CatalogConfig, refs: List<Pair<MediaType, Int>>) {
        if (!isTrustedSource(catalog) || refs.isEmpty()) return
        scope.launch {
            val profile = profileRepository.getActiveProfile() ?: return@launch
            if (!profile.isKidsProfile) return@launch
            val keys = refs.mapTo(HashSet()) { (type, id) -> key(type, id) }
            val current = byProfile.value
            if (current[profile.id]?.get(catalog.id) == keys) return@launch
            val rows = current[profile.id].orEmpty() + (catalog.id to keys)
            byProfile.value = current + (profile.id to rows)
            prefs.edit().putStringSet(prefKey(profile.id, catalog.id), keys).apply()
        }
    }

    private fun load(): Map<String, Map<String, Set<String>>> = runCatching {
        val result = HashMap<String, HashMap<String, Set<String>>>()
        for ((prefKey, value) in prefs.all) {
            val keys = (value as? Set<*>)?.filterIsInstance<String>()?.toSet() ?: continue
            val profileId = prefKey.substringBefore(SEPARATOR, "")
            val rowId = prefKey.substringAfter(SEPARATOR, "")
            if (profileId.isEmpty() || rowId.isEmpty()) continue
            result.getOrPut(profileId) { HashMap() }[rowId] = keys
        }
        result
    }.getOrElse { emptyMap() }

    companion object {
        private const val PREFS_NAME = "kids_trusted_titles_v1"
        private const val SEPARATOR = '|'

        /** A list row the parent added: MDBList or Trakt, not a preinstalled or starter row. */
        fun isTrustedSource(catalog: CatalogConfig): Boolean =
            !catalog.isPreinstalled &&
                catalog.kind == CatalogKind.STANDARD &&
                (catalog.sourceType == CatalogSourceType.MDBLIST || catalog.sourceType == CatalogSourceType.TRAKT)

        fun key(mediaType: MediaType, tmdbId: Int): String = "${mediaType.name}/$tmdbId"

        private fun prefKey(profileId: String, rowId: String) = "$profileId$SEPARATOR$rowId"

        internal fun keysIn(all: Map<String, Map<String, Set<String>>>, profileId: String?): Set<String> =
            profileId?.let { all[it] }?.values?.flatMapTo(HashSet()) { it }.orEmpty()
    }
}
