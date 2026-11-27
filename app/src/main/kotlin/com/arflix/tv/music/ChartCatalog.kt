package com.arflix.tv.music

import java.time.LocalDate
import java.time.Month
import kotlin.random.Random

/**
 * Which rows the Music home shows for an app language: the country's own charts and genres
 * first, then a long mix of moods, genres and decades whose order changes every day.
 */
object ChartCatalog {

    /** One home row; [query] searches playlists, null means Deezer's own chart playlists. */
    data class Row(val key: String, val title: String, val query: String?)

    private data class Local(val countryChart: String, val chartsTitle: String, val local: List<Pair<String, String>>)

    /** Per language: the country's top chart, the row title for the charts, and local rows (title to query). */
    private val LOCAL: Map<String, Local> = mapOf(
        "nl" to Local("Top Netherlands", "Hitlijsten", listOf(
            "Top 40 Nederland" to "Top 40 Nederland", "Nederlandse hits" to "Nederlandse hits",
            "Nederlandstalig" to "Nederlandstalig", "Nederhop" to "Nederhop", "Feestkrakers" to "Feest hits nederlands",
            "Top 2000" to "Top 2000",
        )),
        "de" to Local("Top Germany", "Charts", listOf(
            "Deutsche Hits" to "Deutsche Hits", "Deutschrap" to "Deutschrap", "Deutschpop" to "Deutschpop",
            "Schlager" to "Schlager", "Ballermann Hits" to "Ballermann", "Party Hits" to "Party Hits Deutsch",
        )),
        "fr" to Local("Top France", "Classements", listOf(
            "Hits du moment" to "Hits du moment", "Rap français" to "Rap français", "Chanson française" to "Chanson française",
            "Variété française" to "Variété française", "Soirée" to "Soirée hits",
        )),
        "es" to Local("Top Spain", "Listas de éxitos", listOf(
            "Éxitos España" to "Éxitos España", "Reggaetón" to "Reggaeton", "Pop español" to "Pop español",
            "Flamenco" to "Flamenco", "Fiesta" to "Fiesta latina",
        )),
        "it" to Local("Top Italy", "Classifiche", listOf(
            "Hit italiane" to "Hit italiane", "Rap italiano" to "Rap italiano", "Pop italiano" to "Pop italiano",
            "Cantautori" to "Cantautori italiani",
        )),
        "pt-BR" to Local("Top Brasil", "Paradas", listOf(
            "Sertanejo" to "Sertanejo", "Funk" to "Funk brasileiro", "Pagode" to "Pagode", "MPB" to "MPB",
            "Piseiro" to "Piseiro",
        )),
        "pt-PT" to Local("Top Portugal", "Tabelas", listOf(
            "Música portuguesa" to "Música portuguesa", "Fado" to "Fado", "Kizomba" to "Kizomba", "Rap tuga" to "Rap tuga",
        )),
        "pl" to Local("Top Poland", "Listy przebojów", listOf(
            "Polskie hity" to "Polskie hity", "Polski rap" to "Polski rap", "Disco polo" to "Disco polo",
        )),
        "tr" to Local("Top Turkey", "Listeler", listOf(
            "Türkçe pop" to "Türkçe pop", "Türkçe rap" to "Türkçe rap", "Arabesk" to "Arabesk",
        )),
        "ru" to Local("Top Russia", "Чарты", listOf(
            "Русские хиты" to "Русские хиты", "Русский рэп" to "Русский рэп", "Русский поп" to "Русский поп",
        )),
        "ar" to Local("Top Saudi Arabia", "الأكثر استماعًا", listOf(
            "Arabic hits" to "Arabic hits", "Khaleeji" to "Khaleeji", "Arabic pop" to "Arabic pop",
        )),
        "hi" to Local("Top India", "Charts", listOf(
            "Bollywood" to "Bollywood", "Punjabi" to "Punjabi hits", "Hindi hits" to "Hindi hits",
        )),
        "ja" to Local("Top Japan", "チャート", listOf(
            "J-Pop" to "J-Pop", "Anime" to "Anime songs", "City pop" to "City pop",
        )),
        "ko" to Local("Top South Korea", "차트", listOf(
            "K-Pop" to "K-Pop", "K-R&B" to "Korean R&B", "K-Hip hop" to "Korean hip hop",
        )),
        "zh" to Local("Top Taiwan", "排行榜", listOf(
            "C-Pop" to "C-Pop", "Mandopop" to "Mandopop", "Cantopop" to "Cantopop",
        )),
        // Hebrew searches find few Deezer playlists, so the Israeli rows search in English.
        "he" to Local("Top Israel", "מצעדים", listOf(
            "להיטים ישראליים" to "Israeli hits", "פופ ישראלי" to "Israeli pop", "מזרחית" to "Mizrahi",
            "רוק ישראלי" to "Israeli rock", "היפ הופ ישראלי" to "Israeli rap", "קלאסיקות ישראליות" to "Israeli classics",
        )),
        "en-GB" to Local("Top UK", "Charts", listOf(
            "UK hits" to "UK hits", "UK rap" to "UK rap", "Britpop" to "Britpop",
        )),
        "en" to Local("Top USA", "Charts", listOf(
            "US hits" to "US hits", "Country" to "Country hits", "Hip hop USA" to "Hip hop hits",
        )),
    )

