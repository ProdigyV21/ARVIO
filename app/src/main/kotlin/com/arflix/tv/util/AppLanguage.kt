package com.arflix.tv.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.R
import java.util.Locale

val LocalAppLanguage = staticCompositionLocalOf { AppContentPreferences.DEFAULT_LANGUAGE_TAG }
val LAST_APP_LANGUAGE_KEY = stringPreferencesKey("last_app_language")

fun appLocale(languageTag: String): Locale {
    val normalized = languageTag.replace('_', '-')
    return Locale.forLanguageTag(normalized).takeUnless { it.language.isBlank() }
        ?: Locale.forLanguageTag(AppContentPreferences.DEFAULT_LANGUAGE_TAG)
}

fun localizedAppContext(context: Context, languageTag: String): Context {
    val locale = appLocale(languageTag)
    Locale.setDefault(locale)
    val config = Configuration(context.resources.configuration)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.setLocales(LocaleList(locale))
    } else {
        @Suppress("DEPRECATION")
        config.setLocale(locale)
    }
    return context.createConfigurationContext(config)
}

/**
 * Translate [text] using Android string resources (XML-backed).
 * Covers every key from the old AppTranslations map. Unknown strings fall back to
 * AppTranslations so existing call sites keep working during incremental migration.
 */
@Composable
fun tr(text: String): String {
    if (text.isBlank()) return text
    @StringRes val resId: Int? = when (text.trim()) {
        // Navigation
        "Home" -> R.string.home
        "Search",
        "Search or discover... try \"top 10 horror movies\"" -> R.string.search
        "Watchlist",
        "Add to Watchlist",
        "Remove from Watchlist" -> R.string.watchlist
        "Settings",
        "App Update",
        "App Updates" -> R.string.settings
        "General" -> R.string.general
        "Movies",
        "In Cinema" -> R.string.movies
        "Movie" -> R.string.movie
        "TV Shows" -> R.string.tv_shows
        "Shows" -> R.string.shows
        "Series" -> R.string.series
        "All Genres" -> R.string.all_genres
        "Any Language" -> R.string.any_language
        // Language & subtitles
        "Language & Subtitles" -> R.string.language_and_subtitles
        "App Language",
        "Content Language",
        "App text, titles, descriptions and metadata",
        "Titles, descriptions and metadata" -> R.string.app_language
        "Subtitles",
        "Default Subtitle",
        "Default Subtitles",
        "Auto-select subtitle language",
        "Subtitle Size",
        "Text size for subtitles",
        "Subtitle Color",
        "Text color for subtitles",
        "Subtitle Style",
        "Bold, Normal, or Background style for subtitles" -> R.string.subtitles
        "Audio",
        "Audio Track",
        "Default Audio",
        "Preferred audio track",
        "Volume Boost",
        "Amplify quiet sources (via system LoudnessEnhancer)",
        "No audio tracks available" -> R.string.audio
        // Playback
        "Playback",
        "Match Frame Rate" -> R.string.playback
        "Auto-Play Next",
        "Start next episode automatically",
        "Next Episode",
        "Next",
        "Next channel" -> R.string.next
        "Autoplay",
        "Auto-Play Min Quality",
        "Min quality for auto-play",
        "Auto" -> R.string.auto
        "Trailer Auto-Play",
        "Play trailers in hero banner",
        "Trailer",
        "Close trailer" -> R.string.trailer
        // Interface
        "Interface",
        "Card Layout",
        "Landscape or poster cards",
        "UI Mode",
        "Force TV, Tablet, or Phone",
        "Skip Profile Selection",
        "Auto-load last used profile",
        "Clock Format",
        "Choose 12-hour or 24-hour time" -> R.string.interface_label
        "Show Budget on Home",
        "Display the movie budget on the home hero banner",
        "Budget" -> R.string.budget
        // Network
        "Network",
        "DNS Provider",
        "Resolve API and stream requests" -> R.string.network
        // Catalogs / accounts
        "Catalogs",
        "Add Catalog",
        "Import a Trakt or MDBList catalog URL",
        "Trakt/MDBList URLs can be added manually. Addon catalogs appear automatically." -> R.string.catalogs
        "Accounts",
        "Linked Accounts",
        "Optional account for syncing profiles, addons, catalogs and IPTV settings" -> R.string.accounts
        // Sources
        "Sources",
        "Off opens the source picker on Play" -> R.string.sources
        "Available Sources" -> R.string.available_sources
        "Finding sources..." -> R.string.finding_sources
        "sources available" -> R.string.sources_available
        // IPTV
        "Add Playlist",
        "Create another IPTV list",
        "Add Catalog",
        "Add" -> R.string.add
        "Refresh IPTV Data",
        "Reload playlists now",
        "Reload playlist and EPG now",
        "Refresh" -> R.string.refresh
        "Refreshing channels and EPG...",
        "Waiting for authorization... (Press OK to cancel)" -> R.string.loading_label
        "Delete IPTV Playlists",
        "Remove playlists, EPG and favorites",
        "Remove from Continue Watching",
        "Remove",
        "Delete" -> R.string.delete
        "No playlists configured",
        "Empty" -> R.string.empty
        // Content
        "Details",
        "View Details" -> R.string.details
        "Play" -> R.string.play
        "Seasons" -> R.string.seasons
        "Season" -> R.string.season_label
        "Episodes" -> R.string.episodes
        "Cast" -> R.string.cast
        "Reviews" -> R.string.reviews
        "More Like This" -> R.string.more_like_this
        "Ongoing" -> R.string.ongoing
        // Watchlist
        "Your watchlist is empty" -> R.string.empty_watchlist
        "Add movies and shows to watch later" -> R.string.add_later
        "No results found",
        "Unable to load content",
        "Majo Stream uses community streaming addons to find video sources. Without at least one streaming addon, content cannot be played." -> R.string.no_results
        "No results found for" -> R.string.no_results_for
        // Actions
        "Close",
        "Press BACK to close" -> R.string.close
        "Back",
        "Back to channel list",
        "Previous channel" -> R.string.back
        "Cancel" -> R.string.cancel
        "Confirm",
        "Mark as Watched",
        "Mark as Unwatched",
        "Create" -> R.string.confirm
        "Retry" -> R.string.retry
        "Sign In" -> R.string.sign_in
        "Log Out" -> R.string.log_out
        "Off" -> R.string.off
        "On" -> R.string.on
        "Live" -> R.string.live
        "Now" -> R.string.now
        "Later" -> R.string.later
        "Ends at" -> R.string.ends_at
        "selected",
        "Selected" -> R.string.selected
        else -> null
    }
    if (resId != null) return stringResource(resId)

    // Derived / composite strings
    return when (text.trim()) {
        "MY WATCHLIST" -> stringResource(R.string.watchlist).uppercase()
        "BUDGET" -> stringResource(R.string.budget).uppercase()
        "ONGOING" -> stringResource(R.string.ongoing).uppercase()
        "FILTER BY SOURCE" -> stringResource(R.string.sources).uppercase()
        "TRY AGAIN" -> stringResource(R.string.retry).uppercase()
        "GO BACK" -> stringResource(R.string.back).uppercase()
        "UP NEXT" -> stringResource(R.string.next).uppercase()
        "PLAY NOW" -> stringResource(R.string.play).uppercase()
        "CANCEL" -> stringResource(R.string.cancel).uppercase()
        "OK" -> stringResource(R.string.confirm).uppercase()
        "NOW" -> stringResource(R.string.now).uppercase()
        "NEXT" -> stringResource(R.string.next).uppercase()
        "LATER" -> stringResource(R.string.later).uppercase()
        "LIVE" -> stringResource(R.string.live).uppercase()
        "ADD" -> stringResource(R.string.add).uppercase()
        "LOADING" -> stringResource(R.string.loading_label).uppercase()
        "REFRESH" -> stringResource(R.string.refresh).uppercase()
        "EMPTY" -> stringResource(R.string.empty).uppercase()
        "DELETE" -> stringResource(R.string.delete).uppercase()
        "CONNECT" -> stringResource(R.string.sign_in).uppercase()
        "CONNECTED" -> stringResource(R.string.on).uppercase()
        "Subtitles & Audio" ->
            "${stringResource(R.string.subtitles)} / ${stringResource(R.string.audio)}"
        "Switch tabs • Navigate • BACK Close",
        "Switch tabs â¢ Navigate â¢ BACK Close" ->
            "${stringResource(R.string.subtitles)} • ${stringResource(R.string.back)} • ${stringResource(R.string.close)}"
        "Off, Seamless, or Always" ->
            "${stringResource(R.string.off)} / ${stringResource(R.string.auto)}"
        else -> AppTranslations.translate(text, LocalAppLanguage.current)
    }
}

@Composable
fun trUpper(text: String): String = tr(text).uppercase(appLocale(LocalAppLanguage.current))

object AppTranslations {
    fun translate(text: String, languageTag: String): String {
        if (text.isBlank()) return text
        val locale = appLocale(languageTag)
        val language = locale.language.lowercase(Locale.US)
        if (language == "en") return text

        val normalized = text.trim().replace("â€¢", "•")
        val table = localeKeys(locale).firstNotNullOfOrNull { translations[it] } ?: return text
        translateDynamic(normalized, table)?.let { return it }
        return table[normalized] ?: text
    }

    private fun localeKeys(locale: Locale): List<String> {
        val language = locale.language.lowercase(Locale.US)
        val country = locale.country.uppercase(Locale.US)
        return if (country.isBlank()) {
            listOf(language)
        } else {
            listOf("$language-$country", language)
        }
    }

private object AppLanguageRegexes {
    val MOVIES = Regex("""Movies \((\d+)\)""")
    val TV_SHOWS = Regex("""TV Shows \((\d+)\)""")
    val NO_RESULTS = Regex("No results found for \"(.+)\"")
    val SOURCES = Regex("""(\d+) sources available""")
    val NEXT = Regex("""Next: (.+)""")
    val ENDS_AT = Regex("""Ends at (.+)""")
}

