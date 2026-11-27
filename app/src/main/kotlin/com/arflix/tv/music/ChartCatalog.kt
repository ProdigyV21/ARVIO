package com.arflix.tv.music

import java.time.LocalDate
import java.time.Month
import java.util.Locale
import kotlin.random.Random

/**
 * Which rows the Music home shows: the charts and genres of the country the user is in first,
 * then a long mix of moods, genres and decades whose order changes every day. The country comes
 * from the device (network, SIM, time zone, region); the app language only names the rows, and
 * picks the country when the device does not say.
 */
object ChartCatalog {

    /** One home row; [query] searches playlists, null means Deezer's own chart playlists. */
    data class Row(val key: String, val title: String, val query: String?)

    private data class Local(val countryChart: String, val local: List<Pair<String, String>>)

    /** Per country (ISO 3166 code): its top chart on Deezer and local rows (title to query). */
    private val LOCAL: Map<String, Local> = mapOf(
        "NL" to Local("Top Netherlands", listOf(
            "Top 40 Nederland" to "Top 40 Nederland", "Nederlandse hits" to "Nederlandse hits",
            "Nederlandstalig" to "Nederlandstalig", "Nederhop" to "Nederhop", "Feestkrakers" to "Feest hits nederlands",
            "Top 2000" to "Top 2000",
        )),
        "BE" to Local("Top Belgium", listOf(
            "Ultratop 50" to "Ultratop 50", "Belgische hits" to "Belgische hits", "Vlaamse hits" to "Vlaamse hits",
            "Hits belges" to "Hits belges", "Nederlandstalig" to "Nederlandstalig",
        )),
        "SR" to Local("Top Suriname", listOf(
            "Surinaamse hits" to "Surinaamse muziek", "Kaseko" to "Kaseko", "Kawina" to "Kawina", "Nederlandse hits" to "Nederlandse hits",
        )),
        "DE" to Local("Top Germany", listOf(
            "Deutsche Hits" to "Deutsche Hits", "Deutschrap" to "Deutschrap", "Deutschpop" to "Deutschpop",
            "Schlager" to "Schlager", "Ballermann Hits" to "Ballermann", "Party Hits" to "Party Hits Deutsch",
        )),
        "AT" to Local("Top Austria", listOf(
            "Austropop" to "Austropop", "Deutsche Hits" to "Deutsche Hits", "Deutschrap" to "Deutschrap", "Schlager" to "Schlager",
        )),
        "CH" to Local("Top Switzerland", listOf(
            "Schweizer Hits" to "Schweizer Hits", "Mundart" to "Mundart", "Deutsche Hits" to "Deutsche Hits", "Rap français" to "Rap français",
        )),
        "FR" to Local("Top France", listOf(
            "Hits du moment" to "Hits du moment", "Rap français" to "Rap français", "Chanson française" to "Chanson française",
            "Variété française" to "Variété française", "Soirée" to "Soirée hits",
        )),
        "ES" to Local("Top Spain", listOf(
            "Éxitos España" to "Éxitos España", "Reggaetón" to "Reggaeton", "Pop español" to "Pop español",
            "Flamenco" to "Flamenco", "Fiesta" to "Fiesta latina",
        )),
        "MX" to Local("Top Mexico", listOf(
            "Regional mexicano" to "Regional mexicano", "Corridos" to "Corridos tumbados", "Banda" to "Banda", "Reggaetón" to "Reggaeton",
        )),
        "AR" to Local("Top Argentina", listOf(
            "Trap argentino" to "Trap argentino", "Cumbia" to "Cumbia", "Rock nacional" to "Rock nacional", "Cuarteto" to "Cuarteto",
        )),
        "CO" to Local("Top Colombia", listOf(
            "Reggaetón" to "Reggaeton", "Vallenato" to "Vallenato", "Salsa" to "Salsa", "Champeta" to "Champeta",
        )),
        "IT" to Local("Top Italy", listOf(
            "Hit italiane" to "Hit italiane", "Rap italiano" to "Rap italiano", "Pop italiano" to "Pop italiano",
            "Cantautori" to "Cantautori italiani",
        )),
        "BR" to Local("Top Brasil", listOf(
            "Sertanejo" to "Sertanejo", "Funk" to "Funk brasileiro", "Pagode" to "Pagode", "MPB" to "MPB",
            "Piseiro" to "Piseiro",
        )),
        "PT" to Local("Top Portugal", listOf(
            "Música portuguesa" to "Música portuguesa", "Fado" to "Fado", "Kizomba" to "Kizomba", "Rap tuga" to "Rap tuga",
        )),
        "PL" to Local("Top Poland", listOf(
            "Polskie hity" to "Polskie hity", "Polski rap" to "Polski rap", "Disco polo" to "Disco polo",
        )),
        "TR" to Local("Top Turkey", listOf(
            "Türkçe pop" to "Türkçe pop", "Türkçe rap" to "Türkçe rap", "Arabesk" to "Arabesk",
        )),
        "RU" to Local("Top Russia", listOf(
            "Русские хиты" to "Русские хиты", "Русский рэп" to "Русский рэп", "Русский поп" to "Русский поп",
        )),
        "SA" to Local("Top Saudi Arabia", listOf(
            "Arabic hits" to "Arabic hits", "Khaleeji" to "Khaleeji", "Arabic pop" to "Arabic pop",
        )),
        "AE" to Local("Top United Arab Emirates", listOf(
            "Arabic hits" to "Arabic hits", "Khaleeji" to "Khaleeji", "Bollywood" to "Bollywood",
        )),
        "EG" to Local("Top Egypt", listOf(
            "Mahraganat" to "Mahraganat", "Arabic hits" to "Arabic hits", "Arabic pop" to "Arabic pop",
        )),
        "MA" to Local("Top Morocco", listOf(
            "Rap marocain" to "Rap marocain", "Raï" to "Rai", "Chaabi" to "Chaabi",
        )),
        "IN" to Local("Top India", listOf(
            "Bollywood" to "Bollywood", "Punjabi" to "Punjabi hits", "Hindi hits" to "Hindi hits",
        )),
        "PK" to Local("Top Pakistan", listOf(
            "Coke Studio" to "Coke Studio", "Punjabi" to "Punjabi hits", "Urdu hits" to "Urdu hits",
        )),
        "ID" to Local("Top Indonesia", listOf(
            "Pop Indonesia" to "Pop Indonesia", "Dangdut" to "Dangdut", "Koplo" to "Dangdut koplo",
        )),
        "PH" to Local("Top Philippines", listOf(
            "OPM" to "OPM", "P-Pop" to "P-Pop", "OPM love songs" to "OPM love songs",
        )),
        "JP" to Local("Top Japan", listOf(
            "J-Pop" to "J-Pop", "Anime" to "Anime songs", "City pop" to "City pop",
        )),
        "KR" to Local("Top South Korea", listOf(
            "K-Pop" to "K-Pop", "K-R&B" to "Korean R&B", "K-Hip hop" to "Korean hip hop",
        )),
        "TW" to Local("Top Taiwan", listOf(
            "C-Pop" to "C-Pop", "Mandopop" to "Mandopop", "Cantopop" to "Cantopop",
        )),
        // Hebrew searches find few Deezer playlists, so the Israeli rows search in English.
        "IL" to Local("Top Israel", listOf(
            "להיטים ישראליים" to "Israeli hits", "פופ ישראלי" to "Israeli pop", "מזרחית" to "Mizrahi",
            "רוק ישראלי" to "Israeli rock", "היפ הופ ישראלי" to "Israeli rap", "קלאסיקות ישראליות" to "Israeli classics",
        )),
        "GB" to Local("Top UK", listOf(
            "UK hits" to "UK hits", "UK rap" to "UK rap", "Britpop" to "Britpop",
        )),
        "IE" to Local("Top Ireland", listOf(
            "Irish hits" to "Irish hits", "Irish pub songs" to "Irish pub songs", "UK hits" to "UK hits",
        )),
        "US" to Local("Top USA", listOf(
            "US hits" to "US hits", "Country" to "Country hits", "Hip hop USA" to "Hip hop hits",
        )),
        "CA" to Local("Top Canada", listOf(
            "Canadian hits" to "Canadian hits", "Country" to "Country hits", "Musique québécoise" to "Musique québécoise",
        )),
        "AU" to Local("Top Australia", listOf(
            "Aussie hits" to "Aussie hits", "Aussie rock" to "Aussie rock", "Triple J" to "Triple J hottest",
        )),
        "NZ" to Local("Top New Zealand", listOf(
            "Kiwi hits" to "New Zealand hits", "Aussie hits" to "Aussie hits",
        )),
        "ZA" to Local("Top South Africa", listOf(
            "Amapiano" to "Amapiano", "Gqom" to "Gqom", "SA hip hop" to "South African hip hop",
        )),
        "NG" to Local("Top Nigeria", listOf(
            "Afrobeats" to "Afrobeats", "Naija hits" to "Naija hits", "Amapiano" to "Amapiano",
        )),
        "SE" to Local("Top Sweden", listOf(
            "Svenska hits" to "Svenska hits", "Svensk hiphop" to "Svensk hiphop",
        )),
        "NO" to Local("Top Norway", listOf("Norske hits" to "Norske hits", "Norsk rap" to "Norsk rap")),
        "DK" to Local("Top Denmark", listOf("Danske hits" to "Danske hits", "Dansk rap" to "Dansk rap")),
        "FI" to Local("Top Finland", listOf("Suomi hitit" to "Suomi hitit", "Suomirap" to "Suomirap")),
    )

