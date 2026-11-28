package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.util.replaceLiteral

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvNowNext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withTimeoutOrNull

private val NON_VOD_EPG_CHANNEL_TERMS = setOf(
    "sport",
    "sports",
    "football",
    "soccer",
    "basketball",
    "tennis",
    "motorsport",
    "racing",
    "rugby",
    "hockey",
    "baseball",
    "boxing",
    "ufc",
    "mma",
    "cricket",
    "golf",
    "nfl",
    "nba",
    "mlb",
    "nhl",
    "f1",
    "news",
    "weather",
    "shopping",
    "teleshopping",
)

private val MIXED_ENTERTAINMENT_GROUP_TERMS = setOf(
    "entertainment",
    "movie",
    "movies",
    "film",
    "films",
    "cinema",
    "series",
    "general",
)

internal enum class EpgTemporalState {
    Live,
    Past,
    Future,
}

internal enum class EpgInteractionAction {
    PlayLiveMini,
    PlayLiveFullscreen,
    PlayCatchup,
    ResolveVodOrPlayFullscreen,
    ShowVodDialog,
    /** A show that has not started, or one that aired without catch-up: its details and Remind me. */
    ShowProgrammeInfo,
    NoOp,
}

internal fun epgDialogWatchLiveAction(): EpgInteractionAction =
    EpgInteractionAction.PlayLiveFullscreen

internal suspend fun <T> runEpgLookupWithTimeout(
    timeoutMillis: Long,
    lookup: suspend () -> T,
): T? = withTimeoutOrNull(timeoutMillis) { lookup() }

internal suspend fun awaitLiveEpgProgram(
    programUpdates: Flow<IptvProgram?>,
    timeoutMillis: Long,
    nowMillis: () -> Long = System::currentTimeMillis,
): IptvProgram? = withTimeoutOrNull(timeoutMillis) {
    programUpdates
        .filterNotNull()
        .firstOrNull { program -> program.isLive(nowMillis()) }
}

internal fun channelRowInteractionAction(
    isSamePlayingChannel: Boolean,
): EpgInteractionAction = when {
    !isSamePlayingChannel -> EpgInteractionAction.PlayLiveMini
    else -> EpgInteractionAction.PlayLiveFullscreen
}

internal fun epgProgramInteractionAction(
    temporalState: EpgTemporalState,
    isSamePlayingChannel: Boolean,
    isCatchupSupported: Boolean,
    vodActionsEnabled: Boolean,
): EpgInteractionAction = when (temporalState) {
    EpgTemporalState.Past -> if (isCatchupSupported) {
        EpgInteractionAction.PlayCatchup
    } else {
        EpgInteractionAction.ShowProgrammeInfo
    }
    EpgTemporalState.Future -> EpgInteractionAction.ShowProgrammeInfo
    EpgTemporalState.Live -> when {
        !isSamePlayingChannel -> EpgInteractionAction.PlayLiveMini
        vodActionsEnabled -> EpgInteractionAction.ResolveVodOrPlayFullscreen
        else -> EpgInteractionAction.PlayLiveFullscreen
    }
}

internal fun vodLookupResolution(hasVodMatch: Boolean): EpgInteractionAction =
    if (hasVodMatch) EpgInteractionAction.ShowVodDialog else EpgInteractionAction.PlayLiveFullscreen

internal class EpgVodLookupGuard {
    private var generation = 0

    fun beginLookup(): Int = ++generation

    fun invalidate() {
        generation++
    }

    fun isCurrent(lookupGeneration: Int): Boolean = lookupGeneration == generation
}

internal fun epgVodLookupCanPublish(
    selectedProgram: IptvProgram,
    currentProgram: IptvProgram?,
    nowMillis: Long,
): Boolean = currentProgram != null &&
    currentProgram.title == selectedProgram.title &&
    currentProgram.startUtcMillis == selectedProgram.startUtcMillis &&
    currentProgram.endUtcMillis == selectedProgram.endUtcMillis &&
    currentProgram.isLive(nowMillis)

internal fun guideIdentityKeys(vararg values: String?): Set<String> = values
    .asSequence()
    .mapNotNull { value ->
        value
            ?.trim()
            ?.lowercase()
            ?.filter { it.isLetterOrDigit() }
            ?.takeIf { it.isNotBlank() }
    }
    .toSet()

internal fun guideProgramForAction(
    channelId: String,
    guideIdentityKeys: Set<String>,
    guideByChannelId: Map<String, IptvNowNext>,
    guideIdentityKeysByChannelId: Map<String, Set<String>>,
): IptvProgram? {
    guideByChannelId[channelId]?.now?.let { return it }
    if (guideIdentityKeys.isEmpty()) return null
    return guideIdentityKeysByChannelId.entries.firstNotNullOfOrNull { (candidateId, candidateKeys) ->
        if (candidateId != channelId && candidateKeys.any(guideIdentityKeys::contains)) {
            guideByChannelId[candidateId]?.now
        } else {
            null
        }
    }
}

internal fun epgChannelAllowsVodSearch(
    channelName: String,
    channelGroup: String,
): Boolean {
    // Playlist group labels are often broad combinations such as
    // "News & Entertainment". A broad mixed label must not reject a movie
    // channel, while a dedicated Sports/News group should still fail closed.
    // Exact title matching remains the final guard against false positives.
    val channelTokens = channelName
        .lowercase()
        .split(Regex("[^a-z0-9]+"))
        .filterTo(mutableSetOf()) { it.isNotBlank() }
    if (channelTokens.any { it in NON_VOD_EPG_CHANNEL_TERMS }) return false

    val groupTokens = channelGroup
        .lowercase()
        .split(Regex("[^a-z0-9]+"))
        .filterTo(mutableSetOf()) { it.isNotBlank() }
    val dedicatedNonVodGroup =
        groupTokens.any { it in NON_VOD_EPG_CHANNEL_TERMS } &&
            groupTokens.none { it in MIXED_ENTERTAINMENT_GROUP_TERMS }
    return !dedicatedNonVodGroup
}

private val EPG_TITLE_YEAR_SUFFIX = Regex("""\s*\(?\b(?:19|20)\d{2}\b\)?\s*$""")
private val EPG_YEAR_HINT = Regex("""\b(?:19|20)\d{2}\b""")
private val EPG_TITLE_NON_ALPHANUMERIC = Regex("""[^a-z0-9]+""")

private fun normalizedEpgVodTitle(title: String): String = title
    .trim()
    .replaceLiteral(EPG_TITLE_YEAR_SUFFIX, "")
    .lowercase()
    .replace("&", "and")
    .replaceLiteral(EPG_TITLE_NON_ALPHANUMERIC, "")

internal fun selectConfidentEpgVodMatch(
    programTitle: String,
    results: List<MediaItem>,
    programDescription: String? = null,
): MediaItem? {
    val expectedTitle = normalizedEpgVodTitle(programTitle)
    if (expectedTitle.length < 2) return null
    val exactMatches = results.filter { candidate ->
        candidate.mediaType in setOf(MediaType.MOVIE, MediaType.TV) &&
            normalizedEpgVodTitle(candidate.title) == expectedTitle
    }
    val yearHint = EPG_YEAR_HINT.find("$programTitle ${programDescription.orEmpty()}")?.value
    return if (yearHint != null) {
        exactMatches.singleOrNull { it.year == yearHint }
    } else {
        exactMatches.singleOrNull()
    }
}
