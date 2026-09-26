package com.arflix.tv.util

private val EPISODE_RE = Regex("s\\d{1,2}e\\d{1,2}|\\d{1,2}x\\d{1,2}", RegexOption.IGNORE_CASE)

private val RESOLUTIONS = setOf(
    "480p", "576p", "720p", "1080p", "1080i", "2160p", "4k", "4320p", "8k", "uhd"
)

private val SOURCES = setOf(
    "bluray", "blu", "bdrip", "bdremux",
    "webrip", "webdl", "web", "hdrip",
    "hdtv", "pdtv", "dsr",
    "dvd", "dvdrip", "dvdscr",
    "remux",
    "amzn", "nf", "hulu", "dsnp", "atvp", "hmax", "pcok", "cr", "pmtp"
)

private val CODECS = setOf(
    "x264", "x265", "h264", "h265", "hevc", "avc",
    "xvid", "divx", "vp9", "av1", "mpeg2", "mpeg4"
)

private val AUDIO = setOf(
    "aac", "ac3", "eac3", "dts", "atmos", "truehd", "flac",
    "mp3", "opus", "vorbis", "dd5", "dd2", "ddplus"
)

private val NOISE = setOf(
    // release flags
    "ntsc", "pal", "proper", "repack", "readnfo", "extended",
    "theatrical", "imax", "hdr", "hdr10", "hdr10plus", "sdr",
    "dv", "dolbyvision", "hlg", "dubbed", "subbed",
    "dl", "rip", "complete", "internal", "limited",
    // bit depth and audio-format fragments that split off release names ("DTS-HDMa5.1",
    // "DDP5.1", "10bit") — tech tags, not title words; unlisted, each counted as 10
    "8bit", "10bit", "12bit", "hd", "hdma", "hdma5", "ma", "ddp", "ddp5", "ddp2", "dd", "x",
    "dc", "directors", "cut", "unrated", "retail",
    // subtitle type flags
    "esub", "engsub", "hsub", "sub", "subs",
    // channel counts
    "51", "71", "20",
    // language names — not title words
    "english", "french", "german", "spanish", "italian", "portuguese",
    "arabic", "russian", "chinese", "japanese", "korean", "turkish",
    "hebrew", "hindi", "dutch", "polish", "czech", "hungarian",
    "romanian", "swedish", "norwegian", "danish", "finnish", "greek",
    "thai", "vietnamese", "indonesian", "malay"
)

private val FILE_EXT_RE = Regex("\\.(mkv|mp4|avi|mov|wmv|flv|m4v|ts|m2ts)$", RegexOption.IGNORE_CASE)

// Merge "S06 E01" written as separate tokens back into "S06E01"
private val SPLIT_SEASON_RE = Regex("(s\\d{1,2})\\s+(e\\d{1,2})", RegexOption.IGNORE_CASE)

private val PURE_NUMBERS_RE = Regex("\\d+")
private val SUBTITLE_BRACKET_RE = Regex("^\\[[^]]+]")
private val SEPARATOR_RE = Regex("[.\\-_\\s]+")

private const val EPISODE_TITLE_WEIGHT = 2

private fun tokenWeight(token: String): Int = when {
    token.matches(PURE_NUMBERS_RE) -> 0               // pure numbers: noise
    EPISODE_RE.matches(token) -> 8                  // S01E01
    token in RESOLUTIONS -> 3
    // The source decides the CUT (BluRay vs WEB-DL vs HDTV edits differ by seconds), so it
    // outweighs anything but the show title and episode. Was 4 — below an episode-title word.
    token in SOURCES -> 8
    token in CODECS -> 2
    token in AUDIO -> 2
    token in NOISE -> 1
    else -> 10                                       // title word
}

/**
 * Weighted subtitle match score against a stream release name.
 * Returns 0–100. Embedded subtitles should be handled by the caller (return 100).
 *
 * Token weights:
 *   Title word      → 10
 *   Series/episode  → 8  (S01E01)
 *   Release group   → 5  (last dash-separated segment, position-based)
 *   Source          → 8  (bluray, webrip, webdl…)
 *   Episode title   → 2  (title words right after S01E01, e.g. "First Dance")
 *   Resolution      → 3  (1080p, 720p…)
 *   Codec/audio     → 2  (x264, aac…)
 *   Noise           → 1  (ntsc, proper, hdr…)
 *   Pure numbers    → 0  (skipped)
 */
fun weightedSubtitleScore(streamSourceRaw: String, subtitleId: String): Int {
    if (streamSourceRaw.isBlank() || subtitleId.isBlank()) return 0

    // A stream name can reach us URL-encoded, with '+' where the spaces belong — debrid links carry
    // the filename as a query component, and it is decoded somewhere later in the pipeline. Scoring
    // the encoded form tokenises to nothing and every subtitle ties at 0, which silently turns the
    // ranking into arrival order. Observed From S02E04 (Sept 2026): the same eight subtitles scored
    // 0/0/0 against "FROM+2022+S02E04+..." and 81/81/71 against "FROM 2022 S02E04 ..." eight
    // seconds later, so the optimistic pick started from the worst candidate instead of the best.
    val streamSource = streamSourceRaw.replace('+', ' ')

    val cleanId = subtitleId.replace(SUBTITLE_BRACKET_RE, "").trim()

    // Normalise "S06 E01" → "S06E01" so space-separated season/episode merges with combined form
    fun normalise(s: String) = SPLIT_SEASON_RE.replace(s) { it.groupValues[1] + it.groupValues[2] }

    // Separate release group (last dash-separated segment) from the body
    fun bodyAndGroup(s: String): Pair<String, String?> {
        val i = s.lastIndexOf('-')
        return if (i >= 0) {
            val group = s.substring(i + 1).lowercase().trim()
                .replace(FILE_EXT_RE, "")   // strip .mkv / .mp4 etc.
                .ifBlank { null }
            s.substring(0, i) to group
        } else {
            s to null
        }
    }

    val (streamBody, streamGroup) = bodyAndGroup(normalise(streamSource))
    val (subBody, subGroup)       = bodyAndGroup(normalise(cleanId))

    val streamTokens = streamBody.lowercase().split(SEPARATOR_RE).filter { it.length > 1 }
    val subTokenSet  = subBody.lowercase().split(SEPARATOR_RE).filter { it.length > 1 }.toSet()

    var totalWeight   = 0
    var matchedWeight = 0


    var afterEpisode = false
    var inEpisodeTitle = false
    for (token in streamTokens) {
        val base = tokenWeight(token)
        if (EPISODE_RE.matches(token)) {
            afterEpisode = true
            inEpisodeTitle = true
        } else if (base != 10) {
            inEpisodeTitle = false
        }
        val w = if (afterEpisode && inEpisodeTitle && base == 10) EPISODE_TITLE_WEIGHT else base
        if (w == 0) continue
        totalWeight += w
        if (token in subTokenSet) matchedWeight += w
    }

    // Release group: weight 5, detected by position not value
    if (streamGroup != null) {
        totalWeight += 5
        if (streamGroup == subGroup) matchedWeight += 5
    }

    if (totalWeight == 0) return 0
    return (matchedWeight * 100 / totalWeight).coerceIn(0, 100)
}
