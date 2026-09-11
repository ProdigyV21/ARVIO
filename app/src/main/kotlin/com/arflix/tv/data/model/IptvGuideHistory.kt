package com.arflix.tv.data.model

/** Provider archive bounds, shared by guide storage, refresh decisions and the fullscreen guide. */
internal object IptvGuideHistory {
    const val DAY_MS = 24L * 60L * 60_000L
    const val MAX_WINDOW_MS = 7L * DAY_MS
    const val MAX_PROGRAMS = 1_000

    /** Short refreshes patch schedules; they must not truncate a previously loaded archive. */
    fun mergeSchedules(existing: IptvNowNext?, fresh: IptvNowNext, upcomingLimit: Int = 96): IptvNowNext {
        if (existing == null) return fresh
        return IptvNowNext(
            now = mergeProgram(existing.now, fresh.now),
            next = mergeProgram(existing.next, fresh.next),
            later = mergeProgram(existing.later, fresh.later),
            recent = mergePrograms(existing.recent, fresh.recent).takeLast(MAX_PROGRAMS),
            upcoming = mergePrograms(existing.upcoming, fresh.upcoming).take(upcomingLimit),
        )
    }

    private fun mergeProgram(existing: IptvProgram?, fresh: IptvProgram?): IptvProgram? {
        if (fresh == null) return existing
        if (existing == null || programKey(existing) != programKey(fresh)) return fresh
        return if (fresh.catchupAvailable == null && existing.catchupAvailable != null) {
            fresh.copy(catchupAvailable = existing.catchupAvailable)
        } else fresh
    }

    fun mergePrograms(existing: List<IptvProgram>, fresh: List<IptvProgram>): List<IptvProgram> {
        val merged = LinkedHashMap<String, IptvProgram>(existing.size + fresh.size)
        for (program in existing.asSequence() + fresh.asSequence()) {
            if (program.title.isBlank() || program.endUtcMillis <= program.startUtcMillis) continue
            val key = programKey(program)
            merged[key] = mergeProgram(merged[key], program)!!
        }
        return merged.values.sortedBy { it.startUtcMillis }
    }

    private fun programKey(program: IptvProgram) = "${program.startUtcMillis}|${program.endUtcMillis}|${program.title}"

    fun canReplay(channel: IptvChannel?, program: IptvProgram, nowMs: Long): Boolean {
        if (program.catchupAvailable == false || program.startUtcMillis >= nowMs) return false
        if (program.catchupAvailable == true) return true
        val windowMs = days(channel) * DAY_MS
        return windowMs > 0 && program.startUtcMillis >= nowMs - windowMs
    }

    fun days(channel: IptvChannel?, force: Boolean = false): Int {
        if (channel == null) return 0
        val explicit = channel.catchupDays.coerceIn(0, 7)
        if (explicit > 0) return explicit
        if (!channel.catchupType.isNullOrBlank() || !channel.catchupSource.isNullOrBlank() ||
            channel.streamUrl.contains("/timeshift/", ignoreCase = true)
        ) return 7
        if (force || channel.xtreamStreamId != null || channel.streamUrl.contains("/live/", ignoreCase = true)) return 3
        return 0
    }

    fun hasCoverage(item: IptvNowNext?, windowMs: Long, nowMs: Long): Boolean {
        if (windowMs <= 0L) return true
        val oldest = item?.recent.orEmpty().asSequence()
            .filter { it.endUtcMillis <= nowMs && it.endUtcMillis > nowMs - windowMs }
            .minOfOrNull { it.startUtcMillis } ?: return false
        // Count alone is misleading: 24 five-minute programmes cover only two hours.
        return nowMs - oldest >= windowMs - minOf(3 * 60 * 60_000L, windowMs / 4)
    }
}
