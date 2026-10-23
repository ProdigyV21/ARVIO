package com.arflix.tv.ui.screens.player.subtitles.bilingual

import java.util.concurrent.atomic.AtomicLong

/** A subtitle the smart mode may use: one of the player's subtitle entries. */
data class SubtitleCandidate(
    /** The player's subtitle id (what gets selected). */
    val id: String,
    val languageTag: String?,
    val label: String?,
    val isEmbedded: Boolean,
    val isBitmap: Boolean,
    val isForced: Boolean,
    /** Its track inside [EmbeddedSubtitleStore] when the tap has seen it (null = text not readable off-track). */
    val tapTrackId: Int? = null,
    val isDefault: Boolean = false,
) {
    val language: SubtitleLanguage? get() = SubtitleLanguage.ofTrack(languageTag, label)
    val isText: Boolean get() = !isBitmap
    val looksSdh: Boolean get() = label?.let { Regex("(^|\\W)(sdh|cc|hi)(\\W|$)", RegexOption.IGNORE_CASE).containsMatchIn(it) } == true
}

/** What the smart bilingual mode should do for the current video. */
sealed class SmartPlan {
    /** Path A: both languages exist as embedded text tracks — merge them, no AI. */
    data class MergeEmbedded(
        val primary: SubtitleCandidate,
        val secondary: SubtitleCandidate,
        val primaryLanguage: SubtitleLanguage?,
        val secondaryLanguage: SubtitleLanguage,
        /** The secondary track only says "Chinese" (script unknown) and was accepted as the best available. */
        val ambiguousScript: Boolean,
    ) : SmartPlan()

    /** Path B: translate the primary text track with Gemini/Groq. */
    data class AiTranslate(
        val primary: SubtitleCandidate,
        val primaryLanguage: SubtitleLanguage?,
        val target: SubtitleLanguage,
        val why: BilingualNotice.Kind,
    ) : SmartPlan()

    /** Only the primary can be shown (no second language available without AI, or same language). */
    data class PrimaryOnly(val primary: SubtitleCandidate, val why: BilingualNotice.Kind) : SmartPlan()

    /** No readable text subtitle at all. */
    data class Unavailable(val why: BilingualNotice.Kind) : SmartPlan()
}

/** A short status line for the user about what the smart mode is doing and why. */
data class BilingualNotice(val kind: Kind, val detail: String = "", val id: Long = ids.incrementAndGet()) {
    enum class Kind {
        MERGING_EMBEDDED,
        MERGING_AMBIGUOUS_SCRIPT,
        AI_NO_SECONDARY_TRACK,
        AI_SECONDARY_UNRELIABLE,
        AI_SECONDARY_IMAGE_ONLY,
        PRIMARY_ONLY_NO_AI,
        PRIMARY_ONLY_UNRELIABLE_NO_AI,
        SAME_LANGUAGE,
        NO_TEXT_SUBTITLE,
        USER_PICK_RESPECTED,
        MERGE_NO_SECONDARY,
        MERGE_UNRELIABLE,
        AI_FORCED,
        AI_NOT_CONFIGURED,
    }

    /** True for notices that tell the user something is missing or degraded. */
    val isWarning: Boolean
        get() = kind != Kind.MERGING_EMBEDDED && kind != Kind.AI_NO_SECONDARY_TRACK && kind != Kind.AI_FORCED

    fun message(): String = when (kind) {
        Kind.MERGING_EMBEDDED -> bt("Bilingual: merging embedded %s subtitles (no AI)").format(detail)
        Kind.MERGING_AMBIGUOUS_SCRIPT -> bt("Bilingual: merging %s — the track doesn't say Simplified or Traditional").format(detail)
        Kind.AI_NO_SECONDARY_TRACK -> bt("Bilingual: no embedded %s track — translating with AI").format(detail)
        Kind.AI_SECONDARY_UNRELIABLE -> bt("Bilingual: embedded %s track doesn't line up — switched to AI translation").format(detail)
        Kind.AI_SECONDARY_IMAGE_ONLY -> bt("Bilingual: embedded %s track is an image subtitle — translating with AI").format(detail)
        Kind.PRIMARY_ONLY_NO_AI -> bt("Bilingual: no embedded %s track and no AI key — showing one language. Add a Gemini/Groq key to translate.").format(detail)
        Kind.PRIMARY_ONLY_UNRELIABLE_NO_AI -> bt("Bilingual: embedded %s track doesn't line up and no AI key is set — showing one language").format(detail)
        Kind.SAME_LANGUAGE -> bt("Bilingual: primary and secondary are both %s — choose a different language in Bilingual settings").format(detail)
        Kind.NO_TEXT_SUBTITLE -> bt("Bilingual: this video has no readable text subtitle (image subtitles can't be used)")
        Kind.USER_PICK_RESPECTED -> bt("Bilingual: keeping the subtitle you picked")
        Kind.MERGE_NO_SECONDARY -> bt("Merge mode: no embedded %s text track to merge (AI is off in this mode). Pick the tracks in Subtitles → Bilingual Subtitles.").format(detail)
        Kind.MERGE_UNRELIABLE -> bt("Merge mode: the %s track doesn't line up with the primary (AI is off in this mode). Pick other tracks in Subtitles → Bilingual Subtitles.").format(detail)
        Kind.AI_FORCED -> bt("AI Translate mode: translating into %s").format(detail)
        Kind.AI_NOT_CONFIGURED -> bt("AI Translate mode needs a Gemini/Groq key (Settings → AI Subtitles) — showing one language")
    }

