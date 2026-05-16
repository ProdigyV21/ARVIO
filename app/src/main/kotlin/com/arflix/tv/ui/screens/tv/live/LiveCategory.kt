package com.arflix.tv.ui.screens.tv.live

import androidx.compose.ui.graphics.Color
import com.arflix.tv.data.model.IptvChannel

/** Broad channel genre derived from M3U group name. */
enum class Genre {
    Sports, Movies, Series, News, Kids, Music, Docs, General;
}

/** Picture quality tier derived from channel name / group. */
enum class Quality(val label: String) {
    SD("SD"), HD("HD"), FHD("FHD"), K4("4K");
}

/**
 * Enriched channel — wraps [IptvChannel] with fields parsed from its
 * M3U metadata so the spec-level sidebar / EPG / badges can render.
 *
 * We never mutate [IptvChannel]; all core IPTV code keeps working.
 */
data class EnrichedChannel(
    val source: IptvChannel,
    val number: Int,
    val country: String?, // ISO-ish 2-letter, uppercase, or null if unresolved
    val genre: Genre,
    val quality: Quality,
    val lang: String,     // 2-letter uppercase language hint
    val brandBg: Color,
    val brandFg: Color,
    val isAdult: Boolean,
) {
    val id: String get() = source.id
    val name: String get() = source.name
    val streamUrl: String get() = source.streamUrl
    val logo: String? get() = source.logo
    val catchupDays: Int get() = source.catchupDays
}

data class LiveCategoryIndex(
    val byCategory: Map<String, List<EnrichedChannel>>,
    val byId: Map<String, EnrichedChannel>,
) {
    fun channelsFor(
        categoryId: String,
        favorites: Collection<String>,
        recents: Collection<String>,
    ): List<EnrichedChannel> {
        return when (categoryId) {
            "fav" -> favorites.mapNotNull(byId::get).filterNot { it.isAdult }
            "recent" -> recents.toList().asReversed().mapNotNull(byId::get).filterNot { it.isAdult }
            else -> byCategory[categoryId].orEmpty()
        }
    }

    companion object {
        val Empty = LiveCategoryIndex(emptyMap(), emptyMap())
    }
}

private data class ChannelTraits(
    val country: String?,
    val genre: Genre,
    val quality: Quality,
    val lang: String,
    val brandBg: Color,
    val brandFg: Color,
    val isAdult: Boolean,
)

private val ADULT_KEYWORDS = listOf("adult", "xxx", "18+", "erot", "nsfw")
private val TAG_RE = Regex("""[|\-:/,]+""")
private val TRIM_PUNCT = Regex("""^[\s\-|:•\u2022]+|[\s\-|:•\u2022]+$""")

// Bucketing codes. Real ISO countries plus language prefixes commonly used in
// IPTV playlists (EN, JA, KO, ZH, AR, SV, DA, EL, CS, HI, HE, FA) so groups
// like "EN | CHRISTMAS 1 4K" resolve instead of falling into the null bucket.
private val KNOWN_COUNTRIES = setOf(
    // ISO 3166-1 alpha-2 plus the UK/GB, USA aliases
    "NL", "UK", "GB", "US", "USA", "DE", "FR", "IT", "ES", "PT",
    "BE", "TR", "AR", "IN", "BR", "PL", "EX", "SE", "DK", "NO",
    "FI", "RU", "GR", "RO", "HU", "CZ", "AT", "CH", "IE", "JP",
    "KR", "CN", "TW", "HK", "MX", "CA", "AU", "NZ", "ZA", "AE",
    "SA", "EG", "MA", "UA", "BG", "HR", "RS", "SK", "SI", "LT",
    "LV", "EE", "IL",
    // Language codes used as playlist buckets
    "EN", "JA", "KO", "ZH", "SV", "DA", "EL", "CS", "HI", "HE",
    "FA", "AF",
)

private val COUNTRY_ALIASES = mapOf("GB" to "UK", "USA" to "US")

// When rendering a flag for a language/bucket code that isn't itself a valid
// regional-indicator country, substitute the most-representative country flag.
// "EN" → UK flag, "JA" → JP, etc. Keeps sidebar labels honest ("EN") while
// producing a recognisable flag glyph.
private val FLAG_SUBSTITUTES = mapOf(
    "EN" to "GB",
    "JA" to "JP",
    "KO" to "KR",
    "ZH" to "CN",
    "SV" to "SE",
    "DA" to "DK",
    "EL" to "GR",
    "CS" to "CZ",
    "HI" to "IN",
    "HE" to "IL",
    "FA" to "IR",
    "AR" to "SA",
    "AF" to "ZA",
)

