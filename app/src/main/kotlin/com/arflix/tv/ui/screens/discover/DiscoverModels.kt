package com.arflix.tv.ui.screens.discover

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit

enum class DiscoverMediaMode {
    MOVIES,
    SERIES
}

data class DiscoverSectionSpec(
    val id: String,
    val title: String,
    val subtitle: String,
    val catalogIds: List<String>,
    val accent: Long
)

data class DiscoverUiSection(
    val id: String,
    val title: String,
    val subtitle: String,
    val accent: Long,
    val cards: List<DiscoverUiCard>
)

data class DiscoverUiCard(
    val catalogId: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String?,
    val backdropUrl: String? = null,
    val accent: Long,
    val sourceLabel: String,
    val mediaType: MediaType? = null,
    val mediaId: Int? = null,
    val initialSeason: Int? = null,
    val initialEpisode: Int? = null,
    val year: String = "",
    val genreIds: List<Int> = emptyList(),
    val originalLanguage: String? = null,
    val progress: Int = 0
) {
    val isMediaTitle: Boolean
        get() = mediaType != null && mediaId != null
}

fun buildDiscoverUiSections(
    specs: List<DiscoverSectionSpec>,
    catalogs: List<CatalogConfig>,
    mode: DiscoverMediaMode? = null
): List<DiscoverUiSection> {
    val catalogsById = catalogs.associateBy { it.id }
    return specs.mapNotNull { spec ->
        val cards = spec.catalogIds.mapNotNull { catalogId ->
            val catalog = catalogsById[catalogId] ?: return@mapNotNull null
            DiscoverUiCard(
                catalogId = catalog.id,
                title = catalog.title,
                subtitle = catalog.collectionDescription
                    ?.takeIf { it.isNotBlank() }
                    ?.forDiscoverMode(mode)
                    ?: spec.subtitle,
                imageUrl = catalog.collectionHeroImageUrl
                    ?: catalog.collectionHeroGifUrl
                    ?: catalog.collectionCoverImageUrl
                    ?: catalog.collectionFocusGifUrl,
                backdropUrl = catalog.collectionHeroImageUrl ?: catalog.collectionHeroGifUrl,
                accent = spec.accent,
                sourceLabel = catalog.sourceType.discoverSourceLabel()
            )
        }
        if (cards.isEmpty()) {
            null
        } else {
            DiscoverUiSection(
                id = spec.id,
                title = spec.title,
                subtitle = spec.subtitle,
                accent = spec.accent,
                cards = cards
            )
        }
    }
}

fun buildContinueWatchingDiscoverSection(
    mode: DiscoverMediaMode,
    items: List<MediaItem>
): DiscoverUiSection? {
    return buildMediaDiscoverSection(
        id = when (mode) {
            DiscoverMediaMode.MOVIES -> "personal_continue_movies"
            DiscoverMediaMode.SERIES -> "personal_continue_series"
        },
        title = "Fortsätt där du slutade",
        subtitle = when (mode) {
            DiscoverMediaMode.MOVIES -> "Filmer du redan har startat, med rätt resume-position."
            DiscoverMediaMode.SERIES -> "Serier och nästa avsnitt du redan är igång med."
        },
        accent = 0xFFFFB000,
        sourceLabel = "Resume",
        items = items,
        mode = mode
    )
}

fun buildBecauseYouWatchedDiscoverSection(
    mode: DiscoverMediaMode,
    seedTitle: String,
    items: List<MediaItem>
): DiscoverUiSection? {
    val cleanTitle = seedTitle.trim().takeIf { it.isNotBlank() } ?: return null
    return buildMediaDiscoverSection(
        id = when (mode) {
            DiscoverMediaMode.MOVIES -> "personal_similar_movies"
            DiscoverMediaMode.SERIES -> "personal_similar_series"
        },
        title = "Liknande $cleanTitle",
        subtitle = "Rekommendationer baserat på det du nyss tittade på.",
        accent = 0xFF7DD3FC,
        sourceLabel = "TMDB",
        items = items,
        mode = mode
    )
}

fun buildCatalogMediaDiscoverSection(
    mode: DiscoverMediaMode,
    catalogId: String,
    title: String,
    subtitle: String,
    accent: Long,
    sourceLabel: String,
    items: List<MediaItem>,
    maxItems: Int = 14
): DiscoverUiSection? {
    return buildMediaDiscoverSection(
        id = "title_row_$catalogId",
        title = title,
        subtitle = subtitle,
        accent = accent,
        sourceLabel = sourceLabel,
        items = items,
        mode = mode,
        maxItems = maxItems
    )
}

