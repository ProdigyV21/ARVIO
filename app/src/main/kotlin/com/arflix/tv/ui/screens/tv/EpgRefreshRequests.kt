package com.arflix.tv.ui.screens.tv

import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull

/** One request per channel at a time, with independent short-guide and archive cooldowns. */
internal class EpgRefreshRequests(
    private val clock: () -> Long = System::currentTimeMillis,
    private val cooldownMs: Long = 120_000L,
) {
    enum class Kind { GUIDE, ARCHIVE }

    private val inFlight = HashMap<String, Kind>()
    private val attempts = LinkedHashMap<Pair<String, Kind>, Long>()

    @Synchronized
    fun claim(ids: Collection<String>, kind: Kind = Kind.GUIDE): Set<String> {
        val now = clock()
        val claimed = ids.asSequence().filter { it.isNotBlank() }.filter { id ->
            id !in inFlight && attempts[id to kind]?.let { now - it < cooldownMs } != true
        }.onEach { id ->
            inFlight[id] = kind
            attempts[id to kind] = now
        }.toSet()
        while (attempts.size > 4_000) attempts.remove(attempts.keys.first())
        return claimed
    }

    @Synchronized
    fun release(ids: Collection<String>, aborted: Boolean = false) {
        ids.forEach { id ->
            val kind = inFlight.remove(id) ?: return@forEach
            if (aborted) attempts.remove(id to kind)
        }
    }

    @Synchronized
    private fun isInFlight(id: String): Boolean = id in inFlight

    suspend fun claimArchive(id: String): Set<String> = withTimeoutOrNull(30_000L) {
        // Opening the guide often races the initial now/next request. Wait for it,
        // instead of silently dropping archive loading for another two minutes.
        while (isInFlight(id)) delay(100L)
        claim(listOf(id), Kind.ARCHIVE)
    }.orEmpty()
}