fun countryFlag(code: String?): String {
    if (code.isNullOrBlank() || code.length != 2) return "🌐"
    val effective = FLAG_SUBSTITUTES[code.uppercase()] ?: code.uppercase()
    return effective.map { ch ->
        val off = 0x1F1E6 + (ch.code - 'A'.code)
        String(Character.toChars(off))
    }.joinToString("")
}

/** Human-readable country / language-bucket name. Matches the mockup
 *  ("NL Netherlands", "US United States", …). */
private val COUNTRY_NAMES = mapOf(
    "NL" to "Netherlands", "UK" to "UK", "GB" to "UK",
    "US" to "USA", "DE" to "Germany", "FR" to "France",
    "IT" to "Italy", "ES" to "Spain", "PT" to "Portugal", "BE" to "Belgium",
    "TR" to "Turkey", "IN" to "India", "BR" to "Brazil", "PL" to "Poland",
    "SE" to "Sweden", "DK" to "Denmark", "NO" to "Norway", "FI" to "Finland",
    "RU" to "Russia", "GR" to "Greece", "RO" to "Romania", "HU" to "Hungary",
    "CZ" to "Czechia", "AT" to "Austria", "CH" to "Switzerland",
    "IE" to "Ireland", "JP" to "Japan", "KR" to "South Korea", "CN" to "China",
    "TW" to "Taiwan", "HK" to "Hong Kong", "MX" to "Mexico", "CA" to "Canada",
    "AU" to "Australia", "NZ" to "New Zealand", "ZA" to "South Africa",
    "AE" to "UAE", "SA" to "Saudi Arabia", "EG" to "Egypt", "MA" to "Morocco",
    "UA" to "Ukraine", "BG" to "Bulgaria", "HR" to "Croatia", "RS" to "Serbia",
    "SK" to "Slovakia", "SI" to "Slovenia", "LT" to "Lithuania",
    "LV" to "Latvia", "EE" to "Estonia", "IL" to "Israel",
    // Language buckets
    "EN" to "English", "JA" to "Japanese", "KO" to "Korean", "ZH" to "Chinese",
    "AR" to "Arabic", "SV" to "Swedish", "DA" to "Danish", "EL" to "Greek",
    "CS" to "Czech", "HI" to "Hindi", "HE" to "Hebrew", "FA" to "Persian",
    "AF" to "South Africa",
)

fun countryName(code: String): String = COUNTRY_NAMES[code.uppercase()] ?: code

/** Parse a genre out of any slice of text (group name, channel name, etc). */
fun genreFromText(text: String): Genre {
    val t = text.lowercase()
    return when {
        "sport" in t || "espn" in t || "uefa" in t -> Genre.Sports
        "movie" in t || "cinema" in t || "film" in t -> Genre.Movies
        "series" in t || "show" in t -> Genre.Series
        "news" in t || "cnn" in t || "bbc news" in t -> Genre.News
        "kids" in t || "cartoon" in t || "child" in t || "family" in t -> Genre.Kids
        "music" in t || "mtv" in t || "hits" in t -> Genre.Music
        "doc" in t || "history" in t || "discovery" in t || "nat geo" in t -> Genre.Docs
        else -> Genre.General
    }
}

/** Parse a quality tier out of channel/group name. */
fun qualityFromText(text: String): Quality {
    val t = text.uppercase()
    return when {
        "4K" in t || "UHD" in t || "2160" in t -> Quality.K4
        "FHD" in t || "1080" in t -> Quality.FHD
        "HD" in t || "720" in t -> Quality.HD
        else -> Quality.SD
    }
}

/** Extract a 2-letter bucket code from a group/channel name. Accepts ISO
 *  country codes or language prefixes (EN, JA, …) commonly used in IPTV. */
fun countryFromText(text: String): String? {
    val tokens = text.split(TAG_RE).map { it.trim().trim('[', ']', '(', ')') }
    for (tok in tokens) {
        if (tok.length in 2..3) {
            val up = tok.uppercase()
            val canonical = COUNTRY_ALIASES[up] ?: up
            if (canonical.length == 2 && canonical in KNOWN_COUNTRIES) return canonical
        }
    }
    // Fallback: first two/three chars of the string — handles "UK|Sports"
    // and similar no-delimiter prefixes.
    val head = text.trim().take(3).uppercase()
    val two = head.take(2)
    if (two in KNOWN_COUNTRIES) return COUNTRY_ALIASES[two] ?: two
    if (head in COUNTRY_ALIASES) return COUNTRY_ALIASES[head]
    return null
}

