package com.arflix.tv.ui.screens.player.subtitles.bilingual

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SmartBilingualPlannerTest {

    private val en = SubtitleCandidate("3", "eng", "English", isEmbedded = true, isBitmap = false, isForced = false, tapTrackId = 3)
    private val enSdh = SubtitleCandidate("4", "eng", "English SDH", isEmbedded = true, isBitmap = false, isForced = false, tapTrackId = 4)
    private val zhHans = SubtitleCandidate("5", "chi", "简体", isEmbedded = true, isBitmap = false, isForced = false, tapTrackId = 5)
    private val zhHant = SubtitleCandidate("6", "zh-TW", "繁體", isEmbedded = true, isBitmap = false, isForced = false, tapTrackId = 6)
    private val zhPlain = SubtitleCandidate("7", "chi", "Chinese", isEmbedded = true, isBitmap = false, isForced = false, tapTrackId = 7)
    private val zhPgs = SubtitleCandidate("8", "chi", "简体 PGS", isEmbedded = true, isBitmap = true, isForced = false, tapTrackId = null)
    private val ja = SubtitleCandidate("9", "jpn", "日本語", isEmbedded = true, isBitmap = false, isForced = false, tapTrackId = 9)
    private val enForced = SubtitleCandidate("10", "eng", "English Forced", isEmbedded = true, isBitmap = false, isForced = true, tapTrackId = 10)
    private val addonZh = SubtitleCandidate("addon-1", "zh-CN", "zh addon", isEmbedded = false, isBitmap = false, isForced = false)

    private fun input(
        vararg c: SubtitleCandidate,
        primary: String = "auto",
        secondary: SubtitleLanguage = SubtitleLanguage.SIMPLIFIED_CHINESE,
        audio: String? = "en",
        selected: String? = null,
        pinned: Boolean = false,
        ai: Boolean = true,
        unreliable: Set<String> = emptySet(),
        mode: BilingualGenerationMode = BilingualGenerationMode.AUTO,
        primaryOverride: String? = null,
        secondaryOverride: String? = null,
    ) = SmartBilingualPlanner.Input(c.toList(), primary, secondary, audio, selected, pinned, ai, unreliable, mode, primaryOverride, secondaryOverride)

    @Test fun `English and Simplified Chinese embedded - merge without AI`() {
        val plan = SmartBilingualPlanner.plan(input(en, zhHans, ai = true))
        plan as SmartPlan.MergeEmbedded
        assertEquals("3", plan.primary.id)
        assertEquals("5", plan.secondary.id)
        assertEquals(false, plan.ambiguousScript)
    }

    @Test fun `both languages present still merge when no AI key is configured`() {
        assertTrue(SmartBilingualPlanner.plan(input(en, zhHans, ai = false)) is SmartPlan.MergeEmbedded)
    }

    @Test fun `only English - AI translation into the secondary language`() {
        val plan = SmartBilingualPlanner.plan(input(en, ai = true))
        plan as SmartPlan.AiTranslate
        assertEquals("3", plan.primary.id)
        assertEquals("zh-Hans", plan.target.tag)
        assertEquals(BilingualNotice.Kind.AI_NO_SECONDARY_TRACK, plan.why)
    }

    @Test fun `only English and no AI - primary only with a clear reason`() {
        val plan = SmartBilingualPlanner.plan(input(en, ai = false))
        plan as SmartPlan.PrimaryOnly
        assertEquals(BilingualNotice.Kind.PRIMARY_ONLY_NO_AI, plan.why)
    }

    @Test fun `Traditional track is not used for Simplified - AI fills in instead`() {
        val plan = SmartBilingualPlanner.plan(input(en, zhHant))
        assertTrue(plan is SmartPlan.AiTranslate)
        // ...but it is exactly right when the user asks for Traditional.
        val t = SmartBilingualPlanner.plan(input(en, zhHant, secondary = SubtitleLanguage.TRADITIONAL_CHINESE))
        assertEquals("6", (t as SmartPlan.MergeEmbedded).secondary.id)
    }

    @Test fun `exact script beats a track that only says Chinese, which is a flagged last resort`() {
        val plan = SmartBilingualPlanner.plan(input(en, zhPlain, zhHans)) as SmartPlan.MergeEmbedded
        assertEquals("5", plan.secondary.id)
        val amb = SmartBilingualPlanner.plan(input(en, zhPlain)) as SmartPlan.MergeEmbedded
        assertEquals("7", amb.secondary.id)
        assertTrue(amb.ambiguousScript)
    }

    @Test fun `image subtitle in the secondary language cannot be merged`() {
        val plan = SmartBilingualPlanner.plan(input(en, zhPgs)) as SmartPlan.AiTranslate
        assertEquals(BilingualNotice.Kind.AI_SECONDARY_IMAGE_ONLY, plan.why)
    }

    @Test fun `unreliable secondary falls back to AI, or primary only without AI`() {
        val ai = SmartBilingualPlanner.plan(input(en, zhHans, unreliable = setOf("5"))) as SmartPlan.AiTranslate
        assertEquals(BilingualNotice.Kind.AI_SECONDARY_UNRELIABLE, ai.why)
        val noAi = SmartBilingualPlanner.plan(input(en, zhHans, ai = false, unreliable = setOf("5"))) as SmartPlan.PrimaryOnly
        assertEquals(BilingualNotice.Kind.PRIMARY_ONLY_UNRELIABLE_NO_AI, noAi.why)
    }

    @Test fun `auto primary prefers plain over SDH and never a forced track`() {
        val plan = SmartBilingualPlanner.plan(input(enForced, enSdh, en, zhHans)) as SmartPlan.MergeEmbedded
        assertEquals("3", plan.primary.id)
    }

    @Test fun `auto primary follows the audio language`() {
        val plan = SmartBilingualPlanner.plan(input(en, ja, zhHans, audio = "ja")) as SmartPlan.MergeEmbedded
        assertEquals("9", plan.primary.id)
    }

    @Test fun `explicit languages - Japanese plus Simplified, English plus Japanese`() {
        val p1 = SmartBilingualPlanner.plan(input(en, ja, zhHans, primary = "ja")) as SmartPlan.MergeEmbedded
        assertEquals("9" to "5", p1.primary.id to p1.secondary.id)
        val p2 = SmartBilingualPlanner.plan(input(en, ja, zhHans, primary = "en", secondary = SubtitleLanguage.parse("ja")!!)) as SmartPlan.MergeEmbedded
        assertEquals("3" to "9", p2.primary.id to p2.secondary.id)
    }

    @Test fun `same primary and secondary language is refused with a notice`() {
        val plan = SmartBilingualPlanner.plan(input(en, zhHans, primary = "zh-Hans"))
        plan as SmartPlan.PrimaryOnly
        assertEquals(BilingualNotice.Kind.SAME_LANGUAGE, plan.why)
    }

    @Test fun `a subtitle the user picked stays the primary`() {
        val plan = SmartBilingualPlanner.plan(input(en, enSdh, zhHans, selected = "4", pinned = true)) as SmartPlan.MergeEmbedded
        assertEquals("4", plan.primary.id)
    }

    @Test fun `addon subtitles are not used as the merged secondary`() {
        assertTrue(SmartBilingualPlanner.plan(input(en, addonZh)) is SmartPlan.AiTranslate)
    }

    @Test fun `no text subtitle at all`() {
        val plan = SmartBilingualPlanner.plan(input(zhPgs, ai = true))
        assertEquals(BilingualNotice.Kind.NO_TEXT_SUBTITLE, (plan as SmartPlan.Unavailable).why)
    }

    // ── Generation modes ─────────────────────────────────────────────────────

    @Test fun `Auto is the default mode`() {
        assertEquals(BilingualGenerationMode.AUTO, BilingualSubtitleSettings().generationMode)
        assertEquals(BilingualGenerationMode.AUTO, SmartBilingualPlanner.Input(emptyList(), "auto", SubtitleLanguage.SIMPLIFIED_CHINESE, null, null, false, true).mode)
    }

    @Test fun `Merge mode merges when both tracks exist`() {
        val plan = SmartBilingualPlanner.plan(input(en, zhHans, mode = BilingualGenerationMode.MERGE))
        assertEquals("5", (plan as SmartPlan.MergeEmbedded).secondary.id)
    }

    @Test fun `Merge mode never uses AI - missing language gives a notice even with a key`() {
        val plan = SmartBilingualPlanner.plan(input(en, ai = true, mode = BilingualGenerationMode.MERGE))
        assertEquals(BilingualNotice.Kind.MERGE_NO_SECONDARY, (plan as SmartPlan.PrimaryOnly).why)
        val unreliablePlan = SmartBilingualPlanner.plan(input(en, zhHans, ai = true, unreliable = setOf("5"), mode = BilingualGenerationMode.MERGE))
        assertEquals(BilingualNotice.Kind.MERGE_UNRELIABLE, (unreliablePlan as SmartPlan.PrimaryOnly).why)
        // Image-only secondary: still no AI in Merge mode.
        assertTrue(SmartBilingualPlanner.plan(input(en, zhPgs, mode = BilingualGenerationMode.MERGE)) is SmartPlan.PrimaryOnly)
    }

    @Test fun `Merge mode lets the user pick both tracks by hand`() {
        // e.g. a track labelled only "Chinese" plus a non-default English one.
        val plan = SmartBilingualPlanner.plan(
            input(en, enSdh, zhPlain, zhHant, mode = BilingualGenerationMode.MERGE, primaryOverride = "4", secondaryOverride = "6")
        ) as SmartPlan.MergeEmbedded
        assertEquals("4" to "6", plan.primary.id to plan.secondary.id)
    }

    @Test fun `AI Translate mode translates even when the target track exists`() {
        val plan = SmartBilingualPlanner.plan(input(en, zhHans, mode = BilingualGenerationMode.AI_TRANSLATE))
        plan as SmartPlan.AiTranslate
        assertEquals("3", plan.primary.id)
        assertEquals(BilingualNotice.Kind.AI_FORCED, plan.why)
    }

    @Test fun `AI Translate mode uses the user's source language or track`() {
        val byLang = SmartBilingualPlanner.plan(input(en, ja, zhHans, primary = "ja", mode = BilingualGenerationMode.AI_TRANSLATE)) as SmartPlan.AiTranslate
        assertEquals("9", byLang.primary.id)
        val byTrack = SmartBilingualPlanner.plan(input(en, enSdh, zhHans, primaryOverride = "4", mode = BilingualGenerationMode.AI_TRANSLATE)) as SmartPlan.AiTranslate
        assertEquals("4", byTrack.primary.id)
    }

    @Test fun `AI Translate mode without a key keeps the primary and says why`() {
        val plan = SmartBilingualPlanner.plan(input(en, zhHans, ai = false, mode = BilingualGenerationMode.AI_TRANSLATE))
        assertEquals(BilingualNotice.Kind.AI_NOT_CONFIGURED, (plan as SmartPlan.PrimaryOnly).why)
    }

    @Test fun `switching modes on the same video changes the plan immediately`() {
        val tracks = arrayOf(en, zhHans)
        assertTrue(SmartBilingualPlanner.plan(input(*tracks, mode = BilingualGenerationMode.AUTO)) is SmartPlan.MergeEmbedded)
        assertTrue(SmartBilingualPlanner.plan(input(*tracks, mode = BilingualGenerationMode.AI_TRANSLATE)) is SmartPlan.AiTranslate)
        assertTrue(SmartBilingualPlanner.plan(input(*tracks, mode = BilingualGenerationMode.MERGE)) is SmartPlan.MergeEmbedded)
        // Only English: Auto falls back to AI, Merge does not.
        assertTrue(SmartBilingualPlanner.plan(input(en, mode = BilingualGenerationMode.AUTO)) is SmartPlan.AiTranslate)
        assertTrue(SmartBilingualPlanner.plan(input(en, mode = BilingualGenerationMode.MERGE)) is SmartPlan.PrimaryOnly)
    }

    @Test fun `generation mode and display mode are independent settings`() {
        val s = BilingualSubtitleSettings(generationMode = BilingualGenerationMode.MERGE, mode = BilingualDisplayMode.ORIGINAL_ONLY)
        val back = BilingualSettingsCodec.decode(BilingualSettingsCodec.encode(s))
        assertEquals(BilingualGenerationMode.MERGE, back.generationMode)
        assertEquals(BilingualDisplayMode.ORIGINAL_ONLY, back.mode)
        val changed = back.copy(generationMode = BilingualGenerationMode.AI_TRANSLATE)
        assertEquals(BilingualDisplayMode.ORIGINAL_ONLY, changed.mode)
        // Old settings files (no generationMode key) load as Auto.
        assertEquals(BilingualGenerationMode.AUTO, BilingualSettingsCodec.decode("{\"mode\":\"bilingual\"}").generationMode)
    }

    private fun primaryOf(plan: SmartPlan) = when (plan) {
        is SmartPlan.MergeEmbedded -> plan.primary.id
        is SmartPlan.AiTranslate -> plan.primary.id
        is SmartPlan.PrimaryOnly -> plan.primary.id
        else -> null
    }

    @Test fun `turning bilingual on never changes the selected subtitle - addon pick kept`() {
        val addonEn = SubtitleCandidate("addon-en", "en", "English (OpenSubtitles)", false, false, false, null)
        // Auto-picked or user-picked: either way the selected track stays the primary.
        assertEquals("addon-en", primaryOf(SmartBilingualPlanner.plan(input(addonEn, en, zhHans, selected = "addon-en", pinned = false))))
        assertEquals("addon-en", primaryOf(SmartBilingualPlanner.plan(input(addonEn, en, zhHans, selected = "addon-en", pinned = true))))
    }

    @Test fun `selected track is kept even when another language is preferred`() {
        assertEquals("9", primaryOf(SmartBilingualPlanner.plan(input(en, ja, zhHans, primary = "en", selected = "9"))))
        assertEquals("4", primaryOf(SmartBilingualPlanner.plan(input(en, enSdh, zhHans, selected = "4"))))
        assertEquals("9", primaryOf(SmartBilingualPlanner.plan(input(en, ja, mode = BilingualGenerationMode.AI_TRANSLATE, selected = "9"))))
    }

    @Test fun `nothing selected - embedded track chosen before any addon result`() {
        val addonEn = SubtitleCandidate("addon-en", "en", "English (OpenSubtitles)", false, false, false, null)
        assertEquals("3", primaryOf(SmartBilingualPlanner.plan(input(addonEn, en, zhHans))))
        // No embedded text track at all: an addon result is the only possible primary.
        assertEquals("addon-en", primaryOf(SmartBilingualPlanner.plan(input(addonEn, zhPgs))))
    }
}
