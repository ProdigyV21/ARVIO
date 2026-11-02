package com.arflix.tv.ui.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StreamLanguagesTest {
    @Test
    fun `every named language is found in order`() {
        assertEquals(listOf("en", "de", "it"), detectStreamLanguages("4K WEB-DL | Eng Deu Ita | 20 Mbps"))
    }

    @Test
    fun `scene separators and full names count`() {
        assertEquals(listOf("de", "en"), detectStreamLanguages("Film.2024.German.DL.ENG.2160p.WEB.h265"))
        assertEquals(listOf("de"), detectStreamLanguages("Deutsch AC3"))
    }

    @Test
    fun `flag emoji count`() {
        assertEquals(listOf("en", "de"), detectStreamLanguages("🇬🇧 / 🇩🇪 · 8 GB"))
    }

    @Test
    fun `two letter words are not languages`() {
        assertEquals(emptyList<String>(), detectStreamLanguages("IT 2017 DE LA SOUL 1080p"))
    }

    @Test
    fun `preferred language leads the badge with the others counted`() {
        assertEquals("🇩🇪 DE +2", streamLanguageBadge(listOf("en", "de", "it"), false, "de"))
        assertEquals("🇬🇧 EN +2", streamLanguageBadge(listOf("en", "de", "it"), false, "fr"))
        assertEquals("🇩🇪 DE +1", streamLanguageBadge(listOf("en", "de"), false, "German"))
    }

    @Test
    fun `single language and multi audio`() {
        assertEquals("🇩🇪 DE", streamLanguageBadge(listOf("de"), false, "de"))
        assertEquals("🇩🇪 DE 🌐", streamLanguageBadge(listOf("de"), true, "de"))
        assertEquals("🇫🇷 FR 🌐", streamLanguageBadge(listOf("fr"), true, "de"))
        assertEquals("TE 🌐", streamLanguageBadge(listOf("te"), true, null))
        assertEquals("🇫🇷 FR +1", streamLanguageBadge(listOf("fr", "en"), true, "de"))
        assertNull(streamLanguageBadge(emptyList(), true, "de"))
    }

    @Test
    fun `language without a flag shows its code`() {
        assertEquals("TE +1", streamLanguageBadge(listOf("te", "en"), false, null))
    }

    @Test
    fun `back to back flags are read pair by pair`() {
        assertEquals(listOf("en", "ru"), detectStreamLanguages("🇬🇧🇷🇺"))
        assertEquals(listOf("en", "de", "it", "hu"), detectStreamLanguages("🇺🇸🇩🇪🇮🇹🇭🇺"))
    }

    @Test
    fun `title words and subtitle languages are not audio`() {
        assertEquals(listOf("en"), detectStreamLanguages("The.Italian.Job.2003.ENG.1080p", ignoreWordsOf = "The Italian Job"))
        assertEquals(listOf("en"), detectStreamLanguages("Film.2024.ENG.GERMAN.SUBBED.1080p"))
    }

    @Test
    fun `brazilian portuguese preference ranks portuguese first`() {
        assertEquals("🇵🇹 PT +1", streamLanguageBadge(listOf("en", "pt"), false, "Portuguese (Brazil)"))
    }

    @Test
    fun `subtitle section does not remove preceding audio or add subtitle languages`() {
        assertEquals(listOf("en"), detectStreamLanguages("Audio: English\nSubtitles: German French"))
        assertEquals(listOf("en"), detectStreamLanguages("Audio: English / Subtitles: German, French, Italian"))
    }

    @Test
    fun `audio section resumes after multiline subtitle section`() {
        assertEquals(listOf("en", "de"), detectStreamLanguages("Subtitles:\nFrench\nItalian\nAudio: English German"))
        assertEquals(listOf("en"), detectStreamLanguages("Subtitles German French\nAudio English"))
    }

    @Test
    fun `subtitle flags do not count as audio`() {
        assertEquals(listOf("en"), detectStreamLanguages("🎧 🇬🇧\n💬 🇩🇪🇫🇷"))
        assertEquals(emptyList<String>(), detectStreamLanguages("Subtitles: 🇩🇪🇫🇷"))
        assertEquals(listOf("de"), detectStreamLanguages("💬 🇫🇷\n🔊 🇩🇪"))
    }

    @Test
    fun `subtitle only descriptions have no audio language`() {
        assertEquals(emptyList<String>(), detectStreamLanguages("Subtitles: German French"))
        assertEquals(emptyList<String>(), detectStreamLanguages("German.SUBBED"))
    }

    @Test
    fun `indian flag never invents hindi beside tamil or telugu`() {
        assertEquals(listOf("ta"), detectStreamLanguages("🇮🇳 Tamil"))
        assertEquals(listOf("te"), detectStreamLanguages("🇮🇳 Telugu"))
        assertEquals("TE", streamLanguageBadge(detectStreamLanguages("🇮🇳 Telugu"), false, "hi"))
    }

    @Test
    fun `ambiguous flag alone is unknown but explicit hindi still works`() {
        assertEquals(emptyList<String>(), detectStreamLanguages("🇮🇳"))
        assertEquals(listOf("hi"), detectStreamLanguages("🇮🇳 Hindi"))
        assertEquals("🇮🇳 HI", streamLanguageBadge(detectStreamLanguages("🇮🇳 Hindi"), false, "hi"))
        assertEquals(listOf("hi", "ta"), detectStreamLanguages("🇮🇳 Hindi Tamil"))
    }

    @Test
    fun `subtitle section does not leak from description into filename`() {
        assertEquals(listOf("en", "de"), detectSourceAudioLanguages(
            null, listOf("Subtitles: French Italian", "Film.ENG.DEU.1080p.mkv"), "Film"
        ))
    }

    @Test
    fun `structured audio hint keeps short codes and ignores subtitle text`() {
        val languages = detectSourceAudioLanguages("en", listOf("Subtitles: German French"), "Film")
        assertEquals(listOf("en"), languages)
        assertEquals("🇬🇧 EN", streamLanguageBadge(languages, false, "de"))
        assertEquals(listOf("pt"), detectSourceAudioLanguages("pt-br", emptyList(), "Film"))
    }

    @Test
    fun `explicit audio hint is not mistaken for a word in the film title`() {
        assertEquals(listOf("it"), detectSourceAudioLanguages("it", emptyList(), "The Italian Job"))
        assertEquals(emptyList<String>(), detectSourceAudioLanguages(
            null, listOf("The.Italian.Job.2003.1080p"), "The Italian Job"
        ))
    }

    @Test
    fun `structured hints preserve language lists instead of normalizing only the first name`() {
        assertEquals(listOf("de", "en"), detectSourceAudioLanguages("German English", emptyList(), "Film"))
        assertEquals(listOf("ms"), detectSourceAudioLanguages("ms", emptyList(), "Film"))
        assertEquals(listOf("de"), detectSourceAudioLanguages("deu", emptyList(), "Film"))
    }
}