    /** Rows every language gets, with the playlist search in English (Deezer's titles mostly are). */
    private val GENERAL: List<Pair<String, String>> = listOf(
        "top40" to "Top 40", "worldwide" to "Top Worldwide", "new" to "New releases", "viral" to "Viral hits",
        "gym" to "Gym hits", "workout" to "Workout", "running" to "Running", "chill" to "Chill",
        "party" to "Party", "throwback" to "Throwback", "80s" to "80s hits", "90s" to "90s hits",
        "2000s" to "2000s hits", "2010s" to "2010s hits", "hiphop" to "Hip hop", "rnb" to "R&B",
        "dance" to "Dance hits", "house" to "House", "techno" to "Techno", "edm" to "EDM",
        "rock" to "Rock", "classicrock" to "Classic rock", "metal" to "Metal", "indie" to "Indie",
        "pop" to "Pop hits", "latin" to "Latin hits", "afro" to "Afrobeats", "reggae" to "Reggae",
        "jazz" to "Jazz", "classical" to "Classical", "lofi" to "Lo-fi", "focus" to "Focus",
        "sleep" to "Sleep", "acoustic" to "Acoustic", "love" to "Love songs", "sad" to "Sad songs",
        "roadtrip" to "Road trip", "soul" to "Soul", "piano" to "Piano", "kids" to "Kids",
        "summer" to "Summer hits", "feelgood" to "Feel good", "christmas" to "Christmas hits",
    )

