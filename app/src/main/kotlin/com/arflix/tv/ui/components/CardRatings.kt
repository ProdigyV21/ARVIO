package com.arflix.tv.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap

/**
 * One IMDb score per title for phone cards, shared by every screen, so a card in Library shows
 * the same number as the Home hero and the details page instead of a TMDB vote average.
 */
object CardRatings {
    /** "<type>_<id>" to the IMDb score; an empty string means none exists. */
    val imdb = mutableStateMapOf<String, String>()

    /** Installed by the repository; fetches (and caches) the IMDb score for a title. */
    @Volatile var fetcher: (suspend (MediaType, Int) -> String?)? = null

    private val requested = ConcurrentHashMap.newKeySet<String>()
    private val permits = Semaphore(4)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun key(mediaType: MediaType, id: Int) = "${mediaType}_$id"

    fun put(mediaType: MediaType, id: Int, rating: String?) {
        imdb[key(mediaType, id)] = rating?.trim().orEmpty()
    }

    fun request(mediaType: MediaType, id: Int) {
        if (id <= 0) return
        val k = key(mediaType, id)
        val fetch = fetcher ?: return
        if (imdb.containsKey(k) || !requested.add(k)) return
        scope.launch {
            val rating = try {
                permits.withPermit { fetch(mediaType, id) }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                null
            }
            withContext(Dispatchers.Main.immediate) { imdb[k] = rating?.trim().orEmpty() }
        }
    }
}

/** A card's score and whether it is IMDb's (shown with the IMDb badge) or a TMDB vote (a star). */
internal data class CardScore(val value: String, val isImdb: Boolean)

/**
 * The score a phone card shows: IMDb once known. A TMDB vote only appears when the title has no
 * IMDb score at all, and nothing shows while the lookup runs, so the number never jumps.
 */
@Composable
internal fun rememberMobileCardRating(item: MediaItem, enabled: Boolean): CardScore? {
    if (!enabled) return null
    mobileCardRating(item.imdbRating)?.let { return CardScore(it, isImdb = true) }
    if (item.id <= 0) return mobileCardRating(item.tmdbRating, item.rating)?.let { CardScore(it, isImdb = false) }
    LaunchedEffect(item.mediaType, item.id) { CardRatings.request(item.mediaType, item.id) }
    val shared = CardRatings.imdb[CardRatings.key(item.mediaType, item.id)] ?: return null
    mobileCardRating(shared)?.let { return CardScore(it, isImdb = true) }
    return mobileCardRating(item.tmdbRating, item.rating)?.let { CardScore(it, isImdb = false) }
}