    /** Deezer's spelling where its chart name is not the plain English country name. */
    private val CHART_NAMES = mapOf("US" to "USA", "GB" to "UK")

    /** The country an app language stands for, when the device gives no country. */
    private val LANGUAGE_COUNTRY = mapOf(
        "nl" to "NL", "de" to "DE", "fr" to "FR", "es" to "ES", "it" to "IT", "pt-BR" to "BR", "pt-PT" to "PT", "pt" to "BR",
        "pl" to "PL", "tr" to "TR", "ru" to "RU", "ar" to "SA", "hi" to "IN", "ja" to "JP", "ko" to "KR", "zh" to "TW",
        "en-GB" to "GB", "en" to "US", "sv" to "SE", "nb" to "NO", "da" to "DK", "fi" to "FI", "id" to "ID",
        "he" to "IL",
    )

    /** The charts row's title, per app language. */
    private val CHARTS_TITLES = mapOf(
        "nl" to "Hitlijsten", "fr" to "Classements", "es" to "Listas de éxitos", "it" to "Classifiche",
        "pt" to "Paradas", "pt-PT" to "Tabelas", "pl" to "Listy przebojów", "tr" to "Listeler", "ru" to "Чарты",
        "ar" to "الأكثر استماعًا", "ja" to "チャート", "ko" to "차트", "zh" to "排行榜",
        "he" to "מצעדים",
    )

