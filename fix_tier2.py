import re

with open('app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt', 'r') as f:
    content = f.read()

search_str = """        val resolvedOffset = offset ?: run {
            // Season beyond the hardcoded map — dynamically calculate from highest known
            val maxKnownSeason = offsets.keys.maxOrNull() ?: return null
            val maxKnownOffset = offsets[maxKnownSeason] ?: return null
            var dynamicOffset = maxKnownOffset
            ensureSeasonEpisodeCountsCached(tmdbId, maxKnownSeason, season)
            for (s in maxKnownSeason until season) {
                val cacheKey = "$tmdbId:$s"
                val epCount = cacheMutex.withLock { tmdbSeasonEpCountCache[cacheKey] } ?: 0
                dynamicOffset += epCount
            }
            dynamicOffset
        }"""

replace_str = """        val resolvedOffset = offset ?: run {
            // Season beyond the hardcoded map — dynamically calculate from highest known
            val maxKnownSeason = offsets.keys.maxOrNull() ?: return null
            val maxKnownOffset = offsets[maxKnownSeason] ?: return null
            var dynamicOffset = maxKnownOffset
            ensureSeasonEpisodeCountsCached(tmdbId, maxKnownSeason, season)
            for (s in maxKnownSeason until season) {
                val cacheKey = "$tmdbId:$s"
                val epCount = cacheMutex.withLock { tmdbSeasonEpCountCache[cacheKey] } ?: return null
                dynamicOffset += epCount
            }
            dynamicOffset
        }"""

new_content = content.replace(search_str, replace_str)

with open('app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt', 'w') as f:
    f.write(new_content)
