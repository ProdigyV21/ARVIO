package com.arflix.tv.ui.screens.tv.live

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * The sports schedule is prepared in the background so the Sports page opens instantly,
 * but that work (guide scan, fixture join, artwork) is heavy. Measured on a 2,000-channel
 * guide it took 4-5 Default workers for ~2.7 s on every Live TV open and again after every
 * zap, because a zap refreshes now/next and that restarts the scan. It then competed with
 * the guide's first frame and with the player starting the new channel.
 *
 * While Sports is not on screen the work waits until the user has settled, and it runs on
 * a single worker so it can never take the whole CPU away from the guide or the player.
 */
internal object LiveSportsWorkPolicy {
    /** Quiet time before background sports work starts while Sports is not on screen. */
    const val BACKGROUND_SETTLE_MS = 3_000L

    fun settleMs(sportsOnScreen: Boolean): Long = if (sportsOnScreen) 0L else BACKGROUND_SETTLE_MS

    /**
     * Quiet time before an off-screen schedule *refresh*. Browsing a large guide keeps
     * loading guide pages, and each one asked for a new full scan of the 48-hour guide
     * index; with 20k channels that SQLite pass took a quarter of the app's CPU while
     * the user scrolled. A schedule that already exists only needs refreshing once the
     * user has paused; opening Sports still refreshes at once.
     */
    const val BACKGROUND_REFRESH_SETTLE_MS = 15_000L

    fun rescanSettleMs(sportsOnScreen: Boolean, hasSchedule: Boolean): Long = when {
        sportsOnScreen -> 0L
        hasSchedule -> BACKGROUND_REFRESH_SETTLE_MS
        else -> BACKGROUND_SETTLE_MS
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher: CoroutineDispatcher = Dispatchers.Default.limitedParallelism(1)
}
