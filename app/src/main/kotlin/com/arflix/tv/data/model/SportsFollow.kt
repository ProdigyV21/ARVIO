package com.arflix.tv.data.model

import com.arflix.tv.util.replaceLiteral

/**
 * A team or competition the user follows on the Sports page. Synced with the
 * account per profile, so "My sports" is the same on every device.
 */
data class SportsFollow(
    /** "team" or "league". A plain string keeps the synced JSON readable by the web app. */
    val kind: String,
    val name: String,
    val sport: String? = null,
) {
    val key: String get() = "$kind:${sportsFollowKey(name, kind == KIND_LEAGUE)}"

    companion object {
        const val KIND_TEAM = "team"
        const val KIND_LEAGUE = "league"
    }
}

/**
 * A reminder for one game. Kept on this device only: the device that is on at
 * kick-off is the one that switches. [auto] marks a reminder made because a
 * followed team plays; [dismissed] keeps such a game from being re-added after
 * the user turned its reminder off.
 */
data class SportsReminder(
    val eventId: String,
    val title: String,
    val startsAt: Long,
    val channelIds: List<String> = emptyList(),
    val auto: Boolean = false,
    val dismissed: Boolean = false,
)

private val followAffixes = Regex("^(?:(?:fc|cf|afc|sc|club)\\s+)+|(?:\\s+(?:fc|cf|afc|sc))+$")
private val leagueCountries = Regex("^(?:english|spanish|italian|german|french|dutch|portuguese|scottish|belgian|turkish)\\s+")

/** Names compare without accents, punctuation, club affixes or a league's country adjective. */
fun sportsFollowKey(name: String, league: Boolean = false): String {
    val key = sportsArtworkKey(name)
    return if (league) key.replaceLiteral(leagueCountries, "").trim() else key.replaceLiteral(followAffixes, "").trim()
}

/** Followed teams and competitions, at most this many, so the synced field stays small. */
const val SPORTS_FOLLOW_LIMIT = 100

fun List<SportsFollow>.toggled(follow: SportsFollow): List<SportsFollow> {
    if (follow.name.isBlank()) return this
    return if (any { it.key == follow.key }) filterNot { it.key == follow.key }
    else (listOf(follow) + this).take(SPORTS_FOLLOW_LIMIT)
}

/** Gson can leave a non-null field null when synced JSON is incomplete; drop such entries. */
fun List<SportsFollow>.sanitizedFollows(): List<SportsFollow> =
    filter { follow ->
        val name: String? = follow.name
        val kind: String? = follow.kind
        !name.isNullOrBlank() && (kind == SportsFollow.KIND_TEAM || kind == SportsFollow.KIND_LEAGUE)
    }.distinctBy { it.key }.take(SPORTS_FOLLOW_LIMIT)

fun List<SportsReminder>.sanitizedReminders(): List<SportsReminder> =
    filter { reminder ->
        val id: String? = reminder.eventId
        val title: String? = reminder.title
        !id.isNullOrBlank() && title != null && reminder.startsAt > 0
    }.map { reminder ->
        val channels: List<String>? = reminder.channelIds
        if (channels == null) reminder.copy(channelIds = emptyList()) else reminder
    }.distinctBy { it.eventId }
