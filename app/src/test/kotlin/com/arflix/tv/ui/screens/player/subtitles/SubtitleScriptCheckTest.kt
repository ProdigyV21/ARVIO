package com.arflix.tv.ui.screens.player.subtitles

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * The garbled lines below are real output of gemini-3.5-flash-lite translating English subtitles to
 * Greek. Each one arrived inside a well-formed reply with the right line count, so it was cached and
 * shown. The check has to catch them without flagging the ordinary lines around them, because every
 * flag costs a request.
 */
class SubtitleScriptCheckTest {

    private val greek = ScriptFamily.GREEK

    @Test
    fun lettersFromAnUnrelatedScriptAreCorruption() {
        assertThat(hasScriptCorruption("Πάρε τον Σول Γκούντμαν.", "Call Saul Goodman.", greek)).isTrue()
        assertThat(hasScriptCorruption("Ήταν αابλαβής.", "He was unharmed.", greek)).isTrue()
        assertThat(hasScriptCorruption("Είπूंगा ότι φεύγεις.", "You said you were leaving.", greek)).isTrue()
    }

    @Test
    fun aWordThatSwitchesAlphabetHalfwayIsCorruption() {
        assertThat(hasScriptCorruption("Τι λέει, συνεργάate;", "What's up, partner?", greek)).isTrue()
        assertThat(hasScriptCorruption("Καταστrafηκε.", "Ruined.", greek)).isTrue()
        assertThat(hasScriptCorruption("Πρέπει να εγκlimatιστεί.", "It needs to acclimate.", greek)).isTrue()
    }

    @Test
    fun ordinaryTranslatedLinesAreClean() {
        assertThat(hasScriptCorruption("Πού ήσουν;", "Where were you?", greek)).isFalse()
        assertThat(hasScriptCorruption("- Συγγνώμη.\n- Δεν πειράζει.", "- Sorry.\n- Don't be.", greek)).isFalse()
        assertThat(hasScriptCorruption("♪ ♪", "♪ ♪", greek)).isFalse()
        assertThat(hasScriptCorruption("Το 1985, στις 3:15.", "In 1985, at 3:15.", greek)).isFalse()
    }

    @Test
    fun wholeLatinWordsStayAllowed() {
        // Names, brands and acronyms are routinely left in Latin script in every language.
        assertThat(hasScriptCorruption("Δουλεύει στην DEA.", "He works for the DEA.", greek)).isFalse()
        assertThat(hasScriptCorruption("Πάμε στο Los Pollos Hermanos.", "Let's go to Los Pollos Hermanos.", greek)).isFalse()
        assertThat(hasScriptCorruption("Έχεις Wi-Fi;", "Do you have Wi-Fi?", greek)).isFalse()
    }

    @Test
    fun aSingleLookAlikeLatinLetterIsNotWorthARequest() {
        // Latin "o" inside a Greek word: wrong code point, identical on screen.
        assertThat(hasScriptCorruption("Καλo βράδυ.", "Good evening.", greek)).isFalse()
    }

    @Test
    fun aScriptTheSourceLineUsesIsNotCorruption() {
        // The film's own line quotes Russian; the translation keeping it is correct.
        assertThat(hasScriptCorruption("Είπε «спасибо» και έφυγε.", "He said «спасибо» and left.", greek)).isFalse()
        // The same Cyrillic with nothing in the source to explain it is corruption.
        assertThat(hasScriptCorruption("Είπε спасибо και έφυγε.", "He said thanks and left.", greek)).isTrue()
    }

    @Test
    fun latinTargetsCatchForeignLettersButNotAccents() {
        val latin = ScriptFamily.LATIN
        assertThat(hasScriptCorruption("¿Dónde estabas, compañero?", "Where were you, partner?", latin)).isFalse()
        assertThat(hasScriptCorruption("¿Dónde estaбas?", "Where were you?", latin)).isTrue()
    }

    @Test
    fun languagesWrittenWithoutSpacesMayMixLatinFreely() {
        val eastAsian = ScriptFamily.EAST_ASIAN
        // Kanji, kana and a Latin name in one unbroken run is normal Japanese.
        assertThat(hasScriptCorruption("私はTomです。", "I'm Tom.", eastAsian)).isFalse()
        assertThat(hasScriptCorruption("Tシャツを買った。", "I bought a T-shirt.", eastAsian)).isFalse()
        assertThat(hasScriptCorruption("私はТомです。", "I'm Tom.", eastAsian)).isTrue()
    }

    @Test
    fun dominantScriptComesFromTheTranslationNotTheSource() {
        val source = listOf("Where were you?", "I was at the lab.", "You're late again.")
        val translated = listOf("Πού ήσουν;", "Ήμουν στο εργαστήριο.", "Άργησες πάλι.")
        assertThat(dominantScript(translated, source)).isEqualTo(greek)
    }