    private fun translateDynamic(text: String, table: Map<String, String>): String? {
        AppLanguageRegexes.MOVIES.matchEntire(text)?.let {
            return "${table.word("Movies")} (${it.groupValues[1]})"
        }
        AppLanguageRegexes.TV_SHOWS.matchEntire(text)?.let {
            return "${table.word("TV Shows")} (${it.groupValues[1]})"
        }
        AppLanguageRegexes.NO_RESULTS.matchEntire(text)?.let {
            return "${table.word("No results found for")} \"${it.groupValues[1]}\""
        }
        AppLanguageRegexes.SOURCES.matchEntire(text)?.let {
            return "${it.groupValues[1]} ${table.word("sources available")}"
        }
        AppLanguageRegexes.NEXT.matchEntire(text)?.let {
            return "${table.word("Next")}: ${it.groupValues[1]}"
        }
        AppLanguageRegexes.ENDS_AT.matchEntire(text)?.let {
            return "${table.word("Ends at")} ${it.groupValues[1]}"
        }
        return null
    }

    private fun Map<String, String>.word(key: String): String = this[key] ?: key

    private val translations: Map<String, Map<String, String>> = mapOf(
        "nl" to commonUi(
            home = "Start", search = "Zoeken", watchlist = "Kijklijst", settings = "Instellingen", general = "Algemeen",
            movies = "Films", movie = "Film", tvShows = "Series", shows = "Series", series = "Serie",
            allGenres = "Alle genres", anyLanguage = "Elke taal", appLanguage = "App-taal", languageAndSubtitles = "Taal en ondertitels",
            subtitles = "Ondertitels", audio = "Audio", playback = "Afspelen", interfaceText = "Interface", network = "Netwerk",
            catalogs = "Catalogi", accounts = "Accounts", sources = "Bronnen", details = "Details", play = "Afspelen",
            trailer = "Trailer", seasons = "Seizoenen", season = "Seizoen", episodes = "Afleveringen", cast = "Cast",
            reviews = "Recensies", moreLikeThis = "Meer zoals dit", close = "Sluiten", back = "Terug",
            cancel = "Annuleren", confirm = "Bevestigen", retry = "Opnieuw proberen", loading = "Laden", empty = "Leeg",
            delete = "Verwijderen", add = "Toevoegen", refresh = "Vernieuwen", selected = "geselecteerd",
            signIn = "Inloggen", logOut = "Uitloggen", next = "Volgende", live = "Live", now = "Nu", later = "Later",
            off = "Uit", on = "Aan", auto = "Auto", budget = "Budget", ongoing = "Lopend",
            findingSources = "Bronnen zoeken...", availableSources = "Beschikbare bronnen",
            noResults = "Geen resultaten gevonden", noResultsFor = "Geen resultaten gevonden voor",
            emptyWatchlist = "Je kijklijst is leeg", addLater = "Voeg films en series toe om later te kijken",
            sourcesAvailable = "bronnen beschikbaar", endsAt = "Eindigt om",
            extras = mapOf(
                "Content Language" to "App-taal",
                "App text, titles, descriptions and metadata" to "App-tekst, titels, beschrijvingen en metadata",
                "Titles, descriptions and metadata" to "Titels, beschrijvingen en metadata",
                "Auto-select subtitle language" to "Ondertiteltaal automatisch kiezen",
                "Preferred audio track" to "Voorkeurs-audiospoor",
                "Off opens the source picker on Play" to "Uit opent de bronkiezer bij Afspelen",
                "Show Budget on Home" to "Budget tonen op start",
                "Display the movie budget on the home hero banner" to "Toon het filmbudget in de hero-banner",
                "Trakt/MDBList URLs can be added manually. Addon catalogs appear automatically." to "Trakt/MDBList-URL's kunnen handmatig worden toegevoegd. Addon-catalogi verschijnen automatisch.",
                "Optional account for syncing profiles, addons, catalogs and IPTV settings" to "Optioneel account voor synchronisatie van profielen, addons, catalogi en IPTV-instellingen",
                "Majo Stream uses community streaming addons to find video sources. Without at least one streaming addon, content cannot be played." to "Majo Stream gebruikt streaming-addons uit de community om videobronnen te vinden. Zonder minimaal een streaming-addon kan inhoud niet worden afgespeeld.",
                "Switch tabs • Navigate • BACK Close" to "Wissel tabs • Navigeer • TERUG sluiten",
                "Waiting for authorization... (Press OK to cancel)" to "Wachten op autorisatie... (druk OK om te annuleren)"
            )
        ),
        "fr" to commonUi("Accueil", "Recherche", "Liste", "Paramètres", "Général", "Films", "Film", "Séries TV", "Séries", "Série", "Tous les genres", "Toutes les langues", "Langue de l'app", "Langue et sous-titres", "Sous-titres", "Audio", "Lecture", "Interface", "Réseau", "Catalogues", "Comptes", "Sources", "Détails", "Lire", "Bande-annonce", "Saisons", "Saison", "Épisodes", "Distribution", "Avis", "Similaire", "Fermer", "Retour", "Annuler", "Confirmer", "Réessayer", "Chargement", "Vide", "Supprimer", "Ajouter", "Actualiser", "sélectionné", "Connexion", "Déconnexion", "Suivant", "Direct", "Maintenant", "Plus tard", "Désactivé", "Activé", "Auto", "Budget", "En cours", "Recherche des sources...", "Sources disponibles", "Aucun résultat trouvé", "Aucun résultat trouvé pour", "Votre liste est vide", "Ajoutez des films et séries à regarder plus tard", "sources disponibles", "Se termine à"),
        "de" to commonUi("Start", "Suche", "Merkliste", "Einstellungen", "Allgemein", "Filme", "Film", "Serien", "Serien", "Serie", "Alle Genres", "Jede Sprache", "App-Sprache", "Sprache und Untertitel", "Untertitel", "Audio", "Wiedergabe", "Oberfläche", "Netzwerk", "Kataloge", "Konten", "Quellen", "Details", "Abspielen", "Trailer", "Staffeln", "Staffel", "Episoden", "Besetzung", "Rezensionen", "Mehr davon", "Schließen", "Zurück", "Abbrechen", "Bestätigen", "Erneut versuchen", "Laden", "Leer", "Löschen", "Hinzufügen", "Aktualisieren", "ausgewählt", "Anmelden", "Abmelden", "Weiter", "Live", "Jetzt", "Später", "Aus", "Ein", "Auto", "Budget", "Läuft", "Quellen werden gesucht...", "Verfügbare Quellen", "Keine Ergebnisse gefunden", "Keine Ergebnisse gefunden für", "Deine Merkliste ist leer", "Füge Filme und Serien für später hinzu", "Quellen verfügbar", "Endet um"),
        "es" to commonUi("Inicio", "Buscar", "Lista", "Ajustes", "General", "Películas", "Película", "Series", "Series", "Serie", "Todos los géneros", "Cualquier idioma", "Idioma de la app", "Idioma y subtítulos", "Subtítulos", "Audio", "Reproducción", "Interfaz", "Red", "Catálogos", "Cuentas", "Fuentes", "Detalles", "Reproducir", "Tráiler", "Temporadas", "Temporada", "Episodios", "Reparto", "Reseñas", "Más como esto", "Cerrar", "Atrás", "Cancelar", "Confirmar", "Reintentar", "Cargando", "Vacío", "Eliminar", "Añadir", "Actualizar", "seleccionado", "Iniciar sesión", "Cerrar sesión", "Siguiente", "En vivo", "Ahora", "Más tarde", "No", "Sí", "Auto", "Presupuesto", "En curso", "Buscando fuentes...", "Fuentes disponibles", "No se encontraron resultados", "No se encontraron resultados para", "Tu lista está vacía", "Añade películas y series para ver más tarde", "fuentes disponibles", "Termina a las"),
        "pt-PT" to commonUi("Início", "Pesquisar", "Lista", "Definições", "Geral", "Filmes", "Filme", "Séries TV", "Séries", "Série", "Todos os géneros", "Qualquer idioma", "Idioma da app", "Idioma e legendas", "Legendas", "Áudio", "Reprodução", "Interface", "Rede", "Catálogos", "Contas", "Fontes", "Detalhes", "Reproduzir", "Trailer", "Temporadas", "Temporada", "Episódios", "Elenco", "Críticas", "Mais assim", "Fechar", "Voltar", "Cancelar", "Confirmar", "Tentar novamente", "A carregar", "Vazio", "Eliminar", "Adicionar", "Atualizar", "selecionado", "Iniciar sessão", "Terminar sessão", "Seguinte", "Direto", "Agora", "Mais tarde", "Desligado", "Ligado", "Auto", "Orçamento", "Em curso", "A procurar fontes...", "Fontes disponíveis", "Nenhum resultado encontrado", "Nenhum resultado encontrado para", "A sua lista está vazia", "Adicione filmes e séries para ver mais tarde", "fontes disponíveis", "Termina às"),
        "pt-BR" to commonUi("Início", "Buscar", "Lista", "Configurações", "Geral", "Filmes", "Filme", "Séries", "Séries", "Série", "Todos os gêneros", "Qualquer idioma", "Idioma do app", "Idioma e legendas", "Legendas", "Áudio", "Reprodução", "Interface", "Rede", "Catálogos", "Contas", "Fontes", "Detalhes", "Reproduzir", "Trailer", "Temporadas", "Temporada", "Episódios", "Elenco", "Avaliações", "Mais como este", "Fechar", "Voltar", "Cancelar", "Confirmar", "Tentar novamente", "Carregando", "Vazio", "Excluir", "Adicionar", "Atualizar", "selecionado", "Entrar", "Sair", "Próximo", "Ao vivo", "Agora", "Mais tarde", "Desligado", "Ligado", "Auto", "Orçamento", "Em andamento", "Procurando fontes...", "Fontes disponíveis", "Nenhum resultado encontrado", "Nenhum resultado encontrado para", "Sua lista está vazia", "Adicione filmes e séries para assistir depois", "fontes disponíveis", "Termina às"),
        "it" to commonUi("Home", "Cerca", "Lista", "Impostazioni", "Generale", "Film", "Film", "Serie TV", "Serie", "Serie", "Tutti i generi", "Qualsiasi lingua", "Lingua app", "Lingua e sottotitoli", "Sottotitoli", "Audio", "Riproduzione", "Interfaccia", "Rete", "Cataloghi", "Account", "Fonti", "Dettagli", "Riproduci", "Trailer", "Stagioni", "Stagione", "Episodi", "Cast", "Recensioni", "Altri simili", "Chiudi", "Indietro", "Annulla", "Conferma", "Riprova", "Caricamento", "Vuoto", "Elimina", "Aggiungi", "Aggiorna", "selezionato", "Accedi", "Esci", "Successivo", "Live", "Ora", "Più tardi", "Spento", "Acceso", "Auto", "Budget", "In corso", "Ricerca fonti...", "Fonti disponibili", "Nessun risultato trovato", "Nessun risultato trovato per", "La tua lista è vuota", "Aggiungi film e serie da guardare più tardi", "fonti disponibili", "Finisce alle"),
        "ru" to commonUi("Главная", "Поиск", "Список", "Настройки", "Общие", "Фильмы", "Фильм", "Сериалы", "Сериалы", "Сериал", "Все жанры", "Любой язык", "Язык приложения", "Язык и субтитры", "Субтитры", "Аудио", "Воспроизведение", "Интерфейс", "Сеть", "Каталоги", "Аккаунты", "Источники", "Детали", "Смотреть", "Трейлер", "Сезоны", "Сезон", "Эпизоды", "Актеры", "Отзывы", "Похожие", "Закрыть", "Назад", "Отмена", "Подтвердить", "Повторить", "Загрузка", "Пусто", "Удалить", "Добавить", "Обновить", "выбрано", "Войти", "Выйти", "Далее", "Эфир", "Сейчас", "Позже", "Выкл", "Вкл", "Авто", "Бюджет", "Идет", "Поиск источников...", "Доступные источники", "Ничего не найдено", "Ничего не найдено для", "Ваш список пуст", "Добавьте фильмы и сериалы на потом", "источников доступно", "Заканчивается в"),
        "ja" to commonUi("ホーム", "検索", "ウォッチリスト", "設定", "一般", "映画", "映画", "テレビ番組", "番組", "シリーズ", "すべてのジャンル", "すべての言語", "アプリの言語", "言語と字幕", "字幕", "音声", "再生", "インターフェース", "ネットワーク", "カタログ", "アカウント", "ソース", "詳細", "再生", "予告編", "シーズン", "シーズン", "エピソード", "出演者", "レビュー", "関連作品", "閉じる", "戻る", "キャンセル", "確認", "再試行", "読み込み中", "空", "削除", "追加", "更新", "選択済み", "サインイン", "ログアウト", "次", "ライブ", "現在", "後で", "オフ", "オン", "自動", "予算", "配信中", "ソースを検索中...", "利用可能なソース", "結果が見つかりません", "結果が見つかりません:", "ウォッチリストは空です", "後で見る映画や番組を追加", "件のソースが利用可能", "終了"),
        "ko" to commonUi("홈", "검색", "시청 목록", "설정", "일반", "영화", "영화", "TV 프로그램", "프로그램", "시리즈", "모든 장르", "모든 언어", "앱 언어", "언어 및 자막", "자막", "오디오", "재생", "인터페이스", "네트워크", "카탈로그", "계정", "소스", "상세 정보", "재생", "예고편", "시즌", "시즌", "에피소드", "출연진", "리뷰", "비슷한 콘텐츠", "닫기", "뒤로", "취소", "확인", "다시 시도", "로딩 중", "비어 있음", "삭제", "추가", "새로고침", "선택됨", "로그인", "로그아웃", "다음", "라이브", "지금", "나중에", "끔", "켬", "자동", "예산", "방영 중", "소스 찾는 중...", "사용 가능한 소스", "결과가 없습니다", "결과가 없습니다:", "시청 목록이 비어 있습니다", "나중에 볼 영화와 프로그램 추가", "개의 소스 사용 가능", "종료"),
        "zh-CN" to commonUi("首页", "搜索", "片单", "设置", "通用", "电影", "电影", "电视剧", "剧集", "系列", "所有类型", "任意语言", "应用语言", "语言和字幕", "字幕", "音频", "播放", "界面", "网络", "目录", "账号", "来源", "详情", "播放", "预告片", "季", "季", "集", "演员", "评价", "更多类似", "关闭", "返回", "取消", "确认", "重试", "加载中", "空", "删除", "添加", "刷新", "已选择", "登录", "退出登录", "下一个", "直播", "现在", "稍后", "关", "开", "自动", "预算", "连载中", "正在查找来源...", "可用来源", "未找到结果", "未找到结果:", "片单为空", "添加电影和剧集稍后观看", "个来源可用", "结束于"),
        "zh-TW" to commonUi("首頁", "搜尋", "片單", "設定", "一般", "電影", "電影", "電視節目", "節目", "影集", "所有類型", "任何語言", "應用程式語言", "語言與字幕", "字幕", "音訊", "播放", "介面", "網路", "目錄", "帳號", "來源", "詳細資訊", "播放", "預告片", "季", "季", "集", "演員", "評論", "更多類似", "關閉", "返回", "取消", "確認", "重試", "載入中", "空", "刪除", "新增", "重新整理", "已選取", "登入", "登出", "下一個", "直播", "現在", "稍後", "關", "開", "自動", "預算", "連載中", "正在尋找來源...", "可用來源", "找不到結果", "找不到結果:", "片單是空的", "新增電影與節目稍後觀看", "個來源可用", "結束於"),
        "ar" to commonUi("الرئيسية", "بحث", "قائمة المشاهدة", "الإعدادات", "عام", "أفلام", "فيلم", "مسلسلات", "عروض", "مسلسل", "كل الأنواع", "أي لغة", "لغة التطبيق", "اللغة والترجمات", "ترجمات", "الصوت", "التشغيل", "الواجهة", "الشبكة", "الكتالوجات", "الحسابات", "المصادر", "التفاصيل", "تشغيل", "الإعلان", "المواسم", "موسم", "الحلقات", "طاقم العمل", "المراجعات", "المزيد مثل هذا", "إغلاق", "رجوع", "إلغاء", "تأكيد", "إعادة المحاولة", "جار التحميل", "فارغ", "حذف", "إضافة", "تحديث", "محدد", "تسجيل الدخول", "تسجيل الخروج", "التالي", "مباشر", "الآن", "لاحقا", "إيقاف", "تشغيل", "تلقائي", "الميزانية", "مستمر", "جار البحث عن المصادر...", "المصادر المتاحة", "لم يتم العثور على نتائج", "لم يتم العثور على نتائج لـ", "قائمة المشاهدة فارغة", "أضف أفلاما ومسلسلات للمشاهدة لاحقا", "مصادر متاحة", "ينتهي في"),
        "hi" to commonUi("होम", "खोज", "वॉचलिस्ट", "सेटिंग्स", "सामान्य", "फिल्में", "फिल्म", "टीवी शो", "शो", "सीरीज", "सभी शैली", "कोई भी भाषा", "ऐप भाषा", "भाषा और सबटाइटल", "सबटाइटल", "ऑडियो", "प्लेबैक", "इंटरफेस", "नेटवर्क", "कैटलॉग", "खाते", "स्रोत", "विवरण", "चलाएं", "ट्रेलर", "सीजन", "सीजन", "एपिसोड", "कास्ट", "समीक्षाएं", "ऐसे और", "बंद करें", "वापस", "रद्द करें", "पुष्टि करें", "फिर कोशिश करें", "लोड हो रहा है", "खाली", "हटाएं", "जोड़ें", "रीफ्रेश", "चयनित", "साइन इन", "लॉग आउट", "अगला", "लाइव", "अभी", "बाद में", "बंद", "चालू", "ऑटो", "बजट", "जारी", "स्रोत खोजे जा रहे हैं...", "उपलब्ध स्रोत", "कोई परिणाम नहीं मिला", "कोई परिणाम नहीं मिला:", "आपकी वॉचलिस्ट खाली है", "बाद में देखने के लिए फिल्में और शो जोड़ें", "स्रोत उपलब्ध", "समाप्त"),
        "tr" to commonUi("Ana sayfa", "Ara", "İzleme listesi", "Ayarlar", "Genel", "Filmler", "Film", "Diziler", "Diziler", "Dizi", "Tüm türler", "Herhangi bir dil", "Uygulama dili", "Dil ve altyazılar", "Altyazılar", "Ses", "Oynatma", "Arayüz", "Ağ", "Kataloglar", "Hesaplar", "Kaynaklar", "Detaylar", "Oynat", "Fragman", "Sezonlar", "Sezon", "Bölümler", "Oyuncular", "İncelemeler", "Benzerleri", "Kapat", "Geri", "İptal", "Onayla", "Tekrar dene", "Yükleniyor", "Boş", "Sil", "Ekle", "Yenile", "seçili", "Giriş yap", "Çıkış yap", "Sonraki", "Canlı", "Şimdi", "Sonra", "Kapalı", "Açık", "Otomatik", "Bütçe", "Devam ediyor", "Kaynaklar bulunuyor...", "Mevcut kaynaklar", "Sonuç bulunamadı", "Sonuç bulunamadı:", "İzleme listen boş", "Daha sonra izlemek için film ve dizi ekle", "kaynak mevcut", "Bitiş"),
        "pl" to commonUi("Start", "Szukaj", "Lista", "Ustawienia", "Ogólne", "Filmy", "Film", "Seriale", "Seriale", "Serial", "Wszystkie gatunki", "Dowolny język", "Język aplikacji", "Język i napisy", "Napisy", "Dźwięk", "Odtwarzanie", "Interfejs", "Sieć", "Katalogi", "Konta", "Źródła", "Szczegóły", "Odtwórz", "Zwiastun", "Sezony", "Sezon", "Odcinki", "Obsada", "Recenzje", "Podobne", "Zamknij", "Wstecz", "Anuluj", "Potwierdź", "Spróbuj ponownie", "Ładowanie", "Puste", "Usuń", "Dodaj", "Odśwież", "wybrano", "Zaloguj", "Wyloguj", "Dalej", "Na żywo", "Teraz", "Później", "Wył.", "Wł.", "Auto", "Budżet", "Trwa", "Szukanie źródeł...", "Dostępne źródła", "Brak wyników", "Brak wyników dla", "Twoja lista jest pusta", "Dodaj filmy i seriale na później", "źródeł dostępnych", "Kończy się o"),
        "sv" to commonUi("Hem", "Sök", "Bevakningslista", "Inställningar", "Allmänt", "Filmer", "Film", "TV-serier", "Serier", "Serie", "Alla genrer", "Valfritt språk", "Appspråk", "Språk och undertexter", "Undertexter", "Ljud", "Uppspelning", "Gränssnitt", "Nätverk", "Kataloger", "Konton", "Källor", "Detaljer", "Spela", "Trailer", "Säsonger", "Säsong", "Avsnitt", "Rollista", "Recensioner", "Mer liknande", "Stäng", "Tillbaka", "Avbryt", "Bekräfta", "Försök igen", "Laddar", "Tom", "Ta bort", "Lägg till", "Uppdatera", "valt", "Logga in", "Logga ut", "Nästa", "Live", "Nu", "Senare", "Av", "På", "Auto", "Budget", "Pågående", "Hittar källor...", "Tillgängliga källor", "Inga resultat hittades", "Inga resultat hittades för", "Din lista är tom", "Lägg till filmer och serier att se senare", "källor tillgängliga", "Slutar kl."),
        "da" to commonUi("Hjem", "Søg", "Watchlist", "Indstillinger", "Generelt", "Film", "Film", "TV-serier", "Serier", "Serie", "Alle genrer", "Alle sprog", "Appsprog", "Sprog og undertekster", "Undertekster", "Lyd", "Afspilning", "Interface", "Netværk", "Kataloger", "Konti", "Kilder", "Detaljer", "Afspil", "Trailer", "Sæsoner", "Sæson", "Episoder", "Medvirkende", "Anmeldelser", "Mere som dette", "Luk", "Tilbage", "Annuller", "Bekræft", "Prøv igen", "Indlæser", "Tom", "Slet", "Tilføj", "Opdater", "valgt", "Log ind", "Log ud", "Næste", "Live", "Nu", "Senere", "Fra", "Til", "Auto", "Budget", "I gang", "Finder kilder...", "Tilgængelige kilder", "Ingen resultater fundet", "Ingen resultater fundet for", "Din watchlist er tom", "Tilføj film og serier til senere", "kilder tilgængelige", "Slutter kl."),
        "no" to commonUi("Hjem", "Søk", "Se senere", "Innstillinger", "Generelt", "Filmer", "Film", "TV-serier", "Serier", "Serie", "Alle sjangre", "Alle språk", "Appspråk", "Språk og undertekster", "Undertekster", "Lyd", "Avspilling", "Grensesnitt", "Nettverk", "Kataloger", "Kontoer", "Kilder", "Detaljer", "Spill av", "Trailer", "Sesonger", "Sesong", "Episoder", "Rolleliste", "Anmeldelser", "Mer som dette", "Lukk", "Tilbake", "Avbryt", "Bekreft", "Prøv igjen", "Laster", "Tom", "Slett", "Legg til", "Oppdater", "valgt", "Logg inn", "Logg ut", "Neste", "Direkte", "Nå", "Senere", "Av", "På", "Auto", "Budsjett", "Pågår", "Finner kilder...", "Tilgjengelige kilder", "Ingen resultater funnet", "Ingen resultater funnet for", "Listen din er tom", "Legg til filmer og serier for senere", "kilder tilgjengelig", "Slutter kl."),
        "fi" to commonUi("Koti", "Haku", "Katselulista", "Asetukset", "Yleiset", "Elokuvat", "Elokuva", "TV-sarjat", "Sarjat", "Sarja", "Kaikki lajityypit", "Mikä tahansa kieli", "Sovelluksen kieli", "Kieli ja tekstitykset", "Tekstitykset", "Ääni", "Toisto", "Käyttöliittymä", "Verkko", "Katalogit", "Tilit", "Lähteet", "Tiedot", "Toista", "Traileri", "Kaudet", "Kausi", "Jaksot", "Näyttelijät", "Arvostelut", "Lisää samanlaisia", "Sulje", "Takaisin", "Peruuta", "Vahvista", "Yritä uudelleen", "Ladataan", "Tyhjä", "Poista", "Lisää", "Päivitä", "valittu", "Kirjaudu sisään", "Kirjaudu ulos", "Seuraava", "Live", "Nyt", "Myöhemmin", "Pois", "Päällä", "Auto", "Budjetti", "Jatkuu", "Etsitään lähteitä...", "Saatavilla olevat lähteet", "Ei tuloksia", "Ei tuloksia haulle", "Katselulistasi on tyhjä", "Lisää elokuvia ja sarjoja myöhemmäksi", "lähdettä saatavilla", "Päättyy"),
        "el" to commonUi("Αρχική", "Αναζήτηση", "Λίστα", "Ρυθμίσεις", "Γενικά", "Ταινίες", "Ταινία", "Σειρές TV", "Σειρές", "Σειρά", "Όλα τα είδη", "Οποιαδήποτε γλώσσα", "Γλώσσα εφαρμογής", "Γλώσσα και υπότιτλοι", "Υπότιτλοι", "Ήχος", "Αναπαραγωγή", "Διεπαφή", "Δίκτυο", "Κατάλογοι", "Λογαριασμοί", "Πηγές", "Λεπτομέρειες", "Αναπαραγωγή", "Τρέιλερ", "Σεζόν", "Σεζόν", "Επεισόδια", "Ηθοποιοί", "Κριτικές", "Περισσότερα παρόμοια", "Κλείσιμο", "Πίσω", "Άκυρο", "Επιβεβαίωση", "Δοκιμή ξανά", "Φόρτωση", "Κενό", "Διαγραφή", "Προσθήκη", "Ανανέωση", "επιλέχθηκε", "Σύνδεση", "Αποσύνδεση", "Επόμενο", "Ζωντανά", "Τώρα", "Αργότερα", "Ανενεργό", "Ενεργό", "Αυτόματο", "Προϋπολογισμός", "Σε εξέλιξη", "Αναζήτηση πηγών...", "Διαθέσιμες πηγές", "Δεν βρέθηκαν αποτελέσματα", "Δεν βρέθηκαν αποτελέσματα για", "Η λίστα σας είναι κενή", "Προσθέστε ταινίες και σειρές για αργότερα", "διαθέσιμες πηγές", "Τελειώνει στις"),
        "cs" to commonUi("Domů", "Hledat", "Seznam", "Nastavení", "Obecné", "Filmy", "Film", "TV pořady", "Pořady", "Seriál", "Všechny žánry", "Libovolný jazyk", "Jazyk aplikace", "Jazyk a titulky", "Titulky", "Zvuk", "Přehrávání", "Rozhraní", "Síť", "Katalogy", "Účty", "Zdroje", "Detaily", "Přehrát", "Trailer", "Řady", "Řada", "Epizody", "Obsazení", "Recenze", "Podobné", "Zavřít", "Zpět", "Zrušit", "Potvrdit", "Zkusit znovu", "Načítání", "Prázdné", "Smazat", "Přidat", "Obnovit", "vybráno", "Přihlásit", "Odhlásit", "Další", "Živě", "Nyní", "Později", "Vyp", "Zap", "Auto", "Rozpočet", "Probíhá", "Hledání zdrojů...", "Dostupné zdroje", "Nenalezeny žádné výsledky", "Nenalezeny žádné výsledky pro", "Váš seznam je prázdný", "Přidejte filmy a seriály na později", "zdrojů dostupných", "Končí v"),
        "hu" to commonUi("Kezdőlap", "Keresés", "Figyelőlista", "Beállítások", "Általános", "Filmek", "Film", "TV-műsorok", "Műsorok", "Sorozat", "Minden műfaj", "Bármely nyelv", "Alkalmazás nyelve", "Nyelv és feliratok", "Feliratok", "Hang", "Lejátszás", "Felület", "Hálózat", "Katalógusok", "Fiókok", "Források", "Részletek", "Lejátszás", "Előzetes", "Évadok", "Évad", "Epizódok", "Szereplők", "Vélemények", "Hasonlók", "Bezárás", "Vissza", "Mégse", "Megerősítés", "Újra", "Betöltés", "Üres", "Törlés", "Hozzáadás", "Frissítés", "kiválasztva", "Bejelentkezés", "Kijelentkezés", "Következő", "Élő", "Most", "Később", "Ki", "Be", "Auto", "Költségvetés", "Folyamatban", "Források keresése...", "Elérhető források", "Nincs találat", "Nincs találat erre", "A figyelőlistád üres", "Adj hozzá filmeket és sorozatokat későbbre", "forrás érhető el", "Vége"),
        "ro" to commonUi("Acasă", "Căutare", "Listă", "Setări", "General", "Filme", "Film", "Seriale TV", "Seriale", "Serial", "Toate genurile", "Orice limbă", "Limba aplicației", "Limbă și subtitrări", "Subtitrări", "Audio", "Redare", "Interfață", "Rețea", "Cataloage", "Conturi", "Surse", "Detalii", "Redă", "Trailer", "Sezoane", "Sezon", "Episoade", "Distribuție", "Recenzii", "Mai multe similare", "Închide", "Înapoi", "Anulează", "Confirmă", "Reîncearcă", "Se încarcă", "Gol", "Șterge", "Adaugă", "Actualizează", "selectat", "Autentificare", "Deconectare", "Următorul", "Live", "Acum", "Mai târziu", "Oprit", "Pornit", "Auto", "Buget", "În desfășurare", "Se caută surse...", "Surse disponibile", "Nu s-au găsit rezultate", "Nu s-au găsit rezultate pentru", "Lista ta este goală", "Adaugă filme și seriale pentru mai târziu", "surse disponibile", "Se termină la"),
        "th" to commonUi("หน้าแรก", "ค้นหา", "รายการดู", "ตั้งค่า", "ทั่วไป", "ภาพยนตร์", "ภาพยนตร์", "รายการทีวี", "รายการ", "ซีรีส์", "ทุกแนว", "ทุกภาษา", "ภาษาแอป", "ภาษาและคำบรรยาย", "คำบรรยาย", "เสียง", "การเล่น", "อินเทอร์เฟซ", "เครือข่าย", "แคตตาล็อก", "บัญชี", "แหล่งที่มา", "รายละเอียด", "เล่น", "ตัวอย่าง", "ซีซัน", "ซีซัน", "ตอน", "นักแสดง", "รีวิว", "คล้ายกัน", "ปิด", "ย้อนกลับ", "ยกเลิก", "ยืนยัน", "ลองอีกครั้ง", "กำลังโหลด", "ว่าง", "ลบ", "เพิ่ม", "รีเฟรช", "เลือกแล้ว", "เข้าสู่ระบบ", "ออกจากระบบ", "ถัดไป", "สด", "ตอนนี้", "ภายหลัง", "ปิด", "เปิด", "อัตโนมัติ", "งบประมาณ", "กำลังดำเนินอยู่", "กำลังค้นหาแหล่งที่มา...", "แหล่งที่มาที่ใช้ได้", "ไม่พบผลลัพธ์", "ไม่พบผลลัพธ์สำหรับ", "รายการดูของคุณว่าง", "เพิ่มภาพยนตร์และรายการไว้ดูภายหลัง", "แหล่งที่มาใช้ได้", "สิ้นสุดเวลา"),
        "vi" to commonUi("Trang chủ", "Tìm kiếm", "Danh sách xem", "Cài đặt", "Chung", "Phim", "Phim", "Chương trình TV", "Chương trình", "Phim bộ", "Tất cả thể loại", "Bất kỳ ngôn ngữ nào", "Ngôn ngữ ứng dụng", "Ngôn ngữ và phụ đề", "Phụ đề", "Âm thanh", "Phát", "Giao diện", "Mạng", "Danh mục", "Tài khoản", "Nguồn", "Chi tiết", "Phát", "Trailer", "Mùa", "Mùa", "Tập", "Diễn viên", "Đánh giá", "Nội dung tương tự", "Đóng", "Quay lại", "Hủy", "Xác nhận", "Thử lại", "Đang tải", "Trống", "Xóa", "Thêm", "Làm mới", "đã chọn", "Đăng nhập", "Đăng xuất", "Tiếp theo", "Trực tiếp", "Bây giờ", "Sau", "Tắt", "Bật", "Tự động", "Ngân sách", "Đang phát", "Đang tìm nguồn...", "Nguồn có sẵn", "Không tìm thấy kết quả", "Không tìm thấy kết quả cho", "Danh sách xem trống", "Thêm phim và chương trình để xem sau", "nguồn có sẵn", "Kết thúc lúc"),
        "id" to commonUi("Beranda", "Cari", "Daftar Tonton", "Pengaturan", "Umum", "Film", "Film", "Acara TV", "Acara", "Serial", "Semua genre", "Bahasa apa saja", "Bahasa aplikasi", "Bahasa dan subtitle", "Subtitle", "Audio", "Pemutaran", "Antarmuka", "Jaringan", "Katalog", "Akun", "Sumber", "Detail", "Putar", "Trailer", "Musim", "Musim", "Episode", "Pemeran", "Ulasan", "Lainnya seperti ini", "Tutup", "Kembali", "Batal", "Konfirmasi", "Coba lagi", "Memuat", "Kosong", "Hapus", "Tambah", "Segarkan", "dipilih", "Masuk", "Keluar", "Berikutnya", "Langsung", "Sekarang", "Nanti", "Mati", "Nyala", "Otomatis", "Anggaran", "Berjalan", "Mencari sumber...", "Sumber tersedia", "Tidak ada hasil", "Tidak ada hasil untuk", "Daftar tonton kosong", "Tambahkan film dan acara untuk nanti", "sumber tersedia", "Berakhir pukul"),
        "ms" to commonUi("Utama", "Cari", "Senarai tonton", "Tetapan", "Umum", "Filem", "Filem", "Rancangan TV", "Rancangan", "Siri", "Semua genre", "Mana-mana bahasa", "Bahasa aplikasi", "Bahasa dan sari kata", "Sari kata", "Audio", "Main balik", "Antara muka", "Rangkaian", "Katalog", "Akaun", "Sumber", "Butiran", "Main", "Treler", "Musim", "Musim", "Episod", "Pelakon", "Ulasan", "Lagi seperti ini", "Tutup", "Kembali", "Batal", "Sahkan", "Cuba lagi", "Memuatkan", "Kosong", "Padam", "Tambah", "Segar semula", "dipilih", "Log masuk", "Log keluar", "Seterusnya", "Langsung", "Sekarang", "Kemudian", "Mati", "Hidup", "Auto", "Bajet", "Sedang berlangsung", "Mencari sumber...", "Sumber tersedia", "Tiada keputusan ditemui", "Tiada keputusan ditemui untuk", "Senarai tonton anda kosong", "Tambah filem dan rancangan untuk kemudian", "sumber tersedia", "Tamat pada"),
        "tl" to commonUi("Home", "Hanapin", "Watchlist", "Mga setting", "Pangkalahatan", "Mga pelikula", "Pelikula", "Mga palabas sa TV", "Mga palabas", "Serye", "Lahat ng genre", "Anumang wika", "Wika ng app", "Wika at subtitle", "Mga subtitle", "Audio", "Playback", "Interface", "Network", "Mga katalogo", "Mga account", "Mga source", "Detalye", "I-play", "Trailer", "Mga season", "Season", "Mga episode", "Cast", "Mga review", "Katulad nito", "Isara", "Bumalik", "Kanselahin", "Kumpirmahin", "Subukan muli", "Naglo-load", "Walang laman", "Tanggalin", "Idagdag", "I-refresh", "napili", "Mag-sign in", "Mag-log out", "Susunod", "Live", "Ngayon", "Mamaya", "Off", "On", "Auto", "Budget", "Patuloy", "Naghahanap ng sources...", "Available na sources", "Walang nahanap na resulta", "Walang nahanap na resulta para sa", "Walang laman ang watchlist mo", "Magdagdag ng pelikula at palabas para mamaya", "sources available", "Matatapos sa"),
        "uk" to commonUi("Головна", "Пошук", "Список", "Налаштування", "Загальні", "Фільми", "Фільм", "Серіали", "Шоу", "Серіал", "Усі жанри", "Будь-яка мова", "Мова застосунку", "Мова і субтитри", "Субтитри", "Аудіо", "Відтворення", "Інтерфейс", "Мережа", "Каталоги", "Акаунти", "Джерела", "Деталі", "Дивитись", "Трейлер", "Сезони", "Сезон", "Епізоди", "Актори", "Відгуки", "Схоже", "Закрити", "Назад", "Скасувати", "Підтвердити", "Спробувати знову", "Завантаження", "Порожньо", "Видалити", "Додати", "Оновити", "вибрано", "Увійти", "Вийти", "Далі", "Наживо", "Зараз", "Пізніше", "Вимк", "Увімк", "Авто", "Бюджет", "Триває", "Пошук джерел...", "Доступні джерела", "Нічого не знайдено", "Нічого не знайдено для", "Ваш список порожній", "Додайте фільми й серіали на потім", "джерел доступно", "Закінчується о"),
        "bg" to commonUi("Начало", "Търсене", "Списък", "Настройки", "Общи", "Филми", "Филм", "ТВ предавания", "Предавания", "Сериал", "Всички жанрове", "Всеки език", "Език на приложението", "Език и субтитри", "Субтитри", "Аудио", "Възпроизвеждане", "Интерфейс", "Мрежа", "Каталози", "Акаунти", "Източници", "Детайли", "Пусни", "Трейлър", "Сезони", "Сезон", "Епизоди", "Актьори", "Рецензии", "Още подобни", "Затвори", "Назад", "Отказ", "Потвърди", "Опитай пак", "Зареждане", "Празно", "Изтрий", "Добави", "Обнови", "избрано", "Вход", "Изход", "Следващ", "На живо", "Сега", "По-късно", "Изкл.", "Вкл.", "Авто", "Бюджет", "В процес", "Търсене на източници...", "Налични източници", "Няма резултати", "Няма резултати за", "Списъкът ви е празен", "Добавете филми и сериали за по-късно", "източника налични", "Свършва в"),
        "hr" to commonUi("Početna", "Pretraži", "Popis", "Postavke", "Općenito", "Filmovi", "Film", "TV emisije", "Emisije", "Serija", "Svi žanrovi", "Bilo koji jezik", "Jezik aplikacije", "Jezik i titlovi", "Titlovi", "Audio", "Reprodukcija", "Sučelje", "Mreža", "Katalozi", "Računi", "Izvori", "Detalji", "Reproduciraj", "Trailer", "Sezone", "Sezona", "Epizode", "Glumci", "Recenzije", "Slično", "Zatvori", "Natrag", "Odustani", "Potvrdi", "Pokušaj ponovno", "Učitavanje", "Prazno", "Izbriši", "Dodaj", "Osvježi", "odabrano", "Prijava", "Odjava", "Sljedeće", "Uživo", "Sada", "Kasnije", "Isklj.", "Uklj.", "Auto", "Budžet", "U tijeku", "Traženje izvora...", "Dostupni izvori", "Nema rezultata", "Nema rezultata za", "Vaš popis je prazan", "Dodajte filmove i serije za kasnije", "izvora dostupno", "Završava u"),
        "sr" to commonUi("Почетна", "Претрага", "Листа", "Подешавања", "Опште", "Филмови", "Филм", "ТВ емисије", "Емисије", "Серија", "Сви жанрови", "Било који језик", "Језик апликације", "Језик и титлови", "Титлови", "Аудио", "Репродукција", "Интерфејс", "Мрежа", "Каталози", "Налози", "Извори", "Детаљи", "Пусти", "Трејлер", "Сезоне", "Сезона", "Епизоде", "Глумци", "Рецензије", "Још сличног", "Затвори", "Назад", "Откажи", "Потврди", "Покушај поново", "Учитавање", "Празно", "Обриши", "Додај", "Освежи", "изабрано", "Пријава", "Одјава", "Следеће", "Уживо", "Сада", "Касније", "Искљ.", "Укљ.", "Ауто", "Буџет", "У току", "Тражење извора...", "Доступни извори", "Нема резултата", "Нема резултата за", "Ваша листа је празна", "Додајте филмове и серије за касније", "извора доступно", "Завршава се у"),
        "sk" to commonUi("Domov", "Hľadať", "Zoznam", "Nastavenia", "Všeobecné", "Filmy", "Film", "TV relácie", "Relácie", "Seriál", "Všetky žánre", "Ľubovoľný jazyk", "Jazyk aplikácie", "Jazyk a titulky", "Titulky", "Zvuk", "Prehrávanie", "Rozhranie", "Sieť", "Katalógy", "Účty", "Zdroje", "Detaily", "Prehrať", "Trailer", "Série", "Séria", "Epizódy", "Obsadenie", "Recenzie", "Podobné", "Zavrieť", "Späť", "Zrušiť", "Potvrdiť", "Skúsiť znova", "Načítava sa", "Prázdne", "Odstrániť", "Pridať", "Obnoviť", "vybrané", "Prihlásiť", "Odhlásiť", "Ďalšie", "Naživo", "Teraz", "Neskôr", "Vyp", "Zap", "Auto", "Rozpočet", "Prebieha", "Hľadanie zdrojov...", "Dostupné zdroje", "Nenašli sa výsledky", "Nenašli sa výsledky pre", "Váš zoznam je prázdny", "Pridajte filmy a seriály na neskôr", "zdrojov dostupných", "Končí o"),
        "sl" to commonUi("Domov", "Iskanje", "Seznam", "Nastavitve", "Splošno", "Filmi", "Film", "TV oddaje", "Oddaje", "Serija", "Vsi žanri", "Kateri koli jezik", "Jezik aplikacije", "Jezik in podnapisi", "Podnapisi", "Zvok", "Predvajanje", "Vmesnik", "Omrežje", "Katalogi", "Računi", "Viri", "Podrobnosti", "Predvajaj", "Napovednik", "Sezone", "Sezona", "Epizode", "Igralci", "Ocene", "Več podobnega", "Zapri", "Nazaj", "Prekliči", "Potrdi", "Poskusi znova", "Nalaganje", "Prazno", "Izbriši", "Dodaj", "Osveži", "izbrano", "Prijava", "Odjava", "Naslednje", "V živo", "Zdaj", "Pozneje", "Izkl.", "Vkl.", "Samodejno", "Proračun", "V teku", "Iskanje virov...", "Razpoložljivi viri", "Ni najdenih rezultatov", "Ni najdenih rezultatov za", "Vaš seznam je prazen", "Dodajte filme in serije za pozneje", "virov na voljo", "Konča se ob"),
        "he" to commonUi("בית", "חיפוש", "רשימת צפייה", "הגדרות", "כללי", "סרטים", "סרט", "סדרות טלוויזיה", "תוכניות", "סדרה", "כל הז'אנרים", "כל שפה", "שפת האפליקציה", "שפה וכתוביות", "כתוביות", "אודיו", "ניגון", "ממשק", "רשת", "קטלוגים", "חשבונות", "מקורות", "פרטים", "נגן", "טריילר", "עונות", "עונה", "פרקים", "שחקנים", "ביקורות", "עוד דומים", "סגור", "חזרה", "ביטול", "אישור", "נסה שוב", "טוען", "ריק", "מחק", "הוסף", "רענן", "נבחר", "כניסה", "יציאה", "הבא", "שידור חי", "עכשיו", "מאוחר יותר", "כבוי", "פועל", "אוטומטי", "תקציב", "מתמשך", "מחפש מקורות...", "מקורות זמינים", "לא נמצאו תוצאות", "לא נמצאו תוצאות עבור", "רשימת הצפייה ריקה", "הוסף סרטים וסדרות לצפייה מאוחר יותר", "מקורות זמינים", "מסתיים ב"),
        "fa" to commonUi("خانه", "جستجو", "فهرست تماشا", "تنظیمات", "عمومی", "فیلم‌ها", "فیلم", "برنامه‌های تلویزیونی", "برنامه‌ها", "سریال", "همه ژانرها", "هر زبان", "زبان برنامه", "زبان و زیرنویس", "زیرنویس", "صدا", "پخش", "رابط", "شبکه", "کاتالوگ‌ها", "حساب‌ها", "منابع", "جزئیات", "پخش", "تریلر", "فصل‌ها", "فصل", "قسمت‌ها", "بازیگران", "نقدها", "موارد مشابه", "بستن", "بازگشت", "لغو", "تأیید", "دوباره تلاش کن", "در حال بارگذاری", "خالی", "حذف", "افزودن", "تازه‌سازی", "انتخاب‌شده", "ورود", "خروج", "بعدی", "زنده", "اکنون", "بعدا", "خاموش", "روشن", "خودکار", "بودجه", "در حال پخش", "در حال یافتن منابع...", "منابع موجود", "نتیجه‌ای یافت نشد", "نتیجه‌ای یافت نشد برای", "فهرست تماشای شما خالی است", "فیلم‌ها و سریال‌ها را برای بعد اضافه کنید", "منبع موجود", "پایان در"),
        "bn" to commonUi("হোম", "অনুসন্ধান", "ওয়াচলিস্ট", "সেটিংস", "সাধারণ", "সিনেমা", "সিনেমা", "টিভি শো", "শো", "সিরিজ", "সব ঘরানা", "যে কোনো ভাষা", "অ্যাপের ভাষা", "ভাষা ও সাবটাইটেল", "সাবটাইটেল", "অডিও", "প্লেব্যাক", "ইন্টারফেস", "নেটওয়ার্ক", "ক্যাটালগ", "অ্যাকাউন্ট", "সোর্স", "বিস্তারিত", "চালান", "ট্রেলার", "সিজন", "সিজন", "এপিসোড", "কাস্ট", "রিভিউ", "আরও একই রকম", "বন্ধ", "ফিরে", "বাতিল", "নিশ্চিত", "আবার চেষ্টা", "লোড হচ্ছে", "খালি", "মুছুন", "যোগ করুন", "রিফ্রেশ", "নির্বাচিত", "সাইন ইন", "লগ আউট", "পরবর্তী", "লাইভ", "এখন", "পরে", "বন্ধ", "চালু", "অটো", "বাজেট", "চলমান", "সোর্স খোঁজা হচ্ছে...", "উপলব্ধ সোর্স", "কোনো ফল পাওয়া যায়নি", "কোনো ফল পাওয়া যায়নি:", "আপনার ওয়াচলিস্ট খালি", "পরে দেখার জন্য সিনেমা ও শো যোগ করুন", "সোর্স উপলব্ধ", "শেষ হবে"),
        "ta" to commonUi("முகப்பு", "தேடு", "பார்க்கும் பட்டியல்", "அமைப்புகள்", "பொது", "திரைப்படங்கள்", "திரைப்படம்", "டிவி நிகழ்ச்சிகள்", "நிகழ்ச்சிகள்", "தொடர்", "அனைத்து வகைகள்", "எந்த மொழியும்", "பயன்பாட்டு மொழி", "மொழி மற்றும் வசனங்கள்", "வசனங்கள்", "ஆடியோ", "இயக்கு", "இடைமுகம்", "நெட்வொர்க்", "கேட்டலாக்கள்", "கணக்குகள்", "மூலங்கள்", "விவரங்கள்", "இயக்கு", "ட்ரெய்லர்", "சீசன்கள்", "சீசன்", "எபிசோடுகள்", "நடிகர்கள்", "விமர்சனங்கள்", "இதுபோன்றவை", "மூடு", "பின்", "ரத்து", "உறுதி", "மீண்டும் முயற்சி", "ஏற்றுகிறது", "காலி", "நீக்கு", "சேர்", "புதுப்பி", "தேர்ந்தெடுக்கப்பட்டது", "உள்நுழை", "வெளியேறு", "அடுத்து", "நேரலை", "இப்போது", "பின்னர்", "ஆஃப்", "ஆன்", "தானாக", "பட்ஜெட்", "நடந்து கொண்டிருக்கிறது", "மூலங்கள் தேடப்படுகின்றன...", "கிடைக்கும் மூலங்கள்", "முடிவுகள் இல்லை", "முடிவுகள் இல்லை:", "உங்கள் பார்க்கும் பட்டியல் காலியாக உள்ளது", "பின்னர் பார்க்க திரைப்படங்கள் மற்றும் நிகழ்ச்சிகளைச் சேர்க்கவும்", "மூலங்கள் கிடைக்கும்", "முடிவு"),
        "te" to commonUi("హోమ్", "వెతుకు", "వాచ్‌లిస్ట్", "సెట్టింగ్‌లు", "సాధారణం", "సినిమాలు", "సినిమా", "టీవీ షోలు", "షోలు", "సిరీస్", "అన్ని శైలులు", "ఏ భాషైనా", "యాప్ భాష", "భాష మరియు సబ్‌టైటిల్స్", "సబ్‌టైటిల్స్", "ఆడియో", "ప్లేబ్యాక్", "ఇంటర్‌ఫేస్", "నెట్‌వర్క్", "కాటలాగ్‌లు", "ఖాతాలు", "సోర్స్‌లు", "వివరాలు", "ప్లే", "ట్రైలర్", "సీజన్‌లు", "సీజన్", "ఎపిసోడ్‌లు", "నటీనటులు", "సమీక్షలు", "ఇలాంటివి", "మూసివేయి", "వెనక్కి", "రద్దు", "నిర్ధారించు", "మళ్లీ ప్రయత్నించు", "లోడ్ అవుతోంది", "ఖాళీ", "తొలగించు", "జోడించు", "రిఫ్రెష్", "ఎంచుకున్నది", "సైన్ ఇన్", "లాగ్ అవుట్", "తదుపరి", "లైవ్", "ఇప్పుడు", "తర్వాత", "ఆఫ్", "ఆన్", "ఆటో", "బడ్జెట్", "కొనసాగుతోంది", "సోర్స్‌లు వెతుకుతున్నాం...", "అందుబాటులో ఉన్న సోర్స్‌లు", "ఫలితాలు లేవు", "ఫలితాలు లేవు:", "మీ వాచ్‌లిస్ట్ ఖాళీగా ఉంది", "తర్వాత చూడటానికి సినిమాలు మరియు షోలు జోడించండి", "సోర్స్‌లు అందుబాటులో ఉన్నాయి", "ముగుస్తుంది"),
        "ur" to commonUi("ہوم", "تلاش", "واچ لسٹ", "ترتیبات", "عام", "فلمیں", "فلم", "ٹی وی شوز", "شوز", "سیریز", "تمام اصناف", "کوئی بھی زبان", "ایپ زبان", "زبان اور سب ٹائٹلز", "سب ٹائٹلز", "آڈیو", "پلے بیک", "انٹرفیس", "نیٹ ورک", "کیٹلاگ", "اکاؤنٹس", "ذرائع", "تفصیلات", "چلائیں", "ٹریلر", "سیزنز", "سیزن", "اقساط", "کاسٹ", "تبصرے", "مزید ایسے", "بند", "واپس", "منسوخ", "تصدیق", "دوبارہ کوشش", "لوڈ ہو رہا ہے", "خالی", "حذف", "شامل", "ریفریش", "منتخب", "سائن ان", "لاگ آؤٹ", "اگلا", "لائیو", "ابھی", "بعد میں", "آف", "آن", "آٹو", "بجٹ", "جاری", "ذرائع تلاش ہو رہے ہیں...", "دستیاب ذرائع", "کوئی نتیجہ نہیں ملا", "کوئی نتیجہ نہیں ملا برائے", "آپ کی واچ لسٹ خالی ہے", "بعد میں دیکھنے کے لیے فلمیں اور شوز شامل کریں", "ذرائع دستیاب", "ختم ہوگا"),
        "ca" to commonUi("Inici", "Cerca", "Llista", "Configuració", "General", "Pel·lícules", "Pel·lícula", "Sèries TV", "Sèries", "Sèrie", "Tots els gèneres", "Qualsevol idioma", "Idioma de l'app", "Idioma i subtítols", "Subtítols", "Àudio", "Reproducció", "Interfície", "Xarxa", "Catàlegs", "Comptes", "Fonts", "Detalls", "Reprodueix", "Tràiler", "Temporades", "Temporada", "Episodis", "Repartiment", "Ressenyes", "Més com això", "Tanca", "Enrere", "Cancel·la", "Confirma", "Torna-ho a provar", "Carregant", "Buit", "Suprimeix", "Afegeix", "Actualitza", "seleccionat", "Inicia sessió", "Tanca sessió", "Següent", "En directe", "Ara", "Més tard", "Desactivat", "Activat", "Auto", "Pressupost", "En curs", "Cercant fonts...", "Fonts disponibles", "No s'han trobat resultats", "No s'han trobat resultats per", "La teva llista és buida", "Afegeix pel·lícules i sèries per veure més tard", "fonts disponibles", "Acaba a les"),
        "eu" to commonUi("Hasiera", "Bilatu", "Zerrenda", "Ezarpenak", "Orokorra", "Filmak", "Filma", "TB saioak", "Saioak", "Seriea", "Genero guztiak", "Edozein hizkuntza", "Aplikazioaren hizkuntza", "Hizkuntza eta azpitituluak", "Azpitituluak", "Audioa", "Erreprodukzioa", "Interfazea", "Sarea", "Katalogoak", "Kontuak", "Iturriak", "Xehetasunak", "Erreproduzitu", "Trailerra", "Denboraldiak", "Denboraldia", "Atalak", "Aktoreak", "Iritziak", "Antzeko gehiago", "Itxi", "Atzera", "Utzi", "Berretsi", "Saiatu berriro", "Kargatzen", "Hutsik", "Ezabatu", "Gehitu", "Freskatu", "hautatuta", "Hasi saioa", "Amaitu saioa", "Hurrengoa", "Zuzenean", "Orain", "Geroago", "Itzalita", "Piztuta", "Auto", "Aurrekontua", "Martxan", "Iturriak bilatzen...", "Iturri erabilgarriak", "Ez da emaitzarik aurkitu", "Ez da emaitzarik aurkitu honentzat", "Zure zerrenda hutsik dago", "Gehitu filmak eta saioak gero ikusteko", "iturri erabilgarri", "Amaitzen da"),
        "gl" to commonUi("Inicio", "Buscar", "Lista", "Axustes", "Xeral", "Películas", "Película", "Series TV", "Series", "Serie", "Todos os xéneros", "Calquera idioma", "Idioma da app", "Idioma e subtítulos", "Subtítulos", "Audio", "Reprodución", "Interface", "Rede", "Catálogos", "Contas", "Fontes", "Detalles", "Reproducir", "Tráiler", "Tempadas", "Tempada", "Episodios", "Reparto", "Recensións", "Máis semellante", "Pechar", "Atrás", "Cancelar", "Confirmar", "Tentar de novo", "Cargando", "Baleiro", "Eliminar", "Engadir", "Actualizar", "seleccionado", "Iniciar sesión", "Pechar sesión", "Seguinte", "En directo", "Agora", "Máis tarde", "Desactivado", "Activado", "Auto", "Orzamento", "En curso", "Buscando fontes...", "Fontes dispoñibles", "Non se atoparon resultados", "Non se atoparon resultados para", "A túa lista está baleira", "Engade películas e series para máis tarde", "fontes dispoñibles", "Remata ás"),
        "lt" to commonUi("Pradžia", "Paieška", "Žiūrėti vėliau", "Nustatymai", "Bendra", "Filmai", "Filmas", "TV laidos", "Laidos", "Serialas", "Visi žanrai", "Bet kokia kalba", "Programos kalba", "Kalba ir subtitrai", "Subtitrai", "Garsas", "Atkūrimas", "Sąsaja", "Tinklas", "Katalogai", "Paskyros", "Šaltiniai", "Informacija", "Leisti", "Anonsas", "Sezonai", "Sezonas", "Epizodai", "Aktoriai", "Atsiliepimai", "Daugiau panašių", "Uždaryti", "Atgal", "Atšaukti", "Patvirtinti", "Bandyti dar kartą", "Įkeliama", "Tuščia", "Ištrinti", "Pridėti", "Atnaujinti", "pasirinkta", "Prisijungti", "Atsijungti", "Kitas", "Tiesiogiai", "Dabar", "Vėliau", "Išjungta", "Įjungta", "Auto", "Biudžetas", "Vyksta", "Ieškoma šaltinių...", "Galimi šaltiniai", "Rezultatų nerasta", "Rezultatų nerasta pagal", "Jūsų sąrašas tuščias", "Pridėkite filmų ir laidų vėlesniam žiūrėjimui", "šaltinių pasiekiama", "Baigiasi"),
        "lv" to commonUi("Sākums", "Meklēt", "Skatīšanās saraksts", "Iestatījumi", "Vispārīgi", "Filmas", "Filma", "TV pārraides", "Pārraides", "Seriāls", "Visi žanri", "Jebkura valoda", "Lietotnes valoda", "Valoda un subtitri", "Subtitri", "Audio", "Atskaņošana", "Saskarne", "Tīkls", "Katalogi", "Konti", "Avoti", "Detaļas", "Atskaņot", "Treileris", "Sezonas", "Sezona", "Sērijas", "Aktieri", "Atsauksmes", "Vairāk līdzīga", "Aizvērt", "Atpakaļ", "Atcelt", "Apstiprināt", "Mēģināt vēlreiz", "Ielāde", "Tukšs", "Dzēst", "Pievienot", "Atsvaidzināt", "atlasīts", "Pierakstīties", "Izrakstīties", "Nākamais", "Tiešraide", "Tagad", "Vēlāk", "Izsl.", "Iesl.", "Auto", "Budžets", "Notiek", "Meklē avotus...", "Pieejamie avoti", "Nav atrasti rezultāti", "Nav atrasti rezultāti vaicājumam", "Jūsu saraksts ir tukšs", "Pievienojiet filmas un pārraides vēlākam", "avoti pieejami", "Beidzas"),
        "et" to commonUi("Avaleht", "Otsi", "Vaatamisnimekiri", "Seaded", "Üldine", "Filmid", "Film", "TV-saated", "Saated", "Sari", "Kõik žanrid", "Mis tahes keel", "Rakenduse keel", "Keel ja subtiitrid", "Subtiitrid", "Heli", "Taasesitus", "Liides", "Võrk", "Kataloogid", "Kontod", "Allikad", "Üksikasjad", "Esita", "Treiler", "Hooajad", "Hooaeg", "Episoodid", "Näitlejad", "Arvustused", "Veel sarnaseid", "Sulge", "Tagasi", "Tühista", "Kinnita", "Proovi uuesti", "Laadimine", "Tühi", "Kustuta", "Lisa", "Värskenda", "valitud", "Logi sisse", "Logi välja", "Järgmine", "Otse", "Nüüd", "Hiljem", "Väljas", "Sees", "Auto", "Eelarve", "Käimas", "Allikate otsimine...", "Saadaval allikad", "Tulemusi ei leitud", "Tulemusi ei leitud päringule", "Sinu nimekiri on tühi", "Lisa filme ja saateid hilisemaks", "allikat saadaval", "Lõpeb"),
        "af" to commonUi("Tuis", "Soek", "Kyklys", "Instellings", "Algemeen", "Flieks", "Fliek", "TV-programme", "Programme", "Reeks", "Alle genres", "Enige taal", "Toepassingstaal", "Taal en onderskrifte", "Onderskrifte", "Oudio", "Terugspeel", "Koppelvlak", "Netwerk", "Katalogusse", "Rekeninge", "Bronne", "Besonderhede", "Speel", "Voorskou", "Seisoene", "Seisoen", "Episodes", "Rolverdeling", "Resensies", "Meer soos hierdie", "Sluit", "Terug", "Kanselleer", "Bevestig", "Probeer weer", "Laai", "Leeg", "Vee uit", "Voeg by", "Verfris", "gekies", "Meld aan", "Meld af", "Volgende", "Regstreeks", "Nou", "Later", "Af", "Aan", "Outo", "Begroting", "Aan die gang", "Soek bronne...", "Beskikbare bronne", "Geen resultate gevind", "Geen resultate gevind vir", "Jou kyklys is leeg", "Voeg flieks en programme by vir later", "bronne beskikbaar", "Eindig om"),
        "sw" to commonUi("Nyumbani", "Tafuta", "Orodha ya kutazama", "Mipangilio", "Jumla", "Filamu", "Filamu", "Vipindi vya TV", "Vipindi", "Mfululizo", "Aina zote", "Lugha yoyote", "Lugha ya programu", "Lugha na manukuu", "Manukuu", "Sauti", "Uchezaji", "Kiolesura", "Mtandao", "Katalogi", "Akaunti", "Vyanzo", "Maelezo", "Cheza", "Trela", "Misimu", "Msimu", "Vipindi", "Waigizaji", "Maoni", "Zaidi kama hii", "Funga", "Rudi", "Ghairi", "Thibitisha", "Jaribu tena", "Inapakia", "Tupu", "Futa", "Ongeza", "Onyesha upya", "imechaguliwa", "Ingia", "Toka", "Inayofuata", "Moja kwa moja", "Sasa", "Baadaye", "Zima", "Washa", "Otomatiki", "Bajeti", "Inaendelea", "Inatafuta vyanzo...", "Vyanzo vinavyopatikana", "Hakuna matokeo", "Hakuna matokeo ya", "Orodha yako ni tupu", "Ongeza filamu na vipindi vya kutazama baadaye", "vyanzo vinapatikana", "Inaisha saa"),
        "sq" to commonUi("Kreu", "Kërko", "Lista", "Cilësimet", "Të përgjithshme", "Filma", "Film", "Shfaqje TV", "Shfaqje", "Serial", "Të gjitha zhanret", "Çdo gjuhë", "Gjuha e aplikacionit", "Gjuha dhe titrat", "Titrat", "Audio", "Luajtja", "Ndërfaqja", "Rrjeti", "Katalogët", "Llogaritë", "Burimet", "Detajet", "Luaj", "Trailer", "Sezonet", "Sezoni", "Episodet", "Kasti", "Komentet", "Më shumë si kjo", "Mbyll", "Mbrapa", "Anulo", "Konfirmo", "Provo përsëri", "Duke ngarkuar", "Bosh", "Fshi", "Shto", "Rifresko", "zgjedhur", "Hyr", "Dil", "Tjetër", "Drejtpërdrejt", "Tani", "Më vonë", "Fikur", "Ndezur", "Auto", "Buxheti", "Në vazhdim", "Duke gjetur burime...", "Burime të disponueshme", "Nuk u gjetën rezultate", "Nuk u gjetën rezultate për", "Lista jote është bosh", "Shto filma dhe shfaqje për më vonë", "burime të disponueshme", "Përfundon në")
    )

