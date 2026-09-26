package com.arflix.tv.network

import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Shared concurrency lanes for metadata operations used by Home and Details.
 *
 * Replaces the hand-tuned `delay(180/220/260/320/420ms)` stagger in DetailsViewModel
 * with permit-based gating. Both HomeViewModel (catalog pages, logo/hero decoration)
 * and DetailsViewModel (primary metadata + secondary waves) submit here, so the
 * budget is shared across screens — per-ViewModel semaphores would be invisible to
 * each other while both contend on OkHttp's single per-host connection pool.
 *
 * Budgets (IMMEDIATE 3 + DEFERRED 2 + BACKGROUND 1 = 6 operations):
 * These gate repository operations, not individual HTTP calls. An operation may
 * use cache, call another service, or issue multiple requests; OkHttp still owns
 * its per-host request limits.
 * - [Priority.IMMEDIATE]: 3 slots — metadata needed before first render
 *   (details, external IDs, logo, season episodes, visible Home pagination).
 * - [Priority.DEFERRED]: 2 slots — visible but not critical (trailer, cast,
 *   initial Home rows, providers, similar + logos, collection, the focused Home hero logo).
 *   Two slots so the 8-logo fan-out trickles in pairs instead of serializing behind a single permit.
 * - [Priority.BACKGROUND]: 1 slot — the user never waits on this (reviews,
 *   Home card-logo decoration, season prefetch).
 *
 * Properties:
 * - Fast networks: permits are usually free, so deferred work starts with no
 *   artificial wait — but only when a permit is available; otherwise it queues
 *   behind in-flight work of the same class.
 * - Lanes are independent, not a strict priority queue. Background work cannot
 *   occupy the reserved foreground slots; spare foreground slots are not lent out.
 * - Cancelling a caller removes its queued acquisition or releases its held permit.
 *   Callers own cancellation: leaving composition alone does not clear a retained ViewModel.
 */
@Singleton
class TmdbPriorityDispatcher @Inject constructor() {

    enum class Priority {
        IMMEDIATE,
        DEFERRED,
        BACKGROUND,
    }

    private val immediateSlots = Semaphore(permits = 3)
    private val deferredSlots = Semaphore(permits = 2)
    private val backgroundSlots = Semaphore(permits = 1)

    suspend fun <T> withPermit(priority: Priority, block: suspend () -> T): T {
        return when (priority) {
            Priority.IMMEDIATE -> immediateSlots.withPermit { block() }
            Priority.DEFERRED -> deferredSlots.withPermit { block() }
            Priority.BACKGROUND -> backgroundSlots.withPermit { block() }
        }
    }
}