    /** Row titles for the general rows, per language; missing ones keep the English title. */
    private val TITLES: Map<String, Map<String, String>> = mapOf(
        "he" to mapOf(
            "top40" to "טופ 40", "worldwide" to "המובילים בעולם", "new" to "יציאות חדשות", "viral" to "להיטים ויראליים",
            "gym" to "חדר כושר", "workout" to "אימון", "running" to "ריצה", "chill" to "צ'יל",
            "party" to "מסיבה", "throwback" to "נוסטלגיה", "80s" to "להיטי שנות ה-80", "90s" to "להיטי שנות ה-90",
            "2000s" to "להיטי שנות ה-2000", "2010s" to "להיטי שנות ה-2010", "hiphop" to "היפ הופ", "rnb" to "R&B",
            "dance" to "להיטי דאנס", "house" to "האוס", "techno" to "טכנו", "edm" to "EDM",
            "rock" to "רוק", "classicrock" to "רוק קלאסי", "metal" to "מטאל", "indie" to "אינדי",
            "pop" to "להיטי פופ", "latin" to "להיטים לטיניים", "afro" to "אפרוביטס", "reggae" to "רגאיי",
            "jazz" to "ג'אז", "classical" to "מוזיקה קלאסית", "lofi" to "לו-פיי", "focus" to "ריכוז",
            "sleep" to "שינה", "acoustic" to "אקוסטי", "love" to "שירי אהבה", "sad" to "שירים עצובים",
            "roadtrip" to "בדרכים", "soul" to "סול", "piano" to "פסנתר", "kids" to "ילדים",
            "summer" to "להיטי הקיץ", "feelgood" to "מצב רוח טוב", "christmas" to "שירי חג המולד",
            "year" to "להיטי %d",
        ),
        "nl" to mapOf(
            "worldwide" to "Wereldwijd top", "new" to "Nieuwe releases", "viral" to "Viral hits", "gym" to "Sportschool",
            "running" to "Hardlopen", "party" to "Feest", "throwback" to "Throwback", "love" to "Liefdesliedjes",
            "sad" to "Verdrietig", "roadtrip" to "Onderweg", "sleep" to "Slapen", "summer" to "Zomerhits",
            "feelgood" to "Goed gevoel", "christmas" to "Kersthits", "classical" to "Klassiek",
        ),
        "de" to mapOf(
            "worldwide" to "Weltweit top", "new" to "Neuerscheinungen", "gym" to "Gym Hits", "running" to "Laufen",
            "party" to "Party", "love" to "Liebeslieder", "sad" to "Traurige Songs", "roadtrip" to "Roadtrip",
            "sleep" to "Schlafen", "summer" to "Sommerhits", "feelgood" to "Gute Laune", "christmas" to "Weihnachtshits",
            "classical" to "Klassik", "kids" to "Kinder",
        ),
        "fr" to mapOf(
            "worldwide" to "Top mondial", "new" to "Nouveautés", "gym" to "Muscu", "running" to "Course à pied",
            "party" to "Soirée", "love" to "Chansons d'amour", "sad" to "Chansons tristes", "sleep" to "Dormir",
            "summer" to "Tubes de l'été", "christmas" to "Chansons de Noël", "classical" to "Classique", "kids" to "Enfants",
        ),
        "es" to mapOf(
            "worldwide" to "Top mundial", "new" to "Novedades", "gym" to "Gimnasio", "running" to "Correr",
            "party" to "Fiesta", "love" to "Canciones de amor", "sad" to "Canciones tristes", "sleep" to "Dormir",
            "summer" to "Éxitos del verano", "christmas" to "Navidad", "classical" to "Clásica", "kids" to "Niños",
        ),
        "it" to mapOf(
            "worldwide" to "Top mondiale", "new" to "Nuove uscite", "gym" to "Palestra", "running" to "Corsa",
            "party" to "Festa", "love" to "Canzoni d'amore", "sleep" to "Dormire", "summer" to "Hit dell'estate",
            "christmas" to "Natale", "classical" to "Classica",
        ),
        "pt" to mapOf(
            "worldwide" to "Top mundial", "new" to "Lançamentos", "gym" to "Academia", "running" to "Corrida",
            "party" to "Festa", "love" to "Músicas de amor", "sleep" to "Dormir", "summer" to "Hits do verão",
            "christmas" to "Natal", "classical" to "Clássica",
        ),
    )

    /** The country chart's row title, where the language says the country's name differently. */
    private val COUNTRY_TITLES = mapOf(
        "nl" to "Top Nederland", "de" to "Top Deutschland", "fr" to "Top France", "es" to "Top España",
        "it" to "Top Italia", "pt-BR" to "Top Brasil", "pt-PT" to "Top Portugal", "pl" to "Top Polska",
        "tr" to "Top Türkiye", "he" to "המובילים בישראל",
    )

    /** Rows that lead every day: the country's chart, the charts row and the local rows. */
    private const val PINNED_GENERAL = 3

    fun rows(languageTag: String, date: LocalDate): List<Row> {
        val tag = languageTag.replace('_', '-')
        // Android still reports Hebrew by its old code, "iw".
        val language = tag.substringBefore('-').lowercase().let { if (it == "iw") "he" else it }
        val local = LOCAL[tag] ?: LOCAL[language] ?: LOCAL.getValue("en")
        val titles = TITLES[language].orEmpty()
        val general = GENERAL
            .filter { (key, _) -> key != "christmas" || date.month == Month.DECEMBER || (date.month == Month.NOVEMBER && date.dayOfMonth >= 15) }
            .filter { (key, _) -> key != "summer" || date.month in Month.MAY..Month.AUGUST }
            .map { (key, query) -> Row(key, titles[key] ?: query, query) }
        val year = date.year
        val pinned = listOf(
            Row("country", COUNTRY_TITLES[tag] ?: COUNTRY_TITLES[language] ?: local.countryChart, local.countryChart),
            // Deezer's own chart list follows the caller's location, so the language's country is searched instead.
            Row("charts", local.chartsTitle, "Top 50 " + local.countryChart.removePrefix("Top ")),
        ) + local.local.mapIndexed { i, (title, query) -> Row("local$i", title, query) } +
            general.take(PINNED_GENERAL) +
            Row("year", titles["year"]?.format(year) ?: "Hits $year", "Hits $year")
        // The rest changes order every day, so the home looks different each time.
        val rotating = general.drop(PINNED_GENERAL).shuffled(Random(date.toEpochDay()))
        return pinned + rotating
    }
}
