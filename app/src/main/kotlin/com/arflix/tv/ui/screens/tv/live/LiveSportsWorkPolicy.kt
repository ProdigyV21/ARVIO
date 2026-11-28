package com.arflix.tv.ui.screens.tv.live

import android.os.Process
import java.util.concurrent.Executors
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher

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

    /**
     * Light sports work (comparing lists, joining fixtures, attaching add-on games). It must
     * never queue behind a guide scan: when it did, live add-on games only appeared after
     * the one-to-two-minute scan of a 50k-channel guide had finished.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher: CoroutineDispatcher = Dispatchers.Default.limitedParallelism(1)

    /**
     * Heavy off-screen scans each get their own background-priority thread. A normal-priority
     * worker competed with the main and render threads for the four slow cores of a TV box
     * (TCL Android 14 TV, 57k-channel playlist: these scans used 30-60% of a core each for
     * minutes while the guide dropped to ~30 fps). At background priority they only get the
     * CPU the UI leaves over. While Sports is on screen the scan runs at normal priority
     * instead, because then the user is waiting for it.
     */
    val guideScanDispatcher: CoroutineDispatcher = backgroundThread("SportsGuideScan")
    val broadcasterIndexDispatcher: CoroutineDispatcher = backgroundThread("SportsBroadcasterIndex")

    fun guideScanDispatcher(sportsOnScreen: Boolean): CoroutineDispatcher =
        if (sportsOnScreen) Dispatchers.IO else guideScanDispatcher

    private fun backgroundThread(name: String): CoroutineDispatcher = Executors.newSingleThreadExecutor { runnable ->
        Thread({
            runCatching { Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND + Process.THREAD_PRIORITY_LESS_FAVORABLE) }
            runnable.run()
        }, name).apply { isDaemon = true }
    }.asCoroutineDispatcher()
}
