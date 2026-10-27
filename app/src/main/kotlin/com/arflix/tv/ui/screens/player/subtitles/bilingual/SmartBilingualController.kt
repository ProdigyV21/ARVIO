package com.arflix.tv.ui.screens.player.subtitles.bilingual

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.PlayerUiState
import com.arflix.tv.ui.screens.player.PlayerViewModel
import kotlinx.coroutines.delay

/**
 * Drives the smart bilingual mode for the playing video: builds the candidate list (the player's
 * subtitle entries, tied to the tracks the extractor tap can read), asks [SmartBilingualPlanner]
 * what to do and applies it through the view model.
 *
 * Respects the user: an explicit subtitle pick or "Off" from the menu suspends the smart mode for
 * this video until the user asks for it again ("Smart Bilingual" in the subtitle menu).
 * Re-plans on a language/setting change, when tracks appear, and after a path-A track turned out
 * not to line up (then path B or primary-only, with a notice).
 */
@Composable
fun SmartBilingualController(
    viewModel: PlayerViewModel,
    uiState: PlayerUiState,
    store: EmbeddedSubtitleStore,
    audioLanguage: () -> String?,
) {
    val context = LocalContext.current
    val settingsStore = remember { BilingualSubtitleSettingsStore.from(context) }
    val settings by settingsStore.settings.collectAsState()
    val manager = viewModel.translationManager
    val stream = uiState.selectedStreamUrl
    val latest by rememberUpdatedState(uiState)

    // Per-video state.
    var pickBaseline by remember(stream) { mutableIntStateOf(uiState.subtitleUserPickNonce) }
    var requestedForStream by remember(stream) { mutableStateOf(false) }
    var pinUserSelection by remember(stream) { mutableStateOf(false) }
    var unreliable by remember(stream) { mutableStateOf(emptySet<String>()) }
    var lastNoticeKey by remember(stream) { mutableStateOf<String?>(null) }
    var activeSecondaryId by remember(stream) { mutableStateOf<String?>(null) }
    var appliedPlanKey by remember(stream) { mutableStateOf<String?>(null) }
    val primaryOverride by BilingualPlayerBridge.primaryOverride.collectAsState()
    val secondaryOverride by BilingualPlayerBridge.secondaryOverride.collectAsState()
    // Manual track choices belong to one video.
    LaunchedEffect(stream) {
        BilingualPlayerBridge.primaryOverride.value = null
        BilingualPlayerBridge.secondaryOverride.value = null
        BilingualPlayerBridge.status.value = null
    }
    DisposableEffect(Unit) {
        onDispose {
            BilingualPlayerBridge.tracks.value = emptyList()
            BilingualPlayerBridge.status.value = null
        }
    }

    // Menu request → resume (keeping a subtitle the user picked as the primary).
    var seenRequest by remember { mutableIntStateOf(uiState.smartBilingualRequestNonce) }
    LaunchedEffect(uiState.smartBilingualRequestNonce) {
        if (uiState.smartBilingualRequestNonce != seenRequest) {
            seenRequest = uiState.smartBilingualRequestNonce
            pinUserSelection = uiState.subtitleUserPickNonce != pickBaseline && uiState.selectedSubtitle != null
            pickBaseline = uiState.subtitleUserPickNonce
            requestedForStream = true
            unreliable = emptySet()
            lastNoticeKey = null
        }
    }
    LaunchedEffect(Unit) {
        BilingualPlayerBridge.requests.collect { viewModel.requestSmartBilingual() }
    }
    // A subtitle the user picks while bilingual is on becomes the new primary (re-planned below);
    // only turning subtitles Off from the menu pauses the bilingual layer for this video.
    val suspendedByUser = uiState.subtitleUserPickNonce != pickBaseline && uiState.selectedSubtitle == null
    val active = (settings.smartEnabled || requestedForStream) && !suspendedByUser

    // Track list of the tap changes only a few times per file; poll its version cheaply.
    val tapSignature by produceState("", stream) {
        while (true) {
            value = store.tracks().joinToString("|") { "${it.trackId}:${it.languageTag}:${it.label}:${store.cueCount(it.trackId) > 0}" }
            delay(1_000)
        }
    }

    // Path A low-confidence → fall back (after a few consecutive verdicts, not one odd window).
    DisposableEffect(manager) {
        var strikes = 0
        manager.onSecondaryLookup = { lookup ->
            if (lookup.status == DualTrackSecondarySource.Status.UNRELIABLE) {
                strikes++
                if (strikes >= 3) {
                    strikes = 0
                    val id = activeSecondaryId
                    if (id != null) {
                        android.os.Handler(android.os.Looper.getMainLooper()).post {
                            if (activeSecondaryId == id) unreliable = unreliable + id
                        }
                    }
                }
            } else if (lookup.status == DualTrackSecondarySource.Status.ALIGNED) {
                strikes = 0
            }
        }
        onDispose { manager.onSecondaryLookup = null }
    }

    LaunchedEffect(
        active, settings.generationMode, settings.primaryLanguage, settings.secondaryLanguage,
        uiState.subtitles, tapSignature, unreliable, stream, primaryOverride, secondaryOverride,
        uiState.selectedSubtitle?.id,
    ) {
        if (!active) {
            appliedPlanKey = null
            BilingualPlayerBridge.status.value = null
            if (latest.smartBilingualActive) viewModel.leaveSmartBilingual()
            return@LaunchedEffect
        }
        if (latest.subtitles.isEmpty()) return@LaunchedEffect
        delay(500) // let track lists settle (embedded tracks and addon lists arrive in bursts)
        val state = latest
        val candidates = buildCandidates(state.subtitles, store)
        BilingualPlayerBridge.tracks.value = candidates.filter { it.isText }.map { c ->
            BilingualPlayerBridge.TrackChoice(
                id = c.id,
                label = listOfNotNull(c.language?.localName, c.label?.takeIf { it.isNotBlank() && it != c.language?.localName })
                    .joinToString(" · ").ifBlank { c.id } + if (c.isEmbedded) "" else bt(" (addon)"),
                mergeable = c.isEmbedded && c.tapTrackId != null,
            )
        }
        val plan = SmartBilingualPlanner.plan(
            SmartBilingualPlanner.Input(
                candidates = candidates,
                primaryPreference = settings.primaryLanguage,
                secondaryLanguage = settings.secondary,
                audioLanguageTag = audioLanguage(),
                selectedId = state.selectedSubtitle?.id,
                userPinnedSelection = pinUserSelection,
                aiConfigured = viewModel.isAiTranslationConfigured(),
                unreliableSecondaryIds = unreliable,
                mode = settings.generationMode,
                primaryOverrideId = primaryOverride,
                secondaryOverrideId = secondaryOverride,
            )
        )
        // Same plan as the one already on screen → nothing to do (no re-selection, no flicker,
        // no re-translation).
        val planKey = when (plan) {
            is SmartPlan.MergeEmbedded -> "A:${plan.primary.id}:${plan.secondary.id}"
            is SmartPlan.AiTranslate -> "B:${plan.primary.id}:${plan.target.tag}"
            is SmartPlan.PrimaryOnly -> "P:${plan.primary.id}:${plan.why}"
            is SmartPlan.Unavailable -> "U:${plan.why}"
        }
        if (planKey == appliedPlanKey && latest.smartBilingualActive &&
            (plan !is SmartPlan.Unavailable) && latest.selectedSubtitle?.id == when (plan) {
                is SmartPlan.MergeEmbedded -> plan.primary.id
                is SmartPlan.AiTranslate -> plan.primary.id
                is SmartPlan.PrimaryOnly -> plan.primary.id
                else -> null
            }
        ) return@LaunchedEffect
        appliedPlanKey = planKey
        BilingualPlayerBridge.status.value = describe(plan, settings.generationMode)
        val byId = state.subtitles.associateBy { it.id }
        fun notice(kind: BilingualNotice.Kind, detail: String, key: String) {
            if (lastNoticeKey != key) {
                lastNoticeKey = key
                manager.bilingualNotice.value = BilingualNotice(kind, detail)
            }
        }
        when (plan) {
            is SmartPlan.MergeEmbedded -> {
                val primary = byId[plan.primary.id] ?: return@LaunchedEffect
                val secondaryTrack = plan.secondary.tapTrackId ?: return@LaunchedEffect
                val current = manager.secondarySource
                val source = if (current != null && current.secondaryTrackId == secondaryTrack &&
                    current.primaryTrackId == plan.primary.tapTrackId) current
                else DualTrackSecondarySource(store, plan.primary.tapTrackId, secondaryTrack)
                activeSecondaryId = plan.secondary.id
                viewModel.activateSmartBilingualMerge(primary)
                manager.secondarySource = source
                val names = "${plan.primaryLanguage?.localName ?: primary.label} + ${plan.secondaryLanguage.localName}"
                notice(
                    if (plan.ambiguousScript) BilingualNotice.Kind.MERGING_AMBIGUOUS_SCRIPT else BilingualNotice.Kind.MERGING_EMBEDDED,
                    if (plan.ambiguousScript) "${plan.secondary.label ?: plan.secondary.languageTag}" else names,
                    "merge:${plan.primary.id}:${plan.secondary.id}",
                )
            }
            is SmartPlan.AiTranslate -> {
                val primary = byId[plan.primary.id] ?: return@LaunchedEffect
                activeSecondaryId = null
                viewModel.activateSmartBilingualAi(primary, plan.target.translationTargetName)
                notice(plan.why, plan.target.localName, "ai:${plan.primary.id}:${plan.target.tag}:${plan.why}")
            }
            is SmartPlan.PrimaryOnly -> {
                val primary = byId[plan.primary.id] ?: return@LaunchedEffect
                activeSecondaryId = null
                viewModel.activateSmartBilingualPrimaryOnly(primary)
                val detail = if (plan.why == BilingualNotice.Kind.SAME_LANGUAGE) {
                    plan.primary.language?.localName ?: settings.secondary.localName
                } else settings.secondary.localName
                notice(plan.why, detail, "primary:${plan.primary.id}:${plan.why}:${settings.secondaryLanguage}")
            }
            is SmartPlan.Unavailable -> {
                activeSecondaryId = null
                notice(plan.why, "", "none:${plan.why}")
            }
        }
    }
}