fun isAdultGroup(group: String, name: String): Boolean {
    val t = (group + " " + name).lowercase()
    return ADULT_KEYWORDS.any { it in t }
}

fun brandForGenre(genre: Genre): LiveColors.Brand = when (genre) {
    Genre.News    -> LiveColors.BrandNews
    Genre.Sports  -> LiveColors.BrandSport
    Genre.Movies  -> LiveColors.BrandMovies
    Genre.Series  -> LiveColors.BrandSeries
    Genre.Kids    -> LiveColors.BrandKids
    Genre.Music   -> LiveColors.BrandMusic
    Genre.Docs    -> LiveColors.BrandDocs
    Genre.General -> LiveColors.BrandGeneral
}

private fun IptvChannel.traits(): ChannelTraits {
    val combined = "$group | $name"
    val country = countryFromText(group) ?: countryFromText(name)
    val genre = genreFromText(combined)
    val quality = qualityFromText(name).takeUnless { it == Quality.SD } ?: qualityFromText(group)
    val lang = country ?: "EN"
    val brand = brandForGenre(genre)
    return ChannelTraits(
        country = country,
        genre = genre,
        quality = quality,
        lang = lang,
        brandBg = brand.bg,
        brandFg = brand.fg,
        isAdult = isAdultGroup(group, name),
    )
}

fun IptvChannel.enrich(number: Int): EnrichedChannel {
    val traits = traits()
    return EnrichedChannel(
        source = this,
        number = number,
        country = traits.country,
        genre = traits.genre,
        quality = traits.quality,
        lang = traits.lang,
        brandBg = traits.brandBg,
        brandFg = traits.brandFg,
        isAdult = traits.isAdult,
    )
}

// ─────────────────────────────────────────────────────────────────────────
// Category tree (spec §5)
// ─────────────────────────────────────────────────────────────────────────

fun IptvChannel.enrichForFastStartup(number: Int): EnrichedChannel {
    val brand = brandForGenre(Genre.General)
    return EnrichedChannel(
        source = this,
        number = number,
        country = null,
        genre = Genre.General,
        quality = Quality.SD,
        lang = "EN",
        brandBg = brand.bg,
        brandFg = brand.fg,
        isAdult = isAdultGroup(group, name),
    )
}

data class LiveCategory(
    val id: String,
    val label: String,
    val count: Int,
    val iconToken: CategoryIcon,
    val flagEmoji: String? = null,
    val children: List<LiveCategory> = emptyList(),
    val playlistGroupName: String? = null,
) {
    val isGroup: Boolean get() = children.isNotEmpty()
}

enum class CategoryIcon { Favorite, Recent, All, Grid, Sport, Movie, News, Kids, Docs, Music, Lock, Country, SubEntry }

data class LiveSection(val id: String, val label: String, val categories: List<LiveCategory>)

data class LiveCategoryTree(
    val top: List<LiveCategory>,
    val global: LiveSection,
    val countries: LiveSection,
    val adult: LiveSection,
    val hidden: LiveSection = LiveSection("hidden", "HIDDEN", emptyList()),
) {
    val allSections: List<LiveSection> = listOf(global, countries, adult)
    fun byId(id: String): LiveCategory? {
        fun findIn(category: LiveCategory): LiveCategory? {
            if (category.id == id) return category
            category.children.forEach { child ->
                findIn(child)?.let { return it }
            }
            return null
        }
        top.forEach { category ->
            findIn(category)?.let { return it }
        }
        for (section in allSections) {
            for (cat in section.categories) {
                findIn(cat)?.let { return it }
            }
        }
        return null
    }
}

private fun playlistGroupLabel(group: String): String {
    return group.trim().ifBlank { "Ungrouped" }
}

fun playlistGroupCategoryId(group: String): String {
    val normalized = playlistGroupLabel(group).lowercase()
    return "grp:${normalized.hashCode().toUInt().toString(16)}"
}

/**
 * Build the category tree from a list of enriched channels. Counts are
 * computed up front so the sidebar can render without filtering again.
 */
