import re

with open('app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt', 'r') as f:
    content = f.read()

search_str1 = """        if (isAbsoluteNumbering) {
            // Absolute numbering: calculate offset from TMDB seasons
            val offset = calculateTmdbSeasonOffset(tmdbId, season)
            // Detect if TMDB already uses absolute episode numbering (e.g., One Piece)
            if (offset > 0 && episode >= offset) {
                return Pair(kitsuId, episode)
            }
            val absEpisode = offset + episode
            return Pair(kitsuId, absEpisode)
        }"""

replace_str1 = """        if (isAbsoluteNumbering) {
            // Absolute numbering: calculate offset from TMDB seasons
            val offset = calculateTmdbSeasonOffset(tmdbId, season)
            if (offset != null) {
                // Detect if TMDB already uses absolute episode numbering (e.g., One Piece)
                if (offset > 0 && episode >= offset) {
                    return Pair(kitsuId, episode)
                }
                val absEpisode = offset + episode
                return Pair(kitsuId, absEpisode)
            }
        }"""

search_str2 = """        // Fallback: try absolute offset calculation
        val offset = calculateTmdbSeasonOffset(tmdbId, season)
        if (offset > 0) {
            return Pair(kitsuId, offset + episode)
        }"""

replace_str2 = """        // Fallback: try absolute offset calculation
        val offset = calculateTmdbSeasonOffset(tmdbId, season)
        if (offset != null && offset > 0) {
            return Pair(kitsuId, offset + episode)
        }"""

content = content.replace(search_str1, replace_str1)
content = content.replace(search_str2, replace_str2)

with open('app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt', 'w') as f:
    f.write(content)