private fun buildMediaDiscoverSection(
    id: String,
    title: String,
    subtitle: String,
    accent: Long,
    sourceLabel: String,
    items: List<MediaItem>,
    mode: DiscoverMediaMode,
    maxItems: Int = 12
): DiscoverUiSection? {
    val targetType = when (mode) {
        DiscoverMediaMode.MOVIES -> MediaType.MOVIE
        DiscoverMediaMode.SERIES -> MediaType.TV
    }
    val cards = items
        .asSequence()
        .filter { it.mediaType == targetType }
        .filter { it.id > 0 && it.title.isNotBlank() }
        .distinctBy { "${it.mediaType}:${it.id}" }
        .take(maxItems)
        .map { item ->
            DiscoverUiCard(
                catalogId = "media:${item.mediaType.name.lowercase()}:${item.id}",
                title = item.title,
                subtitle = item.discoverSubtitle(fallback = subtitle),
                imageUrl = item.image.takeIf { it.isNotBlank() } ?: item.backdrop?.takeIf { it.isNotBlank() },
                backdropUrl = item.backdrop?.takeIf { it.isNotBlank() },
                accent = accent,
                sourceLabel = sourceLabel,
                mediaType = item.mediaType,
                mediaId = item.id,
                initialSeason = item.nextEpisode?.seasonNumber,
                initialEpisode = item.nextEpisode?.episodeNumber,
                year = item.year,
                genreIds = item.genreIds,
                originalLanguage = item.originalLanguage,
                progress = item.progress.coerceIn(0, 100)
            )
        }
        .toList()

    return if (cards.isEmpty()) {
        null
    } else {
        DiscoverUiSection(
            id = id,
            title = title,
            subtitle = subtitle,
            accent = accent,
            cards = cards
        )
    }
}

private fun MediaItem.discoverSubtitle(fallback: String): String {
    val parts = buildList {
        subtitle.takeIf { it.isNotBlank() }?.let(::add)
        year.takeIf { it.isNotBlank() && !subtitle.contains(it) }?.let(::add)
        imdbRating.takeIf { it.isNotBlank() }?.let { add("IMDb $it") }
        if (progress in 1..99) add("$progress%")
    }
    return parts.joinToString(" • ").ifBlank { fallback }
}

private fun String.forDiscoverMode(mode: DiscoverMediaMode?): String {
    return when (mode) {
        DiscoverMediaMode.MOVIES -> replace(Regex("movies\\s*(and|&)\\s*series", RegexOption.IGNORE_CASE), "movies")
        DiscoverMediaMode.SERIES -> replace(Regex("movies\\s*(and|&)\\s*series", RegexOption.IGNORE_CASE), "series")
        null -> this
    }
}

private fun CatalogSourceType.discoverSourceLabel(): String {
    return when (this) {
        CatalogSourceType.TRAKT -> "Trakt"
        CatalogSourceType.MDBLIST -> "MDBList"
        CatalogSourceType.ADDON -> "Addon"
        CatalogSourceType.HOME_SERVER -> "Server"
        CatalogSourceType.PREINSTALLED -> "Majo"
    }
}

fun defaultDiscoverSections(
    mode: DiscoverMediaMode,
    date: LocalDate = LocalDate.now()
): List<DiscoverSectionSpec> {
    return activeSwedishSeasonalSections(date, mode) + evergreenDiscoverSections(mode)
}

