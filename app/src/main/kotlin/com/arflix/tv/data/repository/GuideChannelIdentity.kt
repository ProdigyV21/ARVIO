package com.arflix.tv.data.repository

import com.arflix.tv.util.replaceLiteral

import java.util.Locale

/** Equivalent provider labels, without dropping the country or a +1 timeshift. */
internal object GuideChannelIdentity {
    private val countries = ("al ar at au ba be bg br ca ch cl cn co cy cz de dk dz ee eg es fi fr " +
        "gb gr hk hr hu id ie il in iq ir is it jp kr lb lt lu lv ma me mk mt mx my ng nl no nz " +
        "pe ph pk pl pt ro rs ru sa se sg si sk th tn tr tw ua us uy ve vn za").split(' ').toSet()
    private val aliases = mapOf("uk" to "gb", "usa" to "us", "nld" to "nl", "deu" to "de")
    private val prefix = Regex("""^\s*(?:\[([a-z]{2,3})]|\(([a-z]{2,3})\)|([a-z]{2,3})\s*[|:：/\-]+|([a-z]{2,3})\s+)(.*)$""")
    private val suffix = Regex("""^(.+)\.([a-z]{2,3})$""")
    private val quality = Regex("""\b(?:sd|hd|fhd|uhd|lq|hq|4k|8k|720p|1080p|2160p|hevc|x264|x265|h264|h265)\b""")
    private val punctuation = Regex("[^\\p{L}\\p{N}+]")

    fun key(value: String?): String? {
        val raw = value?.trim()?.lowercase(Locale.ROOT).orEmpty()
        if (raw.isEmpty()) return null
        val start = prefix.matchEntire(raw)?.takeIf { match ->
            val code = match.groupValues.slice(1..4).first { it.isNotEmpty() }
            (aliases[code] ?: code) in countries
        }
        val end = if (start == null) suffix.matchEntire(raw) else null
        val code = start?.groupValues?.slice(1..4)?.firstOrNull { it.isNotEmpty() }
            ?: end?.groupValues?.get(2) ?: return null
        val country = aliases[code] ?: code
        if (country !in countries) return null
        val name = start?.groupValues?.get(5) ?: end!!.groupValues[1]
        val label = quality.replace(name, "").replaceLiteral(punctuation, "")
        if (label.isBlank()) return null
        return "guide-region:$country:$label"
    }
}
