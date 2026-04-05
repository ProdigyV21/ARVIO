import re

with open('app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt', 'r') as f:
    content = f.read()

# Add kotlinx.coroutines.CompletableDeferred import if needed
if "import kotlinx.coroutines.CompletableDeferred" not in content:
    content = content.replace("import kotlinx.coroutines.sync.withLock", "import kotlinx.coroutines.sync.withLock\nimport kotlinx.coroutines.CompletableDeferred")

# Add inFlightRequests
search_cache = """    private val hasSequelCache = mutableMapOf<Int, Boolean>()      // kitsuId -> whether it has a sequel
    private val armTmdbCache = mutableMapOf<Int, List<Int>>()      // tmdbId -> list of Kitsu IDs (one per season)"""
replace_cache = """    private val hasSequelCache = mutableMapOf<Int, Boolean>()      // kitsuId -> whether it has a sequel
    private val armTmdbCache = mutableMapOf<Int, List<Int>>()      // tmdbId -> list of Kitsu IDs (one per season)
    private val inFlightRequests = mutableMapOf<Int, CompletableDeferred<Unit>>() // tmdbId -> guard against concurrent API calls"""
content = content.replace(search_cache, replace_cache)


search_str = """    private suspend fun ensureSeasonEpisodeCountsCached(tmdbId: Int, startSeason: Int, endSeason: Int) {
        var missingAny = false
        cacheMutex.withLock {
            for (s in startSeason until endSeason) {
                if (!tmdbSeasonEpCountCache.containsKey("$tmdbId:$s")) {
                    missingAny = true
                    break
                }
            }
        }

        if (missingAny) {
            try {
                val tvDetails = tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY)
                cacheMutex.withLock {
                    evictIfNeeded(tmdbSeasonEpCountCache)
                    for (season in tvDetails.seasons) {
                        tmdbSeasonEpCountCache["$tmdbId:${season.seasonNumber}"] = season.episodeCount
                    }
                }
            } catch (e: Exception) {
                // Keep the same error handling logic (return 0 in loop if cache miss persists)
            }
        }
    }"""

replace_str = """    private suspend fun ensureSeasonEpisodeCountsCached(tmdbId: Int, startSeason: Int, endSeason: Int) {
        var missingAny = false
        var deferred: CompletableDeferred<Unit>? = null

        cacheMutex.withLock {
            for (s in startSeason until endSeason) {
                if (!tmdbSeasonEpCountCache.containsKey("$tmdbId:$s")) {
                    missingAny = true
                    break
                }
            }

            if (missingAny) {
                val existingRequest = inFlightRequests[tmdbId]
                if (existingRequest != null) {
                    deferred = existingRequest
                } else {
                    inFlightRequests[tmdbId] = CompletableDeferred()
                }
            }
        }

        if (missingAny) {
            if (deferred != null) {
                deferred!!.await()
                return
            }

            try {
                val tvDetails = tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY)
                cacheMutex.withLock {
                    evictIfNeeded(tmdbSeasonEpCountCache)
                    for (season in tvDetails.seasons) {
                        tmdbSeasonEpCountCache["$tmdbId:${season.seasonNumber}"] = season.episodeCount
                    }
                    val completed = inFlightRequests.remove(tmdbId)
                    completed?.complete(Unit)
                }
            } catch (e: Exception) {
                // Keep the same error handling logic (return 0 in loop if cache miss persists)
                cacheMutex.withLock {
                    val completed = inFlightRequests.remove(tmdbId)
                    completed?.complete(Unit)
                }
            }
        }
    }"""

content = content.replace(search_str, replace_str)

with open('app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt', 'w') as f:
    f.write(content)