fun buildCategoryTree(
    channels: List<EnrichedChannel>,
    favoritesCount: Int,
    recentCount: Int,
    hiddenGroups: Set<String> = emptySet(),
    groupOrder: List<String> = emptyList(),
): LiveCategoryTree {
    data class CountryAccumulator(
        var total: Int = 0,
        var general: Int = 0,
        var k4: Int = 0,
        var fhd: Int = 0,
        var sports: Int = 0,
        var movies: Int = 0,
        var news: Int = 0,
        var kids: Int = 0,
        var series: Int = 0,
        var docs: Int = 0,
    )

    var allCount = 0
    var adultCount = 0
    var ultraHdCount = 0
    var sportsCount = 0
    var moviesCount = 0
    var newsCount = 0
    var kidsCount = 0
    var docsCount = 0
    var musicCount = 0
    val countryAccumulators = LinkedHashMap<String, CountryAccumulator>()
    val playlistGroupCounts = LinkedHashMap<String, Pair<String, Int>>()
    val hiddenPlaylistGroupCounts = LinkedHashMap<String, Pair<String, Int>>()
    val hiddenPlaylistGroups = hiddenGroups.mapTo(HashSet()) { playlistGroupLabel(it) }

    channels.forEach { channel ->
        val groupLabel = playlistGroupLabel(channel.source.group)
        val groupId = playlistGroupCategoryId(channel.source.group)
        val targetCounts = if (groupLabel in hiddenPlaylistGroups) hiddenPlaylistGroupCounts else playlistGroupCounts
        val groupCount = targetCounts[groupId]?.second ?: 0
        targetCounts[groupId] = groupLabel to (groupCount + 1)

        if (channel.isAdult) {
            adultCount += 1
            return@forEach
        }

        allCount += 1
        when (channel.quality) {
            Quality.K4 -> ultraHdCount += 1
            else -> Unit
        }
        when (channel.genre) {
            Genre.Sports -> sportsCount += 1
            Genre.Movies -> moviesCount += 1
            Genre.News -> newsCount += 1
            Genre.Kids -> kidsCount += 1
            Genre.Docs -> docsCount += 1
            Genre.Music -> musicCount += 1
            else -> Unit
        }

        val countryCode = channel.country
        if (!countryCode.isNullOrBlank()) {
            val country = countryAccumulators.getOrPut(countryCode) { CountryAccumulator() }
            country.total += 1
            when (channel.genre) {
                Genre.General -> country.general += 1
                Genre.Sports -> country.sports += 1
                Genre.Movies -> country.movies += 1
                Genre.News -> country.news += 1
                Genre.Kids -> country.kids += 1
                Genre.Series -> country.series += 1
                Genre.Docs -> country.docs += 1
                else -> Unit
            }
            when (channel.quality) {
                Quality.K4 -> country.k4 += 1
                Quality.FHD -> country.fhd += 1
                else -> Unit
            }
        }
    }

    val autoGlobal = listOf(
        LiveCategory("g-4k",     "4K | Ultra HD",      ultraHdCount, CategoryIcon.Grid),
        LiveCategory("g-sports", "Sports · Global",    sportsCount, CategoryIcon.Sport),
        LiveCategory("g-movies", "Movies · Global",    moviesCount, CategoryIcon.Movie),
        LiveCategory("g-news",   "News · Global",      newsCount,   CategoryIcon.News),
        LiveCategory("g-kids",   "Kids · Global",      kidsCount,   CategoryIcon.Kids),
        LiveCategory("g-docs",   "Documentary",        docsCount,   CategoryIcon.Docs),
        LiveCategory("g-music",  "Music",              musicCount,  CategoryIcon.Music),
    ).filter { it.count > 0 }

    val countryCategories = countryAccumulators
        .entries
        .sortedByDescending { it.value.total }
        .map { (code, counts) ->
            val subs = buildList {
                fun addChild(tag: String, count: Int) {
                    if (count <= 0) return
                    add(
                        LiveCategory(
                            id = "$code-$tag",
                            label = "$code | ${tag.replaceFirstChar(Char::uppercase)}",
                            count = count,
                            iconToken = CategoryIcon.SubEntry,
                        )
                    )
                }
                addChild("general", counts.general)
                addChild("4k", counts.k4)
                addChild("fhd", counts.fhd)
                addChild("sports", counts.sports)
                addChild("movies", counts.movies)
                addChild("news", counts.news)
                addChild("kids", counts.kids)
                addChild("entertainment", counts.series)
                addChild("documentary", counts.docs)
            }
            LiveCategory(
                id = code,
                label = countryName(code),
                count = counts.total,
                iconToken = CategoryIcon.Country,
                flagEmoji = countryFlag(code),
                children = subs,
            )
        }

    val adultCategories = listOf(
        LiveCategory("adult", "Adult", adultCount, CategoryIcon.Lock),
    ).filter { it.count > 0 }
    val top = listOf(
        LiveCategory("fav", "Favorites", favoritesCount, CategoryIcon.Favorite),
        LiveCategory("recent", "Recent", recentCount, CategoryIcon.Recent),
        LiveCategory(
            id = "all",
            label = "All Channels",
            count = allCount,
            iconToken = CategoryIcon.All,
            children = autoGlobal + countryCategories + adultCategories,
        ),
    )
    val playlistGroups = orderPlaylistGroups(playlistGroupCounts, groupOrder).map { (id, value) ->
        LiveCategory(id, value.first, value.second, CategoryIcon.Grid, playlistGroupName = value.first)
    }
    val global = LiveSection("playlist", "PLAYLIST", playlistGroups)
    val countries = LiveSection("matched", "MATCHED", emptyList())
    val adult = LiveSection("adult", "ADULT", emptyList())
    val hidden = LiveSection("hidden", "HIDDEN", emptyList())

    return LiveCategoryTree(top = top, global = global, countries = countries, adult = adult, hidden = hidden)
}