/**
 * The player's subtitle entries as planner candidates. An embedded entry is tied to its tap track
 * by Format.id (the player id may carry a "periodIndex:" prefix), else by language + order.
 */
internal fun buildCandidates(subtitles: List<Subtitle>, store: EmbeddedSubtitleStore): List<SubtitleCandidate> {
    val tapTracks = store.tracks().filter { !it.isBitmap }
    val usedTap = mutableSetOf<Int>()
    fun tapFor(sub: Subtitle): EmbeddedTextTrack? {
        if (!sub.isEmbedded) return null
        val bareId = sub.id.substringAfterLast(':')
        tapTracks.firstOrNull { it.trackId !in usedTap && (it.formatId == sub.id || it.formatId == bareId) }
            ?.let { usedTap += it.trackId; return it }
        val lang = SubtitleLanguage.ofTrack(sub.lang, sub.label)?.tag ?: return null
        return tapTracks.firstOrNull { it.trackId !in usedTap && it.language?.tag == lang && (it.label == null || it.label == sub.label) }
            ?.also { usedTap += it.trackId }
    }
    return subtitles.map { sub ->
        val tap = tapFor(sub)
        SubtitleCandidate(
            id = sub.id,
            languageTag = sub.lang.takeUnless { it.isBlank() || it == "und" } ?: tap?.languageTag,
            label = sub.label.ifBlank { tap?.label.orEmpty() },
            isEmbedded = sub.isEmbedded,
            isBitmap = sub.isBitmap || tap?.isBitmap == true,
            isForced = sub.isForced,
            tapTrackId = tap?.trackId,
        )
    }
}

/** One line for the panel: what the bilingual layer is doing now. */
internal fun describe(plan: SmartPlan, mode: BilingualGenerationMode): String {
    val m = when (mode) {
        BilingualGenerationMode.AUTO -> bt("Auto")
        BilingualGenerationMode.MERGE -> bt("Merge")
        BilingualGenerationMode.AI_TRANSLATE -> bt("AI Translate")
    }
    fun name(c: SubtitleCandidate) = c.language?.localName ?: c.label ?: c.id
    return when (plan) {
        is SmartPlan.MergeEmbedded -> bt("%s · merging %s + %s (no AI)").format(m, name(plan.primary), name(plan.secondary))
        is SmartPlan.AiTranslate -> bt("%s · %s + AI %s").format(m, name(plan.primary), plan.target.localName)
        is SmartPlan.PrimaryOnly -> bt("%s · %s only — %s").format(m, name(plan.primary), BilingualNotice(plan.why).message())
        is SmartPlan.Unavailable -> "$m · ${BilingualNotice(plan.why).message()}"
    }
}
