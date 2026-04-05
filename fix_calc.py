import re

with open('app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt', 'r') as f:
    content = f.read()

search_str = """    private suspend fun calculateTmdbSeasonOffset(tmdbId: Int?, season: Int): Int {
        if (tmdbId == null || season <= 1) return 0

        var offset = 0
        ensureSeasonEpisodeCountsCached(tmdbId, 1, season)
        for (s in 1 until season) {
            val cacheKey = "$tmdbId:$s"
            val epCount = cacheMutex.withLock { tmdbSeasonEpCountCache[cacheKey] } ?: 0
            offset += epCount
        }
        return offset
    }"""

replace_str = """    private suspend fun calculateTmdbSeasonOffset(tmdbId: Int?, season: Int): Int? {
        if (tmdbId == null || season <= 1) return 0

        var offset = 0
        ensureSeasonEpisodeCountsCached(tmdbId, 1, season)
        for (s in 1 until season) {
            val cacheKey = "$tmdbId:$s"
            val epCount = cacheMutex.withLock { tmdbSeasonEpCountCache[cacheKey] } ?: return null
            offset += epCount
        }
        return offset
    }"""

new_content = content.replace(search_str, replace_str)

with open('app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt', 'w') as f:
    f.write(new_content)
