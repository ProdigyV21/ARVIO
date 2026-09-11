package com.arflix.tv.data.model

import java.text.Normalizer
import java.util.Locale

data class ChannelLogoEntry(val id: String, val country: String, val names: List<String>, val urls: List<String>)

/** Exact identity matching only; unknown and ambiguous names intentionally have no logo. */
class ChannelLogoIndex(entries: List<ChannelLogoEntry>) {
    private val byId = entries.associateBy { it.id.lowercase(Locale.ROOT) }
    private val byName = buildMap<String, List<ChannelLogoEntry>> {
        entries.forEach { entry -> entry.names.map(::nameKey).distinct().forEach { key ->
            if (key.isNotBlank()) put(key, get(key).orEmpty() + entry)
        } }
    }

    fun candidates(epgId: String?, name: String): List<String> {
        byId[epgId?.trim()?.lowercase(Locale.ROOT)]?.let { return it.urls }
        val cleaned = name.trim().replace(Regex("^(?:4K|8K|UHD|FHD|HD)\\s*[|:]\\s*", RegexOption.IGNORE_CASE), "")
        val prefix = countryPrefix.find(cleaned)
        val country = prefix?.groupValues?.get(1)?.uppercase(Locale.ROOT)?.let { countries[it] }
        val title = if (country != null) cleaned.substring(prefix!!.range.last + 1) else cleaned
        val matches = byName[nameKey(title)].orEmpty().filter {
            country == null || (countries[it.country.uppercase(Locale.ROOT)] ?: it.country.uppercase(Locale.ROOT)) == country
        }
        return matches.singleOrNull()?.urls.orEmpty()
    }

    companion object {
        private val countries = mapOf("UK" to "GB", "GB" to "GB", "USA" to "US", "US" to "US",
            "NL" to "NL", "NLD" to "NL", "DE" to "DE", "GER" to "DE", "FR" to "FR",
            "ES" to "ES", "IT" to "IT", "CA" to "CA", "AU" to "AU", "PT" to "PT",
            "BR" to "BR", "BE" to "BE", "CH" to "CH", "AT" to "AT", "IE" to "IE",
            "DK" to "DK", "DNK" to "DK", "SE" to "SE", "NO" to "NO", "FI" to "FI",
            "PL" to "PL", "RO" to "RO", "TR" to "TR", "IN" to "IN", "AR" to "AR")
        private val countryPrefix = Regex("^([A-Za-z]{2,3})(?:-[A-Za-z0-9]+)?\\s*[|:]\\s*")
        private val quality = Regex("(?:[\\s|_-]+(?:SD|HD|FHD|UHD|4K|8K|HEVC|H265|H264|RAW|BACKUP|1080P|720P|2160P))+$", RegexOption.IGNORE_CASE)
        private val marks = Regex("\\p{M}+")
        private val punctuation = Regex("[^\\p{L}\\p{N}+]")
        fun nameKey(value: String): String = punctuation.replace(marks.replace(Normalizer.normalize(
            quality.replace(value.trim(), ""), Normalizer.Form.NFKD), ""), "").lowercase(Locale.ROOT)
    }
}
