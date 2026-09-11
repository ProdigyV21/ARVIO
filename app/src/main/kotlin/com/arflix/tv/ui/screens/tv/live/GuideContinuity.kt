package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvGuideHistory

/** Fullscreen and quick-zap must see the same indexed schedules as the grid. */
internal fun resolveFullscreenGuide(
    channelId: String?,
    snapshot: Map<String, IptvNowNext>,
    indexed: Map<String, IptvNowNext>,
    nowMs: Long,
): IptvNowNext? {
    if (channelId == null) return null
    val fresh = snapshot[channelId]
    val local = indexed[channelId]
    return mergeGuideSlices(fresh, local, nowMs)
}

internal fun mergeGuideSlices(primary: IptvNowNext?, secondary: IptvNowNext?, nowMs: Long): IptvNowNext? {
    if (primary == null) return secondary?.atTime(nowMs)
    if (secondary == null) return primary.atTime(nowMs)
    // Merge before rebasing: an old snapshot's "now" must not hide the index's
    // newer live programme just because both occupy the same field.
    val programs = IptvGuideHistory.mergePrograms(
        secondary.recent + listOfNotNull(secondary.now, secondary.next, secondary.later) + secondary.upcoming,
        primary.recent + listOfNotNull(primary.now, primary.next, primary.later) + primary.upcoming,
    )
    return IptvNowNext(recent = programs).atTime(nowMs)
}

/** Rebase cached schedules on the clock, without fetching or discarding archive data. */
internal fun IptvNowNext.atTime(nowMs: Long): IptvNowNext {
    val programs = (recent + listOfNotNull(now, next, later) + upcoming)
        .distinctBy { Triple(it.startUtcMillis, it.endUtcMillis, it.title) }
        .sortedBy { it.startUtcMillis }
    val future = programs.filter { it.startUtcMillis > nowMs }
    return copy(
        now = programs.lastOrNull { it.isLive(nowMs) },
        next = future.getOrNull(0),
        later = future.getOrNull(1),
        upcoming = future,
        recent = programs.filter { it.endUtcMillis <= nowMs },
    )
}

/** Retains neighbouring windows, but never the entire provider's guide in the UI heap. */
internal fun retainGuideWindows(
    previous: Map<String, IptvNowNext>,
    fresh: Map<String, IptvNowNext>,
    requested: Set<String>,
    limit: Int = 160,
): Map<String, IptvNowNext> = LinkedHashMap(previous).apply {
    requested.forEach { id ->
        val old = remove(id)
        val next = fresh[id]?.takeIf {
            it.now != null || it.next != null || it.later != null || it.upcoming.isNotEmpty() || it.recent.isNotEmpty()
        } ?: old
        if (next != null) put(id, next)
    }
    while (size > limit) remove(keys.first())
}

internal class LiveWindowRecovery(private val clock: () -> Long) {
    private var lastRecoveryAt: Long? = null

    fun claim(isCatchup: Boolean): Boolean {
        if (isCatchup) return false
        val now = clock()
        if (lastRecoveryAt?.let { now - it < 60_000L } == true) return false
        lastRecoveryAt = now
        return true
    }
}
