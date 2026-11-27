package com.arflix.tv.music

/** The parts of a search result, in the order the screen shows them. */
enum class SearchSection { TRACKS, ARTISTS, ALBUMS, PLAYLISTS }

/**
 * Puts what the user most likely wants first: a song when they type a song, playlists when they
 * type something like "top gym hits", an artist only when the name is the artist's.
 */
object MusicSearchRanking {

    /** Words that ask for a playlist rather than one song, in the app's languages. */
    private val PLAYLIST_WORDS = setOf(
        "playlist", "playlists", "hits", "top", "mix", "best", "charts", "chart", "songs", "music", "radio",
        "gym", "workout", "running", "chill", "party", "throwback", "classics", "essentials", "vibes",
        "focus", "study", "sleep", "relax", "lofi", "christmas", "summer", "love", "sad", "roadtrip",
        "decade", "oldies", "anthems", "hot", "new", "fitness", "training", "cardio", "dinner", "morning",
        // Dutch
        "afspeellijst", "liedjes", "muziek", "nummers", "feest", "sportschool", "hardlopen", "kerst", "zomer",
        "nederlandstalig", "nederlandse", "beste",
        // German
        "lieder", "musik", "beste", "fitnessstudio", "laufen", "weihnachten", "sommer", "deutsche", "schlager",
        // French, Spanish, Italian, Portuguese
        "musique", "chansons", "tubes", "musica", "canciones", "exitos", "exitos", "canzoni", "musicas", "sucessos",
    )

    private val DECADE = Regex("\\b(19|20)?\\d0s\\b|\\b(19|20)\\d\\d\\b")

    fun wantsPlaylists(query: String): Boolean {
        val q = SpotifyImport.normalize(query)
        if (q.isEmpty()) return false
        val words = q.split(' ')
        return words.any { it in PLAYLIST_WORDS } || DECADE.containsMatchIn(query.lowercase())
    }

    /**
     * How well a song fits the query: its title typed exactly beats a title that only contains the words.
     * [aliases] are the Latin names of an artist typed in another script; their songs beat a title
     * that merely shares a letter with the query.
     */
    internal fun trackScore(query: String, track: MusicTrack, aliases: List<String> = emptyList()): Int {
        val q = SpotifyImport.normalize(query)
        val title = SpotifyImport.normalize(track.title)
        val artist = SpotifyImport.normalize(track.artist)
        if (q.isEmpty() || title.isEmpty()) return 0
        var score = when {
            title == q -> 6
            artist.isNotEmpty() && (q == "$artist $title" || q == "$title $artist") -> 6
            q.contains(title) && title.length * 2 >= q.length -> 4
            q.contains(title) -> 2
            title.contains(q) -> 3
            title.split(' ').any { it.length > 2 && q.split(' ').contains(it) } -> 1
            else -> 0
        }
        if (artist.isNotEmpty() && (q.contains(artist) || artist.contains(q))) score += 2
        if (aliases.any { MusicNameLookup.isArtist(track.artist, it) }) score += 4
        // The plain song beats a remix or live take whose title only differs in brackets.
        if (score > 0 && query.trim().lowercase().contains(track.title.trim().lowercase())) score += 1
        return score
    }

    fun rankTracks(query: String, tracks: List<MusicTrack>, aliases: List<String> = emptyList()): List<MusicTrack> =
        tracks.withIndex().sortedWith(compareByDescending<IndexedValue<MusicTrack>> { trackScore(query, it.value, aliases) }.thenBy { it.index })
            .map { it.value }

    fun order(query: String, result: MusicSearchResult): List<SearchSection> {
        val q = SpotifyImport.normalize(query)
        val names = (result.aliases.map { SpotifyImport.normalize(it) } + q).toSet()
        val artistExact = result.artists.any { SpotifyImport.normalize(it.name) in names }
        val songTyped = result.tracks.any { trackScore(query, it) >= 6 }
        return when {
            result.playlists.isNotEmpty() && wantsPlaylists(query) && !songTyped ->
                listOf(SearchSection.PLAYLISTS, SearchSection.TRACKS, SearchSection.ALBUMS, SearchSection.ARTISTS)
            artistExact && result.tracks.none { SpotifyImport.normalize(it.title) == q } ->
                listOf(SearchSection.ARTISTS, SearchSection.TRACKS, SearchSection.ALBUMS, SearchSection.PLAYLISTS)
            else -> listOf(SearchSection.TRACKS, SearchSection.ALBUMS, SearchSection.ARTISTS, SearchSection.PLAYLISTS)
        }
    }

    /**
     * The home rows: the first chart rows (the country's charts) lead, then the user's own
     * sources' rows, then the rest of the charts.
     */
    fun arrangeHome(shelves: List<MusicShelf>, lead: Int = 4): List<MusicShelf> {
        val (charts, own) = shelves.partition { ChartsSource.isChartShelf(it.key) }
        return charts.take(lead) + own + charts.drop(lead)
    }
}