    private fun commonUi(
        home: String,
        search: String,
        watchlist: String,
        settings: String,
        general: String,
        movies: String,
        movie: String,
        tvShows: String,
        shows: String,
        series: String,
        allGenres: String,
        anyLanguage: String,
        appLanguage: String,
        languageAndSubtitles: String,
        subtitles: String,
        audio: String,
        playback: String,
        interfaceText: String,
        network: String,
        catalogs: String,
        accounts: String,
        sources: String,
        details: String,
        play: String,
        trailer: String,
        seasons: String,
        season: String,
        episodes: String,
        cast: String,
        reviews: String,
        moreLikeThis: String,
        close: String,
        back: String,
        cancel: String,
        confirm: String,
        retry: String,
        loading: String,
        empty: String,
        delete: String,
        add: String,
        refresh: String,
        selected: String,
        signIn: String,
        logOut: String,
        next: String,
        live: String,
        now: String,
        later: String,
        off: String,
        on: String,
        auto: String,
        budget: String,
        ongoing: String,
        findingSources: String,
        availableSources: String,
        noResults: String,
        noResultsFor: String,
        emptyWatchlist: String,
        addLater: String,
        sourcesAvailable: String,
        endsAt: String,
        extras: Map<String, String> = emptyMap()
    ): Map<String, String> = mapOf(
        "Home" to home,
        "Search" to search,
        "Watchlist" to watchlist,
        "TV" to "TV",
        "Settings" to settings,
        "General" to general,
        "IPTV" to "IPTV",
        "Iptv" to "IPTV",
        "Catalogs" to catalogs,
        "Stremio" to "Addons",
        "Accounts" to accounts,
        "MY WATCHLIST" to watchlist.uppercase(Locale.ROOT),
        "Your watchlist is empty" to emptyWatchlist,
        "Add movies and shows to watch later" to addLater,
        "Movies" to movies,
        "Movie" to movie,
        "TV Shows" to tvShows,
        "Shows" to shows,
        "Series" to series,
        "All Genres" to allGenres,
        "Any Language" to anyLanguage,
        "Search or discover... try \"top 10 horror movies\"" to search,
        "No results found" to noResults,
        "No results found for" to noResultsFor,
        "Language & Subtitles" to languageAndSubtitles,
        "App Language" to appLanguage,
        "Content Language" to appLanguage,
        "App text, titles, descriptions and metadata" to appLanguage,
        "Titles, descriptions and metadata" to appLanguage,
        "Default Subtitle" to subtitles,
        "Default Subtitles" to subtitles,
        "Auto-select subtitle language" to subtitles,
        "Default Audio" to audio,
        "Preferred audio track" to audio,
        "Subtitle Size" to subtitles,
        "Text size for subtitles" to subtitles,
        "Subtitle Color" to subtitles,
        "Text color for subtitles" to subtitles,
        "Subtitle Style" to subtitles,
        "Bold, Normal, or Background style for subtitles" to subtitles,
        "Playback" to playback,
        "Auto-Play Next" to next,
        "Start next episode automatically" to next,
        "Autoplay" to auto,
        "Off opens the source picker on Play" to sources,
        "Auto-Play Min Quality" to auto,
        "Min quality for auto-play" to auto,
        "Trailer Auto-Play" to trailer,
        "Play trailers in hero banner" to trailer,
        "Match Frame Rate" to playback,
        "Off, Seamless, or Always" to "$off / $auto",
        "Quality Regex Filters" to "Regex",
        "Exclude quality tiers on this device" to "Regex",
        "Interface" to interfaceText,
        "Card Layout" to interfaceText,
        "Landscape or poster cards" to interfaceText,
        "UI Mode" to interfaceText,
        "Force TV, Tablet, or Phone" to interfaceText,
        "Skip Profile Selection" to interfaceText,
        "Auto-load last used profile" to interfaceText,
        "Clock Format" to interfaceText,
        "Choose 12-hour or 24-hour time" to interfaceText,
        "Show Budget on Home" to budget,
        "Display the movie budget on the home hero banner" to budget,
        "Network" to network,
        "DNS Provider" to network,
        "Resolve API and stream requests" to network,
        "Audio" to audio,
        "Volume Boost" to audio,
        "Amplify quiet sources (via system LoudnessEnhancer)" to audio,
        "Add Playlist" to add,
        "Add up to 3 M3U / Xtream IPTV lists with names" to "IPTV",
        "Create another IPTV list" to add,
        "Refresh IPTV Data" to refresh,
        "Refreshing channels and EPG..." to loading,
        "Reload playlists now" to refresh,
        "Reload playlist and EPG now" to refresh,
        "Delete IPTV Playlists" to delete,
        "No playlists configured" to empty,
        "Remove playlists, EPG and favorites" to delete,
        "Add Catalog" to add,
        "Import a Trakt or MDBList catalog URL" to catalogs,
        "Trakt/MDBList URLs can be added manually. Addon catalogs appear automatically." to catalogs,
        "Linked Accounts" to accounts,
        "Majo Stream" to "Majo Stream",
        "Optional account for syncing profiles, addons, catalogs and IPTV settings" to accounts,
        "App Update" to settings,
        "App Updates" to settings,
        "Unable to load content" to noResults,
        "Retry" to retry,
        "In Cinema" to movies,
        "Details" to details,
        "Included with Prime" to "Prime",
        "View Details" to details,
        "Add to Watchlist" to watchlist,
        "Remove from Watchlist" to watchlist,
        "Mark as Watched" to confirm,
        "Mark as Unwatched" to confirm,
        "Remove from Continue Watching" to delete,
        "Press BACK to close" to close,
        "Trailer" to trailer,
        "Close trailer" to close,
        "Seasons" to seasons,
        "Season" to season,
        "Episodes" to episodes,
        "Cast" to cast,
        "Reviews" to reviews,
        "More Like This" to moreLikeThis,
        "Budget" to budget,
        "BUDGET" to budget.uppercase(Locale.ROOT),
        "ONGOING" to ongoing.uppercase(Locale.ROOT),
        "Sources" to sources,
        "FILTER BY SOURCE" to sources.uppercase(Locale.ROOT),
        "Available Sources" to availableSources,
        "Finding sources..." to findingSources,
        "sources available" to sourcesAvailable,
        "Close" to close,
        "Back" to back,
        "Selected" to selected,
        "Play" to play,
        "Subtitles" to subtitles,
        "Subtitles & Audio" to "$subtitles / $audio",
        "Audio Track" to audio,
        "Next Episode" to next,
        "Switch tabs • Navigate • BACK Close" to "$subtitles • $back • $close",
        "Switch tabs â€¢ Navigate â€¢ BACK Close" to "$subtitles • $back • $close",
        "Majo Stream uses community streaming addons to find video sources. Without at least one streaming addon, content cannot be played." to noResults,
        "No audio tracks available" to audio,
        "TRY AGAIN" to retry.uppercase(Locale.ROOT),
        "GO BACK" to back.uppercase(Locale.ROOT),
        "UP NEXT" to next.uppercase(Locale.ROOT),
        "PLAY NOW" to play.uppercase(Locale.ROOT),
        "CANCEL" to cancel.uppercase(Locale.ROOT),
        "OK" to confirm.uppercase(Locale.ROOT),
        "NOW" to now.uppercase(Locale.ROOT),
        "NEXT" to next.uppercase(Locale.ROOT),
        "Next" to next,
        "LATER" to later.uppercase(Locale.ROOT),
        "LIVE" to live.uppercase(Locale.ROOT),
        "IPTV is not configured" to "IPTV",
        "Back to channel list" to back,
        "Previous channel" to back,
        "Next channel" to next,
        "Off" to off,
        "On" to on,
        "Auto" to auto,
        "Tablet" to "Tablet",
        "Phone" to "Phone",
        "Landscape" to "Landscape",
        "Poster" to "Poster",
        "Medium" to "Medium",
        "White" to "White",
        "Yellow" to "Yellow",
        "Green" to "Green",
        "Cyan" to "Cyan",
        "ADD" to add.uppercase(Locale.ROOT),
        "FULL" to "FULL",
        "LOADING" to loading.uppercase(Locale.ROOT),
        "REFRESH" to refresh.uppercase(Locale.ROOT),
        "EMPTY" to empty.uppercase(Locale.ROOT),
        "DELETE" to delete.uppercase(Locale.ROOT),
        "CONNECTED" to on.uppercase(Locale.ROOT),
        "CONNECT" to signIn.uppercase(Locale.ROOT),
        "Cancel" to cancel,
        "Confirm" to confirm,
        "Create" to add,
        "Remove" to delete,
        "Email" to "Email",
        "Password" to "Password",
        "Sign In" to signIn,
        "Log Out" to logOut,
        "Delete" to delete,
        "selected" to selected,
        "Enter code:" to "Code:",
        "Waiting for authorization... (Press OK to cancel)" to loading,
        "Ends at" to endsAt
    ) + extras
}