fun buildCategoryTree(
    channels: List<IptvChannel>,
    favorites: Set<String>,
    recents: Set<String>,
    hiddenGroups: Set<String> = emptySet(),
    groupOrder: List<String> = emptyList(),
): LiveCategoryTree {
    data class RawCountryAccumulator(
        var total: Int = 0,
        var general: Int = 0,
        var k4: Int = 0,
        var fhd: Int = 0,
        var sports: Int = 0,
        var movies: Int = 0,
        var news: Int = 0,
        var kids: Int = 0,
        var series: Int = 0,
        var docs: Int = 0,
    )

    var allCount = 0
    var adultCount = 0
    var ultraHdCount = 0
    var sportsCount = 0
    var moviesCount = 0
    var newsCount = 0
    var kidsCount = 0
    var docsCount = 0
    var musicCount = 0
    val countryAccumulators = LinkedHashMap<String, RawCountryAccumulator>()
    val playlistGroupCounts = LinkedHashMap<String, Pair<String, Int>>()
    val hiddenPlaylistGroupCounts = LinkedHashMap<String, Pair<String, Int>>()
    val hiddenPlaylistGroups = hiddenGroups.mapTo(HashSet()) { playlistGroupLabel(it) }

    channels.forEach { channel ->
        val traits = channel.traits()
        val groupLabel = playlistGroupLabel(channel.group)
        val groupId = playlistGroupCategoryId(channel.group)
        val targetCounts = if (groupLabel in hiddenPlaylistGroups) hiddenPlaylistGroupCounts else playlistGroupCounts
        val groupCount = targetCounts[groupId]?.second ?: 0
        targetCounts[groupId] = groupLabel to (groupCount + 1)

        if (traits.isAdult) {
            adultCount += 1
            return@forEach
        }

        allCount += 1
        if (traits.quality == Quality.K4) ultraHdCount += 1
        when (traits.genre) {
            Genre.Sports -> sportsCount += 1
            Genre.Movies -> moviesCount += 1
            Genre.News -> newsCount += 1
            Genre.Kids -> kidsCount += 1
            Genre.Docs -> docsCount += 1
            Genre.Music -> musicCount += 1
            else -> Unit
        }

        val countryCode = traits.country
        if (!countryCode.isNullOrBlank()) {
            val country = countryAccumulators.getOrPut(countryCode) { RawCountryAccumulator() }
            country.total += 1
            when (traits.genre) {
                Genre.General -> country.general += 1
                Genre.Sports -> country.sports += 1
                Genre.Movies -> country.movies += 1
                Genre.News -> country.news += 1
                Genre.Kids -> country.kids += 1
                Genre.Series -> country.series += 1
                Genre.Docs -> country.docs += 1
                else -> Unit
            }
            when (traits.quality) {
                Quality.K4 -> country.k4 += 1
                Quality.FHD -> country.fhd += 1
                else -> Unit
            }
        }
    }

    val autoGlobal = listOf(
        LiveCategory("g-4k", "4K | Ultra HD", ultraHdCount, CategoryIcon.Grid),
        LiveCategory("g-sports", "Sports · Global", sportsCount, CategoryIcon.Sport),
        LiveCategory("g-movies", "Movies · Global", moviesCount, CategoryIcon.Movie),
        LiveCategory("g-news", "News · Global", newsCount, CategoryIcon.News),
        LiveCategory("g-kids", "Kids · Global", kidsCount, CategoryIcon.Kids),
        LiveCategory("g-docs", "Documentary", docsCount, CategoryIcon.Docs),
        LiveCategory("g-music", "Music", musicCount, CategoryIcon.Music),
    ).filter { it.count > 0 }

    val countryCategories = countryAccumulators
        .entries
        .sortedByDescending { it.value.total }
        .map { (code, counts) ->
            val subs = buildList {
                fun addChild(tag: String, count: Int) {
                    if (count <= 0) return
                    add(
                        LiveCategory(
                            id = "$code-$tag",
                            label = "$code | ${tag.replaceFirstChar(Char::uppercase)}",
                            count = count,
                            iconToken = CategoryIcon.SubEntry,
                        )
                    )
                }
                addChild("general", counts.general)
                addChild("4k", counts.k4)
                addChild("fhd", counts.fhd)
                addChild("sports", counts.sports)
                addChild("movies", counts.movies)
                addChild("news", counts.news)
                addChild("kids", counts.kids)
                addChild("entertainment", counts.series)
                addChild("documentary", counts.docs)
            }
            LiveCategory(
                id = code,
                label = countryName(code),
                count = counts.total,
                iconToken = CategoryIcon.Country,
                flagEmoji = countryFlag(code),
                children = subs,
            )
        }

    val adultCategories = listOf(LiveCategory("adult", "Adult", adultCount, CategoryIcon.Lock)).filter { it.count > 0 }
    val channelIds = channels.asSequence().map { it.id }.toHashSet()
    val top = listOf(
        LiveCategory("fav", "Favorites", favorites.count { it in channelIds }, CategoryIcon.Favorite),
        LiveCategory("recent", "Recent", recents.count { it in channelIds }, CategoryIcon.Recent),
        LiveCategory(
            id = "all",
            label = "All Channels",
            count = allCount,
            iconToken = CategoryIcon.All,
            children = autoGlobal + countryCategories + adultCategories,
        ),
    )
    val playlistGroups = orderPlaylistGroups(playlistGroupCounts, groupOrder).map { (id, value) ->
        LiveCategory(id, value.first, value.second, CategoryIcon.Grid, playlistGroupName = value.first)
    }
    val global = LiveSection("playlist", "PLAYLIST", playlistGroups)
    val countries = LiveSection("matched", "MATCHED", emptyList())
    val adult = LiveSection("adult", "ADULT", emptyList())
    val hidden = LiveSection("hidden", "HIDDEN", emptyList())

    return LiveCategoryTree(top = top, global = global, countries = countries, adult = adult, hidden = hidden)
}

