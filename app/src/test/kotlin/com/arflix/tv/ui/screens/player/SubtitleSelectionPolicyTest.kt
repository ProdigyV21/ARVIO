package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.util.normalizeLanguageCode
import org.junit.Assert.*
import org.junit.Test

class SubtitleSelectionPolicyTest {
    private val english = Subtitle("en", "", "en", "English", "embedded", isEmbedded = true)
    private val hebrew = english.copy(id = "he", lang = "he", label = "Hebrew")
    private val external = english.copy(id = "addon", url = "https://example.test/sub.srt", provider = "addon", isEmbedded = false)

    private fun source(tracks: List<Subtitle>, target: String = "he", rejected: Set<String> = emptySet()) =
        SubtitleSelectionPolicy.aiSource(tracks, target, rejected, ::normalizeLanguageCode)

    @Test fun providerIsPartOfTheLock() {
        assertNotEquals(SubtitleSelectionPolicy.key(external), SubtitleSelectionPolicy.key(external.copy(provider = "other")))
    }

    @Test fun retimedCopyKeepsTheSameLock() {
        assertEquals(SubtitleSelectionPolicy.key(external), SubtitleSelectionPolicy.key(external.copy(id = external.id + "#retimed")))
    }

    @Test fun externalEnglishIsNeverAnAiSource() {
        assertNull(source(listOf(external)))
    }

    @Test fun rejectedHebrewAddonDoesNotDisplaceEmbeddedEnglish() {
        assertEquals(english, source(listOf(external.copy(lang = "he"), hebrew, english)))
    }

    @Test fun embeddedEnglishBeatsOtherLanguages() {
        assertEquals(english, source(listOf(hebrew.copy(lang = "fr"), english)))
    }

    @Test fun targetEnglishMustUseAnotherLanguage() {
        assertEquals(hebrew, source(listOf(english, hebrew), target = "English"))
    }

    @Test fun targetLanguageAliasesCannotBeTranslatedIntoThemselves() {
        for (tag in listOf("he", "heb", "iw", "Hebrew")) {
            assertNull(source(listOf(hebrew.copy(lang = tag))))
            assertNull(source(listOf(hebrew), target = tag))
        }
    }

    @Test fun targetLanguageLabelCountsWhenTagIsMissing() {
        assertNull(source(listOf(hebrew.copy(lang = ""))))
    }

    @Test fun untaggedContainerPlaceholderIsNotATargetLanguage() {
        // PlayerScreen writes "und" for an untagged muxed track; the label is the only signal.
        for (tag in listOf("und", "UND", "unknown")) {
            assertNull(source(listOf(hebrew.copy(lang = tag))))
        }
    }

    @Test fun bitmapAndForcedTracksCannotBeSources() {
        for (track in listOf(english.copy(isBitmap = true), english.copy(isForced = true), english.copy(label = "SUBFORCED"))) {
            assertNull(source(listOf(track)))
        }
    }

    @Test fun plainEnglishBeatsSdhAndCc() {
        assertEquals(english, source(listOf(english.copy(id = "sdh", label = "English SDH"), english.copy(id = "cc", label = "English CC"), english)))
    }

    @Test fun sdhIsUsableWhenItIsTheOnlyTextSource() {
        val sdh = english.copy(label = "English SDH")
        assertEquals(sdh, source(listOf(sdh)))
    }

    @Test fun runtimeImageSourceIsNotPickedAgain() {
        val french = english.copy(id = "fr", lang = "fr", label = "French")
        val rejected = setOf(SubtitleSelectionPolicy.key(english))
        assertEquals(french, source(listOf(english, french), rejected = rejected))
        assertNull(source(listOf(english), rejected = rejected))
    }

    @Test fun runtimeRejectionIsProviderScoped() {
        val other = english.copy(provider = "another-container")
        assertEquals(other, source(listOf(english, other), rejected = setOf(SubtitleSelectionPolicy.key(english))))
    }

    @Test fun localExternalCopyDoesNotRevertToTheRemoteUrl() {
        val local = external.copy(url = "file:///cache/sub.srt")
        assertEquals(local, SubtitleSelectionPolicy.resolveSelected(local, listOf(external)))
    }

    @Test fun embeddedReindexFollowsTheSameProvidersTrack() {
        val fresh = english.copy(id = "reindexed", groupIndex = 3, trackIndex = 0)
        assertEquals(fresh, SubtitleSelectionPolicy.resolveSelected(english, listOf(fresh)))
    }

    @Test fun matchingIdOrUrlFromAnotherProviderCannotReplaceTheSelection() {
        assertEquals(external, SubtitleSelectionPolicy.resolveSelected(external, listOf(external.copy(provider = "other"))))
        assertEquals(english, SubtitleSelectionPolicy.resolveSelected(english, listOf(english.copy(provider = "other", id = "new"))))
    }

    @Test fun missingOrEmptySelectionIsPreserved() {
        assertEquals(english, SubtitleSelectionPolicy.resolveSelected(english, emptyList()))
        assertNull(SubtitleSelectionPolicy.resolveSelected(null, listOf(english)))
    }
}
