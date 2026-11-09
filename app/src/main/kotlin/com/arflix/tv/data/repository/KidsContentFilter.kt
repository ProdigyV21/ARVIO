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
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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
 * A parent can approve an individual list title with no certification. A known
 * age limit, an unreadable certification, or a failed lookup cannot be overridden.
 */
@Singleton
class KidsContentFilter @Inject constructor(
    @ApplicationContext context: Context,
    private val tmdbApi: TmdbApi,
    private val profileRepository: ProfileRepository,
    private val catalogRepository: CatalogRepository,
    // Provider: MediaRepository is heavy and only its content language is needed here.
    private val mediaRepository: Provider<MediaRepository>
) {
    /** Filter inputs for one moment: the active cap and every age known so far. */
    data class State(
        val level: KidsAgeLevel?,
        val region: String,
        val ages: Map<String, Int>,
        /** [KidsTrustedTitles.key]s approved only when no certification exists. */
        val trusted: Set<String> = emptySet()
    ) {
        val isActive: Boolean get() = level != null

        /** True when [item] may be shown. Unknown titles never pass. */
        fun allows(item: MediaItem): Boolean {
            val cap = level ?: return true
            if (item.isPlaceholder || isCollectionTile(item)) return true
            // A parent picked every video of the profile's own YouTube row; there is no rating to look up.
            if (item.addonNativeAddonId == YouTubeCollection.ADDON_ID) return true
            if (item.id <= 0) return false
            val age = ages[key(region, item.mediaType, item.id)] ?: return false
            return allowsAge(age, cap, KidsTrustedTitles.key(item.mediaType, item.id) in trusted)
        }

        /** True when [item] still waits for a lookup, i.e. hidden only for now. */
        fun isUnknown(item: MediaItem): Boolean =
            level != null && item.id > 0 && !item.isPlaceholder && !isCollectionTile(item) &&
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
    private val initialLevel = runBlocking(Dispatchers.IO) {
        withTimeoutOrNull(INITIAL_READ_TIMEOUT_MS) { profileRepository.getActiveProfile()?.kidsLevel }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val policy = profileRepository.activeProfile.flatMapLatest { profile ->
        val level = profile?.kidsLevel
        if (profile == null || level == null) flowOf(null to emptySet<String>())
        else catalogRepository.observeKidsListApprovals(profile.id).map { level to it }
            .onStart { emit(level to emptySet()) }
    }

    val state: StateFlow<State> = combine(policy, ages) { (level, approved), ages ->
        State(level, region(), ages, approved)
    }.stateIn(scope, SharingStarted.Eagerly, State(initialLevel, region(), ages.value))

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
        val profile = profileRepository.getActiveProfile()
        val cap = profile?.kidsLevel ?: return true
        if (tmdbId < 0 && mediaRepository.get().addonNativeCard(mediaType, tmdbId)?.addonNativeAddonId == YouTubeCollection.ADDON_ID) {
            return true
        }
        if (tmdbId <= 0) return false
        val region = region()
        val key = key(region, mediaType, tmdbId)
        val titleKey = KidsTrustedTitles.key(mediaType, tmdbId)
        val approved = titleKey in catalogRepository.observeKidsListApprovals(profile.id).first()
        val cached = ages.value[key]
        // Recheck an exception when opening a title. A stale "unrated" result must
        // not override a certification added since the parent approved it.
        val age = (if (approved && (cached == NO_CERTIFICATION || cached == UNRATED)) null else cached)
            ?: fetchAge(mediaType, tmdbId, region)?.also { record(key, it) }
            ?: return false
        val current = profileRepository.getActiveProfile()
        if (current?.id != profile.id || current.kidsLevel != cap) return false
        return allowsAge(age, cap, approved && age == NO_CERTIFICATION &&
            titleKey in catalogRepository.observeKidsListApprovals(profile.id).first())
    }

    internal suspend fun approveListTitle(session: KidsListApprovalSession, item: MediaItem) {
        catalogRepository.checkKidsListApproval(session)
        val refs = mediaRepository.get().loadExternalListRefs(session.catalog)
            ?: throw CatalogException(com.arflix.tv.R.string.kids_list_approval_unavailable)
        if (item.id <= 0 || (item.mediaType to item.id) !in refs) {
            throw CatalogException(com.arflix.tv.R.string.kids_list_approval_changed)
        }
        val region = region()
        val age = fetchAge(item.mediaType, item.id, region)
            ?: throw CatalogException(com.arflix.tv.R.string.kids_list_approval_unavailable)
        record(key(region, item.mediaType, item.id), age)
        if (age != NO_CERTIFICATION) throw CatalogException(com.arflix.tv.R.string.kids_list_approval_rated)
        catalogRepository.saveKidsListApproval(session, item.mediaType, item.id)
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
            ages.update { it + batch }
            persist(batch)
        }
    }

    private fun record(key: String, age: Int) {
        ages.update { it + (key to age) }
        persist(mapOf(key to age))
    }

    /** Null means unavailable; [NO_CERTIFICATION] means a successful response with no certification. */
    private suspend fun fetchAge(mediaType: MediaType, id: Int, region: String): Int? = try {
        when (mediaType) {
            MediaType.MOVIE -> {
                val response = tmdbApi.getMovieReleaseDates(id, Constants.TMDB_API_KEY)
                KidsCertification.forMovie(response, region) ?: if (response.results.any { country ->
                    country.releaseDates.any { !it.certification.isNullOrBlank() }
                }) UNRATED else NO_CERTIFICATION
            }
            MediaType.TV -> {
                val response = tmdbApi.getTvContentRatings(id, Constants.TMDB_API_KEY)
                KidsCertification.forTv(response, region) ?: if (response.results.any { !it.rating.isNullOrBlank() }) {
                    UNRATED
                } else NO_CERTIFICATION
            }
        }
    } catch (e: Exception) {
        if (e is CancellationException) throw e
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
        internal const val NO_CERTIFICATION = -2

        private fun allowsAge(age: Int, cap: KidsAgeLevel, approved: Boolean): Boolean =
            age in 0..cap.maxAge || (age == NO_CERTIFICATION && approved)

        fun key(region: String, mediaType: MediaType, id: Int): String =
            "$region/${mediaType.name}/$id"

        /** Collection tiles open a collection page whose titles are filtered there. */
        private fun isCollectionTile(item: MediaItem): Boolean =
            item.collectionGroup != null || item.collectionTileShape != null
    }
}
