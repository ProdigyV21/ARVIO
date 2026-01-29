package com.arflix.tv.util

import android.util.Log

/**
 * Maps TMDB IDs to Kitsu IDs for anime content
 * Also handles absolute episode numbering for anime
 */
object AnimeMapper {
    private const val TAG = "AnimeMapper"

    /**
     * Hardcoded popular anime mappings (TMDB ID -> Kitsu ID)
     * This is a starter set - in production this would be loaded from a database or API
     */
    private val tmdbToKitsuMap = mapOf(
        // One Piece
        37854 to 12,
        // Naruto
        46260 to 40,
        // Naruto Shippuden
        31910 to 1555,
        // Attack on Titan
        1429 to 7442,
        // My Hero Academia
        65930 to 11469,
        // Demon Slayer
        85937 to 41370,
        // Jujutsu Kaisen
        95479 to 43531,
        // Chainsaw Man
        114410 to 45452,
        // Spy x Family
        202250 to 45613,
        // Bleach
        30984 to 6,
        // Death Note
        13916 to 1376,
        // Fullmetal Alchemist Brotherhood
        31911 to 4595,
        // Steins;Gate
        45782 to 5646,
        // Code Geass
        45745 to 1605,
        // Cowboy Bebop
        1043 to 1,
        // Dragon Ball Z
        12609 to 214,
        // Dragon Ball Super
        68727 to 12243,
        // Tokyo Ghoul
        60574 to 8271,
        // Sword Art Online
        45782 to 5700,
        // Hunter x Hunter
        46298 to 6448,
        // Mob Psycho 100
        69122 to 11696,
        // Vinland Saga
        101280 to 40046,
        // The Promised Neverland
        92320 to 13650,
        // Re:Zero
        71448 to 11979,
        // Overlord
        67133 to 11615,
        // Black Clover
        73223 to 13932
    )

    /**
     * Anime that use absolute episode numbering instead of season/episode
     */
    private val absoluteNumberingAnime = setOf(
        37854,  // One Piece
        46260,  // Naruto
        31910,  // Naruto Shippuden
        12609,  // Dragon Ball Z
        68727,  // Dragon Ball Super
        46298,  // Hunter x Hunter
        73223   // Black Clover
    )

    /**
     * Get Kitsu ID from TMDB ID
     */
    fun getKitsuId(tmdbId: Int): Int? {
        return tmdbToKitsuMap[tmdbId]
    }

    /**
     * Check if a TMDB ID is anime
     */
    fun isAnime(tmdbId: Int): Boolean {
        return tmdbToKitsuMap.containsKey(tmdbId)
    }

    /**
     * Check if anime uses absolute episode numbering
     */
    fun usesAbsoluteNumbering(tmdbId: Int): Boolean {
        return absoluteNumberingAnime.contains(tmdbId)
    }

    /**
     * One Piece TMDB Season -> Absolute Episode Offset
     * TMDB splits One Piece into multiple seasons; Stremio/torrent providers use absolute numbering
     */
    private val onePieceSeasonOffsets = mapOf(
        1 to 0,      // Episodes 1-61 (East Blue)
        2 to 61,     // Episodes 62-77 (Alabasta Arc - part 1)
        3 to 77,     // Episodes 78-91 (Post-Arabasta/Goat Island Arc)
        4 to 91,     // Episodes 92-130 (Skypiea Arc)
        5 to 130,    // Episodes 131-143 (G-8 Arc)
        6 to 143,    // Episodes 144-195 (Water 7 Arc)
        7 to 195,    // Episodes 196-206 (Foxy's Return Arc)
        8 to 206,    // Episodes 207-325 (Enies Lobby Arc)
        9 to 325,    // Episodes 326-336 (Ice Hunter Arc)
        10 to 336,   // Episodes 337-381 (Thriller Bark Arc)
        11 to 381,   // Episodes 382-405 (Sabaody Archipelago Arc)
        12 to 405,   // Episodes 406-407 (Special)
        13 to 407,   // Episodes 408-421 (Amazon Lily Arc)
        14 to 421,   // Episodes 422-458 (Impel Down Arc)
        15 to 458,   // Episodes 459-491 (Marineford Arc)
        16 to 491,   // Episodes 492-516 (Post-War Arc)
        17 to 516,   // Episodes 517-522 (Fish-Man Island filler)
        18 to 522,   // Episodes 523-574 (Fish-Man Island Arc)
        19 to 574,   // Episodes 575-628 (Punk Hazard Arc)
        20 to 628,   // Episodes 629-746 (Dressrosa Arc)
        21 to 746,   // Episodes 747-891 (Whole Cake Island Arc)
        22 to 891    // Episodes 892+ (Wano Country)
    )

    /**
     * Convert season/episode to absolute episode number
     * Uses proper season offsets for supported anime
     */
    fun toAbsoluteEpisode(tmdbId: Int, season: Int, episode: Int): Int {
        if (!usesAbsoluteNumbering(tmdbId)) {
            return episode
        }

        return when (tmdbId) {
            37854 -> { // One Piece - use season offset map
                val offset = onePieceSeasonOffsets[season] ?: 0
                offset + episode
            }
            46260 -> { // Naruto - 220 episodes total, use episode directly
                episode
            }
            31910 -> { // Naruto Shippuden - use episode directly (separate series)
                episode
            }
            12609 -> { // Dragon Ball Z - use episode directly
                episode
            }
            68727 -> { // Dragon Ball Super - use episode directly
                episode
            }
            46298 -> { // Hunter x Hunter (2011) - use episode directly
                episode
            }
            73223 -> { // Black Clover - use episode directly
                episode
            }
            else -> {
                // Default: use episode number directly for long-running shows
                episode
            }
        }
    }

    /**
     * Get the query format for anime streams
     * Some anime need special query formats for scrapers
     */
    fun getAnimeQuery(tmdbId: Int, imdbId: String, season: Int?, episode: Int?): String {
        val kitsuId = getKitsuId(tmdbId)

        return if (kitsuId != null && season != null && episode != null) {
            if (usesAbsoluteNumbering(tmdbId)) {
                val absEpisode = toAbsoluteEpisode(tmdbId, season, episode)
                // Format: kitsu:ID:episode
                "kitsu:$kitsuId:$absEpisode"
            } else {
                // Standard format
                "$imdbId:$season:$episode"
            }
        } else {
            // Fallback to standard query
            if (season != null && episode != null) {
                "$imdbId:$season:$episode"
            } else {
                imdbId
            }
        }
    }

    /**
     * Add new anime mapping (for future extensibility)
     */
    fun addMapping(tmdbId: Int, kitsuId: Int) {
        Log.d(TAG, "Adding anime mapping: TMDB $tmdbId -> Kitsu $kitsuId")
        // In production, this would persist to a database
        (tmdbToKitsuMap as? MutableMap)?.put(tmdbId, kitsuId)
    }
}
