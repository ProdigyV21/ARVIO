package com.arflix.tv.ui.screens.tv.live.aisubs

import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

/** org.json is part of the Android framework, so these run under Robolectric. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = android.app.Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class GeminiLiveProtocolTest {
    private val segment = SpeechSegment(startUs = 100_000_000, endUs = 104_000_000, pcm16k = FloatArray(16))

    private fun cues(result: LiveEngineResult) = (result as LiveEngineResult.Cues).cues

    @Test fun documentedObjectIsTimedFromItsOffsets() {
        val result = GeminiLiveProtocol.parseLines(
            """{"lines":[{"text":"שלום","startOffsetMs":0},{"text":"מה שלומך?","startOffsetMs":1500}],"sourceLanguage":"en"}""",
            segment, rtl = true,
        )
        val cues = cues(result)
        assertEquals(listOf("שלום", "מה שלומך?"), cues.map { it.text })
        assertEquals(100_000_000L, cues[0].startUs)
        assertEquals(101_500_000L, cues[0].endUs)
        assertEquals(101_500_000L, cues[1].startUs)
        assertEquals(104_000_000L, cues[1].endUs)
        assertTrue(cues.all { it.rtl })
        assertEquals("en", (result as LiveEngineResult.Cues).sourceLanguage)
    }

    @Test fun linesWithoutOffsetsShareTheSegmentByLength() {
        val cues = cues(GeminiLiveProtocol.parseLines("""{"lines":[{"text":"aaaa"},{"text":"bbbb"}]}""", segment, rtl = false))
        assertEquals(102_000_000L, cues[1].startUs)
    }

    @Test fun offsetsAreKeptInOrderAndInsideTheSegment() {
        val cues = cues(
            GeminiLiveProtocol.parseLines(
                """{"lines":[{"text":"a","startOffsetMs":3000},{"text":"b","startOffsetMs":1000},{"text":"c","startOffsetMs":99000}]}""",
                segment, rtl = false,
            ),
        )
        assertTrue(cues.zipWithNext().all { (x, y) -> y.startUs >= x.startUs })
        assertTrue(cues.all { it.startUs in segment.startUs until segment.endUs })
    }

    @Test fun emptyLinesMeanNoSpeech() {
        assertTrue(cues(GeminiLiveProtocol.parseLines("""{"lines":[]}""", segment, rtl = false)).isEmpty())
    }

    @Test fun bareArrayOfStringsIsAccepted() {
        val cues = cues(GeminiLiveProtocol.parseLines("""["Hello there", "General Kenobi"]""", segment, rtl = false))
        assertEquals(listOf("Hello there", "General Kenobi"), cues.map { it.text })
    }

    @Test fun fencedReplyIsAccepted() {
        val text = "```json\n[{\"text\":\"One\"},{\"text\":\"Two\"}]\n```"
        assertEquals(2, cues(GeminiLiveProtocol.parseLines(text, segment, rtl = false)).size)
    }

    @Test fun newlinesAndExtraSpacesAreCollapsed() {
        val cues = cues(GeminiLiveProtocol.parseLines("""{"lines":[{"text":"  two\nlines   here "}]}""", segment, rtl = false))
        assertEquals("two lines here", cues.single().text)
    }

    @Test fun malformedReplyFails() {
        assertTrue(GeminiLiveProtocol.parseLines("not json at all", segment, rtl = false) is LiveEngineResult.Failed)
    }

    @Test fun responseSkipsThoughtPartsAndReadsTheText() {
        val body = JSONObject().put(
            "candidates",
            JSONArray().put(
                JSONObject().put(
                    "content",
                    JSONObject().put(
                        "parts",
                        JSONArray()
                            .put(JSONObject().put("thought", true).put("text", "thinking…"))
                            .put(JSONObject().put("text", """{"lines":[{"text":"Hi"}]}""")),
                    ),
                ).put("finishReason", "STOP"),
            ),
        ).toString()
        assertEquals("Hi", cues(GeminiLiveProtocol.parseResponse(body, segment, rtl = false)).single().text)
    }

    @Test fun responseWithoutCandidatesFails() {
        val blocked = """{"promptFeedback":{"blockReason":"SAFETY"}}"""
        assertEquals(LiveEngineResult.Failed("blocked"), GeminiLiveProtocol.parseResponse(blocked, segment, rtl = false))
        assertTrue(GeminiLiveProtocol.parseResponse("<html>", segment, rtl = false) is LiveEngineResult.Failed)
    }

    @Test fun invalidKeyIsRecognised() {
        assertTrue(GeminiLiveProtocol.isInvalidKey(400, """{"error":{"status":"INVALID_ARGUMENT","details":[{"reason":"API_KEY_INVALID"}]}}"""))
        assertTrue(GeminiLiveProtocol.isInvalidKey(403, ""))
        assertFalse(GeminiLiveProtocol.isInvalidKey(400, """{"error":{"message":"Request too large"}}"""))
        assertFalse(GeminiLiveProtocol.isInvalidKey(500, ""))
    }

    @Test fun requestCarriesAudioContextAndJsonMode() {
        val body = JSONObject(GeminiLiveProtocol.requestBody(segment, listOf("previous line"), "Hebrew"))
        val parts = body.getJSONArray("contents").getJSONObject(0).getJSONArray("parts")
        assertEquals("audio/wav", parts.getJSONObject(0).getJSONObject("inline_data").getString("mime_type"))
        assertTrue(parts.getJSONObject(1).getString("text").contains("previous line"))
        val config = body.getJSONObject("generationConfig")
        assertEquals("application/json", config.getString("responseMimeType"))
        assertEquals("minimal", config.getJSONObject("thinkingConfig").getString("thinkingLevel"))
        assertTrue(body.getJSONObject("system_instruction").toString().contains("Hebrew"))
    }

    @Test fun wavHeaderDescribes16kMonoPcm16() {
        val wav = GeminiLiveProtocol.wav16kMono(floatArrayOf(0f, 1f, -1f))
        assertEquals(44 + 6, wav.size)
        assertEquals("RIFF", String(wav, 0, 4, Charsets.US_ASCII))
        assertEquals("WAVE", String(wav, 8, 4, Charsets.US_ASCII))
        val rate = (wav[24].toInt() and 0xFF) or ((wav[25].toInt() and 0xFF) shl 8) or ((wav[26].toInt() and 0xFF) shl 16)
        assertEquals(16_000, rate)
        assertEquals(1, wav[22].toInt())
        val loud = (wav[46].toInt() and 0xFF) or (wav[47].toInt() shl 8)
        assertEquals(Short.MAX_VALUE.toInt(), loud)
    }
}