private fun orderPlaylistGroups(
    groups: LinkedHashMap<String, Pair<String, Int>>,
    groupOrder: List<String>,
): List<Map.Entry<String, Pair<String, Int>>> {
    if (groups.isEmpty()) return emptyList()
    if (groupOrder.isEmpty()) return groups.entries.toList()
    val orderMap = groupOrder
        .map(::playlistGroupLabel)
        .withIndex()
        .associate { (index, groupName) -> groupName to index }
    return groups.entries.sortedWith(
        compareBy<Map.Entry<String, Pair<String, Int>>> { entry -> orderMap[entry.value.first] ?: Int.MAX_VALUE }
            .thenBy { entry -> groups.keys.indexOf(entry.key) }
    )
}

private fun rawCategoryMatcher(
    categoryId: String,
    favorites: Set<String>,
    recents: Set<String>,
): (IptvChannel) -> Boolean {
    if (categoryId == "all") return { ch -> !ch.traits().isAdult }
    if (categoryId == "fav") return { ch -> ch.id in favorites && !ch.traits().isAdult }
    if (categoryId == "recent") return { ch -> ch.id in recents && !ch.traits().isAdult }
    if (categoryId == "adult") return { ch -> ch.traits().isAdult }
    if (categoryId.startsWith("grp:")) return { ch -> playlistGroupCategoryId(ch.group) == categoryId }
    if (categoryId == "g-4k") return { ch -> ch.traits().let { !it.isAdult && it.quality == Quality.K4 } }
    if (categoryId == "g-sports") return { ch -> ch.traits().let { !it.isAdult && it.genre == Genre.Sports } }
    if (categoryId == "g-movies") return { ch -> ch.traits().let { !it.isAdult && it.genre == Genre.Movies } }
    if (categoryId == "g-news") return { ch -> ch.traits().let { !it.isAdult && it.genre == Genre.News } }
    if (categoryId == "g-kids") return { ch -> ch.traits().let { !it.isAdult && it.genre == Genre.Kids } }
    if (categoryId == "g-docs") return { ch -> ch.traits().let { !it.isAdult && it.genre == Genre.Docs } }
    if (categoryId == "g-music") return { ch -> ch.traits().let { !it.isAdult && it.genre == Genre.Music } }
    if (categoryId.length == 2 && categoryId.all { it.isUpperCase() }) {
        return { ch -> ch.traits().let { !it.isAdult && it.country == categoryId } }
    }
    if ("-" in categoryId) {
        val parts = categoryId.split("-", limit = 2)
        val cc = parts.getOrNull(0).orEmpty()
        val tag = parts.getOrNull(1).orEmpty()
        return { ch: IptvChannel ->
            val traits = ch.traits()
            if (traits.country != cc || traits.isAdult) {
                false
            } else {
                when (tag) {
                    "general" -> traits.genre == Genre.General
                    "4k" -> traits.quality == Quality.K4
                    "fhd" -> traits.quality == Quality.FHD
                    "sports" -> traits.genre == Genre.Sports
                    "movies" -> traits.genre == Genre.Movies
                    "news" -> traits.genre == Genre.News
                    "kids" -> traits.genre == Genre.Kids
                    "entertainment" -> traits.genre == Genre.Series
                    "documentary" -> traits.genre == Genre.Docs
                    else -> false
                }
            }
        }
    }
    return { ch -> !ch.traits().isAdult }
}

