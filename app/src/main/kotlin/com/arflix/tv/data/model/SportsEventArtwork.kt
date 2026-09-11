package com.arflix.tv.data.model

import com.arflix.tv.data.api.StremioMetaPreview
import java.net.URI
import java.text.Normalizer
import java.util.Locale
import java.time.Instant

/** Artwork only. Addon status/times never overwrite the user's channel schedule. */
data class SportsEventArtwork(val title: String, val background: String, val genres: List<String>,
    val startsAt: Long? = null,
    val homeBadge: String? = null, val awayBadge: String? = null,
    val homeTeam: String? = null, val awayTeam: String? = null,
    val source: String? = null, val fixture: SportsFixture? = null) {
    val key: String = sportsArtworkKey(title)
}

private val marks = Regex("\\p{M}+")
private val livePrefix = Regex("^(live\\s*[:|-]\\s*|live\\s+)")
private val sportPrefix = Regex("^(football|soccer|basketball|baseball|tennis|ice hockey|american football|boxing|mma|cricket)\\s*:\\s*")
private val versus = Regex("\\b(vs\\.?|versus|v\\.)\\s+")
private val punctuation = Regex("[^\\p{L}\\p{N}]+")
private val cosmeticTags = Regex("\\s*[\\[(](?:live|hd|fhd|uhd|4k)[\\])]\\s*", RegexOption.IGNORE_CASE)
private val matchupSeparator = Regex("\\s+(?:vs?\\.?|versus|at|[-–—])\\s+", RegexOption.IGNORE_CASE)

fun sportsArtworkKey(title: String): String = Normalizer.normalize(title, Normalizer.Form.NFD)
    .replace(marks, "").lowercase(Locale.ROOT)
    .replace(livePrefix, "").replace(sportPrefix, "")
    .replace(versus, "vs ").replace(punctuation, " ").trim()

/** Only cosmetic title differences are ignored. Age/gender/round qualifiers remain. */
fun sportsEventIdentity(title: String): String {
    val plain = title.replace(cosmeticTags, " ")
    val matchup = plain.substringAfterLast(':').substringBefore(',').trim()
    val normalized = sportsArtworkKey((if (matchupSeparator.containsMatchIn(matchup)) matchup else plain)
        .replace(matchupSeparator, " vs "))
    val sides = normalized.split(" vs ")
    return if (sides.size == 2 && sides.all { it.length >= 3 }) sides.sorted().joinToString(" vs ") else normalized
}

fun sportsQualifierKey(text: String): String = Regex("\\b(women(?:s|'s)?|youth|u\\d{2}|under[ -]?\\d{2})\\b")
    .findAll(text.lowercase(Locale.ROOT)).map { it.value.replace(Regex("^women.*"), "women").replace("under", "u").replace(Regex("[ -]"), "") }
    .toSet().sorted().joinToString("|")

fun safeSportsImage(image: String?): String? = image?.takeIf { it.length <= 2048 && !it.contains("_UTC", true) }?.let {
    val uri = runCatching { URI(it) }.getOrNull()
    it.takeIf { uri?.scheme?.lowercase(Locale.ROOT) in setOf("https", "http") && !uri?.host.isNullOrBlank() }
}

fun StremioMetaPreview.toSportsEventArtwork(): SportsEventArtwork? {
    val title = name?.takeIf { it.isNotBlank() } ?: return null
    if (id?.startsWith("leaf:") == true) return null // Channel-recording covers are not match artwork.
    // Posters may contain UTC times. Backgrounds are the addon's untimed landscape assets.
    val image = background?.takeIf { it.isNotBlank() && !it.contains("_UTC", ignoreCase = true) } ?: return null
    val uri = runCatching { URI(image) }.getOrNull() ?: return null
    if (uri.scheme?.lowercase(Locale.ROOT) !in setOf("https", "http") || uri.host.isNullOrBlank()) return null
    return SportsEventArtwork(title, image, genres.orEmpty(), released?.let { runCatching { Instant.parse(it).toEpochMilli() }.getOrNull() })
}