    @Test
    fun linesLeftInTheSourceLanguageDoNotVote() {
        // Two of three lines were dropped by the model and are still English. Counting them would
        // make Latin the majority and turn every Greek line into a suspect.
        val source = listOf("Where were you all night long?", "I was at the lab with him.", "You're late again.")
        val translated = listOf(source[0], source[1], "Άργησες πάλι, όπως κάθε μέρα.")
        assertThat(dominantScript(translated, source)).isEqualTo(greek)
    }

    @Test
    fun tooLittleTextTurnsTheCheckOff() {
        assertThat(dominantScript(listOf("Ναι."), listOf("Yes."))).isNull()
        assertThat(dominantScript(listOf("OK"), listOf("Okay"))).isNull()
    }

    @Test
    fun suspectsAreDroppedLinesAndCorruptedLinesOnly() {
        val source = listOf(
            "Where were you?", "Call Saul Goodman.", "You're late again.", "♪ ♪", "What's up, partner?"
        )
        val translated = listOf(
            "Πού ήσουν;", "Πάρε τον Σول Γκούντμαν.", "You're late again.", "♪ ♪", "Τι λέει, συνεργάate;"
        )
        // The model dropped lines 2 and 3 from its reply.
        val suspects = suspectLineIndices(source, translated, untranslated = setOf(2, 3), expected = greek)
        // 1 and 4 are corrupted, 2 was dropped; 3 was dropped too but has nothing to translate.
        assertThat(suspects).containsExactly(1, 2, 4).inOrder()
    }

    @Test
    fun withoutAKnownScriptOnlyDroppedLinesAreSuspect() {
        val source = listOf("Yes.", "No.")
        val translated = listOf("Ναι.", "No.")
        assertThat(suspectLineIndices(source, translated, setOf(1), expected = null)).containsExactly(1)
    }

    @Test
    fun aRetryReplacesALineOnlyWhenItIsBetter() {
        val source = listOf("Call Saul Goodman.", "You're late again.", "What's up, partner?", "Ruined.")
        val first = listOf("Πάρε τον Σول Γκούντμαν.", "You're late again.", "Τι λέει, συνεργάate;", "Καταστrafηκε.")
        val suspects = listOf(0, 1, 2, 3)
        // Retry: line 0 clean, line 1 dropped again, line 2 garbled again, line 3 clean.
        val retryLines = listOf("Πάρε τον Σολ Γκούντμαν.", "You're late again.", "Τι λέει, συνεργάti;", "Καταστράφηκε.")

        val merged = mergeRepairedLines(source, first, suspects, retryLines, retryUntranslated = setOf(1), expected = greek)

        assertThat(merged).containsExactly(
            "Πάρε τον Σολ Γκούντμαν.",   // fixed
            "You're late again.",        // still dropped: first attempt stands
            "Τι λέει, συνεργάate;",      // retry no better: first attempt stands
            "Καταστράφηκε."              // fixed
        ).inOrder()
    }

    @Test
    fun aShortRetryReplyLeavesTheRestUntouched() {
        val source = listOf("Call Saul Goodman.", "Ruined.")
        val first = listOf("Πάρε τον Σول Γκούντμαν.", "Καταστrafηκε.")
        val merged = mergeRepairedLines(source, first, listOf(0, 1), listOf("Πάρε τον Σολ Γκούντμαν."), emptySet(), greek)
        assertThat(merged).containsExactly("Πάρε τον Σολ Γκούντμαν.", "Καταστrafηκε.").inOrder()
    }

    @Test
    fun japaneseIterationMarkIsEastAsian() {
        val ja = ScriptFamily.EAST_ASIAN
        assertThat(scriptFamilyOf('々'.code)).isEqualTo(ja)
        assertThat(hasScriptCorruption("人々が集まった。", "People gathered.", ja)).isFalse()
        assertThat(hasScriptCorruption("時々そう思う。", "Sometimes I think so.", ja)).isFalse()
    }

    @Test
    fun modifierApostropheAndLigaturesAreLatin() {
        assertThat(scriptFamilyOf(0x02BC)).isEqualTo(ScriptFamily.LATIN)
        assertThat(scriptFamilyOf(0xFB01)).isEqualTo(ScriptFamily.LATIN)
        // Ukrainian "мʼясо" (meat) is ordinary Cyrillic text, not a third script.
        assertThat(hasScriptCorruption("Це мʼясо.", "This is meat.", ScriptFamily.CYRILLIC)).isFalse()
    }
}