    private companion object {
        val ids = AtomicLong(0)
    }
}

/**
 * Pure decision of the smart bilingual mode (no Android, no I/O), in the required priority:
 * two embedded text tracks → merge (A) → otherwise AI-translate the primary (B) → otherwise say
 * clearly why nothing better is possible.
 */
object SmartBilingualPlanner {

    data class Input(
        val candidates: List<SubtitleCandidate>,
        /** "auto" or a BCP 47 tag. */
        val primaryPreference: String,
        val secondaryLanguage: SubtitleLanguage,
        val audioLanguageTag: String?,
        /** The subtitle currently selected in the player. */
        val selectedId: String?,
        /** The user explicitly picked [selectedId] from the subtitle menu: it must stay the primary. */
        val userPinnedSelection: Boolean,
        val aiConfigured: Boolean,
        /** Secondary tracks already found not to line up with this video's primary. */
        val unreliableSecondaryIds: Set<String> = emptySet(),
        /** Auto / Merge / AI Translate. */
        val mode: BilingualGenerationMode = BilingualGenerationMode.AUTO,
        /** Track the user chose as primary in the bilingual panel (wins over everything). */
        val primaryOverrideId: String? = null,
        /** Track the user chose as secondary in the bilingual panel (used whatever its language). */
        val secondaryOverrideId: String? = null,
    )

    fun plan(input: Input): SmartPlan {
        val text = input.candidates.filter { it.isText }
        val secondaryLang = input.secondaryLanguage
        val primary = resolvePrimary(input, text) ?: return SmartPlan.Unavailable(BilingualNotice.Kind.NO_TEXT_SUBTITLE)
        val primaryLang = primary.language

        if (SubtitleLanguageMatcher.sameLanguage(primaryLang, secondaryLang) &&
            SubtitleLanguageMatcher.match(primaryLang, secondaryLang) != LanguageMatch.NONE) {
            return SmartPlan.PrimaryOnly(primary, BilingualNotice.Kind.SAME_LANGUAGE)
        }

        // AI Translate mode: always translate the chosen primary, whatever tracks exist.
        if (input.mode == BilingualGenerationMode.AI_TRANSLATE) {
            if (SubtitleLanguageMatcher.sameLanguage(primaryLang, secondaryLang) &&
                SubtitleLanguageMatcher.match(primaryLang, secondaryLang) != LanguageMatch.NONE) {
                return SmartPlan.PrimaryOnly(primary, BilingualNotice.Kind.SAME_LANGUAGE)
            }
            return if (input.aiConfigured) SmartPlan.AiTranslate(primary, primaryLang, secondaryLang, BilingualNotice.Kind.AI_FORCED)
            else SmartPlan.PrimaryOnly(primary, BilingualNotice.Kind.AI_NOT_CONFIGURED)
        }

        // A secondary track picked by hand is used as long as it is readable text.
        text.firstOrNull { it.id == input.secondaryOverrideId && it.id != primary.id && it.tapTrackId != null }?.let { manual ->
            if (manual.id !in input.unreliableSecondaryIds) {
                return SmartPlan.MergeEmbedded(primary, manual, primaryLang, manual.language ?: secondaryLang, ambiguousScript = false)
            }
        }

        val pool = text.filter { it.isEmbedded && it.id != primary.id && !it.isForced }
        val exact = pool.filter { SubtitleLanguageMatcher.match(it.language, secondaryLang) == LanguageMatch.EXACT }
        val ambiguous = pool.filter { SubtitleLanguageMatcher.match(it.language, secondaryLang) == LanguageMatch.AMBIGUOUS_SCRIPT }
        val readable = { c: SubtitleCandidate -> c.tapTrackId != null && c.id !in input.unreliableSecondaryIds }
        val secondary = rank(exact.filter(readable)).firstOrNull()
            ?: rank(ambiguous.filter(readable)).firstOrNull()

        if (secondary != null) {
            return SmartPlan.MergeEmbedded(
                primary = primary,
                secondary = secondary,
                primaryLanguage = primaryLang,
                secondaryLanguage = secondaryLang,
                ambiguousScript = SubtitleLanguageMatcher.match(secondary.language, secondaryLang) == LanguageMatch.AMBIGUOUS_SCRIPT,
            )
        }

        val hadUnreliable = (exact + ambiguous).any { it.id in input.unreliableSecondaryIds }
        val imageOnly = input.candidates.any {
            it.isBitmap && it.isEmbedded && SubtitleLanguageMatcher.match(it.language, secondaryLang) != LanguageMatch.NONE
        }
        if (input.mode == BilingualGenerationMode.MERGE) {
            return SmartPlan.PrimaryOnly(
                primary,
                if (hadUnreliable || input.secondaryOverrideId in input.unreliableSecondaryIds) BilingualNotice.Kind.MERGE_UNRELIABLE
                else BilingualNotice.Kind.MERGE_NO_SECONDARY,
            )
        }
        if (input.aiConfigured) {
            val why = when {
                hadUnreliable -> BilingualNotice.Kind.AI_SECONDARY_UNRELIABLE
                imageOnly && exact.isEmpty() && ambiguous.isEmpty() -> BilingualNotice.Kind.AI_SECONDARY_IMAGE_ONLY
                else -> BilingualNotice.Kind.AI_NO_SECONDARY_TRACK
            }
            return SmartPlan.AiTranslate(primary, primaryLang, secondaryLang, why)
        }
        return SmartPlan.PrimaryOnly(
            primary,
            if (hadUnreliable) BilingualNotice.Kind.PRIMARY_ONLY_UNRELIABLE_NO_AI else BilingualNotice.Kind.PRIMARY_ONLY_NO_AI,
        )
    }