fun buildInitialCategoryChannels(
    channels: List<IptvChannel>,
    categoryId: String,
    favorites: Set<String>,
    recents: Set<String>,
    limit: Int,
): List<EnrichedChannel> {
    if (channels.isEmpty() || limit <= 0) return emptyList()
    if (categoryId == "all") {
        return buildList(limit.coerceAtMost(channels.size)) {
            channels.forEachIndexed { index, channel ->
                if (!isAdultGroup(channel.group, channel.name)) {
                    add(channel.enrichForFastStartup(100 + index))
                    if (size >= limit) return@buildList
                }
            }
        }
    }
    if (categoryId == "fav") {
        return buildList(limit.coerceAtMost(favorites.size)) {
            channels.forEachIndexed { index, channel ->
                if (channel.id in favorites && !isAdultGroup(channel.group, channel.name)) {
                    add(channel.enrichForFastStartup(100 + index))
                    if (size >= limit) return@buildList
                }
            }
        }
    }
    if (categoryId == "recent") {
        val indexById = channels.withIndex().associate { (index, channel) -> channel.id to (index to channel) }
        return buildList(limit.coerceAtMost(recents.size)) {
            recents.toList().asReversed().forEach { id ->
                val (index, channel) = indexById[id] ?: return@forEach
                if (!isAdultGroup(channel.group, channel.name)) {
                    add(channel.enrichForFastStartup(100 + index))
                    if (size >= limit) return@buildList
                }
            }
        }
    }
    val matcher = rawCategoryMatcher(categoryId, favorites, recents)
    return buildList(limit.coerceAtMost(channels.size)) {
        channels.forEachIndexed { index, channel ->
            if (matcher(channel)) {
                add(channel.enrich(100 + index))
                if (size >= limit) return@buildList
            }
        }
    }
}

fun buildCategoryIndex(channels: List<EnrichedChannel>): LiveCategoryIndex {
    if (channels.isEmpty()) return LiveCategoryIndex.Empty

    val byId = LinkedHashMap<String, EnrichedChannel>(channels.size)
    val buckets = LinkedHashMap<String, MutableList<EnrichedChannel>>()

    fun add(categoryId: String, channel: EnrichedChannel) {
        buckets.getOrPut(categoryId) { ArrayList() }.add(channel)
    }

    channels.forEach { channel ->
        byId[channel.id] = channel
        add(playlistGroupCategoryId(channel.source.group), channel)
        if (channel.isAdult) {
            add("adult", channel)
            return@forEach
        }

        add("all", channel)

        if (channel.quality == Quality.K4) add("g-4k", channel)
        when (channel.genre) {
            Genre.Sports -> add("g-sports", channel)
            Genre.Movies -> add("g-movies", channel)
            Genre.News -> add("g-news", channel)
            Genre.Kids -> add("g-kids", channel)
            Genre.Docs -> add("g-docs", channel)
            Genre.Music -> add("g-music", channel)
            else -> Unit
        }

        val country = channel.country
        if (!country.isNullOrBlank()) {
            add(country, channel)
            when (channel.genre) {
                Genre.General -> add("$country-general", channel)
                Genre.Sports -> add("$country-sports", channel)
                Genre.Movies -> add("$country-movies", channel)
                Genre.News -> add("$country-news", channel)
                Genre.Kids -> add("$country-kids", channel)
                Genre.Series -> add("$country-entertainment", channel)
                Genre.Docs -> add("$country-documentary", channel)
                else -> Unit
            }
            when (channel.quality) {
                Quality.K4 -> add("$country-4k", channel)
                Quality.FHD -> add("$country-fhd", channel)
                else -> Unit
            }
        }
    }

    return LiveCategoryIndex(
        byCategory = buckets.mapValues { (_, value) -> value.toList() },
        byId = byId,
    )
}

