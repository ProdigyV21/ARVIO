package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.KidsAgeLevel
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import com.arflix.tv.util.ContentRating
import com.arflix.tv.util.KidsCertification
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.sync.Semaphore
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Age filter of a kids profile.
 *
 * Lists can't wait for a rating lookup per title, so filtering is optimistic in the
 * other direction: a title is hidden until its certification is known and allowed.
 * Unknown titles are looked up in the background and appear once they pass; the
 * screens re-filter because [state] changes. Titles without a readable rating — and
 * items with no TMDB id at all — never pass, which is the "block unrated" rule.
 *
 * Minimum ages are kept on disk per country, so a kids home opens filtered at once
 * after the first visit instead of re-asking TMDB for every card.
 *
 * Titles of the list rows a parent added to the profile ([KidsTrustedTitles]) always pass.
 */
@Singleton
class KidsContentFilter @Inject constructor(
    @ApplicationContext context: Context,
    private val tmdbApi: TmdbApi,
    private val profileRepository: ProfileRepository,
    private val trustedTitles: KidsTrustedTitles,
    // Provider: MediaRepository is heavy and only its content language is needed here.
    private val mediaRepository: Provider<MediaRepository>
) {
    /** Filter inputs for one moment: the active cap and every age known so far. */
    data class State(
        val level: KidsAgeLevel?,
        val region: String,
        val ages: Map<String, Int>,
        /** [KidsTrustedTitles.key]s that pass whatever their rating. */
        val trusted: Set<String> = emptySet()
    ) {
        val isActive: Boolean get() = level != null

        /** True when [item] may be shown. Never true for an unknown or unrated title. */
        fun allows(item: MediaItem): Boolean {
            val cap = level ?: return true
            if (item.isPlaceholder || isCollectionTile(item)) return true
            // A parent picked every video of the profile's own YouTube row; there is no rating to look up.
            if (item.addonNativeAddonId == YouTubeCollection.ADDON_ID) return true
            if (item.id <= 0) return false
            if (KidsTrustedTitles.key(item.mediaType, item.id) in trusted) return true
            val age = ages[key(region, item.mediaType, item.id)] ?: return false
            return age != UNRATED && age <= cap.maxAge
        }

        /** True when [item] still waits for a lookup, i.e. hidden only for now. */
        fun isUnknown(item: MediaItem): Boolean =
            level != null && item.id > 0 && !item.isPlaceholder && !isCollectionTile(item) &&
                KidsTrustedTitles.key(item.mediaType, item.id) !in trusted &&
                key(region, item.mediaType, item.id) !in ages
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val ages = MutableStateFlow(loadAges())
    private val lookups = Channel<Pair<MediaType, Int>>(Channel.UNLIMITED)
    private val inFlight = ConcurrentHashMap.newKeySet<String>()
    private val pending = ConcurrentHashMap<String, Int>()

    /**
     * Read once, blocking, when the filter is created (the first kids-aware screen): a
     * null start would show that screen unfiltered until the profile store answers, and
     * Home's billboard keeps whatever title it picks in that moment.
     */
    private val initialProfile = runBlocking(Dispatchers.IO) {
        withTimeoutOrNull(INITIAL_READ_TIMEOUT_MS) { profileRepository.getActiveProfile() }
    }
    private val initialLevel: KidsAgeLevel? = initialProfile?.kidsLevel

    private val level: StateFlow<KidsAgeLevel?> = profileRepository.activeProfile
        .map { it?.kidsLevel }
        .stateIn(scope, SharingStarted.Eagerly, initialLevel)

    val state: StateFlow<State> = combine(level, ages, trustedTitles.activeKeys) { level, ages, trusted ->
        State(level, region(), ages, trusted)
    }.stateIn(
        scope,
        SharingStarted.Eagerly,
        State(initialLevel, region(), ages.value, trustedTitles.keysFor(initialProfile?.id))
    )

    init {
        scope.launch { runLookups() }
        scope.launch { flushResults() }
    }

    /**
     * The active profile's cap, read fresh from the profile store: the cached [level]
     * starts as null until the store's first read, and a gate must not pass on that.
     */
    suspend fun activeLevel(): KidsAgeLevel? = profileRepository.getActiveProfile()?.kidsLevel

    /**
     * Blocking check for a single title, for the details and player gates. Waits for the
     * lookup instead of hiding, so a title opened from anywhere is judged before it plays.
     */
    suspend fun isAllowed(mediaType: MediaType, tmdbId: Int): Boolean {
        val cap = activeLevel() ?: return true
        if (tmdbId < 0 && mediaRepository.get().addonNativeCard(mediaType, tmdbId)?.addonNativeAddonId == YouTubeCollection.ADDON_ID) {
            return true
        }
        if (tmdbId <= 0) return false
        if (trustedTitles.isTrusted(mediaType, tmdbId)) return true
        val region = region()
        val key = key(region, mediaType, tmdbId)
        val age = ages.value[key] ?: fetchAge(mediaType, tmdbId, region)?.also { record(key, it) }
            ?: return false
        return age != UNRATED && age <= cap.maxAge
    }

    /** Filters [items] with the current state and queues lookups for the unknown ones. */
    fun filter(items: List<MediaItem>, state: State = this.state.value): List<MediaItem> {
        if (!state.isActive) return items
        requestUnknown(items, state)
        return items.filter(state::allows)
    }

    /** Filters every row and drops the rows nothing in them survives. */
    fun filterCategories(categories: List<Category>, state: State = this.state.value): List<Category> {
        if (!state.isActive) return categories
        return categories.mapNotNull { category ->
            val kept = filter(category.items, state)
            when {
                kept.size == category.items.size -> category
                kept.isEmpty() -> null
                else -> category.copy(items = kept)
            }
        }
    }

    private fun requestUnknown(items: List<MediaItem>, state: State) {
        for (item in items) {
            if (!state.isUnknown(item)) continue
            val key = key(state.region, item.mediaType, item.id)
            if (inFlight.add(key)) lookups.trySend(item.mediaType to item.id)
        }
    }

    private suspend fun runLookups() {
        val permits = Semaphore(LOOKUP_PARALLELISM)
        for ((mediaType, id) in lookups) {
            permits.acquire()
            scope.launch {
                val region = region()
                val key = key(region, mediaType, id)
                try {
                    // A failed request stays unknown: hidden now, asked again next time.
                    fetchAge(mediaType, id, region)?.let { pending[key] = it }
                } finally {
                    inFlight.remove(key)
                    permits.release()
                }
            }
        }
    }

    /** Batches results so a screen of cards re-filters a few times, not once per card. */
    private suspend fun flushResults() {
        while (true) {
            delay(FLUSH_INTERVAL_MS)
            if (pending.isEmpty()) continue
            val batch = HashMap(pending)
            batch.keys.forEach(pending::remove)
            ages.value = ages.value + batch
            persist(batch)
        }
    }

    private fun record(key: String, age: Int) {
        ages.value = ages.value + (key to age)
        persist(mapOf(key to age))
    }

    /** The minimum age, [UNRATED] when TMDB has no readable rating, null when the request failed. */
    private suspend fun fetchAge(mediaType: MediaType, id: Int, region: String): Int? = try {
        val age = when (mediaType) {
            MediaType.MOVIE -> KidsCertification.forMovie(
                tmdbApi.getMovieReleaseDates(id, Constants.TMDB_API_KEY), region
            )
            MediaType.TV -> KidsCertification.forTv(
                tmdbApi.getTvContentRatings(id, Constants.TMDB_API_KEY), region
            )
        }
        age ?: UNRATED
    } catch (e: Exception) {
        AppLogger.w(TAG, "Rating lookup failed for $mediaType $id: ${e.message}")
        null
    }

    private fun region(): String =
        ContentRating.regionOf(runCatching { mediaRepository.get().contentLanguage }.getOrNull())

    private fun loadAges(): Map<String, Int> = runCatching {
        prefs.all.mapNotNull { (key, value) -> (value as? Int)?.let { key to it } }.toMap()
    }.getOrElse { emptyMap() }

    private fun persist(batch: Map<String, Int>) {
        val editor = prefs.edit()
        if (prefs.all.size + batch.size > MAX_STORED) editor.clear()
        batch.forEach { (key, age) -> editor.putInt(key, age) }
        editor.apply()
    }

    companion object {
        private const val TAG = "KidsContentFilter"
        // Versioned: bump when the way an age is derived changes, so stale ages are not reused.
        private const val PREFS_NAME = "kids_content_ratings_v3"
        private const val LOOKUP_PARALLELISM = 6
        private const val FLUSH_INTERVAL_MS = 300L
        private const val MAX_STORED = 20_000
        private const val INITIAL_READ_TIMEOUT_MS = 1_000L
        const val UNRATED = -1

        fun key(region: String, mediaType: MediaType, id: Int): String =
            "$region/${mediaType.name}/$id"

        /** Collection tiles open a collection page whose titles are filtered there. */
        private fun isCollectionTile(item: MediaItem): Boolean =
            item.collectionGroup != null || item.collectionTileShape != null
    }
}