    /** "Top <country>" where a language puts it differently; %s is the country's name in that language. */
    private val COUNTRY_TOP = mapOf("he" to "המובילים ב%s")

    /** Android still reports Hebrew by its old code, "iw". */
    private fun normalized(languageTag: String): String {
        val tag = languageTag.replace('_', '-')
        return if (tag.substringBefore('-').lowercase() == "iw") "he" + tag.drop(2) else tag
    }

    /**
     * The country whose charts lead: the device's country when it gives one, else the app
     * language's region ("nl-BE"), else the country the language stands for.
     */
    fun country(languageTag: String, deviceCountry: String?): String {
        deviceCountry?.trim()?.uppercase()?.takeIf { it.length == 2 && it.all(Char::isLetter) }?.let { return it }
        val tag = normalized(languageTag)
        val region = tag.split('-').drop(1).firstOrNull { it.length == 2 && it.all(Char::isLetter) }?.uppercase()
        if (region != null && region in LOCAL) return region
        val language = tag.substringBefore('-').lowercase()
        return LANGUAGE_COUNTRY[tag] ?: LANGUAGE_COUNTRY[language] ?: region ?: "US"
    }

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

    /** Rows that lead every day: the country's chart, the charts row and the local rows. */
    private const val PINNED_GENERAL = 3

