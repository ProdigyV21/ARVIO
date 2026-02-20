package com.arflix.tv.util

import android.util.Log
import com.arflix.tv.data.api.StreamApi
import com.arflix.tv.data.api.TmdbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Maps TMDB IDs to Kitsu IDs for anime content and resolves correct episode queries.
 *
 * Uses a 6-tier fallback chain (ARM API is PRIMARY for global accuracy):
 *   Tier 1: ARM API (arm.haglund.dev) - authoritative TMDB→Kitsu mapping (GLOBAL FIX)
 *   Tier 2: Hardcoded per-season Kitsu map (fallback when ARM unavailable)
 *   Tier 3: Hardcoded absolute numbering + offset maps (for long-running series)
 *   Tier 4: Dynamic Kitsu API via TVDB ID mapping (accurate, cached)
 *   Tier 5: Dynamic Kitsu search by title (less reliable)
 *   Tier 6: IMDB format fallback (works with most addons)
 *
 * IMPORTANT: ARM API is now the primary source because hardcoded IDs can become stale.
 * This ensures ALL anime work correctly, not just the ones with hardcoded entries.
 */
@Singleton
class AnimeMapper @Inject constructor(
    private val streamApi: StreamApi,
    private val tmdbApi: TmdbApi
) {
    private val TAG = "AnimeMapper"

    // ========== Caches ==========

    private val cacheMutex = Mutex()
    private val MAX_CACHE_SIZE = 500

    // Typed caches with clear prefixes
    private val tvdbToKitsuCache = mutableMapOf<Int, Int>()        // tvdbId -> kitsuId
    private val titleToKitsuCache = mutableMapOf<String, Int>()    // title (lowercase) -> kitsuId
    private val episodeCountCache = mutableMapOf<Int, Int>()       // kitsuId -> episodeCount
    private val tmdbSeasonEpCountCache = mutableMapOf<String, Int>() // "tmdbId:season" -> episodeCount
    private val sequelCache = mutableMapOf<Int, Int?>()            // kitsuId -> sequelKitsuId (null = no sequel)
    private val hasSequelCache = mutableMapOf<Int, Boolean>()      // kitsuId -> whether it has a sequel
    private val armTmdbCache = mutableMapOf<Int, List<Int>>()      // tmdbId -> list of Kitsu IDs (one per season)

    // ========== Hardcoded Maps ==========

    /**
     * Hardcoded popular anime mappings (TMDB ID -> Kitsu ID)
     * For anime with per-season Kitsu entries, this is the Season 1 ID.
     */
    private val tmdbToKitsuMap = mutableMapOf(
        37854 to 12,      // One Piece
        46260 to 40,      // Naruto
        31910 to 1555,    // Naruto Shippuden
        1429 to 7442,     // Attack on Titan S1
        65930 to 11469,   // My Hero Academia S1
        85937 to 38000,   // Demon Slayer S1
        95479 to 42765,   // Jujutsu Kaisen S1
        114410 to 43806,  // Chainsaw Man (corrected from AniList ID)
        202250 to 45398,  // Spy x Family Part 1 (corrected from AniList ID)
        30984 to 6,       // Bleach
        13916 to 1376,    // Death Note
        31911 to 4595,    // Fullmetal Alchemist Brotherhood
        62085 to 5646,    // Steins;Gate
        1104 to 1415,     // Code Geass
        1043 to 1,        // Cowboy Bebop
        12609 to 214,     // Dragon Ball Z
        68727 to 12243,   // Dragon Ball Super
        60574 to 8271,    // Tokyo Ghoul
        45782 to 6589,    // Sword Art Online
        46298 to 6448,    // Hunter x Hunter (2011)
        69122 to 11696,   // Mob Psycho 100
        101280 to 40046,  // Vinland Saga
        92320 to 41312,   // The Promised Neverland (corrected from AniList ID)
        71448 to 11209,   // Re:Zero (corrected from AniList ID)
        67133 to 9965,    // Overlord (corrected from AniList ID)
        73223 to 13932,   // Black Clover
        127532 to 47058,  // Solo Leveling
        209867 to 46474,  // Frieren (corrected from AniList ID)
        203737 to 47997,  // Oshi no Ko
        127064 to 44973,  // Blue Lock (corrected from AniList ID)
        135157 to 44196,  // Bocchi the Rock (corrected from AniList ID)
        210232 to 48269,  // Dandadan (corrected from AniList ID)
        225439 to 46300,  // Kaiju No. 8 (corrected from AniList ID)
        154526 to 45713   // MF Ghost S1 (TMDB ID: 154526) - Corrected from ARM API
    )

    /**
     * Per-season Kitsu ID mappings for anime with separate Kitsu entries per season.
     * Format: TMDB_ID -> Map(TMDB_Season -> Kitsu_ID)
     * Episodes use per-season numbering (start at 1 each season).
     */
    private val perSeasonKitsuMap = mapOf(
        95479 to mapOf(
            1 to 42765,  // JJK S1 (24 episodes)
            2 to 45857,  // JJK S2 (23 episodes) - Corrected from ARM API
            3 to 48363   // JJK S3 Culling Game - Corrected from ARM API
        ),
        1429 to mapOf(
            1 to 7442,   // AOT S1 (25 episodes)
            2 to 12960,  // AOT S2 (12 episodes)
            3 to 13569,  // AOT S3 Part 1 (12 episodes) - see seasonSegments for Part 2
            4 to 43469   // AOT Final Season
        ),
        85937 to mapOf(
            1 to 38000,  // Kimetsu S1 (26 episodes)
            2 to 44979,  // Entertainment District (11 episodes)
            3 to 46567,  // Swordsmith Village (11 episodes)
            4 to 48513   // Hashira Training
        ),
        65930 to mapOf(
            1 to 11469,  // MHA S1 (13 episodes)
            2 to 12469,  // MHA S2 (25 episodes)
            3 to 13881,  // MHA S3 (25 episodes)
            4 to 41524,  // MHA S4 (25 episodes)
            5 to 43108,  // MHA S5 (25 episodes)
            6 to 45904,  // MHA S6 (25 episodes)
            7 to 48058   // MHA S7
        ),
        101280 to mapOf(
            1 to 40046,  // Vinland Saga S1 (24 episodes)
            2 to 46262   // Vinland Saga S2 (24 episodes)
        ),
        69122 to mapOf(
            1 to 11696,  // Mob Psycho S1 (12 episodes)
            2 to 13735,  // Mob Psycho S2 (13 episodes)
            3 to 45702   // Mob Psycho S3 (12 episodes)
        ),
        203737 to mapOf(
            1 to 47997,  // Oshi no Ko S1 (11 episodes)
            2 to 48572   // Oshi no Ko S2
        ),
        127532 to mapOf(
            1 to 47058,  // Solo Leveling S1 (12 episodes)
            2 to 48693   // Solo Leveling S2
        ),
        // --- Newly added per-season mappings ---
        202250 to mapOf(
            1 to 45398,  // Spy x Family Part 1 (12 episodes)
            2 to 46873,  // Spy x Family Season 2 (12 episodes)
            3 to 48939   // Spy x Family Season 3 (13 episodes)
        ),
        127064 to mapOf(
            1 to 44973,  // Blue Lock S1 (24 episodes)
            2 to 47245   // Blue Lock VS. U-20 JAPAN (14 episodes)
        ),
        209867 to mapOf(
            1 to 46474,  // Frieren S1 (28 episodes)
            2 to 49240   // Frieren S2 (10 episodes)
        ),
        210232 to mapOf(
            1 to 48269,  // Dandadan S1 (12 episodes)
            2 to 49425   // Dandadan S2 (12 episodes)
        ),
        225439 to mapOf(
            1 to 46300,  // Kaiju No. 8 S1 (12 episodes)
            2 to 48994   // Kaiju No. 8 S2 (11 episodes)
        ),
        92320 to mapOf(
            1 to 41312,  // The Promised Neverland S1 (12 episodes)
            2 to 42220   // The Promised Neverland S2 (11 episodes)
        ),
        71448 to mapOf(
            1 to 11209,  // Re:Zero S1 (25 episodes)
            2 to 42198,  // Re:Zero S2 Part 1 (13 episodes) - see seasonSegments for Part 2
            3 to 47235   // Re:Zero S3 (16 episodes)
        ),
        67133 to mapOf(
            1 to 9965,   // Overlord S1 (13 episodes)
            2 to 13237,  // Overlord II (13 episodes)
            3 to 41174,  // Overlord III (13 episodes)
            4 to 44529   // Overlord IV (13 episodes)
        ),
        60574 to mapOf(
            1 to 8271,   // Tokyo Ghoul S1 (12 episodes)
            2 to 9135,   // Tokyo Ghoul Root A (12 episodes)
            3 to 13929,  // Tokyo Ghoul:re (12 episodes)
            4 to 41359   // Tokyo Ghoul:re 2 (12 episodes)
        ),
        45782 to mapOf(
            1 to 6589,   // SAO S1 (25 episodes)
            2 to 8174,   // SAO II (24 episodes)
            3 to 13893,  // SAO: Alicization (24 episodes)
            4 to 42213   // SAO: Alicization WoU (12 episodes) - see seasonSegments for Part 2
        ),
        62085 to mapOf(
            1 to 5646,   // Steins;Gate (24 episodes)
            2 to 10788   // Steins;Gate 0 (23 episodes)
        ),
        1104 to mapOf(
            1 to 1415,   // Code Geass S1 (25 episodes)
            2 to 2634    // Code Geass R2 (25 episodes)
        ),
        154526 to mapOf(
            1 to 45713,  // MF Ghost S1 (12 episodes) - Corrected from ARM API
            2 to 48337,  // MF Ghost S2 (12 episodes) - Corrected from ARM API
            3 to 49441   // MF Ghost S3 - Corrected from ARM API
        )
    )

    /**
     * Season segments: for anime where TMDB merges multiple Kitsu entries into one season.
     * Format: TMDB_ID -> Map(TMDB_Season -> List(Segment(kitsuId, episodeCount)))
     * e.g. AOT S3: TMDB has 22 episodes, but Kitsu splits into Part 1 (12 eps) + Part 2 (10 eps)
     */
    private data class SeasonSegment(val kitsuId: Int, val episodeCount: Int)

    private val seasonSegments = mapOf(
        // AOT S3: TMDB season 3 has 22 episodes
        // Kitsu: Part 1 = 13569 (12 eps), Part 2 = 41370 (10 eps)
        1429 to mapOf(
            3 to listOf(
                SeasonSegment(kitsuId = 13569, episodeCount = 12),
                SeasonSegment(kitsuId = 41370, episodeCount = 10)
            )
        ),
        // Re:Zero S2: TMDB season 2 may have 25 episodes (Part 1 + Part 2)
        // Kitsu: Part 1 = 42198 (13 eps), Part 2 = 43247 (12 eps)
        71448 to mapOf(
            2 to listOf(
                SeasonSegment(kitsuId = 42198, episodeCount = 13),
                SeasonSegment(kitsuId = 43247, episodeCount = 12)
            )
        ),
        // SAO Alicization WoU: TMDB S4 may have 23 episodes (Part 1 + Part 2)
        // Kitsu: Part 1 = 42213 (12 eps), Part 2 = 42927 (11 eps)
        45782 to mapOf(
            4 to listOf(
                SeasonSegment(kitsuId = 42213, episodeCount = 12),
                SeasonSegment(kitsuId = 42927, episodeCount = 11)
            )
        ),
        // Spy x Family S1: TMDB S1 has 25 episodes (Part 1 + Part 2)
        // Kitsu: Part 1 = 45398 (12 eps), Part 2 = 45619 (13 eps)
        202250 to mapOf(
            1 to listOf(
                SeasonSegment(kitsuId = 45398, episodeCount = 12),
                SeasonSegment(kitsuId = 45619, episodeCount = 13)
            )
        ),
        // MF Ghost: TMDB Season 1 has 29+ episodes spanning 3 Kitsu entries
        // Kitsu IDs from ARM API: S1 = 45713 (12 eps), S2 = 48337 (12 eps), S3 = 49441
        // NOTE: Episode counts are now fetched dynamically from Kitsu API (see resolveDynamicSegmentedEpisode)
        154526 to mapOf(
            1 to listOf(
                SeasonSegment(kitsuId = 45713, episodeCount = 0),   // S1 - count fetched dynamically
                SeasonSegment(kitsuId = 48337, episodeCount = 0),   // S2 - count fetched dynamically
                SeasonSegment(kitsuId = 49441, episodeCount = 0)    // S3 - count fetched dynamically
            )
        )
    )

    /**
     * Anime that use absolute episode numbering (single Kitsu entry).
     * Includes anime like Bleach, Death Note that have single Kitsu entries
     * but multiple TMDB seasons.
     */
    private val absoluteNumberingAnime = setOf(
        37854,  // One Piece (single Kitsu entry, 1000+ eps)
        46260,  // Naruto (single Kitsu entry, 220 eps)
        31910,  // Naruto Shippuden (single Kitsu entry, 500 eps)
        12609,  // Dragon Ball Z (single Kitsu entry, 291 eps)
        68727,  // Dragon Ball Super (single Kitsu entry, 131 eps)
        46298,  // Hunter x Hunter 2011 (single Kitsu entry, 148 eps)
        73223,  // Black Clover (single Kitsu entry, 170 eps)
        30984,  // Bleach (single Kitsu entry, 366 eps)
        13916,  // Death Note (single Kitsu entry, 37 eps)
        31911   // Fullmetal Alchemist Brotherhood (single Kitsu entry, 64 eps)
        // REMOVED: Tokyo Ghoul, SAO, Re:Zero, Overlord — these have per-season Kitsu entries
    )

    /**
     * Season offset maps for anime with absolute numbering.
     * Maps: TMDB Season Number -> Starting absolute episode offset
     */
    private val animeSeasonOffsets = mapOf(
        46298 to mapOf(1 to 0, 2 to 26, 3 to 57, 4 to 87, 5 to 112, 6 to 131),  // HxH
        37854 to mapOf(  // One Piece
            1 to 0, 2 to 61, 3 to 77, 4 to 91, 5 to 130, 6 to 143,
            7 to 195, 8 to 206, 9 to 325, 10 to 336, 11 to 381, 12 to 405,
            13 to 407, 14 to 421, 15 to 458, 16 to 491, 17 to 516, 18 to 522,
            19 to 574, 20 to 628, 21 to 746, 22 to 891
        ),
        // Naruto: TMDB has 5 seasons
        46260 to mapOf(1 to 0, 2 to 35, 3 to 100, 4 to 141, 5 to 183),
        // Naruto Shippuden: TMDB has 21 seasons
        31910 to mapOf(
            1 to 0, 2 to 32, 3 to 53, 4 to 71, 5 to 89, 6 to 112,
            7 to 126, 8 to 143, 9 to 175, 10 to 197, 11 to 222, 12 to 243,
            13 to 261, 14 to 283, 15 to 300, 16 to 321, 17 to 349, 18 to 375,
            19 to 394, 20 to 432, 21 to 459
        ),
        // Dragon Ball Z: TMDB has 9 seasons
        12609 to mapOf(1 to 0, 2 to 39, 3 to 74, 4 to 108, 5 to 139, 6 to 165, 7 to 200, 8 to 220, 9 to 254),
        // Dragon Ball Super: TMDB has 5 seasons
        68727 to mapOf(1 to 0, 2 to 28, 3 to 46, 4 to 77, 5 to 91),
        // Black Clover: TMDB has 4 seasons
        73223 to mapOf(1 to 0, 2 to 51, 3 to 103, 4 to 154),
        // Bleach: TMDB has many arcs as seasons
        30984 to mapOf(
            1 to 0, 2 to 20, 3 to 41, 4 to 63, 5 to 91, 6 to 109,
            7 to 132, 8 to 152, 9 to 167, 10 to 190, 11 to 206, 12 to 230,
            13 to 266, 14 to 310, 15 to 342, 16 to 366
        ),
        // Death Note: TMDB has 1 season, so offset doesn't matter much
        13916 to mapOf(1 to 0),
        // FMA Brotherhood: TMDB has 1 season
        31911 to mapOf(1 to 0)
    )

    // ========== Public API ==========

    /**
     * Check if content is anime - comprehensive check.
     * Uses hardcoded mapping first, then falls back to genre/language detection.
     */
    fun isAnimeContent(tmdbId: Int?, genreIds: List<Int> = emptyList(), originalLanguage: String? = null): Boolean {
        if (tmdbId != null && tmdbToKitsuMap.containsKey(tmdbId)) {
            return true
        }
        val isAnimation = genreIds.contains(16)
        val isJapanese = originalLanguage?.lowercase() == "ja"
        return isAnimation && isJapanese
    }

    /**
     * Main resolution function: resolves the correct Kitsu episode query string
     * for Stremio addons. Uses 5-tier fallback chain.
     *
     * @param tmdbId    TMDB show ID
     * @param tvdbId    TVDB show ID (from TMDB external_ids)
     * @param title     Show title (for Kitsu search fallback)
     * @param imdbId    IMDB ID (for final fallback)
     * @param season    TMDB season number
     * @param episode   TMDB episode number within the season
     * @return Query string like "kitsu:12345:42" or "tt1234567:1:5"
     */
    suspend fun resolveAnimeEpisodeQuery(
        tmdbId: Int?,
        tvdbId: Int?,
        title: String,
        imdbId: String,
        season: Int?,
        episode: Int?
    ): String {
        if (season == null || episode == null) {
            return imdbId
        }

        // --- Tier 1: ARM API (authoritative TMDB -> Kitsu mapping) ---
        // ARM API is the primary source for anime ID mapping because:
        // 1. It's maintained externally and always up-to-date
        // 2. Hardcoded mappings can become stale (e.g., wrong Kitsu IDs)
        // 3. This ensures ALL anime work correctly, not just hardcoded ones
        if (tmdbId != null) {
            val tierArm = resolveTierArm(tmdbId, season, episode)
            if (tierArm != null) {
                return tierArm
            }
        }

        // --- Tier 2: Hardcoded per-season Kitsu map (fallback if ARM fails) ---
        // Only used when ARM API is unavailable or fails
        if (tmdbId != null) {
            val tier2 = resolveTier1PerSeason(tmdbId, season, episode)
            if (tier2 != null) {
                return tier2
            }
        }

        // --- Tier 3: Hardcoded absolute numbering ---
        if (tmdbId != null) {
            val tier3 = resolveTier2Absolute(tmdbId, season, episode)
            if (tier3 != null) {
                return tier3
            }
        }

        // --- Tier 4: Dynamic Kitsu via TVDB mapping ---
        if (tvdbId != null) {
            val tier4 = resolveTier3Tvdb(tvdbId, tmdbId, season, episode)
            if (tier4 != null) {
                return tier4
            }
        }

        // --- Tier 5: Dynamic Kitsu search by title ---
        if (title.isNotBlank()) {
            val tier5 = resolveTier4TitleSearch(title, tmdbId, season, episode)
            if (tier5 != null) {
                return tier5
            }
        }

        // --- Tier 6: IMDB fallback ---
        // Try hardcoded Kitsu ID with validated episode resolution
        // (walks sequels for correct season/cour mapping)
        if (tmdbId != null) {
            val kitsuId = tmdbToKitsuMap[tmdbId]
            if (kitsuId != null) {
                try {
                    val result = resolveValidatedEpisode(kitsuId, tmdbId, season, episode)
                    val query = "kitsu:${result.first}:${result.second}"
                    return query
                } catch (e: Exception) {
                    // If validated resolution fails, fall back to basic
                    val query = "kitsu:$kitsuId:$episode"
                    return query
                }
            }
        }

        val fallback = "$imdbId:$season:$episode"
        return fallback
    }

    // ========== Tier 1: Per-season Kitsu resolution ==========

    private suspend fun resolveTier1PerSeason(tmdbId: Int, season: Int, episode: Int): String? {
        // Check for segmented seasons first (e.g., AOT S3 split into two Kitsu parts)
        seasonSegments[tmdbId]?.get(season)?.let { segments ->
            // Check if any segment has episodeCount = 0 (needs dynamic fetching)
            val needsDynamicFetch = segments.any { it.episodeCount == 0 }
            return if (needsDynamicFetch) {
                resolveDynamicSegmentedEpisode(segments, episode)
            } else {
                resolveSegmentedEpisode(segments, episode)
            }
        }

        // Standard per-season mapping
        val seasonMap = perSeasonKitsuMap[tmdbId] ?: return null
        // BUG FIX: Return null if season not found instead of falling back to last season
        val kitsuId = seasonMap[season] ?: return null
        return "kitsu:$kitsuId:$episode"
    }

    /**
     * Resolve episode within segmented season using hardcoded episode counts.
     * If episode <= first segment count, use first segment's Kitsu ID.
     * Otherwise, subtract and use next segment.
     */
    private fun resolveSegmentedEpisode(segments: List<SeasonSegment>, episode: Int): String {
        var remaining = episode
        for (segment in segments) {
            if (remaining <= segment.episodeCount) {
                return "kitsu:${segment.kitsuId}:$remaining"
            }
            remaining -= segment.episodeCount
        }
        // Past all segments - use last segment with remaining offset
        val last = segments.last()
        return "kitsu:${last.kitsuId}:$remaining"
    }

    /**
     * Resolve episode within segmented season by fetching episode counts from Kitsu API.
     * This is the GLOBAL FIX: Instead of relying on hardcoded episode counts which can be wrong,
     * we fetch the actual episode count from Kitsu for each segment.
     *
     * This handles cases like MF Ghost where TMDB shows 1 season with 29+ episodes,
     * but Kitsu has 3 separate entries (S1: 12, S2: 12, S3: ongoing).
     */
    private suspend fun resolveDynamicSegmentedEpisode(segments: List<SeasonSegment>, episode: Int): String {
        var remaining = episode
        for ((index, segment) in segments.withIndex()) {
            // Fetch episode count from Kitsu API (cached)
            val epCount = getKitsuEpisodeCount(segment.kitsuId)

            if (epCount == null) {
                // If we can't get episode count (ongoing series or API error),
                // and this is the last segment, use it
                if (index == segments.size - 1) {
                    return "kitsu:${segment.kitsuId}:$remaining"
                }
                // Otherwise, assume a reasonable default (12-13 episodes for anime cours)
                val assumedCount = 12
                if (remaining <= assumedCount) {
                    return "kitsu:${segment.kitsuId}:$remaining"
                }
                remaining -= assumedCount
                continue
            }

            if (remaining <= epCount) {
                return "kitsu:${segment.kitsuId}:$remaining"
            }
            remaining -= epCount
        }

        // Past all segments - use last segment with remaining offset
        val last = segments.last()
        return "kitsu:${last.kitsuId}:$remaining"
    }

    // ========== Tier 2: Absolute numbering resolution ==========

    private suspend fun resolveTier2Absolute(tmdbId: Int, season: Int, episode: Int): String? {
        if (!absoluteNumberingAnime.contains(tmdbId)) return null
        val kitsuId = tmdbToKitsuMap[tmdbId] ?: return null
        val offsets = animeSeasonOffsets[tmdbId]
        if (offsets == null) {
            // No offset map available - return null to fall through to dynamic tiers
            return null
        }

        val offset = offsets[season]

        // CRITICAL: Detect if TMDB is already using absolute episode numbering.
        // Some long-running anime (One Piece, Naruto, etc.) use absolute episode numbers
        // within each TMDB season (e.g., S22 has episodes 1089, 1090... not 1, 2, 3...).
        // If offset > 0 and episode >= offset, the episode is already absolute — use it directly.
        val resolvedOffset = offset ?: run {
            // Season beyond the hardcoded map — dynamically calculate from highest known
            val maxKnownSeason = offsets.keys.maxOrNull() ?: return null
            val maxKnownOffset = offsets[maxKnownSeason] ?: return null
            var dynamicOffset = maxKnownOffset
            for (s in maxKnownSeason until season) {
                val cacheKey = "$tmdbId:$s"
                val epCount = cacheMutex.withLock { tmdbSeasonEpCountCache[cacheKey] }
                    ?: try {
                        val seasonDetails = tmdbApi.getTvSeason(tmdbId, s, Constants.TMDB_API_KEY)
                        val count = seasonDetails.episodes.size
                        cacheMutex.withLock {
                            evictIfNeeded(tmdbSeasonEpCountCache)
                            tmdbSeasonEpCountCache[cacheKey] = count
                        }
                        count
                    } catch (e: Exception) {
                        0
                    }
                dynamicOffset += epCount
            }
            dynamicOffset
        }

        if (resolvedOffset > 0 && episode >= resolvedOffset) {
            // Episode number is already absolute (TMDB uses absolute numbering for this anime)
            return "kitsu:$kitsuId:$episode"
        }

        // Episode is per-season — add offset to get absolute
        val absEpisode = resolvedOffset + episode
        return "kitsu:$kitsuId:$absEpisode"
    }

    // ========== Tier 2.5: ARM API (TMDB -> Kitsu) ==========

    /**
     * Resolve anime using ARM API (arm.haglund.dev).
     * ARM provides authoritative mapping from TMDB to Kitsu IDs.
     * Returns a list of Kitsu IDs, one per season/entry.
     *
     * GLOBAL FIX: This tier handles the common case where TMDB shows anime as 1 season
     * with many episodes, but Kitsu has multiple separate entries (e.g., MF Ghost, Spy x Family).
     * By fetching episode counts dynamically from Kitsu, we always get accurate mappings.
     */
    private suspend fun resolveTierArm(tmdbId: Int, season: Int, episode: Int): String? = withContext(Dispatchers.IO) {
        try {
            // Check cache first
            val cachedKitsuIds = cacheMutex.withLock { armTmdbCache[tmdbId] }
            val kitsuIds = cachedKitsuIds ?: fetchArmMapping(tmdbId)

            if (kitsuIds.isNullOrEmpty()) {
                return@withContext null
            }
            // GLOBAL FIX: Handle the case where TMDB has 1 season but Kitsu has multiple entries
            // This is the most common anime numbering discrepancy (e.g., MF Ghost, Spy x Family).
            //
            // Strategy:
            // 1. If TMDB season = 1 and we have multiple Kitsu IDs, walk through ALL entries
            //    because TMDB often merges multiple Kitsu seasons into one.
            // 2. Otherwise, use seasonIndex to pick the correct entry for that TMDB season.

            if (season == 1 && kitsuIds.size > 1) {
                // TMDB season 1 with multiple Kitsu entries - likely merged seasons
                // Walk through all entries to find the right one for this episode
                var remaining = episode
                for (kitsuId in kitsuIds) {
                    val epCount = getKitsuEpisodeCount(kitsuId)
                    if (epCount == null) {
                        // Unknown count (ongoing) - if this is the last entry or episode fits, use it
                        if (kitsuId == kitsuIds.last()) {
                            return@withContext "kitsu:$kitsuId:$remaining"
                        }
                        // Assume typical anime cour (12 episodes) and continue
                        if (remaining <= 12) {
                            return@withContext "kitsu:$kitsuId:$remaining"
                        }
                        remaining -= 12
                        continue
                    }

                    if (remaining <= epCount) {
                        return@withContext "kitsu:$kitsuId:$remaining"
                    }
                    remaining -= epCount
                }

                // Past all entries - use last entry with remaining offset
                val lastId = kitsuIds.last()
                return@withContext "kitsu:$lastId:$remaining"
            }

            // Standard case: TMDB season maps to corresponding Kitsu entry
            val seasonIndex = (season - 1).coerceIn(0, kitsuIds.size - 1)
            val kitsuId = kitsuIds[seasonIndex]

            // Check if episode exceeds this entry's count (cour split within season)
            val episodeCount = getKitsuEpisodeCount(kitsuId)
            if (episodeCount != null && episode > episodeCount && seasonIndex < kitsuIds.size - 1) {
                // Walk through remaining entries
                var remaining = episode
                for (i in seasonIndex until kitsuIds.size) {
                    val id = kitsuIds[i]
                    val count = getKitsuEpisodeCount(id) ?: 99
                    if (remaining <= count) {
                        return@withContext "kitsu:$id:$remaining"
                    }
                    remaining -= count
                }
            }
            "kitsu:$kitsuId:$episode"
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Fetch Kitsu IDs from ARM API for a TMDB ID.
     * Caches the result for future use.
     */
    private suspend fun fetchArmMapping(tmdbId: Int): List<Int>? {
        return try {
            val url = "https://arm.haglund.dev/api/v2/themoviedb?id=$tmdbId"
            val response = streamApi.getArmMappingByTmdb(url)

            // Extract Kitsu IDs from the response (preserves order for seasons)
            val kitsuIds = response.mapNotNull { it.kitsu }

            if (kitsuIds.isNotEmpty()) {
                cacheMutex.withLock {
                    evictIfNeeded(armTmdbCache)
                    armTmdbCache[tmdbId] = kitsuIds
                }
            }
            kitsuIds.ifEmpty { null }
        } catch (e: Exception) {
            null
        }
    }

    // ========== Tier 3: Dynamic TVDB -> Kitsu resolution ==========

    private suspend fun resolveTier3Tvdb(tvdbId: Int, tmdbId: Int?, season: Int, episode: Int): String? = withContext(Dispatchers.IO) {
        try {
            // Check cache first
            val cachedKitsuId = cacheMutex.withLock { tvdbToKitsuCache[tvdbId] }
            val kitsuId = cachedKitsuId ?: resolveKitsuIdFromTvdb(tvdbId)

            if (kitsuId != null) {
                // Use validated resolution that walks sequels if episode is out of range
                val result = resolveValidatedEpisode(kitsuId, tmdbId, season, episode)
                return@withContext "kitsu:${result.first}:${result.second}"
            }
            null
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Resolve Kitsu ID from TVDB ID using Kitsu's mapping API.
     */
    private suspend fun resolveKitsuIdFromTvdb(tvdbId: Int): Int? {
        val url = "https://kitsu.io/api/edge/mappings?filter[externalSite]=thetvdb/series&filter[externalId]=$tvdbId&include=item"
        val response = streamApi.getKitsuMappings(url)

        // Get the anime ID from the mapping relationship
        val mapping = response.data?.firstOrNull { mapping ->
            mapping.relationships?.item?.data?.type == "anime"
        }
        val kitsuId = mapping?.relationships?.item?.data?.id?.toIntOrNull()

        if (kitsuId != null) {
            cacheMutex.withLock {
                evictIfNeeded(tvdbToKitsuCache)
                tvdbToKitsuCache[tvdbId] = kitsuId
            }
        }
        return kitsuId
    }

    // ========== Tier 4: Title search resolution ==========

    private suspend fun resolveTier4TitleSearch(title: String, tmdbId: Int?, season: Int, episode: Int): String? = withContext(Dispatchers.IO) {
        try {
            val key = title.lowercase().trim()
            val cachedKitsuId = cacheMutex.withLock { titleToKitsuCache[key] }
            val kitsuId = cachedKitsuId ?: searchKitsuByTitle(title)

            if (kitsuId != null) {
                // Use validated resolution that walks sequels if episode is out of range
                val result = resolveValidatedEpisode(kitsuId, tmdbId, season, episode)
                return@withContext "kitsu:${result.first}:${result.second}"
            }
            null
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Search Kitsu API for anime by title with improved matching.
     * Tries to match canonical title or English title against the search query.
     */
    private suspend fun searchKitsuByTitle(title: String): Int? {
        val encodedTitle = URLEncoder.encode(title, "UTF-8")
        val url = "https://kitsu.io/api/edge/anime?filter[text]=$encodedTitle&page[limit]=5"
        val response = streamApi.searchKitsuAnime(url)

        val results = response.data ?: return null
        if (results.isEmpty()) return null

        val normalizedQuery = title.lowercase().trim()

        // Try to find an exact or close title match
        val bestMatch = results.firstOrNull { anime ->
            val canonical = anime.attributes?.canonicalTitle?.lowercase()?.trim()
            val english = anime.attributes?.titles?.get("en")?.lowercase()?.trim()
            val enJp = anime.attributes?.titles?.get("en_jp")?.lowercase()?.trim()
            canonical == normalizedQuery || english == normalizedQuery || enJp == normalizedQuery
        } ?: results.firstOrNull() // Fall back to first result

        val kitsuId = bestMatch?.id?.toIntOrNull()
        if (kitsuId != null) {
            cacheMutex.withLock {
                evictIfNeeded(titleToKitsuCache)
                titleToKitsuCache[normalizedQuery] = kitsuId
            }
        }
        return kitsuId
    }

    // ========== Episode number resolution ==========

    /**
     * Validated episode resolution: resolves the correct (kitsuId, episodeNumber) pair.
     *
     * Strategy:
     * 1. Check if the Kitsu entry uses absolute numbering (has sequel = per-season, no sequel + many eps = absolute)
     * 2. For absolute numbering: calculate offset from TMDB seasons
     * 3. For per-season numbering: validate episode is within range, walk sequels if not
     * 4. Returns Pair(resolvedKitsuId, resolvedEpisodeNumber)
     */
    private suspend fun resolveValidatedEpisode(kitsuId: Int, tmdbId: Int?, season: Int, episode: Int): Pair<Int, Int> {
        val episodeCount = getKitsuEpisodeCount(kitsuId)
        val hasSequel = hasKitsuSequel(kitsuId)

        // Determine if this is an absolute-numbering entry
        // Absolute = single Kitsu entry covering all seasons (no sequel, many episodes)
        // Per-season = separate Kitsu entries per season (has sequel, fewer episodes per entry)
        val isAbsoluteNumbering = when {
            // Has sequel → per-season entries (each entry is one season/cour)
            hasSequel -> false
            // No sequel + many episodes → absolute (single long-running entry)
            episodeCount != null && episodeCount > 50 -> true
            // No sequel + unknown count (ongoing) + not season 1 → try absolute
            episodeCount == null && season > 1 -> true
            // Default: per-season
            else -> false
        }

        if (isAbsoluteNumbering) {
            // Absolute numbering: calculate offset from TMDB seasons
            val offset = calculateTmdbSeasonOffset(tmdbId, season)
            // Detect if TMDB already uses absolute episode numbering (e.g., One Piece)
            if (offset > 0 && episode >= offset) {
                return Pair(kitsuId, episode)
            }
            val absEpisode = offset + episode
            return Pair(kitsuId, absEpisode)
        }

        // Per-season numbering: need to find the correct Kitsu entry for this season + episode
        if (season == 1) {
            // Season 1: validate episode is within range
            if (episodeCount != null && episode > episodeCount) {
                // Episode exceeds this entry's count — walk sequels (cours split)
                val result = walkSequelsForEpisode(kitsuId, episode)
                if (result != null) return result
            }
            return Pair(kitsuId, episode)
        }

        // Season 2+: walk the sequel chain to find the correct entry
        // Each sequel represents the next season/cour
        val targetEntry = walkSequelsToSeason(kitsuId, season, episode)
        if (targetEntry != null) return targetEntry

        // Fallback: try absolute offset calculation
        val offset = calculateTmdbSeasonOffset(tmdbId, season)
        if (offset > 0) {
            return Pair(kitsuId, offset + episode)
        }

        // Last resort: use episode as-is with original Kitsu ID
        return Pair(kitsuId, episode)
    }

    /**
     * Walk the sequel chain to find the correct Kitsu entry for a given season.
     * Season 1 = current entry, Season 2 = first sequel, Season 3 = second sequel, etc.
     * Also handles cours splits: if an entry has fewer episodes than requested,
     * subtract and continue to the next sequel.
     */
    private suspend fun walkSequelsToSeason(startKitsuId: Int, targetSeason: Int, episode: Int): Pair<Int, Int>? {
        var currentId = startKitsuId
        var seasonsTraversed = 1
        val maxWalks = 20 // Safety limit

        for (i in 0 until maxWalks) {
            if (seasonsTraversed == targetSeason) {
                // We've reached the target season's entry
                val epCount = getKitsuEpisodeCount(currentId)
                if (epCount != null && episode > epCount) {
                    // Episode exceeds this entry — could be a cours split within this season
                    val result = walkSequelsForEpisode(currentId, episode)
                    if (result != null) return result
                }
                return Pair(currentId, episode)
            }

            // Move to the next sequel
            val sequelId = getKitsuSequelId(currentId)
            if (sequelId == null) {
                return null
            }
            currentId = sequelId
            seasonsTraversed++
        }
        return null
    }

    /**
     * Walk sequels to find the correct entry when episode exceeds current entry's count.
     * Used for cours splits (e.g., TMDB has 24 episodes in one season, Kitsu splits into 2x12).
     * Subtracts episode counts as it walks.
     */
    private suspend fun walkSequelsForEpisode(startKitsuId: Int, episode: Int): Pair<Int, Int>? {
        var currentId = startKitsuId
        var remaining = episode
        val maxWalks = 10 // Safety limit

        for (i in 0 until maxWalks) {
            val epCount = getKitsuEpisodeCount(currentId)
            if (epCount == null) {
                // Unknown count — assume this entry covers the episode
                return Pair(currentId, remaining)
            }
            if (remaining <= epCount) {
                return Pair(currentId, remaining)
            }

            // Subtract this entry's episodes and move to sequel
            remaining -= epCount
            val sequelId = getKitsuSequelId(currentId)
            if (sequelId == null) {
                // No more sequels — use the last entry with remaining offset
                return Pair(currentId, remaining)
            }
            currentId = sequelId
        }
        return null
    }

    /**
     * Check if a Kitsu anime entry has a sequel (cached).
     * Uses the media-relationships endpoint.
     */
    private suspend fun hasKitsuSequel(kitsuId: Int): Boolean {
        cacheMutex.withLock {
            hasSequelCache[kitsuId]?.let { return it }
        }
        // Resolving sequel also populates hasSequelCache
        getKitsuSequelId(kitsuId)
        return cacheMutex.withLock { hasSequelCache[kitsuId] ?: false }
    }

    /**
     * Get the sequel Kitsu ID for an anime entry (cached).
     * Uses Kitsu's media-relationships endpoint to find the "sequel" relationship.
     * Endpoint: /anime/{id}/media-relationships?include=destination&filter[role]=sequel
     */
    private suspend fun getKitsuSequelId(kitsuId: Int): Int? {
        cacheMutex.withLock {
            if (sequelCache.containsKey(kitsuId)) {
                return sequelCache[kitsuId]
            }
        }

        return try {
            // Use the anime's media-relationships sub-resource with include=destination
            val url = "https://kitsu.io/api/edge/anime/$kitsuId/media-relationships?include=destination&filter[role]=sequel&page[limit]=5"
            val response = streamApi.getKitsuMediaRelationships(url)

            // The sequel's Kitsu ID comes from the relationship's destination
            val sequelRel = response.data?.firstOrNull { rel ->
                rel.attributes?.role == "sequel"
            }
            var resolvedSequelId = sequelRel?.relationships?.destination?.data?.id?.toIntOrNull()

            // If the filtered request didn't work, try without filter and find sequel manually
            if (resolvedSequelId == null && (response.data == null || response.data.isEmpty())) {
                try {
                    val unfilteredUrl = "https://kitsu.io/api/edge/anime/$kitsuId/media-relationships?include=destination&page[limit]=20"
                    val unfilteredResponse = streamApi.getKitsuMediaRelationships(unfilteredUrl)
                    val sequel = unfilteredResponse.data?.firstOrNull { rel ->
                        rel.attributes?.role == "sequel"
                    }
                    resolvedSequelId = sequel?.relationships?.destination?.data?.id?.toIntOrNull()
                } catch (e: Exception) {
                }
            }

            cacheMutex.withLock {
                evictIfNeeded(sequelCache)
                sequelCache[kitsuId] = resolvedSequelId
                hasSequelCache[kitsuId] = resolvedSequelId != null
            }

            if (resolvedSequelId != null) {
            }
            resolvedSequelId
        } catch (e: Exception) {
            cacheMutex.withLock {
                sequelCache[kitsuId] = null
                hasSequelCache[kitsuId] = false
            }
            null
        }
    }

    /**
     * Get episode count for a Kitsu anime entry (cached).
     */
    private suspend fun getKitsuEpisodeCount(kitsuId: Int): Int? {
        cacheMutex.withLock {
            episodeCountCache[kitsuId]?.let { return it }
        }

        return try {
            val url = "https://kitsu.io/api/edge/anime/$kitsuId"
            val response = streamApi.getKitsuAnimeDetail(url)
            val count = response.data?.attributes?.episodeCount

            if (count != null) {
                cacheMutex.withLock {
                    evictIfNeeded(episodeCountCache)
                    episodeCountCache[kitsuId] = count
                }
            }
            count
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Calculate the absolute episode offset by summing TMDB episode counts
     * for all seasons before the target season.
     */
    private suspend fun calculateTmdbSeasonOffset(tmdbId: Int?, season: Int): Int {
        if (tmdbId == null || season <= 1) return 0

        var offset = 0
        for (s in 1 until season) {
            val cacheKey = "$tmdbId:$s"
            val epCount = cacheMutex.withLock { tmdbSeasonEpCountCache[cacheKey] }
                ?: try {
                    val seasonDetails = tmdbApi.getTvSeason(tmdbId, s, Constants.TMDB_API_KEY)
                    val count = seasonDetails.episodes.size
                    cacheMutex.withLock {
                        evictIfNeeded(tmdbSeasonEpCountCache)
                        tmdbSeasonEpCountCache[cacheKey] = count
                    }
                    count
                } catch (e: Exception) {
                    0
                }
            offset += epCount
        }
        return offset
    }

    // ========== Cache helpers ==========

    private fun <K> evictIfNeeded(cache: MutableMap<K, *>) {
        if (cache.size >= MAX_CACHE_SIZE) {
            // Remove oldest 20% of entries
            val keysToRemove = cache.keys.take(cache.size / 5)
            keysToRemove.forEach { cache.remove(it) }
        }
    }

    // ========== Legacy compatibility ==========
    // These methods are kept for any code that still references them directly.

    fun getKitsuId(tmdbId: Int): Int? = tmdbToKitsuMap[tmdbId]

    fun isAnime(tmdbId: Int): Boolean = tmdbToKitsuMap.containsKey(tmdbId)

    companion object {
        /**
         * Static anime content detection - can be called without an instance.
         * For use in places where DI isn't available yet.
         */
        @JvmStatic
        fun isAnimeContentStatic(tmdbId: Int?, genreIds: List<Int> = emptyList(), originalLanguage: String? = null): Boolean {
            if (tmdbId != null && staticTmdbIds.contains(tmdbId)) return true
            val isAnimation = genreIds.contains(16)
            val isJapanese = originalLanguage?.lowercase() == "ja"
            return isAnimation && isJapanese
        }

        // Static copy of known TMDB IDs for the companion object
        private val staticTmdbIds = setOf(
            37854, 46260, 31910, 1429, 65930, 85937, 95479, 114410, 202250,
            30984, 13916, 31911, 62085, 1104, 1043, 12609, 68727, 60574,
            45782, 46298, 69122, 101280, 92320, 71448, 67133, 73223,
            127532, 209867, 203737, 127064, 135157, 210232, 225439
        )
    }
}
