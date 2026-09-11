package com.arflix.tv.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

/**
 * How long a downloaded Stalker channel list stays reusable inside the running
 * app. Long enough to cover the startup burst and normal navigation between
 * screens, short enough that the `play_token` carried in every channel URL of
 * that list stays young.
 */
internal const val STALKER_CHANNEL_LIST_FRESHNESS_MS = 5 * 60_000L

/**
 * Lets every entry point that needs the Stalker channel list share a single
 * download instead of starting its own.
 *
 * Three entry points ask for the list on a normal app start — the startup
 * prefetch, the live TV snapshot load and the guide backfill load. Each used to
 * open its own portal session and pull the full list; on a portal with ~21k
 * channels that is 29 MB per entry point.
 *
 * Two mechanisms, both memory only:
 *  - concurrent callers await the download that is already running;
 *  - a caller arriving within [freshnessWindowMs] of a usable download reuses
 *    its result instead of starting another one.
 *
 * **One entry per key, and a key is one portal.** Every portal is downloaded,
 * remembered and retried on its own, so the outcome of one portal can never
 * decide anything about another. A portal that failed leaves no entry behind
 * and is asked again by the very next load, while the portals that did answer
 * keep their lists — which is the whole point of this class for anybody running
 * more than one portal.
 *
 * A caller that wants genuinely fresh data passes `freshSinceMs` — the moment
 * it decided it needed fresh data. A remembered list downloaded after that
 * moment already answers the request; an older one does not and is skipped.
 * That timestamp is what keeps one configuration change from costing two
 * downloads: the second entry point reacting to it asked before the first
 * one's download finished, so that download is fresh enough for it too.
 * A download that is already running is joined either way — a running download
 * is never older than the remembered result (a new one only starts when none
 * is in flight), so joining it is what "fresh" means here.
 *
 * Nothing discards a running download except [retainOnly], which drops the
 * portals that are no longer configured. A configuration change that leaves a
 * portal alone has nothing to do with that portal's channel list.
 *
 * The download runs in [scope] rather than in the calling coroutine, so a
 * caller that gives up (its own timeout, a screen the user left) does not
 * cancel the download the other callers are waiting for.
 *
 * Deliberately **not** persisted across app runs: the playable URLs in the
 * channel list carry a `play_token`, so a list restored from disk would hand
 * out expired tokens. A fresh app start always downloads once.
 *
 * @param isReusable decides whether a result may be remembered. A portal whose
 *   handshake failed yields an empty list, and remembering that would keep the
 *   portal dark for the whole window.
 */
internal class StalkerChannelListLoader<T : Any>(
    private val freshnessWindowMs: Long = STALKER_CHANNEL_LIST_FRESHNESS_MS,
    private val scope: CoroutineScope,
    private val nowMs: () -> Long = System::currentTimeMillis,
    private val isReusable: (T) -> Boolean = { true }
) {

    private val lock = Any()

    /** One entry per key; see [retainOnly] for the only thing that removes one. */
    private val entries = HashMap<String, Entry<T>>()

    private class Entry<T : Any> {
        var inFlight: Deferred<T>? = null
        var remembered: T? = null
        var rememberedAtMs: Long = 0L
    }

    /**
     * Returns the channel list for [key], running [fetch] at most once per
     * freshness window. [key] identifies a single configured portal (id + URL +
     * MAC), so portals never share an entry and never mask each other.
     *
     * @param freshSinceMs the moment the caller decided it needed fresh data.
     *   A remembered list downloaded before that moment is skipped; one
     *   downloaded after it already answers the request. Pass `0` to accept any
     *   list inside the freshness window. A download that is already running is
     *   shared in either case: asking for fresh data is about not being served
     *   an old list, not about opening a second portal session next to the
     *   first.
     */
    suspend fun load(key: String, freshSinceMs: Long = 0L, fetch: suspend () -> T): T {
        val pending = synchronized(lock) {
            val entry = entries.getOrPut(key) { Entry() }
            val previous = entry.remembered
            if (previous != null) {
                val insideWindow = nowMs() - entry.rememberedAtMs < freshnessWindowMs
                if (insideWindow && entry.rememberedAtMs >= freshSinceMs) return previous
                entry.remembered = null
                entry.rememberedAtMs = 0L
            }
            entry.inFlight?.takeIf { !it.isCompleted } ?: startLocked(key, entry, fetch)
        }
        return pending.await()
    }

    /**
     * Drops everything held for portals outside [keys] — their channel list and,
     * with it, the portal session that came with it.
     *
     * This is the only thing that discards an entry, and it exists for the case
     * that never calls [load]: a portal was disabled or removed, so nothing asks
     * for its channel list any more and it would otherwise be held until the app
     * dies. Passing an empty set releases everything, which is what removing the
     * *last* portal means.
     *
     * Portals that are still configured keep their entries, so an unrelated
     * playlist change costs nothing.
     */
    fun retainOnly(keys: Set<String>) {
        synchronized(lock) {
            entries.keys.retainAll(keys)
        }
    }

    private fun startLocked(key: String, entry: Entry<T>, fetch: suspend () -> T): Deferred<T> {
        val deferred = scope.async {
            val value = fetch()
            synchronized(lock) {
                // Only store while this download is still the current one for
                // its key. A download detached by [retainOnly] finds a removed
                // (or freshly recreated) entry and drops its result instead of
                // overwriting the one that replaced it.
                if (entries[key] === entry && isReusable(value)) {
                    entry.remembered = value
                    entry.rememberedAtMs = nowMs()
                }
            }
            value
        }
        entry.inFlight = deferred
        return deferred
    }
}