fun activeSwedishSeasonalSections(
    date: LocalDate,
    mode: DiscoverMediaMode
): List<DiscoverSectionSpec> {
    val sections = mutableListOf<DiscoverSectionSpec>()

    if (isChristmasWindow(date)) {
        sections += when (mode) {
            DiscoverMediaMode.MOVIES -> DiscoverSectionSpec(
                id = "season_christmas_movies",
                title = "Julfilmer",
                subtitle = "Klassiker, familjemys och vinterkänsla inför julen.",
                catalogIds = listOf(
                    "collection_intent_family_break_movies",
                    "collection_genre_family",
                    "collection_genre_romance",
                    "collection_genre_fantasy"
                ),
                accent = 0xFFE83D5C
            )
            DiscoverMediaMode.SERIES -> DiscoverSectionSpec(
                id = "season_christmas_series",
                title = "Julserier",
                subtitle = "Mysiga miniserier, familj och lättare vintertittande.",
                catalogIds = listOf(
                    "collection_intent_family_series",
                    "collection_genre_family",
                    "collection_genre_comedy",
                    "collection_genre_animation"
                ),
                accent = 0xFFE83D5C
            )
        }
    }

    if (isHalloweenWindow(date) || isAutumnBreakWindow(date)) {
        sections += when (mode) {
            DiscoverMediaMode.MOVIES -> DiscoverSectionSpec(
                id = "season_halloween_movies",
                title = "Halloween och höstlov",
                subtitle = "Skräck, thrillers och mörka kvällsfilmer.",
                catalogIds = listOf("collection_genre_horror", "collection_genre_thriller"),
                accent = 0xFFFF7A1A
            )
            DiscoverMediaMode.SERIES -> DiscoverSectionSpec(
                id = "season_halloween_series",
                title = "Halloween-serier",
                subtitle = "Mörka serier, skräck och övernaturligt.",
                catalogIds = listOf("collection_genre_horror", "collection_genre_thriller", "collection_genre_scifi"),
                accent = 0xFFFF7A1A
            )
        }
    }

    if (isSportBreakWindow(date)) {
        sections += when (mode) {
            DiscoverMediaMode.MOVIES -> DiscoverSectionSpec(
                id = "season_sport_break_movies",
                title = "Sportlovsfilmer",
                subtitle = "Familj, äventyr och kortare filmer som funkar en ledig kväll.",
                catalogIds = listOf(
                    "collection_intent_family_break_movies",
                    "collection_intent_short_movie",
                    "collection_genre_family",
                    "collection_genre_adventure"
                ),
                accent = 0xFF7DD3FC
            )
            DiscoverMediaMode.SERIES -> DiscoverSectionSpec(
                id = "season_sport_break_series",
                title = "Sportlovsserier",
                subtitle = "Lättstartade serier för lediga dagar och familjekvällar.",
                catalogIds = listOf("collection_intent_family_series", "collection_genre_family", "collection_genre_animation"),
                accent = 0xFF7DD3FC
            )
        }
    }

    if (isEasterWindow(date)) {
        sections += when (mode) {
            DiscoverMediaMode.MOVIES -> DiscoverSectionSpec(
                id = "season_easter_movies",
                title = "Påsklov",
                subtitle = "Lätt, färgstarkt och familjevänligt när helgen är lång.",
                catalogIds = listOf(
                    "collection_intent_family_break_movies",
                    "collection_genre_family",
                    "collection_genre_comedy",
                    "collection_genre_adventure"
                ),
                accent = 0xFFFACC15
            )
            DiscoverMediaMode.SERIES -> DiscoverSectionSpec(
                id = "season_easter_series",
                title = "Påsklovsserier",
                subtitle = "Komedi, familj och lättare serier att falla in i.",
                catalogIds = listOf("collection_intent_family_series", "collection_genre_comedy", "collection_genre_family"),
                accent = 0xFFFACC15
            )
        }
    }

    if (isMidsummerWindow(date)) {
        sections += when (mode) {
            DiscoverMediaMode.MOVIES -> DiscoverSectionSpec(
                id = "season_midsummer_movies",
                title = "Svensk sommar",
                subtitle = "Sommarkvällar, äventyr och feelgood runt midsommar.",
                catalogIds = listOf("collection_genre_comedy", "collection_genre_adventure", "collection_genre_family"),
                accent = 0xFF59D38C
            )
            DiscoverMediaMode.SERIES -> DiscoverSectionSpec(
                id = "season_midsummer_series",
                title = "Sommarlugna serier",
                subtitle = "Lättare serier, komedi och semesterkänsla.",
                catalogIds = listOf("collection_genre_comedy", "collection_genre_family", "collection_genre_adventure"),
                accent = 0xFF59D38C
            )
        }
    }

    return sections
}