    /**
     * Primary track. Turning bilingual/translation on must NEVER change the subtitle the viewer
     * is watching: whatever text track is selected right now (embedded or addon, picked by the
     * user or by ARVIO) stays the primary, and only the second line is added. (Switching tracks
     * here used to swap a correct subtitle for another one — sometimes an addon result matched to
     * a different episode.) Only when nothing usable is selected does the planner choose one,
     * and then it only takes the file's own embedded tracks first (always in sync with this
     * video): an explicit Primary language, then the audio language, then English, then any.
     */
    internal fun resolvePrimary(input: Input, text: List<SubtitleCandidate>): SubtitleCandidate? {
        text.firstOrNull { it.id == input.primaryOverrideId }?.let { return it }
        val selected = text.firstOrNull { it.id == input.selectedId }
        if (selected != null) return selected

        val notSecondary = text.filter {
            SubtitleLanguageMatcher.match(it.language, input.secondaryLanguage) == LanguageMatch.NONE
        }
        val explicit = SubtitleLanguage.parse(input.primaryPreference)
        val audio = SubtitleLanguage.parse(input.audioLanguageTag)
        // Embedded tracks first, addon results only when the file has no usable text track.
        val pools = listOf(text.filter { it.isEmbedded }, text.filter { !it.isEmbedded })
        for (all in pools) {
            if (all.isEmpty()) continue
            if (explicit != null) {
                rank(all.filter { !it.isForced && SubtitleLanguageMatcher.match(it.language, explicit) == LanguageMatch.EXACT })
                    .firstOrNull()?.let { return it }
                rank(all.filter { !it.isForced && SubtitleLanguageMatcher.match(it.language, explicit) == LanguageMatch.AMBIGUOUS_SCRIPT })
                    .firstOrNull()?.let { return it }
            }
            val pool = all.filter { it in notSecondary }
            if (pool.isEmpty()) continue
            if (audio != null) {
                rank(pool.filter { !it.isForced && SubtitleLanguageMatcher.match(it.language, audio) != LanguageMatch.NONE })
                    .firstOrNull()?.let { return it }
            }
            rank(pool.filter { !it.isForced && it.language?.base == "en" }).firstOrNull()?.let { return it }
            rank(pool.filter { !it.isForced }).firstOrNull()?.let { return it }
        }
        return rank(notSecondary).firstOrNull()
    }

    /** Embedded first, readable off-track, plain over SDH, default flag, then original order. */
    private fun rank(list: List<SubtitleCandidate>): List<SubtitleCandidate> =
        list.withIndex().sortedWith(
            compareByDescending<IndexedValue<SubtitleCandidate>> { it.value.isEmbedded }
                .thenByDescending { it.value.tapTrackId != null }
                .thenBy { it.value.looksSdh }
                .thenByDescending { it.value.isDefault }
                .thenBy { it.index }
        ).map { it.value }
}