    fun rows(languageTag: String, date: LocalDate, deviceCountry: String? = null): List<Row> {
        val tag = normalized(languageTag)
        val language = tag.substringBefore('-').lowercase()
        val country = country(tag, deviceCountry)
        val local = LOCAL[country]
        val englishName = CHART_NAMES[country] ?: Locale("", country).getDisplayCountry(Locale.ENGLISH).ifBlank { country }
        val chartQuery = local?.countryChart ?: "Top $englishName"
        val shownName = Locale("", country).getDisplayCountry(Locale.forLanguageTag(tag)).takeIf { it.isNotBlank() && it != country }
            ?: englishName
        val titles = TITLES[language].orEmpty()
        val general = GENERAL
            .filter { (key, _) -> key != "christmas" || date.month == Month.DECEMBER || (date.month == Month.NOVEMBER && date.dayOfMonth >= 15) }
            .filter { (key, _) -> key != "summer" || date.month in Month.MAY..Month.AUGUST }
            .map { (key, query) -> Row(key, titles[key] ?: query, query) }
        val year = date.year
        val pinned = listOf(
            Row("country", COUNTRY_TOP[language]?.format(shownName) ?: "Top $shownName", chartQuery),
            // Deezer's own chart list follows the server's location, so the country's chart is searched instead.
            Row("charts", CHARTS_TITLES[tag] ?: CHARTS_TITLES[language] ?: "Charts", "Top 50 " + chartQuery.removePrefix("Top ")),
        ) + local?.local.orEmpty().mapIndexed { i, (title, query) -> Row("local$i", title, query) } +
            general.take(PINNED_GENERAL) +
            Row("year", titles["year"]?.format(year) ?: "Hits $year", "Hits $year")
        // The rest changes order every day, so the home looks different each time.
        val rotating = general.drop(PINNED_GENERAL).shuffled(Random(date.toEpochDay()))
        return pinned + rotating
    }

    /** The titles of the personal rows: "because you listened to %s", "based on your searches", a mix's subtitle. */
    data class PersonalTitles(val because: String, val searches: String, val mix: String)

    private val PERSONAL = mapOf(
        "nl" to PersonalTitles("Omdat je luisterde naar %s", "Op basis van je zoekopdrachten", "Mix"),
        "de" to PersonalTitles("Weil du %s gehört hast", "Basierend auf deinen Suchen", "Mix"),
        "fr" to PersonalTitles("Parce que vous avez écouté %s", "D'après vos recherches", "Mix"),
        "es" to PersonalTitles("Porque escuchaste a %s", "Según tus búsquedas", "Mix"),
        "it" to PersonalTitles("Perché hai ascoltato %s", "In base alle tue ricerche", "Mix"),
        "pt" to PersonalTitles("Porque você ouviu %s", "Com base nas suas pesquisas", "Mix"),
        "pl" to PersonalTitles("Bo słuchałeś: %s", "Na podstawie twoich wyszukiwań", "Mix"),
        "tr" to PersonalTitles("%s dinlediğin için", "Aramalarına göre", "Mix"),
        "he" to PersonalTitles("כי שמעת את %s", "לפי החיפושים שלך", "מיקס"),
    )

    fun personalTitles(languageTag: String): PersonalTitles =
        PERSONAL[normalized(languageTag).substringBefore('-').lowercase()]
            ?: PersonalTitles("Because you listened to %s", "Based on your searches", "Mix")
}
