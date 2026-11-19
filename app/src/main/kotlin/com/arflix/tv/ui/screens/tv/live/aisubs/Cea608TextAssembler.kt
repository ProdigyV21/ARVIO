package com.arflix.tv.ui.screens.tv.live.aisubs

/** One caption sentence on the media timeline: from its first character to its last. */
internal data class CaptionSentence(val startUs: Long, val endUs: Long, val text: String)

/**
 * Turns a channel's CEA-608 closed captions (field 1, caption channel CC1) into plain sentences
 * with media times, so they can be translated ahead of playback instead of transcribing audio.
 *
 * Input is the `cc_data` the TS extractor copies out of the video's SEI messages: triplets of
 * (marker/valid/type, byte 1, byte 2), as the player's own CEA-608 decoder receives them. Display
 * layout (roll-up, pop-on, positions, colours) is ignored: only the spoken words matter here.
 * Control codes are sent twice by broadcasters; the repeat is dropped. A sentence ends at . ? !
 * (or after a pause of [GAP_US], or at [MAX_CHARS]).
 *
 * Not thread-safe: one instance per channel source, fed from the loading thread.
 */
internal class Cea608TextAssembler(private val onSentence: (CaptionSentence) -> Unit) {
    private val text = StringBuilder()
    private var firstCharUs = UNSET
    private var lastCharUs = UNSET
    private var lastControl = -1
    private var pendingBoundary = false

    /** True once any caption character has been seen for this source. */
    var sawText: Boolean = false
        private set

    fun feed(timeUs: Long, data: ByteArray, offset: Int, size: Int) {
        if (lastCharUs != UNSET && timeUs - lastCharUs > GAP_US) flush()
        var i = offset
        val end = offset + size - 2
        while (i < end) {
            val header = data[i].toInt() and 0xFF
            val b1 = data[i + 1].toInt() and 0x7F
            val b2 = data[i + 2].toInt() and 0x7F
            i += 3
            val valid = header and 0x04 != 0
            val fieldOne = header and 0x03 == 0
            if (!valid || !fieldOne || (b1 == 0 && b2 == 0)) continue
            handlePair(timeUs, b1, b2)
        }
    }

    /** The stream jumped (seek, reload): what was collected no longer joins what comes next. */
    fun reset() {
        text.setLength(0)
        firstCharUs = UNSET
        lastCharUs = UNSET
        lastControl = -1
        pendingBoundary = false
    }

    /** Emits whatever is collected as a sentence. */
    fun flush() {
        val sentence = text.toString().replace(WHITESPACE, " ").trim()
        if (sentence.length >= MIN_CHARS && firstCharUs != UNSET) {
            onSentence(CaptionSentence(firstCharUs, lastCharUs, sentence))
        }
        text.setLength(0)
        firstCharUs = UNSET
        lastCharUs = UNSET
        pendingBoundary = false
    }

    private fun handlePair(timeUs: Long, b1: Int, b2: Int) {
        if (b1 in 0x10..0x1F) {
            val code = (b1 shl 8) or b2
            // Broadcasters send every control code twice; act on it once.
            if (code == lastControl) {
                lastControl = -1
                return
            }
            lastControl = code
            // The second caption channel (CC2) uses 0x18-0x1F.
            if (b1 >= 0x18) return
            handleControl(timeUs, b1, b2)
            return
        }
        lastControl = -1
        if (b1 >= 0x20) appendChar(timeUs, basicChar(b1))
        if (b2 >= 0x20) appendChar(timeUs, basicChar(b2))
    }

    private fun handleControl(timeUs: Long, b1: Int, b2: Int) {
        when {
            // Miscellaneous control codes (RCL, BS, RU2-4, EDM, CR, ENM, EOC...).
            (b1 == 0x14 || b1 == 0x15) && b2 in 0x20..0x2F -> when (b2) {
                0x21 -> backspace()
                else -> boundary()
            }
            // Special characters (♪, ®, ½...): only letters with meaning are kept.
            b1 == 0x11 && b2 in 0x30..0x3F -> if (b2 == 0x37) boundary() else appendChar(timeUs, SPECIAL[b2 - 0x30])
            // Extended characters (accented capitals, symbols) follow a plain fallback letter
            // that a basic decoder shows instead; keeping that letter is good enough here.
            (b1 == 0x12 || b1 == 0x13) && b2 in 0x20..0x3F -> Unit
            // Preamble address codes and mid-row codes move the cursor: a word boundary.
            else -> boundary()
        }
    }

    private fun boundary() {
        pendingBoundary = true
    }

    private fun backspace() {
        if (text.isNotEmpty()) text.setLength(text.length - 1)
    }

    private fun appendChar(timeUs: Long, c: Char) {
        if (c == '\u0000') return
        if (pendingBoundary && text.isNotEmpty() && text.last() != ' ') text.append(' ')
        pendingBoundary = false
        if (c == ' ' && (text.isEmpty() || text.last() == ' ')) return
        if (c != ' ') {
            sawText = true
            if (firstCharUs == UNSET) firstCharUs = timeUs
            lastCharUs = timeUs
        }
        text.append(c)
        if (c in SENTENCE_END && text.length >= MIN_CHARS) {
            flush()
        } else if (c == ' ' && (text.length >= MAX_CHARS || lastCharUs - firstCharUs >= MAX_DURATION_US)) {
            // Long run-on captions end at a word boundary, like audio segments end at 5 s, so a
            // line is never held back longer than the playback delay allows for.
            flush()
        }
    }

    private fun basicChar(b: Int): Char = when (b) {
        0x2A -> 'á'
        0x5C -> 'é'
        0x5E -> 'í'
        0x5F -> 'ó'
        0x60 -> 'ú'
        0x7B -> 'ç'
        0x7C -> '÷'
        0x7D -> 'Ñ'
        0x7E -> 'ñ'
        0x7F -> '\u0000'
        else -> b.toChar()
    }

    companion object {
        private const val UNSET = Long.MIN_VALUE
        /** A pause in the captions this long ends the sentence. */
        const val GAP_US = 2_500_000L
        const val MAX_CHARS = 160
        const val MAX_DURATION_US = 5_000_000L
        private const val MIN_CHARS = 2
        private val SENTENCE_END = setOf('.', '?', '!')
        private val WHITESPACE = Regex("\\s+")
        private val SPECIAL = charArrayOf(
            '®', '°', '½', '¿', '™', '¢', '£', '\u0000', 'à', ' ', 'è', 'â', 'ê', 'î', 'ô', 'û',
        )
    }
}