private fun evergreenDiscoverSections(mode: DiscoverMediaMode): List<DiscoverSectionSpec> {
    return when (mode) {
        DiscoverMediaMode.MOVIES -> listOf(
            DiscoverSectionSpec(
                id = "movie_decide_now",
                title = "Vad ska jag titta på nu?",
                subtitle = "Snabba, säkra och nya filmval utan att fastna i bläddring.",
                catalogIds = listOf(
                    "collection_intent_short_movie",
                    "collection_intent_best_movies",
                    "collection_intent_new_streaming_movies"
                ),
                accent = 0xFFFFB000
            ),
            DiscoverSectionSpec(
                id = "movie_trending",
                title = "Populära filmer just nu",
                subtitle = "Det som rör sig snabbast på Trakt och MDBList.",
                catalogIds = listOf("trending_movies", "top10_movies_today"),
                accent = 0xFF60A5FA
            ),
            DiscoverSectionSpec(
                id = "movie_new",
                title = "Nytt på dina tjänster",
                subtitle = "Digitala premiärer och filmer som precis dykt upp.",
                catalogIds = listOf("collection_intent_new_streaming_movies", "just_added", "coming_soon"),
                accent = 0xFF34D399
            ),
            DiscoverSectionSpec(
                id = "movie_quality",
                title = "Högt betyg och säkra kort",
                subtitle = "Populära listor där betyg och genomslag väger tungt.",
                catalogIds = listOf("collection_intent_best_movies", "top_movies_week", "collection_genre_drama"),
                accent = 0xFFFBBF24
            ),
            DiscoverSectionSpec(
                id = "movie_mood",
                title = "Välj efter känsla",
                subtitle = "Action, thriller, komedi, familj och sci-fi.",
                catalogIds = listOf(
                    "collection_genre_new",
                    "collection_genre_thriller",
                    "collection_genre_comedy",
                    "collection_genre_family",
                    "collection_genre_scifi"
                ),
                accent = 0xFFA78BFA
            )
        )

        DiscoverMediaMode.SERIES -> listOf(
            DiscoverSectionSpec(
                id = "series_decide_now",
                title = "Vad ska jag titta på nu?",
                subtitle = "Säkra serier och nya snackisar, helt separerat från filmer.",
                catalogIds = listOf(
                    "collection_intent_best_series",
                    "collection_intent_series_buzz",
                    "collection_intent_new_streaming_series",
                    "top10_shows_today"
                ),
                accent = 0xFFFFB000
            ),
            DiscoverSectionSpec(
                id = "series_trending",
                title = "Populära serier just nu",
                subtitle = "Trendande serier, dagstoppar och nya snackisar.",
                catalogIds = listOf("trending_tv", "top10_shows_today"),
                accent = 0xFF60A5FA
            ),
            DiscoverSectionSpec(
                id = "series_new",
                title = "Nytt på dina tjänster",
                subtitle = "Nya serier, aktuella avsnitt och listor som uppdateras ofta.",
                catalogIds = listOf("collection_intent_new_streaming_series", "new_kdramas", "trending_anime"),
                accent = 0xFF34D399
            ),
            DiscoverSectionSpec(
                id = "series_mood",
                title = "Välj efter känsla",
                subtitle = "Sci-fi, thriller, komedi, familj och animation.",
                catalogIds = listOf(
                    "collection_genre_scifi",
                    "collection_genre_thriller",
                    "collection_genre_comedy",
                    "collection_genre_family",
                    "collection_genre_animation"
                ),
                accent = 0xFFA78BFA
            ),
            DiscoverSectionSpec(
                id = "series_services",
                title = "Bläddra efter tjänst",
                subtitle = "Netflix, Max, Disney+, Prime Video och fler.",
                catalogIds = listOf(
                    "collection_service_netflix",
                    "collection_service_max",
                    "collection_service_disneyplus",
                    "collection_service_prime_video",
                    "collection_service_apple_tvplus"
                ),
                accent = 0xFF22D3EE
            )
        )
    }
}

private fun isChristmasWindow(date: LocalDate): Boolean {
    val currentStart = LocalDate.of(date.year, Month.NOVEMBER, 24)
    val currentEnd = LocalDate.of(date.year + 1, Month.JANUARY, 1)
    val previousStart = LocalDate.of(date.year - 1, Month.NOVEMBER, 24)
    val previousEnd = LocalDate.of(date.year, Month.JANUARY, 1)
    return date in currentStart..currentEnd || date in previousStart..previousEnd
}

private fun isHalloweenWindow(date: LocalDate): Boolean {
    return date.month == Month.OCTOBER || (date.month == Month.NOVEMBER && date.dayOfMonth <= 7)
}

private fun isAutumnBreakWindow(date: LocalDate): Boolean {
    return date.month == Month.OCTOBER && date.dayOfMonth >= 20
}

private fun isSportBreakWindow(date: LocalDate): Boolean {
    return (date.month == Month.FEBRUARY && date.dayOfMonth >= 1) ||
        (date.month == Month.MARCH && date.dayOfMonth <= 10)
}

private fun isEasterWindow(date: LocalDate): Boolean {
    val easter = easterSunday(date.year)
    return ChronoUnit.DAYS.between(easter, date).let { days -> days in -7..10 }
}

private fun isMidsummerWindow(date: LocalDate): Boolean {
    val midsummerEve = midsummerEve(date.year)
    return ChronoUnit.DAYS.between(midsummerEve, date).let { days -> days in -14..14 }
}

private fun midsummerEve(year: Int): LocalDate {
    var date = LocalDate.of(year, Month.JUNE, 19)
    while (date.dayOfWeek != DayOfWeek.FRIDAY) {
        date = date.plusDays(1)
    }
    return date
}

private fun easterSunday(year: Int): LocalDate {
    val a = year % 19
    val b = year / 100
    val c = year % 100
    val d = b / 4
    val e = b % 4
    val f = (b + 8) / 25
    val g = (b - f + 1) / 3
    val h = (19 * a + b - d - g + 15) % 30
    val i = c / 4
    val k = c % 4
    val l = (32 + 2 * e + 2 * i - h - k) % 7
    val m = (a + 11 * h + 22 * l) / 451
    val month = (h + l - 7 * m + 114) / 31
    val day = ((h + l - 7 * m + 114) % 31) + 1
    return LocalDate.of(year, month, day)
}
