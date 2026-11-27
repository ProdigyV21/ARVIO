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
    val isScheduleMetadata: Boolean get() = source == "TheSportsDB" || source == "ESPN" || source == "MLB"
}

private val marks = Regex("\\p{M}+")
private val livePrefix = Regex("^(live\\s*[:|-]\\s*|live\\s+)")
private val sportPrefix = Regex("^(football|soccer|basketball|baseball|tennis|ice hockey|american football|boxing|mma|cricket)\\s*:\\s*")
private val versus = Regex("\\b(vs\\.?|versus|v\\.)\\s+")
private val punctuation = Regex("[^\\p{L}\\p{N}]+")
private val cosmeticTags = Regex("\\s*[\\[(](?:live|hd|fhd|uhd|4k)[\\])]\\s*", RegexOption.IGNORE_CASE)
private val matchupSeparator = Regex("\\s+(?:vs?\\.?|versus|at|[-–—])\\s+", RegexOption.IGNORE_CASE)
private val qualifierRegex = Regex("\\b(women(?:s|'s)?|youth|u\\d{2}|under[ -]?\\d{2})\\b")
private val womenPrefixRegex = Regex("^women.*")
private val hyphenSpaceRegex = Regex("[ -]")

// The same programme and fixture titles are normalised again on every catalogue pass
// (several regex passes each). Memoising them took the biggest single cost out of the
// sports join; the bound keeps memory flat on huge guides.
private const val TITLE_CACHE_LIMIT = 8_192
private val artworkKeyCache = java.util.concurrent.ConcurrentHashMap<String, String>()
private val eventIdentityCache = java.util.concurrent.ConcurrentHashMap<String, String>()

private inline fun memo(cache: java.util.concurrent.ConcurrentHashMap<String, String>, title: String, compute: () -> String): String {
    cache[title]?.let { return it }
    val value = compute()
    if (cache.size >= TITLE_CACHE_LIMIT) cache.clear()
    cache[title] = value
    return value
}

fun sportsArtworkKey(title: String): String = memo(artworkKeyCache, title) { computeSportsArtworkKey(title) }

private fun computeSportsArtworkKey(title: String): String = Normalizer.normalize(title, Normalizer.Form.NFD)
    .replace(marks, "").lowercase(Locale.ROOT)
    .replace(livePrefix, "").replace(sportPrefix, "")
    .replace(versus, "vs ").replace(punctuation, " ").trim()

/** Only cosmetic title differences are ignored. Age/gender/round qualifiers remain. */
fun sportsEventIdentity(title: String): String = memo(eventIdentityCache, title) { computeSportsEventIdentity(title) }

private fun computeSportsEventIdentity(title: String): String {
    val plain = title.replace(cosmeticTags, " ")
    val matchup = plain.substringAfterLast(':').substringBefore(',').trim()
    val normalized = sportsArtworkKey((if (matchupSeparator.containsMatchIn(matchup)) matchup else plain)
        .replace(matchupSeparator, " vs "))
    val sides = normalized.split(" vs ")
    return if (sides.size == 2 && sides.all { it.length >= 3 }) sides.sorted().joinToString(" vs ") else normalized
}

fun sportsQualifierKey(text: String): String = qualifierRegex
    .findAll(text.lowercase(Locale.ROOT)).map { it.value.replace(womenPrefixRegex, "women").replace("under", "u").replace(hyphenSpaceRegex, "") }
    .toSet().sorted().joinToString("|")

fun safeSportsImage(image: String?): String? = image?.takeIf { it.length <= 2048 && !it.contains("_UTC", true) }?.let {
    val uri = try { URI(it) } catch (_: Exception) { null }
    it.takeIf { uri?.scheme?.lowercase(Locale.ROOT) in setOf("https", "http") && !uri?.host.isNullOrBlank() }
}

fun StremioMetaPreview.toSportsEventArtwork(): SportsEventArtwork? {
    val title = name?.takeIf { it.isNotBlank() } ?: return null
    if (id?.startsWith("leaf:") == true) return null // Channel-recording covers are not match artwork.
    // Posters may contain UTC times. Backgrounds are the addon's untimed landscape assets.
    val image = background?.takeIf { it.isNotBlank() && !it.contains("_UTC", ignoreCase = true) } ?: return null
    val uri = try { URI(image) } catch (_: Exception) { null } ?: return null
    if (uri.scheme?.lowercase(Locale.ROOT) !in setOf("https", "http") || uri.host.isNullOrBlank()) return null
    return SportsEventArtwork(title, image, genres.orEmpty(), released?.let { try { Instant.parse(it).toEpochMilli() } catch (_: Exception) { null } })
}