fun bestCategoryIdForChannel(
    channel: EnrichedChannel,
    tree: LiveCategoryTree,
): String {
    val playlistGroupId = playlistGroupCategoryId(channel.source.group)
    if (tree.byId(playlistGroupId) != null) return playlistGroupId
    if (channel.isAdult) return "adult"

    val countryId = channel.country
    if (!countryId.isNullOrBlank()) {
        val countryCategory = tree.countries.categories.firstOrNull { it.id == countryId }
        val childId = countryCategory
            ?.children
            ?.firstOrNull { child -> matchesCategoryId(channel, child.id) }
            ?.id
        if (childId != null) return childId
        return countryId
    }

    val globalId = tree.global.categories
        .firstOrNull { global -> matchesCategoryId(channel, global.id) }
        ?.id
    return globalId ?: "all"
}

/** Returns a predicate matching channels for [categoryId]. Implements spec §5. */
fun categoryMatcher(
    categoryId: String,
    favorites: Set<String>,
    recents: Set<String>,
): (EnrichedChannel) -> Boolean {
    return when {
        categoryId == "all"    -> { ch -> !ch.isAdult }
        categoryId == "fav"    -> { ch -> ch.id in favorites && !ch.isAdult }
        categoryId == "recent" -> { ch -> ch.id in recents && !ch.isAdult }
        categoryId == "adult"  -> { ch -> ch.isAdult }
        categoryId.startsWith("grp:") -> { ch -> playlistGroupCategoryId(ch.source.group) == categoryId }
        categoryId == "g-4k"      -> { ch -> ch.quality == Quality.K4 && !ch.isAdult }
        categoryId == "g-sports"  -> { ch -> ch.genre == Genre.Sports && !ch.isAdult }
        categoryId == "g-movies"  -> { ch -> ch.genre == Genre.Movies && !ch.isAdult }
        categoryId == "g-news"    -> { ch -> ch.genre == Genre.News && !ch.isAdult }
        categoryId == "g-kids"    -> { ch -> ch.genre == Genre.Kids && !ch.isAdult }
        categoryId == "g-docs"    -> { ch -> ch.genre == Genre.Docs && !ch.isAdult }
        categoryId == "g-music"   -> { ch -> ch.genre == Genre.Music && !ch.isAdult }
        categoryId.length == 2 && categoryId.all { it.isUpperCase() } ->
            { ch -> ch.country == categoryId && !ch.isAdult }
        "-" in categoryId -> {
            val (cc, tag) = categoryId.split("-", limit = 2)
            val (genre, quality) = when (tag) {
                "4k"  -> null to Quality.K4
                "fhd" -> null to Quality.FHD
                "sports" -> Genre.Sports to null
                "movies" -> Genre.Movies to null
                "news"   -> Genre.News to null
                "kids"   -> Genre.Kids to null
                "entertainment" -> Genre.Series to null
                "documentary"   -> Genre.Docs to null
                "general" -> Genre.General to null
                else -> null to null
            }
            { ch -> ch.country == cc && !ch.isAdult &&
                (genre == null || ch.genre == genre) &&
                (quality == null || ch.quality == quality) }
        }
        else -> { _ -> false }
    }
}

private fun matchesCategoryId(channel: EnrichedChannel, categoryId: String): Boolean =
    when (categoryId) {
        "adult" -> channel.isAdult
        "g-4k" -> !channel.isAdult && channel.quality == Quality.K4
        "g-sports" -> !channel.isAdult && channel.genre == Genre.Sports
        "g-movies" -> !channel.isAdult && channel.genre == Genre.Movies
        "g-news" -> !channel.isAdult && channel.genre == Genre.News
        "g-kids" -> !channel.isAdult && channel.genre == Genre.Kids
        "g-docs" -> !channel.isAdult && channel.genre == Genre.Docs
        "g-music" -> !channel.isAdult && channel.genre == Genre.Music
        else -> {
            val parts = categoryId.split("-", limit = 2)
            if (parts.size != 2 || parts[0] != channel.country || channel.isAdult) {
                false
            } else {
                when (parts[1]) {
                    "4k" -> channel.quality == Quality.K4
                    "fhd" -> channel.quality == Quality.FHD
                    "sports" -> channel.genre == Genre.Sports
                    "movies" -> channel.genre == Genre.Movies
                    "news" -> channel.genre == Genre.News
                    "kids" -> channel.genre == Genre.Kids
                    "entertainment" -> channel.genre == Genre.Series
                    "documentary" -> channel.genre == Genre.Docs
                    "general" -> channel.genre == Genre.General
                    else -> false
                }
            }
        }
    }
